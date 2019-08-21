package com.rcss.humanresource.erpCommon.ad_reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.ad_actionButton.ActionButtonDefaultData;
import org.openbravo.erpCommon.businessUtility.WindowTabs;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.DateTimeData;
import org.openbravo.erpCommon.utility.LeftTabsBar;
import org.openbravo.erpCommon.utility.NavigationBar;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.ToolBar;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.xmlEngine.XmlDocument;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.dal.service.OBDal;
import java.sql.*;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;




public class Advancesreport  extends HttpSecureAppServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) {
		super.init(config);
		boolHist = false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		VariablesSecureApp vars = new VariablesSecureApp(request);

		// Connection conn = new DalConnectionProvider().getConnection();

		// String process = CreateTaxReportsData.processId(this,
		// "CreateTaxReports");
		if (vars.commandIn("DEFAULT")) {
			
		String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"Advancesreport|dateFrom", "");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"Advancesreport|dateTo", "");
		

		
		
		printPage(response, vars, strDateFrom, strDateTo);	
//			printPage(response, vars, strDateFrom, strDateTo, strOrgId, strProductId, strProductCategoryId, strDocTypeId, strBusinessPartnerId, strAgentId, strFormType);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"Advancesreport|dateFrom");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"Advancesreport|dateTo");
		String strOrgId = vars.getRequestGlobalVariable("inpadOrgId",
				"Advancesreport|orgId"); 
		String strProductId = vars.getRequestGlobalVariable("inpmProductId",
				"Advancesreport|productId");
		String strProductCategoryId = vars.getRequestGlobalVariable("inpmProductCategoryId",
				"PurchaseInvoiceProductWise|groupId");
		String strDocTypeId = vars.getRequestGlobalVariable("inpcDoctypeId",
				"Advancesreport|doctypeId");
		
		String strBusinessPartnerId = vars.getRequestGlobalVariable("inpbpartnerId",
				"Advancesreport|bpartnerId");
		String strAgentId = vars.getRequestGlobalVariable("inpagentId",
				"Advancesreport|agentId");
		
		String strFormType = vars.getRequestGlobalVariable("inpformtype",
				"Advancesreport|formtype");

		    printPagePopUp(request, response, vars, strDateFrom, strDateTo, strOrgId, strProductId, strProductCategoryId, strDocTypeId, strBusinessPartnerId, strAgentId, strFormType, "pdf");

		}
		
		
		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strDateFrom, String strDateTo) throws IOException,
			ServletException {

	
	
XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/erpCommon/ad_reports/Advancesreport").createXmlDocument();


		xmlDocument
				.setParameter("calendar", vars.getLanguage().substring(0, 2));
		xmlDocument.setParameter("language",
				"defaultLang=\"" + vars.getLanguage() + "\";");
		xmlDocument.setParameter("directory", "var baseDirectory = \""
				+ strReplaceWith + "/\";\n");

		xmlDocument.setParameter("dateFrom", strDateFrom);
		xmlDocument.setParameter("dateTo", strDateTo);
		
		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));

		ToolBar toolbar = new ToolBar(this, vars.getLanguage(),
				"Advancesreport", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.redcarpet.epcg.erpCommon.ad_reports.Advancesreport");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"Advancesreport.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"Advancesreport.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	


		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(xmlDocument.print());
		out.close();
	}

	private void printPagePopUp(HttpServletRequest request,	HttpServletResponse response, VariablesSecureApp vars, String strDateFrom, String strDateTo, String strOrgId, String strProductId, String strProductCategoryId, String strDocTypeId, String strBusinessPartnerId, String strAgentId, String strFormType, String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/erpCommon/ad_reports/Advancesreport").createXmlDocument();
	
		String 	strReportsName = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/Advancesreport.jrxml";


        System.out.println("from date1:" + strDateFrom);
        System.out.println("from date1:" + strDateTo);
     	SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		Date fromDate = null;
		Date toDate = null;
		
		try{
			fromDate = sdf.parse(strDateFrom);
			toDate = sdf.parse(strDateTo);
			
		}catch(Exception e){
			System.out.println(e);
			//throws new Exception("ERROR: converting date");
		}
		
		
		System.out.println(" aftr from date1:" + fromDate);
		System.out.println(" aftr from date2:" + toDate);
		
		
			
     HashMap<String, Object> parameters = new HashMap<String, Object>();
   
    parameters.put("FromDate", fromDate);
    parameters.put("ToDate", toDate);
	parameters.put("OrgId", strOrgId);
	parameters.put("StrProductId", strProductId);
    parameters.put("StrProductCategoryId", strProductCategoryId);
	parameters.put("DocTypeId", strDocTypeId);
    parameters.put("strBusinessPartner", strBusinessPartnerId);
    parameters.put("strAgent", strAgentId); 
    parameters.put("FormType", strFormType);
      
    renderJR(vars, response, strReportsName, strOutput, parameters, null, null);

	
	}
	
	

}
