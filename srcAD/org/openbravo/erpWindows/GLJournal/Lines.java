
package org.openbravo.erpWindows.GLJournal;




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
  
  private static final String windowId = "132";
  private static final String tabId = "161";
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
     
      if (command.contains("DE1B382FDD2540199D223586F6E216D0")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("DE1B382FDD2540199D223586F6E216D0");
        SessionInfo.setModuleId("A918E3331C404B889D69AA9BFAFB23AC");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("DE1B382FDD2540199D223586F6E216D0")) {
        classInfo.type = "P";
        classInfo.id = "DE1B382FDD2540199D223586F6E216D0";
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
        String strglJournallineId = request.getParameter("inpglJournallineId");
         String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strglJournallineId.equals(""))
              total = saveRecord(vars, myError, 'U', strPGL_Journal_ID);
          else
              total = saveRecord(vars, myError, 'I', strPGL_Journal_ID);
          
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
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID", "");

      String strGL_JournalLine_ID = vars.getGlobalVariable("inpglJournallineId", windowId + "|GL_JournalLine_ID", "");
            if (strPGL_Journal_ID.equals("")) {
        strPGL_Journal_ID = getParentID(vars, strGL_JournalLine_ID);
        if (strPGL_Journal_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|GL_Journal_ID");
        vars.setSessionValue(windowId + "|GL_Journal_ID", strPGL_Journal_ID);
      vars.removeSessionValue(windowId + "|GL_JournalBatch_ID");
        refreshParentSession(vars, strPGL_Journal_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Lines.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strGL_JournalLine_ID.equals("")) strGL_JournalLine_ID = firstElement(vars, tableSQL);
          if (strGL_JournalLine_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strGL_JournalLine_ID, strPGL_Journal_ID, tableSQL);

      else printPageDataSheet(response, vars, strPGL_Journal_ID, strGL_JournalLine_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strGL_JournalLine_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strGL_JournalLine_ID.equals("")) strGL_JournalLine_ID = vars.getRequiredGlobalVariable("inpglJournallineId", windowId + "|GL_JournalLine_ID");
      else vars.setSessionValue(windowId + "|GL_JournalLine_ID", strGL_JournalLine_ID);
      
      
      String strPGL_Journal_ID = getParentID(vars, strGL_JournalLine_ID);
      
      vars.setSessionValue(windowId + "|GL_Journal_ID", strPGL_Journal_ID);
      vars.setSessionValue("160|Header.view", "EDIT");
      vars.removeSessionValue(windowId + "|GL_JournalBatch_ID");
      refreshParentSession(vars, strPGL_Journal_ID);

      vars.setSessionValue(tabId + "|Lines.view", "EDIT");

      printPageEdit(response, request, vars, false, strGL_JournalLine_ID, strPGL_Journal_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|GL_JournalLine_ID");
      refreshParentSession(vars, strPGL_Journal_ID);


      String strView = vars.getSessionValue(tabId + "|Lines.view");
      String strGL_JournalLine_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strGL_JournalLine_ID = firstElement(vars, tableSQL);
          if (strGL_JournalLine_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strGL_JournalLine_ID.equals("")) strGL_JournalLine_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strGL_JournalLine_ID, strPGL_Journal_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPGL_Journal_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamLine", tabId + "|paramLine");
vars.getRequestGlobalVariable("inpParamLine_f", tabId + "|paramLine_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");

      
      vars.removeSessionValue(windowId + "|GL_JournalLine_ID");
      String strGL_JournalLine_ID="";

      String strView = vars.getSessionValue(tabId + "|Lines.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strGL_JournalLine_ID = firstElement(vars, tableSQL);
        if (strGL_JournalLine_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Lines.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strGL_JournalLine_ID, strPGL_Journal_ID, tableSQL);

      else printPageDataSheet(response, vars, strPGL_Journal_ID, strGL_JournalLine_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      

      String strGL_JournalLine_ID = vars.getGlobalVariable("inpglJournallineId", windowId + "|GL_JournalLine_ID", "");
      vars.setSessionValue(tabId + "|Lines.view", "RELATION");
      printPageDataSheet(response, vars, strPGL_Journal_ID, strGL_JournalLine_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");


      printPageEdit(response, request, vars, true, "", strPGL_Journal_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strGL_JournalLine_ID = vars.getGlobalVariable("inpglJournallineId", windowId + "|GL_JournalLine_ID", "");
      vars.setSessionValue(tabId + "|Lines.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strGL_JournalLine_ID, strPGL_Journal_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      String strGL_JournalLine_ID = vars.getRequiredStringParameter("inpglJournallineId");
      
      String strNext = nextElement(vars, strGL_JournalLine_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPGL_Journal_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      String strGL_JournalLine_ID = vars.getRequiredStringParameter("inpglJournallineId");
      
      String strPrevious = previousElement(vars, strGL_JournalLine_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPGL_Journal_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");

      vars.setSessionValue(tabId + "|Lines.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");

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
      vars.removeSessionValue(windowId + "|GL_JournalLine_ID");
      vars.setSessionValue(windowId + "|GL_Journal_ID", strPGL_Journal_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Lines.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|GL_JournalLine_ID");
      vars.setSessionValue(windowId + "|GL_Journal_ID", strPGL_Journal_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPGL_Journal_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPGL_Journal_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPGL_Journal_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPGL_Journal_ID);      
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
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      String strGL_JournalLine_ID = vars.getRequiredGlobalVariable("inpglJournallineId", windowId + "|GL_JournalLine_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPGL_Journal_ID);      
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
          String strNext = nextElement(vars, strGL_JournalLine_ID, tableSQL);
          vars.setSessionValue(windowId + "|GL_JournalLine_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");

      String strGL_JournalLine_ID = vars.getRequiredStringParameter("inpglJournallineId");
      //LinesData data = getEditVariables(vars, strPGL_Journal_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = LinesData.delete(this, strGL_JournalLine_ID, strPGL_Journal_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|glJournallineId");
        vars.setSessionValue(tabId + "|Lines.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONEM_Aprm_AddpaymentDE1B382FDD2540199D223586F6E216D0")) {
        vars.setSessionValue("buttonDE1B382FDD2540199D223586F6E216D0.stremAprmAddpayment", vars.getStringParameter("inpemAprmAddpayment"));
        vars.setSessionValue("buttonDE1B382FDD2540199D223586F6E216D0.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonDE1B382FDD2540199D223586F6E216D0.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonDE1B382FDD2540199D223586F6E216D0.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonDE1B382FDD2540199D223586F6E216D0.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "DE1B382FDD2540199D223586F6E216D0", request.getServletPath());
      } else if (vars.commandIn("BUTTONDE1B382FDD2540199D223586F6E216D0")) {
        String strGL_JournalLine_ID = vars.getGlobalVariable("inpglJournallineId", windowId + "|GL_JournalLine_ID", "");
        String stremAprmAddpayment = vars.getSessionValue("buttonDE1B382FDD2540199D223586F6E216D0.stremAprmAddpayment");
        String strProcessing = vars.getSessionValue("buttonDE1B382FDD2540199D223586F6E216D0.strProcessing");
        String strOrg = vars.getSessionValue("buttonDE1B382FDD2540199D223586F6E216D0.strOrg");
        String strClient = vars.getSessionValue("buttonDE1B382FDD2540199D223586F6E216D0.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Aprm_AddpaymentDE1B382FDD2540199D223586F6E216D0(response, vars, strGL_JournalLine_ID, stremAprmAddpayment, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_Aprm_AddpaymentDE1B382FDD2540199D223586F6E216D0")) {
        String strGL_JournalLine_ID = vars.getGlobalVariable("inpKey", windowId + "|GL_JournalLine_ID", "");
        
        ProcessBundle pb = new ProcessBundle("DE1B382FDD2540199D223586F6E216D0", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("GL_JournalLine_ID", strGL_JournalLine_ID);
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
      String strPGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPGL_Journal_ID);
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
  private LinesData getEditVariables(Connection con, VariablesSecureApp vars, String strPGL_Journal_ID) throws IOException,ServletException {
    LinesData data = new LinesData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredStringParameter("inpadOrgId");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");    try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.cValidcombinationId = vars.getRequiredStringParameter("inpcValidcombinationId");     data.description = vars.getStringParameter("inpdescription");     data.cCurrencyId = vars.getRequiredGlobalVariable("inpcCurrencyId", windowId + "|C_Currency_ID");    try {   data.amtsourcedr = vars.getRequiredNumericParameter("inpamtsourcedr");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.amtsourcecr = vars.getRequiredNumericParameter("inpamtsourcecr");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.amtacctdr = vars.getRequiredNumericParameter("inpamtacctdr", vars.getSessionValue(windowId + "|AmtAcctDr"));  } catch (ServletException paramEx) { ex = paramEx; }     data.isgenerated = vars.getStringParameter("inpisgenerated", "N");    try {   data.amtacctcr = vars.getRequiredNumericParameter("inpamtacctcr", vars.getSessionValue(windowId + "|AmtAcctCr"));  } catch (ServletException paramEx) { ex = paramEx; }     data.currencyratetype = vars.getRequiredGlobalVariable("inpcurrencyratetype", windowId + "|CurrencyRateType");    try {   data.currencyrate = vars.getRequiredNumericParameter("inpcurrencyrate", vars.getSessionValue(windowId + "|CurrencyRate"));  } catch (ServletException paramEx) { ex = paramEx; }     data.openItems = vars.getStringParameter("inpopenItems", "N");     data.finFinancialAccountId = vars.getRequestGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");     data.finFinancialAccountIdr = vars.getStringParameter("inpfinFinancialAccountId_R");     data.finPaymentmethodId = vars.getRequestGlobalVariable("inpfinPaymentmethodId", windowId + "|FIN_Paymentmethod_ID");     data.finPaymentmethodIdr = vars.getStringParameter("inpfinPaymentmethodId_R");     data.cGlitemId = vars.getRequestGlobalVariable("inpcGlitemId", windowId + "|C_Glitem_ID");     data.cGlitemIdr = vars.getStringParameter("inpcGlitemId_R");     data.paymentdate = vars.getStringParameter("inppaymentdate");     data.finPaymentId = vars.getRequestGlobalVariable("inpfinPaymentId", windowId + "|FIN_Payment_ID");     data.cUomId = vars.getStringParameter("inpcUomId");    try {   data.qty = vars.getNumericParameter("inpqty");  } catch (ServletException paramEx) { ex = paramEx; }     data.cDebtPaymentId = vars.getStringParameter("inpcDebtPaymentId");     data.cWithholdingId = vars.getStringParameter("inpcWithholdingId");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.cTaxId = vars.getStringParameter("inpcTaxId");     data.emAprmAddpayment = vars.getStringParameter("inpemAprmAddpayment");     data.cBpartnerId = vars.getRequestGlobalVariable("inpcBpartnerId", windowId + "|C_Bpartner_ID");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.mProductId = vars.getStringParameter("inpmProductId");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cActivityId = vars.getStringParameter("inpcActivityId");     data.cCampaignId = vars.getStringParameter("inpcCampaignId");     data.cSalesregionId = vars.getStringParameter("inpcSalesregionId");     data.cSalesregionIdr = vars.getStringParameter("inpcSalesregionId_R");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.aAssetId = vars.getStringParameter("inpaAssetId");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.dateacct = vars.getRequiredGlobalVariable("inpdateacct", windowId + "|DateAcct");     data.glJournalId = vars.getRequiredGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.glJournallineId = vars.getRequestGlobalVariable("inpglJournallineId", windowId + "|GL_JournalLine_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.glJournalId = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");


          vars.getGlobalVariable("inpdescription1", windowId + "|DESCRIPTION1", "");
          vars.getGlobalVariable("inpcurrency", windowId + "|currency", "");
          vars.getGlobalVariable("inpprocessed", windowId + "|Processed", "");
          vars.getGlobalVariable("inpposted", windowId + "|Posted", "");
          vars.getGlobalVariable("inpnumberofacctschemas", windowId + "|NumberOfAcctSchemas", "");
          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPGL_Journal_ID) throws IOException,ServletException {
      
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getSessionValue(windowId + "|GL_JournalBatch_ID"), strPGL_Journal_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|C_AcctSchema_ID", data[0].cAcctschemaId);    vars.setSessionValue(windowId + "|C_DocType_ID", data[0].cDoctypeId);    vars.setSessionValue(windowId + "|DateDoc", data[0].datedoc);    vars.setSessionValue(windowId + "|DateAcct", data[0].dateacct);    vars.setSessionValue(windowId + "|C_Period_ID", data[0].cPeriodId);    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].cCurrencyId);    vars.setSessionValue(windowId + "|CurrencyRateType", data[0].currencyratetype);    vars.setSessionValue(windowId + "|CurrencyRate", data[0].currencyrate);    vars.setSessionValue(windowId + "|Posted", data[0].posted);    vars.setSessionValue(windowId + "|GL_Category_ID", data[0].glCategoryId);    vars.setSessionValue(windowId + "|GL_JournalBatch_ID", data[0].glJournalbatchId);    vars.setSessionValue(windowId + "|GL_Journal_ID", data[0].glJournalId);    vars.setSessionValue(windowId + "|Processed", data[0].processed);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|GL_Journal_ID", strPGL_Journal_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|DESCRIPTION1", HeaderData.selectAux12(this, Utility.getContext(this, vars, "GL_JOURNALBATCH_ID", "132")));
      
      vars.setSessionValue(windowId + "|batchCurrency", HeaderData.selectAuxC382CB93EB254FC68DB7D25B34A5A6F0(this, Utility.getContext(this, vars, "gl_journalbatch_id", "132")));
      
      vars.setSessionValue(windowId + "|DOCBASETYPE", "GLJ");
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strGL_JournalLine_ID) throws ServletException {
    String strPGL_Journal_ID = LinesData.selectParentID(this, strGL_JournalLine_ID);
    if (strPGL_Journal_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strGL_JournalLine_ID);
      throw new ServletException("Parent record not found");
    }
    return strPGL_Journal_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|C_Currency_ID", data[0].getField("cCurrencyId"));    vars.setSessionValue(windowId + "|AmtAcctDr", data[0].getField("amtacctdr"));    vars.setSessionValue(windowId + "|AmtAcctCr", data[0].getField("amtacctcr"));    vars.setSessionValue(windowId + "|CurrencyRateType", data[0].getField("currencyratetype"));    vars.setSessionValue(windowId + "|CurrencyRate", data[0].getField("currencyrate"));    vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", data[0].getField("finFinancialAccountId"));    vars.setSessionValue(windowId + "|Fin_Paymentmethod_ID", data[0].getField("finPaymentmethodId"));    vars.setSessionValue(windowId + "|C_Glitem_ID", data[0].getField("cGlitemId"));    vars.setSessionValue(windowId + "|Fin_Payment_ID", data[0].getField("finPaymentId"));    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].getField("cBpartnerId"));    vars.setSessionValue(windowId + "|DateAcct", data[0].getField("dateacct"));    vars.setSessionValue(windowId + "|GL_Journal_ID", data[0].getField("glJournalId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|GL_JournalLine_ID", data[0].getField("glJournallineId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPGL_Journal_ID) throws IOException,ServletException {
      LinesData[] data = LinesData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPGL_Journal_ID, vars.getStringParameter("inpglJournallineId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPGL_Journal_ID, String strGL_JournalLine_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamLine) && ("").equals(strParamLine_f)) || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strGL_JournalLine_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strGL_JournalLine_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GLJournal/Lines_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Lines", false, "document.frmMain.inpglJournallineId", "grid", "..", "".equals("Y"), "GLJournal", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPGL_Journal_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("716", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "glJournallineId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines_Relation.html", "GLJournal", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", LinesData.selectParent(this, strPGL_Journal_ID));
    else xmlDocument.setParameter("parent", LinesData.selectParentTrl(this, strPGL_Journal_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strGL_JournalLine_ID, String strPGL_Journal_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " GL_JournalLine.Line";
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
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamLine) && ("").equals(strParamLine_f)) || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = LinesData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPGL_Journal_ID, strGL_JournalLine_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strGL_JournalLine_ID.equals("") && (data == null || data.length==0)) {
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
      if (dataField.getField("glJournallineId") == null || dataField.getField("glJournallineId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strDESCRIPTION1 = LinesData.selectAux800044(this, strPGL_Journal_ID);
    vars.setSessionValue(windowId + "|DESCRIPTION1", strDESCRIPTION1);
        String strcurrency = LinesData.selectAux3398FFC56C9D48689C928F45C7A3745A(this, strPGL_Journal_ID);
    vars.setSessionValue(windowId + "|currency", strcurrency);
        String strProcessed = LinesData.selectAux5BAAD3A90DD249B1ACCFAD81E03144C3(this, strPGL_Journal_ID);
    vars.setSessionValue(windowId + "|Processed", strProcessed);
        String strPosted = LinesData.selectAux67A43C8696ED48EEA6A36426DC073193(this, strPGL_Journal_ID);
    vars.setSessionValue(windowId + "|Posted", strPosted);
        String strNumberOfAcctSchemas = LinesData.selectAux8482E2A2C9D74EA592CE8AF25AA9364C(this, ((dataField!=null)?dataField.getField("adOrgId"):((data==null || data.length==0)?"":data[0].adOrgId)));
    vars.setSessionValue(windowId + "|NumberOfAcctSchemas", strNumberOfAcctSchemas);
        String strDOCBASETYPE = "GLJ";
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPGL_Journal_ID);
        data = LinesData.set(strPGL_Journal_ID, "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "132", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "132", "", dataField), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "132", "", dataField), LinesData.selectDef1664_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "132", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "132", "", dataField), LinesData.selectDef1666_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "132", "", dataField)), LinesData.selectDef1675(this, strPGL_Journal_ID), Utility.getDefault(this, vars, "Description", "@DESCRIPTION1@", "132", "", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "@currency@", "132", "", dataField), Utility.getDefault(this, vars, "AmtSourceDr", "", "132", "0", dataField), Utility.getDefault(this, vars, "AmtSourceCr", "", "132", "0", dataField), Utility.getDefault(this, vars, "AmtAcctDr", "", "132", "0", dataField), Utility.getDefault(this, vars, "AmtAcctCr", "", "132", "0", dataField), Utility.getDefault(this, vars, "IsGenerated", "", "132", "N", dataField), Utility.getDefault(this, vars, "C_Withholding_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Tax_ID", "\" \"", "132", "", dataField), Utility.getDefault(this, vars, "CurrencyRate", "1", "132", "0", dataField), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "CurrencyRateType", "S", "132", "", dataField), Utility.getDefault(this, vars, "DateAcct", "@DateAcct@", "132", "", dataField), Utility.getDefault(this, vars, "C_UOM_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "Qty", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Debt_Payment_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_ValidCombination_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "Open_Items", "N", "132", "N", dataField), Utility.getDefault(this, vars, "C_Glitem_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Campaign_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "132", "", dataField), LinesData.selectDefA7E134B04D5ED632E040007F010037B3_2(this, Utility.getDefault(this, vars, "C_Project_ID", "", "132", "", dataField)), Utility.getDefault(this, vars, "C_Activity_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Salesregion_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "132", "", dataField), LinesData.selectDefA7F1531510454244E040007F010064A6_3(this, Utility.getDefault(this, vars, "C_Bpartner_ID", "", "132", "", dataField)), Utility.getDefault(this, vars, "FIN_Payment_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "Paymentdate", "", "132", "", dataField), Utility.getDefault(this, vars, "EM_Aprm_Addpayment", "Y", "132", "N", dataField), Utility.getDefault(this, vars, "FIN_Financial_Account_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "A_Asset_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "FIN_Paymentmethod_ID", "", "132", "", dataField));
        
      }
     }
      
    String currentPOrg=HeaderData.selectOrg(this, strPGL_Journal_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GLJournal/Lines_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GLJournal/Lines_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Lines", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpglJournallineId", "", "..", "".equals("Y"), "GLJournal", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strGL_JournalLine_ID), !hasReadOnlyAccess);
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
      // if (!strGL_JournalLine_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines_Relation.html", "GLJournal", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("DESCRIPTION1", strDESCRIPTION1);
        xmlDocument.setParameter("currency", strcurrency);
        xmlDocument.setParameter("Processed", strProcessed);
        xmlDocument.setParameter("Posted", strPosted);
        xmlDocument.setParameter("NumberOfAcctSchemas", strNumberOfAcctSchemas);
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
    
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
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "425D5A5259F64FDABC82896596D23A25", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonAmtSourceDr", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonAmtSourceCr", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonAmtAcctDr", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonAmtAcctCr", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonCurrencyRate", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "FIN_Financial_Account_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finFinancialAccountId"):dataField.getField("finFinancialAccountId")));
xmlDocument.setData("reportFIN_Financial_Account_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "FIN_Paymentmethod_ID", "", "959ADF046AFE4F509ABC6E2F41F9DB04", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finPaymentmethodId"):dataField.getField("finPaymentmethodId")));
xmlDocument.setData("reportFIN_Paymentmethod_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Glitem_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cGlitemId"):dataField.getField("cGlitemId")));
xmlDocument.setData("reportC_Glitem_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Paymentdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonQty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("EM_Aprm_Addpayment_BTNname", Utility.getButtonName(this, vars, "110FB2A04E69473784A8478D0BD8B3FB", "EM_Aprm_Addpayment_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_Aprm_Addpayment = org.openbravo.erpCommon.utility.Utility.isModalProcess("DE1B382FDD2540199D223586F6E216D0"); 
xmlDocument.setParameter("EM_Aprm_Addpayment_Modal", modalEM_Aprm_Addpayment?"true":"false");
comboTableData = new ComboTableData(vars, this, "19", "C_Salesregion_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cSalesregionId"):dataField.getField("cSalesregionId")));
xmlDocument.setData("reportC_Salesregion_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("DateAcct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
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



    void printPageButtonEM_Aprm_AddpaymentDE1B382FDD2540199D223586F6E216D0(HttpServletResponse response, VariablesSecureApp vars, String strGL_JournalLine_ID, String stremAprmAddpayment, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DE1B382FDD2540199D223586F6E216D0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Aprm_AddpaymentDE1B382FDD2540199D223586F6E216D0", discard).createXmlDocument();
      xmlDocument.setParameter("key", strGL_JournalLine_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Lines_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "DE1B382FDD2540199D223586F6E216D0");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("DE1B382FDD2540199D223586F6E216D0");
        vars.removeMessage("DE1B382FDD2540199D223586F6E216D0");
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
      String result = "var strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar str$Element_AY=\"" +Utility.getContext(this, vars, "$Element_AY", windowId) + "\";\nvar str$Element_MC=\"" +Utility.getContext(this, vars, "$Element_MC", windowId) + "\";\nvar str$Element_SR=\"" +Utility.getContext(this, vars, "$Element_SR", windowId) + "\";\nvar str$Element_AS=\"" +Utility.getContext(this, vars, "$Element_AS", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPGL_Journal_ID) throws IOException, ServletException {
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
            data = getEditVariables(con, vars, strPGL_Journal_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.glJournallineId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (LinesData.getCurrentDBTimestamp(this, data.glJournallineId).equals(
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
                    data.glJournallineId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|GL_JournalLine_ID", data.glJournallineId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Lines. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
