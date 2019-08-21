package com.redcarpet.epcg.ad_process;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.financialmgmt.calendar.NonBusinessDay;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.erpCommon.utility.OBError;
import org.hibernate.criterion.Restrictions;
import com.redcarpet.epcg.data.EPCGSalestrack;
import com.redcarpet.epcg.data.EPCGSalestrackline;
import com.redcarpet.epcg.data.EpcgEpcgPurchase;
import com.redcarpet.epcg.data.EpcgMacSpare;

import org.openbravo.model.common.invoice.Invoice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class UpdateSalesInvoiceFormTracking extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		
		System.out.println("isnide do execute");
		System.out.println(bundle.getParams() +"are params");
		
		String id = (String)bundle.getParams().get("Epcg_Salestrack_ID");
		System.out.println(id +" is id ");
		EPCGSalestrack salestrack = OBDal.getInstance().get(EPCGSalestrack.class, id);
		String formno=salestrack.getFormno();
		if(formno==null)
		{
			throw new NullPointerException("Please Enter the Form Number in Header");
		}
		
		salestrack.setProcessnewone(true);
             List<EPCGSalestrackline> stracklines = OBDal.getInstance().createCriteria(EPCGSalestrackline.class).list();
             //stracklines.add(Restrictions.eq(EPCGSalestrackline.PROPERTY_EPCGSALESTRACK, salestrack));
            
		        for(EPCGSalestrackline strackline: stracklines){
		        	String strackid=strackline.getEpcgSalestrack().getId();
		        	if(strackid.equals(id))
		        	{
			       String stlinvno=strackline.getInvoiceNo();
			       String stinvid=strackline.getInvoice().getId();
			
			            List<Invoice> invoices = OBDal.getInstance().createCriteria(Invoice.class).list();
			            //invoices.add(Restrictions.eq(Invoice.PROPERTY_DOCUMENTNO, stlinvno));
			                   for(Invoice invoice: invoices){
			                	   String docno=invoice.getDocumentNo();
			                	   String invid=invoice.getId();
			                	   Boolean b1=invoice.isSalesTransaction();
			                	   if(docno.equals(stlinvno) && (invid.equals(stinvid)) && (b1==true))
			                	   {
				                  invoice.setEpcgFormtypeno(formno);
			                      }
			                   }
		        	}
			
			 final OBError msg = new OBError();
		        msg.setType("Success");
		        msg.setTitle("Done");
		        msg.setMessage("Process completed successfully");
		        bundle.setResult(msg);
	
		
			
	}

		System.out.println("done --- exiting");

}
}