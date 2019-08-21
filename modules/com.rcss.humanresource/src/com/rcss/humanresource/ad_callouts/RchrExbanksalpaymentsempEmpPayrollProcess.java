package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.data.RchrAttendanceProod;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.ShiftUtil;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.production.data.RCPRShift;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;

public class RchrExbanksalpaymentsempEmpPayrollProcess  extends SimpleCallout {
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        // String strAttendanceId = info.getStringParameter("inprchrAttendanceId", null);
        String emppayrollprocessId = info.getStringParameter("inprcplEmppayrollprocessId", null);

        RCPL_EmpPayrollProcess rcplEmpPayrollProcess = OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, emppayrollprocessId);


        info.addResult("inpprocessingsal", rcplEmpPayrollProcess.getPendingamt());
        info.addResult("inprchrEmployeeId", rcplEmpPayrollProcess.getEmployee().getId());
        info.addResult("inpifsccode", rcplEmpPayrollProcess.getEmployee().getRchrEmpBankList().get(0).getIFSCCode());
        info.addResult("inpaccountname", rcplEmpPayrollProcess.getEmployee().getRchrEmpBankList().get(0).getAccountname());
	info.addResult("inpaccountno", rcplEmpPayrollProcess.getEmployee().getRchrEmpBankList().get(0).getAccountNo());
        info.addResult("inprchrBankmasterId", rcplEmpPayrollProcess.getEmployee().getRchrEmpBankList().get(0).getRchrBankmaster().getId());
        info.addResult("inpbankname", rcplEmpPayrollProcess.getEmployee().getRchrEmpBankList().get(0).getRchrBankmaster().getName());

    }

}
