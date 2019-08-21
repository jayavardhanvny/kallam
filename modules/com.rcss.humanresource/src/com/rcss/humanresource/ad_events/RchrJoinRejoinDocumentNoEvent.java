package com.rcss.humanresource.ad_events;

import com.rcss.humanresource.data.RchrJoinRejoiningform;
import com.rcss.humanresource.data.RchrMemoShift;
import com.rcss.humanresource.util.DocumentNumberEventUtil;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.enterprise.DocumentType;

import javax.enterprise.event.Observes;
import java.util.logging.Logger;

public class RchrJoinRejoinDocumentNoEvent extends EntityPersistenceEventObserver {
    private static Entity entities[] = { ModelProvider.getInstance().getEntity(RchrJoinRejoiningform.ENTITY_NAME) };
    protected Logger logger = Logger.getLogger(RchrJoinRejoiningform.ENTITY_NAME);

    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        String strEmployeeDocumnentId = "908745C15A4B45C0A1B157BE27236909";
        final RchrJoinRejoiningform rchrJoinRejoiningform = (RchrJoinRejoiningform) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        DocumentType documentType = OBDal.getInstance().get(DocumentType.class,strEmployeeDocumnentId);
        documentNumberEventUtil.saveDocument(rchrJoinRejoiningform.getDocumentType(),rchrJoinRejoiningform.getDate());
        if(rchrJoinRejoiningform.getJointype().equals("J")){
            documentNumberEventUtil.saveDocument(documentType,rchrJoinRejoiningform.getDate());
        }

    }
    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        final RchrJoinRejoiningform rchrJoinRejoiningform = (RchrJoinRejoiningform) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        documentNumberEventUtil.saveDocument(rchrJoinRejoiningform.getDocumentType(),rchrJoinRejoiningform.getDate());
    }
}
