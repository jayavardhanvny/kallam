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
package com.redcarpet.production.data;

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
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity RCPR_MachineCategory (stored in table RCPR_MachineCategory).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPR_MachineCategory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_MachineCategory";
    public static final String ENTITY_NAME = "RCPR_MachineCategory";
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
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_RCPRMACHINELIST = "rCPRMachineList";
    public static final String PROPERTY_RCPRMACHINECATELEMAINTLIST = "rCPRMachineCatEleMaintList";
    public static final String PROPERTY_RCPRMACHINECATMECHMAINTLIST = "rCPRMachineCatMechMaintList";
    public static final String PROPERTY_RCPRMAINTENANCETASKLIST = "rCPRMaintenancetaskList";

    public RCPR_MachineCategory() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCPRMACHINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRMACHINECATELEMAINTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRMACHINECATMECHMAINTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRMAINTENANCETASKLIST, new ArrayList<Object>());
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

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    @SuppressWarnings("unchecked")
    public List<RCPRMachine> getRCPRMachineList() {
        return (List<RCPRMachine>) get(PROPERTY_RCPRMACHINELIST);
    }

    public void setRCPRMachineList(List<RCPRMachine> rCPRMachineList) {
        set(PROPERTY_RCPRMACHINELIST, rCPRMachineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_MachineCategory_Electrical_Maintenance> getRCPRMachineCatEleMaintList() {
        return (List<RCPR_MachineCategory_Electrical_Maintenance>) get(PROPERTY_RCPRMACHINECATELEMAINTLIST);
    }

    public void setRCPRMachineCatEleMaintList(List<RCPR_MachineCategory_Electrical_Maintenance> rCPRMachineCatEleMaintList) {
        set(PROPERTY_RCPRMACHINECATELEMAINTLIST, rCPRMachineCatEleMaintList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_MachineCategory_Mechanical_Maintenance> getRCPRMachineCatMechMaintList() {
        return (List<RCPR_MachineCategory_Mechanical_Maintenance>) get(PROPERTY_RCPRMACHINECATMECHMAINTLIST);
    }

    public void setRCPRMachineCatMechMaintList(List<RCPR_MachineCategory_Mechanical_Maintenance> rCPRMachineCatMechMaintList) {
        set(PROPERTY_RCPRMACHINECATMECHMAINTLIST, rCPRMachineCatMechMaintList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_MaintenanceTask> getRCPRMaintenancetaskList() {
        return (List<RCPR_MaintenanceTask>) get(PROPERTY_RCPRMAINTENANCETASKLIST);
    }

    public void setRCPRMaintenancetaskList(List<RCPR_MaintenanceTask> rCPRMaintenancetaskList) {
        set(PROPERTY_RCPRMAINTENANCETASKLIST, rCPRMaintenancetaskList);
    }

}
