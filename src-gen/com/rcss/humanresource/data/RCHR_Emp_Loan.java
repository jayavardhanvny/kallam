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
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCHR_Emp_Loan (stored in table RCHR_Emp_Loan).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Emp_Loan extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Emp_Loan";
    public static final String ENTITY_NAME = "RCHR_Emp_Loan";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LOANCATEGORY = "loanCategory";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_LOANAMOUNT = "loanAmount";
    public static final String PROPERTY_TENUREMONTHS = "tenureMonths";
    public static final String PROPERTY_PROCESSING = "processing";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PENDINGADVANCES = "pendingAdvances";
    public static final String PROPERTY_LOANPENDINGS = "loanPendings";
    public static final String PROPERTY_SURETYEMPID = "suretyempid";
    public static final String PROPERTY_NOOFPRESENTS = "noofpresents";
    public static final String PROPERTY_DEPARTMENTSTORE = "departmentstore";
    public static final String PROPERTY_MESSBILL = "messbill";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_REQUISITIONDATE = "requisitiondate";
    public static final String PROPERTY_APPROVE = "approve";
    public static final String PROPERTY_RCHRATTDPROCESS = "rchrAttdProcess";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_ISSUED = "issued";
    public static final String PROPERTY_PAID = "paid";
    public static final String PROPERTY_RCHRSALADVANCE = "rchrSaladvance";
    public static final String PROPERTY_ISREMARK = "isremark";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_PAIDDATE = "paiddate";
    public static final String PROPERTY_RCHRHOMEAPPLIANCE = "rchrHomeappliance";
    public static final String PROPERTY_REJECT = "reject";
    public static final String PROPERTY_RCHREMPLOANLINESLIST = "rCHREmpLoanlinesList";

    public RCHR_Emp_Loan() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSING, false);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_DEPARTMENTSTORE, new BigDecimal(0));
        setDefaultValue(PROPERTY_MESSBILL, new BigDecimal(0));
        setDefaultValue(PROPERTY_ALERTSTATUS, "Draft");
        setDefaultValue(PROPERTY_APPROVE, false);
        setDefaultValue(PROPERTY_ISSUED, false);
        setDefaultValue(PROPERTY_PAID, false);
        setDefaultValue(PROPERTY_ISREMARK, false);
        setDefaultValue(PROPERTY_REJECT, false);
        setDefaultValue(PROPERTY_RCHREMPLOANLINESLIST, new ArrayList<Object>());
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

    public RCHR_Loan_Type getLoanCategory() {
        return (RCHR_Loan_Type) get(PROPERTY_LOANCATEGORY);
    }

    public void setLoanCategory(RCHR_Loan_Type loanCategory) {
        set(PROPERTY_LOANCATEGORY, loanCategory);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
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

    public BigDecimal getLoanAmount() {
        return (BigDecimal) get(PROPERTY_LOANAMOUNT);
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        set(PROPERTY_LOANAMOUNT, loanAmount);
    }

    public BigDecimal getTenureMonths() {
        return (BigDecimal) get(PROPERTY_TENUREMONTHS);
    }

    public void setTenureMonths(BigDecimal tenureMonths) {
        set(PROPERTY_TENUREMONTHS, tenureMonths);
    }

    public Boolean isProcessing() {
        return (Boolean) get(PROPERTY_PROCESSING);
    }

    public void setProcessing(Boolean processing) {
        set(PROPERTY_PROCESSING, processing);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public BigDecimal getPendingAdvances() {
        return (BigDecimal) get(PROPERTY_PENDINGADVANCES);
    }

    public void setPendingAdvances(BigDecimal pendingAdvances) {
        set(PROPERTY_PENDINGADVANCES, pendingAdvances);
    }

    public BigDecimal getLoanPendings() {
        return (BigDecimal) get(PROPERTY_LOANPENDINGS);
    }

    public void setLoanPendings(BigDecimal loanPendings) {
        set(PROPERTY_LOANPENDINGS, loanPendings);
    }

    public RchrEmployee getSuretyempid() {
        return (RchrEmployee) get(PROPERTY_SURETYEMPID);
    }

    public void setSuretyempid(RchrEmployee suretyempid) {
        set(PROPERTY_SURETYEMPID, suretyempid);
    }

    public BigDecimal getNoofpresents() {
        return (BigDecimal) get(PROPERTY_NOOFPRESENTS);
    }

    public void setNoofpresents(BigDecimal noofpresents) {
        set(PROPERTY_NOOFPRESENTS, noofpresents);
    }

    public BigDecimal getDepartmentstore() {
        return (BigDecimal) get(PROPERTY_DEPARTMENTSTORE);
    }

    public void setDepartmentstore(BigDecimal departmentstore) {
        set(PROPERTY_DEPARTMENTSTORE, departmentstore);
    }

    public BigDecimal getMessbill() {
        return (BigDecimal) get(PROPERTY_MESSBILL);
    }

    public void setMessbill(BigDecimal messbill) {
        set(PROPERTY_MESSBILL, messbill);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Date getRequisitiondate() {
        return (Date) get(PROPERTY_REQUISITIONDATE);
    }

    public void setRequisitiondate(Date requisitiondate) {
        set(PROPERTY_REQUISITIONDATE, requisitiondate);
    }

    public Boolean isApprove() {
        return (Boolean) get(PROPERTY_APPROVE);
    }

    public void setApprove(Boolean approve) {
        set(PROPERTY_APPROVE, approve);
    }

    public RchrAttdProcess getRchrAttdProcess() {
        return (RchrAttdProcess) get(PROPERTY_RCHRATTDPROCESS);
    }

    public void setRchrAttdProcess(RchrAttdProcess rchrAttdProcess) {
        set(PROPERTY_RCHRATTDPROCESS, rchrAttdProcess);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public Boolean isSued() {
        return (Boolean) get(PROPERTY_ISSUED);
    }

    public void setSued(Boolean issued) {
        set(PROPERTY_ISSUED, issued);
    }

    public Boolean isPaid() {
        return (Boolean) get(PROPERTY_PAID);
    }

    public void setPaid(Boolean paid) {
        set(PROPERTY_PAID, paid);
    }

    public RCHRSaladvance getRchrSaladvance() {
        return (RCHRSaladvance) get(PROPERTY_RCHRSALADVANCE);
    }

    public void setRchrSaladvance(RCHRSaladvance rchrSaladvance) {
        set(PROPERTY_RCHRSALADVANCE, rchrSaladvance);
    }

    public Boolean isRemark() {
        return (Boolean) get(PROPERTY_ISREMARK);
    }

    public void setRemark(Boolean isremark) {
        set(PROPERTY_ISREMARK, isremark);
    }

    public String getReasonForLeave() {
        return (String) get(PROPERTY_REASONFORLEAVE);
    }

    public void setReasonForLeave(String reasonForLeave) {
        set(PROPERTY_REASONFORLEAVE, reasonForLeave);
    }

    public RchrEmpDept getEmployeeDepartment() {
        return (RchrEmpDept) get(PROPERTY_EMPLOYEEDEPARTMENT);
    }

    public void setEmployeeDepartment(RchrEmpDept employeeDepartment) {
        set(PROPERTY_EMPLOYEEDEPARTMENT, employeeDepartment);
    }

    public RchrDesignation getDesignation() {
        return (RchrDesignation) get(PROPERTY_DESIGNATION);
    }

    public void setDesignation(RchrDesignation designation) {
        set(PROPERTY_DESIGNATION, designation);
    }

    public Date getPaiddate() {
        return (Date) get(PROPERTY_PAIDDATE);
    }

    public void setPaiddate(Date paiddate) {
        set(PROPERTY_PAIDDATE, paiddate);
    }

    public rchr_homeappliance getRchrHomeappliance() {
        return (rchr_homeappliance) get(PROPERTY_RCHRHOMEAPPLIANCE);
    }

    public void setRchrHomeappliance(rchr_homeappliance rchrHomeappliance) {
        set(PROPERTY_RCHRHOMEAPPLIANCE, rchrHomeappliance);
    }

    public Boolean isReject() {
        return (Boolean) get(PROPERTY_REJECT);
    }

    public void setReject(Boolean reject) {
        set(PROPERTY_REJECT, reject);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Emp_Loanlines> getRCHREmpLoanlinesList() {
        return (List<RCHR_Emp_Loanlines>) get(PROPERTY_RCHREMPLOANLINESLIST);
    }

    public void setRCHREmpLoanlinesList(List<RCHR_Emp_Loanlines> rCHREmpLoanlinesList) {
        set(PROPERTY_RCHREMPLOANLINESLIST, rCHREmpLoanlinesList);
    }

}
