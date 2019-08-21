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
package org.openbravo.model.financialmgmt.accounting.coa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.AcctSchemaProcess;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.ClientInformation;
import org.openbravo.model.common.bank.BankAccountAccounts;
import org.openbravo.model.common.businesspartner.CategoryAccounts;
import org.openbravo.model.common.businesspartner.CustomerAccounts;
import org.openbravo.model.common.businesspartner.EmployeeAccounts;
import org.openbravo.model.common.businesspartner.VendorAccounts;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationAcctSchema;
import org.openbravo.model.common.enterprise.WarehouseAccounts;
import org.openbravo.model.common.plm.ProductAccounts;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.AccountingFactEndYear;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.financialmgmt.accounting.FIN_FinancialAccountAccounting;
import org.openbravo.model.financialmgmt.accounting.report.AccountingReport;
import org.openbravo.model.financialmgmt.accounting.report.AccountingRptElement;
import org.openbravo.model.financialmgmt.assetmgmt.AssetAccounts;
import org.openbravo.model.financialmgmt.assetmgmt.AssetGroupAcct;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.cashmgmt.CashBookAccounts;
import org.openbravo.model.financialmgmt.gl.GLChargeAccounts;
import org.openbravo.model.financialmgmt.gl.GLItemAccounts;
import org.openbravo.model.financialmgmt.gl.GLJournal;
import org.openbravo.model.financialmgmt.tax.TaxPayment;
import org.openbravo.model.financialmgmt.tax.TaxRateAccounts;
import org.openbravo.model.financialmgmt.tax.WithholdingAccounts;
import org.openbravo.model.project.ProjectAccounts;
/**
 * Entity class for entity FinancialMgmtAcctSchema (stored in table C_AcctSchema).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AcctSchema extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_AcctSchema";
    public static final String ENTITY_NAME = "FinancialMgmtAcctSchema";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_GAAP = "gAAP";
    public static final String PROPERTY_ACCRUAL = "accrual";
    public static final String PROPERTY_COSTINGMETHOD = "costingMethod";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_AUTOMATICPERIODCONTROL = "automaticPeriodControl";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_HISTORYDAYS = "historyDays";
    public static final String PROPERTY_FUTUREDAYS = "futureDays";
    public static final String PROPERTY_STORAGEBINSEPARATOR = "storageBinSeparator";
    public static final String PROPERTY_USEACCOUNTALIAS = "useAccountAlias";
    public static final String PROPERTY_USEACCOUNTCOMBINATIONCONTROL = "useAccountCombinationControl";
    public static final String PROPERTY_POSTTRADEDISCOUNT = "postTradeDiscount";
    public static final String PROPERTY_CORRECTTAXFORDISCOUNTSCHARGES = "correctTaxForDiscountsCharges";
    public static final String PROPERTY_ALLOWNEGATIVE = "allowNegative";
    public static final String PROPERTY_CENTRALMAINTENANCE = "centralMaintenance";
    public static final String PROPERTY_ASSETPOSITIVE = "assetPositive";
    public static final String PROPERTY_LIABILITYPOSITIVE = "liabilityPositive";
    public static final String PROPERTY_EQUITYPOSITIVE = "equityPositive";
    public static final String PROPERTY_REVENUEPOSITIVE = "revenuePositive";
    public static final String PROPERTY_EXPENSEPOSITIVE = "expensePositive";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSLIST = "bankAccountAccountsList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTLIST = "businessPartnerCategoryAccountList";
    public static final String PROPERTY_CACCTSCHEMAPROCESSLIST = "cAcctSchemaProcessList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYACCOUNTINGSCHEMALIST = "clientInformationPrimaryAccountingSchemaList";
    public static final String PROPERTY_CLIENTINFORMATIONSECONDACCOUNTINGSCHEMALIST = "clientInformationSecondAccountingSchemaList";
    public static final String PROPERTY_CLIENTINFORMATIONTHIRDACCOUNTINGSCHEMALIST = "clientInformationThirdAccountingSchemaList";
    public static final String PROPERTY_CUSTOMERACCOUNTSLIST = "customerAccountsList";
    public static final String PROPERTY_EMPLOYEEACCOUNTSLIST = "employeeAccountsList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTLIST = "fINFinancialAccountAcctList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST = "financialMgmtAccountingCombinationList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARLIST = "financialMgmtAccountingFactEndYearList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGREPORTLIST = "financialMgmtAccountingReportList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGRPTELEMENTLIST = "financialMgmtAccountingRptElementList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTLIST = "financialMgmtAcctSchemaDefaultList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST = "financialMgmtAcctSchemaElementList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLLIST = "financialMgmtAcctSchemaGLList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMATABLELIST = "financialMgmtAcctSchemaTableList";
    public static final String PROPERTY_FINANCIALMGMTASSETACCOUNTSLIST = "financialMgmtAssetAccountsList";
    public static final String PROPERTY_FINANCIALMGMTASSETGROUPACCTLIST = "financialMgmtAssetGroupAcctList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSLIST = "financialMgmtCashBookAccountsList";
    public static final String PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSLIST = "financialMgmtGLChargeAccountsList";
    public static final String PROPERTY_FINANCIALMGMTGLITEMACCOUNTSLIST = "financialMgmtGLItemAccountsList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTTAXPAYMENTLIST = "financialMgmtTaxPaymentList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSLIST = "financialMgmtTaxRateAccountsList";
    public static final String PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSLIST = "financialMgmtWithholdingAccountsList";
    public static final String PROPERTY_ORGANIZATIONLIST = "organizationList";
    public static final String PROPERTY_ORGANIZATIONACCTSCHEMALIST = "organizationAcctSchemaList";
    public static final String PROPERTY_PRODUCTACCOUNTSLIST = "productAccountsList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSLIST = "productCategoryAccountsList";
    public static final String PROPERTY_PROJECTACCOUNTSLIST = "projectAccountsList";
    public static final String PROPERTY_VENDORACCOUNTSLIST = "vendorAccountsList";
    public static final String PROPERTY_WAREHOUSEACCOUNTSLIST = "warehouseAccountsList";

    public AcctSchema() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_GAAP, "OT");
        setDefaultValue(PROPERTY_ACCRUAL, true);
        setDefaultValue(PROPERTY_AUTOMATICPERIODCONTROL, false);
        setDefaultValue(PROPERTY_STORAGEBINSEPARATOR, "-");
        setDefaultValue(PROPERTY_USEACCOUNTALIAS, false);
        setDefaultValue(PROPERTY_USEACCOUNTCOMBINATIONCONTROL, false);
        setDefaultValue(PROPERTY_POSTTRADEDISCOUNT, false);
        setDefaultValue(PROPERTY_CORRECTTAXFORDISCOUNTSCHARGES, false);
        setDefaultValue(PROPERTY_ALLOWNEGATIVE, false);
        setDefaultValue(PROPERTY_CENTRALMAINTENANCE, true);
        setDefaultValue(PROPERTY_ASSETPOSITIVE, true);
        setDefaultValue(PROPERTY_LIABILITYPOSITIVE, true);
        setDefaultValue(PROPERTY_EQUITYPOSITIVE, true);
        setDefaultValue(PROPERTY_REVENUEPOSITIVE, true);
        setDefaultValue(PROPERTY_EXPENSEPOSITIVE, true);
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CACCTSCHEMAPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYACCOUNTINGSCHEMALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONSECONDACCOUNTINGSCHEMALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONTHIRDACCOUNTINGSCHEMALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CUSTOMERACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLOYEEACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGREPORTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGRPTELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMATABLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETGROUPACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONACCTSCHEMALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VENDORACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSEACCOUNTSLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getGAAP() {
        return (String) get(PROPERTY_GAAP);
    }

    public void setGAAP(String gAAP) {
        set(PROPERTY_GAAP, gAAP);
    }

    public Boolean isAccrual() {
        return (Boolean) get(PROPERTY_ACCRUAL);
    }

    public void setAccrual(Boolean accrual) {
        set(PROPERTY_ACCRUAL, accrual);
    }

    public String getCostingMethod() {
        return (String) get(PROPERTY_COSTINGMETHOD);
    }

    public void setCostingMethod(String costingMethod) {
        set(PROPERTY_COSTINGMETHOD, costingMethod);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Boolean isAutomaticPeriodControl() {
        return (Boolean) get(PROPERTY_AUTOMATICPERIODCONTROL);
    }

    public void setAutomaticPeriodControl(Boolean automaticPeriodControl) {
        set(PROPERTY_AUTOMATICPERIODCONTROL, automaticPeriodControl);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public Long getHistoryDays() {
        return (Long) get(PROPERTY_HISTORYDAYS);
    }

    public void setHistoryDays(Long historyDays) {
        set(PROPERTY_HISTORYDAYS, historyDays);
    }

    public Long getFutureDays() {
        return (Long) get(PROPERTY_FUTUREDAYS);
    }

    public void setFutureDays(Long futureDays) {
        set(PROPERTY_FUTUREDAYS, futureDays);
    }

    public String getStorageBinSeparator() {
        return (String) get(PROPERTY_STORAGEBINSEPARATOR);
    }

    public void setStorageBinSeparator(String storageBinSeparator) {
        set(PROPERTY_STORAGEBINSEPARATOR, storageBinSeparator);
    }

    public Boolean isUseAccountAlias() {
        return (Boolean) get(PROPERTY_USEACCOUNTALIAS);
    }

    public void setUseAccountAlias(Boolean useAccountAlias) {
        set(PROPERTY_USEACCOUNTALIAS, useAccountAlias);
    }

    public Boolean isUseAccountCombinationControl() {
        return (Boolean) get(PROPERTY_USEACCOUNTCOMBINATIONCONTROL);
    }

    public void setUseAccountCombinationControl(Boolean useAccountCombinationControl) {
        set(PROPERTY_USEACCOUNTCOMBINATIONCONTROL, useAccountCombinationControl);
    }

    public Boolean isPostTradeDiscount() {
        return (Boolean) get(PROPERTY_POSTTRADEDISCOUNT);
    }

    public void setPostTradeDiscount(Boolean postTradeDiscount) {
        set(PROPERTY_POSTTRADEDISCOUNT, postTradeDiscount);
    }

    public Boolean isCorrectTaxForDiscountsCharges() {
        return (Boolean) get(PROPERTY_CORRECTTAXFORDISCOUNTSCHARGES);
    }

    public void setCorrectTaxForDiscountsCharges(Boolean correctTaxForDiscountsCharges) {
        set(PROPERTY_CORRECTTAXFORDISCOUNTSCHARGES, correctTaxForDiscountsCharges);
    }

    public Boolean isAllowNegative() {
        return (Boolean) get(PROPERTY_ALLOWNEGATIVE);
    }

    public void setAllowNegative(Boolean allowNegative) {
        set(PROPERTY_ALLOWNEGATIVE, allowNegative);
    }

    public Boolean isCentralMaintenance() {
        return (Boolean) get(PROPERTY_CENTRALMAINTENANCE);
    }

    public void setCentralMaintenance(Boolean centralMaintenance) {
        set(PROPERTY_CENTRALMAINTENANCE, centralMaintenance);
    }

    public Boolean isAssetPositive() {
        return (Boolean) get(PROPERTY_ASSETPOSITIVE);
    }

    public void setAssetPositive(Boolean assetPositive) {
        set(PROPERTY_ASSETPOSITIVE, assetPositive);
    }

    public Boolean isLiabilityPositive() {
        return (Boolean) get(PROPERTY_LIABILITYPOSITIVE);
    }

    public void setLiabilityPositive(Boolean liabilityPositive) {
        set(PROPERTY_LIABILITYPOSITIVE, liabilityPositive);
    }

    public Boolean isEquityPositive() {
        return (Boolean) get(PROPERTY_EQUITYPOSITIVE);
    }

    public void setEquityPositive(Boolean equityPositive) {
        set(PROPERTY_EQUITYPOSITIVE, equityPositive);
    }

    public Boolean isRevenuePositive() {
        return (Boolean) get(PROPERTY_REVENUEPOSITIVE);
    }

    public void setRevenuePositive(Boolean revenuePositive) {
        set(PROPERTY_REVENUEPOSITIVE, revenuePositive);
    }

    public Boolean isExpensePositive() {
        return (Boolean) get(PROPERTY_EXPENSEPOSITIVE);
    }

    public void setExpensePositive(Boolean expensePositive) {
        set(PROPERTY_EXPENSEPOSITIVE, expensePositive);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSLIST);
    }

    public void setBankAccountAccountsList(List<BankAccountAccounts> bankAccountAccountsList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSLIST, bankAccountAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTLIST);
    }

    public void setBusinessPartnerCategoryAccountList(List<CategoryAccounts> businessPartnerCategoryAccountList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTLIST, businessPartnerCategoryAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaProcess> getCAcctSchemaProcessList() {
        return (List<AcctSchemaProcess>) get(PROPERTY_CACCTSCHEMAPROCESSLIST);
    }

    public void setCAcctSchemaProcessList(List<AcctSchemaProcess> cAcctSchemaProcessList) {
        set(PROPERTY_CACCTSCHEMAPROCESSLIST, cAcctSchemaProcessList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryAccountingSchemaList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYACCOUNTINGSCHEMALIST);
    }

    public void setClientInformationPrimaryAccountingSchemaList(List<ClientInformation> clientInformationPrimaryAccountingSchemaList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYACCOUNTINGSCHEMALIST, clientInformationPrimaryAccountingSchemaList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationSecondAccountingSchemaList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONSECONDACCOUNTINGSCHEMALIST);
    }

    public void setClientInformationSecondAccountingSchemaList(List<ClientInformation> clientInformationSecondAccountingSchemaList) {
        set(PROPERTY_CLIENTINFORMATIONSECONDACCOUNTINGSCHEMALIST, clientInformationSecondAccountingSchemaList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationThirdAccountingSchemaList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONTHIRDACCOUNTINGSCHEMALIST);
    }

    public void setClientInformationThirdAccountingSchemaList(List<ClientInformation> clientInformationThirdAccountingSchemaList) {
        set(PROPERTY_CLIENTINFORMATIONTHIRDACCOUNTINGSCHEMALIST, clientInformationThirdAccountingSchemaList);
    }

    @SuppressWarnings("unchecked")
    public List<CustomerAccounts> getCustomerAccountsList() {
        return (List<CustomerAccounts>) get(PROPERTY_CUSTOMERACCOUNTSLIST);
    }

    public void setCustomerAccountsList(List<CustomerAccounts> customerAccountsList) {
        set(PROPERTY_CUSTOMERACCOUNTSLIST, customerAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeAccounts> getEmployeeAccountsList() {
        return (List<EmployeeAccounts>) get(PROPERTY_EMPLOYEEACCOUNTSLIST);
    }

    public void setEmployeeAccountsList(List<EmployeeAccounts> employeeAccountsList) {
        set(PROPERTY_EMPLOYEEACCOUNTSLIST, employeeAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTLIST);
    }

    public void setFINFinancialAccountAcctList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTLIST, fINFinancialAccountAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationList() {
        return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST);
    }

    public void setFinancialMgmtAccountingCombinationList(List<AccountingCombination> financialMgmtAccountingCombinationList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST, financialMgmtAccountingCombinationList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFactEndYear> getFinancialMgmtAccountingFactEndYearList() {
        return (List<AccountingFactEndYear>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARLIST);
    }

    public void setFinancialMgmtAccountingFactEndYearList(List<AccountingFactEndYear> financialMgmtAccountingFactEndYearList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARLIST, financialMgmtAccountingFactEndYearList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingReport> getFinancialMgmtAccountingReportList() {
        return (List<AccountingReport>) get(PROPERTY_FINANCIALMGMTACCOUNTINGREPORTLIST);
    }

    public void setFinancialMgmtAccountingReportList(List<AccountingReport> financialMgmtAccountingReportList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGREPORTLIST, financialMgmtAccountingReportList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingRptElement> getFinancialMgmtAccountingRptElementList() {
        return (List<AccountingRptElement>) get(PROPERTY_FINANCIALMGMTACCOUNTINGRPTELEMENTLIST);
    }

    public void setFinancialMgmtAccountingRptElementList(List<AccountingRptElement> financialMgmtAccountingRptElementList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGRPTELEMENTLIST, financialMgmtAccountingRptElementList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTLIST, financialMgmtAcctSchemaDefaultList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaElement> getFinancialMgmtAcctSchemaElementList() {
        return (List<AcctSchemaElement>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaElementList(List<AcctSchemaElement> financialMgmtAcctSchemaElementList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, financialMgmtAcctSchemaElementList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLLIST);
    }

    public void setFinancialMgmtAcctSchemaGLList(List<AcctSchemaGL> financialMgmtAcctSchemaGLList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLLIST, financialMgmtAcctSchemaGLList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaTable> getFinancialMgmtAcctSchemaTableList() {
        return (List<AcctSchemaTable>) get(PROPERTY_FINANCIALMGMTACCTSCHEMATABLELIST);
    }

    public void setFinancialMgmtAcctSchemaTableList(List<AcctSchemaTable> financialMgmtAcctSchemaTableList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMATABLELIST, financialMgmtAcctSchemaTableList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetAccounts> getFinancialMgmtAssetAccountsList() {
        return (List<AssetAccounts>) get(PROPERTY_FINANCIALMGMTASSETACCOUNTSLIST);
    }

    public void setFinancialMgmtAssetAccountsList(List<AssetAccounts> financialMgmtAssetAccountsList) {
        set(PROPERTY_FINANCIALMGMTASSETACCOUNTSLIST, financialMgmtAssetAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetGroupAcct> getFinancialMgmtAssetGroupAcctList() {
        return (List<AssetGroupAcct>) get(PROPERTY_FINANCIALMGMTASSETGROUPACCTLIST);
    }

    public void setFinancialMgmtAssetGroupAcctList(List<AssetGroupAcct> financialMgmtAssetGroupAcctList) {
        set(PROPERTY_FINANCIALMGMTASSETGROUPACCTLIST, financialMgmtAssetGroupAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getFinancialMgmtBudgetLineList() {
        return (List<BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<CashBookAccounts> getFinancialMgmtCashBookAccountsList() {
        return (List<CashBookAccounts>) get(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSLIST);
    }

    public void setFinancialMgmtCashBookAccountsList(List<CashBookAccounts> financialMgmtCashBookAccountsList) {
        set(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSLIST, financialMgmtCashBookAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<GLChargeAccounts> getFinancialMgmtGLChargeAccountsList() {
        return (List<GLChargeAccounts>) get(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSLIST);
    }

    public void setFinancialMgmtGLChargeAccountsList(List<GLChargeAccounts> financialMgmtGLChargeAccountsList) {
        set(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSLIST, financialMgmtGLChargeAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<GLItemAccounts> getFinancialMgmtGLItemAccountsList() {
        return (List<GLItemAccounts>) get(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSLIST);
    }

    public void setFinancialMgmtGLItemAccountsList(List<GLItemAccounts> financialMgmtGLItemAccountsList) {
        set(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSLIST, financialMgmtGLItemAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getFinancialMgmtGLJournalList() {
        return (List<GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxPayment> getFinancialMgmtTaxPaymentList() {
        return (List<TaxPayment>) get(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST);
    }

    public void setFinancialMgmtTaxPaymentList(List<TaxPayment> financialMgmtTaxPaymentList) {
        set(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, financialMgmtTaxPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRateAccounts> getFinancialMgmtTaxRateAccountsList() {
        return (List<TaxRateAccounts>) get(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSLIST);
    }

    public void setFinancialMgmtTaxRateAccountsList(List<TaxRateAccounts> financialMgmtTaxRateAccountsList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSLIST, financialMgmtTaxRateAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<WithholdingAccounts> getFinancialMgmtWithholdingAccountsList() {
        return (List<WithholdingAccounts>) get(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSLIST);
    }

    public void setFinancialMgmtWithholdingAccountsList(List<WithholdingAccounts> financialMgmtWithholdingAccountsList) {
        set(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSLIST, financialMgmtWithholdingAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<Organization> getOrganizationList() {
        return (List<Organization>) get(PROPERTY_ORGANIZATIONLIST);
    }

    public void setOrganizationList(List<Organization> organizationList) {
        set(PROPERTY_ORGANIZATIONLIST, organizationList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationAcctSchema> getOrganizationAcctSchemaList() {
        return (List<OrganizationAcctSchema>) get(PROPERTY_ORGANIZATIONACCTSCHEMALIST);
    }

    public void setOrganizationAcctSchemaList(List<OrganizationAcctSchema> organizationAcctSchemaList) {
        set(PROPERTY_ORGANIZATIONACCTSCHEMALIST, organizationAcctSchemaList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSLIST);
    }

    public void setProductAccountsList(List<ProductAccounts> productAccountsList) {
        set(PROPERTY_PRODUCTACCOUNTSLIST, productAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSLIST);
    }

    public void setProductCategoryAccountsList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSLIST, productCategoryAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectAccounts> getProjectAccountsList() {
        return (List<ProjectAccounts>) get(PROPERTY_PROJECTACCOUNTSLIST);
    }

    public void setProjectAccountsList(List<ProjectAccounts> projectAccountsList) {
        set(PROPERTY_PROJECTACCOUNTSLIST, projectAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<VendorAccounts> getVendorAccountsList() {
        return (List<VendorAccounts>) get(PROPERTY_VENDORACCOUNTSLIST);
    }

    public void setVendorAccountsList(List<VendorAccounts> vendorAccountsList) {
        set(PROPERTY_VENDORACCOUNTSLIST, vendorAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseAccounts> getWarehouseAccountsList() {
        return (List<WarehouseAccounts>) get(PROPERTY_WAREHOUSEACCOUNTSLIST);
    }

    public void setWarehouseAccountsList(List<WarehouseAccounts> warehouseAccountsList) {
        set(PROPERTY_WAREHOUSEACCOUNTSLIST, warehouseAccountsList);
    }

}
