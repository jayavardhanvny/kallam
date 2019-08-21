/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPR_PrProduct;
import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.openbravo.dal.service.OBDal;

/**
 *
 * @author mateen
 */
class ReCaluclateProductionRun {

    private String strProdRunId;
    private String strExpenseId;
    private BigDecimal expAmount;

    public ReCaluclateProductionRun(String strProdRunId, String strExpenseId, BigDecimal expAmount) {
        this.strProdRunId = strProdRunId;
        this.strExpenseId = strExpenseId;
        this.expAmount = expAmount;
    }

    public void ReCalculalte() {
        RCPR_ProductionRun run = OBDal.getInstance().get(RCPR_ProductionRun.class, this.strProdRunId);
        BigDecimal totalExp = run.getExpenseCost();
        BigDecimal outPrdCost = run.getItemCost().multiply(run.getIssueQuantity());
        BigDecimal headCost = totalExp.add(outPrdCost);

        for (RCPR_PrProduct prd : run.getRCPRPrProductList()) {
            BigDecimal ratio = getRatio(prd);
            prd.setLineNetAmount(headCost.multiply(ratio));
            prd.setUnitPrice(prd.getLineNetAmount().divide(prd.getQuantity(), 2, RoundingMode.UP));
        }
    }

    private BigDecimal getRatio(RCPR_PrProduct prod) {
        BigDecimal ratio = prod.getQuantity().divide(prod.getProductionRun().getIssueQuantity(), 2, RoundingMode.UP);
        return ratio;
    }
}
