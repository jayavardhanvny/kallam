package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;

/**
 *
 * @author Suneetha
 */
public class AttendanceShiftDuration extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        String shiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
        BigDecimal shifttime=shift.getShiftInMins(); 
        info.addResult("inpduration", shifttime);
        
    }
    
}
