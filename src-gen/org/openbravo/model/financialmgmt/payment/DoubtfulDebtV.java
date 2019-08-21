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
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Category;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FIN_Doubtful_Debt_V (stored in table FIN_Doubtful_Debt_V).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class DoubtfulDebtV extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Doubtful_Debt_V";
    public static final String ENTITY_NAME = "FIN_Doubtful_Debt_V";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_FINPAYMENTSCHEDULE = "finPaymentSchedule";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentno";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORY = "businessPartnerCategory";
    public static final String PROPERTY_DATEINVOICED = "dateinvoiced";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_DUEDATE = "duedate";
    public static final String PROPERTY_RUNDATE = "rundate";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_OUTSTANDINGAMT = "outstandingamt";
    public static final String PROPERTY_DOUBTFULDEBTAMOUNT = "doubtfulDebtAmount";
    public static final String PROPERTY_DAYSOVERDUE = "daysOverdue";
    public static final String PROPERTY_FINDOUBTFULDEBTRUN = "fINDoubtfulDebtRun";
    public static final String PROPERTY_FINDOUBTFULDEBT = "fINDoubtfulDebt";
    public static final String PROPERTY_OBSELECTED = "obSelected";

    public DoubtfulDebtV() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OBSELECTED, false);
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

    public FIN_PaymentSchedule getFinPaymentSchedule() {
        return (FIN_PaymentSchedule) get(PROPERTY_FINPAYMENTSCHEDULE);
    }

    public void setFinPaymentSchedule(FIN_PaymentSchedule finPaymentSchedule) {
        set(PROPERTY_FINPAYMENTSCHEDULE, finPaymentSchedule);
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

    public String getDocumentno() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentno(String documentno) {
        set(PROPERTY_DOCUMENTNO, documentno);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Category getBusinessPartnerCategory() {
        return (Category) get(PROPERTY_BUSINESSPARTNERCATEGORY);
    }

    public void setBusinessPartnerCategory(Category businessPartnerCategory) {
        set(PROPERTY_BUSINESSPARTNERCATEGORY, businessPartnerCategory);
    }

    public Date getDateinvoiced() {
        return (Date) get(PROPERTY_DATEINVOICED);
    }

    public void setDateinvoiced(Date dateinvoiced) {
        set(PROPERTY_DATEINVOICED, dateinvoiced);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Date getDuedate() {
        return (Date) get(PROPERTY_DUEDATE);
    }

    public void setDuedate(Date duedate) {
        set(PROPERTY_DUEDATE, duedate);
    }

    public Date getRundate() {
        return (Date) get(PROPERTY_RUNDATE);
    }

    public void setRundate(Date rundate) {
        set(PROPERTY_RUNDATE, rundate);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public BigDecimal getOutstandingamt() {
        return (BigDecimal) get(PROPERTY_OUTSTANDINGAMT);
    }

    public void setOutstandingamt(BigDecimal outstandingamt) {
        set(PROPERTY_OUTSTANDINGAMT, outstandingamt);
    }

    public BigDecimal getDoubtfulDebtAmount() {
        return (BigDecimal) get(PROPERTY_DOUBTFULDEBTAMOUNT);
    }

    public void setDoubtfulDebtAmount(BigDecimal doubtfulDebtAmount) {
        set(PROPERTY_DOUBTFULDEBTAMOUNT, doubtfulDebtAmount);
    }

    public Long getDaysOverdue() {
        return (Long) get(PROPERTY_DAYSOVERDUE);
    }

    public void setDaysOverdue(Long daysOverdue) {
        set(PROPERTY_DAYSOVERDUE, daysOverdue);
    }

    public DoubtfulDebtRun getFINDoubtfulDebtRun() {
        return (DoubtfulDebtRun) get(PROPERTY_FINDOUBTFULDEBTRUN);
    }

    public void setFINDoubtfulDebtRun(DoubtfulDebtRun fINDoubtfulDebtRun) {
        set(PROPERTY_FINDOUBTFULDEBTRUN, fINDoubtfulDebtRun);
    }

    public DoubtfulDebt getFINDoubtfulDebt() {
        return (DoubtfulDebt) get(PROPERTY_FINDOUBTFULDEBT);
    }

    public void setFINDoubtfulDebt(DoubtfulDebt fINDoubtfulDebt) {
        set(PROPERTY_FINDOUBTFULDEBT, fINDoubtfulDebt);
    }

    public Boolean isObSelected() {
        return (Boolean) get(PROPERTY_OBSELECTED);
    }

    public void setObSelected(Boolean obSelected) {
        set(PROPERTY_OBSELECTED, obSelected);
    }

}
