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
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.domain.Reference;
import org.openbravo.model.ad.domain.Validation;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADProcessParameter (stored in table AD_Process_Para).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProcessParameter extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Process_Para";
    public static final String ENTITY_NAME = "ADProcessParameter";
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
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_REFERENCE = "reference";
    public static final String PROPERTY_REFERENCESEARCHKEY = "referenceSearchKey";
    public static final String PROPERTY_VALIDATION = "validation";
    public static final String PROPERTY_DBCOLUMNNAME = "dBColumnName";
    public static final String PROPERTY_CENTRALMAINTENANCE = "centralMaintenance";
    public static final String PROPERTY_LENGTH = "length";
    public static final String PROPERTY_MANDATORY = "mandatory";
    public static final String PROPERTY_RANGE = "range";
    public static final String PROPERTY_DEFAULTVALUE = "defaultValue";
    public static final String PROPERTY_DEFAULTLOGIC2 = "defaultLogic2";
    public static final String PROPERTY_VALUEFORMAT = "valueFormat";
    public static final String PROPERTY_MINVALUE = "minValue";
    public static final String PROPERTY_MAXVALUE = "maxValue";
    public static final String PROPERTY_APPLICATIONELEMENT = "applicationElement";
    public static final String PROPERTY_ADPROCESSPARAMETERTRLLIST = "aDProcessParameterTrlList";

    public ProcessParameter() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CENTRALMAINTENANCE, true);
        setDefaultValue(PROPERTY_MANDATORY, false);
        setDefaultValue(PROPERTY_RANGE, false);
        setDefaultValue(PROPERTY_ADPROCESSPARAMETERTRLLIST, new ArrayList<Object>());
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

    public Process getProcess() {
        return (Process) get(PROPERTY_PROCESS);
    }

    public void setProcess(Process process) {
        set(PROPERTY_PROCESS, process);
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

    public Validation getValidation() {
        return (Validation) get(PROPERTY_VALIDATION);
    }

    public void setValidation(Validation validation) {
        set(PROPERTY_VALIDATION, validation);
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

    public Boolean isRange() {
        return (Boolean) get(PROPERTY_RANGE);
    }

    public void setRange(Boolean range) {
        set(PROPERTY_RANGE, range);
    }

    public String getDefaultValue() {
        return (String) get(PROPERTY_DEFAULTVALUE);
    }

    public void setDefaultValue(String defaultValue) {
        set(PROPERTY_DEFAULTVALUE, defaultValue);
    }

    public String getDefaultLogic2() {
        return (String) get(PROPERTY_DEFAULTLOGIC2);
    }

    public void setDefaultLogic2(String defaultLogic2) {
        set(PROPERTY_DEFAULTLOGIC2, defaultLogic2);
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

    public Element getApplicationElement() {
        return (Element) get(PROPERTY_APPLICATIONELEMENT);
    }

    public void setApplicationElement(Element applicationElement) {
        set(PROPERTY_APPLICATIONELEMENT, applicationElement);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessParameterTrl> getADProcessParameterTrlList() {
        return (List<ProcessParameterTrl>) get(PROPERTY_ADPROCESSPARAMETERTRLLIST);
    }

    public void setADProcessParameterTrlList(List<ProcessParameterTrl> aDProcessParameterTrlList) {
        set(PROPERTY_ADPROCESSPARAMETERTRLLIST, aDProcessParameterTrlList);
    }

}
