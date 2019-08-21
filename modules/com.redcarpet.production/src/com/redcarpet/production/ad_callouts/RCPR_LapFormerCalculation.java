package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPRCount;
import com.redcarpet.production.data.RCPRShift;
import com.redcarpet.production.data.RCPRUnitConversion;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPR_LapFormerCalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        BigDecimal bdLbsToKgs = getLbsToKgsConstant();
        info.addResult("inplbstokgsconversion", bdLbsToKgs);

        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimeinmins", shift.getShiftInMins());

        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inpsilverhank", count.getHanks());
        info.addResult("inphankmd", count.getEffeciencyStandard());

        //String strShfitTimeMins = info.getStringParameter("inpshifttimeinmins", null);
        String strStoppageTime = info.getStringParameter("inpmachinestoppagemins", null);
        BigDecimal value = calculateMachineWorkingTime(shift.getShiftInMins().toString(), strStoppageTime);
        info.addResult("inpmachineworkinghours", fr.format(value));

        String strInitialHank = info.getStringParameter("inpinitialhank", null);
        String strFinalHank = info.getStringParameter("inpfinalhank", null);
        BigDecimal bdPrdHanks = new BigDecimal(BigDecimalUtil.getPlainStrNumber(strFinalHank).doubleValue()
                - BigDecimalUtil.getPlainStrNumber(strInitialHank).doubleValue());
        fr = new DecimalFormat("##.##");
        info.addResult("inpproducedhanks", fr.format(bdPrdHanks));

        //(Produced hank / 100% Hank) * 100
        //String strProducedHanks = info.getStringParameter("inpproducedhanks", null);
        //String strHanksMD = info.getStringParameter("inphankmd", null);
        value = calculateEffeciencyAchived(bdPrdHanks.toString(), count.getEffeciencyStandard().toString());
        DecimalFormat df = new DecimalFormat("##.##");
        info.addResult("inpefficencyachevied", df.format(value));

        //java.math.BigDecimal deProducedhanks = info.getBigDecimalParameter("inpproducedhanks");
        //java.math.BigDecimal deSilverhank = info.getBigDecimalParameter("inpsilverhank");
        //java.math.BigDecimal deLbs = info.getBigDecimalParameter("inplbstokgsconversion");
        java.math.BigDecimal result = calculateProductionInKGS(bdPrdHanks, count.getHanks(), bdLbsToKgs);
        info.addResult("inpprodinkgs", fr.format(result));

    }

    private java.math.BigDecimal calculateEffeciencyAchived(String strProducedHanks, String strHanksMD) {
        java.math.BigDecimal bdProducedHanks = BigDecimalUtil.getPlainStrNumber(strProducedHanks);
        java.math.BigDecimal bdHanksMd = BigDecimalUtil.getPlainStrNumber(strHanksMD);
        java.math.BigDecimal retVal = java.math.BigDecimal.ZERO;
        try {
            retVal = bdProducedHanks.divide(bdHanksMd, 5, RoundingMode.HALF_UP).multiply(new java.math.BigDecimal(100));
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return retVal;

    }

    private java.math.BigDecimal calculateProductionInKGS(java.math.BigDecimal deProducedhanks, java.math.BigDecimal deSilverhank, java.math.BigDecimal deLbs) {

        java.math.BigDecimal retVal = java.math.BigDecimal.ZERO;
        try {
            retVal = (deProducedhanks.divide(deSilverhank, 5, RoundingMode.HALF_UP)).divide(deLbs, 5, RoundingMode.HALF_UP);
        } catch (Exception ex) {
            // ex.printStackTrace();
        }
        return retVal;
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

    private BigDecimal getLbsToKgsConstant() {

        OBCriteria<RCPRUnitConversion> criteria = OBDal.getInstance().createCriteria(RCPRUnitConversion.class);
        BigDecimal lbsToKgs = new BigDecimal(0);
        try {
            lbsToKgs = criteria.list().get(0).getDifferenceConstant();
        } catch (Exception e) {
        }
        return lbsToKgs;
    }
}
