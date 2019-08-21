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
package org.openbravo.model.financialmgmt.accounting;

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
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
/**
 * Entity class for entity FIN_Financial_Account_Acct (stored in table FIN_Financial_Account_Acct).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_FinancialAccountAccounting extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Financial_Account_Acct";
    public static final String ENTITY_NAME = "FIN_Financial_Account_Acct";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RECEIVEPAYMENTACCOUNT = "receivePaymentAccount";
    public static final String PROPERTY_MAKEPAYMENTACCOUNT = "makePaymentAccount";
    public static final String PROPERTY_DEPOSITACCOUNT = "depositAccount";
    public static final String PROPERTY_WITHDRAWALACCOUNT = "withdrawalAccount";
    public static final String PROPERTY_DEBITACCOUNT = "debitAccount";
    public static final String PROPERTY_CREDITACCOUNT = "creditAccount";
    public static final String PROPERTY_FINBANKFEEACCT = "fINBankfeeAcct";
    public static final String PROPERTY_FINBANKREVALUATIONGAINACCT = "fINBankrevaluationgainAcct";
    public static final String PROPERTY_FINBANKREVALUATIONLOSSACCT = "fINBankrevaluationlossAcct";
    public static final String PROPERTY_FINOUTINTRANSITACCT = "fINOutIntransitAcct";
    public static final String PROPERTY_CLEAREDPAYMENTACCOUNTOUT = "clearedPaymentAccountOUT";
    public static final String PROPERTY_INTRANSITPAYMENTACCOUNTIN = "inTransitPaymentAccountIN";
    public static final String PROPERTY_CLEAREDPAYMENTACCOUNT = "clearedPaymentAccount";
    public static final String PROPERTY_FINASSETACCT = "fINAssetAcct";
    public static final String PROPERTY_FINTRANSITORYACCT = "fINTransitoryAcct";
    public static final String PROPERTY_ENABLEBANKSTATEMENT = "enablebankstatement";

    public FIN_FinancialAccountAccounting() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ENABLEBANKSTATEMENT, false);
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

    public FIN_FinancialAccount getAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(FIN_FinancialAccount account) {
        set(PROPERTY_ACCOUNT, account);
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

    public AccountingCombination getReceivePaymentAccount() {
        return (AccountingCombination) get(PROPERTY_RECEIVEPAYMENTACCOUNT);
    }

    public void setReceivePaymentAccount(AccountingCombination receivePaymentAccount) {
        set(PROPERTY_RECEIVEPAYMENTACCOUNT, receivePaymentAccount);
    }

    public AccountingCombination getMakePaymentAccount() {
        return (AccountingCombination) get(PROPERTY_MAKEPAYMENTACCOUNT);
    }

    public void setMakePaymentAccount(AccountingCombination makePaymentAccount) {
        set(PROPERTY_MAKEPAYMENTACCOUNT, makePaymentAccount);
    }

    public AccountingCombination getDepositAccount() {
        return (AccountingCombination) get(PROPERTY_DEPOSITACCOUNT);
    }

    public void setDepositAccount(AccountingCombination depositAccount) {
        set(PROPERTY_DEPOSITACCOUNT, depositAccount);
    }

    public AccountingCombination getWithdrawalAccount() {
        return (AccountingCombination) get(PROPERTY_WITHDRAWALACCOUNT);
    }

    public void setWithdrawalAccount(AccountingCombination withdrawalAccount) {
        set(PROPERTY_WITHDRAWALACCOUNT, withdrawalAccount);
    }

    public AccountingCombination getDebitAccount() {
        return (AccountingCombination) get(PROPERTY_DEBITACCOUNT);
    }

    public void setDebitAccount(AccountingCombination debitAccount) {
        set(PROPERTY_DEBITACCOUNT, debitAccount);
    }

    public AccountingCombination getCreditAccount() {
        return (AccountingCombination) get(PROPERTY_CREDITACCOUNT);
    }

    public void setCreditAccount(AccountingCombination creditAccount) {
        set(PROPERTY_CREDITACCOUNT, creditAccount);
    }

    public AccountingCombination getFINBankfeeAcct() {
        return (AccountingCombination) get(PROPERTY_FINBANKFEEACCT);
    }

    public void setFINBankfeeAcct(AccountingCombination fINBankfeeAcct) {
        set(PROPERTY_FINBANKFEEACCT, fINBankfeeAcct);
    }

    public AccountingCombination getFINBankrevaluationgainAcct() {
        return (AccountingCombination) get(PROPERTY_FINBANKREVALUATIONGAINACCT);
    }

    public void setFINBankrevaluationgainAcct(AccountingCombination fINBankrevaluationgainAcct) {
        set(PROPERTY_FINBANKREVALUATIONGAINACCT, fINBankrevaluationgainAcct);
    }

    public AccountingCombination getFINBankrevaluationlossAcct() {
        return (AccountingCombination) get(PROPERTY_FINBANKREVALUATIONLOSSACCT);
    }

    public void setFINBankrevaluationlossAcct(AccountingCombination fINBankrevaluationlossAcct) {
        set(PROPERTY_FINBANKREVALUATIONLOSSACCT, fINBankrevaluationlossAcct);
    }

    public AccountingCombination getFINOutIntransitAcct() {
        return (AccountingCombination) get(PROPERTY_FINOUTINTRANSITACCT);
    }

    public void setFINOutIntransitAcct(AccountingCombination fINOutIntransitAcct) {
        set(PROPERTY_FINOUTINTRANSITACCT, fINOutIntransitAcct);
    }

    public AccountingCombination getClearedPaymentAccountOUT() {
        return (AccountingCombination) get(PROPERTY_CLEAREDPAYMENTACCOUNTOUT);
    }

    public void setClearedPaymentAccountOUT(AccountingCombination clearedPaymentAccountOUT) {
        set(PROPERTY_CLEAREDPAYMENTACCOUNTOUT, clearedPaymentAccountOUT);
    }

    public AccountingCombination getInTransitPaymentAccountIN() {
        return (AccountingCombination) get(PROPERTY_INTRANSITPAYMENTACCOUNTIN);
    }

    public void setInTransitPaymentAccountIN(AccountingCombination inTransitPaymentAccountIN) {
        set(PROPERTY_INTRANSITPAYMENTACCOUNTIN, inTransitPaymentAccountIN);
    }

    public AccountingCombination getClearedPaymentAccount() {
        return (AccountingCombination) get(PROPERTY_CLEAREDPAYMENTACCOUNT);
    }

    public void setClearedPaymentAccount(AccountingCombination clearedPaymentAccount) {
        set(PROPERTY_CLEAREDPAYMENTACCOUNT, clearedPaymentAccount);
    }

    public AccountingCombination getFINAssetAcct() {
        return (AccountingCombination) get(PROPERTY_FINASSETACCT);
    }

    public void setFINAssetAcct(AccountingCombination fINAssetAcct) {
        set(PROPERTY_FINASSETACCT, fINAssetAcct);
    }

    public AccountingCombination getFINTransitoryAcct() {
        return (AccountingCombination) get(PROPERTY_FINTRANSITORYACCT);
    }

    public void setFINTransitoryAcct(AccountingCombination fINTransitoryAcct) {
        set(PROPERTY_FINTRANSITORYACCT, fINTransitoryAcct);
    }

    public Boolean isEnablebankstatement() {
        return (Boolean) get(PROPERTY_ENABLEBANKSTATEMENT);
    }

    public void setEnablebankstatement(Boolean enablebankstatement) {
        set(PROPERTY_ENABLEBANKSTATEMENT, enablebankstatement);
    }

}
