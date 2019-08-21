package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_PrProduct;
import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import javax.servlet.ServletException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.cost.Costing;
import org.openbravo.model.materialmgmt.cost.CostingAlgorithm;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Mateen
 */
@SuppressWarnings("unchecked")
public class RCPR_ProductionReceiptProcess extends DalBaseProcess {

    List<RCPR_CostingPojo> list = Collections.EMPTY_LIST;

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        list = new ArrayList<RCPR_CostingPojo>();

        String id = bundle.getParams().get("Rcpr_Productionrun_ID").toString();
        RCPR_ProductionRun run = OBDal.getInstance().get(RCPR_ProductionRun.class, id);
        OBError error = new OBError();
        error.setType("Success");
        error.setTitle("Success");
        error.setMessage("Process Completed Successfully");
        try {

            for (RCPR_PrProduct line : run.getRCPRPrProductList()) {
                if (!ProductionProcessUtils.isNegativeQuantityAllowed(line)) {
                    throw new Exception("Insufficient Stock at line "+line.getLineNo());
                }
            }

            for (RCPR_PrProduct line : run.getRCPRPrProductList()) {
                MaterialTransaction iLine = insertLineInventory(line);
                RCPR_CostingPojo pojo = new RCPR_CostingPojo(iLine.getProduct(), iLine.getOrganization(), "AVA",
                        line.getUnitPrice(), line.getQuantity(),
                        line.getUnitPrice(), line.getQuantity(),
                        iLine.getOrganization().getCurrency(), iLine);
                list.add(pojo);

            }

            insertCosting(list);
            run.setProductionReceipt(Boolean.TRUE);
            System.out.println("M_Costing Committed Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            error.setTitle("Error");
            error.setType("Error");
            error.setMessage(ex.getMessage());
        }

        bundle.setResult(error);
    }

    private MaterialTransaction insertLineInventory(RCPR_PrProduct line) {

        MaterialTransaction trx = OBProvider.getInstance().get(MaterialTransaction.class);
        trx.setProduct(line.getProduct());
        trx.setUOM(line.getProduct().getUOM());
        trx.setOrganization(line.getOrganization());
        trx.setStorageBin(line.getStorageBin());
        trx.setMovementQuantity(line.getQuantity());
        trx.setMovementDate(new Date());
        trx.setTransactionProcessDate(new Date());
        trx.setMovementType("P+");
        trx.setCostCalculated(Boolean.TRUE);
        trx.setCostingStatus("CC");
        trx.setCurrency(trx.getOrganization().getCurrency());
        trx.setTransactionCost(line.getQuantity().multiply(line.getUnitPrice()));
        trx.setCostingAlgorithm(OBDal.getInstance().get(CostingAlgorithm.class, "B069080A0AE149A79CF1FA0E24F16AB6"));
        trx.setRcprProductionrun(line.getProductionRun());
        trx.setRcprPrproduct(line);
        OBDal.getInstance().save(trx);
        return trx;

    }

    private void insertCosting(List<RCPR_CostingPojo> list) throws ServletException {
        for (RCPR_CostingPojo line : list) {
            Costing costing = OBProvider.getInstance().get(Costing.class);
            costing.setProduct(line.getProduct());
            costing.setOrganization(line.getOrg());
            costing.setCostType(line.getCosttype());
            costing.setCost(getStandardAverageCost(line));
            costing.setQuantity(line.getMovementQty());
            costing.setPrice(line.getCost());
            costing.setTotalMovementQuantity(getCumulativeQty(line.getProduct().getId(), line.getMovementQty()));
            costing.setCurrency(line.getCurrency());
            costing.setInventoryTransaction(line.getInventory());
            costing.setStartingDate(new Date());
            costing.setEndingDate(new Date());
            OBDal.getInstance().save(costing);

        }
    }

    private BigDecimal getCumulativeQty(String pId, BigDecimal movementQty) throws ServletException {
        String cumCost = RCPRProductionCostingLinesData.getCumulativeQty(new DalConnectionProvider(), pId);
        System.out.println("pid " + pId);
        System.out.println("movementqyt " + movementQty);
        return new BigDecimal(cumCost).add(movementQty);
    }

    private BigDecimal getStandardAverageCost(RCPR_CostingPojo obj) throws ServletException {
        String hql1 = "select coalesce(SUM(c.cost), 0)  "
                + " from MaterialMgmtCosting as c where c.product.id = '" + obj.getProduct().getId() + "' ";
        String hql2 = "select coalesce(SUM(c.quantity), 0)  "
                + " from MaterialMgmtCosting as c where c.product.id = '" + obj.getProduct().getId() + "' ";
        double lastCost = getValueFromHQL(hql1);
        double lastQty = getValueFromHQL(hql2);
        double lastQtyRate = lastCost * lastQty;
        double newQtyRate = obj.getCost().doubleValue() * obj.getQuantity().doubleValue();
        double result = (lastQtyRate + newQtyRate) / (lastQty + obj.getQuantity().doubleValue());
        return new BigDecimal(result);
    }

    public double getValueFromHQL(String hql) {
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }
}
