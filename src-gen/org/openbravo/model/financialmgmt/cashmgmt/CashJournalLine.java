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
package org.openbravo.model.financialmgmt.cashmgmt;

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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.financialmgmt.gl.GLCharge;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.financialmgmt.payment.DebtPayment;
import org.openbravo.model.financialmgmt.payment.DebtPaymentCancelV;
import org.openbravo.model.financialmgmt.payment.DebtPaymentGenerateV;
import org.openbravo.model.financialmgmt.payment.DebtPaymentV;
/**
 * Entity class for entity FinancialMgmtJournalLine (stored in table C_CashLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CashJournalLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_CashLine";
    public static final String ENTITY_NAME = "FinancialMgmtJournalLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CASHJOURNAL = "cashJournal";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CASHTYPE = "cashType";
    public static final String PROPERTY_CHARGE = "charge";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_DISCOUNTAMOUNT = "discountAmount";
    public static final String PROPERTY_WRITEOFFAMOUNT = "writeoffAmount";
    public static final String PROPERTY_GENERATED = "generated";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_GLITEM = "gLItem";
    public static final String PROPERTY_CREATEAPAYMENTINADVANCE = "createAPaymentInAdvance";
    public static final String PROPERTY_DEBTPAYMENTVLIST = "debtPaymentVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST = "financialMgmtDebtPaymentList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST = "financialMgmtDebtPaymentCancelVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST = "financialMgmtDebtPaymentGenerateVList";

    public CashJournalLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CASHTYPE, "E");
        setDefaultValue(PROPERTY_GENERATED, false);
        setDefaultValue(PROPERTY_CREATEAPAYMENTINADVANCE, false);
        setDefaultValue(PROPERTY_DEBTPAYMENTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, new ArrayList<Object>());
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

    public CashJournal getCashJournal() {
        return (CashJournal) get(PROPERTY_CASHJOURNAL);
    }

    public void setCashJournal(CashJournal cashJournal) {
        set(PROPERTY_CASHJOURNAL, cashJournal);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getCashType() {
        return (String) get(PROPERTY_CASHTYPE);
    }

    public void setCashType(String cashType) {
        set(PROPERTY_CASHTYPE, cashType);
    }

    public GLCharge getCharge() {
        return (GLCharge) get(PROPERTY_CHARGE);
    }

    public void setCharge(GLCharge charge) {
        set(PROPERTY_CHARGE, charge);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public BigDecimal getDiscountAmount() {
        return (BigDecimal) get(PROPERTY_DISCOUNTAMOUNT);
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        set(PROPERTY_DISCOUNTAMOUNT, discountAmount);
    }

    public BigDecimal getWriteoffAmount() {
        return (BigDecimal) get(PROPERTY_WRITEOFFAMOUNT);
    }

    public void setWriteoffAmount(BigDecimal writeoffAmount) {
        set(PROPERTY_WRITEOFFAMOUNT, writeoffAmount);
    }

    public Boolean isGenerated() {
        return (Boolean) get(PROPERTY_GENERATED);
    }

    public void setGenerated(Boolean generated) {
        set(PROPERTY_GENERATED, generated);
    }

    public Order getSalesOrder() {
        return (Order) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Order salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public DebtPayment getPayment() {
        return (DebtPayment) get(PROPERTY_PAYMENT);
    }

    public void setPayment(DebtPayment payment) {
        set(PROPERTY_PAYMENT, payment);
    }

    public GLItem getGLItem() {
        return (GLItem) get(PROPERTY_GLITEM);
    }

    public void setGLItem(GLItem gLItem) {
        set(PROPERTY_GLITEM, gLItem);
    }

    public Boolean isCreateAPaymentInAdvance() {
        return (Boolean) get(PROPERTY_CREATEAPAYMENTINADVANCE);
    }

    public void setCreateAPaymentInAdvance(Boolean createAPaymentInAdvance) {
        set(PROPERTY_CREATEAPAYMENTINADVANCE, createAPaymentInAdvance);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentV> getDebtPaymentVList() {
        return (List<DebtPaymentV>) get(PROPERTY_DEBTPAYMENTVLIST);
    }

    public void setDebtPaymentVList(List<DebtPaymentV> debtPaymentVList) {
        set(PROPERTY_DEBTPAYMENTVLIST, debtPaymentVList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPayment> getFinancialMgmtDebtPaymentList() {
        return (List<DebtPayment>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST);
    }

    public void setFinancialMgmtDebtPaymentList(List<DebtPayment> financialMgmtDebtPaymentList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, financialMgmtDebtPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentCancelV> getFinancialMgmtDebtPaymentCancelVList() {
        return (List<DebtPaymentCancelV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST);
    }

    public void setFinancialMgmtDebtPaymentCancelVList(List<DebtPaymentCancelV> financialMgmtDebtPaymentCancelVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, financialMgmtDebtPaymentCancelVList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentGenerateV> getFinancialMgmtDebtPaymentGenerateVList() {
        return (List<DebtPaymentGenerateV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST);
    }

    public void setFinancialMgmtDebtPaymentGenerateVList(List<DebtPaymentGenerateV> financialMgmtDebtPaymentGenerateVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, financialMgmtDebtPaymentGenerateVList);
    }

}
