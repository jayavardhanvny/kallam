package com.rcss.humanresource.ad_process;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.sql.rowset.serial.SerialException;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import com.rcss.humanresource.data.RCHR_Emp_Loan;
import com.rcss.humanresource.data.RCHR_Emp_Loanlines;

public class RCHRLoanPaid extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		final String strId = bundle.getParams().get("Rchr_Emp_Loan_ID").toString();
        RCHR_Emp_Loan loan = OBDal.getInstance().get(RCHR_Emp_Loan.class, strId);
        BigDecimal lastCumulativeAmt = BigDecimal.ZERO;
        
        if(loan.getTenureMonths().equals(new BigDecimal("0.00"))){
        	throw new SerialException("Please check tenure months..");
        }else{
        	
        	loan.setApprove(true);
        	loan.setPaid(Boolean.TRUE);
        	loan.setAlertStatus("Paid");
        	loan.setPaiddate(new Date());
        	OBDal.getInstance().save(loan);
        	OBDal.getInstance().flush();
        }
	}
}