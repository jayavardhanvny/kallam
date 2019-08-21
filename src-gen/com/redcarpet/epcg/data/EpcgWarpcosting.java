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

import java.util.Date;

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
 * Entity class for entity Epcg_Warpcosting (stored in table Epcg_Warpcosting).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgWarpcosting extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_Warpcosting";
    public static final String ENTITY_NAME = "Epcg_Warpcosting";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_SPEED = "speed";
    public static final String PROPERTY_EFFICIENCY = "efficiency";
    public static final String PROPERTY_WARPRATEKGS = "warpratekgs";
    public static final String PROPERTY_WARPWTKGSPERMTS = "warpwtkgspermts";
    public static final String PROPERTY_WARPWTKGSWITHWASTE = "warpwtkgswithwaste";
    public static final String PROPERTY_SIZINGFROMMASTER = "sizingfrommaster";
    public static final String PROPERTY_SIZINGCHEMICALMTS = "sizingchemicalmts";
    public static final String PROPERTY_RCHRWARPYARNTYPE = "rchrWarpyarntype";
    public static final String PROPERTY_EPCGVARIANT = "epcgVariant";
    public static final String PROPERTY_EPCGCOSTENQUIRY = "epcgCostenquiry";
    public static final String PROPERTY_EPI = "epi";
    public static final String PROPERTY_EPCGYARNCOSTTEMPLATELINES = "epcgYarncosttemplatelines";

    public EpcgWarpcosting() {
        setDefaultValue(PROPERTY_ID, "get_uuid()");
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SPEED, (long) 0);
        setDefaultValue(PROPERTY_EFFICIENCY, (long) 0);
        setDefaultValue(PROPERTY_WARPRATEKGS, (long) 0);
        setDefaultValue(PROPERTY_WARPWTKGSPERMTS, (long) 0);
        setDefaultValue(PROPERTY_WARPWTKGSWITHWASTE, (long) 0);
        setDefaultValue(PROPERTY_SIZINGFROMMASTER, (long) 0);
        setDefaultValue(PROPERTY_SIZINGCHEMICALMTS, (long) 0);
        setDefaultValue(PROPERTY_EPI, (long) 0);
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

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Long getSpeed() {
        return (Long) get(PROPERTY_SPEED);
    }

    public void setSpeed(Long speed) {
        set(PROPERTY_SPEED, speed);
    }

    public Long getEfficiency() {
        return (Long) get(PROPERTY_EFFICIENCY);
    }

    public void setEfficiency(Long efficiency) {
        set(PROPERTY_EFFICIENCY, efficiency);
    }

    public Long getWarpratekgs() {
        return (Long) get(PROPERTY_WARPRATEKGS);
    }

    public void setWarpratekgs(Long warpratekgs) {
        set(PROPERTY_WARPRATEKGS, warpratekgs);
    }

    public Long getWarpwtkgspermts() {
        return (Long) get(PROPERTY_WARPWTKGSPERMTS);
    }

    public void setWarpwtkgspermts(Long warpwtkgspermts) {
        set(PROPERTY_WARPWTKGSPERMTS, warpwtkgspermts);
    }

    public Long getWarpwtkgswithwaste() {
        return (Long) get(PROPERTY_WARPWTKGSWITHWASTE);
    }

    public void setWarpwtkgswithwaste(Long warpwtkgswithwaste) {
        set(PROPERTY_WARPWTKGSWITHWASTE, warpwtkgswithwaste);
    }

    public Long getSizingfrommaster() {
        return (Long) get(PROPERTY_SIZINGFROMMASTER);
    }

    public void setSizingfrommaster(Long sizingfrommaster) {
        set(PROPERTY_SIZINGFROMMASTER, sizingfrommaster);
    }

    public Long getSizingchemicalmts() {
        return (Long) get(PROPERTY_SIZINGCHEMICALMTS);
    }

    public void setSizingchemicalmts(Long sizingchemicalmts) {
        set(PROPERTY_SIZINGCHEMICALMTS, sizingchemicalmts);
    }

    public RCHRWarpyarntype getRchrWarpyarntype() {
        return (RCHRWarpyarntype) get(PROPERTY_RCHRWARPYARNTYPE);
    }

    public void setRchrWarpyarntype(RCHRWarpyarntype rchrWarpyarntype) {
        set(PROPERTY_RCHRWARPYARNTYPE, rchrWarpyarntype);
    }

    public EpcgVariant getEpcgVariant() {
        return (EpcgVariant) get(PROPERTY_EPCGVARIANT);
    }

    public void setEpcgVariant(EpcgVariant epcgVariant) {
        set(PROPERTY_EPCGVARIANT, epcgVariant);
    }

    public EpcgCostenquiry getEpcgCostenquiry() {
        return (EpcgCostenquiry) get(PROPERTY_EPCGCOSTENQUIRY);
    }

    public void setEpcgCostenquiry(EpcgCostenquiry epcgCostenquiry) {
        set(PROPERTY_EPCGCOSTENQUIRY, epcgCostenquiry);
    }

    public Long getEpi() {
        return (Long) get(PROPERTY_EPI);
    }

    public void setEpi(Long epi) {
        set(PROPERTY_EPI, epi);
    }

    public EpcgYarncosttemplatelines getEpcgYarncosttemplatelines() {
        return (EpcgYarncosttemplatelines) get(PROPERTY_EPCGYARNCOSTTEMPLATELINES);
    }

    public void setEpcgYarncosttemplatelines(EpcgYarncosttemplatelines epcgYarncosttemplatelines) {
        set(PROPERTY_EPCGYARNCOSTTEMPLATELINES, epcgYarncosttemplatelines);
    }

}
