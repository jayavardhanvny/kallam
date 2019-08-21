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

import com.redcarpet.production.data.RCPRShift;

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
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCHR_OverTime (stored in table RCHR_OverTime).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_OverTime extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_OverTime";
    public static final String ENTITY_NAME = "RCHR_OverTime";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_OTTYPE = "oTType";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_SHFIT = "shfit";
    public static final String PROPERTY_DAYRATE = "dayRate";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_ISPAID = "isPaid";
    public static final String PROPERTY_OVERTIMEHEADER = "overTimeHeader";
    public static final String PROPERTY_SERVICEDAYS = "servicedays";
    public static final String PROPERTY_SEVICEINCENTIVE = "seviceincentive";
    public static final String PROPERTY_NUMBEROFOTS = "numberofots";
    public static final String PROPERTY_NUMBEROFCONTINUES = "numberofcontinues";
    public static final String PROPERTY_PAIDDATE = "paiddate";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_RCHROVERTIMEDATESLIST = "rchrOvertimedatesList";

    public RCHR_OverTime() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_DAYRATE, new BigDecimal(0));
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ISPAID, false);
        setDefaultValue(PROPERTY_NUMBEROFOTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_NUMBEROFCONTINUES, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCHROVERTIMEDATESLIST, new ArrayList<Object>());
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

    public String getOTType() {
        return (String) get(PROPERTY_OTTYPE);
    }

    public void setOTType(String oTType) {
        set(PROPERTY_OTTYPE, oTType);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public RchrEmpDept getEmployeeDepartment() {
        return (RchrEmpDept) get(PROPERTY_EMPLOYEEDEPARTMENT);
    }

    public void setEmployeeDepartment(RchrEmpDept employeeDepartment) {
        set(PROPERTY_EMPLOYEEDEPARTMENT, employeeDepartment);
    }

    public RCPRShift getShfit() {
        return (RCPRShift) get(PROPERTY_SHFIT);
    }

    public void setShfit(RCPRShift shfit) {
        set(PROPERTY_SHFIT, shfit);
    }

    public BigDecimal getDayRate() {
        return (BigDecimal) get(PROPERTY_DAYRATE);
    }

    public void setDayRate(BigDecimal dayRate) {
        set(PROPERTY_DAYRATE, dayRate);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public Boolean isPaid() {
        return (Boolean) get(PROPERTY_ISPAID);
    }

    public void setPaid(Boolean isPaid) {
        set(PROPERTY_ISPAID, isPaid);
    }

    public RCHR_OverTimeHeader getOverTimeHeader() {
        return (RCHR_OverTimeHeader) get(PROPERTY_OVERTIMEHEADER);
    }

    public void setOverTimeHeader(RCHR_OverTimeHeader overTimeHeader) {
        set(PROPERTY_OVERTIMEHEADER, overTimeHeader);
    }

    public BigDecimal getServicedays() {
        return (BigDecimal) get(PROPERTY_SERVICEDAYS);
    }

    public void setServicedays(BigDecimal servicedays) {
        set(PROPERTY_SERVICEDAYS, servicedays);
    }

    public Long getSeviceincentive() {
        return (Long) get(PROPERTY_SEVICEINCENTIVE);
    }

    public void setSeviceincentive(Long seviceincentive) {
        set(PROPERTY_SEVICEINCENTIVE, seviceincentive);
    }

    public BigDecimal getNumberofots() {
        return (BigDecimal) get(PROPERTY_NUMBEROFOTS);
    }

    public void setNumberofots(BigDecimal numberofots) {
        set(PROPERTY_NUMBEROFOTS, numberofots);
    }

    public BigDecimal getNumberofcontinues() {
        return (BigDecimal) get(PROPERTY_NUMBEROFCONTINUES);
    }

    public void setNumberofcontinues(BigDecimal numberofcontinues) {
        set(PROPERTY_NUMBEROFCONTINUES, numberofcontinues);
    }

    public Date getPaiddate() {
        return (Date) get(PROPERTY_PAIDDATE);
    }

    public void setPaiddate(Date paiddate) {
        set(PROPERTY_PAIDDATE, paiddate);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    @SuppressWarnings("unchecked")
    public List<RchrOvertimedates> getRchrOvertimedatesList() {
        return (List<RchrOvertimedates>) get(PROPERTY_RCHROVERTIMEDATESLIST);
    }

    public void setRchrOvertimedatesList(List<RchrOvertimedates> rchrOvertimedatesList) {
        set(PROPERTY_RCHROVERTIMEDATESLIST, rchrOvertimedatesList);
    }

}
