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
package org.openbravo.model.pricing.pricelist;

import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.rcssob.data.Prlines;
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
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.ClientInformation;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.pos.ExternalPOS;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectVendor;
import org.openbravo.model.timeandexpense.Sheet;
/**
 * Entity class for entity PricingPriceList (stored in table M_PriceList).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PriceList extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_PriceList";
    public static final String ENTITY_NAME = "PricingPriceList";
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
    public static final String PROPERTY_BASEPRICELIST = "basePricelist";
    public static final String PROPERTY_PRICEINCLUDESTAX = "priceIncludesTax";
    public static final String PROPERTY_SALESPRICELIST = "salesPriceList";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_ENFORCEPRICELIMIT = "enforcePriceLimit";
    public static final String PROPERTY_COSTBASEDPRICELIST = "costBasedPriceList";
    public static final String PROPERTY_BUSINESSPARTNERLIST = "businessPartnerList";
    public static final String PROPERTY_BUSINESSPARTNERPURCHASEPRICELISTLIST = "businessPartnerPurchasePricelistList";
    public static final String PROPERTY_CLIENTINFORMATIONLIST = "clientInformationList";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_EXTERNALPOSLIST = "externalPOSList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_PRICINGADJUSTMENTPRICELISTLIST = "pricingAdjustmentPriceListList";
    public static final String PROPERTY_PRICINGPRICELISTBASEPRICELISTLIST = "pricingPriceListBasePricelistList";
    public static final String PROPERTY_PRICINGPRICELISTVERSIONLIST = "pricingPriceListVersionList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLIST = "procurementRequisitionList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLINELIST = "procurementRequisitionLineList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_PROJECTVENDORLIST = "projectVendorList";
    public static final String PROPERTY_RCOBPRLINESLIST = "rCOBPrlinesList";
    public static final String PROPERTY_RCOBPRREQUISITIONLIST = "rCOBPrrequisitionList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLIST = "rCOBPurchasequotationList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLIST = "timeAndExpenseSheetList";

    public PriceList() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PRICEINCLUDESTAX, false);
        setDefaultValue(PROPERTY_SALESPRICELIST, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_ENFORCEPRICELIMIT, false);
        setDefaultValue(PROPERTY_COSTBASEDPRICELIST, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERPURCHASEPRICELISTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTPRICELISTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGPRICELISTBASEPRICELISTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGPRICELISTVERSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPRREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPURCHASEQUOTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLIST, new ArrayList<Object>());
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

    public PriceList getBasePricelist() {
        return (PriceList) get(PROPERTY_BASEPRICELIST);
    }

    public void setBasePricelist(PriceList basePricelist) {
        set(PROPERTY_BASEPRICELIST, basePricelist);
    }

    public Boolean isPriceIncludesTax() {
        return (Boolean) get(PROPERTY_PRICEINCLUDESTAX);
    }

    public void setPriceIncludesTax(Boolean priceIncludesTax) {
        set(PROPERTY_PRICEINCLUDESTAX, priceIncludesTax);
    }

    public Boolean isSalesPriceList() {
        return (Boolean) get(PROPERTY_SALESPRICELIST);
    }

    public void setSalesPriceList(Boolean salesPriceList) {
        set(PROPERTY_SALESPRICELIST, salesPriceList);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Boolean isEnforcePriceLimit() {
        return (Boolean) get(PROPERTY_ENFORCEPRICELIMIT);
    }

    public void setEnforcePriceLimit(Boolean enforcePriceLimit) {
        set(PROPERTY_ENFORCEPRICELIMIT, enforcePriceLimit);
    }

    public Boolean isCostBasedPriceList() {
        return (Boolean) get(PROPERTY_COSTBASEDPRICELIST);
    }

    public void setCostBasedPriceList(Boolean costBasedPriceList) {
        set(PROPERTY_COSTBASEDPRICELIST, costBasedPriceList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERLIST);
    }

    public void setBusinessPartnerList(List<BusinessPartner> businessPartnerList) {
        set(PROPERTY_BUSINESSPARTNERLIST, businessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerPurchasePricelistList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERPURCHASEPRICELISTLIST);
    }

    public void setBusinessPartnerPurchasePricelistList(List<BusinessPartner> businessPartnerPurchasePricelistList) {
        set(PROPERTY_BUSINESSPARTNERPURCHASEPRICELISTLIST, businessPartnerPurchasePricelistList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONLIST);
    }

    public void setClientInformationList(List<ClientInformation> clientInformationList) {
        set(PROPERTY_CLIENTINFORMATIONLIST, clientInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOS> getExternalPOSList() {
        return (List<ExternalPOS>) get(PROPERTY_EXTERNALPOSLIST);
    }

    public void setExternalPOSList(List<ExternalPOS> externalPOSList) {
        set(PROPERTY_EXTERNALPOSLIST, externalPOSList);
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
    public List<Order> getOrderList() {
        return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.priceadjustment.PriceList> getPricingAdjustmentPriceListList() {
        return (List<org.openbravo.model.pricing.priceadjustment.PriceList>) get(PROPERTY_PRICINGADJUSTMENTPRICELISTLIST);
    }

    public void setPricingAdjustmentPriceListList(List<org.openbravo.model.pricing.priceadjustment.PriceList> pricingAdjustmentPriceListList) {
        set(PROPERTY_PRICINGADJUSTMENTPRICELISTLIST, pricingAdjustmentPriceListList);
    }

    @SuppressWarnings("unchecked")
    public List<PriceList> getPricingPriceListBasePricelistList() {
        return (List<PriceList>) get(PROPERTY_PRICINGPRICELISTBASEPRICELISTLIST);
    }

    public void setPricingPriceListBasePricelistList(List<PriceList> pricingPriceListBasePricelistList) {
        set(PROPERTY_PRICINGPRICELISTBASEPRICELISTLIST, pricingPriceListBasePricelistList);
    }

    @SuppressWarnings("unchecked")
    public List<PriceListVersion> getPricingPriceListVersionList() {
        return (List<PriceListVersion>) get(PROPERTY_PRICINGPRICELISTVERSIONLIST);
    }

    public void setPricingPriceListVersionList(List<PriceListVersion> pricingPriceListVersionList) {
        set(PROPERTY_PRICINGPRICELISTVERSIONLIST, pricingPriceListVersionList);
    }

    @SuppressWarnings("unchecked")
    public List<Requisition> getProcurementRequisitionList() {
        return (List<Requisition>) get(PROPERTY_PROCUREMENTREQUISITIONLIST);
    }

    public void setProcurementRequisitionList(List<Requisition> procurementRequisitionList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLIST, procurementRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitionLine> getProcurementRequisitionLineList() {
        return (List<RequisitionLine>) get(PROPERTY_PROCUREMENTREQUISITIONLINELIST);
    }

    public void setProcurementRequisitionLineList(List<RequisitionLine> procurementRequisitionLineList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLINELIST, procurementRequisitionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
        return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectVendor> getProjectVendorList() {
        return (List<ProjectVendor>) get(PROPERTY_PROJECTVENDORLIST);
    }

    public void setProjectVendorList(List<ProjectVendor> projectVendorList) {
        set(PROPERTY_PROJECTVENDORLIST, projectVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<Prlines> getRCOBPrlinesList() {
        return (List<Prlines>) get(PROPERTY_RCOBPRLINESLIST);
    }

    public void setRCOBPrlinesList(List<Prlines> rCOBPrlinesList) {
        set(PROPERTY_RCOBPRLINESLIST, rCOBPrlinesList);
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
    public List<Sheet> getTimeAndExpenseSheetList() {
        return (List<Sheet>) get(PROPERTY_TIMEANDEXPENSESHEETLIST);
    }

    public void setTimeAndExpenseSheetList(List<Sheet> timeAndExpenseSheetList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLIST, timeAndExpenseSheetList);
    }

}
