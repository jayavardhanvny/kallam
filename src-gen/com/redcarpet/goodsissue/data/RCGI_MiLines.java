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
package com.redcarpet.goodsissue.data;

import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.redcarpet.epcg.data.ConeType;
import com.redcarpet.epcg.data.EpcgVariant;
import com.redcarpet.production.data.RCPRMachine;

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
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
/**
 * Entity class for entity RCGI_MiLines (stored in table RCGI_MiLines).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCGI_MiLines extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCGI_MiLines";
    public static final String ENTITY_NAME = "RCGI_MiLines";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_MATERIALISSUE = "materialIssue";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_ORDEREDQUANTITY = "orderedQuantity";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_OPENQUANTITY = "openQuantity";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_PROCESSING = "processing";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_LINENETAMOUNT = "lineNetAmount";
    public static final String PROPERTY_UNITPRICE = "unitprice";
    public static final String PROPERTY_RCPRMACHINE = "rcprMachine";
    public static final String PROPERTY_RCHRWARPYARNTYPE = "rchrWarpyarntype";
    public static final String PROPERTY_EPCGVARIANT = "epcgVariant";
    public static final String PROPERTY_RCGITRANSACTIONS = "rcgiTransactions";
    public static final String PROPERTY_ISSEDCONES = "issedcones";
    public static final String PROPERTY_INDENTTYPE = "indenttype";
    public static final String PROPERTY_EPCGCONETYPE = "epcgConetype";
    public static final String PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST = "materialMgmtMaterialTransactionList";

    public RCGI_MiLines() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ORDEREDQUANTITY, new BigDecimal(1));
        setDefaultValue(PROPERTY_OPENQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESSING, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_LINENETAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_UNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_ISSEDCONES, new BigDecimal(0));
        setDefaultValue(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, new ArrayList<Object>());
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

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public RCGI_MaterialIssue getMaterialIssue() {
        return (RCGI_MaterialIssue) get(PROPERTY_MATERIALISSUE);
    }

    public void setMaterialIssue(RCGI_MaterialIssue materialIssue) {
        set(PROPERTY_MATERIALISSUE, materialIssue);
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

    public BigDecimal getOrderedQuantity() {
        return (BigDecimal) get(PROPERTY_ORDEREDQUANTITY);
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        set(PROPERTY_ORDEREDQUANTITY, orderedQuantity);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public BigDecimal getOpenQuantity() {
        return (BigDecimal) get(PROPERTY_OPENQUANTITY);
    }

    public void setOpenQuantity(BigDecimal openQuantity) {
        set(PROPERTY_OPENQUANTITY, openQuantity);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isProcessing() {
        return (Boolean) get(PROPERTY_PROCESSING);
    }

    public void setProcessing(Boolean processing) {
        set(PROPERTY_PROCESSING, processing);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public BigDecimal getLineNetAmount() {
        return (BigDecimal) get(PROPERTY_LINENETAMOUNT);
    }

    public void setLineNetAmount(BigDecimal lineNetAmount) {
        set(PROPERTY_LINENETAMOUNT, lineNetAmount);
    }

    public BigDecimal getUnitprice() {
        return (BigDecimal) get(PROPERTY_UNITPRICE);
    }

    public void setUnitprice(BigDecimal unitprice) {
        set(PROPERTY_UNITPRICE, unitprice);
    }

    public RCPRMachine getRcprMachine() {
        return (RCPRMachine) get(PROPERTY_RCPRMACHINE);
    }

    public void setRcprMachine(RCPRMachine rcprMachine) {
        set(PROPERTY_RCPRMACHINE, rcprMachine);
    }

    public RCHRWarpyarntype getRchrWarpyarntype() {
        return (RCHRWarpyarntype) get(PROPERTY_RCHRWARPYARNTYPE);
    }

    public void setRchrWarpyarntype(RCHRWarpyarntype rchrWarpyarntype) {
        set(PROPERTY_RCHRWARPYARNTYPE, rchrWarpyarntype);
    }

    public EpcgVariant getEpcgVariant() {
        return (EpcgVariant) get(PROPERTY_EPCGVARIANT);
    }

    public void setEpcgVariant(EpcgVariant epcgVariant) {
        set(PROPERTY_EPCGVARIANT, epcgVariant);
    }

    public RCGITransactions getRcgiTransactions() {
        return (RCGITransactions) get(PROPERTY_RCGITRANSACTIONS);
    }

    public void setRcgiTransactions(RCGITransactions rcgiTransactions) {
        set(PROPERTY_RCGITRANSACTIONS, rcgiTransactions);
    }

    public BigDecimal getIssedcones() {
        return (BigDecimal) get(PROPERTY_ISSEDCONES);
    }

    public void setIssedcones(BigDecimal issedcones) {
        set(PROPERTY_ISSEDCONES, issedcones);
    }

    public String getIndenttype() {
        return (String) get(PROPERTY_INDENTTYPE);
    }

    public void setIndenttype(String indenttype) {
        set(PROPERTY_INDENTTYPE, indenttype);
    }

    public ConeType getEpcgConetype() {
        return (ConeType) get(PROPERTY_EPCGCONETYPE);
    }

    public void setEpcgConetype(ConeType epcgConetype) {
        set(PROPERTY_EPCGCONETYPE, epcgConetype);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransaction> getMaterialMgmtMaterialTransactionList() {
        return (List<MaterialTransaction>) get(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST);
    }

    public void setMaterialMgmtMaterialTransactionList(List<MaterialTransaction> materialMgmtMaterialTransactionList) {
        set(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, materialMgmtMaterialTransactionList);
    }

}
