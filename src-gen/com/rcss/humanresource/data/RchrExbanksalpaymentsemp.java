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

import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;

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
 * Entity class for entity Rchr_Exbanksalpaymentsemp (stored in table Rchr_Exbanksalpaymentsemp).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrExbanksalpaymentsemp extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Exbanksalpaymentsemp";
    public static final String ENTITY_NAME = "Rchr_Exbanksalpaymentsemp";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTS = "rchrExbanksalpayments";
    public static final String PROPERTY_PAIDAMOUNT = "paidAmount";
    public static final String PROPERTY_PROCESSINGSAL = "processingsal";
    public static final String PROPERTY_BANKNAME = "bankname";
    public static final String PROPERTY_ACCOUNTNO = "accountNo";
    public static final String PROPERTY_PAYCASH = "paycash";
    public static final String PROPERTY_COPYSALARY = "copysalary";
    public static final String PROPERTY_IFSCCODE = "iFSCCode";
    public static final String PROPERTY_ENTRYDATE = "entrydate";
    public static final String PROPERTY_REJECT = "reject";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_RCPLEMPPAYROLLPROCESS = "rcplEmppayrollprocess";
    public static final String PROPERTY_SLOTNO = "slotno";
    public static final String PROPERTY_COPYAGENTS = "copyagents";
    public static final String PROPERTY_ACCOUNTNAME = "accountname";
    public static final String PROPERTY_RCHRBANKMASTER = "rchrBankmaster";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST = "rchrBanksalpaymentsapplnList";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST = "rchrExbanksalpaymentsemplnList";

    public RchrExbanksalpaymentsemp() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PAIDAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESSINGSAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_PAYCASH, false);
        setDefaultValue(PROPERTY_COPYSALARY, false);
        setDefaultValue(PROPERTY_REJECT, false);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_SLOTNO, new BigDecimal(0));
        setDefaultValue(PROPERTY_COPYAGENTS, false);
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST, new ArrayList<Object>());
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

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public RchrExbanksalpayments getRchrExbanksalpayments() {
        return (RchrExbanksalpayments) get(PROPERTY_RCHREXBANKSALPAYMENTS);
    }

    public void setRchrExbanksalpayments(RchrExbanksalpayments rchrExbanksalpayments) {
        set(PROPERTY_RCHREXBANKSALPAYMENTS, rchrExbanksalpayments);
    }

    public BigDecimal getPaidAmount() {
        return (BigDecimal) get(PROPERTY_PAIDAMOUNT);
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        set(PROPERTY_PAIDAMOUNT, paidAmount);
    }

    public BigDecimal getProcessingsal() {
        return (BigDecimal) get(PROPERTY_PROCESSINGSAL);
    }

    public void setProcessingsal(BigDecimal processingsal) {
        set(PROPERTY_PROCESSINGSAL, processingsal);
    }

    public String getBankname() {
        return (String) get(PROPERTY_BANKNAME);
    }

    public void setBankname(String bankname) {
        set(PROPERTY_BANKNAME, bankname);
    }

    public String getAccountNo() {
        return (String) get(PROPERTY_ACCOUNTNO);
    }

    public void setAccountNo(String accountNo) {
        set(PROPERTY_ACCOUNTNO, accountNo);
    }

    public Boolean isPaycash() {
        return (Boolean) get(PROPERTY_PAYCASH);
    }

    public void setPaycash(Boolean paycash) {
        set(PROPERTY_PAYCASH, paycash);
    }

    public Boolean isCopysalary() {
        return (Boolean) get(PROPERTY_COPYSALARY);
    }

    public void setCopysalary(Boolean copysalary) {
        set(PROPERTY_COPYSALARY, copysalary);
    }

    public String getIFSCCode() {
        return (String) get(PROPERTY_IFSCCODE);
    }

    public void setIFSCCode(String iFSCCode) {
        set(PROPERTY_IFSCCODE, iFSCCode);
    }

    public Date getEntrydate() {
        return (Date) get(PROPERTY_ENTRYDATE);
    }

    public void setEntrydate(Date entrydate) {
        set(PROPERTY_ENTRYDATE, entrydate);
    }

    public Boolean isReject() {
        return (Boolean) get(PROPERTY_REJECT);
    }

    public void setReject(Boolean reject) {
        set(PROPERTY_REJECT, reject);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public RCPL_EmpPayrollProcess getRcplEmppayrollprocess() {
        return (RCPL_EmpPayrollProcess) get(PROPERTY_RCPLEMPPAYROLLPROCESS);
    }

    public void setRcplEmppayrollprocess(RCPL_EmpPayrollProcess rcplEmppayrollprocess) {
        set(PROPERTY_RCPLEMPPAYROLLPROCESS, rcplEmppayrollprocess);
    }

    public BigDecimal getSlotno() {
        return (BigDecimal) get(PROPERTY_SLOTNO);
    }

    public void setSlotno(BigDecimal slotno) {
        set(PROPERTY_SLOTNO, slotno);
    }

    public Boolean isCopyagents() {
        return (Boolean) get(PROPERTY_COPYAGENTS);
    }

    public void setCopyagents(Boolean copyagents) {
        set(PROPERTY_COPYAGENTS, copyagents);
    }

    public String getAccountname() {
        return (String) get(PROPERTY_ACCOUNTNAME);
    }

    public void setAccountname(String accountname) {
        set(PROPERTY_ACCOUNTNAME, accountname);
    }

    public RchrBankmaster getRchrBankmaster() {
        return (RchrBankmaster) get(PROPERTY_RCHRBANKMASTER);
    }

    public void setRchrBankmaster(RchrBankmaster rchrBankmaster) {
        set(PROPERTY_RCHRBANKMASTER, rchrBankmaster);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsappln> getRchrBanksalpaymentsapplnList() {
        return (List<RchrBanksalpaymentsappln>) get(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST);
    }

    public void setRchrBanksalpaymentsapplnList(List<RchrBanksalpaymentsappln> rchrBanksalpaymentsapplnList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, rchrBanksalpaymentsapplnList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpaymentsempLn> getRchrExbanksalpaymentsemplnList() {
        return (List<RchrExbanksalpaymentsempLn>) get(PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST);
    }

    public void setRchrExbanksalpaymentsemplnList(List<RchrExbanksalpaymentsempLn> rchrExbanksalpaymentsemplnList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST, rchrExbanksalpaymentsemplnList);
    }

}
