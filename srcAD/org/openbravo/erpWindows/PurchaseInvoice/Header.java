
package org.openbravo.erpWindows.PurchaseInvoice;


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
  
  private static final String windowId = "183";
  private static final String tabId = "290";
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
     
      if (command.contains("9EB2228A60684C0DBEC12D5CD8D85218")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("9EB2228A60684C0DBEC12D5CD8D85218");
        SessionInfo.setModuleId("0");
      }
     
      if (command.contains("8D6C70A85BCC4DF1AE626425FE6000F2")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("8D6C70A85BCC4DF1AE626425FE6000F2");
        SessionInfo.setModuleId("F362A88998224918BA3A7D649B4B52D7");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("111")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("111");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "111";
        }
      }
     

     
      if (securedProcess && command.contains("9EB2228A60684C0DBEC12D5CD8D85218")) {
        classInfo.type = "P";
        classInfo.id = "9EB2228A60684C0DBEC12D5CD8D85218";
      }
     
      if (securedProcess && command.contains("8D6C70A85BCC4DF1AE626425FE6000F2")) {
        classInfo.type = "P";
        classInfo.id = "8D6C70A85BCC4DF1AE626425FE6000F2";
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
        String strcInvoiceId = request.getParameter("inpcInvoiceId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strcInvoiceId.equals(""))
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

      String strC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strC_Invoice_ID.equals("")) strC_Invoice_ID = firstElement(vars, tableSQL);
          if (strC_Invoice_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_Invoice_ID, tableSQL);

      else printPageDataSheet(response, vars, strC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strC_Invoice_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strC_Invoice_ID.equals("")) strC_Invoice_ID = vars.getRequiredGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      else vars.setSessionValue(windowId + "|C_Invoice_ID", strC_Invoice_ID);
      
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      printPageEdit(response, request, vars, false, strC_Invoice_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|Header.view");
      String strC_Invoice_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strC_Invoice_ID = firstElement(vars, tableSQL);
          if (strC_Invoice_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strC_Invoice_ID.equals("")) strC_Invoice_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strC_Invoice_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamAD_Org_ID", tabId + "|paramAD_Org_ID");
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");
vars.getRequestGlobalVariable("inpParamPOReference", tabId + "|paramPOReference");
vars.getRequestGlobalVariable("inpParamDateInvoiced", tabId + "|paramDateInvoiced");
vars.getRequestGlobalVariable("inpParamC_DocTypeTarget_ID", tabId + "|paramC_DocTypeTarget_ID");
vars.getRequestGlobalVariable("inpParamC_BPartner_ID", tabId + "|paramC_BPartner_ID");
vars.getRequestGlobalVariable("inpParamSalesRep_ID", tabId + "|paramSalesRep_ID");
vars.getRequestGlobalVariable("inpParamIspaid", tabId + "|paramIspaid");
vars.getRequestGlobalVariable("inpParamOutstandingAmt", tabId + "|paramOutstandingAmt");
vars.getRequestGlobalVariable("inpParamDueAmt", tabId + "|paramDueAmt");
vars.getRequestGlobalVariable("inpParamGrandTotal", tabId + "|paramGrandTotal");
vars.getRequestGlobalVariable("inpParamDocStatus", tabId + "|paramDocStatus");
vars.getRequestGlobalVariable("inpParamDateInvoiced_f", tabId + "|paramDateInvoiced_f");
vars.getRequestGlobalVariable("inpParamOutstandingAmt_f", tabId + "|paramOutstandingAmt_f");
vars.getRequestGlobalVariable("inpParamDueAmt_f", tabId + "|paramDueAmt_f");
vars.getRequestGlobalVariable("inpParamGrandTotal_f", tabId + "|paramGrandTotal_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|C_Invoice_ID");
      String strC_Invoice_ID="";

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strC_Invoice_ID = firstElement(vars, tableSQL);
        if (strC_Invoice_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Header.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_Invoice_ID, tableSQL);

      else printPageDataSheet(response, vars, strC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "RELATION");
      printPageDataSheet(response, vars, strC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strC_Invoice_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strC_Invoice_ID = vars.getRequiredStringParameter("inpcInvoiceId");
      
      String strNext = nextElement(vars, strC_Invoice_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strC_Invoice_ID = vars.getRequiredStringParameter("inpcInvoiceId");
      
      String strPrevious = previousElement(vars, strC_Invoice_ID, tableSQL);

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
      vars.removeSessionValue(windowId + "|C_Invoice_ID");

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
      vars.removeSessionValue(windowId + "|C_Invoice_ID");

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

      String strC_Invoice_ID = vars.getRequiredGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
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
          String strNext = nextElement(vars, strC_Invoice_ID, tableSQL);
          vars.setSessionValue(windowId + "|C_Invoice_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strC_Invoice_ID = vars.getRequiredStringParameter("inpcInvoiceId");
      //HeaderData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = HeaderData.delete(this, strC_Invoice_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|cInvoiceId");
        vars.setSessionValue(tabId + "|Header.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONDocAction111")) {
        vars.setSessionValue("button111.strdocaction", vars.getStringParameter("inpdocaction"));
        vars.setSessionValue("button111.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button111.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button111.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("button111.inpdocstatus", vars.getRequiredStringParameter("inpdocstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button111.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "111", request.getServletPath());    
     } else if (vars.commandIn("BUTTON111")) {
        String strC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");
        String strdocaction = vars.getSessionValue("button111.strdocaction");
        String strProcessing = vars.getSessionValue("button111.strProcessing");
        String strOrg = vars.getSessionValue("button111.strOrg");
        String strClient = vars.getSessionValue("button111.strClient");
        
        String strdocstatus = vars.getSessionValue("button111.inpdocstatus");
String stradTableId = "318";

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocAction111(response, vars, strC_Invoice_ID, strdocaction, strProcessing, strdocstatus, stradTableId);
        }

    } else if (vars.commandIn("BUTTONCalculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218")) {
        vars.setSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strcalculatePromotions", vars.getStringParameter("inpcalculatePromotions"));
        vars.setSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button9EB2228A60684C0DBEC12D5CD8D85218.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "9EB2228A60684C0DBEC12D5CD8D85218", request.getServletPath());
      } else if (vars.commandIn("BUTTON9EB2228A60684C0DBEC12D5CD8D85218")) {
        String strC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");
        String strcalculatePromotions = vars.getSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strcalculatePromotions");
        String strProcessing = vars.getSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strProcessing");
        String strOrg = vars.getSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strOrg");
        String strClient = vars.getSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCalculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218(response, vars, strC_Invoice_ID, strcalculatePromotions, strProcessing);
        }
    } else if (vars.commandIn("BUTTONEM_RCOB_Calculatecommission8D6C70A85BCC4DF1AE626425FE6000F2")) {
        vars.setSessionValue("button8D6C70A85BCC4DF1AE626425FE6000F2.stremRcobCalculatecommission", vars.getStringParameter("inpemRcobCalculatecommission"));
        vars.setSessionValue("button8D6C70A85BCC4DF1AE626425FE6000F2.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button8D6C70A85BCC4DF1AE626425FE6000F2.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button8D6C70A85BCC4DF1AE626425FE6000F2.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button8D6C70A85BCC4DF1AE626425FE6000F2.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "8D6C70A85BCC4DF1AE626425FE6000F2", request.getServletPath());
      } else if (vars.commandIn("BUTTON8D6C70A85BCC4DF1AE626425FE6000F2")) {
        String strC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");
        String stremRcobCalculatecommission = vars.getSessionValue("button8D6C70A85BCC4DF1AE626425FE6000F2.stremRcobCalculatecommission");
        String strProcessing = vars.getSessionValue("button8D6C70A85BCC4DF1AE626425FE6000F2.strProcessing");
        String strOrg = vars.getSessionValue("button8D6C70A85BCC4DF1AE626425FE6000F2.strOrg");
        String strClient = vars.getSessionValue("button8D6C70A85BCC4DF1AE626425FE6000F2.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_RCOB_Calculatecommission8D6C70A85BCC4DF1AE626425FE6000F2(response, vars, strC_Invoice_ID, stremRcobCalculatecommission, strProcessing);
        }

    } else if (vars.commandIn("SAVE_BUTTONDocAction111")) {
        String strC_Invoice_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Invoice_ID", "");
        @SuppressWarnings("unused")
        String strdocaction = vars.getStringParameter("inpdocaction");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "111", (("C_Invoice_ID".equalsIgnoreCase("AD_Language"))?"0":strC_Invoice_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          HeaderData.updateDocAction(this, strdocaction, strC_Invoice_ID);

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

    } else if (vars.commandIn("SAVE_BUTTONCalculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218")) {
        String strC_Invoice_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Invoice_ID", "");
        
        ProcessBundle pb = new ProcessBundle("9EB2228A60684C0DBEC12D5CD8D85218", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Invoice_ID", strC_Invoice_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONEM_RCOB_Calculatecommission8D6C70A85BCC4DF1AE626425FE6000F2")) {
        String strC_Invoice_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Invoice_ID", "");
        
        ProcessBundle pb = new ProcessBundle("8D6C70A85BCC4DF1AE626425FE6000F2", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Invoice_ID", strC_Invoice_ID);
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

    } else if (vars.commandIn("BUTTONCreateFrom")) {
        String strC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");
        String strTableId = "318";
        String strProcessId = "";
        String strDateInvoiced = vars.getStringParameter("inpdateinvoiced", "");
        String strBPartnerLocation = vars.getStringParameter("inpcBpartnerLocationId", "");
        String strPriceList = vars.getStringParameter("inpmPricelistId", "");
        String strBPartner = vars.getStringParameter("inpcBpartnerId", "");
        String strBankAccount = vars.getStringParameter("inpcBankaccountId");
        String strStatementDate = vars.getStringParameter("inpstatementdate");
        String strOrg = vars.getStringParameter("inpadOrgId");
        String strClient = vars.getStringParameter("inpadClientId");
        String strIsreceipt = vars.getStringParameter("inpisreceipt");
        log4j.debug("Loading CreateFrom button in table: " + strTableId);
        vars.setSessionValue("CreateFrom|key", strC_Invoice_ID);
        vars.setSessionValue("CreateFrom|tableId", strTableId);
        vars.setSessionValue("CreateFrom|tabId", tabId);
        vars.setSessionValue("CreateFrom|processId", strProcessId);
        vars.setSessionValue("CreateFrom|path", strDireccion + request.getServletPath());
        vars.setSessionValue("CreateFrom|bpartnerLocation", strBPartnerLocation);
        vars.setSessionValue("CreateFrom|dateInvoiced", strDateInvoiced);
        vars.setSessionValue("CreateFrom|pricelist", strPriceList);
        vars.setSessionValue("CreateFrom|bpartner", strBPartner);
        vars.setSessionValue("CreateFrom|windowId", windowId);
        vars.setSessionValue("CreateFrom|bankAccount", strBankAccount);
        vars.setSessionValue("CreateFrom|statementDate", strStatementDate);
        vars.setSessionValue("CreateFrom|adOrgId", strOrg);
        vars.setSessionValue("CreateFrom|isreceipt", strIsreceipt);
        vars.setSessionValue("CreateFrom|tabName", "Header");
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          response.sendRedirect(strDireccion + "/ad_actionButton/CreateFrom.html");
        }

    } else if (vars.commandIn("BUTTONPosted")) {
        String strC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");
        String strTableId = "318";
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
          vars.setSessionValue("Posted|key", strC_Invoice_ID);
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
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.cDoctypetargetId = vars.getRequiredStringParameter("inpcDoctypetargetId");     data.cDoctypetargetIdr = vars.getStringParameter("inpcDoctypetargetId_R");     data.dateinvoiced = vars.getRequiredGlobalVariable("inpdateinvoiced", windowId + "|DateInvoiced");     data.emEpcgCheck = vars.getStringParameter("inpemEpcgCheck", "N");     data.emEpcgEpcgone = vars.getStringParameter("inpemEpcgEpcgone");     data.emEpcgEpcgoner = vars.getStringParameter("inpemEpcgEpcgone_R");     data.cBpartnerId = vars.getRequiredGlobalVariable("inpcBpartnerId", windowId + "|C_BPartner_ID");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.cBpartnerLocationId = vars.getRequiredStringParameter("inpcBpartnerLocationId");     data.cBpartnerLocationIdr = vars.getStringParameter("inpcBpartnerLocationId_R");     data.mPricelistId = vars.getRequiredGlobalVariable("inpmPricelistId", windowId + "|M_PriceList_ID");     data.mPricelistIdr = vars.getStringParameter("inpmPricelistId_R");     data.dateacct = vars.getRequiredStringParameter("inpdateacct");     data.cPaymenttermId = vars.getRequiredStringParameter("inpcPaymenttermId");     data.cPaymenttermIdr = vars.getStringParameter("inpcPaymenttermId_R");     data.finPaymentmethodId = vars.getRequiredStringParameter("inpfinPaymentmethodId");     data.finPaymentmethodIdr = vars.getStringParameter("inpfinPaymentmethodId_R");     data.emEpcgAgent = vars.getStringParameter("inpemEpcgAgent");     data.emEpcgAgentr = vars.getStringParameter("inpemEpcgAgent_R");     data.emEpcgWaybillno = vars.getStringParameter("inpemEpcgWaybillno");     data.emEpcgLrno = vars.getStringParameter("inpemEpcgLrno");     data.emEpcgTransname = vars.getStringParameter("inpemEpcgTransname");     data.description = vars.getStringParameter("inpdescription");     data.cOrderId = vars.getStringParameter("inpcOrderId");     data.cOrderIdr = vars.getStringParameter("inpcOrderId_R");     data.poreference = vars.getStringParameter("inpporeference");     data.emEpcgType = vars.getStringParameter("inpemEpcgType");     data.emEpcgTyper = vars.getStringParameter("inpemEpcgType_R");     data.emEpcgFormtypeno = vars.getStringParameter("inpemEpcgFormtypeno");     data.docstatus = vars.getRequiredGlobalVariable("inpdocstatus", windowId + "|DocStatus");    try {   data.grandtotal = vars.getRequiredNumericParameter("inpgrandtotal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totallines = vars.getRequiredNumericParameter("inptotallines");  } catch (ServletException paramEx) { ex = paramEx; }     data.cCurrencyId = vars.getRequiredGlobalVariable("inpcCurrencyId", windowId + "|C_Currency_ID");     data.ispaid = vars.getRequiredInputGlobalVariable("inpispaid", windowId + "|Ispaid", "N");     data.adUserId = vars.getStringParameter("inpadUserId");    try {   data.totalpaid = vars.getRequiredNumericParameter("inptotalpaid", vars.getSessionValue(windowId + "|Totalpaid"));  } catch (ServletException paramEx) { ex = paramEx; }     data.lastcalculatedondate = vars.getStringParameter("inplastcalculatedondate");    try {   data.outstandingamt = vars.getRequiredNumericParameter("inpoutstandingamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.taxdate = vars.getStringParameter("inptaxdate");     data.dateordered = vars.getStringParameter("inpdateordered");    try {   data.dueamt = vars.getRequiredNumericParameter("inpdueamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.daystilldue = vars.getRequiredNumericParameter("inpdaystilldue");  } catch (ServletException paramEx) { ex = paramEx; }     data.salesrepId = vars.getStringParameter("inpsalesrepId");     data.isdiscountprinted = vars.getStringParameter("inpisdiscountprinted", "N");     data.cDoctypeId = vars.getRequiredGlobalVariable("inpcDoctypeId", windowId + "|C_DocType_ID");    try {   data.withholdingamount = vars.getNumericParameter("inpwithholdingamount");  } catch (ServletException paramEx) { ex = paramEx; }     data.cWithholdingId = vars.getStringParameter("inpcWithholdingId");    try {   data.percentageoverdue = vars.getNumericParameter("inppercentageoverdue");  } catch (ServletException paramEx) { ex = paramEx; }     data.cActivityId = vars.getStringParameter("inpcActivityId");     data.finalsettlement = vars.getStringParameter("inpfinalsettlement");    try {   data.daysoutstanding = vars.getNumericParameter("inpdaysoutstanding");  } catch (ServletException paramEx) { ex = paramEx; }     data.isactive = vars.getStringParameter("inpisactive", "N");    try {   data.chargeamt = vars.getNumericParameter("inpchargeamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.cChargeId = vars.getStringParameter("inpcChargeId");     data.generateto = vars.getStringParameter("inpgenerateto");     data.adOrgtrxId = vars.getStringParameter("inpadOrgtrxId");     data.updatepaymentmonitor = vars.getRequiredStringParameter("inpupdatepaymentmonitor");     data.emAprmAddpayment = vars.getStringParameter("inpemAprmAddpayment");     data.posted = vars.getRequiredGlobalVariable("inpposted", windowId + "|Posted");     data.emAprmProcessinvoice = vars.getStringParameter("inpemAprmProcessinvoice");     data.docaction = vars.getRequiredStringParameter("inpdocaction");     data.createfrom = vars.getStringParameter("inpcreatefrom");     data.copyfrom = vars.getStringParameter("inpcopyfrom");     data.calculatePromotions = vars.getStringParameter("inpcalculatePromotions");     data.emEpcgTin = vars.getStringParameter("inpemEpcgTin");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.aAssetId = vars.getStringParameter("inpaAssetId");     data.cCampaignId = vars.getStringParameter("inpcCampaignId");     data.cCampaignIdr = vars.getStringParameter("inpcCampaignId_R");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.finPaymentPriorityId = vars.getStringParameter("inpfinPaymentPriorityId");     data.emEpcgInvoicetype = vars.getStringParameter("inpemEpcgInvoicetype");     data.emEpcgDomestictype = vars.getStringParameter("inpemEpcgDomestictype");     data.emEpcgExporttype = vars.getStringParameter("inpemEpcgExporttype");     data.emEpcgEdexempt = vars.getStringParameter("inpemEpcgEdexempt");     data.cBpBankaccountId = vars.getStringParameter("inpcBpBankaccountId");     data.emEpcgLcno = vars.getStringParameter("inpemEpcgLcno");     data.emEpcgLcdate = vars.getStringParameter("inpemEpcgLcdate");     data.emEpcgPortload = vars.getStringParameter("inpemEpcgPortload");     data.emEpcgPortdischarge = vars.getStringParameter("inpemEpcgPortdischarge");     data.emEpcgFinaldest = vars.getStringParameter("inpemEpcgFinaldest");     data.emEpcgAreno = vars.getStringParameter("inpemEpcgAreno");     data.emEpcgExciseno = vars.getStringParameter("inpemEpcgExciseno");     data.emEpcgRemarks = vars.getStringParameter("inpemEpcgRemarks");     data.emEpcgPrinttype = vars.getStringParameter("inpemEpcgPrinttype");     data.emEpcgLocdelivery = vars.getStringParameter("inpemEpcgLocdelivery");     data.emEpcgInspector = vars.getStringParameter("inpemEpcgInspector");     data.emEpcgSuperintendent = vars.getStringParameter("inpemEpcgSuperintendent");    try {   data.emEpcgUsdrate = vars.getNumericParameter("inpemEpcgUsdrate");  } catch (ServletException paramEx) { ex = paramEx; }     data.emRcobCalculatecommission = vars.getStringParameter("inpemRcobCalculatecommission");     data.istaxincluded = vars.getStringParameter("inpistaxincluded", "N");     data.processed = vars.getStringParameter("inpprocessed", "N");     data.dateprinted = vars.getStringParameter("inpdateprinted");     data.isselfservice = vars.getStringParameter("inpisselfservice", "N");     data.issotrx = vars.getRequiredInputGlobalVariable("inpissotrx", windowId + "|IsSOTrx", "N");     data.paymentrule = vars.getStringParameter("inppaymentrule");     data.processing = vars.getStringParameter("inpprocessing");     data.isprinted = vars.getStringParameter("inpisprinted", "N");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.cInvoiceId = vars.getRequestGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
          vars.getGlobalVariable("inpvoidautomaticallycreated", windowId + "|VoidAutomaticallyCreated", "");
    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "C_Invoice", data.cDoctypetargetId, data.cDoctypeId, false, true);

    
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|DateInvoiced", data[0].getField("dateinvoiced"));    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].getField("cBpartnerId"));    vars.setSessionValue(windowId + "|M_PriceList_ID", data[0].getField("mPricelistId"));    vars.setSessionValue(windowId + "|DocStatus", data[0].getField("docstatus"));    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].getField("cCurrencyId"));    vars.setSessionValue(windowId + "|IsPaid", data[0].getField("ispaid"));    vars.setSessionValue(windowId + "|Totalpaid", data[0].getField("totalpaid"));    vars.setSessionValue(windowId + "|C_DocType_ID", data[0].getField("cDoctypeId"));    vars.setSessionValue(windowId + "|Posted", data[0].getField("posted"));    vars.setSessionValue(windowId + "|IsSOTrx", data[0].getField("issotrx"));    vars.setSessionValue(windowId + "|C_Invoice_ID", data[0].getField("cInvoiceId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpcInvoiceId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strC_Invoice_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamAD_Org_ID = vars.getSessionValue(tabId + "|paramAD_Org_ID");
String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamPOReference = vars.getSessionValue(tabId + "|paramPOReference");
String strParamDateInvoiced = vars.getSessionValue(tabId + "|paramDateInvoiced");
String strParamC_DocTypeTarget_ID = vars.getSessionValue(tabId + "|paramC_DocTypeTarget_ID");
String strParamC_BPartner_ID = vars.getSessionValue(tabId + "|paramC_BPartner_ID");
String strParamSalesRep_ID = vars.getSessionValue(tabId + "|paramSalesRep_ID");
String strParamIspaid = vars.getSessionValue(tabId + "|paramIspaid");
String strParamOutstandingAmt = vars.getSessionValue(tabId + "|paramOutstandingAmt");
String strParamDueAmt = vars.getSessionValue(tabId + "|paramDueAmt");
String strParamGrandTotal = vars.getSessionValue(tabId + "|paramGrandTotal");
String strParamDocStatus = vars.getSessionValue(tabId + "|paramDocStatus");
String strParamDateInvoiced_f = vars.getSessionValue(tabId + "|paramDateInvoiced_f");
String strParamOutstandingAmt_f = vars.getSessionValue(tabId + "|paramOutstandingAmt_f");
String strParamDueAmt_f = vars.getSessionValue(tabId + "|paramDueAmt_f");
String strParamGrandTotal_f = vars.getSessionValue(tabId + "|paramGrandTotal_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Org_ID) && ("").equals(strParamDocumentNo) && ("").equals(strParamPOReference) && ("").equals(strParamDateInvoiced) && ("").equals(strParamC_DocTypeTarget_ID) && ("").equals(strParamC_BPartner_ID) && ("").equals(strParamSalesRep_ID) && ("").equals(strParamIspaid) && ("").equals(strParamOutstandingAmt) && ("").equals(strParamDueAmt) && ("").equals(strParamGrandTotal) && ("").equals(strParamDocStatus) && ("").equals(strParamDateInvoiced_f) && ("").equals(strParamOutstandingAmt_f) && ("").equals(strParamDueAmt_f) && ("").equals(strParamGrandTotal_f)) || !(("").equals(strParamAD_Org_ID) || ("%").equals(strParamAD_Org_ID))  || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamPOReference) || ("%").equals(strParamPOReference))  || !(("").equals(strParamDateInvoiced) || ("%").equals(strParamDateInvoiced))  || !(("").equals(strParamC_DocTypeTarget_ID) || ("%").equals(strParamC_DocTypeTarget_ID))  || !(("").equals(strParamC_BPartner_ID) || ("%").equals(strParamC_BPartner_ID))  || !(("").equals(strParamSalesRep_ID) || ("%").equals(strParamSalesRep_ID))  || !(("").equals(strParamIspaid) || ("%").equals(strParamIspaid))  || !(("").equals(strParamOutstandingAmt) || ("%").equals(strParamOutstandingAmt))  || !(("").equals(strParamDueAmt) || ("%").equals(strParamDueAmt))  || !(("").equals(strParamGrandTotal) || ("%").equals(strParamGrandTotal))  || !(("").equals(strParamDocStatus) || ("%").equals(strParamDocStatus))  || !(("").equals(strParamDateInvoiced_f) || ("%").equals(strParamDateInvoiced_f))  || !(("").equals(strParamOutstandingAmt_f) || ("%").equals(strParamOutstandingAmt_f))  || !(("").equals(strParamDueAmt_f) || ("%").equals(strParamDueAmt_f))  || !(("").equals(strParamGrandTotal_f) || ("%").equals(strParamGrandTotal_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strC_Invoice_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strC_Invoice_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseInvoice/Header_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Header", false, "document.frmMain.inpcInvoiceId", "grid", "../com.rcss.humanresource/erpCommon/ad_reports/ReportPurchaseInvoice.pdf", "N".equals("Y"), "PurchaseInvoice", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "cInvoiceId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "PurchaseInvoice", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strC_Invoice_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " C_Invoice.DocumentNo DESC";
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
    String strParamAD_Org_ID = vars.getSessionValue(tabId + "|paramAD_Org_ID");
String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamPOReference = vars.getSessionValue(tabId + "|paramPOReference");
String strParamDateInvoiced = vars.getSessionValue(tabId + "|paramDateInvoiced");
String strParamC_DocTypeTarget_ID = vars.getSessionValue(tabId + "|paramC_DocTypeTarget_ID");
String strParamC_BPartner_ID = vars.getSessionValue(tabId + "|paramC_BPartner_ID");
String strParamSalesRep_ID = vars.getSessionValue(tabId + "|paramSalesRep_ID");
String strParamIspaid = vars.getSessionValue(tabId + "|paramIspaid");
String strParamOutstandingAmt = vars.getSessionValue(tabId + "|paramOutstandingAmt");
String strParamDueAmt = vars.getSessionValue(tabId + "|paramDueAmt");
String strParamGrandTotal = vars.getSessionValue(tabId + "|paramGrandTotal");
String strParamDocStatus = vars.getSessionValue(tabId + "|paramDocStatus");
String strParamDateInvoiced_f = vars.getSessionValue(tabId + "|paramDateInvoiced_f");
String strParamOutstandingAmt_f = vars.getSessionValue(tabId + "|paramOutstandingAmt_f");
String strParamDueAmt_f = vars.getSessionValue(tabId + "|paramDueAmt_f");
String strParamGrandTotal_f = vars.getSessionValue(tabId + "|paramGrandTotal_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Org_ID) && ("").equals(strParamDocumentNo) && ("").equals(strParamPOReference) && ("").equals(strParamDateInvoiced) && ("").equals(strParamC_DocTypeTarget_ID) && ("").equals(strParamC_BPartner_ID) && ("").equals(strParamSalesRep_ID) && ("").equals(strParamIspaid) && ("").equals(strParamOutstandingAmt) && ("").equals(strParamDueAmt) && ("").equals(strParamGrandTotal) && ("").equals(strParamDocStatus) && ("").equals(strParamDateInvoiced_f) && ("").equals(strParamOutstandingAmt_f) && ("").equals(strParamDueAmt_f) && ("").equals(strParamGrandTotal_f)) || !(("").equals(strParamAD_Org_ID) || ("%").equals(strParamAD_Org_ID))  || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamPOReference) || ("%").equals(strParamPOReference))  || !(("").equals(strParamDateInvoiced) || ("%").equals(strParamDateInvoiced))  || !(("").equals(strParamC_DocTypeTarget_ID) || ("%").equals(strParamC_DocTypeTarget_ID))  || !(("").equals(strParamC_BPartner_ID) || ("%").equals(strParamC_BPartner_ID))  || !(("").equals(strParamSalesRep_ID) || ("%").equals(strParamSalesRep_ID))  || !(("").equals(strParamIspaid) || ("%").equals(strParamIspaid))  || !(("").equals(strParamOutstandingAmt) || ("%").equals(strParamOutstandingAmt))  || !(("").equals(strParamDueAmt) || ("%").equals(strParamDueAmt))  || !(("").equals(strParamGrandTotal) || ("%").equals(strParamGrandTotal))  || !(("").equals(strParamDocStatus) || ("%").equals(strParamDocStatus))  || !(("").equals(strParamDateInvoiced_f) || ("%").equals(strParamDateInvoiced_f))  || !(("").equals(strParamOutstandingAmt_f) || ("%").equals(strParamOutstandingAmt_f))  || !(("").equals(strParamDueAmt_f) || ("%").equals(strParamDueAmt_f))  || !(("").equals(strParamGrandTotal_f) || ("%").equals(strParamGrandTotal_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strC_Invoice_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strC_Invoice_ID.equals("") && (data == null || data.length==0)) {
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
      if (dataField.getField("cInvoiceId") == null || dataField.getField("cInvoiceId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strDOCBASETYPE = HeaderData.selectAux68CD2AFDCEAB45ADB0690B33067B7940(this, ((dataField!=null)?dataField.getField("cDoctypetargetId"):((data==null || data.length==0)?"":data[0].cDoctypetargetId)));
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
        String strVoidAutomaticallyCreated = HeaderData.selectAux7E3FB488115E46C5937FDF8A73D36098(this, ((dataField!=null)?dataField.getField("cInvoiceId"):((data==null || data.length==0)?"":data[0].cInvoiceId)));
    vars.setSessionValue(windowId + "|VoidAutomaticallyCreated", strVoidAutomaticallyCreated);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = HeaderData.set(HeaderData.selectDef0445FEDA79A747C48A7B3EF852D176DC(this, Utility.getContext(this, vars, "#AD_Org_ID", windowId), Utility.getContext(this, vars, "AD_Role_ID", "183"), Utility.getContext(this, vars, "#AD_Client_ID", windowId)), Utility.getDefault(this, vars, "LastCalculatedOnDate", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Finaldest", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Printtype", "Standard Invoice", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Usdrate", "", "183", "", dataField), Utility.getDefault(this, vars, "C_Withholding_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "Withholdingamount", "", "183", "", dataField), Utility.getDefault(this, vars, "Taxdate", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Edexempt", "30/2004 CENTRAL EXCISE", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Invoicetype", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Epcgone", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Inspector", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Agent", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Lcno", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Lrno", "", "183", "", dataField), Utility.getDefault(this, vars, "DaysTillDue", "0", "183", "0", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "183", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@#AD_Org_ID@", "183", "", dataField), "Y", Utility.getDefault(this, vars, "Created", "", "183", "", dataField), Utility.getDefault(this, vars, "CreatedBy", "", "183", "", dataField), HeaderData.selectDef3489_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "183", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "183", "", dataField), HeaderData.selectDef3491_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "183", "", dataField)), Utility.getDefault(this, vars, "DocumentNo", "", "183", "", dataField), Utility.getDefault(this, vars, "C_DocType_ID", "0", "183", "", dataField), Utility.getDefault(this, vars, "DocStatus", "DR", "183", "", dataField), Utility.getDefault(this, vars, "DocAction", "CO", "183", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "135", Utility.getDefault(this, vars, "DocAction", "CO", "183", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "135", Utility.getDefault(this, vars, "DocAction", "CO", "183", "N", dataField))), Utility.getDefault(this, vars, "Processing", "", "183", "N", dataField), Utility.getDefault(this, vars, "Processed", "", "183", "N", dataField), Utility.getDefault(this, vars, "C_BPartner_ID", "", "183", "", dataField), HeaderData.selectDef3499_2(this, Utility.getDefault(this, vars, "C_BPartner_ID", "", "183", "", dataField)), Utility.getDefault(this, vars, "C_PaymentTerm_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "C_BPartner_Location_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "@C_Currency_ID@", "183", "", dataField), Utility.getDefault(this, vars, "TotalLines", "", "183", "0", dataField), Utility.getDefault(this, vars, "GrandTotal", "", "183", "0", dataField), Utility.getDefault(this, vars, "DateAcct", "@#Date@", "183", "", dataField), Utility.getDefault(this, vars, "C_Campaign_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "183", "", dataField), HeaderData.selectDef3510_3(this, Utility.getDefault(this, vars, "C_Project_ID", "", "183", "", dataField)), Utility.getDefault(this, vars, "C_Activity_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "SalesRep_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "AD_User_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "IsSOTrx", "@IsSOTrx@", "183", "N", dataField), Utility.getDefault(this, vars, "C_DocTypeTarget_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "Description", "", "183", "", dataField), Utility.getDefault(this, vars, "DateInvoiced", "@#Date@", "183", "", dataField), Utility.getDefault(this, vars, "DatePrinted", "", "183", "", dataField), Utility.getDefault(this, vars, "POReference", "", "183", "", dataField), Utility.getDefault(this, vars, "C_Charge_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "ChargeAmt", "", "183", "", dataField), Utility.getDefault(this, vars, "M_PriceList_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Locdelivery", "", "183", "", dataField), Utility.getDefault(this, vars, "PaymentRule", "P", "183", "", dataField), Utility.getDefault(this, vars, "C_Order_ID", "", "183", "", dataField), HeaderData.selectDef4247_4(this, Utility.getDefault(this, vars, "C_Order_ID", "", "183", "", dataField)), Utility.getDefault(this, vars, "DateOrdered", "", "183", "", dataField), Utility.getDefault(this, vars, "IsDiscountPrinted", "", "183", "N", dataField), Utility.getDefault(this, vars, "IsPrinted", "", "183", "N", dataField), Utility.getDefault(this, vars, "IsTaxIncluded", "", "183", "N", dataField), Utility.getDefault(this, vars, "Posted", "N", "183", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "183", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "183", "N", dataField))), Utility.getDefault(this, vars, "EM_Epcg_Lcdate", "", "183", "", dataField), Utility.getDefault(this, vars, "GenerateTo", "", "183", "N", dataField), Utility.getDefault(this, vars, "CreateFrom", "", "183", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Domestictype", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Exciseno", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_APRM_Addpayment", "Y", "183", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Portdischarge", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Transname", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Remarks", "", "183", "", dataField), Utility.getDefault(this, vars, "FIN_Paymentmethod_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "CopyFrom", "", "183", "N", dataField), Utility.getDefault(this, vars, "IsSelfService", "", "183", "N", dataField), Utility.getDefault(this, vars, "EM_APRM_Processinvoice", "CO", "183", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "135", Utility.getDefault(this, vars, "EM_APRM_Processinvoice", "CO", "183", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "135", Utility.getDefault(this, vars, "EM_APRM_Processinvoice", "CO", "183", "N", dataField))), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "AD_OrgTrx_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Portload", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Exporttype", "", "183", "", dataField), Utility.getDefault(this, vars, "Finalsettlement", "", "183", "", dataField), Utility.getDefault(this, vars, "Daysoutstanding", "", "183", "", dataField), Utility.getDefault(this, vars, "Percentageoverdue", "", "183", "", dataField), Utility.getDefault(this, vars, "Totalpaid", "0", "183", "0", dataField), Utility.getDefault(this, vars, "C_Bp_Bankaccount_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Formtypeno", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Tin", "", "183", "", dataField), Utility.getDefault(this, vars, "OutstandingAmt", "0", "183", "0", dataField), Utility.getDefault(this, vars, "DueAmt", "0", "183", "0", dataField), Utility.getDefault(this, vars, "EM_Epcg_Superintendent", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Areno", "", "183", "", dataField), Utility.getDefault(this, vars, "Ispaid", "N", "183", "N", dataField), Utility.getDefault(this, vars, "UpdatePaymentMonitor", "N", "183", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Type", "", "183", "", dataField), Utility.getDefault(this, vars, "A_Asset_ID", "", "183", "", dataField), Utility.getDefault(this, vars, "Calculate_Promotions", "", "183", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Waybillno", "", "183", "", dataField), Utility.getDefault(this, vars, "EM_RCOB_Calculatecommission", "", "183", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Check", "", "183", "N", dataField));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "C_Invoice", data[0].cDoctypetargetId, data[0].cDoctypeId, false, false) + ">";
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseInvoice/Header_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseInvoice/Header_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Header", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpcInvoiceId", "", "../com.rcss.humanresource/erpCommon/ad_reports/ReportPurchaseInvoice.pdf", "N".equals("Y"), "PurchaseInvoice", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strC_Invoice_ID), !hasReadOnlyAccess);
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
      // if (!strC_Invoice_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "PurchaseInvoice", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Header_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
        xmlDocument.setParameter("VoidAutomaticallyCreated", strVoidAutomaticallyCreated);
    
    
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
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "49DC1D6F086945AB82F84C66F5F13F16", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "C_DocTypeTarget_ID", "22F546D49D3A48E1B2B4F50446A8DE58", "124", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypetargetId"):dataField.getField("cDoctypetargetId")));
xmlDocument.setData("reportC_DocTypeTarget_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("DateInvoiced_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "18", "EM_Epcg_Epcgone", "4C55EE2EE4D74DF39075E3619FE5079E", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("emEpcgEpcgone"):dataField.getField("emEpcgEpcgone")));
xmlDocument.setData("reportEM_Epcg_Epcgone","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_BPartner_Location_ID", "", "119", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cBpartnerLocationId"):dataField.getField("cBpartnerLocationId")));
xmlDocument.setData("reportC_BPartner_Location_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_ID", "", "DB85A30739C3487988921CE3FFFD3BAD", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mPricelistId"):dataField.getField("mPricelistId")));
xmlDocument.setData("reportM_PriceList_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("DateAcct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cPaymenttermId"):dataField.getField("cPaymenttermId")));
xmlDocument.setData("reportC_PaymentTerm_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "FIN_Paymentmethod_ID", "", "FF80808130B107670130B1115F22000D", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finPaymentmethodId"):dataField.getField("finPaymentmethodId")));
xmlDocument.setData("reportFIN_Paymentmethod_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "EM_Epcg_Agent", "252", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("emEpcgAgent"):dataField.getField("emEpcgAgent")));
xmlDocument.setData("reportEM_Epcg_Agent","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "EM_Epcg_Type", "A69BE62857614DA8950AA482E2884D6E", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("emEpcgType"):dataField.getField("emEpcgType")));
xmlDocument.setData("reportEM_Epcg_Type","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonGrandTotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalLines", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalpaid", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("LastCalculatedOnDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Taxdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonOutstandingAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDueAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonWithholdingamount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Finalsettlement_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonChargeAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("UpdatePaymentMonitor_BTNname", Utility.getButtonName(this, vars, "5814736D56B34AD3A6F89D5023FAF575", "UpdatePaymentMonitor_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalUpdatePaymentMonitor = org.openbravo.erpCommon.utility.Utility.isModalProcess("645271C57F8C4DA4A1F64C7ACE37B101"); 
xmlDocument.setParameter("UpdatePaymentMonitor_Modal", modalUpdatePaymentMonitor?"true":"false");
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
xmlDocument.setParameter("EM_APRM_Processinvoice_BTNname", Utility.getButtonName(this, vars, "135", (dataField==null?data[0].getField("emAprmProcessinvoice"):dataField.getField("emAprmProcessinvoice")), "EM_APRM_Processinvoice_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_APRM_Processinvoice = org.openbravo.erpCommon.utility.Utility.isModalProcess("B54318B49E984B9CB855AEFB1F474CD6"); 
xmlDocument.setParameter("EM_APRM_Processinvoice_Modal", modalEM_APRM_Processinvoice?"true":"false");
xmlDocument.setParameter("DocAction_BTNname", Utility.getButtonName(this, vars, "135", (dataField==null?data[0].getField("docaction"):dataField.getField("docaction")), "DocAction_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalDocAction = org.openbravo.erpCommon.utility.Utility.isModalProcess("111"); 
xmlDocument.setParameter("DocAction_Modal", modalDocAction?"true":"false");
xmlDocument.setParameter("CreateFrom_BTNname", Utility.getButtonName(this, vars, "4246", "CreateFrom_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCreateFrom = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("CreateFrom_Modal", modalCreateFrom?"true":"false");
xmlDocument.setParameter("CopyFrom_BTNname", Utility.getButtonName(this, vars, "6532", "CopyFrom_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCopyFrom = org.openbravo.erpCommon.utility.Utility.isModalProcess("210"); 
xmlDocument.setParameter("CopyFrom_Modal", modalCopyFrom?"true":"false");
comboTableData = new ComboTableData(vars, this, "19", "C_Campaign_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCampaignId"):dataField.getField("cCampaignId")));
xmlDocument.setData("reportC_Campaign_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("EM_Epcg_Lcdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonEM_Epcg_Usdrate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("EM_RCOB_Calculatecommission_BTNname", Utility.getButtonName(this, vars, "F868FCF0DFD346E9904FF0BB29087FEB", "EM_RCOB_Calculatecommission_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_RCOB_Calculatecommission = org.openbravo.erpCommon.utility.Utility.isModalProcess("8D6C70A85BCC4DF1AE626425FE6000F2"); 
xmlDocument.setParameter("EM_RCOB_Calculatecommission_Modal", modalEM_RCOB_Calculatecommission?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("DatePrinted_Format", vars.getSessionValue("#AD_SqlDateFormat"));
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

    private void printPageButtonDocAction111(HttpServletResponse response, VariablesSecureApp vars, String strC_Invoice_ID, String strdocaction, String strProcessing, String strdocstatus, String stradTableId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 111");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocAction", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Invoice_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "111");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("111");
        vars.removeMessage("111");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

      xmlDocument.setParameter("docstatus", strdocstatus);
xmlDocument.setParameter("adTableId", stradTableId);
    try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
xmlDocument.setParameter("processId", "111");
xmlDocument.setParameter("processDescription", "Process Invoice");
xmlDocument.setParameter("docaction", (strdocaction.equals("--")?"CL":strdocaction));
FieldProvider[] dataDocAction = ActionButtonUtility.docAction(this, vars, strdocaction, "135", strdocstatus, strProcessing, stradTableId, tabId);
xmlDocument.setData("reportdocaction", "liststructure", dataDocAction);
StringBuffer dact = new StringBuffer();
if (dataDocAction!=null) {
  dact.append("var arrDocAction = new Array(\n");
  for (int i=0;i<dataDocAction.length;i++) {
    dact.append("new Array(\"" + dataDocAction[i].getField("id") + "\", \"" + dataDocAction[i].getField("name") + "\", \"" + dataDocAction[i].getField("description") + "\")\n");
    if (i<dataDocAction.length-1) dact.append(",\n");
  }
  dact.append(");");
} else dact.append("var arrDocAction = null");
xmlDocument.setParameter("array", dact.toString());

      
      out.println(xmlDocument.print());
      out.close();
    }


    void printPageButtonCalculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218(HttpServletResponse response, VariablesSecureApp vars, String strC_Invoice_ID, String strcalculatePromotions, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9EB2228A60684C0DBEC12D5CD8D85218");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Calculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Invoice_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "9EB2228A60684C0DBEC12D5CD8D85218");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("9EB2228A60684C0DBEC12D5CD8D85218");
        vars.removeMessage("9EB2228A60684C0DBEC12D5CD8D85218");
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
    void printPageButtonEM_RCOB_Calculatecommission8D6C70A85BCC4DF1AE626425FE6000F2(HttpServletResponse response, VariablesSecureApp vars, String strC_Invoice_ID, String stremRcobCalculatecommission, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8D6C70A85BCC4DF1AE626425FE6000F2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_RCOB_Calculatecommission8D6C70A85BCC4DF1AE626425FE6000F2", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Invoice_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "8D6C70A85BCC4DF1AE626425FE6000F2");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("8D6C70A85BCC4DF1AE626425FE6000F2");
        vars.removeMessage("8D6C70A85BCC4DF1AE626425FE6000F2");
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
      String result = "var strPaymentMonitor=\"" +Utility.getContext(this, vars, "PaymentMonitor", windowId) + "\";\nvar strShowAcct=\"" +Utility.getContext(this, vars, "#ShowAcct", windowId) + "\";\nvar strInvoiceProcessButton=\"" +Utility.getContext(this, vars, "InvoiceProcessButton", windowId) + "\";\nvar strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar str$Element_MC=\"" +Utility.getContext(this, vars, "$Element_MC", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
                data.cInvoiceId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (HeaderData.getCurrentDBTimestamp(this, data.cInvoiceId).equals(
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
                    data.cInvoiceId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                        //BUTTON TEXT FILLING
                    data.emAprmProcessinvoiceBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "135", data.getField("EM_APRM_Processinvoice"));
                    
                        //BUTTON TEXT FILLING
                    data.docactionBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "135", data.getField("DocAction"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|C_Invoice_ID", data.cInvoiceId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Header. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
