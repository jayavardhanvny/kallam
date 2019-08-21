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
package com.redcarpet.quality.data;

import com.redcarpet.production.data.RCPRMachine;
import com.redcarpet.production.data.RCPRShed;
import com.redcarpet.production.data.RCPR_CountMaster;
import com.redcarpet.production.data.RCPR_MachineProcess;

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
 * Entity class for entity RCQA_Combernoil (stored in table rcqa_combernoil).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RcqaCombernoil extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "rcqa_combernoil";
    public static final String ENTITY_NAME = "RCQA_Combernoil";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCPRMACHINE = "rcprMachine";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_RCPRSHED = "rcprShed";
    public static final String PROPERTY_RCPRMACHINEPROCESS = "rcprMachineprocess";
    public static final String PROPERTY_SLIVER = "sliver";
    public static final String PROPERTY_NOILPERCENTAGE = "noilpercentage";
    public static final String PROPERTY_NOIL = "noil";
    public static final String PROPERTY_TOTALNOIL = "totalnoil";
    public static final String PROPERTY_RCPRCOUNTMASTER = "rcprCountmaster";

    public RcqaCombernoil() {
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

    public RCPRMachine getRcprMachine() {
        return (RCPRMachine) get(PROPERTY_RCPRMACHINE);
    }

    public void setRcprMachine(RCPRMachine rcprMachine) {
        set(PROPERTY_RCPRMACHINE, rcprMachine);
    }

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public RCPRShed getRcprShed() {
        return (RCPRShed) get(PROPERTY_RCPRSHED);
    }

    public void setRcprShed(RCPRShed rcprShed) {
        set(PROPERTY_RCPRSHED, rcprShed);
    }

    public RCPR_MachineProcess getRcprMachineprocess() {
        return (RCPR_MachineProcess) get(PROPERTY_RCPRMACHINEPROCESS);
    }

    public void setRcprMachineprocess(RCPR_MachineProcess rcprMachineprocess) {
        set(PROPERTY_RCPRMACHINEPROCESS, rcprMachineprocess);
    }

    public BigDecimal getSliver() {
        return (BigDecimal) get(PROPERTY_SLIVER);
    }

    public void setSliver(BigDecimal sliver) {
        set(PROPERTY_SLIVER, sliver);
    }

    public BigDecimal getNoilpercentage() {
        return (BigDecimal) get(PROPERTY_NOILPERCENTAGE);
    }

    public void setNoilpercentage(BigDecimal noilpercentage) {
        set(PROPERTY_NOILPERCENTAGE, noilpercentage);
    }

    public BigDecimal getNoil() {
        return (BigDecimal) get(PROPERTY_NOIL);
    }

    public void setNoil(BigDecimal noil) {
        set(PROPERTY_NOIL, noil);
    }

    public BigDecimal getTotalnoil() {
        return (BigDecimal) get(PROPERTY_TOTALNOIL);
    }

    public void setTotalnoil(BigDecimal totalnoil) {
        set(PROPERTY_TOTALNOIL, totalnoil);
    }

    public RCPR_CountMaster getRcprCountmaster() {
        return (RCPR_CountMaster) get(PROPERTY_RCPRCOUNTMASTER);
    }

    public void setRcprCountmaster(RCPR_CountMaster rcprCountmaster) {
        set(PROPERTY_RCPRCOUNTMASTER, rcprCountmaster);
    }

}
