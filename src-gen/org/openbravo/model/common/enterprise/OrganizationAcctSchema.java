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
package org.openbravo.model.common.enterprise;

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
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.financialmgmt.accounting.OrganizationClosing;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
/**
 * Entity class for entity OrganizationAcctSchema (stored in table AD_Org_AcctSchema).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OrganizationAcctSchema extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Org_AcctSchema";
    public static final String ENTITY_NAME = "OrganizationAcctSchema";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_CREATENEWACCOUNTFORBUSINESSPARNTER = "createNewAccountForBusinessParnter";
    public static final String PROPERTY_SEQUANCEFORBUSINESSPARTNER = "sequanceForBusinessPartner";
    public static final String PROPERTY_CREATENEWACOUNTFORPRODUCT = "createNewAcountForProduct";
    public static final String PROPERTY_SEQUENCEFORPRODUCT = "sequenceForProduct";
    public static final String PROPERTY_ACCOUNTLENGTH = "accountLength";
    public static final String PROPERTY_SUBACCOUNTLENGTH = "subAccountLength";
    public static final String PROPERTY_ORGANIZATIONCLOSINGLIST = "organizationClosingList";

    public OrganizationAcctSchema() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_CREATENEWACCOUNTFORBUSINESSPARNTER, false);
        setDefaultValue(PROPERTY_CREATENEWACOUNTFORPRODUCT, false);
        setDefaultValue(PROPERTY_ORGANIZATIONCLOSINGLIST, new ArrayList<Object>());
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

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
    }

    public Boolean isCreateNewAccountForBusinessParnter() {
        return (Boolean) get(PROPERTY_CREATENEWACCOUNTFORBUSINESSPARNTER);
    }

    public void setCreateNewAccountForBusinessParnter(Boolean createNewAccountForBusinessParnter) {
        set(PROPERTY_CREATENEWACCOUNTFORBUSINESSPARNTER, createNewAccountForBusinessParnter);
    }

    public Sequence getSequanceForBusinessPartner() {
        return (Sequence) get(PROPERTY_SEQUANCEFORBUSINESSPARTNER);
    }

    public void setSequanceForBusinessPartner(Sequence sequanceForBusinessPartner) {
        set(PROPERTY_SEQUANCEFORBUSINESSPARTNER, sequanceForBusinessPartner);
    }

    public Boolean isCreateNewAcountForProduct() {
        return (Boolean) get(PROPERTY_CREATENEWACOUNTFORPRODUCT);
    }

    public void setCreateNewAcountForProduct(Boolean createNewAcountForProduct) {
        set(PROPERTY_CREATENEWACOUNTFORPRODUCT, createNewAcountForProduct);
    }

    public Sequence getSequenceForProduct() {
        return (Sequence) get(PROPERTY_SEQUENCEFORPRODUCT);
    }

    public void setSequenceForProduct(Sequence sequenceForProduct) {
        set(PROPERTY_SEQUENCEFORPRODUCT, sequenceForProduct);
    }

    public Long getAccountLength() {
        return (Long) get(PROPERTY_ACCOUNTLENGTH);
    }

    public void setAccountLength(Long accountLength) {
        set(PROPERTY_ACCOUNTLENGTH, accountLength);
    }

    public Long getSubAccountLength() {
        return (Long) get(PROPERTY_SUBACCOUNTLENGTH);
    }

    public void setSubAccountLength(Long subAccountLength) {
        set(PROPERTY_SUBACCOUNTLENGTH, subAccountLength);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationClosing> getOrganizationClosingList() {
        return (List<OrganizationClosing>) get(PROPERTY_ORGANIZATIONCLOSINGLIST);
    }

    public void setOrganizationClosingList(List<OrganizationClosing> organizationClosingList) {
        set(PROPERTY_ORGANIZATIONCLOSINGLIST, organizationClosingList);
    }

}
