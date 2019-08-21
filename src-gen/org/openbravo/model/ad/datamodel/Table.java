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
package org.openbravo.model.ad.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_FinaccTransactionV;
import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.APRM_Reconciliation_v;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.application.Note;
import org.openbravo.model.ad.access.TableAccess;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.domain.ReferencedTable;
import org.openbravo.model.ad.domain.Selector;
import org.openbravo.model.ad.module.DataPackage;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.DimensionMapping;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.ad.ui.Window;
import org.openbravo.model.ad.utility.Attachment;
import org.openbravo.model.ad.utility.AuditTrail;
import org.openbravo.model.ad.utility.DataSetTable;
import org.openbravo.model.ad.utility.ReferenceDataStore;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.interaction.EmailInteraction;
import org.openbravo.model.financialmgmt.accounting.ADCreatefactTemplate;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchemaTable;
import org.openbravo.model.materialmgmt.onhandquantity.StockAux;
import org.openbravo.service.datasource.DataSource;
import org.openbravo.service.datasource.DatasourceField;
/**
 * Entity class for entity ADTable (stored in table AD_Table).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Table extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Table";
    public static final String ENTITY_NAME = "ADTable";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_DBTABLENAME = "dBTableName";
    public static final String PROPERTY_JAVACLASSNAME = "javaClassName";
    public static final String PROPERTY_VIEW = "view";
    public static final String PROPERTY_DATAACCESSLEVEL = "dataAccessLevel";
    public static final String PROPERTY_WINDOW = "window";
    public static final String PROPERTY_SECURITYENABLED = "securityEnabled";
    public static final String PROPERTY_DELETABLERECORDS = "deletableRecords";
    public static final String PROPERTY_HIGHVOLUME = "highVolume";
    public static final String PROPERTY_IMPORTTABLE = "importTable";
    public static final String PROPERTY_MAINTAINCHANGELOG = "maintainChangeLog";
    public static final String PROPERTY_POWINDOW = "pOWindow";
    public static final String PROPERTY_DEFAULTACCOUNT = "defaultAccount";
    public static final String PROPERTY_SQLRECORDIDENTIFIER = "sQLRecordIdentifier";
    public static final String PROPERTY_DEVELOPMENTSTATUS = "developmentStatus";
    public static final String PROPERTY_DATAPACKAGE = "dataPackage";
    public static final String PROPERTY_TREETYPE = "treeType";
    public static final String PROPERTY_ACCTDATECOLUMN = "acctdateColumn";
    public static final String PROPERTY_ACCTCLASSNAME = "acctclassname";
    public static final String PROPERTY_ISFULLYAUDITED = "isFullyAudited";
    public static final String PROPERTY_DATAORIGINTYPE = "dataOriginType";
    public static final String PROPERTY_ISAUDITINSERTS = "isAuditInserts";
    public static final String PROPERTY_OBSERDSDATASOURCE = "obserdsDatasource";
    public static final String PROPERTY_ADATTACHMENTLIST = "aDAttachmentList";
    public static final String PROPERTY_ADCOLUMNLIST = "aDColumnList";
    public static final String PROPERTY_ADDIMENSIONMAPPINGLIST = "aDDimensionMappingList";
    public static final String PROPERTY_ADREFERENCEDTABLELIST = "aDReferencedTableList";
    public static final String PROPERTY_ADSELECTORLIST = "aDSelectorList";
    public static final String PROPERTY_ADTABLIST = "aDTabList";
    public static final String PROPERTY_ADTABLEACCESSLIST = "aDTableAccessList";
    public static final String PROPERTY_AUDITTRAILLIST = "auditTrailList";
    public static final String PROPERTY_CREATEFACTTEMPLATELIST = "createFactTemplateList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONVFORCEDTABLEIDLIST = "aPRMFinaccTransactionVForcedTableIDList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_APRMRECONCILIATIONFORCEDTABLEIDLIST = "aPRMReconciliationForcedTableIDList";
    public static final String PROPERTY_DATASETTABLELIST = "dataSetTableList";
    public static final String PROPERTY_DOCUMENTTYPELIST = "documentTypeList";
    public static final String PROPERTY_EMAILINTERACTIONLIST = "emailInteractionList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMATABLELIST = "financialMgmtAcctSchemaTableList";
    public static final String PROPERTY_MATERIALMGMTSTORAGESTOCKAUXLIST = "materialMgmtStorageStockAuxList";
    public static final String PROPERTY_OBSERDSDATASOURCELIST = "oBSERDSDatasourceList";
    public static final String PROPERTY_OBSERDSDATASOURCEFIELDLIST = "oBSERDSDatasourceFieldList";
    public static final String PROPERTY_OBUIAPPNOTELIST = "oBUIAPPNoteList";
    public static final String PROPERTY_OBUISELSELECTORLIST = "oBUISELSelectorList";
    public static final String PROPERTY_REFERENCEDATASTORELIST = "referenceDataStoreList";

    public Table() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_VIEW, false);
        setDefaultValue(PROPERTY_DATAACCESSLEVEL, "4");
        setDefaultValue(PROPERTY_SECURITYENABLED, false);
        setDefaultValue(PROPERTY_DELETABLERECORDS, true);
        setDefaultValue(PROPERTY_HIGHVOLUME, false);
        setDefaultValue(PROPERTY_IMPORTTABLE, false);
        setDefaultValue(PROPERTY_MAINTAINCHANGELOG, false);
        setDefaultValue(PROPERTY_DEFAULTACCOUNT, false);
        setDefaultValue(PROPERTY_DEVELOPMENTSTATUS, "RE");
        setDefaultValue(PROPERTY_ISFULLYAUDITED, false);
        setDefaultValue(PROPERTY_DATAORIGINTYPE, "Table");
        setDefaultValue(PROPERTY_ISAUDITINSERTS, true);
        setDefaultValue(PROPERTY_ADATTACHMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADDIMENSIONMAPPINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADREFERENCEDTABLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADSELECTORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLEACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AUDITTRAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CREATEFACTTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONVFORCEDTABLEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMRECONCILIATIONFORCEDTABLEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATASETTABLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMAILINTERACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMATABLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSTORAGESTOCKAUXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBSERDSDATASOURCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBSERDSDATASOURCEFIELDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPNOTELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REFERENCEDATASTORELIST, new ArrayList<Object>());
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getHelpComment() {
        return (String) get(PROPERTY_HELPCOMMENT);
    }

    public void setHelpComment(String helpComment) {
        set(PROPERTY_HELPCOMMENT, helpComment);
    }

    public String getDBTableName() {
        return (String) get(PROPERTY_DBTABLENAME);
    }

    public void setDBTableName(String dBTableName) {
        set(PROPERTY_DBTABLENAME, dBTableName);
    }

    public String getJavaClassName() {
        return (String) get(PROPERTY_JAVACLASSNAME);
    }

    public void setJavaClassName(String javaClassName) {
        set(PROPERTY_JAVACLASSNAME, javaClassName);
    }

    public Boolean isView() {
        return (Boolean) get(PROPERTY_VIEW);
    }

    public void setView(Boolean view) {
        set(PROPERTY_VIEW, view);
    }

    public String getDataAccessLevel() {
        return (String) get(PROPERTY_DATAACCESSLEVEL);
    }

    public void setDataAccessLevel(String dataAccessLevel) {
        set(PROPERTY_DATAACCESSLEVEL, dataAccessLevel);
    }

    public Window getWindow() {
        return (Window) get(PROPERTY_WINDOW);
    }

    public void setWindow(Window window) {
        set(PROPERTY_WINDOW, window);
    }

    public Boolean isSecurityEnabled() {
        return (Boolean) get(PROPERTY_SECURITYENABLED);
    }

    public void setSecurityEnabled(Boolean securityEnabled) {
        set(PROPERTY_SECURITYENABLED, securityEnabled);
    }

    public Boolean isDeletableRecords() {
        return (Boolean) get(PROPERTY_DELETABLERECORDS);
    }

    public void setDeletableRecords(Boolean deletableRecords) {
        set(PROPERTY_DELETABLERECORDS, deletableRecords);
    }

    public Boolean isHighVolume() {
        return (Boolean) get(PROPERTY_HIGHVOLUME);
    }

    public void setHighVolume(Boolean highVolume) {
        set(PROPERTY_HIGHVOLUME, highVolume);
    }

    public Boolean isImportTable() {
        return (Boolean) get(PROPERTY_IMPORTTABLE);
    }

    public void setImportTable(Boolean importTable) {
        set(PROPERTY_IMPORTTABLE, importTable);
    }

    public Boolean isMaintainChangeLog() {
        return (Boolean) get(PROPERTY_MAINTAINCHANGELOG);
    }

    public void setMaintainChangeLog(Boolean maintainChangeLog) {
        set(PROPERTY_MAINTAINCHANGELOG, maintainChangeLog);
    }

    public Window getPOWindow() {
        return (Window) get(PROPERTY_POWINDOW);
    }

    public void setPOWindow(Window pOWindow) {
        set(PROPERTY_POWINDOW, pOWindow);
    }

    public Boolean isDefaultAccount() {
        return (Boolean) get(PROPERTY_DEFAULTACCOUNT);
    }

    public void setDefaultAccount(Boolean defaultAccount) {
        set(PROPERTY_DEFAULTACCOUNT, defaultAccount);
    }

    public String getSQLRecordIdentifier() {
        return (String) get(PROPERTY_SQLRECORDIDENTIFIER);
    }

    public void setSQLRecordIdentifier(String sQLRecordIdentifier) {
        set(PROPERTY_SQLRECORDIDENTIFIER, sQLRecordIdentifier);
    }

    public String getDevelopmentStatus() {
        return (String) get(PROPERTY_DEVELOPMENTSTATUS);
    }

    public void setDevelopmentStatus(String developmentStatus) {
        set(PROPERTY_DEVELOPMENTSTATUS, developmentStatus);
    }

    public DataPackage getDataPackage() {
        return (DataPackage) get(PROPERTY_DATAPACKAGE);
    }

    public void setDataPackage(DataPackage dataPackage) {
        set(PROPERTY_DATAPACKAGE, dataPackage);
    }

    public String getTreeType() {
        return (String) get(PROPERTY_TREETYPE);
    }

    public void setTreeType(String treeType) {
        set(PROPERTY_TREETYPE, treeType);
    }

    public Column getAcctdateColumn() {
        return (Column) get(PROPERTY_ACCTDATECOLUMN);
    }

    public void setAcctdateColumn(Column acctdateColumn) {
        set(PROPERTY_ACCTDATECOLUMN, acctdateColumn);
    }

    public String getAcctclassname() {
        return (String) get(PROPERTY_ACCTCLASSNAME);
    }

    public void setAcctclassname(String acctclassname) {
        set(PROPERTY_ACCTCLASSNAME, acctclassname);
    }

    public Boolean isFullyAudited() {
        return (Boolean) get(PROPERTY_ISFULLYAUDITED);
    }

    public void setFullyAudited(Boolean isFullyAudited) {
        set(PROPERTY_ISFULLYAUDITED, isFullyAudited);
    }

    public String getDataOriginType() {
        return (String) get(PROPERTY_DATAORIGINTYPE);
    }

    public void setDataOriginType(String dataOriginType) {
        set(PROPERTY_DATAORIGINTYPE, dataOriginType);
    }

    public Boolean isAuditInserts() {
        return (Boolean) get(PROPERTY_ISAUDITINSERTS);
    }

    public void setAuditInserts(Boolean isAuditInserts) {
        set(PROPERTY_ISAUDITINSERTS, isAuditInserts);
    }

    public DataSource getObserdsDatasource() {
        return (DataSource) get(PROPERTY_OBSERDSDATASOURCE);
    }

    public void setObserdsDatasource(DataSource obserdsDatasource) {
        set(PROPERTY_OBSERDSDATASOURCE, obserdsDatasource);
    }

    @SuppressWarnings("unchecked")
    public List<Attachment> getADAttachmentList() {
        return (List<Attachment>) get(PROPERTY_ADATTACHMENTLIST);
    }

    public void setADAttachmentList(List<Attachment> aDAttachmentList) {
        set(PROPERTY_ADATTACHMENTLIST, aDAttachmentList);
    }

    @SuppressWarnings("unchecked")
    public List<Column> getADColumnList() {
        return (List<Column>) get(PROPERTY_ADCOLUMNLIST);
    }

    public void setADColumnList(List<Column> aDColumnList) {
        set(PROPERTY_ADCOLUMNLIST, aDColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<DimensionMapping> getADDimensionMappingList() {
        return (List<DimensionMapping>) get(PROPERTY_ADDIMENSIONMAPPINGLIST);
    }

    public void setADDimensionMappingList(List<DimensionMapping> aDDimensionMappingList) {
        set(PROPERTY_ADDIMENSIONMAPPINGLIST, aDDimensionMappingList);
    }

    @SuppressWarnings("unchecked")
    public List<ReferencedTable> getADReferencedTableList() {
        return (List<ReferencedTable>) get(PROPERTY_ADREFERENCEDTABLELIST);
    }

    public void setADReferencedTableList(List<ReferencedTable> aDReferencedTableList) {
        set(PROPERTY_ADREFERENCEDTABLELIST, aDReferencedTableList);
    }

    @SuppressWarnings("unchecked")
    public List<Selector> getADSelectorList() {
        return (List<Selector>) get(PROPERTY_ADSELECTORLIST);
    }

    public void setADSelectorList(List<Selector> aDSelectorList) {
        set(PROPERTY_ADSELECTORLIST, aDSelectorList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabList() {
        return (List<Tab>) get(PROPERTY_ADTABLIST);
    }

    public void setADTabList(List<Tab> aDTabList) {
        set(PROPERTY_ADTABLIST, aDTabList);
    }

    @SuppressWarnings("unchecked")
    public List<TableAccess> getADTableAccessList() {
        return (List<TableAccess>) get(PROPERTY_ADTABLEACCESSLIST);
    }

    public void setADTableAccessList(List<TableAccess> aDTableAccessList) {
        set(PROPERTY_ADTABLEACCESSLIST, aDTableAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<AuditTrail> getAuditTrailList() {
        return (List<AuditTrail>) get(PROPERTY_AUDITTRAILLIST);
    }

    public void setAuditTrailList(List<AuditTrail> auditTrailList) {
        set(PROPERTY_AUDITTRAILLIST, auditTrailList);
    }

    @SuppressWarnings("unchecked")
    public List<ADCreatefactTemplate> getCreateFactTemplateList() {
        return (List<ADCreatefactTemplate>) get(PROPERTY_CREATEFACTTEMPLATELIST);
    }

    public void setCreateFactTemplateList(List<ADCreatefactTemplate> createFactTemplateList) {
        set(PROPERTY_CREATEFACTTEMPLATELIST, createFactTemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_FinaccTransactionV> getAPRMFinaccTransactionVForcedTableIDList() {
        return (List<APRM_FinaccTransactionV>) get(PROPERTY_APRMFINACCTRANSACTIONVFORCEDTABLEIDLIST);
    }

    public void setAPRMFinaccTransactionVForcedTableIDList(List<APRM_FinaccTransactionV> aPRMFinaccTransactionVForcedTableIDList) {
        set(PROPERTY_APRMFINACCTRANSACTIONVFORCEDTABLEIDLIST, aPRMFinaccTransactionVForcedTableIDList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Reconciliation_v> getAPRMReconciliationForcedTableIDList() {
        return (List<APRM_Reconciliation_v>) get(PROPERTY_APRMRECONCILIATIONFORCEDTABLEIDLIST);
    }

    public void setAPRMReconciliationForcedTableIDList(List<APRM_Reconciliation_v> aPRMReconciliationForcedTableIDList) {
        set(PROPERTY_APRMRECONCILIATIONFORCEDTABLEIDLIST, aPRMReconciliationForcedTableIDList);
    }

    @SuppressWarnings("unchecked")
    public List<DataSetTable> getDataSetTableList() {
        return (List<DataSetTable>) get(PROPERTY_DATASETTABLELIST);
    }

    public void setDataSetTableList(List<DataSetTable> dataSetTableList) {
        set(PROPERTY_DATASETTABLELIST, dataSetTableList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeList() {
        return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPELIST);
    }

    public void setDocumentTypeList(List<DocumentType> documentTypeList) {
        set(PROPERTY_DOCUMENTTYPELIST, documentTypeList);
    }

    @SuppressWarnings("unchecked")
    public List<EmailInteraction> getEmailInteractionList() {
        return (List<EmailInteraction>) get(PROPERTY_EMAILINTERACTIONLIST);
    }

    public void setEmailInteractionList(List<EmailInteraction> emailInteractionList) {
        set(PROPERTY_EMAILINTERACTIONLIST, emailInteractionList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaTable> getFinancialMgmtAcctSchemaTableList() {
        return (List<AcctSchemaTable>) get(PROPERTY_FINANCIALMGMTACCTSCHEMATABLELIST);
    }

    public void setFinancialMgmtAcctSchemaTableList(List<AcctSchemaTable> financialMgmtAcctSchemaTableList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMATABLELIST, financialMgmtAcctSchemaTableList);
    }

    @SuppressWarnings("unchecked")
    public List<StockAux> getMaterialMgmtStorageStockAuxList() {
        return (List<StockAux>) get(PROPERTY_MATERIALMGMTSTORAGESTOCKAUXLIST);
    }

    public void setMaterialMgmtStorageStockAuxList(List<StockAux> materialMgmtStorageStockAuxList) {
        set(PROPERTY_MATERIALMGMTSTORAGESTOCKAUXLIST, materialMgmtStorageStockAuxList);
    }

    @SuppressWarnings("unchecked")
    public List<DataSource> getOBSERDSDatasourceList() {
        return (List<DataSource>) get(PROPERTY_OBSERDSDATASOURCELIST);
    }

    public void setOBSERDSDatasourceList(List<DataSource> oBSERDSDatasourceList) {
        set(PROPERTY_OBSERDSDATASOURCELIST, oBSERDSDatasourceList);
    }

    @SuppressWarnings("unchecked")
    public List<DatasourceField> getOBSERDSDatasourceFieldList() {
        return (List<DatasourceField>) get(PROPERTY_OBSERDSDATASOURCEFIELDLIST);
    }

    public void setOBSERDSDatasourceFieldList(List<DatasourceField> oBSERDSDatasourceFieldList) {
        set(PROPERTY_OBSERDSDATASOURCEFIELDLIST, oBSERDSDatasourceFieldList);
    }

    @SuppressWarnings("unchecked")
    public List<Note> getOBUIAPPNoteList() {
        return (List<Note>) get(PROPERTY_OBUIAPPNOTELIST);
    }

    public void setOBUIAPPNoteList(List<Note> oBUIAPPNoteList) {
        set(PROPERTY_OBUIAPPNOTELIST, oBUIAPPNoteList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.userinterface.selector.Selector> getOBUISELSelectorList() {
        return (List<org.openbravo.userinterface.selector.Selector>) get(PROPERTY_OBUISELSELECTORLIST);
    }

    public void setOBUISELSelectorList(List<org.openbravo.userinterface.selector.Selector> oBUISELSelectorList) {
        set(PROPERTY_OBUISELSELECTORLIST, oBUISELSelectorList);
    }

    @SuppressWarnings("unchecked")
    public List<ReferenceDataStore> getReferenceDataStoreList() {
        return (List<ReferenceDataStore>) get(PROPERTY_REFERENCEDATASTORELIST);
    }

    public void setReferenceDataStoreList(List<ReferenceDataStore> referenceDataStoreList) {
        set(PROPERTY_REFERENCEDATASTORELIST, referenceDataStoreList);
    }

}
