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

import com.redcarpet.payroll.data.RCPLLoom;
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
 * Entity class for entity Rchr_Rollwisedata (stored in table Rchr_Rollwisedata).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrRollwisedata extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Rollwisedata";
    public static final String ENTITY_NAME = "Rchr_Rollwisedata";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_ROLLMTS = "rollmts";
    public static final String PROPERTY_RCHRINSPECTIONSHEET = "rchrInspectionsheet";
    public static final String PROPERTY_RCHRROLLNOLIST = "rchrRollnolist";
    public static final String PROPERTY_ROLLDATE = "rolldate";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCPLLOOM = "rcplLoom";
    public static final String PROPERTY_RCHRPIECENOLIST = "rchrPiecenolist";
    public static final String PROPERTY_GLM = "glm";
    public static final String PROPERTY_GROSSWEIGHT = "grossweight";

    public RchrRollwisedata() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_GLM, new BigDecimal(0));
        setDefaultValue(PROPERTY_GROSSWEIGHT, new BigDecimal(0));
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

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public BigDecimal getRollmts() {
        return (BigDecimal) get(PROPERTY_ROLLMTS);
    }

    public void setRollmts(BigDecimal rollmts) {
        set(PROPERTY_ROLLMTS, rollmts);
    }

    public RCHR_Inspectionsheet getRchrInspectionsheet() {
        return (RCHR_Inspectionsheet) get(PROPERTY_RCHRINSPECTIONSHEET);
    }

    public void setRchrInspectionsheet(RCHR_Inspectionsheet rchrInspectionsheet) {
        set(PROPERTY_RCHRINSPECTIONSHEET, rchrInspectionsheet);
    }

    public RchrRollnolist getRchrRollnolist() {
        return (RchrRollnolist) get(PROPERTY_RCHRROLLNOLIST);
    }

    public void setRchrRollnolist(RchrRollnolist rchrRollnolist) {
        set(PROPERTY_RCHRROLLNOLIST, rchrRollnolist);
    }

    public Date getRolldate() {
        return (Date) get(PROPERTY_ROLLDATE);
    }

    public void setRolldate(Date rolldate) {
        set(PROPERTY_ROLLDATE, rolldate);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public RCPLLoom getRcplLoom() {
        return (RCPLLoom) get(PROPERTY_RCPLLOOM);
    }

    public void setRcplLoom(RCPLLoom rcplLoom) {
        set(PROPERTY_RCPLLOOM, rcplLoom);
    }

    public RCHRPiecenolist getRchrPiecenolist() {
        return (RCHRPiecenolist) get(PROPERTY_RCHRPIECENOLIST);
    }

    public void setRchrPiecenolist(RCHRPiecenolist rchrPiecenolist) {
        set(PROPERTY_RCHRPIECENOLIST, rchrPiecenolist);
    }

    public BigDecimal getGlm() {
        return (BigDecimal) get(PROPERTY_GLM);
    }

    public void setGlm(BigDecimal glm) {
        set(PROPERTY_GLM, glm);
    }

    public BigDecimal getGrossweight() {
        return (BigDecimal) get(PROPERTY_GROSSWEIGHT);
    }

    public void setGrossweight(BigDecimal grossweight) {
        set(PROPERTY_GROSSWEIGHT, grossweight);
    }

}
