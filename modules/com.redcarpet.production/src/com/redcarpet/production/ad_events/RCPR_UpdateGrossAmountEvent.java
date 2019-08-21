package com.redcarpet.production.ad_events;

import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import javax.enterprise.event.Observes;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBDal;

public class RCPR_UpdateGrossAmountEvent extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCPR_ProductionRun.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPR_ProductionRun target = (RCPR_ProductionRun) event.getTargetInstance();
        BigDecimal gross = target.getTotalItemCost().add(target.getExpenseCost());
        OBDal.getInstance().get(RCPR_ProductionRun.class, target.getId()).setGrossAmount(gross);


    }
}