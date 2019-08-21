
package org.openbravo.erpWindows.ServiceProject;


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

public class ServiceProject extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(ServiceProject.class);
  
  private static final String windowId = "800001";
  private static final String tabId = "800002";
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
     
      if (command.contains("800002")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("800002");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "800002";
        }
      }
     
      if (command.contains("800005")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("800005");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "800005";
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
        String strcProjectId = request.getParameter("inpcProjectId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strcProjectId.equals(""))
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

      String strC_Project_ID = vars.getGlobalVariable("inpcProjectId", windowId + "|C_Project_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|ServiceProject.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strC_Project_ID.equals("")) strC_Project_ID = firstElement(vars, tableSQL);
          if (strC_Project_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_Project_ID, tableSQL);

      else printPageDataSheet(response, vars, strC_Project_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strC_Project_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strC_Project_ID.equals("")) strC_Project_ID = vars.getRequiredGlobalVariable("inpcProjectId", windowId + "|C_Project_ID");
      else vars.setSessionValue(windowId + "|C_Project_ID", strC_Project_ID);
      
      vars.setSessionValue(tabId + "|ServiceProject.view", "EDIT");

      printPageEdit(response, request, vars, false, strC_Project_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|ServiceProject.view");
      String strC_Project_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strC_Project_ID = firstElement(vars, tableSQL);
          if (strC_Project_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strC_Project_ID.equals("")) strC_Project_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strC_Project_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamValue", tabId + "|paramValue");
vars.getRequestGlobalVariable("inpParamName", tabId + "|paramName");
vars.getRequestGlobalVariable("inpParamC_BPartner_ID", tabId + "|paramC_BPartner_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|C_Project_ID");
      String strC_Project_ID="";

      String strView = vars.getSessionValue(tabId + "|ServiceProject.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strC_Project_ID = firstElement(vars, tableSQL);
        if (strC_Project_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|ServiceProject.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_Project_ID, tableSQL);

      else printPageDataSheet(response, vars, strC_Project_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strC_Project_ID = vars.getGlobalVariable("inpcProjectId", windowId + "|C_Project_ID", "");
      vars.setSessionValue(tabId + "|ServiceProject.view", "RELATION");
      printPageDataSheet(response, vars, strC_Project_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strC_Project_ID = vars.getGlobalVariable("inpcProjectId", windowId + "|C_Project_ID", "");
      vars.setSessionValue(tabId + "|ServiceProject.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strC_Project_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strC_Project_ID = vars.getRequiredStringParameter("inpcProjectId");
      
      String strNext = nextElement(vars, strC_Project_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strC_Project_ID = vars.getRequiredStringParameter("inpcProjectId");
      
      String strPrevious = previousElement(vars, strC_Project_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|ServiceProject.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|ServiceProject.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|ServiceProject.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|ServiceProject.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|C_Project_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|ServiceProject.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|ServiceProject.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|C_Project_ID");

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

      String strC_Project_ID = vars.getRequiredGlobalVariable("inpcProjectId", windowId + "|C_Project_ID");
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
          String strNext = nextElement(vars, strC_Project_ID, tableSQL);
          vars.setSessionValue(windowId + "|C_Project_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strC_Project_ID = vars.getRequiredStringParameter("inpcProjectId");
      //ServiceProjectData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = ServiceProjectData.delete(this, strC_Project_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|cProjectId");
        vars.setSessionValue(tabId + "|ServiceProject.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONChangeProjectStatus800002")) {
        vars.setSessionValue("button800002.strchangeprojectstatus", vars.getStringParameter("inpchangeprojectstatus"));
        vars.setSessionValue("button800002.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button800002.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button800002.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("button800002.inpprojectstatus", vars.getRequiredStringParameter("inpprojectstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button800002.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "800002", request.getServletPath());    
     } else if (vars.commandIn("BUTTON800002")) {
        String strC_Project_ID = vars.getGlobalVariable("inpcProjectId", windowId + "|C_Project_ID", "");
        String strchangeprojectstatus = vars.getSessionValue("button800002.strchangeprojectstatus");
        String strProcessing = vars.getSessionValue("button800002.strProcessing");
        String strOrg = vars.getSessionValue("button800002.strOrg");
        String strClient = vars.getSessionValue("button800002.strClient");
        
        String strprojectstatus = vars.getSessionValue("button800002.inpprojectstatus");

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonChangeProjectStatus800002(response, vars, strC_Project_ID, strchangeprojectstatus, strProcessing, strprojectstatus);
        }

     } else if (vars.commandIn("BUTTONGenerateOrder800005")) {
        vars.setSessionValue("button800005.strgenerateorder", vars.getStringParameter("inpgenerateorder"));
        vars.setSessionValue("button800005.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button800005.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button800005.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button800005.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "800005", request.getServletPath());    
     } else if (vars.commandIn("BUTTON800005")) {
        String strC_Project_ID = vars.getGlobalVariable("inpcProjectId", windowId + "|C_Project_ID", "");
        String strgenerateorder = vars.getSessionValue("button800005.strgenerateorder");
        String strProcessing = vars.getSessionValue("button800005.strProcessing");
        String strOrg = vars.getSessionValue("button800005.strOrg");
        String strClient = vars.getSessionValue("button800005.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonGenerateOrder800005(response, vars, strC_Project_ID, strgenerateorder, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONChangeProjectStatus800002")) {
        String strC_Project_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Project_ID", "");
        @SuppressWarnings("unused")
        String strchangeprojectstatus = vars.getStringParameter("inpchangeprojectstatus");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "800002", (("C_Project_ID".equalsIgnoreCase("AD_Language"))?"0":strC_Project_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          PInstanceProcessData.insertPInstanceParam(this, pinstance, "0", "ChangeProjectStatus", strchangeprojectstatus, vars.getClient(), vars.getOrg(), vars.getUser());

          
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
    } else if (vars.commandIn("SAVE_BUTTONGenerateOrder800005")) {
        String strC_Project_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Project_ID", "");
        @SuppressWarnings("unused")
        String strgenerateorder = vars.getStringParameter("inpgenerateorder");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "800005", (("C_Project_ID".equalsIgnoreCase("AD_Language"))?"0":strC_Project_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
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
  private ServiceProjectData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    ServiceProjectData data = new ServiceProjectData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.value = vars.getStringParameter("inpvalue");     data.name = vars.getRequiredStringParameter("inpname");     data.issummary = vars.getStringParameter("inpissummary", "N");     data.projectphase = vars.getStringParameter("inpprojectphase");     data.projectphaser = vars.getStringParameter("inpprojectphase_R");     data.startdate = vars.getStringParameter("inpstartdate");     data.datecontract = vars.getStringParameter("inpdatecontract");     data.datefinish = vars.getStringParameter("inpdatefinish");     data.invoiceToproject = vars.getStringParameter("inpinvoiceToproject", "N");     data.salesrepId = vars.getStringParameter("inpsalesrepId");     data.salesrepIdr = vars.getStringParameter("inpsalesrepId_R");     data.responsibleId = vars.getStringParameter("inpresponsibleId");     data.responsibleIdr = vars.getStringParameter("inpresponsibleId_R");     data.cLocationId = vars.getStringParameter("inpcLocationId");     data.cLocationIdr = vars.getStringParameter("inpcLocationId_R");     data.description = vars.getStringParameter("inpdescription");    try {   data.plannedamt = vars.getRequiredNumericParameter("inpplannedamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.plannedqty = vars.getRequiredNumericParameter("inpplannedqty");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.plannedmarginamt = vars.getRequiredNumericParameter("inpplannedmarginamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.iscommitment = vars.getRequiredInputGlobalVariable("inpiscommitment", windowId + "|IsCommitment", "N");     data.iscommitceiling = vars.getStringParameter("inpiscommitceiling", "N");    try {   data.committedamt = vars.getRequiredNumericParameter("inpcommittedamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.committedqty = vars.getRequiredNumericParameter("inpcommittedqty");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.servrevenue = vars.getNumericParameter("inpservrevenue");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.expexpenses = vars.getNumericParameter("inpexpexpenses");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.servcost = vars.getNumericParameter("inpservcost");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.expreinvoicing = vars.getNumericParameter("inpexpreinvoicing");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.servmargin = vars.getNumericParameter("inpservmargin");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.expmargin = vars.getNumericParameter("inpexpmargin");  } catch (ServletException paramEx) { ex = paramEx; }     data.cBpartnerId = vars.getRequestGlobalVariable("inpcBpartnerId", windowId + "|C_BPartner_ID");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.cBpartnerLocationId = vars.getStringParameter("inpcBpartnerLocationId");     data.cBpartnerLocationIdr = vars.getStringParameter("inpcBpartnerLocationId_R");     data.adUserId = vars.getStringParameter("inpadUserId");     data.adUserIdr = vars.getStringParameter("inpadUserId_R");     data.poreference = vars.getStringParameter("inpporeference");     data.finPaymentmethodId = vars.getStringParameter("inpfinPaymentmethodId");     data.finPaymentmethodIdr = vars.getStringParameter("inpfinPaymentmethodId_R");     data.cPaymenttermId = vars.getStringParameter("inpcPaymenttermId");     data.cPaymenttermIdr = vars.getStringParameter("inpcPaymenttermId_R");     data.createtemppricelist = vars.getStringParameter("inpcreatetemppricelist", "N");     data.mPricelistId = vars.getRequestGlobalVariable("inpmPricelistId", windowId + "|M_PriceList_ID");     data.mPricelistIdr = vars.getStringParameter("inpmPricelistId_R");     data.cCurrencyId = vars.getRequiredGlobalVariable("inpcCurrencyId", windowId + "|C_Currency_ID");     data.cCurrencyIdr = vars.getStringParameter("inpcCurrencyId_R");     data.mWarehouseId = vars.getRequestGlobalVariable("inpmWarehouseId", windowId + "|M_Warehouse_ID");     data.mWarehouseIdr = vars.getStringParameter("inpmWarehouseId_R");     data.changeprojectstatus = vars.getStringParameter("inpchangeprojectstatus");     data.copyfrom = vars.getStringParameter("inpcopyfrom");     data.generateorder = vars.getStringParameter("inpgenerateorder");     data.projectstatus = vars.getRequiredGlobalVariable("inpprojectstatus", windowId + "|Projectstatus");     data.cProjectId = vars.getRequestGlobalVariable("inpcProjectId", windowId + "|C_Project_ID");     data.projectkind = vars.getStringParameter("inpprojectkind");     data.lastplannedproposaldate = vars.getStringParameter("inplastplannedproposaldate");     data.accountno = vars.getStringParameter("inpaccountno");    try {   data.documentCopies = vars.getNumericParameter("inpdocumentCopies");  } catch (ServletException paramEx) { ex = paramEx; }     data.isactive = vars.getStringParameter("inpisactive", "N");     data.publicprivate = vars.getStringParameter("inppublicprivate");     data.cCampaignId = vars.getStringParameter("inpcCampaignId");     data.cProjectphaseId = vars.getStringParameter("inpcProjectphaseId");    try {   data.plannedpoamt = vars.getNumericParameter("inpplannedpoamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.generateto = vars.getStringParameter("inpgenerateto");     data.processing = vars.getStringParameter("inpprocessing");     data.setprojecttype = vars.getStringParameter("inpsetprojecttype");     data.processed = vars.getStringParameter("inpprocessed", "N");     data.cPhaseId = vars.getStringParameter("inpcPhaseId");     data.cProjecttypeId = vars.getStringParameter("inpcProjecttypeId");    try {   data.invoicedqty = vars.getRequiredNumericParameter("inpinvoicedqty");  } catch (ServletException paramEx) { ex = paramEx; }     data.mPricelistVersionId = vars.getStringParameter("inpmPricelistVersionId");     data.note = vars.getStringParameter("inpnote");    try {   data.invoicedamt = vars.getRequiredNumericParameter("inpinvoicedamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.projectbalanceamt = vars.getRequiredNumericParameter("inpprojectbalanceamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.projectcategory = vars.getStringParameter("inpprojectcategory");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.paymentrule = vars.getStringParameter("inppaymentrule");     data.billtoId = vars.getStringParameter("inpbilltoId"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



          vars.getGlobalVariable("inppreprojectcategory", windowId + "|PREPROJECTCATEGORY", "");
    
    

          if (data.value.equals("")) data.value = Utility.getDocumentNoConnection(con, this, vars.getClient(), "C_Project", true);
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|IsCommitment", data[0].getField("iscommitment"));    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].getField("cBpartnerId"));    vars.setSessionValue(windowId + "|M_PriceList_ID", data[0].getField("mPricelistId"));    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].getField("cCurrencyId"));    vars.setSessionValue(windowId + "|M_Warehouse_ID", data[0].getField("mWarehouseId"));    vars.setSessionValue(windowId + "|Projectstatus", data[0].getField("projectstatus"));    vars.setSessionValue(windowId + "|C_Project_ID", data[0].getField("cProjectId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      ServiceProjectData[] data = ServiceProjectData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpcProjectId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strC_Project_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamValue = vars.getSessionValue(tabId + "|paramValue");
String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamC_BPartner_ID = vars.getSessionValue(tabId + "|paramC_BPartner_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamValue) && ("").equals(strParamName) && ("").equals(strParamC_BPartner_ID)) || !(("").equals(strParamValue) || ("%").equals(strParamValue))  || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamC_BPartner_ID) || ("%").equals(strParamC_BPartner_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strC_Project_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strC_Project_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ServiceProject/ServiceProject_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "ServiceProject", false, "document.frmMain.inpcProjectId", "grid", "..", "".equals("Y"), "ServiceProject", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "cProjectId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ServiceProject_Relation.html", "ServiceProject", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ServiceProject_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strC_Project_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " C_Project.Name";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    ServiceProjectData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamValue = vars.getSessionValue(tabId + "|paramValue");
String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamC_BPartner_ID = vars.getSessionValue(tabId + "|paramC_BPartner_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamValue) && ("").equals(strParamName) && ("").equals(strParamC_BPartner_ID)) || !(("").equals(strParamValue) || ("%").equals(strParamValue))  || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamC_BPartner_ID) || ("%").equals(strParamC_BPartner_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = ServiceProjectData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strC_Project_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strC_Project_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new ServiceProjectData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("cProjectId") == null || dataField.getField("cProjectId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strPREPROJECTCATEGORY = "N";
    vars.setSessionValue(windowId + "|PREPROJECTCATEGORY", strPREPROJECTCATEGORY);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = ServiceProjectData.set(Utility.getDefault(this, vars, "C_ProjectPhase_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "Createtemppricelist", "Y", "800001", "N", dataField), Utility.getDefault(this, vars, "Startdate", "", "800001", "", dataField), "", Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "800001", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "800001", "", dataField), "Y", Utility.getDefault(this, vars, "Created", "", "800001", "", dataField), Utility.getDefault(this, vars, "CreatedBy", "", "800001", "", dataField), ServiceProjectData.selectDef1354_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "800001", "", dataField)), Utility.getDefault(this, vars, "Name", "", "800001", "", dataField), Utility.getDefault(this, vars, "UpdatedBy", "", "800001", "", dataField), ServiceProjectData.selectDef1357_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "800001", "", dataField)), Utility.getDefault(this, vars, "Description", "", "800001", "", dataField), Utility.getDefault(this, vars, "IsSummary", "", "800001", "N", dataField), Utility.getDefault(this, vars, "Value", "", "800001", "", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "@C_Currency_ID@", "800001", "", dataField), Utility.getDefault(this, vars, "C_BPartner_ID", "", "800001", "", dataField), ServiceProjectData.selectDef3902_2(this, Utility.getDefault(this, vars, "C_BPartner_ID", "", "800001", "", dataField)), Utility.getDefault(this, vars, "IsCommitment", "", "800001", "N", dataField), Utility.getDefault(this, vars, "CommittedAmt", "", "800001", "0", dataField), Utility.getDefault(this, vars, "DateContract", "", "800001", "", dataField), Utility.getDefault(this, vars, "DateFinish", "", "800001", "", dataField), Utility.getDefault(this, vars, "GenerateTo", "", "800001", "N", dataField), Utility.getDefault(this, vars, "Processed", "", "800001", "N", dataField), Utility.getDefault(this, vars, "Note", "", "800001", "", dataField), Utility.getDefault(this, vars, "SalesRep_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "M_PriceList_Version_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "PlannedAmt", "", "800001", "0", dataField), Utility.getDefault(this, vars, "PlannedQty", "", "800001", "0", dataField), Utility.getDefault(this, vars, "PlannedMarginAmt", "", "800001", "0", dataField), Utility.getDefault(this, vars, "POReference", "", "800001", "", dataField), Utility.getDefault(this, vars, "C_Campaign_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "C_PaymentTerm_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "AD_User_ID", "-1", "800001", "", dataField), Utility.getDefault(this, vars, "C_BPartner_Location_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "Publicprivate", "", "800001", "", dataField), Utility.getDefault(this, vars, "Projectstatus", "OP", "800001", "", dataField), Utility.getDefault(this, vars, "Projectkind", "", "800001", "", dataField), Utility.getDefault(this, vars, "BillTo_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "Projectphase", "", "800001", "", dataField), Utility.getDefault(this, vars, "GenerateOrder", "", "800001", "N", dataField), Utility.getDefault(this, vars, "ChangeProjectStatus", "", "800001", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "800002", Utility.getDefault(this, vars, "ChangeProjectStatus", "", "800001", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "800002", Utility.getDefault(this, vars, "ChangeProjectStatus", "", "800001", "N", dataField))), Utility.getDefault(this, vars, "C_Location_ID", "", "800001", "", dataField), ServiceProjectData.selectDef800142_3(this,  vars.getLanguage(), Utility.getDefault(this, vars, "C_Location_ID", "", "800001", "", dataField)), Utility.getDefault(this, vars, "M_PriceList_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "PaymentRule", "", "800001", "", dataField), Utility.getDefault(this, vars, "Invoice_ToProject", "", "800001", "N", dataField), Utility.getDefault(this, vars, "PlannedPOAmt", "", "800001", "", dataField), Utility.getDefault(this, vars, "LastPlannedProposalDate", "", "800001", "", dataField), Utility.getDefault(this, vars, "Document_Copies", "1", "800001", "", dataField), Utility.getDefault(this, vars, "AccountNo", "", "800001", "", dataField), Utility.getDefault(this, vars, "Responsible_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "Servrevenue", "", "800001", "", dataField), Utility.getDefault(this, vars, "Servcost", "", "800001", "", dataField), Utility.getDefault(this, vars, "Servmargin", "", "800001", "", dataField), Utility.getDefault(this, vars, "Expreinvoicing", "", "800001", "", dataField), Utility.getDefault(this, vars, "Expexpenses", "", "800001", "", dataField), Utility.getDefault(this, vars, "Expmargin", "", "800001", "", dataField), Utility.getDefault(this, vars, "Setprojecttype", "", "800001", "N", dataField), Utility.getDefault(this, vars, "FIN_Paymentmethod_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "InvoicedAmt", "", "800001", "0", dataField), Utility.getDefault(this, vars, "CopyFrom", "", "800001", "N", dataField), Utility.getDefault(this, vars, "C_Phase_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "InvoicedQty", "", "800001", "0", dataField), Utility.getDefault(this, vars, "C_ProjectType_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "ProjectBalanceAmt", "", "800001", "0", dataField), Utility.getDefault(this, vars, "CommittedQty", "", "800001", "0", dataField), Utility.getDefault(this, vars, "IsCommitCeiling", "", "800001", "N", dataField), Utility.getDefault(this, vars, "M_Warehouse_ID", "", "800001", "", dataField), Utility.getDefault(this, vars, "ProjectCategory", "@PREPROJECTCATEGORY@", "800001", "", dataField), Utility.getDefault(this, vars, "Processing", "", "800001", "N", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ServiceProject/ServiceProject_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ServiceProject/ServiceProject_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "ServiceProject", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpcProjectId", "", "..", "".equals("Y"), "ServiceProject", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strC_Project_ID), !hasReadOnlyAccess);
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
      // if (!strC_Project_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ServiceProject_Relation.html", "ServiceProject", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ServiceProject_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("PREPROJECTCATEGORY", strPREPROJECTCATEGORY);
    
    
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
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "130", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Projectphase", "800003", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("projectphase"):dataField.getField("projectphase")));
xmlDocument.setData("reportProjectphase","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Startdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("DateContract_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("DateFinish_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "18", "SalesRep_ID", "190", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("salesrepId"):dataField.getField("salesrepId")));
xmlDocument.setData("reportSalesRep_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Responsible_ID", "800093", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("responsibleId"):dataField.getField("responsibleId")));
xmlDocument.setData("reportResponsible_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonPlannedAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPlannedQty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPlannedMarginAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCommittedAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCommittedQty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonServrevenue", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExpexpenses", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonServcost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExpreinvoicing", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonServmargin", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExpmargin", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_BPartner_Location_ID", "", "167", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cBpartnerLocationId"):dataField.getField("cBpartnerLocationId")));
xmlDocument.setData("reportC_BPartner_Location_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "AD_User_ID", "", "123", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adUserId"):dataField.getField("adUserId")));
xmlDocument.setData("reportAD_User_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "FIN_Paymentmethod_ID", "", "34A2733B41B04DC19B3E54F764753D19", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finPaymentmethodId"):dataField.getField("finPaymentmethodId")));
xmlDocument.setData("reportFIN_Paymentmethod_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cPaymenttermId"):dataField.getField("cPaymenttermId")));
xmlDocument.setData("reportC_PaymentTerm_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mPricelistId"):dataField.getField("mPricelistId")));
xmlDocument.setData("reportM_PriceList_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Currency_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCurrencyId"):dataField.getField("cCurrencyId")));
xmlDocument.setData("reportC_Currency_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mWarehouseId"):dataField.getField("mWarehouseId")));
xmlDocument.setData("reportM_Warehouse_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("ChangeProjectStatus_BTNname", Utility.getButtonName(this, vars, "800044", "ChangeProjectStatus_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalChangeProjectStatus = org.openbravo.erpCommon.utility.Utility.isModalProcess("800002"); 
xmlDocument.setParameter("ChangeProjectStatus_Modal", modalChangeProjectStatus?"true":"false");
xmlDocument.setParameter("CopyFrom_BTNname", Utility.getButtonName(this, vars, "800072", "CopyFrom_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCopyFrom = org.openbravo.erpCommon.utility.Utility.isModalProcess("212"); 
xmlDocument.setParameter("CopyFrom_Modal", modalCopyFrom?"true":"false");
xmlDocument.setParameter("GenerateOrder_BTNname", Utility.getButtonName(this, vars, "800043", "GenerateOrder_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalGenerateOrder = org.openbravo.erpCommon.utility.Utility.isModalProcess("800005"); 
xmlDocument.setParameter("GenerateOrder_Modal", modalGenerateOrder?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("buttonInvoicedQty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("LastPlannedProposalDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonInvoicedAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonProjectBalanceAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPlannedPOAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
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

    private void printPageButtonChangeProjectStatus800002(HttpServletResponse response, VariablesSecureApp vars, String strC_Project_ID, String strchangeprojectstatus, String strProcessing, String strprojectstatus)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800002");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ChangeProjectStatus", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Project_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ServiceProject_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "800002");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("800002");
        vars.removeMessage("800002");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

      xmlDocument.setParameter("projectstatus", strprojectstatus);
    try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
xmlDocument.setParameter("processId", "800002");
xmlDocument.setParameter("processDescription", "Change project status");
xmlDocument.setParameter("projectaction", strchangeprojectstatus);
FieldProvider[] dataProjectAction = ActionButtonUtility.projectAction(this, vars, strchangeprojectstatus, "800002", strprojectstatus);
xmlDocument.setData("reportprojectaction", "liststructure", dataProjectAction);
StringBuffer dact = new StringBuffer();
if (dataProjectAction!=null) {
  dact.append("var arrProjectAction = new Array(\n");
  for (int i=0;i<dataProjectAction.length;i++) {
    dact.append("new Array(\"" + dataProjectAction[i].getField("id") + "\", \"" + dataProjectAction[i].getField("name") + "\", \"" + dataProjectAction[i].getField("description") + "\")\n");
    if (i<dataProjectAction.length-1) dact.append(",\n");
  }
  dact.append(");");
} else dact.append("var arrProjectAction = null");
xmlDocument.setParameter("array", dact.toString());

      
      out.println(xmlDocument.print());
      out.close();
    }
    private void printPageButtonGenerateOrder800005(HttpServletResponse response, VariablesSecureApp vars, String strC_Project_ID, String strgenerateorder, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800005");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/GenerateOrder800005", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Project_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ServiceProject_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "800005");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("800005");
        vars.removeMessage("800005");
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
    ServiceProjectData data = null;
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
                data.cProjectId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (ServiceProjectData.getCurrentDBTimestamp(this, data.cProjectId).equals(
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
                    data.cProjectId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|C_Project_ID", data.cProjectId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet ServiceProject. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
