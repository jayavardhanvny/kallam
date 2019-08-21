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
package org.openbravo.model.common.plm;

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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.uom.UOM;
/**
 * Entity class for entity ApprovedVendor (stored in table M_Product_PO).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ApprovedVendor extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Product_PO";
    public static final String ENTITY_NAME = "ApprovedVendor";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CURRENTVENDOR = "currentVendor";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_LISTPRICE = "listPrice";
    public static final String PROPERTY_PURCHASEORDERPRICE = "purchaseOrderPrice";
    public static final String PROPERTY_PRICEEFFECTIVEFROM = "priceEffectiveFrom";
    public static final String PROPERTY_LASTPURCHASEPRICE = "lastPurchasePrice";
    public static final String PROPERTY_LASTINVOICEPRICE = "lastInvoicePrice";
    public static final String PROPERTY_VENDORPRODUCTNO = "vendorProductNo";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_VENDORCATEGORY = "vendorCategory";
    public static final String PROPERTY_DISCONTINUED = "discontinued";
    public static final String PROPERTY_DISCONTINUEDBY = "discontinuedBy";
    public static final String PROPERTY_MINIMUMORDERQTY = "minimumOrderQty";
    public static final String PROPERTY_QUANTITYPERPACKAGE = "quantityPerPackage";
    public static final String PROPERTY_FIXEDCOSTPERORDER = "fixedCostPerOrder";
    public static final String PROPERTY_PURCHASINGLEADTIME = "purchasingLeadTime";
    public static final String PROPERTY_ACTUALDELIVERYDAYS = "actualDeliveryDays";
    public static final String PROPERTY_QUALITYRATING = "qualityRating";
    public static final String PROPERTY_ROYALTYAMOUNT = "royaltyAmount";
    public static final String PROPERTY_MANUFACTURER = "manufacturer";
    public static final String PROPERTY_CAPACITY = "capacity";
    public static final String PROPERTY_STANDARDQUANTITY = "standardQuantity";
    public static final String PROPERTY_QUANTITYTYPE = "quantityType";
    public static final String PROPERTY_PRODUCTORGLIST = "productOrgList";

    public ApprovedVendor() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CURRENTVENDOR, true);
        setDefaultValue(PROPERTY_DISCONTINUED, false);
        setDefaultValue(PROPERTY_PRODUCTORGLIST, new ArrayList<Object>());
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

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
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

    public Boolean isCurrentVendor() {
        return (Boolean) get(PROPERTY_CURRENTVENDOR);
    }

    public void setCurrentVendor(Boolean currentVendor) {
        set(PROPERTY_CURRENTVENDOR, currentVendor);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BigDecimal getListPrice() {
        return (BigDecimal) get(PROPERTY_LISTPRICE);
    }

    public void setListPrice(BigDecimal listPrice) {
        set(PROPERTY_LISTPRICE, listPrice);
    }

    public BigDecimal getPurchaseOrderPrice() {
        return (BigDecimal) get(PROPERTY_PURCHASEORDERPRICE);
    }

    public void setPurchaseOrderPrice(BigDecimal purchaseOrderPrice) {
        set(PROPERTY_PURCHASEORDERPRICE, purchaseOrderPrice);
    }

    public Date getPriceEffectiveFrom() {
        return (Date) get(PROPERTY_PRICEEFFECTIVEFROM);
    }

    public void setPriceEffectiveFrom(Date priceEffectiveFrom) {
        set(PROPERTY_PRICEEFFECTIVEFROM, priceEffectiveFrom);
    }

    public BigDecimal getLastPurchasePrice() {
        return (BigDecimal) get(PROPERTY_LASTPURCHASEPRICE);
    }

    public void setLastPurchasePrice(BigDecimal lastPurchasePrice) {
        set(PROPERTY_LASTPURCHASEPRICE, lastPurchasePrice);
    }

    public BigDecimal getLastInvoicePrice() {
        return (BigDecimal) get(PROPERTY_LASTINVOICEPRICE);
    }

    public void setLastInvoicePrice(BigDecimal lastInvoicePrice) {
        set(PROPERTY_LASTINVOICEPRICE, lastInvoicePrice);
    }

    public String getVendorProductNo() {
        return (String) get(PROPERTY_VENDORPRODUCTNO);
    }

    public void setVendorProductNo(String vendorProductNo) {
        set(PROPERTY_VENDORPRODUCTNO, vendorProductNo);
    }

    public String getUPCEAN() {
        return (String) get(PROPERTY_UPCEAN);
    }

    public void setUPCEAN(String uPCEAN) {
        set(PROPERTY_UPCEAN, uPCEAN);
    }

    public String getVendorCategory() {
        return (String) get(PROPERTY_VENDORCATEGORY);
    }

    public void setVendorCategory(String vendorCategory) {
        set(PROPERTY_VENDORCATEGORY, vendorCategory);
    }

    public Boolean isDiscontinued() {
        return (Boolean) get(PROPERTY_DISCONTINUED);
    }

    public void setDiscontinued(Boolean discontinued) {
        set(PROPERTY_DISCONTINUED, discontinued);
    }

    public Date getDiscontinuedBy() {
        return (Date) get(PROPERTY_DISCONTINUEDBY);
    }

    public void setDiscontinuedBy(Date discontinuedBy) {
        set(PROPERTY_DISCONTINUEDBY, discontinuedBy);
    }

    public BigDecimal getMinimumOrderQty() {
        return (BigDecimal) get(PROPERTY_MINIMUMORDERQTY);
    }

    public void setMinimumOrderQty(BigDecimal minimumOrderQty) {
        set(PROPERTY_MINIMUMORDERQTY, minimumOrderQty);
    }

    public BigDecimal getQuantityPerPackage() {
        return (BigDecimal) get(PROPERTY_QUANTITYPERPACKAGE);
    }

    public void setQuantityPerPackage(BigDecimal quantityPerPackage) {
        set(PROPERTY_QUANTITYPERPACKAGE, quantityPerPackage);
    }

    public BigDecimal getFixedCostPerOrder() {
        return (BigDecimal) get(PROPERTY_FIXEDCOSTPERORDER);
    }

    public void setFixedCostPerOrder(BigDecimal fixedCostPerOrder) {
        set(PROPERTY_FIXEDCOSTPERORDER, fixedCostPerOrder);
    }

    public Long getPurchasingLeadTime() {
        return (Long) get(PROPERTY_PURCHASINGLEADTIME);
    }

    public void setPurchasingLeadTime(Long purchasingLeadTime) {
        set(PROPERTY_PURCHASINGLEADTIME, purchasingLeadTime);
    }

    public Long getActualDeliveryDays() {
        return (Long) get(PROPERTY_ACTUALDELIVERYDAYS);
    }

    public void setActualDeliveryDays(Long actualDeliveryDays) {
        set(PROPERTY_ACTUALDELIVERYDAYS, actualDeliveryDays);
    }

    public Long getQualityRating() {
        return (Long) get(PROPERTY_QUALITYRATING);
    }

    public void setQualityRating(Long qualityRating) {
        set(PROPERTY_QUALITYRATING, qualityRating);
    }

    public BigDecimal getRoyaltyAmount() {
        return (BigDecimal) get(PROPERTY_ROYALTYAMOUNT);
    }

    public void setRoyaltyAmount(BigDecimal royaltyAmount) {
        set(PROPERTY_ROYALTYAMOUNT, royaltyAmount);
    }

    public String getManufacturer() {
        return (String) get(PROPERTY_MANUFACTURER);
    }

    public void setManufacturer(String manufacturer) {
        set(PROPERTY_MANUFACTURER, manufacturer);
    }

    public BigDecimal getCapacity() {
        return (BigDecimal) get(PROPERTY_CAPACITY);
    }

    public void setCapacity(BigDecimal capacity) {
        set(PROPERTY_CAPACITY, capacity);
    }

    public BigDecimal getStandardQuantity() {
        return (BigDecimal) get(PROPERTY_STANDARDQUANTITY);
    }

    public void setStandardQuantity(BigDecimal standardQuantity) {
        set(PROPERTY_STANDARDQUANTITY, standardQuantity);
    }

    public String getQuantityType() {
        return (String) get(PROPERTY_QUANTITYTYPE);
    }

    public void setQuantityType(String quantityType) {
        set(PROPERTY_QUANTITYTYPE, quantityType);
    }

    @SuppressWarnings("unchecked")
    public List<ProductOrg> getProductOrgList() {
        return (List<ProductOrg>) get(PROPERTY_PRODUCTORGLIST);
    }

    public void setProductOrgList(List<ProductOrg> productOrgList) {
        set(PROPERTY_PRODUCTORGLIST, productOrgList);
    }

}
