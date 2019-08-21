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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_PaymentProposalPickEdit;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FIN_Payment_Proposal (stored in table FIN_Payment_Proposal).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_PaymentProposal extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Payment_Proposal";
    public static final String ENTITY_NAME = "FIN_Payment_Proposal";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RECEIPT = "receipt";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_PAYMENTDATE = "paymentDate";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_WRITEOFFAMOUNT = "writeoffAmount";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_REFERENCENO = "referenceNo";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DUEDATE = "duedate";
    public static final String PROPERTY_APRMPROCESSPROPOSAL = "aPRMProcessProposal";
    public static final String PROPERTY_APRMSELEXPECTEDPAYMENTS = "aPRMSelExpectedPayments";
    public static final String PROPERTY_APRMEXECUTEPAYMENT = "aPRMExecutePayment";
    public static final String PROPERTY_FINANCIALTRANSACTIONCONVERTRATE = "financialTransactionConvertRate";
    public static final String PROPERTY_FINANCIALTRANSACTIONAMOUNT = "financialTransactionAmount";
    public static final String PROPERTY_LIMITWRITEOFF = "limitwriteoff";
    public static final String PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST = "aPRMPaymentProposalPickEditList";
    public static final String PROPERTY_FINPAYMENTPROPDETAILLIST = "fINPaymentPropDetailList";
    public static final String PROPERTY_FINPAYMENTPROPDETAILVLIST = "fINPaymentPropDetailVList";

    public FIN_PaymentProposal() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_WRITEOFFAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_STATUS, "RPAP");
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_APRMPROCESSPROPOSAL, "G");
        setDefaultValue(PROPERTY_APRMSELEXPECTEDPAYMENTS, true);
        setDefaultValue(PROPERTY_APRMEXECUTEPAYMENT, false);
        setDefaultValue(PROPERTY_FINANCIALTRANSACTIONCONVERTRATE, new BigDecimal(1));
        setDefaultValue(PROPERTY_FINANCIALTRANSACTIONAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPDETAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPDETAILVLIST, new ArrayList<Object>());
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public FIN_FinancialAccount getAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(FIN_FinancialAccount account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public Date getPaymentDate() {
        return (Date) get(PROPERTY_PAYMENTDATE);
    }

    public void setPaymentDate(Date paymentDate) {
        set(PROPERTY_PAYMENTDATE, paymentDate);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
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

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getReferenceNo() {
        return (String) get(PROPERTY_REFERENCENO);
    }

    public void setReferenceNo(String referenceNo) {
        set(PROPERTY_REFERENCENO, referenceNo);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public Date getDuedate() {
        return (Date) get(PROPERTY_DUEDATE);
    }

    public void setDuedate(Date duedate) {
        set(PROPERTY_DUEDATE, duedate);
    }

    public String getAPRMProcessProposal() {
        return (String) get(PROPERTY_APRMPROCESSPROPOSAL);
    }

    public void setAPRMProcessProposal(String aPRMProcessProposal) {
        set(PROPERTY_APRMPROCESSPROPOSAL, aPRMProcessProposal);
    }

    public Boolean isAPRMSelExpectedPayments() {
        return (Boolean) get(PROPERTY_APRMSELEXPECTEDPAYMENTS);
    }

    public void setAPRMSelExpectedPayments(Boolean aPRMSelExpectedPayments) {
        set(PROPERTY_APRMSELEXPECTEDPAYMENTS, aPRMSelExpectedPayments);
    }

    public Boolean isAPRMExecutePayment() {
        return (Boolean) get(PROPERTY_APRMEXECUTEPAYMENT);
    }

    public void setAPRMExecutePayment(Boolean aPRMExecutePayment) {
        set(PROPERTY_APRMEXECUTEPAYMENT, aPRMExecutePayment);
    }

    public BigDecimal getFinancialTransactionConvertRate() {
        return (BigDecimal) get(PROPERTY_FINANCIALTRANSACTIONCONVERTRATE);
    }

    public void setFinancialTransactionConvertRate(BigDecimal financialTransactionConvertRate) {
        set(PROPERTY_FINANCIALTRANSACTIONCONVERTRATE, financialTransactionConvertRate);
    }

    public BigDecimal getFinancialTransactionAmount() {
        return (BigDecimal) get(PROPERTY_FINANCIALTRANSACTIONAMOUNT);
    }

    public void setFinancialTransactionAmount(BigDecimal financialTransactionAmount) {
        set(PROPERTY_FINANCIALTRANSACTIONAMOUNT, financialTransactionAmount);
    }

    public BigDecimal getLimitwriteoff() {
        return (BigDecimal) get(PROPERTY_LIMITWRITEOFF);
    }

    public void setLimitwriteoff(BigDecimal limitwriteoff) {
        set(PROPERTY_LIMITWRITEOFF, limitwriteoff);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_PaymentProposalPickEdit> getAPRMPaymentProposalPickEditList() {
        return (List<APRM_PaymentProposalPickEdit>) get(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST);
    }

    public void setAPRMPaymentProposalPickEditList(List<APRM_PaymentProposalPickEdit> aPRMPaymentProposalPickEditList) {
        set(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, aPRMPaymentProposalPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentPropDetail> getFINPaymentPropDetailList() {
        return (List<FIN_PaymentPropDetail>) get(PROPERTY_FINPAYMENTPROPDETAILLIST);
    }

    public void setFINPaymentPropDetailList(List<FIN_PaymentPropDetail> fINPaymentPropDetailList) {
        set(PROPERTY_FINPAYMENTPROPDETAILLIST, fINPaymentPropDetailList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentPropDetailV> getFINPaymentPropDetailVList() {
        return (List<FIN_PaymentPropDetailV>) get(PROPERTY_FINPAYMENTPROPDETAILVLIST);
    }

    public void setFINPaymentPropDetailVList(List<FIN_PaymentPropDetailV> fINPaymentPropDetailVList) {
        set(PROPERTY_FINPAYMENTPROPDETAILVLIST, fINPaymentPropDetailVList);
    }

}
