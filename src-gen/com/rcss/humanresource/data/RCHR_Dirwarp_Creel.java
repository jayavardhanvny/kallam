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
 * Entity class for entity RCHR_Dirwarp_Creel (stored in table RCHR_Dirwarp_Creel).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Dirwarp_Creel extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Dirwarp_Creel";
    public static final String ENTITY_NAME = "RCHR_Dirwarp_Creel";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRDIRECTWARP = "rchrDirectwarp";
    public static final String PROPERTY_FROMTIME = "fromtime";
    public static final String PROPERTY_TOTIME = "totime";
    public static final String PROPERTY_TIMEDIFFERENCE = "timedifference";
    public static final String PROPERTY_UTILIZATION = "utilization";
    public static final String PROPERTY_TOTALBREAKS = "totalbreaks";
    public static final String PROPERTY_BREAKSPER = "breaksPer";
    public static final String PROPERTY_YARNWEIGHT = "yarnWeight";
    public static final String PROPERTY_WARPYARNWEIGHT = "warpyarnWeight";
    public static final String PROPERTY_REMTARNWEIGHT = "remtarnWeight";
    public static final String PROPERTY_REMIMENTPER = "remimentPer";
    public static final String PROPERTY_HARDWASTE = "hardwaste";
    public static final String PROPERTY_HARDWASTEPER = "hardwastePer";
    public static final String PROPERTY_SHIFTINMINS = "shiftinmins";
    public static final String PROPERTY_CREELLENGTH = "creellength";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_RCHRDIRWARPBEAMLIST = "rCHRDirwarpBeamList";

    public RCHR_Dirwarp_Creel() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_UTILIZATION, new BigDecimal(0));
        setDefaultValue(PROPERTY_BREAKSPER, new BigDecimal(0));
        setDefaultValue(PROPERTY_REMIMENTPER, new BigDecimal(0));
        setDefaultValue(PROPERTY_HARDWASTEPER, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREELLENGTH, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCHRDIRWARPBEAMLIST, new ArrayList<Object>());
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

    public RCHR_Directwarp getRchrDirectwarp() {
        return (RCHR_Directwarp) get(PROPERTY_RCHRDIRECTWARP);
    }

    public void setRchrDirectwarp(RCHR_Directwarp rchrDirectwarp) {
        set(PROPERTY_RCHRDIRECTWARP, rchrDirectwarp);
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

    public BigDecimal getUtilization() {
        return (BigDecimal) get(PROPERTY_UTILIZATION);
    }

    public void setUtilization(BigDecimal utilization) {
        set(PROPERTY_UTILIZATION, utilization);
    }

    public BigDecimal getTotalbreaks() {
        return (BigDecimal) get(PROPERTY_TOTALBREAKS);
    }

    public void setTotalbreaks(BigDecimal totalbreaks) {
        set(PROPERTY_TOTALBREAKS, totalbreaks);
    }

    public BigDecimal getBreaksPer() {
        return (BigDecimal) get(PROPERTY_BREAKSPER);
    }

    public void setBreaksPer(BigDecimal breaksPer) {
        set(PROPERTY_BREAKSPER, breaksPer);
    }

    public BigDecimal getYarnWeight() {
        return (BigDecimal) get(PROPERTY_YARNWEIGHT);
    }

    public void setYarnWeight(BigDecimal yarnWeight) {
        set(PROPERTY_YARNWEIGHT, yarnWeight);
    }

    public BigDecimal getWarpyarnWeight() {
        return (BigDecimal) get(PROPERTY_WARPYARNWEIGHT);
    }

    public void setWarpyarnWeight(BigDecimal warpyarnWeight) {
        set(PROPERTY_WARPYARNWEIGHT, warpyarnWeight);
    }

    public BigDecimal getRemtarnWeight() {
        return (BigDecimal) get(PROPERTY_REMTARNWEIGHT);
    }

    public void setRemtarnWeight(BigDecimal remtarnWeight) {
        set(PROPERTY_REMTARNWEIGHT, remtarnWeight);
    }

    public BigDecimal getRemimentPer() {
        return (BigDecimal) get(PROPERTY_REMIMENTPER);
    }

    public void setRemimentPer(BigDecimal remimentPer) {
        set(PROPERTY_REMIMENTPER, remimentPer);
    }

    public BigDecimal getHardwaste() {
        return (BigDecimal) get(PROPERTY_HARDWASTE);
    }

    public void setHardwaste(BigDecimal hardwaste) {
        set(PROPERTY_HARDWASTE, hardwaste);
    }

    public BigDecimal getHardwastePer() {
        return (BigDecimal) get(PROPERTY_HARDWASTEPER);
    }

    public void setHardwastePer(BigDecimal hardwastePer) {
        set(PROPERTY_HARDWASTEPER, hardwastePer);
    }

    public Long getShiftinmins() {
        return (Long) get(PROPERTY_SHIFTINMINS);
    }

    public void setShiftinmins(Long shiftinmins) {
        set(PROPERTY_SHIFTINMINS, shiftinmins);
    }

    public BigDecimal getCreellength() {
        return (BigDecimal) get(PROPERTY_CREELLENGTH);
    }

    public void setCreellength(BigDecimal creellength) {
        set(PROPERTY_CREELLENGTH, creellength);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Dirwarp_Beam> getRCHRDirwarpBeamList() {
        return (List<RCHR_Dirwarp_Beam>) get(PROPERTY_RCHRDIRWARPBEAMLIST);
    }

    public void setRCHRDirwarpBeamList(List<RCHR_Dirwarp_Beam> rCHRDirwarpBeamList) {
        set(PROPERTY_RCHRDIRWARPBEAMLIST, rCHRDirwarpBeamList);
    }

}
