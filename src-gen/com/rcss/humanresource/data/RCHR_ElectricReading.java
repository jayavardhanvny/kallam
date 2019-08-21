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
package com.rcss.humanresource.data;

import com.redcarpet.meterreading.data.RcmrMeter;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCHR_ElectricReading (stored in table RCHR_ElectricReading).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_ElectricReading extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_ElectricReading";
    public static final String ENTITY_NAME = "RCHR_ElectricReading";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ROOM = "room";
    public static final String PROPERTY_INITIALREADING = "initialReading";
    public static final String PROPERTY_FINALREADING = "finalReading";
    public static final String PROPERTY_TOTALREADING = "totalReading";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_METERNUMBER = "meterNumber";
    public static final String PROPERTY_FROMDATE = "fromDate";
    public static final String PROPERTY_TODATE = "toDate";
    public static final String PROPERTY_AMOUNT = "amount";

    public RCHR_ElectricReading() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_INITIALREADING, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
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

    public RCHR_Room getRoom() {
        return (RCHR_Room) get(PROPERTY_ROOM);
    }

    public void setRoom(RCHR_Room room) {
        set(PROPERTY_ROOM, room);
    }

    public BigDecimal getInitialReading() {
        return (BigDecimal) get(PROPERTY_INITIALREADING);
    }

    public void setInitialReading(BigDecimal initialReading) {
        set(PROPERTY_INITIALREADING, initialReading);
    }

    public BigDecimal getFinalReading() {
        return (BigDecimal) get(PROPERTY_FINALREADING);
    }

    public void setFinalReading(BigDecimal finalReading) {
        set(PROPERTY_FINALREADING, finalReading);
    }

    public BigDecimal getTotalReading() {
        return (BigDecimal) get(PROPERTY_TOTALREADING);
    }

    public void setTotalReading(BigDecimal totalReading) {
        set(PROPERTY_TOTALREADING, totalReading);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public RcmrMeter getMeterNumber() {
        return (RcmrMeter) get(PROPERTY_METERNUMBER);
    }

    public void setMeterNumber(RcmrMeter meterNumber) {
        set(PROPERTY_METERNUMBER, meterNumber);
    }

    public Date getFromDate() {
        return (Date) get(PROPERTY_FROMDATE);
    }

    public void setFromDate(Date fromDate) {
        set(PROPERTY_FROMDATE, fromDate);
    }

    public Date getToDate() {
        return (Date) get(PROPERTY_TODATE);
    }

    public void setToDate(Date toDate) {
        set(PROPERTY_TODATE, toDate);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

}
