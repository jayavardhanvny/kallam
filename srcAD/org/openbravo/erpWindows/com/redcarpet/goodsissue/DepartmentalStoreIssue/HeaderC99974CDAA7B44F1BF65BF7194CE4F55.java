
package org.openbravo.erpWindows.com.redcarpet.goodsissue.DepartmentalStoreIssue;




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

public class HeaderC99974CDAA7B44F1BF65BF7194CE4F55 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(HeaderC99974CDAA7B44F1BF65BF7194CE4F55.class);
  
  private static final String windowId = "34B0D49ECE1A4B1AAE945A33DFA1607F";
  private static final String tabId = "C99974CDAA7B44F1BF65BF7194CE4F55";
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
     
      if (command.contains("24C4E55795B741059C4C53C2E2D51CB3")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("24C4E55795B741059C4C53C2E2D51CB3");
        SessionInfo.setModuleId("C71D22E39B944C0EA8C5D2B655E53B3F");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("24C4E55795B741059C4C53C2E2D51CB3")) {
        classInfo.type = "P";
        classInfo.id = "24C4E55795B741059C4C53C2E2D51CB3";
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
        String strrcgiDepartmentissueId = request.getParameter("inprcgiDepartmentissueId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrcgiDepartmentissueId.equals(""))
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

      String strRcgi_Departmentissue_ID = vars.getGlobalVariable("inprcgiDepartmentissueId", windowId + "|Rcgi_Departmentissue_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRcgi_Departmentissue_ID.equals("")) strRcgi_Departmentissue_ID = firstElement(vars, tableSQL);
          if (strRcgi_Departmentissue_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcgi_Departmentissue_ID, tableSQL);

      else printPageDataSheet(response, vars, strRcgi_Departmentissue_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRcgi_Departmentissue_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRcgi_Departmentissue_ID.equals("")) strRcgi_Departmentissue_ID = vars.getRequiredGlobalVariable("inprcgiDepartmentissueId", windowId + "|Rcgi_Departmentissue_ID");
      else vars.setSessionValue(windowId + "|Rcgi_Departmentissue_ID", strRcgi_Departmentissue_ID);
      
      vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.view", "EDIT");

      printPageEdit(response, request, vars, false, strRcgi_Departmentissue_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.view");
      String strRcgi_Departmentissue_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRcgi_Departmentissue_ID = firstElement(vars, tableSQL);
          if (strRcgi_Departmentissue_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRcgi_Departmentissue_ID.equals("")) strRcgi_Departmentissue_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRcgi_Departmentissue_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamTotallines", tabId + "|paramTotallines");
vars.getRequestGlobalVariable("inpParamRchr_Employee_ID", tabId + "|paramRchr_Employee_ID");
vars.getRequestGlobalVariable("inpParamTotallines_f", tabId + "|paramTotallines_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rcgi_Departmentissue_ID");
      String strRcgi_Departmentissue_ID="";

      String strView = vars.getSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRcgi_Departmentissue_ID = firstElement(vars, tableSQL);
        if (strRcgi_Departmentissue_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcgi_Departmentissue_ID, tableSQL);

      else printPageDataSheet(response, vars, strRcgi_Departmentissue_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRcgi_Departmentissue_ID = vars.getGlobalVariable("inprcgiDepartmentissueId", windowId + "|Rcgi_Departmentissue_ID", "");
      vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.view", "RELATION");
      printPageDataSheet(response, vars, strRcgi_Departmentissue_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRcgi_Departmentissue_ID = vars.getGlobalVariable("inprcgiDepartmentissueId", windowId + "|Rcgi_Departmentissue_ID", "");
      vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRcgi_Departmentissue_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRcgi_Departmentissue_ID = vars.getRequiredStringParameter("inprcgiDepartmentissueId");
      
      String strNext = nextElement(vars, strRcgi_Departmentissue_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRcgi_Departmentissue_ID = vars.getRequiredStringParameter("inprcgiDepartmentissueId");
      
      String strPrevious = previousElement(vars, strRcgi_Departmentissue_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rcgi_Departmentissue_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rcgi_Departmentissue_ID");

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

      String strRcgi_Departmentissue_ID = vars.getRequiredGlobalVariable("inprcgiDepartmentissueId", windowId + "|Rcgi_Departmentissue_ID");
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
          String strNext = nextElement(vars, strRcgi_Departmentissue_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rcgi_Departmentissue_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRcgi_Departmentissue_ID = vars.getRequiredStringParameter("inprcgiDepartmentissueId");
      //HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data.delete(this, strRcgi_Departmentissue_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rcgiDepartmentissueId");
        vars.setSessionValue(tabId + "|HeaderC99974CDAA7B44F1BF65BF7194CE4F55.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONComplete24C4E55795B741059C4C53C2E2D51CB3")) {
        vars.setSessionValue("button24C4E55795B741059C4C53C2E2D51CB3.strcomplete", vars.getStringParameter("inpcomplete"));
        vars.setSessionValue("button24C4E55795B741059C4C53C2E2D51CB3.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button24C4E55795B741059C4C53C2E2D51CB3.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button24C4E55795B741059C4C53C2E2D51CB3.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button24C4E55795B741059C4C53C2E2D51CB3.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "24C4E55795B741059C4C53C2E2D51CB3", request.getServletPath());
      } else if (vars.commandIn("BUTTON24C4E55795B741059C4C53C2E2D51CB3")) {
        String strRcgi_Departmentissue_ID = vars.getGlobalVariable("inprcgiDepartmentissueId", windowId + "|Rcgi_Departmentissue_ID", "");
        String strcomplete = vars.getSessionValue("button24C4E55795B741059C4C53C2E2D51CB3.strcomplete");
        String strProcessing = vars.getSessionValue("button24C4E55795B741059C4C53C2E2D51CB3.strProcessing");
        String strOrg = vars.getSessionValue("button24C4E55795B741059C4C53C2E2D51CB3.strOrg");
        String strClient = vars.getSessionValue("button24C4E55795B741059C4C53C2E2D51CB3.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonComplete24C4E55795B741059C4C53C2E2D51CB3(response, vars, strRcgi_Departmentissue_ID, strcomplete, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONComplete24C4E55795B741059C4C53C2E2D51CB3")) {
        String strRcgi_Departmentissue_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcgi_Departmentissue_ID", "");
        
        ProcessBundle pb = new ProcessBundle("24C4E55795B741059C4C53C2E2D51CB3", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcgi_Departmentissue_ID", strRcgi_Departmentissue_ID);
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
  private HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data data = new HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.movementdate = vars.getRequiredStringParameter("inpmovementdate");     data.mRequisitionId = vars.getStringParameter("inpmRequisitionId");     data.rchrEmployeeId = vars.getStringParameter("inprchrEmployeeId");     data.rchrEmployeeIdr = vars.getStringParameter("inprchrEmployeeId_R");     data.adImageId = vars.getStringParameter("inpadImageId");     data.addfamilymembers = vars.getStringParameter("inpaddfamilymembers", "N");     data.rchrEmpDeptId = vars.getStringParameter("inprchrEmpDeptId");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.description = vars.getStringParameter("inpdescription");     data.createfromindent = vars.getStringParameter("inpcreatefromindent", "N");     data.createlines = vars.getStringParameter("inpcreatelines", "N");     data.process = vars.getStringParameter("inpprocess", "N");     data.postx = vars.getRequiredStringParameter("inppostx");     data.processed = vars.getStringParameter("inpprocessed", "N");    try {   data.totallines = vars.getNumericParameter("inptotallines");  } catch (ServletException paramEx) { ex = paramEx; }     data.complete = vars.getRequiredStringParameter("inpcomplete");     data.docstatus = vars.getStringParameter("inpdocstatus");    try {   data.presents = vars.getRequiredNumericParameter("inppresents");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.cashreceived = vars.getRequiredNumericParameter("inpcashreceived");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.payableamount = vars.getRequiredNumericParameter("inppayableamount");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totaleligibityamount = vars.getRequiredNumericParameter("inptotaleligibityamount");  } catch (ServletException paramEx) { ex = paramEx; }     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rcgiDepartmentissueId = vars.getRequestGlobalVariable("inprcgiDepartmentissueId", windowId + "|Rcgi_Departmentissue_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|Rcgi_Departmentissue_ID", data[0].getField("rcgiDepartmentissueId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data[] data = HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprcgiDepartmentissueId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRcgi_Departmentissue_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamTotallines = vars.getSessionValue(tabId + "|paramTotallines");
String strParamRchr_Employee_ID = vars.getSessionValue(tabId + "|paramRchr_Employee_ID");
String strParamTotallines_f = vars.getSessionValue(tabId + "|paramTotallines_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamTotallines) && ("").equals(strParamRchr_Employee_ID) && ("").equals(strParamTotallines_f)) || !(("").equals(strParamTotallines) || ("%").equals(strParamTotallines))  || !(("").equals(strParamRchr_Employee_ID) || ("%").equals(strParamRchr_Employee_ID))  || !(("").equals(strParamTotallines_f) || ("%").equals(strParamTotallines_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRcgi_Departmentissue_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRcgi_Departmentissue_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/DepartmentalStoreIssue/HeaderC99974CDAA7B44F1BF65BF7194CE4F55_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "HeaderC99974CDAA7B44F1BF65BF7194CE4F55", false, "document.frmMain.inprcgiDepartmentissueId", "grid", "../com.redcarpet.goodsissue/erpCommon/ad_reports/DepartmentalStoreIssuePrint.pdf", "N".equals("Y"), "DepartmentalStoreIssue", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rcgiDepartmentissueId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "HeaderC99974CDAA7B44F1BF65BF7194CE4F55_Relation.html", "DepartmentalStoreIssue", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "HeaderC99974CDAA7B44F1BF65BF7194CE4F55_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRcgi_Departmentissue_ID, TableSQLData tableSQL)
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
    HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamTotallines = vars.getSessionValue(tabId + "|paramTotallines");
String strParamRchr_Employee_ID = vars.getSessionValue(tabId + "|paramRchr_Employee_ID");
String strParamTotallines_f = vars.getSessionValue(tabId + "|paramTotallines_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamTotallines) && ("").equals(strParamRchr_Employee_ID) && ("").equals(strParamTotallines_f)) || !(("").equals(strParamTotallines) || ("%").equals(strParamTotallines))  || !(("").equals(strParamRchr_Employee_ID) || ("%").equals(strParamRchr_Employee_ID))  || !(("").equals(strParamTotallines_f) || ("%").equals(strParamTotallines_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRcgi_Departmentissue_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRcgi_Departmentissue_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rcgiDepartmentissueId") == null || dataField.getField("rcgiDepartmentissueId").equals("")) {
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
        data = HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data.set(Utility.getDefault(this, vars, "Totaleligibityamount", "0", "34B0D49ECE1A4B1AAE945A33DFA1607F", "0", dataField), Utility.getDefault(this, vars, "Process", "N", "34B0D49ECE1A4B1AAE945A33DFA1607F", "N", dataField), Utility.getDefault(this, vars, "Createdby", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data.selectDef1981BFCC95A54BF08F1A786F3E451F30_0(this, Utility.getDefault(this, vars, "Createdby", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField)), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "AD_Image_ID", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "Description", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), "Y", Utility.getDefault(this, vars, "Createlines", "N", "34B0D49ECE1A4B1AAE945A33DFA1607F", "N", dataField), Utility.getDefault(this, vars, "Rchr_Employee_ID", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "Docstatus", "DR", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "Cashreceived", "0", "34B0D49ECE1A4B1AAE945A33DFA1607F", "0", dataField), Utility.getDefault(this, vars, "Rchr_Emp_Dept_ID", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "Createfromindent", "N", "34B0D49ECE1A4B1AAE945A33DFA1607F", "N", dataField), Utility.getDefault(this, vars, "Presents", "0", "34B0D49ECE1A4B1AAE945A33DFA1607F", "0", dataField), Utility.getDefault(this, vars, "Movementdate", "@#Date@", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "Complete", "N", "34B0D49ECE1A4B1AAE945A33DFA1607F", "N", dataField), Utility.getDefault(this, vars, "Addfamilymembers", "N", "34B0D49ECE1A4B1AAE945A33DFA1607F", "N", dataField), "", Utility.getDefault(this, vars, "Updatedby", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data.selectDefB810469AAAA94E1191BBE8790F355278_1(this, Utility.getDefault(this, vars, "Updatedby", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField)), Utility.getDefault(this, vars, "Postx", "N", "34B0D49ECE1A4B1AAE945A33DFA1607F", "N", dataField), Utility.getDefault(this, vars, "C_Doctype_ID", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "M_Requisition_ID", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "Totallines", "0", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "Documentno", "", "34B0D49ECE1A4B1AAE945A33DFA1607F", "", dataField), Utility.getDefault(this, vars, "Processed", "N", "34B0D49ECE1A4B1AAE945A33DFA1607F", "N", dataField), Utility.getDefault(this, vars, "Payableamount", "0", "34B0D49ECE1A4B1AAE945A33DFA1607F", "0", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/DepartmentalStoreIssue/HeaderC99974CDAA7B44F1BF65BF7194CE4F55_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/DepartmentalStoreIssue/HeaderC99974CDAA7B44F1BF65BF7194CE4F55_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "HeaderC99974CDAA7B44F1BF65BF7194CE4F55", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprcgiDepartmentissueId", "", "../com.redcarpet.goodsissue/erpCommon/ad_reports/DepartmentalStoreIssuePrint.pdf", "N".equals("Y"), "DepartmentalStoreIssue", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRcgi_Departmentissue_ID), !hasReadOnlyAccess);
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
      // if (!strRcgi_Departmentissue_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "HeaderC99974CDAA7B44F1BF65BF7194CE4F55_Relation.html", "DepartmentalStoreIssue", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "HeaderC99974CDAA7B44F1BF65BF7194CE4F55_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "19", "C_Doctype_ID", "", "433C65658C6C4F8487C5C31B41580317", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_Doctype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Movementdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Employee_ID", "", "2ABA849EE15946C39D05AAD17022A421", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmployeeId"):dataField.getField("rchrEmployeeId")));
xmlDocument.setData("reportRchr_Employee_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
String strCurrentImageURLAD_Image_ID = (dataField==null?data[0].getField("adImageId"):dataField.getField("adImageId"));
if (strCurrentImageURLAD_Image_ID==null || strCurrentImageURLAD_Image_ID.equals("")){
  xmlDocument.setParameter("AD_Image_IDClass", "Image_NotAvailable_medium");
}

xmlDocument.setParameter("buttonTotallines", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Complete_BTNname", Utility.getButtonName(this, vars, "01186E5644E24C75B8899FF990FD3FE6", "Complete_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalComplete = org.openbravo.erpCommon.utility.Utility.isModalProcess("24C4E55795B741059C4C53C2E2D51CB3"); 
xmlDocument.setParameter("Complete_Modal", modalComplete?"true":"false");
xmlDocument.setParameter("buttonPresents", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCashreceived", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPayableamount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotaleligibityamount", Utility.messageBD(this, "Calc", vars.getLanguage()));
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



    void printPageButtonComplete24C4E55795B741059C4C53C2E2D51CB3(HttpServletResponse response, VariablesSecureApp vars, String strRcgi_Departmentissue_ID, String strcomplete, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 24C4E55795B741059C4C53C2E2D51CB3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Complete24C4E55795B741059C4C53C2E2D51CB3", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcgi_Departmentissue_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "HeaderC99974CDAA7B44F1BF65BF7194CE4F55_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "24C4E55795B741059C4C53C2E2D51CB3");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("24C4E55795B741059C4C53C2E2D51CB3");
        vars.removeMessage("24C4E55795B741059C4C53C2E2D51CB3");
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
    HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data data = null;
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
                data.rcgiDepartmentissueId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (HeaderC99974CDAA7B44F1BF65BF7194CE4F55Data.getCurrentDBTimestamp(this, data.rcgiDepartmentissueId).equals(
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
                    data.rcgiDepartmentissueId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rcgi_Departmentissue_ID", data.rcgiDepartmentissueId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet HeaderC99974CDAA7B44F1BF65BF7194CE4F55. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
