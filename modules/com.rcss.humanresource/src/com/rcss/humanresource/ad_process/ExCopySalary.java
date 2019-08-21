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
package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import org.openbravo.dal.service.OBDal;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.StringTokenizer;
import org.openbravo.base.provider.OBProvider;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openbravo.base.filter.IsPositiveIntFilter;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;
import java.math.BigDecimal;

import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;

public class ExCopySalary extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  private static final BigDecimal ZERO = BigDecimal.ZERO;
  

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
    	String strWindowId = vars.getStringParameter("inpwindowId");
    	String strKey = vars.getGlobalVariable("inprchrExbanksalpaymentsempId", strWindowId + "|RCHR_Exbanksalpaymentsemp_Id");
    	String strTabId = vars.getStringParameter("inpTabId");
        printPageDataSheet(response, vars, strKey, strWindowId, strTabId);
    } 
	else if (vars.commandIn("SAVE")) {
		String strRownum = null;
		try {
			strRownum = vars.getRequiredInStringParameter("inpRownumId", IsPositiveIntFilter.instance);
		} catch (ServletException e) {
			log4j.error("Error captured: ", e);
			throw new ServletException(OBMessageUtils.messageBD("@JS1@"));
		}
		//System.out.println("under save");
     
		String strKey = vars.getRequiredStringParameter("inprchrExbanksalpaymentsempId");
		String strTabId = vars.getStringParameter("inpTabId");         			
		if (strRownum.startsWith("(")) {
			strRownum = strRownum.substring(1, strRownum.length() - 1);
		}
		strRownum = Replace.replace(strRownum, "'", "");
		OBError myError = copyLines(vars, strRownum, strKey);
      
		String strWindowPath = Utility.getTabURL(strTabId, "R", true);
		if (strWindowPath.equals(""))
			strWindowPath = strDefaultServlet;

		vars.setMessage(strTabId, myError);
		printPageClosePopUp(response, vars, strWindowPath);
	} else
		pageErrorPopUp(response);
  }
  
  private OBError copyLines(VariablesSecureApp vars, String strRownums, String strKey)
      throws IOException, ServletException {
	
    OBError myError = null;
    int count = 0;
    double empamt =0;
    if (strRownums.equals("")) {
      myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
      return myError;
    }
    Connection conn = null;
    try {
    		conn = getTransactionConnection();
    		StringTokenizer st = new StringTokenizer(strRownums, ",", false);
    		RchrExbanksalpaymentsemp e=OBDal.getInstance().get(RchrExbanksalpaymentsemp.class,strKey);
		    //System.out.println(e.getId());
  	        ExCopySalaryData[] orderData;  	        
    		orderData = ExCopySalaryData.select(this,e.getRchrExbanksalpayments().getRchrAttdProcess().getId(),e.getOrganization().getId(),e.getRchrExbanksalpayments().getId(),e.getRchrExbanksalpayments().getId());	  	     		
    		while (st.hasMoreTokens()) 
    		{
    			String strRownum = st.nextToken().trim();   	
    			int cnt=Integer.parseInt(strRownum);    			   			    				      			                     			    		
    			String strOTEmpLinesId=SequenceIdData.getUUID();
    		//	System.out.println("str num.."+strRownum+"procss id.."+orderData[cnt-1].eid+"emp id.."+orderData[cnt-1].empid);  
    			
       	       try{			       	       				 
				   String amt=vars.getStringParameter("inppamt" + strRownum);
				   empamt=empamt+Double.parseDouble(amt);
				   System.out.println(empamt);
				   RchrExbanksalpaymentsempLn line = OBProvider.getInstance().get(RchrExbanksalpaymentsempLn.class);
				   line.setOrganization(e.getOrganization());
				   // line.setRcplEmppayrollprocess(OBDal.getInstance().get(RCPL_EmpPayrollProcess.class,orderData[cnt-1].eid));
				   //line.setEmployee(OBDal.getInstance().get(RchrEmployee.class,orderData[cnt-1].empid));
				   line.setRcplEmppayrollprocess(OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, vars.getStringParameter("inpeid" + strRownum)));
				   line.setEmployee(OBDal.getInstance().get(RchrEmployee.class, vars.getStringParameter("inpempid" + strRownum)));
				   line.setPaidAmount(new BigDecimal(amt));
				   line.setRchrExbanksalpaymentsemp(e);
				   line.setAlertStatus("DR");
				   OBDal.getInstance().save(line);
				   OBDal.getInstance().flush();
       	       }catch (Exception ex) {
       	          myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), ex.getMessage());
       	          releaseRollbackConnection(conn);
       	          return myError;
       	        }
       	        
				count++;
    		}
			e.setPaidAmount(new BigDecimal(empamt).add(e.getProcessingsal()));
    	    e.setCopysalary(Boolean.TRUE);    	   
       	    releaseCommitConnection(conn);
       	    myError = new OBError();
       	    myError.setType("Success");
       	    myError.setTitle(OBMessageUtils.messageBD("Success"));
       	    myError.setMessage(OBMessageUtils.messageBD("RecordsCopied") + " " + count);
     }
     catch (Exception e) 
     {
		 try {
    		  releaseRollbackConnection(conn);
    	  } catch (Exception ignored) {;}
		  e.printStackTrace();
		  log4j.warn("Rollback in transaction");
		  myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
     }
     return myError;
  }
  

