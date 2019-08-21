
package org.openbravo.erpWindows.Product;




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

public class Product extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Product.class);
  
  private static final String windowId = "140";
  private static final String tabId = "180";
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
     
      if (command.contains("136")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("136");
        SessionInfo.setModuleId("0");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("136")) {
        classInfo.type = "P";
        classInfo.id = "136";
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
        String strmProductId = request.getParameter("inpmProductId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strmProductId.equals(""))
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

      String strM_Product_ID = vars.getGlobalVariable("inpmProductId", windowId + "|M_Product_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|Product.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {

        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strM_Product_ID, tableSQL);

      else printPageDataSheet(response, vars, strM_Product_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strM_Product_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strM_Product_ID.equals("")) strM_Product_ID = vars.getRequiredGlobalVariable("inpmProductId", windowId + "|M_Product_ID");
      else vars.setSessionValue(windowId + "|M_Product_ID", strM_Product_ID);
      
      vars.setSessionValue(tabId + "|Product.view", "EDIT");

      printPageEdit(response, request, vars, false, strM_Product_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|Product.view");
      String strM_Product_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {

        }
      }
      if (strView.equals("EDIT")) {

        if (strM_Product_ID.equals("")) strM_Product_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strM_Product_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamValue", tabId + "|paramValue");
vars.getRequestGlobalVariable("inpParamName", tabId + "|paramName");
vars.getRequestGlobalVariable("inpParamM_Product_Category_ID", tabId + "|paramM_Product_Category_ID");
vars.getRequestGlobalVariable("inpParamProductType", tabId + "|paramProductType");
vars.getRequestGlobalVariable("inpParamC_TaxCategory_ID", tabId + "|paramC_TaxCategory_ID");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|M_Product_ID");
      String strM_Product_ID="";

      String strView = vars.getSessionValue(tabId + "|Product.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strM_Product_ID = firstElement(vars, tableSQL);
        if (strM_Product_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Product.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strM_Product_ID, tableSQL);

      else printPageDataSheet(response, vars, strM_Product_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strM_Product_ID = vars.getGlobalVariable("inpmProductId", windowId + "|M_Product_ID", "");
      vars.setSessionValue(tabId + "|Product.view", "RELATION");
      printPageDataSheet(response, vars, strM_Product_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strM_Product_ID = vars.getGlobalVariable("inpmProductId", windowId + "|M_Product_ID", "");
      vars.setSessionValue(tabId + "|Product.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strM_Product_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strM_Product_ID = vars.getRequiredStringParameter("inpmProductId");
      
      String strNext = nextElement(vars, strM_Product_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strM_Product_ID = vars.getRequiredStringParameter("inpmProductId");
      
      String strPrevious = previousElement(vars, strM_Product_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|Product.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|Product.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Product.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Product.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|M_Product_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|Product.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Product.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|M_Product_ID");

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

      String strM_Product_ID = vars.getRequiredGlobalVariable("inpmProductId", windowId + "|M_Product_ID");
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
          String strNext = nextElement(vars, strM_Product_ID, tableSQL);
          vars.setSessionValue(windowId + "|M_Product_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strM_Product_ID = vars.getRequiredStringParameter("inpmProductId");
      //ProductData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = ProductData.delete(this, strM_Product_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|mProductId");
        vars.setSessionValue(tabId + "|Product.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONProcessing136")) {
        vars.setSessionValue("button136.strprocessing", vars.getStringParameter("inpprocessing"));
        vars.setSessionValue("button136.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button136.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button136.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button136.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "136", request.getServletPath());
      } else if (vars.commandIn("BUTTON136")) {
        String strM_Product_ID = vars.getGlobalVariable("inpmProductId", windowId + "|M_Product_ID", "");
        String strprocessing = vars.getSessionValue("button136.strprocessing");
        String strProcessing = vars.getSessionValue("button136.strProcessing");
        String strOrg = vars.getSessionValue("button136.strOrg");
        String strClient = vars.getSessionValue("button136.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessing136(response, vars, strM_Product_ID, strprocessing, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcessing136")) {
        String strM_Product_ID = vars.getGlobalVariable("inpKey", windowId + "|M_Product_ID", "");
        
        ProcessBundle pb = new ProcessBundle("136", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("M_Product_ID", strM_Product_ID);
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
  private ProductData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    ProductData data = new ProductData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.mProductCategoryId = vars.getRequiredStringParameter("inpmProductCategoryId");     data.mProductCategoryIdr = vars.getStringParameter("inpmProductCategoryId_R");     data.value = vars.getRequestGlobalVariable("inpvalue", windowId + "|Value");     data.name = vars.getRequiredStringParameter("inpname");     data.name2 = vars.getStringParameter("inpname2");     data.upc = vars.getStringParameter("inpupc");     data.sku = vars.getStringParameter("inpsku");     data.adImageId = vars.getStringParameter("inpadImageId");     data.cUomId = vars.getRequiredStringParameter("inpcUomId");     data.cUomIdr = vars.getStringParameter("inpcUomId_R");     data.cTaxcategoryId = vars.getRequiredStringParameter("inpcTaxcategoryId");     data.cTaxcategoryIdr = vars.getStringParameter("inpcTaxcategoryId_R");     data.ispurchased = vars.getStringParameter("inpispurchased", "N");     data.issold = vars.getStringParameter("inpissold", "N");     data.description = vars.getStringParameter("inpdescription");     data.producttype = vars.getRequiredStringParameter("inpproducttype");     data.producttyper = vars.getStringParameter("inpproducttype_R");     data.isstocked = vars.getStringParameter("inpisstocked", "N");    try {   data.weight = vars.getNumericParameter("inpweight");  } catch (ServletException paramEx) { ex = paramEx; }     data.cUomWeightId = vars.getStringParameter("inpcUomWeightId");     data.cUomWeightIdr = vars.getStringParameter("inpcUomWeightId_R");     data.costtype = vars.getRequestGlobalVariable("inpcosttype", windowId + "|Costtype");     data.costtyper = vars.getStringParameter("inpcosttype_R");    try {   data.coststd = vars.getNumericParameter("inpcoststd");  } catch (ServletException paramEx) { ex = paramEx; }     data.mAttributesetId = vars.getRequestGlobalVariable("inpmAttributesetId", windowId + "|M_AttributeSet_ID");     data.mAttributesetIdr = vars.getStringParameter("inpmAttributesetId_R");     data.attrsetvaluetype = vars.getStringParameter("inpattrsetvaluetype");     data.attrsetvaluetyper = vars.getStringParameter("inpattrsetvaluetype_R");     data.mAttributesetinstanceId = vars.getRequestGlobalVariable("inpmAttributesetinstanceId", windowId + "|M_AttributeSetInstance_ID");     data.mAttributesetinstanceIdr = vars.getStringParameter("inpmAttributesetinstanceId_R");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.emEpcgMake = vars.getStringParameter("inpemEpcgMake");     data.mLocatorId = vars.getStringParameter("inpmLocatorId");     data.emRcloIslot = vars.getStringParameter("inpemRcloIslot", "N");     data.salesrepId = vars.getStringParameter("inpsalesrepId");     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");     data.imageurl = vars.getStringParameter("inpimageurl");     data.descriptionurl = vars.getStringParameter("inpdescriptionurl");     data.production = vars.getStringParameter("inpproduction", "N");     data.maProcessplanId = vars.getStringParameter("inpmaProcessplanId");     data.maProcessplanIdr = vars.getStringParameter("inpmaProcessplanId_R");     data.issummary = vars.getStringParameter("inpissummary", "N");    try {   data.volume = vars.getNumericParameter("inpvolume");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.shelfwidth = vars.getNumericParameter("inpshelfwidth");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.shelfheight = vars.getNumericParameter("inpshelfheight");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.shelfdepth = vars.getNumericParameter("inpshelfdepth");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.unitsperpallet = vars.getNumericParameter("inpunitsperpallet");  } catch (ServletException paramEx) { ex = paramEx; }     data.discontinued = vars.getStringParameter("inpdiscontinued", "N");     data.discontinuedby = vars.getStringParameter("inpdiscontinuedby");     data.isbom = vars.getRequiredInputGlobalVariable("inpisbom", windowId + "|IsBOM", "N");     data.isinvoiceprintdetails = vars.getStringParameter("inpisinvoiceprintdetails", "N");     data.ispicklistprintdetails = vars.getStringParameter("inpispicklistprintdetails", "N");     data.isverified = vars.getStringParameter("inpisverified", "N");     data.processing = vars.getRequiredStringParameter("inpprocessing");     data.sExpensetypeId = vars.getStringParameter("inpsExpensetypeId");     data.isquantityvariable = vars.getStringParameter("inpisquantityvariable", "N");     data.sResourceId = vars.getStringParameter("inpsResourceId");     data.isdeferredrevenue = vars.getStringParameter("inpisdeferredrevenue", "N");     data.isdeferredexpense = vars.getStringParameter("inpisdeferredexpense", "N");     data.bookusingpoprice = vars.getStringParameter("inpbookusingpoprice", "N");     data.revplantype = vars.getStringParameter("inprevplantype");     data.revplantyper = vars.getStringParameter("inprevplantype_R");    try {   data.periodnumber = vars.getNumericParameter("inpperiodnumber");  } catch (ServletException paramEx) { ex = paramEx; }     data.defaultperiod = vars.getStringParameter("inpdefaultperiod");     data.defaultperiodr = vars.getStringParameter("inpdefaultperiod_R");     data.expplantype = vars.getStringParameter("inpexpplantype");     data.expplantyper = vars.getStringParameter("inpexpplantype_R");    try {   data.periodnumberExp = vars.getNumericParameter("inpperiodnumberExp");  } catch (ServletException paramEx) { ex = paramEx; }     data.defaultperiodExp = vars.getStringParameter("inpdefaultperiodExp");     data.defaultperiodExpr = vars.getStringParameter("inpdefaultperiodExp_R");     data.calculated = vars.getStringParameter("inpcalculated", "N");    try {   data.capacity = vars.getNumericParameter("inpcapacity");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.delaymin = vars.getNumericParameter("inpdelaymin");  } catch (ServletException paramEx) { ex = paramEx; }     data.mrpPlannerId = vars.getStringParameter("inpmrpPlannerId");     data.mrpPlanningmethodId = vars.getStringParameter("inpmrpPlanningmethodId");    try {   data.qtymax = vars.getNumericParameter("inpqtymax");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.qtymin = vars.getNumericParameter("inpqtymin");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.qtystd = vars.getNumericParameter("inpqtystd");  } catch (ServletException paramEx) { ex = paramEx; }     data.qtytype = vars.getStringParameter("inpqtytype", "N");    try {   data.stockmin = vars.getNumericParameter("inpstockmin");  } catch (ServletException paramEx) { ex = paramEx; }     data.emRcgiDeptproduct = vars.getStringParameter("inpemRcgiDeptproduct", "N");    try {   data.emRcgiRemqty = vars.getNumericParameter("inpemRcgiRemqty");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcgiAvgcp = vars.getNumericParameter("inpemRcgiAvgcp");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.emRcgiCurrentsp = vars.getNumericParameter("inpemRcgiCurrentsp");  } catch (ServletException paramEx) { ex = paramEx; }     data.mFreightcategoryId = vars.getStringParameter("inpmFreightcategoryId");     data.downloadurl = vars.getStringParameter("inpdownloadurl");    try {   data.guaranteedays = vars.getNumericParameter("inpguaranteedays");  } catch (ServletException paramEx) { ex = paramEx; }     data.help = vars.getStringParameter("inphelp");     data.documentnote = vars.getStringParameter("inpdocumentnote");     data.classification = vars.getStringParameter("inpclassification");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.mProductId = vars.getRequestGlobalVariable("inpmProductId", windowId + "|M_Product_ID");     data.enforceAttribute = vars.getStringParameter("inpenforceAttribute", "N");     data.versionno = vars.getStringParameter("inpversionno");    try {   data.stockMin = vars.getNumericParameter("inpstockMin");  } catch (ServletException paramEx) { ex = paramEx; }     data.ispriceprinted = vars.getStringParameter("inpispriceprinted", "N"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



          vars.getGlobalVariable("inpissotrxtab", windowId + "|ISSOTRXTAB", "");
          vars.getGlobalVariable("inpmWarehouseId", windowId + "|M_WAREHOUSE_ID", "");
          vars.getGlobalVariable("inpproductOrg", windowId + "|Product_Org", "");
    
    

          if (data.value.equals("")) data.value = Utility.getDocumentNoConnection(con, this, vars.getClient(), "M_Product", true);
vars.setSessionValue(windowId + "|Value", data.value);
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
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Value", data[0].getField("value"));    vars.setSessionValue(windowId + "|Costtype", data[0].getField("costtype"));    vars.setSessionValue(windowId + "|M_AttributeSet_ID", data[0].getField("mAttributesetId"));    vars.setSessionValue(windowId + "|M_AttributeSetInstance_ID", data[0].getField("mAttributesetinstanceId"));    vars.setSessionValue(windowId + "|IsBOM", data[0].getField("isbom"));    vars.setSessionValue(windowId + "|M_Product_ID", data[0].getField("mProductId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      ProductData[] data = ProductData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpmProductId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strM_Product_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamValue = vars.getSessionValue(tabId + "|paramValue");
String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamM_Product_Category_ID = vars.getSessionValue(tabId + "|paramM_Product_Category_ID");
String strParamProductType = vars.getSessionValue(tabId + "|paramProductType");
String strParamC_TaxCategory_ID = vars.getSessionValue(tabId + "|paramC_TaxCategory_ID");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamValue) && ("").equals(strParamName) && ("").equals(strParamM_Product_Category_ID) && ("").equals(strParamProductType) && ("").equals(strParamC_TaxCategory_ID)) || !(("").equals(strParamValue) || ("%").equals(strParamValue))  || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamM_Product_Category_ID) || ("%").equals(strParamM_Product_Category_ID))  || !(("").equals(strParamProductType) || ("%").equals(strParamProductType))  || !(("").equals(strParamC_TaxCategory_ID) || ("%").equals(strParamC_TaxCategory_ID)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strM_Product_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strM_Product_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/Product/Product_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Product", false, "document.frmMain.inpmProductId", "grid", "..", "".equals("Y"), "Product", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
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
    xmlDocument.setParameter("KeyName", "mProductId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Product_Relation.html", "Product", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Product_Relation.html", strReplaceWith);
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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strM_Product_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " M_Product.Value";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    ProductData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamValue = vars.getSessionValue(tabId + "|paramValue");
String strParamName = vars.getSessionValue(tabId + "|paramName");
String strParamM_Product_Category_ID = vars.getSessionValue(tabId + "|paramM_Product_Category_ID");
String strParamProductType = vars.getSessionValue(tabId + "|paramProductType");
String strParamC_TaxCategory_ID = vars.getSessionValue(tabId + "|paramC_TaxCategory_ID");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamValue) && ("").equals(strParamName) && ("").equals(strParamM_Product_Category_ID) && ("").equals(strParamProductType) && ("").equals(strParamC_TaxCategory_ID)) || !(("").equals(strParamValue) || ("%").equals(strParamValue))  || !(("").equals(strParamName) || ("%").equals(strParamName))  || !(("").equals(strParamM_Product_Category_ID) || ("%").equals(strParamM_Product_Category_ID))  || !(("").equals(strParamProductType) || ("%").equals(strParamProductType))  || !(("").equals(strParamC_TaxCategory_ID) || ("%").equals(strParamC_TaxCategory_ID)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        
        if (("").equals(strParamValue) && ("").equals(strParamName) && ("").equals(strParamM_Product_Category_ID) && ("").equals(strParamProductType) && ("").equals(strParamC_TaxCategory_ID) && strM_Product_ID.equals("")) {
          buscador = "openSearchWindow('../businessUtility/Buscador.html', 'BUSCADOR', " + tabId + ", 'Product/Product_Relation.html', " + windowId + ");";
        } else if (strM_Product_ID.equals("")) {
          ProductData[] data1 = ProductData.select(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strParamValue, strParamName, strParamM_Product_Category_ID, strParamProductType, strParamC_TaxCategory_ID, strParamSessionDate, vars.getUser(), Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel), strOrderByFilter);
          data = new ProductData[1];
          if (data1!=null && data1.length!=0) data[0] = data1[0];
        } else data = ProductData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strM_Product_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strM_Product_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new ProductData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("mProductId") == null || dataField.getField("mProductId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
        String strISSOTRXTAB = "Y";
    vars.setSessionValue(windowId + "|ISSOTRXTAB", strISSOTRXTAB);
        String strM_WAREHOUSE_ID = "''";
    vars.setSessionValue(windowId + "|M_WAREHOUSE_ID", strM_WAREHOUSE_ID);
        String strProduct_Org = ((dataField!=null)?dataField.getField("adOrgId"):((data==null || data.length==0)?"":data[0].adOrgId));
    vars.setSessionValue(windowId + "|Product_Org", strProduct_Org);
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = ProductData.set("", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "140", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_Org_ID@", "140", "", dataField), "Y", Utility.getDefault(this, vars, "CreatedBy", "", "140", "", dataField), ProductData.selectDef1407_0(this, Utility.getDefault(this, vars, "CreatedBy", "", "140", "", dataField)), Utility.getDefault(this, vars, "UpdatedBy", "", "140", "", dataField), ProductData.selectDef1409_1(this, Utility.getDefault(this, vars, "UpdatedBy", "", "140", "", dataField)), Utility.getDefault(this, vars, "Name", "", "140", "", dataField), Utility.getDefault(this, vars, "Description", "", "140", "", dataField), Utility.getDefault(this, vars, "IsSummary", "", "140", "N", dataField), Utility.getDefault(this, vars, "C_UOM_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "IsStocked", "Y", "140", "N", dataField), Utility.getDefault(this, vars, "IsPurchased", "Y", "140", "N", dataField), Utility.getDefault(this, vars, "IsSold", "Y", "140", "N", dataField), Utility.getDefault(this, vars, "Volume", "", "140", "", dataField), Utility.getDefault(this, vars, "Weight", "1", "140", "", dataField), Utility.getDefault(this, vars, "Revplantype", "", "140", "", dataField), Utility.getDefault(this, vars, "EM_Rclo_Islot", "N", "140", "N", dataField), Utility.getDefault(this, vars, "Value", "", "140", "", dataField), Utility.getDefault(this, vars, "M_Product_Category_ID", "923CA80B92144032BA08A531D0B4F069", "140", "", dataField), Utility.getDefault(this, vars, "C_TaxCategory_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "UPC", "", "140", "", dataField), Utility.getDefault(this, vars, "SKU", "", "140", "", dataField), Utility.getDefault(this, vars, "ShelfWidth", "", "140", "", dataField), Utility.getDefault(this, vars, "ShelfHeight", "", "140", "", dataField), Utility.getDefault(this, vars, "ShelfDepth", "", "140", "", dataField), Utility.getDefault(this, vars, "UnitsPerPallet", "", "140", "", dataField), Utility.getDefault(this, vars, "Discontinued", "", "140", "N", dataField), Utility.getDefault(this, vars, "DiscontinuedBy", "", "140", "", dataField), Utility.getDefault(this, vars, "DefaultPeriod", "", "140", "", dataField), Utility.getDefault(this, vars, "DocumentNote", "", "140", "", dataField), Utility.getDefault(this, vars, "Help", "", "140", "", dataField), Utility.getDefault(this, vars, "Classification", "", "140", "", dataField), Utility.getDefault(this, vars, "SalesRep_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "Bookusingpoprice", "N", "140", "N", dataField), Utility.getDefault(this, vars, "IsBOM", "N", "140", "N", dataField), Utility.getDefault(this, vars, "IsInvoicePrintDetails", "", "140", "N", dataField), Utility.getDefault(this, vars, "IsPickListPrintDetails", "", "140", "N", dataField), Utility.getDefault(this, vars, "IsVerified", "N", "140", "N", dataField), Utility.getDefault(this, vars, "Processing", "N", "140", "N", dataField), Utility.getDefault(this, vars, "EM_Rcgi_Deptproduct", "@deptstoreproduct@", "140", "N", dataField), Utility.getDefault(this, vars, "Capacity", "", "140", "", dataField), Utility.getDefault(this, vars, "Delaymin", "", "140", "", dataField), Utility.getDefault(this, vars, "MRP_Planner_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "MRP_Planningmethod_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "Qtymax", "", "140", "", dataField), Utility.getDefault(this, vars, "Qtymin", "", "140", "", dataField), Utility.getDefault(this, vars, "Qtystd", "", "140", "", dataField), Utility.getDefault(this, vars, "Qtytype", "", "140", "N", dataField), Utility.getDefault(this, vars, "Stockmin", "", "140", "", dataField), Utility.getDefault(this, vars, "S_ExpenseType_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "S_Resource_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "ProductType", "I", "140", "", dataField), Utility.getDefault(this, vars, "ImageURL", "", "140", "", dataField), Utility.getDefault(this, vars, "DescriptionURL", "", "140", "", dataField), Utility.getDefault(this, vars, "VersionNo", "", "140", "", dataField), Utility.getDefault(this, vars, "GuaranteeDays", "", "140", "", dataField), Utility.getDefault(this, vars, "Attrsetvaluetype", "", "140", "", dataField), Utility.getDefault(this, vars, "AD_Image_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "C_BPartner_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "Ispriceprinted", "Y", "140", "N", dataField), Utility.getDefault(this, vars, "Name2", "", "140", "", dataField), Utility.getDefault(this, vars, "Costtype", "", "140", "", dataField), Utility.getDefault(this, vars, "Coststd", "", "140", "", dataField), Utility.getDefault(this, vars, "Stock_Min", "", "140", "", dataField), Utility.getDefault(this, vars, "Enforce_Attribute", "N", "140", "N", dataField), Utility.getDefault(this, vars, "Calculated", "", "140", "N", dataField), Utility.getDefault(this, vars, "MA_Processplan_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "Production", "", "140", "N", dataField), Utility.getDefault(this, vars, "Isdeferredrevenue", "N", "140", "N", dataField), Utility.getDefault(this, vars, "M_AttributeSet_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "M_AttributeSetInstance_ID", "", "140", "", dataField), ProductData.selectDef8418_2(this, Utility.getDefault(this, vars, "M_AttributeSetInstance_ID", "", "140", "", dataField)), Utility.getDefault(this, vars, "DownloadURL", "", "140", "", dataField), Utility.getDefault(this, vars, "EM_Rcgi_Avgcp", "0", "140", "", dataField), Utility.getDefault(this, vars, "M_FreightCategory_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "M_Locator_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "EM_Epcg_Make", "", "140", "", dataField), Utility.getDefault(this, vars, "Isdeferredexpense", "N", "140", "N", dataField), Utility.getDefault(this, vars, "DefaultPeriod_Exp", "", "140", "", dataField), Utility.getDefault(this, vars, "Expplantype", "", "140", "", dataField), Utility.getDefault(this, vars, "EM_Rcgi_Remqty", "0", "140", "", dataField), Utility.getDefault(this, vars, "Periodnumber_Exp", "", "140", "", dataField), Utility.getDefault(this, vars, "C_Uom_Weight_ID", "", "140", "", dataField), Utility.getDefault(this, vars, "Isquantityvariable", "N", "140", "N", dataField), Utility.getDefault(this, vars, "EM_Rcgi_Currentsp", "0", "140", "", dataField), Utility.getDefault(this, vars, "Periodnumber", "", "140", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/Product/Product_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/Product/Product_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Product", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpmProductId", "", "..", "".equals("Y"), "Product", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strM_Product_ID), !hasReadOnlyAccess);
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
      // if (!strM_Product_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Product_Relation.html", "Product", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Product_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
        xmlDocument.setParameter("ISSOTRXTAB", strISSOTRXTAB);
        xmlDocument.setParameter("M_WAREHOUSE_ID", strM_WAREHOUSE_ID);
        xmlDocument.setParameter("Product_Org", strProduct_Org);
    
    
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
comboTableData = new ComboTableData(vars, this, "19", "M_Product_Category_ID", "", "772B9BE4957746EC809B8FE4D8E3F924", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mProductCategoryId"):dataField.getField("mProductCategoryId")));
xmlDocument.setData("reportM_Product_Category_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
String strCurrentImageURLAD_Image_ID = (dataField==null?data[0].getField("adImageId"):dataField.getField("adImageId"));
if (strCurrentImageURLAD_Image_ID==null || strCurrentImageURLAD_Image_ID.equals("")){
  xmlDocument.setParameter("AD_Image_IDClass", "Image_NotAvailable_medium");
}

comboTableData = new ComboTableData(vars, this, "19", "C_UOM_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomId"):dataField.getField("cUomId")));
xmlDocument.setData("reportC_UOM_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_TaxCategory_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cTaxcategoryId"):dataField.getField("cTaxcategoryId")));
xmlDocument.setData("reportC_TaxCategory_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "ProductType", "270", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("producttype"):dataField.getField("producttype")));
xmlDocument.setData("reportProductType","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonWeight", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "18", "C_Uom_Weight_ID", "C3ED971A900A414B8A0A937B442374E1", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cUomWeightId"):dataField.getField("cUomWeightId")));
xmlDocument.setData("reportC_Uom_Weight_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Costtype", "800025", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("costtype"):dataField.getField("costtype")));
xmlDocument.setData("reportCosttype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonCoststd", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "M_AttributeSet_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("mAttributesetId"):dataField.getField("mAttributesetId")));
xmlDocument.setData("reportM_AttributeSet_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Attrsetvaluetype", "5AD08D5DF85549E0BCC0DEBDE4C0D340", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("attrsetvaluetype"):dataField.getField("attrsetvaluetype")));
xmlDocument.setData("reportAttrsetvaluetype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "MA_Processplan_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("maProcessplanId"):dataField.getField("maProcessplanId")));
xmlDocument.setData("reportMA_Processplan_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonVolume", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonShelfWidth", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonShelfHeight", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonShelfDepth", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("DiscontinuedBy_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Processing_BTNname", Utility.getButtonName(this, vars, "3747", "Processing_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalProcessing = org.openbravo.erpCommon.utility.Utility.isModalProcess("136"); 
xmlDocument.setParameter("Processing_Modal", modalProcessing?"true":"false");
comboTableData = new ComboTableData(vars, this, "17", "Revplantype", "73625A8F22EF4CD7808603156BA606D7", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("revplantype"):dataField.getField("revplantype")));
xmlDocument.setData("reportRevplantype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "DefaultPeriod", "6669508E338F4A10BA3E0D241D133E62", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("defaultperiod"):dataField.getField("defaultperiod")));
xmlDocument.setData("reportDefaultPeriod","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Expplantype", "73625A8F22EF4CD7808603156BA606D7", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("expplantype"):dataField.getField("expplantype")));
xmlDocument.setData("reportExpplantype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "DefaultPeriod_Exp", "6669508E338F4A10BA3E0D241D133E62", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("defaultperiodExp"):dataField.getField("defaultperiodExp")));
xmlDocument.setData("reportDefaultPeriod_Exp","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonCapacity", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonDelaymin", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQtymax", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQtymin", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonQtystd", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonStockmin", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcgi_Remqty", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcgi_Avgcp", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonEM_Rcgi_Currentsp", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("buttonStock_Min", Utility.messageBD(this, "Calc", vars.getLanguage()));
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



    void printPageButtonProcessing136(HttpServletResponse response, VariablesSecureApp vars, String strM_Product_ID, String strprocessing, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 136");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Processing136", discard).createXmlDocument();
      xmlDocument.setParameter("key", strM_Product_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Product_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "136");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("136");
        vars.removeMessage("136");
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
      String result = "var strCost_Eng_Ins_Migrated=\"" +Utility.getContext(this, vars, "Cost_Eng_Ins_Migrated", windowId) + "\";\nvar strshowMRPandProductionFields=\"" +Utility.getContext(this, vars, "showMRPandProductionFields", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
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
    ProductData data = null;
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
                data.mProductId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (ProductData.getCurrentDBTimestamp(this, data.mProductId).equals(
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
                    data.mProductId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|M_Product_ID", data.mProductId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Product. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
