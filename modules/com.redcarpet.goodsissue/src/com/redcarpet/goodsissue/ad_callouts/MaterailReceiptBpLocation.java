package com.redcarpet.goodsissue.ad_callouts;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.businessUtility.BpartnerMiscData;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.FinAccPaymentMethod;
import org.openbravo.utils.FormatUtilities;
import org.openbravo.xmlEngine.XmlDocument;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class MaterailReceiptBpLocation extends HttpSecureAppServlet {
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) {
        super.init(config);
        boolHist = false;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        VariablesSecureApp vars = new VariablesSecureApp(request);
        if (vars.commandIn("DEFAULT")) {
            String strChanged = vars.getStringParameter("inpLastFieldChanged");
            if (log4j.isDebugEnabled())
                log4j.debug("CHANGED: " + strChanged);
            String strBPartner = vars.getStringParameter("inpcBpartnerId");
            String strDocType = vars.getStringParameter("inpcDoctypetargetId");
            String strLocation = vars.getStringParameter("inpcBpartnerId_LOC");
            String strContact = vars.getStringParameter("inpcBpartnerId_CON");
            String strWindowId = vars.getStringParameter("inpwindowId");
            String strProjectId = vars.getStringParameter("inpcProjectId");
            String strIsSOTrx = Utility.getContext(this, vars, "isSOTrx", strWindowId);
            String strTabId = vars.getStringParameter("inpTabId");
            String strOrgId = vars.getStringParameter("inpadOrgId");

            try {

                    printPage(response, vars, strBPartner, strDocType, strIsSOTrx, strWindowId, strLocation,
                            strContact, strProjectId, strTabId, strOrgId);

            } catch (ServletException ex) {
                pageErrorCallOut(response);
            }
        } else
            pageError(response);
    }

    private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strBPartner,
                           String strDocType, String strIsSOTrx, String strWindowId, String strLocation,
                           String strContact, String strProjectId, String strTabId, String strOrgId) throws IOException,
            ServletException {
        if (log4j.isDebugEnabled())
            log4j.debug("Output: dataSheet");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
                "org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();

        if (strBPartner.equals(""))
            vars.removeSessionValue(strWindowId + "|C_BPartner_ID");

        BpartnerMiscData[] data = BpartnerMiscData.select(this, strBPartner);
        String strUserRep;

        StringBuffer resultado = new StringBuffer();
        resultado.append("var calloutName='SE_Invoice_BPartner';\n\n");
        if (data == null || data.length == 0)
            resultado.append("var respuesta = new Array(new Array(\"inpcBpartnerLocationId\", null));");

        else {
            resultado.append("var respuesta = new Array(");

        }
        FieldProvider[] tdv = null;
        try {
            ComboTableData comboTableData = new ComboTableData(vars, this, "TABLEDIR",
                    "C_BPartner_Location_ID", "", "C_BPartner Location - Bill To", Utility.getContext(this,
                    vars, "#AccessibleOrgTree", strWindowId), Utility.getContext(this, vars,
                    "#User_Client", strWindowId), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData, strWindowId, "");
            tdv = comboTableData.select(false);
            comboTableData = null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        resultado.append("new Array(\"inpcBpartnerLocationId\", ");

        if (tdv != null && tdv.length > 0) {
            resultado.append("new Array(");
            if (strLocation.isEmpty()) {
                // If no location is provided, the first one is selected
                resultado.append("new Array(\"" + tdv[0].getField("id") + "\", \""
                        + FormatUtilities.replaceJS(tdv[0].getField("name")) + "\", \"" + "true" + "\")");
                if (tdv.length > 1) {
                    resultado.append(",\n");
                }
                for (int i = 1; i < tdv.length; i++) {
                    resultado.append("new Array(\"" + tdv[i].getField("id") + "\", \""
                            + FormatUtilities.replaceJS(tdv[i].getField("name")) + "\", \"" + "false" + "\")");
                    if (i < tdv.length - 1)
                        resultado.append(",\n");
                }
            } else {
                // If a location is provided, it is selected
                for (int i = 0; i < tdv.length; i++) {
                    resultado.append("new Array(\"" + tdv[i].getField("id") + "\", \""
                            + FormatUtilities.replaceJS(tdv[i].getField("name")) + "\", \""
                            + (tdv[i].getField("id").equalsIgnoreCase(strLocation) ? "true" : "false") + "\")");
                    if (i < tdv.length - 1) {
                        resultado.append(",\n");
                    }
                }
            }
            resultado.append("\n)");
        } else {
            resultado.append("null");
        }
        resultado.append(");");

        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "appFrame");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();

    }

}


