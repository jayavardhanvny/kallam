package com.rcss.humanresource.ad_actionButtons;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;
import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.data.RCPLPayrollpaidproc;
import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class Payrollpaidlist extends DalBaseProcess{
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		RCPL_PayrollProcess payrollid = null;
	
		String headerid = (String) bundle.getParams().get("Rcpl_Payrollpaidproc_ID");
		RCPLPayrollpaidproc paidheader = OBDal.getInstance().get(RCPLPayrollpaidproc.class, headerid);
		
				
		OBCriteria<RCPL_PayrollProcess> process = OBDal.getInstance().createCriteria(RCPL_PayrollProcess.class);
		process.add(Restrictions.eq(RCPL_PayrollProcess.PROPERTY_PAYROLLPERIOD, paidheader.getPayrollPeriod()));
		for(RCPL_PayrollProcess payrollproc : process.list()){
			payrollid = payrollproc;
		}
		
		OBCriteria<RCPL_EmpPayrollProcess> empprocess = OBDal.getInstance().createCriteria(RCPL_EmpPayrollProcess.class);
		empprocess.add(Restrictions.eq(RCPL_EmpPayrollProcess.PROPERTY_PAYROLLPROCESS, payrollid));
		empprocess.add(Restrictions.gt(RCPL_EmpPayrollProcess.PROPERTY_TOTALPAY, new BigDecimal(0)));
		//empprocess.add(Restrictions.eq(RCPL_EmpPayrollProcess.PROPERTY_EMPLOYEEDEPARTMENT, paidheader.getEmployeeDepartment()));
		//empprocess.add(Restrictions.eq(RCPL_EmpPayrollProcess.PROPERTY_SUBDEPARTMENT, paidheader.getSubDepartment()));
		empprocess.add(Restrictions.ne(RCPL_EmpPayrollProcess.PROPERTY_ISBANK, paidheader.isByhand()));
		for(RCPL_EmpPayrollProcess emprecords : empprocess.list()){
			
			RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, emprecords.getEmployee().getId());
			
			if(emp.getEmployeeType().equals(paidheader.getEmployeeType())){
				RCPLPayrollpaidprocline  paidlines = OBProvider.getInstance().get(RCPLPayrollpaidprocline.class);
				paidlines.setRcplPayrollpaidproc(paidheader);
				paidlines.setOrganization(paidheader.getOrganization());
				paidlines.setEmployee(emprecords.getEmployee());
				paidlines.setStartingDate(emprecords.getStartingDate());
				paidlines.setEndingDate(emprecords.getEndingDate());
				paidlines.setProcessingDays(emprecords.getProcessingDays().longValue());
				paidlines.setAdditions(emprecords.getAdditions());
				paidlines.setDeductions(emprecords.getDeductions());
				paidlines.setIncentiveAdditions(emprecords.getIncentiveAdditions());
				paidlines.setIncentiveDeductions(emprecords.getIncentiveDeductions());
				paidlines.setTotalPay(emprecords.getTotalPay());
				paidlines.setLateDeduction(emprecords.getLateDeduction());
				paidlines.setGrandTotalAmount(emprecords.getGrandTotalAmount());
				paidlines.setPresentdays(emprecords.getPresentdays().longValue());
				paidlines.setWeeklyOff(emprecords.getWeeklyOff().longValue());
				paidlines.setLeaves(emprecords.getLeaves().longValue());
				paidlines.setAbsents(emprecords.getAbsents().longValue());
				paidlines.setWeekoffworked(emprecords.getWeekoffworked().longValue());
				paidlines.setBasicamount(emprecords.getBasicamount());
				paidlines.setServincentiveamount(emprecords.getServincentiveamount());
				paidlines.setEmployeeDepartment(emprecords.getEmployeeDepartment());
				paidlines.setSubDepartment(emprecords.getEmployee().getSubDepartment());
				paidlines.setDesignation(emprecords.getDesignation());
				paidlines.setPFApplicable(emprecords.isPFApplicable());
				paidlines.setBank(emprecords.isBank());
				paidlines.setImage(emp.getImage());
				OBDal.getInstance().save(paidlines);
				paidheader.setAtpayrollperiod(true);				
			}
		}
		final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Generated Payroll Paid List Successfully");
        bundle.setResult(msg);
	}
}