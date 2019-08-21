package com.redcarpet.goodsissue.ad_events;

import com.redcarpet.goodsissue.data.RcgiMaterialIndent;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import com.rcss.humanresource.util.DocumentNumberEventUtil;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

public class YarnIndentDocumentEvent extends EntityPersistenceEventObserver {
    private static Entity entities[] = { ModelProvider.getInstance().getEntity(RcgiMaterialIndent.ENTITY_NAME)};
    protected Logger logger = Logger.getLogger(RcgiMaterialIndent.ENTITY_NAME);
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        final RcgiMaterialIndent indent = (RcgiMaterialIndent) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        documentNumberEventUtil.saveDocument(indent.getDocumentType(),indent.getMovementDate());
    }
   
}
