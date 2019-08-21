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

import com.rcss.humanresource.data.RCHR_Settlemntlines;

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
 * Entity class for entity RCPL_PayDeducitionsHead (stored in table RCPL_PayDeducitionsHead).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPL_PayDeducitionsHead extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_PayDeducitionsHead";
    public static final String ENTITY_NAME = "RCPL_PayDeducitionsHead";
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
    public static final String PROPERTY_CATEGORY = "category";
    public static final String PROPERTY_RCHRSETTLEMNTLINESLIST = "rCHRSettlemntlinesList";
    public static final String PROPERTY_RCPLEMPPAYDEADLIST = "rCPLEmpPayDeadList";
    public static final String PROPERTY_RCPLPAYROLLTEMPLATELINESLIST = "rCPLPayrollTemplateLinesList";

    public RCPL_PayDeducitionsHead() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCHRSETTLEMNTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPAYDEADLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLTEMPLATELINESLIST, new ArrayList<Object>());
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

    public String getCategory() {
        return (String) get(PROPERTY_CATEGORY);
    }

    public void setCategory(String category) {
        set(PROPERTY_CATEGORY, category);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Settlemntlines> getRCHRSettlemntlinesList() {
        return (List<RCHR_Settlemntlines>) get(PROPERTY_RCHRSETTLEMNTLINESLIST);
    }

    public void setRCHRSettlemntlinesList(List<RCHR_Settlemntlines> rCHRSettlemntlinesList) {
        set(PROPERTY_RCHRSETTLEMNTLINESLIST, rCHRSettlemntlinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpPayDead> getRCPLEmpPayDeadList() {
        return (List<RCPL_EmpPayDead>) get(PROPERTY_RCPLEMPPAYDEADLIST);
    }

    public void setRCPLEmpPayDeadList(List<RCPL_EmpPayDead> rCPLEmpPayDeadList) {
        set(PROPERTY_RCPLEMPPAYDEADLIST, rCPLEmpPayDeadList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_PayrollTemplateLines> getRCPLPayrollTemplateLinesList() {
        return (List<RCPL_PayrollTemplateLines>) get(PROPERTY_RCPLPAYROLLTEMPLATELINESLIST);
    }

    public void setRCPLPayrollTemplateLinesList(List<RCPL_PayrollTemplateLines> rCPLPayrollTemplateLinesList) {
        set(PROPERTY_RCPLPAYROLLTEMPLATELINESLIST, rCPLPayrollTemplateLinesList);
    }

}
