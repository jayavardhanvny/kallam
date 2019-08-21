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
 * Entity class for entity RCHR_Leavetype (stored in table RCHR_Leavetype).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Leavetype extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Leavetype";
    public static final String ENTITY_NAME = "RCHR_Leavetype";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SKILLGRADENAME = "skillGradeName";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_CARRYFORWARD = "carryforward";
    public static final String PROPERTY_ISLOP = "islop";
    public static final String PROPERTY_PRIORITY = "priority";
    public static final String PROPERTY_RCHRCOMPENSATEOFFLIST = "rCHRCompensateOffList";
    public static final String PROPERTY_RCHREMPLEAVEBALANCELIST = "rCHREmpLeaveBalanceList";
    public static final String PROPERTY_RCHREMPLOYEELEAVEBALLIST = "rCHREmployeeLeaveBalList";
    public static final String PROPERTY_RCHRLEAVEBALANCEHISTORYLIST = "rCHRLeaveBalanceHistoryList";
    public static final String PROPERTY_RCHRLEAVEOPENBALLIST = "rCHRLeaveOpenBalList";
    public static final String PROPERTY_RCHRLEAVEREQUISITIONLIST = "rCHRLeaveRequisitionList";
    public static final String PROPERTY_RCHRLEAVEREQUISITIONLINELIST = "rCHRLeaveRequisitionLineList";
    public static final String PROPERTY_RCHRLEAVETEMPLATELINELIST = "rCHRLeaveTemplateLineList";
    public static final String PROPERTY_RCHRLOSSOFPAYLIST = "rCHRLossOfPayList";

    public RCHR_Leavetype() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CARRYFORWARD, false);
        setDefaultValue(PROPERTY_ISLOP, false);
        setDefaultValue(PROPERTY_RCHRCOMPENSATEOFFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLEAVEBALANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELEAVEBALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEOPENBALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEREQUISITIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVETEMPLATELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLOSSOFPAYLIST, new ArrayList<Object>());
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

    public String getSkillGradeName() {
        return (String) get(PROPERTY_SKILLGRADENAME);
    }

    public void setSkillGradeName(String skillGradeName) {
        set(PROPERTY_SKILLGRADENAME, skillGradeName);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public Boolean isCarryforward() {
        return (Boolean) get(PROPERTY_CARRYFORWARD);
    }

    public void setCarryforward(Boolean carryforward) {
        set(PROPERTY_CARRYFORWARD, carryforward);
    }

    public Boolean isLop() {
        return (Boolean) get(PROPERTY_ISLOP);
    }

    public void setLop(Boolean islop) {
        set(PROPERTY_ISLOP, islop);
    }

    public Long getPriority() {
        return (Long) get(PROPERTY_PRIORITY);
    }

    public void setPriority(Long priority) {
        set(PROPERTY_PRIORITY, priority);
    }

    @SuppressWarnings("unchecked")
    public List<RchrCompensateOff> getRCHRCompensateOffList() {
        return (List<RchrCompensateOff>) get(PROPERTY_RCHRCOMPENSATEOFFLIST);
    }

    public void setRCHRCompensateOffList(List<RchrCompensateOff> rCHRCompensateOffList) {
        set(PROPERTY_RCHRCOMPENSATEOFFLIST, rCHRCompensateOffList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHREmpLeaveBalance> getRCHREmpLeaveBalanceList() {
        return (List<RCHREmpLeaveBalance>) get(PROPERTY_RCHREMPLEAVEBALANCELIST);
    }

    public void setRCHREmpLeaveBalanceList(List<RCHREmpLeaveBalance> rCHREmpLeaveBalanceList) {
        set(PROPERTY_RCHREMPLEAVEBALANCELIST, rCHREmpLeaveBalanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployeeleaveBal> getRCHREmployeeLeaveBalList() {
        return (List<RchrEmployeeleaveBal>) get(PROPERTY_RCHREMPLOYEELEAVEBALLIST);
    }

    public void setRCHREmployeeLeaveBalList(List<RchrEmployeeleaveBal> rCHREmployeeLeaveBalList) {
        set(PROPERTY_RCHREMPLOYEELEAVEBALLIST, rCHREmployeeLeaveBalList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRLeaveBalanceHistory> getRCHRLeaveBalanceHistoryList() {
        return (List<RCHRLeaveBalanceHistory>) get(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST);
    }

    public void setRCHRLeaveBalanceHistoryList(List<RCHRLeaveBalanceHistory> rCHRLeaveBalanceHistoryList) {
        set(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST, rCHRLeaveBalanceHistoryList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_LeaveOpenBalance> getRCHRLeaveOpenBalList() {
        return (List<RCHR_LeaveOpenBalance>) get(PROPERTY_RCHRLEAVEOPENBALLIST);
    }

    public void setRCHRLeaveOpenBalList(List<RCHR_LeaveOpenBalance> rCHRLeaveOpenBalList) {
        set(PROPERTY_RCHRLEAVEOPENBALLIST, rCHRLeaveOpenBalList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_LeaveRequisition> getRCHRLeaveRequisitionList() {
        return (List<RCHR_LeaveRequisition>) get(PROPERTY_RCHRLEAVEREQUISITIONLIST);
    }

    public void setRCHRLeaveRequisitionList(List<RCHR_LeaveRequisition> rCHRLeaveRequisitionList) {
        set(PROPERTY_RCHRLEAVEREQUISITIONLIST, rCHRLeaveRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRLeaveRequisitionLine> getRCHRLeaveRequisitionLineList() {
        return (List<RCHRLeaveRequisitionLine>) get(PROPERTY_RCHRLEAVEREQUISITIONLINELIST);
    }

    public void setRCHRLeaveRequisitionLineList(List<RCHRLeaveRequisitionLine> rCHRLeaveRequisitionLineList) {
        set(PROPERTY_RCHRLEAVEREQUISITIONLINELIST, rCHRLeaveRequisitionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRLeaveTemplateLine> getRCHRLeaveTemplateLineList() {
        return (List<RCHRLeaveTemplateLine>) get(PROPERTY_RCHRLEAVETEMPLATELINELIST);
    }

    public void setRCHRLeaveTemplateLineList(List<RCHRLeaveTemplateLine> rCHRLeaveTemplateLineList) {
        set(PROPERTY_RCHRLEAVETEMPLATELINELIST, rCHRLeaveTemplateLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrLossOfPay> getRCHRLossOfPayList() {
        return (List<RchrLossOfPay>) get(PROPERTY_RCHRLOSSOFPAYLIST);
    }

    public void setRCHRLossOfPayList(List<RchrLossOfPay> rCHRLossOfPayList) {
        set(PROPERTY_RCHRLOSSOFPAYLIST, rCHRLossOfPayList);
    }

}
