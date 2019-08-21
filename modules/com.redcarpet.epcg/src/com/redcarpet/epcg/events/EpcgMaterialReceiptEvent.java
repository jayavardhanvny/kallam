package com.redcarpet.epcg.events;

import com.rcss.humanresource.util.DocumentNumberEventUtil;
import com.redcarpet.goodsissue.data.RCGI_MaterialReceipt;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.model.common.enterprise.DocumentType;

import javax.enterprise.event.Observes;
import java.util.logging.Logger;

public class EpcgMaterialReceiptEvent extends EntityPersistenceEventObserver {
    private static Entity entities[] = { ModelProvider.getInstance().getEntity(RCGI_MaterialReceipt.ENTITY_NAME) };
    protected Logger logger = Logger.getLogger(RCGI_MaterialReceipt.ENTITY_NAME);

    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        final RCGI_MaterialReceipt rcgi_materialReceipt = (RCGI_MaterialReceipt)event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        DocumentType doctype = rcgi_materialReceipt.getDocumentType();
        documentNumberEventUtil.saveDocument(doctype,rcgi_materialReceipt.getMovementDate());
    }
}
