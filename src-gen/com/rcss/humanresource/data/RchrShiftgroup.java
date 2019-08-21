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
 * Entity class for entity Rchr_Shiftgroup (stored in table Rchr_Shiftgroup).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrShiftgroup extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Shiftgroup";
    public static final String ENTITY_NAME = "Rchr_Shiftgroup";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_ISSHFTROTATEAPLCBLE = "isshftrotateaplcble";
    public static final String PROPERTY_SHIFTTYPE = "shifttype";
    public static final String PROPERTY_RCHREMPRELAYINFOLIST = "rCHREmpRelayInfoList";
    public static final String PROPERTY_RCHRRELAYSHIFTDETAILSLIST = "rCHRRelayShiftDetailsList";
    public static final String PROPERTY_RCPRSHIFTLIST = "rCPRShiftList";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";
    public static final String PROPERTY_RCHRMRELAYLIST = "rchrMRelayList";
    public static final String PROPERTY_RCHRMEMOSHIFTLIST = "rchrMemoShiftList";
    public static final String PROPERTY_RCHRSHIFTLINESLIST = "rchrShiftlinesList";
    public static final String PROPERTY_RCHRSHIFTRELAYLIST = "rchrShiftrelayList";

    public RchrShiftgroup() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISSHFTROTATEAPLCBLE, false);
        setDefaultValue(PROPERTY_RCHREMPRELAYINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRRELAYSHIFTDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMRELAYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMEMOSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSHIFTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSHIFTRELAYLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getReasonForLeave() {
        return (String) get(PROPERTY_REASONFORLEAVE);
    }

    public void setReasonForLeave(String reasonForLeave) {
        set(PROPERTY_REASONFORLEAVE, reasonForLeave);
    }

    public Boolean isShftrotateaplcble() {
        return (Boolean) get(PROPERTY_ISSHFTROTATEAPLCBLE);
    }

    public void setShftrotateaplcble(Boolean isshftrotateaplcble) {
        set(PROPERTY_ISSHFTROTATEAPLCBLE, isshftrotateaplcble);
    }

    public String getShifttype() {
        return (String) get(PROPERTY_SHIFTTYPE);
    }

    public void setShifttype(String shifttype) {
        set(PROPERTY_SHIFTTYPE, shifttype);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmpRelayInfo> getRCHREmpRelayInfoList() {
        return (List<RchrEmpRelayInfo>) get(PROPERTY_RCHREMPRELAYINFOLIST);
    }

    public void setRCHREmpRelayInfoList(List<RchrEmpRelayInfo> rCHREmpRelayInfoList) {
        set(PROPERTY_RCHREMPRELAYINFOLIST, rCHREmpRelayInfoList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRelayShiftDetails> getRCHRRelayShiftDetailsList() {
        return (List<RchrRelayShiftDetails>) get(PROPERTY_RCHRRELAYSHIFTDETAILSLIST);
    }

    public void setRCHRRelayShiftDetailsList(List<RchrRelayShiftDetails> rCHRRelayShiftDetailsList) {
        set(PROPERTY_RCHRRELAYSHIFTDETAILSLIST, rCHRRelayShiftDetailsList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPRShift> getRCPRShiftList() {
        return (List<RCPRShift>) get(PROPERTY_RCPRSHIFTLIST);
    }

    public void setRCPRShiftList(List<RCPRShift> rCPRShiftList) {
        set(PROPERTY_RCPRSHIFTLIST, rCPRShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployee> getRchrEmployeeList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEELIST);
    }

    public void setRchrEmployeeList(List<RchrEmployee> rchrEmployeeList) {
        set(PROPERTY_RCHREMPLOYEELIST, rchrEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrMRelay> getRchrMRelayList() {
        return (List<RchrMRelay>) get(PROPERTY_RCHRMRELAYLIST);
    }

    public void setRchrMRelayList(List<RchrMRelay> rchrMRelayList) {
        set(PROPERTY_RCHRMRELAYLIST, rchrMRelayList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrMemoShift> getRchrMemoShiftList() {
        return (List<RchrMemoShift>) get(PROPERTY_RCHRMEMOSHIFTLIST);
    }

    public void setRchrMemoShiftList(List<RchrMemoShift> rchrMemoShiftList) {
        set(PROPERTY_RCHRMEMOSHIFTLIST, rchrMemoShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrShiftlines> getRchrShiftlinesList() {
        return (List<RchrShiftlines>) get(PROPERTY_RCHRSHIFTLINESLIST);
    }

    public void setRchrShiftlinesList(List<RchrShiftlines> rchrShiftlinesList) {
        set(PROPERTY_RCHRSHIFTLINESLIST, rchrShiftlinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrShiftrelay> getRchrShiftrelayList() {
        return (List<RchrShiftrelay>) get(PROPERTY_RCHRSHIFTRELAYLIST);
    }

    public void setRchrShiftrelayList(List<RchrShiftrelay> rchrShiftrelayList) {
        set(PROPERTY_RCHRSHIFTRELAYLIST, rchrShiftrelayList);
    }

}
