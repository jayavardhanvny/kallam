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



public class RchrDirectWarpDataSheetMandatory extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHR_Directwarp.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCHR_Directwarp warp = (RCHR_Directwarp) event.getTargetInstance();
       // System.out.println("on update ");
        
        if (warp.isStopped().equals(Boolean.FALSE)) {
        	//System.out.println("In Is stopped false update ");
        	if((warp.getDocumentNo()==null) || (warp.getRchrJobcard()==null)||(warp.getSetlength()==null) || (warp.getNoofcreels()==null)||(warp.getNoofbeems()==null)||
        			(warp.getSpeed()==null) || (warp.getConeweight()==null) || (warp.getConelength()==null) || (warp.getConstruction()==null)){
        		//System.out.println("In if condition update ");
        		throwError();
        	}
            
        }
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        //System.out.println("on Save ");
        RCHR_Directwarp warp = (RCHR_Directwarp) event.getTargetInstance();
        if (warp.isStopped().equals(Boolean.FALSE)) {
        	//System.out.println("In Is stopped false Save ");
        	if((warp.getDocumentNo()==null) || (warp.getRchrJobcard()==null)||(warp.getSetlength()==null) || (warp.getNoofcreels()==null)||(warp.getNoofbeems()==null)||
        			(warp.getSpeed()==null) || (warp.getConeweight()==null) || (warp.getConelength()==null) || (warp.getConstruction()==null)||(warp.getTotalends()==null)){
        		//System.out.println("in if condition Save ");
        		throwError();
        	}
            
        }
    }
    private void throwError() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "Set No, Sort No, Job Card, Set Length, No Of Creels, No Of Beams/Creel, Speed, Cone Weight, Cone Lenght, Construction are Mandatory Fields, Please fill them!", language));
    }
    
}
