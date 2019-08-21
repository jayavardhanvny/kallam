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
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_Reconciliation;
/**
 * Entity class for entity APRM_Reconciliation (stored in table APRM_Reconciliation_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class APRM_Reconciliation_v extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "APRM_Reconciliation_v";
    public static final String ENTITY_NAME = "APRM_Reconciliation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_RECONCILIATION = "reconciliation";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_TRANSACTIONDATE = "transactionDate";
    public static final String PROPERTY_ENDINGBALANCE = "endingBalance";
    public static final String PROPERTY_STARTINGBALANCE = "startingBalance";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_PRINTDETAILED = "printDetailed";
    public static final String PROPERTY_PRINTSUMMARY = "printSummary";
    public static final String PROPERTY_PROCESSRECONCILIATION = "processReconciliation";
    public static final String PROPERTY_ITEMNO = "itemNo";
    public static final String PROPERTY_ITEMAMT = "itemAmt";
    public static final String PROPERTY_UNRECNO = "unrecNo";
    public static final String PROPERTY_UNRECAMT = "unrecAmt";
    public static final String PROPERTY_PAYMENTNO = "paymentNo";
    public static final String PROPERTY_PAYMENTAMT = "paymentAmt";
    public static final String PROPERTY_DEPOSITNO = "depositNo";
    public static final String PROPERTY_DEPOSITAMT = "depositAmt";
    public static final String PROPERTY_FORCEDTABLE = "forcedTable";

    public APRM_Reconciliation_v() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PRINTDETAILED, false);
        setDefaultValue(PROPERTY_PRINTSUMMARY, false);
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

    public FIN_Reconciliation getReconciliation() {
        return (FIN_Reconciliation) get(PROPERTY_RECONCILIATION);
    }

    public void setReconciliation(FIN_Reconciliation reconciliation) {
        set(PROPERTY_RECONCILIATION, reconciliation);
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

    public FIN_FinancialAccount getAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(FIN_FinancialAccount account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public Date getTransactionDate() {
        return (Date) get(PROPERTY_TRANSACTIONDATE);
    }

    public void setTransactionDate(Date transactionDate) {
        set(PROPERTY_TRANSACTIONDATE, transactionDate);
    }

    public BigDecimal getEndingBalance() {
        return (BigDecimal) get(PROPERTY_ENDINGBALANCE);
    }

    public void setEndingBalance(BigDecimal endingBalance) {
        set(PROPERTY_ENDINGBALANCE, endingBalance);
    }

    public BigDecimal getStartingBalance() {
        return (BigDecimal) get(PROPERTY_STARTINGBALANCE);
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        set(PROPERTY_STARTINGBALANCE, startingBalance);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
    }

    public Boolean isPrintDetailed() {
        return (Boolean) get(PROPERTY_PRINTDETAILED);
    }

    public void setPrintDetailed(Boolean printDetailed) {
        set(PROPERTY_PRINTDETAILED, printDetailed);
    }

    public Boolean isPrintSummary() {
        return (Boolean) get(PROPERTY_PRINTSUMMARY);
    }

    public void setPrintSummary(Boolean printSummary) {
        set(PROPERTY_PRINTSUMMARY, printSummary);
    }

    public String getProcessReconciliation() {
        return (String) get(PROPERTY_PROCESSRECONCILIATION);
    }

    public void setProcessReconciliation(String processReconciliation) {
        set(PROPERTY_PROCESSRECONCILIATION, processReconciliation);
    }

    public Long getItemNo() {
        return (Long) get(PROPERTY_ITEMNO);
    }

    public void setItemNo(Long itemNo) {
        set(PROPERTY_ITEMNO, itemNo);
    }

    public BigDecimal getItemAmt() {
        return (BigDecimal) get(PROPERTY_ITEMAMT);
    }

    public void setItemAmt(BigDecimal itemAmt) {
        set(PROPERTY_ITEMAMT, itemAmt);
    }

    public Long getUnrecNo() {
        return (Long) get(PROPERTY_UNRECNO);
    }

    public void setUnrecNo(Long unrecNo) {
        set(PROPERTY_UNRECNO, unrecNo);
    }

    public BigDecimal getUnrecAmt() {
        return (BigDecimal) get(PROPERTY_UNRECAMT);
    }

    public void setUnrecAmt(BigDecimal unrecAmt) {
        set(PROPERTY_UNRECAMT, unrecAmt);
    }

    public Long getPaymentNo() {
        return (Long) get(PROPERTY_PAYMENTNO);
    }

    public void setPaymentNo(Long paymentNo) {
        set(PROPERTY_PAYMENTNO, paymentNo);
    }

    public BigDecimal getPaymentAmt() {
        return (BigDecimal) get(PROPERTY_PAYMENTAMT);
    }

    public void setPaymentAmt(BigDecimal paymentAmt) {
        set(PROPERTY_PAYMENTAMT, paymentAmt);
    }

    public Long getDepositNo() {
        return (Long) get(PROPERTY_DEPOSITNO);
    }

    public void setDepositNo(Long depositNo) {
        set(PROPERTY_DEPOSITNO, depositNo);
    }

    public BigDecimal getDepositAmt() {
        return (BigDecimal) get(PROPERTY_DEPOSITAMT);
    }

    public void setDepositAmt(BigDecimal depositAmt) {
        set(PROPERTY_DEPOSITAMT, depositAmt);
    }

    public Table getForcedTable() {
        return (Table) get(PROPERTY_FORCEDTABLE);
    }

    public void setForcedTable(Table forcedTable) {
        set(PROPERTY_FORCEDTABLE, forcedTable);
    }

}
