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
package org.openbravo.model.common.enterprise;

import com.redcarpet.epcg.data.EPCGWarehouseuser;
import com.redcarpet.rcssob.data.PurchaseQuotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.alert.Alert;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.ProductByPriceAndWarehouse;
import org.openbravo.model.materialmgmt.cost.Costing;
import org.openbravo.model.materialmgmt.cost.CostingRuleInit;
import org.openbravo.model.materialmgmt.onhandquantity.Reservation;
import org.openbravo.model.materialmgmt.onhandquantity.ReservationManualPickEdit;
import org.openbravo.model.materialmgmt.onhandquantity.StoragePending;
import org.openbravo.model.materialmgmt.transaction.InventoryCount;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.pos.ExternalPOS;
import org.openbravo.model.project.Project;
import org.openbravo.model.timeandexpense.Resource;
import org.openbravo.model.timeandexpense.Sheet;
/**
 * Entity class for entity Warehouse (stored in table M_Warehouse).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Warehouse extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Warehouse";
    public static final String ENTITY_NAME = "Warehouse";
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
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_STORAGEBINSEPARATOR = "storageBinSeparator";
    public static final String PROPERTY_SHIPMENTVEHICLE = "shipmentVehicle";
    public static final String PROPERTY_SHIPPERCODE = "shipperCode";
    public static final String PROPERTY_FROMDOCUMENTNO = "fromDocumentNo";
    public static final String PROPERTY_TODOCUMENTNO = "toDocumentNo";
    public static final String PROPERTY_RETURNLOCATOR = "returnlocator";
    public static final String PROPERTY_WAREHOUSERULE = "warehouseRule";
    public static final String PROPERTY_ADALERTLIST = "aDAlertList";
    public static final String PROPERTY_ADUSERDEFAULTWAREHOUSELIST = "aDUserDefaultWarehouseList";
    public static final String PROPERTY_COSTINGRULEINITLIST = "costingRuleInitList";
    public static final String PROPERTY_EPCGWAREHOUSEUSERLIST = "ePCGWarehouseuserList";
    public static final String PROPERTY_EXTERNALPOSLIST = "externalPOSList";
    public static final String PROPERTY_LOCATORLIST = "locatorList";
    public static final String PROPERTY_MATERIALMGMTCOSTINGLIST = "materialMgmtCostingList";
    public static final String PROPERTY_MATERIALMGMTINVENTORYCOUNTLIST = "materialMgmtInventoryCountList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONLIST = "materialMgmtReservationList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_MATERIALMGMTSTORAGEPENDINGLIST = "materialMgmtStoragePendingList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_ORGANIZATIONWAREHOUSELIST = "organizationWarehouseList";
    public static final String PROPERTY_PRODUCTBYPRICEANDWAREHOUSELIST = "productByPriceAndWarehouseList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLIST = "rCOBPurchasequotationList";
    public static final String PROPERTY_RESERVATIONMANUALPICKEDITLIST = "reservationManualPickEditList";
    public static final String PROPERTY_RESOURCELIST = "resourceList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLIST = "timeAndExpenseSheetList";
    public static final String PROPERTY_WAREHOUSEACCOUNTSLIST = "warehouseAccountsList";
    public static final String PROPERTY_WAREHOUSESHIPPERLIST = "warehouseShipperList";

    public Warehouse() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_STORAGEBINSEPARATOR, "*");
        setDefaultValue(PROPERTY_SHIPMENTVEHICLE, false);
        setDefaultValue(PROPERTY_ADALERTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADUSERDEFAULTWAREHOUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COSTINGRULEINITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWAREHOUSEUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LOCATORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINVENTORYCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSTORAGEPENDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONWAREHOUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTBYPRICEANDWAREHOUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPURCHASEQUOTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESERVATIONMANUALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESOURCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSEACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSESHIPPERLIST, new ArrayList<Object>());
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

    public Location getLocationAddress() {
        return (Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public String getStorageBinSeparator() {
        return (String) get(PROPERTY_STORAGEBINSEPARATOR);
    }

    public void setStorageBinSeparator(String storageBinSeparator) {
        set(PROPERTY_STORAGEBINSEPARATOR, storageBinSeparator);
    }

    public Boolean isShipmentVehicle() {
        return (Boolean) get(PROPERTY_SHIPMENTVEHICLE);
    }

    public void setShipmentVehicle(Boolean shipmentVehicle) {
        set(PROPERTY_SHIPMENTVEHICLE, shipmentVehicle);
    }

    public String getShipperCode() {
        return (String) get(PROPERTY_SHIPPERCODE);
    }

    public void setShipperCode(String shipperCode) {
        set(PROPERTY_SHIPPERCODE, shipperCode);
    }

    public Long getFromDocumentNo() {
        return (Long) get(PROPERTY_FROMDOCUMENTNO);
    }

    public void setFromDocumentNo(Long fromDocumentNo) {
        set(PROPERTY_FROMDOCUMENTNO, fromDocumentNo);
    }

    public Long getToDocumentNo() {
        return (Long) get(PROPERTY_TODOCUMENTNO);
    }

    public void setToDocumentNo(Long toDocumentNo) {
        set(PROPERTY_TODOCUMENTNO, toDocumentNo);
    }

    public Locator getReturnlocator() {
        return (Locator) get(PROPERTY_RETURNLOCATOR);
    }

    public void setReturnlocator(Locator returnlocator) {
        set(PROPERTY_RETURNLOCATOR, returnlocator);
    }

    public WarehouseRule getWarehouseRule() {
        return (WarehouseRule) get(PROPERTY_WAREHOUSERULE);
    }

    public void setWarehouseRule(WarehouseRule warehouseRule) {
        set(PROPERTY_WAREHOUSERULE, warehouseRule);
    }

    @SuppressWarnings("unchecked")
    public List<Alert> getADAlertList() {
        return (List<Alert>) get(PROPERTY_ADALERTLIST);
    }

    public void setADAlertList(List<Alert> aDAlertList) {
        set(PROPERTY_ADALERTLIST, aDAlertList);
    }

    @SuppressWarnings("unchecked")
    public List<User> getADUserDefaultWarehouseList() {
        return (List<User>) get(PROPERTY_ADUSERDEFAULTWAREHOUSELIST);
    }

    public void setADUserDefaultWarehouseList(List<User> aDUserDefaultWarehouseList) {
        set(PROPERTY_ADUSERDEFAULTWAREHOUSELIST, aDUserDefaultWarehouseList);
    }

    @SuppressWarnings("unchecked")
    public List<CostingRuleInit> getCostingRuleInitList() {
        return (List<CostingRuleInit>) get(PROPERTY_COSTINGRULEINITLIST);
    }

    public void setCostingRuleInitList(List<CostingRuleInit> costingRuleInitList) {
        set(PROPERTY_COSTINGRULEINITLIST, costingRuleInitList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGWarehouseuser> getEPCGWarehouseuserList() {
        return (List<EPCGWarehouseuser>) get(PROPERTY_EPCGWAREHOUSEUSERLIST);
    }

    public void setEPCGWarehouseuserList(List<EPCGWarehouseuser> ePCGWarehouseuserList) {
        set(PROPERTY_EPCGWAREHOUSEUSERLIST, ePCGWarehouseuserList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOS> getExternalPOSList() {
        return (List<ExternalPOS>) get(PROPERTY_EXTERNALPOSLIST);
    }

    public void setExternalPOSList(List<ExternalPOS> externalPOSList) {
        set(PROPERTY_EXTERNALPOSLIST, externalPOSList);
    }

    @SuppressWarnings("unchecked")
    public List<Locator> getLocatorList() {
        return (List<Locator>) get(PROPERTY_LOCATORLIST);
    }

    public void setLocatorList(List<Locator> locatorList) {
        set(PROPERTY_LOCATORLIST, locatorList);
    }

    @SuppressWarnings("unchecked")
    public List<Costing> getMaterialMgmtCostingList() {
        return (List<Costing>) get(PROPERTY_MATERIALMGMTCOSTINGLIST);
    }

    public void setMaterialMgmtCostingList(List<Costing> materialMgmtCostingList) {
        set(PROPERTY_MATERIALMGMTCOSTINGLIST, materialMgmtCostingList);
    }

    @SuppressWarnings("unchecked")
    public List<InventoryCount> getMaterialMgmtInventoryCountList() {
        return (List<InventoryCount>) get(PROPERTY_MATERIALMGMTINVENTORYCOUNTLIST);
    }

    public void setMaterialMgmtInventoryCountList(List<InventoryCount> materialMgmtInventoryCountList) {
        set(PROPERTY_MATERIALMGMTINVENTORYCOUNTLIST, materialMgmtInventoryCountList);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getMaterialMgmtReservationList() {
        return (List<Reservation>) get(PROPERTY_MATERIALMGMTRESERVATIONLIST);
    }

    public void setMaterialMgmtReservationList(List<Reservation> materialMgmtReservationList) {
        set(PROPERTY_MATERIALMGMTRESERVATIONLIST, materialMgmtReservationList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
        return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<StoragePending> getMaterialMgmtStoragePendingList() {
        return (List<StoragePending>) get(PROPERTY_MATERIALMGMTSTORAGEPENDINGLIST);
    }

    public void setMaterialMgmtStoragePendingList(List<StoragePending> materialMgmtStoragePendingList) {
        set(PROPERTY_MATERIALMGMTSTORAGEPENDINGLIST, materialMgmtStoragePendingList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderList() {
        return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrgWarehouse> getOrganizationWarehouseList() {
        return (List<OrgWarehouse>) get(PROPERTY_ORGANIZATIONWAREHOUSELIST);
    }

    public void setOrganizationWarehouseList(List<OrgWarehouse> organizationWarehouseList) {
        set(PROPERTY_ORGANIZATIONWAREHOUSELIST, organizationWarehouseList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductByPriceAndWarehouse> getProductByPriceAndWarehouseList() {
        return (List<ProductByPriceAndWarehouse>) get(PROPERTY_PRODUCTBYPRICEANDWAREHOUSELIST);
    }

    public void setProductByPriceAndWarehouseList(List<ProductByPriceAndWarehouse> productByPriceAndWarehouseList) {
        set(PROPERTY_PRODUCTBYPRICEANDWAREHOUSELIST, productByPriceAndWarehouseList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
        return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseQuotation> getRCOBPurchasequotationList() {
        return (List<PurchaseQuotation>) get(PROPERTY_RCOBPURCHASEQUOTATIONLIST);
    }

    public void setRCOBPurchasequotationList(List<PurchaseQuotation> rCOBPurchasequotationList) {
        set(PROPERTY_RCOBPURCHASEQUOTATIONLIST, rCOBPurchasequotationList);
    }

    @SuppressWarnings("unchecked")
    public List<ReservationManualPickEdit> getReservationManualPickEditList() {
        return (List<ReservationManualPickEdit>) get(PROPERTY_RESERVATIONMANUALPICKEDITLIST);
    }

    public void setReservationManualPickEditList(List<ReservationManualPickEdit> reservationManualPickEditList) {
        set(PROPERTY_RESERVATIONMANUALPICKEDITLIST, reservationManualPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<Resource> getResourceList() {
        return (List<Resource>) get(PROPERTY_RESOURCELIST);
    }

    public void setResourceList(List<Resource> resourceList) {
        set(PROPERTY_RESOURCELIST, resourceList);
    }

    @SuppressWarnings("unchecked")
    public List<Sheet> getTimeAndExpenseSheetList() {
        return (List<Sheet>) get(PROPERTY_TIMEANDEXPENSESHEETLIST);
    }

    public void setTimeAndExpenseSheetList(List<Sheet> timeAndExpenseSheetList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLIST, timeAndExpenseSheetList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseAccounts> getWarehouseAccountsList() {
        return (List<WarehouseAccounts>) get(PROPERTY_WAREHOUSEACCOUNTSLIST);
    }

    public void setWarehouseAccountsList(List<WarehouseAccounts> warehouseAccountsList) {
        set(PROPERTY_WAREHOUSEACCOUNTSLIST, warehouseAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseShipper> getWarehouseShipperList() {
        return (List<WarehouseShipper>) get(PROPERTY_WAREHOUSESHIPPERLIST);
    }

    public void setWarehouseShipperList(List<WarehouseShipper> warehouseShipperList) {
        set(PROPERTY_WAREHOUSESHIPPERLIST, warehouseShipperList);
    }

}
