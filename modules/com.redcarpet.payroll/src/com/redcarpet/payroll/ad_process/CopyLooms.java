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
package com.redcarpet.payroll.ad_process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.pojo.cglib.CGLIBProxyFactory;
import org.jfree.util.Log;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.filter.IsPositiveIntFilter;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
//import org.openbravo.erpCommon.ad_actionButton.CopyFromOrderData;
import org.openbravo.erpCommon.utility.DateTimeData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openbravo.erpCommon.utility.ComboTableData;

import com.redcarpet.payroll.data.RCPLLoom;

import java.sql.SQLException;

import java.util.Date;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;
import org.openbravo.exception.NoConnectionAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rcss.humanresource.exceptions.ExceedException;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPLProdIncentiveLine;
import com.redcarpet.payroll.data.RCPLLoomCategory;
import com.redcarpet.payroll.data.RCPLEfficiency;
import com.redcarpet.payroll.data.RCPLEfficiencyLine;
import com.redcarpet.payroll.data.RcplLoomcategorylines;
import com.redcarpet.payroll.data.RCPLLoomCategory;
//import com.redcarpet.payroll.data.RcplLoomcatlinsclothtype;
public class CopyLooms extends HttpSecureAppServlet {
	final static Logger logger = LoggerFactory.getLogger(CopyLooms.class);
	protected java.util.logging.Logger log1 = java.util.logging.Logger.getLogger("CopyLooms.java");
	private static final long serialVersionUID = 1L;
	private static final BigDecimal ZERO = BigDecimal.ZERO;
	//public long avg1=0;
	//public BigDecimal avg1=new BigDecimal("0");
	
