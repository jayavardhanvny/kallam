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

import com.redcarpet.payroll.data.RCPL_PayrollTemplate;

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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Rchr_Join_Rejoiningform (stored in table Rchr_Join_Rejoiningform).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrJoinRejoiningform extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Join_Rejoiningform";
    public static final String ENTITY_NAME = "Rchr_Join_Rejoiningform";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_JOINTYPE = "jointype";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_LEAVINGDATE = "leavingdate";
    public static final String PROPERTY_SALARY = "salary";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_EMPLOYEENAME = "employeeName";
    public static final String PROPERTY_PUNCHNO = "punchno";
    public static final String PROPERTY_EMPLOYEETYPE = "employeeType";
    public static final String PROPERTY_DATEOFBIRTH = "dateOfBirth";
    public static final String PROPERTY_WEEKOFFAPPLICABLE = "weekOffApplicable";
    public static final String PROPERTY_WEEKLYOFF = "weeklyOff";
    public static final String PROPERTY_GENDER = "gender";
    public static final String PROPERTY_MARITALSTATUS = "maritalStatus";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_ISODAPPLICABLE = "isodapplicable";
    public static final String PROPERTY_ISCOFFAPPLICABLE = "iscoffapplicable";
    public static final String PROPERTY_ISLEAVEAPPLICABLE = "isleaveapplicable";
    public static final String PROPERTY_SUBDEPARTMENT = "subDepartment";
    public static final String PROPERTY_DECLARESERVICEDAYS = "declareservicedays";
    public static final String PROPERTY_BASIC = "basic";
    public static final String PROPERTY_SERVICEINCENTIVE = "serviceincentive";
    public static final String PROPERTY_COMPLETE = "complete";
    public static final String PROPERTY_ISSENIOR = "issenior";
    public static final String PROPERTY_EMPLOYEEID = "employeeid";
    public static final String PROPERTY_CL = "cl";
    public static final String PROPERTY_EL = "el";
    public static final String PROPERTY_RCPLPAYROLLTEMPLATE = "rcplPayrolltemplate";
    public static final String PROPERTY_RCHRDESIGBASIC = "rchrDesigbasic";
    public static final String PROPERTY_RCHRMOBALIZER = "rchrMobalizer";
    public static final String PROPERTY_PREVIOUSSERVICEDAYS = "previousservicedays";
    public static final String PROPERTY_RCHRSHIFTGROUP = "rchrShiftgroup";
    public static final String PROPERTY_RELAYNAME = "relayName";
    public static final String PROPERTY_PREVIOUSJOINDATE = "previousjoindate";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";

    public RchrJoinRejoiningform() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_JOINTYPE, "J");
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_WEEKOFFAPPLICABLE, false);
        setDefaultValue(PROPERTY_WEEKLYOFF, "N/A");
        setDefaultValue(PROPERTY_ISODAPPLICABLE, false);
        setDefaultValue(PROPERTY_ISCOFFAPPLICABLE, false);
        setDefaultValue(PROPERTY_ISLEAVEAPPLICABLE, false);
        setDefaultValue(PROPERTY_DECLARESERVICEDAYS, new BigDecimal(0));
        setDefaultValue(PROPERTY_BASIC, new BigDecimal(0));
        setDefaultValue(PROPERTY_SERVICEINCENTIVE, new BigDecimal(0));
        setDefaultValue(PROPERTY_COMPLETE, false);
        setDefaultValue(PROPERTY_ISSENIOR, false);
        setDefaultValue(PROPERTY_CL, (long) 0);
        setDefaultValue(PROPERTY_EL, (long) 0);
        setDefaultValue(PROPERTY_PREVIOUSSERVICEDAYS, (long) 0);
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
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

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public String getJointype() {
        return (String) get(PROPERTY_JOINTYPE);
    }

    public void setJointype(String jointype) {
        set(PROPERTY_JOINTYPE, jointype);
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

    public Date getLeavingdate() {
        return (Date) get(PROPERTY_LEAVINGDATE);
    }

    public void setLeavingdate(Date leavingdate) {
        set(PROPERTY_LEAVINGDATE, leavingdate);
    }

    public BigDecimal getSalary() {
        return (BigDecimal) get(PROPERTY_SALARY);
    }

    public void setSalary(BigDecimal salary) {
        set(PROPERTY_SALARY, salary);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
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

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public String getEmployeeName() {
        return (String) get(PROPERTY_EMPLOYEENAME);
    }

    public void setEmployeeName(String employeeName) {
        set(PROPERTY_EMPLOYEENAME, employeeName);
    }

    public String getPunchno() {
        return (String) get(PROPERTY_PUNCHNO);
    }

    public void setPunchno(String punchno) {
        set(PROPERTY_PUNCHNO, punchno);
    }

    public String getEmployeeType() {
        return (String) get(PROPERTY_EMPLOYEETYPE);
    }

    public void setEmployeeType(String employeeType) {
        set(PROPERTY_EMPLOYEETYPE, employeeType);
    }

    public Date getDateOfBirth() {
        return (Date) get(PROPERTY_DATEOFBIRTH);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        set(PROPERTY_DATEOFBIRTH, dateOfBirth);
    }

    public Boolean isWeekOffApplicable() {
        return (Boolean) get(PROPERTY_WEEKOFFAPPLICABLE);
    }

    public void setWeekOffApplicable(Boolean weekOffApplicable) {
        set(PROPERTY_WEEKOFFAPPLICABLE, weekOffApplicable);
    }

    public String getWeeklyOff() {
        return (String) get(PROPERTY_WEEKLYOFF);
    }

    public void setWeeklyOff(String weeklyOff) {
        set(PROPERTY_WEEKLYOFF, weeklyOff);
    }

    public String getGender() {
        return (String) get(PROPERTY_GENDER);
    }

    public void setGender(String gender) {
        set(PROPERTY_GENDER, gender);
    }

    public String getMaritalStatus() {
        return (String) get(PROPERTY_MARITALSTATUS);
    }

    public void setMaritalStatus(String maritalStatus) {
        set(PROPERTY_MARITALSTATUS, maritalStatus);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public Boolean isOdapplicable() {
        return (Boolean) get(PROPERTY_ISODAPPLICABLE);
    }

    public void setOdapplicable(Boolean isodapplicable) {
        set(PROPERTY_ISODAPPLICABLE, isodapplicable);
    }

    public Boolean isCoffapplicable() {
        return (Boolean) get(PROPERTY_ISCOFFAPPLICABLE);
    }

    public void setCoffapplicable(Boolean iscoffapplicable) {
        set(PROPERTY_ISCOFFAPPLICABLE, iscoffapplicable);
    }

    public Boolean isLeaveapplicable() {
        return (Boolean) get(PROPERTY_ISLEAVEAPPLICABLE);
    }

    public void setLeaveapplicable(Boolean isleaveapplicable) {
        set(PROPERTY_ISLEAVEAPPLICABLE, isleaveapplicable);
    }

    public RCHR_SubDepartment getSubDepartment() {
        return (RCHR_SubDepartment) get(PROPERTY_SUBDEPARTMENT);
    }

    public void setSubDepartment(RCHR_SubDepartment subDepartment) {
        set(PROPERTY_SUBDEPARTMENT, subDepartment);
    }

    public BigDecimal getDeclareservicedays() {
        return (BigDecimal) get(PROPERTY_DECLARESERVICEDAYS);
    }

    public void setDeclareservicedays(BigDecimal declareservicedays) {
        set(PROPERTY_DECLARESERVICEDAYS, declareservicedays);
    }

    public BigDecimal getBasic() {
        return (BigDecimal) get(PROPERTY_BASIC);
    }

    public void setBasic(BigDecimal basic) {
        set(PROPERTY_BASIC, basic);
    }

    public BigDecimal getServiceincentive() {
        return (BigDecimal) get(PROPERTY_SERVICEINCENTIVE);
    }

    public void setServiceincentive(BigDecimal serviceincentive) {
        set(PROPERTY_SERVICEINCENTIVE, serviceincentive);
    }

    public Boolean isComplete() {
        return (Boolean) get(PROPERTY_COMPLETE);
    }

    public void setComplete(Boolean complete) {
        set(PROPERTY_COMPLETE, complete);
    }

    public Boolean isSenior() {
        return (Boolean) get(PROPERTY_ISSENIOR);
    }

    public void setSenior(Boolean issenior) {
        set(PROPERTY_ISSENIOR, issenior);
    }

    public String getEmployeeid() {
        return (String) get(PROPERTY_EMPLOYEEID);
    }

    public void setEmployeeid(String employeeid) {
        set(PROPERTY_EMPLOYEEID, employeeid);
    }

    public Long getCl() {
        return (Long) get(PROPERTY_CL);
    }

    public void setCl(Long cl) {
        set(PROPERTY_CL, cl);
    }

    public Long getEl() {
        return (Long) get(PROPERTY_EL);
    }

    public void setEl(Long el) {
        set(PROPERTY_EL, el);
    }

    public RCPL_PayrollTemplate getRcplPayrolltemplate() {
        return (RCPL_PayrollTemplate) get(PROPERTY_RCPLPAYROLLTEMPLATE);
    }

    public void setRcplPayrolltemplate(RCPL_PayrollTemplate rcplPayrolltemplate) {
        set(PROPERTY_RCPLPAYROLLTEMPLATE, rcplPayrolltemplate);
    }

    public RCHRDesigBasic getRchrDesigbasic() {
        return (RCHRDesigBasic) get(PROPERTY_RCHRDESIGBASIC);
    }

    public void setRchrDesigbasic(RCHRDesigBasic rchrDesigbasic) {
        set(PROPERTY_RCHRDESIGBASIC, rchrDesigbasic);
    }

    public RCHRMobalizer getRchrMobalizer() {
        return (RCHRMobalizer) get(PROPERTY_RCHRMOBALIZER);
    }

    public void setRchrMobalizer(RCHRMobalizer rchrMobalizer) {
        set(PROPERTY_RCHRMOBALIZER, rchrMobalizer);
    }

    public Long getPreviousservicedays() {
        return (Long) get(PROPERTY_PREVIOUSSERVICEDAYS);
    }

    public void setPreviousservicedays(Long previousservicedays) {
        set(PROPERTY_PREVIOUSSERVICEDAYS, previousservicedays);
    }

    public RchrShiftgroup getRchrShiftgroup() {
        return (RchrShiftgroup) get(PROPERTY_RCHRSHIFTGROUP);
    }

    public void setRchrShiftgroup(RchrShiftgroup rchrShiftgroup) {
        set(PROPERTY_RCHRSHIFTGROUP, rchrShiftgroup);
    }

    public RchrMRelay getRelayName() {
        return (RchrMRelay) get(PROPERTY_RELAYNAME);
    }

    public void setRelayName(RchrMRelay relayName) {
        set(PROPERTY_RELAYNAME, relayName);
    }

    public Date getPreviousjoindate() {
        return (Date) get(PROPERTY_PREVIOUSJOINDATE);
    }

    public void setPreviousjoindate(Date previousjoindate) {
        set(PROPERTY_PREVIOUSJOINDATE, previousjoindate);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployee> getRchrEmployeeList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEELIST);
    }

    public void setRchrEmployeeList(List<RchrEmployee> rchrEmployeeList) {
        set(PROPERTY_RCHREMPLOYEELIST, rchrEmployeeList);
    }

}
