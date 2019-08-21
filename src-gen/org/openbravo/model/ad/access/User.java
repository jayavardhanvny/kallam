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
package org.openbravo.model.ad.access;

import com.rcss.humanresource.data.RCHRApprovalTemplateSpUser;
import com.rcss.humanresource.data.RCHRApprovalTemplateUser;
import com.rcss.humanresource.data.RchrApprovalHistrory;
import com.rcss.humanresource.data.RchrApprovalStageUser;
import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.epcg.data.EPCGDocuser;
import com.redcarpet.epcg.data.EPCGWarehouseuser;
import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.epcg_ppcenquiry;
import com.redcarpet.rcssob.data.Prrequisition;
import com.redcarpet.rcssob.data.PurchaseQuotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.client.application.UIPersonalization;
import org.openbravo.client.myob.WidgetInstance;
import org.openbravo.model.ad.alert.Alert;
import org.openbravo.model.ad.alert.AlertRecipient;
import org.openbravo.model.ad.domain.Preference;
import org.openbravo.model.ad.process.ProcessInstance;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.Language;
import org.openbravo.model.ad.ui.ProcessExecution;
import org.openbravo.model.ad.ui.ProcessRequest;
import org.openbravo.model.ad.utility.AuditTrail;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.businesspartner.BankAccount;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Greeting;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.interaction.ContactEmailInteraction;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLineV2;
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.manufacturing.quality.MeasureShift;
import org.openbravo.model.materialmgmt.onhandquantity.Reservation;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.pos.ExternalPOS;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.project.ActiveProposal;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectProposal;
import org.openbravo.model.project.ProjectProposalTask;
import org.openbravo.model.sales.SalesRegion;
import org.openbravo.model.timeandexpense.Resource;
import org.openbravo.service.integration.openid.OBSOIDUserIdentifier;
/**
 * Entity class for entity ADUser (stored in table AD_User).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class User extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_User";
    public static final String ENTITY_NAME = "ADUser";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_PASSWORD = "password";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_SUPERVISOR = "supervisor";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_EMAILSERVERUSERNAME = "emailServerUsername";
    public static final String PROPERTY_EMAILSERVERPASSWORD = "emailServerPassword";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_GREETING = "greeting";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_PHONE = "phone";
    public static final String PROPERTY_ALTERNATIVEPHONE = "alternativePhone";
    public static final String PROPERTY_FAX = "fax";
    public static final String PROPERTY_LASTCONTACTDATE = "lastContactDate";
    public static final String PROPERTY_LASTCONTACTRESULT = "lastContactResult";
    public static final String PROPERTY_BIRTHDAY = "birthday";
    public static final String PROPERTY_TRXORGANIZATION = "trxOrganization";
    public static final String PROPERTY_FIRSTNAME = "firstName";
    public static final String PROPERTY_LASTNAME = "lastName";
    public static final String PROPERTY_USERNAME = "username";
    public static final String PROPERTY_DEFAULTCLIENT = "defaultClient";
    public static final String PROPERTY_DEFAULTLANGUAGE = "defaultLanguage";
    public static final String PROPERTY_DEFAULTORGANIZATION = "defaultOrganization";
    public static final String PROPERTY_DEFAULTROLE = "defaultRole";
    public static final String PROPERTY_DEFAULTWAREHOUSE = "defaultWarehouse";
    public static final String PROPERTY_LOCKED = "locked";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_GRANTPORTALACCESS = "grantPortalAccess";
    public static final String PROPERTY_EPCGISEMAIL = "epcgIsemail";
    public static final String PROPERTY_ADALERTLIST = "aDAlertList";
    public static final String PROPERTY_ADALERTRECIPIENTLIST = "aDAlertRecipientList";
    public static final String PROPERTY_ADPREFERENCELIST = "aDPreferenceList";
    public static final String PROPERTY_ADPROCESSINSTANCELIST = "aDProcessInstanceList";
    public static final String PROPERTY_ADUSERSUPERVISORLIST = "aDUserSupervisorList";
    public static final String PROPERTY_ADUSERROLESLIST = "aDUserRolesList";
    public static final String PROPERTY_AUDITTRAILLIST = "auditTrailList";
    public static final String PROPERTY_ACTIVEPROPOSALVLIST = "activeProposalVList";
    public static final String PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST = "businessPartnerBankAccountList";
    public static final String PROPERTY_CONTACTEMAILINTERACTIONLIST = "contactEmailInteractionList";
    public static final String PROPERTY_EPCGDOCUSERLIST = "ePCGDocuserList";
    public static final String PROPERTY_EPCGWAREHOUSEUSERLIST = "ePCGWarehouseuserList";
    public static final String PROPERTY_EPCGCOSTENQUIRYUSERIDLIST = "epcgCostenquiryUseridList";
    public static final String PROPERTY_EXTERNALPOSSALESREPRESENTATIVELIST = "externalPOSSalesRepresentativeList";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_INVOICESALESREPRESENTATIVELIST = "invoiceSalesRepresentativeList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICELINEV2SALESREPRESENTATIVELIST = "invoiceLineV2SalesRepresentativeList";
    public static final String PROPERTY_INVOICEV2SALESREPRESENTATIVELIST = "invoiceV2SalesRepresentativeList";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_MANUFACTURINGMEASURESHIFTLIST = "manufacturingMeasureShiftList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONLIST = "materialMgmtReservationList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTSALESREPRESENTATIVELIST = "materialMgmtShipmentInOutSalesRepresentativeList";
    public static final String PROPERTY_OBKMOWIDGETINSTANCEVISIBLEATUSERLIST = "oBKMOWidgetInstanceVisibleAtUserList";
    public static final String PROPERTY_OBSOIDUSERIDENTIFIERLIST = "oBSOIDUserIdentifierList";
    public static final String PROPERTY_OBUIAPPUIPERSONALIZATIONLIST = "oBUIAPPUIPersonalizationList";
    public static final String PROPERTY_ORDERSALESREPRESENTATIVELIST = "orderSalesRepresentativeList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERDROPSHIPCONTACTLIST = "orderDropShipContactList";
    public static final String PROPERTY_ORGANIZATIONINFORMATIONLIST = "organizationInformationList";
    public static final String PROPERTY_PROCESSEXECUTIONLIST = "processExecutionList";
    public static final String PROPERTY_PROCESSREQUESTLIST = "processRequestList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLIST = "procurementRequisitionList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLINELOCKEDBYLIST = "procurementRequisitionLineLockedByList";
    public static final String PROPERTY_PRODUCTSALESREPRESENTATIVELIST = "productSalesRepresentativeList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_PROJECTSALESREPRESENTATIVELIST = "projectSalesRepresentativeList";
    public static final String PROPERTY_PROJECTPROJECTPROPOSALTASKLIST = "projectProjectProposalTaskList";
    public static final String PROPERTY_PROJECTPROPOSALLIST = "projectProposalList";
    public static final String PROPERTY_RCHRAPPROVALHISTRORYLIST = "rCHRApprovalHistroryList";
    public static final String PROPERTY_RCHRAPPROVALHISTRORYAPPROVEUSERLIST = "rCHRApprovalHistroryApproveuserList";
    public static final String PROPERTY_RCHRAPPROVALHISTRORYAPPROVEBYUSERLIST = "rCHRApprovalHistroryApprovebyuserList";
    public static final String PROPERTY_RCHRAPPROVALSTAGEUSERLIST = "rCHRApprovalStageUserList";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATESPUSERLIST = "rCHRApprovalTemplateSpUserList";
    public static final String PROPERTY_RCHRAPPROVALTEMPLATEUSERLIST = "rCHRApprovalTemplateUserList";
    public static final String PROPERTY_RCOBPRREQUISITIONLIST = "rCOBPrrequisitionList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLIST = "rCOBPurchasequotationList";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";
    public static final String PROPERTY_RESOURCELIST = "resourceList";
    public static final String PROPERTY_SALESREGIONSALESREPRESENTATIVELIST = "salesRegionSalesRepresentativeList";
    public static final String PROPERTY_EPCGPPCENQUIRYUSERIDLIST = "epcgPpcenquiryUseridList";

    public User() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_LOCKED, false);
        setDefaultValue(PROPERTY_GRANTPORTALACCESS, false);
        setDefaultValue(PROPERTY_EPCGISEMAIL, false);
        setDefaultValue(PROPERTY_ADALERTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADALERTRECIPIENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPREFERENCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSINSTANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADUSERSUPERVISORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADUSERROLESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AUDITTRAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ACTIVEPROPOSALVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CONTACTEMAILINTERACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGDOCUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWAREHOUSEUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYUSERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSSALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICESALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2SALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2SALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMEASURESHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTSALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBKMOWIDGETINSTANCEVISIBLEATUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBSOIDUSERIDENTIFIERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERSALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERDROPSHIPCONTACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCESSEXECUTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCESSREQUESTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLINELOCKEDBYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTSALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTSALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPROJECTPROPOSALTASKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALHISTRORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALHISTRORYAPPROVEUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALHISTRORYAPPROVEBYUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALSTAGEUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALTEMPLATESPUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALTEMPLATEUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPRREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPURCHASEQUOTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RESOURCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESREGIONSALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPPCENQUIRYUSERIDLIST, new ArrayList<Object>());
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getPassword() {
        return (String) get(PROPERTY_PASSWORD);
    }

    public void setPassword(String password) {
        set(PROPERTY_PASSWORD, password);
    }

    public String getEmail() {
        return (String) get(PROPERTY_EMAIL);
    }

    public void setEmail(String email) {
        set(PROPERTY_EMAIL, email);
    }

    public User getSupervisor() {
        return (User) get(PROPERTY_SUPERVISOR);
    }

    public void setSupervisor(User supervisor) {
        set(PROPERTY_SUPERVISOR, supervisor);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public String getEmailServerUsername() {
        return (String) get(PROPERTY_EMAILSERVERUSERNAME);
    }

    public void setEmailServerUsername(String emailServerUsername) {
        set(PROPERTY_EMAILSERVERUSERNAME, emailServerUsername);
    }

    public String getEmailServerPassword() {
        return (String) get(PROPERTY_EMAILSERVERPASSWORD);
    }

    public void setEmailServerPassword(String emailServerPassword) {
        set(PROPERTY_EMAILSERVERPASSWORD, emailServerPassword);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public Greeting getGreeting() {
        return (Greeting) get(PROPERTY_GREETING);
    }

    public void setGreeting(Greeting greeting) {
        set(PROPERTY_GREETING, greeting);
    }

    public String getPosition() {
        return (String) get(PROPERTY_POSITION);
    }

    public void setPosition(String position) {
        set(PROPERTY_POSITION, position);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public String getPhone() {
        return (String) get(PROPERTY_PHONE);
    }

    public void setPhone(String phone) {
        set(PROPERTY_PHONE, phone);
    }

    public String getAlternativePhone() {
        return (String) get(PROPERTY_ALTERNATIVEPHONE);
    }

    public void setAlternativePhone(String alternativePhone) {
        set(PROPERTY_ALTERNATIVEPHONE, alternativePhone);
    }

    public String getFax() {
        return (String) get(PROPERTY_FAX);
    }

    public void setFax(String fax) {
        set(PROPERTY_FAX, fax);
    }

    public Date getLastContactDate() {
        return (Date) get(PROPERTY_LASTCONTACTDATE);
    }

    public void setLastContactDate(Date lastContactDate) {
        set(PROPERTY_LASTCONTACTDATE, lastContactDate);
    }

    public String getLastContactResult() {
        return (String) get(PROPERTY_LASTCONTACTRESULT);
    }

    public void setLastContactResult(String lastContactResult) {
        set(PROPERTY_LASTCONTACTRESULT, lastContactResult);
    }

    public Date getBirthday() {
        return (Date) get(PROPERTY_BIRTHDAY);
    }

    public void setBirthday(Date birthday) {
        set(PROPERTY_BIRTHDAY, birthday);
    }

    public Organization getTrxOrganization() {
        return (Organization) get(PROPERTY_TRXORGANIZATION);
    }

    public void setTrxOrganization(Organization trxOrganization) {
        set(PROPERTY_TRXORGANIZATION, trxOrganization);
    }

    public String getFirstName() {
        return (String) get(PROPERTY_FIRSTNAME);
    }

    public void setFirstName(String firstName) {
        set(PROPERTY_FIRSTNAME, firstName);
    }

    public String getLastName() {
        return (String) get(PROPERTY_LASTNAME);
    }

    public void setLastName(String lastName) {
        set(PROPERTY_LASTNAME, lastName);
    }

    public String getUsername() {
        return (String) get(PROPERTY_USERNAME);
    }

    public void setUsername(String username) {
        set(PROPERTY_USERNAME, username);
    }

    public Client getDefaultClient() {
        return (Client) get(PROPERTY_DEFAULTCLIENT);
    }

    public void setDefaultClient(Client defaultClient) {
        set(PROPERTY_DEFAULTCLIENT, defaultClient);
    }

    public Language getDefaultLanguage() {
        return (Language) get(PROPERTY_DEFAULTLANGUAGE);
    }

    public void setDefaultLanguage(Language defaultLanguage) {
        set(PROPERTY_DEFAULTLANGUAGE, defaultLanguage);
    }

    public Organization getDefaultOrganization() {
        return (Organization) get(PROPERTY_DEFAULTORGANIZATION);
    }

    public void setDefaultOrganization(Organization defaultOrganization) {
        set(PROPERTY_DEFAULTORGANIZATION, defaultOrganization);
    }

    public Role getDefaultRole() {
        return (Role) get(PROPERTY_DEFAULTROLE);
    }

    public void setDefaultRole(Role defaultRole) {
        set(PROPERTY_DEFAULTROLE, defaultRole);
    }

    public Warehouse getDefaultWarehouse() {
        return (Warehouse) get(PROPERTY_DEFAULTWAREHOUSE);
    }

    public void setDefaultWarehouse(Warehouse defaultWarehouse) {
        set(PROPERTY_DEFAULTWAREHOUSE, defaultWarehouse);
    }

    public Boolean isLocked() {
        return (Boolean) get(PROPERTY_LOCKED);
    }

    public void setLocked(Boolean locked) {
        set(PROPERTY_LOCKED, locked);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public Boolean isGrantPortalAccess() {
        return (Boolean) get(PROPERTY_GRANTPORTALACCESS);
    }

    public void setGrantPortalAccess(Boolean grantPortalAccess) {
        set(PROPERTY_GRANTPORTALACCESS, grantPortalAccess);
    }

    public Boolean isEpcgIsemail() {
        return (Boolean) get(PROPERTY_EPCGISEMAIL);
    }

    public void setEpcgIsemail(Boolean epcgIsemail) {
        set(PROPERTY_EPCGISEMAIL, epcgIsemail);
    }

    @SuppressWarnings("unchecked")
    public List<Alert> getADAlertList() {
        return (List<Alert>) get(PROPERTY_ADALERTLIST);
    }

    public void setADAlertList(List<Alert> aDAlertList) {
        set(PROPERTY_ADALERTLIST, aDAlertList);
    }

    @SuppressWarnings("unchecked")
    public List<AlertRecipient> getADAlertRecipientList() {
        return (List<AlertRecipient>) get(PROPERTY_ADALERTRECIPIENTLIST);
    }

    public void setADAlertRecipientList(List<AlertRecipient> aDAlertRecipientList) {
        set(PROPERTY_ADALERTRECIPIENTLIST, aDAlertRecipientList);
    }

    @SuppressWarnings("unchecked")
    public List<Preference> getADPreferenceList() {
        return (List<Preference>) get(PROPERTY_ADPREFERENCELIST);
    }

    public void setADPreferenceList(List<Preference> aDPreferenceList) {
        set(PROPERTY_ADPREFERENCELIST, aDPreferenceList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessInstance> getADProcessInstanceList() {
        return (List<ProcessInstance>) get(PROPERTY_ADPROCESSINSTANCELIST);
    }

    public void setADProcessInstanceList(List<ProcessInstance> aDProcessInstanceList) {
        set(PROPERTY_ADPROCESSINSTANCELIST, aDProcessInstanceList);
    }

    @SuppressWarnings("unchecked")
    public List<User> getADUserSupervisorList() {
        return (List<User>) get(PROPERTY_ADUSERSUPERVISORLIST);
    }

    public void setADUserSupervisorList(List<User> aDUserSupervisorList) {
        set(PROPERTY_ADUSERSUPERVISORLIST, aDUserSupervisorList);
    }

    @SuppressWarnings("unchecked")
    public List<UserRoles> getADUserRolesList() {
        return (List<UserRoles>) get(PROPERTY_ADUSERROLESLIST);
    }

    public void setADUserRolesList(List<UserRoles> aDUserRolesList) {
        set(PROPERTY_ADUSERROLESLIST, aDUserRolesList);
    }

    @SuppressWarnings("unchecked")
    public List<AuditTrail> getAuditTrailList() {
        return (List<AuditTrail>) get(PROPERTY_AUDITTRAILLIST);
    }

    public void setAuditTrailList(List<AuditTrail> auditTrailList) {
        set(PROPERTY_AUDITTRAILLIST, auditTrailList);
    }

    @SuppressWarnings("unchecked")
    public List<ActiveProposal> getActiveProposalVList() {
        return (List<ActiveProposal>) get(PROPERTY_ACTIVEPROPOSALVLIST);
    }

    public void setActiveProposalVList(List<ActiveProposal> activeProposalVList) {
        set(PROPERTY_ACTIVEPROPOSALVLIST, activeProposalVList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccount> getBusinessPartnerBankAccountList() {
        return (List<BankAccount>) get(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST);
    }

    public void setBusinessPartnerBankAccountList(List<BankAccount> businessPartnerBankAccountList) {
        set(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, businessPartnerBankAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<ContactEmailInteraction> getContactEmailInteractionList() {
        return (List<ContactEmailInteraction>) get(PROPERTY_CONTACTEMAILINTERACTIONLIST);
    }

    public void setContactEmailInteractionList(List<ContactEmailInteraction> contactEmailInteractionList) {
        set(PROPERTY_CONTACTEMAILINTERACTIONLIST, contactEmailInteractionList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGDocuser> getEPCGDocuserList() {
        return (List<EPCGDocuser>) get(PROPERTY_EPCGDOCUSERLIST);
    }

    public void setEPCGDocuserList(List<EPCGDocuser> ePCGDocuserList) {
        set(PROPERTY_EPCGDOCUSERLIST, ePCGDocuserList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGWarehouseuser> getEPCGWarehouseuserList() {
        return (List<EPCGWarehouseuser>) get(PROPERTY_EPCGWAREHOUSEUSERLIST);
    }

    public void setEPCGWarehouseuserList(List<EPCGWarehouseuser> ePCGWarehouseuserList) {
        set(PROPERTY_EPCGWAREHOUSEUSERLIST, ePCGWarehouseuserList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryUseridList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYUSERIDLIST);
    }

    public void setEpcgCostenquiryUseridList(List<EpcgCostenquiry> epcgCostenquiryUseridList) {
        set(PROPERTY_EPCGCOSTENQUIRYUSERIDLIST, epcgCostenquiryUseridList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOS> getExternalPOSSalesRepresentativeList() {
        return (List<ExternalPOS>) get(PROPERTY_EXTERNALPOSSALESREPRESENTATIVELIST);
    }

    public void setExternalPOSSalesRepresentativeList(List<ExternalPOS> externalPOSSalesRepresentativeList) {
        set(PROPERTY_EXTERNALPOSSALESREPRESENTATIVELIST, externalPOSSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
        return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceSalesRepresentativeList() {
        return (List<Invoice>) get(PROPERTY_INVOICESALESREPRESENTATIVELIST);
    }

    public void setInvoiceSalesRepresentativeList(List<Invoice> invoiceSalesRepresentativeList) {
        set(PROPERTY_INVOICESALESREPRESENTATIVELIST, invoiceSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceList() {
        return (List<Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineV2> getInvoiceLineV2SalesRepresentativeList() {
        return (List<InvoiceLineV2>) get(PROPERTY_INVOICELINEV2SALESREPRESENTATIVELIST);
    }

    public void setInvoiceLineV2SalesRepresentativeList(List<InvoiceLineV2> invoiceLineV2SalesRepresentativeList) {
        set(PROPERTY_INVOICELINEV2SALESREPRESENTATIVELIST, invoiceLineV2SalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2SalesRepresentativeList() {
        return (List<InvoiceV2>) get(PROPERTY_INVOICEV2SALESREPRESENTATIVELIST);
    }

    public void setInvoiceV2SalesRepresentativeList(List<InvoiceV2> invoiceV2SalesRepresentativeList) {
        set(PROPERTY_INVOICEV2SALESREPRESENTATIVELIST, invoiceV2SalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2List() {
        return (List<InvoiceV2>) get(PROPERTY_INVOICEV2LIST);
    }

    public void setInvoiceV2List(List<InvoiceV2> invoiceV2List) {
        set(PROPERTY_INVOICEV2LIST, invoiceV2List);
    }

    @SuppressWarnings("unchecked")
    public List<MeasureShift> getManufacturingMeasureShiftList() {
        return (List<MeasureShift>) get(PROPERTY_MANUFACTURINGMEASURESHIFTLIST);
    }

    public void setManufacturingMeasureShiftList(List<MeasureShift> manufacturingMeasureShiftList) {
        set(PROPERTY_MANUFACTURINGMEASURESHIFTLIST, manufacturingMeasureShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getMaterialMgmtReservationList() {
        return (List<Reservation>) get(PROPERTY_MATERIALMGMTRESERVATIONLIST);
    }

    public void setMaterialMgmtReservationList(List<Reservation> materialMgmtReservationList) {
        set(PROPERTY_MATERIALMGMTRESERVATIONLIST, materialMgmtReservationList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
        return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutSalesRepresentativeList() {
        return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTSALESREPRESENTATIVELIST);
    }

    public void setMaterialMgmtShipmentInOutSalesRepresentativeList(List<ShipmentInOut> materialMgmtShipmentInOutSalesRepresentativeList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTSALESREPRESENTATIVELIST, materialMgmtShipmentInOutSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<WidgetInstance> getOBKMOWidgetInstanceVisibleAtUserList() {
        return (List<WidgetInstance>) get(PROPERTY_OBKMOWIDGETINSTANCEVISIBLEATUSERLIST);
    }

    public void setOBKMOWidgetInstanceVisibleAtUserList(List<WidgetInstance> oBKMOWidgetInstanceVisibleAtUserList) {
        set(PROPERTY_OBKMOWIDGETINSTANCEVISIBLEATUSERLIST, oBKMOWidgetInstanceVisibleAtUserList);
    }

    @SuppressWarnings("unchecked")
    public List<OBSOIDUserIdentifier> getOBSOIDUserIdentifierList() {
        return (List<OBSOIDUserIdentifier>) get(PROPERTY_OBSOIDUSERIDENTIFIERLIST);
    }

    public void setOBSOIDUserIdentifierList(List<OBSOIDUserIdentifier> oBSOIDUserIdentifierList) {
        set(PROPERTY_OBSOIDUSERIDENTIFIERLIST, oBSOIDUserIdentifierList);
    }

    @SuppressWarnings("unchecked")
    public List<UIPersonalization> getOBUIAPPUIPersonalizationList() {
        return (List<UIPersonalization>) get(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST);
    }

    public void setOBUIAPPUIPersonalizationList(List<UIPersonalization> oBUIAPPUIPersonalizationList) {
        set(PROPERTY_OBUIAPPUIPERSONALIZATIONLIST, oBUIAPPUIPersonalizationList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderSalesRepresentativeList() {
        return (List<Order>) get(PROPERTY_ORDERSALESREPRESENTATIVELIST);
    }

    public void setOrderSalesRepresentativeList(List<Order> orderSalesRepresentativeList) {
        set(PROPERTY_ORDERSALESREPRESENTATIVELIST, orderSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderList() {
        return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderDropShipContactList() {
        return (List<Order>) get(PROPERTY_ORDERDROPSHIPCONTACTLIST);
    }

    public void setOrderDropShipContactList(List<Order> orderDropShipContactList) {
        set(PROPERTY_ORDERDROPSHIPCONTACTLIST, orderDropShipContactList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationInformation> getOrganizationInformationList() {
        return (List<OrganizationInformation>) get(PROPERTY_ORGANIZATIONINFORMATIONLIST);
    }

    public void setOrganizationInformationList(List<OrganizationInformation> organizationInformationList) {
        set(PROPERTY_ORGANIZATIONINFORMATIONLIST, organizationInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessExecution> getProcessExecutionList() {
        return (List<ProcessExecution>) get(PROPERTY_PROCESSEXECUTIONLIST);
    }

    public void setProcessExecutionList(List<ProcessExecution> processExecutionList) {
        set(PROPERTY_PROCESSEXECUTIONLIST, processExecutionList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessRequest> getProcessRequestList() {
        return (List<ProcessRequest>) get(PROPERTY_PROCESSREQUESTLIST);
    }

    public void setProcessRequestList(List<ProcessRequest> processRequestList) {
        set(PROPERTY_PROCESSREQUESTLIST, processRequestList);
    }

    @SuppressWarnings("unchecked")
    public List<Requisition> getProcurementRequisitionList() {
        return (List<Requisition>) get(PROPERTY_PROCUREMENTREQUISITIONLIST);
    }

    public void setProcurementRequisitionList(List<Requisition> procurementRequisitionList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLIST, procurementRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitionLine> getProcurementRequisitionLineLockedByList() {
        return (List<RequisitionLine>) get(PROPERTY_PROCUREMENTREQUISITIONLINELOCKEDBYLIST);
    }

    public void setProcurementRequisitionLineLockedByList(List<RequisitionLine> procurementRequisitionLineLockedByList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLINELOCKEDBYLIST, procurementRequisitionLineLockedByList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProductSalesRepresentativeList() {
        return (List<Product>) get(PROPERTY_PRODUCTSALESREPRESENTATIVELIST);
    }

    public void setProductSalesRepresentativeList(List<Product> productSalesRepresentativeList) {
        set(PROPERTY_PRODUCTSALESREPRESENTATIVELIST, productSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
        return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectSalesRepresentativeList() {
        return (List<Project>) get(PROPERTY_PROJECTSALESREPRESENTATIVELIST);
    }

    public void setProjectSalesRepresentativeList(List<Project> projectSalesRepresentativeList) {
        set(PROPERTY_PROJECTSALESREPRESENTATIVELIST, projectSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposalTask> getProjectProjectProposalTaskList() {
        return (List<ProjectProposalTask>) get(PROPERTY_PROJECTPROJECTPROPOSALTASKLIST);
    }

    public void setProjectProjectProposalTaskList(List<ProjectProposalTask> projectProjectProposalTaskList) {
        set(PROPERTY_PROJECTPROJECTPROPOSALTASKLIST, projectProjectProposalTaskList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposal> getProjectProposalList() {
        return (List<ProjectProposal>) get(PROPERTY_PROJECTPROPOSALLIST);
    }

    public void setProjectProposalList(List<ProjectProposal> projectProposalList) {
        set(PROPERTY_PROJECTPROPOSALLIST, projectProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalHistrory> getRCHRApprovalHistroryList() {
        return (List<RchrApprovalHistrory>) get(PROPERTY_RCHRAPPROVALHISTRORYLIST);
    }

    public void setRCHRApprovalHistroryList(List<RchrApprovalHistrory> rCHRApprovalHistroryList) {
        set(PROPERTY_RCHRAPPROVALHISTRORYLIST, rCHRApprovalHistroryList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalHistrory> getRCHRApprovalHistroryApproveuserList() {
        return (List<RchrApprovalHistrory>) get(PROPERTY_RCHRAPPROVALHISTRORYAPPROVEUSERLIST);
    }

    public void setRCHRApprovalHistroryApproveuserList(List<RchrApprovalHistrory> rCHRApprovalHistroryApproveuserList) {
        set(PROPERTY_RCHRAPPROVALHISTRORYAPPROVEUSERLIST, rCHRApprovalHistroryApproveuserList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalHistrory> getRCHRApprovalHistroryApprovebyuserList() {
        return (List<RchrApprovalHistrory>) get(PROPERTY_RCHRAPPROVALHISTRORYAPPROVEBYUSERLIST);
    }

    public void setRCHRApprovalHistroryApprovebyuserList(List<RchrApprovalHistrory> rCHRApprovalHistroryApprovebyuserList) {
        set(PROPERTY_RCHRAPPROVALHISTRORYAPPROVEBYUSERLIST, rCHRApprovalHistroryApprovebyuserList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalStageUser> getRCHRApprovalStageUserList() {
        return (List<RchrApprovalStageUser>) get(PROPERTY_RCHRAPPROVALSTAGEUSERLIST);
    }

    public void setRCHRApprovalStageUserList(List<RchrApprovalStageUser> rCHRApprovalStageUserList) {
        set(PROPERTY_RCHRAPPROVALSTAGEUSERLIST, rCHRApprovalStageUserList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRApprovalTemplateSpUser> getRCHRApprovalTemplateSpUserList() {
        return (List<RCHRApprovalTemplateSpUser>) get(PROPERTY_RCHRAPPROVALTEMPLATESPUSERLIST);
    }

    public void setRCHRApprovalTemplateSpUserList(List<RCHRApprovalTemplateSpUser> rCHRApprovalTemplateSpUserList) {
        set(PROPERTY_RCHRAPPROVALTEMPLATESPUSERLIST, rCHRApprovalTemplateSpUserList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRApprovalTemplateUser> getRCHRApprovalTemplateUserList() {
        return (List<RCHRApprovalTemplateUser>) get(PROPERTY_RCHRAPPROVALTEMPLATEUSERLIST);
    }

    public void setRCHRApprovalTemplateUserList(List<RCHRApprovalTemplateUser> rCHRApprovalTemplateUserList) {
        set(PROPERTY_RCHRAPPROVALTEMPLATEUSERLIST, rCHRApprovalTemplateUserList);
    }

    @SuppressWarnings("unchecked")
    public List<Prrequisition> getRCOBPrrequisitionList() {
        return (List<Prrequisition>) get(PROPERTY_RCOBPRREQUISITIONLIST);
    }

    public void setRCOBPrrequisitionList(List<Prrequisition> rCOBPrrequisitionList) {
        set(PROPERTY_RCOBPRREQUISITIONLIST, rCOBPrrequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseQuotation> getRCOBPurchasequotationList() {
        return (List<PurchaseQuotation>) get(PROPERTY_RCOBPURCHASEQUOTATIONLIST);
    }

    public void setRCOBPurchasequotationList(List<PurchaseQuotation> rCOBPurchasequotationList) {
        set(PROPERTY_RCOBPURCHASEQUOTATIONLIST, rCOBPurchasequotationList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployee> getRchrEmployeeList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEELIST);
    }

    public void setRchrEmployeeList(List<RchrEmployee> rchrEmployeeList) {
        set(PROPERTY_RCHREMPLOYEELIST, rchrEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<Resource> getResourceList() {
        return (List<Resource>) get(PROPERTY_RESOURCELIST);
    }

    public void setResourceList(List<Resource> resourceList) {
        set(PROPERTY_RESOURCELIST, resourceList);
    }

    @SuppressWarnings("unchecked")
    public List<SalesRegion> getSalesRegionSalesRepresentativeList() {
        return (List<SalesRegion>) get(PROPERTY_SALESREGIONSALESREPRESENTATIVELIST);
    }

    public void setSalesRegionSalesRepresentativeList(List<SalesRegion> salesRegionSalesRepresentativeList) {
        set(PROPERTY_SALESREGIONSALESREPRESENTATIVELIST, salesRegionSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<epcg_ppcenquiry> getEpcgPpcenquiryUseridList() {
        return (List<epcg_ppcenquiry>) get(PROPERTY_EPCGPPCENQUIRYUSERIDLIST);
    }

    public void setEpcgPpcenquiryUseridList(List<epcg_ppcenquiry> epcgPpcenquiryUseridList) {
        set(PROPERTY_EPCGPPCENQUIRYUSERIDLIST, epcgPpcenquiryUseridList);
    }

}
