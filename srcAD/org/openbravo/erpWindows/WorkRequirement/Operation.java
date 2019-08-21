
package org.openbravo.erpWindows.WorkRequirement;


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

public class Operation extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Operation.class);
  
  private static final String windowId = "800052";
  private static final String tabId = "800112";
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
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("800118")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("800118");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "800118";
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
        String strmaWrphaseId = request.getParameter("inpmaWrphaseId");
         String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strmaWrphaseId.equals(""))
              total = saveRecord(vars, myError, 'U', strPMA_Workrequirement_ID);
          else
              total = saveRecord(vars, myError, 'I', strPMA_Workrequirement_ID);
          
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
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID", "");

      String strMA_Wrphase_ID = vars.getGlobalVariable("inpmaWrphaseId", windowId + "|MA_Wrphase_ID", "");
            if (strPMA_Workrequirement_ID.equals("")) {
        strPMA_Workrequirement_ID = getParentID(vars, strMA_Wrphase_ID);
        if (strPMA_Workrequirement_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|MA_Workrequirement_ID");
        vars.setSessionValue(windowId + "|MA_Workrequirement_ID", strPMA_Workrequirement_ID);

        refreshParentSession(vars, strPMA_Workrequirement_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Operation.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strMA_Wrphase_ID.equals("")) strMA_Wrphase_ID = firstElement(vars, tableSQL);
          if (strMA_Wrphase_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strMA_Wrphase_ID, strPMA_Workrequirement_ID, tableSQL);

      else printPageDataSheet(response, vars, strPMA_Workrequirement_ID, strMA_Wrphase_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strMA_Wrphase_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strMA_Wrphase_ID.equals("")) strMA_Wrphase_ID = vars.getRequiredGlobalVariable("inpmaWrphaseId", windowId + "|MA_Wrphase_ID");
      else vars.setSessionValue(windowId + "|MA_Wrphase_ID", strMA_Wrphase_ID);
      
      
      String strPMA_Workrequirement_ID = getParentID(vars, strMA_Wrphase_ID);
      
      vars.setSessionValue(windowId + "|MA_Workrequirement_ID", strPMA_Workrequirement_ID);
      vars.setSessionValue("800111|Header.view", "EDIT");

      refreshParentSession(vars, strPMA_Workrequirement_ID);

      vars.setSessionValue(tabId + "|Operation.view", "EDIT");

      printPageEdit(response, request, vars, false, strMA_Wrphase_ID, strPMA_Workrequirement_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|MA_Wrphase_ID");
      refreshParentSession(vars, strPMA_Workrequirement_ID);


      String strView = vars.getSessionValue(tabId + "|Operation.view");
      String strMA_Wrphase_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strMA_Wrphase_ID = firstElement(vars, tableSQL);
          if (strMA_Wrphase_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strMA_Wrphase_ID.equals("")) strMA_Wrphase_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strMA_Wrphase_ID, strPMA_Workrequirement_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPMA_Workrequirement_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamMA_Workrequirement_ID", tabId + "|paramMA_Workrequirement_ID");
vars.getRequestGlobalVariable("inpParamSeqNo", tabId + "|paramSeqNo");
vars.getRequestGlobalVariable("inpParamMA_Process_ID", tabId + "|paramMA_Process_ID");
vars.getRequestGlobalVariable("inpParamSeqNo_f", tabId + "|paramSeqNo_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");

      
      vars.removeSessionValue(windowId + "|MA_Wrphase_ID");
      String strMA_Wrphase_ID="";

      String strView = vars.getSessionValue(tabId + "|Operation.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strMA_Wrphase_ID = firstElement(vars, tableSQL);
        if (strMA_Wrphase_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Operation.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strMA_Wrphase_ID, strPMA_Workrequirement_ID, tableSQL);

      else printPageDataSheet(response, vars, strPMA_Workrequirement_ID, strMA_Wrphase_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
      

      String strMA_Wrphase_ID = vars.getGlobalVariable("inpmaWrphaseId", windowId + "|MA_Wrphase_ID", "");
      vars.setSessionValue(tabId + "|Operation.view", "RELATION");
      printPageDataSheet(response, vars, strPMA_Workrequirement_ID, strMA_Wrphase_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");


      printPageEdit(response, request, vars, true, "", strPMA_Workrequirement_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strMA_Wrphase_ID = vars.getGlobalVariable("inpmaWrphaseId", windowId + "|MA_Wrphase_ID", "");
      vars.setSessionValue(tabId + "|Operation.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strMA_Wrphase_ID, strPMA_Workrequirement_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
      String strMA_Wrphase_ID = vars.getRequiredStringParameter("inpmaWrphaseId");
      
      String strNext = nextElement(vars, strMA_Wrphase_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPMA_Workrequirement_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
      String strMA_Wrphase_ID = vars.getRequiredStringParameter("inpmaWrphaseId");
      
      String strPrevious = previousElement(vars, strMA_Wrphase_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPMA_Workrequirement_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");

      vars.setSessionValue(tabId + "|Operation.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Operation.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Operation.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Operation.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|MA_Wrphase_ID");
      vars.setSessionValue(windowId + "|MA_Workrequirement_ID", strPMA_Workrequirement_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Operation.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Operation.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|MA_Wrphase_ID");
      vars.setSessionValue(windowId + "|MA_Workrequirement_ID", strPMA_Workrequirement_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPMA_Workrequirement_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPMA_Workrequirement_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPMA_Workrequirement_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPMA_Workrequirement_ID);      
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
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
      String strMA_Wrphase_ID = vars.getRequiredGlobalVariable("inpmaWrphaseId", windowId + "|MA_Wrphase_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPMA_Workrequirement_ID);      
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
          String strNext = nextElement(vars, strMA_Wrphase_ID, tableSQL);
          vars.setSessionValue(windowId + "|MA_Wrphase_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");

      String strMA_Wrphase_ID = vars.getRequiredStringParameter("inpmaWrphaseId");
      //OperationData data = getEditVariables(vars, strPMA_Workrequirement_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = OperationData.delete(this, strMA_Wrphase_ID, strPMA_Workrequirement_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|maWrphaseId");
        vars.setSessionValue(tabId + "|Operation.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONClosed800118")) {
        vars.setSessionValue("button800118.strclosed", vars.getStringParameter("inpclosed"));
        vars.setSessionValue("button800118.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button800118.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button800118.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button800118.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "800118", request.getServletPath());    
     } else if (vars.commandIn("BUTTON800118")) {
        String strMA_Wrphase_ID = vars.getGlobalVariable("inpmaWrphaseId", windowId + "|MA_Wrphase_ID", "");
        String strclosed = vars.getSessionValue("button800118.strclosed");
        String strProcessing = vars.getSessionValue("button800118.strProcessing");
        String strOrg = vars.getSessionValue("button800118.strOrg");
        String strClient = vars.getSessionValue("button800118.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonClosed800118(response, vars, strMA_Wrphase_ID, strclosed, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONClosed800118")) {
        String strMA_Wrphase_ID = vars.getGlobalVariable("inpKey", windowId + "|MA_Wrphase_ID", "");
        @SuppressWarnings("unused")
        String strclosed = vars.getStringParameter("inpclosed");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "800118", (("MA_Wrphase_ID".equalsIgnoreCase("AD_Language"))?"0":strMA_Wrphase_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
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
      String strPMA_Workrequirement_ID = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPMA_Workrequirement_ID);
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
  private OperationData getEditVariables(Connection con, VariablesSecureApp vars, String strPMA_Workrequirement_ID) throws IOException,ServletException {
    OperationData data = new OperationData();
    ServletException ex = null;
    try {
   try {   data.seqno = vars.getRequiredNumericParameter("inpseqno");  } catch (ServletException paramEx) { ex = paramEx; }     data.maSequenceId = vars.getStringParameter("inpmaSequenceId");     data.maSequenceIdr = vars.getStringParameter("inpmaSequenceId_R");     data.maProcessId = vars.getRequiredStringParameter("inpmaProcessId");     data.maProcessIdr = vars.getStringParameter("inpmaProcessId_R");     data.startdate = vars.getStringParameter("inpstartdate");     data.enddate = vars.getStringParameter("inpenddate");    try {   data.estimatedtime = vars.getRequiredNumericParameter("inpestimatedtime");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.runtime = vars.getRequiredNumericParameter("inpruntime");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.quantity = vars.getRequiredNumericParameter("inpquantity");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.donequantity = vars.getRequiredNumericParameter("inpdonequantity");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.costcenteruse = vars.getRequiredNumericParameter("inpcostcenteruse");  } catch (ServletException paramEx) { ex = paramEx; }     data.description = vars.getStringParameter("inpdescription");     data.noqty = vars.getStringParameter("inpnoqty", "N");     data.groupuse = vars.getStringParameter("inpgroupuse", "N");     data.closed = vars.getRequiredStringParameter("inpclosed");     data.outsourced = vars.getStringParameter("inpoutsourced", "N");    try {   data.preptime = vars.getNumericParameter("inppreptime");  } catch (ServletException paramEx) { ex = paramEx; }     data.usedmaterial = vars.getStringParameter("inpusedmaterial", "N");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.maWrphaseId = vars.getRequestGlobalVariable("inpmaWrphaseId", windowId + "|MA_Wrphase_ID");     data.maWorkrequirementId = vars.getRequiredStringParameter("inpmaWorkrequirementId");     data.name = vars.getStringParameter("inpname"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.maWorkrequirementId = vars.getGlobalVariable("inpmaWorkrequirementId", windowId + "|MA_Workrequirement_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPMA_Workrequirement_ID) throws IOException,ServletException {
      
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPMA_Workrequirement_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|MA_Processplan_ID", data[0].maProcessplanId);    vars.setSessionValue(windowId + "|Launchdate", data[0].launchdate);    vars.setSessionValue(windowId + "|StartDate", data[0].startdate);    vars.setSessionValue(windowId + "|EndDate", data[0].enddate);    vars.setSessionValue(windowId + "|MA_Workrequirement_ID", data[0].maWorkrequirementId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|MA_Workrequirement_ID", strPMA_Workrequirement_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strMA_Wrphase_ID) throws ServletException {
    String strPMA_Workrequirement_ID = OperationData.selectParentID(this, strMA_Wrphase_ID);
    if (strPMA_Workrequirement_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strMA_Wrphase_ID);
      throw new ServletException("Parent record not found");
    }
    return strPMA_Workrequirement_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|MA_Wrphase_ID", data[0].getField("maWrphaseId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPMA_Workrequirement_ID) throws IOException,ServletException {
      OperationData[] data = OperationData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPMA_Workrequirement_ID, vars.getStringParameter("inpmaWrphaseId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPMA_Workrequirement_ID, String strMA_Wrphase_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamMA_Workrequirement_ID = vars.getSessionValue(tabId + "|paramMA_Workrequirement_ID");
String strParamSeqNo = vars.getSessionValue(tabId + "|paramSeqNo");
String strParamMA_Process_ID = vars.getSessionValue(tabId + "|paramMA_Process_ID");
String strParamSeqNo_f = vars.getSessionValue(tabId + "|paramSeqNo_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamMA_Workrequirement_ID) && ("").equals(strParamSeqNo) && ("").equals(strParamMA_Process_ID) && ("").equals(strParamSeqNo_f)) || !(("").equals(strParamMA_Workrequirement_ID) || ("%").equals(strParamMA_Workrequirement_ID))  || !(("").equals(strParamSeqNo) || ("%").equals(strParamSeqNo))  || !(("").equals(strParamMA_Process_ID) || ("%").equals(strParamMA_Process_ID))  || !(("").equals(strParamSeqNo_f) || ("%").equals(strParamSeqNo_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strMA_Wrphase_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strMA_Wrphase_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/WorkRequirement/Operation_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Operation", false, "document.frmMain.inpmaWrphaseId", "grid", "..", "".equals("Y"), "WorkRequirement", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPMA_Workrequirement_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("801783", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "maWrphaseId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Operation_Relation.html", "WorkRequirement", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Operation_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", OperationData.selectParent(this, strPMA_Workrequirement_ID));
    else xmlDocument.setParameter("parent", OperationData.selectParentTrl(this, strPMA_Workrequirement_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strMA_Wrphase_ID, String strPMA_Workrequirement_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " MA_WRPhase.SeqNo";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    OperationData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamMA_Workrequirement_ID = vars.getSessionValue(tabId + "|paramMA_Workrequirement_ID");
String strParamSeqNo = vars.getSessionValue(tabId + "|paramSeqNo");
String strParamMA_Process_ID = vars.getSessionValue(tabId + "|paramMA_Process_ID");
String strParamSeqNo_f = vars.getSessionValue(tabId + "|paramSeqNo_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamMA_Workrequirement_ID) && ("").equals(strParamSeqNo) && ("").equals(strParamMA_Process_ID) && ("").equals(strParamSeqNo_f)) || !(("").equals(strParamMA_Workrequirement_ID) || ("%").equals(strParamMA_Workrequirement_ID))  || !(("").equals(strParamSeqNo) || ("%").equals(strParamSeqNo))  || !(("").equals(strParamMA_Process_ID) || ("%").equals(strParamMA_Process_ID))  || !(("").equals(strParamSeqNo_f) || ("%").equals(strParamSeqNo_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = OperationData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPMA_Workrequirement_ID, strMA_Wrphase_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strMA_Wrphase_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new OperationData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("maWrphaseId") == null || dataField.getField("maWrphaseId").equals("")) {
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
        refreshSessionNew(vars, strPMA_Workrequirement_ID);
        data = OperationData.set(strPMA_Workrequirement_ID, Utility.getDefault(this, vars, "Estimatedtime", "0", "800052", "0", dataField), Utility.getDefault(this, vars, "Usedmaterial", "N", "800052", "N", dataField), Utility.getDefault(this, vars, "Startdate", "@Startdate@", "800052", "", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "800052", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "800052", "", dataField), "Y", Utility.getDefault(this, vars, "Createdby", "", "800052", "", dataField), OperationData.selectDef801569_0(this, Utility.getDefault(this, vars, "Createdby", "", "800052", "", dataField)), Utility.getDefault(this, vars, "Updatedby", "", "800052", "", dataField), OperationData.selectDef801571_1(this, Utility.getDefault(this, vars, "Updatedby", "", "800052", "", dataField)), Utility.getDefault(this, vars, "MA_Process_ID", "", "800052", "", dataField), Utility.getDefault(this, vars, "Quantity", "", "800052", "0", dataField), Utility.getDefault(this, vars, "Donequantity", "", "800052", "0", dataField), Utility.getDefault(this, vars, "CostCenterUse", "", "800052", "0", dataField), Utility.getDefault(this, vars, "Preptime", "", "800052", "", dataField), Utility.getDefault(this, vars, "Closed", "N", "800052", "N", dataField), OperationData.selectDef801759(this, strPMA_Workrequirement_ID), Utility.getDefault(this, vars, "Noqty", "N", "800052", "N", dataField), Utility.getDefault(this, vars, "Groupuse", "N", "800052", "N", dataField), Utility.getDefault(this, vars, "MA_Sequence_ID", "", "800052", "", dataField), Utility.getDefault(this, vars, "Name", "", "800052", "", dataField), Utility.getDefault(this, vars, "Description", "", "800052", "", dataField), Utility.getDefault(this, vars, "Outsourced", "", "800052", "N", dataField), Utility.getDefault(this, vars, "Enddate", "@Enddate@", "800052", "", dataField), Utility.getDefault(this, vars, "Runtime", "0", "800052", "0", dataField));
        
      }
     }
      
    String currentPOrg=HeaderData.selectOrg(this, strPMA_Workrequirement_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/WorkRequirement/Operation_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/WorkRequirement/Operation_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Operation", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpmaWrphaseId", "", "..", "".equals("Y"), "WorkRequirement", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strMA_Wrphase_ID), !hasReadOnlyAccess);
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
      // if (!strMA_Wrphase_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Operation_Relation.html", "WorkRequirement", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Operation_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "MA_Sequence_ID", "", "800008", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("maSequenceId"):dataField.getField("maSequenceId")));
xmlDocument.setData("reportMA_Sequence_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "MA_Process_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("maProcessId"):dataField.getField("maProcessId")));
xmlDocument.setData("reportMA_Process_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Startdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Enddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonEstimatedtime", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonRuntime", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQuantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDonequantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCostCenterUse", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Closed_BTNname", Utility.getButtonName(this, vars, "801779", "Closed_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalClosed = org.openbravo.erpCommon.utility.Utility.isModalProcess("800118"); 
xmlDocument.setParameter("Closed_Modal", modalClosed?"true":"false");
xmlDocument.setParameter("buttonPreptime", Utility.messageBD(this, "Calc", vars.getLanguage()));
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

    private void printPageButtonClosed800118(HttpServletResponse response, VariablesSecureApp vars, String strMA_Wrphase_ID, String strclosed, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800118");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Closed800118", discard).createXmlDocument();
      xmlDocument.setParameter("key", strMA_Wrphase_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Operation_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "800118");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("800118");
        vars.removeMessage("800118");
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
      String result = "var strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPMA_Workrequirement_ID) throws IOException, ServletException {
    OperationData data = null;
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
            data = getEditVariables(con, vars, strPMA_Workrequirement_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.maWrphaseId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (OperationData.getCurrentDBTimestamp(this, data.maWrphaseId).equals(
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
                    data.maWrphaseId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|MA_Wrphase_ID", data.maWrphaseId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Operation. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
