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
 * Entity class for entity Rchr_Attendance (stored in table Rchr_Attendance).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrAttendance extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Attendance";
    public static final String ENTITY_NAME = "Rchr_Attendance";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_FROMDATE = "fromdate";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SHIFT = "shift";
    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_PROCESSSHIFTWISEATTENDANCE = "processShiftWiseAttendance";
    public static final String PROPERTY_LOSTDURATION = "lostduration";
    public static final String PROPERTY_PROCESSLOSTTIME = "processlosttime";
    public static final String PROPERTY_RCHRATTENDLINELIST = "rchrAttendLineList";

    public RchrAttendance() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PROCESSSHIFTWISEATTENDANCE, false);
        setDefaultValue(PROPERTY_LOSTDURATION, "0");
        setDefaultValue(PROPERTY_PROCESSLOSTTIME, false);
        setDefaultValue(PROPERTY_RCHRATTENDLINELIST, new ArrayList<Object>());
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

    public Date getFromdate() {
        return (Date) get(PROPERTY_FROMDATE);
    }

    public void setFromdate(Date fromdate) {
        set(PROPERTY_FROMDATE, fromdate);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public RCPRShift getShift() {
        return (RCPRShift) get(PROPERTY_SHIFT);
    }

    public void setShift(RCPRShift shift) {
        set(PROPERTY_SHIFT, shift);
    }

    public String getDuration() {
        return (String) get(PROPERTY_DURATION);
    }

    public void setDuration(String duration) {
        set(PROPERTY_DURATION, duration);
    }

    public Boolean isProcessShiftWiseAttendance() {
        return (Boolean) get(PROPERTY_PROCESSSHIFTWISEATTENDANCE);
    }

    public void setProcessShiftWiseAttendance(Boolean processShiftWiseAttendance) {
        set(PROPERTY_PROCESSSHIFTWISEATTENDANCE, processShiftWiseAttendance);
    }

    public String getLostduration() {
        return (String) get(PROPERTY_LOSTDURATION);
    }

    public void setLostduration(String lostduration) {
        set(PROPERTY_LOSTDURATION, lostduration);
    }

    public Boolean isProcesslosttime() {
        return (Boolean) get(PROPERTY_PROCESSLOSTTIME);
    }

    public void setProcesslosttime(Boolean processlosttime) {
        set(PROPERTY_PROCESSLOSTTIME, processlosttime);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendLine> getRchrAttendLineList() {
        return (List<RchrAttendLine>) get(PROPERTY_RCHRATTENDLINELIST);
    }

    public void setRchrAttendLineList(List<RchrAttendLine> rchrAttendLineList) {
        set(PROPERTY_RCHRATTENDLINELIST, rchrAttendLineList);
    }

}
