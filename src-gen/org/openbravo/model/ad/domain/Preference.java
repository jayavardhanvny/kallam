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
package org.openbravo.model.ad.domain;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.Role;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Window;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADPreference (stored in table AD_Preference).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Preference extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Preference";
    public static final String ENTITY_NAME = "ADPreference";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WINDOW = "window";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_ATTRIBUTE = "attribute";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_PROPERTY = "property";
    public static final String PROPERTY_PROPERTYLIST = "propertyList";
    public static final String PROPERTY_VISIBLEATCLIENT = "visibleAtClient";
    public static final String PROPERTY_VISIBLEATORGANIZATION = "visibleAtOrganization";
    public static final String PROPERTY_VISIBLEATROLE = "visibleAtRole";
    public static final String PROPERTY_SELECTED = "selected";
    public static final String PROPERTY_MODULE = "module";

    public Preference() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROPERTYLIST, true);
        setDefaultValue(PROPERTY_SELECTED, false);
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

    public Window getWindow() {
        return (Window) get(PROPERTY_WINDOW);
    }

    public void setWindow(Window window) {
        set(PROPERTY_WINDOW, window);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public String getAttribute() {
        return (String) get(PROPERTY_ATTRIBUTE);
    }

    public void setAttribute(String attribute) {
        set(PROPERTY_ATTRIBUTE, attribute);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getProperty() {
        return (String) get(PROPERTY_PROPERTY);
    }

    public void setProperty(String property) {
        set(PROPERTY_PROPERTY, property);
    }

    public Boolean isPropertyList() {
        return (Boolean) get(PROPERTY_PROPERTYLIST);
    }

    public void setPropertyList(Boolean propertyList) {
        set(PROPERTY_PROPERTYLIST, propertyList);
    }

    public Client getVisibleAtClient() {
        return (Client) get(PROPERTY_VISIBLEATCLIENT);
    }

    public void setVisibleAtClient(Client visibleAtClient) {
        set(PROPERTY_VISIBLEATCLIENT, visibleAtClient);
    }

    public Organization getVisibleAtOrganization() {
        return (Organization) get(PROPERTY_VISIBLEATORGANIZATION);
    }

    public void setVisibleAtOrganization(Organization visibleAtOrganization) {
        set(PROPERTY_VISIBLEATORGANIZATION, visibleAtOrganization);
    }

    public Role getVisibleAtRole() {
        return (Role) get(PROPERTY_VISIBLEATROLE);
    }

    public void setVisibleAtRole(Role visibleAtRole) {
        set(PROPERTY_VISIBLEATROLE, visibleAtRole);
    }

    public Boolean isSelected() {
        return (Boolean) get(PROPERTY_SELECTED);
    }

    public void setSelected(Boolean selected) {
        set(PROPERTY_SELECTED, selected);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

}
