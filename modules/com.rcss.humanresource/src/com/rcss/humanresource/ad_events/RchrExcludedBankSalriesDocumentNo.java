package com.rcss.humanresource.ad_events;

import com.rcss.humanresource.data.RchrExbanksalpayments;
import com.rcss.humanresource.util.DocumentNumberEventUtil;
import org.apache.log4j.Logger;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;

import javax.enterprise.event.Observes;

public class RchrExcludedBankSalriesDocumentNo extends EntityPersistenceEventObserver {
    private static Entity entities[] = {ModelProvider.getInstance().getEntity(RchrExbanksalpayments.ENTITY_NAME)};
    protected static Logger logger = Logger.getLogger(RchrExcludedBankSalriesDocumentNo.class);

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
    public void onSave(@Observes EntityNewEvent event){
        if (!isValidEvent(event)){
            return;
        }
        final RchrExbanksalpayments rchrExbanksalpayments = (RchrExbanksalpayments) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        documentNumberEventUtil.saveDocument(rchrExbanksalpayments.getDocumentType(),rchrExbanksalpayments.getPaymentDate());
    }
    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        final RchrExbanksalpayments rchrExbanksalpayments = (RchrExbanksalpayments) event.getTargetInstance();
        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        documentNumberEventUtil.saveDocument(rchrExbanksalpayments.getDocumentType(),rchrExbanksalpayments.getPaymentDate());
    }
}