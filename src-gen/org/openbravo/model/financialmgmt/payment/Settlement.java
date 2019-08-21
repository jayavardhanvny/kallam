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

import java.math.BigDecimal;
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
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.tax.TaxPayment;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.project.Project;
/**
 * Entity class for entity FinancialMgmtSettlement (stored in table C_Settlement).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Settlement extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Settlement";
    public static final String ENTITY_NAME = "FinancialMgmtSettlement";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_TRANSACTIONDATE = "transactionDate";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_CREATELINESFROM = "createLinesFrom";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_CANCELEDNOTCHARGED = "canceledNotCharged";
    public static final String PROPERTY_CREATEDAMOUNT = "createdAmount";
    public static final String PROPERTY_CHARGEDAMOUNT = "chargedAmount";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CREATEFILE = "createFile";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_SETTLEMENTTYPE = "settlementType";
    public static final String PROPERTY_OPENSETTLEMENT = "openSettlement";
    public static final String PROPERTY_COPYFROM = "copyFrom";
    public static final String PROPERTY_GENERATED = "generated";
    public static final String PROPERTY_TEMPLATE = "template";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTSETTLEMENTCANCELLEDLIST = "financialMgmtDebtPaymentSettlementCancelledList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCSETTLEMENTGENERATEIDLIST = "financialMgmtDebtPaymentCSettlementGenerateIDList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST = "financialMgmtDebtPaymentCancelVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVSETTLEMENTGENERATELIST = "financialMgmtDebtPaymentCancelVSettlementGenerateList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST = "financialMgmtDebtPaymentGenerateVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVSETTLEMENTCANCELLEDLIST = "financialMgmtDebtPaymentGenerateVSettlementCancelledList";
    public static final String PROPERTY_FINANCIALMGMTREMITTANCELIST = "financialMgmtRemittanceList";
    public static final String PROPERTY_FINANCIALMGMTTAXPAYMENTLIST = "financialMgmtTaxPaymentList";

    public Settlement() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, "N");
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_CREATELINESFROM, false);
        setDefaultValue(PROPERTY_CANCELEDNOTCHARGED, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREATEDAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_CHARGEDAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREATEFILE, false);
        setDefaultValue(PROPERTY_OPENSETTLEMENT, false);
        setDefaultValue(PROPERTY_COPYFROM, false);
        setDefaultValue(PROPERTY_GENERATED, false);
        setDefaultValue(PROPERTY_TEMPLATE, false);
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTSETTLEMENTCANCELLEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCSETTLEMENTGENERATEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVSETTLEMENTGENERATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVSETTLEMENTCANCELLEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTREMITTANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, new ArrayList<Object>());
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Date getTransactionDate() {
        return (Date) get(PROPERTY_TRANSACTIONDATE);
    }

    public void setTransactionDate(Date transactionDate) {
        set(PROPERTY_TRANSACTIONDATE, transactionDate);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public String getProcessed() {
        return (String) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(String processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Campaign getSalesCampaign() {
        return (Campaign) get(PROPERTY_SALESCAMPAIGN);
    }

    public void setSalesCampaign(Campaign salesCampaign) {
        set(PROPERTY_SALESCAMPAIGN, salesCampaign);
    }

    public ABCActivity getActivity() {
        return (ABCActivity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(ABCActivity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public UserDimension1 getStDimension() {
        return (UserDimension1) get(PROPERTY_STDIMENSION);
    }

    public void setStDimension(UserDimension1 stDimension) {
        set(PROPERTY_STDIMENSION, stDimension);
    }

    public UserDimension2 getNdDimension() {
        return (UserDimension2) get(PROPERTY_NDDIMENSION);
    }

    public void setNdDimension(UserDimension2 ndDimension) {
        set(PROPERTY_NDDIMENSION, ndDimension);
    }

    public Boolean isCreateLinesFrom() {
        return (Boolean) get(PROPERTY_CREATELINESFROM);
    }

    public void setCreateLinesFrom(Boolean createLinesFrom) {
        set(PROPERTY_CREATELINESFROM, createLinesFrom);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BigDecimal getCanceledNotCharged() {
        return (BigDecimal) get(PROPERTY_CANCELEDNOTCHARGED);
    }

    public void setCanceledNotCharged(BigDecimal canceledNotCharged) {
        set(PROPERTY_CANCELEDNOTCHARGED, canceledNotCharged);
    }

    public BigDecimal getCreatedAmount() {
        return (BigDecimal) get(PROPERTY_CREATEDAMOUNT);
    }

    public void setCreatedAmount(BigDecimal createdAmount) {
        set(PROPERTY_CREATEDAMOUNT, createdAmount);
    }

    public BigDecimal getChargedAmount() {
        return (BigDecimal) get(PROPERTY_CHARGEDAMOUNT);
    }

    public void setChargedAmount(BigDecimal chargedAmount) {
        set(PROPERTY_CHARGEDAMOUNT, chargedAmount);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isCreateFile() {
        return (Boolean) get(PROPERTY_CREATEFILE);
    }

    public void setCreateFile(Boolean createFile) {
        set(PROPERTY_CREATEFILE, createFile);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public String getSettlementType() {
        return (String) get(PROPERTY_SETTLEMENTTYPE);
    }

    public void setSettlementType(String settlementType) {
        set(PROPERTY_SETTLEMENTTYPE, settlementType);
    }

    public Boolean isOpenSettlement() {
        return (Boolean) get(PROPERTY_OPENSETTLEMENT);
    }

    public void setOpenSettlement(Boolean openSettlement) {
        set(PROPERTY_OPENSETTLEMENT, openSettlement);
    }

    public Boolean isCopyFrom() {
        return (Boolean) get(PROPERTY_COPYFROM);
    }

    public void setCopyFrom(Boolean copyFrom) {
        set(PROPERTY_COPYFROM, copyFrom);
    }

    public Boolean isGenerated() {
        return (Boolean) get(PROPERTY_GENERATED);
    }

    public void setGenerated(Boolean generated) {
        set(PROPERTY_GENERATED, generated);
    }

    public Boolean isTemplate() {
        return (Boolean) get(PROPERTY_TEMPLATE);
    }

    public void setTemplate(Boolean template) {
        set(PROPERTY_TEMPLATE, template);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPayment> getFinancialMgmtDebtPaymentSettlementCancelledList() {
        return (List<DebtPayment>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTSETTLEMENTCANCELLEDLIST);
    }

    public void setFinancialMgmtDebtPaymentSettlementCancelledList(List<DebtPayment> financialMgmtDebtPaymentSettlementCancelledList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTSETTLEMENTCANCELLEDLIST, financialMgmtDebtPaymentSettlementCancelledList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPayment> getFinancialMgmtDebtPaymentCSettlementGenerateIDList() {
        return (List<DebtPayment>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTCSETTLEMENTGENERATEIDLIST);
    }

    public void setFinancialMgmtDebtPaymentCSettlementGenerateIDList(List<DebtPayment> financialMgmtDebtPaymentCSettlementGenerateIDList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTCSETTLEMENTGENERATEIDLIST, financialMgmtDebtPaymentCSettlementGenerateIDList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentCancelV> getFinancialMgmtDebtPaymentCancelVList() {
        return (List<DebtPaymentCancelV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST);
    }

    public void setFinancialMgmtDebtPaymentCancelVList(List<DebtPaymentCancelV> financialMgmtDebtPaymentCancelVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, financialMgmtDebtPaymentCancelVList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentCancelV> getFinancialMgmtDebtPaymentCancelVSettlementGenerateList() {
        return (List<DebtPaymentCancelV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVSETTLEMENTGENERATELIST);
    }

    public void setFinancialMgmtDebtPaymentCancelVSettlementGenerateList(List<DebtPaymentCancelV> financialMgmtDebtPaymentCancelVSettlementGenerateList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVSETTLEMENTGENERATELIST, financialMgmtDebtPaymentCancelVSettlementGenerateList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentGenerateV> getFinancialMgmtDebtPaymentGenerateVList() {
        return (List<DebtPaymentGenerateV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST);
    }

    public void setFinancialMgmtDebtPaymentGenerateVList(List<DebtPaymentGenerateV> financialMgmtDebtPaymentGenerateVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, financialMgmtDebtPaymentGenerateVList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentGenerateV> getFinancialMgmtDebtPaymentGenerateVSettlementCancelledList() {
        return (List<DebtPaymentGenerateV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVSETTLEMENTCANCELLEDLIST);
    }

    public void setFinancialMgmtDebtPaymentGenerateVSettlementCancelledList(List<DebtPaymentGenerateV> financialMgmtDebtPaymentGenerateVSettlementCancelledList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVSETTLEMENTCANCELLEDLIST, financialMgmtDebtPaymentGenerateVSettlementCancelledList);
    }

    @SuppressWarnings("unchecked")
    public List<Remittance> getFinancialMgmtRemittanceList() {
        return (List<Remittance>) get(PROPERTY_FINANCIALMGMTREMITTANCELIST);
    }

    public void setFinancialMgmtRemittanceList(List<Remittance> financialMgmtRemittanceList) {
        set(PROPERTY_FINANCIALMGMTREMITTANCELIST, financialMgmtRemittanceList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxPayment> getFinancialMgmtTaxPaymentList() {
        return (List<TaxPayment>) get(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST);
    }

    public void setFinancialMgmtTaxPaymentList(List<TaxPayment> financialMgmtTaxPaymentList) {
        set(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, financialMgmtTaxPaymentList);
    }

}
