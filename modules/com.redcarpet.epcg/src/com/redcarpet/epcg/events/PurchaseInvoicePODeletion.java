package com.redcarpet.epcg.events;

import java.math.BigDecimal;
import javax.enterprise.event.Observes;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.base.exception.OBException;
import java.util.logging.Logger;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.plm.Product;
import org.openbravo.client.kernel.event.EntityDeleteEvent;

public class PurchaseInvoicePODeletion extends EntityPersistenceEventObserver {

    private static Entity entities[] = {ModelProvider.getInstance().getEntity(InvoiceLine.ENTITY_NAME)};
    protected Logger logger = Logger.getLogger(InvoiceLine.ENTITY_NAME);

    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onDelete(@Observes EntityDeleteEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
       // System.out.println("in side PurchaseInvoicePODeletion");

        final InvoiceLine line = (InvoiceLine) event.getTargetInstance();
        Boolean b1 = line.getInvoice().isSalesTransaction();
       
        	String invid=line.getInvoice().getId();
        	//System.out.println("invid is" +invid);
        	Invoice inv = OBDal.getInstance().get(Invoice.class, invid);
        	Order order=null;
        	inv.setSalesOrder(order);

        
       
    }
}
