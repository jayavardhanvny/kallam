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
package com.redcarpet.production.data;

import com.rcss.humanresource.data.RCHRDailyattenddemo;
import com.rcss.humanresource.data.RCHREmployeecoffslines;
import com.rcss.humanresource.data.RCHRPermission;
import com.rcss.humanresource.data.RCHR_Attend_Temp;
import com.rcss.humanresource.data.RCHR_Directwarp;
import com.rcss.humanresource.data.RCHR_Dirwarp_Beam;
import com.rcss.humanresource.data.RCHR_Inspectionsheet;
import com.rcss.humanresource.data.RCHR_OverTime;
import com.rcss.humanresource.data.RCHR_Sizing_Beam;
import com.rcss.humanresource.data.RCHR_Sizingsheet;
import com.rcss.humanresource.data.RchrAttendLine;
import com.rcss.humanresource.data.RchrAttendance;
import com.rcss.humanresource.data.RchrAttendanceProod;
import com.rcss.humanresource.data.RchrAttendanceTimeoff;
import com.rcss.humanresource.data.RchrAttendstrength;
import com.rcss.humanresource.data.RchrCompensateOff;
import com.rcss.humanresource.data.RchrMemoShift;
import com.rcss.humanresource.data.RchrOvertimedates;
import com.rcss.humanresource.data.RchrOvertimeprocess;
import com.rcss.humanresource.data.RchrOvertimeprocessLine;
import com.rcss.humanresource.data.RchrPreparatprodincntive;
import com.rcss.humanresource.data.RchrRelay;
import com.rcss.humanresource.data.RchrRollwisedata;
import com.rcss.humanresource.data.RchrShiftgroup;
import com.rcss.humanresource.data.RchrShiftlines;
import com.rcss.humanresource.data.RchrShifttwelve;
import com.redcarpet.meterreading.data.RcmrMainmeter;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPLProdIncentiveLine;
import com.redcarpet.payroll.data.RCPL_VariableLine;
import com.redcarpet.payroll.data.RcplEmppprocessmanual;
import com.redcarpet.payroll.data.RcplLoomsproduction;
import com.redcarpet.payroll.data.RcplLoomsproductionimport;

