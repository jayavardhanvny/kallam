
package org.openbravo.erpWindows.com.rcss.humanresource.MendingInspectionSheet;




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

public class MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.class);
  
  private static final String windowId = "46A6C700CB8945E8B2FA44101BBEF33B";
  private static final String tabId = "E8EBEED258924405AC7358EB56AD948B";
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
     
      if (command.contains("6EEBD3ADA8D945C8B614C641556CEEAD")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("6EEBD3ADA8D945C8B614C641556CEEAD");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("6EEBD3ADA8D945C8B614C641556CEEAD")) {
        classInfo.type = "P";
        classInfo.id = "6EEBD3ADA8D945C8B614C641556CEEAD";
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
        String strrchrInspectionsheetId = request.getParameter("inprchrInspectionsheetId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrInspectionsheetId.equals(""))
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

      String strRchr_Inspectionsheet_ID = vars.getGlobalVariable("inprchrInspectionsheetId", windowId + "|Rchr_Inspectionsheet_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Inspectionsheet_ID.equals("")) strRchr_Inspectionsheet_ID = firstElement(vars, tableSQL);
          if (strRchr_Inspectionsheet_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Inspectionsheet_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Inspectionsheet_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Inspectionsheet_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Inspectionsheet_ID.equals("")) strRchr_Inspectionsheet_ID = vars.getRequiredGlobalVariable("inprchrInspectionsheetId", windowId + "|Rchr_Inspectionsheet_ID");
      else vars.setSessionValue(windowId + "|Rchr_Inspectionsheet_ID", strRchr_Inspectionsheet_ID);
      
      vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Inspectionsheet_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.view");
      String strRchr_Inspectionsheet_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Inspectionsheet_ID = firstElement(vars, tableSQL);
          if (strRchr_Inspectionsheet_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Inspectionsheet_ID.equals("")) strRchr_Inspectionsheet_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Inspectionsheet_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamQualityno", tabId + "|paramQualityno");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rchr_Inspectionsheet_ID");
      String strRchr_Inspectionsheet_ID="";

      String strView = vars.getSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Inspectionsheet_ID = firstElement(vars, tableSQL);
        if (strRchr_Inspectionsheet_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Inspectionsheet_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Inspectionsheet_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRchr_Inspectionsheet_ID = vars.getGlobalVariable("inprchrInspectionsheetId", windowId + "|Rchr_Inspectionsheet_ID", "");
      vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.view", "RELATION");
      printPageDataSheet(response, vars, strRchr_Inspectionsheet_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Inspectionsheet_ID = vars.getGlobalVariable("inprchrInspectionsheetId", windowId + "|Rchr_Inspectionsheet_ID", "");
      vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Inspectionsheet_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRchr_Inspectionsheet_ID = vars.getRequiredStringParameter("inprchrInspectionsheetId");
      
      String strNext = nextElement(vars, strRchr_Inspectionsheet_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRchr_Inspectionsheet_ID = vars.getRequiredStringParameter("inprchrInspectionsheetId");
      
      String strPrevious = previousElement(vars, strRchr_Inspectionsheet_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Inspectionsheet_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Inspectionsheet_ID");

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

      String strRchr_Inspectionsheet_ID = vars.getRequiredGlobalVariable("inprchrInspectionsheetId", windowId + "|Rchr_Inspectionsheet_ID");
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
          String strNext = nextElement(vars, strRchr_Inspectionsheet_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Inspectionsheet_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRchr_Inspectionsheet_ID = vars.getRequiredStringParameter("inprchrInspectionsheetId");
      //MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.delete(this, strRchr_Inspectionsheet_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrInspectionsheetId");
        vars.setSessionValue(tabId + "|MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONMenidngprocess6EEBD3ADA8D945C8B614C641556CEEAD")) {
        vars.setSessionValue("button6EEBD3ADA8D945C8B614C641556CEEAD.strmenidngprocess", vars.getStringParameter("inpmenidngprocess"));
        vars.setSessionValue("button6EEBD3ADA8D945C8B614C641556CEEAD.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button6EEBD3ADA8D945C8B614C641556CEEAD.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button6EEBD3ADA8D945C8B614C641556CEEAD.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button6EEBD3ADA8D945C8B614C641556CEEAD.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "6EEBD3ADA8D945C8B614C641556CEEAD", request.getServletPath());
      } else if (vars.commandIn("BUTTON6EEBD3ADA8D945C8B614C641556CEEAD")) {
        String strRchr_Inspectionsheet_ID = vars.getGlobalVariable("inprchrInspectionsheetId", windowId + "|Rchr_Inspectionsheet_ID", "");
        String strmenidngprocess = vars.getSessionValue("button6EEBD3ADA8D945C8B614C641556CEEAD.strmenidngprocess");
        String strProcessing = vars.getSessionValue("button6EEBD3ADA8D945C8B614C641556CEEAD.strProcessing");
        String strOrg = vars.getSessionValue("button6EEBD3ADA8D945C8B614C641556CEEAD.strOrg");
        String strClient = vars.getSessionValue("button6EEBD3ADA8D945C8B614C641556CEEAD.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonMenidngprocess6EEBD3ADA8D945C8B614C641556CEEAD(response, vars, strRchr_Inspectionsheet_ID, strmenidngprocess, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONMenidngprocess6EEBD3ADA8D945C8B614C641556CEEAD")) {
        String strRchr_Inspectionsheet_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Inspectionsheet_ID", "");
        
        ProcessBundle pb = new ProcessBundle("6EEBD3ADA8D945C8B614C641556CEEAD", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Inspectionsheet_ID", strRchr_Inspectionsheet_ID);
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
  private MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData data = new MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.qualityno = vars.getRequiredStringParameter("inpqualityno");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rcplInsploomctgryId = vars.getStringParameter("inprcplInsploomctgryId");     data.rcplInsploomctgryIdr = vars.getStringParameter("inprcplInsploomctgryId_R");     data.doffdate = vars.getRequiredStringParameter("inpdoffdate");     data.inspdate = vars.getRequiredStringParameter("inpinspdate");     data.inspshift = vars.getRequiredStringParameter("inpinspshift");     data.inspshiftr = vars.getStringParameter("inpinspshift_R");     data.warpweftcount = vars.getRequiredStringParameter("inpwarpweftcount");    try {   data.epi = vars.getRequiredNumericParameter("inpepi");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.ppi = vars.getRequiredNumericParameter("inpppi");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrInspweaveId = vars.getRequiredStringParameter("inprchrInspweaveId");     data.rchrInspweaveIdr = vars.getStringParameter("inprchrInspweaveId_R");     data.rchrInsprolltypeId = vars.getStringParameter("inprchrInsprolltypeId");     data.rchrInsprolltypeIdr = vars.getStringParameter("inprchrInsprolltypeId_R");     data.rchrPiecenolistId = vars.getRequiredStringParameter("inprchrPiecenolistId");     data.rchrPiecenolistIdr = vars.getStringParameter("inprchrPiecenolistId_R");     data.pieceno = vars.getStringParameter("inppieceno");     data.rcprMachineId = vars.getRequiredStringParameter("inprcprMachineId");     data.rcprMachineIdr = vars.getStringParameter("inprcprMachineId_R");    try {   data.widthininches = vars.getRequiredNumericParameter("inpwidthininches");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.loommtr = vars.getRequiredNumericParameter("inploommtr");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalmtr = vars.getRequiredNumericParameter("inptotalmtr");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grossweight = vars.getRequiredNumericParameter("inpgrossweight");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.glm = vars.getNumericParameter("inpglm");  } catch (ServletException paramEx) { ex = paramEx; }     data.dataeop = vars.getRequiredStringParameter("inpdataeop");     data.dataeopr = vars.getStringParameter("inpdataeop_R");    try {   data.totaltwo = vars.getRequiredNumericParameter("inptotaltwo");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalfour = vars.getRequiredNumericParameter("inptotalfour");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalpoints = vars.getNumericParameter("inptotalpoints");  } catch (ServletException paramEx) { ex = paramEx; }     data.selvedgewidthr = vars.getStringParameter("inpselvedgewidthr");     data.selvedgewidthl = vars.getStringParameter("inpselvedgewidthl");     data.fringelenthr = vars.getStringParameter("inpfringelenthr");     data.fringelenthl = vars.getStringParameter("inpfringelenthl");     data.twilldrill = vars.getStringParameter("inptwilldrill");     data.staindir = vars.getStringParameter("inpstaindir");     data.selvedge = vars.getStringParameter("inpselvedge");     data.menidngstatus = vars.getStringParameter("inpmenidngstatus");     data.menidngstatusr = vars.getStringParameter("inpmenidngstatus_R");     data.inspection = vars.getStringParameter("inpinspection");     data.rchrPiecestatusId = vars.getStringParameter("inprchrPiecestatusId");    try {   data.atotal = vars.getNumericParameter("inpatotal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.aonetotal = vars.getNumericParameter("inpaonetotal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.btotal = vars.getNumericParameter("inpbtotal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.sltotal = vars.getNumericParameter("inpsltotal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.cutlookingvalue = vars.getNumericParameter("inpcutlookingvalue");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.rejectioneffi = vars.getNumericParameter("inprejectioneffi");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.slrejectioneffi = vars.getNumericParameter("inpslrejectioneffi");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.remainingquantity = vars.getNumericParameter("inpremainingquantity");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.orderquantity = vars.getNumericParameter("inporderquantity");  } catch (ServletException paramEx) { ex = paramEx; }     data.efficiency = vars.getStringParameter("inpefficiency");     data.remark = vars.getStringParameter("inpremark", "N");     data.remarks = vars.getStringParameter("inpremarks");    try {   data.remainglm = vars.getNumericParameter("inpremainglm");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrInspclothtypeId = vars.getRequiredStringParameter("inprchrInspclothtypeId");     data.menidngprocess = vars.getStringParameter("inpmenidngprocess");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rchrInspectionsheetId = vars.getRequestGlobalVariable("inprchrInspectionsheetId", windowId + "|Rchr_Inspectionsheet_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rchr_Inspectionsheet_ID", data[0].getField("rchrInspectionsheetId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData[] data = MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprchrInspectionsheetId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Inspectionsheet_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamQualityno = vars.getSessionValue(tabId + "|paramQualityno");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamQualityno)) || !(("").equals(strParamQualityno) || ("%").equals(strParamQualityno)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Inspectionsheet_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Inspectionsheet_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/MendingInspectionSheet/MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B", false, "document.frmMain.inprchrInspectionsheetId", "grid", "..", "".equals("Y"), "MendingInspectionSheet", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rchrInspectionsheetId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B_Relation.html", "MendingInspectionSheet", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Inspectionsheet_ID, TableSQLData tableSQL)
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
    MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamQualityno = vars.getSessionValue(tabId + "|paramQualityno");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamQualityno)) || !(("").equals(strParamQualityno) || ("%").equals(strParamQualityno)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Inspectionsheet_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Inspectionsheet_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (data==null || data.length==0) {
        strRchr_Inspectionsheet_ID = firstElement(vars, tableSQL);
        if (strRchr_Inspectionsheet_ID.equals("")) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        } else {
          data = MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Inspectionsheet_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
        }
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrInspectionsheetId") == null || dataField.getField("rchrInspectionsheetId").equals("")) {
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
        data = MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.set(Utility.getDefault(this, vars, "Doffdate", "@#Date@", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Fringelenthl", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Loommtr", "", "46A6C700CB8945E8B2FA44101BBEF33B", "0", dataField), Utility.getDefault(this, vars, "Staindir", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Twilldrill", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Rcpr_Machine_ID", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Qualityno", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Totalpoints", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Rchr_Insprolltype_ID", "8A5B957DBF13494E81386C0D75BE1B3F", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "256551BD83DF49DB80BCE5691149CA0B", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Selvedgewidthr", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Remarks", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Inspdate", "@#Date@", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.selectDef28505CFF10CA4716856FD223C5E09CE4_0(this, Utility.getDefault(this, vars, "Updatedby", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField)), Utility.getDefault(this, vars, "Selvedge", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Orderquantity", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Remainglm", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Rchr_Inspweave_ID", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Remainingquantity", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Efficiency", "N", "46A6C700CB8945E8B2FA44101BBEF33B", "N", dataField), Utility.getDefault(this, vars, "Glm", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Ppi", "", "46A6C700CB8945E8B2FA44101BBEF33B", "0", dataField), Utility.getDefault(this, vars, "Remark", "", "46A6C700CB8945E8B2FA44101BBEF33B", "N", dataField), Utility.getDefault(this, vars, "Rejectioneffi", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Totalfour", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "0", dataField), Utility.getDefault(this, vars, "Widthininches", "", "46A6C700CB8945E8B2FA44101BBEF33B", "0", dataField), Utility.getDefault(this, vars, "Createdby", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.selectDef759D57304734462EB3F94DA2736FCEA5_1(this, Utility.getDefault(this, vars, "Createdby", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField)), Utility.getDefault(this, vars, "Atotal", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Pieceno", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Epi", "", "46A6C700CB8945E8B2FA44101BBEF33B", "0", dataField), "", Utility.getDefault(this, vars, "Warpweftcount", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Inspection", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Rchr_Inspclothtype_ID", "0FC7E139E6634BBEA21FA3F3E2170563", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), "Y", Utility.getDefault(this, vars, "Btotal", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Cutlookingvalue", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Sltotal", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Slrejectioneffi", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Rchr_Piecestatus_ID", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Aonetotal", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Totaltwo", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "0", dataField), Utility.getDefault(this, vars, "Rchr_Piecenolist_ID", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Selvedgewidthl", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Menidngstatus", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Inspshift", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Rcpl_Insploomctgry_ID", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), Utility.getDefault(this, vars, "Grossweight", "0", "46A6C700CB8945E8B2FA44101BBEF33B", "0", dataField), Utility.getDefault(this, vars, "Totalmtr", "", "46A6C700CB8945E8B2FA44101BBEF33B", "0", dataField), Utility.getDefault(this, vars, "Menidngprocess", "N", "46A6C700CB8945E8B2FA44101BBEF33B", "N", dataField), Utility.getDefault(this, vars, "Fringelenthr", "", "46A6C700CB8945E8B2FA44101BBEF33B", "", dataField), MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.selectDefF854883ED588457FB38CD3327379034E(this));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/MendingInspectionSheet/MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/MendingInspectionSheet/MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrInspectionsheetId", "", "..", "".equals("Y"), "MendingInspectionSheet", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Inspectionsheet_ID), !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    toolbar.setDeleteable(true);
    toolbar.prepareEditionTemplate("N".equals("Y"), hasSearchCondition, vars.getSessionValue("#ShowTest", "N").equals("Y"), "RO", Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
      // if (!strRchr_Inspectionsheet_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B_Relation.html", "MendingInspectionSheet", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "Rcpl_Insploomctgry_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rcplInsploomctgryId"):dataField.getField("rcplInsploomctgryId")));
xmlDocument.setData("reportRcpl_Insploomctgry_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Doffdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Inspdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "18", "Inspshift", "320D456FDE704EF0B4EE0E3CA5D60D78", "4E25C778D33C4CDCB8F85851C30A8954", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("inspshift"):dataField.getField("inspshift")));
xmlDocument.setData("reportInspshift","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonEpi", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPpi", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Inspweave_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrInspweaveId"):dataField.getField("rchrInspweaveId")));
xmlDocument.setData("reportRchr_Inspweave_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Insprolltype_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrInsprolltypeId"):dataField.getField("rchrInsprolltypeId")));
xmlDocument.setData("reportRchr_Insprolltype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Rchr_Piecenolist_ID", "3F59206589F2480789E3C0878A8D9CC4", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrPiecenolistId"):dataField.getField("rchrPiecenolistId")));
xmlDocument.setData("reportRchr_Piecenolist_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rcpr_Machine_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rcprMachineId"):dataField.getField("rcprMachineId")));
xmlDocument.setData("reportRcpr_Machine_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonWidthininches", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalmtr", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGrossweight", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGlm", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "Dataeop", "FF8C4A2263A145579A99C28108ECEF24", "C92338047A1C474E94258856F94E47AC", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("dataeop"):dataField.getField("dataeop")));
xmlDocument.setData("reportDataeop","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonTotaltwo", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalfour", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalpoints", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "17", "Menidngstatus", "5C05AE04811E46C59289304A8639EEBB", "77C6E06872EA46309DA473064C282905", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("menidngstatus"):dataField.getField("menidngstatus")));
xmlDocument.setData("reportMenidngstatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonAtotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonAonetotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonBtotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSltotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCutlookingvalue", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonRejectioneffi", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSlrejectioneffi", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonRemainingquantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonOrderquantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonRemainglm", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Menidngprocess_BTNname", Utility.getButtonName(this, vars, "048BD539D59C41559F9D721303209F80", "Menidngprocess_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalMenidngprocess = org.openbravo.erpCommon.utility.Utility.isModalProcess("6EEBD3ADA8D945C8B614C641556CEEAD"); 
xmlDocument.setParameter("Menidngprocess_Modal", modalMenidngprocess?"true":"false");
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



    void printPageButtonMenidngprocess6EEBD3ADA8D945C8B614C641556CEEAD(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Inspectionsheet_ID, String strmenidngprocess, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6EEBD3ADA8D945C8B614C641556CEEAD");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Menidngprocess6EEBD3ADA8D945C8B614C641556CEEAD", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Inspectionsheet_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "6EEBD3ADA8D945C8B614C641556CEEAD");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("6EEBD3ADA8D945C8B614C641556CEEAD");
        vars.removeMessage("6EEBD3ADA8D945C8B614C641556CEEAD");
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
    MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData data = null;
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
                data.rchrInspectionsheetId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (MendingInspectionSheetE8EBEED258924405AC7358EB56AD948BData.getCurrentDBTimestamp(this, data.rchrInspectionsheetId).equals(
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
                    data.rchrInspectionsheetId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Inspectionsheet_ID", data.rchrInspectionsheetId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet MendingInspectionSheetE8EBEED258924405AC7358EB56AD948B. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
