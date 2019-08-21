package com.rcss.humanresource.ad_events;

import java.math.BigDecimal;
import com.rcss.humanresource.data.*;
import javax.enterprise.event.Observes;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityDeleteEvent;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;

public class RCHR_Vehiclegatepassevent extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHRVehiclegatepass.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }                
        RCHRVehiclegatepass gatepass = (RCHRVehiclegatepass) event.getTargetInstance();   
        RCHRArea area = OBDal.getInstance().get(RCHRArea.class, gatepass.getRchrArea().getId());
        if(gatepass.getOutreading() != null && gatepass.getOutreading() > 0){
        if((gatepass.getTravelkms() > area.getMaxkms()) && (!gatepass.isRemark())){
        	throwsError();
        }else{
        	if(gatepass.getOutreading() > gatepass.getInreading()){
        	setLastreading(gatepass.getRchrVehiclemaster().getId(), gatepass.getOutreading());
        }else{
        	throwsNewError();
        }
        	} }
  }  
    
    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCHRVehiclegatepass gatepass = (RCHRVehiclegatepass) event.getTargetInstance();   
        RCHRArea area = OBDal.getInstance().get(RCHRArea.class, gatepass.getRchrArea().getId());
        if(gatepass.getOutreading() != null && gatepass.getOutreading() > 0 ){
            if((gatepass.getTravelkms() > area.getMaxkms()) && (!gatepass.isRemark())){
            	throwsError();
            }else{
            	if(gatepass.getOutreading() > gatepass.getInreading()){
            	setLastreading(gatepass.getRchrVehiclemaster().getId(), gatepass.getOutreading());
            }else{
            	throwsNewError();
            }
            	} } 
    }  
    
    public void onDelete(@Observes EntityDeleteEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCHRVehiclegatepass gatepass = (RCHRVehiclegatepass) event.getTargetInstance();
        if(gatepass.getTravelkms() > 0){
        OBCriteria<RCHRVehiclemaster> master = OBDal.getInstance().createCriteria(RCHRVehiclemaster.class);
        master.add(Restrictions.eq(RCHRVehiclemaster.PROPERTY_ID, gatepass.getRchrVehiclemaster().getId()));
        for(RCHRVehiclemaster masterlist : master.list()){
        	masterlist.setLastreading(masterlist.getLastreading()-gatepass.getTravelkms());       		 
        }
        }else{
        	System.out.println("");
        }
    }
    
     private void throwsError() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "Travel kms is more than maximum km.", language));
    }
     private void throwsNewError() throws OBException {
         String language = OBContext.getOBContext().getLanguage().getLanguage();
         ConnectionProvider conn = new DalConnectionProvider(false);
         throw new OBException(Utility.messageBD(conn, "Out Reading is Less Then In reading ", language));
     }
     
     public void setLastreading(String id, long lastreading){
    	 OBCriteria<RCHRVehiclemaster> master = OBDal.getInstance().createCriteria(RCHRVehiclemaster.class);
         master.add(Restrictions.eq(RCHRVehiclemaster.PROPERTY_ID, id));
         for(RCHRVehiclemaster masterlist : master.list()){
         	masterlist.setLastreading(lastreading);       		 
         }   	 
     }
	
}
