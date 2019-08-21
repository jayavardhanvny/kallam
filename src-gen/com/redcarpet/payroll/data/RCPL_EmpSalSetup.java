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

import com.rcss.humanresource.data.RchrEmployee;

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
 * Entity class for entity RCPL_EmpSalSetup (stored in table RCPL_EmpSalSetup).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPL_EmpSalSetup extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_EmpSalSetup";
    public static final String ENTITY_NAME = "RCPL_EmpSalSetup";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_GROSSAMTPERYEAR = "grossAmtPerYear";
    public static final String PROPERTY_GROSSLIMIT = "grossLimit";
    public static final String PROPERTY_PAYROLLTEMPLATE = "payrollTemplate";
    public static final String PROPERTY_PERDAYSALARY = "perDaySalary";
    public static final String PROPERTY_TAPERYEAR = "taperyear";
    public static final String PROPERTY_QADPERYEAR = "qadperyear";
    public static final String PROPERTY_PRODINCENTIVE = "prodincentive";
    public static final String PROPERTY_ISOPERATOR = "isoperator";
    public static final String PROPERTY_ISNOTDESIG = "isnotdesig";
    public static final String PROPERTY_PERDAYBASIC = "perdaybasic";
    public static final String PROPERTY_SERVICEINCE = "serviceince";
    public static final String PROPERTY_GRANDTOTALAMOUNT = "grandTotalAmount";
    public static final String PROPERTY_SETTLEMENT = "settlement";
    public static final String PROPERTY_RCPLEMPSALSETUPLINESLIST = "rcplEmpsalsetuplinesList";

    public RCPL_EmpSalSetup() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_GROSSAMTPERYEAR, new BigDecimal(0));
        setDefaultValue(PROPERTY_GROSSLIMIT, new BigDecimal(10000));
        setDefaultValue(PROPERTY_PERDAYSALARY, new BigDecimal(0));
        setDefaultValue(PROPERTY_TAPERYEAR, (long) 0);
        setDefaultValue(PROPERTY_QADPERYEAR, (long) 0);
        setDefaultValue(PROPERTY_PRODINCENTIVE, (long) 0);
        setDefaultValue(PROPERTY_ISOPERATOR, false);
        setDefaultValue(PROPERTY_ISNOTDESIG, false);
        setDefaultValue(PROPERTY_PERDAYBASIC, new BigDecimal(0));
        setDefaultValue(PROPERTY_SERVICEINCE, new BigDecimal(0));
        setDefaultValue(PROPERTY_GRANDTOTALAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_SETTLEMENT, false);
        setDefaultValue(PROPERTY_RCPLEMPSALSETUPLINESLIST, new ArrayList<Object>());
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

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public BigDecimal getGrossAmtPerYear() {
        return (BigDecimal) get(PROPERTY_GROSSAMTPERYEAR);
    }

    public void setGrossAmtPerYear(BigDecimal grossAmtPerYear) {
        set(PROPERTY_GROSSAMTPERYEAR, grossAmtPerYear);
    }

    public BigDecimal getGrossLimit() {
        return (BigDecimal) get(PROPERTY_GROSSLIMIT);
    }

    public void setGrossLimit(BigDecimal grossLimit) {
        set(PROPERTY_GROSSLIMIT, grossLimit);
    }

    public RCPL_PayrollTemplate getPayrollTemplate() {
        return (RCPL_PayrollTemplate) get(PROPERTY_PAYROLLTEMPLATE);
    }

    public void setPayrollTemplate(RCPL_PayrollTemplate payrollTemplate) {
        set(PROPERTY_PAYROLLTEMPLATE, payrollTemplate);
    }

    public BigDecimal getPerDaySalary() {
        return (BigDecimal) get(PROPERTY_PERDAYSALARY);
    }

    public void setPerDaySalary(BigDecimal perDaySalary) {
        set(PROPERTY_PERDAYSALARY, perDaySalary);
    }

    public Long getTaperyear() {
        return (Long) get(PROPERTY_TAPERYEAR);
    }

    public void setTaperyear(Long taperyear) {
        set(PROPERTY_TAPERYEAR, taperyear);
    }

    public Long getQadperyear() {
        return (Long) get(PROPERTY_QADPERYEAR);
    }

    public void setQadperyear(Long qadperyear) {
        set(PROPERTY_QADPERYEAR, qadperyear);
    }

    public Long getProdincentive() {
        return (Long) get(PROPERTY_PRODINCENTIVE);
    }

    public void setProdincentive(Long prodincentive) {
        set(PROPERTY_PRODINCENTIVE, prodincentive);
    }

    public Boolean isOperator() {
        return (Boolean) get(PROPERTY_ISOPERATOR);
    }

    public void setOperator(Boolean isoperator) {
        set(PROPERTY_ISOPERATOR, isoperator);
    }

    public Boolean isNotdesig() {
        return (Boolean) get(PROPERTY_ISNOTDESIG);
    }

    public void setNotdesig(Boolean isnotdesig) {
        set(PROPERTY_ISNOTDESIG, isnotdesig);
    }

    public BigDecimal getPerdaybasic() {
        return (BigDecimal) get(PROPERTY_PERDAYBASIC);
    }

    public void setPerdaybasic(BigDecimal perdaybasic) {
        set(PROPERTY_PERDAYBASIC, perdaybasic);
    }

    public BigDecimal getServiceince() {
        return (BigDecimal) get(PROPERTY_SERVICEINCE);
    }

    public void setServiceince(BigDecimal serviceince) {
        set(PROPERTY_SERVICEINCE, serviceince);
    }

    public BigDecimal getGrandTotalAmount() {
        return (BigDecimal) get(PROPERTY_GRANDTOTALAMOUNT);
    }

    public void setGrandTotalAmount(BigDecimal grandTotalAmount) {
        set(PROPERTY_GRANDTOTALAMOUNT, grandTotalAmount);
    }

    public Boolean isSettlement() {
        return (Boolean) get(PROPERTY_SETTLEMENT);
    }

    public void setSettlement(Boolean settlement) {
        set(PROPERTY_SETTLEMENT, settlement);
    }

    @SuppressWarnings("unchecked")
    public List<RcplEmpsalsetuplines> getRcplEmpsalsetuplinesList() {
        return (List<RcplEmpsalsetuplines>) get(PROPERTY_RCPLEMPSALSETUPLINESLIST);
    }

    public void setRcplEmpsalsetuplinesList(List<RcplEmpsalsetuplines> rcplEmpsalsetuplinesList) {
        set(PROPERTY_RCPLEMPSALSETUPLINESLIST, rcplEmpsalsetuplinesList);
    }

}
