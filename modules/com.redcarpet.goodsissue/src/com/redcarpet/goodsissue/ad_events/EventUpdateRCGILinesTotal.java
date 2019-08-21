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
import org.openbravo.model.common.businesspartner.Greeting;

import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import java.util.logging.Logger;
import org.openbravo.client.kernel.event.EntityUpdateEvent;

public class EventUpdateRCGILinesTotal extends EntityPersistenceEventObserver{

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(RCGI_GoodsIssue_Line.ENTITY_NAME)};
	protected Logger logger = Logger.getLogger(RCGI_GoodsIssue_Line.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	
	 public void onUpdate(@Observes EntityUpdateEvent event) {
				    if (!isValidEvent(event)) {
				      return;
				    }
		System.out.println(event.getTargetInstance().getId());
		
		System.out.println("in event");
		
		final RCGI_GoodsIssue_Line issueline = (RCGI_GoodsIssue_Line)event.getTargetInstance();
		
		RCGI_GoodsIssue strGoodsIssue=issueline.getGoodsIssue();
		String strGoodsIssueId=issueline.getGoodsIssue().getId();
		BigDecimal btotal=issueline.getLineNetAmount();
		
		System.out.println("btotal is" +btotal);
		
		OBCriteria<RCGI_GoodsIssue_Line> issuelineList=OBDal.getInstance().createCriteria(RCGI_GoodsIssue_Line.class);
		issuelineList.add(Restrictions.eq(RCGI_GoodsIssue_Line.PROPERTY_GOODSISSUE,strGoodsIssue));
		//BigDecimal sum= new BigDecimal("0");
		BigDecimal sum=new BigDecimal("0");
		for (RCGI_GoodsIssue_Line linesnew : issuelineList.list())
		{
			BigDecimal newtot=linesnew.getLineNetAmount();
			sum=sum.add(newtot);
			
		}
		System.out.println("total is:" +sum);
	
		OBCriteria<RCGI_GoodsIssue> goodsissueList=OBDal.getInstance().createCriteria(RCGI_GoodsIssue.class);
		goodsissueList.add(Restrictions.eq(RCGI_GoodsIssue.PROPERTY_ID,strGoodsIssueId));
		//BigDecimal sum= new BigDecimal("0");
		
		for (RCGI_GoodsIssue goodsissue : goodsissueList.list())
		{
			goodsissue.setSummedLineAmount(sum);
		}
		System.out.println("total is:" +sum);
		
	
}
}