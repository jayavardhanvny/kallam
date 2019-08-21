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

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADSystemInformation (stored in table AD_System_Info).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class SystemInformation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_System_Info";
    public static final String ENTITY_NAME = "ADSystemInformation";
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
    public static final String PROPERTY_POSTPONEDATE = "postponeDate";
    public static final String PROPERTY_PROXYREQUIRED = "proxyRequired";
    public static final String PROPERTY_PROXYSERVER = "proxyServer";
    public static final String PROPERTY_PROXYPORT = "proxyPort";
    public static final String PROPERTY_TESTHEARTBEAT = "testHeartbeat";
    public static final String PROPERTY_ANTVERSION = "antVersion";
    public static final String PROPERTY_OPENBRAVOVERSION = "openbravoVersion";
    public static final String PROPERTY_OPENBRAVOINSTALLMODE = "openbravoInstallMode";
    public static final String PROPERTY_CODEREVISION = "codeRevision";
    public static final String PROPERTY_SERVLETCONTAINER = "servletContainer";
    public static final String PROPERTY_SERVLETCONTAINERVERSION = "servletContainerVersion";
    public static final String PROPERTY_WEBSERVER = "webServer";
    public static final String PROPERTY_WEBSERVERVERSION = "webServerVersion";
    public static final String PROPERTY_CUSTOMIZATIONALLOWED = "customizationAllowed";
    public static final String PROPERTY_LASTBUILD = "lastBuild";
    public static final String PROPERTY_LASTDBUPDATE = "lastDBUpdate";
    public static final String PROPERTY_DBCHECKSUM = "dBChecksum";
    public static final String PROPERTY_YOURCOMPANYLOGINIMAGE = "yourCompanyLoginImage";
    public static final String PROPERTY_YOURITSERVICELOGINIMAGE = "yourItServiceLoginImage";
    public static final String PROPERTY_YOURCOMPANYMENUIMAGE = "yourCompanyMenuImage";
    public static final String PROPERTY_YOURCOMPANYBIGIMAGE = "yourCompanyBigImage";
    public static final String PROPERTY_YOURCOMPANYDOCUMENTIMAGE = "yourCompanyDocumentImage";
    public static final String PROPERTY_SUPPORTCONTACT = "supportContact";
    public static final String PROPERTY_SYSTEMSTATUS = "systemStatus";
    public static final String PROPERTY_MATURITYUPDATE = "maturityUpdate";
    public static final String PROPERTY_MATURITYSEARCH = "maturitySearch";
    public static final String PROPERTY_REQUIRESPROXYAUTHENTICATION = "requiresProxyAuthentication";
    public static final String PROPERTY_PROXYUSER = "proxyUser";
    public static final String PROPERTY_PROXYPASSWORD = "proxyPassword";
    public static final String PROPERTY_INSTANCEPURPOSE = "instancePurpose";
    public static final String PROPERTY_ISUSAGEAUDITENABLED = "isusageauditenabled";
    public static final String PROPERTY_CHANGEINSTANCEPURPOSE = "changeInstancePurpose";
    public static final String PROPERTY_SHOWCOMMUNITYBRANDING = "showCommunityBranding";
    public static final String PROPERTY_PRODUCTIONBANNERIMAGE = "productionBannerImage";
    public static final String PROPERTY_SHOWFORGELOGOINLOGIN = "showForgeLogoInLogin";
    public static final String PROPERTY_YOURCOMPANYURL = "yourCompanyURL";
    public static final String PROPERTY_ENABLECUSTOMQUERIES = "enableCustomQueries";
    public static final String PROPERTY_RCHRINDEVELOPMENT = "rchrIndevelopment";

    public SystemInformation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ENABLEHEARTBEAT, false);
        setDefaultValue(PROPERTY_PROXYREQUIRED, false);
        setDefaultValue(PROPERTY_TESTHEARTBEAT, "N");
        setDefaultValue(PROPERTY_CODEREVISION, "0");
        setDefaultValue(PROPERTY_CUSTOMIZATIONALLOWED, false);
        setDefaultValue(PROPERTY_LASTBUILD, new Date());
        setDefaultValue(PROPERTY_LASTDBUPDATE, new Date());
        setDefaultValue(PROPERTY_MATURITYUPDATE, "500");
        setDefaultValue(PROPERTY_MATURITYSEARCH, "500");
        setDefaultValue(PROPERTY_REQUIRESPROXYAUTHENTICATION, false);
        setDefaultValue(PROPERTY_ISUSAGEAUDITENABLED, true);
        setDefaultValue(PROPERTY_CHANGEINSTANCEPURPOSE, true);
        setDefaultValue(PROPERTY_SHOWCOMMUNITYBRANDING, true);
        setDefaultValue(PROPERTY_SHOWFORGELOGOINLOGIN, true);
        setDefaultValue(PROPERTY_ENABLECUSTOMQUERIES, true);
        setDefaultValue(PROPERTY_RCHRINDEVELOPMENT, false);
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

    public Date getPostponeDate() {
        return (Date) get(PROPERTY_POSTPONEDATE);
    }

    public void setPostponeDate(Date postponeDate) {
        set(PROPERTY_POSTPONEDATE, postponeDate);
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

    public String getTestHeartbeat() {
        return (String) get(PROPERTY_TESTHEARTBEAT);
    }

    public void setTestHeartbeat(String testHeartbeat) {
        set(PROPERTY_TESTHEARTBEAT, testHeartbeat);
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

    public String getCodeRevision() {
        return (String) get(PROPERTY_CODEREVISION);
    }

    public void setCodeRevision(String codeRevision) {
        set(PROPERTY_CODEREVISION, codeRevision);
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

    public Boolean isCustomizationAllowed() {
        return (Boolean) get(PROPERTY_CUSTOMIZATIONALLOWED);
    }

    public void setCustomizationAllowed(Boolean customizationAllowed) {
        set(PROPERTY_CUSTOMIZATIONALLOWED, customizationAllowed);
    }

    public Date getLastBuild() {
        return (Date) get(PROPERTY_LASTBUILD);
    }

    public void setLastBuild(Date lastBuild) {
        set(PROPERTY_LASTBUILD, lastBuild);
    }

    public Date getLastDBUpdate() {
        return (Date) get(PROPERTY_LASTDBUPDATE);
    }

    public void setLastDBUpdate(Date lastDBUpdate) {
        set(PROPERTY_LASTDBUPDATE, lastDBUpdate);
    }

    public String getDBChecksum() {
        return (String) get(PROPERTY_DBCHECKSUM);
    }

    public void setDBChecksum(String dBChecksum) {
        set(PROPERTY_DBCHECKSUM, dBChecksum);
    }

    public Image getYourCompanyLoginImage() {
        return (Image) get(PROPERTY_YOURCOMPANYLOGINIMAGE);
    }

    public void setYourCompanyLoginImage(Image yourCompanyLoginImage) {
        set(PROPERTY_YOURCOMPANYLOGINIMAGE, yourCompanyLoginImage);
    }

    public Image getYourItServiceLoginImage() {
        return (Image) get(PROPERTY_YOURITSERVICELOGINIMAGE);
    }

    public void setYourItServiceLoginImage(Image yourItServiceLoginImage) {
        set(PROPERTY_YOURITSERVICELOGINIMAGE, yourItServiceLoginImage);
    }

    public Image getYourCompanyMenuImage() {
        return (Image) get(PROPERTY_YOURCOMPANYMENUIMAGE);
    }

    public void setYourCompanyMenuImage(Image yourCompanyMenuImage) {
        set(PROPERTY_YOURCOMPANYMENUIMAGE, yourCompanyMenuImage);
    }

    public Image getYourCompanyBigImage() {
        return (Image) get(PROPERTY_YOURCOMPANYBIGIMAGE);
    }

    public void setYourCompanyBigImage(Image yourCompanyBigImage) {
        set(PROPERTY_YOURCOMPANYBIGIMAGE, yourCompanyBigImage);
    }

    public Image getYourCompanyDocumentImage() {
        return (Image) get(PROPERTY_YOURCOMPANYDOCUMENTIMAGE);
    }

    public void setYourCompanyDocumentImage(Image yourCompanyDocumentImage) {
        set(PROPERTY_YOURCOMPANYDOCUMENTIMAGE, yourCompanyDocumentImage);
    }

    public String getSupportContact() {
        return (String) get(PROPERTY_SUPPORTCONTACT);
    }

    public void setSupportContact(String supportContact) {
        set(PROPERTY_SUPPORTCONTACT, supportContact);
    }

    public String getSystemStatus() {
        return (String) get(PROPERTY_SYSTEMSTATUS);
    }

    public void setSystemStatus(String systemStatus) {
        set(PROPERTY_SYSTEMSTATUS, systemStatus);
    }

    public String getMaturityUpdate() {
        return (String) get(PROPERTY_MATURITYUPDATE);
    }

    public void setMaturityUpdate(String maturityUpdate) {
        set(PROPERTY_MATURITYUPDATE, maturityUpdate);
    }

    public String getMaturitySearch() {
        return (String) get(PROPERTY_MATURITYSEARCH);
    }

    public void setMaturitySearch(String maturitySearch) {
        set(PROPERTY_MATURITYSEARCH, maturitySearch);
    }

    public Boolean isRequiresProxyAuthentication() {
        return (Boolean) get(PROPERTY_REQUIRESPROXYAUTHENTICATION);
    }

    public void setRequiresProxyAuthentication(Boolean requiresProxyAuthentication) {
        set(PROPERTY_REQUIRESPROXYAUTHENTICATION, requiresProxyAuthentication);
    }

    public String getProxyUser() {
        return (String) get(PROPERTY_PROXYUSER);
    }

    public void setProxyUser(String proxyUser) {
        set(PROPERTY_PROXYUSER, proxyUser);
    }

    public String getProxyPassword() {
        return (String) get(PROPERTY_PROXYPASSWORD);
    }

    public void setProxyPassword(String proxyPassword) {
        set(PROPERTY_PROXYPASSWORD, proxyPassword);
    }

    public String getInstancePurpose() {
        return (String) get(PROPERTY_INSTANCEPURPOSE);
    }

    public void setInstancePurpose(String instancePurpose) {
        set(PROPERTY_INSTANCEPURPOSE, instancePurpose);
    }

    public Boolean isUsageauditenabled() {
        return (Boolean) get(PROPERTY_ISUSAGEAUDITENABLED);
    }

    public void setUsageauditenabled(Boolean isusageauditenabled) {
        set(PROPERTY_ISUSAGEAUDITENABLED, isusageauditenabled);
    }

    public Boolean isChangeInstancePurpose() {
        return (Boolean) get(PROPERTY_CHANGEINSTANCEPURPOSE);
    }

    public void setChangeInstancePurpose(Boolean changeInstancePurpose) {
        set(PROPERTY_CHANGEINSTANCEPURPOSE, changeInstancePurpose);
    }

    public Boolean isShowCommunityBranding() {
        return (Boolean) get(PROPERTY_SHOWCOMMUNITYBRANDING);
    }

    public void setShowCommunityBranding(Boolean showCommunityBranding) {
        set(PROPERTY_SHOWCOMMUNITYBRANDING, showCommunityBranding);
    }

    public Image getProductionBannerImage() {
        return (Image) get(PROPERTY_PRODUCTIONBANNERIMAGE);
    }

    public void setProductionBannerImage(Image productionBannerImage) {
        set(PROPERTY_PRODUCTIONBANNERIMAGE, productionBannerImage);
    }

    public Boolean isShowForgeLogoInLogin() {
        return (Boolean) get(PROPERTY_SHOWFORGELOGOINLOGIN);
    }

    public void setShowForgeLogoInLogin(Boolean showForgeLogoInLogin) {
        set(PROPERTY_SHOWFORGELOGOINLOGIN, showForgeLogoInLogin);
    }

    public String getYourCompanyURL() {
        return (String) get(PROPERTY_YOURCOMPANYURL);
    }

    public void setYourCompanyURL(String yourCompanyURL) {
        set(PROPERTY_YOURCOMPANYURL, yourCompanyURL);
    }

    public Boolean isEnableCustomQueries() {
        return (Boolean) get(PROPERTY_ENABLECUSTOMQUERIES);
    }

    public void setEnableCustomQueries(Boolean enableCustomQueries) {
        set(PROPERTY_ENABLECUSTOMQUERIES, enableCustomQueries);
    }

    public Boolean isRchrIndevelopment() {
        return (Boolean) get(PROPERTY_RCHRINDEVELOPMENT);
    }

    public void setRchrIndevelopment(Boolean rchrIndevelopment) {
        set(PROPERTY_RCHRINDEVELOPMENT, rchrIndevelopment);
    }

}
