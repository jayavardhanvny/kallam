
package org.openbravo.erpWindows.com.rcss.humanresource.SizingDataSheet;




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

public class SizingBeamsD714ED1289CA455583627EF9D982E3F9 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(SizingBeamsD714ED1289CA455583627EF9D982E3F9.class);
  
  private static final String windowId = "78DB4EAB0B3042C4A0F3CA451362A074";
  private static final String tabId = "D714ED1289CA455583627EF9D982E3F9";
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
     
      if (command.contains("A49580DFBA994334957B0E398916E97F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("A49580DFBA994334957B0E398916E97F");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("A49580DFBA994334957B0E398916E97F")) {
        classInfo.type = "P";
        classInfo.id = "A49580DFBA994334957B0E398916E97F";
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
        String strrchrSizingBeamId = request.getParameter("inprchrSizingBeamId");
         String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrSizingBeamId.equals(""))
              total = saveRecord(vars, myError, 'U', strPRchr_Sizingsheet_ID);
          else
              total = saveRecord(vars, myError, 'I', strPRchr_Sizingsheet_ID);
          
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
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID", "");

      String strRchr_Sizing_Beam_ID = vars.getGlobalVariable("inprchrSizingBeamId", windowId + "|Rchr_Sizing_Beam_ID", "");
            if (strPRchr_Sizingsheet_ID.equals("")) {
        strPRchr_Sizingsheet_ID = getParentID(vars, strRchr_Sizing_Beam_ID);
        if (strPRchr_Sizingsheet_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|Rchr_Sizingsheet_ID");
        vars.setSessionValue(windowId + "|Rchr_Sizingsheet_ID", strPRchr_Sizingsheet_ID);

        refreshParentSession(vars, strPRchr_Sizingsheet_ID);
      }


      String strView = vars.getSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Sizing_Beam_ID.equals("")) strRchr_Sizing_Beam_ID = firstElement(vars, tableSQL);
          if (strRchr_Sizing_Beam_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Sizing_Beam_ID, strPRchr_Sizingsheet_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRchr_Sizingsheet_ID, strRchr_Sizing_Beam_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Sizing_Beam_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Sizing_Beam_ID.equals("")) strRchr_Sizing_Beam_ID = vars.getRequiredGlobalVariable("inprchrSizingBeamId", windowId + "|Rchr_Sizing_Beam_ID");
      else vars.setSessionValue(windowId + "|Rchr_Sizing_Beam_ID", strRchr_Sizing_Beam_ID);
      
      
      String strPRchr_Sizingsheet_ID = getParentID(vars, strRchr_Sizing_Beam_ID);
      
      vars.setSessionValue(windowId + "|Rchr_Sizingsheet_ID", strPRchr_Sizingsheet_ID);
      vars.setSessionValue("D51B7C8348D44320AAC6D0A02AB71D13|Sizing Data Sheet.view", "EDIT");

      refreshParentSession(vars, strPRchr_Sizingsheet_ID);

      vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Sizing_Beam_ID, strPRchr_Sizingsheet_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|Rchr_Sizing_Beam_ID");
      refreshParentSession(vars, strPRchr_Sizingsheet_ID);


      String strView = vars.getSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.view");
      String strRchr_Sizing_Beam_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Sizing_Beam_ID = firstElement(vars, tableSQL);
          if (strRchr_Sizing_Beam_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Sizing_Beam_ID.equals("")) strRchr_Sizing_Beam_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Sizing_Beam_ID, strPRchr_Sizingsheet_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPRchr_Sizingsheet_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamLine", tabId + "|paramLine");
vars.getRequestGlobalVariable("inpParamRchr_Beam_ID", tabId + "|paramRchr_Beam_ID");
vars.getRequestGlobalVariable("inpParamLine_f", tabId + "|paramLine_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");

      
      vars.removeSessionValue(windowId + "|Rchr_Sizing_Beam_ID");
      String strRchr_Sizing_Beam_ID="";

      String strView = vars.getSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Sizing_Beam_ID = firstElement(vars, tableSQL);
        if (strRchr_Sizing_Beam_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Sizing_Beam_ID, strPRchr_Sizingsheet_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRchr_Sizingsheet_ID, strRchr_Sizing_Beam_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
      

      String strRchr_Sizing_Beam_ID = vars.getGlobalVariable("inprchrSizingBeamId", windowId + "|Rchr_Sizing_Beam_ID", "");
      vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.view", "RELATION");
      printPageDataSheet(response, vars, strPRchr_Sizingsheet_ID, strRchr_Sizing_Beam_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");


      printPageEdit(response, request, vars, true, "", strPRchr_Sizingsheet_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Sizing_Beam_ID = vars.getGlobalVariable("inprchrSizingBeamId", windowId + "|Rchr_Sizing_Beam_ID", "");
      vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Sizing_Beam_ID, strPRchr_Sizingsheet_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
      String strRchr_Sizing_Beam_ID = vars.getRequiredStringParameter("inprchrSizingBeamId");
      
      String strNext = nextElement(vars, strRchr_Sizing_Beam_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPRchr_Sizingsheet_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
      String strRchr_Sizing_Beam_ID = vars.getRequiredStringParameter("inprchrSizingBeamId");
      
      String strPrevious = previousElement(vars, strRchr_Sizing_Beam_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPRchr_Sizingsheet_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");

      vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Sizing_Beam_ID");
      vars.setSessionValue(windowId + "|Rchr_Sizingsheet_ID", strPRchr_Sizingsheet_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Sizing_Beam_ID");
      vars.setSessionValue(windowId + "|Rchr_Sizingsheet_ID", strPRchr_Sizingsheet_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPRchr_Sizingsheet_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPRchr_Sizingsheet_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPRchr_Sizingsheet_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPRchr_Sizingsheet_ID);      
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
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
      String strRchr_Sizing_Beam_ID = vars.getRequiredGlobalVariable("inprchrSizingBeamId", windowId + "|Rchr_Sizing_Beam_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPRchr_Sizingsheet_ID);      
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
          String strNext = nextElement(vars, strRchr_Sizing_Beam_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Sizing_Beam_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");

      String strRchr_Sizing_Beam_ID = vars.getRequiredStringParameter("inprchrSizingBeamId");
      //SizingBeamsD714ED1289CA455583627EF9D982E3F9Data data = getEditVariables(vars, strPRchr_Sizingsheet_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.delete(this, strRchr_Sizing_Beam_ID, strPRchr_Sizingsheet_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrSizingBeamId");
        vars.setSessionValue(tabId + "|SizingBeamsD714ED1289CA455583627EF9D982E3F9.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONCompleteA49580DFBA994334957B0E398916E97F")) {
        vars.setSessionValue("buttonA49580DFBA994334957B0E398916E97F.strcomplete", vars.getStringParameter("inpcomplete"));
        vars.setSessionValue("buttonA49580DFBA994334957B0E398916E97F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonA49580DFBA994334957B0E398916E97F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonA49580DFBA994334957B0E398916E97F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonA49580DFBA994334957B0E398916E97F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "A49580DFBA994334957B0E398916E97F", request.getServletPath());
      } else if (vars.commandIn("BUTTONA49580DFBA994334957B0E398916E97F")) {
        String strRchr_Sizing_Beam_ID = vars.getGlobalVariable("inprchrSizingBeamId", windowId + "|Rchr_Sizing_Beam_ID", "");
        String strcomplete = vars.getSessionValue("buttonA49580DFBA994334957B0E398916E97F.strcomplete");
        String strProcessing = vars.getSessionValue("buttonA49580DFBA994334957B0E398916E97F.strProcessing");
        String strOrg = vars.getSessionValue("buttonA49580DFBA994334957B0E398916E97F.strOrg");
        String strClient = vars.getSessionValue("buttonA49580DFBA994334957B0E398916E97F.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCompleteA49580DFBA994334957B0E398916E97F(response, vars, strRchr_Sizing_Beam_ID, strcomplete, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONCompleteA49580DFBA994334957B0E398916E97F")) {
        String strRchr_Sizing_Beam_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Sizing_Beam_ID", "");
        
        ProcessBundle pb = new ProcessBundle("A49580DFBA994334957B0E398916E97F", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Sizing_Beam_ID", strRchr_Sizing_Beam_ID);
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
      String strPRchr_Sizingsheet_ID = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPRchr_Sizingsheet_ID);
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
  private SizingBeamsD714ED1289CA455583627EF9D982E3F9Data getEditVariables(Connection con, VariablesSecureApp vars, String strPRchr_Sizingsheet_ID) throws IOException,ServletException {
    SizingBeamsD714ED1289CA455583627EF9D982E3F9Data data = new SizingBeamsD714ED1289CA455583627EF9D982E3F9Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rchrSizingsheetId = vars.getRequiredStringParameter("inprchrSizingsheetId");    try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.beamdate = vars.getRequiredStringParameter("inpbeamdate");     data.rchrEmployeeId = vars.getRequiredStringParameter("inprchrEmployeeId");     data.rchrEmployeeIdr = vars.getStringParameter("inprchrEmployeeId_R");     data.rcprShiftId = vars.getRequiredStringParameter("inprcprShiftId");     data.rcprShiftIdr = vars.getStringParameter("inprcprShiftId_R");     data.fromtime = vars.getStringParameter("inpfromtime");     data.totime = vars.getStringParameter("inptotime");     data.timedifference = vars.getStringParameter("inptimedifference");     data.rchrBeamId = vars.getRequiredStringParameter("inprchrBeamId");     data.rchrBeamIdr = vars.getStringParameter("inprchrBeamId_R");    try {   data.beamlength = vars.getRequiredNumericParameter("inpbeamlength");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grossWeight = vars.getRequiredNumericParameter("inpgrossWeight");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.tareWeight = vars.getRequiredNumericParameter("inptareWeight");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.netweight = vars.getRequiredNumericParameter("inpnetweight");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.sizepickup = vars.getRequiredNumericParameter("inpsizepickup");  } catch (ServletException paramEx) { ex = paramEx; }     data.rcplLoomcategoryId = vars.getStringParameter("inprcplLoomcategoryId");    try {   data.shiftinmins = vars.getNumericParameter("inpshiftinmins");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrDetentionId = vars.getStringParameter("inprchrDetentionId");     data.rchrDetentionIdr = vars.getStringParameter("inprchrDetentionId_R");     data.detfromtime = vars.getStringParameter("inpdetfromtime");     data.dettotime = vars.getStringParameter("inpdettotime");     data.dettimedifference = vars.getStringParameter("inpdettimedifference");    try {   data.unsizedweight = vars.getRequiredNumericParameter("inpunsizedweight");  } catch (ServletException paramEx) { ex = paramEx; }     data.backsizer = vars.getStringParameter("inpbacksizer");     data.mixer = vars.getStringParameter("inpmixer");     data.helper = vars.getStringParameter("inphelper");     data.process = vars.getStringParameter("inpprocess", "N");     data.complete = vars.getStringParameter("inpcomplete");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rchrSizingBeamId = vars.getRequestGlobalVariable("inprchrSizingBeamId", windowId + "|Rchr_Sizing_Beam_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.rchrSizingsheetId = vars.getGlobalVariable("inprchrSizingsheetId", windowId + "|Rchr_Sizingsheet_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPRchr_Sizingsheet_ID) throws IOException,ServletException {
      
      SizingDataSheetD51B7C8348D44320AAC6D0A02AB71D13Data[] data = SizingDataSheetD51B7C8348D44320AAC6D0A02AB71D13Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Sizingsheet_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|Rchr_Sizingsheet_ID", data[0].rchrSizingsheetId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|Rchr_Sizingsheet_ID", strPRchr_Sizingsheet_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strRchr_Sizing_Beam_ID) throws ServletException {
    String strPRchr_Sizingsheet_ID = SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.selectParentID(this, strRchr_Sizing_Beam_ID);
    if (strPRchr_Sizingsheet_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strRchr_Sizing_Beam_ID);
      throw new ServletException("Parent record not found");
    }
    return strPRchr_Sizingsheet_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rchr_Sizing_Beam_ID", data[0].getField("rchrSizingBeamId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPRchr_Sizingsheet_ID) throws IOException,ServletException {
      SizingBeamsD714ED1289CA455583627EF9D982E3F9Data[] data = SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Sizingsheet_ID, vars.getStringParameter("inprchrSizingBeamId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPRchr_Sizingsheet_ID, String strRchr_Sizing_Beam_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamRchr_Beam_ID = vars.getSessionValue(tabId + "|paramRchr_Beam_ID");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamLine) && ("").equals(strParamRchr_Beam_ID) && ("").equals(strParamLine_f)) || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamRchr_Beam_ID) || ("%").equals(strParamRchr_Beam_ID))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Sizing_Beam_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Sizing_Beam_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/SizingDataSheet/SizingBeamsD714ED1289CA455583627EF9D982E3F9_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "SizingBeamsD714ED1289CA455583627EF9D982E3F9", false, "document.frmMain.inprchrSizingBeamId", "grid", "..", "".equals("Y"), "SizingDataSheet", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPRchr_Sizingsheet_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("1AA7336D97814B9B8DE6E8001626E1F7", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "rchrSizingBeamId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "SizingBeamsD714ED1289CA455583627EF9D982E3F9_Relation.html", "SizingDataSheet", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "SizingBeamsD714ED1289CA455583627EF9D982E3F9_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.selectParent(this, vars.getLanguage(), strPRchr_Sizingsheet_ID));
    else xmlDocument.setParameter("parent", SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.selectParentTrl(this, vars.getLanguage(), strPRchr_Sizingsheet_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Sizing_Beam_ID, String strPRchr_Sizingsheet_ID, TableSQLData tableSQL)
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
    SizingBeamsD714ED1289CA455583627EF9D982E3F9Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamRchr_Beam_ID = vars.getSessionValue(tabId + "|paramRchr_Beam_ID");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamLine) && ("").equals(strParamRchr_Beam_ID) && ("").equals(strParamLine_f)) || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamRchr_Beam_ID) || ("%").equals(strParamRchr_Beam_ID))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Sizingsheet_ID, strRchr_Sizing_Beam_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Sizing_Beam_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new SizingBeamsD714ED1289CA455583627EF9D982E3F9Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrSizingBeamId") == null || dataField.getField("rchrSizingBeamId").equals("")) {
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
        refreshSessionNew(vars, strPRchr_Sizingsheet_ID);
        data = SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.set(strPRchr_Sizingsheet_ID, Utility.getDefault(this, vars, "Rcpl_Loomcategory_ID", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Netweight", "", "78DB4EAB0B3042C4A0F3CA451362A074", "0", dataField), Utility.getDefault(this, vars, "Timedifference", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Detfromtime", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.selectDef2571457E60BE4217B5646A8C4CCB7791(this, strPRchr_Sizingsheet_ID), Utility.getDefault(this, vars, "Gross_Weight", "", "78DB4EAB0B3042C4A0F3CA451362A074", "0", dataField), "", Utility.getDefault(this, vars, "Rchr_Detention_ID", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Shiftinmins", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.selectDef5F1F52ED12094FE38F1DFF95107FA01A_0(this, Utility.getDefault(this, vars, "Createdby", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField)), Utility.getDefault(this, vars, "Dettotime", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Rchr_Beam_ID", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Sizepickup", "0", "78DB4EAB0B3042C4A0F3CA451362A074", "0", dataField), Utility.getDefault(this, vars, "Mixer", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Beamlength", "", "78DB4EAB0B3042C4A0F3CA451362A074", "0", dataField), Utility.getDefault(this, vars, "Dettimedifference", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Helper", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Backsizer", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Fromtime", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), "Y", Utility.getDefault(this, vars, "Complete", "N", "78DB4EAB0B3042C4A0F3CA451362A074", "N", dataField), Utility.getDefault(this, vars, "Unsizedweight", "", "78DB4EAB0B3042C4A0F3CA451362A074", "0", dataField), Utility.getDefault(this, vars, "Beamdate", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Tare_Weight", "", "78DB4EAB0B3042C4A0F3CA451362A074", "0", dataField), Utility.getDefault(this, vars, "Updatedby", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.selectDefE54108371BD34063839FA940DAA26959_1(this, Utility.getDefault(this, vars, "Updatedby", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField)), Utility.getDefault(this, vars, "Process", "N", "78DB4EAB0B3042C4A0F3CA451362A074", "N", dataField), Utility.getDefault(this, vars, "Totime", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField), Utility.getDefault(this, vars, "Rcpr_Shift_ID", "", "78DB4EAB0B3042C4A0F3CA451362A074", "", dataField));
        
      }
     }
      
    String currentPOrg=SizingDataSheetD51B7C8348D44320AAC6D0A02AB71D13Data.selectOrg(this, strPRchr_Sizingsheet_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/SizingDataSheet/SizingBeamsD714ED1289CA455583627EF9D982E3F9_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/SizingDataSheet/SizingBeamsD714ED1289CA455583627EF9D982E3F9_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "SizingBeamsD714ED1289CA455583627EF9D982E3F9", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrSizingBeamId", "", "..", "".equals("Y"), "SizingDataSheet", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Sizing_Beam_ID), !hasReadOnlyAccess);
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
      // if (!strRchr_Sizing_Beam_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "SizingBeamsD714ED1289CA455583627EF9D982E3F9_Relation.html", "SizingDataSheet", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "SizingBeamsD714ED1289CA455583627EF9D982E3F9_Relation.html", strReplaceWith);
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
xmlDocument.setParameter("Beamdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Employee_ID", "", "4BBF1CB2CE524A74AFF49D1DE274D28A", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmployeeId"):dataField.getField("rchrEmployeeId")));
xmlDocument.setData("reportRchr_Employee_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rcpr_Shift_ID", "", "4E25C778D33C4CDCB8F85851C30A8954", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rcprShiftId"):dataField.getField("rcprShiftId")));
xmlDocument.setData("reportRcpr_Shift_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Beam_ID", "", "8D8F428774A846E09A18F53574BE46B4", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrBeamId"):dataField.getField("rchrBeamId")));
xmlDocument.setData("reportRchr_Beam_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonBeamlength", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGross_Weight", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTare_Weight", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonNetweight", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSizepickup", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Detention_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrDetentionId"):dataField.getField("rchrDetentionId")));
xmlDocument.setData("reportRchr_Detention_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonUnsizedweight", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Complete_BTNname", Utility.getButtonName(this, vars, "9AF5168ECABD4DCD96EAC5164734670E", "Complete_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalComplete = org.openbravo.erpCommon.utility.Utility.isModalProcess("A49580DFBA994334957B0E398916E97F"); 
xmlDocument.setParameter("Complete_Modal", modalComplete?"true":"false");
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



    void printPageButtonCompleteA49580DFBA994334957B0E398916E97F(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Sizing_Beam_ID, String strcomplete, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A49580DFBA994334957B0E398916E97F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/CompleteA49580DFBA994334957B0E398916E97F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Sizing_Beam_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "SizingBeamsD714ED1289CA455583627EF9D982E3F9_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "A49580DFBA994334957B0E398916E97F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("A49580DFBA994334957B0E398916E97F");
        vars.removeMessage("A49580DFBA994334957B0E398916E97F");
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPRchr_Sizingsheet_ID) throws IOException, ServletException {
    SizingBeamsD714ED1289CA455583627EF9D982E3F9Data data = null;
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
            data = getEditVariables(con, vars, strPRchr_Sizingsheet_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.rchrSizingBeamId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (SizingBeamsD714ED1289CA455583627EF9D982E3F9Data.getCurrentDBTimestamp(this, data.rchrSizingBeamId).equals(
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
                    data.rchrSizingBeamId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Sizing_Beam_ID", data.rchrSizingBeamId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet SizingBeamsD714ED1289CA455583627EF9D982E3F9. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
