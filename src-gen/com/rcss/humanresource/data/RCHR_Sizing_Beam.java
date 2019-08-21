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

import com.redcarpet.payroll.data.RCPLLoomCategory;
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
 * Entity class for entity RCHR_Sizing_Beam (stored in table RCHR_Sizing_Beam).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Sizing_Beam extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Sizing_Beam";
    public static final String ENTITY_NAME = "RCHR_Sizing_Beam";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRSIZINGSHEET = "rchrSizingsheet";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_BEAMDATE = "beamdate";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_FROMTIME = "fromtime";
    public static final String PROPERTY_TOTIME = "totime";
    public static final String PROPERTY_TIMEDIFFERENCE = "timedifference";
    public static final String PROPERTY_SHIFTINMINS = "shiftinmins";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCHRBEAM = "rchrBeam";
    public static final String PROPERTY_BEAMLENGTH = "beamlength";
    public static final String PROPERTY_GROSSWEIGHT = "grossWeight";
    public static final String PROPERTY_TAREWEIGHT = "tareWeight";
    public static final String PROPERTY_NETWEIGHT = "netweight";
    public static final String PROPERTY_RCHRDETENTION = "rchrDetention";
    public static final String PROPERTY_SIZEPICKUP = "sizepickup";
    public static final String PROPERTY_RCPLLOOMCATEGORY = "rcplLoomcategory";
    public static final String PROPERTY_UNSIZEDWEIGHT = "unsizedweight";
    public static final String PROPERTY_BACKSIZER = "backsizer";
    public static final String PROPERTY_MIXER = "mixer";
    public static final String PROPERTY_HELPER = "helper";
    public static final String PROPERTY_DETFROMTIME = "detfromtime";
    public static final String PROPERTY_DETTOTIME = "dettotime";
    public static final String PROPERTY_DETTIMEDIFFERENCE = "dettimedifference";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_COMPLETE = "complete";
    public static final String PROPERTY_RCHRAUTODRAWINGLIST = "rchrAutodrawingList";
    public static final String PROPERTY_RCHRBEAMLINESLIST = "rchrBeamLinesList";

    public RCHR_Sizing_Beam() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SIZEPICKUP, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_COMPLETE, false);
        setDefaultValue(PROPERTY_RCHRAUTODRAWINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBEAMLINESLIST, new ArrayList<Object>());
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

    public RCHR_Sizingsheet getRchrSizingsheet() {
        return (RCHR_Sizingsheet) get(PROPERTY_RCHRSIZINGSHEET);
    }

    public void setRchrSizingsheet(RCHR_Sizingsheet rchrSizingsheet) {
        set(PROPERTY_RCHRSIZINGSHEET, rchrSizingsheet);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Date getBeamdate() {
        return (Date) get(PROPERTY_BEAMDATE);
    }

    public void setBeamdate(Date beamdate) {
        set(PROPERTY_BEAMDATE, beamdate);
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

    public String getTimedifference() {
        return (String) get(PROPERTY_TIMEDIFFERENCE);
    }

    public void setTimedifference(String timedifference) {
        set(PROPERTY_TIMEDIFFERENCE, timedifference);
    }

    public Long getShiftinmins() {
        return (Long) get(PROPERTY_SHIFTINMINS);
    }

    public void setShiftinmins(Long shiftinmins) {
        set(PROPERTY_SHIFTINMINS, shiftinmins);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public RCHR_Beam getRchrBeam() {
        return (RCHR_Beam) get(PROPERTY_RCHRBEAM);
    }

    public void setRchrBeam(RCHR_Beam rchrBeam) {
        set(PROPERTY_RCHRBEAM, rchrBeam);
    }

    public BigDecimal getBeamlength() {
        return (BigDecimal) get(PROPERTY_BEAMLENGTH);
    }

    public void setBeamlength(BigDecimal beamlength) {
        set(PROPERTY_BEAMLENGTH, beamlength);
    }

    public BigDecimal getGrossWeight() {
        return (BigDecimal) get(PROPERTY_GROSSWEIGHT);
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        set(PROPERTY_GROSSWEIGHT, grossWeight);
    }

    public BigDecimal getTareWeight() {
        return (BigDecimal) get(PROPERTY_TAREWEIGHT);
    }

    public void setTareWeight(BigDecimal tareWeight) {
        set(PROPERTY_TAREWEIGHT, tareWeight);
    }

    public BigDecimal getNetweight() {
        return (BigDecimal) get(PROPERTY_NETWEIGHT);
    }

    public void setNetweight(BigDecimal netweight) {
        set(PROPERTY_NETWEIGHT, netweight);
    }

    public RCHR_Detention getRchrDetention() {
        return (RCHR_Detention) get(PROPERTY_RCHRDETENTION);
    }

    public void setRchrDetention(RCHR_Detention rchrDetention) {
        set(PROPERTY_RCHRDETENTION, rchrDetention);
    }

    public BigDecimal getSizepickup() {
        return (BigDecimal) get(PROPERTY_SIZEPICKUP);
    }

    public void setSizepickup(BigDecimal sizepickup) {
        set(PROPERTY_SIZEPICKUP, sizepickup);
    }

    public RCPLLoomCategory getRcplLoomcategory() {
        return (RCPLLoomCategory) get(PROPERTY_RCPLLOOMCATEGORY);
    }

    public void setRcplLoomcategory(RCPLLoomCategory rcplLoomcategory) {
        set(PROPERTY_RCPLLOOMCATEGORY, rcplLoomcategory);
    }

    public BigDecimal getUnsizedweight() {
        return (BigDecimal) get(PROPERTY_UNSIZEDWEIGHT);
    }

    public void setUnsizedweight(BigDecimal unsizedweight) {
        set(PROPERTY_UNSIZEDWEIGHT, unsizedweight);
    }

    public RchrEmployee getBacksizer() {
        return (RchrEmployee) get(PROPERTY_BACKSIZER);
    }

    public void setBacksizer(RchrEmployee backsizer) {
        set(PROPERTY_BACKSIZER, backsizer);
    }

    public RchrEmployee getMixer() {
        return (RchrEmployee) get(PROPERTY_MIXER);
    }

    public void setMixer(RchrEmployee mixer) {
        set(PROPERTY_MIXER, mixer);
    }

    public RchrEmployee getHelper() {
        return (RchrEmployee) get(PROPERTY_HELPER);
    }

    public void setHelper(RchrEmployee helper) {
        set(PROPERTY_HELPER, helper);
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

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isComplete() {
        return (Boolean) get(PROPERTY_COMPLETE);
    }

    public void setComplete(Boolean complete) {
        set(PROPERTY_COMPLETE, complete);
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

}
