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
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.manufacturing.transaction.WorkRequirement;
import org.openbravo.model.procurement.RequisitionLine;
/**
 * Entity class for entity MRPPurchasingRunLine (stored in table MRP_Run_PurchaseLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PurchasingRunLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MRP_Run_PurchaseLine";
    public static final String ENTITY_NAME = "MRPPurchasingRunLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PURCHASINGPLAN = "purchasingPlan";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_REQUIREDQUANTITY = "requiredQuantity";
    public static final String PROPERTY_PLANNEDDATE = "plannedDate";
    public static final String PROPERTY_TRANSACTIONTYPE = "transactionType";
    public static final String PROPERTY_FIXED = "fixed";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_WORKREQUIREMENT = "workRequirement";
    public static final String PROPERTY_SALESFORECASTLINE = "salesForecastLine";
    public static final String PROPERTY_REQUISITIONLINE = "requisitionLine";
    public static final String PROPERTY_COMPLETED = "completed";
    public static final String PROPERTY_PLANNEDORDERDATE = "plannedOrderDate";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_TOTALMOVEMENTQUANTITY = "totalMovementQuantity";

    public PurchasingRunLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_FIXED, false);
        setDefaultValue(PROPERTY_COMPLETED, false);
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

    public PurchasingRun getPurchasingPlan() {
        return (PurchasingRun) get(PROPERTY_PURCHASINGPLAN);
    }

    public void setPurchasingPlan(PurchasingRun purchasingPlan) {
        set(PROPERTY_PURCHASINGPLAN, purchasingPlan);
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

    public Boolean isCompleted() {
        return (Boolean) get(PROPERTY_COMPLETED);
    }

    public void setCompleted(Boolean completed) {
        set(PROPERTY_COMPLETED, completed);
    }

    public Date getPlannedOrderDate() {
        return (Date) get(PROPERTY_PLANNEDORDERDATE);
    }

    public void setPlannedOrderDate(Date plannedOrderDate) {
        set(PROPERTY_PLANNEDORDERDATE, plannedOrderDate);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public BigDecimal getTotalMovementQuantity() {
        return (BigDecimal) get(PROPERTY_TOTALMOVEMENTQUANTITY);
    }

    public void setTotalMovementQuantity(BigDecimal totalMovementQuantity) {
        set(PROPERTY_TOTALMOVEMENTQUANTITY, totalMovementQuantity);
    }

}
