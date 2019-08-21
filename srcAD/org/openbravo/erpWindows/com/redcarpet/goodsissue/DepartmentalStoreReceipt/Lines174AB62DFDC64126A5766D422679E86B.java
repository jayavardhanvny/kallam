
package org.openbravo.erpWindows.com.redcarpet.goodsissue.DepartmentalStoreReceipt;




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

public class Lines174AB62DFDC64126A5766D422679E86B extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Lines174AB62DFDC64126A5766D422679E86B.class);
  
  private static final String windowId = "FE427DBE5FF04C5CABA4CCE1BEDE96B5";
  private static final String tabId = "174AB62DFDC64126A5766D422679E86B";
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
     
      if (command.contains("2568DC178C5541E4B6194E0C7865F8F1")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("2568DC178C5541E4B6194E0C7865F8F1");
        SessionInfo.setModuleId("C71D22E39B944C0EA8C5D2B655E53B3F");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("2568DC178C5541E4B6194E0C7865F8F1")) {
        classInfo.type = "P";
        classInfo.id = "2568DC178C5541E4B6194E0C7865F8F1";
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
        String strrcgiDrlinesId = request.getParameter("inprcgiDrlinesId");
         String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strrcgiDrlinesId.equals(""))
              total = saveRecord(vars, myError, 'U', strPRcgi_Departmentreceipt_ID);
          else
              total = saveRecord(vars, myError, 'I', strPRcgi_Departmentreceipt_ID);
          
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
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID", "");

      String strRcgi_Drlines_ID = vars.getGlobalVariable("inprcgiDrlinesId", windowId + "|Rcgi_Drlines_ID", "");
            if (strPRcgi_Departmentreceipt_ID.equals("")) {
        strPRcgi_Departmentreceipt_ID = getParentID(vars, strRcgi_Drlines_ID);
        if (strPRcgi_Departmentreceipt_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|Rcgi_Departmentreceipt_ID");
        vars.setSessionValue(windowId + "|Rcgi_Departmentreceipt_ID", strPRcgi_Departmentreceipt_ID);

        refreshParentSession(vars, strPRcgi_Departmentreceipt_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strRcgi_Drlines_ID.equals("")) strRcgi_Drlines_ID = firstElement(vars, tableSQL);
          if (strRcgi_Drlines_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcgi_Drlines_ID, strPRcgi_Departmentreceipt_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRcgi_Departmentreceipt_ID, strRcgi_Drlines_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strRcgi_Drlines_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strRcgi_Drlines_ID.equals("")) strRcgi_Drlines_ID = vars.getRequiredGlobalVariable("inprcgiDrlinesId", windowId + "|Rcgi_Drlines_ID");
      else vars.setSessionValue(windowId + "|Rcgi_Drlines_ID", strRcgi_Drlines_ID);
      
      
      String strPRcgi_Departmentreceipt_ID = getParentID(vars, strRcgi_Drlines_ID);
      
      vars.setSessionValue(windowId + "|Rcgi_Departmentreceipt_ID", strPRcgi_Departmentreceipt_ID);
      vars.setSessionValue("B28F9CEFE13D40BFA0305B3CF19DDECE|Header.view", "EDIT");

      refreshParentSession(vars, strPRcgi_Departmentreceipt_ID);

      vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.view", "EDIT");

      printPageEdit(response, request, vars, false, strRcgi_Drlines_ID, strPRcgi_Departmentreceipt_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|Rcgi_Drlines_ID");
      refreshParentSession(vars, strPRcgi_Departmentreceipt_ID);


      String strView = vars.getSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.view");
      String strRcgi_Drlines_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strRcgi_Drlines_ID = firstElement(vars, tableSQL);
          if (strRcgi_Drlines_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strRcgi_Drlines_ID.equals("")) strRcgi_Drlines_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strRcgi_Drlines_ID, strPRcgi_Departmentreceipt_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPRcgi_Departmentreceipt_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamM_Product_ID", tabId + "|paramM_Product_ID");
vars.getRequestGlobalVariable("inpParamOrderedqty", tabId + "|paramOrderedqty");
vars.getRequestGlobalVariable("inpParamOrderedqty_f", tabId + "|paramOrderedqty_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");

      
      vars.removeSessionValue(windowId + "|Rcgi_Drlines_ID");
      String strRcgi_Drlines_ID="";

      String strView = vars.getSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strRcgi_Drlines_ID = firstElement(vars, tableSQL);
        if (strRcgi_Drlines_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strRcgi_Drlines_ID, strPRcgi_Departmentreceipt_ID, tableSQL);

      else printPageDataSheet(response, vars, strPRcgi_Departmentreceipt_ID, strRcgi_Drlines_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
      

      String strRcgi_Drlines_ID = vars.getGlobalVariable("inprcgiDrlinesId", windowId + "|Rcgi_Drlines_ID", "");
      vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.view", "RELATION");
      printPageDataSheet(response, vars, strPRcgi_Departmentreceipt_ID, strRcgi_Drlines_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");


      printPageEdit(response, request, vars, true, "", strPRcgi_Departmentreceipt_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strRcgi_Drlines_ID = vars.getGlobalVariable("inprcgiDrlinesId", windowId + "|Rcgi_Drlines_ID", "");
      vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strRcgi_Drlines_ID, strPRcgi_Departmentreceipt_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
      String strRcgi_Drlines_ID = vars.getRequiredStringParameter("inprcgiDrlinesId");
      
      String strNext = nextElement(vars, strRcgi_Drlines_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPRcgi_Departmentreceipt_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
      String strRcgi_Drlines_ID = vars.getRequiredStringParameter("inprcgiDrlinesId");
      
      String strPrevious = previousElement(vars, strRcgi_Drlines_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPRcgi_Departmentreceipt_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");

      vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|Rcgi_Drlines_ID");
      vars.setSessionValue(windowId + "|Rcgi_Departmentreceipt_ID", strPRcgi_Departmentreceipt_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|Rcgi_Drlines_ID");
      vars.setSessionValue(windowId + "|Rcgi_Departmentreceipt_ID", strPRcgi_Departmentreceipt_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPRcgi_Departmentreceipt_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPRcgi_Departmentreceipt_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPRcgi_Departmentreceipt_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPRcgi_Departmentreceipt_ID);      
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
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
      String strRcgi_Drlines_ID = vars.getRequiredGlobalVariable("inprcgiDrlinesId", windowId + "|Rcgi_Drlines_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPRcgi_Departmentreceipt_ID);      
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
          String strNext = nextElement(vars, strRcgi_Drlines_ID, tableSQL);
          vars.setSessionValue(windowId + "|Rcgi_Drlines_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");

      String strRcgi_Drlines_ID = vars.getRequiredStringParameter("inprcgiDrlinesId");
      //Lines174AB62DFDC64126A5766D422679E86BData data = getEditVariables(vars, strPRcgi_Departmentreceipt_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = Lines174AB62DFDC64126A5766D422679E86BData.delete(this, strRcgi_Drlines_ID, strPRcgi_Departmentreceipt_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|rcgiDrlinesId");
        vars.setSessionValue(tabId + "|Lines174AB62DFDC64126A5766D422679E86B.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONUpdatelines2568DC178C5541E4B6194E0C7865F8F1")) {
        vars.setSessionValue("button2568DC178C5541E4B6194E0C7865F8F1.strupdatelines", vars.getStringParameter("inpupdatelines"));
        vars.setSessionValue("button2568DC178C5541E4B6194E0C7865F8F1.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button2568DC178C5541E4B6194E0C7865F8F1.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button2568DC178C5541E4B6194E0C7865F8F1.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button2568DC178C5541E4B6194E0C7865F8F1.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "2568DC178C5541E4B6194E0C7865F8F1", request.getServletPath());
      } else if (vars.commandIn("BUTTON2568DC178C5541E4B6194E0C7865F8F1")) {
        String strRcgi_Drlines_ID = vars.getGlobalVariable("inprcgiDrlinesId", windowId + "|Rcgi_Drlines_ID", "");
        String strupdatelines = vars.getSessionValue("button2568DC178C5541E4B6194E0C7865F8F1.strupdatelines");
        String strProcessing = vars.getSessionValue("button2568DC178C5541E4B6194E0C7865F8F1.strProcessing");
        String strOrg = vars.getSessionValue("button2568DC178C5541E4B6194E0C7865F8F1.strOrg");
        String strClient = vars.getSessionValue("button2568DC178C5541E4B6194E0C7865F8F1.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonUpdatelines2568DC178C5541E4B6194E0C7865F8F1(response, vars, strRcgi_Drlines_ID, strupdatelines, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONUpdatelines2568DC178C5541E4B6194E0C7865F8F1")) {
        String strRcgi_Drlines_ID = vars.getGlobalVariable("inpKey", windowId + "|Rcgi_Drlines_ID", "");
        
        ProcessBundle pb = new ProcessBundle("2568DC178C5541E4B6194E0C7865F8F1", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Rcgi_Drlines_ID", strRcgi_Drlines_ID);
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
      String strPRcgi_Departmentreceipt_ID = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPRcgi_Departmentreceipt_ID);
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
  private Lines174AB62DFDC64126A5766D422679E86BData getEditVariables(Connection con, VariablesSecureApp vars, String strPRcgi_Departmentreceipt_ID) throws IOException,ServletException {
    Lines174AB62DFDC64126A5766D422679E86BData data = new Lines174AB62DFDC64126A5766D422679E86BData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.rcgiDepartmentreceiptId = vars.getRequiredStringParameter("inprcgiDepartmentreceiptId");    try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.mProductId = vars.getStringParameter("inpmProductId");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");    try {   data.orderedqty = vars.getRequiredNumericParameter("inporderedqty");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.unitprice = vars.getNumericParameter("inpunitprice");  } catch (ServletException paramEx) { ex = paramEx; }     data.mLocatorId = vars.getStringParameter("inpmLocatorId");     data.mLocatorIdr = vars.getStringParameter("inpmLocatorId_R");     data.processing = vars.getStringParameter("inpprocessing", "N");     data.processed = vars.getStringParameter("inpprocessed", "N");    try {   data.linenetamt = vars.getRequiredNumericParameter("inplinenetamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.cUomId = vars.getRequiredStringParameter("inpcUomId");     data.cUomIdr = vars.getStringParameter("inpcUomId_R");    try {   data.movementqty = vars.getRequiredNumericParameter("inpmovementqty");  } catch (ServletException paramEx) { ex = paramEx; }     data.inventoryuom = vars.getRequiredStringParameter("inpinventoryuom");     data.financialInvoiceLine = vars.getStringParameter("inpfinancialInvoiceLine", "N");     data.accountId = vars.getStringParameter("inpaccountId");     data.updatelines = vars.getStringParameter("inpupdatelines");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.rcgiDrlinesId = vars.getRequestGlobalVariable("inprcgiDrlinesId", windowId + "|Rcgi_Drlines_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.rcgiDepartmentreceiptId = vars.getGlobalVariable("inprcgiDepartmentreceiptId", windowId + "|Rcgi_Departmentreceipt_ID");


          vars.getGlobalVariable("inpdrdocstatus", windowId + "|DRDOCSTATUS", "");
    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPRcgi_Departmentreceipt_ID) throws IOException,ServletException {
      
      HeaderB28F9CEFE13D40BFA0305B3CF19DDECEData[] data = HeaderB28F9CEFE13D40BFA0305B3CF19DDECEData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcgi_Departmentreceipt_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|Rcgi_Departmentreceipt_ID", data[0].rcgiDepartmentreceiptId);
      vars.setSessionValue(windowId + "|Rcgi_Departmentreceipt_ID", strPRcgi_Departmentreceipt_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strRcgi_Drlines_ID) throws ServletException {
    String strPRcgi_Departmentreceipt_ID = Lines174AB62DFDC64126A5766D422679E86BData.selectParentID(this, strRcgi_Drlines_ID);
    if (strPRcgi_Departmentreceipt_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strRcgi_Drlines_ID);
      throw new ServletException("Parent record not found");
    }
    return strPRcgi_Departmentreceipt_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|Rcgi_Drlines_ID", data[0].getField("rcgiDrlinesId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPRcgi_Departmentreceipt_ID) throws IOException,ServletException {
      Lines174AB62DFDC64126A5766D422679E86BData[] data = Lines174AB62DFDC64126A5766D422679E86BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcgi_Departmentreceipt_ID, vars.getStringParameter("inprcgiDrlinesId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPRcgi_Departmentreceipt_ID, String strRcgi_Drlines_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamM_Product_ID = vars.getSessionValue(tabId + "|paramM_Product_ID");
String strParamOrderedqty = vars.getSessionValue(tabId + "|paramOrderedqty");
String strParamOrderedqty_f = vars.getSessionValue(tabId + "|paramOrderedqty_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamM_Product_ID) && ("").equals(strParamOrderedqty) && ("").equals(strParamOrderedqty_f)) || !(("").equals(strParamM_Product_ID) || ("%").equals(strParamM_Product_ID))  || !(("").equals(strParamOrderedqty) || ("%").equals(strParamOrderedqty))  || !(("").equals(strParamOrderedqty_f) || ("%").equals(strParamOrderedqty_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strRcgi_Drlines_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strRcgi_Drlines_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/DepartmentalStoreReceipt/Lines174AB62DFDC64126A5766D422679E86B_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Lines174AB62DFDC64126A5766D422679E86B", false, "document.frmMain.inprcgiDrlinesId", "grid", "..", "".equals("Y"), "DepartmentalStoreReceipt", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPRcgi_Departmentreceipt_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("1CEE161A10824DF5B2E18CD244508E2D", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "rcgiDrlinesId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines174AB62DFDC64126A5766D422679E86B_Relation.html", "DepartmentalStoreReceipt", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines174AB62DFDC64126A5766D422679E86B_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", Lines174AB62DFDC64126A5766D422679E86BData.selectParent(this, strPRcgi_Departmentreceipt_ID));
    else xmlDocument.setParameter("parent", Lines174AB62DFDC64126A5766D422679E86BData.selectParentTrl(this, strPRcgi_Departmentreceipt_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strRcgi_Drlines_ID, String strPRcgi_Departmentreceipt_ID, TableSQLData tableSQL)
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
    Lines174AB62DFDC64126A5766D422679E86BData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamM_Product_ID = vars.getSessionValue(tabId + "|paramM_Product_ID");
String strParamOrderedqty = vars.getSessionValue(tabId + "|paramOrderedqty");
String strParamOrderedqty_f = vars.getSessionValue(tabId + "|paramOrderedqty_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamM_Product_ID) && ("").equals(strParamOrderedqty) && ("").equals(strParamOrderedqty_f)) || !(("").equals(strParamM_Product_ID) || ("%").equals(strParamM_Product_ID))  || !(("").equals(strParamOrderedqty) || ("%").equals(strParamOrderedqty))  || !(("").equals(strParamOrderedqty_f) || ("%").equals(strParamOrderedqty_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = Lines174AB62DFDC64126A5766D422679E86BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPRcgi_Departmentreceipt_ID, strRcgi_Drlines_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strRcgi_Drlines_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new Lines174AB62DFDC64126A5766D422679E86BData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("rcgiDrlinesId") == null || dataField.getField("rcgiDrlinesId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strDRDOCSTATUS = Lines174AB62DFDC64126A5766D422679E86BData.selectAux50EA0BEA3EA247DABC874E22BA3E2281(this, strPRcgi_Departmentreceipt_ID);
    vars.setSessionValue(windowId + "|DRDOCSTATUS", strDRDOCSTATUS);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPRcgi_Departmentreceipt_ID);
        data = Lines174AB62DFDC64126A5766D422679E86BData.set(strPRcgi_Departmentreceipt_ID, Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Utility.getDefault(this, vars, "Account_ID", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Utility.getDefault(this, vars, "Unitprice", "0", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Utility.getDefault(this, vars, "Movementqty", "0", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "0", dataField), Utility.getDefault(this, vars, "Processing", "N", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "N", dataField), "", "Y", Utility.getDefault(this, vars, "Inventoryuom", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Utility.getDefault(this, vars, "Linenetamt", "0", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "0", dataField), Utility.getDefault(this, vars, "Updatedby", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Lines174AB62DFDC64126A5766D422679E86BData.selectDefAD4A7C75877C43F08DE17BB5F28BF76B_0(this, Utility.getDefault(this, vars, "Updatedby", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField)), Utility.getDefault(this, vars, "Updatelines", "N", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "N", dataField), Utility.getDefault(this, vars, "Createdby", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Lines174AB62DFDC64126A5766D422679E86BData.selectDefB92FB61162694B03807EAF09C9994AA3_1(this, Utility.getDefault(this, vars, "Createdby", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField)), Utility.getDefault(this, vars, "Processed", "N", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "N", dataField), Utility.getDefault(this, vars, "Orderedqty", "1", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "0", dataField), Utility.getDefault(this, vars, "M_Locator_ID", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Lines174AB62DFDC64126A5766D422679E86BData.selectDefC9B14B32A28F4770BF964AB901551CBC_2(this, Utility.getDefault(this, vars, "M_Locator_ID", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField)), Utility.getDefault(this, vars, "Financial_Invoice_Line", "N", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "N", dataField), Utility.getDefault(this, vars, "C_Uom_ID", "", "FE427DBE5FF04C5CABA4CCE1BEDE96B5", "", dataField), Lines174AB62DFDC64126A5766D422679E86BData.selectDefFBC4BC8E605140F78A1B7E1DBC283208(this, strPRcgi_Departmentreceipt_ID));
        
      }
     }
      
    String currentPOrg=HeaderB28F9CEFE13D40BFA0305B3CF19DDECEData.selectOrg(this, strPRcgi_Departmentreceipt_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/DepartmentalStoreReceipt/Lines174AB62DFDC64126A5766D422679E86B_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/redcarpet/goodsissue/DepartmentalStoreReceipt/Lines174AB62DFDC64126A5766D422679E86B_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Lines174AB62DFDC64126A5766D422679E86B", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inprcgiDrlinesId", "", "..", "".equals("Y"), "DepartmentalStoreReceipt", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strRcgi_Drlines_ID), !hasReadOnlyAccess);
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
      // if (!strRcgi_Drlines_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines174AB62DFDC64126A5766D422679E86B_Relation.html", "DepartmentalStoreReceipt", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines174AB62DFDC64126A5766D422679E86B_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("DRDOCSTATUS", strDRDOCSTATUS);
    
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
comboTableData = new ComboTableData(vars, this, "19", "M_Product_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mProductId"):dataField.getField("mProductId")));
xmlDocument.setData("reportM_Product_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonOrderedqty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonUnitprice", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonLinenetamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_Uom_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomId"):dataField.getField("cUomId")));
xmlDocument.setData("reportC_Uom_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonMovementqty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Updatelines_BTNname", Utility.getButtonName(this, vars, "7E78C47F1A96460D99DA9EFA257076D1", "Updatelines_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalUpdatelines = org.openbravo.erpCommon.utility.Utility.isModalProcess("2568DC178C5541E4B6194E0C7865F8F1"); 
xmlDocument.setParameter("Updatelines_Modal", modalUpdatelines?"true":"false");
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



    void printPageButtonUpdatelines2568DC178C5541E4B6194E0C7865F8F1(HttpServletResponse response, VariablesSecureApp vars, String strRcgi_Drlines_ID, String strupdatelines, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2568DC178C5541E4B6194E0C7865F8F1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Updatelines2568DC178C5541E4B6194E0C7865F8F1", discard).createXmlDocument();
      xmlDocument.setParameter("key", strRcgi_Drlines_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Lines174AB62DFDC64126A5766D422679E86B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "2568DC178C5541E4B6194E0C7865F8F1");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("2568DC178C5541E4B6194E0C7865F8F1");
        vars.removeMessage("2568DC178C5541E4B6194E0C7865F8F1");
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
      String result = "var strad_user_id=\"" + Utility.getContext(this, vars, "#ad_user_id", windowId) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPRcgi_Departmentreceipt_ID) throws IOException, ServletException {
    Lines174AB62DFDC64126A5766D422679E86BData data = null;
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
            data = getEditVariables(con, vars, strPRcgi_Departmentreceipt_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.rcgiDrlinesId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (Lines174AB62DFDC64126A5766D422679E86BData.getCurrentDBTimestamp(this, data.rcgiDrlinesId).equals(
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
                    data.rcgiDrlinesId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|Rcgi_Drlines_ID", data.rcgiDrlinesId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Lines174AB62DFDC64126A5766D422679E86B. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
