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
package com.redcarpet.production.data;

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
 * Entity class for entity RCPR_Oe (stored in table RCPR_Oe).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPR_Oe extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Oe";
    public static final String ENTITY_NAME = "RCPR_Oe";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROCESSDATE = "processDate";
    public static final String PROPERTY_MACHINEPROCESS = "machineProcess";
    public static final String PROPERTY_SHIFT = "shift";
    public static final String PROPERTY_MACHINE = "machine";
    public static final String PROPERTY_COUNT = "count";
    public static final String PROPERTY_ROTARSPEEDRPM = "rotarSpeedRPM";
    public static final String PROPERTY_TPIMD = "tPIMD";
    public static final String PROPERTY_STANDARDEFFICENCY = "standardEfficency";
    public static final String PROPERTY_ACTUALEFFECIENCY = "actualEffeciency";
    public static final String PROPERTY_SYSTEMEFFECIENCY = "systemEffeciency";
    public static final String PROPERTY_PRODUCTIONINKGS = "productionInKGS";
    public static final String PROPERTY_RELLIGNT = "reLlignt";
    public static final String PROPERTY_SHIFTTIMEMINS = "shiftTimeMins";
    public static final String PROPERTY_MACHINEWORKINGMINS = "machineWorkingMins";
    public static final String PROPERTY_MACHINESTOPPAGEMINS = "machineStoppageMins";
    public static final String PROPERTY_CHEESECOLOR = "cheeseColor";
    public static final String PROPERTY_CHEESEWEIGHT = "cheeseWeight";
    public static final String PROPERTY_NOOFCHEESEWORKED = "noOfCheeseWorked";
    public static final String PROPERTY_EMPLOYEENO = "employeeNo";

    public RCPR_Oe() {
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

    public Date getProcessDate() {
        return (Date) get(PROPERTY_PROCESSDATE);
    }

    public void setProcessDate(Date processDate) {
        set(PROPERTY_PROCESSDATE, processDate);
    }

    public RCPR_MachineProcess getMachineProcess() {
        return (RCPR_MachineProcess) get(PROPERTY_MACHINEPROCESS);
    }

    public void setMachineProcess(RCPR_MachineProcess machineProcess) {
        set(PROPERTY_MACHINEPROCESS, machineProcess);
    }

    public RCPRShift getShift() {
        return (RCPRShift) get(PROPERTY_SHIFT);
    }

    public void setShift(RCPRShift shift) {
        set(PROPERTY_SHIFT, shift);
    }

    public RCPRMachine getMachine() {
        return (RCPRMachine) get(PROPERTY_MACHINE);
    }

    public void setMachine(RCPRMachine machine) {
        set(PROPERTY_MACHINE, machine);
    }

    public RCPRCount getCount() {
        return (RCPRCount) get(PROPERTY_COUNT);
    }

    public void setCount(RCPRCount count) {
        set(PROPERTY_COUNT, count);
    }

    public BigDecimal getRotarSpeedRPM() {
        return (BigDecimal) get(PROPERTY_ROTARSPEEDRPM);
    }

    public void setRotarSpeedRPM(BigDecimal rotarSpeedRPM) {
        set(PROPERTY_ROTARSPEEDRPM, rotarSpeedRPM);
    }

    public BigDecimal getTPIMD() {
        return (BigDecimal) get(PROPERTY_TPIMD);
    }

    public void setTPIMD(BigDecimal tPIMD) {
        set(PROPERTY_TPIMD, tPIMD);
    }

    public BigDecimal getStandardEfficency() {
        return (BigDecimal) get(PROPERTY_STANDARDEFFICENCY);
    }

    public void setStandardEfficency(BigDecimal standardEfficency) {
        set(PROPERTY_STANDARDEFFICENCY, standardEfficency);
    }

    public BigDecimal getActualEffeciency() {
        return (BigDecimal) get(PROPERTY_ACTUALEFFECIENCY);
    }

    public void setActualEffeciency(BigDecimal actualEffeciency) {
        set(PROPERTY_ACTUALEFFECIENCY, actualEffeciency);
    }

    public BigDecimal getSystemEffeciency() {
        return (BigDecimal) get(PROPERTY_SYSTEMEFFECIENCY);
    }

    public void setSystemEffeciency(BigDecimal systemEffeciency) {
        set(PROPERTY_SYSTEMEFFECIENCY, systemEffeciency);
    }

    public BigDecimal getProductionInKGS() {
        return (BigDecimal) get(PROPERTY_PRODUCTIONINKGS);
    }

    public void setProductionInKGS(BigDecimal productionInKGS) {
        set(PROPERTY_PRODUCTIONINKGS, productionInKGS);
    }

    public BigDecimal getReLlignt() {
        return (BigDecimal) get(PROPERTY_RELLIGNT);
    }

    public void setReLlignt(BigDecimal reLlignt) {
        set(PROPERTY_RELLIGNT, reLlignt);
    }

    public BigDecimal getShiftTimeMins() {
        return (BigDecimal) get(PROPERTY_SHIFTTIMEMINS);
    }

    public void setShiftTimeMins(BigDecimal shiftTimeMins) {
        set(PROPERTY_SHIFTTIMEMINS, shiftTimeMins);
    }

    public BigDecimal getMachineWorkingMins() {
        return (BigDecimal) get(PROPERTY_MACHINEWORKINGMINS);
    }

    public void setMachineWorkingMins(BigDecimal machineWorkingMins) {
        set(PROPERTY_MACHINEWORKINGMINS, machineWorkingMins);
    }

    public BigDecimal getMachineStoppageMins() {
        return (BigDecimal) get(PROPERTY_MACHINESTOPPAGEMINS);
    }

    public void setMachineStoppageMins(BigDecimal machineStoppageMins) {
        set(PROPERTY_MACHINESTOPPAGEMINS, machineStoppageMins);
    }

    public String getCheeseColor() {
        return (String) get(PROPERTY_CHEESECOLOR);
    }

    public void setCheeseColor(String cheeseColor) {
        set(PROPERTY_CHEESECOLOR, cheeseColor);
    }

    public String getCheeseWeight() {
        return (String) get(PROPERTY_CHEESEWEIGHT);
    }

    public void setCheeseWeight(String cheeseWeight) {
        set(PROPERTY_CHEESEWEIGHT, cheeseWeight);
    }

    public String getNoOfCheeseWorked() {
        return (String) get(PROPERTY_NOOFCHEESEWORKED);
    }

    public void setNoOfCheeseWorked(String noOfCheeseWorked) {
        set(PROPERTY_NOOFCHEESEWORKED, noOfCheeseWorked);
    }

    public String getEmployeeNo() {
        return (String) get(PROPERTY_EMPLOYEENO);
    }

    public void setEmployeeNo(String employeeNo) {
        set(PROPERTY_EMPLOYEENO, employeeNo);
    }

}
