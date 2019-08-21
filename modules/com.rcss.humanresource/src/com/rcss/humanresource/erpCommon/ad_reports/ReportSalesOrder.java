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
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.order.Order;
import org.apache.commons.lang.StringUtils;

public class ReportSalesOrder extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  		throws IOException, ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strcOrderId = vars.getSessionValue("ReportSalesOrder.inpcOrderId_R");
      if (strcOrderId.equals(""))
        strcOrderId = vars.getSessionValue("ReportSalesOrder.inpcOrderId");
     // System.out.println(strcOrderId+"---strcOrderId");
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
	    //System.out.println("strBaseDesign***********" +strBaseDesign);
	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("C_ORDER_ID", strcOrderId);
	    JasperReport jasperReportLines;
	    
	  
	    try {
            String rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/ReportSalesOrder.jrxml";
            strcOrderId = strcOrderId.replaceAll("\\(|\\)|'", "");
            Order order = OBDal.getInstance().get(Order.class, strcOrderId);
            Integer termvalue = 0;
            try{
            	termvalue = Integer.parseInt(order.getPaymentTerms().getSearchKey());
            }catch(Exception e){
            	
            }
            
            
            
            String paymentCondition="";
            if(order.getPaymentMethod().getId().equals("1576F0ADBBD44A33BA3ABEB77911DFF9")){
            	Integer finalValue = termvalue-order.getEpcgCostenquiry().getCreditperiod().intValue();
            	
            	paymentCondition=order.getPaymentTerms().getName()+"( "+order.getEpcgCostenquiry().getCreditperiod().toString()+" Days interest @KSML A/C & balance "+
            			finalValue.toString()+" Days @ Buyers A/C.)";
            }else if (order.getPaymentMethod().getId().equals("B147F9E2F6874A63BA63187C9297E929")) {
            	paymentCondition =order.getEpcgCostenquiry().getCreditperiod().toString()+" DAYS PDC FROM THE DATE OF DISPATCH. CHEQUE TO BE HANDOVER TO TRANSPORTER AT THE TIME OF TAKING DELIVERY OF GOODS.";
            }else {
            	paymentCondition = "ADVANCE RTGS CD- ";
            }
           
                //if (StringUtils.equals(order.getEpcgOrdertype(), "Sales Order")) {
                    rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/ReportSalesOrder.jrxml";
                //} else if (StringUtils.equals(order.getEpcgOrdertype(), "Proforma")) {
                    //rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/Proforma.jrxml";
                //}
            //Order order = OBDal.getInstance().get(Order.class, strcOrderId);
       
            JasperDesign jasperDesignLines = JRXmlLoader.load(rptJrxml);
            jasperReportLines = JasperCompileManager.compileReport(jasperDesignLines);
            strcOrderId = strcOrderId.replaceAll("\\(|\\)|'", "");
            //parameters.put("SR_LINES", jasperReportLines);
            parameters.put("C_ORDER_ID", strcOrderId);
            
            parameters.put("paymentCondition", paymentCondition);
            renderJR(vars, response, rptJrxml, "pdf", parameters, null, null);
	    }

	    catch (JRException e) {
	      e.printStackTrace();
	      throw new ServletException(e.getMessage());
	    }


	  }

	  public String getServletInfo() {
	    return "Servlet that presents the RptInvoice seeker";
	  } // End of getServletInfo() method
	}
