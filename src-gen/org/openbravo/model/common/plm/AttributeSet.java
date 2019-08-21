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
package org.openbravo.model.common.plm;

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
/**
 * Entity class for entity AttributeSet (stored in table M_AttributeSet).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AttributeSet extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_AttributeSet";
    public static final String ENTITY_NAME = "AttributeSet";
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
    public static final String PROPERTY_SERIALNO = "serialNo";
    public static final String PROPERTY_SERIALNOCONTROL = "serialNoControl";
    public static final String PROPERTY_LOT = "lot";
    public static final String PROPERTY_LOTCONTROL = "lotControl";
    public static final String PROPERTY_EXPIRATIONDATE = "expirationDate";
    public static final String PROPERTY_GUARANTEEDDAYS = "guaranteedDays";
    public static final String PROPERTY_LOCKEDINWAREHOUSE = "lockedInWarehouse";
    public static final String PROPERTY_REQUIREATLEASTONEVALUE = "requireAtLeastOneValue";
    public static final String PROPERTY_ATTRIBUTESETINSTANCELIST = "attributeSetInstanceList";
    public static final String PROPERTY_ATTRIBUTEUSELIST = "attributeUseList";
    public static final String PROPERTY_PRODUCTLIST = "productList";

    public AttributeSet() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SERIALNO, false);
        setDefaultValue(PROPERTY_LOT, false);
        setDefaultValue(PROPERTY_EXPIRATIONDATE, false);
        setDefaultValue(PROPERTY_LOCKEDINWAREHOUSE, false);
        setDefaultValue(PROPERTY_REQUIREATLEASTONEVALUE, true);
        setDefaultValue(PROPERTY_ATTRIBUTESETINSTANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ATTRIBUTEUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTLIST, new ArrayList<Object>());
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

    public Boolean isSerialNo() {
        return (Boolean) get(PROPERTY_SERIALNO);
    }

    public void setSerialNo(Boolean serialNo) {
        set(PROPERTY_SERIALNO, serialNo);
    }

    public SerialNumberControl getSerialNoControl() {
        return (SerialNumberControl) get(PROPERTY_SERIALNOCONTROL);
    }

    public void setSerialNoControl(SerialNumberControl serialNoControl) {
        set(PROPERTY_SERIALNOCONTROL, serialNoControl);
    }

    public Boolean isLot() {
        return (Boolean) get(PROPERTY_LOT);
    }

    public void setLot(Boolean lot) {
        set(PROPERTY_LOT, lot);
    }

    public LotControl getLotControl() {
        return (LotControl) get(PROPERTY_LOTCONTROL);
    }

    public void setLotControl(LotControl lotControl) {
        set(PROPERTY_LOTCONTROL, lotControl);
    }

    public Boolean isExpirationDate() {
        return (Boolean) get(PROPERTY_EXPIRATIONDATE);
    }

    public void setExpirationDate(Boolean expirationDate) {
        set(PROPERTY_EXPIRATIONDATE, expirationDate);
    }

    public Long getGuaranteedDays() {
        return (Long) get(PROPERTY_GUARANTEEDDAYS);
    }

    public void setGuaranteedDays(Long guaranteedDays) {
        set(PROPERTY_GUARANTEEDDAYS, guaranteedDays);
    }

    public Boolean isLockedInWarehouse() {
        return (Boolean) get(PROPERTY_LOCKEDINWAREHOUSE);
    }

    public void setLockedInWarehouse(Boolean lockedInWarehouse) {
        set(PROPERTY_LOCKEDINWAREHOUSE, lockedInWarehouse);
    }

    public Boolean isRequireAtLeastOneValue() {
        return (Boolean) get(PROPERTY_REQUIREATLEASTONEVALUE);
    }

    public void setRequireAtLeastOneValue(Boolean requireAtLeastOneValue) {
        set(PROPERTY_REQUIREATLEASTONEVALUE, requireAtLeastOneValue);
    }

    @SuppressWarnings("unchecked")
    public List<AttributeSetInstance> getAttributeSetInstanceList() {
        return (List<AttributeSetInstance>) get(PROPERTY_ATTRIBUTESETINSTANCELIST);
    }

    public void setAttributeSetInstanceList(List<AttributeSetInstance> attributeSetInstanceList) {
        set(PROPERTY_ATTRIBUTESETINSTANCELIST, attributeSetInstanceList);
    }

    @SuppressWarnings("unchecked")
    public List<AttributeUse> getAttributeUseList() {
        return (List<AttributeUse>) get(PROPERTY_ATTRIBUTEUSELIST);
    }

    public void setAttributeUseList(List<AttributeUse> attributeUseList) {
        set(PROPERTY_ATTRIBUTEUSELIST, attributeUseList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProductList() {
        return (List<Product>) get(PROPERTY_PRODUCTLIST);
    }

    public void setProductList(List<Product> productList) {
        set(PROPERTY_PRODUCTLIST, productList);
    }

}
