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


public class PayrollDeductionReportPF extends HttpSecureAppServlet {
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
					"PayrollDeductionReportPF|payrollid", "");
		String strempType = vars.getRequestGlobalVariable("inpEmployeeType", "PayrollDeductionReportPF|employeetype");
      	//stremployeeId = vars.getGlobalVariable("inpEmployee",
				//"PayrollDeductionReportPF|employee", "");
 /*     	String strDuductionId = vars.getGlobalVariable("inpDeduction",
				"PayrollDeductionReportPF|payrollid", "");
  */    	String strBranch = vars.getRequestGlobalVariable("inpbranch",
				"PayrollDeductionReportPF|branch");
  
  		String strReport = vars.getRequestGlobalVariable("inppayrollDed",
			"PayrollDeductionReportPF|type");
	/*	strdeptId = vars.getRequestGlobalVariable("inpDept",
				"PayrollDeductionReportPF|dept");
		String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
				"ShiftForeCastingReport|subdept");
		
		String strPayroll = vars.getGlobalVariable("inppayroll", 
				"PayrollDeductionReportPF|payroll", "payrollDisplay");
		String strPf = vars.getRequestGlobalVariable("inppf", 
				"PayrollDeductionReportPF|pf");
		String strBank = vars.getRequestGlobalVariable("inpbank", 
				"PayrollDeductionReportPF|bank");
	*/	//System.out.println("type.."+strempType);
		//System.out.println("empid.."+stremployeeId);
		
		printPage(response, vars, strPayrollId, strempType, strBranch,strReport);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			
		String strPayrollId = vars.getRequestGlobalVariable("inpPayrollId",
					"PayrollDeductionReportPF|payrollid");
		String strempType = vars.getRequestGlobalVariable("inpEmployeeType", "PayrollDeductionReportPF|employeetype");
/*	strdeptId = vars.getRequestGlobalVariable("inpDept",
					"PayrollDeductionReportPF|dept");
	*/	String strPFType = vars.getRequestGlobalVariable("inpPFType",
				"ShiftForeCastingReport|PFType");	
	/*	String strDuductionId = vars.getRequestGlobalVariable("inpDeduction",
		"PayrollDeductionReportPF|payrollid");*/
        String strBranch = vars.getRequestGlobalVariable("inpbranch",
		"PayrollDeductionReportPF|branch");
        String strReport = vars.getRequestGlobalVariable("inppayrollDed",
    			"PayrollDeductionReportPF|type");
	/*	String strPayroll = vars.getGlobalVariable("inppayroll", 
				"PayrollDeductionReportPF|payroll", "payrollDisplay");
		String strPf = vars.getRequestGlobalVariable("inppf", 
				"PayrollDeductionReportPF|pf");
		String strBank = vars.getRequestGlobalVariable("inpbank", 
				"PayrollDeductionReportPF|bank");
	*/	//String strempType = vars.getRequestGlobalVariable("inpEmployeeType","PayrollDeductionReportPF|empType");
		//System.out.println("empid.."+stremployeeId);						
		
		printPagePopUp(request, response, vars, strPayrollId, strempType, strBranch, strReport,strPFType,"pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strPayrollId, String strempType, String strBranch, String strReport
			) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/payroll/erpCommon/ad_reports/PayrollDeductionReportPF").createXmlDocument();

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
		xmlDocument.setParameter("rchrEmployeeType", strempType);
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
				"PayrollDeductionReportPF", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.redcarpet.payroll.erpCommon.ad_reports.PayrollDeductionReportPF");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"PayrollDeductionReportPF.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"PayrollDeductionReportPF.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
/*				try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCPL_PAYDEDUCITIONSHEAD_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "PayrollDeductionReportPF"),
					Utility.getContext(this, vars, "#User_Client",
							"PayrollDeductionReportPF"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PayrollDeductionReportPF", "");
			xmlDocument.setData("reportRCHR_EMPDEPTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}*/

/*		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCPL_PAYDEDUCITIONSHEAD_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "PayrollDeductionReportPF"),
					Utility.getContext(this, vars, "#User_Client",
							"PayrollDeductionReportPF"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PayrollDeductionReportPF", "");
			xmlDocument.setData("reportRCPL_PAYDEDUCITIONSHEADID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		*/
		
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"LIST", "", "RCHR_Employee_Type", "", Utility.getContext(
							this, vars, "#User_Org", "Att_crosstab"),
					Utility.getContext(this, vars, "#User_Client",
							"EmployeeAttendanceSummaryReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PayrollDeductionReportPF", "");
			xmlDocument.setData("reportRCHR_EMPLOYEETYPE", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
        
        /*	
		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_SUBDEPARTMENT_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "ShiftForeCastingReport"),
					Utility.getContext(this, vars, "#User_Client",
							"ShiftForeCastingReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PayrollDeductionReportPF", "");
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
						this, vars, "#User_Org", "PayrollDeductionReportPF"),
					Utility.getContext(this, vars, "User_Client",
							"PayrollDeductionReportPF"),0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"PayrollDeductionReportPF", "");
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
		String strempType, String strBranch, String strReport,String strPFType,String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/payroll/erpCommon/ad_reports/PayrollDeductionReportPF").createXmlDocument();
		String 	strReportName = "";
		if(strReport.equals("payrollPF")){
			strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/PayrollDeductionReportPF.jrxml";
		}else{
			strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/PayrollDeductionReportESI.jrxml";
		}
		
		

			
    HashMap<String, Object> parameters = new HashMap<String, Object>();
    //System.out.println("processid.."+strPayrollId);
   // System.out.println("employeeid.."+stremployeeId);
   // System.out.println("dept id.."+strdeptId);
    
    parameters.put("ProcessID", strPayrollId);
//	parameters.put("Employee_ID", strDuductionId);
//	parameters.put("Dept_ID", strdeptId);
	parameters.put("EmpType",strempType);
	parameters.put("Dyieng", strBranch);
	parameters.put("pftype", strPFType);
//	parameters.put("SubDept_ID", strsubdeptId);
//	parameters.put("Emp_PF", strPf);
//	parameters.put("Emp_Bank", strBank);
    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
