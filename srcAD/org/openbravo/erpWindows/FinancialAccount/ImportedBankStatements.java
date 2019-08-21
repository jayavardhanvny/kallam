
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

public class ImportedBankStatements extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(ImportedBankStatements.class);
  
  private static final String windowId = "94EAA455D2644E04AB25D93BE5157B6D";
  private static final String tabId = "C56E698100314ABBBBD3A89626CA551C";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 1;
  private static final String moduleId = "0";
  
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
     
      if (command.contains("58A9261BACEF45DDA526F29D8557272D")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("58A9261BACEF45DDA526F29D8557272D");
        SessionInfo.setModuleId("A918E3331C404B889D69AA9BFAFB23AC");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("58A9261BACEF45DDA526F29D8557272D")) {
        classInfo.type = "P";
        classInfo.id = "58A9261BACEF45DDA526F29D8557272D";
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
        String strfinBankstatementId = request.getParameter("inpfinBankstatementId");
         String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strfinBankstatementId.equals(""))
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

      String strFIN_Bankstatement_ID = vars.getGlobalVariable("inpfinBankstatementId", windowId + "|FIN_Bankstatement_ID", "");
            if (strPFIN_Financial_Account_ID.equals("")) {
        strPFIN_Financial_Account_ID = getParentID(vars, strFIN_Bankstatement_ID);
        if (strPFIN_Financial_Account_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|FIN_Financial_Account_ID");
        vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);

        refreshParentSession(vars, strPFIN_Financial_Account_ID);
      }


      String strView = vars.getSessionValue(tabId + "|ImportedBankStatements.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strFIN_Bankstatement_ID.equals("")) strFIN_Bankstatement_ID = firstElement(vars, tableSQL);
          if (strFIN_Bankstatement_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strFIN_Bankstatement_ID, strPFIN_Financial_Account_ID, tableSQL);

      else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strFIN_Bankstatement_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strFIN_Bankstatement_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strFIN_Bankstatement_ID.equals("")) strFIN_Bankstatement_ID = vars.getRequiredGlobalVariable("inpfinBankstatementId", windowId + "|FIN_Bankstatement_ID");
      else vars.setSessionValue(windowId + "|FIN_Bankstatement_ID", strFIN_Bankstatement_ID);
      
      
      String strPFIN_Financial_Account_ID = getParentID(vars, strFIN_Bankstatement_ID);
      
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);
      vars.setSessionValue("2845D761A8394468BD3BA4710AA888D4|Account.view", "EDIT");

      refreshParentSession(vars, strPFIN_Financial_Account_ID);

      vars.setSessionValue(tabId + "|ImportedBankStatements.view", "EDIT");

      printPageEdit(response, request, vars, false, strFIN_Bankstatement_ID, strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|FIN_Bankstatement_ID");
      refreshParentSession(vars, strPFIN_Financial_Account_ID);


      String strView = vars.getSessionValue(tabId + "|ImportedBankStatements.view");
      String strFIN_Bankstatement_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strFIN_Bankstatement_ID = firstElement(vars, tableSQL);
          if (strFIN_Bankstatement_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strFIN_Bankstatement_ID.equals("")) strFIN_Bankstatement_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strFIN_Bankstatement_ID, strPFIN_Financial_Account_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");
vars.getRequestGlobalVariable("inpParamName", tabId + "|paramName");
vars.getRequestGlobalVariable("inpParamImportdate", tabId + "|paramImportdate");
vars.getRequestGlobalVariable("inpParamStatementdate", tabId + "|paramStatementdate");
vars.getRequestGlobalVariable("inpParamImportdate_f", tabId + "|paramImportdate_f");
vars.getRequestGlobalVariable("inpParamStatementdate_f", tabId + "|paramStatementdate_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      
      vars.removeSessionValue(windowId + "|FIN_Bankstatement_ID");
      String strFIN_Bankstatement_ID="";

      String strView = vars.getSessionValue(tabId + "|ImportedBankStatements.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strFIN_Bankstatement_ID = firstElement(vars, tableSQL);
        if (strFIN_Bankstatement_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|ImportedBankStatements.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strFIN_Bankstatement_ID, strPFIN_Financial_Account_ID, tableSQL);

      else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strFIN_Bankstatement_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      

      String strFIN_Bankstatement_ID = vars.getGlobalVariable("inpfinBankstatementId", windowId + "|FIN_Bankstatement_ID", "");
      vars.setSessionValue(tabId + "|ImportedBankStatements.view", "RELATION");
      printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strFIN_Bankstatement_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");


      printPageEdit(response, request, vars, true, "", strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strFIN_Bankstatement_ID = vars.getGlobalVariable("inpfinBankstatementId", windowId + "|FIN_Bankstatement_ID", "");
      vars.setSessionValue(tabId + "|ImportedBankStatements.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strFIN_Bankstatement_ID, strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      String strFIN_Bankstatement_ID = vars.getRequiredStringParameter("inpfinBankstatementId");
      
      String strNext = nextElement(vars, strFIN_Bankstatement_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      String strFIN_Bankstatement_ID = vars.getRequiredStringParameter("inpfinBankstatementId");
      
      String strPrevious = previousElement(vars, strFIN_Bankstatement_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      vars.setSessionValue(tabId + "|ImportedBankStatements.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|ImportedBankStatements.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|ImportedBankStatements.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|ImportedBankStatements.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|FIN_Bankstatement_ID");
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|ImportedBankStatements.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|ImportedBankStatements.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|FIN_Bankstatement_ID");
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
      String strFIN_Bankstatement_ID = vars.getRequiredGlobalVariable("inpfinBankstatementId", windowId + "|FIN_Bankstatement_ID");
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
          String strNext = nextElement(vars, strFIN_Bankstatement_ID, tableSQL);
          vars.setSessionValue(windowId + "|FIN_Bankstatement_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strFIN_Bankstatement_ID = vars.getRequiredStringParameter("inpfinBankstatementId");
      //ImportedBankStatementsData data = getEditVariables(vars, strPFIN_Financial_Account_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = ImportedBankStatementsData.delete(this, strFIN_Bankstatement_ID, strPFIN_Financial_Account_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|finBankstatementId");
        vars.setSessionValue(tabId + "|ImportedBankStatements.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONEM_APRM_Process_BS58A9261BACEF45DDA526F29D8557272D")) {
        vars.setSessionValue("button58A9261BACEF45DDA526F29D8557272D.stremAprmProcessBs", vars.getStringParameter("inpemAprmProcessBs"));
        vars.setSessionValue("button58A9261BACEF45DDA526F29D8557272D.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button58A9261BACEF45DDA526F29D8557272D.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button58A9261BACEF45DDA526F29D8557272D.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button58A9261BACEF45DDA526F29D8557272D.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "58A9261BACEF45DDA526F29D8557272D", request.getServletPath());
      } else if (vars.commandIn("BUTTON58A9261BACEF45DDA526F29D8557272D")) {
        String strFIN_Bankstatement_ID = vars.getGlobalVariable("inpfinBankstatementId", windowId + "|FIN_Bankstatement_ID", "");
        String stremAprmProcessBs = vars.getSessionValue("button58A9261BACEF45DDA526F29D8557272D.stremAprmProcessBs");
        String strProcessing = vars.getSessionValue("button58A9261BACEF45DDA526F29D8557272D.strProcessing");
        String strOrg = vars.getSessionValue("button58A9261BACEF45DDA526F29D8557272D.strOrg");
        String strClient = vars.getSessionValue("button58A9261BACEF45DDA526F29D8557272D.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_APRM_Process_BS58A9261BACEF45DDA526F29D8557272D(response, vars, strFIN_Bankstatement_ID, stremAprmProcessBs, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_APRM_Process_BS58A9261BACEF45DDA526F29D8557272D")) {
        String strFIN_Bankstatement_ID = vars.getGlobalVariable("inpKey", windowId + "|FIN_Bankstatement_ID", "");
        
        ProcessBundle pb = new ProcessBundle("58A9261BACEF45DDA526F29D8557272D", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("FIN_Bankstatement_ID", strFIN_Bankstatement_ID);
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
        String strFIN_Bankstatement_ID = vars.getGlobalVariable("inpfinBankstatementId", windowId + "|FIN_Bankstatement_ID", "");
        String strTableId = "D4C23A17190649E7B78F55A05AF3438C";
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
          vars.setSessionValue("Posted|key", strFIN_Bankstatement_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "ImportedBankStatements");
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
  private ImportedBankStatementsData getEditVariables(Connection con, VariablesSecureApp vars, String strPFIN_Financial_Account_ID) throws IOException,ServletException {
    ImportedBankStatementsData data = new ImportedBankStatementsData();
    ServletException ex = null;
    try {
    data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.name = vars.getRequiredStringParameter("inpname");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.importdate = vars.getRequiredStringParameter("inpimportdate");     data.statementdate = vars.getRequiredStringParameter("inpstatementdate");     data.filename = vars.getStringParameter("inpfilename");     data.finReconciliationId = vars.getStringParameter("inpfinReconciliationId");     data.notes = vars.getStringParameter("inpnotes");     data.posted = vars.getRequiredStringParameter("inpposted");     data.emAprmProcessBs = vars.getRequestGlobalVariable("inpemAprmProcessBs", windowId + "|EM_APRM_Process_BS");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.finFinancialAccountId = vars.getRequiredStringParameter("inpfinFinancialAccountId");     data.processing = vars.getStringParameter("inpprocessing", "N");     data.finBankstatementId = vars.getRequestGlobalVariable("inpfinBankstatementId", windowId + "|FIN_Bankstatement_ID");     data.processed = vars.getStringParameter("inpprocessed", "N"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.finFinancialAccountId = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");


    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "FIN_BankStatement", "", data.cDoctypeId, false, true);

    
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
  
  
  private String getParentID(VariablesSecureApp vars, String strFIN_Bankstatement_ID) throws ServletException {
    String strPFIN_Financial_Account_ID = ImportedBankStatementsData.selectParentID(this, strFIN_Bankstatement_ID);
    if (strPFIN_Financial_Account_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strFIN_Bankstatement_ID);
      throw new ServletException("Parent record not found");
    }
    return strPFIN_Financial_Account_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|EM_APRM_Process_BS", data[0].getField("emAprmProcessBs"));    vars.setSessionValue(windowId + "|FIN_Bankstatement_ID", data[0].getField("finBankstatementId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPFIN_Financial_Account_ID) throws IOException,ServletException {
      ImportedBankStatementsData[] data = ImportedBankStatementsData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, vars.getStringParameter("inpfinBankstatementId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPFIN_Financial_Account_ID, String strFIN_Bankstatement_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamImportdate = vars.getSessionValue(tabId + "|paramImportdate");
String strParamStatementdate = vars.getSessionValue(tabId + "|paramStatementdate");
String strParamImportdate_f = vars.getSessionValue(tabId + "|paramImportdate_f");
String strParamStatementdate_f = vars.getSessionValue(tabId + "|paramStatementdate_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo) && ("").equals(strParamName) && ("").equals(strParamImportdate) && ("").equals(strParamStatementdate) && ("").equals(strParamImportdate_f) && ("").equals(strParamStatementdate_f)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamImportdate) || ("%").equals(strParamImportdate))  || !(("").equals(strParamStatementdate) || ("%").equals(strParamStatementdate))  || !(("").equals(strParamImportdate_f) || ("%").equals(strParamImportdate_f))  || !(("").equals(strParamStatementdate_f) || ("%").equals(strParamStatementdate_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strFIN_Bankstatement_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strFIN_Bankstatement_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/ImportedBankStatements_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "ImportedBankStatements", false, "document.frmMain.inpfinBankstatementId", "grid", "..", "".equals("Y"), "FinancialAccount", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPFIN_Financial_Account_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("82683CB3CF8518FDE040007F0100573E", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "finBankstatementId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ImportedBankStatements_Relation.html", "FinancialAccount", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ImportedBankStatements_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", ImportedBankStatementsData.selectParent(this, strPFIN_Financial_Account_ID));
    else xmlDocument.setParameter("parent", ImportedBankStatementsData.selectParentTrl(this, strPFIN_Financial_Account_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strFIN_Bankstatement_ID, String strPFIN_Financial_Account_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " FIN_BankStatement.Importdate desc";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    ImportedBankStatementsData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamImportdate = vars.getSessionValue(tabId + "|paramImportdate");
String strParamStatementdate = vars.getSessionValue(tabId + "|paramStatementdate");
String strParamImportdate_f = vars.getSessionValue(tabId + "|paramImportdate_f");
String strParamStatementdate_f = vars.getSessionValue(tabId + "|paramStatementdate_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo) && ("").equals(strParamName) && ("").equals(strParamImportdate) && ("").equals(strParamStatementdate) && ("").equals(strParamImportdate_f) && ("").equals(strParamStatementdate_f)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamImportdate) || ("%").equals(strParamImportdate))  || !(("").equals(strParamStatementdate) || ("%").equals(strParamStatementdate))  || !(("").equals(strParamImportdate_f) || ("%").equals(strParamImportdate_f))  || !(("").equals(strParamStatementdate_f) || ("%").equals(strParamStatementdate_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = ImportedBankStatementsData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, strFIN_Bankstatement_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strFIN_Bankstatement_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new ImportedBankStatementsData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("finBankstatementId") == null || dataField.getField("finBankstatementId").equals("")) {
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
        refreshSessionNew(vars, strPFIN_Financial_Account_ID);
        data = ImportedBankStatementsData.set(strPFIN_Financial_Account_ID, Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField))), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), ImportedBankStatementsData.selectDef8189F49FFEA56E56E040007F01003E83_0(this, Utility.getDefault(this, vars, "Createdby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), Utility.getDefault(this, vars, "Updatedby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), ImportedBankStatementsData.selectDef8189F49FFEA76E56E040007F01003E83_1(this, Utility.getDefault(this, vars, "Updatedby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), "Y", Utility.getDefault(this, vars, "DocumentNo", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Name", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Notes", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Doctype_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Filename", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Importdate", "@#Date@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Statementdate", "@#Date@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "FIN_Reconciliation_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Processing", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Processed", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "EM_APRM_Process_BS", "P", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "EC75B6F5A9504DB6B3F3356EA85F15EE", Utility.getDefault(this, vars, "EM_APRM_Process_BS", "P", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "EC75B6F5A9504DB6B3F3356EA85F15EE", Utility.getDefault(this, vars, "EM_APRM_Process_BS", "P", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField))));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "FIN_BankStatement", "", data[0].cDoctypeId, false, false) + ">";
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
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/ImportedBankStatements_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/ImportedBankStatements_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "ImportedBankStatements", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpfinBankstatementId", "", "..", "".equals("Y"), "FinancialAccount", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strFIN_Bankstatement_ID), !hasReadOnlyAccess);
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
      // if (!strFIN_Bankstatement_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ImportedBankStatements_Relation.html", "FinancialAccount", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ImportedBankStatements_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "C_Doctype_ID", "", "16C9E72407D94B93968D1E81F32D537D", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_Doctype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Importdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Statementdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
xmlDocument.setParameter("EM_APRM_Process_BS_BTNname", Utility.getButtonName(this, vars, "EC75B6F5A9504DB6B3F3356EA85F15EE", (dataField==null?data[0].getField("emAprmProcessBs"):dataField.getField("emAprmProcessBs")), "EM_APRM_Process_BS_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_APRM_Process_BS = org.openbravo.erpCommon.utility.Utility.isModalProcess("58A9261BACEF45DDA526F29D8557272D"); 
xmlDocument.setParameter("EM_APRM_Process_BS_Modal", modalEM_APRM_Process_BS?"true":"false");
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



    void printPageButtonEM_APRM_Process_BS58A9261BACEF45DDA526F29D8557272D(HttpServletResponse response, VariablesSecureApp vars, String strFIN_Bankstatement_ID, String stremAprmProcessBs, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 58A9261BACEF45DDA526F29D8557272D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_APRM_Process_BS58A9261BACEF45DDA526F29D8557272D", discard).createXmlDocument();
      xmlDocument.setParameter("key", strFIN_Bankstatement_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ImportedBankStatements_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "58A9261BACEF45DDA526F29D8557272D");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("58A9261BACEF45DDA526F29D8557272D");
        vars.removeMessage("58A9261BACEF45DDA526F29D8557272D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "EM_APRM_Process_BS", "94EAA455D2644E04AB25D93BE5157B6D"));
    comboTableData = new ComboTableData(vars, this, "17", "action", "EC75B6F5A9504DB6B3F3356EA85F15EE", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button58A9261BACEF45DDA526F29D8557272D.originalParams"), comboTableData, windowId, Utility.getContext(this, vars, "EM_APRM_Process_BS", "94EAA455D2644E04AB25D93BE5157B6D"));
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
      String result = "var strShowAcct=\"" +Utility.getContext(this, vars, "#ShowAcct", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
    ImportedBankStatementsData data = null;
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
                data.finBankstatementId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (ImportedBankStatementsData.getCurrentDBTimestamp(this, data.finBankstatementId).equals(
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
                    data.finBankstatementId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.emAprmProcessBsBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "EC75B6F5A9504DB6B3F3356EA85F15EE", data.getField("EM_APRM_Process_BS"));
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|FIN_Bankstatement_ID", data.finBankstatementId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet ImportedBankStatements. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
