/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html 
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License. 
 * The Original Code is Openbravo ERP. 
 * The Initial Developer of the Original Code is Openbravo SLU 
 * All portions are Copyright (C) 2001-2012 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package com.redcarpet.epcg.ad_forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.filter.IsPositiveIntFilter;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.DateTimeData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.financial.FinancialUtils;

import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;
import com.redcarpet.payroll.util.PayrollUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openbravo.service.db.DalConnectionProvider;
//import org.openbravo.model.project.Project;
import org.openbravo.database.ConnectionProvider;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;
import com.redcarpet.epcg.data.EpcgYarncosttemplate;
import com.redcarpet.epcg.data.EpcgVariant;
import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.redcarpet.epcg.data.EpcgYarncosttemplatelines;

public class EpcgCostingTemplateCopyLines extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  private static final BigDecimal ZERO = BigDecimal.ZERO;
	

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    OBError myError =null;
    if (vars.commandIn("DEFAULT")) {
      String strWindowId = vars.getStringParameter("inpwindowId");
      //String strSOTrx = Utility.getContext(this, vars, "isSOTrx", strWindowId);
      String strKey = vars.getGlobalVariable("inpepcgYarncosttemplateId", strWindowId + "|Epcg_Yarncosttemplate_ID");
      
      System.out.println("strKey is 1 " +strKey);
      
      String strTabId = vars.getStringParameter("inpTabId");
      
      String strYarncosttemplateId = vars.getStringParameter("inpepcgYarncosttemplateId");
      
     // String strQualityStandardId = vars.getStringParameter("inprchrQualitystandardId");
      //System.out.println("strQualityStandardId is " +strQualityStandardId);
      
      
      String strOrg = vars.getStringParameter("inpadOrgId");
     
      printPageDataSheet(response, vars, strKey, strWindowId, strTabId, 
    		  strYarncosttemplateId, strOrg);
      
    } else if (vars.commandIn("SAVE")) {
    	
    	
    	String errorString="";
    	
    	
    	
      String strRownum = null;
      
      try {
        strRownum = vars.getRequiredInStringParameter("inpRownumId", IsPositiveIntFilter.instance);
      } catch (ServletException e) {
        log4j.error("Error captured: ", e);
        throw new ServletException(OBMessageUtils.messageBD("@JS1@"));
      }

      String strKey = vars.getRequiredStringParameter("inpepcgYarncosttemplateId");
      
      EpcgYarncosttemplate yarncost = OBDal.getInstance().get(EpcgYarncosttemplate.class, strKey);
      

      String strclient = yarncost.getClient().getId();
      String strTabId = vars.getStringParameter("inpTabId");
      
      
      
      if (strRownum.startsWith("(")) {
        strRownum = strRownum.substring(1, strRownum.length() - 1);
        System.out.println("In If condition strRownum is " +strRownum);
      }
      
      strRownum = Replace.replace(strRownum, "'", "");
      
      
      myError = copyLines(vars, strRownum, strKey, strclient,yarncost);
      
      String strWindowPath = Utility.getTabURL(strTabId, "R", true);
      if (strWindowPath.equals(""))
        strWindowPath = strDefaultServlet;

      vars.setMessage(strTabId, myError);
      
      printPageClosePopUp(response, vars, strWindowPath);
    } else
      pageErrorPopUp(response);
  }

  
  private OBError copyLines(VariablesSecureApp vars, String strRownums, String strKey, 
		  String strClient,EpcgYarncosttemplate yarncost)
      throws IOException, ServletException {

    OBError myError = null;
    int count = 0;

    if (strRownums.equals("")) {
      // return "";
      myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
      return myError;
    }
    
    
    Connection conn = null;
    
    try {
      conn = getTransactionConnection();
      StringTokenizer st = new StringTokenizer(strRownums, ",", false);
      
      //RchrRollwisedata rollwisedatareturned = insertIntoRollNumber(inspsheet);
      
     count = insertLineLevel(yarncost,st,vars,count);
      
      
      releaseCommitConnection(conn);
      myError = new OBError();
      myError.setType("Success");
      myError.setTitle(OBMessageUtils.messageBD("Success"));
      myError.setMessage(OBMessageUtils.messageBD("RecordsCopied") + " " + count);
    } catch (Exception e) {
      try {
        releaseRollbackConnection(conn);
      } catch (Exception ignored) {
      }
      e.printStackTrace();
      log4j.warn("Rollback in transaction");
      myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
    }
    return myError;
  }
