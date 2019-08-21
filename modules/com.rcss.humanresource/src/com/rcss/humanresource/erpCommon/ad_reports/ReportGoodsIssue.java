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

public class ReportGoodsIssue extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strGoodsissueId = vars.getSessionValue("ReportGoodsIssue.inprcgiGoodsissueId_R");
      if (strGoodsissueId.equals(""))
        strGoodsissueId = vars.getSessionValue("ReportGoodsIssue.inprcgiGoodsissueId");
      System.out.println(strGoodsissueId+"---strGoodsissueId");
      if (log4j.isDebugEnabled())
        log4j.debug("+***********************: " + strGoodsissueId);
      printPagePartePDF(response, vars, strGoodsissueId);
    } else
      pageError(response);
  }

  private void printPagePartePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strGoodsissueId) throws IOException, ServletException {
	  if (log4j.isDebugEnabled())

	      log4j.debug("Output: pdf");
	    String strBaseDesign = getBaseDesignPath(vars.getLanguage());
	    System.out.println("strBaseDesign***********" +strBaseDesign);
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("RCGI_GOODSISSUE_ID", strGoodsissueId);
	    JasperReport jasperReportLines;
	    
	    try {
	         strGoodsissueId = strGoodsissueId.replaceAll("\\(|\\)|'", "");
	       
	                
	          JasperDesign jasperDesignLines = JRXmlLoader.load(strBaseDesign
	          + "/com/rcss/humanresource/erpCommon/ad_reports/ReportGoodsIssue.jrxml");
	          jasperReportLines = JasperCompileManager.compileReport(jasperDesignLines);
	          strGoodsissueId = strGoodsissueId.replaceAll("\\(|\\)|'", "");
	          //parameters.put("SR_LINES", jasperReportLines);
	          parameters.put("RCGI_GOODSISSUE_ID", strGoodsissueId);
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
