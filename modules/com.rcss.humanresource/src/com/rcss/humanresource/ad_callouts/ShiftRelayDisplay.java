package com.rcss.humanresource.ad_callouts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class ShiftRelayDisplay extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strpunchin = info.getStringParameter("inpfromdate", null);
        String strShift = info.getStringParameter("inprchrWorkShiftId", null);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = null;

        try {
            date1 = sdf.parse(strpunchin);
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sqry = "select rchr_relay.rchr_mrelay_id,name from rchr_relay left join rchr_mrelay on rchr_relay.rchr_mrelay_id=rchr_mrelay.rchr_mrelay_id where ('" + date1 + "' between to_date(Rchr_Relay.Fromdate) and to_date(Rchr_Relay.Todate)) and (Rchr_Relay.Rchr_Work_Shift_Id='" + strShift + "')";
            ResultSet rs = stmt.executeQuery(sqry);
            info.addSelect("inprchrMrelayId");
            while (rs.next()) {
                String id = rs.getString("rchr_mrelay_id");
                String mrname = rs.getString("name");
                info.addSelectResult(id, mrname, true);
            }
            info.endSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}