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
 * All portions are Copyright (C) 2013 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.openbravo.erpCommon.businessUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.plm.Product;

/**
 * This class is in charge of calculating prices for Discounts & Promotions of Price Adjustment
 * type. It is intended to be used from callouts so final price can be seen in advance when
 * editing/creating the line, opposite to the rest of promotions that are not calculated until the
 * document is posted. This is done in this way to keep backwards compatibility.
 * 
 * @author alostale
 * 
 */
public class PriceAdjustment {
  private static final Logger log = Logger.getLogger(PriceAdjustment.class);

  /**
   * Calculates price actual from price standard applying the Price Adjustments that fit the rules.
   * 
   */
  public static BigDecimal calculatePriceActual(BaseOBObject orderOrInvoice, Product product,
      BigDecimal qty, BigDecimal priceStd) {
    BigDecimal priceActual = priceStd;
    try {
      int precision = ((Currency) orderOrInvoice.get(Invoice.PROPERTY_CURRENCY))
          .getPricePrecision().intValue();
      for (org.openbravo.model.pricing.priceadjustment.PriceAdjustment promo : getApplicablePriceAdjustments(
          orderOrInvoice, qty, product, false)) {
        log.debug("promo: " + promo + "- " + promo.getDiscount());
        if (promo.getFixedPrice() != null) {
          priceActual = promo.getFixedPrice();
        } else {
          priceActual = priceActual
              .subtract(promo.getDiscountAmount())
              .multiply(
                  BigDecimal.ONE.subtract(promo.getDiscount().divide(BigDecimal.valueOf(100),
                      precision, BigDecimal.ROUND_HALF_UP)))
              .setScale(precision, BigDecimal.ROUND_HALF_UP);
        }
        if (!promo.isApplyNext()) {
          break;
        }
      }
      log.debug("Actual:" + priceStd + "->" + priceActual);
      return priceActual;
    } catch (Throwable t) {
      log.error("Error calculating price actual with adjustments, returning price std (" + priceStd
          + ") order/invoice:" + orderOrInvoice + " - product: " + product + " - qty:" + qty);
      return priceStd;
    }
  }

  /**
   * Calculates price standard from price actual reverse applying the Price Adjustments that fit the
   * rules.
   * 
   */
  public static BigDecimal calculatePriceStd(BaseOBObject orderOrInvoice, Product product,
      BigDecimal qty, BigDecimal priceActual) {
    BigDecimal priceStd = priceActual;
    try {
      int precision = ((Currency) orderOrInvoice.get(Invoice.PROPERTY_CURRENCY))
          .getPricePrecision().intValue();
      for (org.openbravo.model.pricing.priceadjustment.PriceAdjustment promo : getApplicablePriceAdjustments(
          orderOrInvoice, qty, product, true)) {
        log.debug("promo: " + promo + "- " + promo.getDiscount());
        priceStd = priceStd.add(promo.getDiscountAmount()).divide(
            BigDecimal.ONE.subtract(promo.getDiscount().divide(BigDecimal.valueOf(100), precision,
                BigDecimal.ROUND_HALF_UP)), precision, BigDecimal.ROUND_HALF_UP);
      }

      log.debug("Std:" + priceActual + "->" + priceStd);
      return priceStd;
    } catch (Throwable t) {
      log.error("Error calculating price std with adjustments, returning price actual ("
          + priceActual + ") order/invoice:" + orderOrInvoice + " - product: " + product
          + " - qty:" + qty);
      return priceActual;
    }
  }

