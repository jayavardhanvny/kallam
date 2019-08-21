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
package com.rcss.humanresource.data;

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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.calendar.Calendar;
import org.openbravo.model.financialmgmt.calendar.Year;
/**
 * Entity class for entity Rchr_Year (stored in table Rchr_Year).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrYear extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Year";
    public static final String ENTITY_NAME = "Rchr_Year";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_CALENDAR = "calendar";
    public static final String PROPERTY_YEAR = "year";
    public static final String PROPERTY_CREATEPERIODS = "createperiods";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_ENCASHLEAVEFROM = "encashleavefrom";
    public static final String PROPERTY_ENCASHLEAVETO = "encashleaveto";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_RCHRATTDPROCESSLIST = "rchrAttdProcessList";
    public static final String PROPERTY_RCHRHOLIDAYSLISTLIST = "rchrHolidaysListList";

    public RchrYear() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATEPERIODS, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_RCHRATTDPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRHOLIDAYSLISTLIST, new ArrayList<Object>());
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

    public Calendar getCalendar() {
        return (Calendar) get(PROPERTY_CALENDAR);
    }

    public void setCalendar(Calendar calendar) {
        set(PROPERTY_CALENDAR, calendar);
    }

    public Year getYear() {
        return (Year) get(PROPERTY_YEAR);
    }

    public void setYear(Year year) {
        set(PROPERTY_YEAR, year);
    }

    public Boolean isCreateperiods() {
        return (Boolean) get(PROPERTY_CREATEPERIODS);
    }

    public void setCreateperiods(Boolean createperiods) {
        set(PROPERTY_CREATEPERIODS, createperiods);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Date getEncashleavefrom() {
        return (Date) get(PROPERTY_ENCASHLEAVEFROM);
    }

    public void setEncashleavefrom(Date encashleavefrom) {
        set(PROPERTY_ENCASHLEAVEFROM, encashleavefrom);
    }

    public Date getEncashleaveto() {
        return (Date) get(PROPERTY_ENCASHLEAVETO);
    }

    public void setEncashleaveto(Date encashleaveto) {
        set(PROPERTY_ENCASHLEAVETO, encashleaveto);
    }

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttdProcess> getRchrAttdProcessList() {
        return (List<RchrAttdProcess>) get(PROPERTY_RCHRATTDPROCESSLIST);
    }

    public void setRchrAttdProcessList(List<RchrAttdProcess> rchrAttdProcessList) {
        set(PROPERTY_RCHRATTDPROCESSLIST, rchrAttdProcessList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrHolidaysList> getRchrHolidaysListList() {
        return (List<RchrHolidaysList>) get(PROPERTY_RCHRHOLIDAYSLISTLIST);
    }

    public void setRchrHolidaysListList(List<RchrHolidaysList> rchrHolidaysListList) {
        set(PROPERTY_RCHRHOLIDAYSLISTLIST, rchrHolidaysListList);
    }

}
