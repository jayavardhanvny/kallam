package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPRCount;
import com.redcarpet.production.data.RCPRMachine;
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

public class RCPR_SimplexCalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

      BigDecimal bdLbsKgs = getLbsToKgsConstant();
        info.addResult("inplbstokgsconversion", bdLbsKgs);

        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimeinmins", shift.getShiftInMins());

        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inphank", count.getHanks().toString());

        String strMachineId = info.getStringParameter("inprcprMachineId", null);
        RCPRMachine machine = OBDal.getInstance().get(RCPRMachine.class, strMachineId);
        info.addResult("inpallottedspindle", machine.getSpindles());
        info.addResult("inpstdhanksorshift", count.getEffeciencyStandard());

        //String strShfitTimeMins = info.getStringParameter("inpshifttimeinmins", null);
        String strStoppageTime = info.getStringParameter("inpmachinestoppagemins", null);
        BigDecimal value = calculateMachineWorkingTime(shift.getShiftInMins().toString(), strStoppageTime);
        info.addResult("inpmachineworkinghours", fr.format(value));

        String strInitialHank = info.getStringParameter("inpinitialhank", null);
        String strFinalHank = info.getStringParameter("inpfinalhank", null);
        BigDecimal deProducedHanks = new BigDecimal(BigDecimalUtil.getPlainStrNumber(strFinalHank).doubleValue() - BigDecimalUtil.getPlainStrNumber(strInitialHank).doubleValue());
        fr = new DecimalFormat("##.##");
        info.addResult("inpproducedhanks", fr.format(deProducedHanks));

        //BigDecimal deAllottedSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpallottedspindle", null));
        
        BigDecimal deIdleSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpnoofidleorframe", null));
        BigDecimal bdWrkdSpindles = machine.getSpindles().subtract(deIdleSpindles);
        info.addResult("inpworkedspindlesorframe", fr.format(bdWrkdSpindles));

        //BigDecimal deWorkedSpindles = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpworkedspindlesorframe", null));
        //BigDecimal deStandardHanks = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpstdhanksorshift", null));
        //BigDecimal deHanks = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inphank", null));
        //BigDecimal deLbsToKgs = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inplbstokgsconversion", null));
        //BigDecimal deProducedHanks = BigDecimalUtil.getPlainStrNumber(info.getStringParameter("inpproducedhanks", null));

        String strEffeciency = calculateEffeciency(count.getEffeciencyStandard(), deProducedHanks);
        String strActualPrd = calculateActualProductionInKgs(deProducedHanks, count.getHanks(), bdLbsKgs, bdWrkdSpindles);
        String strProdEff = calculateProductionEffeciency(count.getEffeciencyStandard(), deProducedHanks);

        info.addResult("inpefficiency", fr.format(new BigDecimal(strEffeciency)));
        info.addResult("inpactualproductkgs", fr.format(new BigDecimal(strActualPrd)));
        info.addResult("inpprdkgs", fr.format(new BigDecimal(strProdEff)));

    }

    private String calculateEffeciency(BigDecimal deStandardHanks, BigDecimal deProducedHanks) {
        String retVal = "0.0";
        try {
            BigDecimal temp = deProducedHanks.divide(deStandardHanks, 5, RoundingMode.HALF_UP);
            temp = temp.multiply(new BigDecimal(100));
            retVal = BigDecimalUtil.getDecimalPrecisionValue(temp);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return retVal;
    }

    private String calculateActualProductionInKgs(BigDecimal deProducedHanks, BigDecimal deHanks, BigDecimal deLbsToKgs, BigDecimal deWorkedSpindles) {
        String retVal = "0.0";
        try {
            BigDecimal temp = deProducedHanks.divide(deHanks, 5, RoundingMode.HALF_UP);
            temp = temp.divide(deLbsToKgs, 5, RoundingMode.HALF_UP);
            temp = temp.multiply(deWorkedSpindles);
            retVal = BigDecimalUtil.getDecimalPrecisionValue(temp);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return retVal;
    }

    private String calculateProductionEffeciency(BigDecimal deStandardHanks, BigDecimal deProducedHanks) {
        String retVal = "0.0";
        try {
            BigDecimal temp = deProducedHanks.divide(deStandardHanks, 5, RoundingMode.HALF_UP);
            temp = temp.multiply(new BigDecimal(100));
            retVal = BigDecimalUtil.getDecimalPrecisionValue(temp);
        } catch (Exception e) {
            // e.printStackTrace();
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
