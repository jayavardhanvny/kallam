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
package com.rcss.humanresource.data;

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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Rchr_Banksalpayments (stored in table Rchr_Banksalpayments).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrBanksalpayments extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Banksalpayments";
    public static final String ENTITY_NAME = "Rchr_Banksalpayments";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_RCHRATTDPROCESS = "rchrAttdProcess";
    public static final String PROPERTY_TOTALAMOUNT = "totalamount";
    public static final String PROPERTY_PAIDAMOUNT = "paidAmount";
    public static final String PROPERTY_PENDINGAMT = "pendingamt";
    public static final String PROPERTY_COPYDATA = "copydata";
    public static final String PROPERTY_PAYCASH = "paycash";
    public static final String PROPERTY_EMPLOYEETYPE = "employeeType";
    public static final String PROPERTY_CHEQUENO = "chequeno";
    public static final String PROPERTY_PAYMENTDATE = "paymentDate";
    public static final String PROPERTY_RCHRBANKMASTER = "rchrBankmaster";
    public static final String PROPERTY_PAYMENTTYPE = "paymentType";
    public static final String PROPERTY_BANKTYPE = "banktype";
    public static final String PROPERTY_COPYCHEQUE = "copycheque";
    public static final String PROPERTY_COPYONLINE = "copyonline";
    public static final String PROPERTY_COPYCASH = "copycash";
    public static final String PROPERTY_CHEQUECNT = "chequecnt";
    public static final String PROPERTY_ONLINECNT = "onlinecnt";
    public static final String PROPERTY_PROCESSINGSALARIES = "processingSalaries";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSCSLIST = "rchrBanksalpaymentscsList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSLNLIST = "rchrBanksalpaymentslnList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSOLLIST = "rchrBanksalpaymentsolList";

    public RchrBanksalpayments() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_TOTALAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PAIDAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PENDINGAMT, new BigDecimal(0));
        setDefaultValue(PROPERTY_COPYDATA, false);
        setDefaultValue(PROPERTY_PAYCASH, false);
        setDefaultValue(PROPERTY_COPYCHEQUE, false);
        setDefaultValue(PROPERTY_COPYONLINE, false);
        setDefaultValue(PROPERTY_COPYCASH, false);
        setDefaultValue(PROPERTY_CHEQUECNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ONLINECNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSCSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSOLLIST, new ArrayList<Object>());
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

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public RchrAttdProcess getRchrAttdProcess() {
        return (RchrAttdProcess) get(PROPERTY_RCHRATTDPROCESS);
    }

    public void setRchrAttdProcess(RchrAttdProcess rchrAttdProcess) {
        set(PROPERTY_RCHRATTDPROCESS, rchrAttdProcess);
    }

    public BigDecimal getTotalamount() {
        return (BigDecimal) get(PROPERTY_TOTALAMOUNT);
    }

    public void setTotalamount(BigDecimal totalamount) {
        set(PROPERTY_TOTALAMOUNT, totalamount);
    }

    public BigDecimal getPaidAmount() {
        return (BigDecimal) get(PROPERTY_PAIDAMOUNT);
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        set(PROPERTY_PAIDAMOUNT, paidAmount);
    }

    public BigDecimal getPendingamt() {
        return (BigDecimal) get(PROPERTY_PENDINGAMT);
    }

    public void setPendingamt(BigDecimal pendingamt) {
        set(PROPERTY_PENDINGAMT, pendingamt);
    }

    public Boolean isCopydata() {
        return (Boolean) get(PROPERTY_COPYDATA);
    }

    public void setCopydata(Boolean copydata) {
        set(PROPERTY_COPYDATA, copydata);
    }

    public Boolean isPaycash() {
        return (Boolean) get(PROPERTY_PAYCASH);
    }

    public void setPaycash(Boolean paycash) {
        set(PROPERTY_PAYCASH, paycash);
    }

    public String getEmployeeType() {
        return (String) get(PROPERTY_EMPLOYEETYPE);
    }

    public void setEmployeeType(String employeeType) {
        set(PROPERTY_EMPLOYEETYPE, employeeType);
    }

    public String getChequeno() {
        return (String) get(PROPERTY_CHEQUENO);
    }

    public void setChequeno(String chequeno) {
        set(PROPERTY_CHEQUENO, chequeno);
    }

    public Date getPaymentDate() {
        return (Date) get(PROPERTY_PAYMENTDATE);
    }

    public void setPaymentDate(Date paymentDate) {
        set(PROPERTY_PAYMENTDATE, paymentDate);
    }

    public RchrBankmaster getRchrBankmaster() {
        return (RchrBankmaster) get(PROPERTY_RCHRBANKMASTER);
    }

    public void setRchrBankmaster(RchrBankmaster rchrBankmaster) {
        set(PROPERTY_RCHRBANKMASTER, rchrBankmaster);
    }

    public String getPaymentType() {
        return (String) get(PROPERTY_PAYMENTTYPE);
    }

    public void setPaymentType(String paymentType) {
        set(PROPERTY_PAYMENTTYPE, paymentType);
    }

    public String getBanktype() {
        return (String) get(PROPERTY_BANKTYPE);
    }

    public void setBanktype(String banktype) {
        set(PROPERTY_BANKTYPE, banktype);
    }

    public Boolean isCopycheque() {
        return (Boolean) get(PROPERTY_COPYCHEQUE);
    }

    public void setCopycheque(Boolean copycheque) {
        set(PROPERTY_COPYCHEQUE, copycheque);
    }

    public Boolean isCopyonline() {
        return (Boolean) get(PROPERTY_COPYONLINE);
    }

    public void setCopyonline(Boolean copyonline) {
        set(PROPERTY_COPYONLINE, copyonline);
    }

    public Boolean isCopycash() {
        return (Boolean) get(PROPERTY_COPYCASH);
    }

    public void setCopycash(Boolean copycash) {
        set(PROPERTY_COPYCASH, copycash);
    }

    public BigDecimal getChequecnt() {
        return (BigDecimal) get(PROPERTY_CHEQUECNT);
    }

    public void setChequecnt(BigDecimal chequecnt) {
        set(PROPERTY_CHEQUECNT, chequecnt);
    }

    public BigDecimal getOnlinecnt() {
        return (BigDecimal) get(PROPERTY_ONLINECNT);
    }

    public void setOnlinecnt(BigDecimal onlinecnt) {
        set(PROPERTY_ONLINECNT, onlinecnt);
    }

    public BigDecimal getProcessingSalaries() {
        return (BigDecimal) get(PROPERTY_PROCESSINGSALARIES);
    }

    public void setProcessingSalaries(BigDecimal processingSalaries) {
        set(PROPERTY_PROCESSINGSALARIES, processingSalaries);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsCash> getRchrBanksalpaymentscsList() {
        return (List<RchrBanksalpaymentsCash>) get(PROPERTY_RCHRBANKSALPAYMENTSCSLIST);
    }

    public void setRchrBanksalpaymentscsList(List<RchrBanksalpaymentsCash> rchrBanksalpaymentscsList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSCSLIST, rchrBanksalpaymentscsList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsLn> getRchrBanksalpaymentslnList() {
        return (List<RchrBanksalpaymentsLn>) get(PROPERTY_RCHRBANKSALPAYMENTSLNLIST);
    }

    public void setRchrBanksalpaymentslnList(List<RchrBanksalpaymentsLn> rchrBanksalpaymentslnList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSLNLIST, rchrBanksalpaymentslnList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBankSalPaymentsOnline> getRchrBanksalpaymentsolList() {
        return (List<RchrBankSalPaymentsOnline>) get(PROPERTY_RCHRBANKSALPAYMENTSOLLIST);
    }

    public void setRchrBanksalpaymentsolList(List<RchrBankSalPaymentsOnline> rchrBanksalpaymentsolList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSOLLIST, rchrBanksalpaymentsolList);
    }

}
