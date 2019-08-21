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
package org.openbravo.client.myob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity OBKMO_WidgetClassMenu (stored in table OBKMO_Widget_Class_Menu).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class WidgetClassMenu extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBKMO_Widget_Class_Menu";
    public static final String ENTITY_NAME = "OBKMO_WidgetClassMenu";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WIDGETCLASS = "widgetClass";
    public static final String PROPERTY_ISSEPARATOR = "isSeparator";
    public static final String PROPERTY_TITLE = "title";
    public static final String PROPERTY_ACTION = "action";
    public static final String PROPERTY_SEQUENCE = "sequence";
    public static final String PROPERTY_OBKMOWIDGETCLASSMENUTRLLIST = "oBKMOWidgetClassMenuTrlList";

    public WidgetClassMenu() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISSEPARATOR, false);
        setDefaultValue(PROPERTY_SEQUENCE, (long) 0);
        setDefaultValue(PROPERTY_OBKMOWIDGETCLASSMENUTRLLIST, new ArrayList<Object>());
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

    public WidgetClass getWidgetClass() {
        return (WidgetClass) get(PROPERTY_WIDGETCLASS);
    }

    public void setWidgetClass(WidgetClass widgetClass) {
        set(PROPERTY_WIDGETCLASS, widgetClass);
    }

    public Boolean isSeparator() {
        return (Boolean) get(PROPERTY_ISSEPARATOR);
    }

    public void setSeparator(Boolean isSeparator) {
        set(PROPERTY_ISSEPARATOR, isSeparator);
    }

    public String getTitle() {
        return (String) get(PROPERTY_TITLE);
    }

    public void setTitle(String title) {
        set(PROPERTY_TITLE, title);
    }

    public String getAction() {
        return (String) get(PROPERTY_ACTION);
    }

    public void setAction(String action) {
        set(PROPERTY_ACTION, action);
    }

    public Long getSequence() {
        return (Long) get(PROPERTY_SEQUENCE);
    }

    public void setSequence(Long sequence) {
        set(PROPERTY_SEQUENCE, sequence);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetClassMenuTrl> getOBKMOWidgetClassMenuTrlList() {
        return (List<WidgetClassMenuTrl>) get(PROPERTY_OBKMOWIDGETCLASSMENUTRLLIST);
    }

    public void setOBKMOWidgetClassMenuTrlList(List<WidgetClassMenuTrl> oBKMOWidgetClassMenuTrlList) {
        set(PROPERTY_OBKMOWIDGETCLASSMENUTRLLIST, oBKMOWidgetClassMenuTrlList);
    }

}
