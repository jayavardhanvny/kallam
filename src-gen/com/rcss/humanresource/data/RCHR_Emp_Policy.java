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
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCHR_Emp_Policy (stored in table RCHR_Emp_Policy).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Emp_Policy extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Emp_Policy";
    public static final String ENTITY_NAME = "RCHR_Emp_Policy";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_POLICYNAME = "policyName";
    public static final String PROPERTY_POLICYNO = "policyNo";
    public static final String PROPERTY_POLICYISSUEDATE = "policyIssueDate";
    public static final String PROPERTY_POLICYLAPSEDATE = "policyLapseDate";
    public static final String PROPERTY_PERMIUMPERYEAR = "permiumPerYear";
    public static final String PROPERTY_PAYMENTTYPE = "paymentType";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_POLICYISSUERNAME = "policyIssuerName";

    public RCHR_Emp_Policy() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PAYMENTTYPE, "Monthly");
        setDefaultValue(PROPERTY_PROCESS, false);
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

    public RCHR_Policy getPolicyName() {
        return (RCHR_Policy) get(PROPERTY_POLICYNAME);
    }

    public void setPolicyName(RCHR_Policy policyName) {
        set(PROPERTY_POLICYNAME, policyName);
    }

    public String getPolicyNo() {
        return (String) get(PROPERTY_POLICYNO);
    }

    public void setPolicyNo(String policyNo) {
        set(PROPERTY_POLICYNO, policyNo);
    }

    public Date getPolicyIssueDate() {
        return (Date) get(PROPERTY_POLICYISSUEDATE);
    }

    public void setPolicyIssueDate(Date policyIssueDate) {
        set(PROPERTY_POLICYISSUEDATE, policyIssueDate);
    }

    public Date getPolicyLapseDate() {
        return (Date) get(PROPERTY_POLICYLAPSEDATE);
    }

    public void setPolicyLapseDate(Date policyLapseDate) {
        set(PROPERTY_POLICYLAPSEDATE, policyLapseDate);
    }

    public BigDecimal getPermiumPerYear() {
        return (BigDecimal) get(PROPERTY_PERMIUMPERYEAR);
    }

    public void setPermiumPerYear(BigDecimal permiumPerYear) {
        set(PROPERTY_PERMIUMPERYEAR, permiumPerYear);
    }

    public String getPaymentType() {
        return (String) get(PROPERTY_PAYMENTTYPE);
    }

    public void setPaymentType(String paymentType) {
        set(PROPERTY_PAYMENTTYPE, paymentType);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getPolicyIssuerName() {
        return (String) get(PROPERTY_POLICYISSUERNAME);
    }

    public void setPolicyIssuerName(String policyIssuerName) {
        set(PROPERTY_POLICYISSUERNAME, policyIssuerName);
    }

}
