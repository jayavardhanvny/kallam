package com.rcss.humanresource.ad_callouts;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.rcss.humanresource.data.*;
import javax.servlet.ServletException;

public class RCHR_Emp_LoanSanctionCallout extends SimpleCallout{
	@Override
    protected void execute(CalloutInfo info) throws ServletException {
		//String advanceId = "";
		// String strLoanId = info.getStringParameter("inprchrEmpLoanId", null);
		// System.out.println(strLoanId);
		// RCHR_Emp_Loan loan = OBDal.getInstance().get(RCHR_Emp_Loan.class, strLoanId);
		 BigDecimal loanamount = info.getBigDecimalParameter("inploanamt");
		 
		 OBCriteria<RCHRSaladvance> advance = OBDal.getInstance().createCriteria(RCHRSaladvance.class);
		    advance.add(Restrictions.le(RCHRSaladvance.PROPERTY_MONTHLIMITFROM, loanamount));
		    advance.add(Restrictions.ge(RCHRSaladvance.PROPERTY_MONTHLIMITTO, loanamount));
		    for(RCHRSaladvance setauthority : advance.list()){
		      // advanceId = setauthority.getId();
		       info.addResult("inprchrSaladvanceId", setauthority.getId());
		}
		  
	       //  info.addResult("inprchrSaladvance", advanceId);
	}
}