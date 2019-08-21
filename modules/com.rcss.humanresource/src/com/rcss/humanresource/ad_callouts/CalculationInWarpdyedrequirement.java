package com.rcss.humanresource.ad_callouts;

import org.apache.log4j.Logger;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.math.BigDecimal;
import java.lang.Long;
import com.rcss.humanresource.data.RCHRQualitystandard;
import javax.servlet.ServletException;
import com.rcss.humanresource.data.RchrDyedyarn;
/*
 * 
 * Window:- Dyed Yarn Requirement..
 * Tab:- Warp Requirement...
 * Table:- rchr_warpdyedyarnreq...
 * Columns:-  totalends & differnce
 * 
 * Calculation Of No. Of extraincones, First Loading cones, Second Loading Cones and totalkgsrequired... in  Level
 * 
 */
public class CalculationInWarpdyedrequirement extends SimpleCallout{

	private static final Logger log = Logger.getLogger(CalculationInWarpdyedrequirement.class);
	
	@Override
	protected void execute(CalloutInfo info) throws ServletException{
		
		String strDyedyarnId = info.getStringParameter("inprchrDyedyarnId", null);
		
		BigDecimal endsPerRepeat = info.getBigDecimalParameter("inpendsperrepeat");
		
		BigDecimal totalEndsSingleColor = info.getBigDecimalParameter("inptotalends");
		
		BigDecimal differenceValue = info.getBigDecimalParameter("inpdifferencevalue");
		
		
		
		RchrDyedyarn dyedyarn = OBDal.getInstance().get(RchrDyedyarn.class,strDyedyarnId);
		
		RCHRQualitystandard quad = OBDal.getInstance().get(RCHRQualitystandard.class,dyedyarn.getRchrQualitystandard().getId());
		
		
		Long totalEnds = quad.getTotalends();
		
		Long sumOfEndsPerRepeat = dyedyarn.getTotalendsperrepeat();
		
		Long quotient = 672/sumOfEndsPerRepeat;
		
		
		Long totalEndsPerRepeat = quotient*sumOfEndsPerRepeat;
		
		Long noOfSections = totalEnds/totalEndsPerRepeat;
		
		log.info("This is noOfSections "+noOfSections);
		
		Long total = noOfSections*totalEndsPerRepeat;
		
		
		System.out.println("total "+total);
		
		log.info("This is total "+total);
		
		Long remainValue = totalEnds-total;
		log.info("This is remainValue "+remainValue);
		
		//Long differenceValue = remainValue-sumOfEndsPerRepeat;
		
		//log.info("this is "+differenceValue);
		
		Long divideValue = remainValue/sumOfEndsPerRepeat;
		log.info("This is for "+divideValue);
		
		BigDecimal firstLoadingCones = new BigDecimal(divideValue * endsPerRepeat.longValue()).add(differenceValue);
		
		BigDecimal secondLoadingCones = new BigDecimal((quotient*endsPerRepeat.longValue())-firstLoadingCones.longValue());
		
		log.info("This is quotient*endsPerRepeat.longValue() "+quotient*endsPerRepeat.longValue());
		log.info("This is secondLoadingCones "+secondLoadingCones);
		
		double wpglength = dyedyarn.getWpglenght().doubleValue();
		log.info("This is wpglength "+wpglength);
		
		double requireKgs = (totalEndsSingleColor.doubleValue()*wpglength)/1693/quad.getWarpcount().doubleValue();
		
		log.info("1 is ===================== "+totalEndsSingleColor.doubleValue()*wpglength);
		log.info("2 is ===================== "+(totalEndsSingleColor.doubleValue()*wpglength)/1693);
		log.info("3 is ===================== "+requireKgs);
		
		log.info("requireKgs is ===================== "+requireKgs);
		
		BigDecimal extraInCones = (firstLoadingCones.add(secondLoadingCones)).multiply(new BigDecimal(0.013));
		log.info("extraInCones is ===================== "+extraInCones);
		info.addResult("inprequirekgs", new BigDecimal(requireKgs));
		
		info.addResult("inpfirstloadingcones", firstLoadingCones);
		
		info.addResult("inpsecondloadingcones", secondLoadingCones);
		
		info.addResult("inpextraincones",extraInCones);
		
		info.addResult("inptotalkgsrequired", extraInCones.add(new BigDecimal(requireKgs)));

		//info.addResult("inpdifferencevalue", new BigDecimal(differenceValue));
	}
}