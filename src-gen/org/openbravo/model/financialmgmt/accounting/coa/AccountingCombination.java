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

import com.redcarpet.production.data.RCPR_PrExpenseType;
import com.redcarpet.production.data.RCPR_PrProcessLevel;

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
import org.openbravo.model.common.bank.BankAccountAccounts;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.CategoryAccounts;
import org.openbravo.model.common.businesspartner.CustomerAccounts;
import org.openbravo.model.common.businesspartner.EmployeeAccounts;
import org.openbravo.model.common.businesspartner.VendorAccounts;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.WarehouseAccounts;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductAccounts;
import org.openbravo.model.financialmgmt.accounting.FIN_FinancialAccountAccounting;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.assetmgmt.AssetAccounts;
import org.openbravo.model.financialmgmt.assetmgmt.AssetGroupAcct;
import org.openbravo.model.financialmgmt.cashmgmt.CashBookAccounts;
import org.openbravo.model.financialmgmt.gl.GLChargeAccounts;
import org.openbravo.model.financialmgmt.gl.GLItemAccounts;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.financialmgmt.tax.TaxRateAccounts;
import org.openbravo.model.financialmgmt.tax.WithholdingAccounts;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectAccounts;
import org.openbravo.model.sales.SalesRegion;
/**
 * Entity class for entity FinancialMgmtAccountingCombination (stored in table C_ValidCombination).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AccountingCombination extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_ValidCombination";
    public static final String ENTITY_NAME = "FinancialMgmtAccountingCombination";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ALIAS = "alias";
    public static final String PROPERTY_COMBINATION = "combination";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_FULLYQUALIFIED = "fullyQualified";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_ACCOUNT = "account";
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
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKINTRANSITLIST = "bankAccountAccountsBankInTransitList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKASSETLIST = "bankAccountAccountsBankAssetList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKEXPENSELIST = "bankAccountAccountsBankExpenseList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKINTERESTREVENUELIST = "bankAccountAccountsBankInterestRevenueList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKINTERESTEXPENSELIST = "bankAccountAccountsBankInterestExpenseList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKUNIDENTIFIEDRECEIPTSLIST = "bankAccountAccountsBankUnidentifiedReceiptsList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSUNALLOCATEDCASHLIST = "bankAccountAccountsUnallocatedCashList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSPAYMENTSELECTIONLIST = "bankAccountAccountsPaymentSelectionList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKSETTLEMENTGAINLIST = "bankAccountAccountsBankSettlementGainList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKSETTLEMENTLOSSLIST = "bankAccountAccountsBankSettlementLossList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKREVALUATIONGAINLIST = "bankAccountAccountsBankRevaluationGainList";
    public static final String PROPERTY_BANKACCOUNTACCOUNTSBANKREVALUATIONLOSSLIST = "bankAccountAccountsBankRevaluationLossList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTCUSTOMERRECEIVABLESNOLIST = "businessPartnerCategoryAccountCustomerReceivablesNoList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTCUSTOMERPREPAYMENTLIST = "businessPartnerCategoryAccountCustomerPrepaymentList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORLIABILITYLIST = "businessPartnerCategoryAccountVendorLiabilityList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORSERVICELIABILITYLIST = "businessPartnerCategoryAccountVendorServiceLiabilityList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORPREPAYMENTLIST = "businessPartnerCategoryAccountVendorPrepaymentList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTPAYMENTDISCOUNTEXPENSELIST = "businessPartnerCategoryAccountPaymentDiscountExpenseList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTPAYMENTDISCOUNTREVENUELIST = "businessPartnerCategoryAccountPaymentDiscountRevenueList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTWRITEOFFLIST = "businessPartnerCategoryAccountWriteoffList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNREALIZEDGAINSACCTLIST = "businessPartnerCategoryAccountUnrealizedGainsAcctList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNREALIZEDLOSSESACCTLIST = "businessPartnerCategoryAccountUnrealizedLossesAcctList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTREALIZEDGAINACCTLIST = "businessPartnerCategoryAccountRealizedGainAcctList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTREALIZEDLOSSACCTLIST = "businessPartnerCategoryAccountRealizedLossAcctList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDRECEIPTSLIST = "businessPartnerCategoryAccountNonInvoicedReceiptsList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNEARNEDREVENUELIST = "businessPartnerCategoryAccountUnearnedRevenueList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDREVENUESLIST = "businessPartnerCategoryAccountNonInvoicedRevenuesList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDRECEIVABLESLIST = "businessPartnerCategoryAccountNonInvoicedReceivablesList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTWRITEOFFREVACCTLIST = "businessPartnerCategoryAccountWriteoffRevAcctList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTDOUBTFULDEBTACCOUNTLIST = "businessPartnerCategoryAccountDoubtfulDebtAccountList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTBADDEBTEXPENSEACCOUNTLIST = "businessPartnerCategoryAccountBadDebtExpenseAccountList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTBADDEBTREVENUEACCOUNTLIST = "businessPartnerCategoryAccountBadDebtRevenueAccountList";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTALLOWANCEFORDOUBTFULDEBTACCOUNTLIST = "businessPartnerCategoryAccountAllowanceForDoubtfulDebtAccountList";
    public static final String PROPERTY_CUSTOMERACCOUNTSCUSTOMERRECEIVABLESNOLIST = "customerAccountsCustomerReceivablesNoList";
    public static final String PROPERTY_CUSTOMERACCOUNTSCUSTOMERPREPAYMENTLIST = "customerAccountsCustomerPrepaymentList";
    public static final String PROPERTY_EMPLOYEEACCOUNTSEMPLOYEEEXPENSESLIST = "employeeAccountsEmployeeExpensesList";
    public static final String PROPERTY_EMPLOYEEACCOUNTSEMPLOYEEPREPAYMENTSLIST = "employeeAccountsEmployeePrepaymentsList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTRECEIVEPAYMENTACCOUNTLIST = "fINFinancialAccountAcctReceivePaymentAccountList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTMAKEPAYMENTACCOUNTLIST = "fINFinancialAccountAcctMakePaymentAccountList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTDEPOSITACCOUNTLIST = "fINFinancialAccountAcctDepositAccountList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTWITHDRAWALACCOUNTLIST = "fINFinancialAccountAcctWithdrawalAccountList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTDEBITACCOUNTLIST = "fINFinancialAccountAcctDebitAccountList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTCREDITACCOUNTLIST = "fINFinancialAccountAcctCreditAccountList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTFINBANKFEEACCTLIST = "fINFinancialAccountAcctFINBankfeeAcctList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTFINBANKREVALUATIONGAINACCTLIST = "fINFinancialAccountAcctFINBankrevaluationgainAcctList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTFINBANKREVALUATIONLOSSACCTLIST = "fINFinancialAccountAcctFINBankrevaluationlossAcctList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTFINOUTINTRANSITACCTLIST = "fINFinancialAccountAcctFINOutIntransitAcctList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTCLEAREDPAYMENTACCOUNTOUTLIST = "fINFinancialAccountAcctClearedPaymentAccountOUTList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTINTRANSITPAYMENTACCOUNTINLIST = "fINFinancialAccountAcctInTransitPaymentAccountINList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTCLEAREDPAYMENTACCOUNTLIST = "fINFinancialAccountAcctClearedPaymentAccountList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTFINASSETACCTLIST = "fINFinancialAccountAcctFINAssetAcctList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTFINTRANSITORYACCTLIST = "fINFinancialAccountAcctFINTransitoryAcctList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWAREHOUSEINVENTORYLIST = "financialMgmtAcctSchemaDefaultWarehouseInventoryList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVENTORYADJUSTMENTLIST = "financialMgmtAcctSchemaDefaultInventoryAdjustmentList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWAREHOUSEDIFFERENCESLIST = "financialMgmtAcctSchemaDefaultWarehouseDifferencesList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVENTORYREVALUATIONLIST = "financialMgmtAcctSchemaDefaultInventoryRevaluationList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTREVENUELIST = "financialMgmtAcctSchemaDefaultProductRevenueList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTEXPENSELIST = "financialMgmtAcctSchemaDefaultProductExpenseList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTFIXEDASSETLIST = "financialMgmtAcctSchemaDefaultFixedAssetList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPURCHASEPRICEVARIANCELIST = "financialMgmtAcctSchemaDefaultPurchasePriceVarianceList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVOICEPRICEVARIANCELIST = "financialMgmtAcctSchemaDefaultInvoicePriceVarianceList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTRADEDISCOUNTRECEIVEDLIST = "financialMgmtAcctSchemaDefaultTradeDiscountReceivedList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTRADEDISCOUNTGRANTEDLIST = "financialMgmtAcctSchemaDefaultTradeDiscountGrantedList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTCOGSLIST = "financialMgmtAcctSchemaDefaultProductCOGSList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCUSTOMERRECEIVABLESNOLIST = "financialMgmtAcctSchemaDefaultCustomerReceivablesNoList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCUSTOMERPREPAYMENTLIST = "financialMgmtAcctSchemaDefaultCustomerPrepaymentList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORLIABILITYLIST = "financialMgmtAcctSchemaDefaultVendorLiabilityList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORSERVICELIABILITYLIST = "financialMgmtAcctSchemaDefaultVendorServiceLiabilityList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORPREPAYMENTLIST = "financialMgmtAcctSchemaDefaultVendorPrepaymentList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTDISCOUNTEXPENSELIST = "financialMgmtAcctSchemaDefaultPaymentDiscountExpenseList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWRITEOFFLIST = "financialMgmtAcctSchemaDefaultWriteoffList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWRITEOFFREVENUELIST = "financialMgmtAcctSchemaDefaultWriteoffRevenueList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTDISCOUNTREVENUELIST = "financialMgmtAcctSchemaDefaultPaymentDiscountRevenueList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNREALIZEDGAINSACCTLIST = "financialMgmtAcctSchemaDefaultUnrealizedGainsAcctList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNREALIZEDLOSSESACCTLIST = "financialMgmtAcctSchemaDefaultUnrealizedLossesAcctList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTREALIZEDGAINACCTLIST = "financialMgmtAcctSchemaDefaultRealizedGainAcctList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTREALIZEDLOSSACCTLIST = "financialMgmtAcctSchemaDefaultRealizedLossAcctList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWITHHOLDINGACCOUNTLIST = "financialMgmtAcctSchemaDefaultWithholdingAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTEMPLOYEEPREPAYMENTSLIST = "financialMgmtAcctSchemaDefaultEmployeePrepaymentsList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTEMPLOYEEEXPENSESLIST = "financialMgmtAcctSchemaDefaultEmployeeExpensesList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPROJECTASSETLIST = "financialMgmtAcctSchemaDefaultProjectAssetList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWORKINPROGRESSLIST = "financialMgmtAcctSchemaDefaultWorkInProgressList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXEXPENSELIST = "financialMgmtAcctSchemaDefaultTaxExpenseList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXLIABILITYLIST = "financialMgmtAcctSchemaDefaultTaxLiabilityList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXRECEIVABLESLIST = "financialMgmtAcctSchemaDefaultTaxReceivablesList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXDUELIST = "financialMgmtAcctSchemaDefaultTaxDueList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXCREDITLIST = "financialMgmtAcctSchemaDefaultTaxCreditList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTRANSITLIST = "financialMgmtAcctSchemaDefaultBankInTransitList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKASSETLIST = "financialMgmtAcctSchemaDefaultBankAssetList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKEXPENSELIST = "financialMgmtAcctSchemaDefaultBankExpenseList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTERESTREVENUELIST = "financialMgmtAcctSchemaDefaultBankInterestRevenueList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTERESTEXPENSELIST = "financialMgmtAcctSchemaDefaultBankInterestExpenseList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKUNIDENTIFIEDRECEIPTSLIST = "financialMgmtAcctSchemaDefaultBankUnidentifiedReceiptsList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNALLOCATEDCASHLIST = "financialMgmtAcctSchemaDefaultUnallocatedCashList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTSELECTIONLIST = "financialMgmtAcctSchemaDefaultPaymentSelectionList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKSETTLEMENTGAINLIST = "financialMgmtAcctSchemaDefaultBankSettlementGainList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKSETTLEMENTLOSSLIST = "financialMgmtAcctSchemaDefaultBankSettlementLossList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKREVALUATIONGAINLIST = "financialMgmtAcctSchemaDefaultBankRevaluationGainList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKREVALUATIONLOSSLIST = "financialMgmtAcctSchemaDefaultBankRevaluationLossList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCHARGEEXPENSELIST = "financialMgmtAcctSchemaDefaultChargeExpenseList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCHARGEREVENUELIST = "financialMgmtAcctSchemaDefaultChargeRevenueList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNEARNEDREVENUELIST = "financialMgmtAcctSchemaDefaultUnearnedRevenueList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDRECEIVABLESLIST = "financialMgmtAcctSchemaDefaultNonInvoicedReceivablesList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDREVENUESLIST = "financialMgmtAcctSchemaDefaultNonInvoicedRevenuesList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDRECEIPTSLIST = "financialMgmtAcctSchemaDefaultNonInvoicedReceiptsList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKASSETLIST = "financialMgmtAcctSchemaDefaultCashBookAssetList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHTRANSFERLIST = "financialMgmtAcctSchemaDefaultCashTransferList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKDIFFERENCESLIST = "financialMgmtAcctSchemaDefaultCashBookDifferencesList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKEXPENSELIST = "financialMgmtAcctSchemaDefaultCashBookExpenseList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKRECEIPTLIST = "financialMgmtAcctSchemaDefaultCashBookReceiptList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTACCUMULATEDDEPRECIATIONLIST = "financialMgmtAcctSchemaDefaultAccumulatedDepreciationList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDEPRECIATIONLIST = "financialMgmtAcctSchemaDefaultDepreciationList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDISPOSALGAINLIST = "financialMgmtAcctSchemaDefaultDisposalGainList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDISPOSALLOSSLIST = "financialMgmtAcctSchemaDefaultDisposalLossList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTREVENUERETURNLIST = "financialMgmtAcctSchemaDefaultProductRevenueReturnList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTCOGSRETURNLIST = "financialMgmtAcctSchemaDefaultProductCOGSReturnList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTDEFERREDREVENUELIST = "financialMgmtAcctSchemaDefaultProductDeferredRevenueList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTDEFERREDEXPENSELIST = "financialMgmtAcctSchemaDefaultProductDeferredExpenseList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDOUBTFULDEBTACCOUNTLIST = "financialMgmtAcctSchemaDefaultDoubtfulDebtAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBADDEBTEXPENSEACCOUNTLIST = "financialMgmtAcctSchemaDefaultBadDebtExpenseAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBADDEBTREVENUEACCOUNTLIST = "financialMgmtAcctSchemaDefaultBadDebtRevenueAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTALLOWANCEFORDOUBTFULDEBTACCOUNTLIST = "financialMgmtAcctSchemaDefaultAllowanceForDoubtfulDebtAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLSUSPENSEBALANCINGLIST = "financialMgmtAcctSchemaGLSuspenseBalancingList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLSUSPENSEERRORLIST = "financialMgmtAcctSchemaGLSuspenseErrorList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLCURRENCYBALANCINGACCTLIST = "financialMgmtAcctSchemaGLCurrencyBalancingAcctList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLRETAINEDEARNINGLIST = "financialMgmtAcctSchemaGLRetainedEarningList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLINCOMESUMMARYLIST = "financialMgmtAcctSchemaGLIncomeSummaryList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLDUETOINTERCOMPANYLIST = "financialMgmtAcctSchemaGLDueToIntercompanyList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLDUEFROMINTERCOMPANYLIST = "financialMgmtAcctSchemaGLDueFromIntercompanyList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLPPVOFFSETLIST = "financialMgmtAcctSchemaGLPPVOffsetList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAGLCFSORDERACCOUNTLIST = "financialMgmtAcctSchemaGLCFSOrderAccountList";
    public static final String PROPERTY_FINANCIALMGMTASSETACCOUNTSDEPRECIATIONLIST = "financialMgmtAssetAccountsDepreciationList";
    public static final String PROPERTY_FINANCIALMGMTASSETACCOUNTSACCUMULATEDDEPRECIATIONLIST = "financialMgmtAssetAccountsAccumulatedDepreciationList";
    public static final String PROPERTY_FINANCIALMGMTASSETACCOUNTSDISPOSALLOSSLIST = "financialMgmtAssetAccountsDisposalLossList";
    public static final String PROPERTY_FINANCIALMGMTASSETACCOUNTSDISPOSALGAINLIST = "financialMgmtAssetAccountsDisposalGainList";
    public static final String PROPERTY_FINANCIALMGMTASSETGROUPACCTDEPRECIATIONLIST = "financialMgmtAssetGroupAcctDepreciationList";
    public static final String PROPERTY_FINANCIALMGMTASSETGROUPACCTACCUMULATEDDEPRECIATIONLIST = "financialMgmtAssetGroupAcctAccumulatedDepreciationList";
    public static final String PROPERTY_FINANCIALMGMTASSETGROUPACCTDISPOSALLOSSLIST = "financialMgmtAssetGroupAcctDisposalLossList";
    public static final String PROPERTY_FINANCIALMGMTASSETGROUPACCTDISPOSALGAINLIST = "financialMgmtAssetGroupAcctDisposalGainList";
    public static final String PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKASSETLIST = "financialMgmtCashBookAccountsCashBookAssetList";
    public static final String PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHTRANSFERLIST = "financialMgmtCashBookAccountsCashTransferList";
    public static final String PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKDIFFERENCESLIST = "financialMgmtCashBookAccountsCashBookDifferencesList";
    public static final String PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKEXPENSELIST = "financialMgmtCashBookAccountsCashBookExpenseList";
    public static final String PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKRECEIPTLIST = "financialMgmtCashBookAccountsCashBookReceiptList";
    public static final String PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSCHARGEEXPENSELIST = "financialMgmtGLChargeAccountsChargeExpenseList";
    public static final String PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSCHARGEREVENUELIST = "financialMgmtGLChargeAccountsChargeRevenueList";
    public static final String PROPERTY_FINANCIALMGMTGLITEMACCOUNTSGLITEMDEBITACCTLIST = "financialMgmtGLItemAccountsGlitemDebitAcctList";
    public static final String PROPERTY_FINANCIALMGMTGLITEMACCOUNTSGLITEMCREDITACCTLIST = "financialMgmtGLItemAccountsGlitemCreditAcctList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXDUELIST = "financialMgmtTaxRateAccountsTaxDueList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXLIABILITYLIST = "financialMgmtTaxRateAccountsTaxLiabilityList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXCREDITLIST = "financialMgmtTaxRateAccountsTaxCreditList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXRECEIVABLESLIST = "financialMgmtTaxRateAccountsTaxReceivablesList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXEXPENSELIST = "financialMgmtTaxRateAccountsTaxExpenseList";
    public static final String PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSWITHHOLDINGACCOUNTLIST = "financialMgmtWithholdingAccountsWithholdingAccountList";
    public static final String PROPERTY_PRODUCTACCOUNTSPRODUCTREVENUELIST = "productAccountsProductRevenueList";
    public static final String PROPERTY_PRODUCTACCOUNTSPRODUCTEXPENSELIST = "productAccountsProductExpenseList";
    public static final String PROPERTY_PRODUCTACCOUNTSFIXEDASSETLIST = "productAccountsFixedAssetList";
    public static final String PROPERTY_PRODUCTACCOUNTSPURCHASEPRICEVARIANCELIST = "productAccountsPurchasePriceVarianceList";
    public static final String PROPERTY_PRODUCTACCOUNTSINVOICEPRICEVARIANCELIST = "productAccountsInvoicePriceVarianceList";
    public static final String PROPERTY_PRODUCTACCOUNTSPRODUCTCOGSLIST = "productAccountsProductCOGSList";
    public static final String PROPERTY_PRODUCTACCOUNTSTRADEDISCOUNTRECEIVEDLIST = "productAccountsTradeDiscountReceivedList";
    public static final String PROPERTY_PRODUCTACCOUNTSTRADEDISCOUNTGRANTEDLIST = "productAccountsTradeDiscountGrantedList";
    public static final String PROPERTY_PRODUCTACCOUNTSPRODUCTREVENUERETURNLIST = "productAccountsProductRevenueReturnList";
    public static final String PROPERTY_PRODUCTACCOUNTSPRODUCTCOGSRETURNLIST = "productAccountsProductCOGSReturnList";
    public static final String PROPERTY_PRODUCTACCOUNTSPRODUCTDEFERREDREVENUELIST = "productAccountsProductDeferredRevenueList";
    public static final String PROPERTY_PRODUCTACCOUNTSPDEFEXPENSEACCTLIST = "productAccountsPDefExpenseAcctList";
    public static final String PROPERTY_PRODUCTACCOUNTSPRASSETLIST = "productAccountsPrassetList";
    public static final String PROPERTY_PRODUCTACCOUNTSPREXPENSELIST = "productAccountsPrexpenseList";
    public static final String PROPERTY_PRODUCTACCOUNTSPRWIPLIST = "productAccountsPrwipList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTREVENUELIST = "productCategoryAccountsProductRevenueList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTEXPENSELIST = "productCategoryAccountsProductExpenseList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSFIXEDASSETLIST = "productCategoryAccountsFixedAssetList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTCOGSLIST = "productCategoryAccountsProductCOGSList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSPURCHASEPRICEVARIANCELIST = "productCategoryAccountsPurchasePriceVarianceList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSINVOICEPRICEVARIANCELIST = "productCategoryAccountsInvoicePriceVarianceList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSTRADEDISCOUNTRECEIVEDLIST = "productCategoryAccountsTradeDiscountReceivedList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSTRADEDISCOUNTGRANTEDLIST = "productCategoryAccountsTradeDiscountGrantedList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTCOGSRETURNLIST = "productCategoryAccountsProductCOGSReturnList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTREVENUERETURNLIST = "productCategoryAccountsProductRevenueReturnList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTDEFERREDREVENUELIST = "productCategoryAccountsProductDeferredRevenueList";
    public static final String PROPERTY_PRODUCTCATEGORYACCOUNTSPDEFEXPENSEACCTLIST = "productCategoryAccountsPDefExpenseAcctList";
    public static final String PROPERTY_PROJECTACCOUNTSPROJECTASSETLIST = "projectAccountsProjectAssetList";
    public static final String PROPERTY_PROJECTACCOUNTSWORKINPROGRESSLIST = "projectAccountsWorkInProgressList";
    public static final String PROPERTY_RCPRPREXPENSETYPEPRODUCTASSETLIST = "rCPRPrExpenseTypeProductAssetList";
    public static final String PROPERTY_RCPRPRPROCESSLEVELPRODUCTASSETLIST = "rCPRPrProcessLevelProductAssetList";
    public static final String PROPERTY_VENDORACCOUNTSVENDORLIABILITYLIST = "vendorAccountsVendorLiabilityList";
    public static final String PROPERTY_VENDORACCOUNTSVENDORSERVICELIABILITYLIST = "vendorAccountsVendorServiceLiabilityList";
    public static final String PROPERTY_VENDORACCOUNTSVENDORPREPAYMENTLIST = "vendorAccountsVendorPrepaymentList";
    public static final String PROPERTY_WAREHOUSEACCOUNTSWAREHOUSEINVENTORYLIST = "warehouseAccountsWarehouseInventoryList";
    public static final String PROPERTY_WAREHOUSEACCOUNTSINVENTORYADJUSTMENTLIST = "warehouseAccountsInventoryAdjustmentList";
    public static final String PROPERTY_WAREHOUSEACCOUNTSWAREHOUSEDIFFERENCESLIST = "warehouseAccountsWarehouseDifferencesList";
    public static final String PROPERTY_WAREHOUSEACCOUNTSINVENTORYREVALUATIONLIST = "warehouseAccountsInventoryRevaluationList";

    public AccountingCombination() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_FULLYQUALIFIED, false);
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKINTRANSITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKINTERESTREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKINTERESTEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKUNIDENTIFIEDRECEIPTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSUNALLOCATEDCASHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSPAYMENTSELECTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKSETTLEMENTGAINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKSETTLEMENTLOSSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKREVALUATIONGAINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKACCOUNTACCOUNTSBANKREVALUATIONLOSSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTCUSTOMERRECEIVABLESNOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTCUSTOMERPREPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORLIABILITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORSERVICELIABILITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORPREPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTPAYMENTDISCOUNTEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTPAYMENTDISCOUNTREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTWRITEOFFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNREALIZEDGAINSACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNREALIZEDLOSSESACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTREALIZEDGAINACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTREALIZEDLOSSACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDRECEIPTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNEARNEDREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDREVENUESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDRECEIVABLESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTWRITEOFFREVACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTDOUBTFULDEBTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTBADDEBTEXPENSEACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTBADDEBTREVENUEACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTALLOWANCEFORDOUBTFULDEBTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CUSTOMERACCOUNTSCUSTOMERRECEIVABLESNOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CUSTOMERACCOUNTSCUSTOMERPREPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLOYEEACCOUNTSEMPLOYEEEXPENSESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLOYEEACCOUNTSEMPLOYEEPREPAYMENTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTRECEIVEPAYMENTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTMAKEPAYMENTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTDEPOSITACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTWITHDRAWALACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTDEBITACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTCREDITACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKFEEACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKREVALUATIONGAINACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKREVALUATIONLOSSACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTFINOUTINTRANSITACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTCLEAREDPAYMENTACCOUNTOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTINTRANSITPAYMENTACCOUNTINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTCLEAREDPAYMENTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTFINASSETACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTFINTRANSITORYACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWAREHOUSEINVENTORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVENTORYADJUSTMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWAREHOUSEDIFFERENCESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVENTORYREVALUATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTFIXEDASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPURCHASEPRICEVARIANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVOICEPRICEVARIANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTRADEDISCOUNTRECEIVEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTRADEDISCOUNTGRANTEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTCOGSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCUSTOMERRECEIVABLESNOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCUSTOMERPREPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORLIABILITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORSERVICELIABILITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORPREPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTDISCOUNTEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWRITEOFFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWRITEOFFREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTDISCOUNTREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNREALIZEDGAINSACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNREALIZEDLOSSESACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTREALIZEDGAINACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTREALIZEDLOSSACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWITHHOLDINGACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTEMPLOYEEPREPAYMENTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTEMPLOYEEEXPENSESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPROJECTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWORKINPROGRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXLIABILITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXRECEIVABLESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXDUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXCREDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTRANSITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTERESTREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTERESTEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKUNIDENTIFIEDRECEIPTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNALLOCATEDCASHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTSELECTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKSETTLEMENTGAINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKSETTLEMENTLOSSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKREVALUATIONGAINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKREVALUATIONLOSSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCHARGEEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCHARGEREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNEARNEDREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDRECEIVABLESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDREVENUESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDRECEIPTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHTRANSFERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKDIFFERENCESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTACCUMULATEDDEPRECIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDEPRECIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDISPOSALGAINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDISPOSALLOSSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTREVENUERETURNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTCOGSRETURNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTDEFERREDREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTDEFERREDEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDOUBTFULDEBTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBADDEBTEXPENSEACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBADDEBTREVENUEACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTALLOWANCEFORDOUBTFULDEBTACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLSUSPENSEBALANCINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLSUSPENSEERRORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLCURRENCYBALANCINGACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLRETAINEDEARNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLINCOMESUMMARYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLDUETOINTERCOMPANYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLDUEFROMINTERCOMPANYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLPPVOFFSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAGLCFSORDERACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETACCOUNTSDEPRECIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETACCOUNTSACCUMULATEDDEPRECIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETACCOUNTSDISPOSALLOSSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETACCOUNTSDISPOSALGAINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETGROUPACCTDEPRECIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETGROUPACCTACCUMULATEDDEPRECIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETGROUPACCTDISPOSALLOSSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETGROUPACCTDISPOSALGAINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHTRANSFERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKDIFFERENCESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSCHARGEEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSCHARGEREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSGLITEMDEBITACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSGLITEMCREDITACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXDUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXLIABILITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXCREDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXRECEIVABLESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSWITHHOLDINGACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPRODUCTREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPRODUCTEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSFIXEDASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPURCHASEPRICEVARIANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSINVOICEPRICEVARIANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPRODUCTCOGSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSTRADEDISCOUNTRECEIVEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSTRADEDISCOUNTGRANTEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPRODUCTREVENUERETURNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPRODUCTCOGSRETURNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPRODUCTDEFERREDREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPDEFEXPENSEACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPRASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPREXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSPRWIPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTEXPENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSFIXEDASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTCOGSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSPURCHASEPRICEVARIANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSINVOICEPRICEVARIANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSTRADEDISCOUNTRECEIVEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSTRADEDISCOUNTGRANTEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTCOGSRETURNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTREVENUERETURNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTDEFERREDREVENUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYACCOUNTSPDEFEXPENSEACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTACCOUNTSPROJECTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTACCOUNTSWORKINPROGRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPREXPENSETYPEPRODUCTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRPROCESSLEVELPRODUCTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VENDORACCOUNTSVENDORLIABILITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VENDORACCOUNTSVENDORSERVICELIABILITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VENDORACCOUNTSVENDORPREPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSEACCOUNTSWAREHOUSEINVENTORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSEACCOUNTSINVENTORYADJUSTMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSEACCOUNTSWAREHOUSEDIFFERENCESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSEACCOUNTSINVENTORYREVALUATIONLIST, new ArrayList<Object>());
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

    public String getAlias() {
        return (String) get(PROPERTY_ALIAS);
    }

    public void setAlias(String alias) {
        set(PROPERTY_ALIAS, alias);
    }

    public String getCombination() {
        return (String) get(PROPERTY_COMBINATION);
    }

    public void setCombination(String combination) {
        set(PROPERTY_COMBINATION, combination);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isFullyQualified() {
        return (Boolean) get(PROPERTY_FULLYQUALIFIED);
    }

    public void setFullyQualified(Boolean fullyQualified) {
        set(PROPERTY_FULLYQUALIFIED, fullyQualified);
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

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankInTransitList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKINTRANSITLIST);
    }

    public void setBankAccountAccountsBankInTransitList(List<BankAccountAccounts> bankAccountAccountsBankInTransitList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKINTRANSITLIST, bankAccountAccountsBankInTransitList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankAssetList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKASSETLIST);
    }

    public void setBankAccountAccountsBankAssetList(List<BankAccountAccounts> bankAccountAccountsBankAssetList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKASSETLIST, bankAccountAccountsBankAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankExpenseList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKEXPENSELIST);
    }

    public void setBankAccountAccountsBankExpenseList(List<BankAccountAccounts> bankAccountAccountsBankExpenseList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKEXPENSELIST, bankAccountAccountsBankExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankInterestRevenueList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKINTERESTREVENUELIST);
    }

    public void setBankAccountAccountsBankInterestRevenueList(List<BankAccountAccounts> bankAccountAccountsBankInterestRevenueList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKINTERESTREVENUELIST, bankAccountAccountsBankInterestRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankInterestExpenseList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKINTERESTEXPENSELIST);
    }

    public void setBankAccountAccountsBankInterestExpenseList(List<BankAccountAccounts> bankAccountAccountsBankInterestExpenseList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKINTERESTEXPENSELIST, bankAccountAccountsBankInterestExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankUnidentifiedReceiptsList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKUNIDENTIFIEDRECEIPTSLIST);
    }

    public void setBankAccountAccountsBankUnidentifiedReceiptsList(List<BankAccountAccounts> bankAccountAccountsBankUnidentifiedReceiptsList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKUNIDENTIFIEDRECEIPTSLIST, bankAccountAccountsBankUnidentifiedReceiptsList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsUnallocatedCashList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSUNALLOCATEDCASHLIST);
    }

    public void setBankAccountAccountsUnallocatedCashList(List<BankAccountAccounts> bankAccountAccountsUnallocatedCashList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSUNALLOCATEDCASHLIST, bankAccountAccountsUnallocatedCashList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsPaymentSelectionList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSPAYMENTSELECTIONLIST);
    }

    public void setBankAccountAccountsPaymentSelectionList(List<BankAccountAccounts> bankAccountAccountsPaymentSelectionList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSPAYMENTSELECTIONLIST, bankAccountAccountsPaymentSelectionList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankSettlementGainList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKSETTLEMENTGAINLIST);
    }

    public void setBankAccountAccountsBankSettlementGainList(List<BankAccountAccounts> bankAccountAccountsBankSettlementGainList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKSETTLEMENTGAINLIST, bankAccountAccountsBankSettlementGainList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankSettlementLossList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKSETTLEMENTLOSSLIST);
    }

    public void setBankAccountAccountsBankSettlementLossList(List<BankAccountAccounts> bankAccountAccountsBankSettlementLossList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKSETTLEMENTLOSSLIST, bankAccountAccountsBankSettlementLossList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankRevaluationGainList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKREVALUATIONGAINLIST);
    }

    public void setBankAccountAccountsBankRevaluationGainList(List<BankAccountAccounts> bankAccountAccountsBankRevaluationGainList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKREVALUATIONGAINLIST, bankAccountAccountsBankRevaluationGainList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountAccounts> getBankAccountAccountsBankRevaluationLossList() {
        return (List<BankAccountAccounts>) get(PROPERTY_BANKACCOUNTACCOUNTSBANKREVALUATIONLOSSLIST);
    }

    public void setBankAccountAccountsBankRevaluationLossList(List<BankAccountAccounts> bankAccountAccountsBankRevaluationLossList) {
        set(PROPERTY_BANKACCOUNTACCOUNTSBANKREVALUATIONLOSSLIST, bankAccountAccountsBankRevaluationLossList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountCustomerReceivablesNoList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTCUSTOMERRECEIVABLESNOLIST);
    }

    public void setBusinessPartnerCategoryAccountCustomerReceivablesNoList(List<CategoryAccounts> businessPartnerCategoryAccountCustomerReceivablesNoList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTCUSTOMERRECEIVABLESNOLIST, businessPartnerCategoryAccountCustomerReceivablesNoList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountCustomerPrepaymentList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTCUSTOMERPREPAYMENTLIST);
    }

    public void setBusinessPartnerCategoryAccountCustomerPrepaymentList(List<CategoryAccounts> businessPartnerCategoryAccountCustomerPrepaymentList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTCUSTOMERPREPAYMENTLIST, businessPartnerCategoryAccountCustomerPrepaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountVendorLiabilityList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORLIABILITYLIST);
    }

    public void setBusinessPartnerCategoryAccountVendorLiabilityList(List<CategoryAccounts> businessPartnerCategoryAccountVendorLiabilityList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORLIABILITYLIST, businessPartnerCategoryAccountVendorLiabilityList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountVendorServiceLiabilityList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORSERVICELIABILITYLIST);
    }

    public void setBusinessPartnerCategoryAccountVendorServiceLiabilityList(List<CategoryAccounts> businessPartnerCategoryAccountVendorServiceLiabilityList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORSERVICELIABILITYLIST, businessPartnerCategoryAccountVendorServiceLiabilityList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountVendorPrepaymentList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORPREPAYMENTLIST);
    }

    public void setBusinessPartnerCategoryAccountVendorPrepaymentList(List<CategoryAccounts> businessPartnerCategoryAccountVendorPrepaymentList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTVENDORPREPAYMENTLIST, businessPartnerCategoryAccountVendorPrepaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountPaymentDiscountExpenseList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTPAYMENTDISCOUNTEXPENSELIST);
    }

    public void setBusinessPartnerCategoryAccountPaymentDiscountExpenseList(List<CategoryAccounts> businessPartnerCategoryAccountPaymentDiscountExpenseList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTPAYMENTDISCOUNTEXPENSELIST, businessPartnerCategoryAccountPaymentDiscountExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountPaymentDiscountRevenueList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTPAYMENTDISCOUNTREVENUELIST);
    }

    public void setBusinessPartnerCategoryAccountPaymentDiscountRevenueList(List<CategoryAccounts> businessPartnerCategoryAccountPaymentDiscountRevenueList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTPAYMENTDISCOUNTREVENUELIST, businessPartnerCategoryAccountPaymentDiscountRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountWriteoffList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTWRITEOFFLIST);
    }

    public void setBusinessPartnerCategoryAccountWriteoffList(List<CategoryAccounts> businessPartnerCategoryAccountWriteoffList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTWRITEOFFLIST, businessPartnerCategoryAccountWriteoffList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountUnrealizedGainsAcctList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNREALIZEDGAINSACCTLIST);
    }

    public void setBusinessPartnerCategoryAccountUnrealizedGainsAcctList(List<CategoryAccounts> businessPartnerCategoryAccountUnrealizedGainsAcctList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNREALIZEDGAINSACCTLIST, businessPartnerCategoryAccountUnrealizedGainsAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountUnrealizedLossesAcctList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNREALIZEDLOSSESACCTLIST);
    }

    public void setBusinessPartnerCategoryAccountUnrealizedLossesAcctList(List<CategoryAccounts> businessPartnerCategoryAccountUnrealizedLossesAcctList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNREALIZEDLOSSESACCTLIST, businessPartnerCategoryAccountUnrealizedLossesAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountRealizedGainAcctList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTREALIZEDGAINACCTLIST);
    }

    public void setBusinessPartnerCategoryAccountRealizedGainAcctList(List<CategoryAccounts> businessPartnerCategoryAccountRealizedGainAcctList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTREALIZEDGAINACCTLIST, businessPartnerCategoryAccountRealizedGainAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountRealizedLossAcctList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTREALIZEDLOSSACCTLIST);
    }

    public void setBusinessPartnerCategoryAccountRealizedLossAcctList(List<CategoryAccounts> businessPartnerCategoryAccountRealizedLossAcctList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTREALIZEDLOSSACCTLIST, businessPartnerCategoryAccountRealizedLossAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountNonInvoicedReceiptsList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDRECEIPTSLIST);
    }

    public void setBusinessPartnerCategoryAccountNonInvoicedReceiptsList(List<CategoryAccounts> businessPartnerCategoryAccountNonInvoicedReceiptsList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDRECEIPTSLIST, businessPartnerCategoryAccountNonInvoicedReceiptsList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountUnearnedRevenueList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNEARNEDREVENUELIST);
    }

    public void setBusinessPartnerCategoryAccountUnearnedRevenueList(List<CategoryAccounts> businessPartnerCategoryAccountUnearnedRevenueList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTUNEARNEDREVENUELIST, businessPartnerCategoryAccountUnearnedRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountNonInvoicedRevenuesList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDREVENUESLIST);
    }

    public void setBusinessPartnerCategoryAccountNonInvoicedRevenuesList(List<CategoryAccounts> businessPartnerCategoryAccountNonInvoicedRevenuesList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDREVENUESLIST, businessPartnerCategoryAccountNonInvoicedRevenuesList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountNonInvoicedReceivablesList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDRECEIVABLESLIST);
    }

    public void setBusinessPartnerCategoryAccountNonInvoicedReceivablesList(List<CategoryAccounts> businessPartnerCategoryAccountNonInvoicedReceivablesList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTNONINVOICEDRECEIVABLESLIST, businessPartnerCategoryAccountNonInvoicedReceivablesList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountWriteoffRevAcctList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTWRITEOFFREVACCTLIST);
    }

    public void setBusinessPartnerCategoryAccountWriteoffRevAcctList(List<CategoryAccounts> businessPartnerCategoryAccountWriteoffRevAcctList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTWRITEOFFREVACCTLIST, businessPartnerCategoryAccountWriteoffRevAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountDoubtfulDebtAccountList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTDOUBTFULDEBTACCOUNTLIST);
    }

    public void setBusinessPartnerCategoryAccountDoubtfulDebtAccountList(List<CategoryAccounts> businessPartnerCategoryAccountDoubtfulDebtAccountList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTDOUBTFULDEBTACCOUNTLIST, businessPartnerCategoryAccountDoubtfulDebtAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountBadDebtExpenseAccountList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTBADDEBTEXPENSEACCOUNTLIST);
    }

    public void setBusinessPartnerCategoryAccountBadDebtExpenseAccountList(List<CategoryAccounts> businessPartnerCategoryAccountBadDebtExpenseAccountList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTBADDEBTEXPENSEACCOUNTLIST, businessPartnerCategoryAccountBadDebtExpenseAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountBadDebtRevenueAccountList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTBADDEBTREVENUEACCOUNTLIST);
    }

    public void setBusinessPartnerCategoryAccountBadDebtRevenueAccountList(List<CategoryAccounts> businessPartnerCategoryAccountBadDebtRevenueAccountList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTBADDEBTREVENUEACCOUNTLIST, businessPartnerCategoryAccountBadDebtRevenueAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryAccounts> getBusinessPartnerCategoryAccountAllowanceForDoubtfulDebtAccountList() {
        return (List<CategoryAccounts>) get(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTALLOWANCEFORDOUBTFULDEBTACCOUNTLIST);
    }

    public void setBusinessPartnerCategoryAccountAllowanceForDoubtfulDebtAccountList(List<CategoryAccounts> businessPartnerCategoryAccountAllowanceForDoubtfulDebtAccountList) {
        set(PROPERTY_BUSINESSPARTNERCATEGORYACCOUNTALLOWANCEFORDOUBTFULDEBTACCOUNTLIST, businessPartnerCategoryAccountAllowanceForDoubtfulDebtAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<CustomerAccounts> getCustomerAccountsCustomerReceivablesNoList() {
        return (List<CustomerAccounts>) get(PROPERTY_CUSTOMERACCOUNTSCUSTOMERRECEIVABLESNOLIST);
    }

    public void setCustomerAccountsCustomerReceivablesNoList(List<CustomerAccounts> customerAccountsCustomerReceivablesNoList) {
        set(PROPERTY_CUSTOMERACCOUNTSCUSTOMERRECEIVABLESNOLIST, customerAccountsCustomerReceivablesNoList);
    }

    @SuppressWarnings("unchecked")
    public List<CustomerAccounts> getCustomerAccountsCustomerPrepaymentList() {
        return (List<CustomerAccounts>) get(PROPERTY_CUSTOMERACCOUNTSCUSTOMERPREPAYMENTLIST);
    }

    public void setCustomerAccountsCustomerPrepaymentList(List<CustomerAccounts> customerAccountsCustomerPrepaymentList) {
        set(PROPERTY_CUSTOMERACCOUNTSCUSTOMERPREPAYMENTLIST, customerAccountsCustomerPrepaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeAccounts> getEmployeeAccountsEmployeeExpensesList() {
        return (List<EmployeeAccounts>) get(PROPERTY_EMPLOYEEACCOUNTSEMPLOYEEEXPENSESLIST);
    }

    public void setEmployeeAccountsEmployeeExpensesList(List<EmployeeAccounts> employeeAccountsEmployeeExpensesList) {
        set(PROPERTY_EMPLOYEEACCOUNTSEMPLOYEEEXPENSESLIST, employeeAccountsEmployeeExpensesList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeAccounts> getEmployeeAccountsEmployeePrepaymentsList() {
        return (List<EmployeeAccounts>) get(PROPERTY_EMPLOYEEACCOUNTSEMPLOYEEPREPAYMENTSLIST);
    }

    public void setEmployeeAccountsEmployeePrepaymentsList(List<EmployeeAccounts> employeeAccountsEmployeePrepaymentsList) {
        set(PROPERTY_EMPLOYEEACCOUNTSEMPLOYEEPREPAYMENTSLIST, employeeAccountsEmployeePrepaymentsList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctReceivePaymentAccountList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTRECEIVEPAYMENTACCOUNTLIST);
    }

    public void setFINFinancialAccountAcctReceivePaymentAccountList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctReceivePaymentAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTRECEIVEPAYMENTACCOUNTLIST, fINFinancialAccountAcctReceivePaymentAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctMakePaymentAccountList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTMAKEPAYMENTACCOUNTLIST);
    }

    public void setFINFinancialAccountAcctMakePaymentAccountList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctMakePaymentAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTMAKEPAYMENTACCOUNTLIST, fINFinancialAccountAcctMakePaymentAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctDepositAccountList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTDEPOSITACCOUNTLIST);
    }

    public void setFINFinancialAccountAcctDepositAccountList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctDepositAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTDEPOSITACCOUNTLIST, fINFinancialAccountAcctDepositAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctWithdrawalAccountList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTWITHDRAWALACCOUNTLIST);
    }

    public void setFINFinancialAccountAcctWithdrawalAccountList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctWithdrawalAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTWITHDRAWALACCOUNTLIST, fINFinancialAccountAcctWithdrawalAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctDebitAccountList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTDEBITACCOUNTLIST);
    }

    public void setFINFinancialAccountAcctDebitAccountList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctDebitAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTDEBITACCOUNTLIST, fINFinancialAccountAcctDebitAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctCreditAccountList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTCREDITACCOUNTLIST);
    }

    public void setFINFinancialAccountAcctCreditAccountList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctCreditAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTCREDITACCOUNTLIST, fINFinancialAccountAcctCreditAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctFINBankfeeAcctList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKFEEACCTLIST);
    }

    public void setFINFinancialAccountAcctFINBankfeeAcctList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctFINBankfeeAcctList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKFEEACCTLIST, fINFinancialAccountAcctFINBankfeeAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctFINBankrevaluationgainAcctList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKREVALUATIONGAINACCTLIST);
    }

    public void setFINFinancialAccountAcctFINBankrevaluationgainAcctList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctFINBankrevaluationgainAcctList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKREVALUATIONGAINACCTLIST, fINFinancialAccountAcctFINBankrevaluationgainAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctFINBankrevaluationlossAcctList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKREVALUATIONLOSSACCTLIST);
    }

    public void setFINFinancialAccountAcctFINBankrevaluationlossAcctList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctFINBankrevaluationlossAcctList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTFINBANKREVALUATIONLOSSACCTLIST, fINFinancialAccountAcctFINBankrevaluationlossAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctFINOutIntransitAcctList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTFINOUTINTRANSITACCTLIST);
    }

    public void setFINFinancialAccountAcctFINOutIntransitAcctList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctFINOutIntransitAcctList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTFINOUTINTRANSITACCTLIST, fINFinancialAccountAcctFINOutIntransitAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctClearedPaymentAccountOUTList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTCLEAREDPAYMENTACCOUNTOUTLIST);
    }

    public void setFINFinancialAccountAcctClearedPaymentAccountOUTList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctClearedPaymentAccountOUTList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTCLEAREDPAYMENTACCOUNTOUTLIST, fINFinancialAccountAcctClearedPaymentAccountOUTList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctInTransitPaymentAccountINList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTINTRANSITPAYMENTACCOUNTINLIST);
    }

    public void setFINFinancialAccountAcctInTransitPaymentAccountINList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctInTransitPaymentAccountINList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTINTRANSITPAYMENTACCOUNTINLIST, fINFinancialAccountAcctInTransitPaymentAccountINList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctClearedPaymentAccountList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTCLEAREDPAYMENTACCOUNTLIST);
    }

    public void setFINFinancialAccountAcctClearedPaymentAccountList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctClearedPaymentAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTCLEAREDPAYMENTACCOUNTLIST, fINFinancialAccountAcctClearedPaymentAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctFINAssetAcctList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTFINASSETACCTLIST);
    }

    public void setFINFinancialAccountAcctFINAssetAcctList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctFINAssetAcctList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTFINASSETACCTLIST, fINFinancialAccountAcctFINAssetAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctFINTransitoryAcctList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTFINTRANSITORYACCTLIST);
    }

    public void setFINFinancialAccountAcctFINTransitoryAcctList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctFINTransitoryAcctList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTFINTRANSITORYACCTLIST, fINFinancialAccountAcctFINTransitoryAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultWarehouseInventoryList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWAREHOUSEINVENTORYLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultWarehouseInventoryList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultWarehouseInventoryList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWAREHOUSEINVENTORYLIST, financialMgmtAcctSchemaDefaultWarehouseInventoryList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultInventoryAdjustmentList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVENTORYADJUSTMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultInventoryAdjustmentList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultInventoryAdjustmentList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVENTORYADJUSTMENTLIST, financialMgmtAcctSchemaDefaultInventoryAdjustmentList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultWarehouseDifferencesList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWAREHOUSEDIFFERENCESLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultWarehouseDifferencesList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultWarehouseDifferencesList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWAREHOUSEDIFFERENCESLIST, financialMgmtAcctSchemaDefaultWarehouseDifferencesList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultInventoryRevaluationList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVENTORYREVALUATIONLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultInventoryRevaluationList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultInventoryRevaluationList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVENTORYREVALUATIONLIST, financialMgmtAcctSchemaDefaultInventoryRevaluationList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultProductRevenueList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTREVENUELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultProductRevenueList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultProductRevenueList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTREVENUELIST, financialMgmtAcctSchemaDefaultProductRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultProductExpenseList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTEXPENSELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultProductExpenseList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultProductExpenseList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTEXPENSELIST, financialMgmtAcctSchemaDefaultProductExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultFixedAssetList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTFIXEDASSETLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultFixedAssetList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultFixedAssetList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTFIXEDASSETLIST, financialMgmtAcctSchemaDefaultFixedAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultPurchasePriceVarianceList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPURCHASEPRICEVARIANCELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultPurchasePriceVarianceList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultPurchasePriceVarianceList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPURCHASEPRICEVARIANCELIST, financialMgmtAcctSchemaDefaultPurchasePriceVarianceList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultInvoicePriceVarianceList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVOICEPRICEVARIANCELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultInvoicePriceVarianceList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultInvoicePriceVarianceList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTINVOICEPRICEVARIANCELIST, financialMgmtAcctSchemaDefaultInvoicePriceVarianceList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultTradeDiscountReceivedList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTRADEDISCOUNTRECEIVEDLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultTradeDiscountReceivedList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultTradeDiscountReceivedList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTRADEDISCOUNTRECEIVEDLIST, financialMgmtAcctSchemaDefaultTradeDiscountReceivedList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultTradeDiscountGrantedList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTRADEDISCOUNTGRANTEDLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultTradeDiscountGrantedList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultTradeDiscountGrantedList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTRADEDISCOUNTGRANTEDLIST, financialMgmtAcctSchemaDefaultTradeDiscountGrantedList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultProductCOGSList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTCOGSLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultProductCOGSList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultProductCOGSList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTCOGSLIST, financialMgmtAcctSchemaDefaultProductCOGSList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultCustomerReceivablesNoList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCUSTOMERRECEIVABLESNOLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultCustomerReceivablesNoList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultCustomerReceivablesNoList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCUSTOMERRECEIVABLESNOLIST, financialMgmtAcctSchemaDefaultCustomerReceivablesNoList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultCustomerPrepaymentList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCUSTOMERPREPAYMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultCustomerPrepaymentList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultCustomerPrepaymentList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCUSTOMERPREPAYMENTLIST, financialMgmtAcctSchemaDefaultCustomerPrepaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultVendorLiabilityList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORLIABILITYLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultVendorLiabilityList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultVendorLiabilityList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORLIABILITYLIST, financialMgmtAcctSchemaDefaultVendorLiabilityList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultVendorServiceLiabilityList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORSERVICELIABILITYLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultVendorServiceLiabilityList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultVendorServiceLiabilityList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORSERVICELIABILITYLIST, financialMgmtAcctSchemaDefaultVendorServiceLiabilityList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultVendorPrepaymentList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORPREPAYMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultVendorPrepaymentList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultVendorPrepaymentList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTVENDORPREPAYMENTLIST, financialMgmtAcctSchemaDefaultVendorPrepaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultPaymentDiscountExpenseList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTDISCOUNTEXPENSELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultPaymentDiscountExpenseList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultPaymentDiscountExpenseList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTDISCOUNTEXPENSELIST, financialMgmtAcctSchemaDefaultPaymentDiscountExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultWriteoffList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWRITEOFFLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultWriteoffList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultWriteoffList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWRITEOFFLIST, financialMgmtAcctSchemaDefaultWriteoffList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultWriteoffRevenueList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWRITEOFFREVENUELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultWriteoffRevenueList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultWriteoffRevenueList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWRITEOFFREVENUELIST, financialMgmtAcctSchemaDefaultWriteoffRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultPaymentDiscountRevenueList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTDISCOUNTREVENUELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultPaymentDiscountRevenueList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultPaymentDiscountRevenueList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTDISCOUNTREVENUELIST, financialMgmtAcctSchemaDefaultPaymentDiscountRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultUnrealizedGainsAcctList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNREALIZEDGAINSACCTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultUnrealizedGainsAcctList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultUnrealizedGainsAcctList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNREALIZEDGAINSACCTLIST, financialMgmtAcctSchemaDefaultUnrealizedGainsAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultUnrealizedLossesAcctList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNREALIZEDLOSSESACCTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultUnrealizedLossesAcctList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultUnrealizedLossesAcctList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNREALIZEDLOSSESACCTLIST, financialMgmtAcctSchemaDefaultUnrealizedLossesAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultRealizedGainAcctList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTREALIZEDGAINACCTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultRealizedGainAcctList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultRealizedGainAcctList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTREALIZEDGAINACCTLIST, financialMgmtAcctSchemaDefaultRealizedGainAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultRealizedLossAcctList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTREALIZEDLOSSACCTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultRealizedLossAcctList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultRealizedLossAcctList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTREALIZEDLOSSACCTLIST, financialMgmtAcctSchemaDefaultRealizedLossAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultWithholdingAccountList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWITHHOLDINGACCOUNTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultWithholdingAccountList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultWithholdingAccountList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWITHHOLDINGACCOUNTLIST, financialMgmtAcctSchemaDefaultWithholdingAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultEmployeePrepaymentsList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTEMPLOYEEPREPAYMENTSLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultEmployeePrepaymentsList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultEmployeePrepaymentsList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTEMPLOYEEPREPAYMENTSLIST, financialMgmtAcctSchemaDefaultEmployeePrepaymentsList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultEmployeeExpensesList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTEMPLOYEEEXPENSESLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultEmployeeExpensesList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultEmployeeExpensesList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTEMPLOYEEEXPENSESLIST, financialMgmtAcctSchemaDefaultEmployeeExpensesList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultProjectAssetList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPROJECTASSETLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultProjectAssetList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultProjectAssetList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPROJECTASSETLIST, financialMgmtAcctSchemaDefaultProjectAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultWorkInProgressList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWORKINPROGRESSLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultWorkInProgressList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultWorkInProgressList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTWORKINPROGRESSLIST, financialMgmtAcctSchemaDefaultWorkInProgressList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultTaxExpenseList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXEXPENSELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultTaxExpenseList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultTaxExpenseList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXEXPENSELIST, financialMgmtAcctSchemaDefaultTaxExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultTaxLiabilityList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXLIABILITYLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultTaxLiabilityList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultTaxLiabilityList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXLIABILITYLIST, financialMgmtAcctSchemaDefaultTaxLiabilityList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultTaxReceivablesList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXRECEIVABLESLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultTaxReceivablesList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultTaxReceivablesList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXRECEIVABLESLIST, financialMgmtAcctSchemaDefaultTaxReceivablesList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultTaxDueList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXDUELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultTaxDueList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultTaxDueList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXDUELIST, financialMgmtAcctSchemaDefaultTaxDueList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultTaxCreditList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXCREDITLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultTaxCreditList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultTaxCreditList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTTAXCREDITLIST, financialMgmtAcctSchemaDefaultTaxCreditList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankInTransitList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTRANSITLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankInTransitList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankInTransitList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTRANSITLIST, financialMgmtAcctSchemaDefaultBankInTransitList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankAssetList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKASSETLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankAssetList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankAssetList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKASSETLIST, financialMgmtAcctSchemaDefaultBankAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankExpenseList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKEXPENSELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankExpenseList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankExpenseList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKEXPENSELIST, financialMgmtAcctSchemaDefaultBankExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankInterestRevenueList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTERESTREVENUELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankInterestRevenueList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankInterestRevenueList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTERESTREVENUELIST, financialMgmtAcctSchemaDefaultBankInterestRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankInterestExpenseList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTERESTEXPENSELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankInterestExpenseList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankInterestExpenseList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKINTERESTEXPENSELIST, financialMgmtAcctSchemaDefaultBankInterestExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankUnidentifiedReceiptsList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKUNIDENTIFIEDRECEIPTSLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankUnidentifiedReceiptsList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankUnidentifiedReceiptsList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKUNIDENTIFIEDRECEIPTSLIST, financialMgmtAcctSchemaDefaultBankUnidentifiedReceiptsList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultUnallocatedCashList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNALLOCATEDCASHLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultUnallocatedCashList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultUnallocatedCashList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNALLOCATEDCASHLIST, financialMgmtAcctSchemaDefaultUnallocatedCashList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultPaymentSelectionList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTSELECTIONLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultPaymentSelectionList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultPaymentSelectionList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPAYMENTSELECTIONLIST, financialMgmtAcctSchemaDefaultPaymentSelectionList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankSettlementGainList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKSETTLEMENTGAINLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankSettlementGainList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankSettlementGainList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKSETTLEMENTGAINLIST, financialMgmtAcctSchemaDefaultBankSettlementGainList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankSettlementLossList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKSETTLEMENTLOSSLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankSettlementLossList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankSettlementLossList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKSETTLEMENTLOSSLIST, financialMgmtAcctSchemaDefaultBankSettlementLossList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankRevaluationGainList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKREVALUATIONGAINLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankRevaluationGainList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankRevaluationGainList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKREVALUATIONGAINLIST, financialMgmtAcctSchemaDefaultBankRevaluationGainList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBankRevaluationLossList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKREVALUATIONLOSSLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBankRevaluationLossList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBankRevaluationLossList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBANKREVALUATIONLOSSLIST, financialMgmtAcctSchemaDefaultBankRevaluationLossList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultChargeExpenseList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCHARGEEXPENSELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultChargeExpenseList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultChargeExpenseList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCHARGEEXPENSELIST, financialMgmtAcctSchemaDefaultChargeExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultChargeRevenueList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCHARGEREVENUELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultChargeRevenueList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultChargeRevenueList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCHARGEREVENUELIST, financialMgmtAcctSchemaDefaultChargeRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultUnearnedRevenueList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNEARNEDREVENUELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultUnearnedRevenueList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultUnearnedRevenueList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTUNEARNEDREVENUELIST, financialMgmtAcctSchemaDefaultUnearnedRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultNonInvoicedReceivablesList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDRECEIVABLESLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultNonInvoicedReceivablesList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultNonInvoicedReceivablesList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDRECEIVABLESLIST, financialMgmtAcctSchemaDefaultNonInvoicedReceivablesList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultNonInvoicedRevenuesList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDREVENUESLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultNonInvoicedRevenuesList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultNonInvoicedRevenuesList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDREVENUESLIST, financialMgmtAcctSchemaDefaultNonInvoicedRevenuesList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultNonInvoicedReceiptsList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDRECEIPTSLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultNonInvoicedReceiptsList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultNonInvoicedReceiptsList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTNONINVOICEDRECEIPTSLIST, financialMgmtAcctSchemaDefaultNonInvoicedReceiptsList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultCashBookAssetList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKASSETLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultCashBookAssetList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultCashBookAssetList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKASSETLIST, financialMgmtAcctSchemaDefaultCashBookAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultCashTransferList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHTRANSFERLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultCashTransferList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultCashTransferList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHTRANSFERLIST, financialMgmtAcctSchemaDefaultCashTransferList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultCashBookDifferencesList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKDIFFERENCESLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultCashBookDifferencesList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultCashBookDifferencesList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKDIFFERENCESLIST, financialMgmtAcctSchemaDefaultCashBookDifferencesList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultCashBookExpenseList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKEXPENSELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultCashBookExpenseList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultCashBookExpenseList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKEXPENSELIST, financialMgmtAcctSchemaDefaultCashBookExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultCashBookReceiptList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKRECEIPTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultCashBookReceiptList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultCashBookReceiptList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTCASHBOOKRECEIPTLIST, financialMgmtAcctSchemaDefaultCashBookReceiptList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultAccumulatedDepreciationList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTACCUMULATEDDEPRECIATIONLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultAccumulatedDepreciationList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultAccumulatedDepreciationList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTACCUMULATEDDEPRECIATIONLIST, financialMgmtAcctSchemaDefaultAccumulatedDepreciationList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultDepreciationList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDEPRECIATIONLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultDepreciationList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultDepreciationList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDEPRECIATIONLIST, financialMgmtAcctSchemaDefaultDepreciationList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultDisposalGainList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDISPOSALGAINLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultDisposalGainList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultDisposalGainList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDISPOSALGAINLIST, financialMgmtAcctSchemaDefaultDisposalGainList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultDisposalLossList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDISPOSALLOSSLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultDisposalLossList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultDisposalLossList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDISPOSALLOSSLIST, financialMgmtAcctSchemaDefaultDisposalLossList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultProductRevenueReturnList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTREVENUERETURNLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultProductRevenueReturnList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultProductRevenueReturnList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTREVENUERETURNLIST, financialMgmtAcctSchemaDefaultProductRevenueReturnList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultProductCOGSReturnList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTCOGSRETURNLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultProductCOGSReturnList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultProductCOGSReturnList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTCOGSRETURNLIST, financialMgmtAcctSchemaDefaultProductCOGSReturnList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultProductDeferredRevenueList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTDEFERREDREVENUELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultProductDeferredRevenueList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultProductDeferredRevenueList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTDEFERREDREVENUELIST, financialMgmtAcctSchemaDefaultProductDeferredRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultProductDeferredExpenseList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTDEFERREDEXPENSELIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultProductDeferredExpenseList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultProductDeferredExpenseList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTPRODUCTDEFERREDEXPENSELIST, financialMgmtAcctSchemaDefaultProductDeferredExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultDoubtfulDebtAccountList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDOUBTFULDEBTACCOUNTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultDoubtfulDebtAccountList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultDoubtfulDebtAccountList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTDOUBTFULDEBTACCOUNTLIST, financialMgmtAcctSchemaDefaultDoubtfulDebtAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBadDebtExpenseAccountList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBADDEBTEXPENSEACCOUNTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBadDebtExpenseAccountList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBadDebtExpenseAccountList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBADDEBTEXPENSEACCOUNTLIST, financialMgmtAcctSchemaDefaultBadDebtExpenseAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultBadDebtRevenueAccountList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBADDEBTREVENUEACCOUNTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultBadDebtRevenueAccountList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultBadDebtRevenueAccountList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTBADDEBTREVENUEACCOUNTLIST, financialMgmtAcctSchemaDefaultBadDebtRevenueAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaDefault> getFinancialMgmtAcctSchemaDefaultAllowanceForDoubtfulDebtAccountList() {
        return (List<AcctSchemaDefault>) get(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTALLOWANCEFORDOUBTFULDEBTACCOUNTLIST);
    }

    public void setFinancialMgmtAcctSchemaDefaultAllowanceForDoubtfulDebtAccountList(List<AcctSchemaDefault> financialMgmtAcctSchemaDefaultAllowanceForDoubtfulDebtAccountList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMADEFAULTALLOWANCEFORDOUBTFULDEBTACCOUNTLIST, financialMgmtAcctSchemaDefaultAllowanceForDoubtfulDebtAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLSuspenseBalancingList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLSUSPENSEBALANCINGLIST);
    }

    public void setFinancialMgmtAcctSchemaGLSuspenseBalancingList(List<AcctSchemaGL> financialMgmtAcctSchemaGLSuspenseBalancingList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLSUSPENSEBALANCINGLIST, financialMgmtAcctSchemaGLSuspenseBalancingList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLSuspenseErrorList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLSUSPENSEERRORLIST);
    }

    public void setFinancialMgmtAcctSchemaGLSuspenseErrorList(List<AcctSchemaGL> financialMgmtAcctSchemaGLSuspenseErrorList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLSUSPENSEERRORLIST, financialMgmtAcctSchemaGLSuspenseErrorList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLCurrencyBalancingAcctList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLCURRENCYBALANCINGACCTLIST);
    }

    public void setFinancialMgmtAcctSchemaGLCurrencyBalancingAcctList(List<AcctSchemaGL> financialMgmtAcctSchemaGLCurrencyBalancingAcctList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLCURRENCYBALANCINGACCTLIST, financialMgmtAcctSchemaGLCurrencyBalancingAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLRetainedEarningList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLRETAINEDEARNINGLIST);
    }

    public void setFinancialMgmtAcctSchemaGLRetainedEarningList(List<AcctSchemaGL> financialMgmtAcctSchemaGLRetainedEarningList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLRETAINEDEARNINGLIST, financialMgmtAcctSchemaGLRetainedEarningList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLIncomeSummaryList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLINCOMESUMMARYLIST);
    }

    public void setFinancialMgmtAcctSchemaGLIncomeSummaryList(List<AcctSchemaGL> financialMgmtAcctSchemaGLIncomeSummaryList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLINCOMESUMMARYLIST, financialMgmtAcctSchemaGLIncomeSummaryList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLDueToIntercompanyList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLDUETOINTERCOMPANYLIST);
    }

    public void setFinancialMgmtAcctSchemaGLDueToIntercompanyList(List<AcctSchemaGL> financialMgmtAcctSchemaGLDueToIntercompanyList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLDUETOINTERCOMPANYLIST, financialMgmtAcctSchemaGLDueToIntercompanyList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLDueFromIntercompanyList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLDUEFROMINTERCOMPANYLIST);
    }

    public void setFinancialMgmtAcctSchemaGLDueFromIntercompanyList(List<AcctSchemaGL> financialMgmtAcctSchemaGLDueFromIntercompanyList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLDUEFROMINTERCOMPANYLIST, financialMgmtAcctSchemaGLDueFromIntercompanyList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLPPVOffsetList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLPPVOFFSETLIST);
    }

    public void setFinancialMgmtAcctSchemaGLPPVOffsetList(List<AcctSchemaGL> financialMgmtAcctSchemaGLPPVOffsetList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLPPVOFFSETLIST, financialMgmtAcctSchemaGLPPVOffsetList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaGL> getFinancialMgmtAcctSchemaGLCFSOrderAccountList() {
        return (List<AcctSchemaGL>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAGLCFSORDERACCOUNTLIST);
    }

    public void setFinancialMgmtAcctSchemaGLCFSOrderAccountList(List<AcctSchemaGL> financialMgmtAcctSchemaGLCFSOrderAccountList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAGLCFSORDERACCOUNTLIST, financialMgmtAcctSchemaGLCFSOrderAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetAccounts> getFinancialMgmtAssetAccountsDepreciationList() {
        return (List<AssetAccounts>) get(PROPERTY_FINANCIALMGMTASSETACCOUNTSDEPRECIATIONLIST);
    }

    public void setFinancialMgmtAssetAccountsDepreciationList(List<AssetAccounts> financialMgmtAssetAccountsDepreciationList) {
        set(PROPERTY_FINANCIALMGMTASSETACCOUNTSDEPRECIATIONLIST, financialMgmtAssetAccountsDepreciationList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetAccounts> getFinancialMgmtAssetAccountsAccumulatedDepreciationList() {
        return (List<AssetAccounts>) get(PROPERTY_FINANCIALMGMTASSETACCOUNTSACCUMULATEDDEPRECIATIONLIST);
    }

    public void setFinancialMgmtAssetAccountsAccumulatedDepreciationList(List<AssetAccounts> financialMgmtAssetAccountsAccumulatedDepreciationList) {
        set(PROPERTY_FINANCIALMGMTASSETACCOUNTSACCUMULATEDDEPRECIATIONLIST, financialMgmtAssetAccountsAccumulatedDepreciationList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetAccounts> getFinancialMgmtAssetAccountsDisposalLossList() {
        return (List<AssetAccounts>) get(PROPERTY_FINANCIALMGMTASSETACCOUNTSDISPOSALLOSSLIST);
    }

    public void setFinancialMgmtAssetAccountsDisposalLossList(List<AssetAccounts> financialMgmtAssetAccountsDisposalLossList) {
        set(PROPERTY_FINANCIALMGMTASSETACCOUNTSDISPOSALLOSSLIST, financialMgmtAssetAccountsDisposalLossList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetAccounts> getFinancialMgmtAssetAccountsDisposalGainList() {
        return (List<AssetAccounts>) get(PROPERTY_FINANCIALMGMTASSETACCOUNTSDISPOSALGAINLIST);
    }

    public void setFinancialMgmtAssetAccountsDisposalGainList(List<AssetAccounts> financialMgmtAssetAccountsDisposalGainList) {
        set(PROPERTY_FINANCIALMGMTASSETACCOUNTSDISPOSALGAINLIST, financialMgmtAssetAccountsDisposalGainList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetGroupAcct> getFinancialMgmtAssetGroupAcctDepreciationList() {
        return (List<AssetGroupAcct>) get(PROPERTY_FINANCIALMGMTASSETGROUPACCTDEPRECIATIONLIST);
    }

    public void setFinancialMgmtAssetGroupAcctDepreciationList(List<AssetGroupAcct> financialMgmtAssetGroupAcctDepreciationList) {
        set(PROPERTY_FINANCIALMGMTASSETGROUPACCTDEPRECIATIONLIST, financialMgmtAssetGroupAcctDepreciationList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetGroupAcct> getFinancialMgmtAssetGroupAcctAccumulatedDepreciationList() {
        return (List<AssetGroupAcct>) get(PROPERTY_FINANCIALMGMTASSETGROUPACCTACCUMULATEDDEPRECIATIONLIST);
    }

    public void setFinancialMgmtAssetGroupAcctAccumulatedDepreciationList(List<AssetGroupAcct> financialMgmtAssetGroupAcctAccumulatedDepreciationList) {
        set(PROPERTY_FINANCIALMGMTASSETGROUPACCTACCUMULATEDDEPRECIATIONLIST, financialMgmtAssetGroupAcctAccumulatedDepreciationList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetGroupAcct> getFinancialMgmtAssetGroupAcctDisposalLossList() {
        return (List<AssetGroupAcct>) get(PROPERTY_FINANCIALMGMTASSETGROUPACCTDISPOSALLOSSLIST);
    }

    public void setFinancialMgmtAssetGroupAcctDisposalLossList(List<AssetGroupAcct> financialMgmtAssetGroupAcctDisposalLossList) {
        set(PROPERTY_FINANCIALMGMTASSETGROUPACCTDISPOSALLOSSLIST, financialMgmtAssetGroupAcctDisposalLossList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetGroupAcct> getFinancialMgmtAssetGroupAcctDisposalGainList() {
        return (List<AssetGroupAcct>) get(PROPERTY_FINANCIALMGMTASSETGROUPACCTDISPOSALGAINLIST);
    }

    public void setFinancialMgmtAssetGroupAcctDisposalGainList(List<AssetGroupAcct> financialMgmtAssetGroupAcctDisposalGainList) {
        set(PROPERTY_FINANCIALMGMTASSETGROUPACCTDISPOSALGAINLIST, financialMgmtAssetGroupAcctDisposalGainList);
    }

    @SuppressWarnings("unchecked")
    public List<CashBookAccounts> getFinancialMgmtCashBookAccountsCashBookAssetList() {
        return (List<CashBookAccounts>) get(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKASSETLIST);
    }

    public void setFinancialMgmtCashBookAccountsCashBookAssetList(List<CashBookAccounts> financialMgmtCashBookAccountsCashBookAssetList) {
        set(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKASSETLIST, financialMgmtCashBookAccountsCashBookAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<CashBookAccounts> getFinancialMgmtCashBookAccountsCashTransferList() {
        return (List<CashBookAccounts>) get(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHTRANSFERLIST);
    }

    public void setFinancialMgmtCashBookAccountsCashTransferList(List<CashBookAccounts> financialMgmtCashBookAccountsCashTransferList) {
        set(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHTRANSFERLIST, financialMgmtCashBookAccountsCashTransferList);
    }

    @SuppressWarnings("unchecked")
    public List<CashBookAccounts> getFinancialMgmtCashBookAccountsCashBookDifferencesList() {
        return (List<CashBookAccounts>) get(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKDIFFERENCESLIST);
    }

    public void setFinancialMgmtCashBookAccountsCashBookDifferencesList(List<CashBookAccounts> financialMgmtCashBookAccountsCashBookDifferencesList) {
        set(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKDIFFERENCESLIST, financialMgmtCashBookAccountsCashBookDifferencesList);
    }

    @SuppressWarnings("unchecked")
    public List<CashBookAccounts> getFinancialMgmtCashBookAccountsCashBookExpenseList() {
        return (List<CashBookAccounts>) get(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKEXPENSELIST);
    }

    public void setFinancialMgmtCashBookAccountsCashBookExpenseList(List<CashBookAccounts> financialMgmtCashBookAccountsCashBookExpenseList) {
        set(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKEXPENSELIST, financialMgmtCashBookAccountsCashBookExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<CashBookAccounts> getFinancialMgmtCashBookAccountsCashBookReceiptList() {
        return (List<CashBookAccounts>) get(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKRECEIPTLIST);
    }

    public void setFinancialMgmtCashBookAccountsCashBookReceiptList(List<CashBookAccounts> financialMgmtCashBookAccountsCashBookReceiptList) {
        set(PROPERTY_FINANCIALMGMTCASHBOOKACCOUNTSCASHBOOKRECEIPTLIST, financialMgmtCashBookAccountsCashBookReceiptList);
    }

    @SuppressWarnings("unchecked")
    public List<GLChargeAccounts> getFinancialMgmtGLChargeAccountsChargeExpenseList() {
        return (List<GLChargeAccounts>) get(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSCHARGEEXPENSELIST);
    }

    public void setFinancialMgmtGLChargeAccountsChargeExpenseList(List<GLChargeAccounts> financialMgmtGLChargeAccountsChargeExpenseList) {
        set(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSCHARGEEXPENSELIST, financialMgmtGLChargeAccountsChargeExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<GLChargeAccounts> getFinancialMgmtGLChargeAccountsChargeRevenueList() {
        return (List<GLChargeAccounts>) get(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSCHARGEREVENUELIST);
    }

    public void setFinancialMgmtGLChargeAccountsChargeRevenueList(List<GLChargeAccounts> financialMgmtGLChargeAccountsChargeRevenueList) {
        set(PROPERTY_FINANCIALMGMTGLCHARGEACCOUNTSCHARGEREVENUELIST, financialMgmtGLChargeAccountsChargeRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<GLItemAccounts> getFinancialMgmtGLItemAccountsGlitemDebitAcctList() {
        return (List<GLItemAccounts>) get(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSGLITEMDEBITACCTLIST);
    }

    public void setFinancialMgmtGLItemAccountsGlitemDebitAcctList(List<GLItemAccounts> financialMgmtGLItemAccountsGlitemDebitAcctList) {
        set(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSGLITEMDEBITACCTLIST, financialMgmtGLItemAccountsGlitemDebitAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<GLItemAccounts> getFinancialMgmtGLItemAccountsGlitemCreditAcctList() {
        return (List<GLItemAccounts>) get(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSGLITEMCREDITACCTLIST);
    }

    public void setFinancialMgmtGLItemAccountsGlitemCreditAcctList(List<GLItemAccounts> financialMgmtGLItemAccountsGlitemCreditAcctList) {
        set(PROPERTY_FINANCIALMGMTGLITEMACCOUNTSGLITEMCREDITACCTLIST, financialMgmtGLItemAccountsGlitemCreditAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
        return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRateAccounts> getFinancialMgmtTaxRateAccountsTaxDueList() {
        return (List<TaxRateAccounts>) get(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXDUELIST);
    }

    public void setFinancialMgmtTaxRateAccountsTaxDueList(List<TaxRateAccounts> financialMgmtTaxRateAccountsTaxDueList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXDUELIST, financialMgmtTaxRateAccountsTaxDueList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRateAccounts> getFinancialMgmtTaxRateAccountsTaxLiabilityList() {
        return (List<TaxRateAccounts>) get(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXLIABILITYLIST);
    }

    public void setFinancialMgmtTaxRateAccountsTaxLiabilityList(List<TaxRateAccounts> financialMgmtTaxRateAccountsTaxLiabilityList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXLIABILITYLIST, financialMgmtTaxRateAccountsTaxLiabilityList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRateAccounts> getFinancialMgmtTaxRateAccountsTaxCreditList() {
        return (List<TaxRateAccounts>) get(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXCREDITLIST);
    }

    public void setFinancialMgmtTaxRateAccountsTaxCreditList(List<TaxRateAccounts> financialMgmtTaxRateAccountsTaxCreditList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXCREDITLIST, financialMgmtTaxRateAccountsTaxCreditList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRateAccounts> getFinancialMgmtTaxRateAccountsTaxReceivablesList() {
        return (List<TaxRateAccounts>) get(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXRECEIVABLESLIST);
    }

    public void setFinancialMgmtTaxRateAccountsTaxReceivablesList(List<TaxRateAccounts> financialMgmtTaxRateAccountsTaxReceivablesList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXRECEIVABLESLIST, financialMgmtTaxRateAccountsTaxReceivablesList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRateAccounts> getFinancialMgmtTaxRateAccountsTaxExpenseList() {
        return (List<TaxRateAccounts>) get(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXEXPENSELIST);
    }

    public void setFinancialMgmtTaxRateAccountsTaxExpenseList(List<TaxRateAccounts> financialMgmtTaxRateAccountsTaxExpenseList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEACCOUNTSTAXEXPENSELIST, financialMgmtTaxRateAccountsTaxExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<WithholdingAccounts> getFinancialMgmtWithholdingAccountsWithholdingAccountList() {
        return (List<WithholdingAccounts>) get(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSWITHHOLDINGACCOUNTLIST);
    }

    public void setFinancialMgmtWithholdingAccountsWithholdingAccountList(List<WithholdingAccounts> financialMgmtWithholdingAccountsWithholdingAccountList) {
        set(PROPERTY_FINANCIALMGMTWITHHOLDINGACCOUNTSWITHHOLDINGACCOUNTLIST, financialMgmtWithholdingAccountsWithholdingAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsProductRevenueList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPRODUCTREVENUELIST);
    }

    public void setProductAccountsProductRevenueList(List<ProductAccounts> productAccountsProductRevenueList) {
        set(PROPERTY_PRODUCTACCOUNTSPRODUCTREVENUELIST, productAccountsProductRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsProductExpenseList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPRODUCTEXPENSELIST);
    }

    public void setProductAccountsProductExpenseList(List<ProductAccounts> productAccountsProductExpenseList) {
        set(PROPERTY_PRODUCTACCOUNTSPRODUCTEXPENSELIST, productAccountsProductExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsFixedAssetList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSFIXEDASSETLIST);
    }

    public void setProductAccountsFixedAssetList(List<ProductAccounts> productAccountsFixedAssetList) {
        set(PROPERTY_PRODUCTACCOUNTSFIXEDASSETLIST, productAccountsFixedAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsPurchasePriceVarianceList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPURCHASEPRICEVARIANCELIST);
    }

    public void setProductAccountsPurchasePriceVarianceList(List<ProductAccounts> productAccountsPurchasePriceVarianceList) {
        set(PROPERTY_PRODUCTACCOUNTSPURCHASEPRICEVARIANCELIST, productAccountsPurchasePriceVarianceList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsInvoicePriceVarianceList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSINVOICEPRICEVARIANCELIST);
    }

    public void setProductAccountsInvoicePriceVarianceList(List<ProductAccounts> productAccountsInvoicePriceVarianceList) {
        set(PROPERTY_PRODUCTACCOUNTSINVOICEPRICEVARIANCELIST, productAccountsInvoicePriceVarianceList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsProductCOGSList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPRODUCTCOGSLIST);
    }

    public void setProductAccountsProductCOGSList(List<ProductAccounts> productAccountsProductCOGSList) {
        set(PROPERTY_PRODUCTACCOUNTSPRODUCTCOGSLIST, productAccountsProductCOGSList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsTradeDiscountReceivedList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSTRADEDISCOUNTRECEIVEDLIST);
    }

    public void setProductAccountsTradeDiscountReceivedList(List<ProductAccounts> productAccountsTradeDiscountReceivedList) {
        set(PROPERTY_PRODUCTACCOUNTSTRADEDISCOUNTRECEIVEDLIST, productAccountsTradeDiscountReceivedList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsTradeDiscountGrantedList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSTRADEDISCOUNTGRANTEDLIST);
    }

    public void setProductAccountsTradeDiscountGrantedList(List<ProductAccounts> productAccountsTradeDiscountGrantedList) {
        set(PROPERTY_PRODUCTACCOUNTSTRADEDISCOUNTGRANTEDLIST, productAccountsTradeDiscountGrantedList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsProductRevenueReturnList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPRODUCTREVENUERETURNLIST);
    }

    public void setProductAccountsProductRevenueReturnList(List<ProductAccounts> productAccountsProductRevenueReturnList) {
        set(PROPERTY_PRODUCTACCOUNTSPRODUCTREVENUERETURNLIST, productAccountsProductRevenueReturnList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsProductCOGSReturnList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPRODUCTCOGSRETURNLIST);
    }

    public void setProductAccountsProductCOGSReturnList(List<ProductAccounts> productAccountsProductCOGSReturnList) {
        set(PROPERTY_PRODUCTACCOUNTSPRODUCTCOGSRETURNLIST, productAccountsProductCOGSReturnList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsProductDeferredRevenueList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPRODUCTDEFERREDREVENUELIST);
    }

    public void setProductAccountsProductDeferredRevenueList(List<ProductAccounts> productAccountsProductDeferredRevenueList) {
        set(PROPERTY_PRODUCTACCOUNTSPRODUCTDEFERREDREVENUELIST, productAccountsProductDeferredRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsPDefExpenseAcctList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPDEFEXPENSEACCTLIST);
    }

    public void setProductAccountsPDefExpenseAcctList(List<ProductAccounts> productAccountsPDefExpenseAcctList) {
        set(PROPERTY_PRODUCTACCOUNTSPDEFEXPENSEACCTLIST, productAccountsPDefExpenseAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsPrassetList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPRASSETLIST);
    }

    public void setProductAccountsPrassetList(List<ProductAccounts> productAccountsPrassetList) {
        set(PROPERTY_PRODUCTACCOUNTSPRASSETLIST, productAccountsPrassetList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsPrexpenseList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPREXPENSELIST);
    }

    public void setProductAccountsPrexpenseList(List<ProductAccounts> productAccountsPrexpenseList) {
        set(PROPERTY_PRODUCTACCOUNTSPREXPENSELIST, productAccountsPrexpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsPrwipList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSPRWIPLIST);
    }

    public void setProductAccountsPrwipList(List<ProductAccounts> productAccountsPrwipList) {
        set(PROPERTY_PRODUCTACCOUNTSPRWIPLIST, productAccountsPrwipList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsProductRevenueList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTREVENUELIST);
    }

    public void setProductCategoryAccountsProductRevenueList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsProductRevenueList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTREVENUELIST, productCategoryAccountsProductRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsProductExpenseList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTEXPENSELIST);
    }

    public void setProductCategoryAccountsProductExpenseList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsProductExpenseList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTEXPENSELIST, productCategoryAccountsProductExpenseList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsFixedAssetList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSFIXEDASSETLIST);
    }

    public void setProductCategoryAccountsFixedAssetList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsFixedAssetList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSFIXEDASSETLIST, productCategoryAccountsFixedAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsProductCOGSList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTCOGSLIST);
    }

    public void setProductCategoryAccountsProductCOGSList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsProductCOGSList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTCOGSLIST, productCategoryAccountsProductCOGSList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsPurchasePriceVarianceList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSPURCHASEPRICEVARIANCELIST);
    }

    public void setProductCategoryAccountsPurchasePriceVarianceList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsPurchasePriceVarianceList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSPURCHASEPRICEVARIANCELIST, productCategoryAccountsPurchasePriceVarianceList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsInvoicePriceVarianceList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSINVOICEPRICEVARIANCELIST);
    }

    public void setProductCategoryAccountsInvoicePriceVarianceList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsInvoicePriceVarianceList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSINVOICEPRICEVARIANCELIST, productCategoryAccountsInvoicePriceVarianceList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsTradeDiscountReceivedList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSTRADEDISCOUNTRECEIVEDLIST);
    }

    public void setProductCategoryAccountsTradeDiscountReceivedList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsTradeDiscountReceivedList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSTRADEDISCOUNTRECEIVEDLIST, productCategoryAccountsTradeDiscountReceivedList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsTradeDiscountGrantedList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSTRADEDISCOUNTGRANTEDLIST);
    }

    public void setProductCategoryAccountsTradeDiscountGrantedList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsTradeDiscountGrantedList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSTRADEDISCOUNTGRANTEDLIST, productCategoryAccountsTradeDiscountGrantedList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsProductCOGSReturnList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTCOGSRETURNLIST);
    }

    public void setProductCategoryAccountsProductCOGSReturnList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsProductCOGSReturnList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTCOGSRETURNLIST, productCategoryAccountsProductCOGSReturnList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsProductRevenueReturnList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTREVENUERETURNLIST);
    }

    public void setProductCategoryAccountsProductRevenueReturnList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsProductRevenueReturnList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTREVENUERETURNLIST, productCategoryAccountsProductRevenueReturnList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsProductDeferredRevenueList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTDEFERREDREVENUELIST);
    }

    public void setProductCategoryAccountsProductDeferredRevenueList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsProductDeferredRevenueList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSPRODUCTDEFERREDREVENUELIST, productCategoryAccountsProductDeferredRevenueList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.CategoryAccounts> getProductCategoryAccountsPDefExpenseAcctList() {
        return (List<org.openbravo.model.common.plm.CategoryAccounts>) get(PROPERTY_PRODUCTCATEGORYACCOUNTSPDEFEXPENSEACCTLIST);
    }

    public void setProductCategoryAccountsPDefExpenseAcctList(List<org.openbravo.model.common.plm.CategoryAccounts> productCategoryAccountsPDefExpenseAcctList) {
        set(PROPERTY_PRODUCTCATEGORYACCOUNTSPDEFEXPENSEACCTLIST, productCategoryAccountsPDefExpenseAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectAccounts> getProjectAccountsProjectAssetList() {
        return (List<ProjectAccounts>) get(PROPERTY_PROJECTACCOUNTSPROJECTASSETLIST);
    }

    public void setProjectAccountsProjectAssetList(List<ProjectAccounts> projectAccountsProjectAssetList) {
        set(PROPERTY_PROJECTACCOUNTSPROJECTASSETLIST, projectAccountsProjectAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectAccounts> getProjectAccountsWorkInProgressList() {
        return (List<ProjectAccounts>) get(PROPERTY_PROJECTACCOUNTSWORKINPROGRESSLIST);
    }

    public void setProjectAccountsWorkInProgressList(List<ProjectAccounts> projectAccountsWorkInProgressList) {
        set(PROPERTY_PROJECTACCOUNTSWORKINPROGRESSLIST, projectAccountsWorkInProgressList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrExpenseType> getRCPRPrExpenseTypeProductAssetList() {
        return (List<RCPR_PrExpenseType>) get(PROPERTY_RCPRPREXPENSETYPEPRODUCTASSETLIST);
    }

    public void setRCPRPrExpenseTypeProductAssetList(List<RCPR_PrExpenseType> rCPRPrExpenseTypeProductAssetList) {
        set(PROPERTY_RCPRPREXPENSETYPEPRODUCTASSETLIST, rCPRPrExpenseTypeProductAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrProcessLevel> getRCPRPrProcessLevelProductAssetList() {
        return (List<RCPR_PrProcessLevel>) get(PROPERTY_RCPRPRPROCESSLEVELPRODUCTASSETLIST);
    }

    public void setRCPRPrProcessLevelProductAssetList(List<RCPR_PrProcessLevel> rCPRPrProcessLevelProductAssetList) {
        set(PROPERTY_RCPRPRPROCESSLEVELPRODUCTASSETLIST, rCPRPrProcessLevelProductAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<VendorAccounts> getVendorAccountsVendorLiabilityList() {
        return (List<VendorAccounts>) get(PROPERTY_VENDORACCOUNTSVENDORLIABILITYLIST);
    }

    public void setVendorAccountsVendorLiabilityList(List<VendorAccounts> vendorAccountsVendorLiabilityList) {
        set(PROPERTY_VENDORACCOUNTSVENDORLIABILITYLIST, vendorAccountsVendorLiabilityList);
    }

    @SuppressWarnings("unchecked")
    public List<VendorAccounts> getVendorAccountsVendorServiceLiabilityList() {
        return (List<VendorAccounts>) get(PROPERTY_VENDORACCOUNTSVENDORSERVICELIABILITYLIST);
    }

    public void setVendorAccountsVendorServiceLiabilityList(List<VendorAccounts> vendorAccountsVendorServiceLiabilityList) {
        set(PROPERTY_VENDORACCOUNTSVENDORSERVICELIABILITYLIST, vendorAccountsVendorServiceLiabilityList);
    }

    @SuppressWarnings("unchecked")
    public List<VendorAccounts> getVendorAccountsVendorPrepaymentList() {
        return (List<VendorAccounts>) get(PROPERTY_VENDORACCOUNTSVENDORPREPAYMENTLIST);
    }

    public void setVendorAccountsVendorPrepaymentList(List<VendorAccounts> vendorAccountsVendorPrepaymentList) {
        set(PROPERTY_VENDORACCOUNTSVENDORPREPAYMENTLIST, vendorAccountsVendorPrepaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseAccounts> getWarehouseAccountsWarehouseInventoryList() {
        return (List<WarehouseAccounts>) get(PROPERTY_WAREHOUSEACCOUNTSWAREHOUSEINVENTORYLIST);
    }

    public void setWarehouseAccountsWarehouseInventoryList(List<WarehouseAccounts> warehouseAccountsWarehouseInventoryList) {
        set(PROPERTY_WAREHOUSEACCOUNTSWAREHOUSEINVENTORYLIST, warehouseAccountsWarehouseInventoryList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseAccounts> getWarehouseAccountsInventoryAdjustmentList() {
        return (List<WarehouseAccounts>) get(PROPERTY_WAREHOUSEACCOUNTSINVENTORYADJUSTMENTLIST);
    }

    public void setWarehouseAccountsInventoryAdjustmentList(List<WarehouseAccounts> warehouseAccountsInventoryAdjustmentList) {
        set(PROPERTY_WAREHOUSEACCOUNTSINVENTORYADJUSTMENTLIST, warehouseAccountsInventoryAdjustmentList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseAccounts> getWarehouseAccountsWarehouseDifferencesList() {
        return (List<WarehouseAccounts>) get(PROPERTY_WAREHOUSEACCOUNTSWAREHOUSEDIFFERENCESLIST);
    }

    public void setWarehouseAccountsWarehouseDifferencesList(List<WarehouseAccounts> warehouseAccountsWarehouseDifferencesList) {
        set(PROPERTY_WAREHOUSEACCOUNTSWAREHOUSEDIFFERENCESLIST, warehouseAccountsWarehouseDifferencesList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseAccounts> getWarehouseAccountsInventoryRevaluationList() {
        return (List<WarehouseAccounts>) get(PROPERTY_WAREHOUSEACCOUNTSINVENTORYREVALUATIONLIST);
    }

    public void setWarehouseAccountsInventoryRevaluationList(List<WarehouseAccounts> warehouseAccountsInventoryRevaluationList) {
        set(PROPERTY_WAREHOUSEACCOUNTSINVENTORYREVALUATIONLIST, warehouseAccountsInventoryRevaluationList);
    }

}
