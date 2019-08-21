package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrJoinRejoiningform;
import com.rcss.humanresource.util.RchrConstantType;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

public class EmployeeDetailsUpdation {
    public void updateEmployeeRecord(RchrJoinRejoiningform rchrJoinRejoinForm){
        RchrEmployee employee = rchrJoinRejoinForm.getEmployee();
        employee.setActive(Boolean.TRUE);
        employee.setPunchno(rchrJoinRejoinForm.getPunchno());
        employee.setDocumentNo(rchrJoinRejoinForm.getPunchno());
        employee.setEmployeeName(rchrJoinRejoinForm.getEmployeeName());

        //employee.setDocumentNo(rchrJoinRejoinForm.getPunchno()); //for Employee Id
        if(rchrJoinRejoinForm.getDateOfBirth()!=null)
            employee.setDateOfBirth(rchrJoinRejoinForm.getDateOfBirth());
        employee.setDesignation(rchrJoinRejoinForm.getDesignation());
        employee.setEmployeeDepartment(rchrJoinRejoinForm.getEmployeeDepartment());
        employee.setSubDepartment(rchrJoinRejoinForm.getSubDepartment());
        employee.setEmployeestatus(RchrConstantType.EMPLOYEE_STATUS_WORKING);
        employee.setSalarystatus(RchrConstantType.DOCUMENT_STATUS_APPROVED);
        employee.setDate(rchrJoinRejoinForm.getDate());
        employee.setOrganization(rchrJoinRejoinForm.getOrganization());
        employee.setRchrJoinRejoiningform(rchrJoinRejoinForm);
        employee.setGender(rchrJoinRejoinForm.getGender());
        employee.setMaritalStatus(rchrJoinRejoinForm.getMaritalStatus());
        employee.setEmployeeType(rchrJoinRejoinForm.getEmployeeType());
        employee.setWeekOffApplicable(rchrJoinRejoinForm.isWeekOffApplicable());
        employee.setWeeklyOff(rchrJoinRejoinForm.getWeeklyOff());
        employee.setCoffapplicable(rchrJoinRejoinForm.isCoffapplicable());
        employee.setOdapplicable(rchrJoinRejoinForm.isOdapplicable());
        employee.setImage(rchrJoinRejoinForm.getImage());
	employee.setSenior(rchrJoinRejoinForm.isSenior());
	employee.setRchrShiftgroup(rchrJoinRejoinForm.getRchrShiftgroup());
        employee.setRelay(rchrJoinRejoinForm.getRelayName());
	if (rchrJoinRejoinForm.getRchrMobalizer()!=null)
            employee.setRchrMobalizer(rchrJoinRejoinForm.getRchrMobalizer());
	employee.setPreattddays(rchrJoinRejoinForm.getDeclareservicedays().longValue());

    }
    public void updateEmployeeSalarySetup(RchrJoinRejoiningform rchrJoinRejoinForm){
        OBCriteria<RCPL_EmpSalSetup> rcplEmpSalSetupOBCriteria = OBDal.getInstance().createCriteria(RCPL_EmpSalSetup.class);
        rcplEmpSalSetupOBCriteria.add(Restrictions.eq(RCPL_EmpSalSetup.PROPERTY_EMPLOYEE,rchrJoinRejoinForm.getEmployee()));
        for (RCPL_EmpSalSetup rcplEmpSalSetup: rcplEmpSalSetupOBCriteria.list()
             ) {
            if(rchrJoinRejoinForm.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
                rcplEmpSalSetup.setOperator(Boolean.TRUE);
                if(rchrJoinRejoinForm.isSenior()){
                    //rcplEmpSalSetup.setNotdesig(Boolean.TRUE);
                   // rcplEmpSalSetup.setServiceince(rchrJoinRejoinForm.getServiceincentive());
                    //rcplEmpSalSetup.setPerdaybasic(rchrJoinRejoinForm.getBasic());
                }
            }else{
                rcplEmpSalSetup.setGrandTotalAmount(rchrJoinRejoinForm.getSalary());
                //rcplEmpSalSetup.setPayrollTemplate(rchrJoinRejoinForm.getRcplPayrolltemplate());
            }
            rcplEmpSalSetup.setPayrollTemplate(rchrJoinRejoinForm.getRcplPayrolltemplate());
rcplEmpSalSetup.setActive(Boolean.TRUE);
        }
    }
}
