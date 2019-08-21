package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.data.RCHR_Room;
import com.rcss.humanresource.data.RchrEmployee;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author S.A. Mateen
 */
public class RCHR_Employee_Allotment_Rooms extends SimpleCallout {

    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {
        String employeeId = info.getStringParameter("inprchrEmployeeId", null);
        System.out.println("employeeid " + employeeId);
        RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, employeeId);
        RCHR_Room room = employee.getRoom();
        if (room != null) {
            info.addSelect("inprchrRoomId");
            info.addSelectResult(room.getId(), room.getRoomNo(), true);
            info.endSelect();
        }
    }
}
