package com.rcss.humanresource.ad_callouts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import org.openbravo.model.ad.domain.Callout;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;
import org.openbravo.base.session.OBPropertiesProvider;
import org.apache.commons.lang.StringUtils;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrEmployee;

public class RchrCoffsDays extends SimpleCallout{
	@Override
	public void execute(CalloutInfo info) throws ServletException{
		String inDate = info.getStringParameter("inpondate", null);
		String toDate = info.getStringParameter("inpenddate", null);
		String coffType = info.getStringParameter("inpcoffodtype", null);
		String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
        RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, strEmployeeId);
		//BigDecimal leaveOpening = info.getBigDecimalParameter("inpleaveopening");
		
			try {
			
			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
			    Date date1 = myFormat.parse(inDate);
			    Date date2 = myFormat.parse(toDate);
			   
			    
			    long diff = date2.getTime() - date1.getTime();
			    long daysbetween=diff/(24 * 60 * 60 * 1000);
				//long i = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	                       BigDecimal res=new BigDecimal(daysbetween);
	                       //BigDecimal rt = new BigDecimal(rate);
			    //System.out.println ("res " + res);
	                         
	       	            		
	       	            int weekOffDay = getDay(rchrEmployee.getWeeklyOff());
	       	            Calendar calendarStart = Calendar.getInstance();
	       	            Calendar calendarEnd = Calendar.getInstance();
	       	            calendarStart.setTime(date1);
	       	            calendarEnd.setTime(date2);
	       	            int count=0;
	       	            while(calendarEnd.compareTo(calendarStart)>=0){
	       	            	int dayofweek = calendarStart.get(Calendar.DAY_OF_WEEK);
	       	            	
	       	            	if(dayofweek==weekOffDay){
	       		        		count++;
	       		        	}
	       	            	calendarStart.add(Calendar.DATE, 1);
	       	            }
	       	        	if(count>0){
	       	        		info.addResult("inpweekoffleaves", new BigDecimal(count));
	       	        	}else{
	       	        		
	       	        		info.addResult("inpweekoffleaves", new BigDecimal(0));
	       	        	}

				info.addResult("inpnumofcoffs",res.add(new BigDecimal(1)).subtract(new BigDecimal(count)));
				info.addResult("inpweeklyoff", rchrEmployee.getWeeklyOff());
				
			   
			} catch (Exception e) {
			    System.out.println("Date out is not entered but it is not a mandatory now");
			}
		
	
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