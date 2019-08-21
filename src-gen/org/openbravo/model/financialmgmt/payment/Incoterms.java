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
package org.openbravo.model.financialmgmt.payment;

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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.project.ProjectVendor;
/**
 * Entity class for entity FinancialMgmtIncoterms (stored in table C_Incoterms).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Incoterms extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Incoterms";
    public static final String ENTITY_NAME = "FinancialMgmtIncoterms";
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
    public static final String PROPERTY_DESCRIPTIONREQUIRED = "descriptionRequired";
    public static final String PROPERTY_FORDELIVERY = "forDelivery";
    public static final String PROPERTY_BUSINESSPARTNERINCOTERMSPOLIST = "businessPartnerIncotermsPOList";
    public static final String PROPERTY_BUSINESSPARTNERINCOTERMSSOLIST = "businessPartnerIncotermsSOList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_PROJECTVENDORLIST = "projectVendorList";

    public Incoterms() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DESCRIPTIONREQUIRED, false);
        setDefaultValue(PROPERTY_FORDELIVERY, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNERINCOTERMSPOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERINCOTERMSSOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTVENDORLIST, new ArrayList<Object>());
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

    public Boolean isDescriptionRequired() {
        return (Boolean) get(PROPERTY_DESCRIPTIONREQUIRED);
    }

    public void setDescriptionRequired(Boolean descriptionRequired) {
        set(PROPERTY_DESCRIPTIONREQUIRED, descriptionRequired);
    }

    public Boolean isForDelivery() {
        return (Boolean) get(PROPERTY_FORDELIVERY);
    }

    public void setForDelivery(Boolean forDelivery) {
        set(PROPERTY_FORDELIVERY, forDelivery);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerIncotermsPOList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERINCOTERMSPOLIST);
    }

    public void setBusinessPartnerIncotermsPOList(List<BusinessPartner> businessPartnerIncotermsPOList) {
        set(PROPERTY_BUSINESSPARTNERINCOTERMSPOLIST, businessPartnerIncotermsPOList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerIncotermsSOList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERINCOTERMSSOLIST);
    }

    public void setBusinessPartnerIncotermsSOList(List<BusinessPartner> businessPartnerIncotermsSOList) {
        set(PROPERTY_BUSINESSPARTNERINCOTERMSSOLIST, businessPartnerIncotermsSOList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderList() {
        return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectVendor> getProjectVendorList() {
        return (List<ProjectVendor>) get(PROPERTY_PROJECTVENDORLIST);
    }

    public void setProjectVendorList(List<ProjectVendor> projectVendorList) {
        set(PROPERTY_PROJECTVENDORLIST, projectVendorList);
    }

}
