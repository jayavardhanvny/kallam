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


public class UnPaidListReport extends HttpSecureAppServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) {
		super.init(config);
		boolHist = false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String stremployeeId="";
		String strdeptId="";
		VariablesSecureApp vars = new VariablesSecureApp(request);

		if (vars.commandIn("DEFAULT")) {
		
		String strPayrollId = vars.getGlobalVariable("inpPayrollId",
					"UnPaidListReport|payrollid", "");
		String strempType = vars.getGlobalVariable("inpEmployeeType", "UnPaidListReport|employeetype","");
      	
		strdeptId = vars.getRequestGlobalVariable("inpDept",
				"UnPaidListReport|dept");
		String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
				"ShiftForeCastingReport|subdept");
		
		String strLoan = vars.getRequestGlobalVariable("inploan", 
				"UnPaidListReport|loan");
		String strBranch = vars.getRequestGlobalVariable("inpbranch",
				"UnPaidListReport|branch");
		//System.out.println("type.."+strempType);
		//System.out.println("empid.."+stremployeeId);
		
		printPage(response, vars, strPayrollId, strdeptId, strsubdeptId, strempType, strLoan, strBranch);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			
		String strPayrollId = vars.getGlobalVariable("inpPayrollId",
					"UnPaidListReport|payrollid", "");
		strdeptId = vars.getRequestGlobalVariable("inpDept",
					"UnPaidListReport|dept");
		String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
				"ShiftForeCastingReport|subdept");	
		String strempType = vars.getGlobalVariable("inpEmployeeType", "UnPaidListReport|employeeType");
		String strLoan = vars.getRequestGlobalVariable("inploan", 
				"UnPaidListReport|loan");
		String strBranch = vars.getRequestGlobalVariable("inpbranch", 
				"UnPaidListReport|branch");
		
		//String strempType = vars.getRequestGlobalVariable("inpEmployeeType","UnPaidListReport|empType");
		//System.out.println("empid.."+stremployeeId);						
		
		printPagePopUp(request, response, vars, strPayrollId, strdeptId, strsubdeptId, strempType, strLoan, strBranch, "pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strPayrollId, String strdeptId, String strsubdeptId, String strempType, String strLoan, String strBranch
			) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/payroll/erpCommon/ad_reports/UnPaidListReport").createXmlDocument();

		xmlDocument
				.setParameter("calendar", vars.getLanguage().substring(0, 2));
		xmlDocument.setParameter("language",
				"defaultLang=\"" + vars.getLanguage() + "\";");
		xmlDocument.setParameter("directory", "var baseDirectory = \""
				+ strReplaceWith + "/\";\n");
	
		//xmlDocument.setParameter("dateFrom", strDateFrom);
		//xmlDocument.setParameter("dateTo", strDateTo);
		//xmlDocument.setParameter("rcbmProjectId", strprojectId);
		xmlDocument.setParameter("rchrEmpDeptId", strdeptId);
		xmlDocument.setParameter("rcprSubDeptId", strsubdeptId);
		xmlDocument.setParameter("rchrAttdProcessId", strPayrollId);
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
				"UnPaidListReport", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.redcarpet.payroll.erpCommon.ad_reports.UnPaidListReport");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"UnPaidListReport.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"UnPaidListReport.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
				try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_EMP_DEPT_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "UnPaidListReport"),
					Utility.getContext(this, vars, "#User_Client",
							"UnPaidListReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"UnPaidListReport", "");
			xmlDocument.setData("reportRCHR_EMPDEPTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		
		
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_SUBDEPARTMENT_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "ShiftForeCastingReport"),
					Utility.getContext(this, vars, "#User_Client",
							"ShiftForeCastingReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"UnPaidListReport", "");
			xmlDocument.setData("reportRCHR_SUBDEPARTMENTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		try{
			ComboTableData comboTableData = new ComboTableData(vars, this, 
				"TABLEDIR", "RCHR_ATTD_PROCESS_ID", "", "", Utility.getContext(
						this, vars, "#User_Org", "UnPaidListReport"),
					Utility.getContext(this, vars, "User_Client",
							"UnPaidListReport"),0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"UnPaidListReport", "");
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
		String strdeptId, String strsubdeptId, String strempType, String strLoan, String strBranch, String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/payroll/erpCommon/ad_reports/UnPaidListReport").createXmlDocument();
	    
		String 	strReportName;
		
			
			strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/UnPaidListReport.jrxml";
		
		   // strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/UnPaidListReport.jrxml";
		
			
    HashMap<String, Object> parameters = new HashMap<String, Object>();
   
    parameters.put("ProcessID", strPayrollId);
	parameters.put("Dept_ID", strdeptId);
	parameters.put("EmpType",strempType);
	parameters.put("SubDept_ID", strsubdeptId);
	parameters.put("Emp_paid", strLoan);
	parameters.put("Dyieng", strBranch);
	
    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
