package com.redcarpet.payroll.ad_events;

import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_EmpPayHead;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
import java.math.BigDecimal;
import java.util.Date;
import javax.enterprise.event.Observes;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityDeleteEvent;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

public class EmployeeServiceDaysByDate extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCPL_PayrollProcess.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onDelete(@Observes EntityDeleteEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        /*Long finalval=new Long(0);
        RCPL_PayrollProcess payroll=(RCPL_PayrollProcess)event.getTargetInstance();
      
		OBCriteria<RCPL_EmpPayrollProcess> empProcess=OBDal.getInstance().createCriteria(RCPL_EmpPayrollProcess.class);
		empProcess.add(Restrictions.eq(RCPL_EmpPayrollProcess.PROPERTY_PAYROLLPROCESS,payroll));
		System.out.println("empsize..."+empProcess.list().size());
		for(RCPL_EmpPayrollProcess empProcess1 :empProcess.list()){
			String empId=empProcess1.getEmployee().getId();
			
			Double days=getPresentDays(payroll.getStartingDate(),payroll.getEndingDate(),empId);
			RchrEmployee emp=OBDal.getInstance().get(RchrEmployee.class,empId);
			//System.out.println("empid.."+empId);
			Long predays=days.longValue();
			System.out.println("days...................."+predays);
			Long pret=emp.getPreattddays();
			System.out.println("pretdays................."+pret);
			if(pret!=0){
			finalval=(pret-predays);}
			System.out.println("diffrence................"+finalval);
			emp.setPreattddays(finalval);
		}*/
    }
    
    public double getPresentDays(Date startDate,Date endDate,String empId){
    	 AttendanceUtil aUtil = new AttendanceUtil(startDate, endDate, empId);
    	 Double presentDays = aUtil.getNoOfDaysPresent();
         //Double no_of_days_weekoff_worked = aUtil.getNoOfWeekOffWorked();
         
    	return presentDays;
    }
    
}