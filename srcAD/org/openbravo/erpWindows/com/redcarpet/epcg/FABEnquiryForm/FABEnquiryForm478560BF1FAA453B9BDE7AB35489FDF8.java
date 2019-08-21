
package org.openbravo.erpWindows.com.redcarpet.epcg.FABEnquiryForm;




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

public class FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.class);
  
  private static final String windowId = "8DFA2AC4EBC840BDB804131A5E2ABF11";
  private static final String tabId = "478560BF1FAA453B9BDE7AB35489FDF8";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "4D05B71E675B4CBD96D8243D03526AE3";
  
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
     
      if (command.contains("0630AAC03F3A4EA7BDE267AD12637E7F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("0630AAC03F3A4EA7BDE267AD12637E7F");
        SessionInfo.setModuleId("4D05B71E675B4CBD96D8243D03526AE3");
      }
     
      if (command.contains("A5AB0D40FD7742388B7B17AF8282D2C0")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("A5AB0D40FD7742388B7B17AF8282D2C0");
        SessionInfo.setModuleId("4D05B71E675B4CBD96D8243D03526AE3");
      }
     
      if (command.contains("26EF860F5FDA4C6B885445D0B381BBE1")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("26EF860F5FDA4C6B885445D0B381BBE1");
        SessionInfo.setModuleId("4D05B71E675B4CBD96D8243D03526AE3");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("0630AAC03F3A4EA7BDE267AD12637E7F")) {
        classInfo.type = "P";
        classInfo.id = "0630AAC03F3A4EA7BDE267AD12637E7F";
      }
     
      if (securedProcess && command.contains("A5AB0D40FD7742388B7B17AF8282D2C0")) {
        classInfo.type = "P";
        classInfo.id = "A5AB0D40FD7742388B7B17AF8282D2C0";
      }
     
      if (securedProcess && command.contains("26EF860F5FDA4C6B885445D0B381BBE1")) {
        classInfo.type = "P";
        classInfo.id = "26EF860F5FDA4C6B885445D0B381BBE1";
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
        String strepcgCostenquiryId = request.getParameter("inpepcgCostenquiryId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strepcgCostenquiryId.equals(""))
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

      String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strEpcg_Costenquiry_ID.equals("")) strEpcg_Costenquiry_ID = firstElement(vars, tableSQL);
          if (strEpcg_Costenquiry_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strEpcg_Costenquiry_ID, tableSQL);

      else printPageDataSheet(response, vars, strEpcg_Costenquiry_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strEpcg_Costenquiry_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strEpcg_Costenquiry_ID.equals("")) strEpcg_Costenquiry_ID = vars.getRequiredGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID");
      else vars.setSessionValue(windowId + "|Epcg_Costenquiry_ID", strEpcg_Costenquiry_ID);
      
      vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.view", "EDIT");

      printPageEdit(response, request, vars, false, strEpcg_Costenquiry_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.view");
      String strEpcg_Costenquiry_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strEpcg_Costenquiry_ID = firstElement(vars, tableSQL);
          if (strEpcg_Costenquiry_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strEpcg_Costenquiry_ID.equals("")) strEpcg_Costenquiry_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strEpcg_Costenquiry_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentno", tabId + "|paramDocumentno");
vars.getRequestGlobalVariable("inpParamUserid", tabId + "|paramUserid");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Epcg_Costenquiry_ID");
      String strEpcg_Costenquiry_ID="";

      String strView = vars.getSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strEpcg_Costenquiry_ID = firstElement(vars, tableSQL);
        if (strEpcg_Costenquiry_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strEpcg_Costenquiry_ID, tableSQL);

      else printPageDataSheet(response, vars, strEpcg_Costenquiry_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID", "");
      vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.view", "RELATION");
      printPageDataSheet(response, vars, strEpcg_Costenquiry_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID", "");
      vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strEpcg_Costenquiry_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strEpcg_Costenquiry_ID = vars.getRequiredStringParameter("inpepcgCostenquiryId");
      
      String strNext = nextElement(vars, strEpcg_Costenquiry_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strEpcg_Costenquiry_ID = vars.getRequiredStringParameter("inpepcgCostenquiryId");
      
      String strPrevious = previousElement(vars, strEpcg_Costenquiry_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Epcg_Costenquiry_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Epcg_Costenquiry_ID");

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

      String strEpcg_Costenquiry_ID = vars.getRequiredGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID");
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
          String strNext = nextElement(vars, strEpcg_Costenquiry_ID, tableSQL);
          vars.setSessionValue(windowId + "|Epcg_Costenquiry_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strEpcg_Costenquiry_ID = vars.getRequiredStringParameter("inpepcgCostenquiryId");
      //FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.delete(this, strEpcg_Costenquiry_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|epcgCostenquiryId");
        vars.setSessionValue(tabId + "|FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONProcess0630AAC03F3A4EA7BDE267AD12637E7F")) {
        vars.setSessionValue("button0630AAC03F3A4EA7BDE267AD12637E7F.strprocess", vars.getStringParameter("inpprocess"));
        vars.setSessionValue("button0630AAC03F3A4EA7BDE267AD12637E7F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button0630AAC03F3A4EA7BDE267AD12637E7F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button0630AAC03F3A4EA7BDE267AD12637E7F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button0630AAC03F3A4EA7BDE267AD12637E7F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "0630AAC03F3A4EA7BDE267AD12637E7F", request.getServletPath());
      } else if (vars.commandIn("BUTTON0630AAC03F3A4EA7BDE267AD12637E7F")) {
        String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID", "");
        String strprocess = vars.getSessionValue("button0630AAC03F3A4EA7BDE267AD12637E7F.strprocess");
        String strProcessing = vars.getSessionValue("button0630AAC03F3A4EA7BDE267AD12637E7F.strProcessing");
        String strOrg = vars.getSessionValue("button0630AAC03F3A4EA7BDE267AD12637E7F.strOrg");
        String strClient = vars.getSessionValue("button0630AAC03F3A4EA7BDE267AD12637E7F.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcess0630AAC03F3A4EA7BDE267AD12637E7F(response, vars, strEpcg_Costenquiry_ID, strprocess, strProcessing);
        }
    } else if (vars.commandIn("BUTTONCompleteA5AB0D40FD7742388B7B17AF8282D2C0")) {
        vars.setSessionValue("buttonA5AB0D40FD7742388B7B17AF8282D2C0.strcomplete", vars.getStringParameter("inpcomplete"));
        vars.setSessionValue("buttonA5AB0D40FD7742388B7B17AF8282D2C0.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonA5AB0D40FD7742388B7B17AF8282D2C0.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonA5AB0D40FD7742388B7B17AF8282D2C0.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonA5AB0D40FD7742388B7B17AF8282D2C0.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "A5AB0D40FD7742388B7B17AF8282D2C0", request.getServletPath());
      } else if (vars.commandIn("BUTTONA5AB0D40FD7742388B7B17AF8282D2C0")) {
        String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID", "");
        String strcomplete = vars.getSessionValue("buttonA5AB0D40FD7742388B7B17AF8282D2C0.strcomplete");
        String strProcessing = vars.getSessionValue("buttonA5AB0D40FD7742388B7B17AF8282D2C0.strProcessing");
        String strOrg = vars.getSessionValue("buttonA5AB0D40FD7742388B7B17AF8282D2C0.strOrg");
        String strClient = vars.getSessionValue("buttonA5AB0D40FD7742388B7B17AF8282D2C0.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCompleteA5AB0D40FD7742388B7B17AF8282D2C0(response, vars, strEpcg_Costenquiry_ID, strcomplete, strProcessing);
        }
    } else if (vars.commandIn("BUTTONApproval26EF860F5FDA4C6B885445D0B381BBE1")) {
        vars.setSessionValue("button26EF860F5FDA4C6B885445D0B381BBE1.strapproval", vars.getStringParameter("inpapproval"));
        vars.setSessionValue("button26EF860F5FDA4C6B885445D0B381BBE1.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button26EF860F5FDA4C6B885445D0B381BBE1.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button26EF860F5FDA4C6B885445D0B381BBE1.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button26EF860F5FDA4C6B885445D0B381BBE1.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "26EF860F5FDA4C6B885445D0B381BBE1", request.getServletPath());
      } else if (vars.commandIn("BUTTON26EF860F5FDA4C6B885445D0B381BBE1")) {
        String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID", "");
        String strapproval = vars.getSessionValue("button26EF860F5FDA4C6B885445D0B381BBE1.strapproval");
        String strProcessing = vars.getSessionValue("button26EF860F5FDA4C6B885445D0B381BBE1.strProcessing");
        String strOrg = vars.getSessionValue("button26EF860F5FDA4C6B885445D0B381BBE1.strOrg");
        String strClient = vars.getSessionValue("button26EF860F5FDA4C6B885445D0B381BBE1.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonApproval26EF860F5FDA4C6B885445D0B381BBE1(response, vars, strEpcg_Costenquiry_ID, strapproval, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcess0630AAC03F3A4EA7BDE267AD12637E7F")) {
        String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpKey", windowId + "|Epcg_Costenquiry_ID", "");
        
        ProcessBundle pb = new ProcessBundle("0630AAC03F3A4EA7BDE267AD12637E7F", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Epcg_Costenquiry_ID", strEpcg_Costenquiry_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONCompleteA5AB0D40FD7742388B7B17AF8282D2C0")) {
        String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpKey", windowId + "|Epcg_Costenquiry_ID", "");
        
        ProcessBundle pb = new ProcessBundle("A5AB0D40FD7742388B7B17AF8282D2C0", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Epcg_Costenquiry_ID", strEpcg_Costenquiry_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONApproval26EF860F5FDA4C6B885445D0B381BBE1")) {
        String strEpcg_Costenquiry_ID = vars.getGlobalVariable("inpKey", windowId + "|Epcg_Costenquiry_ID", "");
        
        ProcessBundle pb = new ProcessBundle("26EF860F5FDA4C6B885445D0B381BBE1", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Epcg_Costenquiry_ID", strEpcg_Costenquiry_ID);
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
  private FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data data = new FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data();
    ServletException ex = null;
    try {
    data.userid = vars.getStringParameter("inpuserid");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.rchrQualitystandardId = vars.getStringParameter("inprchrQualitystandardId");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.qstandard = vars.getStringParameter("inpqstandard");     data.epcgYarncosttemplatelinesId = vars.getRequiredStringParameter("inpepcgYarncosttemplatelinesId");     data.epcgYarncosttemplatelinesW = vars.getRequiredStringParameter("inpepcgYarncosttemplatelinesW");    try {   data.epi = vars.getRequiredNumericParameter("inpepi");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.ppi = vars.getRequiredNumericParameter("inpppi");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.onesideselvedgewidth = vars.getRequiredNumericParameter("inponesideselvedgewidth");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.fabricwidth = vars.getRequiredNumericParameter("inpfabricwidth");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.fabricloss = vars.getNumericParameter("inpfabricloss");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.warpcrimp = vars.getNumericParameter("inpwarpcrimp");  } catch (ServletException paramEx) { ex = paramEx; }     data.reed = vars.getStringParameter("inpreed");     data.rchrInspweaveId = vars.getRequiredStringParameter("inprchrInspweaveId");     data.rchrInspweaveIdr = vars.getStringParameter("inprchrInspweaveId_R");     data.warpcrimpvalues = vars.getRequiredStringParameter("inpwarpcrimpvalues");     data.warpcrimpvaluesr = vars.getStringParameter("inpwarpcrimpvalues_R");    try {   data.onloomepi = vars.getNumericParameter("inponloomepi");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.widthinches = vars.getNumericParameter("inpwidthinches");  } catch (ServletException paramEx) { ex = paramEx; }     data.typeofsort = vars.getStringParameter("inptypeofsort");     data.typeofsortr = vars.getStringParameter("inptypeofsort_R");    try {   data.actualspeed = vars.getNumericParameter("inpactualspeed");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.sizingfrommaster = vars.getRequiredNumericParameter("inpsizingfrommaster");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.speed = vars.getRequiredNumericParameter("inpspeed");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.efficiency = vars.getRequiredNumericParameter("inpefficiency");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.yeildpdayploom = vars.getRequiredNumericParameter("inpyeildpdayploom");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.warpwtkgspermts = vars.getRequiredNumericParameter("inpwarpwtkgspermts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weftwtkgspermts = vars.getRequiredNumericParameter("inpweftwtkgspermts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.gsmwith = vars.getRequiredNumericParameter("inpgsmwith");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalwtkgspermts = vars.getRequiredNumericParameter("inptotalwtkgspermts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.warpwtkgswithwaste = vars.getRequiredNumericParameter("inpwarpwtkgswithwaste");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weftwtkgswithwaste = vars.getRequiredNumericParameter("inpweftwtkgswithwaste");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalwtkgswithwaste = vars.getRequiredNumericParameter("inptotalwtkgswithwaste");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.glmwith = vars.getRequiredNumericParameter("inpglmwith");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.gsmwithout = vars.getRequiredNumericParameter("inpgsmwithout");  } catch (ServletException paramEx) { ex = paramEx; }     data.epcgVariantId = vars.getStringParameter("inpepcgVariantId");     data.epcgVariantIdr = vars.getStringParameter("inpepcgVariantId_R");     data.weftvariant = vars.getStringParameter("inpweftvariant");     data.weftvariantr = vars.getStringParameter("inpweftvariant_R");    try {   data.warpratekgs = vars.getRequiredNumericParameter("inpwarpratekgs");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weftratekgs = vars.getRequiredNumericParameter("inpweftratekgs");  } catch (ServletException paramEx) { ex = paramEx; }     data.epcgSalespackingId = vars.getRequiredStringParameter("inpepcgSalespackingId");     data.epcgSalespackingIdr = vars.getStringParameter("inpepcgSalespackingId_R");    try {   data.lossoffabricpercent = vars.getNumericParameter("inplossoffabricpercent");  } catch (ServletException paramEx) { ex = paramEx; }     data.finPaymentmethodId = vars.getRequiredStringParameter("inpfinPaymentmethodId");     data.finPaymentmethodIdr = vars.getStringParameter("inpfinPaymentmethodId_R");     data.cPaymenttermId = vars.getRequiredStringParameter("inpcPaymenttermId");     data.cPaymenttermIdr = vars.getStringParameter("inpcPaymenttermId_R");    try {   data.creditperiod = vars.getRequiredNumericParameter("inpcreditperiod");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.salescommission = vars.getRequiredNumericParameter("inpsalescommission");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.cashdiscount = vars.getNumericParameter("inpcashdiscount");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.exmillpricerspermts = vars.getRequiredNumericParameter("inpexmillpricerspermts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.contributionrsploom = vars.getRequiredNumericParameter("inpcontributionrsploom");  } catch (ServletException paramEx) { ex = paramEx; }     data.cBpartnerId = vars.getRequiredStringParameter("inpcBpartnerId");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.cBpartnerLocationId = vars.getRequiredStringParameter("inpcBpartnerLocationId");     data.cBpartnerLocationIdr = vars.getStringParameter("inpcBpartnerLocationId_R");     data.poreferenceno = vars.getStringParameter("inpporeferenceno");     data.agent = vars.getStringParameter("inpagent");    try {   data.orderquantity = vars.getRequiredNumericParameter("inporderquantity");  } catch (ServletException paramEx) { ex = paramEx; }     data.deliverdate = vars.getRequiredStringParameter("inpdeliverdate");     data.description = vars.getStringParameter("inpdescription");    try {   data.prodDayLoomEff = vars.getRequiredNumericParameter("inpprodDayLoomEff");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.workingdays = vars.getRequiredNumericParameter("inpworkingdays");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalproductionmts = vars.getRequiredNumericParameter("inptotalproductionmts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.orderqtywarpwtwithwaste = vars.getRequiredNumericParameter("inporderqtywarpwtwithwaste");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.orderqtyweftwtwithwaste = vars.getRequiredNumericParameter("inporderqtyweftwtwithwaste");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.lessdepb = vars.getRequiredNumericParameter("inplessdepb");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.exportfreightusdperyd = vars.getRequiredNumericParameter("inpexportfreightusdperyd");  } catch (ServletException paramEx) { ex = paramEx; }     data.cTaxId = vars.getStringParameter("inpcTaxId");     data.cTaxIdr = vars.getStringParameter("inpcTaxId_R");    try {   data.interestcostnew = vars.getRequiredNumericParameter("inpinterestcostnew");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.finalprice = vars.getRequiredNumericParameter("inpfinalprice");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.yeildpdayploomoneffcny = vars.getRequiredNumericParameter("inpyeildpdayploomoneffcny");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrOrderstatusId = vars.getRequiredStringParameter("inprchrOrderstatusId");     data.rchrOrderstatusIdr = vars.getStringParameter("inprchrOrderstatusId_R");    try {   data.lessdeptbcal = vars.getRequiredNumericParameter("inplessdeptbcal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.peryd = vars.getRequiredNumericParameter("inpperyd");  } catch (ServletException paramEx) { ex = paramEx; }     data.process = vars.getRequiredStringParameter("inpprocess");     data.complete = vars.getRequiredStringParameter("inpcomplete");     data.enquirydate = vars.getRequiredStringParameter("inpenquirydate");    try {   data.additionalcost = vars.getNumericParameter("inpadditionalcost");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrEmployeeId = vars.getStringParameter("inprchrEmployeeId");     data.weftproduct = vars.getStringParameter("inpweftproduct");     data.status = vars.getStringParameter("inpstatus");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");    try {   data.creditperiodper30 = vars.getRequiredNumericParameter("inpcreditperiodper30");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.netprice = vars.getRequiredNumericParameter("inpnetprice");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.pckgmatrlconsumptionmts = vars.getRequiredNumericParameter("inppckgmatrlconsumptionmts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.sizingchemicalmts = vars.getRequiredNumericParameter("inpsizingchemicalmts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.yarncostmts = vars.getRequiredNumericParameter("inpyarncostmts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.exmillpriceusdperyd = vars.getRequiredNumericParameter("inpexmillpriceusdperyd");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.exmillpriceusdperydcal = vars.getRequiredNumericParameter("inpexmillpriceusdperydcal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalvariablecostmts = vars.getRequiredNumericParameter("inptotalvariablecostmts");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.perpickcontributioin = vars.getRequiredNumericParameter("inpperpickcontributioin");  } catch (ServletException paramEx) { ex = paramEx; }     data.mProductId = vars.getRequiredStringParameter("inpmProductId");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");     data.approval = vars.getRequiredStringParameter("inpapproval");     data.mPricelistId = vars.getStringParameter("inpmPricelistId");     data.mPricelistIdr = vars.getStringParameter("inpmPricelistId_R");     data.invoicerule = vars.getRequiredStringParameter("inpinvoicerule");     data.invoiceruler = vars.getStringParameter("inpinvoicerule_R");     data.epcgRatetype = vars.getRequiredStringParameter("inpepcgRatetype");     data.epcgRatetyper = vars.getStringParameter("inpepcgRatetype_R");     data.epcgOrdertype = vars.getRequiredStringParameter("inpepcgOrdertype");     data.epcgOrdertyper = vars.getStringParameter("inpepcgOrdertype_R");    try {   data.stock = vars.getNumericParameter("inpstock");  } catch (ServletException paramEx) { ex = paramEx; }     data.epcgInsurancetypeId = vars.getRequiredStringParameter("inpepcgInsurancetypeId");     data.epcgInsurancetypeIdr = vars.getStringParameter("inpepcgInsurancetypeId_R");    try {   data.insurance = vars.getNumericParameter("inpinsurance");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.tc = vars.getNumericParameter("inptc");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.pickinsert = vars.getNumericParameter("inppickinsert");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.spilit = vars.getNumericParameter("inpspilit");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.interestcost = vars.getRequiredNumericParameter("inpinterestcost");  } catch (ServletException paramEx) { ex = paramEx; }     data.cCurrencyId = vars.getRequiredStringParameter("inpcCurrencyId");     data.cCurrencyIdr = vars.getStringParameter("inpcCurrencyId_R");     data.epcgDelivermodeId = vars.getRequiredStringParameter("inpepcgDelivermodeId");     data.epcgDelivermodeIdr = vars.getStringParameter("inpepcgDelivermodeId_R");    try {   data.transportationcost = vars.getNumericParameter("inptransportationcost");  } catch (ServletException paramEx) { ex = paramEx; }     data.selvedgecount = vars.getStringParameter("inpselvedgecount");    try {   data.exmillpriceorlanded = vars.getNumericParameter("inpexmillpriceorlanded");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.selvedgeratekgs = vars.getRequiredNumericParameter("inpselvedgeratekgs");  } catch (ServletException paramEx) { ex = paramEx; }     data.oncontract = vars.getStringParameter("inponcontract");     data.cUomId = vars.getStringParameter("inpcUomId");     data.cUomIdr = vars.getStringParameter("inpcUomId_R");    try {   data.noofloomsworked = vars.getRequiredNumericParameter("inpnoofloomsworked");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.commission = vars.getRequiredNumericParameter("inpcommission");  } catch (ServletException paramEx) { ex = paramEx; }     data.deliverytypecharges = vars.getStringParameter("inpdeliverytypecharges");    try {   data.actualefficiency = vars.getNumericParameter("inpactualefficiency");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.prodDayLoomHun = vars.getRequiredNumericParameter("inpprodDayLoomHun");  } catch (ServletException paramEx) { ex = paramEx; }     data.isactive = vars.getStringParameter("inpisactive", "N");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.epcgCostenquiryId = vars.getRequestGlobalVariable("inpepcgCostenquiryId", windowId + "|Epcg_Costenquiry_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Epcg_Costenquiry_ID", data[0].getField("epcgCostenquiryId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data[] data = FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpepcgCostenquiryId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strEpcg_Costenquiry_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");
String strParamUserid = vars.getSessionValue(tabId + "|paramUserid");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno) && ("").equals(strParamUserid)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno))  || !(("").equals(strParamUserid) || ("%").equals(strParamUserid)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strEpcg_Costenquiry_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strEpcg_Costenquiry_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/epcg/FABEnquiryForm/FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8", false, "document.frmMain.inpepcgCostenquiryId", "grid", "../EpcgEnquiryFormPreview.html", "N".equals("Y"), "FABEnquiryForm", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "epcgCostenquiryId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Relation.html", "FABEnquiryForm", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strEpcg_Costenquiry_ID, TableSQLData tableSQL)
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
    FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");
String strParamUserid = vars.getSessionValue(tabId + "|paramUserid");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno) && ("").equals(strParamUserid)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno))  || !(("").equals(strParamUserid) || ("%").equals(strParamUserid)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strEpcg_Costenquiry_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strEpcg_Costenquiry_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("epcgCostenquiryId") == null || dataField.getField("epcgCostenquiryId").equals("")) {
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
        data = FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.set(Utility.getDefault(this, vars, "Epi", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Spilit", "1", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Lossoffabricpercent", "1", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Widthinches", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Status", "DR", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Orderquantity", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Sizingfrommaster", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Exmillpricerspermts", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Lessdeptbcal", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), "", Utility.getDefault(this, vars, "Agent", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Weftvariant", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Actualefficiency", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Totalproductionmts", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Yeildpdayploom", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Yeildpdayploomoneffcny", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "M_Pricelist_ID", "FA26A2CD001F4DA79117A24022CBC32F", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Pickinsert", "1", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Enquirydate", "@#Date@", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Warpwtkgswithwaste", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Gsmwithout", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "C_Paymentterm_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Contributionrsploom", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Ppi", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "FIN_Paymentmethod_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Reed", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Epcg_Variant_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Commission", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Weftratekgs", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Noofloomsworked", "1", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.selectDef4202AC79311A41F1A463C33378909FAC_0(this, Utility.getDefault(this, vars, "C_Bpartner_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField)), Utility.getDefault(this, vars, "Epcg_Yarncosttemplatelines_W", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Rchr_Qualitystandard_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Lessdepb", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Interestcostnew", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Warpcrimpvalues", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Orderqtywarpwtwithwaste", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "C_Tax_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Rchr_Inspweave_ID", "93549E14E2CC43A6B7AAA9DF894623BC", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "C_Doctype_ID", "F2D0A8FEC2D34A3BA1AF24824D016051", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Gsmwith", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Finalprice", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Epcg_Salespacking_ID", "C9CC7F96B68C47C89B5ADF6CC53CE687", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Sizingchemicalmts", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Exmillpriceorlanded", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Epcg_Ordertype", "SO", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Process", "N", "8DFA2AC4EBC840BDB804131A5E2ABF11", "N", dataField), Utility.getDefault(this, vars, "Fabricloss", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Weftwtkgspermts", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Exmillpriceusdperyd", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Createdby", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.selectDef620DF48111E64CB5B274FC75B33918BD_1(this, Utility.getDefault(this, vars, "Createdby", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField)), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "C_Uom_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Glmwith", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Pckgmatrlconsumptionmts", "1", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), "Y", Utility.getDefault(this, vars, "Exmillpriceusdperydcal", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Efficiency", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Documentno", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Totalwtkgswithwaste", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Epcg_Ratetype", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Creditperiodper30", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Weftwtkgswithwaste", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Warpcrimp", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Tc", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Onesideselvedgewidth", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Qstandard", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Warpratekgs", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Complete", "N", "8DFA2AC4EBC840BDB804131A5E2ABF11", "N", dataField), Utility.getDefault(this, vars, "Yarncostmts", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "M_Product_ID", "3A40B0F422424F1A82C8CEADA109AC0A", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.selectDef920261DFC28948D7BF0B14ADDE069B32_2(this,  vars.getLanguage(), Utility.getDefault(this, vars, "M_Product_ID", "3A40B0F422424F1A82C8CEADA109AC0A", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField)), Utility.getDefault(this, vars, "Epcg_Delivermode_ID", "240B2B500CCD4642B6ACDBB7A95EFB33", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Speed", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Cashdiscount", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Transportationcost", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.selectDefA498CDF9B0704DA1870677F90A80A9CE(this), Utility.getDefault(this, vars, "Exportfreightusdperyd", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Selvedgecount", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Warpwtkgspermts", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Insurance", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Onloomepi", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Netprice", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Invoicerule", "D", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Fabricwidth", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Workingdays", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Perpickcontributioin", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "C_Bpartner_Location_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Approval", "N", "8DFA2AC4EBC840BDB804131A5E2ABF11", "N", dataField), Utility.getDefault(this, vars, "Description", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Orderqtyweftwtwithwaste", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Poreferenceno", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Userid", "@#AD_USER_ID@", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Totalwtkgspermts", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Actualspeed", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Salescommission", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Deliverdate", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Typeofsort", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Totalvariablecostmts", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Stock", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Prod_Day_Loom_Hun", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Interestcost", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Peryd", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Selvedgeratekgs", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Epcg_Yarncosttemplatelines_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Weftproduct", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Prod_Day_Loom_Eff", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Creditperiod", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "0", dataField), Utility.getDefault(this, vars, "Oncontract", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Deliverytypecharges", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Additionalcost", "0", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField), FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.selectDefFDFBCD706E8B4A87BB232A38244FADF4_3(this, Utility.getDefault(this, vars, "Updatedby", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField)), Utility.getDefault(this, vars, "Rchr_Orderstatus_ID", "", "8DFA2AC4EBC840BDB804131A5E2ABF11", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/epcg/FABEnquiryForm/FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/epcg/FABEnquiryForm/FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpepcgCostenquiryId", "", "../EpcgEnquiryFormPreview.html", "N".equals("Y"), "FABEnquiryForm", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strEpcg_Costenquiry_ID), !hasReadOnlyAccess);
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
      // if (!strEpcg_Costenquiry_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Relation.html", "FABEnquiryForm", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Relation.html", strReplaceWith);
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
xmlDocument.setParameter("buttonEpi", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPpi", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonOnesideselvedgewidth", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonFabricwidth", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWarpcrimp", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonFabricloss", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Inspweave_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrInspweaveId"):dataField.getField("rchrInspweaveId")));
xmlDocument.setData("reportRchr_Inspweave_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Warpcrimpvalues", "99ED6CC0540C47DA9359A1DEF1849DC8", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("warpcrimpvalues"):dataField.getField("warpcrimpvalues")));
xmlDocument.setData("reportWarpcrimpvalues","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonOnloomepi", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWidthinches", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "17", "Typeofsort", "7BB7843C30F346CEA858FD85BE5860B8", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("typeofsort"):dataField.getField("typeofsort")));
xmlDocument.setData("reportTypeofsort","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonActualspeed", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSizingfrommaster", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSpeed", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEfficiency", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonYeildpdayploom", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWarpwtkgspermts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWeftwtkgspermts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGsmwith", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalwtkgspermts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWarpwtkgswithwaste", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWeftwtkgswithwaste", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalwtkgswithwaste", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGlmwith", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGsmwithout", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Epcg_Variant_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgVariantId"):dataField.getField("epcgVariantId")));
xmlDocument.setData("reportEpcg_Variant_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Weftvariant", "4BE1650E71A645D7BA66E65B0CFA1ACB", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("weftvariant"):dataField.getField("weftvariant")));
xmlDocument.setData("reportWeftvariant","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonWarpratekgs", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWeftratekgs", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Epcg_Salespacking_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgSalespackingId"):dataField.getField("epcgSalespackingId")));
xmlDocument.setData("reportEpcg_Salespacking_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonLossoffabricpercent", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "FIN_Paymentmethod_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finPaymentmethodId"):dataField.getField("finPaymentmethodId")));
xmlDocument.setData("reportFIN_Paymentmethod_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Paymentterm_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cPaymenttermId"):dataField.getField("cPaymenttermId")));
xmlDocument.setData("reportC_Paymentterm_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonCreditperiod", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSalescommission", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCashdiscount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExmillpricerspermts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonContributionrsploom", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_Bpartner_Location_ID", "", "167", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cBpartnerLocationId"):dataField.getField("cBpartnerLocationId")));
xmlDocument.setData("reportC_Bpartner_Location_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonOrderquantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Deliverdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonProd_Day_Loom_Eff", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWorkingdays", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalproductionmts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonOrderqtywarpwtwithwaste", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonOrderqtyweftwtwithwaste", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonLessdepb", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExportfreightusdperyd", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonInterestcostnew", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_Tax_ID", "", "E6707493FBB245749358FD6EFF73C66F", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cTaxId"):dataField.getField("cTaxId")));
xmlDocument.setData("reportC_Tax_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonFinalprice", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonYeildpdayploomoneffcny", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Orderstatus_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrOrderstatusId"):dataField.getField("rchrOrderstatusId")));
xmlDocument.setData("reportRchr_Orderstatus_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonLessdeptbcal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPeryd", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Process_BTNname", Utility.getButtonName(this, vars, "A0BCBE8D01ED48EC8B43D579DB4CD1D2", "Process_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcess = org.openbravo.erpCommon.utility.Utility.isModalProcess("0630AAC03F3A4EA7BDE267AD12637E7F"); 
xmlDocument.setParameter("Process_Modal", modalProcess?"true":"false");
xmlDocument.setParameter("Enquirydate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Complete_BTNname", Utility.getButtonName(this, vars, "CEB51700FA9D4421ABF27E1B730A0458", "Complete_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalComplete = org.openbravo.erpCommon.utility.Utility.isModalProcess("A5AB0D40FD7742388B7B17AF8282D2C0"); 
xmlDocument.setParameter("Complete_Modal", modalComplete?"true":"false");
xmlDocument.setParameter("buttonAdditionalcost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCreditperiodper30", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonNetprice", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPckgmatrlconsumptionmts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSizingchemicalmts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonYarncostmts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExmillpriceusdperyd", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExmillpriceusdperydcal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalvariablecostmts", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPerpickcontributioin", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Approval_BTNname", Utility.getButtonName(this, vars, "C35C8C14532D43CAA2B07F43776332A5", "Approval_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalApproval = org.openbravo.erpCommon.utility.Utility.isModalProcess("26EF860F5FDA4C6B885445D0B381BBE1"); 
xmlDocument.setParameter("Approval_Modal", modalApproval?"true":"false");
comboTableData = new ComboTableData(vars, this, "19", "M_Pricelist_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mPricelistId"):dataField.getField("mPricelistId")));
xmlDocument.setData("reportM_Pricelist_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Invoicerule", "150", "1000200003", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("invoicerule"):dataField.getField("invoicerule")));
xmlDocument.setData("reportInvoicerule","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Epcg_Ratetype", "74D3C7DF005C4D03935CEE28256DC187", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgRatetype"):dataField.getField("epcgRatetype")));
xmlDocument.setData("reportEpcg_Ratetype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Epcg_Ordertype", "36DED13CD40A4041B87B6A7FE14E4B62", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgOrdertype"):dataField.getField("epcgOrdertype")));
xmlDocument.setData("reportEpcg_Ordertype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonStock", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Epcg_Insurancetype_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgInsurancetypeId"):dataField.getField("epcgInsurancetypeId")));
xmlDocument.setData("reportEpcg_Insurancetype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonInsurance", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTc", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPickinsert", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSpilit", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonInterestcost", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_Currency_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCurrencyId"):dataField.getField("cCurrencyId")));
xmlDocument.setData("reportC_Currency_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Epcg_Delivermode_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgDelivermodeId"):dataField.getField("epcgDelivermodeId")));
xmlDocument.setData("reportEpcg_Delivermode_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonTransportationcost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExmillpriceorlanded", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSelvedgeratekgs", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_Uom_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomId"):dataField.getField("cUomId")));
xmlDocument.setData("reportC_Uom_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonNoofloomsworked", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCommission", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonActualefficiency", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonProd_Day_Loom_Hun", Utility.messageBD(this, "Calc", vars.getLanguage()));
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



    void printPageButtonProcess0630AAC03F3A4EA7BDE267AD12637E7F(HttpServletResponse response, VariablesSecureApp vars, String strEpcg_Costenquiry_ID, String strprocess, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0630AAC03F3A4EA7BDE267AD12637E7F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Process0630AAC03F3A4EA7BDE267AD12637E7F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strEpcg_Costenquiry_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "0630AAC03F3A4EA7BDE267AD12637E7F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("0630AAC03F3A4EA7BDE267AD12637E7F");
        vars.removeMessage("0630AAC03F3A4EA7BDE267AD12637E7F");
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
    void printPageButtonCompleteA5AB0D40FD7742388B7B17AF8282D2C0(HttpServletResponse response, VariablesSecureApp vars, String strEpcg_Costenquiry_ID, String strcomplete, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A5AB0D40FD7742388B7B17AF8282D2C0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/CompleteA5AB0D40FD7742388B7B17AF8282D2C0", discard).createXmlDocument();
      xmlDocument.setParameter("key", strEpcg_Costenquiry_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "A5AB0D40FD7742388B7B17AF8282D2C0");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("A5AB0D40FD7742388B7B17AF8282D2C0");
        vars.removeMessage("A5AB0D40FD7742388B7B17AF8282D2C0");
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
    void printPageButtonApproval26EF860F5FDA4C6B885445D0B381BBE1(HttpServletResponse response, VariablesSecureApp vars, String strEpcg_Costenquiry_ID, String strapproval, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 26EF860F5FDA4C6B885445D0B381BBE1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Approval26EF860F5FDA4C6B885445D0B381BBE1", discard).createXmlDocument();
      xmlDocument.setParameter("key", strEpcg_Costenquiry_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "26EF860F5FDA4C6B885445D0B381BBE1");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("26EF860F5FDA4C6B885445D0B381BBE1");
        vars.removeMessage("26EF860F5FDA4C6B885445D0B381BBE1");
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
      String result = "var strad_user_id=\"" +Utility.getContext(this, vars, "ad_user_id", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
      return result;
    }


    private String getReadOnlyLogicContext(VariablesSecureApp vars) throws IOException, ServletException {
      log4j.debug("Output: Read Only logic context fields");
      String result = "var strad_user_id=\"" + Utility.getContext(this, vars, "ad_user_id", windowId) + "\";\n";
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
    FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data data = null;
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
                data.epcgCostenquiryId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8Data.getCurrentDBTimestamp(this, data.epcgCostenquiryId).equals(
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
                    data.epcgCostenquiryId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Epcg_Costenquiry_ID", data.epcgCostenquiryId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet FABEnquiryForm478560BF1FAA453B9BDE7AB35489FDF8. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
