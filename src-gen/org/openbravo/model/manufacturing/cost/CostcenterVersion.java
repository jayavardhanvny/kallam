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
package org.openbravo.model.manufacturing.cost;

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
import org.openbravo.model.materialmgmt.transaction.ProductionPlan;
/**
 * Entity class for entity ManufacturingCostcenterVersion (stored in table MA_Costcenter_Version).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CostcenterVersion extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_Costcenter_Version";
    public static final String ENTITY_NAME = "ManufacturingCostcenterVersion";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_COST = "cost";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_COSTUOM = "costUOM";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_MANUFACTURINGCOSTCENTERMACHINELIST = "manufacturingCostCenterMachineList";
    public static final String PROPERTY_MANUFACTURINGCOSTCENTEREMPLOYEELIST = "manufacturingCostcenterEmployeeList";
    public static final String PROPERTY_MANUFACTURINGCOSTCENTERICLIST = "manufacturingCostcenterICList";
    public static final String PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST = "materialMgmtProductionPlanList";

    public CostcenterVersion() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_MANUFACTURINGCOSTCENTERMACHINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGCOSTCENTEREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGCOSTCENTERICLIST, new ArrayList<Object>());
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public BigDecimal getCost() {
        return (BigDecimal) get(PROPERTY_COST);
    }

    public void setCost(BigDecimal cost) {
        set(PROPERTY_COST, cost);
    }

    public CostCenter getCostCenter() {
        return (CostCenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(CostCenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public String getCostUOM() {
        return (String) get(PROPERTY_COSTUOM);
    }

    public void setCostUOM(String costUOM) {
        set(PROPERTY_COSTUOM, costUOM);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    @SuppressWarnings("unchecked")
    public List<CostCenterMachine> getManufacturingCostCenterMachineList() {
        return (List<CostCenterMachine>) get(PROPERTY_MANUFACTURINGCOSTCENTERMACHINELIST);
    }

    public void setManufacturingCostCenterMachineList(List<CostCenterMachine> manufacturingCostCenterMachineList) {
        set(PROPERTY_MANUFACTURINGCOSTCENTERMACHINELIST, manufacturingCostCenterMachineList);
    }

    @SuppressWarnings("unchecked")
    public List<CostcenterEmployee> getManufacturingCostcenterEmployeeList() {
        return (List<CostcenterEmployee>) get(PROPERTY_MANUFACTURINGCOSTCENTEREMPLOYEELIST);
    }

    public void setManufacturingCostcenterEmployeeList(List<CostcenterEmployee> manufacturingCostcenterEmployeeList) {
        set(PROPERTY_MANUFACTURINGCOSTCENTEREMPLOYEELIST, manufacturingCostcenterEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<CostcenterIC> getManufacturingCostcenterICList() {
        return (List<CostcenterIC>) get(PROPERTY_MANUFACTURINGCOSTCENTERICLIST);
    }

    public void setManufacturingCostcenterICList(List<CostcenterIC> manufacturingCostcenterICList) {
        set(PROPERTY_MANUFACTURINGCOSTCENTERICLIST, manufacturingCostcenterICList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionPlan> getMaterialMgmtProductionPlanList() {
        return (List<ProductionPlan>) get(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST);
    }

    public void setMaterialMgmtProductionPlanList(List<ProductionPlan> materialMgmtProductionPlanList) {
        set(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST, materialMgmtProductionPlanList);
    }

}
