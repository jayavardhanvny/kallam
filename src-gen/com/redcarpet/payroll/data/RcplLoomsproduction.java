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

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.redcarpet.production.data.RCPRShift;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Rcpl_Loomsproduction (stored in table Rcpl_Loomsproduction).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RcplLoomsproduction extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rcpl_Loomsproduction";
    public static final String ENTITY_NAME = "Rcpl_Loomsproduction";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LOOMDATE = "loomdate";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_RCPLLOOM = "rcplLoom";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_PPI = "ppi";
    public static final String PROPERTY_PICKS = "picks";
    public static final String PROPERTY_LOOMMTS = "loommts";
    public static final String PROPERTY_RPM = "rpm";
    public static final String PROPERTY_EFFICIENCY = "efficiency";
    public static final String PROPERTY_WARPBREAKS = "warpbreaks";
    public static final String PROPERTY_WEFTBREAKS = "weftbreaks";
    public static final String PROPERTY_OTHERBREAKS = "otherbreaks";
    public static final String PROPERTY_VALIDATE = "validate";
    public static final String PROPERTY_IMPORTIT = "importit";
    public static final String PROPERTY_VALIDATED = "validated";
    public static final String PROPERTY_ISFLAG = "isflag";

    public RcplLoomsproduction() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PPI, (long) 0);
        setDefaultValue(PROPERTY_PICKS, (long) 0);
        setDefaultValue(PROPERTY_LOOMMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_RPM, (long) 0);
        setDefaultValue(PROPERTY_EFFICIENCY, new BigDecimal(0));
        setDefaultValue(PROPERTY_WARPBREAKS, (long) 0);
        setDefaultValue(PROPERTY_WEFTBREAKS, (long) 0);
        setDefaultValue(PROPERTY_OTHERBREAKS, (long) 0);
        setDefaultValue(PROPERTY_VALIDATE, false);
        setDefaultValue(PROPERTY_IMPORTIT, false);
        setDefaultValue(PROPERTY_VALIDATED, false);
        setDefaultValue(PROPERTY_ISFLAG, false);
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

    public Date getLoomdate() {
        return (Date) get(PROPERTY_LOOMDATE);
    }

    public void setLoomdate(Date loomdate) {
        set(PROPERTY_LOOMDATE, loomdate);
    }

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public RCPLLoom getRcplLoom() {
        return (RCPLLoom) get(PROPERTY_RCPLLOOM);
    }

    public void setRcplLoom(RCPLLoom rcplLoom) {
        set(PROPERTY_RCPLLOOM, rcplLoom);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public Long getPpi() {
        return (Long) get(PROPERTY_PPI);
    }

    public void setPpi(Long ppi) {
        set(PROPERTY_PPI, ppi);
    }

    public Long getPicks() {
        return (Long) get(PROPERTY_PICKS);
    }

    public void setPicks(Long picks) {
        set(PROPERTY_PICKS, picks);
    }

    public BigDecimal getLoommts() {
        return (BigDecimal) get(PROPERTY_LOOMMTS);
    }

    public void setLoommts(BigDecimal loommts) {
        set(PROPERTY_LOOMMTS, loommts);
    }

    public Long getRpm() {
        return (Long) get(PROPERTY_RPM);
    }

    public void setRpm(Long rpm) {
        set(PROPERTY_RPM, rpm);
    }

    public BigDecimal getEfficiency() {
        return (BigDecimal) get(PROPERTY_EFFICIENCY);
    }

    public void setEfficiency(BigDecimal efficiency) {
        set(PROPERTY_EFFICIENCY, efficiency);
    }

    public Long getWarpbreaks() {
        return (Long) get(PROPERTY_WARPBREAKS);
    }

    public void setWarpbreaks(Long warpbreaks) {
        set(PROPERTY_WARPBREAKS, warpbreaks);
    }

    public Long getWeftbreaks() {
        return (Long) get(PROPERTY_WEFTBREAKS);
    }

    public void setWeftbreaks(Long weftbreaks) {
        set(PROPERTY_WEFTBREAKS, weftbreaks);
    }

    public Long getOtherbreaks() {
        return (Long) get(PROPERTY_OTHERBREAKS);
    }

    public void setOtherbreaks(Long otherbreaks) {
        set(PROPERTY_OTHERBREAKS, otherbreaks);
    }

    public Boolean isValidate() {
        return (Boolean) get(PROPERTY_VALIDATE);
    }

    public void setValidate(Boolean validate) {
        set(PROPERTY_VALIDATE, validate);
    }

    public Boolean isImportit() {
        return (Boolean) get(PROPERTY_IMPORTIT);
    }

    public void setImportit(Boolean importit) {
        set(PROPERTY_IMPORTIT, importit);
    }

    public Boolean isValidated() {
        return (Boolean) get(PROPERTY_VALIDATED);
    }

    public void setValidated(Boolean validated) {
        set(PROPERTY_VALIDATED, validated);
    }

    public Boolean isFlag() {
        return (Boolean) get(PROPERTY_ISFLAG);
    }

    public void setFlag(Boolean isflag) {
        set(PROPERTY_ISFLAG, isflag);
    }

}
