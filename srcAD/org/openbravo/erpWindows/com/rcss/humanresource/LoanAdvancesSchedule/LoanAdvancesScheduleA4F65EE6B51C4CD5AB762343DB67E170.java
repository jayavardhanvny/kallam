
package org.openbravo.erpWindows.com.rcss.humanresource.LoanAdvancesSchedule;




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

public class LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.class);
  
  private static final String windowId = "0E0E2F9F4AE845EBB6C85786C771021D";
  private static final String tabId = "A4F65EE6B51C4CD5AB762343DB67E170";
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
     
      if (command.contains("977F0ED7770440D880A48ECBA456706D")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("977F0ED7770440D880A48ECBA456706D");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      if (command.contains("8F8FAACEEF9D449C909758519FBD0760")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("8F8FAACEEF9D449C909758519FBD0760");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      if (command.contains("B76A77639E4B4DAAB097AD11DAE7A1AA")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("B76A77639E4B4DAAB097AD11DAE7A1AA");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("977F0ED7770440D880A48ECBA456706D")) {
        classInfo.type = "P";
        classInfo.id = "977F0ED7770440D880A48ECBA456706D";
      }
     
      if (securedProcess && command.contains("8F8FAACEEF9D449C909758519FBD0760")) {
        classInfo.type = "P";
        classInfo.id = "8F8FAACEEF9D449C909758519FBD0760";
      }
     
      if (securedProcess && command.contains("B76A77639E4B4DAAB097AD11DAE7A1AA")) {
        classInfo.type = "P";
        classInfo.id = "B76A77639E4B4DAAB097AD11DAE7A1AA";
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
        String strrchrEmpLoanId = request.getParameter("inprchrEmpLoanId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrEmpLoanId.equals(""))
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

      String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Emp_Loan_ID.equals("")) strRchr_Emp_Loan_ID = firstElement(vars, tableSQL);
          if (strRchr_Emp_Loan_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Emp_Loan_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Emp_Loan_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Emp_Loan_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Emp_Loan_ID.equals("")) strRchr_Emp_Loan_ID = vars.getRequiredGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID");
      else vars.setSessionValue(windowId + "|Rchr_Emp_Loan_ID", strRchr_Emp_Loan_ID);
      
      vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Emp_Loan_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.view");
      String strRchr_Emp_Loan_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Emp_Loan_ID = firstElement(vars, tableSQL);
          if (strRchr_Emp_Loan_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Emp_Loan_ID.equals("")) strRchr_Emp_Loan_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Emp_Loan_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentno", tabId + "|paramDocumentno");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rchr_Emp_Loan_ID");
      String strRchr_Emp_Loan_ID="";

      String strView = vars.getSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Emp_Loan_ID = firstElement(vars, tableSQL);
        if (strRchr_Emp_Loan_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Emp_Loan_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Emp_Loan_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID", "");
      vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.view", "RELATION");
      printPageDataSheet(response, vars, strRchr_Emp_Loan_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID", "");
      vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Emp_Loan_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRchr_Emp_Loan_ID = vars.getRequiredStringParameter("inprchrEmpLoanId");
      
      String strNext = nextElement(vars, strRchr_Emp_Loan_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRchr_Emp_Loan_ID = vars.getRequiredStringParameter("inprchrEmpLoanId");
      
      String strPrevious = previousElement(vars, strRchr_Emp_Loan_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Emp_Loan_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Emp_Loan_ID");

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

      String strRchr_Emp_Loan_ID = vars.getRequiredGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID");
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
          String strNext = nextElement(vars, strRchr_Emp_Loan_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Emp_Loan_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRchr_Emp_Loan_ID = vars.getRequiredStringParameter("inprchrEmpLoanId");
      //LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data.delete(this, strRchr_Emp_Loan_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrEmpLoanId");
        vars.setSessionValue(tabId + "|LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONProcess977F0ED7770440D880A48ECBA456706D")) {
        vars.setSessionValue("button977F0ED7770440D880A48ECBA456706D.strprocess", vars.getStringParameter("inpprocess"));
        vars.setSessionValue("button977F0ED7770440D880A48ECBA456706D.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button977F0ED7770440D880A48ECBA456706D.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button977F0ED7770440D880A48ECBA456706D.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button977F0ED7770440D880A48ECBA456706D.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "977F0ED7770440D880A48ECBA456706D", request.getServletPath());
      } else if (vars.commandIn("BUTTON977F0ED7770440D880A48ECBA456706D")) {
        String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID", "");
        String strprocess = vars.getSessionValue("button977F0ED7770440D880A48ECBA456706D.strprocess");
        String strProcessing = vars.getSessionValue("button977F0ED7770440D880A48ECBA456706D.strProcessing");
        String strOrg = vars.getSessionValue("button977F0ED7770440D880A48ECBA456706D.strOrg");
        String strClient = vars.getSessionValue("button977F0ED7770440D880A48ECBA456706D.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcess977F0ED7770440D880A48ECBA456706D(response, vars, strRchr_Emp_Loan_ID, strprocess, strProcessing);
        }
    } else if (vars.commandIn("BUTTONApprove8F8FAACEEF9D449C909758519FBD0760")) {
        vars.setSessionValue("button8F8FAACEEF9D449C909758519FBD0760.strapprove", vars.getStringParameter("inpapprove"));
        vars.setSessionValue("button8F8FAACEEF9D449C909758519FBD0760.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button8F8FAACEEF9D449C909758519FBD0760.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button8F8FAACEEF9D449C909758519FBD0760.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button8F8FAACEEF9D449C909758519FBD0760.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "8F8FAACEEF9D449C909758519FBD0760", request.getServletPath());
      } else if (vars.commandIn("BUTTON8F8FAACEEF9D449C909758519FBD0760")) {
        String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID", "");
        String strapprove = vars.getSessionValue("button8F8FAACEEF9D449C909758519FBD0760.strapprove");
        String strProcessing = vars.getSessionValue("button8F8FAACEEF9D449C909758519FBD0760.strProcessing");
        String strOrg = vars.getSessionValue("button8F8FAACEEF9D449C909758519FBD0760.strOrg");
        String strClient = vars.getSessionValue("button8F8FAACEEF9D449C909758519FBD0760.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonApprove8F8FAACEEF9D449C909758519FBD0760(response, vars, strRchr_Emp_Loan_ID, strapprove, strProcessing);
        }
    } else if (vars.commandIn("BUTTONPaidB76A77639E4B4DAAB097AD11DAE7A1AA")) {
        vars.setSessionValue("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.strpaid", vars.getStringParameter("inppaid"));
        vars.setSessionValue("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "B76A77639E4B4DAAB097AD11DAE7A1AA", request.getServletPath());
      } else if (vars.commandIn("BUTTONB76A77639E4B4DAAB097AD11DAE7A1AA")) {
        String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID", "");
        String strpaid = vars.getSessionValue("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.strpaid");
        String strProcessing = vars.getSessionValue("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.strProcessing");
        String strOrg = vars.getSessionValue("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.strOrg");
        String strClient = vars.getSessionValue("buttonB76A77639E4B4DAAB097AD11DAE7A1AA.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonPaidB76A77639E4B4DAAB097AD11DAE7A1AA(response, vars, strRchr_Emp_Loan_ID, strpaid, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcess977F0ED7770440D880A48ECBA456706D")) {
        String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Emp_Loan_ID", "");
        
        ProcessBundle pb = new ProcessBundle("977F0ED7770440D880A48ECBA456706D", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Emp_Loan_ID", strRchr_Emp_Loan_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONApprove8F8FAACEEF9D449C909758519FBD0760")) {
        String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Emp_Loan_ID", "");
        
        ProcessBundle pb = new ProcessBundle("8F8FAACEEF9D449C909758519FBD0760", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Emp_Loan_ID", strRchr_Emp_Loan_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONPaidB76A77639E4B4DAAB097AD11DAE7A1AA")) {
        String strRchr_Emp_Loan_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Emp_Loan_ID", "");
        
        ProcessBundle pb = new ProcessBundle("B76A77639E4B4DAAB097AD11DAE7A1AA", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Emp_Loan_ID", strRchr_Emp_Loan_ID);
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
  private LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data data = new LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rchrLoanTypeId = vars.getRequiredStringParameter("inprchrLoanTypeId");     data.rchrLoanTypeIdr = vars.getStringParameter("inprchrLoanTypeId_R");     data.rchrHomeapplianceId = vars.getStringParameter("inprchrHomeapplianceId");     data.rchrHomeapplianceIdr = vars.getStringParameter("inprchrHomeapplianceId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.rchrAttdProcessId = vars.getRequiredStringParameter("inprchrAttdProcessId");     data.rchrAttdProcessIdr = vars.getStringParameter("inprchrAttdProcessId_R");     data.rchrEmployeeId = vars.getStringParameter("inprchrEmployeeId");     data.rchrEmployeeIdr = vars.getStringParameter("inprchrEmployeeId_R");     data.suretyempid = vars.getStringParameter("inpsuretyempid");     data.requisitiondate = vars.getStringParameter("inprequisitiondate");     data.startdate = vars.getRequiredStringParameter("inpstartdate");     data.enddate = vars.getStringParameter("inpenddate");    try {   data.loanamt = vars.getRequiredNumericParameter("inploanamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.tenuremonths = vars.getRequiredNumericParameter("inptenuremonths");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.noofpresents = vars.getNumericParameter("inpnoofpresents");  } catch (ServletException paramEx) { ex = paramEx; }     data.process = vars.getRequiredStringParameter("inpprocess");    try {   data.pendingadvances = vars.getNumericParameter("inppendingadvances");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.departmentstore = vars.getNumericParameter("inpdepartmentstore");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.loanpendings = vars.getNumericParameter("inploanpendings");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.messbill = vars.getNumericParameter("inpmessbill");  } catch (ServletException paramEx) { ex = paramEx; }     data.processing = vars.getStringParameter("inpprocessing", "N");     data.status = vars.getStringParameter("inpstatus");     data.statusr = vars.getStringParameter("inpstatus_R");     data.approve = vars.getStringParameter("inpapprove");     data.adImageId = vars.getStringParameter("inpadImageId");     data.issued = vars.getStringParameter("inpissued");     data.paiddate = vars.getStringParameter("inppaiddate");     data.paid = vars.getStringParameter("inppaid");     data.rchrSaladvanceId = vars.getStringParameter("inprchrSaladvanceId");     data.rchrSaladvanceIdr = vars.getStringParameter("inprchrSaladvanceId_R");     data.isremark = vars.getStringParameter("inpisremark", "N");     data.description = vars.getRequiredStringParameter("inpdescription");     data.rchrEmpDeptId = vars.getStringParameter("inprchrEmpDeptId");     data.rchrEmpDeptIdr = vars.getStringParameter("inprchrEmpDeptId_R");     data.rchrDesignationId = vars.getStringParameter("inprchrDesignationId");     data.rchrDesignationIdr = vars.getStringParameter("inprchrDesignationId_R");     data.reject = vars.getStringParameter("inpreject");     data.rchrEmpLoanId = vars.getRequestGlobalVariable("inprchrEmpLoanId", windowId + "|Rchr_Emp_Loan_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|Rchr_Emp_Loan_ID", data[0].getField("rchrEmpLoanId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data[] data = LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprchrEmpLoanId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Emp_Loan_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Emp_Loan_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Emp_Loan_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LoanAdvancesSchedule/LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170", false, "document.frmMain.inprchrEmpLoanId", "grid", "../com.rcss.humanresource/erpCommon/ad_reports/ReportLoanRequisition.pdf", "N".equals("Y"), "LoanAdvancesSchedule", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rchrEmpLoanId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Relation.html", "LoanAdvancesSchedule", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Emp_Loan_ID, TableSQLData tableSQL)
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
    LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Emp_Loan_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Emp_Loan_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrEmpLoanId") == null || dataField.getField("rchrEmpLoanId").equals("")) {
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
        data = LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data.set(Utility.getDefault(this, vars, "Description", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Rchr_Saladvance_ID", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Issued", "", "0E0E2F9F4AE845EBB6C85786C771021D", "N", dataField), "", Utility.getDefault(this, vars, "Rchr_Designation_ID", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Loanamt", "@DefaultValue@!=0", "0E0E2F9F4AE845EBB6C85786C771021D", "0", dataField), Utility.getDefault(this, vars, "Documentno", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Rchr_Attd_Process_ID", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Rchr_Loan_Type_ID", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Approve", "N", "0E0E2F9F4AE845EBB6C85786C771021D", "N", dataField), Utility.getDefault(this, vars, "Tenuremonths", "", "0E0E2F9F4AE845EBB6C85786C771021D", "0", dataField), Utility.getDefault(this, vars, "Noofpresents", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Messbill", "0", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Requisitiondate", "@#Date@", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Paid", "N", "0E0E2F9F4AE845EBB6C85786C771021D", "N", dataField), Utility.getDefault(this, vars, "Pendingadvances", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Paiddate", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Process", "N", "0E0E2F9F4AE845EBB6C85786C771021D", "N", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Suretyempid", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Departmentstore", "0", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Processing", "", "0E0E2F9F4AE845EBB6C85786C771021D", "N", dataField), Utility.getDefault(this, vars, "Createdby", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data.selectDefB05F1DCF078640C7A61F971D9DE790E1_0(this, Utility.getDefault(this, vars, "Createdby", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField)), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "AD_Image_ID", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Isremark", "N", "0E0E2F9F4AE845EBB6C85786C771021D", "N", dataField), Utility.getDefault(this, vars, "Rchr_Homeappliance_ID", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Reject", "N", "0E0E2F9F4AE845EBB6C85786C771021D", "N", dataField), Utility.getDefault(this, vars, "Updatedby", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data.selectDefC6534633841540B8B6939133DE9B1B1C_1(this, Utility.getDefault(this, vars, "Updatedby", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField)), Utility.getDefault(this, vars, "Enddate", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Loanpendings", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Rchr_Emp_Dept_ID", "", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), "Y", Utility.getDefault(this, vars, "Startdate", "@#Date@", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField), Utility.getDefault(this, vars, "Status", "Draft", "0E0E2F9F4AE845EBB6C85786C771021D", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LoanAdvancesSchedule/LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LoanAdvancesSchedule/LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrEmpLoanId", "", "../com.rcss.humanresource/erpCommon/ad_reports/ReportLoanRequisition.pdf", "N".equals("Y"), "LoanAdvancesSchedule", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Emp_Loan_ID), !hasReadOnlyAccess);
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
      // if (!strRchr_Emp_Loan_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Relation.html", "LoanAdvancesSchedule", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Loan_Type_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrLoanTypeId"):dataField.getField("rchrLoanTypeId")));
xmlDocument.setData("reportRchr_Loan_Type_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Homeappliance_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrHomeapplianceId"):dataField.getField("rchrHomeapplianceId")));
xmlDocument.setData("reportRchr_Homeappliance_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Attd_Process_ID", "", "52CE5E3099A9436CB7856522A1AB0E3B", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrAttdProcessId"):dataField.getField("rchrAttdProcessId")));
xmlDocument.setData("reportRchr_Attd_Process_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Employee_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmployeeId"):dataField.getField("rchrEmployeeId")));
xmlDocument.setData("reportRchr_Employee_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Requisitiondate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Startdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Enddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonLoanamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTenuremonths", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonNoofpresents", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Process_BTNname", Utility.getButtonName(this, vars, "D90F422E85ED47B9ADD36CFE186D0A08", "Process_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcess = org.openbravo.erpCommon.utility.Utility.isModalProcess("977F0ED7770440D880A48ECBA456706D"); 
xmlDocument.setParameter("Process_Modal", modalProcess?"true":"false");
xmlDocument.setParameter("buttonPendingadvances", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonLoanpendings", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDepartmentstore", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonMessbill", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "17", "Status", "5901BE65AE974FC983073C6726BFC55D", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("status"):dataField.getField("status")));
xmlDocument.setData("reportStatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Approve_BTNname", Utility.getButtonName(this, vars, "2F5408B911F4447CBD119A338217AE9F", "Approve_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalApprove = org.openbravo.erpCommon.utility.Utility.isModalProcess("8F8FAACEEF9D449C909758519FBD0760"); 
xmlDocument.setParameter("Approve_Modal", modalApprove?"true":"false");
String strCurrentImageURLAD_Image_ID = (dataField==null?data[0].getField("adImageId"):dataField.getField("adImageId"));
if (strCurrentImageURLAD_Image_ID==null || strCurrentImageURLAD_Image_ID.equals("")){
  xmlDocument.setParameter("AD_Image_IDClass", "Image_NotAvailable_medium");
}

xmlDocument.setParameter("Paiddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Paid_BTNname", Utility.getButtonName(this, vars, "EF14AA3E2276424198291307F7874F83", "Paid_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPaid = org.openbravo.erpCommon.utility.Utility.isModalProcess("B76A77639E4B4DAAB097AD11DAE7A1AA"); 
xmlDocument.setParameter("Paid_Modal", modalPaid?"true":"false");
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Saladvance_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrSaladvanceId"):dataField.getField("rchrSaladvanceId")));
xmlDocument.setData("reportRchr_Saladvance_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Emp_Dept_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmpDeptId"):dataField.getField("rchrEmpDeptId")));
xmlDocument.setData("reportRchr_Emp_Dept_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Designation_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrDesignationId"):dataField.getField("rchrDesignationId")));
xmlDocument.setData("reportRchr_Designation_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Reject_BTNname", Utility.getButtonName(this, vars, "37A857C9A1E74E59BD16544083EC29DD", "Reject_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalReject = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
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



    void printPageButtonProcess977F0ED7770440D880A48ECBA456706D(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Emp_Loan_ID, String strprocess, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 977F0ED7770440D880A48ECBA456706D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Process977F0ED7770440D880A48ECBA456706D", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Emp_Loan_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "977F0ED7770440D880A48ECBA456706D");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("977F0ED7770440D880A48ECBA456706D");
        vars.removeMessage("977F0ED7770440D880A48ECBA456706D");
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
    void printPageButtonApprove8F8FAACEEF9D449C909758519FBD0760(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Emp_Loan_ID, String strapprove, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8F8FAACEEF9D449C909758519FBD0760");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Approve8F8FAACEEF9D449C909758519FBD0760", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Emp_Loan_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "8F8FAACEEF9D449C909758519FBD0760");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("8F8FAACEEF9D449C909758519FBD0760");
        vars.removeMessage("8F8FAACEEF9D449C909758519FBD0760");
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
    void printPageButtonPaidB76A77639E4B4DAAB097AD11DAE7A1AA(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Emp_Loan_ID, String strpaid, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B76A77639E4B4DAAB097AD11DAE7A1AA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/PaidB76A77639E4B4DAAB097AD11DAE7A1AA", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Emp_Loan_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "B76A77639E4B4DAAB097AD11DAE7A1AA");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("B76A77639E4B4DAAB097AD11DAE7A1AA");
        vars.removeMessage("B76A77639E4B4DAAB097AD11DAE7A1AA");
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
      String result = "var strAD_User_ID=\"" +Utility.getContext(this, vars, "AD_User_ID", windowId) + "\";\nvar strad_user_id=\"" +Utility.getContext(this, vars, "#ad_user_id", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
      return result;
    }


    private String getReadOnlyLogicContext(VariablesSecureApp vars) throws IOException, ServletException {
      log4j.debug("Output: Read Only logic context fields");
      String result = "var strad_user_id=\"" + Utility.getContext(this, vars, "ad_user_id", windowId) + "\";\n";
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
    LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data data = null;
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
                data.rchrEmpLoanId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170Data.getCurrentDBTimestamp(this, data.rchrEmpLoanId).equals(
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
                    data.rchrEmpLoanId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Emp_Loan_ID", data.rchrEmpLoanId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet LoanAdvancesScheduleA4F65EE6B51C4CD5AB762343DB67E170. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
