package com.rcss.humanresource.erpCommon.ad_reports;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;

import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.payroll.data.*;

public class ReportPaySlipsForSettlements extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
	  
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strEmppayrollId = vars.getSessionValue("ReportPaySlipsForSettlements.inprchrEmployeesettlementId_R");
      
      if (strEmppayrollId.equals(""))
        strEmppayrollId = vars.getSessionValue("ReportPaySlipsForSettlements.inprchrEmployeesettlementId");
  
      System.out.println(strEmppayrollId+"---strEmppayrollId");
      if (log4j.isDebugEnabled())
        log4j.debug("+***********************: " + strEmppayrollId);
      	
      printPagePartePDF(response, vars, strEmppayrollId);
    } else
      pageError(response);
  }

  private void printPagePartePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strEmppayrollId) throws IOException, ServletException {
	  
	 
	  if (log4j.isDebugEnabled())

	      log4j.debug("Output: pdf");
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("Id", strEmppayrollId);
	    JasperReport jasperReportLines;
	    
	    try {
	    	 String strBaseDesign=null;
	         strEmppayrollId = strEmppayrollId.replaceAll("\\(|\\)|'", "");
/*	         RCPL_EmpPayrollProcess rcplEmpPayrollProcess = OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, strEmppayrollId);
	         if(rcplEmpPayrollProcess.getEmployee().getEmployeeType().equals("Operator")){
*/		        	 strBaseDesign  = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/ReportPaySlipsForSettlements.jrxml";
	 /*		}else
	 		{
	 			strBaseDesign = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/ReportPaySlipsForStaff.jrxml";
	 		}
	 */        
	          strEmppayrollId = strEmppayrollId.replaceAll("\\(|\\)|'", "");
	          parameters.put("Id", strEmppayrollId);
	          renderJR(vars, response, strBaseDesign, "pdf", parameters, null, null);
	          System.out.println("JOINTSUB-----------------------");

	         
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw new ServletException(e.getMessage());
	    }
  }

	  public String getServletInfo() {
	    return "Servlet that presents the RptInvoice seeker";
	  } 
	}
