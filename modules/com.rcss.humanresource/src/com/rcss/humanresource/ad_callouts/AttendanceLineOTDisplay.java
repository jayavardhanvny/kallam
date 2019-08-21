package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.data.RchrAttendance;
import com.rcss.humanresource.data.RchrAttendLine;
import java.util.List;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author Suneetha
 */
public class AttendanceLineOTDisplay extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        System.out.println("OT Display");
        String strAttendanceId = info.getStringParameter("inprchrAttendanceId", null);
        String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
        RchrAttendance attend = OBDal.getInstance().get(RchrAttendance.class, strAttendanceId);
        List<RchrAttendLine> lines = attend.getRchrAttendLineList();
        
        for(RchrAttendLine line : lines){
           String empl= line.getRchrEmployee().getId();
           if(strEmployeeId.equals(empl))
           {
        	   String strot="Y";
        	   info.addResult("inpisovertime", strot);
           }
        }
        
    }
}
