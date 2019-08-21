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

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCHR_ApprovalHistrory (stored in table RCHR_ApprovalHistrory).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrApprovalHistrory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_ApprovalHistrory";
    public static final String ENTITY_NAME = "RCHR_ApprovalHistrory";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_TAB = "tab";
    public static final String PROPERTY_RECORDID = "recordid";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_DECISION = "decision";
    public static final String PROPERTY_REASON = "reason";
    public static final String PROPERTY_APRPOVEBY = "aprpoveby";
    public static final String PROPERTY_APPROVEDTIME = "approvedtime";
    public static final String PROPERTY_LASTAPPROVED = "lastapproved";
    public static final String PROPERTY_REQUESTDATE = "requestdate";
    public static final String PROPERTY_APPROVE = "approve";
    public static final String PROPERTY_UPDATECOUNT = "updatecount";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATE = "rchrApprovaltemplate";
    public static final String PROPERTY_APPROVEUSER = "approveuser";
    public static final String PROPERTY_RCHRAPPROVALSTAGE = "rchrApprovalstage";
    public static final String PROPERTY_APPROVEBYUSER = "approvebyuser";
    public static final String PROPERTY_BESTOFF = "bestoff";
    public static final String PROPERTY_STAGECOUNT = "stagecount";
    public static final String PROPERTY_REJECT = "reject";
    public static final String PROPERTY_RECORDIDENTIFICATION = "recordIdentification";

    public RchrApprovalHistrory() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_LASTAPPROVED, "-->");
        setDefaultValue(PROPERTY_APPROVE, false);
        setDefaultValue(PROPERTY_UPDATECOUNT, (long) 0);
        setDefaultValue(PROPERTY_BESTOFF, false);
        setDefaultValue(PROPERTY_STAGECOUNT, (long) 0);
        setDefaultValue(PROPERTY_REJECT, false);
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

    public Tab getTab() {
        return (Tab) get(PROPERTY_TAB);
    }

    public void setTab(Tab tab) {
        set(PROPERTY_TAB, tab);
    }

    public String getRecordid() {
        return (String) get(PROPERTY_RECORDID);
    }

    public void setRecordid(String recordid) {
        set(PROPERTY_RECORDID, recordid);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
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

    public String getDecision() {
        return (String) get(PROPERTY_DECISION);
    }

    public void setDecision(String decision) {
        set(PROPERTY_DECISION, decision);
    }

    public String getReason() {
        return (String) get(PROPERTY_REASON);
    }

    public void setReason(String reason) {
        set(PROPERTY_REASON, reason);
    }

    public String getAprpoveby() {
        return (String) get(PROPERTY_APRPOVEBY);
    }

    public void setAprpoveby(String aprpoveby) {
        set(PROPERTY_APRPOVEBY, aprpoveby);
    }

    public Date getApprovedtime() {
        return (Date) get(PROPERTY_APPROVEDTIME);
    }

    public void setApprovedtime(Date approvedtime) {
        set(PROPERTY_APPROVEDTIME, approvedtime);
    }

    public String getLastapproved() {
        return (String) get(PROPERTY_LASTAPPROVED);
    }

    public void setLastapproved(String lastapproved) {
        set(PROPERTY_LASTAPPROVED, lastapproved);
    }

    public Date getRequestdate() {
        return (Date) get(PROPERTY_REQUESTDATE);
    }

    public void setRequestdate(Date requestdate) {
        set(PROPERTY_REQUESTDATE, requestdate);
    }

    public Boolean isApprove() {
        return (Boolean) get(PROPERTY_APPROVE);
    }

    public void setApprove(Boolean approve) {
        set(PROPERTY_APPROVE, approve);
    }

    public Long getUpdatecount() {
        return (Long) get(PROPERTY_UPDATECOUNT);
    }

    public void setUpdatecount(Long updatecount) {
        set(PROPERTY_UPDATECOUNT, updatecount);
    }

    public RchrApprovalTemplate getRchrApprovaltemplate() {
        return (RchrApprovalTemplate) get(PROPERTY_RCHRAPPROVALTEMPLATE);
    }

    public void setRchrApprovaltemplate(RchrApprovalTemplate rchrApprovaltemplate) {
        set(PROPERTY_RCHRAPPROVALTEMPLATE, rchrApprovaltemplate);
    }

    public User getApproveuser() {
        return (User) get(PROPERTY_APPROVEUSER);
    }

    public void setApproveuser(User approveuser) {
        set(PROPERTY_APPROVEUSER, approveuser);
    }

    public RchrApprovalStage getRchrApprovalstage() {
        return (RchrApprovalStage) get(PROPERTY_RCHRAPPROVALSTAGE);
    }

    public void setRchrApprovalstage(RchrApprovalStage rchrApprovalstage) {
        set(PROPERTY_RCHRAPPROVALSTAGE, rchrApprovalstage);
    }

    public User getApprovebyuser() {
        return (User) get(PROPERTY_APPROVEBYUSER);
    }

    public void setApprovebyuser(User approvebyuser) {
        set(PROPERTY_APPROVEBYUSER, approvebyuser);
    }

    public Boolean isBestoff() {
        return (Boolean) get(PROPERTY_BESTOFF);
    }

    public void setBestoff(Boolean bestoff) {
        set(PROPERTY_BESTOFF, bestoff);
    }

    public Long getStagecount() {
        return (Long) get(PROPERTY_STAGECOUNT);
    }

    public void setStagecount(Long stagecount) {
        set(PROPERTY_STAGECOUNT, stagecount);
    }

    public Boolean isReject() {
        return (Boolean) get(PROPERTY_REJECT);
    }

    public void setReject(Boolean reject) {
        set(PROPERTY_REJECT, reject);
    }

    public String getRecordIdentification() {
        return (String) get(PROPERTY_RECORDIDENTIFICATION);
    }

    public void setRecordIdentification(String recordIdentification) {
        set(PROPERTY_RECORDIDENTIFICATION, recordIdentification);
    }

}
