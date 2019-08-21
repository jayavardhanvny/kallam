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
 * Entity class for entity Rchr_Memo_Shift (stored in table Rchr_Memo_Shift).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrMemoShift extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Memo_Shift";
    public static final String ENTITY_NAME = "Rchr_Memo_Shift";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_SHIFTDATE = "shiftdate";
    public static final String PROPERTY_MEMFROMDATE = "memfromdate";
    public static final String PROPERTY_MEMTODATE = "memtodate";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_NEWSHIFT = "newshift";
    public static final String PROPERTY_RCHRSHIFTGROUP = "rchrShiftgroup";
    public static final String PROPERTY_RELAYNAME = "relayName";
    public static final String PROPERTY_COMPLETE = "complete";
    public static final String PROPERTY_ISFLAG = "isflag";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_ISPAYROLL = "ispayroll";
    public static final String PROPERTY_RCHRSHIFTATTDLIST = "rchrShiftAttdList";

    public RchrMemoShift() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_COMPLETE, false);
        setDefaultValue(PROPERTY_ISFLAG, false);
        setDefaultValue(PROPERTY_ISPAYROLL, false);
        setDefaultValue(PROPERTY_RCHRSHIFTATTDLIST, new ArrayList<Object>());
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

    public Date getShiftdate() {
        return (Date) get(PROPERTY_SHIFTDATE);
    }

    public void setShiftdate(Date shiftdate) {
        set(PROPERTY_SHIFTDATE, shiftdate);
    }

    public Date getMemfromdate() {
        return (Date) get(PROPERTY_MEMFROMDATE);
    }

    public void setMemfromdate(Date memfromdate) {
        set(PROPERTY_MEMFROMDATE, memfromdate);
    }

    public Date getMemtodate() {
        return (Date) get(PROPERTY_MEMTODATE);
    }

    public void setMemtodate(Date memtodate) {
        set(PROPERTY_MEMTODATE, memtodate);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public RCPRShift getNewshift() {
        return (RCPRShift) get(PROPERTY_NEWSHIFT);
    }

    public void setNewshift(RCPRShift newshift) {
        set(PROPERTY_NEWSHIFT, newshift);
    }

    public RchrShiftgroup getRchrShiftgroup() {
        return (RchrShiftgroup) get(PROPERTY_RCHRSHIFTGROUP);
    }

    public void setRchrShiftgroup(RchrShiftgroup rchrShiftgroup) {
        set(PROPERTY_RCHRSHIFTGROUP, rchrShiftgroup);
    }

    public RchrMRelay getRelayName() {
        return (RchrMRelay) get(PROPERTY_RELAYNAME);
    }

    public void setRelayName(RchrMRelay relayName) {
        set(PROPERTY_RELAYNAME, relayName);
    }

    public Boolean isComplete() {
        return (Boolean) get(PROPERTY_COMPLETE);
    }

    public void setComplete(Boolean complete) {
        set(PROPERTY_COMPLETE, complete);
    }

    public Boolean isFlag() {
        return (Boolean) get(PROPERTY_ISFLAG);
    }

    public void setFlag(Boolean isflag) {
        set(PROPERTY_ISFLAG, isflag);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isPayroll() {
        return (Boolean) get(PROPERTY_ISPAYROLL);
    }

    public void setPayroll(Boolean ispayroll) {
        set(PROPERTY_ISPAYROLL, ispayroll);
    }

    @SuppressWarnings("unchecked")
    public List<RchrShiftAttd> getRchrShiftAttdList() {
        return (List<RchrShiftAttd>) get(PROPERTY_RCHRSHIFTATTDLIST);
    }

    public void setRchrShiftAttdList(List<RchrShiftAttd> rchrShiftAttdList) {
        set(PROPERTY_RCHRSHIFTATTDLIST, rchrShiftAttdList);
    }

}
