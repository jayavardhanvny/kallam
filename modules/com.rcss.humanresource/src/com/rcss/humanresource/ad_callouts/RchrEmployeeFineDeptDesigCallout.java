package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.data.RchrEmployee;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;

public class RchrEmployeeFineDeptDesigCallout extends SimpleCallout {
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        // String strAttendanceId = info.getStringParameter("inprchrAttendanceId", null);
        String employeeId = info.getStringParameter("inprchrEmployeeId", null);
        RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, employeeId);

        info.addResult("inprchrEmpDeptId", rchrEmployee.getEmployeeDepartment().getId());
	info.addResult("inprchrDesignationId", rchrEmployee.getDesignation().getId());

    }
}

