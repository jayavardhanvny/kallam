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
 * Entity class for entity EPCG_Agentbranch (stored in table EPCG_Agentbranch).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EPCG_Agentbranch extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "EPCG_Agentbranch";
    public static final String ENTITY_NAME = "EPCG_Agentbranch";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EPCGCOMMISSIONAGENT = "epcgCommissionagent";
    public static final String PROPERTY_BRANCHNO = "branchNo";
    public static final String PROPERTY_BRANCHNAME = "branchname";
    public static final String PROPERTY_BRANCHCONTACTPERSON = "branchContactPerson";
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_FAX = "fax";
    public static final String PROPERTY_MOBILENO = "mobileNo";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_BRANCHLOCATION = "branchLocation";
    public static final String PROPERTY_BRANCHBANKACCOUNTNO = "branchBankAccountNo";
    public static final String PROPERTY_BRANCHBANKNAME = "branchBankName";
    public static final String PROPERTY_BRANCHSERVICETAXNO = "branchServiceTaxNo";

    public EPCG_Agentbranch() {
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

    public EpcgCommissionAgent getEpcgCommissionagent() {
        return (EpcgCommissionAgent) get(PROPERTY_EPCGCOMMISSIONAGENT);
    }

    public void setEpcgCommissionagent(EpcgCommissionAgent epcgCommissionagent) {
        set(PROPERTY_EPCGCOMMISSIONAGENT, epcgCommissionagent);
    }

    public String getBranchNo() {
        return (String) get(PROPERTY_BRANCHNO);
    }

    public void setBranchNo(String branchNo) {
        set(PROPERTY_BRANCHNO, branchNo);
    }

    public String getBranchname() {
        return (String) get(PROPERTY_BRANCHNAME);
    }

    public void setBranchname(String branchname) {
        set(PROPERTY_BRANCHNAME, branchname);
    }

    public String getBranchContactPerson() {
        return (String) get(PROPERTY_BRANCHCONTACTPERSON);
    }

    public void setBranchContactPerson(String branchContactPerson) {
        set(PROPERTY_BRANCHCONTACTPERSON, branchContactPerson);
    }

    public String getDesignation() {
        return (String) get(PROPERTY_DESIGNATION);
    }

    public void setDesignation(String designation) {
        set(PROPERTY_DESIGNATION, designation);
    }

    public String getFax() {
        return (String) get(PROPERTY_FAX);
    }

    public void setFax(String fax) {
        set(PROPERTY_FAX, fax);
    }

    public String getMobileNo() {
        return (String) get(PROPERTY_MOBILENO);
    }

    public void setMobileNo(String mobileNo) {
        set(PROPERTY_MOBILENO, mobileNo);
    }

    public String getEmail() {
        return (String) get(PROPERTY_EMAIL);
    }

    public void setEmail(String email) {
        set(PROPERTY_EMAIL, email);
    }

    public String getBranchLocation() {
        return (String) get(PROPERTY_BRANCHLOCATION);
    }

    public void setBranchLocation(String branchLocation) {
        set(PROPERTY_BRANCHLOCATION, branchLocation);
    }

    public String getBranchBankAccountNo() {
        return (String) get(PROPERTY_BRANCHBANKACCOUNTNO);
    }

    public void setBranchBankAccountNo(String branchBankAccountNo) {
        set(PROPERTY_BRANCHBANKACCOUNTNO, branchBankAccountNo);
    }

    public String getBranchBankName() {
        return (String) get(PROPERTY_BRANCHBANKNAME);
    }

    public void setBranchBankName(String branchBankName) {
        set(PROPERTY_BRANCHBANKNAME, branchBankName);
    }

    public String getBranchServiceTaxNo() {
        return (String) get(PROPERTY_BRANCHSERVICETAXNO);
    }

    public void setBranchServiceTaxNo(String branchServiceTaxNo) {
        set(PROPERTY_BRANCHSERVICETAXNO, branchServiceTaxNo);
    }

}
