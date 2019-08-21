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
package com.redcarpet.payroll.data;

import com.rcss.humanresource.data.RchrRelay;

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
 * Entity class for entity RCPL_VariableSet (stored in table RCPL_VariableSet).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPL_VariableSet extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPL_VariableSet";
    public static final String ENTITY_NAME = "RCPL_VariableSet";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_RELAY = "relay";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_EDITLINES = "editlines";
    public static final String PROPERTY_ISATTENDANCE = "isAttendance";
    public static final String PROPERTY_ISLATE = "isLate";
    public static final String PROPERTY_ISSHIFTCHANGEABSENT = "isShiftChangeAbsent";
    public static final String PROPERTY_ISMONTHLYINCENTIVE = "isMonthlyIncentive";
    public static final String PROPERTY_ISWEEKLYOFF = "isWeeklyOff";
    public static final String PROPERTY_ISOTINCENTIVE = "isOTIncentive";
    public static final String PROPERTY_ISNIGHTSHIFTINCENTIVE = "isNightShiftIncentive";
    public static final String PROPERTY_ISLOOMINCENTIVE = "isloomincentive";
    public static final String PROPERTY_ISSERVICEDAYSINCENTIVE = "isservicedaysincentive";
    public static final String PROPERTY_ISATTENDANCEPRIZE = "isattendanceprize";
    public static final String PROPERTY_ISSTAFFATTENDANCE = "isstaffattendance";
    public static final String PROPERTY_ISMESSDEDUCTION = "ismessdeduction";
    public static final String PROPERTY_ISPRODINCENTIVE = "isprodincentive";
    public static final String PROPERTY_IS1000ATTDINCEN = "is1000attdincen";
    public static final String PROPERTY_IS1300ATTDINCEN = "is1300attdincen";
    public static final String PROPERTY_IS1040ATTDINCEN = "is1040attdincen";
    public static final String PROPERTY_ISSTAFFPRODINCEN = "isstaffprodincen";
    public static final String PROPERTY_IS1350ATTDINCEN = "is1350attdincen";
    public static final String PROPERTY_IS12HRSBSHIFTINCEN = "is12hrsbshiftincen";
    public static final String PROPERTY_ISCSHIFTINCEN = "iscshiftincen";
    public static final String PROPERTY_STAFFOTHERPROINCENTIVE = "staffotherproincentive";
    public static final String PROPERTY_RCPLINCENTIVELINESLIST = "rCPLIncentiveLinesList";
    public static final String PROPERTY_RCPLINCENTIVESLIST = "rCPLIncentivesList";
    public static final String PROPERTY_RCPLVARIABLELINELIST = "rCPLVariableLineList";

    public RCPL_VariableSet() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_EDITLINES, false);
        setDefaultValue(PROPERTY_ISATTENDANCE, false);
        setDefaultValue(PROPERTY_ISLATE, false);
        setDefaultValue(PROPERTY_ISSHIFTCHANGEABSENT, false);
        setDefaultValue(PROPERTY_ISMONTHLYINCENTIVE, false);
        setDefaultValue(PROPERTY_ISWEEKLYOFF, false);
        setDefaultValue(PROPERTY_ISOTINCENTIVE, false);
        setDefaultValue(PROPERTY_ISNIGHTSHIFTINCENTIVE, false);
        setDefaultValue(PROPERTY_ISLOOMINCENTIVE, false);
        setDefaultValue(PROPERTY_ISSERVICEDAYSINCENTIVE, false);
        setDefaultValue(PROPERTY_ISATTENDANCEPRIZE, false);
        setDefaultValue(PROPERTY_ISSTAFFATTENDANCE, false);
        setDefaultValue(PROPERTY_ISMESSDEDUCTION, false);
        setDefaultValue(PROPERTY_ISPRODINCENTIVE, false);
        setDefaultValue(PROPERTY_IS1000ATTDINCEN, false);
        setDefaultValue(PROPERTY_IS1300ATTDINCEN, false);
        setDefaultValue(PROPERTY_IS1040ATTDINCEN, false);
        setDefaultValue(PROPERTY_ISSTAFFPRODINCEN, false);
        setDefaultValue(PROPERTY_IS1350ATTDINCEN, false);
        setDefaultValue(PROPERTY_IS12HRSBSHIFTINCEN, false);
        setDefaultValue(PROPERTY_ISCSHIFTINCEN, false);
        setDefaultValue(PROPERTY_STAFFOTHERPROINCENTIVE, false);
        setDefaultValue(PROPERTY_RCPLINCENTIVELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLINCENTIVESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLVARIABLELINELIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public RchrRelay getRelay() {
        return (RchrRelay) get(PROPERTY_RELAY);
    }

    public void setRelay(RchrRelay relay) {
        set(PROPERTY_RELAY, relay);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isEditlines() {
        return (Boolean) get(PROPERTY_EDITLINES);
    }

    public void setEditlines(Boolean editlines) {
        set(PROPERTY_EDITLINES, editlines);
    }

    public Boolean isAttendance() {
        return (Boolean) get(PROPERTY_ISATTENDANCE);
    }

    public void setAttendance(Boolean isAttendance) {
        set(PROPERTY_ISATTENDANCE, isAttendance);
    }

    public Boolean isLate() {
        return (Boolean) get(PROPERTY_ISLATE);
    }

    public void setLate(Boolean isLate) {
        set(PROPERTY_ISLATE, isLate);
    }

    public Boolean isShiftChangeAbsent() {
        return (Boolean) get(PROPERTY_ISSHIFTCHANGEABSENT);
    }

    public void setShiftChangeAbsent(Boolean isShiftChangeAbsent) {
        set(PROPERTY_ISSHIFTCHANGEABSENT, isShiftChangeAbsent);
    }

    public Boolean isMonthlyIncentive() {
        return (Boolean) get(PROPERTY_ISMONTHLYINCENTIVE);
    }

    public void setMonthlyIncentive(Boolean isMonthlyIncentive) {
        set(PROPERTY_ISMONTHLYINCENTIVE, isMonthlyIncentive);
    }

    public Boolean isWeeklyOff() {
        return (Boolean) get(PROPERTY_ISWEEKLYOFF);
    }

    public void setWeeklyOff(Boolean isWeeklyOff) {
        set(PROPERTY_ISWEEKLYOFF, isWeeklyOff);
    }

    public Boolean isOTIncentive() {
        return (Boolean) get(PROPERTY_ISOTINCENTIVE);
    }

    public void setOTIncentive(Boolean isOTIncentive) {
        set(PROPERTY_ISOTINCENTIVE, isOTIncentive);
    }

    public Boolean isNightShiftIncentive() {
        return (Boolean) get(PROPERTY_ISNIGHTSHIFTINCENTIVE);
    }

    public void setNightShiftIncentive(Boolean isNightShiftIncentive) {
        set(PROPERTY_ISNIGHTSHIFTINCENTIVE, isNightShiftIncentive);
    }

    public Boolean isLoomincentive() {
        return (Boolean) get(PROPERTY_ISLOOMINCENTIVE);
    }

    public void setLoomincentive(Boolean isloomincentive) {
        set(PROPERTY_ISLOOMINCENTIVE, isloomincentive);
    }

    public Boolean isServicedaysincentive() {
        return (Boolean) get(PROPERTY_ISSERVICEDAYSINCENTIVE);
    }

    public void setServicedaysincentive(Boolean isservicedaysincentive) {
        set(PROPERTY_ISSERVICEDAYSINCENTIVE, isservicedaysincentive);
    }

    public Boolean isAttendanceprize() {
        return (Boolean) get(PROPERTY_ISATTENDANCEPRIZE);
    }

    public void setAttendanceprize(Boolean isattendanceprize) {
        set(PROPERTY_ISATTENDANCEPRIZE, isattendanceprize);
    }

    public Boolean isStaffattendance() {
        return (Boolean) get(PROPERTY_ISSTAFFATTENDANCE);
    }

    public void setStaffattendance(Boolean isstaffattendance) {
        set(PROPERTY_ISSTAFFATTENDANCE, isstaffattendance);
    }

    public Boolean isMessdeduction() {
        return (Boolean) get(PROPERTY_ISMESSDEDUCTION);
    }

    public void setMessdeduction(Boolean ismessdeduction) {
        set(PROPERTY_ISMESSDEDUCTION, ismessdeduction);
    }

    public Boolean isProdincentive() {
        return (Boolean) get(PROPERTY_ISPRODINCENTIVE);
    }

    public void setProdincentive(Boolean isprodincentive) {
        set(PROPERTY_ISPRODINCENTIVE, isprodincentive);
    }

    public Boolean is1000attdincen() {
        return (Boolean) get(PROPERTY_IS1000ATTDINCEN);
    }

    public void set1000attdincen(Boolean is1000attdincen) {
        set(PROPERTY_IS1000ATTDINCEN, is1000attdincen);
    }

    public Boolean is1300attdincen() {
        return (Boolean) get(PROPERTY_IS1300ATTDINCEN);
    }

    public void set1300attdincen(Boolean is1300attdincen) {
        set(PROPERTY_IS1300ATTDINCEN, is1300attdincen);
    }

    public Boolean is1040attdincen() {
        return (Boolean) get(PROPERTY_IS1040ATTDINCEN);
    }

    public void set1040attdincen(Boolean is1040attdincen) {
        set(PROPERTY_IS1040ATTDINCEN, is1040attdincen);
    }

    public Boolean isStaffprodincen() {
        return (Boolean) get(PROPERTY_ISSTAFFPRODINCEN);
    }

    public void setStaffprodincen(Boolean isstaffprodincen) {
        set(PROPERTY_ISSTAFFPRODINCEN, isstaffprodincen);
    }

    public Boolean is1350attdincen() {
        return (Boolean) get(PROPERTY_IS1350ATTDINCEN);
    }

    public void set1350attdincen(Boolean is1350attdincen) {
        set(PROPERTY_IS1350ATTDINCEN, is1350attdincen);
    }

    public Boolean is12hrsbshiftincen() {
        return (Boolean) get(PROPERTY_IS12HRSBSHIFTINCEN);
    }

    public void set12hrsbshiftincen(Boolean is12hrsbshiftincen) {
        set(PROPERTY_IS12HRSBSHIFTINCEN, is12hrsbshiftincen);
    }

    public Boolean isCshiftincen() {
        return (Boolean) get(PROPERTY_ISCSHIFTINCEN);
    }

    public void setCshiftincen(Boolean iscshiftincen) {
        set(PROPERTY_ISCSHIFTINCEN, iscshiftincen);
    }

    public Boolean isStaffotherproincentive() {
        return (Boolean) get(PROPERTY_STAFFOTHERPROINCENTIVE);
    }

    public void setStaffotherproincentive(Boolean staffotherproincentive) {
        set(PROPERTY_STAFFOTHERPROINCENTIVE, staffotherproincentive);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_IncentiveLines> getRCPLIncentiveLinesList() {
        return (List<RCPL_IncentiveLines>) get(PROPERTY_RCPLINCENTIVELINESLIST);
    }

    public void setRCPLIncentiveLinesList(List<RCPL_IncentiveLines> rCPLIncentiveLinesList) {
        set(PROPERTY_RCPLINCENTIVELINESLIST, rCPLIncentiveLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_Incentives> getRCPLIncentivesList() {
        return (List<RCPL_Incentives>) get(PROPERTY_RCPLINCENTIVESLIST);
    }

    public void setRCPLIncentivesList(List<RCPL_Incentives> rCPLIncentivesList) {
        set(PROPERTY_RCPLINCENTIVESLIST, rCPLIncentivesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_VariableLine> getRCPLVariableLineList() {
        return (List<RCPL_VariableLine>) get(PROPERTY_RCPLVARIABLELINELIST);
    }

    public void setRCPLVariableLineList(List<RCPL_VariableLine> rCPLVariableLineList) {
        set(PROPERTY_RCPLVARIABLELINELIST, rCPLVariableLineList);
    }

}
