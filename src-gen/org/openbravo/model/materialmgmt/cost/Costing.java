/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2008-2011 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.materialmgmt.cost;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.model.materialmgmt.transaction.ProductionLine;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
/**
 * Entity class for entity MaterialMgmtCosting (stored in table M_Costing).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Costing extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Costing";
    public static final String ENTITY_NAME = "MaterialMgmtCosting";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_MANUAL = "manual";
    public static final String PROPERTY_GOODSSHIPMENTLINE = "goodsShipmentLine";
    public static final String PROPERTY_INVOICELINE = "invoiceLine";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_PRICE = "price";
    public static final String PROPERTY_TOTALMOVEMENTQUANTITY = "totalMovementQuantity";
    public static final String PROPERTY_COSTTYPE = "costType";
    public static final String PROPERTY_PERMANENT = "permanent";
    public static final String PROPERTY_COST = "cost";
    public static final String PROPERTY_PRODUCTIONLINE = "productionLine";
    public static final String PROPERTY_PRODUCTION = "production";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_INVENTORYTRANSACTION = "inventoryTransaction";
    public static final String PROPERTY_CURRENCY = "currency";

    public Costing() {
        setDefaultValue(PROPERTY_MANUAL, false);
        setDefaultValue(PROPERTY_PERMANENT, false);
        setDefaultValue(PROPERTY_PRODUCTION, false);
        setDefaultValue(PROPERTY_ACTIVE, true);
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public Boolean isManual() {
        return (Boolean) get(PROPERTY_MANUAL);
    }

    public void setManual(Boolean manual) {
        set(PROPERTY_MANUAL, manual);
    }

    public ShipmentInOutLine getGoodsShipmentLine() {
        return (ShipmentInOutLine) get(PROPERTY_GOODSSHIPMENTLINE);
    }

    public void setGoodsShipmentLine(ShipmentInOutLine goodsShipmentLine) {
        set(PROPERTY_GOODSSHIPMENTLINE, goodsShipmentLine);
    }

    public InvoiceLine getInvoiceLine() {
        return (InvoiceLine) get(PROPERTY_INVOICELINE);
    }

    public void setInvoiceLine(InvoiceLine invoiceLine) {
        set(PROPERTY_INVOICELINE, invoiceLine);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public BigDecimal getPrice() {
        return (BigDecimal) get(PROPERTY_PRICE);
    }

    public void setPrice(BigDecimal price) {
        set(PROPERTY_PRICE, price);
    }

    public BigDecimal getTotalMovementQuantity() {
        return (BigDecimal) get(PROPERTY_TOTALMOVEMENTQUANTITY);
    }

    public void setTotalMovementQuantity(BigDecimal totalMovementQuantity) {
        set(PROPERTY_TOTALMOVEMENTQUANTITY, totalMovementQuantity);
    }

    public String getCostType() {
        return (String) get(PROPERTY_COSTTYPE);
    }

    public void setCostType(String costType) {
        set(PROPERTY_COSTTYPE, costType);
    }

    public Boolean isPermanent() {
        return (Boolean) get(PROPERTY_PERMANENT);
    }

    public void setPermanent(Boolean permanent) {
        set(PROPERTY_PERMANENT, permanent);
    }

    public BigDecimal getCost() {
        return (BigDecimal) get(PROPERTY_COST);
    }

    public void setCost(BigDecimal cost) {
        set(PROPERTY_COST, cost);
    }

    public ProductionLine getProductionLine() {
        return (ProductionLine) get(PROPERTY_PRODUCTIONLINE);
    }

    public void setProductionLine(ProductionLine productionLine) {
        set(PROPERTY_PRODUCTIONLINE, productionLine);
    }

    public Boolean isProduction() {
        return (Boolean) get(PROPERTY_PRODUCTION);
    }

    public void setProduction(Boolean production) {
        set(PROPERTY_PRODUCTION, production);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public MaterialTransaction getInventoryTransaction() {
        return (MaterialTransaction) get(PROPERTY_INVENTORYTRANSACTION);
    }

    public void setInventoryTransaction(MaterialTransaction inventoryTransaction) {
        set(PROPERTY_INVENTORYTRANSACTION, inventoryTransaction);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

}
