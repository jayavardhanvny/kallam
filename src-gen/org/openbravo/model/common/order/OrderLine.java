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
package org.openbravo.model.common.order;

import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.redcarpet.epcg.data.EPCG_Packaging;
import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.EpcgMof;
import com.redcarpet.epcg.data.EpcgProformaline;
import com.redcarpet.epcg.data.EpcgVariant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.OrderLineTax;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.enterprise.WarehouseRule;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductCategory;
import org.openbravo.model.common.plm.ProductUOM;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.gl.GLCharge;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.materialmgmt.onhandquantity.PrereservationManualPickEdit;
import org.openbravo.model.materialmgmt.onhandquantity.Reservation;
import org.openbravo.model.materialmgmt.onhandquantity.ReservationManualPickEdit;
import org.openbravo.model.materialmgmt.onhandquantity.ReservationStock;
import org.openbravo.model.materialmgmt.onhandquantity.SOLReservedStock;
import org.openbravo.model.materialmgmt.transaction.ReturnMaterialReceiptPickEdit;
import org.openbravo.model.materialmgmt.transaction.ReturnMaterialShipmentPickEdit;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.model.mrp.ProductionRunLine;
import org.openbravo.model.mrp.PurchasingRunLine;
import org.openbravo.model.pricing.priceadjustment.PriceAdjustment;
import org.openbravo.model.procurement.POInvoiceMatch;
import org.openbravo.model.procurement.RequisitionPOMatch;
import org.openbravo.model.project.Project;
import org.openbravo.model.sales.CommissionDetail;
import org.openbravo.model.shipping.ShippingCompany;
import org.openbravo.model.timeandexpense.ResourceAssignment;
import org.openbravo.model.timeandexpense.SheetLine;
import org.openbravo.model.timeandexpense.SheetLineV;
/**
 * Entity class for entity OrderLine (stored in table C_OrderLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OrderLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_OrderLine";
    public static final String ENTITY_NAME = "OrderLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_ORDERDATE = "orderDate";
    public static final String PROPERTY_SCHEDULEDDELIVERYDATE = "scheduledDeliveryDate";
    public static final String PROPERTY_DATEDELIVERED = "dateDelivered";
    public static final String PROPERTY_INVOICEDATE = "invoiceDate";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_DIRECTSHIPMENT = "directShipment";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_ORDEREDQUANTITY = "orderedQuantity";
    public static final String PROPERTY_RESERVEDQUANTITY = "reservedQuantity";
    public static final String PROPERTY_DELIVEREDQUANTITY = "deliveredQuantity";
    public static final String PROPERTY_INVOICEDQUANTITY = "invoicedQuantity";
    public static final String PROPERTY_SHIPPINGCOMPANY = "shippingCompany";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_LISTPRICE = "listPrice";
    public static final String PROPERTY_UNITPRICE = "unitPrice";
    public static final String PROPERTY_PRICELIMIT = "priceLimit";
    public static final String PROPERTY_LINENETAMOUNT = "lineNetAmount";
    public static final String PROPERTY_DISCOUNT = "discount";
    public static final String PROPERTY_FREIGHTAMOUNT = "freightAmount";
    public static final String PROPERTY_CHARGE = "charge";
    public static final String PROPERTY_CHARGEAMOUNT = "chargeAmount";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_RESOURCEASSIGNMENT = "resourceAssignment";
    public static final String PROPERTY_SOPOREFERENCE = "sOPOReference";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_DESCRIPTIONONLY = "descriptionOnly";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_ORDERUOM = "orderUOM";
    public static final String PROPERTY_PRICEADJUSTMENT = "priceAdjustment";
    public static final String PROPERTY_STANDARDPRICE = "standardPrice";
    public static final String PROPERTY_CANCELPRICEADJUSTMENT = "cancelPriceAdjustment";
    public static final String PROPERTY_ORDERDISCOUNT = "orderDiscount";
    public static final String PROPERTY_EDITLINEAMOUNT = "editLineAmount";
    public static final String PROPERTY_TAXABLEAMOUNT = "taxableAmount";
    public static final String PROPERTY_GOODSSHIPMENTLINE = "goodsShipmentLine";
    public static final String PROPERTY_RETURNREASON = "returnReason";
    public static final String PROPERTY_GROSSUNITPRICE = "grossUnitPrice";
    public static final String PROPERTY_LINEGROSSAMOUNT = "lineGrossAmount";
    public static final String PROPERTY_GROSSLISTPRICE = "grossListPrice";
    public static final String PROPERTY_COSTCENTER = "costcenter";
    public static final String PROPERTY_BASEGROSSUNITPRICE = "baseGrossUnitPrice";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_WAREHOUSERULE = "warehouseRule";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_QUOTATIONLINE = "quotationLine";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_CREATERESERVATION = "createReservation";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_RESERVATIONSTATUS = "reservationStatus";
    public static final String PROPERTY_MANAGERESERVATION = "manageReservation";
    public static final String PROPERTY_MANAGEPRERESERVATION = "managePrereservation";
    public static final String PROPERTY_EXPLODE = "explode";
    public static final String PROPERTY_BOMPARENT = "bOMParent";
    public static final String PROPERTY_EPCGPACKAGING = "epcgPackaging";
    public static final String PROPERTY_EPCGNOOFPACKAGES = "epcgNoofpackages";
    public static final String PROPERTY_RCFRFREIGHT = "rcfrFreight";
    public static final String PROPERTY_RCFRINSURANCE = "rcfrInsurance";
    public static final String PROPERTY_RCFRPNF = "rcfrPnf";
    public static final String PROPERTY_RCFRDISCO = "rcfrDisco";
    public static final String PROPERTY_RCFRPRICE = "rcfrPrice";
    public static final String PROPERTY_EPCGMAKE = "epcgMake";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_EPCGVARIANT = "epcgVariant";
    public static final String PROPERTY_RCHRWARPYARNTYPE = "rchrWarpyarntype";
    public static final String PROPERTY_EPCGCOSTENQUIRY = "epcgCostenquiry";
    public static final String PROPERTY_EPCGCONEWT = "epcgConewt";
    public static final String PROPERTY_EPCGCONESFORBAGS = "epcgConesforbags";
    public static final String PROPERTY_EPCGCONETYPE = "epcgConetype";
    public static final String PROPERTY_EPCGPROFORMQTY = "epcgProformqty";
    public static final String PROPERTY_RCFRNETUNITRATE = "rcfrNetunitrate";
    public static final String PROPERTY_EPCGMOFLIST = "epcgMofList";
    public static final String PROPERTY_EPCGPROFORMALINELIST = "epcgProformalineList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_MRPPRODUCTIONRUNLINELIST = "mRPProductionRunLineList";
    public static final String PROPERTY_MRPPURCHASINGRUNLINELIST = "mRPPurchasingRunLineList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONLIST = "materialMgmtReservationList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST = "materialMgmtReservationStockList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST = "materialMgmtShipmentInOutLineList";
    public static final String PROPERTY_ORDERLINESOPOREFERENCELIST = "orderLineSOPOReferenceList";
    public static final String PROPERTY_ORDERLINEQUOTATIONLINELIST = "orderLineQuotationLineList";
    public static final String PROPERTY_ORDERLINEBOMPARENTIDLIST = "orderLineBOMParentIDList";
    public static final String PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST = "orderLineAccountingDimensionList";
    public static final String PROPERTY_ORDERLINEOFFERLIST = "orderLineOfferList";
    public static final String PROPERTY_ORDERLINETAXLIST = "orderLineTaxList";
    public static final String PROPERTY_PRERESERVATIONMANUALPICKEDITPURCHASEORDERLINELIST = "prereservationManualPickEditPurchaseOrderLineList";
    public static final String PROPERTY_PRERESERVATIONMANUALPICKEDITSALESORDERLINELIST = "prereservationManualPickEditSalesOrderLineList";
    public static final String PROPERTY_PROCUREMENTPOINVOICEMATCHLIST = "procurementPOInvoiceMatchList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONPOMATCHLIST = "procurementRequisitionPOMatchList";
    public static final String PROPERTY_RESERVATIONMANUALPICKEDITLIST = "reservationManualPickEditList";
    public static final String PROPERTY_RESERVATIONMANUALPICKEDITSALESORDERLINELIST = "reservationManualPickEditSalesOrderLineList";
    public static final String PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST = "returnMaterialOrderPickEditLinesList";
    public static final String PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST = "returnMaterialReceiptPickEditList";
    public static final String PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST = "returnMaterialShipmentPickEditList";
    public static final String PROPERTY_SOLRESERVEDSTOCKLIST = "sOLReservedStockList";
    public static final String PROPERTY_SALESCOMMISSIONDETAILLIST = "salesCommissionDetailList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINELIST = "timeAndExpenseSheetLineList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINEVLIST = "timeAndExpenseSheetLineVList";

    public OrderLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DIRECTSHIPMENT, false);
        setDefaultValue(PROPERTY_ORDEREDQUANTITY, new BigDecimal(1));
        setDefaultValue(PROPERTY_DISCOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_CHARGEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DESCRIPTIONONLY, false);
        setDefaultValue(PROPERTY_STANDARDPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_CANCELPRICEADJUSTMENT, false);
        setDefaultValue(PROPERTY_EDITLINEAMOUNT, false);
        setDefaultValue(PROPERTY_GROSSUNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_LINEGROSSAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_BASEGROSSUNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_MANAGERESERVATION, false);
        setDefaultValue(PROPERTY_MANAGEPRERESERVATION, false);
        setDefaultValue(PROPERTY_EXPLODE, false);
        setDefaultValue(PROPERTY_RCFRFREIGHT, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCFRINSURANCE, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCFRPNF, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCFRDISCO, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCFRPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_EPCGCONEWT, (long) 0);
        setDefaultValue(PROPERTY_EPCGCONESFORBAGS, (long) 0);
        setDefaultValue(PROPERTY_EPCGPROFORMQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_EPCGMOFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPROFORMALINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINESOPOREFERENCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEQUOTATIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEBOMPARENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEOFFERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINETAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRERESERVATIONMANUALPICKEDITPURCHASEORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRERESERVATIONMANUALPICKEDITSALESORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTPOINVOICEMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONPOMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESERVATIONMANUALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESERVATIONMANUALPICKEDITSALESORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SOLRESERVEDSTOCKLIST, new ArrayList<Object>());
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

    public Order getSalesOrder() {
        return (Order) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Order salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
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

    public Date getOrderDate() {
        return (Date) get(PROPERTY_ORDERDATE);
    }

    public void setOrderDate(Date orderDate) {
        set(PROPERTY_ORDERDATE, orderDate);
    }

    public Date getScheduledDeliveryDate() {
        return (Date) get(PROPERTY_SCHEDULEDDELIVERYDATE);
    }

    public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
        set(PROPERTY_SCHEDULEDDELIVERYDATE, scheduledDeliveryDate);
    }

    public Date getDateDelivered() {
        return (Date) get(PROPERTY_DATEDELIVERED);
    }

    public void setDateDelivered(Date dateDelivered) {
        set(PROPERTY_DATEDELIVERED, dateDelivered);
    }

    public Date getInvoiceDate() {
        return (Date) get(PROPERTY_INVOICEDATE);
    }

    public void setInvoiceDate(Date invoiceDate) {
        set(PROPERTY_INVOICEDATE, invoiceDate);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public Boolean isDirectShipment() {
        return (Boolean) get(PROPERTY_DIRECTSHIPMENT);
    }

    public void setDirectShipment(Boolean directShipment) {
        set(PROPERTY_DIRECTSHIPMENT, directShipment);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getOrderedQuantity() {
        return (BigDecimal) get(PROPERTY_ORDEREDQUANTITY);
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        set(PROPERTY_ORDEREDQUANTITY, orderedQuantity);
    }

    public BigDecimal getReservedQuantity() {
        return (BigDecimal) get(PROPERTY_RESERVEDQUANTITY);
    }

    public void setReservedQuantity(BigDecimal reservedQuantity) {
        set(PROPERTY_RESERVEDQUANTITY, reservedQuantity);
    }

    public BigDecimal getDeliveredQuantity() {
        return (BigDecimal) get(PROPERTY_DELIVEREDQUANTITY);
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        set(PROPERTY_DELIVEREDQUANTITY, deliveredQuantity);
    }

    public BigDecimal getInvoicedQuantity() {
        return (BigDecimal) get(PROPERTY_INVOICEDQUANTITY);
    }

    public void setInvoicedQuantity(BigDecimal invoicedQuantity) {
        set(PROPERTY_INVOICEDQUANTITY, invoicedQuantity);
    }

    public ShippingCompany getShippingCompany() {
        return (ShippingCompany) get(PROPERTY_SHIPPINGCOMPANY);
    }

    public void setShippingCompany(ShippingCompany shippingCompany) {
        set(PROPERTY_SHIPPINGCOMPANY, shippingCompany);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
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

    public BigDecimal getDiscount() {
        return (BigDecimal) get(PROPERTY_DISCOUNT);
    }

    public void setDiscount(BigDecimal discount) {
        set(PROPERTY_DISCOUNT, discount);
    }

    public BigDecimal getFreightAmount() {
        return (BigDecimal) get(PROPERTY_FREIGHTAMOUNT);
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        set(PROPERTY_FREIGHTAMOUNT, freightAmount);
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

    public OrderLine getSOPOReference() {
        return (OrderLine) get(PROPERTY_SOPOREFERENCE);
    }

    public void setSOPOReference(OrderLine sOPOReference) {
        set(PROPERTY_SOPOREFERENCE, sOPOReference);
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

    public Boolean isCancelPriceAdjustment() {
        return (Boolean) get(PROPERTY_CANCELPRICEADJUSTMENT);
    }

    public void setCancelPriceAdjustment(Boolean cancelPriceAdjustment) {
        set(PROPERTY_CANCELPRICEADJUSTMENT, cancelPriceAdjustment);
    }

    public OrderDiscount getOrderDiscount() {
        return (OrderDiscount) get(PROPERTY_ORDERDISCOUNT);
    }

    public void setOrderDiscount(OrderDiscount orderDiscount) {
        set(PROPERTY_ORDERDISCOUNT, orderDiscount);
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

    public ShipmentInOutLine getGoodsShipmentLine() {
        return (ShipmentInOutLine) get(PROPERTY_GOODSSHIPMENTLINE);
    }

    public void setGoodsShipmentLine(ShipmentInOutLine goodsShipmentLine) {
        set(PROPERTY_GOODSSHIPMENTLINE, goodsShipmentLine);
    }

    public ReturnReason getReturnReason() {
        return (ReturnReason) get(PROPERTY_RETURNREASON);
    }

    public void setReturnReason(ReturnReason returnReason) {
        set(PROPERTY_RETURNREASON, returnReason);
    }

    public BigDecimal getGrossUnitPrice() {
        return (BigDecimal) get(PROPERTY_GROSSUNITPRICE);
    }

    public void setGrossUnitPrice(BigDecimal grossUnitPrice) {
        set(PROPERTY_GROSSUNITPRICE, grossUnitPrice);
    }

    public BigDecimal getLineGrossAmount() {
        return (BigDecimal) get(PROPERTY_LINEGROSSAMOUNT);
    }

    public void setLineGrossAmount(BigDecimal lineGrossAmount) {
        set(PROPERTY_LINEGROSSAMOUNT, lineGrossAmount);
    }

    public BigDecimal getGrossListPrice() {
        return (BigDecimal) get(PROPERTY_GROSSLISTPRICE);
    }

    public void setGrossListPrice(BigDecimal grossListPrice) {
        set(PROPERTY_GROSSLISTPRICE, grossListPrice);
    }

    public Costcenter getCostcenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostcenter(Costcenter costcenter) {
        set(PROPERTY_COSTCENTER, costcenter);
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

    public WarehouseRule getWarehouseRule() {
        return (WarehouseRule) get(PROPERTY_WAREHOUSERULE);
    }

    public void setWarehouseRule(WarehouseRule warehouseRule) {
        set(PROPERTY_WAREHOUSERULE, warehouseRule);
    }

    public UserDimension1 getStDimension() {
        return (UserDimension1) get(PROPERTY_STDIMENSION);
    }

    public void setStDimension(UserDimension1 stDimension) {
        set(PROPERTY_STDIMENSION, stDimension);
    }

    public OrderLine getQuotationLine() {
        return (OrderLine) get(PROPERTY_QUOTATIONLINE);
    }

    public void setQuotationLine(OrderLine quotationLine) {
        set(PROPERTY_QUOTATIONLINE, quotationLine);
    }

    public UserDimension2 getNdDimension() {
        return (UserDimension2) get(PROPERTY_NDDIMENSION);
    }

    public void setNdDimension(UserDimension2 ndDimension) {
        set(PROPERTY_NDDIMENSION, ndDimension);
    }

    public String getCreateReservation() {
        return (String) get(PROPERTY_CREATERESERVATION);
    }

    public void setCreateReservation(String createReservation) {
        set(PROPERTY_CREATERESERVATION, createReservation);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public String getReservationStatus() {
        return (String) get(PROPERTY_RESERVATIONSTATUS);
    }

    public void setReservationStatus(String reservationStatus) {
        set(PROPERTY_RESERVATIONSTATUS, reservationStatus);
    }

    public Boolean isManageReservation() {
        return (Boolean) get(PROPERTY_MANAGERESERVATION);
    }

    public void setManageReservation(Boolean manageReservation) {
        set(PROPERTY_MANAGERESERVATION, manageReservation);
    }

    public Boolean isManagePrereservation() {
        return (Boolean) get(PROPERTY_MANAGEPRERESERVATION);
    }

    public void setManagePrereservation(Boolean managePrereservation) {
        set(PROPERTY_MANAGEPRERESERVATION, managePrereservation);
    }

    public Boolean isExplode() {
        return (Boolean) get(PROPERTY_EXPLODE);
    }

    public void setExplode(Boolean explode) {
        set(PROPERTY_EXPLODE, explode);
    }

    public OrderLine getBOMParent() {
        return (OrderLine) get(PROPERTY_BOMPARENT);
    }

    public void setBOMParent(OrderLine bOMParent) {
        set(PROPERTY_BOMPARENT, bOMParent);
    }

    public EPCG_Packaging getEpcgPackaging() {
        return (EPCG_Packaging) get(PROPERTY_EPCGPACKAGING);
    }

    public void setEpcgPackaging(EPCG_Packaging epcgPackaging) {
        set(PROPERTY_EPCGPACKAGING, epcgPackaging);
    }

    public BigDecimal getEpcgNoofpackages() {
        return (BigDecimal) get(PROPERTY_EPCGNOOFPACKAGES);
    }

    public void setEpcgNoofpackages(BigDecimal epcgNoofpackages) {
        set(PROPERTY_EPCGNOOFPACKAGES, epcgNoofpackages);
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

    public BigDecimal getRcfrPnf() {
        return (BigDecimal) get(PROPERTY_RCFRPNF);
    }

    public void setRcfrPnf(BigDecimal rcfrPnf) {
        set(PROPERTY_RCFRPNF, rcfrPnf);
    }

    public BigDecimal getRcfrDisco() {
        return (BigDecimal) get(PROPERTY_RCFRDISCO);
    }

    public void setRcfrDisco(BigDecimal rcfrDisco) {
        set(PROPERTY_RCFRDISCO, rcfrDisco);
    }

    public BigDecimal getRcfrPrice() {
        return (BigDecimal) get(PROPERTY_RCFRPRICE);
    }

    public void setRcfrPrice(BigDecimal rcfrPrice) {
        set(PROPERTY_RCFRPRICE, rcfrPrice);
    }

    public String getEpcgMake() {
        return (String) get(PROPERTY_EPCGMAKE);
    }

    public void setEpcgMake(String epcgMake) {
        set(PROPERTY_EPCGMAKE, epcgMake);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public EpcgVariant getEpcgVariant() {
        return (EpcgVariant) get(PROPERTY_EPCGVARIANT);
    }

    public void setEpcgVariant(EpcgVariant epcgVariant) {
        set(PROPERTY_EPCGVARIANT, epcgVariant);
    }

    public RCHRWarpyarntype getRchrWarpyarntype() {
        return (RCHRWarpyarntype) get(PROPERTY_RCHRWARPYARNTYPE);
    }

    public void setRchrWarpyarntype(RCHRWarpyarntype rchrWarpyarntype) {
        set(PROPERTY_RCHRWARPYARNTYPE, rchrWarpyarntype);
    }

    public EpcgCostenquiry getEpcgCostenquiry() {
        return (EpcgCostenquiry) get(PROPERTY_EPCGCOSTENQUIRY);
    }

    public void setEpcgCostenquiry(EpcgCostenquiry epcgCostenquiry) {
        set(PROPERTY_EPCGCOSTENQUIRY, epcgCostenquiry);
    }

    public Long getEpcgConewt() {
        return (Long) get(PROPERTY_EPCGCONEWT);
    }

    public void setEpcgConewt(Long epcgConewt) {
        set(PROPERTY_EPCGCONEWT, epcgConewt);
    }

    public Long getEpcgConesforbags() {
        return (Long) get(PROPERTY_EPCGCONESFORBAGS);
    }

    public void setEpcgConesforbags(Long epcgConesforbags) {
        set(PROPERTY_EPCGCONESFORBAGS, epcgConesforbags);
    }

    public String getEpcgConetype() {
        return (String) get(PROPERTY_EPCGCONETYPE);
    }

    public void setEpcgConetype(String epcgConetype) {
        set(PROPERTY_EPCGCONETYPE, epcgConetype);
    }

    public BigDecimal getEpcgProformqty() {
        return (BigDecimal) get(PROPERTY_EPCGPROFORMQTY);
    }

    public void setEpcgProformqty(BigDecimal epcgProformqty) {
        set(PROPERTY_EPCGPROFORMQTY, epcgProformqty);
    }

    public BigDecimal getRcfrNetunitrate() {
        return (BigDecimal) get(PROPERTY_RCFRNETUNITRATE);
    }

    public void setRcfrNetunitrate(BigDecimal rcfrNetunitrate) {
        set(PROPERTY_RCFRNETUNITRATE, rcfrNetunitrate);
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
    public List<InvoiceLine> getInvoiceLineList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunLine> getMRPProductionRunLineList() {
        return (List<ProductionRunLine>) get(PROPERTY_MRPPRODUCTIONRUNLINELIST);
    }

    public void setMRPProductionRunLineList(List<ProductionRunLine> mRPProductionRunLineList) {
        set(PROPERTY_MRPPRODUCTIONRUNLINELIST, mRPProductionRunLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRunLine> getMRPPurchasingRunLineList() {
        return (List<PurchasingRunLine>) get(PROPERTY_MRPPURCHASINGRUNLINELIST);
    }

    public void setMRPPurchasingRunLineList(List<PurchasingRunLine> mRPPurchasingRunLineList) {
        set(PROPERTY_MRPPURCHASINGRUNLINELIST, mRPPurchasingRunLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getMaterialMgmtReservationList() {
        return (List<Reservation>) get(PROPERTY_MATERIALMGMTRESERVATIONLIST);
    }

    public void setMaterialMgmtReservationList(List<Reservation> materialMgmtReservationList) {
        set(PROPERTY_MATERIALMGMTRESERVATIONLIST, materialMgmtReservationList);
    }

    @SuppressWarnings("unchecked")
    public List<ReservationStock> getMaterialMgmtReservationStockList() {
        return (List<ReservationStock>) get(PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST);
    }

    public void setMaterialMgmtReservationStockList(List<ReservationStock> materialMgmtReservationStockList) {
        set(PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST, materialMgmtReservationStockList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOutLine> getMaterialMgmtShipmentInOutLineList() {
        return (List<ShipmentInOutLine>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST);
    }

    public void setMaterialMgmtShipmentInOutLineList(List<ShipmentInOutLine> materialMgmtShipmentInOutLineList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, materialMgmtShipmentInOutLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineSOPOReferenceList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINESOPOREFERENCELIST);
    }

    public void setOrderLineSOPOReferenceList(List<OrderLine> orderLineSOPOReferenceList) {
        set(PROPERTY_ORDERLINESOPOREFERENCELIST, orderLineSOPOReferenceList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineQuotationLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINEQUOTATIONLINELIST);
    }

    public void setOrderLineQuotationLineList(List<OrderLine> orderLineQuotationLineList) {
        set(PROPERTY_ORDERLINEQUOTATIONLINELIST, orderLineQuotationLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineBOMParentIDList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINEBOMPARENTIDLIST);
    }

    public void setOrderLineBOMParentIDList(List<OrderLine> orderLineBOMParentIDList) {
        set(PROPERTY_ORDERLINEBOMPARENTIDLIST, orderLineBOMParentIDList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineAccountingDimension> getOrderLineAccountingDimensionList() {
        return (List<OrderLineAccountingDimension>) get(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setOrderLineAccountingDimensionList(List<OrderLineAccountingDimension> orderLineAccountingDimensionList) {
        set(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, orderLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineOffer> getOrderLineOfferList() {
        return (List<OrderLineOffer>) get(PROPERTY_ORDERLINEOFFERLIST);
    }

    public void setOrderLineOfferList(List<OrderLineOffer> orderLineOfferList) {
        set(PROPERTY_ORDERLINEOFFERLIST, orderLineOfferList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineTax> getOrderLineTaxList() {
        return (List<OrderLineTax>) get(PROPERTY_ORDERLINETAXLIST);
    }

    public void setOrderLineTaxList(List<OrderLineTax> orderLineTaxList) {
        set(PROPERTY_ORDERLINETAXLIST, orderLineTaxList);
    }

    @SuppressWarnings("unchecked")
    public List<PrereservationManualPickEdit> getPrereservationManualPickEditPurchaseOrderLineList() {
        return (List<PrereservationManualPickEdit>) get(PROPERTY_PRERESERVATIONMANUALPICKEDITPURCHASEORDERLINELIST);
    }

    public void setPrereservationManualPickEditPurchaseOrderLineList(List<PrereservationManualPickEdit> prereservationManualPickEditPurchaseOrderLineList) {
        set(PROPERTY_PRERESERVATIONMANUALPICKEDITPURCHASEORDERLINELIST, prereservationManualPickEditPurchaseOrderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PrereservationManualPickEdit> getPrereservationManualPickEditSalesOrderLineList() {
        return (List<PrereservationManualPickEdit>) get(PROPERTY_PRERESERVATIONMANUALPICKEDITSALESORDERLINELIST);
    }

    public void setPrereservationManualPickEditSalesOrderLineList(List<PrereservationManualPickEdit> prereservationManualPickEditSalesOrderLineList) {
        set(PROPERTY_PRERESERVATIONMANUALPICKEDITSALESORDERLINELIST, prereservationManualPickEditSalesOrderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<POInvoiceMatch> getProcurementPOInvoiceMatchList() {
        return (List<POInvoiceMatch>) get(PROPERTY_PROCUREMENTPOINVOICEMATCHLIST);
    }

    public void setProcurementPOInvoiceMatchList(List<POInvoiceMatch> procurementPOInvoiceMatchList) {
        set(PROPERTY_PROCUREMENTPOINVOICEMATCHLIST, procurementPOInvoiceMatchList);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitionPOMatch> getProcurementRequisitionPOMatchList() {
        return (List<RequisitionPOMatch>) get(PROPERTY_PROCUREMENTREQUISITIONPOMATCHLIST);
    }

    public void setProcurementRequisitionPOMatchList(List<RequisitionPOMatch> procurementRequisitionPOMatchList) {
        set(PROPERTY_PROCUREMENTREQUISITIONPOMATCHLIST, procurementRequisitionPOMatchList);
    }

    @SuppressWarnings("unchecked")
    public List<ReservationManualPickEdit> getReservationManualPickEditList() {
        return (List<ReservationManualPickEdit>) get(PROPERTY_RESERVATIONMANUALPICKEDITLIST);
    }

    public void setReservationManualPickEditList(List<ReservationManualPickEdit> reservationManualPickEditList) {
        set(PROPERTY_RESERVATIONMANUALPICKEDITLIST, reservationManualPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<ReservationManualPickEdit> getReservationManualPickEditSalesOrderLineList() {
        return (List<ReservationManualPickEdit>) get(PROPERTY_RESERVATIONMANUALPICKEDITSALESORDERLINELIST);
    }

    public void setReservationManualPickEditSalesOrderLineList(List<ReservationManualPickEdit> reservationManualPickEditSalesOrderLineList) {
        set(PROPERTY_RESERVATIONMANUALPICKEDITSALESORDERLINELIST, reservationManualPickEditSalesOrderLineList);
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
    public List<SOLReservedStock> getSOLReservedStockList() {
        return (List<SOLReservedStock>) get(PROPERTY_SOLRESERVEDSTOCKLIST);
    }

    public void setSOLReservedStockList(List<SOLReservedStock> sOLReservedStockList) {
        set(PROPERTY_SOLRESERVEDSTOCKLIST, sOLReservedStockList);
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
