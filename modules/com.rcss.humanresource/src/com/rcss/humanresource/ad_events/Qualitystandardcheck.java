package com.rcss.humanresource.ad_events;

import java.math.BigDecimal;

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.rcss.humanresource.data.RCHR_Inspcategoryline;
import com.rcss.humanresource.data.RCHR_Inspectionsheet;
import com.rcss.humanresource.data.RCHR_LeaveRequisition;
import com.rcss.humanresource.data.RchrInspgreigewidth;
import javax.enterprise.event.Observes;

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

public class Qualitystandardcheck extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHR_Inspectionsheet.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }                
        RCHR_Inspectionsheet sheet = (RCHR_Inspectionsheet) event.getTargetInstance();    
        RCHRQualitystandard qtyStrd = OBDal.getInstance().get(RCHRQualitystandard.class, sheet.getRchrQualitystandard().getId());
  		
        RchrInspgreigewidth  grgwidth = OBDal.getInstance().get(RchrInspgreigewidth.class, qtyStrd.getRchrInspgreigewidth().getId());
        
  		  BigDecimal i =  qtyStrd.getEpi();
  		  BigDecimal j = qtyStrd.getPpi();
  		  //BigDecimal width= sheet.getWidthininches();
  		  long width= sheet.getWidthininches().longValue();
  		  double grWidth= Double.parseDouble(grgwidth.getName());
  		 if(!((width >= grWidth-2) && (width <= grWidth+2)) && (!sheet.isRemark())){
 			 throwsErrorWidth();
 		 }
  		 if(!((sheet.getEpi().longValue() >= i.longValue()-2) && (sheet.getEpi().longValue() <= i.longValue()+2)) && (!sheet.isRemark())){
  			 throwsError();
  		 }
  		 if(!((sheet.getPpi().longValue() >= j.longValue()-2) && (sheet.getPpi().longValue() <= j.longValue()+2)) && (!sheet.isRemark())){
  			throwsErrorNew();
  		 }

    }  	  
    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCHR_Inspectionsheet sheet = (RCHR_Inspectionsheet) event.getTargetInstance();    
        RCHRQualitystandard qtyStrd = OBDal.getInstance().get(RCHRQualitystandard.class, sheet.getRchrQualitystandard().getId());
        RchrInspgreigewidth  grgwidth = OBDal.getInstance().get(RchrInspgreigewidth.class, qtyStrd.getRchrInspgreigewidth().getId()); 
		  BigDecimal i =  qtyStrd.getEpi();
		  BigDecimal j = qtyStrd.getPpi();
		  long width= sheet.getWidthininches().longValue();
  		  double grWidth= Double.parseDouble(grgwidth.getName());
  		if(!((width >= grWidth-2) && (width <= grWidth+2)) && (!sheet.isRemark())){
			 throwsErrorWidth();
		 }	
		  if(!((sheet.getEpi().longValue() >= i.longValue()-2) && (sheet.getEpi().longValue() <= i.longValue()+2)) && (!sheet.isRemark())){
	  			 throwsError();
	  		 }
	  		 if(!((sheet.getPpi().longValue() >= j.longValue()-2) && (sheet.getPpi().longValue() <= j.longValue()+2)) && (!sheet.isRemark())){
	  			throwsErrorNew();
	  		 }
    }
    
    public void onDelete(@Observes EntityDeleteEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCHR_Inspectionsheet sheet = (RCHR_Inspectionsheet) event.getTargetInstance();    
        RCHRQualitystandard qtyStrd = OBDal.getInstance().get(RCHRQualitystandard.class, sheet.getRchrQualitystandard().getId());
        qtyStrd.setRemainingquantity(qtyStrd.getRemainingquantity().add(sheet.getOrderquantity()));
    }
     private void throwsError() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "Not Match with EPI No.", language));
    }
     private void throwsErrorNew() throws OBException {
         String language = OBContext.getOBContext().getLanguage().getLanguage();
         ConnectionProvider conn = new DalConnectionProvider(false);
         throw new OBException(Utility.messageBD(conn, "Not Match with PPI No.", language));
     }
     private void throwsErrorWidth() throws OBException {
         String language = OBContext.getOBContext().getLanguage().getLanguage();
         ConnectionProvider conn = new DalConnectionProvider(false);
         throw new OBException(Utility.messageBD(conn, "Not Match with Width Inches", language));
     }
}
