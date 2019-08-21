
package org.openbravo.erpWindows.com.redcarpet.production.ProductionRun;




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

public class ProductionRun0D43A1553F5744439A4D9D47DB20A44C extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(ProductionRun0D43A1553F5744439A4D9D47DB20A44C.class);
  
  private static final String windowId = "1C25794065B0409CA497CFC80BE5933D";
  private static final String tabId = "0D43A1553F5744439A4D9D47DB20A44C";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "9094B2DB5BE14EF9B590F8DBC20AFE40";
  
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
     
      if (command.contains("8D701FA22B894C5195C3E672661BF26D")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("8D701FA22B894C5195C3E672661BF26D");
        SessionInfo.setModuleId("9094B2DB5BE14EF9B590F8DBC20AFE40");
      }
     
      if (command.contains("EEA62DA04DF44D39B9BCA91BEAE81CED")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("EEA62DA04DF44D39B9BCA91BEAE81CED");
        SessionInfo.setModuleId("9094B2DB5BE14EF9B590F8DBC20AFE40");
      }
     
      if (command.contains("219F25ED92D74A40B3AB51973D01F629")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("219F25ED92D74A40B3AB51973D01F629");
        SessionInfo.setModuleId("9094B2DB5BE14EF9B590F8DBC20AFE40");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("8D701FA22B894C5195C3E672661BF26D")) {
        classInfo.type = "P";
        classInfo.id = "8D701FA22B894C5195C3E672661BF26D";
      }
     
      if (securedProcess && command.contains("EEA62DA04DF44D39B9BCA91BEAE81CED")) {
        classInfo.type = "P";
        classInfo.id = "EEA62DA04DF44D39B9BCA91BEAE81CED";
      }
     
      if (securedProcess && command.contains("219F25ED92D74A40B3AB51973D01F629")) {
        classInfo.type = "P";
        classInfo.id = "219F25ED92D74A40B3AB51973D01F629";
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
        String strrcprProductionrunId = request.getParameter("inprcprProductionrunId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrcprProductionrunId.equals(""))
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

      String strRcpr_Productionrun_ID = vars.getGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRcpr_Productionrun_ID.equals("")) strRcpr_Productionrun_ID = firstElement(vars, tableSQL);
          if (strRcpr_Productionrun_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcpr_Productionrun_ID, tableSQL);

      else printPageDataSheet(response, vars, strRcpr_Productionrun_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRcpr_Productionrun_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRcpr_Productionrun_ID.equals("")) strRcpr_Productionrun_ID = vars.getRequiredGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID");
      else vars.setSessionValue(windowId + "|Rcpr_Productionrun_ID", strRcpr_Productionrun_ID);
      
      vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.view", "EDIT");

      printPageEdit(response, request, vars, false, strRcpr_Productionrun_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.view");
      String strRcpr_Productionrun_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRcpr_Productionrun_ID = firstElement(vars, tableSQL);
          if (strRcpr_Productionrun_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRcpr_Productionrun_ID.equals("")) strRcpr_Productionrun_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRcpr_Productionrun_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentno", tabId + "|paramDocumentno");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rcpr_Productionrun_ID");
      String strRcpr_Productionrun_ID="";

      String strView = vars.getSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRcpr_Productionrun_ID = firstElement(vars, tableSQL);
        if (strRcpr_Productionrun_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcpr_Productionrun_ID, tableSQL);

      else printPageDataSheet(response, vars, strRcpr_Productionrun_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRcpr_Productionrun_ID = vars.getGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID", "");
      vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.view", "RELATION");
      printPageDataSheet(response, vars, strRcpr_Productionrun_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRcpr_Productionrun_ID = vars.getGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID", "");
      vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRcpr_Productionrun_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRcpr_Productionrun_ID = vars.getRequiredStringParameter("inprcprProductionrunId");
      
      String strNext = nextElement(vars, strRcpr_Productionrun_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRcpr_Productionrun_ID = vars.getRequiredStringParameter("inprcprProductionrunId");
      
      String strPrevious = previousElement(vars, strRcpr_Productionrun_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rcpr_Productionrun_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rcpr_Productionrun_ID");

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

      String strRcpr_Productionrun_ID = vars.getRequiredGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID");
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
          String strNext = nextElement(vars, strRcpr_Productionrun_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rcpr_Productionrun_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRcpr_Productionrun_ID = vars.getRequiredStringParameter("inprcprProductionrunId");
      //ProductionRun0D43A1553F5744439A4D9D47DB20A44CData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = ProductionRun0D43A1553F5744439A4D9D47DB20A44CData.delete(this, strRcpr_Productionrun_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rcprProductionrunId");
        vars.setSessionValue(tabId + "|ProductionRun0D43A1553F5744439A4D9D47DB20A44C.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONProductionissue8D701FA22B894C5195C3E672661BF26D")) {
        vars.setSessionValue("button8D701FA22B894C5195C3E672661BF26D.strproductionissue", vars.getStringParameter("inpproductionissue"));
        vars.setSessionValue("button8D701FA22B894C5195C3E672661BF26D.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button8D701FA22B894C5195C3E672661BF26D.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button8D701FA22B894C5195C3E672661BF26D.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button8D701FA22B894C5195C3E672661BF26D.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "8D701FA22B894C5195C3E672661BF26D", request.getServletPath());
      } else if (vars.commandIn("BUTTON8D701FA22B894C5195C3E672661BF26D")) {
        String strRcpr_Productionrun_ID = vars.getGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID", "");
        String strproductionissue = vars.getSessionValue("button8D701FA22B894C5195C3E672661BF26D.strproductionissue");
        String strProcessing = vars.getSessionValue("button8D701FA22B894C5195C3E672661BF26D.strProcessing");
        String strOrg = vars.getSessionValue("button8D701FA22B894C5195C3E672661BF26D.strOrg");
        String strClient = vars.getSessionValue("button8D701FA22B894C5195C3E672661BF26D.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProductionissue8D701FA22B894C5195C3E672661BF26D(response, vars, strRcpr_Productionrun_ID, strproductionissue, strProcessing);
        }
    } else if (vars.commandIn("BUTTONCreatelinesEEA62DA04DF44D39B9BCA91BEAE81CED")) {
        vars.setSessionValue("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.strcreatelines", vars.getStringParameter("inpcreatelines"));
        vars.setSessionValue("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "EEA62DA04DF44D39B9BCA91BEAE81CED", request.getServletPath());
      } else if (vars.commandIn("BUTTONEEA62DA04DF44D39B9BCA91BEAE81CED")) {
        String strRcpr_Productionrun_ID = vars.getGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID", "");
        String strcreatelines = vars.getSessionValue("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.strcreatelines");
        String strProcessing = vars.getSessionValue("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.strProcessing");
        String strOrg = vars.getSessionValue("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.strOrg");
        String strClient = vars.getSessionValue("buttonEEA62DA04DF44D39B9BCA91BEAE81CED.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCreatelinesEEA62DA04DF44D39B9BCA91BEAE81CED(response, vars, strRcpr_Productionrun_ID, strcreatelines, strProcessing);
        }
    } else if (vars.commandIn("BUTTONProductionreceipt219F25ED92D74A40B3AB51973D01F629")) {
        vars.setSessionValue("button219F25ED92D74A40B3AB51973D01F629.strproductionreceipt", vars.getStringParameter("inpproductionreceipt"));
        vars.setSessionValue("button219F25ED92D74A40B3AB51973D01F629.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button219F25ED92D74A40B3AB51973D01F629.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button219F25ED92D74A40B3AB51973D01F629.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button219F25ED92D74A40B3AB51973D01F629.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "219F25ED92D74A40B3AB51973D01F629", request.getServletPath());
      } else if (vars.commandIn("BUTTON219F25ED92D74A40B3AB51973D01F629")) {
        String strRcpr_Productionrun_ID = vars.getGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID", "");
        String strproductionreceipt = vars.getSessionValue("button219F25ED92D74A40B3AB51973D01F629.strproductionreceipt");
        String strProcessing = vars.getSessionValue("button219F25ED92D74A40B3AB51973D01F629.strProcessing");
        String strOrg = vars.getSessionValue("button219F25ED92D74A40B3AB51973D01F629.strOrg");
        String strClient = vars.getSessionValue("button219F25ED92D74A40B3AB51973D01F629.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProductionreceipt219F25ED92D74A40B3AB51973D01F629(response, vars, strRcpr_Productionrun_ID, strproductionreceipt, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProductionissue8D701FA22B894C5195C3E672661BF26D")) {
        String strRcpr_Productionrun_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcpr_Productionrun_ID", "");
        
        ProcessBundle pb = new ProcessBundle("8D701FA22B894C5195C3E672661BF26D", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcpr_Productionrun_ID", strRcpr_Productionrun_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONCreatelinesEEA62DA04DF44D39B9BCA91BEAE81CED")) {
        String strRcpr_Productionrun_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcpr_Productionrun_ID", "");
        
        ProcessBundle pb = new ProcessBundle("EEA62DA04DF44D39B9BCA91BEAE81CED", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcpr_Productionrun_ID", strRcpr_Productionrun_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONProductionreceipt219F25ED92D74A40B3AB51973D01F629")) {
        String strRcpr_Productionrun_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcpr_Productionrun_ID", "");
        
        ProcessBundle pb = new ProcessBundle("219F25ED92D74A40B3AB51973D01F629", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcpr_Productionrun_ID", strRcpr_Productionrun_ID);
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
  private ProductionRun0D43A1553F5744439A4D9D47DB20A44CData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    ProductionRun0D43A1553F5744439A4D9D47DB20A44CData data = new ProductionRun0D43A1553F5744439A4D9D47DB20A44CData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredStringParameter("inpadOrgId");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.rcprPrbomId = vars.getRequiredStringParameter("inprcprPrbomId");     data.rcprPrbomIdr = vars.getStringParameter("inprcprPrbomId_R");     data.movementdate = vars.getRequiredStringParameter("inpmovementdate");     data.startdate = vars.getRequiredStringParameter("inpstartdate");     data.exenddate = vars.getRequiredStringParameter("inpexenddate");    try {   data.issuequantity = vars.getRequiredNumericParameter("inpissuequantity");  } catch (ServletException paramEx) { ex = paramEx; }     data.mProductId = vars.getStringParameter("inpmProductId");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");     data.cUomId = vars.getStringParameter("inpcUomId");     data.cUomIdr = vars.getStringParameter("inpcUomId_R");     data.mLocatorId = vars.getRequiredStringParameter("inpmLocatorId");     data.mLocatorIdr = vars.getStringParameter("inpmLocatorId_R");    try {   data.itemcost = vars.getRequiredNumericParameter("inpitemcost");  } catch (ServletException paramEx) { ex = paramEx; }     data.productionissue = vars.getRequiredGlobalVariable("inpproductionissue", windowId + "|Productionissue");     data.createlines = vars.getRequiredStringParameter("inpcreatelines");     data.postedx = vars.getRequiredStringParameter("inppostedx");     data.productionreceipt = vars.getRequiredGlobalVariable("inpproductionreceipt", windowId + "|Productionreceipt");    try {   data.totalitemcost = vars.getRequiredNumericParameter("inptotalitemcost");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.expensecost = vars.getRequiredNumericParameter("inpexpensecost");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grossamount = vars.getRequiredNumericParameter("inpgrossamount");  } catch (ServletException paramEx) { ex = paramEx; }     data.postedy = vars.getRequiredStringParameter("inppostedy");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rcprProductionrunId = vars.getRequestGlobalVariable("inprcprProductionrunId", windowId + "|Rcpr_Productionrun_ID"); 
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
          vars.setSessionValue(windowId + "|Productionissue", data[0].getField("productionissue"));    vars.setSessionValue(windowId + "|Productionreceipt", data[0].getField("productionreceipt"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|Rcpr_Productionrun_ID", data[0].getField("rcprProductionrunId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      ProductionRun0D43A1553F5744439A4D9D47DB20A44CData[] data = ProductionRun0D43A1553F5744439A4D9D47DB20A44CData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprcprProductionrunId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRcpr_Productionrun_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRcpr_Productionrun_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRcpr_Productionrun_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/production/ProductionRun/ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "ProductionRun0D43A1553F5744439A4D9D47DB20A44C", false, "document.frmMain.inprcprProductionrunId", "grid", "..", "".equals("Y"), "ProductionRun", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rcprProductionrunId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Relation.html", "ProductionRun", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRcpr_Productionrun_ID, TableSQLData tableSQL)
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
    ProductionRun0D43A1553F5744439A4D9D47DB20A44CData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = ProductionRun0D43A1553F5744439A4D9D47DB20A44CData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRcpr_Productionrun_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRcpr_Productionrun_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new ProductionRun0D43A1553F5744439A4D9D47DB20A44CData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rcprProductionrunId") == null || dataField.getField("rcprProductionrunId").equals("")) {
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
        data = ProductionRun0D43A1553F5744439A4D9D47DB20A44CData.set(Utility.getDefault(this, vars, "Productionissue", "N", "1C25794065B0409CA497CFC80BE5933D", "N", dataField), Utility.getDefault(this, vars, "Postedy", "N", "1C25794065B0409CA497CFC80BE5933D", "N", dataField), Utility.getDefault(this, vars, "Issuequantity", "", "1C25794065B0409CA497CFC80BE5933D", "0", dataField), Utility.getDefault(this, vars, "Updatedby", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField), ProductionRun0D43A1553F5744439A4D9D47DB20A44CData.selectDef1BC591F1415448DDAF96569306E6F498_0(this, Utility.getDefault(this, vars, "Updatedby", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField)), "", Utility.getDefault(this, vars, "Movementdate", "@#Date@", "1C25794065B0409CA497CFC80BE5933D", "", dataField), Utility.getDefault(this, vars, "C_Uom_ID", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "1C25794065B0409CA497CFC80BE5933D", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField), ProductionRun0D43A1553F5744439A4D9D47DB20A44CData.selectDef69AC032679514D3B85558EC1677B65CD_1(this, Utility.getDefault(this, vars, "Createdby", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField)), "Y", Utility.getDefault(this, vars, "Exenddate", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField), Utility.getDefault(this, vars, "Grossamount", "0", "1C25794065B0409CA497CFC80BE5933D", "0", dataField), Utility.getDefault(this, vars, "Expensecost", "0", "1C25794065B0409CA497CFC80BE5933D", "0", dataField), Utility.getDefault(this, vars, "Itemcost", "0", "1C25794065B0409CA497CFC80BE5933D", "0", dataField), Utility.getDefault(this, vars, "Startdate", "@#Date@", "1C25794065B0409CA497CFC80BE5933D", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "1C25794065B0409CA497CFC80BE5933D", "", dataField), Utility.getDefault(this, vars, "Postedx", "N", "1C25794065B0409CA497CFC80BE5933D", "N", dataField), Utility.getDefault(this, vars, "Rcpr_Prbom_ID", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField), Utility.getDefault(this, vars, "M_Locator_ID", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField), ProductionRun0D43A1553F5744439A4D9D47DB20A44CData.selectDefCEB5E62DAFD244F88712D9ADD56CFAEB_2(this, Utility.getDefault(this, vars, "M_Locator_ID", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField)), Utility.getDefault(this, vars, "Productionreceipt", "N", "1C25794065B0409CA497CFC80BE5933D", "N", dataField), Utility.getDefault(this, vars, "Totalitemcost", "0", "1C25794065B0409CA497CFC80BE5933D", "0", dataField), Utility.getDefault(this, vars, "Createlines", "N", "1C25794065B0409CA497CFC80BE5933D", "N", dataField), Utility.getDefault(this, vars, "Documentno", "", "1C25794065B0409CA497CFC80BE5933D", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/production/ProductionRun/ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/production/ProductionRun/ProductionRun0D43A1553F5744439A4D9D47DB20A44C_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "ProductionRun0D43A1553F5744439A4D9D47DB20A44C", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprcprProductionrunId", "", "..", "".equals("Y"), "ProductionRun", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRcpr_Productionrun_ID), !hasReadOnlyAccess);
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
      // if (!strRcpr_Productionrun_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Relation.html", "ProductionRun", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "Rcpr_Prbom_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rcprPrbomId"):dataField.getField("rcprPrbomId")));
xmlDocument.setData("reportRcpr_Prbom_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Movementdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Startdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Exenddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonIssuequantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "M_Product_ID", "171", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mProductId"):dataField.getField("mProductId")));
xmlDocument.setData("reportM_Product_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Uom_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomId"):dataField.getField("cUomId")));
xmlDocument.setData("reportC_Uom_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonItemcost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Productionissue_BTNname", Utility.getButtonName(this, vars, "2B2CF986F799419699498BCCFFC0DBC2", "Productionissue_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProductionissue = org.openbravo.erpCommon.utility.Utility.isModalProcess("8D701FA22B894C5195C3E672661BF26D"); 
xmlDocument.setParameter("Productionissue_Modal", modalProductionissue?"true":"false");
xmlDocument.setParameter("Createlines_BTNname", Utility.getButtonName(this, vars, "25C1887DD92940639B017DBA93859684", "Createlines_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCreatelines = org.openbravo.erpCommon.utility.Utility.isModalProcess("EEA62DA04DF44D39B9BCA91BEAE81CED"); 
xmlDocument.setParameter("Createlines_Modal", modalCreatelines?"true":"false");
xmlDocument.setParameter("Productionreceipt_BTNname", Utility.getButtonName(this, vars, "57765F4C2C144BA3AB617250C0A7D046", "Productionreceipt_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProductionreceipt = org.openbravo.erpCommon.utility.Utility.isModalProcess("219F25ED92D74A40B3AB51973D01F629"); 
xmlDocument.setParameter("Productionreceipt_Modal", modalProductionreceipt?"true":"false");
xmlDocument.setParameter("buttonTotalitemcost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonExpensecost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGrossamount", Utility.messageBD(this, "Calc", vars.getLanguage()));
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



    void printPageButtonProductionissue8D701FA22B894C5195C3E672661BF26D(HttpServletResponse response, VariablesSecureApp vars, String strRcpr_Productionrun_ID, String strproductionissue, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8D701FA22B894C5195C3E672661BF26D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Productionissue8D701FA22B894C5195C3E672661BF26D", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcpr_Productionrun_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "8D701FA22B894C5195C3E672661BF26D");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("8D701FA22B894C5195C3E672661BF26D");
        vars.removeMessage("8D701FA22B894C5195C3E672661BF26D");
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
    void printPageButtonCreatelinesEEA62DA04DF44D39B9BCA91BEAE81CED(HttpServletResponse response, VariablesSecureApp vars, String strRcpr_Productionrun_ID, String strcreatelines, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process EEA62DA04DF44D39B9BCA91BEAE81CED");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/CreatelinesEEA62DA04DF44D39B9BCA91BEAE81CED", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcpr_Productionrun_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "EEA62DA04DF44D39B9BCA91BEAE81CED");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("EEA62DA04DF44D39B9BCA91BEAE81CED");
        vars.removeMessage("EEA62DA04DF44D39B9BCA91BEAE81CED");
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
    void printPageButtonProductionreceipt219F25ED92D74A40B3AB51973D01F629(HttpServletResponse response, VariablesSecureApp vars, String strRcpr_Productionrun_ID, String strproductionreceipt, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 219F25ED92D74A40B3AB51973D01F629");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Productionreceipt219F25ED92D74A40B3AB51973D01F629", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcpr_Productionrun_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ProductionRun0D43A1553F5744439A4D9D47DB20A44C_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "219F25ED92D74A40B3AB51973D01F629");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("219F25ED92D74A40B3AB51973D01F629");
        vars.removeMessage("219F25ED92D74A40B3AB51973D01F629");
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
    ProductionRun0D43A1553F5744439A4D9D47DB20A44CData data = null;
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
                data.rcprProductionrunId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (ProductionRun0D43A1553F5744439A4D9D47DB20A44CData.getCurrentDBTimestamp(this, data.rcprProductionrunId).equals(
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
                    data.rcprProductionrunId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rcpr_Productionrun_ID", data.rcprProductionrunId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet ProductionRun0D43A1553F5744439A4D9D47DB20A44C. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
