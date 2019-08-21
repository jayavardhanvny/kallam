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
 * Entity class for entity RCPR_Spinning (stored in table RCPR_Spinning).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPR_Spinning extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Spinning";
    public static final String ENTITY_NAME = "RCPR_Spinning";
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
    public static final String PROPERTY_NOOFSPINDLE = "noOfSpindle";
    public static final String PROPERTY_COUNT = "count";
    public static final String PROPERTY_HANK = "hank";
    public static final String PROPERTY_MACHINEWORKINGMINS = "machineWorkingMins";
    public static final String PROPERTY_MACHINESTOPPAGEMINS = "machineStoppageMins";
    public static final String PROPERTY_STOPPAGESPINDLES = "stoppageSpindles";
    public static final String PROPERTY_WORKEDSPINDLES = "workedSpindles";
    public static final String PROPERTY_PRODUCEDHANKS = "producedHanks";
    public static final String PROPERTY_PRODUCTIONCONSTANT = "productionConstant";
    public static final String PROPERTY_PRODUCEDKGS = "producedKGS";
    public static final String PROPERTY_PRODUCEDGRAMS = "producedGrams";
    public static final String PROPERTY_MACHWISEUTILIZATION = "machwiseUtilization";
    public static final String PROPERTY_STANDARDGRAMS = "standardGrams";
    public static final String PROPERTY_DEVIATIONGRAMS = "deviationGrams";
    public static final String PROPERTY_ESTIMATEDPRODUCTION = "estimatedProduction";
    public static final String PROPERTY_KGLOSSPERMACHINE = "kgLossPerMachine";
    public static final String PROPERTY_GRAMSLOSTPERSPINDLE = "gramsLostPerSpindle";
    public static final String PROPERTY_SHIFTTIMEINMINS = "shiftTimeInMins";
    public static final String PROPERTY_MACHINEPROCESS = "machineProcess";

    public RCPR_Spinning() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MACHINESTOPPAGEMINS, new BigDecimal(0));
        setDefaultValue(PROPERTY_STOPPAGESPINDLES, new BigDecimal(0));
        setDefaultValue(PROPERTY_SHIFTTIMEINMINS, new BigDecimal(0));
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

    public BigDecimal getNoOfSpindle() {
        return (BigDecimal) get(PROPERTY_NOOFSPINDLE);
    }

    public void setNoOfSpindle(BigDecimal noOfSpindle) {
        set(PROPERTY_NOOFSPINDLE, noOfSpindle);
    }

    public RCPRCount getCount() {
        return (RCPRCount) get(PROPERTY_COUNT);
    }

    public void setCount(RCPRCount count) {
        set(PROPERTY_COUNT, count);
    }

    public BigDecimal getHank() {
        return (BigDecimal) get(PROPERTY_HANK);
    }

    public void setHank(BigDecimal hank) {
        set(PROPERTY_HANK, hank);
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

    public BigDecimal getStoppageSpindles() {
        return (BigDecimal) get(PROPERTY_STOPPAGESPINDLES);
    }

    public void setStoppageSpindles(BigDecimal stoppageSpindles) {
        set(PROPERTY_STOPPAGESPINDLES, stoppageSpindles);
    }

    public BigDecimal getWorkedSpindles() {
        return (BigDecimal) get(PROPERTY_WORKEDSPINDLES);
    }

    public void setWorkedSpindles(BigDecimal workedSpindles) {
        set(PROPERTY_WORKEDSPINDLES, workedSpindles);
    }

    public BigDecimal getProducedHanks() {
        return (BigDecimal) get(PROPERTY_PRODUCEDHANKS);
    }

    public void setProducedHanks(BigDecimal producedHanks) {
        set(PROPERTY_PRODUCEDHANKS, producedHanks);
    }

    public BigDecimal getProductionConstant() {
        return (BigDecimal) get(PROPERTY_PRODUCTIONCONSTANT);
    }

    public void setProductionConstant(BigDecimal productionConstant) {
        set(PROPERTY_PRODUCTIONCONSTANT, productionConstant);
    }

    public BigDecimal getProducedKGS() {
        return (BigDecimal) get(PROPERTY_PRODUCEDKGS);
    }

    public void setProducedKGS(BigDecimal producedKGS) {
        set(PROPERTY_PRODUCEDKGS, producedKGS);
    }

    public BigDecimal getProducedGrams() {
        return (BigDecimal) get(PROPERTY_PRODUCEDGRAMS);
    }

    public void setProducedGrams(BigDecimal producedGrams) {
        set(PROPERTY_PRODUCEDGRAMS, producedGrams);
    }

    public BigDecimal getMachwiseUtilization() {
        return (BigDecimal) get(PROPERTY_MACHWISEUTILIZATION);
    }

    public void setMachwiseUtilization(BigDecimal machwiseUtilization) {
        set(PROPERTY_MACHWISEUTILIZATION, machwiseUtilization);
    }

    public BigDecimal getStandardGrams() {
        return (BigDecimal) get(PROPERTY_STANDARDGRAMS);
    }

    public void setStandardGrams(BigDecimal standardGrams) {
        set(PROPERTY_STANDARDGRAMS, standardGrams);
    }

    public BigDecimal getDeviationGrams() {
        return (BigDecimal) get(PROPERTY_DEVIATIONGRAMS);
    }

    public void setDeviationGrams(BigDecimal deviationGrams) {
        set(PROPERTY_DEVIATIONGRAMS, deviationGrams);
    }

    public BigDecimal getEstimatedProduction() {
        return (BigDecimal) get(PROPERTY_ESTIMATEDPRODUCTION);
    }

    public void setEstimatedProduction(BigDecimal estimatedProduction) {
        set(PROPERTY_ESTIMATEDPRODUCTION, estimatedProduction);
    }

    public BigDecimal getKgLossPerMachine() {
        return (BigDecimal) get(PROPERTY_KGLOSSPERMACHINE);
    }

    public void setKgLossPerMachine(BigDecimal kgLossPerMachine) {
        set(PROPERTY_KGLOSSPERMACHINE, kgLossPerMachine);
    }

    public BigDecimal getGramsLostPerSpindle() {
        return (BigDecimal) get(PROPERTY_GRAMSLOSTPERSPINDLE);
    }

    public void setGramsLostPerSpindle(BigDecimal gramsLostPerSpindle) {
        set(PROPERTY_GRAMSLOSTPERSPINDLE, gramsLostPerSpindle);
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

}
