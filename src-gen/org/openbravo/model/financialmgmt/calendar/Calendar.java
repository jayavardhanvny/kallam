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
package org.openbravo.model.financialmgmt.calendar;

import com.rcss.humanresource.data.RchrYear;

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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FinancialMgmtCalendar (stored in table C_Calendar).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Calendar extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Calendar";
    public static final String ENTITY_NAME = "FinancialMgmtCalendar";
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
    public static final String PROPERTY_CLIENTINFORMATIONLIST = "clientInformationList";
    public static final String PROPERTY_FINANCIALMGMTNONBUSINESSDAYLIST = "financialMgmtNonBusinessDayList";
    public static final String PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST = "financialMgmtPeriodControlVList";
    public static final String PROPERTY_FINANCIALMGMTYEARLIST = "financialMgmtYearList";
    public static final String PROPERTY_ORGANIZATIONLIST = "organizationList";
    public static final String PROPERTY_PERIODCONTROLLOGLIST = "periodControlLogList";
    public static final String PROPERTY_RCHRYEARLIST = "rchrYearList";
    public static final String PROPERTY_YEARCLOSEVLIST = "yearCloseVList";

    public Calendar() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CLIENTINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTNONBUSINESSDAYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTYEARLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PERIODCONTROLLOGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRYEARLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_YEARCLOSEVLIST, new ArrayList<Object>());
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

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONLIST);
    }

    public void setClientInformationList(List<ClientInformation> clientInformationList) {
        set(PROPERTY_CLIENTINFORMATIONLIST, clientInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<NonBusinessDay> getFinancialMgmtNonBusinessDayList() {
        return (List<NonBusinessDay>) get(PROPERTY_FINANCIALMGMTNONBUSINESSDAYLIST);
    }

    public void setFinancialMgmtNonBusinessDayList(List<NonBusinessDay> financialMgmtNonBusinessDayList) {
        set(PROPERTY_FINANCIALMGMTNONBUSINESSDAYLIST, financialMgmtNonBusinessDayList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlV> getFinancialMgmtPeriodControlVList() {
        return (List<PeriodControlV>) get(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST);
    }

    public void setFinancialMgmtPeriodControlVList(List<PeriodControlV> financialMgmtPeriodControlVList) {
        set(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST, financialMgmtPeriodControlVList);
    }

    @SuppressWarnings("unchecked")
    public List<Year> getFinancialMgmtYearList() {
        return (List<Year>) get(PROPERTY_FINANCIALMGMTYEARLIST);
    }

    public void setFinancialMgmtYearList(List<Year> financialMgmtYearList) {
        set(PROPERTY_FINANCIALMGMTYEARLIST, financialMgmtYearList);
    }

    @SuppressWarnings("unchecked")
    public List<Organization> getOrganizationList() {
        return (List<Organization>) get(PROPERTY_ORGANIZATIONLIST);
    }

    public void setOrganizationList(List<Organization> organizationList) {
        set(PROPERTY_ORGANIZATIONLIST, organizationList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlLog> getPeriodControlLogList() {
        return (List<PeriodControlLog>) get(PROPERTY_PERIODCONTROLLOGLIST);
    }

    public void setPeriodControlLogList(List<PeriodControlLog> periodControlLogList) {
        set(PROPERTY_PERIODCONTROLLOGLIST, periodControlLogList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrYear> getRchrYearList() {
        return (List<RchrYear>) get(PROPERTY_RCHRYEARLIST);
    }

    public void setRchrYearList(List<RchrYear> rchrYearList) {
        set(PROPERTY_RCHRYEARLIST, rchrYearList);
    }

    @SuppressWarnings("unchecked")
    public List<YearClose> getYearCloseVList() {
        return (List<YearClose>) get(PROPERTY_YEARCLOSEVLIST);
    }

    public void setYearCloseVList(List<YearClose> yearCloseVList) {
        set(PROPERTY_YEARCLOSEVLIST, yearCloseVList);
    }

}
