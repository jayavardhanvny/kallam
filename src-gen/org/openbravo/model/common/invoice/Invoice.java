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
package org.openbravo.model.common.invoice;

import com.redcarpet.epcg.data.EPCGPurchasetrackline;
import com.redcarpet.epcg.data.EPCGSalestrackline;
import com.redcarpet.epcg.data.EpcgDetails;
import com.redcarpet.epcg.data.Epcg_EpcgNew;
import com.redcarpet.rcssob.data.RCOB_AgentLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRMPendingPaymentFromInvoice;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.InvoiceLineTax;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BankAccount;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.ConversionRateDoc;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.gl.GLCharge;
import org.openbravo.model.financialmgmt.payment.DebtPayment;
import org.openbravo.model.financialmgmt.payment.DebtPaymentCancelV;
import org.openbravo.model.financialmgmt.payment.DebtPaymentGenerateV;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentSchedInvV;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentSchedOrdV;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentSchedule;
import org.openbravo.model.financialmgmt.payment.Fin_OrigPaymentSchedule;
import org.openbravo.model.financialmgmt.payment.PaymentPriority;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.Withholding;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.model.pricing.volumediscount.DiscountInvoice;
import org.openbravo.model.project.Project;
import org.openbravo.model.sales.CommissionRun;
/**
 * Entity class for entity Invoice (stored in table C_Invoice).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Invoice extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Invoice";
    public static final String ENTITY_NAME = "Invoice";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_TRANSACTIONDOCUMENT = "transactionDocument";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_PRINT = "print";
    public static final String PROPERTY_SALESREPRESENTATIVE = "salesRepresentative";
    public static final String PROPERTY_INVOICEDATE = "invoiceDate";
    public static final String PROPERTY_DATEPRINTED = "datePrinted";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_ORDERREFERENCE = "orderReference";
    public static final String PROPERTY_PRINTDISCOUNT = "printDiscount";
    public static final String PROPERTY_ORDERDATE = "orderDate";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_FORMOFPAYMENT = "formOfPayment";
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_CHARGE = "charge";
    public static final String PROPERTY_CHARGEAMOUNT = "chargeAmount";
    public static final String PROPERTY_SUMMEDLINEAMOUNT = "summedLineAmount";
    public static final String PROPERTY_GRANDTOTALAMOUNT = "grandTotalAmount";
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_PRICEINCLUDESTAX = "priceIncludesTax";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_CREATELINESFROM = "createLinesFrom";
    public static final String PROPERTY_GENERATETO = "generateTo";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_COPYFROM = "copyFrom";
    public static final String PROPERTY_SELFSERVICE = "selfService";
    public static final String PROPERTY_TRXORGANIZATION = "trxOrganization";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_WITHHOLDINGAMOUNT = "withholdingamount";
    public static final String PROPERTY_TAXDATE = "taxDate";
    public static final String PROPERTY_WITHHOLDING = "withholding";
    public static final String PROPERTY_PAYMENTCOMPLETE = "paymentComplete";
    public static final String PROPERTY_TOTALPAID = "totalPaid";
    public static final String PROPERTY_OUTSTANDINGAMOUNT = "outstandingAmount";
    public static final String PROPERTY_DAYSTILLDUE = "daysTillDue";
    public static final String PROPERTY_DUEAMOUNT = "dueAmount";
    public static final String PROPERTY_LASTCALCULATEDONDATE = "lastCalculatedOnDate";
    public static final String PROPERTY_UPDATEPAYMENTMONITOR = "updatePaymentMonitor";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_FINPAYMENTPRIORITY = "fINPaymentPriority";
    public static final String PROPERTY_FINALSETTLEMENTDATE = "finalSettlementDate";
    public static final String PROPERTY_DAYSSALESOUTSTANDING = "daysSalesOutstanding";
    public static final String PROPERTY_PERCENTAGEOVERDUE = "percentageOverdue";
    public static final String PROPERTY_COSTCENTER = "costcenter";
    public static final String PROPERTY_APRMADDPAYMENT = "aPRMAddpayment";
    public static final String PROPERTY_CALCULATEPROMOTIONS = "calculatePromotions";
    public static final String PROPERTY_APRMPROCESSINVOICE = "aPRMProcessinvoice";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_EPCGEPCGONE = "epcgEpcgone";
    public static final String PROPERTY_EPCGCHECK = "epcgCheck";
    public static final String PROPERTY_EPCGINVOICETYPE = "epcgInvoicetype";
    public static final String PROPERTY_EPCGDOMESTICTYPE = "epcgDomestictype";
    public static final String PROPERTY_EPCGEXPORTTYPE = "epcgExporttype";
    public static final String PROPERTY_EPCGEDEXEMPT = "epcgEdexempt";
    public static final String PROPERTY_PARTNERBANKACCOUNT = "partnerBankAccount";
    public static final String PROPERTY_EPCGLCNO = "epcgLcno";
    public static final String PROPERTY_EPCGLCDATE = "epcgLcdate";
    public static final String PROPERTY_EPCGWAYBILLNO = "epcgWaybillno";
    public static final String PROPERTY_EPCGPORTLOAD = "epcgPortload";
    public static final String PROPERTY_EPCGPORTDISCHARGE = "epcgPortdischarge";
    public static final String PROPERTY_EPCGFINALDEST = "epcgFinaldest";
    public static final String PROPERTY_EPCGLRNO = "epcgLrno";
    public static final String PROPERTY_EPCGTRANSNAME = "epcgTransname";
    public static final String PROPERTY_EPCGARENO = "epcgAreno";
    public static final String PROPERTY_EPCGEXCISENO = "epcgExciseno";
    public static final String PROPERTY_EPCGREMARKS = "epcgRemarks";
    public static final String PROPERTY_EPCGPRINTTYPE = "epcgPrinttype";
    public static final String PROPERTY_EPCGLOCDELIVERY = "epcgLocdelivery";
    public static final String PROPERTY_EPCGINSPECTOR = "epcgInspector";
    public static final String PROPERTY_EPCGSUPERINTENDENT = "epcgSuperintendent";
    public static final String PROPERTY_EPCGTYPE = "epcgType";
    public static final String PROPERTY_EPCGUSDRATE = "epcgUsdrate";
    public static final String PROPERTY_EPCGAGENT = "epcgAgent";
    public static final String PROPERTY_EPCGFORMTYPENO = "epcgFormtypeno";
    public static final String PROPERTY_EPCGPOINCVAL = "epcgPoincval";
    public static final String PROPERTY_RCFRFREIGHTPAID = "rcfrFreightpaid";
    public static final String PROPERTY_EPCGDISPATCHFROM = "epcgDispatchfrom";
    public static final String PROPERTY_EPCGDISPATCHTO = "epcgDispatchto";
    public static final String PROPERTY_EPCGTIN = "epcgTin";
    public static final String PROPERTY_RCFRCALCDISTCOSTH = "rcfrCalcdistcosth";
    public static final String PROPERTY_RCFRFREIGHTH = "rcfrFreighth";
    public static final String PROPERTY_RCFRINSURANCEH = "rcfrInsuranceh";
    public static final String PROPERTY_RCFRRATETYPE = "rcfrRatetype";
    public static final String PROPERTY_RCOBCALCULATECOMMISSION = "rCOBCalculatecommission";
    public static final String PROPERTY_RCOBCOMMISSION = "rCOBCommission";
    public static final String PROPERTY_RCOBISINVOICECREATED = "rCOBIsinvoicecreated";
    public static final String PROPERTY_APRMPENDINGPAYMENTINVOICELIST = "aPRMPendingPaymentInvoiceList";
    public static final String PROPERTY_CURRENCYCONVERSIONRATEDOCLIST = "currencyConversionRateDocList";
    public static final String PROPERTY_EPCGPURCHASETRACKLINELIST = "ePCGPurchasetracklineList";
    public static final String PROPERTY_EPCGSALESTRACKLINELIST = "ePCGSalestracklineList";
    public static final String PROPERTY_EPCGDETAILSLIST = "epcgDetailsList";
    public static final String PROPERTY_FINPAYMENTSCHEDINVVLIST = "fINPaymentSchedInvVList";
    public static final String PROPERTY_FINPAYMENTSCHEDORDVLIST = "fINPaymentSchedOrdVList";
    public static final String PROPERTY_FINPAYMENTSCHEDULELIST = "fINPaymentScheduleList";
    public static final String PROPERTY_FINORIGPAYMENTSCHEDULELIST = "finOrigPaymentScheduleList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST = "financialMgmtDebtPaymentList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST = "financialMgmtDebtPaymentCancelVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST = "financialMgmtDebtPaymentGenerateVList";
    public static final String PROPERTY_INVOICEDISCOUNTLIST = "invoiceDiscountList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_INVOICELINEEMRCOBREFERENCEINVOICELIST = "invoiceLineEMRCOBReferenceinvoiceList";
    public static final String PROPERTY_INVOICELINETAXLIST = "invoiceLineTaxList";
    public static final String PROPERTY_INVOICELINEV2LIST = "invoiceLineV2List";
    public static final String PROPERTY_INVOICETAXLIST = "invoiceTaxList";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_PRICINGVOLUMEDISCOUNTINVOICELIST = "pricingVolumeDiscountInvoiceList";
    public static final String PROPERTY_RCOBAGENTLINELIST = "rCOBAgentLineList";
    public static final String PROPERTY_REVERSEDINVOICESLIST = "reversedInvoicesList";
    public static final String PROPERTY_REVERSEDINVOICESREVERSEDINVOICELIST = "reversedInvoicesReversedInvoiceList";
    public static final String PROPERTY_SALESCOMMISSIONRUNLIST = "salesCommissionRunList";

    public Invoice() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_DOCUMENTACTION, "CO");
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_PRINT, false);
        setDefaultValue(PROPERTY_PRINTDISCOUNT, false);
        setDefaultValue(PROPERTY_FORMOFPAYMENT, "P");
        setDefaultValue(PROPERTY_PRICEINCLUDESTAX, false);
        setDefaultValue(PROPERTY_CREATELINESFROM, false);
        setDefaultValue(PROPERTY_GENERATETO, false);
        setDefaultValue(PROPERTY_COPYFROM, false);
        setDefaultValue(PROPERTY_SELFSERVICE, false);
        setDefaultValue(PROPERTY_PAYMENTCOMPLETE, false);
        setDefaultValue(PROPERTY_TOTALPAID, new BigDecimal(0));
        setDefaultValue(PROPERTY_OUTSTANDINGAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DAYSTILLDUE, (long) 0);
        setDefaultValue(PROPERTY_DUEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_UPDATEPAYMENTMONITOR, false);
        setDefaultValue(PROPERTY_APRMADDPAYMENT, true);
        setDefaultValue(PROPERTY_CALCULATEPROMOTIONS, false);
        setDefaultValue(PROPERTY_APRMPROCESSINVOICE, "CO");
        setDefaultValue(PROPERTY_EPCGCHECK, false);
        setDefaultValue(PROPERTY_EPCGEDEXEMPT, "30/2004 CENTRAL EXCISE");
        setDefaultValue(PROPERTY_EPCGPRINTTYPE, "Standard Invoice");
        setDefaultValue(PROPERTY_EPCGPOINCVAL, (long) 0);
        setDefaultValue(PROPERTY_RCFRFREIGHTPAID, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCFRCALCDISTCOSTH, false);
        setDefaultValue(PROPERTY_RCOBCALCULATECOMMISSION, false);
        setDefaultValue(PROPERTY_RCOBCOMMISSION, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCOBISINVOICECREATED, false);
        setDefaultValue(PROPERTY_APRMPENDINGPAYMENTINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CURRENCYCONVERSIONRATEDOCLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPURCHASETRACKLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGSALESTRACKLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDINVVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDORDVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINORIGPAYMENTSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEDISCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEEMRCOBREFERENCEINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINETAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICETAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGVOLUMEDISCOUNTINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBAGENTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REVERSEDINVOICESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REVERSEDINVOICESREVERSEDINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONRUNLIST, new ArrayList<Object>());
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

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
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

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public DocumentType getTransactionDocument() {
        return (DocumentType) get(PROPERTY_TRANSACTIONDOCUMENT);
    }

    public void setTransactionDocument(DocumentType transactionDocument) {
        set(PROPERTY_TRANSACTIONDOCUMENT, transactionDocument);
    }

    public Order getSalesOrder() {
        return (Order) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Order salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isPrint() {
        return (Boolean) get(PROPERTY_PRINT);
    }

    public void setPrint(Boolean print) {
        set(PROPERTY_PRINT, print);
    }

    public User getSalesRepresentative() {
        return (User) get(PROPERTY_SALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(User salesRepresentative) {
        set(PROPERTY_SALESREPRESENTATIVE, salesRepresentative);
    }

    public Date getInvoiceDate() {
        return (Date) get(PROPERTY_INVOICEDATE);
    }

    public void setInvoiceDate(Date invoiceDate) {
        set(PROPERTY_INVOICEDATE, invoiceDate);
    }

    public Date getDatePrinted() {
        return (Date) get(PROPERTY_DATEPRINTED);
    }

    public void setDatePrinted(Date datePrinted) {
        set(PROPERTY_DATEPRINTED, datePrinted);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public String getOrderReference() {
        return (String) get(PROPERTY_ORDERREFERENCE);
    }

    public void setOrderReference(String orderReference) {
        set(PROPERTY_ORDERREFERENCE, orderReference);
    }

    public Boolean isPrintDiscount() {
        return (Boolean) get(PROPERTY_PRINTDISCOUNT);
    }

    public void setPrintDiscount(Boolean printDiscount) {
        set(PROPERTY_PRINTDISCOUNT, printDiscount);
    }

    public Date getOrderDate() {
        return (Date) get(PROPERTY_ORDERDATE);
    }

    public void setOrderDate(Date orderDate) {
        set(PROPERTY_ORDERDATE, orderDate);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getFormOfPayment() {
        return (String) get(PROPERTY_FORMOFPAYMENT);
    }

    public void setFormOfPayment(String formOfPayment) {
        set(PROPERTY_FORMOFPAYMENT, formOfPayment);
    }

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public GLCharge getCharge() {
        return (GLCharge) get(PROPERTY_CHARGE);
    }

    public void setCharge(GLCharge charge) {
        set(PROPERTY_CHARGE, charge);
    }

    public BigDecimal getChargeAmount() {
        return (BigDecimal) get(PROPERTY_CHARGEAMOUNT);
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        set(PROPERTY_CHARGEAMOUNT, chargeAmount);
    }

    public BigDecimal getSummedLineAmount() {
        return (BigDecimal) get(PROPERTY_SUMMEDLINEAMOUNT);
    }

    public void setSummedLineAmount(BigDecimal summedLineAmount) {
        set(PROPERTY_SUMMEDLINEAMOUNT, summedLineAmount);
    }

    public BigDecimal getGrandTotalAmount() {
        return (BigDecimal) get(PROPERTY_GRANDTOTALAMOUNT);
    }

    public void setGrandTotalAmount(BigDecimal grandTotalAmount) {
        set(PROPERTY_GRANDTOTALAMOUNT, grandTotalAmount);
    }

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public Boolean isPriceIncludesTax() {
        return (Boolean) get(PROPERTY_PRICEINCLUDESTAX);
    }

    public void setPriceIncludesTax(Boolean priceIncludesTax) {
        set(PROPERTY_PRICEINCLUDESTAX, priceIncludesTax);
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

    public Boolean isCreateLinesFrom() {
        return (Boolean) get(PROPERTY_CREATELINESFROM);
    }

    public void setCreateLinesFrom(Boolean createLinesFrom) {
        set(PROPERTY_CREATELINESFROM, createLinesFrom);
    }

    public Boolean isGenerateTo() {
        return (Boolean) get(PROPERTY_GENERATETO);
    }

    public void setGenerateTo(Boolean generateTo) {
        set(PROPERTY_GENERATETO, generateTo);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public Boolean isCopyFrom() {
        return (Boolean) get(PROPERTY_COPYFROM);
    }

    public void setCopyFrom(Boolean copyFrom) {
        set(PROPERTY_COPYFROM, copyFrom);
    }

    public Boolean isSelfService() {
        return (Boolean) get(PROPERTY_SELFSERVICE);
    }

    public void setSelfService(Boolean selfService) {
        set(PROPERTY_SELFSERVICE, selfService);
    }

    public Organization getTrxOrganization() {
        return (Organization) get(PROPERTY_TRXORGANIZATION);
    }

    public void setTrxOrganization(Organization trxOrganization) {
        set(PROPERTY_TRXORGANIZATION, trxOrganization);
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

    public BigDecimal getWithholdingamount() {
        return (BigDecimal) get(PROPERTY_WITHHOLDINGAMOUNT);
    }

    public void setWithholdingamount(BigDecimal withholdingamount) {
        set(PROPERTY_WITHHOLDINGAMOUNT, withholdingamount);
    }

    public Date getTaxDate() {
        return (Date) get(PROPERTY_TAXDATE);
    }

    public void setTaxDate(Date taxDate) {
        set(PROPERTY_TAXDATE, taxDate);
    }

    public Withholding getWithholding() {
        return (Withholding) get(PROPERTY_WITHHOLDING);
    }

    public void setWithholding(Withholding withholding) {
        set(PROPERTY_WITHHOLDING, withholding);
    }

    public Boolean isPaymentComplete() {
        return (Boolean) get(PROPERTY_PAYMENTCOMPLETE);
    }

    public void setPaymentComplete(Boolean paymentComplete) {
        set(PROPERTY_PAYMENTCOMPLETE, paymentComplete);
    }

    public BigDecimal getTotalPaid() {
        return (BigDecimal) get(PROPERTY_TOTALPAID);
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        set(PROPERTY_TOTALPAID, totalPaid);
    }

    public BigDecimal getOutstandingAmount() {
        return (BigDecimal) get(PROPERTY_OUTSTANDINGAMOUNT);
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        set(PROPERTY_OUTSTANDINGAMOUNT, outstandingAmount);
    }

    public Long getDaysTillDue() {
        return (Long) get(PROPERTY_DAYSTILLDUE);
    }

    public void setDaysTillDue(Long daysTillDue) {
        set(PROPERTY_DAYSTILLDUE, daysTillDue);
    }

    public BigDecimal getDueAmount() {
        return (BigDecimal) get(PROPERTY_DUEAMOUNT);
    }

    public void setDueAmount(BigDecimal dueAmount) {
        set(PROPERTY_DUEAMOUNT, dueAmount);
    }

    public Date getLastCalculatedOnDate() {
        return (Date) get(PROPERTY_LASTCALCULATEDONDATE);
    }

    public void setLastCalculatedOnDate(Date lastCalculatedOnDate) {
        set(PROPERTY_LASTCALCULATEDONDATE, lastCalculatedOnDate);
    }

    public Boolean isUpdatePaymentMonitor() {
        return (Boolean) get(PROPERTY_UPDATEPAYMENTMONITOR);
    }

    public void setUpdatePaymentMonitor(Boolean updatePaymentMonitor) {
        set(PROPERTY_UPDATEPAYMENTMONITOR, updatePaymentMonitor);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public PaymentPriority getFINPaymentPriority() {
        return (PaymentPriority) get(PROPERTY_FINPAYMENTPRIORITY);
    }

    public void setFINPaymentPriority(PaymentPriority fINPaymentPriority) {
        set(PROPERTY_FINPAYMENTPRIORITY, fINPaymentPriority);
    }

    public Date getFinalSettlementDate() {
        return (Date) get(PROPERTY_FINALSETTLEMENTDATE);
    }

    public void setFinalSettlementDate(Date finalSettlementDate) {
        set(PROPERTY_FINALSETTLEMENTDATE, finalSettlementDate);
    }

    public Long getDaysSalesOutstanding() {
        return (Long) get(PROPERTY_DAYSSALESOUTSTANDING);
    }

    public void setDaysSalesOutstanding(Long daysSalesOutstanding) {
        set(PROPERTY_DAYSSALESOUTSTANDING, daysSalesOutstanding);
    }

    public Long getPercentageOverdue() {
        return (Long) get(PROPERTY_PERCENTAGEOVERDUE);
    }

    public void setPercentageOverdue(Long percentageOverdue) {
        set(PROPERTY_PERCENTAGEOVERDUE, percentageOverdue);
    }

    public Costcenter getCostcenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostcenter(Costcenter costcenter) {
        set(PROPERTY_COSTCENTER, costcenter);
    }

    public Boolean isAPRMAddpayment() {
        return (Boolean) get(PROPERTY_APRMADDPAYMENT);
    }

    public void setAPRMAddpayment(Boolean aPRMAddpayment) {
        set(PROPERTY_APRMADDPAYMENT, aPRMAddpayment);
    }

    public Boolean isCalculatePromotions() {
        return (Boolean) get(PROPERTY_CALCULATEPROMOTIONS);
    }

    public void setCalculatePromotions(Boolean calculatePromotions) {
        set(PROPERTY_CALCULATEPROMOTIONS, calculatePromotions);
    }

    public String getAPRMProcessinvoice() {
        return (String) get(PROPERTY_APRMPROCESSINVOICE);
    }

    public void setAPRMProcessinvoice(String aPRMProcessinvoice) {
        set(PROPERTY_APRMPROCESSINVOICE, aPRMProcessinvoice);
    }

    public Asset getAsset() {
        return (Asset) get(PROPERTY_ASSET);
    }

    public void setAsset(Asset asset) {
        set(PROPERTY_ASSET, asset);
    }

    public Epcg_EpcgNew getEpcgEpcgone() {
        return (Epcg_EpcgNew) get(PROPERTY_EPCGEPCGONE);
    }

    public void setEpcgEpcgone(Epcg_EpcgNew epcgEpcgone) {
        set(PROPERTY_EPCGEPCGONE, epcgEpcgone);
    }

    public Boolean isEpcgCheck() {
        return (Boolean) get(PROPERTY_EPCGCHECK);
    }

    public void setEpcgCheck(Boolean epcgCheck) {
        set(PROPERTY_EPCGCHECK, epcgCheck);
    }

    public String getEpcgInvoicetype() {
        return (String) get(PROPERTY_EPCGINVOICETYPE);
    }

    public void setEpcgInvoicetype(String epcgInvoicetype) {
        set(PROPERTY_EPCGINVOICETYPE, epcgInvoicetype);
    }

    public String getEpcgDomestictype() {
        return (String) get(PROPERTY_EPCGDOMESTICTYPE);
    }

    public void setEpcgDomestictype(String epcgDomestictype) {
        set(PROPERTY_EPCGDOMESTICTYPE, epcgDomestictype);
    }

    public String getEpcgExporttype() {
        return (String) get(PROPERTY_EPCGEXPORTTYPE);
    }

    public void setEpcgExporttype(String epcgExporttype) {
        set(PROPERTY_EPCGEXPORTTYPE, epcgExporttype);
    }

    public String getEpcgEdexempt() {
        return (String) get(PROPERTY_EPCGEDEXEMPT);
    }

    public void setEpcgEdexempt(String epcgEdexempt) {
        set(PROPERTY_EPCGEDEXEMPT, epcgEdexempt);
    }

    public BankAccount getPartnerBankAccount() {
        return (BankAccount) get(PROPERTY_PARTNERBANKACCOUNT);
    }

    public void setPartnerBankAccount(BankAccount partnerBankAccount) {
        set(PROPERTY_PARTNERBANKACCOUNT, partnerBankAccount);
    }

    public String getEpcgLcno() {
        return (String) get(PROPERTY_EPCGLCNO);
    }

    public void setEpcgLcno(String epcgLcno) {
        set(PROPERTY_EPCGLCNO, epcgLcno);
    }

    public Date getEpcgLcdate() {
        return (Date) get(PROPERTY_EPCGLCDATE);
    }

    public void setEpcgLcdate(Date epcgLcdate) {
        set(PROPERTY_EPCGLCDATE, epcgLcdate);
    }

    public String getEpcgWaybillno() {
        return (String) get(PROPERTY_EPCGWAYBILLNO);
    }

    public void setEpcgWaybillno(String epcgWaybillno) {
        set(PROPERTY_EPCGWAYBILLNO, epcgWaybillno);
    }

    public String getEpcgPortload() {
        return (String) get(PROPERTY_EPCGPORTLOAD);
    }

    public void setEpcgPortload(String epcgPortload) {
        set(PROPERTY_EPCGPORTLOAD, epcgPortload);
    }

    public String getEpcgPortdischarge() {
        return (String) get(PROPERTY_EPCGPORTDISCHARGE);
    }

    public void setEpcgPortdischarge(String epcgPortdischarge) {
        set(PROPERTY_EPCGPORTDISCHARGE, epcgPortdischarge);
    }

    public String getEpcgFinaldest() {
        return (String) get(PROPERTY_EPCGFINALDEST);
    }

    public void setEpcgFinaldest(String epcgFinaldest) {
        set(PROPERTY_EPCGFINALDEST, epcgFinaldest);
    }

    public String getEpcgLrno() {
        return (String) get(PROPERTY_EPCGLRNO);
    }

    public void setEpcgLrno(String epcgLrno) {
        set(PROPERTY_EPCGLRNO, epcgLrno);
    }

    public String getEpcgTransname() {
        return (String) get(PROPERTY_EPCGTRANSNAME);
    }

    public void setEpcgTransname(String epcgTransname) {
        set(PROPERTY_EPCGTRANSNAME, epcgTransname);
    }

    public String getEpcgAreno() {
        return (String) get(PROPERTY_EPCGARENO);
    }

    public void setEpcgAreno(String epcgAreno) {
        set(PROPERTY_EPCGARENO, epcgAreno);
    }

    public String getEpcgExciseno() {
        return (String) get(PROPERTY_EPCGEXCISENO);
    }

    public void setEpcgExciseno(String epcgExciseno) {
        set(PROPERTY_EPCGEXCISENO, epcgExciseno);
    }

    public String getEpcgRemarks() {
        return (String) get(PROPERTY_EPCGREMARKS);
    }

    public void setEpcgRemarks(String epcgRemarks) {
        set(PROPERTY_EPCGREMARKS, epcgRemarks);
    }

    public String getEpcgPrinttype() {
        return (String) get(PROPERTY_EPCGPRINTTYPE);
    }

    public void setEpcgPrinttype(String epcgPrinttype) {
        set(PROPERTY_EPCGPRINTTYPE, epcgPrinttype);
    }

    public String getEpcgLocdelivery() {
        return (String) get(PROPERTY_EPCGLOCDELIVERY);
    }

    public void setEpcgLocdelivery(String epcgLocdelivery) {
        set(PROPERTY_EPCGLOCDELIVERY, epcgLocdelivery);
    }

    public String getEpcgInspector() {
        return (String) get(PROPERTY_EPCGINSPECTOR);
    }

    public void setEpcgInspector(String epcgInspector) {
        set(PROPERTY_EPCGINSPECTOR, epcgInspector);
    }

    public String getEpcgSuperintendent() {
        return (String) get(PROPERTY_EPCGSUPERINTENDENT);
    }

    public void setEpcgSuperintendent(String epcgSuperintendent) {
        set(PROPERTY_EPCGSUPERINTENDENT, epcgSuperintendent);
    }

    public String getEpcgType() {
        return (String) get(PROPERTY_EPCGTYPE);
    }

    public void setEpcgType(String epcgType) {
        set(PROPERTY_EPCGTYPE, epcgType);
    }

    public BigDecimal getEpcgUsdrate() {
        return (BigDecimal) get(PROPERTY_EPCGUSDRATE);
    }

    public void setEpcgUsdrate(BigDecimal epcgUsdrate) {
        set(PROPERTY_EPCGUSDRATE, epcgUsdrate);
    }

    public BusinessPartner getEpcgAgent() {
        return (BusinessPartner) get(PROPERTY_EPCGAGENT);
    }

    public void setEpcgAgent(BusinessPartner epcgAgent) {
        set(PROPERTY_EPCGAGENT, epcgAgent);
    }

    public String getEpcgFormtypeno() {
        return (String) get(PROPERTY_EPCGFORMTYPENO);
    }

    public void setEpcgFormtypeno(String epcgFormtypeno) {
        set(PROPERTY_EPCGFORMTYPENO, epcgFormtypeno);
    }

    public Long getEpcgPoincval() {
        return (Long) get(PROPERTY_EPCGPOINCVAL);
    }

    public void setEpcgPoincval(Long epcgPoincval) {
        set(PROPERTY_EPCGPOINCVAL, epcgPoincval);
    }

    public BigDecimal getRcfrFreightpaid() {
        return (BigDecimal) get(PROPERTY_RCFRFREIGHTPAID);
    }

    public void setRcfrFreightpaid(BigDecimal rcfrFreightpaid) {
        set(PROPERTY_RCFRFREIGHTPAID, rcfrFreightpaid);
    }

    public String getEpcgDispatchfrom() {
        return (String) get(PROPERTY_EPCGDISPATCHFROM);
    }

    public void setEpcgDispatchfrom(String epcgDispatchfrom) {
        set(PROPERTY_EPCGDISPATCHFROM, epcgDispatchfrom);
    }

    public String getEpcgDispatchto() {
        return (String) get(PROPERTY_EPCGDISPATCHTO);
    }

    public void setEpcgDispatchto(String epcgDispatchto) {
        set(PROPERTY_EPCGDISPATCHTO, epcgDispatchto);
    }

    public String getEpcgTin() {
        return (String) get(PROPERTY_EPCGTIN);
    }

    public void setEpcgTin(String epcgTin) {
        set(PROPERTY_EPCGTIN, epcgTin);
    }

    public Boolean isRcfrCalcdistcosth() {
        return (Boolean) get(PROPERTY_RCFRCALCDISTCOSTH);
    }

    public void setRcfrCalcdistcosth(Boolean rcfrCalcdistcosth) {
        set(PROPERTY_RCFRCALCDISTCOSTH, rcfrCalcdistcosth);
    }

    public BigDecimal getRcfrFreighth() {
        return (BigDecimal) get(PROPERTY_RCFRFREIGHTH);
    }

    public void setRcfrFreighth(BigDecimal rcfrFreighth) {
        set(PROPERTY_RCFRFREIGHTH, rcfrFreighth);
    }

    public BigDecimal getRcfrInsuranceh() {
        return (BigDecimal) get(PROPERTY_RCFRINSURANCEH);
    }

    public void setRcfrInsuranceh(BigDecimal rcfrInsuranceh) {
        set(PROPERTY_RCFRINSURANCEH, rcfrInsuranceh);
    }

    public String getRcfrRatetype() {
        return (String) get(PROPERTY_RCFRRATETYPE);
    }

    public void setRcfrRatetype(String rcfrRatetype) {
        set(PROPERTY_RCFRRATETYPE, rcfrRatetype);
    }

    public Boolean isRCOBCalculatecommission() {
        return (Boolean) get(PROPERTY_RCOBCALCULATECOMMISSION);
    }

    public void setRCOBCalculatecommission(Boolean rCOBCalculatecommission) {
        set(PROPERTY_RCOBCALCULATECOMMISSION, rCOBCalculatecommission);
    }

    public BigDecimal getRCOBCommission() {
        return (BigDecimal) get(PROPERTY_RCOBCOMMISSION);
    }

    public void setRCOBCommission(BigDecimal rCOBCommission) {
        set(PROPERTY_RCOBCOMMISSION, rCOBCommission);
    }

    public Boolean isRCOBIsinvoicecreated() {
        return (Boolean) get(PROPERTY_RCOBISINVOICECREATED);
    }

    public void setRCOBIsinvoicecreated(Boolean rCOBIsinvoicecreated) {
        set(PROPERTY_RCOBISINVOICECREATED, rCOBIsinvoicecreated);
    }

    @SuppressWarnings("unchecked")
    public List<APRMPendingPaymentFromInvoice> getAPRMPendingPaymentInvoiceList() {
        return (List<APRMPendingPaymentFromInvoice>) get(PROPERTY_APRMPENDINGPAYMENTINVOICELIST);
    }

    public void setAPRMPendingPaymentInvoiceList(List<APRMPendingPaymentFromInvoice> aPRMPendingPaymentInvoiceList) {
        set(PROPERTY_APRMPENDINGPAYMENTINVOICELIST, aPRMPendingPaymentInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<ConversionRateDoc> getCurrencyConversionRateDocList() {
        return (List<ConversionRateDoc>) get(PROPERTY_CURRENCYCONVERSIONRATEDOCLIST);
    }

    public void setCurrencyConversionRateDocList(List<ConversionRateDoc> currencyConversionRateDocList) {
        set(PROPERTY_CURRENCYCONVERSIONRATEDOCLIST, currencyConversionRateDocList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGPurchasetrackline> getEPCGPurchasetracklineList() {
        return (List<EPCGPurchasetrackline>) get(PROPERTY_EPCGPURCHASETRACKLINELIST);
    }

    public void setEPCGPurchasetracklineList(List<EPCGPurchasetrackline> ePCGPurchasetracklineList) {
        set(PROPERTY_EPCGPURCHASETRACKLINELIST, ePCGPurchasetracklineList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGSalestrackline> getEPCGSalestracklineList() {
        return (List<EPCGSalestrackline>) get(PROPERTY_EPCGSALESTRACKLINELIST);
    }

    public void setEPCGSalestracklineList(List<EPCGSalestrackline> ePCGSalestracklineList) {
        set(PROPERTY_EPCGSALESTRACKLINELIST, ePCGSalestracklineList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgDetails> getEpcgDetailsList() {
        return (List<EpcgDetails>) get(PROPERTY_EPCGDETAILSLIST);
    }

    public void setEpcgDetailsList(List<EpcgDetails> epcgDetailsList) {
        set(PROPERTY_EPCGDETAILSLIST, epcgDetailsList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedInvV> getFINPaymentSchedInvVList() {
        return (List<FIN_PaymentSchedInvV>) get(PROPERTY_FINPAYMENTSCHEDINVVLIST);
    }

    public void setFINPaymentSchedInvVList(List<FIN_PaymentSchedInvV> fINPaymentSchedInvVList) {
        set(PROPERTY_FINPAYMENTSCHEDINVVLIST, fINPaymentSchedInvVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedOrdV> getFINPaymentSchedOrdVList() {
        return (List<FIN_PaymentSchedOrdV>) get(PROPERTY_FINPAYMENTSCHEDORDVLIST);
    }

    public void setFINPaymentSchedOrdVList(List<FIN_PaymentSchedOrdV> fINPaymentSchedOrdVList) {
        set(PROPERTY_FINPAYMENTSCHEDORDVLIST, fINPaymentSchedOrdVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedule> getFINPaymentScheduleList() {
        return (List<FIN_PaymentSchedule>) get(PROPERTY_FINPAYMENTSCHEDULELIST);
    }

    public void setFINPaymentScheduleList(List<FIN_PaymentSchedule> fINPaymentScheduleList) {
        set(PROPERTY_FINPAYMENTSCHEDULELIST, fINPaymentScheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<Fin_OrigPaymentSchedule> getFinOrigPaymentScheduleList() {
        return (List<Fin_OrigPaymentSchedule>) get(PROPERTY_FINORIGPAYMENTSCHEDULELIST);
    }

    public void setFinOrigPaymentScheduleList(List<Fin_OrigPaymentSchedule> finOrigPaymentScheduleList) {
        set(PROPERTY_FINORIGPAYMENTSCHEDULELIST, finOrigPaymentScheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPayment> getFinancialMgmtDebtPaymentList() {
        return (List<DebtPayment>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST);
    }

    public void setFinancialMgmtDebtPaymentList(List<DebtPayment> financialMgmtDebtPaymentList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, financialMgmtDebtPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentCancelV> getFinancialMgmtDebtPaymentCancelVList() {
        return (List<DebtPaymentCancelV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST);
    }

    public void setFinancialMgmtDebtPaymentCancelVList(List<DebtPaymentCancelV> financialMgmtDebtPaymentCancelVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, financialMgmtDebtPaymentCancelVList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentGenerateV> getFinancialMgmtDebtPaymentGenerateVList() {
        return (List<DebtPaymentGenerateV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST);
    }

    public void setFinancialMgmtDebtPaymentGenerateVList(List<DebtPaymentGenerateV> financialMgmtDebtPaymentGenerateVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, financialMgmtDebtPaymentGenerateVList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceDiscount> getInvoiceDiscountList() {
        return (List<InvoiceDiscount>) get(PROPERTY_INVOICEDISCOUNTLIST);
    }

    public void setInvoiceDiscountList(List<InvoiceDiscount> invoiceDiscountList) {
        set(PROPERTY_INVOICEDISCOUNTLIST, invoiceDiscountList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineEMRCOBReferenceinvoiceList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINEEMRCOBREFERENCEINVOICELIST);
    }

    public void setInvoiceLineEMRCOBReferenceinvoiceList(List<InvoiceLine> invoiceLineEMRCOBReferenceinvoiceList) {
        set(PROPERTY_INVOICELINEEMRCOBREFERENCEINVOICELIST, invoiceLineEMRCOBReferenceinvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineTax> getInvoiceLineTaxList() {
        return (List<InvoiceLineTax>) get(PROPERTY_INVOICELINETAXLIST);
    }

    public void setInvoiceLineTaxList(List<InvoiceLineTax> invoiceLineTaxList) {
        set(PROPERTY_INVOICELINETAXLIST, invoiceLineTaxList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineV2> getInvoiceLineV2List() {
        return (List<InvoiceLineV2>) get(PROPERTY_INVOICELINEV2LIST);
    }

    public void setInvoiceLineV2List(List<InvoiceLineV2> invoiceLineV2List) {
        set(PROPERTY_INVOICELINEV2LIST, invoiceLineV2List);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceTax> getInvoiceTaxList() {
        return (List<InvoiceTax>) get(PROPERTY_INVOICETAXLIST);
    }

    public void setInvoiceTaxList(List<InvoiceTax> invoiceTaxList) {
        set(PROPERTY_INVOICETAXLIST, invoiceTaxList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2List() {
        return (List<InvoiceV2>) get(PROPERTY_INVOICEV2LIST);
    }

    public void setInvoiceV2List(List<InvoiceV2> invoiceV2List) {
        set(PROPERTY_INVOICEV2LIST, invoiceV2List);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
        return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<DiscountInvoice> getPricingVolumeDiscountInvoiceList() {
        return (List<DiscountInvoice>) get(PROPERTY_PRICINGVOLUMEDISCOUNTINVOICELIST);
    }

    public void setPricingVolumeDiscountInvoiceList(List<DiscountInvoice> pricingVolumeDiscountInvoiceList) {
        set(PROPERTY_PRICINGVOLUMEDISCOUNTINVOICELIST, pricingVolumeDiscountInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<RCOB_AgentLine> getRCOBAgentLineList() {
        return (List<RCOB_AgentLine>) get(PROPERTY_RCOBAGENTLINELIST);
    }

    public void setRCOBAgentLineList(List<RCOB_AgentLine> rCOBAgentLineList) {
        set(PROPERTY_RCOBAGENTLINELIST, rCOBAgentLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ReversedInvoice> getReversedInvoicesList() {
        return (List<ReversedInvoice>) get(PROPERTY_REVERSEDINVOICESLIST);
    }

    public void setReversedInvoicesList(List<ReversedInvoice> reversedInvoicesList) {
        set(PROPERTY_REVERSEDINVOICESLIST, reversedInvoicesList);
    }

    @SuppressWarnings("unchecked")
    public List<ReversedInvoice> getReversedInvoicesReversedInvoiceList() {
        return (List<ReversedInvoice>) get(PROPERTY_REVERSEDINVOICESREVERSEDINVOICELIST);
    }

    public void setReversedInvoicesReversedInvoiceList(List<ReversedInvoice> reversedInvoicesReversedInvoiceList) {
        set(PROPERTY_REVERSEDINVOICESREVERSEDINVOICELIST, reversedInvoicesReversedInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionRun> getSalesCommissionRunList() {
        return (List<CommissionRun>) get(PROPERTY_SALESCOMMISSIONRUNLIST);
    }

    public void setSalesCommissionRunList(List<CommissionRun> salesCommissionRunList) {
        set(PROPERTY_SALESCOMMISSIONRUNLIST, salesCommissionRunList);
    }

}
