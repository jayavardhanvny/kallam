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
public class AttendanceLineNoWorkCallout extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strNowork = info.getStringParameter("inpnowork", null);
    	String strShiftChange = info.getStringParameter("inpisshiftchangeabsent", null);
    	System.out.println("strNowork Is" +strNowork);
    	if(strNowork.equals("Y"))
    	{
    		 String present="N";
    		 info.addResult("inppresent", present);
    	} else
    	{  
    		 String present="Y";
    		 info.addResult("inppresent", present);
    	}
    	
    	if(strShiftChange.equals("Y"))
    	{
    		 String present="N";
    		 info.addResult("inppresent", present);
    	}
        
        }
    
}
