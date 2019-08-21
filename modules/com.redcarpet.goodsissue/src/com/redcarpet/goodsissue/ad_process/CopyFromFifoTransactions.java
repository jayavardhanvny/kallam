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
package com.redcarpet.goodsissue.ad_process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.StringTokenizer;

import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.redcarpet.epcg.data.EpcgVariant;
import com.redcarpet.goodsissue.data.RCGITransactions;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.model.common.plm.Product;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.filter.IsPositiveIntFilter;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;

import org.openbravo.dal.service.OBDal;

import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;
import java.sql.SQLException;
import java.math.BigDecimal;
import org.openbravo.exception.NoConnectionAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CopyFromFifoTransactions extends HttpSecureAppServlet {
	final static Logger logger = LoggerFactory.getLogger(CopyFromFifoTransactions.class);
	//protected java.util.logging.Logger log1 = java.util.logging.Logger.getLogger("CopyFromFifoTransactions.java");
	private static final long serialVersionUID = 1L;
	private static final BigDecimal ZERO = BigDecimal.ZERO;
		
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
			String strWindowId = vars.getStringParameter("inpwindowId");
			String strKey = vars.getGlobalVariable("inprcgiMaterialissueId", strWindowId + "|Rcgi_Materialissue_ID");
			String strTabId = vars.getStringParameter("inpTabId");
			String strOrg = vars.getStringParameter("inpadOrgId");
			String strJobId = vars.getStringParameter("inprchrJobcardId");
			String strConetype=vars.getStringParameter("inpepcgConetypeId");
			String strYarntype=vars.getStringParameter("inprchrWarpyarntypeId");
			String strVariant=vars.getStringParameter("inpepcgVariantId");
			printPageDataSheet(response, vars, strKey, strWindowId, strTabId,strOrg,strJobId,strConetype,strYarntype,strVariant);
		} else if (vars.commandIn("SAVE")) {
			String strRownum = null;
			try {
				strRownum = vars.getRequiredInStringParameter("inpRownumId", IsPositiveIntFilter.instance);
			} catch (ServletException e) {
				log4j.error("Error captured: ", e);
				throw new ServletException(OBMessageUtils.messageBD("@JS1@"));
			}
			logger.info("On Save");
			String strKey = vars.getRequiredStringParameter("inprcgiMaterialissueId");			
			String strTabId = vars.getStringParameter("inpTabId");
			String strJobId = vars.getStringParameter("inprchrJobcardId");
			if (strRownum.startsWith("(")) {
				strRownum = strRownum.substring(1, strRownum.length() - 1);
			}
			strRownum = Replace.replace(strRownum, "'", "");
			//System.out.println("strRownum 1 "+strRownum);
			try{
				//log1.info("strRownum "+strRownum);
				myError = copyLines(vars, strRownum, strKey,strJobId); 
				
			} catch(IOException e){
				logger.debug("IOException Process Error {}",e.getMessage());
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
			} catch(ServletException e){
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
			} catch(NoConnectionAvailableException e){
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+e.getMessage());
			} catch(SQLException sql){
				myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError "+sql.getMessage());
			}catch(Exception sql){
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
	private OBError copyLines(VariablesSecureApp vars, String strRownums, String strKey,String strJobId) throws IOException, ServletException,NoConnectionAvailableException, SQLException {
		OBError myError = null;
		int count = 0;
		if (strRownums.equals("")) {
			  myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError 1 ");
			  return myError;
		}
		
		  Connection conn = null;
		  RCGI_MaterialIssue mi=OBDal.getInstance().get(RCGI_MaterialIssue.class,strKey);
 		  long lineNo=10;
		  try {
	      		conn = getTransactionConnection();
	      		StringTokenizer st = new StringTokenizer(strRownums, ",", false);
	      		while (st.hasMoreTokens()) {
	          		String strRownum = st.nextToken().trim();
	          		System.out.println("row num..."+strRownum);
	          		String strMid = vars.getStringParameter("inpmid" + strRownum);
					String strYarnId = vars.getStringParameter("inpyarnid" + strRownum);
					String strVariantId = vars.getStringParameter("inpvariantid" + strRownum);
					String openqty = vars.getStringParameter("inpopenqty" + strRownum);
					String fifoId = vars.getStringParameter("inpfifoid" + strRownum);
					String issuedqty = vars.getStringParameter("inpissuedqty" + strRownum);
					String issedcones=vars.getStringParameter("inpissuedcones" +strRownum);
					//String fifoId = vars.getStringParameter("inpfifoid" + strRownum);
				   //System.out.println("row num..."+strRownum+" "+productname+" "+strMid+" "+strVariantId+" "+strYarnId);

					RCGI_MiLines miline= OBProvider.getInstance().get(RCGI_MiLines.class);
					miline.setClient(mi.getClient());
					miline.setOrganization(mi.getOrganization());
					miline.setLineNo(lineNo);
					miline.setProduct(OBDal.getInstance().get(Product.class,strMid));
					miline.setRchrWarpyarntype(OBDal.getInstance().get(RCHRWarpyarntype.class,strYarnId));
					miline.setEpcgVariant(OBDal.getInstance().get(EpcgVariant.class,strVariantId));
					miline.setRcgiTransactions(OBDal.getInstance().get(RCGITransactions.class,fifoId));
					miline.setOrderedQuantity(new BigDecimal(issuedqty));
					miline.setMaterialIssue(mi);
					miline.setIssedcones(new BigDecimal(issedcones));
					miline.setUnitprice(OBDal.getInstance().get(RCGITransactions.class,fifoId).getCost());
					miline.setLineNetAmount(new BigDecimal(issuedqty).multiply(OBDal.getInstance().get(RCGITransactions.class,fifoId).getCost()));
					miline.setStorageBin(OBDal.getInstance().get(RCGITransactions.class,fifoId).getStorageBin());
					OBDal.getInstance().save(miline);

					//OBDal.getInstance().flush();
	        		count++;
	                lineNo+=10;

	            }
              myError = new OBError();
              myError.setType("Success");
              myError.setTitle(OBMessageUtils.messageBD("Success"));
              myError.setMessage(OBMessageUtils.messageBD("Copied Records:"+count) );
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }

        conn = getTransactionConnection();
			  			  
        releaseRollbackConnectionCurrent(conn);
		return myError;
	}

	public void releaseRollbackConnectionCurrent(Connection conn) throws SQLException{
		releaseRollbackConnection(conn);
	}

	private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
			String strKey, String strWindowId, String strTabId, 
			String strOrg,String strJobId,String strConetype,String strYarntype,String strVariant) throws IOException, ServletException {
		
		XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
				"com/redcarpet/goodsissue/ad_process/CopyFromFifoTransactions").createXmlDocument();		
		//	System.out.println("job card.."+strJobId+" "+strKey);
			CopyFromFifoTransactionsData[] dataOrder = CopyFromFifoTransactionsData.select(this, strJobId,strConetype,strYarntype,strVariant);
	//		System.out.println("length all...."+dataOrder.length);
    	
			if(dataOrder.length!=0){
				xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
				xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
				xmlDocument.setParameter("theme", vars.getTheme());
				xmlDocument.setParameter("key", strKey);
				xmlDocument.setParameter("windowId", strWindowId);
				xmlDocument.setParameter("tabId", strTabId);
				xmlDocument.setData("structure1", dataOrder);
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(xmlDocument.print());
				out.close();
			}
		
	    		
		

	}

	public String getServletInfo() {
		return "Servlet Copy from order";
	}
}
