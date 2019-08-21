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
/**
 * Entity class for entity RCHR_Employeecoffs (stored in table RCHR_Employeecoffs).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrEmployeecoffs extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Employeecoffs";
    public static final String ENTITY_NAME = "RCHR_Employeecoffs";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_ONDATE = "ondate";
    public static final String PROPERTY_NUMOFCOFFS = "numofcoffs";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_COFFODTYPE = "coffodtype";
    public static final String PROPERTY_WEEKLYOFF = "weeklyOff";
    public static final String PROPERTY_WEEKOFFLEAVES = "weekoffleaves";
    public static final String PROPERTY_GETDATE = "getdate";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_APPROVE = "approve";
    public static final String PROPERTY_RCHREMPLOYEECOFFSLINESLIST = "rCHREmployeecoffslinesList";

    public RchrEmployeecoffs() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_GETDATE, false);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_APPROVE, false);
        setDefaultValue(PROPERTY_RCHREMPLOYEECOFFSLINESLIST, new ArrayList<Object>());
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

    public Date getOndate() {
        return (Date) get(PROPERTY_ONDATE);
    }

    public void setOndate(Date ondate) {
        set(PROPERTY_ONDATE, ondate);
    }

    public BigDecimal getNumofcoffs() {
        return (BigDecimal) get(PROPERTY_NUMOFCOFFS);
    }

    public void setNumofcoffs(BigDecimal numofcoffs) {
        set(PROPERTY_NUMOFCOFFS, numofcoffs);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public String getCoffodtype() {
        return (String) get(PROPERTY_COFFODTYPE);
    }

    public void setCoffodtype(String coffodtype) {
        set(PROPERTY_COFFODTYPE, coffodtype);
    }

    public String getWeeklyOff() {
        return (String) get(PROPERTY_WEEKLYOFF);
    }

    public void setWeeklyOff(String weeklyOff) {
        set(PROPERTY_WEEKLYOFF, weeklyOff);
    }

    public BigDecimal getWeekoffleaves() {
        return (BigDecimal) get(PROPERTY_WEEKOFFLEAVES);
    }

    public void setWeekoffleaves(BigDecimal weekoffleaves) {
        set(PROPERTY_WEEKOFFLEAVES, weekoffleaves);
    }

    public Boolean isGetdate() {
        return (Boolean) get(PROPERTY_GETDATE);
    }

    public void setGetdate(Boolean getdate) {
        set(PROPERTY_GETDATE, getdate);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isApprove() {
        return (Boolean) get(PROPERTY_APPROVE);
    }

    public void setApprove(Boolean approve) {
        set(PROPERTY_APPROVE, approve);
    }

    @SuppressWarnings("unchecked")
    public List<RCHREmployeecoffslines> getRCHREmployeecoffslinesList() {
        return (List<RCHREmployeecoffslines>) get(PROPERTY_RCHREMPLOYEECOFFSLINESLIST);
    }

    public void setRCHREmployeecoffslinesList(List<RCHREmployeecoffslines> rCHREmployeecoffslinesList) {
        set(PROPERTY_RCHREMPLOYEECOFFSLINESLIST, rCHREmployeecoffslinesList);
    }

}
