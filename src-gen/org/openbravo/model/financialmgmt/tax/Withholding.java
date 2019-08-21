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
package org.openbravo.model.financialmgmt.tax;

import java.math.BigDecimal;
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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.financialmgmt.payment.DebtPayment;
import org.openbravo.model.financialmgmt.payment.DebtPaymentCancelV;
import org.openbravo.model.financialmgmt.payment.DebtPaymentGenerateV;
import org.openbravo.model.financialmgmt.payment.DebtPaymentV;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
/**
 * Entity class for entity FinancialMgmtWithholding (stored in table C_Withholding).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Withholding extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Withholding";
    public static final String ENTITY_NAME = "FinancialMgmtWithholding";
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
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_TAXWITHHOLDING = "taxWithholding";
    public static final String PROPERTY_PRORATETAX = "prorateTax";
    public static final String PROPERTY_PAYMENTTOEXTERNALPARTY = "paymentToExternalParty";
    public static final String PROPERTY_BENEFICIARY = "beneficiary";
    public static final String PROPERTY_WITHHOLDINGPERCENTAGE = "withholdingPercentage";
    public static final String PROPERTY_PERCENT = "percent";
    public static final String PROPERTY_FIXAMOUNT = "fixAmount";
    public static final String PROPERTY_THRESHOLDMIN = "thresholdMin";
    public static final String PROPERTY_THRESHOLDMAX = "thresholdMax";
    public static final String PROPERTY_MINAMOUNT = "minAmount";
    public static final String PROPERTY_MAXAMOUNT = "maxAmount";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST = "businessPartnerWithholdingList";
    public static final String PROPERTY_DEBTPAYMENTVLIST = "debtPaymentVList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST = "financialMgmtDebtPaymentList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST = "financialMgmtDebtPaymentCancelVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST = "financialMgmtDebtPaymentGenerateVList";
    public static final String PROPERTY_FINANCIALMGMTGLITEMLIST = "financialMgmtGLItemList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSLIST = "financialMgmtWithholdingAccountsList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";

    public Withholding() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TAXWITHHOLDING, false);
        setDefaultValue(PROPERTY_PRORATETAX, false);
        setDefaultValue(PROPERTY_PAYMENTTOEXTERNALPARTY, false);
        setDefaultValue(PROPERTY_WITHHOLDINGPERCENTAGE, false);
        setDefaultValue(PROPERTY_RATE, new BigDecimal(0));
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DEBTPAYMENTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLITEMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
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

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public Boolean isTaxWithholding() {
        return (Boolean) get(PROPERTY_TAXWITHHOLDING);
    }

    public void setTaxWithholding(Boolean taxWithholding) {
        set(PROPERTY_TAXWITHHOLDING, taxWithholding);
    }

    public Boolean isProrateTax() {
        return (Boolean) get(PROPERTY_PRORATETAX);
    }

    public void setProrateTax(Boolean prorateTax) {
        set(PROPERTY_PRORATETAX, prorateTax);
    }

    public Boolean isPaymentToExternalParty() {
        return (Boolean) get(PROPERTY_PAYMENTTOEXTERNALPARTY);
    }

    public void setPaymentToExternalParty(Boolean paymentToExternalParty) {
        set(PROPERTY_PAYMENTTOEXTERNALPARTY, paymentToExternalParty);
    }

    public BusinessPartner getBeneficiary() {
        return (BusinessPartner) get(PROPERTY_BENEFICIARY);
    }

    public void setBeneficiary(BusinessPartner beneficiary) {
        set(PROPERTY_BENEFICIARY, beneficiary);
    }

    public Boolean isWithholdingPercentage() {
        return (Boolean) get(PROPERTY_WITHHOLDINGPERCENTAGE);
    }

    public void setWithholdingPercentage(Boolean withholdingPercentage) {
        set(PROPERTY_WITHHOLDINGPERCENTAGE, withholdingPercentage);
    }

    public BigDecimal getPercent() {
        return (BigDecimal) get(PROPERTY_PERCENT);
    }

    public void setPercent(BigDecimal percent) {
        set(PROPERTY_PERCENT, percent);
    }

    public BigDecimal getFixAmount() {
        return (BigDecimal) get(PROPERTY_FIXAMOUNT);
    }

    public void setFixAmount(BigDecimal fixAmount) {
        set(PROPERTY_FIXAMOUNT, fixAmount);
    }

    public BigDecimal getThresholdMin() {
        return (BigDecimal) get(PROPERTY_THRESHOLDMIN);
    }

    public void setThresholdMin(BigDecimal thresholdMin) {
        set(PROPERTY_THRESHOLDMIN, thresholdMin);
    }

    public BigDecimal getThresholdMax() {
        return (BigDecimal) get(PROPERTY_THRESHOLDMAX);
    }

    public void setThresholdMax(BigDecimal thresholdMax) {
        set(PROPERTY_THRESHOLDMAX, thresholdMax);
    }

    public BigDecimal getMinAmount() {
        return (BigDecimal) get(PROPERTY_MINAMOUNT);
    }

    public void setMinAmount(BigDecimal minAmount) {
        set(PROPERTY_MINAMOUNT, minAmount);
    }

    public BigDecimal getMaxAmount() {
        return (BigDecimal) get(PROPERTY_MAXAMOUNT);
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        set(PROPERTY_MAXAMOUNT, maxAmount);
    }

    public BigDecimal getRate() {
        return (BigDecimal) get(PROPERTY_RATE);
    }

    public void setRate(BigDecimal rate) {
        set(PROPERTY_RATE, rate);
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
    public List<org.openbravo.model.common.businesspartner.Withholding> getBusinessPartnerWithholdingList() {
        return (List<org.openbravo.model.common.businesspartner.Withholding>) get(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST);
    }

    public void setBusinessPartnerWithholdingList(List<org.openbravo.model.common.businesspartner.Withholding> businessPartnerWithholdingList) {
        set(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST, businessPartnerWithholdingList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentV> getDebtPaymentVList() {
        return (List<DebtPaymentV>) get(PROPERTY_DEBTPAYMENTVLIST);
    }

    public void setDebtPaymentVList(List<DebtPaymentV> debtPaymentVList) {
        set(PROPERTY_DEBTPAYMENTVLIST, debtPaymentVList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
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
    public List<GLItem> getFinancialMgmtGLItemList() {
        return (List<GLItem>) get(PROPERTY_FINANCIALMGMTGLITEMLIST);
    }

    public void setFinancialMgmtGLItemList(List<GLItem> financialMgmtGLItemList) {
        set(PROPERTY_FINANCIALMGMTGLITEMLIST, financialMgmtGLItemList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
        return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<WithholdingAccounts> getFinancialMgmtWithholdingAccountsList() {
        return (List<WithholdingAccounts>) get(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSLIST);
    }

    public void setFinancialMgmtWithholdingAccountsList(List<WithholdingAccounts> financialMgmtWithholdingAccountsList) {
        set(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSLIST, financialMgmtWithholdingAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceList() {
        return (List<Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

}
