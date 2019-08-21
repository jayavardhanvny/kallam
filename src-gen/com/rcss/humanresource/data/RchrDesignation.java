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

import com.redcarpet.goodsissue.data.RCGIDeptStoreEligibilty;
import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RcplEmpfine;
import com.redcarpet.payroll.data.RcplLoomcategorylines;

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
 * Entity class for entity Rchr_Designation (stored in table Rchr_Designation).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrDesignation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Designation";
    public static final String ENTITY_NAME = "Rchr_Designation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchkey";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_RCHRSALARYSTRUCTURE = "rchrSalarystructure";
    public static final String PROPERTY_RCGIDEPTSTOREELIGIBILTYLIST = "rCGIDeptStoreEligibiltyList";
    public static final String PROPERTY_RCHRDESIGBASICLIST = "rCHRDesigBasicList";
    public static final String PROPERTY_RCHREMPJOBINFOLIST = "rCHREmpJobInfoList";
    public static final String PROPERTY_RCHREMPLOANLIST = "rCHREmpLoanList";
    public static final String PROPERTY_RCHRJOBDETAILSLIST = "rCHRJobDetailsList";
    public static final String PROPERTY_RCHRLEAVEREQUISITIONLIST = "rCHRLeaveRequisitionList";
    public static final String PROPERTY_RCHRMOBALIZERINCLINESLIST = "rCHRMobalizerinclinesList";
    public static final String PROPERTY_RCHRPREPARATPRODINCNTIVELIST = "rCHRPreparatprodincntiveList";
    public static final String PROPERTY_RCPLEMPFINELIST = "rCPLEmpFineList";
    public static final String PROPERTY_RCPLEMPPAYROLLPROCESSLIST = "rCPLEmpPayrollProcessList";
    public static final String PROPERTY_RCPLPAYROLLPAIDPROCLINELIST = "rCPLPayrollpaidproclineList";
    public static final String PROPERTY_RCHRDESIGEMPLIST = "rchrDesigEmpList";
    public static final String PROPERTY_RCHREMPJOBLIST = "rchrEmpJobList";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";
    public static final String PROPERTY_RCPLLOOMCATEGORYLINESLIST = "rcplLoomcategorylinesList";

    public RchrDesignation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RCGIDEPTSTOREELIGIBILTYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDESIGBASICLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPJOBINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOANLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOBDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMOBALIZERINCLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRPREPARATPRODINCNTIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPFINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPAYROLLPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDESIGEMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPJOBLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLLOOMCATEGORYLINESLIST, new ArrayList<Object>());
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

    public String getSearchkey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchkey(String searchkey) {
        set(PROPERTY_SEARCHKEY, searchkey);
    }

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public RchrSalarystructure getRchrSalarystructure() {
        return (RchrSalarystructure) get(PROPERTY_RCHRSALARYSTRUCTURE);
    }

    public void setRchrSalarystructure(RchrSalarystructure rchrSalarystructure) {
        set(PROPERTY_RCHRSALARYSTRUCTURE, rchrSalarystructure);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDeptStoreEligibilty> getRCGIDeptStoreEligibiltyList() {
        return (List<RCGIDeptStoreEligibilty>) get(PROPERTY_RCGIDEPTSTOREELIGIBILTYLIST);
    }

    public void setRCGIDeptStoreEligibiltyList(List<RCGIDeptStoreEligibilty> rCGIDeptStoreEligibiltyList) {
        set(PROPERTY_RCGIDEPTSTOREELIGIBILTYLIST, rCGIDeptStoreEligibiltyList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRDesigBasic> getRCHRDesigBasicList() {
        return (List<RCHRDesigBasic>) get(PROPERTY_RCHRDESIGBASICLIST);
    }

    public void setRCHRDesigBasicList(List<RCHRDesigBasic> rCHRDesigBasicList) {
        set(PROPERTY_RCHRDESIGBASICLIST, rCHRDesigBasicList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHREmpJobInfo> getRCHREmpJobInfoList() {
        return (List<RCHREmpJobInfo>) get(PROPERTY_RCHREMPJOBINFOLIST);
    }

    public void setRCHREmpJobInfoList(List<RCHREmpJobInfo> rCHREmpJobInfoList) {
        set(PROPERTY_RCHREMPJOBINFOLIST, rCHREmpJobInfoList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Emp_Loan> getRCHREmpLoanList() {
        return (List<RCHR_Emp_Loan>) get(PROPERTY_RCHREMPLOANLIST);
    }

    public void setRCHREmpLoanList(List<RCHR_Emp_Loan> rCHREmpLoanList) {
        set(PROPERTY_RCHREMPLOANLIST, rCHREmpLoanList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRJobDetails> getRCHRJobDetailsList() {
        return (List<RCHRJobDetails>) get(PROPERTY_RCHRJOBDETAILSLIST);
    }

    public void setRCHRJobDetailsList(List<RCHRJobDetails> rCHRJobDetailsList) {
        set(PROPERTY_RCHRJOBDETAILSLIST, rCHRJobDetailsList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_LeaveRequisition> getRCHRLeaveRequisitionList() {
        return (List<RCHR_LeaveRequisition>) get(PROPERTY_RCHRLEAVEREQUISITIONLIST);
    }

    public void setRCHRLeaveRequisitionList(List<RCHR_LeaveRequisition> rCHRLeaveRequisitionList) {
        set(PROPERTY_RCHRLEAVEREQUISITIONLIST, rCHRLeaveRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRMobalizerinclines> getRCHRMobalizerinclinesList() {
        return (List<RCHRMobalizerinclines>) get(PROPERTY_RCHRMOBALIZERINCLINESLIST);
    }

    public void setRCHRMobalizerinclinesList(List<RCHRMobalizerinclines> rCHRMobalizerinclinesList) {
        set(PROPERTY_RCHRMOBALIZERINCLINESLIST, rCHRMobalizerinclinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrPreparatprodincntive> getRCHRPreparatprodincntiveList() {
        return (List<RchrPreparatprodincntive>) get(PROPERTY_RCHRPREPARATPRODINCNTIVELIST);
    }

    public void setRCHRPreparatprodincntiveList(List<RchrPreparatprodincntive> rCHRPreparatprodincntiveList) {
        set(PROPERTY_RCHRPREPARATPRODINCNTIVELIST, rCHRPreparatprodincntiveList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplEmpfine> getRCPLEmpFineList() {
        return (List<RcplEmpfine>) get(PROPERTY_RCPLEMPFINELIST);
    }

    public void setRCPLEmpFineList(List<RcplEmpfine> rCPLEmpFineList) {
        set(PROPERTY_RCPLEMPFINELIST, rCPLEmpFineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_EmpPayrollProcess> getRCPLEmpPayrollProcessList() {
        return (List<RCPL_EmpPayrollProcess>) get(PROPERTY_RCPLEMPPAYROLLPROCESSLIST);
    }

    public void setRCPLEmpPayrollProcessList(List<RCPL_EmpPayrollProcess> rCPLEmpPayrollProcessList) {
        set(PROPERTY_RCPLEMPPAYROLLPROCESSLIST, rCPLEmpPayrollProcessList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLPayrollpaidprocline> getRCPLPayrollpaidproclineList() {
        return (List<RCPLPayrollpaidprocline>) get(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST);
    }

    public void setRCPLPayrollpaidproclineList(List<RCPLPayrollpaidprocline> rCPLPayrollpaidproclineList) {
        set(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST, rCPLPayrollpaidproclineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrDesigEmp> getRchrDesigEmpList() {
        return (List<RchrDesigEmp>) get(PROPERTY_RCHRDESIGEMPLIST);
    }

    public void setRchrDesigEmpList(List<RchrDesigEmp> rchrDesigEmpList) {
        set(PROPERTY_RCHRDESIGEMPLIST, rchrDesigEmpList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmpJob> getRchrEmpJobList() {
        return (List<RchrEmpJob>) get(PROPERTY_RCHREMPJOBLIST);
    }

    public void setRchrEmpJobList(List<RchrEmpJob> rchrEmpJobList) {
        set(PROPERTY_RCHREMPJOBLIST, rchrEmpJobList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployee> getRchrEmployeeList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEELIST);
    }

    public void setRchrEmployeeList(List<RchrEmployee> rchrEmployeeList) {
        set(PROPERTY_RCHREMPLOYEELIST, rchrEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplLoomcategorylines> getRcplLoomcategorylinesList() {
        return (List<RcplLoomcategorylines>) get(PROPERTY_RCPLLOOMCATEGORYLINESLIST);
    }

    public void setRcplLoomcategorylinesList(List<RcplLoomcategorylines> rcplLoomcategorylinesList) {
        set(PROPERTY_RCPLLOOMCATEGORYLINESLIST, rcplLoomcategorylinesList);
    }

}
