
package org.openbravo.erpWindows.ProcessPlan;




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

public class IOProducts extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(IOProducts.class);
  
  private static final String windowId = "800051";
  private static final String tabId = "800110";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
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
     
      if (command.contains("FF8081813219E68E013219ECFE930004")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("FF8081813219E68E013219ECFE930004");
        SessionInfo.setModuleId("0");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("FF8081813219E68E013219ECFE930004")) {
        classInfo.type = "P";
        classInfo.id = "FF8081813219E68E013219ECFE930004";
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
        String strmaSequenceproductId = request.getParameter("inpmaSequenceproductId");
         String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strmaSequenceproductId.equals(""))
              total = saveRecord(vars, myError, 'U', strPMA_Sequence_ID);
          else
              total = saveRecord(vars, myError, 'I', strPMA_Sequence_ID);
          
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
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID", "");

      String strMA_Sequenceproduct_ID = vars.getGlobalVariable("inpmaSequenceproductId", windowId + "|MA_Sequenceproduct_ID", "");
            if (strPMA_Sequence_ID.equals("")) {
        strPMA_Sequence_ID = getParentID(vars, strMA_Sequenceproduct_ID);
        if (strPMA_Sequence_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|MA_Sequence_ID");
        vars.setSessionValue(windowId + "|MA_Sequence_ID", strPMA_Sequence_ID);
      vars.removeSessionValue(windowId + "|MA_Processplan_Version_ID");
        refreshParentSession(vars, strPMA_Sequence_ID);
      }


      String strView = vars.getSessionValue(tabId + "|IOProducts.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strMA_Sequenceproduct_ID.equals("")) strMA_Sequenceproduct_ID = firstElement(vars, tableSQL);
          if (strMA_Sequenceproduct_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strMA_Sequenceproduct_ID, strPMA_Sequence_ID, tableSQL);

      else printPageDataSheet(response, vars, strPMA_Sequence_ID, strMA_Sequenceproduct_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strMA_Sequenceproduct_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strMA_Sequenceproduct_ID.equals("")) strMA_Sequenceproduct_ID = vars.getRequiredGlobalVariable("inpmaSequenceproductId", windowId + "|MA_Sequenceproduct_ID");
      else vars.setSessionValue(windowId + "|MA_Sequenceproduct_ID", strMA_Sequenceproduct_ID);
      
      
      String strPMA_Sequence_ID = getParentID(vars, strMA_Sequenceproduct_ID);
      
      vars.setSessionValue(windowId + "|MA_Sequence_ID", strPMA_Sequence_ID);
      vars.setSessionValue("800109|Operation.view", "EDIT");
      vars.removeSessionValue(windowId + "|MA_Processplan_Version_ID");
      refreshParentSession(vars, strPMA_Sequence_ID);

      vars.setSessionValue(tabId + "|IOProducts.view", "EDIT");

      printPageEdit(response, request, vars, false, strMA_Sequenceproduct_ID, strPMA_Sequence_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|MA_Sequenceproduct_ID");
      refreshParentSession(vars, strPMA_Sequence_ID);


      String strView = vars.getSessionValue(tabId + "|IOProducts.view");
      String strMA_Sequenceproduct_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strMA_Sequenceproduct_ID = firstElement(vars, tableSQL);
          if (strMA_Sequenceproduct_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strMA_Sequenceproduct_ID.equals("")) strMA_Sequenceproduct_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strMA_Sequenceproduct_ID, strPMA_Sequence_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPMA_Sequence_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamM_Product_ID", tabId + "|paramM_Product_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");

      
      vars.removeSessionValue(windowId + "|MA_Sequenceproduct_ID");
      String strMA_Sequenceproduct_ID="";

      String strView = vars.getSessionValue(tabId + "|IOProducts.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strMA_Sequenceproduct_ID = firstElement(vars, tableSQL);
        if (strMA_Sequenceproduct_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|IOProducts.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strMA_Sequenceproduct_ID, strPMA_Sequence_ID, tableSQL);

      else printPageDataSheet(response, vars, strPMA_Sequence_ID, strMA_Sequenceproduct_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
      

      String strMA_Sequenceproduct_ID = vars.getGlobalVariable("inpmaSequenceproductId", windowId + "|MA_Sequenceproduct_ID", "");
      vars.setSessionValue(tabId + "|IOProducts.view", "RELATION");
      printPageDataSheet(response, vars, strPMA_Sequence_ID, strMA_Sequenceproduct_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");


      printPageEdit(response, request, vars, true, "", strPMA_Sequence_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strMA_Sequenceproduct_ID = vars.getGlobalVariable("inpmaSequenceproductId", windowId + "|MA_Sequenceproduct_ID", "");
      vars.setSessionValue(tabId + "|IOProducts.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strMA_Sequenceproduct_ID, strPMA_Sequence_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
      String strMA_Sequenceproduct_ID = vars.getRequiredStringParameter("inpmaSequenceproductId");
      
      String strNext = nextElement(vars, strMA_Sequenceproduct_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPMA_Sequence_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
      String strMA_Sequenceproduct_ID = vars.getRequiredStringParameter("inpmaSequenceproductId");
      
      String strPrevious = previousElement(vars, strMA_Sequenceproduct_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPMA_Sequence_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");

      vars.setSessionValue(tabId + "|IOProducts.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|IOProducts.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|IOProducts.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|IOProducts.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|MA_Sequenceproduct_ID");
      vars.setSessionValue(windowId + "|MA_Sequence_ID", strPMA_Sequence_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|IOProducts.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|IOProducts.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|MA_Sequenceproduct_ID");
      vars.setSessionValue(windowId + "|MA_Sequence_ID", strPMA_Sequence_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPMA_Sequence_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPMA_Sequence_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPMA_Sequence_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPMA_Sequence_ID);      
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
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
      String strMA_Sequenceproduct_ID = vars.getRequiredGlobalVariable("inpmaSequenceproductId", windowId + "|MA_Sequenceproduct_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPMA_Sequence_ID);      
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
          String strNext = nextElement(vars, strMA_Sequenceproduct_ID, tableSQL);
          vars.setSessionValue(windowId + "|MA_Sequenceproduct_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");

      String strMA_Sequenceproduct_ID = vars.getRequiredStringParameter("inpmaSequenceproductId");
      //IOProductsData data = getEditVariables(vars, strPMA_Sequence_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = IOProductsData.delete(this, strMA_Sequenceproduct_ID, strPMA_Sequence_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|maSequenceproductId");
        vars.setSessionValue(tabId + "|IOProducts.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONCopyproductFF8081813219E68E013219ECFE930004")) {
        vars.setSessionValue("buttonFF8081813219E68E013219ECFE930004.strcopyproduct", vars.getStringParameter("inpcopyproduct"));
        vars.setSessionValue("buttonFF8081813219E68E013219ECFE930004.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonFF8081813219E68E013219ECFE930004.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonFF8081813219E68E013219ECFE930004.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonFF8081813219E68E013219ECFE930004.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "FF8081813219E68E013219ECFE930004", request.getServletPath());
      } else if (vars.commandIn("BUTTONFF8081813219E68E013219ECFE930004")) {
        String strMA_Sequenceproduct_ID = vars.getGlobalVariable("inpmaSequenceproductId", windowId + "|MA_Sequenceproduct_ID", "");
        String strcopyproduct = vars.getSessionValue("buttonFF8081813219E68E013219ECFE930004.strcopyproduct");
        String strProcessing = vars.getSessionValue("buttonFF8081813219E68E013219ECFE930004.strProcessing");
        String strOrg = vars.getSessionValue("buttonFF8081813219E68E013219ECFE930004.strOrg");
        String strClient = vars.getSessionValue("buttonFF8081813219E68E013219ECFE930004.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCopyproductFF8081813219E68E013219ECFE930004(response, vars, strMA_Sequenceproduct_ID, strcopyproduct, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONCopyproductFF8081813219E68E013219ECFE930004")) {
        String strMA_Sequenceproduct_ID = vars.getGlobalVariable("inpKey", windowId + "|MA_Sequenceproduct_ID", "");
        
        ProcessBundle pb = new ProcessBundle("FF8081813219E68E013219ECFE930004", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("MA_Sequenceproduct_ID", strMA_Sequenceproduct_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strvalue = vars.getStringParameter("inpvalue");
params.put("value", strvalue);
String strname = vars.getStringParameter("inpname");
params.put("name", strname);
String strmProductCategoryId = vars.getStringParameter("inpmProductCategoryId");
params.put("mProductCategoryId", strmProductCategoryId);
String strproductiontype = vars.getStringParameter("inpproductiontype");
params.put("productiontype", strproductiontype);
String strqty = vars.getNumericParameter("inpqty");
params.put("qty", strqty);
String strcopyattribute = vars.getStringParameter("inpcopyattribute", "N");
params.put("copyattribute", strcopyattribute);

        
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
      String strPMA_Sequence_ID = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPMA_Sequence_ID);
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
  private IOProductsData getEditVariables(Connection con, VariablesSecureApp vars, String strPMA_Sequence_ID) throws IOException,ServletException {
    IOProductsData data = new IOProductsData();
    ServletException ex = null;
    try {
   try {   data.line = vars.getRequiredNumericParameter("inpline", vars.getSessionValue(windowId + "|Line"));  } catch (ServletException paramEx) { ex = paramEx; }     data.mProductId = vars.getRequiredStringParameter("inpmProductId");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");     data.productiontype = vars.getRequiredStringParameter("inpproductiontype");     data.productiontyper = vars.getStringParameter("inpproductiontype_R");    try {   data.quantity = vars.getRequiredNumericParameter("inpquantity");  } catch (ServletException paramEx) { ex = paramEx; }     data.cUomId = vars.getRequiredStringParameter("inpcUomId");     data.cUomIdr = vars.getStringParameter("inpcUomId_R");     data.uniqueattconsum = vars.getStringParameter("inpuniqueattconsum", "N");    try {   data.componentcost = vars.getNumericParameter("inpcomponentcost");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.decrease = vars.getNumericParameter("inpdecrease");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.rejected = vars.getNumericParameter("inprejected");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.quantityorder = vars.getNumericParameter("inpquantityorder");  } catch (ServletException paramEx) { ex = paramEx; }     data.mProductUomId = vars.getStringParameter("inpmProductUomId");     data.mProductUomIdr = vars.getStringParameter("inpmProductUomId_R");    try {   data.cost = vars.getNumericParameter("inpcost");  } catch (ServletException paramEx) { ex = paramEx; }     data.isactive = vars.getStringParameter("inpisactive", "N");    try {   data.divisiongroupqty = vars.getNumericParameter("inpdivisiongroupqty");  } catch (ServletException paramEx) { ex = paramEx; }     data.copyproduct = vars.getStringParameter("inpcopyproduct");     data.mWarehouseRuleId = vars.getStringParameter("inpmWarehouseRuleId");     data.mWarehouseRuleIdr = vars.getStringParameter("inpmWarehouseRuleId_R");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.maSequenceproductId = vars.getRequestGlobalVariable("inpmaSequenceproductId", windowId + "|MA_Sequenceproduct_ID");     data.maSequenceId = vars.getRequiredStringParameter("inpmaSequenceId"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.maSequenceId = vars.getGlobalVariable("inpmaSequenceId", windowId + "|MA_Sequence_ID");


          vars.getGlobalVariable("inphasseconduom", windowId + "|HASSECONDUOM", "");
    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPMA_Sequence_ID) throws IOException,ServletException {
      
      OperationData[] data = OperationData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getSessionValue(windowId + "|MA_Processplan_Version_ID"), strPMA_Sequence_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|MA_Sequence_ID", data[0].maSequenceId);
      vars.setSessionValue(windowId + "|MA_Sequence_ID", strPMA_Sequence_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strMA_Sequenceproduct_ID) throws ServletException {
    String strPMA_Sequence_ID = IOProductsData.selectParentID(this, strMA_Sequenceproduct_ID);
    if (strPMA_Sequence_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strMA_Sequenceproduct_ID);
      throw new ServletException("Parent record not found");
    }
    return strPMA_Sequence_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|Line", data[0].getField("line"));    vars.setSessionValue(windowId + "|MA_Sequenceproduct_ID", data[0].getField("maSequenceproductId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPMA_Sequence_ID) throws IOException,ServletException {
      IOProductsData[] data = IOProductsData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPMA_Sequence_ID, vars.getStringParameter("inpmaSequenceproductId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPMA_Sequence_ID, String strMA_Sequenceproduct_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamM_Product_ID = vars.getSessionValue(tabId + "|paramM_Product_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamM_Product_ID)) || !(("").equals(strParamM_Product_ID) || ("%").equals(strParamM_Product_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strMA_Sequenceproduct_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strMA_Sequenceproduct_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ProcessPlan/IOProducts_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "IOProducts", false, "document.frmMain.inpmaSequenceproductId", "grid", "..", "".equals("Y"), "ProcessPlan", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPMA_Sequence_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("801760", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "maSequenceproductId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "IOProducts_Relation.html", "ProcessPlan", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "IOProducts_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", IOProductsData.selectParent(this, strPMA_Sequence_ID));
    else xmlDocument.setParameter("parent", IOProductsData.selectParentTrl(this, strPMA_Sequence_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strMA_Sequenceproduct_ID, String strPMA_Sequence_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " line";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    IOProductsData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamM_Product_ID = vars.getSessionValue(tabId + "|paramM_Product_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamM_Product_ID)) || !(("").equals(strParamM_Product_ID) || ("%").equals(strParamM_Product_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = IOProductsData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPMA_Sequence_ID, strMA_Sequenceproduct_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strMA_Sequenceproduct_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new IOProductsData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("maSequenceproductId") == null || dataField.getField("maSequenceproductId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strHASSECONDUOM = IOProductsData.selectAux800038(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|HASSECONDUOM", strHASSECONDUOM);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPMA_Sequence_ID);
        data = IOProductsData.set(strPMA_Sequence_ID, Utility.getDefault(this, vars, "Uniqueattconsum", "N", "800051", "N", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "800051", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "800051", "", dataField), "Y", Utility.getDefault(this, vars, "Createdby", "", "800051", "", dataField), IOProductsData.selectDef801541_0(this, Utility.getDefault(this, vars, "Createdby", "", "800051", "", dataField)), Utility.getDefault(this, vars, "Updatedby", "", "800051", "", dataField), IOProductsData.selectDef801543_1(this, Utility.getDefault(this, vars, "Updatedby", "", "800051", "", dataField)), Utility.getDefault(this, vars, "M_Product_ID", "", "800051", "", dataField), IOProductsData.selectDef801546_2(this,  vars.getLanguage(), Utility.getDefault(this, vars, "M_Product_ID", "", "800051", "", dataField)), Utility.getDefault(this, vars, "Quantity", "", "800051", "0", dataField), Utility.getDefault(this, vars, "Productiontype", "", "800051", "", dataField), Utility.getDefault(this, vars, "C_UOM_ID", "", "800051", "", dataField), Utility.getDefault(this, vars, "QuantityOrder", "", "800051", "", dataField), Utility.getDefault(this, vars, "M_Product_Uom_Id", "", "800051", "", dataField), Utility.getDefault(this, vars, "Componentcost", "1", "800051", "", dataField), Utility.getDefault(this, vars, "Decrease", "1", "800051", "", dataField), Utility.getDefault(this, vars, "Rejected", "1", "800051", "", dataField), Utility.getDefault(this, vars, "Cost", "", "800051", "", dataField), Utility.getDefault(this, vars, "Divisiongroupqty", "", "800051", "", dataField), Utility.getDefault(this, vars, "Copyproduct", "", "800051", "N", dataField), Utility.getDefault(this, vars, "M_Warehouse_Rule_ID", "", "800051", "", dataField), IOProductsData.selectDefECD90C86634B4A51B2FFCE7248293BED(this, strPMA_Sequence_ID));
        
      }
     }
      
    String currentPOrg=OperationData.selectOrg(this, strPMA_Sequence_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ProcessPlan/IOProducts_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/ProcessPlan/IOProducts_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "IOProducts", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpmaSequenceproductId", "", "..", "".equals("Y"), "ProcessPlan", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strMA_Sequenceproduct_ID), !hasReadOnlyAccess);
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
      // if (!strMA_Sequenceproduct_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "IOProducts_Relation.html", "ProcessPlan", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "IOProducts_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("HASSECONDUOM", strHASSECONDUOM);
    
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
comboTableData = new ComboTableData(vars, this, "17", "Productiontype", "800034", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("productiontype"):dataField.getField("productiontype")));
xmlDocument.setData("reportProductiontype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonQuantity", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_UOM_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomId"):dataField.getField("cUomId")));
xmlDocument.setData("reportC_UOM_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonComponentcost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDecrease", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonRejected", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQuantityOrder", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "M_Product_Uom_Id", "800000", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mProductUomId"):dataField.getField("mProductUomId")));
xmlDocument.setData("reportM_Product_Uom_Id","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonCost", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Copyproduct_BTNname", Utility.getButtonName(this, vars, "639A3B8F69E644DAB62B6D07ACE77DDB", "Copyproduct_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCopyproduct = org.openbravo.erpCommon.utility.Utility.isModalProcess("FF8081813219E68E013219ECFE930004"); 
xmlDocument.setParameter("Copyproduct_Modal", modalCopyproduct?"true":"false");
comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_Rule_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mWarehouseRuleId"):dataField.getField("mWarehouseRuleId")));
xmlDocument.setData("reportM_Warehouse_Rule_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
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



    void printPageButtonCopyproductFF8081813219E68E013219ECFE930004(HttpServletResponse response, VariablesSecureApp vars, String strMA_Sequenceproduct_ID, String strcopyproduct, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8081813219E68E013219ECFE930004");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/CopyproductFF8081813219E68E013219ECFE930004", discard).createXmlDocument();
      xmlDocument.setParameter("key", strMA_Sequenceproduct_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "IOProducts_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "FF8081813219E68E013219ECFE930004");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("FF8081813219E68E013219ECFE930004");
        vars.removeMessage("FF8081813219E68E013219ECFE930004");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("Value", IOProductsData.selectActPFF8081813219E68E013219ECFE930004_Value(this, Utility.getContext(this, vars, "MA_SEQUENCEPRODUCT_ID", "800051")));
    xmlDocument.setParameter("Name", IOProductsData.selectActPFF8081813219E68E013219ECFE930004_Name(this, Utility.getContext(this, vars, "MA_SEQUENCEPRODUCT_ID", "800051")));
    xmlDocument.setParameter("M_Product_Category_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Product_Category_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonFF8081813219E68E013219ECFE930004.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportM_Product_Category_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("Productiontype", "+");
    comboTableData = new ComboTableData(vars, this, "17", "Productiontype", "800034", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonFF8081813219E68E013219ECFE930004.originalParams"), comboTableData, windowId, "+");
    xmlDocument.setData("reportProductiontype", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("Qty", "0");
    xmlDocument.setParameter("Copyattribute", "Y");
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPMA_Sequence_ID) throws IOException, ServletException {
    IOProductsData data = null;
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
            data = getEditVariables(con, vars, strPMA_Sequence_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.maSequenceproductId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (IOProductsData.getCurrentDBTimestamp(this, data.maSequenceproductId).equals(
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
                    data.maSequenceproductId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|MA_Sequenceproduct_ID", data.maSequenceproductId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet IOProducts. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
