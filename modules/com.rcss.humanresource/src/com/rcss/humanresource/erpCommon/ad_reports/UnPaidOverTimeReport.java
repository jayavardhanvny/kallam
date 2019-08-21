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


public class UnPaidOverTimeReport extends HttpSecureAppServlet {
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
					"UnPaidOverTimeReport|dateFrom", "");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"UnPaidOverTimeReport|dateTo", "");
      
		String stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
				"UnPaidOverTimeReport|employee");
		String strLoan = vars.getRequestGlobalVariable("inploan", 
				"UnPaidOverTimeReport|loan");
		String strBranch = vars.getRequestGlobalVariable("inpbranch",
				"UnPaidOverTimeReport|branch");
		printPage(response, vars, strDateFrom, strDateTo, stremployeeId, strLoan, strBranch);
		}else if (vars.commandIn("PRINT_HTML")) {
			String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"UnPaidOverTimeReport|dateFrom");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"UnPaidOverTimeReport|dateTo");
		
		String stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
				"UnPaidOverTimeReport|employee");
		String strLoan = vars.getRequestGlobalVariable("inploan", 
				"UnPaidOverTimeReport|loan");
		String strBranch = vars.getRequestGlobalVariable("inpbranch", 
				"UnPaidOverTimeReport|branch");
		//String strOrder = vars.getGlobalVariable("inpOrder", "InspLoomWiseDamageReport|Order", "PunchOrder");
			printPagePopUp(request, response, vars, strDateFrom, strDateTo, stremployeeId, strLoan, strBranch, "html");
		
		      
		    } else if (vars.commandIn("PRINT_PDF")) {
			String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"UnPaidOverTimeReport|dateFrom");
		String strDateTo = vars.getGlobalVariable("inpDateTo",
					"UnPaidOverTimeReport|dateTo");
		
		String stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
				"UnPaidOverTimeReport|employee");
		String strLoan = vars.getRequestGlobalVariable("inploan", 
				"UnPaidOverTimeReport|loan");
		String strBranch = vars.getRequestGlobalVariable("inpbranch", 
				"UnPaidOverTimeReport|branch");
		
		/*String strshiftId = vars.getRequestGlobalVariable("inpShift",
				"UnPaidOverTimeReport|shift");
		String strOrder = vars.getGlobalVariable("inpOrder", "UnPaidOverTimeReport|Order", "PunchOrder");
		*/	
		printPagePopUp(request, response, vars, strDateFrom, strDateTo, stremployeeId, strLoan, strBranch, "pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strDateFrom, String strDateTo, String stremployeeId, String strLoan, String strBranch
			) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/erpCommon/ad_reports/UnPaidOverTimeReport").createXmlDocument();

		xmlDocument
				.setParameter("calendar", vars.getLanguage().substring(0, 2));
		xmlDocument.setParameter("language",
				"defaultLang=\"" + vars.getLanguage() + "\";");
		xmlDocument.setParameter("directory", "var baseDirectory = \""
				+ strReplaceWith + "/\";\n");
	
		xmlDocument.setParameter("dateFrom", strDateFrom);
		xmlDocument.setParameter("dateTo", strDateTo);
		//xmlDocument.setParameter("rcbmProjectId", strprojectId);
		xmlDocument.setParameter("rchrEmployeeId", stremployeeId);
		//xmlDocument.setParameter("rchrEmployeeType", strempType);
		//xmlDocument.setParameter("rchrDeptType", strdeptId);
		//xmlDocument.setParameter("overtimedisplay", strOvertime);
		//xmlDocument.setParameter("overtimepayroll", strOvertime);
		
		
		
		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));

		ToolBar toolbar = new ToolBar(this, vars.getLanguage(),
				"UnPaidOverTimeReport", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.rcss.humanresource.erpCommon.ad_reports.UnPaidOverTimeReport");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"UnPaidOverTimeReport.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"UnPaidOverTimeReport.html", strReplaceWith);
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
			*/	try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_EMPLOYEE_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "UnPaidOverTimeReport"),
					Utility.getContext(this, vars, "#User_Client",
							"UnPaidOverTimeReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"UnPaidOverTimeReport", "");
			xmlDocument.setData("reportRCHR_EMPLOYEEID", "liststructure",
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

	

	
	
	private void printPagePopUp(HttpServletRequest request,HttpServletResponse response, VariablesSecureApp vars, String strDateFrom, String strDateTo, 
			String stremployeeId, String strLoan, String strBranch, String strOutput ) 
					throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/erpCommon/ad_reports/UnPaidOverTimeReport").createXmlDocument();
		
		String strReportName = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/UnPaidOverTimeReport.jrxml";
   
		
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
    
    parameters.put("FromD", fromDate);
    parameters.put("ToDate", toDate);
	//parameters.put("Project_ID", strprojectId);
	//parameters.put("Dept_ID", strdeptId);
	parameters.put("EmpName", stremployeeId);
	parameters.put("Emp_paid", strLoan);
	parameters.put("Dyieng", strBranch);

    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
