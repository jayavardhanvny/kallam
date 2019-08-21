
package org.openbravo.erpWindows.GeneralLedgerConfiguration;


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

public class Defaults extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Defaults.class);
  
  private static final String windowId = "125";
  private static final String tabId = "252";
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
     
      if (command.contains("108")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("108");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "108";
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
        String strcAcctschemaDefaultId = request.getParameter("inpcAcctschemaDefaultId");
         String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strcAcctschemaDefaultId.equals(""))
              total = saveRecord(vars, myError, 'U', strPC_AcctSchema_ID);
          else
              total = saveRecord(vars, myError, 'I', strPC_AcctSchema_ID);
          
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
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID", "");

      String strC_Acctschema_Default_ID = vars.getGlobalVariable("inpcAcctschemaDefaultId", windowId + "|C_Acctschema_Default_ID", "");
            if (strPC_AcctSchema_ID.equals("")) {
        strPC_AcctSchema_ID = getParentID(vars, strC_Acctschema_Default_ID);
        if (strPC_AcctSchema_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|C_AcctSchema_ID");
        vars.setSessionValue(windowId + "|C_AcctSchema_ID", strPC_AcctSchema_ID);

        refreshParentSession(vars, strPC_AcctSchema_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Defaults.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strC_Acctschema_Default_ID.equals("")) strC_Acctschema_Default_ID = firstElement(vars, tableSQL);
          if (strC_Acctschema_Default_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_Acctschema_Default_ID, strPC_AcctSchema_ID, tableSQL);

      else printPageDataSheet(response, vars, strPC_AcctSchema_ID, strC_Acctschema_Default_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strC_Acctschema_Default_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strC_Acctschema_Default_ID.equals("")) strC_Acctschema_Default_ID = vars.getRequiredGlobalVariable("inpcAcctschemaDefaultId", windowId + "|C_Acctschema_Default_ID");
      else vars.setSessionValue(windowId + "|C_Acctschema_Default_ID", strC_Acctschema_Default_ID);
      
      
      String strPC_AcctSchema_ID = getParentID(vars, strC_Acctschema_Default_ID);
      
      vars.setSessionValue(windowId + "|C_AcctSchema_ID", strPC_AcctSchema_ID);
      vars.setSessionValue("199|General Ledger Configuration.view", "EDIT");

      refreshParentSession(vars, strPC_AcctSchema_ID);

      vars.setSessionValue(tabId + "|Defaults.view", "EDIT");

      printPageEdit(response, request, vars, false, strC_Acctschema_Default_ID, strPC_AcctSchema_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|C_Acctschema_Default_ID");
      refreshParentSession(vars, strPC_AcctSchema_ID);


      String strView = vars.getSessionValue(tabId + "|Defaults.view");
      String strC_Acctschema_Default_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strC_Acctschema_Default_ID = firstElement(vars, tableSQL);
          if (strC_Acctschema_Default_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strC_Acctschema_Default_ID.equals("")) strC_Acctschema_Default_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strC_Acctschema_Default_ID, strPC_AcctSchema_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPC_AcctSchema_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamC_AcctSchema_ID", tabId + "|paramC_AcctSchema_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");

      
      vars.removeSessionValue(windowId + "|C_Acctschema_Default_ID");
      String strC_Acctschema_Default_ID="";

      String strView = vars.getSessionValue(tabId + "|Defaults.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strC_Acctschema_Default_ID = firstElement(vars, tableSQL);
        if (strC_Acctschema_Default_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Defaults.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_Acctschema_Default_ID, strPC_AcctSchema_ID, tableSQL);

      else printPageDataSheet(response, vars, strPC_AcctSchema_ID, strC_Acctschema_Default_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
      

      String strC_Acctschema_Default_ID = vars.getGlobalVariable("inpcAcctschemaDefaultId", windowId + "|C_Acctschema_Default_ID", "");
      vars.setSessionValue(tabId + "|Defaults.view", "RELATION");
      printPageDataSheet(response, vars, strPC_AcctSchema_ID, strC_Acctschema_Default_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");


      printPageEdit(response, request, vars, true, "", strPC_AcctSchema_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strC_Acctschema_Default_ID = vars.getGlobalVariable("inpcAcctschemaDefaultId", windowId + "|C_Acctschema_Default_ID", "");
      vars.setSessionValue(tabId + "|Defaults.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strC_Acctschema_Default_ID, strPC_AcctSchema_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
      String strC_Acctschema_Default_ID = vars.getRequiredStringParameter("inpcAcctschemaDefaultId");
      
      String strNext = nextElement(vars, strC_Acctschema_Default_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPC_AcctSchema_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
      String strC_Acctschema_Default_ID = vars.getRequiredStringParameter("inpcAcctschemaDefaultId");
      
      String strPrevious = previousElement(vars, strC_Acctschema_Default_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPC_AcctSchema_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");

      vars.setSessionValue(tabId + "|Defaults.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Defaults.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Defaults.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Defaults.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|C_Acctschema_Default_ID");
      vars.setSessionValue(windowId + "|C_AcctSchema_ID", strPC_AcctSchema_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Defaults.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Defaults.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|C_Acctschema_Default_ID");
      vars.setSessionValue(windowId + "|C_AcctSchema_ID", strPC_AcctSchema_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPC_AcctSchema_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPC_AcctSchema_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPC_AcctSchema_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPC_AcctSchema_ID);      
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
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
      String strC_Acctschema_Default_ID = vars.getRequiredGlobalVariable("inpcAcctschemaDefaultId", windowId + "|C_Acctschema_Default_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPC_AcctSchema_ID);      
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
          String strNext = nextElement(vars, strC_Acctschema_Default_ID, tableSQL);
          vars.setSessionValue(windowId + "|C_Acctschema_Default_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");

      String strC_Acctschema_Default_ID = vars.getRequiredStringParameter("inpcAcctschemaDefaultId");
      //DefaultsData data = getEditVariables(vars, strPC_AcctSchema_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = DefaultsData.delete(this, strC_Acctschema_Default_ID, strPC_AcctSchema_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|cAcctschemaDefaultId");
        vars.setSessionValue(tabId + "|Defaults.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONProcessing108")) {
        vars.setSessionValue("button108.strprocessing", vars.getStringParameter("inpprocessing"));
        vars.setSessionValue("button108.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button108.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button108.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("C_AcctSchema_ID", vars.getStringParameter("inpcAcctschemaId"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button108.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "108", request.getServletPath());    
     } else if (vars.commandIn("BUTTON108")) {
        String strC_Acctschema_Default_ID = vars.getGlobalVariable("inpcAcctschemaDefaultId", windowId + "|C_Acctschema_Default_ID", "");
        String strprocessing = vars.getSessionValue("button108.strprocessing");
        String strProcessing = vars.getSessionValue("button108.strProcessing");
        String strOrg = vars.getSessionValue("button108.strOrg");
        String strClient = vars.getSessionValue("button108.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessing108(response, vars, strC_Acctschema_Default_ID, strprocessing, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcessing108")) {
        String strC_Acctschema_Default_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Acctschema_Default_ID", "");
        @SuppressWarnings("unused")
        String strprocessing = vars.getStringParameter("inpprocessing");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "108", (("C_Acctschema_Default_ID".equalsIgnoreCase("AD_Language"))?"0":strC_Acctschema_Default_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_Client_ID", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
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
      String strPC_AcctSchema_ID = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPC_AcctSchema_ID);
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
  private DefaultsData getEditVariables(Connection con, VariablesSecureApp vars, String strPC_AcctSchema_ID) throws IOException,ServletException {
    DefaultsData data = new DefaultsData();
    ServletException ex = null;
    try {
    data.cReceivableAcct = vars.getRequiredStringParameter("inpcReceivableAcct");     data.cPrepaymentAcct = vars.getStringParameter("inpcPrepaymentAcct");     data.writeoffAcct = vars.getRequiredStringParameter("inpwriteoffAcct");     data.writeoffRevAcct = vars.getStringParameter("inpwriteoffRevAcct");     data.vLiabilityAcct = vars.getRequiredStringParameter("inpvLiabilityAcct");     data.vPrepaymentAcct = vars.getStringParameter("inpvPrepaymentAcct");     data.notinvoicedreceiptsAcct = vars.getStringParameter("inpnotinvoicedreceiptsAcct");     data.doubtfuldebtAcct = vars.getStringParameter("inpdoubtfuldebtAcct");     data.baddebtexpenseAcct = vars.getStringParameter("inpbaddebtexpenseAcct");     data.baddebtrevenueAcct = vars.getStringParameter("inpbaddebtrevenueAcct");     data.allowancefordoubtfulAcct = vars.getStringParameter("inpallowancefordoubtfulAcct");     data.pAssetAcct = vars.getRequiredStringParameter("inppAssetAcct");     data.pExpenseAcct = vars.getRequiredStringParameter("inppExpenseAcct");     data.pDefExpenseAcct = vars.getStringParameter("inppDefExpenseAcct");     data.pRevenueAcct = vars.getRequiredStringParameter("inppRevenueAcct");     data.pDefRevenueAcct = vars.getStringParameter("inppDefRevenueAcct");     data.withholdingAcct = vars.getStringParameter("inpwithholdingAcct");     data.pCogsAcct = vars.getRequiredStringParameter("inppCogsAcct");     data.pInvoicepricevarianceAcct = vars.getStringParameter("inppInvoicepricevarianceAcct");     data.pRevenueReturnAcct = vars.getStringParameter("inppRevenueReturnAcct");     data.pCogsReturnAcct = vars.getStringParameter("inppCogsReturnAcct");     data.wInventoryAcct = vars.getStringParameter("inpwInventoryAcct");     data.wDifferencesAcct = vars.getRequiredStringParameter("inpwDifferencesAcct");     data.wRevaluationAcct = vars.getStringParameter("inpwRevaluationAcct");     data.pjWipAcct = vars.getStringParameter("inppjWipAcct");     data.bAssetAcct = vars.getRequiredStringParameter("inpbAssetAcct");     data.bIntransitAcct = vars.getRequiredStringParameter("inpbIntransitAcct");     data.bExpenseAcct = vars.getRequiredStringParameter("inpbExpenseAcct");     data.bRevaluationgainAcct = vars.getRequiredStringParameter("inpbRevaluationgainAcct");     data.bRevaluationlossAcct = vars.getRequiredStringParameter("inpbRevaluationlossAcct");     data.tDueAcct = vars.getRequiredStringParameter("inptDueAcct");     data.tCreditAcct = vars.getRequiredStringParameter("inptCreditAcct");     data.chExpenseAcct = vars.getRequiredStringParameter("inpchExpenseAcct");     data.cbAssetAcct = vars.getRequiredStringParameter("inpcbAssetAcct");     data.cbDifferencesAcct = vars.getRequiredStringParameter("inpcbDifferencesAcct");     data.cbCashtransferAcct = vars.getRequiredStringParameter("inpcbCashtransferAcct");     data.cbExpenseAcct = vars.getRequiredStringParameter("inpcbExpenseAcct");     data.cbReceiptAcct = vars.getRequiredStringParameter("inpcbReceiptAcct");     data.aDepreciationAcct = vars.getRequiredStringParameter("inpaDepreciationAcct");     data.bPaymentselectAcct = vars.getStringParameter("inpbPaymentselectAcct");     data.aAccumdepreciationAcct = vars.getRequiredStringParameter("inpaAccumdepreciationAcct");     data.processing = vars.getStringParameter("inpprocessing");     data.eExpenseAcct = vars.getStringParameter("inpeExpenseAcct");     data.ePrepaymentAcct = vars.getStringParameter("inpePrepaymentAcct");     data.pjAssetAcct = vars.getStringParameter("inppjAssetAcct");     data.vLiabilityServicesAcct = vars.getStringParameter("inpvLiabilityServicesAcct");     data.notinvoicedrevenueAcct = vars.getStringParameter("inpnotinvoicedrevenueAcct");     data.pPurchasepricevarianceAcct = vars.getStringParameter("inppPurchasepricevarianceAcct");     data.paydiscountExpAcct = vars.getStringParameter("inppaydiscountExpAcct");     data.paydiscountRevAcct = vars.getStringParameter("inppaydiscountRevAcct");     data.unrealizedgainAcct = vars.getStringParameter("inpunrealizedgainAcct");     data.unrealizedlossAcct = vars.getStringParameter("inpunrealizedlossAcct");     data.realizedgainAcct = vars.getStringParameter("inprealizedgainAcct");     data.realizedlossAcct = vars.getStringParameter("inprealizedlossAcct");     data.tExpenseAcct = vars.getStringParameter("inptExpenseAcct");     data.tLiabilityAcct = vars.getStringParameter("inptLiabilityAcct");     data.tReceivablesAcct = vars.getStringParameter("inptReceivablesAcct");     data.bInterestrevAcct = vars.getStringParameter("inpbInterestrevAcct");     data.bInterestexpAcct = vars.getStringParameter("inpbInterestexpAcct");     data.bUnidentifiedAcct = vars.getStringParameter("inpbUnidentifiedAcct");     data.bSettlementgainAcct = vars.getStringParameter("inpbSettlementgainAcct");     data.chRevenueAcct = vars.getStringParameter("inpchRevenueAcct");     data.unearnedrevenueAcct = vars.getStringParameter("inpunearnedrevenueAcct");     data.notinvoicedreceivablesAcct = vars.getStringParameter("inpnotinvoicedreceivablesAcct");     data.pTradediscountrecAcct = vars.getStringParameter("inppTradediscountrecAcct");     data.pTradediscountgrantAcct = vars.getStringParameter("inppTradediscountgrantAcct");     data.wInvactualadjustAcct = vars.getStringParameter("inpwInvactualadjustAcct");     data.bUnallocatedcashAcct = vars.getStringParameter("inpbUnallocatedcashAcct");     data.bSettlementlossAcct = vars.getStringParameter("inpbSettlementlossAcct");     data.cAcctschemaId = vars.getRequiredGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.aDisposalLoss = vars.getStringParameter("inpaDisposalLoss");     data.aDisposalGain = vars.getStringParameter("inpaDisposalGain");     data.cAcctschemaDefaultId = vars.getRequestGlobalVariable("inpcAcctschemaDefaultId", windowId + "|C_Acctschema_Default_ID");     data.isactive = vars.getStringParameter("inpisactive", "N"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.cAcctschemaId = vars.getGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPC_AcctSchema_ID) throws IOException,ServletException {
      
      GeneralLedgerConfigurationData[] data = GeneralLedgerConfigurationData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_AcctSchema_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|C_AcctSchema_ID", data[0].cAcctschemaId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|C_AcctSchema_ID", strPC_AcctSchema_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strC_Acctschema_Default_ID) throws ServletException {
    String strPC_AcctSchema_ID = DefaultsData.selectParentID(this, strC_Acctschema_Default_ID);
    if (strPC_AcctSchema_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strC_Acctschema_Default_ID);
      throw new ServletException("Parent record not found");
    }
    return strPC_AcctSchema_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|C_AcctSchema_ID", data[0].getField("cAcctschemaId"));    vars.setSessionValue(windowId + "|C_Acctschema_Default_ID", data[0].getField("cAcctschemaDefaultId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPC_AcctSchema_ID) throws IOException,ServletException {
      DefaultsData[] data = DefaultsData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_AcctSchema_ID, vars.getStringParameter("inpcAcctschemaDefaultId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPC_AcctSchema_ID, String strC_Acctschema_Default_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamC_AcctSchema_ID = vars.getSessionValue(tabId + "|paramC_AcctSchema_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamC_AcctSchema_ID)) || !(("").equals(strParamC_AcctSchema_ID) || ("%").equals(strParamC_AcctSchema_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strC_Acctschema_Default_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strC_Acctschema_Default_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GeneralLedgerConfiguration/Defaults_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Defaults", false, "document.frmMain.inpcAcctschemaDefaultId", "grid", "..", "".equals("Y"), "GeneralLedgerConfiguration", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPC_AcctSchema_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("2648", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "cAcctschemaDefaultId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Defaults_Relation.html", "GeneralLedgerConfiguration", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Defaults_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", DefaultsData.selectParent(this, strPC_AcctSchema_ID));
    else xmlDocument.setParameter("parent", DefaultsData.selectParentTrl(this, strPC_AcctSchema_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strC_Acctschema_Default_ID, String strPC_AcctSchema_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " C_AcctSchema_Default.IsActive";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    DefaultsData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamC_AcctSchema_ID = vars.getSessionValue(tabId + "|paramC_AcctSchema_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamC_AcctSchema_ID)) || !(("").equals(strParamC_AcctSchema_ID) || ("%").equals(strParamC_AcctSchema_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = DefaultsData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_AcctSchema_ID, strC_Acctschema_Default_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strC_Acctschema_Default_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new DefaultsData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("cAcctschemaDefaultId") == null || dataField.getField("cAcctschemaDefaultId").equals("")) {
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
        refreshSessionNew(vars, strPC_AcctSchema_ID);
        data = DefaultsData.set(strPC_AcctSchema_ID, Utility.getDefault(this, vars, "P_Revenue_Return_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "125", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "125", "", dataField), "Y", Utility.getDefault(this, vars, "Created", "", "125", "", dataField), Utility.getDefault(this, vars, "CreatedBy", "", "125", "", dataField), DefaultsData.selectDef3440_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "125", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "125", "", dataField), DefaultsData.selectDef3442_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "125", "", dataField)), Utility.getDefault(this, vars, "W_Inventory_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "W_Differences_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_Revenue_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_Expense_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_Asset_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_Cogs_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "E_Expense_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "E_Prepayment_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "C_Receivable_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "C_Prepayment_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "V_Liability_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "V_Prepayment_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "V_Liability_Services_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "PJ_Asset_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "DoubtfulDebt_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "NotInvoicedRevenue_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "NotInvoicedReceipts_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "Processing", "", "125", "N", dataField), Utility.getDefault(this, vars, "W_Revaluation_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_PurchasePriceVariance_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "PayDiscount_Exp_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "WriteOff_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "PayDiscount_Rev_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "UnrealizedGain_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "UnrealizedLoss_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "RealizedGain_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "RealizedLoss_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "Withholding_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "PJ_WIP_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "T_Expense_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "T_Liability_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "T_Receivables_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "T_Due_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "T_Credit_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_InTransit_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_Asset_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_Expense_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_InterestRev_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_InterestExp_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_Unidentified_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_SettlementGain_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_SettlementLoss_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_RevaluationGain_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_RevaluationLoss_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "Ch_Expense_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "Ch_Revenue_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "UnEarnedRevenue_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "NotInvoicedReceivables_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "CB_Asset_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "CB_Differences_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "CB_Expense_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "CB_Receipt_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_Def_Revenue_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_InvoicePriceVariance_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_TradeDiscountRec_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_TradeDiscountGrant_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "W_InvActualAdjust_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_PaymentSelect_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "B_UnallocatedCash_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "CB_CashTransfer_Acct", "", "125", "", dataField), "", Utility.getDefault(this, vars, "A_Accumdepreciation_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "A_Depreciation_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "A_Disposal_Gain", "", "125", "", dataField), Utility.getDefault(this, vars, "A_Disposal_Loss", "", "125", "", dataField), Utility.getDefault(this, vars, "P_Cogs_Return_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "Allowancefordoubtful_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "Writeoff_Rev_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "P_Def_Expense_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "BadDebtRevenue_Acct", "", "125", "", dataField), Utility.getDefault(this, vars, "Baddebtexpense_Acct", "", "125", "", dataField));
        
      }
     }
      
    String currentPOrg=GeneralLedgerConfigurationData.selectOrg(this, strPC_AcctSchema_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GeneralLedgerConfiguration/Defaults_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GeneralLedgerConfiguration/Defaults_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Defaults", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpcAcctschemaDefaultId", "", "..", "".equals("Y"), "GeneralLedgerConfiguration", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strC_Acctschema_Default_ID), !hasReadOnlyAccess);
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
      // if (!strC_Acctschema_Default_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Defaults_Relation.html", "GeneralLedgerConfiguration", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Defaults_Relation.html", strReplaceWith);
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
xmlDocument.setParameter("Processing_BTNname", Utility.getButtonName(this, vars, "3823", "Processing_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcessing = org.openbravo.erpCommon.utility.Utility.isModalProcess("108"); 
xmlDocument.setParameter("Processing_Modal", modalProcessing?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
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

    private void printPageButtonProcessing108(HttpServletResponse response, VariablesSecureApp vars, String strC_Acctschema_Default_ID, String strprocessing, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 108");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Processing108", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Acctschema_Default_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Defaults_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "108");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("108");
        vars.removeMessage("108");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Client_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "129", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button108.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Client_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_AcctSchema_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FDA7BA9355A6468DAF67E1C5288990A6", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button108.originalParams"), comboTableData, windowId, "");
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
      String result = "var strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\nvar strADVANCED_ASSETS_MANAGEMENT=\"" +Utility.getContext(this, vars, "ADVANCED_ASSETS_MANAGEMENT", windowId) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPC_AcctSchema_ID) throws IOException, ServletException {
    DefaultsData data = null;
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
            data = getEditVariables(con, vars, strPC_AcctSchema_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.cAcctschemaDefaultId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (DefaultsData.getCurrentDBTimestamp(this, data.cAcctschemaDefaultId).equals(
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
                    data.cAcctschemaDefaultId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|C_Acctschema_Default_ID", data.cAcctschemaDefaultId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Defaults. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
