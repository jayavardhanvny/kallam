package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_Task;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author S.A. Mateen
 */
public class RCPR_MaintenanceTaskProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
//        String recordId = bundle.getParams().get("Rcpr_Task_ID").toString();
//        RCPR_Task task = OBDal.getInstance().get(RCPR_Task.class, recordId);
//        String mTaskId = task.getMaintenanceTask().getId();
//        task.getMaintenanceTask().setLastPMDoneDate(task.getPreventiveMaintenanceOrder().getMaintenanceOrderDate());
//
//        try {
//            Date dateLast = task.getMaintenanceTask().getLastPMDoneDate();
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(dateLast.getTime());
//            cal.add(Calendar.DATE, task.getMaintenanceTask().getFrequency().intValue());
//            Date nextDate = new Date(cal.getTimeInMillis());
//            task.getMaintenanceTask().setNextPMDoneDate(nextDate);
//            task.getMaintenanceTask().setPlannedTimeHours(new BigDecimal(task.getMaintenanceTask().getFrequency().intValue() * 24));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        task.setProcess(true);
        OBError err = new OBError();
        err.setType("Success");
        err.setTitle("Success");
        err.setMessage("Process Completed Successfully");
        bundle.setResult(err);
        
    }
}
