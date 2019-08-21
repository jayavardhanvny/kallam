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

import com.rcss.humanresource.data.RchrApprovalTemplateDoc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.application.RefWindow;
import org.openbravo.client.application.UIPersonalization;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.access.WindowAccess;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.domain.Preference;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADWindow (stored in table AD_Window).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Window extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Window";
    public static final String ENTITY_NAME = "ADWindow";
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
    public static final String PROPERTY_WINDOWTYPE = "windowType";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_ISTHREADSAFE = "isthreadsafe";
    public static final String PROPERTY_ADVANCEDFEATURE = "advancedFeature";
    public static final String PROPERTY_ADMENULIST = "aDMenuList";
    public static final String PROPERTY_ADPREFERENCELIST = "aDPreferenceList";
    public static final String PROPERTY_ADTABLIST = "aDTabList";
    public static final String PROPERTY_ADTABLELIST = "aDTableList";
    public static final String PROPERTY_ADTABLEPOWINDOWLIST = "aDTablePOWindowList";
    public static final String PROPERTY_ADWINDOWACCESSLIST = "aDWindowAccessList";
    public static final String PROPERTY_ADWINDOWTRLLIST = "aDWindowTrlList";
    public static final String PROPERTY_OBUIAPPREFWINDOWLIST = "oBUIAPPRefWindowList";
    public static final String PROPERTY_OBUIAPPUIPERSONALIZATIONLIST = "oBUIAPPUIPersonalizationList";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST = "rCHRApprovalTemplateDocList";

    public Window() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_WINDOWTYPE, "M");
        setDefaultValue(PROPERTY_SALESTRANSACTION, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_ISTHREADSAFE, false);
        setDefaultValue(PROPERTY_ADVANCEDFEATURE, false);
        setDefaultValue(PROPERTY_ADMENULIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPREFERENCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLEPOWINDOWLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADWINDOWACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADWINDOWTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPREFWINDOWLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST, new ArrayList<Object>());
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

    public String getWindowType() {
        return (String) get(PROPERTY_WINDOWTYPE);
    }

    public void setWindowType(String windowType) {
        set(PROPERTY_WINDOWTYPE, windowType);
    }

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public Boolean isThreadsafe() {
        return (Boolean) get(PROPERTY_ISTHREADSAFE);
    }

    public void setThreadsafe(Boolean isthreadsafe) {
        set(PROPERTY_ISTHREADSAFE, isthreadsafe);
    }

    public Boolean isAdvancedFeature() {
        return (Boolean) get(PROPERTY_ADVANCEDFEATURE);
    }

    public void setAdvancedFeature(Boolean advancedFeature) {
        set(PROPERTY_ADVANCEDFEATURE, advancedFeature);
    }

    @SuppressWarnings("unchecked")
    public List<Menu> getADMenuList() {
        return (List<Menu>) get(PROPERTY_ADMENULIST);
    }

    public void setADMenuList(List<Menu> aDMenuList) {
        set(PROPERTY_ADMENULIST, aDMenuList);
    }

    @SuppressWarnings("unchecked")
    public List<Preference> getADPreferenceList() {
        return (List<Preference>) get(PROPERTY_ADPREFERENCELIST);
    }

    public void setADPreferenceList(List<Preference> aDPreferenceList) {
        set(PROPERTY_ADPREFERENCELIST, aDPreferenceList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabList() {
        return (List<Tab>) get(PROPERTY_ADTABLIST);
    }

    public void setADTabList(List<Tab> aDTabList) {
        set(PROPERTY_ADTABLIST, aDTabList);
    }

    @SuppressWarnings("unchecked")
    public List<Table> getADTableList() {
        return (List<Table>) get(PROPERTY_ADTABLELIST);
    }

    public void setADTableList(List<Table> aDTableList) {
        set(PROPERTY_ADTABLELIST, aDTableList);
    }

    @SuppressWarnings("unchecked")
    public List<Table> getADTablePOWindowList() {
        return (List<Table>) get(PROPERTY_ADTABLEPOWINDOWLIST);
    }

    public void setADTablePOWindowList(List<Table> aDTablePOWindowList) {
        set(PROPERTY_ADTABLEPOWINDOWLIST, aDTablePOWindowList);
    }

    @SuppressWarnings("unchecked")
    public List<WindowAccess> getADWindowAccessList() {
        return (List<WindowAccess>) get(PROPERTY_ADWINDOWACCESSLIST);
    }

    public void setADWindowAccessList(List<WindowAccess> aDWindowAccessList) {
        set(PROPERTY_ADWINDOWACCESSLIST, aDWindowAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<WindowTrl> getADWindowTrlList() {
        return (List<WindowTrl>) get(PROPERTY_ADWINDOWTRLLIST);
    }

    public void setADWindowTrlList(List<WindowTrl> aDWindowTrlList) {
        set(PROPERTY_ADWINDOWTRLLIST, aDWindowTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<RefWindow> getOBUIAPPRefWindowList() {
        return (List<RefWindow>) get(PROPERTY_OBUIAPPREFWINDOWLIST);
    }

    public void setOBUIAPPRefWindowList(List<RefWindow> oBUIAPPRefWindowList) {
        set(PROPERTY_OBUIAPPREFWINDOWLIST, oBUIAPPRefWindowList);
    }

    @SuppressWarnings("unchecked")
    public List<UIPersonalization> getOBUIAPPUIPersonalizationList() {
        return (List<UIPersonalization>) get(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST);
    }

    public void setOBUIAPPUIPersonalizationList(List<UIPersonalization> oBUIAPPUIPersonalizationList) {
        set(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST, oBUIAPPUIPersonalizationList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalTemplateDoc> getRCHRApprovalTemplateDocList() {
        return (List<RchrApprovalTemplateDoc>) get(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST);
    }

    public void setRCHRApprovalTemplateDocList(List<RchrApprovalTemplateDoc> rCHRApprovalTemplateDocList) {
        set(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST, rCHRApprovalTemplateDocList);
    }

}
