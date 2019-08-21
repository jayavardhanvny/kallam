package com.redcarpet.payroll.ad_events;

import com.redcarpet.payroll.data.RCPL_EmpPayHead;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import java.math.BigDecimal;
import javax.enterprise.event.Observes;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityDeleteEvent;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;

public class RCPL_NetSalAutoCalculate1 extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCPL_EmpPayHead.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPL_EmpPayHead empPay = (RCPL_EmpPayHead) event.getTargetInstance();
        BigDecimal add = getSumAdditions(empPay.getEmployeePayrollProcess());
        empPay.getEmployeePayrollProcess().setAdditions(add);
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        
        RCPL_EmpPayHead empPay = (RCPL_EmpPayHead) event.getTargetInstance();
        BigDecimal empPayAdditionsOld = empPay.getEmployeePayrollProcess().getAdditions();
     
        BigDecimal addition = empPay.getAdditions();
      
        empPay.getEmployeePayrollProcess().setAdditions(empPayAdditionsOld.add(addition));
        empPay.getEmployeePayrollProcess().setTotalPay(empPay.getAdditions().add(empPay.getEmployeePayrollProcess().getDeductions()).add(empPay.getEmployeePayrollProcess().getIncentiveAdditions()).add(empPay.getEmployeePayrollProcess().getIncentiveDeductions()));
        empPay.getEmployeePayrollProcess().setPendingamt(empPay.getAdditions().add(empPay.getEmployeePayrollProcess().getDeductions()).add(empPay.getEmployeePayrollProcess().getIncentiveAdditions()).add(empPay.getEmployeePayrollProcess().getIncentiveDeductions()));
        if(empPay.getEmployeePayrollProcess().getIncentiveAdditions().doubleValue()==0)
        empPay.getEmployeePayrollProcess().setGrandTotalAmount(empPay.getEmployeePayrollProcess().getAdditions());
        else
        empPay.getEmployeePayrollProcess().setGrandTotalAmount(empPay.getEmployeePayrollProcess().getAdditions().add(empPay.getEmployeePayrollProcess().getIncentiveAdditions()));
        
    }

    public void onDelete(@Observes EntityDeleteEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
//        RCPL_EmpPayHead empPay = (RCPL_EmpPayHead) event.getTargetInstance();
//        BigDecimal empPayAdditionsOld = empPay.getEmployeePayrollProcess().getAdditions();
//        BigDecimal addition = empPay.getAdditions();
//        empPay.getEmployeePayrollProcess().setAdditions(empPayAdditionsOld.subtract(addition));
    }

    private BigDecimal getSumAdditions(RCPL_EmpPayrollProcess empPay) {
        double retVal = 0;
        System.out.println("event size.."+empPay.getRCPLEmpPayHeadList().size());
        for (RCPL_EmpPayHead pay : empPay.getRCPLEmpPayHeadList()) {
            retVal += pay.getAdditions().doubleValue();
            System.out.println("event value.."+pay.getAdditions().doubleValue());
        }
        return new BigDecimal(retVal);
    }
}