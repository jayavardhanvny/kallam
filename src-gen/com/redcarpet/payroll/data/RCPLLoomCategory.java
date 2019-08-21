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

import com.rcss.humanresource.data.RCHR_Sizing_Beam;
import com.rcss.humanresource.data.RchrDesigloomincentives;
import com.rcss.humanresource.data.RchrPreparatprodincntive;

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
/**
 * Entity class for entity RCPL_LoomCategory (stored in table RCPL_LoomCategory).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPLLoomCategory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_LoomCategory";
    public static final String ENTITY_NAME = "RCPL_LoomCategory";
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
    public static final String PROPERTY_EXTRAEFFICIENCY = "extraefficiency";
    public static final String PROPERTY_MINLOOMS = "minlooms";
    public static final String PROPERTY_ISALL = "isall";
    public static final String PROPERTY_ISTOYOTA = "istoyota";
    public static final String PROPERTY_STARMINLOOMS = "starminlooms";
    public static final String PROPERTY_RCHRDESIGLOOMINCENTIVESLIST = "rCHRDesigloomincentivesList";
    public static final String PROPERTY_RCHRPREPARATPRODINCNTIVELIST = "rCHRPreparatprodincntiveList";
    public static final String PROPERTY_RCHRSIZINGBEAMLIST = "rCHRSizingBeamList";
    public static final String PROPERTY_RCPLEFFICIENCYLINELIST = "rCPLEfficiencyLineList";
    public static final String PROPERTY_RCPLLOOMLIST = "rCPLLoomList";
    public static final String PROPERTY_RCPLPRODINCENTIVELIST = "rCPLProdIncentiveList";
    public static final String PROPERTY_RCPLRELIEVEREFFCNCYLIST = "rCPLRelievereffcncyList";
    public static final String PROPERTY_RCPLLOOMCATEGORYLINESLIST = "rcplLoomcategorylinesList";

    public RCPLLoomCategory() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_EXTRAEFFICIENCY, new BigDecimal(0));
        setDefaultValue(PROPERTY_MINLOOMS, (long) 0);
        setDefaultValue(PROPERTY_ISALL, false);
        setDefaultValue(PROPERTY_ISTOYOTA, false);
        setDefaultValue(PROPERTY_STARMINLOOMS, (long) 0);
        setDefaultValue(PROPERTY_RCHRDESIGLOOMINCENTIVESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRPREPARATPRODINCNTIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEFFICIENCYLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLLOOMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLRELIEVEREFFCNCYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLLOOMCATEGORYLINESLIST, new ArrayList<Object>());
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

    public BigDecimal getExtraefficiency() {
        return (BigDecimal) get(PROPERTY_EXTRAEFFICIENCY);
    }

    public void setExtraefficiency(BigDecimal extraefficiency) {
        set(PROPERTY_EXTRAEFFICIENCY, extraefficiency);
    }

    public Long getMinlooms() {
        return (Long) get(PROPERTY_MINLOOMS);
    }

    public void setMinlooms(Long minlooms) {
        set(PROPERTY_MINLOOMS, minlooms);
    }

    public Boolean isAll() {
        return (Boolean) get(PROPERTY_ISALL);
    }

    public void setAll(Boolean isall) {
        set(PROPERTY_ISALL, isall);
    }

    public Boolean isToyota() {
        return (Boolean) get(PROPERTY_ISTOYOTA);
    }

    public void setToyota(Boolean istoyota) {
        set(PROPERTY_ISTOYOTA, istoyota);
    }

    public Long getStarminlooms() {
        return (Long) get(PROPERTY_STARMINLOOMS);
    }

    public void setStarminlooms(Long starminlooms) {
        set(PROPERTY_STARMINLOOMS, starminlooms);
    }

    @SuppressWarnings("unchecked")
    public List<RchrDesigloomincentives> getRCHRDesigloomincentivesList() {
        return (List<RchrDesigloomincentives>) get(PROPERTY_RCHRDESIGLOOMINCENTIVESLIST);
    }

    public void setRCHRDesigloomincentivesList(List<RchrDesigloomincentives> rCHRDesigloomincentivesList) {
        set(PROPERTY_RCHRDESIGLOOMINCENTIVESLIST, rCHRDesigloomincentivesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrPreparatprodincntive> getRCHRPreparatprodincntiveList() {
        return (List<RchrPreparatprodincntive>) get(PROPERTY_RCHRPREPARATPRODINCNTIVELIST);
    }

    public void setRCHRPreparatprodincntiveList(List<RchrPreparatprodincntive> rCHRPreparatprodincntiveList) {
        set(PROPERTY_RCHRPREPARATPRODINCNTIVELIST, rCHRPreparatprodincntiveList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizing_Beam> getRCHRSizingBeamList() {
        return (List<RCHR_Sizing_Beam>) get(PROPERTY_RCHRSIZINGBEAMLIST);
    }

    public void setRCHRSizingBeamList(List<RCHR_Sizing_Beam> rCHRSizingBeamList) {
        set(PROPERTY_RCHRSIZINGBEAMLIST, rCHRSizingBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLEfficiencyLine> getRCPLEfficiencyLineList() {
        return (List<RCPLEfficiencyLine>) get(PROPERTY_RCPLEFFICIENCYLINELIST);
    }

    public void setRCPLEfficiencyLineList(List<RCPLEfficiencyLine> rCPLEfficiencyLineList) {
        set(PROPERTY_RCPLEFFICIENCYLINELIST, rCPLEfficiencyLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLLoom> getRCPLLoomList() {
        return (List<RCPLLoom>) get(PROPERTY_RCPLLOOMLIST);
    }

    public void setRCPLLoomList(List<RCPLLoom> rCPLLoomList) {
        set(PROPERTY_RCPLLOOMLIST, rCPLLoomList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentive> getRCPLProdIncentiveList() {
        return (List<RCPLProdIncentive>) get(PROPERTY_RCPLPRODINCENTIVELIST);
    }

    public void setRCPLProdIncentiveList(List<RCPLProdIncentive> rCPLProdIncentiveList) {
        set(PROPERTY_RCPLPRODINCENTIVELIST, rCPLProdIncentiveList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLRelievereffcncy> getRCPLRelievereffcncyList() {
        return (List<RCPLRelievereffcncy>) get(PROPERTY_RCPLRELIEVEREFFCNCYLIST);
    }

    public void setRCPLRelievereffcncyList(List<RCPLRelievereffcncy> rCPLRelievereffcncyList) {
        set(PROPERTY_RCPLRELIEVEREFFCNCYLIST, rCPLRelievereffcncyList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplLoomcategorylines> getRcplLoomcategorylinesList() {
        return (List<RcplLoomcategorylines>) get(PROPERTY_RCPLLOOMCATEGORYLINESLIST);
    }

    public void setRcplLoomcategorylinesList(List<RcplLoomcategorylines> rcplLoomcategorylinesList) {
        set(PROPERTY_RCPLLOOMCATEGORYLINESLIST, rcplLoomcategorylinesList);
    }

}
