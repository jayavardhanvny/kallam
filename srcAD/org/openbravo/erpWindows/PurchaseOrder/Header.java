
package org.openbravo.erpWindows.PurchaseOrder;


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
  
  private static final String windowId = "181";
  private static final String tabId = "294";
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
     
      if (command.contains("20E70A9D32F644C0BFD3AA1C90566AB3")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("20E70A9D32F644C0BFD3AA1C90566AB3");
        SessionInfo.setModuleId("851153224EB84CC9A2A9E943F9F9C776");
      }
     
      if (command.contains("23D1B163EC0B41F790CE39BF01DA320E")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("23D1B163EC0B41F790CE39BF01DA320E");
        SessionInfo.setModuleId("0");
      }
     
      if (command.contains("A3FE1F9892394386A49FB707AA50A0FA")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("A3FE1F9892394386A49FB707AA50A0FA");
        SessionInfo.setModuleId("0");
      }
     
      if (command.contains("9EB2228A60684C0DBEC12D5CD8D85218")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("9EB2228A60684C0DBEC12D5CD8D85218");
        SessionInfo.setModuleId("0");
      }
     
      if (command.contains("173F6DB31A1643A985E46CA1F15DBD7A")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("173F6DB31A1643A985E46CA1F15DBD7A");
        SessionInfo.setModuleId("F362A88998224918BA3A7D649B4B52D7");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("104")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("104");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "104";
        }
      }
     

     
      if (securedProcess && command.contains("20E70A9D32F644C0BFD3AA1C90566AB3")) {
        classInfo.type = "P";
        classInfo.id = "20E70A9D32F644C0BFD3AA1C90566AB3";
      }
     
      if (securedProcess && command.contains("23D1B163EC0B41F790CE39BF01DA320E")) {
        classInfo.type = "P";
        classInfo.id = "23D1B163EC0B41F790CE39BF01DA320E";
      }
     
      if (securedProcess && command.contains("A3FE1F9892394386A49FB707AA50A0FA")) {
        classInfo.type = "P";
        classInfo.id = "A3FE1F9892394386A49FB707AA50A0FA";
      }
     
      if (securedProcess && command.contains("9EB2228A60684C0DBEC12D5CD8D85218")) {
        classInfo.type = "P";
        classInfo.id = "9EB2228A60684C0DBEC12D5CD8D85218";
      }
     
      if (securedProcess && command.contains("173F6DB31A1643A985E46CA1F15DBD7A")) {
        classInfo.type = "P";
        classInfo.id = "173F6DB31A1643A985E46CA1F15DBD7A";
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
        String strcOrderId = request.getParameter("inpcOrderId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strcOrderId.equals(""))
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

      String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strC_Order_ID.equals("")) strC_Order_ID = firstElement(vars, tableSQL);
          if (strC_Order_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_Order_ID, tableSQL);

      else printPageDataSheet(response, vars, strC_Order_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strC_Order_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strC_Order_ID.equals("")) strC_Order_ID = vars.getRequiredGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      else vars.setSessionValue(windowId + "|C_Order_ID", strC_Order_ID);
      
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      printPageEdit(response, request, vars, false, strC_Order_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|Header.view");
      String strC_Order_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strC_Order_ID = firstElement(vars, tableSQL);
          if (strC_Order_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strC_Order_ID.equals("")) strC_Order_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strC_Order_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamAD_Org_ID", tabId + "|paramAD_Org_ID");
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");
vars.getRequestGlobalVariable("inpParamPOReference", tabId + "|paramPOReference");
vars.getRequestGlobalVariable("inpParamDateOrdered", tabId + "|paramDateOrdered");
vars.getRequestGlobalVariable("inpParamC_DocTypeTarget_ID", tabId + "|paramC_DocTypeTarget_ID");
vars.getRequestGlobalVariable("inpParamC_BPartner_ID", tabId + "|paramC_BPartner_ID");
vars.getRequestGlobalVariable("inpParamInvoiceRule", tabId + "|paramInvoiceRule");
vars.getRequestGlobalVariable("inpParamGrandTotal", tabId + "|paramGrandTotal");
vars.getRequestGlobalVariable("inpParamDocStatus", tabId + "|paramDocStatus");
vars.getRequestGlobalVariable("inpParamDateOrdered_f", tabId + "|paramDateOrdered_f");
vars.getRequestGlobalVariable("inpParamGrandTotal_f", tabId + "|paramGrandTotal_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|C_Order_ID");
      String strC_Order_ID="";

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strC_Order_ID = firstElement(vars, tableSQL);
        if (strC_Order_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Header.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_Order_ID, tableSQL);

      else printPageDataSheet(response, vars, strC_Order_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "RELATION");
      printPageDataSheet(response, vars, strC_Order_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strC_Order_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strC_Order_ID = vars.getRequiredStringParameter("inpcOrderId");
      
      String strNext = nextElement(vars, strC_Order_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strC_Order_ID = vars.getRequiredStringParameter("inpcOrderId");
      
      String strPrevious = previousElement(vars, strC_Order_ID, tableSQL);

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
      vars.removeSessionValue(windowId + "|C_Order_ID");

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
      vars.removeSessionValue(windowId + "|C_Order_ID");

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

      String strC_Order_ID = vars.getRequiredGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
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
          String strNext = nextElement(vars, strC_Order_ID, tableSQL);
          vars.setSessionValue(windowId + "|C_Order_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strC_Order_ID = vars.getRequiredStringParameter("inpcOrderId");
      //HeaderData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = HeaderData.delete(this, strC_Order_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|cOrderId");
        vars.setSessionValue(tabId + "|Header.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONDocAction104")) {
        vars.setSessionValue("button104.strdocaction", vars.getStringParameter("inpdocaction"));
        vars.setSessionValue("button104.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button104.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button104.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("button104.inpdocstatus", vars.getRequiredStringParameter("inpdocstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button104.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "104", request.getServletPath());    
     } else if (vars.commandIn("BUTTON104")) {
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String strdocaction = vars.getSessionValue("button104.strdocaction");
        String strProcessing = vars.getSessionValue("button104.strProcessing");
        String strOrg = vars.getSessionValue("button104.strOrg");
        String strClient = vars.getSessionValue("button104.strClient");
        
        String strdocstatus = vars.getSessionValue("button104.inpdocstatus");
String stradTableId = "259";

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocAction104(response, vars, strC_Order_ID, strdocaction, strProcessing, strdocstatus, stradTableId);
        }

    } else if (vars.commandIn("BUTTONEM_Rcfr_Calcdistcost20E70A9D32F644C0BFD3AA1C90566AB3")) {
        vars.setSessionValue("button20E70A9D32F644C0BFD3AA1C90566AB3.stremRcfrCalcdistcost", vars.getStringParameter("inpemRcfrCalcdistcost"));
        vars.setSessionValue("button20E70A9D32F644C0BFD3AA1C90566AB3.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button20E70A9D32F644C0BFD3AA1C90566AB3.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button20E70A9D32F644C0BFD3AA1C90566AB3.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button20E70A9D32F644C0BFD3AA1C90566AB3.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "20E70A9D32F644C0BFD3AA1C90566AB3", request.getServletPath());
      } else if (vars.commandIn("BUTTON20E70A9D32F644C0BFD3AA1C90566AB3")) {
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String stremRcfrCalcdistcost = vars.getSessionValue("button20E70A9D32F644C0BFD3AA1C90566AB3.stremRcfrCalcdistcost");
        String strProcessing = vars.getSessionValue("button20E70A9D32F644C0BFD3AA1C90566AB3.strProcessing");
        String strOrg = vars.getSessionValue("button20E70A9D32F644C0BFD3AA1C90566AB3.strOrg");
        String strClient = vars.getSessionValue("button20E70A9D32F644C0BFD3AA1C90566AB3.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Rcfr_Calcdistcost20E70A9D32F644C0BFD3AA1C90566AB3(response, vars, strC_Order_ID, stremRcfrCalcdistcost, strProcessing);
        }
    } else if (vars.commandIn("BUTTONRM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E")) {
        vars.setSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strrmAddorphanline", vars.getStringParameter("inprmAddorphanline"));
        vars.setSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("IsSOTrx", vars.getStringParameter("inpissotrx"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button23D1B163EC0B41F790CE39BF01DA320E.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "23D1B163EC0B41F790CE39BF01DA320E", request.getServletPath());
      } else if (vars.commandIn("BUTTON23D1B163EC0B41F790CE39BF01DA320E")) {
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String strrmAddorphanline = vars.getSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strrmAddorphanline");
        String strProcessing = vars.getSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strProcessing");
        String strOrg = vars.getSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strOrg");
        String strClient = vars.getSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonRM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E(response, vars, strC_Order_ID, strrmAddorphanline, strProcessing);
        }
    } else if (vars.commandIn("BUTTONConvertquotationA3FE1F9892394386A49FB707AA50A0FA")) {
        vars.setSessionValue("buttonA3FE1F9892394386A49FB707AA50A0FA.strconvertquotation", vars.getStringParameter("inpconvertquotation"));
        vars.setSessionValue("buttonA3FE1F9892394386A49FB707AA50A0FA.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonA3FE1F9892394386A49FB707AA50A0FA.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonA3FE1F9892394386A49FB707AA50A0FA.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonA3FE1F9892394386A49FB707AA50A0FA.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "A3FE1F9892394386A49FB707AA50A0FA", request.getServletPath());
      } else if (vars.commandIn("BUTTONA3FE1F9892394386A49FB707AA50A0FA")) {
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String strconvertquotation = vars.getSessionValue("buttonA3FE1F9892394386A49FB707AA50A0FA.strconvertquotation");
        String strProcessing = vars.getSessionValue("buttonA3FE1F9892394386A49FB707AA50A0FA.strProcessing");
        String strOrg = vars.getSessionValue("buttonA3FE1F9892394386A49FB707AA50A0FA.strOrg");
        String strClient = vars.getSessionValue("buttonA3FE1F9892394386A49FB707AA50A0FA.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonConvertquotationA3FE1F9892394386A49FB707AA50A0FA(response, vars, strC_Order_ID, strconvertquotation, strProcessing);
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
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String strcalculatePromotions = vars.getSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strcalculatePromotions");
        String strProcessing = vars.getSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strProcessing");
        String strOrg = vars.getSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strOrg");
        String strClient = vars.getSessionValue("button9EB2228A60684C0DBEC12D5CD8D85218.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCalculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218(response, vars, strC_Order_ID, strcalculatePromotions, strProcessing);
        }
    } else if (vars.commandIn("BUTTONEM_Rcob_Lineprocess173F6DB31A1643A985E46CA1F15DBD7A")) {
        vars.setSessionValue("button173F6DB31A1643A985E46CA1F15DBD7A.stremRcobLineprocess", vars.getStringParameter("inpemRcobLineprocess"));
        vars.setSessionValue("button173F6DB31A1643A985E46CA1F15DBD7A.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button173F6DB31A1643A985E46CA1F15DBD7A.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button173F6DB31A1643A985E46CA1F15DBD7A.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button173F6DB31A1643A985E46CA1F15DBD7A.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "173F6DB31A1643A985E46CA1F15DBD7A", request.getServletPath());
      } else if (vars.commandIn("BUTTON173F6DB31A1643A985E46CA1F15DBD7A")) {
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String stremRcobLineprocess = vars.getSessionValue("button173F6DB31A1643A985E46CA1F15DBD7A.stremRcobLineprocess");
        String strProcessing = vars.getSessionValue("button173F6DB31A1643A985E46CA1F15DBD7A.strProcessing");
        String strOrg = vars.getSessionValue("button173F6DB31A1643A985E46CA1F15DBD7A.strOrg");
        String strClient = vars.getSessionValue("button173F6DB31A1643A985E46CA1F15DBD7A.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Rcob_Lineprocess173F6DB31A1643A985E46CA1F15DBD7A(response, vars, strC_Order_ID, stremRcobLineprocess, strProcessing);
        }

    } else if (vars.commandIn("SAVE_BUTTONDocAction104")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        @SuppressWarnings("unused")
        String strdocaction = vars.getStringParameter("inpdocaction");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "104", (("C_Order_ID".equalsIgnoreCase("AD_Language"))?"0":strC_Order_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          HeaderData.updateDocAction(this, strdocaction, strC_Order_ID);

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

    } else if (vars.commandIn("SAVE_BUTTONEM_Rcfr_Calcdistcost20E70A9D32F644C0BFD3AA1C90566AB3")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        
        ProcessBundle pb = new ProcessBundle("20E70A9D32F644C0BFD3AA1C90566AB3", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Order_ID", strC_Order_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONRM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        
        ProcessBundle pb = new ProcessBundle("23D1B163EC0B41F790CE39BF01DA320E", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Order_ID", strC_Order_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strmProductId = vars.getStringParameter("inpmProductId");
params.put("mProductId", strmProductId);
String strreturned = vars.getNumericParameter("inpreturned");
params.put("returned", strreturned);
String strpricestd = vars.getNumericParameter("inppricestd");
params.put("pricestd", strpricestd);
String strcTaxId = vars.getStringParameter("inpcTaxId");
params.put("cTaxId", strcTaxId);
String strcReturnReasonId = vars.getStringParameter("inpcReturnReasonId");
params.put("cReturnReasonId", strcReturnReasonId);

        
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
    } else if (vars.commandIn("SAVE_BUTTONConvertquotationA3FE1F9892394386A49FB707AA50A0FA")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        
        ProcessBundle pb = new ProcessBundle("A3FE1F9892394386A49FB707AA50A0FA", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Order_ID", strC_Order_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strrecalculateprices = vars.getStringParameter("inprecalculateprices", "N");
params.put("recalculateprices", strrecalculateprices);

        
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
    } else if (vars.commandIn("SAVE_BUTTONCalculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        
        ProcessBundle pb = new ProcessBundle("9EB2228A60684C0DBEC12D5CD8D85218", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Order_ID", strC_Order_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONEM_Rcob_Lineprocess173F6DB31A1643A985E46CA1F15DBD7A")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        
        ProcessBundle pb = new ProcessBundle("173F6DB31A1643A985E46CA1F15DBD7A", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Order_ID", strC_Order_ID);
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
  private HeaderData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    HeaderData data = new HeaderData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.cDoctypetargetId = vars.getRequiredStringParameter("inpcDoctypetargetId");     data.cDoctypetargetIdr = vars.getStringParameter("inpcDoctypetargetId_R");     data.generatetemplate = vars.getStringParameter("inpgeneratetemplate");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.emEpcgCheck = vars.getStringParameter("inpemEpcgCheck", "N");     data.emEpcgEpcgone = vars.getStringParameter("inpemEpcgEpcgone");     data.emEpcgEpcgoner = vars.getStringParameter("inpemEpcgEpcgone_R");     data.cReturnReasonId = vars.getStringParameter("inpcReturnReasonId");     data.dateordered = vars.getRequiredGlobalVariable("inpdateordered", windowId + "|DateOrdered");     data.cBpartnerId = vars.getRequiredGlobalVariable("inpcBpartnerId", windowId + "|C_BPartner_ID");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.cBpartnerLocationId = vars.getRequiredGlobalVariable("inpcBpartnerLocationId", windowId + "|C_BPartner_Location_ID");     data.cBpartnerLocationIdr = vars.getStringParameter("inpcBpartnerLocationId_R");     data.mWarehouseId = vars.getRequiredGlobalVariable("inpmWarehouseId", windowId + "|M_Warehouse_ID");     data.mWarehouseIdr = vars.getStringParameter("inpmWarehouseId_R");     data.rmPickfromshipment = vars.getStringParameter("inprmPickfromshipment");     data.datepromised = vars.getRequiredGlobalVariable("inpdatepromised", windowId + "|DatePromised");     data.finPaymentmethodId = vars.getStringParameter("inpfinPaymentmethodId");     data.finPaymentmethodIdr = vars.getStringParameter("inpfinPaymentmethodId_R");     data.rmReceivematerials = vars.getStringParameter("inprmReceivematerials");     data.cPaymenttermId = vars.getRequiredStringParameter("inpcPaymenttermId");     data.cPaymenttermIdr = vars.getStringParameter("inpcPaymenttermId_R");     data.rmCreateinvoice = vars.getStringParameter("inprmCreateinvoice");     data.mPricelistId = vars.getRequiredGlobalVariable("inpmPricelistId", windowId + "|M_PriceList_ID");     data.mPricelistIdr = vars.getStringParameter("inpmPricelistId_R");     data.emRcfrDiscounttype = vars.getStringParameter("inpemRcfrDiscounttype");    try {   data.emRcfrDiscount = vars.getRequiredNumericParameter("inpemRcfrDiscount");  } catch (ServletException paramEx) { ex = paramEx; }     data.poreference = vars.getStringParameter("inpporeference");     data.emRcfrCalcdistcost = vars.getRequiredStringParameter("inpemRcfrCalcdistcost");     data.emEpcgType = vars.getStringParameter("inpemEpcgType");     data.emEpcgTyper = vars.getStringParameter("inpemEpcgType_R");     data.docstatus = vars.getRequiredGlobalVariable("inpdocstatus", windowId + "|DocStatus");    try {   data.grandtotal = vars.getRequiredNumericParameter("inpgrandtotal");  } catch (ServletException paramEx) { ex = paramEx; }     data.emRcobRegdealer = vars.getStringParameter("inpemRcobRegdealer", "N");     data.emEpcgFor = vars.getStringParameter("inpemEpcgFor");     data.emEpcgTransporter = vars.getStringParameter("inpemEpcgTransporter");     data.emEpcgTin = vars.getStringParameter("inpemEpcgTin");    try {   data.totallines = vars.getRequiredNumericParameter("inptotallines");  } catch (ServletException paramEx) { ex = paramEx; }     data.cCurrencyId = vars.getRequiredGlobalVariable("inpcCurrencyId", windowId + "|C_Currency_ID");     data.adUserId = vars.getStringParameter("inpadUserId");     data.deliverynotes = vars.getStringParameter("inpdeliverynotes");     data.description = vars.getStringParameter("inpdescription");     data.salesrepId = vars.getStringParameter("inpsalesrepId");     data.billtoId = vars.getRequiredStringParameter("inpbilltoId");     data.cIncotermsId = vars.getStringParameter("inpcIncotermsId");     data.incotermsdescription = vars.getStringParameter("inpincotermsdescription");     data.isdiscountprinted = vars.getStringParameter("inpisdiscountprinted", "N");     data.cDoctypeId = vars.getRequiredGlobalVariable("inpcDoctypeId", windowId + "|C_DocType_ID");    try {   data.freightamt = vars.getRequiredNumericParameter("inpfreightamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.deliveryviarule = vars.getRequiredGlobalVariable("inpdeliveryviarule", windowId + "|DeliveryViaRule");     data.cCampaignId = vars.getStringParameter("inpcCampaignId");     data.mShipperId = vars.getRequestGlobalVariable("inpmShipperId", windowId + "|M_Shipper_ID");     data.priorityrule = vars.getRequiredStringParameter("inppriorityrule");    try {   data.chargeamt = vars.getNumericParameter("inpchargeamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.cChargeId = vars.getStringParameter("inpcChargeId");     data.cActivityId = vars.getStringParameter("inpcActivityId");     data.freightcostrule = vars.getRequiredGlobalVariable("inpfreightcostrule", windowId + "|FreightCostRule");     data.adOrgtrxId = vars.getStringParameter("inpadOrgtrxId");     data.docaction = vars.getRequiredStringParameter("inpdocaction");     data.copyfrom = vars.getStringParameter("inpcopyfrom");     data.copyfrompo = vars.getStringParameter("inpcopyfrompo");     data.istaxincluded = vars.getStringParameter("inpistaxincluded", "N");     data.rmAddorphanline = vars.getStringParameter("inprmAddorphanline");     data.convertquotation = vars.getStringParameter("inpconvertquotation");     data.cRejectReasonId = vars.getStringParameter("inpcRejectReasonId");     data.validuntil = vars.getStringParameter("inpvaliduntil");     data.calculatePromotions = vars.getStringParameter("inpcalculatePromotions");     data.quotationId = vars.getStringParameter("inpquotationId");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.aAssetId = vars.getStringParameter("inpaAssetId");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.emRcobPurchasequotation = vars.getStringParameter("inpemRcobPurchasequotation");     data.emRcobPurchasequotationr = vars.getStringParameter("inpemRcobPurchasequotation_R");     data.emRcobLineprocess = vars.getStringParameter("inpemRcobLineprocess");     data.emRcobFlag = vars.getStringParameter("inpemRcobFlag", "N");     data.soResStatus = vars.getStringParameter("inpsoResStatus");     data.deliveryLocationId = vars.getStringParameter("inpdeliveryLocationId");     data.isselfservice = vars.getStringParameter("inpisselfservice", "N");     data.dropshipLocationId = vars.getStringParameter("inpdropshipLocationId");     data.dropshipBpartnerId = vars.getStringParameter("inpdropshipBpartnerId");     data.dropshipUserId = vars.getStringParameter("inpdropshipUserId");     data.isselected = vars.getStringParameter("inpisselected", "N");     data.posted = vars.getRequiredGlobalVariable("inpposted", windowId + "|Posted");     data.paymentrule = vars.getRequiredStringParameter("inppaymentrule");     data.issotrx = vars.getRequiredInputGlobalVariable("inpissotrx", windowId + "|IsSOTrx", "N");     data.deliveryrule = vars.getRequiredStringParameter("inpdeliveryrule");     data.processed = vars.getRequiredInputGlobalVariable("inpprocessed", windowId + "|Processed", "N");     data.invoicerule = vars.getRequiredStringParameter("inpinvoicerule");     data.dateacct = vars.getRequiredStringParameter("inpdateacct");     data.isprinted = vars.getStringParameter("inpisprinted", "N");     data.isinvoiced = vars.getStringParameter("inpisinvoiced", "N");     data.isdelivered = vars.getStringParameter("inpisdelivered", "N");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.dateprinted = vars.getStringParameter("inpdateprinted");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.cOrderId = vars.getRequestGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");     data.processing = vars.getStringParameter("inpprocessing"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



          vars.getGlobalVariable("inpordertype", windowId + "|ORDERTYPE", "");
          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "C_Order", data.cDoctypetargetId, data.cDoctypeId, false, true);

    
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|DateOrdered", data[0].getField("dateordered"));    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].getField("cBpartnerId"));    vars.setSessionValue(windowId + "|C_BPartner_Location_ID", data[0].getField("cBpartnerLocationId"));    vars.setSessionValue(windowId + "|M_Warehouse_ID", data[0].getField("mWarehouseId"));    vars.setSessionValue(windowId + "|DatePromised", data[0].getField("datepromised"));    vars.setSessionValue(windowId + "|M_PriceList_ID", data[0].getField("mPricelistId"));    vars.setSessionValue(windowId + "|DocStatus", data[0].getField("docstatus"));    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].getField("cCurrencyId"));    vars.setSessionValue(windowId + "|C_DocType_ID", data[0].getField("cDoctypeId"));    vars.setSessionValue(windowId + "|DeliveryViaRule", data[0].getField("deliveryviarule"));    vars.setSessionValue(windowId + "|M_Shipper_ID", data[0].getField("mShipperId"));    vars.setSessionValue(windowId + "|FreightCostRule", data[0].getField("freightcostrule"));    vars.setSessionValue(windowId + "|Posted", data[0].getField("posted"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|C_Order_ID", data[0].getField("cOrderId"));    vars.setSessionValue(windowId + "|Processed", data[0].getField("processed"));    vars.setSessionValue(windowId + "|IsSOTrx", data[0].getField("issotrx"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpcOrderId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamAD_Org_ID = vars.getSessionValue(tabId + "|paramAD_Org_ID");
String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamPOReference = vars.getSessionValue(tabId + "|paramPOReference");
String strParamDateOrdered = vars.getSessionValue(tabId + "|paramDateOrdered");
String strParamC_DocTypeTarget_ID = vars.getSessionValue(tabId + "|paramC_DocTypeTarget_ID");
String strParamC_BPartner_ID = vars.getSessionValue(tabId + "|paramC_BPartner_ID");
String strParamInvoiceRule = vars.getSessionValue(tabId + "|paramInvoiceRule");
String strParamGrandTotal = vars.getSessionValue(tabId + "|paramGrandTotal");
String strParamDocStatus = vars.getSessionValue(tabId + "|paramDocStatus");
String strParamDateOrdered_f = vars.getSessionValue(tabId + "|paramDateOrdered_f");
String strParamGrandTotal_f = vars.getSessionValue(tabId + "|paramGrandTotal_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Org_ID) && ("").equals(strParamDocumentNo) && ("").equals(strParamPOReference) && ("").equals(strParamDateOrdered) && ("").equals(strParamC_DocTypeTarget_ID) && ("").equals(strParamC_BPartner_ID) && ("").equals(strParamInvoiceRule) && ("").equals(strParamGrandTotal) && ("").equals(strParamDocStatus) && ("").equals(strParamDateOrdered_f) && ("").equals(strParamGrandTotal_f)) || !(("").equals(strParamAD_Org_ID) || ("%").equals(strParamAD_Org_ID))  || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamPOReference) || ("%").equals(strParamPOReference))  || !(("").equals(strParamDateOrdered) || ("%").equals(strParamDateOrdered))  || !(("").equals(strParamC_DocTypeTarget_ID) || ("%").equals(strParamC_DocTypeTarget_ID))  || !(("").equals(strParamC_BPartner_ID) || ("%").equals(strParamC_BPartner_ID))  || !(("").equals(strParamInvoiceRule) || ("%").equals(strParamInvoiceRule))  || !(("").equals(strParamGrandTotal) || ("%").equals(strParamGrandTotal))  || !(("").equals(strParamDocStatus) || ("%").equals(strParamDocStatus))  || !(("").equals(strParamDateOrdered_f) || ("%").equals(strParamDateOrdered_f))  || !(("").equals(strParamGrandTotal_f) || ("%").equals(strParamGrandTotal_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strC_Order_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strC_Order_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseOrder/Header_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Header", false, "document.frmMain.inpcOrderId", "grid", "../com.rcss.humanresource/erpCommon/ad_reports/ReportPurchaseOrder.pdf", "N".equals("Y"), "PurchaseOrder", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "cOrderId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "PurchaseOrder", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strC_Order_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " C_Order.DocumentNo DESC";
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
String strParamDateOrdered = vars.getSessionValue(tabId + "|paramDateOrdered");
String strParamC_DocTypeTarget_ID = vars.getSessionValue(tabId + "|paramC_DocTypeTarget_ID");
String strParamC_BPartner_ID = vars.getSessionValue(tabId + "|paramC_BPartner_ID");
String strParamInvoiceRule = vars.getSessionValue(tabId + "|paramInvoiceRule");
String strParamGrandTotal = vars.getSessionValue(tabId + "|paramGrandTotal");
String strParamDocStatus = vars.getSessionValue(tabId + "|paramDocStatus");
String strParamDateOrdered_f = vars.getSessionValue(tabId + "|paramDateOrdered_f");
String strParamGrandTotal_f = vars.getSessionValue(tabId + "|paramGrandTotal_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Org_ID) && ("").equals(strParamDocumentNo) && ("").equals(strParamPOReference) && ("").equals(strParamDateOrdered) && ("").equals(strParamC_DocTypeTarget_ID) && ("").equals(strParamC_BPartner_ID) && ("").equals(strParamInvoiceRule) && ("").equals(strParamGrandTotal) && ("").equals(strParamDocStatus) && ("").equals(strParamDateOrdered_f) && ("").equals(strParamGrandTotal_f)) || !(("").equals(strParamAD_Org_ID) || ("%").equals(strParamAD_Org_ID))  || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamPOReference) || ("%").equals(strParamPOReference))  || !(("").equals(strParamDateOrdered) || ("%").equals(strParamDateOrdered))  || !(("").equals(strParamC_DocTypeTarget_ID) || ("%").equals(strParamC_DocTypeTarget_ID))  || !(("").equals(strParamC_BPartner_ID) || ("%").equals(strParamC_BPartner_ID))  || !(("").equals(strParamInvoiceRule) || ("%").equals(strParamInvoiceRule))  || !(("").equals(strParamGrandTotal) || ("%").equals(strParamGrandTotal))  || !(("").equals(strParamDocStatus) || ("%").equals(strParamDocStatus))  || !(("").equals(strParamDateOrdered_f) || ("%").equals(strParamDateOrdered_f))  || !(("").equals(strParamGrandTotal_f) || ("%").equals(strParamGrandTotal_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strC_Order_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strC_Order_ID.equals("") && (data == null || data.length==0)) {
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
      if (dataField.getField("cOrderId") == null || dataField.getField("cOrderId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strORDERTYPE = HeaderData.selectAux8(this, ((dataField!=null)?dataField.getField("cOrderId"):((data==null || data.length==0)?"":data[0].cOrderId)));
    vars.setSessionValue(windowId + "|ORDERTYPE", strORDERTYPE);
        String strDOCBASETYPE = HeaderData.selectAux17F5DEA4A5254BDDA77560A7D67667B6(this, ((dataField!=null)?dataField.getField("cDoctypetargetId"):((data==null || data.length==0)?"":data[0].cDoctypetargetId)));
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = HeaderData.set(Utility.getDefault(this, vars, "EM_Epcg_Type", "", "181", "", dataField), Utility.getDefault(this, vars, "Convertquotation", "", "181", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Epcgone", "", "181", "", dataField), Utility.getDefault(this, vars, "validuntil", "", "181", "", dataField), Utility.getDefault(this, vars, "A_Asset_ID", "", "181", "", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "181", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@#AD_Org_ID@", "181", "", dataField), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "181", "", dataField), HeaderData.selectDef2166_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "181", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "181", "", dataField), HeaderData.selectDef2168_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "181", "", dataField)), Utility.getDefault(this, vars, "DocumentNo", "", "181", "", dataField), Utility.getDefault(this, vars, "DocStatus", "DR", "181", "", dataField), Utility.getDefault(this, vars, "DocAction", "CO", "181", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "FF80818130217A35013021A672400035", Utility.getDefault(this, vars, "DocAction", "CO", "181", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "FF80818130217A35013021A672400035", Utility.getDefault(this, vars, "DocAction", "CO", "181", "N", dataField))), Utility.getDefault(this, vars, "C_DocType_ID", "0", "181", "", dataField), Utility.getDefault(this, vars, "C_DocTypeTarget_ID", "6BC89C4724494C1EB2355EB772C63CDA", "181", "", dataField), Utility.getDefault(this, vars, "Description", "", "181", "", dataField), Utility.getDefault(this, vars, "IsDelivered", "", "181", "N", dataField), Utility.getDefault(this, vars, "IsInvoiced", "", "181", "N", dataField), Utility.getDefault(this, vars, "IsPrinted", "", "181", "N", dataField), Utility.getDefault(this, vars, "DateOrdered", "@#Date@", "181", "", dataField), Utility.getDefault(this, vars, "DatePromised", "@#Date@", "181", "", dataField), Utility.getDefault(this, vars, "DateAcct", "@#Date@", "181", "", dataField), Utility.getDefault(this, vars, "SalesRep_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "C_PaymentTerm_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "BillTo_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "@C_Currency_ID@", "181", "", dataField), Utility.getDefault(this, vars, "InvoiceRule", "D", "181", "", dataField), Utility.getDefault(this, vars, "FreightAmt", "", "181", "0", dataField), Utility.getDefault(this, vars, "DeliveryViaRule", "P", "181", "", dataField), Utility.getDefault(this, vars, "M_Shipper_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "PriorityRule", "5", "181", "", dataField), Utility.getDefault(this, vars, "TotalLines", "", "181", "0", dataField), Utility.getDefault(this, vars, "GrandTotal", "", "181", "0", dataField), HeaderData.selectDef2202(this, Utility.getContext(this, vars, "ad_org_id", "181")), Utility.getDefault(this, vars, "M_PriceList_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "Processing", "", "181", "N", dataField), Utility.getDefault(this, vars, "C_Campaign_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "C_BPartner_ID", "", "181", "", dataField), HeaderData.selectDef2762_2(this, Utility.getDefault(this, vars, "C_BPartner_ID", "", "181", "", dataField)), Utility.getDefault(this, vars, "AD_User_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "POReference", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Charge_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "ChargeAmt", "0", "181", "", dataField), Utility.getDefault(this, vars, "Processed", "", "181", "N", dataField), Utility.getDefault(this, vars, "C_BPartner_Location_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "181", "", dataField), HeaderData.selectDef3402_3(this, Utility.getDefault(this, vars, "C_Project_ID", "", "181", "", dataField)), Utility.getDefault(this, vars, "C_Activity_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "Quotation_ID", "", "181", "", dataField), HeaderData.selectDef367DCAA9CF4442ADB9A76F6539102217_4(this, Utility.getDefault(this, vars, "Quotation_ID", "", "181", "", dataField)), Utility.getDefault(this, vars, "IsSOTrx", "@IsSOTrx@", "181", "N", dataField), Utility.getDefault(this, vars, "DatePrinted", "", "181", "", dataField), Utility.getDefault(this, vars, "DeliveryRule", "A", "181", "", dataField), Utility.getDefault(this, vars, "FreightCostRule", "I", "181", "", dataField), Utility.getDefault(this, vars, "EM_Rcob_Flag", "N", "181", "N", dataField), Utility.getDefault(this, vars, "PaymentRule", "B", "181", "", dataField), Utility.getDefault(this, vars, "IsDiscountPrinted", "", "181", "N", dataField), Utility.getDefault(this, vars, "Posted", "N", "181", "N", dataField), Utility.getDefault(this, vars, "IsTaxIncluded", "", "181", "N", dataField), Utility.getDefault(this, vars, "IsSelected", "", "181", "N", dataField), Utility.getDefault(this, vars, "EM_Rcob_Purchasequotation", "", "181", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_For", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Calcdistcost", "N", "181", "N", dataField), Utility.getDefault(this, vars, "Deliverynotes", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Incoterms_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "Incotermsdescription", "", "181", "", dataField), Utility.getDefault(this, vars, "Generatetemplate", "", "181", "N", dataField), Utility.getDefault(this, vars, "Delivery_Location_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "CopyFromPO", "", "181", "N", dataField), Utility.getDefault(this, vars, "FIN_Paymentmethod_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "DropShip_User_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "DropShip_BPartner_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "CopyFrom", "", "181", "N", dataField), Utility.getDefault(this, vars, "DropShip_Location_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "IsSelfService", "", "181", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Transporter", "", "181", "", dataField), Utility.getDefault(this, vars, "AD_OrgTrx_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "Calculate_Promotions", "", "181", "N", dataField), Utility.getDefault(this, vars, "RM_PickFromShipment", "", "181", "N", dataField), Utility.getDefault(this, vars, "RM_ReceiveMaterials", "", "181", "N", dataField), Utility.getDefault(this, vars, "RM_CreateInvoice", "", "181", "N", dataField), Utility.getDefault(this, vars, "C_Return_Reason_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Check", "", "181", "N", dataField), Utility.getDefault(this, vars, "RM_AddOrphanLine", "", "181", "N", dataField), Utility.getDefault(this, vars, "SO_Res_Status", "", "181", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Tin", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Reject_Reason_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Discounttype", "", "181", "", dataField), Utility.getDefault(this, vars, "EM_Rcob_Lineprocess", "", "181", "N", dataField), Utility.getDefault(this, vars, "EM_Rcob_RegDealer", "", "181", "N", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Discount", "0", "181", "0", dataField));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "C_Order", data[0].cDoctypetargetId, data[0].cDoctypeId, false, false) + ">";
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseOrder/Header_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseOrder/Header_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Header", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpcOrderId", "", "../com.rcss.humanresource/erpCommon/ad_reports/ReportPurchaseOrder.pdf", "N".equals("Y"), "PurchaseOrder", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strC_Order_ID), !hasReadOnlyAccess);
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
      // if (!strC_Order_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "PurchaseOrder", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Header_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("ORDERTYPE", strORDERTYPE);
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
    
    
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
comboTableData = new ComboTableData(vars, this, "18", "C_DocTypeTarget_ID", "AA2052554CCE4E098120E8293E1F16F1", "133", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypetargetId"):dataField.getField("cDoctypetargetId")));
xmlDocument.setData("reportC_DocTypeTarget_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "EM_Epcg_Epcgone", "4C55EE2EE4D74DF39075E3619FE5079E", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("emEpcgEpcgone"):dataField.getField("emEpcgEpcgone")));
xmlDocument.setData("reportEM_Epcg_Epcgone","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "C_BPartner_Location_ID", "", "167", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cBpartnerLocationId"):dataField.getField("cBpartnerLocationId")));
xmlDocument.setData("reportC_BPartner_Location_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "M_Warehouse_ID", "98BCC2287E7E481C97E5612DD59E8224", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mWarehouseId"):dataField.getField("mWarehouseId")));
xmlDocument.setData("reportM_Warehouse_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("DatePromised_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "FIN_Paymentmethod_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finPaymentmethodId"):dataField.getField("finPaymentmethodId")));
xmlDocument.setData("reportFIN_Paymentmethod_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cPaymenttermId"):dataField.getField("cPaymenttermId")));
xmlDocument.setData("reportC_PaymentTerm_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_ID", "", "DB85A30739C3487988921CE3FFFD3BAD", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mPricelistId"):dataField.getField("mPricelistId")));
xmlDocument.setData("reportM_PriceList_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonEM_Rcfr_Discount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("EM_Rcfr_Calcdistcost_BTNname", Utility.getButtonName(this, vars, "FEE939D6C0ED40D4A4691672415CD939", "EM_Rcfr_Calcdistcost_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_Rcfr_Calcdistcost = org.openbravo.erpCommon.utility.Utility.isModalProcess("20E70A9D32F644C0BFD3AA1C90566AB3"); 
xmlDocument.setParameter("EM_Rcfr_Calcdistcost_Modal", modalEM_Rcfr_Calcdistcost?"true":"false");
comboTableData = new ComboTableData(vars, this, "17", "EM_Epcg_Type", "A69BE62857614DA8950AA482E2884D6E", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("emEpcgType"):dataField.getField("emEpcgType")));
xmlDocument.setData("reportEM_Epcg_Type","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonGrandTotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalLines", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonFreightAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonChargeAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("DocAction_BTNname", Utility.getButtonName(this, vars, "FF80818130217A35013021A672400035", (dataField==null?data[0].getField("docaction"):dataField.getField("docaction")), "DocAction_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalDocAction = org.openbravo.erpCommon.utility.Utility.isModalProcess("104"); 
xmlDocument.setParameter("DocAction_Modal", modalDocAction?"true":"false");
xmlDocument.setParameter("CopyFrom_BTNname", Utility.getButtonName(this, vars, "6506", "CopyFrom_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCopyFrom = org.openbravo.erpCommon.utility.Utility.isModalProcess("211"); 
xmlDocument.setParameter("CopyFrom_Modal", modalCopyFrom?"true":"false");
xmlDocument.setParameter("CopyFromPO_BTNname", Utility.getButtonName(this, vars, "804197", "CopyFromPO_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCopyFromPO = org.openbravo.erpCommon.utility.Utility.isModalProcess("800165"); 
xmlDocument.setParameter("CopyFromPO_Modal", modalCopyFromPO?"true":"false");
xmlDocument.setParameter("validuntil_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "18", "EM_Rcob_Purchasequotation", "2C4C8AF881294ABE8804E8A8AED35D9D", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("emRcobPurchasequotation"):dataField.getField("emRcobPurchasequotation")));
xmlDocument.setData("reportEM_Rcob_Purchasequotation","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("EM_Rcob_Lineprocess_BTNname", Utility.getButtonName(this, vars, "47E6D5B989604785BC7327ED1529B493", "EM_Rcob_Lineprocess_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_Rcob_Lineprocess = org.openbravo.erpCommon.utility.Utility.isModalProcess("173F6DB31A1643A985E46CA1F15DBD7A"); 
xmlDocument.setParameter("EM_Rcob_Lineprocess_Modal", modalEM_Rcob_Lineprocess?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("DateAcct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
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

    private void printPageButtonDocAction104(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String strdocaction, String strProcessing, String strdocstatus, String stradTableId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 104");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocAction", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "104");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("104");
        vars.removeMessage("104");
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
xmlDocument.setParameter("processId", "104");
xmlDocument.setParameter("processDescription", "Process Order");
xmlDocument.setParameter("docaction", (strdocaction.equals("--")?"CL":strdocaction));
FieldProvider[] dataDocAction = ActionButtonUtility.docAction(this, vars, strdocaction, "FF80818130217A35013021A672400035", strdocstatus, strProcessing, stradTableId, tabId);
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


    void printPageButtonEM_Rcfr_Calcdistcost20E70A9D32F644C0BFD3AA1C90566AB3(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String stremRcfrCalcdistcost, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 20E70A9D32F644C0BFD3AA1C90566AB3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Rcfr_Calcdistcost20E70A9D32F644C0BFD3AA1C90566AB3", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "20E70A9D32F644C0BFD3AA1C90566AB3");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("20E70A9D32F644C0BFD3AA1C90566AB3");
        vars.removeMessage("20E70A9D32F644C0BFD3AA1C90566AB3");
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
    void printPageButtonRM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String strrmAddorphanline, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 23D1B163EC0B41F790CE39BF01DA320E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/RM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "23D1B163EC0B41F790CE39BF01DA320E");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("23D1B163EC0B41F790CE39BF01DA320E");
        vars.removeMessage("23D1B163EC0B41F790CE39BF01DA320E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_Product_ID", "");
    xmlDocument.setParameter("Returned", "");
    xmlDocument.setParameter("PriceStd", "");
    xmlDocument.setParameter("C_Tax_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Tax_ID", "", "299FA667CF374AC5ACC74739C3251134", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button23D1B163EC0B41F790CE39BF01DA320E.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_Tax_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Return_Reason_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Return_Reason_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button23D1B163EC0B41F790CE39BF01DA320E.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_Return_Reason_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonConvertquotationA3FE1F9892394386A49FB707AA50A0FA(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String strconvertquotation, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A3FE1F9892394386A49FB707AA50A0FA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ConvertquotationA3FE1F9892394386A49FB707AA50A0FA", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "A3FE1F9892394386A49FB707AA50A0FA");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("A3FE1F9892394386A49FB707AA50A0FA");
        vars.removeMessage("A3FE1F9892394386A49FB707AA50A0FA");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("RecalculatePrices", "Y");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonCalculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String strcalculatePromotions, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9EB2228A60684C0DBEC12D5CD8D85218");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Calculate_Promotions9EB2228A60684C0DBEC12D5CD8D85218", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
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
    void printPageButtonEM_Rcob_Lineprocess173F6DB31A1643A985E46CA1F15DBD7A(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String stremRcobLineprocess, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 173F6DB31A1643A985E46CA1F15DBD7A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Rcob_Lineprocess173F6DB31A1643A985E46CA1F15DBD7A", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "173F6DB31A1643A985E46CA1F15DBD7A");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("173F6DB31A1643A985E46CA1F15DBD7A");
        vars.removeMessage("173F6DB31A1643A985E46CA1F15DBD7A");
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
                data.cOrderId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (HeaderData.getCurrentDBTimestamp(this, data.cOrderId).equals(
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
                    data.cOrderId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.docactionBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "FF80818130217A35013021A672400035", data.getField("DocAction"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|C_Order_ID", data.cOrderId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Header. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
