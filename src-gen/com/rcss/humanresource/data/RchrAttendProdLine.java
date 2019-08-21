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
 * Entity class for entity Rchr_Attend_Prod_Line (stored in table Rchr_Attend_Prod_Line).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrAttendProdLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Attend_Prod_Line";
    public static final String ENTITY_NAME = "Rchr_Attend_Prod_Line";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRATTENDANCEPROOD = "rchrAttendanceProod";
    public static final String PROPERTY_PUNCHIN = "punchIn";
    public static final String PROPERTY_PUNCHOUT = "punchOut";
    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_PRESENT = "present";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_LATE = "late";
    public static final String PROPERTY_WEEKLYOFF = "weeklyOff";
    public static final String PROPERTY_OVERTIME = "overtime";
    public static final String PROPERTY_EMPID = "empid";
    public static final String PROPERTY_DEPTNAME = "deptname";
    public static final String PROPERTY_GOVTATTENDANCE = "govtAttendance";
    public static final String PROPERTY_RCHROVERTIMEPROCESSLINELIST = "rCHROvertimeprocessLineList";

    public RchrAttendProdLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PRESENT, true);
        setDefaultValue(PROPERTY_LATE, false);
        setDefaultValue(PROPERTY_WEEKLYOFF, false);
        setDefaultValue(PROPERTY_OVERTIME, false);
        setDefaultValue(PROPERTY_GOVTATTENDANCE, false);
        setDefaultValue(PROPERTY_RCHROVERTIMEPROCESSLINELIST, new ArrayList<Object>());
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

    public RchrAttendanceProod getRchrAttendanceProod() {
        return (RchrAttendanceProod) get(PROPERTY_RCHRATTENDANCEPROOD);
    }

    public void setRchrAttendanceProod(RchrAttendanceProod rchrAttendanceProod) {
        set(PROPERTY_RCHRATTENDANCEPROOD, rchrAttendanceProod);
    }

    public Date getPunchIn() {
        return (Date) get(PROPERTY_PUNCHIN);
    }

    public void setPunchIn(Date punchIn) {
        set(PROPERTY_PUNCHIN, punchIn);
    }

    public Date getPunchOut() {
        return (Date) get(PROPERTY_PUNCHOUT);
    }

    public void setPunchOut(Date punchOut) {
        set(PROPERTY_PUNCHOUT, punchOut);
    }

    public String getDuration() {
        return (String) get(PROPERTY_DURATION);
    }

    public void setDuration(String duration) {
        set(PROPERTY_DURATION, duration);
    }

    public Boolean isPresent() {
        return (Boolean) get(PROPERTY_PRESENT);
    }

    public void setPresent(Boolean present) {
        set(PROPERTY_PRESENT, present);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Boolean isLate() {
        return (Boolean) get(PROPERTY_LATE);
    }

    public void setLate(Boolean late) {
        set(PROPERTY_LATE, late);
    }

    public Boolean isWeeklyOff() {
        return (Boolean) get(PROPERTY_WEEKLYOFF);
    }

    public void setWeeklyOff(Boolean weeklyOff) {
        set(PROPERTY_WEEKLYOFF, weeklyOff);
    }

    public Boolean isOvertime() {
        return (Boolean) get(PROPERTY_OVERTIME);
    }

    public void setOvertime(Boolean overtime) {
        set(PROPERTY_OVERTIME, overtime);
    }

    public String getEmpid() {
        return (String) get(PROPERTY_EMPID);
    }

    public void setEmpid(String empid) {
        set(PROPERTY_EMPID, empid);
    }

    public String getDeptname() {
        return (String) get(PROPERTY_DEPTNAME);
    }

    public void setDeptname(String deptname) {
        set(PROPERTY_DEPTNAME, deptname);
    }

    public Boolean isGovtAttendance() {
        return (Boolean) get(PROPERTY_GOVTATTENDANCE);
    }

    public void setGovtAttendance(Boolean govtAttendance) {
        set(PROPERTY_GOVTATTENDANCE, govtAttendance);
    }

    @SuppressWarnings("unchecked")
    public List<RchrOvertimeprocessLine> getRCHROvertimeprocessLineList() {
        return (List<RchrOvertimeprocessLine>) get(PROPERTY_RCHROVERTIMEPROCESSLINELIST);
    }

    public void setRCHROvertimeprocessLineList(List<RchrOvertimeprocessLine> rCHROvertimeprocessLineList) {
        set(PROPERTY_RCHROVERTIMEPROCESSLINELIST, rCHROvertimeprocessLineList);
    }

}
