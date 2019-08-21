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
 * Entity class for entity FIN_ReconciliationLineTemp (stored in table FIN_RecLine_Temp).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_ReconciliationLineTemp extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_RecLine_Temp";
    public static final String ENTITY_NAME = "FIN_ReconciliationLineTemp";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_RECONCILIATION = "reconciliation";
    public static final String PROPERTY_BANKSTATEMENTLINE = "bankStatementLine";
    public static final String PROPERTY_FINANCIALACCOUNTTRANSACTION = "financialAccountTransaction";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_PAYMENTSCHEDULEDETAIL = "paymentScheduleDetail";
    public static final String PROPERTY_MATCHED = "matched";
    public static final String PROPERTY_MATCHLEVEL = "matchlevel";
    public static final String PROPERTY_PAYMENTDOCUMENTNO = "paymentDocumentno";

    public FIN_ReconciliationLineTemp() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MATCHED, false);
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

    public FIN_Reconciliation getReconciliation() {
        return (FIN_Reconciliation) get(PROPERTY_RECONCILIATION);
    }

    public void setReconciliation(FIN_Reconciliation reconciliation) {
        set(PROPERTY_RECONCILIATION, reconciliation);
    }

    public FIN_BankStatementLine getBankStatementLine() {
        return (FIN_BankStatementLine) get(PROPERTY_BANKSTATEMENTLINE);
    }

    public void setBankStatementLine(FIN_BankStatementLine bankStatementLine) {
        set(PROPERTY_BANKSTATEMENTLINE, bankStatementLine);
    }

    public FIN_FinaccTransaction getFinancialAccountTransaction() {
        return (FIN_FinaccTransaction) get(PROPERTY_FINANCIALACCOUNTTRANSACTION);
    }

    public void setFinancialAccountTransaction(FIN_FinaccTransaction financialAccountTransaction) {
        set(PROPERTY_FINANCIALACCOUNTTRANSACTION, financialAccountTransaction);
    }

    public FIN_Payment getPayment() {
        return (FIN_Payment) get(PROPERTY_PAYMENT);
    }

    public void setPayment(FIN_Payment payment) {
        set(PROPERTY_PAYMENT, payment);
    }

    public FIN_PaymentScheduleDetail getPaymentScheduleDetail() {
        return (FIN_PaymentScheduleDetail) get(PROPERTY_PAYMENTSCHEDULEDETAIL);
    }

    public void setPaymentScheduleDetail(FIN_PaymentScheduleDetail paymentScheduleDetail) {
        set(PROPERTY_PAYMENTSCHEDULEDETAIL, paymentScheduleDetail);
    }

    public Boolean isMatched() {
        return (Boolean) get(PROPERTY_MATCHED);
    }

    public void setMatched(Boolean matched) {
        set(PROPERTY_MATCHED, matched);
    }

    public String getMatchlevel() {
        return (String) get(PROPERTY_MATCHLEVEL);
    }

    public void setMatchlevel(String matchlevel) {
        set(PROPERTY_MATCHLEVEL, matchlevel);
    }

    public String getPaymentDocumentno() {
        return (String) get(PROPERTY_PAYMENTDOCUMENTNO);
    }

    public void setPaymentDocumentno(String paymentDocumentno) {
        set(PROPERTY_PAYMENTDOCUMENTNO, paymentDocumentno);
    }

}
