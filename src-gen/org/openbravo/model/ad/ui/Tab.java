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
package org.openbravo.model.ad.ui;

import com.rcss.humanresource.data.RchrApprovalHistrory;
import com.rcss.humanresource.data.RchrApprovalTemplateDoc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.application.UIPersonalization;
import org.openbravo.client.querylist.OBCQL_QueryColumn;
import org.openbravo.model.ad.access.TabAccess;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.alert.AlertRule;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.domain.ModelImplementation;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADTab (stored in table AD_Tab).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Tab extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Tab";
    public static final String ENTITY_NAME = "ADTab";
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
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_WINDOW = "window";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_TABLEVEL = "tabLevel";
    public static final String PROPERTY_DEFAULTEDITMODE = "defaultEditMode";
    public static final String PROPERTY_ACCOUNTINGTAB = "accountingTab";
    public static final String PROPERTY_TRANSLATIONTAB = "translationTab";
    public static final String PROPERTY_READONLY = "readOnly";
    public static final String PROPERTY_COLUMN = "column";
    public static final String PROPERTY_TREEINCLUDED = "treeIncluded";
    public static final String PROPERTY_SQLWHERECLAUSE = "sQLWhereClause";
    public static final String PROPERTY_SQLORDERBYCLAUSE = "sQLOrderByClause";
    public static final String PROPERTY_CONFIRMATIONMESSAGE = "confirmationMessage";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_IMPORTFIELDS = "importFields";
    public static final String PROPERTY_ORDERCOLUMN = "orderColumn";
    public static final String PROPERTY_INCLUDEDCOLUMN = "includedColumn";
    public static final String PROPERTY_SEQUENCETAB = "sequenceTab";
    public static final String PROPERTY_INCLUDEDTAB = "includedTab";
    public static final String PROPERTY_FILTERCLAUSE = "filterClause";
    public static final String PROPERTY_MASTERDETAILFORM = "masterDetailForm";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_UIPATTERN = "uIPattern";
    public static final String PROPERTY_HQLWHERECLAUSE = "hqlwhereclause";
    public static final String PROPERTY_HQLORDERBYCLAUSE = "hqlorderbyclause";
    public static final String PROPERTY_HQLFILTERCLAUSE = "hqlfilterclause";
    public static final String PROPERTY_FILTERNAME = "filterName";
    public static final String PROPERTY_SHOWPARENTSBUTTONS = "showParentsButtons";
    public static final String PROPERTY_OBUIAPPSELECTION = "oBUIAPPSelection";
    public static final String PROPERTY_OBUIAPPCANADD = "obuiappCanAdd";
    public static final String PROPERTY_OBUIAPPCANDELETE = "obuiappCanDelete";
    public static final String PROPERTY_OBUIAPPSHOWSELECT = "obuiappShowSelect";
    public static final String PROPERTY_OBUIAPPNEWFN = "oBUIAPPNewFn";
    public static final String PROPERTY_OBUIAPPREMOVEFN = "obuiappRemovefn";
    public static final String PROPERTY_DISPLAYLOGIC = "displayLogic";
    public static final String PROPERTY_ADALERTRULELIST = "aDAlertRuleList";
    public static final String PROPERTY_ADAUXILIARYINPUTLIST = "aDAuxiliaryInputList";
    public static final String PROPERTY_ADFIELDLIST = "aDFieldList";
    public static final String PROPERTY_ADMODELIMPLEMENTATIONLIST = "aDModelImplementationList";
    public static final String PROPERTY_ADTABINCLUDEDTABLIST = "aDTabIncludedTabList";
    public static final String PROPERTY_ADTABACCESSLIST = "aDTabAccessList";
    public static final String PROPERTY_ADTABTRLLIST = "aDTabTrlList";
    public static final String PROPERTY_OBCQLQUERYCOLUMNLIST = "oBCQLQueryColumnList";
    public static final String PROPERTY_OBUIAPPUIPERSONALIZATIONLIST = "oBUIAPPUIPersonalizationList";
    public static final String PROPERTY_RCHRAPPROVALHISTRORYLIST = "rCHRApprovalHistroryList";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST = "rCHRApprovalTemplateDocList";

    public Tab() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEFAULTEDITMODE, false);
        setDefaultValue(PROPERTY_ACCOUNTINGTAB, false);
        setDefaultValue(PROPERTY_TRANSLATIONTAB, false);
        setDefaultValue(PROPERTY_READONLY, false);
        setDefaultValue(PROPERTY_TREEINCLUDED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_IMPORTFIELDS, false);
        setDefaultValue(PROPERTY_SEQUENCETAB, false);
        setDefaultValue(PROPERTY_UIPATTERN, "STD");
        setDefaultValue(PROPERTY_SHOWPARENTSBUTTONS, true);
        setDefaultValue(PROPERTY_OBUIAPPCANADD, false);
        setDefaultValue(PROPERTY_OBUIAPPCANDELETE, false);
        setDefaultValue(PROPERTY_OBUIAPPSHOWSELECT, true);
        setDefaultValue(PROPERTY_ADALERTRULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADAUXILIARYINPUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADFIELDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMODELIMPLEMENTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABINCLUDEDTABLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABACCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBCQLQUERYCOLUMNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALHISTRORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST, new ArrayList<Object>());
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

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
    }

    public Window getWindow() {
        return (Window) get(PROPERTY_WINDOW);
    }

    public void setWindow(Window window) {
        set(PROPERTY_WINDOW, window);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public Long getTabLevel() {
        return (Long) get(PROPERTY_TABLEVEL);
    }

    public void setTabLevel(Long tabLevel) {
        set(PROPERTY_TABLEVEL, tabLevel);
    }

    public Boolean isDefaultEditMode() {
        return (Boolean) get(PROPERTY_DEFAULTEDITMODE);
    }

    public void setDefaultEditMode(Boolean defaultEditMode) {
        set(PROPERTY_DEFAULTEDITMODE, defaultEditMode);
    }

    public Boolean isAccountingTab() {
        return (Boolean) get(PROPERTY_ACCOUNTINGTAB);
    }

    public void setAccountingTab(Boolean accountingTab) {
        set(PROPERTY_ACCOUNTINGTAB, accountingTab);
    }

    public Boolean isTranslationTab() {
        return (Boolean) get(PROPERTY_TRANSLATIONTAB);
    }

    public void setTranslationTab(Boolean translationTab) {
        set(PROPERTY_TRANSLATIONTAB, translationTab);
    }

    public Boolean isReadOnly() {
        return (Boolean) get(PROPERTY_READONLY);
    }

    public void setReadOnly(Boolean readOnly) {
        set(PROPERTY_READONLY, readOnly);
    }

    public Column getColumn() {
        return (Column) get(PROPERTY_COLUMN);
    }

    public void setColumn(Column column) {
        set(PROPERTY_COLUMN, column);
    }

    public Boolean isTreeIncluded() {
        return (Boolean) get(PROPERTY_TREEINCLUDED);
    }

    public void setTreeIncluded(Boolean treeIncluded) {
        set(PROPERTY_TREEINCLUDED, treeIncluded);
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

    public String getConfirmationMessage() {
        return (String) get(PROPERTY_CONFIRMATIONMESSAGE);
    }

    public void setConfirmationMessage(String confirmationMessage) {
        set(PROPERTY_CONFIRMATIONMESSAGE, confirmationMessage);
    }

    public Process getProcess() {
        return (Process) get(PROPERTY_PROCESS);
    }

    public void setProcess(Process process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public Boolean isImportFields() {
        return (Boolean) get(PROPERTY_IMPORTFIELDS);
    }

    public void setImportFields(Boolean importFields) {
        set(PROPERTY_IMPORTFIELDS, importFields);
    }

    public Column getOrderColumn() {
        return (Column) get(PROPERTY_ORDERCOLUMN);
    }

    public void setOrderColumn(Column orderColumn) {
        set(PROPERTY_ORDERCOLUMN, orderColumn);
    }

    public Column getIncludedColumn() {
        return (Column) get(PROPERTY_INCLUDEDCOLUMN);
    }

    public void setIncludedColumn(Column includedColumn) {
        set(PROPERTY_INCLUDEDCOLUMN, includedColumn);
    }

    public Boolean isSequenceTab() {
        return (Boolean) get(PROPERTY_SEQUENCETAB);
    }

    public void setSequenceTab(Boolean sequenceTab) {
        set(PROPERTY_SEQUENCETAB, sequenceTab);
    }

    public Tab getIncludedTab() {
        return (Tab) get(PROPERTY_INCLUDEDTAB);
    }

    public void setIncludedTab(Tab includedTab) {
        set(PROPERTY_INCLUDEDTAB, includedTab);
    }

    public String getFilterClause() {
        return (String) get(PROPERTY_FILTERCLAUSE);
    }

    public void setFilterClause(String filterClause) {
        set(PROPERTY_FILTERCLAUSE, filterClause);
    }

    public Form getMasterDetailForm() {
        return (Form) get(PROPERTY_MASTERDETAILFORM);
    }

    public void setMasterDetailForm(Form masterDetailForm) {
        set(PROPERTY_MASTERDETAILFORM, masterDetailForm);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public String getUIPattern() {
        return (String) get(PROPERTY_UIPATTERN);
    }

    public void setUIPattern(String uIPattern) {
        set(PROPERTY_UIPATTERN, uIPattern);
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

    public String getHqlfilterclause() {
        return (String) get(PROPERTY_HQLFILTERCLAUSE);
    }

    public void setHqlfilterclause(String hqlfilterclause) {
        set(PROPERTY_HQLFILTERCLAUSE, hqlfilterclause);
    }

    public String getFilterName() {
        return (String) get(PROPERTY_FILTERNAME);
    }

    public void setFilterName(String filterName) {
        set(PROPERTY_FILTERNAME, filterName);
    }

    public Boolean isShowParentsButtons() {
        return (Boolean) get(PROPERTY_SHOWPARENTSBUTTONS);
    }

    public void setShowParentsButtons(Boolean showParentsButtons) {
        set(PROPERTY_SHOWPARENTSBUTTONS, showParentsButtons);
    }

    public String getOBUIAPPSelection() {
        return (String) get(PROPERTY_OBUIAPPSELECTION);
    }

    public void setOBUIAPPSelection(String oBUIAPPSelection) {
        set(PROPERTY_OBUIAPPSELECTION, oBUIAPPSelection);
    }

    public Boolean isObuiappCanAdd() {
        return (Boolean) get(PROPERTY_OBUIAPPCANADD);
    }

    public void setObuiappCanAdd(Boolean obuiappCanAdd) {
        set(PROPERTY_OBUIAPPCANADD, obuiappCanAdd);
    }

    public Boolean isObuiappCanDelete() {
        return (Boolean) get(PROPERTY_OBUIAPPCANDELETE);
    }

    public void setObuiappCanDelete(Boolean obuiappCanDelete) {
        set(PROPERTY_OBUIAPPCANDELETE, obuiappCanDelete);
    }

    public Boolean isObuiappShowSelect() {
        return (Boolean) get(PROPERTY_OBUIAPPSHOWSELECT);
    }

    public void setObuiappShowSelect(Boolean obuiappShowSelect) {
        set(PROPERTY_OBUIAPPSHOWSELECT, obuiappShowSelect);
    }

    public String getOBUIAPPNewFn() {
        return (String) get(PROPERTY_OBUIAPPNEWFN);
    }

    public void setOBUIAPPNewFn(String oBUIAPPNewFn) {
        set(PROPERTY_OBUIAPPNEWFN, oBUIAPPNewFn);
    }

    public String getObuiappRemovefn() {
        return (String) get(PROPERTY_OBUIAPPREMOVEFN);
    }

    public void setObuiappRemovefn(String obuiappRemovefn) {
        set(PROPERTY_OBUIAPPREMOVEFN, obuiappRemovefn);
    }

    public String getDisplayLogic() {
        return (String) get(PROPERTY_DISPLAYLOGIC);
    }

    public void setDisplayLogic(String displayLogic) {
        set(PROPERTY_DISPLAYLOGIC, displayLogic);
    }

    @SuppressWarnings("unchecked")
    public List<AlertRule> getADAlertRuleList() {
        return (List<AlertRule>) get(PROPERTY_ADALERTRULELIST);
    }

    public void setADAlertRuleList(List<AlertRule> aDAlertRuleList) {
        set(PROPERTY_ADALERTRULELIST, aDAlertRuleList);
    }

    @SuppressWarnings("unchecked")
    public List<AuxiliaryInput> getADAuxiliaryInputList() {
        return (List<AuxiliaryInput>) get(PROPERTY_ADAUXILIARYINPUTLIST);
    }

    public void setADAuxiliaryInputList(List<AuxiliaryInput> aDAuxiliaryInputList) {
        set(PROPERTY_ADAUXILIARYINPUTLIST, aDAuxiliaryInputList);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getADFieldList() {
        return (List<Field>) get(PROPERTY_ADFIELDLIST);
    }

    public void setADFieldList(List<Field> aDFieldList) {
        set(PROPERTY_ADFIELDLIST, aDFieldList);
    }

    @SuppressWarnings("unchecked")
    public List<ModelImplementation> getADModelImplementationList() {
        return (List<ModelImplementation>) get(PROPERTY_ADMODELIMPLEMENTATIONLIST);
    }

    public void setADModelImplementationList(List<ModelImplementation> aDModelImplementationList) {
        set(PROPERTY_ADMODELIMPLEMENTATIONLIST, aDModelImplementationList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabIncludedTabList() {
        return (List<Tab>) get(PROPERTY_ADTABINCLUDEDTABLIST);
    }

    public void setADTabIncludedTabList(List<Tab> aDTabIncludedTabList) {
        set(PROPERTY_ADTABINCLUDEDTABLIST, aDTabIncludedTabList);
    }

    @SuppressWarnings("unchecked")
    public List<TabAccess> getADTabAccessList() {
        return (List<TabAccess>) get(PROPERTY_ADTABACCESSLIST);
    }

    public void setADTabAccessList(List<TabAccess> aDTabAccessList) {
        set(PROPERTY_ADTABACCESSLIST, aDTabAccessList);
    }

    @SuppressWarnings("unchecked")
    public List<TabTrl> getADTabTrlList() {
        return (List<TabTrl>) get(PROPERTY_ADTABTRLLIST);
    }

    public void setADTabTrlList(List<TabTrl> aDTabTrlList) {
        set(PROPERTY_ADTABTRLLIST, aDTabTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<OBCQL_QueryColumn> getOBCQLQueryColumnList() {
        return (List<OBCQL_QueryColumn>) get(PROPERTY_OBCQLQUERYCOLUMNLIST);
    }

    public void setOBCQLQueryColumnList(List<OBCQL_QueryColumn> oBCQLQueryColumnList) {
        set(PROPERTY_OBCQLQUERYCOLUMNLIST, oBCQLQueryColumnList);
    }

    @SuppressWarnings("unchecked")
    public List<UIPersonalization> getOBUIAPPUIPersonalizationList() {
        return (List<UIPersonalization>) get(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST);
    }

    public void setOBUIAPPUIPersonalizationList(List<UIPersonalization> oBUIAPPUIPersonalizationList) {
        set(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST, oBUIAPPUIPersonalizationList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalHistrory> getRCHRApprovalHistroryList() {
        return (List<RchrApprovalHistrory>) get(PROPERTY_RCHRAPPROVALHISTRORYLIST);
    }

    public void setRCHRApprovalHistroryList(List<RchrApprovalHistrory> rCHRApprovalHistroryList) {
        set(PROPERTY_RCHRAPPROVALHISTRORYLIST, rCHRApprovalHistroryList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalTemplateDoc> getRCHRApprovalTemplateDocList() {
        return (List<RchrApprovalTemplateDoc>) get(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST);
    }

    public void setRCHRApprovalTemplateDocList(List<RchrApprovalTemplateDoc> rCHRApprovalTemplateDocList) {
        set(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST, rCHRApprovalTemplateDocList);
    }

}
