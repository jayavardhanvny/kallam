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
package org.openbravo.model.financialmgmt.gl;

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
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.tax.TaxPayment;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.project.Project;
/**
 * Entity class for entity FinancialMgmtGLJournal (stored in table GL_Journal).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class GLJournal extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "GL_Journal";
    public static final String ENTITY_NAME = "FinancialMgmtGLJournal";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_APPROVED = "approved";
    public static final String PROPERTY_PRINT = "print";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_POSTINGTYPE = "postingType";
    public static final String PROPERTY_GLCATEGORY = "gLCategory";
    public static final String PROPERTY_DOCUMENTDATE = "documentDate";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_CURRENCYRATETYPE = "currencyRateType";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_JOURNALBATCH = "journalBatch";
    public static final String PROPERTY_TOTALDEBITAMOUNT = "totalDebitAmount";
    public static final String PROPERTY_TOTALCREDITAMOUNT = "totalCreditAmount";
    public static final String PROPERTY_CONTROLAMOUNT = "controlAmount";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_OPENING = "opening";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_FINANCIALMGMTTAXPAYMENTLIST = "financialMgmtTaxPaymentList";

    public GLJournal() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_DOCUMENTACTION, "CO");
        setDefaultValue(PROPERTY_APPROVED, true);
        setDefaultValue(PROPERTY_PRINT, false);
        setDefaultValue(PROPERTY_CURRENCYRATETYPE, "S");
        setDefaultValue(PROPERTY_RATE, new BigDecimal(1));
        setDefaultValue(PROPERTY_TOTALDEBITAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALCREDITAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_OPENING, false);
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, new ArrayList<Object>());
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

    public Campaign getSalesCampaign() {
        return (Campaign) get(PROPERTY_SALESCAMPAIGN);
    }

    public void setSalesCampaign(Campaign salesCampaign) {
        set(PROPERTY_SALESCAMPAIGN, salesCampaign);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public Boolean isApproved() {
        return (Boolean) get(PROPERTY_APPROVED);
    }

    public void setApproved(Boolean approved) {
        set(PROPERTY_APPROVED, approved);
    }

    public Boolean isPrint() {
        return (Boolean) get(PROPERTY_PRINT);
    }

    public void setPrint(Boolean print) {
        set(PROPERTY_PRINT, print);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getPostingType() {
        return (String) get(PROPERTY_POSTINGTYPE);
    }

    public void setPostingType(String postingType) {
        set(PROPERTY_POSTINGTYPE, postingType);
    }

    public GLCategory getGLCategory() {
        return (GLCategory) get(PROPERTY_GLCATEGORY);
    }

    public void setGLCategory(GLCategory gLCategory) {
        set(PROPERTY_GLCATEGORY, gLCategory);
    }

    public Date getDocumentDate() {
        return (Date) get(PROPERTY_DOCUMENTDATE);
    }

    public void setDocumentDate(Date documentDate) {
        set(PROPERTY_DOCUMENTDATE, documentDate);
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

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getCurrencyRateType() {
        return (String) get(PROPERTY_CURRENCYRATETYPE);
    }

    public void setCurrencyRateType(String currencyRateType) {
        set(PROPERTY_CURRENCYRATETYPE, currencyRateType);
    }

    public BigDecimal getRate() {
        return (BigDecimal) get(PROPERTY_RATE);
    }

    public void setRate(BigDecimal rate) {
        set(PROPERTY_RATE, rate);
    }

    public GLBatch getJournalBatch() {
        return (GLBatch) get(PROPERTY_JOURNALBATCH);
    }

    public void setJournalBatch(GLBatch journalBatch) {
        set(PROPERTY_JOURNALBATCH, journalBatch);
    }

    public BigDecimal getTotalDebitAmount() {
        return (BigDecimal) get(PROPERTY_TOTALDEBITAMOUNT);
    }

    public void setTotalDebitAmount(BigDecimal totalDebitAmount) {
        set(PROPERTY_TOTALDEBITAMOUNT, totalDebitAmount);
    }

    public BigDecimal getTotalCreditAmount() {
        return (BigDecimal) get(PROPERTY_TOTALCREDITAMOUNT);
    }

    public void setTotalCreditAmount(BigDecimal totalCreditAmount) {
        set(PROPERTY_TOTALCREDITAMOUNT, totalCreditAmount);
    }

    public BigDecimal getControlAmount() {
        return (BigDecimal) get(PROPERTY_CONTROLAMOUNT);
    }

    public void setControlAmount(BigDecimal controlAmount) {
        set(PROPERTY_CONTROLAMOUNT, controlAmount);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
    }

    public Boolean isOpening() {
        return (Boolean) get(PROPERTY_OPENING);
    }

    public void setOpening(Boolean opening) {
        set(PROPERTY_OPENING, opening);
    }

    public Asset getAsset() {
        return (Asset) get(PROPERTY_ASSET);
    }

    public void setAsset(Asset asset) {
        set(PROPERTY_ASSET, asset);
    }

    public Costcenter getCostCenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(Costcenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
        return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxPayment> getFinancialMgmtTaxPaymentList() {
        return (List<TaxPayment>) get(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST);
    }

    public void setFinancialMgmtTaxPaymentList(List<TaxPayment> financialMgmtTaxPaymentList) {
        set(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, financialMgmtTaxPaymentList);
    }

}
