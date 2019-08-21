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
 * Entity class for entity RCHR_Permission (stored in table RCHR_Permission).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRPermission extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Permission";
    public static final String ENTITY_NAME = "RCHR_Permission";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_FROMTIME = "fromtime";
    public static final String PROPERTY_TOTIME = "totime";
    public static final String PROPERTY_SHIFTINMINS = "shiftinmins";
    public static final String PROPERTY_TIMEDIFFERENCE = "timedifference";
    public static final String PROPERTY_COUNT = "count";
    public static final String PROPERTY_ISDEDUCTION = "isdeduction";
    public static final String PROPERTY_PERMISINDATE = "permisindate";
    public static final String PROPERTY_PROCEED = "proceed";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_ACCEPT = "accept";
    public static final String PROPERTY_REJECT = "reject";
    public static final String PROPERTY_RCHRDAILYATTENDDEMO = "rchrDailyattenddemo";
    public static final String PROPERTY_APPROVEDBY = "approvedby";
    public static final String PROPERTY_RCHRDAILYATTENDDEMOLIST = "rCHRDailyattenddemoList";

    public RCHRPermission() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_COUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ISDEDUCTION, false);
        setDefaultValue(PROPERTY_PROCEED, false);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_ACCEPT, false);
        setDefaultValue(PROPERTY_REJECT, false);
        setDefaultValue(PROPERTY_RCHRDAILYATTENDDEMOLIST, new ArrayList<Object>());
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

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public Timestamp getFromtime() {
        return (Timestamp) get(PROPERTY_FROMTIME);
    }

    public void setFromtime(Timestamp fromtime) {
        set(PROPERTY_FROMTIME, fromtime);
    }

    public Timestamp getTotime() {
        return (Timestamp) get(PROPERTY_TOTIME);
    }

    public void setTotime(Timestamp totime) {
        set(PROPERTY_TOTIME, totime);
    }

    public Long getShiftinmins() {
        return (Long) get(PROPERTY_SHIFTINMINS);
    }

    public void setShiftinmins(Long shiftinmins) {
        set(PROPERTY_SHIFTINMINS, shiftinmins);
    }

    public String getTimedifference() {
        return (String) get(PROPERTY_TIMEDIFFERENCE);
    }

    public void setTimedifference(String timedifference) {
        set(PROPERTY_TIMEDIFFERENCE, timedifference);
    }

    public BigDecimal getCount() {
        return (BigDecimal) get(PROPERTY_COUNT);
    }

    public void setCount(BigDecimal count) {
        set(PROPERTY_COUNT, count);
    }

    public Boolean isDeduction() {
        return (Boolean) get(PROPERTY_ISDEDUCTION);
    }

    public void setDeduction(Boolean isdeduction) {
        set(PROPERTY_ISDEDUCTION, isdeduction);
    }

    public Date getPermisindate() {
        return (Date) get(PROPERTY_PERMISINDATE);
    }

    public void setPermisindate(Date permisindate) {
        set(PROPERTY_PERMISINDATE, permisindate);
    }

    public Boolean isProceed() {
        return (Boolean) get(PROPERTY_PROCEED);
    }

    public void setProceed(Boolean proceed) {
        set(PROPERTY_PROCEED, proceed);
    }

    public String getReasonForLeave() {
        return (String) get(PROPERTY_REASONFORLEAVE);
    }

    public void setReasonForLeave(String reasonForLeave) {
        set(PROPERTY_REASONFORLEAVE, reasonForLeave);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isAccept() {
        return (Boolean) get(PROPERTY_ACCEPT);
    }

    public void setAccept(Boolean accept) {
        set(PROPERTY_ACCEPT, accept);
    }

    public Boolean isReject() {
        return (Boolean) get(PROPERTY_REJECT);
    }

    public void setReject(Boolean reject) {
        set(PROPERTY_REJECT, reject);
    }

    public RCHRDailyattenddemo getRchrDailyattenddemo() {
        return (RCHRDailyattenddemo) get(PROPERTY_RCHRDAILYATTENDDEMO);
    }

    public void setRchrDailyattenddemo(RCHRDailyattenddemo rchrDailyattenddemo) {
        set(PROPERTY_RCHRDAILYATTENDDEMO, rchrDailyattenddemo);
    }

    public String getApprovedby() {
        return (String) get(PROPERTY_APPROVEDBY);
    }

    public void setApprovedby(String approvedby) {
        set(PROPERTY_APPROVEDBY, approvedby);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRDailyattenddemo> getRCHRDailyattenddemoList() {
        return (List<RCHRDailyattenddemo>) get(PROPERTY_RCHRDAILYATTENDDEMOLIST);
    }

    public void setRCHRDailyattenddemoList(List<RCHRDailyattenddemo> rCHRDailyattenddemoList) {
        set(PROPERTY_RCHRDAILYATTENDDEMOLIST, rCHRDailyattenddemoList);
    }

}
