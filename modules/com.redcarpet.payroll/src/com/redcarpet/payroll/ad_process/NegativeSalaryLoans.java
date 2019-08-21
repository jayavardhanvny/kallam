package com.redcarpet.payroll.ad_process;

import com.rcss.humanresource.data.RCHR_Attend_Temp;
import com.rcss.humanresource.data.RCHR_Emp_Loan;
import com.rcss.humanresource.data.RCHR_Emp_Loanlines;
import com.rcss.humanresource.data.RCHR_Loan_Type;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import com.redcarpet.payroll.data.RCPL_EmpPayHead;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.data.RCPL_EmpDedIncentives;
import com.redcarpet.payroll.data.RCPL_EmpPayDead;
import com.redcarpet.payroll.data.RCPL_EmpPayIncentives;
import com.rcss.humanresource.data.RchrAttdProcess;

import javax.sql.rowset.serial.SerialException;
import com.rcss.humanresource.data.RchrAttdProcess;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.dal.core.OBContext;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.base.exception.OBException;

/**
 *
 * 
 */
public class NegativeSalaryLoans extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	String strProcessId = bundle.getParams().get("Rcpl_Payrollprocess_ID").toString();
    	OBError success = new OBError();
        success.setType("Success");
        success.setTitle("Success");
        success.setMessage("-ve salaries are posted as loans");
    	RCPL_PayrollProcess payroll = OBDal.getInstance().get(RCPL_PayrollProcess.class, strProcessId);
    	String loantypeId="4C12FEFF5F26498699D2A856EA85E3E1";
    	Date eDate= payroll.getEndingDate();
    	Calendar fromcal=Calendar.getInstance();
    	fromcal.setTime(eDate);
    	fromcal.add(Calendar.DAY_OF_YEAR, 1);
    	Date starDate=fromcal.getTime();
    	String strPayrollPrdId=null;
    	OBCriteria<RchrAttdProcess> attndPeriod = OBDal.getInstance().createCriteria(RchrAttdProcess.class);
    	attndPeriod.add(Restrictions.eq(RchrAttdProcess.PROPERTY_STARTINGDATE,starDate));
    	//attndPeriod.add(Restrictions.);
    	for (RchrAttdProcess payrollPeriod : attndPeriod.list()){
    		strPayrollPrdId=payrollPeriod.getId();
    	}
    		
    	RchrAttdProcess attndprocess= OBDal.getInstance().get(RchrAttdProcess.class, strPayrollPrdId);
    	String hql = " SELECT ess FROM RCPL_EmpPayrollProcess ess "
                + " WHERE ess.payrollProcess.id='" + strProcessId + "' "
                + " AND ess.totalPay < 0";
        
    	List<RCPL_EmpPayrollProcess> empPayrollList = OBDal.getInstance().getSession().createQuery(hql).list();
        String str=null;
        long count=0;
        //int i=0;
        Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select documentno from RCHR_Emp_Loan order by created desc limit 1");
        while (rs.next()) {
        	str=rs.getString("documentno");
        }
        count=Long.parseLong(str);
        
        for (RCPL_EmpPayrollProcess empPayroll : empPayrollList) {
        	
        	count++;
        	
        	RCHR_Emp_Loan loan = OBProvider.getInstance().get(RCHR_Emp_Loan.class);
        	loan.setOrganization(empPayroll.getOrganization());
        	loan.setLoanCategory(OBDal.getInstance().get(RCHR_Loan_Type.class, loantypeId));
        	loan.setDocumentNo(Long.toString(count));
        	loan.setEmployee(empPayroll.getEmployee());
        	loan.setRequisitiondate(new Date());
        	loan.setRchrAttdProcess(attndprocess);
        	loan.setLoanAmount(empPayroll.getTotalPay().multiply(new BigDecimal(-1)));
        	loan.setTenureMonths(new BigDecimal(1.00));
        	OBDal.getInstance().save(loan);
        	
        	
        	RCHR_Emp_Loanlines line = OBProvider.getInstance().get(RCHR_Emp_Loanlines.class);
        	line.setOrganization(empPayroll.getOrganization());
        	line.setLoanSchedule(loan);
        	line.setDueDate(attndprocess.getStartingDate());
        	line.setLoanAmount(empPayroll.getTotalPay().multiply(new BigDecimal(-1)));
        	line.setPendingAmount(empPayroll.getTotalPay().multiply(new BigDecimal(-1)));
        	line.setCumulativeAmount(empPayroll.getTotalPay().multiply(new BigDecimal(-1)));
        	line.setManual(false);
        	line.setPaid(false);
        	OBDal.getInstance().save(line);
        	//i++;
            
    }
        OBDal.getInstance().flush();
        bundle.setResult(success);
    }
}