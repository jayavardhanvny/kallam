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

import com.redcarpet.goodsissue.data.RCGIDepartmentIssue;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RCGI_MaterialReceipt;
import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_ServiceWeightage;
import com.redcarpet.payroll.data.RcplEmpfine;

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
import org.openbravo.model.procurement.Requisition;
/**
 * Entity class for entity Rchr_Emp_Dept (stored in table Rchr_Emp_Dept).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrEmpDept extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Emp_Dept";
    public static final String ENTITY_NAME = "Rchr_Emp_Dept";
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
    public static final String PROPERTY_ISDYEING = "isdyeing";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLIST = "procurementRequisitionList";
    public static final String PROPERTY_RCGIDEPARTMENTISSUELIST = "rCGIDepartmentIssueList";
    public static final String PROPERTY_RCGIGOODSISSUELIST = "rCGIGoodsIssueList";
    public static final String PROPERTY_RCGIMATERIALISSUELIST = "rCGIMaterialIssueList";
    public static final String PROPERTY_RCGIMATERIALRECEIPTLIST = "rCGIMaterialReceiptList";
    public static final String PROPERTY_RCHRDESIGBASICLIST = "rCHRDesigBasicList";
    public static final String PROPERTY_RCHREMPJOBINFOLIST = "rCHREmpJobInfoList";
    public static final String PROPERTY_RCHREMPLEAVEDEPTLIST = "rCHREmpLeaveDeptList";
    public static final String PROPERTY_RCHREMPLOANLIST = "rCHREmpLoanList";
    public static final String PROPERTY_RCHRJOBDETAILSLIST = "rCHRJobDetailsList";
    public static final String PROPERTY_RCHRLEAVEREQUISITIONLIST = "rCHRLeaveRequisitionList";
    public static final String PROPERTY_RCHROVERTIMELIST = "rCHROverTimeList";
    public static final String PROPERTY_RCHRSUBDEPARTMENTLIST = "rCHRSubDepartmentList";
    public static final String PROPERTY_RCPLEMPFINELIST = "rCPLEmpFineList";
    public static final String PROPERTY_RCPLEMPPAYROLLPROCESSLIST = "rCPLEmpPayrollProcessList";
    public static final String PROPERTY_RCPLPAYROLLPAIDPROCLINELIST = "rCPLPayrollpaidproclineList";
    public static final String PROPERTY_RCPLPRODINCENTIVELIST = "rCPLProdIncentiveList";
    public static final String PROPERTY_RCPLSERVICEWEIGHTAGELIST = "rCPLServiceWeightageList";
    public static final String PROPERTY_RCHRATTENDSTRENGTHLIST = "rchrAttendstrengthList";
    public static final String PROPERTY_RCHREMPJOBLIST = "rchrEmpJobList";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";
    public static final String PROPERTY_RCHREMPLOYEELEAVEDEPARTMENTLIST = "rchrEmployeeLeavedepartmentList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";

    public RchrEmpDept() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISDYEING, false);
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDEPARTMENTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIGOODSISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDESIGBASICLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPJOBINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLEAVEDEPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOANLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOBDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHROVERTIMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSUBDEPARTMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPFINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPAYROLLPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLSERVICEWEIGHTAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDSTRENGTHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPJOBLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELEAVEDEPARTMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
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

    public Boolean isDyeing() {
        return (Boolean) get(PROPERTY_ISDYEING);
    }

    public void setDyeing(Boolean isdyeing) {
        set(PROPERTY_ISDYEING, isdyeing);
    }

    @SuppressWarnings("unchecked")
    public List<Requisition> getProcurementRequisitionList() {
        return (List<Requisition>) get(PROPERTY_PROCUREMENTREQUISITIONLIST);
    }

    public void setProcurementRequisitionList(List<Requisition> procurementRequisitionList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLIST, procurementRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDepartmentIssue> getRCGIDepartmentIssueList() {
        return (List<RCGIDepartmentIssue>) get(PROPERTY_RCGIDEPARTMENTISSUELIST);
    }

    public void setRCGIDepartmentIssueList(List<RCGIDepartmentIssue> rCGIDepartmentIssueList) {
        set(PROPERTY_RCGIDEPARTMENTISSUELIST, rCGIDepartmentIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_GoodsIssue> getRCGIGoodsIssueList() {
        return (List<RCGI_GoodsIssue>) get(PROPERTY_RCGIGOODSISSUELIST);
    }

    public void setRCGIGoodsIssueList(List<RCGI_GoodsIssue> rCGIGoodsIssueList) {
        set(PROPERTY_RCGIGOODSISSUELIST, rCGIGoodsIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MaterialIssue> getRCGIMaterialIssueList() {
        return (List<RCGI_MaterialIssue>) get(PROPERTY_RCGIMATERIALISSUELIST);
    }

    public void setRCGIMaterialIssueList(List<RCGI_MaterialIssue> rCGIMaterialIssueList) {
        set(PROPERTY_RCGIMATERIALISSUELIST, rCGIMaterialIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MaterialReceipt> getRCGIMaterialReceiptList() {
        return (List<RCGI_MaterialReceipt>) get(PROPERTY_RCGIMATERIALRECEIPTLIST);
    }

    public void setRCGIMaterialReceiptList(List<RCGI_MaterialReceipt> rCGIMaterialReceiptList) {
        set(PROPERTY_RCGIMATERIALRECEIPTLIST, rCGIMaterialReceiptList);
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
    public List<EmployeeLeaveDeparment> getRCHREmpLeaveDeptList() {
        return (List<EmployeeLeaveDeparment>) get(PROPERTY_RCHREMPLEAVEDEPTLIST);
    }

    public void setRCHREmpLeaveDeptList(List<EmployeeLeaveDeparment> rCHREmpLeaveDeptList) {
        set(PROPERTY_RCHREMPLEAVEDEPTLIST, rCHREmpLeaveDeptList);
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
    public List<RCHR_OverTime> getRCHROverTimeList() {
        return (List<RCHR_OverTime>) get(PROPERTY_RCHROVERTIMELIST);
    }

    public void setRCHROverTimeList(List<RCHR_OverTime> rCHROverTimeList) {
        set(PROPERTY_RCHROVERTIMELIST, rCHROverTimeList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_SubDepartment> getRCHRSubDepartmentList() {
        return (List<RCHR_SubDepartment>) get(PROPERTY_RCHRSUBDEPARTMENTLIST);
    }

    public void setRCHRSubDepartmentList(List<RCHR_SubDepartment> rCHRSubDepartmentList) {
        set(PROPERTY_RCHRSUBDEPARTMENTLIST, rCHRSubDepartmentList);
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
    public List<RCPLProdIncentive> getRCPLProdIncentiveList() {
        return (List<RCPLProdIncentive>) get(PROPERTY_RCPLPRODINCENTIVELIST);
    }

    public void setRCPLProdIncentiveList(List<RCPLProdIncentive> rCPLProdIncentiveList) {
        set(PROPERTY_RCPLPRODINCENTIVELIST, rCPLProdIncentiveList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_ServiceWeightage> getRCPLServiceWeightageList() {
        return (List<RCPL_ServiceWeightage>) get(PROPERTY_RCPLSERVICEWEIGHTAGELIST);
    }

    public void setRCPLServiceWeightageList(List<RCPL_ServiceWeightage> rCPLServiceWeightageList) {
        set(PROPERTY_RCPLSERVICEWEIGHTAGELIST, rCPLServiceWeightageList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendstrength> getRchrAttendstrengthList() {
        return (List<RchrAttendstrength>) get(PROPERTY_RCHRATTENDSTRENGTHLIST);
    }

    public void setRchrAttendstrengthList(List<RchrAttendstrength> rchrAttendstrengthList) {
        set(PROPERTY_RCHRATTENDSTRENGTHLIST, rchrAttendstrengthList);
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
    public List<RchrEmployee> getRchrEmployeeLeavedepartmentList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEELEAVEDEPARTMENTLIST);
    }

    public void setRchrEmployeeLeavedepartmentList(List<RchrEmployee> rchrEmployeeLeavedepartmentList) {
        set(PROPERTY_RCHREMPLOYEELEAVEDEPARTMENTLIST, rchrEmployeeLeavedepartmentList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

}
