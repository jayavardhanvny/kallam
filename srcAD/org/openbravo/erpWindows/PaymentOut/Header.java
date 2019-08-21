
package org.openbravo.erpWindows.PaymentOut;


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

public class Header extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Header.class);
  
  private static final String windowId = "6F8F913FA60F4CBD93DC1D3AA696E76E";
  private static final String tabId = "F7A52FDAAA0346EFA07D53C125B40404";
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
     
      if (command.contains("6255BE488882480599C81284B70CD9B3")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("6255BE488882480599C81284B70CD9B3");
        SessionInfo.setModuleId("A918E3331C404B889D69AA9BFAFB23AC");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("6255BE488882480599C81284B70CD9B3")) {
        classInfo.type = "P";
        classInfo.id = "6255BE488882480599C81284B70CD9B3";
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
        String strfinPaymentId = request.getParameter("inpfinPaymentId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strfinPaymentId.equals(""))
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

      String strFin_Payment_ID = vars.getGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strFin_Payment_ID.equals("")) strFin_Payment_ID = firstElement(vars, tableSQL);
          if (strFin_Payment_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strFin_Payment_ID, tableSQL);

      else printPageDataSheet(response, vars, strFin_Payment_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strFin_Payment_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strFin_Payment_ID.equals("")) strFin_Payment_ID = vars.getRequiredGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID");
      else vars.setSessionValue(windowId + "|Fin_Payment_ID", strFin_Payment_ID);
      
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      printPageEdit(response, request, vars, false, strFin_Payment_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|Header.view");
      String strFin_Payment_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strFin_Payment_ID = firstElement(vars, tableSQL);
          if (strFin_Payment_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strFin_Payment_ID.equals("")) strFin_Payment_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strFin_Payment_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");
vars.getRequestGlobalVariable("inpParamPaymentdate", tabId + "|paramPaymentdate");
vars.getRequestGlobalVariable("inpParamC_Bpartner_ID", tabId + "|paramC_Bpartner_ID");
vars.getRequestGlobalVariable("inpParamFin_Financial_Account_ID", tabId + "|paramFin_Financial_Account_ID");
vars.getRequestGlobalVariable("inpParamFin_Paymentmethod_ID", tabId + "|paramFin_Paymentmethod_ID");
vars.getRequestGlobalVariable("inpParamPaymentdate_f", tabId + "|paramPaymentdate_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Fin_Payment_ID");
      String strFin_Payment_ID="";

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strFin_Payment_ID = firstElement(vars, tableSQL);
        if (strFin_Payment_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Header.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strFin_Payment_ID, tableSQL);

      else printPageDataSheet(response, vars, strFin_Payment_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strFin_Payment_ID = vars.getGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "RELATION");
      printPageDataSheet(response, vars, strFin_Payment_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strFin_Payment_ID = vars.getGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strFin_Payment_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strFin_Payment_ID = vars.getRequiredStringParameter("inpfinPaymentId");
      
      String strNext = nextElement(vars, strFin_Payment_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strFin_Payment_ID = vars.getRequiredStringParameter("inpfinPaymentId");
      
      String strPrevious = previousElement(vars, strFin_Payment_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|Header.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|Header.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Header.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Header.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Fin_Payment_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|Header.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Header.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Fin_Payment_ID");

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

      String strFin_Payment_ID = vars.getRequiredGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID");
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
          String strNext = nextElement(vars, strFin_Payment_ID, tableSQL);
          vars.setSessionValue(windowId + "|Fin_Payment_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strFin_Payment_ID = vars.getRequiredStringParameter("inpfinPaymentId");
      //HeaderData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = HeaderData.delete(this, strFin_Payment_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|finPaymentId");
        vars.setSessionValue(tabId + "|Header.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONEM_APRM_Process_Payment6255BE488882480599C81284B70CD9B3")) {
        vars.setSessionValue("button6255BE488882480599C81284B70CD9B3.stremAprmProcessPayment", vars.getStringParameter("inpemAprmProcessPayment"));
        vars.setSessionValue("button6255BE488882480599C81284B70CD9B3.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button6255BE488882480599C81284B70CD9B3.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button6255BE488882480599C81284B70CD9B3.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("EM_APRM_Process_Payment", vars.getStringParameter("inpemAprmProcessPayment"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button6255BE488882480599C81284B70CD9B3.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "6255BE488882480599C81284B70CD9B3", request.getServletPath());
      } else if (vars.commandIn("BUTTON6255BE488882480599C81284B70CD9B3")) {
        String strFin_Payment_ID = vars.getGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID", "");
        String stremAprmProcessPayment = vars.getSessionValue("button6255BE488882480599C81284B70CD9B3.stremAprmProcessPayment");
        String strProcessing = vars.getSessionValue("button6255BE488882480599C81284B70CD9B3.strProcessing");
        String strOrg = vars.getSessionValue("button6255BE488882480599C81284B70CD9B3.strOrg");
        String strClient = vars.getSessionValue("button6255BE488882480599C81284B70CD9B3.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_APRM_Process_Payment6255BE488882480599C81284B70CD9B3(response, vars, strFin_Payment_ID, stremAprmProcessPayment, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_APRM_Process_Payment6255BE488882480599C81284B70CD9B3")) {
        String strFin_Payment_ID = vars.getGlobalVariable("inpKey", windowId + "|Fin_Payment_ID", "");
        
        ProcessBundle pb = new ProcessBundle("6255BE488882480599C81284B70CD9B3", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Fin_Payment_ID", strFin_Payment_ID);
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
        String strFin_Payment_ID = vars.getGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID", "");
        String strTableId = "D1A97202E832470285C9B1EB026D54E2";
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
          vars.setSessionValue("Posted|key", strFin_Payment_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "Header");
          response.sendRedirect(strDireccion + "/ad_actionButton/Posted.html");
        }



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
  private HeaderData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    HeaderData data = new HeaderData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.referenceno = vars.getStringParameter("inpreferenceno");     data.paymentdate = vars.getStringParameter("inppaymentdate");     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.description = vars.getStringParameter("inpdescription");     data.finPaymentmethodId = vars.getRequiredStringParameter("inpfinPaymentmethodId");     data.finPaymentmethodIdr = vars.getStringParameter("inpfinPaymentmethodId_R");     data.finFinancialAccountId = vars.getRequiredStringParameter("inpfinFinancialAccountId");     data.finFinancialAccountIdr = vars.getStringParameter("inpfinFinancialAccountId_R");     data.epcgCblinesId = vars.getStringParameter("inpepcgCblinesId");     data.epcgCblinesIdr = vars.getStringParameter("inpepcgCblinesId_R");     data.cCurrencyId = vars.getRequiredStringParameter("inpcCurrencyId");     data.cCurrencyIdr = vars.getStringParameter("inpcCurrencyId_R");    try {   data.finaccTxnAmount = vars.getRequiredNumericParameter("inpfinaccTxnAmount");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.finaccTxnConvertRate = vars.getRequiredNumericParameter("inpfinaccTxnConvertRate");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.generatedCredit = vars.getNumericParameter("inpgeneratedCredit");  } catch (ServletException paramEx) { ex = paramEx; }     data.emEpcgLrno = vars.getStringParameter("inpemEpcgLrno");     data.emEpcgVehicle = vars.getStringParameter("inpemEpcgVehicle");     data.emAprmAddScheduledpayments = vars.getStringParameter("inpemAprmAddScheduledpayments");     data.posted = vars.getStringParameter("inpposted");     data.emAprmProcessPayment = vars.getRequestGlobalVariable("inpemAprmProcessPayment", windowId + "|EM_APRM_Process_Payment");     data.emAprmExecutepayment = vars.getStringParameter("inpemAprmExecutepayment");    try {   data.amount = vars.getRequiredNumericParameter("inpamount");  } catch (ServletException paramEx) { ex = paramEx; }     data.status = vars.getRequiredGlobalVariable("inpstatus", windowId + "|Status");    try {   data.usedCredit = vars.getNumericParameter("inpusedCredit");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.writeoffamt = vars.getRequiredNumericParameter("inpwriteoffamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.emAprmReversepayment = vars.getRequiredStringParameter("inpemAprmReversepayment");     data.finRevPaymentId = vars.getRequestGlobalVariable("inpfinRevPaymentId", windowId + "|FIN_Rev_Payment_ID");     data.createdbyalgorithm = vars.getStringParameter("inpcreatedbyalgorithm", "N");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.cActivityId = vars.getStringParameter("inpcActivityId");     data.cCampaignId = vars.getStringParameter("inpcCampaignId");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.emEpcgChecktype = vars.getStringParameter("inpemEpcgChecktype");     data.processed = vars.getStringParameter("inpprocessed", "N");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.finPaymentId = vars.getRequestGlobalVariable("inpfinPaymentId", windowId + "|Fin_Payment_ID");     data.emAprmReconcilePayment = vars.getStringParameter("inpemAprmReconcilePayment");     data.isreceipt = vars.getStringParameter("inpisreceipt", "N");     data.processing = vars.getStringParameter("inpprocessing", "N"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
          vars.getGlobalVariable("inpfinancialaccountcurrencyid", windowId + "|FinancialAccountCurrencyId", "");
          vars.getGlobalVariable("inpfinIsreceipt", windowId + "|FIN_ISRECEIPT", "");
          vars.getGlobalVariable("inpismulticurrencyenabled", windowId + "|IsMultiCurrencyEnabled", "");
    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "FIN_Payment", "", data.cDoctypeId, false, true);

    
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|EM_APRM_Process_Payment", data[0].getField("emAprmProcessPayment"));    vars.setSessionValue(windowId + "|Status", data[0].getField("status"));    vars.setSessionValue(windowId + "|FIN_Rev_Payment_ID", data[0].getField("finRevPaymentId"));    vars.setSessionValue(windowId + "|FIN_Payment_ID", data[0].getField("finPaymentId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpfinPaymentId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strFin_Payment_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamPaymentdate = vars.getSessionValue(tabId + "|paramPaymentdate");
String strParamC_Bpartner_ID = vars.getSessionValue(tabId + "|paramC_Bpartner_ID");
String strParamFin_Financial_Account_ID = vars.getSessionValue(tabId + "|paramFin_Financial_Account_ID");
String strParamFin_Paymentmethod_ID = vars.getSessionValue(tabId + "|paramFin_Paymentmethod_ID");
String strParamPaymentdate_f = vars.getSessionValue(tabId + "|paramPaymentdate_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo) && ("").equals(strParamPaymentdate) && ("").equals(strParamC_Bpartner_ID) && ("").equals(strParamFin_Financial_Account_ID) && ("").equals(strParamFin_Paymentmethod_ID) && ("").equals(strParamPaymentdate_f)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamPaymentdate) || ("%").equals(strParamPaymentdate))  || !(("").equals(strParamC_Bpartner_ID) || ("%").equals(strParamC_Bpartner_ID))  || !(("").equals(strParamFin_Financial_Account_ID) || ("%").equals(strParamFin_Financial_Account_ID))  || !(("").equals(strParamFin_Paymentmethod_ID) || ("%").equals(strParamFin_Paymentmethod_ID))  || !(("").equals(strParamPaymentdate_f) || ("%").equals(strParamPaymentdate_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strFin_Payment_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strFin_Payment_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PaymentOut/Header_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Header", false, "document.frmMain.inpfinPaymentId", "grid", "../payments/print.html", "N".equals("Y"), "PaymentOut", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "finPaymentId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "PaymentOut", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Header_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strFin_Payment_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " FIN_Payment.DocumentNo DESC";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    HeaderData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamPaymentdate = vars.getSessionValue(tabId + "|paramPaymentdate");
String strParamC_Bpartner_ID = vars.getSessionValue(tabId + "|paramC_Bpartner_ID");
String strParamFin_Financial_Account_ID = vars.getSessionValue(tabId + "|paramFin_Financial_Account_ID");
String strParamFin_Paymentmethod_ID = vars.getSessionValue(tabId + "|paramFin_Paymentmethod_ID");
String strParamPaymentdate_f = vars.getSessionValue(tabId + "|paramPaymentdate_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo) && ("").equals(strParamPaymentdate) && ("").equals(strParamC_Bpartner_ID) && ("").equals(strParamFin_Financial_Account_ID) && ("").equals(strParamFin_Paymentmethod_ID) && ("").equals(strParamPaymentdate_f)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamPaymentdate) || ("%").equals(strParamPaymentdate))  || !(("").equals(strParamC_Bpartner_ID) || ("%").equals(strParamC_Bpartner_ID))  || !(("").equals(strParamFin_Financial_Account_ID) || ("%").equals(strParamFin_Financial_Account_ID))  || !(("").equals(strParamFin_Paymentmethod_ID) || ("%").equals(strParamFin_Paymentmethod_ID))  || !(("").equals(strParamPaymentdate_f) || ("%").equals(strParamPaymentdate_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strFin_Payment_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strFin_Payment_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new HeaderData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("finPaymentId") == null || dataField.getField("finPaymentId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strDOCBASETYPE = "APP";
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
        String strFinancialAccountCurrencyId = HeaderData.selectAux40289DEF2E1E98E4012E1EE6C6CD002E(this, ((dataField!=null)?dataField.getField("finFinancialAccountId"):((data==null || data.length==0)?"":data[0].finFinancialAccountId)));
    vars.setSessionValue(windowId + "|FinancialAccountCurrencyId", strFinancialAccountCurrencyId);
        String strFIN_ISRECEIPT = "N";
    vars.setSessionValue(windowId + "|FIN_ISRECEIPT", strFIN_ISRECEIPT);
        String strIsMultiCurrencyEnabled = HeaderData.selectAuxFF8080812E26BF58012E27DC86DE016C(this, ((dataField!=null)?dataField.getField("finFinancialAccountId"):((data==null || data.length==0)?"":data[0].finFinancialAccountId)), ((dataField!=null)?dataField.getField("finPaymentmethodId"):((data==null || data.length==0)?"":data[0].finPaymentmethodId)));
    vars.setSessionValue(windowId + "|IsMultiCurrencyEnabled", strIsMultiCurrencyEnabled);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = HeaderData.set(Utility.getDefault(this, vars, "Fin_Financial_Account_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "CreatedByAlgorithm", "N", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), Utility.getDefault(this, vars, "Finacc_Txn_Convert_Rate", "1.0", "6F8F913FA60F4CBD93DC1D3AA696E76E", "0", dataField), Utility.getDefault(this, vars, "Finacc_Txn_Amount", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "0", dataField), Utility.getDefault(this, vars, "EM_Epcg_Lrno", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@#AD_Org_ID@", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), HeaderData.selectDef7891269C83D4655DE040007F010155CE_0(this, Utility.getDefault(this, vars, "Createdby", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField)), Utility.getDefault(this, vars, "Updatedby", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), HeaderData.selectDef7891269C83D6655DE040007F010155CE_1(this, Utility.getDefault(this, vars, "Updatedby", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField)), "Y", Utility.getDefault(this, vars, "Isreceipt", "@FIN_ISRECEIPT@", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), HeaderData.selectDef7891269C83D9655DE040007F010155CE_2(this, Utility.getDefault(this, vars, "C_Bpartner_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField)), Utility.getDefault(this, vars, "Paymentdate", "@#Date@", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "Amount", "0", "6F8F913FA60F4CBD93DC1D3AA696E76E", "0", dataField), Utility.getDefault(this, vars, "Writeoffamt", "0", "6F8F913FA60F4CBD93DC1D3AA696E76E", "0", dataField), Utility.getDefault(this, vars, "DocumentNo", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "Referenceno", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "Status", "RPAP", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "Processed", "N", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), Utility.getDefault(this, vars, "Processing", "N", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), Utility.getDefault(this, vars, "Posted", "N", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField))), Utility.getDefault(this, vars, "Fin_Paymentmethod_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "EM_APRM_Process_Payment", "P", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "36972531DA994BB38ECB91993058282F", Utility.getDefault(this, vars, "EM_APRM_Process_Payment", "P", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "36972531DA994BB38ECB91993058282F", Utility.getDefault(this, vars, "EM_APRM_Process_Payment", "P", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField))), Utility.getDefault(this, vars, "EM_APRM_Reconcile_Payment", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), Utility.getDefault(this, vars, "EM_Aprm_Add_Scheduledpayments", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), HeaderData.selectDef8032E178607750D5E040007F01004282(this, Utility.getContext(this, vars, "AD_Org_ID", "6F8F913FA60F4CBD93DC1D3AA696E76E"), Utility.getContext(this, vars, "#AD_Client_ID", windowId), Utility.getContext(this, vars, "Isreceipt", "6F8F913FA60F4CBD93DC1D3AA696E76E")), Utility.getDefault(this, vars, "C_Project_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), HeaderData.selectDef828EE0AE800D5FA1E040007F010067C7_3(this, Utility.getDefault(this, vars, "C_Project_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField)), Utility.getDefault(this, vars, "C_Campaign_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "C_Activity_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Vehicle", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "EM_Aprm_Executepayment", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), Utility.getDefault(this, vars, "Description", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "Generated_Credit", "0", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "Used_Credit", "0", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "Epcg_Cblines_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "EM_APRM_ReversePayment", "N", "6F8F913FA60F4CBD93DC1D3AA696E76E", "N", dataField), Utility.getDefault(this, vars, "FIN_Rev_Payment_ID", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Checktype", "", "6F8F913FA60F4CBD93DC1D3AA696E76E", "", dataField));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "FIN_Payment", "", data[0].cDoctypeId, false, false) + ">";
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PaymentOut/Header_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PaymentOut/Header_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Header", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpfinPaymentId", "", "../payments/print.html", "N".equals("Y"), "PaymentOut", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strFin_Payment_ID), !hasReadOnlyAccess);
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
      // if (!strFin_Payment_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "PaymentOut", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Header_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
        xmlDocument.setParameter("FinancialAccountCurrencyId", strFinancialAccountCurrencyId);
        xmlDocument.setParameter("FIN_ISRECEIPT", strFIN_ISRECEIPT);
        xmlDocument.setParameter("IsMultiCurrencyEnabled", strIsMultiCurrencyEnabled);
    
    
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
comboTableData = new ComboTableData(vars, this, "19", "C_DocType_ID", "", "49A6E01F27DB4D83939B1C34180837CC", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_DocType_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Paymentdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "Fin_Paymentmethod_ID", "", "FF80808130B107670130B118E5F60020", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finPaymentmethodId"):dataField.getField("finPaymentmethodId")));
xmlDocument.setData("reportFin_Paymentmethod_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Fin_Financial_Account_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finFinancialAccountId"):dataField.getField("finFinancialAccountId")));
xmlDocument.setData("reportFin_Financial_Account_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Epcg_Cblines_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgCblinesId"):dataField.getField("epcgCblinesId")));
xmlDocument.setData("reportEpcg_Cblines_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Currency_ID", "", "DC79E946854746F1A7965DCADF1D76FE", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCurrencyId"):dataField.getField("cCurrencyId")));
xmlDocument.setData("reportC_Currency_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonFinacc_Txn_Amount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonFinacc_Txn_Convert_Rate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGenerated_Credit", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("EM_Aprm_Add_Scheduledpayments_BTNname", Utility.getButtonName(this, vars, "80D55E34FCEE37C9E040007F01001C25", "EM_Aprm_Add_Scheduledpayments_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_Aprm_Add_Scheduledpayments = org.openbravo.erpCommon.utility.Utility.isModalProcess("12016E2EDE0245B5A36B84CBA144DBDF"); 
xmlDocument.setParameter("EM_Aprm_Add_Scheduledpayments_Modal", modalEM_Aprm_Add_Scheduledpayments?"true":"false");
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
xmlDocument.setParameter("EM_APRM_Process_Payment_BTNname", Utility.getButtonName(this, vars, "36972531DA994BB38ECB91993058282F", (dataField==null?data[0].getField("emAprmProcessPayment"):dataField.getField("emAprmProcessPayment")), "EM_APRM_Process_Payment_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_APRM_Process_Payment = org.openbravo.erpCommon.utility.Utility.isModalProcess("6255BE488882480599C81284B70CD9B3"); 
xmlDocument.setParameter("EM_APRM_Process_Payment_Modal", modalEM_APRM_Process_Payment?"true":"false");
xmlDocument.setParameter("EM_Aprm_Executepayment_BTNname", Utility.getButtonName(this, vars, "87AA1068F11242CAE040007F010011D5", "EM_Aprm_Executepayment_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_Aprm_Executepayment = org.openbravo.erpCommon.utility.Utility.isModalProcess("E011F492B0814A74B63CD1F3B9FF0526"); 
xmlDocument.setParameter("EM_Aprm_Executepayment_Modal", modalEM_Aprm_Executepayment?"true":"false");
xmlDocument.setParameter("buttonAmount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonUsed_Credit", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWriteoffamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
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



    void printPageButtonEM_APRM_Process_Payment6255BE488882480599C81284B70CD9B3(HttpServletResponse response, VariablesSecureApp vars, String strFin_Payment_ID, String stremAprmProcessPayment, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6255BE488882480599C81284B70CD9B3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_APRM_Process_Payment6255BE488882480599C81284B70CD9B3", discard).createXmlDocument();
      xmlDocument.setParameter("key", strFin_Payment_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "6255BE488882480599C81284B70CD9B3");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("6255BE488882480599C81284B70CD9B3");
        vars.removeMessage("6255BE488882480599C81284B70CD9B3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "EM_APRM_Process_Payment", "6F8F913FA60F4CBD93DC1D3AA696E76E"));
    comboTableData = new ComboTableData(vars, this, "17", "action", "36972531DA994BB38ECB91993058282F", "575E470ABADB4C278132C957A78C47E3", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button6255BE488882480599C81284B70CD9B3.originalParams"), comboTableData, windowId, Utility.getContext(this, vars, "EM_APRM_Process_Payment", "6F8F913FA60F4CBD93DC1D3AA696E76E"));
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
      String result = "var strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
      return result;
    }


    private String getReadOnlyLogicContext(VariablesSecureApp vars) throws IOException, ServletException {
      log4j.debug("Output: Read Only logic context fields");
      String result = "var strNotAllowChangeExchange=\"" + Utility.getContext(this, vars, "NotAllowChangeExchange", windowId) + "\";\n";
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
    HeaderData data = null;
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
                data.finPaymentId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (HeaderData.getCurrentDBTimestamp(this, data.finPaymentId).equals(
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
                    data.finPaymentId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                        //BUTTON TEXT FILLING
                    data.emAprmProcessPaymentBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "36972531DA994BB38ECB91993058282F", data.getField("EM_APRM_Process_Payment"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Fin_Payment_ID", data.finPaymentId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Header. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
