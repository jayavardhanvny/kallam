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
 * All portions are Copyright (C) 2001-2010 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package com.rcss.humanresource.info;

import com.redcarpet.payroll.util.PayrollTypesConstants;
import org.openbravo.base.filter.RequestFilter;
import org.openbravo.base.filter.ValueListFilter;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.info.SelectorUtility;
import org.openbravo.erpCommon.utility.*;
import org.openbravo.xmlEngine.XmlDocument;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class EmployeeMultiple extends HttpSecureAppServlet {
    private static final long serialVersionUID = 1L;

    private static final String[] colNames = { "Value", "Name", "Rchr_Employee_ID",
            "RowKey" };
    private static final RequestFilter columnFilter = new ValueListFilter(colNames);
    private static final RequestFilter directionFilter = new ValueListFilter("asc", "desc");

    public void init(ServletConfig config) {
        super.init(config);
        boolHist = false;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        VariablesSecureApp vars = new VariablesSecureApp(request);

        if (vars.commandIn("DEFAULT")) {
            // String strNameValue =
            // vars.getRequestGlobalVariable("inpNameValue",
            // "ProductMultiple.name");
            // vars.getRequestGlobalVariable("inpProductCategory",
            // "ProductMultiple.productCategory");
            // if (!strNameValue.equals(""))
            // vars.setSessionValue("ProductMultiple.name", strNameValue + "%");
            String strKeyValue = vars.getGlobalVariable("inpKey", "EmployeeMultiple.key", "");
            String strNameValue = vars.getGlobalVariable("inpName", "EmployeeMultiple.name", "");
            String strEmployeeType = vars.getGlobalVariable("org.openbravo.service.datasource", "EmployeeMultiple.employeeType", "");
            log4j.info("employee Type Default "+strEmployeeType);
            PrintPage(response, vars, strKeyValue, strNameValue, strEmployeeType, isCalledFromSoTrx(request));
        } else if (vars.commandIn("STRUCTURE")) {
            log4j.info("in Structure ");
            printGridStructure(response, vars);
        } else if (vars.commandIn("DATA")) {
            String action = vars.getStringParameter("action");
            log4j.info("command DATA - action: " + action);
            if (vars.getStringParameter("clear").equals("true")) {
                vars.removeSessionValue("EmployeeMultiple.key");
                vars.removeSessionValue("EmployeeMultiple.name");
                vars.removeSessionValue("EmployeeMultiple.employeeType");
                vars.removeSessionValue("EmployeeMultiple.adorgid");
            }
            String strKey = vars.getGlobalVariable("inpKey", "EmployeeMultiple.key", "");
            String strName = vars.getGlobalVariable("inpName", "EmployeeMultiple.name", "");
            String strEmployeeType = vars.getGlobalVariable("inpEmployeeType",
                    "EmployeeMultiple.employeeType", "");
            log4j.info("strKey "+strKey);
            log4j.info("strEmployeeType "+strEmployeeType);
            String strOrg = vars.getGlobalVariable("inpAD_Org_ID", "EmployeeMultiple.adorgid", "");
            String strNewFilter = vars.getStringParameter("newFilter");
            String strOffset = vars.getStringParameter("offset");
            String strPageSize = vars.getStringParameter("page_size");
            String strSortCols = vars.getInStringParameter("sort_cols", columnFilter);
            String strSortDirs = vars.getInStringParameter("sort_dirs", directionFilter);
            String[] strIsSoTrx = request.getParameterValues("isSoTrx");
            boolean isSoTrx = (strIsSoTrx != null) ? ("'Y'".equalsIgnoreCase(strIsSoTrx[0])) : false;
            StringBuffer stringBufferEmployeeType = new StringBuffer("'");

            if (strEmployeeType != null && !strEmployeeType.equals("")) {
                stringBufferEmployeeType = stringBufferEmployeeType.append(strEmployeeType).append("'");
            } else {
                stringBufferEmployeeType = stringBufferEmployeeType.append(PayrollTypesConstants.EMPLOYEE_TYPE_OS)
                        .append("','").append(PayrollTypesConstants.EMPLOYEE_TYPE_PS)
                        .append("','").append(PayrollTypesConstants.EMPLOYEE_TYPE_WO)
                        .append("'");
            }
            log4j.info("Str Employee "+stringBufferEmployeeType.toString());
            if (action.equalsIgnoreCase("getRows")) { // Asking for data rows
                printGridData(response, vars, strKey, strName, strOrg, stringBufferEmployeeType.toString(), strSortCols,
                        strSortDirs, strOffset, strPageSize, strNewFilter);

            } else if (action.equalsIgnoreCase("getIdsInRange")) {
                // asking for selected rows
                printGridDataSelectedRows(response, vars, strKey, strName, strOrg, stringBufferEmployeeType.toString(),
                        strSortCols, strSortDirs);
            } else {
                throw new ServletException("Unimplemented action in DATA request: " + action);
            }
        } else
            pageError(response);
    }

    private void PrintPage(HttpServletResponse response, VariablesSecureApp vars, String strKeyValue,
                           String strNameValue, String strEmployeeType, boolean isSoTrx) throws IOException, ServletException {
        if (log4j.isDebugEnabled())
            log4j.debug("Output: Multiple products seeker Frame Set");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
                "com/rcss/humanresource/info/EmployeeMultiple").createXmlDocument();
        if (strKeyValue.equals("") && strNameValue.equals("")) {
            xmlDocument.setParameter("key", "%");
        } else {
            xmlDocument.setParameter("key", strKeyValue);
        }
        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
        xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("alertMsg",
                "ALERT_MSG=\"" + Utility.messageBD(this, "NoEmployeeSelected", vars.getLanguage()) + "\";");
        xmlDocument.setParameter("theme", vars.getTheme());
        xmlDocument.setParameter("name", strNameValue);
        xmlDocument.setParameter("gridSoTrx", ((isSoTrx) ? "&isSoTrx='Y'" : ""));
        try {
            ComboTableData comboTableData = new ComboTableData(vars, this, "LIST",
                    "", "RCHR_Employee_Type", "", Utility.getContext(this, vars, "#AccessibleOrgTree",
                    "EmployeeMultiple"),
                    Utility.getContext(this, vars, "#User_Client", "EmployeeMultiple"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData, "EmployeeMultiple",
                    vars.getSessionValue("EmployeeMultiple.employeeType", ""));
            xmlDocument.setData("reportM_Product_Category_ID", "liststructure",
                    comboTableData.select(false));
            comboTableData = null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        xmlDocument.setParameter("employeeType",
                vars.getSessionValue("EmployeeMultiple.employeeType", ""));
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
    }

    private void printGridStructure(HttpServletResponse response, VariablesSecureApp vars)
            throws IOException, ServletException {
        //if (log4j.isDebugEnabled())
            log4j.info("Output: print page structure ");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
                "org/openbravo/erpCommon/utility/DataGridStructure").createXmlDocument();

        SQLReturnObject[] data = getHeaders(vars);
        String type = "Hidden";
        String title = "";
        String description = "";

        xmlDocument.setParameter("type", type);
        xmlDocument.setParameter("title", title);
        xmlDocument.setParameter("description", description);
        xmlDocument.setData("structure1", data);
        log4j.info("Data is "+data.length);
        xmlDocument.setParameter("backendPageSize", String.valueOf(TableSQLData.maxRowsPerGridPage));
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
            log4j.info(xmlDocument.print());
        out.println(xmlDocument.print());
        out.close();
    }

    private SQLReturnObject[] getHeaders(VariablesSecureApp vars) {
        SQLReturnObject[] data = null;
        Vector<SQLReturnObject> vAux = new Vector<SQLReturnObject>();
        String[] colWidths = { "109", "225", "0", "0"};

        for (int i = 0; i < colNames.length; i++) {
            SQLReturnObject dataAux = new SQLReturnObject();
            log4j.info("Column Names "+colNames[i]);
            dataAux.setData("columnname", colNames[i]);
            dataAux.setData("gridcolumnname", colNames[i]);
            dataAux.setData("adReferenceId", "AD_Reference_ID");
            dataAux.setData("adReferenceValueId", "AD_ReferenceValue_ID");
            dataAux.setData("isidentifier", (colNames[i].equals("rowkey") ? "true" : "false"));
            dataAux.setData("iskey", (colNames[i].equals("RowKey") ? "true" : "false"));
            dataAux.setData("isvisible",
                    (colNames[i].equals("Rchr_Employee_ID") || colNames[i].equalsIgnoreCase("RowKey") ? "false"
                            : "true"));
            String name = Utility.messageBD(this, "MPS_" + colNames[i].toUpperCase(), vars.getLanguage());
            dataAux.setData("name", (name.startsWith("MPS_") ? colNames[i] : name));
            dataAux.setData("type", "string");
            dataAux.setData("width", colWidths[i]);
            vAux.addElement(dataAux);
        }
        data = new SQLReturnObject[vAux.size()];
        vAux.copyInto(data);
        log4j.info("Size of Data in SQL ReturnObject "+data.length);
        return data;
    }

    private void printGridData(HttpServletResponse response, VariablesSecureApp vars, String strKey,
                               String strName, String strOrg, String strEmployeeType, String strOrderCols,
                               String strOrderDirs, String strOffset, String strPageSize, String strNewFilter
                              ) throws IOException, ServletException {
        //if (log4j.isDebugEnabled())
            log4j.info("Output: print page rows");
        int page = 0;
        SQLReturnObject[] headers = getHeaders(vars);
        FieldProvider[] data = null;
        String type = "Hidden";
        String title = "";
        String description = "";
        String strNumRows = "0";
        int offset = Integer.valueOf(strOffset).intValue();
        int pageSize = Integer.valueOf(strPageSize).intValue();

        if (headers != null) {
            try {
                // build sql orderBy clause
                String strOrderBy = SelectorUtility.buildOrderByClause(strOrderCols, strOrderDirs);
                page = TableSQLData.calcAndGetBackendPage(vars, "ProjectData.currentPage");
                if (vars.getStringParameter("movePage", "").length() > 0) {
                    // on movePage action force executing countRows again
                    strNewFilter = "";
                }
                int oldOffset = offset;
                offset = (page * TableSQLData.maxRowsPerGridPage) + offset;
                log4j.debug("relativeOffset: " + oldOffset + " absoluteOffset: " + offset);
                if (strNewFilter.equals("1") || strNewFilter.equals("")) { // New
                    // filter
                    // or
                    // first
                    // load
                    String rownum = "0", oraLimit1 = null, oraLimit2 = null, pgLimit = null;

                        pgLimit = TableSQLData.maxRowsPerGridPage + " OFFSET " + offset;

                        //log4j.info("Organization "+vars.getOrg());
                        //log4j.info("Client "+vars.getClient());
                    strNumRows = EmployeeMultipleData.countRows(this, rownum, strKey, strName,
                            //strProductCategory, (isSoTrx) ? "Y" : null,
                            Utility.getContext(this, vars, "#User_Client", "EmployeeMultiple"),
                            Utility.getSelectorOrgs(this, vars, strOrg), pgLimit, oraLimit1, oraLimit2);
                    vars.setSessionValue("BusinessPartnerInfo.numrows", strNumRows);
                } else {
                    strNumRows = vars.getSessionValue("BusinessPartnerInfo.numrows");
                }

                // Filtering result

                    String pgLimit = pageSize + " OFFSET " + offset;
                    data = EmployeeMultipleData.select(this, "1",
                            strKey, strName,
                            //(isSoTrx) ? "Y" : null,
                            Utility.getContext(this, vars, "#User_Client", "EmployeeMultiple"),
                            Utility.getSelectorOrgs(this, vars, strOrg), strEmployeeType, pgLimit);

            } catch (ServletException e) {
                log4j.error("Error in print page data: " + e);
                e.printStackTrace();
                OBError myError = Utility.translateError(this, vars, vars.getLanguage(), e.getMessage());
                if (!myError.isConnectionAvailable()) {
                    bdErrorAjax(response, "Error", "Connection Error", "No database connection");
                    return;
                } else {
                    type = myError.getType();
                    title = myError.getTitle();
                    if (!myError.getMessage().startsWith("<![CDATA["))
                        description = "<![CDATA[" + myError.getMessage() + "]]>";
                    else
                        description = myError.getMessage();
                }
            } catch (Exception e) {
                if (log4j.isDebugEnabled())
                    log4j.debug("Error obtaining rows data");
                type = "Error";
                title = "Error";
                if (e.getMessage().startsWith("<![CDATA["))
                    description = "<![CDATA[" + e.getMessage() + "]]>";
                else
                    description = e.getMessage();
                e.printStackTrace();
            }
        }

        if (!type.startsWith("<![CDATA["))
            type = "<![CDATA[" + type + "]]>";
        if (!title.startsWith("<![CDATA["))
            title = "<![CDATA[" + title + "]]>";
        if (!description.startsWith("<![CDATA["))
            description = "<![CDATA[" + description + "]]>";
        StringBuffer strRowsData = new StringBuffer();
        strRowsData.append("<xml-data>\n");
        strRowsData.append("  <status>\n");
        strRowsData.append("    <type>").append(type).append("</type>\n");
        strRowsData.append("    <title>").append(title).append("</title>\n");
        strRowsData.append("    <description>").append(description).append("</description>\n");
        strRowsData.append("  </status>\n");
        strRowsData.append("  <rows numRows=\"").append(strNumRows)
                .append("\" backendPage=\"" + page + "\">\n");
        log4j.info("size of a data length vinay is "+data.length);
        log4j.info("Size of headers is "+headers.length);
        if (data != null && data.length > 0) {
            for (int j = 0; j < data.length; j++) {
                //log4j.info("J value "+j);
                strRowsData.append("    <tr>\n");
                for (int k = 0; k < headers.length; k++) {
                    strRowsData.append("      <td><![CDATA[");
                    //log4j.info("K value "+k+" and "+headers[k]);
                    String columnname = headers[k].getField("columnname");
                    if ((data[j].getField(columnname)) != null) {
                        if (headers[k].getField("adReferenceId").equals("32"))
                            strRowsData.append(strReplaceWith).append("/images/");
                        strRowsData.append(data[j].getField(columnname).replaceAll("<b>", "")
                                .replaceAll("<B>", "").replaceAll("</b>", "").replaceAll("</B>", "")
                                .replaceAll("<i>", "").replaceAll("<I>", "").replaceAll("</i>", "")
                                .replaceAll("</I>", "").replaceAll("<p>", "&nbsp;").replaceAll("<P>", "&nbsp;")
                                .replaceAll("<br>", "&nbsp;").replaceAll("<BR>", "&nbsp;"));
                    } else {
                        if (headers[k].getField("adReferenceId").equals("32")) {
                            strRowsData.append(strReplaceWith).append("/images/blank.gif");
                        } else
                            strRowsData.append("&nbsp;");
                    }
                    strRowsData.append("]]></td>\n");
                }
                strRowsData.append("    </tr>\n");
            }
        }
        strRowsData.append("  </rows>\n");
        strRowsData.append("</xml-data>\n");
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        //if (log4j.isDebugEnabled())
            //log4j.info(strRowsData.toString());
        out.print(strRowsData.toString());
        out.close();
    }

    /**
     * Prints the response for the getRowsIds action. It returns the rowkey for the identifier column
     * for the list of selected rows [minOffset..maxOffset]
     *
     */
    private void printGridDataSelectedRows(HttpServletResponse response, VariablesSecureApp vars,
                                           String strKey, String strName, String strEmployeeType,
                                           String strOrg, String strOrderCols,
                                           String strOrderDirs
            //, boolean isSoTrx
    ) throws IOException, ServletException {
        int minOffset = new Integer(vars.getStringParameter("minOffset")).intValue();
        int maxOffset = new Integer(vars.getStringParameter("maxOffset")).intValue();
        log4j.debug("Output: print page ids, minOffset: " + minOffset + ", maxOffset: " + maxOffset);
        String type = "Hidden";
        String title = "";
        String description = "";
        FieldProvider[] data = null;
        FieldProvider[] res = null;
        try {
            // build sql orderBy clause
            String strOrderBy = SelectorUtility.buildOrderByClause(strOrderCols, strOrderDirs);
            String strPage = vars.getSessionValue("EmployeeMultiple|currentPage", "0");
            int page = Integer.valueOf(strPage);
            int oldMinOffset = minOffset;
            int oldMaxOffset = maxOffset;
            minOffset = (page * TableSQLData.maxRowsPerGridPage) + minOffset;
            maxOffset = (page * TableSQLData.maxRowsPerGridPage) + maxOffset;
            //log4j.debug("relativeMinOffset: " + oldMinOffset + " absoluteMinOffset: " + minOffset);
            //log4j.debug("relativeMaxOffset: " + oldMaxOffset + " absoluteMaxOffset: " + maxOffset);
            // Filtering result
                // minOffset and maxOffset are zero based so pageSize is difference +1
                int pageSize = maxOffset - minOffset + 1;
                String pgLimit = pageSize + " OFFSET " + minOffset;
            data = EmployeeMultipleData.select(this, "1",
                    strKey, strName,
                    //(isSoTrx) ? "Y" : null,
                    Utility.getContext(this, vars, "#User_Client", "EmployeeMultiple"),
                    Utility.getSelectorOrgs(this, vars, strOrg), strEmployeeType, pgLimit );
            // result field has to be named id -> rename by copy the list
            res = new FieldProvider[data.length];
            for (int i = 0; i < data.length; i++) {
                SQLReturnObject sqlReturnObject = new SQLReturnObject();
                String resValue = "<![CDATA[" + data[i].getField("rowkey") + "]]>";
                sqlReturnObject.setData("id", resValue);
                res[i] = sqlReturnObject;
            }
        } catch (Exception e) {
            log4j.error("Error obtaining id-list for getIdsInRange", e);
            type = "Error";
            title = "Error";
            if (!e.getMessage().startsWith("<![CDATA["))
                description = "<![CDATA[" + e.getMessage() + "]]>";
        }

        XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
                "org/openbravo/erpCommon/utility/DataGridID").createXmlDocument();
        xmlDocument.setParameter("type", type);
        xmlDocument.setParameter("title", title);
        xmlDocument.setParameter("description", description);
        xmlDocument.setData("structure1", res);
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        log4j.info(xmlDocument.print());
        out.println(xmlDocument.print());
        out.close();
    }

    public String getServletInfo() {
        return "Servlet that presents the multiple products seeker";
    } // end of getServletInfo() method
    /*
     * Currently this is only being used when the selector is called from: - Sales Dimensional Report
     * - Shipments Dimensional Report - Sales Order Report
     */
    private boolean isCalledFromSoTrx(HttpServletRequest request) {
        HashMap parameters = (HashMap) request.getParameterMap();
        Iterator it = parameters.keySet().iterator();
        String[] value;
        boolean isSoTrx = false;
        while (it.hasNext()) {
            String key = (String) it.next();
            if (key.toLowerCase().contains("issotrx")) {
                value = (String[]) (parameters.get(key));
                isSoTrx = value[0].contains("isSoTrx='Y'");
            }
        }

        return isSoTrx;
    }
}