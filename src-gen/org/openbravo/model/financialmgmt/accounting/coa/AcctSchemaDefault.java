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

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FinancialMgmtAcctSchemaDefault (stored in table C_AcctSchema_Default).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AcctSchemaDefault extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_AcctSchema_Default";
    public static final String ENTITY_NAME = "FinancialMgmtAcctSchemaDefault";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WAREHOUSEINVENTORY = "warehouseInventory";
    public static final String PROPERTY_INVENTORYADJUSTMENT = "inventoryAdjustment";
    public static final String PROPERTY_WAREHOUSEDIFFERENCES = "warehouseDifferences";
    public static final String PROPERTY_INVENTORYREVALUATION = "inventoryRevaluation";
    public static final String PROPERTY_PRODUCTREVENUE = "productRevenue";
    public static final String PROPERTY_PRODUCTEXPENSE = "productExpense";
    public static final String PROPERTY_FIXEDASSET = "fixedAsset";
    public static final String PROPERTY_PURCHASEPRICEVARIANCE = "purchasePriceVariance";
    public static final String PROPERTY_INVOICEPRICEVARIANCE = "invoicePriceVariance";
    public static final String PROPERTY_TRADEDISCOUNTRECEIVED = "tradeDiscountReceived";
    public static final String PROPERTY_TRADEDISCOUNTGRANTED = "tradeDiscountGranted";
    public static final String PROPERTY_PRODUCTCOGS = "productCOGS";
    public static final String PROPERTY_CUSTOMERRECEIVABLESNO = "customerReceivablesNo";
    public static final String PROPERTY_CUSTOMERPREPAYMENT = "customerPrepayment";
    public static final String PROPERTY_VENDORLIABILITY = "vendorLiability";
    public static final String PROPERTY_VENDORSERVICELIABILITY = "vendorServiceLiability";
    public static final String PROPERTY_VENDORPREPAYMENT = "vendorPrepayment";
    public static final String PROPERTY_PAYMENTDISCOUNTEXPENSE = "paymentDiscountExpense";
    public static final String PROPERTY_WRITEOFF = "writeoff";
    public static final String PROPERTY_WRITEOFFREVENUE = "writeoffRevenue";
    public static final String PROPERTY_PAYMENTDISCOUNTREVENUE = "paymentDiscountRevenue";
    public static final String PROPERTY_UNREALIZEDGAINSACCT = "unrealizedGainsAcct";
    public static final String PROPERTY_UNREALIZEDLOSSESACCT = "unrealizedLossesAcct";
    public static final String PROPERTY_REALIZEDGAINACCT = "realizedGainAcct";
    public static final String PROPERTY_REALIZEDLOSSACCT = "realizedLossAcct";
    public static final String PROPERTY_WITHHOLDINGACCOUNT = "withholdingAccount";
    public static final String PROPERTY_EMPLOYEEPREPAYMENTS = "employeePrepayments";
    public static final String PROPERTY_EMPLOYEEEXPENSES = "employeeExpenses";
    public static final String PROPERTY_PROJECTASSET = "projectAsset";
    public static final String PROPERTY_WORKINPROGRESS = "workInProgress";
    public static final String PROPERTY_TAXEXPENSE = "taxExpense";
    public static final String PROPERTY_TAXLIABILITY = "taxLiability";
    public static final String PROPERTY_TAXRECEIVABLES = "taxReceivables";
    public static final String PROPERTY_TAXDUE = "taxDue";
    public static final String PROPERTY_TAXCREDIT = "taxCredit";
    public static final String PROPERTY_BANKINTRANSIT = "bankInTransit";
    public static final String PROPERTY_BANKASSET = "bankAsset";
    public static final String PROPERTY_BANKEXPENSE = "bankExpense";
    public static final String PROPERTY_BANKINTERESTREVENUE = "bankInterestRevenue";
    public static final String PROPERTY_BANKINTERESTEXPENSE = "bankInterestExpense";
    public static final String PROPERTY_BANKUNIDENTIFIEDRECEIPTS = "bankUnidentifiedReceipts";
    public static final String PROPERTY_UNALLOCATEDCASH = "unallocatedCash";
    public static final String PROPERTY_PAYMENTSELECTION = "paymentSelection";
    public static final String PROPERTY_BANKSETTLEMENTGAIN = "bankSettlementGain";
    public static final String PROPERTY_BANKSETTLEMENTLOSS = "bankSettlementLoss";
    public static final String PROPERTY_BANKREVALUATIONGAIN = "bankRevaluationGain";
    public static final String PROPERTY_BANKREVALUATIONLOSS = "bankRevaluationLoss";
    public static final String PROPERTY_CHARGEEXPENSE = "chargeExpense";
    public static final String PROPERTY_CHARGEREVENUE = "chargeRevenue";
    public static final String PROPERTY_UNEARNEDREVENUE = "unearnedRevenue";
    public static final String PROPERTY_NONINVOICEDRECEIVABLES = "nonInvoicedReceivables";
    public static final String PROPERTY_NONINVOICEDREVENUES = "nonInvoicedRevenues";
    public static final String PROPERTY_NONINVOICEDRECEIPTS = "nonInvoicedReceipts";
    public static final String PROPERTY_CASHBOOKASSET = "cashBookAsset";
    public static final String PROPERTY_CASHTRANSFER = "cashTransfer";
    public static final String PROPERTY_CASHBOOKDIFFERENCES = "cashBookDifferences";
    public static final String PROPERTY_CASHBOOKEXPENSE = "cashBookExpense";
    public static final String PROPERTY_CASHBOOKRECEIPT = "cashBookReceipt";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_ACCUMULATEDDEPRECIATION = "accumulatedDepreciation";
    public static final String PROPERTY_DEPRECIATION = "depreciation";
    public static final String PROPERTY_DISPOSALGAIN = "disposalGain";
    public static final String PROPERTY_DISPOSALLOSS = "disposalLoss";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_PRODUCTREVENUERETURN = "productRevenueReturn";
    public static final String PROPERTY_PRODUCTCOGSRETURN = "productCOGSReturn";
    public static final String PROPERTY_PRODUCTDEFERREDREVENUE = "productDeferredRevenue";
    public static final String PROPERTY_PRODUCTDEFERREDEXPENSE = "productDeferredExpense";
    public static final String PROPERTY_DOUBTFULDEBTACCOUNT = "doubtfulDebtAccount";
    public static final String PROPERTY_BADDEBTEXPENSEACCOUNT = "badDebtExpenseAccount";
    public static final String PROPERTY_BADDEBTREVENUEACCOUNT = "badDebtRevenueAccount";
    public static final String PROPERTY_ALLOWANCEFORDOUBTFULDEBTACCOUNT = "allowanceForDoubtfulDebtAccount";

    public AcctSchemaDefault() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
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

    public AccountingCombination getWarehouseInventory() {
        return (AccountingCombination) get(PROPERTY_WAREHOUSEINVENTORY);
    }

    public void setWarehouseInventory(AccountingCombination warehouseInventory) {
        set(PROPERTY_WAREHOUSEINVENTORY, warehouseInventory);
    }

    public AccountingCombination getInventoryAdjustment() {
        return (AccountingCombination) get(PROPERTY_INVENTORYADJUSTMENT);
    }

    public void setInventoryAdjustment(AccountingCombination inventoryAdjustment) {
        set(PROPERTY_INVENTORYADJUSTMENT, inventoryAdjustment);
    }

    public AccountingCombination getWarehouseDifferences() {
        return (AccountingCombination) get(PROPERTY_WAREHOUSEDIFFERENCES);
    }

    public void setWarehouseDifferences(AccountingCombination warehouseDifferences) {
        set(PROPERTY_WAREHOUSEDIFFERENCES, warehouseDifferences);
    }

    public AccountingCombination getInventoryRevaluation() {
        return (AccountingCombination) get(PROPERTY_INVENTORYREVALUATION);
    }

    public void setInventoryRevaluation(AccountingCombination inventoryRevaluation) {
        set(PROPERTY_INVENTORYREVALUATION, inventoryRevaluation);
    }

    public AccountingCombination getProductRevenue() {
        return (AccountingCombination) get(PROPERTY_PRODUCTREVENUE);
    }

    public void setProductRevenue(AccountingCombination productRevenue) {
        set(PROPERTY_PRODUCTREVENUE, productRevenue);
    }

    public AccountingCombination getProductExpense() {
        return (AccountingCombination) get(PROPERTY_PRODUCTEXPENSE);
    }

    public void setProductExpense(AccountingCombination productExpense) {
        set(PROPERTY_PRODUCTEXPENSE, productExpense);
    }

    public AccountingCombination getFixedAsset() {
        return (AccountingCombination) get(PROPERTY_FIXEDASSET);
    }

    public void setFixedAsset(AccountingCombination fixedAsset) {
        set(PROPERTY_FIXEDASSET, fixedAsset);
    }

    public AccountingCombination getPurchasePriceVariance() {
        return (AccountingCombination) get(PROPERTY_PURCHASEPRICEVARIANCE);
    }

    public void setPurchasePriceVariance(AccountingCombination purchasePriceVariance) {
        set(PROPERTY_PURCHASEPRICEVARIANCE, purchasePriceVariance);
    }

    public AccountingCombination getInvoicePriceVariance() {
        return (AccountingCombination) get(PROPERTY_INVOICEPRICEVARIANCE);
    }

    public void setInvoicePriceVariance(AccountingCombination invoicePriceVariance) {
        set(PROPERTY_INVOICEPRICEVARIANCE, invoicePriceVariance);
    }

    public AccountingCombination getTradeDiscountReceived() {
        return (AccountingCombination) get(PROPERTY_TRADEDISCOUNTRECEIVED);
    }

    public void setTradeDiscountReceived(AccountingCombination tradeDiscountReceived) {
        set(PROPERTY_TRADEDISCOUNTRECEIVED, tradeDiscountReceived);
    }

    public AccountingCombination getTradeDiscountGranted() {
        return (AccountingCombination) get(PROPERTY_TRADEDISCOUNTGRANTED);
    }

    public void setTradeDiscountGranted(AccountingCombination tradeDiscountGranted) {
        set(PROPERTY_TRADEDISCOUNTGRANTED, tradeDiscountGranted);
    }

    public AccountingCombination getProductCOGS() {
        return (AccountingCombination) get(PROPERTY_PRODUCTCOGS);
    }

    public void setProductCOGS(AccountingCombination productCOGS) {
        set(PROPERTY_PRODUCTCOGS, productCOGS);
    }

    public AccountingCombination getCustomerReceivablesNo() {
        return (AccountingCombination) get(PROPERTY_CUSTOMERRECEIVABLESNO);
    }

    public void setCustomerReceivablesNo(AccountingCombination customerReceivablesNo) {
        set(PROPERTY_CUSTOMERRECEIVABLESNO, customerReceivablesNo);
    }

    public AccountingCombination getCustomerPrepayment() {
        return (AccountingCombination) get(PROPERTY_CUSTOMERPREPAYMENT);
    }

    public void setCustomerPrepayment(AccountingCombination customerPrepayment) {
        set(PROPERTY_CUSTOMERPREPAYMENT, customerPrepayment);
    }

    public AccountingCombination getVendorLiability() {
        return (AccountingCombination) get(PROPERTY_VENDORLIABILITY);
    }

    public void setVendorLiability(AccountingCombination vendorLiability) {
        set(PROPERTY_VENDORLIABILITY, vendorLiability);
    }

    public AccountingCombination getVendorServiceLiability() {
        return (AccountingCombination) get(PROPERTY_VENDORSERVICELIABILITY);
    }

    public void setVendorServiceLiability(AccountingCombination vendorServiceLiability) {
        set(PROPERTY_VENDORSERVICELIABILITY, vendorServiceLiability);
    }

    public AccountingCombination getVendorPrepayment() {
        return (AccountingCombination) get(PROPERTY_VENDORPREPAYMENT);
    }

    public void setVendorPrepayment(AccountingCombination vendorPrepayment) {
        set(PROPERTY_VENDORPREPAYMENT, vendorPrepayment);
    }

    public AccountingCombination getPaymentDiscountExpense() {
        return (AccountingCombination) get(PROPERTY_PAYMENTDISCOUNTEXPENSE);
    }

    public void setPaymentDiscountExpense(AccountingCombination paymentDiscountExpense) {
        set(PROPERTY_PAYMENTDISCOUNTEXPENSE, paymentDiscountExpense);
    }

    public AccountingCombination getWriteoff() {
        return (AccountingCombination) get(PROPERTY_WRITEOFF);
    }

    public void setWriteoff(AccountingCombination writeoff) {
        set(PROPERTY_WRITEOFF, writeoff);
    }

    public AccountingCombination getWriteoffRevenue() {
        return (AccountingCombination) get(PROPERTY_WRITEOFFREVENUE);
    }

    public void setWriteoffRevenue(AccountingCombination writeoffRevenue) {
        set(PROPERTY_WRITEOFFREVENUE, writeoffRevenue);
    }

    public AccountingCombination getPaymentDiscountRevenue() {
        return (AccountingCombination) get(PROPERTY_PAYMENTDISCOUNTREVENUE);
    }

    public void setPaymentDiscountRevenue(AccountingCombination paymentDiscountRevenue) {
        set(PROPERTY_PAYMENTDISCOUNTREVENUE, paymentDiscountRevenue);
    }

    public AccountingCombination getUnrealizedGainsAcct() {
        return (AccountingCombination) get(PROPERTY_UNREALIZEDGAINSACCT);
    }

    public void setUnrealizedGainsAcct(AccountingCombination unrealizedGainsAcct) {
        set(PROPERTY_UNREALIZEDGAINSACCT, unrealizedGainsAcct);
    }

    public AccountingCombination getUnrealizedLossesAcct() {
        return (AccountingCombination) get(PROPERTY_UNREALIZEDLOSSESACCT);
    }

    public void setUnrealizedLossesAcct(AccountingCombination unrealizedLossesAcct) {
        set(PROPERTY_UNREALIZEDLOSSESACCT, unrealizedLossesAcct);
    }

    public AccountingCombination getRealizedGainAcct() {
        return (AccountingCombination) get(PROPERTY_REALIZEDGAINACCT);
    }

    public void setRealizedGainAcct(AccountingCombination realizedGainAcct) {
        set(PROPERTY_REALIZEDGAINACCT, realizedGainAcct);
    }

    public AccountingCombination getRealizedLossAcct() {
        return (AccountingCombination) get(PROPERTY_REALIZEDLOSSACCT);
    }

    public void setRealizedLossAcct(AccountingCombination realizedLossAcct) {
        set(PROPERTY_REALIZEDLOSSACCT, realizedLossAcct);
    }

    public AccountingCombination getWithholdingAccount() {
        return (AccountingCombination) get(PROPERTY_WITHHOLDINGACCOUNT);
    }

    public void setWithholdingAccount(AccountingCombination withholdingAccount) {
        set(PROPERTY_WITHHOLDINGACCOUNT, withholdingAccount);
    }

    public AccountingCombination getEmployeePrepayments() {
        return (AccountingCombination) get(PROPERTY_EMPLOYEEPREPAYMENTS);
    }

    public void setEmployeePrepayments(AccountingCombination employeePrepayments) {
        set(PROPERTY_EMPLOYEEPREPAYMENTS, employeePrepayments);
    }

    public AccountingCombination getEmployeeExpenses() {
        return (AccountingCombination) get(PROPERTY_EMPLOYEEEXPENSES);
    }

    public void setEmployeeExpenses(AccountingCombination employeeExpenses) {
        set(PROPERTY_EMPLOYEEEXPENSES, employeeExpenses);
    }

    public AccountingCombination getProjectAsset() {
        return (AccountingCombination) get(PROPERTY_PROJECTASSET);
    }

    public void setProjectAsset(AccountingCombination projectAsset) {
        set(PROPERTY_PROJECTASSET, projectAsset);
    }

    public AccountingCombination getWorkInProgress() {
        return (AccountingCombination) get(PROPERTY_WORKINPROGRESS);
    }

    public void setWorkInProgress(AccountingCombination workInProgress) {
        set(PROPERTY_WORKINPROGRESS, workInProgress);
    }

    public AccountingCombination getTaxExpense() {
        return (AccountingCombination) get(PROPERTY_TAXEXPENSE);
    }

    public void setTaxExpense(AccountingCombination taxExpense) {
        set(PROPERTY_TAXEXPENSE, taxExpense);
    }

    public AccountingCombination getTaxLiability() {
        return (AccountingCombination) get(PROPERTY_TAXLIABILITY);
    }

    public void setTaxLiability(AccountingCombination taxLiability) {
        set(PROPERTY_TAXLIABILITY, taxLiability);
    }

    public AccountingCombination getTaxReceivables() {
        return (AccountingCombination) get(PROPERTY_TAXRECEIVABLES);
    }

    public void setTaxReceivables(AccountingCombination taxReceivables) {
        set(PROPERTY_TAXRECEIVABLES, taxReceivables);
    }

    public AccountingCombination getTaxDue() {
        return (AccountingCombination) get(PROPERTY_TAXDUE);
    }

    public void setTaxDue(AccountingCombination taxDue) {
        set(PROPERTY_TAXDUE, taxDue);
    }

    public AccountingCombination getTaxCredit() {
        return (AccountingCombination) get(PROPERTY_TAXCREDIT);
    }

    public void setTaxCredit(AccountingCombination taxCredit) {
        set(PROPERTY_TAXCREDIT, taxCredit);
    }

    public AccountingCombination getBankInTransit() {
        return (AccountingCombination) get(PROPERTY_BANKINTRANSIT);
    }

    public void setBankInTransit(AccountingCombination bankInTransit) {
        set(PROPERTY_BANKINTRANSIT, bankInTransit);
    }

    public AccountingCombination getBankAsset() {
        return (AccountingCombination) get(PROPERTY_BANKASSET);
    }

    public void setBankAsset(AccountingCombination bankAsset) {
        set(PROPERTY_BANKASSET, bankAsset);
    }

    public AccountingCombination getBankExpense() {
        return (AccountingCombination) get(PROPERTY_BANKEXPENSE);
    }

    public void setBankExpense(AccountingCombination bankExpense) {
        set(PROPERTY_BANKEXPENSE, bankExpense);
    }

    public AccountingCombination getBankInterestRevenue() {
        return (AccountingCombination) get(PROPERTY_BANKINTERESTREVENUE);
    }

    public void setBankInterestRevenue(AccountingCombination bankInterestRevenue) {
        set(PROPERTY_BANKINTERESTREVENUE, bankInterestRevenue);
    }

    public AccountingCombination getBankInterestExpense() {
        return (AccountingCombination) get(PROPERTY_BANKINTERESTEXPENSE);
    }

    public void setBankInterestExpense(AccountingCombination bankInterestExpense) {
        set(PROPERTY_BANKINTERESTEXPENSE, bankInterestExpense);
    }

    public AccountingCombination getBankUnidentifiedReceipts() {
        return (AccountingCombination) get(PROPERTY_BANKUNIDENTIFIEDRECEIPTS);
    }

    public void setBankUnidentifiedReceipts(AccountingCombination bankUnidentifiedReceipts) {
        set(PROPERTY_BANKUNIDENTIFIEDRECEIPTS, bankUnidentifiedReceipts);
    }

    public AccountingCombination getUnallocatedCash() {
        return (AccountingCombination) get(PROPERTY_UNALLOCATEDCASH);
    }

    public void setUnallocatedCash(AccountingCombination unallocatedCash) {
        set(PROPERTY_UNALLOCATEDCASH, unallocatedCash);
    }

    public AccountingCombination getPaymentSelection() {
        return (AccountingCombination) get(PROPERTY_PAYMENTSELECTION);
    }

    public void setPaymentSelection(AccountingCombination paymentSelection) {
        set(PROPERTY_PAYMENTSELECTION, paymentSelection);
    }

    public AccountingCombination getBankSettlementGain() {
        return (AccountingCombination) get(PROPERTY_BANKSETTLEMENTGAIN);
    }

    public void setBankSettlementGain(AccountingCombination bankSettlementGain) {
        set(PROPERTY_BANKSETTLEMENTGAIN, bankSettlementGain);
    }

    public AccountingCombination getBankSettlementLoss() {
        return (AccountingCombination) get(PROPERTY_BANKSETTLEMENTLOSS);
    }

    public void setBankSettlementLoss(AccountingCombination bankSettlementLoss) {
        set(PROPERTY_BANKSETTLEMENTLOSS, bankSettlementLoss);
    }

    public AccountingCombination getBankRevaluationGain() {
        return (AccountingCombination) get(PROPERTY_BANKREVALUATIONGAIN);
    }

    public void setBankRevaluationGain(AccountingCombination bankRevaluationGain) {
        set(PROPERTY_BANKREVALUATIONGAIN, bankRevaluationGain);
    }

    public AccountingCombination getBankRevaluationLoss() {
        return (AccountingCombination) get(PROPERTY_BANKREVALUATIONLOSS);
    }

    public void setBankRevaluationLoss(AccountingCombination bankRevaluationLoss) {
        set(PROPERTY_BANKREVALUATIONLOSS, bankRevaluationLoss);
    }

    public AccountingCombination getChargeExpense() {
        return (AccountingCombination) get(PROPERTY_CHARGEEXPENSE);
    }

    public void setChargeExpense(AccountingCombination chargeExpense) {
        set(PROPERTY_CHARGEEXPENSE, chargeExpense);
    }

    public AccountingCombination getChargeRevenue() {
        return (AccountingCombination) get(PROPERTY_CHARGEREVENUE);
    }

    public void setChargeRevenue(AccountingCombination chargeRevenue) {
        set(PROPERTY_CHARGEREVENUE, chargeRevenue);
    }

    public AccountingCombination getUnearnedRevenue() {
        return (AccountingCombination) get(PROPERTY_UNEARNEDREVENUE);
    }

    public void setUnearnedRevenue(AccountingCombination unearnedRevenue) {
        set(PROPERTY_UNEARNEDREVENUE, unearnedRevenue);
    }

    public AccountingCombination getNonInvoicedReceivables() {
        return (AccountingCombination) get(PROPERTY_NONINVOICEDRECEIVABLES);
    }

    public void setNonInvoicedReceivables(AccountingCombination nonInvoicedReceivables) {
        set(PROPERTY_NONINVOICEDRECEIVABLES, nonInvoicedReceivables);
    }

    public AccountingCombination getNonInvoicedRevenues() {
        return (AccountingCombination) get(PROPERTY_NONINVOICEDREVENUES);
    }

    public void setNonInvoicedRevenues(AccountingCombination nonInvoicedRevenues) {
        set(PROPERTY_NONINVOICEDREVENUES, nonInvoicedRevenues);
    }

    public AccountingCombination getNonInvoicedReceipts() {
        return (AccountingCombination) get(PROPERTY_NONINVOICEDRECEIPTS);
    }

    public void setNonInvoicedReceipts(AccountingCombination nonInvoicedReceipts) {
        set(PROPERTY_NONINVOICEDRECEIPTS, nonInvoicedReceipts);
    }

    public AccountingCombination getCashBookAsset() {
        return (AccountingCombination) get(PROPERTY_CASHBOOKASSET);
    }

    public void setCashBookAsset(AccountingCombination cashBookAsset) {
        set(PROPERTY_CASHBOOKASSET, cashBookAsset);
    }

    public AccountingCombination getCashTransfer() {
        return (AccountingCombination) get(PROPERTY_CASHTRANSFER);
    }

    public void setCashTransfer(AccountingCombination cashTransfer) {
        set(PROPERTY_CASHTRANSFER, cashTransfer);
    }

    public AccountingCombination getCashBookDifferences() {
        return (AccountingCombination) get(PROPERTY_CASHBOOKDIFFERENCES);
    }

    public void setCashBookDifferences(AccountingCombination cashBookDifferences) {
        set(PROPERTY_CASHBOOKDIFFERENCES, cashBookDifferences);
    }

    public AccountingCombination getCashBookExpense() {
        return (AccountingCombination) get(PROPERTY_CASHBOOKEXPENSE);
    }

    public void setCashBookExpense(AccountingCombination cashBookExpense) {
        set(PROPERTY_CASHBOOKEXPENSE, cashBookExpense);
    }

    public AccountingCombination getCashBookReceipt() {
        return (AccountingCombination) get(PROPERTY_CASHBOOKRECEIPT);
    }

    public void setCashBookReceipt(AccountingCombination cashBookReceipt) {
        set(PROPERTY_CASHBOOKRECEIPT, cashBookReceipt);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public AccountingCombination getAccumulatedDepreciation() {
        return (AccountingCombination) get(PROPERTY_ACCUMULATEDDEPRECIATION);
    }

    public void setAccumulatedDepreciation(AccountingCombination accumulatedDepreciation) {
        set(PROPERTY_ACCUMULATEDDEPRECIATION, accumulatedDepreciation);
    }

    public AccountingCombination getDepreciation() {
        return (AccountingCombination) get(PROPERTY_DEPRECIATION);
    }

    public void setDepreciation(AccountingCombination depreciation) {
        set(PROPERTY_DEPRECIATION, depreciation);
    }

    public AccountingCombination getDisposalGain() {
        return (AccountingCombination) get(PROPERTY_DISPOSALGAIN);
    }

    public void setDisposalGain(AccountingCombination disposalGain) {
        set(PROPERTY_DISPOSALGAIN, disposalGain);
    }

    public AccountingCombination getDisposalLoss() {
        return (AccountingCombination) get(PROPERTY_DISPOSALLOSS);
    }

    public void setDisposalLoss(AccountingCombination disposalLoss) {
        set(PROPERTY_DISPOSALLOSS, disposalLoss);
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public AccountingCombination getProductRevenueReturn() {
        return (AccountingCombination) get(PROPERTY_PRODUCTREVENUERETURN);
    }

    public void setProductRevenueReturn(AccountingCombination productRevenueReturn) {
        set(PROPERTY_PRODUCTREVENUERETURN, productRevenueReturn);
    }

    public AccountingCombination getProductCOGSReturn() {
        return (AccountingCombination) get(PROPERTY_PRODUCTCOGSRETURN);
    }

    public void setProductCOGSReturn(AccountingCombination productCOGSReturn) {
        set(PROPERTY_PRODUCTCOGSRETURN, productCOGSReturn);
    }

    public AccountingCombination getProductDeferredRevenue() {
        return (AccountingCombination) get(PROPERTY_PRODUCTDEFERREDREVENUE);
    }

    public void setProductDeferredRevenue(AccountingCombination productDeferredRevenue) {
        set(PROPERTY_PRODUCTDEFERREDREVENUE, productDeferredRevenue);
    }

    public AccountingCombination getProductDeferredExpense() {
        return (AccountingCombination) get(PROPERTY_PRODUCTDEFERREDEXPENSE);
    }

    public void setProductDeferredExpense(AccountingCombination productDeferredExpense) {
        set(PROPERTY_PRODUCTDEFERREDEXPENSE, productDeferredExpense);
    }

    public AccountingCombination getDoubtfulDebtAccount() {
        return (AccountingCombination) get(PROPERTY_DOUBTFULDEBTACCOUNT);
    }

    public void setDoubtfulDebtAccount(AccountingCombination doubtfulDebtAccount) {
        set(PROPERTY_DOUBTFULDEBTACCOUNT, doubtfulDebtAccount);
    }

    public AccountingCombination getBadDebtExpenseAccount() {
        return (AccountingCombination) get(PROPERTY_BADDEBTEXPENSEACCOUNT);
    }

    public void setBadDebtExpenseAccount(AccountingCombination badDebtExpenseAccount) {
        set(PROPERTY_BADDEBTEXPENSEACCOUNT, badDebtExpenseAccount);
    }

    public AccountingCombination getBadDebtRevenueAccount() {
        return (AccountingCombination) get(PROPERTY_BADDEBTREVENUEACCOUNT);
    }

    public void setBadDebtRevenueAccount(AccountingCombination badDebtRevenueAccount) {
        set(PROPERTY_BADDEBTREVENUEACCOUNT, badDebtRevenueAccount);
    }

    public AccountingCombination getAllowanceForDoubtfulDebtAccount() {
        return (AccountingCombination) get(PROPERTY_ALLOWANCEFORDOUBTFULDEBTACCOUNT);
    }

    public void setAllowanceForDoubtfulDebtAccount(AccountingCombination allowanceForDoubtfulDebtAccount) {
        set(PROPERTY_ALLOWANCEFORDOUBTFULDEBTACCOUNT, allowanceForDoubtfulDebtAccount);
    }

}
