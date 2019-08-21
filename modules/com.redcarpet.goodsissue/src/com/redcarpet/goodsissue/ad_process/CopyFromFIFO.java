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
 * All portions are Copyright (C) 2001-2012 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package com.redcarpet.goodsissue.ad_process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.StringTokenizer;

import com.rcss.humanresource.data.RCHR_Jobcard;
import com.redcarpet.epcg.data.ConeType;
import com.redcarpet.epcg.data.EPCG_Packaging;
import com.redcarpet.goodsissue.ad_process.CopyFromFIFOData;
import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.redcarpet.epcg.data.EpcgVariant;
import com.redcarpet.goodsissue.data.RCGITransactions;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.model.common.plm.Product;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.filter.IsPositiveIntFilter;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;

import org.openbravo.dal.service.OBDal;

import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;
import java.sql.SQLException;
import java.math.BigDecimal;
import org.openbravo.exception.NoConnectionAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CopyFromFIFO extends HttpSecureAppServlet {
    final static Logger logger = LoggerFactory.getLogger(CopyFromFIFO.class);
    private static final long serialVersionUID = 1L;
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    public double avg1=0;
    public void init(ServletConfig config) {
        super.init(config);
        boolHist = false;
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        VariablesSecureApp vars = new VariablesSecureApp(request);
        OBError myError =null;
        if (vars.commandIn("DEFAULT")) {
            try{
            String strWindowId = vars.getStringParameter("inpwindowId");
            String strKey = vars.getGlobalVariable("inprcgiMaterialissueId", strWindowId + "|Rcgi_Materialissue_ID");
            String strTabId = vars.getStringParameter("inpTabId");
            String strOrg = vars.getStringParameter("inpadOrgId");
            String strIndentId=vars.getStringParameter("inprcgiMaterialindentlinesId");
            RCGI_MaterialIssue issue=OBDal.getInstance().get(RCGI_MaterialIssue.class,strKey);
            System.out.println(strIndentId);
            /*String strYarntype = vars.getGlobalVariable("inprchrWarpyarntypeId",
                    "CopyFromFIFO|rchrwarpyarntypeId","");
            String strVariant = vars.getGlobalVariable("inpepcgVariantId",
                    "CopyFromFIFO|epcgvariantid","");
            String  strConetype= vars.getGlobalVariable("inpepcgConetypeId",
                    "CopyFromFIFO|epcgconetypeid","");
            String strConetype=vars.getStringParameter("inpepcgConetypeId");
            String strYarntype=vars.getStringParameter("inprchrWarpyarntypeId");
            String strVariant=vars.getStringParameter("inpepcgVariantId");*/

            String strConetype=issue.getEpcgConetype()!=null?issue.getEpcgConetype().getId():null;
            String strYarntype=issue.getRchrWarpyarntype()!=null?issue.getRchrWarpyarntype().getId():null;
            String strVariant=issue.getEpcgVariant()!=null?issue.getEpcgVariant().getId():null;
                System.out.println(strConetype+" "+strVariant+" "+strYarntype);
                printPageDataSheet(response, vars, strKey, strWindowId, strTabId,strOrg,strConetype,strYarntype,strVariant,strIndentId);
            }catch(NullPointerException ne){
                ne.printStackTrace();
            }


        }else if (vars.commandIn("FIND")) {
            String strWindowId = vars.getStringParameter("inpwindowId");
            String strKey = vars.getGlobalVariable("inprcgiMaterialissueId", strWindowId + "|Rcgi_Materialissue_ID");
            String strTabId = vars.getStringParameter("inpTabId");
            String strOrg = vars.getStringParameter("inpadOrgId");
            String strIndentId=vars.getStringParameter("inprcgiMaterialindentlinesId");

            String strYarntype = vars.getRequestGlobalVariable("inprchrWarpyarntypeId",
                    "CopyFromFIFO|rchrwarpyarntypeId");
            String strVariant = vars.getRequestGlobalVariable("inpepcgVariantId",
                    "CopyFromFIFO|epcgvariantid");
            String  strConetype= vars.getRequestGlobalVariable("inpepcgConetypeId",
                    "CopyFromFIFO|epcgconetypeid");
            searchPrintPageDataSheet(response, vars,strKey, strWindowId, strTabId,strOrg,strConetype,strYarntype,strVariant,strIndentId);
        }  else if (vars.commandIn("SAVE")) {
            String strRownum = null;
            try {
                strRownum = vars.getRequiredInStringParameter("inpRownumId", IsPositiveIntFilter.instance);
            } catch (ServletException e) {
                log4j.error("Error captured: ", e);
                throw new ServletException(OBMessageUtils.messageBD("@JS1@"));
            }
            logger.info("On Save");
            String strKey = vars.getRequiredStringParameter("inprcgiMaterialissueId");
            String strTabId = vars.getStringParameter("inpTabId");
            String strJobId = vars.getStringParameter("inprchrJobcardId");
            if (strRownum.startsWith("(")) {
                strRownum = strRownum.substring(1, strRownum.length() - 1);
            }
            strRownum = Replace.replace(strRownum, "'", "");
            try{
                myError = copyLines(vars, strRownum, strKey,strJobId);

            } catch(IOException e){
                logger.debug("IOException Process Error {}",e.getMessage());
                myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
            } catch(ServletException e){
                myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
            } catch(NoConnectionAvailableException e){
                myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
            } catch(SQLException sql){
                myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+sql.getMessage());
            }catch(Exception sql){
                myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+sql.getMessage());
            }
            String strWindowPath = Utility.getTabURL(strTabId, "R", true);
            if (strWindowPath.equals(""))
                strWindowPath = strDefaultServlet;
            vars.setMessage(strTabId, myError);
            printPageClosePopUp(response, vars, strWindowPath);
        } else
            pageErrorPopUp(response);
    }
    private OBError copyLines(VariablesSecureApp vars, String strRownums, String strKey,String strJobId) throws IOException, ServletException,NoConnectionAvailableException, SQLException {
        OBError myError = null;
        int count = 0;
        if (strRownums.equals("")) {
            myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError 1 ");
            return myError;
        }
        Connection conn = null;
        RCGI_MaterialIssue mi=OBDal.getInstance().get(RCGI_MaterialIssue.class,strKey);
        long lineNo=10;
        try {
            conn = getTransactionConnection();
            StringTokenizer st = new StringTokenizer(strRownums, ",", false);
            while (st.hasMoreTokens()) {
                String strRownum = st.nextToken().trim();
                System.out.println("row num..."+strRownum);
                String strMid = vars.getStringParameter("inpmid" + strRownum);
                String strYarnId = vars.getStringParameter("inpyarnid" + strRownum);
                String strVariantId = vars.getStringParameter("inpvariantid" + strRownum);
                String strConeTypeId=vars.getStringParameter("inpconeid" + strRownum);
                System.out.println(strConeTypeId);
                String openqty = vars.getStringParameter("inpopenqty" + strRownum);
                String fifoId = vars.getStringParameter("inpfifoid" + strRownum);
                String issuedqty = vars.getStringParameter("inpissuedqty" + strRownum);
                String issedcones=vars.getStringParameter("inpissuedcones" +strRownum);
                RCGI_MiLines miline= OBProvider.getInstance().get(RCGI_MiLines.class);
                miline.setClient(mi.getClient());
                miline.setOrganization(mi.getOrganization());
                miline.setLineNo(lineNo);
                miline.setProduct(OBDal.getInstance().get(Product.class,strMid));
                miline.setRchrWarpyarntype(OBDal.getInstance().get(RCHRWarpyarntype.class,strYarnId));
                miline.setEpcgVariant(OBDal.getInstance().get(EpcgVariant.class,strVariantId));
                miline.setRcgiTransactions(OBDal.getInstance().get(RCGITransactions.class,fifoId));
                miline.setOrderedQuantity(new BigDecimal(issuedqty));
                miline.setMaterialIssue(mi);
                miline.setIssedcones(new BigDecimal(issedcones));
                miline.setUnitprice(OBDal.getInstance().get(RCGITransactions.class,fifoId).getCost());
                miline.setLineNetAmount(new BigDecimal(issuedqty).multiply(OBDal.getInstance().get(RCGITransactions.class,fifoId).getCost()));
                miline.setStorageBin(OBDal.getInstance().get(RCGITransactions.class,fifoId).getStorageBin());
                miline.setEpcgConetype(OBDal.getInstance().get(ConeType.class,strConeTypeId));
                OBDal.getInstance().save(miline);
                count++;
                lineNo+=10;

            }
            myError = new OBError();
            myError.setType("Success");
            myError.setTitle(OBMessageUtils.messageBD("Success"));
            myError.setMessage(OBMessageUtils.messageBD("Copied Records:"+count) );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        conn = getTransactionConnection();

        releaseRollbackConnectionCurrent(conn);
        return myError;
    }

    public void releaseRollbackConnectionCurrent(Connection conn) throws SQLException{
        releaseRollbackConnection(conn);
    }

   private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
                                    String strKey, String strWindowId, String strTabId,
                                    String strOrg,String strConetype,String strYarntype,String strVariant,String strIndentId) throws IOException, ServletException {
       System.out.println(strConetype+" "+strVariant+" "+strYarntype);
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
                "com/redcarpet/goodsissue/ad_process/CopyFromFIFO").createXmlDocument();

        xmlDocument.setParameter("rchrWapyarntypeId", strYarntype);
        xmlDocument.setParameter("epcgVariantId", strVariant);
        xmlDocument.setParameter("epcgConetype", strConetype);

        try {
            ComboTableData comboTableData = new ComboTableData(vars, this,
                    "TABLEDIR", "RCHR_Warpyarntype_ID", "", "", Utility.getContext(
                    this, vars, "#User_Org", "CopyFromFIFO"),
                    Utility.getContext(this, vars, "#User_Client",
                            "CopyFromFIFO"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData,
                    "CopyFromFIFO", "");
            xmlDocument.setData("reportRCHR_Warpyarntype_ID", "liststructure",
                    comboTableData.select(false));
            comboTableData = null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        try {
            ComboTableData comboTableData = new ComboTableData(vars, this,
                    "TABLEDIR", "EPCG_Variant_ID", "", "", Utility.getContext(
                    this, vars, "#User_Org", "CopyFromFIFO"),
                    Utility.getContext(this, vars, "#User_Client",
                            "CopyFromFIFO"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData,
                    "CopyFromFIFO", "");
            xmlDocument.setData("reportEPCG_Variant_ID", "liststructure",
                    comboTableData.select(false));
            comboTableData = null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        try {
            ComboTableData comboTableData = new ComboTableData(vars, this,
                    "TABLEDIR", "EPCG_Conetype_ID", "", "", Utility.getContext(
                    this, vars, "#User_Org", "CopyFromFIFO"),
                    Utility.getContext(this, vars, "#User_Client",
                            "CopyFromFIFO"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData,
                    "CopyFromFIFO", "");
            xmlDocument.setData("reportEPCG_Conetype_ID", "liststructure",
                    comboTableData.select(false));
            comboTableData = null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        CopyFromFIFOData[] dataOrder = CopyFromFIFOData.select(this,strIndentId,strYarntype,strVariant,strConetype);

       // if(dataOrder.length!=0){
            xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
            xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
            xmlDocument.setParameter("theme", vars.getTheme());
            xmlDocument.setParameter("key", strKey);
            xmlDocument.setParameter("windowId", strWindowId);
            xmlDocument.setParameter("tabId", strTabId);
            xmlDocument.setData("structure1", dataOrder);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(xmlDocument.print());
            out.close();
      //  }

    }

    private void searchPrintPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
                                    String strKey, String strWindowId, String strTabId,
                                          String strOrg,String strConetype,String strYarntype,String strVariant,String strIndentId) throws IOException, ServletException {

        System.out.println(strConetype+" "+strVariant+" "+strYarntype);
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
                "com/redcarpet/goodsissue/ad_process/CopyFromFIFO").createXmlDocument();
        String indentId=OBDal.getInstance().get(RCGI_MaterialIssue.class,strKey).getRcgiMaterialindentlines().getId();

        try {
            ComboTableData comboTableData = new ComboTableData(vars, this,
                    "TABLEDIR", "RCHR_Warpyarntype_ID", "", "", Utility.getContext(
                    this, vars, "#User_Org", "CopyFromFIFO"),
                    Utility.getContext(this, vars, "#User_Client",
                            "CopyFromFIFO"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData,
                    "CopyFromFIFO", "");
            xmlDocument.setData("reportRCHR_Warpyarntype_ID", "liststructure",
                    comboTableData.select(false));
            comboTableData = null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        try {
            ComboTableData comboTableData = new ComboTableData(vars, this,
                    "TABLEDIR", "EPCG_Variant_ID", "", "", Utility.getContext(
                    this, vars, "#User_Org", "CopyFromFIFO"),
                    Utility.getContext(this, vars, "#User_Client",
                            "CopyFromFIFO"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData,
                    "CopyFromFIFO", "");
            xmlDocument.setData("reportEPCG_Variant_ID", "liststructure",
                    comboTableData.select(false));
            comboTableData = null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        try {
            ComboTableData comboTableData = new ComboTableData(vars, this,
                    "TABLEDIR", "EPCG_Conetype_ID", "", "", Utility.getContext(
                    this, vars, "#User_Org", "CopyFromFIFO"),
                    Utility.getContext(this, vars, "#User_Client",
                            "CopyFromFIFO"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData,
                    "CopyFromFIFO", "");
            xmlDocument.setData("reportEPCG_Conetype_ID", "liststructure",
                    comboTableData.select(false));
            comboTableData = null;
            } catch (Exception ex) {
            throw new ServletException(ex);
            }
            System.out.println("search"+indentId);
            System.out.println("yarn "+strYarntype+" vari"+strVariant+"cone.."+strConetype);
            CopyFromFIFOData[] dataOrder = CopyFromFIFOData.select(this,indentId,strYarntype,strVariant,strConetype);
            System.out.println("length"+dataOrder.length);
            xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
            xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
            xmlDocument.setParameter("theme", vars.getTheme());
            xmlDocument.setParameter("key", strKey);
            xmlDocument.setParameter("windowId", strWindowId);
            xmlDocument.setParameter("tabId", strTabId);
            xmlDocument.setData("structure1", dataOrder);

            xmlDocument.setParameter("rchrWapyarntypeId", strYarntype);
            xmlDocument.setParameter("epcgVariantId", strVariant);
            xmlDocument.setParameter("epcgConetype", strConetype);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(xmlDocument.print());
            out.close();


    }

    public String getServletInfo() {
        return "Servlet Copy from order";
    }
}

