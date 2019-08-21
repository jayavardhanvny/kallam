package com.redcarpet.epcg.ad_actionbuttons;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.ServletException;
import org.openbravo.erpCommon.utility.OBError;
import org.hibernate.criterion.Restrictions;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.model.common.order.Order;

import com.rcss.humanresource.data.RCHR_Orderstatus;
import com.redcarpet.epcg.data.EpcgCostenquiry;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpcgCostEnquiryManagementApproval extends DalBaseProcess {
	final private static Logger logger = LoggerFactory.getLogger(EpcgCostEnquiryManagementApproval.class);
	 @Override
	    protected void doExecute(ProcessBundle bundle) throws Exception {
		 final OBError msg = new OBError();
		 String message = "";
	    	try{
	    		message = doIt(bundle);
	            msg.setType("Success");
	            msg.setTitle("Done");
	            msg.setMessage(message+" was Successfully Authorized");
	            logger.debug("Document No. is {} ",message);
	            //bundle.setResult(msg);
	    	}catch(Exception e){
	            msg.setType("Error");
			    msg.setTitle("Error");
			    msg.setMessage("Error is : "+e.getMessage());
				logger.error("Error {}",message);
	    	}
	    	 bundle.setResult(msg);
	    }
    public String doIt(ProcessBundle bundle){
		 String id = (String) bundle.getParams().get("Epcg_Costenquiry_ID");
	    	EpcgCostenquiry cost=OBDal.getInstance().get(EpcgCostenquiry.class, id);
	    	String message="";
	    	//cost.getBusinessPartner()
	    	String fabType= cost.getDocumentType().getEpcgFabrictype();
	    	//String dyed = cost.getDocumentType().getEpcgFabrictype();
	    	DocumentType documentType = getDocumentType(fabType);
	    	if(documentType!=null){
	    		try{
	    			String warehouseId = "F435C22F39A047AA828E739DCC8B3B05";
	    			Warehouse warehouse = OBDal.getInstance().get(Warehouse.class, warehouseId);
			    	//String documentNo = getDocumentNumber(doctype);
	    			logger.debug("Doc Type ID ",documentType.getId());
		        	Order order = OBProvider.getInstance().get(Order.class);
		        	order.setSalesTransaction(Boolean.TRUE);
		        	order.setDocumentType(documentType);
		        	order.setTransactionDocument(documentType);
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        	String d = sdf.format(cost.getEnquirydate()).toString();
		        	logger.debug("date is Movement ",d);
		        	Date d2 = new Date(); 
		        	try{
		        		d2 = sdf.parse(sdf.format(d2).toString());
		        	}catch(ParseException e ){
		        		
		        	}
		        	logger.debug("d2 date is ",d2);
		        	order.setOrderDate(d2);
		        	logger.debug("Document No ",getDocumentNo(documentType,d,cost.getRchrOrderstatus()));
		        	order.setDocumentNo(this.getDocumentNo(documentType,d,cost.getRchrOrderstatus()));
		        	//order.setOrderDate(cost.getEnquirydate());
		        	order.setScheduledDeliveryDate(cost.getDeliverdate());
		        	order.setBusinessPartner(cost.getBusinessPartner());
		        	order.setPartnerAddress(cost.getBusinessPartner().getBusinessPartnerLocationList().get(0));
		        	order.setInvoiceAddress(cost.getBusinessPartner().getBusinessPartnerLocationList().get(0));
		        	order.setPaymentMethod(cost.getPaymentMethod());
		        	order.setPaymentTerms(cost.getPaymentTerms());
		        	order.setEpcgBusinessagent(cost.getAgent());
		        	order.setWarehouse(warehouse);
		        	order.setRchrOrderstatus(cost.getRchrOrderstatus());
		        	order.setDocumentStatus("DR");
		        	order.setPriceList(cost.getPriceList());
		        	order.setEpcgRatetype(cost.getEpcgRatetype());
		        	order.setInvoiceTerms(cost.getInvoiceTerms());
		        	order.setEpcgOrdertype(cost.getEpcgOrdertype());
		        	order.setCurrency(cost.getCurrency());
		        	order.setEpcgCostenquiry(cost);
		        	order.setOrderReference(cost.getUserid().getName());
		        	order.setAccountingDate(cost.getEnquirydate());
		        	//order.setCreatedBy(cost.getCreatedBy());
		        	OBDal.getInstance().save(order);
			    	cost.setApproval(Boolean.TRUE);
			    	cost.setAlertStatus("AP");
			    	cost.setProcess(Boolean.TRUE);
			    	message = cost.getDocumentNo();
		    	}catch(Exception e){
		    		message="Got Error";
		    	}
	    	}
	    	return message;
    }
    
    public String getDocumentNo(DocumentType doctype,String strMovementDate,RCHR_Orderstatus os){
    	String docno = "";
    	Sequence seq=doctype.getDocumentSequence();
        try{
        	
       
        int currentnext=0;
        String suffix="";
        String prefix="";
        Connection conn = OBDal.getInstance().getConnection();
        Statement stmt1 = conn.createStatement();
        String sqry1 = "select currentnext,prefix,suffix from epcg_seqline where (ad_sequence_id='" + seq.getId() + "') and to_date('" + strMovementDate + "','yyyy-mm-dd') between startdate and enddate";
        //System.out.println("sqry1 is" +sqry1);
        ResultSet rs1 = stmt1.executeQuery(sqry1);
        while (rs1.next()) {
        	currentnext = rs1.getInt("currentnext");
        	prefix=rs1.getString("prefix");
        	suffix=rs1.getString("suffix");
        	
            System.out.println("currentnext is" + currentnext);
            System.out.println("prefix is" + prefix);
            System.out.println("suffix is" + suffix);

        }

        String nextnew=Integer.toString(currentnext);
        
        //String docno="";
        if((prefix != null) && (suffix != null))
        {
        	
        	System.out.println("Last indext "+prefix.lastIndexOf(prefix));
        	docno=prefix.substring(0,6).concat(os.getValidationCode()).concat("B").concat("/").concat(nextnew).concat(suffix);
        }else if((prefix == null) && (suffix == null))
        {
        	docno=nextnew;
        }else if(prefix == null)
        {
        	docno=nextnew.concat(suffix);
        }
        else if(suffix == null)
        {
        	docno=prefix.concat(nextnew);
        } 
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("docno is" +docno);
    	return docno;
    }
    public DocumentType getDocumentType(String fabType){
    	DocumentType document = null;
    	OBCriteria<DocumentType> docTypeCriteria = OBDal.getInstance().createCriteria(DocumentType.class);
    	docTypeCriteria.add(Restrictions.eq(DocumentType.PROPERTY_EPCGFABRICTYPE,fabType));
    	docTypeCriteria.add(Restrictions.eq(DocumentType.PROPERTY_SALESTRANSACTION,Boolean.TRUE));
    	for(DocumentType doctypefor : docTypeCriteria.list()){
    		document=doctypefor;
    	}
    	return document;
    }
    
    private void throwError() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "Lines are not created", language));
    }
   

}
