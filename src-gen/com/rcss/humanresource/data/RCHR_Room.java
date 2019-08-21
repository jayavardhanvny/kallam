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
import com.redcarpet.meterreading.data.RcmrMeter;
import com.redcarpet.payroll.data.RCPLElectricityBill;
import com.redcarpet.payroll.data.RCPLRentBill;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RcplDeprtmtstorerequsition;

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
/**
 * Entity class for entity RCHR_Room (stored in table RCHR_Room).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Room extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Room";
    public static final String ENTITY_NAME = "RCHR_Room";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ROOMNO = "roomNo";
    public static final String PROPERTY_BLOCK = "block";
    public static final String PROPERTY_BLOCKLINE = "blockLine";
    public static final String PROPERTY_ELECTRICALSUBSIDYUNITS = "electricalSubsidyUnits";
    public static final String PROPERTY_VACANT = "vacant";
    public static final String PROPERTY_HASDISH = "hasDish";
    public static final String PROPERTY_METERNUMBER = "meterNumber";
    public static final String PROPERTY_ISBACHELORQUARTER = "isbachelorquarter";
    public static final String PROPERTY_ISRENTED = "isrented";
    public static final String PROPERTY_NOOFEMPLOYEES = "noofemployees";
    public static final String PROPERTY_MAXNOOFEMPLOYEES = "maxnoofemployees";
    public static final String PROPERTY_NOOEMPLOYEES = "nooemployees";
    public static final String PROPERTY_RCHRALLOTMENTLIST = "rCHRAllotmentList";
    public static final String PROPERTY_RCHRDEALLOTMENTLIST = "rCHRDeAllotmentList";
    public static final String PROPERTY_RCHRELECTRICREADINGLIST = "rCHRElectricReadingList";
    public static final String PROPERTY_RCHRROOMRENTLINESLIST = "rCHRRoomRentLinesList";
    public static final String PROPERTY_RCHRROOMAPPLIANCELIST = "rCHRRoomApplianceList";
    public static final String PROPERTY_RCPLDEPRTMTSTOREREQUSITIONLIST = "rCPLDeprtmtstorerequsitionList";
    public static final String PROPERTY_RCPLELECTRICITYBILLLIST = "rCPLElectricityBillList";
    public static final String PROPERTY_RCPLEMPPAYROLLPROCESSLIST = "rCPLEmpPayrollProcessList";
    public static final String PROPERTY_RCPLRENTBILLLIST = "rCPLRentBillList";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";
    public static final String PROPERTY_RCHRROOMEMPLOYEELIST = "rchrRoomEmployeeList";
    public static final String PROPERTY_RCMRDOMESTICMETERLIST = "rcmrDomesticmeterList";

    public RCHR_Room() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ELECTRICALSUBSIDYUNITS, new BigDecimal(0));
        setDefaultValue(PROPERTY_VACANT, true);
        setDefaultValue(PROPERTY_HASDISH, false);
        setDefaultValue(PROPERTY_ISBACHELORQUARTER, false);
        setDefaultValue(PROPERTY_ISRENTED, false);
        setDefaultValue(PROPERTY_NOOFEMPLOYEES, new BigDecimal(0));
        setDefaultValue(PROPERTY_MAXNOOFEMPLOYEES, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCHRALLOTMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDEALLOTMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRELECTRICREADINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROOMRENTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROOMAPPLIANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLDEPRTMTSTOREREQUSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLELECTRICITYBILLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPAYROLLPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLRENTBILLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROOMEMPLOYEELIST, new ArrayList<Object>());
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

    public String getRoomNo() {
        return (String) get(PROPERTY_ROOMNO);
    }

    public void setRoomNo(String roomNo) {
        set(PROPERTY_ROOMNO, roomNo);
    }

    public RCHR_Block getBlock() {
        return (RCHR_Block) get(PROPERTY_BLOCK);
    }

    public void setBlock(RCHR_Block block) {
        set(PROPERTY_BLOCK, block);
    }

    public RCHR_BlockLines getBlockLine() {
        return (RCHR_BlockLines) get(PROPERTY_BLOCKLINE);
    }

    public void setBlockLine(RCHR_BlockLines blockLine) {
        set(PROPERTY_BLOCKLINE, blockLine);
    }

    public BigDecimal getElectricalSubsidyUnits() {
        return (BigDecimal) get(PROPERTY_ELECTRICALSUBSIDYUNITS);
    }

    public void setElectricalSubsidyUnits(BigDecimal electricalSubsidyUnits) {
        set(PROPERTY_ELECTRICALSUBSIDYUNITS, electricalSubsidyUnits);
    }

    public Boolean isVacant() {
        return (Boolean) get(PROPERTY_VACANT);
    }

    public void setVacant(Boolean vacant) {
        set(PROPERTY_VACANT, vacant);
    }

    public Boolean isHasDish() {
        return (Boolean) get(PROPERTY_HASDISH);
    }

    public void setHasDish(Boolean hasDish) {
        set(PROPERTY_HASDISH, hasDish);
    }

    public RcmrMeter getMeterNumber() {
        return (RcmrMeter) get(PROPERTY_METERNUMBER);
    }

    public void setMeterNumber(RcmrMeter meterNumber) {
        set(PROPERTY_METERNUMBER, meterNumber);
    }

    public Boolean isBachelorquarter() {
        return (Boolean) get(PROPERTY_ISBACHELORQUARTER);
    }

    public void setBachelorquarter(Boolean isbachelorquarter) {
        set(PROPERTY_ISBACHELORQUARTER, isbachelorquarter);
    }

    public Boolean isRented() {
        return (Boolean) get(PROPERTY_ISRENTED);
    }

    public void setRented(Boolean isrented) {
        set(PROPERTY_ISRENTED, isrented);
    }

    public BigDecimal getNoofemployees() {
        return (BigDecimal) get(PROPERTY_NOOFEMPLOYEES);
    }

    public void setNoofemployees(BigDecimal noofemployees) {
        set(PROPERTY_NOOFEMPLOYEES, noofemployees);
    }

    public BigDecimal getMaxnoofemployees() {
        return (BigDecimal) get(PROPERTY_MAXNOOFEMPLOYEES);
    }

    public void setMaxnoofemployees(BigDecimal maxnoofemployees) {
        set(PROPERTY_MAXNOOFEMPLOYEES, maxnoofemployees);
    }

    public BigDecimal getNooemployees() {
        return (BigDecimal) get(PROPERTY_NOOEMPLOYEES);
    }

    public void setNooemployees(BigDecimal nooemployees) {
        set(PROPERTY_NOOEMPLOYEES, nooemployees);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Allotment> getRCHRAllotmentList() {
        return (List<RCHR_Allotment>) get(PROPERTY_RCHRALLOTMENTLIST);
    }

    public void setRCHRAllotmentList(List<RCHR_Allotment> rCHRAllotmentList) {
        set(PROPERTY_RCHRALLOTMENTLIST, rCHRAllotmentList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_DeAllotment> getRCHRDeAllotmentList() {
        return (List<RCHR_DeAllotment>) get(PROPERTY_RCHRDEALLOTMENTLIST);
    }

    public void setRCHRDeAllotmentList(List<RCHR_DeAllotment> rCHRDeAllotmentList) {
        set(PROPERTY_RCHRDEALLOTMENTLIST, rCHRDeAllotmentList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_ElectricReading> getRCHRElectricReadingList() {
        return (List<RCHR_ElectricReading>) get(PROPERTY_RCHRELECTRICREADINGLIST);
    }

    public void setRCHRElectricReadingList(List<RCHR_ElectricReading> rCHRElectricReadingList) {
        set(PROPERTY_RCHRELECTRICREADINGLIST, rCHRElectricReadingList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRoomRentLines> getRCHRRoomRentLinesList() {
        return (List<RchrRoomRentLines>) get(PROPERTY_RCHRROOMRENTLINESLIST);
    }

    public void setRCHRRoomRentLinesList(List<RchrRoomRentLines> rCHRRoomRentLinesList) {
        set(PROPERTY_RCHRROOMRENTLINESLIST, rCHRRoomRentLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Room_Appliance> getRCHRRoomApplianceList() {
        return (List<RCHR_Room_Appliance>) get(PROPERTY_RCHRROOMAPPLIANCELIST);
    }

    public void setRCHRRoomApplianceList(List<RCHR_Room_Appliance> rCHRRoomApplianceList) {
        set(PROPERTY_RCHRROOMAPPLIANCELIST, rCHRRoomApplianceList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplDeprtmtstorerequsition> getRCPLDeprtmtstorerequsitionList() {
        return (List<RcplDeprtmtstorerequsition>) get(PROPERTY_RCPLDEPRTMTSTOREREQUSITIONLIST);
    }

    public void setRCPLDeprtmtstorerequsitionList(List<RcplDeprtmtstorerequsition> rCPLDeprtmtstorerequsitionList) {
        set(PROPERTY_RCPLDEPRTMTSTOREREQUSITIONLIST, rCPLDeprtmtstorerequsitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLElectricityBill> getRCPLElectricityBillList() {
        return (List<RCPLElectricityBill>) get(PROPERTY_RCPLELECTRICITYBILLLIST);
    }

    public void setRCPLElectricityBillList(List<RCPLElectricityBill> rCPLElectricityBillList) {
        set(PROPERTY_RCPLELECTRICITYBILLLIST, rCPLElectricityBillList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpPayrollProcess> getRCPLEmpPayrollProcessList() {
        return (List<RCPL_EmpPayrollProcess>) get(PROPERTY_RCPLEMPPAYROLLPROCESSLIST);
    }

    public void setRCPLEmpPayrollProcessList(List<RCPL_EmpPayrollProcess> rCPLEmpPayrollProcessList) {
        set(PROPERTY_RCPLEMPPAYROLLPROCESSLIST, rCPLEmpPayrollProcessList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLRentBill> getRCPLRentBillList() {
        return (List<RCPLRentBill>) get(PROPERTY_RCPLRENTBILLLIST);
    }

    public void setRCPLRentBillList(List<RCPLRentBill> rCPLRentBillList) {
        set(PROPERTY_RCPLRENTBILLLIST, rCPLRentBillList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployee> getRchrEmployeeList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEELIST);
    }

    public void setRchrEmployeeList(List<RchrEmployee> rchrEmployeeList) {
        set(PROPERTY_RCHREMPLOYEELIST, rchrEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRoomEmployee> getRchrRoomEmployeeList() {
        return (List<RchrRoomEmployee>) get(PROPERTY_RCHRROOMEMPLOYEELIST);
    }

    public void setRchrRoomEmployeeList(List<RchrRoomEmployee> rchrRoomEmployeeList) {
        set(PROPERTY_RCHRROOMEMPLOYEELIST, rchrRoomEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RcmrDomesticmeter> getRcmrDomesticmeterList() {
        return (List<RcmrDomesticmeter>) get(PROPERTY_RCMRDOMESTICMETERLIST);
    }

    public void setRcmrDomesticmeterList(List<RcmrDomesticmeter> rcmrDomesticmeterList) {
        set(PROPERTY_RCMRDOMESTICMETERLIST, rcmrDomesticmeterList);
    }

}
