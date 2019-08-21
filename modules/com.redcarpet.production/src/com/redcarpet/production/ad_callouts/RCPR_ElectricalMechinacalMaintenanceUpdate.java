package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPR_MaintenanceTask;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

@SuppressWarnings("unchecked")
public class RCPR_ElectricalMechinacalMaintenanceUpdate extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strTabId = info.getStringParameter("inpTabId", null);
        OBCriteria<RCPR_MaintenanceTask> crit = OBDal.getInstance().createCriteria(RCPR_MaintenanceTask.class);
        if(StringUtils.equals("CEEFBAE3F8534975BDA657947F3DA1A8", strTabId)){
            info.addResult("inpiselectricalmaintenance", "Y");
            List<RCPR_MaintenanceTask> maintainTaskList = crit.add(Restrictions.eq(RCPR_MaintenanceTask.PROPERTY_ISELECTRICALMAINTENANCE, true)).list();
            info.addSelect("inprcprMaintenancetaskId");
            for(RCPR_MaintenanceTask task : maintainTaskList){
                info.addSelectResult(task.getId(), task.getSearchKey());
            }
            info.endSelect();
        }else{
            info.addResult("inpiselectricalmaintenance", "N");
            List<RCPR_MaintenanceTask> maintainTaskList = crit.add(Restrictions.eq(RCPR_MaintenanceTask.PROPERTY_ISELECTRICALMAINTENANCE, false)).list();
            info.addSelect("inprcprMaintenancetaskId");
            for(RCPR_MaintenanceTask task : maintainTaskList){
                info.addSelectResult(task.getId(), task.getSearchKey());
            }
            info.endSelect();
        }
    }
}


    