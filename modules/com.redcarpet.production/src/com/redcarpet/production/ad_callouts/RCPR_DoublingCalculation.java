package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPRCount;
import com.redcarpet.production.data.RCPRMachine;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPR_DoublingCalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimeinmins", shift.getShiftInMins());

        String strMachineId = info.getStringParameter("inprcprMachineId", null);
        RCPRMachine machine = OBDal.getInstance().get(RCPRMachine.class, strMachineId);
        info.addResult("inpnoofspindlesperframemd", machine.getSpindles());

        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inphankspershift", count.getHanks());
        info.addResult("inpproductionconstant", count.getProductionConstanct());

        String strInitialHank = info.getStringParameter("inpinitialhank", null);
        String strFinalHank = info.getStringParameter("inpfinalhank", null);
        BigDecimal deProducedHanksVal = new BigDecimal(BigDecimalUtil.getPlainStrNumber(strFinalHank).doubleValue() - BigDecimalUtil.getPlainStrNumber(strInitialHank).doubleValue());
        info.addResult("inpproducedhanks", fr.format(deProducedHanksVal));

        //BigDecimal dePrdHanks = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpproducedhanks", null));
        //BigDecimal dePrdConst = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpproductionconstant", null));
        String strResult = calculateProducedKgs(deProducedHanksVal, count.getProductionConstanct());
        info.addResult("inpprodinkgs", fr.format(new BigDecimal(strResult)));

        //dePrdHanks = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpproducedhanks", null));
        //BigDecimal deHanksPerShift = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inphankspershift", null));
        strResult = calculateEffeciency(deProducedHanksVal, count.getHanks());
        info.addResult("inpefficency", fr.format(new BigDecimal(strResult)));


    }

    private String calculateProducedKgs(BigDecimal dePrdHanks, BigDecimal dePrdConst) {
        BigDecimal temp = dePrdHanks.multiply(dePrdConst);
        String strResult = BigDecimalUtil.getDecimalPrecisionValue(temp.doubleValue());
        return strResult;
    }

    private String calculateEffeciency(BigDecimal dePrdHanks, BigDecimal deHanksPerShift) {
        BigDecimal temp = new BigDecimal(0);
        try {
            temp = dePrdHanks.divide(deHanksPerShift, 5, RoundingMode.HALF_UP);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        temp = temp.multiply(new BigDecimal(100));
        String strResult = BigDecimalUtil.getDecimalPrecisionValue(temp.doubleValue());
        return strResult;
    }
}
