package com.rcss.humanresource.ad_callouts;
import java.math.BigDecimal;
import org.openbravo.dal.service.OBDal;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.math.BigDecimal;
import java.lang.Long;
import com.rcss.humanresource.data.RCHRQualitystandard;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import com.rcss.humanresource.data.RchrDyedyarn;
/*
 * 
 * @author K Vny kumar
 * 
 * 
 * Window:- Dyed Yarn Requirement Header Level..
 * Tab:- Weft Requirement..
 * Table:- rchr_weftdyedyarnreq
 * Columns:- picksperrepeat
 * Calculation Of No. Of sections, WPG length, Remaining Required Cones... in Sort Master Header Level
 * 
 *  
 * */

public class RchrWeftRequirementCallout extends SimpleCallout{
	
	private static final Logger log = Logger.getLogger(RchrWeftRequirementCallout.class);
	@Override
	protected void execute(CalloutInfo vny) throws ServletException{
		
		BigDecimal picksPerRepeat = vny.getBigDecimalParameter("inppicksperrepeat");
		String strDyedyarnId = vny.getStringParameter("inprchrDyedyarnId", null);
		
		
		//String strQualitystandardId = vny.getStringParameter("inprchrQualitystandardId", null);
		
		RchrDyedyarn dyedyarn = OBDal.getInstance().get(RchrDyedyarn.class,strDyedyarnId);
		
		RCHRQualitystandard quad = OBDal.getInstance().get(RCHRQualitystandard.class,dyedyarn.getRchrQualitystandard().getId());
		
		//log.info("picksPerRepeat =========="+ picksPerRepeat);
		
		//log.info("quad.getTotalpicksperrepeat() =========="+ quad.getTotalpicksperrepeat());
		
		double averagePercentage = (picksPerRepeat.doubleValue()/dyedyarn.getTotalpicksperrepeat().doubleValue())*100;
		
		
		//log.info("picksPerRepeat.longValue()/quad.getTotalpicksperrepeat() ===888888888888888======"+ picksPerRepeat.longValue()/quad.getTotalpicksperrepeat());
		
		
		//log.info("averagePercentage ======ooooooooooppppp="+ averagePercentage);
		
		
		double totalPicks = quad.getPpi().doubleValue()*averagePercentage/100;
		//log.info("total picks oooooooooooooo 7777777 "+ averagePercentage);
		
		double requiredKgs = 0.591*quad.getReedspace().doubleValue()*totalPicks*1.07/1000/quad.getWeftcount().doubleValue()*quad.getOrderquantity().doubleValue();
		
		//log.info("requiredKgs =====_-----------++++++===="+ requiredKgs);
		
		vny.addResult("inptotalpicks", new BigDecimal(totalPicks));
		vny.addResult("inprequirekgs", new BigDecimal(requiredKgs));
		vny.addResult("inpaveragepercentage", new BigDecimal(averagePercentage));
		
	}
}