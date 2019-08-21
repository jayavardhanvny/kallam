
package org.openbravo.erpWindows.ProductionRun;




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

public class ProductionRun extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(ProductionRun.class);
  
  private static final String windowId = "FF808181323E504701323E57E08D0017";
  private static final String tabId = "C9B5394DBA8C465C9CE26A361696B06E";
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
     
      if (command.contains("FF80818132A4F6AD0132A573DD7A0021")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("FF80818132A4F6AD0132A573DD7A0021");
        SessionInfo.setModuleId("0");
      }
     
      if (command.contains("FF808181326CD80501326CE906D70042")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("FF808181326CD80501326CE906D70042");
        SessionInfo.setModuleId("0");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("FF80818132A4F6AD0132A573DD7A0021")) {
        classInfo.type = "P";
        classInfo.id = "FF80818132A4F6AD0132A573DD7A0021";
      }
     
      if (securedProcess && command.contains("FF808181326CD80501326CE906D70042")) {
        classInfo.type = "P";
        classInfo.id = "FF808181326CD80501326CE906D70042";
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
        String strmProductionplanId = request.getParameter("inpmProductionplanId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strmProductionplanId.equals(""))
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

      String strM_ProductionPlan_ID = vars.getGlobalVariable("inpmProductionplanId", windowId + "|M_ProductionPlan_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|ProductionRun.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strM_ProductionPlan_ID.equals("")) strM_ProductionPlan_ID = firstElement(vars, tableSQL);
          if (strM_ProductionPlan_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strM_ProductionPlan_ID, tableSQL);

      else printPageDataSheet(response, vars, strM_ProductionPlan_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strM_ProductionPlan_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strM_ProductionPlan_ID.equals("")) strM_ProductionPlan_ID = vars.getRequiredGlobalVariable("inpmProductionplanId", windowId + "|M_ProductionPlan_ID");
      else vars.setSessionValue(windowId + "|M_ProductionPlan_ID", strM_ProductionPlan_ID);
      
      vars.setSessionValue(tabId + "|ProductionRun.view", "EDIT");

      printPageEdit(response, request, vars, false, strM_ProductionPlan_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|ProductionRun.view");
      String strM_ProductionPlan_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strM_ProductionPlan_ID = firstElement(vars, tableSQL);
          if (strM_ProductionPlan_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strM_ProductionPlan_ID.equals("")) strM_ProductionPlan_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strM_ProductionPlan_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamM_Production_ID", tabId + "|paramM_Production_ID");
vars.getRequestGlobalVariable("inpParamLine", tabId + "|paramLine");
vars.getRequestGlobalVariable("inpParamLine_f", tabId + "|paramLine_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|M_ProductionPlan_ID");
      String strM_ProductionPlan_ID="";

      String strView = vars.getSessionValue(tabId + "|ProductionRun.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strM_ProductionPlan_ID = firstElement(vars, tableSQL);
        if (strM_ProductionPlan_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|ProductionRun.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strM_ProductionPlan_ID, tableSQL);

      else printPageDataSheet(response, vars, strM_ProductionPlan_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strM_ProductionPlan_ID = vars.getGlobalVariable("inpmProductionplanId", windowId + "|M_ProductionPlan_ID", "");
      vars.setSessionValue(tabId + "|ProductionRun.view", "RELATION");
      printPageDataSheet(response, vars, strM_ProductionPlan_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strM_ProductionPlan_ID = vars.getGlobalVariable("inpmProductionplanId", windowId + "|M_ProductionPlan_ID", "");
      vars.setSessionValue(tabId + "|ProductionRun.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strM_ProductionPlan_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strM_ProductionPlan_ID = vars.getRequiredStringParameter("inpmProductionplanId");
      
      String strNext = nextElement(vars, strM_ProductionPlan_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strM_ProductionPlan_ID = vars.getRequiredStringParameter("inpmProductionplanId");
      
      String strPrevious = previousElement(vars, strM_ProductionPlan_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|ProductionRun.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|ProductionRun.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|ProductionRun.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|ProductionRun.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|M_ProductionPlan_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|ProductionRun.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|ProductionRun.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|M_ProductionPlan_ID");

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

      String strM_ProductionPlan_ID = vars.getRequiredGlobalVariable("inpmProductionplanId", windowId + "|M_ProductionPlan_ID");
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
          String strNext = nextElement(vars, strM_ProductionPlan_ID, tableSQL);
          vars.setSessionValue(windowId + "|M_ProductionPlan_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strM_ProductionPlan_ID = vars.getRequiredStringParameter("inpmProductionplanId");
      //ProductionRunData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = ProductionRunData.delete(this, strM_ProductionPlan_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|mProductionplanId");
        vars.setSessionValue(tabId + "|ProductionRun.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONUsedmaterialFF80818132A4F6AD0132A573DD7A0021")) {
        vars.setSessionValue("buttonFF80818132A4F6AD0132A573DD7A0021.strusedmaterial", vars.getStringParameter("inpusedmaterial"));
        vars.setSessionValue("buttonFF80818132A4F6AD0132A573DD7A0021.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonFF80818132A4F6AD0132A573DD7A0021.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonFF80818132A4F6AD0132A573DD7A0021.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonFF80818132A4F6AD0132A573DD7A0021.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "FF80818132A4F6AD0132A573DD7A0021", request.getServletPath());
      } else if (vars.commandIn("BUTTONFF80818132A4F6AD0132A573DD7A0021")) {
        String strM_ProductionPlan_ID = vars.getGlobalVariable("inpmProductionplanId", windowId + "|M_ProductionPlan_ID", "");
        String strusedmaterial = vars.getSessionValue("buttonFF80818132A4F6AD0132A573DD7A0021.strusedmaterial");
        String strProcessing = vars.getSessionValue("buttonFF80818132A4F6AD0132A573DD7A0021.strProcessing");
        String strOrg = vars.getSessionValue("buttonFF80818132A4F6AD0132A573DD7A0021.strOrg");
        String strClient = vars.getSessionValue("buttonFF80818132A4F6AD0132A573DD7A0021.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonUsedmaterialFF80818132A4F6AD0132A573DD7A0021(response, vars, strM_ProductionPlan_ID, strusedmaterial, strProcessing);
        }
    } else if (vars.commandIn("BUTTONValidatingFF808181326CD80501326CE906D70042")) {
        vars.setSessionValue("buttonFF808181326CD80501326CE906D70042.strvalidating", vars.getStringParameter("inpvalidating"));
        vars.setSessionValue("buttonFF808181326CD80501326CE906D70042.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonFF808181326CD80501326CE906D70042.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonFF808181326CD80501326CE906D70042.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonFF808181326CD80501326CE906D70042.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "FF808181326CD80501326CE906D70042", request.getServletPath());
      } else if (vars.commandIn("BUTTONFF808181326CD80501326CE906D70042")) {
        String strM_ProductionPlan_ID = vars.getGlobalVariable("inpmProductionplanId", windowId + "|M_ProductionPlan_ID", "");
        String strvalidating = vars.getSessionValue("buttonFF808181326CD80501326CE906D70042.strvalidating");
        String strProcessing = vars.getSessionValue("buttonFF808181326CD80501326CE906D70042.strProcessing");
        String strOrg = vars.getSessionValue("buttonFF808181326CD80501326CE906D70042.strOrg");
        String strClient = vars.getSessionValue("buttonFF808181326CD80501326CE906D70042.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonValidatingFF808181326CD80501326CE906D70042(response, vars, strM_ProductionPlan_ID, strvalidating, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONUsedmaterialFF80818132A4F6AD0132A573DD7A0021")) {
        String strM_ProductionPlan_ID = vars.getGlobalVariable("inpKey", windowId + "|M_ProductionPlan_ID", "");
        
        ProcessBundle pb = new ProcessBundle("FF80818132A4F6AD0132A573DD7A0021", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("M_ProductionPlan_ID", strM_ProductionPlan_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONValidatingFF808181326CD80501326CE906D70042")) {
        String strM_ProductionPlan_ID = vars.getGlobalVariable("inpKey", windowId + "|M_ProductionPlan_ID", "");
        
        ProcessBundle pb = new ProcessBundle("FF808181326CD80501326CE906D70042", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("M_ProductionPlan_ID", strM_ProductionPlan_ID);
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
  private ProductionRunData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    ProductionRunData data = new ProductionRunData();
    ServletException ex = null;
    try {
    data.mProductionId = vars.getRequiredGlobalVariable("inpmProductionId", windowId + "|M_Production_ID");     data.productionplandate = vars.getStringParameter("inpproductionplandate");    try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.maWrphaseId = vars.getRequiredStringParameter("inpmaWrphaseId");     data.maWrphaseIdr = vars.getStringParameter("inpmaWrphaseId_R");     data.starttime = vars.getStringParameter("inpstarttime");     data.endtime = vars.getStringParameter("inpendtime");     data.maCostcenterVersionId = vars.getStringParameter("inpmaCostcenterVersionId");     data.maCostcenterVersionIdr = vars.getStringParameter("inpmaCostcenterVersionId_R");    try {   data.calccost = vars.getNumericParameter("inpcalccost");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.neededquantity = vars.getRequiredNumericParameter("inpneededquantity");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.productionqty = vars.getRequiredNumericParameter("inpproductionqty");  } catch (ServletException paramEx) { ex = paramEx; }     data.secondaryunit = vars.getStringParameter("inpsecondaryunit");    try {   data.conversionrate = vars.getNumericParameter("inpconversionrate");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.secondaryqty = vars.getNumericParameter("inpsecondaryqty");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.rejectedquantity = vars.getRequiredNumericParameter("inprejectedquantity");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.maCostcenteruse = vars.getRequiredNumericParameter("inpmaCostcenteruse");  } catch (ServletException paramEx) { ex = paramEx; }     data.usedmaterial = vars.getStringParameter("inpusedmaterial");     data.outsourced = vars.getStringParameter("inpoutsourced", "N");     data.processed = vars.getStringParameter("inpprocessed", "N");    try {   data.estimatedtime = vars.getRequiredNumericParameter("inpestimatedtime");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.runtime = vars.getRequiredNumericParameter("inpruntime");  } catch (ServletException paramEx) { ex = paramEx; }     data.closephase = vars.getStringParameter("inpclosephase", "N");     data.validating = vars.getStringParameter("inpvalidating");     data.mLocatorId = vars.getRequestGlobalVariable("inpmLocatorId", windowId + "|M_Locator_ID");     data.mProductionplanId = vars.getRequestGlobalVariable("inpmProductionplanId", windowId + "|M_ProductionPlan_ID");     data.description = vars.getStringParameter("inpdescription");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.mProductId = vars.getStringParameter("inpmProductId");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



          vars.getGlobalVariable("inpadOrgForced", windowId + "|AD_Org_Forced", "");
          vars.getGlobalVariable("inpworkeffortvalidated", windowId + "|WorkEffortValidated", "");
    
    

    
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
          vars.setSessionValue(windowId + "|M_Production_ID", data[0].getField("mProductionId"));    vars.setSessionValue(windowId + "|M_ProductionPlan_ID", data[0].getField("mProductionplanId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|M_Locator_ID", data[0].getField("mLocatorId"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      ProductionRunData[] data = ProductionRunData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpmProductionplanId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strM_ProductionPlan_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamM_Production_ID = vars.getSessionValue(tabId + "|paramM_Production_ID");
String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamM_Production_ID) && ("").equals(strParamLine) && ("").equals(strParamLine_f)) || !(("").equals(strParamM_Production_ID) || ("%").equals(strParamM_Production_ID))  || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strM_ProductionPlan_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strM_ProductionPlan_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ProductionRun/ProductionRun_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "ProductionRun", false, "document.frmMain.inpmProductionplanId", "grid", "..", "".equals("Y"), "ProductionRun", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), true, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
    xmlDocument.setParameter("KeyName", "mProductionplanId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ProductionRun_Relation.html", "ProductionRun", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ProductionRun_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strM_ProductionPlan_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " M_ProductionPlan.Line ASC";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    ProductionRunData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamM_Production_ID = vars.getSessionValue(tabId + "|paramM_Production_ID");
String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamM_Production_ID) && ("").equals(strParamLine) && ("").equals(strParamLine_f)) || !(("").equals(strParamM_Production_ID) || ("%").equals(strParamM_Production_ID))  || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = ProductionRunData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strM_ProductionPlan_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strM_ProductionPlan_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (data==null || data.length==0) {
        strM_ProductionPlan_ID = firstElement(vars, tableSQL);
        if (strM_ProductionPlan_ID.equals("")) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        } else {
          data = ProductionRunData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strM_ProductionPlan_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
        }
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new ProductionRunData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("mProductionplanId") == null || dataField.getField("mProductionplanId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strAD_Org_Forced = ProductionRunData.selectAuxFF808181323F83E801323F959F07002C(this, ((dataField!=null)?dataField.getField("mProductionId"):((data==null || data.length==0)?"":data[0].mProductionId)));
    vars.setSessionValue(windowId + "|AD_Org_Forced", strAD_Org_Forced);
        String strWorkEffortValidated = ProductionRunData.selectAuxFF80818132916D6E013291C49E7E00E6(this, ((dataField!=null)?dataField.getField("mProductionId"):((data==null || data.length==0)?"":data[0].mProductionId)));
    vars.setSessionValue(windowId + "|WorkEffortValidated", strWorkEffortValidated);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = ProductionRunData.set("", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "FF808181323E504701323E57E08D0017", "", dataField), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "FF808181323E504701323E57E08D0017", "", dataField), ProductionRunData.selectDef4759_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "FF808181323E504701323E57E08D0017", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "FF808181323E504701323E57E08D0017", "", dataField), ProductionRunData.selectDef4761_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "FF808181323E504701323E57E08D0017", "", dataField)), Utility.getDefault(this, vars, "M_Production_ID", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "ProductionQty", "", "FF808181323E504701323E57E08D0017", "0", dataField), Utility.getDefault(this, vars, "M_Locator_ID", "@M_Locator_ID@", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "Description", "", "FF808181323E504701323E57E08D0017", "", dataField), ProductionRunData.selectDef4769(this, Utility.getContext(this, vars, "M_Production_ID", "FF808181323E504701323E57E08D0017")), Utility.getDefault(this, vars, "Productionplandate", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "Calccost", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "Validating", "", "FF808181323E504701323E57E08D0017", "N", dataField), Utility.getDefault(this, vars, "Closephase", "N", "FF808181323E504701323E57E08D0017", "N", dataField), ProductionRunData.selectDef802026(this, Utility.getContext(this, vars, "MA_WRPhase_ID", "FF808181323E504701323E57E08D0017")), Utility.getDefault(this, vars, "Rejectedquantity", "", "FF808181323E504701323E57E08D0017", "0", dataField), Utility.getDefault(this, vars, "Usedmaterial", "", "FF808181323E504701323E57E08D0017", "N", dataField), Utility.getDefault(this, vars, "Processed", "", "FF808181323E504701323E57E08D0017", "N", dataField), Utility.getDefault(this, vars, "MA_Wrphase_ID", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "MA_Costcenteruse", "", "FF808181323E504701323E57E08D0017", "0", dataField), Utility.getDefault(this, vars, "Secondaryunit", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "Secondaryqty", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "Conversionrate", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "Outsourced", "", "FF808181323E504701323E57E08D0017", "N", dataField), Utility.getDefault(this, vars, "MA_Costcenter_Version_ID", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "Starttime", "", "FF808181323E504701323E57E08D0017", "", dataField), Utility.getDefault(this, vars, "Estimatedtime", "0", "FF808181323E504701323E57E08D0017", "0", dataField), Utility.getDefault(this, vars, "Runtime", "0", "FF808181323E504701323E57E08D0017", "0", dataField), Utility.getDefault(this, vars, "Endtime", "", "FF808181323E504701323E57E08D0017", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ProductionRun/ProductionRun_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ProductionRun/ProductionRun_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "ProductionRun", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpmProductionplanId", "", "..", "".equals("Y"), "ProductionRun", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strM_ProductionPlan_ID), !hasReadOnlyAccess);
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
      // if (!strM_ProductionPlan_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ProductionRun_Relation.html", "ProductionRun", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ProductionRun_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("AD_Org_Forced", strAD_Org_Forced);
        xmlDocument.setParameter("WorkEffortValidated", strWorkEffortValidated);
    
    
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
xmlDocument.setParameter("Productionplandate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "MA_Wrphase_ID", "", "800006", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("maWrphaseId"):dataField.getField("maWrphaseId")));
xmlDocument.setData("reportMA_Wrphase_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "MA_Costcenter_Version_ID", "", "80418A74E20E4D2590CF848E90D7CD6D", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("maCostcenterVersionId"):dataField.getField("maCostcenterVersionId")));
xmlDocument.setData("reportMA_Costcenter_Version_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonCalccost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonProductionQty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonConversionrate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSecondaryqty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonMA_Costcenteruse", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Usedmaterial_BTNname", Utility.getButtonName(this, vars, "22D230F3B7A044C1AD5D8CDFE7C36AF7", "Usedmaterial_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalUsedmaterial = org.openbravo.erpCommon.utility.Utility.isModalProcess("FF80818132A4F6AD0132A573DD7A0021"); 
xmlDocument.setParameter("Usedmaterial_Modal", modalUsedmaterial?"true":"false");
xmlDocument.setParameter("buttonEstimatedtime", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Validating_BTNname", Utility.getButtonName(this, vars, "F36C5360FCA7427AA77D5543BE7B0C46", "Validating_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalValidating = org.openbravo.erpCommon.utility.Utility.isModalProcess("FF808181326CD80501326CE906D70042"); 
xmlDocument.setParameter("Validating_Modal", modalValidating?"true":"false");
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



    void printPageButtonUsedmaterialFF80818132A4F6AD0132A573DD7A0021(HttpServletResponse response, VariablesSecureApp vars, String strM_ProductionPlan_ID, String strusedmaterial, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF80818132A4F6AD0132A573DD7A0021");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/UsedmaterialFF80818132A4F6AD0132A573DD7A0021", discard).createXmlDocument();
      xmlDocument.setParameter("key", strM_ProductionPlan_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ProductionRun_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "FF80818132A4F6AD0132A573DD7A0021");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("FF80818132A4F6AD0132A573DD7A0021");
        vars.removeMessage("FF80818132A4F6AD0132A573DD7A0021");
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
    void printPageButtonValidatingFF808181326CD80501326CE906D70042(HttpServletResponse response, VariablesSecureApp vars, String strM_ProductionPlan_ID, String strvalidating, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF808181326CD80501326CE906D70042");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ValidatingFF808181326CD80501326CE906D70042", discard).createXmlDocument();
      xmlDocument.setParameter("key", strM_ProductionPlan_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ProductionRun_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "FF808181326CD80501326CE906D70042");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("FF808181326CD80501326CE906D70042");
        vars.removeMessage("FF808181326CD80501326CE906D70042");
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
    ProductionRunData data = null;
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
                data.mProductionplanId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (ProductionRunData.getCurrentDBTimestamp(this, data.mProductionplanId).equals(
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
                    data.mProductionplanId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|M_ProductionPlan_ID", data.mProductionplanId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet ProductionRun. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
