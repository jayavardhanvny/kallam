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
 * All portions are Copyright (C) 2001-2013 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.openbravo.erpCommon.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.businessUtility.PriceAdjustment;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.financial.FinancialUtils;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.utils.FormatUtilities;
import org.openbravo.xmlEngine.XmlDocument;

public class SL_Order_Amt extends HttpSecureAppServlet {

    private static final long serialVersionUID = 1L;
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    @Override
    public void init(ServletConfig config) {
        super.init(config);
        boolHist = false;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        VariablesSecureApp vars = new VariablesSecureApp(request);
        if (vars.commandIn("DEFAULT")) {
            String strChanged = vars.getStringParameter("inpLastFieldChanged");
            log4j.debug("CHANGED: " + strChanged);
            String strQtyOrdered = vars.getNumericParameter("inpqtyordered");
            String strPriceActual = vars.getNumericParameter("inppriceactual");
            String strDiscount = vars.getNumericParameter("inpdiscount");
            String strPriceLimit = vars.getNumericParameter("inppricelimit");
            String strPriceList = vars.getNumericParameter("inppricelist");
            String strPriceStd = vars.getNumericParameter("inppricestd");
            String strCOrderId = vars.getStringParameter("inpcOrderId");
            String strProduct = vars.getStringParameter("inpmProductId");
            String strUOM = vars.getStringParameter("inpcUomId");
            String strAttribute = vars.getStringParameter("inpmAttributesetinstanceId");
            String strQty = vars.getNumericParameter("inpqtyordered");
            boolean cancelPriceAd = "Y".equals(vars.getStringParameter("inpcancelpricead"));
            String strLineNetAmt = vars.getNumericParameter("inplinenetamt");
            String strTaxId = vars.getStringParameter("inpcTaxId");
            String strGrossUnitPrice = vars.getNumericParameter("inpgrossUnitPrice");
            String strGrossPriceList = vars.getNumericParameter("inpgrosspricelist");
            String strGrossBaseUnitPrice = vars.getNumericParameter("inpgrosspricestd");
            String strtaxbaseamt = vars.getNumericParameter("inptaxbaseamt");

            try {
                printPage(response, vars, strChanged, strQtyOrdered, strPriceActual, strDiscount,
                        strPriceLimit, strPriceList, strCOrderId, strProduct, strUOM, strAttribute, strQty,
                        strPriceStd, cancelPriceAd, strLineNetAmt, strTaxId, strGrossUnitPrice,
                        strGrossPriceList, strtaxbaseamt, strGrossBaseUnitPrice);
            } catch (ServletException ex) {
                pageErrorCallOut(response);
            }
        } else {
            pageError(response);
        }
    }

