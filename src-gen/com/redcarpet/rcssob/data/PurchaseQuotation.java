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
package com.redcarpet.rcssob.data;

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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.pricing.pricelist.PriceList;
/**
 * Entity class for entity RCOB_Purchasequotation (stored in table RCOB_Purchasequotation).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PurchaseQuotation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCOB_Purchasequotation";
    public static final String ENTITY_NAME = "RCOB_Purchasequotation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_SCHEDULEDDELIVERYDATE = "scheduleddeliverydate";
    public static final String PROPERTY_OREDERDATE = "orederdate";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_LINESFLAG = "linesflag";
    public static final String PROPERTY_QUOTSTATUS = "quotstatus";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_LINEPROCESS = "lineprocess";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_RCOBPRREQUISITION = "rcobPrrequisition";
    public static final String PROPERTY_ORDEREMRCOBPURCHASEQUOTATIONLIST = "orderEMRcobPurchasequotationList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST = "rCOBPurchasequotationlinesList";
    public static final String PROPERTY_RCOBQUOTATIONTAXLIST = "rCOBQuotationtaxList";

    public PurchaseQuotation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_SALESTRANSACTION, true);
        setDefaultValue(PROPERTY_LINESFLAG, false);
        setDefaultValue(PROPERTY_ALERTSTATUS, false);
        setDefaultValue(PROPERTY_LINEPROCESS, false);
        setDefaultValue(PROPERTY_ORDEREMRCOBPURCHASEQUOTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBQUOTATIONTAXLIST, new ArrayList<Object>());
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

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public Date getScheduleddeliverydate() {
        return (Date) get(PROPERTY_SCHEDULEDDELIVERYDATE);
    }

    public void setScheduleddeliverydate(Date scheduleddeliverydate) {
        set(PROPERTY_SCHEDULEDDELIVERYDATE, scheduleddeliverydate);
    }

    public Date getOrederdate() {
        return (Date) get(PROPERTY_OREDERDATE);
    }

    public void setOrederdate(Date orederdate) {
        set(PROPERTY_OREDERDATE, orederdate);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public Boolean isLinesflag() {
        return (Boolean) get(PROPERTY_LINESFLAG);
    }

    public void setLinesflag(Boolean linesflag) {
        set(PROPERTY_LINESFLAG, linesflag);
    }

    public String getQuotstatus() {
        return (String) get(PROPERTY_QUOTSTATUS);
    }

    public void setQuotstatus(String quotstatus) {
        set(PROPERTY_QUOTSTATUS, quotstatus);
    }

    public Boolean isAlertStatus() {
        return (Boolean) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(Boolean alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isLineprocess() {
        return (Boolean) get(PROPERTY_LINEPROCESS);
    }

    public void setLineprocess(Boolean lineprocess) {
        set(PROPERTY_LINEPROCESS, lineprocess);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public Prrequisition getRcobPrrequisition() {
        return (Prrequisition) get(PROPERTY_RCOBPRREQUISITION);
    }

    public void setRcobPrrequisition(Prrequisition rcobPrrequisition) {
        set(PROPERTY_RCOBPRREQUISITION, rcobPrrequisition);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderEMRcobPurchasequotationList() {
        return (List<Order>) get(PROPERTY_ORDEREMRCOBPURCHASEQUOTATIONLIST);
    }

    public void setOrderEMRcobPurchasequotationList(List<Order> orderEMRcobPurchasequotationList) {
        set(PROPERTY_ORDEREMRCOBPURCHASEQUOTATIONLIST, orderEMRcobPurchasequotationList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseQuotationLines> getRCOBPurchasequotationlinesList() {
        return (List<PurchaseQuotationLines>) get(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST);
    }

    public void setRCOBPurchasequotationlinesList(List<PurchaseQuotationLines> rCOBPurchasequotationlinesList) {
        set(PROPERTY_RCOBPURCHASEQUOTATIONLINESLIST, rCOBPurchasequotationlinesList);
    }

    @SuppressWarnings("unchecked")
    public List<Quotationtax> getRCOBQuotationtaxList() {
        return (List<Quotationtax>) get(PROPERTY_RCOBQUOTATIONTAXLIST);
    }

    public void setRCOBQuotationtaxList(List<Quotationtax> rCOBQuotationtaxList) {
        set(PROPERTY_RCOBQUOTATIONTAXLIST, rCOBQuotationtaxList);
    }

}
