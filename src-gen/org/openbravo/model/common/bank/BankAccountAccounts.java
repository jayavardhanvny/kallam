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
package org.openbravo.model.common.bank;

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
 * Entity class for entity BankAccountAccounts (stored in table C_BankAccount_Acct).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class BankAccountAccounts extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BankAccount_Acct";
    public static final String ENTITY_NAME = "BankAccountAccounts";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_BANKACCOUNT = "bankAccount";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BANKINTRANSIT = "bankInTransit";
    public static final String PROPERTY_BANKASSET = "bankAsset";
    public static final String PROPERTY_BANKEXPENSE = "bankExpense";
    public static final String PROPERTY_BANKINTERESTREVENUE = "bankInterestRevenue";
    public static final String PROPERTY_BANKINTERESTEXPENSE = "bankInterestExpense";
    public static final String PROPERTY_BANKUNIDENTIFIEDRECEIPTS = "bankUnidentifiedReceipts";
    public static final String PROPERTY_UNALLOCATEDCASH = "unallocatedCash";
    public static final String PROPERTY_PAYMENTSELECTION = "paymentSelection";
    public static final String PROPERTY_BANKSETTLEMENTGAIN = "bankSettlementGain";
    public static final String PROPERTY_BANKSETTLEMENTLOSS = "bankSettlementLoss";
    public static final String PROPERTY_BANKREVALUATIONGAIN = "bankRevaluationGain";
    public static final String PROPERTY_BANKREVALUATIONLOSS = "bankRevaluationLoss";

    public BankAccountAccounts() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public BankAccount getBankAccount() {
        return (BankAccount) get(PROPERTY_BANKACCOUNT);
    }

    public void setBankAccount(BankAccount bankAccount) {
        set(PROPERTY_BANKACCOUNT, bankAccount);
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

    public AccountingCombination getBankInTransit() {
        return (AccountingCombination) get(PROPERTY_BANKINTRANSIT);
    }

    public void setBankInTransit(AccountingCombination bankInTransit) {
        set(PROPERTY_BANKINTRANSIT, bankInTransit);
    }

    public AccountingCombination getBankAsset() {
        return (AccountingCombination) get(PROPERTY_BANKASSET);
    }

    public void setBankAsset(AccountingCombination bankAsset) {
        set(PROPERTY_BANKASSET, bankAsset);
    }

    public AccountingCombination getBankExpense() {
        return (AccountingCombination) get(PROPERTY_BANKEXPENSE);
    }

    public void setBankExpense(AccountingCombination bankExpense) {
        set(PROPERTY_BANKEXPENSE, bankExpense);
    }

    public AccountingCombination getBankInterestRevenue() {
        return (AccountingCombination) get(PROPERTY_BANKINTERESTREVENUE);
    }

    public void setBankInterestRevenue(AccountingCombination bankInterestRevenue) {
        set(PROPERTY_BANKINTERESTREVENUE, bankInterestRevenue);
    }

    public AccountingCombination getBankInterestExpense() {
        return (AccountingCombination) get(PROPERTY_BANKINTERESTEXPENSE);
    }

    public void setBankInterestExpense(AccountingCombination bankInterestExpense) {
        set(PROPERTY_BANKINTERESTEXPENSE, bankInterestExpense);
    }

    public AccountingCombination getBankUnidentifiedReceipts() {
        return (AccountingCombination) get(PROPERTY_BANKUNIDENTIFIEDRECEIPTS);
    }

    public void setBankUnidentifiedReceipts(AccountingCombination bankUnidentifiedReceipts) {
        set(PROPERTY_BANKUNIDENTIFIEDRECEIPTS, bankUnidentifiedReceipts);
    }

    public AccountingCombination getUnallocatedCash() {
        return (AccountingCombination) get(PROPERTY_UNALLOCATEDCASH);
    }

    public void setUnallocatedCash(AccountingCombination unallocatedCash) {
        set(PROPERTY_UNALLOCATEDCASH, unallocatedCash);
    }

    public AccountingCombination getPaymentSelection() {
        return (AccountingCombination) get(PROPERTY_PAYMENTSELECTION);
    }

    public void setPaymentSelection(AccountingCombination paymentSelection) {
        set(PROPERTY_PAYMENTSELECTION, paymentSelection);
    }

    public AccountingCombination getBankSettlementGain() {
        return (AccountingCombination) get(PROPERTY_BANKSETTLEMENTGAIN);
    }

    public void setBankSettlementGain(AccountingCombination bankSettlementGain) {
        set(PROPERTY_BANKSETTLEMENTGAIN, bankSettlementGain);
    }

    public AccountingCombination getBankSettlementLoss() {
        return (AccountingCombination) get(PROPERTY_BANKSETTLEMENTLOSS);
    }

    public void setBankSettlementLoss(AccountingCombination bankSettlementLoss) {
        set(PROPERTY_BANKSETTLEMENTLOSS, bankSettlementLoss);
    }

    public AccountingCombination getBankRevaluationGain() {
        return (AccountingCombination) get(PROPERTY_BANKREVALUATIONGAIN);
    }

    public void setBankRevaluationGain(AccountingCombination bankRevaluationGain) {
        set(PROPERTY_BANKREVALUATIONGAIN, bankRevaluationGain);
    }

    public AccountingCombination getBankRevaluationLoss() {
        return (AccountingCombination) get(PROPERTY_BANKREVALUATIONLOSS);
    }

    public void setBankRevaluationLoss(AccountingCombination bankRevaluationLoss) {
        set(PROPERTY_BANKREVALUATIONLOSS, bankRevaluationLoss);
    }

}
