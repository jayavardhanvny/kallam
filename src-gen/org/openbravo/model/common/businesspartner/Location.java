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
package org.openbravo.model.common.businesspartner;

import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.EpcgProforma;
import com.redcarpet.goodsissue.data.RCGIDepartmentReceipt;
import com.redcarpet.goodsissue.data.RCGI_MaterialReceipt;
import com.redcarpet.rcssob.data.PurchaseQuotation;

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
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.project.ActiveProposal;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectProposal;
import org.openbravo.model.sales.SalesRegion;
/**
 * Entity class for entity BusinessPartnerLocation (stored in table C_BPartner_Location).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Location extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BPartner_Location";
    public static final String ENTITY_NAME = "BusinessPartnerLocation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_INVOICETOADDRESS = "invoiceToAddress";
    public static final String PROPERTY_SHIPTOADDRESS = "shipToAddress";
    public static final String PROPERTY_PAYFROMADDRESS = "payFromAddress";
    public static final String PROPERTY_REMITTOADDRESS = "remitToAddress";
    public static final String PROPERTY_PHONE = "phone";
    public static final String PROPERTY_ALTERNATIVEPHONE = "alternativePhone";
    public static final String PROPERTY_FAX = "fax";
    public static final String PROPERTY_SALESREGION = "salesRegion";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_TAXLOCATION = "taxLocation";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_EPCGGSTNO = "epcgGstno";
    public static final String PROPERTY_ADUSERLIST = "aDUserList";
    public static final String PROPERTY_ACTIVEPROPOSALVLIST = "activeProposalVList";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_EPCGPROFORMALIST = "epcgProformaList";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTDELIVERYLOCATIONLIST = "materialMgmtShipmentInOutDeliveryLocationList";
    public static final String PROPERTY_ORDERINVOICEADDRESSLIST = "orderInvoiceAddressList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERDROPSHIPLOCATIONLIST = "orderDropShipLocationList";
    public static final String PROPERTY_ORDERDELIVERYLOCATIONLIST = "orderDeliveryLocationList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_PROJECTINVOICEADDRESSLIST = "projectInvoiceAddressList";
    public static final String PROPERTY_PROJECTPROPOSALLIST = "projectProposalList";
    public static final String PROPERTY_RCGIDEPARTMENTRECEIPTLIST = "rCGIDepartmentReceiptList";
    public static final String PROPERTY_RCGIMATERIALRECEIPTLIST = "rCGIMaterialReceiptList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLIST = "rCOBPurchasequotationList";

    public Location() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_NAME, ".");
        setDefaultValue(PROPERTY_INVOICETOADDRESS, true);
        setDefaultValue(PROPERTY_SHIPTOADDRESS, true);
        setDefaultValue(PROPERTY_PAYFROMADDRESS, true);
        setDefaultValue(PROPERTY_REMITTOADDRESS, true);
        setDefaultValue(PROPERTY_TAXLOCATION, false);
        setDefaultValue(PROPERTY_ADUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ACTIVEPROPOSALVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPROFORMALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTDELIVERYLOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERINVOICEADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERDROPSHIPLOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERDELIVERYLOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTINVOICEADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDEPARTMENTRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPURCHASEQUOTATIONLIST, new ArrayList<Object>());
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

    public Boolean isInvoiceToAddress() {
        return (Boolean) get(PROPERTY_INVOICETOADDRESS);
    }

    public void setInvoiceToAddress(Boolean invoiceToAddress) {
        set(PROPERTY_INVOICETOADDRESS, invoiceToAddress);
    }

    public Boolean isShipToAddress() {
        return (Boolean) get(PROPERTY_SHIPTOADDRESS);
    }

    public void setShipToAddress(Boolean shipToAddress) {
        set(PROPERTY_SHIPTOADDRESS, shipToAddress);
    }

    public Boolean isPayFromAddress() {
        return (Boolean) get(PROPERTY_PAYFROMADDRESS);
    }

    public void setPayFromAddress(Boolean payFromAddress) {
        set(PROPERTY_PAYFROMADDRESS, payFromAddress);
    }

    public Boolean isRemitToAddress() {
        return (Boolean) get(PROPERTY_REMITTOADDRESS);
    }

    public void setRemitToAddress(Boolean remitToAddress) {
        set(PROPERTY_REMITTOADDRESS, remitToAddress);
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

    public SalesRegion getSalesRegion() {
        return (SalesRegion) get(PROPERTY_SALESREGION);
    }

    public void setSalesRegion(SalesRegion salesRegion) {
        set(PROPERTY_SALESREGION, salesRegion);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public org.openbravo.model.common.geography.Location getLocationAddress() {
        return (org.openbravo.model.common.geography.Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(org.openbravo.model.common.geography.Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public Boolean isTaxLocation() {
        return (Boolean) get(PROPERTY_TAXLOCATION);
    }

    public void setTaxLocation(Boolean taxLocation) {
        set(PROPERTY_TAXLOCATION, taxLocation);
    }

    public String getUPCEAN() {
        return (String) get(PROPERTY_UPCEAN);
    }

    public void setUPCEAN(String uPCEAN) {
        set(PROPERTY_UPCEAN, uPCEAN);
    }

    public String getEpcgGstno() {
        return (String) get(PROPERTY_EPCGGSTNO);
    }

    public void setEpcgGstno(String epcgGstno) {
        set(PROPERTY_EPCGGSTNO, epcgGstno);
    }

    @SuppressWarnings("unchecked")
    public List<User> getADUserList() {
        return (List<User>) get(PROPERTY_ADUSERLIST);
    }

    public void setADUserList(List<User> aDUserList) {
        set(PROPERTY_ADUSERLIST, aDUserList);
    }

    @SuppressWarnings("unchecked")
    public List<ActiveProposal> getActiveProposalVList() {
        return (List<ActiveProposal>) get(PROPERTY_ACTIVEPROPOSALVLIST);
    }

    public void setActiveProposalVList(List<ActiveProposal> activeProposalVList) {
        set(PROPERTY_ACTIVEPROPOSALVLIST, activeProposalVList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgProforma> getEpcgProformaList() {
        return (List<EpcgProforma>) get(PROPERTY_EPCGPROFORMALIST);
    }

    public void setEpcgProformaList(List<EpcgProforma> epcgProformaList) {
        set(PROPERTY_EPCGPROFORMALIST, epcgProformaList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
        return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceList() {
        return (List<Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2List() {
        return (List<InvoiceV2>) get(PROPERTY_INVOICEV2LIST);
    }

    public void setInvoiceV2List(List<InvoiceV2> invoiceV2List) {
        set(PROPERTY_INVOICEV2LIST, invoiceV2List);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
        return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutDeliveryLocationList() {
        return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTDELIVERYLOCATIONLIST);
    }

    public void setMaterialMgmtShipmentInOutDeliveryLocationList(List<ShipmentInOut> materialMgmtShipmentInOutDeliveryLocationList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTDELIVERYLOCATIONLIST, materialMgmtShipmentInOutDeliveryLocationList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderInvoiceAddressList() {
        return (List<Order>) get(PROPERTY_ORDERINVOICEADDRESSLIST);
    }

    public void setOrderInvoiceAddressList(List<Order> orderInvoiceAddressList) {
        set(PROPERTY_ORDERINVOICEADDRESSLIST, orderInvoiceAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderList() {
        return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderDropShipLocationList() {
        return (List<Order>) get(PROPERTY_ORDERDROPSHIPLOCATIONLIST);
    }

    public void setOrderDropShipLocationList(List<Order> orderDropShipLocationList) {
        set(PROPERTY_ORDERDROPSHIPLOCATIONLIST, orderDropShipLocationList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderDeliveryLocationList() {
        return (List<Order>) get(PROPERTY_ORDERDELIVERYLOCATIONLIST);
    }

    public void setOrderDeliveryLocationList(List<Order> orderDeliveryLocationList) {
        set(PROPERTY_ORDERDELIVERYLOCATIONLIST, orderDeliveryLocationList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
        return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectInvoiceAddressList() {
        return (List<Project>) get(PROPERTY_PROJECTINVOICEADDRESSLIST);
    }

    public void setProjectInvoiceAddressList(List<Project> projectInvoiceAddressList) {
        set(PROPERTY_PROJECTINVOICEADDRESSLIST, projectInvoiceAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposal> getProjectProposalList() {
        return (List<ProjectProposal>) get(PROPERTY_PROJECTPROPOSALLIST);
    }

    public void setProjectProposalList(List<ProjectProposal> projectProposalList) {
        set(PROPERTY_PROJECTPROPOSALLIST, projectProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDepartmentReceipt> getRCGIDepartmentReceiptList() {
        return (List<RCGIDepartmentReceipt>) get(PROPERTY_RCGIDEPARTMENTRECEIPTLIST);
    }

    public void setRCGIDepartmentReceiptList(List<RCGIDepartmentReceipt> rCGIDepartmentReceiptList) {
        set(PROPERTY_RCGIDEPARTMENTRECEIPTLIST, rCGIDepartmentReceiptList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MaterialReceipt> getRCGIMaterialReceiptList() {
        return (List<RCGI_MaterialReceipt>) get(PROPERTY_RCGIMATERIALRECEIPTLIST);
    }

    public void setRCGIMaterialReceiptList(List<RCGI_MaterialReceipt> rCGIMaterialReceiptList) {
        set(PROPERTY_RCGIMATERIALRECEIPTLIST, rCGIMaterialReceiptList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseQuotation> getRCOBPurchasequotationList() {
        return (List<PurchaseQuotation>) get(PROPERTY_RCOBPURCHASEQUOTATIONLIST);
    }

    public void setRCOBPurchasequotationList(List<PurchaseQuotation> rCOBPurchasequotationList) {
        set(PROPERTY_RCOBPURCHASEQUOTATIONLIST, rCOBPurchasequotationList);
    }

}
