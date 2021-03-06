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

public class ReportPurchaseOrder extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strcOrderId = vars.getSessionValue("ReportPurchaseOrder.inpcOrderId_R");
      if (strcOrderId.equals(""))
        strcOrderId = vars.getSessionValue("ReportPurchaseOrder.inpcOrderId");
      System.out.println(strcOrderId+"---strcOrderId");
      if (log4j.isDebugEnabled())
        log4j.debug("+***********************: " + strcOrderId);
      printPagePartePDF(response, vars, strcOrderId);
    } else
      pageError(response);
  }

  private void printPagePartePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strcOrderId) throws IOException, ServletException {
	  if (log4j.isDebugEnabled())

	      log4j.debug("Output: pdf");
	    String strBaseDesign = getBaseDesignPath(vars.getLanguage());
	    System.out.println("strBaseDesign***********" +strBaseDesign);
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("C_ORDER_ID", strcOrderId);
	    JasperReport jasperReportLines;
	    
	    try {
	         strcOrderId = strcOrderId.replaceAll("\\(|\\)|'", "");
	       
	                
	          JasperDesign jasperDesignLines = JRXmlLoader.load(strBaseDesign
	          + "/com/rcss/humanresource/erpCommon/ad_reports/ReportPurchaseOrder.jrxml");
	          jasperReportLines = JasperCompileManager.compileReport(jasperDesignLines);
	          strcOrderId = strcOrderId.replaceAll("\\(|\\)|'", "");
	          //parameters.put("SR_LINES", jasperReportLines);
	          parameters.put("C_ORDER_ID", strcOrderId);
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
