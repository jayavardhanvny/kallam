package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_Machine_ElectricalMaintenance;
import com.redcarpet.production.data.RCPR_Machine_Mechanical_Maintenance;
import com.redcarpet.production.data.RCPR_PreventiveMaintenanceOrder;
import com.redcarpet.production.data.RCPR_Task;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author S.A. Mateen
 */
public class RCPR_PreventiveOrderMaintenance extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {


        String recordId = bundle.getParams().get("Rcpr_Preventivemaintenance_ID").toString();
        RCPR_PreventiveMaintenanceOrder task = OBDal.getInstance().get(RCPR_PreventiveMaintenanceOrder.class, recordId);
        for (RCPR_Task line : task.getRCPRTaskList()) {
            String strMachineId = line.getMachine().getId();
            String strMaintenanceTask = line.getMaintenanaceTask().getId();
            boolean isExtension = line.isExtension();
            int noOfDays = line.getNoOfDays().intValue();
            String department = line.getDepartment();
            if (isExtension) {
                String strLastPmDoneDate = getLastPmDoneDate(strMachineId, strMaintenanceTask, department);
                String strNxtPMDoneDate = sumNoOfDaysNextPmDone(strLastPmDoneDate, noOfDays);
                if (StringUtils.equalsIgnoreCase("Electrical", line.getDepartment())) {
                    RCPRPreventiveOrderMaintenanceData.updateElectrical(new DalConnectionProvider(), strNxtPMDoneDate, strMachineId, strMaintenanceTask);
                } else {
                    RCPRPreventiveOrderMaintenanceData.updateMechanical(new DalConnectionProvider(), strNxtPMDoneDate, strMachineId, strMaintenanceTask);
                }
            } else {
                String fromDate = getFormattedDate(line.getPreventiveMaintenanceOrder().getCreationDate());
                String endDate = getSumLastDateWithFrequency(fromDate, strMachineId, strMaintenanceTask, department);
                if (StringUtils.equalsIgnoreCase("Electrical", line.getDepartment())) {
                    RCPRPreventiveOrderMaintenanceData.updateElectrical2(new DalConnectionProvider(), fromDate, endDate, strMachineId, strMaintenanceTask);
                } else {
                    RCPRPreventiveOrderMaintenanceData.updateMechanical2(new DalConnectionProvider(), fromDate, endDate, strMachineId, strMaintenanceTask);
                }
            }
            //line.setProcess(true);
        }
        task.setProcess(true);
        OBError err = new OBError();
        err.setType("Success");
        err.setTitle("Success");
        err.setMessage("Process Completed Successfully");
        bundle.setResult(err);

    }

    private String getLastPmDoneDate(String strMachineId, String strMaintenanceTask, String department) throws ServletException {
        if (StringUtils.equalsIgnoreCase("Electrical", department)) {
            return RCPRPreventiveOrderMaintenanceData.getElectricalLastDate(new DalConnectionProvider(), strMachineId, strMaintenanceTask);
        } else {
            return RCPRPreventiveOrderMaintenanceData.getMechanicalLastDate(new DalConnectionProvider(), strMachineId, strMaintenanceTask);
        }
    }

    private String getSumLastDateWithFrequency(String fromDate, String strMachineId, String strMaintenanceTask, String department) throws ServletException, ParseException {
        if (StringUtils.equalsIgnoreCase("Electrical", department)) {
            // fire querey to RCPR_Machine_EMaintenance
            String id = RCPRPreventiveOrderMaintenanceData.getElectricalMaintenanceId(new DalConnectionProvider(), strMachineId, strMaintenanceTask);
            RCPR_Machine_ElectricalMaintenance maintenance = OBDal.getInstance().get(RCPR_Machine_ElectricalMaintenance.class, id);
            return sumNoOfDaysNextPmDone(fromDate, maintenance.getFrequency().intValue());
        } else {
            // fire querey to RCPR_Machine_MMaintenance
            String id = RCPRPreventiveOrderMaintenanceData.getMechanicalMaintenanceId(new DalConnectionProvider(), strMachineId, strMaintenanceTask);
            RCPR_Machine_Mechanical_Maintenance maintenance = OBDal.getInstance().get(RCPR_Machine_Mechanical_Maintenance.class, id);
            return sumNoOfDaysNextPmDone(fromDate, maintenance.getFrequency().intValue());
        }
    }

    private String sumNoOfDaysNextPmDone(String nextPMDoneDate, int noOfDays) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(nextPMDoneDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, (noOfDays));
        return df.format(new Date(cal.getTimeInMillis()));
    }

    private String getFormattedDate(Date creationDate) {
        return new SimpleDateFormat("yyyy-MM-dd").format(creationDate).toString();
    }
}
