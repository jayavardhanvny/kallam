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
package org.openbravo.model.mrp;

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
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.manufacturing.processplan.ProcessPlan;
import org.openbravo.model.manufacturing.transaction.WorkRequirement;
import org.openbravo.model.procurement.RequisitionLine;
/**
 * Entity class for entity MRPProductionRunLine (stored in table MRP_Run_ProductionLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProductionRunLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MRP_Run_ProductionLine";
    public static final String ENTITY_NAME = "MRPProductionRunLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MANUFACTURINGPLAN = "manufacturingPlan";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_REQUIREDQUANTITY = "requiredQuantity";
    public static final String PROPERTY_PLANNEDDATE = "plannedDate";
    public static final String PROPERTY_REPORTSET = "reportSet";
    public static final String PROPERTY_TRANSACTIONTYPE = "transactionType";
    public static final String PROPERTY_FIXED = "fixed";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_WORKREQUIREMENT = "workRequirement";
    public static final String PROPERTY_SALESFORECASTLINE = "salesForecastLine";
    public static final String PROPERTY_REQUISITIONLINE = "requisitionLine";
    public static final String PROPERTY_PROCESSPLAN = "processPlan";
    public static final String PROPERTY_INSERTED = "inserted";
    public static final String PROPERTY_PLANNEDORDERDATE = "plannedOrderDate";
    public static final String PROPERTY_PLANNED = "planned";
    public static final String PROPERTY_TOTALMOVEMENTQUANTITY = "totalMovementQuantity";
    public static final String PROPERTY_RECALCULATESTOCK = "recalculateStock";
    public static final String PROPERTY_MRPPRODUCTIONRUNLINEREPORTSETLIST = "mRPProductionRunLineReportSetList";

    public ProductionRunLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_FIXED, false);
        setDefaultValue(PROPERTY_INSERTED, false);
        setDefaultValue(PROPERTY_PLANNED, false);
        setDefaultValue(PROPERTY_RECALCULATESTOCK, false);
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLINEREPORTSETLIST, new ArrayList<Object>());
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

    public ProductionRun getManufacturingPlan() {
        return (ProductionRun) get(PROPERTY_MANUFACTURINGPLAN);
    }

    public void setManufacturingPlan(ProductionRun manufacturingPlan) {
        set(PROPERTY_MANUFACTURINGPLAN, manufacturingPlan);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public BigDecimal getRequiredQuantity() {
        return (BigDecimal) get(PROPERTY_REQUIREDQUANTITY);
    }

    public void setRequiredQuantity(BigDecimal requiredQuantity) {
        set(PROPERTY_REQUIREDQUANTITY, requiredQuantity);
    }

    public Date getPlannedDate() {
        return (Date) get(PROPERTY_PLANNEDDATE);
    }

    public void setPlannedDate(Date plannedDate) {
        set(PROPERTY_PLANNEDDATE, plannedDate);
    }

    public ProductionRunLine getReportSet() {
        return (ProductionRunLine) get(PROPERTY_REPORTSET);
    }

    public void setReportSet(ProductionRunLine reportSet) {
        set(PROPERTY_REPORTSET, reportSet);
    }

    public String getTransactionType() {
        return (String) get(PROPERTY_TRANSACTIONTYPE);
    }

    public void setTransactionType(String transactionType) {
        set(PROPERTY_TRANSACTIONTYPE, transactionType);
    }

    public Boolean isFixed() {
        return (Boolean) get(PROPERTY_FIXED);
    }

    public void setFixed(Boolean fixed) {
        set(PROPERTY_FIXED, fixed);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public WorkRequirement getWorkRequirement() {
        return (WorkRequirement) get(PROPERTY_WORKREQUIREMENT);
    }

    public void setWorkRequirement(WorkRequirement workRequirement) {
        set(PROPERTY_WORKREQUIREMENT, workRequirement);
    }

    public SalesForecastLine getSalesForecastLine() {
        return (SalesForecastLine) get(PROPERTY_SALESFORECASTLINE);
    }

    public void setSalesForecastLine(SalesForecastLine salesForecastLine) {
        set(PROPERTY_SALESFORECASTLINE, salesForecastLine);
    }

    public RequisitionLine getRequisitionLine() {
        return (RequisitionLine) get(PROPERTY_REQUISITIONLINE);
    }

    public void setRequisitionLine(RequisitionLine requisitionLine) {
        set(PROPERTY_REQUISITIONLINE, requisitionLine);
    }

    public ProcessPlan getProcessPlan() {
        return (ProcessPlan) get(PROPERTY_PROCESSPLAN);
    }

    public void setProcessPlan(ProcessPlan processPlan) {
        set(PROPERTY_PROCESSPLAN, processPlan);
    }

    public Boolean isInserted() {
        return (Boolean) get(PROPERTY_INSERTED);
    }

    public void setInserted(Boolean inserted) {
        set(PROPERTY_INSERTED, inserted);
    }

    public Date getPlannedOrderDate() {
        return (Date) get(PROPERTY_PLANNEDORDERDATE);
    }

    public void setPlannedOrderDate(Date plannedOrderDate) {
        set(PROPERTY_PLANNEDORDERDATE, plannedOrderDate);
    }

    public Boolean isPlanned() {
        return (Boolean) get(PROPERTY_PLANNED);
    }

    public void setPlanned(Boolean planned) {
        set(PROPERTY_PLANNED, planned);
    }

    public BigDecimal getTotalMovementQuantity() {
        return (BigDecimal) get(PROPERTY_TOTALMOVEMENTQUANTITY);
    }

    public void setTotalMovementQuantity(BigDecimal totalMovementQuantity) {
        set(PROPERTY_TOTALMOVEMENTQUANTITY, totalMovementQuantity);
    }

    public Boolean isRecalculateStock() {
        return (Boolean) get(PROPERTY_RECALCULATESTOCK);
    }

    public void setRecalculateStock(Boolean recalculateStock) {
        set(PROPERTY_RECALCULATESTOCK, recalculateStock);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunLine> getMRPProductionRunLineReportSetList() {
        return (List<ProductionRunLine>) get(PROPERTY_MRPPRODUCTIONRUNLINEREPORTSETLIST);
    }

    public void setMRPProductionRunLineReportSetList(List<ProductionRunLine> mRPProductionRunLineReportSetList) {
        set(PROPERTY_MRPPRODUCTIONRUNLINEREPORTSETLIST, mRPProductionRunLineReportSetList);
    }

}
