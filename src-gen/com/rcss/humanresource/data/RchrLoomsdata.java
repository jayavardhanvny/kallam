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

import com.redcarpet.payroll.data.RCPLLoom;

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
 * Entity class for entity Rchr_Loomsdata (stored in table Rchr_Loomsdata).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrLoomsdata extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Loomsdata";
    public static final String ENTITY_NAME = "Rchr_Loomsdata";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCPLLOOM = "rcplLoom";
    public static final String PROPERTY_LOOMSSTARTDATE = "loomsstartdate";
    public static final String PROPERTY_RCHRBEAM = "rchrBeam";
    public static final String PROPERTY_LOOMSENDDATE = "loomsenddate";
    public static final String PROPERTY_BEAMLENGTH = "beamlength";
    public static final String PROPERTY_GROSSWEIGHT = "grossWeight";
    public static final String PROPERTY_TAREWEIGHT = "tareWeight";
    public static final String PROPERTY_NETWEIGHT = "netweight";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_RCHRBEAMLINES = "rchrBeamLines";
    public static final String PROPERTY_RCHRJOBCARD = "rchrJobcard";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_FROMTIME = "fromtime";
    public static final String PROPERTY_CUTBEAM = "cutbeam";
    public static final String PROPERTY_TOTALPIECELENGTH = "totalpiecelength";
    public static final String PROPERTY_RCHRLOOMSDATALINESLIST = "rchrLoomsdataLinesList";

    public RchrLoomsdata() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_CUTBEAM, false);
        setDefaultValue(PROPERTY_RCHRLOOMSDATALINESLIST, new ArrayList<Object>());
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

    public RCPLLoom getRcplLoom() {
        return (RCPLLoom) get(PROPERTY_RCPLLOOM);
    }

    public void setRcplLoom(RCPLLoom rcplLoom) {
        set(PROPERTY_RCPLLOOM, rcplLoom);
    }

    public Date getLoomsstartdate() {
        return (Date) get(PROPERTY_LOOMSSTARTDATE);
    }

    public void setLoomsstartdate(Date loomsstartdate) {
        set(PROPERTY_LOOMSSTARTDATE, loomsstartdate);
    }

    public RCHR_Beam getRchrBeam() {
        return (RCHR_Beam) get(PROPERTY_RCHRBEAM);
    }

    public void setRchrBeam(RCHR_Beam rchrBeam) {
        set(PROPERTY_RCHRBEAM, rchrBeam);
    }

    public Date getLoomsenddate() {
        return (Date) get(PROPERTY_LOOMSENDDATE);
    }

    public void setLoomsenddate(Date loomsenddate) {
        set(PROPERTY_LOOMSENDDATE, loomsenddate);
    }

    public Long getBeamlength() {
        return (Long) get(PROPERTY_BEAMLENGTH);
    }

    public void setBeamlength(Long beamlength) {
        set(PROPERTY_BEAMLENGTH, beamlength);
    }

    public Long getGrossWeight() {
        return (Long) get(PROPERTY_GROSSWEIGHT);
    }

    public void setGrossWeight(Long grossWeight) {
        set(PROPERTY_GROSSWEIGHT, grossWeight);
    }

    public Long getTareWeight() {
        return (Long) get(PROPERTY_TAREWEIGHT);
    }

    public void setTareWeight(Long tareWeight) {
        set(PROPERTY_TAREWEIGHT, tareWeight);
    }

    public Long getNetweight() {
        return (Long) get(PROPERTY_NETWEIGHT);
    }

    public void setNetweight(Long netweight) {
        set(PROPERTY_NETWEIGHT, netweight);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public RchrBeamLines getRchrBeamLines() {
        return (RchrBeamLines) get(PROPERTY_RCHRBEAMLINES);
    }

    public void setRchrBeamLines(RchrBeamLines rchrBeamLines) {
        set(PROPERTY_RCHRBEAMLINES, rchrBeamLines);
    }

    public RCHR_Jobcard getRchrJobcard() {
        return (RCHR_Jobcard) get(PROPERTY_RCHRJOBCARD);
    }

    public void setRchrJobcard(RCHR_Jobcard rchrJobcard) {
        set(PROPERTY_RCHRJOBCARD, rchrJobcard);
    }

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public Timestamp getFromtime() {
        return (Timestamp) get(PROPERTY_FROMTIME);
    }

    public void setFromtime(Timestamp fromtime) {
        set(PROPERTY_FROMTIME, fromtime);
    }

    public Boolean isCutbeam() {
        return (Boolean) get(PROPERTY_CUTBEAM);
    }

    public void setCutbeam(Boolean cutbeam) {
        set(PROPERTY_CUTBEAM, cutbeam);
    }

    public Long getTotalpiecelength() {
        return (Long) get(PROPERTY_TOTALPIECELENGTH);
    }

    public void setTotalpiecelength(Long totalpiecelength) {
        set(PROPERTY_TOTALPIECELENGTH, totalpiecelength);
    }

    @SuppressWarnings("unchecked")
    public List<RchrLoomsdataLines> getRchrLoomsdataLinesList() {
        return (List<RchrLoomsdataLines>) get(PROPERTY_RCHRLOOMSDATALINESLIST);
    }

    public void setRchrLoomsdataLinesList(List<RchrLoomsdataLines> rchrLoomsdataLinesList) {
        set(PROPERTY_RCHRLOOMSDATALINESLIST, rchrLoomsdataLinesList);
    }

}
