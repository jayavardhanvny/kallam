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
 * All portions are Copyright (C) 2008-2011 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.pricing.pricelist;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductCategory;
/**
 * Entity class for entity PricingPriceListSchemeLine (stored in table M_DiscountSchemaLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PriceListSchemeLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_DiscountSchemaLine";
    public static final String ENTITY_NAME = "PricingPriceListSchemeLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PRICELISTSCHEMA = "priceListSchema";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_CONVERSIONRATETYPE = "conversionRateType";
    public static final String PROPERTY_CONVERSIONDATE = "conversionDate";
    public static final String PROPERTY_BASELISTPRICE = "baseListPrice";
    public static final String PROPERTY_SURCHARGELISTPRICEAMOUNT = "surchargeListPriceAmount";
    public static final String PROPERTY_LISTPRICEDISCOUNT = "listPriceDiscount";
    public static final String PROPERTY_LISTPRICEROUNDING = "listPriceRounding";
    public static final String PROPERTY_MINLISTPRICEMARGIN = "minListPriceMargin";
    public static final String PROPERTY_MAXLISTPRICEMARGIN = "maxListPriceMargin";
    public static final String PROPERTY_FIXEDLISTPRICE = "fixedListPrice";
    public static final String PROPERTY_STANDARDBASEPRICE = "standardBasePrice";
    public static final String PROPERTY_SURCHARGESTANDARDPRICEAMOUNT = "surchargeStandardPriceAmount";
    public static final String PROPERTY_STANDARDPRICEDISCOUNT = "standardPriceDiscount";
    public static final String PROPERTY_STANDARDPRICEROUNDING = "standardPriceRounding";
    public static final String PROPERTY_MINSTANDARDPRICEMARGIN = "minStandardPriceMargin";
    public static final String PROPERTY_MAXSTANDARDMARGIN = "maxStandardMargin";
    public static final String PROPERTY_FIXEDSTANDARDPRICE = "fixedStandardPrice";
    public static final String PROPERTY_BASELIMITPRICE = "baseLimitPrice";
    public static final String PROPERTY_SURCHARGEPRICELIMITAMOUNT = "surchargePriceLimitAmount";
    public static final String PROPERTY_PRICELIMITDISCOUNT = "priceLimitDiscount";
    public static final String PROPERTY_PRICELIMITROUNDING = "priceLimitRounding";
    public static final String PROPERTY_LIMITPRICEMINMARGIN = "limitPriceMinMargin";
    public static final String PROPERTY_MAXPRICELIMITMARGIN = "maxPriceLimitMargin";
    public static final String PROPERTY_FIXEDLIMITPRICE = "fixedLimitPrice";
    public static final String PROPERTY_LISTPRICEMARGIN = "listPriceMargin";
    public static final String PROPERTY_UNITPRICEMARGIN = "unitPriceMargin";
    public static final String PROPERTY_LIMITPRICEMARGIN = "limitPriceMargin";

    public PriceListSchemeLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CONVERSIONRATETYPE, "S");
        setDefaultValue(PROPERTY_BASELISTPRICE, "L");
        setDefaultValue(PROPERTY_LISTPRICEROUNDING, "C");
        setDefaultValue(PROPERTY_STANDARDBASEPRICE, "S");
        setDefaultValue(PROPERTY_STANDARDPRICEROUNDING, "C");
        setDefaultValue(PROPERTY_BASELIMITPRICE, "X");
        setDefaultValue(PROPERTY_PRICELIMITROUNDING, "C");
        setDefaultValue(PROPERTY_LISTPRICEMARGIN, new BigDecimal(0));
        setDefaultValue(PROPERTY_UNITPRICEMARGIN, new BigDecimal(0));
        setDefaultValue(PROPERTY_LIMITPRICEMARGIN, new BigDecimal(0));
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public PriceListSchema getPriceListSchema() {
        return (PriceListSchema) get(PROPERTY_PRICELISTSCHEMA);
    }

    public void setPriceListSchema(PriceListSchema priceListSchema) {
        set(PROPERTY_PRICELISTSCHEMA, priceListSchema);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getConversionRateType() {
        return (String) get(PROPERTY_CONVERSIONRATETYPE);
    }

    public void setConversionRateType(String conversionRateType) {
        set(PROPERTY_CONVERSIONRATETYPE, conversionRateType);
    }

    public Date getConversionDate() {
        return (Date) get(PROPERTY_CONVERSIONDATE);
    }

    public void setConversionDate(Date conversionDate) {
        set(PROPERTY_CONVERSIONDATE, conversionDate);
    }

    public String getBaseListPrice() {
        return (String) get(PROPERTY_BASELISTPRICE);
    }

    public void setBaseListPrice(String baseListPrice) {
        set(PROPERTY_BASELISTPRICE, baseListPrice);
    }

    public BigDecimal getSurchargeListPriceAmount() {
        return (BigDecimal) get(PROPERTY_SURCHARGELISTPRICEAMOUNT);
    }

    public void setSurchargeListPriceAmount(BigDecimal surchargeListPriceAmount) {
        set(PROPERTY_SURCHARGELISTPRICEAMOUNT, surchargeListPriceAmount);
    }

    public BigDecimal getListPriceDiscount() {
        return (BigDecimal) get(PROPERTY_LISTPRICEDISCOUNT);
    }

    public void setListPriceDiscount(BigDecimal listPriceDiscount) {
        set(PROPERTY_LISTPRICEDISCOUNT, listPriceDiscount);
    }

    public String getListPriceRounding() {
        return (String) get(PROPERTY_LISTPRICEROUNDING);
    }

    public void setListPriceRounding(String listPriceRounding) {
        set(PROPERTY_LISTPRICEROUNDING, listPriceRounding);
    }

    public BigDecimal getMinListPriceMargin() {
        return (BigDecimal) get(PROPERTY_MINLISTPRICEMARGIN);
    }

    public void setMinListPriceMargin(BigDecimal minListPriceMargin) {
        set(PROPERTY_MINLISTPRICEMARGIN, minListPriceMargin);
    }

    public BigDecimal getMaxListPriceMargin() {
        return (BigDecimal) get(PROPERTY_MAXLISTPRICEMARGIN);
    }

    public void setMaxListPriceMargin(BigDecimal maxListPriceMargin) {
        set(PROPERTY_MAXLISTPRICEMARGIN, maxListPriceMargin);
    }

    public BigDecimal getFixedListPrice() {
        return (BigDecimal) get(PROPERTY_FIXEDLISTPRICE);
    }

    public void setFixedListPrice(BigDecimal fixedListPrice) {
        set(PROPERTY_FIXEDLISTPRICE, fixedListPrice);
    }

    public String getStandardBasePrice() {
        return (String) get(PROPERTY_STANDARDBASEPRICE);
    }

    public void setStandardBasePrice(String standardBasePrice) {
        set(PROPERTY_STANDARDBASEPRICE, standardBasePrice);
    }

    public BigDecimal getSurchargeStandardPriceAmount() {
        return (BigDecimal) get(PROPERTY_SURCHARGESTANDARDPRICEAMOUNT);
    }

    public void setSurchargeStandardPriceAmount(BigDecimal surchargeStandardPriceAmount) {
        set(PROPERTY_SURCHARGESTANDARDPRICEAMOUNT, surchargeStandardPriceAmount);
    }

    public BigDecimal getStandardPriceDiscount() {
        return (BigDecimal) get(PROPERTY_STANDARDPRICEDISCOUNT);
    }

    public void setStandardPriceDiscount(BigDecimal standardPriceDiscount) {
        set(PROPERTY_STANDARDPRICEDISCOUNT, standardPriceDiscount);
    }

    public String getStandardPriceRounding() {
        return (String) get(PROPERTY_STANDARDPRICEROUNDING);
    }

    public void setStandardPriceRounding(String standardPriceRounding) {
        set(PROPERTY_STANDARDPRICEROUNDING, standardPriceRounding);
    }

    public BigDecimal getMinStandardPriceMargin() {
        return (BigDecimal) get(PROPERTY_MINSTANDARDPRICEMARGIN);
    }

    public void setMinStandardPriceMargin(BigDecimal minStandardPriceMargin) {
        set(PROPERTY_MINSTANDARDPRICEMARGIN, minStandardPriceMargin);
    }

    public BigDecimal getMaxStandardMargin() {
        return (BigDecimal) get(PROPERTY_MAXSTANDARDMARGIN);
    }

    public void setMaxStandardMargin(BigDecimal maxStandardMargin) {
        set(PROPERTY_MAXSTANDARDMARGIN, maxStandardMargin);
    }

    public BigDecimal getFixedStandardPrice() {
        return (BigDecimal) get(PROPERTY_FIXEDSTANDARDPRICE);
    }

    public void setFixedStandardPrice(BigDecimal fixedStandardPrice) {
        set(PROPERTY_FIXEDSTANDARDPRICE, fixedStandardPrice);
    }

    public String getBaseLimitPrice() {
        return (String) get(PROPERTY_BASELIMITPRICE);
    }

    public void setBaseLimitPrice(String baseLimitPrice) {
        set(PROPERTY_BASELIMITPRICE, baseLimitPrice);
    }

    public BigDecimal getSurchargePriceLimitAmount() {
        return (BigDecimal) get(PROPERTY_SURCHARGEPRICELIMITAMOUNT);
    }

    public void setSurchargePriceLimitAmount(BigDecimal surchargePriceLimitAmount) {
        set(PROPERTY_SURCHARGEPRICELIMITAMOUNT, surchargePriceLimitAmount);
    }

    public BigDecimal getPriceLimitDiscount() {
        return (BigDecimal) get(PROPERTY_PRICELIMITDISCOUNT);
    }

    public void setPriceLimitDiscount(BigDecimal priceLimitDiscount) {
        set(PROPERTY_PRICELIMITDISCOUNT, priceLimitDiscount);
    }

    public String getPriceLimitRounding() {
        return (String) get(PROPERTY_PRICELIMITROUNDING);
    }

    public void setPriceLimitRounding(String priceLimitRounding) {
        set(PROPERTY_PRICELIMITROUNDING, priceLimitRounding);
    }

    public BigDecimal getLimitPriceMinMargin() {
        return (BigDecimal) get(PROPERTY_LIMITPRICEMINMARGIN);
    }

    public void setLimitPriceMinMargin(BigDecimal limitPriceMinMargin) {
        set(PROPERTY_LIMITPRICEMINMARGIN, limitPriceMinMargin);
    }

    public BigDecimal getMaxPriceLimitMargin() {
        return (BigDecimal) get(PROPERTY_MAXPRICELIMITMARGIN);
    }

    public void setMaxPriceLimitMargin(BigDecimal maxPriceLimitMargin) {
        set(PROPERTY_MAXPRICELIMITMARGIN, maxPriceLimitMargin);
    }

    public BigDecimal getFixedLimitPrice() {
        return (BigDecimal) get(PROPERTY_FIXEDLIMITPRICE);
    }

    public void setFixedLimitPrice(BigDecimal fixedLimitPrice) {
        set(PROPERTY_FIXEDLIMITPRICE, fixedLimitPrice);
    }

    public BigDecimal getListPriceMargin() {
        return (BigDecimal) get(PROPERTY_LISTPRICEMARGIN);
    }

    public void setListPriceMargin(BigDecimal listPriceMargin) {
        set(PROPERTY_LISTPRICEMARGIN, listPriceMargin);
    }

    public BigDecimal getUnitPriceMargin() {
        return (BigDecimal) get(PROPERTY_UNITPRICEMARGIN);
    }

    public void setUnitPriceMargin(BigDecimal unitPriceMargin) {
        set(PROPERTY_UNITPRICEMARGIN, unitPriceMargin);
    }

    public BigDecimal getLimitPriceMargin() {
        return (BigDecimal) get(PROPERTY_LIMITPRICEMARGIN);
    }

    public void setLimitPriceMargin(BigDecimal limitPriceMargin) {
        set(PROPERTY_LIMITPRICEMARGIN, limitPriceMargin);
    }

}
