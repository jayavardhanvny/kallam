package com.redcarpet.epcg.ad_callouts;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import org.openbravo.base.secureApp.VariablesSecureApp;

import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.Utility;


import org.openbravo.utils.FormatUtilities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.enterprise.DocumentType;

import com.rcss.humanresource.data.RCHR_Orderstatus;




public class CostingEnquiryOrderDocumentType extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		
        String strDoctypeId = info.getStringParameter("inpcDoctypeId", null);
        String strOrderstatusId = info.getStringParameter("inprchrOrderstatusId", null);
        //System.out.println("strDoctypeId is " +strDoctypeId);
        
        String strMovementDate = info.getStringParameter("inpenquirydate", null);
       // RCHR_Orderstatus os = 
        
        		
       // System.out.println("strMovementDate in String is " +strMovementDate);
        try {
            
        if ((strDoctypeId != null) && (strMovementDate != null)) {
            doIt(strDoctypeId, strMovementDate,strOrderstatusId, info);
        }
        
        } catch (Exception ex) {
         ex.printStackTrace();
      }


    }
	  private void doIt(String strDoctypeId, String strMovementDate, String strOrderstatusId,CalloutInfo info) {
	        try {
	        	RCHR_Orderstatus os = OBDal.getInstance().get(RCHR_Orderstatus.class, strOrderstatusId);
        		System.out.println("os is " +os.getValidationCode());
        		
	                DocumentType doctype = OBDal.getInstance().get(DocumentType.class, strDoctypeId);
	                Sequence seq=doctype.getDocumentSequence();
	                
	                int currentnext=0;
	                String suffix="";
	                String prefix="";
	                Connection conn = OBDal.getInstance().getConnection();
	                Statement stmt1 = conn.createStatement();
	                String sqry1 = "select currentnext,prefix,suffix from epcg_seqline where (ad_sequence_id='" + seq.getId() + "') and to_date('" + strMovementDate + "') between startdate and enddate";
	                System.out.println("sqry1 is" +sqry1);
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
	                String docno="";
	                if((prefix != null) && (suffix != null))
	                {
	                	//System.out.println("prefix is" +prefix);
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
	                System.out.println("docno is" +docno);
	                

	                info.addResult("inpdocumentno", docno);
	                	            

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}