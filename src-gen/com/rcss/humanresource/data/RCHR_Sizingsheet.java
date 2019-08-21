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
 * Entity class for entity RCHR_Sizingsheet (stored in table RCHR_Sizingsheet).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Sizingsheet extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Sizingsheet";
    public static final String ENTITY_NAME = "RCHR_Sizingsheet";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SIZINGDATE = "sizingdate";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_RCHRDIRECTWARP = "rchrDirectwarp";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_CONSTRUCTION = "construction";
    public static final String PROPERTY_TOTALENDS = "totalends";
    public static final String PROPERTY_SETLENGTH = "setlength";
    public static final String PROPERTY_BEAMCOUNT = "beamcount";
    public static final String PROPERTY_GLM = "glm";
    public static final String PROPERTY_WARPERBEAMS = "warperbeams";
    public static final String PROPERTY_YARNSUPPLIIER = "yarnsuppliier";
    public static final String PROPERTY_FROMTIME = "fromtime";
    public static final String PROPERTY_TOTIME = "totime";
    public static final String PROPERTY_SHIFTINMINS = "shiftinmins";
    public static final String PROPERTY_UTILIZATION = "utilization";
    public static final String PROPERTY_RCHRJOBCARD = "rchrJobcard";
    public static final String PROPERTY_ISSTOPPED = "isstopped";
    public static final String PROPERTY_RCHRDETENTION = "rchrDetention";
    public static final String PROPERTY_DETFROMTIME = "detfromtime";
    public static final String PROPERTY_DETTOTIME = "dettotime";
    public static final String PROPERTY_DETTIMEDIFFERENCE = "dettimedifference";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_COMPLETE = "complete";
    public static final String PROPERTY_RCHRSIZINGBEAMLIST = "rCHRSizingBeamList";
    public static final String PROPERTY_RCHRCHEMICALMIXINGLIST = "rchrChemicalmixingList";

    public RCHR_Sizingsheet() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BEAMCOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_SHIFTINMINS, (long) 0);
        setDefaultValue(PROPERTY_UTILIZATION, new BigDecimal(0));
        setDefaultValue(PROPERTY_ISSTOPPED, false);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_COMPLETE, false);
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRCHEMICALMIXINGLIST, new ArrayList<Object>());
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

    public Date getSizingdate() {
        return (Date) get(PROPERTY_SIZINGDATE);
    }

    public void setSizingdate(Date sizingdate) {
        set(PROPERTY_SIZINGDATE, sizingdate);
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

    public RCHR_Directwarp getRchrDirectwarp() {
        return (RCHR_Directwarp) get(PROPERTY_RCHRDIRECTWARP);
    }

    public void setRchrDirectwarp(RCHR_Directwarp rchrDirectwarp) {
        set(PROPERTY_RCHRDIRECTWARP, rchrDirectwarp);
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

    public BigDecimal getTotalends() {
        return (BigDecimal) get(PROPERTY_TOTALENDS);
    }

    public void setTotalends(BigDecimal totalends) {
        set(PROPERTY_TOTALENDS, totalends);
    }

    public BigDecimal getSetlength() {
        return (BigDecimal) get(PROPERTY_SETLENGTH);
    }

    public void setSetlength(BigDecimal setlength) {
        set(PROPERTY_SETLENGTH, setlength);
    }

    public BigDecimal getBeamcount() {
        return (BigDecimal) get(PROPERTY_BEAMCOUNT);
    }

    public void setBeamcount(BigDecimal beamcount) {
        set(PROPERTY_BEAMCOUNT, beamcount);
    }

    public BigDecimal getGlm() {
        return (BigDecimal) get(PROPERTY_GLM);
    }

    public void setGlm(BigDecimal glm) {
        set(PROPERTY_GLM, glm);
    }

    public BigDecimal getWarperbeams() {
        return (BigDecimal) get(PROPERTY_WARPERBEAMS);
    }

    public void setWarperbeams(BigDecimal warperbeams) {
        set(PROPERTY_WARPERBEAMS, warperbeams);
    }

    public String getYarnsuppliier() {
        return (String) get(PROPERTY_YARNSUPPLIIER);
    }

    public void setYarnsuppliier(String yarnsuppliier) {
        set(PROPERTY_YARNSUPPLIIER, yarnsuppliier);
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

    public BigDecimal getUtilization() {
        return (BigDecimal) get(PROPERTY_UTILIZATION);
    }

    public void setUtilization(BigDecimal utilization) {
        set(PROPERTY_UTILIZATION, utilization);
    }

    public RCHR_Jobcard getRchrJobcard() {
        return (RCHR_Jobcard) get(PROPERTY_RCHRJOBCARD);
    }

    public void setRchrJobcard(RCHR_Jobcard rchrJobcard) {
        set(PROPERTY_RCHRJOBCARD, rchrJobcard);
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

    public Timestamp getDetfromtime() {
        return (Timestamp) get(PROPERTY_DETFROMTIME);
    }

    public void setDetfromtime(Timestamp detfromtime) {
        set(PROPERTY_DETFROMTIME, detfromtime);
    }

    public Timestamp getDettotime() {
        return (Timestamp) get(PROPERTY_DETTOTIME);
    }

    public void setDettotime(Timestamp dettotime) {
        set(PROPERTY_DETTOTIME, dettotime);
    }

    public String getDettimedifference() {
        return (String) get(PROPERTY_DETTIMEDIFFERENCE);
    }

    public void setDettimedifference(String dettimedifference) {
        set(PROPERTY_DETTIMEDIFFERENCE, dettimedifference);
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
    public List<RCHR_Sizing_Beam> getRCHRSizingBeamList() {
        return (List<RCHR_Sizing_Beam>) get(PROPERTY_RCHRSIZINGBEAMLIST);
    }

    public void setRCHRSizingBeamList(List<RCHR_Sizing_Beam> rCHRSizingBeamList) {
        set(PROPERTY_RCHRSIZINGBEAMLIST, rCHRSizingBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrChemicalmixing> getRchrChemicalmixingList() {
        return (List<RchrChemicalmixing>) get(PROPERTY_RCHRCHEMICALMIXINGLIST);
    }

    public void setRchrChemicalmixingList(List<RchrChemicalmixing> rchrChemicalmixingList) {
        set(PROPERTY_RCHRCHEMICALMIXINGLIST, rchrChemicalmixingList);
    }

}
