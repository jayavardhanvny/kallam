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
 * Entity class for entity RCPR_Autoclone (stored in table RCPR_Autoclone).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Autoclone extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Autoclone";
    public static final String ENTITY_NAME = "RCPR_Autoclone";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROCESSDATE = "processDate";
    public static final String PROPERTY_SHIFT = "shift";
    public static final String PROPERTY_MACHINE = "machine";
    public static final String PROPERTY_NOOFDRUMSPERMACHINE = "noOfDrumsPerMachine";
    public static final String PROPERTY_COUNTWORKING = "countWorking";
    public static final String PROPERTY_SPEEDMPM = "speedMPM";
    public static final String PROPERTY_NOOFDRUMSALLOTTEDPERCOUNT = "noOfDrumsAllottedPerCount";
    public static final String PROPERTY_STANDARDEFFICIENCY = "standardEfficiency";
    public static final String PROPERTY_ACTUALEFFICIENCY = "actualEfficiency";
    public static final String PROPERTY_SYSTEMEFFICIENCY = "systemEfficiency";
    public static final String PROPERTY_ACTUALPRODKGS = "actualprodkgs";
    public static final String PROPERTY_REDLIGHT = "redLight";
    public static final String PROPERTY_SHIFTTIMEINMINS = "shiftTimeInMins";
    public static final String PROPERTY_MACHINEWORKINGMINS = "machineWorkingMins";
    public static final String PROPERTY_MACHINESTOPPAGETIME = "machineStoppageTime";
    public static final String PROPERTY_COUNT = "count";
    public static final String PROPERTY_MACHINEPROCESS = "machineProcess";

    public Autoclone() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MACHINESTOPPAGETIME, new BigDecimal(0));
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

    public String getNoOfDrumsPerMachine() {
        return (String) get(PROPERTY_NOOFDRUMSPERMACHINE);
    }

    public void setNoOfDrumsPerMachine(String noOfDrumsPerMachine) {
        set(PROPERTY_NOOFDRUMSPERMACHINE, noOfDrumsPerMachine);
    }

    public String getCountWorking() {
        return (String) get(PROPERTY_COUNTWORKING);
    }

    public void setCountWorking(String countWorking) {
        set(PROPERTY_COUNTWORKING, countWorking);
    }

    public String getSpeedMPM() {
        return (String) get(PROPERTY_SPEEDMPM);
    }

    public void setSpeedMPM(String speedMPM) {
        set(PROPERTY_SPEEDMPM, speedMPM);
    }

    public String getNoOfDrumsAllottedPerCount() {
        return (String) get(PROPERTY_NOOFDRUMSALLOTTEDPERCOUNT);
    }

    public void setNoOfDrumsAllottedPerCount(String noOfDrumsAllottedPerCount) {
        set(PROPERTY_NOOFDRUMSALLOTTEDPERCOUNT, noOfDrumsAllottedPerCount);
    }

    public String getStandardEfficiency() {
        return (String) get(PROPERTY_STANDARDEFFICIENCY);
    }

    public void setStandardEfficiency(String standardEfficiency) {
        set(PROPERTY_STANDARDEFFICIENCY, standardEfficiency);
    }

    public String getActualEfficiency() {
        return (String) get(PROPERTY_ACTUALEFFICIENCY);
    }

    public void setActualEfficiency(String actualEfficiency) {
        set(PROPERTY_ACTUALEFFICIENCY, actualEfficiency);
    }

    public String getSystemEfficiency() {
        return (String) get(PROPERTY_SYSTEMEFFICIENCY);
    }

    public void setSystemEfficiency(String systemEfficiency) {
        set(PROPERTY_SYSTEMEFFICIENCY, systemEfficiency);
    }

    public String getActualprodkgs() {
        return (String) get(PROPERTY_ACTUALPRODKGS);
    }

    public void setActualprodkgs(String actualprodkgs) {
        set(PROPERTY_ACTUALPRODKGS, actualprodkgs);
    }

    public String getRedLight() {
        return (String) get(PROPERTY_REDLIGHT);
    }

    public void setRedLight(String redLight) {
        set(PROPERTY_REDLIGHT, redLight);
    }

    public BigDecimal getShiftTimeInMins() {
        return (BigDecimal) get(PROPERTY_SHIFTTIMEINMINS);
    }

    public void setShiftTimeInMins(BigDecimal shiftTimeInMins) {
        set(PROPERTY_SHIFTTIMEINMINS, shiftTimeInMins);
    }

    public BigDecimal getMachineWorkingMins() {
        return (BigDecimal) get(PROPERTY_MACHINEWORKINGMINS);
    }

    public void setMachineWorkingMins(BigDecimal machineWorkingMins) {
        set(PROPERTY_MACHINEWORKINGMINS, machineWorkingMins);
    }

    public BigDecimal getMachineStoppageTime() {
        return (BigDecimal) get(PROPERTY_MACHINESTOPPAGETIME);
    }

    public void setMachineStoppageTime(BigDecimal machineStoppageTime) {
        set(PROPERTY_MACHINESTOPPAGETIME, machineStoppageTime);
    }

    public RCPRCount getCount() {
        return (RCPRCount) get(PROPERTY_COUNT);
    }

    public void setCount(RCPRCount count) {
        set(PROPERTY_COUNT, count);
    }

    public RCPR_MachineProcess getMachineProcess() {
        return (RCPR_MachineProcess) get(PROPERTY_MACHINEPROCESS);
    }

    public void setMachineProcess(RCPR_MachineProcess machineProcess) {
        set(PROPERTY_MACHINEPROCESS, machineProcess);
    }

}
