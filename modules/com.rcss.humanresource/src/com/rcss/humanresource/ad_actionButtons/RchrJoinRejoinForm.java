package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RchrJoinRejoiningform;
import com.rcss.humanresource.exceptions.FieldEmptyException;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.RchrConstantType;
import com.redcarpet.payroll.ad_actionbutton.EmployeeDetailsInsertion;
import com.redcarpet.payroll.ad_actionbutton.EmployeeDetailsUpdation;
import org.hibernate.exception.ConstraintViolationException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.serial.SerialException;


public class RchrJoinRejoinForm extends DalBaseProcess implements BundleProcess {
    final private Logger logger = LoggerFactory.getLogger(RchrJoinRejoinForm.class);
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        final OBError msg = new OBError();
        try {
            doIt(bundle);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Success");
            OBDal.getInstance().commitAndClose();
        } catch (FieldEmptyException e) {
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Error is : " + e.getMessage());
            OBDal.getInstance().rollbackAndClose();
        } catch (IllegalArgumentException e) {
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Error is : " + e.getMessage());
            OBDal.getInstance().rollbackAndClose();
        } catch (ConstraintViolationException e) {
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Error is : " + "This Employee as already been created");

            OBDal.getInstance().rollbackAndClose();
        }catch (SerialException se){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Error is : " + se.getMessage());
        }
        bundle.setResult(msg);
    }
    @Override
    public void doIt(ProcessBundle bundle) throws ConstraintViolationException,
                FieldEmptyException,SerialException,IllegalArgumentException {
        String strEmpSet = (String)bundle.getParams().get("Rchr_Join_Rejoiningform_ID");
        System.out.println("strEmpSet "+strEmpSet);
        logger.debug("{}",strEmpSet);
        RchrJoinRejoiningform rchrJoinRejoinForm = OBDal.getInstance().get(RchrJoinRejoiningform.class,strEmpSet);
        if(rchrJoinRejoinForm.getEmployeeDepartment()==null){
            throw new SerialException("Department is Mandatory.");
        }else if(rchrJoinRejoinForm.getDesignation()==null){
            throw new SerialException("Designation is Mandatory.");
        }else if(rchrJoinRejoinForm.getEmployeeType()==null){
            throw new SerialException("Select Employee Type.");
        }else if(rchrJoinRejoinForm.getSubDepartment()==null){
            throw new SerialException("Sub-Department is Mandatory.");
        }else if(rchrJoinRejoinForm.getGender()==null){
            throw new SerialException("Gender is Mandatory.");
        }else if(rchrJoinRejoinForm.getRcplPayrolltemplate()==null){
            throw new SerialException("Payroll Template is Mandatory.");
        }else if(rchrJoinRejoinForm.equals(RchrConstantType.EMPLOYEE_STATUS_JOIN) && rchrJoinRejoinForm.getEmployee()!=null){
            throw new SerialException("Make Sure Employee is Null, that is only for Re-Joining Employees");
        }else if(rchrJoinRejoinForm.equals(RchrConstantType.EMPLOYEE_STATUS_REJOIN)
                && rchrJoinRejoinForm.getEmployee()==null){
            throw new SerialException("Please Select Employee, Mandatory");
        }
        if(rchrJoinRejoinForm.getJointype().equals(RchrConstantType.JOINTYPE_JOIN)){
            EmployeeDetailsInsertion employeeDetailsInsertion = new EmployeeDetailsInsertion();
            employeeDetailsInsertion.insertEmployeeRecord(rchrJoinRejoinForm);
            //employeeDetailsInsertion.insertEmployeeSalarySetup();
        }else if(rchrJoinRejoinForm.getJointype().equals(RchrConstantType.JOINTYPE_REJOIN)){
            EmployeeDetailsUpdation  employeeDetailsUpdation =  new EmployeeDetailsUpdation();
            employeeDetailsUpdation.updateEmployeeRecord(rchrJoinRejoinForm);
            employeeDetailsUpdation.updateEmployeeSalarySetup(rchrJoinRejoinForm);
        }
        rchrJoinRejoinForm.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_CO);
    }

}
