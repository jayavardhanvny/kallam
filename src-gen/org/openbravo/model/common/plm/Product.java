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
package org.openbravo.model.common.plm;

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.redcarpet.epcg.data.EPCGBookmngmnt;
import com.redcarpet.epcg.data.EPCG_Bookuser;
import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.EpcgMacSpare;
import com.redcarpet.epcg.data.EpcgMof;
import com.redcarpet.epcg.data.EpcgProformaline;
import com.redcarpet.epcg.data.EpcgWarpcosting;
import com.redcarpet.epcg.data.EpcgWeftcosting;
import com.redcarpet.epcg.data.EpcgYarncosttemplatelines;
import com.redcarpet.epcg.data.epcg_ppcenquiry;
import com.redcarpet.goodsissue.data.RCGIDiFifoLines;
import com.redcarpet.goodsissue.data.RCGIDiLines;
import com.redcarpet.goodsissue.data.RCGIDrLines;
import com.redcarpet.goodsissue.data.RCGIPurchasePrice;
import com.redcarpet.goodsissue.data.RCGITransactions;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.goodsissue.data.RCGI_MrLines;
import com.redcarpet.production.data.RCPR_Consumables;
import com.redcarpet.production.data.RCPR_MachineCategory;
import com.redcarpet.production.data.RCPR_PrBOM;
import com.redcarpet.production.data.RCPR_PrBOMLines;
import com.redcarpet.production.data.RCPR_PrProduct;
import com.redcarpet.production.data.RCPR_ProductionRun;
import com.redcarpet.rcssob.data.Prlines;
import com.redcarpet.rcssob.data.PurchaseQuotationLines;
import com.redcarpet.rcssob.data.RcobStoragebins;

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
import org.openbravo.model.ad.system.ClientInformation;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.ProductTemplate;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.invoice.InvoiceLineAccountingDimension;
import org.openbravo.model.common.invoice.InvoiceLineV2;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.order.OrderLineAccountingDimension;
import org.openbravo.model.common.order.ReturnMaterialOrderPickEditLines;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchemaElement;
import org.openbravo.model.financialmgmt.assetmgmt.AmortizationLineAccountingDimension;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.gl.GLJournal;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebt;
import org.openbravo.model.financialmgmt.payment.FIN_FinaccTransaction;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentDetailV;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentScheduleDetail;
import org.openbravo.model.financialmgmt.tax.TaxCategory;
import org.openbravo.model.manufacturing.processplan.OperationProduct;
import org.openbravo.model.manufacturing.processplan.ProcessPlan;
import org.openbravo.model.manufacturing.quality.Case;
import org.openbravo.model.manufacturing.transaction.GlobalUse;
import org.openbravo.model.manufacturing.transaction.WorkRequirementProduct;
import org.openbravo.model.materialmgmt.cost.Costing;
import org.openbravo.model.materialmgmt.cost.CostingRule;
import org.openbravo.model.materialmgmt.cost.CostingRuleProductV;
import org.openbravo.model.materialmgmt.onhandquantity.ProductStockView;
import org.openbravo.model.materialmgmt.onhandquantity.Reservation;
import org.openbravo.model.materialmgmt.onhandquantity.StorageDetail;
import org.openbravo.model.materialmgmt.onhandquantity.StoragePending;
import org.openbravo.model.materialmgmt.transaction.InOutLineAccountingDimension;
import org.openbravo.model.materialmgmt.transaction.InternalConsumptionLine;
import org.openbravo.model.materialmgmt.transaction.InternalMovementLine;
import org.openbravo.model.materialmgmt.transaction.InventoryCountLine;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.model.materialmgmt.transaction.MaterialTransactionV;
import org.openbravo.model.materialmgmt.transaction.ProductionLine;
import org.openbravo.model.materialmgmt.transaction.ProductionPlan;
import org.openbravo.model.materialmgmt.transaction.ReturnMaterialReceiptPickEdit;
import org.openbravo.model.materialmgmt.transaction.ReturnMaterialShipmentPickEdit;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.model.mrp.Planner;
import org.openbravo.model.mrp.PlanningMethod;
import org.openbravo.model.mrp.ProductionRun;
import org.openbravo.model.mrp.ProductionRunLine;
import org.openbravo.model.mrp.PurchasingRun;
import org.openbravo.model.mrp.PurchasingRunLine;
import org.openbravo.model.mrp.SalesForecastLine;
import org.openbravo.model.pos.ExternalPOSProduct;
import org.openbravo.model.pricing.discount.Discount;
import org.openbravo.model.pricing.pricelist.PriceListSchemeLine;
import org.openbravo.model.pricing.pricelist.ProductPrice;
import org.openbravo.model.procurement.POInvoiceMatch;
import org.openbravo.model.procurement.ReceiptInvoiceMatch;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.project.ProjectIssue;
import org.openbravo.model.project.ProjectLine;
import org.openbravo.model.project.ProjectPhase;
import org.openbravo.model.project.ProjectProposalLine;
import org.openbravo.model.project.ProjectTask;
import org.openbravo.model.project.StandardPhase;
import org.openbravo.model.project.StandardTask;
import org.openbravo.model.sales.Commission;
import org.openbravo.model.sales.CommissionLine;
import org.openbravo.model.shipping.FreightCategory;
import org.openbravo.model.timeandexpense.ExpenseType;
import org.openbravo.model.timeandexpense.Resource;
import org.openbravo.model.timeandexpense.SheetLine;
import org.openbravo.model.timeandexpense.SheetLineV;
/**
 * Entity class for entity Product (stored in table M_Product).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Product extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Product";
    public static final String ENTITY_NAME = "Product";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_SKU = "sKU";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_SALESREPRESENTATIVE = "salesRepresentative";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_STOCKED = "stocked";
    public static final String PROPERTY_PURCHASE = "purchase";
    public static final String PROPERTY_SALE = "sale";
    public static final String PROPERTY_BILLOFMATERIALS = "billOfMaterials";
    public static final String PROPERTY_PRINTDETAILSONINVOICE = "printDetailsOnInvoice";
    public static final String PROPERTY_PRINTDETAILSONPICKLIST = "printDetailsOnPickList";
    public static final String PROPERTY_BOMVERIFIED = "bOMVerified";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_CLASSIFICATION = "classification";
    public static final String PROPERTY_VOLUME = "volume";
    public static final String PROPERTY_WEIGHT = "weight";
    public static final String PROPERTY_SHELFWIDTH = "shelfWidth";
    public static final String PROPERTY_SHELFHEIGHT = "shelfHeight";
    public static final String PROPERTY_SHELFDEPTH = "shelfDepth";
    public static final String PROPERTY_UNITSPERPALLET = "unitsPerPallet";
    public static final String PROPERTY_TAXCATEGORY = "taxCategory";
    public static final String PROPERTY_RESOURCE = "resource";
    public static final String PROPERTY_DISCONTINUED = "discontinued";
    public static final String PROPERTY_DISCONTINUEDBY = "discontinuedBy";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_EXPENSETYPE = "expenseType";
    public static final String PROPERTY_PRODUCTTYPE = "productType";
    public static final String PROPERTY_IMAGEURL = "imageURL";
    public static final String PROPERTY_DESCRIPTIONURL = "descriptionURL";
    public static final String PROPERTY_GUARANTEEDDAYS = "guaranteedDays";
    public static final String PROPERTY_VERSIONNO = "versionNo";
    public static final String PROPERTY_ATTRIBUTESET = "attributeSet";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_DOWNLOADURL = "downloadURL";
    public static final String PROPERTY_FREIGHTCATEGORY = "freightCategory";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PRINTPRICE = "printPrice";
    public static final String PROPERTY_NAME2 = "name2";
    public static final String PROPERTY_COSTTYPE = "costType";
    public static final String PROPERTY_STANDARDCOST = "standardCost";
    public static final String PROPERTY_MINIMUMSTOCK = "minimumStock";
    public static final String PROPERTY_ENFORCEATTRIBUTE = "enforceAttribute";
    public static final String PROPERTY_CALCULATED = "calculated";
    public static final String PROPERTY_PROCESSPLAN = "processPlan";
    public static final String PROPERTY_PRODUCTION = "production";
    public static final String PROPERTY_CAPACITY = "capacity";
    public static final String PROPERTY_MINIMUMLEADTIME = "minimumLeadTime";
    public static final String PROPERTY_PLANNER = "planner";
    public static final String PROPERTY_PLANNINGMETHOD = "planningMethod";
    public static final String PROPERTY_MAXQUANTITY = "maxQuantity";
    public static final String PROPERTY_MINQUANTITY = "minQuantity";
    public static final String PROPERTY_STANDARDQUANTITY = "standardQuantity";
    public static final String PROPERTY_QUANTITYTYPE = "quantityType";
    public static final String PROPERTY_SAFETYSTOCK = "safetyStock";
    public static final String PROPERTY_USEATTRIBUTESETVALUEAS = "useAttributeSetValueAs";
    public static final String PROPERTY_ISQUANTITYVARIABLE = "isquantityvariable";
    public static final String PROPERTY_DEFERREDREVENUE = "deferredRevenue";
    public static final String PROPERTY_REVENUEPLANTYPE = "revenuePlanType";
    public static final String PROPERTY_PERIODNUMBER = "periodNumber";
    public static final String PROPERTY_ISDEFERREDEXPENSE = "isdeferredexpense";
    public static final String PROPERTY_EXPPLANTYPE = "expplantype";
    public static final String PROPERTY_PERIODNUMBEREXP = "periodnumberExp";
    public static final String PROPERTY_DEFAULTPERIOD = "defaultPeriod";
    public static final String PROPERTY_DEFAULTPERIODEXPENSE = "defaultPeriodExpense";
    public static final String PROPERTY_BOOKUSINGPURCHASEORDERPRICE = "bookUsingPurchaseOrderPrice";
    public static final String PROPERTY_UOMFORWEIGHT = "uOMForWeight";
    public static final String PROPERTY_RCLOISLOT = "rcloIslot";
    public static final String PROPERTY_EPCGMAKE = "epcgMake";
    public static final String PROPERTY_RCGIDEPTPRODUCT = "rcgiDeptproduct";
    public static final String PROPERTY_RCGIKSMLBARCODE = "rcgiKsmlbarcode";
    public static final String PROPERTY_RCGIREMQTY = "rcgiRemqty";
    public static final String PROPERTY_RCGIAVGCP = "rcgiAvgcp";
    public static final String PROPERTY_RCGICURRENTSP = "rcgiCurrentsp";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONVLIST = "aPRMFinaccTransactionVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST = "amortizationLineAccountingDimensionList";
    public static final String PROPERTY_APPROVEDVENDORLIST = "approvedVendorList";
    public static final String PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST = "businessPartnerProductTemplateList";
    public static final String PROPERTY_CLIENTINFORMATIONPRODUCTFORFREIGHTLIST = "clientInformationProductForFreightList";
    public static final String PROPERTY_COSTINGRULELIST = "costingRuleList";
    public static final String PROPERTY_COSTINGRULEPRODUCTVAPPLYPRODUCTLIST = "costingRuleProductVApplyProductList";
    public static final String PROPERTY_COSTINGRULEPRODUCTVLIST = "costingRuleProductVList";
    public static final String PROPERTY_EPCGBOOKMNGMNTLIST = "ePCGBookmngmntList";
    public static final String PROPERTY_EPCGBOOKUSERLIST = "ePCGBookuserList";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_EPCGCOSTENQUIRYWEFTPRODUCTLIST = "epcgCostenquiryWeftproductList";
    public static final String PROPERTY_EPCGMACSPARELIST = "epcgMacSpareList";
    public static final String PROPERTY_EPCGMOFLIST = "epcgMofList";
    public static final String PROPERTY_EPCGPROFORMALINELIST = "epcgProformalineList";
    public static final String PROPERTY_EPCGWARPCOSTINGLIST = "epcgWarpcostingList";
    public static final String PROPERTY_EPCGWEFTCOSTINGLIST = "epcgWeftcostingList";
    public static final String PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST = "epcgYarncosttemplatelinesList";
    public static final String PROPERTY_EXTERNALPOSPRODUCTLIST = "externalPOSProductList";
    public static final String PROPERTY_FINDOUBTFULDEBTLIST = "fINDoubtfulDebtList";
    public static final String PROPERTY_FINFINACCTRANSACTIONLIST = "fINFinaccTransactionList";
    public static final String PROPERTY_FINPAYMENTDETAILVLIST = "fINPaymentDetailVList";
    public static final String PROPERTY_FINPAYMENTSCHEDULEDETAILLIST = "fINPaymentScheduleDetailList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST = "financialMgmtAccountingCombinationList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST = "financialMgmtAcctSchemaElementList";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST = "inOutLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST = "invoiceLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELINEV2LIST = "invoiceLineV2List";
    public static final String PROPERTY_LOTLIST = "lotList";
    public static final String PROPERTY_MRPPRODUCTIONRUNLIST = "mRPProductionRunList";
    public static final String PROPERTY_MRPPRODUCTIONRUNLINELIST = "mRPProductionRunLineList";
    public static final String PROPERTY_MRPPURCHASINGRUNLIST = "mRPPurchasingRunList";
    public static final String PROPERTY_MRPPURCHASINGRUNLINELIST = "mRPPurchasingRunLineList";
    public static final String PROPERTY_MRPSALESFORECASTLINELIST = "mRPSalesForecastLineList";
    public static final String PROPERTY_MANUFACTURINGCASELIST = "manufacturingCaseList";
    public static final String PROPERTY_MANUFACTURINGGLOBALUSELIST = "manufacturingGlobalUseList";
    public static final String PROPERTY_MANUFACTURINGOPERATIONPRODUCTLIST = "manufacturingOperationProductList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONLINELIST = "manufacturingProductionLineList";
    public static final String PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST = "manufacturingWorkRequirementProductList";
    public static final String PROPERTY_MATERIALMGMTCOSTINGLIST = "materialMgmtCostingList";
    public static final String PROPERTY_MATERIALMGMTINTERNALCONSUMPTIONLINELIST = "materialMgmtInternalConsumptionLineList";
    public static final String PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINELIST = "materialMgmtInternalMovementLineList";
    public static final String PROPERTY_MATERIALMGMTINVENTORYCOUNTLINELIST = "materialMgmtInventoryCountLineList";
    public static final String PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST = "materialMgmtMaterialTransactionList";
    public static final String PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST = "materialMgmtProductionPlanList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONLIST = "materialMgmtReservationList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST = "materialMgmtShipmentInOutLineList";
    public static final String PROPERTY_MATERIALMGMTSTORAGEDETAILLIST = "materialMgmtStorageDetailList";
    public static final String PROPERTY_MATERIALMGMTSTORAGEPENDINGLIST = "materialMgmtStoragePendingList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST = "orderLineAccountingDimensionList";
    public static final String PROPERTY_PRICINGADJUSTMENTPRODUCTLIST = "pricingAdjustmentProductList";
    public static final String PROPERTY_PRICINGDISCOUNTLIST = "pricingDiscountList";
    public static final String PROPERTY_PRICINGPRICELISTSCHEMELINELIST = "pricingPriceListSchemeLineList";
    public static final String PROPERTY_PRICINGPRODUCTPRICELIST = "pricingProductPriceList";
    public static final String PROPERTY_PRICINGVOLUMEDISCOUNTPRODUCTLIST = "pricingVolumeDiscountProductList";
    public static final String PROPERTY_PROCUREMENTPOINVOICEMATCHLIST = "procurementPOInvoiceMatchList";
    public static final String PROPERTY_PROCUREMENTRECEIPTINVOICEMATCHLIST = "procurementReceiptInvoiceMatchList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLINELIST = "procurementRequisitionLineList";
    public static final String PROPERTY_PRODUCTACCOUNTSLIST = "productAccountsList";
    public static final String PROPERTY_PRODUCTBOMLIST = "productBOMList";
    public static final String PROPERTY_PRODUCTBOMBOMPRODUCTLIST = "productBOMBOMProductList";
    public static final String PROPERTY_PRODUCTBYPRICEANDWAREHOUSELIST = "productByPriceAndWarehouseList";
    public static final String PROPERTY_PRODUCTCUSTOMERLIST = "productCustomerList";
    public static final String PROPERTY_PRODUCTORGLIST = "productOrgList";
    public static final String PROPERTY_PRODUCTSTOCKVIEWLIST = "productStockViewList";
    public static final String PROPERTY_PRODUCTSUBSTITUTELIST = "productSubstituteList";
    public static final String PROPERTY_PRODUCTSUBSTITUTESUBSTITUTEPRODUCTLIST = "productSubstituteSubstituteProductList";
    public static final String PROPERTY_PRODUCTTRLLIST = "productTrlList";
    public static final String PROPERTY_PRODUCTUOMLIST = "productUOMList";
    public static final String PROPERTY_PROJECTISSUELIST = "projectIssueList";
    public static final String PROPERTY_PROJECTLINELIST = "projectLineList";
    public static final String PROPERTY_PROJECTPHASELIST = "projectPhaseList";
    public static final String PROPERTY_PROJECTPROPOSALLINELIST = "projectProposalLineList";
    public static final String PROPERTY_PROJECTSTANDARDPHASELIST = "projectStandardPhaseList";
    public static final String PROPERTY_PROJECTSTANDARDTASKLIST = "projectStandardTaskList";
    public static final String PROPERTY_PROJECTTASKLIST = "projectTaskList";
    public static final String PROPERTY_RCGIDIFIFOLINESLIST = "rCGIDiFifoLinesList";
    public static final String PROPERTY_RCGIDILINESLIST = "rCGIDiLinesList";
    public static final String PROPERTY_RCGIDRLINESLIST = "rCGIDrLinesList";
    public static final String PROPERTY_RCGIGOODSISSUELINELIST = "rCGIGoodsIssueLineList";
    public static final String PROPERTY_RCGIMILINESLIST = "rCGIMiLinesList";
    public static final String PROPERTY_RCGIMRLINESLIST = "rCGIMrLinesList";
    public static final String PROPERTY_RCGIPURCHASEPRICELIST = "rCGIPurchasePriceList";
    public static final String PROPERTY_RCGITRANSACTIONSLIST = "rCGITransactionsList";
    public static final String PROPERTY_RCHRQUALITYSTANDARDLIST = "rCHRQualitystandardList";
    public static final String PROPERTY_RCHRQUALITYSTANDARDWEFTPRODUCTLIST = "rCHRQualitystandardWeftProductList";
    public static final String PROPERTY_RCOBPRLINESLIST = "rCOBPrlinesList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST = "rCOBPurchasequotationlinesList";
    public static final String PROPERTY_RCPRCONSUMABLESLIST = "rCPRConsumablesList";
    public static final String PROPERTY_RCPRMACHINECATEGORYLIST = "rCPRMachineCategoryList";
    public static final String PROPERTY_RCPRPRBOMLIST = "rCPRPrBOMList";
    public static final String PROPERTY_RCPRPRBOMLINESLIST = "rCPRPrBOMLinesList";
    public static final String PROPERTY_RCPRPRPRODUCTLIST = "rCPRPrProductList";
    public static final String PROPERTY_RCPRPRODUCTIONRUNLIST = "rCPRProductionRunList";
    public static final String PROPERTY_RCOBSTORAGEBINSLIST = "rcobStoragebinsList";
    public static final String PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST = "returnMaterialOrderPickEditLinesList";
    public static final String PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST = "returnMaterialReceiptPickEditList";
    public static final String PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST = "returnMaterialShipmentPickEditList";
    public static final String PROPERTY_SALESCOMMISSIONLIST = "salesCommissionList";
    public static final String PROPERTY_SALESCOMMISSIONLINELIST = "salesCommissionLineList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINELIST = "timeAndExpenseSheetLineList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINEVLIST = "timeAndExpenseSheetLineVList";
    public static final String PROPERTY_TRANSACTIONVLIST = "transactionVList";
    public static final String PROPERTY_EPCGPPCENQUIRYLIST = "epcgPpcenquiryList";

    public Product() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_STOCKED, true);
        setDefaultValue(PROPERTY_PURCHASE, true);
        setDefaultValue(PROPERTY_SALE, true);
        setDefaultValue(PROPERTY_BILLOFMATERIALS, false);
        setDefaultValue(PROPERTY_PRINTDETAILSONINVOICE, false);
        setDefaultValue(PROPERTY_PRINTDETAILSONPICKLIST, false);
        setDefaultValue(PROPERTY_BOMVERIFIED, false);
        setDefaultValue(PROPERTY_WEIGHT, new BigDecimal(1));
        setDefaultValue(PROPERTY_DISCONTINUED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PRODUCTTYPE, "I");
        setDefaultValue(PROPERTY_PRINTPRICE, true);
        setDefaultValue(PROPERTY_ENFORCEATTRIBUTE, false);
        setDefaultValue(PROPERTY_CALCULATED, false);
        setDefaultValue(PROPERTY_PRODUCTION, false);
        setDefaultValue(PROPERTY_QUANTITYTYPE, false);
        setDefaultValue(PROPERTY_ISQUANTITYVARIABLE, false);
        setDefaultValue(PROPERTY_DEFERREDREVENUE, false);
        setDefaultValue(PROPERTY_ISDEFERREDEXPENSE, false);
        setDefaultValue(PROPERTY_BOOKUSINGPURCHASEORDERPRICE, false);
        setDefaultValue(PROPERTY_RCLOISLOT, false);
        setDefaultValue(PROPERTY_RCGIREMQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCGIAVGCP, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCGICURRENTSP, new BigDecimal(0));
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APPROVEDVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRODUCTFORFREIGHTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COSTINGRULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COSTINGRULEPRODUCTVAPPLYPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COSTINGRULEPRODUCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGBOOKMNGMNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGBOOKUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYWEFTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGMACSPARELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGMOFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPROFORMALINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWARPCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWEFTCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINACCTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LOTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPSALESFORECASTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGCASELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGGLOBALUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINTERNALCONSUMPTIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINVENTORYCOUNTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSTORAGEDETAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSTORAGEPENDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGDISCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGPRICELISTSCHEMELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGPRODUCTPRICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGVOLUMEDISCOUNTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTPOINVOICEMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTRECEIPTINVOICEMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTBOMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTBOMBOMPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTBYPRICEANDWAREHOUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCUSTOMERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTORGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTSTOCKVIEWLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTSUBSTITUTELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTSUBSTITUTESUBSTITUTEPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTUOMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPHASELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPROPOSALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTSTANDARDPHASELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTSTANDARDTASKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTTASKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDIFIFOLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIGOODSISSUELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIPURCHASEPRICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGITRANSACTIONSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRQUALITYSTANDARDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRQUALITYSTANDARDWEFTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCONSUMABLESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRMACHINECATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRBOMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRBOMLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRODUCTIONRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBSTORAGEBINSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPPCENQUIRYLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
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

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public String getHelpComment() {
        return (String) get(PROPERTY_HELPCOMMENT);
    }

    public void setHelpComment(String helpComment) {
        set(PROPERTY_HELPCOMMENT, helpComment);
    }

    public String getUPCEAN() {
        return (String) get(PROPERTY_UPCEAN);
    }

    public void setUPCEAN(String uPCEAN) {
        set(PROPERTY_UPCEAN, uPCEAN);
    }

    public String getSKU() {
        return (String) get(PROPERTY_SKU);
    }

    public void setSKU(String sKU) {
        set(PROPERTY_SKU, sKU);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public User getSalesRepresentative() {
        return (User) get(PROPERTY_SALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(User salesRepresentative) {
        set(PROPERTY_SALESREPRESENTATIVE, salesRepresentative);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public Boolean isStocked() {
        return (Boolean) get(PROPERTY_STOCKED);
    }

    public void setStocked(Boolean stocked) {
        set(PROPERTY_STOCKED, stocked);
    }

    public Boolean isPurchase() {
        return (Boolean) get(PROPERTY_PURCHASE);
    }

    public void setPurchase(Boolean purchase) {
        set(PROPERTY_PURCHASE, purchase);
    }

    public Boolean isSale() {
        return (Boolean) get(PROPERTY_SALE);
    }

    public void setSale(Boolean sale) {
        set(PROPERTY_SALE, sale);
    }

    public Boolean isBillOfMaterials() {
        return (Boolean) get(PROPERTY_BILLOFMATERIALS);
    }

    public void setBillOfMaterials(Boolean billOfMaterials) {
        set(PROPERTY_BILLOFMATERIALS, billOfMaterials);
    }

    public Boolean isPrintDetailsOnInvoice() {
        return (Boolean) get(PROPERTY_PRINTDETAILSONINVOICE);
    }

    public void setPrintDetailsOnInvoice(Boolean printDetailsOnInvoice) {
        set(PROPERTY_PRINTDETAILSONINVOICE, printDetailsOnInvoice);
    }

    public Boolean isPrintDetailsOnPickList() {
        return (Boolean) get(PROPERTY_PRINTDETAILSONPICKLIST);
    }

    public void setPrintDetailsOnPickList(Boolean printDetailsOnPickList) {
        set(PROPERTY_PRINTDETAILSONPICKLIST, printDetailsOnPickList);
    }

    public Boolean isBOMVerified() {
        return (Boolean) get(PROPERTY_BOMVERIFIED);
    }

    public void setBOMVerified(Boolean bOMVerified) {
        set(PROPERTY_BOMVERIFIED, bOMVerified);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public String getClassification() {
        return (String) get(PROPERTY_CLASSIFICATION);
    }

    public void setClassification(String classification) {
        set(PROPERTY_CLASSIFICATION, classification);
    }

    public BigDecimal getVolume() {
        return (BigDecimal) get(PROPERTY_VOLUME);
    }

    public void setVolume(BigDecimal volume) {
        set(PROPERTY_VOLUME, volume);
    }

    public BigDecimal getWeight() {
        return (BigDecimal) get(PROPERTY_WEIGHT);
    }

    public void setWeight(BigDecimal weight) {
        set(PROPERTY_WEIGHT, weight);
    }

    public BigDecimal getShelfWidth() {
        return (BigDecimal) get(PROPERTY_SHELFWIDTH);
    }

    public void setShelfWidth(BigDecimal shelfWidth) {
        set(PROPERTY_SHELFWIDTH, shelfWidth);
    }

    public BigDecimal getShelfHeight() {
        return (BigDecimal) get(PROPERTY_SHELFHEIGHT);
    }

    public void setShelfHeight(BigDecimal shelfHeight) {
        set(PROPERTY_SHELFHEIGHT, shelfHeight);
    }

    public BigDecimal getShelfDepth() {
        return (BigDecimal) get(PROPERTY_SHELFDEPTH);
    }

    public void setShelfDepth(BigDecimal shelfDepth) {
        set(PROPERTY_SHELFDEPTH, shelfDepth);
    }

    public Long getUnitsPerPallet() {
        return (Long) get(PROPERTY_UNITSPERPALLET);
    }

    public void setUnitsPerPallet(Long unitsPerPallet) {
        set(PROPERTY_UNITSPERPALLET, unitsPerPallet);
    }

    public TaxCategory getTaxCategory() {
        return (TaxCategory) get(PROPERTY_TAXCATEGORY);
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        set(PROPERTY_TAXCATEGORY, taxCategory);
    }

    public Resource getResource() {
        return (Resource) get(PROPERTY_RESOURCE);
    }

    public void setResource(Resource resource) {
        set(PROPERTY_RESOURCE, resource);
    }

    public Boolean isDiscontinued() {
        return (Boolean) get(PROPERTY_DISCONTINUED);
    }

    public void setDiscontinued(Boolean discontinued) {
        set(PROPERTY_DISCONTINUED, discontinued);
    }

    public Date getDiscontinuedBy() {
        return (Date) get(PROPERTY_DISCONTINUEDBY);
    }

    public void setDiscontinuedBy(Date discontinuedBy) {
        set(PROPERTY_DISCONTINUEDBY, discontinuedBy);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public ExpenseType getExpenseType() {
        return (ExpenseType) get(PROPERTY_EXPENSETYPE);
    }

    public void setExpenseType(ExpenseType expenseType) {
        set(PROPERTY_EXPENSETYPE, expenseType);
    }

    public String getProductType() {
        return (String) get(PROPERTY_PRODUCTTYPE);
    }

    public void setProductType(String productType) {
        set(PROPERTY_PRODUCTTYPE, productType);
    }

    public String getImageURL() {
        return (String) get(PROPERTY_IMAGEURL);
    }

    public void setImageURL(String imageURL) {
        set(PROPERTY_IMAGEURL, imageURL);
    }

    public String getDescriptionURL() {
        return (String) get(PROPERTY_DESCRIPTIONURL);
    }

    public void setDescriptionURL(String descriptionURL) {
        set(PROPERTY_DESCRIPTIONURL, descriptionURL);
    }

    public Long getGuaranteedDays() {
        return (Long) get(PROPERTY_GUARANTEEDDAYS);
    }

    public void setGuaranteedDays(Long guaranteedDays) {
        set(PROPERTY_GUARANTEEDDAYS, guaranteedDays);
    }

    public String getVersionNo() {
        return (String) get(PROPERTY_VERSIONNO);
    }

    public void setVersionNo(String versionNo) {
        set(PROPERTY_VERSIONNO, versionNo);
    }

    public AttributeSet getAttributeSet() {
        return (AttributeSet) get(PROPERTY_ATTRIBUTESET);
    }

    public void setAttributeSet(AttributeSet attributeSet) {
        set(PROPERTY_ATTRIBUTESET, attributeSet);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public String getDownloadURL() {
        return (String) get(PROPERTY_DOWNLOADURL);
    }

    public void setDownloadURL(String downloadURL) {
        set(PROPERTY_DOWNLOADURL, downloadURL);
    }

    public FreightCategory getFreightCategory() {
        return (FreightCategory) get(PROPERTY_FREIGHTCATEGORY);
    }

    public void setFreightCategory(FreightCategory freightCategory) {
        set(PROPERTY_FREIGHTCATEGORY, freightCategory);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Boolean isPrintPrice() {
        return (Boolean) get(PROPERTY_PRINTPRICE);
    }

    public void setPrintPrice(Boolean printPrice) {
        set(PROPERTY_PRINTPRICE, printPrice);
    }

    public String getName2() {
        return (String) get(PROPERTY_NAME2);
    }

    public void setName2(String name2) {
        set(PROPERTY_NAME2, name2);
    }

    public String getCostType() {
        return (String) get(PROPERTY_COSTTYPE);
    }

    public void setCostType(String costType) {
        set(PROPERTY_COSTTYPE, costType);
    }

    public BigDecimal getStandardCost() {
        return (BigDecimal) get(PROPERTY_STANDARDCOST);
    }

    public void setStandardCost(BigDecimal standardCost) {
        set(PROPERTY_STANDARDCOST, standardCost);
    }

    public BigDecimal getMinimumStock() {
        return (BigDecimal) get(PROPERTY_MINIMUMSTOCK);
    }

    public void setMinimumStock(BigDecimal minimumStock) {
        set(PROPERTY_MINIMUMSTOCK, minimumStock);
    }

    public Boolean isEnforceAttribute() {
        return (Boolean) get(PROPERTY_ENFORCEATTRIBUTE);
    }

    public void setEnforceAttribute(Boolean enforceAttribute) {
        set(PROPERTY_ENFORCEATTRIBUTE, enforceAttribute);
    }

    public Boolean isCalculated() {
        return (Boolean) get(PROPERTY_CALCULATED);
    }

    public void setCalculated(Boolean calculated) {
        set(PROPERTY_CALCULATED, calculated);
    }

    public ProcessPlan getProcessPlan() {
        return (ProcessPlan) get(PROPERTY_PROCESSPLAN);
    }

    public void setProcessPlan(ProcessPlan processPlan) {
        set(PROPERTY_PROCESSPLAN, processPlan);
    }

    public Boolean isProduction() {
        return (Boolean) get(PROPERTY_PRODUCTION);
    }

    public void setProduction(Boolean production) {
        set(PROPERTY_PRODUCTION, production);
    }

    public BigDecimal getCapacity() {
        return (BigDecimal) get(PROPERTY_CAPACITY);
    }

    public void setCapacity(BigDecimal capacity) {
        set(PROPERTY_CAPACITY, capacity);
    }

    public BigDecimal getMinimumLeadTime() {
        return (BigDecimal) get(PROPERTY_MINIMUMLEADTIME);
    }

    public void setMinimumLeadTime(BigDecimal minimumLeadTime) {
        set(PROPERTY_MINIMUMLEADTIME, minimumLeadTime);
    }

    public Planner getPlanner() {
        return (Planner) get(PROPERTY_PLANNER);
    }

    public void setPlanner(Planner planner) {
        set(PROPERTY_PLANNER, planner);
    }

    public PlanningMethod getPlanningMethod() {
        return (PlanningMethod) get(PROPERTY_PLANNINGMETHOD);
    }

    public void setPlanningMethod(PlanningMethod planningMethod) {
        set(PROPERTY_PLANNINGMETHOD, planningMethod);
    }

    public BigDecimal getMaxQuantity() {
        return (BigDecimal) get(PROPERTY_MAXQUANTITY);
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        set(PROPERTY_MAXQUANTITY, maxQuantity);
    }

    public BigDecimal getMinQuantity() {
        return (BigDecimal) get(PROPERTY_MINQUANTITY);
    }

    public void setMinQuantity(BigDecimal minQuantity) {
        set(PROPERTY_MINQUANTITY, minQuantity);
    }

    public BigDecimal getStandardQuantity() {
        return (BigDecimal) get(PROPERTY_STANDARDQUANTITY);
    }

    public void setStandardQuantity(BigDecimal standardQuantity) {
        set(PROPERTY_STANDARDQUANTITY, standardQuantity);
    }

    public Boolean isQuantityType() {
        return (Boolean) get(PROPERTY_QUANTITYTYPE);
    }

    public void setQuantityType(Boolean quantityType) {
        set(PROPERTY_QUANTITYTYPE, quantityType);
    }

    public BigDecimal getSafetyStock() {
        return (BigDecimal) get(PROPERTY_SAFETYSTOCK);
    }

    public void setSafetyStock(BigDecimal safetyStock) {
        set(PROPERTY_SAFETYSTOCK, safetyStock);
    }

    public String getUseAttributeSetValueAs() {
        return (String) get(PROPERTY_USEATTRIBUTESETVALUEAS);
    }

    public void setUseAttributeSetValueAs(String useAttributeSetValueAs) {
        set(PROPERTY_USEATTRIBUTESETVALUEAS, useAttributeSetValueAs);
    }

    public Boolean isQuantityvariable() {
        return (Boolean) get(PROPERTY_ISQUANTITYVARIABLE);
    }

    public void setQuantityvariable(Boolean isquantityvariable) {
        set(PROPERTY_ISQUANTITYVARIABLE, isquantityvariable);
    }

    public Boolean isDeferredRevenue() {
        return (Boolean) get(PROPERTY_DEFERREDREVENUE);
    }

    public void setDeferredRevenue(Boolean deferredRevenue) {
        set(PROPERTY_DEFERREDREVENUE, deferredRevenue);
    }

    public String getRevenuePlanType() {
        return (String) get(PROPERTY_REVENUEPLANTYPE);
    }

    public void setRevenuePlanType(String revenuePlanType) {
        set(PROPERTY_REVENUEPLANTYPE, revenuePlanType);
    }

    public Long getPeriodNumber() {
        return (Long) get(PROPERTY_PERIODNUMBER);
    }

    public void setPeriodNumber(Long periodNumber) {
        set(PROPERTY_PERIODNUMBER, periodNumber);
    }

    public Boolean isDeferredexpense() {
        return (Boolean) get(PROPERTY_ISDEFERREDEXPENSE);
    }

    public void setDeferredexpense(Boolean isdeferredexpense) {
        set(PROPERTY_ISDEFERREDEXPENSE, isdeferredexpense);
    }

    public String getExpplantype() {
        return (String) get(PROPERTY_EXPPLANTYPE);
    }

    public void setExpplantype(String expplantype) {
        set(PROPERTY_EXPPLANTYPE, expplantype);
    }

    public Long getPeriodnumberExp() {
        return (Long) get(PROPERTY_PERIODNUMBEREXP);
    }

    public void setPeriodnumberExp(Long periodnumberExp) {
        set(PROPERTY_PERIODNUMBEREXP, periodnumberExp);
    }

    public String getDefaultPeriod() {
        return (String) get(PROPERTY_DEFAULTPERIOD);
    }

    public void setDefaultPeriod(String defaultPeriod) {
        set(PROPERTY_DEFAULTPERIOD, defaultPeriod);
    }

    public String getDefaultPeriodExpense() {
        return (String) get(PROPERTY_DEFAULTPERIODEXPENSE);
    }

    public void setDefaultPeriodExpense(String defaultPeriodExpense) {
        set(PROPERTY_DEFAULTPERIODEXPENSE, defaultPeriodExpense);
    }

    public Boolean isBookUsingPurchaseOrderPrice() {
        return (Boolean) get(PROPERTY_BOOKUSINGPURCHASEORDERPRICE);
    }

    public void setBookUsingPurchaseOrderPrice(Boolean bookUsingPurchaseOrderPrice) {
        set(PROPERTY_BOOKUSINGPURCHASEORDERPRICE, bookUsingPurchaseOrderPrice);
    }

    public UOM getUOMForWeight() {
        return (UOM) get(PROPERTY_UOMFORWEIGHT);
    }

    public void setUOMForWeight(UOM uOMForWeight) {
        set(PROPERTY_UOMFORWEIGHT, uOMForWeight);
    }

    public Boolean isRcloIslot() {
        return (Boolean) get(PROPERTY_RCLOISLOT);
    }

    public void setRcloIslot(Boolean rcloIslot) {
        set(PROPERTY_RCLOISLOT, rcloIslot);
    }

    public String getEpcgMake() {
        return (String) get(PROPERTY_EPCGMAKE);
    }

    public void setEpcgMake(String epcgMake) {
        set(PROPERTY_EPCGMAKE, epcgMake);
    }

    public Boolean isRcgiDeptproduct() {
        return (Boolean) get(PROPERTY_RCGIDEPTPRODUCT);
    }

    public void setRcgiDeptproduct(Boolean rcgiDeptproduct) {
        set(PROPERTY_RCGIDEPTPRODUCT, rcgiDeptproduct);
    }

    public String getRcgiKsmlbarcode() {
        return (String) get(PROPERTY_RCGIKSMLBARCODE);
    }

    public void setRcgiKsmlbarcode(String rcgiKsmlbarcode) {
        set(PROPERTY_RCGIKSMLBARCODE, rcgiKsmlbarcode);
    }

    public BigDecimal getRcgiRemqty() {
        return (BigDecimal) get(PROPERTY_RCGIREMQTY);
    }

    public void setRcgiRemqty(BigDecimal rcgiRemqty) {
        set(PROPERTY_RCGIREMQTY, rcgiRemqty);
    }

    public BigDecimal getRcgiAvgcp() {
        return (BigDecimal) get(PROPERTY_RCGIAVGCP);
    }

    public void setRcgiAvgcp(BigDecimal rcgiAvgcp) {
        set(PROPERTY_RCGIAVGCP, rcgiAvgcp);
    }

    public BigDecimal getRcgiCurrentsp() {
        return (BigDecimal) get(PROPERTY_RCGICURRENTSP);
    }

    public void setRcgiCurrentsp(BigDecimal rcgiCurrentsp) {
        set(PROPERTY_RCGICURRENTSP, rcgiCurrentsp);
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
    public List<AmortizationLineAccountingDimension> getAmortizationLineAccountingDimensionList() {
        return (List<AmortizationLineAccountingDimension>) get(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setAmortizationLineAccountingDimensionList(List<AmortizationLineAccountingDimension> amortizationLineAccountingDimensionList) {
        set(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST, amortizationLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<ApprovedVendor> getApprovedVendorList() {
        return (List<ApprovedVendor>) get(PROPERTY_APPROVEDVENDORLIST);
    }

    public void setApprovedVendorList(List<ApprovedVendor> approvedVendorList) {
        set(PROPERTY_APPROVEDVENDORLIST, approvedVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductTemplate> getBusinessPartnerProductTemplateList() {
        return (List<ProductTemplate>) get(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST);
    }

    public void setBusinessPartnerProductTemplateList(List<ProductTemplate> businessPartnerProductTemplateList) {
        set(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST, businessPartnerProductTemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationProductForFreightList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRODUCTFORFREIGHTLIST);
    }

    public void setClientInformationProductForFreightList(List<ClientInformation> clientInformationProductForFreightList) {
        set(PROPERTY_CLIENTINFORMATIONPRODUCTFORFREIGHTLIST, clientInformationProductForFreightList);
    }

    @SuppressWarnings("unchecked")
    public List<CostingRule> getCostingRuleList() {
        return (List<CostingRule>) get(PROPERTY_COSTINGRULELIST);
    }

    public void setCostingRuleList(List<CostingRule> costingRuleList) {
        set(PROPERTY_COSTINGRULELIST, costingRuleList);
    }

    @SuppressWarnings("unchecked")
    public List<CostingRuleProductV> getCostingRuleProductVApplyProductList() {
        return (List<CostingRuleProductV>) get(PROPERTY_COSTINGRULEPRODUCTVAPPLYPRODUCTLIST);
    }

    public void setCostingRuleProductVApplyProductList(List<CostingRuleProductV> costingRuleProductVApplyProductList) {
        set(PROPERTY_COSTINGRULEPRODUCTVAPPLYPRODUCTLIST, costingRuleProductVApplyProductList);
    }

    @SuppressWarnings("unchecked")
    public List<CostingRuleProductV> getCostingRuleProductVList() {
        return (List<CostingRuleProductV>) get(PROPERTY_COSTINGRULEPRODUCTVLIST);
    }

    public void setCostingRuleProductVList(List<CostingRuleProductV> costingRuleProductVList) {
        set(PROPERTY_COSTINGRULEPRODUCTVLIST, costingRuleProductVList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGBookmngmnt> getEPCGBookmngmntList() {
        return (List<EPCGBookmngmnt>) get(PROPERTY_EPCGBOOKMNGMNTLIST);
    }

    public void setEPCGBookmngmntList(List<EPCGBookmngmnt> ePCGBookmngmntList) {
        set(PROPERTY_EPCGBOOKMNGMNTLIST, ePCGBookmngmntList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCG_Bookuser> getEPCGBookuserList() {
        return (List<EPCG_Bookuser>) get(PROPERTY_EPCGBOOKUSERLIST);
    }

    public void setEPCGBookuserList(List<EPCG_Bookuser> ePCGBookuserList) {
        set(PROPERTY_EPCGBOOKUSERLIST, ePCGBookuserList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryWeftproductList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYWEFTPRODUCTLIST);
    }

    public void setEpcgCostenquiryWeftproductList(List<EpcgCostenquiry> epcgCostenquiryWeftproductList) {
        set(PROPERTY_EPCGCOSTENQUIRYWEFTPRODUCTLIST, epcgCostenquiryWeftproductList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgMacSpare> getEpcgMacSpareList() {
        return (List<EpcgMacSpare>) get(PROPERTY_EPCGMACSPARELIST);
    }

    public void setEpcgMacSpareList(List<EpcgMacSpare> epcgMacSpareList) {
        set(PROPERTY_EPCGMACSPARELIST, epcgMacSpareList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgMof> getEpcgMofList() {
        return (List<EpcgMof>) get(PROPERTY_EPCGMOFLIST);
    }

    public void setEpcgMofList(List<EpcgMof> epcgMofList) {
        set(PROPERTY_EPCGMOFLIST, epcgMofList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgProformaline> getEpcgProformalineList() {
        return (List<EpcgProformaline>) get(PROPERTY_EPCGPROFORMALINELIST);
    }

    public void setEpcgProformalineList(List<EpcgProformaline> epcgProformalineList) {
        set(PROPERTY_EPCGPROFORMALINELIST, epcgProformalineList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgWarpcosting> getEpcgWarpcostingList() {
        return (List<EpcgWarpcosting>) get(PROPERTY_EPCGWARPCOSTINGLIST);
    }

    public void setEpcgWarpcostingList(List<EpcgWarpcosting> epcgWarpcostingList) {
        set(PROPERTY_EPCGWARPCOSTINGLIST, epcgWarpcostingList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgWeftcosting> getEpcgWeftcostingList() {
        return (List<EpcgWeftcosting>) get(PROPERTY_EPCGWEFTCOSTINGLIST);
    }

    public void setEpcgWeftcostingList(List<EpcgWeftcosting> epcgWeftcostingList) {
        set(PROPERTY_EPCGWEFTCOSTINGLIST, epcgWeftcostingList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgYarncosttemplatelines> getEpcgYarncosttemplatelinesList() {
        return (List<EpcgYarncosttemplatelines>) get(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST);
    }

    public void setEpcgYarncosttemplatelinesList(List<EpcgYarncosttemplatelines> epcgYarncosttemplatelinesList) {
        set(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST, epcgYarncosttemplatelinesList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOSProduct> getExternalPOSProductList() {
        return (List<ExternalPOSProduct>) get(PROPERTY_EXTERNALPOSPRODUCTLIST);
    }

    public void setExternalPOSProductList(List<ExternalPOSProduct> externalPOSProductList) {
        set(PROPERTY_EXTERNALPOSPRODUCTLIST, externalPOSProductList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebt> getFINDoubtfulDebtList() {
        return (List<DoubtfulDebt>) get(PROPERTY_FINDOUBTFULDEBTLIST);
    }

    public void setFINDoubtfulDebtList(List<DoubtfulDebt> fINDoubtfulDebtList) {
        set(PROPERTY_FINDOUBTFULDEBTLIST, fINDoubtfulDebtList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinaccTransaction> getFINFinaccTransactionList() {
        return (List<FIN_FinaccTransaction>) get(PROPERTY_FINFINACCTRANSACTIONLIST);
    }

    public void setFINFinaccTransactionList(List<FIN_FinaccTransaction> fINFinaccTransactionList) {
        set(PROPERTY_FINFINACCTRANSACTIONLIST, fINFinaccTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentDetailV> getFINPaymentDetailVList() {
        return (List<FIN_PaymentDetailV>) get(PROPERTY_FINPAYMENTDETAILVLIST);
    }

    public void setFINPaymentDetailVList(List<FIN_PaymentDetailV> fINPaymentDetailVList) {
        set(PROPERTY_FINPAYMENTDETAILVLIST, fINPaymentDetailVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentScheduleDetail> getFINPaymentScheduleDetailList() {
        return (List<FIN_PaymentScheduleDetail>) get(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST);
    }

    public void setFINPaymentScheduleDetailList(List<FIN_PaymentScheduleDetail> fINPaymentScheduleDetailList) {
        set(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST, fINPaymentScheduleDetailList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationList() {
        return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST);
    }

    public void setFinancialMgmtAccountingCombinationList(List<AccountingCombination> financialMgmtAccountingCombinationList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST, financialMgmtAccountingCombinationList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaElement> getFinancialMgmtAcctSchemaElementList() {
        return (List<AcctSchemaElement>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaElementList(List<AcctSchemaElement> financialMgmtAcctSchemaElementList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, financialMgmtAcctSchemaElementList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
        return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getFinancialMgmtBudgetLineList() {
        return (List<BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getFinancialMgmtGLJournalList() {
        return (List<GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
        return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InOutLineAccountingDimension> getInOutLineAccountingDimensionList() {
        return (List<InOutLineAccountingDimension>) get(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInOutLineAccountingDimensionList(List<InOutLineAccountingDimension> inOutLineAccountingDimensionList) {
        set(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, inOutLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineAccountingDimension> getInvoiceLineAccountingDimensionList() {
        return (List<InvoiceLineAccountingDimension>) get(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInvoiceLineAccountingDimensionList(List<InvoiceLineAccountingDimension> invoiceLineAccountingDimensionList) {
        set(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, invoiceLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineV2> getInvoiceLineV2List() {
        return (List<InvoiceLineV2>) get(PROPERTY_INVOICELINEV2LIST);
    }

    public void setInvoiceLineV2List(List<InvoiceLineV2> invoiceLineV2List) {
        set(PROPERTY_INVOICELINEV2LIST, invoiceLineV2List);
    }

    @SuppressWarnings("unchecked")
    public List<Lot> getLotList() {
        return (List<Lot>) get(PROPERTY_LOTLIST);
    }

    public void setLotList(List<Lot> lotList) {
        set(PROPERTY_LOTLIST, lotList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRun> getMRPProductionRunList() {
        return (List<ProductionRun>) get(PROPERTY_MRPPRODUCTIONRUNLIST);
    }

    public void setMRPProductionRunList(List<ProductionRun> mRPProductionRunList) {
        set(PROPERTY_MRPPRODUCTIONRUNLIST, mRPProductionRunList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunLine> getMRPProductionRunLineList() {
        return (List<ProductionRunLine>) get(PROPERTY_MRPPRODUCTIONRUNLINELIST);
    }

    public void setMRPProductionRunLineList(List<ProductionRunLine> mRPProductionRunLineList) {
        set(PROPERTY_MRPPRODUCTIONRUNLINELIST, mRPProductionRunLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRun> getMRPPurchasingRunList() {
        return (List<PurchasingRun>) get(PROPERTY_MRPPURCHASINGRUNLIST);
    }

    public void setMRPPurchasingRunList(List<PurchasingRun> mRPPurchasingRunList) {
        set(PROPERTY_MRPPURCHASINGRUNLIST, mRPPurchasingRunList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRunLine> getMRPPurchasingRunLineList() {
        return (List<PurchasingRunLine>) get(PROPERTY_MRPPURCHASINGRUNLINELIST);
    }

    public void setMRPPurchasingRunLineList(List<PurchasingRunLine> mRPPurchasingRunLineList) {
        set(PROPERTY_MRPPURCHASINGRUNLINELIST, mRPPurchasingRunLineList);
    }

    @SuppressWarnings("unchecked")
    public List<SalesForecastLine> getMRPSalesForecastLineList() {
        return (List<SalesForecastLine>) get(PROPERTY_MRPSALESFORECASTLINELIST);
    }

    public void setMRPSalesForecastLineList(List<SalesForecastLine> mRPSalesForecastLineList) {
        set(PROPERTY_MRPSALESFORECASTLINELIST, mRPSalesForecastLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Case> getManufacturingCaseList() {
        return (List<Case>) get(PROPERTY_MANUFACTURINGCASELIST);
    }

    public void setManufacturingCaseList(List<Case> manufacturingCaseList) {
        set(PROPERTY_MANUFACTURINGCASELIST, manufacturingCaseList);
    }

    @SuppressWarnings("unchecked")
    public List<GlobalUse> getManufacturingGlobalUseList() {
        return (List<GlobalUse>) get(PROPERTY_MANUFACTURINGGLOBALUSELIST);
    }

    public void setManufacturingGlobalUseList(List<GlobalUse> manufacturingGlobalUseList) {
        set(PROPERTY_MANUFACTURINGGLOBALUSELIST, manufacturingGlobalUseList);
    }

    @SuppressWarnings("unchecked")
    public List<OperationProduct> getManufacturingOperationProductList() {
        return (List<OperationProduct>) get(PROPERTY_MANUFACTURINGOPERATIONPRODUCTLIST);
    }

    public void setManufacturingOperationProductList(List<OperationProduct> manufacturingOperationProductList) {
        set(PROPERTY_MANUFACTURINGOPERATIONPRODUCTLIST, manufacturingOperationProductList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionLine> getManufacturingProductionLineList() {
        return (List<ProductionLine>) get(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST);
    }

    public void setManufacturingProductionLineList(List<ProductionLine> manufacturingProductionLineList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST, manufacturingProductionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkRequirementProduct> getManufacturingWorkRequirementProductList() {
        return (List<WorkRequirementProduct>) get(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST);
    }

    public void setManufacturingWorkRequirementProductList(List<WorkRequirementProduct> manufacturingWorkRequirementProductList) {
        set(PROPERTY_MANUFACTURINGWORKREQUIREMENTPRODUCTLIST, manufacturingWorkRequirementProductList);
    }

    @SuppressWarnings("unchecked")
    public List<Costing> getMaterialMgmtCostingList() {
        return (List<Costing>) get(PROPERTY_MATERIALMGMTCOSTINGLIST);
    }

    public void setMaterialMgmtCostingList(List<Costing> materialMgmtCostingList) {
        set(PROPERTY_MATERIALMGMTCOSTINGLIST, materialMgmtCostingList);
    }

    @SuppressWarnings("unchecked")
    public List<InternalConsumptionLine> getMaterialMgmtInternalConsumptionLineList() {
        return (List<InternalConsumptionLine>) get(PROPERTY_MATERIALMGMTINTERNALCONSUMPTIONLINELIST);
    }

    public void setMaterialMgmtInternalConsumptionLineList(List<InternalConsumptionLine> materialMgmtInternalConsumptionLineList) {
        set(PROPERTY_MATERIALMGMTINTERNALCONSUMPTIONLINELIST, materialMgmtInternalConsumptionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InternalMovementLine> getMaterialMgmtInternalMovementLineList() {
        return (List<InternalMovementLine>) get(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINELIST);
    }

    public void setMaterialMgmtInternalMovementLineList(List<InternalMovementLine> materialMgmtInternalMovementLineList) {
        set(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINELIST, materialMgmtInternalMovementLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InventoryCountLine> getMaterialMgmtInventoryCountLineList() {
        return (List<InventoryCountLine>) get(PROPERTY_MATERIALMGMTINVENTORYCOUNTLINELIST);
    }

    public void setMaterialMgmtInventoryCountLineList(List<InventoryCountLine> materialMgmtInventoryCountLineList) {
        set(PROPERTY_MATERIALMGMTINVENTORYCOUNTLINELIST, materialMgmtInventoryCountLineList);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransaction> getMaterialMgmtMaterialTransactionList() {
        return (List<MaterialTransaction>) get(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST);
    }

    public void setMaterialMgmtMaterialTransactionList(List<MaterialTransaction> materialMgmtMaterialTransactionList) {
        set(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, materialMgmtMaterialTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionPlan> getMaterialMgmtProductionPlanList() {
        return (List<ProductionPlan>) get(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST);
    }

    public void setMaterialMgmtProductionPlanList(List<ProductionPlan> materialMgmtProductionPlanList) {
        set(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST, materialMgmtProductionPlanList);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getMaterialMgmtReservationList() {
        return (List<Reservation>) get(PROPERTY_MATERIALMGMTRESERVATIONLIST);
    }

    public void setMaterialMgmtReservationList(List<Reservation> materialMgmtReservationList) {
        set(PROPERTY_MATERIALMGMTRESERVATIONLIST, materialMgmtReservationList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOutLine> getMaterialMgmtShipmentInOutLineList() {
        return (List<ShipmentInOutLine>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST);
    }

    public void setMaterialMgmtShipmentInOutLineList(List<ShipmentInOutLine> materialMgmtShipmentInOutLineList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, materialMgmtShipmentInOutLineList);
    }

    @SuppressWarnings("unchecked")
    public List<StorageDetail> getMaterialMgmtStorageDetailList() {
        return (List<StorageDetail>) get(PROPERTY_MATERIALMGMTSTORAGEDETAILLIST);
    }

    public void setMaterialMgmtStorageDetailList(List<StorageDetail> materialMgmtStorageDetailList) {
        set(PROPERTY_MATERIALMGMTSTORAGEDETAILLIST, materialMgmtStorageDetailList);
    }

    @SuppressWarnings("unchecked")
    public List<StoragePending> getMaterialMgmtStoragePendingList() {
        return (List<StoragePending>) get(PROPERTY_MATERIALMGMTSTORAGEPENDINGLIST);
    }

    public void setMaterialMgmtStoragePendingList(List<StoragePending> materialMgmtStoragePendingList) {
        set(PROPERTY_MATERIALMGMTSTORAGEPENDINGLIST, materialMgmtStoragePendingList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineAccountingDimension> getOrderLineAccountingDimensionList() {
        return (List<OrderLineAccountingDimension>) get(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setOrderLineAccountingDimensionList(List<OrderLineAccountingDimension> orderLineAccountingDimensionList) {
        set(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, orderLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.priceadjustment.Product> getPricingAdjustmentProductList() {
        return (List<org.openbravo.model.pricing.priceadjustment.Product>) get(PROPERTY_PRICINGADJUSTMENTPRODUCTLIST);
    }

    public void setPricingAdjustmentProductList(List<org.openbravo.model.pricing.priceadjustment.Product> pricingAdjustmentProductList) {
        set(PROPERTY_PRICINGADJUSTMENTPRODUCTLIST, pricingAdjustmentProductList);
    }

    @SuppressWarnings("unchecked")
    public List<Discount> getPricingDiscountList() {
        return (List<Discount>) get(PROPERTY_PRICINGDISCOUNTLIST);
    }

    public void setPricingDiscountList(List<Discount> pricingDiscountList) {
        set(PROPERTY_PRICINGDISCOUNTLIST, pricingDiscountList);
    }

    @SuppressWarnings("unchecked")
    public List<PriceListSchemeLine> getPricingPriceListSchemeLineList() {
        return (List<PriceListSchemeLine>) get(PROPERTY_PRICINGPRICELISTSCHEMELINELIST);
    }

    public void setPricingPriceListSchemeLineList(List<PriceListSchemeLine> pricingPriceListSchemeLineList) {
        set(PROPERTY_PRICINGPRICELISTSCHEMELINELIST, pricingPriceListSchemeLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductPrice> getPricingProductPriceList() {
        return (List<ProductPrice>) get(PROPERTY_PRICINGPRODUCTPRICELIST);
    }

    public void setPricingProductPriceList(List<ProductPrice> pricingProductPriceList) {
        set(PROPERTY_PRICINGPRODUCTPRICELIST, pricingProductPriceList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.volumediscount.Product> getPricingVolumeDiscountProductList() {
        return (List<org.openbravo.model.pricing.volumediscount.Product>) get(PROPERTY_PRICINGVOLUMEDISCOUNTPRODUCTLIST);
    }

    public void setPricingVolumeDiscountProductList(List<org.openbravo.model.pricing.volumediscount.Product> pricingVolumeDiscountProductList) {
        set(PROPERTY_PRICINGVOLUMEDISCOUNTPRODUCTLIST, pricingVolumeDiscountProductList);
    }

    @SuppressWarnings("unchecked")
    public List<POInvoiceMatch> getProcurementPOInvoiceMatchList() {
        return (List<POInvoiceMatch>) get(PROPERTY_PROCUREMENTPOINVOICEMATCHLIST);
    }

    public void setProcurementPOInvoiceMatchList(List<POInvoiceMatch> procurementPOInvoiceMatchList) {
        set(PROPERTY_PROCUREMENTPOINVOICEMATCHLIST, procurementPOInvoiceMatchList);
    }

    @SuppressWarnings("unchecked")
    public List<ReceiptInvoiceMatch> getProcurementReceiptInvoiceMatchList() {
        return (List<ReceiptInvoiceMatch>) get(PROPERTY_PROCUREMENTRECEIPTINVOICEMATCHLIST);
    }

    public void setProcurementReceiptInvoiceMatchList(List<ReceiptInvoiceMatch> procurementReceiptInvoiceMatchList) {
        set(PROPERTY_PROCUREMENTRECEIPTINVOICEMATCHLIST, procurementReceiptInvoiceMatchList);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitionLine> getProcurementRequisitionLineList() {
        return (List<RequisitionLine>) get(PROPERTY_PROCUREMENTREQUISITIONLINELIST);
    }

    public void setProcurementRequisitionLineList(List<RequisitionLine> procurementRequisitionLineList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLINELIST, procurementRequisitionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductAccounts> getProductAccountsList() {
        return (List<ProductAccounts>) get(PROPERTY_PRODUCTACCOUNTSLIST);
    }

    public void setProductAccountsList(List<ProductAccounts> productAccountsList) {
        set(PROPERTY_PRODUCTACCOUNTSLIST, productAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductBOM> getProductBOMList() {
        return (List<ProductBOM>) get(PROPERTY_PRODUCTBOMLIST);
    }

    public void setProductBOMList(List<ProductBOM> productBOMList) {
        set(PROPERTY_PRODUCTBOMLIST, productBOMList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductBOM> getProductBOMBOMProductList() {
        return (List<ProductBOM>) get(PROPERTY_PRODUCTBOMBOMPRODUCTLIST);
    }

    public void setProductBOMBOMProductList(List<ProductBOM> productBOMBOMProductList) {
        set(PROPERTY_PRODUCTBOMBOMPRODUCTLIST, productBOMBOMProductList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductByPriceAndWarehouse> getProductByPriceAndWarehouseList() {
        return (List<ProductByPriceAndWarehouse>) get(PROPERTY_PRODUCTBYPRICEANDWAREHOUSELIST);
    }

    public void setProductByPriceAndWarehouseList(List<ProductByPriceAndWarehouse> productByPriceAndWarehouseList) {
        set(PROPERTY_PRODUCTBYPRICEANDWAREHOUSELIST, productByPriceAndWarehouseList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductCustomer> getProductCustomerList() {
        return (List<ProductCustomer>) get(PROPERTY_PRODUCTCUSTOMERLIST);
    }

    public void setProductCustomerList(List<ProductCustomer> productCustomerList) {
        set(PROPERTY_PRODUCTCUSTOMERLIST, productCustomerList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductOrg> getProductOrgList() {
        return (List<ProductOrg>) get(PROPERTY_PRODUCTORGLIST);
    }

    public void setProductOrgList(List<ProductOrg> productOrgList) {
        set(PROPERTY_PRODUCTORGLIST, productOrgList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductStockView> getProductStockViewList() {
        return (List<ProductStockView>) get(PROPERTY_PRODUCTSTOCKVIEWLIST);
    }

    public void setProductStockViewList(List<ProductStockView> productStockViewList) {
        set(PROPERTY_PRODUCTSTOCKVIEWLIST, productStockViewList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductSubstitute> getProductSubstituteList() {
        return (List<ProductSubstitute>) get(PROPERTY_PRODUCTSUBSTITUTELIST);
    }

    public void setProductSubstituteList(List<ProductSubstitute> productSubstituteList) {
        set(PROPERTY_PRODUCTSUBSTITUTELIST, productSubstituteList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductSubstitute> getProductSubstituteSubstituteProductList() {
        return (List<ProductSubstitute>) get(PROPERTY_PRODUCTSUBSTITUTESUBSTITUTEPRODUCTLIST);
    }

    public void setProductSubstituteSubstituteProductList(List<ProductSubstitute> productSubstituteSubstituteProductList) {
        set(PROPERTY_PRODUCTSUBSTITUTESUBSTITUTEPRODUCTLIST, productSubstituteSubstituteProductList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductTrl> getProductTrlList() {
        return (List<ProductTrl>) get(PROPERTY_PRODUCTTRLLIST);
    }

    public void setProductTrlList(List<ProductTrl> productTrlList) {
        set(PROPERTY_PRODUCTTRLLIST, productTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductUOM> getProductUOMList() {
        return (List<ProductUOM>) get(PROPERTY_PRODUCTUOMLIST);
    }

    public void setProductUOMList(List<ProductUOM> productUOMList) {
        set(PROPERTY_PRODUCTUOMLIST, productUOMList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectIssue> getProjectIssueList() {
        return (List<ProjectIssue>) get(PROPERTY_PROJECTISSUELIST);
    }

    public void setProjectIssueList(List<ProjectIssue> projectIssueList) {
        set(PROPERTY_PROJECTISSUELIST, projectIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectLine> getProjectLineList() {
        return (List<ProjectLine>) get(PROPERTY_PROJECTLINELIST);
    }

    public void setProjectLineList(List<ProjectLine> projectLineList) {
        set(PROPERTY_PROJECTLINELIST, projectLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectPhase> getProjectPhaseList() {
        return (List<ProjectPhase>) get(PROPERTY_PROJECTPHASELIST);
    }

    public void setProjectPhaseList(List<ProjectPhase> projectPhaseList) {
        set(PROPERTY_PROJECTPHASELIST, projectPhaseList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposalLine> getProjectProposalLineList() {
        return (List<ProjectProposalLine>) get(PROPERTY_PROJECTPROPOSALLINELIST);
    }

    public void setProjectProposalLineList(List<ProjectProposalLine> projectProposalLineList) {
        set(PROPERTY_PROJECTPROPOSALLINELIST, projectProposalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<StandardPhase> getProjectStandardPhaseList() {
        return (List<StandardPhase>) get(PROPERTY_PROJECTSTANDARDPHASELIST);
    }

    public void setProjectStandardPhaseList(List<StandardPhase> projectStandardPhaseList) {
        set(PROPERTY_PROJECTSTANDARDPHASELIST, projectStandardPhaseList);
    }

    @SuppressWarnings("unchecked")
    public List<StandardTask> getProjectStandardTaskList() {
        return (List<StandardTask>) get(PROPERTY_PROJECTSTANDARDTASKLIST);
    }

    public void setProjectStandardTaskList(List<StandardTask> projectStandardTaskList) {
        set(PROPERTY_PROJECTSTANDARDTASKLIST, projectStandardTaskList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectTask> getProjectTaskList() {
        return (List<ProjectTask>) get(PROPERTY_PROJECTTASKLIST);
    }

    public void setProjectTaskList(List<ProjectTask> projectTaskList) {
        set(PROPERTY_PROJECTTASKLIST, projectTaskList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDiFifoLines> getRCGIDiFifoLinesList() {
        return (List<RCGIDiFifoLines>) get(PROPERTY_RCGIDIFIFOLINESLIST);
    }

    public void setRCGIDiFifoLinesList(List<RCGIDiFifoLines> rCGIDiFifoLinesList) {
        set(PROPERTY_RCGIDIFIFOLINESLIST, rCGIDiFifoLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDiLines> getRCGIDiLinesList() {
        return (List<RCGIDiLines>) get(PROPERTY_RCGIDILINESLIST);
    }

    public void setRCGIDiLinesList(List<RCGIDiLines> rCGIDiLinesList) {
        set(PROPERTY_RCGIDILINESLIST, rCGIDiLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDrLines> getRCGIDrLinesList() {
        return (List<RCGIDrLines>) get(PROPERTY_RCGIDRLINESLIST);
    }

    public void setRCGIDrLinesList(List<RCGIDrLines> rCGIDrLinesList) {
        set(PROPERTY_RCGIDRLINESLIST, rCGIDrLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_GoodsIssue_Line> getRCGIGoodsIssueLineList() {
        return (List<RCGI_GoodsIssue_Line>) get(PROPERTY_RCGIGOODSISSUELINELIST);
    }

    public void setRCGIGoodsIssueLineList(List<RCGI_GoodsIssue_Line> rCGIGoodsIssueLineList) {
        set(PROPERTY_RCGIGOODSISSUELINELIST, rCGIGoodsIssueLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MiLines> getRCGIMiLinesList() {
        return (List<RCGI_MiLines>) get(PROPERTY_RCGIMILINESLIST);
    }

    public void setRCGIMiLinesList(List<RCGI_MiLines> rCGIMiLinesList) {
        set(PROPERTY_RCGIMILINESLIST, rCGIMiLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MrLines> getRCGIMrLinesList() {
        return (List<RCGI_MrLines>) get(PROPERTY_RCGIMRLINESLIST);
    }

    public void setRCGIMrLinesList(List<RCGI_MrLines> rCGIMrLinesList) {
        set(PROPERTY_RCGIMRLINESLIST, rCGIMrLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIPurchasePrice> getRCGIPurchasePriceList() {
        return (List<RCGIPurchasePrice>) get(PROPERTY_RCGIPURCHASEPRICELIST);
    }

    public void setRCGIPurchasePriceList(List<RCGIPurchasePrice> rCGIPurchasePriceList) {
        set(PROPERTY_RCGIPURCHASEPRICELIST, rCGIPurchasePriceList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGITransactions> getRCGITransactionsList() {
        return (List<RCGITransactions>) get(PROPERTY_RCGITRANSACTIONSLIST);
    }

    public void setRCGITransactionsList(List<RCGITransactions> rCGITransactionsList) {
        set(PROPERTY_RCGITRANSACTIONSLIST, rCGITransactionsList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRQualitystandard> getRCHRQualitystandardList() {
        return (List<RCHRQualitystandard>) get(PROPERTY_RCHRQUALITYSTANDARDLIST);
    }

    public void setRCHRQualitystandardList(List<RCHRQualitystandard> rCHRQualitystandardList) {
        set(PROPERTY_RCHRQUALITYSTANDARDLIST, rCHRQualitystandardList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRQualitystandard> getRCHRQualitystandardWeftProductList() {
        return (List<RCHRQualitystandard>) get(PROPERTY_RCHRQUALITYSTANDARDWEFTPRODUCTLIST);
    }

    public void setRCHRQualitystandardWeftProductList(List<RCHRQualitystandard> rCHRQualitystandardWeftProductList) {
        set(PROPERTY_RCHRQUALITYSTANDARDWEFTPRODUCTLIST, rCHRQualitystandardWeftProductList);
    }

    @SuppressWarnings("unchecked")
    public List<Prlines> getRCOBPrlinesList() {
        return (List<Prlines>) get(PROPERTY_RCOBPRLINESLIST);
    }

    public void setRCOBPrlinesList(List<Prlines> rCOBPrlinesList) {
        set(PROPERTY_RCOBPRLINESLIST, rCOBPrlinesList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseQuotationLines> getRCOBPurchasequotationlinesList() {
        return (List<PurchaseQuotationLines>) get(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST);
    }

    public void setRCOBPurchasequotationlinesList(List<PurchaseQuotationLines> rCOBPurchasequotationlinesList) {
        set(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST, rCOBPurchasequotationlinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Consumables> getRCPRConsumablesList() {
        return (List<RCPR_Consumables>) get(PROPERTY_RCPRCONSUMABLESLIST);
    }

    public void setRCPRConsumablesList(List<RCPR_Consumables> rCPRConsumablesList) {
        set(PROPERTY_RCPRCONSUMABLESLIST, rCPRConsumablesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_MachineCategory> getRCPRMachineCategoryList() {
        return (List<RCPR_MachineCategory>) get(PROPERTY_RCPRMACHINECATEGORYLIST);
    }

    public void setRCPRMachineCategoryList(List<RCPR_MachineCategory> rCPRMachineCategoryList) {
        set(PROPERTY_RCPRMACHINECATEGORYLIST, rCPRMachineCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrBOM> getRCPRPrBOMList() {
        return (List<RCPR_PrBOM>) get(PROPERTY_RCPRPRBOMLIST);
    }

    public void setRCPRPrBOMList(List<RCPR_PrBOM> rCPRPrBOMList) {
        set(PROPERTY_RCPRPRBOMLIST, rCPRPrBOMList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrBOMLines> getRCPRPrBOMLinesList() {
        return (List<RCPR_PrBOMLines>) get(PROPERTY_RCPRPRBOMLINESLIST);
    }

    public void setRCPRPrBOMLinesList(List<RCPR_PrBOMLines> rCPRPrBOMLinesList) {
        set(PROPERTY_RCPRPRBOMLINESLIST, rCPRPrBOMLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PrProduct> getRCPRPrProductList() {
        return (List<RCPR_PrProduct>) get(PROPERTY_RCPRPRPRODUCTLIST);
    }

    public void setRCPRPrProductList(List<RCPR_PrProduct> rCPRPrProductList) {
        set(PROPERTY_RCPRPRPRODUCTLIST, rCPRPrProductList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_ProductionRun> getRCPRProductionRunList() {
        return (List<RCPR_ProductionRun>) get(PROPERTY_RCPRPRODUCTIONRUNLIST);
    }

    public void setRCPRProductionRunList(List<RCPR_ProductionRun> rCPRProductionRunList) {
        set(PROPERTY_RCPRPRODUCTIONRUNLIST, rCPRProductionRunList);
    }

    @SuppressWarnings("unchecked")
    public List<RcobStoragebins> getRcobStoragebinsList() {
        return (List<RcobStoragebins>) get(PROPERTY_RCOBSTORAGEBINSLIST);
    }

    public void setRcobStoragebinsList(List<RcobStoragebins> rcobStoragebinsList) {
        set(PROPERTY_RCOBSTORAGEBINSLIST, rcobStoragebinsList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialOrderPickEditLines> getReturnMaterialOrderPickEditLinesList() {
        return (List<ReturnMaterialOrderPickEditLines>) get(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST);
    }

    public void setReturnMaterialOrderPickEditLinesList(List<ReturnMaterialOrderPickEditLines> returnMaterialOrderPickEditLinesList) {
        set(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST, returnMaterialOrderPickEditLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialReceiptPickEdit> getReturnMaterialReceiptPickEditList() {
        return (List<ReturnMaterialReceiptPickEdit>) get(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST);
    }

    public void setReturnMaterialReceiptPickEditList(List<ReturnMaterialReceiptPickEdit> returnMaterialReceiptPickEditList) {
        set(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, returnMaterialReceiptPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialShipmentPickEdit> getReturnMaterialShipmentPickEditList() {
        return (List<ReturnMaterialShipmentPickEdit>) get(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST);
    }

    public void setReturnMaterialShipmentPickEditList(List<ReturnMaterialShipmentPickEdit> returnMaterialShipmentPickEditList) {
        set(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, returnMaterialShipmentPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<Commission> getSalesCommissionList() {
        return (List<Commission>) get(PROPERTY_SALESCOMMISSIONLIST);
    }

    public void setSalesCommissionList(List<Commission> salesCommissionList) {
        set(PROPERTY_SALESCOMMISSIONLIST, salesCommissionList);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionLine> getSalesCommissionLineList() {
        return (List<CommissionLine>) get(PROPERTY_SALESCOMMISSIONLINELIST);
    }

    public void setSalesCommissionLineList(List<CommissionLine> salesCommissionLineList) {
        set(PROPERTY_SALESCOMMISSIONLINELIST, salesCommissionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLine> getTimeAndExpenseSheetLineList() {
        return (List<SheetLine>) get(PROPERTY_TIMEANDEXPENSESHEETLINELIST);
    }

    public void setTimeAndExpenseSheetLineList(List<SheetLine> timeAndExpenseSheetLineList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINELIST, timeAndExpenseSheetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLineV> getTimeAndExpenseSheetLineVList() {
        return (List<SheetLineV>) get(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST);
    }

    public void setTimeAndExpenseSheetLineVList(List<SheetLineV> timeAndExpenseSheetLineVList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST, timeAndExpenseSheetLineVList);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransactionV> getTransactionVList() {
        return (List<MaterialTransactionV>) get(PROPERTY_TRANSACTIONVLIST);
    }

    public void setTransactionVList(List<MaterialTransactionV> transactionVList) {
        set(PROPERTY_TRANSACTIONVLIST, transactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<epcg_ppcenquiry> getEpcgPpcenquiryList() {
        return (List<epcg_ppcenquiry>) get(PROPERTY_EPCGPPCENQUIRYLIST);
    }

    public void setEpcgPpcenquiryList(List<epcg_ppcenquiry> epcgPpcenquiryList) {
        set(PROPERTY_EPCGPPCENQUIRYLIST, epcgPpcenquiryList);
    }

}
