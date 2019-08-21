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
package org.openbravo.model.manufacturing.processplan;

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
import org.openbravo.model.manufacturing.transaction.Activity;
import org.openbravo.model.manufacturing.transaction.WorkRequirementOperation;
/**
 * Entity class for entity ManufacturingOperation (stored in table MA_Sequence).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Operation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_Sequence";
    public static final String ENTITY_NAME = "ManufacturingOperation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROCESSPLANVERSION = "processPlanVersion";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_COSTCENTERUSETIME = "costCenterUseTime";
    public static final String PROPERTY_PREPARATIONTIME = "preparationTime";
    public static final String PROPERTY_MULTIPLIER = "multiplier";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_EMPTYCELLSAREZERO = "emptyCellsAreZero";
    public static final String PROPERTY_GLOBALUSE = "globalUse";
    public static final String PROPERTY_CALCULATED = "calculated";
    public static final String PROPERTY_COSTCENTERCOST = "costCenterCost";
    public static final String PROPERTY_OUTSOURCED = "outsourced";
    public static final String PROPERTY_OUTSOURCINGCOST = "outsourcingCost";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_ESTIMATEDTIME = "estimatedTime";
    public static final String PROPERTY_CREATESTANDARDS = "createStandards";
    public static final String PROPERTY_MANUFACTURINGOPERATIONEMPLOYEELIST = "manufacturingOperationEmployeeList";
    public static final String PROPERTY_MANUFACTURINGOPERATIONINDIRECTCOSTLIST = "manufacturingOperationIndirectCostList";
    public static final String PROPERTY_MANUFACTURINGOPERATIONMACHINELIST = "manufacturingOperationMachineList";
    public static final String PROPERTY_MANUFACTURINGOPERATIONPRODUCTLIST = "manufacturingOperationProductList";
    public static final String PROPERTY_MANUFACTURINGWORKREQUIREMENTOPERATIONLIST = "manufacturingWorkRequirementOperationList";

    public Operation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MULTIPLIER, new BigDecimal(1));
        setDefaultValue(PROPERTY_EMPTYCELLSAREZERO, false);
        setDefaultValue(PROPERTY_GLOBALUSE, false);
        setDefaultValue(PROPERTY_CALCULATED, false);
        setDefaultValue(PROPERTY_OUTSOURCED, false);
        setDefaultValue(PROPERTY_DEFAULT, true);
        setDefaultValue(PROPERTY_ESTIMATEDTIME, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREATESTANDARDS, false);
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONINDIRECTCOSTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONMACHINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKREQUIREMENTOPERATIONLIST, new ArrayList<Object>());
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

    public Version getProcessPlanVersion() {
        return (Version) get(PROPERTY_PROCESSPLANVERSION);
    }

    public void setProcessPlanVersion(Version processPlanVersion) {
        set(PROPERTY_PROCESSPLANVERSION, processPlanVersion);
    }

    public Activity getActivity() {
        return (Activity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(Activity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
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

    public BigDecimal getCostCenterUseTime() {
        return (BigDecimal) get(PROPERTY_COSTCENTERUSETIME);
    }

    public void setCostCenterUseTime(BigDecimal costCenterUseTime) {
        set(PROPERTY_COSTCENTERUSETIME, costCenterUseTime);
    }

    public BigDecimal getPreparationTime() {
        return (BigDecimal) get(PROPERTY_PREPARATIONTIME);
    }

    public void setPreparationTime(BigDecimal preparationTime) {
        set(PROPERTY_PREPARATIONTIME, preparationTime);
    }

    public BigDecimal getMultiplier() {
        return (BigDecimal) get(PROPERTY_MULTIPLIER);
    }

    public void setMultiplier(BigDecimal multiplier) {
        set(PROPERTY_MULTIPLIER, multiplier);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public Boolean isEmptyCellsAreZero() {
        return (Boolean) get(PROPERTY_EMPTYCELLSAREZERO);
    }

    public void setEmptyCellsAreZero(Boolean emptyCellsAreZero) {
        set(PROPERTY_EMPTYCELLSAREZERO, emptyCellsAreZero);
    }

    public Boolean isGlobalUse() {
        return (Boolean) get(PROPERTY_GLOBALUSE);
    }

    public void setGlobalUse(Boolean globalUse) {
        set(PROPERTY_GLOBALUSE, globalUse);
    }

    public Boolean isCalculated() {
        return (Boolean) get(PROPERTY_CALCULATED);
    }

    public void setCalculated(Boolean calculated) {
        set(PROPERTY_CALCULATED, calculated);
    }

    public BigDecimal getCostCenterCost() {
        return (BigDecimal) get(PROPERTY_COSTCENTERCOST);
    }

    public void setCostCenterCost(BigDecimal costCenterCost) {
        set(PROPERTY_COSTCENTERCOST, costCenterCost);
    }

    public Boolean isOutsourced() {
        return (Boolean) get(PROPERTY_OUTSOURCED);
    }

    public void setOutsourced(Boolean outsourced) {
        set(PROPERTY_OUTSOURCED, outsourced);
    }

    public BigDecimal getOutsourcingCost() {
        return (BigDecimal) get(PROPERTY_OUTSOURCINGCOST);
    }

    public void setOutsourcingCost(BigDecimal outsourcingCost) {
        set(PROPERTY_OUTSOURCINGCOST, outsourcingCost);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public BigDecimal getEstimatedTime() {
        return (BigDecimal) get(PROPERTY_ESTIMATEDTIME);
    }

    public void setEstimatedTime(BigDecimal estimatedTime) {
        set(PROPERTY_ESTIMATEDTIME, estimatedTime);
    }

    public Boolean isCreateStandards() {
        return (Boolean) get(PROPERTY_CREATESTANDARDS);
    }

    public void setCreateStandards(Boolean createStandards) {
        set(PROPERTY_CREATESTANDARDS, createStandards);
    }

    @SuppressWarnings("unchecked")
    public List<OperationEmployee> getManufacturingOperationEmployeeList() {
        return (List<OperationEmployee>) get(PROPERTY_MANUFACTURINGOPERATIONEMPLOYEELIST);
    }

    public void setManufacturingOperationEmployeeList(List<OperationEmployee> manufacturingOperationEmployeeList) {
        set(PROPERTY_MANUFACTURINGOPERATIONEMPLOYEELIST, manufacturingOperationEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<OperationIndirectCost> getManufacturingOperationIndirectCostList() {
        return (List<OperationIndirectCost>) get(PROPERTY_MANUFACTURINGOPERATIONINDIRECTCOSTLIST);
    }

    public void setManufacturingOperationIndirectCostList(List<OperationIndirectCost> manufacturingOperationIndirectCostList) {
        set(PROPERTY_MANUFACTURINGOPERATIONINDIRECTCOSTLIST, manufacturingOperationIndirectCostList);
    }

    @SuppressWarnings("unchecked")
    public List<OperationMachine> getManufacturingOperationMachineList() {
        return (List<OperationMachine>) get(PROPERTY_MANUFACTURINGOPERATIONMACHINELIST);
    }

    public void setManufacturingOperationMachineList(List<OperationMachine> manufacturingOperationMachineList) {
        set(PROPERTY_MANUFACTURINGOPERATIONMACHINELIST, manufacturingOperationMachineList);
    }

    @SuppressWarnings("unchecked")
    public List<OperationProduct> getManufacturingOperationProductList() {
        return (List<OperationProduct>) get(PROPERTY_MANUFACTURINGOPERATIONPRODUCTLIST);
    }

    public void setManufacturingOperationProductList(List<OperationProduct> manufacturingOperationProductList) {
        set(PROPERTY_MANUFACTURINGOPERATIONPRODUCTLIST, manufacturingOperationProductList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkRequirementOperation> getManufacturingWorkRequirementOperationList() {
        return (List<WorkRequirementOperation>) get(PROPERTY_MANUFACTURINGWORKREQUIREMENTOPERATIONLIST);
    }

    public void setManufacturingWorkRequirementOperationList(List<WorkRequirementOperation> manufacturingWorkRequirementOperationList) {
        set(PROPERTY_MANUFACTURINGWORKREQUIREMENTOPERATIONLIST, manufacturingWorkRequirementOperationList);
    }

}
