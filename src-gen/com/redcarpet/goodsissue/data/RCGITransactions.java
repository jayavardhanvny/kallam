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
package com.redcarpet.goodsissue.data;

import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.redcarpet.epcg.data.ConeType;
import com.redcarpet.epcg.data.EPCG_Packaging;
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
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.materialmgmt.cost.CostingAlgorithm;
import org.openbravo.model.materialmgmt.transaction.InventoryCountLine;
/**
 * Entity class for entity RCGI_Transactions (stored in table RCGI_Transactions).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCGITransactions extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCGI_Transactions";
    public static final String ENTITY_NAME = "RCGI_Transactions";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MOVEMENTTYPE = "movementType";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_MOVEMENTDATE = "movementDate";
    public static final String PROPERTY_MOVEMENTQUANTITY = "movementQuantity";
    public static final String PROPERTY_ORDERREFERENCE = "orderReference";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_OPENQUANTITY = "openQuantity";
    public static final String PROPERTY_CONSUMEDQTY = "consumedqty";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_TRANSACTIONCOST = "transactionCost";
    public static final String PROPERTY_TAXINC = "taxinc";
    public static final String PROPERTY_TRANSACTIONPROCESSDATE = "transactionProcessDate";
    public static final String PROPERTY_COSTINGALGORITHM = "costingAlgorithm";
    public static final String PROPERTY_ISCOSTCALCULATED = "isCostCalculated";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_COSTINGSTATUS = "costingStatus";
    public static final String PROPERTY_INVOICELINE = "invoiceLine";
    public static final String PROPERTY_PHYSICALINVENTORYLINE = "physicalInventoryLine";
    public static final String PROPERTY_GOODSISSUELINE = "goodsIssueLine";
    public static final String PROPERTY_COST = "cost";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_EPCGPACKAGING = "epcgPackaging";
    public static final String PROPERTY_NOOFPACKS = "noofpacks";
    public static final String PROPERTY_EPCGVARIANT = "epcgVariant";
    public static final String PROPERTY_RCHRWARPYARNTYPE = "rchrWarpyarntype";
    public static final String PROPERTY_MATERIALLINE = "materialLine";
    public static final String PROPERTY_EPCGCONETYPE = "epcgConetype";
    public static final String PROPERTY_NOOFCONES = "noofcones";
    public static final String PROPERTY_OPENCONES = "opencones";
    public static final String PROPERTY_CONSUMEDCONES = "consumedcones";
    public static final String PROPERTY_AVGCONEWEIGHT = "avgconeweight";
    public static final String PROPERTY_WEFTISSUED = "weftissued";
    public static final String PROPERTY_WARPISSUED = "warpissued";
    public static final String PROPERTY_INVENTORYUOM = "inventoryuom";
    public static final String PROPERTY_RCGIDRLINES = "rcgiDrlines";
    public static final String PROPERTY_RCGIDILINES = "rcgiDilines";
    public static final String PROPERTY_OPENINVENTORUQTY = "openinventoruqty";
    public static final String PROPERTY_CONSUMEDINVENTORUQTY = "consumedinventoruqty";
    public static final String PROPERTY_RCGIDIFIFOLINESLIST = "rCGIDiFifoLinesList";
    public static final String PROPERTY_RCGIMILINESLIST = "rCGIMiLinesList";

    public RCGITransactions() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MOVEMENTQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_OPENQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_CONSUMEDQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_ISCOSTCALCULATED, false);
        setDefaultValue(PROPERTY_COSTINGSTATUS, "NC");
        setDefaultValue(PROPERTY_COST, new BigDecimal(0));
        setDefaultValue(PROPERTY_NOOFPACKS, new BigDecimal(0));
        setDefaultValue(PROPERTY_NOOFCONES, new BigDecimal(0));
        setDefaultValue(PROPERTY_OPENCONES, new BigDecimal(0));
        setDefaultValue(PROPERTY_CONSUMEDCONES, new BigDecimal(0));
        setDefaultValue(PROPERTY_AVGCONEWEIGHT, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEFTISSUED, new BigDecimal(0));
        setDefaultValue(PROPERTY_WARPISSUED, new BigDecimal(0));
        setDefaultValue(PROPERTY_OPENINVENTORUQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_CONSUMEDINVENTORUQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCGIDIFIFOLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMILINESLIST, new ArrayList<Object>());
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

    public String getMovementType() {
        return (String) get(PROPERTY_MOVEMENTTYPE);
    }

    public void setMovementType(String movementType) {
        set(PROPERTY_MOVEMENTTYPE, movementType);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Date getMovementDate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementDate(Date movementDate) {
        set(PROPERTY_MOVEMENTDATE, movementDate);
    }

    public BigDecimal getMovementQuantity() {
        return (BigDecimal) get(PROPERTY_MOVEMENTQUANTITY);
    }

    public void setMovementQuantity(BigDecimal movementQuantity) {
        set(PROPERTY_MOVEMENTQUANTITY, movementQuantity);
    }

    public String getOrderReference() {
        return (String) get(PROPERTY_ORDERREFERENCE);
    }

    public void setOrderReference(String orderReference) {
        set(PROPERTY_ORDERREFERENCE, orderReference);
    }

    public BigDecimal getOrderQuantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        set(PROPERTY_ORDERQUANTITY, orderQuantity);
    }

    public BigDecimal getOpenQuantity() {
        return (BigDecimal) get(PROPERTY_OPENQUANTITY);
    }

    public void setOpenQuantity(BigDecimal openQuantity) {
        set(PROPERTY_OPENQUANTITY, openQuantity);
    }

    public BigDecimal getConsumedqty() {
        return (BigDecimal) get(PROPERTY_CONSUMEDQTY);
    }

    public void setConsumedqty(BigDecimal consumedqty) {
        set(PROPERTY_CONSUMEDQTY, consumedqty);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getTransactionCost() {
        return (BigDecimal) get(PROPERTY_TRANSACTIONCOST);
    }

    public void setTransactionCost(BigDecimal transactionCost) {
        set(PROPERTY_TRANSACTIONCOST, transactionCost);
    }

    public BigDecimal getTaxinc() {
        return (BigDecimal) get(PROPERTY_TAXINC);
    }

    public void setTaxinc(BigDecimal taxinc) {
        set(PROPERTY_TAXINC, taxinc);
    }

    public Date getTransactionProcessDate() {
        return (Date) get(PROPERTY_TRANSACTIONPROCESSDATE);
    }

    public void setTransactionProcessDate(Date transactionProcessDate) {
        set(PROPERTY_TRANSACTIONPROCESSDATE, transactionProcessDate);
    }

    public CostingAlgorithm getCostingAlgorithm() {
        return (CostingAlgorithm) get(PROPERTY_COSTINGALGORITHM);
    }

    public void setCostingAlgorithm(CostingAlgorithm costingAlgorithm) {
        set(PROPERTY_COSTINGALGORITHM, costingAlgorithm);
    }

    public Boolean isCostCalculated() {
        return (Boolean) get(PROPERTY_ISCOSTCALCULATED);
    }

    public void setCostCalculated(Boolean isCostCalculated) {
        set(PROPERTY_ISCOSTCALCULATED, isCostCalculated);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getCostingStatus() {
        return (String) get(PROPERTY_COSTINGSTATUS);
    }

    public void setCostingStatus(String costingStatus) {
        set(PROPERTY_COSTINGSTATUS, costingStatus);
    }

    public InvoiceLine getInvoiceLine() {
        return (InvoiceLine) get(PROPERTY_INVOICELINE);
    }

    public void setInvoiceLine(InvoiceLine invoiceLine) {
        set(PROPERTY_INVOICELINE, invoiceLine);
    }

    public InventoryCountLine getPhysicalInventoryLine() {
        return (InventoryCountLine) get(PROPERTY_PHYSICALINVENTORYLINE);
    }

    public void setPhysicalInventoryLine(InventoryCountLine physicalInventoryLine) {
        set(PROPERTY_PHYSICALINVENTORYLINE, physicalInventoryLine);
    }

    public RCGI_GoodsIssue_Line getGoodsIssueLine() {
        return (RCGI_GoodsIssue_Line) get(PROPERTY_GOODSISSUELINE);
    }

    public void setGoodsIssueLine(RCGI_GoodsIssue_Line goodsIssueLine) {
        set(PROPERTY_GOODSISSUELINE, goodsIssueLine);
    }

    public BigDecimal getCost() {
        return (BigDecimal) get(PROPERTY_COST);
    }

    public void setCost(BigDecimal cost) {
        set(PROPERTY_COST, cost);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public EPCG_Packaging getEpcgPackaging() {
        return (EPCG_Packaging) get(PROPERTY_EPCGPACKAGING);
    }

    public void setEpcgPackaging(EPCG_Packaging epcgPackaging) {
        set(PROPERTY_EPCGPACKAGING, epcgPackaging);
    }

    public BigDecimal getNoofpacks() {
        return (BigDecimal) get(PROPERTY_NOOFPACKS);
    }

    public void setNoofpacks(BigDecimal noofpacks) {
        set(PROPERTY_NOOFPACKS, noofpacks);
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

    public RCGI_MrLines getMaterialLine() {
        return (RCGI_MrLines) get(PROPERTY_MATERIALLINE);
    }

    public void setMaterialLine(RCGI_MrLines materialLine) {
        set(PROPERTY_MATERIALLINE, materialLine);
    }

    public ConeType getEpcgConetype() {
        return (ConeType) get(PROPERTY_EPCGCONETYPE);
    }

    public void setEpcgConetype(ConeType epcgConetype) {
        set(PROPERTY_EPCGCONETYPE, epcgConetype);
    }

    public BigDecimal getNoofcones() {
        return (BigDecimal) get(PROPERTY_NOOFCONES);
    }

    public void setNoofcones(BigDecimal noofcones) {
        set(PROPERTY_NOOFCONES, noofcones);
    }

    public BigDecimal getOpencones() {
        return (BigDecimal) get(PROPERTY_OPENCONES);
    }

    public void setOpencones(BigDecimal opencones) {
        set(PROPERTY_OPENCONES, opencones);
    }

    public BigDecimal getConsumedcones() {
        return (BigDecimal) get(PROPERTY_CONSUMEDCONES);
    }

    public void setConsumedcones(BigDecimal consumedcones) {
        set(PROPERTY_CONSUMEDCONES, consumedcones);
    }

    public BigDecimal getAvgconeweight() {
        return (BigDecimal) get(PROPERTY_AVGCONEWEIGHT);
    }

    public void setAvgconeweight(BigDecimal avgconeweight) {
        set(PROPERTY_AVGCONEWEIGHT, avgconeweight);
    }

    public BigDecimal getWeftissued() {
        return (BigDecimal) get(PROPERTY_WEFTISSUED);
    }

    public void setWeftissued(BigDecimal weftissued) {
        set(PROPERTY_WEFTISSUED, weftissued);
    }

    public BigDecimal getWarpissued() {
        return (BigDecimal) get(PROPERTY_WARPISSUED);
    }

    public void setWarpissued(BigDecimal warpissued) {
        set(PROPERTY_WARPISSUED, warpissued);
    }

    public UOM getInventoryuom() {
        return (UOM) get(PROPERTY_INVENTORYUOM);
    }

    public void setInventoryuom(UOM inventoryuom) {
        set(PROPERTY_INVENTORYUOM, inventoryuom);
    }

    public RCGIDrLines getRcgiDrlines() {
        return (RCGIDrLines) get(PROPERTY_RCGIDRLINES);
    }

    public void setRcgiDrlines(RCGIDrLines rcgiDrlines) {
        set(PROPERTY_RCGIDRLINES, rcgiDrlines);
    }

    public RCGIDiLines getRcgiDilines() {
        return (RCGIDiLines) get(PROPERTY_RCGIDILINES);
    }

    public void setRcgiDilines(RCGIDiLines rcgiDilines) {
        set(PROPERTY_RCGIDILINES, rcgiDilines);
    }

    public BigDecimal getOpeninventoruqty() {
        return (BigDecimal) get(PROPERTY_OPENINVENTORUQTY);
    }

    public void setOpeninventoruqty(BigDecimal openinventoruqty) {
        set(PROPERTY_OPENINVENTORUQTY, openinventoruqty);
    }

    public BigDecimal getConsumedinventoruqty() {
        return (BigDecimal) get(PROPERTY_CONSUMEDINVENTORUQTY);
    }

    public void setConsumedinventoruqty(BigDecimal consumedinventoruqty) {
        set(PROPERTY_CONSUMEDINVENTORUQTY, consumedinventoruqty);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDiFifoLines> getRCGIDiFifoLinesList() {
        return (List<RCGIDiFifoLines>) get(PROPERTY_RCGIDIFIFOLINESLIST);
    }

    public void setRCGIDiFifoLinesList(List<RCGIDiFifoLines> rCGIDiFifoLinesList) {
        set(PROPERTY_RCGIDIFIFOLINESLIST, rCGIDiFifoLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MiLines> getRCGIMiLinesList() {
        return (List<RCGI_MiLines>) get(PROPERTY_RCGIMILINESLIST);
    }

    public void setRCGIMiLinesList(List<RCGI_MiLines> rCGIMiLinesList) {
        set(PROPERTY_RCGIMILINESLIST, rCGIMiLinesList);
    }

}
