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
package com.redcarpet.production.data;

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
 * Entity class for entity RCPR_Task (stored in table RCPR_Task).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPR_Task extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Task";
    public static final String ENTITY_NAME = "RCPR_Task";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PREVENTIVEMAINTENANCEORDER = "preventiveMaintenanceOrder";
    public static final String PROPERTY_MACHINE = "machine";
    public static final String PROPERTY_DEPARTMENT = "department";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_MAINTENANACETASK = "maintenanaceTask";
    public static final String PROPERTY_ISEXTENSION = "isExtension";
    public static final String PROPERTY_NOOFDAYS = "noOfDays";
    public static final String PROPERTY_MAINTENANCETYPE = "maintenanceType";
    public static final String PROPERTY_RCPRTASKLINELIST = "rCPRTasklineList";

    public RCPR_Task() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISEXTENSION, false);
        setDefaultValue(PROPERTY_NOOFDAYS, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCPRTASKLINELIST, new ArrayList<Object>());
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

    public RCPR_PreventiveMaintenanceOrder getPreventiveMaintenanceOrder() {
        return (RCPR_PreventiveMaintenanceOrder) get(PROPERTY_PREVENTIVEMAINTENANCEORDER);
    }

    public void setPreventiveMaintenanceOrder(RCPR_PreventiveMaintenanceOrder preventiveMaintenanceOrder) {
        set(PROPERTY_PREVENTIVEMAINTENANCEORDER, preventiveMaintenanceOrder);
    }

    public RCPRMachine getMachine() {
        return (RCPRMachine) get(PROPERTY_MACHINE);
    }

    public void setMachine(RCPRMachine machine) {
        set(PROPERTY_MACHINE, machine);
    }

    public String getDepartment() {
        return (String) get(PROPERTY_DEPARTMENT);
    }

    public void setDepartment(String department) {
        set(PROPERTY_DEPARTMENT, department);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public RCPR_MaintenanceTask getMaintenanaceTask() {
        return (RCPR_MaintenanceTask) get(PROPERTY_MAINTENANACETASK);
    }

    public void setMaintenanaceTask(RCPR_MaintenanceTask maintenanaceTask) {
        set(PROPERTY_MAINTENANACETASK, maintenanaceTask);
    }

    public Boolean isExtension() {
        return (Boolean) get(PROPERTY_ISEXTENSION);
    }

    public void setExtension(Boolean isExtension) {
        set(PROPERTY_ISEXTENSION, isExtension);
    }

    public BigDecimal getNoOfDays() {
        return (BigDecimal) get(PROPERTY_NOOFDAYS);
    }

    public void setNoOfDays(BigDecimal noOfDays) {
        set(PROPERTY_NOOFDAYS, noOfDays);
    }

    public String getMaintenanceType() {
        return (String) get(PROPERTY_MAINTENANCETYPE);
    }

    public void setMaintenanceType(String maintenanceType) {
        set(PROPERTY_MAINTENANCETYPE, maintenanceType);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Taskline> getRCPRTasklineList() {
        return (List<RCPR_Taskline>) get(PROPERTY_RCPRTASKLINELIST);
    }

    public void setRCPRTasklineList(List<RCPR_Taskline> rCPRTasklineList) {
        set(PROPERTY_RCPRTASKLINELIST, rCPRTasklineList);
    }

}
