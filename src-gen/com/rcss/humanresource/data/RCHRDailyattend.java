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

import com.redcarpet.payroll.data.RcplEmppprocessmanual;

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
 * Entity class for entity RCHR_Dailyattend (stored in table RCHR_Dailyattend).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRDailyattend extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Dailyattend";
    public static final String ENTITY_NAME = "RCHR_Dailyattend";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ATTNDLOGSID = "attndlogsid";
    public static final String PROPERTY_ATTENDANCEDATE = "attendanceDate";
    public static final String PROPERTY_PUNCHIN = "punchIn";
    public static final String PROPERTY_PUNCHOUT = "punchOut";
    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_PRESENT = "present";
    public static final String PROPERTY_EMPLOYEEID = "employeeId";
    public static final String PROPERTY_LATE = "late";
    public static final String PROPERTY_WEEKLYOFF = "weeklyOff";
    public static final String PROPERTY_SHIFTCHANGEABSENT = "shiftChangeAbsent";
    public static final String PROPERTY_OVERTIME = "overtime";
    public static final String PROPERTY_NOWORK = "noWork";
    public static final String PROPERTY_NIGHTSHIFT = "nightShift";
    public static final String PROPERTY_SHIFT = "shift";
    public static final String PROPERTY_LATETIME = "lateTime";
    public static final String PROPERTY_ERRORLOG = "errorLog";
    public static final String PROPERTY_VALIDATE = "validate";
    public static final String PROPERTY_VALIDATION = "validation";
    public static final String PROPERTY_IMPORTIT = "importit";
    public static final String PROPERTY_VALIDATED = "validated";
    public static final String PROPERTY_ISIMPORTED = "isImported";
    public static final String PROPERTY_ISFLAG = "isflag";
    public static final String PROPERTY_BREAKOUT = "breakout";
    public static final String PROPERTY_BREAKIN = "breakin";
    public static final String PROPERTY_COUNT = "count";
    public static final String PROPERTY_DIRECTION = "direction";
    public static final String PROPERTY_DEVICENAME = "devicename";
    public static final String PROPERTY_EMPLOYEENAME = "employeeName";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_ISGRIEVANCE = "isgrievance";
    public static final String PROPERTY_RCPLEMPPPROCESSMANUAL = "rcplEmppprocessmanual";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_ISLEAVE = "isleave";
    public static final String PROPERTY_DAYTYPE = "daytype";
    public static final String PROPERTY_DIFFDURATION = "diffduration";
    public static final String PROPERTY_FORECASTEDSHIFT = "forecastedshift";
    public static final String PROPERTY_RCHRDAILYATTENDDEMOLIST = "rCHRDailyattenddemoList";
    public static final String PROPERTY_RCPLEMPPPROCESSMANUALLIST = "rcplEmppprocessmanualList";

    public RCHRDailyattend() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PRESENT, true);
        setDefaultValue(PROPERTY_LATE, false);
        setDefaultValue(PROPERTY_WEEKLYOFF, false);
        setDefaultValue(PROPERTY_SHIFTCHANGEABSENT, false);
        setDefaultValue(PROPERTY_OVERTIME, false);
        setDefaultValue(PROPERTY_NOWORK, false);
        setDefaultValue(PROPERTY_NIGHTSHIFT, false);
        setDefaultValue(PROPERTY_VALIDATE, false);
        setDefaultValue(PROPERTY_VALIDATION, false);
        setDefaultValue(PROPERTY_IMPORTIT, false);
        setDefaultValue(PROPERTY_VALIDATED, false);
        setDefaultValue(PROPERTY_ISIMPORTED, false);
        setDefaultValue(PROPERTY_ISFLAG, false);
        setDefaultValue(PROPERTY_ISGRIEVANCE, false);
        setDefaultValue(PROPERTY_ISLEAVE, false);
        setDefaultValue(PROPERTY_RCHRDAILYATTENDDEMOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPPROCESSMANUALLIST, new ArrayList<Object>());
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

    public String getAttndlogsid() {
        return (String) get(PROPERTY_ATTNDLOGSID);
    }

    public void setAttndlogsid(String attndlogsid) {
        set(PROPERTY_ATTNDLOGSID, attndlogsid);
    }

    public Date getAttendanceDate() {
        return (Date) get(PROPERTY_ATTENDANCEDATE);
    }

    public void setAttendanceDate(Date attendanceDate) {
        set(PROPERTY_ATTENDANCEDATE, attendanceDate);
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

    public String getEmployeeId() {
        return (String) get(PROPERTY_EMPLOYEEID);
    }

    public void setEmployeeId(String employeeId) {
        set(PROPERTY_EMPLOYEEID, employeeId);
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

    public Boolean isShiftChangeAbsent() {
        return (Boolean) get(PROPERTY_SHIFTCHANGEABSENT);
    }

    public void setShiftChangeAbsent(Boolean shiftChangeAbsent) {
        set(PROPERTY_SHIFTCHANGEABSENT, shiftChangeAbsent);
    }

    public Boolean isOvertime() {
        return (Boolean) get(PROPERTY_OVERTIME);
    }

    public void setOvertime(Boolean overtime) {
        set(PROPERTY_OVERTIME, overtime);
    }

    public Boolean isNoWork() {
        return (Boolean) get(PROPERTY_NOWORK);
    }

    public void setNoWork(Boolean noWork) {
        set(PROPERTY_NOWORK, noWork);
    }

    public Boolean isNightShift() {
        return (Boolean) get(PROPERTY_NIGHTSHIFT);
    }

    public void setNightShift(Boolean nightShift) {
        set(PROPERTY_NIGHTSHIFT, nightShift);
    }

    public String getShift() {
        return (String) get(PROPERTY_SHIFT);
    }

    public void setShift(String shift) {
        set(PROPERTY_SHIFT, shift);
    }

    public Long getLateTime() {
        return (Long) get(PROPERTY_LATETIME);
    }

    public void setLateTime(Long lateTime) {
        set(PROPERTY_LATETIME, lateTime);
    }

    public String getErrorLog() {
        return (String) get(PROPERTY_ERRORLOG);
    }

    public void setErrorLog(String errorLog) {
        set(PROPERTY_ERRORLOG, errorLog);
    }

    public Boolean isValidate() {
        return (Boolean) get(PROPERTY_VALIDATE);
    }

    public void setValidate(Boolean validate) {
        set(PROPERTY_VALIDATE, validate);
    }

    public Boolean isValidation() {
        return (Boolean) get(PROPERTY_VALIDATION);
    }

    public void setValidation(Boolean validation) {
        set(PROPERTY_VALIDATION, validation);
    }

    public Boolean isImportit() {
        return (Boolean) get(PROPERTY_IMPORTIT);
    }

    public void setImportit(Boolean importit) {
        set(PROPERTY_IMPORTIT, importit);
    }

    public Boolean isValidated() {
        return (Boolean) get(PROPERTY_VALIDATED);
    }

    public void setValidated(Boolean validated) {
        set(PROPERTY_VALIDATED, validated);
    }

    public Boolean isImported() {
        return (Boolean) get(PROPERTY_ISIMPORTED);
    }

    public void setImported(Boolean isImported) {
        set(PROPERTY_ISIMPORTED, isImported);
    }

    public Boolean isFlag() {
        return (Boolean) get(PROPERTY_ISFLAG);
    }

    public void setFlag(Boolean isflag) {
        set(PROPERTY_ISFLAG, isflag);
    }

    public Date getBreakout() {
        return (Date) get(PROPERTY_BREAKOUT);
    }

    public void setBreakout(Date breakout) {
        set(PROPERTY_BREAKOUT, breakout);
    }

    public Date getBreakin() {
        return (Date) get(PROPERTY_BREAKIN);
    }

    public void setBreakin(Date breakin) {
        set(PROPERTY_BREAKIN, breakin);
    }

    public Long getCount() {
        return (Long) get(PROPERTY_COUNT);
    }

    public void setCount(Long count) {
        set(PROPERTY_COUNT, count);
    }

    public String getDirection() {
        return (String) get(PROPERTY_DIRECTION);
    }

    public void setDirection(String direction) {
        set(PROPERTY_DIRECTION, direction);
    }

    public String getDevicename() {
        return (String) get(PROPERTY_DEVICENAME);
    }

    public void setDevicename(String devicename) {
        set(PROPERTY_DEVICENAME, devicename);
    }

    public String getEmployeeName() {
        return (String) get(PROPERTY_EMPLOYEENAME);
    }

    public void setEmployeeName(String employeeName) {
        set(PROPERTY_EMPLOYEENAME, employeeName);
    }

    public String getReasonForLeave() {
        return (String) get(PROPERTY_REASONFORLEAVE);
    }

    public void setReasonForLeave(String reasonForLeave) {
        set(PROPERTY_REASONFORLEAVE, reasonForLeave);
    }

    public Boolean isGrievance() {
        return (Boolean) get(PROPERTY_ISGRIEVANCE);
    }

    public void setGrievance(Boolean isgrievance) {
        set(PROPERTY_ISGRIEVANCE, isgrievance);
    }

    public RcplEmppprocessmanual getRcplEmppprocessmanual() {
        return (RcplEmppprocessmanual) get(PROPERTY_RCPLEMPPPROCESSMANUAL);
    }

    public void setRcplEmppprocessmanual(RcplEmppprocessmanual rcplEmppprocessmanual) {
        set(PROPERTY_RCPLEMPPPROCESSMANUAL, rcplEmppprocessmanual);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Boolean isLeave() {
        return (Boolean) get(PROPERTY_ISLEAVE);
    }

    public void setLeave(Boolean isleave) {
        set(PROPERTY_ISLEAVE, isleave);
    }

    public String getDaytype() {
        return (String) get(PROPERTY_DAYTYPE);
    }

    public void setDaytype(String daytype) {
        set(PROPERTY_DAYTYPE, daytype);
    }

    public Long getDiffduration() {
        return (Long) get(PROPERTY_DIFFDURATION);
    }

    public void setDiffduration(Long diffduration) {
        set(PROPERTY_DIFFDURATION, diffduration);
    }

    public String getForecastedshift() {
        return (String) get(PROPERTY_FORECASTEDSHIFT);
    }

    public void setForecastedshift(String forecastedshift) {
        set(PROPERTY_FORECASTEDSHIFT, forecastedshift);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRDailyattenddemo> getRCHRDailyattenddemoList() {
        return (List<RCHRDailyattenddemo>) get(PROPERTY_RCHRDAILYATTENDDEMOLIST);
    }

    public void setRCHRDailyattenddemoList(List<RCHRDailyattenddemo> rCHRDailyattenddemoList) {
        set(PROPERTY_RCHRDAILYATTENDDEMOLIST, rCHRDailyattenddemoList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplEmppprocessmanual> getRcplEmppprocessmanualList() {
        return (List<RcplEmppprocessmanual>) get(PROPERTY_RCPLEMPPPROCESSMANUALLIST);
    }

    public void setRcplEmppprocessmanualList(List<RcplEmppprocessmanual> rcplEmppprocessmanualList) {
        set(PROPERTY_RCPLEMPPPROCESSMANUALLIST, rcplEmppprocessmanualList);
    }

}
