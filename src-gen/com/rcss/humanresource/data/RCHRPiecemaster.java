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
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.calendar.Year;
/**
 * Entity class for entity RCHR_Piecemaster (stored in table RCHR_Piecemaster).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRPiecemaster extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Piecemaster";
    public static final String ENTITY_NAME = "RCHR_Piecemaster";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_FROMDATE = "fromDate";
    public static final String PROPERTY_TODATE = "toDate";
    public static final String PROPERTY_PIECECHAR = "piecechar";
    public static final String PROPERTY_RANGE = "range";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_YEAR = "year";
    public static final String PROPERTY_ISNEEDED = "isneeded";
    public static final String PROPERTY_EXTENDPIECENOS = "extendpiecenos";
    public static final String PROPERTY_RCHRPIECENOLISTLIST = "rCHRPiecenolistList";

    public RCHRPiecemaster() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RANGE, (long) 10000);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_EXTENDPIECENOS, false);
        setDefaultValue(PROPERTY_RCHRPIECENOLISTLIST, new ArrayList<Object>());
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Date getFromDate() {
        return (Date) get(PROPERTY_FROMDATE);
    }

    public void setFromDate(Date fromDate) {
        set(PROPERTY_FROMDATE, fromDate);
    }

    public Date getToDate() {
        return (Date) get(PROPERTY_TODATE);
    }

    public void setToDate(Date toDate) {
        set(PROPERTY_TODATE, toDate);
    }

    public String getPiecechar() {
        return (String) get(PROPERTY_PIECECHAR);
    }

    public void setPiecechar(String piecechar) {
        set(PROPERTY_PIECECHAR, piecechar);
    }

    public Long getRange() {
        return (Long) get(PROPERTY_RANGE);
    }

    public void setRange(Long range) {
        set(PROPERTY_RANGE, range);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public Year getYear() {
        return (Year) get(PROPERTY_YEAR);
    }

    public void setYear(Year year) {
        set(PROPERTY_YEAR, year);
    }

    public Long getIsneeded() {
        return (Long) get(PROPERTY_ISNEEDED);
    }

    public void setIsneeded(Long isneeded) {
        set(PROPERTY_ISNEEDED, isneeded);
    }

    public Boolean isExtendpiecenos() {
        return (Boolean) get(PROPERTY_EXTENDPIECENOS);
    }

    public void setExtendpiecenos(Boolean extendpiecenos) {
        set(PROPERTY_EXTENDPIECENOS, extendpiecenos);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRPiecenolist> getRCHRPiecenolistList() {
        return (List<RCHRPiecenolist>) get(PROPERTY_RCHRPIECENOLISTLIST);
    }

    public void setRCHRPiecenolistList(List<RCHRPiecenolist> rCHRPiecenolistList) {
        set(PROPERTY_RCHRPIECENOLISTLIST, rCHRPiecenolistList);
    }

}
