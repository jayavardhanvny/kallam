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
package org.openbravo.model.common.enterprise;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
/**
 * Entity class for entity EmailServerConfiguration (stored in table C_POC_CONFIGURATION).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EmailServerConfiguration extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_POC_CONFIGURATION";
    public static final String ENTITY_NAME = "EmailServerConfiguration";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SMTPSERVER = "smtpServer";
    public static final String PROPERTY_SMTPSERVERACCOUNT = "smtpServerAccount";
    public static final String PROPERTY_SMTPSERVERPASSWORD = "smtpServerPassword";
    public static final String PROPERTY_SMTPAUTHENTIFICATION = "sMTPAuthentification";
    public static final String PROPERTY_SMTPSERVERSENDERADDRESS = "smtpServerSenderAddress";
    public static final String PROPERTY_SMTPCONNECTIONSECURITY = "smtpConnectionSecurity";
    public static final String PROPERTY_SMTPPORT = "smtpPort";

    public EmailServerConfiguration() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SMTPAUTHENTIFICATION, false);
        setDefaultValue(PROPERTY_SMTPPORT, (long) 25);
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

    public String getSmtpServer() {
        return (String) get(PROPERTY_SMTPSERVER);
    }

    public void setSmtpServer(String smtpServer) {
        set(PROPERTY_SMTPSERVER, smtpServer);
    }

    public String getSmtpServerAccount() {
        return (String) get(PROPERTY_SMTPSERVERACCOUNT);
    }

    public void setSmtpServerAccount(String smtpServerAccount) {
        set(PROPERTY_SMTPSERVERACCOUNT, smtpServerAccount);
    }

    public String getSmtpServerPassword() {
        return (String) get(PROPERTY_SMTPSERVERPASSWORD);
    }

    public void setSmtpServerPassword(String smtpServerPassword) {
        set(PROPERTY_SMTPSERVERPASSWORD, smtpServerPassword);
    }

    public Boolean isSMTPAuthentification() {
        return (Boolean) get(PROPERTY_SMTPAUTHENTIFICATION);
    }

    public void setSMTPAuthentification(Boolean sMTPAuthentification) {
        set(PROPERTY_SMTPAUTHENTIFICATION, sMTPAuthentification);
    }

    public String getSmtpServerSenderAddress() {
        return (String) get(PROPERTY_SMTPSERVERSENDERADDRESS);
    }

    public void setSmtpServerSenderAddress(String smtpServerSenderAddress) {
        set(PROPERTY_SMTPSERVERSENDERADDRESS, smtpServerSenderAddress);
    }

    public String getSmtpConnectionSecurity() {
        return (String) get(PROPERTY_SMTPCONNECTIONSECURITY);
    }

    public void setSmtpConnectionSecurity(String smtpConnectionSecurity) {
        set(PROPERTY_SMTPCONNECTIONSECURITY, smtpConnectionSecurity);
    }

    public Long getSmtpPort() {
        return (Long) get(PROPERTY_SMTPPORT);
    }

    public void setSmtpPort(Long smtpPort) {
        set(PROPERTY_SMTPPORT, smtpPort);
    }

}
