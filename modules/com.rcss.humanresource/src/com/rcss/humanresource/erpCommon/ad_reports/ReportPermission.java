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
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;

public class ReportPermission extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strPermissionId = vars.getSessionValue("ReportPermission.inprchrPermissionId_R");
      if (strPermissionId.equals(""))
        strPermissionId = vars.getSessionValue("ReportPermission.inprchrPermissionId");
      System.out.println(strPermissionId+"---strPermissionId");
      if (log4j.isDebugEnabled())
        log4j.debug("+***********************: " + strPermissionId);
      printPagePartePDF(response, vars, strPermissionId);
    } else
      pageError(response);
  }

  private void printPagePartePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strPermissionId) throws IOException, ServletException {
	  if (log4j.isDebugEnabled())

	      log4j.debug("Output: pdf");
	    String strBaseDesign = getBaseDesignPath(vars.getLanguage());
	    System.out.println("strBaseDesign***********" +strBaseDesign);
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("Permission_ID", strPermissionId);
	    JasperReport jasperReportLines;
	    
	    try {
	         strPermissionId = strPermissionId.replaceAll("\\(|\\)|'", "");
	       
	                
	          JasperDesign jasperDesignLines = JRXmlLoader.load(strBaseDesign
	          + "/com/rcss/humanresource/erpCommon/ad_reports/ReportPermission.jrxml");
	          jasperReportLines = JasperCompileManager.compileReport(jasperDesignLines);
	          strPermissionId = strPermissionId.replaceAll("\\(|\\)|'", "");
	          //parameters.put("SR_LINES", jasperReportLines);
	          parameters.put("Permission_ID", strPermissionId);
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
