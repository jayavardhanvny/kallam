package com.redcarpet.goodsissue.ad_events;

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
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import org.openbravo.client.kernel.event.EntityDeleteEvent;


public class GoodsIssueNewLineValidation extends EntityPersistenceEventObserver {

    private static Entity entities[] = {ModelProvider.getInstance().getEntity(RCGI_GoodsIssue_Line.ENTITY_NAME)};
    protected Logger logger = Logger.getLogger(RCGI_GoodsIssue_Line.ENTITY_NAME);

    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }

        final RCGI_GoodsIssue_Line line = (RCGI_GoodsIssue_Line) event.getTargetInstance();
        String docstatus=line.getGoodsIssue().getDocumentStatus();
        System.out.println("docstatus is" +docstatus);
       
            if (docstatus.equals("CO")) {

                String language = OBContext.getOBContext().getLanguage().getLanguage();
                ConnectionProvider conn = new DalConnectionProvider(false);
                throw new OBException(Utility.messageBD(conn, "RCGI_New_ErrorMessage", language));

            } else
            {
            	System.out.println("gi is in draft");
            }
        }
    
    public void onDelete(@Observes EntityDeleteEvent event) {
        if (!isValidEvent(event)) {
          return;
        }

        final RCGI_GoodsIssue_Line line = (RCGI_GoodsIssue_Line) event.getTargetInstance();
        String docstatus=line.getGoodsIssue().getDocumentStatus();
        System.out.println("docstatus is" +docstatus);
       
            if (docstatus.equals("CO")) {

                String language = OBContext.getOBContext().getLanguage().getLanguage();
                ConnectionProvider conn = new DalConnectionProvider(false);
                throw new OBException(Utility.messageBD(conn, "RCGI_New_ErrorMessage", language));

            } else
            {
            	System.out.println("gi is in draft");
            }
      }

}
