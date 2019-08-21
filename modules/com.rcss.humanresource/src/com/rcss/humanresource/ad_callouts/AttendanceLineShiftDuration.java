package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.RchrAttendance;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Suneetha
 */
public class AttendanceLineShiftDuration extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String shiftId = info.getStringParameter("inprcprShiftId", null);
    	System.out.println("ShiftId Is" +shiftId);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
        Timestamp fromtime=shift.getFromTime();
        System.out.println("fromtime is" +fromtime);
        Timestamp totime=shift.getToTime();
        System.out.println("totime is" +totime);
        try {
        String strpunchin=shift.getFromTime().toString();
        System.out.println("strpunchin is" +strpunchin);
        String strpunchout=shift.getToTime().toString();
        System.out.println("strpunchout is" +strpunchout);
        
        String[] partone = strpunchin.split(" ");
        String[] parttwo = strpunchout.split(" ");
        String strone=partone[1];
        String strtwo=parttwo[1];
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        
        Date dateFrom = sdf.parse(strone);
        System.out.println("dateFrom is" +dateFrom);
        Date dateTo = sdf.parse(strtwo);
        System.out.println("dateTo is" +dateTo);
    
       /* String str[] = strone.split(":");
        String str1[] = strtwo.split(":");

        if (str.length == 0 || str1.length == 0) {
            throw new NullPointerException("str is null");
        }
        int hours = new Integer(str[0]).intValue();
        int increment = 0;
        if (hours < 6) {
            increment = -5;
        } else {
            increment = -18;
        }*/

       
        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(dateFrom);
        calFrom.add(Calendar.HOUR, -5);
        calFrom.add(Calendar.MINUTE, -30);
        Date timecal2=calFrom.getTime();
        String from = sdf.format(new Date(calFrom.getTimeInMillis()));
        System.out.println("from is" +from);
        //System.out.println("timecal2 is" +timecal2);
        
        Calendar calTo = Calendar.getInstance();
        calTo.setTime(dateTo); 
        calTo.add(Calendar.HOUR, -5);
        calTo.add(Calendar.MINUTE, -30);
        Date timecal1=calTo.getTime();
        String to = sdf.format(new Date(calTo.getTimeInMillis()));
        System.out.println("to is" +to);
        //System.out.println("timecal1 is" +timecal1);
        

        BigDecimal shifttime=shift.getShiftInMins(); 
        
        info.addResult("inppunchin", from);
        info.addResult("inppunchout", to);
        info.addResult("inpduration", shifttime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
    }
    
}
