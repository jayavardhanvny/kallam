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
package com.redcarpet.payroll.data;

import com.rcss.humanresource.data.RCHR_Inspectionsheet;
import com.rcss.humanresource.data.RchrLoomsdata;
import com.rcss.humanresource.data.RchrRollwisedata;

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
 * Entity class for entity RCPL_Loom (stored in table RCPL_Loom).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPLLoom extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_Loom";
    public static final String ENTITY_NAME = "RCPL_Loom";
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
    public static final String PROPERTY_RCPLLOOMCATEGORY = "rcplLoomcategory";
    public static final String PROPERTY_RCPLINSPLOOMCTGRY = "rcplInsploomctgry";
    public static final String PROPERTY_LOOMNUMBER = "loomnumber";
    public static final String PROPERTY_RCPLLOOMTYPE = "rcplLoomtype";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_RCHRINSPECTIONSHEETLIST = "rCHRInspectionsheetList";
    public static final String PROPERTY_RCPLPRODINCENTIVELINELIST = "rCPLProdIncentiveLineList";
    public static final String PROPERTY_RCHRLOOMSDATALIST = "rchrLoomsdataList";
    public static final String PROPERTY_RCHRROLLWISEDATALIST = "rchrRollwisedataList";
    public static final String PROPERTY_RCPLLOOMSPRODUCTIONLIST = "rcplLoomsproductionList";

    public RCPLLoom() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCHRINSPECTIONSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLOOMSDATALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROLLWISEDATALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLLOOMSPRODUCTIONLIST, new ArrayList<Object>());
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

    public RCPLLoomCategory getRcplLoomcategory() {
        return (RCPLLoomCategory) get(PROPERTY_RCPLLOOMCATEGORY);
    }

    public void setRcplLoomcategory(RCPLLoomCategory rcplLoomcategory) {
        set(PROPERTY_RCPLLOOMCATEGORY, rcplLoomcategory);
    }

    public RCPL_Insploomctgry getRcplInsploomctgry() {
        return (RCPL_Insploomctgry) get(PROPERTY_RCPLINSPLOOMCTGRY);
    }

    public void setRcplInsploomctgry(RCPL_Insploomctgry rcplInsploomctgry) {
        set(PROPERTY_RCPLINSPLOOMCTGRY, rcplInsploomctgry);
    }

    public Long getLoomnumber() {
        return (Long) get(PROPERTY_LOOMNUMBER);
    }

    public void setLoomnumber(Long loomnumber) {
        set(PROPERTY_LOOMNUMBER, loomnumber);
    }

    public RcplLoomtype getRcplLoomtype() {
        return (RcplLoomtype) get(PROPERTY_RCPLLOOMTYPE);
    }

    public void setRcplLoomtype(RcplLoomtype rcplLoomtype) {
        set(PROPERTY_RCPLLOOMTYPE, rcplLoomtype);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspectionsheet> getRCHRInspectionsheetList() {
        return (List<RCHR_Inspectionsheet>) get(PROPERTY_RCHRINSPECTIONSHEETLIST);
    }

    public void setRCHRInspectionsheetList(List<RCHR_Inspectionsheet> rCHRInspectionsheetList) {
        set(PROPERTY_RCHRINSPECTIONSHEETLIST, rCHRInspectionsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentiveLine> getRCPLProdIncentiveLineList() {
        return (List<RCPLProdIncentiveLine>) get(PROPERTY_RCPLPRODINCENTIVELINELIST);
    }

    public void setRCPLProdIncentiveLineList(List<RCPLProdIncentiveLine> rCPLProdIncentiveLineList) {
        set(PROPERTY_RCPLPRODINCENTIVELINELIST, rCPLProdIncentiveLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrLoomsdata> getRchrLoomsdataList() {
        return (List<RchrLoomsdata>) get(PROPERTY_RCHRLOOMSDATALIST);
    }

    public void setRchrLoomsdataList(List<RchrLoomsdata> rchrLoomsdataList) {
        set(PROPERTY_RCHRLOOMSDATALIST, rchrLoomsdataList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRollwisedata> getRchrRollwisedataList() {
        return (List<RchrRollwisedata>) get(PROPERTY_RCHRROLLWISEDATALIST);
    }

    public void setRchrRollwisedataList(List<RchrRollwisedata> rchrRollwisedataList) {
        set(PROPERTY_RCHRROLLWISEDATALIST, rchrRollwisedataList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplLoomsproduction> getRcplLoomsproductionList() {
        return (List<RcplLoomsproduction>) get(PROPERTY_RCPLLOOMSPRODUCTIONLIST);
    }

    public void setRcplLoomsproductionList(List<RcplLoomsproduction> rcplLoomsproductionList) {
        set(PROPERTY_RCPLLOOMSPRODUCTIONLIST, rcplLoomsproductionList);
    }

}
