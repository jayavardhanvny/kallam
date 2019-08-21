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
 * Entity class for entity Rchr_Loomsdata_Lines (stored in table Rchr_Loomsdata_Lines).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrLoomsdataLines extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Loomsdata_Lines";
    public static final String ENTITY_NAME = "Rchr_Loomsdata_Lines";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRLOOMSDATA = "rchrLoomsdata";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_ACTUALMTS = "actualmts";
    public static final String PROPERTY_RCHRINSPROLLTYPE = "rchrInsprolltype";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_ISLAST = "islast";
    public static final String PROPERTY_TOTIME = "totime";
    public static final String PROPERTY_LOOMSENDDATE = "loomsenddate";
    public static final String PROPERTY_COMPLETE = "complete";

    public RchrLoomsdataLines() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_ISLAST, false);
        setDefaultValue(PROPERTY_COMPLETE, false);
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

    public RchrLoomsdata getRchrLoomsdata() {
        return (RchrLoomsdata) get(PROPERTY_RCHRLOOMSDATA);
    }

    public void setRchrLoomsdata(RchrLoomsdata rchrLoomsdata) {
        set(PROPERTY_RCHRLOOMSDATA, rchrLoomsdata);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Long getActualmts() {
        return (Long) get(PROPERTY_ACTUALMTS);
    }

    public void setActualmts(Long actualmts) {
        set(PROPERTY_ACTUALMTS, actualmts);
    }

    public RCHRInsprolltype getRchrInsprolltype() {
        return (RCHRInsprolltype) get(PROPERTY_RCHRINSPROLLTYPE);
    }

    public void setRchrInsprolltype(RCHRInsprolltype rchrInsprolltype) {
        set(PROPERTY_RCHRINSPROLLTYPE, rchrInsprolltype);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isLast() {
        return (Boolean) get(PROPERTY_ISLAST);
    }

    public void setLast(Boolean islast) {
        set(PROPERTY_ISLAST, islast);
    }

    public Timestamp getTotime() {
        return (Timestamp) get(PROPERTY_TOTIME);
    }

    public void setTotime(Timestamp totime) {
        set(PROPERTY_TOTIME, totime);
    }

    public Date getLoomsenddate() {
        return (Date) get(PROPERTY_LOOMSENDDATE);
    }

    public void setLoomsenddate(Date loomsenddate) {
        set(PROPERTY_LOOMSENDDATE, loomsenddate);
    }

    public Boolean isComplete() {
        return (Boolean) get(PROPERTY_COMPLETE);
    }

    public void setComplete(Boolean complete) {
        set(PROPERTY_COMPLETE, complete);
    }

}
