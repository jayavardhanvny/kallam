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
package org.openbravo.model.financialmgmt.calendar;

import com.rcss.humanresource.data.RCHRPiecemaster;
import com.rcss.humanresource.data.RchrRollnomonthlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.gl.GLBatch;
import org.openbravo.model.financialmgmt.gl.GLJournal;
/**
 * Entity class for entity FinancialMgmtPeriod (stored in table C_Period).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Period extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Period";
    public static final String ENTITY_NAME = "FinancialMgmtPeriod";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PERIODNO = "periodNo";
    public static final String PROPERTY_YEAR = "year";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_PERIODTYPE = "periodType";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_CLOSINGFACTACCTGROUP = "closingFactAcctGroup";
    public static final String PROPERTY_REGULARIZATIONFACTACCTGROUP = "regularizationFactAcctGroup";
    public static final String PROPERTY_DIVIDEUPFACTACCTGROUP = "divideupFactAcctGroup";
    public static final String PROPERTY_OPENFACTACCTGROUP = "openFactAcctGroup";
    public static final String PROPERTY_OPENCLOSE = "openClose";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMALIST = "financialMgmtAcctSchemaList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_FINANCIALMGMTGLBATCHLIST = "financialMgmtGLBatchList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTPERIODCONTROLLIST = "financialMgmtPeriodControlList";
    public static final String PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST = "financialMgmtPeriodControlVList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_PERIODCONTROLLOGPERIODNOLIST = "periodControlLogPeriodNoList";
    public static final String PROPERTY_PERIODCONTROLLOGLIST = "periodControlLogList";
    public static final String PROPERTY_RCHRPIECEMASTERLIST = "rCHRPiecemasterList";
    public static final String PROPERTY_RCHRROLLNOMONTHLISTLIST = "rchrRollnomonthlistList";

    public Period() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PERIODTYPE, "S");
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_OPENCLOSE, "O");
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLBATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPERIODCONTROLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PERIODCONTROLLOGPERIODNOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PERIODCONTROLLOGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRPIECEMASTERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROLLNOMONTHLISTLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Long getPeriodNo() {
        return (Long) get(PROPERTY_PERIODNO);
    }

    public void setPeriodNo(Long periodNo) {
        set(PROPERTY_PERIODNO, periodNo);
    }

    public Year getYear() {
        return (Year) get(PROPERTY_YEAR);
    }

    public void setYear(Year year) {
        set(PROPERTY_YEAR, year);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public String getPeriodType() {
        return (String) get(PROPERTY_PERIODTYPE);
    }

    public void setPeriodType(String periodType) {
        set(PROPERTY_PERIODTYPE, periodType);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public String getClosingFactAcctGroup() {
        return (String) get(PROPERTY_CLOSINGFACTACCTGROUP);
    }

    public void setClosingFactAcctGroup(String closingFactAcctGroup) {
        set(PROPERTY_CLOSINGFACTACCTGROUP, closingFactAcctGroup);
    }

    public String getRegularizationFactAcctGroup() {
        return (String) get(PROPERTY_REGULARIZATIONFACTACCTGROUP);
    }

    public void setRegularizationFactAcctGroup(String regularizationFactAcctGroup) {
        set(PROPERTY_REGULARIZATIONFACTACCTGROUP, regularizationFactAcctGroup);
    }

    public String getDivideupFactAcctGroup() {
        return (String) get(PROPERTY_DIVIDEUPFACTACCTGROUP);
    }

    public void setDivideupFactAcctGroup(String divideupFactAcctGroup) {
        set(PROPERTY_DIVIDEUPFACTACCTGROUP, divideupFactAcctGroup);
    }

    public String getOpenFactAcctGroup() {
        return (String) get(PROPERTY_OPENFACTACCTGROUP);
    }

    public void setOpenFactAcctGroup(String openFactAcctGroup) {
        set(PROPERTY_OPENFACTACCTGROUP, openFactAcctGroup);
    }

    public String getOpenClose() {
        return (String) get(PROPERTY_OPENCLOSE);
    }

    public void setOpenClose(String openClose) {
        set(PROPERTY_OPENCLOSE, openClose);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchema> getFinancialMgmtAcctSchemaList() {
        return (List<AcctSchema>) get(PROPERTY_FINANCIALMGMTACCTSCHEMALIST);
    }

    public void setFinancialMgmtAcctSchemaList(List<AcctSchema> financialMgmtAcctSchemaList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMALIST, financialMgmtAcctSchemaList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getFinancialMgmtBudgetLineList() {
        return (List<BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<GLBatch> getFinancialMgmtGLBatchList() {
        return (List<GLBatch>) get(PROPERTY_FINANCIALMGMTGLBATCHLIST);
    }

    public void setFinancialMgmtGLBatchList(List<GLBatch> financialMgmtGLBatchList) {
        set(PROPERTY_FINANCIALMGMTGLBATCHLIST, financialMgmtGLBatchList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getFinancialMgmtGLJournalList() {
        return (List<GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControl> getFinancialMgmtPeriodControlList() {
        return (List<PeriodControl>) get(PROPERTY_FINANCIALMGMTPERIODCONTROLLIST);
    }

    public void setFinancialMgmtPeriodControlList(List<PeriodControl> financialMgmtPeriodControlList) {
        set(PROPERTY_FINANCIALMGMTPERIODCONTROLLIST, financialMgmtPeriodControlList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlV> getFinancialMgmtPeriodControlVList() {
        return (List<PeriodControlV>) get(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST);
    }

    public void setFinancialMgmtPeriodControlVList(List<PeriodControlV> financialMgmtPeriodControlVList) {
        set(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST, financialMgmtPeriodControlVList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlLog> getPeriodControlLogPeriodNoList() {
        return (List<PeriodControlLog>) get(PROPERTY_PERIODCONTROLLOGPERIODNOLIST);
    }

    public void setPeriodControlLogPeriodNoList(List<PeriodControlLog> periodControlLogPeriodNoList) {
        set(PROPERTY_PERIODCONTROLLOGPERIODNOLIST, periodControlLogPeriodNoList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlLog> getPeriodControlLogList() {
        return (List<PeriodControlLog>) get(PROPERTY_PERIODCONTROLLOGLIST);
    }

    public void setPeriodControlLogList(List<PeriodControlLog> periodControlLogList) {
        set(PROPERTY_PERIODCONTROLLOGLIST, periodControlLogList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRPiecemaster> getRCHRPiecemasterList() {
        return (List<RCHRPiecemaster>) get(PROPERTY_RCHRPIECEMASTERLIST);
    }

    public void setRCHRPiecemasterList(List<RCHRPiecemaster> rCHRPiecemasterList) {
        set(PROPERTY_RCHRPIECEMASTERLIST, rCHRPiecemasterList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRollnomonthlist> getRchrRollnomonthlistList() {
        return (List<RchrRollnomonthlist>) get(PROPERTY_RCHRROLLNOMONTHLISTLIST);
    }

    public void setRchrRollnomonthlistList(List<RchrRollnomonthlist> rchrRollnomonthlistList) {
        set(PROPERTY_RCHRROLLNOMONTHLISTLIST, rchrRollnomonthlistList);
    }

}
