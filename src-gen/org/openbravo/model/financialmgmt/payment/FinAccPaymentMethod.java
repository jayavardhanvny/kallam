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
 * Entity class for entity FinancialMgmtFinAccPaymentMethod (stored in table FIN_FinAcc_PaymentMethod).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FinAccPaymentMethod extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_FinAcc_PaymentMethod";
    public static final String ENTITY_NAME = "FinancialMgmtFinAccPaymentMethod";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_AUTOMATICRECEIPT = "automaticReceipt";
    public static final String PROPERTY_AUTOMATICPAYMENT = "automaticPayment";
    public static final String PROPERTY_AUTOMATICDEPOSIT = "automaticDeposit";
    public static final String PROPERTY_AUTOMATICWITHDRAWN = "automaticWithdrawn";
    public static final String PROPERTY_PAYINALLOW = "payinAllow";
    public static final String PROPERTY_PAYOUTALLOW = "payoutAllow";
    public static final String PROPERTY_PAYINEXECUTIONTYPE = "payinExecutionType";
    public static final String PROPERTY_PAYOUTEXECUTIONTYPE = "payoutExecutionType";
    public static final String PROPERTY_PAYINEXECUTIONPROCESS = "payinExecutionProcess";
    public static final String PROPERTY_PAYOUTEXECUTIONPROCESS = "payoutExecutionProcess";
    public static final String PROPERTY_PAYINDEFERRED = "payinDeferred";
    public static final String PROPERTY_PAYOUTDEFERRED = "payoutDeferred";
    public static final String PROPERTY_UPONRECEIPTUSE = "uponReceiptUse";
    public static final String PROPERTY_UPONDEPOSITUSE = "uponDepositUse";
    public static final String PROPERTY_INUPONCLEARINGUSE = "iNUponClearingUse";
    public static final String PROPERTY_UPONPAYMENTUSE = "uponPaymentUse";
    public static final String PROPERTY_UPONWITHDRAWALUSE = "uponWithdrawalUse";
    public static final String PROPERTY_OUTUPONCLEARINGUSE = "oUTUponClearingUse";
    public static final String PROPERTY_PAYINISMULTICURRENCY = "payinIsMulticurrency";
    public static final String PROPERTY_PAYOUTISMULTICURRENCY = "payoutIsMulticurrency";
    public static final String PROPERTY_DEFAULT = "default";

    public FinAccPaymentMethod() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_AUTOMATICRECEIPT, false);
        setDefaultValue(PROPERTY_AUTOMATICPAYMENT, false);
        setDefaultValue(PROPERTY_AUTOMATICDEPOSIT, false);
        setDefaultValue(PROPERTY_AUTOMATICWITHDRAWN, false);
        setDefaultValue(PROPERTY_PAYINALLOW, true);
        setDefaultValue(PROPERTY_PAYOUTALLOW, true);
        setDefaultValue(PROPERTY_PAYINEXECUTIONTYPE, "M");
        setDefaultValue(PROPERTY_PAYOUTEXECUTIONTYPE, "M");
        setDefaultValue(PROPERTY_PAYINDEFERRED, false);
        setDefaultValue(PROPERTY_PAYOUTDEFERRED, false);
        setDefaultValue(PROPERTY_PAYINISMULTICURRENCY, false);
        setDefaultValue(PROPERTY_PAYOUTISMULTICURRENCY, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
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

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public FIN_FinancialAccount getAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(FIN_FinancialAccount account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public Boolean isAutomaticReceipt() {
        return (Boolean) get(PROPERTY_AUTOMATICRECEIPT);
    }

    public void setAutomaticReceipt(Boolean automaticReceipt) {
        set(PROPERTY_AUTOMATICRECEIPT, automaticReceipt);
    }

    public Boolean isAutomaticPayment() {
        return (Boolean) get(PROPERTY_AUTOMATICPAYMENT);
    }

    public void setAutomaticPayment(Boolean automaticPayment) {
        set(PROPERTY_AUTOMATICPAYMENT, automaticPayment);
    }

    public Boolean isAutomaticDeposit() {
        return (Boolean) get(PROPERTY_AUTOMATICDEPOSIT);
    }

    public void setAutomaticDeposit(Boolean automaticDeposit) {
        set(PROPERTY_AUTOMATICDEPOSIT, automaticDeposit);
    }

    public Boolean isAutomaticWithdrawn() {
        return (Boolean) get(PROPERTY_AUTOMATICWITHDRAWN);
    }

    public void setAutomaticWithdrawn(Boolean automaticWithdrawn) {
        set(PROPERTY_AUTOMATICWITHDRAWN, automaticWithdrawn);
    }

    public Boolean isPayinAllow() {
        return (Boolean) get(PROPERTY_PAYINALLOW);
    }

    public void setPayinAllow(Boolean payinAllow) {
        set(PROPERTY_PAYINALLOW, payinAllow);
    }

    public Boolean isPayoutAllow() {
        return (Boolean) get(PROPERTY_PAYOUTALLOW);
    }

    public void setPayoutAllow(Boolean payoutAllow) {
        set(PROPERTY_PAYOUTALLOW, payoutAllow);
    }

    public String getPayinExecutionType() {
        return (String) get(PROPERTY_PAYINEXECUTIONTYPE);
    }

    public void setPayinExecutionType(String payinExecutionType) {
        set(PROPERTY_PAYINEXECUTIONTYPE, payinExecutionType);
    }

    public String getPayoutExecutionType() {
        return (String) get(PROPERTY_PAYOUTEXECUTIONTYPE);
    }

    public void setPayoutExecutionType(String payoutExecutionType) {
        set(PROPERTY_PAYOUTEXECUTIONTYPE, payoutExecutionType);
    }

    public PaymentExecutionProcess getPayinExecutionProcess() {
        return (PaymentExecutionProcess) get(PROPERTY_PAYINEXECUTIONPROCESS);
    }

    public void setPayinExecutionProcess(PaymentExecutionProcess payinExecutionProcess) {
        set(PROPERTY_PAYINEXECUTIONPROCESS, payinExecutionProcess);
    }

    public PaymentExecutionProcess getPayoutExecutionProcess() {
        return (PaymentExecutionProcess) get(PROPERTY_PAYOUTEXECUTIONPROCESS);
    }

    public void setPayoutExecutionProcess(PaymentExecutionProcess payoutExecutionProcess) {
        set(PROPERTY_PAYOUTEXECUTIONPROCESS, payoutExecutionProcess);
    }

    public Boolean isPayinDeferred() {
        return (Boolean) get(PROPERTY_PAYINDEFERRED);
    }

    public void setPayinDeferred(Boolean payinDeferred) {
        set(PROPERTY_PAYINDEFERRED, payinDeferred);
    }

    public Boolean isPayoutDeferred() {
        return (Boolean) get(PROPERTY_PAYOUTDEFERRED);
    }

    public void setPayoutDeferred(Boolean payoutDeferred) {
        set(PROPERTY_PAYOUTDEFERRED, payoutDeferred);
    }

    public String getUponReceiptUse() {
        return (String) get(PROPERTY_UPONRECEIPTUSE);
    }

    public void setUponReceiptUse(String uponReceiptUse) {
        set(PROPERTY_UPONRECEIPTUSE, uponReceiptUse);
    }

    public String getUponDepositUse() {
        return (String) get(PROPERTY_UPONDEPOSITUSE);
    }

    public void setUponDepositUse(String uponDepositUse) {
        set(PROPERTY_UPONDEPOSITUSE, uponDepositUse);
    }

    public String getINUponClearingUse() {
        return (String) get(PROPERTY_INUPONCLEARINGUSE);
    }

    public void setINUponClearingUse(String iNUponClearingUse) {
        set(PROPERTY_INUPONCLEARINGUSE, iNUponClearingUse);
    }

    public String getUponPaymentUse() {
        return (String) get(PROPERTY_UPONPAYMENTUSE);
    }

    public void setUponPaymentUse(String uponPaymentUse) {
        set(PROPERTY_UPONPAYMENTUSE, uponPaymentUse);
    }

    public String getUponWithdrawalUse() {
        return (String) get(PROPERTY_UPONWITHDRAWALUSE);
    }

    public void setUponWithdrawalUse(String uponWithdrawalUse) {
        set(PROPERTY_UPONWITHDRAWALUSE, uponWithdrawalUse);
    }

    public String getOUTUponClearingUse() {
        return (String) get(PROPERTY_OUTUPONCLEARINGUSE);
    }

    public void setOUTUponClearingUse(String oUTUponClearingUse) {
        set(PROPERTY_OUTUPONCLEARINGUSE, oUTUponClearingUse);
    }

    public Boolean isPayinIsMulticurrency() {
        return (Boolean) get(PROPERTY_PAYINISMULTICURRENCY);
    }

    public void setPayinIsMulticurrency(Boolean payinIsMulticurrency) {
        set(PROPERTY_PAYINISMULTICURRENCY, payinIsMulticurrency);
    }

    public Boolean isPayoutIsMulticurrency() {
        return (Boolean) get(PROPERTY_PAYOUTISMULTICURRENCY);
    }

    public void setPayoutIsMulticurrency(Boolean payoutIsMulticurrency) {
        set(PROPERTY_PAYOUTISMULTICURRENCY, payoutIsMulticurrency);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

}