/*
  public RchrRollwisedata insertIntoRollNumber(RCHR_Inspectionsheet inspsheet){

      
		 
		 RchrRollwisedata rollwiseProvider = OBProvider.getInstance().get(RchrRollwisedata.class);
			
			rollwiseProvider.setRchrInspectionsheet(inspsheet);
			rollwiseProvider.setOrganization(inspsheet.getOrganization());
			rollwiseProvider.setRchrQualitystandard(inspsheet.getRchrQualitystandard());
			rollwiseProvider.setRollmts(inspsheet.getTotalmtr());
			rollwiseProvider.setRolldate(new Date());
			rollwiseProvider.setRcprShift(inspsheet.getInspshift());
			rollwiseProvider.setEmployee(inspsheet.getDataeop());
			rollwiseProvider.setRcplLoom(inspsheet.getRcplLoom());
			rollwiseProvider.setRchrPiecenolist(inspsheet.getRchrPiecenolist());
			rollwiseProvider.setGlm(inspsheet.getGlm());
			rollwiseProvider.setGrossweight(inspsheet.getGrossweight());
			
			// Getting the inserted Roll Number List tab level Object and setting into the Roll Wise data 
			rollwiseProvider.setRchrRollnolist(this.getinsertedRollNumberObject(inspsheet.getInspdate()));
			//rollwiseProvider.setDocumentNo("1");
			
			inspsheet.setGeneraterollno(true);
			
			OBDal.getInstance().save(rollwiseProvider);
			
			
			//insertLineLevel(rollwiseProvider);
			
			final OBError msg = new OBError();
	        msg.setType("Success");
	        msg.setTitle("Done");
	        msg.setMessage("Roll Number has been generated Successfully");
	        bundle.setResult(msg);
			 
			
	        return rollwiseProvider;
}
  */
