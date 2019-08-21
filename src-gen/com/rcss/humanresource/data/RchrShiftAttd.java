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
 * Entity class for entity Rchr_Shift_Attd (stored in table Rchr_Shift_Attd).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrShiftAttd extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Shift_Attd";
    public static final String ENTITY_NAME = "Rchr_Shift_Attd";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRMEMOSHIFT = "rchrMemoShift";
    public static final String PROPERTY_RCHRDAILYATTENDDEMO = "rchrDailyattenddemo";
    public static final String PROPERTY_ATTENDANCEDATE = "attendanceDate";
    public static final String PROPERTY_PRESENT = "present";
    public static final String PROPERTY_OVERTIME = "overtime";
    public static final String PROPERTY_WEEKLYOFF = "weeklyOff";
    public static final String PROPERTY_COMPLETE = "complete";
    public static final String PROPERTY_ISFLAG = "isflag";
    public static final String PROPERTY_ISPAYROLL = "ispayroll";

    public RchrShiftAttd() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PRESENT, false);
        setDefaultValue(PROPERTY_OVERTIME, false);
        setDefaultValue(PROPERTY_WEEKLYOFF, false);
        setDefaultValue(PROPERTY_COMPLETE, false);
        setDefaultValue(PROPERTY_ISFLAG, false);
        setDefaultValue(PROPERTY_ISPAYROLL, false);
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

    public RchrMemoShift getRchrMemoShift() {
        return (RchrMemoShift) get(PROPERTY_RCHRMEMOSHIFT);
    }

    public void setRchrMemoShift(RchrMemoShift rchrMemoShift) {
        set(PROPERTY_RCHRMEMOSHIFT, rchrMemoShift);
    }

    public RCHRDailyattenddemo getRchrDailyattenddemo() {
        return (RCHRDailyattenddemo) get(PROPERTY_RCHRDAILYATTENDDEMO);
    }

    public void setRchrDailyattenddemo(RCHRDailyattenddemo rchrDailyattenddemo) {
        set(PROPERTY_RCHRDAILYATTENDDEMO, rchrDailyattenddemo);
    }

    public Date getAttendanceDate() {
        return (Date) get(PROPERTY_ATTENDANCEDATE);
    }

    public void setAttendanceDate(Date attendanceDate) {
        set(PROPERTY_ATTENDANCEDATE, attendanceDate);
    }

    public Boolean isPresent() {
        return (Boolean) get(PROPERTY_PRESENT);
    }

    public void setPresent(Boolean present) {
        set(PROPERTY_PRESENT, present);
    }

    public Boolean isOvertime() {
        return (Boolean) get(PROPERTY_OVERTIME);
    }

    public void setOvertime(Boolean overtime) {
        set(PROPERTY_OVERTIME, overtime);
    }

    public Boolean isWeeklyOff() {
        return (Boolean) get(PROPERTY_WEEKLYOFF);
    }

    public void setWeeklyOff(Boolean weeklyOff) {
        set(PROPERTY_WEEKLYOFF, weeklyOff);
    }

    public Boolean isComplete() {
        return (Boolean) get(PROPERTY_COMPLETE);
    }

    public void setComplete(Boolean complete) {
        set(PROPERTY_COMPLETE, complete);
    }

    public Boolean isFlag() {
        return (Boolean) get(PROPERTY_ISFLAG);
    }

    public void setFlag(Boolean isflag) {
        set(PROPERTY_ISFLAG, isflag);
    }

    public Boolean isPayroll() {
        return (Boolean) get(PROPERTY_ISPAYROLL);
    }

    public void setPayroll(Boolean ispayroll) {
        set(PROPERTY_ISPAYROLL, ispayroll);
    }

}
