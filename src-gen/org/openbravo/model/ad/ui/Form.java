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
package org.openbravo.model.ad.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.FormAccess;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.domain.ModelImplementation;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADForm (stored in table AD_Form).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Form extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Form";
    public static final String ENTITY_NAME = "ADForm";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_DATAACCESSLEVEL = "dataAccessLevel";
    public static final String PROPERTY_JAVACLASSNAME = "javaClassName";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_ADVANCEDFEATURE = "advancedFeature";
    public static final String PROPERTY_ADFORMACCESSLIST = "aDFormAccessList";
    public static final String PROPERTY_ADFORMTRLLIST = "aDFormTrlList";
    public static final String PROPERTY_ADMENULIST = "aDMenuList";
    public static final String PROPERTY_ADMODELIMPLEMENTATIONLIST = "aDModelImplementationList";
    public static final String PROPERTY_ADTABMASTERDETAILFORMLIST = "aDTabMasterDetailFormList";

    public Form() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ADVANCEDFEATURE, false);
        setDefaultValue(PROPERTY_ADFORMACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADFORMTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMENULIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMODELIMPLEMENTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABMASTERDETAILFORMLIST, new ArrayList<Object>());
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

    public String getHelpComment() {
        return (String) get(PROPERTY_HELPCOMMENT);
    }

    public void setHelpComment(String helpComment) {
        set(PROPERTY_HELPCOMMENT, helpComment);
    }

    public String getDataAccessLevel() {
        return (String) get(PROPERTY_DATAACCESSLEVEL);
    }

    public void setDataAccessLevel(String dataAccessLevel) {
        set(PROPERTY_DATAACCESSLEVEL, dataAccessLevel);
    }

    public String getJavaClassName() {
        return (String) get(PROPERTY_JAVACLASSNAME);
    }

    public void setJavaClassName(String javaClassName) {
        set(PROPERTY_JAVACLASSNAME, javaClassName);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public Boolean isAdvancedFeature() {
        return (Boolean) get(PROPERTY_ADVANCEDFEATURE);
    }

    public void setAdvancedFeature(Boolean advancedFeature) {
        set(PROPERTY_ADVANCEDFEATURE, advancedFeature);
    }

    @SuppressWarnings("unchecked")
    public List<FormAccess> getADFormAccessList() {
        return (List<FormAccess>) get(PROPERTY_ADFORMACCESSLIST);
    }

    public void setADFormAccessList(List<FormAccess> aDFormAccessList) {
        set(PROPERTY_ADFORMACCESSLIST, aDFormAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<FormTrl> getADFormTrlList() {
        return (List<FormTrl>) get(PROPERTY_ADFORMTRLLIST);
    }

    public void setADFormTrlList(List<FormTrl> aDFormTrlList) {
        set(PROPERTY_ADFORMTRLLIST, aDFormTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<Menu> getADMenuList() {
        return (List<Menu>) get(PROPERTY_ADMENULIST);
    }

    public void setADMenuList(List<Menu> aDMenuList) {
        set(PROPERTY_ADMENULIST, aDMenuList);
    }

    @SuppressWarnings("unchecked")
    public List<ModelImplementation> getADModelImplementationList() {
        return (List<ModelImplementation>) get(PROPERTY_ADMODELIMPLEMENTATIONLIST);
    }

    public void setADModelImplementationList(List<ModelImplementation> aDModelImplementationList) {
        set(PROPERTY_ADMODELIMPLEMENTATIONLIST, aDModelImplementationList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabMasterDetailFormList() {
        return (List<Tab>) get(PROPERTY_ADTABMASTERDETAILFORMLIST);
    }

    public void setADTabMasterDetailFormList(List<Tab> aDTabMasterDetailFormList) {
        set(PROPERTY_ADTABMASTERDETAILFORMLIST, aDTabMasterDetailFormList);
    }

}
