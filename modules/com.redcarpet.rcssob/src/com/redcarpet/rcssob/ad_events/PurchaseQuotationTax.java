package com.redcarpet.rcssob.ad_events;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.openbravo.model.common.businesspartner.Greeting;
import com.redcarpet.rcssob.data.*;

import java.util.logging.Logger;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.invoice.InvoiceTax;
import org.openbravo.model.financialmgmt.tax.TaxRate;


public class PurchaseQuotationTax extends EntityPersistenceEventObserver {

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(
			PurchaseQuotationLines.ENTITY_NAME) };
	protected Logger logger = Logger.getLogger(InvoiceLine.ENTITY_NAME);

	protected Entity[] getObservedEntities() {
		return entities;
	}

	  public void onUpdate(@Observes EntityUpdateEvent event) {
		    if (!isValidEvent(event)) {
		      return;
		    }
		    
		System.out.println(event.getTargetInstance().getId());

		System.out.println("in event for pq tax");

		final PurchaseQuotationLines pqline = (PurchaseQuotationLines) event.getTargetInstance();
	   // String id= pqline.getPurchaseQuotationlines.
		PurchaseQuotation strPurchaseQuotation=pqline.getRcobPurchasequotation();
		System.out.println("pq id" + strPurchaseQuotation);
		//String strPurchaseQuotationID = pqline.getRCPPurchasequotation().getId();
		String strPurchaseQuotationID = pqline.getRcobPurchasequotation().getId();
		
		TaxRate taxId=pqline.getTax();
		long linenum = pqline.getLineNo();
		BigDecimal qty=pqline.getQuantity();
		//long qtyl=qty.longValue();
		BigDecimal price=pqline.getNetUnitPrice();
		//BigDecimal prc=BigDecimal.valueOf(price);
		BigDecimal taxamt=qty.multiply(price);
		long taxamtpq=taxamt.longValue();
		BigDecimal txrate=pqline.getTax().getRate();
		System.out.println("tax rate is ..."+txrate);
		BigDecimal multx = taxamt.multiply(txrate);
		BigDecimal divvar=new BigDecimal("100");
		BigDecimal divtx=multx.divide(divvar);
		long finamt=divtx.longValue();
		System.out.println("tax amount is ..."+finamt);
		
		Quotationtax pqtax = OBProvider.getInstance().get(Quotationtax.class);
		
	 	/*Connection con = OBDal.getInstance().getSession().connection();
	 	String qry = "delete from bm_quotationtax where syst_client_id='"+strPurchaseQuotationID+"'";
	 	con.createStatement().execute(qry);*/
	 	
		pqtax.setLineNo(linenum);
		pqtax.setTax(taxId);
		pqtax.setRcobPurchasequotationlines(pqline);
		//pqtax.setRCPPurchasequotation(strPurchaseQuotation);
		pqtax.setRcobPurchasequotation(strPurchaseQuotation);
		//pqtax.setTaxableAmount(taxamtpq);
		pqtax.setTaxableAmount(taxamtpq);
		//pqtax.setTaxAmount(finamt);
		pqtax.setTaxAmount(finamt);
		OBDal.getInstance().save(pqtax);
		System.out.println("end of pq tax event");
		
}

}