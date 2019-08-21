package com.redcarpet.freight.ad_events;

import com.redcarpet.freight.data.RCFR_AdditionalCostVariables;
import javax.enterprise.event.Observes;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityDeleteEvent;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;

public class RCFR_AdditionalCostVars_Change_Eve extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCFR_AdditionalCostVariables.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCFR_AdditionalCostVariables var = (RCFR_AdditionalCostVariables) event.getTargetInstance();
        var.getSalesOrder().setRcfrCalcdistcost(Boolean.FALSE);
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCFR_AdditionalCostVariables var = (RCFR_AdditionalCostVariables) event.getTargetInstance();
        var.getSalesOrder().setRcfrCalcdistcost(Boolean.FALSE);
    }

    public void onDelete(@Observes EntityDeleteEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCFR_AdditionalCostVariables var = (RCFR_AdditionalCostVariables) event.getTargetInstance();
        var.getSalesOrder().setRcfrCalcdistcost(Boolean.FALSE);
    }
}