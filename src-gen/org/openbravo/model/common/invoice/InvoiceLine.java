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

import com.redcarpet.epcg.data.EPCG_Packaging;
import com.redcarpet.goodsissue.data.RCGITransactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.InvoiceLineTax;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductUOM;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.gl.GLCharge;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.manufacturing.transaction.ProductionRunInvoiceLine;
import org.openbravo.model.materialmgmt.cost.Costing;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.model.pricing.priceadjustment.PriceAdjustment;
import org.openbravo.model.procurement.POInvoiceMatch;
import org.openbravo.model.procurement.ReceiptInvoiceMatch;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectLine;
import org.openbravo.model.sales.CommissionDetail;
import org.openbravo.model.timeandexpense.ResourceAssignment;
import org.openbravo.model.timeandexpense.SheetLine;
import org.openbravo.model.timeandexpense.SheetLineV;
/**
 * Entity class for entity InvoiceLine (stored in table C_InvoiceLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class InvoiceLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_InvoiceLine";
    public static final String ENTITY_NAME = "InvoiceLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_GOODSSHIPMENTLINE = "goodsShipmentLine";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_FINANCIALINVOICELINE = "financialInvoiceLine";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_INVOICEDQUANTITY = "invoicedQuantity";
    public static final String PROPERTY_LISTPRICE = "listPrice";
    public static final String PROPERTY_UNITPRICE = "unitPrice";
    public static final String PROPERTY_PRICELIMIT = "priceLimit";
    public static final String PROPERTY_LINENETAMOUNT = "lineNetAmount";
    public static final String PROPERTY_CHARGE = "charge";
    public static final String PROPERTY_CHARGEAMOUNT = "chargeAmount";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_RESOURCEASSIGNMENT = "resourceAssignment";
    public static final String PROPERTY_TAXAMOUNT = "taxAmount";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_DESCRIPTIONONLY = "descriptionOnly";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_ORDERUOM = "orderUOM";
    public static final String PROPERTY_INVOICEDISCOUNT = "invoiceDiscount";
    public static final String PROPERTY_PROJECTLINE = "projectLine";
    public static final String PROPERTY_PRICEADJUSTMENT = "priceAdjustment";
    public static final String PROPERTY_STANDARDPRICE = "standardPrice";
    public static final String PROPERTY_EXCLUDEFORWITHHOLDING = "excludeforwithholding";
    public static final String PROPERTY_EDITLINEAMOUNT = "editLineAmount";
    public static final String PROPERTY_TAXABLEAMOUNT = "taxableAmount";
    public static final String PROPERTY_GROSSAMOUNT = "grossAmount";
    public static final String PROPERTY_GROSSUNITPRICE = "grossUnitPrice";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PERIODNUMBER = "periodNumber";
    public static final String PROPERTY_BASEGROSSUNITPRICE = "baseGrossUnitPrice";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_DEFERREDPLANTYPE = "deferredPlanType";
    public static final String PROPERTY_GROSSLISTPRICE = "grossListPrice";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_DEFERRED = "deferred";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_COSTCENTER = "costcenter";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_EXPLODE = "explode";
    public static final String PROPERTY_BOMPARENT = "bOMParent";
    public static final String PROPERTY_EPCGNOOFPACKS = "epcgNoofpacks";
    public static final String PROPERTY_EPCGNOOFPACKAGES = "epcgNoofpackages";
    public static final String PROPERTY_EPCGPACKAGING = "epcgPackaging";
    public static final String PROPERTY_EPCGPRODUCTALIAS = "epcgProductalias";
    public static final String PROPERTY_EPCGPROLISTALIAS = "epcgProlistalias";
    public static final String PROPERTY_RCFRFREIGHT = "rcfrFreight";
    public static final String PROPERTY_RCFRINSURANCE = "rcfrInsurance";
    public static final String PROPERTY_RCFRNETRATE = "rcfrNetrate";
    public static final String PROPERTY_RCFRNETUNITRATE = "rcfrNetunitrate";
    public static final String PROPERTY_RCOBREFERENCEINVOICE = "rCOBReferenceinvoice";
    public static final String PROPERTY_INVOICELINEBOMPARENTIDLIST = "invoiceLineBOMParentIDList";
    public static final String PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST = "invoiceLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELINEOFFERLIST = "invoiceLineOfferList";
    public static final String PROPERTY_INVOICELINETAXLIST = "invoiceLineTaxList";
    public static final String PROPERTY_INVOICELINEV2LIST = "invoiceLineV2List";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNINVOICELINELIST = "manufacturingProductionRunInvoiceLineList";
    public static final String PROPERTY_MATERIALMGMTCOSTINGLIST = "materialMgmtCostingList";
    public static final String PROPERTY_PROCUREMENTPOINVOICEMATCHLIST = "procurementPOInvoiceMatchList";
    public static final String PROPERTY_PROCUREMENTRECEIPTINVOICEMATCHLIST = "procurementReceiptInvoiceMatchList";
    public static final String PROPERTY_RCGITRANSACTIONSLIST = "rCGITransactionsList";
    public static final String PROPERTY_SALESCOMMISSIONDETAILLIST = "salesCommissionDetailList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINELIST = "timeAndExpenseSheetLineList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINEVLIST = "timeAndExpenseSheetLineVList";

    public InvoiceLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_FINANCIALINVOICELINE, false);
        setDefaultValue(PROPERTY_INVOICEDQUANTITY, new BigDecimal(1));
        setDefaultValue(PROPERTY_DESCRIPTIONONLY, false);
        setDefaultValue(PROPERTY_STANDARDPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_EXCLUDEFORWITHHOLDING, false);
        setDefaultValue(PROPERTY_EDITLINEAMOUNT, false);
        setDefaultValue(PROPERTY_GROSSAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_GROSSUNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_BASEGROSSUNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEFERRED, false);
        setDefaultValue(PROPERTY_EXPLODE, false);
        setDefaultValue(PROPERTY_INVOICELINEBOMPARENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEOFFERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINETAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNINVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTPOINVOICEMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTRECEIPTINVOICEMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGITRANSACTIONSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONDETAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST, new ArrayList<Object>());
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

    public Invoice getInvoice() {
        return (Invoice) get(PROPERTY_INVOICE);
    }

    public void setInvoice(Invoice invoice) {
        set(PROPERTY_INVOICE, invoice);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public ShipmentInOutLine getGoodsShipmentLine() {
        return (ShipmentInOutLine) get(PROPERTY_GOODSSHIPMENTLINE);
    }

    public void setGoodsShipmentLine(ShipmentInOutLine goodsShipmentLine) {
        set(PROPERTY_GOODSSHIPMENTLINE, goodsShipmentLine);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isFinancialInvoiceLine() {
        return (Boolean) get(PROPERTY_FINANCIALINVOICELINE);
    }

    public void setFinancialInvoiceLine(Boolean financialInvoiceLine) {
        set(PROPERTY_FINANCIALINVOICELINE, financialInvoiceLine);
    }

    public GLItem getAccount() {
        return (GLItem) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(GLItem account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getInvoicedQuantity() {
        return (BigDecimal) get(PROPERTY_INVOICEDQUANTITY);
    }

    public void setInvoicedQuantity(BigDecimal invoicedQuantity) {
        set(PROPERTY_INVOICEDQUANTITY, invoicedQuantity);
    }

    public BigDecimal getListPrice() {
        return (BigDecimal) get(PROPERTY_LISTPRICE);
    }

    public void setListPrice(BigDecimal listPrice) {
        set(PROPERTY_LISTPRICE, listPrice);
    }

    public BigDecimal getUnitPrice() {
        return (BigDecimal) get(PROPERTY_UNITPRICE);
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        set(PROPERTY_UNITPRICE, unitPrice);
    }

    public BigDecimal getPriceLimit() {
        return (BigDecimal) get(PROPERTY_PRICELIMIT);
    }

    public void setPriceLimit(BigDecimal priceLimit) {
        set(PROPERTY_PRICELIMIT, priceLimit);
    }

    public BigDecimal getLineNetAmount() {
        return (BigDecimal) get(PROPERTY_LINENETAMOUNT);
    }

    public void setLineNetAmount(BigDecimal lineNetAmount) {
        set(PROPERTY_LINENETAMOUNT, lineNetAmount);
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

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public ResourceAssignment getResourceAssignment() {
        return (ResourceAssignment) get(PROPERTY_RESOURCEASSIGNMENT);
    }

    public void setResourceAssignment(ResourceAssignment resourceAssignment) {
        set(PROPERTY_RESOURCEASSIGNMENT, resourceAssignment);
    }

    public BigDecimal getTaxAmount() {
        return (BigDecimal) get(PROPERTY_TAXAMOUNT);
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        set(PROPERTY_TAXAMOUNT, taxAmount);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public Boolean isDescriptionOnly() {
        return (Boolean) get(PROPERTY_DESCRIPTIONONLY);
    }

    public void setDescriptionOnly(Boolean descriptionOnly) {
        set(PROPERTY_DESCRIPTIONONLY, descriptionOnly);
    }

    public BigDecimal getOrderQuantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        set(PROPERTY_ORDERQUANTITY, orderQuantity);
    }

    public ProductUOM getOrderUOM() {
        return (ProductUOM) get(PROPERTY_ORDERUOM);
    }

    public void setOrderUOM(ProductUOM orderUOM) {
        set(PROPERTY_ORDERUOM, orderUOM);
    }

    public InvoiceDiscount getInvoiceDiscount() {
        return (InvoiceDiscount) get(PROPERTY_INVOICEDISCOUNT);
    }

    public void setInvoiceDiscount(InvoiceDiscount invoiceDiscount) {
        set(PROPERTY_INVOICEDISCOUNT, invoiceDiscount);
    }

    public ProjectLine getProjectLine() {
        return (ProjectLine) get(PROPERTY_PROJECTLINE);
    }

    public void setProjectLine(ProjectLine projectLine) {
        set(PROPERTY_PROJECTLINE, projectLine);
    }

    public PriceAdjustment getPriceAdjustment() {
        return (PriceAdjustment) get(PROPERTY_PRICEADJUSTMENT);
    }

    public void setPriceAdjustment(PriceAdjustment priceAdjustment) {
        set(PROPERTY_PRICEADJUSTMENT, priceAdjustment);
    }

    public BigDecimal getStandardPrice() {
        return (BigDecimal) get(PROPERTY_STANDARDPRICE);
    }

    public void setStandardPrice(BigDecimal standardPrice) {
        set(PROPERTY_STANDARDPRICE, standardPrice);
    }

    public Boolean isExcludeforwithholding() {
        return (Boolean) get(PROPERTY_EXCLUDEFORWITHHOLDING);
    }

    public void setExcludeforwithholding(Boolean excludeforwithholding) {
        set(PROPERTY_EXCLUDEFORWITHHOLDING, excludeforwithholding);
    }

    public Boolean isEditLineAmount() {
        return (Boolean) get(PROPERTY_EDITLINEAMOUNT);
    }

    public void setEditLineAmount(Boolean editLineAmount) {
        set(PROPERTY_EDITLINEAMOUNT, editLineAmount);
    }

    public BigDecimal getTaxableAmount() {
        return (BigDecimal) get(PROPERTY_TAXABLEAMOUNT);
    }

    public void setTaxableAmount(BigDecimal taxableAmount) {
        set(PROPERTY_TAXABLEAMOUNT, taxableAmount);
    }

    public BigDecimal getGrossAmount() {
        return (BigDecimal) get(PROPERTY_GROSSAMOUNT);
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        set(PROPERTY_GROSSAMOUNT, grossAmount);
    }

    public BigDecimal getGrossUnitPrice() {
        return (BigDecimal) get(PROPERTY_GROSSUNITPRICE);
    }

    public void setGrossUnitPrice(BigDecimal grossUnitPrice) {
        set(PROPERTY_GROSSUNITPRICE, grossUnitPrice);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Long getPeriodNumber() {
        return (Long) get(PROPERTY_PERIODNUMBER);
    }

    public void setPeriodNumber(Long periodNumber) {
        set(PROPERTY_PERIODNUMBER, periodNumber);
    }

    public BigDecimal getBaseGrossUnitPrice() {
        return (BigDecimal) get(PROPERTY_BASEGROSSUNITPRICE);
    }

    public void setBaseGrossUnitPrice(BigDecimal baseGrossUnitPrice) {
        set(PROPERTY_BASEGROSSUNITPRICE, baseGrossUnitPrice);
    }

    public Asset getAsset() {
        return (Asset) get(PROPERTY_ASSET);
    }

    public void setAsset(Asset asset) {
        set(PROPERTY_ASSET, asset);
    }

    public String getDeferredPlanType() {
        return (String) get(PROPERTY_DEFERREDPLANTYPE);
    }

    public void setDeferredPlanType(String deferredPlanType) {
        set(PROPERTY_DEFERREDPLANTYPE, deferredPlanType);
    }

    public BigDecimal getGrossListPrice() {
        return (BigDecimal) get(PROPERTY_GROSSLISTPRICE);
    }

    public void setGrossListPrice(BigDecimal grossListPrice) {
        set(PROPERTY_GROSSLISTPRICE, grossListPrice);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Boolean isDeferred() {
        return (Boolean) get(PROPERTY_DEFERRED);
    }

    public void setDeferred(Boolean deferred) {
        set(PROPERTY_DEFERRED, deferred);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public Costcenter getCostcenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostcenter(Costcenter costcenter) {
        set(PROPERTY_COSTCENTER, costcenter);
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

    public Boolean isExplode() {
        return (Boolean) get(PROPERTY_EXPLODE);
    }

    public void setExplode(Boolean explode) {
        set(PROPERTY_EXPLODE, explode);
    }

    public InvoiceLine getBOMParent() {
        return (InvoiceLine) get(PROPERTY_BOMPARENT);
    }

    public void setBOMParent(InvoiceLine bOMParent) {
        set(PROPERTY_BOMPARENT, bOMParent);
    }

    public Long getEpcgNoofpacks() {
        return (Long) get(PROPERTY_EPCGNOOFPACKS);
    }

    public void setEpcgNoofpacks(Long epcgNoofpacks) {
        set(PROPERTY_EPCGNOOFPACKS, epcgNoofpacks);
    }

    public BigDecimal getEpcgNoofpackages() {
        return (BigDecimal) get(PROPERTY_EPCGNOOFPACKAGES);
    }

    public void setEpcgNoofpackages(BigDecimal epcgNoofpackages) {
        set(PROPERTY_EPCGNOOFPACKAGES, epcgNoofpackages);
    }

    public EPCG_Packaging getEpcgPackaging() {
        return (EPCG_Packaging) get(PROPERTY_EPCGPACKAGING);
    }

    public void setEpcgPackaging(EPCG_Packaging epcgPackaging) {
        set(PROPERTY_EPCGPACKAGING, epcgPackaging);
    }

    public String getEpcgProductalias() {
        return (String) get(PROPERTY_EPCGPRODUCTALIAS);
    }

    public void setEpcgProductalias(String epcgProductalias) {
        set(PROPERTY_EPCGPRODUCTALIAS, epcgProductalias);
    }

    public String getEpcgProlistalias() {
        return (String) get(PROPERTY_EPCGPROLISTALIAS);
    }

    public void setEpcgProlistalias(String epcgProlistalias) {
        set(PROPERTY_EPCGPROLISTALIAS, epcgProlistalias);
    }

    public BigDecimal getRcfrFreight() {
        return (BigDecimal) get(PROPERTY_RCFRFREIGHT);
    }

    public void setRcfrFreight(BigDecimal rcfrFreight) {
        set(PROPERTY_RCFRFREIGHT, rcfrFreight);
    }

    public BigDecimal getRcfrInsurance() {
        return (BigDecimal) get(PROPERTY_RCFRINSURANCE);
    }

    public void setRcfrInsurance(BigDecimal rcfrInsurance) {
        set(PROPERTY_RCFRINSURANCE, rcfrInsurance);
    }

    public BigDecimal getRcfrNetrate() {
        return (BigDecimal) get(PROPERTY_RCFRNETRATE);
    }

    public void setRcfrNetrate(BigDecimal rcfrNetrate) {
        set(PROPERTY_RCFRNETRATE, rcfrNetrate);
    }

    public BigDecimal getRcfrNetunitrate() {
        return (BigDecimal) get(PROPERTY_RCFRNETUNITRATE);
    }

    public void setRcfrNetunitrate(BigDecimal rcfrNetunitrate) {
        set(PROPERTY_RCFRNETUNITRATE, rcfrNetunitrate);
    }

    public Invoice getRCOBReferenceinvoice() {
        return (Invoice) get(PROPERTY_RCOBREFERENCEINVOICE);
    }

    public void setRCOBReferenceinvoice(Invoice rCOBReferenceinvoice) {
        set(PROPERTY_RCOBREFERENCEINVOICE, rCOBReferenceinvoice);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineBOMParentIDList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINEBOMPARENTIDLIST);
    }

    public void setInvoiceLineBOMParentIDList(List<InvoiceLine> invoiceLineBOMParentIDList) {
        set(PROPERTY_INVOICELINEBOMPARENTIDLIST, invoiceLineBOMParentIDList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineAccountingDimension> getInvoiceLineAccountingDimensionList() {
        return (List<InvoiceLineAccountingDimension>) get(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInvoiceLineAccountingDimensionList(List<InvoiceLineAccountingDimension> invoiceLineAccountingDimensionList) {
        set(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, invoiceLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineOffer> getInvoiceLineOfferList() {
        return (List<InvoiceLineOffer>) get(PROPERTY_INVOICELINEOFFERLIST);
    }

    public void setInvoiceLineOfferList(List<InvoiceLineOffer> invoiceLineOfferList) {
        set(PROPERTY_INVOICELINEOFFERLIST, invoiceLineOfferList);
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
    public List<ProductionRunInvoiceLine> getManufacturingProductionRunInvoiceLineList() {
        return (List<ProductionRunInvoiceLine>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNINVOICELINELIST);
    }

    public void setManufacturingProductionRunInvoiceLineList(List<ProductionRunInvoiceLine> manufacturingProductionRunInvoiceLineList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNINVOICELINELIST, manufacturingProductionRunInvoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Costing> getMaterialMgmtCostingList() {
        return (List<Costing>) get(PROPERTY_MATERIALMGMTCOSTINGLIST);
    }

    public void setMaterialMgmtCostingList(List<Costing> materialMgmtCostingList) {
        set(PROPERTY_MATERIALMGMTCOSTINGLIST, materialMgmtCostingList);
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
    public List<RCGITransactions> getRCGITransactionsList() {
        return (List<RCGITransactions>) get(PROPERTY_RCGITRANSACTIONSLIST);
    }

    public void setRCGITransactionsList(List<RCGITransactions> rCGITransactionsList) {
        set(PROPERTY_RCGITRANSACTIONSLIST, rCGITransactionsList);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionDetail> getSalesCommissionDetailList() {
        return (List<CommissionDetail>) get(PROPERTY_SALESCOMMISSIONDETAILLIST);
    }

    public void setSalesCommissionDetailList(List<CommissionDetail> salesCommissionDetailList) {
        set(PROPERTY_SALESCOMMISSIONDETAILLIST, salesCommissionDetailList);
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

}
