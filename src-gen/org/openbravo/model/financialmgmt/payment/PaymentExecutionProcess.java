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

import org.openbravo.advpaymentmngt.APRMPendingPaymentFromInvoice;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FinancialMgmtPaymentExecutionProcess (stored in table FIN_Pay_Exec_Process).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PaymentExecutionProcess extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Pay_Exec_Process";
    public static final String ENTITY_NAME = "FinancialMgmtPaymentExecutionProcess";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_JAVACLASSNAME = "javaClassName";
    public static final String PROPERTY_APRMPENDINGPAYMENTINVOICELIST = "aPRMPendingPaymentInvoiceList";
    public static final String PROPERTY_FINPAYMENTMETHODPAYINEXECUTIONPROCESSIDLIST = "fINPaymentMethodPayinExecutionProcessIDList";
    public static final String PROPERTY_FINPAYMENTMETHODPAYOUTEXECUTIONPROCESSIDLIST = "fINPaymentMethodPayoutExecutionProcessIDList";
    public static final String PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODPAYINEXECUTIONPROCESSIDLIST = "financialMgmtFinAccPaymentMethodPayinExecutionProcessIDList";
    public static final String PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODPAYOUTEXECUTIONPROCESSIDLIST = "financialMgmtFinAccPaymentMethodPayoutExecutionProcessIDList";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTEXECUTIONPROCESSPARAMETERLIST = "financialMgmtPaymentExecutionProcessParameterList";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTRUNLIST = "financialMgmtPaymentRunList";

    public PaymentExecutionProcess() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_APRMPENDINGPAYMENTINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTMETHODPAYINEXECUTIONPROCESSIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTMETHODPAYOUTEXECUTIONPROCESSIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODPAYINEXECUTIONPROCESSIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODPAYOUTEXECUTIONPROCESSIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTEXECUTIONPROCESSPARAMETERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTRUNLIST, new ArrayList<Object>());
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

    public String getJavaClassName() {
        return (String) get(PROPERTY_JAVACLASSNAME);
    }

    public void setJavaClassName(String javaClassName) {
        set(PROPERTY_JAVACLASSNAME, javaClassName);
    }

    @SuppressWarnings("unchecked")
    public List<APRMPendingPaymentFromInvoice> getAPRMPendingPaymentInvoiceList() {
        return (List<APRMPendingPaymentFromInvoice>) get(PROPERTY_APRMPENDINGPAYMENTINVOICELIST);
    }

    public void setAPRMPendingPaymentInvoiceList(List<APRMPendingPaymentFromInvoice> aPRMPendingPaymentInvoiceList) {
        set(PROPERTY_APRMPENDINGPAYMENTINVOICELIST, aPRMPendingPaymentInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentMethod> getFINPaymentMethodPayinExecutionProcessIDList() {
        return (List<FIN_PaymentMethod>) get(PROPERTY_FINPAYMENTMETHODPAYINEXECUTIONPROCESSIDLIST);
    }

    public void setFINPaymentMethodPayinExecutionProcessIDList(List<FIN_PaymentMethod> fINPaymentMethodPayinExecutionProcessIDList) {
        set(PROPERTY_FINPAYMENTMETHODPAYINEXECUTIONPROCESSIDLIST, fINPaymentMethodPayinExecutionProcessIDList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentMethod> getFINPaymentMethodPayoutExecutionProcessIDList() {
        return (List<FIN_PaymentMethod>) get(PROPERTY_FINPAYMENTMETHODPAYOUTEXECUTIONPROCESSIDLIST);
    }

    public void setFINPaymentMethodPayoutExecutionProcessIDList(List<FIN_PaymentMethod> fINPaymentMethodPayoutExecutionProcessIDList) {
        set(PROPERTY_FINPAYMENTMETHODPAYOUTEXECUTIONPROCESSIDLIST, fINPaymentMethodPayoutExecutionProcessIDList);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccPaymentMethod> getFinancialMgmtFinAccPaymentMethodPayinExecutionProcessIDList() {
        return (List<FinAccPaymentMethod>) get(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODPAYINEXECUTIONPROCESSIDLIST);
    }

    public void setFinancialMgmtFinAccPaymentMethodPayinExecutionProcessIDList(List<FinAccPaymentMethod> financialMgmtFinAccPaymentMethodPayinExecutionProcessIDList) {
        set(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODPAYINEXECUTIONPROCESSIDLIST, financialMgmtFinAccPaymentMethodPayinExecutionProcessIDList);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccPaymentMethod> getFinancialMgmtFinAccPaymentMethodPayoutExecutionProcessIDList() {
        return (List<FinAccPaymentMethod>) get(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODPAYOUTEXECUTIONPROCESSIDLIST);
    }

    public void setFinancialMgmtFinAccPaymentMethodPayoutExecutionProcessIDList(List<FinAccPaymentMethod> financialMgmtFinAccPaymentMethodPayoutExecutionProcessIDList) {
        set(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODPAYOUTEXECUTIONPROCESSIDLIST, financialMgmtFinAccPaymentMethodPayoutExecutionProcessIDList);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentExecutionProcessParameter> getFinancialMgmtPaymentExecutionProcessParameterList() {
        return (List<PaymentExecutionProcessParameter>) get(PROPERTY_FINANCIALMGMTPAYMENTEXECUTIONPROCESSPARAMETERLIST);
    }

    public void setFinancialMgmtPaymentExecutionProcessParameterList(List<PaymentExecutionProcessParameter> financialMgmtPaymentExecutionProcessParameterList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTEXECUTIONPROCESSPARAMETERLIST, financialMgmtPaymentExecutionProcessParameterList);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentRun> getFinancialMgmtPaymentRunList() {
        return (List<PaymentRun>) get(PROPERTY_FINANCIALMGMTPAYMENTRUNLIST);
    }

    public void setFinancialMgmtPaymentRunList(List<PaymentRun> financialMgmtPaymentRunList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTRUNLIST, financialMgmtPaymentRunList);
    }

}
