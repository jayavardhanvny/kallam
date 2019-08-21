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
package com.redcarpet.payroll.data;

import com.rcss.humanresource.data.RchrJoinRejoiningform;

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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCPL_PayrollTemplate (stored in table RCPL_PayrollTemplate).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPL_PayrollTemplate extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_PayrollTemplate";
    public static final String ENTITY_NAME = "RCPL_PayrollTemplate";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PFLIMIT = "pFLimit";
    public static final String PROPERTY_ESILIMIT = "eSILimit";
    public static final String PROPERTY_UNITRATE = "unitRate";
    public static final String PROPERTY_CLEANINGCHARGES = "cleaningCharges";
    public static final String PROPERTY_WELFAREFUND = "welfareFund";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_DISHCHARGES = "dishCharges";
    public static final String PROPERTY_RCPLEMPSALSETUPLIST = "rCPLEmpSalSetupList";
    public static final String PROPERTY_RCPLPTSLABLIST = "rCPLPTSlabList";
    public static final String PROPERTY_RCPLPAYROLLINCENTIVELINESLIST = "rCPLPayrollIncentiveLinesList";
    public static final String PROPERTY_RCPLPAYROLLTEMPLATELINESLIST = "rCPLPayrollTemplateLinesList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";

    public RCPL_PayrollTemplate() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PFLIMIT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ESILIMIT, new BigDecimal(0));
        setDefaultValue(PROPERTY_UNITRATE, new BigDecimal(0));
        setDefaultValue(PROPERTY_CLEANINGCHARGES, new BigDecimal(0));
        setDefaultValue(PROPERTY_WELFAREFUND, new BigDecimal(0));
        setDefaultValue(PROPERTY_DISHCHARGES, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCPLEMPSALSETUPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPTSLABLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLINCENTIVELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLTEMPLATELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public BigDecimal getPFLimit() {
        return (BigDecimal) get(PROPERTY_PFLIMIT);
    }

    public void setPFLimit(BigDecimal pFLimit) {
        set(PROPERTY_PFLIMIT, pFLimit);
    }

    public BigDecimal getESILimit() {
        return (BigDecimal) get(PROPERTY_ESILIMIT);
    }

    public void setESILimit(BigDecimal eSILimit) {
        set(PROPERTY_ESILIMIT, eSILimit);
    }

    public BigDecimal getUnitRate() {
        return (BigDecimal) get(PROPERTY_UNITRATE);
    }

    public void setUnitRate(BigDecimal unitRate) {
        set(PROPERTY_UNITRATE, unitRate);
    }

    public BigDecimal getCleaningCharges() {
        return (BigDecimal) get(PROPERTY_CLEANINGCHARGES);
    }

    public void setCleaningCharges(BigDecimal cleaningCharges) {
        set(PROPERTY_CLEANINGCHARGES, cleaningCharges);
    }

    public BigDecimal getWelfareFund() {
        return (BigDecimal) get(PROPERTY_WELFAREFUND);
    }

    public void setWelfareFund(BigDecimal welfareFund) {
        set(PROPERTY_WELFAREFUND, welfareFund);
    }

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public BigDecimal getDishCharges() {
        return (BigDecimal) get(PROPERTY_DISHCHARGES);
    }

    public void setDishCharges(BigDecimal dishCharges) {
        set(PROPERTY_DISHCHARGES, dishCharges);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpSalSetup> getRCPLEmpSalSetupList() {
        return (List<RCPL_EmpSalSetup>) get(PROPERTY_RCPLEMPSALSETUPLIST);
    }

    public void setRCPLEmpSalSetupList(List<RCPL_EmpSalSetup> rCPLEmpSalSetupList) {
        set(PROPERTY_RCPLEMPSALSETUPLIST, rCPLEmpSalSetupList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_PTSlab> getRCPLPTSlabList() {
        return (List<RCPL_PTSlab>) get(PROPERTY_RCPLPTSLABLIST);
    }

    public void setRCPLPTSlabList(List<RCPL_PTSlab> rCPLPTSlabList) {
        set(PROPERTY_RCPLPTSLABLIST, rCPLPTSlabList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_PayrollIncentiveLines> getRCPLPayrollIncentiveLinesList() {
        return (List<RCPL_PayrollIncentiveLines>) get(PROPERTY_RCPLPAYROLLINCENTIVELINESLIST);
    }

    public void setRCPLPayrollIncentiveLinesList(List<RCPL_PayrollIncentiveLines> rCPLPayrollIncentiveLinesList) {
        set(PROPERTY_RCPLPAYROLLINCENTIVELINESLIST, rCPLPayrollIncentiveLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_PayrollTemplateLines> getRCPLPayrollTemplateLinesList() {
        return (List<RCPL_PayrollTemplateLines>) get(PROPERTY_RCPLPAYROLLTEMPLATELINESLIST);
    }

    public void setRCPLPayrollTemplateLinesList(List<RCPL_PayrollTemplateLines> rCPLPayrollTemplateLinesList) {
        set(PROPERTY_RCPLPAYROLLTEMPLATELINESLIST, rCPLPayrollTemplateLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

}
