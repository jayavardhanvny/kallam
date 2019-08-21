 package com.rcss.humanresource.ad_events;

//import com.rcss.humanresource.data.RCHR_Token;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RCHRPermission;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;

import javax.enterprise.event.Observes;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;

public class PermissionValidation extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHRPermission.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
    	if (!isValidEvent(event)) {
            return;
        }
       /* RCHRPermission permission = (RCHRPermission) event.getTargetInstance();
        double count=getPermissionCount(permission.getEmployee().getId(), permission.getPermisindate());
        double range=permission.getRcprShift().getPermissiondays().doubleValue();
        if(count>=range && Boolean.FALSE.equals(permission.isProceed())){
            throwError();
        }*/
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCHRPermission permission = (RCHRPermission) event.getTargetInstance();
        double count=getPermissionCount(permission.getEmployee().getId(), permission.getPermisindate());
        double range=permission.getRcprShift().getPermissiondays().doubleValue();
        if(count>=range && Boolean.FALSE.equals(permission.isProceed())){
            throwError();
        }
        
    }

    private void throwError() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "RCHR_Permission", language));
    }
    
      private double getPermissionCount(String employeeId,Date permsnDate){
    	  Date fromdate=new Date(permsnDate.getTime());
    		Calendar fromcal=Calendar.getInstance();
    		Calendar tocal=Calendar.getInstance();
    		fromcal.set(Calendar.MONTH,fromdate.getMonth());
    		//fromcal.set(Calendar.DAY_OF_MONTH, 1);
    		fromcal.set(Calendar.DAY_OF_MONTH, fromcal.getActualMinimum(Calendar.DATE));
    		tocal.set(Calendar.MONTH,fromdate.getMonth());
    		tocal.set(Calendar.DATE, tocal.getActualMaximum(Calendar.DATE));
    		Date fromDate=fromcal.getTime();
    		Date toDate=tocal.getTime();
    	
    	String hql="SELECT COUNT(line.id) FROM RCHR_Permission AS line"
    		    + " where line.employee='"+employeeId+"'"
    		   + " AND (line.permisindate BETWEEN '"+fromDate+"' AND '"+toDate+"')"
                + " AND line.alertStatus='AP'";
    	
    	Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        //System.out.println("noofdays.."+Double.valueOf(li.get(0).toString()));
        return Double.valueOf(li.get(0).toString());
   
    }
    
}
