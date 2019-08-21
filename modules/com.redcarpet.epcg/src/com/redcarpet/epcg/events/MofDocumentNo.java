package com.redcarpet.epcg.events;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.Connection;
import javax.enterprise.event.Observes;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.base.exception.OBException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Greeting;


import com.redcarpet.epcg.data.EpcgMof;
import com.redcarpet.epcg.data.EpcgSeqline;
import com.redcarpet.epcg.data.EpcgYarncosttemplate;


import org.openbravo.model.common.enterprise.Organization;

import java.util.logging.Logger;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.invoice.InvoiceTax;
import org.openbravo.model.financialmgmt.calendar.Period;

import org.openbravo.model.ad.utility.Sequence;



public class MofDocumentNo extends EntityPersistenceEventObserver {

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(EpcgMof.ENTITY_NAME) };
	protected Logger logger = Logger.getLogger(EpcgMof.ENTITY_NAME);

	protected Entity[] getObservedEntities() {
		return entities;
	}

	public void onSave(@Observes EntityNewEvent event) {
				    if (!isValidEvent(event)) {
				      return;
				    }
		//System.out.println(event.getTargetInstance().getId());
		
		//System.out.println("in event");
		
		
		final EpcgMof issue = (EpcgMof)event.getTargetInstance();
		
		// update sequence next number
		
		DocumentType doctype = issue.getDocumentType();
		Sequence seq=doctype.getDocumentSequence();
		
		Date gidate=issue.getContractdate();
		
		String status="";
		
		Date aDate=issue.getContractdate();
		
		//System.out.println("Costing date "+aDate);
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal=Calendar.getInstance();
		cal.setTime(aDate);
		cal.set(Calendar.DATE,cal.getActualMinimum(Calendar.DATE));
		java.util.Date fromDate=cal.getTime();
		//System.out.println("Start date cal.getTime() "+cal.getTime());
		
		cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
		
		java.util.Date toDate=cal.getTime();
		
		//System.out.println("End Date cal.getTime() "+cal.getTime());
		

		OBCriteria<Period> p=OBDal.getInstance().createCriteria(Period.class);
		
		p.add(Restrictions.eq(Period.PROPERTY_STARTINGDATE,fromDate)).
		add(Restrictions.eq(Period.PROPERTY_ENDINGDATE,toDate));
		//System.out.println("size..."+p.list().size());
		
		if(p.list().size()!=0){
		for(Period pobj:p.list()){
			Period p1=OBDal.getInstance().get(Period.class,pobj.getId());
			
			
			status=p1.getStatus();
			//System.out.println("status"+status);
		}
		if(status.equals("O")){
			
		} else         
		{
			
			String language = OBContext.getOBContext().getLanguage().getLanguage();
		    ConnectionProvider conn = new DalConnectionProvider(false);
		    throw new OBException(Utility.messageBD(conn, "RCGI_SequenceLineDate", language));
		    
			      }
		}else{
			
			String language = OBContext.getOBContext().getLanguage().getLanguage();
		    ConnectionProvider conn = new DalConnectionProvider(false);
		    throw new OBException(Utility.messageBD(conn, "RCGI_SequenceLineDate", language));
		    
		}
		
		
		OBCriteria<EpcgSeqline> seqlinelist=OBDal.getInstance().createCriteria(EpcgSeqline.class);
		seqlinelist.add(Restrictions.eq(EpcgSeqline.PROPERTY_SEQUENCE,seq));
		/*seqlinelist.add(Restrictions.ge(EpcgSeqline.PROPERTY_STARTINGDATE,gidate));
		seqlinelist.add(Restrictions.lt(EpcgSeqline.PROPERTY_ENDINGDATE,gidate));*/
		
		//System.out.println("size of lines is" +seqlinelist.list().size());
		//System.out.println(".......");
		
		for (EpcgSeqline seqline : seqlinelist.list()){
			  Date stdate=seqline.getStartingDate();
			  Date endate=seqline.getEndingDate();
			  
			  if(((gidate.before(endate))&&(gidate.after(stdate))) || (gidate.equals(stdate)) || (gidate.equals(endate)))
			  {
			  //System.out.println("inside for loop == if");
		
			  long oldnum=seqline.getNextAssignedNumber();
			  long incnum=seqline.getIncrementBy();
			  long newnum=oldnum+incnum;
			  //System.out.println("newnum is:" + newnum);
		
			  seqline.setNextAssignedNumber(newnum);
			  }
				
		}
		
		
	}
		//System.out.println("end of event new one");
	 
	    
}