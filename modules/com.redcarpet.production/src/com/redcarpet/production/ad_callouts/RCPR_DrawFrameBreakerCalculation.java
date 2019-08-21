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

public class RCPR_DrawFrameBreakerCalculation extends SimpleCallout {

    DecimalFormat fr = new DecimalFormat("##.##");

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        
        if(StringUtils.equals("B1B09F5BF1B64CB5A0DB4EC0D75990AA", info.getTabId())){
            info.addResult("inpisoe", "Y");
        }
        BigDecimal lbsToKgs = getLbsToKgsConstant();
        info.addResult("inplbstokgsconversion", lbsToKgs);

        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimeinmins", shift.getShiftInMins());

        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inpsilverhank", count.getHanks());
        info.addResult("inphankmd", count.getEffeciencyStandard().toString());

        String strInitialHank = info.getStringParameter("inpinitialhank", null);
        String strFinalHank = info.getStringParameter("inpfinalhank", null);
        BigDecimal bdPrdHanks = new BigDecimal(BigDecimalUtil.getPlainStrNumber(strFinalHank).doubleValue() - BigDecimalUtil.getPlainStrNumber(strInitialHank).doubleValue());
        info.addResult("inpproducedhanks", fr.format(bdPrdHanks));

        //String strShfitTimeMins = info.getStringParameter("inpshifttimeinmins", null);
        String strShfitTimeMins = shift.getShiftInMins().toString();
        String strStoppageTime = info.getStringParameter("inpmachinestoppagemins", null);
        BigDecimal bdMachWrkTime = calculateMachineWorkingTime(strShfitTimeMins, strStoppageTime);
        info.addResult("inpmachineworkinghours", fr.format(bdMachWrkTime));


        //BigDecimal deProducedhanks = info.getBigDecimalParameter("inpproducedhanks");
        //BigDecimal deSilverhank = info.getBigDecimalParameter("inpsilverhank");
        //BigDecimal deLbs = info.getBigDecimalParameter("inplbstokgsconversion");
        BigDecimal result = calculateProductionInKGS(bdPrdHanks, count.getHanks(), lbsToKgs);
        info.addResult("inpprodinkgs", fr.format(result));

        //String strProducedHanks = info.getStringParameter("inpproducedhanks", null);
        //String strHanksMd = info.getStringParameter("inphankmd", null);
        BigDecimal bdEffAchived = calculateEffeciencyAchived(bdPrdHanks.toString(), count.getEffeciencyStandard().toString());
        info.addResult("inpefficencyachevied", fr.format(bdEffAchived));

    }

    private BigDecimal calculateEffeciencyAchived(String strProducedHanks, String hanksMd) {

        BigDecimal retVal = BigDecimal.ZERO;
        try {
            strProducedHanks = StringUtils.equalsIgnoreCase(strProducedHanks, "") ? "0" : strProducedHanks;
            hanksMd = StringUtils.equalsIgnoreCase(hanksMd, "") ? "0" : hanksMd;
            retVal = new BigDecimal(strProducedHanks).divide(new BigDecimal(hanksMd), 5, RoundingMode.HALF_UP);
            retVal = retVal.multiply(new BigDecimal(100));
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return retVal;

    }

    private BigDecimal calculateProductionInKGS(BigDecimal deProducedhanks, BigDecimal deSilverhank, BigDecimal deLbs) {
        BigDecimal retVal = BigDecimal.ZERO;
        try {
            BigDecimal subValue = (deProducedhanks.divide(deSilverhank, 5, RoundingMode.HALF_UP)).divide(deLbs, 5, RoundingMode.HALF_UP);
            retVal = subValue.multiply(new BigDecimal(2));
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
    
}
