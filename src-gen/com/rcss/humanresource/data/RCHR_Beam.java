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

import java.math.BigDecimal;
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
 * Entity class for entity RCHR_Beam (stored in table RCHR_Beam).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Beam extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Beam";
    public static final String ENTITY_NAME = "RCHR_Beam";
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
    public static final String PROPERTY_TAREWEIGHT = "tareweight";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_MANUALBREAK = "manualbreak";
    public static final String PROPERTY_FRINDGETYPE = "frindgetype";
    public static final String PROPERTY_RCHRDIRWARPBEAMLIST = "rCHRDirwarpBeamList";
    public static final String PROPERTY_RCHRSIZINGBEAMLIST = "rCHRSizingBeamList";
    public static final String PROPERTY_RCHRAUTODRAWINGLIST = "rchrAutodrawingList";
    public static final String PROPERTY_RCHRBEAMLINESLIST = "rchrBeamLinesList";
    public static final String PROPERTY_RCHRLOOMSDATALIST = "rchrLoomsdataList";

    public RCHR_Beam() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ALERTSTATUS, "IDLE");
        setDefaultValue(PROPERTY_MANUALBREAK, false);
        setDefaultValue(PROPERTY_RCHRDIRWARPBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAUTODRAWINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBEAMLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLOOMSDATALIST, new ArrayList<Object>());
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

    public BigDecimal getTareweight() {
        return (BigDecimal) get(PROPERTY_TAREWEIGHT);
    }

    public void setTareweight(BigDecimal tareweight) {
        set(PROPERTY_TAREWEIGHT, tareweight);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isManualbreak() {
        return (Boolean) get(PROPERTY_MANUALBREAK);
    }

    public void setManualbreak(Boolean manualbreak) {
        set(PROPERTY_MANUALBREAK, manualbreak);
    }

    public String getFrindgetype() {
        return (String) get(PROPERTY_FRINDGETYPE);
    }

    public void setFrindgetype(String frindgetype) {
        set(PROPERTY_FRINDGETYPE, frindgetype);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Dirwarp_Beam> getRCHRDirwarpBeamList() {
        return (List<RCHR_Dirwarp_Beam>) get(PROPERTY_RCHRDIRWARPBEAMLIST);
    }

    public void setRCHRDirwarpBeamList(List<RCHR_Dirwarp_Beam> rCHRDirwarpBeamList) {
        set(PROPERTY_RCHRDIRWARPBEAMLIST, rCHRDirwarpBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizing_Beam> getRCHRSizingBeamList() {
        return (List<RCHR_Sizing_Beam>) get(PROPERTY_RCHRSIZINGBEAMLIST);
    }

    public void setRCHRSizingBeamList(List<RCHR_Sizing_Beam> rCHRSizingBeamList) {
        set(PROPERTY_RCHRSIZINGBEAMLIST, rCHRSizingBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAutodrawing> getRchrAutodrawingList() {
        return (List<RchrAutodrawing>) get(PROPERTY_RCHRAUTODRAWINGLIST);
    }

    public void setRchrAutodrawingList(List<RchrAutodrawing> rchrAutodrawingList) {
        set(PROPERTY_RCHRAUTODRAWINGLIST, rchrAutodrawingList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBeamLines> getRchrBeamLinesList() {
        return (List<RchrBeamLines>) get(PROPERTY_RCHRBEAMLINESLIST);
    }

    public void setRchrBeamLinesList(List<RchrBeamLines> rchrBeamLinesList) {
        set(PROPERTY_RCHRBEAMLINESLIST, rchrBeamLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrLoomsdata> getRchrLoomsdataList() {
        return (List<RchrLoomsdata>) get(PROPERTY_RCHRLOOMSDATALIST);
    }

    public void setRchrLoomsdataList(List<RchrLoomsdata> rchrLoomsdataList) {
        set(PROPERTY_RCHRLOOMSDATALIST, rchrLoomsdataList);
    }

}
