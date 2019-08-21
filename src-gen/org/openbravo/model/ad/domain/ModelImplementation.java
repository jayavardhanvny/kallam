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
package org.openbravo.model.ad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Form;
import org.openbravo.model.ad.ui.Process;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADModelImplementation (stored in table AD_Model_Object).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ModelImplementation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Model_Object";
    public static final String ENTITY_NAME = "ADModelImplementation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTION = "action";
    public static final String PROPERTY_TAB = "tab";
    public static final String PROPERTY_JAVACLASSNAME = "javaClassName";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_SPECIALFORM = "specialForm";
    public static final String PROPERTY_REFERENCE = "reference";
    public static final String PROPERTY_CALLOUT = "callout";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_OBJECTTYPE = "objectType";
    public static final String PROPERTY_LOADONSTARTUP = "loadOnStartUp";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_ADMODELIMPLEMENTATIONMAPPINGLIST = "aDModelImplementationMappingList";
    public static final String PROPERTY_MODELIMPLEMENTATIONPARAMETERLIST = "modelImplementationParameterList";

    public ModelImplementation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_OBJECTTYPE, "S");
        setDefaultValue(PROPERTY_ADMODELIMPLEMENTATIONMAPPINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MODELIMPLEMENTATIONPARAMETERLIST, new ArrayList<Object>());
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

    public String getAction() {
        return (String) get(PROPERTY_ACTION);
    }

    public void setAction(String action) {
        set(PROPERTY_ACTION, action);
    }

    public Tab getTab() {
        return (Tab) get(PROPERTY_TAB);
    }

    public void setTab(Tab tab) {
        set(PROPERTY_TAB, tab);
    }

    public String getJavaClassName() {
        return (String) get(PROPERTY_JAVACLASSNAME);
    }

    public void setJavaClassName(String javaClassName) {
        set(PROPERTY_JAVACLASSNAME, javaClassName);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public Process getProcess() {
        return (Process) get(PROPERTY_PROCESS);
    }

    public void setProcess(Process process) {
        set(PROPERTY_PROCESS, process);
    }

    public Form getSpecialForm() {
        return (Form) get(PROPERTY_SPECIALFORM);
    }

    public void setSpecialForm(Form specialForm) {
        set(PROPERTY_SPECIALFORM, specialForm);
    }

    public Reference getReference() {
        return (Reference) get(PROPERTY_REFERENCE);
    }

    public void setReference(Reference reference) {
        set(PROPERTY_REFERENCE, reference);
    }

    public Callout getCallout() {
        return (Callout) get(PROPERTY_CALLOUT);
    }

    public void setCallout(Callout callout) {
        set(PROPERTY_CALLOUT, callout);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public String getObjectType() {
        return (String) get(PROPERTY_OBJECTTYPE);
    }

    public void setObjectType(String objectType) {
        set(PROPERTY_OBJECTTYPE, objectType);
    }

    public Long getLoadOnStartUp() {
        return (Long) get(PROPERTY_LOADONSTARTUP);
    }

    public void setLoadOnStartUp(Long loadOnStartUp) {
        set(PROPERTY_LOADONSTARTUP, loadOnStartUp);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    @SuppressWarnings("unchecked")
    public List<ModelImplementationMapping> getADModelImplementationMappingList() {
        return (List<ModelImplementationMapping>) get(PROPERTY_ADMODELIMPLEMENTATIONMAPPINGLIST);
    }

    public void setADModelImplementationMappingList(List<ModelImplementationMapping> aDModelImplementationMappingList) {
        set(PROPERTY_ADMODELIMPLEMENTATIONMAPPINGLIST, aDModelImplementationMappingList);
    }

    @SuppressWarnings("unchecked")
    public List<ModelImplementationParameter> getModelImplementationParameterList() {
        return (List<ModelImplementationParameter>) get(PROPERTY_MODELIMPLEMENTATIONPARAMETERLIST);
    }

    public void setModelImplementationParameterList(List<ModelImplementationParameter> modelImplementationParameterList) {
        set(PROPERTY_MODELIMPLEMENTATIONPARAMETERLIST, modelImplementationParameterList);
    }

}
