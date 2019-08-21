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
package org.openbravo.model.ad.system;

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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADHeartbeatLog (stored in table AD_Heartbeat_Log).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class HeartbeatLog extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Heartbeat_Log";
    public static final String ENTITY_NAME = "ADHeartbeatLog";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SYSTEMIDENTIFIER = "systemIdentifier";
    public static final String PROPERTY_ENABLEHEARTBEAT = "enableHeartbeat";
    public static final String PROPERTY_PROXYREQUIRED = "proxyRequired";
    public static final String PROPERTY_PROXYSERVER = "proxyServer";
    public static final String PROPERTY_PROXYPORT = "proxyPort";
    public static final String PROPERTY_ACTIVITYRATE = "activityRate";
    public static final String PROPERTY_COMPLEXITYRATE = "complexityRate";
    public static final String PROPERTY_OPERATINGSYSTEM = "operatingSystem";
    public static final String PROPERTY_OPERATINGSYSTEMVERSION = "operatingSystemVersion";
    public static final String PROPERTY_DATABASE = "database";
    public static final String PROPERTY_DATABASEVERSION = "databaseVersion";
    public static final String PROPERTY_SERVLETCONTAINER = "servletContainer";
    public static final String PROPERTY_SERVLETCONTAINERVERSION = "servletContainerVersion";
    public static final String PROPERTY_WEBSERVER = "webServer";
    public static final String PROPERTY_WEBSERVERVERSION = "webServerVersion";
    public static final String PROPERTY_JAVAVERSION = "javaVersion";
    public static final String PROPERTY_ANTVERSION = "antVersion";
    public static final String PROPERTY_OPENBRAVOVERSION = "openbravoVersion";
    public static final String PROPERTY_OPENBRAVOINSTALLMODE = "openbravoInstallMode";
    public static final String PROPERTY_NUMBEROFREGISTEREDUSERS = "numberOfRegisteredUsers";
    public static final String PROPERTY_CODEREVISION = "codeRevision";
    public static final String PROPERTY_BEATTYPE = "beatType";
    public static final String PROPERTY_DATABASEIDENTIFIER = "databaseIdentifier";
    public static final String PROPERTY_MACIDENTIFIER = "macIdentifier";
    public static final String PROPERTY_INSTALLEDMODULES = "installedModules";
    public static final String PROPERTY_ACTIVATIONKEYIDENTIFIER = "activationKeyIdentifier";
    public static final String PROPERTY_FIRSTLOGIN = "firstLogin";
    public static final String PROPERTY_LASTLOGIN = "lastLogin";
    public static final String PROPERTY_TOTALLOGINS = "totalLogins";
    public static final String PROPERTY_TOTALLOGINSLASTMONTH = "totalLoginsLastMonth";
    public static final String PROPERTY_CONCURRENTUSERSAVERAGE = "concurrentUsersAverage";
    public static final String PROPERTY_USAGEPERCENTAGE = "usagePercentage";
    public static final String PROPERTY_MAXIMUMCONCURRENTUSERS = "maximumConcurrentUsers";
    public static final String PROPERTY_NUMBEROFCLIENTS = "numberOfClients";
    public static final String PROPERTY_NUMBEROFORGANIZATIONS = "numberOfOrganizations";
    public static final String PROPERTY_USAGEAUDITENABLED = "usageAuditEnabled";
    public static final String PROPERTY_INSTANCEPURPOSE = "instancePurpose";
    public static final String PROPERTY_REJECTEDLOGINSDUECONCUSERS = "rejectedLoginsDueConcUsers";
    public static final String PROPERTY_INSTANCENUMBER = "instanceNumber";
    public static final String PROPERTY_ENABLECUSTOMQUERIES = "enableCustomQueries";
    public static final String PROPERTY_WSCALLSMAXIMUM = "wSCallsMaximum";
    public static final String PROPERTY_WSCALLSAVERAGE = "wSCallsAverage";
    public static final String PROPERTY_CONNECTORCALLSMAX = "connectorCallsMax";
    public static final String PROPERTY_CONNECTORCALLSAVERAGE = "connectorCallsAverage";
    public static final String PROPERTY_WSREJECTEDMAXIMUM = "wSRejectedMaximum";
    public static final String PROPERTY_WSREJECTEDAVERAGE = "wSRejectedAverage";
    public static final String PROPERTY_ADHEARTBEATLOGCUSTOMQUERYLIST = "aDHeartbeatLogCustomQueryList";

    public HeartbeatLog() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ENABLEHEARTBEAT, false);
        setDefaultValue(PROPERTY_PROXYREQUIRED, false);
        setDefaultValue(PROPERTY_CODEREVISION, "0");
        setDefaultValue(PROPERTY_BEATTYPE, "U");
        setDefaultValue(PROPERTY_USAGEAUDITENABLED, false);
        setDefaultValue(PROPERTY_ENABLECUSTOMQUERIES, true);
        setDefaultValue(PROPERTY_ADHEARTBEATLOGCUSTOMQUERYLIST, new ArrayList<Object>());
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

    public String getSystemIdentifier() {
        return (String) get(PROPERTY_SYSTEMIDENTIFIER);
    }

    public void setSystemIdentifier(String systemIdentifier) {
        set(PROPERTY_SYSTEMIDENTIFIER, systemIdentifier);
    }

    public Boolean isEnableHeartbeat() {
        return (Boolean) get(PROPERTY_ENABLEHEARTBEAT);
    }

    public void setEnableHeartbeat(Boolean enableHeartbeat) {
        set(PROPERTY_ENABLEHEARTBEAT, enableHeartbeat);
    }

    public Boolean isProxyRequired() {
        return (Boolean) get(PROPERTY_PROXYREQUIRED);
    }

    public void setProxyRequired(Boolean proxyRequired) {
        set(PROPERTY_PROXYREQUIRED, proxyRequired);
    }

    public String getProxyServer() {
        return (String) get(PROPERTY_PROXYSERVER);
    }

    public void setProxyServer(String proxyServer) {
        set(PROPERTY_PROXYSERVER, proxyServer);
    }

    public Long getProxyPort() {
        return (Long) get(PROPERTY_PROXYPORT);
    }

    public void setProxyPort(Long proxyPort) {
        set(PROPERTY_PROXYPORT, proxyPort);
    }

    public BigDecimal getActivityRate() {
        return (BigDecimal) get(PROPERTY_ACTIVITYRATE);
    }

    public void setActivityRate(BigDecimal activityRate) {
        set(PROPERTY_ACTIVITYRATE, activityRate);
    }

    public BigDecimal getComplexityRate() {
        return (BigDecimal) get(PROPERTY_COMPLEXITYRATE);
    }

    public void setComplexityRate(BigDecimal complexityRate) {
        set(PROPERTY_COMPLEXITYRATE, complexityRate);
    }

    public String getOperatingSystem() {
        return (String) get(PROPERTY_OPERATINGSYSTEM);
    }

    public void setOperatingSystem(String operatingSystem) {
        set(PROPERTY_OPERATINGSYSTEM, operatingSystem);
    }

    public String getOperatingSystemVersion() {
        return (String) get(PROPERTY_OPERATINGSYSTEMVERSION);
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        set(PROPERTY_OPERATINGSYSTEMVERSION, operatingSystemVersion);
    }

    public String getDatabase() {
        return (String) get(PROPERTY_DATABASE);
    }

    public void setDatabase(String database) {
        set(PROPERTY_DATABASE, database);
    }

    public String getDatabaseVersion() {
        return (String) get(PROPERTY_DATABASEVERSION);
    }

    public void setDatabaseVersion(String databaseVersion) {
        set(PROPERTY_DATABASEVERSION, databaseVersion);
    }

    public String getServletContainer() {
        return (String) get(PROPERTY_SERVLETCONTAINER);
    }

    public void setServletContainer(String servletContainer) {
        set(PROPERTY_SERVLETCONTAINER, servletContainer);
    }

    public String getServletContainerVersion() {
        return (String) get(PROPERTY_SERVLETCONTAINERVERSION);
    }

    public void setServletContainerVersion(String servletContainerVersion) {
        set(PROPERTY_SERVLETCONTAINERVERSION, servletContainerVersion);
    }

    public String getWebServer() {
        return (String) get(PROPERTY_WEBSERVER);
    }

    public void setWebServer(String webServer) {
        set(PROPERTY_WEBSERVER, webServer);
    }

    public String getWebServerVersion() {
        return (String) get(PROPERTY_WEBSERVERVERSION);
    }

    public void setWebServerVersion(String webServerVersion) {
        set(PROPERTY_WEBSERVERVERSION, webServerVersion);
    }

    public String getJavaVersion() {
        return (String) get(PROPERTY_JAVAVERSION);
    }

    public void setJavaVersion(String javaVersion) {
        set(PROPERTY_JAVAVERSION, javaVersion);
    }

    public String getAntVersion() {
        return (String) get(PROPERTY_ANTVERSION);
    }

    public void setAntVersion(String antVersion) {
        set(PROPERTY_ANTVERSION, antVersion);
    }

    public String getOpenbravoVersion() {
        return (String) get(PROPERTY_OPENBRAVOVERSION);
    }

    public void setOpenbravoVersion(String openbravoVersion) {
        set(PROPERTY_OPENBRAVOVERSION, openbravoVersion);
    }

    public String getOpenbravoInstallMode() {
        return (String) get(PROPERTY_OPENBRAVOINSTALLMODE);
    }

    public void setOpenbravoInstallMode(String openbravoInstallMode) {
        set(PROPERTY_OPENBRAVOINSTALLMODE, openbravoInstallMode);
    }

    public Long getNumberOfRegisteredUsers() {
        return (Long) get(PROPERTY_NUMBEROFREGISTEREDUSERS);
    }

    public void setNumberOfRegisteredUsers(Long numberOfRegisteredUsers) {
        set(PROPERTY_NUMBEROFREGISTEREDUSERS, numberOfRegisteredUsers);
    }

    public String getCodeRevision() {
        return (String) get(PROPERTY_CODEREVISION);
    }

    public void setCodeRevision(String codeRevision) {
        set(PROPERTY_CODEREVISION, codeRevision);
    }

    public String getBeatType() {
        return (String) get(PROPERTY_BEATTYPE);
    }

    public void setBeatType(String beatType) {
        set(PROPERTY_BEATTYPE, beatType);
    }

    public String getDatabaseIdentifier() {
        return (String) get(PROPERTY_DATABASEIDENTIFIER);
    }

    public void setDatabaseIdentifier(String databaseIdentifier) {
        set(PROPERTY_DATABASEIDENTIFIER, databaseIdentifier);
    }

    public String getMacIdentifier() {
        return (String) get(PROPERTY_MACIDENTIFIER);
    }

    public void setMacIdentifier(String macIdentifier) {
        set(PROPERTY_MACIDENTIFIER, macIdentifier);
    }

    public String getInstalledModules() {
        return (String) get(PROPERTY_INSTALLEDMODULES);
    }

    public void setInstalledModules(String installedModules) {
        set(PROPERTY_INSTALLEDMODULES, installedModules);
    }

    public String getActivationKeyIdentifier() {
        return (String) get(PROPERTY_ACTIVATIONKEYIDENTIFIER);
    }

    public void setActivationKeyIdentifier(String activationKeyIdentifier) {
        set(PROPERTY_ACTIVATIONKEYIDENTIFIER, activationKeyIdentifier);
    }

    public Date getFirstLogin() {
        return (Date) get(PROPERTY_FIRSTLOGIN);
    }

    public void setFirstLogin(Date firstLogin) {
        set(PROPERTY_FIRSTLOGIN, firstLogin);
    }

    public Date getLastLogin() {
        return (Date) get(PROPERTY_LASTLOGIN);
    }

    public void setLastLogin(Date lastLogin) {
        set(PROPERTY_LASTLOGIN, lastLogin);
    }

    public Long getTotalLogins() {
        return (Long) get(PROPERTY_TOTALLOGINS);
    }

    public void setTotalLogins(Long totalLogins) {
        set(PROPERTY_TOTALLOGINS, totalLogins);
    }

    public Long getTotalLoginsLastMonth() {
        return (Long) get(PROPERTY_TOTALLOGINSLASTMONTH);
    }

    public void setTotalLoginsLastMonth(Long totalLoginsLastMonth) {
        set(PROPERTY_TOTALLOGINSLASTMONTH, totalLoginsLastMonth);
    }

    public BigDecimal getConcurrentUsersAverage() {
        return (BigDecimal) get(PROPERTY_CONCURRENTUSERSAVERAGE);
    }

    public void setConcurrentUsersAverage(BigDecimal concurrentUsersAverage) {
        set(PROPERTY_CONCURRENTUSERSAVERAGE, concurrentUsersAverage);
    }

    public BigDecimal getUsagePercentage() {
        return (BigDecimal) get(PROPERTY_USAGEPERCENTAGE);
    }

    public void setUsagePercentage(BigDecimal usagePercentage) {
        set(PROPERTY_USAGEPERCENTAGE, usagePercentage);
    }

    public Long getMaximumConcurrentUsers() {
        return (Long) get(PROPERTY_MAXIMUMCONCURRENTUSERS);
    }

    public void setMaximumConcurrentUsers(Long maximumConcurrentUsers) {
        set(PROPERTY_MAXIMUMCONCURRENTUSERS, maximumConcurrentUsers);
    }

    public Long getNumberOfClients() {
        return (Long) get(PROPERTY_NUMBEROFCLIENTS);
    }

    public void setNumberOfClients(Long numberOfClients) {
        set(PROPERTY_NUMBEROFCLIENTS, numberOfClients);
    }

    public Long getNumberOfOrganizations() {
        return (Long) get(PROPERTY_NUMBEROFORGANIZATIONS);
    }

    public void setNumberOfOrganizations(Long numberOfOrganizations) {
        set(PROPERTY_NUMBEROFORGANIZATIONS, numberOfOrganizations);
    }

    public Boolean isUsageAuditEnabled() {
        return (Boolean) get(PROPERTY_USAGEAUDITENABLED);
    }

    public void setUsageAuditEnabled(Boolean usageAuditEnabled) {
        set(PROPERTY_USAGEAUDITENABLED, usageAuditEnabled);
    }

    public String getInstancePurpose() {
        return (String) get(PROPERTY_INSTANCEPURPOSE);
    }

    public void setInstancePurpose(String instancePurpose) {
        set(PROPERTY_INSTANCEPURPOSE, instancePurpose);
    }

    public Long getRejectedLoginsDueConcUsers() {
        return (Long) get(PROPERTY_REJECTEDLOGINSDUECONCUSERS);
    }

    public void setRejectedLoginsDueConcUsers(Long rejectedLoginsDueConcUsers) {
        set(PROPERTY_REJECTEDLOGINSDUECONCUSERS, rejectedLoginsDueConcUsers);
    }

    public Long getInstanceNumber() {
        return (Long) get(PROPERTY_INSTANCENUMBER);
    }

    public void setInstanceNumber(Long instanceNumber) {
        set(PROPERTY_INSTANCENUMBER, instanceNumber);
    }

    public Boolean isEnableCustomQueries() {
        return (Boolean) get(PROPERTY_ENABLECUSTOMQUERIES);
    }

    public void setEnableCustomQueries(Boolean enableCustomQueries) {
        set(PROPERTY_ENABLECUSTOMQUERIES, enableCustomQueries);
    }

    public Long getWSCallsMaximum() {
        return (Long) get(PROPERTY_WSCALLSMAXIMUM);
    }

    public void setWSCallsMaximum(Long wSCallsMaximum) {
        set(PROPERTY_WSCALLSMAXIMUM, wSCallsMaximum);
    }

    public BigDecimal getWSCallsAverage() {
        return (BigDecimal) get(PROPERTY_WSCALLSAVERAGE);
    }

    public void setWSCallsAverage(BigDecimal wSCallsAverage) {
        set(PROPERTY_WSCALLSAVERAGE, wSCallsAverage);
    }

    public Long getConnectorCallsMax() {
        return (Long) get(PROPERTY_CONNECTORCALLSMAX);
    }

    public void setConnectorCallsMax(Long connectorCallsMax) {
        set(PROPERTY_CONNECTORCALLSMAX, connectorCallsMax);
    }

    public BigDecimal getConnectorCallsAverage() {
        return (BigDecimal) get(PROPERTY_CONNECTORCALLSAVERAGE);
    }

    public void setConnectorCallsAverage(BigDecimal connectorCallsAverage) {
        set(PROPERTY_CONNECTORCALLSAVERAGE, connectorCallsAverage);
    }

    public Long getWSRejectedMaximum() {
        return (Long) get(PROPERTY_WSREJECTEDMAXIMUM);
    }

    public void setWSRejectedMaximum(Long wSRejectedMaximum) {
        set(PROPERTY_WSREJECTEDMAXIMUM, wSRejectedMaximum);
    }

    public BigDecimal getWSRejectedAverage() {
        return (BigDecimal) get(PROPERTY_WSREJECTEDAVERAGE);
    }

    public void setWSRejectedAverage(BigDecimal wSRejectedAverage) {
        set(PROPERTY_WSREJECTEDAVERAGE, wSRejectedAverage);
    }

    @SuppressWarnings("unchecked")
    public List<HeartbeatLogCustomQuery> getADHeartbeatLogCustomQueryList() {
        return (List<HeartbeatLogCustomQuery>) get(PROPERTY_ADHEARTBEATLOGCUSTOMQUERYLIST);
    }

    public void setADHeartbeatLogCustomQueryList(List<HeartbeatLogCustomQuery> aDHeartbeatLogCustomQueryList) {
        set(PROPERTY_ADHEARTBEATLOGCUSTOMQUERYLIST, aDHeartbeatLogCustomQueryList);
    }

}
