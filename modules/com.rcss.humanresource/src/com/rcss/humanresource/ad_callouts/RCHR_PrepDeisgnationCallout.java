

package com.rcss.humanresource.ad_callouts;

import java.math.BigDecimal;

import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;
import org.hibernate.criterion.Restrictions;


import com.rcss.humanresource.data.RchrDesigloomincentives;
import com.rcss.humanresource.data.RchrDesignation;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrSalarystructure;
import com.redcarpet.payroll.data.RCPLLoomCategory;

public class RCHR_PrepDeisgnationCallout extends SimpleCallout{
	@Override
    protected void execute(CalloutInfo info) throws ServletException {
		String id=info.getStringParameter("inprchrDesignationId", null).toString();
		String loomid=info.getStringParameter("inprcplLoomcategoryId", null).toString();
		BigDecimal yield = info.getBigDecimalParameter("inpyield");
	//	System.out.println("yield "+yield);
		//System.out.println("loomid "+loomid);
		RchrDesignation designation = OBDal.getInstance().get(RchrDesignation.class,id);
		//System.out.println(designation.getRchrSalarystructure());
		OBCriteria<RchrDesigloomincentives> desigLoomIncentive = OBDal.getInstance().createCriteria(RchrDesigloomincentives.class);
		desigLoomIncentive.add(Restrictions.eq(RchrDesigloomincentives.PROPERTY_RCHRSALARYSTRUCTURE,designation.getRchrSalarystructure()));
		desigLoomIncentive.add(Restrictions.eq(RchrDesigloomincentives.PROPERTY_RCPLLOOMCATEGORY,OBDal.getInstance().get(RCPLLoomCategory.class,loomid)));
		desigLoomIncentive.add(Restrictions.le(RchrDesigloomincentives.PROPERTY_FROMMTS,yield.longValue()));
		desigLoomIncentive.add(Restrictions.ge(RchrDesigloomincentives.PROPERTY_TOMTS,yield.longValue()));
		BigDecimal amount = BigDecimal.ZERO;
		//System.out.println("1 ");
		//System.out.println("2 "); 
		for(RchrDesigloomincentives desigLoom : desigLoomIncentive.list()){
			//System.out.println("for loop ");
			amount = desigLoom.getAmount();
			System.out.println("amount "+amount);
			//System.out.println("desigLoom.getFrommts() "+desigLoom.getFrommts());
			//System.out.println("desigLoom.gettomts() "+desigLoom.getTomts());
		}
		
		info.addResult("inpamount", amount);
		
	}
}