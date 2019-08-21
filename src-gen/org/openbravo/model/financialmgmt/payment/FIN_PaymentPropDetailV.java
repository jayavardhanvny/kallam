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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FIN_Payment_Prop_Detail_V (stored in table FIN_Payment_Prop_Detail_V).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_PaymentPropDetailV extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Payment_Prop_Detail_V";
    public static final String ENTITY_NAME = "FIN_Payment_Prop_Detail_V";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PAYMENTSCHEDULEDETAIL = "paymentScheduleDetail";
    public static final String PROPERTY_PAYMENTPROPOSAL = "paymentProposal";
    public static final String PROPERTY_INVOICENO = "invoiceNo";
    public static final String PROPERTY_ORDERNO = "orderNo";
    public static final String PROPERTY_PAYMENTNO = "paymentNo";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_DUEDATE = "dueDate";
    public static final String PROPERTY_EXPECTEDDATE = "expectedDate";
    public static final String PROPERTY_INVOICEAMOUNT = "invoiceAmount";
    public static final String PROPERTY_EXPECTED = "expected";
    public static final String PROPERTY_PAIDAMOUNT = "paidAmount";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_WRITEOFFAMOUNT = "writeoffAmount";

    public FIN_PaymentPropDetailV() {
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

    public String getInvoiceNo() {
        return (String) get(PROPERTY_INVOICENO);
    }

    public void setInvoiceNo(String invoiceNo) {
        set(PROPERTY_INVOICENO, invoiceNo);
    }

    public String getOrderNo() {
        return (String) get(PROPERTY_ORDERNO);
    }

    public void setOrderNo(String orderNo) {
        set(PROPERTY_ORDERNO, orderNo);
    }

    public String getPaymentNo() {
        return (String) get(PROPERTY_PAYMENTNO);
    }

    public void setPaymentNo(String paymentNo) {
        set(PROPERTY_PAYMENTNO, paymentNo);
    }

    public FIN_Payment getPayment() {
        return (FIN_Payment) get(PROPERTY_PAYMENT);
    }

    public void setPayment(FIN_Payment payment) {
        set(PROPERTY_PAYMENT, payment);
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

    public BigDecimal getInvoiceAmount() {
        return (BigDecimal) get(PROPERTY_INVOICEAMOUNT);
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        set(PROPERTY_INVOICEAMOUNT, invoiceAmount);
    }

    public BigDecimal getExpected() {
        return (BigDecimal) get(PROPERTY_EXPECTED);
    }

    public void setExpected(BigDecimal expected) {
        set(PROPERTY_EXPECTED, expected);
    }

    public BigDecimal getPaidAmount() {
        return (BigDecimal) get(PROPERTY_PAIDAMOUNT);
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        set(PROPERTY_PAIDAMOUNT, paidAmount);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public BigDecimal getWriteoffAmount() {
        return (BigDecimal) get(PROPERTY_WRITEOFFAMOUNT);
    }

    public void setWriteoffAmount(BigDecimal writeoffAmount) {
        set(PROPERTY_WRITEOFFAMOUNT, writeoffAmount);
    }

}
