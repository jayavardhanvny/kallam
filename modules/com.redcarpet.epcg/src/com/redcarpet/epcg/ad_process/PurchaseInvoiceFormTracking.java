package com.redcarpet.epcg.ad_process;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.financialmgmt.calendar.NonBusinessDay;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.erpCommon.utility.OBError;
import com.redcarpet.epcg.data.EPCGPurchasetrack;
import com.redcarpet.epcg.data.EPCGPurchasetrackline;;
import org.openbravo.model.common.invoice.Invoice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class PurchaseInvoiceFormTracking extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		
		System.out.println("isnide do execute");
		System.out.println(bundle.getParams() +"are params");
		
		String id = (String)bundle.getParams().get("Epcg_Purchasetrack_ID");
		System.out.println(id +" is id ");
		EPCGPurchasetrack purchasestrack = OBDal.getInstance().get(EPCGPurchasetrack.class, id);
		String ftype=purchasestrack.getFormtype();
		Date fdate=purchasestrack.getFromDate();
		Date tdate=purchasestrack.getToDate();
		String bpid=purchasestrack.getBusinessPartner().getId();
		String orgid=purchasestrack.getOrganization().getId();
		
		if(orgid.equals("0") || (orgid.equals("256551BD83DF49DB80BCE5691149CA0B")))
		{	
	List<Invoice> invoices = OBDal.getInstance().createCriteria(Invoice.class).list();
		BigDecimal sum= new BigDecimal("0");
		for(Invoice invoice: invoices){
			Date invdate=invoice.getInvoiceDate();
			Boolean b1=invoice.isSalesTransaction();
			String invftype=invoice.getEpcgType();
			
			if(invftype==null)
			{
				invftype="";
			}
			String epcgformno=invoice.getEpcgFormtypeno();
			String invbpid=invoice.getBusinessPartner().getId();
			String invorgid=invoice.getOrganization().getId();
			String invstatus=invoice.getDocumentStatus();
			String snew="CO";
			if((((invdate.before(tdate))&&(invdate.after(fdate)))||(invdate.equals(fdate)) ||(invdate.equals(tdate))) && (b1==false) && (invftype.equals(ftype)) && (epcgformno == null) && (bpid.equals(invbpid)) && (invstatus.equals(snew)) )
			{
	
			EPCGPurchasetrackline strackline = OBProvider.getInstance().get(EPCGPurchasetrackline.class);
			strackline.setOrganization(invoice.getOrganization());
			strackline.setInvoice(invoice);
			strackline.setEpcgPurchasetrack(purchasestrack);
			strackline.setOrganization(invoice.getOrganization());
			strackline.setInvoiceDate(invoice.getInvoiceDate());
			strackline.setInvoiceNo(invoice.getDocumentNo());
			strackline.setLinetotal(invoice.getSummedLineAmount());
			BigDecimal sumnew=invoice.getSummedLineAmount();
			sum=sum.add(sumnew);
			
			 final OBError msg = new OBError();
		        msg.setType("Success");
		        msg.setTitle("Done");
		        msg.setMessage("Process completed successfully");
		        bundle.setResult(msg);
	
			OBDal.getInstance().save(strackline);
		     }
	
	}
		purchasestrack.setSummedLineAmount(sum);
		purchasestrack.setProcessed(true);
		
		System.out.println("done --- exiting");
		
		}  else
		{
			
			List<Invoice> invoices = OBDal.getInstance().createCriteria(Invoice.class).list();
			BigDecimal sum= new BigDecimal("0");
			for(Invoice invoice: invoices){
				Date invdate=invoice.getInvoiceDate();
				Boolean b1=invoice.isSalesTransaction();
				String invftype=invoice.getEpcgType();
				
				if(invftype==null)
				{
					invftype="";
				}
				String epcgformno=invoice.getEpcgFormtypeno();
				String invbpid=invoice.getBusinessPartner().getId();
				String invorgid=invoice.getOrganization().getId();
				String invstatus=invoice.getDocumentStatus();
				String snew="CO";
				if((((invdate.before(tdate))&&(invdate.after(fdate)))||(invdate.equals(fdate)) ||(invdate.equals(tdate))) && (b1==false) && (invftype.equals(ftype)) && (epcgformno == null) && (bpid.equals(invbpid)) &&(orgid.equals(invorgid)) && (invstatus.equals(snew)) )
				{
		
				EPCGPurchasetrackline strackline = OBProvider.getInstance().get(EPCGPurchasetrackline.class);
				strackline.setOrganization(invoice.getOrganization());
				strackline.setInvoice(invoice);
				strackline.setEpcgPurchasetrack(purchasestrack);
				strackline.setOrganization(invoice.getOrganization());
				strackline.setInvoiceDate(invoice.getInvoiceDate());
				strackline.setInvoiceNo(invoice.getDocumentNo());
				strackline.setLinetotal(invoice.getSummedLineAmount());
				BigDecimal sumnew=invoice.getSummedLineAmount();
				sum=sum.add(sumnew);
				
				 final OBError msg = new OBError();
			        msg.setType("Success");
			        msg.setTitle("Done");
			        msg.setMessage("Process completed successfully");
			        bundle.setResult(msg);
		
				OBDal.getInstance().save(strackline);
			     }
		
		}
			purchasestrack.setSummedLineAmount(sum);
			purchasestrack.setProcessed(true);
			
			System.out.println("done --- exiting");
			
			
		}
		
}
}