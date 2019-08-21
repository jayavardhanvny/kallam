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
package org.openbravo.model.manufacturing.transaction;

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
import org.openbravo.model.manufacturing.processplan.Operation;
import org.openbravo.model.materialmgmt.transaction.ProductionPlan;
/**
 * Entity class for entity ManufacturingWorkRequirementOperation (stored in table MA_WRPhase).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class WorkRequirementOperation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_WRPhase";
    public static final String ENTITY_NAME = "ManufacturingWorkRequirementOperation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WORKREQUIREMENT = "workRequirement";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_COMPLETEDQUANTITY = "completedQuantity";
    public static final String PROPERTY_COSTCENTERUSETIME = "costCenterUseTime";
    public static final String PROPERTY_PREPARATIONTIME = "preparationTime";
    public static final String PROPERTY_CLOSED = "closed";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_EMPTYCELLSAREZERO = "emptyCellsAreZero";
    public static final String PROPERTY_GLOBALUSE = "globalUse";
    public static final String PROPERTY_MASEQUENCE = "mASequence";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_OUTSOURCED = "outsourced";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_ESTIMATEDTIME = "estimatedTime";
    public static final String PROPERTY_RUNTIME = "runTime";
    public static final String PROPERTY_CREATESTANDARDS = "createStandards";
    public static final String PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST = "manufacturingWorkRequirementProductList";
    public static final String PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST = "materialMgmtProductionPlanList";

    public WorkRequirementOperation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CLOSED, false);
        setDefaultValue(PROPERTY_EMPTYCELLSAREZERO, false);
        setDefaultValue(PROPERTY_GLOBALUSE, false);
        setDefaultValue(PROPERTY_OUTSOURCED, false);
        setDefaultValue(PROPERTY_ESTIMATEDTIME, new BigDecimal(0));
        setDefaultValue(PROPERTY_RUNTIME, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREATESTANDARDS, false);
        setDefaultValue(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST, new ArrayList<Object>());
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

    public WorkRequirement getWorkRequirement() {
        return (WorkRequirement) get(PROPERTY_WORKREQUIREMENT);
    }

    public void setWorkRequirement(WorkRequirement workRequirement) {
        set(PROPERTY_WORKREQUIREMENT, workRequirement);
    }

    public Activity getActivity() {
        return (Activity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(Activity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public BigDecimal getCompletedQuantity() {
        return (BigDecimal) get(PROPERTY_COMPLETEDQUANTITY);
    }

    public void setCompletedQuantity(BigDecimal completedQuantity) {
        set(PROPERTY_COMPLETEDQUANTITY, completedQuantity);
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

    public Boolean isClosed() {
        return (Boolean) get(PROPERTY_CLOSED);
    }

    public void setClosed(Boolean closed) {
        set(PROPERTY_CLOSED, closed);
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

    public Operation getMASequence() {
        return (Operation) get(PROPERTY_MASEQUENCE);
    }

    public void setMASequence(Operation mASequence) {
        set(PROPERTY_MASEQUENCE, mASequence);
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

    public Boolean isOutsourced() {
        return (Boolean) get(PROPERTY_OUTSOURCED);
    }

    public void setOutsourced(Boolean outsourced) {
        set(PROPERTY_OUTSOURCED, outsourced);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public BigDecimal getEstimatedTime() {
        return (BigDecimal) get(PROPERTY_ESTIMATEDTIME);
    }

    public void setEstimatedTime(BigDecimal estimatedTime) {
        set(PROPERTY_ESTIMATEDTIME, estimatedTime);
    }

    public BigDecimal getRunTime() {
        return (BigDecimal) get(PROPERTY_RUNTIME);
    }

    public void setRunTime(BigDecimal runTime) {
        set(PROPERTY_RUNTIME, runTime);
    }

    public Boolean isCreateStandards() {
        return (Boolean) get(PROPERTY_CREATESTANDARDS);
    }

    public void setCreateStandards(Boolean createStandards) {
        set(PROPERTY_CREATESTANDARDS, createStandards);
    }

    @SuppressWarnings("unchecked")
    public List<WorkRequirementProduct> getManufacturingWorkRequirementProductList() {
        return (List<WorkRequirementProduct>) get(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST);
    }

    public void setManufacturingWorkRequirementProductList(List<WorkRequirementProduct> manufacturingWorkRequirementProductList) {
        set(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST, manufacturingWorkRequirementProductList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionPlan> getMaterialMgmtProductionPlanList() {
        return (List<ProductionPlan>) get(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST);
    }

    public void setMaterialMgmtProductionPlanList(List<ProductionPlan> materialMgmtProductionPlanList) {
        set(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST, materialMgmtProductionPlanList);
    }

}
