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
package org.openbravo.model.mrp;

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
 * Entity class for entity MRPPurchasingRun (stored in table MRP_Run_Purchase).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PurchasingRun extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MRP_Run_Purchase";
    public static final String ENTITY_NAME = "MRPPurchasingRun";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_DOCUMENTDATE = "documentDate";
    public static final String PROPERTY_PLANNER = "planner";
    public static final String PROPERTY_VENDOR = "vendor";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORY = "businessPartnerCategory";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_TIMEHORIZON = "timeHorizon";
    public static final String PROPERTY_SAFETYLEADTIME = "safetyLeadTime";
    public static final String PROPERTY_SIMULATE = "simulate";
    public static final String PROPERTY_CREATEPURCHASEORDER = "createPurchaseOrder";
    public static final String PROPERTY_CREATERESERVATIONS = "createReservations";
    public static final String PROPERTY_MRPPURCHASINGRUNLINELIST = "mRPPurchasingRunLineList";

    public PurchasingRun() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_SIMULATE, false);
        setDefaultValue(PROPERTY_CREATEPURCHASEORDER, false);
        setDefaultValue(PROPERTY_CREATERESERVATIONS, false);
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLINELIST, new ArrayList<Object>());
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Date getDocumentDate() {
        return (Date) get(PROPERTY_DOCUMENTDATE);
    }

    public void setDocumentDate(Date documentDate) {
        set(PROPERTY_DOCUMENTDATE, documentDate);
    }

    public Planner getPlanner() {
        return (Planner) get(PROPERTY_PLANNER);
    }

    public void setPlanner(Planner planner) {
        set(PROPERTY_PLANNER, planner);
    }

    public BusinessPartner getVendor() {
        return (BusinessPartner) get(PROPERTY_VENDOR);
    }

    public void setVendor(BusinessPartner vendor) {
        set(PROPERTY_VENDOR, vendor);
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

    public Long getTimeHorizon() {
        return (Long) get(PROPERTY_TIMEHORIZON);
    }

    public void setTimeHorizon(Long timeHorizon) {
        set(PROPERTY_TIMEHORIZON, timeHorizon);
    }

    public Long getSafetyLeadTime() {
        return (Long) get(PROPERTY_SAFETYLEADTIME);
    }

    public void setSafetyLeadTime(Long safetyLeadTime) {
        set(PROPERTY_SAFETYLEADTIME, safetyLeadTime);
    }

    public Boolean isSimulate() {
        return (Boolean) get(PROPERTY_SIMULATE);
    }

    public void setSimulate(Boolean simulate) {
        set(PROPERTY_SIMULATE, simulate);
    }

    public Boolean isCreatePurchaseOrder() {
        return (Boolean) get(PROPERTY_CREATEPURCHASEORDER);
    }

    public void setCreatePurchaseOrder(Boolean createPurchaseOrder) {
        set(PROPERTY_CREATEPURCHASEORDER, createPurchaseOrder);
    }

    public Boolean isCreateReservations() {
        return (Boolean) get(PROPERTY_CREATERESERVATIONS);
    }

    public void setCreateReservations(Boolean createReservations) {
        set(PROPERTY_CREATERESERVATIONS, createReservations);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRunLine> getMRPPurchasingRunLineList() {
        return (List<PurchasingRunLine>) get(PROPERTY_MRPPURCHASINGRUNLINELIST);
    }

    public void setMRPPurchasingRunLineList(List<PurchasingRunLine> mRPPurchasingRunLineList) {
        set(PROPERTY_MRPPURCHASINGRUNLINELIST, mRPPurchasingRunLineList);
    }

}
