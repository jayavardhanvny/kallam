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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.order.Order;
/**
 * Entity class for entity FIN_Payment_Priority (stored in table FIN_Payment_Priority).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PaymentPriority extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Payment_Priority";
    public static final String ENTITY_NAME = "FIN_Payment_Priority";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_PRIORITY = "priority";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_COLOR = "color";
    public static final String PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST = "aPRMPaymentProposalPickEditList";
    public static final String PROPERTY_FINPAYMENTSCHEDINVVLIST = "fINPaymentSchedInvVList";
    public static final String PROPERTY_FINPAYMENTSCHEDORDVLIST = "fINPaymentSchedOrdVList";
    public static final String PROPERTY_FINPAYMENTSCHEDULELIST = "fINPaymentScheduleList";
    public static final String PROPERTY_FINORIGPAYMENTSCHEDULELIST = "finOrigPaymentScheduleList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_ORDERLIST = "orderList";

    public PaymentPriority() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDINVVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDORDVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINORIGPAYMENTSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Long getPriority() {
        return (Long) get(PROPERTY_PRIORITY);
    }

    public void setPriority(Long priority) {
        set(PROPERTY_PRIORITY, priority);
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

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public String getColor() {
        return (String) get(PROPERTY_COLOR);
    }

    public void setColor(String color) {
        set(PROPERTY_COLOR, color);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_PaymentProposalPickEdit> getAPRMPaymentProposalPickEditList() {
        return (List<APRM_PaymentProposalPickEdit>) get(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST);
    }

    public void setAPRMPaymentProposalPickEditList(List<APRM_PaymentProposalPickEdit> aPRMPaymentProposalPickEditList) {
        set(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, aPRMPaymentProposalPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedInvV> getFINPaymentSchedInvVList() {
        return (List<FIN_PaymentSchedInvV>) get(PROPERTY_FINPAYMENTSCHEDINVVLIST);
    }

    public void setFINPaymentSchedInvVList(List<FIN_PaymentSchedInvV> fINPaymentSchedInvVList) {
        set(PROPERTY_FINPAYMENTSCHEDINVVLIST, fINPaymentSchedInvVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedOrdV> getFINPaymentSchedOrdVList() {
        return (List<FIN_PaymentSchedOrdV>) get(PROPERTY_FINPAYMENTSCHEDORDVLIST);
    }

    public void setFINPaymentSchedOrdVList(List<FIN_PaymentSchedOrdV> fINPaymentSchedOrdVList) {
        set(PROPERTY_FINPAYMENTSCHEDORDVLIST, fINPaymentSchedOrdVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedule> getFINPaymentScheduleList() {
        return (List<FIN_PaymentSchedule>) get(PROPERTY_FINPAYMENTSCHEDULELIST);
    }

    public void setFINPaymentScheduleList(List<FIN_PaymentSchedule> fINPaymentScheduleList) {
        set(PROPERTY_FINPAYMENTSCHEDULELIST, fINPaymentScheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<Fin_OrigPaymentSchedule> getFinOrigPaymentScheduleList() {
        return (List<Fin_OrigPaymentSchedule>) get(PROPERTY_FINORIGPAYMENTSCHEDULELIST);
    }

    public void setFinOrigPaymentScheduleList(List<Fin_OrigPaymentSchedule> finOrigPaymentScheduleList) {
        set(PROPERTY_FINORIGPAYMENTSCHEDULELIST, finOrigPaymentScheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceList() {
        return (List<Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderList() {
        return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

}
