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
//import com.sun.xml.internal.ws.util.StringUtils;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.apache.commons.lang.time.DateUtils;
import org.openbravo.base.session.OBPropertiesProvider;
import org.apache.commons.lang.StringUtils;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class GetCOffDate extends DalBaseProcess{
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		RCPL_PayrollProcess payrollid = null;
	
		String headerid = (String) bundle.getParams().get("Rchr_Employeecoffs_ID");  
		RchrEmployeecoffs coffs = OBDal.getInstance().get(RchrEmployeecoffs.class, headerid);
		Date inDate = coffs.getOndate();
		Date toDate = coffs.getEndingDate();
//		System.out.println("inDate  and  toDate "+ inDate  + "   "+ toDate);
		RchrEmployee rchrEmployee = coffs.getEmployee();
//		System.out.println("inDate "+inDate+"   "+"toDate "+toDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(coffs.getEndingDate());
			int outdate = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(coffs.getOndate());
			int indate = cal.get(Calendar.DAY_OF_MONTH);
			int daysbetween = outdate - indate;
//			System.out.println("indate = "+indate+ " outdate"+outdate+"daysbetween "+daysbetween);
			int weekOffDay = getDay(rchrEmployee.getWeeklyOff());
//			System.out.println("weekOffDay ="+weekOffDay);
			 for(int i=0; i <= daysbetween ; i++)
			 {
	//			 System.out.println("With in For Loop");
				 int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
				 if(dayofweek!=weekOffDay){
					 RCHREmployeecoffslines cofflines = OBProvider.getInstance().get(RCHREmployeecoffslines.class);
					 //System.out.println("Record Saved");
					 cofflines.setOrganization(coffs.getOrganization());
					 cofflines.setCoffday(cal.getTime());
					 cofflines.setRchrEmployeecoffs(coffs);
					 OBDal.getInstance().save(cofflines);					 
		       	     	}
		       	       cal.add(Calendar.DATE, 1);
				 }
			 
			 
/*		try {
				SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
			    Date date1 = myFormat.parse(inDate.toString());
			    Date date2 = myFormat.parse(toDate.toString());
			    System.out.println("date1  and  date2 "+ date1  + "   "+ date2);	    
			    long diff = date2.getTime() - date1.getTime();
			    long daysbetween = diff/(24 * 60 * 60 * 1000);
			    System.out.println("daysbetween = "+daysbetween);
			    BigDecimal res=new BigDecimal(daysbetween);
	            System.out.println("res  = "+res);
			    int weekOffDay = getDay(rchrEmployee.getWeeklyOff());
			    System.out.println("weekOffDay  = "+weekOffDay);
			    Calendar calendarStart = Calendar.getInstance();
	       	    Calendar calendarEnd = Calendar.getInstance();
	       	    calendarStart.setTime(date1);
	       	    calendarEnd.setTime(date2);
//	       	    System.out.println(" calendarStart.setTime(date1) ="+ calendarStart.setTime(date1));
//	       	    System.out.println(" calendarEnd.setTime(date2) ="+calendarEnd.setTime(date2));
	       	    int count=0;
	       	    while(calendarEnd.compareTo(calendarStart)>=0){
	       	    System.out.println("IN While Loop");
	       	    	int dayofweek = calendarStart.get(Calendar.DAY_OF_WEEK);
	       	    System.out.println("dayof week = "+dayofweek);
	    		RCHREmployeecoffslines cofflines = OBProvider.getInstance().get(RCHREmployeecoffslines.class);	
	       	            	
	       	    if(dayofweek!=weekOffDay){
	       	    	System.out.println("record saved ");
	       	    	cofflines.setCoffday(calendarStart.getTime());
	       	    	cofflines.setRchrEmployeecoffs(coffs);
	       	    	OBDal.getInstance().save(cofflines);
	       	    	    	}
	       	            	calendarStart.add(Calendar.DATE, 1);
//	       	            	System.out.println("calendarStart.add(Calendar.DATE, 1) = "+calendarStart.add(Calendar.DATE, 1));
	       	            }      	        				   
			} catch (Exception e) {
			    e.printStackTrace();
			}*/
		coffs.setGetdate(true);
		final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Generated COffs Dates Successfully");
        bundle.setResult(msg);
	}
	
	
	private int getDay(String day) {
        int weekOff = -1;
        if (StringUtils.equalsIgnoreCase(day, "SUNDAY")) {
            weekOff = 1;
        } else if (StringUtils.equalsIgnoreCase(day, "MONDAY")) {
            weekOff = 2;
        } else if (StringUtils.equalsIgnoreCase(day, "TUESDAY")) {
            weekOff = 3;
        } else if (StringUtils.equalsIgnoreCase(day, "WEDNESDAY")) {
            weekOff = 4;
        } else if (StringUtils.equalsIgnoreCase(day, "THURSDAY")) {
            weekOff = 5;
        } else if (StringUtils.equalsIgnoreCase(day, "FRIDAY")) {
            weekOff = 6;
        } else if (StringUtils.equalsIgnoreCase(day, "SATURDAY")) {
            weekOff = 7;
        }
        return weekOff;
    }
	
}