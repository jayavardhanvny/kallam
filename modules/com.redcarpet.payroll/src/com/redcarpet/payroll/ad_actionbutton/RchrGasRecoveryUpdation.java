package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RCHRGasAdvancelines;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollProcessUpdation;
import org.openbravo.dal.service.OBDal;

import java.math.BigDecimal;
import java.util.List;

public class RchrGasRecoveryUpdation implements PayrollProcessUpdation{
    @Override
    public void update(RCPL_PayrollProcess process) {
        for(RCPL_EmpPayrollProcess empPayrollProcess : process.getRCPLEmpPayrollProcessList()){
            PayrollDBSessionUtil sessionUtil = new PayrollDBSessionUtil(empPayrollProcess.getEmployee().getId(),process.getStartingDate(),process.getEndingDate());
            String hql = sessionUtil.getGasRecoveryHQL();
            List<RCHRGasAdvancelines> loanList = OBDal.getInstance().getSession().createQuery(hql).list();
            BigDecimal loanamts = new BigDecimal("0");
            BigDecimal totalAmounts = BigDecimal.ZERO;
            for (RCHRGasAdvancelines gasAdvancelines : loanList) {
                loanamts = gasAdvancelines.getLoanAmount();
                totalAmounts=totalAmounts.add(loanamts);
                gasAdvancelines.setPaid(true);
            }
        }
    }
}