import java.math.BigDecimal;
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
 * Entity class for entity RCPR_Shift (stored in table RCPR_Shift).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPRShift extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Shift";
    public static final String ENTITY_NAME = "RCPR_Shift";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_FROMTIME = "fromTime";
    public static final String PROPERTY_TOTIME = "toTime";
    public static final String PROPERTY_SHIFTINMINS = "shiftInMins";
    public static final String PROPERTY_TIMEDIFFERENCE = "timeDifference";
    public static final String PROPERTY_PERMISSIONDAYS = "permissiondays";
    public static final String PROPERTY_INPUNCHGRACE = "inpunchgrace";
    public static final String PROPERTY_SHIFTTYPE = "shifttype";
    public static final String PROPERTY_BREAKSTARTTIME = "breakstarttime";
    public static final String PROPERTY_BREAKENDTIME = "breakendtime";
    public static final String PROPERTY_BREAKDURATION = "breakduration";
    public static final String PROPERTY_LATECONSIDERATION = "lateconsideration";
    public static final String PROPERTY_OTSHIFTDURATION = "otshiftduration";
    public static final String PROPERTY_MINSHIFTDURATION = "minshiftduration";
    public static final String PROPERTY_RCHRSHIFTGROUP = "rchrShiftgroup";
    public static final String PROPERTY_PUNCHBEGINBEFORE = "punchbeginbefore";
    public static final String PROPERTY_PUNCHENDAFTER = "punchendafter";
    public static final String PROPERTY_MINPUNCHESREQUIRED = "minpunchesrequired";
    public static final String PROPERTY_RCHRATTENDTEMPLIST = "rCHRAttendTempList";
    public static final String PROPERTY_RCHRCOMPENSATEOFFLIST = "rCHRCompensateOffList";
    public static final String PROPERTY_RCHRDAILYATTENDDEMOLIST = "rCHRDailyattenddemoList";
    public static final String PROPERTY_RCHRDIRECTWARPLIST = "rCHRDirectwarpList";
    public static final String PROPERTY_RCHRDIRWARPBEAMLIST = "rCHRDirwarpBeamList";
    public static final String PROPERTY_RCHREMPLOYEECOFFSLINESLIST = "rCHREmployeecoffslinesList";
    public static final String PROPERTY_RCHRINSPECTIONSHEETLIST = "rCHRInspectionsheetList";
    public static final String PROPERTY_RCHRINSPECTIONSHEETINSPSHIFTLIST = "rCHRInspectionsheetInspshiftList";
    public static final String PROPERTY_RCHROVERTIMELIST = "rCHROverTimeList";
    public static final String PROPERTY_RCHROVERTIMEPROCESSLIST = "rCHROvertimeprocessList";
    public static final String PROPERTY_RCHROVERTIMEPROCESSLINELIST = "rCHROvertimeprocessLineList";
    public static final String PROPERTY_RCHRPERMISSIONLIST = "rCHRPermissionList";
    public static final String PROPERTY_RCHRPREPARATPRODINCNTIVELIST = "rCHRPreparatprodincntiveList";
    public static final String PROPERTY_RCHRSIZINGBEAMLIST = "rCHRSizingBeamList";
    public static final String PROPERTY_RCHRSIZINGSHEETLIST = "rCHRSizingsheetList";
    public static final String PROPERTY_RCPLPRODINCENTIVELIST = "rCPLProdIncentiveList";
    public static final String PROPERTY_RCPLPRODINCENTIVELINELIST = "rCPLProdIncentiveLineList";
    public static final String PROPERTY_RCPLVARIABLELINELIST = "rCPLVariableLineList";
    public static final String PROPERTY_RCPRAUTOCLONELIST = "rCPRAutocloneList";
    public static final String PROPERTY_RCPRCARDINGLIST = "rCPRCardingList";
    public static final String PROPERTY_RCPRCOMBERSLIST = "rCPRCombersList";
    public static final String PROPERTY_RCPRDOUBLINGLIST = "rCPRDoublingList";
    public static final String PROPERTY_RCPRDRAWFRAMEBREAKERLIST = "rCPRDrawframebreakerList";
    public static final String PROPERTY_RCPRLAPFORMERLIST = "rCPRLapFormerList";
    public static final String PROPERTY_RCPROELIST = "rCPROeList";
    public static final String PROPERTY_RCPRPREVENTIVEMAINTENANCELIST = "rCPRPreventivemaintenanceList";
    public static final String PROPERTY_RCPRRSBDRAWFRAMELIST = "rCPRRSBDrawFrameList";
    public static final String PROPERTY_RCPRSIMPLEXLIST = "rCPRSimplexList";
    public static final String PROPERTY_RCPRSPINNINGLIST = "rCPRSpinningList";
    public static final String PROPERTY_RCPRTFOLIST = "rCPRTFOList";
    public static final String PROPERTY_RCHRATTENDLINELIST = "rchrAttendLineList";
    public static final String PROPERTY_RCHRATTENDANCELIST = "rchrAttendanceList";
    public static final String PROPERTY_RCHRATTENDANCEPROODLIST = "rchrAttendanceProodList";
    public static final String PROPERTY_RCHRATTENDANCETIMEOFFLIST = "rchrAttendanceTimeoffList";
    public static final String PROPERTY_RCHRATTENDSTRENGTHLIST = "rchrAttendstrengthList";
    public static final String PROPERTY_RCHRMEMOSHIFTLIST = "rchrMemoShiftList";
    public static final String PROPERTY_RCHRMEMOSHIFTNEWSHIFTLIST = "rchrMemoShiftNewshiftList";
    public static final String PROPERTY_RCHROVERTIMEDATESLIST = "rchrOvertimedatesList";
    public static final String PROPERTY_RCHRRELAYLIST = "rchrRelayList";
    public static final String PROPERTY_RCHRROLLWISEDATALIST = "rchrRollwisedataList";
    public static final String PROPERTY_RCHRSHIFTLINESLIST = "rchrShiftlinesList";
    public static final String PROPERTY_RCHRSHIFTTWELVELIST = "rchrShifttwelveList";
    public static final String PROPERTY_RCMRMAINMETERLIST = "rcmrMainmeterList";
    public static final String PROPERTY_RCPLEMPPPROCESSMANUALLIST = "rcplEmppprocessmanualList";
    public static final String PROPERTY_RCPLLOOMSPRODUCTIONLIST = "rcplLoomsproductionList";
    public static final String PROPERTY_RCPLLOOMSPRODUCTIONIMPORTLIST = "rcplLoomsproductionimportList";

    public RCPRShift() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PERMISSIONDAYS, (long) 0);
        setDefaultValue(PROPERTY_INPUNCHGRACE, (long) 0);
        setDefaultValue(PROPERTY_BREAKDURATION, new BigDecimal(0));
        setDefaultValue(PROPERTY_LATECONSIDERATION, new BigDecimal(0));
        setDefaultValue(PROPERTY_OTSHIFTDURATION, new BigDecimal(0));
        setDefaultValue(PROPERTY_MINSHIFTDURATION, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCHRATTENDTEMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRCOMPENSATEOFFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDAILYATTENDDEMOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRECTWARPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRWARPBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEECOFFSLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPECTIONSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPECTIONSHEETINSPSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHROVERTIMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHROVERTIMEPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHROVERTIMEPROCESSLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRPERMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRPREPARATPRODINCNTIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLVARIABLELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRAUTOCLONELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCARDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCOMBERSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRDOUBLINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRDRAWFRAMEBREAKERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRLAPFORMERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPROELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPREVENTIVEMAINTENANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRRSBDRAWFRAMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRSIMPLEXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRSPINNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRTFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDANCEPROODLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDANCETIMEOFFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDSTRENGTHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMEMOSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMEMOSHIFTNEWSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHROVERTIMEDATESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRRELAYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROLLWISEDATALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSHIFTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSHIFTTWELVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCMRMAINMETERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPPROCESSMANUALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLLOOMSPRODUCTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLLOOMSPRODUCTIONIMPORTLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Timestamp getFromTime() {
        return (Timestamp) get(PROPERTY_FROMTIME);
    }

    public void setFromTime(Timestamp fromTime) {
        set(PROPERTY_FROMTIME, fromTime);
    }

    public Timestamp getToTime() {
        return (Timestamp) get(PROPERTY_TOTIME);
    }

    public void setToTime(Timestamp toTime) {
        set(PROPERTY_TOTIME, toTime);
    }

    public BigDecimal getShiftInMins() {
        return (BigDecimal) get(PROPERTY_SHIFTINMINS);
    }

    public void setShiftInMins(BigDecimal shiftInMins) {
        set(PROPERTY_SHIFTINMINS, shiftInMins);
    }

    public String getTimeDifference() {
        return (String) get(PROPERTY_TIMEDIFFERENCE);
    }

    public void setTimeDifference(String timeDifference) {
        set(PROPERTY_TIMEDIFFERENCE, timeDifference);
    }

    public Long getPermissiondays() {
        return (Long) get(PROPERTY_PERMISSIONDAYS);
    }

    public void setPermissiondays(Long permissiondays) {
        set(PROPERTY_PERMISSIONDAYS, permissiondays);
    }

    public Long getInpunchgrace() {
        return (Long) get(PROPERTY_INPUNCHGRACE);
    }

    public void setInpunchgrace(Long inpunchgrace) {
        set(PROPERTY_INPUNCHGRACE, inpunchgrace);
    }

    public String getShifttype() {
        return (String) get(PROPERTY_SHIFTTYPE);
    }

    public void setShifttype(String shifttype) {
        set(PROPERTY_SHIFTTYPE, shifttype);
    }

    public Timestamp getBreakstarttime() {
        return (Timestamp) get(PROPERTY_BREAKSTARTTIME);
    }

    public void setBreakstarttime(Timestamp breakstarttime) {
        set(PROPERTY_BREAKSTARTTIME, breakstarttime);
    }

    public Timestamp getBreakendtime() {
        return (Timestamp) get(PROPERTY_BREAKENDTIME);
    }

    public void setBreakendtime(Timestamp breakendtime) {
        set(PROPERTY_BREAKENDTIME, breakendtime);
    }

    public BigDecimal getBreakduration() {
        return (BigDecimal) get(PROPERTY_BREAKDURATION);
    }

    public void setBreakduration(BigDecimal breakduration) {
        set(PROPERTY_BREAKDURATION, breakduration);
    }

    public BigDecimal getLateconsideration() {
        return (BigDecimal) get(PROPERTY_LATECONSIDERATION);
    }

    public void setLateconsideration(BigDecimal lateconsideration) {
        set(PROPERTY_LATECONSIDERATION, lateconsideration);
    }

    public BigDecimal getOtshiftduration() {
        return (BigDecimal) get(PROPERTY_OTSHIFTDURATION);
    }

    public void setOtshiftduration(BigDecimal otshiftduration) {
        set(PROPERTY_OTSHIFTDURATION, otshiftduration);
    }

    public BigDecimal getMinshiftduration() {
        return (BigDecimal) get(PROPERTY_MINSHIFTDURATION);
    }

    public void setMinshiftduration(BigDecimal minshiftduration) {
        set(PROPERTY_MINSHIFTDURATION, minshiftduration);
    }

    public RchrShiftgroup getRchrShiftgroup() {
        return (RchrShiftgroup) get(PROPERTY_RCHRSHIFTGROUP);
    }

    public void setRchrShiftgroup(RchrShiftgroup rchrShiftgroup) {
        set(PROPERTY_RCHRSHIFTGROUP, rchrShiftgroup);
    }

    public BigDecimal getPunchbeginbefore() {
        return (BigDecimal) get(PROPERTY_PUNCHBEGINBEFORE);
    }

    public void setPunchbeginbefore(BigDecimal punchbeginbefore) {
        set(PROPERTY_PUNCHBEGINBEFORE, punchbeginbefore);
    }

    public BigDecimal getPunchendafter() {
        return (BigDecimal) get(PROPERTY_PUNCHENDAFTER);
    }

    public void setPunchendafter(BigDecimal punchendafter) {
        set(PROPERTY_PUNCHENDAFTER, punchendafter);
    }

    public BigDecimal getMinpunchesrequired() {
        return (BigDecimal) get(PROPERTY_MINPUNCHESREQUIRED);
    }

    public void setMinpunchesrequired(BigDecimal minpunchesrequired) {
        set(PROPERTY_MINPUNCHESREQUIRED, minpunchesrequired);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Attend_Temp> getRCHRAttendTempList() {
        return (List<RCHR_Attend_Temp>) get(PROPERTY_RCHRATTENDTEMPLIST);
    }

    public void setRCHRAttendTempList(List<RCHR_Attend_Temp> rCHRAttendTempList) {
        set(PROPERTY_RCHRATTENDTEMPLIST, rCHRAttendTempList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrCompensateOff> getRCHRCompensateOffList() {
        return (List<RchrCompensateOff>) get(PROPERTY_RCHRCOMPENSATEOFFLIST);
    }

    public void setRCHRCompensateOffList(List<RchrCompensateOff> rCHRCompensateOffList) {
        set(PROPERTY_RCHRCOMPENSATEOFFLIST, rCHRCompensateOffList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRDailyattenddemo> getRCHRDailyattenddemoList() {
        return (List<RCHRDailyattenddemo>) get(PROPERTY_RCHRDAILYATTENDDEMOLIST);
    }

    public void setRCHRDailyattenddemoList(List<RCHRDailyattenddemo> rCHRDailyattenddemoList) {
        set(PROPERTY_RCHRDAILYATTENDDEMOLIST, rCHRDailyattenddemoList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Directwarp> getRCHRDirectwarpList() {
        return (List<RCHR_Directwarp>) get(PROPERTY_RCHRDIRECTWARPLIST);
    }

    public void setRCHRDirectwarpList(List<RCHR_Directwarp> rCHRDirectwarpList) {
        set(PROPERTY_RCHRDIRECTWARPLIST, rCHRDirectwarpList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Dirwarp_Beam> getRCHRDirwarpBeamList() {
        return (List<RCHR_Dirwarp_Beam>) get(PROPERTY_RCHRDIRWARPBEAMLIST);
    }

    public void setRCHRDirwarpBeamList(List<RCHR_Dirwarp_Beam> rCHRDirwarpBeamList) {
        set(PROPERTY_RCHRDIRWARPBEAMLIST, rCHRDirwarpBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHREmployeecoffslines> getRCHREmployeecoffslinesList() {
        return (List<RCHREmployeecoffslines>) get(PROPERTY_RCHREMPLOYEECOFFSLINESLIST);
    }

    public void setRCHREmployeecoffslinesList(List<RCHREmployeecoffslines> rCHREmployeecoffslinesList) {
        set(PROPERTY_RCHREMPLOYEECOFFSLINESLIST, rCHREmployeecoffslinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspectionsheet> getRCHRInspectionsheetList() {
        return (List<RCHR_Inspectionsheet>) get(PROPERTY_RCHRINSPECTIONSHEETLIST);
    }

    public void setRCHRInspectionsheetList(List<RCHR_Inspectionsheet> rCHRInspectionsheetList) {
        set(PROPERTY_RCHRINSPECTIONSHEETLIST, rCHRInspectionsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspectionsheet> getRCHRInspectionsheetInspshiftList() {
        return (List<RCHR_Inspectionsheet>) get(PROPERTY_RCHRINSPECTIONSHEETINSPSHIFTLIST);
    }

    public void setRCHRInspectionsheetInspshiftList(List<RCHR_Inspectionsheet> rCHRInspectionsheetInspshiftList) {
        set(PROPERTY_RCHRINSPECTIONSHEETINSPSHIFTLIST, rCHRInspectionsheetInspshiftList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_OverTime> getRCHROverTimeList() {
        return (List<RCHR_OverTime>) get(PROPERTY_RCHROVERTIMELIST);
    }

    public void setRCHROverTimeList(List<RCHR_OverTime> rCHROverTimeList) {
        set(PROPERTY_RCHROVERTIMELIST, rCHROverTimeList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrOvertimeprocess> getRCHROvertimeprocessList() {
        return (List<RchrOvertimeprocess>) get(PROPERTY_RCHROVERTIMEPROCESSLIST);
    }

    public void setRCHROvertimeprocessList(List<RchrOvertimeprocess> rCHROvertimeprocessList) {
        set(PROPERTY_RCHROVERTIMEPROCESSLIST, rCHROvertimeprocessList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrOvertimeprocessLine> getRCHROvertimeprocessLineList() {
        return (List<RchrOvertimeprocessLine>) get(PROPERTY_RCHROVERTIMEPROCESSLINELIST);
    }

    public void setRCHROvertimeprocessLineList(List<RchrOvertimeprocessLine> rCHROvertimeprocessLineList) {
        set(PROPERTY_RCHROVERTIMEPROCESSLINELIST, rCHROvertimeprocessLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRPermission> getRCHRPermissionList() {
        return (List<RCHRPermission>) get(PROPERTY_RCHRPERMISSIONLIST);
    }

    public void setRCHRPermissionList(List<RCHRPermission> rCHRPermissionList) {
        set(PROPERTY_RCHRPERMISSIONLIST, rCHRPermissionList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrPreparatprodincntive> getRCHRPreparatprodincntiveList() {
        return (List<RchrPreparatprodincntive>) get(PROPERTY_RCHRPREPARATPRODINCNTIVELIST);
    }

    public void setRCHRPreparatprodincntiveList(List<RchrPreparatprodincntive> rCHRPreparatprodincntiveList) {
        set(PROPERTY_RCHRPREPARATPRODINCNTIVELIST, rCHRPreparatprodincntiveList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizing_Beam> getRCHRSizingBeamList() {
        return (List<RCHR_Sizing_Beam>) get(PROPERTY_RCHRSIZINGBEAMLIST);
    }

    public void setRCHRSizingBeamList(List<RCHR_Sizing_Beam> rCHRSizingBeamList) {
        set(PROPERTY_RCHRSIZINGBEAMLIST, rCHRSizingBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizingsheet> getRCHRSizingsheetList() {
        return (List<RCHR_Sizingsheet>) get(PROPERTY_RCHRSIZINGSHEETLIST);
    }

    public void setRCHRSizingsheetList(List<RCHR_Sizingsheet> rCHRSizingsheetList) {
        set(PROPERTY_RCHRSIZINGSHEETLIST, rCHRSizingsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentive> getRCPLProdIncentiveList() {
        return (List<RCPLProdIncentive>) get(PROPERTY_RCPLPRODINCENTIVELIST);
    }

    public void setRCPLProdIncentiveList(List<RCPLProdIncentive> rCPLProdIncentiveList) {
        set(PROPERTY_RCPLPRODINCENTIVELIST, rCPLProdIncentiveList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentiveLine> getRCPLProdIncentiveLineList() {
        return (List<RCPLProdIncentiveLine>) get(PROPERTY_RCPLPRODINCENTIVELINELIST);
    }

    public void setRCPLProdIncentiveLineList(List<RCPLProdIncentiveLine> rCPLProdIncentiveLineList) {
        set(PROPERTY_RCPLPRODINCENTIVELINELIST, rCPLProdIncentiveLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_VariableLine> getRCPLVariableLineList() {
        return (List<RCPL_VariableLine>) get(PROPERTY_RCPLVARIABLELINELIST);
    }

    public void setRCPLVariableLineList(List<RCPL_VariableLine> rCPLVariableLineList) {
        set(PROPERTY_RCPLVARIABLELINELIST, rCPLVariableLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Autoclone> getRCPRAutocloneList() {
        return (List<Autoclone>) get(PROPERTY_RCPRAUTOCLONELIST);
    }

    public void setRCPRAutocloneList(List<Autoclone> rCPRAutocloneList) {
        set(PROPERTY_RCPRAUTOCLONELIST, rCPRAutocloneList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPRCarding> getRCPRCardingList() {
        return (List<RCPRCarding>) get(PROPERTY_RCPRCARDINGLIST);
    }

    public void setRCPRCardingList(List<RCPRCarding> rCPRCardingList) {
        set(PROPERTY_RCPRCARDINGLIST, rCPRCardingList);
    }

    @SuppressWarnings("unchecked")
    public List<Combers> getRCPRCombersList() {
        return (List<Combers>) get(PROPERTY_RCPRCOMBERSLIST);
    }

    public void setRCPRCombersList(List<Combers> rCPRCombersList) {
        set(PROPERTY_RCPRCOMBERSLIST, rCPRCombersList);
    }

    @SuppressWarnings("unchecked")
    public List<Doubling> getRCPRDoublingList() {
        return (List<Doubling>) get(PROPERTY_RCPRDOUBLINGLIST);
    }

    public void setRCPRDoublingList(List<Doubling> rCPRDoublingList) {
        set(PROPERTY_RCPRDOUBLINGLIST, rCPRDoublingList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_DrawFrameBreaker> getRCPRDrawframebreakerList() {
        return (List<RCPR_DrawFrameBreaker>) get(PROPERTY_RCPRDRAWFRAMEBREAKERLIST);
    }

    public void setRCPRDrawframebreakerList(List<RCPR_DrawFrameBreaker> rCPRDrawframebreakerList) {
        set(PROPERTY_RCPRDRAWFRAMEBREAKERLIST, rCPRDrawframebreakerList);
    }

    @SuppressWarnings("unchecked")
    public List<LapFormer> getRCPRLapFormerList() {
        return (List<LapFormer>) get(PROPERTY_RCPRLAPFORMERLIST);
    }

    public void setRCPRLapFormerList(List<LapFormer> rCPRLapFormerList) {
        set(PROPERTY_RCPRLAPFORMERLIST, rCPRLapFormerList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Oe> getRCPROeList() {
        return (List<RCPR_Oe>) get(PROPERTY_RCPROELIST);
    }

    public void setRCPROeList(List<RCPR_Oe> rCPROeList) {
        set(PROPERTY_RCPROELIST, rCPROeList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PreventiveMaintenanceOrder> getRCPRPreventivemaintenanceList() {
        return (List<RCPR_PreventiveMaintenanceOrder>) get(PROPERTY_RCPRPREVENTIVEMAINTENANCELIST);
    }

    public void setRCPRPreventivemaintenanceList(List<RCPR_PreventiveMaintenanceOrder> rCPRPreventivemaintenanceList) {
        set(PROPERTY_RCPRPREVENTIVEMAINTENANCELIST, rCPRPreventivemaintenanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RSBDrawFrame> getRCPRRSBDrawFrameList() {
        return (List<RSBDrawFrame>) get(PROPERTY_RCPRRSBDRAWFRAMELIST);
    }

    public void setRCPRRSBDrawFrameList(List<RSBDrawFrame> rCPRRSBDrawFrameList) {
        set(PROPERTY_RCPRRSBDRAWFRAMELIST, rCPRRSBDrawFrameList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Simplex> getRCPRSimplexList() {
        return (List<RCPR_Simplex>) get(PROPERTY_RCPRSIMPLEXLIST);
    }

    public void setRCPRSimplexList(List<RCPR_Simplex> rCPRSimplexList) {
        set(PROPERTY_RCPRSIMPLEXLIST, rCPRSimplexList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Spinning> getRCPRSpinningList() {
        return (List<RCPR_Spinning>) get(PROPERTY_RCPRSPINNINGLIST);
    }

    public void setRCPRSpinningList(List<RCPR_Spinning> rCPRSpinningList) {
        set(PROPERTY_RCPRSPINNINGLIST, rCPRSpinningList);
    }

    @SuppressWarnings("unchecked")
    public List<TFO> getRCPRTFOList() {
        return (List<TFO>) get(PROPERTY_RCPRTFOLIST);
    }

    public void setRCPRTFOList(List<TFO> rCPRTFOList) {
        set(PROPERTY_RCPRTFOLIST, rCPRTFOList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendLine> getRchrAttendLineList() {
        return (List<RchrAttendLine>) get(PROPERTY_RCHRATTENDLINELIST);
    }

    public void setRchrAttendLineList(List<RchrAttendLine> rchrAttendLineList) {
        set(PROPERTY_RCHRATTENDLINELIST, rchrAttendLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendance> getRchrAttendanceList() {
        return (List<RchrAttendance>) get(PROPERTY_RCHRATTENDANCELIST);
    }

    public void setRchrAttendanceList(List<RchrAttendance> rchrAttendanceList) {
        set(PROPERTY_RCHRATTENDANCELIST, rchrAttendanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendanceProod> getRchrAttendanceProodList() {
        return (List<RchrAttendanceProod>) get(PROPERTY_RCHRATTENDANCEPROODLIST);
    }

    public void setRchrAttendanceProodList(List<RchrAttendanceProod> rchrAttendanceProodList) {
        set(PROPERTY_RCHRATTENDANCEPROODLIST, rchrAttendanceProodList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendanceTimeoff> getRchrAttendanceTimeoffList() {
        return (List<RchrAttendanceTimeoff>) get(PROPERTY_RCHRATTENDANCETIMEOFFLIST);
    }

    public void setRchrAttendanceTimeoffList(List<RchrAttendanceTimeoff> rchrAttendanceTimeoffList) {
        set(PROPERTY_RCHRATTENDANCETIMEOFFLIST, rchrAttendanceTimeoffList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendstrength> getRchrAttendstrengthList() {
        return (List<RchrAttendstrength>) get(PROPERTY_RCHRATTENDSTRENGTHLIST);
    }

    public void setRchrAttendstrengthList(List<RchrAttendstrength> rchrAttendstrengthList) {
        set(PROPERTY_RCHRATTENDSTRENGTHLIST, rchrAttendstrengthList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrMemoShift> getRchrMemoShiftList() {
        return (List<RchrMemoShift>) get(PROPERTY_RCHRMEMOSHIFTLIST);
    }

    public void setRchrMemoShiftList(List<RchrMemoShift> rchrMemoShiftList) {
        set(PROPERTY_RCHRMEMOSHIFTLIST, rchrMemoShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrMemoShift> getRchrMemoShiftNewshiftList() {
        return (List<RchrMemoShift>) get(PROPERTY_RCHRMEMOSHIFTNEWSHIFTLIST);
    }

    public void setRchrMemoShiftNewshiftList(List<RchrMemoShift> rchrMemoShiftNewshiftList) {
        set(PROPERTY_RCHRMEMOSHIFTNEWSHIFTLIST, rchrMemoShiftNewshiftList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrOvertimedates> getRchrOvertimedatesList() {
        return (List<RchrOvertimedates>) get(PROPERTY_RCHROVERTIMEDATESLIST);
    }

    public void setRchrOvertimedatesList(List<RchrOvertimedates> rchrOvertimedatesList) {
        set(PROPERTY_RCHROVERTIMEDATESLIST, rchrOvertimedatesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRelay> getRchrRelayList() {
        return (List<RchrRelay>) get(PROPERTY_RCHRRELAYLIST);
    }

    public void setRchrRelayList(List<RchrRelay> rchrRelayList) {
        set(PROPERTY_RCHRRELAYLIST, rchrRelayList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRollwisedata> getRchrRollwisedataList() {
        return (List<RchrRollwisedata>) get(PROPERTY_RCHRROLLWISEDATALIST);
    }

    public void setRchrRollwisedataList(List<RchrRollwisedata> rchrRollwisedataList) {
        set(PROPERTY_RCHRROLLWISEDATALIST, rchrRollwisedataList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrShiftlines> getRchrShiftlinesList() {
        return (List<RchrShiftlines>) get(PROPERTY_RCHRSHIFTLINESLIST);
    }

    public void setRchrShiftlinesList(List<RchrShiftlines> rchrShiftlinesList) {
        set(PROPERTY_RCHRSHIFTLINESLIST, rchrShiftlinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrShifttwelve> getRchrShifttwelveList() {
        return (List<RchrShifttwelve>) get(PROPERTY_RCHRSHIFTTWELVELIST);
    }

    public void setRchrShifttwelveList(List<RchrShifttwelve> rchrShifttwelveList) {
        set(PROPERTY_RCHRSHIFTTWELVELIST, rchrShifttwelveList);
    }

    @SuppressWarnings("unchecked")
    public List<RcmrMainmeter> getRcmrMainmeterList() {
        return (List<RcmrMainmeter>) get(PROPERTY_RCMRMAINMETERLIST);
    }

    public void setRcmrMainmeterList(List<RcmrMainmeter> rcmrMainmeterList) {
        set(PROPERTY_RCMRMAINMETERLIST, rcmrMainmeterList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplEmppprocessmanual> getRcplEmppprocessmanualList() {
        return (List<RcplEmppprocessmanual>) get(PROPERTY_RCPLEMPPPROCESSMANUALLIST);
    }

    public void setRcplEmppprocessmanualList(List<RcplEmppprocessmanual> rcplEmppprocessmanualList) {
        set(PROPERTY_RCPLEMPPPROCESSMANUALLIST, rcplEmppprocessmanualList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplLoomsproduction> getRcplLoomsproductionList() {
        return (List<RcplLoomsproduction>) get(PROPERTY_RCPLLOOMSPRODUCTIONLIST);
    }

    public void setRcplLoomsproductionList(List<RcplLoomsproduction> rcplLoomsproductionList) {
        set(PROPERTY_RCPLLOOMSPRODUCTIONLIST, rcplLoomsproductionList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplLoomsproductionimport> getRcplLoomsproductionimportList() {
        return (List<RcplLoomsproductionimport>) get(PROPERTY_RCPLLOOMSPRODUCTIONIMPORTLIST);
    }

    public void setRcplLoomsproductionimportList(List<RcplLoomsproductionimport> rcplLoomsproductionimportList) {
        set(PROPERTY_RCPLLOOMSPRODUCTIONIMPORTLIST, rcplLoomsproductionimportList);
    }

}
