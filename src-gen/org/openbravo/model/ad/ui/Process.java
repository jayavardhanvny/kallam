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
import org.openbravo.model.ad.access.ProcessAccess;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.domain.ModelImplementation;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.process.ProcessInstance;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADProcess (stored in table AD_Process).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Process extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Process";
    public static final String ENTITY_NAME = "ADProcess";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_DATAACCESSLEVEL = "dataAccessLevel";
    public static final String PROPERTY_USERCANSTARTPROCESS = "userCanStartProcess";
    public static final String PROPERTY_PROCEDURE = "procedure";
    public static final String PROPERTY_REPORT = "report";
    public static final String PROPERTY_DIRECTPRINT = "directPrint";
    public static final String PROPERTY_JAVACLASSNAME = "javaClassName";
    public static final String PROPERTY_BACKGROUND = "background";
    public static final String PROPERTY_JASPERREPORT = "jasperReport";
    public static final String PROPERTY_JRTEMPLATENAME = "jRTemplateName";
    public static final String PROPERTY_SERVICETYPE = "serviceType";
    public static final String PROPERTY_EXTERNALSERVICE = "externalService";
    public static final String PROPERTY_SERVICESOURCE = "serviceSource";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_UIPATTERN = "uIPattern";
    public static final String PROPERTY_ADVANCEDFEATURE = "advancedFeature";
    public static final String PROPERTY_PREVENTCONCURRENTEXECUTIONS = "preventConcurrentExecutions";
    public static final String PROPERTY_ADCOLUMNLIST = "aDColumnList";
    public static final String PROPERTY_ADMENULIST = "aDMenuList";
    public static final String PROPERTY_ADMODELIMPLEMENTATIONLIST = "aDModelImplementationList";
    public static final String PROPERTY_ADPROCESSACCESSLIST = "aDProcessAccessList";
    public static final String PROPERTY_ADPROCESSINSTANCELIST = "aDProcessInstanceList";
    public static final String PROPERTY_ADPROCESSPARAMETERLIST = "aDProcessParameterList";
    public static final String PROPERTY_ADPROCESSSCHEDULINGLIST = "aDProcessSchedulingList";
    public static final String PROPERTY_ADPROCESSTRLLIST = "aDProcessTrlList";
    public static final String PROPERTY_ADTABLIST = "aDTabList";
    public static final String PROPERTY_PROCESSEXECUTIONLIST = "processExecutionList";
    public static final String PROPERTY_PROCESSREQUESTLIST = "processRequestList";

    public Process() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_USERCANSTARTPROCESS, false);
        setDefaultValue(PROPERTY_REPORT, false);
        setDefaultValue(PROPERTY_DIRECTPRINT, false);
        setDefaultValue(PROPERTY_BACKGROUND, false);
        setDefaultValue(PROPERTY_JASPERREPORT, false);
        setDefaultValue(PROPERTY_EXTERNALSERVICE, false);
        setDefaultValue(PROPERTY_UIPATTERN, "S");
        setDefaultValue(PROPERTY_ADVANCEDFEATURE, false);
        setDefaultValue(PROPERTY_PREVENTCONCURRENTEXECUTIONS, false);
        setDefaultValue(PROPERTY_ADCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMENULIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMODELIMPLEMENTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSINSTANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSPARAMETERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSSCHEDULINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCESSEXECUTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCESSREQUESTLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
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

    public String getDataAccessLevel() {
        return (String) get(PROPERTY_DATAACCESSLEVEL);
    }

    public void setDataAccessLevel(String dataAccessLevel) {
        set(PROPERTY_DATAACCESSLEVEL, dataAccessLevel);
    }

    public Boolean isUserCanStartProcess() {
        return (Boolean) get(PROPERTY_USERCANSTARTPROCESS);
    }

    public void setUserCanStartProcess(Boolean userCanStartProcess) {
        set(PROPERTY_USERCANSTARTPROCESS, userCanStartProcess);
    }

    public String getProcedure() {
        return (String) get(PROPERTY_PROCEDURE);
    }

    public void setProcedure(String procedure) {
        set(PROPERTY_PROCEDURE, procedure);
    }

    public Boolean isReport() {
        return (Boolean) get(PROPERTY_REPORT);
    }

    public void setReport(Boolean report) {
        set(PROPERTY_REPORT, report);
    }

    public Boolean isDirectPrint() {
        return (Boolean) get(PROPERTY_DIRECTPRINT);
    }

    public void setDirectPrint(Boolean directPrint) {
        set(PROPERTY_DIRECTPRINT, directPrint);
    }

    public String getJavaClassName() {
        return (String) get(PROPERTY_JAVACLASSNAME);
    }

    public void setJavaClassName(String javaClassName) {
        set(PROPERTY_JAVACLASSNAME, javaClassName);
    }

    public Boolean isBackground() {
        return (Boolean) get(PROPERTY_BACKGROUND);
    }

    public void setBackground(Boolean background) {
        set(PROPERTY_BACKGROUND, background);
    }

    public Boolean isJasperReport() {
        return (Boolean) get(PROPERTY_JASPERREPORT);
    }

    public void setJasperReport(Boolean jasperReport) {
        set(PROPERTY_JASPERREPORT, jasperReport);
    }

    public String getJRTemplateName() {
        return (String) get(PROPERTY_JRTEMPLATENAME);
    }

    public void setJRTemplateName(String jRTemplateName) {
        set(PROPERTY_JRTEMPLATENAME, jRTemplateName);
    }

    public String getServiceType() {
        return (String) get(PROPERTY_SERVICETYPE);
    }

    public void setServiceType(String serviceType) {
        set(PROPERTY_SERVICETYPE, serviceType);
    }

    public Boolean isExternalService() {
        return (Boolean) get(PROPERTY_EXTERNALSERVICE);
    }

    public void setExternalService(Boolean externalService) {
        set(PROPERTY_EXTERNALSERVICE, externalService);
    }

    public String getServiceSource() {
        return (String) get(PROPERTY_SERVICESOURCE);
    }

    public void setServiceSource(String serviceSource) {
        set(PROPERTY_SERVICESOURCE, serviceSource);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public String getUIPattern() {
        return (String) get(PROPERTY_UIPATTERN);
    }

    public void setUIPattern(String uIPattern) {
        set(PROPERTY_UIPATTERN, uIPattern);
    }

    public Boolean isAdvancedFeature() {
        return (Boolean) get(PROPERTY_ADVANCEDFEATURE);
    }

    public void setAdvancedFeature(Boolean advancedFeature) {
        set(PROPERTY_ADVANCEDFEATURE, advancedFeature);
    }

    public Boolean isPreventConcurrentExecutions() {
        return (Boolean) get(PROPERTY_PREVENTCONCURRENTEXECUTIONS);
    }

    public void setPreventConcurrentExecutions(Boolean preventConcurrentExecutions) {
        set(PROPERTY_PREVENTCONCURRENTEXECUTIONS, preventConcurrentExecutions);
    }

    @SuppressWarnings("unchecked")
    public List<Column> getADColumnList() {
        return (List<Column>) get(PROPERTY_ADCOLUMNLIST);
    }

    public void setADColumnList(List<Column> aDColumnList) {
        set(PROPERTY_ADCOLUMNLIST, aDColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<Menu> getADMenuList() {
        return (List<Menu>) get(PROPERTY_ADMENULIST);
    }

    public void setADMenuList(List<Menu> aDMenuList) {
        set(PROPERTY_ADMENULIST, aDMenuList);
    }

    @SuppressWarnings("unchecked")
    public List<ModelImplementation> getADModelImplementationList() {
        return (List<ModelImplementation>) get(PROPERTY_ADMODELIMPLEMENTATIONLIST);
    }

    public void setADModelImplementationList(List<ModelImplementation> aDModelImplementationList) {
        set(PROPERTY_ADMODELIMPLEMENTATIONLIST, aDModelImplementationList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessAccess> getADProcessAccessList() {
        return (List<ProcessAccess>) get(PROPERTY_ADPROCESSACCESSLIST);
    }

    public void setADProcessAccessList(List<ProcessAccess> aDProcessAccessList) {
        set(PROPERTY_ADPROCESSACCESSLIST, aDProcessAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessInstance> getADProcessInstanceList() {
        return (List<ProcessInstance>) get(PROPERTY_ADPROCESSINSTANCELIST);
    }

    public void setADProcessInstanceList(List<ProcessInstance> aDProcessInstanceList) {
        set(PROPERTY_ADPROCESSINSTANCELIST, aDProcessInstanceList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessParameter> getADProcessParameterList() {
        return (List<ProcessParameter>) get(PROPERTY_ADPROCESSPARAMETERLIST);
    }

    public void setADProcessParameterList(List<ProcessParameter> aDProcessParameterList) {
        set(PROPERTY_ADPROCESSPARAMETERLIST, aDProcessParameterList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessScheduling> getADProcessSchedulingList() {
        return (List<ProcessScheduling>) get(PROPERTY_ADPROCESSSCHEDULINGLIST);
    }

    public void setADProcessSchedulingList(List<ProcessScheduling> aDProcessSchedulingList) {
        set(PROPERTY_ADPROCESSSCHEDULINGLIST, aDProcessSchedulingList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessTrl> getADProcessTrlList() {
        return (List<ProcessTrl>) get(PROPERTY_ADPROCESSTRLLIST);
    }

    public void setADProcessTrlList(List<ProcessTrl> aDProcessTrlList) {
        set(PROPERTY_ADPROCESSTRLLIST, aDProcessTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabList() {
        return (List<Tab>) get(PROPERTY_ADTABLIST);
    }

    public void setADTabList(List<Tab> aDTabList) {
        set(PROPERTY_ADTABLIST, aDTabList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessExecution> getProcessExecutionList() {
        return (List<ProcessExecution>) get(PROPERTY_PROCESSEXECUTIONLIST);
    }

    public void setProcessExecutionList(List<ProcessExecution> processExecutionList) {
        set(PROPERTY_PROCESSEXECUTIONLIST, processExecutionList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessRequest> getProcessRequestList() {
        return (List<ProcessRequest>) get(PROPERTY_PROCESSREQUESTLIST);
    }

    public void setProcessRequestList(List<ProcessRequest> processRequestList) {
        set(PROPERTY_PROCESSREQUESTLIST, processRequestList);
    }

}
