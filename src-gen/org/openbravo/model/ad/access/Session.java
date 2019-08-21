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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADSession (stored in table AD_Session).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Session extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Session";
    public static final String ENTITY_NAME = "ADSession";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WEBSESSION = "webSession";
    public static final String PROPERTY_REMOTEADDRESS = "remoteAddress";
    public static final String PROPERTY_REMOTEHOST = "remoteHost";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_SESSIONACTIVE = "sessionActive";
    public static final String PROPERTY_SERVERURL = "serverUrl";
    public static final String PROPERTY_LASTPING = "lastPing";
    public static final String PROPERTY_USERNAME = "username";
    public static final String PROPERTY_LOGINSTATUS = "loginStatus";
    public static final String PROPERTY_ADSESSIONUSAGEAUDITLIST = "aDSessionUsageAuditList";

    public Session() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_SESSIONACTIVE, false);
        setDefaultValue(PROPERTY_ADSESSIONUSAGEAUDITLIST, new ArrayList<Object>());
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

    public String getWebSession() {
        return (String) get(PROPERTY_WEBSESSION);
    }

    public void setWebSession(String webSession) {
        set(PROPERTY_WEBSESSION, webSession);
    }

    public String getRemoteAddress() {
        return (String) get(PROPERTY_REMOTEADDRESS);
    }

    public void setRemoteAddress(String remoteAddress) {
        set(PROPERTY_REMOTEADDRESS, remoteAddress);
    }

    public String getRemoteHost() {
        return (String) get(PROPERTY_REMOTEHOST);
    }

    public void setRemoteHost(String remoteHost) {
        set(PROPERTY_REMOTEHOST, remoteHost);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isSessionActive() {
        return (Boolean) get(PROPERTY_SESSIONACTIVE);
    }

    public void setSessionActive(Boolean sessionActive) {
        set(PROPERTY_SESSIONACTIVE, sessionActive);
    }

    public String getServerUrl() {
        return (String) get(PROPERTY_SERVERURL);
    }

    public void setServerUrl(String serverUrl) {
        set(PROPERTY_SERVERURL, serverUrl);
    }

    public Date getLastPing() {
        return (Date) get(PROPERTY_LASTPING);
    }

    public void setLastPing(Date lastPing) {
        set(PROPERTY_LASTPING, lastPing);
    }

    public String getUsername() {
        return (String) get(PROPERTY_USERNAME);
    }

    public void setUsername(String username) {
        set(PROPERTY_USERNAME, username);
    }

    public String getLoginStatus() {
        return (String) get(PROPERTY_LOGINSTATUS);
    }

    public void setLoginStatus(String loginStatus) {
        set(PROPERTY_LOGINSTATUS, loginStatus);
    }

    @SuppressWarnings("unchecked")
    public List<SessionUsageAudit> getADSessionUsageAuditList() {
        return (List<SessionUsageAudit>) get(PROPERTY_ADSESSIONUSAGEAUDITLIST);
    }

    public void setADSessionUsageAuditList(List<SessionUsageAudit> aDSessionUsageAuditList) {
        set(PROPERTY_ADSESSIONUSAGEAUDITLIST, aDSessionUsageAuditList);
    }

}
