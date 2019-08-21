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

import com.redcarpet.epcg.data.EpcgProformaline;
import com.redcarpet.goodsissue.data.RCGIDiLines;
import com.redcarpet.goodsissue.data.RCGIDrLines;
import com.redcarpet.goodsissue.data.RCGITransactions;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.goodsissue.data.RCGI_MrLines;
import com.redcarpet.production.data.RCPR_PrBOM;
import com.redcarpet.production.data.RCPR_PrBOMLines;
import com.redcarpet.production.data.RCPR_PrProduct;
import com.redcarpet.production.data.RCPR_ProductionRun;
import com.redcarpet.rcssob.data.RcobStoragebins;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductOrg;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.manufacturing.floorshop.Toolset;
import org.openbravo.model.manufacturing.transaction.GlobalUse;
import org.openbravo.model.materialmgmt.onhandquantity.ProductStockView;
import org.openbravo.model.materialmgmt.onhandquantity.Reservation;
import org.openbravo.model.materialmgmt.onhandquantity.ReservationManualPickEdit;
import org.openbravo.model.materialmgmt.onhandquantity.ReservationStock;
import org.openbravo.model.materialmgmt.onhandquantity.StorageDetail;
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
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.project.ProjectIssue;
/**
 * Entity class for entity Locator (stored in table M_Locator).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Locator extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Locator";
    public static final String ENTITY_NAME = "Locator";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_RELATIVEPRIORITY = "relativePriority";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_ROWX = "rowX";
    public static final String PROPERTY_STACKY = "stackY";
    public static final String PROPERTY_LEVELZ = "levelZ";
    public static final String PROPERTY_BARCODE = "barcode";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLOCATIONFROMADDRESSLIST = "aPRMFinaccTrxFullAcctVLocationFromAddressList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLOCATIONTOADDRESSLIST = "aPRMFinaccTrxFullAcctVLocationToAddressList";
    public static final String PROPERTY_EPCGPROFORMALINELIST = "epcgProformalineList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_MANUFACTURINGGLOBALUSELIST = "manufacturingGlobalUseList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONLINELIST = "manufacturingProductionLineList";
    public static final String PROPERTY_MANUFACTURINGTOOLSETLIST = "manufacturingToolsetList";
    public static final String PROPERTY_MATERIALMGMTINTERNALCONSUMPTIONLINELIST = "materialMgmtInternalConsumptionLineList";
    public static final String PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINELIST = "materialMgmtInternalMovementLineList";
    public static final String PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINENEWSTORAGEBINLIST = "materialMgmtInternalMovementLineNewStorageBinList";
    public static final String PROPERTY_MATERIALMGMTINVENTORYCOUNTLINELIST = "materialMgmtInventoryCountLineList";
    public static final String PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST = "materialMgmtMaterialTransactionList";
    public static final String PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST = "materialMgmtProductionPlanList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONLIST = "materialMgmtReservationList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST = "materialMgmtReservationStockList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST = "materialMgmtShipmentInOutLineList";
    public static final String PROPERTY_MATERIALMGMTSTORAGEDETAILLIST = "materialMgmtStorageDetailList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLINELIST = "procurementRequisitionLineList";
    public static final String PROPERTY_PRODUCTLIST = "productList";
    public static final String PROPERTY_PRODUCTORGLIST = "productOrgList";
    public static final String PROPERTY_PRODUCTSTOCKVIEWLIST = "productStockViewList";
    public static final String PROPERTY_PROJECTISSUELIST = "projectIssueList";
    public static final String PROPERTY_RCGIDILINESLIST = "rCGIDiLinesList";
    public static final String PROPERTY_RCGIDRLINESLIST = "rCGIDrLinesList";
    public static final String PROPERTY_RCGIGOODSISSUELINELIST = "rCGIGoodsIssueLineList";
    public static final String PROPERTY_RCGIMILINESLIST = "rCGIMiLinesList";
    public static final String PROPERTY_RCGIMRLINESLIST = "rCGIMrLinesList";
    public static final String PROPERTY_RCGITRANSACTIONSLIST = "rCGITransactionsList";
    public static final String PROPERTY_RCPRPRBOMLIST = "rCPRPrBOMList";
    public static final String PROPERTY_RCPRPRBOMLINESLIST = "rCPRPrBOMLinesList";
    public static final String PROPERTY_RCPRPRPRODUCTLIST = "rCPRPrProductList";
    public static final String PROPERTY_RCPRPRODUCTIONRUNLIST = "rCPRProductionRunList";
    public static final String PROPERTY_RCOBSTORAGEBINSLIST = "rcobStoragebinsList";
    public static final String PROPERTY_RESERVATIONMANUALPICKEDITLIST = "reservationManualPickEditList";
    public static final String PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST = "returnMaterialReceiptPickEditList";
    public static final String PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST = "returnMaterialShipmentPickEditList";
    public static final String PROPERTY_TRANSACTIONVLIST = "transactionVList";
    public static final String PROPERTY_WAREHOUSEMRETURNLOCATORIDLIST = "warehouseMReturnlocatorIDList";

    public Locator() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RELATIVEPRIORITY, (long) 50);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLOCATIONFROMADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLOCATIONTOADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPROFORMALINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGGLOBALUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGTOOLSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINTERNALCONSUMPTIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINENEWSTORAGEBINLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINVENTORYCOUNTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTPRODUCTIONPLANLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSTORAGEDETAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTORGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTSTOCKVIEWLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIGOODSISSUELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGITRANSACTIONSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRBOMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRBOMLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPRODUCTIONRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBSTORAGEBINSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESERVATIONMANUALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSEMRETURNLOCATORIDLIST, new ArrayList<Object>());
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

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public Long getRelativePriority() {
        return (Long) get(PROPERTY_RELATIVEPRIORITY);
    }

    public void setRelativePriority(Long relativePriority) {
        set(PROPERTY_RELATIVEPRIORITY, relativePriority);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public String getRowX() {
        return (String) get(PROPERTY_ROWX);
    }

    public void setRowX(String rowX) {
        set(PROPERTY_ROWX, rowX);
    }

    public String getStackY() {
        return (String) get(PROPERTY_STACKY);
    }

    public void setStackY(String stackY) {
        set(PROPERTY_STACKY, stackY);
    }

    public String getLevelZ() {
        return (String) get(PROPERTY_LEVELZ);
    }

    public void setLevelZ(String levelZ) {
        set(PROPERTY_LEVELZ, levelZ);
    }

    public String getBarcode() {
        return (String) get(PROPERTY_BARCODE);
    }

    public void setBarcode(String barcode) {
        set(PROPERTY_BARCODE, barcode);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVLocationFromAddressList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLOCATIONFROMADDRESSLIST);
    }

    public void setAPRMFinaccTrxFullAcctVLocationFromAddressList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVLocationFromAddressList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLOCATIONFROMADDRESSLIST, aPRMFinaccTrxFullAcctVLocationFromAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVLocationToAddressList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLOCATIONTOADDRESSLIST);
    }

    public void setAPRMFinaccTrxFullAcctVLocationToAddressList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVLocationToAddressList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLOCATIONTOADDRESSLIST, aPRMFinaccTrxFullAcctVLocationToAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgProformaline> getEpcgProformalineList() {
        return (List<EpcgProformaline>) get(PROPERTY_EPCGPROFORMALINELIST);
    }

    public void setEpcgProformalineList(List<EpcgProformaline> epcgProformalineList) {
        set(PROPERTY_EPCGPROFORMALINELIST, epcgProformalineList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
        return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<GlobalUse> getManufacturingGlobalUseList() {
        return (List<GlobalUse>) get(PROPERTY_MANUFACTURINGGLOBALUSELIST);
    }

    public void setManufacturingGlobalUseList(List<GlobalUse> manufacturingGlobalUseList) {
        set(PROPERTY_MANUFACTURINGGLOBALUSELIST, manufacturingGlobalUseList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionLine> getManufacturingProductionLineList() {
        return (List<ProductionLine>) get(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST);
    }

    public void setManufacturingProductionLineList(List<ProductionLine> manufacturingProductionLineList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONLINELIST, manufacturingProductionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Toolset> getManufacturingToolsetList() {
        return (List<Toolset>) get(PROPERTY_MANUFACTURINGTOOLSETLIST);
    }

    public void setManufacturingToolsetList(List<Toolset> manufacturingToolsetList) {
        set(PROPERTY_MANUFACTURINGTOOLSETLIST, manufacturingToolsetList);
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
    public List<InternalMovementLine> getMaterialMgmtInternalMovementLineNewStorageBinList() {
        return (List<InternalMovementLine>) get(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINENEWSTORAGEBINLIST);
    }

    public void setMaterialMgmtInternalMovementLineNewStorageBinList(List<InternalMovementLine> materialMgmtInternalMovementLineNewStorageBinList) {
        set(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINENEWSTORAGEBINLIST, materialMgmtInternalMovementLineNewStorageBinList);
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
    public List<StorageDetail> getMaterialMgmtStorageDetailList() {
        return (List<StorageDetail>) get(PROPERTY_MATERIALMGMTSTORAGEDETAILLIST);
    }

    public void setMaterialMgmtStorageDetailList(List<StorageDetail> materialMgmtStorageDetailList) {
        set(PROPERTY_MATERIALMGMTSTORAGEDETAILLIST, materialMgmtStorageDetailList);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitionLine> getProcurementRequisitionLineList() {
        return (List<RequisitionLine>) get(PROPERTY_PROCUREMENTREQUISITIONLINELIST);
    }

    public void setProcurementRequisitionLineList(List<RequisitionLine> procurementRequisitionLineList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLINELIST, procurementRequisitionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProductList() {
        return (List<Product>) get(PROPERTY_PRODUCTLIST);
    }

    public void setProductList(List<Product> productList) {
        set(PROPERTY_PRODUCTLIST, productList);
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
    public List<ProjectIssue> getProjectIssueList() {
        return (List<ProjectIssue>) get(PROPERTY_PROJECTISSUELIST);
    }

    public void setProjectIssueList(List<ProjectIssue> projectIssueList) {
        set(PROPERTY_PROJECTISSUELIST, projectIssueList);
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
    public List<RCGITransactions> getRCGITransactionsList() {
        return (List<RCGITransactions>) get(PROPERTY_RCGITRANSACTIONSLIST);
    }

    public void setRCGITransactionsList(List<RCGITransactions> rCGITransactionsList) {
        set(PROPERTY_RCGITRANSACTIONSLIST, rCGITransactionsList);
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
    public List<ReservationManualPickEdit> getReservationManualPickEditList() {
        return (List<ReservationManualPickEdit>) get(PROPERTY_RESERVATIONMANUALPICKEDITLIST);
    }

    public void setReservationManualPickEditList(List<ReservationManualPickEdit> reservationManualPickEditList) {
        set(PROPERTY_RESERVATIONMANUALPICKEDITLIST, reservationManualPickEditList);
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
    public List<MaterialTransactionV> getTransactionVList() {
        return (List<MaterialTransactionV>) get(PROPERTY_TRANSACTIONVLIST);
    }

    public void setTransactionVList(List<MaterialTransactionV> transactionVList) {
        set(PROPERTY_TRANSACTIONVLIST, transactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<Warehouse> getWarehouseMReturnlocatorIDList() {
        return (List<Warehouse>) get(PROPERTY_WAREHOUSEMRETURNLOCATORIDLIST);
    }

    public void setWarehouseMReturnlocatorIDList(List<Warehouse> warehouseMReturnlocatorIDList) {
        set(PROPERTY_WAREHOUSEMRETURNLOCATORIDLIST, warehouseMReturnlocatorIDList);
    }

}
