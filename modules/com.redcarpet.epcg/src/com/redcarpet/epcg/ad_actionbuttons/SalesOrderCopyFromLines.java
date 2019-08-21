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
package com.redcarpet.epcg.ad_actionbuttons;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.pricing.pricelist.ProductPrice;
import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;
import org.openbravo.model.common.businesspartner.BusinessPartner;


public class SalesOrderCopyFromLines extends HttpSecureAppServlet {
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
      String strSOTrx = Utility.getContext(this, vars, "isSOTrx", strWindowId);
      String strKey = vars.getGlobalVariable("inpcOrderId", strWindowId + "|C_Order_ID");
      String strTabId = vars.getStringParameter("inpTabId");
      String strBpartner = vars.getStringParameter("inpcBpartnerId");
      String strmPricelistId = vars.getStringParameter("inpmPricelistId");
      
      printPageDataSheet(response, vars, strKey, strWindowId, strTabId, strSOTrx, strBpartner,
          strmPricelistId);
    } else if (vars.commandIn("SAVE")) {
      String strRownum = null;
      try {
        strRownum = vars.getRequiredInStringParameter("inpRownumId", IsPositiveIntFilter.instance);
      } catch (ServletException e) {
        log4j.error("Error captured: ", e);
        throw new ServletException(OBMessageUtils.messageBD("@JS1@"));
      }
      String strKey = vars.getRequiredStringParameter("inpcOrderId");
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

    if (strRownums.equals("")) {
      // return "";
      myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
      return myError;
    }
    Connection conn = null;
    try {
      conn = getTransactionConnection();
      StringTokenizer st = new StringTokenizer(strRownums, ",", false);
      SOCopyFromLinesRecordData[] orderData = SOCopyFromLinesRecordData.select(this, strKey);
      Order order = OBDal.getInstance().get(Order.class, strKey);
      
      BigDecimal discount, priceActual, priceList, netPriceList, grossPriceList, priceStd, priceLimit, priceGross, amtGross, pricestdgross;
      
      while (st.hasMoreTokens()) {
        String strRownum = st.nextToken().trim();
        String strmProductId = vars.getStringParameter("inpmProductId" + strRownum);
        //String strmAttributesetinstanceId = vars.getStringParameter("inpmAttributesetinstanceId"
           // + strRownum);
        String strLastpriceso = vars.getNumericParameter("inpLastpriceso" + strRownum);
        String strQty = vars.getNumericParameter("inpquantity" + strRownum);
        String strcTaxId = vars.getStringParameter("inpcTaxId" + strRownum);
        String strcUOMId = vars.getStringParameter("inpcUOMId" + strRownum);
        System.out.println("strcTaxId "+strcTaxId);
        String cashdisc= vars.getStringParameter("inpcashdiscount" + strRownum);
        String strcEpcgCostenquiryId = vars.getStringParameter("inpepcgCostenquiryId" + strRownum);
        //String strcUOMId = vars.getStringParameter("inpcUOMId" + strRownum);
        //String strcUOMId = vars.getStringParameter("inpcUOMId" + strRownum);
        //String strcUOMId = vars.getStringParameter("inpcUOMId" + strRownum);
        System.out.println("strmProductId "+strmProductId);
        System.out.println("strQty "+strQty);
        System.out.println("strcEpcgCostenquiryId "+strcEpcgCostenquiryId);
        System.out.println("UOM "+strcUOMId);
        
        
        String strCOrderlineID = SequenceIdData.getUUID();
        priceStd = new BigDecimal(SalesOrderCopyFromLinesData.getOffersStdPrice(this,
            orderData[0].cBpartnerId, strLastpriceso, strmProductId, orderData[0].dateordered,
            strQty, orderData[0].mPricelistId, strKey));
        ProductPrice prices = FinancialUtils.getProductPrice(
            OBDal.getInstance().get(Product.class, strmProductId), order.getOrderDate(),
            order.isSalesTransaction(), order.getPriceList(), false);
        if (prices != null) {
          priceLimit = prices.getPriceLimit();
          priceList = prices.getListPrice();
          pricestdgross = prices.getStandardPrice();
        } else {
          priceLimit = BigDecimal.ZERO;
          priceList = BigDecimal.ZERO;
          pricestdgross = BigDecimal.ZERO;
        }

        int stdPrecision = 2;
        int pricePrecision = 2;
        OBContext.setAdminMode(true);
        try {
          stdPrecision = order.getCurrency().getStandardPrecision().intValue();
          pricePrecision = order.getCurrency().getPricePrecision().intValue();
        } finally {
          OBContext.restorePreviousMode();
        }

        if (order.getPriceList().isPriceIncludesTax()) {
          BigDecimal qty = new BigDecimal(strQty);
          priceGross = (strLastpriceso.equals("") ? ZERO : new BigDecimal(strLastpriceso));
          log4j.debug("priceGross: in If condintion" + priceGross.toString());
          amtGross = priceGross.multiply(qty).setScale(stdPrecision, BigDecimal.ROUND_HALF_UP);
          priceActual = FinancialUtils.calculateNetFromGross(strcTaxId, amtGross, pricePrecision,
              amtGross, qty);
          priceLimit = priceActual;
          netPriceList = priceActual;
          grossPriceList = priceList;
        } else {
          priceActual = (strLastpriceso.equals("") ? ZERO : new BigDecimal(strLastpriceso));
          netPriceList = priceList;
          priceGross = (strLastpriceso.equals("") ? ZERO : new BigDecimal(strLastpriceso));
          log4j.debug("priceGross:" + priceGross.toString());
          amtGross = BigDecimal.ZERO;
          grossPriceList = BigDecimal.ZERO;
        }

        if (priceList.compareTo(BigDecimal.ZERO) == 0) {
          discount = ZERO;
        } else {
          log4j.debug("pricelist:" + priceList.toString());
          log4j.debug("priceActual:" + priceActual.toString());
          BigDecimal unitPrice;
          if (order.getPriceList().isPriceIncludesTax()) {
            unitPrice = pricestdgross;
          } else {
            unitPrice = priceActual;
          }
          // (PL-UP)/PL * 100
          discount = ((priceList.subtract(unitPrice)).multiply(new BigDecimal("100")).divide(
              priceList, stdPrecision, BigDecimal.ROUND_HALF_UP));
        }
        log4j.debug("Discount: " + discount.toString());
        if (priceStd.scale() > pricePrecision) {
          priceStd = priceStd.setScale(pricePrecision, BigDecimal.ROUND_HALF_UP);
        }
        //log4j.debug("strCOrderlineID: " + strCOrderlineID);
        //log4j.debug("orderData[0].adClientId: " + orderData[0].adClientId);
        //log4j.debug("orderData[0].adOrgId: " + orderData[0].adOrgId);
        //log4j.debug("vars.getUser(): " + vars.getUser());
        //log4j.debug("strKey: " + strKey);
        //log4j.debug("orderData[0].cBpartnerId: " + orderData[0].cBpartnerId);
        //log4j.debug("orderData[0].cBpartnerLocationId: " + orderData[0].cBpartnerLocationId);
        //log4j.debug("orderData[0].dateordered: " + orderData[0].dateordered);
        //log4j.debug("orderData[0].dateordered: " + orderData[0].dateordered);
        //log4j.debug("strmProductId: " + strmProductId);
        //log4j.debug("orderData[0].mWarehouseId: " + orderData[0].mWarehouseId);
        //log4j.debug("vars.getWarehouse(): " + vars.getWarehouse());
       // log4j.debug("strcUOMId: " + strcUOMId);
        //log4j.debug("orderData[0].cCurrencyId: " + orderData[0].cCurrencyId);
        //log4j.debug("netPriceList.toString(): " + netPriceList.toString());
        //log4j.debug("priceActual.toString(): " + priceActual.toString());
        String defaultTaxId = "76E0750020D541899B14027BE71E8124"; 
        try {
        	EpcgSalesOrderData.insertCOrderline(conn, this, strCOrderlineID, orderData[0].adClientId,
              orderData[0].adOrgId, vars.getUser(), strKey, orderData[0].cBpartnerId,
              orderData[0].cBpartnerLocationId, orderData[0].dateordered, orderData[0].dateordered,
              strmProductId, orderData[0].mWarehouseId.equals("") ? vars.getWarehouse()
                  : orderData[0].mWarehouseId, strcUOMId, strQty, orderData[0].cCurrencyId,
              netPriceList.toString(), priceActual.toString(), priceLimit.toString(), priceStd
                  .toString(), cashdisc, strcTaxId.equals("") ? defaultTaxId : strcTaxId, 
              grossPriceList.toString(), priceGross.toString(), amtGross.toString(), pricestdgross
                  .toString(),strcEpcgCostenquiryId);
        	order.setEpcgCopylines(Boolean.TRUE);
        	
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
      e.printStackTrace();
      log4j.warn("Rollback in transaction");
      myError = OBMessageUtils.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
    }
    return myError;
  }

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars,
      String strKey, String strWindowId, String strTabId, String strSOTrx, String strBpartner,
      String strmPricelistId) throws IOException, ServletException {
    log4j.debug("Output: Shipment");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/redcarpet/epcg/ad_actionbuttons/SalesOrderCopyFromLines").createXmlDocument();
    // Order Details
    SOCopyFromLinesRecordData[] dataOrder = SOCopyFromLinesRecordData.select(this, strKey);
    Order order = OBDal.getInstance().get(Order.class, strKey);
    
    // Lines List
    /*SalesOrderCopyFromLinesData[] data = SalesOrderCopyFromLinesData.select(this, strBpartner, strmPricelistId,
        dataOrder[0].dateordered, order.getPriceList().isPriceIncludesTax() ? "Y" : "N", strSOTrx,
        dataOrder[0].lastDays.equals("") ? "0" : dataOrder[0].lastDays);*/
    
    
    EpcgSalesOrderData[] data = EpcgSalesOrderData.selectcostenquiry(this, strBpartner,order.getEpcgCostenquiry().getId());
    
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
        strSOTrx.equals("Y") ? SOCopyFromLinesRecordData.pendingDeliverySales(this, strBpartner,
            dataOrder[0].adOrgId, dataOrder[0].adClientId) : SOCopyFromLinesRecordData
            .materialReceiptPending(this, strBpartner, dataOrder[0].adOrgId,
                dataOrder[0].adClientId));
    
    
    
    xmlDocument.setParameter(
        "pendingInvoice",
        strSOTrx.equals("Y") ? SOCopyFromLinesRecordData.pendingInvoiceSales(this, strBpartner,
            dataOrder[0].adOrgId, dataOrder[0].adClientId) : SOCopyFromLinesRecordData
            .purchasePendingInvoice(this, strBpartner, dataOrder[0].adOrgId,
                dataOrder[0].adClientId));
    
    
    xmlDocument.setParameter("debtpending", SOCopyFromLinesRecordData.debtPending(this, strBpartner,
        dataOrder[0].adOrgId, dataOrder[0].adClientId, strSOTrx));
    
    
    xmlDocument.setParameter("contact",
        SOCopyFromLinesRecordData.contact(this, dataOrder[0].adUserId));
    
    xmlDocument.setParameter("lastOrder", SOCopyFromLinesRecordData.maxDateordered(this,
        vars.getSqlDateFormat(), strBpartner, strSOTrx, dataOrder[0].adOrgId,
        dataOrder[0].adClientId));
    
    xmlDocument.setParameter("orgname", dataOrder[0].orgname);
    
    String strInvoicing = SOCopyFromLinesRecordData.invoicing(this, strSOTrx, strBpartner,
        dataOrder[0].adOrgId, dataOrder[0].adClientId);
    
    
    String strTotal = SOCopyFromLinesRecordData.invoicingTotal(this, strSOTrx, dataOrder[0].adOrgId,
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

    xmlDocument.setParameter("totalAverage", strTotalAverage);

    xmlDocument.setData("structure1", data);
    xmlDocument.setData("structure2", dataOrder);
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();

  }

  public String getServletInfo() {
    return "Servlet Copy from order";
  } // end of getServletInfo() method
}
