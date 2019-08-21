
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

public class Transaction extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Transaction.class);
  
  private static final String windowId = "94EAA455D2644E04AB25D93BE5157B6D";
  private static final String tabId = "23691259D1BD4496BCC5F32645BCA4B9";
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
     
      if (command.contains("FF8080812F348A97012F349DC24F0007")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("FF8080812F348A97012F349DC24F0007");
        SessionInfo.setModuleId("A918E3331C404B889D69AA9BFAFB23AC");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("FF8080812F348A97012F349DC24F0007")) {
        classInfo.type = "P";
        classInfo.id = "FF8080812F348A97012F349DC24F0007";
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
        String strfinFinaccTransactionId = request.getParameter("inpfinFinaccTransactionId");
         String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strfinFinaccTransactionId.equals(""))
              total = saveRecord(vars, myError, 'U', strPFin_Financial_Account_ID);
          else
              total = saveRecord(vars, myError, 'I', strPFin_Financial_Account_ID);
          
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
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID", "");

      String strFin_Finacc_Transaction_ID = vars.getGlobalVariable("inpfinFinaccTransactionId", windowId + "|Fin_Finacc_Transaction_ID", "");
            if (strPFin_Financial_Account_ID.equals("")) {
        strPFin_Financial_Account_ID = getParentID(vars, strFin_Finacc_Transaction_ID);
        if (strPFin_Financial_Account_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|Fin_Financial_Account_ID");
        vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", strPFin_Financial_Account_ID);

        refreshParentSession(vars, strPFin_Financial_Account_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Transaction.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strFin_Finacc_Transaction_ID.equals("")) strFin_Finacc_Transaction_ID = firstElement(vars, tableSQL);
          if (strFin_Finacc_Transaction_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strFin_Finacc_Transaction_ID, strPFin_Financial_Account_ID, tableSQL);

      else printPageDataSheet(response, vars, strPFin_Financial_Account_ID, strFin_Finacc_Transaction_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strFin_Finacc_Transaction_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strFin_Finacc_Transaction_ID.equals("")) strFin_Finacc_Transaction_ID = vars.getRequiredGlobalVariable("inpfinFinaccTransactionId", windowId + "|Fin_Finacc_Transaction_ID");
      else vars.setSessionValue(windowId + "|Fin_Finacc_Transaction_ID", strFin_Finacc_Transaction_ID);
      
      
      String strPFin_Financial_Account_ID = getParentID(vars, strFin_Finacc_Transaction_ID);
      
      vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", strPFin_Financial_Account_ID);
      vars.setSessionValue("2845D761A8394468BD3BA4710AA888D4|Account.view", "EDIT");

      refreshParentSession(vars, strPFin_Financial_Account_ID);

      vars.setSessionValue(tabId + "|Transaction.view", "EDIT");

      printPageEdit(response, request, vars, false, strFin_Finacc_Transaction_ID, strPFin_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|Fin_Finacc_Transaction_ID");
      refreshParentSession(vars, strPFin_Financial_Account_ID);


      String strView = vars.getSessionValue(tabId + "|Transaction.view");
      String strFin_Finacc_Transaction_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strFin_Finacc_Transaction_ID = firstElement(vars, tableSQL);
          if (strFin_Finacc_Transaction_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strFin_Finacc_Transaction_ID.equals("")) strFin_Finacc_Transaction_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strFin_Finacc_Transaction_ID, strPFin_Financial_Account_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPFin_Financial_Account_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamFin_Financial_Account_ID", tabId + "|paramFin_Financial_Account_ID");
vars.getRequestGlobalVariable("inpParamFin_Payment_ID", tabId + "|paramFin_Payment_ID");
vars.getRequestGlobalVariable("inpParamPaymentamt", tabId + "|paramPaymentamt");
vars.getRequestGlobalVariable("inpParamPaymentamt_f", tabId + "|paramPaymentamt_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");

      
      vars.removeSessionValue(windowId + "|Fin_Finacc_Transaction_ID");
      String strFin_Finacc_Transaction_ID="";

      String strView = vars.getSessionValue(tabId + "|Transaction.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strFin_Finacc_Transaction_ID = firstElement(vars, tableSQL);
        if (strFin_Finacc_Transaction_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Transaction.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strFin_Finacc_Transaction_ID, strPFin_Financial_Account_ID, tableSQL);

      else printPageDataSheet(response, vars, strPFin_Financial_Account_ID, strFin_Finacc_Transaction_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
      

      String strFin_Finacc_Transaction_ID = vars.getGlobalVariable("inpfinFinaccTransactionId", windowId + "|Fin_Finacc_Transaction_ID", "");
      vars.setSessionValue(tabId + "|Transaction.view", "RELATION");
      printPageDataSheet(response, vars, strPFin_Financial_Account_ID, strFin_Finacc_Transaction_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");


      printPageEdit(response, request, vars, true, "", strPFin_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strFin_Finacc_Transaction_ID = vars.getGlobalVariable("inpfinFinaccTransactionId", windowId + "|Fin_Finacc_Transaction_ID", "");
      vars.setSessionValue(tabId + "|Transaction.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strFin_Finacc_Transaction_ID, strPFin_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
      String strFin_Finacc_Transaction_ID = vars.getRequiredStringParameter("inpfinFinaccTransactionId");
      
      String strNext = nextElement(vars, strFin_Finacc_Transaction_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPFin_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
      String strFin_Finacc_Transaction_ID = vars.getRequiredStringParameter("inpfinFinaccTransactionId");
      
      String strPrevious = previousElement(vars, strFin_Finacc_Transaction_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPFin_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");

      vars.setSessionValue(tabId + "|Transaction.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Transaction.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Transaction.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Transaction.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Fin_Finacc_Transaction_ID");
      vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", strPFin_Financial_Account_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Transaction.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Transaction.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Fin_Finacc_Transaction_ID");
      vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", strPFin_Financial_Account_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPFin_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPFin_Financial_Account_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPFin_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPFin_Financial_Account_ID);      
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
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
      String strFin_Finacc_Transaction_ID = vars.getRequiredGlobalVariable("inpfinFinaccTransactionId", windowId + "|Fin_Finacc_Transaction_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPFin_Financial_Account_ID);      
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
          String strNext = nextElement(vars, strFin_Finacc_Transaction_ID, tableSQL);
          vars.setSessionValue(windowId + "|Fin_Finacc_Transaction_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");

      String strFin_Finacc_Transaction_ID = vars.getRequiredStringParameter("inpfinFinaccTransactionId");
      //TransactionData data = getEditVariables(vars, strPFin_Financial_Account_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = TransactionData.delete(this, strFin_Finacc_Transaction_ID, strPFin_Financial_Account_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|finFinaccTransactionId");
        vars.setSessionValue(tabId + "|Transaction.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONEM_APRM_DeleteFF8080812F348A97012F349DC24F0007")) {
        vars.setSessionValue("buttonFF8080812F348A97012F349DC24F0007.stremAprmDelete", vars.getStringParameter("inpemAprmDelete"));
        vars.setSessionValue("buttonFF8080812F348A97012F349DC24F0007.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonFF8080812F348A97012F349DC24F0007.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonFF8080812F348A97012F349DC24F0007.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonFF8080812F348A97012F349DC24F0007.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "FF8080812F348A97012F349DC24F0007", request.getServletPath());
      } else if (vars.commandIn("BUTTONFF8080812F348A97012F349DC24F0007")) {
        String strFin_Finacc_Transaction_ID = vars.getGlobalVariable("inpfinFinaccTransactionId", windowId + "|Fin_Finacc_Transaction_ID", "");
        String stremAprmDelete = vars.getSessionValue("buttonFF8080812F348A97012F349DC24F0007.stremAprmDelete");
        String strProcessing = vars.getSessionValue("buttonFF8080812F348A97012F349DC24F0007.strProcessing");
        String strOrg = vars.getSessionValue("buttonFF8080812F348A97012F349DC24F0007.strOrg");
        String strClient = vars.getSessionValue("buttonFF8080812F348A97012F349DC24F0007.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_APRM_DeleteFF8080812F348A97012F349DC24F0007(response, vars, strFin_Finacc_Transaction_ID, stremAprmDelete, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_APRM_DeleteFF8080812F348A97012F349DC24F0007")) {
        String strFin_Finacc_Transaction_ID = vars.getGlobalVariable("inpKey", windowId + "|Fin_Finacc_Transaction_ID", "");
        
        ProcessBundle pb = new ProcessBundle("FF8080812F348A97012F349DC24F0007", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Fin_Finacc_Transaction_ID", strFin_Finacc_Transaction_ID);
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


    } else if (vars.commandIn("BUTTONPosted")) {
        String strFin_Finacc_Transaction_ID = vars.getGlobalVariable("inpfinFinaccTransactionId", windowId + "|Fin_Finacc_Transaction_ID", "");
        String strTableId = "4D8C3B3C31D1410DA046140C9F024D17";
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
          vars.setSessionValue("Posted|key", strFin_Finacc_Transaction_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "Transaction");
          response.sendRedirect(strDireccion + "/ad_actionButton/Posted.html");
        }



    } else if (vars.commandIn("SAVE_XHR")) {
      String strPFin_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPFin_Financial_Account_ID);
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
  private TransactionData getEditVariables(Connection con, VariablesSecureApp vars, String strPFin_Financial_Account_ID) throws IOException,ServletException {
    TransactionData data = new TransactionData();
    ServletException ex = null;
    try {
   try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.trxtype = vars.getStringParameter("inptrxtype");     data.trxtyper = vars.getStringParameter("inptrxtype_R");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.status = vars.getRequiredStringParameter("inpstatus");     data.statusr = vars.getStringParameter("inpstatus_R");     data.createdbyalgorithm = vars.getStringParameter("inpcreatedbyalgorithm", "N");     data.statementdate = vars.getStringParameter("inpstatementdate");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.emAprmModify = vars.getRequiredStringParameter("inpemAprmModify");     data.dateacct = vars.getStringParameter("inpdateacct");     data.finFinancialAccountId = vars.getRequiredStringParameter("inpfinFinancialAccountId");     data.finReconciliationId = vars.getStringParameter("inpfinReconciliationId");     data.finReconciliationIdr = vars.getStringParameter("inpfinReconciliationId_R");     data.finPaymentId = vars.getRequestGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID");     data.finPaymentIdr = vars.getStringParameter("inpfinPaymentId_R");     data.description = vars.getStringParameter("inpdescription");     data.cGlitemId = vars.getStringParameter("inpcGlitemId");     data.cGlitemIdr = vars.getStringParameter("inpcGlitemId_R");     data.cCurrencyId = vars.getRequiredStringParameter("inpcCurrencyId");     data.cCurrencyIdr = vars.getStringParameter("inpcCurrencyId_R");    try {   data.depositamt = vars.getRequiredNumericParameter("inpdepositamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.paymentamt = vars.getRequiredNumericParameter("inppaymentamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.processed = vars.getStringParameter("inpprocessed", "N");     data.foreignCurrencyId = vars.getStringParameter("inpforeignCurrencyId");     data.foreignCurrencyIdr = vars.getStringParameter("inpforeignCurrencyId_R");     data.processing = vars.getStringParameter("inpprocessing", "N");    try {   data.foreignAmount = vars.getNumericParameter("inpforeignAmount");  } catch (ServletException paramEx) { ex = paramEx; }     data.posted = vars.getRequiredStringParameter("inpposted");     data.emAprmDelete = vars.getStringParameter("inpemAprmDelete");    try {   data.foreignConvertRate = vars.getNumericParameter("inpforeignConvertRate");  } catch (ServletException paramEx) { ex = paramEx; }     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.mProductId = vars.getStringParameter("inpmProductId");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.cCampaignId = vars.getStringParameter("inpcCampaignId");     data.cCampaignIdr = vars.getStringParameter("inpcCampaignId_R");     data.cActivityId = vars.getStringParameter("inpcActivityId");     data.cActivityIdr = vars.getStringParameter("inpcActivityId_R");     data.cSalesregionId = vars.getStringParameter("inpcSalesregionId");     data.cSalesregionIdr = vars.getStringParameter("inpcSalesregionId_R");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.finFinaccTransactionId = vars.getRequestGlobalVariable("inpfinFinaccTransactionId", windowId + "|Fin_Finacc_Transaction_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.finFinancialAccountId = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|Fin_Financial_Account_ID");


          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
          vars.getGlobalVariable("inpissotrx", windowId + "|IsSOTrx", "");
    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPFin_Financial_Account_ID) throws IOException,ServletException {
      
      AccountData[] data = AccountData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFin_Financial_Account_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", data[0].finFinancialAccountId);
      vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", strPFin_Financial_Account_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|HasTransaction", AccountData.selectAuxA72A59A036BB47B09105AC5C3361C99C(this, strPFin_Financial_Account_ID));
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strFin_Finacc_Transaction_ID) throws ServletException {
    String strPFin_Financial_Account_ID = TransactionData.selectParentID(this, strFin_Finacc_Transaction_ID);
    if (strPFin_Financial_Account_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strFin_Finacc_Transaction_ID);
      throw new ServletException("Parent record not found");
    }
    return strPFin_Financial_Account_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Fin_Payment_ID", data[0].getField("finPaymentId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|Fin_Finacc_Transaction_ID", data[0].getField("finFinaccTransactionId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPFin_Financial_Account_ID) throws IOException,ServletException {
      TransactionData[] data = TransactionData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFin_Financial_Account_ID, vars.getStringParameter("inpfinFinaccTransactionId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPFin_Financial_Account_ID, String strFin_Finacc_Transaction_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamFin_Financial_Account_ID = vars.getSessionValue(tabId + "|paramFin_Financial_Account_ID");
String strParamFin_Payment_ID = vars.getSessionValue(tabId + "|paramFin_Payment_ID");
String strParamPaymentamt = vars.getSessionValue(tabId + "|paramPaymentamt");
String strParamPaymentamt_f = vars.getSessionValue(tabId + "|paramPaymentamt_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamFin_Financial_Account_ID) && ("").equals(strParamFin_Payment_ID) && ("").equals(strParamPaymentamt) && ("").equals(strParamPaymentamt_f)) || !(("").equals(strParamFin_Financial_Account_ID) || ("%").equals(strParamFin_Financial_Account_ID))  || !(("").equals(strParamFin_Payment_ID) || ("%").equals(strParamFin_Payment_ID))  || !(("").equals(strParamPaymentamt) || ("%").equals(strParamPaymentamt))  || !(("").equals(strParamPaymentamt_f) || ("%").equals(strParamPaymentamt_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strFin_Finacc_Transaction_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strFin_Finacc_Transaction_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/Transaction_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Transaction", false, "document.frmMain.inpfinFinaccTransactionId", "grid", "..", "".equals("Y"), "FinancialAccount", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), true, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPFin_Financial_Account_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("4EA61992AC63419F99B4E94B3C26BE4A", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "finFinaccTransactionId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Transaction_Relation.html", "FinancialAccount", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Transaction_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", TransactionData.selectParent(this, strPFin_Financial_Account_ID));
    else xmlDocument.setParameter("parent", TransactionData.selectParentTrl(this, strPFin_Financial_Account_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strFin_Finacc_Transaction_ID, String strPFin_Financial_Account_ID, TableSQLData tableSQL)
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
    TransactionData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamFin_Financial_Account_ID = vars.getSessionValue(tabId + "|paramFin_Financial_Account_ID");
String strParamFin_Payment_ID = vars.getSessionValue(tabId + "|paramFin_Payment_ID");
String strParamPaymentamt = vars.getSessionValue(tabId + "|paramPaymentamt");
String strParamPaymentamt_f = vars.getSessionValue(tabId + "|paramPaymentamt_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamFin_Financial_Account_ID) && ("").equals(strParamFin_Payment_ID) && ("").equals(strParamPaymentamt) && ("").equals(strParamPaymentamt_f)) || !(("").equals(strParamFin_Financial_Account_ID) || ("%").equals(strParamFin_Financial_Account_ID))  || !(("").equals(strParamFin_Payment_ID) || ("%").equals(strParamFin_Payment_ID))  || !(("").equals(strParamPaymentamt) || ("%").equals(strParamPaymentamt))  || !(("").equals(strParamPaymentamt_f) || ("%").equals(strParamPaymentamt_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = TransactionData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFin_Financial_Account_ID, strFin_Finacc_Transaction_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strFin_Finacc_Transaction_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (data==null || data.length==0) {
        strFin_Finacc_Transaction_ID = firstElement(vars, tableSQL);
        if (strFin_Finacc_Transaction_ID.equals("")) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        } else {
          data = TransactionData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFin_Financial_Account_ID, strFin_Finacc_Transaction_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
        }
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new TransactionData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("finFinaccTransactionId") == null || dataField.getField("finFinaccTransactionId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strDOCBASETYPE = "FAT";
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
        String strIsSOTrx = TransactionData.selectAuxDCA04CBCB2454C7690E5F131326F06EF(this, ((dataField!=null)?dataField.getField("finPaymentId"):((data==null || data.length==0)?"":data[0].finPaymentId)));
    vars.setSessionValue(windowId + "|IsSOTrx", strIsSOTrx);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPFin_Financial_Account_ID);
        data = TransactionData.set(strPFin_Financial_Account_ID, Utility.getDefault(this, vars, "CreatedByAlgorithm", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Foreign_Currency_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Foreign_Amount", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), TransactionData.selectDef50C572BF5B0E46319FC8F32201A8408E_0(this, Utility.getDefault(this, vars, "C_Bpartner_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), Utility.getDefault(this, vars, "M_Product_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), TransactionData.selectDef5170629668EC427DBABD5D9A6B2C15DA_1(this,  vars.getLanguage(), Utility.getDefault(this, vars, "M_Product_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), TransactionData.selectDef7891269C8412655DE040007F010155CE_2(this, Utility.getDefault(this, vars, "Createdby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), Utility.getDefault(this, vars, "Updatedby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), TransactionData.selectDef7891269C8414655DE040007F010155CE_3(this, Utility.getDefault(this, vars, "Updatedby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), "Y", Utility.getDefault(this, vars, "C_Currency_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Fin_Payment_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Glitem_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Status", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Paymentamt", "0", "94EAA455D2644E04AB25D93BE5157B6D", "0", dataField), Utility.getDefault(this, vars, "Depositamt", "0", "94EAA455D2644E04AB25D93BE5157B6D", "0", dataField), Utility.getDefault(this, vars, "Processed", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Processing", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField))), Utility.getDefault(this, vars, "C_Salesregion_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "DateAcct", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Foreign_Convert_Rate", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Campaign_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Activity_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Trxtype", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Statementdate", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Description", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "FIN_Reconciliation_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "EM_APRM_Delete", "", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "EM_APRM_Modify", "N", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), TransactionData.selectDefE16BB81D95984DF08700A8940683A636(this, strPFin_Financial_Account_ID));
        
      }
     }
      
    String currentPOrg=AccountData.selectOrg(this, strPFin_Financial_Account_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/Transaction_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/Transaction_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Transaction", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpfinFinaccTransactionId", "", "..", "".equals("Y"), "FinancialAccount", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strFin_Finacc_Transaction_ID), !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    toolbar.setDeleteable(true);
    toolbar.prepareEditionTemplate("N".equals("Y"), hasSearchCondition, vars.getSessionValue("#ShowTest", "N").equals("Y"), "SR", Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
      // if (!strFin_Finacc_Transaction_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Transaction_Relation.html", "FinancialAccount", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Transaction_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
        xmlDocument.setParameter("IsSOTrx", strIsSOTrx);
    
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
comboTableData = new ComboTableData(vars, this, "17", "Trxtype", "4EFC9773F30B4ACE97D225BD13CFF8CB", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("trxtype"):dataField.getField("trxtype")));
xmlDocument.setData("reportTrxtype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Status", "575BCB88A4694C27BC013DE9C73E6FE7", "7A86F689FB1C46F19DBE338D6DECD703", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("status"):dataField.getField("status")));
xmlDocument.setData("reportStatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Statementdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("DateAcct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "FIN_Reconciliation_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finReconciliationId"):dataField.getField("finReconciliationId")));
xmlDocument.setData("reportFIN_Reconciliation_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Fin_Payment_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finPaymentId"):dataField.getField("finPaymentId")));
xmlDocument.setData("reportFin_Payment_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Glitem_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cGlitemId"):dataField.getField("cGlitemId")));
xmlDocument.setData("reportC_Glitem_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Currency_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCurrencyId"):dataField.getField("cCurrencyId")));
xmlDocument.setData("reportC_Currency_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonDepositamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPaymentamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "Foreign_Currency_ID", "112", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("foreignCurrencyId"):dataField.getField("foreignCurrencyId")));
xmlDocument.setData("reportForeign_Currency_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonForeign_Amount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
xmlDocument.setParameter("EM_APRM_Delete_BTNname", Utility.getButtonName(this, vars, "1968A3A7AE17432FB792343C8F48002B", "EM_APRM_Delete_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_APRM_Delete = org.openbravo.erpCommon.utility.Utility.isModalProcess("FF8080812F348A97012F349DC24F0007"); 
xmlDocument.setParameter("EM_APRM_Delete_Modal", modalEM_APRM_Delete?"true":"false");
xmlDocument.setParameter("buttonForeign_Convert_Rate", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_Project_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cProjectId"):dataField.getField("cProjectId")));
xmlDocument.setData("reportC_Project_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Campaign_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCampaignId"):dataField.getField("cCampaignId")));
xmlDocument.setData("reportC_Campaign_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Activity_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cActivityId"):dataField.getField("cActivityId")));
xmlDocument.setData("reportC_Activity_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Salesregion_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cSalesregionId"):dataField.getField("cSalesregionId")));
xmlDocument.setData("reportC_Salesregion_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
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



    void printPageButtonEM_APRM_DeleteFF8080812F348A97012F349DC24F0007(HttpServletResponse response, VariablesSecureApp vars, String strFin_Finacc_Transaction_ID, String stremAprmDelete, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8080812F348A97012F349DC24F0007");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_APRM_DeleteFF8080812F348A97012F349DC24F0007", discard).createXmlDocument();
      xmlDocument.setParameter("key", strFin_Finacc_Transaction_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Transaction_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "FF8080812F348A97012F349DC24F0007");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("FF8080812F348A97012F349DC24F0007");
        vars.removeMessage("FF8080812F348A97012F349DC24F0007");
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
      String result = "var strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar str$Element_MC=\"" +Utility.getContext(this, vars, "$Element_MC", windowId) + "\";\nvar str$Element_AY=\"" +Utility.getContext(this, vars, "$Element_AY", windowId) + "\";\nvar str$Element_SR=\"" +Utility.getContext(this, vars, "$Element_SR", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPFin_Financial_Account_ID) throws IOException, ServletException {
    TransactionData data = null;
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
            data = getEditVariables(con, vars, strPFin_Financial_Account_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.finFinaccTransactionId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (TransactionData.getCurrentDBTimestamp(this, data.finFinaccTransactionId).equals(
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
                    data.finFinaccTransactionId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Fin_Finacc_Transaction_ID", data.finFinaccTransactionId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Transaction. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
