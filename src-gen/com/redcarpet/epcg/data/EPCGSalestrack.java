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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity EPCG_Salestrack (stored in table EPCG_Salestrack).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EPCGSalestrack extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "EPCG_Salestrack";
    public static final String ENTITY_NAME = "EPCG_Salestrack";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_FROMDATE = "fromDate";
    public static final String PROPERTY_TODATE = "toDate";
    public static final String PROPERTY_FORMTYPE = "formtype";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_FORMNO = "formno";
    public static final String PROPERTY_SUMMEDLINEAMOUNT = "summedLineAmount";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PROCESSNEW = "processnew";
    public static final String PROPERTY_PROCESSNEWONE = "processnewone";
    public static final String PROPERTY_EPCGSALESTRACKLINELIST = "ePCGSalestracklineList";

    public EPCGSalestrack() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMEDLINEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PROCESSNEW, false);
        setDefaultValue(PROPERTY_PROCESSNEWONE, false);
        setDefaultValue(PROPERTY_EPCGSALESTRACKLINELIST, new ArrayList<Object>());
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

    public Date getFromDate() {
        return (Date) get(PROPERTY_FROMDATE);
    }

    public void setFromDate(Date fromDate) {
        set(PROPERTY_FROMDATE, fromDate);
    }

    public Date getToDate() {
        return (Date) get(PROPERTY_TODATE);
    }

    public void setToDate(Date toDate) {
        set(PROPERTY_TODATE, toDate);
    }

    public String getFormtype() {
        return (String) get(PROPERTY_FORMTYPE);
    }

    public void setFormtype(String formtype) {
        set(PROPERTY_FORMTYPE, formtype);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getFormno() {
        return (String) get(PROPERTY_FORMNO);
    }

    public void setFormno(String formno) {
        set(PROPERTY_FORMNO, formno);
    }

    public BigDecimal getSummedLineAmount() {
        return (BigDecimal) get(PROPERTY_SUMMEDLINEAMOUNT);
    }

    public void setSummedLineAmount(BigDecimal summedLineAmount) {
        set(PROPERTY_SUMMEDLINEAMOUNT, summedLineAmount);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isProcessnew() {
        return (Boolean) get(PROPERTY_PROCESSNEW);
    }

    public void setProcessnew(Boolean processnew) {
        set(PROPERTY_PROCESSNEW, processnew);
    }

    public Boolean isProcessnewone() {
        return (Boolean) get(PROPERTY_PROCESSNEWONE);
    }

    public void setProcessnewone(Boolean processnewone) {
        set(PROPERTY_PROCESSNEWONE, processnewone);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGSalestrackline> getEPCGSalestracklineList() {
        return (List<EPCGSalestrackline>) get(PROPERTY_EPCGSALESTRACKLINELIST);
    }

    public void setEPCGSalestracklineList(List<EPCGSalestrackline> ePCGSalestracklineList) {
        set(PROPERTY_EPCGSALESTRACKLINELIST, ePCGSalestracklineList);
    }

}
