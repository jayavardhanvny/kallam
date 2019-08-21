
package org.openbravo.erpWindows.com.rcss.humanresource.JoiningRejoiningForm;




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

public class JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.class);
  
  private static final String windowId = "5A7195FB974D44B3B7072D70266BAAEB";
  private static final String tabId = "DDF60BD613EE4554B820AC53E2F51AD8";
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
     
      if (command.contains("95DACEED13B34B0280F0740265B38EBB")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("95DACEED13B34B0280F0740265B38EBB");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("95DACEED13B34B0280F0740265B38EBB")) {
        classInfo.type = "P";
        classInfo.id = "95DACEED13B34B0280F0740265B38EBB";
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
        String strrchrJoinRejoiningformId = request.getParameter("inprchrJoinRejoiningformId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrJoinRejoiningformId.equals(""))
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

      String strRchr_Join_Rejoiningform_ID = vars.getGlobalVariable("inprchrJoinRejoiningformId", windowId + "|Rchr_Join_Rejoiningform_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Join_Rejoiningform_ID.equals("")) strRchr_Join_Rejoiningform_ID = firstElement(vars, tableSQL);
          if (strRchr_Join_Rejoiningform_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Join_Rejoiningform_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Join_Rejoiningform_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Join_Rejoiningform_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Join_Rejoiningform_ID.equals("")) strRchr_Join_Rejoiningform_ID = vars.getRequiredGlobalVariable("inprchrJoinRejoiningformId", windowId + "|Rchr_Join_Rejoiningform_ID");
      else vars.setSessionValue(windowId + "|Rchr_Join_Rejoiningform_ID", strRchr_Join_Rejoiningform_ID);
      
      vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Join_Rejoiningform_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.view");
      String strRchr_Join_Rejoiningform_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Join_Rejoiningform_ID = firstElement(vars, tableSQL);
          if (strRchr_Join_Rejoiningform_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Join_Rejoiningform_ID.equals("")) strRchr_Join_Rejoiningform_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Join_Rejoiningform_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentno", tabId + "|paramDocumentno");
vars.getRequestGlobalVariable("inpParamRchr_Desigbasic_ID", tabId + "|paramRchr_Desigbasic_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rchr_Join_Rejoiningform_ID");
      String strRchr_Join_Rejoiningform_ID="";

      String strView = vars.getSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Join_Rejoiningform_ID = firstElement(vars, tableSQL);
        if (strRchr_Join_Rejoiningform_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Join_Rejoiningform_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Join_Rejoiningform_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRchr_Join_Rejoiningform_ID = vars.getGlobalVariable("inprchrJoinRejoiningformId", windowId + "|Rchr_Join_Rejoiningform_ID", "");
      vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.view", "RELATION");
      printPageDataSheet(response, vars, strRchr_Join_Rejoiningform_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Join_Rejoiningform_ID = vars.getGlobalVariable("inprchrJoinRejoiningformId", windowId + "|Rchr_Join_Rejoiningform_ID", "");
      vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Join_Rejoiningform_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRchr_Join_Rejoiningform_ID = vars.getRequiredStringParameter("inprchrJoinRejoiningformId");
      
      String strNext = nextElement(vars, strRchr_Join_Rejoiningform_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRchr_Join_Rejoiningform_ID = vars.getRequiredStringParameter("inprchrJoinRejoiningformId");
      
      String strPrevious = previousElement(vars, strRchr_Join_Rejoiningform_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Join_Rejoiningform_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Join_Rejoiningform_ID");

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

      String strRchr_Join_Rejoiningform_ID = vars.getRequiredGlobalVariable("inprchrJoinRejoiningformId", windowId + "|Rchr_Join_Rejoiningform_ID");
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
          String strNext = nextElement(vars, strRchr_Join_Rejoiningform_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Join_Rejoiningform_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRchr_Join_Rejoiningform_ID = vars.getRequiredStringParameter("inprchrJoinRejoiningformId");
      //JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data.delete(this, strRchr_Join_Rejoiningform_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrJoinRejoiningformId");
        vars.setSessionValue(tabId + "|JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONComplete95DACEED13B34B0280F0740265B38EBB")) {
        vars.setSessionValue("button95DACEED13B34B0280F0740265B38EBB.strcomplete", vars.getStringParameter("inpcomplete"));
        vars.setSessionValue("button95DACEED13B34B0280F0740265B38EBB.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button95DACEED13B34B0280F0740265B38EBB.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button95DACEED13B34B0280F0740265B38EBB.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button95DACEED13B34B0280F0740265B38EBB.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "95DACEED13B34B0280F0740265B38EBB", request.getServletPath());
      } else if (vars.commandIn("BUTTON95DACEED13B34B0280F0740265B38EBB")) {
        String strRchr_Join_Rejoiningform_ID = vars.getGlobalVariable("inprchrJoinRejoiningformId", windowId + "|Rchr_Join_Rejoiningform_ID", "");
        String strcomplete = vars.getSessionValue("button95DACEED13B34B0280F0740265B38EBB.strcomplete");
        String strProcessing = vars.getSessionValue("button95DACEED13B34B0280F0740265B38EBB.strProcessing");
        String strOrg = vars.getSessionValue("button95DACEED13B34B0280F0740265B38EBB.strOrg");
        String strClient = vars.getSessionValue("button95DACEED13B34B0280F0740265B38EBB.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonComplete95DACEED13B34B0280F0740265B38EBB(response, vars, strRchr_Join_Rejoiningform_ID, strcomplete, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONComplete95DACEED13B34B0280F0740265B38EBB")) {
        String strRchr_Join_Rejoiningform_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Join_Rejoiningform_ID", "");
        
        ProcessBundle pb = new ProcessBundle("95DACEED13B34B0280F0740265B38EBB", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Join_Rejoiningform_ID", strRchr_Join_Rejoiningform_ID);
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
  private JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data data = new JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.date = vars.getStringParameter("inpdate");     data.jointype = vars.getStringParameter("inpjointype");     data.jointyper = vars.getStringParameter("inpjointype_R");     data.rchrEmployeeId = vars.getStringParameter("inprchrEmployeeId");     data.rchrEmployeeIdr = vars.getStringParameter("inprchrEmployeeId_R");     data.employeename = vars.getRequiredStringParameter("inpemployeename");     data.punchno = vars.getRequiredStringParameter("inppunchno");     data.cDoctypeId = vars.getStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.employeetype = vars.getRequiredStringParameter("inpemployeetype");     data.employeetyper = vars.getStringParameter("inpemployeetype_R");     data.process = vars.getStringParameter("inpprocess", "N");     data.status = vars.getStringParameter("inpstatus");     data.statusr = vars.getStringParameter("inpstatus_R");     data.rchrSubdepartmentId = vars.getRequiredStringParameter("inprchrSubdepartmentId");     data.rchrSubdepartmentIdr = vars.getStringParameter("inprchrSubdepartmentId_R");     data.rchrEmpDeptId = vars.getRequiredStringParameter("inprchrEmpDeptId");     data.rchrEmpDeptIdr = vars.getStringParameter("inprchrEmpDeptId_R");     data.rchrDesignationId = vars.getRequiredStringParameter("inprchrDesignationId");     data.rchrDesignationIdr = vars.getStringParameter("inprchrDesignationId_R");     data.dob = vars.getRequiredStringParameter("inpdob");     data.weekoffapplicable = vars.getStringParameter("inpweekoffapplicable", "N");     data.weeklyoff = vars.getRequiredStringParameter("inpweeklyoff");     data.weeklyoffr = vars.getStringParameter("inpweeklyoff_R");     data.gender = vars.getRequiredStringParameter("inpgender");     data.genderr = vars.getStringParameter("inpgender_R");     data.maritalstatus = vars.getRequiredStringParameter("inpmaritalstatus");     data.maritalstatusr = vars.getStringParameter("inpmaritalstatus_R");     data.adImageId = vars.getRequiredStringParameter("inpadImageId");     data.isodapplicable = vars.getStringParameter("inpisodapplicable", "N");     data.iscoffapplicable = vars.getStringParameter("inpiscoffapplicable", "N");     data.isleaveapplicable = vars.getStringParameter("inpisleaveapplicable", "N");     data.issenior = vars.getStringParameter("inpissenior", "N");    try {   data.declareservicedays = vars.getNumericParameter("inpdeclareservicedays");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.salary = vars.getRequiredNumericParameter("inpsalary");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.basic = vars.getRequiredNumericParameter("inpbasic");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.serviceincentive = vars.getRequiredNumericParameter("inpserviceincentive");  } catch (ServletException paramEx) { ex = paramEx; }     data.complete = vars.getRequiredStringParameter("inpcomplete");     data.employeeid = vars.getStringParameter("inpemployeeid");    try {   data.cl = vars.getRequiredNumericParameter("inpcl");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.el = vars.getRequiredNumericParameter("inpel");  } catch (ServletException paramEx) { ex = paramEx; }     data.rcplPayrolltemplateId = vars.getStringParameter("inprcplPayrolltemplateId");     data.rcplPayrolltemplateIdr = vars.getStringParameter("inprcplPayrolltemplateId_R");     data.rchrDesigbasicId = vars.getStringParameter("inprchrDesigbasicId");     data.description = vars.getStringParameter("inpdescription");     data.leavingdate = vars.getStringParameter("inpleavingdate");     data.rchrMobalizerId = vars.getStringParameter("inprchrMobalizerId");     data.rchrMobalizerIdr = vars.getStringParameter("inprchrMobalizerId_R");    try {   data.previousservicedays = vars.getNumericParameter("inppreviousservicedays");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrShiftgroupId = vars.getStringParameter("inprchrShiftgroupId");     data.rchrShiftgroupIdr = vars.getStringParameter("inprchrShiftgroupId_R");     data.rchrMrelayId = vars.getStringParameter("inprchrMrelayId");     data.rchrMrelayIdr = vars.getStringParameter("inprchrMrelayId_R");     data.previousjoindate = vars.getStringParameter("inppreviousjoindate");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rchrJoinRejoiningformId = vars.getRequestGlobalVariable("inprchrJoinRejoiningformId", windowId + "|Rchr_Join_Rejoiningform_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rchr_Join_Rejoiningform_ID", data[0].getField("rchrJoinRejoiningformId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data[] data = JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprchrJoinRejoiningformId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Join_Rejoiningform_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");
String strParamRchr_Desigbasic_ID = vars.getSessionValue(tabId + "|paramRchr_Desigbasic_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno) && ("").equals(strParamRchr_Desigbasic_ID)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno))  || !(("").equals(strParamRchr_Desigbasic_ID) || ("%").equals(strParamRchr_Desigbasic_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Join_Rejoiningform_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Join_Rejoiningform_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/JoiningRejoiningForm/JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8", false, "document.frmMain.inprchrJoinRejoiningformId", "grid", "..", "".equals("Y"), "JoiningRejoiningForm", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rchrJoinRejoiningformId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8_Relation.html", "JoiningRejoiningForm", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Join_Rejoiningform_ID, TableSQLData tableSQL)
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
    JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");
String strParamRchr_Desigbasic_ID = vars.getSessionValue(tabId + "|paramRchr_Desigbasic_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno) && ("").equals(strParamRchr_Desigbasic_ID)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno))  || !(("").equals(strParamRchr_Desigbasic_ID) || ("%").equals(strParamRchr_Desigbasic_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Join_Rejoiningform_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Join_Rejoiningform_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrJoinRejoiningformId") == null || dataField.getField("rchrJoinRejoiningformId").equals("")) {
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
        data = JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data.set(Utility.getDefault(this, vars, "Date", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Declareservicedays", "0", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Iscoffapplicable", "N", "5A7195FB974D44B3B7072D70266BAAEB", "N", dataField), Utility.getDefault(this, vars, "Dob", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Rchr_Subdepartment_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Jointype", "J", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Process", "N", "5A7195FB974D44B3B7072D70266BAAEB", "N", dataField), Utility.getDefault(this, vars, "Rchr_Shiftgroup_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Leavingdate", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Basic", "0", "5A7195FB974D44B3B7072D70266BAAEB", "0", dataField), Utility.getDefault(this, vars, "Cl", "0", "5A7195FB974D44B3B7072D70266BAAEB", "0", dataField), Utility.getDefault(this, vars, "Weekoffapplicable", "N", "5A7195FB974D44B3B7072D70266BAAEB", "N", dataField), Utility.getDefault(this, vars, "Punchno", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "AD_Image_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Weeklyoff", "N/A", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Employeename", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Rchr_Designation_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), "Y", Utility.getDefault(this, vars, "Rchr_Mobalizer_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data.selectDef88BD4FD6214B45AEBECC98C6E18A1E9E_0(this, Utility.getDefault(this, vars, "Updatedby", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField)), Utility.getDefault(this, vars, "Rchr_Emp_Dept_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Rchr_Desigbasic_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Isodapplicable", "N", "5A7195FB974D44B3B7072D70266BAAEB", "N", dataField), Utility.getDefault(this, vars, "C_Doctype_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Documentno", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Previousservicedays", "0", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Employeeid", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Isleaveapplicable", "N", "5A7195FB974D44B3B7072D70266BAAEB", "N", dataField), Utility.getDefault(this, vars, "Previousjoindate", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Status", "DR", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data.selectDefC430E5E17F6A482DA5E9A159C3654318_1(this, Utility.getDefault(this, vars, "Createdby", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField)), Utility.getDefault(this, vars, "Gender", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Maritalstatus", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Issenior", "", "5A7195FB974D44B3B7072D70266BAAEB", "N", dataField), "", Utility.getDefault(this, vars, "Description", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Complete", "N", "5A7195FB974D44B3B7072D70266BAAEB", "N", dataField), Utility.getDefault(this, vars, "Salary", "", "5A7195FB974D44B3B7072D70266BAAEB", "0", dataField), Utility.getDefault(this, vars, "Serviceincentive", "0", "5A7195FB974D44B3B7072D70266BAAEB", "0", dataField), Utility.getDefault(this, vars, "Rchr_Mrelay_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "Rcpl_Payrolltemplate_ID", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField), Utility.getDefault(this, vars, "El", "0", "5A7195FB974D44B3B7072D70266BAAEB", "0", dataField), Utility.getDefault(this, vars, "Employeetype", "", "5A7195FB974D44B3B7072D70266BAAEB", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/JoiningRejoiningForm/JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/JoiningRejoiningForm/JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrJoinRejoiningformId", "", "..", "".equals("Y"), "JoiningRejoiningForm", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Join_Rejoiningform_ID), !hasReadOnlyAccess);
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
      // if (!strRchr_Join_Rejoiningform_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8_Relation.html", "JoiningRejoiningForm", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8_Relation.html", strReplaceWith);
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
xmlDocument.setParameter("Date_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "17", "Jointype", "FC14213FA54E428AA6108273EF9E05DC", "B5CD11C68549401780C40E5F4DE8B1D0", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("jointype"):dataField.getField("jointype")));
xmlDocument.setData("reportJointype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Employee_ID", "", "8D62C894E6DF4094B5DD8BEDF7E8F602", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmployeeId"):dataField.getField("rchrEmployeeId")));
xmlDocument.setData("reportRchr_Employee_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Doctype_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_Doctype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Employeetype", "60AC4DA6E1D54452BDC42FF3387DC68C", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("employeetype"):dataField.getField("employeetype")));
xmlDocument.setData("reportEmployeetype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Status", "131", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("status"):dataField.getField("status")));
xmlDocument.setData("reportStatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Subdepartment_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrSubdepartmentId"):dataField.getField("rchrSubdepartmentId")));
xmlDocument.setData("reportRchr_Subdepartment_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Emp_Dept_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmpDeptId"):dataField.getField("rchrEmpDeptId")));
xmlDocument.setData("reportRchr_Emp_Dept_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Designation_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrDesignationId"):dataField.getField("rchrDesignationId")));
xmlDocument.setData("reportRchr_Designation_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Dob_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "17", "Weeklyoff", "A39B727AF2784C809BCF4AD5DED5C3DC", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("weeklyoff"):dataField.getField("weeklyoff")));
xmlDocument.setData("reportWeeklyoff","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Gender", "B8F4E6135F39438AB324C66053F51662", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("gender"):dataField.getField("gender")));
xmlDocument.setData("reportGender","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Maritalstatus", "E34F72895CB8445199EA344EB9C6B0D4", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("maritalstatus"):dataField.getField("maritalstatus")));
xmlDocument.setData("reportMaritalstatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
String strCurrentImageURLAD_Image_ID = (dataField==null?data[0].getField("adImageId"):dataField.getField("adImageId"));
if (strCurrentImageURLAD_Image_ID==null || strCurrentImageURLAD_Image_ID.equals("")){
  xmlDocument.setParameter("AD_Image_IDClass", "Image_NotAvailable_medium");
}

xmlDocument.setParameter("buttonDeclareservicedays", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSalary", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonBasic", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonServiceincentive", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Complete_BTNname", Utility.getButtonName(this, vars, "2AA24CAC568A472098FDF3D86F804E9E", "Complete_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalComplete = org.openbravo.erpCommon.utility.Utility.isModalProcess("95DACEED13B34B0280F0740265B38EBB"); 
xmlDocument.setParameter("Complete_Modal", modalComplete?"true":"false");
comboTableData = new ComboTableData(vars, this, "19", "Rcpl_Payrolltemplate_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rcplPayrolltemplateId"):dataField.getField("rcplPayrolltemplateId")));
xmlDocument.setData("reportRcpl_Payrolltemplate_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Leavingdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Mobalizer_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrMobalizerId"):dataField.getField("rchrMobalizerId")));
xmlDocument.setData("reportRchr_Mobalizer_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Shiftgroup_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrShiftgroupId"):dataField.getField("rchrShiftgroupId")));
xmlDocument.setData("reportRchr_Shiftgroup_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Mrelay_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrMrelayId"):dataField.getField("rchrMrelayId")));
xmlDocument.setData("reportRchr_Mrelay_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Previousjoindate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
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



    void printPageButtonComplete95DACEED13B34B0280F0740265B38EBB(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Join_Rejoiningform_ID, String strcomplete, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 95DACEED13B34B0280F0740265B38EBB");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Complete95DACEED13B34B0280F0740265B38EBB", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Join_Rejoiningform_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "95DACEED13B34B0280F0740265B38EBB");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("95DACEED13B34B0280F0740265B38EBB");
        vars.removeMessage("95DACEED13B34B0280F0740265B38EBB");
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
    JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data data = null;
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
                data.rchrJoinRejoiningformId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8Data.getCurrentDBTimestamp(this, data.rchrJoinRejoiningformId).equals(
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
                    data.rchrJoinRejoiningformId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Join_Rejoiningform_ID", data.rchrJoinRejoiningformId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet JoiningRejoiningDDF60BD613EE4554B820AC53E2F51AD8. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
