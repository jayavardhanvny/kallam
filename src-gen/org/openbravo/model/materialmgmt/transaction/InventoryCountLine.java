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

import com.redcarpet.goodsissue.data.RCGITransactions;

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
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductUOM;
import org.openbravo.model.common.uom.UOM;
/**
 * Entity class for entity MaterialMgmtInventoryCountLine (stored in table M_InventoryLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class InventoryCountLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_InventoryLine";
    public static final String ENTITY_NAME = "MaterialMgmtInventoryCountLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PHYSINVENTORY = "physInventory";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_BOOKQUANTITY = "bookQuantity";
    public static final String PROPERTY_QUANTITYCOUNT = "quantityCount";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_ORDERUOM = "orderUOM";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_QUANTITYORDERBOOK = "quantityOrderBook";
    public static final String PROPERTY_COST = "cost";
    public static final String PROPERTY_RELATEDINVENTORY = "relatedInventory";
    public static final String PROPERTY_MATERIALMGMTINVENTORYCOUNTLINERELATEDINVENTORYLIST = "materialMgmtInventoryCountLineRelatedInventoryList";
    public static final String PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST = "materialMgmtMaterialTransactionList";
    public static final String PROPERTY_RCGITRANSACTIONSLIST = "rCGITransactionsList";

    public InventoryCountLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_QUANTITYORDERBOOK, new BigDecimal(0));
        setDefaultValue(PROPERTY_MATERIALMGMTINVENTORYCOUNTLINERELATEDINVENTORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGITRANSACTIONSLIST, new ArrayList<Object>());
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

    public InventoryCount getPhysInventory() {
        return (InventoryCount) get(PROPERTY_PHYSINVENTORY);
    }

    public void setPhysInventory(InventoryCount physInventory) {
        set(PROPERTY_PHYSINVENTORY, physInventory);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public BigDecimal getBookQuantity() {
        return (BigDecimal) get(PROPERTY_BOOKQUANTITY);
    }

    public void setBookQuantity(BigDecimal bookQuantity) {
        set(PROPERTY_BOOKQUANTITY, bookQuantity);
    }

    public BigDecimal getQuantityCount() {
        return (BigDecimal) get(PROPERTY_QUANTITYCOUNT);
    }

    public void setQuantityCount(BigDecimal quantityCount) {
        set(PROPERTY_QUANTITYCOUNT, quantityCount);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public ProductUOM getOrderUOM() {
        return (ProductUOM) get(PROPERTY_ORDERUOM);
    }

    public void setOrderUOM(ProductUOM orderUOM) {
        set(PROPERTY_ORDERUOM, orderUOM);
    }

    public BigDecimal getOrderQuantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        set(PROPERTY_ORDERQUANTITY, orderQuantity);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getQuantityOrderBook() {
        return (BigDecimal) get(PROPERTY_QUANTITYORDERBOOK);
    }

    public void setQuantityOrderBook(BigDecimal quantityOrderBook) {
        set(PROPERTY_QUANTITYORDERBOOK, quantityOrderBook);
    }

    public BigDecimal getCost() {
        return (BigDecimal) get(PROPERTY_COST);
    }

    public void setCost(BigDecimal cost) {
        set(PROPERTY_COST, cost);
    }

    public InventoryCountLine getRelatedInventory() {
        return (InventoryCountLine) get(PROPERTY_RELATEDINVENTORY);
    }

    public void setRelatedInventory(InventoryCountLine relatedInventory) {
        set(PROPERTY_RELATEDINVENTORY, relatedInventory);
    }

    @SuppressWarnings("unchecked")
    public List<InventoryCountLine> getMaterialMgmtInventoryCountLineRelatedInventoryList() {
        return (List<InventoryCountLine>) get(PROPERTY_MATERIALMGMTINVENTORYCOUNTLINERELATEDINVENTORYLIST);
    }

    public void setMaterialMgmtInventoryCountLineRelatedInventoryList(List<InventoryCountLine> materialMgmtInventoryCountLineRelatedInventoryList) {
        set(PROPERTY_MATERIALMGMTINVENTORYCOUNTLINERELATEDINVENTORYLIST, materialMgmtInventoryCountLineRelatedInventoryList);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransaction> getMaterialMgmtMaterialTransactionList() {
        return (List<MaterialTransaction>) get(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST);
    }

    public void setMaterialMgmtMaterialTransactionList(List<MaterialTransaction> materialMgmtMaterialTransactionList) {
        set(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, materialMgmtMaterialTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGITransactions> getRCGITransactionsList() {
        return (List<RCGITransactions>) get(PROPERTY_RCGITRANSACTIONSLIST);
    }

    public void setRCGITransactionsList(List<RCGITransactions> rCGITransactionsList) {
        set(PROPERTY_RCGITRANSACTIONSLIST, rCGITransactionsList);
    }

}
