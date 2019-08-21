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
package com.redcarpet.rcssob.data;

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
import org.openbravo.model.common.businesspartner.TaxCategory;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductUOM;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.procurement.Requisition;
/**
 * Entity class for entity RCOB_Purchasequotationlines (stored in table RCOB_Purchasequotationlines).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PurchaseQuotationLines extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCOB_Purchasequotationlines";
    public static final String ENTITY_NAME = "RCOB_Purchasequotationlines";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCOBPURCHASEQUOTATION = "rcobPurchasequotation";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_ORDERUOM = "orderUOM";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_MATCHEDPOQTY = "matchedPOQty";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_OREDERDATE = "orederdate";
    public static final String PROPERTY_SCHEDULEDDELIVERYDATE = "scheduleddeliverydate";
    public static final String PROPERTY_NETUNITPRICE = "netUnitPrice";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_FREIGHTAMOUNT = "freightamount";
    public static final String PROPERTY_QUALITY = "quality";
    public static final String PROPERTY_REQUISITION = "requisition";
    public static final String PROPERTY_BUSINESSPARTNERTAXCATEGORY = "businessPartnerTaxCategory";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_LINENETAMOUNT = "lineNetAmount";
    public static final String PROPERTY_RCOBQUOTATIONTAXLIST = "rCOBQuotationtaxList";

    public PurchaseQuotationLines() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MATCHEDPOQTY, (long) 0);
        setDefaultValue(PROPERTY_NETUNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_FREIGHTAMOUNT, (long) 0);
        setDefaultValue(PROPERTY_LINENETAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCOBQUOTATIONTAXLIST, new ArrayList<Object>());
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

    public PurchaseQuotation getRcobPurchasequotation() {
        return (PurchaseQuotation) get(PROPERTY_RCOBPURCHASEQUOTATION);
    }

    public void setRcobPurchasequotation(PurchaseQuotation rcobPurchasequotation) {
        set(PROPERTY_RCOBPURCHASEQUOTATION, rcobPurchasequotation);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public ProductUOM getOrderUOM() {
        return (ProductUOM) get(PROPERTY_ORDERUOM);
    }

    public void setOrderUOM(ProductUOM orderUOM) {
        set(PROPERTY_ORDERUOM, orderUOM);
    }

    public BigDecimal getOrderQuantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        set(PROPERTY_ORDERQUANTITY, orderQuantity);
    }

    public Long getMatchedPOQty() {
        return (Long) get(PROPERTY_MATCHEDPOQTY);
    }

    public void setMatchedPOQty(Long matchedPOQty) {
        set(PROPERTY_MATCHEDPOQTY, matchedPOQty);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Date getOrederdate() {
        return (Date) get(PROPERTY_OREDERDATE);
    }

    public void setOrederdate(Date orederdate) {
        set(PROPERTY_OREDERDATE, orederdate);
    }

    public Date getScheduleddeliverydate() {
        return (Date) get(PROPERTY_SCHEDULEDDELIVERYDATE);
    }

    public void setScheduleddeliverydate(Date scheduleddeliverydate) {
        set(PROPERTY_SCHEDULEDDELIVERYDATE, scheduleddeliverydate);
    }

    public BigDecimal getNetUnitPrice() {
        return (BigDecimal) get(PROPERTY_NETUNITPRICE);
    }

    public void setNetUnitPrice(BigDecimal netUnitPrice) {
        set(PROPERTY_NETUNITPRICE, netUnitPrice);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Long getFreightamount() {
        return (Long) get(PROPERTY_FREIGHTAMOUNT);
    }

    public void setFreightamount(Long freightamount) {
        set(PROPERTY_FREIGHTAMOUNT, freightamount);
    }

    public String getQuality() {
        return (String) get(PROPERTY_QUALITY);
    }

    public void setQuality(String quality) {
        set(PROPERTY_QUALITY, quality);
    }

    public Requisition getRequisition() {
        return (Requisition) get(PROPERTY_REQUISITION);
    }

    public void setRequisition(Requisition requisition) {
        set(PROPERTY_REQUISITION, requisition);
    }

    public TaxCategory getBusinessPartnerTaxCategory() {
        return (TaxCategory) get(PROPERTY_BUSINESSPARTNERTAXCATEGORY);
    }

    public void setBusinessPartnerTaxCategory(TaxCategory businessPartnerTaxCategory) {
        set(PROPERTY_BUSINESSPARTNERTAXCATEGORY, businessPartnerTaxCategory);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public BigDecimal getLineNetAmount() {
        return (BigDecimal) get(PROPERTY_LINENETAMOUNT);
    }

    public void setLineNetAmount(BigDecimal lineNetAmount) {
        set(PROPERTY_LINENETAMOUNT, lineNetAmount);
    }

    @SuppressWarnings("unchecked")
    public List<Quotationtax> getRCOBQuotationtaxList() {
        return (List<Quotationtax>) get(PROPERTY_RCOBQUOTATIONTAXLIST);
    }

    public void setRCOBQuotationtaxList(List<Quotationtax> rCOBQuotationtaxList) {
        set(PROPERTY_RCOBQUOTATIONTAXLIST, rCOBQuotationtaxList);
    }

}
