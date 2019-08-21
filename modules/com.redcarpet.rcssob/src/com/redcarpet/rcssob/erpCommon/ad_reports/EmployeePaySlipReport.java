package com.redcarpet.rcssob.erpCommon.ad_reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Exception;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;

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
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.util.*;


import java.awt.Desktop;



import org.openbravo.model.procurement.Requisition;


public class EmployeePaySlipReport extends HttpSecureAppServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) {
        super.init(config);
        boolHist = false;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        VariablesSecureApp vars = new VariablesSecureApp(request);

        if (vars.commandIn("DEFAULT")) {
        	
        	String strPeriod = vars.getGlobalVariable("inprchrAttdProcessId", "EmployeePaySlipReport|processId", "");
        	String strempType = vars.getGlobalVariable("inpEmployeeType", "EmployeePaySlipReport|employeetype", "");
        	
            printPage(response, vars, strPeriod, strempType);

        } else if (vars.commandIn("FIND")) {
        	String strPeriod = vars.getRequestGlobalVariable("inprchrAttdProcessId", "EmployeePaySlipReport|processId");
        	String strempType = vars.getRequestGlobalVariable("inpEmployeeType", "EmployeePaySlipReport|employeetype");
            
            printPageHTML(response, vars, strPeriod, strempType);
        } else if (vars.commandIn("PRINT_XLS")) {
        	String strPeriod = vars.getGlobalVariable("inprchrAttdProcessId", "EmployeePaySlipReport|processId");
        	String strempType = vars.getGlobalVariable("inpEmployeeType", "EmployeePaySlipReport|employeetype");
 
            printPagePopUp(request, response, vars, strPeriod, strempType, "xls");
        } else {
            pageErrorPopUp(response);
        }
    }

    private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strPeriod, String strempType) throws IOException, ServletException {

        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/rcssob/erpCommon/ad_reports/EmployeePaySlipReport").createXmlDocument();
        // String strArray = arrayEntry(vars);
        xmlDocument.setParameter("calendar", vars.getLanguage().substring(0, 2));
        xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
        
        
        xmlDocument.setParameter("processId", strPeriod);
		//xmlDocument.setParameter("orgId", strempType);
		xmlDocument.setParameter("rchrEmployeeType", strempType);
		
        //  xmlDocument.setParameter("Normal", strOrder);

        ToolBar toolbar = new ToolBar(this, vars.getLanguage(), "EmployeePaySlipReport", false, "", "", "", false, "ad_reports", strReplaceWith, false, true);
        toolbar.prepareSimpleToolBarTemplate();
        xmlDocument.setParameter("toolbar", toolbar.toString());

        // New interface paramenters
        try {
            WindowTabs tabs = new WindowTabs(this, vars, "com.redcarpet.rcssob.erpCommon.ad_reports.EmployeePaySlipReport");
            xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
            xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
            xmlDocument.setParameter("childTabContainer", tabs.childTabs());
            xmlDocument.setParameter("theme", vars.getTheme());
            NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "EmployeePaySlipReport.html", classInfo.id, classInfo.type, strReplaceWith, tabs.breadcrumb());
            xmlDocument.setParameter("navigationBar", nav.toString());
            LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "EmployeePaySlipReport.html", strReplaceWith);
            xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        
        try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_Attd_Process_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "EmployeePaySlipReport"),
					Utility.getContext(this, vars, "#User_Client",
							"EmployeePaySlipReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"EmployeePaySlipReport", "");
			xmlDocument.setData("reportRCHR_Attd_Process", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

        
        try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"LIST", "", "RCHR_Employee_Type", "", Utility.getContext(
							this, vars, "#User_Org", "EmployeePaySlipReport"),
					Utility.getContext(this, vars, "#User_Client",
							"EmployeeAttendanceSummaryReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"EmployeePaySlipReport", "");
			xmlDocument.setData("reportRCHR_EMPLOYEETYPE", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}


       
        // xmlDocument.setData("structure1", data);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
    }

    private void printPageHTML(HttpServletResponse response, VariablesSecureApp vars,  String strPeriod, String strempType) throws ServletException, IOException {
        if (log4j.isDebugEnabled()) {
            log4j.debug("Output: process EmployeePaySlipReport");
        }
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/rcssob/erpCommon/ad_reports/EmployeePaySlipReport").createXmlDocument();
       
        
       
        EmployeePaySlipReportData[] data = null;
            data = EmployeePaySlipReportData.select(new DalConnectionProvider(), strPeriod, strempType);

      
        xmlDocument.setParameter("calendar", vars.getLanguage().substring(0, 2));
        xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");

        xmlDocument.setParameter("processId", strPeriod);
		//xmlDocument.setParameter("orgId", strempType);
        xmlDocument.setParameter("rchrEmployeeType", strempType);
		
		
        xmlDocument.setParameter("calendar", vars.getLanguage().substring(0, 2));
        xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");

        ToolBar toolbar = new ToolBar(this, vars.getLanguage(), "EmployeePaySlipReport", false, "", "", "", false, "ad_reports", strReplaceWith, false, true);
        toolbar.prepareSimpleToolBarTemplate();
        xmlDocument.setParameter("toolbar", toolbar.toString());

        // New interface paramenters
        try {
            WindowTabs tabs = new WindowTabs(this, vars, "com.redcarpet.rcssob.erpCommon.ad_reports.EmployeePaySlipReport");
            xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
            xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
            xmlDocument.setParameter("childTabContainer", tabs.childTabs());
            xmlDocument.setParameter("theme", vars.getTheme());
            NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "EmployeePaySlipReport.html", classInfo.id, classInfo.type, strReplaceWith, tabs.breadcrumb());
            xmlDocument.setParameter("navigationBar", nav.toString());
            LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "EmployeePaySlipReport.html", strReplaceWith);
            xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"TABLEDIR", "RCHR_Attd_Process_ID", "", "", Utility.getContext(
							this, vars, "#User_Org", "EmployeePaySlipReport"),
					Utility.getContext(this, vars, "#User_Client",
							"EmployeePaySlipReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"EmployeePaySlipReport", "");
			xmlDocument.setData("reportRCHR_Attd_Process", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

        try {
			ComboTableData comboTableData = new ComboTableData(vars, this,
					"LIST", "", "RCHR_Employee_Type", "", Utility.getContext(
							this, vars, "#User_Org", "EmployeePaySlipReport"),
					Utility.getContext(this, vars, "#User_Client",
							"EmployeeAttendanceSummaryReport"), 0);
			Utility.fillSQLParameters(this, vars, null, comboTableData,
					"EmployeePaySlipReport", "");
			xmlDocument.setData("reportRCHR_EMPLOYEETYPE", "liststructure",
					comboTableData.select(false));
			comboTableData = null;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

        xmlDocument.setData("structure1", data);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
    }

    private void printPagePopUp(HttpServletRequest request, HttpServletResponse response, VariablesSecureApp vars, String strPeriod, String strempType, String strOutput) throws IOException, ServletException {
     
    	    	EmployeePaySlipReportData[] data = null;
    	        

    	        data = EmployeePaySlipReportData.select(new DalConnectionProvider(), strPeriod, strempType);

    	    	
    	    	  try {
    	    		  Connection connection = OBDal.getInstance().getSession().connection();
    	              PreparedStatement psmnt = null;
    	              Statement st = connection.createStatement(); 
    	              String sql = "SELECT (SELECT rchr_attd_process.monthname FROM rchr_attd_process WHERE rchr_attd_process.rchr_attd_process_id=pp.rchr_attd_process_id) AS payrollmonth,"+
    	            		 " emp.punchno AS punchno,emp.employeename AS name,"+
    	            		 " (SELECT rchr_designation.name FROM rchr_designation WHERE rchr_designation_id=emp.rchr_designation_id ) AS designation,"+
    	            		 " (CASE WHEN 'Y'=(SELECT RCPL_EMPSALSETUP.isnotdesig from RCPL_EMPSALSETUP WHERE RCPL_EMPSALSETUP.RCHR_EMPLOYEE_ID=emp.rchr_employee_id)"+
    	            		 " THEN (select RCPL_EMPSALSETUP.PERDAYBASIC from RCPL_EMPSALSETUP where RCPL_EMPSALSETUP.RCHR_EMPLOYEE_ID=emp.rchr_employee_id)"+
    	            		 " ELSE(emppp.basicamount)END) as basic,"+
    	            		 " (CASE WHEN 'Y'=(SELECT RCPL_EMPSALSETUP.isnotdesig from RCPL_EMPSALSETUP WHERE RCPL_EMPSALSETUP.RCHR_EMPLOYEE_ID=emp.rchr_employee_id)"+
    	            		 " THEN (select RCPL_EMPSALSETUP.SERVICEINCE from RCPL_EMPSALSETUP where RCPL_EMPSALSETUP.RCHR_EMPLOYEE_ID=emp.rchr_employee_id)ELSE"+
    	            		 " (emppp.servincentiveamount ) END ) as serviceincv,"+
    	            		" ((CASE WHEN 'Y'=(SELECT RCPL_EMPSALSETUP.isnotdesig from RCPL_EMPSALSETUP WHERE RCPL_EMPSALSETUP.RCHR_EMPLOYEE_ID=emp.rchr_employee_id)"+
    	            		" THEN (select RCPL_EMPSALSETUP.PERDAYBASIC from RCPL_EMPSALSETUP where RCPL_EMPSALSETUP.RCHR_EMPLOYEE_ID=emp.rchr_employee_id)ELSE"+
    	            		" (emppp.basicamount)END)+(CASE WHEN 'Y'=(SELECT RCPL_EMPSALSETUP.isnotdesig from RCPL_EMPSALSETUP WHERE RCPL_EMPSALSETUP.RCHR_EMPLOYEE_ID=emp.rchr_employee_id)"+
    	            		" THEN (select RCPL_EMPSALSETUP.SERVICEINCE from RCPL_EMPSALSETUP where RCPL_EMPSALSETUP.RCHR_EMPLOYEE_ID=emp.rchr_employee_id)ELSE"+
    	            		" (emppp.servincentiveamount ) END )) as wageperday,"+
    	            		" (SELECT RCHR_EMPLOYEE.PREATTDDAYS FROM RCHR_EMPLOYEE WHERE RCHR_EMPLOYEE_ID=emp.rchr_employee_id)- emppp.presentdays as incrdays,"+
    	            		" (SELECT RCHR_EMPLOYEE.PREATTDDAYS FROM RCHR_EMPLOYEE WHERE RCHR_EMPLOYEE_ID=emp.rchr_employee_id) AS servicedays,"+
    	            		" (SELECT RCPL_EmpPayHead.additions FROM RCPL_EmpPayHead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPayHead.rcpl_emppayrollprocess_id AND"+
    	            		" RCPL_EmpPayHead.rcpl_payadditionshead_id=(SELECT rcpl_payadditionshead_id FROM"+
    	            		" rcpl_payadditionshead WHERE category='Basic')) AS basicamnt,"+
    	            		" ((SELECT RCPL_EmpPayHead.additions FROM RCPL_EmpPayHead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPayHead.rcpl_emppayrollprocess_id AND"+
    	            		" RCPL_EmpPayHead.rcpl_payadditionshead_id=(SELECT rcpl_payadditionshead_id FROM"+
    	            		" rcpl_payadditionshead WHERE category='Basic'))+(SELECT RCPL_EmpPayIncentives.additions FROM RCPL_EmpPayIncentives WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPayIncentives.rcpl_emppayrollprocess_id AND"+
    	            		" RCPL_EmpPayIncentives.rcpl_Incentives_id=(SELECT rcpl_incentives_id FROM"+
    	                    " rcpl_incentives WHERE rcpl_incentives.rcpl_variableset_id=(SELECT rcpl_variableset_id from rcpl_variableset where isservicedaysincentive='Y')))) AS grosswages,"+
    	                    " emppp.presentdays AS presentdays,emppp.weeklyoff AS weekoffdays,emppp.absents AS absents,"+
    	                    " (SELECT RCPL_EmpPayIncentives.additions FROM RCPL_EmpPayIncentives WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPayIncentives.rcpl_emppayrollprocess_id AND"+
    	                    " RCPL_EmpPayIncentives.rcpl_Incentives_id=(SELECT rcpl_incentives_id FROM"+
    	                    " rcpl_incentives WHERE rcpl_incentives.rcpl_variableset_id=(SELECT rcpl_variableset_id from rcpl_variableset where isattendanceprize='Y'))) AS attndincv,"+
    	                    " (SELECT RCPL_EmpPayIncentives.additions FROM RCPL_EmpPayIncentives WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPayIncentives.rcpl_emppayrollprocess_id AND"+
    	                    " RCPL_EmpPayIncentives.rcpl_Incentives_id=(SELECT rcpl_incentives_id FROM"+
    	                    " rcpl_incentives WHERE rcpl_incentives.rcpl_variableset_id=(SELECT rcpl_variableset_id from rcpl_variableset where isprodincentive='Y'))) AS prodincntv,"+
    	                    " (SELECT RCPL_EmpDedIncentives.deductions FROM RCPL_EmpDedIncentives WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpDedIncentives.rcpl_emppayrollprocess_id AND"+
    	                    " RCPL_EmpDedIncentives.rcpl_Incentives_id=(SELECT rcpl_incentives_id FROM"+
    	                    " rcpl_incentives WHERE rcpl_incentives.rcpl_variableset_id=(SELECT rcpl_variableset_id from rcpl_variableset where ismessdeduction='Y'))) AS messsubsidy,"+
    	                    " (SELECT RCPL_EmpPayIncentives.additions FROM RCPL_EmpPayIncentives WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPayIncentives.rcpl_emppayrollprocess_id AND RCPL_EmpPayIncentives.rcpl_Incentives_id=(SELECT rcpl_incentives_id FROM"+
    	                    " rcpl_incentives WHERE rcpl_incentives.rcpl_variableset_id=(SELECT RCPL_Variableset_id FROM RCPL_Variableset where iscshiftincen='Y'))) AS punchamount,emppp.grandtotal as grandtotal,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions from RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id AND RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM"+
    	                    " rcpl_paydeducitionshead WHERE category='PF')) AS pf,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions FROM RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id AND"+
    	                    " RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM rcpl_paydeducitionshead WHERE category='Mess Bill')) AS mesbill,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions FROM RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id  AND RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM"+
    	                    " rcpl_paydeducitionshead WHERE category='Electricity Bill')) AS electricitybill,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions from RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id AND RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM"+
    	                    " rcpl_paydeducitionshead WHERE category='Rent Bill')) AS rentBill,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions from RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id AND RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM"+
    	                    " rcpl_paydeducitionshead WHERE category='Gas')) AS gasrecovery,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions from RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id AND RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM"+
    	                    " rcpl_paydeducitionshead WHERE category='Employee Fine')) AS fine,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions FROM RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id AND"+
    	                    " RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM"+
    	                    " rcpl_paydeducitionshead WHERE category='Loans and Advances')) AS advances,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions FROM RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id AND"+
    	                    " RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM"+
    	                    " rcpl_paydeducitionshead WHERE category='Store Recovery')) AS deptstore,"+
    	                    " (SELECT RCPL_EmpPaydead.deductions FROM RCPL_EmpPaydead WHERE emppp.rcpl_emppayrollprocess_id=RCPL_EmpPaydead.rcpl_emppayrollprocess_id AND"+
    	                    " RCPL_EmpPaydead.rcpl_paydeducitionshead_id=(SELECT rcpl_paydeducitionshead_id FROM"+
    	                    " rcpl_paydeducitionshead WHERE category='Security Deposit')) AS securitydeposite,"+
    	                    " emppp.deductionsum AS totaldeduction,emppp.totalpay AS netsalary FROM rcpl_payrollprocess pp"+
    	                    " left join rcpl_emppayrollprocess emppp ON emppp.rcpl_payrollprocess_id = pp.rcpl_payrollprocess_id"+
    	                    " left join rchr_employee emp on emp.rchr_employee_id=emppp.rchr_employee_id"+
    	                    " where pp.rchr_attd_process_id='"+strPeriod+"' AND emp.employeetype='"+strempType+"'" +
    	                    " ORDER BY emp.documentno";

    	              
    	              //String sql="SELECT (SELECT Name FROM ad_org where ad_org_id  = A.ad_org_id ) As Org,(SELECT Name FROM C_Doctype where C_Doctype_id = A.C_Doctype_id ) as DocumentType,A.DocumentNo,A.em_epcg_type as FormType,Em_epcg_formtypeno As FormTypeNo,to_char(A.Dateinvoiced,'Mon-dd-YYYY') As InvoiceDate,to_char(A.dateAcct,'Mon-dd-YYYY')  as AccoutingDate,(SELECT Name FROm c_bpartner Where  c_bpartner_id = A.c_bpartner_id limit 1) As PartyName,(SELECT Name FROm c_bpartner Where  em_epcg_agent = A.c_bpartner_id limit 1) As AgentName,(select name from C_PaymentTerm where C_PaymentTerm_ID=A.C_PaymentTerm_ID) as Paymentterm,B.Line,(SELECT Name FROm m_product Where  m_product_id = B.m_product_id) As Product,(SELECT Name FROm m_product_category Where m_product_category_id = (select m_product_category_id from m_product where m_product_id = B.m_product_id))As ProductCat,B.EM_Epcg_Prolistalias as ProductAlias, B.qtyinvoiced,B.priceactual,A.em_rcfr_ratetype,B.EM_Rcfr_Netunitrate,A.Description,A.Poreference,A.Grandtotal,A.ispaid,A.Totalpaid,A.OutstandingAmt,A.Totallines,(A.Grandtotal-A.Totallines)as TaxAmount,A.em_rcfr_freighth As FreightAmount,A.em_rcfr_freightpaid as FreightPaid,em_rcob_commission as Commission,(SELECT  C_Invoiceline.linenetamt FROM C_Invoiceline where C_Invoiceline.account_id = 'EFF09991F7BB4C9B92AC9F1B2A76851A' and C_Invoiceline.C_Invoice_id = A.C_Invoice_id)/(SELECT Count(*) FROM C_Invoiceline where C_Invoiceline.financial_invoice_line = 'N' and C_Invoiceline.C_Invoice_id = A.C_Invoice_id)  As FreightLine,em_rcfr_insuranceh As Insurance,(SELECT  C_Invoiceline.linenetamt FROM C_Invoiceline where C_Invoiceline.account_id = 'D0CBBC60923D4664936B6F8298F5F908' and C_Invoiceline.C_Invoice_id = A.C_Invoice_id)/(SELECT Count(*) FROM C_Invoiceline where C_Invoiceline.financial_invoice_line = 'N' and C_Invoiceline.C_Invoice_id = A.C_Invoice_id) As InsuranceAmount,A.Em_epcg_locdelivery  as DeliveryLocation FROM c_invoice  A, c_invoiceline B Where  A.c_invoice_id = B.c_invoice_id And A.issotrx = 'Y' And A.Docstatus = 'CO'  And B.m_product_id in (SELECT m_product_id FROm m_product) And A.dateinvoiced >=to_date('" + strDateFrom + "') and A.dateinvoiced <=to_date('" + strDateTo + "') Order by A.Dateinvoiced,B.Line" ;
    	             // System.out.println("query is " +sql);
    	              
    	              ResultSet rs = st.executeQuery(sql);

    	              HSSFWorkbook wb = new HSSFWorkbook();
    	              HSSFSheet sheet = wb.createSheet("Excel Sheet");
    	              HSSFRow rowhead = sheet.createRow((short) 0);
    	              rowhead.createCell((short) 0).setCellValue("Payroll Month");
    	              rowhead.createCell((short) 1).setCellValue("PunchNo");
    	              rowhead.createCell((short) 2).setCellValue("Name");
    	              rowhead.createCell((short) 3).setCellValue("Designation");
    	            
    	              rowhead.createCell((short) 4).setCellValue("Basic");
    	              rowhead.createCell((short) 5).setCellValue("ServiceIncv");
    	              rowhead.createCell((short) 6).setCellValue("Wageperday");
    	              rowhead.createCell((short) 7).setCellValue("Incr.Days");
    	            
    	              rowhead.createCell((short) 8).setCellValue("ServiceDays");
    	              rowhead.createCell((short) 9).setCellValue("Basic Amnt");
    	              rowhead.createCell((short) 10).setCellValue("Gross Wages");
    	              rowhead.createCell((short) 11).setCellValue("Present Days");
    	              rowhead.createCell((short) 12).setCellValue("Weekoff Days");
    	              rowhead.createCell((short) 13).setCellValue("Absents");
    	             
    	              
    	              rowhead.createCell((short) 14).setCellValue("Attnd Incv");
    	              rowhead.createCell((short) 15).setCellValue("Prod Incntv");
    	            
    	              rowhead.createCell((short) 16).setCellValue("MessSubsidy");
    	              rowhead.createCell((short) 17).setCellValue("Punch Amount");
    	              rowhead.createCell((short) 18).setCellValue("Grand Total");
    	              rowhead.createCell((short) 19).setCellValue("PF");
    	              rowhead.createCell((short) 20).setCellValue("Mess Bill");
    	            
    	              rowhead.createCell((short) 21).setCellValue("ElectricityBill");
    	              rowhead.createCell((short) 22).setCellValue("RentBill");
    	              rowhead.createCell((short) 23).setCellValue("GasRecovery");
    	              rowhead.createCell((short) 24).setCellValue("Fine");
    	              rowhead.createCell((short) 25).setCellValue("Advances");
    	            
    	              rowhead.createCell((short) 26).setCellValue("Dept Store");
    	              rowhead.createCell((short) 27).setCellValue("Security Deposite");
    	              rowhead.createCell((short) 28).setCellValue("TotalDeduction");
    	              rowhead.createCell((short) 29).setCellValue("Net Salary");
    	              

    	              int index = 1;
    	              while (rs.next()) {

    	                      HSSFRow row = sheet.createRow((short) index);
    	                      row.createCell((short) 0).setCellValue(rs.getString(1));
    	                      row.createCell((short) 1).setCellValue(rs.getString(2));
    	                      row.createCell((short) 2).setCellValue(rs.getString(3));
    	                      row.createCell((short) 3).setCellValue(rs.getString(4));
    	                      row.createCell((short) 4).setCellValue(rs.getString(5));
    	                      row.createCell((short) 5).setCellValue(rs.getString(6));
    	                      row.createCell((short) 6).setCellValue(rs.getString(7));
    	                      row.createCell((short) 7).setCellValue(rs.getString(8));
    	                      row.createCell((short) 8).setCellValue(rs.getString(9));
    	                      row.createCell((short) 9).setCellValue(rs.getString(10));
    	                      row.createCell((short) 10).setCellValue(rs.getString(11));
    	                      row.createCell((short) 11).setCellValue(rs.getString(12));
    	                      row.createCell((short) 12).setCellValue(rs.getString(13));
    	                      row.createCell((short) 13).setCellValue(rs.getString(14));
    	                      row.createCell((short) 14).setCellValue(rs.getString(15));
    	                      row.createCell((short) 15).setCellValue(rs.getString(16));
    	                      row.createCell((short) 16).setCellValue(rs.getString(17));
    	                      row.createCell((short) 17).setCellValue(rs.getString(18));
    	                      row.createCell((short) 18).setCellValue(rs.getString(19));
    	                      row.createCell((short) 19).setCellValue(rs.getString(20));
    	                      row.createCell((short) 20).setCellValue(rs.getString(21));
    	                      row.createCell((short) 21).setCellValue(rs.getString(22));
    	                      row.createCell((short) 22).setCellValue(rs.getString(23));
    	                      row.createCell((short) 23).setCellValue(rs.getString(24));
    	                      row.createCell((short) 24).setCellValue(rs.getString(25));
    	                      row.createCell((short) 25).setCellValue(rs.getString(26));
    	                      row.createCell((short) 26).setCellValue(rs.getString(27));
    	                      row.createCell((short) 27).setCellValue(rs.getString(28));
    	                      row.createCell((short) 28).setCellValue(rs.getString(29));
    	                      row.createCell((short) 29).setCellValue(rs.getString(30));
    	                     
    	                      index++;
    	              }
                     /*String filen = System.getProperty("catalina.base")+"/EmployeePaySlipReport.xls";     

    	              
    	              File file = new File(filen);
    	              FileOutputStream fileOut = new FileOutputStream(file);
    	              wb.write(fileOut);
    	             
    	              fileOut.close();
    	              System.out.println("Data is saved in excel file.");
    	              
    	              Desktop.getDesktop().browse(file.toURI());  
    	             // Desktop.getDesktop().open(new File("c:\\EmployeePaySlipReport.xls"));
    	              response.setContentType("text/xls; charset=UTF-8");*/
    	              
    	              response.setContentType("application/vnd.ms-excel");
    	              wb.write(response.getOutputStream());
    	              response.getOutputStream().close();
    	              rs.close();
    	              connection.close();
    	              
    	      } catch (Exception e) {
    	      }
    	    }
    	}
