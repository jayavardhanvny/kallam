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
 * Entity class for entity Rchr_Banksalpaymentsappln (stored in table Rchr_Banksalpaymentsappln).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrBanksalpaymentsappln extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Banksalpaymentsappln";
    public static final String ENTITY_NAME = "Rchr_Banksalpaymentsappln";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSAPP = "rchrBanksalpaymentsapp";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSOL = "rchrBanksalpaymentsol";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSLN = "rchrBanksalpaymentsln";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSEMP = "rchrExbanksalpaymentsemp";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_SALALRY = "salalry";
    public static final String PROPERTY_RCPLEMPPAYROLLPROCESS = "rcplEmppayrollprocess";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_ACCOUNTNO = "accountNo";
    public static final String PROPERTY_IFSCCODE = "iFSCCode";
    public static final String PROPERTY_RCHRBANKMASTER = "rchrBankmaster";
    public static final String PROPERTY_ACCOUNTNAME = "accountname";

    public RchrBanksalpaymentsappln() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SALALRY, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
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

    public RchrBanksalpaymentsApp getRchrBanksalpaymentsapp() {
        return (RchrBanksalpaymentsApp) get(PROPERTY_RCHRBANKSALPAYMENTSAPP);
    }

    public void setRchrBanksalpaymentsapp(RchrBanksalpaymentsApp rchrBanksalpaymentsapp) {
        set(PROPERTY_RCHRBANKSALPAYMENTSAPP, rchrBanksalpaymentsapp);
    }

    public RchrBankSalPaymentsOnline getRchrBanksalpaymentsol() {
        return (RchrBankSalPaymentsOnline) get(PROPERTY_RCHRBANKSALPAYMENTSOL);
    }

    public void setRchrBanksalpaymentsol(RchrBankSalPaymentsOnline rchrBanksalpaymentsol) {
        set(PROPERTY_RCHRBANKSALPAYMENTSOL, rchrBanksalpaymentsol);
    }

    public RchrBanksalpaymentsLn getRchrBanksalpaymentsln() {
        return (RchrBanksalpaymentsLn) get(PROPERTY_RCHRBANKSALPAYMENTSLN);
    }

    public void setRchrBanksalpaymentsln(RchrBanksalpaymentsLn rchrBanksalpaymentsln) {
        set(PROPERTY_RCHRBANKSALPAYMENTSLN, rchrBanksalpaymentsln);
    }

    public RchrExbanksalpaymentsemp getRchrExbanksalpaymentsemp() {
        return (RchrExbanksalpaymentsemp) get(PROPERTY_RCHREXBANKSALPAYMENTSEMP);
    }

    public void setRchrExbanksalpaymentsemp(RchrExbanksalpaymentsemp rchrExbanksalpaymentsemp) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSEMP, rchrExbanksalpaymentsemp);
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

    public BigDecimal getSalalry() {
        return (BigDecimal) get(PROPERTY_SALALRY);
    }

    public void setSalalry(BigDecimal salalry) {
        set(PROPERTY_SALALRY, salalry);
    }

    public RCPL_EmpPayrollProcess getRcplEmppayrollprocess() {
        return (RCPL_EmpPayrollProcess) get(PROPERTY_RCPLEMPPAYROLLPROCESS);
    }

    public void setRcplEmppayrollprocess(RCPL_EmpPayrollProcess rcplEmppayrollprocess) {
        set(PROPERTY_RCPLEMPPAYROLLPROCESS, rcplEmppayrollprocess);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getAccountNo() {
        return (String) get(PROPERTY_ACCOUNTNO);
    }

    public void setAccountNo(String accountNo) {
        set(PROPERTY_ACCOUNTNO, accountNo);
    }

    public String getIFSCCode() {
        return (String) get(PROPERTY_IFSCCODE);
    }

    public void setIFSCCode(String iFSCCode) {
        set(PROPERTY_IFSCCODE, iFSCCode);
    }

    public RchrBankmaster getRchrBankmaster() {
        return (RchrBankmaster) get(PROPERTY_RCHRBANKMASTER);
    }

    public void setRchrBankmaster(RchrBankmaster rchrBankmaster) {
        set(PROPERTY_RCHRBANKMASTER, rchrBankmaster);
    }

    public String getAccountname() {
        return (String) get(PROPERTY_ACCOUNTNAME);
    }

    public void setAccountname(String accountname) {
        set(PROPERTY_ACCOUNTNAME, accountname);
    }

}
