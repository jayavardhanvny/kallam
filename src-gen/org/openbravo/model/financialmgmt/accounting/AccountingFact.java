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
package org.openbravo.model.financialmgmt.accounting;

import java.math.BigDecimal;
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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
import org.openbravo.model.financialmgmt.accounting.report.AcctCFS;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.gl.GLCategory;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.financialmgmt.tax.Withholding;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.project.Project;
import org.openbravo.model.sales.SalesRegion;
/**
 * Entity class for entity FinancialMgmtAccountingFact (stored in table Fact_Acct).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AccountingFact extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Fact_Acct";
    public static final String ENTITY_NAME = "FinancialMgmtAccountingFact";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_TRANSACTIONDATE = "transactionDate";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_RECORDID = "recordID";
    public static final String PROPERTY_LINEID = "lineID";
    public static final String PROPERTY_GLCATEGORY = "gLCategory";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_POSTINGTYPE = "postingType";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_FOREIGNCURRENCYDEBIT = "foreignCurrencyDebit";
    public static final String PROPERTY_FOREIGNCURRENCYCREDIT = "foreignCurrencyCredit";
    public static final String PROPERTY_DEBIT = "debit";
    public static final String PROPERTY_CREDIT = "credit";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_TRXORGANIZATION = "trxOrganization";
    public static final String PROPERTY_LOCATIONFROMADDRESS = "locationFromAddress";
    public static final String PROPERTY_LOCATIONTOADDRESS = "locationToAddress";
    public static final String PROPERTY_SALESREGION = "salesRegion";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_GROUPID = "groupID";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_DOCUMENTCATEGORY = "documentCategory";
    public static final String PROPERTY_VALUE = "value";
    public static final String PROPERTY_ACCOUNTINGENTRYDESCRIPTION = "accountingEntryDescription";
    public static final String PROPERTY_RECORDID2 = "recordID2";
    public static final String PROPERTY_WITHHOLDING = "withholding";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_COSTCENTER = "costcenter";
    public static final String PROPERTY_MODIFY = "modify";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARLIST = "financialMgmtAccountingFactEndYearList";
    public static final String PROPERTY_FINANCIALMGMTACCTCFSLIST = "financialMgmtAcctCFSList";
    public static final String PROPERTY_FINANCIALMGMTACCTCFSACCOUNTINGFACTREFERENCELIST = "financialMgmtAcctCFSAccountingFactReferenceList";

    public AccountingFact() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TYPE, "N");
        setDefaultValue(PROPERTY_MODIFY, false);
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTCFSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTCFSACCOUNTINGFACTREFERENCELIST, new ArrayList<Object>());
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

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
    }

    public ElementValue getAccount() {
        return (ElementValue) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(ElementValue account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public Date getTransactionDate() {
        return (Date) get(PROPERTY_TRANSACTIONDATE);
    }

    public void setTransactionDate(Date transactionDate) {
        set(PROPERTY_TRANSACTIONDATE, transactionDate);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
    }

    public String getRecordID() {
        return (String) get(PROPERTY_RECORDID);
    }

    public void setRecordID(String recordID) {
        set(PROPERTY_RECORDID, recordID);
    }

    public String getLineID() {
        return (String) get(PROPERTY_LINEID);
    }

    public void setLineID(String lineID) {
        set(PROPERTY_LINEID, lineID);
    }

    public GLCategory getGLCategory() {
        return (GLCategory) get(PROPERTY_GLCATEGORY);
    }

    public void setGLCategory(GLCategory gLCategory) {
        set(PROPERTY_GLCATEGORY, gLCategory);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public String getPostingType() {
        return (String) get(PROPERTY_POSTINGTYPE);
    }

    public void setPostingType(String postingType) {
        set(PROPERTY_POSTINGTYPE, postingType);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BigDecimal getForeignCurrencyDebit() {
        return (BigDecimal) get(PROPERTY_FOREIGNCURRENCYDEBIT);
    }

    public void setForeignCurrencyDebit(BigDecimal foreignCurrencyDebit) {
        set(PROPERTY_FOREIGNCURRENCYDEBIT, foreignCurrencyDebit);
    }

    public BigDecimal getForeignCurrencyCredit() {
        return (BigDecimal) get(PROPERTY_FOREIGNCURRENCYCREDIT);
    }

    public void setForeignCurrencyCredit(BigDecimal foreignCurrencyCredit) {
        set(PROPERTY_FOREIGNCURRENCYCREDIT, foreignCurrencyCredit);
    }

    public BigDecimal getDebit() {
        return (BigDecimal) get(PROPERTY_DEBIT);
    }

    public void setDebit(BigDecimal debit) {
        set(PROPERTY_DEBIT, debit);
    }

    public BigDecimal getCredit() {
        return (BigDecimal) get(PROPERTY_CREDIT);
    }

    public void setCredit(BigDecimal credit) {
        set(PROPERTY_CREDIT, credit);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Organization getTrxOrganization() {
        return (Organization) get(PROPERTY_TRXORGANIZATION);
    }

    public void setTrxOrganization(Organization trxOrganization) {
        set(PROPERTY_TRXORGANIZATION, trxOrganization);
    }

    public Location getLocationFromAddress() {
        return (Location) get(PROPERTY_LOCATIONFROMADDRESS);
    }

    public void setLocationFromAddress(Location locationFromAddress) {
        set(PROPERTY_LOCATIONFROMADDRESS, locationFromAddress);
    }

    public Location getLocationToAddress() {
        return (Location) get(PROPERTY_LOCATIONTOADDRESS);
    }

    public void setLocationToAddress(Location locationToAddress) {
        set(PROPERTY_LOCATIONTOADDRESS, locationToAddress);
    }

    public SalesRegion getSalesRegion() {
        return (SalesRegion) get(PROPERTY_SALESREGION);
    }

    public void setSalesRegion(SalesRegion salesRegion) {
        set(PROPERTY_SALESREGION, salesRegion);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Campaign getSalesCampaign() {
        return (Campaign) get(PROPERTY_SALESCAMPAIGN);
    }

    public void setSalesCampaign(Campaign salesCampaign) {
        set(PROPERTY_SALESCAMPAIGN, salesCampaign);
    }

    public ABCActivity getActivity() {
        return (ABCActivity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(ABCActivity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public UserDimension1 getStDimension() {
        return (UserDimension1) get(PROPERTY_STDIMENSION);
    }

    public void setStDimension(UserDimension1 stDimension) {
        set(PROPERTY_STDIMENSION, stDimension);
    }

    public UserDimension2 getNdDimension() {
        return (UserDimension2) get(PROPERTY_NDDIMENSION);
    }

    public void setNdDimension(UserDimension2 ndDimension) {
        set(PROPERTY_NDDIMENSION, ndDimension);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Asset getAsset() {
        return (Asset) get(PROPERTY_ASSET);
    }

    public void setAsset(Asset asset) {
        set(PROPERTY_ASSET, asset);
    }

    public String getGroupID() {
        return (String) get(PROPERTY_GROUPID);
    }

    public void setGroupID(String groupID) {
        set(PROPERTY_GROUPID, groupID);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public String getDocumentCategory() {
        return (String) get(PROPERTY_DOCUMENTCATEGORY);
    }

    public void setDocumentCategory(String documentCategory) {
        set(PROPERTY_DOCUMENTCATEGORY, documentCategory);
    }

    public String getValue() {
        return (String) get(PROPERTY_VALUE);
    }

    public void setValue(String value) {
        set(PROPERTY_VALUE, value);
    }

    public String getAccountingEntryDescription() {
        return (String) get(PROPERTY_ACCOUNTINGENTRYDESCRIPTION);
    }

    public void setAccountingEntryDescription(String accountingEntryDescription) {
        set(PROPERTY_ACCOUNTINGENTRYDESCRIPTION, accountingEntryDescription);
    }

    public String getRecordID2() {
        return (String) get(PROPERTY_RECORDID2);
    }

    public void setRecordID2(String recordID2) {
        set(PROPERTY_RECORDID2, recordID2);
    }

    public Withholding getWithholding() {
        return (Withholding) get(PROPERTY_WITHHOLDING);
    }

    public void setWithholding(Withholding withholding) {
        set(PROPERTY_WITHHOLDING, withholding);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public Costcenter getCostcenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostcenter(Costcenter costcenter) {
        set(PROPERTY_COSTCENTER, costcenter);
    }

    public Boolean isModify() {
        return (Boolean) get(PROPERTY_MODIFY);
    }

    public void setModify(Boolean modify) {
        set(PROPERTY_MODIFY, modify);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFactEndYear> getFinancialMgmtAccountingFactEndYearList() {
        return (List<AccountingFactEndYear>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARLIST);
    }

    public void setFinancialMgmtAccountingFactEndYearList(List<AccountingFactEndYear> financialMgmtAccountingFactEndYearList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTENDYEARLIST, financialMgmtAccountingFactEndYearList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctCFS> getFinancialMgmtAcctCFSList() {
        return (List<AcctCFS>) get(PROPERTY_FINANCIALMGMTACCTCFSLIST);
    }

    public void setFinancialMgmtAcctCFSList(List<AcctCFS> financialMgmtAcctCFSList) {
        set(PROPERTY_FINANCIALMGMTACCTCFSLIST, financialMgmtAcctCFSList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctCFS> getFinancialMgmtAcctCFSAccountingFactReferenceList() {
        return (List<AcctCFS>) get(PROPERTY_FINANCIALMGMTACCTCFSACCOUNTINGFACTREFERENCELIST);
    }

    public void setFinancialMgmtAcctCFSAccountingFactReferenceList(List<AcctCFS> financialMgmtAcctCFSAccountingFactReferenceList) {
        set(PROPERTY_FINANCIALMGMTACCTCFSACCOUNTINGFACTREFERENCELIST, financialMgmtAcctCFSAccountingFactReferenceList);
    }

}
