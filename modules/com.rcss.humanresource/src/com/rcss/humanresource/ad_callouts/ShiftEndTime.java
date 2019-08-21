package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;

import org.hibernate.Session;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.redcarpet.production.data.RCPRShift;
import com.rcss.humanresource.data.RchrEmployee; 
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.text.ParseException;
/**
 *
 * @author tirumal
 */
public class ShiftEndTime extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        String employeeId = info.getStringParameter("inprchrEmployeeId", null);
        String shiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, employeeId);
        String toDate=info.getStringParameter("inppermisindate", null).toString();
        System.out.println("Date is.."+toDate);
        try{
        SimpleDateFormat sdftOt = new SimpleDateFormat("dd-MM-yyyy");
        Date permsnDate=sdftOt.parse(toDate);
        BigDecimal count=getPermissionCount(employeeId, permsnDate);
        count=count.add(new BigDecimal(1));
        System.out.println("count is"+count);
        info.addResult("inpcount", count);
        }catch(ParseException e){
        	//
        }
    }
    
    private BigDecimal getPermissionCount(String employeeId,Date permsnDate){
  	  Date fromdate=new Date(permsnDate.getTime());
  		Calendar fromcal=Calendar.getInstance();
  		Calendar tocal=Calendar.getInstance();
  		fromcal.set(Calendar.MONTH,permsnDate.getMonth());
  		//fromcal.set(Calendar.DAY_OF_MONTH, 1);
  		fromcal.set(Calendar.DAY_OF_MONTH, fromcal.getActualMinimum(Calendar.DATE));
  		tocal.set(Calendar.MONTH,permsnDate.getMonth());
  		tocal.set(Calendar.DATE, tocal.getActualMaximum(Calendar.DATE));
  		Date fromDate=fromcal.getTime();
  		Date toDate=tocal.getTime();
  		System.out.println("from date is--"+ fromDate);
  		System.out.println("to date is--"+ toDate);
  	String hql="SELECT COUNT(line.id) FROM RCHR_Permission AS line"
  		    + " where line.employee='"+employeeId+"'"
			+ " AND line.alertStatus='AP'"
  		    + " AND (line.permisindate BETWEEN '"+fromDate+"' AND '"+toDate+"')";
  	
  	Session session = OBDal.getInstance().getSession();
      List li = session.createQuery(hql).list();
      if (li.size() <= 0 || li.isEmpty()) {
          return new BigDecimal(0);
      }
      if (li.get(0) == null || li.get(0).toString() == null) {
    	  return new BigDecimal(0);
      }
      //System.out.println("noofdays.."+Double.valueOf(li.get(0).toString()));
      //return Double.valueOf(li.get(0).toString());
      return new BigDecimal(li.get(0).toString());
  }
    
}
