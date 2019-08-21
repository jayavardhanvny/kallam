package com.redcarpet.goodsissue.ad_events;

import com.redcarpet.goodsissue.data.RCGIDepartmentIssue;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import com.rcss.humanresource.util.DocumentNumberEventUtil;
import com.rcss.humanresource.util.DocumentNumberEventUtil;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

public class DeptIssueDocumentEvent extends EntityPersistenceEventObserver {
    private static Entity entities[] = { ModelProvider.getInstance().getEntity(RCGIDepartmentIssue.ENTITY_NAME)};
    protected Logger logger = Logger.getLogger(RCGIDepartmentIssue.ENTITY_NAME);
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        final RCGIDepartmentIssue indent = (RCGIDepartmentIssue) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        documentNumberEventUtil.saveDocument(indent.getDocumentType(),indent.getMovementDate());
    }
}
