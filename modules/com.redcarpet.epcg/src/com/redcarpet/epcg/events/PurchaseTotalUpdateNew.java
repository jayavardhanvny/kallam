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

public class PurchaseTotalUpdateNew extends EntityPersistenceEventObserver{

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(Epcg_EpcgNew.ENTITY_NAME)};
	protected Logger logger = Logger.getLogger(Epcg_EpcgNew.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	public void onSave(@Observes EntityNewEvent event) {
		if (!isValidEvent(event)) {
			return;
		}
		System.out.println(event.getTargetInstance().getId());
		
		System.out.println("in new event of purchase tot update");
		
		
		final Epcg_EpcgNew epcg = (Epcg_EpcgNew)event.getTargetInstance();

		String strEpcgID =epcg.getId();
		System.out.println("epcg id is" +strEpcgID);
		
		
		OBCriteria<EpcgEpcgPurchase> macList=OBDal.getInstance().createCriteria(EpcgEpcgPurchase.class);
		macList.add(Restrictions.eq(EpcgEpcgPurchase.PROPERTY_EPCGEPCGNEW,epcg));
		BigDecimal sum= new BigDecimal("0");
		//BigDecimal sum=btotal;
		for (EpcgEpcgPurchase macs : macList.list())
		{
			BigDecimal newtot=macs.getInvoiceTotal();
			sum=sum.add(newtot);
			
		}
		System.out.println("total is:" +sum);
	
		OBCriteria<Epcg_EpcgNew> epcgList=OBDal.getInstance().createCriteria(Epcg_EpcgNew.class);
		epcgList.add(Restrictions.eq(Epcg_EpcgNew.PROPERTY_ID,strEpcgID));
		//BigDecimal sum= new BigDecimal("0");
		
		for (Epcg_EpcgNew epcgn : epcgList.list())
		{
			epcgn.setPurchaseImportTotal(sum);
			
		}
		System.out.println("new total after updating is:" +sum);
		
		
}
}