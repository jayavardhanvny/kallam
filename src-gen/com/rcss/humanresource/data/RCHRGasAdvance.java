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
 * Entity class for entity RCHR_Gas_Advance (stored in table RCHR_Gas_Advance).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRGasAdvance extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Gas_Advance";
    public static final String ENTITY_NAME = "RCHR_Gas_Advance";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_RCHRATTDPROCESS = "rchrAttdProcess";
    public static final String PROPERTY_ADVANCEAMOUNT = "advanceamount";
    public static final String PROPERTY_NOOFINSTALLMENTS = "noofinstallments";
    public static final String PROPERTY_PROCESSING = "processing";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_NOOFPRESENTS = "noofpresents";
    public static final String PROPERTY_RCHRGASADVANCELINESLIST = "rCHRGasAdvancelinesList";

    public RCHRGasAdvance() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSING, false);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_RCHRGASADVANCELINESLIST, new ArrayList<Object>());
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public RchrAttdProcess getRchrAttdProcess() {
        return (RchrAttdProcess) get(PROPERTY_RCHRATTDPROCESS);
    }

    public void setRchrAttdProcess(RchrAttdProcess rchrAttdProcess) {
        set(PROPERTY_RCHRATTDPROCESS, rchrAttdProcess);
    }

    public BigDecimal getAdvanceamount() {
        return (BigDecimal) get(PROPERTY_ADVANCEAMOUNT);
    }

    public void setAdvanceamount(BigDecimal advanceamount) {
        set(PROPERTY_ADVANCEAMOUNT, advanceamount);
    }

    public BigDecimal getNoofinstallments() {
        return (BigDecimal) get(PROPERTY_NOOFINSTALLMENTS);
    }

    public void setNoofinstallments(BigDecimal noofinstallments) {
        set(PROPERTY_NOOFINSTALLMENTS, noofinstallments);
    }

    public Boolean isProcessing() {
        return (Boolean) get(PROPERTY_PROCESSING);
    }

    public void setProcessing(Boolean processing) {
        set(PROPERTY_PROCESSING, processing);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public BigDecimal getNoofpresents() {
        return (BigDecimal) get(PROPERTY_NOOFPRESENTS);
    }

    public void setNoofpresents(BigDecimal noofpresents) {
        set(PROPERTY_NOOFPRESENTS, noofpresents);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRGasAdvancelines> getRCHRGasAdvancelinesList() {
        return (List<RCHRGasAdvancelines>) get(PROPERTY_RCHRGASADVANCELINESLIST);
    }

    public void setRCHRGasAdvancelinesList(List<RCHRGasAdvancelines> rCHRGasAdvancelinesList) {
        set(PROPERTY_RCHRGASADVANCELINESLIST, rCHRGasAdvancelinesList);
    }

}
