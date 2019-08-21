package com.rcss.humanresource.ad_events;

import com.rcss.humanresource.data.RchrBanksalpayments;
import com.rcss.humanresource.util.DocumentNumberEventUtil;
import org.apache.log4j.Logger;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;


import javax.enterprise.event.Observes;

public class RchrBankSalariesDocumentNo extends EntityPersistenceEventObserver {
    private static Entity entities[] = {ModelProvider.getInstance().getEntity(RchrBanksalpayments.ENTITY_NAME)};
    protected static Logger logger = Logger.getLogger(RchrBankSalariesDocumentNo.class);

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
    public void onSave(@Observes EntityNewEvent event){
        if (!isValidEvent(event)){
            return;
        }
        final RchrBanksalpayments rchrBanksalpayments = (RchrBanksalpayments) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        documentNumberEventUtil.saveDocument(rchrBanksalpayments.getDocumentType(),rchrBanksalpayments.getPaymentDate());
    }
    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        final RchrBanksalpayments rchrBanksalpayments = (RchrBanksalpayments) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();

        documentNumberEventUtil.saveDocument(rchrBanksalpayments.getDocumentType(),rchrBanksalpayments.getPaymentDate());
    }
}
