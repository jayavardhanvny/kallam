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
package org.openbravo.advpaymentmngt;

import java.math.BigDecimal;
import java.util.Date;

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
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentProposal;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentScheduleDetail;
import org.openbravo.model.financialmgmt.payment.PaymentPriority;
/**
 * Entity class for entity APRM_PaymentProposalPickEdit (stored in table APRM_Payment_Prop_Pick_Edit).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class APRM_PaymentProposalPickEdit extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "APRM_Payment_Prop_Pick_Edit";
    public static final String ENTITY_NAME = "APRM_PaymentProposalPickEdit";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PAYMENTSCHEDULEDETAIL = "paymentScheduleDetail";
    public static final String PROPERTY_PAYMENTPROPOSAL = "paymentProposal";
    public static final String PROPERTY_ORDERDOCUMENTNO = "orderDocumentno";
    public static final String PROPERTY_INVOICEDOCUMENTNO = "invoiceDocumentno";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_SUPPLIERREFERENCE = "supplierReference";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_DUEDATE = "dueDate";
    public static final String PROPERTY_EXPECTEDDATE = "expectedDate";
    public static final String PROPERTY_INVOICED = "invoiced";
    public static final String PROPERTY_EXPECTEDAMOUNT = "expectedAmount";
    public static final String PROPERTY_OUTSTANDING = "outstanding";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_DIFFERENCE = "difference";
    public static final String PROPERTY_WRITEOFF = "writeoff";
    public static final String PROPERTY_OBSELECTED = "obSelected";
    public static final String PROPERTY_PAYMENTPRIORITY = "paymentPriority";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";

    public APRM_PaymentProposalPickEdit() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_WRITEOFF, false);
        setDefaultValue(PROPERTY_OBSELECTED, false);
        setDefaultValue(PROPERTY_SALESTRANSACTION, false);
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

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public FIN_PaymentScheduleDetail getPaymentScheduleDetail() {
        return (FIN_PaymentScheduleDetail) get(PROPERTY_PAYMENTSCHEDULEDETAIL);
    }

    public void setPaymentScheduleDetail(FIN_PaymentScheduleDetail paymentScheduleDetail) {
        set(PROPERTY_PAYMENTSCHEDULEDETAIL, paymentScheduleDetail);
    }

    public FIN_PaymentProposal getPaymentProposal() {
        return (FIN_PaymentProposal) get(PROPERTY_PAYMENTPROPOSAL);
    }

    public void setPaymentProposal(FIN_PaymentProposal paymentProposal) {
        set(PROPERTY_PAYMENTPROPOSAL, paymentProposal);
    }

    public String getOrderDocumentno() {
        return (String) get(PROPERTY_ORDERDOCUMENTNO);
    }

    public void setOrderDocumentno(String orderDocumentno) {
        set(PROPERTY_ORDERDOCUMENTNO, orderDocumentno);
    }

    public String getInvoiceDocumentno() {
        return (String) get(PROPERTY_INVOICEDOCUMENTNO);
    }

    public void setInvoiceDocumentno(String invoiceDocumentno) {
        set(PROPERTY_INVOICEDOCUMENTNO, invoiceDocumentno);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public String getSupplierReference() {
        return (String) get(PROPERTY_SUPPLIERREFERENCE);
    }

    public void setSupplierReference(String supplierReference) {
        set(PROPERTY_SUPPLIERREFERENCE, supplierReference);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Date getDueDate() {
        return (Date) get(PROPERTY_DUEDATE);
    }

    public void setDueDate(Date dueDate) {
        set(PROPERTY_DUEDATE, dueDate);
    }

    public Date getExpectedDate() {
        return (Date) get(PROPERTY_EXPECTEDDATE);
    }

    public void setExpectedDate(Date expectedDate) {
        set(PROPERTY_EXPECTEDDATE, expectedDate);
    }

    public BigDecimal getInvoiced() {
        return (BigDecimal) get(PROPERTY_INVOICED);
    }

    public void setInvoiced(BigDecimal invoiced) {
        set(PROPERTY_INVOICED, invoiced);
    }

    public BigDecimal getExpectedAmount() {
        return (BigDecimal) get(PROPERTY_EXPECTEDAMOUNT);
    }

    public void setExpectedAmount(BigDecimal expectedAmount) {
        set(PROPERTY_EXPECTEDAMOUNT, expectedAmount);
    }

    public BigDecimal getOutstanding() {
        return (BigDecimal) get(PROPERTY_OUTSTANDING);
    }

    public void setOutstanding(BigDecimal outstanding) {
        set(PROPERTY_OUTSTANDING, outstanding);
    }

    public BigDecimal getPayment() {
        return (BigDecimal) get(PROPERTY_PAYMENT);
    }

    public void setPayment(BigDecimal payment) {
        set(PROPERTY_PAYMENT, payment);
    }

    public BigDecimal getDifference() {
        return (BigDecimal) get(PROPERTY_DIFFERENCE);
    }

    public void setDifference(BigDecimal difference) {
        set(PROPERTY_DIFFERENCE, difference);
    }

    public Boolean isWriteoff() {
        return (Boolean) get(PROPERTY_WRITEOFF);
    }

    public void setWriteoff(Boolean writeoff) {
        set(PROPERTY_WRITEOFF, writeoff);
    }

    public Boolean isObSelected() {
        return (Boolean) get(PROPERTY_OBSELECTED);
    }

    public void setObSelected(Boolean obSelected) {
        set(PROPERTY_OBSELECTED, obSelected);
    }

    public PaymentPriority getPaymentPriority() {
        return (PaymentPriority) get(PROPERTY_PAYMENTPRIORITY);
    }

    public void setPaymentPriority(PaymentPriority paymentPriority) {
        set(PROPERTY_PAYMENTPRIORITY, paymentPriority);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
    }

}
