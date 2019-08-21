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
package org.openbravo.model.timeandexpense;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import org.openbravo.model.common.plm.ProductCategory;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.tax.TaxCategory;
/**
 * Entity class for entity ResourceType (stored in table S_ResourceType).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ResourceType extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "S_ResourceType";
    public static final String ENTITY_NAME = "ResourceType";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SINGLEASSIGNMENTONLY = "singleAssignmentOnly";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_ALLOWUOMFRACTIONS = "allowUoMFractions";
    public static final String PROPERTY_SLOTSTART = "slotStart";
    public static final String PROPERTY_SLOTEND = "slotEnd";
    public static final String PROPERTY_TIMESLOT = "timeSlot";
    public static final String PROPERTY_DAYSLOT = "daySlot";
    public static final String PROPERTY_SUNDAY = "sunday";
    public static final String PROPERTY_MONDAY = "monday";
    public static final String PROPERTY_TUESDAY = "tuesday";
    public static final String PROPERTY_WEDNESDAY = "wednesday";
    public static final String PROPERTY_THURSDAY = "thursday";
    public static final String PROPERTY_FRIDAY = "friday";
    public static final String PROPERTY_SATURDAY = "saturday";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_TAXCATEGORY = "taxCategory";
    public static final String PROPERTY_CHARGEABLEQUANTITY = "chargeableQuantity";
    public static final String PROPERTY_RESOURCELIST = "resourceList";

    public ResourceType() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SINGLEASSIGNMENTONLY, false);
        setDefaultValue(PROPERTY_ALLOWUOMFRACTIONS, false);
        setDefaultValue(PROPERTY_TIMESLOT, false);
        setDefaultValue(PROPERTY_DAYSLOT, false);
        setDefaultValue(PROPERTY_SUNDAY, false);
        setDefaultValue(PROPERTY_MONDAY, true);
        setDefaultValue(PROPERTY_TUESDAY, true);
        setDefaultValue(PROPERTY_WEDNESDAY, true);
        setDefaultValue(PROPERTY_THURSDAY, true);
        setDefaultValue(PROPERTY_FRIDAY, true);
        setDefaultValue(PROPERTY_SATURDAY, false);
        setDefaultValue(PROPERTY_RESOURCELIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
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

    public Boolean isSingleAssignmentOnly() {
        return (Boolean) get(PROPERTY_SINGLEASSIGNMENTONLY);
    }

    public void setSingleAssignmentOnly(Boolean singleAssignmentOnly) {
        set(PROPERTY_SINGLEASSIGNMENTONLY, singleAssignmentOnly);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public Boolean isAllowUoMFractions() {
        return (Boolean) get(PROPERTY_ALLOWUOMFRACTIONS);
    }

    public void setAllowUoMFractions(Boolean allowUoMFractions) {
        set(PROPERTY_ALLOWUOMFRACTIONS, allowUoMFractions);
    }

    public Timestamp getSlotStart() {
        return (Timestamp) get(PROPERTY_SLOTSTART);
    }

    public void setSlotStart(Timestamp slotStart) {
        set(PROPERTY_SLOTSTART, slotStart);
    }

    public Timestamp getSlotEnd() {
        return (Timestamp) get(PROPERTY_SLOTEND);
    }

    public void setSlotEnd(Timestamp slotEnd) {
        set(PROPERTY_SLOTEND, slotEnd);
    }

    public Boolean isTimeSlot() {
        return (Boolean) get(PROPERTY_TIMESLOT);
    }

    public void setTimeSlot(Boolean timeSlot) {
        set(PROPERTY_TIMESLOT, timeSlot);
    }

    public Boolean isDaySlot() {
        return (Boolean) get(PROPERTY_DAYSLOT);
    }

    public void setDaySlot(Boolean daySlot) {
        set(PROPERTY_DAYSLOT, daySlot);
    }

    public Boolean isSunday() {
        return (Boolean) get(PROPERTY_SUNDAY);
    }

    public void setSunday(Boolean sunday) {
        set(PROPERTY_SUNDAY, sunday);
    }

    public Boolean isMonday() {
        return (Boolean) get(PROPERTY_MONDAY);
    }

    public void setMonday(Boolean monday) {
        set(PROPERTY_MONDAY, monday);
    }

    public Boolean isTuesday() {
        return (Boolean) get(PROPERTY_TUESDAY);
    }

    public void setTuesday(Boolean tuesday) {
        set(PROPERTY_TUESDAY, tuesday);
    }

    public Boolean isWednesday() {
        return (Boolean) get(PROPERTY_WEDNESDAY);
    }

    public void setWednesday(Boolean wednesday) {
        set(PROPERTY_WEDNESDAY, wednesday);
    }

    public Boolean isThursday() {
        return (Boolean) get(PROPERTY_THURSDAY);
    }

    public void setThursday(Boolean thursday) {
        set(PROPERTY_THURSDAY, thursday);
    }

    public Boolean isFriday() {
        return (Boolean) get(PROPERTY_FRIDAY);
    }

    public void setFriday(Boolean friday) {
        set(PROPERTY_FRIDAY, friday);
    }

    public Boolean isSaturday() {
        return (Boolean) get(PROPERTY_SATURDAY);
    }

    public void setSaturday(Boolean saturday) {
        set(PROPERTY_SATURDAY, saturday);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public TaxCategory getTaxCategory() {
        return (TaxCategory) get(PROPERTY_TAXCATEGORY);
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        set(PROPERTY_TAXCATEGORY, taxCategory);
    }

    public BigDecimal getChargeableQuantity() {
        return (BigDecimal) get(PROPERTY_CHARGEABLEQUANTITY);
    }

    public void setChargeableQuantity(BigDecimal chargeableQuantity) {
        set(PROPERTY_CHARGEABLEQUANTITY, chargeableQuantity);
    }

    @SuppressWarnings("unchecked")
    public List<Resource> getResourceList() {
        return (List<Resource>) get(PROPERTY_RESOURCELIST);
    }

    public void setResourceList(List<Resource> resourceList) {
        set(PROPERTY_RESOURCELIST, resourceList);
    }

}
