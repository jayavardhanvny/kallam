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
package org.openbravo.model.financialmgmt.payment;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.bank.BankAccount;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.financialmgmt.cashmgmt.BankStatementLine;
import org.openbravo.model.financialmgmt.cashmgmt.CashBook;
import org.openbravo.model.financialmgmt.cashmgmt.CashJournalLine;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.financialmgmt.tax.Withholding;
import org.openbravo.model.project.Project;
/**
 * Entity class for entity FinancialMgmtDebtPaymentGenerateV (stored in table C_Debt_Payment_Generate).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class DebtPaymentGenerateV extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Debt_Payment_Generate";
    public static final String ENTITY_NAME = "FinancialMgmtDebtPaymentGenerateV";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RECEIPT = "receipt";
    public static final String PROPERTY_SETTLEMENT = "settlement";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_CASHJOURNALLINE = "cashJournalLine";
    public static final String PROPERTY_BANKSTATEMENTLINE = "bankStatementLine";
    public static final String PROPERTY_BANKACCOUNT = "bankAccount";
    public static final String PROPERTY_CASHBOOK = "cashbook";
    public static final String PROPERTY_FORMOFPAYMENT = "formOfPayment";
    public static final String PROPERTY_PAYMENTCOMPLETE = "paymentComplete";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_WRITEOFFAMOUNT = "writeoffAmount";
    public static final String PROPERTY_DUEDATE = "dueDate";
    public static final String PROPERTY_MANUAL = "manual";
    public static final String PROPERTY_VALID = "valid";
    public static final String PROPERTY_GENERATEPROCESSED = "generateProcessed";
    public static final String PROPERTY_BALANCINGAMOUNT = "balancingAmount";
    public static final String PROPERTY_SETTLEMENTCANCELLED = "settlementCancelled";
    public static final String PROPERTY_GLITEM = "gLItem";
    public static final String PROPERTY_DIRECTPOSTING = "directPosting";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_WITHHOLDING = "withholding";
    public static final String PROPERTY_WITHHOLDINGAMOUNT = "withholdingamount";

    public DebtPaymentGenerateV() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RECEIPT, true);
        setDefaultValue(PROPERTY_PAYMENTCOMPLETE, false);
        setDefaultValue(PROPERTY_WRITEOFFAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_VALID, false);
        setDefaultValue(PROPERTY_GENERATEPROCESSED, false);
        setDefaultValue(PROPERTY_DIRECTPOSTING, false);
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

    public Boolean isReceipt() {
        return (Boolean) get(PROPERTY_RECEIPT);
    }

    public void setReceipt(Boolean receipt) {
        set(PROPERTY_RECEIPT, receipt);
    }

    public Settlement getSettlement() {
        return (Settlement) get(PROPERTY_SETTLEMENT);
    }

    public void setSettlement(Settlement settlement) {
        set(PROPERTY_SETTLEMENT, settlement);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Invoice getInvoice() {
        return (Invoice) get(PROPERTY_INVOICE);
    }

    public void setInvoice(Invoice invoice) {
        set(PROPERTY_INVOICE, invoice);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public CashJournalLine getCashJournalLine() {
        return (CashJournalLine) get(PROPERTY_CASHJOURNALLINE);
    }

    public void setCashJournalLine(CashJournalLine cashJournalLine) {
        set(PROPERTY_CASHJOURNALLINE, cashJournalLine);
    }

    public BankStatementLine getBankStatementLine() {
        return (BankStatementLine) get(PROPERTY_BANKSTATEMENTLINE);
    }

    public void setBankStatementLine(BankStatementLine bankStatementLine) {
        set(PROPERTY_BANKSTATEMENTLINE, bankStatementLine);
    }

    public BankAccount getBankAccount() {
        return (BankAccount) get(PROPERTY_BANKACCOUNT);
    }

    public void setBankAccount(BankAccount bankAccount) {
        set(PROPERTY_BANKACCOUNT, bankAccount);
    }

    public CashBook getCashbook() {
        return (CashBook) get(PROPERTY_CASHBOOK);
    }

    public void setCashbook(CashBook cashbook) {
        set(PROPERTY_CASHBOOK, cashbook);
    }

    public String getFormOfPayment() {
        return (String) get(PROPERTY_FORMOFPAYMENT);
    }

    public void setFormOfPayment(String formOfPayment) {
        set(PROPERTY_FORMOFPAYMENT, formOfPayment);
    }

    public Boolean isPaymentComplete() {
        return (Boolean) get(PROPERTY_PAYMENTCOMPLETE);
    }

    public void setPaymentComplete(Boolean paymentComplete) {
        set(PROPERTY_PAYMENTCOMPLETE, paymentComplete);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public BigDecimal getWriteoffAmount() {
        return (BigDecimal) get(PROPERTY_WRITEOFFAMOUNT);
    }

    public void setWriteoffAmount(BigDecimal writeoffAmount) {
        set(PROPERTY_WRITEOFFAMOUNT, writeoffAmount);
    }

    public Date getDueDate() {
        return (Date) get(PROPERTY_DUEDATE);
    }

    public void setDueDate(Date dueDate) {
        set(PROPERTY_DUEDATE, dueDate);
    }

    public Boolean isManual() {
        return (Boolean) get(PROPERTY_MANUAL);
    }

    public void setManual(Boolean manual) {
        set(PROPERTY_MANUAL, manual);
    }

    public Boolean isValid() {
        return (Boolean) get(PROPERTY_VALID);
    }

    public void setValid(Boolean valid) {
        set(PROPERTY_VALID, valid);
    }

    public Boolean isGenerateProcessed() {
        return (Boolean) get(PROPERTY_GENERATEPROCESSED);
    }

    public void setGenerateProcessed(Boolean generateProcessed) {
        set(PROPERTY_GENERATEPROCESSED, generateProcessed);
    }

    public BigDecimal getBalancingAmount() {
        return (BigDecimal) get(PROPERTY_BALANCINGAMOUNT);
    }

    public void setBalancingAmount(BigDecimal balancingAmount) {
        set(PROPERTY_BALANCINGAMOUNT, balancingAmount);
    }

    public Settlement getSettlementCancelled() {
        return (Settlement) get(PROPERTY_SETTLEMENTCANCELLED);
    }

    public void setSettlementCancelled(Settlement settlementCancelled) {
        set(PROPERTY_SETTLEMENTCANCELLED, settlementCancelled);
    }

    public GLItem getGLItem() {
        return (GLItem) get(PROPERTY_GLITEM);
    }

    public void setGLItem(GLItem gLItem) {
        set(PROPERTY_GLITEM, gLItem);
    }

    public Boolean isDirectPosting() {
        return (Boolean) get(PROPERTY_DIRECTPOSTING);
    }

    public void setDirectPosting(Boolean directPosting) {
        set(PROPERTY_DIRECTPOSTING, directPosting);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Withholding getWithholding() {
        return (Withholding) get(PROPERTY_WITHHOLDING);
    }

    public void setWithholding(Withholding withholding) {
        set(PROPERTY_WITHHOLDING, withholding);
    }

    public BigDecimal getWithholdingamount() {
        return (BigDecimal) get(PROPERTY_WITHHOLDINGAMOUNT);
    }

    public void setWithholdingamount(BigDecimal withholdingamount) {
        set(PROPERTY_WITHHOLDINGAMOUNT, withholdingamount);
    }

}
