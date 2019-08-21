package com.rcss.humanresource.ad_callouts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.rcss.humanresource.data.*;
import javax.servlet.ServletException;

public class RCHR_GasNoOfPresentsCallout extends SimpleCallout{
	@Override
    protected void execute(CalloutInfo info) throws ServletException {
		String id=info.getStringParameter("inprchrEmployeeId", null);	
		String attId = info.getStringParameter("inprchrAttdProcessId", null);
		RchrEmployee emp=OBDal.getInstance().get(RchrEmployee.class,id);	
		RchrAttdProcess payrollmonth = OBDal.getInstance().get(RchrAttdProcess.class, attId);
        Date startDate = payrollmonth.getStartingDate();
        Date presentDate = payrollmonth.getEndingDate();
		double count=0;	      
        count = new PayrollDBSessionUtil(emp.getId(), startDate, presentDate).getNoOfPresentsDailyDemo();
		info.addResult("inpnoofpresents", count);
	}
}