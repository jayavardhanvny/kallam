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
package org.openbravo.model.manufacturing.cost;

import java.math.BigDecimal;
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
import org.openbravo.model.manufacturing.floorshop.Machine;
import org.openbravo.model.manufacturing.transaction.Activity;
/**
 * Entity class for entity ManufacturingCostCenter (stored in table MA_CostCenter).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CostCenter extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_CostCenter";
    public static final String ENTITY_NAME = "ManufacturingCostCenter";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_COST = "cost";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_BYDEFAULT = "byDefault";
    public static final String PROPERTY_CALCULATED = "calculated";
    public static final String PROPERTY_COSTUOM = "costUOM";
    public static final String PROPERTY_MANUFACTURINGACTIVITYLIST = "manufacturingActivityList";
    public static final String PROPERTY_MANUFACTURINGCOSTCENTERVERSIONLIST = "manufacturingCostcenterVersionList";
    public static final String PROPERTY_MANUFACTURINGMACHINELIST = "manufacturingMachineList";

    public CostCenter() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BYDEFAULT, false);
        setDefaultValue(PROPERTY_CALCULATED, false);
        setDefaultValue(PROPERTY_MANUFACTURINGACTIVITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGCOSTCENTERVERSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMACHINELIST, new ArrayList<Object>());
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

    public BigDecimal getCost() {
        return (BigDecimal) get(PROPERTY_COST);
    }

    public void setCost(BigDecimal cost) {
        set(PROPERTY_COST, cost);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public Boolean isByDefault() {
        return (Boolean) get(PROPERTY_BYDEFAULT);
    }

    public void setByDefault(Boolean byDefault) {
        set(PROPERTY_BYDEFAULT, byDefault);
    }

    public Boolean isCalculated() {
        return (Boolean) get(PROPERTY_CALCULATED);
    }

    public void setCalculated(Boolean calculated) {
        set(PROPERTY_CALCULATED, calculated);
    }

    public String getCostUOM() {
        return (String) get(PROPERTY_COSTUOM);
    }

    public void setCostUOM(String costUOM) {
        set(PROPERTY_COSTUOM, costUOM);
    }

    @SuppressWarnings("unchecked")
    public List<Activity> getManufacturingActivityList() {
        return (List<Activity>) get(PROPERTY_MANUFACTURINGACTIVITYLIST);
    }

    public void setManufacturingActivityList(List<Activity> manufacturingActivityList) {
        set(PROPERTY_MANUFACTURINGACTIVITYLIST, manufacturingActivityList);
    }

    @SuppressWarnings("unchecked")
    public List<CostcenterVersion> getManufacturingCostcenterVersionList() {
        return (List<CostcenterVersion>) get(PROPERTY_MANUFACTURINGCOSTCENTERVERSIONLIST);
    }

    public void setManufacturingCostcenterVersionList(List<CostcenterVersion> manufacturingCostcenterVersionList) {
        set(PROPERTY_MANUFACTURINGCOSTCENTERVERSIONLIST, manufacturingCostcenterVersionList);
    }

    @SuppressWarnings("unchecked")
    public List<Machine> getManufacturingMachineList() {
        return (List<Machine>) get(PROPERTY_MANUFACTURINGMACHINELIST);
    }

    public void setManufacturingMachineList(List<Machine> manufacturingMachineList) {
        set(PROPERTY_MANUFACTURINGMACHINELIST, manufacturingMachineList);
    }

}
