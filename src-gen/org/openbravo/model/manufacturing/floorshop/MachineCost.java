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
package org.openbravo.model.manufacturing.floorshop;

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
 * Entity class for entity ManufacturingMachineCost (stored in table MA_Machine_Cost).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class MachineCost extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_Machine_Cost";
    public static final String ENTITY_NAME = "ManufacturingMachineCost";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_MACHINE = "machine";
    public static final String PROPERTY_COST = "cost";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_COSTUOM = "costUOM";
    public static final String PROPERTY_AMORTIZATION = "amortization";
    public static final String PROPERTY_CONSUME = "consume";
    public static final String PROPERTY_UOMANNUALCOST = "uOMAnnualCost";
    public static final String PROPERTY_WORKHOURSDAY = "workHoursDay";
    public static final String PROPERTY_WORKDAYSYEAR = "workDaysYear";
    public static final String PROPERTY_IDLETIMEYEAR = "idleTimeYear";
    public static final String PROPERTY_PURCHASEAMOUNT = "purchaseAmount";
    public static final String PROPERTY_TOOLSETAMOUNT = "toolsetAmount";
    public static final String PROPERTY_VALUEYEAR = "valueYear";

    public MachineCost() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public Machine getMachine() {
        return (Machine) get(PROPERTY_MACHINE);
    }

    public void setMachine(Machine machine) {
        set(PROPERTY_MACHINE, machine);
    }

    public BigDecimal getCost() {
        return (BigDecimal) get(PROPERTY_COST);
    }

    public void setCost(BigDecimal cost) {
        set(PROPERTY_COST, cost);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public String getCostUOM() {
        return (String) get(PROPERTY_COSTUOM);
    }

    public void setCostUOM(String costUOM) {
        set(PROPERTY_COSTUOM, costUOM);
    }

    public Long getAmortization() {
        return (Long) get(PROPERTY_AMORTIZATION);
    }

    public void setAmortization(Long amortization) {
        set(PROPERTY_AMORTIZATION, amortization);
    }

    public BigDecimal getConsume() {
        return (BigDecimal) get(PROPERTY_CONSUME);
    }

    public void setConsume(BigDecimal consume) {
        set(PROPERTY_CONSUME, consume);
    }

    public BigDecimal getUOMAnnualCost() {
        return (BigDecimal) get(PROPERTY_UOMANNUALCOST);
    }

    public void setUOMAnnualCost(BigDecimal uOMAnnualCost) {
        set(PROPERTY_UOMANNUALCOST, uOMAnnualCost);
    }

    public BigDecimal getWorkHoursDay() {
        return (BigDecimal) get(PROPERTY_WORKHOURSDAY);
    }

    public void setWorkHoursDay(BigDecimal workHoursDay) {
        set(PROPERTY_WORKHOURSDAY, workHoursDay);
    }

    public Long getWorkDaysYear() {
        return (Long) get(PROPERTY_WORKDAYSYEAR);
    }

    public void setWorkDaysYear(Long workDaysYear) {
        set(PROPERTY_WORKDAYSYEAR, workDaysYear);
    }

    public BigDecimal getIdleTimeYear() {
        return (BigDecimal) get(PROPERTY_IDLETIMEYEAR);
    }

    public void setIdleTimeYear(BigDecimal idleTimeYear) {
        set(PROPERTY_IDLETIMEYEAR, idleTimeYear);
    }

    public BigDecimal getPurchaseAmount() {
        return (BigDecimal) get(PROPERTY_PURCHASEAMOUNT);
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        set(PROPERTY_PURCHASEAMOUNT, purchaseAmount);
    }

    public BigDecimal getToolsetAmount() {
        return (BigDecimal) get(PROPERTY_TOOLSETAMOUNT);
    }

    public void setToolsetAmount(BigDecimal toolsetAmount) {
        set(PROPERTY_TOOLSETAMOUNT, toolsetAmount);
    }

    public BigDecimal getValueYear() {
        return (BigDecimal) get(PROPERTY_VALUEYEAR);
    }

    public void setValueYear(BigDecimal valueYear) {
        set(PROPERTY_VALUEYEAR, valueYear);
    }

}
