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
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.common.order.Order;
/**
 * Entity class for entity Epcg_CommissionAgent (stored in table Epcg_CommissionAgent).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgCommissionAgent extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_CommissionAgent";
    public static final String ENTITY_NAME = "Epcg_CommissionAgent";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_AGENTNAME = "agentname";
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_PANNO = "panno";
    public static final String PROPERTY_COMMRATE = "commrate";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CONTACTPERSONNAME = "contactPersonName";
    public static final String PROPERTY_PHONENO = "phoneNo";
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_FAX = "fax";
    public static final String PROPERTY_MOBILENO = "mobileNo";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_ACCOUNTNO = "accountNo";
    public static final String PROPERTY_BANKNAME = "bankName";
    public static final String PROPERTY_EPCGAGENTBRANCHLIST = "ePCGAgentbranchList";
    public static final String PROPERTY_ORDEREMEPCGCOMMISSIONAGENTIDLIST = "orderEMEpcgCommissionagentIDList";

    public EpcgCommissionAgent() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_EPCGAGENTBRANCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDEREMEPCGCOMMISSIONAGENTIDLIST, new ArrayList<Object>());
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

    public String getAgentname() {
        return (String) get(PROPERTY_AGENTNAME);
    }

    public void setAgentname(String agentname) {
        set(PROPERTY_AGENTNAME, agentname);
    }

    public Location getLocationAddress() {
        return (Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public String getPanno() {
        return (String) get(PROPERTY_PANNO);
    }

    public void setPanno(String panno) {
        set(PROPERTY_PANNO, panno);
    }

    public Long getCommrate() {
        return (Long) get(PROPERTY_COMMRATE);
    }

    public void setCommrate(Long commrate) {
        set(PROPERTY_COMMRATE, commrate);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getContactPersonName() {
        return (String) get(PROPERTY_CONTACTPERSONNAME);
    }

    public void setContactPersonName(String contactPersonName) {
        set(PROPERTY_CONTACTPERSONNAME, contactPersonName);
    }

    public String getPhoneNo() {
        return (String) get(PROPERTY_PHONENO);
    }

    public void setPhoneNo(String phoneNo) {
        set(PROPERTY_PHONENO, phoneNo);
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

    public String getAccountNo() {
        return (String) get(PROPERTY_ACCOUNTNO);
    }

    public void setAccountNo(String accountNo) {
        set(PROPERTY_ACCOUNTNO, accountNo);
    }

    public String getBankName() {
        return (String) get(PROPERTY_BANKNAME);
    }

    public void setBankName(String bankName) {
        set(PROPERTY_BANKNAME, bankName);
    }

    @SuppressWarnings("unchecked")
    public List<EPCG_Agentbranch> getEPCGAgentbranchList() {
        return (List<EPCG_Agentbranch>) get(PROPERTY_EPCGAGENTBRANCHLIST);
    }

    public void setEPCGAgentbranchList(List<EPCG_Agentbranch> ePCGAgentbranchList) {
        set(PROPERTY_EPCGAGENTBRANCHLIST, ePCGAgentbranchList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderEMEpcgCommissionagentIDList() {
        return (List<Order>) get(PROPERTY_ORDEREMEPCGCOMMISSIONAGENTIDLIST);
    }

    public void setOrderEMEpcgCommissionagentIDList(List<Order> orderEMEpcgCommissionagentIDList) {
        set(PROPERTY_ORDEREMEPCGCOMMISSIONAGENTIDLIST, orderEMEpcgCommissionagentIDList);
    }

}
