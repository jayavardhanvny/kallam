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
import org.openbravo.client.application.Parameter;
import org.openbravo.client.application.RefWindow;
import org.openbravo.client.kernel.ReferencedMask;
import org.openbravo.client.kernel.UserInterfaceDefinition;
import org.openbravo.client.myob.WidgetReference;
import org.openbravo.client.querylist.OBCQL_QueryColumn;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.ProcessParameter;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.service.datasource.DatasourceField;
import org.openbravo.userinterface.selector.SelectorField;
/**
 * Entity class for entity ADReference (stored in table AD_Reference).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Reference extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Reference";
    public static final String ENTITY_NAME = "ADReference";
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
    public static final String PROPERTY_VALIDATIONTYPE = "validationType";
    public static final String PROPERTY_VALUEFORMAT = "valueFormat";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_ISBASEREFERENCE = "isBaseReference";
    public static final String PROPERTY_PARENTREFERENCE = "parentReference";
    public static final String PROPERTY_MODELIMPL = "modelImpl";
    public static final String PROPERTY_WADIMPL = "wADImpl";
    public static final String PROPERTY_IMPL = "impl";
    public static final String PROPERTY_DISPLAYEDVALUE = "displayedValue";
    public static final String PROPERTY_ADCOLUMNLIST = "aDColumnList";
    public static final String PROPERTY_ADCOLUMNREFERENCESEARCHKEYLIST = "aDColumnReferenceSearchKeyList";
    public static final String PROPERTY_ADLISTLIST = "aDListList";
    public static final String PROPERTY_ADMODELIMPLEMENTATIONLIST = "aDModelImplementationList";
    public static final String PROPERTY_ADPROCESSPARAMETERLIST = "aDProcessParameterList";
    public static final String PROPERTY_ADPROCESSPARAMETERREFERENCESEARCHKEYLIST = "aDProcessParameterReferenceSearchKeyList";
    public static final String PROPERTY_ADREFERENCEPARENTREFERENCEIDLIST = "aDReferenceParentReferenceIDList";
    public static final String PROPERTY_ADREFERENCETRLLIST = "aDReferenceTrlList";
    public static final String PROPERTY_ADREFERENCEDTABLELIST = "aDReferencedTableList";
    public static final String PROPERTY_ADSELECTORLIST = "aDSelectorList";
    public static final String PROPERTY_OBCLKERREFMASKLIST = "oBCLKERREFMASKList";
    public static final String PROPERTY_OBCLKERUIDEFINITIONLIST = "oBCLKERUIDefinitionList";
    public static final String PROPERTY_OBCQLQUERYCOLUMNLIST = "oBCQLQueryColumnList";
    public static final String PROPERTY_OBCQLQUERYCOLUMNREFERENCESEARCHKEYLIST = "oBCQLQueryColumnReferenceSearchKeyList";
    public static final String PROPERTY_OBKMOWIDGETREFERENCELIST = "oBKMOWidgetReferenceList";
    public static final String PROPERTY_OBSERDSDATASOURCEFIELDLIST = "oBSERDSDatasourceFieldList";
    public static final String PROPERTY_OBUIAPPPARAMETERLIST = "oBUIAPPParameterList";
    public static final String PROPERTY_OBUIAPPPARAMETERREFERENCESEARCHKEYLIST = "oBUIAPPParameterReferenceSearchKeyList";
    public static final String PROPERTY_OBUIAPPREFWINDOWLIST = "oBUIAPPRefWindowList";
    public static final String PROPERTY_OBUISELSELECTORLIST = "oBUISELSelectorList";
    public static final String PROPERTY_OBUISELSELECTORFIELDLIST = "oBUISELSelectorFieldList";

    public Reference() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISBASEREFERENCE, false);
        setDefaultValue(PROPERTY_DISPLAYEDVALUE, false);
        setDefaultValue(PROPERTY_ADCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADCOLUMNREFERENCESEARCHKEYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADLISTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMODELIMPLEMENTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSPARAMETERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSPARAMETERREFERENCESEARCHKEYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADREFERENCEPARENTREFERENCEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADREFERENCETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADREFERENCEDTABLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADSELECTORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBCLKERREFMASKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBCLKERUIDEFINITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBCQLQUERYCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBCQLQUERYCOLUMNREFERENCESEARCHKEYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETREFERENCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBSERDSDATASOURCEFIELDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPPARAMETERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPPARAMETERREFERENCESEARCHKEYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPREFWINDOWLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORFIELDLIST, new ArrayList<Object>());
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

    public String getValidationType() {
        return (String) get(PROPERTY_VALIDATIONTYPE);
    }

    public void setValidationType(String validationType) {
        set(PROPERTY_VALIDATIONTYPE, validationType);
    }

    public String getValueFormat() {
        return (String) get(PROPERTY_VALUEFORMAT);
    }

    public void setValueFormat(String valueFormat) {
        set(PROPERTY_VALUEFORMAT, valueFormat);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public Boolean isBaseReference() {
        return (Boolean) get(PROPERTY_ISBASEREFERENCE);
    }

    public void setBaseReference(Boolean isBaseReference) {
        set(PROPERTY_ISBASEREFERENCE, isBaseReference);
    }

    public Reference getParentReference() {
        return (Reference) get(PROPERTY_PARENTREFERENCE);
    }

    public void setParentReference(Reference parentReference) {
        set(PROPERTY_PARENTREFERENCE, parentReference);
    }

    public String getModelImpl() {
        return (String) get(PROPERTY_MODELIMPL);
    }

    public void setModelImpl(String modelImpl) {
        set(PROPERTY_MODELIMPL, modelImpl);
    }

    public String getWADImpl() {
        return (String) get(PROPERTY_WADIMPL);
    }

    public void setWADImpl(String wADImpl) {
        set(PROPERTY_WADIMPL, wADImpl);
    }

    public String getImpl() {
        return (String) get(PROPERTY_IMPL);
    }

    public void setImpl(String impl) {
        set(PROPERTY_IMPL, impl);
    }

    public Boolean isDisplayedValue() {
        return (Boolean) get(PROPERTY_DISPLAYEDVALUE);
    }

    public void setDisplayedValue(Boolean displayedValue) {
        set(PROPERTY_DISPLAYEDVALUE, displayedValue);
    }

    @SuppressWarnings("unchecked")
    public List<Column> getADColumnList() {
        return (List<Column>) get(PROPERTY_ADCOLUMNLIST);
    }

    public void setADColumnList(List<Column> aDColumnList) {
        set(PROPERTY_ADCOLUMNLIST, aDColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<Column> getADColumnReferenceSearchKeyList() {
        return (List<Column>) get(PROPERTY_ADCOLUMNREFERENCESEARCHKEYLIST);
    }

    public void setADColumnReferenceSearchKeyList(List<Column> aDColumnReferenceSearchKeyList) {
        set(PROPERTY_ADCOLUMNREFERENCESEARCHKEYLIST, aDColumnReferenceSearchKeyList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.ad.domain.List> getADListList() {
        return (List<org.openbravo.model.ad.domain.List>) get(PROPERTY_ADLISTLIST);
    }

    public void setADListList(List<org.openbravo.model.ad.domain.List> aDListList) {
        set(PROPERTY_ADLISTLIST, aDListList);
    }

    @SuppressWarnings("unchecked")
    public List<ModelImplementation> getADModelImplementationList() {
        return (List<ModelImplementation>) get(PROPERTY_ADMODELIMPLEMENTATIONLIST);
    }

    public void setADModelImplementationList(List<ModelImplementation> aDModelImplementationList) {
        set(PROPERTY_ADMODELIMPLEMENTATIONLIST, aDModelImplementationList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessParameter> getADProcessParameterList() {
        return (List<ProcessParameter>) get(PROPERTY_ADPROCESSPARAMETERLIST);
    }

    public void setADProcessParameterList(List<ProcessParameter> aDProcessParameterList) {
        set(PROPERTY_ADPROCESSPARAMETERLIST, aDProcessParameterList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessParameter> getADProcessParameterReferenceSearchKeyList() {
        return (List<ProcessParameter>) get(PROPERTY_ADPROCESSPARAMETERREFERENCESEARCHKEYLIST);
    }

    public void setADProcessParameterReferenceSearchKeyList(List<ProcessParameter> aDProcessParameterReferenceSearchKeyList) {
        set(PROPERTY_ADPROCESSPARAMETERREFERENCESEARCHKEYLIST, aDProcessParameterReferenceSearchKeyList);
    }

    @SuppressWarnings("unchecked")
    public List<Reference> getADReferenceParentReferenceIDList() {
        return (List<Reference>) get(PROPERTY_ADREFERENCEPARENTREFERENCEIDLIST);
    }

    public void setADReferenceParentReferenceIDList(List<Reference> aDReferenceParentReferenceIDList) {
        set(PROPERTY_ADREFERENCEPARENTREFERENCEIDLIST, aDReferenceParentReferenceIDList);
    }

    @SuppressWarnings("unchecked")
    public List<ReferenceTrl> getADReferenceTrlList() {
        return (List<ReferenceTrl>) get(PROPERTY_ADREFERENCETRLLIST);
    }

    public void setADReferenceTrlList(List<ReferenceTrl> aDReferenceTrlList) {
        set(PROPERTY_ADREFERENCETRLLIST, aDReferenceTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ReferencedTable> getADReferencedTableList() {
        return (List<ReferencedTable>) get(PROPERTY_ADREFERENCEDTABLELIST);
    }

    public void setADReferencedTableList(List<ReferencedTable> aDReferencedTableList) {
        set(PROPERTY_ADREFERENCEDTABLELIST, aDReferencedTableList);
    }

    @SuppressWarnings("unchecked")
    public List<Selector> getADSelectorList() {
        return (List<Selector>) get(PROPERTY_ADSELECTORLIST);
    }

    public void setADSelectorList(List<Selector> aDSelectorList) {
        set(PROPERTY_ADSELECTORLIST, aDSelectorList);
    }

    @SuppressWarnings("unchecked")
    public List<ReferencedMask> getOBCLKERREFMASKList() {
        return (List<ReferencedMask>) get(PROPERTY_OBCLKERREFMASKLIST);
    }

    public void setOBCLKERREFMASKList(List<ReferencedMask> oBCLKERREFMASKList) {
        set(PROPERTY_OBCLKERREFMASKLIST, oBCLKERREFMASKList);
    }

    @SuppressWarnings("unchecked")
    public List<UserInterfaceDefinition> getOBCLKERUIDefinitionList() {
        return (List<UserInterfaceDefinition>) get(PROPERTY_OBCLKERUIDEFINITIONLIST);
    }

    public void setOBCLKERUIDefinitionList(List<UserInterfaceDefinition> oBCLKERUIDefinitionList) {
        set(PROPERTY_OBCLKERUIDEFINITIONLIST, oBCLKERUIDefinitionList);
    }

    @SuppressWarnings("unchecked")
    public List<OBCQL_QueryColumn> getOBCQLQueryColumnList() {
        return (List<OBCQL_QueryColumn>) get(PROPERTY_OBCQLQUERYCOLUMNLIST);
    }

    public void setOBCQLQueryColumnList(List<OBCQL_QueryColumn> oBCQLQueryColumnList) {
        set(PROPERTY_OBCQLQUERYCOLUMNLIST, oBCQLQueryColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<OBCQL_QueryColumn> getOBCQLQueryColumnReferenceSearchKeyList() {
        return (List<OBCQL_QueryColumn>) get(PROPERTY_OBCQLQUERYCOLUMNREFERENCESEARCHKEYLIST);
    }

    public void setOBCQLQueryColumnReferenceSearchKeyList(List<OBCQL_QueryColumn> oBCQLQueryColumnReferenceSearchKeyList) {
        set(PROPERTY_OBCQLQUERYCOLUMNREFERENCESEARCHKEYLIST, oBCQLQueryColumnReferenceSearchKeyList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetReference> getOBKMOWidgetReferenceList() {
        return (List<WidgetReference>) get(PROPERTY_OBKMOWIDGETREFERENCELIST);
    }

    public void setOBKMOWidgetReferenceList(List<WidgetReference> oBKMOWidgetReferenceList) {
        set(PROPERTY_OBKMOWIDGETREFERENCELIST, oBKMOWidgetReferenceList);
    }

    @SuppressWarnings("unchecked")
    public List<DatasourceField> getOBSERDSDatasourceFieldList() {
        return (List<DatasourceField>) get(PROPERTY_OBSERDSDATASOURCEFIELDLIST);
    }

    public void setOBSERDSDatasourceFieldList(List<DatasourceField> oBSERDSDatasourceFieldList) {
        set(PROPERTY_OBSERDSDATASOURCEFIELDLIST, oBSERDSDatasourceFieldList);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> getOBUIAPPParameterList() {
        return (List<Parameter>) get(PROPERTY_OBUIAPPPARAMETERLIST);
    }

    public void setOBUIAPPParameterList(List<Parameter> oBUIAPPParameterList) {
        set(PROPERTY_OBUIAPPPARAMETERLIST, oBUIAPPParameterList);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> getOBUIAPPParameterReferenceSearchKeyList() {
        return (List<Parameter>) get(PROPERTY_OBUIAPPPARAMETERREFERENCESEARCHKEYLIST);
    }

    public void setOBUIAPPParameterReferenceSearchKeyList(List<Parameter> oBUIAPPParameterReferenceSearchKeyList) {
        set(PROPERTY_OBUIAPPPARAMETERREFERENCESEARCHKEYLIST, oBUIAPPParameterReferenceSearchKeyList);
    }

    @SuppressWarnings("unchecked")
    public List<RefWindow> getOBUIAPPRefWindowList() {
        return (List<RefWindow>) get(PROPERTY_OBUIAPPREFWINDOWLIST);
    }

    public void setOBUIAPPRefWindowList(List<RefWindow> oBUIAPPRefWindowList) {
        set(PROPERTY_OBUIAPPREFWINDOWLIST, oBUIAPPRefWindowList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.userinterface.selector.Selector> getOBUISELSelectorList() {
        return (List<org.openbravo.userinterface.selector.Selector>) get(PROPERTY_OBUISELSELECTORLIST);
    }

    public void setOBUISELSelectorList(List<org.openbravo.userinterface.selector.Selector> oBUISELSelectorList) {
        set(PROPERTY_OBUISELSELECTORLIST, oBUISELSelectorList);
    }

    @SuppressWarnings("unchecked")
    public List<SelectorField> getOBUISELSelectorFieldList() {
        return (List<SelectorField>) get(PROPERTY_OBUISELSELECTORFIELDLIST);
    }

    public void setOBUISELSelectorFieldList(List<SelectorField> oBUISELSelectorFieldList) {
        set(PROPERTY_OBUISELSELECTORFIELDLIST, oBUISELSelectorFieldList);
    }

}
