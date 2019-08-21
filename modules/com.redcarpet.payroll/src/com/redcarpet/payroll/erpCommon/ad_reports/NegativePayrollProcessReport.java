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


public class NegativePayrollProcessReport extends HttpSecureAppServlet {
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
					"NegativePayrollProcessReport|payrollid", "");
		String strempType = vars.getGlobalVariable("inpEmployeeType", "NegativePayrollProcessReport|employeetype","");
      	stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
				"NegativePayrollProcessReport|employee");
		strdeptId = vars.getRequestGlobalVariable("inpDept",
				"NegativePayrollProcessReport|dept");
		String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
				"ShiftForeCastingReport|subdept");
		
		String strPayroll = vars.getGlobalVariable("inppayroll", 
				"NegativePayrollProcessReport|payroll", "payrollDisplay");
		String strPf = vars.getRequestGlobalVariable("inppf", 
				"NegativePayrollProcessReport|pf");
		String strBank = vars.getRequestGlobalVariable("inpbank", 
				"NegativePayrollProcessReport|bank");
		//System.out.println("type.."+strempType);
		//System.out.println("empid.."+stremployeeId);
		
		printPage(response, vars, strPayrollId, strdeptId, strsubdeptId, stremployeeId, strempType, strPayroll, strPf, strBank);
		} 

		else if (vars.commandIn("PRINT_PDF")) {
			
		String strPayrollId = vars.getGlobalVariable("inpPayrollId",
					"NegativePayrollProcessReport|payrollid", "");
		stremployeeId = vars.getRequestGlobalVariable("inpEmployee",
				"NegativePayrollProcessReport|employee");
		strdeptId = vars.getRequestGlobalVariable("inpDept",
					"NegativePayrollProcessReport|dept");
		String strsubdeptId = vars.getRequestGlobalVariable("inpSubDept",
				"ShiftForeCastingReport|subdept");	
		String strempType = vars.getGlobalVariable("inpEmployeeType", "NegativePayrollProcessReport|employeeType");
		String strPayroll = vars.getGlobalVariable("inppayroll", 
				"NegativePayrollProcessReport|payroll", "payrollDisplay");
		String strPf = vars.getRequestGlobalVariable("inppf", 
				"NegativePayrollProcessReport|pf");
		String strBank = vars.getRequestGlobalVariable("inpbank", 
				"NegativePayrollProcessReport|bank");
		//String strempType = vars.getRequestGlobalVariable("inpEmployeeType","NegativePayrollProcessReport|empType");
		//System.out.println("empid.."+stremployeeId);						
		
		printPagePopUp(request, response, vars, strPayrollId, strdeptId, strsubdeptId, stremployeeId, strempType, strPayroll, strPf, strBank, "pdf");
		}


		else
		{
			pageErrorPopUp(response);
		}
	}

	private void printPage(HttpServletResponse response,
			VariablesSecureApp vars, String strPayrollId, String strdeptId, String strsubdeptId, String stremployeeId, String strempType, String strPayroll, String strPf, String strBank
			) throws IOException,
			ServletException {
	

XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/payroll/erpCommon/ad_reports/NegativePayrollProcessReport").createXmlDocument();

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
		xmlDocument.setParameter("rchrEmployeeId", stremployeeId);
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
				"NegativePayrollProcessReport", false, "", "", "", false, "ad_process",
				strReplaceWith, false, true);
		toolbar.prepareSimpleToolBarTemplate();
		xmlDocument.setParameter("toolbar", toolbar.toString());

		// New interface paramenters
		try {
			WindowTabs tabs = new WindowTabs(this, vars,
					"com.redcarpet.payroll.erpCommon.ad_reports.NegativePayrollProcessReport");

			xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
			xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
			xmlDocument.setParameter("childTabContainer", tabs.childTabs());
			xmlDocument.setParameter("theme", vars.getTheme());
			NavigationBar nav = new NavigationBar(this, vars.getLanguage(),
					"NegativePayrollProcessReport.html", classInfo.id, classInfo.type,
					strReplaceWith, tabs.breadcrumb());
			xmlDocument.setParameter("navigationBar", nav.toString());
			LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(),
					"NegativePayrollProcessReport.html", strReplaceWith);
			xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
				try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_EMP_DEPT_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "NegativePayrollProcessReport"),
					Utility.getContext(this, vars, "#User_Client",
							"NegativePayrollProcessReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"NegativePayrollProcessReport", "");
			xmlDocument.setData("reportRCHR_EMPDEPTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_EMPLOYEE_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "NegativePayrollProcessReport"),
					Utility.getContext(this, vars, "#User_Client",
							"NegativePayrollProcessReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"NegativePayrollProcessReport", "");
			xmlDocument.setData("reportRCHR_EMPLOYEEID", "liststructure",
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
					"NegativePayrollProcessReport", "");
			xmlDocument.setData("reportRCHR_SUBDEPARTMENTID", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		try{
			ComboTableData comboTableData = new ComboTableData(vars, this, 
				"TABLEDIR", "RCHR_ATTD_PROCESS_ID", "", "", Utility.getContext(
						this, vars, "#User_Org", "NegativePayrollProcessReport"),
					Utility.getContext(this, vars, "User_Client",
							"NegativePayrollProcessReport"),0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"NegativePayrollProcessReport", "");
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
		String strdeptId, String strsubdeptId, String stremployeeId, String strempType, String strPayroll, String strPf, String strBank, String strOutput) throws IOException, ServletException {
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/payroll/erpCommon/ad_reports/NegativePayrollProcessReport").createXmlDocument();
	    
		String 	strReportName;
		String  empTypeTemp;
		if(stremployeeId!=""||stremployeeId.length()!=0){
		
		RchrEmployee employee= OBDal.getInstance().get(RchrEmployee.class, stremployeeId);
		empTypeTemp=employee.getEmployeeType();
		if(employee.getEmployeeType().equals(new String("Operator"))){
			if(!strempType.equals("Operator")){
				String language = OBContext.getOBContext().getLanguage().getLanguage();
		        ConnectionProvider conn = new DalConnectionProvider(false);
		        throw new OBException(Utility.messageBD(conn, "Please select Opeartor Type..", language));
		    
			}
		}else{
			if(employee.getEmployeeType().equals("Staff")){
			if(!strempType.equals("Staff")){
				String language = OBContext.getOBContext().getLanguage().getLanguage();
		        ConnectionProvider conn = new DalConnectionProvider(false);
		        throw new OBException(Utility.messageBD(conn, "Please select Staff Type..", language));
		    
				
			}}
			else if(!strempType.equals("Semi Staff")){
				String language = OBContext.getOBContext().getLanguage().getLanguage();
		        ConnectionProvider conn = new DalConnectionProvider(false);
		        throw new OBException(Utility.messageBD(conn, "Please select Semi Staff Type..", language));
		    
				
			}
		}}
		else{
			empTypeTemp=strempType;
		}
		if(strempType.equals("Operator")){
			
			if (strPayroll.equals("payrollDisplay")) {
				//strReportType="Daily Over Time Report";
				if(strBank.equals("Y")){
					strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativeOperatorDisplayBank.jrxml";
				}else{
	            strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativeOperatorDisplayReport.jrxml";}
	        }else if(strPayroll.equals("payrollAccounts")){
			
				strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativeOperatorDisplayAccounts.jrxml";				
			}else {
	        	//strReportType="Payroll Over Time Report";
	        	strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativeOperatorPayrollReport.jrxml";
	        }			
		}
	
		else if(strempType.equals("Staff") || strempType.equals("Semi Staff")){
			
			if (strPayroll.equals("payrollDisplay")) {
				//strReportType="Daily Over Time Report";
				if(strBank.equals("Y")){
					strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativeStaffDisplayBank.jrxml";
				}else{
	            strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativeStaffDisplayReport.jrxml";}
	        }else if(strPayroll.equals("payrollAccounts")){
			
				strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativeStaffDisplayAccounts.jrxml";				
			}else {
	        	//strReportType="Payroll Over Time Report";
	        	strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativeStaffPayrollReport.jrxml";
	        }
			
		}
			
	
		
		else
		{
			//System.out.println("under staff");
		    strReportName = "@basedesign@/com/redcarpet/payroll/erpCommon/ad_reports/NegativePayrollProcessReport.jrxml";
		}
			
    HashMap<String, Object> parameters = new HashMap<String, Object>();
    //System.out.println("processid.."+strPayrollId);
   // System.out.println("employeeid.."+stremployeeId);
   // System.out.println("dept id.."+strdeptId);
    
    parameters.put("ProcessID", strPayrollId);
	parameters.put("Employee_ID", stremployeeId);
	parameters.put("Dept_ID", strdeptId);
	parameters.put("EmpType",empTypeTemp);
	parameters.put("SubDept_ID", strsubdeptId);
	parameters.put("Emp_PF", strPf);
	parameters.put("Emp_Bank", strBank);
    renderJR(vars, response, strReportName, strOutput, parameters, null, null);

	
	}

}
