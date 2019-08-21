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
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.mrp.Planner;
import org.openbravo.model.mrp.PlanningMethod;
/**
 * Entity class for entity ProductOrg (stored in table M_Product_Org).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProductOrg extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Product_Org";
    public static final String ENTITY_NAME = "ProductOrg";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_CAPACITY = "capacity";
    public static final String PROPERTY_MINIMUMLEADTIME = "minimumLeadTime";
    public static final String PROPERTY_PLANNER = "planner";
    public static final String PROPERTY_PLANNINGMETHOD = "planningMethod";
    public static final String PROPERTY_MAXQUANTITY = "maxQuantity";
    public static final String PROPERTY_MINQUANTITY = "minQuantity";
    public static final String PROPERTY_STANDARDQUANTITY = "standardQuantity";
    public static final String PROPERTY_QUANTITYTYPE = "quantityType";
    public static final String PROPERTY_SAFETYSTOCK = "safetyStock";
    public static final String PROPERTY_ABC = "abc";
    public static final String PROPERTY_PREFERREDVENDOR = "preferredVendor";
    public static final String PROPERTY_MAXIMUMQTY = "maximumqty";

    public ProductOrg() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public BigDecimal getCapacity() {
        return (BigDecimal) get(PROPERTY_CAPACITY);
    }

    public void setCapacity(BigDecimal capacity) {
        set(PROPERTY_CAPACITY, capacity);
    }

    public Long getMinimumLeadTime() {
        return (Long) get(PROPERTY_MINIMUMLEADTIME);
    }

    public void setMinimumLeadTime(Long minimumLeadTime) {
        set(PROPERTY_MINIMUMLEADTIME, minimumLeadTime);
    }

    public Planner getPlanner() {
        return (Planner) get(PROPERTY_PLANNER);
    }

    public void setPlanner(Planner planner) {
        set(PROPERTY_PLANNER, planner);
    }

    public PlanningMethod getPlanningMethod() {
        return (PlanningMethod) get(PROPERTY_PLANNINGMETHOD);
    }

    public void setPlanningMethod(PlanningMethod planningMethod) {
        set(PROPERTY_PLANNINGMETHOD, planningMethod);
    }

    public BigDecimal getMaxQuantity() {
        return (BigDecimal) get(PROPERTY_MAXQUANTITY);
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        set(PROPERTY_MAXQUANTITY, maxQuantity);
    }

    public BigDecimal getMinQuantity() {
        return (BigDecimal) get(PROPERTY_MINQUANTITY);
    }

    public void setMinQuantity(BigDecimal minQuantity) {
        set(PROPERTY_MINQUANTITY, minQuantity);
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

    public BigDecimal getSafetyStock() {
        return (BigDecimal) get(PROPERTY_SAFETYSTOCK);
    }

    public void setSafetyStock(BigDecimal safetyStock) {
        set(PROPERTY_SAFETYSTOCK, safetyStock);
    }

    public String getAbc() {
        return (String) get(PROPERTY_ABC);
    }

    public void setAbc(String abc) {
        set(PROPERTY_ABC, abc);
    }

    public ApprovedVendor getPreferredVendor() {
        return (ApprovedVendor) get(PROPERTY_PREFERREDVENDOR);
    }

    public void setPreferredVendor(ApprovedVendor preferredVendor) {
        set(PROPERTY_PREFERREDVENDOR, preferredVendor);
    }

    public BigDecimal getMaximumqty() {
        return (BigDecimal) get(PROPERTY_MAXIMUMQTY);
    }

    public void setMaximumqty(BigDecimal maximumqty) {
        set(PROPERTY_MAXIMUMQTY, maximumqty);
    }

}
