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
 * Entity class for entity Fin_Orig_Payment_Schedule (stored in table Fin_Orig_Payment_Schedule).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Fin_OrigPaymentSchedule extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Fin_Orig_Payment_Schedule";
    public static final String ENTITY_NAME = "Fin_Orig_Payment_Schedule";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_DUEDATE = "dueDate";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_PAYMENTPRIORITY = "paymentPriority";
    public static final String PROPERTY_UPDATEPAYMENTPLAN = "updatePaymentPlan";
    public static final String PROPERTY_FINORIGPAYMENTSCHEDULEDETAILLIST = "fINOrigPaymentScheduleDetailList";

    public Fin_OrigPaymentSchedule() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_UPDATEPAYMENTPLAN, true);
        setDefaultValue(PROPERTY_FINORIGPAYMENTSCHEDULEDETAILLIST, new ArrayList<Object>());
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

    public Order getSalesOrder() {
        return (Order) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Order salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public Date getDueDate() {
        return (Date) get(PROPERTY_DUEDATE);
    }

    public void setDueDate(Date dueDate) {
        set(PROPERTY_DUEDATE, dueDate);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public PaymentPriority getPaymentPriority() {
        return (PaymentPriority) get(PROPERTY_PAYMENTPRIORITY);
    }

    public void setPaymentPriority(PaymentPriority paymentPriority) {
        set(PROPERTY_PAYMENTPRIORITY, paymentPriority);
    }

    public Boolean isUpdatePaymentPlan() {
        return (Boolean) get(PROPERTY_UPDATEPAYMENTPLAN);
    }

    public void setUpdatePaymentPlan(Boolean updatePaymentPlan) {
        set(PROPERTY_UPDATEPAYMENTPLAN, updatePaymentPlan);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_OrigPaymentScheduleDetail> getFINOrigPaymentScheduleDetailList() {
        return (List<FIN_OrigPaymentScheduleDetail>) get(PROPERTY_FINORIGPAYMENTSCHEDULEDETAILLIST);
    }

    public void setFINOrigPaymentScheduleDetailList(List<FIN_OrigPaymentScheduleDetail> fINOrigPaymentScheduleDetailList) {
        set(PROPERTY_FINORIGPAYMENTSCHEDULEDETAILLIST, fINOrigPaymentScheduleDetailList);
    }

}
