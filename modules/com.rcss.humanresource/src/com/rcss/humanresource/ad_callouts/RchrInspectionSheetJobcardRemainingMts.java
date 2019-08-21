package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.rcss.humanresource.data.*;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class RchrInspectionSheetJobcardRemainingMts extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strJobId = info.getStringParameter("inprchrJobcardId", null);
    	//System.out.println(" strJobId "+strJobId);
    	RCHR_Jobcard jobCard = OBDal.getInstance().get(RCHR_Jobcard.class, strJobId);
    	
    	BigDecimal dbtowarpmts = jobCard.getOrderquantity();
    	
    	//System.out.println(" dbtowarpmts "+dbtowarpmts);
    	
    	BigDecimal cumulativeWarpmts = new BigDecimal(0);
    	
    	OBCriteria<RCHR_Inspectionsheet> directwarpcriteria = OBDal.getInstance().createCriteria(RCHR_Inspectionsheet.class);
    	directwarpcriteria.add(Restrictions.eq(RCHR_Inspectionsheet.PROPERTY_RCHRJOBCARD, jobCard));
		if(directwarpcriteria.list().size()==0){
			
		}else{
			for(RCHR_Inspectionsheet emprecords : directwarpcriteria.list()){
				cumulativeWarpmts = cumulativeWarpmts.add(emprecords.getTotalmtr());
				//System.out.println(" cumulativeWarpmts "+cumulativeWarpmts);
			}
		}
		info.addResult("inptowrapmtrs", dbtowarpmts);
		info.addResult("inpwrapdonemtrs", cumulativeWarpmts);
		info.addResult("inpremainingwrapmtrs", dbtowarpmts.subtract(cumulativeWarpmts));
		//info.addResult("inpyarnsuppliier",jobCard.getWarpcustomer());
		
    }
}
    

