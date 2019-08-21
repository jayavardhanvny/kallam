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

import com.redcarpet.production.data.RCPRCount;
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
 * Entity class for entity RCQA_Cardingneps (stored in table rcqa_cardingneps).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RcqaCardingneps extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "rcqa_cardingneps";
    public static final String ENTITY_NAME = "RCQA_Cardingneps";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCPRCOUNT = "rcprCount";
    public static final String PROPERTY_RCPRMACHINE = "rcprMachine";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_RCPRSHED = "rcprShed";
    public static final String PROPERTY_RCPRMACHINEPROCESS = "rcprMachineprocess";
    public static final String PROPERTY_HANK = "hank";
    public static final String PROPERTY_SPEED = "speed";
    public static final String PROPERTY_CHUTENEPS = "chuteneps";
    public static final String PROPERTY_SLIVERNEPS = "sliverneps";
    public static final String PROPERTY_NRE = "nre";
    public static final String PROPERTY_RCPRCOUNTMASTER = "rcprCountmaster";

    public RcqaCardingneps() {
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

    public RCPRCount getRcprCount() {
        return (RCPRCount) get(PROPERTY_RCPRCOUNT);
    }

    public void setRcprCount(RCPRCount rcprCount) {
        set(PROPERTY_RCPRCOUNT, rcprCount);
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

    public BigDecimal getHank() {
        return (BigDecimal) get(PROPERTY_HANK);
    }

    public void setHank(BigDecimal hank) {
        set(PROPERTY_HANK, hank);
    }

    public Long getSpeed() {
        return (Long) get(PROPERTY_SPEED);
    }

    public void setSpeed(Long speed) {
        set(PROPERTY_SPEED, speed);
    }

    public Long getChuteneps() {
        return (Long) get(PROPERTY_CHUTENEPS);
    }

    public void setChuteneps(Long chuteneps) {
        set(PROPERTY_CHUTENEPS, chuteneps);
    }

    public Long getSliverneps() {
        return (Long) get(PROPERTY_SLIVERNEPS);
    }

    public void setSliverneps(Long sliverneps) {
        set(PROPERTY_SLIVERNEPS, sliverneps);
    }

    public BigDecimal getNre() {
        return (BigDecimal) get(PROPERTY_NRE);
    }

    public void setNre(BigDecimal nre) {
        set(PROPERTY_NRE, nre);
    }

    public RCPR_CountMaster getRcprCountmaster() {
        return (RCPR_CountMaster) get(PROPERTY_RCPRCOUNTMASTER);
    }

    public void setRcprCountmaster(RCPR_CountMaster rcprCountmaster) {
        set(PROPERTY_RCPRCOUNTMASTER, rcprCountmaster);
    }

}
