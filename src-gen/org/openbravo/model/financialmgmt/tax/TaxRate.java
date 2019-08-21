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
package org.openbravo.model.financialmgmt.tax;

import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.EpcgProformaline;
import com.redcarpet.rcssob.data.PurchaseQuotationLines;
import com.redcarpet.rcssob.data.Quotationtax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.InvoiceLineTax;
import org.openbravo.model.ad.access.OrderLineTax;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.Withholding;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.invoice.InvoiceTax;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.order.OrderTax;
import org.openbravo.model.common.order.ReturnMaterialOrderPickEditLines;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.project.ProjectLine;
/**
 * Entity class for entity FinancialMgmtTaxRate (stored in table C_Tax).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class TaxRate extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Tax";
    public static final String ENTITY_NAME = "FinancialMgmtTaxRate";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_TAXSEARCHKEY = "taxSearchKey";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_PARENTTAXRATE = "parentTaxRate";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_DESTINATIONCOUNTRY = "destinationCountry";
    public static final String PROPERTY_DESTINATIONREGION = "destinationRegion";
    public static final String PROPERTY_TAXCATEGORY = "taxCategory";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_TAXEXEMPT = "taxExempt";
    public static final String PROPERTY_SALESPURCHASETYPE = "salesPurchaseType";
    public static final String PROPERTY_CASCADE = "cascade";
    public static final String PROPERTY_BUSINESSPARTNERTAXCATEGORY = "businessPartnerTaxCategory";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_WITHHOLDINGTAX = "withholdingTax";
    public static final String PROPERTY_NOTTAXABLE = "notTaxable";
    public static final String PROPERTY_DEDUCTABLERATE = "deductableRate";
    public static final String PROPERTY_ORIGINALRATE = "originalRate";
    public static final String PROPERTY_NOTTAXDEDUCTABLE = "notTaxdeductable";
    public static final String PROPERTY_ISTAXDEDUCTABLE = "istaxdeductable";
    public static final String PROPERTY_NOVAT = "noVAT";
    public static final String PROPERTY_BASEAMOUNT = "baseAmount";
    public static final String PROPERTY_TAXBASE = "taxBase";
    public static final String PROPERTY_DOCTAXAMOUNT = "docTaxAmount";
    public static final String PROPERTY_EPCGTAXREGION = "epcgTaxregion";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST = "businessPartnerWithholdingList";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_EPCGPROFORMALINELIST = "epcgProformalineList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTGLITEMLIST = "financialMgmtGLItemList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEPARENTTAXRATELIST = "financialMgmtTaxRateParentTaxRateList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATETAXBASELIST = "financialMgmtTaxRateTaxBaseList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSLIST = "financialMgmtTaxRateAccountsList";
    public static final String PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST = "financialMgmtTaxRegisterTypeLinesList";
    public static final String PROPERTY_FINANCIALMGMTTAXREGISTERLINELIST = "financialMgmtTaxRegisterlineList";
    public static final String PROPERTY_FINANCIALMGMTTAXREPORTLIST = "financialMgmtTaxReportList";
    public static final String PROPERTY_FINANCIALMGMTTAXTRLLIST = "financialMgmtTaxTrlList";
    public static final String PROPERTY_FINANCIALMGMTTAXZONELIST = "financialMgmtTaxZoneList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_INVOICELINETAXLIST = "invoiceLineTaxList";
    public static final String PROPERTY_INVOICETAXLIST = "invoiceTaxList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_ORDERLINETAXLIST = "orderLineTaxList";
    public static final String PROPERTY_ORDERTAXLIST = "orderTaxList";
    public static final String PROPERTY_ORGANIZATIONINFORMATIONLIST = "organizationInformationList";
    public static final String PROPERTY_PROJECTLINELIST = "projectLineList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST = "rCOBPurchasequotationlinesList";
    public static final String PROPERTY_RCOBQUOTATIONTAXLIST = "rCOBQuotationtaxList";
    public static final String PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST = "returnMaterialOrderPickEditLinesList";

    public TaxRate() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_TAXEXEMPT, false);
        setDefaultValue(PROPERTY_SALESPURCHASETYPE, "B");
        setDefaultValue(PROPERTY_CASCADE, false);
        setDefaultValue(PROPERTY_WITHHOLDINGTAX, false);
        setDefaultValue(PROPERTY_NOTTAXABLE, false);
        setDefaultValue(PROPERTY_NOTTAXDEDUCTABLE, false);
        setDefaultValue(PROPERTY_ISTAXDEDUCTABLE, false);
        setDefaultValue(PROPERTY_NOVAT, false);
        setDefaultValue(PROPERTY_BASEAMOUNT, "LNA");
        setDefaultValue(PROPERTY_DOCTAXAMOUNT, "D");
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPROFORMALINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLITEMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEPARENTTAXRATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATETAXBASELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXREGISTERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXREPORTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXZONELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINETAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICETAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINETAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERTAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBQUOTATIONTAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getTaxSearchKey() {
        return (String) get(PROPERTY_TAXSEARCHKEY);
    }

    public void setTaxSearchKey(String taxSearchKey) {
        set(PROPERTY_TAXSEARCHKEY, taxSearchKey);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public BigDecimal getRate() {
        return (BigDecimal) get(PROPERTY_RATE);
    }

    public void setRate(BigDecimal rate) {
        set(PROPERTY_RATE, rate);
    }

    public TaxRate getParentTaxRate() {
        return (TaxRate) get(PROPERTY_PARENTTAXRATE);
    }

    public void setParentTaxRate(TaxRate parentTaxRate) {
        set(PROPERTY_PARENTTAXRATE, parentTaxRate);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public Region getRegion() {
        return (Region) get(PROPERTY_REGION);
    }

    public void setRegion(Region region) {
        set(PROPERTY_REGION, region);
    }

    public Country getDestinationCountry() {
        return (Country) get(PROPERTY_DESTINATIONCOUNTRY);
    }

    public void setDestinationCountry(Country destinationCountry) {
        set(PROPERTY_DESTINATIONCOUNTRY, destinationCountry);
    }

    public Region getDestinationRegion() {
        return (Region) get(PROPERTY_DESTINATIONREGION);
    }

    public void setDestinationRegion(Region destinationRegion) {
        set(PROPERTY_DESTINATIONREGION, destinationRegion);
    }

    public TaxCategory getTaxCategory() {
        return (TaxCategory) get(PROPERTY_TAXCATEGORY);
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        set(PROPERTY_TAXCATEGORY, taxCategory);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public Boolean isTaxExempt() {
        return (Boolean) get(PROPERTY_TAXEXEMPT);
    }

    public void setTaxExempt(Boolean taxExempt) {
        set(PROPERTY_TAXEXEMPT, taxExempt);
    }

    public String getSalesPurchaseType() {
        return (String) get(PROPERTY_SALESPURCHASETYPE);
    }

    public void setSalesPurchaseType(String salesPurchaseType) {
        set(PROPERTY_SALESPURCHASETYPE, salesPurchaseType);
    }

    public Boolean isCascade() {
        return (Boolean) get(PROPERTY_CASCADE);
    }

    public void setCascade(Boolean cascade) {
        set(PROPERTY_CASCADE, cascade);
    }

    public org.openbravo.model.common.businesspartner.TaxCategory getBusinessPartnerTaxCategory() {
        return (org.openbravo.model.common.businesspartner.TaxCategory) get(PROPERTY_BUSINESSPARTNERTAXCATEGORY);
    }

    public void setBusinessPartnerTaxCategory(org.openbravo.model.common.businesspartner.TaxCategory businessPartnerTaxCategory) {
        set(PROPERTY_BUSINESSPARTNERTAXCATEGORY, businessPartnerTaxCategory);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Boolean isWithholdingTax() {
        return (Boolean) get(PROPERTY_WITHHOLDINGTAX);
    }

    public void setWithholdingTax(Boolean withholdingTax) {
        set(PROPERTY_WITHHOLDINGTAX, withholdingTax);
    }

    public Boolean isNotTaxable() {
        return (Boolean) get(PROPERTY_NOTTAXABLE);
    }

    public void setNotTaxable(Boolean notTaxable) {
        set(PROPERTY_NOTTAXABLE, notTaxable);
    }

    public BigDecimal getDeductableRate() {
        return (BigDecimal) get(PROPERTY_DEDUCTABLERATE);
    }

    public void setDeductableRate(BigDecimal deductableRate) {
        set(PROPERTY_DEDUCTABLERATE, deductableRate);
    }

    public BigDecimal getOriginalRate() {
        return (BigDecimal) get(PROPERTY_ORIGINALRATE);
    }

    public void setOriginalRate(BigDecimal originalRate) {
        set(PROPERTY_ORIGINALRATE, originalRate);
    }

    public Boolean isNotTaxdeductable() {
        return (Boolean) get(PROPERTY_NOTTAXDEDUCTABLE);
    }

    public void setNotTaxdeductable(Boolean notTaxdeductable) {
        set(PROPERTY_NOTTAXDEDUCTABLE, notTaxdeductable);
    }

    public Boolean isTaxdeductable() {
        return (Boolean) get(PROPERTY_ISTAXDEDUCTABLE);
    }

    public void setTaxdeductable(Boolean istaxdeductable) {
        set(PROPERTY_ISTAXDEDUCTABLE, istaxdeductable);
    }

    public Boolean isNoVAT() {
        return (Boolean) get(PROPERTY_NOVAT);
    }

    public void setNoVAT(Boolean noVAT) {
        set(PROPERTY_NOVAT, noVAT);
    }

    public String getBaseAmount() {
        return (String) get(PROPERTY_BASEAMOUNT);
    }

    public void setBaseAmount(String baseAmount) {
        set(PROPERTY_BASEAMOUNT, baseAmount);
    }

    public TaxRate getTaxBase() {
        return (TaxRate) get(PROPERTY_TAXBASE);
    }

    public void setTaxBase(TaxRate taxBase) {
        set(PROPERTY_TAXBASE, taxBase);
    }

    public String getDocTaxAmount() {
        return (String) get(PROPERTY_DOCTAXAMOUNT);
    }

    public void setDocTaxAmount(String docTaxAmount) {
        set(PROPERTY_DOCTAXAMOUNT, docTaxAmount);
    }

    public String getEpcgTaxregion() {
        return (String) get(PROPERTY_EPCGTAXREGION);
    }

    public void setEpcgTaxregion(String epcgTaxregion) {
        set(PROPERTY_EPCGTAXREGION, epcgTaxregion);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<Withholding> getBusinessPartnerWithholdingList() {
        return (List<Withholding>) get(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST);
    }

    public void setBusinessPartnerWithholdingList(List<Withholding> businessPartnerWithholdingList) {
        set(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST, businessPartnerWithholdingList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgProformaline> getEpcgProformalineList() {
        return (List<EpcgProformaline>) get(PROPERTY_EPCGPROFORMALINELIST);
    }

    public void setEpcgProformalineList(List<EpcgProformaline> epcgProformalineList) {
        set(PROPERTY_EPCGPROFORMALINELIST, epcgProformalineList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<GLItem> getFinancialMgmtGLItemList() {
        return (List<GLItem>) get(PROPERTY_FINANCIALMGMTGLITEMLIST);
    }

    public void setFinancialMgmtGLItemList(List<GLItem> financialMgmtGLItemList) {
        set(PROPERTY_FINANCIALMGMTGLITEMLIST, financialMgmtGLItemList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
        return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRate> getFinancialMgmtTaxRateParentTaxRateList() {
        return (List<TaxRate>) get(PROPERTY_FINANCIALMGMTTAXRATEPARENTTAXRATELIST);
    }

    public void setFinancialMgmtTaxRateParentTaxRateList(List<TaxRate> financialMgmtTaxRateParentTaxRateList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEPARENTTAXRATELIST, financialMgmtTaxRateParentTaxRateList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRate> getFinancialMgmtTaxRateTaxBaseList() {
        return (List<TaxRate>) get(PROPERTY_FINANCIALMGMTTAXRATETAXBASELIST);
    }

    public void setFinancialMgmtTaxRateTaxBaseList(List<TaxRate> financialMgmtTaxRateTaxBaseList) {
        set(PROPERTY_FINANCIALMGMTTAXRATETAXBASELIST, financialMgmtTaxRateTaxBaseList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRateAccounts> getFinancialMgmtTaxRateAccountsList() {
        return (List<TaxRateAccounts>) get(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSLIST);
    }

    public void setFinancialMgmtTaxRateAccountsList(List<TaxRateAccounts> financialMgmtTaxRateAccountsList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSLIST, financialMgmtTaxRateAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRegisterTypeLines> getFinancialMgmtTaxRegisterTypeLinesList() {
        return (List<TaxRegisterTypeLines>) get(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST);
    }

    public void setFinancialMgmtTaxRegisterTypeLinesList(List<TaxRegisterTypeLines> financialMgmtTaxRegisterTypeLinesList) {
        set(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST, financialMgmtTaxRegisterTypeLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRegisterline> getFinancialMgmtTaxRegisterlineList() {
        return (List<TaxRegisterline>) get(PROPERTY_FINANCIALMGMTTAXREGISTERLINELIST);
    }

    public void setFinancialMgmtTaxRegisterlineList(List<TaxRegisterline> financialMgmtTaxRegisterlineList) {
        set(PROPERTY_FINANCIALMGMTTAXREGISTERLINELIST, financialMgmtTaxRegisterlineList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxReport> getFinancialMgmtTaxReportList() {
        return (List<TaxReport>) get(PROPERTY_FINANCIALMGMTTAXREPORTLIST);
    }

    public void setFinancialMgmtTaxReportList(List<TaxReport> financialMgmtTaxReportList) {
        set(PROPERTY_FINANCIALMGMTTAXREPORTLIST, financialMgmtTaxReportList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxTrl> getFinancialMgmtTaxTrlList() {
        return (List<TaxTrl>) get(PROPERTY_FINANCIALMGMTTAXTRLLIST);
    }

    public void setFinancialMgmtTaxTrlList(List<TaxTrl> financialMgmtTaxTrlList) {
        set(PROPERTY_FINANCIALMGMTTAXTRLLIST, financialMgmtTaxTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxZone> getFinancialMgmtTaxZoneList() {
        return (List<TaxZone>) get(PROPERTY_FINANCIALMGMTTAXZONELIST);
    }

    public void setFinancialMgmtTaxZoneList(List<TaxZone> financialMgmtTaxZoneList) {
        set(PROPERTY_FINANCIALMGMTTAXZONELIST, financialMgmtTaxZoneList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineTax> getInvoiceLineTaxList() {
        return (List<InvoiceLineTax>) get(PROPERTY_INVOICELINETAXLIST);
    }

    public void setInvoiceLineTaxList(List<InvoiceLineTax> invoiceLineTaxList) {
        set(PROPERTY_INVOICELINETAXLIST, invoiceLineTaxList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceTax> getInvoiceTaxList() {
        return (List<InvoiceTax>) get(PROPERTY_INVOICETAXLIST);
    }

    public void setInvoiceTaxList(List<InvoiceTax> invoiceTaxList) {
        set(PROPERTY_INVOICETAXLIST, invoiceTaxList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineTax> getOrderLineTaxList() {
        return (List<OrderLineTax>) get(PROPERTY_ORDERLINETAXLIST);
    }

    public void setOrderLineTaxList(List<OrderLineTax> orderLineTaxList) {
        set(PROPERTY_ORDERLINETAXLIST, orderLineTaxList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderTax> getOrderTaxList() {
        return (List<OrderTax>) get(PROPERTY_ORDERTAXLIST);
    }

    public void setOrderTaxList(List<OrderTax> orderTaxList) {
        set(PROPERTY_ORDERTAXLIST, orderTaxList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationInformation> getOrganizationInformationList() {
        return (List<OrganizationInformation>) get(PROPERTY_ORGANIZATIONINFORMATIONLIST);
    }

    public void setOrganizationInformationList(List<OrganizationInformation> organizationInformationList) {
        set(PROPERTY_ORGANIZATIONINFORMATIONLIST, organizationInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectLine> getProjectLineList() {
        return (List<ProjectLine>) get(PROPERTY_PROJECTLINELIST);
    }

    public void setProjectLineList(List<ProjectLine> projectLineList) {
        set(PROPERTY_PROJECTLINELIST, projectLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseQuotationLines> getRCOBPurchasequotationlinesList() {
        return (List<PurchaseQuotationLines>) get(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST);
    }

    public void setRCOBPurchasequotationlinesList(List<PurchaseQuotationLines> rCOBPurchasequotationlinesList) {
        set(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST, rCOBPurchasequotationlinesList);
    }

    @SuppressWarnings("unchecked")
    public List<Quotationtax> getRCOBQuotationtaxList() {
        return (List<Quotationtax>) get(PROPERTY_RCOBQUOTATIONTAXLIST);
    }

    public void setRCOBQuotationtaxList(List<Quotationtax> rCOBQuotationtaxList) {
        set(PROPERTY_RCOBQUOTATIONTAXLIST, rCOBQuotationtaxList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialOrderPickEditLines> getReturnMaterialOrderPickEditLinesList() {
        return (List<ReturnMaterialOrderPickEditLines>) get(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST);
    }

    public void setReturnMaterialOrderPickEditLinesList(List<ReturnMaterialOrderPickEditLines> returnMaterialOrderPickEditLinesList) {
        set(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST, returnMaterialOrderPickEditLinesList);
    }

}
