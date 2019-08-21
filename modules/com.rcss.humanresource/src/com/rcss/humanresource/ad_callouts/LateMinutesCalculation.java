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
public class LateMinutesCalculation extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String shiftId = info.getStringParameter("inprcprShiftId", null);
    	System.out.println("ShiftId Is" +shiftId);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
        BigDecimal shifttime=shift.getShiftInMins(); 
        String duration = info.getStringParameter("inpduration", null);
        System.out.println("duration is" +duration);
        BigDecimal durationtime=new BigDecimal(duration);
        System.out.println("durationtime is" +durationtime);

        int shifttnew=shifttime.intValue();
        System.out.println("shifttnew is" +shifttnew);

        int durationtnew=durationtime.intValue();
        System.out.println("durationtnew is" +durationtnew);

        
        if(durationtnew<shifttnew)
        {
        	int result=shifttnew-durationtnew;
        	String s1="Y";
        	info.addResult("inpislate", s1);
        	info.addResult("inplatetime", result);
        } else
        {
        	int resultnew=0;
        	String s2="N";
        	info.addResult("inpislate", s2);
        	info.addResult("inplatetime", resultnew);
        }
        
      
    }
    
}
