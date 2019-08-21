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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_FinaccTransactionV;
import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.APRM_Reconciliation_v;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.financialmgmt.accounting.FIN_FinancialAccountAccounting;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
/**
 * Entity class for entity FIN_Financial_Account (stored in table FIN_Financial_Account).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_FinancialAccount extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Financial_Account";
    public static final String ENTITY_NAME = "FIN_Financial_Account";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_ROUTINGNO = "routingNo";
    public static final String PROPERTY_SWIFTCODE = "swiftCode";
    public static final String PROPERTY_BANKCODE = "bankCode";
    public static final String PROPERTY_BRANCHCODE = "branchCode";
    public static final String PROPERTY_BANKDIGITCONTROL = "bankDigitcontrol";
    public static final String PROPERTY_INENO = "iNENo";
    public static final String PROPERTY_ACCOUNTDIGITCONTROL = "accountDigitcontrol";
    public static final String PROPERTY_PARTIALACCOUNTNO = "partialAccountNo";
    public static final String PROPERTY_ACCOUNTNO = "accountNo";
    public static final String PROPERTY_CURRENTBALANCE = "currentBalance";
    public static final String PROPERTY_INITIALBALANCE = "initialBalance";
    public static final String PROPERTY_CREDITLIMIT = "creditLimit";
    public static final String PROPERTY_IBAN = "iBAN";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_MATCHINGALGORITHM = "matchingAlgorithm";
    public static final String PROPERTY_APRMIMPORTBANKFILE = "aPRMImportBankFile";
    public static final String PROPERTY_GENERICACCOUNTNO = "genericAccountNo";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_APRMMATCHTRANSACTIONS = "aPRMMatchTransactions";
    public static final String PROPERTY_BANKFORMAT = "bankFormat";
    public static final String PROPERTY_APRMRECONCILE = "aPRMReconcile";
    public static final String PROPERTY_APRMADDTRANSACTIONS = "aPRMAddTransactions";
    public static final String PROPERTY_TYPEWRITEOFF = "typewriteoff";
    public static final String PROPERTY_WRITEOFFLIMIT = "writeofflimit";
    public static final String PROPERTY_LASTRECONBALANCE = "lastreconbalance";
    public static final String PROPERTY_LASTRECONCILIATION = "lastreconciliation";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONVLIST = "aPRMFinaccTransactionVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_APRMRECONCILIATIONLIST = "aPRMReconciliationList";
    public static final String PROPERTY_BUSINESSPARTNERLIST = "businessPartnerList";
    public static final String PROPERTY_BUSINESSPARTNERPOFINANCIALACCOUNTLIST = "businessPartnerPOFinancialAccountList";
    public static final String PROPERTY_EPCGCHEQUEBOOKLIST = "ePCGChequebookList";
    public static final String PROPERTY_FINBANKSTATEMENTLIST = "fINBankStatementList";
    public static final String PROPERTY_FINFINACCTRANSACTIONLIST = "fINFinaccTransactionList";
    public static final String PROPERTY_FINFINANCIALACCOUNTACCTLIST = "fINFinancialAccountAcctList";
    public static final String PROPERTY_FINPAYMENTLIST = "fINPaymentList";
    public static final String PROPERTY_FINPAYMENTDETAILVLIST = "fINPaymentDetailVList";
    public static final String PROPERTY_FINPAYMENTPROPOSALLIST = "fINPaymentProposalList";
    public static final String PROPERTY_FINRECONCILIATIONLIST = "fINReconciliationList";
    public static final String PROPERTY_FINRECONCILIATIONLINEVLIST = "fINReconciliationLineVList";
    public static final String PROPERTY_FINANCIALMGMTBANKFILEEXCEPTIONLIST = "financialMgmtBankFileExceptionList";
    public static final String PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODLIST = "financialMgmtFinAccPaymentMethodList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";

    public FIN_FinancialAccount() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TYPE, "B");
        setDefaultValue(PROPERTY_CURRENTBALANCE, new BigDecimal(0));
        setDefaultValue(PROPERTY_INITIALBALANCE, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREDITLIMIT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_APRMIMPORTBANKFILE, false);
        setDefaultValue(PROPERTY_APRMMATCHTRANSACTIONS, false);
        setDefaultValue(PROPERTY_APRMRECONCILE, false);
        setDefaultValue(PROPERTY_APRMADDTRANSACTIONS, false);
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMRECONCILIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERPOFINANCIALACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCHEQUEBOOKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINBANKSTATEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINACCTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINRECONCILIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINRECONCILIATIONLINEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBANKFILEEXCEPTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTFINACCPAYMENTMETHODLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
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

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
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

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Location getLocationAddress() {
        return (Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public String getRoutingNo() {
        return (String) get(PROPERTY_ROUTINGNO);
    }

    public void setRoutingNo(String routingNo) {
        set(PROPERTY_ROUTINGNO, routingNo);
    }

    public String getSwiftCode() {
        return (String) get(PROPERTY_SWIFTCODE);
    }

    public void setSwiftCode(String swiftCode) {
        set(PROPERTY_SWIFTCODE, swiftCode);
    }

    public String getBankCode() {
        return (String) get(PROPERTY_BANKCODE);
    }

    public void setBankCode(String bankCode) {
        set(PROPERTY_BANKCODE, bankCode);
    }

    public String getBranchCode() {
        return (String) get(PROPERTY_BRANCHCODE);
    }

    public void setBranchCode(String branchCode) {
        set(PROPERTY_BRANCHCODE, branchCode);
    }

    public String getBankDigitcontrol() {
        return (String) get(PROPERTY_BANKDIGITCONTROL);
    }

    public void setBankDigitcontrol(String bankDigitcontrol) {
        set(PROPERTY_BANKDIGITCONTROL, bankDigitcontrol);
    }

    public String getINENo() {
        return (String) get(PROPERTY_INENO);
    }

    public void setINENo(String iNENo) {
        set(PROPERTY_INENO, iNENo);
    }

    public String getAccountDigitcontrol() {
        return (String) get(PROPERTY_ACCOUNTDIGITCONTROL);
    }

    public void setAccountDigitcontrol(String accountDigitcontrol) {
        set(PROPERTY_ACCOUNTDIGITCONTROL, accountDigitcontrol);
    }

    public String getPartialAccountNo() {
        return (String) get(PROPERTY_PARTIALACCOUNTNO);
    }

    public void setPartialAccountNo(String partialAccountNo) {
        set(PROPERTY_PARTIALACCOUNTNO, partialAccountNo);
    }

    public String getAccountNo() {
        return (String) get(PROPERTY_ACCOUNTNO);
    }

    public void setAccountNo(String accountNo) {
        set(PROPERTY_ACCOUNTNO, accountNo);
    }

    public BigDecimal getCurrentBalance() {
        return (BigDecimal) get(PROPERTY_CURRENTBALANCE);
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        set(PROPERTY_CURRENTBALANCE, currentBalance);
    }

    public BigDecimal getInitialBalance() {
        return (BigDecimal) get(PROPERTY_INITIALBALANCE);
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        set(PROPERTY_INITIALBALANCE, initialBalance);
    }

    public BigDecimal getCreditLimit() {
        return (BigDecimal) get(PROPERTY_CREDITLIMIT);
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        set(PROPERTY_CREDITLIMIT, creditLimit);
    }

    public String getIBAN() {
        return (String) get(PROPERTY_IBAN);
    }

    public void setIBAN(String iBAN) {
        set(PROPERTY_IBAN, iBAN);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public MatchingAlgorithm getMatchingAlgorithm() {
        return (MatchingAlgorithm) get(PROPERTY_MATCHINGALGORITHM);
    }

    public void setMatchingAlgorithm(MatchingAlgorithm matchingAlgorithm) {
        set(PROPERTY_MATCHINGALGORITHM, matchingAlgorithm);
    }

    public Boolean isAPRMImportBankFile() {
        return (Boolean) get(PROPERTY_APRMIMPORTBANKFILE);
    }

    public void setAPRMImportBankFile(Boolean aPRMImportBankFile) {
        set(PROPERTY_APRMIMPORTBANKFILE, aPRMImportBankFile);
    }

    public String getGenericAccountNo() {
        return (String) get(PROPERTY_GENERICACCOUNTNO);
    }

    public void setGenericAccountNo(String genericAccountNo) {
        set(PROPERTY_GENERICACCOUNTNO, genericAccountNo);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public Boolean isAPRMMatchTransactions() {
        return (Boolean) get(PROPERTY_APRMMATCHTRANSACTIONS);
    }

    public void setAPRMMatchTransactions(Boolean aPRMMatchTransactions) {
        set(PROPERTY_APRMMATCHTRANSACTIONS, aPRMMatchTransactions);
    }

    public String getBankFormat() {
        return (String) get(PROPERTY_BANKFORMAT);
    }

    public void setBankFormat(String bankFormat) {
        set(PROPERTY_BANKFORMAT, bankFormat);
    }

    public Boolean isAPRMReconcile() {
        return (Boolean) get(PROPERTY_APRMRECONCILE);
    }

    public void setAPRMReconcile(Boolean aPRMReconcile) {
        set(PROPERTY_APRMRECONCILE, aPRMReconcile);
    }

    public Boolean isAPRMAddTransactions() {
        return (Boolean) get(PROPERTY_APRMADDTRANSACTIONS);
    }

    public void setAPRMAddTransactions(Boolean aPRMAddTransactions) {
        set(PROPERTY_APRMADDTRANSACTIONS, aPRMAddTransactions);
    }

    public String getTypewriteoff() {
        return (String) get(PROPERTY_TYPEWRITEOFF);
    }

    public void setTypewriteoff(String typewriteoff) {
        set(PROPERTY_TYPEWRITEOFF, typewriteoff);
    }

    public BigDecimal getWriteofflimit() {
        return (BigDecimal) get(PROPERTY_WRITEOFFLIMIT);
    }

    public void setWriteofflimit(BigDecimal writeofflimit) {
        set(PROPERTY_WRITEOFFLIMIT, writeofflimit);
    }

    public BigDecimal getLastreconbalance() {
        return (BigDecimal) get(PROPERTY_LASTRECONBALANCE);
    }

    public void setLastreconbalance(BigDecimal lastreconbalance) {
        set(PROPERTY_LASTRECONBALANCE, lastreconbalance);
    }

    public Date getLastreconciliation() {
        return (Date) get(PROPERTY_LASTRECONCILIATION);
    }

    public void setLastreconciliation(Date lastreconciliation) {
        set(PROPERTY_LASTRECONCILIATION, lastreconciliation);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_FinaccTransactionV> getAPRMFinaccTransactionVList() {
        return (List<APRM_FinaccTransactionV>) get(PROPERTY_APRMFINACCTRANSACTIONVLIST);
    }

    public void setAPRMFinaccTransactionVList(List<APRM_FinaccTransactionV> aPRMFinaccTransactionVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONVLIST, aPRMFinaccTransactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
        return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Reconciliation_v> getAPRMReconciliationList() {
        return (List<APRM_Reconciliation_v>) get(PROPERTY_APRMRECONCILIATIONLIST);
    }

    public void setAPRMReconciliationList(List<APRM_Reconciliation_v> aPRMReconciliationList) {
        set(PROPERTY_APRMRECONCILIATIONLIST, aPRMReconciliationList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERLIST);
    }

    public void setBusinessPartnerList(List<BusinessPartner> businessPartnerList) {
        set(PROPERTY_BUSINESSPARTNERLIST, businessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerPOFinancialAccountList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERPOFINANCIALACCOUNTLIST);
    }

    public void setBusinessPartnerPOFinancialAccountList(List<BusinessPartner> businessPartnerPOFinancialAccountList) {
        set(PROPERTY_BUSINESSPARTNERPOFINANCIALACCOUNTLIST, businessPartnerPOFinancialAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<EPCGChequebook> getEPCGChequebookList() {
        return (List<EPCGChequebook>) get(PROPERTY_EPCGCHEQUEBOOKLIST);
    }

    public void setEPCGChequebookList(List<EPCGChequebook> ePCGChequebookList) {
        set(PROPERTY_EPCGCHEQUEBOOKLIST, ePCGChequebookList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_BankStatement> getFINBankStatementList() {
        return (List<FIN_BankStatement>) get(PROPERTY_FINBANKSTATEMENTLIST);
    }

    public void setFINBankStatementList(List<FIN_BankStatement> fINBankStatementList) {
        set(PROPERTY_FINBANKSTATEMENTLIST, fINBankStatementList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinaccTransaction> getFINFinaccTransactionList() {
        return (List<FIN_FinaccTransaction>) get(PROPERTY_FINFINACCTRANSACTIONLIST);
    }

    public void setFINFinaccTransactionList(List<FIN_FinaccTransaction> fINFinaccTransactionList) {
        set(PROPERTY_FINFINACCTRANSACTIONLIST, fINFinaccTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccountAccounting> getFINFinancialAccountAcctList() {
        return (List<FIN_FinancialAccountAccounting>) get(PROPERTY_FINFINANCIALACCOUNTACCTLIST);
    }

    public void setFINFinancialAccountAcctList(List<FIN_FinancialAccountAccounting> fINFinancialAccountAcctList) {
        set(PROPERTY_FINFINANCIALACCOUNTACCTLIST, fINFinancialAccountAcctList);
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
    public List<FIN_Reconciliation> getFINReconciliationList() {
        return (List<FIN_Reconciliation>) get(PROPERTY_FINRECONCILIATIONLIST);
    }

    public void setFINReconciliationList(List<FIN_Reconciliation> fINReconciliationList) {
        set(PROPERTY_FINRECONCILIATIONLIST, fINReconciliationList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_ReconciliationLine_v> getFINReconciliationLineVList() {
        return (List<FIN_ReconciliationLine_v>) get(PROPERTY_FINRECONCILIATIONLINEVLIST);
    }

    public void setFINReconciliationLineVList(List<FIN_ReconciliationLine_v> fINReconciliationLineVList) {
        set(PROPERTY_FINRECONCILIATIONLINEVLIST, fINReconciliationLineVList);
    }

    @SuppressWarnings("unchecked")
    public List<BankFileException> getFinancialMgmtBankFileExceptionList() {
        return (List<BankFileException>) get(PROPERTY_FINANCIALMGMTBANKFILEEXCEPTIONLIST);
    }

    public void setFinancialMgmtBankFileExceptionList(List<BankFileException> financialMgmtBankFileExceptionList) {
        set(PROPERTY_FINANCIALMGMTBANKFILEEXCEPTIONLIST, financialMgmtBankFileExceptionList);
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

}
