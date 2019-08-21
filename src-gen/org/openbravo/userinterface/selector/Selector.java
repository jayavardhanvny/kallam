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
package org.openbravo.userinterface.selector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.kernel.Template;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.domain.Reference;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.service.datasource.DataSource;
/**
 * Entity class for entity OBUISEL_Selector (stored in table OBUISEL_Selector).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Selector extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBUISEL_Selector";
    public static final String ENTITY_NAME = "OBUISEL_Selector";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_REFERENCE = "reference";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_COLUMN = "column";
    public static final String PROPERTY_HQLWHERECLAUSE = "hQLWhereClause";
    public static final String PROPERTY_FILTEREXPRESSION = "filterExpression";
    public static final String PROPERTY_OBSERDSDATASOURCE = "obserdsDatasource";
    public static final String PROPERTY_OBCLKERTEMPLATE = "obclkerTemplate";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_VALUEFIELD = "valuefield";
    public static final String PROPERTY_DISPLAYFIELD = "displayfield";
    public static final String PROPERTY_SUGGESTIONTEXTMATCHSTYLE = "suggestiontextmatchstyle";
    public static final String PROPERTY_POPUPTEXTMATCHSTYLE = "popuptextmatchstyle";
    public static final String PROPERTY_HQL = "hQL";
    public static final String PROPERTY_ENTITYALIAS = "entityAlias";
    public static final String PROPERTY_CUSTOMQUERY = "customQuery";
    public static final String PROPERTY_OBUISELSELECTORFIELDLIST = "oBUISELSelectorFieldList";
    public static final String PROPERTY_OBUISELSELECTORTRLLIST = "oBUISELSelectorTrlList";

    public Selector() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUGGESTIONTEXTMATCHSTYLE, "startsWith");
        setDefaultValue(PROPERTY_POPUPTEXTMATCHSTYLE, "startsWith");
        setDefaultValue(PROPERTY_CUSTOMQUERY, false);
        setDefaultValue(PROPERTY_OBUISELSELECTORFIELDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORTRLLIST, new ArrayList<Object>());
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

    public String getHelpComment() {
        return (String) get(PROPERTY_HELPCOMMENT);
    }

    public void setHelpComment(String helpComment) {
        set(PROPERTY_HELPCOMMENT, helpComment);
    }

    public Reference getReference() {
        return (Reference) get(PROPERTY_REFERENCE);
    }

    public void setReference(Reference reference) {
        set(PROPERTY_REFERENCE, reference);
    }

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
    }

    public Column getColumn() {
        return (Column) get(PROPERTY_COLUMN);
    }

    public void setColumn(Column column) {
        set(PROPERTY_COLUMN, column);
    }

    public String getHQLWhereClause() {
        return (String) get(PROPERTY_HQLWHERECLAUSE);
    }

    public void setHQLWhereClause(String hQLWhereClause) {
        set(PROPERTY_HQLWHERECLAUSE, hQLWhereClause);
    }

    public String getFilterExpression() {
        return (String) get(PROPERTY_FILTEREXPRESSION);
    }

    public void setFilterExpression(String filterExpression) {
        set(PROPERTY_FILTEREXPRESSION, filterExpression);
    }

    public DataSource getObserdsDatasource() {
        return (DataSource) get(PROPERTY_OBSERDSDATASOURCE);
    }

    public void setObserdsDatasource(DataSource obserdsDatasource) {
        set(PROPERTY_OBSERDSDATASOURCE, obserdsDatasource);
    }

    public Template getObclkerTemplate() {
        return (Template) get(PROPERTY_OBCLKERTEMPLATE);
    }

    public void setObclkerTemplate(Template obclkerTemplate) {
        set(PROPERTY_OBCLKERTEMPLATE, obclkerTemplate);
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

    public SelectorField getValuefield() {
        return (SelectorField) get(PROPERTY_VALUEFIELD);
    }

    public void setValuefield(SelectorField valuefield) {
        set(PROPERTY_VALUEFIELD, valuefield);
    }

    public SelectorField getDisplayfield() {
        return (SelectorField) get(PROPERTY_DISPLAYFIELD);
    }

    public void setDisplayfield(SelectorField displayfield) {
        set(PROPERTY_DISPLAYFIELD, displayfield);
    }

    public String getSuggestiontextmatchstyle() {
        return (String) get(PROPERTY_SUGGESTIONTEXTMATCHSTYLE);
    }

    public void setSuggestiontextmatchstyle(String suggestiontextmatchstyle) {
        set(PROPERTY_SUGGESTIONTEXTMATCHSTYLE, suggestiontextmatchstyle);
    }

    public String getPopuptextmatchstyle() {
        return (String) get(PROPERTY_POPUPTEXTMATCHSTYLE);
    }

    public void setPopuptextmatchstyle(String popuptextmatchstyle) {
        set(PROPERTY_POPUPTEXTMATCHSTYLE, popuptextmatchstyle);
    }

    public String getHQL() {
        return (String) get(PROPERTY_HQL);
    }

    public void setHQL(String hQL) {
        set(PROPERTY_HQL, hQL);
    }

    public String getEntityAlias() {
        return (String) get(PROPERTY_ENTITYALIAS);
    }

    public void setEntityAlias(String entityAlias) {
        set(PROPERTY_ENTITYALIAS, entityAlias);
    }

    public Boolean isCustomQuery() {
        return (Boolean) get(PROPERTY_CUSTOMQUERY);
    }

    public void setCustomQuery(Boolean customQuery) {
        set(PROPERTY_CUSTOMQUERY, customQuery);
    }

    @SuppressWarnings("unchecked")
    public List<SelectorField> getOBUISELSelectorFieldList() {
        return (List<SelectorField>) get(PROPERTY_OBUISELSELECTORFIELDLIST);
    }

    public void setOBUISELSelectorFieldList(List<SelectorField> oBUISELSelectorFieldList) {
        set(PROPERTY_OBUISELSELECTORFIELDLIST, oBUISELSelectorFieldList);
    }

    @SuppressWarnings("unchecked")
    public List<SelectorTrl> getOBUISELSelectorTrlList() {
        return (List<SelectorTrl>) get(PROPERTY_OBUISELSELECTORTRLLIST);
    }

    public void setOBUISELSelectorTrlList(List<SelectorTrl> oBUISELSelectorTrlList) {
        set(PROPERTY_OBUISELSELECTORTRLLIST, oBUISELSelectorTrlList);
    }

}
