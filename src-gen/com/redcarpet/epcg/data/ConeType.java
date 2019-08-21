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

import com.redcarpet.goodsissue.data.RCGITransactions;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.goodsissue.data.RCGI_MrLines;
import com.redcarpet.goodsissue.data.RcgiMaterialIndentLines;

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
 * Entity class for entity EPCG_ConeType (stored in table EPCG_ConeType).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ConeType extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "EPCG_ConeType";
    public static final String ENTITY_NAME = "EPCG_ConeType";
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
    public static final String PROPERTY_RCGIMATERIALINDENTLINESLIST = "rCGIMaterialIndentLinesList";
    public static final String PROPERTY_RCGIMATERIALISSUELIST = "rCGIMaterialIssueList";
    public static final String PROPERTY_RCGIMILINESLIST = "rCGIMiLinesList";
    public static final String PROPERTY_RCGIMRLINESLIST = "rCGIMrLinesList";
    public static final String PROPERTY_RCGITRANSACTIONSLIST = "rCGITransactionsList";

    public ConeType() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCGIMATERIALINDENTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGITRANSACTIONSLIST, new ArrayList<Object>());
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

}
