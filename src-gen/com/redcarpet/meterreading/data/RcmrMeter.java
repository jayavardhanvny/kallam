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
package com.redcarpet.meterreading.data;

import com.rcss.humanresource.data.RCHR_ElectricReading;
import com.rcss.humanresource.data.RCHR_Room;
import com.redcarpet.payroll.data.RCPLElectricityBill;

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
 * Entity class for entity Rcmr_Meter (stored in table Rcmr_Meter).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RcmrMeter extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rcmr_Meter";
    public static final String ENTITY_NAME = "Rcmr_Meter";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_METERNUMBER = "meterNumber";
    public static final String PROPERTY_RCHRELECTRICREADINGLIST = "rCHRElectricReadingList";
    public static final String PROPERTY_RCHRROOMLIST = "rCHRRoomList";
    public static final String PROPERTY_RCPLELECTRICITYBILLLIST = "rCPLElectricityBillList";
    public static final String PROPERTY_RCMRDOMESTICMETERLIST = "rcmrDomesticmeterList";
    public static final String PROPERTY_RCMRLTPANELLIST = "rcmrLtpanelList";
    public static final String PROPERTY_RCMRMAINMETERLIST = "rcmrMainmeterList";
    public static final String PROPERTY_RCMRPOWERDISTRIBUTIONLIST = "rcmrPowerdistributionList";

    public RcmrMeter() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCHRELECTRICREADINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROOMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLELECTRICITYBILLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCMRDOMESTICMETERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCMRLTPANELLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCMRMAINMETERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCMRPOWERDISTRIBUTIONLIST, new ArrayList<Object>());
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

    public String getMeterNumber() {
        return (String) get(PROPERTY_METERNUMBER);
    }

    public void setMeterNumber(String meterNumber) {
        set(PROPERTY_METERNUMBER, meterNumber);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_ElectricReading> getRCHRElectricReadingList() {
        return (List<RCHR_ElectricReading>) get(PROPERTY_RCHRELECTRICREADINGLIST);
    }

    public void setRCHRElectricReadingList(List<RCHR_ElectricReading> rCHRElectricReadingList) {
        set(PROPERTY_RCHRELECTRICREADINGLIST, rCHRElectricReadingList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Room> getRCHRRoomList() {
        return (List<RCHR_Room>) get(PROPERTY_RCHRROOMLIST);
    }

    public void setRCHRRoomList(List<RCHR_Room> rCHRRoomList) {
        set(PROPERTY_RCHRROOMLIST, rCHRRoomList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLElectricityBill> getRCPLElectricityBillList() {
        return (List<RCPLElectricityBill>) get(PROPERTY_RCPLELECTRICITYBILLLIST);
    }

    public void setRCPLElectricityBillList(List<RCPLElectricityBill> rCPLElectricityBillList) {
        set(PROPERTY_RCPLELECTRICITYBILLLIST, rCPLElectricityBillList);
    }

    @SuppressWarnings("unchecked")
    public List<RcmrDomesticmeter> getRcmrDomesticmeterList() {
        return (List<RcmrDomesticmeter>) get(PROPERTY_RCMRDOMESTICMETERLIST);
    }

    public void setRcmrDomesticmeterList(List<RcmrDomesticmeter> rcmrDomesticmeterList) {
        set(PROPERTY_RCMRDOMESTICMETERLIST, rcmrDomesticmeterList);
    }

    @SuppressWarnings("unchecked")
    public List<RcmrLtpanel> getRcmrLtpanelList() {
        return (List<RcmrLtpanel>) get(PROPERTY_RCMRLTPANELLIST);
    }

    public void setRcmrLtpanelList(List<RcmrLtpanel> rcmrLtpanelList) {
        set(PROPERTY_RCMRLTPANELLIST, rcmrLtpanelList);
    }

    @SuppressWarnings("unchecked")
    public List<RcmrMainmeter> getRcmrMainmeterList() {
        return (List<RcmrMainmeter>) get(PROPERTY_RCMRMAINMETERLIST);
    }

    public void setRcmrMainmeterList(List<RcmrMainmeter> rcmrMainmeterList) {
        set(PROPERTY_RCMRMAINMETERLIST, rcmrMainmeterList);
    }

    @SuppressWarnings("unchecked")
    public List<RcmrPowerdistribution> getRcmrPowerdistributionList() {
        return (List<RcmrPowerdistribution>) get(PROPERTY_RCMRPOWERDISTRIBUTIONLIST);
    }

    public void setRcmrPowerdistributionList(List<RcmrPowerdistribution> rcmrPowerdistributionList) {
        set(PROPERTY_RCMRPOWERDISTRIBUTIONLIST, rcmrPowerdistributionList);
    }

}
