/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.0  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2012 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 *************************************************************************
 */
package org.openbravo.costing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.costing.CostingServer.TrxType;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.utility.OBDateUtils;
import org.openbravo.financial.FinancialUtils;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.cost.Costing;

public class AverageAlgorithm extends CostingAlgorithm {

  public BigDecimal getTransactionCost() {
    BigDecimal trxCost = super.getTransactionCost();
    // If it is a transaction whose cost has not been calculated based on current average cost
    // calculate new average cost.
    switch (trxType) {
    case Receipt:
    case ReceiptVoid:
    case ShipmentVoid:
    case ShipmentReturn:
    case ShipmentNegative:
    case InventoryIncrease:
    case IntMovementTo:
    case InternalConsNegative:
    case InternalConsVoid:
    case BOMProduct:
    case ManufacturingProduced:
      Costing currentCosting = getProductCost();
      BigDecimal trxCostWithSign = (transaction.getMovementQuantity().signum() == -1) ? trxCost
          .negate() : trxCost;
      BigDecimal newCost = null;
      BigDecimal currentValuedStock = CostingUtils.getCurrentValuedStock(transaction.getProduct(),
          costOrg, transaction.getTransactionProcessDate(), costDimensions, costCurrency);
      BigDecimal currentStock = CostingUtils.getCurrentStock(transaction.getProduct(), costOrg,
          transaction.getTransactionProcessDate(), costDimensions);
      if (currentCosting == null) {
        if (transaction.getMovementQuantity().signum() == 0) {
          newCost = BigDecimal.ZERO;
        } else {
          newCost = trxCostWithSign.divide(transaction.getMovementQuantity(), costCurrency
              .getCostingPrecision().intValue(), RoundingMode.HALF_UP);
        }
      } else {
        BigDecimal newCostAmt = currentValuedStock.add(trxCostWithSign);
        BigDecimal newStock = currentStock.add(transaction.getMovementQuantity());
        if (newStock.signum() == 0) {
          // If stock is zero keep current cost.
          newCost = currentCosting.getCost();
        } else {
          newCost = newCostAmt.divide(newStock, costCurrency.getCostingPrecision().intValue(),
              RoundingMode.HALF_UP);
        }
      }
      insertCost(currentCosting, newCost, currentStock, trxCostWithSign);

    default:
      break;
    }
    return trxCost;
  }

  @Override
  protected BigDecimal getOutgoingTransactionCost() {
    Costing currentCosting = getProductCost();
    if (currentCosting == null) {
      throw new OBException("@NoAvgCostDefined@ @Organization@: " + costOrg.getName()
          + ", @Product@: " + transaction.getProduct().getName() + ", @Date@: "
          + OBDateUtils.formatDate(transaction.getTransactionProcessDate()));
    }
    BigDecimal cost = currentCosting.getCost();
    if (currentCosting.getCurrency() != costCurrency) {
      cost = FinancialUtils.getConvertedAmount(currentCosting.getCost(),
          currentCosting.getCurrency(), costCurrency, transaction.getTransactionProcessDate(),
          costOrg, FinancialUtils.PRECISION_COSTING);
    }
    return transaction.getMovementQuantity().abs().multiply(cost);
  }

  /**
   * In case the Default Cost is used it prioritizes the existence of an average cost.
   */
  @Override
  protected BigDecimal getDefaultCost() {
    if (getProductCost() != null) {
      return getOutgoingTransactionCost();
    }
    return super.getDefaultCost();
  }

