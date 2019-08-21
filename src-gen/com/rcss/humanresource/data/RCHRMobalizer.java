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
package com.rcss.humanresource.data;

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
/**
 * Entity class for entity RCHR_Mobalizer (stored in table RCHR_Mobalizer).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRMobalizer extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Mobalizer";
    public static final String ENTITY_NAME = "RCHR_Mobalizer";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_RCHRMOBALIZERLINELIST = "rCHRMobalizerlineList";
    public static final String PROPERTY_RCHRMOBALIZERSERVICEINCLIST = "rCHRMobalizerserviceincList";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";

    public RCHRMobalizer() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCHRMOBALIZERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMOBALIZERSERVICEINCLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
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

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRMobalizerline> getRCHRMobalizerlineList() {
        return (List<RCHRMobalizerline>) get(PROPERTY_RCHRMOBALIZERLINELIST);
    }

    public void setRCHRMobalizerlineList(List<RCHRMobalizerline> rCHRMobalizerlineList) {
        set(PROPERTY_RCHRMOBALIZERLINELIST, rCHRMobalizerlineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRMobalizerserviceinc> getRCHRMobalizerserviceincList() {
        return (List<RCHRMobalizerserviceinc>) get(PROPERTY_RCHRMOBALIZERSERVICEINCLIST);
    }

    public void setRCHRMobalizerserviceincList(List<RCHRMobalizerserviceinc> rCHRMobalizerserviceincList) {
        set(PROPERTY_RCHRMOBALIZERSERVICEINCLIST, rCHRMobalizerserviceincList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployee> getRchrEmployeeList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEELIST);
    }

    public void setRchrEmployeeList(List<RchrEmployee> rchrEmployeeList) {
        set(PROPERTY_RCHREMPLOYEELIST, rchrEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

}
