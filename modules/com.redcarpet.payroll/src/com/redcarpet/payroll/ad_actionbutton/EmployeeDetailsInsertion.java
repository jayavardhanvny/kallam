package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrJoinRejoiningform;
import com.rcss.humanresource.util.RchrConstantType;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import org.hibernate.exception.ConstraintViolationException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;

import javax.sql.rowset.serial.SerialException;
public class EmployeeDetailsInsertion {
    public void insertEmployeeRecord(RchrJoinRejoiningform rchrJoinRejoinForm) throws SerialException{
        RchrEmployee employee = OBProvider.getInstance().get(RchrEmployee.class);
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
	employee.setPreattddays(rchrJoinRejoinForm.getDeclareservicedays().longValue());
        OBDal.getInstance().save(employee);
	employee.setSenior(rchrJoinRejoinForm.isSenior());
	employee.setRchrShiftgroup(rchrJoinRejoinForm.getRchrShiftgroup());
        employee.setRelay(rchrJoinRejoinForm.getRelayName());
	employee.setRchrMobalizer(rchrJoinRejoinForm.getRchrMobalizer());
        insertEmployeeSalarySetup(employee,rchrJoinRejoinForm);
    }
    public void insertEmployeeSalarySetup(RchrEmployee employee,RchrJoinRejoiningform rchrJoinRejoinForm) throws ConstraintViolationException{
        RCPL_EmpSalSetup rcplEmpSalSetup = OBProvider.getInstance().get(RCPL_EmpSalSetup.class);
        rcplEmpSalSetup.setEmployee(employee);
        if(rchrJoinRejoinForm.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
            rcplEmpSalSetup.setOperator(Boolean.TRUE);
            if(rchrJoinRejoinForm.isSenior()){
                //rcplEmpSalSetup.setNotdesig(Boolean.TRUE);
                //rcplEmpSalSetup.setServiceince(rchrJoinRejoinForm.getServiceincentive());
                //rcplEmpSalSetup.setPerdaybasic(rchrJoinRejoinForm.getBasic());
            }
        }else{
            rcplEmpSalSetup.setGrandTotalAmount(rchrJoinRejoinForm.getSalary());
            //rcplEmpSalSetup.setPayrollTemplate(rchrJoinRejoinForm.getRcplPayrolltemplate());
        }
        rcplEmpSalSetup.setPayrollTemplate(rchrJoinRejoinForm.getRcplPayrolltemplate());
        OBDal.getInstance().save(rcplEmpSalSetup);
    }
}