  private void insertCost(Costing currentCosting, BigDecimal newCost, BigDecimal currentStock,
      BigDecimal trxCost) {
    Date dateTo = getLastDate();
    if (currentCosting != null) {
      dateTo = currentCosting.getEndingDate();
      currentCosting.setEndingDate(transaction.getTransactionProcessDate());
      OBDal.getInstance().save(currentCosting);
    }
    Costing cost = OBProvider.getInstance().get(Costing.class);
    cost.setCost(newCost);
    cost.setCurrency(costCurrency);
    cost.setStartingDate(transaction.getTransactionProcessDate());
    cost.setEndingDate(dateTo);
    cost.setInventoryTransaction(transaction);
    cost.setProduct(transaction.getProduct());
    if (transaction.getProduct().isProduction()) {
      cost.setOrganization(OBDal.getInstance().get(Organization.class, "0"));
    } else {
      cost.setOrganization(costOrg);
    }
    cost.setQuantity(transaction.getMovementQuantity());
    cost.setTotalMovementQuantity(currentStock.add(transaction.getMovementQuantity()));
    if (transaction.getMovementQuantity().signum() == 0) {
      cost.setPrice(newCost);
    } else {
      cost.setPrice(trxCost.divide(transaction.getMovementQuantity(), costCurrency
          .getPricePrecision().intValue(), BigDecimal.ROUND_HALF_UP));
    }
    cost.setCostType("AVA");
    cost.setManual(false);
    cost.setPermanent(true);
    // FIXME: remove when manufacturing costs are fully migrated
    cost.setProduction(trxType == TrxType.ManufacturingProduced);
    cost.setWarehouse((Warehouse) costDimensions.get(CostDimension.Warehouse));
    OBDal.getInstance().save(cost);
  }

  private Costing getProductCost() {
    Product product = transaction.getProduct();
    Date date = transaction.getTransactionProcessDate();
    StringBuffer where = new StringBuffer();
    where.append(Costing.PROPERTY_PRODUCT + ".id = :product");
    where.append("  and " + Costing.PROPERTY_STARTINGDATE + " <= :startingDate");
    where.append("  and " + Costing.PROPERTY_ENDINGDATE + " > :endingDate");
    where.append("  and " + Costing.PROPERTY_COSTTYPE + " = 'AVA'");
    where.append("  and " + Costing.PROPERTY_COST + " is not null");
    if (costDimensions.get(CostDimension.Warehouse) != null) {
      where.append("  and " + Costing.PROPERTY_WAREHOUSE + ".id = :warehouse");
    } else {
      where.append("  and " + Costing.PROPERTY_WAREHOUSE + " is null");
    }
    // FIXME: remove when manufacturing costs are fully migrated
    if (product.isProduction()) {
      where.append("  and " + Costing.PROPERTY_CLIENT + ".id = :client");
    } else {
      where.append("  and " + Costing.PROPERTY_ORGANIZATION + ".id = :org");
    }
    OBQuery<Costing> costQry = OBDal.getInstance().createQuery(Costing.class, where.toString());
    costQry.setFilterOnReadableOrganization(false);
    costQry.setNamedParameter("product", product.getId());
    costQry.setNamedParameter("startingDate", date);
    costQry.setNamedParameter("endingDate", date);
    if (costDimensions.get(CostDimension.Warehouse) != null) {
      costQry.setNamedParameter("warehouse", costDimensions.get(CostDimension.Warehouse).getId());
    }
    // FIXME: remove when manufacturing costs are fully migrated
    if (product.isProduction()) {
      costQry.setNamedParameter("client", costOrg.getClient());
    } else {
      costQry.setNamedParameter("org", costOrg);
    }

    List<Costing> costList = costQry.list();
    // If no average cost is found return null.
    if (costList.size() == 0) {
      return null;
    }
    if (costList.size() > 1) {
      log4j.warn("More than one cost found for same date: " + OBDateUtils.formatDate(date)
          + " for product: " + product.getName() + " (" + product.getId() + ")");
    }
    return costList.get(0);
  }

  private Date getLastDate() {
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
    try {
      return outputFormat.parse("31-12-9999");
    } catch (ParseException e) {
      // Error parsing the date.
      log4j.error("Error parsing the date.", e);
      return null;
    }
  }

}
