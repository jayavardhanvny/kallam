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
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;

public class ReportPaySlips extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
	  
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strEmppayrollId = vars.getSessionValue("ReportPaySlips.inprcplEmppayrollprocessId_R");
      
      if (strEmppayrollId.equals(""))
        strEmppayrollId = vars.getSessionValue("ReportPaySlips.inprcplEmppayrollprocessId");
      	//stremployeeId = vars.getSessionValue("ReportPayslips.inprchrEmployeeId");
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
	    //String strBaseDesign = getBaseDesignPath(vars.getLanguage());
	  	
	   // System.out.println("strBaseDesign***********" +strBaseDesign);
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("Id", strEmppayrollId);
	    JasperReport jasperReportLines;
	    
	    try {
	    	 String strBaseDesign=null;
	         strEmppayrollId = strEmppayrollId.replaceAll("\\(|\\)|'", "");
	         RCPL_EmpPayrollProcess rcplEmpPayrollProcess = OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, strEmppayrollId);
	         if(rcplEmpPayrollProcess.getEmployee().getEmployeeType().equals("Operator")){
	 			//System.out.println("under operator");
	        	 strBaseDesign  = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/ReportPaySlipsForOperators.jrxml";
	 		}else
	 		{
	 			//System.out.println("under staff");
	 			strBaseDesign = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/ReportPaySlipsForStaff.jrxml";
	 		}
	         
	         // JasperDesign jasperDesignLines = JRXmlLoader.load(strBaseDesign);
	         //jasperReportLines = JasperCompileManager.compileReport(jasperDesignLines);
	          strEmppayrollId = strEmppayrollId.replaceAll("\\(|\\)|'", "");
	          //p/arameters.put("SR_LINES", jasperReportLines);
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
	  } // End of getServletInfo() method
	}
