package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.MathContext;

public class CalculateBPM extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
        //String strndId = info.getStringParameter("inprchrQualitystandardId", null);
    	BigDecimal beamLength = info.getBigDecimalParameter("inpbeamlength");
    	BigDecimal totalEnds = info.getBigDecimalParameter("inptotalends");
    	
        BigDecimal cutend = info.getBigDecimalParameter("inpcutend");
        BigDecimal entanglement = info.getBigDecimalParameter("inpentanglement"); //totalends
        BigDecimal sloughoff = info.getBigDecimalParameter("inpsloughoff");
        BigDecimal polypropyline = info.getBigDecimalParameter("inppolypropyline");
        BigDecimal poorsplice = info.getBigDecimalParameter("inppoorsplice");
        BigDecimal tipdamage = info.getBigDecimalParameter("inptipdamage");
        BigDecimal others = info.getBigDecimalParameter("inpothers");
        BigDecimal weakplace = info.getBigDecimalParameter("inpweakplace");
        
        
        BigDecimal totalBreaks = cutend.add(entanglement).add(sloughoff).add(polypropyline).add(poorsplice).add(tipdamage).add(others).add(weakplace);
        BigDecimal bpm = ((totalBreaks.divide(beamLength,MathContext.DECIMAL32)).divide(totalEnds,MathContext.DECIMAL32)).multiply(new BigDecimal(1000000));
        
        info.addResult("inptotalbreaks", totalBreaks);
        info.addResult("inpbreaksPer", bpm);
        //info.addResult("inpremainingquantity",orderquantity);
        //info.addResult("inprchrInspclothtypeId",strCloth);
       
    }
    
}

