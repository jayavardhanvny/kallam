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


public class LoomSummaryReport extends HttpSecureAppServlet {
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
					"LoomSummaryReport|dateFrom", "");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"LoomSummaryReport|dateTo", "");
		String stremployeeId = vars.getGlobalVariable("inpEmployee",
				"LatecomingReport|employee", "");
		String strshiftId = vars.getRequestGlobalVariable("inpShift",
					"LoomIncentiveReport|shift");
		String strloomCatId = vars.getRequestGlobalVariable("inpLoom",
					"LoomIncentiveReport|loom");

		//String strloomId = vars.getGlobalVariable("inpLoom",
		//		"LoomSummaryReport|loom", "");
	//	String strshiftId = vars.getGlobalVariable("inpShift",
		//		"LoomSummaryReport|shift", "");
		//String strsubdeptId = vars.getGlobalVariable("inpSubDept",
			//	"LoomSummaryReport|subdept", "");
		//String strdesignationId = vars.getGlobalVariable("inpDesignation",
			//	"LoomSummaryReport|designation", "");
		/*	String strOrder = vars.getGlobalVariable("inpOrder", "LoomSummaryReport|Order", "PunchOrder");
		*/	
		printPage(response, vars, strDateFrom, strDateTo, stremployeeId, strshiftId, strloomCatId);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"LoomSummaryReport|dateFrom");
		//String strloomId = vars.getRequestGlobalVariable("inpLoom",
			//		"LoomSummaryReport|loom");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
				"LoomSummaryReport|dateTo");
		String stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
				"LatecomingReport|employee");
		String strshiftId = vars.getRequestGlobalVariable("inpShift",
					"LoomIncentiveReport|shift");
		String strloomCatId = vars.getRequestGlobalVariable("inpLoom",
					"LoomIncentiveReport|loom");
		String strReportType = vars.getRequestGlobalVariable("inploomProductionReportType",
					"LoomIncentiveReport|loomsProductionReportType");
		//String stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
			//	"LoomSummaryReport|employee");
		//String strdeptId = vars.getRequestGlobalVariable("inpDept",
			//		"LoomSummaryReport|dept");	
		//String strshiftId = vars.getRequestGlobalVariable("inpShift",
				//"LoomSummaryReport|shift");
	//	String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
		//		"LoomSummaryReport|subdept");	
		//String strdesignationId = vars.getRequestGlobalVariable("inpDesignation",
			//	"LoomSummaryReport|designation");
		/*String strOrder = vars.getGlobalVariable("inpOrder", "LoomSummaryReport|Order", "PunchOrder");
		*/
		printPagePopUp(request, response, vars, strDateFrom, strDateTo, stremployeeId, strshiftId, strloomCatId, strReportType, "pdf");

		}
		else if (vars.commandIn("PRINT_HTML")) {
			String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"LoomSummaryReport|dateFrom");
			//String strloomId = vars.getRequestGlobalVariable("inpLoom",
			//		"LoomSummaryReport|loom");
			String strDateTo = vars.getGlobalVariable("inpDateTo",
					"LoomSummaryReport|dateTo");
			String stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
					"LatecomingReport|employee");
			String strshiftId = vars.getRequestGlobalVariable("inpShift",
					"LoomIncentiveReport|shift");
			String strloomCatId = vars.getRequestGlobalVariable("inpLoom",
					"LoomIncentiveReport|loom");

			String strReportType = vars.getRequestGlobalVariable("inploomProductionReportType",
					"LoomIncentiveReport|loomsProductionReportType");
			//String stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
			//	"LoomSummaryReport|employee");
			//String strdeptId = vars.getRequestGlobalVariable("inpDept",
			//		"LoomSummaryReport|dept");
			//String strshiftId = vars.getRequestGlobalVariable("inpShift",
			//"LoomSummaryReport|shift");
			//	String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
			//		"LoomSummaryReport|subdept");
			//String strdesignationId = vars.getRequestGlobalVariable("inpDesignation",
			//	"LoomSummaryReport|designation");
		/*String strOrder = vars.getGlobalVariable("inpOrder", "LoomSummaryReport|Order", "PunchOrder");
		*/
			printPagePopUp(request, response, vars, strDateFrom, strDateTo, stremployeeId, strshiftId, strloomCatId, strReportType,"html");

		}

		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strDateFrom, String strDateTo, String stremployeeId, String strshiftId, String strloomCatId) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/erpCommon/ad_reports/LoomSummaryReport").createXmlDocument();

		xmlDocument
				.setParameter("calendar", vars.getLanguage().substring(0, 2));
		xmlDocument.setParameter("language",
				"defaultLang=\"" + vars.getLanguage() + "\";");
		xmlDocument.setParameter("directory", "var baseDirectory = \""
				+ strReplaceWith + "/\";\n");
	
		xmlDocument.setParameter("dateFrom", strDateFrom);
		xmlDocument.setParameter("dateTo", strDateTo);
		xmlDocument.setParameter("rchrEmployeeId", stremployeeId);
		//xmlDocument.setParameter("rcplLoomcategoryId", strloomId);
		//xmlDocument.setParameter("rcbmProjectId", strprojectId);
		//xmlDocument.setParameter("rchrEmpDeptId", strdeptId);
		//xmlDocument.setParameter("rcprShiftId", strshiftId);
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
				"LoomSummaryReport", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.rcss.humanresource.erpCommon.ad_reports.LoomSummaryReport");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"LoomSummaryReport.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"LoomSummaryReport.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_EMPLOYEE_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "MessBillReport"),
					Utility.getContext(this, vars, "#User_Client",
							"MessBillReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"LoomSummaryReport", "");
			xmlDocument.setData("reportRCHR_EMPLOYEEID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCPR_SHIFT_ID", "", "", Utility.getContext(
					this, vars, "#User_Org", "LoomIncentiveReport"),
					Utility.getContext(this, vars, "#User_Client",
							"LoomIncentiveReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"LoomIncentiveReport", "");
			xmlDocument.setData("reportRCPR_SHIFTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCPL_LOOMCATEGORY_ID", "", "", Utility.getContext(
					this, vars, "#User_Org", "LoomIncentiveReport"),
					Utility.getContext(this, vars, "#User_Client",
							"LoomIncentiveReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"LoomIncentiveReport", "");
			xmlDocument.setData("reportRCPL_LOOMCATEGORYID", "liststructure",
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

	private void printPagePopUp(HttpServletRequest request,HttpServletResponse response, VariablesSecureApp vars,
								String strDateFrom, String strDateTo, String stremployeeId, String strshiftId, String strloomCatId, String strReportType, String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/erpCommon/ad_reports/LoomSummaryReport").createXmlDocument();
	

		
		
        System.out.println("from date1:" + strDateFrom);
        System.out.println("from date1:" + strDateTo);
		//System.out.println("Project:" + strprojectId);
		//System.out.println("Product:" + strshiftId);
		//System.out.println("Bpartner:" + stremployeeId);
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

		String 	strReportName = "";
		if (strReportType.equals("efficiencySummaryReport")){
			strReportName = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/LoomSummaryReport.jrxml";
		} else if (strReportType.equals("loomsAllocatedReport")){
			strReportName = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/LoomsProductionAssignedLooms.jrxml";
		} else if (strReportType.equals("loomIncentiveReport")){
			strReportName = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/LoomIncentiveReport.jrxml";
		}


		log4j.info(" aftr from date1:" + fromDate);
		//System.out.println(" aftr from date2:" + toDate);
		
		
			
    HashMap<String, Object> parameters = new HashMap<String, Object>();
    
    parameters.put("FromD", fromDate);
    parameters.put("ToDate", toDate);
    parameters.put("EmpName", stremployeeId);
    parameters.put("Loom_ctgry", strloomCatId);
	//parameters.put("Project_ID", strprojectId);
	//parameters.put("Shift_ID", strshiftId);
	//parameters.put("Dept_ID", strdeptId);
	//parameters.put("SubDept_ID", strsubdeptId);
	//parameters.put("Designation_ID", strdesignationId);
	//put("Orderby_Punch", strOrder);

    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
