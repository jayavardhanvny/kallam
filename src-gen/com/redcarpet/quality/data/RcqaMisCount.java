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

import com.redcarpet.production.data.RCPRCount;
import com.redcarpet.production.data.RCPRShed;
import com.redcarpet.production.data.RCPR_CountMaster;

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
 * Entity class for entity RCQA_Mis_Count (stored in table Rcqa_Mis_Count).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RcqaMisCount extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rcqa_Mis_Count";
    public static final String ENTITY_NAME = "RCQA_Mis_Count";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MISDATE = "misdate";
    public static final String PROPERTY_RCPRSHED = "rcprShed";
    public static final String PROPERTY_RCPRCOUNT = "rcprCount";
    public static final String PROPERTY_ACTUALCOUNT = "actualcount";
    public static final String PROPERTY_CCV = "ccv";
    public static final String PROPERTY_STR = "str";
    public static final String PROPERTY_STRCV = "strcv";
    public static final String PROPERTY_CSP = "csp";
    public static final String PROPERTY_IPI = "ipi";
    public static final String PROPERTY_MISTYPE = "mistype";
    public static final String PROPERTY_RCPRCOUNTMASTER = "rcprCountmaster";

    public RcqaMisCount() {
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

    public Date getMisdate() {
        return (Date) get(PROPERTY_MISDATE);
    }

    public void setMisdate(Date misdate) {
        set(PROPERTY_MISDATE, misdate);
    }

    public RCPRShed getRcprShed() {
        return (RCPRShed) get(PROPERTY_RCPRSHED);
    }

    public void setRcprShed(RCPRShed rcprShed) {
        set(PROPERTY_RCPRSHED, rcprShed);
    }

    public RCPRCount getRcprCount() {
        return (RCPRCount) get(PROPERTY_RCPRCOUNT);
    }

    public void setRcprCount(RCPRCount rcprCount) {
        set(PROPERTY_RCPRCOUNT, rcprCount);
    }

    public BigDecimal getActualcount() {
        return (BigDecimal) get(PROPERTY_ACTUALCOUNT);
    }

    public void setActualcount(BigDecimal actualcount) {
        set(PROPERTY_ACTUALCOUNT, actualcount);
    }

    public BigDecimal getCcv() {
        return (BigDecimal) get(PROPERTY_CCV);
    }

    public void setCcv(BigDecimal ccv) {
        set(PROPERTY_CCV, ccv);
    }

    public BigDecimal getStr() {
        return (BigDecimal) get(PROPERTY_STR);
    }

    public void setStr(BigDecimal str) {
        set(PROPERTY_STR, str);
    }

    public BigDecimal getStrcv() {
        return (BigDecimal) get(PROPERTY_STRCV);
    }

    public void setStrcv(BigDecimal strcv) {
        set(PROPERTY_STRCV, strcv);
    }

    public BigDecimal getCsp() {
        return (BigDecimal) get(PROPERTY_CSP);
    }

    public void setCsp(BigDecimal csp) {
        set(PROPERTY_CSP, csp);
    }

    public BigDecimal getIpi() {
        return (BigDecimal) get(PROPERTY_IPI);
    }

    public void setIpi(BigDecimal ipi) {
        set(PROPERTY_IPI, ipi);
    }

    public String getMistype() {
        return (String) get(PROPERTY_MISTYPE);
    }

    public void setMistype(String mistype) {
        set(PROPERTY_MISTYPE, mistype);
    }

    public RCPR_CountMaster getRcprCountmaster() {
        return (RCPR_CountMaster) get(PROPERTY_RCPRCOUNTMASTER);
    }

    public void setRcprCountmaster(RCPR_CountMaster rcprCountmaster) {
        set(PROPERTY_RCPRCOUNTMASTER, rcprCountmaster);
    }

}
