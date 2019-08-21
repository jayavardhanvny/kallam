package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
//import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculateTotalPoints extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
        BigDecimal totalTwo = info.getBigDecimalParameter("inptotaltwo");
        BigDecimal totalFour = info.getBigDecimalParameter("inptotalfour");
        BigDecimal totmtr = info.getBigDecimalParameter("inptotalmtr");
       
        
        double points= (totalTwo.doubleValue()*2) + (totalFour.doubleValue()*4);
        double totalPoints = (points/totmtr.doubleValue())*100;
        
        MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
        BigDecimal newpoints= new BigDecimal(totalPoints,mc);
        //MathContext mc = new MathContext(2, RoundingMode.);
        info.addResult("inptotalpoints", newpoints);
        
       
    }
    
}
