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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.OrderLine;
/**
 * Entity class for entity PrereservationManualPickEdit (stored in table M_Prereservation_Pick_Edit).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PrereservationManualPickEdit extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Prereservation_Pick_Edit";
    public static final String ENTITY_NAME = "PrereservationManualPickEdit";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_PURCHASEORDERLINE = "purchaseOrderLine";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_ORDERDATE = "orderDate";
    public static final String PROPERTY_SCHEDULEDDELIVERYDATE = "scheduledDeliveryDate";
    public static final String PROPERTY_PURCHASEDQTY = "purchasedQty";
    public static final String PROPERTY_RECEIVEDQTY = "receivedQty";
    public static final String PROPERTY_RESERVEDQTY = "reservedQty";
    public static final String PROPERTY_RESERVATIONSTOCK = "reservationStock";
    public static final String PROPERTY_OBSELECTED = "obSelected";
    public static final String PROPERTY_ORDEREDQUANTITY = "orderedQuantity";
    public static final String PROPERTY_DELIVEREDQUANTITY = "deliveredQuantity";
    public static final String PROPERTY_STOCKRESERVATION = "stockReservation";
    public static final String PROPERTY_OTHERRESERVEDQTY = "otherReservedQty";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";

    public PrereservationManualPickEdit() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OBSELECTED, false);
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

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public OrderLine getPurchaseOrderLine() {
        return (OrderLine) get(PROPERTY_PURCHASEORDERLINE);
    }

    public void setPurchaseOrderLine(OrderLine purchaseOrderLine) {
        set(PROPERTY_PURCHASEORDERLINE, purchaseOrderLine);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
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

    public BigDecimal getPurchasedQty() {
        return (BigDecimal) get(PROPERTY_PURCHASEDQTY);
    }

    public void setPurchasedQty(BigDecimal purchasedQty) {
        set(PROPERTY_PURCHASEDQTY, purchasedQty);
    }

    public BigDecimal getReceivedQty() {
        return (BigDecimal) get(PROPERTY_RECEIVEDQTY);
    }

    public void setReceivedQty(BigDecimal receivedQty) {
        set(PROPERTY_RECEIVEDQTY, receivedQty);
    }

    public BigDecimal getReservedQty() {
        return (BigDecimal) get(PROPERTY_RESERVEDQTY);
    }

    public void setReservedQty(BigDecimal reservedQty) {
        set(PROPERTY_RESERVEDQTY, reservedQty);
    }

    public ReservationStock getReservationStock() {
        return (ReservationStock) get(PROPERTY_RESERVATIONSTOCK);
    }

    public void setReservationStock(ReservationStock reservationStock) {
        set(PROPERTY_RESERVATIONSTOCK, reservationStock);
    }

    public Boolean isObSelected() {
        return (Boolean) get(PROPERTY_OBSELECTED);
    }

    public void setObSelected(Boolean obSelected) {
        set(PROPERTY_OBSELECTED, obSelected);
    }

    public BigDecimal getOrderedQuantity() {
        return (BigDecimal) get(PROPERTY_ORDEREDQUANTITY);
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        set(PROPERTY_ORDEREDQUANTITY, orderedQuantity);
    }

    public BigDecimal getDeliveredQuantity() {
        return (BigDecimal) get(PROPERTY_DELIVEREDQUANTITY);
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        set(PROPERTY_DELIVEREDQUANTITY, deliveredQuantity);
    }

    public Reservation getStockReservation() {
        return (Reservation) get(PROPERTY_STOCKRESERVATION);
    }

    public void setStockReservation(Reservation stockReservation) {
        set(PROPERTY_STOCKRESERVATION, stockReservation);
    }

    public BigDecimal getOtherReservedQty() {
        return (BigDecimal) get(PROPERTY_OTHERRESERVEDQTY);
    }

    public void setOtherReservedQty(BigDecimal otherReservedQty) {
        set(PROPERTY_OTHERRESERVEDQTY, otherReservedQty);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

}
