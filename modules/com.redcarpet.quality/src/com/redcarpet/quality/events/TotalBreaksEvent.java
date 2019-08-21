package com.redcarpet.quality.events;

import java.math.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.event.Observes;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.Greeting;


import com.redcarpet.quality.data.RcqaWrapbreak;
import com.redcarpet.quality.data.RcqaWrapbreakline;
import java.util.logging.Logger;

public class TotalBreaksEvent extends EntityPersistenceEventObserver{

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(RcqaWrapbreakline.ENTITY_NAME)};
	protected Logger logger = Logger.getLogger(RcqaWrapbreakline.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	public void onUpdate(@Observes EntityUpdateEvent event) {
	    if (!isValidEvent(event)) {
	      return;
	    }
		System.out.println(event.getTargetInstance().getId());
		
		System.out.println("in event");
		
		
		final RcqaWrapbreakline macs = (RcqaWrapbreakline)event.getTargetInstance();

		RcqaWrapbreak strWrapbreakID = macs.getRcqaWrapbreak();
		System.out.println("break id is" +strWrapbreakID);
		
		String strWrapbreakIDN = macs.getRcqaWrapbreak().getId();
		
		long btotal=macs.getNoofbreaks();
		
		System.out.println("btotal is" +btotal);
		
		
		OBCriteria<RcqaWrapbreakline> macList=OBDal.getInstance().createCriteria(RcqaWrapbreakline.class);
		macList.add(Restrictions.eq(RcqaWrapbreakline.PROPERTY_RCQAWRAPBREAK,strWrapbreakID));
		//BigDecimal sum= new BigDecimal("0");
		long sum=0;
		for (RcqaWrapbreakline macsn : macList.list())
		{
			long newtot=macsn.getNoofbreaks();
			sum=sum+newtot;

		}
		
	
		System.out.println("total is:" +sum);
		
		BigDecimal sumnew=new BigDecimal(sum);
		System.out.println("total new is:" +sumnew);
		
	
		OBCriteria<RcqaWrapbreak> epcgList=OBDal.getInstance().createCriteria(RcqaWrapbreak.class);
		epcgList.add(Restrictions.eq(RcqaWrapbreak.PROPERTY_ID,strWrapbreakIDN));
		//BigDecimal sum= new BigDecimal("0");
		
		for (RcqaWrapbreak epcg : epcgList.list())
		{
			
			epcg.setTotbrkcaused(sumnew);
			
			BigDecimal totwrap=epcg.getTotwraplength();
			
      BigDecimal resone=sumnew.divide(totwrap, 20, RoundingMode.HALF_UP);  
            
            System.out.println("divide res is" +resone);
            
            BigDecimal mulvalue=new BigDecimal("1000000");
            


            BigDecimal restwo=resone.multiply(mulvalue);
            
            epcg.setBreaks(restwo);
		}
		
		
}
}