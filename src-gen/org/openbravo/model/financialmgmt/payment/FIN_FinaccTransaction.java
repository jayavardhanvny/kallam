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
package org.openbravo.model.financialmgmt.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_FinaccTransactionV;
import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.ConversionRateDoc;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.project.Project;
import org.openbravo.model.sales.SalesRegion;
/**
 * Entity class for entity FIN_Finacc_Transaction (stored in table FIN_Finacc_Transaction).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_FinaccTransaction extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Finacc_Transaction";
    public static final String ENTITY_NAME = "FIN_Finacc_Transaction";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_FINPAYMENT = "finPayment";
    public static final String PROPERTY_DATEACCT = "dateAcct";
    public static final String PROPERTY_GLITEM = "gLItem";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_PAYMENTAMOUNT = "paymentAmount";
    public static final String PROPERTY_DEPOSITAMOUNT = "depositAmount";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_TRANSACTIONTYPE = "transactionType";
    public static final String PROPERTY_TRANSACTIONDATE = "transactionDate";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_RECONCILIATION = "reconciliation";
    public static final String PROPERTY_CREATEDBYALGORITHM = "createdByAlgorithm";
    public static final String PROPERTY_FOREIGNCURRENCY = "foreignCurrency";
    public static final String PROPERTY_FOREIGNCONVERSIONRATE = "foreignConversionRate";
    public static final String PROPERTY_FOREIGNAMOUNT = "foreignAmount";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_SALESREGION = "salesRegion";
    public static final String PROPERTY_APRMDELETE = "aPRMDelete";
    public static final String PROPERTY_APRMMODIFY = "aPRMModify";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONVLIST = "aPRMFinaccTransactionVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_CURRENCYCONVERSIONRATEDOCLIST = "currencyConversionRateDocList";
    public static final String PROPERTY_FINBANKSTATEMENTLINELIST = "fINBankStatementLineList";
    public static final String PROPERTY_FINRECONCILIATIONLINETEMPLIST = "fINReconciliationLineTempList";
    public static final String PROPERTY_FINRECONCILIATIONLINEVLIST = "fINReconciliationLineVList";

    public FIN_FinaccTransaction() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PAYMENTAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEPOSITAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_CREATEDBYALGORITHM, false);
        setDefaultValue(PROPERTY_APRMDELETE, false);
        setDefaultValue(PROPERTY_APRMMODIFY, false);
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CURRENCYCONVERSIONRATEDOCLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINBANKSTATEMENTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINRECONCILIATIONLINETEMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINRECONCILIATIONLINEVLIST, new ArrayList<Object>());
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public FIN_FinancialAccount getAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(FIN_FinancialAccount account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public FIN_Payment getFinPayment() {
        return (FIN_Payment) get(PROPERTY_FINPAYMENT);
    }

    public void setFinPayment(FIN_Payment finPayment) {
        set(PROPERTY_FINPAYMENT, finPayment);
    }

    public Date getDateAcct() {
        return (Date) get(PROPERTY_DATEACCT);
    }

    public void setDateAcct(Date dateAcct) {
        set(PROPERTY_DATEACCT, dateAcct);
    }

    public GLItem getGLItem() {
        return (GLItem) get(PROPERTY_GLITEM);
    }

    public void setGLItem(GLItem gLItem) {
        set(PROPERTY_GLITEM, gLItem);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public BigDecimal getPaymentAmount() {
        return (BigDecimal) get(PROPERTY_PAYMENTAMOUNT);
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        set(PROPERTY_PAYMENTAMOUNT, paymentAmount);
    }

    public BigDecimal getDepositAmount() {
        return (BigDecimal) get(PROPERTY_DEPOSITAMOUNT);
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        set(PROPERTY_DEPOSITAMOUNT, depositAmount);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
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

    public String getTransactionType() {
        return (String) get(PROPERTY_TRANSACTIONTYPE);
    }

    public void setTransactionType(String transactionType) {
        set(PROPERTY_TRANSACTIONTYPE, transactionType);
    }

    public Date getTransactionDate() {
        return (Date) get(PROPERTY_TRANSACTIONDATE);
    }

    public void setTransactionDate(Date transactionDate) {
        set(PROPERTY_TRANSACTIONDATE, transactionDate);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public FIN_Reconciliation getReconciliation() {
        return (FIN_Reconciliation) get(PROPERTY_RECONCILIATION);
    }

    public void setReconciliation(FIN_Reconciliation reconciliation) {
        set(PROPERTY_RECONCILIATION, reconciliation);
    }

    public Boolean isCreatedByAlgorithm() {
        return (Boolean) get(PROPERTY_CREATEDBYALGORITHM);
    }

    public void setCreatedByAlgorithm(Boolean createdByAlgorithm) {
        set(PROPERTY_CREATEDBYALGORITHM, createdByAlgorithm);
    }

    public Currency getForeignCurrency() {
        return (Currency) get(PROPERTY_FOREIGNCURRENCY);
    }

    public void setForeignCurrency(Currency foreignCurrency) {
        set(PROPERTY_FOREIGNCURRENCY, foreignCurrency);
    }

    public BigDecimal getForeignConversionRate() {
        return (BigDecimal) get(PROPERTY_FOREIGNCONVERSIONRATE);
    }

    public void setForeignConversionRate(BigDecimal foreignConversionRate) {
        set(PROPERTY_FOREIGNCONVERSIONRATE, foreignConversionRate);
    }

    public BigDecimal getForeignAmount() {
        return (BigDecimal) get(PROPERTY_FOREIGNAMOUNT);
    }

    public void setForeignAmount(BigDecimal foreignAmount) {
        set(PROPERTY_FOREIGNAMOUNT, foreignAmount);
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

    public SalesRegion getSalesRegion() {
        return (SalesRegion) get(PROPERTY_SALESREGION);
    }

    public void setSalesRegion(SalesRegion salesRegion) {
        set(PROPERTY_SALESREGION, salesRegion);
    }

    public Boolean isAPRMDelete() {
        return (Boolean) get(PROPERTY_APRMDELETE);
    }

    public void setAPRMDelete(Boolean aPRMDelete) {
        set(PROPERTY_APRMDELETE, aPRMDelete);
    }

    public Boolean isAPRMModify() {
        return (Boolean) get(PROPERTY_APRMMODIFY);
    }

    public void setAPRMModify(Boolean aPRMModify) {
        set(PROPERTY_APRMMODIFY, aPRMModify);
    }

    public Costcenter getCostCenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(Costcenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_FinaccTransactionV> getAPRMFinaccTransactionVList() {
        return (List<APRM_FinaccTransactionV>) get(PROPERTY_APRMFINACCTRANSACTIONVLIST);
    }

    public void setAPRMFinaccTransactionVList(List<APRM_FinaccTransactionV> aPRMFinaccTransactionVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONVLIST, aPRMFinaccTransactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<ConversionRateDoc> getCurrencyConversionRateDocList() {
        return (List<ConversionRateDoc>) get(PROPERTY_CURRENCYCONVERSIONRATEDOCLIST);
    }

    public void setCurrencyConversionRateDocList(List<ConversionRateDoc> currencyConversionRateDocList) {
        set(PROPERTY_CURRENCYCONVERSIONRATEDOCLIST, currencyConversionRateDocList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_BankStatementLine> getFINBankStatementLineList() {
        return (List<FIN_BankStatementLine>) get(PROPERTY_FINBANKSTATEMENTLINELIST);
    }

    public void setFINBankStatementLineList(List<FIN_BankStatementLine> fINBankStatementLineList) {
        set(PROPERTY_FINBANKSTATEMENTLINELIST, fINBankStatementLineList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_ReconciliationLineTemp> getFINReconciliationLineTempList() {
        return (List<FIN_ReconciliationLineTemp>) get(PROPERTY_FINRECONCILIATIONLINETEMPLIST);
    }

    public void setFINReconciliationLineTempList(List<FIN_ReconciliationLineTemp> fINReconciliationLineTempList) {
        set(PROPERTY_FINRECONCILIATIONLINETEMPLIST, fINReconciliationLineTempList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_ReconciliationLine_v> getFINReconciliationLineVList() {
        return (List<FIN_ReconciliationLine_v>) get(PROPERTY_FINRECONCILIATIONLINEVLIST);
    }

    public void setFINReconciliationLineVList(List<FIN_ReconciliationLine_v> fINReconciliationLineVList) {
        set(PROPERTY_FINRECONCILIATIONLINEVLIST, fINReconciliationLineVList);
    }

}
