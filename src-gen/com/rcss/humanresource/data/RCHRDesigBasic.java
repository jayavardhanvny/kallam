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
 * Entity class for entity RCHR_DesigBasic (stored in table RCHR_DesigBasic).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRDesigBasic extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_DesigBasic";
    public static final String ENTITY_NAME = "RCHR_DesigBasic";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_SUBDEPARTMENT = "subDepartment";
    public static final String PROPERTY_FROMDAYS = "fromdays";
    public static final String PROPERTY_TODAYS = "todays";
    public static final String PROPERTY_BASICAMOUNT = "basicamount";
    public static final String PROPERTY_SERVICEINCENTIVE = "serviceincentive";
    public static final String PROPERTY_RCHRSALARYSTRUCTURE = "rchrSalarystructure";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";

    public RCHRDesigBasic() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_FROMDAYS, (long) 0);
        setDefaultValue(PROPERTY_TODAYS, (long) 0);
        setDefaultValue(PROPERTY_BASICAMOUNT, (long) 0);
        setDefaultValue(PROPERTY_SERVICEINCENTIVE, (long) 0);
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

    public RchrDesignation getDesignation() {
        return (RchrDesignation) get(PROPERTY_DESIGNATION);
    }

    public void setDesignation(RchrDesignation designation) {
        set(PROPERTY_DESIGNATION, designation);
    }

    public RchrEmpDept getEmployeeDepartment() {
        return (RchrEmpDept) get(PROPERTY_EMPLOYEEDEPARTMENT);
    }

    public void setEmployeeDepartment(RchrEmpDept employeeDepartment) {
        set(PROPERTY_EMPLOYEEDEPARTMENT, employeeDepartment);
    }

    public RCHR_SubDepartment getSubDepartment() {
        return (RCHR_SubDepartment) get(PROPERTY_SUBDEPARTMENT);
    }

    public void setSubDepartment(RCHR_SubDepartment subDepartment) {
        set(PROPERTY_SUBDEPARTMENT, subDepartment);
    }

    public Long getFromdays() {
        return (Long) get(PROPERTY_FROMDAYS);
    }

    public void setFromdays(Long fromdays) {
        set(PROPERTY_FROMDAYS, fromdays);
    }

    public Long getTodays() {
        return (Long) get(PROPERTY_TODAYS);
    }

    public void setTodays(Long todays) {
        set(PROPERTY_TODAYS, todays);
    }

    public Long getBasicamount() {
        return (Long) get(PROPERTY_BASICAMOUNT);
    }

    public void setBasicamount(Long basicamount) {
        set(PROPERTY_BASICAMOUNT, basicamount);
    }

    public Long getServiceincentive() {
        return (Long) get(PROPERTY_SERVICEINCENTIVE);
    }

    public void setServiceincentive(Long serviceincentive) {
        set(PROPERTY_SERVICEINCENTIVE, serviceincentive);
    }

    public RchrSalarystructure getRchrSalarystructure() {
        return (RchrSalarystructure) get(PROPERTY_RCHRSALARYSTRUCTURE);
    }

    public void setRchrSalarystructure(RchrSalarystructure rchrSalarystructure) {
        set(PROPERTY_RCHRSALARYSTRUCTURE, rchrSalarystructure);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

}
