package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPRCount;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPR_CardingCalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");
    
    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        if(StringUtils.equals("E9C3524B608E44028B24FC7D61DEA8CC", info.getTabId())){
            info.addResult("inpisoe", "Y");
        }
        
        String strInitialHank = info.getStringParameter("inpinitialhank", null);
        String strFinalHank = info.getStringParameter("inpfinalhank", null);
        BigDecimal bdPrdHanks = new BigDecimal(BigDecimalUtil.getPlainStrNumber(strFinalHank).doubleValue()
                - BigDecimalUtil.getPlainStrNumber(strInitialHank).doubleValue());
        info.addResult("inpproducedhanks", fr.format(bdPrdHanks));

        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimeinmins", shift.getShiftInMins());

        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inphank", count.getHanks());
        info.addResult("inpdeliveryspeedmpm", count.getSpeedStandard());

        //String strShfitTimeMins = info.getStringParameter("inpshifttimeinmins", null);
        String strShiftTimeMins = shift.getShiftInMins().toString();
        String strStoppageTime = info.getStringParameter("inpmacstopagetime", null);
        BigDecimal value = calculateMachineWorkingTime(strShiftTimeMins, strStoppageTime);
        info.addResult("inpmachineworkinghours", fr.format(value));

        //String deliverySpeedMpm = info.getStringParameter("inpdeliveryspeedmpm", null);
        //String strHanks = info.getStringParameter("inphank", null);
        //String strShiftTimeMins = info.getStringParameter("inpshifttimeinmins", null);
        BigDecimal bdPrdShift = calculatePercentageProduction(count.getHanks().toString(), count.getSpeedStandard().toString(), strShiftTimeMins);
        info.addResult("inpproductionorshift", fr.format(bdPrdShift));

        //String strProducedHanks = new BigDecimal(BigDecimalUtil.getPlainStrNumber(strFinalHank).doubleValue()
        //        - BigDecimalUtil.getPlainStrNumber(strInitialHank).doubleValue()).toString();
        //String strProductionShift = info.getStringParameter("inpproductionorshift", null);
        value = effeciency(bdPrdHanks.toString(), bdPrdShift.toString());
        info.addResult("inpefficiency", fr.format(value));
    }

    public BigDecimal calculatePercentageProduction(String strHanks, String deliverySpeedMpm, String strShiftTimeInMins) {
        BigDecimal retVal = BigDecimal.ZERO;
        BigDecimal deHanksPerHour = BigDecimalUtil.getPlainStrNumber(strHanks);
        BigDecimal deDelSpeedMPM = BigDecimalUtil.getPlainStrNumber(deliverySpeedMpm);
        BigDecimal deShiftTimeInMins = BigDecimalUtil.getPlainStrNumber(strShiftTimeInMins);
        //((0.59/ hank) *speed)*(Shift Time mins /1000)
        try {
            retVal = new BigDecimal(0.59).divide(deHanksPerHour, 5, RoundingMode.HALF_UP);
            retVal = retVal.multiply(deDelSpeedMPM);
            retVal = retVal.multiply(deShiftTimeInMins.divide(new BigDecimal(1000)));
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return retVal;
    }

    public BigDecimal effeciency(String strProducedHanks, String strProductionShift) {
        BigDecimal deProducedHanks = BigDecimalUtil.getPlainStrNumber(strProducedHanks);
        BigDecimal deProductionShift = BigDecimalUtil.getPlainStrNumber(strProductionShift);
        BigDecimal retVal = BigDecimal.ZERO;
        try {
            retVal = deProducedHanks.divide(deProductionShift, 5, RoundingMode.HALF_UP);
            retVal = (retVal).multiply(new BigDecimal(100));
        } catch (Exception ex) {
            //  ex.printStackTrace();
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
}