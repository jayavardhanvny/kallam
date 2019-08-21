
package org.openbravo.erpWindows.com.rcss.humanresource.EmployeeRecords;




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

public class EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.class);
  
  private static final String windowId = "0D37288BA71746F7BA8318A1020360F2";
  private static final String tabId = "BCE3FD6947754152A50FFD057C2E47C2";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "BAD4B9EE21444679A85DAB2F8624F1BB";
  
  @Override
  public void init(ServletConfig config) {
    setClassInfo("W", tabId, moduleId);
    super.init(config);
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
        String strrchrEmployeeId = request.getParameter("inprchrEmployeeId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrEmployeeId.equals(""))
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

      String strRchr_Employee_ID = vars.getGlobalVariable("inprchrEmployeeId", windowId + "|Rchr_Employee_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Employee_ID.equals("")) strRchr_Employee_ID = firstElement(vars, tableSQL);
          if (strRchr_Employee_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Employee_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Employee_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Employee_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Employee_ID.equals("")) strRchr_Employee_ID = vars.getRequiredGlobalVariable("inprchrEmployeeId", windowId + "|Rchr_Employee_ID");
      else vars.setSessionValue(windowId + "|Rchr_Employee_ID", strRchr_Employee_ID);
      
      vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Employee_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.view");
      String strRchr_Employee_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Employee_ID = firstElement(vars, tableSQL);
          if (strRchr_Employee_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Employee_ID.equals("")) strRchr_Employee_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Employee_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamPunchno", tabId + "|paramPunchno");
vars.getRequestGlobalVariable("inpParamEmployeename", tabId + "|paramEmployeename");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|Rchr_Employee_ID");
      String strRchr_Employee_ID="";

      String strView = vars.getSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Employee_ID = firstElement(vars, tableSQL);
        if (strRchr_Employee_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Employee_ID, tableSQL);

      else printPageDataSheet(response, vars, strRchr_Employee_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strRchr_Employee_ID = vars.getGlobalVariable("inprchrEmployeeId", windowId + "|Rchr_Employee_ID", "");
      vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.view", "RELATION");
      printPageDataSheet(response, vars, strRchr_Employee_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Employee_ID = vars.getGlobalVariable("inprchrEmployeeId", windowId + "|Rchr_Employee_ID", "");
      vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Employee_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strRchr_Employee_ID = vars.getRequiredStringParameter("inprchrEmployeeId");
      
      String strNext = nextElement(vars, strRchr_Employee_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strRchr_Employee_ID = vars.getRequiredStringParameter("inprchrEmployeeId");
      
      String strPrevious = previousElement(vars, strRchr_Employee_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Employee_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Employee_ID");

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

      String strRchr_Employee_ID = vars.getRequiredGlobalVariable("inprchrEmployeeId", windowId + "|Rchr_Employee_ID");
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
          String strNext = nextElement(vars, strRchr_Employee_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Employee_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strRchr_Employee_ID = vars.getRequiredStringParameter("inprchrEmployeeId");
      //EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data.delete(this, strRchr_Employee_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrEmployeeId");
        vars.setSessionValue(tabId + "|EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());








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
  private EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data data = new EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequestGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.employeestatus = vars.getStringParameter("inpemployeestatus");     data.employeestatusr = vars.getStringParameter("inpemployeestatus_R");     data.salarystatus = vars.getStringParameter("inpsalarystatus");     data.salarystatusr = vars.getStringParameter("inpsalarystatus_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.withheld = vars.getStringParameter("inpwithheld", "N");     data.employeename = vars.getRequiredStringParameter("inpemployeename");     data.punchno = vars.getRequiredStringParameter("inppunchno");     data.firstname = vars.getStringParameter("inpfirstname");     data.lastname = vars.getStringParameter("inplastname");     data.surname = vars.getStringParameter("inpsurname");     data.gender = vars.getRequiredStringParameter("inpgender");     data.genderr = vars.getStringParameter("inpgender_R");     data.maritalstatus = vars.getRequiredStringParameter("inpmaritalstatus");     data.maritalstatusr = vars.getStringParameter("inpmaritalstatus_R");     data.rchrEmpDeptId = vars.getStringParameter("inprchrEmpDeptId");     data.rchrEmpDeptIdr = vars.getStringParameter("inprchrEmpDeptId_R");     data.landlineno = vars.getStringParameter("inplandlineno");     data.rchrSubdepartmentId = vars.getStringParameter("inprchrSubdepartmentId");     data.rchrSubdepartmentIdr = vars.getStringParameter("inprchrSubdepartmentId_R");     data.rchrDesignationId = vars.getStringParameter("inprchrDesignationId");     data.rchrDesignationIdr = vars.getStringParameter("inprchrDesignationId_R");     data.emergencyphoneno = vars.getStringParameter("inpemergencyphoneno");     data.supervisorname = vars.getStringParameter("inpsupervisorname");     data.supervisornamer = vars.getStringParameter("inpsupervisorname_R");     data.rchrShiftgroupId = vars.getStringParameter("inprchrShiftgroupId");     data.rchrShiftgroupIdr = vars.getStringParameter("inprchrShiftgroupId_R");     data.rchrMrelayId = vars.getStringParameter("inprchrMrelayId");     data.rchrMrelayIdr = vars.getStringParameter("inprchrMrelayId_R");     data.jdate = vars.getRequiredStringParameter("inpjdate");     data.jobtype = vars.getStringParameter("inpjobtype");     data.employeetype = vars.getRequiredStringParameter("inpemployeetype");     data.employeetyper = vars.getStringParameter("inpemployeetype_R");     data.dob = vars.getStringParameter("inpdob");     data.shift = vars.getStringParameter("inpshift", "N");     data.weekoffapplicable = vars.getStringParameter("inpweekoffapplicable", "N");     data.rchrAgentmasterId = vars.getStringParameter("inprchrAgentmasterId");     data.weeklyoff = vars.getRequiredStringParameter("inpweeklyoff");     data.weeklyoffr = vars.getStringParameter("inpweeklyoff_R");     data.isallotment = vars.getStringParameter("inpisallotment", "N");     data.adImageId = vars.getStringParameter("inpadImageId");     data.rchrRoomId = vars.getStringParameter("inprchrRoomId");     data.rchrRoomIdr = vars.getStringParameter("inprchrRoomId_R");    try {   data.preattddays = vars.getNumericParameter("inppreattddays");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.actualservicedays = vars.getNumericParameter("inpactualservicedays");  } catch (ServletException paramEx) { ex = paramEx; }     data.ispfapplicable = vars.getStringParameter("inpispfapplicable", "N");     data.panno = vars.getStringParameter("inppanno");     data.pftype = vars.getStringParameter("inppftype");     data.pftyper = vars.getStringParameter("inppftype_R");     data.epfno = vars.getStringParameter("inpepfno");     data.isesiapplicable = vars.getStringParameter("inpisesiapplicable", "N");     data.esino = vars.getStringParameter("inpesino");     data.isbank = vars.getStringParameter("inpisbank", "N");     data.adharno = vars.getStringParameter("inpadharno");     data.accountno = vars.getStringParameter("inpaccountno");     data.rotation = vars.getStringParameter("inprotation");     data.isleaveapplicable = vars.getStringParameter("inpisleaveapplicable", "N");     data.iscoffapplicable = vars.getStringParameter("inpiscoffapplicable", "N");     data.isodapplicable = vars.getStringParameter("inpisodapplicable", "N");     data.licapplicable = vars.getStringParameter("inplicapplicable", "N");    try {   data.vpfamount = vars.getNumericParameter("inpvpfamount");  } catch (ServletException paramEx) { ex = paramEx; }     data.halfdayapplicable = vars.getStringParameter("inphalfdayapplicable", "N");     data.rchrMobalizerId = vars.getStringParameter("inprchrMobalizerId");     data.rchrMobalizerIdr = vars.getStringParameter("inprchrMobalizerId_R");    try {   data.observicedays = vars.getNumericParameter("inpobservicedays");  } catch (ServletException paramEx) { ex = paramEx; }     data.email = vars.getStringParameter("inpemail");     data.phoneno = vars.getStringParameter("inpphoneno");     data.vpf = vars.getStringParameter("inpvpf", "N");     data.rchrLeavetemplateId = vars.getStringParameter("inprchrLeavetemplateId");     data.rchrLeavetemplateIdr = vars.getStringParameter("inprchrLeavetemplateId_R");     data.adUserId = vars.getStringParameter("inpadUserId");     data.adUserIdr = vars.getStringParameter("inpadUserId_R");     data.leavedepartment = vars.getStringParameter("inpleavedepartment");     data.leavedepartmentr = vars.getStringParameter("inpleavedepartment_R");     data.rchrJoinRejoiningformId = vars.getStringParameter("inprchrJoinRejoiningformId");     data.rchrJoinRejoiningformIdr = vars.getStringParameter("inprchrJoinRejoiningformId_R");     data.senior = vars.getStringParameter("inpsenior", "N");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.adClientId = vars.getRequestGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rchrEmployeeId = vars.getRequestGlobalVariable("inprchrEmployeeId", windowId + "|Rchr_Employee_ID"); 
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|Rchr_Employee_ID", data[0].getField("rchrEmployeeId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data[] data = EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inprchrEmployeeId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Employee_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamPunchno = vars.getSessionValue(tabId + "|paramPunchno");
String strParamEmployeename = vars.getSessionValue(tabId + "|paramEmployeename");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamPunchno) && ("").equals(strParamEmployeename)) || !(("").equals(strParamPunchno) || ("%").equals(strParamPunchno))  || !(("").equals(strParamEmployeename) || ("%").equals(strParamEmployeename)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Employee_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Employee_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/EmployeeRecords/EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2", false, "document.frmMain.inprchrEmployeeId", "grid", "../com.rcss.humanresource/ad_forms/EmployeeInformationPreview.html", "N".equals("Y"), "EmployeeRecords", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "rchrEmployeeId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2_Relation.html", "EmployeeRecords", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Employee_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " 1";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamPunchno = vars.getSessionValue(tabId + "|paramPunchno");
String strParamEmployeename = vars.getSessionValue(tabId + "|paramEmployeename");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamPunchno) && ("").equals(strParamEmployeename)) || !(("").equals(strParamPunchno) || ("%").equals(strParamPunchno))  || !(("").equals(strParamEmployeename) || ("%").equals(strParamEmployeename)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strRchr_Employee_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Employee_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrEmployeeId") == null || dataField.getField("rchrEmployeeId").equals("")) {
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
        data = EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data.set(Utility.getDefault(this, vars, "Weeklyoff", "N/A", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Jdate", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Firstname", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Observicedays", "0", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Jobtype", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Rchr_Shiftgroup_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Documentno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Maritalstatus", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Rchr_Join_Rejoiningform_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Landlineno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Isallotment", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Withheld", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Actualservicedays", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Dob", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "AD_Image_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Epfno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Punchno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Emergencyphoneno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Rotation", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Rchr_Mrelay_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Licapplicable", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Rchr_Mobalizer_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Employeetype", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Isesiapplicable", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Preattddays", "0", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Supervisorname", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), "Y", Utility.getDefault(this, vars, "Phoneno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Rchr_Designation_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Employeename", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Leavedepartment", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Salarystatus", "AP", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Weekoffapplicable", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Esino", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data.selectDefA3992BD9A1E14B038036F92D1899EA90_0(this, Utility.getDefault(this, vars, "Updatedby", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField)), Utility.getDefault(this, vars, "Employeestatus", "W", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data.selectDefAE1C0BBE0F294F17B905CE375F852EDF_1(this, Utility.getDefault(this, vars, "Createdby", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField)), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Lastname", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Pftype", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Accountno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Ispfapplicable", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Gender", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Adharno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), "", Utility.getDefault(this, vars, "Rchr_Leavetemplate_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Rchr_Agentmaster_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Email", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Halfdayapplicable", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Panno", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "AD_User_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Vpf", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Iscoffapplicable", "", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Isbank", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Rchr_Emp_Dept_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Isodapplicable", "", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Shift", "", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Surname", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Senior", "N", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Isleaveapplicable", "", "0D37288BA71746F7BA8318A1020360F2", "N", dataField), Utility.getDefault(this, vars, "Rchr_Subdepartment_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Rchr_Room_ID", "", "0D37288BA71746F7BA8318A1020360F2", "", dataField), Utility.getDefault(this, vars, "Vpfamount", "0", "0D37288BA71746F7BA8318A1020360F2", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/EmployeeRecords/EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/EmployeeRecords/EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrEmployeeId", "", "../com.rcss.humanresource/ad_forms/EmployeeInformationPreview.html", "N".equals("Y"), "EmployeeRecords", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Employee_ID), !hasReadOnlyAccess);
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
      // if (!strRchr_Employee_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2_Relation.html", "EmployeeRecords", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "17", "Employeestatus", "FC14213FA54E428AA6108273EF9E05DC", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("employeestatus"):dataField.getField("employeestatus")));
xmlDocument.setData("reportEmployeestatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Salarystatus", "131", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("salarystatus"):dataField.getField("salarystatus")));
xmlDocument.setData("reportSalarystatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Gender", "B8F4E6135F39438AB324C66053F51662", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("gender"):dataField.getField("gender")));
xmlDocument.setData("reportGender","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Maritalstatus", "E34F72895CB8445199EA344EB9C6B0D4", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("maritalstatus"):dataField.getField("maritalstatus")));
xmlDocument.setData("reportMaritalstatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Emp_Dept_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrEmpDeptId"):dataField.getField("rchrEmpDeptId")));
xmlDocument.setData("reportRchr_Emp_Dept_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Subdepartment_ID", "", "3F4DC7D9F58C406F90B09C83519BB20D", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrSubdepartmentId"):dataField.getField("rchrSubdepartmentId")));
xmlDocument.setData("reportRchr_Subdepartment_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Designation_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrDesignationId"):dataField.getField("rchrDesignationId")));
xmlDocument.setData("reportRchr_Designation_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Supervisorname", "8515E8B6F915487EAC5500A964A9801D", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("supervisorname"):dataField.getField("supervisorname")));
xmlDocument.setData("reportSupervisorname","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Shiftgroup_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrShiftgroupId"):dataField.getField("rchrShiftgroupId")));
xmlDocument.setData("reportRchr_Shiftgroup_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Mrelay_ID", "", "3EFAACFD0A08401E98D7A813635BC097", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrMrelayId"):dataField.getField("rchrMrelayId")));
xmlDocument.setData("reportRchr_Mrelay_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Jdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "17", "Employeetype", "60AC4DA6E1D54452BDC42FF3387DC68C", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("employeetype"):dataField.getField("employeetype")));
xmlDocument.setData("reportEmployeetype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Dob_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "17", "Weeklyoff", "A39B727AF2784C809BCF4AD5DED5C3DC", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("weeklyoff"):dataField.getField("weeklyoff")));
xmlDocument.setData("reportWeeklyoff","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
String strCurrentImageURLAD_Image_ID = (dataField==null?data[0].getField("adImageId"):dataField.getField("adImageId"));
if (strCurrentImageURLAD_Image_ID==null || strCurrentImageURLAD_Image_ID.equals("")){
  xmlDocument.setParameter("AD_Image_IDClass", "Image_NotAvailable_medium");
}

comboTableData = new ComboTableData(vars, this, "19", "Rchr_Room_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrRoomId"):dataField.getField("rchrRoomId")));
xmlDocument.setData("reportRchr_Room_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonActualservicedays", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "17", "Pftype", "B3D302F1AC1349EDADEA70D09AE29AC4", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("pftype"):dataField.getField("pftype")));
xmlDocument.setData("reportPftype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonVpfamount", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Mobalizer_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrMobalizerId"):dataField.getField("rchrMobalizerId")));
xmlDocument.setData("reportRchr_Mobalizer_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Leavetemplate_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrLeavetemplateId"):dataField.getField("rchrLeavetemplateId")));
xmlDocument.setData("reportRchr_Leavetemplate_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "AD_User_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adUserId"):dataField.getField("adUserId")));
xmlDocument.setData("reportAD_User_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Leavedepartment", "2CAB2644B21E473388F9D6F6A426F662", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("leavedepartment"):dataField.getField("leavedepartment")));
xmlDocument.setData("reportLeavedepartment","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Join_Rejoiningform_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrJoinRejoiningformId"):dataField.getField("rchrJoinRejoiningformId")));
xmlDocument.setData("reportRchr_Join_Rejoiningform_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ServletException(ex);
    }

    xmlDocument.setParameter("scriptOnLoad", getShortcutScript(usedButtonShortCuts));
    
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





    private String getDisplayLogicContext(VariablesSecureApp vars, boolean isNew) throws IOException, ServletException {
      log4j.debug("Output: Display logic context fields");
      String result = "var strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
      return result;
    }


    private String getReadOnlyLogicContext(VariablesSecureApp vars) throws IOException, ServletException {
      log4j.debug("Output: Read Only logic context fields");
      String result = "var strad_user_id=\"" + Utility.getContext(this, vars, "#ad_user_id", windowId) + "\";\n";
      return result;
    }




 
  private String getShortcutScript( HashMap<String, String> usedButtonShortCuts){
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
    EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data data = null;
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
                data.rchrEmployeeId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2Data.getCurrentDBTimestamp(this, data.rchrEmployeeId).equals(
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
                    data.rchrEmployeeId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Employee_ID", data.rchrEmployeeId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet EmployeeRecordsBCE3FD6947754152A50FFD057C2E47C2. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
