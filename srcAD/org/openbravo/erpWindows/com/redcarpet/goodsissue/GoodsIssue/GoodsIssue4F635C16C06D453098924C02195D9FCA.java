
package org.openbravo.erpWindows.com.redcarpet.goodsissue.GoodsIssue;




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

public class GoodsIssue4F635C16C06D453098924C02195D9FCA extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(GoodsIssue4F635C16C06D453098924C02195D9FCA.class);
  
  private static final String windowId = "85F3EEC77B1648AF8B533C9144EB7A51";
  private static final String tabId = "4F635C16C06D453098924C02195D9FCA";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "C71D22E39B944C0EA8C5D2B655E53B3F";
  
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
     
      if (command.contains("6DB70589347647CDB5C8409320947308")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("6DB70589347647CDB5C8409320947308");
        SessionInfo.setModuleId("C71D22E39B944C0EA8C5D2B655E53B3F");
      }
     
      if (command.contains("E0A6DA47391A4AFDBF738C7F17607DC0")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("E0A6DA47391A4AFDBF738C7F17607DC0");
        SessionInfo.setModuleId("C71D22E39B944C0EA8C5D2B655E53B3F");
      }
     
      if (command.contains("DAB5D8627EE5415E9FF2531998E303B6")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("DAB5D8627EE5415E9FF2531998E303B6");
        SessionInfo.setModuleId("C71D22E39B944C0EA8C5D2B655E53B3F");
      }
     
      if (command.contains("D39B25EADB224BB59E2A4F758A867D44")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("D39B25EADB224BB59E2A4F758A867D44");
        SessionInfo.setModuleId("C71D22E39B944C0EA8C5D2B655E53B3F");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("6DB70589347647CDB5C8409320947308")) {
        classInfo.type = "P";
        classInfo.id = "6DB70589347647CDB5C8409320947308";
      }
     
      if (securedProcess && command.contains("E0A6DA47391A4AFDBF738C7F17607DC0")) {
        classInfo.type = "P";
        classInfo.id = "E0A6DA47391A4AFDBF738C7F17607DC0";
      }
     
      if (securedProcess && command.contains("DAB5D8627EE5415E9FF2531998E303B6")) {
        classInfo.type = "P";
        classInfo.id = "DAB5D8627EE5415E9FF2531998E303B6";
      }
     
      if (securedProcess && command.contains("D39B25EADB224BB59E2A4F758A867D44")) {
        classInfo.type = "P";
        classInfo.id = "D39B25EADB224BB59E2A4F758A867D44";
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
        String strrcgiGoodsissueId = request.getParameter("inprcgiGoodsissueId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrcgiGoodsissueId.equals(""))
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

      String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRcgi_Goodsissue_ID.equals("")) strRcgi_Goodsissue_ID = firstElement(vars, tableSQL);
          if (strRcgi_Goodsissue_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcgi_Goodsissue_ID, tableSQL);

      else printPageDataSheet(response, vars, strRcgi_Goodsissue_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRcgi_Goodsissue_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRcgi_Goodsissue_ID.equals("")) strRcgi_Goodsissue_ID = vars.getRequiredGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID");
      else vars.setSessionValue(windowId + "|Rcgi_Goodsissue_ID", strRcgi_Goodsissue_ID);
      
      vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.view", "EDIT");

      printPageEdit(response, request, vars, false, strRcgi_Goodsissue_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.view");
      String strRcgi_Goodsissue_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRcgi_Goodsissue_ID = firstElement(vars, tableSQL);
          if (strRcgi_Goodsissue_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRcgi_Goodsissue_ID.equals("")) strRcgi_Goodsissue_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRcgi_Goodsissue_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentno", tabId + "|paramDocumentno");
vars.getRequestGlobalVariable("inpParamName", tabId + "|paramName");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rcgi_Goodsissue_ID");
      String strRcgi_Goodsissue_ID="";

      String strView = vars.getSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRcgi_Goodsissue_ID = firstElement(vars, tableSQL);
        if (strRcgi_Goodsissue_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcgi_Goodsissue_ID, tableSQL);

      else printPageDataSheet(response, vars, strRcgi_Goodsissue_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID", "");
      vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.view", "RELATION");
      printPageDataSheet(response, vars, strRcgi_Goodsissue_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID", "");
      vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRcgi_Goodsissue_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRcgi_Goodsissue_ID = vars.getRequiredStringParameter("inprcgiGoodsissueId");
      
      String strNext = nextElement(vars, strRcgi_Goodsissue_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRcgi_Goodsissue_ID = vars.getRequiredStringParameter("inprcgiGoodsissueId");
      
      String strPrevious = previousElement(vars, strRcgi_Goodsissue_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rcgi_Goodsissue_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rcgi_Goodsissue_ID");

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

      String strRcgi_Goodsissue_ID = vars.getRequiredGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID");
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
          String strNext = nextElement(vars, strRcgi_Goodsissue_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rcgi_Goodsissue_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRcgi_Goodsissue_ID = vars.getRequiredStringParameter("inprcgiGoodsissueId");
      //GoodsIssue4F635C16C06D453098924C02195D9FCAData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = GoodsIssue4F635C16C06D453098924C02195D9FCAData.delete(this, strRcgi_Goodsissue_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rcgiGoodsissueId");
        vars.setSessionValue(tabId + "|GoodsIssue4F635C16C06D453098924C02195D9FCA.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONCreatelines6DB70589347647CDB5C8409320947308")) {
        vars.setSessionValue("button6DB70589347647CDB5C8409320947308.strcreatelines", vars.getStringParameter("inpcreatelines"));
        vars.setSessionValue("button6DB70589347647CDB5C8409320947308.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button6DB70589347647CDB5C8409320947308.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button6DB70589347647CDB5C8409320947308.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button6DB70589347647CDB5C8409320947308.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "6DB70589347647CDB5C8409320947308", request.getServletPath());
      } else if (vars.commandIn("BUTTON6DB70589347647CDB5C8409320947308")) {
        String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID", "");
        String strcreatelines = vars.getSessionValue("button6DB70589347647CDB5C8409320947308.strcreatelines");
        String strProcessing = vars.getSessionValue("button6DB70589347647CDB5C8409320947308.strProcessing");
        String strOrg = vars.getSessionValue("button6DB70589347647CDB5C8409320947308.strOrg");
        String strClient = vars.getSessionValue("button6DB70589347647CDB5C8409320947308.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCreatelines6DB70589347647CDB5C8409320947308(response, vars, strRcgi_Goodsissue_ID, strcreatelines, strProcessing);
        }
    } else if (vars.commandIn("BUTTONProcessE0A6DA47391A4AFDBF738C7F17607DC0")) {
        vars.setSessionValue("buttonE0A6DA47391A4AFDBF738C7F17607DC0.strprocess", vars.getStringParameter("inpprocess"));
        vars.setSessionValue("buttonE0A6DA47391A4AFDBF738C7F17607DC0.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonE0A6DA47391A4AFDBF738C7F17607DC0.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonE0A6DA47391A4AFDBF738C7F17607DC0.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonE0A6DA47391A4AFDBF738C7F17607DC0.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "E0A6DA47391A4AFDBF738C7F17607DC0", request.getServletPath());
      } else if (vars.commandIn("BUTTONE0A6DA47391A4AFDBF738C7F17607DC0")) {
        String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID", "");
        String strprocess = vars.getSessionValue("buttonE0A6DA47391A4AFDBF738C7F17607DC0.strprocess");
        String strProcessing = vars.getSessionValue("buttonE0A6DA47391A4AFDBF738C7F17607DC0.strProcessing");
        String strOrg = vars.getSessionValue("buttonE0A6DA47391A4AFDBF738C7F17607DC0.strOrg");
        String strClient = vars.getSessionValue("buttonE0A6DA47391A4AFDBF738C7F17607DC0.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessE0A6DA47391A4AFDBF738C7F17607DC0(response, vars, strRcgi_Goodsissue_ID, strprocess, strProcessing);
        }
    } else if (vars.commandIn("BUTTONPostxDAB5D8627EE5415E9FF2531998E303B6")) {
        vars.setSessionValue("buttonDAB5D8627EE5415E9FF2531998E303B6.strpostx", vars.getStringParameter("inppostx"));
        vars.setSessionValue("buttonDAB5D8627EE5415E9FF2531998E303B6.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonDAB5D8627EE5415E9FF2531998E303B6.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonDAB5D8627EE5415E9FF2531998E303B6.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonDAB5D8627EE5415E9FF2531998E303B6.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "DAB5D8627EE5415E9FF2531998E303B6", request.getServletPath());
      } else if (vars.commandIn("BUTTONDAB5D8627EE5415E9FF2531998E303B6")) {
        String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID", "");
        String strpostx = vars.getSessionValue("buttonDAB5D8627EE5415E9FF2531998E303B6.strpostx");
        String strProcessing = vars.getSessionValue("buttonDAB5D8627EE5415E9FF2531998E303B6.strProcessing");
        String strOrg = vars.getSessionValue("buttonDAB5D8627EE5415E9FF2531998E303B6.strOrg");
        String strClient = vars.getSessionValue("buttonDAB5D8627EE5415E9FF2531998E303B6.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonPostxDAB5D8627EE5415E9FF2531998E303B6(response, vars, strRcgi_Goodsissue_ID, strpostx, strProcessing);
        }
    } else if (vars.commandIn("BUTTONCompletenewD39B25EADB224BB59E2A4F758A867D44")) {
        vars.setSessionValue("buttonD39B25EADB224BB59E2A4F758A867D44.strcompletenew", vars.getStringParameter("inpcompletenew"));
        vars.setSessionValue("buttonD39B25EADB224BB59E2A4F758A867D44.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonD39B25EADB224BB59E2A4F758A867D44.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonD39B25EADB224BB59E2A4F758A867D44.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonD39B25EADB224BB59E2A4F758A867D44.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "D39B25EADB224BB59E2A4F758A867D44", request.getServletPath());
      } else if (vars.commandIn("BUTTOND39B25EADB224BB59E2A4F758A867D44")) {
        String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID", "");
        String strcompletenew = vars.getSessionValue("buttonD39B25EADB224BB59E2A4F758A867D44.strcompletenew");
        String strProcessing = vars.getSessionValue("buttonD39B25EADB224BB59E2A4F758A867D44.strProcessing");
        String strOrg = vars.getSessionValue("buttonD39B25EADB224BB59E2A4F758A867D44.strOrg");
        String strClient = vars.getSessionValue("buttonD39B25EADB224BB59E2A4F758A867D44.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCompletenewD39B25EADB224BB59E2A4F758A867D44(response, vars, strRcgi_Goodsissue_ID, strcompletenew, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONCreatelines6DB70589347647CDB5C8409320947308")) {
        String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcgi_Goodsissue_ID", "");
        
        ProcessBundle pb = new ProcessBundle("6DB70589347647CDB5C8409320947308", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcgi_Goodsissue_ID", strRcgi_Goodsissue_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONProcessE0A6DA47391A4AFDBF738C7F17607DC0")) {
        String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcgi_Goodsissue_ID", "");
        
        ProcessBundle pb = new ProcessBundle("E0A6DA47391A4AFDBF738C7F17607DC0", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcgi_Goodsissue_ID", strRcgi_Goodsissue_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONPostxDAB5D8627EE5415E9FF2531998E303B6")) {
        String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcgi_Goodsissue_ID", "");
        
        ProcessBundle pb = new ProcessBundle("DAB5D8627EE5415E9FF2531998E303B6", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcgi_Goodsissue_ID", strRcgi_Goodsissue_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONCompletenewD39B25EADB224BB59E2A4F758A867D44")) {
        String strRcgi_Goodsissue_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcgi_Goodsissue_ID", "");
        
        ProcessBundle pb = new ProcessBundle("D39B25EADB224BB59E2A4F758A867D44", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcgi_Goodsissue_ID", strRcgi_Goodsissue_ID);
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
  private GoodsIssue4F635C16C06D453098924C02195D9FCAData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    GoodsIssue4F635C16C06D453098924C02195D9FCAData data = new GoodsIssue4F635C16C06D453098924C02195D9FCAData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.name = vars.getStringParameter("inpname");     data.movementdate = vars.getRequiredStringParameter("inpmovementdate");     data.mRequisitionId = vars.getStringParameter("inpmRequisitionId");     data.mRequisitionIdr = vars.getStringParameter("inpmRequisitionId_R");     data.rchrEmpDeptId = vars.getRequiredStringParameter("inprchrEmpDeptId");     data.rchrEmpDeptIdr = vars.getStringParameter("inprchrEmpDeptId_R");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user1Idr = vars.getStringParameter("inpuser1Id_R");     data.description = vars.getStringParameter("inpdescription");     data.createfromindent = vars.getRequiredStringParameter("inpcreatefromindent");     data.createlines = vars.getRequiredStringParameter("inpcreatelines");     data.process = vars.getRequiredStringParameter("inpprocess");     data.postx = vars.getRequiredStringParameter("inppostx");     data.processed = vars.getRequiredInputGlobalVariable("inpprocessed", windowId + "|Processed", "N");    try {   data.totallines = vars.getNumericParameter("inptotallines");  } catch (ServletException paramEx) { ex = paramEx; }     data.completenew = vars.getStringParameter("inpcompletenew");     data.docstatus = vars.getStringParameter("inpdocstatus");     data.docstatusr = vars.getStringParameter("inpdocstatus_R");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rcgiGoodsissueId = vars.getRequestGlobalVariable("inprcgiGoodsissueId", windowId + "|Rcgi_Goodsissue_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Processed", data[0].getField("processed"));    vars.setSessionValue(windowId + "|Rcgi_Goodsissue_ID", data[0].getField("rcgiGoodsissueId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      GoodsIssue4F635C16C06D453098924C02195D9FCAData[] data = GoodsIssue4F635C16C06D453098924C02195D9FCAData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprcgiGoodsissueId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRcgi_Goodsissue_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");
String strParamName = vars.getSessionValue(tabId + "|paramName");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno) && ("").equals(strParamName)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno))  || !(("").equals(strParamName) || ("%").equals(strParamName)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRcgi_Goodsissue_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRcgi_Goodsissue_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/GoodsIssue/GoodsIssue4F635C16C06D453098924C02195D9FCA_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "GoodsIssue4F635C16C06D453098924C02195D9FCA", false, "document.frmMain.inprcgiGoodsissueId", "grid", "../com.rcss.humanresource/erpCommon/ad_reports/ReportGoodsIssue.pdf", "N".equals("Y"), "GoodsIssue", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rcgiGoodsissueId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "GoodsIssue4F635C16C06D453098924C02195D9FCA_Relation.html", "GoodsIssue", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "GoodsIssue4F635C16C06D453098924C02195D9FCA_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRcgi_Goodsissue_ID, TableSQLData tableSQL)
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
    GoodsIssue4F635C16C06D453098924C02195D9FCAData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");
String strParamName = vars.getSessionValue(tabId + "|paramName");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno) && ("").equals(strParamName)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno))  || !(("").equals(strParamName) || ("%").equals(strParamName)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = GoodsIssue4F635C16C06D453098924C02195D9FCAData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRcgi_Goodsissue_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRcgi_Goodsissue_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new GoodsIssue4F635C16C06D453098924C02195D9FCAData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rcgiGoodsissueId") == null || dataField.getField("rcgiGoodsissueId").equals("")) {
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
        data = GoodsIssue4F635C16C06D453098924C02195D9FCAData.set(Utility.getDefault(this, vars, "Completenew", "", "85F3EEC77B1648AF8B533C9144EB7A51", "N", dataField), Utility.getDefault(this, vars, "Totallines", "0", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "Name", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), GoodsIssue4F635C16C06D453098924C02195D9FCAData.selectDef4417C3DC5BC74AC59897504B0905FEC0_0(this, Utility.getDefault(this, vars, "Createdby", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField)), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "Postx", "N", "85F3EEC77B1648AF8B533C9144EB7A51", "N", dataField), Utility.getDefault(this, vars, "Processed", "N", "85F3EEC77B1648AF8B533C9144EB7A51", "N", dataField), Utility.getDefault(this, vars, "Createfromindent", "N", "85F3EEC77B1648AF8B533C9144EB7A51", "N", dataField), Utility.getDefault(this, vars, "C_Doctype_ID", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "Createlines", "N", "85F3EEC77B1648AF8B533C9144EB7A51", "N", dataField), Utility.getDefault(this, vars, "Docstatus", "DR", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), "", Utility.getDefault(this, vars, "Process", "N", "85F3EEC77B1648AF8B533C9144EB7A51", "N", dataField), Utility.getDefault(this, vars, "Movementdate", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "M_Requisition_ID", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), GoodsIssue4F635C16C06D453098924C02195D9FCAData.selectDefB758E0FCC67D4B6CAA24EF8824C65BB5_1(this, Utility.getDefault(this, vars, "Updatedby", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField)), Utility.getDefault(this, vars, "Description", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), Utility.getDefault(this, vars, "Rchr_emp_dept_id", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField), "Y", Utility.getDefault(this, vars, "Documentno", "", "85F3EEC77B1648AF8B533C9144EB7A51", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/GoodsIssue/GoodsIssue4F635C16C06D453098924C02195D9FCA_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/GoodsIssue/GoodsIssue4F635C16C06D453098924C02195D9FCA_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "GoodsIssue4F635C16C06D453098924C02195D9FCA", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprcgiGoodsissueId", "", "../com.rcss.humanresource/erpCommon/ad_reports/ReportGoodsIssue.pdf", "N".equals("Y"), "GoodsIssue", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRcgi_Goodsissue_ID), !hasReadOnlyAccess);
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
      // if (!strRcgi_Goodsissue_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "GoodsIssue4F635C16C06D453098924C02195D9FCA_Relation.html", "GoodsIssue", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "GoodsIssue4F635C16C06D453098924C02195D9FCA_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "18", "C_Doctype_ID", "85E428A36BF7493DA34E6D653090D924", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_Doctype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Movementdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "M_Requisition_ID", "", "7085F8B02ED148658F6D89608AB995D1", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mRequisitionId"):dataField.getField("mRequisitionId")));
xmlDocument.setData("reportM_Requisition_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_emp_dept_id", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmpDeptId"):dataField.getField("rchrEmpDeptId")));
xmlDocument.setData("reportRchr_emp_dept_id","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "User1_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("user1Id"):dataField.getField("user1Id")));
xmlDocument.setData("reportUser1_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Createlines_BTNname", Utility.getButtonName(this, vars, "3A238199B0BD415E9F780F998BED840D", "Createlines_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCreatelines = org.openbravo.erpCommon.utility.Utility.isModalProcess("6DB70589347647CDB5C8409320947308"); 
xmlDocument.setParameter("Createlines_Modal", modalCreatelines?"true":"false");
xmlDocument.setParameter("Process_BTNname", Utility.getButtonName(this, vars, "39E74F6C20A2484FB705A070E393D904", "Process_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcess = org.openbravo.erpCommon.utility.Utility.isModalProcess("E0A6DA47391A4AFDBF738C7F17607DC0"); 
xmlDocument.setParameter("Process_Modal", modalProcess?"true":"false");
xmlDocument.setParameter("Postx_BTNname", Utility.getButtonName(this, vars, "C2FE6DD6598045D79DCDD848C20DF030", "Postx_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPostx = org.openbravo.erpCommon.utility.Utility.isModalProcess("DAB5D8627EE5415E9FF2531998E303B6"); 
xmlDocument.setParameter("Postx_Modal", modalPostx?"true":"false");
xmlDocument.setParameter("buttonTotallines", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Completenew_BTNname", Utility.getButtonName(this, vars, "50B2160E45FB42848287BB781508A8CD", "Completenew_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCompletenew = org.openbravo.erpCommon.utility.Utility.isModalProcess("D39B25EADB224BB59E2A4F758A867D44"); 
xmlDocument.setParameter("Completenew_Modal", modalCompletenew?"true":"false");
comboTableData = new ComboTableData(vars, this, "17", "Docstatus", "131", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("docstatus"):dataField.getField("docstatus")));
xmlDocument.setData("reportDocstatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
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



    void printPageButtonCreatelines6DB70589347647CDB5C8409320947308(HttpServletResponse response, VariablesSecureApp vars, String strRcgi_Goodsissue_ID, String strcreatelines, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6DB70589347647CDB5C8409320947308");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Createlines6DB70589347647CDB5C8409320947308", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcgi_Goodsissue_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "GoodsIssue4F635C16C06D453098924C02195D9FCA_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "6DB70589347647CDB5C8409320947308");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("6DB70589347647CDB5C8409320947308");
        vars.removeMessage("6DB70589347647CDB5C8409320947308");
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
    void printPageButtonProcessE0A6DA47391A4AFDBF738C7F17607DC0(HttpServletResponse response, VariablesSecureApp vars, String strRcgi_Goodsissue_ID, String strprocess, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E0A6DA47391A4AFDBF738C7F17607DC0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ProcessE0A6DA47391A4AFDBF738C7F17607DC0", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcgi_Goodsissue_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "GoodsIssue4F635C16C06D453098924C02195D9FCA_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "E0A6DA47391A4AFDBF738C7F17607DC0");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("E0A6DA47391A4AFDBF738C7F17607DC0");
        vars.removeMessage("E0A6DA47391A4AFDBF738C7F17607DC0");
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
    void printPageButtonPostxDAB5D8627EE5415E9FF2531998E303B6(HttpServletResponse response, VariablesSecureApp vars, String strRcgi_Goodsissue_ID, String strpostx, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DAB5D8627EE5415E9FF2531998E303B6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/PostxDAB5D8627EE5415E9FF2531998E303B6", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcgi_Goodsissue_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "GoodsIssue4F635C16C06D453098924C02195D9FCA_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "DAB5D8627EE5415E9FF2531998E303B6");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("DAB5D8627EE5415E9FF2531998E303B6");
        vars.removeMessage("DAB5D8627EE5415E9FF2531998E303B6");
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
    void printPageButtonCompletenewD39B25EADB224BB59E2A4F758A867D44(HttpServletResponse response, VariablesSecureApp vars, String strRcgi_Goodsissue_ID, String strcompletenew, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D39B25EADB224BB59E2A4F758A867D44");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/CompletenewD39B25EADB224BB59E2A4F758A867D44", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcgi_Goodsissue_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "GoodsIssue4F635C16C06D453098924C02195D9FCA_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "D39B25EADB224BB59E2A4F758A867D44");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("D39B25EADB224BB59E2A4F758A867D44");
        vars.removeMessage("D39B25EADB224BB59E2A4F758A867D44");
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
    GoodsIssue4F635C16C06D453098924C02195D9FCAData data = null;
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
                data.rcgiGoodsissueId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (GoodsIssue4F635C16C06D453098924C02195D9FCAData.getCurrentDBTimestamp(this, data.rcgiGoodsissueId).equals(
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
                    data.rcgiGoodsissueId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rcgi_Goodsissue_ID", data.rcgiGoodsissueId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet GoodsIssue4F635C16C06D453098924C02195D9FCA. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
