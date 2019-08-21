
package org.openbravo.erpWindows.com.rcss.humanresource.LeaveRequisition;




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

public class Lines2C84B0324AF74954A3632C432384FE77 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Lines2C84B0324AF74954A3632C432384FE77.class);
  
  private static final String windowId = "359F1AC6D7504282B2094A3424ABC68D";
  private static final String tabId = "2C84B0324AF74954A3632C432384FE77";
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
     
      if (command.contains("B9F945AFAC0A4FEBBF5B0DCF6420C70B")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("B9F945AFAC0A4FEBBF5B0DCF6420C70B");
        SessionInfo.setModuleId("BAD4B9EE21444679A85DAB2F8624F1BB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("B9F945AFAC0A4FEBBF5B0DCF6420C70B")) {
        classInfo.type = "P";
        classInfo.id = "B9F945AFAC0A4FEBBF5B0DCF6420C70B";
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
        String strrchrLeaverequisitionlineId = request.getParameter("inprchrLeaverequisitionlineId");
         String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrchrLeaverequisitionlineId.equals(""))
              total = saveRecord(vars, myError, 'U', strPRchr_Leaverequisition_ID);
          else
              total = saveRecord(vars, myError, 'I', strPRchr_Leaverequisition_ID);
          
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
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", "");

      String strRchr_Leaverequisitionline_ID = vars.getGlobalVariable("inprchrLeaverequisitionlineId", windowId + "|Rchr_Leaverequisitionline_ID", "");
            if (strPRchr_Leaverequisition_ID.equals("")) {
        strPRchr_Leaverequisition_ID = getParentID(vars, strRchr_Leaverequisitionline_ID);
        if (strPRchr_Leaverequisition_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|Rchr_Leaverequisition_ID");
        vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", strPRchr_Leaverequisition_ID);

        refreshParentSession(vars, strPRchr_Leaverequisition_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRchr_Leaverequisitionline_ID.equals("")) strRchr_Leaverequisitionline_ID = firstElement(vars, tableSQL);
          if (strRchr_Leaverequisitionline_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Leaverequisitionline_ID, strPRchr_Leaverequisition_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRchr_Leaverequisition_ID, strRchr_Leaverequisitionline_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRchr_Leaverequisitionline_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRchr_Leaverequisitionline_ID.equals("")) strRchr_Leaverequisitionline_ID = vars.getRequiredGlobalVariable("inprchrLeaverequisitionlineId", windowId + "|Rchr_Leaverequisitionline_ID");
      else vars.setSessionValue(windowId + "|Rchr_Leaverequisitionline_ID", strRchr_Leaverequisitionline_ID);
      
      
      String strPRchr_Leaverequisition_ID = getParentID(vars, strRchr_Leaverequisitionline_ID);
      
      vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", strPRchr_Leaverequisition_ID);
      vars.setSessionValue("B6508E279C3E4C749D4F914C28DCFF56|Leave Requisition.view", "EDIT");

      refreshParentSession(vars, strPRchr_Leaverequisition_ID);

      vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.view", "EDIT");

      printPageEdit(response, request, vars, false, strRchr_Leaverequisitionline_ID, strPRchr_Leaverequisition_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|Rchr_Leaverequisitionline_ID");
      refreshParentSession(vars, strPRchr_Leaverequisition_ID);


      String strView = vars.getSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.view");
      String strRchr_Leaverequisitionline_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRchr_Leaverequisitionline_ID = firstElement(vars, tableSQL);
          if (strRchr_Leaverequisitionline_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRchr_Leaverequisitionline_ID.equals("")) strRchr_Leaverequisitionline_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRchr_Leaverequisitionline_ID, strPRchr_Leaverequisition_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPRchr_Leaverequisition_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamRchr_Leavetype_ID", tabId + "|paramRchr_Leavetype_ID");
vars.getRequestGlobalVariable("inpParamLeavedate", tabId + "|paramLeavedate");
vars.getRequestGlobalVariable("inpParamLeavedate_f", tabId + "|paramLeavedate_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");

      
      vars.removeSessionValue(windowId + "|Rchr_Leaverequisitionline_ID");
      String strRchr_Leaverequisitionline_ID="";

      String strView = vars.getSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRchr_Leaverequisitionline_ID = firstElement(vars, tableSQL);
        if (strRchr_Leaverequisitionline_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRchr_Leaverequisitionline_ID, strPRchr_Leaverequisition_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRchr_Leaverequisition_ID, strRchr_Leaverequisitionline_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      

      String strRchr_Leaverequisitionline_ID = vars.getGlobalVariable("inprchrLeaverequisitionlineId", windowId + "|Rchr_Leaverequisitionline_ID", "");
      vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.view", "RELATION");
      printPageDataSheet(response, vars, strPRchr_Leaverequisition_ID, strRchr_Leaverequisitionline_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");


      printPageEdit(response, request, vars, true, "", strPRchr_Leaverequisition_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRchr_Leaverequisitionline_ID = vars.getGlobalVariable("inprchrLeaverequisitionlineId", windowId + "|Rchr_Leaverequisitionline_ID", "");
      vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRchr_Leaverequisitionline_ID, strPRchr_Leaverequisition_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      String strRchr_Leaverequisitionline_ID = vars.getRequiredStringParameter("inprchrLeaverequisitionlineId");
      
      String strNext = nextElement(vars, strRchr_Leaverequisitionline_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPRchr_Leaverequisition_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      String strRchr_Leaverequisitionline_ID = vars.getRequiredStringParameter("inprchrLeaverequisitionlineId");
      
      String strPrevious = previousElement(vars, strRchr_Leaverequisitionline_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPRchr_Leaverequisition_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");

      vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rchr_Leaverequisitionline_ID");
      vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", strPRchr_Leaverequisition_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rchr_Leaverequisitionline_ID");
      vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", strPRchr_Leaverequisition_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPRchr_Leaverequisition_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPRchr_Leaverequisition_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPRchr_Leaverequisition_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPRchr_Leaverequisition_ID);      
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
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      String strRchr_Leaverequisitionline_ID = vars.getRequiredGlobalVariable("inprchrLeaverequisitionlineId", windowId + "|Rchr_Leaverequisitionline_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPRchr_Leaverequisition_ID);      
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
          String strNext = nextElement(vars, strRchr_Leaverequisitionline_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rchr_Leaverequisitionline_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");

      String strRchr_Leaverequisitionline_ID = vars.getRequiredStringParameter("inprchrLeaverequisitionlineId");
      //Lines2C84B0324AF74954A3632C432384FE77Data data = getEditVariables(vars, strPRchr_Leaverequisition_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = Lines2C84B0324AF74954A3632C432384FE77Data.delete(this, strRchr_Leaverequisitionline_ID, strPRchr_Leaverequisition_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rchrLeaverequisitionlineId");
        vars.setSessionValue(tabId + "|Lines2C84B0324AF74954A3632C432384FE77.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONCancelB9F945AFAC0A4FEBBF5B0DCF6420C70B")) {
        vars.setSessionValue("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.strcancel", vars.getStringParameter("inpcancel"));
        vars.setSessionValue("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "B9F945AFAC0A4FEBBF5B0DCF6420C70B", request.getServletPath());
      } else if (vars.commandIn("BUTTONB9F945AFAC0A4FEBBF5B0DCF6420C70B")) {
        String strRchr_Leaverequisitionline_ID = vars.getGlobalVariable("inprchrLeaverequisitionlineId", windowId + "|Rchr_Leaverequisitionline_ID", "");
        String strcancel = vars.getSessionValue("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.strcancel");
        String strProcessing = vars.getSessionValue("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.strProcessing");
        String strOrg = vars.getSessionValue("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.strOrg");
        String strClient = vars.getSessionValue("buttonB9F945AFAC0A4FEBBF5B0DCF6420C70B.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCancelB9F945AFAC0A4FEBBF5B0DCF6420C70B(response, vars, strRchr_Leaverequisitionline_ID, strcancel, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONCancelB9F945AFAC0A4FEBBF5B0DCF6420C70B")) {
        String strRchr_Leaverequisitionline_ID = vars.getGlobalVariable("inpKey", windowId + "|Rchr_Leaverequisitionline_ID", "");
        
        ProcessBundle pb = new ProcessBundle("B9F945AFAC0A4FEBBF5B0DCF6420C70B", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rchr_Leaverequisitionline_ID", strRchr_Leaverequisitionline_ID);
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
      String strPRchr_Leaverequisition_ID = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPRchr_Leaverequisition_ID);
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
  private Lines2C84B0324AF74954A3632C432384FE77Data getEditVariables(Connection con, VariablesSecureApp vars, String strPRchr_Leaverequisition_ID) throws IOException,ServletException {
    Lines2C84B0324AF74954A3632C432384FE77Data data = new Lines2C84B0324AF74954A3632C432384FE77Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rchrLeaverequisitionId = vars.getRequiredStringParameter("inprchrLeaverequisitionId");     data.rchrLeavetypeId = vars.getStringParameter("inprchrLeavetypeId");     data.rchrLeavetypeIdr = vars.getStringParameter("inprchrLeavetypeId_R");     data.leavedate = vars.getRequiredStringParameter("inpleavedate");     data.rchrCompensateoffId = vars.getStringParameter("inprchrCompensateoffId");     data.cancel = vars.getRequiredStringParameter("inpcancel");     data.iscanceled = vars.getStringParameter("inpiscanceled", "N");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rchrLeaverequisitionlineId = vars.getRequestGlobalVariable("inprchrLeaverequisitionlineId", windowId + "|Rchr_Leaverequisitionline_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.rchrLeaverequisitionId = vars.getGlobalVariable("inprchrLeaverequisitionId", windowId + "|Rchr_Leaverequisition_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPRchr_Leaverequisition_ID) throws IOException,ServletException {
      
      LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data[] data = LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Leaverequisition_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|DocStatus", data[0].docstatus);    vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", data[0].rchrLeaverequisitionId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|Rchr_Leaverequisition_ID", strPRchr_Leaverequisition_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strRchr_Leaverequisitionline_ID) throws ServletException {
    String strPRchr_Leaverequisition_ID = Lines2C84B0324AF74954A3632C432384FE77Data.selectParentID(this, strRchr_Leaverequisitionline_ID);
    if (strPRchr_Leaverequisition_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strRchr_Leaverequisitionline_ID);
      throw new ServletException("Parent record not found");
    }
    return strPRchr_Leaverequisition_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Rchr_Leaverequisitionline_ID", data[0].getField("rchrLeaverequisitionlineId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPRchr_Leaverequisition_ID) throws IOException,ServletException {
      Lines2C84B0324AF74954A3632C432384FE77Data[] data = Lines2C84B0324AF74954A3632C432384FE77Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Leaverequisition_ID, vars.getStringParameter("inprchrLeaverequisitionlineId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPRchr_Leaverequisition_ID, String strRchr_Leaverequisitionline_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamRchr_Leavetype_ID = vars.getSessionValue(tabId + "|paramRchr_Leavetype_ID");
String strParamLeavedate = vars.getSessionValue(tabId + "|paramLeavedate");
String strParamLeavedate_f = vars.getSessionValue(tabId + "|paramLeavedate_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Leavetype_ID) && ("").equals(strParamLeavedate) && ("").equals(strParamLeavedate_f)) || !(("").equals(strParamRchr_Leavetype_ID) || ("%").equals(strParamRchr_Leavetype_ID))  || !(("").equals(strParamLeavedate) || ("%").equals(strParamLeavedate))  || !(("").equals(strParamLeavedate_f) || ("%").equals(strParamLeavedate_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRchr_Leaverequisitionline_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRchr_Leaverequisitionline_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LeaveRequisition/Lines2C84B0324AF74954A3632C432384FE77_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Lines2C84B0324AF74954A3632C432384FE77", false, "document.frmMain.inprchrLeaverequisitionlineId", "grid", "..", "".equals("Y"), "LeaveRequisition", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), true, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPRchr_Leaverequisition_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("A187F2EEB77F4149BD7816446C3AF965", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "rchrLeaverequisitionlineId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines2C84B0324AF74954A3632C432384FE77_Relation.html", "LeaveRequisition", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines2C84B0324AF74954A3632C432384FE77_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", Lines2C84B0324AF74954A3632C432384FE77Data.selectParent(this, strPRchr_Leaverequisition_ID));
    else xmlDocument.setParameter("parent", Lines2C84B0324AF74954A3632C432384FE77Data.selectParentTrl(this, strPRchr_Leaverequisition_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRchr_Leaverequisitionline_ID, String strPRchr_Leaverequisition_ID, TableSQLData tableSQL)
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
    Lines2C84B0324AF74954A3632C432384FE77Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamRchr_Leavetype_ID = vars.getSessionValue(tabId + "|paramRchr_Leavetype_ID");
String strParamLeavedate = vars.getSessionValue(tabId + "|paramLeavedate");
String strParamLeavedate_f = vars.getSessionValue(tabId + "|paramLeavedate_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamRchr_Leavetype_ID) && ("").equals(strParamLeavedate) && ("").equals(strParamLeavedate_f)) || !(("").equals(strParamRchr_Leavetype_ID) || ("%").equals(strParamRchr_Leavetype_ID))  || !(("").equals(strParamLeavedate) || ("%").equals(strParamLeavedate))  || !(("").equals(strParamLeavedate_f) || ("%").equals(strParamLeavedate_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = Lines2C84B0324AF74954A3632C432384FE77Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Leaverequisition_ID, strRchr_Leaverequisitionline_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRchr_Leaverequisitionline_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (data==null || data.length==0) {
        strRchr_Leaverequisitionline_ID = firstElement(vars, tableSQL);
        if (strRchr_Leaverequisitionline_ID.equals("")) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        } else {
          data = Lines2C84B0324AF74954A3632C432384FE77Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRchr_Leaverequisition_ID, strRchr_Leaverequisitionline_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
        }
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new Lines2C84B0324AF74954A3632C432384FE77Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rchrLeaverequisitionlineId") == null || dataField.getField("rchrLeaverequisitionlineId").equals("")) {
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
        refreshSessionNew(vars, strPRchr_Leaverequisition_ID);
        data = Lines2C84B0324AF74954A3632C432384FE77Data.set(strPRchr_Leaverequisition_ID, Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Iscanceled", "N", "359F1AC6D7504282B2094A3424ABC68D", "N", dataField), Utility.getDefault(this, vars, "Rchr_Compensateoff_ID", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Lines2C84B0324AF74954A3632C432384FE77Data.selectDef47764AE130CC496B90B53C7C56A6DC21_0(this, Utility.getDefault(this, vars, "Createdby", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField)), "", Utility.getDefault(this, vars, "Updatedby", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Lines2C84B0324AF74954A3632C432384FE77Data.selectDefB92085EB3E09479A99400AE9AA5FEE07_1(this, Utility.getDefault(this, vars, "Updatedby", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField)), Utility.getDefault(this, vars, "Cancel", "N", "359F1AC6D7504282B2094A3424ABC68D", "N", dataField), "Y", Utility.getDefault(this, vars, "Rchr_Leavetype_ID", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField), Utility.getDefault(this, vars, "Leavedate", "", "359F1AC6D7504282B2094A3424ABC68D", "", dataField));
        
      }
     }
      
    String currentPOrg=LeaveRequisitionB6508E279C3E4C749D4F914C28DCFF56Data.selectOrg(this, strPRchr_Leaverequisition_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LeaveRequisition/Lines2C84B0324AF74954A3632C432384FE77_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/rcss/humanresource/LeaveRequisition/Lines2C84B0324AF74954A3632C432384FE77_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Lines2C84B0324AF74954A3632C432384FE77", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprchrLeaverequisitionlineId", "", "..", "".equals("Y"), "LeaveRequisition", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRchr_Leaverequisitionline_ID), !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    toolbar.setDeleteable(true);
    toolbar.prepareEditionTemplate("N".equals("Y"), hasSearchCondition, vars.getSessionValue("#ShowTest", "N").equals("Y"), "RO", Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
      // if (!strRchr_Leaverequisitionline_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines2C84B0324AF74954A3632C432384FE77_Relation.html", "LeaveRequisition", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines2C84B0324AF74954A3632C432384FE77_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
    
    xmlDocument.setParameter("parentOrg", currentPOrg);
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
comboTableData = new ComboTableData(vars, this, "19", "Rchr_Leavetype_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("rchrLeavetypeId"):dataField.getField("rchrLeavetypeId")));
xmlDocument.setData("reportRchr_Leavetype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Leavedate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Cancel_BTNname", Utility.getButtonName(this, vars, "F857901DDE4840ADA2D576E09BC2C0A0", "Cancel_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCancel = org.openbravo.erpCommon.utility.Utility.isModalProcess("B9F945AFAC0A4FEBBF5B0DCF6420C70B"); 
xmlDocument.setParameter("Cancel_Modal", modalCancel?"true":"false");
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



    void printPageButtonCancelB9F945AFAC0A4FEBBF5B0DCF6420C70B(HttpServletResponse response, VariablesSecureApp vars, String strRchr_Leaverequisitionline_ID, String strcancel, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B9F945AFAC0A4FEBBF5B0DCF6420C70B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/CancelB9F945AFAC0A4FEBBF5B0DCF6420C70B", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRchr_Leaverequisitionline_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Lines2C84B0324AF74954A3632C432384FE77_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "B9F945AFAC0A4FEBBF5B0DCF6420C70B");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("B9F945AFAC0A4FEBBF5B0DCF6420C70B");
        vars.removeMessage("B9F945AFAC0A4FEBBF5B0DCF6420C70B");
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
      String result = "var strDocstatus=\"" +Utility.getContext(this, vars, "Docstatus", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPRchr_Leaverequisition_ID) throws IOException, ServletException {
    Lines2C84B0324AF74954A3632C432384FE77Data data = null;
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
            data = getEditVariables(con, vars, strPRchr_Leaverequisition_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.rchrLeaverequisitionlineId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (Lines2C84B0324AF74954A3632C432384FE77Data.getCurrentDBTimestamp(this, data.rchrLeaverequisitionlineId).equals(
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
                    data.rchrLeaverequisitionlineId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rchr_Leaverequisitionline_ID", data.rchrLeaverequisitionlineId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Lines2C84B0324AF74954A3632C432384FE77. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
