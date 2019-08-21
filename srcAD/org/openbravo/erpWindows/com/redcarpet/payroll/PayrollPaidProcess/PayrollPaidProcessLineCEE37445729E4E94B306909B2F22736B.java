
package org.openbravo.erpWindows.com.redcarpet.payroll.PayrollPaidProcess;




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

public class PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.class);
  
  private static final String windowId = "076697253ACB481C80A6D679BA0339A3";
  private static final String tabId = "CEE37445729E4E94B306909B2F22736B";
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
     
      if (command.contains("DEC765A7E71445ABB8C7DBDBFB735A13")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("DEC765A7E71445ABB8C7DBDBFB735A13");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("DEC765A7E71445ABB8C7DBDBFB735A13")) {
        classInfo.type = "P";
        classInfo.id = "DEC765A7E71445ABB8C7DBDBFB735A13";
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
        String strrcplPayrollpaidproclineId = request.getParameter("inprcplPayrollpaidproclineId");
         String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrcplPayrollpaidproclineId.equals(""))
              total = saveRecord(vars, myError, 'U', strPRcpl_Payrollpaidproc_ID);
          else
              total = saveRecord(vars, myError, 'I', strPRcpl_Payrollpaidproc_ID);
          
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
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID", "");

      String strRcpl_Payrollpaidprocline_ID = vars.getGlobalVariable("inprcplPayrollpaidproclineId", windowId + "|Rcpl_Payrollpaidprocline_ID", "");
            if (strPRcpl_Payrollpaidproc_ID.equals("")) {
        strPRcpl_Payrollpaidproc_ID = getParentID(vars, strRcpl_Payrollpaidprocline_ID);
        if (strPRcpl_Payrollpaidproc_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|Rcpl_Payrollpaidproc_ID");
        vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", strPRcpl_Payrollpaidproc_ID);

        refreshParentSession(vars, strPRcpl_Payrollpaidproc_ID);
      }


      String strView = vars.getSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRcpl_Payrollpaidprocline_ID.equals("")) strRcpl_Payrollpaidprocline_ID = firstElement(vars, tableSQL);
          if (strRcpl_Payrollpaidprocline_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcpl_Payrollpaidprocline_ID, strPRcpl_Payrollpaidproc_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRcpl_Payrollpaidproc_ID, strRcpl_Payrollpaidprocline_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRcpl_Payrollpaidprocline_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRcpl_Payrollpaidprocline_ID.equals("")) strRcpl_Payrollpaidprocline_ID = vars.getRequiredGlobalVariable("inprcplPayrollpaidproclineId", windowId + "|Rcpl_Payrollpaidprocline_ID");
      else vars.setSessionValue(windowId + "|Rcpl_Payrollpaidprocline_ID", strRcpl_Payrollpaidprocline_ID);
      
      
      String strPRcpl_Payrollpaidproc_ID = getParentID(vars, strRcpl_Payrollpaidprocline_ID);
      
      vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", strPRcpl_Payrollpaidproc_ID);
      vars.setSessionValue("25A01986250B46ECB0037C3DD4BF42A5|Payroll Paid Process.view", "EDIT");

      refreshParentSession(vars, strPRcpl_Payrollpaidproc_ID);

      vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.view", "EDIT");

      printPageEdit(response, request, vars, false, strRcpl_Payrollpaidprocline_ID, strPRcpl_Payrollpaidproc_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|Rcpl_Payrollpaidprocline_ID");
      refreshParentSession(vars, strPRcpl_Payrollpaidproc_ID);


      String strView = vars.getSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.view");
      String strRcpl_Payrollpaidprocline_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRcpl_Payrollpaidprocline_ID = firstElement(vars, tableSQL);
          if (strRcpl_Payrollpaidprocline_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRcpl_Payrollpaidprocline_ID.equals("")) strRcpl_Payrollpaidprocline_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRcpl_Payrollpaidprocline_ID, strPRcpl_Payrollpaidproc_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPRcpl_Payrollpaidproc_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamRchr_Employee_ID", tabId + "|paramRchr_Employee_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");

      
      vars.removeSessionValue(windowId + "|Rcpl_Payrollpaidprocline_ID");
      String strRcpl_Payrollpaidprocline_ID="";

      String strView = vars.getSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRcpl_Payrollpaidprocline_ID = firstElement(vars, tableSQL);
        if (strRcpl_Payrollpaidprocline_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcpl_Payrollpaidprocline_ID, strPRcpl_Payrollpaidproc_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRcpl_Payrollpaidproc_ID, strRcpl_Payrollpaidprocline_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      

      String strRcpl_Payrollpaidprocline_ID = vars.getGlobalVariable("inprcplPayrollpaidproclineId", windowId + "|Rcpl_Payrollpaidprocline_ID", "");
      vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.view", "RELATION");
      printPageDataSheet(response, vars, strPRcpl_Payrollpaidproc_ID, strRcpl_Payrollpaidprocline_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");


      printPageEdit(response, request, vars, true, "", strPRcpl_Payrollpaidproc_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRcpl_Payrollpaidprocline_ID = vars.getGlobalVariable("inprcplPayrollpaidproclineId", windowId + "|Rcpl_Payrollpaidprocline_ID", "");
      vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRcpl_Payrollpaidprocline_ID, strPRcpl_Payrollpaidproc_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      String strRcpl_Payrollpaidprocline_ID = vars.getRequiredStringParameter("inprcplPayrollpaidproclineId");
      
      String strNext = nextElement(vars, strRcpl_Payrollpaidprocline_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPRcpl_Payrollpaidproc_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      String strRcpl_Payrollpaidprocline_ID = vars.getRequiredStringParameter("inprcplPayrollpaidproclineId");
      
      String strPrevious = previousElement(vars, strRcpl_Payrollpaidprocline_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPRcpl_Payrollpaidproc_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");

      vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rcpl_Payrollpaidprocline_ID");
      vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", strPRcpl_Payrollpaidproc_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rcpl_Payrollpaidprocline_ID");
      vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", strPRcpl_Payrollpaidproc_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPRcpl_Payrollpaidproc_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPRcpl_Payrollpaidproc_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPRcpl_Payrollpaidproc_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPRcpl_Payrollpaidproc_ID);      
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
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      String strRcpl_Payrollpaidprocline_ID = vars.getRequiredGlobalVariable("inprcplPayrollpaidproclineId", windowId + "|Rcpl_Payrollpaidprocline_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPRcpl_Payrollpaidproc_ID);      
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
          String strNext = nextElement(vars, strRcpl_Payrollpaidprocline_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rcpl_Payrollpaidprocline_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");

      String strRcpl_Payrollpaidprocline_ID = vars.getRequiredStringParameter("inprcplPayrollpaidproclineId");
      //PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData data = getEditVariables(vars, strPRcpl_Payrollpaidproc_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.delete(this, strRcpl_Payrollpaidprocline_ID, strPRcpl_Payrollpaidproc_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rcplPayrollpaidproclineId");
        vars.setSessionValue(tabId + "|PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONPaidapprovalDEC765A7E71445ABB8C7DBDBFB735A13")) {
        vars.setSessionValue("buttonDEC765A7E71445ABB8C7DBDBFB735A13.strpaidapproval", vars.getStringParameter("inppaidapproval"));
        vars.setSessionValue("buttonDEC765A7E71445ABB8C7DBDBFB735A13.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonDEC765A7E71445ABB8C7DBDBFB735A13.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonDEC765A7E71445ABB8C7DBDBFB735A13.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonDEC765A7E71445ABB8C7DBDBFB735A13.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "DEC765A7E71445ABB8C7DBDBFB735A13", request.getServletPath());
      } else if (vars.commandIn("BUTTONDEC765A7E71445ABB8C7DBDBFB735A13")) {
        String strRcpl_Payrollpaidprocline_ID = vars.getGlobalVariable("inprcplPayrollpaidproclineId", windowId + "|Rcpl_Payrollpaidprocline_ID", "");
        String strpaidapproval = vars.getSessionValue("buttonDEC765A7E71445ABB8C7DBDBFB735A13.strpaidapproval");
        String strProcessing = vars.getSessionValue("buttonDEC765A7E71445ABB8C7DBDBFB735A13.strProcessing");
        String strOrg = vars.getSessionValue("buttonDEC765A7E71445ABB8C7DBDBFB735A13.strOrg");
        String strClient = vars.getSessionValue("buttonDEC765A7E71445ABB8C7DBDBFB735A13.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonPaidapprovalDEC765A7E71445ABB8C7DBDBFB735A13(response, vars, strRcpl_Payrollpaidprocline_ID, strpaidapproval, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONPaidapprovalDEC765A7E71445ABB8C7DBDBFB735A13")) {
        String strRcpl_Payrollpaidprocline_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcpl_Payrollpaidprocline_ID", "");
        
        ProcessBundle pb = new ProcessBundle("DEC765A7E71445ABB8C7DBDBFB735A13", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcpl_Payrollpaidprocline_ID", strRcpl_Payrollpaidprocline_ID);
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
      String strPRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPRcpl_Payrollpaidproc_ID);
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
  private PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData getEditVariables(Connection con, VariablesSecureApp vars, String strPRcpl_Payrollpaidproc_ID) throws IOException,ServletException {
    PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData data = new PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rcplPayrollpaidprocId = vars.getRequiredStringParameter("inprcplPayrollpaidprocId");     data.rchrEmployeeId = vars.getRequiredStringParameter("inprchrEmployeeId");     data.rchrEmployeeIdr = vars.getStringParameter("inprchrEmployeeId_R");     data.startdate = vars.getStringParameter("inpstartdate");     data.enddate = vars.getStringParameter("inpenddate");    try {   data.processingdays = vars.getRequiredNumericParameter("inpprocessingdays");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.additionsum = vars.getRequiredNumericParameter("inpadditionsum");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.deductionsum = vars.getRequiredNumericParameter("inpdeductionsum");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.incentiveaddsum = vars.getRequiredNumericParameter("inpincentiveaddsum");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.incentivededsum = vars.getRequiredNumericParameter("inpincentivededsum");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalpay = vars.getRequiredNumericParameter("inptotalpay");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.latededuction = vars.getRequiredNumericParameter("inplatededuction");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grandtotal = vars.getRequiredNumericParameter("inpgrandtotal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.presentdays = vars.getNumericParameter("inppresentdays");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weeklyoff = vars.getNumericParameter("inpweeklyoff");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.leaves = vars.getNumericParameter("inpleaves");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.absents = vars.getNumericParameter("inpabsents");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weekoffworked = vars.getNumericParameter("inpweekoffworked");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.basicamount = vars.getRequiredNumericParameter("inpbasicamount");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.servincentiveamount = vars.getRequiredNumericParameter("inpservincentiveamount");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrDesignationId = vars.getStringParameter("inprchrDesignationId");     data.rchrDesignationIdr = vars.getStringParameter("inprchrDesignationId_R");     data.ispfapplicable = vars.getStringParameter("inpispfapplicable", "N");     data.isbank = vars.getStringParameter("inpisbank", "N");     data.paiddate = vars.getStringParameter("inppaiddate");     data.paid = vars.getStringParameter("inppaid", "N");     data.adImageId = vars.getStringParameter("inpadImageId");     data.paidapproval = vars.getStringParameter("inppaidapproval");     data.rchrEmpDeptId = vars.getRequiredStringParameter("inprchrEmpDeptId");     data.rchrEmpDeptIdr = vars.getStringParameter("inprchrEmpDeptId_R");     data.rchrSubdepartmentId = vars.getRequiredStringParameter("inprchrSubdepartmentId");     data.rchrSubdepartmentIdr = vars.getStringParameter("inprchrSubdepartmentId_R");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rcplPayrollpaidproclineId = vars.getRequestGlobalVariable("inprcplPayrollpaidproclineId", windowId + "|Rcpl_Payrollpaidprocline_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.rcplPayrollpaidprocId = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPRcpl_Payrollpaidproc_ID) throws IOException,ServletException {
      
      PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data[] data = PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcpl_Payrollpaidproc_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", data[0].rcplPayrollpaidprocId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", strPRcpl_Payrollpaidproc_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strRcpl_Payrollpaidprocline_ID) throws ServletException {
    String strPRcpl_Payrollpaidproc_ID = PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.selectParentID(this, strRcpl_Payrollpaidprocline_ID);
    if (strPRcpl_Payrollpaidproc_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strRcpl_Payrollpaidprocline_ID);
      throw new ServletException("Parent record not found");
    }
    return strPRcpl_Payrollpaidproc_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rcpl_Payrollpaidprocline_ID", data[0].getField("rcplPayrollpaidproclineId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPRcpl_Payrollpaidproc_ID) throws IOException,ServletException {
      PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData[] data = PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcpl_Payrollpaidproc_ID, vars.getStringParameter("inprcplPayrollpaidproclineId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPRcpl_Payrollpaidproc_ID, String strRcpl_Payrollpaidprocline_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamRchr_Employee_ID = vars.getSessionValue(tabId + "|paramRchr_Employee_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Employee_ID)) || !(("").equals(strParamRchr_Employee_ID) || ("%").equals(strParamRchr_Employee_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRcpl_Payrollpaidprocline_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRcpl_Payrollpaidprocline_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/PayrollPaidProcess/PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B", false, "document.frmMain.inprcplPayrollpaidproclineId", "grid", "..", "".equals("Y"), "PayrollPaidProcess", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), true, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPRcpl_Payrollpaidproc_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("6B0160E073A449AF901127AD018FAD1B", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "rcplPayrollpaidproclineId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B_Relation.html", "PayrollPaidProcess", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.selectParent(this, strPRcpl_Payrollpaidproc_ID));
    else xmlDocument.setParameter("parent", PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.selectParentTrl(this, strPRcpl_Payrollpaidproc_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRcpl_Payrollpaidprocline_ID, String strPRcpl_Payrollpaidproc_ID, TableSQLData tableSQL)
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
    PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData[] data=null;
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
        data = PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcpl_Payrollpaidproc_ID, strRcpl_Payrollpaidprocline_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRcpl_Payrollpaidprocline_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (data==null || data.length==0) {
        strRcpl_Payrollpaidprocline_ID = firstElement(vars, tableSQL);
        if (strRcpl_Payrollpaidprocline_ID.equals("")) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        } else {
          data = PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcpl_Payrollpaidproc_ID, strRcpl_Payrollpaidprocline_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
        }
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rcplPayrollpaidproclineId") == null || dataField.getField("rcplPayrollpaidproclineId").equals("")) {
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
        refreshSessionNew(vars, strPRcpl_Payrollpaidproc_ID);
        data = PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.set(strPRcpl_Payrollpaidproc_ID, "", Utility.getDefault(this, vars, "Startdate", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Latededuction", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), Utility.getDefault(this, vars, "Additionsum", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), Utility.getDefault(this, vars, "Weekoffworked", "0", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Presentdays", "0", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Rchr_Designation_ID", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Enddate", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Paiddate", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Incentiveaddsum", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), Utility.getDefault(this, vars, "Servincentiveamount", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), Utility.getDefault(this, vars, "Paid", "N", "076697253ACB481C80A6D679BA0339A3", "N", dataField), Utility.getDefault(this, vars, "Grandtotal", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), Utility.getDefault(this, vars, "Rchr_Emp_Dept_ID", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Basicamount", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), Utility.getDefault(this, vars, "Incentivededsum", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Weeklyoff", "0", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Leaves", "0", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.selectDef91027BC36F254F118FAE087573CEAB57_0(this, Utility.getDefault(this, vars, "Createdby", "", "076697253ACB481C80A6D679BA0339A3", "", dataField)), Utility.getDefault(this, vars, "AD_Image_ID", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Totalpay", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), "Y", Utility.getDefault(this, vars, "Absents", "0", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Paidapproval", "N", "076697253ACB481C80A6D679BA0339A3", "N", dataField), Utility.getDefault(this, vars, "Updatedby", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.selectDefD79725A6BD62400896814BA634ED49D4_1(this, Utility.getDefault(this, vars, "Updatedby", "", "076697253ACB481C80A6D679BA0339A3", "", dataField)), Utility.getDefault(this, vars, "Rchr_Subdepartment_ID", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Deductionsum", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField), Utility.getDefault(this, vars, "Ispfapplicable", "N", "076697253ACB481C80A6D679BA0339A3", "N", dataField), Utility.getDefault(this, vars, "Isbank", "N", "076697253ACB481C80A6D679BA0339A3", "N", dataField), Utility.getDefault(this, vars, "Processingdays", "0", "076697253ACB481C80A6D679BA0339A3", "0", dataField));
        
      }
     }
      
    String currentPOrg=PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.selectOrg(this, strPRcpl_Payrollpaidproc_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/PayrollPaidProcess/PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/PayrollPaidProcess/PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprcplPayrollpaidproclineId", "", "..", "".equals("Y"), "PayrollPaidProcess", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRcpl_Payrollpaidprocline_ID), !hasReadOnlyAccess);
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
      // if (!strRcpl_Payrollpaidprocline_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B_Relation.html", "PayrollPaidProcess", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B_Relation.html", strReplaceWith);
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
xmlDocument.setParameter("Startdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Enddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonAdditionsum", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDeductionsum", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonIncentiveaddsum", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonIncentivededsum", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalpay", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonLatededuction", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGrandtotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonBasicamount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonServincentiveamount", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Designation_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrDesignationId"):dataField.getField("rchrDesignationId")));
xmlDocument.setData("reportRchr_Designation_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Paiddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
String strCurrentImageURLAD_Image_ID = (dataField==null?data[0].getField("adImageId"):dataField.getField("adImageId"));
if (strCurrentImageURLAD_Image_ID==null || strCurrentImageURLAD_Image_ID.equals("")){
  xmlDocument.setParameter("AD_Image_IDClass", "Image_NotAvailable_medium");
}

xmlDocument.setParameter("Paidapproval_BTNname", Utility.getButtonName(this, vars, "65666A5AAFBA45438524065D1151D9BC", "Paidapproval_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPaidapproval = org.openbravo.erpCommon.utility.Utility.isModalProcess("DEC765A7E71445ABB8C7DBDBFB735A13"); 
xmlDocument.setParameter("Paidapproval_Modal", modalPaidapproval?"true":"false");
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Emp_Dept_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmpDeptId"):dataField.getField("rchrEmpDeptId")));
xmlDocument.setData("reportRchr_Emp_Dept_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Subdepartment_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrSubdepartmentId"):dataField.getField("rchrSubdepartmentId")));
xmlDocument.setData("reportRchr_Subdepartment_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
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



    void printPageButtonPaidapprovalDEC765A7E71445ABB8C7DBDBFB735A13(HttpServletResponse response, VariablesSecureApp vars, String strRcpl_Payrollpaidprocline_ID, String strpaidapproval, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DEC765A7E71445ABB8C7DBDBFB735A13");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/PaidapprovalDEC765A7E71445ABB8C7DBDBFB735A13", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcpl_Payrollpaidprocline_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "DEC765A7E71445ABB8C7DBDBFB735A13");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("DEC765A7E71445ABB8C7DBDBFB735A13");
        vars.removeMessage("DEC765A7E71445ABB8C7DBDBFB735A13");
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPRcpl_Payrollpaidproc_ID) throws IOException, ServletException {
    PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData data = null;
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
            data = getEditVariables(con, vars, strPRcpl_Payrollpaidproc_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.rcplPayrollpaidproclineId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736BData.getCurrentDBTimestamp(this, data.rcplPayrollpaidproclineId).equals(
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
                    data.rcplPayrollpaidproclineId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rcpl_Payrollpaidprocline_ID", data.rcplPayrollpaidproclineId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet PayrollPaidProcessLineCEE37445729E4E94B306909B2F22736B. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
