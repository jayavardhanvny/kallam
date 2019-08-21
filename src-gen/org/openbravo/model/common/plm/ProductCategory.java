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
package org.openbravo.model.common.plm;

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
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLineV2;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.financialmgmt.assetmgmt.AssetGroup;
import org.openbravo.model.materialmgmt.cost.CostingRule;
import org.openbravo.model.materialmgmt.cost.CostingRuleProductV;
import org.openbravo.model.materialmgmt.transaction.MaterialTransactionV;
import org.openbravo.model.mrp.ProductionRun;
import org.openbravo.model.mrp.PurchasingRun;
import org.openbravo.model.pos.ExternalPOSCategory;
import org.openbravo.model.pricing.pricelist.PriceListSchemeLine;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.project.ProjectLine;
import org.openbravo.model.sales.CommissionLine;
import org.openbravo.model.timeandexpense.ExpenseType;
import org.openbravo.model.timeandexpense.ResourceType;
/**
 * Entity class for entity ProductCategory (stored in table M_Product_Category).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProductCategory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Product_Category";
    public static final String ENTITY_NAME = "ProductCategory";
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
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_PLANNEDMARGIN = "plannedMargin";
    public static final String PROPERTY_ASSETCATEGORY = "assetCategory";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_EPCGISRAWMATERIAL = "epcgIsrawmaterial";
    public static final String PROPERTY_COSTINGRULELIST = "costingRuleList";
    public static final String PROPERTY_COSTINGRULEPRODUCTVLIST = "costingRuleProductVList";
    public static final String PROPERTY_EXPENSETYPELIST = "expenseTypeList";
    public static final String PROPERTY_EXTERNALPOSCATEGORYLIST = "externalPOSCategoryList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_INVOICELINEV2LIST = "invoiceLineV2List";
    public static final String PROPERTY_MRPPRODUCTIONRUNLIST = "mRPProductionRunList";
    public static final String PROPERTY_MRPPURCHASINGRUNLIST = "mRPPurchasingRunList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_PRICINGADJUSTMENTPRODUCTCATEGORYLIST = "pricingAdjustmentProductCategoryList";
    public static final String PROPERTY_PRICINGPRICELISTSCHEMELINELIST = "pricingPriceListSchemeLineList";
    public static final String PROPERTY_PRICINGVOLUMEDISCOUNTPRODUCTCATEGORYLIST = "pricingVolumeDiscountProductCategoryList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLIST = "procurementRequisitionList";
    public static final String PROPERTY_PRODUCTLIST = "productList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSLIST = "productCategoryAccountsList";
    public static final String PROPERTY_PROJECTLINELIST = "projectLineList";
    public static final String PROPERTY_RESOURCETYPELIST = "resourceTypeList";
    public static final String PROPERTY_SALESCOMMISSIONLINELIST = "salesCommissionLineList";
    public static final String PROPERTY_TRANSACTIONVLIST = "transactionVList";

    public ProductCategory() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_EPCGISRAWMATERIAL, false);
        setDefaultValue(PROPERTY_COSTINGRULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COSTINGRULEPRODUCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXPENSETYPELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSCATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTPRODUCTCATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGPRICELISTSCHEMELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGVOLUMEDISCOUNTPRODUCTCATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESOURCETYPELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TRANSACTIONVLIST, new ArrayList<Object>());
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

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public BigDecimal getPlannedMargin() {
        return (BigDecimal) get(PROPERTY_PLANNEDMARGIN);
    }

    public void setPlannedMargin(BigDecimal plannedMargin) {
        set(PROPERTY_PLANNEDMARGIN, plannedMargin);
    }

    public AssetGroup getAssetCategory() {
        return (AssetGroup) get(PROPERTY_ASSETCATEGORY);
    }

    public void setAssetCategory(AssetGroup assetCategory) {
        set(PROPERTY_ASSETCATEGORY, assetCategory);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public Boolean isEpcgIsrawmaterial() {
        return (Boolean) get(PROPERTY_EPCGISRAWMATERIAL);
    }

    public void setEpcgIsrawmaterial(Boolean epcgIsrawmaterial) {
        set(PROPERTY_EPCGISRAWMATERIAL, epcgIsrawmaterial);
    }

    @SuppressWarnings("unchecked")
    public List<CostingRule> getCostingRuleList() {
        return (List<CostingRule>) get(PROPERTY_COSTINGRULELIST);
    }

    public void setCostingRuleList(List<CostingRule> costingRuleList) {
        set(PROPERTY_COSTINGRULELIST, costingRuleList);
    }

    @SuppressWarnings("unchecked")
    public List<CostingRuleProductV> getCostingRuleProductVList() {
        return (List<CostingRuleProductV>) get(PROPERTY_COSTINGRULEPRODUCTVLIST);
    }

    public void setCostingRuleProductVList(List<CostingRuleProductV> costingRuleProductVList) {
        set(PROPERTY_COSTINGRULEPRODUCTVLIST, costingRuleProductVList);
    }

    @SuppressWarnings("unchecked")
    public List<ExpenseType> getExpenseTypeList() {
        return (List<ExpenseType>) get(PROPERTY_EXPENSETYPELIST);
    }

    public void setExpenseTypeList(List<ExpenseType> expenseTypeList) {
        set(PROPERTY_EXPENSETYPELIST, expenseTypeList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOSCategory> getExternalPOSCategoryList() {
        return (List<ExternalPOSCategory>) get(PROPERTY_EXTERNALPOSCATEGORYLIST);
    }

    public void setExternalPOSCategoryList(List<ExternalPOSCategory> externalPOSCategoryList) {
        set(PROPERTY_EXTERNALPOSCATEGORYLIST, externalPOSCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getFinancialMgmtBudgetLineList() {
        return (List<BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineV2> getInvoiceLineV2List() {
        return (List<InvoiceLineV2>) get(PROPERTY_INVOICELINEV2LIST);
    }

    public void setInvoiceLineV2List(List<InvoiceLineV2> invoiceLineV2List) {
        set(PROPERTY_INVOICELINEV2LIST, invoiceLineV2List);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRun> getMRPProductionRunList() {
        return (List<ProductionRun>) get(PROPERTY_MRPPRODUCTIONRUNLIST);
    }

    public void setMRPProductionRunList(List<ProductionRun> mRPProductionRunList) {
        set(PROPERTY_MRPPRODUCTIONRUNLIST, mRPProductionRunList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRun> getMRPPurchasingRunList() {
        return (List<PurchasingRun>) get(PROPERTY_MRPPURCHASINGRUNLIST);
    }

    public void setMRPPurchasingRunList(List<PurchasingRun> mRPPurchasingRunList) {
        set(PROPERTY_MRPPURCHASINGRUNLIST, mRPPurchasingRunList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.priceadjustment.ProductCategory> getPricingAdjustmentProductCategoryList() {
        return (List<org.openbravo.model.pricing.priceadjustment.ProductCategory>) get(PROPERTY_PRICINGADJUSTMENTPRODUCTCATEGORYLIST);
    }

    public void setPricingAdjustmentProductCategoryList(List<org.openbravo.model.pricing.priceadjustment.ProductCategory> pricingAdjustmentProductCategoryList) {
        set(PROPERTY_PRICINGADJUSTMENTPRODUCTCATEGORYLIST, pricingAdjustmentProductCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<PriceListSchemeLine> getPricingPriceListSchemeLineList() {
        return (List<PriceListSchemeLine>) get(PROPERTY_PRICINGPRICELISTSCHEMELINELIST);
    }

    public void setPricingPriceListSchemeLineList(List<PriceListSchemeLine> pricingPriceListSchemeLineList) {
        set(PROPERTY_PRICINGPRICELISTSCHEMELINELIST, pricingPriceListSchemeLineList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.volumediscount.ProductCategory> getPricingVolumeDiscountProductCategoryList() {
        return (List<org.openbravo.model.pricing.volumediscount.ProductCategory>) get(PROPERTY_PRICINGVOLUMEDISCOUNTPRODUCTCATEGORYLIST);
    }

    public void setPricingVolumeDiscountProductCategoryList(List<org.openbravo.model.pricing.volumediscount.ProductCategory> pricingVolumeDiscountProductCategoryList) {
        set(PROPERTY_PRICINGVOLUMEDISCOUNTPRODUCTCATEGORYLIST, pricingVolumeDiscountProductCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<Requisition> getProcurementRequisitionList() {
        return (List<Requisition>) get(PROPERTY_PROCUREMENTREQUISITIONLIST);
    }

    public void setProcurementRequisitionList(List<Requisition> procurementRequisitionList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLIST, procurementRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProductList() {
        return (List<Product>) get(PROPERTY_PRODUCTLIST);
    }

    public void setProductList(List<Product> productList) {
        set(PROPERTY_PRODUCTLIST, productList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getProductCategoryAccountsList() {
        return (List<CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSLIST);
    }

    public void setProductCategoryAccountsList(List<CategoryAccounts> productCategoryAccountsList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSLIST, productCategoryAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectLine> getProjectLineList() {
        return (List<ProjectLine>) get(PROPERTY_PROJECTLINELIST);
    }

    public void setProjectLineList(List<ProjectLine> projectLineList) {
        set(PROPERTY_PROJECTLINELIST, projectLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ResourceType> getResourceTypeList() {
        return (List<ResourceType>) get(PROPERTY_RESOURCETYPELIST);
    }

    public void setResourceTypeList(List<ResourceType> resourceTypeList) {
        set(PROPERTY_RESOURCETYPELIST, resourceTypeList);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionLine> getSalesCommissionLineList() {
        return (List<CommissionLine>) get(PROPERTY_SALESCOMMISSIONLINELIST);
    }

    public void setSalesCommissionLineList(List<CommissionLine> salesCommissionLineList) {
        set(PROPERTY_SALESCOMMISSIONLINELIST, salesCommissionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransactionV> getTransactionVList() {
        return (List<MaterialTransactionV>) get(PROPERTY_TRANSACTIONVLIST);
    }

    public void setTransactionVList(List<MaterialTransactionV> transactionVList) {
        set(PROPERTY_TRANSACTIONVLIST, transactionVList);
    }

}
