package com.rcss.humanresource.erpCommon.ad_reports;

import com.sam.numbertowords.NumberToWordsConverter_en_In;
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
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.invoice.Invoice;

public class ReportSalesInvoice extends HttpSecureAppServlet {

    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) {
        super.init(config);
        boolHist = false;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        VariablesSecureApp vars = new VariablesSecureApp(request);

        if (vars.commandIn("DEFAULT")) {

            String strcInvoiceId = vars.getSessionValue("ReportSalesInvoice.inpcInvoiceId_R");
            String strcInvoiceId1 = vars.getSessionValue("ReportSalesInvoice.inpcInvoiceId");

            if (strcInvoiceId.equals("")) {
                strcInvoiceId = vars.getSessionValue("ReportSalesInvoice.inpcInvoiceId");
            }
            if (log4j.isDebugEnabled()) {
                log4j.debug("+***********************: " + strcInvoiceId);
            }
            printPagePartePDF(response, vars, strcInvoiceId);
        } else {
            pageError(response);
        }
    }

    private void printPagePartePDF(HttpServletResponse response, VariablesSecureApp vars,
            String strcInvoiceId) throws IOException, ServletException {
        if (log4j.isDebugEnabled()) {
            log4j.debug("Output: pdf");
        }
        String strBaseDesign = getBaseDesignPath(vars.getLanguage());
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("C_INVOICE_ID", strcInvoiceId);
        JasperReport jasperReportLines;

        try {
            String rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/ReportSalesInvoice.jrxml";
            strcInvoiceId = strcInvoiceId.replaceAll("\\(|\\)|'", "");
            Invoice invoice = OBDal.getInstance().get(Invoice.class, strcInvoiceId);
            //String docname=invoice.getTransactionDocument().getName();
            String docprint=invoice.getTransactionDocument().getPrintText();
			
			

            if (StringUtils.equals(invoice.getEpcgPrinttype(), "Standard Invoice")) {
                
                    rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/ReportSalesInvoice.jrxml";
            }
   
                if (StringUtils.equals(invoice.getEpcgPrinttype(), "Commercial Invoice")) {
                    rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/DirectExportCommercialInvoice.jrxml";
                }
                if (StringUtils.equals(invoice.getEpcgPrinttype(), "ARE1")) {
                    rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/DirectExportFormARE1.jrxml";
                }
                if (StringUtils.equals(invoice.getEpcgPrinttype(), "Annexure C1")) {
                    rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/YarnMerchantExportAnnexureC1.jrxml";
                }
                if (StringUtils.equals(invoice.getEpcgPrinttype(), "Packing List")) {
                    rptJrxml = strBaseDesign + "/com/rcss/humanresource/erpCommon/ad_reports/DirectExportPackingList.jrxml";
                }



            JasperDesign jasperDesignLines = JRXmlLoader.load(rptJrxml);
            jasperReportLines = JasperCompileManager.compileReport(jasperDesignLines);
            strcInvoiceId = strcInvoiceId.replaceAll("\\(|\\)|'", "");
            //parameters.put("SR_LINES", jasperReportLines);
            parameters.put("C_INVOICE_ID", strcInvoiceId);
            parameters.put("strPrint", docprint);
            renderJR(vars, response, rptJrxml, "pdf", parameters, null, null);

        } catch (JRException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

    }

    public String getServletInfo() {
        return "Servlet that presents the RptInvoice seeker";
    } // End of getServletInfo() method
}


