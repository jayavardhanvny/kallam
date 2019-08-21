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
package com.redcarpet.payroll.data;

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
 * Entity class for entity RCPL_EfficiencyLine (stored in table RCPL_EfficiencyLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPLEfficiencyLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_EfficiencyLine";
    public static final String ENTITY_NAME = "RCPL_EfficiencyLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCPLLOOMCATEGORY = "rcplLoomcategory";
    public static final String PROPERTY_MINLOOMS = "minlooms";
    public static final String PROPERTY_NETUNITPRICE = "netUnitPrice";
    public static final String PROPERTY_RCPLEFFICIENCY = "rcplEfficiency";
    public static final String PROPERTY_DYEDEFFICEINCYPRICE = "dyedefficeincyprice";
    public static final String PROPERTY_DYEDMINLOOMS = "dyedminlooms";

    public RCPLEfficiencyLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MINLOOMS, (long) 0);
        setDefaultValue(PROPERTY_NETUNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_DYEDEFFICEINCYPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_DYEDMINLOOMS, (long) 0);
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

    public RCPLLoomCategory getRcplLoomcategory() {
        return (RCPLLoomCategory) get(PROPERTY_RCPLLOOMCATEGORY);
    }

    public void setRcplLoomcategory(RCPLLoomCategory rcplLoomcategory) {
        set(PROPERTY_RCPLLOOMCATEGORY, rcplLoomcategory);
    }

    public Long getMinlooms() {
        return (Long) get(PROPERTY_MINLOOMS);
    }

    public void setMinlooms(Long minlooms) {
        set(PROPERTY_MINLOOMS, minlooms);
    }

    public BigDecimal getNetUnitPrice() {
        return (BigDecimal) get(PROPERTY_NETUNITPRICE);
    }

    public void setNetUnitPrice(BigDecimal netUnitPrice) {
        set(PROPERTY_NETUNITPRICE, netUnitPrice);
    }

    public RCPLEfficiency getRcplEfficiency() {
        return (RCPLEfficiency) get(PROPERTY_RCPLEFFICIENCY);
    }

    public void setRcplEfficiency(RCPLEfficiency rcplEfficiency) {
        set(PROPERTY_RCPLEFFICIENCY, rcplEfficiency);
    }

    public BigDecimal getDyedefficeincyprice() {
        return (BigDecimal) get(PROPERTY_DYEDEFFICEINCYPRICE);
    }

    public void setDyedefficeincyprice(BigDecimal dyedefficeincyprice) {
        set(PROPERTY_DYEDEFFICEINCYPRICE, dyedefficeincyprice);
    }

    public Long getDyedminlooms() {
        return (Long) get(PROPERTY_DYEDMINLOOMS);
    }

    public void setDyedminlooms(Long dyedminlooms) {
        set(PROPERTY_DYEDMINLOOMS, dyedminlooms);
    }

}