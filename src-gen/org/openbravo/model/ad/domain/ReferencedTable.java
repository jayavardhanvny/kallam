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
package org.openbravo.model.ad.domain;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADReferencedTable (stored in table AD_Ref_Table).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ReferencedTable extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Ref_Table";
    public static final String ENTITY_NAME = "ADReferencedTable";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_KEYCOLUMN = "keyColumn";
    public static final String PROPERTY_DISPLAYEDCOLUMN = "displayedColumn";
    public static final String PROPERTY_DISPLAYEDVALUE = "displayedValue";
    public static final String PROPERTY_SQLWHERECLAUSE = "sQLWhereClause";
    public static final String PROPERTY_SQLORDERBYCLAUSE = "sQLOrderByClause";
    public static final String PROPERTY_HQLWHERECLAUSE = "hqlwhereclause";
    public static final String PROPERTY_HQLORDERBYCLAUSE = "hqlorderbyclause";
    public static final String PROPERTY_REFERENCE = "reference";

    public ReferencedTable() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DISPLAYEDVALUE, false);
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

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
    }

    public Column getKeyColumn() {
        return (Column) get(PROPERTY_KEYCOLUMN);
    }

    public void setKeyColumn(Column keyColumn) {
        set(PROPERTY_KEYCOLUMN, keyColumn);
    }

    public Column getDisplayedColumn() {
        return (Column) get(PROPERTY_DISPLAYEDCOLUMN);
    }

    public void setDisplayedColumn(Column displayedColumn) {
        set(PROPERTY_DISPLAYEDCOLUMN, displayedColumn);
    }

    public Boolean isDisplayedValue() {
        return (Boolean) get(PROPERTY_DISPLAYEDVALUE);
    }

    public void setDisplayedValue(Boolean displayedValue) {
        set(PROPERTY_DISPLAYEDVALUE, displayedValue);
    }

    public String getSQLWhereClause() {
        return (String) get(PROPERTY_SQLWHERECLAUSE);
    }

    public void setSQLWhereClause(String sQLWhereClause) {
        set(PROPERTY_SQLWHERECLAUSE, sQLWhereClause);
    }

    public String getSQLOrderByClause() {
        return (String) get(PROPERTY_SQLORDERBYCLAUSE);
    }

    public void setSQLOrderByClause(String sQLOrderByClause) {
        set(PROPERTY_SQLORDERBYCLAUSE, sQLOrderByClause);
    }

    public String getHqlwhereclause() {
        return (String) get(PROPERTY_HQLWHERECLAUSE);
    }

    public void setHqlwhereclause(String hqlwhereclause) {
        set(PROPERTY_HQLWHERECLAUSE, hqlwhereclause);
    }

    public String getHqlorderbyclause() {
        return (String) get(PROPERTY_HQLORDERBYCLAUSE);
    }

    public void setHqlorderbyclause(String hqlorderbyclause) {
        set(PROPERTY_HQLORDERBYCLAUSE, hqlorderbyclause);
    }

    public Reference getReference() {
        return (Reference) get(PROPERTY_REFERENCE);
    }

    public void setReference(Reference reference) {
        set(PROPERTY_REFERENCE, reference);
    }

}
