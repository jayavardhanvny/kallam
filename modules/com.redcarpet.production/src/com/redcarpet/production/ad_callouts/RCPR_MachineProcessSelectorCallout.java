package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPRMachine;
import com.redcarpet.production.data.RCPR_MachineProcess;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPR_MachineProcessSelectorCallout extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strMachineProcessId = info.getStringParameter("inprcprMachineprocessId", null);
        if (!StringUtils.equalsIgnoreCase(strMachineProcessId, "")) {
            RCPR_MachineProcess machineProcess = OBDal.getInstance().get(RCPR_MachineProcess.class, strMachineProcessId);
            info.addSelect("inprcprMachineId");
            for (RCPRMachine machine2 : machineProcess.getRCPRMachineList()) {
                info.addSelectResult(machine2.getId(), machine2.getName());
            }
            info.endSelect();
        }
    }
}


    