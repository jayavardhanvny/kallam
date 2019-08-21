
package org.openbravo.erpWindows.SalesInvoice;


import org.openbravo.erpCommon.reference.*;



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

public class Lines extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Lines.class);
  
  private static final String windowId = "167";
  private static final String tabId = "270";
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
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("6E1ADD5C8B6B4ACB82237DAA8114451E")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("6E1ADD5C8B6B4ACB82237DAA8114451E");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "6E1ADD5C8B6B4ACB82237DAA8114451E";
        }
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
        String strcInvoicelineId = request.getParameter("inpcInvoicelineId");
         String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strcInvoicelineId.equals(""))
              total = saveRecord(vars, myError, 'U', strPC_Invoice_ID);
          else
              total = saveRecord(vars, myError, 'I', strPC_Invoice_ID);
          
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
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");

      String strC_InvoiceLine_ID = vars.getGlobalVariable("inpcInvoicelineId", windowId + "|C_InvoiceLine_ID", "");
            if (strPC_Invoice_ID.equals("")) {
        strPC_Invoice_ID = getParentID(vars, strC_InvoiceLine_ID);
        if (strPC_Invoice_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|C_Invoice_ID");
        vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID);

        refreshParentSession(vars, strPC_Invoice_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Lines.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strC_InvoiceLine_ID.equals("")) strC_InvoiceLine_ID = firstElement(vars, tableSQL);
          if (strC_InvoiceLine_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_InvoiceLine_ID, strPC_Invoice_ID, tableSQL);

      else printPageDataSheet(response, vars, strPC_Invoice_ID, strC_InvoiceLine_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strC_InvoiceLine_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strC_InvoiceLine_ID.equals("")) strC_InvoiceLine_ID = vars.getRequiredGlobalVariable("inpcInvoicelineId", windowId + "|C_InvoiceLine_ID");
      else vars.setSessionValue(windowId + "|C_InvoiceLine_ID", strC_InvoiceLine_ID);
      
      
      String strPC_Invoice_ID = getParentID(vars, strC_InvoiceLine_ID);
      
      vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID);
      vars.setSessionValue("263|Header.view", "EDIT");

      refreshParentSession(vars, strPC_Invoice_ID);

      vars.setSessionValue(tabId + "|Lines.view", "EDIT");

      printPageEdit(response, request, vars, false, strC_InvoiceLine_ID, strPC_Invoice_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|C_InvoiceLine_ID");
      refreshParentSession(vars, strPC_Invoice_ID);


      String strView = vars.getSessionValue(tabId + "|Lines.view");
      String strC_InvoiceLine_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strC_InvoiceLine_ID = firstElement(vars, tableSQL);
          if (strC_InvoiceLine_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strC_InvoiceLine_ID.equals("")) strC_InvoiceLine_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strC_InvoiceLine_ID, strPC_Invoice_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPC_Invoice_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamC_Invoice_ID", tabId + "|paramC_Invoice_ID");
vars.getRequestGlobalVariable("inpParamLine", tabId + "|paramLine");
vars.getRequestGlobalVariable("inpParamLineNetAmt", tabId + "|paramLineNetAmt");
vars.getRequestGlobalVariable("inpParamLine_f", tabId + "|paramLine_f");
vars.getRequestGlobalVariable("inpParamLineNetAmt_f", tabId + "|paramLineNetAmt_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      
      vars.removeSessionValue(windowId + "|C_InvoiceLine_ID");
      String strC_InvoiceLine_ID="";

      String strView = vars.getSessionValue(tabId + "|Lines.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strC_InvoiceLine_ID = firstElement(vars, tableSQL);
        if (strC_InvoiceLine_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Lines.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_InvoiceLine_ID, strPC_Invoice_ID, tableSQL);

      else printPageDataSheet(response, vars, strPC_Invoice_ID, strC_InvoiceLine_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      

      String strC_InvoiceLine_ID = vars.getGlobalVariable("inpcInvoicelineId", windowId + "|C_InvoiceLine_ID", "");
      vars.setSessionValue(tabId + "|Lines.view", "RELATION");
      printPageDataSheet(response, vars, strPC_Invoice_ID, strC_InvoiceLine_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");


      printPageEdit(response, request, vars, true, "", strPC_Invoice_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strC_InvoiceLine_ID = vars.getGlobalVariable("inpcInvoicelineId", windowId + "|C_InvoiceLine_ID", "");
      vars.setSessionValue(tabId + "|Lines.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strC_InvoiceLine_ID, strPC_Invoice_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      String strC_InvoiceLine_ID = vars.getRequiredStringParameter("inpcInvoicelineId");
      
      String strNext = nextElement(vars, strC_InvoiceLine_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      String strC_InvoiceLine_ID = vars.getRequiredStringParameter("inpcInvoicelineId");
      
      String strPrevious = previousElement(vars, strC_InvoiceLine_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      vars.setSessionValue(tabId + "|Lines.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Lines.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Lines.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|C_InvoiceLine_ID");
      vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Lines.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|C_InvoiceLine_ID");
      vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPC_Invoice_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPC_Invoice_ID);      
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
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      String strC_InvoiceLine_ID = vars.getRequiredGlobalVariable("inpcInvoicelineId", windowId + "|C_InvoiceLine_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPC_Invoice_ID);      
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
          String strNext = nextElement(vars, strC_InvoiceLine_ID, tableSQL);
          vars.setSessionValue(windowId + "|C_InvoiceLine_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      String strC_InvoiceLine_ID = vars.getRequiredStringParameter("inpcInvoicelineId");
      //LinesData data = getEditVariables(vars, strPC_Invoice_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = LinesData.delete(this, strC_InvoiceLine_ID, strPC_Invoice_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|cInvoicelineId");
        vars.setSessionValue(tabId + "|Lines.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONExplode6E1ADD5C8B6B4ACB82237DAA8114451E")) {
        vars.setSessionValue("button6E1ADD5C8B6B4ACB82237DAA8114451E.strexplode", vars.getStringParameter("inpexplode"));
        vars.setSessionValue("button6E1ADD5C8B6B4ACB82237DAA8114451E.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button6E1ADD5C8B6B4ACB82237DAA8114451E.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button6E1ADD5C8B6B4ACB82237DAA8114451E.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button6E1ADD5C8B6B4ACB82237DAA8114451E.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "6E1ADD5C8B6B4ACB82237DAA8114451E", request.getServletPath());    
     } else if (vars.commandIn("BUTTON6E1ADD5C8B6B4ACB82237DAA8114451E")) {
        String strC_InvoiceLine_ID = vars.getGlobalVariable("inpcInvoicelineId", windowId + "|C_InvoiceLine_ID", "");
        String strexplode = vars.getSessionValue("button6E1ADD5C8B6B4ACB82237DAA8114451E.strexplode");
        String strProcessing = vars.getSessionValue("button6E1ADD5C8B6B4ACB82237DAA8114451E.strProcessing");
        String strOrg = vars.getSessionValue("button6E1ADD5C8B6B4ACB82237DAA8114451E.strOrg");
        String strClient = vars.getSessionValue("button6E1ADD5C8B6B4ACB82237DAA8114451E.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonExplode6E1ADD5C8B6B4ACB82237DAA8114451E(response, vars, strC_InvoiceLine_ID, strexplode, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONExplode6E1ADD5C8B6B4ACB82237DAA8114451E")) {
        String strC_InvoiceLine_ID = vars.getGlobalVariable("inpKey", windowId + "|C_InvoiceLine_ID", "");
        @SuppressWarnings("unused")
        String strexplode = vars.getStringParameter("inpexplode");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "6E1ADD5C8B6B4ACB82237DAA8114451E", (("C_InvoiceLine_ID".equalsIgnoreCase("AD_Language"))?"0":strC_InvoiceLine_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
          ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
          new ProcessRunner(bundle).execute(this);
          
          PInstanceProcessData[] pinstanceData = PInstanceProcessData.select(this, pinstance);
          myMessage = Utility.getProcessInstanceMessage(this, vars, pinstanceData);
        } catch (ServletException ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
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
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPC_Invoice_ID);
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
  private LinesData getEditVariables(Connection con, VariablesSecureApp vars, String strPC_Invoice_ID) throws IOException,ServletException {
    LinesData data = new LinesData();
    ServletException ex = null;
    try {
   try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.financialInvoiceLine = vars.getStringParameter("inpfinancialInvoiceLine", "N");     data.mProductId = vars.getRequestGlobalVariable("inpmProductId", windowId + "|M_Product_ID");     data.emEpcgProlistalias = vars.getStringParameter("inpemEpcgProlistalias");     data.emEpcgProlistaliasr = vars.getStringParameter("inpemEpcgProlistalias_R");     data.emEpcgProductalias = vars.getStringParameter("inpemEpcgProductalias");     data.accountId = vars.getStringParameter("inpaccountId");     data.accountIdr = vars.getStringParameter("inpaccountId_R");     data.epcgPackagingId = vars.getStringParameter("inpepcgPackagingId");     data.epcgPackagingIdr = vars.getStringParameter("inpepcgPackagingId_R");    try {   data.emEpcgNoofpackages = vars.getNumericParameter("inpemEpcgNoofpackages");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.qtyinvoiced = vars.getRequiredNumericParameter("inpqtyinvoiced");  } catch (ServletException paramEx) { ex = paramEx; }     data.cUomId = vars.getStringParameter("inpcUomId");     data.cUomIdr = vars.getStringParameter("inpcUomId_R");    try {   data.priceactual = vars.getRequiredNumericParameter("inppriceactual");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrNetunitrate = vars.getNumericParameter("inpemRcfrNetunitrate");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grossUnitPrice = vars.getNumericParameter("inpgrossUnitPrice");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.linenetamt = vars.getRequiredNumericParameter("inplinenetamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.lineGrossAmount = vars.getNumericParameter("inplineGrossAmount");  } catch (ServletException paramEx) { ex = paramEx; }     data.cTaxId = vars.getStringParameter("inpcTaxId");     data.cTaxIdr = vars.getStringParameter("inpcTaxId_R");    try {   data.emRcfrNetrate = vars.getNumericParameter("inpemRcfrNetrate");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrFreight = vars.getNumericParameter("inpemRcfrFreight");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrInsurance = vars.getNumericParameter("inpemRcfrInsurance");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emEpcgNoofpacks = vars.getNumericParameter("inpemEpcgNoofpacks");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grosspricelist = vars.getRequiredNumericParameter("inpgrosspricelist");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.pricelist = vars.getRequiredNumericParameter("inppricelist");  } catch (ServletException paramEx) { ex = paramEx; }     data.mAttributesetinstanceId = vars.getStringParameter("inpmAttributesetinstanceId");     data.mAttributesetinstanceIdr = vars.getStringParameter("inpmAttributesetinstanceId_R");     data.description = vars.getStringParameter("inpdescription");     data.cOrderlineId = vars.getStringParameter("inpcOrderlineId");     data.cOrderlineIdr = vars.getStringParameter("inpcOrderlineId_R");     data.mInoutlineId = vars.getStringParameter("inpmInoutlineId");     data.mInoutlineIdr = vars.getStringParameter("inpmInoutlineId_R");     data.iseditlinenetamt = vars.getStringParameter("inpiseditlinenetamt", "N");    try {   data.taxbaseamt = vars.getNumericParameter("inptaxbaseamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.excludeforwithholding = vars.getStringParameter("inpexcludeforwithholding", "N");     data.mProductUomId = vars.getStringParameter("inpmProductUomId");     data.mProductUomIdr = vars.getStringParameter("inpmProductUomId_R");     data.isdeferred = vars.getStringParameter("inpisdeferred", "N");    try {   data.quantityorder = vars.getNumericParameter("inpquantityorder");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.pricestd = vars.getRequiredNumericParameter("inppricestd");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grosspricestd = vars.getRequiredNumericParameter("inpgrosspricestd");  } catch (ServletException paramEx) { ex = paramEx; }     data.defplantype = vars.getStringParameter("inpdefplantype");     data.defplantyper = vars.getStringParameter("inpdefplantype_R");    try {   data.periodnumber = vars.getNumericParameter("inpperiodnumber");  } catch (ServletException paramEx) { ex = paramEx; }     data.cPeriodId = vars.getStringParameter("inpcPeriodId");     data.cPeriodIdr = vars.getStringParameter("inpcPeriodId_R");     data.adOrgId = vars.getRequiredStringParameter("inpadOrgId");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.aAssetId = vars.getStringParameter("inpaAssetId");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.explode = vars.getStringParameter("inpexplode");     data.bomParentId = vars.getStringParameter("inpbomParentId");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.mOfferId = vars.getStringParameter("inpmOfferId");     data.cProjectlineId = vars.getStringParameter("inpcProjectlineId");     data.isdescription = vars.getStringParameter("inpisdescription", "N");    try {   data.taxamt = vars.getNumericParameter("inptaxamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.sResourceassignmentId = vars.getStringParameter("inpsResourceassignmentId");     data.cInvoiceDiscountId = vars.getStringParameter("inpcInvoiceDiscountId");    try {   data.pricelimit = vars.getRequiredNumericParameter("inppricelimit");  } catch (ServletException paramEx) { ex = paramEx; }     data.isactive = vars.getStringParameter("inpisactive", "N");    try {   data.chargeamt = vars.getNumericParameter("inpchargeamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.cInvoicelineId = vars.getRequestGlobalVariable("inpcInvoicelineId", windowId + "|C_InvoiceLine_ID");     data.cChargeId = vars.getStringParameter("inpcChargeId");     data.cInvoiceId = vars.getRequiredGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.cInvoiceId = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");


          vars.getGlobalVariable("inphasseconduom", windowId + "|HASSECONDUOM", "");
          vars.getGlobalVariable("inpcBpartnerId", windowId + "|C_BPARTNER_ID", "");
          vars.getGlobalVariable("inpusesalternate", windowId + "|UsesAlternate", "");
          vars.getGlobalVariable("inpparentAdOrg", windowId + "|Parent_AD_Org", "");
          vars.getGlobalVariable("inpisbom", windowId + "|isBOM", "");
          vars.getGlobalVariable("inpisstocked", windowId + "|IsStocked", "");
          vars.getGlobalVariable("inpprocessed", windowId + "|Processed", "");
          vars.getGlobalVariable("inpattrsetvaluetype", windowId + "|ATTRSETVALUETYPE", "");
          vars.getGlobalVariable("inpposted", windowId + "|Posted", "");
          vars.getGlobalVariable("inpattributeset", windowId + "|ATTRIBUTESET", "");
          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
          vars.getGlobalVariable("inpgrossprice", windowId + "|GROSSPRICE", "");
    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPC_Invoice_ID) throws IOException,ServletException {
      
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Invoice_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|DateInvoiced", data[0].dateinvoiced);    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].cBpartnerId);    vars.setSessionValue(windowId + "|DocStatus", data[0].docstatus);    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].cCurrencyId);    vars.setSessionValue(windowId + "|IsPaid", data[0].ispaid);    vars.setSessionValue(windowId + "|Posted", data[0].posted);    vars.setSessionValue(windowId + "|M_PriceList_ID", data[0].mPricelistId);    vars.setSessionValue(windowId + "|C_DocType_ID", data[0].cDoctypeId);    vars.setSessionValue(windowId + "|Totalpaid", data[0].totalpaid);    vars.setSessionValue(windowId + "|IsSOTrx", data[0].issotrx);    vars.setSessionValue(windowId + "|C_Invoice_ID", data[0].cInvoiceId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|PromotionsDefined", HeaderData.selectAux1459AA66723E4905BE05C09DC757DA6E(this, ((dataField!=null)?dataField.getField("adClientId"):((data==null || data.length==0)?"":data[0].adClientId))));
      
      vars.setSessionValue(windowId + "|DOCBASETYPE", HeaderData.selectAux7A6DD0A1AF304BE288BBFBE305EA1227(this, Utility.getContext(this, vars, "c_doctypetarget_id", "167")));
      
      vars.setSessionValue(windowId + "|VoidAutomaticallyCreated", HeaderData.selectAuxB54EEEFACAD4427ABE5F88D0C27524B5(this, strPC_Invoice_ID));
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strC_InvoiceLine_ID) throws ServletException {
    String strPC_Invoice_ID = LinesData.selectParentID(this, strC_InvoiceLine_ID);
    if (strPC_Invoice_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strC_InvoiceLine_ID);
      throw new ServletException("Parent record not found");
    }
    return strPC_Invoice_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|M_Product_ID", data[0].getField("mProductId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|C_Invoice_ID", data[0].getField("cInvoiceId"));    vars.setSessionValue(windowId + "|C_InvoiceLine_ID", data[0].getField("cInvoicelineId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPC_Invoice_ID) throws IOException,ServletException {
      LinesData[] data = LinesData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Invoice_ID, vars.getStringParameter("inpcInvoicelineId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPC_Invoice_ID, String strC_InvoiceLine_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamC_Invoice_ID = vars.getSessionValue(tabId + "|paramC_Invoice_ID");
String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamLineNetAmt = vars.getSessionValue(tabId + "|paramLineNetAmt");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");
String strParamLineNetAmt_f = vars.getSessionValue(tabId + "|paramLineNetAmt_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamC_Invoice_ID) && ("").equals(strParamLine) && ("").equals(strParamLineNetAmt) && ("").equals(strParamLine_f) && ("").equals(strParamLineNetAmt_f)) || !(("").equals(strParamC_Invoice_ID) || ("%").equals(strParamC_Invoice_ID))  || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamLineNetAmt) || ("%").equals(strParamLineNetAmt))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f))  || !(("").equals(strParamLineNetAmt_f) || ("%").equals(strParamLineNetAmt_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strC_InvoiceLine_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strC_InvoiceLine_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/SalesInvoice/Lines_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Lines", false, "document.frmMain.inpcInvoicelineId", "grid", "..", "".equals("Y"), "SalesInvoice", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPC_Invoice_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("2986", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "cInvoicelineId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines_Relation.html", "SalesInvoice", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", LinesData.selectParent(this, strPC_Invoice_ID));
    else xmlDocument.setParameter("parent", LinesData.selectParentTrl(this, strPC_Invoice_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strC_InvoiceLine_ID, String strPC_Invoice_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = "  ( COALESCE(TO_CHAR(list2.name),'') ), C_InvoiceLine.QuantityOrder, C_InvoiceLine.Line";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    LinesData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamC_Invoice_ID = vars.getSessionValue(tabId + "|paramC_Invoice_ID");
String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamLineNetAmt = vars.getSessionValue(tabId + "|paramLineNetAmt");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");
String strParamLineNetAmt_f = vars.getSessionValue(tabId + "|paramLineNetAmt_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamC_Invoice_ID) && ("").equals(strParamLine) && ("").equals(strParamLineNetAmt) && ("").equals(strParamLine_f) && ("").equals(strParamLineNetAmt_f)) || !(("").equals(strParamC_Invoice_ID) || ("%").equals(strParamC_Invoice_ID))  || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamLineNetAmt) || ("%").equals(strParamLineNetAmt))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f))  || !(("").equals(strParamLineNetAmt_f) || ("%").equals(strParamLineNetAmt_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = LinesData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Invoice_ID, strC_InvoiceLine_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strC_InvoiceLine_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new LinesData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("cInvoicelineId") == null || dataField.getField("cInvoicelineId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strHASSECONDUOM = LinesData.selectAux800004(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|HASSECONDUOM", strHASSECONDUOM);
        String strC_BPARTNER_ID = ((dataField!=null)?dataField.getField("cBpartnerId"):((data==null || data.length==0)?"":data[0].cBpartnerId));
    vars.setSessionValue(windowId + "|C_BPARTNER_ID", strC_BPARTNER_ID);
        String strUsesAlternate = LinesData.selectAux1F06154C94734F81A968E51738CC7B62(this, ((dataField!=null)?dataField.getField("cTaxId"):((data==null || data.length==0)?"":data[0].cTaxId)));
    vars.setSessionValue(windowId + "|UsesAlternate", strUsesAlternate);
        String strParent_AD_Org = LinesData.selectAux231D1034C3D2450C969AEFCA7B680A8E(this, strPC_Invoice_ID);
    vars.setSessionValue(windowId + "|Parent_AD_Org", strParent_AD_Org);
        String strisBOM = LinesData.selectAux3EE2062DF62F4E948FD9786D21AF2D0C(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|isBOM", strisBOM);
        String strIsStocked = LinesData.selectAux467629226B744AD5A9D5CEBD284C29E4(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|IsStocked", strIsStocked);
        String strProcessed = LinesData.selectAux4D9613116EAA46A585AEDB75237D5140(this, strPC_Invoice_ID);
    vars.setSessionValue(windowId + "|Processed", strProcessed);
        String strATTRSETVALUETYPE = LinesData.selectAux51568B79156646C8966F49F82EE51CB7(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|ATTRSETVALUETYPE", strATTRSETVALUETYPE);
        String strPosted = LinesData.selectAux571778C5E2AD4A13BDD7DD15A4DA470E(this, strPC_Invoice_ID);
    vars.setSessionValue(windowId + "|Posted", strPosted);
        String strATTRIBUTESET = LinesData.selectAux637CCFE37ADD45B5810D3490DF33CF2E(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|ATTRIBUTESET", strATTRIBUTESET);
        String strDOCBASETYPE = LinesData.selectAuxB727417F9385474AB816BA585B1DAD74(this, strPC_Invoice_ID);
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
        String strGROSSPRICE = LinesData.selectAuxE852BFC21B4C468C9E3F22901A6ED75C(this, Utility.getContext(this, vars, "M_PRICELIST_ID", "167"));
    vars.setSessionValue(windowId + "|GROSSPRICE", strGROSSPRICE);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPC_Invoice_ID);
        data = LinesData.set(strPC_Invoice_ID, Utility.getDefault(this, vars, "EM_Rcfr_Insurance", "", "167", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Productalias", "", "167", "", dataField), Utility.getDefault(this, vars, "Excludeforwithholding", "", "167", "N", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "167", "", dataField), LinesData.selectDef1655799099FD4E7A9EB982F98A5C8066_0(this, Utility.getDefault(this, vars, "C_Bpartner_ID", "", "167", "", dataField)), Utility.getDefault(this, vars, "C_Period_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "Financial_Invoice_Line", "N", "167", "N", dataField), Utility.getDefault(this, vars, "DefPlanType", "", "167", "", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_Client_ID@", "167", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "167", "", dataField), "Y", Utility.getDefault(this, vars, "Created", "", "167", "", dataField), Utility.getDefault(this, vars, "CreatedBy", "", "167", "", dataField), LinesData.selectDef3833_1(this, Utility.getDefault(this, vars, "CreatedBy", "", "167", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "167", "", dataField), LinesData.selectDef3835_2(this, Utility.getDefault(this, vars, "UpdatedBy", "", "167", "", dataField)), Utility.getDefault(this, vars, "C_OrderLine_ID", "", "167", "", dataField), LinesData.selectDef3837_3(this, Utility.getDefault(this, vars, "C_OrderLine_ID", "", "167", "", dataField)), LinesData.selectDef3838(this, strPC_Invoice_ID), Utility.getDefault(this, vars, "Description", "", "167", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "QtyInvoiced", "1", "167", "0", dataField), Utility.getDefault(this, vars, "PriceList", "", "167", "0", dataField), Utility.getDefault(this, vars, "PriceActual", "", "167", "0", dataField), Utility.getDefault(this, vars, "LineNetAmt", "", "167", "0", dataField), Utility.getDefault(this, vars, "C_Charge_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "ChargeAmt", "", "167", "", dataField), Utility.getDefault(this, vars, "C_UOM_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "C_Tax_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Noofpacks", "", "167", "", dataField), Utility.getDefault(this, vars, "M_InOutLine_ID", "", "167", "", dataField), LinesData.selectDef4251_4(this,  vars.getLanguage(), Utility.getDefault(this, vars, "M_InOutLine_ID", "", "167", "", dataField)), Utility.getDefault(this, vars, "PriceLimit", "", "167", "0", dataField), Utility.getDefault(this, vars, "Grosspricelist", "", "167", "0", dataField), Utility.getDefault(this, vars, "EM_Epcg_Noofpackages", "", "167", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Netrate", "", "167", "", dataField), Utility.getDefault(this, vars, "Epcg_Packaging_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "Periodnumber", "", "167", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Netunitrate", "", "167", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Freight", "", "167", "", dataField), Utility.getDefault(this, vars, "S_ResourceAssignment_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "Gross_Unit_Price", "0", "167", "", dataField), Utility.getDefault(this, vars, "Iseditlinenetamt", "N", "167", "N", dataField), Utility.getDefault(this, vars, "Taxbaseamt", "", "167", "", dataField), Utility.getDefault(this, vars, "QuantityOrder", "", "167", "", dataField), Utility.getDefault(this, vars, "M_Product_Uom_Id", "", "167", "", dataField), Utility.getDefault(this, vars, "C_Invoice_Discount_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "C_Projectline_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "M_Offer_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "PriceStd", "0", "167", "0", dataField), Utility.getDefault(this, vars, "TaxAmt", "", "167", "", dataField), Utility.getDefault(this, vars, "M_AttributeSetInstance_ID", "", "167", "", dataField), LinesData.selectDef8553_5(this, Utility.getDefault(this, vars, "M_AttributeSetInstance_ID", "", "167", "", dataField)), Utility.getDefault(this, vars, "A_Asset_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "grosspricestd", "0", "167", "0", dataField), Utility.getDefault(this, vars, "IsDescription", "N", "167", "N", dataField), Utility.getDefault(this, vars, "IsDeferred", "N", "167", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Prolistalias", "", "167", "", dataField), Utility.getDefault(this, vars, "Line_Gross_Amount", "0", "167", "", dataField), Utility.getDefault(this, vars, "Explode", "", "167", "N", dataField), Utility.getDefault(this, vars, "BOM_Parent_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "167", "", dataField), LinesData.selectDefE9065F7B304E419787AD6EB080A54143_6(this, Utility.getDefault(this, vars, "C_Project_ID", "", "167", "", dataField)), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "Account_ID", "", "167", "", dataField));
        
      }
     }
      
    String currentPOrg=HeaderData.selectOrg(this, strPC_Invoice_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/SalesInvoice/Lines_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/SalesInvoice/Lines_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Lines", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpcInvoicelineId", "", "..", "".equals("Y"), "SalesInvoice", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strC_InvoiceLine_ID), !hasReadOnlyAccess);
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
      // if (!strC_InvoiceLine_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines_Relation.html", "SalesInvoice", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("HASSECONDUOM", strHASSECONDUOM);
        xmlDocument.setParameter("C_BPARTNER_ID", strC_BPARTNER_ID);
        xmlDocument.setParameter("UsesAlternate", strUsesAlternate);
        xmlDocument.setParameter("Parent_AD_Org", strParent_AD_Org);
        xmlDocument.setParameter("isBOM", strisBOM);
        xmlDocument.setParameter("IsStocked", strIsStocked);
        xmlDocument.setParameter("Processed", strProcessed);
        xmlDocument.setParameter("ATTRSETVALUETYPE", strATTRSETVALUETYPE);
        xmlDocument.setParameter("Posted", strPosted);
        xmlDocument.setParameter("ATTRIBUTESET", strATTRIBUTESET);
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
        xmlDocument.setParameter("GROSSPRICE", strGROSSPRICE);
    
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
comboTableData = new ComboTableData(vars, this, "17", "EM_Epcg_Prolistalias", "50F1BCA501DF41C095D92E2AC4EB6AA4", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("emEpcgProlistalias"):dataField.getField("emEpcgProlistalias")));
xmlDocument.setData("reportEM_Epcg_Prolistalias","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Account_ID", "2F57E5B5EBF44E0A9D45492DECBD3CC1", "58401EEE65CC4551BEC649A16FA16479", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("accountId"):dataField.getField("accountId")));
xmlDocument.setData("reportAccount_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Epcg_Packaging_ID", "8CEADB212B5441A0B392E9D3498E9B4D", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgPackagingId"):dataField.getField("epcgPackagingId")));
xmlDocument.setData("reportEpcg_Packaging_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonEM_Epcg_Noofpackages", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQtyInvoiced", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_UOM_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomId"):dataField.getField("cUomId")));
xmlDocument.setData("reportC_UOM_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonPriceActual", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Netunitrate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGross_Unit_Price", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonLineNetAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonLine_Gross_Amount", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "C_Tax_ID", "158", "5BEA56148DC74215A63927481BDC0B63", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cTaxId"):dataField.getField("cTaxId")));
xmlDocument.setData("reportC_Tax_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonEM_Rcfr_Netrate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Freight", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Insurance", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGrosspricelist", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPriceList", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTaxbaseamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "M_Product_Uom_Id", "800000", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mProductUomId"):dataField.getField("mProductUomId")));
xmlDocument.setData("reportM_Product_Uom_Id","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonQuantityOrder", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPriceStd", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttongrosspricestd", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "17", "DefPlanType", "73625A8F22EF4CD7808603156BA606D7", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("defplantype"):dataField.getField("defplantype")));
xmlDocument.setData("reportDefPlanType","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "C_Period_ID", "233", "E0541EB2493547BC934F5EFA044AB668", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cPeriodId"):dataField.getField("cPeriodId")));
xmlDocument.setData("reportC_Period_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
String userOrgList = "";
if (editableTab) 
  userOrgList= Utility.getReferenceableOrg(this, vars, currentPOrg, windowId, accesslevel); //referenceable from parent org, only the writeable orgs
else 
  userOrgList=currentOrg;
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "53AE60A473D2460D8663D7A1BC5BA732", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Explode_BTNname", Utility.getButtonName(this, vars, "D5C2F8F063FAD47CE040007F01004029", "Explode_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalExplode = org.openbravo.erpCommon.utility.Utility.isModalProcess("6E1ADD5C8B6B4ACB82237DAA8114451E"); 
xmlDocument.setParameter("Explode_Modal", modalExplode?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("buttonPriceLimit", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("buttonTaxAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonChargeAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
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

    private void printPageButtonExplode6E1ADD5C8B6B4ACB82237DAA8114451E(HttpServletResponse response, VariablesSecureApp vars, String strC_InvoiceLine_ID, String strexplode, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6E1ADD5C8B6B4ACB82237DAA8114451E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Explode6E1ADD5C8B6B4ACB82237DAA8114451E", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_InvoiceLine_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Lines_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "6E1ADD5C8B6B4ACB82237DAA8114451E");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("6E1ADD5C8B6B4ACB82237DAA8114451E");
        vars.removeMessage("6E1ADD5C8B6B4ACB82237DAA8114451E");
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
      String result = "var strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar str$Element_AS=\"" +Utility.getContext(this, vars, "$Element_AS", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPC_Invoice_ID) throws IOException, ServletException {
    LinesData data = null;
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
            data = getEditVariables(con, vars, strPC_Invoice_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.cInvoicelineId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (LinesData.getCurrentDBTimestamp(this, data.cInvoicelineId).equals(
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
                    data.cInvoicelineId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|C_InvoiceLine_ID", data.cInvoicelineId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Lines. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
