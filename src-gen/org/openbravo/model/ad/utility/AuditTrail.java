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
package org.openbravo.model.ad.utility;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity AD_Audit_Trail (stored in table AD_Audit_Trail_V).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AuditTrail extends BaseOBObject implements ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Audit_Trail_V";
    public static final String ENTITY_NAME = "AD_Audit_Trail";
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
    public static final String PROPERTY_PROCESSTYPE = "processtype";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_ACTION = "action";
    public static final String PROPERTY_COLUMN = "column";
    public static final String PROPERTY_OLDCHAR = "oLDChar";
    public static final String PROPERTY_NEWCHAR = "nEWChar";
    public static final String PROPERTY_OLDNCHAR = "oLDNchar";
    public static final String PROPERTY_NEWNCHAR = "nEWNchar";
    public static final String PROPERTY_OLDNUMBER = "oLDNumber";
    public static final String PROPERTY_NEWNUMBER = "nEWNumber";
    public static final String PROPERTY_OLDDATE = "oLDDate";
    public static final String PROPERTY_NEWDATE = "nEWDate";
    public static final String PROPERTY_OLDTEXT = "oldText";
    public static final String PROPERTY_NEWTEXT = "newText";
    public static final String PROPERTY_PROCESSDESCRIPTION = "processDescription";
    public static final String PROPERTY_OLDVALUE = "oldValue";
    public static final String PROPERTY_NEWVALUE = "newValue";

    public AuditTrail() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_CREATEDBY, "0");
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_UPDATEDBY, "0");
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

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
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

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public Date getEventTime() {
        return (Date) get(PROPERTY_EVENTTIME);
    }

    public void setEventTime(Date eventTime) {
        set(PROPERTY_EVENTTIME, eventTime);
    }

    public String getProcesstype() {
        return (String) get(PROPERTY_PROCESSTYPE);
    }

    public void setProcesstype(String processtype) {
        set(PROPERTY_PROCESSTYPE, processtype);
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

    public Column getColumn() {
        return (Column) get(PROPERTY_COLUMN);
    }

    public void setColumn(Column column) {
        set(PROPERTY_COLUMN, column);
    }

    public String getOLDChar() {
        return (String) get(PROPERTY_OLDCHAR);
    }

    public void setOLDChar(String oLDChar) {
        set(PROPERTY_OLDCHAR, oLDChar);
    }

    public String getNEWChar() {
        return (String) get(PROPERTY_NEWCHAR);
    }

    public void setNEWChar(String nEWChar) {
        set(PROPERTY_NEWCHAR, nEWChar);
    }

    public String getOLDNchar() {
        return (String) get(PROPERTY_OLDNCHAR);
    }

    public void setOLDNchar(String oLDNchar) {
        set(PROPERTY_OLDNCHAR, oLDNchar);
    }

    public String getNEWNchar() {
        return (String) get(PROPERTY_NEWNCHAR);
    }

    public void setNEWNchar(String nEWNchar) {
        set(PROPERTY_NEWNCHAR, nEWNchar);
    }

    public BigDecimal getOLDNumber() {
        return (BigDecimal) get(PROPERTY_OLDNUMBER);
    }

    public void setOLDNumber(BigDecimal oLDNumber) {
        set(PROPERTY_OLDNUMBER, oLDNumber);
    }

    public BigDecimal getNEWNumber() {
        return (BigDecimal) get(PROPERTY_NEWNUMBER);
    }

    public void setNEWNumber(BigDecimal nEWNumber) {
        set(PROPERTY_NEWNUMBER, nEWNumber);
    }

    public Date getOLDDate() {
        return (Date) get(PROPERTY_OLDDATE);
    }

    public void setOLDDate(Date oLDDate) {
        set(PROPERTY_OLDDATE, oLDDate);
    }

    public Date getNEWDate() {
        return (Date) get(PROPERTY_NEWDATE);
    }

    public void setNEWDate(Date nEWDate) {
        set(PROPERTY_NEWDATE, nEWDate);
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

    public String getProcessDescription() {
        return (String) get(PROPERTY_PROCESSDESCRIPTION);
    }

    public void setProcessDescription(String processDescription) {
        set(PROPERTY_PROCESSDESCRIPTION, processDescription);
    }

    public String getOldValue() {
        return (String) get(PROPERTY_OLDVALUE);
    }

    public void setOldValue(String oldValue) {
        set(PROPERTY_OLDVALUE, oldValue);
    }

    public String getNewValue() {
        return (String) get(PROPERTY_NEWVALUE);
    }

    public void setNewValue(String newValue) {
        set(PROPERTY_NEWVALUE, newValue);
    }

}
