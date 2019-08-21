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
package org.openbravo.model.financialmgmt.tax;

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
import org.openbravo.model.financialmgmt.gl.GLCharge;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.timeandexpense.ExpenseType;
import org.openbravo.model.timeandexpense.ResourceType;
/**
 * Entity class for entity FinancialMgmtTaxCategory (stored in table C_TaxCategory).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class TaxCategory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_TaxCategory";
    public static final String ENTITY_NAME = "FinancialMgmtTaxCategory";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_EXPENSETYPELIST = "expenseTypeList";
    public static final String PROPERTY_FINANCIALMGMTGLCHARGELIST = "financialMgmtGLChargeList";
    public static final String PROPERTY_FINANCIALMGMTGLITEMLIST = "financialMgmtGLItemList";
    public static final String PROPERTY_FINANCIALMGMTTAXCATEGORYTRLLIST = "financialMgmtTaxCategoryTrlList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATELIST = "financialMgmtTaxRateList";
    public static final String PROPERTY_PRODUCTLIST = "productList";
    public static final String PROPERTY_RESOURCETYPELIST = "resourceTypeList";

    public TaxCategory() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_EXPENSETYPELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLCHARGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLITEMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXCATEGORYTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESOURCETYPELIST, new ArrayList<Object>());
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    @SuppressWarnings("unchecked")
    public List<ExpenseType> getExpenseTypeList() {
        return (List<ExpenseType>) get(PROPERTY_EXPENSETYPELIST);
    }

    public void setExpenseTypeList(List<ExpenseType> expenseTypeList) {
        set(PROPERTY_EXPENSETYPELIST, expenseTypeList);
    }

    @SuppressWarnings("unchecked")
    public List<GLCharge> getFinancialMgmtGLChargeList() {
        return (List<GLCharge>) get(PROPERTY_FINANCIALMGMTGLCHARGELIST);
    }

    public void setFinancialMgmtGLChargeList(List<GLCharge> financialMgmtGLChargeList) {
        set(PROPERTY_FINANCIALMGMTGLCHARGELIST, financialMgmtGLChargeList);
    }

    @SuppressWarnings("unchecked")
    public List<GLItem> getFinancialMgmtGLItemList() {
        return (List<GLItem>) get(PROPERTY_FINANCIALMGMTGLITEMLIST);
    }

    public void setFinancialMgmtGLItemList(List<GLItem> financialMgmtGLItemList) {
        set(PROPERTY_FINANCIALMGMTGLITEMLIST, financialMgmtGLItemList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxCategoryTrl> getFinancialMgmtTaxCategoryTrlList() {
        return (List<TaxCategoryTrl>) get(PROPERTY_FINANCIALMGMTTAXCATEGORYTRLLIST);
    }

    public void setFinancialMgmtTaxCategoryTrlList(List<TaxCategoryTrl> financialMgmtTaxCategoryTrlList) {
        set(PROPERTY_FINANCIALMGMTTAXCATEGORYTRLLIST, financialMgmtTaxCategoryTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRate> getFinancialMgmtTaxRateList() {
        return (List<TaxRate>) get(PROPERTY_FINANCIALMGMTTAXRATELIST);
    }

    public void setFinancialMgmtTaxRateList(List<TaxRate> financialMgmtTaxRateList) {
        set(PROPERTY_FINANCIALMGMTTAXRATELIST, financialMgmtTaxRateList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProductList() {
        return (List<Product>) get(PROPERTY_PRODUCTLIST);
    }

    public void setProductList(List<Product> productList) {
        set(PROPERTY_PRODUCTLIST, productList);
    }

    @SuppressWarnings("unchecked")
    public List<ResourceType> getResourceTypeList() {
        return (List<ResourceType>) get(PROPERTY_RESOURCETYPELIST);
    }

    public void setResourceTypeList(List<ResourceType> resourceTypeList) {
        set(PROPERTY_RESOURCETYPELIST, resourceTypeList);
    }

}
