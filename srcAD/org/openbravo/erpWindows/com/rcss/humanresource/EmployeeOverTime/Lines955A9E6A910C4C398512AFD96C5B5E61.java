
package org.openbravo.erpWindows.com.rcss.humanresource.EmployeeOverTime;




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

public class Lines955A9E6A910C4C398512AFD96C5B5E61 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Lines955A9E6A910C4C398512AFD96C5B5E61.class);
  
  private static final String windowId = "60CB610DD33C4A10A4AC69B15E362F07";
  private static final String tabId = "955A9E6A910C4C398512AFD96C5B5E61";
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
     
      if (command.contains("93876A2A71E14EB7AFEAF1D05F60F832")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("93876A2A71E14EB7AFEAF1D05F60F832");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("93876A2A71E14EB7AFEAF1D05F60F832")) {
        classInfo.type = "P";
        classInfo.id = "93876A2A71E14EB7AFEAF1D05F60F832";
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
        String strrchrOvertimeId = request.getParameter("inprchrOvertimeId");
         String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrOvertimeId.equals(""))
              total = saveRecord(vars, myError, 'U', strPRchr_Overtimeheader_ID);
          else
              total = saveRecord(vars, myError, 'I', strPRchr_Overtimeheader_ID);
          
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
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID", "");

      String strRchr_Overtime_ID = vars.getGlobalVariable("inprchrOvertimeId", windowId + "|Rchr_Overtime_ID", "");
            if (strPRchr_Overtimeheader_ID.equals("")) {
        strPRchr_Overtimeheader_ID = getParentID(vars, strRchr_Overtime_ID);
        if (strPRchr_Overtimeheader_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|Rchr_Overtimeheader_ID");
        vars.setSessionValue(windowId + "|Rchr_Overtimeheader_ID", strPRchr_Overtimeheader_ID);

        refreshParentSession(vars, strPRchr_Overtimeheader_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Overtime_ID.equals("")) strRchr_Overtime_ID = firstElement(vars, tableSQL);
          if (strRchr_Overtime_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Overtime_ID, strPRchr_Overtimeheader_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRchr_Overtimeheader_ID, strRchr_Overtime_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Overtime_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Overtime_ID.equals("")) strRchr_Overtime_ID = vars.getRequiredGlobalVariable("inprchrOvertimeId", windowId + "|Rchr_Overtime_ID");
      else vars.setSessionValue(windowId + "|Rchr_Overtime_ID", strRchr_Overtime_ID);
      
      
      String strPRchr_Overtimeheader_ID = getParentID(vars, strRchr_Overtime_ID);
      
      vars.setSessionValue(windowId + "|Rchr_Overtimeheader_ID", strPRchr_Overtimeheader_ID);
      vars.setSessionValue("E6A64D5828994C469282D819A0CA338E|Employee Over Time.view", "EDIT");

      refreshParentSession(vars, strPRchr_Overtimeheader_ID);

      vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Overtime_ID, strPRchr_Overtimeheader_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|Rchr_Overtime_ID");
      refreshParentSession(vars, strPRchr_Overtimeheader_ID);


      String strView = vars.getSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.view");
      String strRchr_Overtime_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Overtime_ID = firstElement(vars, tableSQL);
          if (strRchr_Overtime_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Overtime_ID.equals("")) strRchr_Overtime_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Overtime_ID, strPRchr_Overtimeheader_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPRchr_Overtimeheader_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamRchr_Employee_ID", tabId + "|paramRchr_Employee_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");

      
      vars.removeSessionValue(windowId + "|Rchr_Overtime_ID");
      String strRchr_Overtime_ID="";

      String strView = vars.getSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Overtime_ID = firstElement(vars, tableSQL);
        if (strRchr_Overtime_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Overtime_ID, strPRchr_Overtimeheader_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRchr_Overtimeheader_ID, strRchr_Overtime_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
      

      String strRchr_Overtime_ID = vars.getGlobalVariable("inprchrOvertimeId", windowId + "|Rchr_Overtime_ID", "");
      vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.view", "RELATION");
      printPageDataSheet(response, vars, strPRchr_Overtimeheader_ID, strRchr_Overtime_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");


      printPageEdit(response, request, vars, true, "", strPRchr_Overtimeheader_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Overtime_ID = vars.getGlobalVariable("inprchrOvertimeId", windowId + "|Rchr_Overtime_ID", "");
      vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Overtime_ID, strPRchr_Overtimeheader_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
      String strRchr_Overtime_ID = vars.getRequiredStringParameter("inprchrOvertimeId");
      
      String strNext = nextElement(vars, strRchr_Overtime_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPRchr_Overtimeheader_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
      String strRchr_Overtime_ID = vars.getRequiredStringParameter("inprchrOvertimeId");
      
      String strPrevious = previousElement(vars, strRchr_Overtime_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPRchr_Overtimeheader_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");

      vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Overtime_ID");
      vars.setSessionValue(windowId + "|Rchr_Overtimeheader_ID", strPRchr_Overtimeheader_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Overtime_ID");
      vars.setSessionValue(windowId + "|Rchr_Overtimeheader_ID", strPRchr_Overtimeheader_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPRchr_Overtimeheader_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPRchr_Overtimeheader_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPRchr_Overtimeheader_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPRchr_Overtimeheader_ID);      
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
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
      String strRchr_Overtime_ID = vars.getRequiredGlobalVariable("inprchrOvertimeId", windowId + "|Rchr_Overtime_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPRchr_Overtimeheader_ID);      
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
          String strNext = nextElement(vars, strRchr_Overtime_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Overtime_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");

      String strRchr_Overtime_ID = vars.getRequiredStringParameter("inprchrOvertimeId");
      //Lines955A9E6A910C4C398512AFD96C5B5E61Data data = getEditVariables(vars, strPRchr_Overtimeheader_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = Lines955A9E6A910C4C398512AFD96C5B5E61Data.delete(this, strRchr_Overtime_ID, strPRchr_Overtimeheader_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrOvertimeId");
        vars.setSessionValue(tabId + "|Lines955A9E6A910C4C398512AFD96C5B5E61.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONProcess93876A2A71E14EB7AFEAF1D05F60F832")) {
        vars.setSessionValue("button93876A2A71E14EB7AFEAF1D05F60F832.strprocess", vars.getStringParameter("inpprocess"));
        vars.setSessionValue("button93876A2A71E14EB7AFEAF1D05F60F832.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button93876A2A71E14EB7AFEAF1D05F60F832.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button93876A2A71E14EB7AFEAF1D05F60F832.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button93876A2A71E14EB7AFEAF1D05F60F832.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "93876A2A71E14EB7AFEAF1D05F60F832", request.getServletPath());
      } else if (vars.commandIn("BUTTON93876A2A71E14EB7AFEAF1D05F60F832")) {
        String strRchr_Overtime_ID = vars.getGlobalVariable("inprchrOvertimeId", windowId + "|Rchr_Overtime_ID", "");
        String strprocess = vars.getSessionValue("button93876A2A71E14EB7AFEAF1D05F60F832.strprocess");
        String strProcessing = vars.getSessionValue("button93876A2A71E14EB7AFEAF1D05F60F832.strProcessing");
        String strOrg = vars.getSessionValue("button93876A2A71E14EB7AFEAF1D05F60F832.strOrg");
        String strClient = vars.getSessionValue("button93876A2A71E14EB7AFEAF1D05F60F832.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcess93876A2A71E14EB7AFEAF1D05F60F832(response, vars, strRchr_Overtime_ID, strprocess, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcess93876A2A71E14EB7AFEAF1D05F60F832")) {
        String strRchr_Overtime_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Overtime_ID", "");
        
        ProcessBundle pb = new ProcessBundle("93876A2A71E14EB7AFEAF1D05F60F832", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Overtime_ID", strRchr_Overtime_ID);
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
      String strPRchr_Overtimeheader_ID = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPRchr_Overtimeheader_ID);
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
  private Lines955A9E6A910C4C398512AFD96C5B5E61Data getEditVariables(Connection con, VariablesSecureApp vars, String strPRchr_Overtimeheader_ID) throws IOException,ServletException {
    Lines955A9E6A910C4C398512AFD96C5B5E61Data data = new Lines955A9E6A910C4C398512AFD96C5B5E61Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.rchrEmployeeId = vars.getRequiredStringParameter("inprchrEmployeeId");     data.rchrEmployeeIdr = vars.getStringParameter("inprchrEmployeeId_R");     data.rchrEmpDeptId = vars.getStringParameter("inprchrEmpDeptId");    try {   data.numberofots = vars.getNumericParameter("inpnumberofots");  } catch (ServletException paramEx) { ex = paramEx; }     data.isactive = vars.getStringParameter("inpisactive", "N");    try {   data.dayrate = vars.getRequiredNumericParameter("inpdayrate");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.seviceincentive = vars.getRequiredNumericParameter("inpseviceincentive");  } catch (ServletException paramEx) { ex = paramEx; }     data.ottype = vars.getStringParameter("inpottype");    try {   data.amount = vars.getRequiredNumericParameter("inpamount");  } catch (ServletException paramEx) { ex = paramEx; }     data.process = vars.getRequiredStringParameter("inpprocess");     data.paiddate = vars.getStringParameter("inppaiddate");     data.ispaid = vars.getStringParameter("inpispaid", "N");     data.rcprShiftId = vars.getStringParameter("inprcprShiftId");     data.rchrOvertimeheaderId = vars.getRequiredStringParameter("inprchrOvertimeheaderId");    try {   data.servicedays = vars.getNumericParameter("inpservicedays");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.numberofcontinues = vars.getNumericParameter("inpnumberofcontinues");  } catch (ServletException paramEx) { ex = paramEx; }     data.adImageId = vars.getStringParameter("inpadImageId");     data.adImageIdr = vars.getStringParameter("inpadImageId_R");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rchrOvertimeId = vars.getRequestGlobalVariable("inprchrOvertimeId", windowId + "|Rchr_Overtime_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.rchrOvertimeheaderId = vars.getGlobalVariable("inprchrOvertimeheaderId", windowId + "|Rchr_Overtimeheader_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPRchr_Overtimeheader_ID) throws IOException,ServletException {
      
      EmployeeOverTimeE6A64D5828994C469282D819A0CA338EData[] data = EmployeeOverTimeE6A64D5828994C469282D819A0CA338EData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Overtimeheader_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|Rchr_Overtimeheader_ID", data[0].rchrOvertimeheaderId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|Rchr_Overtimeheader_ID", strPRchr_Overtimeheader_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strRchr_Overtime_ID) throws ServletException {
    String strPRchr_Overtimeheader_ID = Lines955A9E6A910C4C398512AFD96C5B5E61Data.selectParentID(this, strRchr_Overtime_ID);
    if (strPRchr_Overtimeheader_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strRchr_Overtime_ID);
      throw new ServletException("Parent record not found");
    }
    return strPRchr_Overtimeheader_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rchr_Overtime_ID", data[0].getField("rchrOvertimeId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPRchr_Overtimeheader_ID) throws IOException,ServletException {
      Lines955A9E6A910C4C398512AFD96C5B5E61Data[] data = Lines955A9E6A910C4C398512AFD96C5B5E61Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Overtimeheader_ID, vars.getStringParameter("inprchrOvertimeId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPRchr_Overtimeheader_ID, String strRchr_Overtime_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamRchr_Employee_ID = vars.getSessionValue(tabId + "|paramRchr_Employee_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Employee_ID)) || !(("").equals(strParamRchr_Employee_ID) || ("%").equals(strParamRchr_Employee_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Overtime_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Overtime_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/EmployeeOverTime/Lines955A9E6A910C4C398512AFD96C5B5E61_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Lines955A9E6A910C4C398512AFD96C5B5E61", false, "document.frmMain.inprchrOvertimeId", "grid", "..", "".equals("Y"), "EmployeeOverTime", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), true, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPRchr_Overtimeheader_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("5CEB4BBF66514378AF71D298D86095E5", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "rchrOvertimeId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines955A9E6A910C4C398512AFD96C5B5E61_Relation.html", "EmployeeOverTime", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines955A9E6A910C4C398512AFD96C5B5E61_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", Lines955A9E6A910C4C398512AFD96C5B5E61Data.selectParent(this, strPRchr_Overtimeheader_ID));
    else xmlDocument.setParameter("parent", Lines955A9E6A910C4C398512AFD96C5B5E61Data.selectParentTrl(this, strPRchr_Overtimeheader_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Overtime_ID, String strPRchr_Overtimeheader_ID, TableSQLData tableSQL)
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
    Lines955A9E6A910C4C398512AFD96C5B5E61Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamRchr_Employee_ID = vars.getSessionValue(tabId + "|paramRchr_Employee_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Employee_ID)) || !(("").equals(strParamRchr_Employee_ID) || ("%").equals(strParamRchr_Employee_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = Lines955A9E6A910C4C398512AFD96C5B5E61Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Overtimeheader_ID, strRchr_Overtime_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Overtime_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (data==null || data.length==0) {
        strRchr_Overtime_ID = firstElement(vars, tableSQL);
        if (strRchr_Overtime_ID.equals("")) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        } else {
          data = Lines955A9E6A910C4C398512AFD96C5B5E61Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Overtimeheader_ID, strRchr_Overtime_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
        }
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new Lines955A9E6A910C4C398512AFD96C5B5E61Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrOvertimeId") == null || dataField.getField("rchrOvertimeId").equals("")) {
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
        refreshSessionNew(vars, strPRchr_Overtimeheader_ID);
        data = Lines955A9E6A910C4C398512AFD96C5B5E61Data.set(strPRchr_Overtimeheader_ID, Utility.getDefault(this, vars, "Updatedby", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Lines955A9E6A910C4C398512AFD96C5B5E61Data.selectDef16A880F2B3E74018812F825132C59EE1_0(this, Utility.getDefault(this, vars, "Updatedby", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField)), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "Amount", "0", "60CB610DD33C4A10A4AC69B15E362F07", "0", dataField), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), "Y", Utility.getDefault(this, vars, "Numberofcontinues", "0", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "AD_Image_ID", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "Servicedays", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "Rcpr_Shift_ID", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "Dayrate", "0", "60CB610DD33C4A10A4AC69B15E362F07", "0", dataField), Utility.getDefault(this, vars, "Numberofots", "0", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), "", Utility.getDefault(this, vars, "Ottype", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "Seviceincentive", "", "60CB610DD33C4A10A4AC69B15E362F07", "0", dataField), Utility.getDefault(this, vars, "Rchr_Emp_Dept_ID", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "Paiddate", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Lines955A9E6A910C4C398512AFD96C5B5E61Data.selectDefDD1F780BA211405CA10945A09DC2E7D0_1(this, Utility.getDefault(this, vars, "Createdby", "", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField)), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "60CB610DD33C4A10A4AC69B15E362F07", "", dataField), Utility.getDefault(this, vars, "Ispaid", "N", "60CB610DD33C4A10A4AC69B15E362F07", "N", dataField), Utility.getDefault(this, vars, "Process", "N", "60CB610DD33C4A10A4AC69B15E362F07", "N", dataField));
        
      }
     }
      
    String currentPOrg=EmployeeOverTimeE6A64D5828994C469282D819A0CA338EData.selectOrg(this, strPRchr_Overtimeheader_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/EmployeeOverTime/Lines955A9E6A910C4C398512AFD96C5B5E61_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/EmployeeOverTime/Lines955A9E6A910C4C398512AFD96C5B5E61_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Lines955A9E6A910C4C398512AFD96C5B5E61", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrOvertimeId", "", "..", "".equals("Y"), "EmployeeOverTime", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Overtime_ID), !hasReadOnlyAccess);
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
      // if (!strRchr_Overtime_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines955A9E6A910C4C398512AFD96C5B5E61_Relation.html", "EmployeeOverTime", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines955A9E6A910C4C398512AFD96C5B5E61_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Employee_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmployeeId"):dataField.getField("rchrEmployeeId")));
xmlDocument.setData("reportRchr_Employee_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonNumberofots", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDayrate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonAmount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Process_BTNname", Utility.getButtonName(this, vars, "5A650290A9DA4E10AEAF79FA777CC15B", "Process_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcess = org.openbravo.erpCommon.utility.Utility.isModalProcess("93876A2A71E14EB7AFEAF1D05F60F832"); 
xmlDocument.setParameter("Process_Modal", modalProcess?"true":"false");
xmlDocument.setParameter("Paiddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonServicedays", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonNumberofcontinues", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "AD_Image_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adImageId"):dataField.getField("adImageId")));
xmlDocument.setData("reportAD_Image_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
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



    void printPageButtonProcess93876A2A71E14EB7AFEAF1D05F60F832(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Overtime_ID, String strprocess, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 93876A2A71E14EB7AFEAF1D05F60F832");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Process93876A2A71E14EB7AFEAF1D05F60F832", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Overtime_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Lines955A9E6A910C4C398512AFD96C5B5E61_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "93876A2A71E14EB7AFEAF1D05F60F832");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("93876A2A71E14EB7AFEAF1D05F60F832");
        vars.removeMessage("93876A2A71E14EB7AFEAF1D05F60F832");
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPRchr_Overtimeheader_ID) throws IOException, ServletException {
    Lines955A9E6A910C4C398512AFD96C5B5E61Data data = null;
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
            data = getEditVariables(con, vars, strPRchr_Overtimeheader_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.rchrOvertimeId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (Lines955A9E6A910C4C398512AFD96C5B5E61Data.getCurrentDBTimestamp(this, data.rchrOvertimeId).equals(
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
                    data.rchrOvertimeId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Overtime_ID", data.rchrOvertimeId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Lines955A9E6A910C4C398512AFD96C5B5E61. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
