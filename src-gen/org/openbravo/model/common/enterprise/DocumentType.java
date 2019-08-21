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
package org.openbravo.model.common.enterprise;

import com.rcss.humanresource.data.RCHR_Directwarp;
import com.rcss.humanresource.data.RCHR_Sizingsheet;
import com.rcss.humanresource.data.RchrApprovalHistrory;
import com.rcss.humanresource.data.RchrBanksalpayments;
import com.rcss.humanresource.data.RchrExbanksalpayments;
import com.rcss.humanresource.data.RchrJoinRejoiningform;
import com.rcss.humanresource.data.RchrMemoShift;
import com.redcarpet.epcg.data.EPCGDocuser;
import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.EpcgMof;
import com.redcarpet.epcg.data.EpcgProforma;
import com.redcarpet.epcg.data.EpcgYarncosttemplate;
import com.redcarpet.goodsissue.data.RCGIDepartmentIssue;
import com.redcarpet.goodsissue.data.RCGIDepartmentReceipt;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RCGI_MaterialReceipt;
import com.redcarpet.goodsissue.data.RcgiMaterialIndent;
import com.redcarpet.lotmanagement.data.RCLO_LotIssue;
import com.redcarpet.lotmanagement.data.RCLO_LotIssueLine;
import com.redcarpet.payroll.data.RcplPayrollprocessmanual;
import com.redcarpet.production.data.RCPR_PreventiveMaintenanceOrder;
import com.redcarpet.rcssob.data.PurchaseQuotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_Reconciliation_v;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.gl.GLCategory;
import org.openbravo.model.financialmgmt.gl.GLJournal;
import org.openbravo.model.financialmgmt.payment.DPManagement;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebt;
import org.openbravo.model.financialmgmt.payment.FIN_BankStatement;
import org.openbravo.model.financialmgmt.payment.FIN_Payment;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentProposal;
import org.openbravo.model.financialmgmt.payment.FIN_Reconciliation;
import org.openbravo.model.financialmgmt.payment.Settlement;
import org.openbravo.model.financialmgmt.tax.TaxRegisterTypeLines;
import org.openbravo.model.manufacturing.transaction.WorkRequirement;
import org.openbravo.model.materialmgmt.transaction.InternalMovement;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.pos.ExternalPOS;
import org.openbravo.model.procurement.Requisition;
/**
 * Entity class for entity DocumentType (stored in table C_DocType).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class DocumentType extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_DocType";
    public static final String ENTITY_NAME = "DocumentType";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PRINTTEXT = "printText";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_DOCUMENTCATEGORY = "documentCategory";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";
    public static final String PROPERTY_SOSUBTYPE = "sOSubType";
    public static final String PROPERTY_DOCUMENTTYPEFORSHIPMENT = "documentTypeForShipment";
    public static final String PROPERTY_DOCUMENTTYPEFORINVOICE = "documentTypeForInvoice";
    public static final String PROPERTY_SEQUENCEDDOCUMENT = "sequencedDocument";
    public static final String PROPERTY_DOCUMENTSEQUENCE = "documentSequence";
    public static final String PROPERTY_GLCATEGORY = "gLCategory";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_NUMBEROFCOPIES = "numberOfCopies";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_FILTERBYORGANIZATION = "filterByOrganization";
    public static final String PROPERTY_DOCUMENTCANCELLED = "documentCancelled";
    public static final String PROPERTY_EXPENSE = "expense";
    public static final String PROPERTY_REVERSAL = "reversal";
    public static final String PROPERTY_RETURN = "return";
    public static final String PROPERTY_DOCUMENTTYPEFORORDER = "documentTypeForOrder";
    public static final String PROPERTY_EPCGISMOVEMENT = "epcgIsmovement";
    public static final String PROPERTY_EPCGISPREVENTIVE = "epcgIspreventive";
    public static final String PROPERTY_EPCGISMATERIALISSUE = "epcgIsmaterialissue";
    public static final String PROPERTY_EPCGISMATERIALRECEIPT = "epcgIsmaterialreceipt";
    public static final String PROPERTY_EPCGISREQUISITION = "epcgIsrequisition";
    public static final String PROPERTY_EPCGISGOODSISSUE = "epcgIsgoodsissue";
    public static final String PROPERTY_RCHRISEMPLOYEE = "rchrIsemployee";
    public static final String PROPERTY_RCHRISWARPING = "rchrIswarping";
    public static final String PROPERTY_RCHRISSIZING = "rchrIssizing";
    public static final String PROPERTY_EPCGISFABRIC = "epcgIsfabric";
    public static final String PROPERTY_EPCGFABRICTYPE = "epcgFabrictype";
    public static final String PROPERTY_APRMRECONCILIATIONLIST = "aPRMReconciliationList";
    public static final String PROPERTY_DOCUMENTTEMPLATELIST = "documentTemplateList";
    public static final String PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORSHIPMENTLIST = "documentTypeDocumentTypeForShipmentList";
    public static final String PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORINVOICELIST = "documentTypeDocumentTypeForInvoiceList";
    public static final String PROPERTY_DOCUMENTTYPEDOCUMENTCANCELLEDLIST = "documentTypeDocumentCancelledList";
    public static final String PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORORDERLIST = "documentTypeDocumentTypeForOrderList";
    public static final String PROPERTY_DOCUMENTTYPETRLLIST = "documentTypeTrlList";
    public static final String PROPERTY_EPCGDOCUSERLIST = "ePCGDocuserList";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_EPCGMOFLIST = "epcgMofList";
    public static final String PROPERTY_EPCGPROFORMALIST = "epcgProformaList";
    public static final String PROPERTY_EPCGYARNCOSTTEMPLATELIST = "epcgYarncosttemplateList";
    public static final String PROPERTY_EXTERNALPOSLIST = "externalPOSList";
    public static final String PROPERTY_FINBANKSTATEMENTLIST = "fINBankStatementList";
    public static final String PROPERTY_FINDOUBTFULDEBTLIST = "fINDoubtfulDebtList";
    public static final String PROPERTY_FINPAYMENTLIST = "fINPaymentList";
    public static final String PROPERTY_FINPAYMENTPROPOSALLIST = "fINPaymentProposalList";
    public static final String PROPERTY_FINRECONCILIATIONLIST = "fINReconciliationList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTDPMANAGEMENTLIST = "financialMgmtDPManagementList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTSETTLEMENTLIST = "financialMgmtSettlementList";
    public static final String PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST = "financialMgmtTaxRegisterTypeLinesList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICETRANSACTIONDOCUMENTLIST = "invoiceTransactionDocumentList";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_INVOICEV2TRANSACTIONDOCUMENTLIST = "invoiceV2TransactionDocumentList";
    public static final String PROPERTY_MANUFACTURINGWORKREQUIREMENTLIST = "manufacturingWorkRequirementList";
    public static final String PROPERTY_MATERIALMGMTINTERNALMOVEMENTLIST = "materialMgmtInternalMovementList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERTRANSACTIONDOCUMENTLIST = "orderTransactionDocumentList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLIST = "procurementRequisitionList";
    public static final String PROPERTY_RCGIDEPARTMENTISSUELIST = "rCGIDepartmentIssueList";
    public static final String PROPERTY_RCGIDEPARTMENTRECEIPTLIST = "rCGIDepartmentReceiptList";
    public static final String PROPERTY_RCGIGOODSISSUELIST = "rCGIGoodsIssueList";
    public static final String PROPERTY_RCGIMATERIALINDENTLIST = "rCGIMaterialIndentList";
    public static final String PROPERTY_RCGIMATERIALISSUELIST = "rCGIMaterialIssueList";
    public static final String PROPERTY_RCGIMATERIALRECEIPTLIST = "rCGIMaterialReceiptList";
    public static final String PROPERTY_RCHRAPPROVALHISTRORYLIST = "rCHRApprovalHistroryList";
    public static final String PROPERTY_RCHRDIRECTWARPLIST = "rCHRDirectwarpList";
    public static final String PROPERTY_RCHRSIZINGSHEETLIST = "rCHRSizingsheetList";
    public static final String PROPERTY_RCLOLOTISSUELIST = "rCLOLotIssueList";
    public static final String PROPERTY_RCLOLOTISSUELINELIST = "rCLOLotIssueLineList";
    public static final String PROPERTY_RCOBPURCHASEQUOTATIONLIST = "rCOBPurchasequotationList";
    public static final String PROPERTY_RCPRPREVENTIVEMAINTENANCELIST = "rCPRPreventivemaintenanceList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSLIST = "rchrBanksalpaymentsList";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSLIST = "rchrExbanksalpaymentsList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";
    public static final String PROPERTY_RCHRMEMOSHIFTLIST = "rchrMemoShiftList";
    public static final String PROPERTY_RCPLPAYROLLPROCESSMANUALLIST = "rcplPayrollprocessmanualList";

    public DocumentType() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SALESTRANSACTION, false);
        setDefaultValue(PROPERTY_SEQUENCEDDOCUMENT, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_NUMBEROFCOPIES, (long) 1);
        setDefaultValue(PROPERTY_FILTERBYORGANIZATION, false);
        setDefaultValue(PROPERTY_EXPENSE, false);
        setDefaultValue(PROPERTY_REVERSAL, false);
        setDefaultValue(PROPERTY_RETURN, false);
        setDefaultValue(PROPERTY_EPCGISMOVEMENT, false);
        setDefaultValue(PROPERTY_EPCGISPREVENTIVE, false);
        setDefaultValue(PROPERTY_EPCGISMATERIALISSUE, false);
        setDefaultValue(PROPERTY_EPCGISMATERIALRECEIPT, false);
        setDefaultValue(PROPERTY_EPCGISREQUISITION, false);
        setDefaultValue(PROPERTY_EPCGISGOODSISSUE, false);
        setDefaultValue(PROPERTY_RCHRISEMPLOYEE, false);
        setDefaultValue(PROPERTY_RCHRISWARPING, false);
        setDefaultValue(PROPERTY_RCHRISSIZING, false);
        setDefaultValue(PROPERTY_EPCGISFABRIC, false);
        setDefaultValue(PROPERTY_APRMRECONCILIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORSHIPMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPEDOCUMENTCANCELLEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGDOCUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGMOFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPROFORMALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGYARNCOSTTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINBANKSTATEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINRECONCILIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDPMANAGEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTSETTLEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICETRANSACTIONDOCUMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2TRANSACTIONDOCUMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKREQUIREMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERTRANSACTIONDOCUMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDEPARTMENTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDEPARTMENTRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIGOODSISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALINDENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAPPROVALHISTRORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRECTWARPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCLOLOTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCLOLOTISSUELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCOBPURCHASEQUOTATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRPREVENTIVEMAINTENANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREXBANKSALPAYMENTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMEMOSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPROCESSMANUALLIST, new ArrayList<Object>());
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

    public String getPrintText() {
        return (String) get(PROPERTY_PRINTTEXT);
    }

    public void setPrintText(String printText) {
        set(PROPERTY_PRINTTEXT, printText);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getDocumentCategory() {
        return (String) get(PROPERTY_DOCUMENTCATEGORY);
    }

    public void setDocumentCategory(String documentCategory) {
        set(PROPERTY_DOCUMENTCATEGORY, documentCategory);
    }

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
    }

    public String getSOSubType() {
        return (String) get(PROPERTY_SOSUBTYPE);
    }

    public void setSOSubType(String sOSubType) {
        set(PROPERTY_SOSUBTYPE, sOSubType);
    }

    public DocumentType getDocumentTypeForShipment() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPEFORSHIPMENT);
    }

    public void setDocumentTypeForShipment(DocumentType documentTypeForShipment) {
        set(PROPERTY_DOCUMENTTYPEFORSHIPMENT, documentTypeForShipment);
    }

    public DocumentType getDocumentTypeForInvoice() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPEFORINVOICE);
    }

    public void setDocumentTypeForInvoice(DocumentType documentTypeForInvoice) {
        set(PROPERTY_DOCUMENTTYPEFORINVOICE, documentTypeForInvoice);
    }

    public Boolean isSequencedDocument() {
        return (Boolean) get(PROPERTY_SEQUENCEDDOCUMENT);
    }

    public void setSequencedDocument(Boolean sequencedDocument) {
        set(PROPERTY_SEQUENCEDDOCUMENT, sequencedDocument);
    }

    public Sequence getDocumentSequence() {
        return (Sequence) get(PROPERTY_DOCUMENTSEQUENCE);
    }

    public void setDocumentSequence(Sequence documentSequence) {
        set(PROPERTY_DOCUMENTSEQUENCE, documentSequence);
    }

    public GLCategory getGLCategory() {
        return (GLCategory) get(PROPERTY_GLCATEGORY);
    }

    public void setGLCategory(GLCategory gLCategory) {
        set(PROPERTY_GLCATEGORY, gLCategory);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public Long getNumberOfCopies() {
        return (Long) get(PROPERTY_NUMBEROFCOPIES);
    }

    public void setNumberOfCopies(Long numberOfCopies) {
        set(PROPERTY_NUMBEROFCOPIES, numberOfCopies);
    }

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
    }

    public Boolean isFilterByOrganization() {
        return (Boolean) get(PROPERTY_FILTERBYORGANIZATION);
    }

    public void setFilterByOrganization(Boolean filterByOrganization) {
        set(PROPERTY_FILTERBYORGANIZATION, filterByOrganization);
    }

    public DocumentType getDocumentCancelled() {
        return (DocumentType) get(PROPERTY_DOCUMENTCANCELLED);
    }

    public void setDocumentCancelled(DocumentType documentCancelled) {
        set(PROPERTY_DOCUMENTCANCELLED, documentCancelled);
    }

    public Boolean isExpense() {
        return (Boolean) get(PROPERTY_EXPENSE);
    }

    public void setExpense(Boolean expense) {
        set(PROPERTY_EXPENSE, expense);
    }

    public Boolean isReversal() {
        return (Boolean) get(PROPERTY_REVERSAL);
    }

    public void setReversal(Boolean reversal) {
        set(PROPERTY_REVERSAL, reversal);
    }

    public Boolean isReturn() {
        return (Boolean) get(PROPERTY_RETURN);
    }

    public void setReturn(Boolean rturn) {
        set(PROPERTY_RETURN, rturn);
    }

    public DocumentType getDocumentTypeForOrder() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPEFORORDER);
    }

    public void setDocumentTypeForOrder(DocumentType documentTypeForOrder) {
        set(PROPERTY_DOCUMENTTYPEFORORDER, documentTypeForOrder);
    }

    public Boolean isEpcgIsmovement() {
        return (Boolean) get(PROPERTY_EPCGISMOVEMENT);
    }

    public void setEpcgIsmovement(Boolean epcgIsmovement) {
        set(PROPERTY_EPCGISMOVEMENT, epcgIsmovement);
    }

    public Boolean isEpcgIspreventive() {
        return (Boolean) get(PROPERTY_EPCGISPREVENTIVE);
    }

    public void setEpcgIspreventive(Boolean epcgIspreventive) {
        set(PROPERTY_EPCGISPREVENTIVE, epcgIspreventive);
    }

    public Boolean isEpcgIsmaterialissue() {
        return (Boolean) get(PROPERTY_EPCGISMATERIALISSUE);
    }

    public void setEpcgIsmaterialissue(Boolean epcgIsmaterialissue) {
        set(PROPERTY_EPCGISMATERIALISSUE, epcgIsmaterialissue);
    }

    public Boolean isEpcgIsmaterialreceipt() {
        return (Boolean) get(PROPERTY_EPCGISMATERIALRECEIPT);
    }

    public void setEpcgIsmaterialreceipt(Boolean epcgIsmaterialreceipt) {
        set(PROPERTY_EPCGISMATERIALRECEIPT, epcgIsmaterialreceipt);
    }

    public Boolean isEpcgIsrequisition() {
        return (Boolean) get(PROPERTY_EPCGISREQUISITION);
    }

    public void setEpcgIsrequisition(Boolean epcgIsrequisition) {
        set(PROPERTY_EPCGISREQUISITION, epcgIsrequisition);
    }

    public Boolean isEpcgIsgoodsissue() {
        return (Boolean) get(PROPERTY_EPCGISGOODSISSUE);
    }

    public void setEpcgIsgoodsissue(Boolean epcgIsgoodsissue) {
        set(PROPERTY_EPCGISGOODSISSUE, epcgIsgoodsissue);
    }

    public Boolean isRchrIsemployee() {
        return (Boolean) get(PROPERTY_RCHRISEMPLOYEE);
    }

    public void setRchrIsemployee(Boolean rchrIsemployee) {
        set(PROPERTY_RCHRISEMPLOYEE, rchrIsemployee);
    }

    public Boolean isRchrIswarping() {
        return (Boolean) get(PROPERTY_RCHRISWARPING);
    }

    public void setRchrIswarping(Boolean rchrIswarping) {
        set(PROPERTY_RCHRISWARPING, rchrIswarping);
    }

    public Boolean isRchrIssizing() {
        return (Boolean) get(PROPERTY_RCHRISSIZING);
    }

    public void setRchrIssizing(Boolean rchrIssizing) {
        set(PROPERTY_RCHRISSIZING, rchrIssizing);
    }

    public Boolean isEpcgIsfabric() {
        return (Boolean) get(PROPERTY_EPCGISFABRIC);
    }

    public void setEpcgIsfabric(Boolean epcgIsfabric) {
        set(PROPERTY_EPCGISFABRIC, epcgIsfabric);
    }

    public String getEpcgFabrictype() {
        return (String) get(PROPERTY_EPCGFABRICTYPE);
    }

    public void setEpcgFabrictype(String epcgFabrictype) {
        set(PROPERTY_EPCGFABRICTYPE, epcgFabrictype);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Reconciliation_v> getAPRMReconciliationList() {
        return (List<APRM_Reconciliation_v>) get(PROPERTY_APRMRECONCILIATIONLIST);
    }

    public void setAPRMReconciliationList(List<APRM_Reconciliation_v> aPRMReconciliationList) {
        set(PROPERTY_APRMRECONCILIATIONLIST, aPRMReconciliationList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentTemplate> getDocumentTemplateList() {
        return (List<DocumentTemplate>) get(PROPERTY_DOCUMENTTEMPLATELIST);
    }

    public void setDocumentTemplateList(List<DocumentTemplate> documentTemplateList) {
        set(PROPERTY_DOCUMENTTEMPLATELIST, documentTemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeDocumentTypeForShipmentList() {
        return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORSHIPMENTLIST);
    }

    public void setDocumentTypeDocumentTypeForShipmentList(List<DocumentType> documentTypeDocumentTypeForShipmentList) {
        set(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORSHIPMENTLIST, documentTypeDocumentTypeForShipmentList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeDocumentTypeForInvoiceList() {
        return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORINVOICELIST);
    }

    public void setDocumentTypeDocumentTypeForInvoiceList(List<DocumentType> documentTypeDocumentTypeForInvoiceList) {
        set(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORINVOICELIST, documentTypeDocumentTypeForInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeDocumentCancelledList() {
        return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPEDOCUMENTCANCELLEDLIST);
    }

    public void setDocumentTypeDocumentCancelledList(List<DocumentType> documentTypeDocumentCancelledList) {
        set(PROPERTY_DOCUMENTTYPEDOCUMENTCANCELLEDLIST, documentTypeDocumentCancelledList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeDocumentTypeForOrderList() {
        return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORORDERLIST);
    }

    public void setDocumentTypeDocumentTypeForOrderList(List<DocumentType> documentTypeDocumentTypeForOrderList) {
        set(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORORDERLIST, documentTypeDocumentTypeForOrderList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentTypeTrl> getDocumentTypeTrlList() {
        return (List<DocumentTypeTrl>) get(PROPERTY_DOCUMENTTYPETRLLIST);
    }

    public void setDocumentTypeTrlList(List<DocumentTypeTrl> documentTypeTrlList) {
        set(PROPERTY_DOCUMENTTYPETRLLIST, documentTypeTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGDocuser> getEPCGDocuserList() {
        return (List<EPCGDocuser>) get(PROPERTY_EPCGDOCUSERLIST);
    }

    public void setEPCGDocuserList(List<EPCGDocuser> ePCGDocuserList) {
        set(PROPERTY_EPCGDOCUSERLIST, ePCGDocuserList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgMof> getEpcgMofList() {
        return (List<EpcgMof>) get(PROPERTY_EPCGMOFLIST);
    }

    public void setEpcgMofList(List<EpcgMof> epcgMofList) {
        set(PROPERTY_EPCGMOFLIST, epcgMofList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgProforma> getEpcgProformaList() {
        return (List<EpcgProforma>) get(PROPERTY_EPCGPROFORMALIST);
    }

    public void setEpcgProformaList(List<EpcgProforma> epcgProformaList) {
        set(PROPERTY_EPCGPROFORMALIST, epcgProformaList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgYarncosttemplate> getEpcgYarncosttemplateList() {
        return (List<EpcgYarncosttemplate>) get(PROPERTY_EPCGYARNCOSTTEMPLATELIST);
    }

    public void setEpcgYarncosttemplateList(List<EpcgYarncosttemplate> epcgYarncosttemplateList) {
        set(PROPERTY_EPCGYARNCOSTTEMPLATELIST, epcgYarncosttemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOS> getExternalPOSList() {
        return (List<ExternalPOS>) get(PROPERTY_EXTERNALPOSLIST);
    }

    public void setExternalPOSList(List<ExternalPOS> externalPOSList) {
        set(PROPERTY_EXTERNALPOSLIST, externalPOSList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_BankStatement> getFINBankStatementList() {
        return (List<FIN_BankStatement>) get(PROPERTY_FINBANKSTATEMENTLIST);
    }

    public void setFINBankStatementList(List<FIN_BankStatement> fINBankStatementList) {
        set(PROPERTY_FINBANKSTATEMENTLIST, fINBankStatementList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebt> getFINDoubtfulDebtList() {
        return (List<DoubtfulDebt>) get(PROPERTY_FINDOUBTFULDEBTLIST);
    }

    public void setFINDoubtfulDebtList(List<DoubtfulDebt> fINDoubtfulDebtList) {
        set(PROPERTY_FINDOUBTFULDEBTLIST, fINDoubtfulDebtList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_Payment> getFINPaymentList() {
        return (List<FIN_Payment>) get(PROPERTY_FINPAYMENTLIST);
    }

    public void setFINPaymentList(List<FIN_Payment> fINPaymentList) {
        set(PROPERTY_FINPAYMENTLIST, fINPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentProposal> getFINPaymentProposalList() {
        return (List<FIN_PaymentProposal>) get(PROPERTY_FINPAYMENTPROPOSALLIST);
    }

    public void setFINPaymentProposalList(List<FIN_PaymentProposal> fINPaymentProposalList) {
        set(PROPERTY_FINPAYMENTPROPOSALLIST, fINPaymentProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_Reconciliation> getFINReconciliationList() {
        return (List<FIN_Reconciliation>) get(PROPERTY_FINRECONCILIATIONLIST);
    }

    public void setFINReconciliationList(List<FIN_Reconciliation> fINReconciliationList) {
        set(PROPERTY_FINRECONCILIATIONLIST, fINReconciliationList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<DPManagement> getFinancialMgmtDPManagementList() {
        return (List<DPManagement>) get(PROPERTY_FINANCIALMGMTDPMANAGEMENTLIST);
    }

    public void setFinancialMgmtDPManagementList(List<DPManagement> financialMgmtDPManagementList) {
        set(PROPERTY_FINANCIALMGMTDPMANAGEMENTLIST, financialMgmtDPManagementList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getFinancialMgmtGLJournalList() {
        return (List<GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<Settlement> getFinancialMgmtSettlementList() {
        return (List<Settlement>) get(PROPERTY_FINANCIALMGMTSETTLEMENTLIST);
    }

    public void setFinancialMgmtSettlementList(List<Settlement> financialMgmtSettlementList) {
        set(PROPERTY_FINANCIALMGMTSETTLEMENTLIST, financialMgmtSettlementList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRegisterTypeLines> getFinancialMgmtTaxRegisterTypeLinesList() {
        return (List<TaxRegisterTypeLines>) get(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST);
    }

    public void setFinancialMgmtTaxRegisterTypeLinesList(List<TaxRegisterTypeLines> financialMgmtTaxRegisterTypeLinesList) {
        set(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST, financialMgmtTaxRegisterTypeLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceList() {
        return (List<Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceTransactionDocumentList() {
        return (List<Invoice>) get(PROPERTY_INVOICETRANSACTIONDOCUMENTLIST);
    }

    public void setInvoiceTransactionDocumentList(List<Invoice> invoiceTransactionDocumentList) {
        set(PROPERTY_INVOICETRANSACTIONDOCUMENTLIST, invoiceTransactionDocumentList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2List() {
        return (List<InvoiceV2>) get(PROPERTY_INVOICEV2LIST);
    }

    public void setInvoiceV2List(List<InvoiceV2> invoiceV2List) {
        set(PROPERTY_INVOICEV2LIST, invoiceV2List);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2TransactionDocumentList() {
        return (List<InvoiceV2>) get(PROPERTY_INVOICEV2TRANSACTIONDOCUMENTLIST);
    }

    public void setInvoiceV2TransactionDocumentList(List<InvoiceV2> invoiceV2TransactionDocumentList) {
        set(PROPERTY_INVOICEV2TRANSACTIONDOCUMENTLIST, invoiceV2TransactionDocumentList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkRequirement> getManufacturingWorkRequirementList() {
        return (List<WorkRequirement>) get(PROPERTY_MANUFACTURINGWORKREQUIREMENTLIST);
    }

    public void setManufacturingWorkRequirementList(List<WorkRequirement> manufacturingWorkRequirementList) {
        set(PROPERTY_MANUFACTURINGWORKREQUIREMENTLIST, manufacturingWorkRequirementList);
    }

    @SuppressWarnings("unchecked")
    public List<InternalMovement> getMaterialMgmtInternalMovementList() {
        return (List<InternalMovement>) get(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLIST);
    }

    public void setMaterialMgmtInternalMovementList(List<InternalMovement> materialMgmtInternalMovementList) {
        set(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLIST, materialMgmtInternalMovementList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
        return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderList() {
        return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderTransactionDocumentList() {
        return (List<Order>) get(PROPERTY_ORDERTRANSACTIONDOCUMENTLIST);
    }

    public void setOrderTransactionDocumentList(List<Order> orderTransactionDocumentList) {
        set(PROPERTY_ORDERTRANSACTIONDOCUMENTLIST, orderTransactionDocumentList);
    }

    @SuppressWarnings("unchecked")
    public List<Requisition> getProcurementRequisitionList() {
        return (List<Requisition>) get(PROPERTY_PROCUREMENTREQUISITIONLIST);
    }

    public void setProcurementRequisitionList(List<Requisition> procurementRequisitionList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLIST, procurementRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDepartmentIssue> getRCGIDepartmentIssueList() {
        return (List<RCGIDepartmentIssue>) get(PROPERTY_RCGIDEPARTMENTISSUELIST);
    }

    public void setRCGIDepartmentIssueList(List<RCGIDepartmentIssue> rCGIDepartmentIssueList) {
        set(PROPERTY_RCGIDEPARTMENTISSUELIST, rCGIDepartmentIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDepartmentReceipt> getRCGIDepartmentReceiptList() {
        return (List<RCGIDepartmentReceipt>) get(PROPERTY_RCGIDEPARTMENTRECEIPTLIST);
    }

    public void setRCGIDepartmentReceiptList(List<RCGIDepartmentReceipt> rCGIDepartmentReceiptList) {
        set(PROPERTY_RCGIDEPARTMENTRECEIPTLIST, rCGIDepartmentReceiptList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_GoodsIssue> getRCGIGoodsIssueList() {
        return (List<RCGI_GoodsIssue>) get(PROPERTY_RCGIGOODSISSUELIST);
    }

    public void setRCGIGoodsIssueList(List<RCGI_GoodsIssue> rCGIGoodsIssueList) {
        set(PROPERTY_RCGIGOODSISSUELIST, rCGIGoodsIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RcgiMaterialIndent> getRCGIMaterialIndentList() {
        return (List<RcgiMaterialIndent>) get(PROPERTY_RCGIMATERIALINDENTLIST);
    }

    public void setRCGIMaterialIndentList(List<RcgiMaterialIndent> rCGIMaterialIndentList) {
        set(PROPERTY_RCGIMATERIALINDENTLIST, rCGIMaterialIndentList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MaterialIssue> getRCGIMaterialIssueList() {
        return (List<RCGI_MaterialIssue>) get(PROPERTY_RCGIMATERIALISSUELIST);
    }

    public void setRCGIMaterialIssueList(List<RCGI_MaterialIssue> rCGIMaterialIssueList) {
        set(PROPERTY_RCGIMATERIALISSUELIST, rCGIMaterialIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MaterialReceipt> getRCGIMaterialReceiptList() {
        return (List<RCGI_MaterialReceipt>) get(PROPERTY_RCGIMATERIALRECEIPTLIST);
    }

    public void setRCGIMaterialReceiptList(List<RCGI_MaterialReceipt> rCGIMaterialReceiptList) {
        set(PROPERTY_RCGIMATERIALRECEIPTLIST, rCGIMaterialReceiptList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrApprovalHistrory> getRCHRApprovalHistroryList() {
        return (List<RchrApprovalHistrory>) get(PROPERTY_RCHRAPPROVALHISTRORYLIST);
    }

    public void setRCHRApprovalHistroryList(List<RchrApprovalHistrory> rCHRApprovalHistroryList) {
        set(PROPERTY_RCHRAPPROVALHISTRORYLIST, rCHRApprovalHistroryList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Directwarp> getRCHRDirectwarpList() {
        return (List<RCHR_Directwarp>) get(PROPERTY_RCHRDIRECTWARPLIST);
    }

    public void setRCHRDirectwarpList(List<RCHR_Directwarp> rCHRDirectwarpList) {
        set(PROPERTY_RCHRDIRECTWARPLIST, rCHRDirectwarpList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizingsheet> getRCHRSizingsheetList() {
        return (List<RCHR_Sizingsheet>) get(PROPERTY_RCHRSIZINGSHEETLIST);
    }

    public void setRCHRSizingsheetList(List<RCHR_Sizingsheet> rCHRSizingsheetList) {
        set(PROPERTY_RCHRSIZINGSHEETLIST, rCHRSizingsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<RCLO_LotIssue> getRCLOLotIssueList() {
        return (List<RCLO_LotIssue>) get(PROPERTY_RCLOLOTISSUELIST);
    }

    public void setRCLOLotIssueList(List<RCLO_LotIssue> rCLOLotIssueList) {
        set(PROPERTY_RCLOLOTISSUELIST, rCLOLotIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCLO_LotIssueLine> getRCLOLotIssueLineList() {
        return (List<RCLO_LotIssueLine>) get(PROPERTY_RCLOLOTISSUELINELIST);
    }

    public void setRCLOLotIssueLineList(List<RCLO_LotIssueLine> rCLOLotIssueLineList) {
        set(PROPERTY_RCLOLOTISSUELINELIST, rCLOLotIssueLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseQuotation> getRCOBPurchasequotationList() {
        return (List<PurchaseQuotation>) get(PROPERTY_RCOBPURCHASEQUOTATIONLIST);
    }

    public void setRCOBPurchasequotationList(List<PurchaseQuotation> rCOBPurchasequotationList) {
        set(PROPERTY_RCOBPURCHASEQUOTATIONLIST, rCOBPurchasequotationList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_PreventiveMaintenanceOrder> getRCPRPreventivemaintenanceList() {
        return (List<RCPR_PreventiveMaintenanceOrder>) get(PROPERTY_RCPRPREVENTIVEMAINTENANCELIST);
    }

    public void setRCPRPreventivemaintenanceList(List<RCPR_PreventiveMaintenanceOrder> rCPRPreventivemaintenanceList) {
        set(PROPERTY_RCPRPREVENTIVEMAINTENANCELIST, rCPRPreventivemaintenanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpayments> getRchrBanksalpaymentsList() {
        return (List<RchrBanksalpayments>) get(PROPERTY_RCHRBANKSALPAYMENTSLIST);
    }

    public void setRchrBanksalpaymentsList(List<RchrBanksalpayments> rchrBanksalpaymentsList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSLIST, rchrBanksalpaymentsList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpayments> getRchrExbanksalpaymentsList() {
        return (List<RchrExbanksalpayments>) get(PROPERTY_RCHREXBANKSALPAYMENTSLIST);
    }

    public void setRchrExbanksalpaymentsList(List<RchrExbanksalpayments> rchrExbanksalpaymentsList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSLIST, rchrExbanksalpaymentsList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrMemoShift> getRchrMemoShiftList() {
        return (List<RchrMemoShift>) get(PROPERTY_RCHRMEMOSHIFTLIST);
    }

    public void setRchrMemoShiftList(List<RchrMemoShift> rchrMemoShiftList) {
        set(PROPERTY_RCHRMEMOSHIFTLIST, rchrMemoShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplPayrollprocessmanual> getRcplPayrollprocessmanualList() {
        return (List<RcplPayrollprocessmanual>) get(PROPERTY_RCPLPAYROLLPROCESSMANUALLIST);
    }

    public void setRcplPayrollprocessmanualList(List<RcplPayrollprocessmanual> rcplPayrollprocessmanualList) {
        set(PROPERTY_RCPLPAYROLLPROCESSMANUALLIST, rcplPayrollprocessmanualList);
    }

}
