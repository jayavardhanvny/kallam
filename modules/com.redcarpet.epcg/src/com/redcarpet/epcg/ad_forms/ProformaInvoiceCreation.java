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
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.StringTokenizer;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.filter.IsPositiveIntFilter;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.DateTimeData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.financial.FinancialUtils;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import com.redcarpet.epcg.data.EpcgProformaline;
import com.redcarpet.epcg.data.EpcgProforma;
import org.openbravo.model.ad.access.OrderLineTax;
import java.util.List;


//import org.openbravo.erpCommon.info.SalesOrder;

import org.openbravo.model.pricing.pricelist.ProductPrice;
import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;

public class ProformaInvoiceCreation extends HttpSecureAppServlet {
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
      //String strSOTrx = Utility.getContext(this, vars, "isSOTrx", strWindowId);
      String strKey = vars.getGlobalVariable("inpcOrderId", strWindowId + "|C_Order_ID");
      String strTabId = vars.getStringParameter("inpTabId");
      String strBpartner = vars.getStringParameter("inpcBpartnerId");
      String strOrg = vars.getStringParameter("inpadOrgId");
	  String strDateFrom = vars.getGlobalVariable("inpDateFrom",
					"ProformaInvoiceCreation|dateFrom", "");
	  String strDateTo = vars.getGlobalVariable("inpDateTo",
				"ProformaInvoiceCreation|dateTo", "");
      System.out.println("strKey is 1" +strKey);

