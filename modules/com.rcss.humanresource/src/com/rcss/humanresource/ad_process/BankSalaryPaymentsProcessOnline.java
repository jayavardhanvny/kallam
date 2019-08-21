package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;

import java.math.BigDecimal;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;


/**
 *
 * @author Surya
 **/
public class BankSalaryPaymentsProcessOnline extends DalBaseProcess {

	@Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rchr_Banksalpayments_ID").toString();
        System.out.println("id.."+id);
		RchrBanksalpayments e1=OBDal.getInstance().get(RchrBanksalpayments.class,id);
        OBContext.setAdminMode();
        OBError err = new OBError();
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");

        try {
        	process(e1);
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
	
    private void process(RchrBanksalpayments e){
	     double val=0;
	     double slotno=0;	  	     
	     for(RchrBankSalPaymentsOnline line:e.getRchrBanksalpaymentsolList()){
	    	 if(line.getAlertStatus().equals("DR")){
		 	 	val=val+line.getProcessingsal().doubleValue();
		 	 	slotno=line.getSlotno().doubleValue();}
		 }	     
	     RchrBanksalpaymentsApp head=OBProvider.getInstance().get(RchrBanksalpaymentsApp.class);
	     head.setOrganization(e.getOrganization());
	     head.setDocumentNo(e.getDocumentNo());
	     head.setRchrAttdProcess(e.getRchrAttdProcess());
	     head.setTotalamount(new BigDecimal(val));
	     head.setSlotno(new BigDecimal(slotno));
	     head.setTransactiontype("PF");
	     //head.setPaidAmount(new BigDecimal(val));
	     head.setPaymentType("Online");	     
	     OBDal.getInstance().save(head);
		 OBDal.getInstance().flush();
		 
	 	 createLines(e,head);	 	   		 			
	} 	
	
	private void createLines(RchrBanksalpayments bank,RchrBanksalpaymentsApp head){
		
		for(RchrBankSalPaymentsOnline line:bank.getRchrBanksalpaymentsolList()){
		 if(line.getAlertStatus().equals("DR")){
	     line.setAlertStatus("PRCS");
		 RchrBanksalpaymentsappln appline=OBProvider.getInstance().get(RchrBanksalpaymentsappln.class);
		 appline.setOrganization(head.getOrganization());
		 appline.setRchrBanksalpaymentsapp(head);
		 appline.setSalalry(line.getProcessingsal());
		 appline.setAccountNo(line.getAccountNo());
		 appline.setIFSCCode(line.getIFSCCode());
		 appline.setRchrBankmaster(line.getRchrBankmaster());
		 appline.setAlertStatus("PRCS");
		 appline.setRcplEmppayrollprocess(line.getRcplEmppayrollprocess());
		 appline.setEmployee(line.getEmployee());
		 appline.setRchrBanksalpaymentsol(line);
	     OBDal.getInstance().save(appline);
		 OBDal.getInstance().flush();
		}
	   }
	} 		 
}
