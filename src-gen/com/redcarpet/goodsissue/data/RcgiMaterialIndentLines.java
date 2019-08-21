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
package com.redcarpet.goodsissue.data;

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.rcss.humanresource.data.RCHR_Jobcard;
import com.redcarpet.epcg.data.ConeType;
import com.redcarpet.epcg.data.EpcgVariant;

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
 * Entity class for entity RCGI_MaterialIndentLines (stored in table RCGI_MaterialIndentLines).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RcgiMaterialIndentLines extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCGI_MaterialIndentLines";
    public static final String ENTITY_NAME = "RCGI_MaterialIndentLines";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_RCGIMATERIALINDENT = "rcgiMaterialindent";
    public static final String PROPERTY_RCHRJOBCARD = "rchrJobcard";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_CONSTRUCTION = "construction";
    public static final String PROPERTY_REQUIREDQTY = "requiredqty";
    public static final String PROPERTY_INDENTQTY = "indentqty";
    public static final String PROPERTY_COOMPLETE = "coomplete";
    public static final String PROPERTY_RCHRWARPYARNTYPE = "rchrWarpyarntype";
    public static final String PROPERTY_EPCGVARIANT = "epcgVariant";
    public static final String PROPERTY_EPCGCONETYPE = "epcgConetype";
    public static final String PROPERTY_INDENTTYPE = "indenttype";
    public static final String PROPERTY_RCGIMATERIALISSUELIST = "rCGIMaterialIssueList";

    public RcgiMaterialIndentLines() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_REQUIREDQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_INDENTQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_COOMPLETE, false);
        setDefaultValue(PROPERTY_RCGIMATERIALISSUELIST, new ArrayList<Object>());
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

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public RcgiMaterialIndent getRcgiMaterialindent() {
        return (RcgiMaterialIndent) get(PROPERTY_RCGIMATERIALINDENT);
    }

    public void setRcgiMaterialindent(RcgiMaterialIndent rcgiMaterialindent) {
        set(PROPERTY_RCGIMATERIALINDENT, rcgiMaterialindent);
    }

    public RCHR_Jobcard getRchrJobcard() {
        return (RCHR_Jobcard) get(PROPERTY_RCHRJOBCARD);
    }

    public void setRchrJobcard(RCHR_Jobcard rchrJobcard) {
        set(PROPERTY_RCHRJOBCARD, rchrJobcard);
    }

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public String getConstruction() {
        return (String) get(PROPERTY_CONSTRUCTION);
    }

    public void setConstruction(String construction) {
        set(PROPERTY_CONSTRUCTION, construction);
    }

    public BigDecimal getRequiredqty() {
        return (BigDecimal) get(PROPERTY_REQUIREDQTY);
    }

    public void setRequiredqty(BigDecimal requiredqty) {
        set(PROPERTY_REQUIREDQTY, requiredqty);
    }

    public BigDecimal getIndentqty() {
        return (BigDecimal) get(PROPERTY_INDENTQTY);
    }

    public void setIndentqty(BigDecimal indentqty) {
        set(PROPERTY_INDENTQTY, indentqty);
    }

    public Boolean isCoomplete() {
        return (Boolean) get(PROPERTY_COOMPLETE);
    }

    public void setCoomplete(Boolean coomplete) {
        set(PROPERTY_COOMPLETE, coomplete);
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

    public ConeType getEpcgConetype() {
        return (ConeType) get(PROPERTY_EPCGCONETYPE);
    }

    public void setEpcgConetype(ConeType epcgConetype) {
        set(PROPERTY_EPCGCONETYPE, epcgConetype);
    }

    public String getIndenttype() {
        return (String) get(PROPERTY_INDENTTYPE);
    }

    public void setIndenttype(String indenttype) {
        set(PROPERTY_INDENTTYPE, indenttype);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MaterialIssue> getRCGIMaterialIssueList() {
        return (List<RCGI_MaterialIssue>) get(PROPERTY_RCGIMATERIALISSUELIST);
    }

    public void setRCGIMaterialIssueList(List<RCGI_MaterialIssue> rCGIMaterialIssueList) {
        set(PROPERTY_RCGIMATERIALISSUELIST, rCGIMaterialIssueList);
    }

}
