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

public class Visitorgatepass extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strPermissionId = vars.getSessionValue("Visitorgatepass.inprchrVisitorgatepassId_R");  
      if (strPermissionId.equals(""))
        strPermissionId = vars.getSessionValue("Visitorgatepass.inprchrVisitorgatepassId");
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
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("GatePassId", strPermissionId);
	    JasperReport jasperReportLines;	    
	    try {
	    	String strBaseDesign=null;
	         strPermissionId = strPermissionId.replaceAll("\\(|\\)|'", "");
	         
	         strBaseDesign  = "@basedesign@/com/rcss/humanresource/erpCommon/ad_reports/Visitorgatepass.jrxml";
	 	 	
	          strPermissionId = strPermissionId.replaceAll("\\(|\\)|'", "");
	          parameters.put("GatePassId", strPermissionId);
	          renderJR(vars, response, strBaseDesign, "pdf", parameters, null, null);
	          System.out.println("JOINTSUB-----------------------");	         
	    } catch (Exception e) {
	    	System.out.println("In Catch Block....");
	      e.printStackTrace();
	      throw new ServletException(e.getMessage());
	    }
	  }
	  public String getServletInfo() {
	    return "Servlet that presents the RptInvoice seeker";
	  }
	}
