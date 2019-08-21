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
package org.openbravo.model.procurement;

import com.redcarpet.goodsissue.data.RCGIDiLines;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import com.redcarpet.production.data.RCPRMachine;

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
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductUOM;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.mrp.ProductionRunLine;
import org.openbravo.model.mrp.PurchasingRunLine;
import org.openbravo.model.pricing.pricelist.PriceList;
/**
 * Entity class for entity ProcurementRequisitionLine (stored in table M_RequisitionLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RequisitionLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_RequisitionLine";
    public static final String ENTITY_NAME = "ProcurementRequisitionLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_REQUISITION = "requisition";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_LISTPRICE = "listPrice";
    public static final String PROPERTY_LINENETAMOUNT = "lineNetAmount";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_ORDERUOM = "orderUOM";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_REQUISITIONLINESTATUS = "requisitionLineStatus";
    public static final String PROPERTY_MATCHEDPOQTY = "matchedPOQty";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CHANGESTATUS = "changeStatus";
    public static final String PROPERTY_INTERNALNOTES = "internalNotes";
    public static final String PROPERTY_NOTESFORSUPPLIER = "notesForSupplier";
    public static final String PROPERTY_PLANNEDDATE = "plannedDate";
    public static final String PROPERTY_NEEDBYDATE = "needByDate";
    public static final String PROPERTY_UNITPRICE = "unitPrice";
    public static final String PROPERTY_DISCOUNT = "discount";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_LOCKEDBY = "lockedBy";
    public static final String PROPERTY_LOCKQTY = "lockQty";
    public static final String PROPERTY_LOCKPRICE = "lockPrice";
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_LOCKDATE = "lockDate";
    public static final String PROPERTY_LOCKCAUSE = "lockCause";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_GROSSUNITPRICE = "grossUnitPrice";
    public static final String PROPERTY_GROSSAMOUNT = "grossAmount";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_EPCGOPENQTY = "epcgOpenqty";
    public static final String PROPERTY_EPCGSTATUS = "epcgStatus";
    public static final String PROPERTY_RCPRMACHINE = "rcprMachine";
    public static final String PROPERTY_RCOBMOVEMENTQTY = "rcobMovementqty";
    public static final String PROPERTY_MRPPRODUCTIONRUNLINELIST = "mRPProductionRunLineList";
    public static final String PROPERTY_MRPPURCHASINGRUNLINELIST = "mRPPurchasingRunLineList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONPOMATCHLIST = "procurementRequisitionPOMatchList";
    public static final String PROPERTY_RCGIDILINESLIST = "rCGIDiLinesList";
    public static final String PROPERTY_RCGIGOODSISSUELINELIST = "rCGIGoodsIssueLineList";

    public RequisitionLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_QUANTITY, new BigDecimal(1));
        setDefaultValue(PROPERTY_REQUISITIONLINESTATUS, "O");
        setDefaultValue(PROPERTY_MATCHEDPOQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_CHANGESTATUS, false);
        setDefaultValue(PROPERTY_GROSSUNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_GROSSAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_EPCGOPENQTY, new BigDecimal(1));
        setDefaultValue(PROPERTY_EPCGSTATUS, "Open");
        setDefaultValue(PROPERTY_RCOBMOVEMENTQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONPOMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIGOODSISSUELINELIST, new ArrayList<Object>());
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

    public Requisition getRequisition() {
        return (Requisition) get(PROPERTY_REQUISITION);
    }

    public void setRequisition(Requisition requisition) {
        set(PROPERTY_REQUISITION, requisition);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public BigDecimal getListPrice() {
        return (BigDecimal) get(PROPERTY_LISTPRICE);
    }

    public void setListPrice(BigDecimal listPrice) {
        set(PROPERTY_LISTPRICE, listPrice);
    }

    public BigDecimal getLineNetAmount() {
        return (BigDecimal) get(PROPERTY_LINENETAMOUNT);
    }

    public void setLineNetAmount(BigDecimal lineNetAmount) {
        set(PROPERTY_LINENETAMOUNT, lineNetAmount);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public ProductUOM getOrderUOM() {
        return (ProductUOM) get(PROPERTY_ORDERUOM);
    }

    public void setOrderUOM(ProductUOM orderUOM) {
        set(PROPERTY_ORDERUOM, orderUOM);
    }

    public BigDecimal getOrderQuantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        set(PROPERTY_ORDERQUANTITY, orderQuantity);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public String getRequisitionLineStatus() {
        return (String) get(PROPERTY_REQUISITIONLINESTATUS);
    }

    public void setRequisitionLineStatus(String requisitionLineStatus) {
        set(PROPERTY_REQUISITIONLINESTATUS, requisitionLineStatus);
    }

    public BigDecimal getMatchedPOQty() {
        return (BigDecimal) get(PROPERTY_MATCHEDPOQTY);
    }

    public void setMatchedPOQty(BigDecimal matchedPOQty) {
        set(PROPERTY_MATCHEDPOQTY, matchedPOQty);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isChangeStatus() {
        return (Boolean) get(PROPERTY_CHANGESTATUS);
    }

    public void setChangeStatus(Boolean changeStatus) {
        set(PROPERTY_CHANGESTATUS, changeStatus);
    }

    public String getInternalNotes() {
        return (String) get(PROPERTY_INTERNALNOTES);
    }

    public void setInternalNotes(String internalNotes) {
        set(PROPERTY_INTERNALNOTES, internalNotes);
    }

    public String getNotesForSupplier() {
        return (String) get(PROPERTY_NOTESFORSUPPLIER);
    }

    public void setNotesForSupplier(String notesForSupplier) {
        set(PROPERTY_NOTESFORSUPPLIER, notesForSupplier);
    }

    public Date getPlannedDate() {
        return (Date) get(PROPERTY_PLANNEDDATE);
    }

    public void setPlannedDate(Date plannedDate) {
        set(PROPERTY_PLANNEDDATE, plannedDate);
    }

    public Date getNeedByDate() {
        return (Date) get(PROPERTY_NEEDBYDATE);
    }

    public void setNeedByDate(Date needByDate) {
        set(PROPERTY_NEEDBYDATE, needByDate);
    }

    public BigDecimal getUnitPrice() {
        return (BigDecimal) get(PROPERTY_UNITPRICE);
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        set(PROPERTY_UNITPRICE, unitPrice);
    }

    public BigDecimal getDiscount() {
        return (BigDecimal) get(PROPERTY_DISCOUNT);
    }

    public void setDiscount(BigDecimal discount) {
        set(PROPERTY_DISCOUNT, discount);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public User getLockedBy() {
        return (User) get(PROPERTY_LOCKEDBY);
    }

    public void setLockedBy(User lockedBy) {
        set(PROPERTY_LOCKEDBY, lockedBy);
    }

    public BigDecimal getLockQty() {
        return (BigDecimal) get(PROPERTY_LOCKQTY);
    }

    public void setLockQty(BigDecimal lockQty) {
        set(PROPERTY_LOCKQTY, lockQty);
    }

    public BigDecimal getLockPrice() {
        return (BigDecimal) get(PROPERTY_LOCKPRICE);
    }

    public void setLockPrice(BigDecimal lockPrice) {
        set(PROPERTY_LOCKPRICE, lockPrice);
    }

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public Date getLockDate() {
        return (Date) get(PROPERTY_LOCKDATE);
    }

    public void setLockDate(Date lockDate) {
        set(PROPERTY_LOCKDATE, lockDate);
    }

    public String getLockCause() {
        return (String) get(PROPERTY_LOCKCAUSE);
    }

    public void setLockCause(String lockCause) {
        set(PROPERTY_LOCKCAUSE, lockCause);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public BigDecimal getGrossUnitPrice() {
        return (BigDecimal) get(PROPERTY_GROSSUNITPRICE);
    }

    public void setGrossUnitPrice(BigDecimal grossUnitPrice) {
        set(PROPERTY_GROSSUNITPRICE, grossUnitPrice);
    }

    public BigDecimal getGrossAmount() {
        return (BigDecimal) get(PROPERTY_GROSSAMOUNT);
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        set(PROPERTY_GROSSAMOUNT, grossAmount);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public BigDecimal getEpcgOpenqty() {
        return (BigDecimal) get(PROPERTY_EPCGOPENQTY);
    }

    public void setEpcgOpenqty(BigDecimal epcgOpenqty) {
        set(PROPERTY_EPCGOPENQTY, epcgOpenqty);
    }

    public String getEpcgStatus() {
        return (String) get(PROPERTY_EPCGSTATUS);
    }

    public void setEpcgStatus(String epcgStatus) {
        set(PROPERTY_EPCGSTATUS, epcgStatus);
    }

    public RCPRMachine getRcprMachine() {
        return (RCPRMachine) get(PROPERTY_RCPRMACHINE);
    }

    public void setRcprMachine(RCPRMachine rcprMachine) {
        set(PROPERTY_RCPRMACHINE, rcprMachine);
    }

    public BigDecimal getRcobMovementqty() {
        return (BigDecimal) get(PROPERTY_RCOBMOVEMENTQTY);
    }

    public void setRcobMovementqty(BigDecimal rcobMovementqty) {
        set(PROPERTY_RCOBMOVEMENTQTY, rcobMovementqty);
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
    public List<RequisitionPOMatch> getProcurementRequisitionPOMatchList() {
        return (List<RequisitionPOMatch>) get(PROPERTY_PROCUREMENTREQUISITIONPOMATCHLIST);
    }

    public void setProcurementRequisitionPOMatchList(List<RequisitionPOMatch> procurementRequisitionPOMatchList) {
        set(PROPERTY_PROCUREMENTREQUISITIONPOMATCHLIST, procurementRequisitionPOMatchList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDiLines> getRCGIDiLinesList() {
        return (List<RCGIDiLines>) get(PROPERTY_RCGIDILINESLIST);
    }

    public void setRCGIDiLinesList(List<RCGIDiLines> rCGIDiLinesList) {
        set(PROPERTY_RCGIDILINESLIST, rCGIDiLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_GoodsIssue_Line> getRCGIGoodsIssueLineList() {
        return (List<RCGI_GoodsIssue_Line>) get(PROPERTY_RCGIGOODSISSUELINELIST);
    }

    public void setRCGIGoodsIssueLineList(List<RCGI_GoodsIssue_Line> rCGIGoodsIssueLineList) {
        set(PROPERTY_RCGIGOODSISSUELINELIST, rCGIGoodsIssueLineList);
    }

}
