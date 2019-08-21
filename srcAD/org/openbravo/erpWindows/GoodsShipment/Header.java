
package org.openbravo.erpWindows.GoodsShipment;


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
  
  private static final String windowId = "169";
  private static final String tabId = "257";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 1;
  private static final String moduleId = "0";
  
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
        String strmInoutId = request.getParameter("inpmInoutId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strmInoutId.equals(""))
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

      String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strM_InOut_ID.equals("")) strM_InOut_ID = firstElement(vars, tableSQL);
          if (strM_InOut_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strM_InOut_ID, tableSQL);

      else printPageDataSheet(response, vars, strM_InOut_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strM_InOut_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strM_InOut_ID.equals("")) strM_InOut_ID = vars.getRequiredGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
      else vars.setSessionValue(windowId + "|M_InOut_ID", strM_InOut_ID);
      
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      printPageEdit(response, request, vars, false, strM_InOut_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|Header.view");
      String strM_InOut_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strM_InOut_ID = firstElement(vars, tableSQL);
          if (strM_InOut_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strM_InOut_ID.equals("")) strM_InOut_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strM_InOut_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamAD_Org_ID", tabId + "|paramAD_Org_ID");
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");
vars.getRequestGlobalVariable("inpParamPOReference", tabId + "|paramPOReference");
vars.getRequestGlobalVariable("inpParamMovementDate", tabId + "|paramMovementDate");
vars.getRequestGlobalVariable("inpParamC_BPartner_ID", tabId + "|paramC_BPartner_ID");
vars.getRequestGlobalVariable("inpParamM_Warehouse_ID", tabId + "|paramM_Warehouse_ID");
vars.getRequestGlobalVariable("inpParamDocStatus", tabId + "|paramDocStatus");
vars.getRequestGlobalVariable("inpParamMovementDate_f", tabId + "|paramMovementDate_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|M_InOut_ID");
      String strM_InOut_ID="";

      String strView = vars.getSessionValue(tabId + "|Header.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strM_InOut_ID = firstElement(vars, tableSQL);
        if (strM_InOut_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Header.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strM_InOut_ID, tableSQL);

      else printPageDataSheet(response, vars, strM_InOut_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "RELATION");
      printPageDataSheet(response, vars, strM_InOut_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
      vars.setSessionValue(tabId + "|Header.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strM_InOut_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strM_InOut_ID = vars.getRequiredStringParameter("inpmInoutId");
      
      String strNext = nextElement(vars, strM_InOut_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strM_InOut_ID = vars.getRequiredStringParameter("inpmInoutId");
      
      String strPrevious = previousElement(vars, strM_InOut_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|Header.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

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
      vars.removeSessionValue(windowId + "|M_InOut_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|Header.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Header.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|M_InOut_ID");

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

      String strM_InOut_ID = vars.getRequiredGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");
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
          String strNext = nextElement(vars, strM_InOut_ID, tableSQL);
          vars.setSessionValue(windowId + "|M_InOut_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strM_InOut_ID = vars.getRequiredStringParameter("inpmInoutId");
      //HeaderData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = HeaderData.delete(this, strM_InOut_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|mInoutId");
        vars.setSessionValue(tabId + "|Header.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());




    } else if (vars.commandIn("BUTTONCreateFrom")) {
        String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
        String strTableId = "319";
        String strProcessId = "";
        String strDateInvoiced = vars.getStringParameter("inpdateinvoiced", "");
        String strBPartnerLocation = vars.getStringParameter("inpcBpartnerLocationId", "");
        String strPriceList = vars.getStringParameter("inpmPricelistId", "");
        String strBPartner = vars.getStringParameter("inpcBpartnerId", "");
        String strBankAccount = vars.getStringParameter("inpcBankaccountId");
        String strStatementDate = vars.getStringParameter("inpstatementdate");
        String strOrg = vars.getStringParameter("inpadOrgId");
        String strClient = vars.getStringParameter("inpadClientId");
        String strIsreceipt = vars.getStringParameter("inpisreceipt");
        log4j.debug("Loading CreateFrom button in table: " + strTableId);
        vars.setSessionValue("CreateFrom|key", strM_InOut_ID);
        vars.setSessionValue("CreateFrom|tableId", strTableId);
        vars.setSessionValue("CreateFrom|tabId", tabId);
        vars.setSessionValue("CreateFrom|processId", strProcessId);
        vars.setSessionValue("CreateFrom|path", strDireccion + request.getServletPath());
        vars.setSessionValue("CreateFrom|bpartnerLocation", strBPartnerLocation);
        vars.setSessionValue("CreateFrom|dateInvoiced", strDateInvoiced);
        vars.setSessionValue("CreateFrom|pricelist", strPriceList);
        vars.setSessionValue("CreateFrom|bpartner", strBPartner);
        vars.setSessionValue("CreateFrom|windowId", windowId);
        vars.setSessionValue("CreateFrom|bankAccount", strBankAccount);
        vars.setSessionValue("CreateFrom|statementDate", strStatementDate);
        vars.setSessionValue("CreateFrom|adOrgId", strOrg);
        vars.setSessionValue("CreateFrom|isreceipt", strIsreceipt);
        vars.setSessionValue("CreateFrom|tabName", "Header");
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          response.sendRedirect(strDireccion + "/ad_actionButton/CreateFrom.html");
        }

    } else if (vars.commandIn("BUTTONPosted")) {
        String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
        String strTableId = "319";
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
          vars.setSessionValue("Posted|key", strM_InOut_ID);
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
  private HeaderData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    HeaderData data = new HeaderData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.mConditionGoodsId = vars.getStringParameter("inpmConditionGoodsId");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.mWarehouseId = vars.getRequiredGlobalVariable("inpmWarehouseId", windowId + "|M_Warehouse_ID");     data.cBpartnerId = vars.getRequiredGlobalVariable("inpcBpartnerId", windowId + "|C_BPartner_ID");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.cBpartnerLocationId = vars.getRequiredStringParameter("inpcBpartnerLocationId");     data.cBpartnerLocationIdr = vars.getStringParameter("inpcBpartnerLocationId_R");     data.description = vars.getStringParameter("inpdescription");     data.emRcfrRatetype = vars.getRequiredStringParameter("inpemRcfrRatetype");     data.emRcfrRatetyper = vars.getStringParameter("inpemRcfrRatetype_R");    try {   data.emRcfrFreighth = vars.getRequiredNumericParameter("inpemRcfrFreighth");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrFreightpaid = vars.getRequiredNumericParameter("inpemRcfrFreightpaid");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcfrInsuranceh = vars.getRequiredNumericParameter("inpemRcfrInsuranceh");  } catch (ServletException paramEx) { ex = paramEx; }     data.movementdate = vars.getRequiredStringParameter("inpmovementdate");     data.deliveryLocationId = vars.getStringParameter("inpdeliveryLocationId");     data.deliveryLocationIdr = vars.getStringParameter("inpdeliveryLocationId_R");     data.docstatus = vars.getRequiredGlobalVariable("inpdocstatus", windowId + "|DocStatus");     data.adUserId = vars.getStringParameter("inpadUserId");     data.dateacct = vars.getRequiredStringParameter("inpdateacct");     data.cOrderId = vars.getStringParameter("inpcOrderId");     data.cOrderIdr = vars.getStringParameter("inpcOrderId_R");     data.poreference = vars.getStringParameter("inpporeference");     data.dateordered = vars.getStringParameter("inpdateordered");     data.salesrepId = vars.getStringParameter("inpsalesrepId");     data.createfrom = vars.getStringParameter("inpcreatefrom");     data.processGoodsJava = vars.getStringParameter("inpprocessGoodsJava");     data.docaction = vars.getRequiredStringParameter("inpdocaction");     data.posted = vars.getRequiredGlobalVariable("inpposted", windowId + "|Posted");     data.deliveryrule = vars.getRequiredStringParameter("inpdeliveryrule");     data.deliveryviarule = vars.getRequiredStringParameter("inpdeliveryviarule");    try {   data.freightamt = vars.getNumericParameter("inpfreightamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.freightcostrule = vars.getRequiredStringParameter("inpfreightcostrule");     data.mShipperId = vars.getStringParameter("inpmShipperId");     data.priorityrule = vars.getRequiredStringParameter("inppriorityrule");     data.pickdate = vars.getStringParameter("inppickdate");     data.trackingno = vars.getStringParameter("inptrackingno");    try {   data.nopackages = vars.getNumericParameter("inpnopackages");  } catch (ServletException paramEx) { ex = paramEx; }     data.movementtype = vars.getRequiredStringParameter("inpmovementtype");     data.cChargeId = vars.getStringParameter("inpcChargeId");    try {   data.chargeamt = vars.getNumericParameter("inpchargeamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.shipdate = vars.getStringParameter("inpshipdate");     data.cActivityId = vars.getStringParameter("inpcActivityId");     data.cCampaignId = vars.getStringParameter("inpcCampaignId");     data.adOrgtrxId = vars.getStringParameter("inpadOrgtrxId");     data.mFreightcategoryId = vars.getStringParameter("inpmFreightcategoryId");     data.calculateFreight = vars.getStringParameter("inpcalculateFreight");     data.freightCurrencyId = vars.getRequestGlobalVariable("inpfreightCurrencyId", windowId + "|Freight_Currency_ID");     data.cProjectId = vars.getStringParameter("inpcProjectId");     data.cProjectIdr = vars.getStringParameter("inpcProjectId_R");     data.cCostcenterId = vars.getStringParameter("inpcCostcenterId");     data.aAssetId = vars.getStringParameter("inpaAssetId");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");    try {   data.emRcobCommission = vars.getNumericParameter("inpemRcobCommission");  } catch (ServletException paramEx) { ex = paramEx; }     data.isprinted = vars.getStringParameter("inpisprinted", "N");     data.issotrx = vars.getRequiredInputGlobalVariable("inpissotrx", windowId + "|IsSOTrx", "N");     data.dateprinted = vars.getStringParameter("inpdateprinted");     data.processed = vars.getStringParameter("inpprocessed", "N");     data.mInoutId = vars.getRequestGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID");     data.generateto = vars.getStringParameter("inpgenerateto");     data.generatelines = vars.getStringParameter("inpgeneratelines");     data.updatelines = vars.getStringParameter("inpupdatelines");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.processing = vars.getStringParameter("inpprocessing", "N");     data.rmReceiptPickedit = vars.getStringParameter("inprmReceiptPickedit");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.islogistic = vars.getStringParameter("inpislogistic", "N");     data.cInvoiceId = vars.getStringParameter("inpcInvoiceId");     data.rmShipmentPickedit = vars.getStringParameter("inprmShipmentPickedit"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



          vars.getGlobalVariable("inpislogisticaux", windowId + "|ISLOGISTICAUX", "");
          vars.getGlobalVariable("inpdocbasetype", windowId + "|DOCBASETYPE", "");
    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "M_InOut", "", data.cDoctypeId, false, true);

    
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|M_Warehouse_ID", data[0].getField("mWarehouseId"));    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].getField("cBpartnerId"));    vars.setSessionValue(windowId + "|DocStatus", data[0].getField("docstatus"));    vars.setSessionValue(windowId + "|Posted", data[0].getField("posted"));    vars.setSessionValue(windowId + "|Freight_Currency_ID", data[0].getField("freightCurrencyId"));    vars.setSessionValue(windowId + "|IsSOTrx", data[0].getField("issotrx"));    vars.setSessionValue(windowId + "|M_InOut_ID", data[0].getField("mInoutId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpmInoutId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strM_InOut_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamAD_Org_ID = vars.getSessionValue(tabId + "|paramAD_Org_ID");
String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamPOReference = vars.getSessionValue(tabId + "|paramPOReference");
String strParamMovementDate = vars.getSessionValue(tabId + "|paramMovementDate");
String strParamC_BPartner_ID = vars.getSessionValue(tabId + "|paramC_BPartner_ID");
String strParamM_Warehouse_ID = vars.getSessionValue(tabId + "|paramM_Warehouse_ID");
String strParamDocStatus = vars.getSessionValue(tabId + "|paramDocStatus");
String strParamMovementDate_f = vars.getSessionValue(tabId + "|paramMovementDate_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Org_ID) && ("").equals(strParamDocumentNo) && ("").equals(strParamPOReference) && ("").equals(strParamMovementDate) && ("").equals(strParamC_BPartner_ID) && ("").equals(strParamM_Warehouse_ID) && ("").equals(strParamDocStatus) && ("").equals(strParamMovementDate_f)) || !(("").equals(strParamAD_Org_ID) || ("%").equals(strParamAD_Org_ID))  || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamPOReference) || ("%").equals(strParamPOReference))  || !(("").equals(strParamMovementDate) || ("%").equals(strParamMovementDate))  || !(("").equals(strParamC_BPartner_ID) || ("%").equals(strParamC_BPartner_ID))  || !(("").equals(strParamM_Warehouse_ID) || ("%").equals(strParamM_Warehouse_ID))  || !(("").equals(strParamDocStatus) || ("%").equals(strParamDocStatus))  || !(("").equals(strParamMovementDate_f) || ("%").equals(strParamMovementDate_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strM_InOut_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strM_InOut_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GoodsShipment/Header_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Header", false, "document.frmMain.inpmInoutId", "grid", "../shipments/print.html", "N".equals("Y"), "GoodsShipment", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "mInoutId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "GoodsShipment", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strM_InOut_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " M_InOut.DocumentNo DESC";
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
    String strParamAD_Org_ID = vars.getSessionValue(tabId + "|paramAD_Org_ID");
String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");
String strParamPOReference = vars.getSessionValue(tabId + "|paramPOReference");
String strParamMovementDate = vars.getSessionValue(tabId + "|paramMovementDate");
String strParamC_BPartner_ID = vars.getSessionValue(tabId + "|paramC_BPartner_ID");
String strParamM_Warehouse_ID = vars.getSessionValue(tabId + "|paramM_Warehouse_ID");
String strParamDocStatus = vars.getSessionValue(tabId + "|paramDocStatus");
String strParamMovementDate_f = vars.getSessionValue(tabId + "|paramMovementDate_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAD_Org_ID) && ("").equals(strParamDocumentNo) && ("").equals(strParamPOReference) && ("").equals(strParamMovementDate) && ("").equals(strParamC_BPartner_ID) && ("").equals(strParamM_Warehouse_ID) && ("").equals(strParamDocStatus) && ("").equals(strParamMovementDate_f)) || !(("").equals(strParamAD_Org_ID) || ("%").equals(strParamAD_Org_ID))  || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo))  || !(("").equals(strParamPOReference) || ("%").equals(strParamPOReference))  || !(("").equals(strParamMovementDate) || ("%").equals(strParamMovementDate))  || !(("").equals(strParamC_BPartner_ID) || ("%").equals(strParamC_BPartner_ID))  || !(("").equals(strParamM_Warehouse_ID) || ("%").equals(strParamM_Warehouse_ID))  || !(("").equals(strParamDocStatus) || ("%").equals(strParamDocStatus))  || !(("").equals(strParamMovementDate_f) || ("%").equals(strParamMovementDate_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strM_InOut_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strM_InOut_ID.equals("") && (data == null || data.length==0)) {
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
      if (dataField.getField("mInoutId") == null || dataField.getField("mInoutId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strISLOGISTICAUX = "N";
    vars.setSessionValue(windowId + "|ISLOGISTICAUX", strISLOGISTICAUX);
        String strDOCBASETYPE = HeaderData.selectAux3E769128EA274A8480EBB341E05468AF(this, ((dataField!=null)?dataField.getField("cDoctypeId"):((data==null || data.length==0)?"":data[0].cDoctypeId)));
    vars.setSessionValue(windowId + "|DOCBASETYPE", strDOCBASETYPE);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = HeaderData.set(Utility.getDefault(this, vars, "Freight_Currency_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Ratetype", "", "169", "", dataField), Utility.getDefault(this, vars, "Process_Goods_Java", "CO", "169", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "135", Utility.getDefault(this, vars, "Process_Goods_Java", "CO", "169", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "135", Utility.getDefault(this, vars, "Process_Goods_Java", "CO", "169", "N", dataField))), Utility.getDefault(this, vars, "Description", "", "169", "", dataField), Utility.getDefault(this, vars, "MovementType", "", "169", "", dataField), Utility.getDefault(this, vars, "MovementDate", "@#Date@", "169", "", dataField), Utility.getDefault(this, vars, "Processed", "", "169", "N", dataField), Utility.getDefault(this, vars, "Processing", "", "169", "N", dataField), "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "169", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@#AD_Org_ID@", "169", "", dataField), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "169", "", dataField), HeaderData.selectDef3526_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "169", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "169", "", dataField), HeaderData.selectDef3528_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "169", "", dataField)), Utility.getDefault(this, vars, "IsSOTrx", "@IsSOTrx@", "169", "N", dataField), Utility.getDefault(this, vars, "DocumentNo", "", "169", "", dataField), Utility.getDefault(this, vars, "C_DocType_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "IsPrinted", "", "169", "N", dataField), Utility.getDefault(this, vars, "DateAcct", "@#Date@", "169", "", dataField), Utility.getDefault(this, vars, "C_BPartner_ID", "", "169", "", dataField), HeaderData.selectDef3795_2(this, Utility.getDefault(this, vars, "C_BPartner_ID", "", "169", "", dataField)), Utility.getDefault(this, vars, "C_BPartner_Location_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "AD_User_ID", "", "169", "", dataField), HeaderData.selectDef3798(this, Utility.getContext(this, vars, "ad_org_id", "169")), Utility.getDefault(this, vars, "POReference", "", "169", "", dataField), Utility.getDefault(this, vars, "DeliveryRule", "A", "169", "", dataField), Utility.getDefault(this, vars, "FreightCostRule", "I", "169", "", dataField), Utility.getDefault(this, vars, "FreightAmt", "0", "169", "", dataField), Utility.getDefault(this, vars, "DeliveryViaRule", "P", "169", "", dataField), Utility.getDefault(this, vars, "M_Shipper_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "C_Charge_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "ChargeAmt", "0", "169", "", dataField), Utility.getDefault(this, vars, "PriorityRule", "5", "169", "", dataField), Utility.getDefault(this, vars, "DatePrinted", "", "169", "", dataField), Utility.getDefault(this, vars, "C_Order_ID", "", "169", "", dataField), HeaderData.selectDef3809_3(this, Utility.getDefault(this, vars, "C_Order_ID", "", "169", "", dataField)), Utility.getDefault(this, vars, "M_Condition_Goods_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "DateOrdered", "", "169", "", dataField), Utility.getDefault(this, vars, "DocStatus", "DR", "169", "", dataField), Utility.getDefault(this, vars, "DocAction", "CO", "169", "N", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Freighth", "", "169", "0", dataField), Utility.getDefault(this, vars, "CreateFrom", "", "169", "N", dataField), Utility.getDefault(this, vars, "GenerateTo", "", "169", "N", dataField), Utility.getDefault(this, vars, "C_Invoice_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "Posted", "", "169", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "", "169", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "", "169", "N", dataField))), Utility.getDefault(this, vars, "EM_RCOB_Commission", "0", "169", "", dataField), Utility.getDefault(this, vars, "UpdateLines", "N", "169", "N", dataField), Utility.getDefault(this, vars, "Islogistic", "@ISLOGISTICAUX@", "169", "N", dataField), Utility.getDefault(this, vars, "GenerateLines", "N", "169", "N", dataField), Utility.getDefault(this, vars, "Delivery_Location_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "M_FreightCategory_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "Calculate_Freight", "", "169", "N", dataField), Utility.getDefault(this, vars, "SalesRep_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "C_Costcenter_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Freightpaid", "0", "169", "0", dataField), Utility.getDefault(this, vars, "PickDate", "", "169", "", dataField), Utility.getDefault(this, vars, "TrackingNo", "", "169", "", dataField), Utility.getDefault(this, vars, "NoPackages", "", "169", "", dataField), Utility.getDefault(this, vars, "ShipDate", "", "169", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "User1_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "C_Activity_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "C_Campaign_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "169", "", dataField), HeaderData.selectDef9585_4(this, Utility.getDefault(this, vars, "C_Project_ID", "", "169", "", dataField)), Utility.getDefault(this, vars, "AD_OrgTrx_ID", "", "169", "", dataField), Utility.getDefault(this, vars, "EM_Rcfr_Insuranceh", "", "169", "0", dataField), Utility.getDefault(this, vars, "RM_Receipt_PickEdit", "", "169", "N", dataField), Utility.getDefault(this, vars, "RM_Shipment_Pickedit", "", "169", "N", dataField), Utility.getDefault(this, vars, "A_Asset_ID", "", "169", "", dataField));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "M_InOut", "", data[0].cDoctypeId, false, false) + ">";
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GoodsShipment/Header_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/GoodsShipment/Header_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Header", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpmInoutId", "", "../shipments/print.html", "N".equals("Y"), "GoodsShipment", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strM_InOut_ID), !hasReadOnlyAccess);
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
      // if (!strM_InOut_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Header_Relation.html", "GoodsShipment", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Header_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("ISLOGISTICAUX", strISLOGISTICAUX);
        xmlDocument.setParameter("DOCBASETYPE", strDOCBASETYPE);
    
    
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
comboTableData = new ComboTableData(vars, this, "18", "C_DocType_ID", "22F546D49D3A48E1B2B4F50446A8DE58", "125", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_DocType_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_BPartner_Location_ID", "", "167", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cBpartnerLocationId"):dataField.getField("cBpartnerLocationId")));
xmlDocument.setData("reportC_BPartner_Location_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "EM_Rcfr_Ratetype", "74D3C7DF005C4D03935CEE28256DC187", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("emRcfrRatetype"):dataField.getField("emRcfrRatetype")));
xmlDocument.setData("reportEM_Rcfr_Ratetype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonEM_Rcfr_Freighth", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Freightpaid", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcfr_Insuranceh", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("MovementDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "18", "Delivery_Location_ID", "159", "167", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("deliveryLocationId"):dataField.getField("deliveryLocationId")));
xmlDocument.setData("reportDelivery_Location_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("DateAcct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("CreateFrom_BTNname", Utility.getButtonName(this, vars, "4242", "CreateFrom_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalCreateFrom = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("CreateFrom_Modal", modalCreateFrom?"true":"false");
xmlDocument.setParameter("Process_Goods_Java_BTNname", Utility.getButtonName(this, vars, "135", (dataField==null?data[0].getField("processGoodsJava"):dataField.getField("processGoodsJava")), "Process_Goods_Java_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcess_Goods_Java = org.openbravo.erpCommon.utility.Utility.isModalProcess("49DEE812BF0545269781FCEBF2235924"); 
xmlDocument.setParameter("Process_Goods_Java_Modal", modalProcess_Goods_Java?"true":"false");
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
xmlDocument.setParameter("buttonFreightAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("PickDate_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("PickDate_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("buttonChargeAmt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("ShipDate_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("ShipDate_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("buttonEM_RCOB_Commission", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("DatePrinted_Format", vars.getSessionValue("#AD_SqlDateFormat"));
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





    private String getDisplayLogicContext(VariablesSecureApp vars, boolean isNew) throws IOException, ServletException {
      log4j.debug("Output: Display logic context fields");
      String result = "var strShowAcct=\"" +Utility.getContext(this, vars, "#ShowAcct", windowId) + "\";\nvar strACCT_DIMENSION_DISPLAY=\"" +Utility.getContext(this, vars, "ACCT_DIMENSION_DISPLAY", windowId) + "\";\nvar str$Element_AS=\"" +Utility.getContext(this, vars, "$Element_AS", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
            data = getEditVariables(con, vars);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.mInoutId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (HeaderData.getCurrentDBTimestamp(this, data.mInoutId).equals(
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
                    data.mInoutId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                        //BUTTON TEXT FILLING
                    data.processGoodsJavaBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "135", data.getField("Process_Goods_Java"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|M_InOut_ID", data.mInoutId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Header. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
