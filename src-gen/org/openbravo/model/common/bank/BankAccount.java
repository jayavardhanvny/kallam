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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
import org.openbravo.model.financialmgmt.cashmgmt.BankStatement;
import org.openbravo.model.financialmgmt.payment.DebtPayment;
import org.openbravo.model.financialmgmt.payment.DebtPaymentCancelV;
import org.openbravo.model.financialmgmt.payment.DebtPaymentGenerateV;
import org.openbravo.model.financialmgmt.payment.PromissoryFormat;
import org.openbravo.model.financialmgmt.payment.Remittance;
/**
 * Entity class for entity BankAccount (stored in table C_BankAccount).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class BankAccount extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BankAccount";
    public static final String ENTITY_NAME = "BankAccount";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BANK = "bank";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_ACCOUNTTYPE = "accountType";
    public static final String PROPERTY_ACCOUNTNO = "accountNo";
    public static final String PROPERTY_CURRENTBALANCE = "currentBalance";
    public static final String PROPERTY_CREDITLIMIT = "creditLimit";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_PARTIALACCOUNTNO = "partialAccountNo";
    public static final String PROPERTY_CONTROLDIGIT = "controlDigit";
    public static final String PROPERTY_IBAN = "iBAN";
    public static final String PROPERTY_GENERICACCOUNT = "genericAccount";
    public static final String PROPERTY_SHOWGENERIC = "showGeneric";
    public static final String PROPERTY_SHOWSPANISH = "showSpanish";
    public static final String PROPERTY_SHOWIBAN = "showIBAN";
    public static final String PROPERTY_DISPLAYEDACCOUNT = "displayedAccount";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSLIST = "bankAccountAccountsList";
    public static final String PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST = "businessPartnerBankAccountList";
    public static final String PROPERTY_BUSINESSPARTNERTRANSACTIONALBANKACCOUNTLIST = "businessPartnerTransactionalBankAccountList";
    public static final String PROPERTY_FINANCIALMGMTBANKSTATEMENTLIST = "financialMgmtBankStatementList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST = "financialMgmtDebtPaymentList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST = "financialMgmtDebtPaymentCancelVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST = "financialMgmtDebtPaymentGenerateVList";
    public static final String PROPERTY_FINANCIALMGMTELEMENTVALUELIST = "financialMgmtElementValueList";
    public static final String PROPERTY_FINANCIALMGMTPROMISSORYFORMATLIST = "financialMgmtPromissoryFormatList";
    public static final String PROPERTY_FINANCIALMGMTREMITTANCELIST = "financialMgmtRemittanceList";

    public BankAccount() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_SHOWGENERIC, false);
        setDefaultValue(PROPERTY_SHOWSPANISH, false);
        setDefaultValue(PROPERTY_SHOWIBAN, false);
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERTRANSACTIONALBANKACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBANKSTATEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTVALUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPROMISSORYFORMATLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTREMITTANCELIST, new ArrayList<Object>());
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

    public Bank getBank() {
        return (Bank) get(PROPERTY_BANK);
    }

    public void setBank(Bank bank) {
        set(PROPERTY_BANK, bank);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getAccountType() {
        return (String) get(PROPERTY_ACCOUNTTYPE);
    }

    public void setAccountType(String accountType) {
        set(PROPERTY_ACCOUNTTYPE, accountType);
    }

    public String getAccountNo() {
        return (String) get(PROPERTY_ACCOUNTNO);
    }

    public void setAccountNo(String accountNo) {
        set(PROPERTY_ACCOUNTNO, accountNo);
    }

    public BigDecimal getCurrentBalance() {
        return (BigDecimal) get(PROPERTY_CURRENTBALANCE);
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        set(PROPERTY_CURRENTBALANCE, currentBalance);
    }

    public BigDecimal getCreditLimit() {
        return (BigDecimal) get(PROPERTY_CREDITLIMIT);
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        set(PROPERTY_CREDITLIMIT, creditLimit);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public String getPartialAccountNo() {
        return (String) get(PROPERTY_PARTIALACCOUNTNO);
    }

    public void setPartialAccountNo(String partialAccountNo) {
        set(PROPERTY_PARTIALACCOUNTNO, partialAccountNo);
    }

    public String getControlDigit() {
        return (String) get(PROPERTY_CONTROLDIGIT);
    }

    public void setControlDigit(String controlDigit) {
        set(PROPERTY_CONTROLDIGIT, controlDigit);
    }

    public String getIBAN() {
        return (String) get(PROPERTY_IBAN);
    }

    public void setIBAN(String iBAN) {
        set(PROPERTY_IBAN, iBAN);
    }

    public String getGenericAccount() {
        return (String) get(PROPERTY_GENERICACCOUNT);
    }

    public void setGenericAccount(String genericAccount) {
        set(PROPERTY_GENERICACCOUNT, genericAccount);
    }

    public Boolean isShowGeneric() {
        return (Boolean) get(PROPERTY_SHOWGENERIC);
    }

    public void setShowGeneric(Boolean showGeneric) {
        set(PROPERTY_SHOWGENERIC, showGeneric);
    }

    public Boolean isShowSpanish() {
        return (Boolean) get(PROPERTY_SHOWSPANISH);
    }

    public void setShowSpanish(Boolean showSpanish) {
        set(PROPERTY_SHOWSPANISH, showSpanish);
    }

    public Boolean isShowIBAN() {
        return (Boolean) get(PROPERTY_SHOWIBAN);
    }

    public void setShowIBAN(Boolean showIBAN) {
        set(PROPERTY_SHOWIBAN, showIBAN);
    }

    public String getDisplayedAccount() {
        return (String) get(PROPERTY_DISPLAYEDACCOUNT);
    }

    public void setDisplayedAccount(String displayedAccount) {
        set(PROPERTY_DISPLAYEDACCOUNT, displayedAccount);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSLIST);
    }

    public void setBankAccountAccountsList(List<BankAccountAccounts> bankAccountAccountsList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSLIST, bankAccountAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerBankAccountList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST);
    }

    public void setBusinessPartnerBankAccountList(List<BusinessPartner> businessPartnerBankAccountList) {
        set(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, businessPartnerBankAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerTransactionalBankAccountList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERTRANSACTIONALBANKACCOUNTLIST);
    }

    public void setBusinessPartnerTransactionalBankAccountList(List<BusinessPartner> businessPartnerTransactionalBankAccountList) {
        set(PROPERTY_BUSINESSPARTNERTRANSACTIONALBANKACCOUNTLIST, businessPartnerTransactionalBankAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<BankStatement> getFinancialMgmtBankStatementList() {
        return (List<BankStatement>) get(PROPERTY_FINANCIALMGMTBANKSTATEMENTLIST);
    }

    public void setFinancialMgmtBankStatementList(List<BankStatement> financialMgmtBankStatementList) {
        set(PROPERTY_FINANCIALMGMTBANKSTATEMENTLIST, financialMgmtBankStatementList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPayment> getFinancialMgmtDebtPaymentList() {
        return (List<DebtPayment>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST);
    }

    public void setFinancialMgmtDebtPaymentList(List<DebtPayment> financialMgmtDebtPaymentList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, financialMgmtDebtPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentCancelV> getFinancialMgmtDebtPaymentCancelVList() {
        return (List<DebtPaymentCancelV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST);
    }

    public void setFinancialMgmtDebtPaymentCancelVList(List<DebtPaymentCancelV> financialMgmtDebtPaymentCancelVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, financialMgmtDebtPaymentCancelVList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentGenerateV> getFinancialMgmtDebtPaymentGenerateVList() {
        return (List<DebtPaymentGenerateV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST);
    }

    public void setFinancialMgmtDebtPaymentGenerateVList(List<DebtPaymentGenerateV> financialMgmtDebtPaymentGenerateVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, financialMgmtDebtPaymentGenerateVList);
    }

    @SuppressWarnings("unchecked")
    public List<ElementValue> getFinancialMgmtElementValueList() {
        return (List<ElementValue>) get(PROPERTY_FINANCIALMGMTELEMENTVALUELIST);
    }

    public void setFinancialMgmtElementValueList(List<ElementValue> financialMgmtElementValueList) {
        set(PROPERTY_FINANCIALMGMTELEMENTVALUELIST, financialMgmtElementValueList);
    }

    @SuppressWarnings("unchecked")
    public List<PromissoryFormat> getFinancialMgmtPromissoryFormatList() {
        return (List<PromissoryFormat>) get(PROPERTY_FINANCIALMGMTPROMISSORYFORMATLIST);
    }

    public void setFinancialMgmtPromissoryFormatList(List<PromissoryFormat> financialMgmtPromissoryFormatList) {
        set(PROPERTY_FINANCIALMGMTPROMISSORYFORMATLIST, financialMgmtPromissoryFormatList);
    }

    @SuppressWarnings("unchecked")
    public List<Remittance> getFinancialMgmtRemittanceList() {
        return (List<Remittance>) get(PROPERTY_FINANCIALMGMTREMITTANCELIST);
    }

    public void setFinancialMgmtRemittanceList(List<Remittance> financialMgmtRemittanceList) {
        set(PROPERTY_FINANCIALMGMTREMITTANCELIST, financialMgmtRemittanceList);
    }

}
