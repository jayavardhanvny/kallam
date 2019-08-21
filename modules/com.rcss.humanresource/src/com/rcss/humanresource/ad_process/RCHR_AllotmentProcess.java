package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RCHR_Allotment;
import com.rcss.humanresource.data.RCHR_Room;
import com.rcss.humanresource.data.RchrEmployee;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class RCHR_AllotmentProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String id = (String) bundle.getParams().get("Rchr_Allotment_ID");
        RCHR_Allotment allot = OBDal.getInstance().get(RCHR_Allotment.class, id);
        String empid = allot.getEmployee().getId();
        String roomid = allot.getRoom().getId();

        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, empid);
        emp.setRoom(allot.getRoom());
        emp.setRoomAllotted(true);
        
        RCHR_Room room = OBDal.getInstance().get(RCHR_Room.class, roomid);
        room.setVacant(false);
        final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Process completed successfully");
        bundle.setResult(msg);
        OBDal.getInstance().save(allot);
        OBDal.getInstance().save(emp); 
    }
}
