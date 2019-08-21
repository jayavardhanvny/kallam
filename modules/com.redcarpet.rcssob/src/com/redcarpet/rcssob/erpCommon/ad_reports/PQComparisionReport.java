package com.redcarpet.rcssob.erpCommon.ad_reports;

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



public class PQComparisionReport extends HttpSecureAppServlet {
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
			
		String strRequisition = vars.getGlobalVariable("inprcobPrrequisitionId",
					"PQComparisionReport|requisition", ""); 
		
			printPage(response, vars,  strRequisition);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			String strRequisition = vars.getGlobalVariable("inprcobPrrequisitionId",
					"PQComparisionReport|requisition");
		
			printPagePopUp(request, response, vars, strRequisition, "pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strRequisition
			) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/rcssob/erpCommon/ad_reports/PQComparisionReport").createXmlDocument();

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


		xmlDocument.setParameter("requisition", strRequisition);
	
		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));

		ToolBar toolbar = new ToolBar(this, vars.getLanguage(),
				"PQComparisionReport", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.redcarpet.rcssob.erpCommon.ad_reports.PQComparisionReport");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"PQComparisionReport.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"PQComparisionReport.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	/*	{
			OBError myMessage = vars.getMessage("PQComparisionReport");
			vars.removeMessage("PQComparisionReport");
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
					"TABLEDIR", "RCOB_Prrequisition_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "PQComparisionReport"),
					Utility.getContext(this, vars, "#User_Client",
							"PQComparisionReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PQComparisionReport", "");
			xmlDocument.setData("reportBM_Prrequisition", "liststructure",
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

	private void printPagePopUp(HttpServletRequest request,	HttpServletResponse response, VariablesSecureApp vars, String strRequisition, String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/rcssob/erpCommon/ad_reports/PQComparisionReport").createXmlDocument();
	
		String 	strReportName = "@basedesign@/com/redcarpet/rcssob/erpCommon/ad_reports/PQComparisionReport.jrxml";


        System.out.println("requisition is:" + strRequisition);
       
			
    HashMap<String, Object> parameters = new HashMap<String, Object>();
   
    parameters.put("StrRequisition", strRequisition);


    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
