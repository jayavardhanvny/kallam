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
package org.openbravo.model.project;

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
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
/**
 * Entity class for entity ProjectProposal (stored in table C_ProjectProposal).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProjectProposal extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_ProjectProposal";
    public static final String ENTITY_NAME = "ProjectProposal";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_DATESENT = "dateSent";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_COPYFROM = "copyFrom";
    public static final String PROPERTY_HEADERNOTE = "headerNote";
    public static final String PROPERTY_FOOTER = "footer";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_FORMOFPAYMENT = "formOfPayment";
    public static final String PROPERTY_PROJECTBIDWON = "projectBidWon";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_PROJECTPROJECTPROPOSALTASKLIST = "projectProjectProposalTaskList";
    public static final String PROPERTY_PROJECTPROPOSALLINELIST = "projectProposalLineList";

    public ProjectProposal() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_COPYFROM, false);
        setDefaultValue(PROPERTY_SALESTRANSACTION, true);
        setDefaultValue(PROPERTY_PROJECTBIDWON, false);
        setDefaultValue(PROPERTY_PROJECTPROJECTPROPOSALTASKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPROPOSALLINELIST, new ArrayList<Object>());
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

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Date getDateSent() {
        return (Date) get(PROPERTY_DATESENT);
    }

    public void setDateSent(Date dateSent) {
        set(PROPERTY_DATESENT, dateSent);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public Boolean isCopyFrom() {
        return (Boolean) get(PROPERTY_COPYFROM);
    }

    public void setCopyFrom(Boolean copyFrom) {
        set(PROPERTY_COPYFROM, copyFrom);
    }

    public String getHeaderNote() {
        return (String) get(PROPERTY_HEADERNOTE);
    }

    public void setHeaderNote(String headerNote) {
        set(PROPERTY_HEADERNOTE, headerNote);
    }

    public String getFooter() {
        return (String) get(PROPERTY_FOOTER);
    }

    public void setFooter(String footer) {
        set(PROPERTY_FOOTER, footer);
    }

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
    }

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public String getFormOfPayment() {
        return (String) get(PROPERTY_FORMOFPAYMENT);
    }

    public void setFormOfPayment(String formOfPayment) {
        set(PROPERTY_FORMOFPAYMENT, formOfPayment);
    }

    public Boolean isProjectBidWon() {
        return (Boolean) get(PROPERTY_PROJECTBIDWON);
    }

    public void setProjectBidWon(Boolean projectBidWon) {
        set(PROPERTY_PROJECTBIDWON, projectBidWon);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposalTask> getProjectProjectProposalTaskList() {
        return (List<ProjectProposalTask>) get(PROPERTY_PROJECTPROJECTPROPOSALTASKLIST);
    }

    public void setProjectProjectProposalTaskList(List<ProjectProposalTask> projectProjectProposalTaskList) {
        set(PROPERTY_PROJECTPROJECTPROPOSALTASKLIST, projectProjectProposalTaskList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposalLine> getProjectProposalLineList() {
        return (List<ProjectProposalLine>) get(PROPERTY_PROJECTPROPOSALLINELIST);
    }

    public void setProjectProposalLineList(List<ProjectProposalLine> projectProposalLineList) {
        set(PROPERTY_PROJECTPROPOSALLINELIST, projectProposalLineList);
    }

}
