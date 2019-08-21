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
package com.rcss.humanresource.data;

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
 * Entity class for entity RCHR_Inspcategoryline (stored in table RCHR_Inspcategoryline).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Inspcategoryline extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Inspcategoryline";
    public static final String ENTITY_NAME = "RCHR_Inspcategoryline";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTUAL = "actual";
    public static final String PROPERTY_A = "A";
    public static final String PROPERTY_AONE = "aone";
    public static final String PROPERTY_B = "B";
    public static final String PROPERTY_SL = "sl";
    public static final String PROPERTY_RCHRINSPECTIONSHEET = "rchrInspectionsheet";
    public static final String PROPERTY_FENTS = "fents";
    public static final String PROPERTY_RACKS = "racks";
    public static final String PROPERTY_CHANDI = "chandi";
    public static final String PROPERTY_FP = "fp";
    public static final String PROPERTY_BF = "bf";
    public static final String PROPERTY_RCHRINSPECTIONTYPE = "rchrInspectiontype";
    public static final String PROPERTY_TWOPOINTS = "twopoints";
    public static final String PROPERTY_FOURPOINTS = "fourpoints";

    public RCHR_Inspcategoryline() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_A, new BigDecimal(0));
        setDefaultValue(PROPERTY_AONE, new BigDecimal(0));
        setDefaultValue(PROPERTY_B, new BigDecimal(0));
        setDefaultValue(PROPERTY_SL, new BigDecimal(0));
        setDefaultValue(PROPERTY_FENTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_RACKS, (long) 0);
        setDefaultValue(PROPERTY_CHANDI, new BigDecimal(0));
        setDefaultValue(PROPERTY_FP, new BigDecimal(0));
        setDefaultValue(PROPERTY_BF, new BigDecimal(0));
        setDefaultValue(PROPERTY_TWOPOINTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_FOURPOINTS, new BigDecimal(0));
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

    public BigDecimal getActual() {
        return (BigDecimal) get(PROPERTY_ACTUAL);
    }

    public void setActual(BigDecimal actual) {
        set(PROPERTY_ACTUAL, actual);
    }

    public BigDecimal getA() {
        return (BigDecimal) get(PROPERTY_A);
    }

    public void setA(BigDecimal A) {
        set(PROPERTY_A, A);
    }

    public BigDecimal getAone() {
        return (BigDecimal) get(PROPERTY_AONE);
    }

    public void setAone(BigDecimal aone) {
        set(PROPERTY_AONE, aone);
    }

    public BigDecimal getB() {
        return (BigDecimal) get(PROPERTY_B);
    }

    public void setB(BigDecimal B) {
        set(PROPERTY_B, B);
    }

    public BigDecimal getSl() {
        return (BigDecimal) get(PROPERTY_SL);
    }

    public void setSl(BigDecimal sl) {
        set(PROPERTY_SL, sl);
    }

    public RCHR_Inspectionsheet getRchrInspectionsheet() {
        return (RCHR_Inspectionsheet) get(PROPERTY_RCHRINSPECTIONSHEET);
    }

    public void setRchrInspectionsheet(RCHR_Inspectionsheet rchrInspectionsheet) {
        set(PROPERTY_RCHRINSPECTIONSHEET, rchrInspectionsheet);
    }

    public BigDecimal getFents() {
        return (BigDecimal) get(PROPERTY_FENTS);
    }

    public void setFents(BigDecimal fents) {
        set(PROPERTY_FENTS, fents);
    }

    public Long getRacks() {
        return (Long) get(PROPERTY_RACKS);
    }

    public void setRacks(Long racks) {
        set(PROPERTY_RACKS, racks);
    }

    public BigDecimal getChandi() {
        return (BigDecimal) get(PROPERTY_CHANDI);
    }

    public void setChandi(BigDecimal chandi) {
        set(PROPERTY_CHANDI, chandi);
    }

    public BigDecimal getFp() {
        return (BigDecimal) get(PROPERTY_FP);
    }

    public void setFp(BigDecimal fp) {
        set(PROPERTY_FP, fp);
    }

    public BigDecimal getBf() {
        return (BigDecimal) get(PROPERTY_BF);
    }

    public void setBf(BigDecimal bf) {
        set(PROPERTY_BF, bf);
    }

    public RCHRInspectiontype getRchrInspectiontype() {
        return (RCHRInspectiontype) get(PROPERTY_RCHRINSPECTIONTYPE);
    }

    public void setRchrInspectiontype(RCHRInspectiontype rchrInspectiontype) {
        set(PROPERTY_RCHRINSPECTIONTYPE, rchrInspectiontype);
    }

    public BigDecimal getTwopoints() {
        return (BigDecimal) get(PROPERTY_TWOPOINTS);
    }

    public void setTwopoints(BigDecimal twopoints) {
        set(PROPERTY_TWOPOINTS, twopoints);
    }

    public BigDecimal getFourpoints() {
        return (BigDecimal) get(PROPERTY_FOURPOINTS);
    }

    public void setFourpoints(BigDecimal fourpoints) {
        set(PROPERTY_FOURPOINTS, fourpoints);
    }

}
