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
package com.rcss.humanresource.data;

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
 * Entity class for entity RCHR_Inspremarktype (stored in table RCHR_Inspremarktype).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRInspremarktype extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Inspremarktype";
    public static final String ENTITY_NAME = "RCHR_Inspremarktype";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_VALIDATIONCODE = "validationCode";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_ISSIZING = "issizing";
    public static final String PROPERTY_ISYARNDYIED = "isyarndyied";
    public static final String PROPERTY_ISLASHING = "islashing";
    public static final String PROPERTY_ISDYING = "isdying";
    public static final String PROPERTY_RCHRINSPECTIONTYPELIST = "rCHRInspectiontypeList";

    public RCHRInspremarktype() {
        setDefaultValue(PROPERTY_ACTIVE, false);
        setDefaultValue(PROPERTY_ISSIZING, false);
        setDefaultValue(PROPERTY_ISYARNDYIED, false);
        setDefaultValue(PROPERTY_ISLASHING, false);
        setDefaultValue(PROPERTY_ISDYING, false);
        setDefaultValue(PROPERTY_RCHRINSPECTIONTYPELIST, new ArrayList<Object>());
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

    public String getValidationCode() {
        return (String) get(PROPERTY_VALIDATIONCODE);
    }

    public void setValidationCode(String validationCode) {
        set(PROPERTY_VALIDATIONCODE, validationCode);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Boolean isSizing() {
        return (Boolean) get(PROPERTY_ISSIZING);
    }

    public void setSizing(Boolean issizing) {
        set(PROPERTY_ISSIZING, issizing);
    }

    public Boolean isYarndyied() {
        return (Boolean) get(PROPERTY_ISYARNDYIED);
    }

    public void setYarndyied(Boolean isyarndyied) {
        set(PROPERTY_ISYARNDYIED, isyarndyied);
    }

    public Boolean isLashing() {
        return (Boolean) get(PROPERTY_ISLASHING);
    }

    public void setLashing(Boolean islashing) {
        set(PROPERTY_ISLASHING, islashing);
    }

    public Boolean isDying() {
        return (Boolean) get(PROPERTY_ISDYING);
    }

    public void setDying(Boolean isdying) {
        set(PROPERTY_ISDYING, isdying);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRInspectiontype> getRCHRInspectiontypeList() {
        return (List<RCHRInspectiontype>) get(PROPERTY_RCHRINSPECTIONTYPELIST);
    }

    public void setRCHRInspectiontypeList(List<RCHRInspectiontype> rCHRInspectiontypeList) {
        set(PROPERTY_RCHRINSPECTIONTYPELIST, rCHRInspectiontypeList);
    }

}
