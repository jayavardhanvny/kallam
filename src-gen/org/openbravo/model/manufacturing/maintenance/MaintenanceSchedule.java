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
package org.openbravo.model.manufacturing.maintenance;

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
import org.openbravo.model.manufacturing.floorshop.Machine;
import org.openbravo.model.manufacturing.floorshop.MachineType;
import org.openbravo.model.materialmgmt.transaction.InternalConsumption;
/**
 * Entity class for entity ManufacturingMaintenanceSchedule (stored in table MA_Maint_Scheduled).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class MaintenanceSchedule extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_Maint_Scheduled";
    public static final String ENTITY_NAME = "ManufacturingMaintenanceSchedule";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MAINTENANCE = "maintenance";
    public static final String PROPERTY_MAINTENANCEORDER = "maintenanceOrder";
    public static final String PROPERTY_MACHINE = "machine";
    public static final String PROPERTY_MACHINECATEGORY = "machineCategory";
    public static final String PROPERTY_MAINTENANCETYPE = "maintenanceType";
    public static final String PROPERTY_PLANNEDDATE = "plannedDate";
    public static final String PROPERTY_CONFIRMATION = "confirmation";
    public static final String PROPERTY_SHIFT = "shift";
    public static final String PROPERTY_RESULT = "result";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_TIMEUSED = "timeUsed";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_MAINTENANCETASK = "maintenanceTask";
    public static final String PROPERTY_INTERNALCONSUMPTION = "internalConsumption";

    public MaintenanceSchedule() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CONFIRMATION, false);
        setDefaultValue(PROPERTY_RESULT, false);
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

    public Maintenance getMaintenance() {
        return (Maintenance) get(PROPERTY_MAINTENANCE);
    }

    public void setMaintenance(Maintenance maintenance) {
        set(PROPERTY_MAINTENANCE, maintenance);
    }

    public MainteanceOrder getMaintenanceOrder() {
        return (MainteanceOrder) get(PROPERTY_MAINTENANCEORDER);
    }

    public void setMaintenanceOrder(MainteanceOrder maintenanceOrder) {
        set(PROPERTY_MAINTENANCEORDER, maintenanceOrder);
    }

    public Machine getMachine() {
        return (Machine) get(PROPERTY_MACHINE);
    }

    public void setMachine(Machine machine) {
        set(PROPERTY_MACHINE, machine);
    }

    public MachineType getMachineCategory() {
        return (MachineType) get(PROPERTY_MACHINECATEGORY);
    }

    public void setMachineCategory(MachineType machineCategory) {
        set(PROPERTY_MACHINECATEGORY, machineCategory);
    }

    public String getMaintenanceType() {
        return (String) get(PROPERTY_MAINTENANCETYPE);
    }

    public void setMaintenanceType(String maintenanceType) {
        set(PROPERTY_MAINTENANCETYPE, maintenanceType);
    }

    public Date getPlannedDate() {
        return (Date) get(PROPERTY_PLANNEDDATE);
    }

    public void setPlannedDate(Date plannedDate) {
        set(PROPERTY_PLANNEDDATE, plannedDate);
    }

    public Boolean isConfirmation() {
        return (Boolean) get(PROPERTY_CONFIRMATION);
    }

    public void setConfirmation(Boolean confirmation) {
        set(PROPERTY_CONFIRMATION, confirmation);
    }

    public String getShift() {
        return (String) get(PROPERTY_SHIFT);
    }

    public void setShift(String shift) {
        set(PROPERTY_SHIFT, shift);
    }

    public Boolean isResult() {
        return (Boolean) get(PROPERTY_RESULT);
    }

    public void setResult(Boolean result) {
        set(PROPERTY_RESULT, result);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public BigDecimal getTimeUsed() {
        return (BigDecimal) get(PROPERTY_TIMEUSED);
    }

    public void setTimeUsed(BigDecimal timeUsed) {
        set(PROPERTY_TIMEUSED, timeUsed);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Task getMaintenanceTask() {
        return (Task) get(PROPERTY_MAINTENANCETASK);
    }

    public void setMaintenanceTask(Task maintenanceTask) {
        set(PROPERTY_MAINTENANCETASK, maintenanceTask);
    }

    public InternalConsumption getInternalConsumption() {
        return (InternalConsumption) get(PROPERTY_INTERNALCONSUMPTION);
    }

    public void setInternalConsumption(InternalConsumption internalConsumption) {
        set(PROPERTY_INTERNALCONSUMPTION, internalConsumption);
    }

}
