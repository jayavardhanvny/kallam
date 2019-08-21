
package org.openbravo.erpWindows.FinancialAccount;


import org.openbravo.erpCommon.reference.*;


import org.openbravo.erpCommon.ad_actionButton.*;


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

public class ReconciliationsFF8080813320657F0133209DE21B0042 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(ReconciliationsFF8080813320657F0133209DE21B0042.class);
  
  private static final String windowId = "94EAA455D2644E04AB25D93BE5157B6D";
  private static final String tabId = "FF8080813320657F0133209DE21B0042";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 1;
  private static final String moduleId = "A918E3331C404B889D69AA9BFAFB23AC";
  
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
     
      if (command.contains("FF8080812E2F8EAE012E2F94CF470014")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("FF8080812E2F8EAE012E2F94CF470014");
        SessionInfo.setModuleId("A918E3331C404B889D69AA9BFAFB23AC");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("FF8080812E2F8EAE012E2F94CF470014")) {
        classInfo.type = "P";
        classInfo.id = "FF8080812E2F8EAE012E2F94CF470014";
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
        String strfinReconciliationId = request.getParameter("inpfinReconciliationId");
         String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strfinReconciliationId.equals(""))
              total = saveRecord(vars, myError, 'U', strPFIN_Financial_Account_ID);
          else
              total = saveRecord(vars, myError, 'I', strPFIN_Financial_Account_ID);
          
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
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID", "");

      String strFIN_Reconciliation_ID = vars.getGlobalVariable("inpfinReconciliationId", windowId + "|FIN_Reconciliation_ID", "");
            if (strPFIN_Financial_Account_ID.equals("")) {
        strPFIN_Financial_Account_ID = getParentID(vars, strFIN_Reconciliation_ID);
        if (strPFIN_Financial_Account_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|FIN_Financial_Account_ID");
        vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);

        refreshParentSession(vars, strPFIN_Financial_Account_ID);
      }


      String strView = vars.getSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strFIN_Reconciliation_ID.equals("")) strFIN_Reconciliation_ID = firstElement(vars, tableSQL);
          if (strFIN_Reconciliation_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strFIN_Reconciliation_ID, strPFIN_Financial_Account_ID, tableSQL);

      else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strFIN_Reconciliation_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strFIN_Reconciliation_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strFIN_Reconciliation_ID.equals("")) strFIN_Reconciliation_ID = vars.getRequiredGlobalVariable("inpfinReconciliationId", windowId + "|FIN_Reconciliation_ID");
      else vars.setSessionValue(windowId + "|FIN_Reconciliation_ID", strFIN_Reconciliation_ID);
      
      
      String strPFIN_Financial_Account_ID = getParentID(vars, strFIN_Reconciliation_ID);
      
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);
      vars.setSessionValue("2845D761A8394468BD3BA4710AA888D4|Account.view", "EDIT");

      refreshParentSession(vars, strPFIN_Financial_Account_ID);

      vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.view", "EDIT");

      printPageEdit(response, request, vars, false, strFIN_Reconciliation_ID, strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|FIN_Reconciliation_ID");
      refreshParentSession(vars, strPFIN_Financial_Account_ID);


      String strView = vars.getSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.view");
      String strFIN_Reconciliation_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strFIN_Reconciliation_ID = firstElement(vars, tableSQL);
          if (strFIN_Reconciliation_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strFIN_Reconciliation_ID.equals("")) strFIN_Reconciliation_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strFIN_Reconciliation_ID, strPFIN_Financial_Account_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");
vars.getRequestGlobalVariable("inpParamStatementdate", tabId + "|paramStatementdate");
vars.getRequestGlobalVariable("inpParamStatementdate_f", tabId + "|paramStatementdate_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      
      vars.removeSessionValue(windowId + "|FIN_Reconciliation_ID");
      String strFIN_Reconciliation_ID="";

      String strView = vars.getSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strFIN_Reconciliation_ID = firstElement(vars, tableSQL);
        if (strFIN_Reconciliation_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strFIN_Reconciliation_ID, strPFIN_Financial_Account_ID, tableSQL);

      else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strFIN_Reconciliation_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      

      String strFIN_Reconciliation_ID = vars.getGlobalVariable("inpfinReconciliationId", windowId + "|FIN_Reconciliation_ID", "");
      vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.view", "RELATION");
      printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strFIN_Reconciliation_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");


      printPageEdit(response, request, vars, true, "", strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strFIN_Reconciliation_ID = vars.getGlobalVariable("inpfinReconciliationId", windowId + "|FIN_Reconciliation_ID", "");
      vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strFIN_Reconciliation_ID, strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      String strFIN_Reconciliation_ID = vars.getRequiredStringParameter("inpfinReconciliationId");
      
      String strNext = nextElement(vars, strFIN_Reconciliation_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      String strFIN_Reconciliation_ID = vars.getRequiredStringParameter("inpfinReconciliationId");
      
      String strPrevious = previousElement(vars, strFIN_Reconciliation_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|FIN_Reconciliation_ID");
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|FIN_Reconciliation_ID");
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPFIN_Financial_Account_ID);      
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
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      String strFIN_Reconciliation_ID = vars.getRequiredGlobalVariable("inpfinReconciliationId", windowId + "|FIN_Reconciliation_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPFIN_Financial_Account_ID);      
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
          String strNext = nextElement(vars, strFIN_Reconciliation_ID, tableSQL);
          vars.setSessionValue(windowId + "|FIN_Reconciliation_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strFIN_Reconciliation_ID = vars.getRequiredStringParameter("inpfinReconciliationId");
      //ReconciliationsFF8080813320657F0133209DE21B0042Data data = getEditVariables(vars, strPFIN_Financial_Account_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = ReconciliationsFF8080813320657F0133209DE21B0042Data.delete(this, strFIN_Reconciliation_ID, strPFIN_Financial_Account_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|finReconciliationId");
        vars.setSessionValue(tabId + "|ReconciliationsFF8080813320657F0133209DE21B0042.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONEM_Aprm_Process_RecFF8080812E2F8EAE012E2F94CF470014")) {
        vars.setSessionValue("buttonFF8080812E2F8EAE012E2F94CF470014.stremAprmProcessRec", vars.getStringParameter("inpemAprmProcessRec"));
        vars.setSessionValue("buttonFF8080812E2F8EAE012E2F94CF470014.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonFF8080812E2F8EAE012E2F94CF470014.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonFF8080812E2F8EAE012E2F94CF470014.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("Processed", vars.getStringParameter("inpprocessed"));
p.put("Processed", vars.getStringParameter("inpprocessed"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonFF8080812E2F8EAE012E2F94CF470014.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "FF8080812E2F8EAE012E2F94CF470014", request.getServletPath());
      } else if (vars.commandIn("BUTTONFF8080812E2F8EAE012E2F94CF470014")) {
        String strFIN_Reconciliation_ID = vars.getGlobalVariable("inpfinReconciliationId", windowId + "|FIN_Reconciliation_ID", "");
        String stremAprmProcessRec = vars.getSessionValue("buttonFF8080812E2F8EAE012E2F94CF470014.stremAprmProcessRec");
        String strProcessing = vars.getSessionValue("buttonFF8080812E2F8EAE012E2F94CF470014.strProcessing");
        String strOrg = vars.getSessionValue("buttonFF8080812E2F8EAE012E2F94CF470014.strOrg");
        String strClient = vars.getSessionValue("buttonFF8080812E2F8EAE012E2F94CF470014.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Aprm_Process_RecFF8080812E2F8EAE012E2F94CF470014(response, vars, strFIN_Reconciliation_ID, stremAprmProcessRec, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_Aprm_Process_RecFF8080812E2F8EAE012E2F94CF470014")) {
        String strFIN_Reconciliation_ID = vars.getGlobalVariable("inpKey", windowId + "|FIN_Reconciliation_ID", "");
        
        ProcessBundle pb = new ProcessBundle("FF8080812E2F8EAE012E2F94CF470014", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("FIN_Reconciliation_ID", strFIN_Reconciliation_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
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


    } else if (vars.commandIn("BUTTONPosted")) {
        String strFIN_Reconciliation_ID = vars.getGlobalVariable("inpfinReconciliationId", windowId + "|FIN_Reconciliation_ID", "");
        String strTableId = "B1B7075C46934F0A9FD4C4D0F1457B42";
        String strPosted = vars.getStringParameter("inpposted");
        String strProcessId = "";
        log4j.debug("Loading Posted button in table: " + strTableId);
        String strOrg = vars.getStringParameter("inpadOrgId");
        String strClient = vars.getStringParameter("inpadClientId");
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{
          vars.setSessionValue("Posted|key", strFIN_Reconciliation_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "ReconciliationsFF8080813320657F0133209DE21B0042");
          response.sendRedirect(strDireccion + "/ad_actionButton/Posted.html");
        }



    } else if (vars.commandIn("SAVE_XHR")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPFIN_Financial_Account_ID);
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
  private ReconciliationsFF8080813320657F0133209DE21B0042Data getEditVariables(Connection con, VariablesSecureApp vars, String strPFIN_Financial_Account_ID) throws IOException,ServletException {
    ReconciliationsFF8080813320657F0133209DE21B0042Data data = new ReconciliationsFF8080813320657F0133209DE21B0042Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.finFinancialAccountId = vars.getRequiredStringParameter("inpfinFinancialAccountId");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.widget = vars.getRequiredStringParameter("inpwidget");     data.dateto = vars.getRequiredStringParameter("inpdateto");     data.statementdate = vars.getRequiredStringParameter("inpstatementdate");     data.emAprmPrintdetailed = vars.getRequiredStringParameter("inpemAprmPrintdetailed");     data.printdetailed = vars.getStringParameter("inpprintdetailed", "N");    try {   data.startingbalance = vars.getRequiredNumericParameter("inpstartingbalance");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.endingbalance = vars.getRequiredNumericParameter("inpendingbalance");  } catch (ServletException paramEx) { ex = paramEx; }     data.emAprmPrintsummary = vars.getRequiredStringParameter("inpemAprmPrintsummary");     data.printsummary = vars.getStringParameter("inpprintsummary", "N");     data.docstatus = vars.getRequiredStringParameter("inpdocstatus");     data.processing = vars.getStringParameter("inpprocessing", "N");     data.processed = vars.getRequiredInputGlobalVariable("inpprocessed", windowId + "|Processed", "N");     data.posted = vars.getRequiredStringParameter("inpposted");     data.emAprmProcessReconciliation = vars.getStringParameter("inpemAprmProcessReconciliation");     data.emAprmProcessRec = vars.getStringParameter("inpemAprmProcessRec");     data.finReconciliationId = vars.getRequestGlobalVariable("inpfinReconciliationId", windowId + "|FIN_Reconciliation_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.finFinancialAccountId = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");


          vars.getGlobalVariable("inpforcedTableId", windowId + "|Forced_Table_ID", "");
    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "FIN_Reconciliation", "", data.cDoctypeId, false, true);

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPFIN_Financial_Account_ID) throws IOException,ServletException {
      
      AccountData[] data = AccountData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", data[0].finFinancialAccountId);
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|HasTransaction", AccountData.selectAuxA72A59A036BB47B09105AC5C3361C99C(this, strPFIN_Financial_Account_ID));
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strFIN_Reconciliation_ID) throws ServletException {
    String strPFIN_Financial_Account_ID = ReconciliationsFF8080813320657F0133209DE21B0042Data.selectParentID(this, strFIN_Reconciliation_ID);
    if (strPFIN_Financial_Account_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strFIN_Reconciliation_ID);
      throw new ServletException("Parent record not found");
    }
    return strPFIN_Financial_Account_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Processed", data[0].getField("processed"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|FIN_Reconciliation_ID", data[0].getField("finReconciliationId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPFIN_Financial_Account_ID) throws IOException,ServletException {
      ReconciliationsFF8080813320657F0133209DE21B0042Data[] data = ReconciliationsFF8080813320657F0133209DE21B0042Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, vars.getStringParameter("inpfinReconciliationId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPFIN_Financial_Account_ID, String strFIN_Reconciliation_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamStatementdate = vars.getSessionValue(tabId + "|paramStatementdate");
String strParamStatementdate_f = vars.getSessionValue(tabId + "|paramStatementdate_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo) && ("").equals(strParamStatementdate) && ("").equals(strParamStatementdate_f)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamStatementdate) || ("%").equals(strParamStatementdate))  || !(("").equals(strParamStatementdate_f) || ("%").equals(strParamStatementdate_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strFIN_Reconciliation_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strFIN_Reconciliation_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/ReconciliationsFF8080813320657F0133209DE21B0042_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "ReconciliationsFF8080813320657F0133209DE21B0042", false, "document.frmMain.inpfinReconciliationId", "grid", "..", "".equals("Y"), "FinancialAccount", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPFIN_Financial_Account_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("0055340A334944A285A326395DC98BEC", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "finReconciliationId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ReconciliationsFF8080813320657F0133209DE21B0042_Relation.html", "FinancialAccount", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ReconciliationsFF8080813320657F0133209DE21B0042_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", ReconciliationsFF8080813320657F0133209DE21B0042Data.selectParent(this, strPFIN_Financial_Account_ID));
    else xmlDocument.setParameter("parent", ReconciliationsFF8080813320657F0133209DE21B0042Data.selectParentTrl(this, strPFIN_Financial_Account_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strFIN_Reconciliation_ID, String strPFIN_Financial_Account_ID, TableSQLData tableSQL)
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
    ReconciliationsFF8080813320657F0133209DE21B0042Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamStatementdate = vars.getSessionValue(tabId + "|paramStatementdate");
String strParamStatementdate_f = vars.getSessionValue(tabId + "|paramStatementdate_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo) && ("").equals(strParamStatementdate) && ("").equals(strParamStatementdate_f)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamStatementdate) || ("%").equals(strParamStatementdate))  || !(("").equals(strParamStatementdate_f) || ("%").equals(strParamStatementdate_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = ReconciliationsFF8080813320657F0133209DE21B0042Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, strFIN_Reconciliation_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strFIN_Reconciliation_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new ReconciliationsFF8080813320657F0133209DE21B0042Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("finReconciliationId") == null || dataField.getField("finReconciliationId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strForced_Table_ID = Utility.getContext(this, vars, "AD_TABLE_ID", "94EAA455D2644E04AB25D93BE5157B6D");
    vars.setSessionValue(windowId + "|Forced_Table_ID", strForced_Table_ID);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPFIN_Financial_Account_ID);
        data = ReconciliationsFF8080813320657F0133209DE21B0042Data.set(strPFIN_Financial_Account_ID, Utility.getDefault(this, vars, "Widget", "N", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "EM_APRM_PrintDetailed", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), ReconciliationsFF8080813320657F0133209DE21B0042Data.selectDef8189F49FFED86E56E040007F01003E83_0(this, Utility.getDefault(this, vars, "Createdby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), Utility.getDefault(this, vars, "Updatedby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), ReconciliationsFF8080813320657F0133209DE21B0042Data.selectDef8189F49FFEDA6E56E040007F01003E83_1(this, Utility.getDefault(this, vars, "Updatedby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), "Y", Utility.getDefault(this, vars, "DocumentNo", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Doctype_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Dateto", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Statementdate", "@#Date@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Endingbalance", "0", "94EAA455D2644E04AB25D93BE5157B6D", "0", dataField), Utility.getDefault(this, vars, "Startingbalance", "0", "94EAA455D2644E04AB25D93BE5157B6D", "0", dataField), Utility.getDefault(this, vars, "Docstatus", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Processing", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Processed", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField))), Utility.getDefault(this, vars, "Printdetailed", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Printsummary", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "EM_APRM_PrintSummary", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "EM_Aprm_Process_Rec", "P", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "FF8080812E443491012E443C053A001A", Utility.getDefault(this, vars, "EM_Aprm_Process_Rec", "P", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "FF8080812E443491012E443C053A001A", Utility.getDefault(this, vars, "EM_Aprm_Process_Rec", "P", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField))), Utility.getDefault(this, vars, "EM_APRM_Process_Reconciliation", "P", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "FIN_Reconciliation", "", data[0].cDoctypeId, false, false) + ">";
      }
     }
      
    String currentPOrg=AccountData.selectOrg(this, strPFIN_Financial_Account_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/ReconciliationsFF8080813320657F0133209DE21B0042_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/ReconciliationsFF8080813320657F0133209DE21B0042_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "ReconciliationsFF8080813320657F0133209DE21B0042", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpfinReconciliationId", "", "..", "".equals("Y"), "FinancialAccount", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strFIN_Reconciliation_ID), !hasReadOnlyAccess);
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
      // if (!strFIN_Reconciliation_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ReconciliationsFF8080813320657F0133209DE21B0042_Relation.html", "FinancialAccount", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ReconciliationsFF8080813320657F0133209DE21B0042_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("Forced_Table_ID", strForced_Table_ID);
    
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
comboTableData = new ComboTableData(vars, this, "19", "C_Doctype_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_Doctype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Dateto_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Statementdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonStartingbalance", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("EM_APRM_PrintDetailed_BTNname", Utility.getButtonName(this, vars, "B44441304C1349479806A081D0CF1DC7", "EM_APRM_PrintDetailed_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_APRM_PrintDetailed = org.openbravo.erpCommon.utility.Utility.isModalProcess("3C4A5FB206B74C3CA9FE20116FCA0464"); 
xmlDocument.setParameter("EM_APRM_PrintDetailed_Modal", modalEM_APRM_PrintDetailed?"true":"false");
xmlDocument.setParameter("EM_APRM_PrintSummary_BTNname", Utility.getButtonName(this, vars, "2ED1B6259CE04EBE9EA73C51D365BE29", "EM_APRM_PrintSummary_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_APRM_PrintSummary = org.openbravo.erpCommon.utility.Utility.isModalProcess("BBA11D1A061346459AF6148920FE6629"); 
xmlDocument.setParameter("EM_APRM_PrintSummary_Modal", modalEM_APRM_PrintSummary?"true":"false");
xmlDocument.setParameter("buttonEndingbalance", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
xmlDocument.setParameter("EM_Aprm_Process_Rec_BTNname", Utility.getButtonName(this, vars, "FF8080812E443491012E443C053A001A", (dataField==null?data[0].getField("emAprmProcessRec"):dataField.getField("emAprmProcessRec")), "EM_Aprm_Process_Rec_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_Aprm_Process_Rec = org.openbravo.erpCommon.utility.Utility.isModalProcess("FF8080812E2F8EAE012E2F94CF470014"); 
xmlDocument.setParameter("EM_Aprm_Process_Rec_Modal", modalEM_Aprm_Process_Rec?"true":"false");
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



    void printPageButtonEM_Aprm_Process_RecFF8080812E2F8EAE012E2F94CF470014(HttpServletResponse response, VariablesSecureApp vars, String strFIN_Reconciliation_ID, String stremAprmProcessRec, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8080812E2F8EAE012E2F94CF470014");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Aprm_Process_RecFF8080812E2F8EAE012E2F94CF470014", discard).createXmlDocument();
      xmlDocument.setParameter("key", strFIN_Reconciliation_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ReconciliationsFF8080813320657F0133209DE21B0042_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "FF8080812E2F8EAE012E2F94CF470014");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("FF8080812E2F8EAE012E2F94CF470014");
        vars.removeMessage("FF8080812E2F8EAE012E2F94CF470014");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "Process_Reconciliation", "94EAA455D2644E04AB25D93BE5157B6D"));
    comboTableData = new ComboTableData(vars, this, "17", "action", "FF8080812E443491012E443C053A001A", "FF808081332719060133271E5BB1001B", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonFF8080812E2F8EAE012E2F94CF470014.originalParams"), comboTableData, windowId, Utility.getContext(this, vars, "Process_Reconciliation", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPFIN_Financial_Account_ID) throws IOException, ServletException {
    ReconciliationsFF8080813320657F0133209DE21B0042Data data = null;
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
            data = getEditVariables(con, vars, strPFIN_Financial_Account_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.finReconciliationId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (ReconciliationsFF8080813320657F0133209DE21B0042Data.getCurrentDBTimestamp(this, data.finReconciliationId).equals(
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
                    data.finReconciliationId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.emAprmProcessRecBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "FF8080812E443491012E443C053A001A", data.getField("EM_Aprm_Process_Rec"));
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|FIN_Reconciliation_ID", data.finReconciliationId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet ReconciliationsFF8080813320657F0133209DE21B0042. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
