/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.rcssob.ad_process;

import java.math.BigDecimal;
import javax.servlet.ServletException;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.erpCommon.utility.OBMessageUtils;
/**
 *
 * @author S.A. Mateen
 * Edited By Suneetha.
 */
public class RCOB_Process_AgentCommission extends DalBaseProcess {


    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	 
        OBContext.setAdminMode();
        OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Success");
        msg.setMessage("Process completed successfully");
        try {
            String strInvoiceId = bundle.getParams().get("C_Invoice_ID").toString();
            Invoice invoice = OBDal.getInstance().get(Invoice.class, strInvoiceId);
            String strBPartnerId = invoice.getBusinessPartner().getId();
            
            BusinessPartner bpartner = OBDal.getInstance().get(BusinessPartner.class, strBPartnerId);
            String caltype = bpartner.getRcobComcalon();
            
            if(caltype==null)
            {
            	throw new OBException("Please Select The Commission Calculation Type In Agent");
            	
            }
            RCOBAgentCommissionData[] data = RCOBAgentCommissionData.selectData(new DalConnectionProvider(), strBPartnerId);
            int len=data.length;
            System.out.println("data length is" +data.length);
            if(len==0)
            {
             
            	 String language = OBContext.getOBContext().getLanguage().getLanguage();
                 ConnectionProvider conn = new DalConnectionProvider(false);
                 throw new OBException(Utility.messageBD(conn, "RCOB_AgentInformation", language));	

            }
            
            for (RCOBAgentCommissionData line : data) {
                String strAgentInvoiceId = line.cInvoiceId;
                if(caltype.equals("ExMill Rate"))
                {
                insertAccountingLine(strInvoiceId, new BigDecimal(line.finnetamount), getAgentServiceAccount(), "76E0750020D541899B14027BE71E8124", strAgentInvoiceId);
                }
                else if(caltype.equals("NetUnit Price"))
                {
                insertAccountingLine(strInvoiceId, new BigDecimal(line.finnetamountnew), getAgentServiceAccount(), "76E0750020D541899B14027BE71E8124", strAgentInvoiceId);
                }
               // OBDal.getInstance().get(Invoice.class, strAgentInvoiceId).setRCOBIsinvoicecreated(Boolean.TRUE);
               // invoice.setRCOBIsinvoicecreated(Boolean.TRUE);
               // invoice.setRCOBCalculatecommission(Boolean.TRUE);
            }
            OBDal.getInstance().commitAndClose();
        } catch (Exception ex) {
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());
        }
        bundle.setResult(msg);
        OBContext.restorePreviousMode();
    }

    private void insertAccountingLine(String strInvoiceId, BigDecimal unitPrice, String strAccountId, String strTaxRateId, String referenceInvoiceId) throws ServletException {
        InvoiceLine invoiceLine = OBProvider.getInstance().get(InvoiceLine.class);
        invoiceLine.setInvoice(OBDal.getInstance().get(Invoice.class, strInvoiceId));
        invoiceLine.setLineNo(getLineNo(strInvoiceId));
        invoiceLine.setUnitPrice(unitPrice);
        invoiceLine.setLineNetAmount(unitPrice);
        invoiceLine.setFinancialInvoiceLine(Boolean.TRUE);
        invoiceLine.setAccount(OBDal.getInstance().get(GLItem.class, strAccountId));
        invoiceLine.setTax(OBDal.getInstance().get(TaxRate.class, strTaxRateId));
        invoiceLine.setRCOBReferenceinvoice(OBDal.getInstance().get(Invoice.class, referenceInvoiceId));
        OBDal.getInstance().save(invoiceLine);
    }

    private long getLineNo(String strInvoiceId) throws ServletException {
        String strLineNo = RCOBAgentCommissionData.selectLineNo(new DalConnectionProvider(), OBDal.getInstance().get(Invoice.class, strInvoiceId).getDocumentNo());
        return Long.valueOf(strLineNo);
    }

    private String getAgentServiceAccount() throws Exception {
        return RCOBAgentCommissionData.getAgentServiceAccount(new DalConnectionProvider());
    }
}