private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
      String strKey, String strWindowId, String strTabId) throws IOException, ServletException {
      
	String etype="";
	RchrExbanksalpaymentsemp e=OBDal.getInstance().get(RchrExbanksalpaymentsemp.class,strKey);
	 
	log4j.debug("Output: Approvee");
   
   	ExCopySalaryData[] dataOrder = null;
	XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/rcss/humanresource/ad_process/ExCopySalary").createXmlDocument();

	{

/*
		log4j.info("length all...."+dataOrder.length);
		if (dataOrder.length > 0){
			xmlDocument.setParameter("messageType", "Info");
			xmlDocument.setParameter("messageTitle", "Information");
			xmlDocument.setParameter("messageMessage",Utility.messageBD(this, "Rchr_CopySalariesSucces", vars.getLanguage()));
		} else {
			xmlDocument.setParameter("messageType", "Error");
			xmlDocument.setParameter("messageTitle", "Information");
			xmlDocument.setParameter("messageMessage", Utility.messageBD(this, "RCOB_AgentInformation", vars.getLanguage()));
		}
*/

		final OBError myMessage = vars.getMessage(strTabId);
		vars.removeMessage(strTabId);

		if (checkPending(strKey)==0) {
			dataOrder = ExCopySalaryData.select(this,e.getRchrExbanksalpayments().getRchrAttdProcess().getId(), e.getOrganization().getId(),
					e.getRchrExbanksalpayments().getId(),e.getRchrExbanksalpayments().getId());
			if (dataOrder.length > 0){
				xmlDocument.setParameter("messageType", "Info");
				xmlDocument.setParameter("messageTitle", "Information");
				xmlDocument.setParameter("messageMessage",Utility.messageBD(this, "Rchr_CopySalariesSucces", vars.getLanguage()));
			} else {
				xmlDocument.setParameter("messageType", "Error");
				xmlDocument.setParameter("messageTitle", "Information");
				xmlDocument.setParameter("messageMessage", Utility.messageBD(this, "RCOB_AgentInformation", vars.getLanguage()));
			}
		} else {
			xmlDocument.setParameter("messageType", "Error");
			xmlDocument.setParameter("messageTitle", "Information");
			xmlDocument.setParameter("messageMessage", Utility.messageBD(this, "Rchr_CopySalariesError", vars.getLanguage()));
		}
	}
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

  public String getServletInfo() {
    return "Servlet Copy from Advance";
  }
	private double checkPending(String strKey) {
  	log4j.info("str Key "+strKey);
		RchrExbanksalpaymentsemp rchrExbanksalpaymentsemp = OBDal.getInstance().get(RchrExbanksalpaymentsemp.class, strKey);
		log4j.info("rchrExbanksalpayments "+rchrExbanksalpaymentsemp);
		double cnt=0,cnt1=0;
		for(RchrExbanksalpaymentsempLn line : rchrExbanksalpaymentsemp.getRchrExbanksalpaymentsemplnList()){
			if(line.getAlertStatus().equals("DR")){
				cnt++;
			}
		}
		
			return cnt;
	}
}

