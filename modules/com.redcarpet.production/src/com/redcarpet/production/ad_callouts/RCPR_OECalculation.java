package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPRCount;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;

public class RCPR_OECalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");

    protected void execute(CalloutInfo info) throws ServletException {
        //inprcprMachineprocessId
        //inprcprMachineId
        //inprcprCountId
        //inprotarspeedrpm
        //inpstandardefficency
        //inpshifttimemins
        //inpmachineworkingmins
        //inpmachinestoppagemins
        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimemins", shift.getShiftInMins());
        
        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inpstandardefficency", count.getHanks());
        
        String strShiftTimeMins = shift.getShiftInMins().toString();
        String strStoppageTime = info.getStringParameter("inpmachinestoppagemins", null);
        BigDecimal value = calculateMachineWorkingTime(strShiftTimeMins, strStoppageTime);
        info.addResult("inpmachineworkingmins", fr.format(value));
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


    