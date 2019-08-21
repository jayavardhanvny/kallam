
package org.openbravo.erpWindows.GoodsShipment;


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

public class Lines extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Lines.class);
  
  private static final String windowId = "169";
  private static final String tabId = "258";
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
     
      if (command.contains("DAE719940FE9463F8A3E3C401BBAFC53")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("DAE719940FE9463F8A3E3C401BBAFC53");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "DAE719940FE9463F8A3E3C401BBAFC53";
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
        String strmInoutlineId = request.getParameter("inpmInoutlineId");
         String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strmInoutlineId.equals(""))
              total = saveRecord(vars, myError, 'U', strPM_InOut_ID);
          else
              total = saveRecord(vars, myError, 'I', strPM_InOut_ID);
          
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
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");

      String strM_InOutLine_ID = vars.getGlobalVariable("inpmInoutlineId", windowId + "|M_InOutLine_ID", "");
            if (strPM_InOut_ID.equals("")) {
        strPM_InOut_ID = getParentID(vars, strM_InOutLine_ID);
        if (strPM_InOut_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|M_InOut_ID");
        vars.setSessionValue(windowId + "|M_InOut_ID", strPM_InOut_ID);

        refreshParentSession(vars, strPM_InOut_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Lines.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strM_InOutLine_ID.equals("")) strM_InOutLine_ID = firstElement(vars, tableSQL);
          if (strM_InOutLine_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strM_InOutLine_ID, strPM_InOut_ID, tableSQL);

      else printPageDataSheet(response, vars, strPM_InOut_ID, strM_InOutLine_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strM_InOutLine_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strM_InOutLine_ID.equals("")) strM_InOutLine_ID = vars.getRequiredGlobalVariable("inpmInoutlineId", windowId + "|M_InOutLine_ID");
      else vars.setSessionValue(windowId + "|M_InOutLine_ID", strM_InOutLine_ID);
      
      
      String strPM_InOut_ID = getParentID(vars, strM_InOutLine_ID);
      
      vars.setSessionValue(windowId + "|M_InOut_ID", strPM_InOut_ID);
      vars.setSessionValue("257|Header.view", "EDIT");

      refreshParentSession(vars, strPM_InOut_ID);

      vars.setSessionValue(tabId + "|Lines.view", "EDIT");

      printPageEdit(response, request, vars, false, strM_InOutLine_ID, strPM_InOut_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|M_InOutLine_ID");
      refreshParentSession(vars, strPM_InOut_ID);


      String strView = vars.getSessionValue(tabId + "|Lines.view");
      String strM_InOutLine_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strM_InOutLine_ID = firstElement(vars, tableSQL);
          if (strM_InOutLine_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strM_InOutLine_ID.equals("")) strM_InOutLine_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strM_InOutLine_ID, strPM_InOut_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPM_InOut_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamLine", tabId + "|paramLine");
vars.getRequestGlobalVariable("inpParamMovementQty", tabId + "|paramMovementQty");
vars.getRequestGlobalVariable("inpParamM_InOut_ID", tabId + "|paramM_InOut_ID");
vars.getRequestGlobalVariable("inpParamM_Product_ID", tabId + "|paramM_Product_ID");
vars.getRequestGlobalVariable("inpParamLine_f", tabId + "|paramLine_f");
vars.getRequestGlobalVariable("inpParamMovementQty_f", tabId + "|paramMovementQty_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");

      
      vars.removeSessionValue(windowId + "|M_InOutLine_ID");
      String strM_InOutLine_ID="";

      String strView = vars.getSessionValue(tabId + "|Lines.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strM_InOutLine_ID = firstElement(vars, tableSQL);
        if (strM_InOutLine_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Lines.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strM_InOutLine_ID, strPM_InOut_ID, tableSQL);

      else printPageDataSheet(response, vars, strPM_InOut_ID, strM_InOutLine_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      

      String strM_InOutLine_ID = vars.getGlobalVariable("inpmInoutlineId", windowId + "|M_InOutLine_ID", "");
      vars.setSessionValue(tabId + "|Lines.view", "RELATION");
      printPageDataSheet(response, vars, strPM_InOut_ID, strM_InOutLine_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");


      printPageEdit(response, request, vars, true, "", strPM_InOut_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strM_InOutLine_ID = vars.getGlobalVariable("inpmInoutlineId", windowId + "|M_InOutLine_ID", "");
      vars.setSessionValue(tabId + "|Lines.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strM_InOutLine_ID, strPM_InOut_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      String strM_InOutLine_ID = vars.getRequiredStringParameter("inpmInoutlineId");
      
      String strNext = nextElement(vars, strM_InOutLine_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPM_InOut_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      String strM_InOutLine_ID = vars.getRequiredStringParameter("inpmInoutlineId");
      
      String strPrevious = previousElement(vars, strM_InOutLine_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPM_InOut_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");

      vars.setSessionValue(tabId + "|Lines.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Lines.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Lines.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|M_InOutLine_ID");
      vars.setSessionValue(windowId + "|M_InOut_ID", strPM_InOut_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Lines.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|M_InOutLine_ID");
      vars.setSessionValue(windowId + "|M_InOut_ID", strPM_InOut_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPM_InOut_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPM_InOut_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPM_InOut_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPM_InOut_ID);      
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
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      String strM_InOutLine_ID = vars.getRequiredGlobalVariable("inpmInoutlineId", windowId + "|M_InOutLine_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPM_InOut_ID);      
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
          String strNext = nextElement(vars, strM_InOutLine_ID, tableSQL);
          vars.setSessionValue(windowId + "|M_InOutLine_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");

      String strM_InOutLine_ID = vars.getRequiredStringParameter("inpmInoutlineId");
      //LinesData data = getEditVariables(vars, strPM_InOut_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = LinesData.delete(this, strM_InOutLine_ID, strPM_InOut_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|mInoutlineId");
        vars.setSessionValue(tabId + "|Lines.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONExplodeDAE719940FE9463F8A3E3C401BBAFC53")) {
        vars.setSessionValue("buttonDAE719940FE9463F8A3E3C401BBAFC53.strexplode", vars.getStringParameter("inpexplode"));
        vars.setSessionValue("buttonDAE719940FE9463F8A3E3C401BBAFC53.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonDAE719940FE9463F8A3E3C401BBAFC53.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonDAE719940FE9463F8A3E3C401BBAFC53.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonDAE719940FE9463F8A3E3C401BBAFC53.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "DAE719940FE9463F8A3E3C401BBAFC53", request.getServletPath());    
     } else if (vars.commandIn("BUTTONDAE719940FE9463F8A3E3C401BBAFC53")) {
        String strM_InOutLine_ID = vars.getGlobalVariable("inpmInoutlineId", windowId + "|M_InOutLine_ID", "");
        String strexplode = vars.getSessionValue("buttonDAE719940FE9463F8A3E3C401BBAFC53.strexplode");
        String strProcessing = vars.getSessionValue("buttonDAE719940FE9463F8A3E3C401BBAFC53.strProcessing");
        String strOrg = vars.getSessionValue("buttonDAE719940FE9463F8A3E3C401BBAFC53.strOrg");
        String strClient = vars.getSessionValue("buttonDAE719940FE9463F8A3E3C401BBAFC53.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonExplodeDAE719940FE9463F8A3E3C401BBAFC53(response, vars, strM_InOutLine_ID, strexplode, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONExplodeDAE719940FE9463F8A3E3C401BBAFC53")) {
        String strM_InOutLine_ID = vars.getGlobalVariable("inpKey", windowId + "|M_InOutLine_ID", "");
        @SuppressWarnings("unused")
        String strexplode = vars.getStringParameter("inpexplode");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "DAE719940FE9463F8A3E3C401BBAFC53", (("M_InOutLine_ID".equalsIgnoreCase("AD_Language"))?"0":strM_InOutLine_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
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
      String strPM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPM_InOut_ID);
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
  private LinesData getEditVariables(Connection con, VariablesSecureApp vars, String strPM_InOut_ID) throws IOException,ServletException {
    LinesData data = new LinesData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredStringParameter("inpadOrgId");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");    try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.mProductId = vars.getRequestGlobalVariable("inpmProductId", windowId + "|M_Product_ID");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");     data.epcgPackagingId = vars.getStringParameter("inpepcgPackagingId");     data.epcgPackagingIdr = vars.getStringParameter("inpepcgPackagingId_R");    try {   data.emEpcgNoofpackages = vars.getNumericParameter("inpemEpcgNoofpackages");  } catch (ServletException paramEx) { ex = paramEx; }     data.mConditionGoodsId = vars.getStringParameter("inpmConditionGoodsId");     data.mAttributesetinstanceId = vars.getStringParameter("inpmAttributesetinstanceId");     data.mAttributesetinstanceIdr = vars.getStringParameter("inpmAttributesetinstanceId_R");    try {   data.movementqty = vars.getRequiredNumericParameter("inpmovementqty", vars.getSessionValue(windowId + "|MovementQty"));  } catch (ServletException paramEx) { ex = paramEx; }     data.cUomId = vars.getRequiredGlobalVariable("inpcUomId", windowId + "|C_UOM_ID");     data.cUomIdr = vars.getStringParameter("inpcUomId_R");    try {   data.emRcfrPriceactual = vars.getRequiredNumericParameter("inpemRcfrPriceactual");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrNetrate = vars.getRequiredNumericParameter("inpemRcfrNetrate");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrNetunitrate = vars.getRequiredNumericParameter("inpemRcfrNetunitrate");  } catch (ServletException paramEx) { ex = paramEx; }     data.mLocatorId = vars.getRequestGlobalVariable("inpmLocatorId", windowId + "|M_Locator_ID");     data.mLocatorIdr = vars.getStringParameter("inpmLocatorId_R");     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");     data.description = vars.getRequestGlobalVariable("inpdescription", windowId + "|Description");     data.cOrderlineId = vars.getRequestGlobalVariable("inpcOrderlineId", windowId + "|C_OrderLine_ID");     data.cOrderlineIdr = vars.getStringParameter("inpcOrderlineId_R");     data.mProductUomId = vars.getStringParameter("inpmProductUomId");    try {   data.quantityorder = vars.getNumericParameter("inpquantityorder");  } catch (ServletException paramEx) { ex = paramEx; }     data.isdescription = vars.getStringParameter("inpisdescription", "N");     data.canceledInoutlineId = vars.getStringParameter("inpcanceledInoutlineId");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.aAssetId = vars.getStringParameter("inpaAssetId");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.explode = vars.getRequiredStringParameter("inpexplode");     data.bomParentId = vars.getStringParameter("inpbomParentId");    try {   data.emRcloNoofbales = vars.getRequiredNumericParameter("inpemRcloNoofbales");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emEpcgTarewt = vars.getNumericParameter("inpemEpcgTarewt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emEpcgGrosswt = vars.getNumericParameter("inpemEpcgGrosswt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emEpcgNetwt = vars.getNumericParameter("inpemEpcgNetwt");  } catch (ServletException paramEx) { ex = paramEx; }     data.isinvoiced = vars.getStringParameter("inpisinvoiced", "N");     data.mInoutlineId = vars.getRequestGlobalVariable("inpmInoutlineId", windowId + "|M_InOutLine_ID");     data.mInoutId = vars.getRequiredGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.managePrereservation = vars.getStringParameter("inpmanagePrereservation"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.mInoutId = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");


          vars.getGlobalVariable("inphasseconduom", windowId + "|HASSECONDUOM", "");
          vars.getGlobalVariable("inpcBpartnerId", windowId + "|C_BPARTNER_ID", "");
          vars.getGlobalVariable("inpisstocked", windowId + "|IsStocked", "");
          vars.getGlobalVariable("inpmWarehouseId", windowId + "|M_Warehouse_ID", "");
          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
          vars.getGlobalVariable("inpattrsetvaluetype", windowId + "|ATTRSETVALUETYPE", "");
          vars.getGlobalVariable("inpposted", windowId + "|Posted", "");
          vars.getGlobalVariable("inpisbom", windowId + "|isBOM", "");
          vars.getGlobalVariable("inpprocessed", windowId + "|Processed", "");
          vars.getGlobalVariable("inpattributeset", windowId + "|ATTRIBUTESET", "");
          vars.getGlobalVariable("inpparentAdOrg", windowId + "|Parent_AD_Org", "");
    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPM_InOut_ID) throws IOException,ServletException {
      
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPM_InOut_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|M_Warehouse_ID", data[0].mWarehouseId);    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].cBpartnerId);    vars.setSessionValue(windowId + "|DocStatus", data[0].docstatus);    vars.setSessionValue(windowId + "|Posted", data[0].posted);    vars.setSessionValue(windowId + "|Freight_Currency_ID", data[0].freightCurrencyId);    vars.setSessionValue(windowId + "|IsSOTrx", data[0].issotrx);    vars.setSessionValue(windowId + "|M_InOut_ID", data[0].mInoutId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|M_InOut_ID", strPM_InOut_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|ISLOGISTICAUX", "N");
      
      vars.setSessionValue(windowId + "|DOCBASETYPE", HeaderData.selectAux3E769128EA274A8480EBB341E05468AF(this, Utility.getContext(this, vars, "c_doctype_id", "169")));
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strM_InOutLine_ID) throws ServletException {
    String strPM_InOut_ID = LinesData.selectParentID(this, strM_InOutLine_ID);
    if (strPM_InOut_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strM_InOutLine_ID);
      throw new ServletException("Parent record not found");
    }
    return strPM_InOut_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|M_Product_ID", data[0].getField("mProductId"));    vars.setSessionValue(windowId + "|MovementQty", data[0].getField("movementqty"));    vars.setSessionValue(windowId + "|C_UOM_ID", data[0].getField("cUomId"));    vars.setSessionValue(windowId + "|M_Locator_ID", data[0].getField("mLocatorId"));    vars.setSessionValue(windowId + "|Description", data[0].getField("description"));    vars.setSessionValue(windowId + "|C_OrderLine_ID", data[0].getField("cOrderlineId"));    vars.setSessionValue(windowId + "|M_InOutLine_ID", data[0].getField("mInoutlineId"));    vars.setSessionValue(windowId + "|M_InOut_ID", data[0].getField("mInoutId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPM_InOut_ID) throws IOException,ServletException {
      LinesData[] data = LinesData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPM_InOut_ID, vars.getStringParameter("inpmInoutlineId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPM_InOut_ID, String strM_InOutLine_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamMovementQty = vars.getSessionValue(tabId + "|paramMovementQty");
String strParamM_InOut_ID = vars.getSessionValue(tabId + "|paramM_InOut_ID");
String strParamM_Product_ID = vars.getSessionValue(tabId + "|paramM_Product_ID");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");
String strParamMovementQty_f = vars.getSessionValue(tabId + "|paramMovementQty_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamLine) && ("").equals(strParamMovementQty) && ("").equals(strParamM_InOut_ID) && ("").equals(strParamM_Product_ID) && ("").equals(strParamLine_f) && ("").equals(strParamMovementQty_f)) || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamMovementQty) || ("%").equals(strParamMovementQty))  || !(("").equals(strParamM_InOut_ID) || ("%").equals(strParamM_InOut_ID))  || !(("").equals(strParamM_Product_ID) || ("%").equals(strParamM_Product_ID))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f))  || !(("").equals(strParamMovementQty_f) || ("%").equals(strParamMovementQty_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strM_InOutLine_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strM_InOutLine_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GoodsShipment/Lines_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Lines", false, "document.frmMain.inpmInoutlineId", "grid", "..", "".equals("Y"), "GoodsShipment", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPM_InOut_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("2715", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "mInoutlineId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines_Relation.html", "GoodsShipment", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", LinesData.selectParent(this, strPM_InOut_ID));
    else xmlDocument.setParameter("parent", LinesData.selectParentTrl(this, strPM_InOut_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strM_InOutLine_ID, String strPM_InOut_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " M_InOutLine.QuantityOrder, M_InOutLine.Line";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    LinesData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamMovementQty = vars.getSessionValue(tabId + "|paramMovementQty");
String strParamM_InOut_ID = vars.getSessionValue(tabId + "|paramM_InOut_ID");
String strParamM_Product_ID = vars.getSessionValue(tabId + "|paramM_Product_ID");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");
String strParamMovementQty_f = vars.getSessionValue(tabId + "|paramMovementQty_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamLine) && ("").equals(strParamMovementQty) && ("").equals(strParamM_InOut_ID) && ("").equals(strParamM_Product_ID) && ("").equals(strParamLine_f) && ("").equals(strParamMovementQty_f)) || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamMovementQty) || ("%").equals(strParamMovementQty))  || !(("").equals(strParamM_InOut_ID) || ("%").equals(strParamM_InOut_ID))  || !(("").equals(strParamM_Product_ID) || ("%").equals(strParamM_Product_ID))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f))  || !(("").equals(strParamMovementQty_f) || ("%").equals(strParamMovementQty_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = LinesData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPM_InOut_ID, strM_InOutLine_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strM_InOutLine_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new LinesData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("mInoutlineId") == null || dataField.getField("mInoutlineId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strHASSECONDUOM = LinesData.selectAux800009(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|HASSECONDUOM", strHASSECONDUOM);
        String strC_BPARTNER_ID = ((dataField!=null)?dataField.getField("cBpartnerId"):((data==null || data.length==0)?"":data[0].cBpartnerId));
    vars.setSessionValue(windowId + "|C_BPARTNER_ID", strC_BPARTNER_ID);
        String strIsStocked = LinesData.selectAux1FC6075EA994422F8CF3B041621F374D(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|IsStocked", strIsStocked);
        String strM_Warehouse_ID = LinesData.selectAux3BC05D242CF749FBAC016CAF3071EAB0(this, strPM_InOut_ID);
    vars.setSessionValue(windowId + "|M_Warehouse_ID", strM_Warehouse_ID);
        String strDOCBASETYPE = LinesData.selectAux3C85410D6A9549C99BC60258CBD809BD(this, strPM_InOut_ID);
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
        String strATTRSETVALUETYPE = LinesData.selectAux8EE1AE595E1E462BBFE568C0C27BEAC0(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|ATTRSETVALUETYPE", strATTRSETVALUETYPE);
        String strPosted = LinesData.selectAuxA9B8EA3C15E14EFB901131BF4DFFF687(this, strPM_InOut_ID);
    vars.setSessionValue(windowId + "|Posted", strPosted);
        String strisBOM = LinesData.selectAuxAA79289FD0CB4B69BFB31FC8A8C076EB(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|isBOM", strisBOM);
        String strProcessed = LinesData.selectAuxB4A64414F61A4901A24A5B968FEE309A(this, strPM_InOut_ID);
    vars.setSessionValue(windowId + "|Processed", strProcessed);
        String strATTRIBUTESET = LinesData.selectAuxDE561A006F4247DFA68E13C955437EF5(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|ATTRIBUTESET", strATTRIBUTESET);
        String strParent_AD_Org = LinesData.selectAuxE5295F791A5840A2B28868ED6A7634D3(this, strPM_InOut_ID);
    vars.setSessionValue(windowId + "|Parent_AD_Org", strParent_AD_Org);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPM_InOut_ID);
        data = LinesData.set(strPM_InOut_ID, Utility.getDefault(this, vars, "EM_Epcg_Noofpackages", "", "169", "", dataField), Utility.getDefault(this, vars, "A_Asset_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Tarewt", "", "169", "", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_Client_ID@", "169", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "169", "", dataField), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "169", "", dataField), LinesData.selectDef3534_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "169", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "169", "", dataField), LinesData.selectDef3536_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "169", "", dataField)), LinesData.selectDef3537(this, Utility.getContext(this, vars, "AD_Org_ID", "169"), Utility.getContext(this, vars, "#AD_Client_ID", windowId), Utility.getContext(this, vars, "M_WAREHOUSE_ID", "169")), LinesData.selectDef3537_2(this, LinesData.selectDef3537(this, Utility.getContext(this, vars, "AD_Org_ID", "169"), Utility.getContext(this, vars, "#AD_Client_ID", windowId), Utility.getContext(this, vars, "M_WAREHOUSE_ID", "169"))), Utility.getDefault(this, vars, "M_Product_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "MovementQty", "1", "169", "0", dataField), Utility.getDefault(this, vars, "Description", "", "169", "", dataField), Utility.getDefault(this, vars, "M_Condition_Goods_ID", "", "169", "", dataField), LinesData.selectDef3810(this, strPM_InOut_ID), Utility.getDefault(this, vars, "C_OrderLine_ID", "", "169", "", dataField), LinesData.selectDef3811_3(this, Utility.getDefault(this, vars, "C_OrderLine_ID", "", "169", "", dataField)), Utility.getDefault(this, vars, "C_UOM_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "EM_Rclo_Noofbales", "0", "169", "0", dataField), Utility.getDefault(this, vars, "IsInvoiced", "", "169", "N", dataField), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Netwt", "", "169", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Netrate", "", "169", "0", dataField), Utility.getDefault(this, vars, "Explode", "N", "169", "N", dataField), Utility.getDefault(this, vars, "M_Product_Uom_Id", "", "169", "", dataField), Utility.getDefault(this, vars, "QuantityOrder", "", "169", "", dataField), Utility.getDefault(this, vars, "M_AttributeSetInstance_ID", "", "169", "", dataField), LinesData.selectDef8772_4(this, Utility.getDefault(this, vars, "M_AttributeSetInstance_ID", "", "169", "", dataField)), Utility.getDefault(this, vars, "IsDescription", "N", "169", "N", dataField), Utility.getDefault(this, vars, "User2_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "Canceled_Inoutline_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Grosswt", "", "169", "", dataField), Utility.getDefault(this, vars, "Manage_Prereservation", "", "169", "N", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Netunitrate", "", "169", "0", dataField), Utility.getDefault(this, vars, "BOM_Parent_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "169", "", dataField), LinesData.selectDefF12044E4570546E18AF6834B00961F8E_5(this, Utility.getDefault(this, vars, "C_Project_ID", "", "169", "", dataField)), Utility.getDefault(this, vars, "EM_Rcfr_priceactual", "", "169", "0", dataField), Utility.getDefault(this, vars, "Epcg_Packaging_ID", "", "169", "", dataField));
        
      }
     }
      
    String currentPOrg=HeaderData.selectOrg(this, strPM_InOut_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GoodsShipment/Lines_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GoodsShipment/Lines_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Lines", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpmInoutlineId", "", "..", "".equals("Y"), "GoodsShipment", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strM_InOutLine_ID), !hasReadOnlyAccess);
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
      // if (!strM_InOutLine_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines_Relation.html", "GoodsShipment", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("HASSECONDUOM", strHASSECONDUOM);
        xmlDocument.setParameter("C_BPARTNER_ID", strC_BPARTNER_ID);
        xmlDocument.setParameter("IsStocked", strIsStocked);
        xmlDocument.setParameter("M_Warehouse_ID", strM_Warehouse_ID);
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
        xmlDocument.setParameter("ATTRSETVALUETYPE", strATTRSETVALUETYPE);
        xmlDocument.setParameter("Posted", strPosted);
        xmlDocument.setParameter("isBOM", strisBOM);
        xmlDocument.setParameter("Processed", strProcessed);
        xmlDocument.setParameter("ATTRIBUTESET", strATTRIBUTESET);
        xmlDocument.setParameter("Parent_AD_Org", strParent_AD_Org);
    
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
String userOrgList = "";
if (editableTab) 
  userOrgList= Utility.getReferenceableOrg(this, vars, currentPOrg, windowId, accesslevel); //referenceable from parent org, only the writeable orgs
else 
  userOrgList=currentOrg;
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "53AE60A473D2460D8663D7A1BC5BA732", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "M_Product_ID", "171", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mProductId"):dataField.getField("mProductId")));
xmlDocument.setData("reportM_Product_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "Epcg_Packaging_ID", "8CEADB212B5441A0B392E9D3498E9B4D", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("epcgPackagingId"):dataField.getField("epcgPackagingId")));
xmlDocument.setData("reportEpcg_Packaging_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonEM_Epcg_Noofpackages", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonMovementQty", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_UOM_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomId"):dataField.getField("cUomId")));
xmlDocument.setData("reportC_UOM_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonEM_Rcfr_priceactual", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Netrate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Netunitrate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQuantityOrder", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Explode_BTNname", Utility.getButtonName(this, vars, "515C368DC0E049E6B66878FC064D561B", "Explode_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalExplode = org.openbravo.erpCommon.utility.Utility.isModalProcess("DAE719940FE9463F8A3E3C401BBAFC53"); 
xmlDocument.setParameter("Explode_Modal", modalExplode?"true":"false");
xmlDocument.setParameter("buttonEM_Rclo_Noofbales", Utility.messageBD(this, "Calc", vars.getLanguage()));
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

    private void printPageButtonExplodeDAE719940FE9463F8A3E3C401BBAFC53(HttpServletResponse response, VariablesSecureApp vars, String strM_InOutLine_ID, String strexplode, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DAE719940FE9463F8A3E3C401BBAFC53");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ExplodeDAE719940FE9463F8A3E3C401BBAFC53", discard).createXmlDocument();
      xmlDocument.setParameter("key", strM_InOutLine_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Lines_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "DAE719940FE9463F8A3E3C401BBAFC53");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("DAE719940FE9463F8A3E3C401BBAFC53");
        vars.removeMessage("DAE719940FE9463F8A3E3C401BBAFC53");
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
      String result = "var strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar str$Element_AS=\"" +Utility.getContext(this, vars, "$Element_AS", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPM_InOut_ID) throws IOException, ServletException {
    LinesData data = null;
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
            data = getEditVariables(con, vars, strPM_InOut_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.mInoutlineId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (LinesData.getCurrentDBTimestamp(this, data.mInoutlineId).equals(
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
                    data.mInoutlineId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|M_InOutLine_ID", data.mInoutlineId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Lines. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
