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

import com.rcss.humanresource.data.RCHRWarpyarntype;

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
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity Epcg_Yarncosttemplatelines (stored in table Epcg_Yarncosttemplatelines).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgYarncosttemplatelines extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_Yarncosttemplatelines";
    public static final String ENTITY_NAME = "Epcg_Yarncosttemplatelines";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EPCGYARNCOSTTEMPLATE = "epcgYarncosttemplate";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_RCHRWARPYARNTYPE = "rchrWarpyarntype";
    public static final String PROPERTY_SIZING = "sizing";
    public static final String PROPERTY_EFFICIENCY = "efficiency";
    public static final String PROPERTY_SPEED = "speed";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_EPCGVARIANT = "epcgVariant";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_EPCGCOSTENQUIRYEPCGYARNCOSTTEMPLATELINESWLIST = "epcgCostenquiryEpcgYarncosttemplatelinesWList";
    public static final String PROPERTY_EPCGWARPCOSTINGLIST = "epcgWarpcostingList";
    public static final String PROPERTY_EPCGWEFTCOSTINGLIST = "epcgWeftcostingList";

    public EpcgYarncosttemplatelines() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SIZING, (long) 0);
        setDefaultValue(PROPERTY_EFFICIENCY, (long) 0);
        setDefaultValue(PROPERTY_SPEED, (long) 0);
        setDefaultValue(PROPERTY_RATE, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYEPCGYARNCOSTTEMPLATELINESWLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWARPCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWEFTCOSTINGLIST, new ArrayList<Object>());
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

    public EpcgYarncosttemplate getEpcgYarncosttemplate() {
        return (EpcgYarncosttemplate) get(PROPERTY_EPCGYARNCOSTTEMPLATE);
    }

    public void setEpcgYarncosttemplate(EpcgYarncosttemplate epcgYarncosttemplate) {
        set(PROPERTY_EPCGYARNCOSTTEMPLATE, epcgYarncosttemplate);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public RCHRWarpyarntype getRchrWarpyarntype() {
        return (RCHRWarpyarntype) get(PROPERTY_RCHRWARPYARNTYPE);
    }

    public void setRchrWarpyarntype(RCHRWarpyarntype rchrWarpyarntype) {
        set(PROPERTY_RCHRWARPYARNTYPE, rchrWarpyarntype);
    }

    public Long getSizing() {
        return (Long) get(PROPERTY_SIZING);
    }

    public void setSizing(Long sizing) {
        set(PROPERTY_SIZING, sizing);
    }

    public Long getEfficiency() {
        return (Long) get(PROPERTY_EFFICIENCY);
    }

    public void setEfficiency(Long efficiency) {
        set(PROPERTY_EFFICIENCY, efficiency);
    }

    public Long getSpeed() {
        return (Long) get(PROPERTY_SPEED);
    }

    public void setSpeed(Long speed) {
        set(PROPERTY_SPEED, speed);
    }

    public BigDecimal getRate() {
        return (BigDecimal) get(PROPERTY_RATE);
    }

    public void setRate(BigDecimal rate) {
        set(PROPERTY_RATE, rate);
    }

    public EpcgVariant getEpcgVariant() {
        return (EpcgVariant) get(PROPERTY_EPCGVARIANT);
    }

    public void setEpcgVariant(EpcgVariant epcgVariant) {
        set(PROPERTY_EPCGVARIANT, epcgVariant);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryEpcgYarncosttemplatelinesWList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYEPCGYARNCOSTTEMPLATELINESWLIST);
    }

    public void setEpcgCostenquiryEpcgYarncosttemplatelinesWList(List<EpcgCostenquiry> epcgCostenquiryEpcgYarncosttemplatelinesWList) {
        set(PROPERTY_EPCGCOSTENQUIRYEPCGYARNCOSTTEMPLATELINESWLIST, epcgCostenquiryEpcgYarncosttemplatelinesWList);
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

}
