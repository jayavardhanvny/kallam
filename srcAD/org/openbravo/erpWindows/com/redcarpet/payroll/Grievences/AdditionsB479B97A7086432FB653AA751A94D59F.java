
package org.openbravo.erpWindows.com.redcarpet.payroll.Grievences;




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

public class AdditionsB479B97A7086432FB653AA751A94D59F extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(AdditionsB479B97A7086432FB653AA751A94D59F.class);
  
  private static final String windowId = "BA00CD9835D0423193FAB5395377FA71";
  private static final String tabId = "B479B97A7086432FB653AA751A94D59F";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "6105C1DA6BF846EBBEEAF6105EAA0267";
  
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
     
      if (command.contains("E038F781230D4505AAADDE1EBFD2573E")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("E038F781230D4505AAADDE1EBFD2573E");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("E038F781230D4505AAADDE1EBFD2573E")) {
        classInfo.type = "P";
        classInfo.id = "E038F781230D4505AAADDE1EBFD2573E";
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
        String strrcplEmppprocessmanualId = request.getParameter("inprcplEmppprocessmanualId");
         String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrcplEmppprocessmanualId.equals(""))
              total = saveRecord(vars, myError, 'U', strPRcpl_Payrollprocessmanual_ID);
          else
              total = saveRecord(vars, myError, 'I', strPRcpl_Payrollprocessmanual_ID);
          
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
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID", "");

      String strRcpl_Emppprocessmanual_ID = vars.getGlobalVariable("inprcplEmppprocessmanualId", windowId + "|Rcpl_Emppprocessmanual_ID", "");
            if (strPRcpl_Payrollprocessmanual_ID.equals("")) {
        strPRcpl_Payrollprocessmanual_ID = getParentID(vars, strRcpl_Emppprocessmanual_ID);
        if (strPRcpl_Payrollprocessmanual_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|Rcpl_Payrollprocessmanual_ID");
        vars.setSessionValue(windowId + "|Rcpl_Payrollprocessmanual_ID", strPRcpl_Payrollprocessmanual_ID);

        refreshParentSession(vars, strPRcpl_Payrollprocessmanual_ID);
      }


      String strView = vars.getSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRcpl_Emppprocessmanual_ID.equals("")) strRcpl_Emppprocessmanual_ID = firstElement(vars, tableSQL);
          if (strRcpl_Emppprocessmanual_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcpl_Emppprocessmanual_ID, strPRcpl_Payrollprocessmanual_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRcpl_Payrollprocessmanual_ID, strRcpl_Emppprocessmanual_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRcpl_Emppprocessmanual_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRcpl_Emppprocessmanual_ID.equals("")) strRcpl_Emppprocessmanual_ID = vars.getRequiredGlobalVariable("inprcplEmppprocessmanualId", windowId + "|Rcpl_Emppprocessmanual_ID");
      else vars.setSessionValue(windowId + "|Rcpl_Emppprocessmanual_ID", strRcpl_Emppprocessmanual_ID);
      
      
      String strPRcpl_Payrollprocessmanual_ID = getParentID(vars, strRcpl_Emppprocessmanual_ID);
      
      vars.setSessionValue(windowId + "|Rcpl_Payrollprocessmanual_ID", strPRcpl_Payrollprocessmanual_ID);
      vars.setSessionValue("E28D8318C8D249BDBAA06C584ECC32C2|Grievences.view", "EDIT");

      refreshParentSession(vars, strPRcpl_Payrollprocessmanual_ID);

      vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.view", "EDIT");

      printPageEdit(response, request, vars, false, strRcpl_Emppprocessmanual_ID, strPRcpl_Payrollprocessmanual_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|Rcpl_Emppprocessmanual_ID");
      refreshParentSession(vars, strPRcpl_Payrollprocessmanual_ID);


      String strView = vars.getSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.view");
      String strRcpl_Emppprocessmanual_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRcpl_Emppprocessmanual_ID = firstElement(vars, tableSQL);
          if (strRcpl_Emppprocessmanual_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRcpl_Emppprocessmanual_ID.equals("")) strRcpl_Emppprocessmanual_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRcpl_Emppprocessmanual_ID, strPRcpl_Payrollprocessmanual_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPRcpl_Payrollprocessmanual_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDescription", tabId + "|paramDescription");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");

      
      vars.removeSessionValue(windowId + "|Rcpl_Emppprocessmanual_ID");
      String strRcpl_Emppprocessmanual_ID="";

      String strView = vars.getSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRcpl_Emppprocessmanual_ID = firstElement(vars, tableSQL);
        if (strRcpl_Emppprocessmanual_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcpl_Emppprocessmanual_ID, strPRcpl_Payrollprocessmanual_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRcpl_Payrollprocessmanual_ID, strRcpl_Emppprocessmanual_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
      

      String strRcpl_Emppprocessmanual_ID = vars.getGlobalVariable("inprcplEmppprocessmanualId", windowId + "|Rcpl_Emppprocessmanual_ID", "");
      vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.view", "RELATION");
      printPageDataSheet(response, vars, strPRcpl_Payrollprocessmanual_ID, strRcpl_Emppprocessmanual_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");


      printPageEdit(response, request, vars, true, "", strPRcpl_Payrollprocessmanual_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRcpl_Emppprocessmanual_ID = vars.getGlobalVariable("inprcplEmppprocessmanualId", windowId + "|Rcpl_Emppprocessmanual_ID", "");
      vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRcpl_Emppprocessmanual_ID, strPRcpl_Payrollprocessmanual_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
      String strRcpl_Emppprocessmanual_ID = vars.getRequiredStringParameter("inprcplEmppprocessmanualId");
      
      String strNext = nextElement(vars, strRcpl_Emppprocessmanual_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPRcpl_Payrollprocessmanual_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
      String strRcpl_Emppprocessmanual_ID = vars.getRequiredStringParameter("inprcplEmppprocessmanualId");
      
      String strPrevious = previousElement(vars, strRcpl_Emppprocessmanual_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPRcpl_Payrollprocessmanual_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");

      vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rcpl_Emppprocessmanual_ID");
      vars.setSessionValue(windowId + "|Rcpl_Payrollprocessmanual_ID", strPRcpl_Payrollprocessmanual_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rcpl_Emppprocessmanual_ID");
      vars.setSessionValue(windowId + "|Rcpl_Payrollprocessmanual_ID", strPRcpl_Payrollprocessmanual_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPRcpl_Payrollprocessmanual_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPRcpl_Payrollprocessmanual_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPRcpl_Payrollprocessmanual_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPRcpl_Payrollprocessmanual_ID);      
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
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
      String strRcpl_Emppprocessmanual_ID = vars.getRequiredGlobalVariable("inprcplEmppprocessmanualId", windowId + "|Rcpl_Emppprocessmanual_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPRcpl_Payrollprocessmanual_ID);      
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
          String strNext = nextElement(vars, strRcpl_Emppprocessmanual_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rcpl_Emppprocessmanual_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");

      String strRcpl_Emppprocessmanual_ID = vars.getRequiredStringParameter("inprcplEmppprocessmanualId");
      //AdditionsB479B97A7086432FB653AA751A94D59FData data = getEditVariables(vars, strPRcpl_Payrollprocessmanual_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = AdditionsB479B97A7086432FB653AA751A94D59FData.delete(this, strRcpl_Emppprocessmanual_ID, strPRcpl_Payrollprocessmanual_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rcplEmppprocessmanualId");
        vars.setSessionValue(tabId + "|AdditionsB479B97A7086432FB653AA751A94D59F.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONCompleteE038F781230D4505AAADDE1EBFD2573E")) {
        vars.setSessionValue("buttonE038F781230D4505AAADDE1EBFD2573E.strcomplete", vars.getStringParameter("inpcomplete"));
        vars.setSessionValue("buttonE038F781230D4505AAADDE1EBFD2573E.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonE038F781230D4505AAADDE1EBFD2573E.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonE038F781230D4505AAADDE1EBFD2573E.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonE038F781230D4505AAADDE1EBFD2573E.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "E038F781230D4505AAADDE1EBFD2573E", request.getServletPath());
      } else if (vars.commandIn("BUTTONE038F781230D4505AAADDE1EBFD2573E")) {
        String strRcpl_Emppprocessmanual_ID = vars.getGlobalVariable("inprcplEmppprocessmanualId", windowId + "|Rcpl_Emppprocessmanual_ID", "");
        String strcomplete = vars.getSessionValue("buttonE038F781230D4505AAADDE1EBFD2573E.strcomplete");
        String strProcessing = vars.getSessionValue("buttonE038F781230D4505AAADDE1EBFD2573E.strProcessing");
        String strOrg = vars.getSessionValue("buttonE038F781230D4505AAADDE1EBFD2573E.strOrg");
        String strClient = vars.getSessionValue("buttonE038F781230D4505AAADDE1EBFD2573E.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCompleteE038F781230D4505AAADDE1EBFD2573E(response, vars, strRcpl_Emppprocessmanual_ID, strcomplete, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONCompleteE038F781230D4505AAADDE1EBFD2573E")) {
        String strRcpl_Emppprocessmanual_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcpl_Emppprocessmanual_ID", "");
        
        ProcessBundle pb = new ProcessBundle("E038F781230D4505AAADDE1EBFD2573E", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcpl_Emppprocessmanual_ID", strRcpl_Emppprocessmanual_ID);
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
      String strPRcpl_Payrollprocessmanual_ID = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPRcpl_Payrollprocessmanual_ID);
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
  private AdditionsB479B97A7086432FB653AA751A94D59FData getEditVariables(Connection con, VariablesSecureApp vars, String strPRcpl_Payrollprocessmanual_ID) throws IOException,ServletException {
    AdditionsB479B97A7086432FB653AA751A94D59FData data = new AdditionsB479B97A7086432FB653AA751A94D59FData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rcplPayrollprocessmanualId = vars.getRequiredStringParameter("inprcplPayrollprocessmanualId");     data.rcplPayrollprocessmanualIdr = vars.getStringParameter("inprcplPayrollprocessmanualId_R");     data.rchrEmployeeId = vars.getStringParameter("inprchrEmployeeId");     data.rchrEmployeeIdr = vars.getStringParameter("inprchrEmployeeId_R");     data.attddate = vars.getRequiredStringParameter("inpattddate");     data.process = vars.getStringParameter("inpprocess", "N");     data.processed = vars.getStringParameter("inpprocessed", "N");     data.complete = vars.getStringParameter("inpcomplete");     data.rchrDailyattendId = vars.getStringParameter("inprchrDailyattendId");     data.daytype = vars.getRequiredStringParameter("inpdaytype");     data.daytyper = vars.getStringParameter("inpdaytype_R");     data.description = vars.getRequiredStringParameter("inpdescription");     data.otprocess = vars.getStringParameter("inpotprocess", "N");     data.manualpresents = vars.getStringParameter("inpmanualpresents", "N");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rcplEmppprocessmanualId = vars.getRequestGlobalVariable("inprcplEmppprocessmanualId", windowId + "|Rcpl_Emppprocessmanual_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.rcplPayrollprocessmanualId = vars.getGlobalVariable("inprcplPayrollprocessmanualId", windowId + "|Rcpl_Payrollprocessmanual_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPRcpl_Payrollprocessmanual_ID) throws IOException,ServletException {
      
      GrievencesE28D8318C8D249BDBAA06C584ECC32C2Data[] data = GrievencesE28D8318C8D249BDBAA06C584ECC32C2Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcpl_Payrollprocessmanual_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|Rcpl_Payrollprocessmanual_ID", data[0].rcplPayrollprocessmanualId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|Rcpl_Payrollprocessmanual_ID", strPRcpl_Payrollprocessmanual_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strRcpl_Emppprocessmanual_ID) throws ServletException {
    String strPRcpl_Payrollprocessmanual_ID = AdditionsB479B97A7086432FB653AA751A94D59FData.selectParentID(this, strRcpl_Emppprocessmanual_ID);
    if (strPRcpl_Payrollprocessmanual_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strRcpl_Emppprocessmanual_ID);
      throw new ServletException("Parent record not found");
    }
    return strPRcpl_Payrollprocessmanual_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|Rcpl_Emppprocessmanual_ID", data[0].getField("rcplEmppprocessmanualId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPRcpl_Payrollprocessmanual_ID) throws IOException,ServletException {
      AdditionsB479B97A7086432FB653AA751A94D59FData[] data = AdditionsB479B97A7086432FB653AA751A94D59FData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcpl_Payrollprocessmanual_ID, vars.getStringParameter("inprcplEmppprocessmanualId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPRcpl_Payrollprocessmanual_ID, String strRcpl_Emppprocessmanual_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDescription = vars.getSessionValue(tabId + "|paramDescription");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDescription)) || !(("").equals(strParamDescription) || ("%").equals(strParamDescription)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRcpl_Emppprocessmanual_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRcpl_Emppprocessmanual_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/Grievences/AdditionsB479B97A7086432FB653AA751A94D59F_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "AdditionsB479B97A7086432FB653AA751A94D59F", false, "document.frmMain.inprcplEmppprocessmanualId", "grid", "..", "".equals("Y"), "Grievences", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPRcpl_Payrollprocessmanual_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("0A43869A47B945288C09EFCB08F1A6D5", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "rcplEmppprocessmanualId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "AdditionsB479B97A7086432FB653AA751A94D59F_Relation.html", "Grievences", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "AdditionsB479B97A7086432FB653AA751A94D59F_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", AdditionsB479B97A7086432FB653AA751A94D59FData.selectParent(this, strPRcpl_Payrollprocessmanual_ID));
    else xmlDocument.setParameter("parent", AdditionsB479B97A7086432FB653AA751A94D59FData.selectParentTrl(this, strPRcpl_Payrollprocessmanual_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRcpl_Emppprocessmanual_ID, String strPRcpl_Payrollprocessmanual_ID, TableSQLData tableSQL)
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
    AdditionsB479B97A7086432FB653AA751A94D59FData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDescription = vars.getSessionValue(tabId + "|paramDescription");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDescription)) || !(("").equals(strParamDescription) || ("%").equals(strParamDescription)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = AdditionsB479B97A7086432FB653AA751A94D59FData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcpl_Payrollprocessmanual_ID, strRcpl_Emppprocessmanual_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRcpl_Emppprocessmanual_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new AdditionsB479B97A7086432FB653AA751A94D59FData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rcplEmppprocessmanualId") == null || dataField.getField("rcplEmppprocessmanualId").equals("")) {
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
        refreshSessionNew(vars, strPRcpl_Payrollprocessmanual_ID);
        data = AdditionsB479B97A7086432FB653AA751A94D59FData.set(strPRcpl_Payrollprocessmanual_ID, Utility.getDefault(this, vars, "Processed", "N", "BA00CD9835D0423193FAB5395377FA71", "N", dataField), Utility.getDefault(this, vars, "Complete", "N", "BA00CD9835D0423193FAB5395377FA71", "N", dataField), Utility.getDefault(this, vars, "Attddate", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField), "", Utility.getDefault(this, vars, "Description", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField), AdditionsB479B97A7086432FB653AA751A94D59FData.selectDef2B8100ADF9194ABDA03D1F8AF94512F6_0(this, Utility.getDefault(this, vars, "Updatedby", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField)), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "BA00CD9835D0423193FAB5395377FA71", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "BA00CD9835D0423193FAB5395377FA71", "", dataField), Utility.getDefault(this, vars, "Daytype", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField), Utility.getDefault(this, vars, "Process", "N", "BA00CD9835D0423193FAB5395377FA71", "N", dataField), Utility.getDefault(this, vars, "Otprocess", "N", "BA00CD9835D0423193FAB5395377FA71", "N", dataField), "Y", Utility.getDefault(this, vars, "Manualpresents", "N", "BA00CD9835D0423193FAB5395377FA71", "N", dataField), Utility.getDefault(this, vars, "Rchr_Dailyattend_ID", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField), AdditionsB479B97A7086432FB653AA751A94D59FData.selectDefE2FDF006E80940439BE56D69655CB054_1(this, Utility.getDefault(this, vars, "Createdby", "", "BA00CD9835D0423193FAB5395377FA71", "", dataField)));
        
      }
     }
      
    String currentPOrg=GrievencesE28D8318C8D249BDBAA06C584ECC32C2Data.selectOrg(this, strPRcpl_Payrollprocessmanual_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/Grievences/AdditionsB479B97A7086432FB653AA751A94D59F_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/Grievences/AdditionsB479B97A7086432FB653AA751A94D59F_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "AdditionsB479B97A7086432FB653AA751A94D59F", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprcplEmppprocessmanualId", "", "..", "".equals("Y"), "Grievences", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRcpl_Emppprocessmanual_ID), !hasReadOnlyAccess);
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
      // if (!strRcpl_Emppprocessmanual_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "AdditionsB479B97A7086432FB653AA751A94D59F_Relation.html", "Grievences", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "AdditionsB479B97A7086432FB653AA751A94D59F_Relation.html", strReplaceWith);
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
String userOrgList = "";
if (editableTab) 
  userOrgList= Utility.getReferenceableOrg(this, vars, currentPOrg, windowId, accesslevel); //referenceable from parent org, only the writeable orgs
else 
  userOrgList=currentOrg;
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rcpl_Payrollprocessmanual_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rcplPayrollprocessmanualId"):dataField.getField("rcplPayrollprocessmanualId")));
xmlDocument.setData("reportRcpl_Payrollprocessmanual_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Employee_ID", "", "8745C2D9710C46709C2DC8F27E738471", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmployeeId"):dataField.getField("rchrEmployeeId")));
xmlDocument.setData("reportRchr_Employee_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Attddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Complete_BTNname", Utility.getButtonName(this, vars, "A6964BCA3DA34986BA2D996BFFF757AF", "Complete_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalComplete = org.openbravo.erpCommon.utility.Utility.isModalProcess("E038F781230D4505AAADDE1EBFD2573E"); 
xmlDocument.setParameter("Complete_Modal", modalComplete?"true":"false");
comboTableData = new ComboTableData(vars, this, "17", "Daytype", "7ACBFA8FF0FA4862A49790DB5FC62EB7", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("daytype"):dataField.getField("daytype")));
xmlDocument.setData("reportDaytype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
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



    void printPageButtonCompleteE038F781230D4505AAADDE1EBFD2573E(HttpServletResponse response, VariablesSecureApp vars, String strRcpl_Emppprocessmanual_ID, String strcomplete, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E038F781230D4505AAADDE1EBFD2573E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/CompleteE038F781230D4505AAADDE1EBFD2573E", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcpl_Emppprocessmanual_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "AdditionsB479B97A7086432FB653AA751A94D59F_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "E038F781230D4505AAADDE1EBFD2573E");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("E038F781230D4505AAADDE1EBFD2573E");
        vars.removeMessage("E038F781230D4505AAADDE1EBFD2573E");
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPRcpl_Payrollprocessmanual_ID) throws IOException, ServletException {
    AdditionsB479B97A7086432FB653AA751A94D59FData data = null;
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
            data = getEditVariables(con, vars, strPRcpl_Payrollprocessmanual_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.rcplEmppprocessmanualId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (AdditionsB479B97A7086432FB653AA751A94D59FData.getCurrentDBTimestamp(this, data.rcplEmppprocessmanualId).equals(
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
                    data.rcplEmppprocessmanualId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rcpl_Emppprocessmanual_ID", data.rcplEmppprocessmanualId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet AdditionsB479B97A7086432FB653AA751A94D59F. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
