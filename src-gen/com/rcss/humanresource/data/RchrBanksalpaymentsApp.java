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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Rchr_Banksalpaymentsapp (stored in table Rchr_Banksalpaymentsapp).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrBanksalpaymentsApp extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Banksalpaymentsapp";
    public static final String ENTITY_NAME = "Rchr_Banksalpaymentsapp";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_RCHRATTDPROCESS = "rchrAttdProcess";
    public static final String PROPERTY_TOTALAMOUNT = "totalamount";
    public static final String PROPERTY_PAIDAMOUNT = "paidAmount";
    public static final String PROPERTY_REJECTAMT = "rejectamt";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PAID = "paid";
    public static final String PROPERTY_EMPLOYEETYPE = "employeeType";
    public static final String PROPERTY_CHEQUENO = "chequeno";
    public static final String PROPERTY_BATCHNO = "batchno";
    public static final String PROPERTY_PAYMENTTYPE = "paymentType";
    public static final String PROPERTY_SLOTNO = "slotno";
    public static final String PROPERTY_TRANSACTIONTYPE = "transactiontype";
    public static final String PROPERTY_BANKNAME = "bankname";
    public static final String PROPERTY_RCHRBANKMASTER = "rchrBankmaster";
    public static final String PROPERTY_GENNOTEPAD = "gennotepad";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST = "rchrBanksalpaymentsapplnList";

    public RchrBanksalpaymentsApp() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TOTALAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PAIDAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_REJECTAMT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PAID, false);
        setDefaultValue(PROPERTY_SLOTNO, new BigDecimal(0));
        setDefaultValue(PROPERTY_GENNOTEPAD, false);
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, new ArrayList<Object>());
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

    public BigDecimal getRejectamt() {
        return (BigDecimal) get(PROPERTY_REJECTAMT);
    }

    public void setRejectamt(BigDecimal rejectamt) {
        set(PROPERTY_REJECTAMT, rejectamt);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isPaid() {
        return (Boolean) get(PROPERTY_PAID);
    }

    public void setPaid(Boolean paid) {
        set(PROPERTY_PAID, paid);
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

    public String getBatchno() {
        return (String) get(PROPERTY_BATCHNO);
    }

    public void setBatchno(String batchno) {
        set(PROPERTY_BATCHNO, batchno);
    }

    public String getPaymentType() {
        return (String) get(PROPERTY_PAYMENTTYPE);
    }

    public void setPaymentType(String paymentType) {
        set(PROPERTY_PAYMENTTYPE, paymentType);
    }

    public BigDecimal getSlotno() {
        return (BigDecimal) get(PROPERTY_SLOTNO);
    }

    public void setSlotno(BigDecimal slotno) {
        set(PROPERTY_SLOTNO, slotno);
    }

    public String getTransactiontype() {
        return (String) get(PROPERTY_TRANSACTIONTYPE);
    }

    public void setTransactiontype(String transactiontype) {
        set(PROPERTY_TRANSACTIONTYPE, transactiontype);
    }

    public String getBankname() {
        return (String) get(PROPERTY_BANKNAME);
    }

    public void setBankname(String bankname) {
        set(PROPERTY_BANKNAME, bankname);
    }

    public RchrBankmaster getRchrBankmaster() {
        return (RchrBankmaster) get(PROPERTY_RCHRBANKMASTER);
    }

    public void setRchrBankmaster(RchrBankmaster rchrBankmaster) {
        set(PROPERTY_RCHRBANKMASTER, rchrBankmaster);
    }

    public Boolean isGennotepad() {
        return (Boolean) get(PROPERTY_GENNOTEPAD);
    }

    public void setGennotepad(Boolean gennotepad) {
        set(PROPERTY_GENNOTEPAD, gennotepad);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsappln> getRchrBanksalpaymentsapplnList() {
        return (List<RchrBanksalpaymentsappln>) get(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST);
    }

    public void setRchrBanksalpaymentsapplnList(List<RchrBanksalpaymentsappln> rchrBanksalpaymentsapplnList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, rchrBanksalpaymentsapplnList);
    }

}
