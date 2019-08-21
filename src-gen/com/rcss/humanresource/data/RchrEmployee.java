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

import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.epcg_ppcenquiry;
import com.redcarpet.goodsissue.data.RCGIDepartmentIssue;
import com.redcarpet.goodsissue.data.RCGIDeptStoreEligibilty;
import com.redcarpet.goodsissue.data.RCGIDiFamilyList;
import com.redcarpet.payroll.data.RCPLElectricityBill;
import com.redcarpet.payroll.data.RCPLMessBill;
import com.redcarpet.payroll.data.RCPLPRODINCENT;
import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPLRentBill;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_FineLine;
import com.redcarpet.payroll.data.RCPL_Otherdeductions;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.data.RCPL_ProcessSWLine;
import com.redcarpet.payroll.data.RcplDepartmentstore;
import com.redcarpet.payroll.data.RcplDeprtmtstorerequsition;
import com.redcarpet.payroll.data.RcplEmpfine;
import com.redcarpet.payroll.data.RcplEmppprocessmanual;
import com.redcarpet.payroll.data.RcplGas;
import com.redcarpet.payroll.data.RcplSecuritydeposit;
import com.redcarpet.production.data.RCPR_Taskline;

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
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Rchr_Employee (stored in table Rchr_Employee).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrEmployee extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Employee";
    public static final String ENTITY_NAME = "Rchr_Employee";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_EMPLOYEENAME = "employeeName";
    public static final String PROPERTY_IMAGE = "image";
    public static final String PROPERTY_ROTATION = "rotation";
    public static final String PROPERTY_SHIFT = "shift";
    public static final String PROPERTY_ROOMALLOTTED = "roomAllotted";
    public static final String PROPERTY_ROOM = "room";
    public static final String PROPERTY_LICAPPLICABLE = "lICApplicable";
    public static final String PROPERTY_WEEKOFFAPPLICABLE = "weekOffApplicable";
    public static final String PROPERTY_WEEKLYOFF = "weeklyOff";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_PHONENO = "phoneNo";
    public static final String PROPERTY_LANDLINENO = "landLineNo";
    public static final String PROPERTY_EMERGENCYPHONENO = "emergencyPhoneNo";
    public static final String PROPERTY_GENDER = "gender";
    public static final String PROPERTY_MARITALSTATUS = "maritalStatus";
    public static final String PROPERTY_DATEOFBIRTH = "dateOfBirth";
    public static final String PROPERTY_PANNO = "pANNo";
    public static final String PROPERTY_ESINO = "eSINo";
    public static final String PROPERTY_EPFNO = "ePFNo";
    public static final String PROPERTY_ADHARNO = "adharNo";
    public static final String PROPERTY_PFAPPLICABLE = "pFApplicable";
    public static final String PROPERTY_ESIAPPLICABLE = "eSIApplicable";
    public static final String PROPERTY_EMPLOYEETYPE = "employeeType";
    public static final String PROPERTY_FIRSTNAME = "firstName";
    public static final String PROPERTY_LASTNAME = "lastName";
    public static final String PROPERTY_SURNAME = "surName";
    public static final String PROPERTY_RELAY = "relay";
    public static final String PROPERTY_AGENTMASTER = "agentMaster";
    public static final String PROPERTY_VPF = "vPF";
    public static final String PROPERTY_VPFAMOUNT = "vPFAmount";
    public static final String PROPERTY_PREATTDDAYS = "preattddays";
    public static final String PROPERTY_PUNCHNO = "punchno";
    public static final String PROPERTY_SUPERVISORNAME = "supervisorname";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_SUBDEPARTMENT = "subDepartment";
    public static final String PROPERTY_DESIGNATION = "designation";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_JOBTYPE = "jobType";
    public static final String PROPERTY_RCHRSHIFTGROUP = "rchrShiftgroup";
    public static final String PROPERTY_WITHHELD = "withheld";
    public static final String PROPERTY_OBSERVICEDAYS = "observicedays";
    public static final String PROPERTY_ISBANK = "isbank";
    public static final String PROPERTY_ACCOUNTNO = "accountNo";
    public static final String PROPERTY_RCHRMOBALIZER = "rchrMobalizer";
    public static final String PROPERTY_PFTYPE = "pftype";
    public static final String PROPERTY_EMPLOYEESTATUS = "employeestatus";
    public static final String PROPERTY_SALARYSTATUS = "salarystatus";
    public static final String PROPERTY_ISLEAVEAPPLICABLE = "isleaveapplicable";
    public static final String PROPERTY_ISCOFFAPPLICABLE = "iscoffapplicable";
    public static final String PROPERTY_ISODAPPLICABLE = "isodapplicable";
    public static final String PROPERTY_RCHRLEAVETEMPLATE = "rchrLeavetemplate";
    public static final String PROPERTY_LEAVEDEPARTMENT = "leavedepartment";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_RCHRJOINREJOININGFORM = "rchrJoinRejoiningform";
    public static final String PROPERTY_ACTUALSERVICEDAYS = "actualservicedays";
    public static final String PROPERTY_SENIOR = "senior";
    public static final String PROPERTY_HALFDAYAPPLICABLE = "halfdayapplicable";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_RCGIDEPARTMENTISSUELIST = "rCGIDepartmentIssueList";
    public static final String PROPERTY_RCGIDEPTSTOREELIGIBILTYLIST = "rCGIDeptStoreEligibiltyList";
    public static final String PROPERTY_RCGIDIFAMILYLISTLIST = "rCGIDiFamilyListList";
    public static final String PROPERTY_RCHRALLOTMENTLIST = "rCHRAllotmentList";
    public static final String PROPERTY_RCHRATTENDTEMPLIST = "rCHRAttendTempList";
    public static final String PROPERTY_RCHRCERTIFICATELIST = "rCHRCertificateList";
    public static final String PROPERTY_RCHRCOMPENSATEOFFLIST = "rCHRCompensateOffList";
    public static final String PROPERTY_RCHRDAILYATTENDLIST = "rCHRDailyattendList";
    public static final String PROPERTY_RCHRDAILYATTENDDEMOLIST = "rCHRDailyattenddemoList";
    public static final String PROPERTY_RCHRDEALLOTMENTLIST = "rCHRDeAllotmentList";
    public static final String PROPERTY_RCHRDIRWARPBEAMLIST = "rCHRDirwarpBeamList";
    public static final String PROPERTY_RCHRDIRWARPBEAMASSTWARPEMPLOYEELIST = "rCHRDirwarpBeamAsstwarpemployeeList";
    public static final String PROPERTY_RCHRDIRWARPBEAMASSTCREELEMPLOYEELIST = "rCHRDirwarpBeamAsstcreelemployeeList";
    public static final String PROPERTY_RCHREMPADDRESSLIST = "rCHREmpAddressList";
    public static final String PROPERTY_RCHREMPJOBINFOLIST = "rCHREmpJobInfoList";
    public static final String PROPERTY_RCHREMPLEAVEBALANCELIST = "rCHREmpLeaveBalanceList";
    public static final String PROPERTY_RCHREMPLEAVEDEPTLIST = "rCHREmpLeaveDeptList";
    public static final String PROPERTY_RCHREMPLOANLIST = "rCHREmpLoanList";
    public static final String PROPERTY_RCHREMPLOANSURETYEMPIDLIST = "rCHREmpLoanSuretyempidList";
    public static final String PROPERTY_RCHREMPPOLICYLIST = "rCHREmpPolicyList";
    public static final String PROPERTY_RCHREMPRELAYINFOLIST = "rCHREmpRelayInfoList";
    public static final String PROPERTY_RCHREMPWEEKOFFINFOLIST = "rCHREmpWeekoffInfoList";
    public static final String PROPERTY_RCHREMPLOYEELEAVEBALLIST = "rCHREmployeeLeaveBalList";
    public static final String PROPERTY_RCHREMPLOYEECOFFSLIST = "rCHREmployeecoffsList";
    public static final String PROPERTY_RCHREMPLOYEESETTLEMENTLIST = "rCHREmployeesettlementList";
    public static final String PROPERTY_RCHREXPERIENCEDETAILLIST = "rCHRExperienceDetailList";
    public static final String PROPERTY_RCHRFAMILYDETAILSLIST = "rCHRFamilyDetailsList";
    public static final String PROPERTY_RCHRFAMILYDETAILSRELATIONEMPLOYEELIST = "rCHRFamilyDetailsRelationEmployeeList";
    public static final String PROPERTY_RCHRGASADVANCELIST = "rCHRGasAdvanceList";
    public static final String PROPERTY_RCHRINSPECTIONSHEETLIST = "rCHRInspectionsheetList";
    public static final String PROPERTY_RCHRINSPECTIONSHEETDATAEOPLIST = "rCHRInspectionsheetDataeopList";
    public static final String PROPERTY_RCHRISSUETOKENLIST = "rCHRIssueTokenList";
    public static final String PROPERTY_RCHRJOBDETAILSLIST = "rCHRJobDetailsList";
    public static final String PROPERTY_RCHRLEAVEBALANCEHISTORYLIST = "rCHRLeaveBalanceHistoryList";
    public static final String PROPERTY_RCHRLEAVEOPENBALLIST = "rCHRLeaveOpenBalList";
    public static final String PROPERTY_RCHRLEAVEREQUISITIONLIST = "rCHRLeaveRequisitionList";
    public static final String PROPERTY_RCHRLOSSOFPAYLIST = "rCHRLossOfPayList";
    public static final String PROPERTY_RCHRMOBALIZERLIST = "rCHRMobalizerList";
    public static final String PROPERTY_RCHRMOBALIZERINCLINESLIST = "rCHRMobalizerinclinesList";
    public static final String PROPERTY_RCHROVERTIMELIST = "rCHROverTimeList";
    public static final String PROPERTY_RCHROVERTIMEPROCESSLINELIST = "rCHROvertimeprocessLineList";
    public static final String PROPERTY_RCHRPERMISSIONLIST = "rCHRPermissionList";
    public static final String PROPERTY_RCHRPOLICYSLABLIST = "rCHRPolicySlabList";
    public static final String PROPERTY_RCHRPREPARATPRODINCNTIVELIST = "rCHRPreparatprodincntiveList";
    public static final String PROPERTY_RCHRQUALIFICATIONLIST = "rCHRQualificationList";
    public static final String PROPERTY_RCHRRELAYSHIFTDETAILSLIST = "rCHRRelayShiftDetailsList";
    public static final String PROPERTY_RCHRROOMRENTLINESLIST = "rCHRRoomRentLinesList";
    public static final String PROPERTY_RCHRSALADVANCELIST = "rCHRSaladvanceList";
    public static final String PROPERTY_RCHRSECURITYDEPOSITELIST = "rCHRSecuritydepositeList";
    public static final String PROPERTY_RCHRSIZINGBEAMLIST = "rCHRSizingBeamList";
    public static final String PROPERTY_RCHRSIZINGBEAMBACKSIZERLIST = "rCHRSizingBeamBacksizerList";
    public static final String PROPERTY_RCHRSIZINGBEAMMIXERLIST = "rCHRSizingBeamMixerList";
    public static final String PROPERTY_RCHRSIZINGBEAMHELPERLIST = "rCHRSizingBeamHelperList";
    public static final String PROPERTY_RCHRVEHICLEGATEPASSLIST = "rCHRVehiclegatepassList";
    public static final String PROPERTY_RCHRVISITORGATEPASSLIST = "rCHRVisitorgatepassList";
    public static final String PROPERTY_RCHRWEEKLYOFFDETAILSLIST = "rCHRWeeklyOffDetailsList";
    public static final String PROPERTY_RCPLDEPARTMENTSTORELIST = "rCPLDepartmentStoreList";
    public static final String PROPERTY_RCPLDEPRTMTSTOREREQUSITIONLIST = "rCPLDeprtmtstorerequsitionList";
    public static final String PROPERTY_RCPLELECTRICITYBILLLIST = "rCPLElectricityBillList";
    public static final String PROPERTY_RCPLEMPFINELIST = "rCPLEmpFineList";
    public static final String PROPERTY_RCPLEMPPAYROLLPROCESSLIST = "rCPLEmpPayrollProcessList";
    public static final String PROPERTY_RCPLEMPSALSETUPLIST = "rCPLEmpSalSetupList";
    public static final String PROPERTY_RCPLFINELINELIST = "rCPLFinelineList";
    public static final String PROPERTY_RCPLGASLIST = "rCPLGasList";
    public static final String PROPERTY_RCPLMESSBILLLIST = "rCPLMessBillList";
    public static final String PROPERTY_RCPLOTHERDEDUCTIONSLIST = "rCPLOtherdeductionsList";
    public static final String PROPERTY_RCPLPRODINCENTLIST = "rCPLPRODINCENTList";
    public static final String PROPERTY_RCPLPAYROLLPROCESSLIST = "rCPLPayrollProcessList";
    public static final String PROPERTY_RCPLPAYROLLPAIDPROCLINELIST = "rCPLPayrollpaidproclineList";
    public static final String PROPERTY_RCPLPROCESSSWLINELIST = "rCPLProcessswlineList";
    public static final String PROPERTY_RCPLPRODINCENTIVELIST = "rCPLProdIncentiveList";
    public static final String PROPERTY_RCPLRENTBILLLIST = "rCPLRentBillList";
    public static final String PROPERTY_RCPLSECURITYDEPOSITLIST = "rCPLSecurityDepositList";
    public static final String PROPERTY_RCPRTASKLINELIST = "rCPRTasklineList";
    public static final String PROPERTY_RCHRATTENDLINELIST = "rchrAttendLineList";
    public static final String PROPERTY_RCHRATTENDPRODLINELIST = "rchrAttendProdLineList";
    public static final String PROPERTY_RCHRATTENDANCETOLINELIST = "rchrAttendanceToLineList";
    public static final String PROPERTY_RCHRATTENDSTRENGTHLIST = "rchrAttendstrengthList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST = "rchrBanksalpaymentsapplnList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSCSLIST = "rchrBanksalpaymentscsList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSLNLIST = "rchrBanksalpaymentslnList";
    public static final String PROPERTY_RCHRBANKSALPAYMENTSOLLIST = "rchrBanksalpaymentsolList";
    public static final String PROPERTY_RCHRBIOCARDCONFIGLIST = "rchrBiocardconfigList";
    public static final String PROPERTY_RCHRDESIGEMPLIST = "rchrDesigEmpList";
    public static final String PROPERTY_RCHREMPBANKLIST = "rchrEmpBankList";
    public static final String PROPERTY_RCHREMPJOBLIST = "rchrEmpJobList";
    public static final String PROPERTY_RCHREMPLOYEESUPERVISORNAMELIST = "rchrEmployeeSupervisornameList";
    public static final String PROPERTY_RCHREMPLOYEEWEEKOFFSLIST = "rchrEmployeeweekoffsList";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST = "rchrExbanksalpaymentsempList";
    public static final String PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST = "rchrExbanksalpaymentsemplnList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";
    public static final String PROPERTY_RCHRMEMOSHIFTLIST = "rchrMemoShiftList";
    public static final String PROPERTY_RCHRONDUTYLIST = "rchrOndutyList";
    public static final String PROPERTY_RCHRROLLWISEDATALIST = "rchrRollwisedataList";
    public static final String PROPERTY_RCHRROOMEMPLOYEELIST = "rchrRoomEmployeeList";
    public static final String PROPERTY_RCHRSHIFTTWELVELIST = "rchrShifttwelveList";
    public static final String PROPERTY_RCHRSTRNGTHLNSLIST = "rchrStrngthlnsList";
    public static final String PROPERTY_RCPLEMPPPROCESSMANUALLIST = "rcplEmppprocessmanualList";
    public static final String PROPERTY_EPCGPPCENQUIRYLIST = "epcgPpcenquiryList";

    public RchrEmployee() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SHIFT, false);
        setDefaultValue(PROPERTY_ROOMALLOTTED, false);
        setDefaultValue(PROPERTY_LICAPPLICABLE, false);
        setDefaultValue(PROPERTY_WEEKOFFAPPLICABLE, false);
        setDefaultValue(PROPERTY_WEEKLYOFF, "N/A");
        setDefaultValue(PROPERTY_PFAPPLICABLE, false);
        setDefaultValue(PROPERTY_ESIAPPLICABLE, false);
        setDefaultValue(PROPERTY_VPF, false);
        setDefaultValue(PROPERTY_VPFAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PREATTDDAYS, (long) 0);
        setDefaultValue(PROPERTY_WITHHELD, false);
        setDefaultValue(PROPERTY_OBSERVICEDAYS, (long) 0);
        setDefaultValue(PROPERTY_ISBANK, false);
        setDefaultValue(PROPERTY_EMPLOYEESTATUS, "W");
        setDefaultValue(PROPERTY_SALARYSTATUS, "AP");
        setDefaultValue(PROPERTY_ISLEAVEAPPLICABLE, false);
        setDefaultValue(PROPERTY_ISCOFFAPPLICABLE, false);
        setDefaultValue(PROPERTY_ISODAPPLICABLE, false);
        setDefaultValue(PROPERTY_SENIOR, false);
        setDefaultValue(PROPERTY_HALFDAYAPPLICABLE, false);
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDEPARTMENTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDEPTSTOREELIGIBILTYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDIFAMILYLISTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRALLOTMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDTEMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRCERTIFICATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRCOMPENSATEOFFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDAILYATTENDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDAILYATTENDDEMOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDEALLOTMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRWARPBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRWARPBEAMASSTWARPEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRWARPBEAMASSTCREELEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPJOBINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLEAVEBALANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLEAVEDEPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOANLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOANSURETYEMPIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPPOLICYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPRELAYINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPWEEKOFFINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELEAVEBALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEECOFFSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEESETTLEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREXPERIENCEDETAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRFAMILYDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRFAMILYDETAILSRELATIONEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRGASADVANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPECTIONSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPECTIONSHEETDATAEOPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRISSUETOKENLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOBDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEOPENBALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLEAVEREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLOSSOFPAYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMOBALIZERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMOBALIZERINCLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHROVERTIMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHROVERTIMEPROCESSLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRPERMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRPOLICYSLABLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRPREPARATPRODINCNTIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRQUALIFICATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRRELAYSHIFTDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROOMRENTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSALADVANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSECURITYDEPOSITELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMBACKSIZERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMMIXERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGBEAMHELPERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRVEHICLEGATEPASSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRVISITORGATEPASSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRWEEKLYOFFDETAILSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLDEPARTMENTSTORELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLDEPRTMTSTOREREQUSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLELECTRICITYBILLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPFINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPAYROLLPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPSALSETUPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLFINELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLGASLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLMESSBILLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLOTHERDEDUCTIONSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPROCESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPROCESSSWLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPRODINCENTIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLRENTBILLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLSECURITYDEPOSITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRTASKLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDPRODLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDANCETOLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRATTENDSTRENGTHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSCSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKSALPAYMENTSOLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBIOCARDCONFIGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDESIGEMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPBANKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPJOBLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEESUPERVISORNAMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEEWEEKOFFSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRMEMOSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRONDUTYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROLLWISEDATALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROOMEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSHIFTTWELVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSTRNGTHLNSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLEMPPPROCESSMANUALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPPCENQUIRYLIST, new ArrayList<Object>());
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

    public String getEmployeeName() {
        return (String) get(PROPERTY_EMPLOYEENAME);
    }

    public void setEmployeeName(String employeeName) {
        set(PROPERTY_EMPLOYEENAME, employeeName);
    }

    public Image getImage() {
        return (Image) get(PROPERTY_IMAGE);
    }

    public void setImage(Image image) {
        set(PROPERTY_IMAGE, image);
    }

    public String getRotation() {
        return (String) get(PROPERTY_ROTATION);
    }

    public void setRotation(String rotation) {
        set(PROPERTY_ROTATION, rotation);
    }

    public Boolean isShift() {
        return (Boolean) get(PROPERTY_SHIFT);
    }

    public void setShift(Boolean shift) {
        set(PROPERTY_SHIFT, shift);
    }

    public Boolean isRoomAllotted() {
        return (Boolean) get(PROPERTY_ROOMALLOTTED);
    }

    public void setRoomAllotted(Boolean roomAllotted) {
        set(PROPERTY_ROOMALLOTTED, roomAllotted);
    }

    public RCHR_Room getRoom() {
        return (RCHR_Room) get(PROPERTY_ROOM);
    }

    public void setRoom(RCHR_Room room) {
        set(PROPERTY_ROOM, room);
    }

    public Boolean isLICApplicable() {
        return (Boolean) get(PROPERTY_LICAPPLICABLE);
    }

    public void setLICApplicable(Boolean lICApplicable) {
        set(PROPERTY_LICAPPLICABLE, lICApplicable);
    }

    public Boolean isWeekOffApplicable() {
        return (Boolean) get(PROPERTY_WEEKOFFAPPLICABLE);
    }

    public void setWeekOffApplicable(Boolean weekOffApplicable) {
        set(PROPERTY_WEEKOFFAPPLICABLE, weekOffApplicable);
    }

    public String getWeeklyOff() {
        return (String) get(PROPERTY_WEEKLYOFF);
    }

    public void setWeeklyOff(String weeklyOff) {
        set(PROPERTY_WEEKLYOFF, weeklyOff);
    }

    public String getEmail() {
        return (String) get(PROPERTY_EMAIL);
    }

    public void setEmail(String email) {
        set(PROPERTY_EMAIL, email);
    }

    public String getPhoneNo() {
        return (String) get(PROPERTY_PHONENO);
    }

    public void setPhoneNo(String phoneNo) {
        set(PROPERTY_PHONENO, phoneNo);
    }

    public String getLandLineNo() {
        return (String) get(PROPERTY_LANDLINENO);
    }

    public void setLandLineNo(String landLineNo) {
        set(PROPERTY_LANDLINENO, landLineNo);
    }

    public String getEmergencyPhoneNo() {
        return (String) get(PROPERTY_EMERGENCYPHONENO);
    }

    public void setEmergencyPhoneNo(String emergencyPhoneNo) {
        set(PROPERTY_EMERGENCYPHONENO, emergencyPhoneNo);
    }

    public String getGender() {
        return (String) get(PROPERTY_GENDER);
    }

    public void setGender(String gender) {
        set(PROPERTY_GENDER, gender);
    }

    public String getMaritalStatus() {
        return (String) get(PROPERTY_MARITALSTATUS);
    }

    public void setMaritalStatus(String maritalStatus) {
        set(PROPERTY_MARITALSTATUS, maritalStatus);
    }

    public Date getDateOfBirth() {
        return (Date) get(PROPERTY_DATEOFBIRTH);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        set(PROPERTY_DATEOFBIRTH, dateOfBirth);
    }

    public String getPANNo() {
        return (String) get(PROPERTY_PANNO);
    }

    public void setPANNo(String pANNo) {
        set(PROPERTY_PANNO, pANNo);
    }

    public String getESINo() {
        return (String) get(PROPERTY_ESINO);
    }

    public void setESINo(String eSINo) {
        set(PROPERTY_ESINO, eSINo);
    }

    public String getEPFNo() {
        return (String) get(PROPERTY_EPFNO);
    }

    public void setEPFNo(String ePFNo) {
        set(PROPERTY_EPFNO, ePFNo);
    }

    public String getAdharNo() {
        return (String) get(PROPERTY_ADHARNO);
    }

    public void setAdharNo(String adharNo) {
        set(PROPERTY_ADHARNO, adharNo);
    }

    public Boolean isPFApplicable() {
        return (Boolean) get(PROPERTY_PFAPPLICABLE);
    }

    public void setPFApplicable(Boolean pFApplicable) {
        set(PROPERTY_PFAPPLICABLE, pFApplicable);
    }

    public Boolean isESIApplicable() {
        return (Boolean) get(PROPERTY_ESIAPPLICABLE);
    }

    public void setESIApplicable(Boolean eSIApplicable) {
        set(PROPERTY_ESIAPPLICABLE, eSIApplicable);
    }

    public String getEmployeeType() {
        return (String) get(PROPERTY_EMPLOYEETYPE);
    }

    public void setEmployeeType(String employeeType) {
        set(PROPERTY_EMPLOYEETYPE, employeeType);
    }

    public String getFirstName() {
        return (String) get(PROPERTY_FIRSTNAME);
    }

    public void setFirstName(String firstName) {
        set(PROPERTY_FIRSTNAME, firstName);
    }

    public String getLastName() {
        return (String) get(PROPERTY_LASTNAME);
    }

    public void setLastName(String lastName) {
        set(PROPERTY_LASTNAME, lastName);
    }

    public String getSurName() {
        return (String) get(PROPERTY_SURNAME);
    }

    public void setSurName(String surName) {
        set(PROPERTY_SURNAME, surName);
    }

    public RchrMRelay getRelay() {
        return (RchrMRelay) get(PROPERTY_RELAY);
    }

    public void setRelay(RchrMRelay relay) {
        set(PROPERTY_RELAY, relay);
    }

    public RCHR_AgentMaster getAgentMaster() {
        return (RCHR_AgentMaster) get(PROPERTY_AGENTMASTER);
    }

    public void setAgentMaster(RCHR_AgentMaster agentMaster) {
        set(PROPERTY_AGENTMASTER, agentMaster);
    }

    public Boolean isVPF() {
        return (Boolean) get(PROPERTY_VPF);
    }

    public void setVPF(Boolean vPF) {
        set(PROPERTY_VPF, vPF);
    }

    public BigDecimal getVPFAmount() {
        return (BigDecimal) get(PROPERTY_VPFAMOUNT);
    }

    public void setVPFAmount(BigDecimal vPFAmount) {
        set(PROPERTY_VPFAMOUNT, vPFAmount);
    }

    public Long getPreattddays() {
        return (Long) get(PROPERTY_PREATTDDAYS);
    }

    public void setPreattddays(Long preattddays) {
        set(PROPERTY_PREATTDDAYS, preattddays);
    }

    public String getPunchno() {
        return (String) get(PROPERTY_PUNCHNO);
    }

    public void setPunchno(String punchno) {
        set(PROPERTY_PUNCHNO, punchno);
    }

    public RchrEmployee getSupervisorname() {
        return (RchrEmployee) get(PROPERTY_SUPERVISORNAME);
    }

    public void setSupervisorname(RchrEmployee supervisorname) {
        set(PROPERTY_SUPERVISORNAME, supervisorname);
    }

    public RchrEmpDept getEmployeeDepartment() {
        return (RchrEmpDept) get(PROPERTY_EMPLOYEEDEPARTMENT);
    }

    public void setEmployeeDepartment(RchrEmpDept employeeDepartment) {
        set(PROPERTY_EMPLOYEEDEPARTMENT, employeeDepartment);
    }

    public RCHR_SubDepartment getSubDepartment() {
        return (RCHR_SubDepartment) get(PROPERTY_SUBDEPARTMENT);
    }

    public void setSubDepartment(RCHR_SubDepartment subDepartment) {
        set(PROPERTY_SUBDEPARTMENT, subDepartment);
    }

    public RchrDesignation getDesignation() {
        return (RchrDesignation) get(PROPERTY_DESIGNATION);
    }

    public void setDesignation(RchrDesignation designation) {
        set(PROPERTY_DESIGNATION, designation);
    }

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public String getJobType() {
        return (String) get(PROPERTY_JOBTYPE);
    }

    public void setJobType(String jobType) {
        set(PROPERTY_JOBTYPE, jobType);
    }

    public RchrShiftgroup getRchrShiftgroup() {
        return (RchrShiftgroup) get(PROPERTY_RCHRSHIFTGROUP);
    }

    public void setRchrShiftgroup(RchrShiftgroup rchrShiftgroup) {
        set(PROPERTY_RCHRSHIFTGROUP, rchrShiftgroup);
    }

    public Boolean isWithheld() {
        return (Boolean) get(PROPERTY_WITHHELD);
    }

    public void setWithheld(Boolean withheld) {
        set(PROPERTY_WITHHELD, withheld);
    }

    public Long getObservicedays() {
        return (Long) get(PROPERTY_OBSERVICEDAYS);
    }

    public void setObservicedays(Long observicedays) {
        set(PROPERTY_OBSERVICEDAYS, observicedays);
    }

    public Boolean isBank() {
        return (Boolean) get(PROPERTY_ISBANK);
    }

    public void setBank(Boolean isbank) {
        set(PROPERTY_ISBANK, isbank);
    }

    public String getAccountNo() {
        return (String) get(PROPERTY_ACCOUNTNO);
    }

    public void setAccountNo(String accountNo) {
        set(PROPERTY_ACCOUNTNO, accountNo);
    }

    public RCHRMobalizer getRchrMobalizer() {
        return (RCHRMobalizer) get(PROPERTY_RCHRMOBALIZER);
    }

    public void setRchrMobalizer(RCHRMobalizer rchrMobalizer) {
        set(PROPERTY_RCHRMOBALIZER, rchrMobalizer);
    }

    public String getPftype() {
        return (String) get(PROPERTY_PFTYPE);
    }

    public void setPftype(String pftype) {
        set(PROPERTY_PFTYPE, pftype);
    }

    public String getEmployeestatus() {
        return (String) get(PROPERTY_EMPLOYEESTATUS);
    }

    public void setEmployeestatus(String employeestatus) {
        set(PROPERTY_EMPLOYEESTATUS, employeestatus);
    }

    public String getSalarystatus() {
        return (String) get(PROPERTY_SALARYSTATUS);
    }

    public void setSalarystatus(String salarystatus) {
        set(PROPERTY_SALARYSTATUS, salarystatus);
    }

    public Boolean isLeaveapplicable() {
        return (Boolean) get(PROPERTY_ISLEAVEAPPLICABLE);
    }

    public void setLeaveapplicable(Boolean isleaveapplicable) {
        set(PROPERTY_ISLEAVEAPPLICABLE, isleaveapplicable);
    }

    public Boolean isCoffapplicable() {
        return (Boolean) get(PROPERTY_ISCOFFAPPLICABLE);
    }

    public void setCoffapplicable(Boolean iscoffapplicable) {
        set(PROPERTY_ISCOFFAPPLICABLE, iscoffapplicable);
    }

    public Boolean isOdapplicable() {
        return (Boolean) get(PROPERTY_ISODAPPLICABLE);
    }

    public void setOdapplicable(Boolean isodapplicable) {
        set(PROPERTY_ISODAPPLICABLE, isodapplicable);
    }

    public RCHRLeaveTemplate getRchrLeavetemplate() {
        return (RCHRLeaveTemplate) get(PROPERTY_RCHRLEAVETEMPLATE);
    }

    public void setRchrLeavetemplate(RCHRLeaveTemplate rchrLeavetemplate) {
        set(PROPERTY_RCHRLEAVETEMPLATE, rchrLeavetemplate);
    }

    public RchrEmpDept getLeavedepartment() {
        return (RchrEmpDept) get(PROPERTY_LEAVEDEPARTMENT);
    }

    public void setLeavedepartment(RchrEmpDept leavedepartment) {
        set(PROPERTY_LEAVEDEPARTMENT, leavedepartment);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public RchrJoinRejoiningform getRchrJoinRejoiningform() {
        return (RchrJoinRejoiningform) get(PROPERTY_RCHRJOINREJOININGFORM);
    }

    public void setRchrJoinRejoiningform(RchrJoinRejoiningform rchrJoinRejoiningform) {
        set(PROPERTY_RCHRJOINREJOININGFORM, rchrJoinRejoiningform);
    }

    public BigDecimal getActualservicedays() {
        return (BigDecimal) get(PROPERTY_ACTUALSERVICEDAYS);
    }

    public void setActualservicedays(BigDecimal actualservicedays) {
        set(PROPERTY_ACTUALSERVICEDAYS, actualservicedays);
    }

    public Boolean isSenior() {
        return (Boolean) get(PROPERTY_SENIOR);
    }

    public void setSenior(Boolean senior) {
        set(PROPERTY_SENIOR, senior);
    }

    public Boolean isHalfdayapplicable() {
        return (Boolean) get(PROPERTY_HALFDAYAPPLICABLE);
    }

    public void setHalfdayapplicable(Boolean halfdayapplicable) {
        set(PROPERTY_HALFDAYAPPLICABLE, halfdayapplicable);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDepartmentIssue> getRCGIDepartmentIssueList() {
        return (List<RCGIDepartmentIssue>) get(PROPERTY_RCGIDEPARTMENTISSUELIST);
    }

    public void setRCGIDepartmentIssueList(List<RCGIDepartmentIssue> rCGIDepartmentIssueList) {
        set(PROPERTY_RCGIDEPARTMENTISSUELIST, rCGIDepartmentIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDeptStoreEligibilty> getRCGIDeptStoreEligibiltyList() {
        return (List<RCGIDeptStoreEligibilty>) get(PROPERTY_RCGIDEPTSTOREELIGIBILTYLIST);
    }

    public void setRCGIDeptStoreEligibiltyList(List<RCGIDeptStoreEligibilty> rCGIDeptStoreEligibiltyList) {
        set(PROPERTY_RCGIDEPTSTOREELIGIBILTYLIST, rCGIDeptStoreEligibiltyList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDiFamilyList> getRCGIDiFamilyListList() {
        return (List<RCGIDiFamilyList>) get(PROPERTY_RCGIDIFAMILYLISTLIST);
    }

    public void setRCGIDiFamilyListList(List<RCGIDiFamilyList> rCGIDiFamilyListList) {
        set(PROPERTY_RCGIDIFAMILYLISTLIST, rCGIDiFamilyListList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Allotment> getRCHRAllotmentList() {
        return (List<RCHR_Allotment>) get(PROPERTY_RCHRALLOTMENTLIST);
    }

    public void setRCHRAllotmentList(List<RCHR_Allotment> rCHRAllotmentList) {
        set(PROPERTY_RCHRALLOTMENTLIST, rCHRAllotmentList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Attend_Temp> getRCHRAttendTempList() {
        return (List<RCHR_Attend_Temp>) get(PROPERTY_RCHRATTENDTEMPLIST);
    }

    public void setRCHRAttendTempList(List<RCHR_Attend_Temp> rCHRAttendTempList) {
        set(PROPERTY_RCHRATTENDTEMPLIST, rCHRAttendTempList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Certificate> getRCHRCertificateList() {
        return (List<RCHR_Certificate>) get(PROPERTY_RCHRCERTIFICATELIST);
    }

    public void setRCHRCertificateList(List<RCHR_Certificate> rCHRCertificateList) {
        set(PROPERTY_RCHRCERTIFICATELIST, rCHRCertificateList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrCompensateOff> getRCHRCompensateOffList() {
        return (List<RchrCompensateOff>) get(PROPERTY_RCHRCOMPENSATEOFFLIST);
    }

    public void setRCHRCompensateOffList(List<RchrCompensateOff> rCHRCompensateOffList) {
        set(PROPERTY_RCHRCOMPENSATEOFFLIST, rCHRCompensateOffList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRDailyattend> getRCHRDailyattendList() {
        return (List<RCHRDailyattend>) get(PROPERTY_RCHRDAILYATTENDLIST);
    }

    public void setRCHRDailyattendList(List<RCHRDailyattend> rCHRDailyattendList) {
        set(PROPERTY_RCHRDAILYATTENDLIST, rCHRDailyattendList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRDailyattenddemo> getRCHRDailyattenddemoList() {
        return (List<RCHRDailyattenddemo>) get(PROPERTY_RCHRDAILYATTENDDEMOLIST);
    }

    public void setRCHRDailyattenddemoList(List<RCHRDailyattenddemo> rCHRDailyattenddemoList) {
        set(PROPERTY_RCHRDAILYATTENDDEMOLIST, rCHRDailyattenddemoList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_DeAllotment> getRCHRDeAllotmentList() {
        return (List<RCHR_DeAllotment>) get(PROPERTY_RCHRDEALLOTMENTLIST);
    }

    public void setRCHRDeAllotmentList(List<RCHR_DeAllotment> rCHRDeAllotmentList) {
        set(PROPERTY_RCHRDEALLOTMENTLIST, rCHRDeAllotmentList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Dirwarp_Beam> getRCHRDirwarpBeamList() {
        return (List<RCHR_Dirwarp_Beam>) get(PROPERTY_RCHRDIRWARPBEAMLIST);
    }

    public void setRCHRDirwarpBeamList(List<RCHR_Dirwarp_Beam> rCHRDirwarpBeamList) {
        set(PROPERTY_RCHRDIRWARPBEAMLIST, rCHRDirwarpBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Dirwarp_Beam> getRCHRDirwarpBeamAsstwarpemployeeList() {
        return (List<RCHR_Dirwarp_Beam>) get(PROPERTY_RCHRDIRWARPBEAMASSTWARPEMPLOYEELIST);
    }

    public void setRCHRDirwarpBeamAsstwarpemployeeList(List<RCHR_Dirwarp_Beam> rCHRDirwarpBeamAsstwarpemployeeList) {
        set(PROPERTY_RCHRDIRWARPBEAMASSTWARPEMPLOYEELIST, rCHRDirwarpBeamAsstwarpemployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Dirwarp_Beam> getRCHRDirwarpBeamAsstcreelemployeeList() {
        return (List<RCHR_Dirwarp_Beam>) get(PROPERTY_RCHRDIRWARPBEAMASSTCREELEMPLOYEELIST);
    }

    public void setRCHRDirwarpBeamAsstcreelemployeeList(List<RCHR_Dirwarp_Beam> rCHRDirwarpBeamAsstcreelemployeeList) {
        set(PROPERTY_RCHRDIRWARPBEAMASSTCREELEMPLOYEELIST, rCHRDirwarpBeamAsstcreelemployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_EmpAddress> getRCHREmpAddressList() {
        return (List<RCHR_EmpAddress>) get(PROPERTY_RCHREMPADDRESSLIST);
    }

    public void setRCHREmpAddressList(List<RCHR_EmpAddress> rCHREmpAddressList) {
        set(PROPERTY_RCHREMPADDRESSLIST, rCHREmpAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHREmpJobInfo> getRCHREmpJobInfoList() {
        return (List<RCHREmpJobInfo>) get(PROPERTY_RCHREMPJOBINFOLIST);
    }

    public void setRCHREmpJobInfoList(List<RCHREmpJobInfo> rCHREmpJobInfoList) {
        set(PROPERTY_RCHREMPJOBINFOLIST, rCHREmpJobInfoList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHREmpLeaveBalance> getRCHREmpLeaveBalanceList() {
        return (List<RCHREmpLeaveBalance>) get(PROPERTY_RCHREMPLEAVEBALANCELIST);
    }

    public void setRCHREmpLeaveBalanceList(List<RCHREmpLeaveBalance> rCHREmpLeaveBalanceList) {
        set(PROPERTY_RCHREMPLEAVEBALANCELIST, rCHREmpLeaveBalanceList);
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
    public List<RCHR_Emp_Loan> getRCHREmpLoanSuretyempidList() {
        return (List<RCHR_Emp_Loan>) get(PROPERTY_RCHREMPLOANSURETYEMPIDLIST);
    }

    public void setRCHREmpLoanSuretyempidList(List<RCHR_Emp_Loan> rCHREmpLoanSuretyempidList) {
        set(PROPERTY_RCHREMPLOANSURETYEMPIDLIST, rCHREmpLoanSuretyempidList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Emp_Policy> getRCHREmpPolicyList() {
        return (List<RCHR_Emp_Policy>) get(PROPERTY_RCHREMPPOLICYLIST);
    }

    public void setRCHREmpPolicyList(List<RCHR_Emp_Policy> rCHREmpPolicyList) {
        set(PROPERTY_RCHREMPPOLICYLIST, rCHREmpPolicyList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmpRelayInfo> getRCHREmpRelayInfoList() {
        return (List<RchrEmpRelayInfo>) get(PROPERTY_RCHREMPRELAYINFOLIST);
    }

    public void setRCHREmpRelayInfoList(List<RchrEmpRelayInfo> rCHREmpRelayInfoList) {
        set(PROPERTY_RCHREMPRELAYINFOLIST, rCHREmpRelayInfoList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHREmpWeekoffInfo> getRCHREmpWeekoffInfoList() {
        return (List<RCHREmpWeekoffInfo>) get(PROPERTY_RCHREMPWEEKOFFINFOLIST);
    }

    public void setRCHREmpWeekoffInfoList(List<RCHREmpWeekoffInfo> rCHREmpWeekoffInfoList) {
        set(PROPERTY_RCHREMPWEEKOFFINFOLIST, rCHREmpWeekoffInfoList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployeeleaveBal> getRCHREmployeeLeaveBalList() {
        return (List<RchrEmployeeleaveBal>) get(PROPERTY_RCHREMPLOYEELEAVEBALLIST);
    }

    public void setRCHREmployeeLeaveBalList(List<RchrEmployeeleaveBal> rCHREmployeeLeaveBalList) {
        set(PROPERTY_RCHREMPLOYEELEAVEBALLIST, rCHREmployeeLeaveBalList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployeecoffs> getRCHREmployeecoffsList() {
        return (List<RchrEmployeecoffs>) get(PROPERTY_RCHREMPLOYEECOFFSLIST);
    }

    public void setRCHREmployeecoffsList(List<RchrEmployeecoffs> rCHREmployeecoffsList) {
        set(PROPERTY_RCHREMPLOYEECOFFSLIST, rCHREmployeecoffsList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Employeesettlement> getRCHREmployeesettlementList() {
        return (List<RCHR_Employeesettlement>) get(PROPERTY_RCHREMPLOYEESETTLEMENTLIST);
    }

    public void setRCHREmployeesettlementList(List<RCHR_Employeesettlement> rCHREmployeesettlementList) {
        set(PROPERTY_RCHREMPLOYEESETTLEMENTLIST, rCHREmployeesettlementList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_ExperienceDetail> getRCHRExperienceDetailList() {
        return (List<RCHR_ExperienceDetail>) get(PROPERTY_RCHREXPERIENCEDETAILLIST);
    }

    public void setRCHRExperienceDetailList(List<RCHR_ExperienceDetail> rCHRExperienceDetailList) {
        set(PROPERTY_RCHREXPERIENCEDETAILLIST, rCHRExperienceDetailList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_FamilyDetails> getRCHRFamilyDetailsList() {
        return (List<RCHR_FamilyDetails>) get(PROPERTY_RCHRFAMILYDETAILSLIST);
    }

    public void setRCHRFamilyDetailsList(List<RCHR_FamilyDetails> rCHRFamilyDetailsList) {
        set(PROPERTY_RCHRFAMILYDETAILSLIST, rCHRFamilyDetailsList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_FamilyDetails> getRCHRFamilyDetailsRelationEmployeeList() {
        return (List<RCHR_FamilyDetails>) get(PROPERTY_RCHRFAMILYDETAILSRELATIONEMPLOYEELIST);
    }

    public void setRCHRFamilyDetailsRelationEmployeeList(List<RCHR_FamilyDetails> rCHRFamilyDetailsRelationEmployeeList) {
        set(PROPERTY_RCHRFAMILYDETAILSRELATIONEMPLOYEELIST, rCHRFamilyDetailsRelationEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRGasAdvance> getRCHRGasAdvanceList() {
        return (List<RCHRGasAdvance>) get(PROPERTY_RCHRGASADVANCELIST);
    }

    public void setRCHRGasAdvanceList(List<RCHRGasAdvance> rCHRGasAdvanceList) {
        set(PROPERTY_RCHRGASADVANCELIST, rCHRGasAdvanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspectionsheet> getRCHRInspectionsheetList() {
        return (List<RCHR_Inspectionsheet>) get(PROPERTY_RCHRINSPECTIONSHEETLIST);
    }

    public void setRCHRInspectionsheetList(List<RCHR_Inspectionsheet> rCHRInspectionsheetList) {
        set(PROPERTY_RCHRINSPECTIONSHEETLIST, rCHRInspectionsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspectionsheet> getRCHRInspectionsheetDataeopList() {
        return (List<RCHR_Inspectionsheet>) get(PROPERTY_RCHRINSPECTIONSHEETDATAEOPLIST);
    }

    public void setRCHRInspectionsheetDataeopList(List<RCHR_Inspectionsheet> rCHRInspectionsheetDataeopList) {
        set(PROPERTY_RCHRINSPECTIONSHEETDATAEOPLIST, rCHRInspectionsheetDataeopList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_IssueToken> getRCHRIssueTokenList() {
        return (List<RCHR_IssueToken>) get(PROPERTY_RCHRISSUETOKENLIST);
    }

    public void setRCHRIssueTokenList(List<RCHR_IssueToken> rCHRIssueTokenList) {
        set(PROPERTY_RCHRISSUETOKENLIST, rCHRIssueTokenList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRJobDetails> getRCHRJobDetailsList() {
        return (List<RCHRJobDetails>) get(PROPERTY_RCHRJOBDETAILSLIST);
    }

    public void setRCHRJobDetailsList(List<RCHRJobDetails> rCHRJobDetailsList) {
        set(PROPERTY_RCHRJOBDETAILSLIST, rCHRJobDetailsList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRLeaveBalanceHistory> getRCHRLeaveBalanceHistoryList() {
        return (List<RCHRLeaveBalanceHistory>) get(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST);
    }

    public void setRCHRLeaveBalanceHistoryList(List<RCHRLeaveBalanceHistory> rCHRLeaveBalanceHistoryList) {
        set(PROPERTY_RCHRLEAVEBALANCEHISTORYLIST, rCHRLeaveBalanceHistoryList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_LeaveOpenBalance> getRCHRLeaveOpenBalList() {
        return (List<RCHR_LeaveOpenBalance>) get(PROPERTY_RCHRLEAVEOPENBALLIST);
    }

    public void setRCHRLeaveOpenBalList(List<RCHR_LeaveOpenBalance> rCHRLeaveOpenBalList) {
        set(PROPERTY_RCHRLEAVEOPENBALLIST, rCHRLeaveOpenBalList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_LeaveRequisition> getRCHRLeaveRequisitionList() {
        return (List<RCHR_LeaveRequisition>) get(PROPERTY_RCHRLEAVEREQUISITIONLIST);
    }

    public void setRCHRLeaveRequisitionList(List<RCHR_LeaveRequisition> rCHRLeaveRequisitionList) {
        set(PROPERTY_RCHRLEAVEREQUISITIONLIST, rCHRLeaveRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrLossOfPay> getRCHRLossOfPayList() {
        return (List<RchrLossOfPay>) get(PROPERTY_RCHRLOSSOFPAYLIST);
    }

    public void setRCHRLossOfPayList(List<RchrLossOfPay> rCHRLossOfPayList) {
        set(PROPERTY_RCHRLOSSOFPAYLIST, rCHRLossOfPayList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRMobalizer> getRCHRMobalizerList() {
        return (List<RCHRMobalizer>) get(PROPERTY_RCHRMOBALIZERLIST);
    }

    public void setRCHRMobalizerList(List<RCHRMobalizer> rCHRMobalizerList) {
        set(PROPERTY_RCHRMOBALIZERLIST, rCHRMobalizerList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRMobalizerinclines> getRCHRMobalizerinclinesList() {
        return (List<RCHRMobalizerinclines>) get(PROPERTY_RCHRMOBALIZERINCLINESLIST);
    }

    public void setRCHRMobalizerinclinesList(List<RCHRMobalizerinclines> rCHRMobalizerinclinesList) {
        set(PROPERTY_RCHRMOBALIZERINCLINESLIST, rCHRMobalizerinclinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_OverTime> getRCHROverTimeList() {
        return (List<RCHR_OverTime>) get(PROPERTY_RCHROVERTIMELIST);
    }

    public void setRCHROverTimeList(List<RCHR_OverTime> rCHROverTimeList) {
        set(PROPERTY_RCHROVERTIMELIST, rCHROverTimeList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrOvertimeprocessLine> getRCHROvertimeprocessLineList() {
        return (List<RchrOvertimeprocessLine>) get(PROPERTY_RCHROVERTIMEPROCESSLINELIST);
    }

    public void setRCHROvertimeprocessLineList(List<RchrOvertimeprocessLine> rCHROvertimeprocessLineList) {
        set(PROPERTY_RCHROVERTIMEPROCESSLINELIST, rCHROvertimeprocessLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRPermission> getRCHRPermissionList() {
        return (List<RCHRPermission>) get(PROPERTY_RCHRPERMISSIONLIST);
    }

    public void setRCHRPermissionList(List<RCHRPermission> rCHRPermissionList) {
        set(PROPERTY_RCHRPERMISSIONLIST, rCHRPermissionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_PolicySlab> getRCHRPolicySlabList() {
        return (List<RCHR_PolicySlab>) get(PROPERTY_RCHRPOLICYSLABLIST);
    }

    public void setRCHRPolicySlabList(List<RCHR_PolicySlab> rCHRPolicySlabList) {
        set(PROPERTY_RCHRPOLICYSLABLIST, rCHRPolicySlabList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrPreparatprodincntive> getRCHRPreparatprodincntiveList() {
        return (List<RchrPreparatprodincntive>) get(PROPERTY_RCHRPREPARATPRODINCNTIVELIST);
    }

    public void setRCHRPreparatprodincntiveList(List<RchrPreparatprodincntive> rCHRPreparatprodincntiveList) {
        set(PROPERTY_RCHRPREPARATPRODINCNTIVELIST, rCHRPreparatprodincntiveList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Qualification> getRCHRQualificationList() {
        return (List<RCHR_Qualification>) get(PROPERTY_RCHRQUALIFICATIONLIST);
    }

    public void setRCHRQualificationList(List<RCHR_Qualification> rCHRQualificationList) {
        set(PROPERTY_RCHRQUALIFICATIONLIST, rCHRQualificationList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRelayShiftDetails> getRCHRRelayShiftDetailsList() {
        return (List<RchrRelayShiftDetails>) get(PROPERTY_RCHRRELAYSHIFTDETAILSLIST);
    }

    public void setRCHRRelayShiftDetailsList(List<RchrRelayShiftDetails> rCHRRelayShiftDetailsList) {
        set(PROPERTY_RCHRRELAYSHIFTDETAILSLIST, rCHRRelayShiftDetailsList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRoomRentLines> getRCHRRoomRentLinesList() {
        return (List<RchrRoomRentLines>) get(PROPERTY_RCHRROOMRENTLINESLIST);
    }

    public void setRCHRRoomRentLinesList(List<RchrRoomRentLines> rCHRRoomRentLinesList) {
        set(PROPERTY_RCHRROOMRENTLINESLIST, rCHRRoomRentLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRSaladvance> getRCHRSaladvanceList() {
        return (List<RCHRSaladvance>) get(PROPERTY_RCHRSALADVANCELIST);
    }

    public void setRCHRSaladvanceList(List<RCHRSaladvance> rCHRSaladvanceList) {
        set(PROPERTY_RCHRSALADVANCELIST, rCHRSaladvanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRSecuritydeposite> getRCHRSecuritydepositeList() {
        return (List<RCHRSecuritydeposite>) get(PROPERTY_RCHRSECURITYDEPOSITELIST);
    }

    public void setRCHRSecuritydepositeList(List<RCHRSecuritydeposite> rCHRSecuritydepositeList) {
        set(PROPERTY_RCHRSECURITYDEPOSITELIST, rCHRSecuritydepositeList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizing_Beam> getRCHRSizingBeamList() {
        return (List<RCHR_Sizing_Beam>) get(PROPERTY_RCHRSIZINGBEAMLIST);
    }

    public void setRCHRSizingBeamList(List<RCHR_Sizing_Beam> rCHRSizingBeamList) {
        set(PROPERTY_RCHRSIZINGBEAMLIST, rCHRSizingBeamList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizing_Beam> getRCHRSizingBeamBacksizerList() {
        return (List<RCHR_Sizing_Beam>) get(PROPERTY_RCHRSIZINGBEAMBACKSIZERLIST);
    }

    public void setRCHRSizingBeamBacksizerList(List<RCHR_Sizing_Beam> rCHRSizingBeamBacksizerList) {
        set(PROPERTY_RCHRSIZINGBEAMBACKSIZERLIST, rCHRSizingBeamBacksizerList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizing_Beam> getRCHRSizingBeamMixerList() {
        return (List<RCHR_Sizing_Beam>) get(PROPERTY_RCHRSIZINGBEAMMIXERLIST);
    }

    public void setRCHRSizingBeamMixerList(List<RCHR_Sizing_Beam> rCHRSizingBeamMixerList) {
        set(PROPERTY_RCHRSIZINGBEAMMIXERLIST, rCHRSizingBeamMixerList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizing_Beam> getRCHRSizingBeamHelperList() {
        return (List<RCHR_Sizing_Beam>) get(PROPERTY_RCHRSIZINGBEAMHELPERLIST);
    }

    public void setRCHRSizingBeamHelperList(List<RCHR_Sizing_Beam> rCHRSizingBeamHelperList) {
        set(PROPERTY_RCHRSIZINGBEAMHELPERLIST, rCHRSizingBeamHelperList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRVehiclegatepass> getRCHRVehiclegatepassList() {
        return (List<RCHRVehiclegatepass>) get(PROPERTY_RCHRVEHICLEGATEPASSLIST);
    }

    public void setRCHRVehiclegatepassList(List<RCHRVehiclegatepass> rCHRVehiclegatepassList) {
        set(PROPERTY_RCHRVEHICLEGATEPASSLIST, rCHRVehiclegatepassList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRVisitorgatepass> getRCHRVisitorgatepassList() {
        return (List<RCHRVisitorgatepass>) get(PROPERTY_RCHRVISITORGATEPASSLIST);
    }

    public void setRCHRVisitorgatepassList(List<RCHRVisitorgatepass> rCHRVisitorgatepassList) {
        set(PROPERTY_RCHRVISITORGATEPASSLIST, rCHRVisitorgatepassList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrWeeklyOffDetails> getRCHRWeeklyOffDetailsList() {
        return (List<RchrWeeklyOffDetails>) get(PROPERTY_RCHRWEEKLYOFFDETAILSLIST);
    }

    public void setRCHRWeeklyOffDetailsList(List<RchrWeeklyOffDetails> rCHRWeeklyOffDetailsList) {
        set(PROPERTY_RCHRWEEKLYOFFDETAILSLIST, rCHRWeeklyOffDetailsList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplDepartmentstore> getRCPLDepartmentStoreList() {
        return (List<RcplDepartmentstore>) get(PROPERTY_RCPLDEPARTMENTSTORELIST);
    }

    public void setRCPLDepartmentStoreList(List<RcplDepartmentstore> rCPLDepartmentStoreList) {
        set(PROPERTY_RCPLDEPARTMENTSTORELIST, rCPLDepartmentStoreList);
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
    public List<RCPL_EmpSalSetup> getRCPLEmpSalSetupList() {
        return (List<RCPL_EmpSalSetup>) get(PROPERTY_RCPLEMPSALSETUPLIST);
    }

    public void setRCPLEmpSalSetupList(List<RCPL_EmpSalSetup> rCPLEmpSalSetupList) {
        set(PROPERTY_RCPLEMPSALSETUPLIST, rCPLEmpSalSetupList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_FineLine> getRCPLFinelineList() {
        return (List<RCPL_FineLine>) get(PROPERTY_RCPLFINELINELIST);
    }

    public void setRCPLFinelineList(List<RCPL_FineLine> rCPLFinelineList) {
        set(PROPERTY_RCPLFINELINELIST, rCPLFinelineList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplGas> getRCPLGasList() {
        return (List<RcplGas>) get(PROPERTY_RCPLGASLIST);
    }

    public void setRCPLGasList(List<RcplGas> rCPLGasList) {
        set(PROPERTY_RCPLGASLIST, rCPLGasList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLMessBill> getRCPLMessBillList() {
        return (List<RCPLMessBill>) get(PROPERTY_RCPLMESSBILLLIST);
    }

    public void setRCPLMessBillList(List<RCPLMessBill> rCPLMessBillList) {
        set(PROPERTY_RCPLMESSBILLLIST, rCPLMessBillList);
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
    public List<RCPLPayrollpaidprocline> getRCPLPayrollpaidproclineList() {
        return (List<RCPLPayrollpaidprocline>) get(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST);
    }

    public void setRCPLPayrollpaidproclineList(List<RCPLPayrollpaidprocline> rCPLPayrollpaidproclineList) {
        set(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST, rCPLPayrollpaidproclineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPL_ProcessSWLine> getRCPLProcessswlineList() {
        return (List<RCPL_ProcessSWLine>) get(PROPERTY_RCPLPROCESSSWLINELIST);
    }

    public void setRCPLProcessswlineList(List<RCPL_ProcessSWLine> rCPLProcessswlineList) {
        set(PROPERTY_RCPLPROCESSSWLINELIST, rCPLProcessswlineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLProdIncentive> getRCPLProdIncentiveList() {
        return (List<RCPLProdIncentive>) get(PROPERTY_RCPLPRODINCENTIVELIST);
    }

    public void setRCPLProdIncentiveList(List<RCPLProdIncentive> rCPLProdIncentiveList) {
        set(PROPERTY_RCPLPRODINCENTIVELIST, rCPLProdIncentiveList);
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
    public List<RCPR_Taskline> getRCPRTasklineList() {
        return (List<RCPR_Taskline>) get(PROPERTY_RCPRTASKLINELIST);
    }

    public void setRCPRTasklineList(List<RCPR_Taskline> rCPRTasklineList) {
        set(PROPERTY_RCPRTASKLINELIST, rCPRTasklineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendLine> getRchrAttendLineList() {
        return (List<RchrAttendLine>) get(PROPERTY_RCHRATTENDLINELIST);
    }

    public void setRchrAttendLineList(List<RchrAttendLine> rchrAttendLineList) {
        set(PROPERTY_RCHRATTENDLINELIST, rchrAttendLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendProdLine> getRchrAttendProdLineList() {
        return (List<RchrAttendProdLine>) get(PROPERTY_RCHRATTENDPRODLINELIST);
    }

    public void setRchrAttendProdLineList(List<RchrAttendProdLine> rchrAttendProdLineList) {
        set(PROPERTY_RCHRATTENDPRODLINELIST, rchrAttendProdLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendanceToLine> getRchrAttendanceToLineList() {
        return (List<RchrAttendanceToLine>) get(PROPERTY_RCHRATTENDANCETOLINELIST);
    }

    public void setRchrAttendanceToLineList(List<RchrAttendanceToLine> rchrAttendanceToLineList) {
        set(PROPERTY_RCHRATTENDANCETOLINELIST, rchrAttendanceToLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAttendstrength> getRchrAttendstrengthList() {
        return (List<RchrAttendstrength>) get(PROPERTY_RCHRATTENDSTRENGTHLIST);
    }

    public void setRchrAttendstrengthList(List<RchrAttendstrength> rchrAttendstrengthList) {
        set(PROPERTY_RCHRATTENDSTRENGTHLIST, rchrAttendstrengthList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsappln> getRchrBanksalpaymentsapplnList() {
        return (List<RchrBanksalpaymentsappln>) get(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST);
    }

    public void setRchrBanksalpaymentsapplnList(List<RchrBanksalpaymentsappln> rchrBanksalpaymentsapplnList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSAPPLNLIST, rchrBanksalpaymentsapplnList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsCash> getRchrBanksalpaymentscsList() {
        return (List<RchrBanksalpaymentsCash>) get(PROPERTY_RCHRBANKSALPAYMENTSCSLIST);
    }

    public void setRchrBanksalpaymentscsList(List<RchrBanksalpaymentsCash> rchrBanksalpaymentscsList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSCSLIST, rchrBanksalpaymentscsList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBanksalpaymentsLn> getRchrBanksalpaymentslnList() {
        return (List<RchrBanksalpaymentsLn>) get(PROPERTY_RCHRBANKSALPAYMENTSLNLIST);
    }

    public void setRchrBanksalpaymentslnList(List<RchrBanksalpaymentsLn> rchrBanksalpaymentslnList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSLNLIST, rchrBanksalpaymentslnList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBankSalPaymentsOnline> getRchrBanksalpaymentsolList() {
        return (List<RchrBankSalPaymentsOnline>) get(PROPERTY_RCHRBANKSALPAYMENTSOLLIST);
    }

    public void setRchrBanksalpaymentsolList(List<RchrBankSalPaymentsOnline> rchrBanksalpaymentsolList) {
        set(PROPERTY_RCHRBANKSALPAYMENTSOLLIST, rchrBanksalpaymentsolList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrBiocardconfig> getRchrBiocardconfigList() {
        return (List<RchrBiocardconfig>) get(PROPERTY_RCHRBIOCARDCONFIGLIST);
    }

    public void setRchrBiocardconfigList(List<RchrBiocardconfig> rchrBiocardconfigList) {
        set(PROPERTY_RCHRBIOCARDCONFIGLIST, rchrBiocardconfigList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrDesigEmp> getRchrDesigEmpList() {
        return (List<RchrDesigEmp>) get(PROPERTY_RCHRDESIGEMPLIST);
    }

    public void setRchrDesigEmpList(List<RchrDesigEmp> rchrDesigEmpList) {
        set(PROPERTY_RCHRDESIGEMPLIST, rchrDesigEmpList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmpBank> getRchrEmpBankList() {
        return (List<RchrEmpBank>) get(PROPERTY_RCHREMPBANKLIST);
    }

    public void setRchrEmpBankList(List<RchrEmpBank> rchrEmpBankList) {
        set(PROPERTY_RCHREMPBANKLIST, rchrEmpBankList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmpJob> getRchrEmpJobList() {
        return (List<RchrEmpJob>) get(PROPERTY_RCHREMPJOBLIST);
    }

    public void setRchrEmpJobList(List<RchrEmpJob> rchrEmpJobList) {
        set(PROPERTY_RCHREMPJOBLIST, rchrEmpJobList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployee> getRchrEmployeeSupervisornameList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEESUPERVISORNAMELIST);
    }

    public void setRchrEmployeeSupervisornameList(List<RchrEmployee> rchrEmployeeSupervisornameList) {
        set(PROPERTY_RCHREMPLOYEESUPERVISORNAMELIST, rchrEmployeeSupervisornameList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployeeweekoffs> getRchrEmployeeweekoffsList() {
        return (List<RchrEmployeeweekoffs>) get(PROPERTY_RCHREMPLOYEEWEEKOFFSLIST);
    }

    public void setRchrEmployeeweekoffsList(List<RchrEmployeeweekoffs> rchrEmployeeweekoffsList) {
        set(PROPERTY_RCHREMPLOYEEWEEKOFFSLIST, rchrEmployeeweekoffsList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpaymentsemp> getRchrExbanksalpaymentsempList() {
        return (List<RchrExbanksalpaymentsemp>) get(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST);
    }

    public void setRchrExbanksalpaymentsempList(List<RchrExbanksalpaymentsemp> rchrExbanksalpaymentsempList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSEMPLIST, rchrExbanksalpaymentsempList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrExbanksalpaymentsempLn> getRchrExbanksalpaymentsemplnList() {
        return (List<RchrExbanksalpaymentsempLn>) get(PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST);
    }

    public void setRchrExbanksalpaymentsemplnList(List<RchrExbanksalpaymentsempLn> rchrExbanksalpaymentsemplnList) {
        set(PROPERTY_RCHREXBANKSALPAYMENTSEMPLNLIST, rchrExbanksalpaymentsemplnList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrMemoShift> getRchrMemoShiftList() {
        return (List<RchrMemoShift>) get(PROPERTY_RCHRMEMOSHIFTLIST);
    }

    public void setRchrMemoShiftList(List<RchrMemoShift> rchrMemoShiftList) {
        set(PROPERTY_RCHRMEMOSHIFTLIST, rchrMemoShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrOnduty> getRchrOndutyList() {
        return (List<RchrOnduty>) get(PROPERTY_RCHRONDUTYLIST);
    }

    public void setRchrOndutyList(List<RchrOnduty> rchrOndutyList) {
        set(PROPERTY_RCHRONDUTYLIST, rchrOndutyList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRollwisedata> getRchrRollwisedataList() {
        return (List<RchrRollwisedata>) get(PROPERTY_RCHRROLLWISEDATALIST);
    }

    public void setRchrRollwisedataList(List<RchrRollwisedata> rchrRollwisedataList) {
        set(PROPERTY_RCHRROLLWISEDATALIST, rchrRollwisedataList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRoomEmployee> getRchrRoomEmployeeList() {
        return (List<RchrRoomEmployee>) get(PROPERTY_RCHRROOMEMPLOYEELIST);
    }

    public void setRchrRoomEmployeeList(List<RchrRoomEmployee> rchrRoomEmployeeList) {
        set(PROPERTY_RCHRROOMEMPLOYEELIST, rchrRoomEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrShifttwelve> getRchrShifttwelveList() {
        return (List<RchrShifttwelve>) get(PROPERTY_RCHRSHIFTTWELVELIST);
    }

    public void setRchrShifttwelveList(List<RchrShifttwelve> rchrShifttwelveList) {
        set(PROPERTY_RCHRSHIFTTWELVELIST, rchrShifttwelveList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrStrngthlns> getRchrStrngthlnsList() {
        return (List<RchrStrngthlns>) get(PROPERTY_RCHRSTRNGTHLNSLIST);
    }

    public void setRchrStrngthlnsList(List<RchrStrngthlns> rchrStrngthlnsList) {
        set(PROPERTY_RCHRSTRNGTHLNSLIST, rchrStrngthlnsList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplEmppprocessmanual> getRcplEmppprocessmanualList() {
        return (List<RcplEmppprocessmanual>) get(PROPERTY_RCPLEMPPPROCESSMANUALLIST);
    }

    public void setRcplEmppprocessmanualList(List<RcplEmppprocessmanual> rcplEmppprocessmanualList) {
        set(PROPERTY_RCPLEMPPPROCESSMANUALLIST, rcplEmppprocessmanualList);
    }

    @SuppressWarnings("unchecked")
    public List<epcg_ppcenquiry> getEpcgPpcenquiryList() {
        return (List<epcg_ppcenquiry>) get(PROPERTY_EPCGPPCENQUIRYLIST);
    }

    public void setEpcgPpcenquiryList(List<epcg_ppcenquiry> epcgPpcenquiryList) {
        set(PROPERTY_EPCGPPCENQUIRYLIST, epcgPpcenquiryList);
    }

}
