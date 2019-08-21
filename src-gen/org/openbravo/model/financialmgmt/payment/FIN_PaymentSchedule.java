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

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.order.Order;
/**
 * Entity class for entity FIN_Payment_Schedule (stored in table FIN_Payment_Schedule).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_PaymentSchedule extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Payment_Schedule";
    public static final String ENTITY_NAME = "FIN_Payment_Schedule";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_ORDER = "order";
    public static final String PROPERTY_DUEDATE = "dueDate";
    public static final String PROPERTY_FINPAYMENTMETHOD = "finPaymentmethod";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_PAIDAMOUNT = "paidAmount";
    public static final String PROPERTY_OUTSTANDINGAMOUNT = "outstandingAmount";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_FINPAYMENTPRIORITY = "fINPaymentPriority";
    public static final String PROPERTY_UPDATEPAYMENTPLAN = "updatePaymentPlan";
    public static final String PROPERTY_ORIGDUEDATE = "origDueDate";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_APRMMODIFPAYMENTINPLAN = "aprmModifPaymentINPlan";
    public static final String PROPERTY_APRMMODIFPAYMENTOUTPLAN = "aprmModifPaymentOUTPlan";
    public static final String PROPERTY_EXPECTEDDATE = "expectedDate";
    public static final String PROPERTY_DAYSOVERDUE = "daysOverdue";
    public static final String PROPERTY_LASTPAYMENTDATE = "lastPaymentDate";
    public static final String PROPERTY_NUMBEROFPAYMENTS = "numberOfPayments";
    public static final String PROPERTY_TOTALDEBTAMOUNT = "totalDebtAmount";
    public static final String PROPERTY_FINDOUBTFULDEBTLIST = "fINDoubtfulDebtList";
    public static final String PROPERTY_FINDOUBTFULDEBTVLIST = "fINDoubtfulDebtVList";
    public static final String PROPERTY_FINPAYMENTDETAILVPAYMENTPLANINVOICELIST = "fINPaymentDetailVPaymentPlanInvoiceList";
    public static final String PROPERTY_FINPAYMENTDETAILVPAYMENTPLANORDERLIST = "fINPaymentDetailVPaymentPlanOrderList";
    public static final String PROPERTY_FINPAYMENTSCHEDULEDETAILORDERPAYMENTSCHEDULELIST = "fINPaymentScheduleDetailOrderPaymentScheduleList";
    public static final String PROPERTY_FINPAYMENTSCHEDULEDETAILINVOICEPAYMENTSCHEDULELIST = "fINPaymentScheduleDetailInvoicePaymentScheduleList";

    public FIN_PaymentSchedule() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PAIDAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_OUTSTANDINGAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_UPDATEPAYMENTPLAN, true);
        setDefaultValue(PROPERTY_APRMMODIFPAYMENTINPLAN, false);
        setDefaultValue(PROPERTY_APRMMODIFPAYMENTOUTPLAN, false);
        setDefaultValue(PROPERTY_TOTALDEBTAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVPAYMENTPLANINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVPAYMENTPLANORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDULEDETAILORDERPAYMENTSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDULEDETAILINVOICEPAYMENTSCHEDULELIST, new ArrayList<Object>());
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

    public Invoice getInvoice() {
        return (Invoice) get(PROPERTY_INVOICE);
    }

    public void setInvoice(Invoice invoice) {
        set(PROPERTY_INVOICE, invoice);
    }

    public Order getOrder() {
        return (Order) get(PROPERTY_ORDER);
    }

    public void setOrder(Order order) {
        set(PROPERTY_ORDER, order);
    }

    public Date getDueDate() {
        return (Date) get(PROPERTY_DUEDATE);
    }

    public void setDueDate(Date dueDate) {
        set(PROPERTY_DUEDATE, dueDate);
    }

    public FIN_PaymentMethod getFinPaymentmethod() {
        return (FIN_PaymentMethod) get(PROPERTY_FINPAYMENTMETHOD);
    }

    public void setFinPaymentmethod(FIN_PaymentMethod finPaymentmethod) {
        set(PROPERTY_FINPAYMENTMETHOD, finPaymentmethod);
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

    public BigDecimal getPaidAmount() {
        return (BigDecimal) get(PROPERTY_PAIDAMOUNT);
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        set(PROPERTY_PAIDAMOUNT, paidAmount);
    }

    public BigDecimal getOutstandingAmount() {
        return (BigDecimal) get(PROPERTY_OUTSTANDINGAMOUNT);
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        set(PROPERTY_OUTSTANDINGAMOUNT, outstandingAmount);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public PaymentPriority getFINPaymentPriority() {
        return (PaymentPriority) get(PROPERTY_FINPAYMENTPRIORITY);
    }

    public void setFINPaymentPriority(PaymentPriority fINPaymentPriority) {
        set(PROPERTY_FINPAYMENTPRIORITY, fINPaymentPriority);
    }

    public Boolean isUpdatePaymentPlan() {
        return (Boolean) get(PROPERTY_UPDATEPAYMENTPLAN);
    }

    public void setUpdatePaymentPlan(Boolean updatePaymentPlan) {
        set(PROPERTY_UPDATEPAYMENTPLAN, updatePaymentPlan);
    }

    public Date getOrigDueDate() {
        return (Date) get(PROPERTY_ORIGDUEDATE);
    }

    public void setOrigDueDate(Date origDueDate) {
        set(PROPERTY_ORIGDUEDATE, origDueDate);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isAprmModifPaymentINPlan() {
        return (Boolean) get(PROPERTY_APRMMODIFPAYMENTINPLAN);
    }

    public void setAprmModifPaymentINPlan(Boolean aprmModifPaymentINPlan) {
        set(PROPERTY_APRMMODIFPAYMENTINPLAN, aprmModifPaymentINPlan);
    }

    public Boolean isAprmModifPaymentOUTPlan() {
        return (Boolean) get(PROPERTY_APRMMODIFPAYMENTOUTPLAN);
    }

    public void setAprmModifPaymentOUTPlan(Boolean aprmModifPaymentOUTPlan) {
        set(PROPERTY_APRMMODIFPAYMENTOUTPLAN, aprmModifPaymentOUTPlan);
    }

    public Date getExpectedDate() {
        return (Date) get(PROPERTY_EXPECTEDDATE);
    }

    public void setExpectedDate(Date expectedDate) {
        set(PROPERTY_EXPECTEDDATE, expectedDate);
    }

    public Long getDaysOverdue() {
        return (Long) get(PROPERTY_DAYSOVERDUE);
    }

    public void setDaysOverdue(Long daysOverdue) {
        set(PROPERTY_DAYSOVERDUE, daysOverdue);
    }

    public Date getLastPaymentDate() {
        return (Date) get(PROPERTY_LASTPAYMENTDATE);
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        set(PROPERTY_LASTPAYMENTDATE, lastPaymentDate);
    }

    public Long getNumberOfPayments() {
        return (Long) get(PROPERTY_NUMBEROFPAYMENTS);
    }

    public void setNumberOfPayments(Long numberOfPayments) {
        set(PROPERTY_NUMBEROFPAYMENTS, numberOfPayments);
    }

    public BigDecimal getTotalDebtAmount() {
        return (BigDecimal) get(PROPERTY_TOTALDEBTAMOUNT);
    }

    public void setTotalDebtAmount(BigDecimal totalDebtAmount) {
        set(PROPERTY_TOTALDEBTAMOUNT, totalDebtAmount);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebt> getFINDoubtfulDebtList() {
        return (List<DoubtfulDebt>) get(PROPERTY_FINDOUBTFULDEBTLIST);
    }

    public void setFINDoubtfulDebtList(List<DoubtfulDebt> fINDoubtfulDebtList) {
        set(PROPERTY_FINDOUBTFULDEBTLIST, fINDoubtfulDebtList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtV> getFINDoubtfulDebtVList() {
        return (List<DoubtfulDebtV>) get(PROPERTY_FINDOUBTFULDEBTVLIST);
    }

    public void setFINDoubtfulDebtVList(List<DoubtfulDebtV> fINDoubtfulDebtVList) {
        set(PROPERTY_FINDOUBTFULDEBTVLIST, fINDoubtfulDebtVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentDetailV> getFINPaymentDetailVPaymentPlanInvoiceList() {
        return (List<FIN_PaymentDetailV>) get(PROPERTY_FINPAYMENTDETAILVPAYMENTPLANINVOICELIST);
    }

    public void setFINPaymentDetailVPaymentPlanInvoiceList(List<FIN_PaymentDetailV> fINPaymentDetailVPaymentPlanInvoiceList) {
        set(PROPERTY_FINPAYMENTDETAILVPAYMENTPLANINVOICELIST, fINPaymentDetailVPaymentPlanInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentDetailV> getFINPaymentDetailVPaymentPlanOrderList() {
        return (List<FIN_PaymentDetailV>) get(PROPERTY_FINPAYMENTDETAILVPAYMENTPLANORDERLIST);
    }

    public void setFINPaymentDetailVPaymentPlanOrderList(List<FIN_PaymentDetailV> fINPaymentDetailVPaymentPlanOrderList) {
        set(PROPERTY_FINPAYMENTDETAILVPAYMENTPLANORDERLIST, fINPaymentDetailVPaymentPlanOrderList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentScheduleDetail> getFINPaymentScheduleDetailOrderPaymentScheduleList() {
        return (List<FIN_PaymentScheduleDetail>) get(PROPERTY_FINPAYMENTSCHEDULEDETAILORDERPAYMENTSCHEDULELIST);
    }

    public void setFINPaymentScheduleDetailOrderPaymentScheduleList(List<FIN_PaymentScheduleDetail> fINPaymentScheduleDetailOrderPaymentScheduleList) {
        set(PROPERTY_FINPAYMENTSCHEDULEDETAILORDERPAYMENTSCHEDULELIST, fINPaymentScheduleDetailOrderPaymentScheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentScheduleDetail> getFINPaymentScheduleDetailInvoicePaymentScheduleList() {
        return (List<FIN_PaymentScheduleDetail>) get(PROPERTY_FINPAYMENTSCHEDULEDETAILINVOICEPAYMENTSCHEDULELIST);
    }

    public void setFINPaymentScheduleDetailInvoicePaymentScheduleList(List<FIN_PaymentScheduleDetail> fINPaymentScheduleDetailInvoicePaymentScheduleList) {
        set(PROPERTY_FINPAYMENTSCHEDULEDETAILINVOICEPAYMENTSCHEDULELIST, fINPaymentScheduleDetailInvoicePaymentScheduleList);
    }

}
