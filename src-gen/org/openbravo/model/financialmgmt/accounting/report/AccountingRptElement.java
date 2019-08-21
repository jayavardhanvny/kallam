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
package org.openbravo.model.financialmgmt.accounting.report;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
/**
 * Entity class for entity FinancialMgmtAccountingRptElement (stored in table AD_AccountingRpt_Element).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AccountingRptElement extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_AccountingRpt_Element";
    public static final String ENTITY_NAME = "FinancialMgmtAccountingRptElement";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_SHOWN = "shown";
    public static final String PROPERTY_FILTEREDBYORGANIZATION = "filteredByOrganization";
    public static final String PROPERTY_REPORTINGINTERVAL = "reportingInterval";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_REPORT = "report";
    public static final String PROPERTY_REPORTTYPE = "reportType";
    public static final String PROPERTY_YEARINITIALBALANCE = "yearInitialBalance";
    public static final String PROPERTY_CONSIDERZERO = "considerZero";

    public AccountingRptElement() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, true);
        setDefaultValue(PROPERTY_SHOWN, true);
        setDefaultValue(PROPERTY_FILTEREDBYORGANIZATION, false);
        setDefaultValue(PROPERTY_REPORT, false);
        setDefaultValue(PROPERTY_REPORTTYPE, "dd");
        setDefaultValue(PROPERTY_YEARINITIALBALANCE, false);
        setDefaultValue(PROPERTY_CONSIDERZERO, "NEVER");
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public Boolean isShown() {
        return (Boolean) get(PROPERTY_SHOWN);
    }

    public void setShown(Boolean shown) {
        set(PROPERTY_SHOWN, shown);
    }

    public Boolean isFilteredByOrganization() {
        return (Boolean) get(PROPERTY_FILTEREDBYORGANIZATION);
    }

    public void setFilteredByOrganization(Boolean filteredByOrganization) {
        set(PROPERTY_FILTEREDBYORGANIZATION, filteredByOrganization);
    }

    public String getReportingInterval() {
        return (String) get(PROPERTY_REPORTINGINTERVAL);
    }

    public void setReportingInterval(String reportingInterval) {
        set(PROPERTY_REPORTINGINTERVAL, reportingInterval);
    }

    public ElementValue getAccount() {
        return (ElementValue) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(ElementValue account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
    }

    public Boolean isReport() {
        return (Boolean) get(PROPERTY_REPORT);
    }

    public void setReport(Boolean report) {
        set(PROPERTY_REPORT, report);
    }

    public String getReportType() {
        return (String) get(PROPERTY_REPORTTYPE);
    }

    public void setReportType(String reportType) {
        set(PROPERTY_REPORTTYPE, reportType);
    }

    public Boolean isYearInitialBalance() {
        return (Boolean) get(PROPERTY_YEARINITIALBALANCE);
    }

    public void setYearInitialBalance(Boolean yearInitialBalance) {
        set(PROPERTY_YEARINITIALBALANCE, yearInitialBalance);
    }

    public String getConsiderZero() {
        return (String) get(PROPERTY_CONSIDERZERO);
    }

    public void setConsiderZero(String considerZero) {
        set(PROPERTY_CONSIDERZERO, considerZero);
    }

}
