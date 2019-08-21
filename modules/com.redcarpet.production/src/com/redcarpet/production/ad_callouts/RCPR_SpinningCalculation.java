package com.redcarpet.production.ad_callouts;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPR_SpinningCalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strShfitTimeMins = info.getStringParameter("inpshifttimeinmins", null);
        String strStoppageTime = info.getStringParameter("inpmachinestoppagemins", null);
        BigDecimal value = calculateMachineWorkingTime(strShfitTimeMins, strStoppageTime);
        info.addResult("inpmachineworkinghours", fr.format(value));

//        BigDecimal deShiftTime = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpshifttimeinmins", null));
//        BigDecimal deMachineWrkHrs = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpmachineworkinghours", null));
//        BigDecimal deNoOfSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpnofspindle", null));
//        String strResultWorkedSpindles = calculateWorkedSpindles(deNoOfSpindles, deShiftTime, deMachineWrkHrs);
//        info.addResult("inpworkedspindles", strResultWorkedSpindles);

        BigDecimal deNoOfSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpnofspindle", null));
        BigDecimal deStoppageSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpstoppagespindles", null));
        String bdWrkdSpndls = calculateWorkedSpindles(deNoOfSpindles, deStoppageSpindles);
        info.addResult("inpworkedspindles", bdWrkdSpndls);

        BigDecimal dePrdHanks = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpproducedhanks", null));
        BigDecimal dePrdConst = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpproductionconstant", null));
        String strPrdKgs = calculateProducedKgs(dePrdHanks, dePrdConst);
        info.addResult("inpproducedkgs", fr.format(new BigDecimal(strPrdKgs)));

        BigDecimal dePrdKgs = BigDecimalUtil.getPlainStrNumber(strPrdKgs);
        BigDecimal deWrkSpindles = BigDecimalUtil.getPlainStrNumber(bdWrkdSpndls);// info.getStringParameter("inpworkedspindles", null)
        String strPrdGrams = calculateProducedGrams(dePrdKgs, deWrkSpindles);
        info.addResult("inpproducedgrams", fr.format(new BigDecimal(strPrdGrams)));

        //deNoOfSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpnofspindle", null));
        String strUtilization = calculateMachineWiseUtilization(deWrkSpindles, deNoOfSpindles);
        info.addResult("inpmachwiseutilization", fr.format(new BigDecimal(strUtilization)));
    }

    private String calculateWorkedSpindles(BigDecimal deNoOfSpindles, BigDecimal deWorkedSpindles) {
        BigDecimal res = deNoOfSpindles.subtract(deWorkedSpindles);
        String strResult = BigDecimalUtil.getDecimalPrecisionValue(res.doubleValue());
        return strResult;
    }

    private String calculateStoppageSpindles2(BigDecimal deNoOfSpindles, BigDecimal deShiftTime, BigDecimal deMachineWrkHrs) {

        BigDecimal temp = new BigDecimal(0);
        BigDecimal tempWorkedSpindles = new BigDecimal(0);
        BigDecimal deMachineWrkMins = deMachineWrkHrs.multiply(new BigDecimal(60));
        try {
            tempWorkedSpindles = (deNoOfSpindles.divide(deShiftTime)).multiply(deMachineWrkMins);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        temp = deNoOfSpindles.subtract(tempWorkedSpindles);
        return BigDecimalUtil.getDecimalPrecisionValue(temp.doubleValue());
    }

    private String calculateWorkedSpindles(BigDecimal deNoOfSpindles, BigDecimal deShiftTime, BigDecimal deMachineWrkHrs) {
        BigDecimal temp = new BigDecimal(0);

        try {
            temp = deNoOfSpindles.divide(deShiftTime, 5, RoundingMode.HALF_UP);
            temp = temp.multiply(deMachineWrkHrs.multiply(new BigDecimal(60)));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        String strResult = BigDecimalUtil.getDecimalPrecisionValue(temp.doubleValue());
        return strResult;
    }

    private String calculateProducedKgs(BigDecimal dePrdHanks, BigDecimal dePrdConst) {
        BigDecimal temp = dePrdHanks.multiply(dePrdConst);
        String strResult = BigDecimalUtil.getDecimalPrecisionValue(temp.doubleValue());
        return strResult;
    }

    private String calculateProducedGrams(BigDecimal dePrdKgs, BigDecimal deWrkSpindles) {
        BigDecimal temp = new BigDecimal(0);
        temp = dePrdKgs.multiply(new BigDecimal(1000));
        try {
            temp = temp.divide(deWrkSpindles, 5, RoundingMode.HALF_UP);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        String strResult = BigDecimalUtil.getDecimalPrecisionValue(temp.doubleValue());
        return strResult;
    }

    public BigDecimal calculateMachineWorkingTime(String strShiftTimeMins, String strStoppageMins) {

        strShiftTimeMins = getPlainStrNumber(strShiftTimeMins);
        strStoppageMins = getPlainStrNumber(strStoppageMins);
        BigDecimal retVal = BigDecimal.ZERO;
        try {
            double shiftTme = new BigDecimal(strShiftTimeMins).doubleValue();
            double machStopMins = new BigDecimal(strStoppageMins).doubleValue();
            retVal = new BigDecimal((shiftTme - machStopMins));///60
        } catch (Exception ex) {
            // ex.printStackTrace();
        }
        return retVal;
    }

    public String getPlainStrNumber(String value) {
        return StringUtils.equalsIgnoreCase(value, "") ? "0" : value.replace(",", "");
    }

    private String calculateMachineWiseUtilization(BigDecimal deWrkSpindles, BigDecimal deNoOfSpindles) {
        BigDecimal temp = new BigDecimal(0);
        try {
            temp = deWrkSpindles.divide(deNoOfSpindles, 5, RoundingMode.HALF_UP);
            temp = temp.multiply(new BigDecimal(100));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        String strResult = BigDecimalUtil.getDecimalPrecisionValue(temp.doubleValue());
        return strResult;
    }
}
