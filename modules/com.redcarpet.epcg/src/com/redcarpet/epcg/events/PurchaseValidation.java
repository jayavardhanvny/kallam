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
import com.redcarpet.epcg.data.Epcg_EpcgNew;
import com.redcarpet.epcg.data.EpcgMacSpare;
import java.util.logging.Logger;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.plm.Product;

public class PurchaseValidation extends EntityPersistenceEventObserver {

    private static Entity entities[] = {ModelProvider.getInstance().getEntity(InvoiceLine.ENTITY_NAME)};
    protected Logger logger = Logger.getLogger(InvoiceLine.ENTITY_NAME);

    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }

        final InvoiceLine line = (InvoiceLine) event.getTargetInstance();
        BigDecimal curquantity = line.getInvoicedQuantity();
        Epcg_EpcgNew strEpcgID = line.getInvoice().getEpcgEpcgone();
        Product strProduct = line.getProduct();
        if (line.getProduct() != null) {
            String strProductId = line.getProduct().getId();
            int currentnew = curquantity.intValue();
            Boolean b1 = line.getInvoice().isSalesTransaction();
            OBCriteria<EpcgMacSpare> macList = OBDal.getInstance().createCriteria(EpcgMacSpare.class);
            macList.add(Restrictions.eq(EpcgMacSpare.PROPERTY_EPCGEPCGNEW, strEpcgID));
            macList.add(Restrictions.eq(EpcgMacSpare.PROPERTY_PRODUCT, strProduct));
            //BigDecimal sum= new BigDecimal("0");
            long stillqty = 0;
            String pid = "";
            for (EpcgMacSpare macsn : macList.list()) {
                stillqty = macsn.getStillQuantityToPurchase();
                pid = macsn.getProduct().getId();

            }
            BigDecimal still = new BigDecimal(stillqty);
            int stillnew = still.intValue();
            if ((currentnew > stillnew) && (strProductId.equals(pid)) && (b1 == false)) {

                String language = OBContext.getOBContext().getLanguage().getLanguage();
                ConnectionProvider conn = new DalConnectionProvider(false);
                throw new OBException(Utility.messageBD(conn, "EPCG_ErrorMessage", language));

            }
        }

    }
}
