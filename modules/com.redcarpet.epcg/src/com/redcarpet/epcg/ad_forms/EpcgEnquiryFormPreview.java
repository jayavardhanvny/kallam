package com.redcarpet.epcg.ad_forms;

import java.io.IOException;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import javax.servlet.ServletException;
import org.openbravo.dal.core.DalUtil;
import org.openbravo.dal.core.OBContext;
import java.io.PrintWriter;
import org.openbravo.xmlEngine.XmlDocument;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openbravo.erpCommon.utility.DateTimeData;
import org.openbravo.model.ad.system.Client;

public class EpcgEnquiryFormPreview extends HttpSecureAppServlet{
	private static final long serialVersionUID = 1L;
	
	/*public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		VariablesSecureApp vars = new VariablesSecureApp(request);

	  if (vars.commandIn("DEFAULT")) {
	    String strcBpartnerId = vars.getSessionValue("RptC_Bpartner.inpcBpartnerId_R");
	    if (strcBpartnerId.equals(""))
	      strcBpartnerId = vars.getSessionValue("RptC_Bpartner.inpcBpartnerId");
	    printPageDataSheet(response, vars, strcBpartnerId);
	  } else
	    pageError(response);
	    
	    
	    
	    
	    
	    String strWindowId = vars.getStringParameter("inpwindowId");
      	String strSOTrx = Utility.getContext(this, vars, "isSOTrx", strWindowId);
      	String strKey = vars.getGlobalVariable("inpcOrderId", strWindowId + "|C_Order_ID");
      	String strTabId = vars.getStringParameter("inpTabId");
      
      
      
	}*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	  		throws IOException, ServletException {
		  
	    VariablesSecureApp vars = new VariablesSecureApp(request);

	    if (vars.commandIn("DEFAULT")) {
	      //String strEmppayrollId = vars.getSessionValue("ReportPaySlips.inprcplEmppayrollprocessId_R");
	    	
	    //    String strBpartner = vars.getStringParameter("inpcBpartnerId");
	      //  String strmPricelistId = vars.getStringParameter("inpmPricelistId");
	    	
	    	
	    	
	        String strEpcgCostEnquiryId = vars.getSessionValue("EpcgEnquiryFormPreview.inpepcgCostenquiryId_R");
	      
	      	String strWindowId = vars.getStringParameter("inpwindowId");
	      	
	      	String strSOTrx = Utility.getContext(this, vars, "isSOTrx", strWindowId);
	      	String strKey = "";//vars.getGlobalVariable("inpepcgCostenquiryId", strWindowId + "|Epcg_Costenquiry_ID");
	      	
	      	String strTabId = vars.getStringParameter("inpTabId");
	      	
	      	System.out.println("strSOTrx ooooooooooooo  "+strSOTrx);
	      	System.out.println("strTabId ooooooooooooo  "+strTabId);
	      	System.out.println("strWindowId ooooooooooooo  "+strWindowId);
	      	//System.out.println("strKey ooooooooooooo  "+strKey);
	      	
	      if (strEpcgCostEnquiryId.equals(""))
	    	  strEpcgCostEnquiryId = vars.getSessionValue("EpcgEnquiryFormPreview.inpepcgCostenquiryId");
	      
	      	//stremployeeId = vars.getSessionValue("ReportPayslips.inprchrEmployeeId");
	      System.out.println(strEpcgCostEnquiryId+" ---strEpcgCostEnquiryId");
	      if (log4j.isDebugEnabled())
	        log4j.debug("+***********************: " + strEpcgCostEnquiryId);
	      	
	      printPageDataSheet(response, vars, strKey, strWindowId, strTabId, strSOTrx, strEpcgCostEnquiryId);
	    } else
	      pageError(response);
	  }
	
	
	private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,String strKey, 
			String strWindowId, String strTabId, String strSOTrx, 
			String strEpcgCostEnquiryId) throws IOException, ServletException {
		if (log4j.isDebugEnabled())
			log4j.debug("Output: dataSheet");
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    //String discard[] = { "", "", "", "", "", "", "", "", "", "", "", "", "" };
		    XmlDocument xmlDocument = null;

		    try {
		      OBContext.setAdminMode(true);
		      System.out.println("strEpcgCostEnquiryId-----------------------"+strEpcgCostEnquiryId);
		      strEpcgCostEnquiryId = strEpcgCostEnquiryId.replaceAll("\\(|\\)|'", "");
		      System.out.println("strEpcgCostEnquiryId 333232 -----------------------"+strEpcgCostEnquiryId);
		      EpcgEnquiryFormPreviewData[] dataPartner = EpcgEnquiryFormPreviewData.select(this,
		    		  strEpcgCostEnquiryId);
		      
		      Client c = OBDal.getInstance().get(Client.class,
		          OBContext.getOBContext().getCurrentClient().getId());
		      
		      
		      xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/epcg/ad_forms/EpcgEnquiryFormPreview")
		          .createXmlDocument();
		      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
		      xmlDocument.setParameter("paramLanguage", "defaultLang=\"" + vars.getLanguage() + "\";");
		      xmlDocument.setParameter("theme", vars.getTheme());
		      //xmlDocument.setParameter("paramBpartner", dataPartner[0].cBpartnerId);
		      xmlDocument.setParameter("paramEpcgCostenquiryId", strEpcgCostEnquiryId);
		      //xmlDocument.setParameter("key", strKey);
		      //xmlDocument.setParameter("windowId", strWindowId);
		      //xmlDocument.setParameter("tabId", strTabId);
		      xmlDocument.setParameter("paramSysdate", DateTimeData.today(this));
		      
		      xmlDocument.setData("structure1", dataPartner);
		      //System.out.println("dataPartner[0] ++++++++++++ "+dataPartner[0].epcgCostenquiryId);
		      
		      out.println(xmlDocument.print());
		      out.close();
		    } finally {
		      OBContext.restorePreviousMode();
		    }
	}	
	public String getServletInfo() {
	    return "Servlet strEpcgCostEnquiryId. This Servlet was made by Vinay Kumar";
	  } // End of getServletInfo() method
}