 package com.rcss.humanresource.ad_events;

 import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.rcss.humanresource.data.*;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
//import com.sun.imageio.plugins.common.BogusColorSpace;

import javax.enterprise.event.Observes;

import oracle.net.aso.b;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityDeleteEvent;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;

public class Fabricinspection extends EntityPersistenceEventObserver {
    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHR_Inspcategoryline.ENTITY_NAME)};  
    protected Logger logger = Logger.getLogger(RCHR_Inspcategoryline.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	public void onSave(@Observes EntityNewEvent event){
		if(!isValidEvent(event))
		{
			return;
		}
      final RCHR_Inspcategoryline lines = (RCHR_Inspcategoryline)event.getTargetInstance();
      
      RCHR_Inspectionsheet inspSheet = lines.getRchrInspectionsheet();
      RCHRQualitystandard standard = inspSheet.getRchrQualitystandard();
      
      //long deduction = lines.getA()+lines.getAone()+lines.getB()+lines.getSl();
      OBCriteria<RCHR_Inspcategoryline> linelist = OBDal.getInstance().createCriteria(RCHR_Inspcategoryline.class);
		linelist.add(Restrictions.eq(RCHR_Inspcategoryline.PROPERTY_RCHRINSPECTIONSHEET, inspSheet));	
	
		
       BigDecimal sumA=lines.getA();
       BigDecimal sumB=lines.getB();
       BigDecimal sumAone=lines.getAone();
       BigDecimal sumSl=lines.getSl();
       
	  for(RCHR_Inspcategoryline inspLines : linelist.list())
	  {
		  BigDecimal newA = inspLines.getA();
		  BigDecimal newB = inspLines.getB();
		  BigDecimal newAone = inspLines.getAone();
		  BigDecimal newSl = inspLines.getSl();
		  
		  sumA = sumA.add(newA);
		  sumB = sumB.add(newB);
		  sumAone = sumAone.add(newAone);
		  sumSl = sumSl.add(newSl);
		  
		  	  
	  }
	  inspSheet.setAtotal(sumA);
	  inspSheet.setBtotal(sumB);
	  inspSheet.setAonetotal(sumAone);
	  inspSheet.setSltotal(sumSl);
	 
	  
		}
	public void onUpdate(@Observes EntityUpdateEvent event) {
		if(!isValidEvent(event))
		{
			return;
		}
		final RCHR_Inspcategoryline lines = (RCHR_Inspcategoryline)event.getTargetInstance();
		RCHR_Inspectionsheet inspSheet = lines.getRchrInspectionsheet();

		RCHRQualitystandard standard = inspSheet.getRchrQualitystandard();
		String id = lines.getRchrInspectionsheet().getId();
		
	    //long deduction = lines.getA().longValue()+lines.getAone().longValue()+lines.getB().longValue()+lines.getSl().longValue();
		  
		OBCriteria<RCHR_Inspcategoryline> linelist = OBDal.getInstance().createCriteria(RCHR_Inspcategoryline.class);
		linelist.add(Restrictions.eq(RCHR_Inspcategoryline.PROPERTY_RCHRINSPECTIONSHEET, inspSheet));	
		
		//long sum = 0;
		BigDecimal sumA=new BigDecimal(0);
		BigDecimal sumB=new BigDecimal(0);
	    BigDecimal sumAone=new BigDecimal(0);
	    BigDecimal sumSl=new BigDecimal(0);
		for(RCHR_Inspcategoryline inspLines : linelist.list())
		  {
			 BigDecimal newA = inspLines.getA();
			  BigDecimal newB = inspLines.getB();
			  BigDecimal newAone = inspLines.getAone();
			  BigDecimal newSl = inspLines.getSl();
			  
			  sumA = sumA.add(newA);
			  sumB = sumB.add(newB);
			  sumAone = sumAone.add(newAone);
			  sumSl = sumSl.add(newSl);
			  
			  	  
		  }
		  inspSheet.setAtotal(sumA);
		  inspSheet.setBtotal(sumB);
		  inspSheet.setAonetotal(sumAone);
		  inspSheet.setSltotal(sumSl);
		  
		  
		  }
		public void onDelete(@Observes EntityDeleteEvent event) {
		    if (!isValidEvent(event)) {
		      return;
		    }
		    final RCHR_Inspcategoryline lines = (RCHR_Inspcategoryline)event.getTargetInstance();
		    RCHR_Inspectionsheet inspSheet = lines.getRchrInspectionsheet();
		    RCHRQualitystandard standard = inspSheet.getRchrQualitystandard();
		    String id = lines.getRchrInspectionsheet().getId();
		    
		    //long deduction = lines.getA()+lines.getAone()+lines.getB()+lines.getSl();
		      
		    OBCriteria<RCHR_Inspcategoryline> linelist = OBDal.getInstance().createCriteria(RCHR_Inspcategoryline.class);
		    linelist.add(Restrictions.eq(RCHR_Inspcategoryline.PROPERTY_RCHRINSPECTIONSHEET, inspSheet));
		    
		    BigDecimal sumA=new BigDecimal(0);
			BigDecimal sumB=new BigDecimal(0);
		    BigDecimal sumAone=new BigDecimal(0);
		    BigDecimal sumSl=new BigDecimal(0);
		       
			  for(RCHR_Inspcategoryline inspLines : linelist.list())
			  {
				  BigDecimal newA = inspLines.getA();
				  BigDecimal newB = inspLines.getB();
				  BigDecimal newAone = inspLines.getAone();
				  BigDecimal newSl = inspLines.getSl();
				  
				  sumA = sumA.add(newA);
				  sumB = sumB.add(newB);
				  sumAone = sumAone.add(newAone);
				  sumSl = sumSl.add(newSl);
				  
				  	  
			  }
			  inspSheet.setAtotal(sumA.subtract(lines.getA()));
			  inspSheet.setBtotal(sumB.subtract(lines.getB()));
			  inspSheet.setAonetotal(sumAone.subtract(lines.getAone()));
			  inspSheet.setSltotal(sumSl.subtract(lines.getSl()));
			 
		   
	 }
	}


