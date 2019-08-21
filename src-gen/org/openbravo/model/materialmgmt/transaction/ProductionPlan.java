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
package org.openbravo.model.materialmgmt.transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.manufacturing.cost.CostcenterVersion;
import org.openbravo.model.manufacturing.transaction.ProductionRunEmployee;
import org.openbravo.model.manufacturing.transaction.ProductionRunIndirectCosts;
import org.openbravo.model.manufacturing.transaction.ProductionRunInvoiceLine;
import org.openbravo.model.manufacturing.transaction.ProductionRunMachine;
import org.openbravo.model.manufacturing.transaction.ProductionRunToolset;
import org.openbravo.model.manufacturing.transaction.WorkEffortIncidence;
import org.openbravo.model.manufacturing.transaction.WorkRequirementOperation;
/**
 * Entity class for entity MaterialMgmtProductionPlan (stored in table M_ProductionPlan).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProductionPlan extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_ProductionPlan";
    public static final String ENTITY_NAME = "MaterialMgmtProductionPlan";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PRODUCTION = "production";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_PRODUCTIONQUANTITY = "productionQuantity";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CONVERSIONRATE = "conversionRate";
    public static final String PROPERTY_COSTCENTERUSE = "costCenterUse";
    public static final String PROPERTY_WRPHASE = "wRPhase";
    public static final String PROPERTY_REQUIREDQUANTITY = "requiredQuantity";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_REJECTEDQUANTITY = "rejectedQuantity";
    public static final String PROPERTY_PROCESSQUANTITY = "processQuantity";
    public static final String PROPERTY_PROCESSUNIT = "processUnit";
    public static final String PROPERTY_CREATESTANDARDS = "createStandards";
    public static final String PROPERTY_ESTIMATEDCOST = "estimatedCost";
    public static final String PROPERTY_COSTCENTERVERSION = "costCenterVersion";
    public static final String PROPERTY_OUTSOURCED = "outsourced";
    public static final String PROPERTY_STARTINGTIME = "startingTime";
    public static final String PROPERTY_ENDINGTIME = "endingTime";
    public static final String PROPERTY_ESTIMATEDTIME = "estimatedTime";
    public static final String PROPERTY_RUNTIME = "runTime";
    public static final String PROPERTY_CLOSEPHASE = "closephase";
    public static final String PROPERTY_VALIDATEWORKEFFORT = "validateWorkEffort";
    public static final String PROPERTY_PRODUCTIONPLANDATE = "productionplandate";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONLINELIST = "manufacturingProductionLineList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST = "manufacturingProductionRunEmployeeList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNINDIRECTCOSTSLIST = "manufacturingProductionRunIndirectCostsList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNINVOICELINELIST = "manufacturingProductionRunInvoiceLineList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNMACHINELIST = "manufacturingProductionRunMachineList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNTOOLSETLIST = "manufacturingProductionRunToolsetList";
    public static final String PROPERTY_MANUFACTURINGWORKEFFORTINCIDENCELIST = "manufacturingWorkEffortIncidenceList";

    public ProductionPlan() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_CREATESTANDARDS, false);
        setDefaultValue(PROPERTY_OUTSOURCED, false);
        setDefaultValue(PROPERTY_ESTIMATEDTIME, new BigDecimal(0));
        setDefaultValue(PROPERTY_RUNTIME, (long) 0);
        setDefaultValue(PROPERTY_CLOSEPHASE, false);
        setDefaultValue(PROPERTY_VALIDATEWORKEFFORT, false);
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNINDIRECTCOSTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNINVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNMACHINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNTOOLSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKEFFORTINCIDENCELIST, new ArrayList<Object>());
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

    public ProductionTransaction getProduction() {
        return (ProductionTransaction) get(PROPERTY_PRODUCTION);
    }

    public void setProduction(ProductionTransaction production) {
        set(PROPERTY_PRODUCTION, production);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getProductionQuantity() {
        return (BigDecimal) get(PROPERTY_PRODUCTIONQUANTITY);
    }

    public void setProductionQuantity(BigDecimal productionQuantity) {
        set(PROPERTY_PRODUCTIONQUANTITY, productionQuantity);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BigDecimal getConversionRate() {
        return (BigDecimal) get(PROPERTY_CONVERSIONRATE);
    }

    public void setConversionRate(BigDecimal conversionRate) {
        set(PROPERTY_CONVERSIONRATE, conversionRate);
    }

    public BigDecimal getCostCenterUse() {
        return (BigDecimal) get(PROPERTY_COSTCENTERUSE);
    }

    public void setCostCenterUse(BigDecimal costCenterUse) {
        set(PROPERTY_COSTCENTERUSE, costCenterUse);
    }

    public WorkRequirementOperation getWRPhase() {
        return (WorkRequirementOperation) get(PROPERTY_WRPHASE);
    }

    public void setWRPhase(WorkRequirementOperation wRPhase) {
        set(PROPERTY_WRPHASE, wRPhase);
    }

    public Long getRequiredQuantity() {
        return (Long) get(PROPERTY_REQUIREDQUANTITY);
    }

    public void setRequiredQuantity(Long requiredQuantity) {
        set(PROPERTY_REQUIREDQUANTITY, requiredQuantity);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Long getRejectedQuantity() {
        return (Long) get(PROPERTY_REJECTEDQUANTITY);
    }

    public void setRejectedQuantity(Long rejectedQuantity) {
        set(PROPERTY_REJECTEDQUANTITY, rejectedQuantity);
    }

    public BigDecimal getProcessQuantity() {
        return (BigDecimal) get(PROPERTY_PROCESSQUANTITY);
    }

    public void setProcessQuantity(BigDecimal processQuantity) {
        set(PROPERTY_PROCESSQUANTITY, processQuantity);
    }

    public String getProcessUnit() {
        return (String) get(PROPERTY_PROCESSUNIT);
    }

    public void setProcessUnit(String processUnit) {
        set(PROPERTY_PROCESSUNIT, processUnit);
    }

    public Boolean isCreateStandards() {
        return (Boolean) get(PROPERTY_CREATESTANDARDS);
    }

    public void setCreateStandards(Boolean createStandards) {
        set(PROPERTY_CREATESTANDARDS, createStandards);
    }

    public BigDecimal getEstimatedCost() {
        return (BigDecimal) get(PROPERTY_ESTIMATEDCOST);
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        set(PROPERTY_ESTIMATEDCOST, estimatedCost);
    }

    public CostcenterVersion getCostCenterVersion() {
        return (CostcenterVersion) get(PROPERTY_COSTCENTERVERSION);
    }

    public void setCostCenterVersion(CostcenterVersion costCenterVersion) {
        set(PROPERTY_COSTCENTERVERSION, costCenterVersion);
    }

    public Boolean isOutsourced() {
        return (Boolean) get(PROPERTY_OUTSOURCED);
    }

    public void setOutsourced(Boolean outsourced) {
        set(PROPERTY_OUTSOURCED, outsourced);
    }

    public Timestamp getStartingTime() {
        return (Timestamp) get(PROPERTY_STARTINGTIME);
    }

    public void setStartingTime(Timestamp startingTime) {
        set(PROPERTY_STARTINGTIME, startingTime);
    }

    public Timestamp getEndingTime() {
        return (Timestamp) get(PROPERTY_ENDINGTIME);
    }

    public void setEndingTime(Timestamp endingTime) {
        set(PROPERTY_ENDINGTIME, endingTime);
    }

    public BigDecimal getEstimatedTime() {
        return (BigDecimal) get(PROPERTY_ESTIMATEDTIME);
    }

    public void setEstimatedTime(BigDecimal estimatedTime) {
        set(PROPERTY_ESTIMATEDTIME, estimatedTime);
    }

    public Long getRunTime() {
        return (Long) get(PROPERTY_RUNTIME);
    }

    public void setRunTime(Long runTime) {
        set(PROPERTY_RUNTIME, runTime);
    }

    public Boolean isClosephase() {
        return (Boolean) get(PROPERTY_CLOSEPHASE);
    }

    public void setClosephase(Boolean closephase) {
        set(PROPERTY_CLOSEPHASE, closephase);
    }

    public Boolean isValidateWorkEffort() {
        return (Boolean) get(PROPERTY_VALIDATEWORKEFFORT);
    }

    public void setValidateWorkEffort(Boolean validateWorkEffort) {
        set(PROPERTY_VALIDATEWORKEFFORT, validateWorkEffort);
    }

    public Date getProductionplandate() {
        return (Date) get(PROPERTY_PRODUCTIONPLANDATE);
    }

    public void setProductionplandate(Date productionplandate) {
        set(PROPERTY_PRODUCTIONPLANDATE, productionplandate);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionLine> getManufacturingProductionLineList() {
        return (List<ProductionLine>) get(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST);
    }

    public void setManufacturingProductionLineList(List<ProductionLine> manufacturingProductionLineList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST, manufacturingProductionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunEmployee> getManufacturingProductionRunEmployeeList() {
        return (List<ProductionRunEmployee>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST);
    }

    public void setManufacturingProductionRunEmployeeList(List<ProductionRunEmployee> manufacturingProductionRunEmployeeList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST, manufacturingProductionRunEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunIndirectCosts> getManufacturingProductionRunIndirectCostsList() {
        return (List<ProductionRunIndirectCosts>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNINDIRECTCOSTSLIST);
    }

    public void setManufacturingProductionRunIndirectCostsList(List<ProductionRunIndirectCosts> manufacturingProductionRunIndirectCostsList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNINDIRECTCOSTSLIST, manufacturingProductionRunIndirectCostsList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunInvoiceLine> getManufacturingProductionRunInvoiceLineList() {
        return (List<ProductionRunInvoiceLine>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNINVOICELINELIST);
    }

    public void setManufacturingProductionRunInvoiceLineList(List<ProductionRunInvoiceLine> manufacturingProductionRunInvoiceLineList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNINVOICELINELIST, manufacturingProductionRunInvoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunMachine> getManufacturingProductionRunMachineList() {
        return (List<ProductionRunMachine>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNMACHINELIST);
    }

    public void setManufacturingProductionRunMachineList(List<ProductionRunMachine> manufacturingProductionRunMachineList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNMACHINELIST, manufacturingProductionRunMachineList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunToolset> getManufacturingProductionRunToolsetList() {
        return (List<ProductionRunToolset>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNTOOLSETLIST);
    }

    public void setManufacturingProductionRunToolsetList(List<ProductionRunToolset> manufacturingProductionRunToolsetList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNTOOLSETLIST, manufacturingProductionRunToolsetList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkEffortIncidence> getManufacturingWorkEffortIncidenceList() {
        return (List<WorkEffortIncidence>) get(PROPERTY_MANUFACTURINGWORKEFFORTINCIDENCELIST);
    }

    public void setManufacturingWorkEffortIncidenceList(List<WorkEffortIncidence> manufacturingWorkEffortIncidenceList) {
        set(PROPERTY_MANUFACTURINGWORKEFFORTINCIDENCELIST, manufacturingWorkEffortIncidenceList);
    }

}
