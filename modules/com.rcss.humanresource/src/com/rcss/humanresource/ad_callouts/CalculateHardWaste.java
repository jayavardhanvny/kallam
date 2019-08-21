package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
//import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculateHardWaste extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	BigDecimal yarnWeight = info.getBigDecimalParameter("inpyarnWeight");
    	BigDecimal hardWaste = info.getBigDecimalParameter("inphardwaste");
    	//BigDecimal totmtr = info.getBigDecimalParameter("inptotalmtr");
    	// BigDecimal beamCount = (beamLength.divide(netWeight.divide(totalEnds,MathContext.DECIMAL32),MathContext.DECIMAL32)).divide(new BigDecimal(1693),MathContext.DECIMAL32);

    	BigDecimal hardPercent = (hardWaste.divide(yarnWeight, MathContext.DECIMAL32)).multiply(new BigDecimal(100));

    	//MathContext mc = new MathContext(2, RoundingMode.);
    	info.addResult("inphardwastePer", hardPercent);
}
    
}


