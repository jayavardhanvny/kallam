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

import com.rcss.humanresource.data.RchrBankmaster;
import com.rcss.humanresource.data.RchrBanksalpayments;
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
import org.openbravo.base.exception.OBException;
import org.openbravo.base.filter.IsPositiveIntFilter;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;
import java.math.BigDecimal;
import com.rcss.humanresource.data.RchrBankSalPaymentsOnline;
import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;

public class CopyOnlineSalaries extends HttpSecureAppServlet {
	
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
    	String strKey = vars.getGlobalVariable("inprchrBanksalpaymentsId", strWindowId + "|Rchr_Banksalpayments_Id");
    	log4j.info("checkPending(strKey) "+checkPending(strKey));
    	String strTabId = vars.getStringParameter("inpTabId");    	

        	printPageDataSheet(response, vars, strKey, strWindowId, strTabId);
    } else if (vars.commandIn("SAVE")) {
		String strRownum = null;
		try {
			strRownum = vars.getRequiredInStringParameter("inpRownumId", IsPositiveIntFilter.instance);
		} catch (ServletException e) {
			log4j.error("Error captured: ", e);
			throw new ServletException(OBMessageUtils.messageBD("@JS1@"));
		}
		//System.out.println("under save");
     
		String strKey = vars.getRequiredStringParameter("inprchrBanksalpaymentsId");
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



  private double checkPending(String strKey){
	  RchrBanksalpayments e=OBDal.getInstance().get(RchrBanksalpayments.class,strKey);
	  double cnt=0,cnt1=0;
	  for(RchrBankSalPaymentsOnline line:e.getRchrBanksalpaymentsolList()){
  	 	if(line.getAlertStatus().equals("DR")){
	 	 	cnt++;
  	 	}
	 }
	 if(e.getOnlinecnt().doubleValue()==0)
	 return cnt1;
	 else 
	 return cnt;
  }
  
  private OBError copyLines(VariablesSecureApp vars, String strRownums, String strKey)
      throws IOException, ServletException {
		
    OBError myError = null;
    int count = 0;
    double empamt =0;
   // double ot=0;
    if (strRownums.equals("")) {
      myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
      return myError;
    }
    Connection conn = null;
    try {
    		
    		conn = getTransactionConnection();    		
    		StringTokenizer st = new StringTokenizer(strRownums, ",", false);
		RchrBanksalpayments e=OBDal.getInstance().get(RchrBanksalpayments.class,strKey);
		    //System.out.println(e.getId());
		    CopyOnlineSalariesData[] orderData;
  	        //if(e.getEmployeeType().equals("Staff")) {
			//orderData = CopyOnlineSalariesData.select(this,e.getRchrAttdProcess().getId(),e.getOrganization().getId());
			//}
			//else
			//{
			orderData = CopyOnlineSalariesData.selectnew(this,e.getRchrAttdProcess().getId(),e.getOrganization().getId(),e.getEmployeeType());						
			//}
  	        e.setOnlinecnt(e.getOnlinecnt().add(new BigDecimal(1)));
    		while (st.hasMoreTokens()) 
    		{
    			String strRownum = st.nextToken().trim();
    			int cnt=Integer.parseInt(strRownum);    			   			    				      			                     			    		
    			String strOTEmpLinesId=SequenceIdData.getUUID();
    			
    			
       	       try{			
       	       				 
       	       		 String amt=vars.getStringParameter("inppamt" + strRownum);       	       				
       	       		 empamt=empamt+Double.parseDouble(amt);	       	       				 
       	       		
       	       		 
       	       		 RchrBankSalPaymentsOnline line = OBProvider.getInstance().get(RchrBankSalPaymentsOnline.class);       	       		 
					 line.setOrganization(e.getOrganization());
					 line.setRcplEmppayrollprocess(OBDal.getInstance().get(RCPL_EmpPayrollProcess.class,orderData[cnt-1].eid));
					 line.setEmployee(OBDal.getInstance().get(RchrEmployee.class,orderData[cnt-1].empid));
					 line.setAccountNo(orderData[cnt-1].acno);
					 line.setProcessingsal(new BigDecimal(amt));
					 line.setIFSCCode(orderData[cnt-1].ifsccode);
					 line.setBankname(orderData[cnt-1].bankname);
					 line.setRchrBankmaster(OBDal.getInstance().get(RchrBankmaster.class,orderData[cnt-1].mid));
					 line.setAlertStatus("DR");
					 line.setRchrBanksalpayments(e);					
					 line.setSlotno(e.getOnlinecnt());
					 OBDal.getInstance().save(line);
					 OBDal.getInstance().flush();															 
       	       }catch (Exception ex) {
       	          myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), ex.getMessage());
       	          releaseRollbackConnection(conn);
       	          return myError;
       	        }
       	        
				count++;
    		} 
    	 
    	    e.setCopydata(Boolean.TRUE);    
       	    releaseCommitConnection(conn);
       	    myError = new OBError();
       	    myError.setType("Success");
       	    myError.setTitle(OBMessageUtils.messageBD("Success"));
       	    myError.setMessage(OBMessageUtils.messageBD("RecordsCopied") + " " + count);
     }
     catch (Exception e) 
     {
    	  
    	  try 
    	  {
    		  releaseRollbackConnection(conn);
    	  }
    	  catch (Exception ignored) {;}
		  e.printStackTrace();
		  log4j.warn("Rollback in transaction");
		  myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
     }
     return myError;
  }
  

private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
      String strKey, String strWindowId, String strTabId) throws IOException, ServletException {




	String etype="";
	RchrBanksalpayments e=OBDal.getInstance().get(RchrBanksalpayments.class,strKey);
		
	log4j.debug("Output: Approvee");
   
	CopyOnlineSalariesData[] dataOrder = null;
	XmlDocument xmlDocument;
        //if(e.getEmployeeType().equals("Staff")) {
		//	dataOrder = CopyOnlineSalariesData.select(this,e.getRchrAttdProcess().getId(),e.getOrganization().getId());
		//}
		//else
		//{

		//}

	xmlDocument = xmlEngine.readXmlTemplate(
			"com/rcss/humanresource/ad_process/CopyOnlineSalaries").createXmlDocument();


	{
		final OBError myMessage = vars.getMessage(strTabId);
		vars.removeMessage(strTabId);
		if (checkPending(strKey)==0) {
			dataOrder = CopyOnlineSalariesData.selectnew(this,e.getRchrAttdProcess().getId(),e.getOrganization().getId(),e.getEmployeeType());
			xmlDocument.setParameter("messageType", "Info");
			xmlDocument.setParameter("messageTitle", "Information");
			xmlDocument.setParameter("messageMessage",Utility.messageBD(this, "Rchr_CopySalariesSucces", vars.getLanguage()));
		} else {
			xmlDocument.setParameter("messageType", "Error");
			xmlDocument.setParameter("messageTitle", "Information");
			xmlDocument.setParameter("messageMessage", Utility.messageBD(this, "Rchr_CopySalariesError", vars.getLanguage()));
		}
	}

	xmlDocument.setParameter("windowId", strWindowId);
	xmlDocument.setParameter("key", strKey);
	xmlDocument.setParameter("tabId", strTabId);
	xmlDocument.setData("structure1", dataOrder);
	xmlDocument.setParameter("theme", vars.getTheme());
	xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
	xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println(xmlDocument.print());
	out.close();
  }

  private void throwError1() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "Bank name should be mandatory if payment type bank", language));
    }
  
  public String getServletInfo() {
    return "Servlet Copy from Advance";
  } 
}
