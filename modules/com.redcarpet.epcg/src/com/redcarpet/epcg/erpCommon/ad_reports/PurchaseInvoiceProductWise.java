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



public class PurchaseInvoiceProductWise extends HttpSecureAppServlet {
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
					"PurchaseInvoiceProductWise|dateFrom", "");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"PurchaseInvoiceProductWise|dateTo", "");

		String strProductId = vars.getGlobalVariable("inpmProductId",
				"PurchaseInvoiceProductWise|productId", "");

		String strOrgId = vars.getGlobalVariable("inpadOrgId",
				"PurchaseInvoiceProductWise|orgId", ""); 

		String strProductCategoryId = vars.getGlobalVariable("inpmProductCategoryId",
						"PurchaseInvoiceProductWise|groupId", ""); 
		String strWarehouseId = vars.getGlobalVariable("inpmWarehouseId",
				"PurchaseInvoiceProductWise|warehouseId", ""); 

		
			printPage(response, vars,  strDateFrom, strDateTo, strProductId, strOrgId, strProductCategoryId, strWarehouseId);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"PurchaseInvoiceProductWise|dateFrom");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"PurchaseInvoiceProductWise|dateTo");
		String strProductId = vars.getRequestGlobalVariable("inpmProductId", 
		         "PurchaseInvoiceProductWise|productId");
		String strOrgId = vars.getGlobalVariable("inpadOrgId",
				"PurchaseInvoiceProductWise|orgId");
		String strProductCategoryId = vars.getRequestGlobalVariable("inpmProductCategoryId",
				"PurchaseInvoiceProductWise|groupId"); 
        String strWarehouseId = vars.getRequestGlobalVariable("inpmWarehouseId",
		"PurchaseInvoiceProductWise|warehouseId"); 


			printPagePopUp(request, response, vars,strDateFrom, strDateTo, strProductId, strOrgId, strProductCategoryId, strWarehouseId, "pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strDateFrom, String strDateTo,String strProductId, String strOrgId, String strProductCategoryId, String strWarehouseId
			) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/epcg/erpCommon/ad_reports/PurchaseInvoiceProductWise").createXmlDocument();

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
		xmlDocument.setParameter("productId", strProductId);
        xmlDocument.setParameter("orgId", strOrgId);
        xmlDocument.setParameter("groupId", strProductCategoryId);
        xmlDocument.setParameter("warehouseId", strWarehouseId); 


		
		

		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));

		ToolBar toolbar = new ToolBar(this, vars.getLanguage(),
				"PurchaseInvoiceProductWise", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.redcarpet.epcg.erpCommon.ad_reports.PurchaseInvoiceProductWise");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"PurchaseInvoiceProductWise.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"PurchaseInvoiceProductWise.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	/*	{
			OBError myMessage = vars.getMessage("PurchaseInvoiceProductWise");
			vars.removeMessage("PurchaseInvoiceProductWise");
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
					"TABLEDIR", "M_Product_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "PurchaseInvoiceProductWise"),
					Utility.getContext(this, vars, "#User_Client",
							"PurchaseInvoiceProductWise"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PurchaseInvoiceProductWise", "");
			xmlDocument.setData("reportM_Product", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		

		
		
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "AD_Org_ID", "", "Org Validation", Utility.getContext(
							this, vars, "#User_Org", "PurchaseInvoiceProductWise"),
					Utility.getContext(this, vars, "#User_Client",
							"PurchaseInvoiceProductWise"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PurchaseInvoiceProductWise", "");
			xmlDocument.setData("reportAD_Org", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		

		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "M_Product_Category_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "PurchaseInvoiceProductWise"),
					Utility.getContext(this, vars, "#User_Client",
							"PurchaseInvoiceProductWise"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PurchaseInvoiceProductWise", "");
			xmlDocument.setData("reportM_Product_Category", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}


		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "M_Warehouse_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "PurchaseInvoiceProductWise"),
					Utility.getContext(this, vars, "#User_Client",
							"PurchaseInvoiceProductWise"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PurchaseInvoiceProductWise", "");
			xmlDocument.setData("reportM_Warehouse", "liststructure",
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

	private void printPagePopUp(HttpServletRequest request,	HttpServletResponse response, VariablesSecureApp vars, String strDateFrom, String strDateTo, String strProductId, String strOrgId,String strProductCategoryId, String strWarehouseId, String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/epcg/erpCommon/ad_reports/PurchaseInvoiceProductWise").createXmlDocument();
	
		String 	strReportName = "@basedesign@/com/redcarpet/epcg/erpCommon/ad_reports/PurchaseInvoiceProductWise.jrxml";


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
		
		
		System.out.println("strProductCategoryId:" + strProductCategoryId);
		System.out.println("strWarehouseId:" + strWarehouseId);
			
    HashMap<String, Object> parameters = new HashMap<String, Object>();
   
    parameters.put("FromDate", fromDate);
    parameters.put("ToDate", toDate);
    parameters.put("StrProductId", strProductId);
    parameters.put("OrgId", strOrgId);
    parameters.put("StrProductCategoryId", strProductCategoryId);
    parameters.put("StrWarehouseId", strWarehouseId);

    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
