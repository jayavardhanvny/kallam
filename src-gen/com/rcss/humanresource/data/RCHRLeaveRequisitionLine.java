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
 * Entity class for entity RCHR_LeaveRequisitionLine (stored in table RCHR_LeaveRequisitionLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRLeaveRequisitionLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_LeaveRequisitionLine";
    public static final String ENTITY_NAME = "RCHR_LeaveRequisitionLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LEAVEREQUISITION = "leaveRequisition";
    public static final String PROPERTY_LEAVETYPE = "leaveType";
    public static final String PROPERTY_LEAVEDATE = "leavedate";
    public static final String PROPERTY_RCHRCOMPENSATEOFF = "rchrCompensateoff";
    public static final String PROPERTY_CANCEL = "cancel";
    public static final String PROPERTY_CANCELED = "canceled";
    public static final String PROPERTY_RCHRLEAVEBALANCEHISTORYLIST = "rCHRLeaveBalanceHistoryList";

    public RCHRLeaveRequisitionLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CANCEL, false);
        setDefaultValue(PROPERTY_CANCELED, false);
        setDefaultValue(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST, new ArrayList<Object>());
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

    public RCHR_LeaveRequisition getLeaveRequisition() {
        return (RCHR_LeaveRequisition) get(PROPERTY_LEAVEREQUISITION);
    }

    public void setLeaveRequisition(RCHR_LeaveRequisition leaveRequisition) {
        set(PROPERTY_LEAVEREQUISITION, leaveRequisition);
    }

    public RCHR_Leavetype getLeaveType() {
        return (RCHR_Leavetype) get(PROPERTY_LEAVETYPE);
    }

    public void setLeaveType(RCHR_Leavetype leaveType) {
        set(PROPERTY_LEAVETYPE, leaveType);
    }

    public Date getLeavedate() {
        return (Date) get(PROPERTY_LEAVEDATE);
    }

    public void setLeavedate(Date leavedate) {
        set(PROPERTY_LEAVEDATE, leavedate);
    }

    public RchrCompensateOff getRchrCompensateoff() {
        return (RchrCompensateOff) get(PROPERTY_RCHRCOMPENSATEOFF);
    }

    public void setRchrCompensateoff(RchrCompensateOff rchrCompensateoff) {
        set(PROPERTY_RCHRCOMPENSATEOFF, rchrCompensateoff);
    }

    public Boolean isCancel() {
        return (Boolean) get(PROPERTY_CANCEL);
    }

    public void setCancel(Boolean cancel) {
        set(PROPERTY_CANCEL, cancel);
    }

    public Boolean isCanceled() {
        return (Boolean) get(PROPERTY_CANCELED);
    }

    public void setCanceled(Boolean canceled) {
        set(PROPERTY_CANCELED, canceled);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRLeaveBalanceHistory> getRCHRLeaveBalanceHistoryList() {
        return (List<RCHRLeaveBalanceHistory>) get(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST);
    }

    public void setRCHRLeaveBalanceHistoryList(List<RCHRLeaveBalanceHistory> rCHRLeaveBalanceHistoryList) {
        set(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST, rCHRLeaveBalanceHistoryList);
    }

}
