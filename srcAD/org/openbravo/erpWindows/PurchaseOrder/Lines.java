
package org.openbravo.erpWindows.PurchaseOrder;


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
  
  private static final String windowId = "181";
  private static final String tabId = "293";
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
     
      if (command.contains("DFC78024B1F54CBB95DC73425BA6687F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("DFC78024B1F54CBB95DC73425BA6687F");
        SessionInfo.setModuleId("0");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "DFC78024B1F54CBB95DC73425BA6687F";
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
        String strcOrderlineId = request.getParameter("inpcOrderlineId");
         String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strcOrderlineId.equals(""))
              total = saveRecord(vars, myError, 'U', strPC_Order_ID);
          else
              total = saveRecord(vars, myError, 'I', strPC_Order_ID);
          
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
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");

      String strC_OrderLine_ID = vars.getGlobalVariable("inpcOrderlineId", windowId + "|C_OrderLine_ID", "");
            if (strPC_Order_ID.equals("")) {
        strPC_Order_ID = getParentID(vars, strC_OrderLine_ID);
        if (strPC_Order_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|C_Order_ID");
        vars.setSessionValue(windowId + "|C_Order_ID", strPC_Order_ID);

        refreshParentSession(vars, strPC_Order_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Lines.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strC_OrderLine_ID.equals("")) strC_OrderLine_ID = firstElement(vars, tableSQL);
          if (strC_OrderLine_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_OrderLine_ID, strPC_Order_ID, tableSQL);

      else printPageDataSheet(response, vars, strPC_Order_ID, strC_OrderLine_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strC_OrderLine_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strC_OrderLine_ID.equals("")) strC_OrderLine_ID = vars.getRequiredGlobalVariable("inpcOrderlineId", windowId + "|C_OrderLine_ID");
      else vars.setSessionValue(windowId + "|C_OrderLine_ID", strC_OrderLine_ID);
      
      
      String strPC_Order_ID = getParentID(vars, strC_OrderLine_ID);
      
      vars.setSessionValue(windowId + "|C_Order_ID", strPC_Order_ID);
      vars.setSessionValue("294|Header.view", "EDIT");

      refreshParentSession(vars, strPC_Order_ID);

      vars.setSessionValue(tabId + "|Lines.view", "EDIT");

      printPageEdit(response, request, vars, false, strC_OrderLine_ID, strPC_Order_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|C_OrderLine_ID");
      refreshParentSession(vars, strPC_Order_ID);


      String strView = vars.getSessionValue(tabId + "|Lines.view");
      String strC_OrderLine_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strC_OrderLine_ID = firstElement(vars, tableSQL);
          if (strC_OrderLine_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strC_OrderLine_ID.equals("")) strC_OrderLine_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strC_OrderLine_ID, strPC_Order_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPC_Order_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamC_Order_ID", tabId + "|paramC_Order_ID");
vars.getRequestGlobalVariable("inpParamLine", tabId + "|paramLine");
vars.getRequestGlobalVariable("inpParamLineNetAmt", tabId + "|paramLineNetAmt");
vars.getRequestGlobalVariable("inpParamLine_f", tabId + "|paramLine_f");
vars.getRequestGlobalVariable("inpParamLineNetAmt_f", tabId + "|paramLineNetAmt_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");

      
      vars.removeSessionValue(windowId + "|C_OrderLine_ID");
      String strC_OrderLine_ID="";

      String strView = vars.getSessionValue(tabId + "|Lines.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strC_OrderLine_ID = firstElement(vars, tableSQL);
        if (strC_OrderLine_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Lines.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strC_OrderLine_ID, strPC_Order_ID, tableSQL);

      else printPageDataSheet(response, vars, strPC_Order_ID, strC_OrderLine_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      

      String strC_OrderLine_ID = vars.getGlobalVariable("inpcOrderlineId", windowId + "|C_OrderLine_ID", "");
      vars.setSessionValue(tabId + "|Lines.view", "RELATION");
      printPageDataSheet(response, vars, strPC_Order_ID, strC_OrderLine_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");


      printPageEdit(response, request, vars, true, "", strPC_Order_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strC_OrderLine_ID = vars.getGlobalVariable("inpcOrderlineId", windowId + "|C_OrderLine_ID", "");
      vars.setSessionValue(tabId + "|Lines.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strC_OrderLine_ID, strPC_Order_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      String strC_OrderLine_ID = vars.getRequiredStringParameter("inpcOrderlineId");
      
      String strNext = nextElement(vars, strC_OrderLine_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPC_Order_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      String strC_OrderLine_ID = vars.getRequiredStringParameter("inpcOrderlineId");
      
      String strPrevious = previousElement(vars, strC_OrderLine_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPC_Order_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");

      vars.setSessionValue(tabId + "|Lines.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");

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
      vars.removeSessionValue(windowId + "|C_OrderLine_ID");
      vars.setSessionValue(windowId + "|C_Order_ID", strPC_Order_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Lines.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Lines.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|C_OrderLine_ID");
      vars.setSessionValue(windowId + "|C_Order_ID", strPC_Order_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPC_Order_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPC_Order_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPC_Order_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPC_Order_ID);      
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
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      String strC_OrderLine_ID = vars.getRequiredGlobalVariable("inpcOrderlineId", windowId + "|C_OrderLine_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPC_Order_ID);      
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
          String strNext = nextElement(vars, strC_OrderLine_ID, tableSQL);
          vars.setSessionValue(windowId + "|C_OrderLine_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");

      String strC_OrderLine_ID = vars.getRequiredStringParameter("inpcOrderlineId");
      //LinesData data = getEditVariables(vars, strPC_Order_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = LinesData.delete(this, strC_OrderLine_ID, strPC_Order_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|cOrderlineId");
        vars.setSessionValue(tabId + "|Lines.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONExplodeDFC78024B1F54CBB95DC73425BA6687F")) {
        vars.setSessionValue("buttonDFC78024B1F54CBB95DC73425BA6687F.strexplode", vars.getStringParameter("inpexplode"));
        vars.setSessionValue("buttonDFC78024B1F54CBB95DC73425BA6687F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonDFC78024B1F54CBB95DC73425BA6687F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonDFC78024B1F54CBB95DC73425BA6687F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonDFC78024B1F54CBB95DC73425BA6687F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "DFC78024B1F54CBB95DC73425BA6687F", request.getServletPath());    
     } else if (vars.commandIn("BUTTONDFC78024B1F54CBB95DC73425BA6687F")) {
        String strC_OrderLine_ID = vars.getGlobalVariable("inpcOrderlineId", windowId + "|C_OrderLine_ID", "");
        String strexplode = vars.getSessionValue("buttonDFC78024B1F54CBB95DC73425BA6687F.strexplode");
        String strProcessing = vars.getSessionValue("buttonDFC78024B1F54CBB95DC73425BA6687F.strProcessing");
        String strOrg = vars.getSessionValue("buttonDFC78024B1F54CBB95DC73425BA6687F.strOrg");
        String strClient = vars.getSessionValue("buttonDFC78024B1F54CBB95DC73425BA6687F.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonExplodeDFC78024B1F54CBB95DC73425BA6687F(response, vars, strC_OrderLine_ID, strexplode, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONExplodeDFC78024B1F54CBB95DC73425BA6687F")) {
        String strC_OrderLine_ID = vars.getGlobalVariable("inpKey", windowId + "|C_OrderLine_ID", "");
        @SuppressWarnings("unused")
        String strexplode = vars.getStringParameter("inpexplode");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "DFC78024B1F54CBB95DC73425BA6687F", (("C_OrderLine_ID".equalsIgnoreCase("AD_Language"))?"0":strC_OrderLine_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
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
      String strPC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPC_Order_ID);
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
  private LinesData getEditVariables(Connection con, VariablesSecureApp vars, String strPC_Order_ID) throws IOException,ServletException {
    LinesData data = new LinesData();
    ServletException ex = null;
    try {
   try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.mProductId = vars.getRequiredGlobalVariable("inpmProductId", windowId + "|M_Product_ID");     data.mProductIdr = vars.getStringParameter("inpmProductId_R");     data.mProductCategoryId = vars.getStringParameter("inpmProductCategoryId");     data.mProductCategoryIdr = vars.getStringParameter("inpmProductCategoryId_R");     data.emEpcgMake = vars.getStringParameter("inpemEpcgMake");    try {   data.qtyordered = vars.getRequiredNumericParameter("inpqtyordered");  } catch (ServletException paramEx) { ex = paramEx; }     data.mInoutlineId = vars.getStringParameter("inpmInoutlineId");     data.mAttributesetinstanceId = vars.getRequestGlobalVariable("inpmAttributesetinstanceId", windowId + "|M_AttributeSetInstance_ID");     data.cUomId = vars.getRequiredGlobalVariable("inpcUomId", windowId + "|C_UOM_ID");     data.cUomIdr = vars.getStringParameter("inpcUomId_R");     data.cReturnReasonId = vars.getStringParameter("inpcReturnReasonId");    try {   data.priceactual = vars.getRequiredNumericParameter("inppriceactual");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrPrice = vars.getRequiredNumericParameter("inpemRcfrPrice");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grossUnitPrice = vars.getNumericParameter("inpgrossUnitPrice");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.linenetamt = vars.getRequiredNumericParameter("inplinenetamt");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.lineGrossAmount = vars.getNumericParameter("inplineGrossAmount");  } catch (ServletException paramEx) { ex = paramEx; }     data.cTaxId = vars.getRequiredStringParameter("inpcTaxId");     data.cTaxIdr = vars.getStringParameter("inpcTaxId_R");    try {   data.pricelist = vars.getRequiredNumericParameter("inppricelist");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grosspricelist = vars.getNumericParameter("inpgrosspricelist");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.discount = vars.getNumericParameter("inpdiscount");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrFreight = vars.getRequiredNumericParameter("inpemRcfrFreight");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrInsurance = vars.getRequiredNumericParameter("inpemRcfrInsurance");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrPnf = vars.getRequiredNumericParameter("inpemRcfrPnf");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrDisco = vars.getNumericParameter("inpemRcfrDisco");  } catch (ServletException paramEx) { ex = paramEx; }     data.description = vars.getStringParameter("inpdescription");    try {   data.taxbaseamt = vars.getNumericParameter("inptaxbaseamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.iseditlinenetamt = vars.getStringParameter("inpiseditlinenetamt", "N");     data.cancelpricead = vars.getStringParameter("inpcancelpricead", "N");    try {   data.pricestd = vars.getRequiredNumericParameter("inppricestd");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.grosspricestd = vars.getRequiredNumericParameter("inpgrosspricestd");  } catch (ServletException paramEx) { ex = paramEx; }     data.mWarehouseId = vars.getRequiredGlobalVariable("inpmWarehouseId", windowId + "|M_Warehouse_ID");    try {   data.qtyreserved = vars.getRequiredNumericParameter("inpqtyreserved");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.qtydelivered = vars.getRequiredNumericParameter("inpqtydelivered");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.qtyinvoiced = vars.getRequiredNumericParameter("inpqtyinvoiced");  } catch (ServletException paramEx) { ex = paramEx; }     data.mShipperId = vars.getRequestGlobalVariable("inpmShipperId", windowId + "|M_Shipper_ID");     data.dateordered = vars.getRequiredGlobalVariable("inpdateordered", windowId + "|DateOrdered");     data.datepromised = vars.getRequestGlobalVariable("inpdatepromised", windowId + "|DatePromised");     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");    try {   data.freightamt = vars.getRequiredNumericParameter("inpfreightamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.cChargeId = vars.getStringParameter("inpcChargeId");     data.cBpartnerLocationId = vars.getRequestGlobalVariable("inpcBpartnerLocationId", windowId + "|C_BPartner_Location_ID");    try {   data.quantityorder = vars.getNumericParameter("inpquantityorder");  } catch (ServletException paramEx) { ex = paramEx; }     data.mProductUomId = vars.getStringParameter("inpmProductUomId");     data.managePrereservation = vars.getStringParameter("inpmanagePrereservation");     data.quotationlineId = vars.getStringParameter("inpquotationlineId");     data.adOrgId = vars.getRequiredStringParameter("inpadOrgId");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.aAssetId = vars.getStringParameter("inpaAssetId");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.explode = vars.getStringParameter("inpexplode");     data.bomParentId = vars.getStringParameter("inpbomParentId");     data.isdescription = vars.getStringParameter("inpisdescription", "N");     data.soResStatus = vars.getStringParameter("inpsoResStatus");     data.cOrderDiscountId = vars.getStringParameter("inpcOrderDiscountId");     data.refOrderlineId = vars.getStringParameter("inprefOrderlineId");     data.sResourceassignmentId = vars.getStringParameter("inpsResourceassignmentId");     data.manageReservation = vars.getStringParameter("inpmanageReservation");    try {   data.chargeamt = vars.getNumericParameter("inpchargeamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.datedelivered = vars.getStringParameter("inpdatedelivered");     data.cOrderId = vars.getRequiredGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.cOrderlineId = vars.getRequestGlobalVariable("inpcOrderlineId", windowId + "|C_OrderLine_ID");     data.cCurrencyId = vars.getRequiredGlobalVariable("inpcCurrencyId", windowId + "|C_Currency_ID");    try {   data.pricelimit = vars.getRequiredNumericParameter("inppricelimit");  } catch (ServletException paramEx) { ex = paramEx; }     data.dateinvoiced = vars.getStringParameter("inpdateinvoiced");     data.directship = vars.getStringParameter("inpdirectship", "N");     data.createReservation = vars.getStringParameter("inpcreateReservation");     data.mWarehouseRuleId = vars.getStringParameter("inpmWarehouseRuleId");     data.mOfferId = vars.getStringParameter("inpmOfferId"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.cOrderId = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID");


          vars.getGlobalVariable("inphasseconduom", windowId + "|HASSECONDUOM", "");
          vars.getGlobalVariable("inpattrsetvaluetype", windowId + "|ATTRSETVALUETYPE", "");
          vars.getGlobalVariable("inpprocessed", windowId + "|Processed", "");
          vars.getGlobalVariable("inpattributeset", windowId + "|ATTRIBUTESET", "");
          vars.getGlobalVariable("inpusesalternate", windowId + "|UsesAlternate", "");
          vars.getGlobalVariable("inpisbom", windowId + "|isBOM", "");
          vars.getGlobalVariable("inpisstocked", windowId + "|IsStocked", "");
          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
          vars.getGlobalVariable("inpgrossprice", windowId + "|GROSSPRICE", "");
          vars.getGlobalVariable("inpparentAdOrg", windowId + "|Parent_AD_Org", "");
          vars.getGlobalVariable("inpposted", windowId + "|Posted", "");
    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPC_Order_ID) throws IOException,ServletException {
      
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Order_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|DateOrdered", data[0].dateordered);    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].cBpartnerId);    vars.setSessionValue(windowId + "|C_BPartner_Location_ID", data[0].cBpartnerLocationId);    vars.setSessionValue(windowId + "|M_Warehouse_ID", data[0].mWarehouseId);    vars.setSessionValue(windowId + "|DatePromised", data[0].datepromised);    vars.setSessionValue(windowId + "|M_PriceList_ID", data[0].mPricelistId);    vars.setSessionValue(windowId + "|DocStatus", data[0].docstatus);    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].cCurrencyId);    vars.setSessionValue(windowId + "|C_DocType_ID", data[0].cDoctypeId);    vars.setSessionValue(windowId + "|DeliveryViaRule", data[0].deliveryviarule);    vars.setSessionValue(windowId + "|M_Shipper_ID", data[0].mShipperId);    vars.setSessionValue(windowId + "|FreightCostRule", data[0].freightcostrule);    vars.setSessionValue(windowId + "|Posted", data[0].posted);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|C_Order_ID", data[0].cOrderId);    vars.setSessionValue(windowId + "|Processed", data[0].processed);    vars.setSessionValue(windowId + "|IsSOTrx", data[0].issotrx);
      vars.setSessionValue(windowId + "|C_Order_ID", strPC_Order_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|ORDERTYPE", HeaderData.selectAux8(this, strPC_Order_ID));
      
      vars.setSessionValue(windowId + "|DOCBASETYPE", HeaderData.selectAux17F5DEA4A5254BDDA77560A7D67667B6(this, Utility.getContext(this, vars, "c_doctypetarget_id", "181")));
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strC_OrderLine_ID) throws ServletException {
    String strPC_Order_ID = LinesData.selectParentID(this, strC_OrderLine_ID);
    if (strPC_Order_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strC_OrderLine_ID);
      throw new ServletException("Parent record not found");
    }
    return strPC_Order_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|M_Product_ID", data[0].getField("mProductId"));    vars.setSessionValue(windowId + "|M_AttributeSetInstance_ID", data[0].getField("mAttributesetinstanceId"));    vars.setSessionValue(windowId + "|C_UOM_ID", data[0].getField("cUomId"));    vars.setSessionValue(windowId + "|M_Warehouse_ID", data[0].getField("mWarehouseId"));    vars.setSessionValue(windowId + "|M_Shipper_ID", data[0].getField("mShipperId"));    vars.setSessionValue(windowId + "|DateOrdered", data[0].getField("dateordered"));    vars.setSessionValue(windowId + "|DatePromised", data[0].getField("datepromised"));    vars.setSessionValue(windowId + "|C_BPartner_Location_ID", data[0].getField("cBpartnerLocationId"));    vars.setSessionValue(windowId + "|C_OrderLine_ID", data[0].getField("cOrderlineId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|C_Order_ID", data[0].getField("cOrderId"));    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].getField("cCurrencyId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPC_Order_ID) throws IOException,ServletException {
      LinesData[] data = LinesData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Order_ID, vars.getStringParameter("inpcOrderlineId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPC_Order_ID, String strC_OrderLine_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamC_Order_ID = vars.getSessionValue(tabId + "|paramC_Order_ID");
String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamLineNetAmt = vars.getSessionValue(tabId + "|paramLineNetAmt");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");
String strParamLineNetAmt_f = vars.getSessionValue(tabId + "|paramLineNetAmt_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamC_Order_ID) && ("").equals(strParamLine) && ("").equals(strParamLineNetAmt) && ("").equals(strParamLine_f) && ("").equals(strParamLineNetAmt_f)) || !(("").equals(strParamC_Order_ID) || ("%").equals(strParamC_Order_ID))  || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamLineNetAmt) || ("%").equals(strParamLineNetAmt))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f))  || !(("").equals(strParamLineNetAmt_f) || ("%").equals(strParamLineNetAmt_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strC_OrderLine_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strC_OrderLine_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseOrder/Lines_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Lines", false, "document.frmMain.inpcOrderlineId", "grid", "..", "".equals("Y"), "PurchaseOrder", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPC_Order_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("3402", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "cOrderlineId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines_Relation.html", "PurchaseOrder", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", LinesData.selectParent(this, strPC_Order_ID));
    else xmlDocument.setParameter("parent", LinesData.selectParentTrl(this, strPC_Order_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strC_OrderLine_ID, String strPC_Order_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " C_OrderLine.Line";
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
    String strParamC_Order_ID = vars.getSessionValue(tabId + "|paramC_Order_ID");
String strParamLine = vars.getSessionValue(tabId + "|paramLine");
String strParamLineNetAmt = vars.getSessionValue(tabId + "|paramLineNetAmt");
String strParamLine_f = vars.getSessionValue(tabId + "|paramLine_f");
String strParamLineNetAmt_f = vars.getSessionValue(tabId + "|paramLineNetAmt_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamC_Order_ID) && ("").equals(strParamLine) && ("").equals(strParamLineNetAmt) && ("").equals(strParamLine_f) && ("").equals(strParamLineNetAmt_f)) || !(("").equals(strParamC_Order_ID) || ("%").equals(strParamC_Order_ID))  || !(("").equals(strParamLine) || ("%").equals(strParamLine))  || !(("").equals(strParamLineNetAmt) || ("%").equals(strParamLineNetAmt))  || !(("").equals(strParamLine_f) || ("%").equals(strParamLine_f))  || !(("").equals(strParamLineNetAmt_f) || ("%").equals(strParamLineNetAmt_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = LinesData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Order_ID, strC_OrderLine_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strC_OrderLine_ID.equals("") && (data == null || data.length==0)) {
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
      if (dataField.getField("cOrderlineId") == null || dataField.getField("cOrderlineId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strHASSECONDUOM = LinesData.selectAux800000(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|HASSECONDUOM", strHASSECONDUOM);
        String strATTRSETVALUETYPE = LinesData.selectAux10444AA4DB1D49D7A621F1EF47787122(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|ATTRSETVALUETYPE", strATTRSETVALUETYPE);
        String strProcessed = LinesData.selectAux1F6072F3CE0F43F6AF71874398914072(this, strPC_Order_ID);
    vars.setSessionValue(windowId + "|Processed", strProcessed);
        String strATTRIBUTESET = LinesData.selectAux2B0D9093C42A41E3AB05FEF0A64DBEA5(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|ATTRIBUTESET", strATTRIBUTESET);
        String strUsesAlternate = LinesData.selectAux3648B8ACFDE44EF4A649B7933D9DBD97(this, ((dataField!=null)?dataField.getField("cTaxId"):((data==null || data.length==0)?"":data[0].cTaxId)));
    vars.setSessionValue(windowId + "|UsesAlternate", strUsesAlternate);
        String strisBOM = LinesData.selectAux54B597A45774438696EC38DB00010B67(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|isBOM", strisBOM);
        String strIsStocked = LinesData.selectAux9039E3F1B99F4D76855A89FA73B4F05F(this, ((dataField!=null)?dataField.getField("mProductId"):((data==null || data.length==0)?"":data[0].mProductId)));
    vars.setSessionValue(windowId + "|IsStocked", strIsStocked);
        String strDOCBASETYPE = LinesData.selectAux9E0F5BB107EE463B8E4F18395952D8F2(this, strPC_Order_ID);
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
        String strGROSSPRICE = LinesData.selectAuxE0DB11E8D0384B29A64B251E2262A541(this, Utility.getContext(this, vars, "M_PRICELIST_ID", "181"));
    vars.setSessionValue(windowId + "|GROSSPRICE", strGROSSPRICE);
        String strParent_AD_Org = LinesData.selectAuxE3CF65D49DB84E488645E425A7C7C0B2(this, strPC_Order_ID);
    vars.setSessionValue(windowId + "|Parent_AD_Org", strParent_AD_Org);
        String strPosted = LinesData.selectAuxEACCFCC1A36E4D379187291CF600E8FF(this, strPC_Order_ID);
    vars.setSessionValue(windowId + "|Posted", strPosted);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars, strPC_Order_ID);
        data = LinesData.set(strPC_Order_ID, Utility.getDefault(this, vars, "Line_Gross_Amount", "0", "181", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Price", "0", "181", "0", dataField), Utility.getDefault(this, vars, "CANCELPRICEAD", "N", "181", "N", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Insurance", "0", "181", "0", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_Client_ID@", "181", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "181", "", dataField), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "181", "", dataField), LinesData.selectDef2210_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "181", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "181", "", dataField), LinesData.selectDef2212_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "181", "", dataField)), LinesData.selectDef2214(this, strPC_Order_ID), Utility.getDefault(this, vars, "DateOrdered", "@DateOrdered@", "181", "", dataField), Utility.getDefault(this, vars, "DatePromised", "@DatePromised@", "181", "", dataField), Utility.getDefault(this, vars, "DateDelivered", "", "181", "", dataField), Utility.getDefault(this, vars, "DateInvoiced", "", "181", "", dataField), Utility.getDefault(this, vars, "Description", "", "181", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "181", "", dataField), LinesData.selectDef2221_2(this,  vars.getLanguage(), Utility.getDefault(this, vars, "M_Product_ID", "", "181", "", dataField)), Utility.getDefault(this, vars, "C_UOM_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "M_Warehouse_ID", "@M_Warehouse_ID@", "181", "", dataField), Utility.getDefault(this, vars, "QtyOrdered", "1", "181", "0", dataField), Utility.getDefault(this, vars, "QtyReserved", "", "181", "0", dataField), Utility.getDefault(this, vars, "QtyDelivered", "", "181", "0", dataField), Utility.getDefault(this, vars, "QtyInvoiced", "", "181", "0", dataField), Utility.getDefault(this, vars, "M_Shipper_ID", "@M_Shipper_ID@", "181", "", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "@C_Currency_ID@", "181", "", dataField), Utility.getDefault(this, vars, "PriceList", "", "181", "0", dataField), Utility.getDefault(this, vars, "PriceActual", "", "181", "0", dataField), Utility.getDefault(this, vars, "C_Tax_ID", "", "181", "", dataField), LinesData.selectDef2764(this, strPC_Order_ID), Utility.getDefault(this, vars, "DirectShip", "", "181", "N", dataField), Utility.getDefault(this, vars, "FreightAmt", "", "181", "0", dataField), Utility.getDefault(this, vars, "C_Charge_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "ChargeAmt", "0", "181", "", dataField), Utility.getDefault(this, vars, "C_BPartner_Location_ID", "@C_BPartner_Location_ID@", "181", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Disco", "0", "181", "", dataField), Utility.getDefault(this, vars, "LineNetAmt", "", "181", "0", dataField), Utility.getDefault(this, vars, "PriceLimit", "", "181", "0", dataField), Utility.getDefault(this, vars, "Discount", "0", "181", "", dataField), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Return_Reason_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "S_ResourceAssignment_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Order_Discount_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "A_Asset_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "Ref_OrderLine_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "Iseditlinenetamt", "N", "181", "N", dataField), Utility.getDefault(this, vars, "Taxbaseamt", "", "181", "", dataField), Utility.getDefault(this, vars, "M_Product_Uom_Id", "", "181", "", dataField), Utility.getDefault(this, vars, "QuantityOrder", "", "181", "", dataField), Utility.getDefault(this, vars, "M_Offer_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "PriceStd", "0", "181", "0", dataField), Utility.getDefault(this, vars, "M_AttributeSetInstance_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Pnf", "0", "181", "0", dataField), Utility.getDefault(this, vars, "IsDescription", "N", "181", "N", dataField), Utility.getDefault(this, vars, "BOM_Parent_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "grosspricestd", "0", "181", "0", dataField), Utility.getDefault(this, vars, "Explode", "", "181", "N", dataField), Utility.getDefault(this, vars, "M_Inoutline_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "181", "", dataField), LinesData.selectDefB2023461A08649D58E5D144D4FC2C507_3(this, Utility.getDefault(this, vars, "C_Project_ID", "", "181", "", dataField)), Utility.getDefault(this, vars, "GrossPriceList", "", "181", "", dataField), Utility.getDefault(this, vars, "M_Warehouse_Rule_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "Create_Reservation", "", "181", "", dataField), Utility.getDefault(this, vars, "SO_Res_Status", "", "181", "", dataField), Utility.getDefault(this, vars, "Manage_Reservation", "", "181", "N", dataField), Utility.getDefault(this, vars, "Manage_Prereservation", "", "181", "N", dataField), Utility.getDefault(this, vars, "EM_Epcg_Make", "", "181", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Freight", "0", "181", "0", dataField), Utility.getDefault(this, vars, "M_Product_Category_ID", "", "181", "", dataField), Utility.getDefault(this, vars, "Quotationline_ID", "", "181", "", dataField), LinesData.selectDefE9612846B6F5472A8F64CF729E0B25AE_4(this, Utility.getDefault(this, vars, "Quotationline_ID", "", "181", "", dataField)), Utility.getDefault(this, vars, "Gross_Unit_Price", "0", "181", "", dataField));
        
      }
     }
      
    String currentPOrg=HeaderData.selectOrg(this, strPC_Order_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseOrder/Lines_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/PurchaseOrder/Lines_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Lines", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpcOrderlineId", "", "..", "".equals("Y"), "PurchaseOrder", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strC_OrderLine_ID), !hasReadOnlyAccess);
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
      // if (!strC_OrderLine_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Lines_Relation.html", "PurchaseOrder", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Lines_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("HASSECONDUOM", strHASSECONDUOM);
        xmlDocument.setParameter("ATTRSETVALUETYPE", strATTRSETVALUETYPE);
        xmlDocument.setParameter("Processed", strProcessed);
        xmlDocument.setParameter("ATTRIBUTESET", strATTRIBUTESET);
        xmlDocument.setParameter("UsesAlternate", strUsesAlternate);
        xmlDocument.setParameter("isBOM", strisBOM);
        xmlDocument.setParameter("IsStocked", strIsStocked);
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
        xmlDocument.setParameter("GROSSPRICE", strGROSSPRICE);
        xmlDocument.setParameter("Parent_AD_Org", strParent_AD_Org);
        xmlDocument.setParameter("Posted", strPosted);
    
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
comboTableData = new ComboTableData(vars, this, "19", "M_Product_Category_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mProductCategoryId"):dataField.getField("mProductCategoryId")));
xmlDocument.setData("reportM_Product_Category_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonQtyOrdered", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_UOM_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomId"):dataField.getField("cUomId")));
xmlDocument.setData("reportC_UOM_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonPriceActual", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Price", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGross_Unit_Price", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonLineNetAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonLine_Gross_Amount", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "C_Tax_ID", "158", "5BEA56148DC74215A63927481BDC0B63", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cTaxId"):dataField.getField("cTaxId")));
xmlDocument.setData("reportC_Tax_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonPriceList", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonGrossPriceList", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDiscount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Freight", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Insurance", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Pnf", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Disco", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTaxbaseamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttongrosspricestd", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPriceStd", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQtyReserved", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQtyDelivered", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQtyInvoiced", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("DatePromised_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonFreightAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQuantityOrder", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Manage_Prereservation_BTNname", Utility.getButtonName(this, vars, "CE883E35999D707CE040007F01002B1E", "Manage_Prereservation_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalManage_Prereservation = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Manage_Prereservation_Modal", modalManage_Prereservation?"true":"false");
String userOrgList = "";
if (editableTab) 
  userOrgList= Utility.getReferenceableOrg(this, vars, currentPOrg, windowId, accesslevel); //referenceable from parent org, only the writeable orgs
else 
  userOrgList=currentOrg;
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "53AE60A473D2460D8663D7A1BC5BA732", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Explode_BTNname", Utility.getButtonName(this, vars, "D523D045C0050880E040007F0100468E", "Explode_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalExplode = org.openbravo.erpCommon.utility.Utility.isModalProcess("DFC78024B1F54CBB95DC73425BA6687F"); 
xmlDocument.setParameter("Explode_Modal", modalExplode?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("buttonPriceLimit", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("DateInvoiced_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonChargeAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("DateDelivered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
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

    private void printPageButtonExplodeDFC78024B1F54CBB95DC73425BA6687F(HttpServletResponse response, VariablesSecureApp vars, String strC_OrderLine_ID, String strexplode, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DFC78024B1F54CBB95DC73425BA6687F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ExplodeDFC78024B1F54CBB95DC73425BA6687F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_OrderLine_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Lines_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "DFC78024B1F54CBB95DC73425BA6687F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("DFC78024B1F54CBB95DC73425BA6687F");
        vars.removeMessage("DFC78024B1F54CBB95DC73425BA6687F");
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
      String result = "var strshowMRPandProductionFields=\"" +Utility.getContext(this, vars, "showMRPandProductionFields", windowId) + "\";\nvar strDOCSTATUS=\"" +Utility.getContext(this, vars, "DOCSTATUS", windowId) + "\";\nvar strStockReservations=\"" +Utility.getContext(this, vars, "StockReservations", windowId) + "\";\nvar strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar str$Element_AS=\"" +Utility.getContext(this, vars, "$Element_AS", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPC_Order_ID) throws IOException, ServletException {
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
            data = getEditVariables(con, vars, strPC_Order_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.cOrderlineId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (LinesData.getCurrentDBTimestamp(this, data.cOrderlineId).equals(
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
                    data.cOrderlineId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|C_OrderLine_ID", data.cOrderlineId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Lines. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
