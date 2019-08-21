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

import java.sql.Timestamp;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity RCHR_Vehiclegatepass (stored in table RCHR_Vehiclegatepass).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRVehiclegatepass extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Vehiclegatepass";
    public static final String ENTITY_NAME = "RCHR_Vehiclegatepass";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCHRVEHICLEMASTER = "rchrVehiclemaster";
    public static final String PROPERTY_RCHRAREA = "rchrArea";
    public static final String PROPERTY_PURPOSE = "purpose";
    public static final String PROPERTY_INREADING = "inreading";
    public static final String PROPERTY_OUTREADING = "outreading";
    public static final String PROPERTY_TRAVELKMS = "travelkms";
    public static final String PROPERTY_ISREMARK = "isremark";
    public static final String PROPERTY_FROMTIME = "fromtime";
    public static final String PROPERTY_TOTIME = "totime";
    public static final String PROPERTY_TIMEDIFFERENCE = "timedifference";
    public static final String PROPERTY_REMARKS = "remarks";
    public static final String PROPERTY_EMPLOYEES = "employees";
    public static final String PROPERTY_NUMBEROFMEMBERS = "numberofmembers";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_TRAVELEMPLOYEEDETAILS = "travelemployeedetails";

    public RCHRVehiclegatepass() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISREMARK, false);
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public RCHRVehiclemaster getRchrVehiclemaster() {
        return (RCHRVehiclemaster) get(PROPERTY_RCHRVEHICLEMASTER);
    }

    public void setRchrVehiclemaster(RCHRVehiclemaster rchrVehiclemaster) {
        set(PROPERTY_RCHRVEHICLEMASTER, rchrVehiclemaster);
    }

    public RCHRArea getRchrArea() {
        return (RCHRArea) get(PROPERTY_RCHRAREA);
    }

    public void setRchrArea(RCHRArea rchrArea) {
        set(PROPERTY_RCHRAREA, rchrArea);
    }

    public String getPurpose() {
        return (String) get(PROPERTY_PURPOSE);
    }

    public void setPurpose(String purpose) {
        set(PROPERTY_PURPOSE, purpose);
    }

    public Long getInreading() {
        return (Long) get(PROPERTY_INREADING);
    }

    public void setInreading(Long inreading) {
        set(PROPERTY_INREADING, inreading);
    }

    public Long getOutreading() {
        return (Long) get(PROPERTY_OUTREADING);
    }

    public void setOutreading(Long outreading) {
        set(PROPERTY_OUTREADING, outreading);
    }

    public Long getTravelkms() {
        return (Long) get(PROPERTY_TRAVELKMS);
    }

    public void setTravelkms(Long travelkms) {
        set(PROPERTY_TRAVELKMS, travelkms);
    }

    public Boolean isRemark() {
        return (Boolean) get(PROPERTY_ISREMARK);
    }

    public void setRemark(Boolean isremark) {
        set(PROPERTY_ISREMARK, isremark);
    }

    public Timestamp getFromtime() {
        return (Timestamp) get(PROPERTY_FROMTIME);
    }

    public void setFromtime(Timestamp fromtime) {
        set(PROPERTY_FROMTIME, fromtime);
    }

    public Timestamp getTotime() {
        return (Timestamp) get(PROPERTY_TOTIME);
    }

    public void setTotime(Timestamp totime) {
        set(PROPERTY_TOTIME, totime);
    }

    public String getTimedifference() {
        return (String) get(PROPERTY_TIMEDIFFERENCE);
    }

    public void setTimedifference(String timedifference) {
        set(PROPERTY_TIMEDIFFERENCE, timedifference);
    }

    public String getRemarks() {
        return (String) get(PROPERTY_REMARKS);
    }

    public void setRemarks(String remarks) {
        set(PROPERTY_REMARKS, remarks);
    }

    public String getEmployees() {
        return (String) get(PROPERTY_EMPLOYEES);
    }

    public void setEmployees(String employees) {
        set(PROPERTY_EMPLOYEES, employees);
    }

    public Long getNumberofmembers() {
        return (Long) get(PROPERTY_NUMBEROFMEMBERS);
    }

    public void setNumberofmembers(Long numberofmembers) {
        set(PROPERTY_NUMBEROFMEMBERS, numberofmembers);
    }

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public String getTravelemployeedetails() {
        return (String) get(PROPERTY_TRAVELEMPLOYEEDETAILS);
    }

    public void setTravelemployeedetails(String travelemployeedetails) {
        set(PROPERTY_TRAVELEMPLOYEEDETAILS, travelemployeedetails);
    }

}
