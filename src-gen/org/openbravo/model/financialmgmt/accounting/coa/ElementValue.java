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
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.bank.BankAccount;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLineV2;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.AccountingFactEndYear;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.financialmgmt.accounting.report.AccountingRptElement;
import org.openbravo.model.financialmgmt.accounting.report.AcctCFS;
import org.openbravo.model.financialmgmt.accounting.report.AcctRptNode;
/**
 * Entity class for entity FinancialMgmtElementValue (stored in table C_ElementValue).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ElementValue extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_ElementValue";
    public static final String ENTITY_NAME = "FinancialMgmtElementValue";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ACCOUNTTYPE = "accountType";
    public static final String PROPERTY_ACCOUNTSIGN = "accountSign";
    public static final String PROPERTY_DOCUMENTCONTROLLED = "documentControlled";
    public static final String PROPERTY_ACCOUNTINGELEMENT = "accountingElement";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_VALIDTODATE = "validToDate";
    public static final String PROPERTY_POSTACTUAL = "postActual";
    public static final String PROPERTY_POSTBUDGET = "postBudget";
    public static final String PROPERTY_POSTENCUMBRANCE = "postEncumbrance";
    public static final String PROPERTY_POSTSTATISTICAL = "postStatistical";
    public static final String PROPERTY_ISBANKACCOUNT = "isBankAccount";
    public static final String PROPERTY_BANKACCOUNT = "bankAccount";
    public static final String PROPERTY_FOREIGNCURRENCYACCOUNT = "foreignCurrencyAccount";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_ELEMENTSHOWN = "elementShown";
    public static final String PROPERTY_SHOWVALUECONDITION = "showValueCondition";
    public static final String PROPERTY_ELEMENTLEVEL = "elementLevel";
    public static final String PROPERTY_TITLENODE = "titleNode";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONACCOUNTLIST = "financialMgmtAccountingCombinationAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTACCOUNTLIST = "financialMgmtAccountingFactAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARACCOUNTLIST = "financialMgmtAccountingFactEndYearAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGRPTELEMENTACCOUNTLIST = "financialMgmtAccountingRptElementAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCTCFSACCOUNTLIST = "financialMgmtAcctCFSAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCTRPTNODELIST = "financialMgmtAcctRptNodeList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST = "financialMgmtAcctSchemaElementList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_FINANCIALMGMTELEMENTVALUEOPERANDLIST = "financialMgmtElementValueOperandList";
    public static final String PROPERTY_FINANCIALMGMTELEMENTVALUEOPERANDACCOUNTLIST = "financialMgmtElementValueOperandAccountList";
    public static final String PROPERTY_FINANCIALMGMTELEMENTVALUETRLLIST = "financialMgmtElementValueTrlList";
    public static final String PROPERTY_INVOICELINEV2ACCOUNTLIST = "invoiceLineV2AccountList";

    public ElementValue() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ACCOUNTTYPE, "E");
        setDefaultValue(PROPERTY_ACCOUNTSIGN, "N");
        setDefaultValue(PROPERTY_DOCUMENTCONTROLLED, false);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_POSTACTUAL, true);
        setDefaultValue(PROPERTY_POSTBUDGET, true);
        setDefaultValue(PROPERTY_POSTENCUMBRANCE, true);
        setDefaultValue(PROPERTY_POSTSTATISTICAL, true);
        setDefaultValue(PROPERTY_ISBANKACCOUNT, false);
        setDefaultValue(PROPERTY_FOREIGNCURRENCYACCOUNT, false);
        setDefaultValue(PROPERTY_ELEMENTSHOWN, true);
        setDefaultValue(PROPERTY_SHOWVALUECONDITION, "A");
        setDefaultValue(PROPERTY_ELEMENTLEVEL, "S");
        setDefaultValue(PROPERTY_TITLENODE, false);
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGRPTELEMENTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTCFSACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTRPTNODELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTVALUEOPERANDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTVALUEOPERANDACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTVALUETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2ACCOUNTLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
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

    public String getAccountType() {
        return (String) get(PROPERTY_ACCOUNTTYPE);
    }

    public void setAccountType(String accountType) {
        set(PROPERTY_ACCOUNTTYPE, accountType);
    }

    public String getAccountSign() {
        return (String) get(PROPERTY_ACCOUNTSIGN);
    }

    public void setAccountSign(String accountSign) {
        set(PROPERTY_ACCOUNTSIGN, accountSign);
    }

    public Boolean isDocumentControlled() {
        return (Boolean) get(PROPERTY_DOCUMENTCONTROLLED);
    }

    public void setDocumentControlled(Boolean documentControlled) {
        set(PROPERTY_DOCUMENTCONTROLLED, documentControlled);
    }

    public Element getAccountingElement() {
        return (Element) get(PROPERTY_ACCOUNTINGELEMENT);
    }

    public void setAccountingElement(Element accountingElement) {
        set(PROPERTY_ACCOUNTINGELEMENT, accountingElement);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public Date getValidToDate() {
        return (Date) get(PROPERTY_VALIDTODATE);
    }

    public void setValidToDate(Date validToDate) {
        set(PROPERTY_VALIDTODATE, validToDate);
    }

    public Boolean isPostActual() {
        return (Boolean) get(PROPERTY_POSTACTUAL);
    }

    public void setPostActual(Boolean postActual) {
        set(PROPERTY_POSTACTUAL, postActual);
    }

    public Boolean isPostBudget() {
        return (Boolean) get(PROPERTY_POSTBUDGET);
    }

    public void setPostBudget(Boolean postBudget) {
        set(PROPERTY_POSTBUDGET, postBudget);
    }

    public Boolean isPostEncumbrance() {
        return (Boolean) get(PROPERTY_POSTENCUMBRANCE);
    }

    public void setPostEncumbrance(Boolean postEncumbrance) {
        set(PROPERTY_POSTENCUMBRANCE, postEncumbrance);
    }

    public Boolean isPostStatistical() {
        return (Boolean) get(PROPERTY_POSTSTATISTICAL);
    }

    public void setPostStatistical(Boolean postStatistical) {
        set(PROPERTY_POSTSTATISTICAL, postStatistical);
    }

    public Boolean isBankAccount() {
        return (Boolean) get(PROPERTY_ISBANKACCOUNT);
    }

    public void setBankAccount(Boolean isBankAccount) {
        set(PROPERTY_ISBANKACCOUNT, isBankAccount);
    }

    public BankAccount getBankAccount() {
        return (BankAccount) get(PROPERTY_BANKACCOUNT);
    }

    public void setBankAccount(BankAccount bankAccount) {
        set(PROPERTY_BANKACCOUNT, bankAccount);
    }

    public Boolean isForeignCurrencyAccount() {
        return (Boolean) get(PROPERTY_FOREIGNCURRENCYACCOUNT);
    }

    public void setForeignCurrencyAccount(Boolean foreignCurrencyAccount) {
        set(PROPERTY_FOREIGNCURRENCYACCOUNT, foreignCurrencyAccount);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Boolean isElementShown() {
        return (Boolean) get(PROPERTY_ELEMENTSHOWN);
    }

    public void setElementShown(Boolean elementShown) {
        set(PROPERTY_ELEMENTSHOWN, elementShown);
    }

    public String getShowValueCondition() {
        return (String) get(PROPERTY_SHOWVALUECONDITION);
    }

    public void setShowValueCondition(String showValueCondition) {
        set(PROPERTY_SHOWVALUECONDITION, showValueCondition);
    }

    public String getElementLevel() {
        return (String) get(PROPERTY_ELEMENTLEVEL);
    }

    public void setElementLevel(String elementLevel) {
        set(PROPERTY_ELEMENTLEVEL, elementLevel);
    }

    public Boolean isTitleNode() {
        return (Boolean) get(PROPERTY_TITLENODE);
    }

    public void setTitleNode(Boolean titleNode) {
        set(PROPERTY_TITLENODE, titleNode);
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
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationAccountList() {
        return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONACCOUNTLIST);
    }

    public void setFinancialMgmtAccountingCombinationAccountList(List<AccountingCombination> financialMgmtAccountingCombinationAccountList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONACCOUNTLIST, financialMgmtAccountingCombinationAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactAccountList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTACCOUNTLIST);
    }

    public void setFinancialMgmtAccountingFactAccountList(List<AccountingFact> financialMgmtAccountingFactAccountList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTACCOUNTLIST, financialMgmtAccountingFactAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFactEndYear> getFinancialMgmtAccountingFactEndYearAccountList() {
        return (List<AccountingFactEndYear>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARACCOUNTLIST);
    }

    public void setFinancialMgmtAccountingFactEndYearAccountList(List<AccountingFactEndYear> financialMgmtAccountingFactEndYearAccountList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARACCOUNTLIST, financialMgmtAccountingFactEndYearAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingRptElement> getFinancialMgmtAccountingRptElementAccountList() {
        return (List<AccountingRptElement>) get(PROPERTY_FINANCIALMGMTACCOUNTINGRPTELEMENTACCOUNTLIST);
    }

    public void setFinancialMgmtAccountingRptElementAccountList(List<AccountingRptElement> financialMgmtAccountingRptElementAccountList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGRPTELEMENTACCOUNTLIST, financialMgmtAccountingRptElementAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctCFS> getFinancialMgmtAcctCFSAccountList() {
        return (List<AcctCFS>) get(PROPERTY_FINANCIALMGMTACCTCFSACCOUNTLIST);
    }

    public void setFinancialMgmtAcctCFSAccountList(List<AcctCFS> financialMgmtAcctCFSAccountList) {
        set(PROPERTY_FINANCIALMGMTACCTCFSACCOUNTLIST, financialMgmtAcctCFSAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctRptNode> getFinancialMgmtAcctRptNodeList() {
        return (List<AcctRptNode>) get(PROPERTY_FINANCIALMGMTACCTRPTNODELIST);
    }

    public void setFinancialMgmtAcctRptNodeList(List<AcctRptNode> financialMgmtAcctRptNodeList) {
        set(PROPERTY_FINANCIALMGMTACCTRPTNODELIST, financialMgmtAcctRptNodeList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaElement> getFinancialMgmtAcctSchemaElementList() {
        return (List<AcctSchemaElement>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaElementList(List<AcctSchemaElement> financialMgmtAcctSchemaElementList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, financialMgmtAcctSchemaElementList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getFinancialMgmtBudgetLineList() {
        return (List<BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ElementValueOperand> getFinancialMgmtElementValueOperandList() {
        return (List<ElementValueOperand>) get(PROPERTY_FINANCIALMGMTELEMENTVALUEOPERANDLIST);
    }

    public void setFinancialMgmtElementValueOperandList(List<ElementValueOperand> financialMgmtElementValueOperandList) {
        set(PROPERTY_FINANCIALMGMTELEMENTVALUEOPERANDLIST, financialMgmtElementValueOperandList);
    }

    @SuppressWarnings("unchecked")
    public List<ElementValueOperand> getFinancialMgmtElementValueOperandAccountList() {
        return (List<ElementValueOperand>) get(PROPERTY_FINANCIALMGMTELEMENTVALUEOPERANDACCOUNTLIST);
    }

    public void setFinancialMgmtElementValueOperandAccountList(List<ElementValueOperand> financialMgmtElementValueOperandAccountList) {
        set(PROPERTY_FINANCIALMGMTELEMENTVALUEOPERANDACCOUNTLIST, financialMgmtElementValueOperandAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<ElementValueTrl> getFinancialMgmtElementValueTrlList() {
        return (List<ElementValueTrl>) get(PROPERTY_FINANCIALMGMTELEMENTVALUETRLLIST);
    }

    public void setFinancialMgmtElementValueTrlList(List<ElementValueTrl> financialMgmtElementValueTrlList) {
        set(PROPERTY_FINANCIALMGMTELEMENTVALUETRLLIST, financialMgmtElementValueTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineV2> getInvoiceLineV2AccountList() {
        return (List<InvoiceLineV2>) get(PROPERTY_INVOICELINEV2ACCOUNTLIST);
    }

    public void setInvoiceLineV2AccountList(List<InvoiceLineV2> invoiceLineV2AccountList) {
        set(PROPERTY_INVOICELINEV2ACCOUNTLIST, invoiceLineV2AccountList);
    }

}
