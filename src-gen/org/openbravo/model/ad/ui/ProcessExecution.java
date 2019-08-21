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
 * Entity class for entity ProcessExecution (stored in table AD_Process_Execution_V).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProcessExecution extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Process_Execution_V";
    public static final String ENTITY_NAME = "ProcessExecution";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_STARTTIME = "startTime";
    public static final String PROPERTY_ENDTIME = "endTime";
    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_PROCESSLOG = "processLog";
    public static final String PROPERTY_RESULT = "result";
    public static final String PROPERTY_PARAMS = "params";
    public static final String PROPERTY_REPORT = "report";
    public static final String PROPERTY_CHANNEL = "channel";
    public static final String PROPERTY_SECURITYBASEDONROLE = "securityBasedOnRole";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PROCESSREQUEST = "processRequest";

    public ProcessExecution() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_RESULT, false);
        setDefaultValue(PROPERTY_SECURITYBASEDONROLE, false);
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

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public Date getStartTime() {
        return (Date) get(PROPERTY_STARTTIME);
    }

    public void setStartTime(Date startTime) {
        set(PROPERTY_STARTTIME, startTime);
    }

    public Date getEndTime() {
        return (Date) get(PROPERTY_ENDTIME);
    }

    public void setEndTime(Date endTime) {
        set(PROPERTY_ENDTIME, endTime);
    }

    public String getDuration() {
        return (String) get(PROPERTY_DURATION);
    }

    public void setDuration(String duration) {
        set(PROPERTY_DURATION, duration);
    }

    public String getProcessLog() {
        return (String) get(PROPERTY_PROCESSLOG);
    }

    public void setProcessLog(String processLog) {
        set(PROPERTY_PROCESSLOG, processLog);
    }

    public Boolean isResult() {
        return (Boolean) get(PROPERTY_RESULT);
    }

    public void setResult(Boolean result) {
        set(PROPERTY_RESULT, result);
    }

    public String getParams() {
        return (String) get(PROPERTY_PARAMS);
    }

    public void setParams(String params) {
        set(PROPERTY_PARAMS, params);
    }

    public String getReport() {
        return (String) get(PROPERTY_REPORT);
    }

    public void setReport(String report) {
        set(PROPERTY_REPORT, report);
    }

    public String getChannel() {
        return (String) get(PROPERTY_CHANNEL);
    }

    public void setChannel(String channel) {
        set(PROPERTY_CHANNEL, channel);
    }

    public Boolean isSecurityBasedOnRole() {
        return (Boolean) get(PROPERTY_SECURITYBASEDONROLE);
    }

    public void setSecurityBasedOnRole(Boolean securityBasedOnRole) {
        set(PROPERTY_SECURITYBASEDONROLE, securityBasedOnRole);
    }

    public Process getProcess() {
        return (Process) get(PROPERTY_PROCESS);
    }

    public void setProcess(Process process) {
        set(PROPERTY_PROCESS, process);
    }

    public ProcessRequest getProcessRequest() {
        return (ProcessRequest) get(PROPERTY_PROCESSREQUEST);
    }

    public void setProcessRequest(ProcessRequest processRequest) {
        set(PROPERTY_PROCESSREQUEST, processRequest);
    }

}