      //String strmPricelistId = vars.getStringParameter("inpmPricelistId");
      printPageDataSheet(response, vars, strKey, strWindowId, strTabId, strBpartner, strOrg, strDateFrom
          );
    } else if (vars.commandIn("SAVE")) {
      String strRownum = null;
      try {
        strRownum = vars.getRequiredInStringParameter("inpRownumId", IsPositiveIntFilter.instance);
      } catch (ServletException e) {
        log4j.error("Error captured: ", e);
        throw new ServletException(OBMessageUtils.messageBD("@JS1@"));
      }
          
      String strKey = vars.getRequiredStringParameter("inpcOrderId");
      //System.out.println("strKey1 is" +strKey1);
      //String strKey = "0DF13F03ADEA454E88444B286AAE6B05";
      //System.out.println("strKey is 2 " +strKey);
      
      String strDateFrom = vars.getGlobalVariable("inpDateFrom",
				"ProformaInvoiceCreation|dateFrom", "");
      String strDateTo = vars.getGlobalVariable("inpDateTo",
				"ProformaInvoiceCreation|dateTo", "");

      Order requisition = OBDal.getInstance().get(Order.class, strKey);
	  String strclient = requisition.getClient().getId();

      
      String strTabId = vars.getStringParameter("inpTabId");
      if (strRownum.startsWith("(")) {
        strRownum = strRownum.substring(1, strRownum.length() - 1);
      }
      strRownum = Replace.replace(strRownum, "'", "");
      OBError myError = copyLines(vars, strRownum, strKey, strclient,strDateFrom,strDateTo);

      String strWindowPath = Utility.getTabURL(strTabId, "R", true);
      if (strWindowPath.equals(""))
        strWindowPath = strDefaultServlet;

      vars.setMessage(strTabId, myError);
      printPageClosePopUp(response, vars, strWindowPath);
    } else
      pageErrorPopUp(response);
  }

  private OBError copyLines(VariablesSecureApp vars, String strRownums, String strKey, String strClient , String strDateFrom,String strDateTo)
      throws IOException, ServletException {

    OBError myError = null;
    long count = 0;

    if (strRownums.equals("")) {
      // return "";
      myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
      return myError;
    }
    
    System.out.println("from date1:" + strDateFrom);
 	SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	Date fromDate = null;
	Date ToDate = null;
	try{
		fromDate = sdf.parse(strDateFrom);
		ToDate = sdf.parse(strDateTo);
	}catch(Exception e){
		System.out.println(e);
		//throws new Exception("ERROR: converting date");
	}
	
	 
	
	Order ord=OBDal.getInstance().get(Order.class,strKey);
	BigDecimal grtot=new BigDecimal(0);
	BigDecimal linetot =new BigDecimal(0);
	BigDecimal taxamt,prtaxamt;
	taxamt=new BigDecimal(0);
	prtaxamt=new BigDecimal(0);

	
	
Integer i = new Integer(ord.getEpcgProformaList().size()+1);
	
	String s =i.toString();
	
	
	
	
	EpcgProforma proform= OBProvider.getInstance().get(EpcgProforma.class) ;
	proform.setSalesOrder(ord);
	proform.setDocumentType(ord.getDocumentType());
	proform.setDocumentNo(ord.getDocumentNo()+"/PR- "+s);
	proform.setProformadate(fromDate); // 
	proform.setPartnerAddress(ord.getPartnerAddress());	
	proform.setBusinessPartner(ord.getBusinessPartner());
	proform.setClient(ord.getClient());
	proform.setOrganization(ord.getOrganization());
//	OBDal.getInstance().save(proform);
	
	
	    Connection conn = null;
	    try {
	      conn = getTransactionConnection();
	      StringTokenizer st = new StringTokenizer(strRownums, ",", false);
	      while (st.hasMoreTokens()) {
	          String strRownum = st.nextToken().trim();
	          String strQty = vars.getNumericParameter("inpquantity" + strRownum);
	          //System.out.println("strQty is "+strQty);
	          String orderline=vars.getStringParameter("inpcOrderlineId" + strRownum);
	        //  String strorderlineId = vars.getStringParameter("inpcOrderlineId" + strRownum)
	          //System.out.println("orderline is "+strRownum);
	         // System.out.println("orderline is "+orderline);
	          
	          
	      	OrderLine ordlin=OBDal.getInstance().get(OrderLine.class,orderline);

            if(ordlin.getOrderedQuantity().longValue()< (ordlin.getEpcgProformqty().longValue()+ new BigDecimal(strQty).longValue()))
            {
            	myError = new OBError();
            	myError.setType("Error");
            	myError.setTitle(OBMessageUtils.messageBD("Error"));
            	myError.setMessage(OBMessageUtils.messageBD("Proforma quantity greter than Order quantity ") );
            }
	      	
            else
            {
	      	EpcgProformaline proformline= OBProvider.getInstance().get(EpcgProformaline.class) ;
	      	proformline.setOrganization(ord.getOrganization());
	      	//System.out.println("tax is "+ordlin.getProduct());
	      	
	      	proformline.setProduct(ordlin.getProduct());
	    	//System.out.println("Produc "+ordlin.getTax());
	    	
	      	proformline.setTax(ordlin.getTax());
	    	//System.out.println("unit "+ordlin.getUnitPrice().longValue());

	      	proformline.setUnitPrice(ordlin.getUnitPrice());
	    	//System.out.println("11111111");
	      	proformline.setEpcgProforma(proform);
	      	//System.out.println("2222222");
	      	proformline.setSalesOrderLine(ordlin);
	      	//System.out.println("3333333");
	      	proformline.setUOM(ordlin.getUOM());
	      	//System.out.println("44444444");
	      	proformline.setOrderQuantity(ordlin.getOrderedQuantity());
	      	//System.out.println("55555555");
	      	proformline.setLineNo((count+1)*10);
	      //	taxamt=	((ordlin.getTaxableAmount().multiply(new BigDecimal(strQty))).divide(ordlin.getOrderQuantity(),3));
	      	
      	    List<OrderLineTax> ordlinetaxs=ordlin.getOrderLineTaxList();
      	   
      	    if(ordlinetaxs.size()>0)
      	    {
      	    for(OrderLineTax ordlinetax:ordlinetaxs )
      	    {
      	    	System.out.println("--"+ordlinetax.getTaxAmount());
      	    	prtaxamt=prtaxamt.add(ordlinetax.getTaxAmount());
      	    	
      	    }
      	  System.out.println("-prtaxamt-"+prtaxamt+" "+ordlin.getOrderedQuantity());
      	  
      	    taxamt=prtaxamt.multiply(new BigDecimal(strQty)).divide(ordlin.getOrderedQuantity(),3);
      	  System.out.println("-taxamt-"+taxamt);
      	    }
      	    if(ord.getEpcgRatetype().equals("NetUnit Price"))
      	    {
    	    linetot=linetot.add((new BigDecimal(strQty).multiply(ordlin.getUnitPrice())));	      	
      	    	
      	    }
      	    else
      	    {
	      	linetot=linetot.add((new BigDecimal(strQty).multiply(ordlin.getUnitPrice())));	      	
      	    }
	      	proformline.setProformalineqty(new BigDecimal(strQty));
	      	
	      	grtot=grtot.add((new BigDecimal(strQty).multiply(ordlin.getUnitPrice()))).add(taxamt);
	      	proformline.setNetUnitPrice(ordlin.getRcfrPrice());
	      	
	      	proformline.setInsurance((ordlin.getRcfrInsurance().divide(ordlin.getOrderedQuantity(),3)).multiply(new BigDecimal(strQty)));
	      	
	      	
	      	
	      	proform.setGrandTotalAmount(grtot);
	      	proform.setSummedLineAmount(linetot);
	      	
	      	
	      	proformline.setUnitPrice(ordlin.getUnitPrice());
	      	//System.out.println("6");
	      	proformline.setNetrate(ordlin.getUnitPrice());
	      	BigDecimal insurance=(new BigDecimal(strQty).multiply(ordlin.getRcfrInsurance()).divide(ordlin.getOrderedQuantity(),3));
	      	proformline.setInsurance(insurance);
	      	//System.out.println("7");
	     
	      	proformline.setTaxAmount(taxamt);
	     // 	(new BigDecimal(0).longValue());
	      	//(ordlin.getTaxableAmount().divide(ordlin.getOrderQuantity(),3)).multiply(new BigDecimal(strQty)).longValue()
	      	proformline.setLineNetAmount((new BigDecimal(strQty).multiply(ordlin.getUnitPrice())));
	      	System.out.println("Reached Last");
	    	OBDal.getInstance().save(proform);
	      	OBDal.getInstance().save(proformline);

	      	myError = new OBError();
	        myError.setType("Success");
	        myError.setTitle(OBMessageUtils.messageBD("Success"));
	        myError.setMessage(OBMessageUtils.messageBD("Copied Records") );
	        count++;
	        
	        
	      }
            
           
	      }
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }

    
	//
	
	//String id= SequenceIdData.getUUID();
    
	/**/
    
    /* try {
     conn = getTransactionConnection();
      StringTokenizer st = new StringTokenizer(strRownums, ",", false);
      ShipmentInOut inout = OBDal.getInstance().get(ShipmentInOut.class, strKey);
      String strBp=inout.getBusinessPartner().getId();
      String strOrg=inout.getOrganization().getId();
      
      ProformaInvoiceCreationData[] orderData = ProformaInvoiceCreationData.select(this, strBp, strOrg);
      

      BigDecimal discount, priceActual, priceList, netPriceList, grossPriceList, priceStd, priceLimit, priceGross, amtGross, pricestdgross;
      while (st.hasMoreTokens()) {
        String strRownum = st.nextToken().trim();
        String strmProductId = vars.getStringParameter("inpmProductId" + strRownum);
        String strrcgiContranslineId = vars.getStringParameter("inprcgiContranslineId" + strRownum);
        System.out.println("consignmenttransline id is" +strrcgiContranslineId);
        String strQty = vars.getNumericParameter("inpquantity" + strRownum);
        String strcBpartnerId = vars.getStringParameter("inpcBpartnerId" + strRownum);
        //String strcUOMId = vars.getStringParameter("inpcUOMId" + strRownum);
        String strMInoutlineID = SequenceIdData.getUUID();

        
        String strcUOMId = "";
        String strcOrderlineId = "";
        String strLocatorToId = "";
        if(strrcgiContranslineId != null)
        {
        	RCGIContransline contransl = OBDal.getInstance().get(RCGIContransline.class, strrcgiContranslineId);
        	strcUOMId = contransl.getUOM().getId();
        	strcOrderlineId = contransl.getSalesOrderLine().getId();
        	strLocatorToId = contransl.getNewStorageBin().getId();
        	
            //System.out.println("strcUOMId is" +strcUOMId);
            //System.out.println("strcOrderlineId is" +strcOrderlineId);
            //System.out.println("strLocatorToId is" +strLocatorToId);



        }


        try {
          ProformaInvoiceCreationData.insertCOrderline(conn, this, strMInoutlineID, strClient,
              orderData[0].adOrgId, vars.getUser(), strKey, orderData[0].cBpartnerId,
              
              strmProductId,  strcUOMId, strQty, strcOrderlineId, strLocatorToId,
              strrcgiContranslineId
             );
        } catch (ServletException ex) {
          myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          releaseRollbackConnection(conn);
          return myError;
        }
        count++;
      }

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
      
      */
      //e.printStackTrace();
      //log4j.warn("Rollback in transaction");
     // myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
    
   return myError;
  }

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
      String strKey, String strWindowId, String strTabId, String strBpartner,
      String strOrg, String strDateFrom) throws IOException, ServletException {
    log4j.debug("Output: Shipment");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/redcarpet/epcg/ad_forms/ProformaInvoiceCreation").createXmlDocument();
    ProformaInvoiceCreationData[] dataOrder = ProformaInvoiceCreationData.select(this, strKey);
    
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
    xmlDocument.setParameter("theme", vars.getTheme());
    xmlDocument.setParameter("key", strKey);
    xmlDocument.setParameter("windowId", strWindowId);
    xmlDocument.setParameter("tabId", strTabId);
    
    xmlDocument.setParameter("dateFrom", strDateFrom);
    
    xmlDocument
	.setParameter("calendar", vars.getLanguage().substring(0, 2));
    
    
		xmlDocument.setParameter("dateFromdisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateFromsaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		
		
		
		xmlDocument.setParameter("dateTodisplayFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		xmlDocument.setParameter("dateTosaveFormat",
				vars.getSessionValue("#AD_SqlDateFormat"));
		
		
    /*
    Order order = OBDal.getInstance().get(Order.class, strKey);
    ProformaInvoiceCreationData[] data = ProformaInvoiceCreationData.select(this, strBpartner, strmPricelistId,
        dataOrder[0].dateordered, order.getPriceList().isPriceIncludesTax() ? "Y" : "N", strSOTrx,
        dataOrder[0].lastDays.equals("") ? "0" : dataOrder[0].lastDays);
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
    xmlDocument.setParameter("theme", vars.getTheme());
    xmlDocument.setParameter("key", strKey);
    xmlDocument.setParameter("windowId", strWindowId);
    xmlDocument.setParameter("tabId", strTabId);
    xmlDocument.setParameter("sotrx", strSOTrx);
    xmlDocument.setParameter("yearactual", DateTimeData.sysdateYear(this));
    xmlDocument.setParameter("lastmonth", dataOrder[0].lastDays.equals("") ? "0"
        : dataOrder[0].lastDays);
    xmlDocument.setParameter(
        "pendingdelivery",
        strSOTrx.equals("Y") ? ProformaInvoiceCreationRecordData.pendingDeliverySales(this, strBpartner,
            dataOrder[0].adOrgId, dataOrder[0].adClientId) : ProformaInvoiceCreationRecordData
            .materialReceiptPending(this, strBpartner, dataOrder[0].adOrgId,
                dataOrder[0].adClientId));
    xmlDocument.setParameter(
        "pendingInvoice",
        strSOTrx.equals("Y") ? ProformaInvoiceCreationRecordData.pendingInvoiceSales(this, strBpartner,
            dataOrder[0].adOrgId, dataOrder[0].adClientId) : ProformaInvoiceCreationRecordData
            .purchasePendingInvoice(this, strBpartner, dataOrder[0].adOrgId,
                dataOrder[0].adClientId));
    xmlDocument.setParameter("debtpending", ProformaInvoiceCreationRecordData.debtPending(this, strBpartner,
        dataOrder[0].adOrgId, dataOrder[0].adClientId, strSOTrx));
    xmlDocument.setParameter("contact",
        ProformaInvoiceCreationRecordData.contact(this, dataOrder[0].adUserId));
    xmlDocument.setParameter("lastOrder", ProformaInvoiceCreationRecordData.maxDateordered(this,
        vars.getSqlDateFormat(), strBpartner, strSOTrx, dataOrder[0].adOrgId,
        dataOrder[0].adClientId));
    xmlDocument.setParameter("orgname", dataOrder[0].orgname);
    String strInvoicing = ProformaInvoiceCreationRecordData.invoicing(this, strSOTrx, strBpartner,
        dataOrder[0].adOrgId, dataOrder[0].adClientId);
    String strTotal = ProformaInvoiceCreationRecordData.invoicingTotal(this, strSOTrx, dataOrder[0].adOrgId,
        dataOrder[0].adClientId);
    xmlDocument.setParameter("invoicing", strInvoicing);
    xmlDocument.setParameter("bpartnername", dataOrder[0].bpartnername);

    BigDecimal invoicing, total, totalAverage;

    invoicing = (strInvoicing.equals("") ? ZERO : (new BigDecimal(strInvoicing)));
    total = (strTotal.equals("") ? ZERO : new BigDecimal(strTotal));
    String strTotalAverage = "";
    if (total == ZERO) {
      totalAverage = (invoicing.divide(total, 12, BigDecimal.ROUND_HALF_EVEN))
          .multiply(new BigDecimal("100"));
      totalAverage = totalAverage.setScale(2, BigDecimal.ROUND_HALF_UP);
      strTotalAverage = totalAverage.toPlainString();
      // int intscale = totalAverage.scale();
    }
*/
   // xmlDocument.setParameter("totalAverage", strTotalAverage);

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
}
