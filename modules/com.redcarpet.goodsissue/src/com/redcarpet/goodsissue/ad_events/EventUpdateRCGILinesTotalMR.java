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

import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import com.redcarpet.goodsissue.data.RCGI_MaterialReceipt;
import com.redcarpet.goodsissue.data.RCGI_MrLines;
import java.util.logging.Logger;
import org.openbravo.client.kernel.event.EntityUpdateEvent;

public class EventUpdateRCGILinesTotalMR extends EntityPersistenceEventObserver{

	private static Entity entities[] = { ModelProvider.getInstance().getEntity(RCGI_MrLines.ENTITY_NAME)};
	protected Logger logger = Logger.getLogger(RCGI_MrLines.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	
	 public void onUpdate(@Observes EntityUpdateEvent event) {
				    if (!isValidEvent(event)) {
				      return;
				    }
		System.out.println(event.getTargetInstance().getId());
		
		System.out.println("in event");
		
		
		final RCGI_MrLines issueline = (RCGI_MrLines)event.getTargetInstance();
		
		RCGI_MaterialReceipt strGoodsIssue=issueline.getMaterialReceipt();
		String strGoodsIssueId=issueline.getMaterialReceipt().getId();

		
		BigDecimal btotal=issueline.getLineNetAmount();
		
		System.out.println("btotal is" +btotal);
		
		OBCriteria<RCGI_MrLines> issuelineList=OBDal.getInstance().createCriteria(RCGI_MrLines.class);
		issuelineList.add(Restrictions.eq(RCGI_MrLines.PROPERTY_MATERIALRECEIPT,strGoodsIssue));
		//BigDecimal sum= new BigDecimal("0");
		BigDecimal sum=new BigDecimal("0");
		for (RCGI_MrLines linesnew : issuelineList.list())
		{
			BigDecimal newtot=linesnew.getLineNetAmount();
			sum=sum.add(newtot);
			
		}
		System.out.println("total is:" +sum);
	
		OBCriteria<RCGI_MaterialReceipt> goodsissueList=OBDal.getInstance().createCriteria(RCGI_MaterialReceipt.class);
		goodsissueList.add(Restrictions.eq(RCGI_MaterialReceipt.PROPERTY_ID,strGoodsIssueId));
		//BigDecimal sum= new BigDecimal("0");
		
		for (RCGI_MaterialReceipt goodsissue : goodsissueList.list())
		{
			goodsissue.setSummedLineAmount(sum);
		}
		System.out.println("total is:" +sum);
		
	
}
}
