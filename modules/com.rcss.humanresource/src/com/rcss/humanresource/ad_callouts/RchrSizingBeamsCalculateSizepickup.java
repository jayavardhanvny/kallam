
package com.rcss.humanresource.ad_callouts;

/**
*
* @author K Vinay kumar 
* 
*/

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.MathContext;
	// Callout on Un Sized Weight...
	// Sizing Data Sheet Window...
	// Sizing Beams...
	// Calculate Size Pickup by using (((Net Weight- Un Sized Weight)/ Un Sized Weight)*100)
public class RchrSizingBeamsCalculateSizepickup extends SimpleCallout{
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	System.out.println("Starting..Callout from sizing beams ");
    	BigDecimal netWght = info.getBigDecimalParameter("inpnetweight");
    	BigDecimal unsizedWght = info.getBigDecimalParameter("inpunsizedweight");
    	BigDecimal diff = netWght.subtract(unsizedWght);
    	
    	BigDecimal sizepickup = diff.divide(unsizedWght,MathContext.DECIMAL32).multiply(new BigDecimal(100));
    	//BigDecimal netWeight = grossWght.subtract(tareWght);
    	info.addResult("inpsizepickup", sizepickup);
    }
}
