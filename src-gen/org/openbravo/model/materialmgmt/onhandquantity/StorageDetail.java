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
package org.openbravo.model.materialmgmt.onhandquantity;

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
 * Entity class for entity MaterialMgmtStorageDetail (stored in table M_Storage_Detail).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class StorageDetail extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Storage_Detail";
    public static final String ENTITY_NAME = "MaterialMgmtStorageDetail";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_ORDERUOM = "orderUOM";
    public static final String PROPERTY_QUANTITYONHAND = "quantityOnHand";
    public static final String PROPERTY_ONHANDORDERQUANITY = "onHandOrderQuanity";
    public static final String PROPERTY_LASTINVENTORYCOUNTDATE = "lastInventoryCountDate";
    public static final String PROPERTY_QUANTITYINDRAFTTRANSACTIONS = "quantityInDraftTransactions";
    public static final String PROPERTY_QUANTITYORDERINDRAFTTRANSACTIONS = "quantityOrderInDraftTransactions";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_MATERIALMGMTSTORAGESTOCKAUXLIST = "materialMgmtStorageStockAuxList";
    public static final String PROPERTY_MATERIALMGMTSTORAGESTOCKPROPOSEDLIST = "materialMgmtStorageStockProposedList";

    public StorageDetail() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MATERIALMGMTSTORAGESTOCKAUXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSTORAGESTOCKPROPOSEDLIST, new ArrayList<Object>());
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public ProductUOM getOrderUOM() {
        return (ProductUOM) get(PROPERTY_ORDERUOM);
    }

    public void setOrderUOM(ProductUOM orderUOM) {
        set(PROPERTY_ORDERUOM, orderUOM);
    }

    public BigDecimal getQuantityOnHand() {
        return (BigDecimal) get(PROPERTY_QUANTITYONHAND);
    }

    public void setQuantityOnHand(BigDecimal quantityOnHand) {
        set(PROPERTY_QUANTITYONHAND, quantityOnHand);
    }

    public BigDecimal getOnHandOrderQuanity() {
        return (BigDecimal) get(PROPERTY_ONHANDORDERQUANITY);
    }

    public void setOnHandOrderQuanity(BigDecimal onHandOrderQuanity) {
        set(PROPERTY_ONHANDORDERQUANITY, onHandOrderQuanity);
    }

    public Date getLastInventoryCountDate() {
        return (Date) get(PROPERTY_LASTINVENTORYCOUNTDATE);
    }

    public void setLastInventoryCountDate(Date lastInventoryCountDate) {
        set(PROPERTY_LASTINVENTORYCOUNTDATE, lastInventoryCountDate);
    }

    public BigDecimal getQuantityInDraftTransactions() {
        return (BigDecimal) get(PROPERTY_QUANTITYINDRAFTTRANSACTIONS);
    }

    public void setQuantityInDraftTransactions(BigDecimal quantityInDraftTransactions) {
        set(PROPERTY_QUANTITYINDRAFTTRANSACTIONS, quantityInDraftTransactions);
    }

    public BigDecimal getQuantityOrderInDraftTransactions() {
        return (BigDecimal) get(PROPERTY_QUANTITYORDERINDRAFTTRANSACTIONS);
    }

    public void setQuantityOrderInDraftTransactions(BigDecimal quantityOrderInDraftTransactions) {
        set(PROPERTY_QUANTITYORDERINDRAFTTRANSACTIONS, quantityOrderInDraftTransactions);
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

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    @SuppressWarnings("unchecked")
    public List<StockAux> getMaterialMgmtStorageStockAuxList() {
        return (List<StockAux>) get(PROPERTY_MATERIALMGMTSTORAGESTOCKAUXLIST);
    }

    public void setMaterialMgmtStorageStockAuxList(List<StockAux> materialMgmtStorageStockAuxList) {
        set(PROPERTY_MATERIALMGMTSTORAGESTOCKAUXLIST, materialMgmtStorageStockAuxList);
    }

    @SuppressWarnings("unchecked")
    public List<StockProposed> getMaterialMgmtStorageStockProposedList() {
        return (List<StockProposed>) get(PROPERTY_MATERIALMGMTSTORAGESTOCKPROPOSEDLIST);
    }

    public void setMaterialMgmtStorageStockProposedList(List<StockProposed> materialMgmtStorageStockProposedList) {
        set(PROPERTY_MATERIALMGMTSTORAGESTOCKPROPOSEDLIST, materialMgmtStorageStockProposedList);
    }

}
