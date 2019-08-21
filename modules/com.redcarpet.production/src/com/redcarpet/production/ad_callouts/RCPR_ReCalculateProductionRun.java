package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPR_PrExpense;
import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;

public class RCPR_ReCalculateProductionRun extends SimpleCallout {

    protected void execute(CalloutInfo info) throws ServletException {

        String strProdRunId = info.getStringParameter("inprcprProductionrunId", null);
        if (StringUtils.equals(info.getStringParameter("inpLastFieldChanged", null), "inpamount")) {
            String strExpenseId = info.getStringParameter("Rcpr_Prexpense_ID", null);
            BigDecimal amount = info.getBigDecimalParameter("inpamount");
            updateTotalExpenses(strProdRunId, strExpenseId, amount);
            ReCaluclateProductionRun run = new ReCaluclateProductionRun(strProdRunId, strExpenseId, amount);
            run.ReCalculalte();
        } else if (StringUtils.equals(info.getStringParameter("inpLastFieldChanged", null), "inpquantity")) {
            String strProductId = info.getStringParameter("inpmProductId", null);
            BigDecimal quantity = info.getBigDecimalParameter("inpquantity");
            BigDecimal unitPrice = info.getBigDecimalParameter("inppriceactual");
            info.addResult("inppriceactual", unitPrice);
            info.addResult("inplinenetamt", quantity.multiply(unitPrice));
        }
//        updateHeaderGrossAmt(strProdRunId);
    }

    private void updateTotalExpenses(String strProdRunId, String strExpenseId, BigDecimal newAmt) {

        double retVal = 0;
        RCPR_ProductionRun run = OBDal.getInstance().get(RCPR_ProductionRun.class, strProdRunId);
        for (RCPR_PrExpense ex : run.getRCPRPrExpenseList()) {
            retVal += ex.getAmount().doubleValue();
        }
        RCPR_PrExpense ex = OBDal.getInstance().get(RCPR_PrExpense.class, strExpenseId);
        double result = retVal - ex.getAmount().doubleValue() + newAmt.doubleValue();
        run.setExpenseCost(new BigDecimal(result));
    }

    private void updateHeaderGrossAmt(String strProdRunId) {
        RCPR_ProductionRun run = OBDal.getInstance().get(RCPR_ProductionRun.class, strProdRunId);
        run.setGrossAmount(run.getTotalItemCost().add(run.getExpenseCost()));
    }
}
