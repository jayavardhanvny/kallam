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

import com.redcarpet.payroll.data.RCPLProdIncentiveLine;
import com.redcarpet.payroll.data.RcplProdincsecline;

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
 * Entity class for entity RCHR_Detention (stored in table RCHR_Detention).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Detention extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Detention";
    public static final String ENTITY_NAME = "RCHR_Detention";
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
    public static final String PROPERTY_ISPREPARATORY = "ispreparatory";
    public static final String PROPERTY_ISINSPECTION = "isinspection";
    public static final String PROPERTY_ISLOOMS = "islooms";
    public static final String PROPERTY_RCHRDIRECTWARPLIST = "rCHRDirectwarpList";
    public static final String PROPERTY_RCHRSIZINGBEAMLIST = "rCHRSizingBeamList";
    public static final String PROPERTY_RCHRSIZINGSHEETLIST = "rCHRSizingsheetList";
    public static final String PROPERTY_RCPLPRODINCENTIVELINEDETENTIONTWOLIST = "rCPLProdIncentiveLineDetentiontwoList";
    public static final String PROPERTY_RCPLPRODINCENTIVELINEDETENTIONFOURLIST = "rCPLProdIncentiveLineDetentionfourList";
    public static final String PROPERTY_RCPLPRODINCENTIVELINEDETENTIONTHREELIST = "rCPLProdIncentiveLineDetentionthreeList";
    public static final String PROPERTY_RCPLPRODINCENTIVELINELIST = "rCPLProdIncentiveLineList";
    public static final String PROPERTY_RCPLPRODINCSECLINELIST = "rcplProdincseclineList";

    public RCHR_Detention() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISPREPARATORY, false);
        setDefaultValue(PROPERTY_ISINSPECTION, false);
        setDefaultValue(PROPERTY_ISLOOMS, false);
        setDefaultValue(PROPERTY_RCHRDIRECTWARPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONTWOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONFOURLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONTHREELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCSECLINELIST, new ArrayList<Object>());
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

    public Boolean isPreparatory() {
        return (Boolean) get(PROPERTY_ISPREPARATORY);
    }

    public void setPreparatory(Boolean ispreparatory) {
        set(PROPERTY_ISPREPARATORY, ispreparatory);
    }

    public Boolean isInspection() {
        return (Boolean) get(PROPERTY_ISINSPECTION);
    }

    public void setInspection(Boolean isinspection) {
        set(PROPERTY_ISINSPECTION, isinspection);
    }

    public Boolean isLooms() {
        return (Boolean) get(PROPERTY_ISLOOMS);
    }

    public void setLooms(Boolean islooms) {
        set(PROPERTY_ISLOOMS, islooms);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Directwarp> getRCHRDirectwarpList() {
        return (List<RCHR_Directwarp>) get(PROPERTY_RCHRDIRECTWARPLIST);
    }

    public void setRCHRDirectwarpList(List<RCHR_Directwarp> rCHRDirectwarpList) {
        set(PROPERTY_RCHRDIRECTWARPLIST, rCHRDirectwarpList);
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
    public List<RCPLProdIncentiveLine> getRCPLProdIncentiveLineDetentiontwoList() {
        return (List<RCPLProdIncentiveLine>) get(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONTWOLIST);
    }

    public void setRCPLProdIncentiveLineDetentiontwoList(List<RCPLProdIncentiveLine> rCPLProdIncentiveLineDetentiontwoList) {
        set(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONTWOLIST, rCPLProdIncentiveLineDetentiontwoList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentiveLine> getRCPLProdIncentiveLineDetentionfourList() {
        return (List<RCPLProdIncentiveLine>) get(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONFOURLIST);
    }

    public void setRCPLProdIncentiveLineDetentionfourList(List<RCPLProdIncentiveLine> rCPLProdIncentiveLineDetentionfourList) {
        set(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONFOURLIST, rCPLProdIncentiveLineDetentionfourList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentiveLine> getRCPLProdIncentiveLineDetentionthreeList() {
        return (List<RCPLProdIncentiveLine>) get(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONTHREELIST);
    }

    public void setRCPLProdIncentiveLineDetentionthreeList(List<RCPLProdIncentiveLine> rCPLProdIncentiveLineDetentionthreeList) {
        set(PROPERTY_RCPLPRODINCENTIVELINEDETENTIONTHREELIST, rCPLProdIncentiveLineDetentionthreeList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentiveLine> getRCPLProdIncentiveLineList() {
        return (List<RCPLProdIncentiveLine>) get(PROPERTY_RCPLPRODINCENTIVELINELIST);
    }

    public void setRCPLProdIncentiveLineList(List<RCPLProdIncentiveLine> rCPLProdIncentiveLineList) {
        set(PROPERTY_RCPLPRODINCENTIVELINELIST, rCPLProdIncentiveLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplProdincsecline> getRcplProdincseclineList() {
        return (List<RcplProdincsecline>) get(PROPERTY_RCPLPRODINCSECLINELIST);
    }

    public void setRcplProdincseclineList(List<RcplProdincsecline> rcplProdincseclineList) {
        set(PROPERTY_RCPLPRODINCSECLINELIST, rcplProdincseclineList);
    }

}
