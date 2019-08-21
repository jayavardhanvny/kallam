package com.rcss.humanresource.ad_process;


import com.rcss.humanresource.data.RchrBanksalpaymentsappln;
import com.rcss.humanresource.data.RchrExbanksalpaymentsempLn;
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
ad_tab = name of the Tab
ad_table = Rchr_Banksalpaymentsappln
processName = BankSalaryPaymentsReject
filedName = Reject
columnName = Process
 */
public class BankSalaryPaymentsReject extends DalBaseProcess {

	@Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rchr_Banksalpaymentsappln_ID").toString();
        System.out.println("id.."+id);
		RchrBanksalpaymentsappln e1=OBDal.getInstance().get(RchrBanksalpaymentsappln.class,id);
        OBContext.setAdminMode();
        OBError err = new OBError();
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");

        try {
        	reject(e1);
        	OBDal.getInstance().commitAndClose();
        }	        	
        catch (Exception e) {
            err.setMessage(e.getMessage());
            err.setTitle("Error");
            err.setType("Error");
            bundle.setResult(err);
            e.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }

        OBContext.restorePreviousMode();
        bundle.setResult(err);
    }
	
    private void reject(RchrBanksalpaymentsappln e){
	     if(e.getRchrBanksalpaymentsapp().getPaymentType().equals("Online")&&e.getRchrBanksalpaymentsapp().getTransactiontype().equals("PF")){
	    	 e.getRcplEmppayrollprocess().setPendingamt(e.getRcplEmppayrollprocess().getPendingamt().add(e.getSalalry()));
		     e.setAlertStatus("NA");
		     e.getRchrBanksalpaymentsapp().setRejectamt(e.getRchrBanksalpaymentsapp().getRejectamt().add(e.getSalalry()));
		     e.getRchrBanksalpaymentsapp().setPaidAmount(e.getRchrBanksalpaymentsapp().getPaidAmount().subtract(e.getSalalry()));
		     e.getRchrBanksalpaymentsol().setAlertStatus("NA");
	     }	     
	     else if(e.getRchrBanksalpaymentsapp().getPaymentType().equals("Cheque") && e.getRchrBanksalpaymentsapp().getTransactiontype().equals("PF")){
	    	 e.getRcplEmppayrollprocess().setPendingamt(e.getRcplEmppayrollprocess().getPendingamt().add(e.getSalalry()));
		     e.setAlertStatus("NA");
		     e.getRchrBanksalpaymentsapp().setRejectamt(e.getRchrBanksalpaymentsapp().getRejectamt().add(e.getSalalry()));
		     e.getRchrBanksalpaymentsapp().setPaidAmount(e.getRchrBanksalpaymentsapp().getPaidAmount().subtract(e.getSalalry()));
		     e.getRchrBanksalpaymentsln().setAlertStatus("NA");
	     }
	     else{
	    	 if(e.getRchrExbanksalpaymentsemp().getPaidAmount().doubleValue()>0)
	    	 e.getRchrExbanksalpaymentsemp().getRcplEmppayrollprocess().setPendingamt(e.getRchrExbanksalpaymentsemp().getRcplEmppayrollprocess().getPendingamt().add(e.getRchrExbanksalpaymentsemp().getProcessingsal()));
	    	 e.getRchrExbanksalpaymentsemp().setAlertStatus("NA");
	    	 e.getRchrBanksalpaymentsapp().setRejectamt(e.getRchrBanksalpaymentsapp().getRejectamt().add(e.getSalalry()));
		     e.getRchrBanksalpaymentsapp().setPaidAmount(e.getRchrBanksalpaymentsapp().getPaidAmount().subtract(e.getSalalry()));
	    	 for(RchrExbanksalpaymentsempLn eline:e.getRchrExbanksalpaymentsemp().getRchrExbanksalpaymentsemplnList()){
	    		 	eline.setAlertStatus("NA");
		    		eline.getRcplEmppayrollprocess().setPendingamt(eline.getRcplEmppayrollprocess().getPendingamt().add(eline.getPaidAmount()));		    		 
		     }
	    	 /*for(RCHRExbankSalpaymentsAgent agentLine:e.getRchrExbanksalpaymentsemp().getRCHRExbankSalpaymentsAgentList()){
	    		 agentLine.setAlertStatus("Reject");
	    		 agentLine.getRcplAgentmastercal().setPendingamt(agentLine.getRcplAgentmastercal().getPendingamt().add(agentLine.getPaidAmount()));
	    	 }*/
	    	e.setAlertStatus("NA");
	     }
	} 	
		
}
