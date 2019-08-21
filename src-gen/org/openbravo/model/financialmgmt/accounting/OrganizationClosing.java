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
package org.openbravo.model.financialmgmt.accounting;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationAcctSchema;
import org.openbravo.model.financialmgmt.calendar.Year;
/**
 * Entity class for entity OrganizationClosing (stored in table AD_Org_Closing).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OrganizationClosing extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Org_Closing";
    public static final String ENTITY_NAME = "OrganizationClosing";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_YEAR = "year";
    public static final String PROPERTY_ORGACCTSCHEMA = "orgAcctschema";
    public static final String PROPERTY_REGULARIZATIONFACTACCTGROUP = "regularizationFactAcctGroup";
    public static final String PROPERTY_CLOSINGFACTACCTGROUP = "closingFactAcctGroup";
    public static final String PROPERTY_DIVIDEUPFACTACCTGROUP = "divideupFactAcctGroup";
    public static final String PROPERTY_OPENFACTACCTGROUP = "openFactAcctGroup";

    public OrganizationClosing() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
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

    public Year getYear() {
        return (Year) get(PROPERTY_YEAR);
    }

    public void setYear(Year year) {
        set(PROPERTY_YEAR, year);
    }

    public OrganizationAcctSchema getOrgAcctschema() {
        return (OrganizationAcctSchema) get(PROPERTY_ORGACCTSCHEMA);
    }

    public void setOrgAcctschema(OrganizationAcctSchema orgAcctschema) {
        set(PROPERTY_ORGACCTSCHEMA, orgAcctschema);
    }

    public String getRegularizationFactAcctGroup() {
        return (String) get(PROPERTY_REGULARIZATIONFACTACCTGROUP);
    }

    public void setRegularizationFactAcctGroup(String regularizationFactAcctGroup) {
        set(PROPERTY_REGULARIZATIONFACTACCTGROUP, regularizationFactAcctGroup);
    }

    public String getClosingFactAcctGroup() {
        return (String) get(PROPERTY_CLOSINGFACTACCTGROUP);
    }

    public void setClosingFactAcctGroup(String closingFactAcctGroup) {
        set(PROPERTY_CLOSINGFACTACCTGROUP, closingFactAcctGroup);
    }

    public String getDivideupFactAcctGroup() {
        return (String) get(PROPERTY_DIVIDEUPFACTACCTGROUP);
    }

    public void setDivideupFactAcctGroup(String divideupFactAcctGroup) {
        set(PROPERTY_DIVIDEUPFACTACCTGROUP, divideupFactAcctGroup);
    }

    public String getOpenFactAcctGroup() {
        return (String) get(PROPERTY_OPENFACTACCTGROUP);
    }

    public void setOpenFactAcctGroup(String openFactAcctGroup) {
        set(PROPERTY_OPENFACTACCTGROUP, openFactAcctGroup);
    }

}
