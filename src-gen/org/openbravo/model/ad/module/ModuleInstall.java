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
package org.openbravo.model.ad.module;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.Language;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADModuleInstall (stored in table AD_Module_Install).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ModuleInstall extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Module_Install";
    public static final String ENTITY_NAME = "ADModuleInstall";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_VERSION = "version";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_URL = "uRL";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_LICENSETEXT = "licenseText";
    public static final String PROPERTY_INDEVELOPMENT = "inDevelopment";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_JAVAPACKAGE = "javaPackage";
    public static final String PROPERTY_LICENSETYPE = "licenseType";
    public static final String PROPERTY_AUTHOR = "author";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_AVAILABLEUPDATE = "availableUpdate";
    public static final String PROPERTY_TRANSLATIONREQUIRED = "translationRequired";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_HASCHARTOFACCOUNTS = "hasChartOfAccounts";
    public static final String PROPERTY_ISTRANSLATIONMODULE = "isTranslationModule";
    public static final String PROPERTY_HASREFERENCEDATA = "hasReferenceData";
    public static final String PROPERTY_REGISTERMODULE = "registerModule";
    public static final String PROPERTY_UPDATEINFORMATION = "updateInformation";
    public static final String PROPERTY_UPDATEVERSION = "updateVersion";
    public static final String PROPERTY_REFERENCEDATADESCRIPTION = "referenceDataDescription";
    public static final String PROPERTY_VERSIONLABEL = "versionLabel";
    public static final String PROPERTY_VERSIONID = "versionID";
    public static final String PROPERTY_ISCOMMERCIAL = "isCommercial";
    public static final String PROPERTY_TIER = "tier";
    public static final String PROPERTY_ENABLED = "enabled";
    public static final String PROPERTY_APPLYCONFIGURATIONSCRIPT = "applyConfigurationScript";
    public static final String PROPERTY_FREEFORTRIAL = "freeForTrial";

    public ModuleInstall() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_TYPE, "M");
        setDefaultValue(PROPERTY_INDEVELOPMENT, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_STATUS, "I");
        setDefaultValue(PROPERTY_TRANSLATIONREQUIRED, false);
        setDefaultValue(PROPERTY_HASCHARTOFACCOUNTS, false);
        setDefaultValue(PROPERTY_ISTRANSLATIONMODULE, false);
        setDefaultValue(PROPERTY_HASREFERENCEDATA, false);
        setDefaultValue(PROPERTY_REGISTERMODULE, false);
        setDefaultValue(PROPERTY_ISCOMMERCIAL, false);
        setDefaultValue(PROPERTY_ENABLED, true);
        setDefaultValue(PROPERTY_APPLYCONFIGURATIONSCRIPT, true);
        setDefaultValue(PROPERTY_FREEFORTRIAL, false);
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

    public String getModule() {
        return (String) get(PROPERTY_MODULE);
    }

    public void setModule(String module) {
        set(PROPERTY_MODULE, module);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getVersion() {
        return (String) get(PROPERTY_VERSION);
    }

    public void setVersion(String version) {
        set(PROPERTY_VERSION, version);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getHelpComment() {
        return (String) get(PROPERTY_HELPCOMMENT);
    }

    public void setHelpComment(String helpComment) {
        set(PROPERTY_HELPCOMMENT, helpComment);
    }

    public String getURL() {
        return (String) get(PROPERTY_URL);
    }

    public void setURL(String uRL) {
        set(PROPERTY_URL, uRL);
    }

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public String getLicenseText() {
        return (String) get(PROPERTY_LICENSETEXT);
    }

    public void setLicenseText(String licenseText) {
        set(PROPERTY_LICENSETEXT, licenseText);
    }

    public Boolean isInDevelopment() {
        return (Boolean) get(PROPERTY_INDEVELOPMENT);
    }

    public void setInDevelopment(Boolean inDevelopment) {
        set(PROPERTY_INDEVELOPMENT, inDevelopment);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public String getJavaPackage() {
        return (String) get(PROPERTY_JAVAPACKAGE);
    }

    public void setJavaPackage(String javaPackage) {
        set(PROPERTY_JAVAPACKAGE, javaPackage);
    }

    public String getLicenseType() {
        return (String) get(PROPERTY_LICENSETYPE);
    }

    public void setLicenseType(String licenseType) {
        set(PROPERTY_LICENSETYPE, licenseType);
    }

    public String getAuthor() {
        return (String) get(PROPERTY_AUTHOR);
    }

    public void setAuthor(String author) {
        set(PROPERTY_AUTHOR, author);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public String getAvailableUpdate() {
        return (String) get(PROPERTY_AVAILABLEUPDATE);
    }

    public void setAvailableUpdate(String availableUpdate) {
        set(PROPERTY_AVAILABLEUPDATE, availableUpdate);
    }

    public Boolean isTranslationRequired() {
        return (Boolean) get(PROPERTY_TRANSLATIONREQUIRED);
    }

    public void setTranslationRequired(Boolean translationRequired) {
        set(PROPERTY_TRANSLATIONREQUIRED, translationRequired);
    }

    public Language getLanguage() {
        return (Language) get(PROPERTY_LANGUAGE);
    }

    public void setLanguage(Language language) {
        set(PROPERTY_LANGUAGE, language);
    }

    public Boolean isHasChartOfAccounts() {
        return (Boolean) get(PROPERTY_HASCHARTOFACCOUNTS);
    }

    public void setHasChartOfAccounts(Boolean hasChartOfAccounts) {
        set(PROPERTY_HASCHARTOFACCOUNTS, hasChartOfAccounts);
    }

    public Boolean isTranslationModule() {
        return (Boolean) get(PROPERTY_ISTRANSLATIONMODULE);
    }

    public void setTranslationModule(Boolean isTranslationModule) {
        set(PROPERTY_ISTRANSLATIONMODULE, isTranslationModule);
    }

    public Boolean isHasReferenceData() {
        return (Boolean) get(PROPERTY_HASREFERENCEDATA);
    }

    public void setHasReferenceData(Boolean hasReferenceData) {
        set(PROPERTY_HASREFERENCEDATA, hasReferenceData);
    }

    public Boolean isRegisterModule() {
        return (Boolean) get(PROPERTY_REGISTERMODULE);
    }

    public void setRegisterModule(Boolean registerModule) {
        set(PROPERTY_REGISTERMODULE, registerModule);
    }

    public String getUpdateInformation() {
        return (String) get(PROPERTY_UPDATEINFORMATION);
    }

    public void setUpdateInformation(String updateInformation) {
        set(PROPERTY_UPDATEINFORMATION, updateInformation);
    }

    public String getUpdateVersion() {
        return (String) get(PROPERTY_UPDATEVERSION);
    }

    public void setUpdateVersion(String updateVersion) {
        set(PROPERTY_UPDATEVERSION, updateVersion);
    }

    public String getReferenceDataDescription() {
        return (String) get(PROPERTY_REFERENCEDATADESCRIPTION);
    }

    public void setReferenceDataDescription(String referenceDataDescription) {
        set(PROPERTY_REFERENCEDATADESCRIPTION, referenceDataDescription);
    }

    public String getVersionLabel() {
        return (String) get(PROPERTY_VERSIONLABEL);
    }

    public void setVersionLabel(String versionLabel) {
        set(PROPERTY_VERSIONLABEL, versionLabel);
    }

    public String getVersionID() {
        return (String) get(PROPERTY_VERSIONID);
    }

    public void setVersionID(String versionID) {
        set(PROPERTY_VERSIONID, versionID);
    }

    public Boolean isCommercial() {
        return (Boolean) get(PROPERTY_ISCOMMERCIAL);
    }

    public void setCommercial(Boolean isCommercial) {
        set(PROPERTY_ISCOMMERCIAL, isCommercial);
    }

    public String getTier() {
        return (String) get(PROPERTY_TIER);
    }

    public void setTier(String tier) {
        set(PROPERTY_TIER, tier);
    }

    public Boolean isEnabled() {
        return (Boolean) get(PROPERTY_ENABLED);
    }

    public void setEnabled(Boolean enabled) {
        set(PROPERTY_ENABLED, enabled);
    }

    public Boolean isApplyConfigurationScript() {
        return (Boolean) get(PROPERTY_APPLYCONFIGURATIONSCRIPT);
    }

    public void setApplyConfigurationScript(Boolean applyConfigurationScript) {
        set(PROPERTY_APPLYCONFIGURATIONSCRIPT, applyConfigurationScript);
    }

    public Boolean isFreeForTrial() {
        return (Boolean) get(PROPERTY_FREEFORTRIAL);
    }

    public void setFreeForTrial(Boolean freeForTrial) {
        set(PROPERTY_FREEFORTRIAL, freeForTrial);
    }

}
