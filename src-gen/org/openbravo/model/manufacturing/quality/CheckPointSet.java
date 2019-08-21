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
package org.openbravo.model.manufacturing.quality;

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
/**
 * Entity class for entity ManufacturingCheckPointSet (stored in table MA_CCP_Group).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CheckPointSet extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_CCP_Group";
    public static final String ENTITY_NAME = "ManufacturingCheckPointSet";
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
    public static final String PROPERTY_FREQUENCY = "frequency";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_MANUFACTURINGCHECKPOINTLIST = "manufacturingCheckPointList";
    public static final String PROPERTY_MANUFACTURINGCHECKPOINTSHIFTLIST = "manufacturingCheckPointShiftList";
    public static final String PROPERTY_MANUFACTURINGMEASUREGROUPLIST = "manufacturingMeasureGroupList";

    public CheckPointSet() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MANUFACTURINGCHECKPOINTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGCHECKPOINTSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMEASUREGROUPLIST, new ArrayList<Object>());
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

    public Timestamp getFrequency() {
        return (Timestamp) get(PROPERTY_FREQUENCY);
    }

    public void setFrequency(Timestamp frequency) {
        set(PROPERTY_FREQUENCY, frequency);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    @SuppressWarnings("unchecked")
    public List<CheckPoint> getManufacturingCheckPointList() {
        return (List<CheckPoint>) get(PROPERTY_MANUFACTURINGCHECKPOINTLIST);
    }

    public void setManufacturingCheckPointList(List<CheckPoint> manufacturingCheckPointList) {
        set(PROPERTY_MANUFACTURINGCHECKPOINTLIST, manufacturingCheckPointList);
    }

    @SuppressWarnings("unchecked")
    public List<CheckPointShift> getManufacturingCheckPointShiftList() {
        return (List<CheckPointShift>) get(PROPERTY_MANUFACTURINGCHECKPOINTSHIFTLIST);
    }

    public void setManufacturingCheckPointShiftList(List<CheckPointShift> manufacturingCheckPointShiftList) {
        set(PROPERTY_MANUFACTURINGCHECKPOINTSHIFTLIST, manufacturingCheckPointShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<MeasureGroup> getManufacturingMeasureGroupList() {
        return (List<MeasureGroup>) get(PROPERTY_MANUFACTURINGMEASUREGROUPLIST);
    }

    public void setManufacturingMeasureGroupList(List<MeasureGroup> manufacturingMeasureGroupList) {
        set(PROPERTY_MANUFACTURINGMEASUREGROUPLIST, manufacturingMeasureGroupList);
    }

}
