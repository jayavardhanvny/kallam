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
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.Greeting;

import com.redcarpet.epcg.data.Epcg_EpcgNew;
import com.redcarpet.epcg.data.EpcgMacSpare;
import org.openbravo.model.common.plm.Product;
import java.util.logging.Logger;

public class TotalAutoUpdate extends EntityPersistenceEventObserver{

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(EpcgMacSpare.ENTITY_NAME)};
	protected Logger logger = Logger.getLogger(EpcgMacSpare.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	public void onSave(@Observes EntityNewEvent event){
		if(!isValidEvent(event))
		{
			return;
		}
		System.out.println(event.getTargetInstance().getId());
		
		System.out.println("in event");
		
		
		final EpcgMacSpare macs = (EpcgMacSpare)event.getTargetInstance();

		Epcg_EpcgNew strEpcgID = macs.getEpcgEpcgnew();
		System.out.println("epcg id is" +strEpcgID);
		
		String strEpcgIDN = macs.getEpcgEpcgnew().getId();
		
		BigDecimal btotal=macs.getTotal();
		
		System.out.println("btotal is" +btotal);
		String strProductID=macs.getProduct().getId();
		
		
		OBCriteria<EpcgMacSpare> macList=OBDal.getInstance().createCriteria(EpcgMacSpare.class);
		macList.add(Restrictions.eq(EpcgMacSpare.PROPERTY_EPCGEPCGNEW,strEpcgID));
		//BigDecimal sum= new BigDecimal("0");
		BigDecimal sum=btotal;
		for (EpcgMacSpare macsn : macList.list())
		{
			BigDecimal newtot=macsn.getTotal();
			sum=sum.add(newtot);
			
		}
		System.out.println("total is:" +sum);
	
		OBCriteria<Epcg_EpcgNew> epcgList=OBDal.getInstance().createCriteria(Epcg_EpcgNew.class);
		epcgList.add(Restrictions.eq(Epcg_EpcgNew.PROPERTY_ID,strEpcgIDN));
		//BigDecimal sum= new BigDecimal("0");
		
		for (Epcg_EpcgNew epcg : epcgList.list())
		{
			epcg.setPermitTotal(sum);
			BigDecimal years=epcg.getMultipleFactor();
		
			BigDecimal newone=sum.multiply(years);
			epcg.setTotalTarget(newone);
		}
		System.out.println("total is:" +sum);
		
		
		OBCriteria<Product> pdList=OBDal.getInstance().createCriteria(Product.class);
		pdList.add(Restrictions.eq(Product.PROPERTY_ID,strProductID));
		//BigDecimal sum= new BigDecimal("0");
		
		for (Product pdct : pdList.list())
		{
			//pdct.setEpcgCheckepcg(true);
		}
		System.out.println("end of event:" +sum);
		
}
}