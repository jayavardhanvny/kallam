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
 * Entity class for entity RCHR_Employeesettlement (stored in table RCHR_Employeesettlement).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Employeesettlement extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Employeesettlement";
    public static final String ENTITY_NAME = "RCHR_Employeesettlement";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_SETTLEMENTDATE = "settlementdate";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_PRESENTDAYS = "presentdays";
    public static final String PROPERTY_PERDAYSALARY = "perdaysalary";
    public static final String PROPERTY_GROSS = "gross";
    public static final String PROPERTY_TOTALDEDUCTION = "totaldeduction";
    public static final String PROPERTY_GRANDTOTALAMOUNT = "grandTotalAmount";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_COMPLETED = "completed";
    public static final String PROPERTY_PRODUCTIONINCNETIVE = "productionincnetive";
    public static final String PROPERTY_OTHERINCNETIVE = "otherincnetive";
    public static final String PROPERTY_ATTDINCENTIVE = "attdincentive";
    public static final String PROPERTY_OTINCENTIVE = "otincentive";
    public static final String PROPERTY_CSHIFTALLOWANCE = "cshiftallowance";
    public static final String PROPERTY_EMPLOYEESTATUS = "employeestatus";
    public static final String PROPERTY_RCHRSETTLEMNTLINESLIST = "rCHRSettlemntlinesList";

    public RCHR_Employeesettlement() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_GROSS, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALDEDUCTION, new BigDecimal(0));
        setDefaultValue(PROPERTY_GRANDTOTALAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_COMPLETED, false);
        setDefaultValue(PROPERTY_PRODUCTIONINCNETIVE, new BigDecimal(0));
        setDefaultValue(PROPERTY_OTHERINCNETIVE, new BigDecimal(0));
        setDefaultValue(PROPERTY_ATTDINCENTIVE, (long) 0);
        setDefaultValue(PROPERTY_OTINCENTIVE, (long) 0);
        setDefaultValue(PROPERTY_CSHIFTALLOWANCE, (long) 0);
        setDefaultValue(PROPERTY_RCHRSETTLEMNTLINESLIST, new ArrayList<Object>());
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

    public Date getSettlementdate() {
        return (Date) get(PROPERTY_SETTLEMENTDATE);
    }

    public void setSettlementdate(Date settlementdate) {
        set(PROPERTY_SETTLEMENTDATE, settlementdate);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Long getPresentdays() {
        return (Long) get(PROPERTY_PRESENTDAYS);
    }

    public void setPresentdays(Long presentdays) {
        set(PROPERTY_PRESENTDAYS, presentdays);
    }

    public BigDecimal getPerdaysalary() {
        return (BigDecimal) get(PROPERTY_PERDAYSALARY);
    }

    public void setPerdaysalary(BigDecimal perdaysalary) {
        set(PROPERTY_PERDAYSALARY, perdaysalary);
    }

    public BigDecimal getGross() {
        return (BigDecimal) get(PROPERTY_GROSS);
    }

    public void setGross(BigDecimal gross) {
        set(PROPERTY_GROSS, gross);
    }

    public BigDecimal getTotaldeduction() {
        return (BigDecimal) get(PROPERTY_TOTALDEDUCTION);
    }

    public void setTotaldeduction(BigDecimal totaldeduction) {
        set(PROPERTY_TOTALDEDUCTION, totaldeduction);
    }

    public BigDecimal getGrandTotalAmount() {
        return (BigDecimal) get(PROPERTY_GRANDTOTALAMOUNT);
    }

    public void setGrandTotalAmount(BigDecimal grandTotalAmount) {
        set(PROPERTY_GRANDTOTALAMOUNT, grandTotalAmount);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isCompleted() {
        return (Boolean) get(PROPERTY_COMPLETED);
    }

    public void setCompleted(Boolean completed) {
        set(PROPERTY_COMPLETED, completed);
    }

    public BigDecimal getProductionincnetive() {
        return (BigDecimal) get(PROPERTY_PRODUCTIONINCNETIVE);
    }

    public void setProductionincnetive(BigDecimal productionincnetive) {
        set(PROPERTY_PRODUCTIONINCNETIVE, productionincnetive);
    }

    public BigDecimal getOtherincnetive() {
        return (BigDecimal) get(PROPERTY_OTHERINCNETIVE);
    }

    public void setOtherincnetive(BigDecimal otherincnetive) {
        set(PROPERTY_OTHERINCNETIVE, otherincnetive);
    }

    public Long getAttdincentive() {
        return (Long) get(PROPERTY_ATTDINCENTIVE);
    }

    public void setAttdincentive(Long attdincentive) {
        set(PROPERTY_ATTDINCENTIVE, attdincentive);
    }

    public Long getOtincentive() {
        return (Long) get(PROPERTY_OTINCENTIVE);
    }

    public void setOtincentive(Long otincentive) {
        set(PROPERTY_OTINCENTIVE, otincentive);
    }

    public Long getCshiftallowance() {
        return (Long) get(PROPERTY_CSHIFTALLOWANCE);
    }

    public void setCshiftallowance(Long cshiftallowance) {
        set(PROPERTY_CSHIFTALLOWANCE, cshiftallowance);
    }

    public String getEmployeestatus() {
        return (String) get(PROPERTY_EMPLOYEESTATUS);
    }

    public void setEmployeestatus(String employeestatus) {
        set(PROPERTY_EMPLOYEESTATUS, employeestatus);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Settlemntlines> getRCHRSettlemntlinesList() {
        return (List<RCHR_Settlemntlines>) get(PROPERTY_RCHRSETTLEMNTLINESLIST);
    }

    public void setRCHRSettlemntlinesList(List<RCHR_Settlemntlines> rCHRSettlemntlinesList) {
        set(PROPERTY_RCHRSETTLEMNTLINESLIST, rCHRSettlemntlinesList);
    }

}
