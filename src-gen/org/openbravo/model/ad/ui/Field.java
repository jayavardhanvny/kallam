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
import org.openbravo.model.ad.access.FieldAccess;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.userinterface.selector.SelectorField;
/**
 * Entity class for entity ADField (stored in table AD_Field).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Field extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Field";
    public static final String ENTITY_NAME = "ADField";
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
    public static final String PROPERTY_CENTRALMAINTENANCE = "centralMaintenance";
    public static final String PROPERTY_TAB = "tab";
    public static final String PROPERTY_COLUMN = "column";
    public static final String PROPERTY_PROPERTY = "property";
    public static final String PROPERTY_IGNOREINWAD = "ignoreInWad";
    public static final String PROPERTY_FIELDGROUP = "fieldGroup";
    public static final String PROPERTY_DISPLAYED = "displayed";
    public static final String PROPERTY_DISPLAYLOGIC = "displayLogic";
    public static final String PROPERTY_DISPLAYEDLENGTH = "displayedLength";
    public static final String PROPERTY_READONLY = "readOnly";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_RECORDSORTNO = "recordSortNo";
    public static final String PROPERTY_DISPLAYONSAMELINE = "displayOnSameLine";
    public static final String PROPERTY_DISPLAYFIELDONLY = "displayFieldOnly";
    public static final String PROPERTY_DISPLAYENCRIPTION = "displayEncription";
    public static final String PROPERTY_SHOWINGRIDVIEW = "showInGridView";
    public static final String PROPERTY_ISFIRSTFOCUSEDFIELD = "isFirstFocusedField";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_GRIDPOSITION = "gridPosition";
    public static final String PROPERTY_STARTINODDCOLUMN = "startinoddcolumn";
    public static final String PROPERTY_STARTNEWLINE = "startnewline";
    public static final String PROPERTY_SHOWNINSTATUSBAR = "shownInStatusBar";
    public static final String PROPERTY_ONCHANGEFUNCTION = "onChangeFunction";
    public static final String PROPERTY_CLIENTCLASS = "clientclass";
    public static final String PROPERTY_OBUIAPPCOLSPAN = "obuiappColspan";
    public static final String PROPERTY_OBUIAPPROWSPAN = "obuiappRowspan";
    public static final String PROPERTY_OBUIAPPVALIDATOR = "obuiappValidator";
    public static final String PROPERTY_OBUIAPPSHOWSUMMARY = "oBUIAPPShowSummary";
    public static final String PROPERTY_OBUIAPPSUMMARYFN = "obuiappSummaryfn";
    public static final String PROPERTY_OBUISELOUTFIELD = "obuiselOutfield";
    public static final String PROPERTY_ADFIELDACCESSLIST = "aDFieldAccessList";
    public static final String PROPERTY_ADFIELDTRLLIST = "aDFieldTrlList";

    public Field() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CENTRALMAINTENANCE, true);
        setDefaultValue(PROPERTY_IGNOREINWAD, false);
        setDefaultValue(PROPERTY_DISPLAYED, true);
        setDefaultValue(PROPERTY_DISPLAYEDLENGTH, (long) 0);
        setDefaultValue(PROPERTY_READONLY, false);
        setDefaultValue(PROPERTY_DISPLAYONSAMELINE, false);
        setDefaultValue(PROPERTY_DISPLAYFIELDONLY, false);
        setDefaultValue(PROPERTY_DISPLAYENCRIPTION, false);
        setDefaultValue(PROPERTY_SHOWINGRIDVIEW, false);
        setDefaultValue(PROPERTY_ISFIRSTFOCUSEDFIELD, false);
        setDefaultValue(PROPERTY_STARTINODDCOLUMN, false);
        setDefaultValue(PROPERTY_STARTNEWLINE, false);
        setDefaultValue(PROPERTY_SHOWNINSTATUSBAR, false);
        setDefaultValue(PROPERTY_OBUIAPPSHOWSUMMARY, false);
        setDefaultValue(PROPERTY_ADFIELDACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADFIELDTRLLIST, new ArrayList<Object>());
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

    public Boolean isCentralMaintenance() {
        return (Boolean) get(PROPERTY_CENTRALMAINTENANCE);
    }

    public void setCentralMaintenance(Boolean centralMaintenance) {
        set(PROPERTY_CENTRALMAINTENANCE, centralMaintenance);
    }

    public Tab getTab() {
        return (Tab) get(PROPERTY_TAB);
    }

    public void setTab(Tab tab) {
        set(PROPERTY_TAB, tab);
    }

    public Column getColumn() {
        return (Column) get(PROPERTY_COLUMN);
    }

    public void setColumn(Column column) {
        set(PROPERTY_COLUMN, column);
    }

    public String getProperty() {
        return (String) get(PROPERTY_PROPERTY);
    }

    public void setProperty(String property) {
        set(PROPERTY_PROPERTY, property);
    }

    public Boolean isIgnoreInWad() {
        return (Boolean) get(PROPERTY_IGNOREINWAD);
    }

    public void setIgnoreInWad(Boolean ignoreInWad) {
        set(PROPERTY_IGNOREINWAD, ignoreInWad);
    }

    public FieldGroup getFieldGroup() {
        return (FieldGroup) get(PROPERTY_FIELDGROUP);
    }

    public void setFieldGroup(FieldGroup fieldGroup) {
        set(PROPERTY_FIELDGROUP, fieldGroup);
    }

    public Boolean isDisplayed() {
        return (Boolean) get(PROPERTY_DISPLAYED);
    }

    public void setDisplayed(Boolean displayed) {
        set(PROPERTY_DISPLAYED, displayed);
    }

    public String getDisplayLogic() {
        return (String) get(PROPERTY_DISPLAYLOGIC);
    }

    public void setDisplayLogic(String displayLogic) {
        set(PROPERTY_DISPLAYLOGIC, displayLogic);
    }

    public Long getDisplayedLength() {
        return (Long) get(PROPERTY_DISPLAYEDLENGTH);
    }

    public void setDisplayedLength(Long displayedLength) {
        set(PROPERTY_DISPLAYEDLENGTH, displayedLength);
    }

    public Boolean isReadOnly() {
        return (Boolean) get(PROPERTY_READONLY);
    }

    public void setReadOnly(Boolean readOnly) {
        set(PROPERTY_READONLY, readOnly);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public Long getRecordSortNo() {
        return (Long) get(PROPERTY_RECORDSORTNO);
    }

    public void setRecordSortNo(Long recordSortNo) {
        set(PROPERTY_RECORDSORTNO, recordSortNo);
    }

    public Boolean isDisplayOnSameLine() {
        return (Boolean) get(PROPERTY_DISPLAYONSAMELINE);
    }

    public void setDisplayOnSameLine(Boolean displayOnSameLine) {
        set(PROPERTY_DISPLAYONSAMELINE, displayOnSameLine);
    }

    public Boolean isDisplayFieldOnly() {
        return (Boolean) get(PROPERTY_DISPLAYFIELDONLY);
    }

    public void setDisplayFieldOnly(Boolean displayFieldOnly) {
        set(PROPERTY_DISPLAYFIELDONLY, displayFieldOnly);
    }

    public Boolean isDisplayEncription() {
        return (Boolean) get(PROPERTY_DISPLAYENCRIPTION);
    }

    public void setDisplayEncription(Boolean displayEncription) {
        set(PROPERTY_DISPLAYENCRIPTION, displayEncription);
    }

    public Boolean isShowInGridView() {
        return (Boolean) get(PROPERTY_SHOWINGRIDVIEW);
    }

    public void setShowInGridView(Boolean showInGridView) {
        set(PROPERTY_SHOWINGRIDVIEW, showInGridView);
    }

    public Boolean isFirstFocusedField() {
        return (Boolean) get(PROPERTY_ISFIRSTFOCUSEDFIELD);
    }

    public void setFirstFocusedField(Boolean isFirstFocusedField) {
        set(PROPERTY_ISFIRSTFOCUSEDFIELD, isFirstFocusedField);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public Long getGridPosition() {
        return (Long) get(PROPERTY_GRIDPOSITION);
    }

    public void setGridPosition(Long gridPosition) {
        set(PROPERTY_GRIDPOSITION, gridPosition);
    }

    public Boolean isStartinoddcolumn() {
        return (Boolean) get(PROPERTY_STARTINODDCOLUMN);
    }

    public void setStartinoddcolumn(Boolean startinoddcolumn) {
        set(PROPERTY_STARTINODDCOLUMN, startinoddcolumn);
    }

    public Boolean isStartnewline() {
        return (Boolean) get(PROPERTY_STARTNEWLINE);
    }

    public void setStartnewline(Boolean startnewline) {
        set(PROPERTY_STARTNEWLINE, startnewline);
    }

    public Boolean isShownInStatusBar() {
        return (Boolean) get(PROPERTY_SHOWNINSTATUSBAR);
    }

    public void setShownInStatusBar(Boolean shownInStatusBar) {
        set(PROPERTY_SHOWNINSTATUSBAR, shownInStatusBar);
    }

    public String getOnChangeFunction() {
        return (String) get(PROPERTY_ONCHANGEFUNCTION);
    }

    public void setOnChangeFunction(String onChangeFunction) {
        set(PROPERTY_ONCHANGEFUNCTION, onChangeFunction);
    }

    public String getClientclass() {
        return (String) get(PROPERTY_CLIENTCLASS);
    }

    public void setClientclass(String clientclass) {
        set(PROPERTY_CLIENTCLASS, clientclass);
    }

    public Long getObuiappColspan() {
        return (Long) get(PROPERTY_OBUIAPPCOLSPAN);
    }

    public void setObuiappColspan(Long obuiappColspan) {
        set(PROPERTY_OBUIAPPCOLSPAN, obuiappColspan);
    }

    public Long getObuiappRowspan() {
        return (Long) get(PROPERTY_OBUIAPPROWSPAN);
    }

    public void setObuiappRowspan(Long obuiappRowspan) {
        set(PROPERTY_OBUIAPPROWSPAN, obuiappRowspan);
    }

    public String getObuiappValidator() {
        return (String) get(PROPERTY_OBUIAPPVALIDATOR);
    }

    public void setObuiappValidator(String obuiappValidator) {
        set(PROPERTY_OBUIAPPVALIDATOR, obuiappValidator);
    }

    public Boolean isOBUIAPPShowSummary() {
        return (Boolean) get(PROPERTY_OBUIAPPSHOWSUMMARY);
    }

    public void setOBUIAPPShowSummary(Boolean oBUIAPPShowSummary) {
        set(PROPERTY_OBUIAPPSHOWSUMMARY, oBUIAPPShowSummary);
    }

    public String getObuiappSummaryfn() {
        return (String) get(PROPERTY_OBUIAPPSUMMARYFN);
    }

    public void setObuiappSummaryfn(String obuiappSummaryfn) {
        set(PROPERTY_OBUIAPPSUMMARYFN, obuiappSummaryfn);
    }

    public SelectorField getObuiselOutfield() {
        return (SelectorField) get(PROPERTY_OBUISELOUTFIELD);
    }

    public void setObuiselOutfield(SelectorField obuiselOutfield) {
        set(PROPERTY_OBUISELOUTFIELD, obuiselOutfield);
    }

    @SuppressWarnings("unchecked")
    public List<FieldAccess> getADFieldAccessList() {
        return (List<FieldAccess>) get(PROPERTY_ADFIELDACCESSLIST);
    }

    public void setADFieldAccessList(List<FieldAccess> aDFieldAccessList) {
        set(PROPERTY_ADFIELDACCESSLIST, aDFieldAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<FieldTrl> getADFieldTrlList() {
        return (List<FieldTrl>) get(PROPERTY_ADFIELDTRLLIST);
    }

    public void setADFieldTrlList(List<FieldTrl> aDFieldTrlList) {
        set(PROPERTY_ADFIELDTRLLIST, aDFieldTrlList);
    }

}
