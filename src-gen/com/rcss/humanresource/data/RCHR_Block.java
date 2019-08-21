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

import com.redcarpet.meterreading.data.RcmrDomesticmeter;

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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.Location;
/**
 * Entity class for entity RCHR_Block (stored in table RCHR_Block).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Block extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Block";
    public static final String ENTITY_NAME = "RCHR_Block";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BLOCKNO = "blockNo";
    public static final String PROPERTY_BLOCKNAME = "blockName";
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_RENTAMOUNT = "rentamount";
    public static final String PROPERTY_FRSTATTDPRCNT = "frstattdprcnt";
    public static final String PROPERTY_SCNDATTDPRCNT = "scndattdprcnt";
    public static final String PROPERTY_WELFAREFUND = "welfarefund";
    public static final String PROPERTY_RCHRBLOCKLINESLIST = "rCHRBlockLinesList";
    public static final String PROPERTY_RCHRROOMLIST = "rCHRRoomList";
    public static final String PROPERTY_RCMRDOMESTICMETERLIST = "rcmrDomesticmeterList";

    public RCHR_Block() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_WELFAREFUND, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCHRBLOCKLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROOMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCMRDOMESTICMETERLIST, new ArrayList<Object>());
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

    public String getBlockNo() {
        return (String) get(PROPERTY_BLOCKNO);
    }

    public void setBlockNo(String blockNo) {
        set(PROPERTY_BLOCKNO, blockNo);
    }

    public String getBlockName() {
        return (String) get(PROPERTY_BLOCKNAME);
    }

    public void setBlockName(String blockName) {
        set(PROPERTY_BLOCKNAME, blockName);
    }

    public Location getLocationAddress() {
        return (Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public BigDecimal getRentamount() {
        return (BigDecimal) get(PROPERTY_RENTAMOUNT);
    }

    public void setRentamount(BigDecimal rentamount) {
        set(PROPERTY_RENTAMOUNT, rentamount);
    }

    public BigDecimal getFrstattdprcnt() {
        return (BigDecimal) get(PROPERTY_FRSTATTDPRCNT);
    }

    public void setFrstattdprcnt(BigDecimal frstattdprcnt) {
        set(PROPERTY_FRSTATTDPRCNT, frstattdprcnt);
    }

    public BigDecimal getScndattdprcnt() {
        return (BigDecimal) get(PROPERTY_SCNDATTDPRCNT);
    }

    public void setScndattdprcnt(BigDecimal scndattdprcnt) {
        set(PROPERTY_SCNDATTDPRCNT, scndattdprcnt);
    }

    public BigDecimal getWelfarefund() {
        return (BigDecimal) get(PROPERTY_WELFAREFUND);
    }

    public void setWelfarefund(BigDecimal welfarefund) {
        set(PROPERTY_WELFAREFUND, welfarefund);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_BlockLines> getRCHRBlockLinesList() {
        return (List<RCHR_BlockLines>) get(PROPERTY_RCHRBLOCKLINESLIST);
    }

    public void setRCHRBlockLinesList(List<RCHR_BlockLines> rCHRBlockLinesList) {
        set(PROPERTY_RCHRBLOCKLINESLIST, rCHRBlockLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Room> getRCHRRoomList() {
        return (List<RCHR_Room>) get(PROPERTY_RCHRROOMLIST);
    }

    public void setRCHRRoomList(List<RCHR_Room> rCHRRoomList) {
        set(PROPERTY_RCHRROOMLIST, rCHRRoomList);
    }

    @SuppressWarnings("unchecked")
    public List<RcmrDomesticmeter> getRcmrDomesticmeterList() {
        return (List<RcmrDomesticmeter>) get(PROPERTY_RCMRDOMESTICMETERLIST);
    }

    public void setRcmrDomesticmeterList(List<RcmrDomesticmeter> rcmrDomesticmeterList) {
        set(PROPERTY_RCMRDOMESTICMETERLIST, rcmrDomesticmeterList);
    }

}
