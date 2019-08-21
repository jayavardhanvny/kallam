package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPRCount;
import com.redcarpet.production.data.RCPRMachine;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPR_AutoConeCalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strMachineId = info.getStringParameter("inprcprMachineId", null);
        RCPRMachine machine = OBDal.getInstance().get(RCPRMachine.class, strMachineId);
        info.addResult("inpnoofdrumsperspindle", machine.getSpindles());

        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inpstandardefficiency", count.getEffeciencyStandard().toString());

        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimeinmins", shift.getShiftInMins());

        //String strShfitTimeMins = info.getStringParameter("inpshifttimeinmins", null);
        String strStoppageTime = info.getStringParameter("inpmachinestoppagetime", null);

        BigDecimal value = calculateMachineWorkingTime(shift.getShiftInMins().toString(), strStoppageTime);
        info.addResult("inpmachineworkinghours", fr.format(value));

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
