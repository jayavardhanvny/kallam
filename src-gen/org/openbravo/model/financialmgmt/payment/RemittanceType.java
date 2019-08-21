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
package org.openbravo.model.financialmgmt.payment;

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
 * Entity class for entity FinancialMgmtRemittanceType (stored in table C_Remittance_Type).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RemittanceType extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Remittance_Type";
    public static final String ENTITY_NAME = "FinancialMgmtRemittanceType";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_CONSOLIDATE = "consolidate";
    public static final String PROPERTY_FINALSTATUS = "finalStatus";
    public static final String PROPERTY_PAYMENTRULECONSOLIDATED = "paymentRuleConsolidated";
    public static final String PROPERTY_RETURNEDSTATUS = "returnedStatus";
    public static final String PROPERTY_RECEIPT = "receipt";
    public static final String PROPERTY_FINANCIALMGMTREMITTANCELIST = "financialMgmtRemittanceList";
    public static final String PROPERTY_FINANCIALMGMTREMITTANCEPARAMETERLIST = "financialMgmtRemittanceParameterList";

    public RemittanceType() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CONSOLIDATE, false);
        setDefaultValue(PROPERTY_RECEIPT, false);
        setDefaultValue(PROPERTY_FINANCIALMGMTREMITTANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTREMITTANCEPARAMETERLIST, new ArrayList<Object>());
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

    public Boolean isConsolidate() {
        return (Boolean) get(PROPERTY_CONSOLIDATE);
    }

    public void setConsolidate(Boolean consolidate) {
        set(PROPERTY_CONSOLIDATE, consolidate);
    }

    public String getFinalStatus() {
        return (String) get(PROPERTY_FINALSTATUS);
    }

    public void setFinalStatus(String finalStatus) {
        set(PROPERTY_FINALSTATUS, finalStatus);
    }

    public String getPaymentRuleConsolidated() {
        return (String) get(PROPERTY_PAYMENTRULECONSOLIDATED);
    }

    public void setPaymentRuleConsolidated(String paymentRuleConsolidated) {
        set(PROPERTY_PAYMENTRULECONSOLIDATED, paymentRuleConsolidated);
    }

    public String getReturnedStatus() {
        return (String) get(PROPERTY_RETURNEDSTATUS);
    }

    public void setReturnedStatus(String returnedStatus) {
        set(PROPERTY_RETURNEDSTATUS, returnedStatus);
    }

    public Boolean isReceipt() {
        return (Boolean) get(PROPERTY_RECEIPT);
    }

    public void setReceipt(Boolean receipt) {
        set(PROPERTY_RECEIPT, receipt);
    }

    @SuppressWarnings("unchecked")
    public List<Remittance> getFinancialMgmtRemittanceList() {
        return (List<Remittance>) get(PROPERTY_FINANCIALMGMTREMITTANCELIST);
    }

    public void setFinancialMgmtRemittanceList(List<Remittance> financialMgmtRemittanceList) {
        set(PROPERTY_FINANCIALMGMTREMITTANCELIST, financialMgmtRemittanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RemittanceParameter> getFinancialMgmtRemittanceParameterList() {
        return (List<RemittanceParameter>) get(PROPERTY_FINANCIALMGMTREMITTANCEPARAMETERLIST);
    }

    public void setFinancialMgmtRemittanceParameterList(List<RemittanceParameter> financialMgmtRemittanceParameterList) {
        set(PROPERTY_FINANCIALMGMTREMITTANCEPARAMETERLIST, financialMgmtRemittanceParameterList);
    }

}
