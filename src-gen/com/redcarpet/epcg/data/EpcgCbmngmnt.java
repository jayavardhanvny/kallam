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
 * Entity class for entity Epcg_Cbmngmnt (stored in table Epcg_Cbmngmnt).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgCbmngmnt extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_Cbmngmnt";
    public static final String ENTITY_NAME = "Epcg_Cbmngmnt";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SDATE = "sdate";
    public static final String PROPERTY_EPCGDIVISION = "epcgDivision";
    public static final String PROPERTY_EPCGACCTMASTER = "epcgAcctmaster";
    public static final String PROPERTY_EPCGJACCOUNT = "epcgJaccount";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_VARONE = "varone";
    public static final String PROPERTY_VARTWO = "vartwo";
    public static final String PROPERTY_VARTHREE = "varthree";
    public static final String PROPERTY_DEBIT = "debit";
    public static final String PROPERTY_CREDIT = "credit";

    public EpcgCbmngmnt() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEBIT, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREDIT, new BigDecimal(0));
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

    public Date getSdate() {
        return (Date) get(PROPERTY_SDATE);
    }

    public void setSdate(Date sdate) {
        set(PROPERTY_SDATE, sdate);
    }

    public EPCGDivision getEpcgDivision() {
        return (EPCGDivision) get(PROPERTY_EPCGDIVISION);
    }

    public void setEpcgDivision(EPCGDivision epcgDivision) {
        set(PROPERTY_EPCGDIVISION, epcgDivision);
    }

    public EPCGAcctmaster getEpcgAcctmaster() {
        return (EPCGAcctmaster) get(PROPERTY_EPCGACCTMASTER);
    }

    public void setEpcgAcctmaster(EPCGAcctmaster epcgAcctmaster) {
        set(PROPERTY_EPCGACCTMASTER, epcgAcctmaster);
    }

    public EPCGJaccount getEpcgJaccount() {
        return (EPCGJaccount) get(PROPERTY_EPCGJACCOUNT);
    }

    public void setEpcgJaccount(EPCGJaccount epcgJaccount) {
        set(PROPERTY_EPCGJACCOUNT, epcgJaccount);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getVarone() {
        return (String) get(PROPERTY_VARONE);
    }

    public void setVarone(String varone) {
        set(PROPERTY_VARONE, varone);
    }

    public String getVartwo() {
        return (String) get(PROPERTY_VARTWO);
    }

    public void setVartwo(String vartwo) {
        set(PROPERTY_VARTWO, vartwo);
    }

    public String getVarthree() {
        return (String) get(PROPERTY_VARTHREE);
    }

    public void setVarthree(String varthree) {
        set(PROPERTY_VARTHREE, varthree);
    }

    public BigDecimal getDebit() {
        return (BigDecimal) get(PROPERTY_DEBIT);
    }

    public void setDebit(BigDecimal debit) {
        set(PROPERTY_DEBIT, debit);
    }

    public BigDecimal getCredit() {
        return (BigDecimal) get(PROPERTY_CREDIT);
    }

    public void setCredit(BigDecimal credit) {
        set(PROPERTY_CREDIT, credit);
    }

}
