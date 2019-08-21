package com.rcss.humanresource.ad_actionButtons;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.system.System;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;
//import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;
import com.rcss.humanresource.data.RCHR_OverTime;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class OverTimePaidApproval extends DalBaseProcess{
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		String id = (String) bundle.getParams().get("Rchr_Overtime_ID");	
		RCHR_OverTime overTime = OBDal.getInstance().get(RCHR_OverTime.class, id);
		Calendar now = Calendar.getInstance();
		overTime.setPaid(Boolean.TRUE);	
		overTime.setProcess(Boolean.TRUE);
		overTime.setPaiddate(now.getTime());
		final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Success");
        bundle.setResult(msg);
	}
}