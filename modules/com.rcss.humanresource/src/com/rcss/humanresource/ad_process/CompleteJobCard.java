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
import com.rcss.humanresource.data.RCHR_Jobcard;

public class CompleteJobCard extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		final String strId = bundle.getParams().get("Rchr_Jobcard_ID").toString();
		
        RCHR_Jobcard jobCard = OBDal.getInstance().get(RCHR_Jobcard.class, strId);
        //BigDecimal lastCumulativeAmt = BigDecimal.ZERO;
        
        
            jobCard.setProcess(Boolean.TRUE);
            jobCard.setDocumentStatus("CO");
        	
        	OBDal.getInstance().save(jobCard);
        	OBDal.getInstance().flush();
        
	}
}