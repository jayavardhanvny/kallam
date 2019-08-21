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
package org.openbravo.client.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.myob.WidgetClass;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.domain.Reference;
import org.openbravo.model.ad.domain.Validation;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Element;
import org.openbravo.model.ad.ui.FieldGroup;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity OBUIAPP_Parameter (stored in table OBUIAPP_Parameter).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Parameter extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBUIAPP_Parameter";
    public static final String ENTITY_NAME = "OBUIAPP_Parameter";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_REFERENCE = "reference";
    public static final String PROPERTY_REFERENCESEARCHKEY = "referenceSearchKey";
    public static final String PROPERTY_DBCOLUMNNAME = "dBColumnName";
    public static final String PROPERTY_CENTRALMAINTENANCE = "centralMaintenance";
    public static final String PROPERTY_LENGTH = "length";
    public static final String PROPERTY_MANDATORY = "mandatory";
    public static final String PROPERTY_DEFAULTVALUE = "defaultValue";
    public static final String PROPERTY_APPLICATIONELEMENT = "applicationElement";
    public static final String PROPERTY_FIXED = "fixed";
    public static final String PROPERTY_FIXEDVALUE = "fixedValue";
    public static final String PROPERTY_OBKMOWIDGETCLASS = "obkmoWidgetClass";
    public static final String PROPERTY_EVALUATEFIXEDVALUE = "evaluateFixedValue";
    public static final String PROPERTY_OBUIAPPPROCESS = "obuiappProcess";
    public static final String PROPERTY_STARTINNEWLINE = "startinnewline";
    public static final String PROPERTY_DISPLAYLOGIC = "displayLogic";
    public static final String PROPERTY_VALIDATION = "validation";
    public static final String PROPERTY_FIELDGROUP = "fieldGroup";
    public static final String PROPERTY_READONLYLOGIC = "readOnlyLogic";
    public static final String PROPERTY_OBUIAPPPARAMETERTRLLIST = "oBUIAPPParameterTrlList";
    public static final String PROPERTY_OBUIAPPPARAMETERVALUELIST = "oBUIAPPParameterValueList";

    public Parameter() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_CENTRALMAINTENANCE, true);
        setDefaultValue(PROPERTY_MANDATORY, false);
        setDefaultValue(PROPERTY_FIXED, false);
        setDefaultValue(PROPERTY_EVALUATEFIXEDVALUE, false);
        setDefaultValue(PROPERTY_STARTINNEWLINE, false);
        setDefaultValue(PROPERTY_OBUIAPPPARAMETERTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPPARAMETERVALUELIST, new ArrayList<Object>());
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

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
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

    public String getDBColumnName() {
        return (String) get(PROPERTY_DBCOLUMNNAME);
    }

    public void setDBColumnName(String dBColumnName) {
        set(PROPERTY_DBCOLUMNNAME, dBColumnName);
    }

    public Boolean isCentralMaintenance() {
        return (Boolean) get(PROPERTY_CENTRALMAINTENANCE);
    }

    public void setCentralMaintenance(Boolean centralMaintenance) {
        set(PROPERTY_CENTRALMAINTENANCE, centralMaintenance);
    }

    public Long getLength() {
        return (Long) get(PROPERTY_LENGTH);
    }

    public void setLength(Long length) {
        set(PROPERTY_LENGTH, length);
    }

    public Boolean isMandatory() {
        return (Boolean) get(PROPERTY_MANDATORY);
    }

    public void setMandatory(Boolean mandatory) {
        set(PROPERTY_MANDATORY, mandatory);
    }

    public String getDefaultValue() {
        return (String) get(PROPERTY_DEFAULTVALUE);
    }

    public void setDefaultValue(String defaultValue) {
        set(PROPERTY_DEFAULTVALUE, defaultValue);
    }

    public Element getApplicationElement() {
        return (Element) get(PROPERTY_APPLICATIONELEMENT);
    }

    public void setApplicationElement(Element applicationElement) {
        set(PROPERTY_APPLICATIONELEMENT, applicationElement);
    }

    public Boolean isFixed() {
        return (Boolean) get(PROPERTY_FIXED);
    }

    public void setFixed(Boolean fixed) {
        set(PROPERTY_FIXED, fixed);
    }

    public String getFixedValue() {
        return (String) get(PROPERTY_FIXEDVALUE);
    }

    public void setFixedValue(String fixedValue) {
        set(PROPERTY_FIXEDVALUE, fixedValue);
    }

    public WidgetClass getObkmoWidgetClass() {
        return (WidgetClass) get(PROPERTY_OBKMOWIDGETCLASS);
    }

    public void setObkmoWidgetClass(WidgetClass obkmoWidgetClass) {
        set(PROPERTY_OBKMOWIDGETCLASS, obkmoWidgetClass);
    }

    public Boolean isEvaluateFixedValue() {
        return (Boolean) get(PROPERTY_EVALUATEFIXEDVALUE);
    }

    public void setEvaluateFixedValue(Boolean evaluateFixedValue) {
        set(PROPERTY_EVALUATEFIXEDVALUE, evaluateFixedValue);
    }

    public Process getObuiappProcess() {
        return (Process) get(PROPERTY_OBUIAPPPROCESS);
    }

    public void setObuiappProcess(Process obuiappProcess) {
        set(PROPERTY_OBUIAPPPROCESS, obuiappProcess);
    }

    public Boolean isStartinnewline() {
        return (Boolean) get(PROPERTY_STARTINNEWLINE);
    }

    public void setStartinnewline(Boolean startinnewline) {
        set(PROPERTY_STARTINNEWLINE, startinnewline);
    }

    public String getDisplayLogic() {
        return (String) get(PROPERTY_DISPLAYLOGIC);
    }

    public void setDisplayLogic(String displayLogic) {
        set(PROPERTY_DISPLAYLOGIC, displayLogic);
    }

    public Validation getValidation() {
        return (Validation) get(PROPERTY_VALIDATION);
    }

    public void setValidation(Validation validation) {
        set(PROPERTY_VALIDATION, validation);
    }

    public FieldGroup getFieldGroup() {
        return (FieldGroup) get(PROPERTY_FIELDGROUP);
    }

    public void setFieldGroup(FieldGroup fieldGroup) {
        set(PROPERTY_FIELDGROUP, fieldGroup);
    }

    public String getReadOnlyLogic() {
        return (String) get(PROPERTY_READONLYLOGIC);
    }

    public void setReadOnlyLogic(String readOnlyLogic) {
        set(PROPERTY_READONLYLOGIC, readOnlyLogic);
    }

    @SuppressWarnings("unchecked")
    public List<ParameterTrl> getOBUIAPPParameterTrlList() {
        return (List<ParameterTrl>) get(PROPERTY_OBUIAPPPARAMETERTRLLIST);
    }

    public void setOBUIAPPParameterTrlList(List<ParameterTrl> oBUIAPPParameterTrlList) {
        set(PROPERTY_OBUIAPPPARAMETERTRLLIST, oBUIAPPParameterTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ParameterValue> getOBUIAPPParameterValueList() {
        return (List<ParameterValue>) get(PROPERTY_OBUIAPPPARAMETERVALUELIST);
    }

    public void setOBUIAPPParameterValueList(List<ParameterValue> oBUIAPPParameterValueList) {
        set(PROPERTY_OBUIAPPPARAMETERVALUELIST, oBUIAPPParameterValueList);
    }

}
