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
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.AttributeSetInstance;
/**
 * Entity class for entity ReservationManualPickEdit (stored in table M_Reservation_Pick_Edit).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ReservationManualPickEdit extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Reservation_Pick_Edit";
    public static final String ENTITY_NAME = "ReservationManualPickEdit";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_OBSELECTED = "obSelected";
    public static final String PROPERTY_RESERVATIONSTOCK = "reservationStock";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_PURCHASEORDERLINE = "purchaseOrderLine";
    public static final String PROPERTY_AVAILABLEQTY = "availableQty";
    public static final String PROPERTY_RESERVEDINOTHERS = "reservedinothers";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_RELEASED = "released";
    public static final String PROPERTY_ALLOCATED = "allocated";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_STOCKRESERVATION = "stockReservation";
    public static final String PROPERTY_RESERVATIONQUANTITY = "reservationQuantity";

    public ReservationManualPickEdit() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OBSELECTED, false);
        setDefaultValue(PROPERTY_ALLOCATED, false);
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

    public Boolean isObSelected() {
        return (Boolean) get(PROPERTY_OBSELECTED);
    }

    public void setObSelected(Boolean obSelected) {
        set(PROPERTY_OBSELECTED, obSelected);
    }

    public ReservationStock getReservationStock() {
        return (ReservationStock) get(PROPERTY_RESERVATIONSTOCK);
    }

    public void setReservationStock(ReservationStock reservationStock) {
        set(PROPERTY_RESERVATIONSTOCK, reservationStock);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public OrderLine getPurchaseOrderLine() {
        return (OrderLine) get(PROPERTY_PURCHASEORDERLINE);
    }

    public void setPurchaseOrderLine(OrderLine purchaseOrderLine) {
        set(PROPERTY_PURCHASEORDERLINE, purchaseOrderLine);
    }

    public BigDecimal getAvailableQty() {
        return (BigDecimal) get(PROPERTY_AVAILABLEQTY);
    }

    public void setAvailableQty(BigDecimal availableQty) {
        set(PROPERTY_AVAILABLEQTY, availableQty);
    }

    public BigDecimal getReservedinothers() {
        return (BigDecimal) get(PROPERTY_RESERVEDINOTHERS);
    }

    public void setReservedinothers(BigDecimal reservedinothers) {
        set(PROPERTY_RESERVEDINOTHERS, reservedinothers);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public BigDecimal getReleased() {
        return (BigDecimal) get(PROPERTY_RELEASED);
    }

    public void setReleased(BigDecimal released) {
        set(PROPERTY_RELEASED, released);
    }

    public Boolean isAllocated() {
        return (Boolean) get(PROPERTY_ALLOCATED);
    }

    public void setAllocated(Boolean allocated) {
        set(PROPERTY_ALLOCATED, allocated);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public Reservation getStockReservation() {
        return (Reservation) get(PROPERTY_STOCKRESERVATION);
    }

    public void setStockReservation(Reservation stockReservation) {
        set(PROPERTY_STOCKRESERVATION, stockReservation);
    }

    public BigDecimal getReservationQuantity() {
        return (BigDecimal) get(PROPERTY_RESERVATIONQUANTITY);
    }

    public void setReservationQuantity(BigDecimal reservationQuantity) {
        set(PROPERTY_RESERVATIONQUANTITY, reservationQuantity);
    }

}
