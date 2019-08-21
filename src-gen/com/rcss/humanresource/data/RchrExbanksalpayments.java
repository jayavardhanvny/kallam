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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Rchr_Exbanksalpayments (stored in table Rchr_Exbanksalpayments).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrExbanksalpayments extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Exbanksalpayments";
    public static final String ENTITY_NAME = "Rchr_Exbanksalpayments";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_RCHRATTDPROCESS = "rchrAttdProcess";
    public static final String PROPERTY_TOTALAMOUNT = "totalamount";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_PAYMENTDATE = "paymentDate";
    public static final String PROPERTY_PAYMENTTYPE = "paymentType";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PROCESSONLINE = "processonline";
    public static final String PROPERTY_ONLINECNT = "onlinecnt";
    public static final String PROPERTY_AGENTCOMMISSION = "agentcommission";
    public static final String PROPERTY_SALARIESAMOUNT = "salariesamount";
    public static final String PROPERTY_PROCESSINGAMOUNT = "processingAmount";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST = "rchrExbanksalpaymentsempList";

    public RchrExbanksalpayments() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_TOTALAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PROCESSONLINE, false);
        setDefaultValue(PROPERTY_ONLINECNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_AGENTCOMMISSION, (long) 0);
        setDefaultValue(PROPERTY_SALARIESAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST, new ArrayList<Object>());
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

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public RchrAttdProcess getRchrAttdProcess() {
        return (RchrAttdProcess) get(PROPERTY_RCHRATTDPROCESS);
    }

    public void setRchrAttdProcess(RchrAttdProcess rchrAttdProcess) {
        set(PROPERTY_RCHRATTDPROCESS, rchrAttdProcess);
    }

    public BigDecimal getTotalamount() {
        return (BigDecimal) get(PROPERTY_TOTALAMOUNT);
    }

    public void setTotalamount(BigDecimal totalamount) {
        set(PROPERTY_TOTALAMOUNT, totalamount);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public Date getPaymentDate() {
        return (Date) get(PROPERTY_PAYMENTDATE);
    }

    public void setPaymentDate(Date paymentDate) {
        set(PROPERTY_PAYMENTDATE, paymentDate);
    }

    public String getPaymentType() {
        return (String) get(PROPERTY_PAYMENTTYPE);
    }

    public void setPaymentType(String paymentType) {
        set(PROPERTY_PAYMENTTYPE, paymentType);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isProcessonline() {
        return (Boolean) get(PROPERTY_PROCESSONLINE);
    }

    public void setProcessonline(Boolean processonline) {
        set(PROPERTY_PROCESSONLINE, processonline);
    }

    public BigDecimal getOnlinecnt() {
        return (BigDecimal) get(PROPERTY_ONLINECNT);
    }

    public void setOnlinecnt(BigDecimal onlinecnt) {
        set(PROPERTY_ONLINECNT, onlinecnt);
    }

    public Long getAgentcommission() {
        return (Long) get(PROPERTY_AGENTCOMMISSION);
    }

    public void setAgentcommission(Long agentcommission) {
        set(PROPERTY_AGENTCOMMISSION, agentcommission);
    }

    public BigDecimal getSalariesamount() {
        return (BigDecimal) get(PROPERTY_SALARIESAMOUNT);
    }

    public void setSalariesamount(BigDecimal salariesamount) {
        set(PROPERTY_SALARIESAMOUNT, salariesamount);
    }

    public BigDecimal getProcessingAmount() {
        return (BigDecimal) get(PROPERTY_PROCESSINGAMOUNT);
    }

    public void setProcessingAmount(BigDecimal processingAmount) {
        set(PROPERTY_PROCESSINGAMOUNT, processingAmount);
    }

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpaymentsemp> getRchrExbanksalpaymentsempList() {
        return (List<RchrExbanksalpaymentsemp>) get(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST);
    }

    public void setRchrExbanksalpaymentsempList(List<RchrExbanksalpaymentsemp> rchrExbanksalpaymentsempList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST, rchrExbanksalpaymentsempList);
    }

}
