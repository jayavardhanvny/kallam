package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.util.ProcessStatus;
import com.rcss.humanresource.util.RchrConstantType;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.util.PayrollProcessUpdation;
import org.apache.log4j.Logger;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.dal.core.OBContext;
import org.openbravo.erpCommon.utility.OBError;

import java.util.concurrent.TimeUnit;

public class RcplPayrollProcessConfirmation extends DalBaseProcess {
    public static final Logger logger = Logger.getLogger(RcplPayrollProcessConfirmation.class);
    @Override
    public void doExecute(ProcessBundle bundle) throws Exception {
        OBError err = new OBError();
        ProcessStatus processStatus = new ProcessStatus(bundle, DalBaseProcess.PROCESSING);
        if (processStatus.isProcessing()){
            logger.info("In Second Condtion Already this process is in Running state ");
            //throw new SerialException("Already this Process is Running...!");
            err.setMessage(ProcessStatus.PREVIOUS_PROCESS_RUNNING);
            err.setTitle("Error");
            err.setType("Error");
            //bundle.setResult(err);
            bundle.setResult(err);
        } else {
            Long starTime = System.currentTimeMillis();
            OBContext.setAdminMode();
            String strProcessId = bundle.getParams().get("Rcpl_Payrollprocess_ID").toString();
            //OBError err = new OBError();
            String errorMsg = "";
            try {
                errorMsg = doIt(strProcessId);
                err.setMessage("Process Completed Successfully");
                err.setTitle("Success");
                err.setType("Success");
                OBDal.getInstance().commitAndClose();
            } catch (Exception e) {
                err.setMessage(e.getMessage());
                err.setTitle("Error");
                err.setType("Error");
                // bundle.setResult(err);
                OBDal.getInstance().rollbackAndClose();
                e.printStackTrace();
            }finally {
                OBContext.restorePreviousMode();
            }
            bundle.setResult(err);
            //OBContext.restorePreviousMode();
            Long endTime = System.currentTimeMillis();
            //Long starTime = System.currentTimeMillis();
            logger.info("Total Time is "+ TimeUnit.MILLISECONDS.toSeconds(endTime-starTime));
        }
    }
    public String doIt(String strProcessId){
        RCPL_PayrollProcess process = OBDal.getInstance().get(RCPL_PayrollProcess.class,strProcessId);
        PayrollProcessUpdation updateLoans = new RchrPayrollLoansUpdation();
        PayrollProcessUpdation updateServiceDays = new RchrEmployeeServiceDaysUpdation();
        PayrollProcessUpdation updateSecurityDeposit = new RchrSecurityDepositUpdation();
        PayrollProcessUpdation updateGasRecovery = new RchrGasRecoveryUpdation();
        PayrollProcessUpdation updateAttendance = new RchrAttendanceUpdation();
        PayrollProcessUpdation insertNegativePayments = new RchrNegativePayrollInsertion();

        updateLoans.update(process);
        updateServiceDays.update(process);
        updateSecurityDeposit.update(process);
        updateGasRecovery.update(process);
        updateAttendance.update(process);
        insertNegativePayments.update(process);
        process.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_CO);
        process.setPosted(Boolean.TRUE);
    return "Success";
    }
}
