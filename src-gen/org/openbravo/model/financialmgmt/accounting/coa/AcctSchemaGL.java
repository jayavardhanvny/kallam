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
package org.openbravo.model.financialmgmt.accounting.coa;

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
 * Entity class for entity FinancialMgmtAcctSchemaGL (stored in table C_AcctSchema_GL).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AcctSchemaGL extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_AcctSchema_GL";
    public static final String ENTITY_NAME = "FinancialMgmtAcctSchemaGL";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SUSPENSEBALANCINGUSE = "suspenseBalancingUse";
    public static final String PROPERTY_SUSPENSEBALANCING = "suspenseBalancing";
    public static final String PROPERTY_SUSPENSEERRORUSE = "suspenseErrorUse";
    public static final String PROPERTY_SUSPENSEERROR = "suspenseError";
    public static final String PROPERTY_CURRENCYBALANCINGUSE = "currencyBalancingUse";
    public static final String PROPERTY_CURRENCYBALANCINGACCT = "currencyBalancingAcct";
    public static final String PROPERTY_RETAINEDEARNING = "retainedEarning";
    public static final String PROPERTY_INCOMESUMMARY = "incomeSummary";
    public static final String PROPERTY_DUETOINTERCOMPANY = "dueToIntercompany";
    public static final String PROPERTY_DUEFROMINTERCOMPANY = "dueFromIntercompany";
    public static final String PROPERTY_PPVOFFSET = "pPVOffset";
    public static final String PROPERTY_CFSORDERACCOUNT = "cFSOrderAccount";
    public static final String PROPERTY_CREATECLOSING = "createClosing";

    public AcctSchemaGL() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUSPENSEBALANCINGUSE, false);
        setDefaultValue(PROPERTY_SUSPENSEERRORUSE, false);
        setDefaultValue(PROPERTY_CURRENCYBALANCINGUSE, false);
        setDefaultValue(PROPERTY_CREATECLOSING, true);
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

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
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

    public Boolean isSuspenseBalancingUse() {
        return (Boolean) get(PROPERTY_SUSPENSEBALANCINGUSE);
    }

    public void setSuspenseBalancingUse(Boolean suspenseBalancingUse) {
        set(PROPERTY_SUSPENSEBALANCINGUSE, suspenseBalancingUse);
    }

    public AccountingCombination getSuspenseBalancing() {
        return (AccountingCombination) get(PROPERTY_SUSPENSEBALANCING);
    }

    public void setSuspenseBalancing(AccountingCombination suspenseBalancing) {
        set(PROPERTY_SUSPENSEBALANCING, suspenseBalancing);
    }

    public Boolean isSuspenseErrorUse() {
        return (Boolean) get(PROPERTY_SUSPENSEERRORUSE);
    }

    public void setSuspenseErrorUse(Boolean suspenseErrorUse) {
        set(PROPERTY_SUSPENSEERRORUSE, suspenseErrorUse);
    }

    public AccountingCombination getSuspenseError() {
        return (AccountingCombination) get(PROPERTY_SUSPENSEERROR);
    }

    public void setSuspenseError(AccountingCombination suspenseError) {
        set(PROPERTY_SUSPENSEERROR, suspenseError);
    }

    public Boolean isCurrencyBalancingUse() {
        return (Boolean) get(PROPERTY_CURRENCYBALANCINGUSE);
    }

    public void setCurrencyBalancingUse(Boolean currencyBalancingUse) {
        set(PROPERTY_CURRENCYBALANCINGUSE, currencyBalancingUse);
    }

    public AccountingCombination getCurrencyBalancingAcct() {
        return (AccountingCombination) get(PROPERTY_CURRENCYBALANCINGACCT);
    }

    public void setCurrencyBalancingAcct(AccountingCombination currencyBalancingAcct) {
        set(PROPERTY_CURRENCYBALANCINGACCT, currencyBalancingAcct);
    }

    public AccountingCombination getRetainedEarning() {
        return (AccountingCombination) get(PROPERTY_RETAINEDEARNING);
    }

    public void setRetainedEarning(AccountingCombination retainedEarning) {
        set(PROPERTY_RETAINEDEARNING, retainedEarning);
    }

    public AccountingCombination getIncomeSummary() {
        return (AccountingCombination) get(PROPERTY_INCOMESUMMARY);
    }

    public void setIncomeSummary(AccountingCombination incomeSummary) {
        set(PROPERTY_INCOMESUMMARY, incomeSummary);
    }

    public AccountingCombination getDueToIntercompany() {
        return (AccountingCombination) get(PROPERTY_DUETOINTERCOMPANY);
    }

    public void setDueToIntercompany(AccountingCombination dueToIntercompany) {
        set(PROPERTY_DUETOINTERCOMPANY, dueToIntercompany);
    }

    public AccountingCombination getDueFromIntercompany() {
        return (AccountingCombination) get(PROPERTY_DUEFROMINTERCOMPANY);
    }

    public void setDueFromIntercompany(AccountingCombination dueFromIntercompany) {
        set(PROPERTY_DUEFROMINTERCOMPANY, dueFromIntercompany);
    }

    public AccountingCombination getPPVOffset() {
        return (AccountingCombination) get(PROPERTY_PPVOFFSET);
    }

    public void setPPVOffset(AccountingCombination pPVOffset) {
        set(PROPERTY_PPVOFFSET, pPVOffset);
    }

    public AccountingCombination getCFSOrderAccount() {
        return (AccountingCombination) get(PROPERTY_CFSORDERACCOUNT);
    }

    public void setCFSOrderAccount(AccountingCombination cFSOrderAccount) {
        set(PROPERTY_CFSORDERACCOUNT, cFSOrderAccount);
    }

    public Boolean isCreateClosing() {
        return (Boolean) get(PROPERTY_CREATECLOSING);
    }

    public void setCreateClosing(Boolean createClosing) {
        set(PROPERTY_CREATECLOSING, createClosing);
    }

}
