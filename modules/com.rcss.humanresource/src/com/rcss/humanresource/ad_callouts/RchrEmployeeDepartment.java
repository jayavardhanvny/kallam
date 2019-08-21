package com.rcss.humanresource.ad_callouts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.data.*;

import javax.servlet.ServletException;
import com.redcarpet.production.data.RCPRShift;
import com.rcss.humanresource.util.ShiftUtil;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author Vinsy
 */
public class RchrEmployeeDepartment extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	// String strAttendanceId = info.getStringParameter("inprchrAttendanceId", null);
        String employeeId = info.getStringParameter("inprchrEmployeeId", null);
        String headerId = info.getStringParameter("inprchrAttendanceProodId", null);
        //RchrAttendance attend = OBDal.getInstance().get(RchrAttendance.class, strAttendanceId);
       // List<RchrAttendLine> lines = attend.getRchrAttendLineList();

        RchrAttendanceProod rchrAttendanceProod = OBDal.getInstance().get(RchrAttendanceProod.class,headerId);

        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, employeeId);
        ShiftUtil shiftUtil = new ShiftUtil();
        String shiftId = shiftUtil.getShiftRotation(rchrAttendanceProod.getFromDate(),emp);
        System.out.println("Proudction muster Employee Rotational shift is "+shiftId);

        RCPRShift rcprShift = OBDal.getInstance().get(RCPRShift.class,shiftId);

EmployeeUtil employeeUtil = new EmployeeUtil();

        int weekOff = employeeUtil.getWeeklyOff(emp,rchrAttendanceProod.getFromDate());

        if((!shiftId.equals(rchrAttendanceProod.getRcprShift().getId()) &&
                (rcprShift.getRchrShiftgroup().getId().equals("4A9C752FFA4840519EB6A78D22FC06A9"))) || (weekOff==rchrAttendanceProod.getFromDate().getDay())){
            info.addResult("inpisovertime", Boolean.TRUE);
        }else{
            info.addResult("inpisovertime", Boolean.FALSE);
        }
        //String empdocno=emp.getDocumentNo();
        info.addResult("inpempid", emp.getPunchno());
        info.addResult("inpdeptname", emp.getEmployeeDepartment().getCommercialName());

    }
    
}
