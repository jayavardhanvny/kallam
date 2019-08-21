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
import org.openbravo.model.common.enterprise.WarehouseRule;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductUOM;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.manufacturing.transaction.WorkRequirementProduct;
/**
 * Entity class for entity ManufacturingOperationProduct (stored in table MA_SequenceProduct).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OperationProduct extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_SequenceProduct";
    public static final String ENTITY_NAME = "ManufacturingOperationProduct";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MASEQUENCE = "mASequence";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_PRODUCTIONTYPE = "productionType";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_ORDERUOM = "orderUOM";
    public static final String PROPERTY_COMPONENTCOST = "componentCost";
    public static final String PROPERTY_DECREASE = "decrease";
    public static final String PROPERTY_REJECTED = "rejected";
    public static final String PROPERTY_COST = "cost";
    public static final String PROPERTY_UNIQUEATTCONSUM = "uniqueattconsum";
    public static final String PROPERTY_COPYPRODUCT = "copyproduct";
    public static final String PROPERTY_DIVISIONGROUPQUANTITY = "divisionGroupQuantity";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_WAREHOUSERULE = "warehouseRule";
    public static final String PROPERTY_MANUFACTURINGOPERATIONPRODUCTATTRIBUTELIST = "manufacturingOperationProductAttributeList";
    public static final String PROPERTY_MANUFACTURINGOPERATIONPRODUCTATTRIBUTEPRODUCTFROMLIST = "manufacturingOperationProductAttributeProductFromList";
    public static final String PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST = "manufacturingWorkRequirementProductList";

    public OperationProduct() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_COMPONENTCOST, new BigDecimal(1));
        setDefaultValue(PROPERTY_DECREASE, new BigDecimal(1));
        setDefaultValue(PROPERTY_REJECTED, new BigDecimal(1));
        setDefaultValue(PROPERTY_UNIQUEATTCONSUM, false);
        setDefaultValue(PROPERTY_COPYPRODUCT, false);
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONPRODUCTATTRIBUTELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONPRODUCTATTRIBUTEPRODUCTFROMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST, new ArrayList<Object>());
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

    public Operation getMASequence() {
        return (Operation) get(PROPERTY_MASEQUENCE);
    }

    public void setMASequence(Operation mASequence) {
        set(PROPERTY_MASEQUENCE, mASequence);
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

    public String getProductionType() {
        return (String) get(PROPERTY_PRODUCTIONTYPE);
    }

    public void setProductionType(String productionType) {
        set(PROPERTY_PRODUCTIONTYPE, productionType);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getOrderQuantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        set(PROPERTY_ORDERQUANTITY, orderQuantity);
    }

    public ProductUOM getOrderUOM() {
        return (ProductUOM) get(PROPERTY_ORDERUOM);
    }

    public void setOrderUOM(ProductUOM orderUOM) {
        set(PROPERTY_ORDERUOM, orderUOM);
    }

    public BigDecimal getComponentCost() {
        return (BigDecimal) get(PROPERTY_COMPONENTCOST);
    }

    public void setComponentCost(BigDecimal componentCost) {
        set(PROPERTY_COMPONENTCOST, componentCost);
    }

    public BigDecimal getDecrease() {
        return (BigDecimal) get(PROPERTY_DECREASE);
    }

    public void setDecrease(BigDecimal decrease) {
        set(PROPERTY_DECREASE, decrease);
    }

    public BigDecimal getRejected() {
        return (BigDecimal) get(PROPERTY_REJECTED);
    }

    public void setRejected(BigDecimal rejected) {
        set(PROPERTY_REJECTED, rejected);
    }

    public BigDecimal getCost() {
        return (BigDecimal) get(PROPERTY_COST);
    }

    public void setCost(BigDecimal cost) {
        set(PROPERTY_COST, cost);
    }

    public Boolean isUniqueattconsum() {
        return (Boolean) get(PROPERTY_UNIQUEATTCONSUM);
    }

    public void setUniqueattconsum(Boolean uniqueattconsum) {
        set(PROPERTY_UNIQUEATTCONSUM, uniqueattconsum);
    }

    public Boolean isCopyproduct() {
        return (Boolean) get(PROPERTY_COPYPRODUCT);
    }

    public void setCopyproduct(Boolean copyproduct) {
        set(PROPERTY_COPYPRODUCT, copyproduct);
    }

    public Long getDivisionGroupQuantity() {
        return (Long) get(PROPERTY_DIVISIONGROUPQUANTITY);
    }

    public void setDivisionGroupQuantity(Long divisionGroupQuantity) {
        set(PROPERTY_DIVISIONGROUPQUANTITY, divisionGroupQuantity);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public WarehouseRule getWarehouseRule() {
        return (WarehouseRule) get(PROPERTY_WAREHOUSERULE);
    }

    public void setWarehouseRule(WarehouseRule warehouseRule) {
        set(PROPERTY_WAREHOUSERULE, warehouseRule);
    }

    @SuppressWarnings("unchecked")
    public List<OperationProductAttribute> getManufacturingOperationProductAttributeList() {
        return (List<OperationProductAttribute>) get(PROPERTY_MANUFACTURINGOPERATIONPRODUCTATTRIBUTELIST);
    }

    public void setManufacturingOperationProductAttributeList(List<OperationProductAttribute> manufacturingOperationProductAttributeList) {
        set(PROPERTY_MANUFACTURINGOPERATIONPRODUCTATTRIBUTELIST, manufacturingOperationProductAttributeList);
    }

    @SuppressWarnings("unchecked")
    public List<OperationProductAttribute> getManufacturingOperationProductAttributeProductFromList() {
        return (List<OperationProductAttribute>) get(PROPERTY_MANUFACTURINGOPERATIONPRODUCTATTRIBUTEPRODUCTFROMLIST);
    }

    public void setManufacturingOperationProductAttributeProductFromList(List<OperationProductAttribute> manufacturingOperationProductAttributeProductFromList) {
        set(PROPERTY_MANUFACTURINGOPERATIONPRODUCTATTRIBUTEPRODUCTFROMLIST, manufacturingOperationProductAttributeProductFromList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkRequirementProduct> getManufacturingWorkRequirementProductList() {
        return (List<WorkRequirementProduct>) get(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST);
    }

    public void setManufacturingWorkRequirementProductList(List<WorkRequirementProduct> manufacturingWorkRequirementProductList) {
        set(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST, manufacturingWorkRequirementProductList);
    }

}
