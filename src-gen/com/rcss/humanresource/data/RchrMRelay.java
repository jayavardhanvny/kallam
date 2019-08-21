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
 * Entity class for entity Rchr_MRelay (stored in table Rchr_MRelay).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrMRelay extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_MRelay";
    public static final String ENTITY_NAME = "Rchr_MRelay";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchkey";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_RCHRSHIFTGROUP = "rchrShiftgroup";
    public static final String PROPERTY_ISROTATED = "isrotated";
    public static final String PROPERTY_RCHREMPRELAYINFOLIST = "rCHREmpRelayInfoList";
    public static final String PROPERTY_RCHRRELAYSHIFTDETAILSLIST = "rCHRRelayShiftDetailsList";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";
    public static final String PROPERTY_RCHRMEMOSHIFTLIST = "rchrMemoShiftList";
    public static final String PROPERTY_RCHRRELAYLIST = "rchrRelayList";
    public static final String PROPERTY_RCHRSHIFTRELAYLIST = "rchrShiftrelayList";

    public RchrMRelay() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISROTATED, false);
        setDefaultValue(PROPERTY_RCHREMPRELAYINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRRELAYSHIFTDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMEMOSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRRELAYLIST, new ArrayList<Object>());
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

    public String getSearchkey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchkey(String searchkey) {
        set(PROPERTY_SEARCHKEY, searchkey);
    }

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public RchrShiftgroup getRchrShiftgroup() {
        return (RchrShiftgroup) get(PROPERTY_RCHRSHIFTGROUP);
    }

    public void setRchrShiftgroup(RchrShiftgroup rchrShiftgroup) {
        set(PROPERTY_RCHRSHIFTGROUP, rchrShiftgroup);
    }

    public Boolean isRotated() {
        return (Boolean) get(PROPERTY_ISROTATED);
    }

    public void setRotated(Boolean isrotated) {
        set(PROPERTY_ISROTATED, isrotated);
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
    public List<RchrMemoShift> getRchrMemoShiftList() {
        return (List<RchrMemoShift>) get(PROPERTY_RCHRMEMOSHIFTLIST);
    }

    public void setRchrMemoShiftList(List<RchrMemoShift> rchrMemoShiftList) {
        set(PROPERTY_RCHRMEMOSHIFTLIST, rchrMemoShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRelay> getRchrRelayList() {
        return (List<RchrRelay>) get(PROPERTY_RCHRRELAYLIST);
    }

    public void setRchrRelayList(List<RchrRelay> rchrRelayList) {
        set(PROPERTY_RCHRRELAYLIST, rchrRelayList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrShiftrelay> getRchrShiftrelayList() {
        return (List<RchrShiftrelay>) get(PROPERTY_RCHRSHIFTRELAYLIST);
    }

    public void setRchrShiftrelayList(List<RchrShiftrelay> rchrShiftrelayList) {
        set(PROPERTY_RCHRSHIFTRELAYLIST, rchrShiftrelayList);
    }

}
