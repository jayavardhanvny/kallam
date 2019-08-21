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

import com.rcss.humanresource.data.RCHRDailyattend;
import com.rcss.humanresource.data.RchrEmployee;
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
 * Entity class for entity Rcpl_Emppprocessmanual (stored in table Rcpl_Emppprocessmanual).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RcplEmppprocessmanual extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rcpl_Emppprocessmanual";
    public static final String ENTITY_NAME = "Rcpl_Emppprocessmanual";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCPLPAYROLLPROCESSMANUAL = "rcplPayrollprocessmanual";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_ATTDDATE = "attddate";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_COMPLETE = "complete";
    public static final String PROPERTY_RCHRDAILYATTEND = "rchrDailyattend";
    public static final String PROPERTY_DAYTYPE = "daytype";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_OTPROCESS = "otprocess";
    public static final String PROPERTY_MANUALPRESENTS = "manualpresents";
    public static final String PROPERTY_PUNCHIN = "punchIn";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_RCHRDAILYATTENDLIST = "rCHRDailyattendList";

    public RcplEmppprocessmanual() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_COMPLETE, false);
        setDefaultValue(PROPERTY_OTPROCESS, false);
        setDefaultValue(PROPERTY_MANUALPRESENTS, false);
        setDefaultValue(PROPERTY_RCHRDAILYATTENDLIST, new ArrayList<Object>());
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

    public RcplPayrollprocessmanual getRcplPayrollprocessmanual() {
        return (RcplPayrollprocessmanual) get(PROPERTY_RCPLPAYROLLPROCESSMANUAL);
    }

    public void setRcplPayrollprocessmanual(RcplPayrollprocessmanual rcplPayrollprocessmanual) {
        set(PROPERTY_RCPLPAYROLLPROCESSMANUAL, rcplPayrollprocessmanual);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Date getAttddate() {
        return (Date) get(PROPERTY_ATTDDATE);
    }

    public void setAttddate(Date attddate) {
        set(PROPERTY_ATTDDATE, attddate);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isComplete() {
        return (Boolean) get(PROPERTY_COMPLETE);
    }

    public void setComplete(Boolean complete) {
        set(PROPERTY_COMPLETE, complete);
    }

    public RCHRDailyattend getRchrDailyattend() {
        return (RCHRDailyattend) get(PROPERTY_RCHRDAILYATTEND);
    }

    public void setRchrDailyattend(RCHRDailyattend rchrDailyattend) {
        set(PROPERTY_RCHRDAILYATTEND, rchrDailyattend);
    }

    public String getDaytype() {
        return (String) get(PROPERTY_DAYTYPE);
    }

    public void setDaytype(String daytype) {
        set(PROPERTY_DAYTYPE, daytype);
    }

    public String getReasonForLeave() {
        return (String) get(PROPERTY_REASONFORLEAVE);
    }

    public void setReasonForLeave(String reasonForLeave) {
        set(PROPERTY_REASONFORLEAVE, reasonForLeave);
    }

    public Boolean isOtprocess() {
        return (Boolean) get(PROPERTY_OTPROCESS);
    }

    public void setOtprocess(Boolean otprocess) {
        set(PROPERTY_OTPROCESS, otprocess);
    }

    public Boolean isManualpresents() {
        return (Boolean) get(PROPERTY_MANUALPRESENTS);
    }

    public void setManualpresents(Boolean manualpresents) {
        set(PROPERTY_MANUALPRESENTS, manualpresents);
    }

    public Timestamp getPunchIn() {
        return (Timestamp) get(PROPERTY_PUNCHIN);
    }

    public void setPunchIn(Timestamp punchIn) {
        set(PROPERTY_PUNCHIN, punchIn);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRDailyattend> getRCHRDailyattendList() {
        return (List<RCHRDailyattend>) get(PROPERTY_RCHRDAILYATTENDLIST);
    }

    public void setRCHRDailyattendList(List<RCHRDailyattend> rCHRDailyattendList) {
        set(PROPERTY_RCHRDAILYATTENDLIST, rCHRDailyattendList);
    }

}
