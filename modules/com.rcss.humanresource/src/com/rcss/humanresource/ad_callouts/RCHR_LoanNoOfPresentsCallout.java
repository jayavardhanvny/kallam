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

import com.rcss.humanresource.data.RchrAttdProcess;
import com.rcss.humanresource.data.RchrEmployee;
import javax.servlet.ServletException;

public class RCHR_LoanNoOfPresentsCallout extends SimpleCallout{
	@Override
    protected void execute(CalloutInfo info) throws ServletException {
		String id=info.getStringParameter("inprchrEmployeeId", null);
		String date = info.getStringParameter("inpstartdate", null);
		String attId = info.getStringParameter("inprchrAttdProcessId", null);// we have to map the callout to rchrAttdProcessId column
		RchrEmployee emp=OBDal.getInstance().get(RchrEmployee.class,id);
		
		RchrAttdProcess payrollmonth = OBDal.getInstance().get(RchrAttdProcess.class, attId);
        Calendar cal = Calendar.getInstance();
        Date startDate = payrollmonth.getStartingDate();
        Date presentDate = payrollmonth.getEndingDate();
		double count=0;
		double deptstore = 0;
		double messbill = 0;
		double pendingAmount = 0;
		info.addResult("inprchrDesignationId", emp.getDesignation().getId());
		info.addResult("inprchrEmpDeptId", emp.getEmployeeDepartment().getId());
//		BigDecimal presentdays = new BigDecimal(count);
	      
        count = new PayrollDBSessionUtil(emp.getId(), startDate, presentDate).getNoOfPresentsDailyDemo();
        deptstore = new PayrollDBSessionUtil(emp.getId(), startDate, presentDate).getDepartmentStoreRequisition();
		messbill = new PayrollDBSessionUtil(emp.getId(), startDate, presentDate).getMessAmount();
		pendingAmount = new PayrollDBSessionUtil(emp.getId(), startDate, presentDate).getPendingAmount();
       // System.out.println("emp.getDesignation().getId() " +emp.getDesignation().getId());
        //System.out.println("emp.getEmployeeDepartment().getId() " +emp.getEmployeeDepartment().getId());
		info.addResult("inpnoofpresents", count);
		info.addResult("inpdepartmentstore", deptstore);
		info.addResult("inpmessbill", messbill);
		info.addResult("inppendingadvances", pendingAmount);
		
	}
}