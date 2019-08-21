package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RCHRGasAdvancelines;
import com.rcss.humanresource.data.RchrAttdProcess;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_Otherdeductions;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollProcessUpdation;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RchrNegativePayrollInsertion implements PayrollProcessUpdation {
    @Override
    public void update(RCPL_PayrollProcess process) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(process.getEndingDate());
        calendar.add(Calendar.DATE,1);
        Date startingDate = calendar.getTime();
        OBCriteria<RchrAttdProcess> rchrAttdProcessOBCriteria = OBDal.getInstance().createCriteria(RchrAttdProcess.class);
        rchrAttdProcessOBCriteria.add(Restrictions.eq(RchrAttdProcess.PROPERTY_STARTINGDATE,startingDate));
        if(rchrAttdProcessOBCriteria.list().size()==1){
        for(RCPL_EmpPayrollProcess empPayrollProcess : process.getRCPLEmpPayrollProcessList()){
            if(empPayrollProcess.getTotalPay().intValue()<0){
                RCPL_Otherdeductions rcplOtherdeductions = OBProvider.getInstance().get(RCPL_Otherdeductions.class);
		rcplOtherdeductions.setAmount(empPayrollProcess.getTotalPay().abs());
                rcplOtherdeductions.setEmployee(empPayrollProcess.getEmployee());
                rcplOtherdeductions.setEntrydate(new Date());
                rcplOtherdeductions.setOrganization(empPayrollProcess.getOrganization());
                rcplOtherdeductions.setReasonForLeave(rchrAttdProcessOBCriteria.list().get(0).getMonthname()+" Negative Salary Recovery");
                rcplOtherdeductions.setPayrollPeriod(rchrAttdProcessOBCriteria.list().get(0));
                OBDal.getInstance().save(rcplOtherdeductions);
            }
        }
        }
    }
}
