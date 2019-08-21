package com.redcarpet.payroll.erpCommon.ad_reports;

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

import org.openbravo.base.exception.OBException;
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
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;

import com.rcss.humanresource.data.RchrEmployee;
import org.openbravo.database.ConnectionProvider;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;


public class DeductionsSummary extends HttpSecureAppServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) {
		super.init(config);
		boolHist = false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String stremployeeId="";
//		String strdeptId="";
		VariablesSecureApp vars = new VariablesSecureApp(request);

		if (vars.commandIn("DEFAULT")) {
		
		String strPayrollId = vars.getGlobalVariable("inpPayrollId",
					"DeductionsSummary|payrollid", "");
//		String strempType = vars.getRequestGlobalVariable("inpEmployeeType", "DeductionsSummary|employeetype");
      	//stremployeeId = vars.getGlobalVariable("inpEmployee",
				//"DeductionsSummary|employee", "");
 /*     	String strDuductionId = vars.getGlobalVariable("inpDeduction",
				"DeductionsSummary|payrollid", "");
  */   // 	String strBranch = vars.getRequestGlobalVariable("inpbranch",
		//		"DeductionsSummary|branch");
	/*	strdeptId = vars.getRequestGlobalVariable("inpDept",
				"DeductionsSummary|dept");
		String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
				"ShiftForeCastingReport|subdept");
		
		String strPayroll = vars.getGlobalVariable("inppayroll", 
				"DeductionsSummary|payroll", "payrollDisplay");
		String strPf = vars.getRequestGlobalVariable("inppf", 
				"DeductionsSummary|pf");
		String strBank = vars.getRequestGlobalVariable("inpbank", 
				"DeductionsSummary|bank");
	*/	//System.out.println("type.."+strempType);
		//System.out.println("empid.."+stremployeeId);
		
		printPage(response, vars, strPayrollId);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			
		String strPayrollId = vars.getRequestGlobalVariable("inpPayrollId",
					"DeductionsSummary|payrollid");
	//	String strempType = vars.getRequestGlobalVariable("inpEmployeeType", "DeductionsSummary|employeetype");
/*	strdeptId = vars.getRequestGlobalVariable("inpDept",
					"DeductionsSummary|dept");
		String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
				"ShiftForeCastingReport|subdept");	
*/	/*	String strDuductionId = vars.getRequestGlobalVariable("inpDeduction",
		"DeductionsSummary|payrollid");*/
     //   String strBranch = vars.getRequestGlobalVariable("inpbranch",
	//	"DeductionsSummary|branch");
	/*	String strPayroll = vars.getGlobalVariable("inppayroll", 
				"DeductionsSummary|payroll", "payrollDisplay");
		String strPf = vars.getRequestGlobalVariable("inppf", 
				"DeductionsSummary|pf");
		String strBank = vars.getRequestGlobalVariable("inpbank", 
				"DeductionsSummary|bank");
	*/	//String strempType = vars.getRequestGlobalVariable("inpEmployeeType","DeductionsSummary|empType");
		//System.out.println("empid.."+stremployeeId);						
		
		printPagePopUp(request, response, vars, strPayrollId, "pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strPayrollId) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/payroll/erpCommon/ad_reports/DeductionsSummary").createXmlDocument();

		xmlDocument
				.setParameter("calendar", vars.getLanguage().substring(0, 2));
		xmlDocument.setParameter("language",
				"defaultLang=\"" + vars.getLanguage() + "\";");
		xmlDocument.setParameter("directory", "var baseDirectory = \""
				+ strReplaceWith + "/\";\n");
	
		//xmlDocument.setParameter("dateFrom", strDateFrom);
		//xmlDocument.setParameter("dateTo", strDateTo);
		//xmlDocument.setParameter("rcbmProjectId", strprojectId);
//		xmlDocument.setParameter("rchrEmpDeptId", strdeptId);
//		xmlDocument.setParameter("rcprSubDeptId", strsubdeptId);
		xmlDocument.setParameter("rchrAttdProcessId", strPayrollId);
//		xmlDocument.setParameter("rchrEmployeeType", strempType);
//		xmlDocument.setParameter("rcplPaydeductionsheadId", strDuductionId);
		//xmlDocument.setParameter("rchrEmployeeId", stremployeeId);
		//xmlDocument.setParameter("rchrEmpType", strempType);
		
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
				"DeductionsSummary", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.redcarpet.payroll.erpCommon.ad_reports.DeductionsSummary");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"DeductionsSummary.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"DeductionsSummary.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
/*				try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCPL_PAYDEDUCITIONSHEAD_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "DeductionsSummary"),
					Utility.getContext(this, vars, "#User_Client",
							"DeductionsSummary"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"DeductionsSummary", "");
			xmlDocument.setData("reportRCHR_EMPDEPTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}*/

/*		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCPL_PAYDEDUCITIONSHEAD_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "DeductionsSummary"),
					Utility.getContext(this, vars, "#User_Client",
							"DeductionsSummary"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"DeductionsSummary", "");
			xmlDocument.setData("reportRCPL_PAYDEDUCITIONSHEADID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		*/
	/*	
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"LIST", "", "RCHR_Employee_Type", "", Utility.getContext(
							this, vars, "#User_Org", "Att_crosstab"),
					Utility.getContext(this, vars, "#User_Client",
							"EmployeeAttendanceSummaryReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"DeductionsSummary", "");
			xmlDocument.setData("reportRCHR_EMPLOYEETYPE", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
        */
        /*	
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_SUBDEPARTMENT_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "ShiftForeCastingReport"),
					Utility.getContext(this, vars, "#User_Client",
							"ShiftForeCastingReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"DeductionsSummary", "");
			xmlDocument.setData("reportRCHR_SUBDEPARTMENTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
*/
		try{
			ComboTableData comboTableData = new ComboTableData(vars, this, 
				"TABLEDIR", "RCHR_ATTD_PROCESS_ID", "", "", Utility.getContext(
						this, vars, "#User_Org", "DeductionsSummary"),
					Utility.getContext(this, vars, "User_Client",
							"DeductionsSummary"),0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"DeductionsSummary", "");
			xmlDocument.setData("reportRCHR_ATTDPROCESSID", "liststructure",
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

	private void printPagePopUp(HttpServletRequest request,HttpServletResponse response, VariablesSecureApp vars, String strPayrollId, 
		String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/payroll/erpCommon/ad_reports/DeductionsSummary").createXmlDocument();
	    

		
		String 	strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/DeductionsSummary.jrxml";

			
    HashMap<String, Object> parameters = new HashMap<String, Object>();
    //System.out.println("processid.."+strPayrollId);
   // System.out.println("employeeid.."+stremployeeId);
   // System.out.println("dept id.."+strdeptId);
    
    parameters.put("ProcessID", strPayrollId);
//	parameters.put("Employee_ID", strDuductionId);
//	parameters.put("Dept_ID", strdeptId);
//	parameters.put("EmpType",strempType);
//	parameters.put("Dyieng", strBranch);
//	parameters.put("SubDept_ID", strsubdeptId);
//	parameters.put("Emp_PF", strPf);
//	parameters.put("Emp_Bank", strBank);
    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
