package com.rcss.humanresource.ad_actionButtons;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.system.System;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;


import com.rcss.humanresource.data.RCHR_Sizingsheet;
import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class Payrollpaidapproval extends DalBaseProcess{
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		String id = (String) bundle.getParams().get("Rcpl_Payrollpaidprocline_ID");	
		RCPLPayrollpaidprocline paidline = OBDal.getInstance().get(RCPLPayrollpaidprocline.class, id);
		Calendar now = Calendar.getInstance();
		paidline.setPaid(true);	
		paidline.setPaidapproval(true);
		paidline.setPaiddate(now.getTime());
		final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Success");
        bundle.setResult(msg);
        
	}
}