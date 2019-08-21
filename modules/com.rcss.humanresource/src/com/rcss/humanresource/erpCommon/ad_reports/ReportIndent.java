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

public class ReportIndent extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strmRequisitionId = vars.getSessionValue("ReportIndent.inpmRequisitionId_R");
      if (strmRequisitionId.equals(""))
        strmRequisitionId = vars.getSessionValue("ReportIndent.inpmRequisitionId");
      System.out.println(strmRequisitionId+"---strmRequisitionId");
      if (log4j.isDebugEnabled())
        log4j.debug("+***********************: " + strmRequisitionId);
      printPagePartePDF(response, vars, strmRequisitionId);
    } else
      pageError(response);
  }

  private void printPagePartePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strmRequisitionId) throws IOException, ServletException {
	  if (log4j.isDebugEnabled())

	      log4j.debug("Output: pdf");
	    String strBaseDesign = getBaseDesignPath(vars.getLanguage());
	    System.out.println("strBaseDesign***********" +strBaseDesign);
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("M_REQUISITION_ID", strmRequisitionId);
	    JasperReport jasperReportLines;
	    
	    try {
	         strmRequisitionId = strmRequisitionId.replaceAll("\\(|\\)|'", "");
	       
	                
	          JasperDesign jasperDesignLines = JRXmlLoader.load(strBaseDesign
	          + "/com/rcss/humanresource/erpCommon/ad_reports/ReportIndent.jrxml");
	          jasperReportLines = JasperCompileManager.compileReport(jasperDesignLines);
	          strmRequisitionId = strmRequisitionId.replaceAll("\\(|\\)|'", "");
	          //parameters.put("SR_LINES", jasperReportLines);
	          parameters.put("M_REQUISITION_ID", strmRequisitionId);
	          renderJR(vars, response, null, "pdf", parameters, null, null);
	          System.out.println("JOINTSUB-----------------------");

	         
	    } catch (JRException e) {
	      e.printStackTrace();
	      throw new ServletException(e.getMessage());
	    }


	  }

	  public String getServletInfo() {
	    return "Servlet that presents the RptInvoice seeker";
	  } // End of getServletInfo() method
	}
