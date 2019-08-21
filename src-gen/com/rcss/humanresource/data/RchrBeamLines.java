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
 * Entity class for entity Rchr_Beam_Lines (stored in table Rchr_Beam_Lines).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrBeamLines extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Beam_Lines";
    public static final String ENTITY_NAME = "Rchr_Beam_Lines";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRBEAM = "rchrBeam";
    public static final String PROPERTY_RCHRSIZINGBEAM = "rchrSizingBeam";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_SIZINGSTARTDATE = "sizingstartdate";
    public static final String PROPERTY_LOOMSENDDATE = "loomsenddate";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_BEAMTYPE = "beamtype";
    public static final String PROPERTY_ADBEAMLENGTH = "adbeamlength";
    public static final String PROPERTY_RCHRLOOMSDATALIST = "rchrLoomsdataList";

    public RchrBeamLines() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public RCHR_Beam getRchrBeam() {
        return (RCHR_Beam) get(PROPERTY_RCHRBEAM);
    }

    public void setRchrBeam(RCHR_Beam rchrBeam) {
        set(PROPERTY_RCHRBEAM, rchrBeam);
    }

    public RCHR_Sizing_Beam getRchrSizingBeam() {
        return (RCHR_Sizing_Beam) get(PROPERTY_RCHRSIZINGBEAM);
    }

    public void setRchrSizingBeam(RCHR_Sizing_Beam rchrSizingBeam) {
        set(PROPERTY_RCHRSIZINGBEAM, rchrSizingBeam);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Date getSizingstartdate() {
        return (Date) get(PROPERTY_SIZINGSTARTDATE);
    }

    public void setSizingstartdate(Date sizingstartdate) {
        set(PROPERTY_SIZINGSTARTDATE, sizingstartdate);
    }

    public Date getLoomsenddate() {
        return (Date) get(PROPERTY_LOOMSENDDATE);
    }

    public void setLoomsenddate(Date loomsenddate) {
        set(PROPERTY_LOOMSENDDATE, loomsenddate);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public String getBeamtype() {
        return (String) get(PROPERTY_BEAMTYPE);
    }

    public void setBeamtype(String beamtype) {
        set(PROPERTY_BEAMTYPE, beamtype);
    }

    public BigDecimal getAdbeamlength() {
        return (BigDecimal) get(PROPERTY_ADBEAMLENGTH);
    }

    public void setAdbeamlength(BigDecimal adbeamlength) {
        set(PROPERTY_ADBEAMLENGTH, adbeamlength);
    }

    @SuppressWarnings("unchecked")
    public List<RchrLoomsdata> getRchrLoomsdataList() {
        return (List<RchrLoomsdata>) get(PROPERTY_RCHRLOOMSDATALIST);
    }

    public void setRchrLoomsdataList(List<RchrLoomsdata> rchrLoomsdataList) {
        set(PROPERTY_RCHRLOOMSDATALIST, rchrLoomsdataList);
    }

}
