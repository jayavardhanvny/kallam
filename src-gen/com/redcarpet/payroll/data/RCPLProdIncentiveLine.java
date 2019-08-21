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

import com.rcss.humanresource.data.RCHR_Detention;
import com.redcarpet.production.data.RCPRShift;

import java.sql.Timestamp;
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
 * Entity class for entity RCPL_ProdIncentiveLine (stored in table RCPL_ProdIncentiveLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPLProdIncentiveLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_ProdIncentiveLine";
    public static final String ENTITY_NAME = "RCPL_ProdIncentiveLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCPLLOOM = "rcplLoom";
    public static final String PROPERTY_RCPLPRODINCENTIVE = "rcplProdincentive";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_EFFICIENCY = "efficiency";
    public static final String PROPERTY_TIME1 = "time1";
    public static final String PROPERTY_TIME2 = "time2";
    public static final String PROPERTY_REMARKS = "remarks";
    public static final String PROPERTY_DETENTIONTWO = "detentiontwo";
    public static final String PROPERTY_DETENTIONFOUR = "detentionfour";
    public static final String PROPERTY_DETENTIONTHREE = "detentionthree";
    public static final String PROPERTY_TIME3 = "time3";
    public static final String PROPERTY_TIME4 = "time4";
    public static final String PROPERTY_RCHRDETENTION = "rchrDetention";
    public static final String PROPERTY_DYED = "dyed";
    public static final String PROPERTY_GREY = "grey";
    public static final String PROPERTY_RCPLPRODINCSECLINELIST = "rcplProdincseclineList";

    public RCPLProdIncentiveLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_EFFICIENCY, "0");
        setDefaultValue(PROPERTY_DYED, false);
        setDefaultValue(PROPERTY_GREY, false);
        setDefaultValue(PROPERTY_RCPLPRODINCSECLINELIST, new ArrayList<Object>());
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

    public RCPLLoom getRcplLoom() {
        return (RCPLLoom) get(PROPERTY_RCPLLOOM);
    }

    public void setRcplLoom(RCPLLoom rcplLoom) {
        set(PROPERTY_RCPLLOOM, rcplLoom);
    }

    public RCPLProdIncentive getRcplProdincentive() {
        return (RCPLProdIncentive) get(PROPERTY_RCPLPRODINCENTIVE);
    }

    public void setRcplProdincentive(RCPLProdIncentive rcplProdincentive) {
        set(PROPERTY_RCPLPRODINCENTIVE, rcplProdincentive);
    }

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public String getEfficiency() {
        return (String) get(PROPERTY_EFFICIENCY);
    }

    public void setEfficiency(String efficiency) {
        set(PROPERTY_EFFICIENCY, efficiency);
    }

    public Timestamp getTime1() {
        return (Timestamp) get(PROPERTY_TIME1);
    }

    public void setTime1(Timestamp time1) {
        set(PROPERTY_TIME1, time1);
    }

    public Timestamp getTime2() {
        return (Timestamp) get(PROPERTY_TIME2);
    }

    public void setTime2(Timestamp time2) {
        set(PROPERTY_TIME2, time2);
    }

    public String getRemarks() {
        return (String) get(PROPERTY_REMARKS);
    }

    public void setRemarks(String remarks) {
        set(PROPERTY_REMARKS, remarks);
    }

    public RCHR_Detention getDetentiontwo() {
        return (RCHR_Detention) get(PROPERTY_DETENTIONTWO);
    }

    public void setDetentiontwo(RCHR_Detention detentiontwo) {
        set(PROPERTY_DETENTIONTWO, detentiontwo);
    }

    public RCHR_Detention getDetentionfour() {
        return (RCHR_Detention) get(PROPERTY_DETENTIONFOUR);
    }

    public void setDetentionfour(RCHR_Detention detentionfour) {
        set(PROPERTY_DETENTIONFOUR, detentionfour);
    }

    public RCHR_Detention getDetentionthree() {
        return (RCHR_Detention) get(PROPERTY_DETENTIONTHREE);
    }

    public void setDetentionthree(RCHR_Detention detentionthree) {
        set(PROPERTY_DETENTIONTHREE, detentionthree);
    }

    public Timestamp getTime3() {
        return (Timestamp) get(PROPERTY_TIME3);
    }

    public void setTime3(Timestamp time3) {
        set(PROPERTY_TIME3, time3);
    }

    public Timestamp getTime4() {
        return (Timestamp) get(PROPERTY_TIME4);
    }

    public void setTime4(Timestamp time4) {
        set(PROPERTY_TIME4, time4);
    }

    public RCHR_Detention getRchrDetention() {
        return (RCHR_Detention) get(PROPERTY_RCHRDETENTION);
    }

    public void setRchrDetention(RCHR_Detention rchrDetention) {
        set(PROPERTY_RCHRDETENTION, rchrDetention);
    }

    public Boolean isDyed() {
        return (Boolean) get(PROPERTY_DYED);
    }

    public void setDyed(Boolean dyed) {
        set(PROPERTY_DYED, dyed);
    }

    public Boolean isGrey() {
        return (Boolean) get(PROPERTY_GREY);
    }

    public void setGrey(Boolean grey) {
        set(PROPERTY_GREY, grey);
    }

    @SuppressWarnings("unchecked")
    public List<RcplProdincsecline> getRcplProdincseclineList() {
        return (List<RcplProdincsecline>) get(PROPERTY_RCPLPRODINCSECLINELIST);
    }

    public void setRcplProdincseclineList(List<RcplProdincsecline> rcplProdincseclineList) {
        set(PROPERTY_RCPLPRODINCSECLINELIST, rcplProdincseclineList);
    }

}
