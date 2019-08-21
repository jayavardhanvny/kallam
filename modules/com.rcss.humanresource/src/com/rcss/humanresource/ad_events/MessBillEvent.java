package com.rcss.humanresource.ad_events;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.data.RCPLMessBill;
import com.redcarpet.payroll.data.RCPLMessType;

import javax.enterprise.event.Observes;
import javax.sql.rowset.serial.SerialException;

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

public class MessBillEvent extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCPLMessBill.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }
    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }                     
        RCPLMessBill messbill = (RCPLMessBill) event.getTargetInstance(); 
        RCPLMessType messtype = OBDal.getInstance().get(RCPLMessType.class, messbill.getRcplMesstype().getId());

        if((messbill.getBreakfast()) > ((getDaysDifference(messbill.getFromDate(), messbill.getToDate()))*(messtype.getBreakfastcost().intValue()))){
        	throwsError();
        }        
        if((messbill.getLunch()) > ((getDaysDifference(messbill.getFromDate(), messbill.getToDate()))*(messtype.getLunchcost().intValue()))){
        	throwsNewError();
        }        
        if((messbill.getDinner()) > ((getDaysDifference(messbill.getFromDate(), messbill.getToDate()))*(messtype.getDinnercost().intValue()))){
        	throwsNewErrors();
        }
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPLMessBill messbill = (RCPLMessBill) event.getTargetInstance(); 
        RCPLMessType messtype = OBDal.getInstance().get(RCPLMessType.class, messbill.getRcplMesstype().getId());

        if((messbill.getBreakfast()) > ((getDaysDifference(messbill.getFromDate(), messbill.getToDate()))*(messtype.getBreakfastcost().intValue()))){
        	throwsError();
        }        
        if((messbill.getLunch()) > ((getDaysDifference(messbill.getFromDate(), messbill.getToDate()))*(messtype.getLunchcost().intValue()))){
        	throwsNewError();
        }        
        if((messbill.getDinner()) > ((getDaysDifference(messbill.getFromDate(), messbill.getToDate()))*(messtype.getDinnercost().intValue()))){
        	throwsNewErrors();
        } 
    }  
    
    
    private void throwsError() throws OBException {
       // String language = OBContext.getOBContext().getLanguage().getLanguage();
       // ConnectionProvider conn = new DalConnectionProvider(false);
       // throw new OBException(Utility.messageBD(conn, " Breakfast Amount is Over Then Maximum Amount", language));
    }
    private void throwsNewError() throws OBException {
         String language = OBContext.getOBContext().getLanguage().getLanguage();
         ConnectionProvider conn = new DalConnectionProvider(false);
         throw new OBException(Utility.messageBD(conn, "Lunch Amount is Over Then Maximum Amount", language));
     }
    private void throwsNewErrors() throws OBException {
         String language = OBContext.getOBContext().getLanguage().getLanguage();
         ConnectionProvider conn = new DalConnectionProvider(false);
         throw new OBException(Utility.messageBD(conn, "Dinner Amount is Over Then Maximum Amount", language));
     }
    public long getDaysDifference(Date start, Date end){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(start);
//    	System.out.println(calendar.getTime());
    	long miliSecondForDate1 = calendar.getTimeInMillis();
    	calendar.setTime(end);
//    	System.out.println(calendar.getTime());
    	calendar.add(Calendar.DATE, 1);
//    	System.out.println(calendar.getTime());
    	long miliSecondForDate2 = calendar.getTimeInMillis();
//    	System.out.println(((miliSecondForDate2-miliSecondForDate1) / (24 * 60 * 60 * 1000)));
    	return  ((miliSecondForDate2-miliSecondForDate1) / (24 * 60 * 60 * 1000));
    }
    
/*    public static int getDaysDifference(Date start, Date end) {
         if (start == null || end == null) {
             return 0;
         }
         return Days.daysBetween(new DateTime(start), new DateTime(end)).getDays()+1;
     }
*/	
/*    public static int getDaysDifference(Date start, Date end) {
        if (start == null || end == null) {
            return 0;
        }
        System.out.println(getDaysDifference(start, end)+1);
          return getDaysDifference(start, end)+1;
//        return Days.daysBetween(new DateTime(start), new DateTime(end)).getDays()+1;
    }
*/
    }

