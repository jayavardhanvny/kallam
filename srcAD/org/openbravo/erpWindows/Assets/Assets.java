
package org.openbravo.erpWindows.Assets;


import org.openbravo.erpCommon.reference.*;


import org.openbravo.erpCommon.ad_actionButton.*;


import org.codehaus.jettison.json.JSONObject;
import org.openbravo.erpCommon.utility.*;
import org.openbravo.data.FieldProvider;
import org.openbravo.utils.FormatUtilities;
import org.openbravo.utils.Replace;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.exception.OBException;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessRunner;
import org.openbravo.erpCommon.businessUtility.WindowTabs;
import org.openbravo.xmlEngine.XmlDocument;
import java.util.Vector;
import java.util.StringTokenizer;
import org.openbravo.database.SessionInfo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection;
import org.apache.log4j.Logger;

public class Assets extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Assets.class);
  
  private static final String windowId = "800027";
  private static final String tabId = "800078";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 1;
  private static final String moduleId = "0";
  
  @Override
  public void init(ServletConfig config) {
    setClassInfo("W", tabId, moduleId);
    super.init(config);
  }
  
  
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String command = vars.getCommand();
    
    boolean securedProcess = false;
    if (command.contains("BUTTON")) {
     SessionInfo.setUserId(vars.getSessionValue("#AD_User_ID"));
     SessionInfo.setSessionId(vars.getSessionValue("#AD_Session_ID"));
     
      if (command.contains("85601427EAEE401FA0250FF0A6DD62EF")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("85601427EAEE401FA0250FF0A6DD62EF");
        SessionInfo.setModuleId("0");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("800125")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("800125");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "800125";
        }
      }
     

     
      if (securedProcess && command.contains("85601427EAEE401FA0250FF0A6DD62EF")) {
        classInfo.type = "P";
        classInfo.id = "85601427EAEE401FA0250FF0A6DD62EF";
      }
     
    }
    if (!securedProcess) {
      setClassInfo("W", tabId, moduleId);
    }
    super.service(request, response);
  }
  

  public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
    TableSQLData tableSQL = null;
    VariablesSecureApp vars = new VariablesSecureApp(request);
    Boolean saveRequest = (Boolean) request.getAttribute("autosave");
    
    if(saveRequest != null && saveRequest){
      String currentOrg = vars.getStringParameter("inpadOrgId");
      String currentClient = vars.getStringParameter("inpadClientId");
      boolean editableTab = (!org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)
                            && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars,"#User_Org", windowId, accesslevel), currentOrg)) 
                            && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),currentClient)));
    
        OBError myError = new OBError();
        String commandType = request.getParameter("inpCommandType");
        String straAssetId = request.getParameter("inpaAssetId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !straAssetId.equals(""))
              total = saveRecord(vars, myError, 'U');
          else
              total = saveRecord(vars, myError, 'I');
          
          if (!myError.isEmpty() && total == 0)     
            throw new OBException(myError.getMessage());
        }
        vars.setSessionValue(request.getParameter("mappingName") +"|hash", vars.getPostDataHash());
        vars.setSessionValue(tabId + "|Header.view", "EDIT");
        
        return;
    }
    
    try {
      tableSQL = new TableSQLData(vars, this, tabId, Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    String strOrderBy = vars.getSessionValue(tabId + "|orderby");
    if (!strOrderBy.equals("")) {
      vars.setSessionValue(tabId + "|newOrder", "1");
    }

    if (vars.commandIn("DEFAULT")) {

      String strA_Asset_ID = vars.getGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|Assets.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strA_Asset_ID.equals("")) strA_Asset_ID = firstElement(vars, tableSQL);
          if (strA_Asset_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strA_Asset_ID, tableSQL);

      else printPageDataSheet(response, vars, strA_Asset_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strA_Asset_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strA_Asset_ID.equals("")) strA_Asset_ID = vars.getRequiredGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID");
      else vars.setSessionValue(windowId + "|A_Asset_ID", strA_Asset_ID);
      
      vars.setSessionValue(tabId + "|Assets.view", "EDIT");

      printPageEdit(response, request, vars, false, strA_Asset_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|Assets.view");
      String strA_Asset_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strA_Asset_ID = firstElement(vars, tableSQL);
          if (strA_Asset_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strA_Asset_ID.equals("")) strA_Asset_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strA_Asset_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamAD_Org_ID", tabId + "|paramAD_Org_ID");
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");
vars.getRequestGlobalVariable("inpParamName", tabId + "|paramName");
vars.getRequestGlobalVariable("inpParamM_Product_ID", tabId + "|paramM_Product_ID");
vars.getRequestGlobalVariable("inpParamA_Asset_Group_ID", tabId + "|paramA_Asset_Group_ID");
vars.getRequestGlobalVariable("inpParamAmortizationcalctype", tabId + "|paramAmortizationcalctype");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|A_Asset_ID");
      String strA_Asset_ID="";

      String strView = vars.getSessionValue(tabId + "|Assets.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strA_Asset_ID = firstElement(vars, tableSQL);
        if (strA_Asset_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Assets.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strA_Asset_ID, tableSQL);

      else printPageDataSheet(response, vars, strA_Asset_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strA_Asset_ID = vars.getGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID", "");
      vars.setSessionValue(tabId + "|Assets.view", "RELATION");
      printPageDataSheet(response, vars, strA_Asset_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strA_Asset_ID = vars.getGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID", "");
      vars.setSessionValue(tabId + "|Assets.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strA_Asset_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strA_Asset_ID = vars.getRequiredStringParameter("inpaAssetId");
      
      String strNext = nextElement(vars, strA_Asset_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strA_Asset_ID = vars.getRequiredStringParameter("inpaAssetId");
      
      String strPrevious = previousElement(vars, strA_Asset_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|Assets.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|Assets.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Assets.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Assets.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|A_Asset_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|Assets.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Assets.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|A_Asset_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {

      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {

      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {

      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I');      
      if (!myError.isEmpty()) {        
        response.sendRedirect(strDireccion + request.getServletPath() + "?Command=NEW");
      } 
      else {
		if (myError.isEmpty()) {
		  myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsInserted");
		  myError.setMessage(total + " " + myError.getMessage());
		  vars.setMessage(tabId, myError);
		}        
        if (vars.commandIn("SAVE_NEW_NEW")) response.sendRedirect(strDireccion + request.getServletPath() + "?Command=NEW");
        else if (vars.commandIn("SAVE_NEW_EDIT")) response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("SAVE_EDIT_RELATION", "SAVE_EDIT_NEW", "SAVE_EDIT_EDIT", "SAVE_EDIT_NEXT")) {

      String strA_Asset_ID = vars.getRequiredGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U');      
      if (!myError.isEmpty()) {
        response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
      } 
      else {
        if (myError.isEmpty()) {
          myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsUpdated");
          myError.setMessage(total + " " + myError.getMessage());
          vars.setMessage(tabId, myError);
        }
        if (vars.commandIn("SAVE_EDIT_NEW")) response.sendRedirect(strDireccion + request.getServletPath() + "?Command=NEW");
        else if (vars.commandIn("SAVE_EDIT_EDIT")) response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        else if (vars.commandIn("SAVE_EDIT_NEXT")) {
          String strNext = nextElement(vars, strA_Asset_ID, tableSQL);
          vars.setSessionValue(windowId + "|A_Asset_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strA_Asset_ID = vars.getRequiredStringParameter("inpaAssetId");
      //AssetsData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = AssetsData.delete(this, strA_Asset_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
        } catch(ServletException ex) {
          myError = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          if (!myError.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          } else vars.setMessage(tabId, myError);
        }
        if (myError==null && total==0) {
          myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
        }
        vars.removeSessionValue(windowId + "|aAssetId");
        vars.setSessionValue(tabId + "|Assets.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONProcessed800125")) {
        vars.setSessionValue("button800125.strprocessed", vars.getStringParameter("inpprocessed"));
        vars.setSessionValue("button800125.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button800125.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button800125.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button800125.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "800125", request.getServletPath());    
     } else if (vars.commandIn("BUTTON800125")) {
        String strA_Asset_ID = vars.getGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID", "");
        String strprocessed = vars.getSessionValue("button800125.strprocessed");
        String strProcessing = vars.getSessionValue("button800125.strProcessing");
        String strOrg = vars.getSessionValue("button800125.strOrg");
        String strClient = vars.getSessionValue("button800125.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessed800125(response, vars, strA_Asset_ID, strprocessed, strProcessing);
        }

    } else if (vars.commandIn("BUTTONProcess_Asset85601427EAEE401FA0250FF0A6DD62EF")) {
        vars.setSessionValue("button85601427EAEE401FA0250FF0A6DD62EF.strprocessAsset", vars.getStringParameter("inpprocessAsset"));
        vars.setSessionValue("button85601427EAEE401FA0250FF0A6DD62EF.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button85601427EAEE401FA0250FF0A6DD62EF.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button85601427EAEE401FA0250FF0A6DD62EF.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button85601427EAEE401FA0250FF0A6DD62EF.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "85601427EAEE401FA0250FF0A6DD62EF", request.getServletPath());
      } else if (vars.commandIn("BUTTON85601427EAEE401FA0250FF0A6DD62EF")) {
        String strA_Asset_ID = vars.getGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID", "");
        String strprocessAsset = vars.getSessionValue("button85601427EAEE401FA0250FF0A6DD62EF.strprocessAsset");
        String strProcessing = vars.getSessionValue("button85601427EAEE401FA0250FF0A6DD62EF.strProcessing");
        String strOrg = vars.getSessionValue("button85601427EAEE401FA0250FF0A6DD62EF.strOrg");
        String strClient = vars.getSessionValue("button85601427EAEE401FA0250FF0A6DD62EF.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcess_Asset85601427EAEE401FA0250FF0A6DD62EF(response, vars, strA_Asset_ID, strprocessAsset, strProcessing);
        }

    } else if (vars.commandIn("SAVE_BUTTONProcessed800125")) {
        String strA_Asset_ID = vars.getGlobalVariable("inpKey", windowId + "|A_Asset_ID", "");
        @SuppressWarnings("unused")
        String strprocessed = vars.getStringParameter("inpprocessed");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "800125", (("A_Asset_ID".equalsIgnoreCase("AD_Language"))?"0":strA_Asset_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
          ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
          new ProcessRunner(bundle).execute(this);
          
          PInstanceProcessData[] pinstanceData = PInstanceProcessData.select(this, pinstance);
          myMessage = Utility.getProcessInstanceMessage(this, vars, pinstanceData);
        } catch (ServletException ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          } else vars.setMessage(tabId, myMessage);
        }
        //close popup
        if (myMessage!=null) {
          if (log4j.isDebugEnabled()) log4j.debug(myMessage.getMessage());
          vars.setMessage(tabId, myMessage);
        }
        printPageClosePopUp(response, vars);

    } else if (vars.commandIn("SAVE_BUTTONProcess_Asset85601427EAEE401FA0250FF0A6DD62EF")) {
        String strA_Asset_ID = vars.getGlobalVariable("inpKey", windowId + "|A_Asset_ID", "");
        
        ProcessBundle pb = new ProcessBundle("85601427EAEE401FA0250FF0A6DD62EF", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("A_Asset_ID", strA_Asset_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new ProcessRunner(pb).execute(this);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          } else vars.setMessage(tabId, myMessage);
        }
        //close popup
        if (myMessage!=null) {
          if (log4j.isDebugEnabled()) log4j.debug(myMessage.getMessage());
          vars.setMessage(tabId, myMessage);
        }
        printPageClosePopUp(response, vars);





    } else if (vars.commandIn("SAVE_XHR")) {
      
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType);
        if (myError.isEmpty()) {
          myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsUpdated");
          myError.setMessage(total + " " + myError.getMessage());
          myError.setType("Success");
        }
        result.put("oberror", myError.toMap());
        result.put("tabid", vars.getStringParameter("tabID"));
        result.put("redirect", strDireccion + request.getServletPath() + "?Command=" + commandType);
      } catch (Exception e) {
        log4j.error("Error saving record (XHR request): " + e.getMessage(), e);
        myError.setType("Error");
        myError.setMessage(e.getMessage());
      }

      response.setContentType("application/json");
      PrintWriter out = response.getWriter();
      out.print(result.toString());
      out.flush();
      out.close();
    } else if (vars.getCommand().toUpperCase().startsWith("BUTTON") || vars.getCommand().toUpperCase().startsWith("SAVE_BUTTON")) {
      pageErrorPopUp(response);
    } else pageError(response);
  }
  private AssetsData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    AssetsData data = new AssetsData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.value = vars.getStringParameter("inpvalue");     data.name = vars.getRequiredStringParameter("inpname");     data.aAssetGroupId = vars.getRequiredStringParameter("inpaAssetGroupId");     data.aAssetGroupIdr = vars.getStringParameter("inpaAssetGroupId_R");     data.documentno = vars.getStringParameter("inpdocumentno");     data.description = vars.getStringParameter("inpdescription");     data.cCurrencyId = vars.getStringParameter("inpcCurrencyId");     data.cCurrencyIdr = vars.getStringParameter("inpcCurrencyId_R");     data.mProductId = vars.getRequestGlobalVariable("inpmProductId", windowId + "|M_Product_ID");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");     data.issummary = vars.getStringParameter("inpissummary", "N");     data.isstatic = vars.getStringParameter("inpisstatic", "N");     data.isdepreciated = vars.getStringParameter("inpisdepreciated", "N");     data.amortizationtype = vars.getRequiredStringParameter("inpamortizationtype");     data.amortizationtyper = vars.getStringParameter("inpamortizationtype_R");     data.amortizationcalctype = vars.getRequiredGlobalVariable("inpamortizationcalctype", windowId + "|Amortizationcalctype");     data.amortizationcalctyper = vars.getStringParameter("inpamortizationcalctype_R");    try {   data.annualamortizationpercentage = vars.getNumericParameter("inpannualamortizationpercentage");  } catch (ServletException paramEx) { ex = paramEx; }     data.assetschedule = vars.getRequiredStringParameter("inpassetschedule");     data.assetscheduler = vars.getStringParameter("inpassetschedule_R");    try {   data.uselifeyears = vars.getNumericParameter("inpuselifeyears");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.uselifemonths = vars.getNumericParameter("inpuselifemonths");  } catch (ServletException paramEx) { ex = paramEx; }     data.is30daymonth = vars.getStringParameter("inpis30daymonth", "N");     data.datepurchased = vars.getStringParameter("inpdatepurchased");     data.datecancelled = vars.getStringParameter("inpdatecancelled");     data.amortizationstartdate = vars.getStringParameter("inpamortizationstartdate");     data.amortizationenddate = vars.getStringParameter("inpamortizationenddate");    try {   data.assetvalueamt = vars.getNumericParameter("inpassetvalueamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.residualassetvalueamt = vars.getNumericParameter("inpresidualassetvalueamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.amortizationvalueamt = vars.getNumericParameter("inpamortizationvalueamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.depreciatedpreviousamt = vars.getNumericParameter("inpdepreciatedpreviousamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.processed = vars.getRequiredStringParameter("inpprocessed");    try {   data.depreciatedvalue = vars.getNumericParameter("inpdepreciatedvalue");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.depreciatedplan = vars.getNumericParameter("inpdepreciatedplan");  } catch (ServletException paramEx) { ex = paramEx; }     data.isfullydepreciated = vars.getStringParameter("inpisfullydepreciated", "N");     data.mAttributesetinstanceId = vars.getStringParameter("inpmAttributesetinstanceId");     data.isactive = vars.getStringParameter("inpisactive", "N");    try {   data.qty = vars.getNumericParameter("inpqty");  } catch (ServletException paramEx) { ex = paramEx; }     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.processAsset = vars.getRequiredStringParameter("inpprocessAsset");     data.cLocationId = vars.getStringParameter("inpcLocationId");     data.cBpartnerLocationId = vars.getStringParameter("inpcBpartnerLocationId");     data.aAssetId = vars.getRequestGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID");    try {   data.profit = vars.getNumericParameter("inpprofit");  } catch (ServletException paramEx) { ex = paramEx; }     data.lot = vars.getStringParameter("inplot");     data.isinposession = vars.getStringParameter("inpisinposession", "N");     data.adUserId = vars.getStringParameter("inpadUserId");     data.assetservicedate = vars.getStringParameter("inpassetservicedate");    try {   data.acctvalueamt = vars.getNumericParameter("inpacctvalueamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");    try {   data.useunits = vars.getNumericParameter("inpuseunits");  } catch (ServletException paramEx) { ex = paramEx; }     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");     data.assetdisposaldate = vars.getStringParameter("inpassetdisposaldate");     data.guaranteedate = vars.getStringParameter("inpguaranteedate");     data.isowned = vars.getStringParameter("inpisowned", "N");     data.help = vars.getStringParameter("inphelp");     data.assetdepreciationdate = vars.getStringParameter("inpassetdepreciationdate");     data.locationcomment = vars.getStringParameter("inplocationcomment");     data.versionno = vars.getStringParameter("inpversionno");     data.serno = vars.getStringParameter("inpserno");     data.mLocatorId = vars.getStringParameter("inpmLocatorId");     data.isdisposed = vars.getStringParameter("inpisdisposed", "N");    try {   data.lifeuseunits = vars.getNumericParameter("inplifeuseunits");  } catch (ServletException paramEx) { ex = paramEx; } 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



          vars.getGlobalVariable("inpattributeset", windowId + "|ATTRIBUTESET", "");
          vars.getGlobalVariable("inpattrsetvaluetype", windowId + "|ATTRSETVALUETYPE", "");
          vars.getGlobalVariable("inpexistamortizationlines", windowId + "|existAmortizationLines", "");
    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "A_Asset", "", "", false, true);

          if (data.value.equals("")) data.value = data.documentno;
    }
    catch(ServletException e) {
    	vars.setEditionData(tabId, data);
    	throw e;
    }
    // Behavior with exception for numeric fields is to catch last one if we have multiple ones
    if(ex != null) {
      vars.setEditionData(tabId, data);
      throw ex;
    }
    return data;
  }




    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|M_Product_ID", data[0].getField("mProductId"));    vars.setSessionValue(windowId + "|Amortizationcalctype", data[0].getField("amortizationcalctype"));    vars.setSessionValue(windowId + "|A_Asset_ID", data[0].getField("aAssetId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      AssetsData[] data = AssetsData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpaAssetId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
      refreshSessionEdit(vars, data);
    }

  private String nextElement(VariablesSecureApp vars, String strSelected, TableSQLData tableSQL) throws IOException, ServletException {
    if (strSelected == null || strSelected.equals("")) return firstElement(vars, tableSQL);
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(), 0, 0);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.NEXT, strSelected, tableSQL.getKeyColumn());
      } catch (Exception e) { 
        log4j.error("Error getting next element", e);
      }
      if (data!=null) {
        if (data!=null) return data;
      }
    }
    return strSelected;
  }

  private int getKeyPosition(VariablesSecureApp vars, String strSelected, TableSQLData tableSQL) throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("getKeyPosition: " + strSelected);
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(),0,0);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.GETPOSITION, strSelected, tableSQL.getKeyColumn());
      } catch (Exception e) { 
        log4j.error("Error getting key position", e);
      }
      if (data!=null) {
        // split offset -> (page,relativeOffset)
        int absoluteOffset = Integer.valueOf(data);
        int page = absoluteOffset / TableSQLData.maxRowsPerGridPage;
        int relativeOffset = absoluteOffset % TableSQLData.maxRowsPerGridPage;
        log4j.debug("getKeyPosition: absOffset: " + absoluteOffset + "=> page: " + page + " relOffset: " + relativeOffset);
        String currPageKey = tabId + "|" + "currentPage";
        vars.setSessionValue(currPageKey, String.valueOf(page));
        return relativeOffset;
      }
    }
    return 0;
  }

  private String previousElement(VariablesSecureApp vars, String strSelected, TableSQLData tableSQL) throws IOException, ServletException {
    if (strSelected == null || strSelected.equals("")) return firstElement(vars, tableSQL);
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(),0,0);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.PREVIOUS, strSelected, tableSQL.getKeyColumn());
      } catch (Exception e) { 
        log4j.error("Error getting previous element", e);
      }
      if (data!=null) {
        return data;
      }
    }
    return strSelected;
  }

  private String firstElement(VariablesSecureApp vars, TableSQLData tableSQL) throws IOException, ServletException {
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(),0,1);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.FIRST, "", tableSQL.getKeyColumn());

      } catch (Exception e) { 
        log4j.debug("Error getting first element", e);
      }
      if (data!=null) return data;
    }
    return "";
  }

  private String lastElement(VariablesSecureApp vars, TableSQLData tableSQL) throws IOException, ServletException {
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(),0,0);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.LAST, "", tableSQL.getKeyColumn());
      } catch (Exception e) { 
        log4j.error("Error getting last element", e);
      }
      if (data!=null) return data;
    }
    return "";
  }

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strA_Asset_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamAD_Org_ID = vars.getSessionValue(tabId + "|paramAD_Org_ID");
String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamM_Product_ID = vars.getSessionValue(tabId + "|paramM_Product_ID");
String strParamA_Asset_Group_ID = vars.getSessionValue(tabId + "|paramA_Asset_Group_ID");
String strParamAmortizationcalctype = vars.getSessionValue(tabId + "|paramAmortizationcalctype");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Org_ID) && ("").equals(strParamDocumentNo) && ("").equals(strParamName) && ("").equals(strParamM_Product_ID) && ("").equals(strParamA_Asset_Group_ID) && ("").equals(strParamAmortizationcalctype)) || !(("").equals(strParamAD_Org_ID) || ("%").equals(strParamAD_Org_ID))  || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamM_Product_ID) || ("%").equals(strParamM_Product_ID))  || !(("").equals(strParamA_Asset_Group_ID) || ("%").equals(strParamA_Asset_Group_ID))  || !(("").equals(strParamAmortizationcalctype) || ("%").equals(strParamAmortizationcalctype)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strA_Asset_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strA_Asset_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/Assets/Assets_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Assets", false, "document.frmMain.inpaAssetId", "grid", "..", "".equals("Y"), "Assets", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("Y".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());



    StringBuffer orderByArray = new StringBuffer();
      vars.setSessionValue(tabId + "|newOrder", "1");
      String positions = vars.getSessionValue(tabId + "|orderbyPositions");
      orderByArray.append("var orderByPositions = new Array(\n");
      if (!positions.equals("")) {
        StringTokenizer tokens=new StringTokenizer(positions, ",");
        boolean firstOrder = true;
        while(tokens.hasMoreTokens()){
          if (!firstOrder) orderByArray.append(",\n");
          orderByArray.append("\"").append(tokens.nextToken()).append("\"");
          firstOrder = false;
        }
      }
      orderByArray.append(");\n");
      String directions = vars.getSessionValue(tabId + "|orderbyDirections");
      orderByArray.append("var orderByDirections = new Array(\n");
      if (!positions.equals("")) {
        StringTokenizer tokens=new StringTokenizer(directions, ",");
        boolean firstOrder = true;
        while(tokens.hasMoreTokens()){
          if (!firstOrder) orderByArray.append(",\n");
          orderByArray.append("\"").append(tokens.nextToken()).append("\"");
          firstOrder = false;
        }
      }
      orderByArray.append(");\n");
//    }

    xmlDocument.setParameter("selectedColumn", "\nvar selectedRow = " + selectedRow + ";\n" + orderByArray.toString());
    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
    xmlDocument.setParameter("windowId", windowId);
    xmlDocument.setParameter("KeyName", "aAssetId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Assets_Relation.html", "Assets", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Assets_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.relationTemplate());
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    {
      OBError myMessage = vars.getMessage(tabId);
      vars.removeMessage(tabId);
      if (myMessage!=null) {
        xmlDocument.setParameter("messageType", myMessage.getType());
        xmlDocument.setParameter("messageTitle", myMessage.getTitle());
        xmlDocument.setParameter("messageMessage", myMessage.getMessage());
      }
    }


    xmlDocument.setParameter("grid", Utility.getContext(this, vars, "#RecordRange", windowId));
xmlDocument.setParameter("grid_Offset", strOffset);
xmlDocument.setParameter("grid_SortCols", positions);
xmlDocument.setParameter("grid_SortDirs", directions);
xmlDocument.setParameter("grid_Default", selectedRow);


    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strA_Asset_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " A_Asset.Name";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    AssetsData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamAD_Org_ID = vars.getSessionValue(tabId + "|paramAD_Org_ID");
String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamM_Product_ID = vars.getSessionValue(tabId + "|paramM_Product_ID");
String strParamA_Asset_Group_ID = vars.getSessionValue(tabId + "|paramA_Asset_Group_ID");
String strParamAmortizationcalctype = vars.getSessionValue(tabId + "|paramAmortizationcalctype");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Org_ID) && ("").equals(strParamDocumentNo) && ("").equals(strParamName) && ("").equals(strParamM_Product_ID) && ("").equals(strParamA_Asset_Group_ID) && ("").equals(strParamAmortizationcalctype)) || !(("").equals(strParamAD_Org_ID) || ("%").equals(strParamAD_Org_ID))  || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamM_Product_ID) || ("%").equals(strParamM_Product_ID))  || !(("").equals(strParamA_Asset_Group_ID) || ("%").equals(strParamA_Asset_Group_ID))  || !(("").equals(strParamAmortizationcalctype) || ("%").equals(strParamAmortizationcalctype)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = AssetsData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strA_Asset_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strA_Asset_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new AssetsData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("aAssetId") == null || dataField.getField("aAssetId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strATTRIBUTESET = AssetsData.selectAux0C9EAE5805B14C159CE74DAF015A12C9(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|ATTRIBUTESET", strATTRIBUTESET);
        String strATTRSETVALUETYPE = AssetsData.selectAux42236E76CCB2491B8B2E5BCDA4E83624(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|ATTRSETVALUETYPE", strATTRSETVALUETYPE);
        String strexistAmortizationLines = AssetsData.selectAuxAD306651FEC841DF9D01E831482C6A09(this, ((dataField!=null)?dataField.getField("aAssetId"):((data==null || data.length==0)?"":data[0].aAssetId)));
    vars.setSessionValue(windowId + "|existAmortizationLines", strexistAmortizationLines);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = AssetsData.set(Utility.getDefault(this, vars, "Process_Asset", "N", "800027", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "800042", Utility.getDefault(this, vars, "Process_Asset", "N", "800027", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "800042", Utility.getDefault(this, vars, "Process_Asset", "N", "800027", "N", dataField))), Utility.getDefault(this, vars, "C_Project_ID", "", "800027", "", dataField), AssetsData.selectDef3808ABE5D08E4650A2DE65596DB61F49_0(this, Utility.getDefault(this, vars, "C_Project_ID", "", "800027", "", dataField)), Utility.getDefault(this, vars, "Datepurchased", "", "800027", "", dataField), Utility.getDefault(this, vars, "Datecancelled", "", "800027", "", dataField), Utility.getDefault(this, vars, "Amortizationstartdate", "", "800027", "", dataField), Utility.getDefault(this, vars, "Amortizationenddate", "", "800027", "", dataField), Utility.getDefault(this, vars, "Annualamortizationpercentage", "", "800027", "", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "@C_Currency_ID@", "800027", "", dataField), Utility.getDefault(this, vars, "AssetValueAmt", "", "800027", "", dataField), Utility.getDefault(this, vars, "Residualassetvalueamt", "", "800027", "", dataField), Utility.getDefault(this, vars, "M_AttributeSetInstance_ID", "", "800027", "", dataField), Utility.getDefault(this, vars, "Acctvalueamt", "", "800027", "", dataField), Utility.getDefault(this, vars, "Amortizationtype", "", "800027", "", dataField), Utility.getDefault(this, vars, "Amortizationvalueamt", "", "800027", "", dataField), Utility.getDefault(this, vars, "Assetschedule", "", "800027", "", dataField), Utility.getDefault(this, vars, "DocumentNo", "", "800027", "", dataField), Utility.getDefault(this, vars, "Processed", "N", "800027", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "800042", Utility.getDefault(this, vars, "Processed", "N", "800027", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "800042", Utility.getDefault(this, vars, "Processed", "N", "800027", "N", dataField))), Utility.getDefault(this, vars, "Profit", "", "800027", "", dataField), Utility.getDefault(this, vars, "Qty", "", "800027", "", dataField), Utility.getDefault(this, vars, "Depreciatedvalue", "0", "800027", "", dataField), Utility.getDefault(this, vars, "Depreciatedplan", "0", "800027", "", dataField), Utility.getDefault(this, vars, "Amortizationcalctype", "", "800027", "", dataField), Utility.getDefault(this, vars, "Depreciatedpreviousamt", "0", "800027", "", dataField), Utility.getDefault(this, vars, "Lot", "", "800027", "", dataField), Utility.getDefault(this, vars, "AD_User_ID", "", "800027", "", dataField), Utility.getDefault(this, vars, "IsDisposed", "", "800027", "N", dataField), Utility.getDefault(this, vars, "M_Locator_ID", "", "800027", "", dataField), Utility.getDefault(this, vars, "IsInPosession", "", "800027", "N", dataField), "Y", Utility.getDefault(this, vars, "AssetDepreciationDate", "", "800027", "", dataField), Utility.getDefault(this, vars, "CreatedBy", "", "800027", "", dataField), AssetsData.selectDef8045_1(this, Utility.getDefault(this, vars, "CreatedBy", "", "800027", "", dataField)), Utility.getDefault(this, vars, "UseLifeYears", "", "800027", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "800027", "", dataField), AssetsData.selectDef8047_2(this,  vars.getLanguage(), Utility.getDefault(this, vars, "M_Product_ID", "", "800027", "", dataField)), Utility.getDefault(this, vars, "AssetDisposalDate", "", "800027", "", dataField), Utility.getDefault(this, vars, "C_Location_ID", "", "800027", "", dataField), Utility.getDefault(this, vars, "A_Asset_Group_ID", "", "800027", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "800027", "", dataField), Utility.getDefault(this, vars, "VersionNo", "", "800027", "", dataField), Utility.getDefault(this, vars, "Value", "", "800027", "", dataField), Utility.getDefault(this, vars, "IsOwned", "", "800027", "N", dataField), Utility.getDefault(this, vars, "Name", "", "800027", "", dataField), Utility.getDefault(this, vars, "Description", "", "800027", "", dataField), Utility.getDefault(this, vars, "LocationComment", "", "800027", "", dataField), Utility.getDefault(this, vars, "IsDepreciated", "", "800027", "N", dataField), Utility.getDefault(this, vars, "LifeUseUnits", "", "800027", "", dataField), Utility.getDefault(this, vars, "Help", "", "800027", "", dataField), Utility.getDefault(this, vars, "UpdatedBy", "", "800027", "", dataField), AssetsData.selectDef8063_3(this, Utility.getDefault(this, vars, "UpdatedBy", "", "800027", "", dataField)), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "800027", "", dataField), Utility.getDefault(this, vars, "C_BPartner_ID", "", "800027", "", dataField), Utility.getDefault(this, vars, "GuaranteeDate", "", "800027", "", dataField), Utility.getDefault(this, vars, "UseLifeMonths", "", "800027", "", dataField), Utility.getDefault(this, vars, "AssetServiceDate", "", "800027", "", dataField), Utility.getDefault(this, vars, "UseUnits", "", "800027", "", dataField), "", Utility.getDefault(this, vars, "SerNo", "", "800027", "", dataField), Utility.getDefault(this, vars, "C_BPartner_Location_ID", "", "800027", "", dataField), Utility.getDefault(this, vars, "IsFullyDepreciated", "N", "800027", "N", dataField), Utility.getDefault(this, vars, "IsStatic", "N", "800027", "N", dataField), Utility.getDefault(this, vars, "Issummary", "N", "800027", "N", dataField), Utility.getDefault(this, vars, "Is30DayMonth", "Y", "800027", "N", dataField));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "A_Asset", "", "", false, false) + ">";
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/Assets/Assets_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/Assets/Assets_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Assets", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpaAssetId", "", "..", "".equals("Y"), "Assets", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strA_Asset_ID), !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    toolbar.setDeleteable(true);
    toolbar.prepareEditionTemplate("Y".equals("Y"), hasSearchCondition, vars.getSessionValue("#ShowTest", "N").equals("Y"), "STD", Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    // set updated timestamp to manage locking mechanism
    if (!boolNew) {
      xmlDocument.setParameter("updatedTimestamp", (dataField != null ? dataField
          .getField("updatedTimeStamp") : data[0].getField("updatedTimeStamp")));
    }
    
    boolean concurrentSave = vars.getSessionValue(tabId + "|concurrentSave").equals("true");
    if (concurrentSave) {
      //after concurrent save error, force autosave
      xmlDocument.setParameter("autosave", "Y");
    } else {
      xmlDocument.setParameter("autosave", "N");
    }
    vars.removeSessionValue(tabId + "|concurrentSave");

    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, true, (strCommand.equalsIgnoreCase("NEW")));
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      // if (!strA_Asset_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Assets_Relation.html", "Assets", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Assets_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("ATTRIBUTESET", strATTRIBUTESET);
        xmlDocument.setParameter("ATTRSETVALUETYPE", strATTRSETVALUETYPE);
        xmlDocument.setParameter("existAmortizationLines", strexistAmortizationLines);
    
    
    xmlDocument.setParameter("commandType", strCommand);
    xmlDocument.setParameter("buscador",buscador);
    xmlDocument.setParameter("windowId", windowId);
    xmlDocument.setParameter("changed", "");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    final String strMappingName = Utility.getTabURL(tabId, "E", false);
    xmlDocument.setParameter("mappingName", strMappingName);
    xmlDocument.setParameter("confirmOnChanges", Utility.getJSConfirmOnChanges(vars, windowId));
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));

    xmlDocument.setParameter("paramSessionDate", strParamSessionDate);

    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
    OBError myMessage = vars.getMessage(tabId);
    vars.removeMessage(tabId);
    if (myMessage!=null) {
      xmlDocument.setParameter("messageType", myMessage.getType());
      xmlDocument.setParameter("messageTitle", myMessage.getTitle());
      xmlDocument.setParameter("messageMessage", myMessage.getMessage());
    }
    xmlDocument.setParameter("displayLogic", getDisplayLogicContext(vars, boolNew));
    
    
     if (dataField==null) {
      xmlDocument.setData("structure1",data);
      
    } else {
      
        FieldProvider[] dataAux = new FieldProvider[1];
        dataAux[0] = dataField;
        
        xmlDocument.setData("structure1",dataAux);
      
    }
    
      
   
    try {
      ComboTableData comboTableData = null;
String userOrgList = "";
if (editableTab) 
  userOrgList=Utility.getContext(this, vars, "#User_Org", windowId, accesslevel); //editable record 
else 
  userOrgList=currentOrg;
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "A_Asset_Group_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("aAssetGroupId"):dataField.getField("aAssetGroupId")));
xmlDocument.setData("reportA_Asset_Group_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Currency_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCurrencyId"):dataField.getField("cCurrencyId")));
xmlDocument.setData("reportC_Currency_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Amortizationtype", "800040", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("amortizationtype"):dataField.getField("amortizationtype")));
xmlDocument.setData("reportAmortizationtype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Amortizationcalctype", "800068", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("amortizationcalctype"):dataField.getField("amortizationcalctype")));
xmlDocument.setData("reportAmortizationcalctype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonAnnualamortizationpercentage", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "17", "Assetschedule", "800041", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("assetschedule"):dataField.getField("assetschedule")));
xmlDocument.setData("reportAssetschedule","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Datepurchased_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Datecancelled_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Amortizationstartdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Amortizationenddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonAssetValueAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonResidualassetvalueamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonAmortizationvalueamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDepreciatedpreviousamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDepreciatedvalue", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDepreciatedplan", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Process_Asset_BTNname", Utility.getButtonName(this, vars, "800042", (dataField==null?data[0].getField("processAsset"):dataField.getField("processAsset")), "Process_Asset_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcess_Asset = org.openbravo.erpCommon.utility.Utility.isModalProcess("85601427EAEE401FA0250FF0A6DD62EF"); 
xmlDocument.setParameter("Process_Asset_Modal", modalProcess_Asset?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("AssetDisposalDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("AssetDepreciationDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonProfit", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("GuaranteeDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("AssetServiceDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonAcctvalueamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ServletException(ex);
    }

    xmlDocument.setParameter("scriptOnLoad", getShortcutScript(usedButtonShortCuts, reservedButtonShortCuts));
    
    final String refererURL = vars.getSessionValue(tabId + "|requestURL");
    vars.removeSessionValue(tabId + "|requestURL");
    if(!refererURL.equals("")) {
    	final Boolean failedAutosave = (Boolean) vars.getSessionObject(tabId + "|failedAutosave");
		vars.removeSessionValue(tabId + "|failedAutosave");
    	if(failedAutosave != null && failedAutosave) {
    		final String jsFunction = "continueUserAction('"+refererURL+"');";
    		xmlDocument.setParameter("failedAutosave", jsFunction);
    	}
    }

    if (strCommand.equalsIgnoreCase("NEW")) {
      vars.removeSessionValue(tabId + "|failedAutosave");
      vars.removeSessionValue(strMappingName + "|hash");
    }

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  private void printPageButtonFS(HttpServletResponse response, VariablesSecureApp vars, String strProcessId, String path) throws IOException, ServletException {
    log4j.debug("Output: Frames action button");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "org/openbravo/erpCommon/ad_actionButton/ActionButtonDefaultFrames").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
    xmlDocument.setParameter("trlFormType", "PROCESS");
    xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    xmlDocument.setParameter("type", strDireccion + path);
    out.println(xmlDocument.print());
    out.close();
  }

    private void printPageButtonProcessed800125(HttpServletResponse response, VariablesSecureApp vars, String strA_Asset_ID, String strprocessed, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800125");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Processed800125", discard).createXmlDocument();
      xmlDocument.setParameter("key", strA_Asset_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Assets_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "800125");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("800125");
        vars.removeMessage("800125");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }


    void printPageButtonProcess_Asset85601427EAEE401FA0250FF0A6DD62EF(HttpServletResponse response, VariablesSecureApp vars, String strA_Asset_ID, String strprocessAsset, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 85601427EAEE401FA0250FF0A6DD62EF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Process_Asset85601427EAEE401FA0250FF0A6DD62EF", discard).createXmlDocument();
      xmlDocument.setParameter("key", strA_Asset_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Assets_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "85601427EAEE401FA0250FF0A6DD62EF");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("85601427EAEE401FA0250FF0A6DD62EF");
        vars.removeMessage("85601427EAEE401FA0250FF0A6DD62EF");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }


    private String getDisplayLogicContext(VariablesSecureApp vars, boolean isNew) throws IOException, ServletException {
      log4j.debug("Output: Display logic context fields");
      String result = "var str$Element_PJ=\"" +Utility.getContext(this, vars, "$Element_PJ", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
      return result;
    }


    private String getReadOnlyLogicContext(VariablesSecureApp vars) throws IOException, ServletException {
      log4j.debug("Output: Read Only logic context fields");
      String result = "";
      return result;
    }




 
  private String getShortcutScript( HashMap<String, String> usedButtonShortCuts, HashMap<String, String> reservedButtonShortCuts){
    StringBuffer shortcuts = new StringBuffer();
    shortcuts.append(" function buttonListShorcuts() {\n");
    Iterator<String> ik = usedButtonShortCuts.keySet().iterator();
    Iterator<String> iv = usedButtonShortCuts.values().iterator();
    while(ik.hasNext() && iv.hasNext()){
      shortcuts.append("  keyArray[keyArray.length] = new keyArrayItem(\"").append(ik.next()).append("\", \"").append(iv.next()).append("\", null, \"altKey\", false, \"onkeydown\");\n");
    }
    shortcuts.append(" return true;\n}");
    return shortcuts.toString();
  }
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type) throws IOException, ServletException {
    AssetsData data = null;
    int total = 0;
    if (org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) {
        OBError newError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        myError.setError(newError);
        vars.setMessage(tabId, myError);
    }
    else {
        Connection con = null;
        try {
            con = this.getTransactionConnection();
            data = getEditVariables(con, vars);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.aAssetId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (AssetsData.getCurrentDBTimestamp(this, data.aAssetId).equals(
                vars.getStringParameter("updatedTimestamp"))) {
                total = data.update(con, this);
               } else {
                 myError.setMessage(Replace.replace(Replace.replace(Utility.messageBD(this,
                    "SavingModifiedRecord", vars.getLanguage()), "\\n", "<br/>"), "&quot;", "\""));
                 myError.setType("Error");
                 vars.setSessionValue(tabId + "|concurrentSave", "true");
               } 
		     }		            
          
            }
                else {
            OBError newError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
            myError.setError(newError);            
          }
          releaseCommitConnection(con);
        } catch(Exception ex) {
            OBError newError = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
            myError.setError(newError);   
            try {
              releaseRollbackConnection(con);
            } catch (final Exception e) { //do nothing 
            }           
        }
            
        if (myError.isEmpty() && total == 0) {
            OBError newError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=DBExecuteError");
            myError.setError(newError);
        }
        vars.setMessage(tabId, myError);
            
        if(!myError.isEmpty()){
            if(data != null ) {
                if(type == 'I') {            			
                    data.aAssetId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.processAssetBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "800042", data.getField("Process_Asset"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|A_Asset_ID", data.aAssetId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Assets. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