    private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strChanged,
            String strQtyOrdered, String _strPriceActual, String strDiscount, String strPriceLimit,
            String strPriceList, String strCOrderId, String strProduct, String strUOM,
            String strAttribute, String strQty, String strPriceStd, boolean cancelPriceAd,
            String strLineNetAmt, String strTaxId, String strGrossUnitPrice, String strGrossPriceList,
            String strTaxBaseAmt, String strGrossBaseUnitPrice) throws IOException, ServletException {
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
                "org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        SLOrderAmtData[] data = SLOrderAmtData.select(this, strCOrderId);
        SLOrderStockData[] data1 = SLOrderStockData.select(this, strProduct);
        String strPrecision = "0", strPricePrecision = "0";
        String strStockSecurity = "0";
        String strEnforceAttribute = "N";
        String isSOTrx = SLOrderStockData.isSotrx(this, strCOrderId);
        String strStockNoAttribute;
        String strStockAttribute;
        String strPriceActual = _strPriceActual;
        boolean isTaxIncludedPriceList = OBDal.getInstance().get(PriceList.class, data[0].mPricelistId)
                .isPriceIncludesTax();
        boolean isGrossUnitPriceChanged = strChanged.equals("inpgrossUnitPrice");

        if (data1 != null && data1.length > 0) {
            strStockSecurity = data1[0].stock;
            strEnforceAttribute = data1[0].enforceAttribute;
        }
        // boolean isUnderLimit=false;
        if (data != null && data.length > 0) {
            strPrecision = data[0].stdprecision.equals("") ? "0" : data[0].stdprecision;
            strPricePrecision = data[0].priceprecision.equals("") ? "0" : data[0].priceprecision;
        }
        int stdPrecision = Integer.valueOf(strPrecision).intValue();
        int pricePrecision = Integer.valueOf(strPricePrecision).intValue();

        BigDecimal qtyOrdered, priceActual, priceLimit, netPriceList, stockSecurity, stockNoAttribute, stockAttribute, resultStock, priceStd, lineNetAmt, taxBaseAmt;
        stockSecurity = new BigDecimal(strStockSecurity);
        qtyOrdered = (strQtyOrdered.equals("") ? ZERO : new BigDecimal(strQtyOrdered));
        priceActual = (strPriceActual.equals("") ? ZERO : (new BigDecimal(strPriceActual))).setScale(
                pricePrecision, BigDecimal.ROUND_HALF_UP);
        priceLimit = (strPriceLimit.equals("") ? ZERO : (new BigDecimal(strPriceLimit))).setScale(
                pricePrecision, BigDecimal.ROUND_HALF_UP);
        netPriceList = (strPriceList.equals("") ? ZERO : (new BigDecimal(strPriceList))).setScale(
                pricePrecision, BigDecimal.ROUND_HALF_UP);
        priceStd = (strPriceStd.equals("") ? ZERO : (new BigDecimal(strPriceStd))).setScale(
                pricePrecision, BigDecimal.ROUND_HALF_UP);
        lineNetAmt = (strLineNetAmt.equals("") ? ZERO : (new BigDecimal(strLineNetAmt))).setScale(
                pricePrecision, BigDecimal.ROUND_HALF_UP);
        taxBaseAmt = (strTaxBaseAmt.equals("") ? ZERO : (new BigDecimal(strTaxBaseAmt))).setScale(
                pricePrecision, BigDecimal.ROUND_HALF_UP);
        BigDecimal grossUnitPrice = (strGrossUnitPrice.equals("") ? ZERO : new BigDecimal(
                strGrossUnitPrice).setScale(pricePrecision, BigDecimal.ROUND_HALF_UP));
        BigDecimal grossPriceList = (strGrossPriceList.equals("") ? ZERO : new BigDecimal(
                strGrossPriceList).setScale(pricePrecision, BigDecimal.ROUND_HALF_UP));
        BigDecimal grossBaseUnitPrice = (strGrossBaseUnitPrice.equals("") ? ZERO : new BigDecimal(
                strGrossBaseUnitPrice).setScale(pricePrecision, BigDecimal.ROUND_HALF_UP));

        StringBuffer resultado = new StringBuffer();
        resultado.append("var calloutName='SL_Order_Amt';\n\n");
        resultado.append("var respuesta = new Array(");

        Order order = OBDal.getInstance().get(Order.class, strCOrderId);
        Product product = OBDal.getInstance().get(Product.class, strProduct);

        if (strChanged.equals("inplinenetamt")) {
            priceActual = lineNetAmt.divide(qtyOrdered, pricePrecision, BigDecimal.ROUND_HALF_UP);
            if (priceActual.compareTo(BigDecimal.ZERO) == 0) {
                lineNetAmt = BigDecimal.ZERO;
            }
        }

        if (strChanged.equals("inpqtyordered") && !cancelPriceAd) {
            if (isTaxIncludedPriceList) {
                grossUnitPrice = PriceAdjustment.calculatePriceActual(order, product, qtyOrdered,
                        grossBaseUnitPrice);
                BigDecimal grossAmount = grossUnitPrice.multiply(qtyOrdered).setScale(stdPrecision,
                        RoundingMode.HALF_UP);
                priceActual = FinancialUtils.calculateNetFromGross(strTaxId, grossAmount, pricePrecision,
                        taxBaseAmt, qtyOrdered);
                resultado.append("new Array(\"inpgrossUnitPrice\", " + grossUnitPrice.toString() + "),");
            } else {
                priceActual = PriceAdjustment.calculatePriceActual(order, product, qtyOrdered, priceStd);
            }
            resultado.append("new Array(\"inppriceactual\", " + priceActual + "),");
        }
        // Calculating prices for offers...
        if (strChanged.equals("inppriceactual") || strChanged.equals("inplinenetamt")) {
            log4j.debug("priceActual:" + priceActual.toString());
            if (!cancelPriceAd) {
                priceStd = PriceAdjustment.calculatePriceStd(order, product, qtyOrdered, priceActual);
            } else {
                priceStd = priceActual;
            }
            resultado.append("new Array(\"inppricestd\", " + priceStd.toString() + "),");
        }

        if (strChanged.equals("inpcancelpricead")) {
            if (cancelPriceAd) {
                resultado.append("new Array(\"inppriceactual\", " + strPriceStd + "),");
            }
        }

        // if taxinclusive field is changed then modify net unit price and gross price
        if (isGrossUnitPriceChanged || (strChanged.equals("inpcTaxId") && isTaxIncludedPriceList)) {
            BigDecimal grossAmount = grossUnitPrice.multiply(qtyOrdered).setScale(stdPrecision,
                    RoundingMode.HALF_UP);

            final BigDecimal netUnitPrice = FinancialUtils.calculateNetFromGross(strTaxId, grossAmount,
                    pricePrecision, taxBaseAmt, qtyOrdered);

            priceActual = netUnitPrice;
            priceStd = netUnitPrice;
            grossBaseUnitPrice = grossUnitPrice;
            resultado.append("new Array(\"inpgrosspricestd\", " + grossBaseUnitPrice.toString() + "),");

            resultado.append("new Array(\"inppriceactual\"," + priceActual.toString() + "),");
            resultado.append("new Array(\"inppricelimit\", " + netUnitPrice.toString() + "),");
            resultado.append("new Array(\"inppricestd\"," + netUnitPrice.toString() + "),");
        }

        if (isGrossUnitPriceChanged || (strChanged.equals("inpcTaxId") && isTaxIncludedPriceList)) {
            BigDecimal grossAmount = grossUnitPrice.multiply(qtyOrdered).setScale(stdPrecision,
                    RoundingMode.HALF_UP);

            final BigDecimal netUnitPrice = FinancialUtils.calculateNetFromGross(strTaxId, grossAmount,
                    pricePrecision, taxBaseAmt, qtyOrdered);

            priceActual = netUnitPrice;

            if (cancelPriceAd) {
                grossBaseUnitPrice = grossUnitPrice;
                priceStd = netUnitPrice;
            } else {
                grossBaseUnitPrice = PriceAdjustment.calculatePriceStd(order, product, qtyOrdered,
                        grossUnitPrice);
                BigDecimal baseGrossAmount = grossBaseUnitPrice.multiply(qtyOrdered).setScale(stdPrecision,
                        RoundingMode.HALF_UP);
                priceStd = FinancialUtils.calculateNetFromGross(strTaxId, baseGrossAmount, pricePrecision,
                        taxBaseAmt, qtyOrdered);
            }

            resultado.append("new Array(\"inpgrosspricestd\", " + grossBaseUnitPrice.toString() + "),");

            resultado.append("new Array(\"inppriceactual\"," + priceActual.toString() + "),");
            resultado.append("new Array(\"inppricelimit\", " + netUnitPrice.toString() + "),");
            resultado.append("new Array(\"inppricestd\"," + priceStd.toString() + "),");
        }

        // calculating discount
        if (strChanged.equals("inppricelist") || strChanged.equals("inppriceactual")
                || strChanged.equals("inplinenetamt") || strChanged.equals("inpgrosspricelist")
                || strChanged.equals("inpgrossUnitPrice")) {
            BigDecimal priceList = BigDecimal.ZERO;
            BigDecimal unitPrice = BigDecimal.ZERO;
            BigDecimal discount;

            if (isTaxIncludedPriceList) {
                priceList = grossPriceList;
                unitPrice = grossBaseUnitPrice;
            } else {
                priceList = netPriceList;
                unitPrice = priceStd;
            }
            if (priceList.compareTo(BigDecimal.ZERO) == 0) {
                discount = ZERO;
            } else {
                log4j.debug("pricelist:" + priceList.toString());
                log4j.debug("unit price:" + unitPrice.toString());
                discount = priceList.subtract(unitPrice).multiply(new BigDecimal("100"))
                        .divide(priceList, stdPrecision, BigDecimal.ROUND_HALF_EVEN);
                discount = ZERO;
            }
            log4j.debug("Discount rounded: " + discount.toString());
            resultado.append("new Array(\"inpdiscount\", " + discount.toString() + "),");

        } else if (strChanged.equals("inpdiscount")) { // calculate std and actual
            BigDecimal origDiscount = null;
            BigDecimal priceList;
            if (isTaxIncludedPriceList) {
                priceList = grossPriceList;
            } else {
                priceList = netPriceList;
            }
            if (priceList.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal baseUnitPrice = BigDecimal.ZERO;
                if (isTaxIncludedPriceList) {
                    baseUnitPrice = grossBaseUnitPrice;
                } else {
                    baseUnitPrice = priceStd;
                }
                origDiscount = priceList.subtract(baseUnitPrice).multiply(new BigDecimal("100"))
                        .divide(priceList, stdPrecision, BigDecimal.ROUND_HALF_UP);
                origDiscount = BigDecimal.ZERO;
            } else {
                origDiscount = BigDecimal.ZERO;
            }
            BigDecimal newDiscount = (strDiscount.equals("") ? ZERO : new BigDecimal(strDiscount)
                    .setScale(stdPrecision, BigDecimal.ROUND_HALF_UP));
            newDiscount = BigDecimal.ZERO;

            if (origDiscount.compareTo(newDiscount) != 0) {
                BigDecimal baseUnitPrice = priceList.subtract(priceList.multiply(newDiscount).divide(
                        new BigDecimal("100"), pricePrecision, BigDecimal.ROUND_HALF_UP));
                if (isTaxIncludedPriceList) {
                    grossUnitPrice = PriceAdjustment.calculatePriceActual(order, product, qtyOrdered,
                            baseUnitPrice);
                    resultado.append("new Array(\"inpgrosspricestd\", " + baseUnitPrice.toString() + "),");
                    resultado.append("new Array(\"inpgrossUnitPrice\", " + grossUnitPrice.toString() + "),");

                    // set also net prices
                    BigDecimal grossAmount = grossUnitPrice.multiply(qtyOrdered).setScale(stdPrecision,
                            RoundingMode.HALF_UP);

                    final BigDecimal netUnitPrice = FinancialUtils.calculateNetFromGross(strTaxId,
                            grossAmount, pricePrecision, taxBaseAmt, qtyOrdered);

                    priceStd = netUnitPrice;
                } else {
                    priceStd = baseUnitPrice;
                }

                if (!cancelPriceAd) {
                    priceActual = PriceAdjustment.calculatePriceActual(order, product, qtyOrdered, priceStd);
                } else {
                    priceActual = priceStd;
                }
                resultado.append("new Array(\"inppriceactual\", " + priceActual.toString() + "),");
                resultado.append("new Array(\"inppricestd\", " + priceStd.toString() + "),");
            }
        }

        if (isSOTrx.equals("Y") && !strStockSecurity.equals("0")
                && qtyOrdered.compareTo(BigDecimal.ZERO) != 0) {
            if (strEnforceAttribute.equals("N")) {
                strStockNoAttribute = SLOrderStockData.totalStockNoAttribute(this, strProduct, strUOM);
                stockNoAttribute = new BigDecimal(strStockNoAttribute);
                resultStock = stockNoAttribute.subtract(qtyOrdered);
                if (stockSecurity.compareTo(resultStock) > 0) {
                    resultado
                            .append("new Array('MESSAGE', \""
                            + FormatUtilities.replaceJS(Utility.messageBD(this, "StockLimit",
                            vars.getLanguage())) + "\"),");
                }
            } else if (!strAttribute.equals("") && strAttribute != null) {
                strStockAttribute = SLOrderStockData.totalStockAttribute(this, strProduct, strUOM,
                        strAttribute);
                stockAttribute = new BigDecimal(strStockAttribute);
                resultStock = stockAttribute.subtract(qtyOrdered);
                if (stockSecurity.compareTo(resultStock) > 0) {
                    resultado
                            .append("new Array('MESSAGE', \""
                            + FormatUtilities.replaceJS(Utility.messageBD(this, "StockLimit",
                            vars.getLanguage())) + "\"),");
                }
            }
        }
        log4j.debug(resultado.toString());
        if (!strChanged.equals("inpqtyordered") || strChanged.equals("inplinenetamt")) {
            // Check PriceLimit
            boolean enforced = SLOrderAmtData.listPriceType(this, strPriceList);
            // Check Price Limit?
            if (enforced && priceLimit.compareTo(BigDecimal.ZERO) != 0
                    && priceActual.compareTo(priceLimit) < 0) {
                resultado.append("new Array('MESSAGE', \""
                        + Utility.messageBD(this, "UnderLimitPrice", vars.getLanguage()) + "\")");
            }
        }

        // if net unit price changed then modify tax inclusive unit price
        if (strChanged.equals("inppriceactual")) {
            priceActual = new BigDecimal(strPriceActual.trim());
            log4j.debug("Net unit price results: " + resultado.toString());
        }
        // Multiply
        if (cancelPriceAd) {
            lineNetAmt = qtyOrdered.multiply(priceStd);
        } else {
            if (!strChanged.equals("inplinenetamt")) {
                lineNetAmt = qtyOrdered.multiply(priceActual);
                if (lineNetAmt.scale() > stdPrecision) {
                    lineNetAmt = lineNetAmt.setScale(stdPrecision, BigDecimal.ROUND_HALF_UP);
                }
            }
        }
        if (strChanged.equals("inplinenetamt")) {
            resultado.append("new Array(\"inppriceactual\", " + priceActual.toString() + "),");
            resultado.append("new Array(\"inptaxbaseamt\", " + lineNetAmt.toString() + "),");
        }
        if (!strChanged.equals("inplinenetamt") || priceActual.compareTo(BigDecimal.ZERO) == 0) {
            resultado.append("new Array(\"inplinenetamt\", " + lineNetAmt.toString() + "),");
        }
        if (!strChanged.equals("inplineGrossAmount")) {
            BigDecimal grossLineAmt = grossUnitPrice.multiply(qtyOrdered).setScale(stdPrecision,
                    BigDecimal.ROUND_HALF_UP);
            resultado.append("new Array(\"inplineGrossAmount\", " + grossLineAmt.toString() + "),");
        }
        resultado.append("new Array(\"inptaxbaseamt\", " + lineNetAmt.toString() + "),");
        resultado.append("new Array(\"dummy\", \"\" )");

        // mateen calculation starts here 
        if (OBDal.getInstance().get(Order.class, strCOrderId).isSalesTransaction()) {
            String strNetUnitRate_m = vars.getNumericParameter("inpemRcfrNetunitrate");
            BigDecimal netUnitRate = new BigDecimal(StringUtils.endsWith("", strNetUnitRate_m) ? "0" : strNetUnitRate_m);
            String strTaxId_m = vars.getStringParameter("inpcTaxId");
            TaxRate tax = OBDal.getInstance().get(TaxRate.class, strTaxId_m);
            BigDecimal temp = new BigDecimal(1).add((tax.getRate().divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
            BigDecimal netUnitPrice = (netUnitRate.divide(temp, 6, RoundingMode.HALF_UP)).multiply(tax.getRate().multiply(new BigDecimal(0.01)));
            String strResult = netUnitRate.subtract(netUnitPrice).toString();
            resultado.append(",new Array(\"inppriceactual\", " + strResult + "),");
            resultado.append("new Array(\"dummy\", \"\" )");
        }
        resultado.append(");");
        xmlDocument.setParameter("array", resultado.toString());
        log4j.debug("Callout for field changed: " + strChanged + " is " + resultado.toString());
        xmlDocument.setParameter("frameName", "appFrame");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
    }
}