	public double avg1=0;
	public void init(ServletConfig config) {
		super.init(config);
		boolHist = false;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		VariablesSecureApp vars = new VariablesSecureApp(request);
		logger.info("In Copy Looms Class");
		OBError myError =null;
		if (vars.commandIn("DEFAULT")) {
			//System.out.println("=====Executing Default=====");
			String strWindowId = vars.getStringParameter("inpwindowId");
			String strKey = vars.getGlobalVariable("inprcplProdincentiveId", strWindowId + "|Rcpl_Prodincentive_ID");
			String strTabId = vars.getStringParameter("inpTabId");
			String strOrg = vars.getStringParameter("inpadOrgId");
			RCPLProdIncentive e1 = OBDal.getInstance().get(RCPLProdIncentive.class, strKey);
			String strLoomcg1=e1.getRcplLoomcategory().getId();
			String strShiftid1=e1.getRcprShift().getId();
			SimpleDateFormat sf1=new SimpleDateFormat("dd-MM-yyyy");
			String date1=sf1.format(e1.getDate());
			printPageDataSheet(response, vars, strKey, strWindowId, strTabId,strOrg,strLoomcg1,date1,strShiftid1);
		} else if (vars.commandIn("SAVE")) {
			String strRownum = null;
			try {
				strRownum = vars.getRequiredInStringParameter("inpRownumId", IsPositiveIntFilter.instance);
			} catch (ServletException e) {
				log4j.error("Error captured: ", e);
				throw new ServletException(OBMessageUtils.messageBD("@JS1@"));
			}
			logger.info("On Save");
			String strKey = vars.getRequiredStringParameter("inprcplProdincentiveId");
			RCPLProdIncentive e1=OBDal.getInstance().get(RCPLProdIncentive.class,strKey);
			String strclient = e1.getClient().getId();
			String strLoomcg=e1.getRcplLoomcategory().getId();
			String strShiftid=e1.getRcprShift().getId();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String date2=sf.format(e1.getDate());
			SimpleDateFormat sf2=new SimpleDateFormat("dd-MM-yyyy");
			String date1=sf2.format(e1.getDate());
			String strTabId = vars.getStringParameter("inpTabId");
			if (strRownum.startsWith("(")) {
				strRownum = strRownum.substring(1, strRownum.length() - 1);
			}
			strRownum = Replace.replace(strRownum, "'", "");
			System.out.println("strRownum 1 "+strRownum);
			try{
				log1.info("strRownum "+strRownum);
				//System.out.println("strRownum 2 "+strRownum);
				myError = copyLines(vars, strRownum, strKey,strclient,strLoomcg,date1,date2,strShiftid); 
				
			} catch(IOException e){
				logger.debug("IOException Process Error {}",e.getMessage());
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
			} catch(ServletException e){
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
			} catch(NoConnectionAvailableException e){
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
			} catch(SQLException sql){
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+sql.getMessage());
			}catch(ExceedException sql){
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+sql.getMessage());
			}
			String strWindowPath = Utility.getTabURL(strTabId, "R", true);
			if (strWindowPath.equals(""))
				strWindowPath = strDefaultServlet;
			vars.setMessage(strTabId, myError);
			printPageClosePopUp(response, vars, strWindowPath);
		} else
			pageErrorPopUp(response);
	}
	private OBError copyLines(VariablesSecureApp vars, String strRownums, String strKey,String strClient,String strLoomcg1,String date1,String date2,
			String strShiftId1) throws IOException, ServletException,NoConnectionAvailableException, SQLException,ExceedException {
		OBError myError = null;
		int count = 0;
		if (strRownums.equals("")) {
			  myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError 1 ");
			  return myError;
		}
		
		  Connection conn = null;
			  conn = getTransactionConnection();
			  
			  LoomIncentiveCalculation loomIncentiveCalculationObject = new LoomIncentiveCalculation(this);
			  myError = loomIncentiveCalculationObject.getCalculation(vars, strRownums, strKey,strClient,strLoomcg1,date1,date2,strShiftId1,count,conn); 
			  releaseRollbackConnectionCurrent(conn);
		return myError;
	}

	public void releaseRollbackConnectionCurrent(Connection conn) throws SQLException{
		releaseRollbackConnection(conn);
	}
	private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
			String strKey, String strWindowId, String strTabId, 
			String strOrg,String strLoomcg1,String date1,String strShiftId1) throws IOException, ServletException {
		log4j.debug("Output: Shipment");
		String strKey1=strKey;
		String date2=date1;
		String date3=date1;
		String date4=date1;
		String date5=date1;
		String strShiftId4=strShiftId1;
		String strShiftId5=strShiftId1;
		String strShiftId2=strShiftId1;
		String strShiftId3=strShiftId1;
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
				"com/redcarpet/payroll/ad_process/CopyLooms").createXmlDocument();
		RCPLLoomCategory category=OBDal.getInstance().get(RCPLLoomCategory.class,strLoomcg1);
		if(category.isAll()){
			CopyLoomsData[] dataOrder = CopyLoomsData.selectAll(this, strKey1, date1, date2, strShiftId1, date3, strShiftId2, strShiftId4, date5, strShiftId5, strShiftId3, date4);
			System.out.println("length all...."+dataOrder.length);
    	
			if(dataOrder.length!=0){
				xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
				xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
				xmlDocument.setParameter("theme", vars.getTheme());
				xmlDocument.setParameter("key", strKey);
				xmlDocument.setParameter("windowId", strWindowId);
				xmlDocument.setParameter("tabId", strTabId);
				xmlDocument.setData("structure1", dataOrder);
				try{
	    			ComboTableData comboTableData = new ComboTableData(vars, this, 
	    				"TABLEDIR", "RCHR_DETENTION_ID", "LoomsDetentions", "", Utility.getContext(
	    						this, vars, "#User_Org", "DETENTIONSLIST"),
	    					Utility.getContext(this, vars, "User_Client",
	    							"DETENTIONSLIST"),0);
	    			Utility.fillSQLParameters(this, vars, null, comboTableData,
	    					"DETENTIONSLIST", "");
	    			xmlDocument.setData("reportRCHR_DETENTIONID", "liststructure",
	    					comboTableData.select(false));
	    			comboTableData = null;
				} catch (Exception ex) {
					throw new ServletException(ex);
				}
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(xmlDocument.print());
				out.close();}
		} else{	
			CopyLoomsData[] dataOrder = CopyLoomsData.select(this, strKey1, strLoomcg1, date1, date2, strShiftId1, date3, strShiftId2, strShiftId4, date5, strShiftId5, strShiftId3, date4);
			System.out.println("length...."+dataOrder.length);
			if(dataOrder.length!=0){
	    		xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
	    		xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
	    		xmlDocument.setParameter("theme", vars.getTheme());
	    		xmlDocument.setParameter("key", strKey);
	    		xmlDocument.setParameter("windowId", strWindowId);
	    		xmlDocument.setParameter("tabId", strTabId);
	    		xmlDocument.setData("structure1", dataOrder);
	    		try{
	    			ComboTableData comboTableData = new ComboTableData(vars, this, 
	    				"TABLEDIR", "RCHR_DETENTION_ID", "", "", Utility.getContext(
	    						this, vars, "#User_Org", "DETENTIONSLIST"),
	    					Utility.getContext(this, vars, "User_Client",
	    							"DeductionsSummary"),0);
	    			Utility.fillSQLParameters(this, vars, null, comboTableData,
	    					"DETENTIONSLIST", "");
	    			xmlDocument.setData("reportRCHR_DETENTIONID", "liststructure",
	    					comboTableData.select(false));
	    			comboTableData = null;
	    		} catch (Exception ex) {
	    			throw new ServletException(ex);
	    		}
	    		response.setContentType("text/html; charset=UTF-8");
	    		PrintWriter out = response.getWriter();
	    		out.println(xmlDocument.print());
	    		out.close();}
		}

	}

	public String getServletInfo() {
		return "Servlet Copy from order";
	}
}
