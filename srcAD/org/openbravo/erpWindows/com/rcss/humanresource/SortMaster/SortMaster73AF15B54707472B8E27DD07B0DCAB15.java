
package org.openbravo.erpWindows.com.rcss.humanresource.SortMaster;


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

public class SortMaster73AF15B54707472B8E27DD07B0DCAB15 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(SortMaster73AF15B54707472B8E27DD07B0DCAB15.class);
  
  private static final String windowId = "84E5CC55E02A43619597C6C2A235D39A";
  private static final String tabId = "73AF15B54707472B8E27DD07B0DCAB15";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "BAD4B9EE21444679A85DAB2F8624F1BB";
  
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
     
      if (command.contains("FFBF17017DC14ECFB5A66B99D890E76A")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("FFBF17017DC14ECFB5A66B99D890E76A");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "FFBF17017DC14ECFB5A66B99D890E76A";
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
        String strrchrQualitystandardId = request.getParameter("inprchrQualitystandardId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrQualitystandardId.equals(""))
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

      String strRchr_Qualitystandard_ID = vars.getGlobalVariable("inprchrQualitystandardId", windowId + "|Rchr_Qualitystandard_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Qualitystandard_ID.equals("")) strRchr_Qualitystandard_ID = firstElement(vars, tableSQL);
          if (strRchr_Qualitystandard_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Qualitystandard_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Qualitystandard_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Qualitystandard_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Qualitystandard_ID.equals("")) strRchr_Qualitystandard_ID = vars.getRequiredGlobalVariable("inprchrQualitystandardId", windowId + "|Rchr_Qualitystandard_ID");
      else vars.setSessionValue(windowId + "|Rchr_Qualitystandard_ID", strRchr_Qualitystandard_ID);
      
      vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Qualitystandard_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.view");
      String strRchr_Qualitystandard_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Qualitystandard_ID = firstElement(vars, tableSQL);
          if (strRchr_Qualitystandard_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Qualitystandard_ID.equals("")) strRchr_Qualitystandard_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Qualitystandard_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamQualityno", tabId + "|paramQualityno");
vars.getRequestGlobalVariable("inpParamPartyconstruction", tabId + "|paramPartyconstruction");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rchr_Qualitystandard_ID");
      String strRchr_Qualitystandard_ID="";

      String strView = vars.getSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Qualitystandard_ID = firstElement(vars, tableSQL);
        if (strRchr_Qualitystandard_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Qualitystandard_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Qualitystandard_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRchr_Qualitystandard_ID = vars.getGlobalVariable("inprchrQualitystandardId", windowId + "|Rchr_Qualitystandard_ID", "");
      vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.view", "RELATION");
      printPageDataSheet(response, vars, strRchr_Qualitystandard_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Qualitystandard_ID = vars.getGlobalVariable("inprchrQualitystandardId", windowId + "|Rchr_Qualitystandard_ID", "");
      vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Qualitystandard_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRchr_Qualitystandard_ID = vars.getRequiredStringParameter("inprchrQualitystandardId");
      
      String strNext = nextElement(vars, strRchr_Qualitystandard_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRchr_Qualitystandard_ID = vars.getRequiredStringParameter("inprchrQualitystandardId");
      
      String strPrevious = previousElement(vars, strRchr_Qualitystandard_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Qualitystandard_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Qualitystandard_ID");

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

      String strRchr_Qualitystandard_ID = vars.getRequiredGlobalVariable("inprchrQualitystandardId", windowId + "|Rchr_Qualitystandard_ID");
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
          String strNext = nextElement(vars, strRchr_Qualitystandard_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Qualitystandard_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRchr_Qualitystandard_ID = vars.getRequiredStringParameter("inprchrQualitystandardId");
      //SortMaster73AF15B54707472B8E27DD07B0DCAB15Data data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.delete(this, strRchr_Qualitystandard_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrQualitystandardId");
        vars.setSessionValue(tabId + "|SortMaster73AF15B54707472B8E27DD07B0DCAB15.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONUpdateremqtyFFBF17017DC14ECFB5A66B99D890E76A")) {
        vars.setSessionValue("buttonFFBF17017DC14ECFB5A66B99D890E76A.strupdateremqty", vars.getStringParameter("inpupdateremqty"));
        vars.setSessionValue("buttonFFBF17017DC14ECFB5A66B99D890E76A.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonFFBF17017DC14ECFB5A66B99D890E76A.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonFFBF17017DC14ECFB5A66B99D890E76A.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonFFBF17017DC14ECFB5A66B99D890E76A.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "FFBF17017DC14ECFB5A66B99D890E76A", request.getServletPath());    
     } else if (vars.commandIn("BUTTONFFBF17017DC14ECFB5A66B99D890E76A")) {
        String strRchr_Qualitystandard_ID = vars.getGlobalVariable("inprchrQualitystandardId", windowId + "|Rchr_Qualitystandard_ID", "");
        String strupdateremqty = vars.getSessionValue("buttonFFBF17017DC14ECFB5A66B99D890E76A.strupdateremqty");
        String strProcessing = vars.getSessionValue("buttonFFBF17017DC14ECFB5A66B99D890E76A.strProcessing");
        String strOrg = vars.getSessionValue("buttonFFBF17017DC14ECFB5A66B99D890E76A.strOrg");
        String strClient = vars.getSessionValue("buttonFFBF17017DC14ECFB5A66B99D890E76A.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonUpdateremqtyFFBF17017DC14ECFB5A66B99D890E76A(response, vars, strRchr_Qualitystandard_ID, strupdateremqty, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONUpdateremqtyFFBF17017DC14ECFB5A66B99D890E76A")) {
        String strRchr_Qualitystandard_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Qualitystandard_ID", "");
        @SuppressWarnings("unused")
        String strupdateremqty = vars.getStringParameter("inpupdateremqty");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "FFBF17017DC14ECFB5A66B99D890E76A", (("Rchr_Qualitystandard_ID".equalsIgnoreCase("AD_Language"))?"0":strRchr_Qualitystandard_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
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
  private SortMaster73AF15B54707472B8E27DD07B0DCAB15Data getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    SortMaster73AF15B54707472B8E27DD07B0DCAB15Data data = new SortMaster73AF15B54707472B8E27DD07B0DCAB15Data();
    ServletException ex = null;
    try {
    data.rchrOrderstatusId = vars.getStringParameter("inprchrOrderstatusId");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rchrInspclothtypeId = vars.getRequiredStringParameter("inprchrInspclothtypeId");     data.rchrInspclothtypeIdr = vars.getStringParameter("inprchrInspclothtypeId_R");     data.orderno = vars.getStringParameter("inporderno");     data.mofno = vars.getStringParameter("inpmofno");     data.qualityno = vars.getRequiredStringParameter("inpqualityno");     data.mProductId = vars.getStringParameter("inpmProductId");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");    try {   data.warpcount = vars.getRequiredNumericParameter("inpwarpcount");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrWarpyarntypeId = vars.getRequiredStringParameter("inprchrWarpyarntypeId");     data.rchrWarpyarntypeIdr = vars.getStringParameter("inprchrWarpyarntypeId_R");     data.epcgVariantId = vars.getStringParameter("inpepcgVariantId");     data.epcgVariantIdr = vars.getStringParameter("inpepcgVariantId_R");     data.weftProduct = vars.getRequiredStringParameter("inpweftProduct");     data.weftProductr = vars.getStringParameter("inpweftProduct_R");    try {   data.weftcount = vars.getRequiredNumericParameter("inpweftcount");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrSkillgradeId = vars.getStringParameter("inprchrSkillgradeId");     data.warpyarntype = vars.getRequiredStringParameter("inpwarpyarntype");     data.warpyarntyper = vars.getStringParameter("inpwarpyarntype_R");     data.weftvariant = vars.getStringParameter("inpweftvariant");     data.weftvariantr = vars.getStringParameter("inpweftvariant_R");    try {   data.epi = vars.getRequiredNumericParameter("inpepi");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.epitolerence = vars.getNumericParameter("inpepitolerence");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.ppi = vars.getRequiredNumericParameter("inpppi");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.ppitolerence = vars.getNumericParameter("inpppitolerence");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.orderquantity = vars.getRequiredNumericParameter("inporderquantity");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrInspgreigewidthId = vars.getRequiredStringParameter("inprchrInspgreigewidthId");     data.rchrInspgreigewidthIdr = vars.getStringParameter("inprchrInspgreigewidthId_R");    try {   data.gwtolerence = vars.getNumericParameter("inpgwtolerence");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.atotal = vars.getNumericParameter("inpatotal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalends = vars.getNumericParameter("inptotalends");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.aonetotal = vars.getNumericParameter("inpaonetotal");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.btotal = vars.getNumericParameter("inpbtotal");  } catch (ServletException paramEx) { ex = paramEx; }     data.reed = vars.getStringParameter("inpreed");    try {   data.reedspace = vars.getNumericParameter("inpreedspace");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.sltotal = vars.getNumericParameter("inpsltotal");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrInspweaveId = vars.getRequiredStringParameter("inprchrInspweaveId");     data.rchrInspweaveIdr = vars.getStringParameter("inprchrInspweaveId_R");    try {   data.warpcrimp = vars.getNumericParameter("inpwarpcrimp");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weftcrimp = vars.getNumericParameter("inpweftcrimp");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.sizepickup = vars.getNumericParameter("inpsizepickup");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.warpglm = vars.getNumericParameter("inpwarpglm");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weftglm = vars.getNumericParameter("inpweftglm");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.glm = vars.getNumericParameter("inpglm");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.towrapmtrs = vars.getNumericParameter("inptowrapmtrs");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.warpyarnreq = vars.getNumericParameter("inpwarpyarnreq");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.weftyarnreq = vars.getNumericParameter("inpweftyarnreq");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.widthcms = vars.getNumericParameter("inpwidthcms");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.widthmtrs = vars.getNumericParameter("inpwidthmtrs");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.gsm = vars.getNumericParameter("inpgsm");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.coverfactor = vars.getNumericParameter("inpcoverfactor");  } catch (ServletException paramEx) { ex = paramEx; }     data.qstandard = vars.getStringParameter("inpqstandard");     data.partyconstruction = vars.getStringParameter("inppartyconstruction");    try {   data.remainingquantity = vars.getNumericParameter("inpremainingquantity");  } catch (ServletException paramEx) { ex = paramEx; }     data.orderdate = vars.getStringParameter("inporderdate");     data.deliverydate = vars.getStringParameter("inpdeliverydate");     data.rchrWarpweftclolorId = vars.getStringParameter("inprchrWarpweftclolorId");     data.rchrWarpweftclolorIdr = vars.getStringParameter("inprchrWarpweftclolorId_R");     data.weftcolor = vars.getStringParameter("inpweftcolor");     data.weftcolorr = vars.getStringParameter("inpweftcolor_R");     data.updateremqty = vars.getStringParameter("inpupdateremqty");    try {   data.avgglm = vars.getNumericParameter("inpavgglm");  } catch (ServletException paramEx) { ex = paramEx; }     data.description = vars.getStringParameter("inpdescription");    try {   data.nooframes = vars.getNumericParameter("inpnooframes");  } catch (ServletException paramEx) { ex = paramEx; }     data.selvweave = vars.getStringParameter("inpselvweave");    try {   data.selvedents = vars.getNumericParameter("inpselvedents");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.selveends = vars.getNumericParameter("inpselveends");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.selvedge = vars.getNumericParameter("inpselvedge");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.fulllengthper = vars.getNumericParameter("inpfulllengthper");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.fulllengthmtr = vars.getNumericParameter("inpfulllengthmtr");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.shortlengthper = vars.getNumericParameter("inpshortlengthper");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.shortlengthmtr = vars.getNumericParameter("inpshortlengthmtr");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.baleorrole = vars.getNumericParameter("inpbaleorrole");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.balesize = vars.getNumericParameter("inpbalesize");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.warptearingstrngth = vars.getNumericParameter("inpwarptearingstrngth");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.wefttearingstrngth = vars.getNumericParameter("inpwefttearingstrngth");  } catch (ServletException paramEx) { ex = paramEx; }     data.tearingtestmethod = vars.getStringParameter("inptearingtestmethod");    try {   data.warptensiblestrngth = vars.getNumericParameter("inpwarptensiblestrngth");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.wefttensiblestrngth = vars.getNumericParameter("inpwefttensiblestrngth");  } catch (ServletException paramEx) { ex = paramEx; }     data.tensibletestmethod = vars.getStringParameter("inptensibletestmethod");    try {   data.hsncode = vars.getNumericParameter("inphsncode");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.speed = vars.getRequiredNumericParameter("inpspeed");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.efficiency = vars.getRequiredNumericParameter("inpefficiency");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.sizing = vars.getRequiredNumericParameter("inpsizing");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.valueloss = vars.getNumericParameter("inpvalueloss");  } catch (ServletException paramEx) { ex = paramEx; }     data.typeofsort = vars.getRequiredStringParameter("inptypeofsort");     data.typeofsortr = vars.getStringParameter("inptypeofsort_R");    try {   data.onloomepi = vars.getNumericParameter("inponloomepi");  } catch (ServletException paramEx) { ex = paramEx; }     data.rchrQualitystandardId = vars.getRequestGlobalVariable("inprchrQualitystandardId", windowId + "|Rchr_Qualitystandard_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rchr_Qualitystandard_ID", data[0].getField("rchrQualitystandardId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      SortMaster73AF15B54707472B8E27DD07B0DCAB15Data[] data = SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprchrQualitystandardId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Qualitystandard_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamQualityno = vars.getSessionValue(tabId + "|paramQualityno");
String strParamPartyconstruction = vars.getSessionValue(tabId + "|paramPartyconstruction");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamQualityno) && ("").equals(strParamPartyconstruction)) || !(("").equals(strParamQualityno) || ("%").equals(strParamQualityno))  || !(("").equals(strParamPartyconstruction) || ("%").equals(strParamPartyconstruction)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Qualitystandard_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Qualitystandard_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/SortMaster/SortMaster73AF15B54707472B8E27DD07B0DCAB15_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "SortMaster73AF15B54707472B8E27DD07B0DCAB15", false, "document.frmMain.inprchrQualitystandardId", "grid", "..", "".equals("Y"), "SortMaster", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rchrQualitystandardId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "SortMaster73AF15B54707472B8E27DD07B0DCAB15_Relation.html", "SortMaster", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "SortMaster73AF15B54707472B8E27DD07B0DCAB15_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Qualitystandard_ID, TableSQLData tableSQL)
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
    SortMaster73AF15B54707472B8E27DD07B0DCAB15Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamQualityno = vars.getSessionValue(tabId + "|paramQualityno");
String strParamPartyconstruction = vars.getSessionValue(tabId + "|paramPartyconstruction");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamQualityno) && ("").equals(strParamPartyconstruction)) || !(("").equals(strParamQualityno) || ("%").equals(strParamQualityno))  || !(("").equals(strParamPartyconstruction) || ("%").equals(strParamPartyconstruction)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Qualitystandard_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Qualitystandard_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new SortMaster73AF15B54707472B8E27DD07B0DCAB15Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrQualitystandardId") == null || dataField.getField("rchrQualitystandardId").equals("")) {
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
        data = SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.set(Utility.getDefault(this, vars, "Ppitolerence", "2", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Rchr_Warpyarntype_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Gsm", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Mofno", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Aonetotal", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Nooframes", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Wefttensiblestrngth", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Weftcount", "", "84E5CC55E02A43619597C6C2A235D39A", "0", dataField), Utility.getDefault(this, vars, "Deliverydate", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Btotal", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Warpyarnreq", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Epitolerence", "2", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Sizing", "0", "84E5CC55E02A43619597C6C2A235D39A", "0", dataField), Utility.getDefault(this, vars, "Gwtolerence", "0.5", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Towrapmtrs", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Weftcrimp", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Efficiency", "0", "84E5CC55E02A43619597C6C2A235D39A", "0", dataField), Utility.getDefault(this, vars, "Rchr_Orderstatus_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Weftglm", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Warpcrimp", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Typeofsort", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Qualityno", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Rchr_Inspclothtype_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Sizepickup", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Speed", "0", "84E5CC55E02A43619597C6C2A235D39A", "0", dataField), Utility.getDefault(this, vars, "Warpyarntype", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Shortlengthper", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Orderno", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Weftyarnreq", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Sltotal", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Onloomepi", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Atotal", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Tensibletestmethod", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Balesize", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.selectDef905194ED19664BAF8EF7E9B5D0083061_0(this, Utility.getDefault(this, vars, "Updatedby", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField)), Utility.getDefault(this, vars, "Reed", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Rchr_Warpweftclolor_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), "Y", Utility.getDefault(this, vars, "Baleorrole", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Tearingtestmethod", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Warptensiblestrngth", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Weft_Product", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.selectDefA48E9BCD9D3245FC81102B7A5C623553_1(this,  vars.getLanguage(), Utility.getDefault(this, vars, "Weft_Product", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField)), Utility.getDefault(this, vars, "Weftcolor", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Avgglm", "0", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Epcg_Variant_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Remainingquantity", "0", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Partyconstruction", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Shortlengthmtr", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Rchr_Inspweave_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.selectDefB5E9587A01DC4914894F4586F02C3822_2(this, Utility.getDefault(this, vars, "Createdby", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField)), Utility.getDefault(this, vars, "Reedspace", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Wefttearingstrngth", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Selveends", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Weftvariant", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Qstandard", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Glm", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Widthcms", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Fulllengthmtr", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Hsncode", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Selvedge", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Totalends", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), "", Utility.getDefault(this, vars, "Rchr_Inspgreigewidth_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Warpcount", "", "84E5CC55E02A43619597C6C2A235D39A", "0", dataField), Utility.getDefault(this, vars, "Warptearingstrngth", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Selvedents", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Warpglm", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Epi", "@DefaultValue@!=0", "84E5CC55E02A43619597C6C2A235D39A", "0", dataField), Utility.getDefault(this, vars, "Valueloss", "0", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Coverfactor", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.selectDefE47AECC9E0024212AC3D2B5A7CCDEA8D_3(this,  vars.getLanguage(), Utility.getDefault(this, vars, "M_Product_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField)), Utility.getDefault(this, vars, "Orderquantity", "0", "84E5CC55E02A43619597C6C2A235D39A", "0", dataField), Utility.getDefault(this, vars, "Selvweave", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Rchr_Skillgrade_ID", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Updateremqty", "N", "84E5CC55E02A43619597C6C2A235D39A", "N", dataField), Utility.getDefault(this, vars, "Orderdate", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Description", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Fulllengthper", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField), Utility.getDefault(this, vars, "Ppi", "@DefaultValue@!=0", "84E5CC55E02A43619597C6C2A235D39A", "0", dataField), Utility.getDefault(this, vars, "Widthmtrs", "", "84E5CC55E02A43619597C6C2A235D39A", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/SortMaster/SortMaster73AF15B54707472B8E27DD07B0DCAB15_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/SortMaster/SortMaster73AF15B54707472B8E27DD07B0DCAB15_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "SortMaster73AF15B54707472B8E27DD07B0DCAB15", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrQualitystandardId", "", "..", "".equals("Y"), "SortMaster", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Qualitystandard_ID), !hasReadOnlyAccess);
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
      // if (!strRchr_Qualitystandard_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "SortMaster73AF15B54707472B8E27DD07B0DCAB15_Relation.html", "SortMaster", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "SortMaster73AF15B54707472B8E27DD07B0DCAB15_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Inspclothtype_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrInspclothtypeId"):dataField.getField("rchrInspclothtypeId")));
xmlDocument.setData("reportRchr_Inspclothtype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonWarpcount", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Warpyarntype_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrWarpyarntypeId"):dataField.getField("rchrWarpyarntypeId")));
xmlDocument.setData("reportRchr_Warpyarntype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Epcg_Variant_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgVariantId"):dataField.getField("epcgVariantId")));
xmlDocument.setData("reportEpcg_Variant_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonWeftcount", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "Warpyarntype", "D669B389CB4145B188D6C2B32919FC21", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("warpyarntype"):dataField.getField("warpyarntype")));
xmlDocument.setData("reportWarpyarntype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Weftvariant", "4BE1650E71A645D7BA66E65B0CFA1ACB", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("weftvariant"):dataField.getField("weftvariant")));
xmlDocument.setData("reportWeftvariant","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonEpi", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEpitolerence", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPpi", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPpitolerence", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonOrderquantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Inspgreigewidth_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrInspgreigewidthId"):dataField.getField("rchrInspgreigewidthId")));
xmlDocument.setData("reportRchr_Inspgreigewidth_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonGwtolerence", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonAtotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonAonetotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonBtotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSltotal", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonReedspace", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Inspweave_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrInspweaveId"):dataField.getField("rchrInspweaveId")));
xmlDocument.setData("reportRchr_Inspweave_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonWarpcrimp", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWeftcrimp", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWarpglm", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWeftglm", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGlm", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTowrapmtrs", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWarpyarnreq", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWeftyarnreq", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWidthcms", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonWidthmtrs", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGsm", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCoverfactor", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonRemainingquantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Orderdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Warpweftclolor_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrWarpweftclolorId"):dataField.getField("rchrWarpweftclolorId")));
xmlDocument.setData("reportRchr_Warpweftclolor_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Deliverydate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "18", "Weftcolor", "94AC478C75894B78B3AFE77798ED48A8", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("weftcolor"):dataField.getField("weftcolor")));
xmlDocument.setData("reportWeftcolor","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Updateremqty_BTNname", Utility.getButtonName(this, vars, "8893E22D5C854BD591030A8D294BAD7F", "Updateremqty_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalUpdateremqty = org.openbravo.erpCommon.utility.Utility.isModalProcess("FFBF17017DC14ECFB5A66B99D890E76A"); 
xmlDocument.setParameter("Updateremqty_Modal", modalUpdateremqty?"true":"false");
xmlDocument.setParameter("buttonAvgglm", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSpeed", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEfficiency", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonSizing", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonValueloss", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "17", "Typeofsort", "7BB7843C30F346CEA858FD85BE5860B8", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("typeofsort"):dataField.getField("typeofsort")));
xmlDocument.setData("reportTypeofsort","liststructure", comboTableData.select(!strCommand.equals("NEW")));
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

    private void printPageButtonUpdateremqtyFFBF17017DC14ECFB5A66B99D890E76A(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Qualitystandard_ID, String strupdateremqty, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FFBF17017DC14ECFB5A66B99D890E76A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/UpdateremqtyFFBF17017DC14ECFB5A66B99D890E76A", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Qualitystandard_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "SortMaster73AF15B54707472B8E27DD07B0DCAB15_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "FFBF17017DC14ECFB5A66B99D890E76A");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("FFBF17017DC14ECFB5A66B99D890E76A");
        vars.removeMessage("FFBF17017DC14ECFB5A66B99D890E76A");
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
    SortMaster73AF15B54707472B8E27DD07B0DCAB15Data data = null;
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
                data.rchrQualitystandardId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (SortMaster73AF15B54707472B8E27DD07B0DCAB15Data.getCurrentDBTimestamp(this, data.rchrQualitystandardId).equals(
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
                    data.rchrQualitystandardId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Qualitystandard_ID", data.rchrQualitystandardId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet SortMaster73AF15B54707472B8E27DD07B0DCAB15. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
