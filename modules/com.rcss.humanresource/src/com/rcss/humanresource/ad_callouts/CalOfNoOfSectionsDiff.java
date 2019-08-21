package com.rcss.humanresource.ad_callouts;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.math.BigDecimal;
import java.math.MathContext;
import java.lang.Long;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import com.rcss.humanresource.data.RCHRQualitystandard;
import com.rcss.humanresource.data.RCHR_Jobcard;
import org.openbravo.dal.service.OBDal;
/*
 * 
 * @author K Vny kumar
 * 
 * 
 * Window:- Dyed Yarn Requirement Header Level..
 * Table:- rchr_dyedyarn...
 * Columns:-  totalends 
 * Calculation Of No. Of sections, WPG length, Remaining Required Cones... in Sort Master Header Level
 * 
 *  
 * */

public class CalOfNoOfSectionsDiff extends SimpleCallout{

	private static final Logger log = Logger.getLogger(CalOfNoOfSectionsDiff.class);
	
	@Override
	protected void execute(CalloutInfo info) throws ServletException{
		
		String strndId = info.getStringParameter("inprchrQualitystandardId", null);
		System.out.println("Inside callout ");
		log.info("This is strndId +++++++++++++"+strndId);
		//String jobcardId = info.getStringParameter("inprchrJobcardId", null);
		
		//BigDecimal totalEnds = info.getBigDecimalParameter("inptotalends");
		
		BigDecimal totalEndsPerRepeat = info.getBigDecimalParameter("inptotalendsperrepeat");
		log.info("This is totalEndsPerRepeat +++++++++++++"+totalEndsPerRepeat);
		
		//RCHR_Jobcard jobCard = OBDal.getInstance().get(RCHR_Jobcard.class, jobcardId);
		
		
		
		RCHRQualitystandard standard = OBDal.getInstance().get(RCHRQualitystandard.class, strndId);
		BigDecimal totalEnds = new BigDecimal(standard.getTotalends());
		BigDecimal warpcrimp = standard.getWarpcrimp();
		
		BigDecimal orderquantity =   standard.getOrderquantity();
		
		log.info("This is orderquantity +++++++++++++ "+orderquantity);
		//Long totalEnds = jobCard.getTotalends();
		BigDecimal warpcount = standard.getWarpcount();
		
		//BigDecimal weftcount = info.getBigDecimalParameter("inpweftcount");
		//log.info("This is warpcrimp "+orderquantity);
		//log.info("This is orderquantity "+warpcrimp);
		// ================Calculation of diff, wpglength and noofsections....==========================
		Long sumOfEndsPerRepeat = totalEndsPerRepeat.longValue(); // Converting BigDecimal to Long Value...
		
		Long quotient = 672/sumOfEndsPerRepeat; // Dividing the value with 672....
		
		Long totalEndsPer = quotient*sumOfEndsPerRepeat; //Total Ends Per Repeat INPUT with multiplication of quotient...
		
		Long noOfSections = totalEnds.longValue()/totalEndsPer; // Calculating the No.Of Sections ie.quotient ... 
		//log.info("This is noOfSections "+noOfSections);
		
		Long total = noOfSections*totalEndsPer; 
		
		Long remainValue = totalEnds.longValue()-total;
		
		
		Long differenceValue = new Long(0);
		if(remainValue>=totalEndsPerRepeat.longValue()){
			differenceValue=remainValue-totalEndsPerRepeat.longValue();
		}else{
			differenceValue = remainValue;
		}
		//noOfSections = totalEnds.divide(new BigDecimal(totalEndsPer));
		
		
		
		BigDecimal bdnoOfSections = totalEnds.divide(new BigDecimal(totalEndsPer),MathContext.DECIMAL32);
		log.info("This is bdnoOfSections +++++++++++++"+bdnoOfSections);
		
		//=====================Calculation of Average Cone length and Average Cone Weight for both First Loading and Second Loading==================================
		Double doubleNoOfSections = new Double(bdnoOfSections.toString());
		BigDecimal wpgLength = orderquantity.add(warpcrimp.divide(new BigDecimal(100),MathContext.DECIMAL32).multiply(orderquantity));
				
		double firstAvgConeLenght = Math.ceil(doubleNoOfSections)*wpgLength.doubleValue()+500;
		double secondAvgConeLenght = Math.floor(doubleNoOfSections)*wpgLength.doubleValue()+500;
		
		log.info("This is first loading Cone Lenght "+firstAvgConeLenght);
		log.info("This is first loading Cone Lenght "+secondAvgConeLenght);
		
		double firstAverageConeWeight = firstAvgConeLenght/1693/warpcount.doubleValue();
		double secondAverageConeWeight = secondAvgConeLenght/1693/warpcount.doubleValue();
		
		log.info("This is first loading Cone Weight "+firstAverageConeWeight);
		log.info("This is Second loading Cone Weight "+secondAverageConeWeight);
		
		//==================================================================================================================================
				
		//log.info("This is totalEnds.longValue()%totalEndsPer ++++++++++++"+totalEnds.longValue()%totalEndsPer);
		//==================================================================================================
		
		info.addResult("inporderquantity", orderquantity);
		info.addResult("inptotalends", standard.getTotalends());
		info.addResult("inpnoofsections", bdnoOfSections);
		
		info.addResult("inptotaldifference", new BigDecimal(differenceValue));
		info.addResult("inpwpglenght", wpgLength);
		
		info.addResult("inpfirstavgconelength", new BigDecimal(firstAvgConeLenght));
		info.addResult("inpsecondavgconelength", new BigDecimal(secondAvgConeLenght));
		
		
		info.addResult("inpfirstavgconeweight", new BigDecimal(firstAverageConeWeight).setScale(3, BigDecimal.ROUND_HALF_EVEN));
		info.addResult("inpsecondavgconeweight", new BigDecimal(secondAverageConeWeight).setScale(3, BigDecimal.ROUND_HALF_EVEN));
	}
}

