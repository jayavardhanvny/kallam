package com.redcarpet.production.ad_callouts;

import java.math.BigDecimal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;

public class RCPR_SpinningWorkedSpindlesCalc extends SimpleCallout {

    protected void execute(CalloutInfo info) throws ServletException {

//        if (StringUtils.equalsIgnoreCase(info.getLastFieldChanged(), "inpworkedspindles")) {
//            BigDecimal deNoOfSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpnofspindle", null));
//            BigDecimal deWorkedSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpworkedspindles", null));
//            String strResult = calculateStoppageSpindles(deNoOfSpindles, deWorkedSpindles);
//            info.addResult("inpstoppagespindles", strResult);
//        }
         if (StringUtils.equalsIgnoreCase(info.getLastFieldChanged(), "inpstoppagespindles")) {//inpstoppagespindles
            BigDecimal deNoOfSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpnofspindle", null));
            BigDecimal deStoppageSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpstoppagespindles", null));
            String bdWrkdSpndls = calculateWorkedSpindles(deNoOfSpindles, deStoppageSpindles);
            info.addResult("inpworkedspindles", bdWrkdSpndls);//inpworkedspindles
        }
    }

    private String calculateWorkedSpindles(BigDecimal deNoOfSpindles, BigDecimal deStoppageSpindles) {
        BigDecimal res = deNoOfSpindles.subtract(deStoppageSpindles);
        String strResult = BigDecimalUtil.getDecimalPrecisionValue(res.doubleValue());
        return strResult;
    }
}


    