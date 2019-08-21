package com.redcarpet.payroll.ad_actionbutton;

import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.util.PayrollProcessUpdation;
import com.rcss.humanresource.util.HqlUtils;
import java.math.BigDecimal;
public class RchrEmployeeServiceDaysUpdation implements PayrollProcessUpdation {
    @Override
    public void update(RCPL_PayrollProcess process) {
        for(RCPL_EmpPayrollProcess empPayrollProcess : process.getRCPLEmpPayrollProcessList()){
            long serviceDays = empPayrollProcess.getEmployee().getPreattddays()+empPayrollProcess.getPresentdays().longValue();
            empPayrollProcess.getEmployee().setPreattddays(serviceDays);
            empPayrollProcess.getEmployee().setObservicedays(serviceDays);
	        empPayrollProcess.getEmployee().setActualservicedays(new BigDecimal(empPayrollProcess.getEmployee().getActualservicedays().longValue()+empPayrollProcess.getPresentdays().longValue()));
        }
    }
}
