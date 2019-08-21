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

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FinancialMgmtPaymentExecutionHistoryV (stored in table FIN_Payment_Exec_History_V).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PaymentExecutionHistoryV extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Payment_Exec_History_V";
    public static final String ENTITY_NAME = "FinancialMgmtPaymentExecutionHistoryV";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_EXECUTIONDATE = "executionDate";
    public static final String PROPERTY_PAYMENTRUN = "paymentRun";
    public static final String PROPERTY_PAYMENTRUNMESSAGE = "paymentRunMessage";
    public static final String PROPERTY_PAYMENTRUNSTATUS = "paymentRunStatus";
    public static final String PROPERTY_SOURCEOFTHEEXECUTION = "sourceOfTheExecution";
    public static final String PROPERTY_PAYMENTEXECUTIONRESULT = "paymentExecutionResult";
    public static final String PROPERTY_PAYMENTEXECUTIONMESSAGE = "paymentExecutionMessage";

    public PaymentExecutionHistoryV() {
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

    public FIN_Payment getPayment() {
        return (FIN_Payment) get(PROPERTY_PAYMENT);
    }

    public void setPayment(FIN_Payment payment) {
        set(PROPERTY_PAYMENT, payment);
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

    public Date getExecutionDate() {
        return (Date) get(PROPERTY_EXECUTIONDATE);
    }

    public void setExecutionDate(Date executionDate) {
        set(PROPERTY_EXECUTIONDATE, executionDate);
    }

    public PaymentRun getPaymentRun() {
        return (PaymentRun) get(PROPERTY_PAYMENTRUN);
    }

    public void setPaymentRun(PaymentRun paymentRun) {
        set(PROPERTY_PAYMENTRUN, paymentRun);
    }

    public String getPaymentRunMessage() {
        return (String) get(PROPERTY_PAYMENTRUNMESSAGE);
    }

    public void setPaymentRunMessage(String paymentRunMessage) {
        set(PROPERTY_PAYMENTRUNMESSAGE, paymentRunMessage);
    }

    public String getPaymentRunStatus() {
        return (String) get(PROPERTY_PAYMENTRUNSTATUS);
    }

    public void setPaymentRunStatus(String paymentRunStatus) {
        set(PROPERTY_PAYMENTRUNSTATUS, paymentRunStatus);
    }

    public String getSourceOfTheExecution() {
        return (String) get(PROPERTY_SOURCEOFTHEEXECUTION);
    }

    public void setSourceOfTheExecution(String sourceOfTheExecution) {
        set(PROPERTY_SOURCEOFTHEEXECUTION, sourceOfTheExecution);
    }

    public String getPaymentExecutionResult() {
        return (String) get(PROPERTY_PAYMENTEXECUTIONRESULT);
    }

    public void setPaymentExecutionResult(String paymentExecutionResult) {
        set(PROPERTY_PAYMENTEXECUTIONRESULT, paymentExecutionResult);
    }

    public String getPaymentExecutionMessage() {
        return (String) get(PROPERTY_PAYMENTEXECUTIONMESSAGE);
    }

    public void setPaymentExecutionMessage(String paymentExecutionMessage) {
        set(PROPERTY_PAYMENTEXECUTIONMESSAGE, paymentExecutionMessage);
    }

}
