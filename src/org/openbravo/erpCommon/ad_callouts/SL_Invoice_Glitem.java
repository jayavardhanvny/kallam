/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html 
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License. 
 * The Original Code is Openbravo ERP. 
 * The Initial Developer of the Original Code is Openbravo SLU 
 * All portions are Copyright (C) 2010-2012 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.openbravo.erpCommon.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.businessUtility.Tax;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.xmlEngine.XmlDocument;

public class SL_Invoice_Glitem extends HttpSecureAppServlet {
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
      String strAccountID = vars.getStringParameter("inpaccountId");
      String strADOrgID = vars.getStringParameter("inpadOrgId");
      String strCInvoiceID = vars.getStringParameter("inpcInvoiceId");
      String strWindowId = vars.getStringParameter("inpwindowId");
      String strIsSOTrx = Utility.getContext(this, vars, "isSOTrx", strWindowId);
      String strWharehouse = Utility.getContext(this, vars, "#M_Warehouse_ID", strWindowId);
      try {
        printPage(response, vars, strChanged, strAccountID, strADOrgID, strCInvoiceID, strIsSOTrx,
            strWharehouse);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strChanged,
      String strAccountID, String strADOrgID, String strCInvoiceID, String strIsSOTrx,
      String strWharehouse) throws IOException, ServletException {
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
    if (strChanged.equalsIgnoreCase("inpaccountId")) {
      StringBuilder result = new StringBuilder();

      result.append("var calloutName='SL_Invoice_Glitem';\n\n");
      result.append("var respuesta = new Array(");
      SLInvoiceTaxData[] data = SLInvoiceTaxData.select(this, strCInvoiceID);
      if (data != null && data.length > 0) {
        String strCTaxID = Tax.get(this, null, data[0].dateinvoiced, strADOrgID, strWharehouse,
            data[0].cBpartnerLocationId, data[0].cBpartnerLocationId, data[0].cProjectId,
            strIsSOTrx, strAccountID);

        result.append("new Array(\"inpcTaxId\", \"" + strCTaxID + "\")");
      }
      result.append(",\n");
      result.append("new Array(\"EXECUTE\", \"displayLogic();\")\n");
      result.append(");");
      xmlDocument.setParameter("array", result.toString());
    }
    xmlDocument.setParameter("frameName", "appFrame");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }
}
