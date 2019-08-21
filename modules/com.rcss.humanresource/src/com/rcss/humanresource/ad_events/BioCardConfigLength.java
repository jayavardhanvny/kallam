package com.rcss.humanresource.ad_events;

//import com.rcss.humanresource.data.RCHR_Token;
import com.rcss.humanresource.data.RchrBiocardconfig;
import javax.enterprise.event.Observes;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.core.OBContext;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;

public class BioCardConfigLength extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RchrBiocardconfig.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RchrBiocardconfig crdConfig = (RchrBiocardconfig) event.getTargetInstance();
        if (crdConfig.getPunchno().length() != 4) {
            throwError();
        }
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RchrBiocardconfig crdConfig = (RchrBiocardconfig) event.getTargetInstance();
        if (crdConfig.getPunchno().length() != 4) {
            throwError();
        }
    }

    private void throwError() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "Punch Number Should be Maintaind in 4 digits", language));
    }
}
