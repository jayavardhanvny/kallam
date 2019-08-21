/*
 * 
 * @author vinay
 * 
 * 
 */

package com.redcarpet.epcg.erpCommon.ad_reports;

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
import com.redcarpet.epcg.data.EpcgMof;
public class EpcgMofReport extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
	  
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      
      
      String strEpcgMofId = vars.getSessionValue("EpcgMofReport.inpepcgMofId_R");
      
      if (strEpcgMofId.equals(""))
    	  strEpcgMofId = vars.getSessionValue("EpcgMofReport.inpepcgMofId");
      
      System.out.println(strEpcgMofId+" ---strEpcgMofId");
      if (log4j.isDebugEnabled())
        log4j.debug("+***********************: " + strEpcgMofId);
      	
      printPagePartePDF(response, vars, strEpcgMofId);
    } else
      pageError(response);
  }

  private void printPagePartePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strEpcgMofId) throws IOException, ServletException {
	  
	 
	  if (log4j.isDebugEnabled())

	      log4j.debug("Output: pdf");
	  
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	   // parameters.put("Id", strEpcgMofId);
	 
	    
	    try {
	    	 String strBaseDesign=null;
	    	 strEpcgMofId = strEpcgMofId.replaceAll("\\(|\\)|'", "");
	        
	        	 strBaseDesign  = "@basedesign@/com/redcarpet/epcg/erpCommon/ad_reports/MofReport.jrxml";
	 		
	        	 strEpcgMofId = strEpcgMofId.replaceAll("\\(|\\)|'", "");
	        	 EpcgMof mof = OBDal.getInstance().get(EpcgMof.class,strEpcgMofId);
		if(mof.getDocumentType().getId().equals("50459F19015E4ED88C537E5FE2647809")){
				strBaseDesign =  "@basedesign@/com/redcarpet/epcg/erpCommon/ad_reports/ExportMofReport.jrxml";
			}else
				strBaseDesign  = "@basedesign@/com/redcarpet/epcg/erpCommon/ad_reports/MofReport.jrxml";

	          parameters.put("Id", strEpcgMofId);
	          renderJR(vars, response, strBaseDesign,"ManufacturingOrderForm" ,"pdf", parameters, null, null);
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



