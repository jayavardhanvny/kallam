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

public class RCPR_RSBDrawFrameBreakerCalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");

    @Override
    protected void execute(CalloutInfo info) throws ServletException {


        BigDecimal lbsToKgs = getLbsToKgsConstant();
        info.addResult("inplbstokgsconversion", lbsToKgs);

        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimeinmins", shift.getShiftInMins());

        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inpsilverhank", count.getHanks());
        info.addResult("inphankmd", count.getEffeciencyStandard());

        String strInitialHank = info.getStringParameter("inpinitialhank", null);
        String strFinalHank = info.getStringParameter("inpfinalhank", null);
        BigDecimal dbProducedHanks = new BigDecimal(BigDecimalUtil.getPlainStrNumber(strFinalHank).doubleValue()
                - BigDecimalUtil.getPlainStrNumber(strInitialHank).doubleValue());
        info.addResult("inpproducedhanks", fr.format(dbProducedHanks));

        String strShfitTimeMins = info.getStringParameter("inpshifttimeinmins", null);
        String strStoppageTime = info.getStringParameter("inpmachinestoppagemins", null);
        BigDecimal dbMachWrkHrs = calculateMachineWorkingTime(strShfitTimeMins, strStoppageTime);
        info.addResult("inpmachineworkinghours", fr.format(dbMachWrkHrs));

        //(Produced hank / 100% Hank) * 100
        //String strProducedHanks = info.getStringParameter("inpproducedhanks", null);
        //String strHanksMD = info.getStringParameter("inphankmd", null);
        BigDecimal bdEffAch = calculateEffeciencyAchived(dbProducedHanks.toString(), count.getEffeciencyStandard().toString());
        info.addResult("inpefficencyachevied", fr.format(bdEffAch));

        //BigDecimal deProducedhanks = info.getBigDecimalParameter("inpproducedhanks");
        //BigDecimal deSilverhank = info.getBigDecimalParameter("inpsilverhank");
        //BigDecimal deLbs = info.getBigDecimalParameter("inplbstokgsconversion");
        BigDecimal bdPrdnKgs = calculateProductionInKGS(dbProducedHanks, count.getHanks(), lbsToKgs);
        info.addResult("inpprodinkgs", fr.format(bdPrdnKgs));

    }

    private BigDecimal calculateEffeciencyAchived(String strProducedHanks, String strHanksMD) {
        BigDecimal bdProducedHanks = BigDecimalUtil.getPlainStrNumber(strProducedHanks);
        BigDecimal bdHanksMd = BigDecimalUtil.getPlainStrNumber(strHanksMD);
        BigDecimal retVal = BigDecimal.ZERO;
        try {
            retVal = bdProducedHanks.divide(bdHanksMd, 5, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return retVal;

    }

    private BigDecimal calculateProductionInKGS(BigDecimal deProducedhanks, BigDecimal deSilverhank, BigDecimal deLbs) {

        System.out.println("deProducedhanks "+deProducedhanks);
        System.out.println("deSilverhank "+deSilverhank);
        System.out.println("deLbs "+deLbs);
        BigDecimal retVal = BigDecimal.ZERO;
        try {
            retVal = (deProducedhanks.divide(deSilverhank, 5, RoundingMode.HALF_UP)).divide(deLbs, 5, RoundingMode.HALF_UP);
            System.out.println("retVal "+retVal);
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
