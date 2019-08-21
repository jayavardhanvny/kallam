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

import com.rcss.humanresource.data.RCHR_SubDepartment;
import com.rcss.humanresource.data.RchrDesignation;
import com.rcss.humanresource.data.RchrEmpDept;
import com.rcss.humanresource.data.RchrEmployee;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCPL_Payrollpaidprocline (stored in table RCPL_Payrollpaidprocline).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPLPayrollpaidprocline extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_Payrollpaidprocline";
    public static final String ENTITY_NAME = "RCPL_Payrollpaidprocline";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCPLPAYROLLPAIDPROC = "rcplPayrollpaidproc";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_PROCESSINGDAYS = "processingDays";
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
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_PFAPPLICABLE = "pFApplicable";
    public static final String PROPERTY_ISBANK = "isbank";
    public static final String PROPERTY_PAIDDATE = "paiddate";
    public static final String PROPERTY_PAID = "paid";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_PAIDAPPROVAL = "paidapproval";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_SUBDEPARTMENT = "subDepartment";

    public RCPLPayrollpaidprocline() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSINGDAYS, (long) 0);
        setDefaultValue(PROPERTY_ADDITIONS, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEDUCTIONS, new BigDecimal(0));
        setDefaultValue(PROPERTY_INCENTIVEADDITIONS, new BigDecimal(0));
        setDefaultValue(PROPERTY_INCENTIVEDEDUCTIONS, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALPAY, new BigDecimal(0));
        setDefaultValue(PROPERTY_LATEDEDUCTION, new BigDecimal(0));
        setDefaultValue(PROPERTY_GRANDTOTALAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRESENTDAYS, (long) 0);
        setDefaultValue(PROPERTY_WEEKLYOFF, (long) 0);
        setDefaultValue(PROPERTY_LEAVES, (long) 0);
        setDefaultValue(PROPERTY_ABSENTS, (long) 0);
        setDefaultValue(PROPERTY_WEEKOFFWORKED, (long) 0);
        setDefaultValue(PROPERTY_BASICAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_SERVINCENTIVEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PFAPPLICABLE, false);
        setDefaultValue(PROPERTY_ISBANK, false);
        setDefaultValue(PROPERTY_PAID, false);
        setDefaultValue(PROPERTY_PAIDAPPROVAL, false);
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

    public RCPLPayrollpaidproc getRcplPayrollpaidproc() {
        return (RCPLPayrollpaidproc) get(PROPERTY_RCPLPAYROLLPAIDPROC);
    }

    public void setRcplPayrollpaidproc(RCPLPayrollpaidproc rcplPayrollpaidproc) {
        set(PROPERTY_RCPLPAYROLLPAIDPROC, rcplPayrollpaidproc);
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

    public Long getProcessingDays() {
        return (Long) get(PROPERTY_PROCESSINGDAYS);
    }

    public void setProcessingDays(Long processingDays) {
        set(PROPERTY_PROCESSINGDAYS, processingDays);
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

    public Long getPresentdays() {
        return (Long) get(PROPERTY_PRESENTDAYS);
    }

    public void setPresentdays(Long presentdays) {
        set(PROPERTY_PRESENTDAYS, presentdays);
    }

    public Long getWeeklyOff() {
        return (Long) get(PROPERTY_WEEKLYOFF);
    }

    public void setWeeklyOff(Long weeklyOff) {
        set(PROPERTY_WEEKLYOFF, weeklyOff);
    }

    public Long getLeaves() {
        return (Long) get(PROPERTY_LEAVES);
    }

    public void setLeaves(Long leaves) {
        set(PROPERTY_LEAVES, leaves);
    }

    public Long getAbsents() {
        return (Long) get(PROPERTY_ABSENTS);
    }

    public void setAbsents(Long absents) {
        set(PROPERTY_ABSENTS, absents);
    }

    public Long getWeekoffworked() {
        return (Long) get(PROPERTY_WEEKOFFWORKED);
    }

    public void setWeekoffworked(Long weekoffworked) {
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

    public Date getPaiddate() {
        return (Date) get(PROPERTY_PAIDDATE);
    }

    public void setPaiddate(Date paiddate) {
        set(PROPERTY_PAIDDATE, paiddate);
    }

    public Boolean isPaid() {
        return (Boolean) get(PROPERTY_PAID);
    }

    public void setPaid(Boolean paid) {
        set(PROPERTY_PAID, paid);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public Boolean isPaidapproval() {
        return (Boolean) get(PROPERTY_PAIDAPPROVAL);
    }

    public void setPaidapproval(Boolean paidapproval) {
        set(PROPERTY_PAIDAPPROVAL, paidapproval);
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

}
