package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.*;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class RCPR_MachineMaintenanceLines_Process extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String machineId = bundle.getParams().get("Rcpr_Machine_ID").toString();
        processIt(machineId);
        OBError error = new OBError();
        error.setType("Success");
        error.setTitle("Success");
        error.setMessage("Process Completed Successfully");
        bundle.setResult(error);
    }

    private void processIt(String machineId) {
        RCPRMachine machine = OBDal.getInstance().get(RCPRMachine.class, machineId);
        RCPR_MachineCategory mCat = machine.getMachineCategory();
        for(RCPR_MachineCategory_Electrical_Maintenance ele : mCat.getRCPRMachineCatEleMaintList()){
            RCPR_Machine_ElectricalMaintenance mEle = OBProvider.getInstance().get(RCPR_Machine_ElectricalMaintenance.class);
            mEle.setMachine(machine);
            mEle.setLineNo(ele.getLineNo());
            mEle.setMaintenanaceTask(ele.getMaintenanaceTask());
            mEle.setPlannedTimeHours(ele.getPlannedTimeHours());
            mEle.setFrequency(ele.getFrequency());
            mEle.setLastPMDoneDate(ele.getLastPMDoneDate());
            mEle.setNextPMDoneDate(ele.getNextPMDoneDate());
            mEle.setDescription(ele.getDescription());
            OBDal.getInstance().save(mEle);
            
        }
        for(RCPR_MachineCategory_Mechanical_Maintenance ele : mCat.getRCPRMachineCatMechMaintList()){
            RCPR_Machine_Mechanical_Maintenance mEle = OBProvider.getInstance().get(RCPR_Machine_Mechanical_Maintenance.class);
            mEle.setMachine(machine);
            mEle.setLineNo(ele.getLineNo());
            mEle.setMaintenanaceTask(ele.getMaintenanaceTask());
            mEle.setPlannedTimeHours(ele.getPlannedTimeHours());
            mEle.setFrequency(ele.getFrequency());
            mEle.setLastPMDoneDate(ele.getLastPMDoneDate());
            mEle.setNextPMDoneDate(ele.getNextPMDoneDate());
            mEle.setDescription(ele.getDescription());
            OBDal.getInstance().save(mEle);
        }
    }
    
}
