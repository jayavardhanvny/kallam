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
 * Entity class for entity RCHR_ExperienceDetail (stored in table RCHR_ExperienceDetail).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_ExperienceDetail extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_ExperienceDetail";
    public static final String ENTITY_NAME = "RCHR_ExperienceDetail";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_PREVIOUSEMPLOYER = "previousEmployer";
    public static final String PROPERTY_PREVIOUSEMPLOYERDESIGNATION = "previousEmployerDesignation";
    public static final String PROPERTY_DATEOFJOINING = "dateOfJoining";
    public static final String PROPERTY_SALARY = "salary";
    public static final String PROPERTY_RELIEVINGDATE = "relievingDate";
    public static final String PROPERTY_REMARKS = "remarks";
    public static final String PROPERTY_FRESHER = "fresher";

    public RCHR_ExperienceDetail() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_FRESHER, true);
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

    public String getPreviousEmployer() {
        return (String) get(PROPERTY_PREVIOUSEMPLOYER);
    }

    public void setPreviousEmployer(String previousEmployer) {
        set(PROPERTY_PREVIOUSEMPLOYER, previousEmployer);
    }

    public String getPreviousEmployerDesignation() {
        return (String) get(PROPERTY_PREVIOUSEMPLOYERDESIGNATION);
    }

    public void setPreviousEmployerDesignation(String previousEmployerDesignation) {
        set(PROPERTY_PREVIOUSEMPLOYERDESIGNATION, previousEmployerDesignation);
    }

    public Date getDateOfJoining() {
        return (Date) get(PROPERTY_DATEOFJOINING);
    }

    public void setDateOfJoining(Date dateOfJoining) {
        set(PROPERTY_DATEOFJOINING, dateOfJoining);
    }

    public Long getSalary() {
        return (Long) get(PROPERTY_SALARY);
    }

    public void setSalary(Long salary) {
        set(PROPERTY_SALARY, salary);
    }

    public Date getRelievingDate() {
        return (Date) get(PROPERTY_RELIEVINGDATE);
    }

    public void setRelievingDate(Date relievingDate) {
        set(PROPERTY_RELIEVINGDATE, relievingDate);
    }

    public String getRemarks() {
        return (String) get(PROPERTY_REMARKS);
    }

    public void setRemarks(String remarks) {
        set(PROPERTY_REMARKS, remarks);
    }

    public Boolean isFresher() {
        return (Boolean) get(PROPERTY_FRESHER);
    }

    public void setFresher(Boolean fresher) {
        set(PROPERTY_FRESHER, fresher);
    }

}
