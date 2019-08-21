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
package org.openbravo.model.pricing.pricelist;

import com.redcarpet.goodsissue.data.RCGIPurchasePrice;

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
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectVendor;
/**
 * Entity class for entity PricingPriceListVersion (stored in table M_PriceList_Version).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PriceListVersion extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_PriceList_Version";
    public static final String ENTITY_NAME = "PricingPriceListVersion";
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
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_PRICELISTSCHEMA = "priceListSchema";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_CREATE = "create";
    public static final String PROPERTY_BASEPRICELISTVERSION = "basePriceListVersion";
    public static final String PROPERTY_GENERATEPRICELISTVERSION = "generatePriceListVersion";
    public static final String PROPERTY_PRICINGPRICELISTVERSIONBASEPRICELISTVERSIONLIST = "pricingPriceListVersionBasePriceListVersionList";
    public static final String PROPERTY_PRICINGPRODUCTPRICELIST = "pricingProductPriceList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_PROJECTVENDORLIST = "projectVendorList";
    public static final String PROPERTY_RCGIPURCHASEPRICELIST = "rCGIPurchasePriceList";

    public PriceListVersion() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATE, false);
        setDefaultValue(PROPERTY_GENERATEPRICELISTVERSION, false);
        setDefaultValue(PROPERTY_PRICINGPRICELISTVERSIONBASEPRICELISTVERSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGPRODUCTPRICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIPURCHASEPRICELIST, new ArrayList<Object>());
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

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public PriceListSchema getPriceListSchema() {
        return (PriceListSchema) get(PROPERTY_PRICELISTSCHEMA);
    }

    public void setPriceListSchema(PriceListSchema priceListSchema) {
        set(PROPERTY_PRICELISTSCHEMA, priceListSchema);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public Boolean isCreate() {
        return (Boolean) get(PROPERTY_CREATE);
    }

    public void setCreate(Boolean create) {
        set(PROPERTY_CREATE, create);
    }

    public PriceListVersion getBasePriceListVersion() {
        return (PriceListVersion) get(PROPERTY_BASEPRICELISTVERSION);
    }

    public void setBasePriceListVersion(PriceListVersion basePriceListVersion) {
        set(PROPERTY_BASEPRICELISTVERSION, basePriceListVersion);
    }

    public Boolean isGeneratePriceListVersion() {
        return (Boolean) get(PROPERTY_GENERATEPRICELISTVERSION);
    }

    public void setGeneratePriceListVersion(Boolean generatePriceListVersion) {
        set(PROPERTY_GENERATEPRICELISTVERSION, generatePriceListVersion);
    }

    @SuppressWarnings("unchecked")
    public List<PriceListVersion> getPricingPriceListVersionBasePriceListVersionList() {
        return (List<PriceListVersion>) get(PROPERTY_PRICINGPRICELISTVERSIONBASEPRICELISTVERSIONLIST);
    }

    public void setPricingPriceListVersionBasePriceListVersionList(List<PriceListVersion> pricingPriceListVersionBasePriceListVersionList) {
        set(PROPERTY_PRICINGPRICELISTVERSIONBASEPRICELISTVERSIONLIST, pricingPriceListVersionBasePriceListVersionList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductPrice> getPricingProductPriceList() {
        return (List<ProductPrice>) get(PROPERTY_PRICINGPRODUCTPRICELIST);
    }

    public void setPricingProductPriceList(List<ProductPrice> pricingProductPriceList) {
        set(PROPERTY_PRICINGPRODUCTPRICELIST, pricingProductPriceList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
        return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectVendor> getProjectVendorList() {
        return (List<ProjectVendor>) get(PROPERTY_PROJECTVENDORLIST);
    }

    public void setProjectVendorList(List<ProjectVendor> projectVendorList) {
        set(PROPERTY_PROJECTVENDORLIST, projectVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIPurchasePrice> getRCGIPurchasePriceList() {
        return (List<RCGIPurchasePrice>) get(PROPERTY_RCGIPURCHASEPRICELIST);
    }

    public void setRCGIPurchasePriceList(List<RCGIPurchasePrice> rCGIPurchasePriceList) {
        set(PROPERTY_RCGIPURCHASEPRICELIST, rCGIPurchasePriceList);
    }

}
