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
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.domain.Reference;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Field;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.service.datasource.DatasourceField;
/**
 * Entity class for entity OBUISEL_Selector_Field (stored in table OBUISEL_Selector_Field).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class SelectorField extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBUISEL_Selector_Field";
    public static final String ENTITY_NAME = "OBUISEL_Selector_Field";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_OBUISELSELECTOR = "obuiselSelector";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_COLUMN = "column";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_PROPERTY = "property";
    public static final String PROPERTY_OBSERDSDATASOURCEFIELD = "obserdsDatasourceField";
    public static final String PROPERTY_CENTRALMAINTENANCE = "centralMaintenance";
    public static final String PROPERTY_SORTABLE = "sortable";
    public static final String PROPERTY_FILTERABLE = "filterable";
    public static final String PROPERTY_SEARCHINSUGGESTIONBOX = "searchinsuggestionbox";
    public static final String PROPERTY_SHOWINGRID = "showingrid";
    public static final String PROPERTY_SORTNO = "sortno";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ISOUTFIELD = "isoutfield";
    public static final String PROPERTY_DEFAULTEXPRESSION = "defaultExpression";
    public static final String PROPERTY_SUFFIX = "suffix";
    public static final String PROPERTY_DISPLAYCOLUMNALIAS = "displayColumnAlias";
    public static final String PROPERTY_CLAUSELEFTPART = "clauseLeftPart";
    public static final String PROPERTY_REFERENCE = "reference";
    public static final String PROPERTY_SHOWINPICKLIST = "showInPicklist";
    public static final String PROPERTY_ADFIELDEMOBUISELOUTFIELDIDLIST = "aDFieldEMObuiselOutfieldIDList";
    public static final String PROPERTY_OBUISELSELECTORVALUEFIELDIDLIST = "oBUISELSelectorValuefieldIDList";
    public static final String PROPERTY_OBUISELSELECTORDISPLAYFIELDIDLIST = "oBUISELSelectorDisplayfieldIDList";
    public static final String PROPERTY_OBUISELSELECTORFIELDTRLLIST = "oBUISELSelectorFieldTrlList";

    public SelectorField() {
        setDefaultValue(PROPERTY_CENTRALMAINTENANCE, true);
        setDefaultValue(PROPERTY_SORTABLE, true);
        setDefaultValue(PROPERTY_FILTERABLE, true);
        setDefaultValue(PROPERTY_SEARCHINSUGGESTIONBOX, false);
        setDefaultValue(PROPERTY_SHOWINGRID, true);
        setDefaultValue(PROPERTY_SORTNO, (long) 0);
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISOUTFIELD, false);
        setDefaultValue(PROPERTY_SHOWINPICKLIST, false);
        setDefaultValue(PROPERTY_ADFIELDEMOBUISELOUTFIELDIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORVALUEFIELDIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORDISPLAYFIELDIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORFIELDTRLLIST, new ArrayList<Object>());
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

    public Selector getObuiselSelector() {
        return (Selector) get(PROPERTY_OBUISELSELECTOR);
    }

    public void setObuiselSelector(Selector obuiselSelector) {
        set(PROPERTY_OBUISELSELECTOR, obuiselSelector);
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

    public Column getColumn() {
        return (Column) get(PROPERTY_COLUMN);
    }

    public void setColumn(Column column) {
        set(PROPERTY_COLUMN, column);
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

    public String getProperty() {
        return (String) get(PROPERTY_PROPERTY);
    }

    public void setProperty(String property) {
        set(PROPERTY_PROPERTY, property);
    }

    public DatasourceField getObserdsDatasourceField() {
        return (DatasourceField) get(PROPERTY_OBSERDSDATASOURCEFIELD);
    }

    public void setObserdsDatasourceField(DatasourceField obserdsDatasourceField) {
        set(PROPERTY_OBSERDSDATASOURCEFIELD, obserdsDatasourceField);
    }

    public Boolean isCentralMaintenance() {
        return (Boolean) get(PROPERTY_CENTRALMAINTENANCE);
    }

    public void setCentralMaintenance(Boolean centralMaintenance) {
        set(PROPERTY_CENTRALMAINTENANCE, centralMaintenance);
    }

    public Boolean isSortable() {
        return (Boolean) get(PROPERTY_SORTABLE);
    }

    public void setSortable(Boolean sortable) {
        set(PROPERTY_SORTABLE, sortable);
    }

    public Boolean isFilterable() {
        return (Boolean) get(PROPERTY_FILTERABLE);
    }

    public void setFilterable(Boolean filterable) {
        set(PROPERTY_FILTERABLE, filterable);
    }

    public Boolean isSearchinsuggestionbox() {
        return (Boolean) get(PROPERTY_SEARCHINSUGGESTIONBOX);
    }

    public void setSearchinsuggestionbox(Boolean searchinsuggestionbox) {
        set(PROPERTY_SEARCHINSUGGESTIONBOX, searchinsuggestionbox);
    }

    public Boolean isShowingrid() {
        return (Boolean) get(PROPERTY_SHOWINGRID);
    }

    public void setShowingrid(Boolean showingrid) {
        set(PROPERTY_SHOWINGRID, showingrid);
    }

    public Long getSortno() {
        return (Long) get(PROPERTY_SORTNO);
    }

    public void setSortno(Long sortno) {
        set(PROPERTY_SORTNO, sortno);
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

    public Boolean isOutfield() {
        return (Boolean) get(PROPERTY_ISOUTFIELD);
    }

    public void setOutfield(Boolean isoutfield) {
        set(PROPERTY_ISOUTFIELD, isoutfield);
    }

    public String getDefaultExpression() {
        return (String) get(PROPERTY_DEFAULTEXPRESSION);
    }

    public void setDefaultExpression(String defaultExpression) {
        set(PROPERTY_DEFAULTEXPRESSION, defaultExpression);
    }

    public String getSuffix() {
        return (String) get(PROPERTY_SUFFIX);
    }

    public void setSuffix(String suffix) {
        set(PROPERTY_SUFFIX, suffix);
    }

    public String getDisplayColumnAlias() {
        return (String) get(PROPERTY_DISPLAYCOLUMNALIAS);
    }

    public void setDisplayColumnAlias(String displayColumnAlias) {
        set(PROPERTY_DISPLAYCOLUMNALIAS, displayColumnAlias);
    }

    public String getClauseLeftPart() {
        return (String) get(PROPERTY_CLAUSELEFTPART);
    }

    public void setClauseLeftPart(String clauseLeftPart) {
        set(PROPERTY_CLAUSELEFTPART, clauseLeftPart);
    }

    public Reference getReference() {
        return (Reference) get(PROPERTY_REFERENCE);
    }

    public void setReference(Reference reference) {
        set(PROPERTY_REFERENCE, reference);
    }

    public Boolean isShowInPicklist() {
        return (Boolean) get(PROPERTY_SHOWINPICKLIST);
    }

    public void setShowInPicklist(Boolean showInPicklist) {
        set(PROPERTY_SHOWINPICKLIST, showInPicklist);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getADFieldEMObuiselOutfieldIDList() {
        return (List<Field>) get(PROPERTY_ADFIELDEMOBUISELOUTFIELDIDLIST);
    }

    public void setADFieldEMObuiselOutfieldIDList(List<Field> aDFieldEMObuiselOutfieldIDList) {
        set(PROPERTY_ADFIELDEMOBUISELOUTFIELDIDLIST, aDFieldEMObuiselOutfieldIDList);
    }

    @SuppressWarnings("unchecked")
    public List<Selector> getOBUISELSelectorValuefieldIDList() {
        return (List<Selector>) get(PROPERTY_OBUISELSELECTORVALUEFIELDIDLIST);
    }

    public void setOBUISELSelectorValuefieldIDList(List<Selector> oBUISELSelectorValuefieldIDList) {
        set(PROPERTY_OBUISELSELECTORVALUEFIELDIDLIST, oBUISELSelectorValuefieldIDList);
    }

    @SuppressWarnings("unchecked")
    public List<Selector> getOBUISELSelectorDisplayfieldIDList() {
        return (List<Selector>) get(PROPERTY_OBUISELSELECTORDISPLAYFIELDIDLIST);
    }

    public void setOBUISELSelectorDisplayfieldIDList(List<Selector> oBUISELSelectorDisplayfieldIDList) {
        set(PROPERTY_OBUISELSELECTORDISPLAYFIELDIDLIST, oBUISELSelectorDisplayfieldIDList);
    }

    @SuppressWarnings("unchecked")
    public List<SelectorFieldTrl> getOBUISELSelectorFieldTrlList() {
        return (List<SelectorFieldTrl>) get(PROPERTY_OBUISELSELECTORFIELDTRLLIST);
    }

    public void setOBUISELSelectorFieldTrlList(List<SelectorFieldTrl> oBUISELSelectorFieldTrlList) {
        set(PROPERTY_OBUISELSELECTORFIELDTRLLIST, oBUISELSelectorFieldTrlList);
    }

}
