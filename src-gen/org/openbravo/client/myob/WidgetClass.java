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
package org.openbravo.client.myob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.application.Parameter;
import org.openbravo.client.querylist.OBCQL_WidgetQuery;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity OBKMO_WidgetClass (stored in table OBKMO_Widget_Class).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class WidgetClass extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBKMO_Widget_Class";
    public static final String ENTITY_NAME = "OBKMO_WidgetClass";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_WIDGETTITLE = "widgetTitle";
    public static final String PROPERTY_JAVACLASS = "javaClass";
    public static final String PROPERTY_HEIGHT = "height";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ALLOWANONYMOUSACCESS = "allowAnonymousAccess";
    public static final String PROPERTY_SUPERCLASS = "superclass";
    public static final String PROPERTY_WIDGETSUPERCLASS = "widgetSuperclass";
    public static final String PROPERTY_CANMAXIMIZE = "canMaximize";
    public static final String PROPERTY_DATAACCESSLEVEL = "dataAccessLevel";
    public static final String PROPERTY_AUTHORMSG = "authorMsg";
    public static final String PROPERTY_AUTHORURL = "authorUrl";
    public static final String PROPERTY_OBCQLWIDGETQUERYLIST = "oBCQLWidgetQueryList";
    public static final String PROPERTY_OBKMOWIDGETCLASSWIDGETSUPERCLASSLIST = "oBKMOWidgetClassWidgetSuperclassList";
    public static final String PROPERTY_OBKMOWIDGETCLASSACCESSLIST = "oBKMOWidgetClassAccessList";
    public static final String PROPERTY_OBKMOWIDGETCLASSMENULIST = "oBKMOWidgetClassMenuList";
    public static final String PROPERTY_OBKMOWIDGETCLASSTRLLIST = "oBKMOWidgetClassTrlList";
    public static final String PROPERTY_OBKMOWIDGETINSTANCELIST = "oBKMOWidgetInstanceList";
    public static final String PROPERTY_OBKMOWIDGETLISTLIST = "oBKMOWidgetListList";
    public static final String PROPERTY_OBKMOWIDGETREFERENCELIST = "oBKMOWidgetReferenceList";
    public static final String PROPERTY_OBKMOWIDGETURLLIST = "oBKMOWidgetURLList";
    public static final String PROPERTY_OBUIAPPPARAMETEREMOBKMOWIDGETCLASSIDLIST = "oBUIAPPParameterEMObkmoWidgetClassIDList";

    public WidgetClass() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_HEIGHT, (long) 300);
        setDefaultValue(PROPERTY_ALLOWANONYMOUSACCESS, false);
        setDefaultValue(PROPERTY_SUPERCLASS, false);
        setDefaultValue(PROPERTY_CANMAXIMIZE, false);
        setDefaultValue(PROPERTY_DATAACCESSLEVEL, "3");
        setDefaultValue(PROPERTY_OBCQLWIDGETQUERYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETCLASSWIDGETSUPERCLASSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETCLASSACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETCLASSMENULIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETCLASSTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETINSTANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETLISTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETREFERENCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETURLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPPARAMETEREMOBKMOWIDGETCLASSIDLIST, new ArrayList<Object>());
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

    public String getWidgetTitle() {
        return (String) get(PROPERTY_WIDGETTITLE);
    }

    public void setWidgetTitle(String widgetTitle) {
        set(PROPERTY_WIDGETTITLE, widgetTitle);
    }

    public String getJavaClass() {
        return (String) get(PROPERTY_JAVACLASS);
    }

    public void setJavaClass(String javaClass) {
        set(PROPERTY_JAVACLASS, javaClass);
    }

    public Long getHeight() {
        return (Long) get(PROPERTY_HEIGHT);
    }

    public void setHeight(Long height) {
        set(PROPERTY_HEIGHT, height);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isAllowAnonymousAccess() {
        return (Boolean) get(PROPERTY_ALLOWANONYMOUSACCESS);
    }

    public void setAllowAnonymousAccess(Boolean allowAnonymousAccess) {
        set(PROPERTY_ALLOWANONYMOUSACCESS, allowAnonymousAccess);
    }

    public Boolean isSuperclass() {
        return (Boolean) get(PROPERTY_SUPERCLASS);
    }

    public void setSuperclass(Boolean superclass) {
        set(PROPERTY_SUPERCLASS, superclass);
    }

    public WidgetClass getWidgetSuperclass() {
        return (WidgetClass) get(PROPERTY_WIDGETSUPERCLASS);
    }

    public void setWidgetSuperclass(WidgetClass widgetSuperclass) {
        set(PROPERTY_WIDGETSUPERCLASS, widgetSuperclass);
    }

    public Boolean isCanMaximize() {
        return (Boolean) get(PROPERTY_CANMAXIMIZE);
    }

    public void setCanMaximize(Boolean canMaximize) {
        set(PROPERTY_CANMAXIMIZE, canMaximize);
    }

    public String getDataAccessLevel() {
        return (String) get(PROPERTY_DATAACCESSLEVEL);
    }

    public void setDataAccessLevel(String dataAccessLevel) {
        set(PROPERTY_DATAACCESSLEVEL, dataAccessLevel);
    }

    public String getAuthorMsg() {
        return (String) get(PROPERTY_AUTHORMSG);
    }

    public void setAuthorMsg(String authorMsg) {
        set(PROPERTY_AUTHORMSG, authorMsg);
    }

    public String getAuthorUrl() {
        return (String) get(PROPERTY_AUTHORURL);
    }

    public void setAuthorUrl(String authorUrl) {
        set(PROPERTY_AUTHORURL, authorUrl);
    }

    @SuppressWarnings("unchecked")
    public List<OBCQL_WidgetQuery> getOBCQLWidgetQueryList() {
        return (List<OBCQL_WidgetQuery>) get(PROPERTY_OBCQLWIDGETQUERYLIST);
    }

    public void setOBCQLWidgetQueryList(List<OBCQL_WidgetQuery> oBCQLWidgetQueryList) {
        set(PROPERTY_OBCQLWIDGETQUERYLIST, oBCQLWidgetQueryList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetClass> getOBKMOWidgetClassWidgetSuperclassList() {
        return (List<WidgetClass>) get(PROPERTY_OBKMOWIDGETCLASSWIDGETSUPERCLASSLIST);
    }

    public void setOBKMOWidgetClassWidgetSuperclassList(List<WidgetClass> oBKMOWidgetClassWidgetSuperclassList) {
        set(PROPERTY_OBKMOWIDGETCLASSWIDGETSUPERCLASSLIST, oBKMOWidgetClassWidgetSuperclassList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetClassAccess> getOBKMOWidgetClassAccessList() {
        return (List<WidgetClassAccess>) get(PROPERTY_OBKMOWIDGETCLASSACCESSLIST);
    }

    public void setOBKMOWidgetClassAccessList(List<WidgetClassAccess> oBKMOWidgetClassAccessList) {
        set(PROPERTY_OBKMOWIDGETCLASSACCESSLIST, oBKMOWidgetClassAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetClassMenu> getOBKMOWidgetClassMenuList() {
        return (List<WidgetClassMenu>) get(PROPERTY_OBKMOWIDGETCLASSMENULIST);
    }

    public void setOBKMOWidgetClassMenuList(List<WidgetClassMenu> oBKMOWidgetClassMenuList) {
        set(PROPERTY_OBKMOWIDGETCLASSMENULIST, oBKMOWidgetClassMenuList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetClassTrl> getOBKMOWidgetClassTrlList() {
        return (List<WidgetClassTrl>) get(PROPERTY_OBKMOWIDGETCLASSTRLLIST);
    }

    public void setOBKMOWidgetClassTrlList(List<WidgetClassTrl> oBKMOWidgetClassTrlList) {
        set(PROPERTY_OBKMOWIDGETCLASSTRLLIST, oBKMOWidgetClassTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetInstance> getOBKMOWidgetInstanceList() {
        return (List<WidgetInstance>) get(PROPERTY_OBKMOWIDGETINSTANCELIST);
    }

    public void setOBKMOWidgetInstanceList(List<WidgetInstance> oBKMOWidgetInstanceList) {
        set(PROPERTY_OBKMOWIDGETINSTANCELIST, oBKMOWidgetInstanceList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetList> getOBKMOWidgetListList() {
        return (List<WidgetList>) get(PROPERTY_OBKMOWIDGETLISTLIST);
    }

    public void setOBKMOWidgetListList(List<WidgetList> oBKMOWidgetListList) {
        set(PROPERTY_OBKMOWIDGETLISTLIST, oBKMOWidgetListList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetReference> getOBKMOWidgetReferenceList() {
        return (List<WidgetReference>) get(PROPERTY_OBKMOWIDGETREFERENCELIST);
    }

    public void setOBKMOWidgetReferenceList(List<WidgetReference> oBKMOWidgetReferenceList) {
        set(PROPERTY_OBKMOWIDGETREFERENCELIST, oBKMOWidgetReferenceList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetURL> getOBKMOWidgetURLList() {
        return (List<WidgetURL>) get(PROPERTY_OBKMOWIDGETURLLIST);
    }

    public void setOBKMOWidgetURLList(List<WidgetURL> oBKMOWidgetURLList) {
        set(PROPERTY_OBKMOWIDGETURLLIST, oBKMOWidgetURLList);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> getOBUIAPPParameterEMObkmoWidgetClassIDList() {
        return (List<Parameter>) get(PROPERTY_OBUIAPPPARAMETEREMOBKMOWIDGETCLASSIDLIST);
    }

    public void setOBUIAPPParameterEMObkmoWidgetClassIDList(List<Parameter> oBUIAPPParameterEMObkmoWidgetClassIDList) {
        set(PROPERTY_OBUIAPPPARAMETEREMOBKMOWIDGETCLASSIDLIST, oBUIAPPParameterEMObkmoWidgetClassIDList);
    }

}
