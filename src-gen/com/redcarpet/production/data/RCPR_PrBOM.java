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
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
/**
 * Entity class for entity RCPR_PrBOM (stored in table RCPR_PrBOM).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPR_PrBOM extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_PrBOM";
    public static final String ENTITY_NAME = "RCPR_PrBOM";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_PRODUCTIONPROCESSLEVEL = "productionProcessLevel";
    public static final String PROPERTY_RECORDSCREATED = "recordsCreated";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_VALIDATEWORKEFFORT = "validateWorkEffort";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_RCPRPRBOMEXPENSELIST = "rCPRPrBOMExpenseList";
    public static final String PROPERTY_RCPRPRBOMLINESLIST = "rCPRPrBOMLinesList";
    public static final String PROPERTY_RCPRPRODUCTIONRUNLIST = "rCPRProductionRunList";

    public RCPR_PrBOM() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RECORDSCREATED, false);
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_VALIDATEWORKEFFORT, false);
        setDefaultValue(PROPERTY_RCPRPRBOMEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRBOMLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRODUCTIONRUNLIST, new ArrayList<Object>());
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
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

    public RCPR_PrProcessLevel getProductionProcessLevel() {
        return (RCPR_PrProcessLevel) get(PROPERTY_PRODUCTIONPROCESSLEVEL);
    }

    public void setProductionProcessLevel(RCPR_PrProcessLevel productionProcessLevel) {
        set(PROPERTY_PRODUCTIONPROCESSLEVEL, productionProcessLevel);
    }

    public Boolean isRecordsCreated() {
        return (Boolean) get(PROPERTY_RECORDSCREATED);
    }

    public void setRecordsCreated(Boolean recordsCreated) {
        set(PROPERTY_RECORDSCREATED, recordsCreated);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public UserDimension1 getStDimension() {
        return (UserDimension1) get(PROPERTY_STDIMENSION);
    }

    public void setStDimension(UserDimension1 stDimension) {
        set(PROPERTY_STDIMENSION, stDimension);
    }

    public UserDimension2 getNdDimension() {
        return (UserDimension2) get(PROPERTY_NDDIMENSION);
    }

    public void setNdDimension(UserDimension2 ndDimension) {
        set(PROPERTY_NDDIMENSION, ndDimension);
    }

    public Boolean isValidateWorkEffort() {
        return (Boolean) get(PROPERTY_VALIDATEWORKEFFORT);
    }

    public void setValidateWorkEffort(Boolean validateWorkEffort) {
        set(PROPERTY_VALIDATEWORKEFFORT, validateWorkEffort);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrBOMExpense> getRCPRPrBOMExpenseList() {
        return (List<RCPR_PrBOMExpense>) get(PROPERTY_RCPRPRBOMEXPENSELIST);
    }

    public void setRCPRPrBOMExpenseList(List<RCPR_PrBOMExpense> rCPRPrBOMExpenseList) {
        set(PROPERTY_RCPRPRBOMEXPENSELIST, rCPRPrBOMExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrBOMLines> getRCPRPrBOMLinesList() {
        return (List<RCPR_PrBOMLines>) get(PROPERTY_RCPRPRBOMLINESLIST);
    }

    public void setRCPRPrBOMLinesList(List<RCPR_PrBOMLines> rCPRPrBOMLinesList) {
        set(PROPERTY_RCPRPRBOMLINESLIST, rCPRPrBOMLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_ProductionRun> getRCPRProductionRunList() {
        return (List<RCPR_ProductionRun>) get(PROPERTY_RCPRPRODUCTIONRUNLIST);
    }

    public void setRCPRProductionRunList(List<RCPR_ProductionRun> rCPRProductionRunList) {
        set(PROPERTY_RCPRPRODUCTIONRUNLIST, rCPRProductionRunList);
    }

}
