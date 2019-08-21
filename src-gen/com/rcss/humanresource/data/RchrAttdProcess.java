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

import com.redcarpet.payroll.data.RCPLPRODINCENT;
import com.redcarpet.payroll.data.RCPLPayrollpaidproc;
import com.redcarpet.payroll.data.RCPLRentBill;
import com.redcarpet.payroll.data.RCPL_Otherdeductions;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.data.RCPL_Processsw;
import com.redcarpet.payroll.data.RcplGas;
import com.redcarpet.payroll.data.RcplPayrollprocessmanual;
import com.redcarpet.payroll.data.RcplSecuritydeposit;

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
 * Entity class for entity Rchr_Attd_Process (stored in table Rchr_Attd_Process).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrAttdProcess extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Attd_Process";
    public static final String ENTITY_NAME = "Rchr_Attd_Process";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRYEAR = "rchrYear";
    public static final String PROPERTY_MONTHNAME = "monthname";
    public static final String PROPERTY_PERIODNO = "periodNo";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_PROCESSINGDAYS = "processingdays";
    public static final String PROPERTY_ATTENDANCEPROCESS = "attendanceprocess";
    public static final String PROPERTY_OTPROCESS = "otprocess";
    public static final String PROPERTY_PAYROLLPROCESS = "payrollprocess";
    public static final String PROPERTY_PAYROLLPOST = "payrollpost";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_INCENTIVE = "incentive";
    public static final String PROPERTY_RCHREMPLOANLIST = "rCHREmpLoanList";
    public static final String PROPERTY_RCHRGASADVANCELIST = "rCHRGasAdvanceList";
    public static final String PROPERTY_RCHRMOBALIZERSERVICEINCLIST = "rCHRMobalizerserviceincList";
    public static final String PROPERTY_RCHRROOMRENTLIST = "rCHRRoomrentList";
    public static final String PROPERTY_RCHRSECURITYDEPOSITELIST = "rCHRSecuritydepositeList";
    public static final String PROPERTY_RCPLGASLIST = "rCPLGasList";
    public static final String PROPERTY_RCPLOTHERDEDUCTIONSLIST = "rCPLOtherdeductionsList";
    public static final String PROPERTY_RCPLPRODINCENTLIST = "rCPLPRODINCENTList";
    public static final String PROPERTY_RCPLPAYROLLPROCESSLIST = "rCPLPayrollProcessList";
    public static final String PROPERTY_RCPLPAYROLLPAIDPROCLIST = "rCPLPayrollpaidprocList";
    public static final String PROPERTY_RCPLPROCESSSWLIST = "rCPLProcessswList";
    public static final String PROPERTY_RCPLRENTBILLLIST = "rCPLRentBillList";
    public static final String PROPERTY_RCPLSECURITYDEPOSITLIST = "rCPLSecurityDepositList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSLIST = "rchrBanksalpaymentsList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSAPPLIST = "rchrBanksalpaymentsappList";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSLIST = "rchrExbanksalpaymentsList";
    public static final String PROPERTY_RCPLPAYROLLPROCESSMANUALLIST = "rcplPayrollprocessmanualList";

    public RchrAttdProcess() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PERIODNO, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESSINGDAYS, (long) 0);
        setDefaultValue(PROPERTY_ATTENDANCEPROCESS, false);
        setDefaultValue(PROPERTY_OTPROCESS, false);
        setDefaultValue(PROPERTY_PAYROLLPROCESS, false);
        setDefaultValue(PROPERTY_PAYROLLPOST, false);
        setDefaultValue(PROPERTY_INCENTIVE, false);
        setDefaultValue(PROPERTY_RCHREMPLOANLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRGASADVANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMOBALIZERSERVICEINCLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROOMRENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSECURITYDEPOSITELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLGASLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLOTHERDEDUCTIONSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPAIDPROCLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPROCESSSWLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLRENTBILLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLSECURITYDEPOSITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSAPPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREXBANKSALPAYMENTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPROCESSMANUALLIST, new ArrayList<Object>());
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

    public RchrYear getRchrYear() {
        return (RchrYear) get(PROPERTY_RCHRYEAR);
    }

    public void setRchrYear(RchrYear rchrYear) {
        set(PROPERTY_RCHRYEAR, rchrYear);
    }

    public String getMonthname() {
        return (String) get(PROPERTY_MONTHNAME);
    }

    public void setMonthname(String monthname) {
        set(PROPERTY_MONTHNAME, monthname);
    }

    public BigDecimal getPeriodNo() {
        return (BigDecimal) get(PROPERTY_PERIODNO);
    }

    public void setPeriodNo(BigDecimal periodNo) {
        set(PROPERTY_PERIODNO, periodNo);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public Long getProcessingdays() {
        return (Long) get(PROPERTY_PROCESSINGDAYS);
    }

    public void setProcessingdays(Long processingdays) {
        set(PROPERTY_PROCESSINGDAYS, processingdays);
    }

    public Boolean isAttendanceprocess() {
        return (Boolean) get(PROPERTY_ATTENDANCEPROCESS);
    }

    public void setAttendanceprocess(Boolean attendanceprocess) {
        set(PROPERTY_ATTENDANCEPROCESS, attendanceprocess);
    }

    public Boolean isOtprocess() {
        return (Boolean) get(PROPERTY_OTPROCESS);
    }

    public void setOtprocess(Boolean otprocess) {
        set(PROPERTY_OTPROCESS, otprocess);
    }

    public Boolean isPayrollprocess() {
        return (Boolean) get(PROPERTY_PAYROLLPROCESS);
    }

    public void setPayrollprocess(Boolean payrollprocess) {
        set(PROPERTY_PAYROLLPROCESS, payrollprocess);
    }

    public Boolean isPayrollpost() {
        return (Boolean) get(PROPERTY_PAYROLLPOST);
    }

    public void setPayrollpost(Boolean payrollpost) {
        set(PROPERTY_PAYROLLPOST, payrollpost);
    }

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public Boolean isIncentive() {
        return (Boolean) get(PROPERTY_INCENTIVE);
    }

    public void setIncentive(Boolean incentive) {
        set(PROPERTY_INCENTIVE, incentive);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Emp_Loan> getRCHREmpLoanList() {
        return (List<RCHR_Emp_Loan>) get(PROPERTY_RCHREMPLOANLIST);
    }

    public void setRCHREmpLoanList(List<RCHR_Emp_Loan> rCHREmpLoanList) {
        set(PROPERTY_RCHREMPLOANLIST, rCHREmpLoanList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRGasAdvance> getRCHRGasAdvanceList() {
        return (List<RCHRGasAdvance>) get(PROPERTY_RCHRGASADVANCELIST);
    }

    public void setRCHRGasAdvanceList(List<RCHRGasAdvance> rCHRGasAdvanceList) {
        set(PROPERTY_RCHRGASADVANCELIST, rCHRGasAdvanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRMobalizerserviceinc> getRCHRMobalizerserviceincList() {
        return (List<RCHRMobalizerserviceinc>) get(PROPERTY_RCHRMOBALIZERSERVICEINCLIST);
    }

    public void setRCHRMobalizerserviceincList(List<RCHRMobalizerserviceinc> rCHRMobalizerserviceincList) {
        set(PROPERTY_RCHRMOBALIZERSERVICEINCLIST, rCHRMobalizerserviceincList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRoomrent> getRCHRRoomrentList() {
        return (List<RchrRoomrent>) get(PROPERTY_RCHRROOMRENTLIST);
    }

    public void setRCHRRoomrentList(List<RchrRoomrent> rCHRRoomrentList) {
        set(PROPERTY_RCHRROOMRENTLIST, rCHRRoomrentList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRSecuritydeposite> getRCHRSecuritydepositeList() {
        return (List<RCHRSecuritydeposite>) get(PROPERTY_RCHRSECURITYDEPOSITELIST);
    }

    public void setRCHRSecuritydepositeList(List<RCHRSecuritydeposite> rCHRSecuritydepositeList) {
        set(PROPERTY_RCHRSECURITYDEPOSITELIST, rCHRSecuritydepositeList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplGas> getRCPLGasList() {
        return (List<RcplGas>) get(PROPERTY_RCPLGASLIST);
    }

    public void setRCPLGasList(List<RcplGas> rCPLGasList) {
        set(PROPERTY_RCPLGASLIST, rCPLGasList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_Otherdeductions> getRCPLOtherdeductionsList() {
        return (List<RCPL_Otherdeductions>) get(PROPERTY_RCPLOTHERDEDUCTIONSLIST);
    }

    public void setRCPLOtherdeductionsList(List<RCPL_Otherdeductions> rCPLOtherdeductionsList) {
        set(PROPERTY_RCPLOTHERDEDUCTIONSLIST, rCPLOtherdeductionsList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLPRODINCENT> getRCPLPRODINCENTList() {
        return (List<RCPLPRODINCENT>) get(PROPERTY_RCPLPRODINCENTLIST);
    }

    public void setRCPLPRODINCENTList(List<RCPLPRODINCENT> rCPLPRODINCENTList) {
        set(PROPERTY_RCPLPRODINCENTLIST, rCPLPRODINCENTList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_PayrollProcess> getRCPLPayrollProcessList() {
        return (List<RCPL_PayrollProcess>) get(PROPERTY_RCPLPAYROLLPROCESSLIST);
    }

    public void setRCPLPayrollProcessList(List<RCPL_PayrollProcess> rCPLPayrollProcessList) {
        set(PROPERTY_RCPLPAYROLLPROCESSLIST, rCPLPayrollProcessList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLPayrollpaidproc> getRCPLPayrollpaidprocList() {
        return (List<RCPLPayrollpaidproc>) get(PROPERTY_RCPLPAYROLLPAIDPROCLIST);
    }

    public void setRCPLPayrollpaidprocList(List<RCPLPayrollpaidproc> rCPLPayrollpaidprocList) {
        set(PROPERTY_RCPLPAYROLLPAIDPROCLIST, rCPLPayrollpaidprocList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_Processsw> getRCPLProcessswList() {
        return (List<RCPL_Processsw>) get(PROPERTY_RCPLPROCESSSWLIST);
    }

    public void setRCPLProcessswList(List<RCPL_Processsw> rCPLProcessswList) {
        set(PROPERTY_RCPLPROCESSSWLIST, rCPLProcessswList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLRentBill> getRCPLRentBillList() {
        return (List<RCPLRentBill>) get(PROPERTY_RCPLRENTBILLLIST);
    }

    public void setRCPLRentBillList(List<RCPLRentBill> rCPLRentBillList) {
        set(PROPERTY_RCPLRENTBILLLIST, rCPLRentBillList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplSecuritydeposit> getRCPLSecurityDepositList() {
        return (List<RcplSecuritydeposit>) get(PROPERTY_RCPLSECURITYDEPOSITLIST);
    }

    public void setRCPLSecurityDepositList(List<RcplSecuritydeposit> rCPLSecurityDepositList) {
        set(PROPERTY_RCPLSECURITYDEPOSITLIST, rCPLSecurityDepositList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpayments> getRchrBanksalpaymentsList() {
        return (List<RchrBanksalpayments>) get(PROPERTY_RCHRBANKSALPAYMENTSLIST);
    }

    public void setRchrBanksalpaymentsList(List<RchrBanksalpayments> rchrBanksalpaymentsList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSLIST, rchrBanksalpaymentsList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsApp> getRchrBanksalpaymentsappList() {
        return (List<RchrBanksalpaymentsApp>) get(PROPERTY_RCHRBANKSALPAYMENTSAPPLIST);
    }

    public void setRchrBanksalpaymentsappList(List<RchrBanksalpaymentsApp> rchrBanksalpaymentsappList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSAPPLIST, rchrBanksalpaymentsappList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpayments> getRchrExbanksalpaymentsList() {
        return (List<RchrExbanksalpayments>) get(PROPERTY_RCHREXBANKSALPAYMENTSLIST);
    }

    public void setRchrExbanksalpaymentsList(List<RchrExbanksalpayments> rchrExbanksalpaymentsList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSLIST, rchrExbanksalpaymentsList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplPayrollprocessmanual> getRcplPayrollprocessmanualList() {
        return (List<RcplPayrollprocessmanual>) get(PROPERTY_RCPLPAYROLLPROCESSMANUALLIST);
    }

    public void setRcplPayrollprocessmanualList(List<RcplPayrollprocessmanual> rcplPayrollprocessmanualList) {
        set(PROPERTY_RCPLPAYROLLPROCESSMANUALLIST, rcplPayrollprocessmanualList);
    }

}
