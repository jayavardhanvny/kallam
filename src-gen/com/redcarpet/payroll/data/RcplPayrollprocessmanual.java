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

import com.rcss.humanresource.data.RchrAttdProcess;

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
 * Entity class for entity Rcpl_Payrollprocessmanual (stored in table Rcpl_Payrollprocessmanual).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RcplPayrollprocessmanual extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rcpl_Payrollprocessmanual";
    public static final String ENTITY_NAME = "Rcpl_Payrollprocessmanual";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_PROCESSINGDAYS = "processingDays";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PAYROLLPOSTED = "payrollPosted";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_UNPOST = "unPost";
    public static final String PROPERTY_DOCUMENT = "document";
    public static final String PROPERTY_PAYROLLPERIOD = "payrollPeriod";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_GETEMPLOYEES = "getemployees";
    public static final String PROPERTY_RCPLEMPPPROCESSMANUALLIST = "rcplEmppprocessmanualList";

    public RcplPayrollprocessmanual() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PROCESSINGDAYS, (long) 0);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PAYROLLPOSTED, false);
        setDefaultValue(PROPERTY_POSTED, false);
        setDefaultValue(PROPERTY_UNPOST, false);
        setDefaultValue(PROPERTY_GETEMPLOYEES, false);
        setDefaultValue(PROPERTY_RCPLEMPPPROCESSMANUALLIST, new ArrayList<Object>());
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

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public Long getProcessingDays() {
        return (Long) get(PROPERTY_PROCESSINGDAYS);
    }

    public void setProcessingDays(Long processingDays) {
        set(PROPERTY_PROCESSINGDAYS, processingDays);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isPayrollPosted() {
        return (Boolean) get(PROPERTY_PAYROLLPOSTED);
    }

    public void setPayrollPosted(Boolean payrollPosted) {
        set(PROPERTY_PAYROLLPOSTED, payrollPosted);
    }

    public Boolean isPosted() {
        return (Boolean) get(PROPERTY_POSTED);
    }

    public void setPosted(Boolean posted) {
        set(PROPERTY_POSTED, posted);
    }

    public Boolean isUnPost() {
        return (Boolean) get(PROPERTY_UNPOST);
    }

    public void setUnPost(Boolean unPost) {
        set(PROPERTY_UNPOST, unPost);
    }

    public String getDocument() {
        return (String) get(PROPERTY_DOCUMENT);
    }

    public void setDocument(String document) {
        set(PROPERTY_DOCUMENT, document);
    }

    public RchrAttdProcess getPayrollPeriod() {
        return (RchrAttdProcess) get(PROPERTY_PAYROLLPERIOD);
    }

    public void setPayrollPeriod(RchrAttdProcess payrollPeriod) {
        set(PROPERTY_PAYROLLPERIOD, payrollPeriod);
    }

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public Boolean isGetemployees() {
        return (Boolean) get(PROPERTY_GETEMPLOYEES);
    }

    public void setGetemployees(Boolean getemployees) {
        set(PROPERTY_GETEMPLOYEES, getemployees);
    }

    @SuppressWarnings("unchecked")
    public List<RcplEmppprocessmanual> getRcplEmppprocessmanualList() {
        return (List<RcplEmppprocessmanual>) get(PROPERTY_RCPLEMPPPROCESSMANUALLIST);
    }

    public void setRcplEmppprocessmanualList(List<RcplEmppprocessmanual> rcplEmppprocessmanualList) {
        set(PROPERTY_RCPLEMPPPROCESSMANUALLIST, rcplEmppprocessmanualList);
    }

}
