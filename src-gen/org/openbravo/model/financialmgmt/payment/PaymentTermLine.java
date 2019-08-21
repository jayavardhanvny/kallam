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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FinancialMgmtPaymentTermLine (stored in table C_Paymenttermline).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PaymentTermLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Paymenttermline";
    public static final String ENTITY_NAME = "FinancialMgmtPaymentTermLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_PERCENTAGEDUE = "percentageDue";
    public static final String PROPERTY_REST = "rest";
    public static final String PROPERTY_EXCLUDETAX = "excludeTax";
    public static final String PROPERTY_FORMOFPAYMENT = "formOfPayment";
    public static final String PROPERTY_FIXEDDUEDATE = "fixedDueDate";
    public static final String PROPERTY_OVERDUEPAYMENTDAYSRULE = "overduePaymentDaysRule";
    public static final String PROPERTY_LASTDAYCUTOFF = "lastDayCutoff";
    public static final String PROPERTY_MATURITYDATE1 = "maturityDate1";
    public static final String PROPERTY_MATURITYDATE2 = "maturityDate2";
    public static final String PROPERTY_MATURITYDATE3 = "maturityDate3";
    public static final String PROPERTY_OFFSETMONTHDUE = "offsetMonthDue";
    public static final String PROPERTY_NEXTBUSINESSDAY = "nextBusinessDay";
    public static final String PROPERTY_OVERDUEPAYMENTDAYRULE = "overduePaymentDayRule";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";

    public PaymentTermLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PERCENTAGEDUE, new BigDecimal(100));
        setDefaultValue(PROPERTY_REST, true);
        setDefaultValue(PROPERTY_EXCLUDETAX, false);
        setDefaultValue(PROPERTY_FIXEDDUEDATE, false);
        setDefaultValue(PROPERTY_NEXTBUSINESSDAY, true);
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

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public BigDecimal getPercentageDue() {
        return (BigDecimal) get(PROPERTY_PERCENTAGEDUE);
    }

    public void setPercentageDue(BigDecimal percentageDue) {
        set(PROPERTY_PERCENTAGEDUE, percentageDue);
    }

    public Boolean isRest() {
        return (Boolean) get(PROPERTY_REST);
    }

    public void setRest(Boolean rest) {
        set(PROPERTY_REST, rest);
    }

    public Boolean isExcludeTax() {
        return (Boolean) get(PROPERTY_EXCLUDETAX);
    }

    public void setExcludeTax(Boolean excludeTax) {
        set(PROPERTY_EXCLUDETAX, excludeTax);
    }

    public String getFormOfPayment() {
        return (String) get(PROPERTY_FORMOFPAYMENT);
    }

    public void setFormOfPayment(String formOfPayment) {
        set(PROPERTY_FORMOFPAYMENT, formOfPayment);
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

    public Long getLastDayCutoff() {
        return (Long) get(PROPERTY_LASTDAYCUTOFF);
    }

    public void setLastDayCutoff(Long lastDayCutoff) {
        set(PROPERTY_LASTDAYCUTOFF, lastDayCutoff);
    }

    public Long getMaturityDate1() {
        return (Long) get(PROPERTY_MATURITYDATE1);
    }

    public void setMaturityDate1(Long maturityDate1) {
        set(PROPERTY_MATURITYDATE1, maturityDate1);
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

    public String getOverduePaymentDayRule() {
        return (String) get(PROPERTY_OVERDUEPAYMENTDAYRULE);
    }

    public void setOverduePaymentDayRule(String overduePaymentDayRule) {
        set(PROPERTY_OVERDUEPAYMENTDAYRULE, overduePaymentDayRule);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

}
