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
package com.redcarpet.production.data;

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
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
/**
 * Entity class for entity RCPR_ProductionRun (stored in table RCPR_ProductionRun).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPR_ProductionRun extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_ProductionRun";
    public static final String ENTITY_NAME = "RCPR_ProductionRun";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_MOVEMENTDATE = "movementDate";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_EXPECTEDENDDATE = "expectedEndDate";
    public static final String PROPERTY_ISSUEQUANTITY = "issueQuantity";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_CREATELINES = "createLines";
    public static final String PROPERTY_PRODUCTIONISSUE = "productionIssue";
    public static final String PROPERTY_PRODUCTIONISSUEPOST = "productionIssuePost";
    public static final String PROPERTY_PRODUCTIONRECEIPT = "productionReceipt";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_TOTALITEMCOST = "totalItemCost";
    public static final String PROPERTY_EXPENSECOST = "expenseCost";
    public static final String PROPERTY_GROSSAMOUNT = "grossAmount";
    public static final String PROPERTY_ITEMCOST = "itemCost";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_BOMPRODUCTION = "bOMProduction";
    public static final String PROPERTY_PRODUCTIONRECEIPTPOST = "productionReceiptPost";
    public static final String PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST = "materialMgmtMaterialTransactionList";
    public static final String PROPERTY_RCPRPREXPENSELIST = "rCPRPrExpenseList";
    public static final String PROPERTY_RCPRPRLABOURLIST = "rCPRPrLabourList";
    public static final String PROPERTY_RCPRPRPRODUCTLIST = "rCPRPrProductList";

    public RCPR_ProductionRun() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATELINES, false);
        setDefaultValue(PROPERTY_PRODUCTIONISSUE, false);
        setDefaultValue(PROPERTY_PRODUCTIONISSUEPOST, false);
        setDefaultValue(PROPERTY_PRODUCTIONRECEIPT, false);
        setDefaultValue(PROPERTY_TOTALITEMCOST, new BigDecimal(0));
        setDefaultValue(PROPERTY_EXPENSECOST, new BigDecimal(0));
        setDefaultValue(PROPERTY_GROSSAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ITEMCOST, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRODUCTIONRECEIPTPOST, false);
        setDefaultValue(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPREXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRLABOURLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRPRODUCTLIST, new ArrayList<Object>());
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

    public Date getMovementDate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementDate(Date movementDate) {
        set(PROPERTY_MOVEMENTDATE, movementDate);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getExpectedEndDate() {
        return (Date) get(PROPERTY_EXPECTEDENDDATE);
    }

    public void setExpectedEndDate(Date expectedEndDate) {
        set(PROPERTY_EXPECTEDENDDATE, expectedEndDate);
    }

    public BigDecimal getIssueQuantity() {
        return (BigDecimal) get(PROPERTY_ISSUEQUANTITY);
    }

    public void setIssueQuantity(BigDecimal issueQuantity) {
        set(PROPERTY_ISSUEQUANTITY, issueQuantity);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public Boolean isCreateLines() {
        return (Boolean) get(PROPERTY_CREATELINES);
    }

    public void setCreateLines(Boolean createLines) {
        set(PROPERTY_CREATELINES, createLines);
    }

    public Boolean isProductionIssue() {
        return (Boolean) get(PROPERTY_PRODUCTIONISSUE);
    }

    public void setProductionIssue(Boolean productionIssue) {
        set(PROPERTY_PRODUCTIONISSUE, productionIssue);
    }

    public Boolean isProductionIssuePost() {
        return (Boolean) get(PROPERTY_PRODUCTIONISSUEPOST);
    }

    public void setProductionIssuePost(Boolean productionIssuePost) {
        set(PROPERTY_PRODUCTIONISSUEPOST, productionIssuePost);
    }

    public Boolean isProductionReceipt() {
        return (Boolean) get(PROPERTY_PRODUCTIONRECEIPT);
    }

    public void setProductionReceipt(Boolean productionReceipt) {
        set(PROPERTY_PRODUCTIONRECEIPT, productionReceipt);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getTotalItemCost() {
        return (BigDecimal) get(PROPERTY_TOTALITEMCOST);
    }

    public void setTotalItemCost(BigDecimal totalItemCost) {
        set(PROPERTY_TOTALITEMCOST, totalItemCost);
    }

    public BigDecimal getExpenseCost() {
        return (BigDecimal) get(PROPERTY_EXPENSECOST);
    }

    public void setExpenseCost(BigDecimal expenseCost) {
        set(PROPERTY_EXPENSECOST, expenseCost);
    }

    public BigDecimal getGrossAmount() {
        return (BigDecimal) get(PROPERTY_GROSSAMOUNT);
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        set(PROPERTY_GROSSAMOUNT, grossAmount);
    }

    public BigDecimal getItemCost() {
        return (BigDecimal) get(PROPERTY_ITEMCOST);
    }

    public void setItemCost(BigDecimal itemCost) {
        set(PROPERTY_ITEMCOST, itemCost);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public RCPR_PrBOM getBOMProduction() {
        return (RCPR_PrBOM) get(PROPERTY_BOMPRODUCTION);
    }

    public void setBOMProduction(RCPR_PrBOM bOMProduction) {
        set(PROPERTY_BOMPRODUCTION, bOMProduction);
    }

    public Boolean isProductionReceiptPost() {
        return (Boolean) get(PROPERTY_PRODUCTIONRECEIPTPOST);
    }

    public void setProductionReceiptPost(Boolean productionReceiptPost) {
        set(PROPERTY_PRODUCTIONRECEIPTPOST, productionReceiptPost);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransaction> getMaterialMgmtMaterialTransactionList() {
        return (List<MaterialTransaction>) get(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST);
    }

    public void setMaterialMgmtMaterialTransactionList(List<MaterialTransaction> materialMgmtMaterialTransactionList) {
        set(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, materialMgmtMaterialTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrExpense> getRCPRPrExpenseList() {
        return (List<RCPR_PrExpense>) get(PROPERTY_RCPRPREXPENSELIST);
    }

    public void setRCPRPrExpenseList(List<RCPR_PrExpense> rCPRPrExpenseList) {
        set(PROPERTY_RCPRPREXPENSELIST, rCPRPrExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrLabour> getRCPRPrLabourList() {
        return (List<RCPR_PrLabour>) get(PROPERTY_RCPRPRLABOURLIST);
    }

    public void setRCPRPrLabourList(List<RCPR_PrLabour> rCPRPrLabourList) {
        set(PROPERTY_RCPRPRLABOURLIST, rCPRPrLabourList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrProduct> getRCPRPrProductList() {
        return (List<RCPR_PrProduct>) get(PROPERTY_RCPRPRPRODUCTLIST);
    }

    public void setRCPRPrProductList(List<RCPR_PrProduct> rCPRPrProductList) {
        set(PROPERTY_RCPRPRPRODUCTLIST, rCPRPrProductList);
    }

}
