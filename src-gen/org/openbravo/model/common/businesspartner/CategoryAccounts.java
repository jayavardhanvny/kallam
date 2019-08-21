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
package org.openbravo.model.common.businesspartner;

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
 * Entity class for entity BusinessPartnerCategoryAccount (stored in table C_BP_Group_Acct).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CategoryAccounts extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BP_Group_Acct";
    public static final String ENTITY_NAME = "BusinessPartnerCategoryAccount";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORY = "businessPartnerCategory";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CUSTOMERRECEIVABLESNO = "customerReceivablesNo";
    public static final String PROPERTY_CUSTOMERPREPAYMENT = "customerPrepayment";
    public static final String PROPERTY_VENDORLIABILITY = "vendorLiability";
    public static final String PROPERTY_VENDORSERVICELIABILITY = "vendorServiceLiability";
    public static final String PROPERTY_VENDORPREPAYMENT = "vendorPrepayment";
    public static final String PROPERTY_PAYMENTDISCOUNTEXPENSE = "paymentDiscountExpense";
    public static final String PROPERTY_PAYMENTDISCOUNTREVENUE = "paymentDiscountRevenue";
    public static final String PROPERTY_WRITEOFF = "writeoff";
    public static final String PROPERTY_UNREALIZEDGAINSACCT = "unrealizedGainsAcct";
    public static final String PROPERTY_UNREALIZEDLOSSESACCT = "unrealizedLossesAcct";
    public static final String PROPERTY_REALIZEDGAINACCT = "realizedGainAcct";
    public static final String PROPERTY_REALIZEDLOSSACCT = "realizedLossAcct";
    public static final String PROPERTY_NONINVOICEDRECEIPTS = "nonInvoicedReceipts";
    public static final String PROPERTY_UNEARNEDREVENUE = "unearnedRevenue";
    public static final String PROPERTY_NONINVOICEDREVENUES = "nonInvoicedRevenues";
    public static final String PROPERTY_NONINVOICEDRECEIVABLES = "nonInvoicedReceivables";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_WRITEOFFREVACCT = "writeoffRevAcct";
    public static final String PROPERTY_DOUBTFULDEBTACCOUNT = "doubtfulDebtAccount";
    public static final String PROPERTY_BADDEBTEXPENSEACCOUNT = "badDebtExpenseAccount";
    public static final String PROPERTY_BADDEBTREVENUEACCOUNT = "badDebtRevenueAccount";
    public static final String PROPERTY_ALLOWANCEFORDOUBTFULDEBTACCOUNT = "allowanceForDoubtfulDebtAccount";

    public CategoryAccounts() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
    }

    public Category getBusinessPartnerCategory() {
        return (Category) get(PROPERTY_BUSINESSPARTNERCATEGORY);
    }

    public void setBusinessPartnerCategory(Category businessPartnerCategory) {
        set(PROPERTY_BUSINESSPARTNERCATEGORY, businessPartnerCategory);
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

    public AccountingCombination getCustomerReceivablesNo() {
        return (AccountingCombination) get(PROPERTY_CUSTOMERRECEIVABLESNO);
    }

    public void setCustomerReceivablesNo(AccountingCombination customerReceivablesNo) {
        set(PROPERTY_CUSTOMERRECEIVABLESNO, customerReceivablesNo);
    }

    public AccountingCombination getCustomerPrepayment() {
        return (AccountingCombination) get(PROPERTY_CUSTOMERPREPAYMENT);
    }

    public void setCustomerPrepayment(AccountingCombination customerPrepayment) {
        set(PROPERTY_CUSTOMERPREPAYMENT, customerPrepayment);
    }

    public AccountingCombination getVendorLiability() {
        return (AccountingCombination) get(PROPERTY_VENDORLIABILITY);
    }

    public void setVendorLiability(AccountingCombination vendorLiability) {
        set(PROPERTY_VENDORLIABILITY, vendorLiability);
    }

    public AccountingCombination getVendorServiceLiability() {
        return (AccountingCombination) get(PROPERTY_VENDORSERVICELIABILITY);
    }

    public void setVendorServiceLiability(AccountingCombination vendorServiceLiability) {
        set(PROPERTY_VENDORSERVICELIABILITY, vendorServiceLiability);
    }

    public AccountingCombination getVendorPrepayment() {
        return (AccountingCombination) get(PROPERTY_VENDORPREPAYMENT);
    }

    public void setVendorPrepayment(AccountingCombination vendorPrepayment) {
        set(PROPERTY_VENDORPREPAYMENT, vendorPrepayment);
    }

    public AccountingCombination getPaymentDiscountExpense() {
        return (AccountingCombination) get(PROPERTY_PAYMENTDISCOUNTEXPENSE);
    }

    public void setPaymentDiscountExpense(AccountingCombination paymentDiscountExpense) {
        set(PROPERTY_PAYMENTDISCOUNTEXPENSE, paymentDiscountExpense);
    }

    public AccountingCombination getPaymentDiscountRevenue() {
        return (AccountingCombination) get(PROPERTY_PAYMENTDISCOUNTREVENUE);
    }

    public void setPaymentDiscountRevenue(AccountingCombination paymentDiscountRevenue) {
        set(PROPERTY_PAYMENTDISCOUNTREVENUE, paymentDiscountRevenue);
    }

    public AccountingCombination getWriteoff() {
        return (AccountingCombination) get(PROPERTY_WRITEOFF);
    }

    public void setWriteoff(AccountingCombination writeoff) {
        set(PROPERTY_WRITEOFF, writeoff);
    }

    public AccountingCombination getUnrealizedGainsAcct() {
        return (AccountingCombination) get(PROPERTY_UNREALIZEDGAINSACCT);
    }

    public void setUnrealizedGainsAcct(AccountingCombination unrealizedGainsAcct) {
        set(PROPERTY_UNREALIZEDGAINSACCT, unrealizedGainsAcct);
    }

    public AccountingCombination getUnrealizedLossesAcct() {
        return (AccountingCombination) get(PROPERTY_UNREALIZEDLOSSESACCT);
    }

    public void setUnrealizedLossesAcct(AccountingCombination unrealizedLossesAcct) {
        set(PROPERTY_UNREALIZEDLOSSESACCT, unrealizedLossesAcct);
    }

    public AccountingCombination getRealizedGainAcct() {
        return (AccountingCombination) get(PROPERTY_REALIZEDGAINACCT);
    }

    public void setRealizedGainAcct(AccountingCombination realizedGainAcct) {
        set(PROPERTY_REALIZEDGAINACCT, realizedGainAcct);
    }

    public AccountingCombination getRealizedLossAcct() {
        return (AccountingCombination) get(PROPERTY_REALIZEDLOSSACCT);
    }

    public void setRealizedLossAcct(AccountingCombination realizedLossAcct) {
        set(PROPERTY_REALIZEDLOSSACCT, realizedLossAcct);
    }

    public AccountingCombination getNonInvoicedReceipts() {
        return (AccountingCombination) get(PROPERTY_NONINVOICEDRECEIPTS);
    }

    public void setNonInvoicedReceipts(AccountingCombination nonInvoicedReceipts) {
        set(PROPERTY_NONINVOICEDRECEIPTS, nonInvoicedReceipts);
    }

    public AccountingCombination getUnearnedRevenue() {
        return (AccountingCombination) get(PROPERTY_UNEARNEDREVENUE);
    }

    public void setUnearnedRevenue(AccountingCombination unearnedRevenue) {
        set(PROPERTY_UNEARNEDREVENUE, unearnedRevenue);
    }

    public AccountingCombination getNonInvoicedRevenues() {
        return (AccountingCombination) get(PROPERTY_NONINVOICEDREVENUES);
    }

    public void setNonInvoicedRevenues(AccountingCombination nonInvoicedRevenues) {
        set(PROPERTY_NONINVOICEDREVENUES, nonInvoicedRevenues);
    }

    public AccountingCombination getNonInvoicedReceivables() {
        return (AccountingCombination) get(PROPERTY_NONINVOICEDRECEIVABLES);
    }

    public void setNonInvoicedReceivables(AccountingCombination nonInvoicedReceivables) {
        set(PROPERTY_NONINVOICEDRECEIVABLES, nonInvoicedReceivables);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public AccountingCombination getWriteoffRevAcct() {
        return (AccountingCombination) get(PROPERTY_WRITEOFFREVACCT);
    }

    public void setWriteoffRevAcct(AccountingCombination writeoffRevAcct) {
        set(PROPERTY_WRITEOFFREVACCT, writeoffRevAcct);
    }

    public AccountingCombination getDoubtfulDebtAccount() {
        return (AccountingCombination) get(PROPERTY_DOUBTFULDEBTACCOUNT);
    }

    public void setDoubtfulDebtAccount(AccountingCombination doubtfulDebtAccount) {
        set(PROPERTY_DOUBTFULDEBTACCOUNT, doubtfulDebtAccount);
    }

    public AccountingCombination getBadDebtExpenseAccount() {
        return (AccountingCombination) get(PROPERTY_BADDEBTEXPENSEACCOUNT);
    }

    public void setBadDebtExpenseAccount(AccountingCombination badDebtExpenseAccount) {
        set(PROPERTY_BADDEBTEXPENSEACCOUNT, badDebtExpenseAccount);
    }

    public AccountingCombination getBadDebtRevenueAccount() {
        return (AccountingCombination) get(PROPERTY_BADDEBTREVENUEACCOUNT);
    }

    public void setBadDebtRevenueAccount(AccountingCombination badDebtRevenueAccount) {
        set(PROPERTY_BADDEBTREVENUEACCOUNT, badDebtRevenueAccount);
    }

    public AccountingCombination getAllowanceForDoubtfulDebtAccount() {
        return (AccountingCombination) get(PROPERTY_ALLOWANCEFORDOUBTFULDEBTACCOUNT);
    }

    public void setAllowanceForDoubtfulDebtAccount(AccountingCombination allowanceForDoubtfulDebtAccount) {
        set(PROPERTY_ALLOWANCEFORDOUBTFULDEBTACCOUNT, allowanceForDoubtfulDebtAccount);
    }

}
