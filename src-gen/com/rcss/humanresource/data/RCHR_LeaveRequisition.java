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
 * Entity class for entity RCHR_LeaveRequisition (stored in table RCHR_LeaveRequisition).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_LeaveRequisition extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_LeaveRequisition";
    public static final String ENTITY_NAME = "RCHR_LeaveRequisition";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_LEAVETYPE = "leaveType";
    public static final String PROPERTY_NOOFLEAVES = "noOfLeaves";
    public static final String PROPERTY_REASON = "reason";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_FROMDATE = "fromDate";
    public static final String PROPERTY_TODATE = "toDate";
    public static final String PROPERTY_REQUISITIONDATE = "requisitiondate";
    public static final String PROPERTY_LEAVEOPENING = "leaveopening";
    public static final String PROPERTY_LOPLEAVES = "lopleaves";
    public static final String PROPERTY_WEEKOFFLEAVES = "weekoffleaves";
    public static final String PROPERTY_POST = "post";
    public static final String PROPERTY_EMPLOYEETYPE = "employeeType";
    public static final String PROPERTY_VERIFIED = "verified";
    public static final String PROPERTY_APPROVE = "approve";
    public static final String PROPERTY_TOTALLEAVES = "totalleaves";
    public static final String PROPERTY_EL = "el";
    public static final String PROPERTY_CL = "cl";
    public static final String PROPERTY_COFFS = "coffs";
    public static final String PROPERTY_APPROVALSTATUS = "approvalstatus";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_BALANCESL = "balancesl";
    public static final String PROPERTY_BALANCECL = "balancecl";
    public static final String PROPERTY_BALANCECOFFS = "balancecoffs";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_SUBDEPARTMENT = "subDepartment";
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_REMARKS = "remarks";
    public static final String PROPERTY_CANCEL = "cancel";
    public static final String PROPERTY_PAYROLLPOSTED = "payrollposted";
    public static final String PROPERTY_RCHRLEAVEREQUISITIONLINELIST = "rCHRLeaveRequisitionLineList";

    public RCHR_LeaveRequisition() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_LOPLEAVES, (long) 0);
        setDefaultValue(PROPERTY_WEEKOFFLEAVES, (long) 0);
        setDefaultValue(PROPERTY_POST, false);
        setDefaultValue(PROPERTY_VERIFIED, false);
        setDefaultValue(PROPERTY_APPROVE, false);
        setDefaultValue(PROPERTY_TOTALLEAVES, (long) 0);
        setDefaultValue(PROPERTY_EL, (long) 0);
        setDefaultValue(PROPERTY_CL, (long) 0);
        setDefaultValue(PROPERTY_COFFS, (long) 0);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_BALANCESL, (long) 0);
        setDefaultValue(PROPERTY_BALANCECL, (long) 0);
        setDefaultValue(PROPERTY_BALANCECOFFS, (long) 0);
        setDefaultValue(PROPERTY_CANCEL, false);
        setDefaultValue(PROPERTY_PAYROLLPOSTED, false);
        setDefaultValue(PROPERTY_RCHRLEAVEREQUISITIONLINELIST, new ArrayList<Object>());
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

    public RCHR_Leavetype getLeaveType() {
        return (RCHR_Leavetype) get(PROPERTY_LEAVETYPE);
    }

    public void setLeaveType(RCHR_Leavetype leaveType) {
        set(PROPERTY_LEAVETYPE, leaveType);
    }

    public Long getNoOfLeaves() {
        return (Long) get(PROPERTY_NOOFLEAVES);
    }

    public void setNoOfLeaves(Long noOfLeaves) {
        set(PROPERTY_NOOFLEAVES, noOfLeaves);
    }

    public String getReason() {
        return (String) get(PROPERTY_REASON);
    }

    public void setReason(String reason) {
        set(PROPERTY_REASON, reason);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Date getFromDate() {
        return (Date) get(PROPERTY_FROMDATE);
    }

    public void setFromDate(Date fromDate) {
        set(PROPERTY_FROMDATE, fromDate);
    }

    public Date getToDate() {
        return (Date) get(PROPERTY_TODATE);
    }

    public void setToDate(Date toDate) {
        set(PROPERTY_TODATE, toDate);
    }

    public Date getRequisitiondate() {
        return (Date) get(PROPERTY_REQUISITIONDATE);
    }

    public void setRequisitiondate(Date requisitiondate) {
        set(PROPERTY_REQUISITIONDATE, requisitiondate);
    }

    public Long getLeaveopening() {
        return (Long) get(PROPERTY_LEAVEOPENING);
    }

    public void setLeaveopening(Long leaveopening) {
        set(PROPERTY_LEAVEOPENING, leaveopening);
    }

    public Long getLopleaves() {
        return (Long) get(PROPERTY_LOPLEAVES);
    }

    public void setLopleaves(Long lopleaves) {
        set(PROPERTY_LOPLEAVES, lopleaves);
    }

    public Long getWeekoffleaves() {
        return (Long) get(PROPERTY_WEEKOFFLEAVES);
    }

    public void setWeekoffleaves(Long weekoffleaves) {
        set(PROPERTY_WEEKOFFLEAVES, weekoffleaves);
    }

    public Boolean isPost() {
        return (Boolean) get(PROPERTY_POST);
    }

    public void setPost(Boolean post) {
        set(PROPERTY_POST, post);
    }

    public String getEmployeeType() {
        return (String) get(PROPERTY_EMPLOYEETYPE);
    }

    public void setEmployeeType(String employeeType) {
        set(PROPERTY_EMPLOYEETYPE, employeeType);
    }

    public Boolean isVerified() {
        return (Boolean) get(PROPERTY_VERIFIED);
    }

    public void setVerified(Boolean verified) {
        set(PROPERTY_VERIFIED, verified);
    }

    public Boolean isApprove() {
        return (Boolean) get(PROPERTY_APPROVE);
    }

    public void setApprove(Boolean approve) {
        set(PROPERTY_APPROVE, approve);
    }

    public Long getTotalleaves() {
        return (Long) get(PROPERTY_TOTALLEAVES);
    }

    public void setTotalleaves(Long totalleaves) {
        set(PROPERTY_TOTALLEAVES, totalleaves);
    }

    public Long getEl() {
        return (Long) get(PROPERTY_EL);
    }

    public void setEl(Long el) {
        set(PROPERTY_EL, el);
    }

    public Long getCl() {
        return (Long) get(PROPERTY_CL);
    }

    public void setCl(Long cl) {
        set(PROPERTY_CL, cl);
    }

    public Long getCoffs() {
        return (Long) get(PROPERTY_COFFS);
    }

    public void setCoffs(Long coffs) {
        set(PROPERTY_COFFS, coffs);
    }

    public String getApprovalstatus() {
        return (String) get(PROPERTY_APPROVALSTATUS);
    }

    public void setApprovalstatus(String approvalstatus) {
        set(PROPERTY_APPROVALSTATUS, approvalstatus);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Long getBalancesl() {
        return (Long) get(PROPERTY_BALANCESL);
    }

    public void setBalancesl(Long balancesl) {
        set(PROPERTY_BALANCESL, balancesl);
    }

    public Long getBalancecl() {
        return (Long) get(PROPERTY_BALANCECL);
    }

    public void setBalancecl(Long balancecl) {
        set(PROPERTY_BALANCECL, balancecl);
    }

    public Long getBalancecoffs() {
        return (Long) get(PROPERTY_BALANCECOFFS);
    }

    public void setBalancecoffs(Long balancecoffs) {
        set(PROPERTY_BALANCECOFFS, balancecoffs);
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

    public String getRemarks() {
        return (String) get(PROPERTY_REMARKS);
    }

    public void setRemarks(String remarks) {
        set(PROPERTY_REMARKS, remarks);
    }

    public Boolean isCancel() {
        return (Boolean) get(PROPERTY_CANCEL);
    }

    public void setCancel(Boolean cancel) {
        set(PROPERTY_CANCEL, cancel);
    }

    public Boolean isPayrollposted() {
        return (Boolean) get(PROPERTY_PAYROLLPOSTED);
    }

    public void setPayrollposted(Boolean payrollposted) {
        set(PROPERTY_PAYROLLPOSTED, payrollposted);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRLeaveRequisitionLine> getRCHRLeaveRequisitionLineList() {
        return (List<RCHRLeaveRequisitionLine>) get(PROPERTY_RCHRLEAVEREQUISITIONLINELIST);
    }

    public void setRCHRLeaveRequisitionLineList(List<RCHRLeaveRequisitionLine> rCHRLeaveRequisitionLineList) {
        set(PROPERTY_RCHRLEAVEREQUISITIONLINELIST, rCHRLeaveRequisitionLineList);
    }

}
