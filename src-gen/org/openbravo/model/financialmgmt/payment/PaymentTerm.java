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
package org.openbravo.model.financialmgmt.payment;

import com.redcarpet.epcg.data.EpcgCostenquiry;
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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.financialmgmt.tax.Withholding;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectProposal;
/**
 * Entity class for entity FinancialMgmtPaymentTerm (stored in table C_PaymentTerm).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PaymentTerm extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_PaymentTerm";
    public static final String ENTITY_NAME = "FinancialMgmtPaymentTerm";
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
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_FIXEDDUEDATE = "fixedDueDate";
    public static final String PROPERTY_OVERDUEPAYMENTDAYSRULE = "overduePaymentDaysRule";
    public static final String PROPERTY_MATURITYDATE1 = "maturityDate1";
    public static final String PROPERTY_OFFSETMONTHDUE = "offsetMonthDue";
    public static final String PROPERTY_NEXTBUSINESSDAY = "nextBusinessDay";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_OVERDUEPAYMENTDAYRULE = "overduePaymentDayRule";
    public static final String PROPERTY_VALID = "valid";
    public static final String PROPERTY_MATURITYDATE2 = "maturityDate2";
    public static final String PROPERTY_MATURITYDATE3 = "maturityDate3";
    public static final String PROPERTY_BUSINESSPARTNERLIST = "businessPartnerList";
    public static final String PROPERTY_BUSINESSPARTNERPOPAYMENTTERMSLIST = "businessPartnerPOPaymentTermsList";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTTERMLINELIST = "financialMgmtPaymentTermLineList";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTTERMTRLLIST = "financialMgmtPaymentTermTrlList";
    public static final String PROPERTY_FINANCIALMGMTWITHHOLDINGLIST = "financialMgmtWithholdingList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_PROJECTPROPOSALLIST = "projectProposalList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLIST = "rCOBPurchasequotationList";

    public PaymentTerm() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_FIXEDDUEDATE, false);
        setDefaultValue(PROPERTY_NEXTBUSINESSDAY, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_VALID, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERPOPAYMENTTERMSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTTERMLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTTERMTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTWITHHOLDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPROPOSALLIST, new ArrayList<Object>());
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public Boolean isFixedDueDate() {
        return (Boolean) get(PROPERTY_FIXEDDUEDATE);
    }

    public void setFixedDueDate(Boolean fixedDueDate) {
        set(PROPERTY_FIXEDDUEDATE, fixedDueDate);
    }

    public Long getOverduePaymentDaysRule() {
        return (Long) get(PROPERTY_OVERDUEPAYMENTDAYSRULE);
    }

    public void setOverduePaymentDaysRule(Long overduePaymentDaysRule) {
        set(PROPERTY_OVERDUEPAYMENTDAYSRULE, overduePaymentDaysRule);
    }

    public Long getMaturityDate1() {
        return (Long) get(PROPERTY_MATURITYDATE1);
    }

    public void setMaturityDate1(Long maturityDate1) {
        set(PROPERTY_MATURITYDATE1, maturityDate1);
    }

    public Long getOffsetMonthDue() {
        return (Long) get(PROPERTY_OFFSETMONTHDUE);
    }

    public void setOffsetMonthDue(Long offsetMonthDue) {
        set(PROPERTY_OFFSETMONTHDUE, offsetMonthDue);
    }

    public Boolean isNextBusinessDay() {
        return (Boolean) get(PROPERTY_NEXTBUSINESSDAY);
    }

    public void setNextBusinessDay(Boolean nextBusinessDay) {
        set(PROPERTY_NEXTBUSINESSDAY, nextBusinessDay);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getOverduePaymentDayRule() {
        return (String) get(PROPERTY_OVERDUEPAYMENTDAYRULE);
    }

    public void setOverduePaymentDayRule(String overduePaymentDayRule) {
        set(PROPERTY_OVERDUEPAYMENTDAYRULE, overduePaymentDayRule);
    }

    public Boolean isValid() {
        return (Boolean) get(PROPERTY_VALID);
    }

    public void setValid(Boolean valid) {
        set(PROPERTY_VALID, valid);
    }

    public Long getMaturityDate2() {
        return (Long) get(PROPERTY_MATURITYDATE2);
    }

    public void setMaturityDate2(Long maturityDate2) {
        set(PROPERTY_MATURITYDATE2, maturityDate2);
    }

    public Long getMaturityDate3() {
        return (Long) get(PROPERTY_MATURITYDATE3);
    }

    public void setMaturityDate3(Long maturityDate3) {
        set(PROPERTY_MATURITYDATE3, maturityDate3);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERLIST);
    }

    public void setBusinessPartnerList(List<BusinessPartner> businessPartnerList) {
        set(PROPERTY_BUSINESSPARTNERLIST, businessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerPOPaymentTermsList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERPOPAYMENTTERMSLIST);
    }

    public void setBusinessPartnerPOPaymentTermsList(List<BusinessPartner> businessPartnerPOPaymentTermsList) {
        set(PROPERTY_BUSINESSPARTNERPOPAYMENTTERMSLIST, businessPartnerPOPaymentTermsList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentTermLine> getFinancialMgmtPaymentTermLineList() {
        return (List<PaymentTermLine>) get(PROPERTY_FINANCIALMGMTPAYMENTTERMLINELIST);
    }

    public void setFinancialMgmtPaymentTermLineList(List<PaymentTermLine> financialMgmtPaymentTermLineList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTTERMLINELIST, financialMgmtPaymentTermLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentTermTrl> getFinancialMgmtPaymentTermTrlList() {
        return (List<PaymentTermTrl>) get(PROPERTY_FINANCIALMGMTPAYMENTTERMTRLLIST);
    }

    public void setFinancialMgmtPaymentTermTrlList(List<PaymentTermTrl> financialMgmtPaymentTermTrlList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTTERMTRLLIST, financialMgmtPaymentTermTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<Withholding> getFinancialMgmtWithholdingList() {
        return (List<Withholding>) get(PROPERTY_FINANCIALMGMTWITHHOLDINGLIST);
    }

    public void setFinancialMgmtWithholdingList(List<Withholding> financialMgmtWithholdingList) {
        set(PROPERTY_FINANCIALMGMTWITHHOLDINGLIST, financialMgmtWithholdingList);
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
    public List<Project> getProjectList() {
        return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposal> getProjectProposalList() {
        return (List<ProjectProposal>) get(PROPERTY_PROJECTPROPOSALLIST);
    }

    public void setProjectProposalList(List<ProjectProposal> projectProposalList) {
        set(PROPERTY_PROJECTPROPOSALLIST, projectProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseQuotation> getRCOBPurchasequotationList() {
        return (List<PurchaseQuotation>) get(PROPERTY_RCOBPURCHASEQUOTATIONLIST);
    }

    public void setRCOBPurchasequotationList(List<PurchaseQuotation> rCOBPurchasequotationList) {
        set(PROPERTY_RCOBPURCHASEQUOTATIONLIST, rCOBPurchasequotationList);
    }

}
