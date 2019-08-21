package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.apache.log4j.Logger;
import com.rcss.humanresource.data.RCHRQualitystandard;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.hibernate.criterion.Restrictions;	
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.model.ad.utility.Sequence;
import java.lang.StringBuffer;



public class WarpAndWeftyarnRequisition extends SimpleCallout{
	private static final Logger log = Logger.getLogger(WarpAndWeftyarnRequisition.class);
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	//getting Warp GLM Parametrs
    	
        String strndId = info.getStringParameter("inprchrQualitystandardId", null);
       BigDecimal orderQty = info.getBigDecimalParameter("inporderquantity");
    	log.info("orderQty "+orderQty);
        RCHRQualitystandard standard = OBDal.getInstance().get(RCHRQualitystandard.class, strndId);
        
      /*  
        String result = "";
		if(standard.getRchrInspclothtype().getId().equals("436DBA61E1F04BACB00506D89C3408E7")){
			OBCriteria<Sequence> sqCriteria=OBDal.getInstance().createCriteria(Sequence.class); 
			sqCriteria.add(Restrictions.eq(Sequence.PROPERTY_ID, "ACEA24C3EBB14ED4B9E5EC70E1B452D0"));
			
			log.info("Size is "+sqCriteria.list().size());
			long number = 0;
			
			
			for(Sequence sq : sqCriteria.list()){
				log.info("next number "+sq.getNextAssignedNumber());
				//sq.getNextAssignedNumber();
				
				Long nextAssignedValue = sq.getNextAssignedNumber()+sq.getIncrementBy();
				
				result = sq.getPrefix().concat(nextAssignedValue.toString());
			}
			log.info("result "+result);
		}else{
			OBCriteria<Sequence> sqCriteria=OBDal.getInstance().createCriteria(Sequence.class); 
			sqCriteria.add(Restrictions.eq(Sequence.PROPERTY_ID, "A7438CA7E0BA4396A0607A4756DF5210"));
			
			log.info("Else Size is "+sqCriteria.list().size());
			long number = 0;
			
			
			for(Sequence sq : sqCriteria.list()){
				log.info("Else next number "+sq.getNextAssignedNumber());
				//sq.getNextAssignedNumber();
				StringBuffer sb = new StringBuffer("<");
				
				result = sb.append(sq.getPrefix()).append(sq.getNextAssignedNumber().toString()).append(">").toString();
			}
			log.info("in else result "+result);
		}
		*/
        
        
        BigDecimal warpCrimp = standard.getWarpcrimp();
        BigDecimal warp_glm = standard.getWarpglm();
    	BigDecimal weft_glm = standard.getWeftglm();
    	String qstandard = standard.getQstandard();
    	
        BigDecimal ppi = standard.getPpi();
        
        
    	double warpMtrs = orderQty.doubleValue()*(1+warpCrimp.doubleValue()/100);
        double warpyrnReq = (orderQty.doubleValue()*warp_glm.doubleValue())/1000;
    	double weftyrnReq = (orderQty.doubleValue()*weft_glm.doubleValue())/1000;
    	
    	double loomProduction = ((950*24*60*0.85)/39.37)/ppi.doubleValue();
    	
    	log.info("warpMtrs "+warpMtrs);
    	
    	log.info("warpyrnReq "+warpyrnReq);
    	
    	log.info("weftyrnReq "+weftyrnReq);
    	log.info("loomProduction "+loomProduction);
    	
    	
    	//info.addResult("inpdocumentno",result);
    	info.addResult("inptowrapmtrs", new BigDecimal(warpMtrs));
    	info.addResult("inpwarpyarnreq", new BigDecimal(warpyrnReq));
    	info.addResult("inpweftyarnreq", new BigDecimal(weftyrnReq));
    	info.addResult("inpwarpremainingqty",new BigDecimal(warpyrnReq));
	info.addResult("inpweftremainingqty",new BigDecimal(weftyrnReq));
        info.addResult("inploomprdctn", new BigDecimal(loomProduction));
        //info.addResult("inporderquantity", standard.getOrderquantity());      
    }
    
}
