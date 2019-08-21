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
/*
 * 
 * 
 * @ K Vinay kumar
 */
public class RchrJobcardDocumentNo extends SimpleCallout{
	private static final Logger log = Logger.getLogger(RchrJobcardDocumentNo.class);
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	//getting Warp GLM Parametrs
    	
        String strndId = info.getStringParameter("inprchrQualitystandardId", null);
       // BigDecimal orderQty = info.getBigDecimalParameter("inporderquantity");
    	
        RCHRQualitystandard standard = OBDal.getInstance().get(RCHRQualitystandard.class, strndId);
        String result = "";
        
        StringBuffer sb = new StringBuffer("<");
        //long number = 0;
        boolean parity=false;
        OBCriteria<Sequence> sqCriteria = null;
		if(standard.getRchrInspclothtype().getId().equals("436DBA61E1F04BACB00506D89C3408E7")){
			sqCriteria=OBDal.getInstance().createCriteria(Sequence.class); 
			sqCriteria.add(Restrictions.eq(Sequence.PROPERTY_ID, "8C0EFAA5C2C247E6B34D25A9AE2F3109"));
			log.info("Size is "+sqCriteria.list().size());
			parity=true;
		}else{
			sqCriteria=OBDal.getInstance().createCriteria(Sequence.class); 
			sqCriteria.add(Restrictions.eq(Sequence.PROPERTY_ID, "A7438CA7E0BA4396A0607A4756DF5210"));
			
			log.info("Else Size is "+sqCriteria.list().size());
			//long number = 0;
			
		}
		
		for(Sequence sq : sqCriteria.list()){
			log.info("Else next number "+sq.getNextAssignedNumber());
			//sq.getNextAssignedNumber();
			if(parity){
				result = sq.getPrefix().concat(sq.getNextAssignedNumber().toString());
			}else{
				result = sb.append(sq.getPrefix()).append(sq.getNextAssignedNumber().toString()).append(">").toString();
			}
			
		}
		log.info("in else result "+result);
		
		BigDecimal ppi = standard.getPpi();
		
		info.addResult("inpconstruction", standard.getQstandard());
        info.addResult("inpppi", ppi);
		info.addResult("inpdocumentno",result);
    }
}
