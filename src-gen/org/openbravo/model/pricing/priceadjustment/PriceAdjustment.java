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
package org.openbravo.model.pricing.priceadjustment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.invoice.InvoiceLineOffer;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.order.OrderLineOffer;
/**
 * Entity class for entity PricingAdjustment (stored in table M_Offer).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PriceAdjustment extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Offer";
    public static final String ENTITY_NAME = "PricingAdjustment";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PRIORITY = "priority";
    public static final String PROPERTY_DISCOUNTAMOUNT = "discountAmount";
    public static final String PROPERTY_DISCOUNT = "discount";
    public static final String PROPERTY_FIXEDPRICE = "fixedPrice";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_INCLUDEDBUSINESSPARTNERS = "includedBusinessPartners";
    public static final String PROPERTY_INCLUDEDBPCATEGORIES = "includedBPCategories";
    public static final String PROPERTY_INCLUDEDPRODUCTS = "includedProducts";
    public static final String PROPERTY_INCLUDEDPRODUCTCATEGORIES = "includedProductCategories";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_INCLUDEPRICELISTS = "includePriceLists";
    public static final String PROPERTY_MINQUANTITY = "minQuantity";
    public static final String PROPERTY_MAXQUANTITY = "maxQuantity";
    public static final String PROPERTY_DISCOUNTTYPE = "discountType";
    public static final String PROPERTY_APPLYNEXT = "applyNext";
    public static final String PROPERTY_PRINTNAME = "printName";
    public static final String PROPERTY_INCLUDEDORGANIZATIONS = "includedOrganizations";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_INVOICELINEOFFERLIST = "invoiceLineOfferList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_ORDERLINEOFFERLIST = "orderLineOfferList";
    public static final String PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST = "pricingAdjustmentBusinessPartnerList";
    public static final String PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERGROUPLIST = "pricingAdjustmentBusinessPartnerGroupList";
    public static final String PROPERTY_PRICINGADJUSTMENTORGANIZATIONLIST = "pricingAdjustmentOrganizationList";
    public static final String PROPERTY_PRICINGADJUSTMENTPRICELISTLIST = "pricingAdjustmentPriceListList";
    public static final String PROPERTY_PRICINGADJUSTMENTPRODUCTLIST = "pricingAdjustmentProductList";
    public static final String PROPERTY_PRICINGADJUSTMENTPRODUCTCATEGORYLIST = "pricingAdjustmentProductCategoryList";

    public PriceAdjustment() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DISCOUNTAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DISCOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_INCLUDEDBUSINESSPARTNERS, "Y");
        setDefaultValue(PROPERTY_INCLUDEDBPCATEGORIES, "Y");
        setDefaultValue(PROPERTY_INCLUDEDPRODUCTS, "Y");
        setDefaultValue(PROPERTY_INCLUDEDPRODUCTCATEGORIES, "Y");
        setDefaultValue(PROPERTY_INCLUDEPRICELISTS, "Y");
        setDefaultValue(PROPERTY_APPLYNEXT, true);
        setDefaultValue(PROPERTY_INCLUDEDORGANIZATIONS, "Y");
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEOFFERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEOFFERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERGROUPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTORGANIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTPRICELISTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTPRODUCTCATEGORYLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Long getPriority() {
        return (Long) get(PROPERTY_PRIORITY);
    }

    public void setPriority(Long priority) {
        set(PROPERTY_PRIORITY, priority);
    }

    public BigDecimal getDiscountAmount() {
        return (BigDecimal) get(PROPERTY_DISCOUNTAMOUNT);
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        set(PROPERTY_DISCOUNTAMOUNT, discountAmount);
    }

    public BigDecimal getDiscount() {
        return (BigDecimal) get(PROPERTY_DISCOUNT);
    }

    public void setDiscount(BigDecimal discount) {
        set(PROPERTY_DISCOUNT, discount);
    }

    public BigDecimal getFixedPrice() {
        return (BigDecimal) get(PROPERTY_FIXEDPRICE);
    }

    public void setFixedPrice(BigDecimal fixedPrice) {
        set(PROPERTY_FIXEDPRICE, fixedPrice);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public String getIncludedBusinessPartners() {
        return (String) get(PROPERTY_INCLUDEDBUSINESSPARTNERS);
    }

    public void setIncludedBusinessPartners(String includedBusinessPartners) {
        set(PROPERTY_INCLUDEDBUSINESSPARTNERS, includedBusinessPartners);
    }

    public String getIncludedBPCategories() {
        return (String) get(PROPERTY_INCLUDEDBPCATEGORIES);
    }

    public void setIncludedBPCategories(String includedBPCategories) {
        set(PROPERTY_INCLUDEDBPCATEGORIES, includedBPCategories);
    }

    public String getIncludedProducts() {
        return (String) get(PROPERTY_INCLUDEDPRODUCTS);
    }

    public void setIncludedProducts(String includedProducts) {
        set(PROPERTY_INCLUDEDPRODUCTS, includedProducts);
    }

    public String getIncludedProductCategories() {
        return (String) get(PROPERTY_INCLUDEDPRODUCTCATEGORIES);
    }

    public void setIncludedProductCategories(String includedProductCategories) {
        set(PROPERTY_INCLUDEDPRODUCTCATEGORIES, includedProductCategories);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getIncludePriceLists() {
        return (String) get(PROPERTY_INCLUDEPRICELISTS);
    }

    public void setIncludePriceLists(String includePriceLists) {
        set(PROPERTY_INCLUDEPRICELISTS, includePriceLists);
    }

    public BigDecimal getMinQuantity() {
        return (BigDecimal) get(PROPERTY_MINQUANTITY);
    }

    public void setMinQuantity(BigDecimal minQuantity) {
        set(PROPERTY_MINQUANTITY, minQuantity);
    }

    public BigDecimal getMaxQuantity() {
        return (BigDecimal) get(PROPERTY_MAXQUANTITY);
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        set(PROPERTY_MAXQUANTITY, maxQuantity);
    }

    public PromotionType getDiscountType() {
        return (PromotionType) get(PROPERTY_DISCOUNTTYPE);
    }

    public void setDiscountType(PromotionType discountType) {
        set(PROPERTY_DISCOUNTTYPE, discountType);
    }

    public Boolean isApplyNext() {
        return (Boolean) get(PROPERTY_APPLYNEXT);
    }

    public void setApplyNext(Boolean applyNext) {
        set(PROPERTY_APPLYNEXT, applyNext);
    }

    public String getPrintName() {
        return (String) get(PROPERTY_PRINTNAME);
    }

    public void setPrintName(String printName) {
        set(PROPERTY_PRINTNAME, printName);
    }

    public String getIncludedOrganizations() {
        return (String) get(PROPERTY_INCLUDEDORGANIZATIONS);
    }

    public void setIncludedOrganizations(String includedOrganizations) {
        set(PROPERTY_INCLUDEDORGANIZATIONS, includedOrganizations);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineOffer> getInvoiceLineOfferList() {
        return (List<InvoiceLineOffer>) get(PROPERTY_INVOICELINEOFFERLIST);
    }

    public void setInvoiceLineOfferList(List<InvoiceLineOffer> invoiceLineOfferList) {
        set(PROPERTY_INVOICELINEOFFERLIST, invoiceLineOfferList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineOffer> getOrderLineOfferList() {
        return (List<OrderLineOffer>) get(PROPERTY_ORDERLINEOFFERLIST);
    }

    public void setOrderLineOfferList(List<OrderLineOffer> orderLineOfferList) {
        set(PROPERTY_ORDERLINEOFFERLIST, orderLineOfferList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getPricingAdjustmentBusinessPartnerList() {
        return (List<BusinessPartner>) get(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST);
    }

    public void setPricingAdjustmentBusinessPartnerList(List<BusinessPartner> pricingAdjustmentBusinessPartnerList) {
        set(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST, pricingAdjustmentBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartnerGroup> getPricingAdjustmentBusinessPartnerGroupList() {
        return (List<BusinessPartnerGroup>) get(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERGROUPLIST);
    }

    public void setPricingAdjustmentBusinessPartnerGroupList(List<BusinessPartnerGroup> pricingAdjustmentBusinessPartnerGroupList) {
        set(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERGROUPLIST, pricingAdjustmentBusinessPartnerGroupList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationFilter> getPricingAdjustmentOrganizationList() {
        return (List<OrganizationFilter>) get(PROPERTY_PRICINGADJUSTMENTORGANIZATIONLIST);
    }

    public void setPricingAdjustmentOrganizationList(List<OrganizationFilter> pricingAdjustmentOrganizationList) {
        set(PROPERTY_PRICINGADJUSTMENTORGANIZATIONLIST, pricingAdjustmentOrganizationList);
    }

    @SuppressWarnings("unchecked")
    public List<PriceList> getPricingAdjustmentPriceListList() {
        return (List<PriceList>) get(PROPERTY_PRICINGADJUSTMENTPRICELISTLIST);
    }

    public void setPricingAdjustmentPriceListList(List<PriceList> pricingAdjustmentPriceListList) {
        set(PROPERTY_PRICINGADJUSTMENTPRICELISTLIST, pricingAdjustmentPriceListList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getPricingAdjustmentProductList() {
        return (List<Product>) get(PROPERTY_PRICINGADJUSTMENTPRODUCTLIST);
    }

    public void setPricingAdjustmentProductList(List<Product> pricingAdjustmentProductList) {
        set(PROPERTY_PRICINGADJUSTMENTPRODUCTLIST, pricingAdjustmentProductList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductCategory> getPricingAdjustmentProductCategoryList() {
        return (List<ProductCategory>) get(PROPERTY_PRICINGADJUSTMENTPRODUCTCATEGORYLIST);
    }

    public void setPricingAdjustmentProductCategoryList(List<ProductCategory> pricingAdjustmentProductCategoryList) {
        set(PROPERTY_PRICINGADJUSTMENTPRODUCTCATEGORYLIST, pricingAdjustmentProductCategoryList);
    }

}
