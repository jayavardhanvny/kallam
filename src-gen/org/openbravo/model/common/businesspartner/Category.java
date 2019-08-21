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
package org.openbravo.model.common.businesspartner;

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
import org.openbravo.model.common.invoice.InvoiceLineV2;
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebtRun;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebtV;
import org.openbravo.model.mrp.ProductionRun;
import org.openbravo.model.mrp.PurchasingRun;
import org.openbravo.model.pricing.priceadjustment.BusinessPartnerGroup;
import org.openbravo.model.sales.CommissionLine;
/**
 * Entity class for entity BusinessPartnerCategory (stored in table C_BP_Group).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Category extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BP_Group";
    public static final String ENTITY_NAME = "BusinessPartnerCategory";
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
    public static final String PROPERTY_EPCGNEXTASSIGNNUMBER = "epcgNextassignnumber";
    public static final String PROPERTY_BUSINESSPARTNERLIST = "businessPartnerList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTLIST = "businessPartnerCategoryAccountList";
    public static final String PROPERTY_FINDOUBTFULDEBTRUNLIST = "fINDoubtfulDebtRunList";
    public static final String PROPERTY_FINDOUBTFULDEBTVLIST = "fINDoubtfulDebtVList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_INVOICELINEV2LIST = "invoiceLineV2List";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_MRPPRODUCTIONRUNLIST = "mRPProductionRunList";
    public static final String PROPERTY_MRPPURCHASINGRUNLIST = "mRPPurchasingRunList";
    public static final String PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERGROUPLIST = "pricingAdjustmentBusinessPartnerGroupList";
    public static final String PROPERTY_SALESCOMMISSIONLINELIST = "salesCommissionLineList";

    public Category() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERGROUPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONLINELIST, new ArrayList<Object>());
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

    public Long getEpcgNextassignnumber() {
        return (Long) get(PROPERTY_EPCGNEXTASSIGNNUMBER);
    }

    public void setEpcgNextassignnumber(Long epcgNextassignnumber) {
        set(PROPERTY_EPCGNEXTASSIGNNUMBER, epcgNextassignnumber);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERLIST);
    }

    public void setBusinessPartnerList(List<BusinessPartner> businessPartnerList) {
        set(PROPERTY_BUSINESSPARTNERLIST, businessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTLIST);
    }

    public void setBusinessPartnerCategoryAccountList(List<CategoryAccounts> businessPartnerCategoryAccountList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTLIST, businessPartnerCategoryAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtRun> getFINDoubtfulDebtRunList() {
        return (List<DoubtfulDebtRun>) get(PROPERTY_FINDOUBTFULDEBTRUNLIST);
    }

    public void setFINDoubtfulDebtRunList(List<DoubtfulDebtRun> fINDoubtfulDebtRunList) {
        set(PROPERTY_FINDOUBTFULDEBTRUNLIST, fINDoubtfulDebtRunList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtV> getFINDoubtfulDebtVList() {
        return (List<DoubtfulDebtV>) get(PROPERTY_FINDOUBTFULDEBTVLIST);
    }

    public void setFINDoubtfulDebtVList(List<DoubtfulDebtV> fINDoubtfulDebtVList) {
        set(PROPERTY_FINDOUBTFULDEBTVLIST, fINDoubtfulDebtVList);
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
    public List<InvoiceV2> getInvoiceV2List() {
        return (List<InvoiceV2>) get(PROPERTY_INVOICEV2LIST);
    }

    public void setInvoiceV2List(List<InvoiceV2> invoiceV2List) {
        set(PROPERTY_INVOICEV2LIST, invoiceV2List);
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
    public List<BusinessPartnerGroup> getPricingAdjustmentBusinessPartnerGroupList() {
        return (List<BusinessPartnerGroup>) get(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERGROUPLIST);
    }

    public void setPricingAdjustmentBusinessPartnerGroupList(List<BusinessPartnerGroup> pricingAdjustmentBusinessPartnerGroupList) {
        set(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERGROUPLIST, pricingAdjustmentBusinessPartnerGroupList);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionLine> getSalesCommissionLineList() {
        return (List<CommissionLine>) get(PROPERTY_SALESCOMMISSIONLINELIST);
    }

    public void setSalesCommissionLineList(List<CommissionLine> salesCommissionLineList) {
        set(PROPERTY_SALESCOMMISSIONLINELIST, salesCommissionLineList);
    }

}
