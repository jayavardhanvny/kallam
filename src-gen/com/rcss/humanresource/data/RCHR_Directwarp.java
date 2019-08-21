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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCHR_Directwarp (stored in table RCHR_Directwarp).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Directwarp extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Directwarp";
    public static final String ENTITY_NAME = "RCHR_Directwarp";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WARPDATE = "warpdate";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_CONSTRUCTION = "construction";
    public static final String PROPERTY_SETLENGTH = "setlength";
    public static final String PROPERTY_NOOFCREELS = "noofcreels";
    public static final String PROPERTY_NOOFBEEMS = "noofbeems";
    public static final String PROPERTY_CONEWEIGHT = "coneweight";
    public static final String PROPERTY_CONELENGTH = "conelength";
    public static final String PROPERTY_MOFNO = "mofno";
    public static final String PROPERTY_TOTALENDS = "totalends";
    public static final String PROPERTY_SPEED = "speed";
    public static final String PROPERTY_RCHRJOBCARD = "rchrJobcard";
    public static final String PROPERTY_TOWRAPMTRS = "towrapmtrs";
    public static final String PROPERTY_WRAPDONEMTRS = "wrapdonemtrs";
    public static final String PROPERTY_REMAININGWRAPMTRS = "remainingwrapmtrs";
    public static final String PROPERTY_YARNSUPPLIIER = "yarnsuppliier";
    public static final String PROPERTY_BEAMCOUNT = "beamcount";
    public static final String PROPERTY_ISSTOPPED = "isstopped";
    public static final String PROPERTY_RCHRDETENTION = "rchrDetention";
    public static final String PROPERTY_FROMTIME = "fromtime";
    public static final String PROPERTY_TOTIME = "totime";
    public static final String PROPERTY_TIMEDIFFERENCE = "timedifference";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_COMPLETE = "complete";
    public static final String PROPERTY_RCHRDIRWARPBEAMLIST = "rCHRDirwarpBeamList";
    public static final String PROPERTY_RCHRDIRWARPCREELLIST = "rCHRDirwarpCreelList";
    public static final String PROPERTY_RCHRSIZINGSHEETLIST = "rCHRSizingsheetList";
    public static final String PROPERTY_RCHRAUTODRAWINGLIST = "rchrAutodrawingList";

    public RCHR_Directwarp() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BEAMCOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ISSTOPPED, false);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_COMPLETE, false);
        setDefaultValue(PROPERTY_RCHRDIRWARPBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRWARPCREELLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAUTODRAWINGLIST, new ArrayList<Object>());
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

    public Date getWarpdate() {
        return (Date) get(PROPERTY_WARPDATE);
    }

    public void setWarpdate(Date warpdate) {
        set(PROPERTY_WARPDATE, warpdate);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public String getConstruction() {
        return (String) get(PROPERTY_CONSTRUCTION);
    }

    public void setConstruction(String construction) {
        set(PROPERTY_CONSTRUCTION, construction);
    }

    public BigDecimal getSetlength() {
        return (BigDecimal) get(PROPERTY_SETLENGTH);
    }

    public void setSetlength(BigDecimal setlength) {
        set(PROPERTY_SETLENGTH, setlength);
    }

    public BigDecimal getNoofcreels() {
        return (BigDecimal) get(PROPERTY_NOOFCREELS);
    }

    public void setNoofcreels(BigDecimal noofcreels) {
        set(PROPERTY_NOOFCREELS, noofcreels);
    }

    public BigDecimal getNoofbeems() {
        return (BigDecimal) get(PROPERTY_NOOFBEEMS);
    }

    public void setNoofbeems(BigDecimal noofbeems) {
        set(PROPERTY_NOOFBEEMS, noofbeems);
    }

    public BigDecimal getConeweight() {
        return (BigDecimal) get(PROPERTY_CONEWEIGHT);
    }

    public void setConeweight(BigDecimal coneweight) {
        set(PROPERTY_CONEWEIGHT, coneweight);
    }

    public BigDecimal getConelength() {
        return (BigDecimal) get(PROPERTY_CONELENGTH);
    }

    public void setConelength(BigDecimal conelength) {
        set(PROPERTY_CONELENGTH, conelength);
    }

    public String getMofno() {
        return (String) get(PROPERTY_MOFNO);
    }

    public void setMofno(String mofno) {
        set(PROPERTY_MOFNO, mofno);
    }

    public BigDecimal getTotalends() {
        return (BigDecimal) get(PROPERTY_TOTALENDS);
    }

    public void setTotalends(BigDecimal totalends) {
        set(PROPERTY_TOTALENDS, totalends);
    }

    public BigDecimal getSpeed() {
        return (BigDecimal) get(PROPERTY_SPEED);
    }

    public void setSpeed(BigDecimal speed) {
        set(PROPERTY_SPEED, speed);
    }

    public RCHR_Jobcard getRchrJobcard() {
        return (RCHR_Jobcard) get(PROPERTY_RCHRJOBCARD);
    }

    public void setRchrJobcard(RCHR_Jobcard rchrJobcard) {
        set(PROPERTY_RCHRJOBCARD, rchrJobcard);
    }

    public BigDecimal getTowrapmtrs() {
        return (BigDecimal) get(PROPERTY_TOWRAPMTRS);
    }

    public void setTowrapmtrs(BigDecimal towrapmtrs) {
        set(PROPERTY_TOWRAPMTRS, towrapmtrs);
    }

    public BigDecimal getWrapdonemtrs() {
        return (BigDecimal) get(PROPERTY_WRAPDONEMTRS);
    }

    public void setWrapdonemtrs(BigDecimal wrapdonemtrs) {
        set(PROPERTY_WRAPDONEMTRS, wrapdonemtrs);
    }

    public BigDecimal getRemainingwrapmtrs() {
        return (BigDecimal) get(PROPERTY_REMAININGWRAPMTRS);
    }

    public void setRemainingwrapmtrs(BigDecimal remainingwrapmtrs) {
        set(PROPERTY_REMAININGWRAPMTRS, remainingwrapmtrs);
    }

    public String getYarnsuppliier() {
        return (String) get(PROPERTY_YARNSUPPLIIER);
    }

    public void setYarnsuppliier(String yarnsuppliier) {
        set(PROPERTY_YARNSUPPLIIER, yarnsuppliier);
    }

    public BigDecimal getBeamcount() {
        return (BigDecimal) get(PROPERTY_BEAMCOUNT);
    }

    public void setBeamcount(BigDecimal beamcount) {
        set(PROPERTY_BEAMCOUNT, beamcount);
    }

    public Boolean isStopped() {
        return (Boolean) get(PROPERTY_ISSTOPPED);
    }

    public void setStopped(Boolean isstopped) {
        set(PROPERTY_ISSTOPPED, isstopped);
    }

    public RCHR_Detention getRchrDetention() {
        return (RCHR_Detention) get(PROPERTY_RCHRDETENTION);
    }

    public void setRchrDetention(RCHR_Detention rchrDetention) {
        set(PROPERTY_RCHRDETENTION, rchrDetention);
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

    public String getTimedifference() {
        return (String) get(PROPERTY_TIMEDIFFERENCE);
    }

    public void setTimedifference(String timedifference) {
        set(PROPERTY_TIMEDIFFERENCE, timedifference);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isComplete() {
        return (Boolean) get(PROPERTY_COMPLETE);
    }

    public void setComplete(Boolean complete) {
        set(PROPERTY_COMPLETE, complete);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Dirwarp_Beam> getRCHRDirwarpBeamList() {
        return (List<RCHR_Dirwarp_Beam>) get(PROPERTY_RCHRDIRWARPBEAMLIST);
    }

    public void setRCHRDirwarpBeamList(List<RCHR_Dirwarp_Beam> rCHRDirwarpBeamList) {
        set(PROPERTY_RCHRDIRWARPBEAMLIST, rCHRDirwarpBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Dirwarp_Creel> getRCHRDirwarpCreelList() {
        return (List<RCHR_Dirwarp_Creel>) get(PROPERTY_RCHRDIRWARPCREELLIST);
    }

    public void setRCHRDirwarpCreelList(List<RCHR_Dirwarp_Creel> rCHRDirwarpCreelList) {
        set(PROPERTY_RCHRDIRWARPCREELLIST, rCHRDirwarpCreelList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizingsheet> getRCHRSizingsheetList() {
        return (List<RCHR_Sizingsheet>) get(PROPERTY_RCHRSIZINGSHEETLIST);
    }

    public void setRCHRSizingsheetList(List<RCHR_Sizingsheet> rCHRSizingsheetList) {
        set(PROPERTY_RCHRSIZINGSHEETLIST, rCHRSizingsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAutodrawing> getRchrAutodrawingList() {
        return (List<RchrAutodrawing>) get(PROPERTY_RCHRAUTODRAWINGLIST);
    }

    public void setRchrAutodrawingList(List<RchrAutodrawing> rchrAutodrawingList) {
        set(PROPERTY_RCHRAUTODRAWINGLIST, rchrAutodrawingList);
    }

}
