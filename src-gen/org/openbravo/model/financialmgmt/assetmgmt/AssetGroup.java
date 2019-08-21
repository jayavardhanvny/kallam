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
package org.openbravo.model.financialmgmt.assetmgmt;

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
import org.openbravo.model.common.plm.ProductCategory;
/**
 * Entity class for entity FinancialMgmtAssetGroup (stored in table A_Asset_Group).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AssetGroup extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "A_Asset_Group";
    public static final String ENTITY_NAME = "FinancialMgmtAssetGroup";
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
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_OWNED = "owned";
    public static final String PROPERTY_DEPRECIATE = "depreciate";
    public static final String PROPERTY_DEPRECIATIONTYPE = "depreciationType";
    public static final String PROPERTY_CALCULATETYPE = "calculateType";
    public static final String PROPERTY_ANNUALDEPRECIATION = "annualDepreciation";
    public static final String PROPERTY_AMORTIZE = "amortize";
    public static final String PROPERTY_USABLELIFEYEARS = "usableLifeYears";
    public static final String PROPERTY_USABLELIFEMONTHS = "usableLifeMonths";
    public static final String PROPERTY_EVERYMONTHIS30DAYS = "everyMonthIs30Days";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_FINANCIALMGMTASSETGROUPACCTLIST = "financialMgmtAssetGroupAcctList";
    public static final String PROPERTY_PRODUCTCATEGORYLIST = "productCategoryList";

    public AssetGroup() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OWNED, true);
        setDefaultValue(PROPERTY_DEPRECIATE, false);
        setDefaultValue(PROPERTY_EVERYMONTHIS30DAYS, true);
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETGROUPACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYLIST, new ArrayList<Object>());
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

    public String getHelpComment() {
        return (String) get(PROPERTY_HELPCOMMENT);
    }

    public void setHelpComment(String helpComment) {
        set(PROPERTY_HELPCOMMENT, helpComment);
    }

    public Boolean isOwned() {
        return (Boolean) get(PROPERTY_OWNED);
    }

    public void setOwned(Boolean owned) {
        set(PROPERTY_OWNED, owned);
    }

    public Boolean isDepreciate() {
        return (Boolean) get(PROPERTY_DEPRECIATE);
    }

    public void setDepreciate(Boolean depreciate) {
        set(PROPERTY_DEPRECIATE, depreciate);
    }

    public String getDepreciationType() {
        return (String) get(PROPERTY_DEPRECIATIONTYPE);
    }

    public void setDepreciationType(String depreciationType) {
        set(PROPERTY_DEPRECIATIONTYPE, depreciationType);
    }

    public String getCalculateType() {
        return (String) get(PROPERTY_CALCULATETYPE);
    }

    public void setCalculateType(String calculateType) {
        set(PROPERTY_CALCULATETYPE, calculateType);
    }

    public BigDecimal getAnnualDepreciation() {
        return (BigDecimal) get(PROPERTY_ANNUALDEPRECIATION);
    }

    public void setAnnualDepreciation(BigDecimal annualDepreciation) {
        set(PROPERTY_ANNUALDEPRECIATION, annualDepreciation);
    }

    public String getAmortize() {
        return (String) get(PROPERTY_AMORTIZE);
    }

    public void setAmortize(String amortize) {
        set(PROPERTY_AMORTIZE, amortize);
    }

    public Long getUsableLifeYears() {
        return (Long) get(PROPERTY_USABLELIFEYEARS);
    }

    public void setUsableLifeYears(Long usableLifeYears) {
        set(PROPERTY_USABLELIFEYEARS, usableLifeYears);
    }

    public Long getUsableLifeMonths() {
        return (Long) get(PROPERTY_USABLELIFEMONTHS);
    }

    public void setUsableLifeMonths(Long usableLifeMonths) {
        set(PROPERTY_USABLELIFEMONTHS, usableLifeMonths);
    }

    public Boolean isEveryMonthIs30Days() {
        return (Boolean) get(PROPERTY_EVERYMONTHIS30DAYS);
    }

    public void setEveryMonthIs30Days(Boolean everyMonthIs30Days) {
        set(PROPERTY_EVERYMONTHIS30DAYS, everyMonthIs30Days);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
        return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetGroupAcct> getFinancialMgmtAssetGroupAcctList() {
        return (List<AssetGroupAcct>) get(PROPERTY_FINANCIALMGMTASSETGROUPACCTLIST);
    }

    public void setFinancialMgmtAssetGroupAcctList(List<AssetGroupAcct> financialMgmtAssetGroupAcctList) {
        set(PROPERTY_FINANCIALMGMTASSETGROUPACCTLIST, financialMgmtAssetGroupAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductCategory> getProductCategoryList() {
        return (List<ProductCategory>) get(PROPERTY_PRODUCTCATEGORYLIST);
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        set(PROPERTY_PRODUCTCATEGORYLIST, productCategoryList);
    }

}
