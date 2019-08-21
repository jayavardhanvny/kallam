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
package org.openbravo.model.ad.process;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADParameter (stored in table AD_PInstance_Para).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Parameter extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_PInstance_Para";
    public static final String ENTITY_NAME = "ADParameter";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_PROCESSINSTANCE = "processInstance";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_PARAMETERNAME = "parameterName";
    public static final String PROPERTY_STRING = "string";
    public static final String PROPERTY_STRINGTO = "stringTo";
    public static final String PROPERTY_PROCESSNUMBER = "processNumber";
    public static final String PROPERTY_PROCESSNUMBERTO = "processNumberTo";
    public static final String PROPERTY_PROCESSDATE = "processDate";
    public static final String PROPERTY_PROCESSDATETO = "processDateTo";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_INFOTO = "infoTo";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";

    public Parameter() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public ProcessInstance getProcessInstance() {
        return (ProcessInstance) get(PROPERTY_PROCESSINSTANCE);
    }

    public void setProcessInstance(ProcessInstance processInstance) {
        set(PROPERTY_PROCESSINSTANCE, processInstance);
    }

    public String getSequenceNumber() {
        return (String) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(String sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public String getParameterName() {
        return (String) get(PROPERTY_PARAMETERNAME);
    }

    public void setParameterName(String parameterName) {
        set(PROPERTY_PARAMETERNAME, parameterName);
    }

    public String getString() {
        return (String) get(PROPERTY_STRING);
    }

    public void setString(String string) {
        set(PROPERTY_STRING, string);
    }

    public String getStringTo() {
        return (String) get(PROPERTY_STRINGTO);
    }

    public void setStringTo(String stringTo) {
        set(PROPERTY_STRINGTO, stringTo);
    }

    public BigDecimal getProcessNumber() {
        return (BigDecimal) get(PROPERTY_PROCESSNUMBER);
    }

    public void setProcessNumber(BigDecimal processNumber) {
        set(PROPERTY_PROCESSNUMBER, processNumber);
    }

    public BigDecimal getProcessNumberTo() {
        return (BigDecimal) get(PROPERTY_PROCESSNUMBERTO);
    }

    public void setProcessNumberTo(BigDecimal processNumberTo) {
        set(PROPERTY_PROCESSNUMBERTO, processNumberTo);
    }

    public Date getProcessDate() {
        return (Date) get(PROPERTY_PROCESSDATE);
    }

    public void setProcessDate(Date processDate) {
        set(PROPERTY_PROCESSDATE, processDate);
    }

    public Date getProcessDateTo() {
        return (Date) get(PROPERTY_PROCESSDATETO);
    }

    public void setProcessDateTo(Date processDateTo) {
        set(PROPERTY_PROCESSDATETO, processDateTo);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public String getInfoTo() {
        return (String) get(PROPERTY_INFOTO);
    }

    public void setInfoTo(String infoTo) {
        set(PROPERTY_INFOTO, infoTo);
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

}
