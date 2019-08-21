
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

public class PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.class);
  
  private static final String windowId = "076697253ACB481C80A6D679BA0339A3";
  private static final String tabId = "25A01986250B46ECB0037C3DD4BF42A5";
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
     
      if (command.contains("8E00D1574F384B4A9D95F3ED022F0A47")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("8E00D1574F384B4A9D95F3ED022F0A47");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("8E00D1574F384B4A9D95F3ED022F0A47")) {
        classInfo.type = "P";
        classInfo.id = "8E00D1574F384B4A9D95F3ED022F0A47";
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
        String strrcplPayrollpaidprocId = request.getParameter("inprcplPayrollpaidprocId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrcplPayrollpaidprocId.equals(""))
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

      String strRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRcpl_Payrollpaidproc_ID.equals("")) strRcpl_Payrollpaidproc_ID = firstElement(vars, tableSQL);
          if (strRcpl_Payrollpaidproc_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcpl_Payrollpaidproc_ID, tableSQL);

      else printPageDataSheet(response, vars, strRcpl_Payrollpaidproc_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRcpl_Payrollpaidproc_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRcpl_Payrollpaidproc_ID.equals("")) strRcpl_Payrollpaidproc_ID = vars.getRequiredGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
      else vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", strRcpl_Payrollpaidproc_ID);
      
      vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.view", "EDIT");

      printPageEdit(response, request, vars, false, strRcpl_Payrollpaidproc_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.view");
      String strRcpl_Payrollpaidproc_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRcpl_Payrollpaidproc_ID = firstElement(vars, tableSQL);
          if (strRcpl_Payrollpaidproc_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRcpl_Payrollpaidproc_ID.equals("")) strRcpl_Payrollpaidproc_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRcpl_Payrollpaidproc_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamRchr_Attd_Process_ID", tabId + "|paramRchr_Attd_Process_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID");
      String strRcpl_Payrollpaidproc_ID="";

      String strView = vars.getSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRcpl_Payrollpaidproc_ID = firstElement(vars, tableSQL);
        if (strRcpl_Payrollpaidproc_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcpl_Payrollpaidproc_ID, tableSQL);

      else printPageDataSheet(response, vars, strRcpl_Payrollpaidproc_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID", "");
      vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.view", "RELATION");
      printPageDataSheet(response, vars, strRcpl_Payrollpaidproc_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID", "");
      vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRcpl_Payrollpaidproc_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRcpl_Payrollpaidproc_ID = vars.getRequiredStringParameter("inprcplPayrollpaidprocId");
      
      String strNext = nextElement(vars, strRcpl_Payrollpaidproc_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRcpl_Payrollpaidproc_ID = vars.getRequiredStringParameter("inprcplPayrollpaidprocId");
      
      String strPrevious = previousElement(vars, strRcpl_Payrollpaidproc_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID");

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

      String strRcpl_Payrollpaidproc_ID = vars.getRequiredGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");
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
          String strNext = nextElement(vars, strRcpl_Payrollpaidproc_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRcpl_Payrollpaidproc_ID = vars.getRequiredStringParameter("inprcplPayrollpaidprocId");
      //PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.delete(this, strRcpl_Payrollpaidproc_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rcplPayrollpaidprocId");
        vars.setSessionValue(tabId + "|PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONAtpayrollperiod8E00D1574F384B4A9D95F3ED022F0A47")) {
        vars.setSessionValue("button8E00D1574F384B4A9D95F3ED022F0A47.stratpayrollperiod", vars.getStringParameter("inpatpayrollperiod"));
        vars.setSessionValue("button8E00D1574F384B4A9D95F3ED022F0A47.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button8E00D1574F384B4A9D95F3ED022F0A47.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button8E00D1574F384B4A9D95F3ED022F0A47.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button8E00D1574F384B4A9D95F3ED022F0A47.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "8E00D1574F384B4A9D95F3ED022F0A47", request.getServletPath());
      } else if (vars.commandIn("BUTTON8E00D1574F384B4A9D95F3ED022F0A47")) {
        String strRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID", "");
        String stratpayrollperiod = vars.getSessionValue("button8E00D1574F384B4A9D95F3ED022F0A47.stratpayrollperiod");
        String strProcessing = vars.getSessionValue("button8E00D1574F384B4A9D95F3ED022F0A47.strProcessing");
        String strOrg = vars.getSessionValue("button8E00D1574F384B4A9D95F3ED022F0A47.strOrg");
        String strClient = vars.getSessionValue("button8E00D1574F384B4A9D95F3ED022F0A47.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonAtpayrollperiod8E00D1574F384B4A9D95F3ED022F0A47(response, vars, strRcpl_Payrollpaidproc_ID, stratpayrollperiod, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONAtpayrollperiod8E00D1574F384B4A9D95F3ED022F0A47")) {
        String strRcpl_Payrollpaidproc_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcpl_Payrollpaidproc_ID", "");
        
        ProcessBundle pb = new ProcessBundle("8E00D1574F384B4A9D95F3ED022F0A47", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcpl_Payrollpaidproc_ID", strRcpl_Payrollpaidproc_ID);
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
  private PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data data = new PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rchrAttdProcessId = vars.getRequiredStringParameter("inprchrAttdProcessId");     data.rchrAttdProcessIdr = vars.getStringParameter("inprchrAttdProcessId_R");     data.employeetype = vars.getRequiredStringParameter("inpemployeetype");     data.employeetyper = vars.getStringParameter("inpemployeetype_R");     data.atpayrollperiod = vars.getRequiredStringParameter("inpatpayrollperiod");     data.byhand = vars.getStringParameter("inpbyhand", "N");     data.rcplPayrollpaidprocId = vars.getRequestGlobalVariable("inprcplPayrollpaidprocId", windowId + "|Rcpl_Payrollpaidproc_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", data[0].getField("rcplPayrollpaidprocId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data[] data = PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprcplPayrollpaidprocId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRcpl_Payrollpaidproc_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamRchr_Attd_Process_ID = vars.getSessionValue(tabId + "|paramRchr_Attd_Process_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Attd_Process_ID)) || !(("").equals(strParamRchr_Attd_Process_ID) || ("%").equals(strParamRchr_Attd_Process_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRcpl_Payrollpaidproc_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRcpl_Payrollpaidproc_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/PayrollPaidProcess/PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5", false, "document.frmMain.inprcplPayrollpaidprocId", "grid", "..", "".equals("Y"), "PayrollPaidProcess", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rcplPayrollpaidprocId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5_Relation.html", "PayrollPaidProcess", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRcpl_Payrollpaidproc_ID, TableSQLData tableSQL)
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
    PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamRchr_Attd_Process_ID = vars.getSessionValue(tabId + "|paramRchr_Attd_Process_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Attd_Process_ID)) || !(("").equals(strParamRchr_Attd_Process_ID) || ("%").equals(strParamRchr_Attd_Process_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRcpl_Payrollpaidproc_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRcpl_Payrollpaidproc_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rcplPayrollpaidprocId") == null || dataField.getField("rcplPayrollpaidprocId").equals("")) {
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
        data = PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.set("", Utility.getDefault(this, vars, "Atpayrollperiod", "N", "076697253ACB481C80A6D679BA0339A3", "N", dataField), "Y", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Employeetype", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Byhand", "Y", "076697253ACB481C80A6D679BA0339A3", "N", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Rchr_Attd_Process_ID", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.selectDefF6E55CD245FA4D30B6B0553E8FE436D5_0(this, Utility.getDefault(this, vars, "Updatedby", "", "076697253ACB481C80A6D679BA0339A3", "", dataField)), Utility.getDefault(this, vars, "Createdby", "", "076697253ACB481C80A6D679BA0339A3", "", dataField), PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.selectDefF89332CF5D2144919A63B1D02618F5C6_1(this, Utility.getDefault(this, vars, "Createdby", "", "076697253ACB481C80A6D679BA0339A3", "", dataField)));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/PayrollPaidProcess/PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/payroll/PayrollPaidProcess/PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprcplPayrollpaidprocId", "", "..", "".equals("Y"), "PayrollPaidProcess", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRcpl_Payrollpaidproc_ID), !hasReadOnlyAccess);
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
      // if (!strRcpl_Payrollpaidproc_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5_Relation.html", "PayrollPaidProcess", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Attd_Process_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrAttdProcessId"):dataField.getField("rchrAttdProcessId")));
xmlDocument.setData("reportRchr_Attd_Process_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Employeetype", "60AC4DA6E1D54452BDC42FF3387DC68C", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("employeetype"):dataField.getField("employeetype")));
xmlDocument.setData("reportEmployeetype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Atpayrollperiod_BTNname", Utility.getButtonName(this, vars, "A259C6A9EEAE43AB94F0BC632F0A00AA", "Atpayrollperiod_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalAtpayrollperiod = org.openbravo.erpCommon.utility.Utility.isModalProcess("8E00D1574F384B4A9D95F3ED022F0A47"); 
xmlDocument.setParameter("Atpayrollperiod_Modal", modalAtpayrollperiod?"true":"false");
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



    void printPageButtonAtpayrollperiod8E00D1574F384B4A9D95F3ED022F0A47(HttpServletResponse response, VariablesSecureApp vars, String strRcpl_Payrollpaidproc_ID, String stratpayrollperiod, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8E00D1574F384B4A9D95F3ED022F0A47");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Atpayrollperiod8E00D1574F384B4A9D95F3ED022F0A47", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcpl_Payrollpaidproc_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "8E00D1574F384B4A9D95F3ED022F0A47");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("8E00D1574F384B4A9D95F3ED022F0A47");
        vars.removeMessage("8E00D1574F384B4A9D95F3ED022F0A47");
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
    PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data data = null;
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
                data.rcplPayrollpaidprocId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5Data.getCurrentDBTimestamp(this, data.rcplPayrollpaidprocId).equals(
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
                    data.rcplPayrollpaidprocId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rcpl_Payrollpaidproc_ID", data.rcplPayrollpaidprocId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet PayrollPaidProcess25A01986250B46ECB0037C3DD4BF42A5. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
