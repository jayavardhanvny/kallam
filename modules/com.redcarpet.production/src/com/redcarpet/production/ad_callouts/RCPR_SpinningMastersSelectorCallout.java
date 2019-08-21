package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPRCount;
import com.redcarpet.production.data.RCPRMachine;
import com.redcarpet.production.data.RCPRShift;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPR_SpinningMastersSelectorCallout extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strShiftId = info.getStringParameter("inprcprShiftId", null);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
        info.addResult("inpshifttimeinmins", shift.getShiftInMins());

        String strMachineId = info.getStringParameter("inprcprMachineId", null);
        RCPRMachine machine = OBDal.getInstance().get(RCPRMachine.class, strMachineId);
        info.addResult("inpnofspindle", machine.getSpindles());

        String strCountId = info.getStringParameter("inprcprCountId", null);
        RCPRCount count = OBDal.getInstance().get(RCPRCount.class, strCountId);
        info.addResult("inphank", count.getHanks());
        info.addResult("inpproductionconstant", count.getProductionConstanct());
    }
}


    