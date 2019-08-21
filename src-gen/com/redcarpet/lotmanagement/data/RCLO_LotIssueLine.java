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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCLO_LotIssueLine (stored in table RCLO_LotIssueLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCLO_LotIssueLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCLO_LotIssueLine";
    public static final String ENTITY_NAME = "RCLO_LotIssueLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LOTISSUE = "lotIssue";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_NOOFBALES = "noOfBales";
    public static final String PROPERTY_ISSUEDKGS = "issuedKgs";
    public static final String PROPERTY_AVAILABLELOTS = "availableLots";
    public static final String PROPERTY_LOTRECEIPT = "lotReceipt";

    public RCLO_LotIssueLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_NOOFBALES, new BigDecimal(0));
        setDefaultValue(PROPERTY_ISSUEDKGS, new BigDecimal(0));
        setDefaultValue(PROPERTY_AVAILABLELOTS, new BigDecimal(0));
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

    public RCLO_LotIssue getLotIssue() {
        return (RCLO_LotIssue) get(PROPERTY_LOTISSUE);
    }

    public void setLotIssue(RCLO_LotIssue lotIssue) {
        set(PROPERTY_LOTISSUE, lotIssue);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public BigDecimal getNoOfBales() {
        return (BigDecimal) get(PROPERTY_NOOFBALES);
    }

    public void setNoOfBales(BigDecimal noOfBales) {
        set(PROPERTY_NOOFBALES, noOfBales);
    }

    public BigDecimal getIssuedKgs() {
        return (BigDecimal) get(PROPERTY_ISSUEDKGS);
    }

    public void setIssuedKgs(BigDecimal issuedKgs) {
        set(PROPERTY_ISSUEDKGS, issuedKgs);
    }

    public BigDecimal getAvailableLots() {
        return (BigDecimal) get(PROPERTY_AVAILABLELOTS);
    }

    public void setAvailableLots(BigDecimal availableLots) {
        set(PROPERTY_AVAILABLELOTS, availableLots);
    }

    public RCLO_LotReceipt getLotReceipt() {
        return (RCLO_LotReceipt) get(PROPERTY_LOTRECEIPT);
    }

    public void setLotReceipt(RCLO_LotReceipt lotReceipt) {
        set(PROPERTY_LOTRECEIPT, lotReceipt);
    }

}
