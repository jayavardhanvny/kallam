package com.redcarpet.payroll.ad_callouts;

import com.rcss.humanresource.data.RCHRDailyattend;
import com.rcss.humanresource.data.RchrEmployee;

import com.rcss.humanresource.util.HqlUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetDailyAttenandanceIdCallout  extends SimpleCallout {
    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        // Grievances Window Line level RcplEmppprocessmanual Table
        try {
                doIt(info);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public void doIt(CalloutInfo info) {
            executeGrievanceLines(info);
    }
    private void executeGrievanceLines(CalloutInfo info){
        String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
        String strDate = info.getStringParameter("inpattddate", null);
        String strTabId = info.getStringParameter("inpTabId", null);
        if (strTabId.equals("8B4B40AA611544298F503AB589667C90")) {
            info.addResult("inpotprocess", Boolean.TRUE);
        } else if(strTabId.equals("B479B97A7086432FB653AA751A94D59F"))
            info.addResult("inpmanualpresents", Boolean.TRUE);
        if (!strEmployeeId.equals("") && !strDate.equals("")) {
            try {
                Date fromDate = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
                HqlUtils hqlUtils = new HqlUtils();
                RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class,strEmployeeId);
                List<RCHRDailyattend> rchrDailyattendList = hqlUtils.getDailyAttendanceObject(fromDate,employee.getDocumentNo());
                if(rchrDailyattendList.size()>0){
                    info.addResult("inprchrDailyattendId",rchrDailyattendList.get(0).getId());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}

