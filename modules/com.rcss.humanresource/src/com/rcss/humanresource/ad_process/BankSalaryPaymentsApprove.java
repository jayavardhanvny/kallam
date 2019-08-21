package com.rcss.humanresource.ad_process;


import com.rcss.humanresource.data.RchrBanksalpaymentsApp;

import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author Surya
 **/
/*
ad_window = Bank Payment Approval
ad_tab = Tab Name
ad_table = Rchr_Banksalpaymentsapp
processName = BankSalaryPaymentsApprove
filedName = Approve
columnName = Process
 */
public class BankSalaryPaymentsApprove extends DalBaseProcess {

	@Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rchr_Banksalpaymentsapp_ID").toString();
        System.out.println("id.."+id);
        RchrBanksalpaymentsApp e1=OBDal.getInstance().get(RchrBanksalpaymentsApp.class,id);
        OBContext.setAdminMode();
        OBError err = new OBError();
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");

        try {
            approve(e1);
            e1.setProcess(Boolean.TRUE);
        }
        catch (Exception e) {
            err.setMessage(e.getMessage());
            err.setTitle("Error");
            err.setType("Error");
            bundle.setResult(err);
            e.printStackTrace();		
        }

        OBContext.restorePreviousMode();
        bundle.setResult(err);
    }
	
    private void approve(RchrBanksalpaymentsApp e){
	     e.setAlertStatus("AP");
	     e.setProcess(Boolean.TRUE);
	} 	
	
	 
}
