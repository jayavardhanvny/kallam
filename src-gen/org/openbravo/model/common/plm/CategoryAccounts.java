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
package org.openbravo.model.common.plm;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
/**
 * Entity class for entity ProductCategoryAccounts (stored in table M_Product_Category_Acct).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CategoryAccounts extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Product_Category_Acct";
    public static final String ENTITY_NAME = "ProductCategoryAccounts";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PRODUCTREVENUE = "productRevenue";
    public static final String PROPERTY_PRODUCTEXPENSE = "productExpense";
    public static final String PROPERTY_FIXEDASSET = "fixedAsset";
    public static final String PROPERTY_PRODUCTCOGS = "productCOGS";
    public static final String PROPERTY_PURCHASEPRICEVARIANCE = "purchasePriceVariance";
    public static final String PROPERTY_INVOICEPRICEVARIANCE = "invoicePriceVariance";
    public static final String PROPERTY_TRADEDISCOUNTRECEIVED = "tradeDiscountReceived";
    public static final String PROPERTY_TRADEDISCOUNTGRANTED = "tradeDiscountGranted";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PRODUCTCOGSRETURN = "productCOGSReturn";
    public static final String PROPERTY_PRODUCTREVENUERETURN = "productRevenueReturn";
    public static final String PROPERTY_PRODUCTDEFERREDREVENUE = "productDeferredRevenue";
    public static final String PROPERTY_DEFEXPENSEACCT = "defExpenseAcct";

    public CategoryAccounts() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
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

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
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

    public AccountingCombination getProductRevenue() {
        return (AccountingCombination) get(PROPERTY_PRODUCTREVENUE);
    }

    public void setProductRevenue(AccountingCombination productRevenue) {
        set(PROPERTY_PRODUCTREVENUE, productRevenue);
    }

    public AccountingCombination getProductExpense() {
        return (AccountingCombination) get(PROPERTY_PRODUCTEXPENSE);
    }

    public void setProductExpense(AccountingCombination productExpense) {
        set(PROPERTY_PRODUCTEXPENSE, productExpense);
    }

    public AccountingCombination getFixedAsset() {
        return (AccountingCombination) get(PROPERTY_FIXEDASSET);
    }

    public void setFixedAsset(AccountingCombination fixedAsset) {
        set(PROPERTY_FIXEDASSET, fixedAsset);
    }

    public AccountingCombination getProductCOGS() {
        return (AccountingCombination) get(PROPERTY_PRODUCTCOGS);
    }

    public void setProductCOGS(AccountingCombination productCOGS) {
        set(PROPERTY_PRODUCTCOGS, productCOGS);
    }

    public AccountingCombination getPurchasePriceVariance() {
        return (AccountingCombination) get(PROPERTY_PURCHASEPRICEVARIANCE);
    }

    public void setPurchasePriceVariance(AccountingCombination purchasePriceVariance) {
        set(PROPERTY_PURCHASEPRICEVARIANCE, purchasePriceVariance);
    }

    public AccountingCombination getInvoicePriceVariance() {
        return (AccountingCombination) get(PROPERTY_INVOICEPRICEVARIANCE);
    }

    public void setInvoicePriceVariance(AccountingCombination invoicePriceVariance) {
        set(PROPERTY_INVOICEPRICEVARIANCE, invoicePriceVariance);
    }

    public AccountingCombination getTradeDiscountReceived() {
        return (AccountingCombination) get(PROPERTY_TRADEDISCOUNTRECEIVED);
    }

    public void setTradeDiscountReceived(AccountingCombination tradeDiscountReceived) {
        set(PROPERTY_TRADEDISCOUNTRECEIVED, tradeDiscountReceived);
    }

    public AccountingCombination getTradeDiscountGranted() {
        return (AccountingCombination) get(PROPERTY_TRADEDISCOUNTGRANTED);
    }

    public void setTradeDiscountGranted(AccountingCombination tradeDiscountGranted) {
        set(PROPERTY_TRADEDISCOUNTGRANTED, tradeDiscountGranted);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public AccountingCombination getProductCOGSReturn() {
        return (AccountingCombination) get(PROPERTY_PRODUCTCOGSRETURN);
    }

    public void setProductCOGSReturn(AccountingCombination productCOGSReturn) {
        set(PROPERTY_PRODUCTCOGSRETURN, productCOGSReturn);
    }

    public AccountingCombination getProductRevenueReturn() {
        return (AccountingCombination) get(PROPERTY_PRODUCTREVENUERETURN);
    }

    public void setProductRevenueReturn(AccountingCombination productRevenueReturn) {
        set(PROPERTY_PRODUCTREVENUERETURN, productRevenueReturn);
    }

    public AccountingCombination getProductDeferredRevenue() {
        return (AccountingCombination) get(PROPERTY_PRODUCTDEFERREDREVENUE);
    }

    public void setProductDeferredRevenue(AccountingCombination productDeferredRevenue) {
        set(PROPERTY_PRODUCTDEFERREDREVENUE, productDeferredRevenue);
    }

    public AccountingCombination getDefExpenseAcct() {
        return (AccountingCombination) get(PROPERTY_DEFEXPENSEACCT);
    }

    public void setDefExpenseAcct(AccountingCombination defExpenseAcct) {
        set(PROPERTY_DEFEXPENSEACCT, defExpenseAcct);
    }

}
