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
 * Entity class for entity RCHR_ApprovalTemplate (stored in table RCHR_ApprovalTemplate).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrApprovalTemplate extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_ApprovalTemplate";
    public static final String ENTITY_NAME = "RCHR_ApprovalTemplate";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_RCHRAPPROVALHISTRORYLIST = "rCHRApprovalHistroryList";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST = "rCHRApprovalTemplateDocList";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATESPUSERLIST = "rCHRApprovalTemplateSpUserList";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATESTAGELIST = "rCHRApprovalTemplateStageList";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATEUSERLIST = "rCHRApprovalTemplateUserList";

    public RchrApprovalTemplate() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCHRAPPROVALHISTRORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALTEMPLATESPUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALTEMPLATESTAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALTEMPLATEUSERLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getReasonForLeave() {
        return (String) get(PROPERTY_REASONFORLEAVE);
    }

    public void setReasonForLeave(String reasonForLeave) {
        set(PROPERTY_REASONFORLEAVE, reasonForLeave);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalHistrory> getRCHRApprovalHistroryList() {
        return (List<RchrApprovalHistrory>) get(PROPERTY_RCHRAPPROVALHISTRORYLIST);
    }

    public void setRCHRApprovalHistroryList(List<RchrApprovalHistrory> rCHRApprovalHistroryList) {
        set(PROPERTY_RCHRAPPROVALHISTRORYLIST, rCHRApprovalHistroryList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalTemplateDoc> getRCHRApprovalTemplateDocList() {
        return (List<RchrApprovalTemplateDoc>) get(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST);
    }

    public void setRCHRApprovalTemplateDocList(List<RchrApprovalTemplateDoc> rCHRApprovalTemplateDocList) {
        set(PROPERTY_RCHRAPPROVALTEMPLATEDOCLIST, rCHRApprovalTemplateDocList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRApprovalTemplateSpUser> getRCHRApprovalTemplateSpUserList() {
        return (List<RCHRApprovalTemplateSpUser>) get(PROPERTY_RCHRAPPROVALTEMPLATESPUSERLIST);
    }

    public void setRCHRApprovalTemplateSpUserList(List<RCHRApprovalTemplateSpUser> rCHRApprovalTemplateSpUserList) {
        set(PROPERTY_RCHRAPPROVALTEMPLATESPUSERLIST, rCHRApprovalTemplateSpUserList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalTemplateStage> getRCHRApprovalTemplateStageList() {
        return (List<RchrApprovalTemplateStage>) get(PROPERTY_RCHRAPPROVALTEMPLATESTAGELIST);
    }

    public void setRCHRApprovalTemplateStageList(List<RchrApprovalTemplateStage> rCHRApprovalTemplateStageList) {
        set(PROPERTY_RCHRAPPROVALTEMPLATESTAGELIST, rCHRApprovalTemplateStageList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRApprovalTemplateUser> getRCHRApprovalTemplateUserList() {
        return (List<RCHRApprovalTemplateUser>) get(PROPERTY_RCHRAPPROVALTEMPLATEUSERLIST);
    }

    public void setRCHRApprovalTemplateUserList(List<RCHRApprovalTemplateUser> rCHRApprovalTemplateUserList) {
        set(PROPERTY_RCHRAPPROVALTEMPLATEUSERLIST, rCHRApprovalTemplateUserList);
    }

}
