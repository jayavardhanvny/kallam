package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.data.RchrBanksalpaymentsappln;
import com.rcss.humanresource.data.RchrBanksalpaymentsApp;
import java.math.BigDecimal;

import com.rcss.humanresource.util.BundleProcess;
import org.apache.log4j.Logger;
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
/*
ad_window = Bank Salaries
ad_table = Rchr_Banksalpayments
processName = BankSalaryPaymentsProcess
filedName = Process
columnName = Copydata
 */
public class BankSalaryPaymentsProcess extends DalBaseProcess implements BundleProcess{
	private final static Logger logger = Logger.getLogger(BankSalaryPaymentsProcess.class);
	@Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        //System.out.println("id.."+id);
        OBContext.setAdminMode();
        OBError err = new OBError();
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");
        try {
        	doIt(bundle);
        }	        	
        catch (Exception e) {
            err.setMessage(e.getMessage());
            err.setTitle("Error");
            err.setType("Error");
            //bundle.setResult(err);
            e.printStackTrace();
            OBContext.restorePreviousMode();
        }
        bundle.setResult(err);
    }

	@Override
	public void doIt(ProcessBundle bundle) throws Exception {
		String id = bundle.getParams().get("Rchr_Banksalpayments_ID").toString();
		RchrBanksalpayments e1=OBDal.getInstance().get(RchrBanksalpayments.class,id);
		payCash(e1);
	}

	private void payCash(RchrBanksalpayments e){
	     double val=0;
	     double slotno=0;
		RchrBankmaster mas=null;
	     for(RchrBanksalpaymentsLn line:e.getRchrBanksalpaymentslnList()){
			 if(line.getAlertStatus().equals("DR")){
		 	 	val=val+line.getProcessingsal().doubleValue();
		 	 	slotno=line.getSlotno().doubleValue();
		 	 	mas=line.getRchrBankmaster();
	    	 }
		 }
	     RchrBanksalpaymentsApp head=OBProvider.getInstance().get(RchrBanksalpaymentsApp.class);
	     head.setOrganization(e.getOrganization());
	     head.setDocumentNo(e.getDocumentNo());
	     head.setSlotno(new BigDecimal(slotno));
	     head.setRchrBankmaster(mas);
	     head.setRchrAttdProcess(e.getRchrAttdProcess());
	     head.setTransactiontype("PF");
		head.setAlertStatus("DR");	
	     head.setTotalamount(new BigDecimal(val));
	     head.setPaymentType("Cheque");	     
	     OBDal.getInstance().save(head);
		 OBDal.getInstance().flush();
	 	 createLines(e,head);	 	   		 			
	} 	
	
	private void createLines(RchrBanksalpayments bank,RchrBanksalpaymentsApp head){
		logger.info("NO of Employees "+bank.getRchrBanksalpaymentslnList());
		for(RchrBanksalpaymentsLn line:bank.getRchrBanksalpaymentslnList()){
		if(line.getAlertStatus().equals("DR")){
		 line.setAlertStatus("PRCS");
			RchrBanksalpaymentsappln appline=OBProvider.getInstance().get(RchrBanksalpaymentsappln.class);
		 appline.setOrganization(head.getOrganization());
		 appline.setRchrBanksalpaymentsapp(head);
		 appline.setAccountNo(line.getAccountNo());		
		 appline.setIFSCCode(line.getIFSCCode());
		 appline.setRchrBankmaster(line.getRchrBankmaster());
		 appline.setSalalry(line.getProcessingsal());
		 appline.setRcplEmppayrollprocess(line.getRcplEmppayrollprocess());
		 appline.setEmployee(line.getEmployee());
		 appline.setAlertStatus("PRCS");
		 appline.setRchrBanksalpaymentsln(line);
	     OBDal.getInstance().save(appline);
		 OBDal.getInstance().flush();
		}
		}
	} 		 
}

