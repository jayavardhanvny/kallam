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
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCHR_Dirwarp_Beam (stored in table RCHR_Dirwarp_Beam).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Dirwarp_Beam extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Dirwarp_Beam";
    public static final String ENTITY_NAME = "RCHR_Dirwarp_Beam";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRDIRECTWARP = "rchrDirectwarp";
    public static final String PROPERTY_RCHRDIRWARPCREEL = "rchrDirwarpCreel";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_TOTALENDS = "totalends";
    public static final String PROPERTY_BEAMLENGTH = "beamlength";
    public static final String PROPERTY_FROMTIME = "fromtime";
    public static final String PROPERTY_TOTIME = "totime";
    public static final String PROPERTY_TIMEDIFFERENCE = "timedifference";
    public static final String PROPERTY_GROSSWEIGHT = "grossWeight";
    public static final String PROPERTY_TAREWEIGHT = "tareWeight";
    public static final String PROPERTY_BEAMCOUNT = "beamCount";
    public static final String PROPERTY_CUTEND = "cutend";
    public static final String PROPERTY_ENTANGLEMENT = "entanglement";
    public static final String PROPERTY_SLOUGHOFF = "sloughoff";
    public static final String PROPERTY_WEAKPLACE = "weakplace";
    public static final String PROPERTY_POLYPROPYLINE = "polypropyline";
    public static final String PROPERTY_POORSPLICE = "poorsplice";
    public static final String PROPERTY_TIPDAMAGE = "tipdamage";
    public static final String PROPERTY_OTHERS = "others";
    public static final String PROPERTY_TOTALBREAKS = "totalbreaks";
    public static final String PROPERTY_BREAKSPER = "breaksPer";
    public static final String PROPERTY_NETWEIGHT = "netweight";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCHRBEAM = "rchrBeam";
    public static final String PROPERTY_SHIFTINMINS = "shiftinmins";
    public static final String PROPERTY_SPEED = "speed";
    public static final String PROPERTY_ASSTWARPEMPLOYEE = "asstwarpemployee";
    public static final String PROPERTY_ASSTCREELEMPLOYEE = "asstcreelemployee";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_BEAMDATE = "beamdate";

    public RCHR_Dirwarp_Beam() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BEAMCOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_CUTEND, (long) 0);
        setDefaultValue(PROPERTY_ENTANGLEMENT, (long) 0);
        setDefaultValue(PROPERTY_SLOUGHOFF, (long) 0);
        setDefaultValue(PROPERTY_WEAKPLACE, (long) 0);
        setDefaultValue(PROPERTY_POLYPROPYLINE, (long) 0);
        setDefaultValue(PROPERTY_POORSPLICE, (long) 0);
        setDefaultValue(PROPERTY_TIPDAMAGE, (long) 0);
        setDefaultValue(PROPERTY_OTHERS, (long) 0);
        setDefaultValue(PROPERTY_TOTALBREAKS, new BigDecimal(0));
        setDefaultValue(PROPERTY_BREAKSPER, new BigDecimal(0));
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

    public RCHR_Dirwarp_Creel getRchrDirwarpCreel() {
        return (RCHR_Dirwarp_Creel) get(PROPERTY_RCHRDIRWARPCREEL);
    }

    public void setRchrDirwarpCreel(RCHR_Dirwarp_Creel rchrDirwarpCreel) {
        set(PROPERTY_RCHRDIRWARPCREEL, rchrDirwarpCreel);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public BigDecimal getTotalends() {
        return (BigDecimal) get(PROPERTY_TOTALENDS);
    }

    public void setTotalends(BigDecimal totalends) {
        set(PROPERTY_TOTALENDS, totalends);
    }

    public BigDecimal getBeamlength() {
        return (BigDecimal) get(PROPERTY_BEAMLENGTH);
    }

    public void setBeamlength(BigDecimal beamlength) {
        set(PROPERTY_BEAMLENGTH, beamlength);
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

    public BigDecimal getBeamCount() {
        return (BigDecimal) get(PROPERTY_BEAMCOUNT);
    }

    public void setBeamCount(BigDecimal beamCount) {
        set(PROPERTY_BEAMCOUNT, beamCount);
    }

    public Long getCutend() {
        return (Long) get(PROPERTY_CUTEND);
    }

    public void setCutend(Long cutend) {
        set(PROPERTY_CUTEND, cutend);
    }

    public Long getEntanglement() {
        return (Long) get(PROPERTY_ENTANGLEMENT);
    }

    public void setEntanglement(Long entanglement) {
        set(PROPERTY_ENTANGLEMENT, entanglement);
    }

    public Long getSloughoff() {
        return (Long) get(PROPERTY_SLOUGHOFF);
    }

    public void setSloughoff(Long sloughoff) {
        set(PROPERTY_SLOUGHOFF, sloughoff);
    }

    public Long getWeakplace() {
        return (Long) get(PROPERTY_WEAKPLACE);
    }

    public void setWeakplace(Long weakplace) {
        set(PROPERTY_WEAKPLACE, weakplace);
    }

    public Long getPolypropyline() {
        return (Long) get(PROPERTY_POLYPROPYLINE);
    }

    public void setPolypropyline(Long polypropyline) {
        set(PROPERTY_POLYPROPYLINE, polypropyline);
    }

    public Long getPoorsplice() {
        return (Long) get(PROPERTY_POORSPLICE);
    }

    public void setPoorsplice(Long poorsplice) {
        set(PROPERTY_POORSPLICE, poorsplice);
    }

    public Long getTipdamage() {
        return (Long) get(PROPERTY_TIPDAMAGE);
    }

    public void setTipdamage(Long tipdamage) {
        set(PROPERTY_TIPDAMAGE, tipdamage);
    }

    public Long getOthers() {
        return (Long) get(PROPERTY_OTHERS);
    }

    public void setOthers(Long others) {
        set(PROPERTY_OTHERS, others);
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

    public BigDecimal getNetweight() {
        return (BigDecimal) get(PROPERTY_NETWEIGHT);
    }

    public void setNetweight(BigDecimal netweight) {
        set(PROPERTY_NETWEIGHT, netweight);
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

    public Long getShiftinmins() {
        return (Long) get(PROPERTY_SHIFTINMINS);
    }

    public void setShiftinmins(Long shiftinmins) {
        set(PROPERTY_SHIFTINMINS, shiftinmins);
    }

    public BigDecimal getSpeed() {
        return (BigDecimal) get(PROPERTY_SPEED);
    }

    public void setSpeed(BigDecimal speed) {
        set(PROPERTY_SPEED, speed);
    }

    public RchrEmployee getAsstwarpemployee() {
        return (RchrEmployee) get(PROPERTY_ASSTWARPEMPLOYEE);
    }

    public void setAsstwarpemployee(RchrEmployee asstwarpemployee) {
        set(PROPERTY_ASSTWARPEMPLOYEE, asstwarpemployee);
    }

    public RchrEmployee getAsstcreelemployee() {
        return (RchrEmployee) get(PROPERTY_ASSTCREELEMPLOYEE);
    }

    public void setAsstcreelemployee(RchrEmployee asstcreelemployee) {
        set(PROPERTY_ASSTCREELEMPLOYEE, asstcreelemployee);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public Date getBeamdate() {
        return (Date) get(PROPERTY_BEAMDATE);
    }

    public void setBeamdate(Date beamdate) {
        set(PROPERTY_BEAMDATE, beamdate);
    }

}
