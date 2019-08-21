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
package org.openbravo.model.sales;

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
import org.openbravo.model.common.businesspartner.Category;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductCategory;
/**
 * Entity class for entity SalesCommissionLine (stored in table C_CommissionLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CommissionLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_CommissionLine";
    public static final String ENTITY_NAME = "SalesCommissionLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_COMMISSION = "commission";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SUBTRACTAMOUNT = "subtractAmount";
    public static final String PROPERTY_MULTIPLIERAMOUNT = "multiplierAmount";
    public static final String PROPERTY_SUBTRACTQUANTITY = "subtractQuantity";
    public static final String PROPERTY_MULTIPLIERQUANTITY = "multiplierQuantity";
    public static final String PROPERTY_POSITIVEONLY = "positiveOnly";
    public static final String PROPERTY_COMMISSIONONLYSPECIFIEDORDERS = "commissionOnlySpecifiedOrders";
    public static final String PROPERTY_TRXORGANIZATION = "trxOrganization";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORY = "businessPartnerCategory";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_SALESREGION = "salesRegion";
    public static final String PROPERTY_EXCLUDE = "exclude";
    public static final String PROPERTY_SALESCOMMISSIONAMOUNTLIST = "salesCommissionAmountList";

    public CommissionLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_POSITIVEONLY, false);
        setDefaultValue(PROPERTY_COMMISSIONONLYSPECIFIEDORDERS, false);
        setDefaultValue(PROPERTY_EXCLUDE, false);
        setDefaultValue(PROPERTY_SALESCOMMISSIONAMOUNTLIST, new ArrayList<Object>());
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

    public Commission getCommission() {
        return (Commission) get(PROPERTY_COMMISSION);
    }

    public void setCommission(Commission commission) {
        set(PROPERTY_COMMISSION, commission);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BigDecimal getSubtractAmount() {
        return (BigDecimal) get(PROPERTY_SUBTRACTAMOUNT);
    }

    public void setSubtractAmount(BigDecimal subtractAmount) {
        set(PROPERTY_SUBTRACTAMOUNT, subtractAmount);
    }

    public BigDecimal getMultiplierAmount() {
        return (BigDecimal) get(PROPERTY_MULTIPLIERAMOUNT);
    }

    public void setMultiplierAmount(BigDecimal multiplierAmount) {
        set(PROPERTY_MULTIPLIERAMOUNT, multiplierAmount);
    }

    public BigDecimal getSubtractQuantity() {
        return (BigDecimal) get(PROPERTY_SUBTRACTQUANTITY);
    }

    public void setSubtractQuantity(BigDecimal subtractQuantity) {
        set(PROPERTY_SUBTRACTQUANTITY, subtractQuantity);
    }

    public BigDecimal getMultiplierQuantity() {
        return (BigDecimal) get(PROPERTY_MULTIPLIERQUANTITY);
    }

    public void setMultiplierQuantity(BigDecimal multiplierQuantity) {
        set(PROPERTY_MULTIPLIERQUANTITY, multiplierQuantity);
    }

    public Boolean isPositiveOnly() {
        return (Boolean) get(PROPERTY_POSITIVEONLY);
    }

    public void setPositiveOnly(Boolean positiveOnly) {
        set(PROPERTY_POSITIVEONLY, positiveOnly);
    }

    public Boolean isCommissionOnlySpecifiedOrders() {
        return (Boolean) get(PROPERTY_COMMISSIONONLYSPECIFIEDORDERS);
    }

    public void setCommissionOnlySpecifiedOrders(Boolean commissionOnlySpecifiedOrders) {
        set(PROPERTY_COMMISSIONONLYSPECIFIEDORDERS, commissionOnlySpecifiedOrders);
    }

    public Organization getTrxOrganization() {
        return (Organization) get(PROPERTY_TRXORGANIZATION);
    }

    public void setTrxOrganization(Organization trxOrganization) {
        set(PROPERTY_TRXORGANIZATION, trxOrganization);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Category getBusinessPartnerCategory() {
        return (Category) get(PROPERTY_BUSINESSPARTNERCATEGORY);
    }

    public void setBusinessPartnerCategory(Category businessPartnerCategory) {
        set(PROPERTY_BUSINESSPARTNERCATEGORY, businessPartnerCategory);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public SalesRegion getSalesRegion() {
        return (SalesRegion) get(PROPERTY_SALESREGION);
    }

    public void setSalesRegion(SalesRegion salesRegion) {
        set(PROPERTY_SALESREGION, salesRegion);
    }

    public Boolean isExclude() {
        return (Boolean) get(PROPERTY_EXCLUDE);
    }

    public void setExclude(Boolean exclude) {
        set(PROPERTY_EXCLUDE, exclude);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionAmount> getSalesCommissionAmountList() {
        return (List<CommissionAmount>) get(PROPERTY_SALESCOMMISSIONAMOUNTLIST);
    }

    public void setSalesCommissionAmountList(List<CommissionAmount> salesCommissionAmountList) {
        set(PROPERTY_SALESCOMMISSIONAMOUNTLIST, salesCommissionAmountList);
    }

}
