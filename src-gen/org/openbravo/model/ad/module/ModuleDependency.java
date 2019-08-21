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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ModuleDependency (stored in table AD_Module_Dependency).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ModuleDependency extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Module_Dependency";
    public static final String ENTITY_NAME = "ModuleDependency";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_DEPENDENTMODULE = "dependentModule";
    public static final String PROPERTY_FIRSTVERSION = "firstVersion";
    public static final String PROPERTY_LASTVERSION = "lastVersion";
    public static final String PROPERTY_ISINCLUDED = "isIncluded";
    public static final String PROPERTY_DEPENDANTMODULENAME = "dependantModuleName";
    public static final String PROPERTY_DEPENDENCYENFORCEMENT = "dependencyEnforcement";
    public static final String PROPERTY_USEREDITABLEENFORCEMENT = "userEditableEnforcement";
    public static final String PROPERTY_INSTANCEENFORCEMENT = "instanceEnforcement";

    public ModuleDependency() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_DEPENDENCYENFORCEMENT, "MAJOR");
        setDefaultValue(PROPERTY_USEREDITABLEENFORCEMENT, false);
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

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public Module getDependentModule() {
        return (Module) get(PROPERTY_DEPENDENTMODULE);
    }

    public void setDependentModule(Module dependentModule) {
        set(PROPERTY_DEPENDENTMODULE, dependentModule);
    }

    public String getFirstVersion() {
        return (String) get(PROPERTY_FIRSTVERSION);
    }

    public void setFirstVersion(String firstVersion) {
        set(PROPERTY_FIRSTVERSION, firstVersion);
    }

    public String getLastVersion() {
        return (String) get(PROPERTY_LASTVERSION);
    }

    public void setLastVersion(String lastVersion) {
        set(PROPERTY_LASTVERSION, lastVersion);
    }

    public Boolean isIncluded() {
        return (Boolean) get(PROPERTY_ISINCLUDED);
    }

    public void setIncluded(Boolean isIncluded) {
        set(PROPERTY_ISINCLUDED, isIncluded);
    }

    public String getDependantModuleName() {
        return (String) get(PROPERTY_DEPENDANTMODULENAME);
    }

    public void setDependantModuleName(String dependantModuleName) {
        set(PROPERTY_DEPENDANTMODULENAME, dependantModuleName);
    }

    public String getDependencyEnforcement() {
        return (String) get(PROPERTY_DEPENDENCYENFORCEMENT);
    }

    public void setDependencyEnforcement(String dependencyEnforcement) {
        set(PROPERTY_DEPENDENCYENFORCEMENT, dependencyEnforcement);
    }

    public Boolean isUserEditableEnforcement() {
        return (Boolean) get(PROPERTY_USEREDITABLEENFORCEMENT);
    }

    public void setUserEditableEnforcement(Boolean userEditableEnforcement) {
        set(PROPERTY_USEREDITABLEENFORCEMENT, userEditableEnforcement);
    }

    public String getInstanceEnforcement() {
        return (String) get(PROPERTY_INSTANCEENFORCEMENT);
    }

    public void setInstanceEnforcement(String instanceEnforcement) {
        set(PROPERTY_INSTANCEENFORCEMENT, instanceEnforcement);
    }

}
