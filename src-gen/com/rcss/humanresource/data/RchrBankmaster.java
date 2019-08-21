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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Rchr_Bankmaster (stored in table Rchr_Bankmaster).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrBankmaster extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Bankmaster";
    public static final String ENTITY_NAME = "Rchr_Bankmaster";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSLIST = "rchrBanksalpaymentsList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSAPPLIST = "rchrBanksalpaymentsappList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST = "rchrBanksalpaymentsapplnList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSLNLIST = "rchrBanksalpaymentslnList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSOLLIST = "rchrBanksalpaymentsolList";
    public static final String PROPERTY_RCHREMPBANKLIST = "rchrEmpBankList";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST = "rchrExbanksalpaymentsempList";

    public RchrBankmaster() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSAPPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSOLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPBANKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpayments> getRchrBanksalpaymentsList() {
        return (List<RchrBanksalpayments>) get(PROPERTY_RCHRBANKSALPAYMENTSLIST);
    }

    public void setRchrBanksalpaymentsList(List<RchrBanksalpayments> rchrBanksalpaymentsList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSLIST, rchrBanksalpaymentsList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsApp> getRchrBanksalpaymentsappList() {
        return (List<RchrBanksalpaymentsApp>) get(PROPERTY_RCHRBANKSALPAYMENTSAPPLIST);
    }

    public void setRchrBanksalpaymentsappList(List<RchrBanksalpaymentsApp> rchrBanksalpaymentsappList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSAPPLIST, rchrBanksalpaymentsappList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsappln> getRchrBanksalpaymentsapplnList() {
        return (List<RchrBanksalpaymentsappln>) get(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST);
    }

    public void setRchrBanksalpaymentsapplnList(List<RchrBanksalpaymentsappln> rchrBanksalpaymentsapplnList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, rchrBanksalpaymentsapplnList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsLn> getRchrBanksalpaymentslnList() {
        return (List<RchrBanksalpaymentsLn>) get(PROPERTY_RCHRBANKSALPAYMENTSLNLIST);
    }

    public void setRchrBanksalpaymentslnList(List<RchrBanksalpaymentsLn> rchrBanksalpaymentslnList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSLNLIST, rchrBanksalpaymentslnList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBankSalPaymentsOnline> getRchrBanksalpaymentsolList() {
        return (List<RchrBankSalPaymentsOnline>) get(PROPERTY_RCHRBANKSALPAYMENTSOLLIST);
    }

    public void setRchrBanksalpaymentsolList(List<RchrBankSalPaymentsOnline> rchrBanksalpaymentsolList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSOLLIST, rchrBanksalpaymentsolList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmpBank> getRchrEmpBankList() {
        return (List<RchrEmpBank>) get(PROPERTY_RCHREMPBANKLIST);
    }

    public void setRchrEmpBankList(List<RchrEmpBank> rchrEmpBankList) {
        set(PROPERTY_RCHREMPBANKLIST, rchrEmpBankList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpaymentsemp> getRchrExbanksalpaymentsempList() {
        return (List<RchrExbanksalpaymentsemp>) get(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST);
    }

    public void setRchrExbanksalpaymentsempList(List<RchrExbanksalpaymentsemp> rchrExbanksalpaymentsempList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST, rchrExbanksalpaymentsempList);
    }

}
