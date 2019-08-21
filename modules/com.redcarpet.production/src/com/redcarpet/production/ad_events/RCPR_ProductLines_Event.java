package com.redcarpet.production.ad_events;

import com.redcarpet.production.data.RCPR_PrExpense;
import com.redcarpet.production.data.RCPR_PrProduct;
import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import javax.enterprise.event.Observes;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityDeleteEvent;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;

public class RCPR_ProductLines_Event extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCPR_PrProduct.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPR_PrProduct target = (RCPR_PrProduct) event.getTargetInstance();
        BigDecimal addition = getSumAdditions(target.getProductionRun());
        target.getProductionRun().setTotalItemCost(addition);
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPR_PrProduct target = (RCPR_PrProduct) event.getTargetInstance();
        BigDecimal oldAmt = getSumAdditions(target.getProductionRun());
        BigDecimal newAmt = target.getLineNetAmount();
        target.getProductionRun().setTotalItemCost(oldAmt.add(newAmt));
    }

    public void onDelete(@Observes EntityDeleteEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPR_PrProduct target = (RCPR_PrProduct) event.getTargetInstance();
        BigDecimal oldAmt = getSumAdditions(target.getProductionRun());
        BigDecimal newAmt = target.getLineNetAmount();
        target.getProductionRun().setTotalItemCost(oldAmt.subtract(newAmt));
    }

    private BigDecimal getSumAdditions(RCPR_ProductionRun run) {
        double retVal = 0;
        for (RCPR_PrProduct ex : run.getRCPRPrProductList()) {
            retVal += ex.getLineNetAmount().doubleValue();
        }
        return new BigDecimal(retVal);
    }
}