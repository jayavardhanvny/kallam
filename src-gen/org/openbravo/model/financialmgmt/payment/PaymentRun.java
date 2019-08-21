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

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FinancialMgmtPaymentRun (stored in table FIN_Payment_Run).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PaymentRun extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Payment_Run";
    public static final String ENTITY_NAME = "FinancialMgmtPaymentRun";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_SOURCEOFTHEEXECUTION = "sourceOfTheExecution";
    public static final String PROPERTY_MESSAGE = "message";
    public static final String PROPERTY_PAYMENTEXECUTIONPROCESS = "paymentExecutionProcess";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTEXECUTIONHISTORYVLIST = "financialMgmtPaymentExecutionHistoryVList";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTRUNPARAMETERLIST = "financialMgmtPaymentRunParameterList";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTRUNPAYMENTLIST = "financialMgmtPaymentRunPaymentList";

    public PaymentRun() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SOURCEOFTHEEXECUTION, "OTHER");
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTEXECUTIONHISTORYVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTRUNPARAMETERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTRUNPAYMENTLIST, new ArrayList<Object>());
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

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public String getSourceOfTheExecution() {
        return (String) get(PROPERTY_SOURCEOFTHEEXECUTION);
    }

    public void setSourceOfTheExecution(String sourceOfTheExecution) {
        set(PROPERTY_SOURCEOFTHEEXECUTION, sourceOfTheExecution);
    }

    public String getMessage() {
        return (String) get(PROPERTY_MESSAGE);
    }

    public void setMessage(String message) {
        set(PROPERTY_MESSAGE, message);
    }

    public PaymentExecutionProcess getPaymentExecutionProcess() {
        return (PaymentExecutionProcess) get(PROPERTY_PAYMENTEXECUTIONPROCESS);
    }

    public void setPaymentExecutionProcess(PaymentExecutionProcess paymentExecutionProcess) {
        set(PROPERTY_PAYMENTEXECUTIONPROCESS, paymentExecutionProcess);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentExecutionHistoryV> getFinancialMgmtPaymentExecutionHistoryVList() {
        return (List<PaymentExecutionHistoryV>) get(PROPERTY_FINANCIALMGMTPAYMENTEXECUTIONHISTORYVLIST);
    }

    public void setFinancialMgmtPaymentExecutionHistoryVList(List<PaymentExecutionHistoryV> financialMgmtPaymentExecutionHistoryVList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTEXECUTIONHISTORYVLIST, financialMgmtPaymentExecutionHistoryVList);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentRunParameter> getFinancialMgmtPaymentRunParameterList() {
        return (List<PaymentRunParameter>) get(PROPERTY_FINANCIALMGMTPAYMENTRUNPARAMETERLIST);
    }

    public void setFinancialMgmtPaymentRunParameterList(List<PaymentRunParameter> financialMgmtPaymentRunParameterList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTRUNPARAMETERLIST, financialMgmtPaymentRunParameterList);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentRunPayment> getFinancialMgmtPaymentRunPaymentList() {
        return (List<PaymentRunPayment>) get(PROPERTY_FINANCIALMGMTPAYMENTRUNPAYMENTLIST);
    }

    public void setFinancialMgmtPaymentRunPaymentList(List<PaymentRunPayment> financialMgmtPaymentRunPaymentList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTRUNPAYMENTLIST, financialMgmtPaymentRunPaymentList);
    }

}
