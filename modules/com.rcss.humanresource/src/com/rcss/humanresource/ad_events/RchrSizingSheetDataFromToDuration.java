package com.rcss.humanresource.ad_events;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;


import com.rcss.humanresource.data.RCHR_Sizing_Beam;

public class RchrSizingSheetDataFromToDuration extends EntityPersistenceEventObserver {
    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHR_Sizing_Beam.ENTITY_NAME)};  
    protected Logger logger = Logger.getLogger(RCHR_Sizing_Beam.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	
	public void onSave(@Observes EntityNewEvent event){
		if(!isValidEvent(event))
		{
			return;
		}
      final RCHR_Sizing_Beam beam = (RCHR_Sizing_Beam)event.getTargetInstance();
      
      
      OBCriteria<RCHR_Sizing_Beam> beamlist = OBDal.getInstance().createCriteria(RCHR_Sizing_Beam.class);
      beamlist.add(Restrictions.eq(RCHR_Sizing_Beam.PROPERTY_RCHRSIZINGSHEET, beam.getRchrSizingsheet()));	
      
      if(beamlist.list().size()==0){
    	  beam.getRchrSizingsheet().setFromtime(beam.getFromtime());
    	  beam.getRchrSizingsheet().setTotime(beam.getTotime());
    	  beam.getRchrSizingsheet().setShiftinmins(beam.getShiftinmins());
      }else{
    	  beam.getRchrSizingsheet().setTotime(beam.getTotime());
    	  beam.getRchrSizingsheet().setShiftinmins(beam.getRchrSizingsheet().getShiftinmins()+(beam.getShiftinmins()));
      }
      
      
	}
	public void onUpdate(@Observes EntityUpdateEvent event) {
		if(!isValidEvent(event))
		{
			return;
		}
		final RCHR_Sizing_Beam beam = (RCHR_Sizing_Beam)event.getTargetInstance();
	      
	      
	      OBCriteria<RCHR_Sizing_Beam> beamlist = OBDal.getInstance().createCriteria(RCHR_Sizing_Beam.class);
	      beamlist.add(Restrictions.eq(RCHR_Sizing_Beam.PROPERTY_RCHRSIZINGSHEET, beam.getRchrSizingsheet()));	
	      
	      if(beamlist.list().size()==1){
	    	  beam.getRchrSizingsheet().setFromtime(beam.getFromtime());
	    	  beam.getRchrSizingsheet().setTotime(beam.getTotime());
	    	  beam.getRchrSizingsheet().setShiftinmins(beam.getShiftinmins());
	      }else{
	    	  beam.getRchrSizingsheet().setTotime(beam.getTotime());
	    	  beam.getRchrSizingsheet().setShiftinmins(beam.getRchrSizingsheet().getShiftinmins()+(beam.getShiftinmins()));
	      }
	}
}
