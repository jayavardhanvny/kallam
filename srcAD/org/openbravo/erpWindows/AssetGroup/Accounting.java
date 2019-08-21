
package org.openbravo.erpWindows.AssetGroup;


import org.openbravo.erpCommon.reference.*;



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

public class Accounting extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Accounting.class);
  
  private static final String windowId = "252";
  private static final String tabId = "800204";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
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
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("800136")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("800136");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "800136";
        }
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
        String straAssetGroupAcctId = request.getParameter("inpaAssetGroupAcctId");
         String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !straAssetGroupAcctId.equals(""))
              total = saveRecord(vars, myError, 'U', strPA_Asset_Group_ID);
          else
              total = saveRecord(vars, myError, 'I', strPA_Asset_Group_ID);
          
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
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID", "");

      String strA_Asset_Group_Acct_ID = vars.getGlobalVariable("inpaAssetGroupAcctId", windowId + "|A_Asset_Group_Acct_ID", "");
            if (strPA_Asset_Group_ID.equals("")) {
        strPA_Asset_Group_ID = getParentID(vars, strA_Asset_Group_Acct_ID);
        if (strPA_Asset_Group_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|A_Asset_Group_ID");
        vars.setSessionValue(windowId + "|A_Asset_Group_ID", strPA_Asset_Group_ID);

        refreshParentSession(vars, strPA_Asset_Group_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Accounting.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strA_Asset_Group_Acct_ID.equals("")) strA_Asset_Group_Acct_ID = firstElement(vars, tableSQL);
          if (strA_Asset_Group_Acct_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strA_Asset_Group_Acct_ID, strPA_Asset_Group_ID, tableSQL);

      else printPageDataSheet(response, vars, strPA_Asset_Group_ID, strA_Asset_Group_Acct_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strA_Asset_Group_Acct_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strA_Asset_Group_Acct_ID.equals("")) strA_Asset_Group_Acct_ID = vars.getRequiredGlobalVariable("inpaAssetGroupAcctId", windowId + "|A_Asset_Group_Acct_ID");
      else vars.setSessionValue(windowId + "|A_Asset_Group_Acct_ID", strA_Asset_Group_Acct_ID);
      
      
      String strPA_Asset_Group_ID = getParentID(vars, strA_Asset_Group_Acct_ID);
      
      vars.setSessionValue(windowId + "|A_Asset_Group_ID", strPA_Asset_Group_ID);
      vars.setSessionValue("452|Asset Category.view", "EDIT");

      refreshParentSession(vars, strPA_Asset_Group_ID);

      vars.setSessionValue(tabId + "|Accounting.view", "EDIT");

      printPageEdit(response, request, vars, false, strA_Asset_Group_Acct_ID, strPA_Asset_Group_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|A_Asset_Group_Acct_ID");
      refreshParentSession(vars, strPA_Asset_Group_ID);


      String strView = vars.getSessionValue(tabId + "|Accounting.view");
      String strA_Asset_Group_Acct_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strA_Asset_Group_Acct_ID = firstElement(vars, tableSQL);
          if (strA_Asset_Group_Acct_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strA_Asset_Group_Acct_ID.equals("")) strA_Asset_Group_Acct_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strA_Asset_Group_Acct_ID, strPA_Asset_Group_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPA_Asset_Group_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamA_Asset_Group_ID", tabId + "|paramA_Asset_Group_ID");
vars.getRequestGlobalVariable("inpParamC_AcctSchema_ID", tabId + "|paramC_AcctSchema_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");

      
      vars.removeSessionValue(windowId + "|A_Asset_Group_Acct_ID");
      String strA_Asset_Group_Acct_ID="";

      String strView = vars.getSessionValue(tabId + "|Accounting.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strA_Asset_Group_Acct_ID = firstElement(vars, tableSQL);
        if (strA_Asset_Group_Acct_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Accounting.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strA_Asset_Group_Acct_ID, strPA_Asset_Group_ID, tableSQL);

      else printPageDataSheet(response, vars, strPA_Asset_Group_ID, strA_Asset_Group_Acct_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
      

      String strA_Asset_Group_Acct_ID = vars.getGlobalVariable("inpaAssetGroupAcctId", windowId + "|A_Asset_Group_Acct_ID", "");
      vars.setSessionValue(tabId + "|Accounting.view", "RELATION");
      printPageDataSheet(response, vars, strPA_Asset_Group_ID, strA_Asset_Group_Acct_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");


      printPageEdit(response, request, vars, true, "", strPA_Asset_Group_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strA_Asset_Group_Acct_ID = vars.getGlobalVariable("inpaAssetGroupAcctId", windowId + "|A_Asset_Group_Acct_ID", "");
      vars.setSessionValue(tabId + "|Accounting.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strA_Asset_Group_Acct_ID, strPA_Asset_Group_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
      String strA_Asset_Group_Acct_ID = vars.getRequiredStringParameter("inpaAssetGroupAcctId");
      
      String strNext = nextElement(vars, strA_Asset_Group_Acct_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPA_Asset_Group_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
      String strA_Asset_Group_Acct_ID = vars.getRequiredStringParameter("inpaAssetGroupAcctId");
      
      String strPrevious = previousElement(vars, strA_Asset_Group_Acct_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPA_Asset_Group_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");

      vars.setSessionValue(tabId + "|Accounting.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Accounting.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Accounting.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Accounting.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|A_Asset_Group_Acct_ID");
      vars.setSessionValue(windowId + "|A_Asset_Group_ID", strPA_Asset_Group_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Accounting.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Accounting.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|A_Asset_Group_Acct_ID");
      vars.setSessionValue(windowId + "|A_Asset_Group_ID", strPA_Asset_Group_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPA_Asset_Group_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPA_Asset_Group_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPA_Asset_Group_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPA_Asset_Group_ID);      
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
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
      String strA_Asset_Group_Acct_ID = vars.getRequiredGlobalVariable("inpaAssetGroupAcctId", windowId + "|A_Asset_Group_Acct_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPA_Asset_Group_ID);      
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
          String strNext = nextElement(vars, strA_Asset_Group_Acct_ID, tableSQL);
          vars.setSessionValue(windowId + "|A_Asset_Group_Acct_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");

      String strA_Asset_Group_Acct_ID = vars.getRequiredStringParameter("inpaAssetGroupAcctId");
      //AccountingData data = getEditVariables(vars, strPA_Asset_Group_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = AccountingData.delete(this, strA_Asset_Group_Acct_ID, strPA_Asset_Group_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|aAssetGroupAcctId");
        vars.setSessionValue(tabId + "|Accounting.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONProcessing800136")) {
        vars.setSessionValue("button800136.strprocessing", vars.getStringParameter("inpprocessing"));
        vars.setSessionValue("button800136.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button800136.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button800136.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("AD_Org_ID", vars.getStringParameter("inpadOrgId"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button800136.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "800136", request.getServletPath());    
     } else if (vars.commandIn("BUTTON800136")) {
        String strA_Asset_Group_Acct_ID = vars.getGlobalVariable("inpaAssetGroupAcctId", windowId + "|A_Asset_Group_Acct_ID", "");
        String strprocessing = vars.getSessionValue("button800136.strprocessing");
        String strProcessing = vars.getSessionValue("button800136.strProcessing");
        String strOrg = vars.getSessionValue("button800136.strOrg");
        String strClient = vars.getSessionValue("button800136.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessing800136(response, vars, strA_Asset_Group_Acct_ID, strprocessing, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcessing800136")) {
        String strA_Asset_Group_Acct_ID = vars.getGlobalVariable("inpKey", windowId + "|A_Asset_Group_Acct_ID", "");
        @SuppressWarnings("unused")
        String strprocessing = vars.getStringParameter("inpprocessing");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "800136", (("A_Asset_Group_Acct_ID".equalsIgnoreCase("AD_Language"))?"0":strA_Asset_Group_Acct_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          String straAssetGroupId = vars.getStringParameter("inpaAssetGroupId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "A_Asset_Group_ID", straAssetGroupId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcAcctschemaId = vars.getStringParameter("inpcAcctschemaId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "C_AcctSchema_ID", strcAcctschemaId, vars.getClient(), vars.getOrg(), vars.getUser());

          
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






    } else if (vars.commandIn("SAVE_XHR")) {
      String strPA_Asset_Group_ID = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPA_Asset_Group_ID);
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
  private AccountingData getEditVariables(Connection con, VariablesSecureApp vars, String strPA_Asset_Group_ID) throws IOException,ServletException {
    AccountingData data = new AccountingData();
    ServletException ex = null;
    try {
    data.cAcctschemaId = vars.getRequiredStringParameter("inpcAcctschemaId");     data.cAcctschemaIdr = vars.getStringParameter("inpcAcctschemaId_R");     data.aAccumdepreciationAcct = vars.getRequiredStringParameter("inpaAccumdepreciationAcct");     data.aDepreciationAcct = vars.getRequiredStringParameter("inpaDepreciationAcct");     data.processing = vars.getStringParameter("inpprocessing");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.aDisposalGain = vars.getStringParameter("inpaDisposalGain");     data.aDisposalLoss = vars.getStringParameter("inpaDisposalLoss");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.aAssetGroupId = vars.getRequiredStringParameter("inpaAssetGroupId");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.aAssetGroupAcctId = vars.getRequestGlobalVariable("inpaAssetGroupAcctId", windowId + "|A_Asset_Group_Acct_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.aAssetGroupId = vars.getGlobalVariable("inpaAssetGroupId", windowId + "|A_Asset_Group_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPA_Asset_Group_ID) throws IOException,ServletException {
      
      AssetCategoryData[] data = AssetCategoryData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPA_Asset_Group_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|A_Asset_Group_ID", data[0].aAssetGroupId);
      vars.setSessionValue(windowId + "|A_Asset_Group_ID", strPA_Asset_Group_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strA_Asset_Group_Acct_ID) throws ServletException {
    String strPA_Asset_Group_ID = AccountingData.selectParentID(this, strA_Asset_Group_Acct_ID);
    if (strPA_Asset_Group_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strA_Asset_Group_Acct_ID);
      throw new ServletException("Parent record not found");
    }
    return strPA_Asset_Group_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|A_Asset_Group_Acct_ID", data[0].getField("aAssetGroupAcctId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPA_Asset_Group_ID) throws IOException,ServletException {
      AccountingData[] data = AccountingData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPA_Asset_Group_ID, vars.getStringParameter("inpaAssetGroupAcctId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPA_Asset_Group_ID, String strA_Asset_Group_Acct_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamA_Asset_Group_ID = vars.getSessionValue(tabId + "|paramA_Asset_Group_ID");
String strParamC_AcctSchema_ID = vars.getSessionValue(tabId + "|paramC_AcctSchema_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamA_Asset_Group_ID) && ("").equals(strParamC_AcctSchema_ID)) || !(("").equals(strParamA_Asset_Group_ID) || ("%").equals(strParamA_Asset_Group_ID))  || !(("").equals(strParamC_AcctSchema_ID) || ("%").equals(strParamC_AcctSchema_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strA_Asset_Group_Acct_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strA_Asset_Group_Acct_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/AssetGroup/Accounting_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Accounting", false, "document.frmMain.inpaAssetGroupAcctId", "grid", "..", "".equals("Y"), "AssetGroup", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPA_Asset_Group_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("803127", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "aAssetGroupAcctId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Accounting_Relation.html", "AssetGroup", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Accounting_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", AccountingData.selectParent(this, strPA_Asset_Group_ID));
    else xmlDocument.setParameter("parent", AccountingData.selectParentTrl(this, strPA_Asset_Group_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strA_Asset_Group_Acct_ID, String strPA_Asset_Group_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " 1";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    AccountingData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamA_Asset_Group_ID = vars.getSessionValue(tabId + "|paramA_Asset_Group_ID");
String strParamC_AcctSchema_ID = vars.getSessionValue(tabId + "|paramC_AcctSchema_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamA_Asset_Group_ID) && ("").equals(strParamC_AcctSchema_ID)) || !(("").equals(strParamA_Asset_Group_ID) || ("%").equals(strParamA_Asset_Group_ID))  || !(("").equals(strParamC_AcctSchema_ID) || ("%").equals(strParamC_AcctSchema_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = AccountingData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPA_Asset_Group_ID, strA_Asset_Group_Acct_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strA_Asset_Group_Acct_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new AccountingData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("aAssetGroupAcctId") == null || dataField.getField("aAssetGroupAcctId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPA_Asset_Group_ID);
        data = AccountingData.set(strPA_Asset_Group_ID, "", Utility.getDefault(this, vars, "C_AcctSchema_ID", "", "252", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "252", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "252", "", dataField), "Y", Utility.getDefault(this, vars, "Createdby", "", "252", "", dataField), AccountingData.selectDef802808_0(this, Utility.getDefault(this, vars, "Createdby", "", "252", "", dataField)), Utility.getDefault(this, vars, "Updatedby", "", "252", "", dataField), AccountingData.selectDef802810_1(this, Utility.getDefault(this, vars, "Updatedby", "", "252", "", dataField)), Utility.getDefault(this, vars, "A_Depreciation_Acct", "", "252", "", dataField), Utility.getDefault(this, vars, "A_Accumdepreciation_Acct", "", "252", "", dataField), Utility.getDefault(this, vars, "A_Disposal_Loss", "", "252", "", dataField), Utility.getDefault(this, vars, "A_Disposal_Gain", "", "252", "", dataField), Utility.getDefault(this, vars, "Processing", "N", "252", "N", dataField));
        
      }
     }
      
    String currentPOrg=AssetCategoryData.selectOrg(this, strPA_Asset_Group_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/AssetGroup/Accounting_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/AssetGroup/Accounting_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Accounting", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpaAssetGroupAcctId", "", "..", "".equals("Y"), "AssetGroup", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strA_Asset_Group_Acct_ID), !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    toolbar.setDeleteable(true);
    toolbar.prepareEditionTemplate("N".equals("Y"), hasSearchCondition, vars.getSessionValue("#ShowTest", "N").equals("Y"), "STD", Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
      // if (!strA_Asset_Group_Acct_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Accounting_Relation.html", "AssetGroup", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Accounting_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
    
    xmlDocument.setParameter("parentOrg", currentPOrg);
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
comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cAcctschemaId"):dataField.getField("cAcctschemaId")));
xmlDocument.setData("reportC_AcctSchema_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Processing_BTNname", Utility.getButtonName(this, vars, "803185", "Processing_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcessing = org.openbravo.erpCommon.utility.Utility.isModalProcess("800136"); 
xmlDocument.setParameter("Processing_Modal", modalProcessing?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
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

    private void printPageButtonProcessing800136(HttpServletResponse response, VariablesSecureApp vars, String strA_Asset_Group_Acct_ID, String strprocessing, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800136");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Processing800136", discard).createXmlDocument();
      xmlDocument.setParameter("key", strA_Asset_Group_Acct_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Accounting_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "800136");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("800136");
        vars.removeMessage("800136");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("A_Asset_Group_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "A_Asset_Group_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button800136.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportA_Asset_Group_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_AcctSchema_ID", Utility.getContext(this, vars, "C_AcctSchema_ID", "252"));
    comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FF8081812F06A183012F07323A2A001C", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button800136.originalParams"), comboTableData, windowId, Utility.getContext(this, vars, "C_AcctSchema_ID", "252"));
    xmlDocument.setData("reportC_AcctSchema_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }




    private String getDisplayLogicContext(VariablesSecureApp vars, boolean isNew) throws IOException, ServletException {
      log4j.debug("Output: Display logic context fields");
      String result = "var strADVANCED_ASSETS_MANAGEMENT=\"" +Utility.getContext(this, vars, "ADVANCED_ASSETS_MANAGEMENT", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPA_Asset_Group_ID) throws IOException, ServletException {
    AccountingData data = null;
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
            data = getEditVariables(con, vars, strPA_Asset_Group_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.aAssetGroupAcctId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (AccountingData.getCurrentDBTimestamp(this, data.aAssetGroupAcctId).equals(
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
                    data.aAssetGroupAcctId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|A_Asset_Group_Acct_ID", data.aAssetGroupAcctId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Accounting. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