public int insertLineLevel(EpcgYarncosttemplate yarncost, StringTokenizer st, VariablesSecureApp vars,int count){
	String previousOpenedId = vars.getStringParameter("inpOpenedYarnTemplateId");
	System.out.println("previousOpenedId "+previousOpenedId);
  EpcgYarncosttemplate epcgYarncosttemplate = OBDal.getInstance().get(EpcgYarncosttemplate.class,previousOpenedId);

  yarncost.setNoofdays(epcgYarncosttemplate.getNoofdays());
  yarncost.setWarpwastepercentage(epcgYarncosttemplate.getWarpwastepercentage());
  yarncost.setWeftwastepercentage(epcgYarncosttemplate.getWeftwastepercentage());
  yarncost.setFringewidth(epcgYarncosttemplate.getFringewidth());
  yarncost.setPackngmatrlcnsmptn(epcgYarncosttemplate.getPackngmatrlcnsmptn());

	
    while (st.hasMoreTokens()) {
      String strRownum = st.nextToken().trim();
      System.out.println("str num.."+strRownum);
      
      
      
      String strproduct = vars.getStringParameter("inpproductid" + strRownum);
      System.out.println("strproduct is =========-" +strproduct);
      
      
      
      String stryarntype = vars.getStringParameter("inpyarntypeid" + strRownum);
      System.out.println("stryarntype is" + stryarntype);
      
      String strvariant = vars.getStringParameter("inpvariantid" + strRownum);
      System.out.println("strvariant is" + strvariant);
      
      String strsizing = vars.getStringParameter("inpsizing" + strRownum);
      System.out.println("strsizing is" + strsizing);
      
      String strefficiency = vars.getStringParameter("inpefficiency" + strRownum);
      System.out.println("strefficiency is" + strefficiency);
      
      String strspeed = vars.getStringParameter("inpspeed" + strRownum);
      System.out.println("strspeed is" + strspeed);
      
      String strcurrentyarnprice = vars.getStringParameter("inpcurrentyarnprice" + strRownum);
      System.out.println("strcurrentyarnprice is" + strcurrentyarnprice);
      
      String stryarnprice = vars.getStringParameter("inpyarnprice" + strRownum);
      System.out.println("stryarnprice is " + stryarnprice);
     
     
      
      org.openbravo.model.common.plm.Product prod =  OBDal.getInstance().get(org.openbravo.model.common.plm.Product.class,strproduct);
      EpcgVariant variant = null;
      RCHRWarpyarntype yarntype = null;
      if(strvariant!=null){
    	  try{
    		  variant = OBDal.getInstance().get(EpcgVariant.class,strvariant);
    		  yarntype = OBDal.getInstance().get(RCHRWarpyarntype.class,stryarntype);
          }catch(Exception e){
        	  
          }
      }else{
    	  
      }
      
      
      //Double a = 0;
      Double rate = new Double(0);
      Long sizing = new Long(0);
      Long efficiency = new Long(0);
      Long speed = new Long(0);
      try{
    	  //a = Double.
    	  rate=Double.parseDouble(stryarnprice);
    	  sizing = Long.parseLong(strsizing);
    	  efficiency = Long.parseLong(strefficiency);
    	  speed = Long.parseLong(strspeed);
    	  //Long.parseLong(strsizing);
      }catch(Exception e){
    	  
      }
      System.out.println("rate, sizing is " + rate +" , "+sizing +" , "+efficiency); 
     // EpcgYarncosttemplatelines yarnlines = OBDal.getInstance().get(EpcgYarncosttemplatelines.class,strInspectionlineId);
      
      
      //Providing Roll wise data line Level
      
      
      EpcgYarncosttemplatelines yarnlines = OBProvider.getInstance().get(EpcgYarncosttemplatelines.class); 
      yarncost.setCopylines(Boolean.TRUE);
      yarnlines.setOrganization(yarncost.getOrganization());
      yarnlines.setProduct(prod);
      yarnlines.setRate(new BigDecimal(rate));
      yarnlines.setSizing(sizing);
      yarnlines.setEfficiency(efficiency);
      yarnlines.setSpeed(speed);
      yarnlines.setRchrWarpyarntype(yarntype);
      yarnlines.setEpcgYarncosttemplate(yarncost);
      yarnlines.setEpcgVariant(variant);
      yarnlines.setProcessed(Boolean.TRUE);
      OBDal.getInstance().save(yarnlines);
      System.out.println("Ensdingf ");
	  count++;
    }
    return count;
}

  

  
  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
      String strKey, String strWindowId, String strTabId, String strYarncosttemplateId,
      String strOrg) throws IOException, ServletException {
    log4j.debug("Output: Shipment");

    OBCriteria<EpcgYarncosttemplate> epcgYarncosttemplateOBCriteria = OBDal.getInstance().createCriteria(EpcgYarncosttemplate.class);
    epcgYarncosttemplateOBCriteria.add(Restrictions.eq(EpcgYarncosttemplate.PROPERTY_ALERTSTATUS,"OP") );


    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/redcarpet/epcg/ad_forms/EpcgCostingTemplateCopyLines").createXmlDocument();
    EpcgCostingTemplateCopyLinesData[] dataOrder = EpcgCostingTemplateCopyLinesData.select(this);
    //EpcgYarncosttemplate epcgYarncosttemplate = OBDal.getInstance().get(EpcgYarncosttemplate.class,strYarncosttemplateId);
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
    xmlDocument.setParameter("theme", vars.getTheme());
    xmlDocument.setParameter("key", strKey);
    xmlDocument.setParameter("windowId", strWindowId);
    xmlDocument.setParameter("tabId", strTabId);

    xmlDocument.setParameter("openedYarnTemplateId", epcgYarncosttemplateOBCriteria.list().get(0).getId());


    xmlDocument.setData("structure1", dataOrder);
   // xmlDocument.setData("structure2", dataOrder);
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();

  }

  public String getServletInfo() {
    return "Servlet Copy from order";
  } // end of getServletInfo() method
  

  
	
 
  
  
  private void throwErrorNull() throws OBException {
      String language = OBContext.getOBContext().getLanguage().getLanguage();
      ConnectionProvider conn = new DalConnectionProvider(false);
      throw new OBException(Utility.messageBD(conn, "This Month Period was empty and not generated in Roll Master Months!", language));
  }
  private void throwError(String errorString) throws OBException {
      String language = OBContext.getOBContext().getLanguage().getLanguage();
      ConnectionProvider conn = new DalConnectionProvider(false);
      throw new OBException(Utility.messageBD(conn, errorString, language));
  }
}
