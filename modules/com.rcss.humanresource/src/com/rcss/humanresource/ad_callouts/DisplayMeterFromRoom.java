package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.data.RCHR_Room;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author S.A. Mateen
 */
public class DisplayMeterFromRoom extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        String roomId = info.getStringParameter("inprchrRoomId", null);
        RCHR_Room room = OBDal.getInstance().get(RCHR_Room.class, roomId);
        String mno=room.getMeterNumber().getId();
        System.out.println("Meter NO From Room is" + mno);
       
        info.addResult("inprcmrMeterId", mno);
        
    }
    
}
