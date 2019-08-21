package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RCHR_Emp_Loanlines;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.data.RcplSecuritydeposit;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollProcessUpdation;
import org.openbravo.dal.service.OBDal;

import java.math.BigDecimal;
import java.util.List;

public class RchrSecurityDepositUpdation implements PayrollProcessUpdation{
    @Override
    public void update(RCPL_PayrollProcess process) {
        for(RCPL_EmpPayrollProcess empPayrollProcess : process.getRCPLEmpPayrollProcessList()){
            PayrollDBSessionUtil sessionUtil = new PayrollDBSessionUtil(empPayrollProcess.getEmployee().getId(),process.getStartingDate(),process.getEndingDate());
            String hql = sessionUtil.getSecurityDepositHQL();
            List<RcplSecuritydeposit> depositList = OBDal.getInstance().getSession().createQuery(hql).list();
            BigDecimal loanamt = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            double presentdays = empPayrollProcess.getPresentdays().doubleValue();
            if(presentdays > 10 ){
                for (RcplSecuritydeposit security : depositList) {
                    //loanamt = security.getDepositamount();
                    security.setPaid(true);
                }
            }
        }
    }
}
