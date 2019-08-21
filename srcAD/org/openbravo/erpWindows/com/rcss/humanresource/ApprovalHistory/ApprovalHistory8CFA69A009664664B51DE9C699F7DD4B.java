
package org.openbravo.erpWindows.com.rcss.humanresource.ApprovalHistory;




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

public class ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.class);
  
  private static final String windowId = "D2F940DA10264717A1EB8A63367B1073";
  private static final String tabId = "8CFA69A009664664B51DE9C699F7DD4B";
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
     
      if (command.contains("9E8E9A0881C640A88F0846A961FA6D47")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("9E8E9A0881C640A88F0846A961FA6D47");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      if (command.contains("F78498E7CB2D442396D26954D97A47D7")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("F78498E7CB2D442396D26954D97A47D7");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("9E8E9A0881C640A88F0846A961FA6D47")) {
        classInfo.type = "P";
        classInfo.id = "9E8E9A0881C640A88F0846A961FA6D47";
      }
     
      if (securedProcess && command.contains("F78498E7CB2D442396D26954D97A47D7")) {
        classInfo.type = "P";
        classInfo.id = "F78498E7CB2D442396D26954D97A47D7";
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
        String strrchrApprovalhistroryId = request.getParameter("inprchrApprovalhistroryId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrApprovalhistroryId.equals(""))
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

      String strRchr_Approvalhistrory_ID = vars.getGlobalVariable("inprchrApprovalhistroryId", windowId + "|Rchr_Approvalhistrory_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Approvalhistrory_ID.equals("")) strRchr_Approvalhistrory_ID = firstElement(vars, tableSQL);
          if (strRchr_Approvalhistrory_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Approvalhistrory_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Approvalhistrory_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Approvalhistrory_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Approvalhistrory_ID.equals("")) strRchr_Approvalhistrory_ID = vars.getRequiredGlobalVariable("inprchrApprovalhistroryId", windowId + "|Rchr_Approvalhistrory_ID");
      else vars.setSessionValue(windowId + "|Rchr_Approvalhistrory_ID", strRchr_Approvalhistrory_ID);
      
      vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Approvalhistrory_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.view");
      String strRchr_Approvalhistrory_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Approvalhistrory_ID = firstElement(vars, tableSQL);
          if (strRchr_Approvalhistrory_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Approvalhistrory_ID.equals("")) strRchr_Approvalhistrory_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Approvalhistrory_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamAD_Tab_ID", tabId + "|paramAD_Tab_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rchr_Approvalhistrory_ID");
      String strRchr_Approvalhistrory_ID="";

      String strView = vars.getSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Approvalhistrory_ID = firstElement(vars, tableSQL);
        if (strRchr_Approvalhistrory_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Approvalhistrory_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Approvalhistrory_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRchr_Approvalhistrory_ID = vars.getGlobalVariable("inprchrApprovalhistroryId", windowId + "|Rchr_Approvalhistrory_ID", "");
      vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.view", "RELATION");
      printPageDataSheet(response, vars, strRchr_Approvalhistrory_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Approvalhistrory_ID = vars.getGlobalVariable("inprchrApprovalhistroryId", windowId + "|Rchr_Approvalhistrory_ID", "");
      vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Approvalhistrory_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRchr_Approvalhistrory_ID = vars.getRequiredStringParameter("inprchrApprovalhistroryId");
      
      String strNext = nextElement(vars, strRchr_Approvalhistrory_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRchr_Approvalhistrory_ID = vars.getRequiredStringParameter("inprchrApprovalhistroryId");
      
      String strPrevious = previousElement(vars, strRchr_Approvalhistrory_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Approvalhistrory_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Approvalhistrory_ID");

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

      String strRchr_Approvalhistrory_ID = vars.getRequiredGlobalVariable("inprchrApprovalhistroryId", windowId + "|Rchr_Approvalhistrory_ID");
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
          String strNext = nextElement(vars, strRchr_Approvalhistrory_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Approvalhistrory_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRchr_Approvalhistrory_ID = vars.getRequiredStringParameter("inprchrApprovalhistroryId");
      //ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData.delete(this, strRchr_Approvalhistrory_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrApprovalhistroryId");
        vars.setSessionValue(tabId + "|ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONApprove9E8E9A0881C640A88F0846A961FA6D47")) {
        vars.setSessionValue("button9E8E9A0881C640A88F0846A961FA6D47.strapprove", vars.getStringParameter("inpapprove"));
        vars.setSessionValue("button9E8E9A0881C640A88F0846A961FA6D47.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button9E8E9A0881C640A88F0846A961FA6D47.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button9E8E9A0881C640A88F0846A961FA6D47.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button9E8E9A0881C640A88F0846A961FA6D47.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "9E8E9A0881C640A88F0846A961FA6D47", request.getServletPath());
      } else if (vars.commandIn("BUTTON9E8E9A0881C640A88F0846A961FA6D47")) {
        String strRchr_Approvalhistrory_ID = vars.getGlobalVariable("inprchrApprovalhistroryId", windowId + "|Rchr_Approvalhistrory_ID", "");
        String strapprove = vars.getSessionValue("button9E8E9A0881C640A88F0846A961FA6D47.strapprove");
        String strProcessing = vars.getSessionValue("button9E8E9A0881C640A88F0846A961FA6D47.strProcessing");
        String strOrg = vars.getSessionValue("button9E8E9A0881C640A88F0846A961FA6D47.strOrg");
        String strClient = vars.getSessionValue("button9E8E9A0881C640A88F0846A961FA6D47.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonApprove9E8E9A0881C640A88F0846A961FA6D47(response, vars, strRchr_Approvalhistrory_ID, strapprove, strProcessing);
        }
    } else if (vars.commandIn("BUTTONRejectF78498E7CB2D442396D26954D97A47D7")) {
        vars.setSessionValue("buttonF78498E7CB2D442396D26954D97A47D7.strreject", vars.getStringParameter("inpreject"));
        vars.setSessionValue("buttonF78498E7CB2D442396D26954D97A47D7.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonF78498E7CB2D442396D26954D97A47D7.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonF78498E7CB2D442396D26954D97A47D7.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonF78498E7CB2D442396D26954D97A47D7.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "F78498E7CB2D442396D26954D97A47D7", request.getServletPath());
      } else if (vars.commandIn("BUTTONF78498E7CB2D442396D26954D97A47D7")) {
        String strRchr_Approvalhistrory_ID = vars.getGlobalVariable("inprchrApprovalhistroryId", windowId + "|Rchr_Approvalhistrory_ID", "");
        String strreject = vars.getSessionValue("buttonF78498E7CB2D442396D26954D97A47D7.strreject");
        String strProcessing = vars.getSessionValue("buttonF78498E7CB2D442396D26954D97A47D7.strProcessing");
        String strOrg = vars.getSessionValue("buttonF78498E7CB2D442396D26954D97A47D7.strOrg");
        String strClient = vars.getSessionValue("buttonF78498E7CB2D442396D26954D97A47D7.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonRejectF78498E7CB2D442396D26954D97A47D7(response, vars, strRchr_Approvalhistrory_ID, strreject, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONApprove9E8E9A0881C640A88F0846A961FA6D47")) {
        String strRchr_Approvalhistrory_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Approvalhistrory_ID", "");
        
        ProcessBundle pb = new ProcessBundle("9E8E9A0881C640A88F0846A961FA6D47", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Approvalhistrory_ID", strRchr_Approvalhistrory_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONRejectF78498E7CB2D442396D26954D97A47D7")) {
        String strRchr_Approvalhistrory_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Approvalhistrory_ID", "");
        
        ProcessBundle pb = new ProcessBundle("F78498E7CB2D442396D26954D97A47D7", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Approvalhistrory_ID", strRchr_Approvalhistrory_ID);
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
  private ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData data = new ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.adTabId = vars.getRequiredStringParameter("inpadTabId");     data.adTabIdr = vars.getStringParameter("inpadTabId_R");     data.requestdate = vars.getStringParameter("inprequestdate");     data.recordid = vars.getRequiredStringParameter("inprecordid");     data.adUserId = vars.getRequiredStringParameter("inpadUserId");     data.adUserIdr = vars.getStringParameter("inpadUserId_R");     data.cDoctypeId = vars.getStringParameter("inpcDoctypeId");     data.documentno = vars.getStringParameter("inpdocumentno");     data.decision = vars.getStringParameter("inpdecision");     data.decisionr = vars.getStringParameter("inpdecision_R");     data.aprpoveby = vars.getStringParameter("inpaprpoveby");     data.approvedtime = vars.getStringParameter("inpapprovedtime");     data.lastapproved = vars.getStringParameter("inplastapproved");     data.approve = vars.getRequiredStringParameter("inpapprove");     data.rchrApprovaltemplateId = vars.getRequiredStringParameter("inprchrApprovaltemplateId");     data.rchrApprovaltemplateIdr = vars.getStringParameter("inprchrApprovaltemplateId_R");     data.approveuser = vars.getStringParameter("inpapproveuser");     data.approveuserr = vars.getStringParameter("inpapproveuser_R");     data.rchrApprovalstageId = vars.getRequiredStringParameter("inprchrApprovalstageId");     data.rchrApprovalstageIdr = vars.getStringParameter("inprchrApprovalstageId_R");     data.approvebyuser = vars.getStringParameter("inpapprovebyuser");     data.approvebyuserr = vars.getStringParameter("inpapprovebyuser_R");     data.bestoff = vars.getStringParameter("inpbestoff", "N");    try {   data.updatecount = vars.getRequiredNumericParameter("inpupdatecount");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.stagecount = vars.getRequiredNumericParameter("inpstagecount");  } catch (ServletException paramEx) { ex = paramEx; }     data.reject = vars.getRequiredStringParameter("inpreject");     data.reason = vars.getStringParameter("inpreason");     data.rchrApprovalhistroryId = vars.getRequestGlobalVariable("inprchrApprovalhistroryId", windowId + "|Rchr_Approvalhistrory_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rchr_Approvalhistrory_ID", data[0].getField("rchrApprovalhistroryId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData[] data = ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprchrApprovalhistroryId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Approvalhistrory_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamAD_Tab_ID = vars.getSessionValue(tabId + "|paramAD_Tab_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Tab_ID)) || !(("").equals(strParamAD_Tab_ID) || ("%").equals(strParamAD_Tab_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Approvalhistrory_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Approvalhistrory_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/ApprovalHistory/ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B", false, "document.frmMain.inprchrApprovalhistroryId", "grid", "..", "".equals("Y"), "ApprovalHistory", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), true, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
    xmlDocument.setParameter("KeyName", "rchrApprovalhistroryId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_Relation.html", "ApprovalHistory", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Approvalhistrory_ID, TableSQLData tableSQL)
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
    ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamAD_Tab_ID = vars.getSessionValue(tabId + "|paramAD_Tab_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Tab_ID)) || !(("").equals(strParamAD_Tab_ID) || ("%").equals(strParamAD_Tab_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Approvalhistrory_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Approvalhistrory_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (data==null || data.length==0) {
        strRchr_Approvalhistrory_ID = firstElement(vars, tableSQL);
        if (strRchr_Approvalhistrory_ID.equals("")) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        } else {
          data = ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Approvalhistrory_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
        }
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrApprovalhistroryId") == null || dataField.getField("rchrApprovalhistroryId").equals("")) {
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
        data = ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData.set("", Utility.getDefault(this, vars, "Stagecount", "0", "D2F940DA10264717A1EB8A63367B1073", "0", dataField), Utility.getDefault(this, vars, "AD_User_ID", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Aprpoveby", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Updatecount", "0", "D2F940DA10264717A1EB8A63367B1073", "0", dataField), "Y", Utility.getDefault(this, vars, "Decision", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData.selectDef42F272571400433EB199EC1AA09117CF_0(this, Utility.getDefault(this, vars, "Createdby", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField)), Utility.getDefault(this, vars, "Requestdate", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Recordid", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Bestoff", "N", "D2F940DA10264717A1EB8A63367B1073", "N", dataField), Utility.getDefault(this, vars, "Reason", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "AD_Tab_ID", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Documentno", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Approvedtime", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData.selectDef9561BA46B5E14F59BCFC34F37C78D5FD_1(this, Utility.getDefault(this, vars, "Updatedby", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField)), Utility.getDefault(this, vars, "Approve", "N", "D2F940DA10264717A1EB8A63367B1073", "N", dataField), Utility.getDefault(this, vars, "Lastapproved", "-->", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Reject", "N", "D2F940DA10264717A1EB8A63367B1073", "N", dataField), Utility.getDefault(this, vars, "Rchr_Approvalstage_ID", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Approvebyuser", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Approveuser", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "C_Doctype_ID", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "D2F940DA10264717A1EB8A63367B1073", "", dataField), Utility.getDefault(this, vars, "Rchr_Approvaltemplate_ID", "", "D2F940DA10264717A1EB8A63367B1073", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/ApprovalHistory/ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/ApprovalHistory/ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrApprovalhistroryId", "", "..", "".equals("Y"), "ApprovalHistory", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Approvalhistrory_ID), !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    toolbar.setDeleteable(true);
    toolbar.prepareEditionTemplate("N".equals("Y"), hasSearchCondition, vars.getSessionValue("#ShowTest", "N").equals("Y"), "SR", Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
      // if (!strRchr_Approvalhistrory_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_Relation.html", "ApprovalHistory", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "AD_Tab_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adTabId"):dataField.getField("adTabId")));
xmlDocument.setData("reportAD_Tab_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Requestdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "AD_User_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adUserId"):dataField.getField("adUserId")));
xmlDocument.setData("reportAD_User_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Decision", "AF65B9366A6B4AEEA655C7367D7A924C", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("decision"):dataField.getField("decision")));
xmlDocument.setData("reportDecision","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Approvedtime_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Approve_BTNname", Utility.getButtonName(this, vars, "79FEECCBDDFC4497816524646C5BED4E", "Approve_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Approvaltemplate_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrApprovaltemplateId"):dataField.getField("rchrApprovaltemplateId")));
xmlDocument.setData("reportRchr_Approvaltemplate_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Approveuser", "110", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("approveuser"):dataField.getField("approveuser")));
xmlDocument.setData("reportApproveuser","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Approvalstage_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrApprovalstageId"):dataField.getField("rchrApprovalstageId")));
xmlDocument.setData("reportRchr_Approvalstage_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Approvebyuser", "110", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("approvebyuser"):dataField.getField("approvebyuser")));
xmlDocument.setData("reportApprovebyuser","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Reject_BTNname", Utility.getButtonName(this, vars, "E695DB4EC3B84F3A95373F7E6DC3BED4", "Reject_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalReject = org.openbravo.erpCommon.utility.Utility.isModalProcess("F78498E7CB2D442396D26954D97A47D7"); 
xmlDocument.setParameter("Reject_Modal", modalReject?"true":"false");
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



    void printPageButtonApprove9E8E9A0881C640A88F0846A961FA6D47(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Approvalhistrory_ID, String strapprove, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9E8E9A0881C640A88F0846A961FA6D47");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Approve9E8E9A0881C640A88F0846A961FA6D47", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Approvalhistrory_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "9E8E9A0881C640A88F0846A961FA6D47");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("9E8E9A0881C640A88F0846A961FA6D47");
        vars.removeMessage("9E8E9A0881C640A88F0846A961FA6D47");
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
    void printPageButtonRejectF78498E7CB2D442396D26954D97A47D7(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Approvalhistrory_ID, String strreject, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F78498E7CB2D442396D26954D97A47D7");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/RejectF78498E7CB2D442396D26954D97A47D7", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Approvalhistrory_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "F78498E7CB2D442396D26954D97A47D7");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("F78498E7CB2D442396D26954D97A47D7");
        vars.removeMessage("F78498E7CB2D442396D26954D97A47D7");
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
    ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData data = null;
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
                data.rchrApprovalhistroryId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (ApprovalHistory8CFA69A009664664B51DE9C699F7DD4BData.getCurrentDBTimestamp(this, data.rchrApprovalhistroryId).equals(
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
                    data.rchrApprovalhistroryId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Approvalhistrory_ID", data.rchrApprovalhistroryId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet ApprovalHistory8CFA69A009664664B51DE9C699F7DD4B. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
