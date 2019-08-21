package com.rcss.humanresource.ad_events;

import javax.enterprise.event.Observes;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.core.OBContext;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;

import com.rcss.humanresource.data.RCHR_Directwarp;
import com.rcss.humanresource.data.RCHR_Sizingsheet;



public class RchrSzingDataSheetMandatory extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHR_Sizingsheet.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        String message = "";
        
        RCHR_Sizingsheet warp = (RCHR_Sizingsheet) event.getTargetInstance();
       // System.out.println("on update ");
        
        if (warp.isStopped().equals(Boolean.FALSE)) {
        	//System.out.println("In Is stopped false update ");
        	if((warp.getDocumentNo()==null) || (warp.getRchrJobcard()==null)||(warp.getSetlength()==null) ||
        			(warp.getRchrDirectwarp()==null)||(warp.getRchrQualitystandard()==null)||
        			(warp.getWarperbeams()==null) || (warp.getYarnsuppliier()==null) || (warp.getGlm()==null) || 
        			(warp.getTotalends()==null) || (warp.getConstruction()==null)){
        		//System.out.println("In if condition update ");
        		message="Set No, Sort No, Job Card, Set Length, Dire Warping Set No., Sort No. , " +
        				"Construction, Total Ends, GLM, Weaver Beams Yarn Suppliers are Mandatory Fields, Please fill them!";
        		throwError(message);
        	}
            
        }else if(warp.isStopped().equals(Boolean.TRUE)){
        	//System.out.println("warp.getRchrDetention() "+warp.getRchrDetention());
        	//System.out.println("warp.getFromtime() "+warp.getFromtime().toString());
        	//System.out.println("warp.getTotime() "+warp.getTotime().toString()));
        	
        	if(warp.getRchrDetention()==null){
        		message="Detentions, From Time and To Time are Mandatories";
        		System.out.println("Null ");
        		throwError(message);
        	}
        }
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        String message = "";
        //System.out.println("on Save ");
        RCHR_Sizingsheet warp = (RCHR_Sizingsheet) event.getTargetInstance();
        if (warp.isStopped().equals(Boolean.FALSE)) {
        	//System.out.println("In Is stopped false Save ");
        	if((warp.getDocumentNo()==null) || (warp.getRchrJobcard()==null)||(warp.getSetlength()==null) || (warp.getRchrDirectwarp()==null)||(warp.getRchrQualitystandard()==null)||
        			(warp.getWarperbeams()==null) || (warp.getGlm()==null) || (warp.getTotalends()==null) || (warp.getConstruction()==null)){
        		//System.out.println("in if condition Save ");
        		message="Set No, Sort No, Job Card, Set Length, Dire Warping Set No., Sort No. , " +
        				"Construction, Total Ends, GLM, Weaver Beams Yarn Suppliers are Mandatory Fields, Please fill them!";
        		throwError(message);
        	}
            
        }else if(warp.isStopped().equals(Boolean.TRUE)){
        	if(warp.getRchrDetention()==null){
        		message="Detentions, From Time and To Time are Mandatories";
        		throwError(message);
        	}
        }
    }
    private void throwError(String message) throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, message, language));
    }
    
}
