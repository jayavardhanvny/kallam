package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RCHR_Emp_Loanlines;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollProcessUpdation;
import org.openbravo.dal.service.OBDal;

import java.math.BigDecimal;
import java.util.List;

public class RchrPayrollLoansUpdation implements PayrollProcessUpdation{
    @Override
    public void update(RCPL_PayrollProcess process) {
        for(RCPL_EmpPayrollProcess empPayrollProcess : process.getRCPLEmpPayrollProcessList()){
            PayrollDBSessionUtil sessionUtil = new PayrollDBSessionUtil(empPayrollProcess.getEmployee().getId(),process.getStartingDate(),process.getEndingDate());
            String hql = sessionUtil.getLoansAndAdvanceHQL();
            List<RCHR_Emp_Loanlines> loanList = OBDal.getInstance().getSession().createQuery(hql).list();
            BigDecimal loanamt = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (RCHR_Emp_Loanlines loans : loanList) {
                loanamt = loans.getLoanAmount();
                totalAmount=totalAmount.add(loanamt);
                loans.setPaid(true);
                loans.setPaidAmount(loanamt);
            }
        }
    }
}
