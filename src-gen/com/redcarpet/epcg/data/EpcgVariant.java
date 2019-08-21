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
package com.redcarpet.epcg.data;

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.redcarpet.goodsissue.data.RCGITransactions;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.goodsissue.data.RCGI_MrLines;
import com.redcarpet.goodsissue.data.RcgiMaterialIndentLines;

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
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
/**
 * Entity class for entity Epcg_Variant (stored in table Epcg_Variant).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgVariant extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_Variant";
    public static final String ENTITY_NAME = "Epcg_Variant";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_EPCGCOSTENQUIRYWEFTVARIANTLIST = "epcgCostenquiryWeftvariantList";
    public static final String PROPERTY_EPCGWARPCOSTINGLIST = "epcgWarpcostingList";
    public static final String PROPERTY_EPCGWEFTCOSTINGLIST = "epcgWeftcostingList";
    public static final String PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST = "epcgYarncosttemplatelinesList";
    public static final String PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST = "materialMgmtMaterialTransactionList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_RCGIMATERIALINDENTLINESLIST = "rCGIMaterialIndentLinesList";
    public static final String PROPERTY_RCGIMATERIALISSUELIST = "rCGIMaterialIssueList";
    public static final String PROPERTY_RCGIMILINESLIST = "rCGIMiLinesList";
    public static final String PROPERTY_RCGIMRLINESLIST = "rCGIMrLinesList";
    public static final String PROPERTY_RCGITRANSACTIONSLIST = "rCGITransactionsList";
    public static final String PROPERTY_RCHRQUALITYSTANDARDLIST = "rCHRQualitystandardList";
    public static final String PROPERTY_RCHRQUALITYSTANDARDWEFTVARIANTLIST = "rCHRQualitystandardWeftvariantList";

    public EpcgVariant() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYWEFTVARIANTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWARPCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWEFTCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALINDENTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGITRANSACTIONSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRQUALITYSTANDARDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRQUALITYSTANDARDWEFTVARIANTLIST, new ArrayList<Object>());
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

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public BigDecimal getRate() {
        return (BigDecimal) get(PROPERTY_RATE);
    }

    public void setRate(BigDecimal rate) {
        set(PROPERTY_RATE, rate);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryWeftvariantList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYWEFTVARIANTLIST);
    }

    public void setEpcgCostenquiryWeftvariantList(List<EpcgCostenquiry> epcgCostenquiryWeftvariantList) {
        set(PROPERTY_EPCGCOSTENQUIRYWEFTVARIANTLIST, epcgCostenquiryWeftvariantList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgWarpcosting> getEpcgWarpcostingList() {
        return (List<EpcgWarpcosting>) get(PROPERTY_EPCGWARPCOSTINGLIST);
    }

    public void setEpcgWarpcostingList(List<EpcgWarpcosting> epcgWarpcostingList) {
        set(PROPERTY_EPCGWARPCOSTINGLIST, epcgWarpcostingList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgWeftcosting> getEpcgWeftcostingList() {
        return (List<EpcgWeftcosting>) get(PROPERTY_EPCGWEFTCOSTINGLIST);
    }

    public void setEpcgWeftcostingList(List<EpcgWeftcosting> epcgWeftcostingList) {
        set(PROPERTY_EPCGWEFTCOSTINGLIST, epcgWeftcostingList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgYarncosttemplatelines> getEpcgYarncosttemplatelinesList() {
        return (List<EpcgYarncosttemplatelines>) get(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST);
    }

    public void setEpcgYarncosttemplatelinesList(List<EpcgYarncosttemplatelines> epcgYarncosttemplatelinesList) {
        set(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST, epcgYarncosttemplatelinesList);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransaction> getMaterialMgmtMaterialTransactionList() {
        return (List<MaterialTransaction>) get(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST);
    }

    public void setMaterialMgmtMaterialTransactionList(List<MaterialTransaction> materialMgmtMaterialTransactionList) {
        set(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, materialMgmtMaterialTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RcgiMaterialIndentLines> getRCGIMaterialIndentLinesList() {
        return (List<RcgiMaterialIndentLines>) get(PROPERTY_RCGIMATERIALINDENTLINESLIST);
    }

    public void setRCGIMaterialIndentLinesList(List<RcgiMaterialIndentLines> rCGIMaterialIndentLinesList) {
        set(PROPERTY_RCGIMATERIALINDENTLINESLIST, rCGIMaterialIndentLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MaterialIssue> getRCGIMaterialIssueList() {
        return (List<RCGI_MaterialIssue>) get(PROPERTY_RCGIMATERIALISSUELIST);
    }

    public void setRCGIMaterialIssueList(List<RCGI_MaterialIssue> rCGIMaterialIssueList) {
        set(PROPERTY_RCGIMATERIALISSUELIST, rCGIMaterialIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MiLines> getRCGIMiLinesList() {
        return (List<RCGI_MiLines>) get(PROPERTY_RCGIMILINESLIST);
    }

    public void setRCGIMiLinesList(List<RCGI_MiLines> rCGIMiLinesList) {
        set(PROPERTY_RCGIMILINESLIST, rCGIMiLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MrLines> getRCGIMrLinesList() {
        return (List<RCGI_MrLines>) get(PROPERTY_RCGIMRLINESLIST);
    }

    public void setRCGIMrLinesList(List<RCGI_MrLines> rCGIMrLinesList) {
        set(PROPERTY_RCGIMRLINESLIST, rCGIMrLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGITransactions> getRCGITransactionsList() {
        return (List<RCGITransactions>) get(PROPERTY_RCGITRANSACTIONSLIST);
    }

    public void setRCGITransactionsList(List<RCGITransactions> rCGITransactionsList) {
        set(PROPERTY_RCGITRANSACTIONSLIST, rCGITransactionsList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRQualitystandard> getRCHRQualitystandardList() {
        return (List<RCHRQualitystandard>) get(PROPERTY_RCHRQUALITYSTANDARDLIST);
    }

    public void setRCHRQualitystandardList(List<RCHRQualitystandard> rCHRQualitystandardList) {
        set(PROPERTY_RCHRQUALITYSTANDARDLIST, rCHRQualitystandardList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRQualitystandard> getRCHRQualitystandardWeftvariantList() {
        return (List<RCHRQualitystandard>) get(PROPERTY_RCHRQUALITYSTANDARDWEFTVARIANTLIST);
    }

    public void setRCHRQualitystandardWeftvariantList(List<RCHRQualitystandard> rCHRQualitystandardWeftvariantList) {
        set(PROPERTY_RCHRQUALITYSTANDARDWEFTVARIANTLIST, rCHRQualitystandardWeftvariantList);
    }

}