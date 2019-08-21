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
package org.openbravo.model.ad.utility;

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
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity DataSetTable (stored in table AD_Dataset_Table).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class DataSetTable extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Dataset_Table";
    public static final String ENTITY_NAME = "DataSetTable";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DATASET = "dataset";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_INCLUDEALLCOLUMNS = "includeAllColumns";
    public static final String PROPERTY_SQLWHERECLAUSE = "sQLWhereClause";
    public static final String PROPERTY_EXCLUDEAUDITINFO = "excludeAuditInfo";
    public static final String PROPERTY_ISBUSINESSOBJECT = "isBusinessObject";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_SECONDARYWHERECLAUSE = "secondarywhereclause";
    public static final String PROPERTY_DATASETCOLUMNLIST = "dataSetColumnList";

    public DataSetTable() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_INCLUDEALLCOLUMNS, true);
        setDefaultValue(PROPERTY_EXCLUDEAUDITINFO, false);
        setDefaultValue(PROPERTY_ISBUSINESSOBJECT, false);
        setDefaultValue(PROPERTY_DATASETCOLUMNLIST, new ArrayList<Object>());
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

    public DataSet getDataset() {
        return (DataSet) get(PROPERTY_DATASET);
    }

    public void setDataset(DataSet dataset) {
        set(PROPERTY_DATASET, dataset);
    }

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
    }

    public Boolean isIncludeAllColumns() {
        return (Boolean) get(PROPERTY_INCLUDEALLCOLUMNS);
    }

    public void setIncludeAllColumns(Boolean includeAllColumns) {
        set(PROPERTY_INCLUDEALLCOLUMNS, includeAllColumns);
    }

    public String getSQLWhereClause() {
        return (String) get(PROPERTY_SQLWHERECLAUSE);
    }

    public void setSQLWhereClause(String sQLWhereClause) {
        set(PROPERTY_SQLWHERECLAUSE, sQLWhereClause);
    }

    public Boolean isExcludeAuditInfo() {
        return (Boolean) get(PROPERTY_EXCLUDEAUDITINFO);
    }

    public void setExcludeAuditInfo(Boolean excludeAuditInfo) {
        set(PROPERTY_EXCLUDEAUDITINFO, excludeAuditInfo);
    }

    public Boolean isBusinessObject() {
        return (Boolean) get(PROPERTY_ISBUSINESSOBJECT);
    }

    public void setBusinessObject(Boolean isBusinessObject) {
        set(PROPERTY_ISBUSINESSOBJECT, isBusinessObject);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public String getSecondarywhereclause() {
        return (String) get(PROPERTY_SECONDARYWHERECLAUSE);
    }

    public void setSecondarywhereclause(String secondarywhereclause) {
        set(PROPERTY_SECONDARYWHERECLAUSE, secondarywhereclause);
    }

    @SuppressWarnings("unchecked")
    public List<DataSetColumn> getDataSetColumnList() {
        return (List<DataSetColumn>) get(PROPERTY_DATASETCOLUMNLIST);
    }

    public void setDataSetColumnList(List<DataSetColumn> dataSetColumnList) {
        set(PROPERTY_DATASETCOLUMNLIST, dataSetColumnList);
    }

}
