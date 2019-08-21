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
import org.openbravo.model.common.bank.BankAccount;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FinancialMgmtPromissoryFormat (stored in table C_PromissoryFormat).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class PromissoryFormat extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_PromissoryFormat";
    public static final String ENTITY_NAME = "FinancialMgmtPromissoryFormat";
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
    public static final String PROPERTY_BANKACCOUNT = "bankAccount";
    public static final String PROPERTY_LINE1TOP = "line1Top";
    public static final String PROPERTY_LINE1LEFT = "line1Left";
    public static final String PROPERTY_LINE2TOP = "line2Top";
    public static final String PROPERTY_LINE2LEFT = "line2Left";
    public static final String PROPERTY_LINE3TOP = "line3Top";
    public static final String PROPERTY_LINE3LEFT = "line3Left";
    public static final String PROPERTY_LINE4TOP = "line4Top";
    public static final String PROPERTY_LINE4LEFT = "line4Left";
    public static final String PROPERTY_PARAM11 = "param11";
    public static final String PROPERTY_PARAM12 = "param12";
    public static final String PROPERTY_PARAM13 = "param13";
    public static final String PROPERTY_PARAM31 = "param31";
    public static final String PROPERTY_PARAM32 = "param32";
    public static final String PROPERTY_PARAM41 = "param41";
    public static final String PROPERTY_PARAM42 = "param42";
    public static final String PROPERTY_PARAM43 = "param43";
    public static final String PROPERTY_PRINTBANKLOCATION = "printBankLocation";
    public static final String PROPERTY_SOURCEPLANNEDDAY = "sourcePlannedDay";
    public static final String PROPERTY_SOURCEPLANNEDMONTH = "sourcePlannedMonth";
    public static final String PROPERTY_SOURCELASTYEAR = "sourceLastYear";
    public static final String PROPERTY_SOURCEAMOUNT = "sourceAmount";
    public static final String PROPERTY_SOURCETOBUSINESSPARTNER = "sourceToBusinessPartner";
    public static final String PROPERTY_SOURCEAMOUNTTEXT = "sourceAmountText";
    public static final String PROPERTY_SOURCEDAYTODAY = "sourceDayToday";
    public static final String PROPERTY_SOURCEMONTHTODAY = "sourceMonthToDay";
    public static final String PROPERTY_SOURCEYEARTODAY = "sourceYearToDay";
    public static final String PROPERTY_BANKLOCATIONFONT = "bankLocationFont";

    public PromissoryFormat() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_LINE1TOP, new BigDecimal(1));
        setDefaultValue(PROPERTY_LINE1LEFT, new BigDecimal(1));
        setDefaultValue(PROPERTY_LINE2TOP, new BigDecimal(1));
        setDefaultValue(PROPERTY_LINE2LEFT, new BigDecimal(1));
        setDefaultValue(PROPERTY_LINE3TOP, new BigDecimal(1));
        setDefaultValue(PROPERTY_LINE3LEFT, new BigDecimal(1));
        setDefaultValue(PROPERTY_LINE4TOP, new BigDecimal(1));
        setDefaultValue(PROPERTY_LINE4LEFT, new BigDecimal(1));
        setDefaultValue(PROPERTY_PARAM11, new BigDecimal(0));
        setDefaultValue(PROPERTY_PARAM12, new BigDecimal(0));
        setDefaultValue(PROPERTY_PARAM13, new BigDecimal(0));
        setDefaultValue(PROPERTY_PARAM31, new BigDecimal(0));
        setDefaultValue(PROPERTY_PARAM32, new BigDecimal(0));
        setDefaultValue(PROPERTY_PARAM41, new BigDecimal(0));
        setDefaultValue(PROPERTY_PARAM42, new BigDecimal(0));
        setDefaultValue(PROPERTY_PARAM43, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRINTBANKLOCATION, true);
        setDefaultValue(PROPERTY_SOURCEPLANNEDDAY, new BigDecimal(8));
        setDefaultValue(PROPERTY_SOURCEPLANNEDMONTH, new BigDecimal(8));
        setDefaultValue(PROPERTY_SOURCELASTYEAR, new BigDecimal(8));
        setDefaultValue(PROPERTY_SOURCEAMOUNT, new BigDecimal(8));
        setDefaultValue(PROPERTY_SOURCETOBUSINESSPARTNER, new BigDecimal(8));
        setDefaultValue(PROPERTY_SOURCEAMOUNTTEXT, new BigDecimal(8));
        setDefaultValue(PROPERTY_SOURCEDAYTODAY, new BigDecimal(8));
        setDefaultValue(PROPERTY_SOURCEMONTHTODAY, new BigDecimal(8));
        setDefaultValue(PROPERTY_SOURCEYEARTODAY, new BigDecimal(8));
        setDefaultValue(PROPERTY_BANKLOCATIONFONT, new BigDecimal(8));
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

    public BankAccount getBankAccount() {
        return (BankAccount) get(PROPERTY_BANKACCOUNT);
    }

    public void setBankAccount(BankAccount bankAccount) {
        set(PROPERTY_BANKACCOUNT, bankAccount);
    }

    public BigDecimal getLine1Top() {
        return (BigDecimal) get(PROPERTY_LINE1TOP);
    }

    public void setLine1Top(BigDecimal line1Top) {
        set(PROPERTY_LINE1TOP, line1Top);
    }

    public BigDecimal getLine1Left() {
        return (BigDecimal) get(PROPERTY_LINE1LEFT);
    }

    public void setLine1Left(BigDecimal line1Left) {
        set(PROPERTY_LINE1LEFT, line1Left);
    }

    public BigDecimal getLine2Top() {
        return (BigDecimal) get(PROPERTY_LINE2TOP);
    }

    public void setLine2Top(BigDecimal line2Top) {
        set(PROPERTY_LINE2TOP, line2Top);
    }

    public BigDecimal getLine2Left() {
        return (BigDecimal) get(PROPERTY_LINE2LEFT);
    }

    public void setLine2Left(BigDecimal line2Left) {
        set(PROPERTY_LINE2LEFT, line2Left);
    }

    public BigDecimal getLine3Top() {
        return (BigDecimal) get(PROPERTY_LINE3TOP);
    }

    public void setLine3Top(BigDecimal line3Top) {
        set(PROPERTY_LINE3TOP, line3Top);
    }

    public BigDecimal getLine3Left() {
        return (BigDecimal) get(PROPERTY_LINE3LEFT);
    }

    public void setLine3Left(BigDecimal line3Left) {
        set(PROPERTY_LINE3LEFT, line3Left);
    }

    public BigDecimal getLine4Top() {
        return (BigDecimal) get(PROPERTY_LINE4TOP);
    }

    public void setLine4Top(BigDecimal line4Top) {
        set(PROPERTY_LINE4TOP, line4Top);
    }

    public BigDecimal getLine4Left() {
        return (BigDecimal) get(PROPERTY_LINE4LEFT);
    }

    public void setLine4Left(BigDecimal line4Left) {
        set(PROPERTY_LINE4LEFT, line4Left);
    }

    public BigDecimal getParam11() {
        return (BigDecimal) get(PROPERTY_PARAM11);
    }

    public void setParam11(BigDecimal param11) {
        set(PROPERTY_PARAM11, param11);
    }

    public BigDecimal getParam12() {
        return (BigDecimal) get(PROPERTY_PARAM12);
    }

    public void setParam12(BigDecimal param12) {
        set(PROPERTY_PARAM12, param12);
    }

    public BigDecimal getParam13() {
        return (BigDecimal) get(PROPERTY_PARAM13);
    }

    public void setParam13(BigDecimal param13) {
        set(PROPERTY_PARAM13, param13);
    }

    public BigDecimal getParam31() {
        return (BigDecimal) get(PROPERTY_PARAM31);
    }

    public void setParam31(BigDecimal param31) {
        set(PROPERTY_PARAM31, param31);
    }

    public BigDecimal getParam32() {
        return (BigDecimal) get(PROPERTY_PARAM32);
    }

    public void setParam32(BigDecimal param32) {
        set(PROPERTY_PARAM32, param32);
    }

    public BigDecimal getParam41() {
        return (BigDecimal) get(PROPERTY_PARAM41);
    }

    public void setParam41(BigDecimal param41) {
        set(PROPERTY_PARAM41, param41);
    }

    public BigDecimal getParam42() {
        return (BigDecimal) get(PROPERTY_PARAM42);
    }

    public void setParam42(BigDecimal param42) {
        set(PROPERTY_PARAM42, param42);
    }

    public BigDecimal getParam43() {
        return (BigDecimal) get(PROPERTY_PARAM43);
    }

    public void setParam43(BigDecimal param43) {
        set(PROPERTY_PARAM43, param43);
    }

    public Boolean isPrintBankLocation() {
        return (Boolean) get(PROPERTY_PRINTBANKLOCATION);
    }

    public void setPrintBankLocation(Boolean printBankLocation) {
        set(PROPERTY_PRINTBANKLOCATION, printBankLocation);
    }

    public BigDecimal getSourcePlannedDay() {
        return (BigDecimal) get(PROPERTY_SOURCEPLANNEDDAY);
    }

    public void setSourcePlannedDay(BigDecimal sourcePlannedDay) {
        set(PROPERTY_SOURCEPLANNEDDAY, sourcePlannedDay);
    }

    public BigDecimal getSourcePlannedMonth() {
        return (BigDecimal) get(PROPERTY_SOURCEPLANNEDMONTH);
    }

    public void setSourcePlannedMonth(BigDecimal sourcePlannedMonth) {
        set(PROPERTY_SOURCEPLANNEDMONTH, sourcePlannedMonth);
    }

    public BigDecimal getSourceLastYear() {
        return (BigDecimal) get(PROPERTY_SOURCELASTYEAR);
    }

    public void setSourceLastYear(BigDecimal sourceLastYear) {
        set(PROPERTY_SOURCELASTYEAR, sourceLastYear);
    }

    public BigDecimal getSourceAmount() {
        return (BigDecimal) get(PROPERTY_SOURCEAMOUNT);
    }

    public void setSourceAmount(BigDecimal sourceAmount) {
        set(PROPERTY_SOURCEAMOUNT, sourceAmount);
    }

    public BigDecimal getSourceToBusinessPartner() {
        return (BigDecimal) get(PROPERTY_SOURCETOBUSINESSPARTNER);
    }

    public void setSourceToBusinessPartner(BigDecimal sourceToBusinessPartner) {
        set(PROPERTY_SOURCETOBUSINESSPARTNER, sourceToBusinessPartner);
    }

    public BigDecimal getSourceAmountText() {
        return (BigDecimal) get(PROPERTY_SOURCEAMOUNTTEXT);
    }

    public void setSourceAmountText(BigDecimal sourceAmountText) {
        set(PROPERTY_SOURCEAMOUNTTEXT, sourceAmountText);
    }

    public BigDecimal getSourceDayToday() {
        return (BigDecimal) get(PROPERTY_SOURCEDAYTODAY);
    }

    public void setSourceDayToday(BigDecimal sourceDayToday) {
        set(PROPERTY_SOURCEDAYTODAY, sourceDayToday);
    }

    public BigDecimal getSourceMonthToDay() {
        return (BigDecimal) get(PROPERTY_SOURCEMONTHTODAY);
    }

    public void setSourceMonthToDay(BigDecimal sourceMonthToDay) {
        set(PROPERTY_SOURCEMONTHTODAY, sourceMonthToDay);
    }

    public BigDecimal getSourceYearToDay() {
        return (BigDecimal) get(PROPERTY_SOURCEYEARTODAY);
    }

    public void setSourceYearToDay(BigDecimal sourceYearToDay) {
        set(PROPERTY_SOURCEYEARTODAY, sourceYearToDay);
    }

    public BigDecimal getBankLocationFont() {
        return (BigDecimal) get(PROPERTY_BANKLOCATIONFONT);
    }

    public void setBankLocationFont(BigDecimal bankLocationFont) {
        set(PROPERTY_BANKLOCATIONFONT, bankLocationFont);
    }

}
