package com.redcarpet.epcg.ad_callouts;
//import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;
import org.hibernate.Session;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.plm.Product;
import java.util.List;

import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.EpcgYarncosttemplatelines;

public class EpcgWarpCostingCallout extends SimpleCallout{
	
	
	@Override
	public void execute(CalloutInfo info) throws ServletException{
		
		//String warpCount = info.getStringParameter("inpmProductId",null);
		//String strHeadertId = info.getStringParameter("inpepcgCostenquiryId",null);
		//BigDecimal epi=info.getBigDecimalParameter("inpepi");
		//String strCostingtempwarp = info.getStringParameter("inpepcgYarncosttemplatelinesW",null);
		String strCostingtempwarp = info.getStringParameter("inpepcgYarncosttemplatelinesId",null);
		String strCostingtempweft = info.getStringParameter("inpepcgYarncosttemplatelinesW",null);
		
		System.out.println("strCostingtempwarp "+strCostingtempwarp);
		System.out.println("strCostingtempweft "+strCostingtempweft);
		//EpcgYarncosttemplatelines warptype=OBDal.getInstance().get(EpcgYarncosttemplatelines.class, strCostingtempwarp);
		
		//String warpyarntype = info.getStringParameter("inprchrWarpyarntypeId",null); //Warp types
	
		//EpcgCostenquiry header = OBDal.getInstance().get(EpcgCostenquiry.class, strHeadertId);
						
		//BigDecimal widthinches = header.getWidthinches();
		
		//int intWarpCount = Integer.parseInt(warptype.getProduct().getName());
		
		EpcgYarncosttemplatelines warptype = null;
		EpcgYarncosttemplatelines wefttype = null;
		Double warpRate = new Double(0);
		Double weftRate =  new Double(0);
		if(!strCostingtempwarp.equals("")){
			try{
				warptype=OBDal.getInstance().get(EpcgYarncosttemplatelines.class, strCostingtempwarp);
			}catch(Exception e){
				
			}
			
			warpRate=warptype.getRate().doubleValue();
			if(warptype!=null){
				info.addResult("inpwarpratekgs",new BigDecimal(warpRate));
			}
		}
		
		if(!strCostingtempweft.equals("")){
			try{
				
				wefttype=OBDal.getInstance().get(EpcgYarncosttemplatelines.class, strCostingtempweft);
			}catch(Exception e){
				
			}
			weftRate=wefttype.getRate().doubleValue();
			if(wefttype!=null){
				info.addResult("inpspeed",new BigDecimal(wefttype.getSpeed()));
				info.addResult("inpefficiency",new BigDecimal(wefttype.getEfficiency()));
				
				//info.addResult("inpactualspeed",new BigDecimal(wefttype.getSpeed()));
				//info.addResult("inpactualefficiency",new BigDecimal(wefttype.getEfficiency()));
				
		    	info.addResult("inpsizingfrommaster",new BigDecimal(wefttype.getSizing()));		    	
		    	info.addResult("inpweftratekgs",new BigDecimal(weftRate));
		    	
		    	info.addResult("inpselvedgecount",wefttype.getProduct().getSearchKey());
		    	info.addResult("inpselvedgeratekgs",new BigDecimal(weftRate));
			}
			
			
		}
		
		
		
		
		//System.out.println("inches"+widthinches.doubleValue());
		//System.out.println("epi"+epi.doubleValue());
		//System.out.println("warp count..."+intWarpCount);
		//double warpwtkgspermts = (((widthinches.doubleValue()*epi.doubleValue()/intWarpCount)/1.693)*1.1)/1000;	
		//System.out.println("WARPWTKGSMTRS"+warpwtkgspermts);
		//double warpwtwithwaste = warpwtkgspermts*1.04;
		//System.out.println("WARPWTKGSWASTE"+warpwtwithwaste);
		
		
		
		
		
		
		
		
    	
    	
    	//info.addResult("inpwarpwtkgspermts",new BigDecimal(warpwtkgspermts).setScale(3, BigDecimal.ROUND_HALF_UP));
    	//info.addResult("inpwarpwtkgswithwaste",new BigDecimal(warpwtwithwaste).setScale(3, BigDecimal.ROUND_HALF_UP));
    	
    	//info.addResult("inpsizingchemicalmts",new BigDecimal(warpwtwithwaste).setScale(3, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(warptype.getSizing()).setScale(3, BigDecimal.ROUND_HALF_UP)));
   	
	}
	
	
}