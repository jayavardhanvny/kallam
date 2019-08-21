package com.redcarpet.payroll.ad_events;

import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_EmpDedIncentives;
import com.redcarpet.payroll.data.RCPL_EmpPayIncentives;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.enterprise.event.Observes;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBDal;

public class RCPL_NetSalAutoCalculate4 extends EntityPersistenceEventObserver {

    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCPL_EmpDedIncentives.ENTITY_NAME)};

    @Override
    protected Entity[] getObservedEntities() {
        return entities;
    }

    public void onUpdate(@Observes EntityUpdateEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPL_EmpDedIncentives empPay = (RCPL_EmpDedIncentives) event.getTargetInstance();
        BigDecimal add = getSumIncentiveDeductions(empPay.getEmployeePayrollProcess());
        if (empPay.getEmployeePayrollProcess().getAdditions().doubleValue() > 0) {
            empPay.getEmployeePayrollProcess().setIncentiveDeductions(add);
        }
        empPay.getEmployeePayrollProcess().setProcessNow(Boolean.FALSE);
        updateTotalPay(empPay.getEmployeePayrollProcess());
    }

    public void onSave(@Observes EntityNewEvent event) {
        if (!isValidEvent(event)) {
            return;
        }
        RCPL_EmpDedIncentives empPay = (RCPL_EmpDedIncentives) event.getTargetInstance();
        BigDecimal iDeductionsOld = empPay.getEmployeePayrollProcess().getIncentiveDeductions();
        if (empPay.getEmployeePayrollProcess().getAdditions().doubleValue() > 0) {
            empPay.getEmployeePayrollProcess().setIncentiveDeductions(iDeductionsOld.add(empPay.getDeductions()));
        }
        updateTotalPay(empPay.getEmployeePayrollProcess());
    }

    private BigDecimal getSumIncentiveDeductions(RCPL_EmpPayrollProcess empPay) {
        double retVal = 0;
        for (RCPL_EmpPayIncentives pay : empPay.getRCPLEmpPayIncentivesList()) {
            if (pay.getIncentives().isDeductions()) {
                retVal += pay.getAdditions().doubleValue();
            }

        }
        return new BigDecimal(retVal);
    }

    private void updateTotalPay(RCPL_EmpPayrollProcess empPay1) {
        BigDecimal total = empPay1.getAdditions().add(empPay1.getDeductions()).add(empPay1.getIncentiveAdditions()).add(empPay1.getIncentiveDeductions());
   //     BigDecimal lateAmount = getLateAmount(empPay1.getEmployee(), empPay1.getStartingDate(), empPay1.getEndingDate());
   //     empPay1.setLateDeduction(lateAmount);
        BigDecimal tempTotalPay = total;
        empPay1.setTotalPay(tempTotalPay);
        empPay1.setPendingamt(tempTotalPay);
        empPay1.setProcess(Boolean.TRUE);
        empPay1.setProcessed(Boolean.TRUE);
    }

    /*
     * @description Case 3:
     */
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