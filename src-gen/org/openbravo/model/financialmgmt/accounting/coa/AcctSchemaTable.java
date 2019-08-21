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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.AcctSchemaTableDocType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.ADCreatefactTemplate;
/**
 * Entity class for entity FinancialMgmtAcctSchemaTable (stored in table C_AcctSchema_Table).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AcctSchemaTable extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_AcctSchema_Table";
    public static final String ENTITY_NAME = "FinancialMgmtAcctSchemaTable";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CREATEFACTTEMPLATE = "createfactTemplate";
    public static final String PROPERTY_SQLDESCRIPTION = "sQLDescription";
    public static final String PROPERTY_ISBACKGROUNDDISABLED = "isBackgroundDisabled";
    public static final String PROPERTY_ACCTSCHEMATABLEDOCTYPELIST = "acctSchemaTableDocTypeList";

    public AcctSchemaTable() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISBACKGROUNDDISABLED, false);
        setDefaultValue(PROPERTY_ACCTSCHEMATABLEDOCTYPELIST, new ArrayList<Object>());
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

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
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

    public ADCreatefactTemplate getCreatefactTemplate() {
        return (ADCreatefactTemplate) get(PROPERTY_CREATEFACTTEMPLATE);
    }

    public void setCreatefactTemplate(ADCreatefactTemplate createfactTemplate) {
        set(PROPERTY_CREATEFACTTEMPLATE, createfactTemplate);
    }

    public String getSQLDescription() {
        return (String) get(PROPERTY_SQLDESCRIPTION);
    }

    public void setSQLDescription(String sQLDescription) {
        set(PROPERTY_SQLDESCRIPTION, sQLDescription);
    }

    public Boolean isBackgroundDisabled() {
        return (Boolean) get(PROPERTY_ISBACKGROUNDDISABLED);
    }

    public void setBackgroundDisabled(Boolean isBackgroundDisabled) {
        set(PROPERTY_ISBACKGROUNDDISABLED, isBackgroundDisabled);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaTableDocType> getAcctSchemaTableDocTypeList() {
        return (List<AcctSchemaTableDocType>) get(PROPERTY_ACCTSCHEMATABLEDOCTYPELIST);
    }

    public void setAcctSchemaTableDocTypeList(List<AcctSchemaTableDocType> acctSchemaTableDocTypeList) {
        set(PROPERTY_ACCTSCHEMATABLEDOCTYPELIST, acctSchemaTableDocTypeList);
    }

}
