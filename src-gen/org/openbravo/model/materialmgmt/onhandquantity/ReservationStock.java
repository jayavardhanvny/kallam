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
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.AttributeSetInstance;
/**
 * Entity class for entity MaterialMgmtReservationStock (stored in table M_Reservation_Stock).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ReservationStock extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_Reservation_Stock";
    public static final String ENTITY_NAME = "MaterialMgmtReservationStock";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RESERVATION = "reservation";
    public static final String PROPERTY_STORAGEDETAIL = "storageDetail";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_ALLOCATED = "allocated";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_RELEASED = "released";
    public static final String PROPERTY_PRERESERVATIONMANUALPICKEDITLIST = "prereservationManualPickEditList";
    public static final String PROPERTY_RESERVATIONMANUALPICKEDITLIST = "reservationManualPickEditList";
    public static final String PROPERTY_SOLRESERVEDSTOCKLIST = "sOLReservedStockList";

    public ReservationStock() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ALLOCATED, false);
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

    public Reservation getReservation() {
        return (Reservation) get(PROPERTY_RESERVATION);
    }

    public void setReservation(Reservation reservation) {
        set(PROPERTY_RESERVATION, reservation);
    }

    public AttributeSetInstance getStorageDetail() {
        return (AttributeSetInstance) get(PROPERTY_STORAGEDETAIL);
    }

    public void setStorageDetail(AttributeSetInstance storageDetail) {
        set(PROPERTY_STORAGEDETAIL, storageDetail);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public Boolean isAllocated() {
        return (Boolean) get(PROPERTY_ALLOCATED);
    }

    public void setAllocated(Boolean allocated) {
        set(PROPERTY_ALLOCATED, allocated);
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

    public BigDecimal getReleased() {
        return (BigDecimal) get(PROPERTY_RELEASED);
    }

    public void setReleased(BigDecimal released) {
        set(PROPERTY_RELEASED, released);
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
