package com.rcss.humanresource.ad_events;

import com.rcss.humanresource.data.RchrMemoShift;
import com.rcss.humanresource.util.DocumentNumberEventUtil;
import com.redcarpet.payroll.data.RcplPayrollprocessmanual;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;

import javax.enterprise.event.Observes;
import java.util.logging.Logger;

public class RchrGrievancesDocumentNoEvent extends EntityPersistenceEventObserver {

    private static Entity entities[] = { ModelProvider.getInstance().getEntity(RcplPayrollprocessmanual.ENTITY_NAME) };
    protected Logger logger = Logger.getLogger(RchrMemoShift.ENTITY_NAME);

    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        final RcplPayrollprocessmanual rcplPayrollprocessmanual = (RcplPayrollprocessmanual)event.getTargetInstance();

        DocumentNumberEventUtil documentNumberEventUtil = new DocumentNumberEventUtil();
        documentNumberEventUtil.saveDocument(rcplPayrollprocessmanual.getDocumentType(),rcplPayrollprocessmanual.getDate());
    }
}
