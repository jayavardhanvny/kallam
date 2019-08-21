package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
//import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculateGLM extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
        BigDecimal weight = info.getBigDecimalParameter("inpgrossweight");
        BigDecimal totmtr = info.getBigDecimalParameter("inptotalmtr");
        String sortNo = info.getStringParameter("inprchrQualitystandardId", null);  
        RCHRQualitystandard standard = OBDal.getInstance().get(RCHRQualitystandard.class, sortNo);
        String qstandard = standard.getQstandard();
        
        double netweight = weight.doubleValue()-1;
        //double glm= (new BigDecimal(netweight).divide(totmtr)).multiply(new BigDecimal(1000));
        double glm=(netweight/totmtr.doubleValue())*1000;
        MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
        BigDecimal newglm= new BigDecimal(glm,mc);
        double remGlm =  Math.abs(standard.getAvgglm().doubleValue()-glm);
        //  new MathContext(arg0, arg1);
        info.addResult("inpglm",glm);
        info.addResult("inpremainglm",remGlm);
       
    }
    
}
