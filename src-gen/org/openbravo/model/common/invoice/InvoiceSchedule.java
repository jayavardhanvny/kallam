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
package org.openbravo.model.common.invoice;

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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity InvoiceSchedule (stored in table C_InvoiceSchedule).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class InvoiceSchedule extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_InvoiceSchedule";
    public static final String ENTITY_NAME = "InvoiceSchedule";
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
    public static final String PROPERTY_LIMITAMOUNT = "limitAmount";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_INVOICEFREQUENCY = "invoiceFrequency";
    public static final String PROPERTY_DAYOFTHEWEEK = "dayOfTheWeek";
    public static final String PROPERTY_DAYOFTHEWEEKCUTOFF = "dayOfTheWeekCutoff";
    public static final String PROPERTY_INVOICEONEVENWEEKS = "invoiceOnEvenWeeks";
    public static final String PROPERTY_DAYOFTHEMONTH = "dayOfTheMonth";
    public static final String PROPERTY_INVOICECUTOFFDAY = "invoiceCutOffDay";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_BUSINESSPARTNERLIST = "businessPartnerList";

    public InvoiceSchedule() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_LIMITAMOUNT, false);
        setDefaultValue(PROPERTY_INVOICEONEVENWEEKS, false);
        setDefaultValue(PROPERTY_DAYOFTHEMONTH, (long) 1);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNERLIST, new ArrayList<Object>());
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

    public Boolean isLimitAmount() {
        return (Boolean) get(PROPERTY_LIMITAMOUNT);
    }

    public void setLimitAmount(Boolean limitAmount) {
        set(PROPERTY_LIMITAMOUNT, limitAmount);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public String getInvoiceFrequency() {
        return (String) get(PROPERTY_INVOICEFREQUENCY);
    }

    public void setInvoiceFrequency(String invoiceFrequency) {
        set(PROPERTY_INVOICEFREQUENCY, invoiceFrequency);
    }

    public String getDayOfTheWeek() {
        return (String) get(PROPERTY_DAYOFTHEWEEK);
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        set(PROPERTY_DAYOFTHEWEEK, dayOfTheWeek);
    }

    public String getDayOfTheWeekCutoff() {
        return (String) get(PROPERTY_DAYOFTHEWEEKCUTOFF);
    }

    public void setDayOfTheWeekCutoff(String dayOfTheWeekCutoff) {
        set(PROPERTY_DAYOFTHEWEEKCUTOFF, dayOfTheWeekCutoff);
    }

    public Boolean isInvoiceOnEvenWeeks() {
        return (Boolean) get(PROPERTY_INVOICEONEVENWEEKS);
    }

    public void setInvoiceOnEvenWeeks(Boolean invoiceOnEvenWeeks) {
        set(PROPERTY_INVOICEONEVENWEEKS, invoiceOnEvenWeeks);
    }

    public Long getDayOfTheMonth() {
        return (Long) get(PROPERTY_DAYOFTHEMONTH);
    }

    public void setDayOfTheMonth(Long dayOfTheMonth) {
        set(PROPERTY_DAYOFTHEMONTH, dayOfTheMonth);
    }

    public Long getInvoiceCutOffDay() {
        return (Long) get(PROPERTY_INVOICECUTOFFDAY);
    }

    public void setInvoiceCutOffDay(Long invoiceCutOffDay) {
        set(PROPERTY_INVOICECUTOFFDAY, invoiceCutOffDay);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerList() {
        return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERLIST);
    }

    public void setBusinessPartnerList(List<BusinessPartner> businessPartnerList) {
        set(PROPERTY_BUSINESSPARTNERLIST, businessPartnerList);
    }

}
