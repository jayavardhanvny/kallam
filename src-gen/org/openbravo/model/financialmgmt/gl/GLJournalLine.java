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
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.payment.DebtPayment;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_Payment;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.financialmgmt.tax.Withholding;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.project.Project;
import org.openbravo.model.sales.SalesRegion;
/**
 * Entity class for entity FinancialMgmtGLJournalLine (stored in table GL_JournalLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class GLJournalLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "GL_JournalLine";
    public static final String ENTITY_NAME = "FinancialMgmtGLJournalLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_JOURNALENTRY = "journalEntry";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_GENERATED = "generated";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_FOREIGNCURRENCYDEBIT = "foreignCurrencyDebit";
    public static final String PROPERTY_FOREIGNCURRENCYCREDIT = "foreignCurrencyCredit";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_CURRENCYRATETYPE = "currencyRateType";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_DEBIT = "debit";
    public static final String PROPERTY_CREDIT = "credit";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_ACCOUNTINGCOMBINATION = "accountingCombination";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_WITHHOLDING = "withholding";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_SALESREGION = "salesRegion";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_RELATEDPAYMENT = "relatedPayment";
    public static final String PROPERTY_FINANCIALACCOUNT = "financialAccount";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_OPENITEMS = "openItems";
    public static final String PROPERTY_GLITEM = "gLItem";
    public static final String PROPERTY_APRMADDPAYMENT = "aPRMAddPayment";
    public static final String PROPERTY_PAYMENTDATE = "paymentDate";

    public GLJournalLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_GENERATED, false);
        setDefaultValue(PROPERTY_CURRENCYRATETYPE, "S");
        setDefaultValue(PROPERTY_RATE, new BigDecimal(1));
        setDefaultValue(PROPERTY_OPENITEMS, false);
        setDefaultValue(PROPERTY_APRMADDPAYMENT, true);
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

    public GLJournal getJournalEntry() {
        return (GLJournal) get(PROPERTY_JOURNALENTRY);
    }

    public void setJournalEntry(GLJournal journalEntry) {
        set(PROPERTY_JOURNALENTRY, journalEntry);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Boolean isGenerated() {
        return (Boolean) get(PROPERTY_GENERATED);
    }

    public void setGenerated(Boolean generated) {
        set(PROPERTY_GENERATED, generated);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
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

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
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

    public AccountingCombination getAccountingCombination() {
        return (AccountingCombination) get(PROPERTY_ACCOUNTINGCOMBINATION);
    }

    public void setAccountingCombination(AccountingCombination accountingCombination) {
        set(PROPERTY_ACCOUNTINGCOMBINATION, accountingCombination);
    }

    public DebtPayment getPayment() {
        return (DebtPayment) get(PROPERTY_PAYMENT);
    }

    public void setPayment(DebtPayment payment) {
        set(PROPERTY_PAYMENT, payment);
    }

    public Withholding getWithholding() {
        return (Withholding) get(PROPERTY_WITHHOLDING);
    }

    public void setWithholding(Withholding withholding) {
        set(PROPERTY_WITHHOLDING, withholding);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
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

    public ABCActivity getActivity() {
        return (ABCActivity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(ABCActivity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public SalesRegion getSalesRegion() {
        return (SalesRegion) get(PROPERTY_SALESREGION);
    }

    public void setSalesRegion(SalesRegion salesRegion) {
        set(PROPERTY_SALESREGION, salesRegion);
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

    public FIN_Payment getRelatedPayment() {
        return (FIN_Payment) get(PROPERTY_RELATEDPAYMENT);
    }

    public void setRelatedPayment(FIN_Payment relatedPayment) {
        set(PROPERTY_RELATEDPAYMENT, relatedPayment);
    }

    public FIN_FinancialAccount getFinancialAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_FINANCIALACCOUNT);
    }

    public void setFinancialAccount(FIN_FinancialAccount financialAccount) {
        set(PROPERTY_FINANCIALACCOUNT, financialAccount);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public Boolean isOpenItems() {
        return (Boolean) get(PROPERTY_OPENITEMS);
    }

    public void setOpenItems(Boolean openItems) {
        set(PROPERTY_OPENITEMS, openItems);
    }

    public GLItem getGLItem() {
        return (GLItem) get(PROPERTY_GLITEM);
    }

    public void setGLItem(GLItem gLItem) {
        set(PROPERTY_GLITEM, gLItem);
    }

    public Boolean isAPRMAddPayment() {
        return (Boolean) get(PROPERTY_APRMADDPAYMENT);
    }

    public void setAPRMAddPayment(Boolean aPRMAddPayment) {
        set(PROPERTY_APRMADDPAYMENT, aPRMAddPayment);
    }

    public Date getPaymentDate() {
        return (Date) get(PROPERTY_PAYMENTDATE);
    }

    public void setPaymentDate(Date paymentDate) {
        set(PROPERTY_PAYMENTDATE, paymentDate);
    }

}
