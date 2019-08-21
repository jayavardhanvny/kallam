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
package com.redcarpet.lotmanagement.data;

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
 * Entity class for entity RCLO_LotWiseQC (stored in table RCLO_LotWiseQC).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCLO_LotWiseQC extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCLO_LotWiseQC";
    public static final String ENTITY_NAME = "RCLO_LotWiseQC";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_MOVEMENTDATE = "movementDate";
    public static final String PROPERTY_LOTRECEIPT = "lotReceipt";
    public static final String PROPERTY_SI = "sI";
    public static final String PROPERTY_SL = "sL";
    public static final String PROPERTY_UR = "uR";
    public static final String PROPERTY_STRGTEX = "sTRGTex";
    public static final String PROPERTY_MIC = "mIC";
    public static final String PROPERTY_MOIST = "mOIST";
    public static final String PROPERTY_RD = "rD";
    public static final String PROPERTY_BPLUS = "bplus";
    public static final String PROPERTY_CG = "cG";
    public static final String PROPERTY_FQI = "fQI";
    public static final String PROPERTY_MR = "mR";

    public RCLO_LotWiseQC() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SI, new BigDecimal(0));
        setDefaultValue(PROPERTY_SL, new BigDecimal(0));
        setDefaultValue(PROPERTY_UR, new BigDecimal(0));
        setDefaultValue(PROPERTY_STRGTEX, new BigDecimal(0));
        setDefaultValue(PROPERTY_MIC, new BigDecimal(0));
        setDefaultValue(PROPERTY_MOIST, new BigDecimal(0));
        setDefaultValue(PROPERTY_RD, new BigDecimal(0));
        setDefaultValue(PROPERTY_BPLUS, new BigDecimal(0));
        setDefaultValue(PROPERTY_CG, new BigDecimal(0));
        setDefaultValue(PROPERTY_FQI, new BigDecimal(0));
        setDefaultValue(PROPERTY_MR, new BigDecimal(0));
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Date getMovementDate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementDate(Date movementDate) {
        set(PROPERTY_MOVEMENTDATE, movementDate);
    }

    public RCLO_LotReceipt getLotReceipt() {
        return (RCLO_LotReceipt) get(PROPERTY_LOTRECEIPT);
    }

    public void setLotReceipt(RCLO_LotReceipt lotReceipt) {
        set(PROPERTY_LOTRECEIPT, lotReceipt);
    }

    public BigDecimal getSI() {
        return (BigDecimal) get(PROPERTY_SI);
    }

    public void setSI(BigDecimal sI) {
        set(PROPERTY_SI, sI);
    }

    public BigDecimal getSL() {
        return (BigDecimal) get(PROPERTY_SL);
    }

    public void setSL(BigDecimal sL) {
        set(PROPERTY_SL, sL);
    }

    public BigDecimal getUR() {
        return (BigDecimal) get(PROPERTY_UR);
    }

    public void setUR(BigDecimal uR) {
        set(PROPERTY_UR, uR);
    }

    public BigDecimal getSTRGTex() {
        return (BigDecimal) get(PROPERTY_STRGTEX);
    }

    public void setSTRGTex(BigDecimal sTRGTex) {
        set(PROPERTY_STRGTEX, sTRGTex);
    }

    public BigDecimal getMIC() {
        return (BigDecimal) get(PROPERTY_MIC);
    }

    public void setMIC(BigDecimal mIC) {
        set(PROPERTY_MIC, mIC);
    }

    public BigDecimal getMOIST() {
        return (BigDecimal) get(PROPERTY_MOIST);
    }

    public void setMOIST(BigDecimal mOIST) {
        set(PROPERTY_MOIST, mOIST);
    }

    public BigDecimal getRD() {
        return (BigDecimal) get(PROPERTY_RD);
    }

    public void setRD(BigDecimal rD) {
        set(PROPERTY_RD, rD);
    }

    public BigDecimal getBplus() {
        return (BigDecimal) get(PROPERTY_BPLUS);
    }

    public void setBplus(BigDecimal bplus) {
        set(PROPERTY_BPLUS, bplus);
    }

    public BigDecimal getCG() {
        return (BigDecimal) get(PROPERTY_CG);
    }

    public void setCG(BigDecimal cG) {
        set(PROPERTY_CG, cG);
    }

    public BigDecimal getFQI() {
        return (BigDecimal) get(PROPERTY_FQI);
    }

    public void setFQI(BigDecimal fQI) {
        set(PROPERTY_FQI, fQI);
    }

    public BigDecimal getMR() {
        return (BigDecimal) get(PROPERTY_MR);
    }

    public void setMR(BigDecimal mR) {
        set(PROPERTY_MR, mR);
    }

}
