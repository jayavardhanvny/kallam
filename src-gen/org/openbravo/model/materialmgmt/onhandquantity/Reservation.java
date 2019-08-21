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
package org.openbravo.model.materialmgmt.onhandquantity;

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
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
/**
 * Entity class for entity MaterialMgmtReservation (stored in table M_Reservation).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Reservation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Reservation";
    public static final String ENTITY_NAME = "MaterialMgmtReservation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_RESERVEDQTY = "reservedQty";
    public static final String PROPERTY_RELEASED = "released";
    public static final String PROPERTY_RESSTATUS = "rESStatus";
    public static final String PROPERTY_RESPROCESS = "rESProcess";
    public static final String PROPERTY_MANAGESTOCK = "manageStock";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST = "materialMgmtReservationStockList";
    public static final String PROPERTY_PRERESERVATIONMANUALPICKEDITLIST = "prereservationManualPickEditList";
    public static final String PROPERTY_RESERVATIONMANUALPICKEDITLIST = "reservationManualPickEditList";
    public static final String PROPERTY_SOLRESERVEDSTOCKLIST = "sOLReservedStockList";

    public Reservation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_RESSTATUS, "DR");
        setDefaultValue(PROPERTY_RESPROCESS, "PR");
        setDefaultValue(PROPERTY_MANAGESTOCK, false);
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESERVATIONMANUALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SOLRESERVEDSTOCKLIST, new ArrayList<Object>());
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

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public BigDecimal getReservedQty() {
        return (BigDecimal) get(PROPERTY_RESERVEDQTY);
    }

    public void setReservedQty(BigDecimal reservedQty) {
        set(PROPERTY_RESERVEDQTY, reservedQty);
    }

    public BigDecimal getReleased() {
        return (BigDecimal) get(PROPERTY_RELEASED);
    }

    public void setReleased(BigDecimal released) {
        set(PROPERTY_RELEASED, released);
    }

    public String getRESStatus() {
        return (String) get(PROPERTY_RESSTATUS);
    }

    public void setRESStatus(String rESStatus) {
        set(PROPERTY_RESSTATUS, rESStatus);
    }

    public String getRESProcess() {
        return (String) get(PROPERTY_RESPROCESS);
    }

    public void setRESProcess(String rESProcess) {
        set(PROPERTY_RESPROCESS, rESProcess);
    }

    public Boolean isManageStock() {
        return (Boolean) get(PROPERTY_MANAGESTOCK);
    }

    public void setManageStock(Boolean manageStock) {
        set(PROPERTY_MANAGESTOCK, manageStock);
    }

    @SuppressWarnings("unchecked")
    public List<ReservationStock> getMaterialMgmtReservationStockList() {
        return (List<ReservationStock>) get(PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST);
    }

    public void setMaterialMgmtReservationStockList(List<ReservationStock> materialMgmtReservationStockList) {
        set(PROPERTY_MATERIALMGMTRESERVATIONSTOCKLIST, materialMgmtReservationStockList);
    }

    @SuppressWarnings("unchecked")
    public List<PrereservationManualPickEdit> getPrereservationManualPickEditList() {
        return (List<PrereservationManualPickEdit>) get(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST);
    }

    public void setPrereservationManualPickEditList(List<PrereservationManualPickEdit> prereservationManualPickEditList) {
        set(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST, prereservationManualPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<ReservationManualPickEdit> getReservationManualPickEditList() {
        return (List<ReservationManualPickEdit>) get(PROPERTY_RESERVATIONMANUALPICKEDITLIST);
    }

    public void setReservationManualPickEditList(List<ReservationManualPickEdit> reservationManualPickEditList) {
        set(PROPERTY_RESERVATIONMANUALPICKEDITLIST, reservationManualPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<SOLReservedStock> getSOLReservedStockList() {
        return (List<SOLReservedStock>) get(PROPERTY_SOLRESERVEDSTOCKLIST);
    }

    public void setSOLReservedStockList(List<SOLReservedStock> sOLReservedStockList) {
        set(PROPERTY_SOLRESERVEDSTOCKLIST, sOLReservedStockList);
    }

}
