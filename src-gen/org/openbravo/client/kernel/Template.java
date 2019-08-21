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
package org.openbravo.client.kernel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.application.NavBarComponent;
import org.openbravo.client.application.OBUIAPPViewImplementation;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.service.datasource.DataSource;
import org.openbravo.userinterface.selector.Selector;
/**
 * Entity class for entity OBCLKER_Template (stored in table OBCLKER_Template).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Template extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBCLKER_Template";
    public static final String ENTITY_NAME = "OBCLKER_Template";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_OVERRIDESTEMPLATE = "overridesTemplate";
    public static final String PROPERTY_TEMPLATE = "template";
    public static final String PROPERTY_TEMPLATECLASSPATHLOCATION = "templateClasspathLocation";
    public static final String PROPERTY_TEMPLATELANGUAGE = "templateLanguage";
    public static final String PROPERTY_COMPONENTTYPE = "componentType";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_OBCLKERTEMPLATEOVERRIDESTEMPLATEIDLIST = "oBCLKERTemplateOverridesTemplateIDList";
    public static final String PROPERTY_OBCLKERTEMPLATEDEPENDENCYLIST = "oBCLKERTemplateDependencyList";
    public static final String PROPERTY_OBCLKERTEMPLATEDEPENDENCYDEPENDSONTEMPLATELIST = "oBCLKERTemplateDependencyDependsOnTemplateList";
    public static final String PROPERTY_OBSERDSDATASOURCELIST = "oBSERDSDatasourceList";
    public static final String PROPERTY_OBUIAPPNAVIGATIONBARCOMPONENTLIST = "oBUIAPPNavigationBarComponentList";
    public static final String PROPERTY_OBUIAPPVIEWIMPLEMENTATIONLIST = "oBUIAPPViewImplementationList";
    public static final String PROPERTY_OBUISELSELECTORLIST = "oBUISELSelectorList";

    public Template() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OBCLKERTEMPLATEOVERRIDESTEMPLATEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBCLKERTEMPLATEDEPENDENCYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBCLKERTEMPLATEDEPENDENCYDEPENDSONTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBSERDSDATASOURCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPNAVIGATIONBARCOMPONENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPVIEWIMPLEMENTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORLIST, new ArrayList<Object>());
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

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Template getOverridesTemplate() {
        return (Template) get(PROPERTY_OVERRIDESTEMPLATE);
    }

    public void setOverridesTemplate(Template overridesTemplate) {
        set(PROPERTY_OVERRIDESTEMPLATE, overridesTemplate);
    }

    public String getTemplate() {
        return (String) get(PROPERTY_TEMPLATE);
    }

    public void setTemplate(String template) {
        set(PROPERTY_TEMPLATE, template);
    }

    public String getTemplateClasspathLocation() {
        return (String) get(PROPERTY_TEMPLATECLASSPATHLOCATION);
    }

    public void setTemplateClasspathLocation(String templateClasspathLocation) {
        set(PROPERTY_TEMPLATECLASSPATHLOCATION, templateClasspathLocation);
    }

    public String getTemplateLanguage() {
        return (String) get(PROPERTY_TEMPLATELANGUAGE);
    }

    public void setTemplateLanguage(String templateLanguage) {
        set(PROPERTY_TEMPLATELANGUAGE, templateLanguage);
    }

    public String getComponentType() {
        return (String) get(PROPERTY_COMPONENTTYPE);
    }

    public void setComponentType(String componentType) {
        set(PROPERTY_COMPONENTTYPE, componentType);
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

    @SuppressWarnings("unchecked")
    public List<Template> getOBCLKERTemplateOverridesTemplateIDList() {
        return (List<Template>) get(PROPERTY_OBCLKERTEMPLATEOVERRIDESTEMPLATEIDLIST);
    }

    public void setOBCLKERTemplateOverridesTemplateIDList(List<Template> oBCLKERTemplateOverridesTemplateIDList) {
        set(PROPERTY_OBCLKERTEMPLATEOVERRIDESTEMPLATEIDLIST, oBCLKERTemplateOverridesTemplateIDList);
    }

    @SuppressWarnings("unchecked")
    public List<TemplateDependency> getOBCLKERTemplateDependencyList() {
        return (List<TemplateDependency>) get(PROPERTY_OBCLKERTEMPLATEDEPENDENCYLIST);
    }

    public void setOBCLKERTemplateDependencyList(List<TemplateDependency> oBCLKERTemplateDependencyList) {
        set(PROPERTY_OBCLKERTEMPLATEDEPENDENCYLIST, oBCLKERTemplateDependencyList);
    }

    @SuppressWarnings("unchecked")
    public List<TemplateDependency> getOBCLKERTemplateDependencyDependsOnTemplateList() {
        return (List<TemplateDependency>) get(PROPERTY_OBCLKERTEMPLATEDEPENDENCYDEPENDSONTEMPLATELIST);
    }

    public void setOBCLKERTemplateDependencyDependsOnTemplateList(List<TemplateDependency> oBCLKERTemplateDependencyDependsOnTemplateList) {
        set(PROPERTY_OBCLKERTEMPLATEDEPENDENCYDEPENDSONTEMPLATELIST, oBCLKERTemplateDependencyDependsOnTemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<DataSource> getOBSERDSDatasourceList() {
        return (List<DataSource>) get(PROPERTY_OBSERDSDATASOURCELIST);
    }

    public void setOBSERDSDatasourceList(List<DataSource> oBSERDSDatasourceList) {
        set(PROPERTY_OBSERDSDATASOURCELIST, oBSERDSDatasourceList);
    }

    @SuppressWarnings("unchecked")
    public List<NavBarComponent> getOBUIAPPNavigationBarComponentList() {
        return (List<NavBarComponent>) get(PROPERTY_OBUIAPPNAVIGATIONBARCOMPONENTLIST);
    }

    public void setOBUIAPPNavigationBarComponentList(List<NavBarComponent> oBUIAPPNavigationBarComponentList) {
        set(PROPERTY_OBUIAPPNAVIGATIONBARCOMPONENTLIST, oBUIAPPNavigationBarComponentList);
    }

    @SuppressWarnings("unchecked")
    public List<OBUIAPPViewImplementation> getOBUIAPPViewImplementationList() {
        return (List<OBUIAPPViewImplementation>) get(PROPERTY_OBUIAPPVIEWIMPLEMENTATIONLIST);
    }

    public void setOBUIAPPViewImplementationList(List<OBUIAPPViewImplementation> oBUIAPPViewImplementationList) {
        set(PROPERTY_OBUIAPPVIEWIMPLEMENTATIONLIST, oBUIAPPViewImplementationList);
    }

    @SuppressWarnings("unchecked")
    public List<Selector> getOBUISELSelectorList() {
        return (List<Selector>) get(PROPERTY_OBUISELSELECTORLIST);
    }

    public void setOBUISELSelectorList(List<Selector> oBUISELSelectorList) {
        set(PROPERTY_OBUISELSELECTORLIST, oBUISELSelectorList);
    }

}
