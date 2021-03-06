package com.redcarpet.goodsissue.ad_events;

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
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import java.util.logging.Logger;
import org.openbravo.client.kernel.event.EntityUpdateEvent;

public class EventUpdateRCGILinesTotalMI extends EntityPersistenceEventObserver{

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(RCGI_MiLines.ENTITY_NAME)};
	protected Logger logger = Logger.getLogger(RCGI_MiLines.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	
	 public void onUpdate(@Observes EntityUpdateEvent event) {
				    if (!isValidEvent(event)) {
				      return;
				    }
		System.out.println(event.getTargetInstance().getId());
		
		System.out.println("in event");
		
		
		final RCGI_MiLines issueline = (RCGI_MiLines)event.getTargetInstance();
		
		RCGI_MaterialIssue strGoodsIssue=issueline.getMaterialIssue();
		String strGoodsIssueId=issueline.getMaterialIssue().getId();

		
		BigDecimal btotal=issueline.getLineNetAmount();
		
		System.out.println("btotal is" +btotal);
		
		OBCriteria<RCGI_MiLines> issuelineList=OBDal.getInstance().createCriteria(RCGI_MiLines.class);
		issuelineList.add(Restrictions.eq(RCGI_MiLines.PROPERTY_MATERIALISSUE,strGoodsIssue));
		//BigDecimal sum= new BigDecimal("0");
		BigDecimal sum=new BigDecimal("0");
		for (RCGI_MiLines linesnew : issuelineList.list())
		{
			BigDecimal newtot=linesnew.getLineNetAmount();
			sum=sum.add(newtot);
			
		}
		System.out.println("total is:" +sum);
	
		OBCriteria<RCGI_MaterialIssue> goodsissueList=OBDal.getInstance().createCriteria(RCGI_MaterialIssue.class);
		goodsissueList.add(Restrictions.eq(RCGI_MaterialIssue.PROPERTY_ID,strGoodsIssueId));
		//BigDecimal sum= new BigDecimal("0");
		
		for (RCGI_MaterialIssue goodsissue : goodsissueList.list())
		{
			goodsissue.setSummedLineAmount(sum);
		}
		System.out.println("total is:" +sum);
		
	
}
}
