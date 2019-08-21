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
package org.openbravo.model.ad.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.domain.Callout;
import org.openbravo.model.ad.domain.Reference;
import org.openbravo.model.ad.domain.ReferencedTable;
import org.openbravo.model.ad.domain.Selector;
import org.openbravo.model.ad.domain.Validation;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Element;
import org.openbravo.model.ad.ui.Field;
import org.openbravo.model.ad.ui.Process;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.ad.utility.AuditTrail;
import org.openbravo.model.ad.utility.DataSetColumn;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.userinterface.selector.SelectorField;
/**
 * Entity class for entity ADColumn (stored in table AD_Column).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Column extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Column";
    public static final String ENTITY_NAME = "ADColumn";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_DBCOLUMNNAME = "dBColumnName";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_REFERENCE = "reference";
    public static final String PROPERTY_REFERENCESEARCHKEY = "referenceSearchKey";
    public static final String PROPERTY_VALIDATION = "validation";
    public static final String PROPERTY_LENGTH = "length";
    public static final String PROPERTY_DEFAULTVALUE = "defaultValue";
    public static final String PROPERTY_KEYCOLUMN = "keyColumn";
    public static final String PROPERTY_LINKTOPARENTCOLUMN = "linkToParentColumn";
    public static final String PROPERTY_MANDATORY = "mandatory";
    public static final String PROPERTY_UPDATABLE = "updatable";
    public static final String PROPERTY_READONLYLOGIC = "readOnlyLogic";
    public static final String PROPERTY_IDENTIFIER = "identifier";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_TRANSLATION = "translation";
    public static final String PROPERTY_DISPLAYENCRIPTION = "displayEncription";
    public static final String PROPERTY_CALLOUTFUNCTION = "calloutFunction";
    public static final String PROPERTY_VALUEFORMAT = "valueFormat";
    public static final String PROPERTY_MINVALUE = "minValue";
    public static final String PROPERTY_MAXVALUE = "maxValue";
    public static final String PROPERTY_FILTERCOLUMN = "filterColumn";
    public static final String PROPERTY_APPLICATIONELEMENT = "applicationElement";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_STOREDINSESSION = "storedInSession";
    public static final String PROPERTY_SECONDARYKEY = "secondaryKey";
    public static final String PROPERTY_DEENCRYPTABLE = "deencryptable";
    public static final String PROPERTY_CALLOUT = "callout";
    public static final String PROPERTY_DEVELOPMENTSTATUS = "developmentStatus";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_TRANSIENT = "transient";
    public static final String PROPERTY_TRANSIENTCONDITION = "transientCondition";
    public static final String PROPERTY_ISAUTOSAVE = "isautosave";
    public static final String PROPERTY_VALIDATEONNEW = "validateOnNew";
    public static final String PROPERTY_EXCLUDEAUDIT = "excludeAudit";
    public static final String PROPERTY_IMAGESIZEVALUESACTION = "imageSizeValuesAction";
    public static final String PROPERTY_IMAGEWIDTH = "imageWidth";
    public static final String PROPERTY_IMAGEHEIGHT = "imageHeight";
    public static final String PROPERTY_USEAUTOMATICSEQUENCE = "useAutomaticSequence";
    public static final String PROPERTY_OBUIAPPPROCESS = "oBUIAPPProcess";
    public static final String PROPERTY_SQLLOGIC = "sqllogic";
    public static final String PROPERTY_ADFIELDLIST = "aDFieldList";
    public static final String PROPERTY_ADREFERENCEDTABLEKEYCOLUMNLIST = "aDReferencedTableKeyColumnList";
    public static final String PROPERTY_ADREFERENCEDTABLEDISPLAYEDCOLUMNLIST = "aDReferencedTableDisplayedColumnList";
    public static final String PROPERTY_ADSELECTORLIST = "aDSelectorList";
    public static final String PROPERTY_ADTABLIST = "aDTabList";
    public static final String PROPERTY_ADTABORDERCOLUMNLIST = "aDTabOrderColumnList";
    public static final String PROPERTY_ADTABINCLUDEDCOLUMNLIST = "aDTabIncludedColumnList";
    public static final String PROPERTY_ADTABLEACCTDATECOLUMNIDLIST = "aDTableAcctdateColumnIDList";
    public static final String PROPERTY_AUDITTRAILLIST = "auditTrailList";
    public static final String PROPERTY_DATASETCOLUMNLIST = "dataSetColumnList";
    public static final String PROPERTY_OBUISELSELECTORLIST = "oBUISELSelectorList";
    public static final String PROPERTY_OBUISELSELECTORFIELDLIST = "oBUISELSelectorFieldList";

    public Column() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_KEYCOLUMN, false);
        setDefaultValue(PROPERTY_LINKTOPARENTCOLUMN, false);
        setDefaultValue(PROPERTY_MANDATORY, false);
        setDefaultValue(PROPERTY_UPDATABLE, true);
        setDefaultValue(PROPERTY_IDENTIFIER, false);
        setDefaultValue(PROPERTY_TRANSLATION, false);
        setDefaultValue(PROPERTY_DISPLAYENCRIPTION, false);
        setDefaultValue(PROPERTY_FILTERCOLUMN, false);
        setDefaultValue(PROPERTY_STOREDINSESSION, false);
        setDefaultValue(PROPERTY_SECONDARYKEY, false);
        setDefaultValue(PROPERTY_DEENCRYPTABLE, false);
        setDefaultValue(PROPERTY_DEVELOPMENTSTATUS, "RE");
        setDefaultValue(PROPERTY_TRANSIENT, false);
        setDefaultValue(PROPERTY_ISAUTOSAVE, true);
        setDefaultValue(PROPERTY_VALIDATEONNEW, true);
        setDefaultValue(PROPERTY_EXCLUDEAUDIT, false);
        setDefaultValue(PROPERTY_USEAUTOMATICSEQUENCE, false);
        setDefaultValue(PROPERTY_ADFIELDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADREFERENCEDTABLEKEYCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADREFERENCEDTABLEDISPLAYEDCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADSELECTORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABORDERCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABINCLUDEDCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLEACCTDATECOLUMNIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AUDITTRAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATASETCOLUMNLIST, new ArrayList<Object>());
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

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
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

    public String getDBColumnName() {
        return (String) get(PROPERTY_DBCOLUMNNAME);
    }

    public void setDBColumnName(String dBColumnName) {
        set(PROPERTY_DBCOLUMNNAME, dBColumnName);
    }

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
    }

    public Reference getReference() {
        return (Reference) get(PROPERTY_REFERENCE);
    }

    public void setReference(Reference reference) {
        set(PROPERTY_REFERENCE, reference);
    }

    public Reference getReferenceSearchKey() {
        return (Reference) get(PROPERTY_REFERENCESEARCHKEY);
    }

    public void setReferenceSearchKey(Reference referenceSearchKey) {
        set(PROPERTY_REFERENCESEARCHKEY, referenceSearchKey);
    }

    public Validation getValidation() {
        return (Validation) get(PROPERTY_VALIDATION);
    }

    public void setValidation(Validation validation) {
        set(PROPERTY_VALIDATION, validation);
    }

    public Long getLength() {
        return (Long) get(PROPERTY_LENGTH);
    }

    public void setLength(Long length) {
        set(PROPERTY_LENGTH, length);
    }

    public String getDefaultValue() {
        return (String) get(PROPERTY_DEFAULTVALUE);
    }

    public void setDefaultValue(String defaultValue) {
        set(PROPERTY_DEFAULTVALUE, defaultValue);
    }

    public Boolean isKeyColumn() {
        return (Boolean) get(PROPERTY_KEYCOLUMN);
    }

    public void setKeyColumn(Boolean keyColumn) {
        set(PROPERTY_KEYCOLUMN, keyColumn);
    }

    public Boolean isLinkToParentColumn() {
        return (Boolean) get(PROPERTY_LINKTOPARENTCOLUMN);
    }

    public void setLinkToParentColumn(Boolean linkToParentColumn) {
        set(PROPERTY_LINKTOPARENTCOLUMN, linkToParentColumn);
    }

    public Boolean isMandatory() {
        return (Boolean) get(PROPERTY_MANDATORY);
    }

    public void setMandatory(Boolean mandatory) {
        set(PROPERTY_MANDATORY, mandatory);
    }

    public Boolean isUpdatable() {
        return (Boolean) get(PROPERTY_UPDATABLE);
    }

    public void setUpdatable(Boolean updatable) {
        set(PROPERTY_UPDATABLE, updatable);
    }

    public String getReadOnlyLogic() {
        return (String) get(PROPERTY_READONLYLOGIC);
    }

    public void setReadOnlyLogic(String readOnlyLogic) {
        set(PROPERTY_READONLYLOGIC, readOnlyLogic);
    }

    public Boolean isIdentifier() {
        return (Boolean) get(PROPERTY_IDENTIFIER);
    }

    public void setIdentifier(Boolean identifier) {
        set(PROPERTY_IDENTIFIER, identifier);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public Boolean isTranslation() {
        return (Boolean) get(PROPERTY_TRANSLATION);
    }

    public void setTranslation(Boolean translation) {
        set(PROPERTY_TRANSLATION, translation);
    }

    public Boolean isDisplayEncription() {
        return (Boolean) get(PROPERTY_DISPLAYENCRIPTION);
    }

    public void setDisplayEncription(Boolean displayEncription) {
        set(PROPERTY_DISPLAYENCRIPTION, displayEncription);
    }

    public String getCalloutFunction() {
        return (String) get(PROPERTY_CALLOUTFUNCTION);
    }

    public void setCalloutFunction(String calloutFunction) {
        set(PROPERTY_CALLOUTFUNCTION, calloutFunction);
    }

    public String getValueFormat() {
        return (String) get(PROPERTY_VALUEFORMAT);
    }

    public void setValueFormat(String valueFormat) {
        set(PROPERTY_VALUEFORMAT, valueFormat);
    }

    public String getMinValue() {
        return (String) get(PROPERTY_MINVALUE);
    }

    public void setMinValue(String minValue) {
        set(PROPERTY_MINVALUE, minValue);
    }

    public String getMaxValue() {
        return (String) get(PROPERTY_MAXVALUE);
    }

    public void setMaxValue(String maxValue) {
        set(PROPERTY_MAXVALUE, maxValue);
    }

    public Boolean isFilterColumn() {
        return (Boolean) get(PROPERTY_FILTERCOLUMN);
    }

    public void setFilterColumn(Boolean filterColumn) {
        set(PROPERTY_FILTERCOLUMN, filterColumn);
    }

    public Element getApplicationElement() {
        return (Element) get(PROPERTY_APPLICATIONELEMENT);
    }

    public void setApplicationElement(Element applicationElement) {
        set(PROPERTY_APPLICATIONELEMENT, applicationElement);
    }

    public Process getProcess() {
        return (Process) get(PROPERTY_PROCESS);
    }

    public void setProcess(Process process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isStoredInSession() {
        return (Boolean) get(PROPERTY_STOREDINSESSION);
    }

    public void setStoredInSession(Boolean storedInSession) {
        set(PROPERTY_STOREDINSESSION, storedInSession);
    }

    public Boolean isSecondaryKey() {
        return (Boolean) get(PROPERTY_SECONDARYKEY);
    }

    public void setSecondaryKey(Boolean secondaryKey) {
        set(PROPERTY_SECONDARYKEY, secondaryKey);
    }

    public Boolean isDeencryptable() {
        return (Boolean) get(PROPERTY_DEENCRYPTABLE);
    }

    public void setDeencryptable(Boolean deencryptable) {
        set(PROPERTY_DEENCRYPTABLE, deencryptable);
    }

    public Callout getCallout() {
        return (Callout) get(PROPERTY_CALLOUT);
    }

    public void setCallout(Callout callout) {
        set(PROPERTY_CALLOUT, callout);
    }

    public String getDevelopmentStatus() {
        return (String) get(PROPERTY_DEVELOPMENTSTATUS);
    }

    public void setDevelopmentStatus(String developmentStatus) {
        set(PROPERTY_DEVELOPMENTSTATUS, developmentStatus);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public Long getPosition() {
        return (Long) get(PROPERTY_POSITION);
    }

    public void setPosition(Long position) {
        set(PROPERTY_POSITION, position);
    }

    public Boolean isTransient() {
        return (Boolean) get(PROPERTY_TRANSIENT);
    }

    public void setTransient(Boolean trnsnt) {
        set(PROPERTY_TRANSIENT, trnsnt);
    }

    public String getTransientCondition() {
        return (String) get(PROPERTY_TRANSIENTCONDITION);
    }

    public void setTransientCondition(String transientCondition) {
        set(PROPERTY_TRANSIENTCONDITION, transientCondition);
    }

    public Boolean isAutosave() {
        return (Boolean) get(PROPERTY_ISAUTOSAVE);
    }

    public void setAutosave(Boolean isautosave) {
        set(PROPERTY_ISAUTOSAVE, isautosave);
    }

    public Boolean isValidateOnNew() {
        return (Boolean) get(PROPERTY_VALIDATEONNEW);
    }

    public void setValidateOnNew(Boolean validateOnNew) {
        set(PROPERTY_VALIDATEONNEW, validateOnNew);
    }

    public Boolean isExcludeAudit() {
        return (Boolean) get(PROPERTY_EXCLUDEAUDIT);
    }

    public void setExcludeAudit(Boolean excludeAudit) {
        set(PROPERTY_EXCLUDEAUDIT, excludeAudit);
    }

    public String getImageSizeValuesAction() {
        return (String) get(PROPERTY_IMAGESIZEVALUESACTION);
    }

    public void setImageSizeValuesAction(String imageSizeValuesAction) {
        set(PROPERTY_IMAGESIZEVALUESACTION, imageSizeValuesAction);
    }

    public Long getImageWidth() {
        return (Long) get(PROPERTY_IMAGEWIDTH);
    }

    public void setImageWidth(Long imageWidth) {
        set(PROPERTY_IMAGEWIDTH, imageWidth);
    }

    public Long getImageHeight() {
        return (Long) get(PROPERTY_IMAGEHEIGHT);
    }

    public void setImageHeight(Long imageHeight) {
        set(PROPERTY_IMAGEHEIGHT, imageHeight);
    }

    public Boolean isUseAutomaticSequence() {
        return (Boolean) get(PROPERTY_USEAUTOMATICSEQUENCE);
    }

    public void setUseAutomaticSequence(Boolean useAutomaticSequence) {
        set(PROPERTY_USEAUTOMATICSEQUENCE, useAutomaticSequence);
    }

    public org.openbravo.client.application.Process getOBUIAPPProcess() {
        return (org.openbravo.client.application.Process) get(PROPERTY_OBUIAPPPROCESS);
    }

    public void setOBUIAPPProcess(org.openbravo.client.application.Process oBUIAPPProcess) {
        set(PROPERTY_OBUIAPPPROCESS, oBUIAPPProcess);
    }

    public String getSqllogic() {
        return (String) get(PROPERTY_SQLLOGIC);
    }

    public void setSqllogic(String sqllogic) {
        set(PROPERTY_SQLLOGIC, sqllogic);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getADFieldList() {
        return (List<Field>) get(PROPERTY_ADFIELDLIST);
    }

    public void setADFieldList(List<Field> aDFieldList) {
        set(PROPERTY_ADFIELDLIST, aDFieldList);
    }

    @SuppressWarnings("unchecked")
    public List<ReferencedTable> getADReferencedTableKeyColumnList() {
        return (List<ReferencedTable>) get(PROPERTY_ADREFERENCEDTABLEKEYCOLUMNLIST);
    }

    public void setADReferencedTableKeyColumnList(List<ReferencedTable> aDReferencedTableKeyColumnList) {
        set(PROPERTY_ADREFERENCEDTABLEKEYCOLUMNLIST, aDReferencedTableKeyColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<ReferencedTable> getADReferencedTableDisplayedColumnList() {
        return (List<ReferencedTable>) get(PROPERTY_ADREFERENCEDTABLEDISPLAYEDCOLUMNLIST);
    }

    public void setADReferencedTableDisplayedColumnList(List<ReferencedTable> aDReferencedTableDisplayedColumnList) {
        set(PROPERTY_ADREFERENCEDTABLEDISPLAYEDCOLUMNLIST, aDReferencedTableDisplayedColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<Selector> getADSelectorList() {
        return (List<Selector>) get(PROPERTY_ADSELECTORLIST);
    }

    public void setADSelectorList(List<Selector> aDSelectorList) {
        set(PROPERTY_ADSELECTORLIST, aDSelectorList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabList() {
        return (List<Tab>) get(PROPERTY_ADTABLIST);
    }

    public void setADTabList(List<Tab> aDTabList) {
        set(PROPERTY_ADTABLIST, aDTabList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabOrderColumnList() {
        return (List<Tab>) get(PROPERTY_ADTABORDERCOLUMNLIST);
    }

    public void setADTabOrderColumnList(List<Tab> aDTabOrderColumnList) {
        set(PROPERTY_ADTABORDERCOLUMNLIST, aDTabOrderColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabIncludedColumnList() {
        return (List<Tab>) get(PROPERTY_ADTABINCLUDEDCOLUMNLIST);
    }

    public void setADTabIncludedColumnList(List<Tab> aDTabIncludedColumnList) {
        set(PROPERTY_ADTABINCLUDEDCOLUMNLIST, aDTabIncludedColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<Table> getADTableAcctdateColumnIDList() {
        return (List<Table>) get(PROPERTY_ADTABLEACCTDATECOLUMNIDLIST);
    }

    public void setADTableAcctdateColumnIDList(List<Table> aDTableAcctdateColumnIDList) {
        set(PROPERTY_ADTABLEACCTDATECOLUMNIDLIST, aDTableAcctdateColumnIDList);
    }

    @SuppressWarnings("unchecked")
    public List<AuditTrail> getAuditTrailList() {
        return (List<AuditTrail>) get(PROPERTY_AUDITTRAILLIST);
    }

    public void setAuditTrailList(List<AuditTrail> auditTrailList) {
        set(PROPERTY_AUDITTRAILLIST, auditTrailList);
    }

    @SuppressWarnings("unchecked")
    public List<DataSetColumn> getDataSetColumnList() {
        return (List<DataSetColumn>) get(PROPERTY_DATASETCOLUMNLIST);
    }

    public void setDataSetColumnList(List<DataSetColumn> dataSetColumnList) {
        set(PROPERTY_DATASETCOLUMNLIST, dataSetColumnList);
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
