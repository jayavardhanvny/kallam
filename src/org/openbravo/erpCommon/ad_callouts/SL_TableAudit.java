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
 * All portions are Copyright (C) 2010-2011 Openbravo SLU 
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

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.SessionInfo;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.xmlEngine.XmlDocument;

public class SL_TableAudit extends HttpSecureAppServlet {

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
      try {
        printPage(response, vars, strChanged);
      } catch (Exception ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strChanged)
      throws IOException, ServletException {
    // Change audit trail status regarding whether there are audited tables
    StringBuffer action = new StringBuffer();
    if (strChanged.equalsIgnoreCase("inpisfullyaudited")) {
      boolean currentRecordFullyAudited = vars.getStringParameter("inpisfullyaudited").equals("Y");
      if (currentRecordFullyAudited) {
        SessionInfo.setAuditActive(true);

        OBCriteria<Table> qTables = OBDal.getInstance().createCriteria(Table.class);
        qTables.add(Restrictions.eq(Table.PROPERTY_ISFULLYAUDITED, true));
        qTables.add(Restrictions.eq(Table.PROPERTY_ISAUDITINSERTS, true));
        if (qTables.count() == 0) {
          action.append("new Array(\"inpisauditinserts\", \"N\"),\n");
        } else {
          action.append("new Array(\"inpisauditinserts\", \"Y\"),\n");
        }
        action.append("new Array(\"MESSAGE\", \""
            + Utility.messageBD(this, "RegenerateAudit_ExcludeColumn", vars.getLanguage())
            + "\")\n");
      } else {
        OBCriteria<Table> obc = OBDal.getInstance().createCriteria(Table.class);
        obc.add(Restrictions.eq(Table.PROPERTY_ISFULLYAUDITED, true));
        SessionInfo.setAuditActive(obc.list().size() > 0);
      }
    } else if (strChanged.equalsIgnoreCase("inpisexcludeaudit")) {
      boolean currentRecordExcludeAudit = vars.getStringParameter("inpisexcludeaudit").equals("Y");
      if (currentRecordExcludeAudit) {
        SessionInfo.setAuditActive(true);
        action.append("new Array(\"MESSAGE\", \""
            + Utility.messageBD(this, "RegenerateAudit", vars.getLanguage()) + "\")\n");
      } else {
        OBCriteria<Table> obc = OBDal.getInstance().createCriteria(Table.class);
        obc.add(Restrictions.eq(Table.PROPERTY_ISFULLYAUDITED, true));
        SessionInfo.setAuditActive(obc.list().size() > 0);
      }
    }

    StringBuffer result = new StringBuffer();
    result.append("var calloutName='SL_TableAudit';\n\n");
    result.append("var respuesta = new Array(");
    result.append(action);
    result.append(");");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
    xmlDocument.setParameter("array", result.toString());
    xmlDocument.setParameter("frameName", "appFrame");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }
}
