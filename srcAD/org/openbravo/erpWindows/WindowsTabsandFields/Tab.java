
package org.openbravo.erpWindows.WindowsTabsandFields;


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

public class Tab extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Tab.class);
  
  private static final String windowId = "102";
  private static final String tabId = "106";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 4;
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
     
      if (command.contains("174")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("174");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "174";
        }
      }
     
      if (command.contains("114")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("114");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "114";
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
        String stradTabId = request.getParameter("inpadTabId");
         String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !stradTabId.equals(""))
              total = saveRecord(vars, myError, 'U', strPAD_Window_ID);
          else
              total = saveRecord(vars, myError, 'I', strPAD_Window_ID);
          
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
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID", "");

      String strAD_Tab_ID = vars.getGlobalVariable("inpadTabId", windowId + "|AD_Tab_ID", "");
            if (strPAD_Window_ID.equals("")) {
        strPAD_Window_ID = getParentID(vars, strAD_Tab_ID);
        if (strPAD_Window_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|AD_Window_ID");
        vars.setSessionValue(windowId + "|AD_Window_ID", strPAD_Window_ID);

        refreshParentSession(vars, strPAD_Window_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Tab.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strAD_Tab_ID.equals("")) strAD_Tab_ID = firstElement(vars, tableSQL);
          if (strAD_Tab_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strAD_Tab_ID, strPAD_Window_ID, tableSQL);

      else printPageDataSheet(response, vars, strPAD_Window_ID, strAD_Tab_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strAD_Tab_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strAD_Tab_ID.equals("")) strAD_Tab_ID = vars.getRequiredGlobalVariable("inpadTabId", windowId + "|AD_Tab_ID");
      else vars.setSessionValue(windowId + "|AD_Tab_ID", strAD_Tab_ID);
      
      
      String strPAD_Window_ID = getParentID(vars, strAD_Tab_ID);
      
      vars.setSessionValue(windowId + "|AD_Window_ID", strPAD_Window_ID);
      vars.setSessionValue("105|Window.view", "EDIT");

      refreshParentSession(vars, strPAD_Window_ID);

      vars.setSessionValue(tabId + "|Tab.view", "EDIT");

      printPageEdit(response, request, vars, false, strAD_Tab_ID, strPAD_Window_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|AD_Tab_ID");
      refreshParentSession(vars, strPAD_Window_ID);


      String strView = vars.getSessionValue(tabId + "|Tab.view");
      String strAD_Tab_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strAD_Tab_ID = firstElement(vars, tableSQL);
          if (strAD_Tab_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strAD_Tab_ID.equals("")) strAD_Tab_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strAD_Tab_ID, strPAD_Window_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPAD_Window_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamName", tabId + "|paramName");
vars.getRequestGlobalVariable("inpParamAD_Window_ID", tabId + "|paramAD_Window_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");

      
      vars.removeSessionValue(windowId + "|AD_Tab_ID");
      String strAD_Tab_ID="";

      String strView = vars.getSessionValue(tabId + "|Tab.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strAD_Tab_ID = firstElement(vars, tableSQL);
        if (strAD_Tab_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Tab.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strAD_Tab_ID, strPAD_Window_ID, tableSQL);

      else printPageDataSheet(response, vars, strPAD_Window_ID, strAD_Tab_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
      

      String strAD_Tab_ID = vars.getGlobalVariable("inpadTabId", windowId + "|AD_Tab_ID", "");
      vars.setSessionValue(tabId + "|Tab.view", "RELATION");
      printPageDataSheet(response, vars, strPAD_Window_ID, strAD_Tab_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");


      printPageEdit(response, request, vars, true, "", strPAD_Window_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strAD_Tab_ID = vars.getGlobalVariable("inpadTabId", windowId + "|AD_Tab_ID", "");
      vars.setSessionValue(tabId + "|Tab.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strAD_Tab_ID, strPAD_Window_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
      String strAD_Tab_ID = vars.getRequiredStringParameter("inpadTabId");
      
      String strNext = nextElement(vars, strAD_Tab_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPAD_Window_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
      String strAD_Tab_ID = vars.getRequiredStringParameter("inpadTabId");
      
      String strPrevious = previousElement(vars, strAD_Tab_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPAD_Window_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");

      vars.setSessionValue(tabId + "|Tab.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Tab.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Tab.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Tab.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|AD_Tab_ID");
      vars.setSessionValue(windowId + "|AD_Window_ID", strPAD_Window_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Tab.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Tab.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|AD_Tab_ID");
      vars.setSessionValue(windowId + "|AD_Window_ID", strPAD_Window_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPAD_Window_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPAD_Window_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPAD_Window_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPAD_Window_ID);      
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
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
      String strAD_Tab_ID = vars.getRequiredGlobalVariable("inpadTabId", windowId + "|AD_Tab_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPAD_Window_ID);      
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
          String strNext = nextElement(vars, strAD_Tab_ID, tableSQL);
          vars.setSessionValue(windowId + "|AD_Tab_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");

      String strAD_Tab_ID = vars.getRequiredStringParameter("inpadTabId");
      //TabData data = getEditVariables(vars, strPAD_Window_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = TabData.delete(this, strAD_Tab_ID, strPAD_Window_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|adTabId");
        vars.setSessionValue(tabId + "|Tab.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONImportFields174")) {
        vars.setSessionValue("button174.strimportfields", vars.getStringParameter("inpimportfields"));
        vars.setSessionValue("button174.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button174.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button174.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button174.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "174", request.getServletPath());    
     } else if (vars.commandIn("BUTTON174")) {
        String strAD_Tab_ID = vars.getGlobalVariable("inpadTabId", windowId + "|AD_Tab_ID", "");
        String strimportfields = vars.getSessionValue("button174.strimportfields");
        String strProcessing = vars.getSessionValue("button174.strProcessing");
        String strOrg = vars.getSessionValue("button174.strOrg");
        String strClient = vars.getSessionValue("button174.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonImportFields174(response, vars, strAD_Tab_ID, strimportfields, strProcessing);
        }

     } else if (vars.commandIn("BUTTONProcessing114")) {
        vars.setSessionValue("button114.strprocessing", vars.getStringParameter("inpprocessing"));
        vars.setSessionValue("button114.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button114.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button114.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button114.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "114", request.getServletPath());    
     } else if (vars.commandIn("BUTTON114")) {
        String strAD_Tab_ID = vars.getGlobalVariable("inpadTabId", windowId + "|AD_Tab_ID", "");
        String strprocessing = vars.getSessionValue("button114.strprocessing");
        String strProcessing = vars.getSessionValue("button114.strProcessing");
        String strOrg = vars.getSessionValue("button114.strOrg");
        String strClient = vars.getSessionValue("button114.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessing114(response, vars, strAD_Tab_ID, strprocessing, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONImportFields174")) {
        String strAD_Tab_ID = vars.getGlobalVariable("inpKey", windowId + "|AD_Tab_ID", "");
        @SuppressWarnings("unused")
        String strimportfields = vars.getStringParameter("inpimportfields");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "174", (("AD_Tab_ID".equalsIgnoreCase("AD_Language"))?"0":strAD_Tab_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
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
    } else if (vars.commandIn("SAVE_BUTTONProcessing114")) {
        String strAD_Tab_ID = vars.getGlobalVariable("inpKey", windowId + "|AD_Tab_ID", "");
        @SuppressWarnings("unused")
        String strprocessing = vars.getStringParameter("inpprocessing");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "114", (("AD_Tab_ID".equalsIgnoreCase("AD_Language"))?"0":strAD_Tab_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          String stradTabId = vars.getStringParameter("inpadTabId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_Tab_ID", stradTabId, vars.getClient(), vars.getOrg(), vars.getUser());

          
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
      String strPAD_Window_ID = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPAD_Window_ID);
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
  private TabData getEditVariables(Connection con, VariablesSecureApp vars, String strPAD_Window_ID) throws IOException,ServletException {
    TabData data = new TabData();
    ServletException ex = null;
    try {
    data.adModuleId = vars.getRequiredStringParameter("inpadModuleId");     data.adModuleIdr = vars.getStringParameter("inpadModuleId_R");     data.name = vars.getRequiredStringParameter("inpname");     data.adTableId = vars.getRequiredGlobalVariable("inpadTableId", windowId + "|AD_Table_ID");     data.adTableIdr = vars.getStringParameter("inpadTableId_R");     data.uipattern = vars.getRequiredStringParameter("inpuipattern");     data.uipatternr = vars.getStringParameter("inpuipattern_R");    try {   data.seqno = vars.getRequiredNumericParameter("inpseqno");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.tablevel = vars.getRequiredNumericParameter("inptablevel");  } catch (ServletException paramEx) { ex = paramEx; }     data.help = vars.getStringParameter("inphelp");     data.description = vars.getStringParameter("inpdescription");     data.whereclause = vars.getStringParameter("inpwhereclause");     data.hqlwhereclause = vars.getStringParameter("inphqlwhereclause");     data.filterclause = vars.getStringParameter("inpfilterclause");     data.hqlfilterclause = vars.getStringParameter("inphqlfilterclause");     data.filtername = vars.getStringParameter("inpfiltername");     data.orderbyclause = vars.getStringParameter("inporderbyclause");     data.hqlorderbyclause = vars.getStringParameter("inphqlorderbyclause");     data.isinfotab = vars.getStringParameter("inpisinfotab", "N");     data.istranslationtab = vars.getStringParameter("inpistranslationtab", "N");     data.issinglerow = vars.getStringParameter("inpissinglerow", "N");     data.hastree = vars.getStringParameter("inphastree", "N");     data.showparentbuttons = vars.getStringParameter("inpshowparentbuttons", "N");     data.adProcessId = vars.getStringParameter("inpadProcessId");     data.adProcessIdr = vars.getStringParameter("inpadProcessId_R");     data.editreference = vars.getStringParameter("inpeditreference");     data.editreferencer = vars.getStringParameter("inpeditreference_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.displaylogic = vars.getStringParameter("inpdisplaylogic");     data.importfields = vars.getStringParameter("inpimportfields");     data.processing = vars.getStringParameter("inpprocessing");     data.emObuiappCanAdd = vars.getStringParameter("inpemObuiappCanAdd", "N");     data.emObuiappCanDelete = vars.getStringParameter("inpemObuiappCanDelete", "N");     data.emObuiappSelection = vars.getStringParameter("inpemObuiappSelection");     data.emObuiappShowSelect = vars.getStringParameter("inpemObuiappShowSelect", "N");     data.emObuiappNewfn = vars.getStringParameter("inpemObuiappNewfn");     data.emObuiappRemovefn = vars.getStringParameter("inpemObuiappRemovefn");     data.adColumnId = vars.getStringParameter("inpadColumnId");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.adWindowId = vars.getRequiredStringParameter("inpadWindowId");     data.includedTabId = vars.getStringParameter("inpincludedTabId");     data.adTabId = vars.getRequestGlobalVariable("inpadTabId", windowId + "|AD_Tab_ID");     data.adColumnsortorderId = vars.getStringParameter("inpadColumnsortorderId");     data.adColumnsortyesnoId = vars.getStringParameter("inpadColumnsortyesnoId");     data.issorttab = vars.getStringParameter("inpissorttab", "N");     data.adImageId = vars.getStringParameter("inpadImageId");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.commitwarning = vars.getStringParameter("inpcommitwarning"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.adWindowId = vars.getGlobalVariable("inpadWindowId", windowId + "|AD_Window_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPAD_Window_ID) throws IOException,ServletException {
      
      WindowData[] data = WindowData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPAD_Window_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|WindowType", data[0].windowtype);    vars.setSessionValue(windowId + "|AD_Window_ID", data[0].adWindowId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);
      vars.setSessionValue(windowId + "|AD_Window_ID", strPAD_Window_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strAD_Tab_ID) throws ServletException {
    String strPAD_Window_ID = TabData.selectParentID(this, strAD_Tab_ID);
    if (strPAD_Window_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strAD_Tab_ID);
      throw new ServletException("Parent record not found");
    }
    return strPAD_Window_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Table_ID", data[0].getField("adTableId"));    vars.setSessionValue(windowId + "|AD_Tab_ID", data[0].getField("adTabId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPAD_Window_ID) throws IOException,ServletException {
      TabData[] data = TabData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPAD_Window_ID, vars.getStringParameter("inpadTabId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPAD_Window_ID, String strAD_Tab_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamAD_Window_ID = vars.getSessionValue(tabId + "|paramAD_Window_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamName) && ("").equals(strParamAD_Window_ID)) || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamAD_Window_ID) || ("%").equals(strParamAD_Window_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strAD_Tab_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strAD_Tab_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/WindowsTabsandFields/Tab_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Tab", false, "document.frmMain.inpadTabId", "grid", "..", "".equals("Y"), "WindowsTabsandFields", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPAD_Window_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("119", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "adTabId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Tab_Relation.html", "WindowsTabsandFields", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Tab_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", TabData.selectParent(this, vars.getLanguage(), strPAD_Window_ID));
    else xmlDocument.setParameter("parent", TabData.selectParentTrl(this, vars.getLanguage(), strPAD_Window_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strAD_Tab_ID, String strPAD_Window_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " AD_Tab.SeqNo";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    TabData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamAD_Window_ID = vars.getSessionValue(tabId + "|paramAD_Window_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamName) && ("").equals(strParamAD_Window_ID)) || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamAD_Window_ID) || ("%").equals(strParamAD_Window_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = TabData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPAD_Window_ID, strAD_Tab_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strAD_Tab_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new TabData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("adTabId") == null || dataField.getField("adTabId").equals("")) {
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
        refreshSessionNew(vars, strPAD_Window_ID);
        data = TabData.set(strPAD_Window_ID, Utility.getDefault(this, vars, "HasTree", "", "102", "N", dataField), "", Utility.getDefault(this, vars, "Name", "", "102", "", dataField), Utility.getDefault(this, vars, "Description", "", "102", "", dataField), Utility.getDefault(this, vars, "Help", "", "102", "", dataField), TabData.selectDef165(this, strPAD_Window_ID), Utility.getDefault(this, vars, "IsSingleRow", "", "102", "N", dataField), Utility.getDefault(this, vars, "Displaylogic", "", "102", "", dataField), Utility.getDefault(this, vars, "IsInfoTab", "", "102", "N", dataField), Utility.getDefault(this, vars, "IsTranslationTab", "", "102", "N", dataField), Utility.getDefault(this, vars, "IsReadOnly", "", "102", "N", dataField), Utility.getDefault(this, vars, "AD_Table_ID", "", "102", "", dataField), Utility.getDefault(this, vars, "AD_Column_ID", "", "102", "", dataField), Utility.getDefault(this, vars, "WhereClause", "", "102", "", dataField), Utility.getDefault(this, vars, "OrderByClause", "", "102", "", dataField), Utility.getDefault(this, vars, "CommitWarning", "", "102", "", dataField), Utility.getDefault(this, vars, "AD_Process_ID", "", "102", "", dataField), "0", Utility.getDefault(this, vars, "EM_Obuiapp_Show_Select", "Y", "102", "N", dataField), "0", Utility.getDefault(this, vars, "Processing", "", "102", "N", dataField), TabData.selectDef5661EC317B643D0FE040007F01004528(this, strPAD_Window_ID), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "102", "", dataField), TabData.selectDef575_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "102", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "102", "", dataField), TabData.selectDef577_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "102", "", dataField)), Utility.getDefault(this, vars, "EM_Obuiapp_Removefn", "", "102", "", dataField), Utility.getDefault(this, vars, "Hqlwhereclause", "", "102", "", dataField), Utility.getDefault(this, vars, "AD_Image_ID", "", "102", "", dataField), Utility.getDefault(this, vars, "ImportFields", "", "102", "N", dataField), Utility.getDefault(this, vars, "TabLevel", "", "102", "0", dataField), Utility.getDefault(this, vars, "UIPattern", "STD", "102", "", dataField), Utility.getDefault(this, vars, "IsSortTab", "N", "102", "N", dataField), Utility.getDefault(this, vars, "AD_ColumnSortYesNo_ID", "", "102", "", dataField), Utility.getDefault(this, vars, "AD_ColumnSortOrder_ID", "", "102", "", dataField), Utility.getDefault(this, vars, "EM_Obuiapp_Newfn", "", "102", "", dataField), Utility.getDefault(this, vars, "FilterClause", "", "102", "", dataField), Utility.getDefault(this, vars, "EditReference", "", "102", "", dataField), Utility.getDefault(this, vars, "Included_Tab_ID", "", "102", "", dataField), Utility.getDefault(this, vars, "EM_Obuiapp_Can_Delete", "N", "102", "N", dataField), Utility.getDefault(this, vars, "FilterName", "", "102", "", dataField), Utility.getDefault(this, vars, "ShowParentButtons", "Y", "102", "N", dataField), Utility.getDefault(this, vars, "Hqlorderbyclause", "", "102", "", dataField), Utility.getDefault(this, vars, "Hqlfilterclause", "", "102", "", dataField), Utility.getDefault(this, vars, "EM_Obuiapp_Can_Add", "N", "102", "N", dataField), Utility.getDefault(this, vars, "EM_OBUIAPP_Selection", "", "102", "", dataField));
        
      }
     }
      
    String currentPOrg=WindowData.selectOrg(this, strPAD_Window_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/WindowsTabsandFields/Tab_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/WindowsTabsandFields/Tab_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Tab", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpadTabId", "", "..", "".equals("Y"), "WindowsTabsandFields", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strAD_Tab_ID), !hasReadOnlyAccess);
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
      // if (!strAD_Tab_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Tab_Relation.html", "WindowsTabsandFields", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Tab_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "AD_Module_ID", "", "C45CFD74FF5145B0A356E9233D49996F", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adModuleId"):dataField.getField("adModuleId")));
xmlDocument.setData("reportAD_Module_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "AD_Table_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adTableId"):dataField.getField("adTableId")));
xmlDocument.setData("reportAD_Table_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "UIPattern", "D15C950D445D408E8CC8135E530C246B", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("uipattern"):dataField.getField("uipattern")));
xmlDocument.setData("reportUIPattern","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "AD_Process_ID", "", "128", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adProcessId"):dataField.getField("adProcessId")));
xmlDocument.setData("reportAD_Process_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "EditReference", "800028", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("editreference"):dataField.getField("editreference")));
xmlDocument.setData("reportEditReference","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("ImportFields_BTNname", Utility.getButtonName(this, vars, "5131", "ImportFields_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalImportFields = org.openbravo.erpCommon.utility.Utility.isModalProcess("174"); 
xmlDocument.setParameter("ImportFields_Modal", modalImportFields?"true":"false");
xmlDocument.setParameter("Processing_BTNname", Utility.getButtonName(this, vars, "3205", "Processing_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcessing = org.openbravo.erpCommon.utility.Utility.isModalProcess("114"); 
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

    private void printPageButtonImportFields174(HttpServletResponse response, VariablesSecureApp vars, String strAD_Tab_ID, String strimportfields, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 174");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ImportFields174", discard).createXmlDocument();
      xmlDocument.setParameter("key", strAD_Tab_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Tab_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "174");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("174");
        vars.removeMessage("174");
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
    private void printPageButtonProcessing114(HttpServletResponse response, VariablesSecureApp vars, String strAD_Tab_ID, String strprocessing, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 114");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Processing114", discard).createXmlDocument();
      xmlDocument.setParameter("key", strAD_Tab_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Tab_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "114");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("114");
        vars.removeMessage("114");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Tab_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_Tab_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button114.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Tab_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }




    private String getDisplayLogicContext(VariablesSecureApp vars, boolean isNew) throws IOException, ServletException {
      log4j.debug("Output: Display logic context fields");
      String result = "var strWindowType=\"" +Utility.getContext(this, vars, "WindowType", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPAD_Window_ID) throws IOException, ServletException {
    TabData data = null;
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
            data = getEditVariables(con, vars, strPAD_Window_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.adTabId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (TabData.getCurrentDBTimestamp(this, data.adTabId).equals(
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
                    data.adTabId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|AD_Tab_ID", data.adTabId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Tab. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
