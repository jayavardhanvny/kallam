package com.rcss.humanresource.ad_actionButtons;

import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
//import org.openbravo.model.ad.system.System;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.rcss.humanresource.data.RCHRMobalizer;
import com.rcss.humanresource.data.RCHRMobalizerinclines;
import com.rcss.humanresource.data.RCHRMobalizerline;
import com.rcss.humanresource.data.RCHRMobalizerserviceinc;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrEmployeeleaveBal;
import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class RCHR_MobalizerserviceincGetInc extends DalBaseProcess{
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		String id = (String) bundle.getParams().get("Rchr_Mobalizerserviceinc_ID");	
		RCHRMobalizerserviceinc serinc = OBDal.getInstance().get(RCHRMobalizerserviceinc.class, id);
		RCHRMobalizer mobalizer = serinc.getRchrMobalizer();
		Date fromdate = serinc.getRchrAttdProcess().getStartingDate();
		Date todate = serinc.getRchrAttdProcess().getEndingDate();
		
		OBCriteria<RchrEmployee> employee = OBDal.getInstance().createCriteria(RchrEmployee.class);
		employee.add(Restrictions.eq(RchrEmployee.PROPERTY_RCHRMOBALIZER, mobalizer));
		for(RchrEmployee employeelist : employee.list()){
			
	//		 double presentdays = new PayrollDBSessionUtil(employeelist.getId(), fromdate, todate).getNoOfPresentsDailyDemo();
			double presentdays = new AttendanceUtil(fromdate, todate, employeelist.getId()).getNoOfDaysPresent();
			// double presentdays = new PayrollDBSessionUtil(employeelist.getId(), fromdate, todate).getNoOfDaysPresent();
			 RCHRMobalizerinclines inclines = OBProvider.getInstance().get(RCHRMobalizerinclines.class);
				inclines.setOrganization(serinc.getOrganization());
				inclines.setRchrMobalizerserviceinc(serinc);				
				inclines.setEmployee(employeelist);
				inclines.setJoindate(employeelist.getDate());
				inclines.setDesignation(employeelist.getDesignation());
				inclines.setPresentdays(new BigDecimal(presentdays));
				inclines.setIncperday(new BigDecimal(getIncperDay(presentdays, employeelist.getRchrMobalizer())));				
				inclines.setTotal(new BigDecimal((getIncperDay(presentdays, employeelist.getRchrMobalizer())) * presentdays));				
				OBDal.getInstance().save(inclines);	
			 
		}
		final OBError msg = new OBError();
serinc.setGetincentive(Boolean.TRUE);
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Success");
        bundle.setResult(msg);
	}
	
	public long getIncperDay(double  pdays, RCHRMobalizer mob){
	   long incperday = 0;
		   OBCriteria<RCHRMobalizerline> moblines = OBDal.getInstance().createCriteria(RCHRMobalizerline.class);
		   moblines.add(Restrictions.eq(RCHRMobalizerline.PROPERTY_RCHRMOBALIZER, mob));
		   moblines.add(Restrictions.ge(RCHRMobalizerline.PROPERTY_TODAYS, (long) pdays));
		   moblines.add(Restrictions.le(RCHRMobalizerline.PROPERTY_FROMDAYS, (long) pdays));
				for(RCHRMobalizerline linelist: moblines.list()){
					incperday = linelist.getAmount();
				}		
				return incperday;
    }
	
}
