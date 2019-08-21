package com.rcss.humanresource.ad_process;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.sql.rowset.serial.SerialException;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import com.rcss.humanresource.data.RCHR_Emp_Loan;
import com.rcss.humanresource.data.RCHR_Emp_Loanlines;

public class RCHRLoanApprove extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		final String strId = bundle.getParams().get("Rchr_Emp_Loan_ID").toString();
        RCHR_Emp_Loan loan = OBDal.getInstance().get(RCHR_Emp_Loan.class, strId);
        BigDecimal lastCumulativeAmt = BigDecimal.ZERO;
        	//System.out.println("loan Tenure months " +loan.getRchrAttdProcess().getStartingDate());
        	//System.out.println("if condition");
        RCHR_Emp_Loanlines loanline = OBDal.getInstance().get(RCHR_Emp_Loanlines.class, strId);
        try{
        Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
       String sql="Delete from rchr_emp_loanlines where rchr_emp_loanlines.rchr_emp_loan_id= '" + strId + "'";
    		   stmt.executeUpdate(sql);
        } catch (Exception e) {
        	e.printStackTrace();
        	}
        Calendar cal = Calendar.getInstance();
        cal.setTime(loan.getRchrAttdProcess().getStartingDate());
        if(loan.getTenureMonths().equals(new BigDecimal("0.00"))){
        	throw new SerialException("Please check tenure months..");
        }else{
        	for (int i = 1; i <= loan.getTenureMonths().intValue(); i++) {
        		RCHR_Emp_Loanlines line = OBProvider.getInstance().get(RCHR_Emp_Loanlines.class);
        		System.out.println("for loop");
        		line.setOrganization(loan.getOrganization());
        		line.setLoanSchedule(loan);
        		line.setLineNo(Long.valueOf((i * 10) + ""));
        		line.setDueDate(cal.getTime());
        		line.setLoanAmount(loan.getLoanAmount().divide(loan.getTenureMonths(), MathContext.DECIMAL32));
        		line.setPaidAmount(BigDecimal.ZERO);
        		line.setPendingAmount(line.getLoanAmount().subtract(line.getPaidAmount()));
        		lastCumulativeAmt = line.getPendingAmount().add(lastCumulativeAmt);
        		line.setCumulativeAmount(lastCumulativeAmt);
        		line.setManual(false);
        		line.setPaid(false);
        		cal.add(Calendar.MONTH, +1);
        		OBDal.getInstance().save(line);
        	}
        	
        	loan.setApprove(Boolean.TRUE);
        	loan.setAlertStatus("Approved");
        	lastCumulativeAmt = BigDecimal.ZERO;
        	OBDal.getInstance().flush();
        }
	}
}