  private static List<org.openbravo.model.pricing.priceadjustment.PriceAdjustment> getApplicablePriceAdjustments(
      BaseOBObject orderOrInvoice, BigDecimal qty, Product product, boolean reverse) {
    String hql = "as p ";
    hql += "where active = true ";
    hql += "and client = :client ";
    hql += "and (endingDate is null or endingDate>:date) ";
    hql += "and p.discountType.id = '5D4BAF6BB86D4D2C9ED3D5A6FC051579' ";
    hql += "and (minQuantity is null or minQuantity <= :qty) ";
    hql += "and (maxQuantity is null or maxQuantity >= :qty) ";

    // price list
    hql += "and ((includePriceLists='Y' ";
    hql += "  and not exists (select 1 ";
    hql += "         from PricingAdjustmentPriceList pl";
    hql += "        where active = true";
    hql += "          and pl.priceAdjustment = p";
    hql += "          and pl.priceList = :priceList)) ";
    hql += "   or (includePriceLists='N' ";
    hql += "  and  exists (select 1 ";
    hql += "         from PricingAdjustmentPriceList pl";
    hql += "        where active = true";
    hql += "          and pl.priceAdjustment = p";
    hql += "          and pl.priceList.id = :priceList)) ";
    hql += "    ) ";

    // Business Partner
    hql += "and ((includedBusinessPartners = 'Y' ";
    hql += "and not exists (select 1 ";
    hql += "        from PricingAdjustmentBusinessPartner bp";
    hql += "       where active = true";
    hql += "         and bp.priceAdjustment = p";
    hql += "         and bp.businessPartner = :bp)) ";
    hql += "      or (includedBusinessPartners = 'N'";
    hql += "  and exists (select 1";
    hql += "        from PricingAdjustmentBusinessPartner bp";
    hql += "         where active = true";
    hql += "         and bp.priceAdjustment = p";
    hql += "         and bp.businessPartner = :bp)) ";
    hql += "    ) ";

    // Business Partner Category
    hql += "and ((includedBPCategories = 'Y' ";
    hql += "and not exists (select 1 ";
    hql += "          from BusinessPartner bp, ";
    hql += "               PricingAdjustmentBusinessPartnerGroup bpc";
    hql += "         where bpc.active = true";
    hql += "           and bpc.priceAdjustment = p";
    hql += "           and bp = :bp";
    hql += "           and bp.businessPartnerCategory = bpc.businessPartnerCategory))";
    hql += "  or (includedBPCategories = 'N'";
    hql += " and exists (select 1 ";
    hql += "          from BusinessPartner bp, ";
    hql += "               PricingAdjustmentBusinessPartnerGroup bpc";
    hql += "         where bpc.active = true";
    hql += "           and bpc.priceAdjustment = p";
    hql += "           and bp = :bp";
    hql += "           and bp.businessPartnerCategory = bpc.businessPartnerCategory))";
    hql += "    ) ";

    // Product
    hql += "and ((includedProducts = 'Y' ";
    hql += "and not exists (select 1";
    hql += "          from PricingAdjustmentProduct pr";
    hql += "         where active = true";
    hql += "           and pr.priceAdjustment = p";
    hql += "           and pr.product = :product)) ";
    hql += "        or (includedProducts = 'N'";
    hql += "and exists (select 1";
    hql += "          from PricingAdjustmentProduct pr";
    hql += "         where active = true";
    hql += "           and pr.priceAdjustment = p";
    hql += "           and pr.product = :product)) ";
    hql += "    ) ";

    // Product Category
    hql += "and ((includedProductCategories ='Y' ";
    hql += "and not exists (select 1";
    hql += "          from PricingAdjustmentProductCategory pc, Product pr";
    hql += "          where pc.active = true";
    hql += "            and pc.priceAdjustment = p";
    hql += "            and pr = :product";
    hql += "            and pc.productCategory = pr.productCategory)) ";
    hql += "           or (includedProductCategories ='N'";
    hql += "and exists (select 1";
    hql += "          from PricingAdjustmentProductCategory pc, Product pr";
    hql += "          where pc.active = true";
    hql += "            and pc.priceAdjustment = p";
    hql += "            and pr = :product";
    hql += "            and pc.productCategory = pr.productCategory)) ";
    hql += "    ) ";

    // organization
    hql += "and ((includedOrganizations='Y' ";
    hql += "  and not exists (select 1 ";
    hql += "         from PricingAdjustmentOrganization o";
    hql += "        where active = true";
    hql += "          and o.priceAdjustment = p";
    hql += "          and o.organization.id = :org)) ";
    hql += "   or (includedOrganizations='N' ";
    hql += "  and  exists (select 1 ";
    hql += "         from PricingAdjustmentOrganization o";
    hql += "        where active = true";
    hql += "          and o.priceAdjustment = p";
    hql += "          and o.organization.id = :org )) ";
    hql += "    ) ";

    hql += " order by priority, id";

    OBQuery<org.openbravo.model.pricing.priceadjustment.PriceAdjustment> q = OBDal.getInstance()
        .createQuery(org.openbravo.model.pricing.priceadjustment.PriceAdjustment.class, hql);
    q.setNamedParameter("client", orderOrInvoice.get(Invoice.PROPERTY_CLIENT));
    q.setNamedParameter("org", orderOrInvoice.get(Invoice.PROPERTY_ORGANIZATION));
    q.setNamedParameter("priceList", orderOrInvoice.get(Invoice.PROPERTY_PRICELIST));
    q.setNamedParameter("bp", orderOrInvoice.get(Invoice.PROPERTY_BUSINESSPARTNER));

    if (orderOrInvoice instanceof Invoice) {
      q.setNamedParameter("date", ((Invoice) orderOrInvoice).getInvoiceDate());
    } else {
      q.setNamedParameter("date", ((Order) orderOrInvoice).getOrderDate());
    }
    q.setNamedParameter("qty", qty);
    q.setNamedParameter("product", product);

    List<org.openbravo.model.pricing.priceadjustment.PriceAdjustment> ql = q.list();
    List<org.openbravo.model.pricing.priceadjustment.PriceAdjustment> result = ql;
    if (reverse) {
      // when reversing the list, special care must be taken with cascades
      result = new ArrayList<org.openbravo.model.pricing.priceadjustment.PriceAdjustment>();
      for (int i = ql.size() - 1; i >= 0; i--) {
        org.openbravo.model.pricing.priceadjustment.PriceAdjustment promo = ql.get(i);
        if (!promo.isApplyNext()) {
          result = new ArrayList<org.openbravo.model.pricing.priceadjustment.PriceAdjustment>();
        }
        result.add(promo);
      }
    }
    return result;
  }
}
