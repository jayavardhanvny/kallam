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
 * Entity class for entity RCPR_Carding (stored in table RCPR_Carding).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPRCarding extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Carding";
    public static final String ENTITY_NAME = "RCPR_Carding";
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
    public static final String PROPERTY_COUNT = "count";
    public static final String PROPERTY_HANK = "hank";
    public static final String PROPERTY_DELIVERYSPEEDMPM = "deliverySpeedMPM";
    public static final String PROPERTY_SHED = "shed";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_MACHINEWORKINGMINS = "machineWorkingMins";
    public static final String PROPERTY_MACHINESTOPPAGEMINS = "machineStoppageMins";
    public static final String PROPERTY_INITIALHANK = "initialHank";
    public static final String PROPERTY_FINALHANK = "finalHank";
    public static final String PROPERTY_PRODUCEDHANKS = "producedHanks";
    public static final String PROPERTY_PRODUCTIONORSHIFT = "productionOrShift";
    public static final String PROPERTY_EFFICIENCY = "efficiency";
    public static final String PROPERTY_TORF = "torf";
    public static final String PROPERTY_PRODUCTIONEFFECIENCYPLANNED = "productionEffeciencyPlanned";
    public static final String PROPERTY_PRODUCTIONEFFECIENCYACTUAL = "productionEffeciencyActual";
    public static final String PROPERTY_KGSPERDAY = "kgsPerDay";
    public static final String PROPERTY_SHIFTTIMEINMINS = "shiftTimeInMins";
    public static final String PROPERTY_MACHINEPROCESS = "machineProcess";
    public static final String PROPERTY_OEPROCESS = "oEProcess";

    public RCPRCarding() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MACHINEWORKINGMINS, new BigDecimal(8.5));
        setDefaultValue(PROPERTY_MACHINESTOPPAGEMINS, "0");
        setDefaultValue(PROPERTY_OEPROCESS, false);
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

    public RCPRCount getCount() {
        return (RCPRCount) get(PROPERTY_COUNT);
    }

    public void setCount(RCPRCount count) {
        set(PROPERTY_COUNT, count);
    }

    public String getHank() {
        return (String) get(PROPERTY_HANK);
    }

    public void setHank(String hank) {
        set(PROPERTY_HANK, hank);
    }

    public String getDeliverySpeedMPM() {
        return (String) get(PROPERTY_DELIVERYSPEEDMPM);
    }

    public void setDeliverySpeedMPM(String deliverySpeedMPM) {
        set(PROPERTY_DELIVERYSPEEDMPM, deliverySpeedMPM);
    }

    public RCPRShed getShed() {
        return (RCPRShed) get(PROPERTY_SHED);
    }

    public void setShed(RCPRShed shed) {
        set(PROPERTY_SHED, shed);
    }

    public String getLineNo() {
        return (String) get(PROPERTY_LINENO);
    }

    public void setLineNo(String lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public BigDecimal getMachineWorkingMins() {
        return (BigDecimal) get(PROPERTY_MACHINEWORKINGMINS);
    }

    public void setMachineWorkingMins(BigDecimal machineWorkingMins) {
        set(PROPERTY_MACHINEWORKINGMINS, machineWorkingMins);
    }

    public String getMachineStoppageMins() {
        return (String) get(PROPERTY_MACHINESTOPPAGEMINS);
    }

    public void setMachineStoppageMins(String machineStoppageMins) {
        set(PROPERTY_MACHINESTOPPAGEMINS, machineStoppageMins);
    }

    public BigDecimal getInitialHank() {
        return (BigDecimal) get(PROPERTY_INITIALHANK);
    }

    public void setInitialHank(BigDecimal initialHank) {
        set(PROPERTY_INITIALHANK, initialHank);
    }

    public BigDecimal getFinalHank() {
        return (BigDecimal) get(PROPERTY_FINALHANK);
    }

    public void setFinalHank(BigDecimal finalHank) {
        set(PROPERTY_FINALHANK, finalHank);
    }

    public BigDecimal getProducedHanks() {
        return (BigDecimal) get(PROPERTY_PRODUCEDHANKS);
    }

    public void setProducedHanks(BigDecimal producedHanks) {
        set(PROPERTY_PRODUCEDHANKS, producedHanks);
    }

    public String getProductionOrShift() {
        return (String) get(PROPERTY_PRODUCTIONORSHIFT);
    }

    public void setProductionOrShift(String productionOrShift) {
        set(PROPERTY_PRODUCTIONORSHIFT, productionOrShift);
    }

    public String getEfficiency() {
        return (String) get(PROPERTY_EFFICIENCY);
    }

    public void setEfficiency(String efficiency) {
        set(PROPERTY_EFFICIENCY, efficiency);
    }

    public String getTorf() {
        return (String) get(PROPERTY_TORF);
    }

    public void setTorf(String torf) {
        set(PROPERTY_TORF, torf);
    }

    public String getProductionEffeciencyPlanned() {
        return (String) get(PROPERTY_PRODUCTIONEFFECIENCYPLANNED);
    }

    public void setProductionEffeciencyPlanned(String productionEffeciencyPlanned) {
        set(PROPERTY_PRODUCTIONEFFECIENCYPLANNED, productionEffeciencyPlanned);
    }

    public String getProductionEffeciencyActual() {
        return (String) get(PROPERTY_PRODUCTIONEFFECIENCYACTUAL);
    }

    public void setProductionEffeciencyActual(String productionEffeciencyActual) {
        set(PROPERTY_PRODUCTIONEFFECIENCYACTUAL, productionEffeciencyActual);
    }

    public String getKgsPerDay() {
        return (String) get(PROPERTY_KGSPERDAY);
    }

    public void setKgsPerDay(String kgsPerDay) {
        set(PROPERTY_KGSPERDAY, kgsPerDay);
    }

    public BigDecimal getShiftTimeInMins() {
        return (BigDecimal) get(PROPERTY_SHIFTTIMEINMINS);
    }

    public void setShiftTimeInMins(BigDecimal shiftTimeInMins) {
        set(PROPERTY_SHIFTTIMEINMINS, shiftTimeInMins);
    }

    public RCPR_MachineProcess getMachineProcess() {
        return (RCPR_MachineProcess) get(PROPERTY_MACHINEPROCESS);
    }

    public void setMachineProcess(RCPR_MachineProcess machineProcess) {
        set(PROPERTY_MACHINEPROCESS, machineProcess);
    }

    public Boolean isOEProcess() {
        return (Boolean) get(PROPERTY_OEPROCESS);
    }

    public void setOEProcess(Boolean oEProcess) {
        set(PROPERTY_OEPROCESS, oEProcess);
    }

}
