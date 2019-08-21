
package org.openbravo.erpWindows.GLJournal;


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

public class Header extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Header.class);
  
  private static final String windowId = "132";
  private static final String tabId = "160";
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
     
      if (command.contains("5BE14AA10165490A9ADEFB7532F7FA94")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("5BE14AA10165490A9ADEFB7532F7FA94");
        SessionInfo.setModuleId("A918E3331C404B889D69AA9BFAFB23AC");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("5BE14AA10165490A9ADEFB7532F7FA94")) {
        classInfo.type = "P";
        classInfo.id = "5BE14AA10165490A9ADEFB7532F7FA94";
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
        String strglJournalId = request.getParameter("inpglJournalId");
         String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strglJournalId.equals(""))
              total = saveRecord(vars, myError, 'U', strPGL_JournalBatch_ID);
          else
              total = saveRecord(vars, myError, 'I', strPGL_JournalBatch_ID);
          
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
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID", "");

      String strGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID", "");
            if (strPGL_JournalBatch_ID.equals("")) {
        strPGL_JournalBatch_ID = getParentID(vars, strGL_Journal_ID);
        if (strPGL_JournalBatch_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|GL_JournalBatch_ID");
        vars.setSessionValue(windowId + "|GL_JournalBatch_ID", strPGL_JournalBatch_ID);

        refreshParentSession(vars, strPGL_JournalBatch_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strGL_Journal_ID.equals("")) strGL_Journal_ID = firstElement(vars, tableSQL);
          if (strGL_Journal_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strGL_Journal_ID, strPGL_JournalBatch_ID, tableSQL);

      else printPageDataSheet(response, vars, strPGL_JournalBatch_ID, strGL_Journal_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strGL_Journal_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strGL_Journal_ID.equals("")) strGL_Journal_ID = vars.getRequiredGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      else vars.setSessionValue(windowId + "|GL_Journal_ID", strGL_Journal_ID);
      
      
      String strPGL_JournalBatch_ID = getParentID(vars, strGL_Journal_ID);
      
      vars.setSessionValue(windowId + "|GL_JournalBatch_ID", strPGL_JournalBatch_ID);
      vars.setSessionValue("159|Batch.view", "EDIT");

      refreshParentSession(vars, strPGL_JournalBatch_ID);

      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      printPageEdit(response, request, vars, false, strGL_Journal_ID, strPGL_JournalBatch_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|GL_Journal_ID");
      refreshParentSession(vars, strPGL_JournalBatch_ID);


      String strView = vars.getSessionValue(tabId + "|Header.view");
      String strGL_Journal_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strGL_Journal_ID = firstElement(vars, tableSQL);
          if (strGL_Journal_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strGL_Journal_ID.equals("")) strGL_Journal_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strGL_Journal_ID, strPGL_JournalBatch_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPGL_JournalBatch_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");

      
      vars.removeSessionValue(windowId + "|GL_Journal_ID");
      String strGL_Journal_ID="";

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strGL_Journal_ID = firstElement(vars, tableSQL);
        if (strGL_Journal_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Header.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strGL_Journal_ID, strPGL_JournalBatch_ID, tableSQL);

      else printPageDataSheet(response, vars, strPGL_JournalBatch_ID, strGL_Journal_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
      

      String strGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "RELATION");
      printPageDataSheet(response, vars, strPGL_JournalBatch_ID, strGL_Journal_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");


      printPageEdit(response, request, vars, true, "", strPGL_JournalBatch_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strGL_Journal_ID, strPGL_JournalBatch_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
      String strGL_Journal_ID = vars.getRequiredStringParameter("inpglJournalId");
      
      String strNext = nextElement(vars, strGL_Journal_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPGL_JournalBatch_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
      String strGL_Journal_ID = vars.getRequiredStringParameter("inpglJournalId");
      
      String strPrevious = previousElement(vars, strGL_Journal_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPGL_JournalBatch_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");

      vars.setSessionValue(tabId + "|Header.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Header.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Header.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Header.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|GL_Journal_ID");
      vars.setSessionValue(windowId + "|GL_JournalBatch_ID", strPGL_JournalBatch_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Header.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Header.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|GL_Journal_ID");
      vars.setSessionValue(windowId + "|GL_JournalBatch_ID", strPGL_JournalBatch_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPGL_JournalBatch_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPGL_JournalBatch_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPGL_JournalBatch_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPGL_JournalBatch_ID);      
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
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
      String strGL_Journal_ID = vars.getRequiredGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPGL_JournalBatch_ID);      
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
          String strNext = nextElement(vars, strGL_Journal_ID, tableSQL);
          vars.setSessionValue(windowId + "|GL_Journal_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");

      String strGL_Journal_ID = vars.getRequiredStringParameter("inpglJournalId");
      //HeaderData data = getEditVariables(vars, strPGL_JournalBatch_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = HeaderData.delete(this, strGL_Journal_ID, strPGL_JournalBatch_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|glJournalId");
        vars.setSessionValue(tabId + "|Header.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONDocAction5BE14AA10165490A9ADEFB7532F7FA94")) {
        vars.setSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.strdocaction", vars.getStringParameter("inpdocaction"));
        vars.setSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.inpdocstatus", vars.getRequiredStringParameter("inpdocstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button5BE14AA10165490A9ADEFB7532F7FA94.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "5BE14AA10165490A9ADEFB7532F7FA94", request.getServletPath());
      } else if (vars.commandIn("BUTTON5BE14AA10165490A9ADEFB7532F7FA94")) {
        String strGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID", "");
        String strdocaction = vars.getSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.strdocaction");
        String strProcessing = vars.getSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.strProcessing");
        String strOrg = vars.getSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.strOrg");
        String strClient = vars.getSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.strClient");

        String strdocstatus = vars.getSessionValue("button5BE14AA10165490A9ADEFB7532F7FA94.inpdocstatus");
String stradTableId = "224";

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocAction5BE14AA10165490A9ADEFB7532F7FA94(response, vars, strGL_Journal_ID, strdocaction, strProcessing, strdocstatus, stradTableId);
        }


    } else if (vars.commandIn("SAVE_BUTTONDocAction5BE14AA10165490A9ADEFB7532F7FA94")) {
        String strGL_Journal_ID = vars.getGlobalVariable("inpKey", windowId + "|GL_Journal_ID", "");
        
        ProcessBundle pb = new ProcessBundle("5BE14AA10165490A9ADEFB7532F7FA94", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("GL_Journal_ID", strGL_Journal_ID);
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


    } else if (vars.commandIn("BUTTONPosted")) {
        String strGL_Journal_ID = vars.getGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID", "");
        String strTableId = "224";
        String strPosted = vars.getStringParameter("inpposted");
        String strProcessId = "";
        log4j.debug("Loading Posted button in table: " + strTableId);
        String strOrg = vars.getStringParameter("inpadOrgId");
        String strClient = vars.getStringParameter("inpadClientId");
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{
          vars.setSessionValue("Posted|key", strGL_Journal_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "Header");
          response.sendRedirect(strDireccion + "/ad_actionButton/Posted.html");
        }



    } else if (vars.commandIn("SAVE_XHR")) {
      String strPGL_JournalBatch_ID = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPGL_JournalBatch_ID);
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
  private HeaderData getEditVariables(Connection con, VariablesSecureApp vars, String strPGL_JournalBatch_ID) throws IOException,ServletException {
    HeaderData data = new HeaderData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.cAcctschemaId = vars.getRequiredGlobalVariable("inpcAcctschemaId", windowId + "|C_AcctSchema_ID");     data.cAcctschemaIdr = vars.getStringParameter("inpcAcctschemaId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.description = vars.getRequiredStringParameter("inpdescription");     data.cDoctypeId = vars.getRequiredGlobalVariable("inpcDoctypeId", windowId + "|C_DocType_ID");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.datedoc = vars.getRequiredGlobalVariable("inpdatedoc", windowId + "|DateDoc");     data.dateacct = vars.getRequiredGlobalVariable("inpdateacct", windowId + "|DateAcct");     data.cPeriodId = vars.getRequiredGlobalVariable("inpcPeriodId", windowId + "|C_Period_ID");     data.cPeriodIdr = vars.getStringParameter("inpcPeriodId_R");     data.cCurrencyId = vars.getRequiredGlobalVariable("inpcCurrencyId", windowId + "|C_Currency_ID");     data.cCurrencyIdr = vars.getStringParameter("inpcCurrencyId_R");     data.currencyratetype = vars.getRequiredGlobalVariable("inpcurrencyratetype", windowId + "|CurrencyRateType");    try {   data.currencyrate = vars.getRequiredNumericParameter("inpcurrencyrate", vars.getSessionValue(windowId + "|CurrencyRate"));  } catch (ServletException paramEx) { ex = paramEx; }     data.isopening = vars.getStringParameter("inpisopening", "N");    try {   data.controlamt = vars.getNumericParameter("inpcontrolamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totaldr = vars.getRequiredNumericParameter("inptotaldr");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalcr = vars.getRequiredNumericParameter("inptotalcr");  } catch (ServletException paramEx) { ex = paramEx; }     data.docaction = vars.getRequiredStringParameter("inpdocaction");     data.docstatus = vars.getRequiredStringParameter("inpdocstatus");     data.posted = vars.getRequiredGlobalVariable("inpposted", windowId + "|Posted");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.isprinted = vars.getStringParameter("inpisprinted", "N");     data.glCategoryId = vars.getRequiredGlobalVariable("inpglCategoryId", windowId + "|GL_Category_ID");     data.isapproved = vars.getStringParameter("inpisapproved", "N");     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.mProductId = vars.getStringParameter("inpmProductId");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.aAssetId = vars.getStringParameter("inpaAssetId");     data.cCampaignId = vars.getStringParameter("inpcCampaignId");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.glJournalId = vars.getRequestGlobalVariable("inpglJournalId", windowId + "|GL_Journal_ID");     data.glJournalbatchId = vars.getRequestGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");     data.processed = vars.getRequiredInputGlobalVariable("inpprocessed", windowId + "|Processed", "N");     data.postingtype = vars.getRequiredStringParameter("inppostingtype");     data.processing = vars.getStringParameter("inpprocessing"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.glJournalbatchId = vars.getGlobalVariable("inpglJournalbatchId", windowId + "|GL_JournalBatch_ID");


          vars.getGlobalVariable("inpdescription1", windowId + "|DESCRIPTION1", "");
          vars.getGlobalVariable("inpbatchcurrency", windowId + "|batchCurrency", "");
          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "GL_Journal", "", data.cDoctypeId, false, true);

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPGL_JournalBatch_ID) throws IOException,ServletException {
      
      BatchData[] data = BatchData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPGL_JournalBatch_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|DateDoc", data[0].datedoc);    vars.setSessionValue(windowId + "|DateAcct", data[0].dateacct);    vars.setSessionValue(windowId + "|C_Period_ID", data[0].cPeriodId);    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].cCurrencyId);    vars.setSessionValue(windowId + "|GL_Category_ID", data[0].glCategoryId);    vars.setSessionValue(windowId + "|GL_JournalBatch_ID", data[0].glJournalbatchId);    vars.setSessionValue(windowId + "|PostingType", data[0].postingtype);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);
      vars.setSessionValue(windowId + "|GL_JournalBatch_ID", strPGL_JournalBatch_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|Batch_Org", ((dataField!=null)?dataField.getField("adOrgId"):((data==null || data.length==0)?"":data[0].adOrgId)));
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strGL_Journal_ID) throws ServletException {
    String strPGL_JournalBatch_ID = HeaderData.selectParentID(this, strGL_Journal_ID);
    if (strPGL_JournalBatch_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strGL_Journal_ID);
      throw new ServletException("Parent record not found");
    }
    return strPGL_JournalBatch_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|C_AcctSchema_ID", data[0].getField("cAcctschemaId"));    vars.setSessionValue(windowId + "|C_DocType_ID", data[0].getField("cDoctypeId"));    vars.setSessionValue(windowId + "|DateDoc", data[0].getField("datedoc"));    vars.setSessionValue(windowId + "|DateAcct", data[0].getField("dateacct"));    vars.setSessionValue(windowId + "|C_Period_ID", data[0].getField("cPeriodId"));    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].getField("cCurrencyId"));    vars.setSessionValue(windowId + "|CurrencyRateType", data[0].getField("currencyratetype"));    vars.setSessionValue(windowId + "|CurrencyRate", data[0].getField("currencyrate"));    vars.setSessionValue(windowId + "|Posted", data[0].getField("posted"));    vars.setSessionValue(windowId + "|GL_Category_ID", data[0].getField("glCategoryId"));    vars.setSessionValue(windowId + "|GL_JournalBatch_ID", data[0].getField("glJournalbatchId"));    vars.setSessionValue(windowId + "|GL_Journal_ID", data[0].getField("glJournalId"));    vars.setSessionValue(windowId + "|Processed", data[0].getField("processed"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPGL_JournalBatch_ID) throws IOException,ServletException {
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPGL_JournalBatch_ID, vars.getStringParameter("inpglJournalId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPGL_JournalBatch_ID, String strGL_Journal_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strGL_Journal_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strGL_Journal_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GLJournal/Header_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Header", false, "document.frmMain.inpglJournalId", "grid", "..", "".equals("Y"), "GLJournal", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPGL_JournalBatch_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("710", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "glJournalId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "GLJournal", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Header_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", HeaderData.selectParent(this, strPGL_JournalBatch_ID));
    else xmlDocument.setParameter("parent", HeaderData.selectParentTrl(this, strPGL_JournalBatch_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strGL_Journal_ID, String strPGL_JournalBatch_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " GL_Journal.DocumentNo DESC";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    HeaderData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPGL_JournalBatch_ID, strGL_Journal_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strGL_Journal_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new HeaderData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("glJournalId") == null || dataField.getField("glJournalId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strDESCRIPTION1 = HeaderData.selectAux12(this, strPGL_JournalBatch_ID);
    vars.setSessionValue(windowId + "|DESCRIPTION1", strDESCRIPTION1);
        String strbatchCurrency = HeaderData.selectAuxC382CB93EB254FC68DB7D25B34A5A6F0(this, strPGL_JournalBatch_ID);
    vars.setSessionValue(windowId + "|batchCurrency", strbatchCurrency);
        String strDOCBASETYPE = "GLJ";
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPGL_JournalBatch_ID);
        data = HeaderData.set(strPGL_JournalBatch_ID, Utility.getDefault(this, vars, "User2_ID", "", "132", "", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "132", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "132", "", dataField), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "132", "", dataField), HeaderData.selectDef1622_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "132", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "132", "", dataField), HeaderData.selectDef1624_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "132", "", dataField)), HeaderData.selectDef1625(this, Utility.getContext(this, vars, "AD_Org_ID", "132"), Utility.getContext(this, vars, "#AD_Client_ID", windowId)), Utility.getDefault(this, vars, "DocumentNo", "", "132", "", dataField), Utility.getDefault(this, vars, "DocStatus", "DR", "132", "", dataField), Utility.getDefault(this, vars, "IsPrinted", "N", "132", "N", dataField), Utility.getDefault(this, vars, "Posted", "N", "132", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "132", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "132", "N", dataField))), Utility.getDefault(this, vars, "Description", "@DESCRIPTION1@", "132", "", dataField), Utility.getDefault(this, vars, "PostingType", "@PostingType@", "132", "", dataField), Utility.getDefault(this, vars, "C_AcctSchema_ID", "@$C_AcctSchema_ID@", "132", "", dataField), Utility.getDefault(this, vars, "GL_Category_ID", "@GL_Category_ID@", "132", "", dataField), HeaderData.selectDef1634(this, Utility.getContext(this, vars, "C_PERIOD_ID", "132"), Utility.getContext(this, vars, "DateDoc", "132")), HeaderData.selectDef1635(this, Utility.getContext(this, vars, "C_PERIOD_ID", "132"), Utility.getContext(this, vars, "DateAcct", "132")), HeaderData.selectDef1636(this, Utility.getContext(this, vars, "C_Period_ID", "132"), Utility.getContext(this, vars, "AD_Client_ID", "132"), Utility.getContext(this, vars, "AD_Org_ID", "132"), Utility.getContext(this, vars, "DateAcct", "132")), Utility.getDefault(this, vars, "C_Currency_ID", "@C_Currency_ID@", "132", "", dataField), Utility.getDefault(this, vars, "TotalDr", "0", "132", "0", dataField), Utility.getDefault(this, vars, "TotalCr", "0", "132", "0", dataField), Utility.getDefault(this, vars, "DocAction", "CO", "132", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "135", Utility.getDefault(this, vars, "DocAction", "CO", "132", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "135", Utility.getDefault(this, vars, "DocAction", "CO", "132", "N", dataField))), Utility.getDefault(this, vars, "IsApproved", "Y", "132", "N", dataField), Utility.getDefault(this, vars, "CurrencyRateType", "S", "132", "", dataField), Utility.getDefault(this, vars, "CurrencyRate", "1", "132", "0", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "IsOpening", "N", "132", "N", dataField), Utility.getDefault(this, vars, "ControlAmt", "", "132", "", dataField), Utility.getDefault(this, vars, "Processed", "", "132", "N", dataField), Utility.getDefault(this, vars, "Processing", "", "132", "N", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "132", "", dataField), HeaderData.selectDef6371060DDBD14FE88ABBCE2DC0EACCF8_2(this,  vars.getLanguage(), Utility.getDefault(this, vars, "M_Product_ID", "", "132", "", dataField)), Utility.getDefault(this, vars, "A_Asset_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Campaign_ID", "", "132", "", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "132", "", dataField), HeaderData.selectDefAA038C785DE7452199DB51FE58FE1DA6_3(this, Utility.getDefault(this, vars, "C_Bpartner_ID", "", "132", "", dataField)), Utility.getDefault(this, vars, "User1_ID", "", "132", "", dataField));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "GL_Journal", "", data[0].cDoctypeId, false, false) + ">";
      }
     }
      
    String currentPOrg=BatchData.selectOrg(this, strPGL_JournalBatch_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GLJournal/Header_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GLJournal/Header_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Header", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpglJournalId", "", "..", "".equals("Y"), "GLJournal", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strGL_Journal_ID), !hasReadOnlyAccess);
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
      // if (!strGL_Journal_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "GLJournal", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Header_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("DESCRIPTION1", strDESCRIPTION1);
        xmlDocument.setParameter("batchCurrency", strbatchCurrency);
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
comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "7BAD557A87B64D5386215D77F79A43D7", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cAcctschemaId"):dataField.getField("cAcctschemaId")));
xmlDocument.setData("reportC_AcctSchema_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_DocType_ID", "", "102", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_DocType_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("DateDoc_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("DateAcct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "18", "C_Period_ID", "233", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cPeriodId"):dataField.getField("cPeriodId")));
xmlDocument.setData("reportC_Period_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Currency_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCurrencyId"):dataField.getField("cCurrencyId")));
xmlDocument.setData("reportC_Currency_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonCurrencyRate", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalDr", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonControlAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotalCr", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("DocAction_BTNname", Utility.getButtonName(this, vars, "135", (dataField==null?data[0].getField("docaction"):dataField.getField("docaction")), "DocAction_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalDocAction = org.openbravo.erpCommon.utility.Utility.isModalProcess("5BE14AA10165490A9ADEFB7532F7FA94"); 
xmlDocument.setParameter("DocAction_Modal", modalDocAction?"true":"false");
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
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



    void printPageButtonDocAction5BE14AA10165490A9ADEFB7532F7FA94(HttpServletResponse response, VariablesSecureApp vars, String strGL_Journal_ID, String strdocaction, String strProcessing, String strdocstatus, String stradTableId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 5BE14AA10165490A9ADEFB7532F7FA94");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocAction", discard).createXmlDocument();
      xmlDocument.setParameter("key", strGL_Journal_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "5BE14AA10165490A9ADEFB7532F7FA94");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("5BE14AA10165490A9ADEFB7532F7FA94");
        vars.removeMessage("5BE14AA10165490A9ADEFB7532F7FA94");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

      xmlDocument.setParameter("docstatus", strdocstatus);
xmlDocument.setParameter("adTableId", stradTableId);
    try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
xmlDocument.setParameter("processId", "5BE14AA10165490A9ADEFB7532F7FA94");
xmlDocument.setParameter("processDescription", "");
xmlDocument.setParameter("docaction", (strdocaction.equals("--")?"CL":strdocaction));
FieldProvider[] dataDocAction = ActionButtonUtility.docAction(this, vars, strdocaction, "135", strdocstatus, strProcessing, stradTableId, tabId);
xmlDocument.setData("reportdocaction", "liststructure", dataDocAction);
StringBuffer dact = new StringBuffer();
if (dataDocAction!=null) {
  dact.append("var arrDocAction = new Array(\n");
  for (int i=0;i<dataDocAction.length;i++) {
    dact.append("new Array(\"" + dataDocAction[i].getField("id") + "\", \"" + dataDocAction[i].getField("name") + "\", \"" + dataDocAction[i].getField("description") + "\")\n");
    if (i<dataDocAction.length-1) dact.append(",\n");
  }
  dact.append(");");
} else dact.append("var arrDocAction = null");
xmlDocument.setParameter("array", dact.toString());

      
      out.println(xmlDocument.print());
      out.close();
    }


    private String getDisplayLogicContext(VariablesSecureApp vars, boolean isNew) throws IOException, ServletException {
      log4j.debug("Output: Display logic context fields");
      String result = "var strShowAcct=\"" +Utility.getContext(this, vars, "#ShowAcct", windowId) + "\";\nvar strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar str$Element_AS=\"" +Utility.getContext(this, vars, "$Element_AS", windowId) + "\";\nvar str$Element_MC=\"" +Utility.getContext(this, vars, "$Element_MC", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPGL_JournalBatch_ID) throws IOException, ServletException {
    HeaderData data = null;
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
            data = getEditVariables(con, vars, strPGL_JournalBatch_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.glJournalId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (HeaderData.getCurrentDBTimestamp(this, data.glJournalId).equals(
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
                    data.glJournalId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                        //BUTTON TEXT FILLING
                    data.docactionBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "135", data.getField("DocAction"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|GL_Journal_ID", data.glJournalId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Header. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
