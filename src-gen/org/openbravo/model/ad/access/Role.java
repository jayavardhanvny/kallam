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
package org.openbravo.model.ad.access;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.application.NavbarRoleaccess;
import org.openbravo.client.application.UIPersonalization;
import org.openbravo.client.application.ViewRoleAccess;
import org.openbravo.client.myob.WidgetClassAccess;
import org.openbravo.client.myob.WidgetInstance;
import org.openbravo.model.ad.alert.Alert;
import org.openbravo.model.ad.alert.AlertRecipient;
import org.openbravo.model.ad.domain.Preference;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.utility.Tree;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.service.integration.google.OBSEIGDefaults;
/**
 * Entity class for entity ADRole (stored in table AD_Role).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Role extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Role";
    public static final String ENTITY_NAME = "ADRole";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_USERLEVEL = "userLevel";
    public static final String PROPERTY_CLIENTLIST = "clientList";
    public static final String PROPERTY_ORGANIZATIONLIST = "organizationList";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_APPROVALAMOUNT = "approvalAmount";
    public static final String PROPERTY_PRIMARYTREEMENU = "primaryTreeMenu";
    public static final String PROPERTY_MANUAL = "manual";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_CLIENTADMIN = "clientAdmin";
    public static final String PROPERTY_ADVANCED = "advanced";
    public static final String PROPERTY_ISRESTRICTBACKEND = "isrestrictbackend";
    public static final String PROPERTY_FORPORTALUSERS = "forPortalUsers";
    public static final String PROPERTY_PORTALADMIN = "portalAdmin";
    public static final String PROPERTY_ADALERTLIST = "aDAlertList";
    public static final String PROPERTY_ADALERTRECIPIENTLIST = "aDAlertRecipientList";
    public static final String PROPERTY_ADFORMACCESSLIST = "aDFormAccessList";
    public static final String PROPERTY_ADPREFERENCEVISIBLEATROLELIST = "aDPreferenceVisibleAtRoleList";
    public static final String PROPERTY_ADPROCESSACCESSLIST = "aDProcessAccessList";
    public static final String PROPERTY_ADROLEORGANIZATIONLIST = "aDRoleOrganizationList";
    public static final String PROPERTY_ADTABLEACCESSLIST = "aDTableAccessList";
    public static final String PROPERTY_ADUSERDEFAULTROLELIST = "aDUserDefaultRoleList";
    public static final String PROPERTY_ADUSERROLESLIST = "aDUserRolesList";
    public static final String PROPERTY_ADWINDOWACCESSLIST = "aDWindowAccessList";
    public static final String PROPERTY_OBKMOWIDGETCLASSACCESSLIST = "oBKMOWidgetClassAccessList";
    public static final String PROPERTY_OBKMOWIDGETINSTANCEVISIBLEATROLELIST = "oBKMOWidgetInstanceVisibleAtRoleList";
    public static final String PROPERTY_OBSEIGDEFAULTSLIST = "oBSEIGDefaultsList";
    public static final String PROPERTY_OBUIAPPNAVBARROLEACCESSLIST = "oBUIAPPNavbarRoleaccessList";
    public static final String PROPERTY_OBUIAPPPROCESSACCESSLIST = "oBUIAPPProcessAccessList";
    public static final String PROPERTY_OBUIAPPUIPERSONALIZATIONVISIBLEATROLELIST = "oBUIAPPUIPersonalizationVisibleAtRoleList";
    public static final String PROPERTY_OBUIAPPVIEWROLEACCESSLIST = "obuiappViewRoleAccessList";

    public Role() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MANUAL, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_CLIENTADMIN, false);
        setDefaultValue(PROPERTY_ADVANCED, true);
        setDefaultValue(PROPERTY_ISRESTRICTBACKEND, false);
        setDefaultValue(PROPERTY_FORPORTALUSERS, false);
        setDefaultValue(PROPERTY_PORTALADMIN, false);
        setDefaultValue(PROPERTY_ADALERTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADALERTRECIPIENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADFORMACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPREFERENCEVISIBLEATROLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADROLEORGANIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLEACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADUSERDEFAULTROLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADUSERROLESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADWINDOWACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETCLASSACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETINSTANCEVISIBLEATROLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBSEIGDEFAULTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPNAVBARROLEACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPPROCESSACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPUIPERSONALIZATIONVISIBLEATROLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPVIEWROLEACCESSLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getUserLevel() {
        return (String) get(PROPERTY_USERLEVEL);
    }

    public void setUserLevel(String userLevel) {
        set(PROPERTY_USERLEVEL, userLevel);
    }

    public String getClientList() {
        return (String) get(PROPERTY_CLIENTLIST);
    }

    public void setClientList(String clientList) {
        set(PROPERTY_CLIENTLIST, clientList);
    }

    public String getOrganizationList() {
        return (String) get(PROPERTY_ORGANIZATIONLIST);
    }

    public void setOrganizationList(String organizationList) {
        set(PROPERTY_ORGANIZATIONLIST, organizationList);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BigDecimal getApprovalAmount() {
        return (BigDecimal) get(PROPERTY_APPROVALAMOUNT);
    }

    public void setApprovalAmount(BigDecimal approvalAmount) {
        set(PROPERTY_APPROVALAMOUNT, approvalAmount);
    }

    public Tree getPrimaryTreeMenu() {
        return (Tree) get(PROPERTY_PRIMARYTREEMENU);
    }

    public void setPrimaryTreeMenu(Tree primaryTreeMenu) {
        set(PROPERTY_PRIMARYTREEMENU, primaryTreeMenu);
    }

    public Boolean isManual() {
        return (Boolean) get(PROPERTY_MANUAL);
    }

    public void setManual(Boolean manual) {
        set(PROPERTY_MANUAL, manual);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isClientAdmin() {
        return (Boolean) get(PROPERTY_CLIENTADMIN);
    }

    public void setClientAdmin(Boolean clientAdmin) {
        set(PROPERTY_CLIENTADMIN, clientAdmin);
    }

    public Boolean isAdvanced() {
        return (Boolean) get(PROPERTY_ADVANCED);
    }

    public void setAdvanced(Boolean advanced) {
        set(PROPERTY_ADVANCED, advanced);
    }

    public Boolean isRestrictbackend() {
        return (Boolean) get(PROPERTY_ISRESTRICTBACKEND);
    }

    public void setRestrictbackend(Boolean isrestrictbackend) {
        set(PROPERTY_ISRESTRICTBACKEND, isrestrictbackend);
    }

    public Boolean isForPortalUsers() {
        return (Boolean) get(PROPERTY_FORPORTALUSERS);
    }

    public void setForPortalUsers(Boolean forPortalUsers) {
        set(PROPERTY_FORPORTALUSERS, forPortalUsers);
    }

    public Boolean isPortalAdmin() {
        return (Boolean) get(PROPERTY_PORTALADMIN);
    }

    public void setPortalAdmin(Boolean portalAdmin) {
        set(PROPERTY_PORTALADMIN, portalAdmin);
    }

    @SuppressWarnings("unchecked")
    public List<Alert> getADAlertList() {
        return (List<Alert>) get(PROPERTY_ADALERTLIST);
    }

    public void setADAlertList(List<Alert> aDAlertList) {
        set(PROPERTY_ADALERTLIST, aDAlertList);
    }

    @SuppressWarnings("unchecked")
    public List<AlertRecipient> getADAlertRecipientList() {
        return (List<AlertRecipient>) get(PROPERTY_ADALERTRECIPIENTLIST);
    }

    public void setADAlertRecipientList(List<AlertRecipient> aDAlertRecipientList) {
        set(PROPERTY_ADALERTRECIPIENTLIST, aDAlertRecipientList);
    }

    @SuppressWarnings("unchecked")
    public List<FormAccess> getADFormAccessList() {
        return (List<FormAccess>) get(PROPERTY_ADFORMACCESSLIST);
    }

    public void setADFormAccessList(List<FormAccess> aDFormAccessList) {
        set(PROPERTY_ADFORMACCESSLIST, aDFormAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<Preference> getADPreferenceVisibleAtRoleList() {
        return (List<Preference>) get(PROPERTY_ADPREFERENCEVISIBLEATROLELIST);
    }

    public void setADPreferenceVisibleAtRoleList(List<Preference> aDPreferenceVisibleAtRoleList) {
        set(PROPERTY_ADPREFERENCEVISIBLEATROLELIST, aDPreferenceVisibleAtRoleList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessAccess> getADProcessAccessList() {
        return (List<ProcessAccess>) get(PROPERTY_ADPROCESSACCESSLIST);
    }

    public void setADProcessAccessList(List<ProcessAccess> aDProcessAccessList) {
        set(PROPERTY_ADPROCESSACCESSLIST, aDProcessAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<RoleOrganization> getADRoleOrganizationList() {
        return (List<RoleOrganization>) get(PROPERTY_ADROLEORGANIZATIONLIST);
    }

    public void setADRoleOrganizationList(List<RoleOrganization> aDRoleOrganizationList) {
        set(PROPERTY_ADROLEORGANIZATIONLIST, aDRoleOrganizationList);
    }

    @SuppressWarnings("unchecked")
    public List<TableAccess> getADTableAccessList() {
        return (List<TableAccess>) get(PROPERTY_ADTABLEACCESSLIST);
    }

    public void setADTableAccessList(List<TableAccess> aDTableAccessList) {
        set(PROPERTY_ADTABLEACCESSLIST, aDTableAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<User> getADUserDefaultRoleList() {
        return (List<User>) get(PROPERTY_ADUSERDEFAULTROLELIST);
    }

    public void setADUserDefaultRoleList(List<User> aDUserDefaultRoleList) {
        set(PROPERTY_ADUSERDEFAULTROLELIST, aDUserDefaultRoleList);
    }

    @SuppressWarnings("unchecked")
    public List<UserRoles> getADUserRolesList() {
        return (List<UserRoles>) get(PROPERTY_ADUSERROLESLIST);
    }

    public void setADUserRolesList(List<UserRoles> aDUserRolesList) {
        set(PROPERTY_ADUSERROLESLIST, aDUserRolesList);
    }

    @SuppressWarnings("unchecked")
    public List<WindowAccess> getADWindowAccessList() {
        return (List<WindowAccess>) get(PROPERTY_ADWINDOWACCESSLIST);
    }

    public void setADWindowAccessList(List<WindowAccess> aDWindowAccessList) {
        set(PROPERTY_ADWINDOWACCESSLIST, aDWindowAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetClassAccess> getOBKMOWidgetClassAccessList() {
        return (List<WidgetClassAccess>) get(PROPERTY_OBKMOWIDGETCLASSACCESSLIST);
    }

    public void setOBKMOWidgetClassAccessList(List<WidgetClassAccess> oBKMOWidgetClassAccessList) {
        set(PROPERTY_OBKMOWIDGETCLASSACCESSLIST, oBKMOWidgetClassAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetInstance> getOBKMOWidgetInstanceVisibleAtRoleList() {
        return (List<WidgetInstance>) get(PROPERTY_OBKMOWIDGETINSTANCEVISIBLEATROLELIST);
    }

    public void setOBKMOWidgetInstanceVisibleAtRoleList(List<WidgetInstance> oBKMOWidgetInstanceVisibleAtRoleList) {
        set(PROPERTY_OBKMOWIDGETINSTANCEVISIBLEATROLELIST, oBKMOWidgetInstanceVisibleAtRoleList);
    }

    @SuppressWarnings("unchecked")
    public List<OBSEIGDefaults> getOBSEIGDefaultsList() {
        return (List<OBSEIGDefaults>) get(PROPERTY_OBSEIGDEFAULTSLIST);
    }

    public void setOBSEIGDefaultsList(List<OBSEIGDefaults> oBSEIGDefaultsList) {
        set(PROPERTY_OBSEIGDEFAULTSLIST, oBSEIGDefaultsList);
    }

    @SuppressWarnings("unchecked")
    public List<NavbarRoleaccess> getOBUIAPPNavbarRoleaccessList() {
        return (List<NavbarRoleaccess>) get(PROPERTY_OBUIAPPNAVBARROLEACCESSLIST);
    }

    public void setOBUIAPPNavbarRoleaccessList(List<NavbarRoleaccess> oBUIAPPNavbarRoleaccessList) {
        set(PROPERTY_OBUIAPPNAVBARROLEACCESSLIST, oBUIAPPNavbarRoleaccessList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.client.application.ProcessAccess> getOBUIAPPProcessAccessList() {
        return (List<org.openbravo.client.application.ProcessAccess>) get(PROPERTY_OBUIAPPPROCESSACCESSLIST);
    }

    public void setOBUIAPPProcessAccessList(List<org.openbravo.client.application.ProcessAccess> oBUIAPPProcessAccessList) {
        set(PROPERTY_OBUIAPPPROCESSACCESSLIST, oBUIAPPProcessAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<UIPersonalization> getOBUIAPPUIPersonalizationVisibleAtRoleList() {
        return (List<UIPersonalization>) get(PROPERTY_OBUIAPPUIPERSONALIZATIONVISIBLEATROLELIST);
    }

    public void setOBUIAPPUIPersonalizationVisibleAtRoleList(List<UIPersonalization> oBUIAPPUIPersonalizationVisibleAtRoleList) {
        set(PROPERTY_OBUIAPPUIPERSONALIZATIONVISIBLEATROLELIST, oBUIAPPUIPersonalizationVisibleAtRoleList);
    }

    @SuppressWarnings("unchecked")
    public List<ViewRoleAccess> getObuiappViewRoleAccessList() {
        return (List<ViewRoleAccess>) get(PROPERTY_OBUIAPPVIEWROLEACCESSLIST);
    }

    public void setObuiappViewRoleAccessList(List<ViewRoleAccess> obuiappViewRoleAccessList) {
        set(PROPERTY_OBUIAPPVIEWROLEACCESSLIST, obuiappViewRoleAccessList);
    }

}
