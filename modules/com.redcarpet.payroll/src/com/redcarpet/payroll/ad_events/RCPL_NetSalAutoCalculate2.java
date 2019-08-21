package com.redcarpet.payroll.ad_events;


import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_EmpPayDead;
import com.redcarpet.payroll.data.RCPL_EmpPayIncentives;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.production.data.RCPRShift;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import org.hibernate.criterion.Restrictions;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;


public class RCPL_NetSalAutoCalculate2 extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCPL_EmpPayDead.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPL_EmpPayDead empPay = (RCPL_EmpPayDead) event.getTargetInstance();
        BigDecimal add = getSumDeductions(empPay.getEmployeePayrollProcess());
        if(empPay.getEmployeePayrollProcess().getAdditions().doubleValue()>0){
            empPay.getEmployeePayrollProcess().setDeductions(add);
        }
    }
    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        
        RCPL_EmpPayDead empPay = (RCPL_EmpPayDead) event.getTargetInstance();
        BigDecimal empPayDeductionsOld = empPay.getEmployeePayrollProcess().getDeductions();
        BigDecimal deductions = empPay.getDeductions();
        if(!empPay.getPayDeductions().getCategory().equalsIgnoreCase(PayrollTypesConstants.LOSS_OF_PAY)){
        if(empPay.getEmployeePayrollProcess().getAdditions().doubleValue()>0){
            empPay.getEmployeePayrollProcess().setDeductions(empPayDeductionsOld.subtract(deductions));
            empPay.getEmployeePayrollProcess().setTotalPay(empPay.getEmployeePayrollProcess().getAdditions().add(empPay.getEmployeePayrollProcess().getDeductions()).add(empPay.getEmployeePayrollProcess().getIncentiveAdditions()).add(empPay.getEmployeePayrollProcess().getIncentiveDeductions()));
            empPay.getEmployeePayrollProcess().setPendingamt(empPay.getEmployeePayrollProcess().getAdditions().add(empPay.getEmployeePayrollProcess().getDeductions()).add(empPay.getEmployeePayrollProcess().getIncentiveAdditions()).add(empPay.getEmployeePayrollProcess().getIncentiveDeductions()));
        }
        
        OBCriteria<RCPL_EmpPayIncentives> rcplEmpPayIncentives = OBDal.getInstance().createCriteria(RCPL_EmpPayIncentives.class);
        rcplEmpPayIncentives.add(Restrictions.eq(RCPL_EmpPayIncentives.PROPERTY_EMPLOYEEPAYROLLPROCESS,empPay.getEmployeePayrollProcess()));
        if(rcplEmpPayIncentives.list().size()==0){
     	   updateTotalPay(empPay.getEmployeePayrollProcess());
        }
        }
    }
/*
    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        
        RCPL_EmpPayDead empPay = (RCPL_EmpPayDead) event.getTargetInstance();
        BigDecimal empPayDeductionsOld = empPay.getEmployeePayrollProcess().getDeductions();
        BigDecimal deductions = empPay.getDeductions();
        if(empPay.getEmployeePayrollProcess().getAdditions().doubleValue()>0){
            empPay.getEmployeePayrollProcess().setDeductions(empPayDeductionsOld.subtract(deductions));
            empPay.getEmployeePayrollProcess().setTotalPay(empPay.getEmployeePayrollProcess().getAdditions().add(empPay.getEmployeePayrollProcess().getDeductions()).add(empPay.getEmployeePayrollProcess().getIncentiveAdditions()).add(empPay.getEmployeePayrollProcess().getIncentiveDeductions()));
        }
    }*/

    private BigDecimal getSumDeductions(RCPL_EmpPayrollProcess empPay) {
        double retVal = 0;
        for (RCPL_EmpPayDead pay : empPay.getRCPLEmpPayDeadList()) {
        	
            retVal += pay.getDeductions().doubleValue();
            
        }
        return new BigDecimal(retVal);
    }
    
    private void updateTotalPay(RCPL_EmpPayrollProcess empPay1) {
        BigDecimal total = empPay1.getAdditions().add(empPay1.getDeductions()).add(empPay1.getIncentiveAdditions()).add(empPay1.getIncentiveDeductions());
//        BigDecimal lateAmount = getLateAmount(empPay1.getEmployee(), empPay1.getStartingDate(), empPay1.getEndingDate());
//        empPay1.setLateDeduction(lateAmount);
        BigDecimal tempTotalPay = total;
        empPay1.setTotalPay(tempTotalPay);
        empPay1.setPendingamt(tempTotalPay);
        empPay1.setProcess(Boolean.TRUE);
        empPay1.setProcessed(Boolean.TRUE);
    }
    
    public BigDecimal getLateAmount(RchrEmployee e, Date sdate, Date edate) {
        double sum = 0;
        if (e.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)) {
            List<RCPRShift> wsList = OBDal.getInstance().createCriteria(RCPRShift.class).list();
            double tempSum = 0;
            for (RCPRShift ws : wsList) {
                RCPL_LateAmountCalc calc = new RCPL_LateAmountCalc(e.getId(), ws.getId(), sdate, edate);
                tempSum += calc.getLateAmount();
            }
            sum = tempSum;
        } else {
            sum = 0;
        }
        return new BigDecimal(-1 * sum);
    }


}