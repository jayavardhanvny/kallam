package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;

import java.math.BigDecimal;

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
ad_table = Rchr_Banksalpaymentsapp
processName = BankSalaryPaymentsApprove
filedName = Paid
columnName = Paid
 */
public class BankSalaryPaymentsPaid extends DalBaseProcess {

	@Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rchr_Banksalpaymentsapp_ID").toString();
       // HttpBaseServlet ses=new HttpBaseServlet();
		RchrBanksalpaymentsApp e1=OBDal.getInstance().get(RchrBanksalpaymentsApp.class,id);
        OBContext.setAdminMode();
       
        OBError err = new OBError();
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");
       // Connection conn = null;
               	          	
        try {
        	//conn = ses.getTransactionConnection();  
        	if(e1.getPaymentType().equals("Cheque")&&(e1.getChequeno()==null||e1.getChequeno().equals(""))){
        		err.setMessage("Cheque No Mandatory for Cheque Paid");
                err.setTitle("Error");
                err.setType("Error");
                bundle.setResult(err);
        	}
        	else if(e1.getPaymentType().equals("Online")&&(e1.getBatchno()==null||e1.getBatchno().equals(""))){
        		err.setMessage("Batch No Mandatory for Online Paid");
                err.setTitle("Error");
                err.setType("Error");
                bundle.setResult(err);
        	}else{
        		pay(e1);
        		//ses.releaseCommitConnection(conn);
        	}
        	
        }	        	
        catch (Exception e) {
        	//ses.releaseRollbackConnection(conn);
            err.setMessage(e.getMessage());
            err.setTitle("Error");
            err.setType("Error");
            bundle.setResult(err);
            e.printStackTrace();		
        }
        OBContext.restorePreviousMode();
        bundle.setResult(err);
    }
	
    private void pay(RchrBanksalpaymentsApp e){
    	 double totalPaidamount=0;
	     for(RchrBanksalpaymentsappln ln:e.getRchrBanksalpaymentsapplnList()){
	    	 if(e.getPaymentType().equals("Online")&&e.getTransactiontype().equals("PF")){
	    		 ln.getRchrBanksalpaymentsol().getRcplEmppayrollprocess().setPendingamt(ln.getRchrBanksalpaymentsol().getRcplEmppayrollprocess().getPendingamt().subtract(ln.getSalalry()));
	    		 ln.getRchrBanksalpaymentsol().setPaycash(Boolean.TRUE);
	    		 ln.getRchrBanksalpaymentsol().setAlertStatus("Paid");
	    	 }else if(e.getPaymentType().equals("Cheque")&&e.getTransactiontype().equals("PF")){
	    		 ln.getRchrBanksalpaymentsln().getRcplEmppayrollprocess().setPendingamt(ln.getRchrBanksalpaymentsln().getRcplEmppayrollprocess().getPendingamt().subtract(ln.getSalalry()));
	    		 ln.getRchrBanksalpaymentsln().setPaycash(Boolean.TRUE);
	    		 ln.getRchrBanksalpaymentsln().setAlertStatus("Paid");	    				    	 
	    	 }
	    	 else{
	    		 ln.getRchrExbanksalpaymentsemp().setPaycash(Boolean.TRUE);
		    	 ln.getRchrExbanksalpaymentsemp().setAlertStatus("Paid");
		    	 if(ln.getRchrExbanksalpaymentsemp().getProcessingsal().doubleValue()>0)
		    	 ln.getRchrExbanksalpaymentsemp().getRcplEmppayrollprocess().setPendingamt(ln.getRchrExbanksalpaymentsemp().getRcplEmppayrollprocess().getPendingamt().subtract(ln.getRchrExbanksalpaymentsemp().getProcessingsal()));
		    	 for(RchrExbanksalpaymentsempLn eline:ln.getRchrExbanksalpaymentsemp().getRchrExbanksalpaymentsemplnList()){
		    		eline.setAlertStatus("Paid");
		    		eline.getRcplEmppayrollprocess().setPendingamt(eline.getRcplEmppayrollprocess().getPendingamt().subtract(eline.getPaidAmount()));		    		 
		    	 }
		    	 /*for(RCHRExbankSalpaymentsAgent ag:ln.getRchrExbanksalpaymentsemp().getRCHRExbankSalpaymentsAgentList()){
		    		 ag.setAlertStatus("Paid");
		    		 ag.getRcplAgentmastercal().setPendingamt(ag.getRcplAgentmastercal().getPendingamt().subtract(ag.getPaidAmount()));
		    	 }*/
	    	 }
	    	 totalPaidamount=totalPaidamount+ln.getSalalry().doubleValue();
	    	 ln.setAlertStatus("Paid");
	     }
	     e.setPaidAmount(new BigDecimal(totalPaidamount));
	     e.setPaid(Boolean.TRUE);
	     e.setAlertStatus("Paid");
	} 	
	
	 
}
