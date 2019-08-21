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

import com.redcarpet.meterreading.data.RcmrLtpanel;
import com.redcarpet.quality.data.RcqaCardingneps;
import com.redcarpet.quality.data.RcqaCombernoil;
import com.redcarpet.quality.data.RcqaCombersu;
import com.redcarpet.quality.data.RcqaMisCount;
import com.redcarpet.quality.data.RcqaOebreakage;
import com.redcarpet.quality.data.RcqaRingframebreak;
import com.redcarpet.quality.data.RcqaRingframeidle;
import com.redcarpet.quality.data.RcqaRsbapercentage;
import com.redcarpet.quality.data.RcqaSimplexstr;
import com.redcarpet.quality.data.RcqaWrapbreak;

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
 * Entity class for entity RCPR_Shed (stored in table RCPR_Shed).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPRShed extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Shed";
    public static final String ENTITY_NAME = "RCPR_Shed";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_SHEDNO = "shedNo";
    public static final String PROPERTY_UNIT = "unit";
    public static final String PROPERTY_RCPRCARDINGLIST = "rCPRCardingList";
    public static final String PROPERTY_RCPRMACHINELIST = "rCPRMachineList";
    public static final String PROPERTY_RCQACARDINGNEPSLIST = "rCQACardingnepsList";
    public static final String PROPERTY_RCQACOMBERNOILLIST = "rCQACombernoilList";
    public static final String PROPERTY_RCQACOMBERSULIST = "rCQACombersuList";
    public static final String PROPERTY_RCQAMISCOUNTLIST = "rCQAMisCountList";
    public static final String PROPERTY_RCQAOEBREAKAGELIST = "rCQAOebreakageList";
    public static final String PROPERTY_RCQARINGFRAMEBREAKLIST = "rCQARingframebreakList";
    public static final String PROPERTY_RCQARINGFRAMEIDLELIST = "rCQARingframeidleList";
    public static final String PROPERTY_RCQARSBAPERCENTAGELIST = "rCQARsbapercentageList";
    public static final String PROPERTY_RCQASIMPLEXSTRLIST = "rCQASimplexstrList";
    public static final String PROPERTY_RCQAWRAPBREAKLIST = "rCQAWrapbreakList";
    public static final String PROPERTY_RCMRLTPANELLIST = "rcmrLtpanelList";

    public RCPRShed() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCPRCARDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRMACHINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQACARDINGNEPSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQACOMBERNOILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQACOMBERSULIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQAMISCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQAOEBREAKAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQARINGFRAMEBREAKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQARINGFRAMEIDLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQARSBAPERCENTAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQASIMPLEXSTRLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQAWRAPBREAKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCMRLTPANELLIST, new ArrayList<Object>());
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

    public String getShedNo() {
        return (String) get(PROPERTY_SHEDNO);
    }

    public void setShedNo(String shedNo) {
        set(PROPERTY_SHEDNO, shedNo);
    }

    public RCPRUnit getUnit() {
        return (RCPRUnit) get(PROPERTY_UNIT);
    }

    public void setUnit(RCPRUnit unit) {
        set(PROPERTY_UNIT, unit);
    }

    @SuppressWarnings("unchecked")
    public List<RCPRCarding> getRCPRCardingList() {
        return (List<RCPRCarding>) get(PROPERTY_RCPRCARDINGLIST);
    }

    public void setRCPRCardingList(List<RCPRCarding> rCPRCardingList) {
        set(PROPERTY_RCPRCARDINGLIST, rCPRCardingList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPRMachine> getRCPRMachineList() {
        return (List<RCPRMachine>) get(PROPERTY_RCPRMACHINELIST);
    }

    public void setRCPRMachineList(List<RCPRMachine> rCPRMachineList) {
        set(PROPERTY_RCPRMACHINELIST, rCPRMachineList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaCardingneps> getRCQACardingnepsList() {
        return (List<RcqaCardingneps>) get(PROPERTY_RCQACARDINGNEPSLIST);
    }

    public void setRCQACardingnepsList(List<RcqaCardingneps> rCQACardingnepsList) {
        set(PROPERTY_RCQACARDINGNEPSLIST, rCQACardingnepsList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaCombernoil> getRCQACombernoilList() {
        return (List<RcqaCombernoil>) get(PROPERTY_RCQACOMBERNOILLIST);
    }

    public void setRCQACombernoilList(List<RcqaCombernoil> rCQACombernoilList) {
        set(PROPERTY_RCQACOMBERNOILLIST, rCQACombernoilList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaCombersu> getRCQACombersuList() {
        return (List<RcqaCombersu>) get(PROPERTY_RCQACOMBERSULIST);
    }

    public void setRCQACombersuList(List<RcqaCombersu> rCQACombersuList) {
        set(PROPERTY_RCQACOMBERSULIST, rCQACombersuList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaMisCount> getRCQAMisCountList() {
        return (List<RcqaMisCount>) get(PROPERTY_RCQAMISCOUNTLIST);
    }

    public void setRCQAMisCountList(List<RcqaMisCount> rCQAMisCountList) {
        set(PROPERTY_RCQAMISCOUNTLIST, rCQAMisCountList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaOebreakage> getRCQAOebreakageList() {
        return (List<RcqaOebreakage>) get(PROPERTY_RCQAOEBREAKAGELIST);
    }

    public void setRCQAOebreakageList(List<RcqaOebreakage> rCQAOebreakageList) {
        set(PROPERTY_RCQAOEBREAKAGELIST, rCQAOebreakageList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaRingframebreak> getRCQARingframebreakList() {
        return (List<RcqaRingframebreak>) get(PROPERTY_RCQARINGFRAMEBREAKLIST);
    }

    public void setRCQARingframebreakList(List<RcqaRingframebreak> rCQARingframebreakList) {
        set(PROPERTY_RCQARINGFRAMEBREAKLIST, rCQARingframebreakList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaRingframeidle> getRCQARingframeidleList() {
        return (List<RcqaRingframeidle>) get(PROPERTY_RCQARINGFRAMEIDLELIST);
    }

    public void setRCQARingframeidleList(List<RcqaRingframeidle> rCQARingframeidleList) {
        set(PROPERTY_RCQARINGFRAMEIDLELIST, rCQARingframeidleList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaRsbapercentage> getRCQARsbapercentageList() {
        return (List<RcqaRsbapercentage>) get(PROPERTY_RCQARSBAPERCENTAGELIST);
    }

    public void setRCQARsbapercentageList(List<RcqaRsbapercentage> rCQARsbapercentageList) {
        set(PROPERTY_RCQARSBAPERCENTAGELIST, rCQARsbapercentageList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaSimplexstr> getRCQASimplexstrList() {
        return (List<RcqaSimplexstr>) get(PROPERTY_RCQASIMPLEXSTRLIST);
    }

    public void setRCQASimplexstrList(List<RcqaSimplexstr> rCQASimplexstrList) {
        set(PROPERTY_RCQASIMPLEXSTRLIST, rCQASimplexstrList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaWrapbreak> getRCQAWrapbreakList() {
        return (List<RcqaWrapbreak>) get(PROPERTY_RCQAWRAPBREAKLIST);
    }

    public void setRCQAWrapbreakList(List<RcqaWrapbreak> rCQAWrapbreakList) {
        set(PROPERTY_RCQAWRAPBREAKLIST, rCQAWrapbreakList);
    }

    @SuppressWarnings("unchecked")
    public List<RcmrLtpanel> getRcmrLtpanelList() {
        return (List<RcmrLtpanel>) get(PROPERTY_RCMRLTPANELLIST);
    }

    public void setRcmrLtpanelList(List<RcmrLtpanel> rcmrLtpanelList) {
        set(PROPERTY_RCMRLTPANELLIST, rcmrLtpanelList);
    }

}
