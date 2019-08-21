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

import com.redcarpet.production.data.RCPRShift;

import java.sql.Timestamp;
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
 * Entity class for entity RCHR_Overtimeprocess_Line (stored in table RCHR_Overtimeprocess_Line).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrOvertimeprocessLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Overtimeprocess_Line";
    public static final String ENTITY_NAME = "RCHR_Overtimeprocess_Line";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHROVERTIMEPROCESS = "rchrOvertimeprocess";
    public static final String PROPERTY_PUNCHIN = "punchIn";
    public static final String PROPERTY_PUNCHOUT = "punchOut";
    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_PRESENT = "present";
    public static final String PROPERTY_LATE = "late";
    public static final String PROPERTY_WEEKLYOFF = "weeklyOff";
    public static final String PROPERTY_SHIFTCHANGEABSENT = "shiftChangeAbsent";
    public static final String PROPERTY_OVERTIME = "overtime";
    public static final String PROPERTY_MANUAL = "manual";
    public static final String PROPERTY_NIGHTSHIFT = "nightShift";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_LATETIME = "lateTime";
    public static final String PROPERTY_INCENTIVE = "incentive";
    public static final String PROPERTY_LOSTDURATION = "lostduration";
    public static final String PROPERTY_ISCONTINUESHIFT = "iscontinueshift";
    public static final String PROPERTY_IMPORTATTENDANCE = "importAttendance";
    public static final String PROPERTY_HEADDATE = "headdate";
    public static final String PROPERTY_RCHRDAILYATTENDDEMO = "rchrDailyattenddemo";
    public static final String PROPERTY_ISCHECKED = "ischecked";
    public static final String PROPERTY_ISPROCESSED = "isprocessed";
    public static final String PROPERTY_RCHRATTENDPRODLINE = "rchrAttendProdLine";
    public static final String PROPERTY_RCHRATTENDANCETOLINE = "rchrAttendanceToLine";
    public static final String PROPERTY_RCHROVERTIMEDATESLIST = "rchrOvertimedatesList";

    public RchrOvertimeprocessLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PRESENT, true);
        setDefaultValue(PROPERTY_LATE, false);
        setDefaultValue(PROPERTY_WEEKLYOFF, false);
        setDefaultValue(PROPERTY_SHIFTCHANGEABSENT, false);
        setDefaultValue(PROPERTY_OVERTIME, false);
        setDefaultValue(PROPERTY_MANUAL, false);
        setDefaultValue(PROPERTY_NIGHTSHIFT, false);
        setDefaultValue(PROPERTY_INCENTIVE, false);
        setDefaultValue(PROPERTY_LOSTDURATION, "0");
        setDefaultValue(PROPERTY_ISCONTINUESHIFT, false);
        setDefaultValue(PROPERTY_ISCHECKED, false);
        setDefaultValue(PROPERTY_ISPROCESSED, false);
        setDefaultValue(PROPERTY_RCHROVERTIMEDATESLIST, new ArrayList<Object>());
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

    public RchrOvertimeprocess getRchrOvertimeprocess() {
        return (RchrOvertimeprocess) get(PROPERTY_RCHROVERTIMEPROCESS);
    }

    public void setRchrOvertimeprocess(RchrOvertimeprocess rchrOvertimeprocess) {
        set(PROPERTY_RCHROVERTIMEPROCESS, rchrOvertimeprocess);
    }

    public Timestamp getPunchIn() {
        return (Timestamp) get(PROPERTY_PUNCHIN);
    }

    public void setPunchIn(Timestamp punchIn) {
        set(PROPERTY_PUNCHIN, punchIn);
    }

    public Timestamp getPunchOut() {
        return (Timestamp) get(PROPERTY_PUNCHOUT);
    }

    public void setPunchOut(Timestamp punchOut) {
        set(PROPERTY_PUNCHOUT, punchOut);
    }

    public String getDuration() {
        return (String) get(PROPERTY_DURATION);
    }

    public void setDuration(String duration) {
        set(PROPERTY_DURATION, duration);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Boolean isPresent() {
        return (Boolean) get(PROPERTY_PRESENT);
    }

    public void setPresent(Boolean present) {
        set(PROPERTY_PRESENT, present);
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

    public Boolean isManual() {
        return (Boolean) get(PROPERTY_MANUAL);
    }

    public void setManual(Boolean manual) {
        set(PROPERTY_MANUAL, manual);
    }

    public Boolean isNightShift() {
        return (Boolean) get(PROPERTY_NIGHTSHIFT);
    }

    public void setNightShift(Boolean nightShift) {
        set(PROPERTY_NIGHTSHIFT, nightShift);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public Long getLateTime() {
        return (Long) get(PROPERTY_LATETIME);
    }

    public void setLateTime(Long lateTime) {
        set(PROPERTY_LATETIME, lateTime);
    }

    public Boolean isIncentive() {
        return (Boolean) get(PROPERTY_INCENTIVE);
    }

    public void setIncentive(Boolean incentive) {
        set(PROPERTY_INCENTIVE, incentive);
    }

    public String getLostduration() {
        return (String) get(PROPERTY_LOSTDURATION);
    }

    public void setLostduration(String lostduration) {
        set(PROPERTY_LOSTDURATION, lostduration);
    }

    public Boolean isContinueshift() {
        return (Boolean) get(PROPERTY_ISCONTINUESHIFT);
    }

    public void setContinueshift(Boolean iscontinueshift) {
        set(PROPERTY_ISCONTINUESHIFT, iscontinueshift);
    }

    public RCHR_Attend_Temp getImportAttendance() {
        return (RCHR_Attend_Temp) get(PROPERTY_IMPORTATTENDANCE);
    }

    public void setImportAttendance(RCHR_Attend_Temp importAttendance) {
        set(PROPERTY_IMPORTATTENDANCE, importAttendance);
    }

    public Date getHeaddate() {
        return (Date) get(PROPERTY_HEADDATE);
    }

    public void setHeaddate(Date headdate) {
        set(PROPERTY_HEADDATE, headdate);
    }

    public RCHRDailyattenddemo getRchrDailyattenddemo() {
        return (RCHRDailyattenddemo) get(PROPERTY_RCHRDAILYATTENDDEMO);
    }

    public void setRchrDailyattenddemo(RCHRDailyattenddemo rchrDailyattenddemo) {
        set(PROPERTY_RCHRDAILYATTENDDEMO, rchrDailyattenddemo);
    }

    public Boolean isChecked() {
        return (Boolean) get(PROPERTY_ISCHECKED);
    }

    public void setChecked(Boolean ischecked) {
        set(PROPERTY_ISCHECKED, ischecked);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_ISPROCESSED);
    }

    public void setProcessed(Boolean isprocessed) {
        set(PROPERTY_ISPROCESSED, isprocessed);
    }

    public RchrAttendProdLine getRchrAttendProdLine() {
        return (RchrAttendProdLine) get(PROPERTY_RCHRATTENDPRODLINE);
    }

    public void setRchrAttendProdLine(RchrAttendProdLine rchrAttendProdLine) {
        set(PROPERTY_RCHRATTENDPRODLINE, rchrAttendProdLine);
    }

    public RchrAttendanceToLine getRchrAttendanceToLine() {
        return (RchrAttendanceToLine) get(PROPERTY_RCHRATTENDANCETOLINE);
    }

    public void setRchrAttendanceToLine(RchrAttendanceToLine rchrAttendanceToLine) {
        set(PROPERTY_RCHRATTENDANCETOLINE, rchrAttendanceToLine);
    }

    @SuppressWarnings("unchecked")
    public List<RchrOvertimedates> getRchrOvertimedatesList() {
        return (List<RchrOvertimedates>) get(PROPERTY_RCHROVERTIMEDATESLIST);
    }

    public void setRchrOvertimedatesList(List<RchrOvertimedates> rchrOvertimedatesList) {
        set(PROPERTY_RCHROVERTIMEDATESLIST, rchrOvertimedatesList);
    }

}
