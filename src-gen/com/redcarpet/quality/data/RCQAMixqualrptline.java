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
package com.redcarpet.quality.data;

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
 * Entity class for entity RCQA_Mixqualrptline (stored in table RCQA_Mixqualrptline).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCQAMixqualrptline extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCQA_Mixqualrptline";
    public static final String ENTITY_NAME = "RCQA_Mixqualrptline";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MIXING = "mixing";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_NOOFBALES = "noofbales";
    public static final String PROPERTY_LOTNETQTY = "lotnetqty";
    public static final String PROPERTY_SL = "sL";
    public static final String PROPERTY_MIC = "mIC";
    public static final String PROPERTY_FSL = "fsl";
    public static final String PROPERTY_UR = "ur";
    public static final String PROPERTY_STR = "str";
    public static final String PROPERTY_MOIST = "moist";
    public static final String PROPERTY_RD = "rD";
    public static final String PROPERTY_PLUSB = "plusb";
    public static final String PROPERTY_CG = "cg";
    public static final String PROPERTY_FQI = "fQI";
    public static final String PROPERTY_MR = "mr";
    public static final String PROPERTY_REMARKS = "remarks";

    public RCQAMixqualrptline() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public RCQAMixqualityreport getMixing() {
        return (RCQAMixqualityreport) get(PROPERTY_MIXING);
    }

    public void setMixing(RCQAMixqualityreport mixing) {
        set(PROPERTY_MIXING, mixing);
    }

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public BigDecimal getNoofbales() {
        return (BigDecimal) get(PROPERTY_NOOFBALES);
    }

    public void setNoofbales(BigDecimal noofbales) {
        set(PROPERTY_NOOFBALES, noofbales);
    }

    public BigDecimal getLotnetqty() {
        return (BigDecimal) get(PROPERTY_LOTNETQTY);
    }

    public void setLotnetqty(BigDecimal lotnetqty) {
        set(PROPERTY_LOTNETQTY, lotnetqty);
    }

    public String getSL() {
        return (String) get(PROPERTY_SL);
    }

    public void setSL(String sL) {
        set(PROPERTY_SL, sL);
    }

    public String getMIC() {
        return (String) get(PROPERTY_MIC);
    }

    public void setMIC(String mIC) {
        set(PROPERTY_MIC, mIC);
    }

    public BigDecimal getFsl() {
        return (BigDecimal) get(PROPERTY_FSL);
    }

    public void setFsl(BigDecimal fsl) {
        set(PROPERTY_FSL, fsl);
    }

    public BigDecimal getUr() {
        return (BigDecimal) get(PROPERTY_UR);
    }

    public void setUr(BigDecimal ur) {
        set(PROPERTY_UR, ur);
    }

    public BigDecimal getStr() {
        return (BigDecimal) get(PROPERTY_STR);
    }

    public void setStr(BigDecimal str) {
        set(PROPERTY_STR, str);
    }

    public BigDecimal getMoist() {
        return (BigDecimal) get(PROPERTY_MOIST);
    }

    public void setMoist(BigDecimal moist) {
        set(PROPERTY_MOIST, moist);
    }

    public BigDecimal getRD() {
        return (BigDecimal) get(PROPERTY_RD);
    }

    public void setRD(BigDecimal rD) {
        set(PROPERTY_RD, rD);
    }

    public BigDecimal getPlusb() {
        return (BigDecimal) get(PROPERTY_PLUSB);
    }

    public void setPlusb(BigDecimal plusb) {
        set(PROPERTY_PLUSB, plusb);
    }

    public BigDecimal getCg() {
        return (BigDecimal) get(PROPERTY_CG);
    }

    public void setCg(BigDecimal cg) {
        set(PROPERTY_CG, cg);
    }

    public BigDecimal getFQI() {
        return (BigDecimal) get(PROPERTY_FQI);
    }

    public void setFQI(BigDecimal fQI) {
        set(PROPERTY_FQI, fQI);
    }

    public BigDecimal getMr() {
        return (BigDecimal) get(PROPERTY_MR);
    }

    public void setMr(BigDecimal mr) {
        set(PROPERTY_MR, mr);
    }

    public String getRemarks() {
        return (String) get(PROPERTY_REMARKS);
    }

    public void setRemarks(String remarks) {
        set(PROPERTY_REMARKS, remarks);
    }

}
