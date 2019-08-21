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

import com.rcss.humanresource.data.RchrEmpDept;
import com.rcss.humanresource.data.RchrEmployee;
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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCPL_ProdIncentive (stored in table RCPL_ProdIncentive).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPLProdIncentive extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_ProdIncentive";
    public static final String ENTITY_NAME = "RCPL_ProdIncentive";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCPLLOOMCATEGORY = "rcplLoomcategory";
    public static final String PROPERTY_NOOFLOOMS = "nooflooms";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_SELECTLOOMS = "selectlooms";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_RCPLPRODINCENTIVETYPE = "rcplProdincentivetype";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_EFFAVG = "effavg";
    public static final String PROPERTY_SETRELEIVER = "setreleiver";
    public static final String PROPERTY_ISRELEIVER = "isreleiver";
    public static final String PROPERTY_YARNDYEDINCENTIVE = "yarndyedincentive";
    public static final String PROPERTY_LOOMSYARNTYPE = "loomsyarntype";
    public static final String PROPERTY_RCPLPRODINCENTIVELINELIST = "rCPLProdIncentiveLineList";

    public RCPLProdIncentive() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_SELECTLOOMS, true);
        setDefaultValue(PROPERTY_EFFAVG, new BigDecimal(0));
        setDefaultValue(PROPERTY_SETRELEIVER, false);
        setDefaultValue(PROPERTY_ISRELEIVER, false);
        setDefaultValue(PROPERTY_YARNDYEDINCENTIVE, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELINELIST, new ArrayList<Object>());
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

    public RCPLLoomCategory getRcplLoomcategory() {
        return (RCPLLoomCategory) get(PROPERTY_RCPLLOOMCATEGORY);
    }

    public void setRcplLoomcategory(RCPLLoomCategory rcplLoomcategory) {
        set(PROPERTY_RCPLLOOMCATEGORY, rcplLoomcategory);
    }

    public Long getNooflooms() {
        return (Long) get(PROPERTY_NOOFLOOMS);
    }

    public void setNooflooms(Long nooflooms) {
        set(PROPERTY_NOOFLOOMS, nooflooms);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public Boolean isSelectlooms() {
        return (Boolean) get(PROPERTY_SELECTLOOMS);
    }

    public void setSelectlooms(Boolean selectlooms) {
        set(PROPERTY_SELECTLOOMS, selectlooms);
    }

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public RCPLProdIncentiveType getRcplProdincentivetype() {
        return (RCPLProdIncentiveType) get(PROPERTY_RCPLPRODINCENTIVETYPE);
    }

    public void setRcplProdincentivetype(RCPLProdIncentiveType rcplProdincentivetype) {
        set(PROPERTY_RCPLPRODINCENTIVETYPE, rcplProdincentivetype);
    }

    public RchrEmpDept getEmployeeDepartment() {
        return (RchrEmpDept) get(PROPERTY_EMPLOYEEDEPARTMENT);
    }

    public void setEmployeeDepartment(RchrEmpDept employeeDepartment) {
        set(PROPERTY_EMPLOYEEDEPARTMENT, employeeDepartment);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public BigDecimal getEffavg() {
        return (BigDecimal) get(PROPERTY_EFFAVG);
    }

    public void setEffavg(BigDecimal effavg) {
        set(PROPERTY_EFFAVG, effavg);
    }

    public Boolean isSetreleiver() {
        return (Boolean) get(PROPERTY_SETRELEIVER);
    }

    public void setSetreleiver(Boolean setreleiver) {
        set(PROPERTY_SETRELEIVER, setreleiver);
    }

    public Boolean isReleiver() {
        return (Boolean) get(PROPERTY_ISRELEIVER);
    }

    public void setReleiver(Boolean isreleiver) {
        set(PROPERTY_ISRELEIVER, isreleiver);
    }

    public BigDecimal getYarndyedincentive() {
        return (BigDecimal) get(PROPERTY_YARNDYEDINCENTIVE);
    }

    public void setYarndyedincentive(BigDecimal yarndyedincentive) {
        set(PROPERTY_YARNDYEDINCENTIVE, yarndyedincentive);
    }

    public String getLoomsyarntype() {
        return (String) get(PROPERTY_LOOMSYARNTYPE);
    }

    public void setLoomsyarntype(String loomsyarntype) {
        set(PROPERTY_LOOMSYARNTYPE, loomsyarntype);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentiveLine> getRCPLProdIncentiveLineList() {
        return (List<RCPLProdIncentiveLine>) get(PROPERTY_RCPLPRODINCENTIVELINELIST);
    }

    public void setRCPLProdIncentiveLineList(List<RCPLProdIncentiveLine> rCPLProdIncentiveLineList) {
        set(PROPERTY_RCPLPRODINCENTIVELINELIST, rCPLProdIncentiveLineList);
    }

}
