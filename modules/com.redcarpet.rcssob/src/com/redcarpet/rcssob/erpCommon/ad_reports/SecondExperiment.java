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

//import com.redcarpet.rcssob.erpCommon.ad_reports.SQLExecutorExpData;

public class SecondExperiment extends HttpSecureAppServlet {

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
        	
        	String strDateFrom = vars.getGlobalVariable("inpDateFrom", "SecondExperiment|DateFrom", "");
            String strDateTo = vars.getGlobalVariable("inpDateTo", "SecondExperiment|DateTo", "");

            printPage(response, vars, strDateFrom, strDateTo);

        } else if (vars.commandIn("FIND")) {
        	String strDateFrom = vars.getGlobalVariable("inpDateFrom", "SecondExperiment|DateFrom");
            String strDateTo = vars.getGlobalVariable("inpDateTo", "SecondExperiment|DateTo");
            
            printPageHTML(response, vars, strDateFrom, strDateTo);
        } else if (vars.commandIn("PRINT_XLS")) {
        	String strDateFrom = vars.getGlobalVariable("inpDateFrom", "SecondExperiment|DateFrom");
            String strDateTo = vars.getGlobalVariable("inpDateTo", "SecondExperiment|DateTo");
 
            printPagePopUp(request, response, vars, strDateFrom, strDateTo, "xls");
        } else {
            pageErrorPopUp(response);
        }
    }

    private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strDateFrom, String strDateTo) throws IOException, ServletException {

        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/rcssob/erpCommon/ad_reports/SecondExperiment").createXmlDocument();
        // String strArray = arrayEntry(vars);
        xmlDocument.setParameter("calendar", vars.getLanguage().substring(0, 2));
        xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
        
        
        xmlDocument.setParameter("dateFrom", strDateFrom);
		xmlDocument.setParameter("dateTo", strDateTo);
		
		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
        //  xmlDocument.setParameter("Normal", strOrder);

        ToolBar toolbar = new ToolBar(this, vars.getLanguage(), "SecondExperiment", false, "", "", "", false, "ad_reports", strReplaceWith, false, true);
        toolbar.prepareSimpleToolBarTemplate();
        xmlDocument.setParameter("toolbar", toolbar.toString());

        // New interface paramenters
        try {
            WindowTabs tabs = new WindowTabs(this, vars, "com.redcarpet.rcssob.erpCommon.ad_reports.SecondExperiment");
            xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
            xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
            xmlDocument.setParameter("childTabContainer", tabs.childTabs());
            xmlDocument.setParameter("theme", vars.getTheme());
            NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "SecondExperiment.html", classInfo.id, classInfo.type, strReplaceWith, tabs.breadcrumb());
            xmlDocument.setParameter("navigationBar", nav.toString());
            LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "SecondExperiment.html", strReplaceWith);
            xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

       
        // xmlDocument.setData("structure1", data);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
    }

    private void printPageHTML(HttpServletResponse response, VariablesSecureApp vars,  String strDateFrom, String strDateTo) throws ServletException, IOException {
        if (log4j.isDebugEnabled()) {
            log4j.debug("Output: process SecondExperiment");
        }
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/rcssob/erpCommon/ad_reports/SecondExperiment").createXmlDocument();
       
        
       
        SecondExperimentData[] data = null;
        

            data = SecondExperimentData.select(new DalConnectionProvider(), strDateFrom, strDateTo);

      
        xmlDocument.setParameter("calendar", vars.getLanguage().substring(0, 2));
        xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");

        xmlDocument.setParameter("dateFrom", strDateFrom);
		xmlDocument.setParameter("dateTo", strDateTo);
		
		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		
		
        xmlDocument.setParameter("calendar", vars.getLanguage().substring(0, 2));
        xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");

        ToolBar toolbar = new ToolBar(this, vars.getLanguage(), "SecondExperiment", false, "", "", "", false, "ad_reports", strReplaceWith, false, true);
        toolbar.prepareSimpleToolBarTemplate();
        xmlDocument.setParameter("toolbar", toolbar.toString());

        // New interface paramenters
        try {
            WindowTabs tabs = new WindowTabs(this, vars, "com.redcarpet.rcssob.erpCommon.ad_reports.SecondExperiment");
            xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
            xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
            xmlDocument.setParameter("childTabContainer", tabs.childTabs());
            xmlDocument.setParameter("theme", vars.getTheme());
            NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "SecondExperiment.html", classInfo.id, classInfo.type, strReplaceWith, tabs.breadcrumb());
            xmlDocument.setParameter("navigationBar", nav.toString());
            LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "SecondExperiment.html", strReplaceWith);
            xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
        } catch (Exception ex) {
            throw new ServletException(ex);
        }


        xmlDocument.setData("structure1", data);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
    }

    private void printPagePopUp(HttpServletRequest request, HttpServletResponse response, VariablesSecureApp vars, String strDateFrom, String strDateTo, String strOutput) throws IOException, ServletException {
     
    	    	SecondExperimentData[] data = null;
    	        

    	        data = SecondExperimentData.select(new DalConnectionProvider(), strDateFrom, strDateTo);

    	    	
    	    	  try {
    	    		  Connection connection = OBDal.getInstance().getSession().connection();
    	              PreparedStatement psmnt = null;
    	              Statement st = connection.createStatement();
    	              String sql="SELECT (SELECT Name FROM ad_org where ad_org_id  = A.ad_org_id ) As Org,(SELECT Name FROM C_Doctype where C_Doctype_id = A.C_Doctype_id ) as DocumentType,A.DocumentNo,A.em_epcg_type as FormType,Em_epcg_formtypeno As FormTypeNo,to_char(A.Dateinvoiced,'Mon-dd-YYYY') As InvoiceDate,to_char(A.dateAcct,'Mon-dd-YYYY')  as AccoutingDate,(SELECT Name FROm c_bpartner Where  c_bpartner_id = A.c_bpartner_id limit 1) As PartyName,(SELECT Name FROm c_bpartner Where  em_epcg_agent = A.c_bpartner_id limit 1) As AgentName,(select name from C_PaymentTerm where C_PaymentTerm_ID=A.C_PaymentTerm_ID) as Paymentterm,B.Line,(SELECT Name FROm m_product Where  m_product_id = B.m_product_id) As Product,(SELECT Name FROm m_product_category Where m_product_category_id = (select m_product_category_id from m_product where m_product_id = B.m_product_id))As ProductCat,B.EM_Epcg_Prolistalias as ProductAlias, B.qtyinvoiced,B.priceactual,A.em_rcfr_ratetype,B.EM_Rcfr_Netunitrate,A.Description,A.Poreference,A.Grandtotal,A.ispaid,A.Totalpaid,A.OutstandingAmt,A.Totallines,(A.Grandtotal-A.Totallines)as TaxAmount,A.em_rcfr_freighth As FreightAmount,A.em_rcfr_freightpaid as FreightPaid,em_rcob_commission as Commission,(SELECT  C_Invoiceline.linenetamt FROM C_Invoiceline where C_Invoiceline.account_id = 'EFF09991F7BB4C9B92AC9F1B2A76851A' and C_Invoiceline.C_Invoice_id = A.C_Invoice_id)/(SELECT Count(*) FROM C_Invoiceline where C_Invoiceline.financial_invoice_line = 'N' and C_Invoiceline.C_Invoice_id = A.C_Invoice_id)  As FreightLine,em_rcfr_insuranceh As Insurance,(SELECT  C_Invoiceline.linenetamt FROM C_Invoiceline where C_Invoiceline.account_id = 'D0CBBC60923D4664936B6F8298F5F908' and C_Invoiceline.C_Invoice_id = A.C_Invoice_id)/(SELECT Count(*) FROM C_Invoiceline where C_Invoiceline.financial_invoice_line = 'N' and C_Invoiceline.C_Invoice_id = A.C_Invoice_id) As InsuranceAmount,A.Em_epcg_locdelivery  as DeliveryLocation FROM c_invoice  A, c_invoiceline B Where  A.c_invoice_id = B.c_invoice_id And A.issotrx = 'Y' And A.Docstatus = 'CO'  And B.m_product_id in (SELECT m_product_id FROm m_product) And A.dateinvoiced >=to_date('" + strDateFrom + "') and A.dateinvoiced <=to_date('" + strDateTo + "') Order by A.Dateinvoiced,B.Line" ;
    	              //System.out.println("query is " +sql);
    	              
    	              ResultSet rs = st.executeQuery(sql);

    	              HSSFWorkbook wb = new HSSFWorkbook();
    	              HSSFSheet sheet = wb.createSheet("Excel Sheet");
    	              HSSFRow rowhead = sheet.createRow((short) 0);
    	              rowhead.createCell((short) 0).setCellValue("Org");
    	              rowhead.createCell((short) 1).setCellValue("Document Type");
    	              rowhead.createCell((short) 2).setCellValue("Document No.");
    	              rowhead.createCell((short) 3).setCellValue("Form Type");
    	            
    	              rowhead.createCell((short) 4).setCellValue("Form Type No");
    	              rowhead.createCell((short) 5).setCellValue("Invoice Date");
    	              rowhead.createCell((short) 6).setCellValue("Accouting Date");
    	              rowhead.createCell((short) 7).setCellValue("Party Name");
    	            
    	              rowhead.createCell((short) 8).setCellValue("Agent Name");
    	              rowhead.createCell((short) 9).setCellValue("Payment Term");
    	              rowhead.createCell((short) 10).setCellValue("Line");
    	              rowhead.createCell((short) 11).setCellValue("Product");
    	              rowhead.createCell((short) 12).setCellValue("Product Category");
    	              rowhead.createCell((short) 13).setCellValue("Product Alias");
    	             
    	              
    	              rowhead.createCell((short) 14).setCellValue("Qty Invoiced");
    	              rowhead.createCell((short) 15).setCellValue("Price Actual");
    	            
    	              rowhead.createCell((short) 16).setCellValue("Rate Type");
    	              rowhead.createCell((short) 17).setCellValue("Net Rate");
    	              rowhead.createCell((short) 18).setCellValue("Description");
    	              rowhead.createCell((short) 19).setCellValue("Poreference");
    	              rowhead.createCell((short) 20).setCellValue("Grandtotal");
    	            
    	              rowhead.createCell((short) 21).setCellValue("Ispaid");
    	              rowhead.createCell((short) 22).setCellValue("Total Paid");
    	              rowhead.createCell((short) 23).setCellValue("Pending Amount");
    	              rowhead.createCell((short) 24).setCellValue("Total Lines");
    	              rowhead.createCell((short) 25).setCellValue("Tax Amount");
    	            
    	              rowhead.createCell((short) 26).setCellValue("Freight Amount");
    	              rowhead.createCell((short) 27).setCellValue("Freight Paid");
    	              rowhead.createCell((short) 28).setCellValue("Commission %");
    	              rowhead.createCell((short) 29).setCellValue("FreightLine");
    	              rowhead.createCell((short) 30).setCellValue("Insurance %");
    	              rowhead.createCell((short) 31).setCellValue("Insurance Amount");
    	              rowhead.createCell((short) 32).setCellValue("Delivery Location");
    	              

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
    	                      row.createCell((short) 30).setCellValue(rs.getString(31));
    	                      row.createCell((short) 31).setCellValue(rs.getString(32));
    	                      row.createCell((short) 32).setCellValue(rs.getString(33));
    	                     
    	                      index++;
    	              }
    	             /* FileOutputStream fileOut = new FileOutputStream("c:\\SalesRegister.xls");
    	              wb.write(fileOut);
    	              fileOut.close();
    	              System.out.println("Data is saved in excel file.");
    	              rs.close();
    	              connection.close();
    	              
    	              Desktop.getDesktop().open(new File("c:\\SalesRegister.xls"));
    	              response.setContentType("text/xls; charset=UTF-8"); */
					  
					  response.setContentType("application/vnd.ms-excel");
    	              wb.write(response.getOutputStream());
    	              response.getOutputStream().close();
    	              rs.close();
    	              connection.close();
    	              
    	      } catch (Exception e) {
    	      }
    	    }
    	}
