 package com.rcss.humanresource.ad_events;

 import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RCHRPermission;
import com.rcss.humanresource.data.RCHR_Employeesettlement;
import com.rcss.humanresource.data.RCHR_Settlemntlines;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;

import javax.enterprise.event.Observes;

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




public class Employeesettleevent extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHR_Settlemntlines.ENTITY_NAME)};
    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
	public void onSave(@Observes EntityNewEvent event){
		if(!isValidEvent(event))
		{
			return;
		}
		final RCHR_Settlemntlines lines = (RCHR_Settlemntlines)event.getTargetInstance();
		RCHR_Employeesettlement setline = lines.getRchrEmployeesettlement();
		String id = lines.getRchrEmployeesettlement().getId();
		BigDecimal deduction = lines.getAmount();
		OBCriteria<RCHR_Settlemntlines> linelist = OBDal.getInstance().createCriteria(RCHR_Settlemntlines.class);
		linelist.add(Restrictions.eq(RCHR_Settlemntlines.PROPERTY_RCHREMPLOYEESETTLEMENT, setline));
		BigDecimal sum = deduction;
		for(RCHR_Settlemntlines forlinelist : linelist.list())
		{
			BigDecimal newdeduction = forlinelist.getAmount();
			
			sum = sum.add(newdeduction);
		}		
		setline.setTotaldeduction(sum);
	}	
	public void onUpdate(@Observes EntityUpdateEvent event) {
		if(!isValidEvent(event))
		{
			return;
		}
		final RCHR_Settlemntlines lines = (RCHR_Settlemntlines)event.getTargetInstance();
		RCHR_Employeesettlement setline = lines.getRchrEmployeesettlement();
		String id = lines.getRchrEmployeesettlement().getId();
		BigDecimal deduction = lines.getAmount();
		OBCriteria<RCHR_Settlemntlines> linelist = OBDal.getInstance().createCriteria(RCHR_Settlemntlines.class);
		linelist.add(Restrictions.eq(RCHR_Settlemntlines.PROPERTY_RCHREMPLOYEESETTLEMENT, setline));
		BigDecimal sum = new BigDecimal(0);
		for(RCHR_Settlemntlines forlinelist : linelist.list())
		{
			BigDecimal newdeduction = forlinelist.getAmount();
			sum = sum.add(newdeduction);
		}
		setline.setTotaldeduction(sum);
	}
	 public void onDelete(@Observes EntityDeleteEvent event) {
		    if (!isValidEvent(event)) {
		      return;
		    }
		    final RCHR_Settlemntlines lines = (RCHR_Settlemntlines)event.getTargetInstance();
		    RCHR_Employeesettlement setline = lines.getRchrEmployeesettlement();
		    String id = lines.getRchrEmployeesettlement().getId();
		    BigDecimal deduction = lines.getAmount();
		    OBCriteria<RCHR_Settlemntlines> linelist = OBDal.getInstance().createCriteria(RCHR_Settlemntlines.class);
		    linelist.add(Restrictions.eq(RCHR_Settlemntlines.PROPERTY_RCHREMPLOYEESETTLEMENT, setline));
		    BigDecimal nt = new BigDecimal(-1);
		    BigDecimal sum = (deduction.multiply(nt));
		    for(RCHR_Settlemntlines forlinelist : linelist.list())
		    {
		    	BigDecimal newdeduction = forlinelist.getAmount();
		    	sum = sum.add(newdeduction);
		    }
		    setline.setTotaldeduction(sum);
	 }
}