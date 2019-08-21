package com.redcarpet.epcg.events;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.event.Observes;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.Greeting;

import com.redcarpet.epcg.data.Epcg_EpcgNew;
import com.redcarpet.epcg.data.EpcgEpcgPurchase;
import org.openbravo.model.common.plm.Product;
import java.util.logging.Logger;

public class PurchaseTotalUpdate extends EntityPersistenceEventObserver{

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(EpcgEpcgPurchase.ENTITY_NAME)};
	protected Logger logger = Logger.getLogger(EpcgEpcgPurchase.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	public void onUpdate(@Observes EntityUpdateEvent event) {
		if (!isValidEvent(event)) {
			return;
		}
		System.out.println(event.getTargetInstance().getId());
		
		System.out.println("in event of purchase tot update");
		
		
		final EpcgEpcgPurchase macs = (EpcgEpcgPurchase)event.getTargetInstance();

		Epcg_EpcgNew strEpcgID = macs.getEpcgEpcgnew();
		System.out.println("epcg id is" +strEpcgID);
		
		String strEpcgIDN = macs.getEpcgEpcgnew().getId();
		
		BigDecimal btotal=macs.getInvoiceTotal();
		
		System.out.println("btotal is" +btotal);
		
		
		
		OBCriteria<EpcgEpcgPurchase> macList=OBDal.getInstance().createCriteria(EpcgEpcgPurchase.class);
		macList.add(Restrictions.eq(EpcgEpcgPurchase.PROPERTY_EPCGEPCGNEW,strEpcgID));
		BigDecimal sum= new BigDecimal("0");
		//BigDecimal sum=btotal;
		for (EpcgEpcgPurchase macsn : macList.list())
		{
			BigDecimal newtot=macsn.getInvoiceTotal();
			sum=sum.add(newtot);
			
		}
		System.out.println("total is:" +sum);
	
		OBCriteria<Epcg_EpcgNew> epcgList=OBDal.getInstance().createCriteria(Epcg_EpcgNew.class);
		epcgList.add(Restrictions.eq(Epcg_EpcgNew.PROPERTY_ID,strEpcgIDN));
		//BigDecimal sum= new BigDecimal("0");
		
		for (Epcg_EpcgNew epcg : epcgList.list())
		{
			epcg.setPurchaseImportTotal(sum);
			
		}
		System.out.println("total after updating is:" +sum);
		
		
}
}