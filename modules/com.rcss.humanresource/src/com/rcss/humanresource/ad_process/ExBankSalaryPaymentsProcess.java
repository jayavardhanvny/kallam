package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.RchrConstantType;
import org.apache.log4j.Logger;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.math.BigDecimal;
/**
 *
 * @author Vinay
 **/
/*
ad_window = Excluded Bank Salaries
ad_table = Rchr_Exbanksalpayments
processName = ExBankSalaryPaymentsProcess
filedName = Complete Process
columnName = Process
 */
public class ExBankSalaryPaymentsProcess extends DalBaseProcess implements BundleProcess {
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
        String id = bundle.getParams().get("Rchr_Exbanksalpayments_ID").toString();
        RchrExbanksalpayments e1= OBDal.getInstance().get(RchrExbanksalpayments.class,id);
        payCash(e1);
        e1.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_CO);
    }

    private void payCash(RchrExbanksalpayments rchrExbanksalpayments){
        double val=0;
        double slotno=0;
        RchrBankmaster mas=null;




        for(RchrExbanksalpaymentsemp rchrExbanksalpaymentsemp : rchrExbanksalpayments.getRchrExbanksalpaymentsempList()){
            if(rchrExbanksalpaymentsemp.getAlertStatus().equals(RchrConstantType.DOCUMENT_STATUS_DR)){
                val=val+rchrExbanksalpaymentsemp.getPaidAmount().doubleValue();
                slotno=rchrExbanksalpaymentsemp.getSlotno().doubleValue();
                mas=rchrExbanksalpaymentsemp.getRchrBankmaster();
            }
        }
        RchrBanksalpaymentsApp rchrBanksalpaymentsApp= OBProvider.getInstance().get(RchrBanksalpaymentsApp.class);
        rchrBanksalpaymentsApp.setOrganization(rchrExbanksalpayments.getOrganization());
        rchrBanksalpaymentsApp.setDocumentNo(rchrExbanksalpayments.getDocumentNo());
        rchrBanksalpaymentsApp.setSlotno(new BigDecimal(slotno));
        rchrBanksalpaymentsApp.setRchrBankmaster(mas);
        rchrBanksalpaymentsApp.setRchrAttdProcess(rchrExbanksalpayments.getRchrAttdProcess());
        rchrBanksalpaymentsApp.setTransactiontype("NPF");
        rchrBanksalpaymentsApp.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_DR);
        rchrBanksalpaymentsApp.setTotalamount(new BigDecimal(val));
        rchrBanksalpaymentsApp.setPaymentType("Cheque");
        OBDal.getInstance().save(rchrBanksalpaymentsApp);
        OBDal.getInstance().flush();

        createLines(rchrExbanksalpayments, rchrBanksalpaymentsApp);
    }

    private void createLines(RchrExbanksalpayments rchrExbanksalpayments, RchrBanksalpaymentsApp head){
        logger.info("NO of Employees "+rchrExbanksalpayments.getRchrExbanksalpaymentsempList());
        for(RchrExbanksalpaymentsemp rchrExbanksalpaymentsemp : rchrExbanksalpayments.getRchrExbanksalpaymentsempList()){
            if(rchrExbanksalpaymentsemp.getAlertStatus().equals(RchrConstantType.DOCUMENT_STATUS_DR)){
                rchrExbanksalpaymentsemp.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_PROCESSED);
                RchrBanksalpaymentsappln rchrBanksalpaymentsappln=OBProvider.getInstance().get(RchrBanksalpaymentsappln.class);
                rchrBanksalpaymentsappln.setOrganization(head.getOrganization());
                rchrBanksalpaymentsappln.setRchrBanksalpaymentsapp(head);
                rchrBanksalpaymentsappln.setAccountNo(rchrExbanksalpaymentsemp.getAccountNo());
                rchrBanksalpaymentsappln.setIFSCCode(rchrExbanksalpaymentsemp.getIFSCCode());
                rchrBanksalpaymentsappln.setRchrBankmaster(rchrExbanksalpaymentsemp.getRchrBankmaster());
                rchrBanksalpaymentsappln.setSalalry(rchrExbanksalpaymentsemp.getPaidAmount());
                rchrBanksalpaymentsappln.setRcplEmppayrollprocess(rchrExbanksalpaymentsemp.getRcplEmppayrollprocess());
                rchrBanksalpaymentsappln.setEmployee(rchrExbanksalpaymentsemp.getEmployee());
                rchrBanksalpaymentsappln.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_PROCESSED);
                rchrBanksalpaymentsappln.setDocumentNo(rchrExbanksalpayments.getDocumentNo());
                rchrBanksalpaymentsappln.setRchrExbanksalpaymentsemp(rchrExbanksalpaymentsemp);
                //appline.setRchrBanksalpaymentsln(rchrExbanksalpaymentsemp);
                OBDal.getInstance().save(rchrBanksalpaymentsappln);
                OBDal.getInstance().flush();
                updateStatus(rchrExbanksalpaymentsemp);
            }
        }
    }
    private void updateStatus(RchrExbanksalpaymentsemp rchrExbanksalpaymentsemp){
        for (RchrExbanksalpaymentsempLn rchrExbanksalpaymentsempLn : rchrExbanksalpaymentsemp.getRchrExbanksalpaymentsemplnList()){
            rchrExbanksalpaymentsempLn.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_DR);
        }
    }
}
