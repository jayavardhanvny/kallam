package com.redcarpet.goodsissue.ad_events;


import com.redcarpet.goodsissue.data.RCGIDepartmentReceipt;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import com.rcss.humanresource.util.DocumentNumberEventUtil;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;

import javax.enterprise.event.Observes;
import java.util.logging.Logger;

public class DeptReceiptDocumentEvent extends EntityPersistenceEventObserver {
    private static Entity entities[] = { ModelProvider.getInstance().getEntity(RCGIDepartmentReceipt.ENTITY_NAME)};
    protected Logger logger = Logger.getLogger(RCGIDepartmentReceipt.ENTITY_NAME);
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        final RCGIDepartmentReceipt indent = (RCGIDepartmentReceipt) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        documentNumberEventUtil.saveDocument(indent.getDocumentType(),indent.getMovementDate());
    }
}
