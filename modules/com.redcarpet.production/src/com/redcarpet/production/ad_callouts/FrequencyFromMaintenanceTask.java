package com.redcarpet.production.ad_callouts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.math.BigDecimal;

import com.rcss.humanresource.data.RCHR_Bank;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.redcarpet.production.data.RCPR_MaintenanceTask;


/**
 *
 * @author Suneetha
 */
public class FrequencyFromMaintenanceTask extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        String maintenancetaskId = info.getStringParameter("inprcprMaintenancetaskId", null);
       // System.out.println("id from mtask is" +maintenancetaskId);
        RCPR_MaintenanceTask mtask = OBDal.getInstance().get(RCPR_MaintenanceTask.class, maintenancetaskId);
        BigDecimal frequency=mtask.getFrequency();
        info.addResult("inpfrequency", frequency);    
    }
    
}
