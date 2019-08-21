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
package com.redcarpet.epcg.data;

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
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.order.Order;
/**
 * Entity class for entity Epcg_EpcgNew (stored in table Epcg_EpcgNew).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Epcg_EpcgNew extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_EpcgNew";
    public static final String ENTITY_NAME = "Epcg_EpcgNew";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EPCGDATE = "epcgDate";
    public static final String PROPERTY_EPCGLICENSENO = "epcgLicenseNo";
    public static final String PROPERTY_PERMITFROMDATE = "permitFromDate";
    public static final String PROPERTY_PERMITTODATE = "permitToDate";
    public static final String PROPERTY_VALIDITYDATE = "validityDate";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PERMITTOTAL = "permitTotal";
    public static final String PROPERTY_PURCHASEIMPORTTOTAL = "purchaseImportTotal";
    public static final String PROPERTY_SALESEXPORTTOTAL = "salesExportTotal";
    public static final String PROPERTY_TENUREYEARS = "tenureYears";
    public static final String PROPERTY_PROCESSONE = "processone";
    public static final String PROPERTY_TOTALTARGET = "totalTarget";
    public static final String PROPERTY_CHECKONE = "checkone";
    public static final String PROPERTY_MULTIPLEFACTOR = "multipleFactor";
    public static final String PROPERTY_EPCGEPCGPURCHASELIST = "epcgEpcgPurchaseList";
    public static final String PROPERTY_EPCGEPCGSALESLIST = "epcgEpcgSalesList";
    public static final String PROPERTY_EPCGMACSPARELIST = "epcgMacSpareList";
    public static final String PROPERTY_EPCGTARGETLIST = "epcgTargetList";
    public static final String PROPERTY_INVOICEEMEPCGEPCGONELIST = "invoiceEMEpcgEpcgoneList";
    public static final String PROPERTY_ORDEREMEPCGEPCGONELIST = "orderEMEpcgEpcgoneList";

    public Epcg_EpcgNew() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PURCHASEIMPORTTOTAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_SALESEXPORTTOTAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESSONE, false);
        setDefaultValue(PROPERTY_EPCGEPCGPURCHASELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGEPCGSALESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGMACSPARELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGTARGETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEEMEPCGEPCGONELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDEREMEPCGEPCGONELIST, new ArrayList<Object>());
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

    public Date getEpcgDate() {
        return (Date) get(PROPERTY_EPCGDATE);
    }

    public void setEpcgDate(Date epcgDate) {
        set(PROPERTY_EPCGDATE, epcgDate);
    }

    public String getEpcgLicenseNo() {
        return (String) get(PROPERTY_EPCGLICENSENO);
    }

    public void setEpcgLicenseNo(String epcgLicenseNo) {
        set(PROPERTY_EPCGLICENSENO, epcgLicenseNo);
    }

    public Date getPermitFromDate() {
        return (Date) get(PROPERTY_PERMITFROMDATE);
    }

    public void setPermitFromDate(Date permitFromDate) {
        set(PROPERTY_PERMITFROMDATE, permitFromDate);
    }

    public Date getPermitToDate() {
        return (Date) get(PROPERTY_PERMITTODATE);
    }

    public void setPermitToDate(Date permitToDate) {
        set(PROPERTY_PERMITTODATE, permitToDate);
    }

    public Date getValidityDate() {
        return (Date) get(PROPERTY_VALIDITYDATE);
    }

    public void setValidityDate(Date validityDate) {
        set(PROPERTY_VALIDITYDATE, validityDate);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public BigDecimal getPermitTotal() {
        return (BigDecimal) get(PROPERTY_PERMITTOTAL);
    }

    public void setPermitTotal(BigDecimal permitTotal) {
        set(PROPERTY_PERMITTOTAL, permitTotal);
    }

    public BigDecimal getPurchaseImportTotal() {
        return (BigDecimal) get(PROPERTY_PURCHASEIMPORTTOTAL);
    }

    public void setPurchaseImportTotal(BigDecimal purchaseImportTotal) {
        set(PROPERTY_PURCHASEIMPORTTOTAL, purchaseImportTotal);
    }

    public BigDecimal getSalesExportTotal() {
        return (BigDecimal) get(PROPERTY_SALESEXPORTTOTAL);
    }

    public void setSalesExportTotal(BigDecimal salesExportTotal) {
        set(PROPERTY_SALESEXPORTTOTAL, salesExportTotal);
    }

    public BigDecimal getTenureYears() {
        return (BigDecimal) get(PROPERTY_TENUREYEARS);
    }

    public void setTenureYears(BigDecimal tenureYears) {
        set(PROPERTY_TENUREYEARS, tenureYears);
    }

    public Boolean isProcessone() {
        return (Boolean) get(PROPERTY_PROCESSONE);
    }

    public void setProcessone(Boolean processone) {
        set(PROPERTY_PROCESSONE, processone);
    }

    public BigDecimal getTotalTarget() {
        return (BigDecimal) get(PROPERTY_TOTALTARGET);
    }

    public void setTotalTarget(BigDecimal totalTarget) {
        set(PROPERTY_TOTALTARGET, totalTarget);
    }

    public String getCheckone() {
        return (String) get(PROPERTY_CHECKONE);
    }

    public void setCheckone(String checkone) {
        set(PROPERTY_CHECKONE, checkone);
    }

    public BigDecimal getMultipleFactor() {
        return (BigDecimal) get(PROPERTY_MULTIPLEFACTOR);
    }

    public void setMultipleFactor(BigDecimal multipleFactor) {
        set(PROPERTY_MULTIPLEFACTOR, multipleFactor);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgEpcgPurchase> getEpcgEpcgPurchaseList() {
        return (List<EpcgEpcgPurchase>) get(PROPERTY_EPCGEPCGPURCHASELIST);
    }

    public void setEpcgEpcgPurchaseList(List<EpcgEpcgPurchase> epcgEpcgPurchaseList) {
        set(PROPERTY_EPCGEPCGPURCHASELIST, epcgEpcgPurchaseList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgEpcgSales> getEpcgEpcgSalesList() {
        return (List<EpcgEpcgSales>) get(PROPERTY_EPCGEPCGSALESLIST);
    }

    public void setEpcgEpcgSalesList(List<EpcgEpcgSales> epcgEpcgSalesList) {
        set(PROPERTY_EPCGEPCGSALESLIST, epcgEpcgSalesList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgMacSpare> getEpcgMacSpareList() {
        return (List<EpcgMacSpare>) get(PROPERTY_EPCGMACSPARELIST);
    }

    public void setEpcgMacSpareList(List<EpcgMacSpare> epcgMacSpareList) {
        set(PROPERTY_EPCGMACSPARELIST, epcgMacSpareList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgTarget> getEpcgTargetList() {
        return (List<EpcgTarget>) get(PROPERTY_EPCGTARGETLIST);
    }

    public void setEpcgTargetList(List<EpcgTarget> epcgTargetList) {
        set(PROPERTY_EPCGTARGETLIST, epcgTargetList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceEMEpcgEpcgoneList() {
        return (List<Invoice>) get(PROPERTY_INVOICEEMEPCGEPCGONELIST);
    }

    public void setInvoiceEMEpcgEpcgoneList(List<Invoice> invoiceEMEpcgEpcgoneList) {
        set(PROPERTY_INVOICEEMEPCGEPCGONELIST, invoiceEMEpcgEpcgoneList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderEMEpcgEpcgoneList() {
        return (List<Order>) get(PROPERTY_ORDEREMEPCGEPCGONELIST);
    }

    public void setOrderEMEpcgEpcgoneList(List<Order> orderEMEpcgEpcgoneList) {
        set(PROPERTY_ORDEREMEPCGEPCGONELIST, orderEMEpcgEpcgoneList);
    }

}
