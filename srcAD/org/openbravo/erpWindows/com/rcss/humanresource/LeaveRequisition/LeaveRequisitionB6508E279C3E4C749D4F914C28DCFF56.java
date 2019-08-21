
package org.openbravo.erpWindows.com.rcss.humanresource.LeaveRequisition;




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

public class LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.class);
  
  private static final String windowId = "359F1AC6D7504282B2094A3424ABC68D";
  private static final String tabId = "B6508E279C3E4C749D4F914C28DCFF56";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "BAD4B9EE21444679A85DAB2F8624F1BB";
  
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
     
      if (command.contains("84143CAAADE74274B0560AFDE28E7156")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("84143CAAADE74274B0560AFDE28E7156");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      if (command.contains("921B8092DE21435192CB8B7C6F5E6961")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("921B8092DE21435192CB8B7C6F5E6961");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      if (command.contains("B198D7B384724E6BB9A30C54B85361AF")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("B198D7B384724E6BB9A30C54B85361AF");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      if (command.contains("2B6ACF25EAFA473D8A5F5F8DCACCDCBC")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("2B6ACF25EAFA473D8A5F5F8DCACCDCBC");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("84143CAAADE74274B0560AFDE28E7156")) {
        classInfo.type = "P";
        classInfo.id = "84143CAAADE74274B0560AFDE28E7156";
      }
     
      if (securedProcess && command.contains("921B8092DE21435192CB8B7C6F5E6961")) {
        classInfo.type = "P";
        classInfo.id = "921B8092DE21435192CB8B7C6F5E6961";
      }
     
      if (securedProcess && command.contains("B198D7B384724E6BB9A30C54B85361AF")) {
        classInfo.type = "P";
        classInfo.id = "B198D7B384724E6BB9A30C54B85361AF";
      }
     
      if (securedProcess && command.contains("2B6ACF25EAFA473D8A5F5F8DCACCDCBC")) {
        classInfo.type = "P";
        classInfo.id = "2B6ACF25EAFA473D8A5F5F8DCACCDCBC";
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
        String strrchrLeaverequisitionId = request.getParameter("inprchrLeaverequisitionId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrLeaverequisitionId.equals(""))
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

      String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Leaverequisition_ID.equals("")) strRchr_Leaverequisition_ID = firstElement(vars, tableSQL);
          if (strRchr_Leaverequisition_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Leaverequisition_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Leaverequisition_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Leaverequisition_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Leaverequisition_ID.equals("")) strRchr_Leaverequisition_ID = vars.getRequiredGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      else vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", strRchr_Leaverequisition_ID);
      
      vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Leaverequisition_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.view");
      String strRchr_Leaverequisition_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Leaverequisition_ID = firstElement(vars, tableSQL);
          if (strRchr_Leaverequisition_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Leaverequisition_ID.equals("")) strRchr_Leaverequisition_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Leaverequisition_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamRchr_Employee_ID", tabId + "|paramRchr_Employee_ID");
vars.getRequestGlobalVariable("inpParamFromdate", tabId + "|paramFromdate");
vars.getRequestGlobalVariable("inpParamTodate", tabId + "|paramTodate");
vars.getRequestGlobalVariable("inpParamTotalleaves", tabId + "|paramTotalleaves");
vars.getRequestGlobalVariable("inpParamRchr_Emp_Dept_ID", tabId + "|paramRchr_Emp_Dept_ID");
vars.getRequestGlobalVariable("inpParamRchr_Subdepartment_ID", tabId + "|paramRchr_Subdepartment_ID");
vars.getRequestGlobalVariable("inpParamRchr_Designation_ID", tabId + "|paramRchr_Designation_ID");
vars.getRequestGlobalVariable("inpParamFromdate_f", tabId + "|paramFromdate_f");
vars.getRequestGlobalVariable("inpParamTodate_f", tabId + "|paramTodate_f");
vars.getRequestGlobalVariable("inpParamTotalleaves_f", tabId + "|paramTotalleaves_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rchr_Leaverequisition_ID");
      String strRchr_Leaverequisition_ID="";

      String strView = vars.getSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Leaverequisition_ID = firstElement(vars, tableSQL);
        if (strRchr_Leaverequisition_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Leaverequisition_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Leaverequisition_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", "");
      vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.view", "RELATION");
      printPageDataSheet(response, vars, strRchr_Leaverequisition_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", "");
      vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Leaverequisition_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRchr_Leaverequisition_ID = vars.getRequiredStringParameter("inprchrLeaverequisitionId");
      
      String strNext = nextElement(vars, strRchr_Leaverequisition_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRchr_Leaverequisition_ID = vars.getRequiredStringParameter("inprchrLeaverequisitionId");
      
      String strPrevious = previousElement(vars, strRchr_Leaverequisition_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Leaverequisition_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Leaverequisition_ID");

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

      String strRchr_Leaverequisition_ID = vars.getRequiredGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
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
          String strNext = nextElement(vars, strRchr_Leaverequisition_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRchr_Leaverequisition_ID = vars.getRequiredStringParameter("inprchrLeaverequisitionId");
      //LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.delete(this, strRchr_Leaverequisition_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrLeaverequisitionId");
        vars.setSessionValue(tabId + "|LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONProcess84143CAAADE74274B0560AFDE28E7156")) {
        vars.setSessionValue("button84143CAAADE74274B0560AFDE28E7156.strprocess", vars.getStringParameter("inpprocess"));
        vars.setSessionValue("button84143CAAADE74274B0560AFDE28E7156.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button84143CAAADE74274B0560AFDE28E7156.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button84143CAAADE74274B0560AFDE28E7156.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button84143CAAADE74274B0560AFDE28E7156.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "84143CAAADE74274B0560AFDE28E7156", request.getServletPath());
      } else if (vars.commandIn("BUTTON84143CAAADE74274B0560AFDE28E7156")) {
        String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", "");
        String strprocess = vars.getSessionValue("button84143CAAADE74274B0560AFDE28E7156.strprocess");
        String strProcessing = vars.getSessionValue("button84143CAAADE74274B0560AFDE28E7156.strProcessing");
        String strOrg = vars.getSessionValue("button84143CAAADE74274B0560AFDE28E7156.strOrg");
        String strClient = vars.getSessionValue("button84143CAAADE74274B0560AFDE28E7156.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcess84143CAAADE74274B0560AFDE28E7156(response, vars, strRchr_Leaverequisition_ID, strprocess, strProcessing);
        }
    } else if (vars.commandIn("BUTTONPost921B8092DE21435192CB8B7C6F5E6961")) {
        vars.setSessionValue("button921B8092DE21435192CB8B7C6F5E6961.strpost", vars.getStringParameter("inppost"));
        vars.setSessionValue("button921B8092DE21435192CB8B7C6F5E6961.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button921B8092DE21435192CB8B7C6F5E6961.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button921B8092DE21435192CB8B7C6F5E6961.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button921B8092DE21435192CB8B7C6F5E6961.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "921B8092DE21435192CB8B7C6F5E6961", request.getServletPath());
      } else if (vars.commandIn("BUTTON921B8092DE21435192CB8B7C6F5E6961")) {
        String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", "");
        String strpost = vars.getSessionValue("button921B8092DE21435192CB8B7C6F5E6961.strpost");
        String strProcessing = vars.getSessionValue("button921B8092DE21435192CB8B7C6F5E6961.strProcessing");
        String strOrg = vars.getSessionValue("button921B8092DE21435192CB8B7C6F5E6961.strOrg");
        String strClient = vars.getSessionValue("button921B8092DE21435192CB8B7C6F5E6961.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonPost921B8092DE21435192CB8B7C6F5E6961(response, vars, strRchr_Leaverequisition_ID, strpost, strProcessing);
        }
    } else if (vars.commandIn("BUTTONApproveB198D7B384724E6BB9A30C54B85361AF")) {
        vars.setSessionValue("buttonB198D7B384724E6BB9A30C54B85361AF.strapprove", vars.getStringParameter("inpapprove"));
        vars.setSessionValue("buttonB198D7B384724E6BB9A30C54B85361AF.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonB198D7B384724E6BB9A30C54B85361AF.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonB198D7B384724E6BB9A30C54B85361AF.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonB198D7B384724E6BB9A30C54B85361AF.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "B198D7B384724E6BB9A30C54B85361AF", request.getServletPath());
      } else if (vars.commandIn("BUTTONB198D7B384724E6BB9A30C54B85361AF")) {
        String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", "");
        String strapprove = vars.getSessionValue("buttonB198D7B384724E6BB9A30C54B85361AF.strapprove");
        String strProcessing = vars.getSessionValue("buttonB198D7B384724E6BB9A30C54B85361AF.strProcessing");
        String strOrg = vars.getSessionValue("buttonB198D7B384724E6BB9A30C54B85361AF.strOrg");
        String strClient = vars.getSessionValue("buttonB198D7B384724E6BB9A30C54B85361AF.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonApproveB198D7B384724E6BB9A30C54B85361AF(response, vars, strRchr_Leaverequisition_ID, strapprove, strProcessing);
        }
    } else if (vars.commandIn("BUTTONCancel2B6ACF25EAFA473D8A5F5F8DCACCDCBC")) {
        vars.setSessionValue("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.strcancel", vars.getStringParameter("inpcancel"));
        vars.setSessionValue("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "2B6ACF25EAFA473D8A5F5F8DCACCDCBC", request.getServletPath());
      } else if (vars.commandIn("BUTTON2B6ACF25EAFA473D8A5F5F8DCACCDCBC")) {
        String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", "");
        String strcancel = vars.getSessionValue("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.strcancel");
        String strProcessing = vars.getSessionValue("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.strProcessing");
        String strOrg = vars.getSessionValue("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.strOrg");
        String strClient = vars.getSessionValue("button2B6ACF25EAFA473D8A5F5F8DCACCDCBC.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCancel2B6ACF25EAFA473D8A5F5F8DCACCDCBC(response, vars, strRchr_Leaverequisition_ID, strcancel, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcess84143CAAADE74274B0560AFDE28E7156")) {
        String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Leaverequisition_ID", "");
        
        ProcessBundle pb = new ProcessBundle("84143CAAADE74274B0560AFDE28E7156", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Leaverequisition_ID", strRchr_Leaverequisition_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONPost921B8092DE21435192CB8B7C6F5E6961")) {
        String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Leaverequisition_ID", "");
        
        ProcessBundle pb = new ProcessBundle("921B8092DE21435192CB8B7C6F5E6961", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Leaverequisition_ID", strRchr_Leaverequisition_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONApproveB198D7B384724E6BB9A30C54B85361AF")) {
        String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Leaverequisition_ID", "");
        
        ProcessBundle pb = new ProcessBundle("B198D7B384724E6BB9A30C54B85361AF", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Leaverequisition_ID", strRchr_Leaverequisition_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONCancel2B6ACF25EAFA473D8A5F5F8DCACCDCBC")) {
        String strRchr_Leaverequisition_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Leaverequisition_ID", "");
        
        ProcessBundle pb = new ProcessBundle("2B6ACF25EAFA473D8A5F5F8DCACCDCBC", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Leaverequisition_ID", strRchr_Leaverequisition_ID);
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
  private LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data data = new LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rchrEmployeeId = vars.getStringParameter("inprchrEmployeeId");     data.rchrEmployeeIdr = vars.getStringParameter("inprchrEmployeeId_R");     data.requisitiondate = vars.getRequiredStringParameter("inprequisitiondate");     data.fromdate = vars.getRequiredStringParameter("inpfromdate");     data.todate = vars.getRequiredStringParameter("inptodate");     data.employeetype = vars.getStringParameter("inpemployeetype");     data.rchrLeavetypeId = vars.getStringParameter("inprchrLeavetypeId");     data.rchrEmpDeptId = vars.getStringParameter("inprchrEmpDeptId");     data.rchrEmpDeptIdr = vars.getStringParameter("inprchrEmpDeptId_R");    try {   data.leaveopening = vars.getNumericParameter("inpleaveopening");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrSubdepartmentId = vars.getStringParameter("inprchrSubdepartmentId");     data.rchrSubdepartmentIdr = vars.getStringParameter("inprchrSubdepartmentId_R");     data.rchrDesignationId = vars.getStringParameter("inprchrDesignationId");     data.rchrDesignationIdr = vars.getStringParameter("inprchrDesignationId_R");     data.reason = vars.getRequiredStringParameter("inpreason");     data.docstatus = vars.getRequiredGlobalVariable("inpdocstatus", windowId + "|Docstatus");     data.docstatusr = vars.getStringParameter("inpdocstatus_R");    try {   data.totalleaves = vars.getRequiredNumericParameter("inptotalleaves");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.noofleaves = vars.getRequiredNumericParameter("inpnoofleaves");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weekoffleaves = vars.getNumericParameter("inpweekoffleaves");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.el = vars.getRequiredNumericParameter("inpel");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.cl = vars.getRequiredNumericParameter("inpcl");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.lopleaves = vars.getNumericParameter("inplopleaves");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.coffs = vars.getRequiredNumericParameter("inpcoffs");  } catch (ServletException paramEx) { ex = paramEx; }     data.process = vars.getRequiredStringParameter("inpprocess");     data.post = vars.getRequiredStringParameter("inppost");     data.verified = vars.getStringParameter("inpverified");     data.approve = vars.getStringParameter("inpapprove");     data.approvalstatus = vars.getStringParameter("inpapprovalstatus");    try {   data.balancesl = vars.getRequiredNumericParameter("inpbalancesl");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.balancecl = vars.getRequiredNumericParameter("inpbalancecl");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.balancecoffs = vars.getRequiredNumericParameter("inpbalancecoffs");  } catch (ServletException paramEx) { ex = paramEx; }     data.remarks = vars.getStringParameter("inpremarks");     data.cancel = vars.getRequiredStringParameter("inpcancel");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rchrLeaverequisitionId = vars.getRequestGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



    
    

    
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|DocStatus", data[0].getField("docstatus"));    vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", data[0].getField("rchrLeaverequisitionId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data[] data = LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprchrLeaverequisitionId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Leaverequisition_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamRchr_Employee_ID = vars.getSessionValue(tabId + "|paramRchr_Employee_ID");
String strParamFromdate = vars.getSessionValue(tabId + "|paramFromdate");
String strParamTodate = vars.getSessionValue(tabId + "|paramTodate");
String strParamTotalleaves = vars.getSessionValue(tabId + "|paramTotalleaves");
String strParamRchr_Emp_Dept_ID = vars.getSessionValue(tabId + "|paramRchr_Emp_Dept_ID");
String strParamRchr_Subdepartment_ID = vars.getSessionValue(tabId + "|paramRchr_Subdepartment_ID");
String strParamRchr_Designation_ID = vars.getSessionValue(tabId + "|paramRchr_Designation_ID");
String strParamFromdate_f = vars.getSessionValue(tabId + "|paramFromdate_f");
String strParamTodate_f = vars.getSessionValue(tabId + "|paramTodate_f");
String strParamTotalleaves_f = vars.getSessionValue(tabId + "|paramTotalleaves_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Employee_ID) && ("").equals(strParamFromdate) && ("").equals(strParamTodate) && ("").equals(strParamTotalleaves) && ("").equals(strParamRchr_Emp_Dept_ID) && ("").equals(strParamRchr_Subdepartment_ID) && ("").equals(strParamRchr_Designation_ID) && ("").equals(strParamFromdate_f) && ("").equals(strParamTodate_f) && ("").equals(strParamTotalleaves_f)) || !(("").equals(strParamRchr_Employee_ID) || ("%").equals(strParamRchr_Employee_ID))  || !(("").equals(strParamFromdate) || ("%").equals(strParamFromdate))  || !(("").equals(strParamTodate) || ("%").equals(strParamTodate))  || !(("").equals(strParamTotalleaves) || ("%").equals(strParamTotalleaves))  || !(("").equals(strParamRchr_Emp_Dept_ID) || ("%").equals(strParamRchr_Emp_Dept_ID))  || !(("").equals(strParamRchr_Subdepartment_ID) || ("%").equals(strParamRchr_Subdepartment_ID))  || !(("").equals(strParamRchr_Designation_ID) || ("%").equals(strParamRchr_Designation_ID))  || !(("").equals(strParamFromdate_f) || ("%").equals(strParamFromdate_f))  || !(("").equals(strParamTodate_f) || ("%").equals(strParamTodate_f))  || !(("").equals(strParamTotalleaves_f) || ("%").equals(strParamTotalleaves_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Leaverequisition_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Leaverequisition_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LeaveRequisition/LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56", false, "document.frmMain.inprchrLeaverequisitionId", "grid", "../com.rcss.humanresource/erpCommon/ad_reports/Leavereport.pdf", "N".equals("Y"), "LeaveRequisition", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
    xmlDocument.setParameter("KeyName", "rchrLeaverequisitionId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Relation.html", "LeaveRequisition", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Leaverequisition_ID, TableSQLData tableSQL)
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
    LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamRchr_Employee_ID = vars.getSessionValue(tabId + "|paramRchr_Employee_ID");
String strParamFromdate = vars.getSessionValue(tabId + "|paramFromdate");
String strParamTodate = vars.getSessionValue(tabId + "|paramTodate");
String strParamTotalleaves = vars.getSessionValue(tabId + "|paramTotalleaves");
String strParamRchr_Emp_Dept_ID = vars.getSessionValue(tabId + "|paramRchr_Emp_Dept_ID");
String strParamRchr_Subdepartment_ID = vars.getSessionValue(tabId + "|paramRchr_Subdepartment_ID");
String strParamRchr_Designation_ID = vars.getSessionValue(tabId + "|paramRchr_Designation_ID");
String strParamFromdate_f = vars.getSessionValue(tabId + "|paramFromdate_f");
String strParamTodate_f = vars.getSessionValue(tabId + "|paramTodate_f");
String strParamTotalleaves_f = vars.getSessionValue(tabId + "|paramTotalleaves_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Employee_ID) && ("").equals(strParamFromdate) && ("").equals(strParamTodate) && ("").equals(strParamTotalleaves) && ("").equals(strParamRchr_Emp_Dept_ID) && ("").equals(strParamRchr_Subdepartment_ID) && ("").equals(strParamRchr_Designation_ID) && ("").equals(strParamFromdate_f) && ("").equals(strParamTodate_f) && ("").equals(strParamTotalleaves_f)) || !(("").equals(strParamRchr_Employee_ID) || ("%").equals(strParamRchr_Employee_ID))  || !(("").equals(strParamFromdate) || ("%").equals(strParamFromdate))  || !(("").equals(strParamTodate) || ("%").equals(strParamTodate))  || !(("").equals(strParamTotalleaves) || ("%").equals(strParamTotalleaves))  || !(("").equals(strParamRchr_Emp_Dept_ID) || ("%").equals(strParamRchr_Emp_Dept_ID))  || !(("").equals(strParamRchr_Subdepartment_ID) || ("%").equals(strParamRchr_Subdepartment_ID))  || !(("").equals(strParamRchr_Designation_ID) || ("%").equals(strParamRchr_Designation_ID))  || !(("").equals(strParamFromdate_f) || ("%").equals(strParamFromdate_f))  || !(("").equals(strParamTodate_f) || ("%").equals(strParamTodate_f))  || !(("").equals(strParamTotalleaves_f) || ("%").equals(strParamTotalleaves_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Leaverequisition_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Leaverequisition_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrLeaverequisitionId") == null || dataField.getField("rchrLeaverequisitionId").equals("")) {
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
        refreshSessionNew(vars);
        data = LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.set(Utility.getDefault(this, vars, "Rchr_Designation_ID", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Balancecoffs", "0", "359F1AC6D7504282B2094A3424ABC68D", "0", dataField), Utility.getDefault(this, vars, "Cl", "0", "359F1AC6D7504282B2094A3424ABC68D", "0", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "El", "0", "359F1AC6D7504282B2094A3424ABC68D", "0", dataField), Utility.getDefault(this, vars, "Coffs", "0", "359F1AC6D7504282B2094A3424ABC68D", "0", dataField), Utility.getDefault(this, vars, "Weekoffleaves", "0", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Employeetype", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.selectDef256828AFF94B4E269C8ABC7E6BD91187_0(this, Utility.getDefault(this, vars, "Updatedby", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField)), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Fromdate", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), "Y", Utility.getDefault(this, vars, "Remarks", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), "", Utility.getDefault(this, vars, "Rchr_Leavetype_ID", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Rchr_Subdepartment_ID", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Docstatus", "DR", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Reason", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Verified", "N", "359F1AC6D7504282B2094A3424ABC68D", "N", dataField), Utility.getDefault(this, vars, "Process", "N", "359F1AC6D7504282B2094A3424ABC68D", "N", dataField), Utility.getDefault(this, vars, "Post", "N", "359F1AC6D7504282B2094A3424ABC68D", "N", dataField), Utility.getDefault(this, vars, "Requisitiondate", "@#Date@", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Balancecl", "0", "359F1AC6D7504282B2094A3424ABC68D", "0", dataField), Utility.getDefault(this, vars, "Approvalstatus", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Balancesl", "0", "359F1AC6D7504282B2094A3424ABC68D", "0", dataField), Utility.getDefault(this, vars, "Totalleaves", "0", "359F1AC6D7504282B2094A3424ABC68D", "0", dataField), Utility.getDefault(this, vars, "Leaveopening", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Rchr_Emp_Dept_ID", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Approve", "N", "359F1AC6D7504282B2094A3424ABC68D", "N", dataField), Utility.getDefault(this, vars, "Lopleaves", "0", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.selectDefD709A66F53E74595932046A6970A6689_1(this, Utility.getDefault(this, vars, "Createdby", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField)), Utility.getDefault(this, vars, "Noofleaves", "", "359F1AC6D7504282B2094A3424ABC68D", "0", dataField), Utility.getDefault(this, vars, "Cancel", "N", "359F1AC6D7504282B2094A3424ABC68D", "N", dataField), Utility.getDefault(this, vars, "Todate", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LeaveRequisition/LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LeaveRequisition/LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrLeaverequisitionId", "", "../com.rcss.humanresource/erpCommon/ad_reports/Leavereport.pdf", "N".equals("Y"), "LeaveRequisition", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Leaverequisition_ID), !hasReadOnlyAccess);
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
      // if (!strRchr_Leaverequisition_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Relation.html", "LeaveRequisition", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
    
    
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
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Employee_ID", "", "EC4ECED2EB1C4C47982CB2B6949B4377", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmployeeId"):dataField.getField("rchrEmployeeId")));
xmlDocument.setData("reportRchr_Employee_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Requisitiondate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Fromdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Todate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Emp_Dept_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmpDeptId"):dataField.getField("rchrEmpDeptId")));
xmlDocument.setData("reportRchr_Emp_Dept_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Subdepartment_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrSubdepartmentId"):dataField.getField("rchrSubdepartmentId")));
xmlDocument.setData("reportRchr_Subdepartment_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Designation_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrDesignationId"):dataField.getField("rchrDesignationId")));
xmlDocument.setData("reportRchr_Designation_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Docstatus", "8A2CA8866C9C45E9862D396193CC55D6", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("docstatus"):dataField.getField("docstatus")));
xmlDocument.setData("reportDocstatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Process_BTNname", Utility.getButtonName(this, vars, "9B88783552A7406583851B63FAE84411", "Process_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcess = org.openbravo.erpCommon.utility.Utility.isModalProcess("84143CAAADE74274B0560AFDE28E7156"); 
xmlDocument.setParameter("Process_Modal", modalProcess?"true":"false");
xmlDocument.setParameter("Post_BTNname", Utility.getButtonName(this, vars, "F2AA031E5C9D410FAFDD3F23E2BD0FE5", "Post_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPost = org.openbravo.erpCommon.utility.Utility.isModalProcess("921B8092DE21435192CB8B7C6F5E6961"); 
xmlDocument.setParameter("Post_Modal", modalPost?"true":"false");
xmlDocument.setParameter("Approve_BTNname", Utility.getButtonName(this, vars, "BF5A78A1A849428AB1550790D4EB8360", "Approve_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalApprove = org.openbravo.erpCommon.utility.Utility.isModalProcess("B198D7B384724E6BB9A30C54B85361AF"); 
xmlDocument.setParameter("Approve_Modal", modalApprove?"true":"false");
xmlDocument.setParameter("Cancel_BTNname", Utility.getButtonName(this, vars, "0CBDFBD64DD44114900AD2F240351539", "Cancel_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCancel = org.openbravo.erpCommon.utility.Utility.isModalProcess("2B6ACF25EAFA473D8A5F5F8DCACCDCBC"); 
xmlDocument.setParameter("Cancel_Modal", modalCancel?"true":"false");
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



    void printPageButtonProcess84143CAAADE74274B0560AFDE28E7156(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Leaverequisition_ID, String strprocess, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 84143CAAADE74274B0560AFDE28E7156");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Process84143CAAADE74274B0560AFDE28E7156", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Leaverequisition_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "84143CAAADE74274B0560AFDE28E7156");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("84143CAAADE74274B0560AFDE28E7156");
        vars.removeMessage("84143CAAADE74274B0560AFDE28E7156");
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
    void printPageButtonPost921B8092DE21435192CB8B7C6F5E6961(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Leaverequisition_ID, String strpost, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 921B8092DE21435192CB8B7C6F5E6961");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Post921B8092DE21435192CB8B7C6F5E6961", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Leaverequisition_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "921B8092DE21435192CB8B7C6F5E6961");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("921B8092DE21435192CB8B7C6F5E6961");
        vars.removeMessage("921B8092DE21435192CB8B7C6F5E6961");
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
    void printPageButtonApproveB198D7B384724E6BB9A30C54B85361AF(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Leaverequisition_ID, String strapprove, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B198D7B384724E6BB9A30C54B85361AF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ApproveB198D7B384724E6BB9A30C54B85361AF", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Leaverequisition_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "B198D7B384724E6BB9A30C54B85361AF");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("B198D7B384724E6BB9A30C54B85361AF");
        vars.removeMessage("B198D7B384724E6BB9A30C54B85361AF");
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
    void printPageButtonCancel2B6ACF25EAFA473D8A5F5F8DCACCDCBC(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Leaverequisition_ID, String strcancel, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2B6ACF25EAFA473D8A5F5F8DCACCDCBC");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Cancel2B6ACF25EAFA473D8A5F5F8DCACCDCBC", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Leaverequisition_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "2B6ACF25EAFA473D8A5F5F8DCACCDCBC");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("2B6ACF25EAFA473D8A5F5F8DCACCDCBC");
        vars.removeMessage("2B6ACF25EAFA473D8A5F5F8DCACCDCBC");
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type) throws IOException, ServletException {
    LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data data = null;
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
                data.rchrLeaverequisitionId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.getCurrentDBTimestamp(this, data.rchrLeaverequisitionId).equals(
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
                    data.rchrLeaverequisitionId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", data.rchrLeaverequisitionId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
