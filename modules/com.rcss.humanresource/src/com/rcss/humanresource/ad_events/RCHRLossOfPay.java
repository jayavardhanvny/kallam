package com.rcss.humanresource.ad_events;

//import com.rcss.humanresource.data.RCHR_Token;
import java.math.BigDecimal;

import com.rcss.humanresource.data.RCHR_LeaveRequisition;
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

public class RCHRLossOfPay extends EntityPersistenceEventObserver {
	
    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHR_LeaveRequisition.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
	/*
    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCHR_LeaveRequisition leaveReq = (RCHR_LeaveRequisition) event.getTargetInstance();
        
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCHR_LeaveRequisition leaveReq = (RCHR_LeaveRequisition) event.getTargetInstance();
        if(leaveReq.getLeaveopening() != leaveReq.getNoOfLeaves()){
        if (leaveReq.getLeaveopening().longValue() < leaveReq.getNoOfLeaves().longValue() && Boolean.FALSE.equals(leaveReq.getLeaveType().isLop())) {
            throwError(leaveReq.getLeaveType().getSearchKey());
        }
        }
       
    }

    private void throwError(String leaveType) throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "You dont have "+leaveType+" leaves so You need to select LOP", language));
    } */
}
