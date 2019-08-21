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
package org.openbravo.model.materialmgmt.transaction;

import com.redcarpet.epcg.data.EPCG_Packaging;
import com.redcarpet.lotmanagement.data.RCLO_LotReceipt;

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
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.order.ReturnMaterialOrderPickEditLines;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductUOM;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.materialmgmt.cost.Costing;
import org.openbravo.model.procurement.POInvoiceMatch;
import org.openbravo.model.procurement.ReceiptInvoiceMatch;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectIssue;
import org.openbravo.model.sales.ConditionGoods;
/**
 * Entity class for entity MaterialMgmtShipmentInOutLine (stored in table M_InOutLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ShipmentInOutLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_InOutLine";
    public static final String ENTITY_NAME = "MaterialMgmtShipmentInOutLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SHIPMENTRECEIPT = "shipmentReceipt";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_MOVEMENTQUANTITY = "movementQuantity";
    public static final String PROPERTY_REINVOICE = "reinvoice";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_DESCRIPTIONONLY = "descriptionOnly";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_ORDERUOM = "orderUOM";
    public static final String PROPERTY_CONDITIONGOODS = "conditionGoods";
    public static final String PROPERTY_CANCELEDINOUTLINE = "canceledInoutLine";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_MANAGEPRERESERVATION = "managePrereservation";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_COSTCENTER = "costcenter";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_EXPLODE = "explode";
    public static final String PROPERTY_BOMPARENT = "bOMParent";
    public static final String PROPERTY_RCLONOOFBALES = "rcloNoofbales";
    public static final String PROPERTY_EPCGPACKAGING = "epcgPackaging";
    public static final String PROPERTY_EPCGTAREWT = "epcgTarewt";
    public static final String PROPERTY_EPCGGROSSWT = "epcgGrosswt";
    public static final String PROPERTY_EPCGNETWT = "epcgNetwt";
    public static final String PROPERTY_EPCGNOOFPACKAGES = "epcgNoofpackages";
    public static final String PROPERTY_EPCGMILLSNO = "epcgMillsno";
    public static final String PROPERTY_RCFRNETRATE = "rcfrNetrate";
    public static final String PROPERTY_RCFRNETUNITRATE = "rcfrNetunitrate";
    public static final String PROPERTY_RCFRPRICEACTUAL = "rcfrPriceactual";
    public static final String PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST = "inOutLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_MATERIALMGMTCOSTINGLIST = "materialMgmtCostingList";
    public static final String PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST = "materialMgmtMaterialTransactionList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINECANCELEDINOUTLINELIST = "materialMgmtShipmentInOutLineCanceledInoutLineList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINEBOMPARENTIDLIST = "materialMgmtShipmentInOutLineBOMParentIDList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_PROCUREMENTPOINVOICEMATCHLIST = "procurementPOInvoiceMatchList";
    public static final String PROPERTY_PROCUREMENTRECEIPTINVOICEMATCHLIST = "procurementReceiptInvoiceMatchList";
    public static final String PROPERTY_PROJECTISSUELIST = "projectIssueList";
    public static final String PROPERTY_RCLOLOTRECEIPTLIST = "rCLOLotReceiptList";
    public static final String PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST = "returnMaterialOrderPickEditLinesList";
    public static final String PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST = "returnMaterialReceiptPickEditList";
    public static final String PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST = "returnMaterialShipmentPickEditList";

    public ShipmentInOutLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MOVEMENTQUANTITY, new BigDecimal(1));
        setDefaultValue(PROPERTY_REINVOICE, false);
        setDefaultValue(PROPERTY_DESCRIPTIONONLY, false);
        setDefaultValue(PROPERTY_MANAGEPRERESERVATION, false);
        setDefaultValue(PROPERTY_EXPLODE, false);
        setDefaultValue(PROPERTY_RCLONOOFBALES, new BigDecimal(0));
        setDefaultValue(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINECANCELEDINOUTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINEBOMPARENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTPOINVOICEMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTRECEIPTINVOICEMATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCLOLOTRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, new ArrayList<Object>());
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

    public ShipmentInOut getShipmentReceipt() {
        return (ShipmentInOut) get(PROPERTY_SHIPMENTRECEIPT);
    }

    public void setShipmentReceipt(ShipmentInOut shipmentReceipt) {
        set(PROPERTY_SHIPMENTRECEIPT, shipmentReceipt);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
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

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getMovementQuantity() {
        return (BigDecimal) get(PROPERTY_MOVEMENTQUANTITY);
    }

    public void setMovementQuantity(BigDecimal movementQuantity) {
        set(PROPERTY_MOVEMENTQUANTITY, movementQuantity);
    }

    public Boolean isReinvoice() {
        return (Boolean) get(PROPERTY_REINVOICE);
    }

    public void setReinvoice(Boolean reinvoice) {
        set(PROPERTY_REINVOICE, reinvoice);
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

    public ConditionGoods getConditionGoods() {
        return (ConditionGoods) get(PROPERTY_CONDITIONGOODS);
    }

    public void setConditionGoods(ConditionGoods conditionGoods) {
        set(PROPERTY_CONDITIONGOODS, conditionGoods);
    }

    public ShipmentInOutLine getCanceledInoutLine() {
        return (ShipmentInOutLine) get(PROPERTY_CANCELEDINOUTLINE);
    }

    public void setCanceledInoutLine(ShipmentInOutLine canceledInoutLine) {
        set(PROPERTY_CANCELEDINOUTLINE, canceledInoutLine);
    }

    public Asset getAsset() {
        return (Asset) get(PROPERTY_ASSET);
    }

    public void setAsset(Asset asset) {
        set(PROPERTY_ASSET, asset);
    }

    public Boolean isManagePrereservation() {
        return (Boolean) get(PROPERTY_MANAGEPRERESERVATION);
    }

    public void setManagePrereservation(Boolean managePrereservation) {
        set(PROPERTY_MANAGEPRERESERVATION, managePrereservation);
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

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Costcenter getCostcenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostcenter(Costcenter costcenter) {
        set(PROPERTY_COSTCENTER, costcenter);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Boolean isExplode() {
        return (Boolean) get(PROPERTY_EXPLODE);
    }

    public void setExplode(Boolean explode) {
        set(PROPERTY_EXPLODE, explode);
    }

    public ShipmentInOutLine getBOMParent() {
        return (ShipmentInOutLine) get(PROPERTY_BOMPARENT);
    }

    public void setBOMParent(ShipmentInOutLine bOMParent) {
        set(PROPERTY_BOMPARENT, bOMParent);
    }

    public BigDecimal getRcloNoofbales() {
        return (BigDecimal) get(PROPERTY_RCLONOOFBALES);
    }

    public void setRcloNoofbales(BigDecimal rcloNoofbales) {
        set(PROPERTY_RCLONOOFBALES, rcloNoofbales);
    }

    public EPCG_Packaging getEpcgPackaging() {
        return (EPCG_Packaging) get(PROPERTY_EPCGPACKAGING);
    }

    public void setEpcgPackaging(EPCG_Packaging epcgPackaging) {
        set(PROPERTY_EPCGPACKAGING, epcgPackaging);
    }

    public Long getEpcgTarewt() {
        return (Long) get(PROPERTY_EPCGTAREWT);
    }

    public void setEpcgTarewt(Long epcgTarewt) {
        set(PROPERTY_EPCGTAREWT, epcgTarewt);
    }

    public Long getEpcgGrosswt() {
        return (Long) get(PROPERTY_EPCGGROSSWT);
    }

    public void setEpcgGrosswt(Long epcgGrosswt) {
        set(PROPERTY_EPCGGROSSWT, epcgGrosswt);
    }

    public Long getEpcgNetwt() {
        return (Long) get(PROPERTY_EPCGNETWT);
    }

    public void setEpcgNetwt(Long epcgNetwt) {
        set(PROPERTY_EPCGNETWT, epcgNetwt);
    }

    public BigDecimal getEpcgNoofpackages() {
        return (BigDecimal) get(PROPERTY_EPCGNOOFPACKAGES);
    }

    public void setEpcgNoofpackages(BigDecimal epcgNoofpackages) {
        set(PROPERTY_EPCGNOOFPACKAGES, epcgNoofpackages);
    }

    public String getEpcgMillsno() {
        return (String) get(PROPERTY_EPCGMILLSNO);
    }

    public void setEpcgMillsno(String epcgMillsno) {
        set(PROPERTY_EPCGMILLSNO, epcgMillsno);
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

    public BigDecimal getRcfrPriceactual() {
        return (BigDecimal) get(PROPERTY_RCFRPRICEACTUAL);
    }

    public void setRcfrPriceactual(BigDecimal rcfrPriceactual) {
        set(PROPERTY_RCFRPRICEACTUAL, rcfrPriceactual);
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
    public List<Costing> getMaterialMgmtCostingList() {
        return (List<Costing>) get(PROPERTY_MATERIALMGMTCOSTINGLIST);
    }

    public void setMaterialMgmtCostingList(List<Costing> materialMgmtCostingList) {
        set(PROPERTY_MATERIALMGMTCOSTINGLIST, materialMgmtCostingList);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransaction> getMaterialMgmtMaterialTransactionList() {
        return (List<MaterialTransaction>) get(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST);
    }

    public void setMaterialMgmtMaterialTransactionList(List<MaterialTransaction> materialMgmtMaterialTransactionList) {
        set(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, materialMgmtMaterialTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOutLine> getMaterialMgmtShipmentInOutLineCanceledInoutLineList() {
        return (List<ShipmentInOutLine>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINECANCELEDINOUTLINELIST);
    }

    public void setMaterialMgmtShipmentInOutLineCanceledInoutLineList(List<ShipmentInOutLine> materialMgmtShipmentInOutLineCanceledInoutLineList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINECANCELEDINOUTLINELIST, materialMgmtShipmentInOutLineCanceledInoutLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOutLine> getMaterialMgmtShipmentInOutLineBOMParentIDList() {
        return (List<ShipmentInOutLine>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINEBOMPARENTIDLIST);
    }

    public void setMaterialMgmtShipmentInOutLineBOMParentIDList(List<ShipmentInOutLine> materialMgmtShipmentInOutLineBOMParentIDList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINEBOMPARENTIDLIST, materialMgmtShipmentInOutLineBOMParentIDList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
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
    public List<ProjectIssue> getProjectIssueList() {
        return (List<ProjectIssue>) get(PROPERTY_PROJECTISSUELIST);
    }

    public void setProjectIssueList(List<ProjectIssue> projectIssueList) {
        set(PROPERTY_PROJECTISSUELIST, projectIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCLO_LotReceipt> getRCLOLotReceiptList() {
        return (List<RCLO_LotReceipt>) get(PROPERTY_RCLOLOTRECEIPTLIST);
    }

    public void setRCLOLotReceiptList(List<RCLO_LotReceipt> rCLOLotReceiptList) {
        set(PROPERTY_RCLOLOTRECEIPTLIST, rCLOLotReceiptList);
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

}
