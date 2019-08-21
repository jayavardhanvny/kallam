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
import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;
import com.rcss.humanresource.data.RCHRQualitystandard;
import java.math.BigDecimal;
import java.math.MathContext;

	/* Sizing Data Sheet Window 
	 * Sizing Beams
	 * Calculate Net weight from Gross Weight
	 * Calculate unsizedweight from Gross Weight
	 * Calculate sizepickup from Gross Weight
	 * 
	 * 
	 */

public class RchrSizingBeamCalculationNetWeight extends SimpleCallout{
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	System.out.println("Starting..Callout from sizing beams ");
    	BigDecimal grossWght = info.getBigDecimalParameter("inpgrossWeight");
    	BigDecimal beamlength = info.getBigDecimalParameter("inpbeamlength");
    	BigDecimal tareWght = info.getBigDecimalParameter("inptareWeight");
    	BigDecimal netWeight = grossWght.subtract(tareWght);
    	
    	
    	String sizingId = info.getStringParameter("inprchrSizingsheetId", null);
    	System.out.println("strQualityStandardId "+sizingId);
    	RCHR_Sizingsheet sizing = OBDal.getInstance().get(RCHR_Sizingsheet.class, sizingId);
    	
    	System.out.println("sizing.getGlm() "+sizing.getGlm());
    	BigDecimal unsizedweight = beamlength.multiply(sizing.getGlm());
    	System.out.println("unsizedweight "+unsizedweight);
    	BigDecimal diff = netWeight.subtract(unsizedweight);
    	System.out.println("diff "+diff);
    	
    	BigDecimal sizepickup = diff.divide(unsizedweight,MathContext.DECIMAL32).multiply(new BigDecimal(100));
    	
    	info.addResult("inpnetweight", netWeight);
    	info.addResult("inpunsizedweight", unsizedweight);
    	info.addResult("inpsizepickup", sizepickup);
    	
    }
}
