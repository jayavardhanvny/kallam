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

import com.redcarpet.epcg.data.EPCGChequebook;
import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.rcssob.data.PurchaseQuotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_PaymentProposalPickEdit;
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
import org.openbravo.model.common.order.Order;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectProposal;
/**
 * Entity class for entity FIN_PaymentMethod (stored in table FIN_PaymentMethod).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_PaymentMethod extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_PaymentMethod";
    public static final String ENTITY_NAME = "FIN_PaymentMethod";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_AUTOMATICRECEIPT = "automaticReceipt";
    public static final String PROPERTY_AUTOMATICPAYMENT = "automaticPayment";
    public static final String PROPERTY_AUTOMATICDEPOSIT = "automaticDeposit";
    public static final String PROPERTY_AUTOMATICWITHDRAWN = "automaticWithdrawn";
    public static final String PROPERTY_PAYINALLOW = "payinAllow";
    public static final String PROPERTY_PAYOUTALLOW = "payoutAllow";
    public static final String PROPERTY_PAYINEXECUTIONTYPE = "payinExecutionType";
    public static final String PROPERTY_PAYOUTEXECUTIONTYPE = "payoutExecutionType";
    public static final String PROPERTY_PAYINEXECUTIONPROCESS = "payinExecutionProcess";
    public static final String PROPERTY_PAYOUTEXECUTIONPROCESS = "payoutExecutionProcess";
    public static final String PROPERTY_PAYINDEFERRED = "payinDeferred";
    public static final String PROPERTY_PAYOUTDEFERRED = "payoutDeferred";
    public static final String PROPERTY_UPONRECEIPTUSE = "uponReceiptUse";
    public static final String PROPERTY_UPONDEPOSITUSE = "uponDepositUse";
    public static final String PROPERTY_INUPONCLEARINGUSE = "iNUponClearingUse";
    public static final String PROPERTY_UPONPAYMENTUSE = "uponPaymentUse";
    public static final String PROPERTY_UPONWITHDRAWALUSE = "uponWithdrawalUse";
    public static final String PROPERTY_OUTUPONCLEARINGUSE = "oUTUponClearingUse";
    public static final String PROPERTY_PAYINISMULTICURRENCY = "payinIsMulticurrency";
    public static final String PROPERTY_PAYOUTISMULTICURRENCY = "payoutIsMulticurrency";
    public static final String PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST = "aPRMPaymentProposalPickEditList";
    public static final String PROPERTY_BUSINESSPARTNERLIST = "businessPartnerList";
    public static final String PROPERTY_BUSINESSPARTNERPOPAYMENTMETHODLIST = "businessPartnerPOPaymentMethodList";
    public static final String PROPERTY_EPCGCHEQUEBOOKLIST = "ePCGChequebookList";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_FINDOUBTFULDEBTVLIST = "fINDoubtfulDebtVList";
    public static final String PROPERTY_FINPAYMENTLIST = "fINPaymentList";
    public static final String PROPERTY_FINPAYMENTDETAILVLIST = "fINPaymentDetailVList";
    public static final String PROPERTY_FINPAYMENTPROPOSALLIST = "fINPaymentProposalList";
    public static final String PROPERTY_FINPAYMENTSCHEDINVVLIST = "fINPaymentSchedInvVList";
    public static final String PROPERTY_FINPAYMENTSCHEDORDVLIST = "fINPaymentSchedOrdVList";
    public static final String PROPERTY_FINPAYMENTSCHEDULELIST = "fINPaymentScheduleList";
    public static final String PROPERTY_FINORIGPAYMENTSCHEDULELIST = "finOrigPaymentScheduleList";
    public static final String PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODLIST = "financialMgmtFinAccPaymentMethodList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTTERMLINELIST = "financialMgmtPaymentTermLineList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_PROJECTPROPOSALLIST = "projectProposalList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLIST = "rCOBPurchasequotationList";

    public FIN_PaymentMethod() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_AUTOMATICRECEIPT, false);
        setDefaultValue(PROPERTY_AUTOMATICPAYMENT, false);
        setDefaultValue(PROPERTY_AUTOMATICDEPOSIT, false);
        setDefaultValue(PROPERTY_AUTOMATICWITHDRAWN, false);
        setDefaultValue(PROPERTY_PAYINALLOW, true);
        setDefaultValue(PROPERTY_PAYOUTALLOW, true);
        setDefaultValue(PROPERTY_PAYINEXECUTIONTYPE, "M");
        setDefaultValue(PROPERTY_PAYOUTEXECUTIONTYPE, "M");
        setDefaultValue(PROPERTY_PAYINDEFERRED, false);
        setDefaultValue(PROPERTY_PAYOUTDEFERRED, false);
        setDefaultValue(PROPERTY_PAYINISMULTICURRENCY, false);
        setDefaultValue(PROPERTY_PAYOUTISMULTICURRENCY, false);
        setDefaultValue(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERPOPAYMENTMETHODLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCHEQUEBOOKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDINVVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDORDVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINORIGPAYMENTSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTTERMLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
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

    public Boolean isAutomaticReceipt() {
        return (Boolean) get(PROPERTY_AUTOMATICRECEIPT);
    }

    public void setAutomaticReceipt(Boolean automaticReceipt) {
        set(PROPERTY_AUTOMATICRECEIPT, automaticReceipt);
    }

    public Boolean isAutomaticPayment() {
        return (Boolean) get(PROPERTY_AUTOMATICPAYMENT);
    }

    public void setAutomaticPayment(Boolean automaticPayment) {
        set(PROPERTY_AUTOMATICPAYMENT, automaticPayment);
    }

    public Boolean isAutomaticDeposit() {
        return (Boolean) get(PROPERTY_AUTOMATICDEPOSIT);
    }

    public void setAutomaticDeposit(Boolean automaticDeposit) {
        set(PROPERTY_AUTOMATICDEPOSIT, automaticDeposit);
    }

    public Boolean isAutomaticWithdrawn() {
        return (Boolean) get(PROPERTY_AUTOMATICWITHDRAWN);
    }

    public void setAutomaticWithdrawn(Boolean automaticWithdrawn) {
        set(PROPERTY_AUTOMATICWITHDRAWN, automaticWithdrawn);
    }

    public Boolean isPayinAllow() {
        return (Boolean) get(PROPERTY_PAYINALLOW);
    }

    public void setPayinAllow(Boolean payinAllow) {
        set(PROPERTY_PAYINALLOW, payinAllow);
    }

    public Boolean isPayoutAllow() {
        return (Boolean) get(PROPERTY_PAYOUTALLOW);
    }

    public void setPayoutAllow(Boolean payoutAllow) {
        set(PROPERTY_PAYOUTALLOW, payoutAllow);
    }

    public String getPayinExecutionType() {
        return (String) get(PROPERTY_PAYINEXECUTIONTYPE);
    }

    public void setPayinExecutionType(String payinExecutionType) {
        set(PROPERTY_PAYINEXECUTIONTYPE, payinExecutionType);
    }

    public String getPayoutExecutionType() {
        return (String) get(PROPERTY_PAYOUTEXECUTIONTYPE);
    }

    public void setPayoutExecutionType(String payoutExecutionType) {
        set(PROPERTY_PAYOUTEXECUTIONTYPE, payoutExecutionType);
    }

    public PaymentExecutionProcess getPayinExecutionProcess() {
        return (PaymentExecutionProcess) get(PROPERTY_PAYINEXECUTIONPROCESS);
    }

    public void setPayinExecutionProcess(PaymentExecutionProcess payinExecutionProcess) {
        set(PROPERTY_PAYINEXECUTIONPROCESS, payinExecutionProcess);
    }

    public PaymentExecutionProcess getPayoutExecutionProcess() {
        return (PaymentExecutionProcess) get(PROPERTY_PAYOUTEXECUTIONPROCESS);
    }

    public void setPayoutExecutionProcess(PaymentExecutionProcess payoutExecutionProcess) {
        set(PROPERTY_PAYOUTEXECUTIONPROCESS, payoutExecutionProcess);
    }

    public Boolean isPayinDeferred() {
        return (Boolean) get(PROPERTY_PAYINDEFERRED);
    }

    public void setPayinDeferred(Boolean payinDeferred) {
        set(PROPERTY_PAYINDEFERRED, payinDeferred);
    }

    public Boolean isPayoutDeferred() {
        return (Boolean) get(PROPERTY_PAYOUTDEFERRED);
    }

    public void setPayoutDeferred(Boolean payoutDeferred) {
        set(PROPERTY_PAYOUTDEFERRED, payoutDeferred);
    }

    public String getUponReceiptUse() {
        return (String) get(PROPERTY_UPONRECEIPTUSE);
    }

    public void setUponReceiptUse(String uponReceiptUse) {
        set(PROPERTY_UPONRECEIPTUSE, uponReceiptUse);
    }

    public String getUponDepositUse() {
        return (String) get(PROPERTY_UPONDEPOSITUSE);
    }

    public void setUponDepositUse(String uponDepositUse) {
        set(PROPERTY_UPONDEPOSITUSE, uponDepositUse);
    }

    public String getINUponClearingUse() {
        return (String) get(PROPERTY_INUPONCLEARINGUSE);
    }

    public void setINUponClearingUse(String iNUponClearingUse) {
        set(PROPERTY_INUPONCLEARINGUSE, iNUponClearingUse);
    }

    public String getUponPaymentUse() {
        return (String) get(PROPERTY_UPONPAYMENTUSE);
    }

    public void setUponPaymentUse(String uponPaymentUse) {
        set(PROPERTY_UPONPAYMENTUSE, uponPaymentUse);
    }

    public String getUponWithdrawalUse() {
        return (String) get(PROPERTY_UPONWITHDRAWALUSE);
    }

    public void setUponWithdrawalUse(String uponWithdrawalUse) {
        set(PROPERTY_UPONWITHDRAWALUSE, uponWithdrawalUse);
    }

    public String getOUTUponClearingUse() {
        return (String) get(PROPERTY_OUTUPONCLEARINGUSE);
    }

    public void setOUTUponClearingUse(String oUTUponClearingUse) {
        set(PROPERTY_OUTUPONCLEARINGUSE, oUTUponClearingUse);
    }

    public Boolean isPayinIsMulticurrency() {
        return (Boolean) get(PROPERTY_PAYINISMULTICURRENCY);
    }

    public void setPayinIsMulticurrency(Boolean payinIsMulticurrency) {
        set(PROPERTY_PAYINISMULTICURRENCY, payinIsMulticurrency);
    }

    public Boolean isPayoutIsMulticurrency() {
        return (Boolean) get(PROPERTY_PAYOUTISMULTICURRENCY);
    }

    public void setPayoutIsMulticurrency(Boolean payoutIsMulticurrency) {
        set(PROPERTY_PAYOUTISMULTICURRENCY, payoutIsMulticurrency);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_PaymentProposalPickEdit> getAPRMPaymentProposalPickEditList() {
        return (List<APRM_PaymentProposalPickEdit>) get(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST);
    }

    public void setAPRMPaymentProposalPickEditList(List<APRM_PaymentProposalPickEdit> aPRMPaymentProposalPickEditList) {
        set(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, aPRMPaymentProposalPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERLIST);
    }

    public void setBusinessPartnerList(List<BusinessPartner> businessPartnerList) {
        set(PROPERTY_BUSINESSPARTNERLIST, businessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerPOPaymentMethodList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERPOPAYMENTMETHODLIST);
    }

    public void setBusinessPartnerPOPaymentMethodList(List<BusinessPartner> businessPartnerPOPaymentMethodList) {
        set(PROPERTY_BUSINESSPARTNERPOPAYMENTMETHODLIST, businessPartnerPOPaymentMethodList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGChequebook> getEPCGChequebookList() {
        return (List<EPCGChequebook>) get(PROPERTY_EPCGCHEQUEBOOKLIST);
    }

    public void setEPCGChequebookList(List<EPCGChequebook> ePCGChequebookList) {
        set(PROPERTY_EPCGCHEQUEBOOKLIST, ePCGChequebookList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtV> getFINDoubtfulDebtVList() {
        return (List<DoubtfulDebtV>) get(PROPERTY_FINDOUBTFULDEBTVLIST);
    }

    public void setFINDoubtfulDebtVList(List<DoubtfulDebtV> fINDoubtfulDebtVList) {
        set(PROPERTY_FINDOUBTFULDEBTVLIST, fINDoubtfulDebtVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_Payment> getFINPaymentList() {
        return (List<FIN_Payment>) get(PROPERTY_FINPAYMENTLIST);
    }

    public void setFINPaymentList(List<FIN_Payment> fINPaymentList) {
        set(PROPERTY_FINPAYMENTLIST, fINPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentDetailV> getFINPaymentDetailVList() {
        return (List<FIN_PaymentDetailV>) get(PROPERTY_FINPAYMENTDETAILVLIST);
    }

    public void setFINPaymentDetailVList(List<FIN_PaymentDetailV> fINPaymentDetailVList) {
        set(PROPERTY_FINPAYMENTDETAILVLIST, fINPaymentDetailVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentProposal> getFINPaymentProposalList() {
        return (List<FIN_PaymentProposal>) get(PROPERTY_FINPAYMENTPROPOSALLIST);
    }

    public void setFINPaymentProposalList(List<FIN_PaymentProposal> fINPaymentProposalList) {
        set(PROPERTY_FINPAYMENTPROPOSALLIST, fINPaymentProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedInvV> getFINPaymentSchedInvVList() {
        return (List<FIN_PaymentSchedInvV>) get(PROPERTY_FINPAYMENTSCHEDINVVLIST);
    }

    public void setFINPaymentSchedInvVList(List<FIN_PaymentSchedInvV> fINPaymentSchedInvVList) {
        set(PROPERTY_FINPAYMENTSCHEDINVVLIST, fINPaymentSchedInvVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedOrdV> getFINPaymentSchedOrdVList() {
        return (List<FIN_PaymentSchedOrdV>) get(PROPERTY_FINPAYMENTSCHEDORDVLIST);
    }

    public void setFINPaymentSchedOrdVList(List<FIN_PaymentSchedOrdV> fINPaymentSchedOrdVList) {
        set(PROPERTY_FINPAYMENTSCHEDORDVLIST, fINPaymentSchedOrdVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentSchedule> getFINPaymentScheduleList() {
        return (List<FIN_PaymentSchedule>) get(PROPERTY_FINPAYMENTSCHEDULELIST);
    }

    public void setFINPaymentScheduleList(List<FIN_PaymentSchedule> fINPaymentScheduleList) {
        set(PROPERTY_FINPAYMENTSCHEDULELIST, fINPaymentScheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<Fin_OrigPaymentSchedule> getFinOrigPaymentScheduleList() {
        return (List<Fin_OrigPaymentSchedule>) get(PROPERTY_FINORIGPAYMENTSCHEDULELIST);
    }

    public void setFinOrigPaymentScheduleList(List<Fin_OrigPaymentSchedule> finOrigPaymentScheduleList) {
        set(PROPERTY_FINORIGPAYMENTSCHEDULELIST, finOrigPaymentScheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccPaymentMethod> getFinancialMgmtFinAccPaymentMethodList() {
        return (List<FinAccPaymentMethod>) get(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODLIST);
    }

    public void setFinancialMgmtFinAccPaymentMethodList(List<FinAccPaymentMethod> financialMgmtFinAccPaymentMethodList) {
        set(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODLIST, financialMgmtFinAccPaymentMethodList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
        return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentTermLine> getFinancialMgmtPaymentTermLineList() {
        return (List<PaymentTermLine>) get(PROPERTY_FINANCIALMGMTPAYMENTTERMLINELIST);
    }

    public void setFinancialMgmtPaymentTermLineList(List<PaymentTermLine> financialMgmtPaymentTermLineList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTTERMLINELIST, financialMgmtPaymentTermLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceList() {
        return (List<Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
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
