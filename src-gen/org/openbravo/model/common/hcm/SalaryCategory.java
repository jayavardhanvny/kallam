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
package org.openbravo.model.common.hcm;

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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.EmployeeSalaryCategory;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.manufacturing.cost.CostcenterEmployee;
import org.openbravo.model.manufacturing.processplan.OperationEmployee;
import org.openbravo.model.manufacturing.transaction.ProductionRunEmployee;
/**
 * Entity class for entity SalaryCategory (stored in table C_Salary_Category).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class SalaryCategory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Salary_Category";
    public static final String ENTITY_NAME = "SalaryCategory";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_COSTAPPLIED = "costApplied";
    public static final String PROPERTY_BUSINESSPARTNERLIST = "businessPartnerList";
    public static final String PROPERTY_EMPLOYEESALARYCATEGORYLIST = "employeeSalaryCategoryList";
    public static final String PROPERTY_MANUFACTURINGCOSTCENTEREMPLOYEELIST = "manufacturingCostcenterEmployeeList";
    public static final String PROPERTY_MANUFACTURINGOPERATIONEMPLOYEELIST = "manufacturingOperationEmployeeList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST = "manufacturingProductionRunEmployeeList";
    public static final String PROPERTY_SALARYCATEGORYCOSTLIST = "salaryCategoryCostList";

    public SalaryCategory() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_COSTAPPLIED, true);
        setDefaultValue(PROPERTY_BUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLOYEESALARYCATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGCOSTCENTEREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALARYCATEGORYCOSTLIST, new ArrayList<Object>());
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Boolean isCostApplied() {
        return (Boolean) get(PROPERTY_COSTAPPLIED);
    }

    public void setCostApplied(Boolean costApplied) {
        set(PROPERTY_COSTAPPLIED, costApplied);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERLIST);
    }

    public void setBusinessPartnerList(List<BusinessPartner> businessPartnerList) {
        set(PROPERTY_BUSINESSPARTNERLIST, businessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeSalaryCategory> getEmployeeSalaryCategoryList() {
        return (List<EmployeeSalaryCategory>) get(PROPERTY_EMPLOYEESALARYCATEGORYLIST);
    }

    public void setEmployeeSalaryCategoryList(List<EmployeeSalaryCategory> employeeSalaryCategoryList) {
        set(PROPERTY_EMPLOYEESALARYCATEGORYLIST, employeeSalaryCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<CostcenterEmployee> getManufacturingCostcenterEmployeeList() {
        return (List<CostcenterEmployee>) get(PROPERTY_MANUFACTURINGCOSTCENTEREMPLOYEELIST);
    }

    public void setManufacturingCostcenterEmployeeList(List<CostcenterEmployee> manufacturingCostcenterEmployeeList) {
        set(PROPERTY_MANUFACTURINGCOSTCENTEREMPLOYEELIST, manufacturingCostcenterEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<OperationEmployee> getManufacturingOperationEmployeeList() {
        return (List<OperationEmployee>) get(PROPERTY_MANUFACTURINGOPERATIONEMPLOYEELIST);
    }

    public void setManufacturingOperationEmployeeList(List<OperationEmployee> manufacturingOperationEmployeeList) {
        set(PROPERTY_MANUFACTURINGOPERATIONEMPLOYEELIST, manufacturingOperationEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunEmployee> getManufacturingProductionRunEmployeeList() {
        return (List<ProductionRunEmployee>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST);
    }

    public void setManufacturingProductionRunEmployeeList(List<ProductionRunEmployee> manufacturingProductionRunEmployeeList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST, manufacturingProductionRunEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<SalaryCategoryCost> getSalaryCategoryCostList() {
        return (List<SalaryCategoryCost>) get(PROPERTY_SALARYCATEGORYCOSTLIST);
    }

    public void setSalaryCategoryCostList(List<SalaryCategoryCost> salaryCategoryCostList) {
        set(PROPERTY_SALARYCATEGORYCOSTLIST, salaryCategoryCostList);
    }

}
