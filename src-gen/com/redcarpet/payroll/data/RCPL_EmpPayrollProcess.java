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
package com.redcarpet.payroll.data;

import com.rcss.humanresource.data.RCHR_Room;
import com.rcss.humanresource.data.RCHR_SubDepartment;
import com.rcss.humanresource.data.RchrBankSalPaymentsOnline;
import com.rcss.humanresource.data.RchrBanksalpaymentsCash;
import com.rcss.humanresource.data.RchrBanksalpaymentsLn;
import com.rcss.humanresource.data.RchrBanksalpaymentsappln;
import com.rcss.humanresource.data.RchrDesignation;
import com.rcss.humanresource.data.RchrEmpDept;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrExbanksalpaymentsemp;
import com.rcss.humanresource.data.RchrExbanksalpaymentsempLn;

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
 * Entity class for entity RCPL_EmpPayrollProcess (stored in table RCPL_EmpPayrollProcess).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPL_EmpPayrollProcess extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_EmpPayrollProcess";
    public static final String ENTITY_NAME = "RCPL_EmpPayrollProcess";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PAYROLLPROCESS = "payrollProcess";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_PROCESSINGDAYS = "processingDays";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_ADDITIONS = "additions";
    public static final String PROPERTY_DEDUCTIONS = "deductions";
    public static final String PROPERTY_INCENTIVEADDITIONS = "incentiveAdditions";
    public static final String PROPERTY_INCENTIVEDEDUCTIONS = "incentiveDeductions";
    public static final String PROPERTY_TOTALPAY = "totalPay";
    public static final String PROPERTY_LATEDEDUCTION = "lateDeduction";
    public static final String PROPERTY_GRANDTOTALAMOUNT = "grandTotalAmount";
    public static final String PROPERTY_PRESENTDAYS = "presentdays";
    public static final String PROPERTY_WEEKLYOFF = "weeklyOff";
    public static final String PROPERTY_LEAVES = "leaves";
    public static final String PROPERTY_ABSENTS = "absents";
    public static final String PROPERTY_WEEKOFFWORKED = "weekoffworked";
    public static final String PROPERTY_BASICAMOUNT = "basicamount";
    public static final String PROPERTY_SERVINCENTIVEAMOUNT = "servincentiveamount";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_SUBDEPARTMENT = "subDepartment";
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_PFAPPLICABLE = "pFApplicable";
    public static final String PROPERTY_ISBANK = "isbank";
    public static final String PROPERTY_PENDING = "pending";
    public static final String PROPERTY_CURRENTSERVICEDAYS = "currentservicedays";
    public static final String PROPERTY_GROSSPAY = "grosspay";
    public static final String PROPERTY_PENDINGAMT = "pendingamt";
    public static final String PROPERTY_EMPLOYEETYPE = "employeeType";
    public static final String PROPERTY_ROOM = "room";
    public static final String PROPERTY_ACCOUNTNO = "accountNo";
    public static final String PROPERTY_RCPLEMPDEDINCENTIVESLIST = "rCPLEmpDedIncentivesList";
    public static final String PROPERTY_RCPLEMPOTINCENTIVESLIST = "rCPLEmpOTIncentivesList";
    public static final String PROPERTY_RCPLEMPPAYDEADLIST = "rCPLEmpPayDeadList";
    public static final String PROPERTY_RCPLEMPPAYHEADLIST = "rCPLEmpPayHeadList";
    public static final String PROPERTY_RCPLEMPPAYINCENTIVESLIST = "rCPLEmpPayIncentivesList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST = "rchrBanksalpaymentsapplnList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSCSLIST = "rchrBanksalpaymentscsList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSLNLIST = "rchrBanksalpaymentslnList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSOLLIST = "rchrBanksalpaymentsolList";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST = "rchrExbanksalpaymentsempList";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST = "rchrExbanksalpaymentsemplnList";

    public RCPL_EmpPayrollProcess() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSINGDAYS, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_ADDITIONS, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEDUCTIONS, new BigDecimal(0));
        setDefaultValue(PROPERTY_INCENTIVEADDITIONS, new BigDecimal(0));
        setDefaultValue(PROPERTY_INCENTIVEDEDUCTIONS, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALPAY, new BigDecimal(0));
        setDefaultValue(PROPERTY_LATEDEDUCTION, new BigDecimal(0));
        setDefaultValue(PROPERTY_GRANDTOTALAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRESENTDAYS, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEEKLYOFF, new BigDecimal(0));
        setDefaultValue(PROPERTY_LEAVES, new BigDecimal(0));
        setDefaultValue(PROPERTY_ABSENTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEEKOFFWORKED, new BigDecimal(0));
        setDefaultValue(PROPERTY_BASICAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_SERVINCENTIVEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PFAPPLICABLE, false);
        setDefaultValue(PROPERTY_ISBANK, false);
        setDefaultValue(PROPERTY_PENDING, false);
        setDefaultValue(PROPERTY_PENDINGAMT, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCPLEMPDEDINCENTIVESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPOTINCENTIVESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPAYDEADLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPAYHEADLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPAYINCENTIVESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSCSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSOLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST, new ArrayList<Object>());
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

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public RCPL_PayrollProcess getPayrollProcess() {
        return (RCPL_PayrollProcess) get(PROPERTY_PAYROLLPROCESS);
    }

    public void setPayrollProcess(RCPL_PayrollProcess payrollProcess) {
        set(PROPERTY_PAYROLLPROCESS, payrollProcess);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
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

    public BigDecimal getProcessingDays() {
        return (BigDecimal) get(PROPERTY_PROCESSINGDAYS);
    }

    public void setProcessingDays(BigDecimal processingDays) {
        set(PROPERTY_PROCESSINGDAYS, processingDays);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public BigDecimal getAdditions() {
        return (BigDecimal) get(PROPERTY_ADDITIONS);
    }

    public void setAdditions(BigDecimal additions) {
        set(PROPERTY_ADDITIONS, additions);
    }

    public BigDecimal getDeductions() {
        return (BigDecimal) get(PROPERTY_DEDUCTIONS);
    }

    public void setDeductions(BigDecimal deductions) {
        set(PROPERTY_DEDUCTIONS, deductions);
    }

    public BigDecimal getIncentiveAdditions() {
        return (BigDecimal) get(PROPERTY_INCENTIVEADDITIONS);
    }

    public void setIncentiveAdditions(BigDecimal incentiveAdditions) {
        set(PROPERTY_INCENTIVEADDITIONS, incentiveAdditions);
    }

    public BigDecimal getIncentiveDeductions() {
        return (BigDecimal) get(PROPERTY_INCENTIVEDEDUCTIONS);
    }

    public void setIncentiveDeductions(BigDecimal incentiveDeductions) {
        set(PROPERTY_INCENTIVEDEDUCTIONS, incentiveDeductions);
    }

    public BigDecimal getTotalPay() {
        return (BigDecimal) get(PROPERTY_TOTALPAY);
    }

    public void setTotalPay(BigDecimal totalPay) {
        set(PROPERTY_TOTALPAY, totalPay);
    }

    public BigDecimal getLateDeduction() {
        return (BigDecimal) get(PROPERTY_LATEDEDUCTION);
    }

    public void setLateDeduction(BigDecimal lateDeduction) {
        set(PROPERTY_LATEDEDUCTION, lateDeduction);
    }

    public BigDecimal getGrandTotalAmount() {
        return (BigDecimal) get(PROPERTY_GRANDTOTALAMOUNT);
    }

    public void setGrandTotalAmount(BigDecimal grandTotalAmount) {
        set(PROPERTY_GRANDTOTALAMOUNT, grandTotalAmount);
    }

    public BigDecimal getPresentdays() {
        return (BigDecimal) get(PROPERTY_PRESENTDAYS);
    }

    public void setPresentdays(BigDecimal presentdays) {
        set(PROPERTY_PRESENTDAYS, presentdays);
    }

    public BigDecimal getWeeklyOff() {
        return (BigDecimal) get(PROPERTY_WEEKLYOFF);
    }

    public void setWeeklyOff(BigDecimal weeklyOff) {
        set(PROPERTY_WEEKLYOFF, weeklyOff);
    }

    public BigDecimal getLeaves() {
        return (BigDecimal) get(PROPERTY_LEAVES);
    }

    public void setLeaves(BigDecimal leaves) {
        set(PROPERTY_LEAVES, leaves);
    }

    public BigDecimal getAbsents() {
        return (BigDecimal) get(PROPERTY_ABSENTS);
    }

    public void setAbsents(BigDecimal absents) {
        set(PROPERTY_ABSENTS, absents);
    }

    public BigDecimal getWeekoffworked() {
        return (BigDecimal) get(PROPERTY_WEEKOFFWORKED);
    }

    public void setWeekoffworked(BigDecimal weekoffworked) {
        set(PROPERTY_WEEKOFFWORKED, weekoffworked);
    }

    public BigDecimal getBasicamount() {
        return (BigDecimal) get(PROPERTY_BASICAMOUNT);
    }

    public void setBasicamount(BigDecimal basicamount) {
        set(PROPERTY_BASICAMOUNT, basicamount);
    }

    public BigDecimal getServincentiveamount() {
        return (BigDecimal) get(PROPERTY_SERVINCENTIVEAMOUNT);
    }

    public void setServincentiveamount(BigDecimal servincentiveamount) {
        set(PROPERTY_SERVINCENTIVEAMOUNT, servincentiveamount);
    }

    public RchrEmpDept getEmployeeDepartment() {
        return (RchrEmpDept) get(PROPERTY_EMPLOYEEDEPARTMENT);
    }

    public void setEmployeeDepartment(RchrEmpDept employeeDepartment) {
        set(PROPERTY_EMPLOYEEDEPARTMENT, employeeDepartment);
    }

    public RCHR_SubDepartment getSubDepartment() {
        return (RCHR_SubDepartment) get(PROPERTY_SUBDEPARTMENT);
    }

    public void setSubDepartment(RCHR_SubDepartment subDepartment) {
        set(PROPERTY_SUBDEPARTMENT, subDepartment);
    }

    public RchrDesignation getDesignation() {
        return (RchrDesignation) get(PROPERTY_DESIGNATION);
    }

    public void setDesignation(RchrDesignation designation) {
        set(PROPERTY_DESIGNATION, designation);
    }

    public Boolean isPFApplicable() {
        return (Boolean) get(PROPERTY_PFAPPLICABLE);
    }

    public void setPFApplicable(Boolean pFApplicable) {
        set(PROPERTY_PFAPPLICABLE, pFApplicable);
    }

    public Boolean isBank() {
        return (Boolean) get(PROPERTY_ISBANK);
    }

    public void setBank(Boolean isbank) {
        set(PROPERTY_ISBANK, isbank);
    }

    public Boolean isPending() {
        return (Boolean) get(PROPERTY_PENDING);
    }

    public void setPending(Boolean pending) {
        set(PROPERTY_PENDING, pending);
    }

    public BigDecimal getCurrentservicedays() {
        return (BigDecimal) get(PROPERTY_CURRENTSERVICEDAYS);
    }

    public void setCurrentservicedays(BigDecimal currentservicedays) {
        set(PROPERTY_CURRENTSERVICEDAYS, currentservicedays);
    }

    public BigDecimal getGrosspay() {
        return (BigDecimal) get(PROPERTY_GROSSPAY);
    }

    public void setGrosspay(BigDecimal grosspay) {
        set(PROPERTY_GROSSPAY, grosspay);
    }

    public BigDecimal getPendingamt() {
        return (BigDecimal) get(PROPERTY_PENDINGAMT);
    }

    public void setPendingamt(BigDecimal pendingamt) {
        set(PROPERTY_PENDINGAMT, pendingamt);
    }

    public String getEmployeeType() {
        return (String) get(PROPERTY_EMPLOYEETYPE);
    }

    public void setEmployeeType(String employeeType) {
        set(PROPERTY_EMPLOYEETYPE, employeeType);
    }

    public RCHR_Room getRoom() {
        return (RCHR_Room) get(PROPERTY_ROOM);
    }

    public void setRoom(RCHR_Room room) {
        set(PROPERTY_ROOM, room);
    }

    public String getAccountNo() {
        return (String) get(PROPERTY_ACCOUNTNO);
    }

    public void setAccountNo(String accountNo) {
        set(PROPERTY_ACCOUNTNO, accountNo);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpDedIncentives> getRCPLEmpDedIncentivesList() {
        return (List<RCPL_EmpDedIncentives>) get(PROPERTY_RCPLEMPDEDINCENTIVESLIST);
    }

    public void setRCPLEmpDedIncentivesList(List<RCPL_EmpDedIncentives> rCPLEmpDedIncentivesList) {
        set(PROPERTY_RCPLEMPDEDINCENTIVESLIST, rCPLEmpDedIncentivesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpOTIncentives> getRCPLEmpOTIncentivesList() {
        return (List<RCPL_EmpOTIncentives>) get(PROPERTY_RCPLEMPOTINCENTIVESLIST);
    }

    public void setRCPLEmpOTIncentivesList(List<RCPL_EmpOTIncentives> rCPLEmpOTIncentivesList) {
        set(PROPERTY_RCPLEMPOTINCENTIVESLIST, rCPLEmpOTIncentivesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpPayDead> getRCPLEmpPayDeadList() {
        return (List<RCPL_EmpPayDead>) get(PROPERTY_RCPLEMPPAYDEADLIST);
    }

    public void setRCPLEmpPayDeadList(List<RCPL_EmpPayDead> rCPLEmpPayDeadList) {
        set(PROPERTY_RCPLEMPPAYDEADLIST, rCPLEmpPayDeadList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpPayHead> getRCPLEmpPayHeadList() {
        return (List<RCPL_EmpPayHead>) get(PROPERTY_RCPLEMPPAYHEADLIST);
    }

    public void setRCPLEmpPayHeadList(List<RCPL_EmpPayHead> rCPLEmpPayHeadList) {
        set(PROPERTY_RCPLEMPPAYHEADLIST, rCPLEmpPayHeadList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpPayIncentives> getRCPLEmpPayIncentivesList() {
        return (List<RCPL_EmpPayIncentives>) get(PROPERTY_RCPLEMPPAYINCENTIVESLIST);
    }

    public void setRCPLEmpPayIncentivesList(List<RCPL_EmpPayIncentives> rCPLEmpPayIncentivesList) {
        set(PROPERTY_RCPLEMPPAYINCENTIVESLIST, rCPLEmpPayIncentivesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsappln> getRchrBanksalpaymentsapplnList() {
        return (List<RchrBanksalpaymentsappln>) get(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST);
    }

    public void setRchrBanksalpaymentsapplnList(List<RchrBanksalpaymentsappln> rchrBanksalpaymentsapplnList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, rchrBanksalpaymentsapplnList);
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

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpaymentsemp> getRchrExbanksalpaymentsempList() {
        return (List<RchrExbanksalpaymentsemp>) get(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST);
    }

    public void setRchrExbanksalpaymentsempList(List<RchrExbanksalpaymentsemp> rchrExbanksalpaymentsempList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST, rchrExbanksalpaymentsempList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpaymentsempLn> getRchrExbanksalpaymentsemplnList() {
        return (List<RchrExbanksalpaymentsempLn>) get(PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST);
    }

    public void setRchrExbanksalpaymentsemplnList(List<RchrExbanksalpaymentsempLn> rchrExbanksalpaymentsemplnList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST, rchrExbanksalpaymentsemplnList);
    }

}
