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


public class PreperatoryIncevReport extends HttpSecureAppServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) {
		super.init(config);
		boolHist = false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		VariablesSecureApp vars = new VariablesSecureApp(request);

		if (vars.commandIn("DEFAULT")) {
			
		String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"PreperatoryIncevReport|dateFrom", "");
		//String strDateTo = vars.getGlobalVariable("inpDateTo",
				//	"PreperatoryIncevReport|dateTo", "");
      
		String strloomId = vars.getGlobalVariable("inpLoom",
				"PreperatoryIncevReport|loom", "");
		String strshiftId = vars.getGlobalVariable("inpShift",
				"PreperatoryIncevReport|shift", "");
		//String strsubdeptId = vars.getGlobalVariable("inpSubDept",
			//	"PreperatoryIncevReport|subdept", "");
		//String strdesignationId = vars.getGlobalVariable("inpDesignation",
			//	"PreperatoryIncevReport|designation", "");
		/*	String strOrder = vars.getGlobalVariable("inpOrder", "PreperatoryIncevReport|Order", "PunchOrder");
		*/	
		printPage(response, vars, strDateFrom, strloomId, strshiftId);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"PreperatoryIncevReport|dateFrom");
		String strloomId = vars.getRequestGlobalVariable("inpLoom",
					"PreperatoryIncevReport|loom");
		
		//String stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
			//	"PreperatoryIncevReport|employee");
		//String strdeptId = vars.getRequestGlobalVariable("inpDept",
			//		"PreperatoryIncevReport|dept");	
		String strshiftId = vars.getRequestGlobalVariable("inpShift",
				"PreperatoryIncevReport|shift");
	//	String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
		//		"PreperatoryIncevReport|subdept");	
		//String strdesignationId = vars.getRequestGlobalVariable("inpDesignation",
			//	"PreperatoryIncevReport|designation");
		/*String strOrder = vars.getGlobalVariable("inpOrder", "PreperatoryIncevReport|Order", "PunchOrder");
		*/
		printPagePopUp(request, response, vars, strDateFrom, strloomId, strshiftId, "pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strDateFrom, String strloomId, String strshiftId ) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/erpCommon/ad_reports/PreperatoryIncevReport").createXmlDocument();

		xmlDocument
				.setParameter("calendar", vars.getLanguage().substring(0, 2));
		xmlDocument.setParameter("language",
				"defaultLang=\"" + vars.getLanguage() + "\";");
		xmlDocument.setParameter("directory", "var baseDirectory = \""
				+ strReplaceWith + "/\";\n");
	
		xmlDocument.setParameter("dateFrom", strDateFrom);
		xmlDocument.setParameter("rcplLoomcategoryId", strloomId);
		//xmlDocument.setParameter("rcbmProjectId", strprojectId);
		//xmlDocument.setParameter("rchrEmpDeptId", strdeptId);
		xmlDocument.setParameter("rcprShiftId", strshiftId);
		//xmlDocument.setParameter("rcprSubDeptId", strsubdeptId);
		//xmlDocument.setParameter("rcprDesignationId", strdesignationId);
	
		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));

		ToolBar toolbar = new ToolBar(this, vars.getLanguage(),
				"PreperatoryIncevReport", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.rcss.humanresource.erpCommon.ad_reports.PreperatoryIncevReport");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"PreperatoryIncevReport.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"PreperatoryIncevReport.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	/*	{
			OBError myMessage = vars.getMessage("POTracking");
			vars.removeMessage("POTracking");
			if (myMessage != null) {
				xmlDocument.setParameter("messageType", myMessage.getType());
				xmlDocument.setParameter("messageTitle", myMessage.getTitle());
				xmlDocument.setParameter("messageMessage",
						myMessage.getMessage());
			}
		} */

		// //----

		// xmlDocument.setParameter("array", strArray);
		
	/*	try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCBM_Project_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "GoodsReceiptReport"),
					Utility.getContext(this, vars, "#User_Client",
							"GoodsReceiptReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"GoodsReceiptReport", "");
			xmlDocument.setData("reportRCBM_PROJECTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
				try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_EMP_DEPT_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "PreperatoryIncevReport"),
					Utility.getContext(this, vars, "#User_Client",
							"PreperatoryIncevReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PreperatoryIncevReport", "");
			xmlDocument.setData("reportRCHR_EMPDEPTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}*/
			try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCPR_SHIFT_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "PreperatoryIncevReport"),
					Utility.getContext(this, vars, "#User_Client",
							"PreperatoryIncevReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PreperatoryIncevReport", "");
			xmlDocument.setData("reportRCPR_SHIFTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
			
		try {
				ComboTableData comboTableData = new ComboTableData(vars, this,
						"TABLEDIR", "RCPL_LOOMCATEGORY_ID", "", "", Utility.getContext(
								this, vars, "#User_Org", "PreperatoryIncevReport"),
						Utility.getContext(this, vars, "#User_Client",
								"PreperatoryIncevReport"), 0);
				Utility.fillSQLParameters(this, vars, null, comboTableData,
						"PreperatoryIncevReport", "");
				xmlDocument.setData("reportRCPL_LOOMCATEGORYID", "liststructure",
						comboTableData.select(false));
				comboTableData = null;
			} catch (Exception ex) {
				throw new ServletException(ex);
			}
		/* 
			try {
				ComboTableData comboTableData = new ComboTableData(vars, this,
						"TABLEDIR", "RCHR_DESIGNATION_ID", "", "", Utility.getContext(
								this, vars, "#User_Org", "PreperatoryIncevReport"),
						Utility.getContext(this, vars, "#User_Client",
								"PreperatoryIncevReport"), 0);
				Utility.fillSQLParameters(this, vars, null, comboTableData,
						"PreperatoryIncevReport", "");
				xmlDocument.setData("reportRCHR_DESIGNATIONID", "liststructure",
						comboTableData.select(false));
				comboTableData = null;
			} catch (Exception ex) {
				throw new ServletException(ex);
			}*/
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(xmlDocument.print());
		out.close();
	}

	private void printPagePopUp(HttpServletRequest request,HttpServletResponse response, VariablesSecureApp vars, String strDateFrom, String strloomId, String strshiftId, String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/erpCommon/ad_reports/PreperatoryIncevReport").createXmlDocument();
	
		String 	strReportName = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/PreperatoryIncevReport.jrxml";

		
        System.out.println("from date1:" + strDateFrom);
       // System.out.println("from date1:" + strDateTo);
		//System.out.println("Project:" + strprojectId);
		//System.out.println("Product:" + strshiftId);
		//System.out.println("Bpartner:" + stremployeeId);
     	SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		Date fromDate = null;
		Date toDate = null;
		
		try{
			fromDate = sdf.parse(strDateFrom);
			//toDate = sdf.parse(strDateTo);
			
		}catch(Exception e){
			System.out.println(e);
			//throws new Exception("ERROR: converting date");
		}
		
		
		System.out.println(" aftr from date1:" + fromDate);
		//System.out.println(" aftr from date2:" + toDate);
		
		
			
    HashMap<String, Object> parameters = new HashMap<String, Object>();
    
    parameters.put("FromD", fromDate);
    parameters.put("Loom_ctgry", strloomId);
	//parameters.put("Project_ID", strprojectId);
	parameters.put("Shift_ID", strshiftId);
	//parameters.put("Dept_ID", strdeptId);
	//parameters.put("SubDept_ID", strsubdeptId);
	//parameters.put("Designation_ID", strdesignationId);
	//put("Orderby_Punch", strOrder);

    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
