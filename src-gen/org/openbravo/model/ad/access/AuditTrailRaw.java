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
package org.openbravo.model.ad.access;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity AD_Audit_Trail_Raw (stored in table AD_Audit_Trail).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AuditTrailRaw extends BaseOBObject implements ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Audit_Trail";
    public static final String ENTITY_NAME = "AD_Audit_Trail_Raw";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_RECORDID = "recordID";
    public static final String PROPERTY_RECORDREVISION = "recordRevision";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_EVENTTIME = "eventTime";
    public static final String PROPERTY_PROCESSTYPE = "processType";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_ACTION = "action";
    public static final String PROPERTY_COLUMN = "column";
    public static final String PROPERTY_OLDCHAR = "oldChar";
    public static final String PROPERTY_NEWCHAR = "newChar";
    public static final String PROPERTY_OLDNCHAR = "oldNChar";
    public static final String PROPERTY_NEWNCHAR = "newNChar";
    public static final String PROPERTY_OLDNUMBER = "oldNumber";
    public static final String PROPERTY_NEWNUMBER = "newNumber";
    public static final String PROPERTY_OLDDATE = "oldDate";
    public static final String PROPERTY_NEWDATE = "newDate";
    public static final String PROPERTY_OLDTEXT = "oldText";
    public static final String PROPERTY_NEWTEXT = "newText";

    public AuditTrailRaw() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATEDBY, "0");
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

    public String getCreatedBy() {
        return (String) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(String createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public String getUpdatedBy() {
        return (String) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(String updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public String getTable() {
        return (String) get(PROPERTY_TABLE);
    }

    public void setTable(String table) {
        set(PROPERTY_TABLE, table);
    }

    public String getRecordID() {
        return (String) get(PROPERTY_RECORDID);
    }

    public void setRecordID(String recordID) {
        set(PROPERTY_RECORDID, recordID);
    }

    public Long getRecordRevision() {
        return (Long) get(PROPERTY_RECORDREVISION);
    }

    public void setRecordRevision(Long recordRevision) {
        set(PROPERTY_RECORDREVISION, recordRevision);
    }

    public String getUserContact() {
        return (String) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(String userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public Date getEventTime() {
        return (Date) get(PROPERTY_EVENTTIME);
    }

    public void setEventTime(Date eventTime) {
        set(PROPERTY_EVENTTIME, eventTime);
    }

    public String getProcessType() {
        return (String) get(PROPERTY_PROCESSTYPE);
    }

    public void setProcessType(String processType) {
        set(PROPERTY_PROCESSTYPE, processType);
    }

    public String getProcess() {
        return (String) get(PROPERTY_PROCESS);
    }

    public void setProcess(String process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getAction() {
        return (String) get(PROPERTY_ACTION);
    }

    public void setAction(String action) {
        set(PROPERTY_ACTION, action);
    }

    public String getColumn() {
        return (String) get(PROPERTY_COLUMN);
    }

    public void setColumn(String column) {
        set(PROPERTY_COLUMN, column);
    }

    public String getOldChar() {
        return (String) get(PROPERTY_OLDCHAR);
    }

    public void setOldChar(String oldChar) {
        set(PROPERTY_OLDCHAR, oldChar);
    }

    public String getNewChar() {
        return (String) get(PROPERTY_NEWCHAR);
    }

    public void setNewChar(String newChar) {
        set(PROPERTY_NEWCHAR, newChar);
    }

    public String getOldNChar() {
        return (String) get(PROPERTY_OLDNCHAR);
    }

    public void setOldNChar(String oldNChar) {
        set(PROPERTY_OLDNCHAR, oldNChar);
    }

    public String getNewNChar() {
        return (String) get(PROPERTY_NEWNCHAR);
    }

    public void setNewNChar(String newNChar) {
        set(PROPERTY_NEWNCHAR, newNChar);
    }

    public BigDecimal getOldNumber() {
        return (BigDecimal) get(PROPERTY_OLDNUMBER);
    }

    public void setOldNumber(BigDecimal oldNumber) {
        set(PROPERTY_OLDNUMBER, oldNumber);
    }

    public BigDecimal getNewNumber() {
        return (BigDecimal) get(PROPERTY_NEWNUMBER);
    }

    public void setNewNumber(BigDecimal newNumber) {
        set(PROPERTY_NEWNUMBER, newNumber);
    }

    public Date getOldDate() {
        return (Date) get(PROPERTY_OLDDATE);
    }

    public void setOldDate(Date oldDate) {
        set(PROPERTY_OLDDATE, oldDate);
    }

    public Date getNewDate() {
        return (Date) get(PROPERTY_NEWDATE);
    }

    public void setNewDate(Date newDate) {
        set(PROPERTY_NEWDATE, newDate);
    }

    public String getOldText() {
        return (String) get(PROPERTY_OLDTEXT);
    }

    public void setOldText(String oldText) {
        set(PROPERTY_OLDTEXT, oldText);
    }

    public String getNewText() {
        return (String) get(PROPERTY_NEWTEXT);
    }

    public void setNewText(String newText) {
        set(PROPERTY_NEWTEXT, newText);
    }

}
