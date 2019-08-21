package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RCHR_DeAllotment;
import com.rcss.humanresource.data.RCHR_Room;
import com.rcss.humanresource.data.RchrEmployee;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class RCHR_DeAllotmentProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String id = (String) bundle.getParams().get("Rchr_Deallotment_ID");
        RCHR_DeAllotment allot = OBDal.getInstance().get(RCHR_DeAllotment.class, id);
        String empid = allot.getEmployee().getId();
        String roomid = allot.getRoom().getId();
        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, empid);
        emp.setRoom(null);
        emp.setRoomAllotted(false);
        RCHR_Room room = OBDal.getInstance().get(RCHR_Room.class, roomid);
        room.setVacant(true);
        final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Process completed successfully");
        bundle.setResult(msg);
        OBDal.getInstance().save(allot);
        OBDal.getInstance().save(emp); 
    }
}
