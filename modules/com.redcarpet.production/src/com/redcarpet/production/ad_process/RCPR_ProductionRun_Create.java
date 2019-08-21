package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_PrBOMExpense;
import com.redcarpet.production.data.RCPR_PrBOMLines;
import com.redcarpet.production.data.RCPR_PrExpense;
import com.redcarpet.production.data.RCPR_PrProduct;
import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import javax.servlet.ServletException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Mateen
 */
public class RCPR_ProductionRun_Create extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcpr_Productionrun_ID").toString();
        RCPR_ProductionRun prun = OBDal.getInstance().get(RCPR_ProductionRun.class, id);
        OBError err = new OBError();
        err.setType("Success");
        err.setTitle("Success");
        err.setMessage("Process Completed Successfully");
        try {
            insertExpenses(prun);
            insertLines(prun);
            updateHeader(prun);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bundle.setResult(err);

    }

    private void insertLines(RCPR_ProductionRun prun) throws ServletException {


        List<RCPR_PrBOMLines> prodList = prun.getBOMProduction().getRCPRPrBOMLinesList();
        int line = 10;
        NumberFormat nf = new DecimalFormat("##.##");
        BigDecimal unitPrice = new BigDecimal(0);
        for (RCPR_PrBOMLines prd : prodList) {
            String client = prun.getClient().getId();
            String org = prun.getOrganization().getId();
            String productId = prd.getProduct().getId();
            String uomId = prd.getProduct().getUOM().getId();
            String locatorId = prun.getBOMProduction().getStorageBin().getId();
            BigDecimal qty = (prd.getRatio().multiply(prun.getIssueQuantity())).divide(new BigDecimal(100));
            BigDecimal lineNetAmt = getProductCost(prun.getExpenseCost(), prun.getIssueQuantity(), prun.getItemCost(), prd.getRatio());
            unitPrice = lineNetAmt.divide(qty, 2, RoundingMode.UP);

            if (prd.isPrimaryProduct()) {
                String strUnitPrice = nf.format(unitPrice);
                String strLNA = nf.format(lineNetAmt);
                RCPRInsertPRLinesData.insert(new DalConnectionProvider(), client, org, prun.getId(),
                        new String(line + ""), productId, uomId, locatorId, qty.toString(), strUnitPrice, strLNA);
            } else {
                String strUnitPrice = nf.format(unitPrice);
                String strLNA = nf.format(lineNetAmt);
                RCPRInsertPRLinesData.insert(new DalConnectionProvider(), client, org, prun.getId(),
                        new String(line + ""), productId, uomId, locatorId, qty.toString(), strUnitPrice, strLNA);
            }

            line = line + 10;
        }
    }

    private BigDecimal getProductCost(BigDecimal expenseCost, BigDecimal outProdCost, BigDecimal outProdQty, BigDecimal ratio) {
        BigDecimal temp = expenseCost.add(outProdCost.multiply(outProdQty));
        return temp.multiply(ratio).divide(new BigDecimal(100));
    }

    private void updateHeader(RCPR_ProductionRun prun) {
        double sumPrd = 0.0;

        for (RCPR_PrProduct prod : prun.getRCPRPrProductList()) {
            sumPrd += prod.getLineNetAmount().doubleValue();
        }
        prun.setTotalItemCost(new BigDecimal(sumPrd));
        prun.setGrossAmount(prun.getTotalItemCost().add(prun.getExpenseCost()));
    }

    private void insertExpenses(RCPR_ProductionRun prun) {
        OBContext.setAdminMode();
        double retVal = 0;
        for (RCPR_PrBOMExpense ex : prun.getBOMProduction().getRCPRPrBOMExpenseList()) {
            RCPR_PrExpense prex = OBProvider.getInstance().get(RCPR_PrExpense.class);
            prex.setExpenseType(ex.getExpenseType());
            prex.setAmount(ex.getAmount());
            prex.setProductionRun(prun);
            retVal += ex.getAmount().doubleValue();
            OBDal.getInstance().save(prex);
        }
        prun.setExpenseCost(new BigDecimal(retVal));
        OBContext.restorePreviousMode();
    }
}
