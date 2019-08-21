package com.redcarpet.epcg.erpCommon.ad_reports;

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



public class SalesInvoicesPeriodReport extends HttpSecureAppServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) {
		super.init(config);
		boolHist = false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		VariablesSecureApp vars = new VariablesSecureApp(request);

		// Connection conn = new DalConnectionProvider().getConnection();

		// String process = CreateTaxReportData.processId(this,
		// "CreateTaxReport");
		if (vars.commandIn("DEFAULT")) {
			
		String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"SalesInvoicesPeriodReport|dateFrom", "");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"SalesInvoicesPeriodReport|dateTo", "");


		String strDocTypeId = vars.getGlobalVariable("inpcDoctypeId",
				"SalesInvoicesPeriodReport|doctypeId", "");
		
		String strBusinessPartnerId = vars.getGlobalVariable("inpcBpartnerId",
				"SalesInvoicesPeriodReport|bpartnerId", "");
		String strAgentId = vars.getGlobalVariable("inpcBpartnerId",
				"SalesInvoicesPeriodReport|agentId", "");
		
		String strFormType = vars.getGlobalVariable("inpformtype",
				"SalesInvoicesPeriodReport|formtype", "");
		
		
	
			printPage(response, vars,  strDateFrom, strDateTo, strDocTypeId, strBusinessPartnerId, strAgentId, strFormType);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"SalesInvoicesPeriodReport|dateFrom");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"SalesInvoicesPeriodReport|dateTo");

		String strDocTypeId = vars.getGlobalVariable("inpcDoctypeId",
				"SalesInvoicesPeriodReport|doctypeId");
		String strBusinessPartnerId = vars.getRequestGlobalVariable("inpcBpartnerId",
				"SalesInvoicesPeriodReport|bpartnerId");
		String strAgentId = vars.getRequestGlobalVariable("inpcBpartnerId",
				"SalesInvoicesPeriodReport|agentId");
		String strFormType = vars.getRequestGlobalVariable("inpformtype",
				"SalesInvoicesPeriodReport|formtype");
		
	
			printPagePopUp(request, response, vars,strDateFrom, strDateTo, strDocTypeId,  strBusinessPartnerId, strAgentId, strFormType, "pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strDateFrom, String strDateTo, String strDocTypeId,
			String  strBusinessPartnerId, String strAgentId, String strFormType
			) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/epcg/erpCommon/ad_reports/SalesInvoicesPeriodReport").createXmlDocument();

		// String strArray = arrayEntry(vars);

		xmlDocument
				.setParameter("calendar", vars.getLanguage().substring(0, 2));
		xmlDocument.setParameter("language",
				"defaultLang=\"" + vars.getLanguage() + "\";");
		xmlDocument.setParameter("directory", "var baseDirectory = \""
				+ strReplaceWith + "/\";\n");
		//xmlDocument.setParameter("description", strDescription);
		//xmlDocument.setParameter("help", strHelp);
		//xmlDocument.setParameter("dateFrom", strDateFrom);
		
		//xmlDocument.setParameter("org", strOrg)


		xmlDocument.setParameter("dateFrom", strDateFrom);
		xmlDocument.setParameter("dateTo", strDateTo);
        xmlDocument.setParameter("doctypeId", strDocTypeId);
        xmlDocument.setParameter("bpartnerId", strBusinessPartnerId);
        xmlDocument.setParameter("agentId", strAgentId); 
        xmlDocument.setParameter("formtype", strFormType);
      
		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));

		ToolBar toolbar = new ToolBar(this, vars.getLanguage(),
				"SalesInvoicesPeriodReport", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.redcarpet.epcg.erpCommon.ad_reports.SalesInvoicesPeriodReport");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"SalesInvoicesPeriodReport.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"SalesInvoicesPeriodReport.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	/*	{
			OBError myMessage = vars.getMessage("SalesInvoicesPeriodReport");
			vars.removeMessage("SalesInvoicesPeriodReport");
			if (myMessage != null) {
				xmlDocument.setParameter("messageType", myMessage.getType());
				xmlDocument.setParameter("messageTitle", myMessage.getTitle());
				xmlDocument.setParameter("messageMessage",
						myMessage.getMessage());
			}
		} */

		// //----

		// xmlDocument.setParameter("array", strArray);

		
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "C_Doctype_ID", "", "Report DocType SalesInvoice", Utility.getContext(
							this, vars, "#User_Org", "SalesInvoicesPeriodReport"),
					Utility.getContext(this, vars, "#User_Client",
							"SalesInvoicesPeriodReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"SalesInvoicesPeriodReport", "");
			xmlDocument.setData("reportC_Doctype", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "C_Bpartner_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "SalesInvoicesPeriodReport"),
					Utility.getContext(this, vars, "#User_Client",
							"SalesInvoicesPeriodReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"SalesInvoicesPeriodReport", "");
			xmlDocument.setData("reportC_Bpartner", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "C_Bpartner_ID", "", "Business Partner Agent", Utility.getContext(
							this, vars, "#User_Org", "SalesInvoicesPeriodReport"),
					Utility.getContext(this, vars, "#User_Client",
							"SalesInvoicesPeriodReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"SalesInvoicesPeriodReport", "");
			xmlDocument.setData("reportC_BpartnerAgent", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
		
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"LIST", "", "EPCG Type List", "", Utility.getContext(
							this, vars, "#User_Org", "SalesInvoicesPeriodReport"),
					Utility.getContext(this, vars, "#User_Client",
							"SalesInvoicesPeriodReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"SalesInvoicesPeriodReport", "");
			xmlDocument.setData("reportEpcg_Form", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
	

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(xmlDocument.print());
		out.close();
	}

	private void printPagePopUp(HttpServletRequest request,	HttpServletResponse response, VariablesSecureApp vars, String strDateFrom, String strDateTo, String strDocTypeId, String  strBusinessPartnerId, String strAgentId, String strFormType, String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/epcg/erpCommon/ad_reports/SalesInvoicesPeriodReport").createXmlDocument();
	
		String 	strReportName = "@basedesign@/com/redcarpet/epcg/erpCommon/ad_reports/SalesInvoicesPeriodReport.jrxml";

		
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
    parameters.put("DocTypeId", strDocTypeId);
    parameters.put("strBusinessPartner", strBusinessPartnerId);
    parameters.put("strAgent", strAgentId); 
    parameters.put("FormType", strFormType);

    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
