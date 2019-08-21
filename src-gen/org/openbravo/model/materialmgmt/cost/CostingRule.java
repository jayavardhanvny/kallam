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
package org.openbravo.model.materialmgmt.cost;

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
import org.openbravo.model.common.plm.ProductCategory;
/**
 * Entity class for entity CostingRule (stored in table M_Costing_Rule).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CostingRule extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Costing_Rule";
    public static final String ENTITY_NAME = "CostingRule";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_COSTINGALGORITHM = "costingAlgorithm";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ORGANIZATIONDIMENSION = "organizationDimension";
    public static final String PROPERTY_WAREHOUSEDIMENSION = "warehouseDimension";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_VALIDATED = "validated";
    public static final String PROPERTY_PROCESSRULE = "processRule";
    public static final String PROPERTY_COSTINGRULEINITLIST = "costingRuleInitList";
    public static final String PROPERTY_COSTINGRULEPRODUCTVLIST = "costingRuleProductVList";

    public CostingRule() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ORGANIZATIONDIMENSION, false);
        setDefaultValue(PROPERTY_WAREHOUSEDIMENSION, false);
        setDefaultValue(PROPERTY_VALIDATED, false);
        setDefaultValue(PROPERTY_PROCESSRULE, false);
        setDefaultValue(PROPERTY_COSTINGRULEINITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COSTINGRULEPRODUCTVLIST, new ArrayList<Object>());
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public CostingAlgorithm getCostingAlgorithm() {
        return (CostingAlgorithm) get(PROPERTY_COSTINGALGORITHM);
    }

    public void setCostingAlgorithm(CostingAlgorithm costingAlgorithm) {
        set(PROPERTY_COSTINGALGORITHM, costingAlgorithm);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Boolean isOrganizationDimension() {
        return (Boolean) get(PROPERTY_ORGANIZATIONDIMENSION);
    }

    public void setOrganizationDimension(Boolean organizationDimension) {
        set(PROPERTY_ORGANIZATIONDIMENSION, organizationDimension);
    }

    public Boolean isWarehouseDimension() {
        return (Boolean) get(PROPERTY_WAREHOUSEDIMENSION);
    }

    public void setWarehouseDimension(Boolean warehouseDimension) {
        set(PROPERTY_WAREHOUSEDIMENSION, warehouseDimension);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public Boolean isValidated() {
        return (Boolean) get(PROPERTY_VALIDATED);
    }

    public void setValidated(Boolean validated) {
        set(PROPERTY_VALIDATED, validated);
    }

    public Boolean isProcessRule() {
        return (Boolean) get(PROPERTY_PROCESSRULE);
    }

    public void setProcessRule(Boolean processRule) {
        set(PROPERTY_PROCESSRULE, processRule);
    }

    @SuppressWarnings("unchecked")
    public List<CostingRuleInit> getCostingRuleInitList() {
        return (List<CostingRuleInit>) get(PROPERTY_COSTINGRULEINITLIST);
    }

    public void setCostingRuleInitList(List<CostingRuleInit> costingRuleInitList) {
        set(PROPERTY_COSTINGRULEINITLIST, costingRuleInitList);
    }

    @SuppressWarnings("unchecked")
    public List<CostingRuleProductV> getCostingRuleProductVList() {
        return (List<CostingRuleProductV>) get(PROPERTY_COSTINGRULEPRODUCTVLIST);
    }

    public void setCostingRuleProductVList(List<CostingRuleProductV> costingRuleProductVList) {
        set(PROPERTY_COSTINGRULEPRODUCTVLIST, costingRuleProductVList);
    }

}
