/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_PayDeducitionsHead;
import com.redcarpet.payroll.data.RcplEmppprocessmanual;
import com.redcarpet.payroll.data.RcplEmpsalsetuplines;
import com.redcarpet.payroll.util.PayrollUtils;
import com.rcss.humanresource.util.EmployeeUtil;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import com.redcarpet.payroll.util.SalariesUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
/**
*
* @author Matheen
* Customized By Tirumalakumar.U
*  Surya rao.A
*  Vinay kumar.K
*/
public class AttendanceUtil extends HRAttendance {
	public static final Logger logger = Logger.getLogger(AttendanceUtil.class);
    private String periodId;
    private String employeeId;
    private Date startDate;
    private Date endDate;

    public AttendanceUtil() {
        super();
    }

    public AttendanceUtil(String periodId, String employeeId) {
        this();
        this.employeeId = employeeId;
        this.periodId = periodId;
        RchrAttdProcess attPeriod = OBDal.getInstance().get(RchrAttdProcess.class, periodId);
        this.startDate = attPeriod.getStartingDate();
        this.endDate = attPeriod.getEndingDate();
    }
    public AttendanceUtil(Date startDate, Date endDate, String employeeId) {
        this();
        this.employeeId = employeeId;

        this.startDate = startDate;
        this.endDate = endDate;
    }
    public AttendanceUtil(String attendanceId, String periodId, String employeeId, Date startDate, Date endDate) {
        this();
        this.employeeId = employeeId;
        this.periodId = periodId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    // getting employee type...
    public String getEmpType() {
    	RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
    	String emptype = rchrEmployee.getEmployeeType();
    	return emptype;
    }



    public double getNoOfDaysPresent() {
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee.id = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.overtime=false "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();

        RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
        //double manualPresents = getNoOfManualDaysPresents();
        if(rchrEmployee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)||
        		rchrEmployee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)){
        	//double numOfCOffs = getNoOfCOffsNew();
            //double onDuties = getNoOfOnDuties();

        	//logger.info("NumOfCOffs "+numOfCOffs);
        	//logger.info("onDuties "+onDuties);
            //logger.info("manualPresents "+manualPresents);
            //logger.info("Presents "+Double.valueOf(li.get(0).toString()));
        	//return Double.valueOf(li.get(0).toString()) + numOfCOffs + onDuties+ manualPresents;
            return Double.valueOf(li.get(0).toString());
        }else{
        	return Double.valueOf(li.get(0).toString());
        }

    }

    public double getNoOfManualDaysPresents() {
    	String hql = "SELECT COUNT(aline.id) from Rcpl_Emppprocessmanual AS aline "
    			+ " JOIN aline.rcplPayrollprocessmanual AS head"
                + " WHERE aline.employee.id = '" + getEmployeeId() + "' "
                + " AND aline.attddate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())
                + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND aline.process=true AND aline.daytype in ('PR','CO','OD')"
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        //logger.info("get getNoOfManualDaysPresents hql is "+hql);
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        	return Double.valueOf(li.get(0).toString());
    }
    public double getNoOfManualDaysLeaves() {
        String hql = "SELECT COUNT(aline.id) from Rcpl_Emppprocessmanual AS aline "
                + " JOIN aline.rcplPayrollprocessmanual AS head"
                + " WHERE aline.employee.id = '" + getEmployeeId() + "' "
                + " AND aline.attddate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())
                + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND aline.process=true AND aline.daytype in ('CL','SL')"
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        //logger.info("get getNoOfManualDaysPresents hql is "+hql);
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }
    public double getNoOfManualDaysPresents(Date startedDate, Date endedDate) {
    	String hql = "SELECT COUNT(aline.id) from Rcpl_Emppprocessmanual AS aline "
    			+ " JOIN aline.rcplPayrollprocessmanual AS head"
                + " WHERE aline.employee.id = '" + getEmployeeId() + "' "
                + " AND aline.attddate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startedDate)
                + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endedDate) + "' "
                + " AND aline.process=true AND aline.daytype in ('PR','CO','OD')"
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        //logger.info("get getNoOfManualDaysPresents hql is "+hql);
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        	return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfManualDaysOTPresents(Date startedDate, Date endedDate) {
        String hql = "SELECT COUNT(aline.id) from Rcpl_Emppprocessmanual AS aline "
                + " JOIN aline.rcplPayrollprocessmanual AS head"
                + " WHERE aline.employee.id = '" + getEmployeeId() + "' "
                + " AND aline.attddate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startedDate)
                + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endedDate) + "' "
                + " AND aline.process=true AND aline.daytype = 'PROT'"
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        //logger.info("get getNoOfManualDaysPresents hql is "+hql);
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfOnDuties(){
        String hql = " SELECT COUNT(aline.id) FROM Rchr_Ondutylines aline " +
                " JOIN aline.rchrOnduty AS head " +
                " WHERE head.employee.id = '" + getEmployeeId() + "' " +
                " AND aline.oddate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())
                + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' " +
                " AND head.approve = true AND aline.weeklyOff = false" +
                " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        //logger.info("get getNoOfManualDaysPresents hql is "+hql);
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }
    public double getNoOfOnDuties(Date startedDate, Date endedDate){
        String hql = " SELECT COUNT(aline.id) FROM Rchr_Ondutylines aline " +
                " JOIN aline.rchrOnduty AS head " +
                " WHERE head.employee.id = '" + getEmployeeId() + "' " +
                " AND aline.oddate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startedDate)
                + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endedDate) + "' " +

                " AND head.approve = true AND aline.weeklyOff = false" +
                " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        //logger.info("get getNoOfManualDaysPresents hql is "+hql);
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }

/*    public double getNoOfCOffs(){
    	String hql = "SELECT SUM(coffs.numofcoffs) from RCHR_Employeecoffs AS coffs "
                + " WHERE coffs.employee.id = '" + getEmployeeId() + "' "
                + " AND coffs.ondate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
		Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }
*/

    public double getNoOfCOffs(){
    	String hql = "SELECT COUNT(aline.coffday) from RCHR_Employeecoffslines AS aline "
	             + " JOIN aline.rchrEmployeecoffs AS att "
	             + " WHERE att.employee.id = '" + getEmployeeId() + "' "
	             + " AND aline.coffday BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
	             + " AND 1 = 1";

		Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }






    public double getNoOfCOffsNew() {
        String hql = "SELECT COUNT(aline.id) from RCHR_LeaveRequisitionLine AS aline "
                + " JOIN aline.leaveRequisition AS req "
                + " WHERE req.employee.id = '" + getEmployeeId() + "' "
                + " AND req.documentStatus = 'CO' "
                + " AND aline.leavedate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND aline.canceled=false AND aline.leaveType.searchKey = 'CO' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfPhysicalWorkingDaysPresent() {
        String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true"
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())
                + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString()) + getNoOfManualDaysPresents();
    }


	 public double getNoOfDaysPresentWithOutOt() {
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.overtime = false "
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())
                + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }


	 public double getNoOfDaysPresent(Date startedDate, Date endedDate){
//			logger.info("In getNoOfDaysPresent Meyhod");
			String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
	                + " WHERE aline.employee = '" + getEmployeeId() + "' "
	                + " AND aline.present = true "
	                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startedDate)
                    + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endedDate) + "' "
	                + " AND 1 = 1";
		        Session session = OBDal.getInstance().getSession();
		        List li = session.createQuery(hql).list();
		        double numOfCOffs = getNoOfCOffsForWeeklyOffNew(startedDate,endedDate);
		        double noOfOnDuties =getNoOfOnDuties(startedDate,endedDate);
			//logger.info("noOfOnDuties Atttendanec Util getNoOfDaysPresent method "+startedDate+" and "+endedDate +" "+noOfOnDuties);
	        	return Double.valueOf(li.get(0).toString());
                        //+ numOfCOffs + getNoOfManualDaysPresents(startedDate,endedDate) +noOfOnDuties;
		        //return Double.valueOf(li.get(0).toString());
     }
/*	 public double getNoOfCOffsForWeeklyOff(Date startedDate, Date endedDate){
	    	String hql = "SELECT SUM(coffs.numofcoffs) from RCHR_Employeecoffs AS coffs "
	                + " WHERE coffs.employee.id = '" + getEmployeeId() + "' "
	                + " AND coffs.ondate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startedDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endedDate) + "' "
	                + " AND 1 = 1";
			Session session = OBDal.getInstance().getSession();
	        List li = session.createQuery(hql).list();
	        if (li.size() <= 0 || li.isEmpty()) {
	            return 0;
	        }
	        if (li.get(0) == null || li.get(0).toString() == null) {
	            return 0;
	        }
	        return Double.valueOf(li.get(0).toString());
	    }*/


	 public double getNoOfCOffsForWeeklyOff(Date startedDate, Date endedDate){
//		 logger.info("In getNoOfCOffsForWeeklyOff Method");

		 String hql = "SELECT COUNT(aline.coffday) from RCHR_Employeecoffslines AS aline "
	             + " JOIN aline.rchrEmployeecoffs AS att "
	             + " WHERE att.employee.id = '" + getEmployeeId() + "' "
	             + " AND aline.coffday BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startedDate)
                 + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endedDate) + "' "
	             + " AND 1 = 1";
			Session session = OBDal.getInstance().getSession();
	        List li = session.createQuery(hql).list();
	        if (li.size() <= 0 || li.isEmpty()) {
	            return 0;
	        }
	        if (li.get(0) == null || li.get(0).toString() == null) {
	            return 0;
	        }
//	        logger.info(startedDate+"*****"+endedDate);
//	        logger.info(Double.valueOf(li.get(0).toString()));
	        return Double.valueOf(li.get(0).toString());

	    }



    public double getNoOfCOffsForWeeklyOffNew(Date startedDate, Date endedDate) {
        String hql = "SELECT COUNT(aline.id) from RCHR_LeaveRequisitionLine AS aline "
                + " JOIN aline.leaveRequisition AS req "
                + " WHERE req.employee.id = '" + getEmployeeId() + "' "
                + " AND req.documentStatus = 'CO' "
                + " AND aline.leavedate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startedDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endedDate) + "' "
                + " AND aline.canceled=false AND aline.leaveType.searchKey = 'CO' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }

















	/*
	 String hql = "SELECT COUNT(att.coffday) from RCHR_Employeecoffslines AS aline "
             + " JOIN aline.rchrEmployeecoffs AS att "
             + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
             + " AND att.coffday BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startedDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endedDate) + "' "
             + " AND 1 = 1";

	 */








    public double getNoOfWorkingDays() {
    	double noofdays = PayrollUtils.getInstance().getDaysDifference(startDate, endDate);
        //return PayrollUtils.getInstance().getDaysDifference(startDate, endDate);
        /*if(noofdays==31){
    		noofdays =30;
    	}*/
        return noofdays;

    }

    public double getNoOfDaysAbsent(){
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = false AND aline.weeklyOff = false "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }

    /*public double getNoOfDaysAbsent() {
        return this.getNoOfWorkingDays() - this.getNoOfDaysPresent();
    }*/

    public double getNoOfDaysLate() {
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.present= true  AND aline.late = true "
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());

    }

    public double getNoOfShiftChangeAbsent() {
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.shiftChangeAbsent = true "
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfDaysOTed() {
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.overtime = true "
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfDaysWeeklyOffWorked() {
        /*String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Temp AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.weeklyOff = true AND aline.present= true "
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";*/

        String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.weeklyOff=true "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfDaysNoWorked() {
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.noWork = true AND aline.present= false "
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfDaysNightShiftWorked(String shiftId) {
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.noWork = true AND aline.present= true "
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfLeaves() {
		String hql = "SELECT SUM(casual.el+casual.sl+casual.coffs) from RCHR_LeaveRequisition AS casual "
                + " WHERE casual.employee.id = '" + getEmployeeId() + "' "
                + " AND casual.documentStatus = 'CO' "
                + " AND casual.fromDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
		Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }

        return Double.valueOf(li.get(0).toString());
	}
    public double getNoOfLeavesNew() {
        String hql = "SELECT COUNT(aline.id) from RCHR_LeaveRequisitionLine AS aline "
                + " JOIN aline.leaveRequisition AS req "
                + " WHERE req.employee.id = '" + getEmployeeId() + "' "
                + " AND req.documentStatus = 'CO' "
                + " AND aline.leavedate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND aline.canceled=false AND aline.leaveType.searchKey in ('CL','SL') "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        double manualLeaves = getNoOfManualDaysLeaves();
        return Double.valueOf(li.get(0).toString()) + manualLeaves;
    }

    public double getNoOfLeavesCL() {
        String hql = "SELECT COUNT(aline.id) from RCHR_LeaveRequisitionLine AS aline "
                + " JOIN aline.leaveRequisition AS req "
                + " WHERE req.employee.id = '" + getEmployeeId() + "' "
                + " AND req.documentStatus = 'CO' "
                + " AND aline.leavedate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND aline.canceled=false AND aline.leaveType.searchKey in ('CL') "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        double manualLeaves = getNoOfManualDaysLeaves();
        return Double.valueOf(li.get(0).toString()) + manualLeaves;
    }

    public double getLeavesCountMonth() {
		String hql = "SELECT SUM(ob.leavesPerYear) from RCHR_LeaveOpenBal AS ob "
                + " WHERE ob.employee.id = '" + getEmployeeId() + "' "
                  + " AND 1 = 1";
		Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
	}

    public double getNoOfLOP() {
    	String hql = "SELECT SUM(lop.noOfLeaves) FROM RCHR_LossOfPay AS lop "
    			+ "WHERE lop.employee.id = '" + getEmployeeId() + "' "
    			+ " AND lop.fromDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
    			+ " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }

    public double getNoOfLOPNew() {
        String hql = "SELECT COUNT(aline.id) from RCHR_LeaveRequisitionLine AS aline "
                + " JOIN aline.leaveRequisition AS req "
                + " WHERE req.employee.id = '" + getEmployeeId() + "' "
                + " AND req.documentStatus = 'CO' "
                + " AND aline.leavedate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND aline.canceled=false AND aline.leaveType.searchKey = 'LOP' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }

        return Double.valueOf(li.get(0).toString());
    }

    public boolean isPFApplicable() {
        boolean isPFApplicable = false;
        String isPFHql = "SELECT pd FROM RCPL_EmpSalSetup ess "
                + "JOIN ess.payrollTemplate pt "
                + "JOIN pt.rCPLPayrollTemplateLinesList ptl "
                + "JOIN ptl.payDeductions pd "
                + "WHERE ess.employee.id='" + getEmployeeId() + "' "
                + "AND pd.category='PF'";
        Session session = OBDal.getInstance().getSession();
        List<RCPL_PayDeducitionsHead> li = session.createQuery(isPFHql).list();
        if (!li.isEmpty() && li.size() > 0) {
            isPFApplicable = true;
        }
        return isPFApplicable;
    }
    public double getNoOfOTsNotPaidParollMonth(){
            String hql = "SELECT SUM(ot.amount) FROM RCHR_OverTime AS ot "
                    + "JOIN ot.overTimeHeader head "
                    + " WHERE ot.employee.id = '" + getEmployeeId() + "' " +
                    " AND ot.isPaid=false"
                    + " AND head.startingDate BETWEEN '"+ PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+"' "+
                    " AND '"+PayrollUtils.getInstance().getDBCompatiableDate(getEndDate())+"' "
                    + " AND head.endingDate BETWEEN '"+ PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+"' "+
                    " AND '"+PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) +"' ";
            return getFirstRecordValue(hql);
   }
    public PeriodInfo getLastMonthInfo() throws ServletException {
        Date lastMonthStartDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MONTH, -1);
        lastMonthStartDate = cal.getTime();

        Date lastMonthEndDate = new Date();
        cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.MONTH, -1);
        lastMonthEndDate = cal.getTime();
        return new PeriodInfo(lastMonthStartDate, lastMonthEndDate);
    }

    public PeriodInfo getTheyBeforeLastMonthInfo() throws ServletException {

        Date lastMonthStartDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MONTH, -2);
        lastMonthStartDate = cal.getTime();

        Date lastMonthEndDate = new Date();
        cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.MONTH, -2);
        lastMonthEndDate = cal.getTime();
        return new PeriodInfo(lastMonthStartDate, lastMonthEndDate);
    }

    public List getPeriodRange() {
        List<Integer> li = Collections.emptyList();
        String periodHql = "SELECT period.id as id, period.rchrYear.id as year, period.periodNo as lowValue, period.periodNo-1 as medValue, period.periodNo-2 as highValue "
                + " FROM Rchr_Attd_Process period "
                + " WHERE period.id IN "
                + " (SELECT period FROM Rchr_Attd_Process period "
                + " WHERE period.id = '" + periodId + "'"
                + " )";
        Session session = OBDal.getInstance().getSession();
        li = session.createQuery(periodHql).list();
        return li;
    }

    public double getNoOfWeekOffWorked(){
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.present= true  AND aline.weeklyOff = true "
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }


    public double getNoOfWeekOffDaysIfWeekOffApplicable() {
        double retVal = 0;
        RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
        if (employee.isWeekOffApplicable()) {

            String weekOffDay = employee.getWeeklyOff();
            EmployeeUtil employeeUtil = new EmployeeUtil();
            weekOffDay = employeeUtil.getEmployeeWeekOffForPayroll(employee,getStartDate());
            if(weekOffDay.equals("N/A")){
                weekOffDay = employee.getWeeklyOff();
            }

            retVal = getNoOfDaysBetweenOnParticularDay(startDate, weekOffDay);
//            logger.info("no of week " + weekOffDay + " offs in month " + new SimpleDateFormat("dd/MM/yyyy").format(startDate) + " is " + retVal);
        }
        return retVal;
    }

    private double getNoOfDaysBetweenOnParticularDay(Date startDate, String weekOffDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int daysinmonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int count = 0;
        for (int day = 1; day <= daysinmonth; day++) {
            calendar.set(year, month - 1, day);
            int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
//            logger.info(dayofweek + "dayofweek");
            if (dayofweek == getDay(weekOffDay)) {
                count++;
            }
        }
        return count;
    }

    public double getNoOfWeekOffs() {
//    	logger.info("In getNoOfWeekOffs Method");
        double count = 0;
        RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
           if (employee.isWeekOffApplicable() && !employee.getWeeklyOff().equals("N/A")) {

	       EmployeeUtil employeeUtil = new EmployeeUtil();
               String weekOffDay = employeeUtil.getEmployeeWeekOffForPayroll(employee,getStartDate());
               if(weekOffDay.equals("N/A")){
                   weekOffDay = employee.getWeeklyOff();
               }
               Calendar calendarStart = Calendar.getInstance();
               Calendar StaticEnd = Calendar.getInstance();
               calendarStart.setTime(getStartDate());
               StaticEnd.setTime(getEndDate());

               while(calendarStart.getTime().compareTo(calendarStart.getTime())>=0){
                int dayofweek = calendarStart.get(Calendar.DAY_OF_WEEK);
               // logger.info("dayofweek "+dayofweek);


             if(dayofweek==getDay(weekOffDay)){
              break;
             }else{
              //logger.info("Continue to try");
             }
             calendarStart.add(Calendar.DATE, 1);
               }
               //logger.info("calendarStart "+calendarStart.getTime());
               Calendar dynamicEndDate = Calendar.getInstance();
               dynamicEndDate.setTime(calendarStart.getTime());
              // logger.info("dynamicEndDate " +dynamicEndDate);
               Calendar dynamicStartDate = Calendar.getInstance();
               calendarStart.add(Calendar.DATE, -7);
               dynamicStartDate.setTime(calendarStart.getTime());
              while(StaticEnd.compareTo(dynamicEndDate)>=0){
            	dynamicStartDate.add(Calendar.DATE, 1);
                try{
                 double noOfPresents = getNoOfDaysPresent(dynamicStartDate.getTime(),dynamicEndDate.getTime());
                //logger.info("no of present Atttendanec Util weekoffs "+ noOfPresents);
                    if(noOfPresents<4){
                     count++;
                    }
                    dynamicStartDate.setTime(dynamicEndDate.getTime());
                    //calstart.setTime(calend);
                    dynamicEndDate.add(Calendar.DATE, 7);
                    //dynamicEndDate.setTime(dynamicEndDate.getTime());
                }catch (Exception e){
                 //logger.info(e);
                }

               }
           }
           return count;
    }

    private int getDay(String day) {

        int weekOff = -1;

        if (StringUtils.equalsIgnoreCase(day, "SUNDAY")) {
            weekOff = 1;
        } else if (StringUtils.equalsIgnoreCase(day, "MONDAY")) {
            weekOff = 2;
        } else if (StringUtils.equalsIgnoreCase(day, "TUESDAY")) {
            weekOff = 3;
        } else if (StringUtils.equalsIgnoreCase(day, "WEDNESDAY")) {
            weekOff = 4;
        } else if (StringUtils.equalsIgnoreCase(day, "THURSDAY")) {
            weekOff = 5;
        } else if (StringUtils.equalsIgnoreCase(day, "FRIDAY")) {
            weekOff = 6;
        } else if (StringUtils.equalsIgnoreCase(day, "SATURDAY")) {
            weekOff = 7;
        }else{
        	weekOff=-1;
        }
        return weekOff;
    }

    public class PeriodInfo {

        private Date start;
        private Date end;

        public PeriodInfo(Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        public double getNoOfWrokingDays() {
            return PayrollUtils.getInstance().getDaysDifference(start, end);
        }

        public double getNoOfDaysPresentDays() {

            if (this.start == null || this.end == null) {
                return 0;
            }

            String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                    + " WHERE aline.employee = '" + getEmployeeId() + "' "
                    + " AND aline.present = true AND aline.overtime=false "
                    + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(this.start) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(this.end) + "' "
                    + " AND 1 = 1";
            Session session = OBDal.getInstance().getSession();
            List li = session.createQuery(hql).list();
            return Double.valueOf(li.get(0).toString());
        }

		 public double getNoOfDaysPresentWithOutOt() {

        	 if (this.start == null || this.end == null) {
                 return 0;
             }

            String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                    + " JOIN aline.rchrAttendance AS att "
                    + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                    + " AND aline.present = true AND aline.overtime = false "
                    + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(this.start) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(this.end) + "' "
                    + " AND 1 = 1";
            Session session = OBDal.getInstance().getSession();
            List li = session.createQuery(hql).list();
            return Double.valueOf(li.get(0).toString());
        }
    }


    public double getBasicAmount(){
    	double latestBasicAmount = 0;
    	//double latestServiceAmount = 0;
    	double totalPay = 0;
    	double basicAmount=0;
    	//serviceIncentive=0;
    	double presentDays=getNoOfDaysPresent();
    	RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
    	long presentServiceDays = (long)presentDays + employee.getPreattddays();
    	RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
        OBCriteria<RCHRDesigBasic> desigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));


        desigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,employee.getPreattddays()));
       	desigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,employee.getPreattddays()));
        for(RCHRDesigBasic basic:desigbasic.list()){

        	long toDays = basic.getTodays();
        	if(presentServiceDays<=toDays){
        		totalPay=basic.getBasicamount().doubleValue();

        		//serviceIncentive=basic.getServiceincentive().doubleValue();
        		logger.info("totalPay "+totalPay);
        	}else{
 //       		logger.info("else statement");
        		OBCriteria<RCHRDesigBasic> latestDesigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        		latestDesigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));
        		latestDesigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,presentServiceDays));
        		latestDesigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,presentServiceDays));

        		double ServiceDaysPresents = presentServiceDays-toDays;
        		double oldServiceDayspresents = presentDays - ServiceDaysPresents;
        		double presentTotalMonthPay = 0;
        		double oldTotalMonthPay = 0;
        		for(RCHRDesigBasic latestBasic:latestDesigbasic.list()){
        			//logger.info("2nd forLoop ");
        			latestBasicAmount = latestBasic.getBasicamount().doubleValue();

        			//logger.info("latestBasicAmount "+latestBasicAmount);

        		}
        		presentTotalMonthPay = latestBasicAmount*ServiceDaysPresents;
        		//logger.info("ServiceDaysPresents "+ServiceDaysPresents);
        		//logger.info("presentTotalMonthPay "+presentTotalMonthPay);
        		basicAmount=basic.getBasicamount().doubleValue();
        		//serviceIncentive=basic.getServiceincentive().doubleValue();
        		oldTotalMonthPay = basicAmount * oldServiceDayspresents;


        		totalPay = (oldTotalMonthPay + presentTotalMonthPay)/presentDays;
        		//logger.info("totalPay "+totalPay);

        	}
        }

       return totalPay;
    }

    public double getServiceIncentive(){
    	//double latestBasicAmount = 0;
    	double latestServiceAmount = 0;
    	double totalPay = 0;
    	double serviceIncentive=0;
    	double presentDays=getNoOfDaysPresent();
    	RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
    	long presentServiceDays = (long)presentDays + employee.getPreattddays();
    	RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
        OBCriteria<RCHRDesigBasic> desigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));


        desigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,employee.getPreattddays()));
       	desigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,employee.getPreattddays()));
        for(RCHRDesigBasic basic:desigbasic.list()){

        	long toDays = basic.getTodays();
        	if(presentServiceDays <=toDays){
        		//basicAmount=basic.getBasicamount().doubleValue();
        		totalPay=basic.getServiceincentive().doubleValue();
        		logger.info("totalPay "+totalPay);
        	}else{
 //       		logger.info("else statement");
        		OBCriteria<RCHRDesigBasic> latestDesigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        		latestDesigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));
        		latestDesigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,presentServiceDays));
        		latestDesigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,presentServiceDays));

        		double ServiceDaysPresents = presentServiceDays-toDays;
        		double oldServiceDayspresents = presentDays - ServiceDaysPresents;
        		double presentTotalMonthPay = 0;
        		double oldTotalMonthPay = 0;
        		for(RCHRDesigBasic latestBasic:latestDesigbasic.list()){
        			//logger.info("2nd forLoop service");
        			//latestBasicAmount = latestBasic.getBasicamount().doubleValue();
        			latestServiceAmount = latestBasic.getServiceincentive().doubleValue();

        			//logger.info("latestServiceAmount service "+latestServiceAmount);
        		}
        		presentTotalMonthPay = latestServiceAmount*ServiceDaysPresents;
        		//logger.info("ServiceDaysPresents service "+ServiceDaysPresents);
        		//logger.info("presentTotalMonthPay service "+presentTotalMonthPay);
        		//basicAmount=basic.getBasicamount().doubleValue();
        		serviceIncentive=basic.getServiceincentive().doubleValue();
        		oldTotalMonthPay = serviceIncentive* oldServiceDayspresents;
        		//logger.info("oldServiceDayspresents service "+oldServiceDayspresents);
        		//logger.info("oldTotalMonthPay service "+oldTotalMonthPay);

        		totalPay = (oldTotalMonthPay + presentTotalMonthPay)/presentDays;
        		//logger.info("totalPay service "+totalPay);

        	}
        }
       return totalPay;
    }



    public double getIncrementedServiceIncentive(RCPL_EmpSalSetup rcplEmpSalSetup,Date toDate){

        String hql = "SELECT SUM(newsalary) FROM Rcpl_Empsalsetuplines lines " +
                " WHERE lines.employeeSalarySetup.id = '"+rcplEmpSalSetup.getId()+"' " +
                " AND lines.isserviceincr = true AND lines.effected <= '"+PayrollUtils.getInstance().getDBCompatiableDate(toDate)+"' ";
        return getFirstRecordValue(hql);
    }
    private double getFirstRecordValue(String hql) throws HibernateException, NumberFormatException {
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }


    public double getBtndaysPresentDaysForOT(Date headerfromDate){
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.overtime=false "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(headerfromDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

    public double getNoOfPresentDaysForOT(){
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.overtime=false "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

    public double getCheckPayrollmonth(Date fromDateTemp,Date toDateTemp){
    	String hql = "SELECT COUNT(aline.id) from RCPL_PayrollProcess AS aline "
                + " WHERE '" + PayrollUtils.getInstance().getDBCompatiableDate(fromDateTemp) + "' BETWEEN startingDate AND endingDate "
                + " AND '" + PayrollUtils.getInstance().getDBCompatiableDate(toDateTemp) + "' BETWEEN startingDate AND endingDate "
                + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

    public double getCheckAttendanceImport(Date toDateTemp){
    	 String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                 + " WHERE aline.attendanceDate = '" + PayrollUtils.getInstance().getDBCompatiableDate(toDateTemp) + "' "
                 + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

    public double getPrevmonthPresentDays(Date fromDateTemp,Date toDateTemp) {
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.overtime=false "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(fromDateTemp) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(toDateTemp) + "' "
                + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

    public double getPrevmonthPresentDaysDemo(Date fromDateTemp,Date toDateTemp){
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.overtime=false "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(fromDateTemp) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(toDateTemp) + "' "
                + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }
    public double getCountinuiesShift(){
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

    public double getNoOfOTs(){
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.overtime=true "
                + " AND aline.attendanceDate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

    public double getPresentDaysOfSettlement(){


  	  Date eDate= new Date(getEndDate().getTime());

      	Calendar fromcal=Calendar.getInstance();
  		Calendar tocal=Calendar.getInstance();
      	fromcal.set(Calendar.MONTH,eDate.getMonth());
      	fromcal.add(Calendar.MONTH, -1);
  		fromcal.set(Calendar.DAY_OF_MONTH, fromcal.getActualMinimum(Calendar.DATE));

  		tocal.set(Calendar.MONTH,eDate.getMonth());
  		tocal.add(Calendar.MONTH, -1);
  		tocal.set(Calendar.DATE, tocal.getActualMaximum(Calendar.DATE));
  		Date fromDateTemp=fromcal.getTime();
  		Date toDateTemp=tocal.getTime();

      	double checkMnth = getCheckPayrollmonth(fromDateTemp, toDateTemp);
      	double prvMnthDays = 0;
          if(checkMnth==0){
      		double importCheck=getCheckAttendanceImport(toDateTemp);
          	if(importCheck>0){
          		prvMnthDays = getPrevmonthPresentDays(fromDateTemp, toDateTemp);

          	}else{
          		prvMnthDays = getPrevmonthPresentDaysDemo(fromDateTemp, toDateTemp);

          	}
  //        	logger.info("prvMnthDays 2 "+prvMnthDays);
      	}


      	Calendar headercal=Calendar.getInstance();
  		//Calendar tocal=Calendar.getInstance();
      	headercal.set(Calendar.MONTH,eDate.getMonth());
      	//headercal.add(Calendar.MONTH, -1);
      	headercal.set(Calendar.DAY_OF_MONTH, headercal.getActualMinimum(Calendar.DATE));
      	Date headerfromDate=headercal.getTime();

      	double betweenDays=getBtndaysPresentDaysForOT(headerfromDate);
 //     	logger.info("presentDays are wrong...... "+betweenDays);
      	return betweenDays+prvMnthDays;

      }
    public SalariesUtil getSalariesUtil(RchrDesignation rchrDesignation, RchrEmployee employee){
        double latestBasicAmount = 0;
        //double latestServiceAmount = 0;
        double latestServiceAmount = 0;
        double totalPayBasicAmount = 0;
        double totalPayServiceAmount = 0;
        double basicAmount=0;
        double serviceIncentive=0;
        double presentDays=getNoOfDaysPresent();
        //RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
        long presentServiceDays = (long)presentDays + employee.getPreattddays();
        SalariesUtil salariesUtil = new SalariesUtil();
        //RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
        //EmployeeUtil employeeUtil = new EmployeeUtil();
        //RchrDesignation desig = employeeUtil.getEmployeeDesignation(employee,getStartDate(),getEndDate());
        OBCriteria<RCHRDesigBasic> desigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,rchrDesignation.getRchrSalarystructure()));
        desigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,employee.getPreattddays()));
        desigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,employee.getPreattddays()));
        for(RCHRDesigBasic basic:desigbasic.list()){
            long toDays = basic.getTodays();
		//logger.info("If Service  presentServiceDays "+presentServiceDays);
            if(presentServiceDays<=toDays){
                totalPayBasicAmount=basic.getBasicamount().doubleValue();
		//logger.info("If Service "+this.getIncrementedServiceIncentive(employee.getRCPLEmpSalSetupList().get(0), getEndDate()));
                totalPayServiceAmount = basic.getServiceincentive().doubleValue()+this.getIncrementedServiceIncentive(employee.getRCPLEmpSalSetupList().get(0),getEndDate());
		//logger.info("If Service basic.getServiceincentive() "+basic.getServiceincentive().doubleValue());
		//logger.info("If Service totalPayServiceAmount "+totalPayServiceAmount);
                //serviceIncentive=basic.getServiceincentive().doubleValue();
            }else{
                //       		logger.info("else statement");
                OBCriteria<RCHRDesigBasic> latestDesigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
                latestDesigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,rchrDesignation.getRchrSalarystructure()));
                latestDesigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,presentServiceDays));
                latestDesigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,presentServiceDays));

                double ServiceDaysPresents = presentServiceDays-toDays;
                double oldServiceDayspresents = presentDays - ServiceDaysPresents;
                double presentTotalMonthPay = 0;
                double presentTotalMonthPayService = 0;
                double oldTotalMonthPay = 0;
                double oldTotalMonthPayService = 0;
                for(RCHRDesigBasic latestBasic:latestDesigbasic.list()){
                    //logger.info("2nd forLoop ");
                    latestBasicAmount = latestBasic.getBasicamount().doubleValue();
                    latestServiceAmount = latestBasic.getServiceincentive().doubleValue();
                    //logger.info("latestBasicAmount "+latestBasicAmount);
                }
                presentTotalMonthPay = latestBasicAmount*ServiceDaysPresents;
                presentTotalMonthPayService = latestServiceAmount * ServiceDaysPresents;
                //logger.info("ServiceDaysPresents "+ServiceDaysPresents);
                //logger.info("presentTotalMonthPay "+presentTotalMonthPay);
                basicAmount=basic.getBasicamount().doubleValue();
		logger.info("Else Service "+this.getIncrementedServiceIncentive(employee.getRCPLEmpSalSetupList().get(0), getEndDate()));
                serviceIncentive=basic.getServiceincentive().doubleValue()+this.getIncrementedServiceIncentive(employee.getRCPLEmpSalSetupList().get(0),getEndDate());
                oldTotalMonthPay = basicAmount * oldServiceDayspresents;
                oldTotalMonthPayService = serviceIncentive * oldServiceDayspresents;
                totalPayBasicAmount = (oldTotalMonthPay + presentTotalMonthPay)/presentDays;
                totalPayServiceAmount = (oldTotalMonthPayService + presentTotalMonthPayService)/presentDays;

                //logger.info("totalPay "+totalPay);
            }

            salariesUtil.setBasicAmount(totalPayBasicAmount);
            salariesUtil.setServiceIncentiveAmount(totalPayServiceAmount);
            logger.info("totalPayBasicAmount "+totalPayBasicAmount);
            logger.info("totalPayServiceAmount "+totalPayServiceAmount);
        }

        return salariesUtil;
    }
    public SalariesUtil getSalariesUtilOT(RchrDesignation rchrDesignation, RchrEmployee employee){
        double latestBasicAmount = 0;
        double latestServiceAmount = 0;
        long presentServiceDays=0;
        long checkpresentServiceDays=0;
        //Date sDate= new Date(getStartDate().getTime());
        Date eDate= new Date(getEndDate().getTime());
        SalariesUtil salariesUtil = new SalariesUtil();
        Calendar fromcal=Calendar.getInstance();
        Calendar tocal=Calendar.getInstance();
        fromcal.set(Calendar.MONTH,eDate.getMonth());
        fromcal.add(Calendar.MONTH, -1);
        fromcal.set(Calendar.DAY_OF_MONTH, fromcal.getActualMinimum(Calendar.DATE));

        tocal.set(Calendar.MONTH,eDate.getMonth());
        tocal.add(Calendar.MONTH, -1);
        tocal.set(Calendar.DATE, tocal.getActualMaximum(Calendar.DATE));
        Date fromDateTemp=fromcal.getTime();
        Date toDateTemp=tocal.getTime();

        double checkMnth = getCheckPayrollmonth(fromDateTemp, toDateTemp);
        double prvMnthDays = 0;
        if(checkMnth==0){
            double importCheck=getCheckAttendanceImport(toDateTemp);
            if(importCheck>0){
                prvMnthDays = getPrevmonthPresentDays(fromDateTemp, toDateTemp);

            }else{
                prvMnthDays = getPrevmonthPresentDaysDemo(fromDateTemp, toDateTemp);

            }
            //       	logger.info("prvMnthDays 2 "+prvMnthDays);
        }

        double totalPayBasic = 0;
        double totalPayService = 0;
        double basicAmount= 0;
        double serviceIncentive=0;
        //serviceIncentive=0;

        Calendar headercal=Calendar.getInstance();
        //Calendar tocal=Calendar.getInstance();
        headercal.set(Calendar.MONTH,eDate.getMonth());
        //headercal.add(Calendar.MONTH, -1);
        headercal.set(Calendar.DAY_OF_MONTH, headercal.getActualMinimum(Calendar.DATE));
        Date headerfromDate=headercal.getTime();
        double presentDays=getNoOfPresentDaysForOT();
        //   	logger.info("presentDays are wrong...... "+presentDays);
        double betweenDays=getBtndaysPresentDaysForOT(headerfromDate);
        //RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
        //long servAndPredays= (long)prvMnthDays + employee.getPreattddays();
        presentServiceDays = (long)prvMnthDays + (long)betweenDays + employee.getPreattddays()+(long)presentDays;
        checkpresentServiceDays=(long)prvMnthDays + (long)betweenDays + employee.getPreattddays();
        //RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
        //EmployeeUtil employeeUtil = new EmployeeUtil();
        //RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
        //RchrDesignation desig = employeeUtil.getEmployeeDesignation(employee,startDate, endDate);
        OBCriteria<RCHRDesigBasic> desigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,rchrDesignation.getRchrSalarystructure()));
        desigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,presentServiceDays));
        desigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,presentServiceDays));

        for(RCHRDesigBasic basic:desigbasic.list()){
            long toDays = basic.getTodays();
            //long checkpresentServiceDays=presentServiceDays+(long)presentDays;
            if(checkpresentServiceDays<=toDays){
                totalPayBasic=basic.getBasicamount().doubleValue();
                totalPayService=basic.getServiceincentive().doubleValue()+this.getIncrementedServiceIncentive(employee.getRCPLEmpSalSetupList().get(0),getEndDate());
                //serviceIncentive=basic.getServiceincentive().doubleValue();
                //logger.info("totalPay "+totalPayBasic);
                //logger.info("totalPay "+totalPayService);
            }else{
                //logger.info("else statement");
                OBCriteria<RCHRDesigBasic> latestDesigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
                latestDesigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,rchrDesignation.getRchrSalarystructure()));
                latestDesigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,checkpresentServiceDays));
                latestDesigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,checkpresentServiceDays));

                double ServiceDaysPresents = checkpresentServiceDays-toDays;
                double oldServiceDayspresents = presentDays - ServiceDaysPresents;
                double presentTotalMonthPay = 0;
                double presentTotalMonthPayServiceIncentive =0;
                double oldTotalMonthPay = 0;
                double oldTotalMonthPayService = 0;
                for(RCHRDesigBasic latestBasic:latestDesigbasic.list()){
                    //logger.info("2nd forLoop ");
                    latestBasicAmount = latestBasic.getBasicamount().doubleValue();
                    latestServiceAmount = latestBasic.getServiceincentive().doubleValue();
                    //logger.info("latestBasicAmount "+latestBasicAmount);
                }
                presentTotalMonthPay = latestBasicAmount*ServiceDaysPresents;
                presentTotalMonthPayServiceIncentive = latestServiceAmount*ServiceDaysPresents;
                //logger.info("ServiceDaysPresents "+ServiceDaysPresents);
                //logger.info("presentTotalMonthPay "+presentTotalMonthPay);
                basicAmount=basic.getBasicamount().doubleValue();
                serviceIncentive=basic.getServiceincentive().doubleValue();
                oldTotalMonthPay = basicAmount * oldServiceDayspresents;
                oldTotalMonthPayService = serviceIncentive*oldServiceDayspresents;
                totalPayBasic = (oldTotalMonthPay + presentTotalMonthPay)/presentDays;
                totalPayService = (oldTotalMonthPayService + presentTotalMonthPayServiceIncentive)/presentDays+this.getIncrementedServiceIncentive(employee.getRCPLEmpSalSetupList().get(0),getEndDate());
                //logger.info("totalPay "+totalPay);

            }

            salariesUtil.setBasicAmount(totalPayBasic);
            salariesUtil.setServiceIncentiveAmount(totalPayService);
        }
        return salariesUtil;
    }



    public double getBasicAmountForOT(){
    	double latestBasicAmount = 0;
        double latestServiceAmount = 0;
    	long presentServiceDays=0;
    	long checkpresentServiceDays=0;
    	//Date sDate= new Date(getStartDate().getTime());
    	Date eDate= new Date(getEndDate().getTime());

    	Calendar fromcal=Calendar.getInstance();
		Calendar tocal=Calendar.getInstance();
    	fromcal.set(Calendar.MONTH,eDate.getMonth());
    	fromcal.add(Calendar.MONTH, -1);
		fromcal.set(Calendar.DAY_OF_MONTH, fromcal.getActualMinimum(Calendar.DATE));

		tocal.set(Calendar.MONTH,eDate.getMonth());
		tocal.add(Calendar.MONTH, -1);
		tocal.set(Calendar.DATE, tocal.getActualMaximum(Calendar.DATE));
		Date fromDateTemp=fromcal.getTime();
		Date toDateTemp=tocal.getTime();

    	double checkMnth = getCheckPayrollmonth(fromDateTemp, toDateTemp);
    	double prvMnthDays = 0;
        if(checkMnth==0){
    		double importCheck=getCheckAttendanceImport(toDateTemp);
        	if(importCheck>0){
        		prvMnthDays = getPrevmonthPresentDays(fromDateTemp, toDateTemp);

        	}else{
        		prvMnthDays = getPrevmonthPresentDaysDemo(fromDateTemp, toDateTemp);

        	}
 //       	logger.info("prvMnthDays 2 "+prvMnthDays);
    	}

    	double totalPayBasic = 0;
    	double basicAmount= 0;
        double serviceIncentive=0;
    	//serviceIncentive=0;

    	Calendar headercal=Calendar.getInstance();
		//Calendar tocal=Calendar.getInstance();
    	headercal.set(Calendar.MONTH,eDate.getMonth());
    	//headercal.add(Calendar.MONTH, -1);
    	headercal.set(Calendar.DAY_OF_MONTH, headercal.getActualMinimum(Calendar.DATE));
    	Date headerfromDate=headercal.getTime();
    	double presentDays=getNoOfPresentDaysForOT();
 //   	logger.info("presentDays are wrong...... "+presentDays);
    	double betweenDays=getBtndaysPresentDaysForOT(headerfromDate);
    	RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
    	//long servAndPredays= (long)prvMnthDays + employee.getPreattddays();
    	presentServiceDays = (long)prvMnthDays + (long)betweenDays + employee.getPreattddays()+(long)presentDays;
    	checkpresentServiceDays=(long)prvMnthDays + (long)betweenDays + employee.getPreattddays();
    	//RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
        EmployeeUtil employeeUtil = new EmployeeUtil();
        //RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
        RchrDesignation desig = employeeUtil.getEmployeeDesignation(employee,startDate, endDate);
        OBCriteria<RCHRDesigBasic> desigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));
        desigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,presentServiceDays));
       	desigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,presentServiceDays));

        for(RCHRDesigBasic basic:desigbasic.list()){
        	long toDays = basic.getTodays();
        	//long checkpresentServiceDays=presentServiceDays+(long)presentDays;
        	if(checkpresentServiceDays<=toDays){
                totalPayBasic=basic.getBasicamount().doubleValue();
        		//serviceIncentive=basic.getServiceincentive().doubleValue();
        		logger.info("totalPay "+totalPayBasic);
        	}else{
        		//logger.info("else statement");
        		OBCriteria<RCHRDesigBasic> latestDesigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        		latestDesigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));
        		latestDesigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,checkpresentServiceDays));
        		latestDesigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,checkpresentServiceDays));

        		double ServiceDaysPresents = checkpresentServiceDays-toDays;
        		double oldServiceDayspresents = presentDays - ServiceDaysPresents;
        		double presentTotalMonthPay = 0;
        		double oldTotalMonthPay = 0;
        		for(RCHRDesigBasic latestBasic:latestDesigbasic.list()){
        			//logger.info("2nd forLoop ");
        			latestBasicAmount = latestBasic.getBasicamount().doubleValue();

        			//logger.info("latestBasicAmount "+latestBasicAmount);

        		}
        		presentTotalMonthPay = latestBasicAmount*ServiceDaysPresents;

                        //logger.info("ServiceDaysPresents "+ServiceDaysPresents);
        		//logger.info("presentTotalMonthPay "+presentTotalMonthPay);
        		basicAmount=basic.getBasicamount().doubleValue();
        		serviceIncentive=basic.getServiceincentive().doubleValue();
        		oldTotalMonthPay = basicAmount * oldServiceDayspresents;


                totalPayBasic = (oldTotalMonthPay + presentTotalMonthPay)/presentDays;
        		//logger.info("totalPay "+totalPay);

        	}


        }

       return totalPayBasic;
    }

    public double getServiceIncentiveForOT(){
    	double latestServiceAmount = 0;
    	long presentServiceDays=0;
    	long checkpresentServiceDays=0;
    	Date eDate= new Date(getEndDate().getTime());

    	Calendar fromcal=Calendar.getInstance();
		Calendar tocal=Calendar.getInstance();
    	fromcal.set(Calendar.MONTH,eDate.getMonth());
    	fromcal.add(Calendar.MONTH, -1);
		fromcal.set(Calendar.DAY_OF_MONTH, fromcal.getActualMinimum(Calendar.DATE));

		tocal.set(Calendar.MONTH,eDate.getMonth());
		tocal.add(Calendar.MONTH, -1);
		tocal.set(Calendar.DATE, tocal.getActualMaximum(Calendar.DATE));
		Date fromDateTemp=fromcal.getTime();
		Date toDateTemp=tocal.getTime();

    	double checkMnth = getCheckPayrollmonth(fromDateTemp, toDateTemp);
    	double prvMnthDays = 0;
        if(checkMnth==0){
    		double importCheck=getCheckAttendanceImport(toDateTemp);
        	if(importCheck>0){
        		prvMnthDays = getPrevmonthPresentDays(fromDateTemp, toDateTemp);
        	}else{
        		prvMnthDays = getPrevmonthPresentDaysDemo(fromDateTemp, toDateTemp);
        	}

    	}
    	double totalPay = 0;
    	double serviceIncentive=0;
    	Calendar headercal=Calendar.getInstance();
		//Calendar tocal=Calendar.getInstance();
    	headercal.set(Calendar.MONTH,eDate.getMonth());
    	//cal.add(Calendar.MONTH, -1);
    	headercal.set(Calendar.DAY_OF_MONTH, headercal.getActualMinimum(Calendar.DATE));
    	Date headerfromDate=headercal.getTime();
    	double presentDays=getNoOfPresentDaysForOT();
    	double betweenDays=getBtndaysPresentDaysForOT(headerfromDate);
    	RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
    	//long servAndPredays= (long)prvMnthDays + employee.getPreattddays();
        presentServiceDays = (long)prvMnthDays + (long)betweenDays + employee.getPreattddays()+(long)presentDays;
        checkpresentServiceDays=(long)prvMnthDays + (long)betweenDays + employee.getPreattddays();
        EmployeeUtil employeeUtil = new EmployeeUtil();
    	//RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
        RchrDesignation desig = employeeUtil.getEmployeeDesignation(employee,startDate, endDate);
        OBCriteria<RCHRDesigBasic> desigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));
        desigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,presentServiceDays));
       	desigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,presentServiceDays));

        for(RCHRDesigBasic basic:desigbasic.list()){
        	long toDays = basic.getTodays();
        	if(checkpresentServiceDays <=toDays){
        		//basicAmount=basic.getBasicamount().doubleValue();
        		totalPay=basic.getServiceincentive().doubleValue();
        		logger.info("totalPay "+totalPay);
        	}else{
 //       		logger.info("else statement");
        		OBCriteria<RCHRDesigBasic> latestDesigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
        		latestDesigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));
        		latestDesigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,checkpresentServiceDays));
        		latestDesigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,checkpresentServiceDays));

        		double ServiceDaysPresents = checkpresentServiceDays-toDays;
        		double oldServiceDayspresents = presentDays - ServiceDaysPresents;
        		double presentTotalMonthPay = 0;
        		double oldTotalMonthPay = 0;
        		for(RCHRDesigBasic latestBasic:latestDesigbasic.list()){
        			//logger.info("2nd forLoop service");
        			//latestBasicAmount = latestBasic.getBasicamount().doubleValue();
        			latestServiceAmount = latestBasic.getServiceincentive().doubleValue();

        			//logger.info("latestServiceAmount service "+latestServiceAmount);
        		}
        		presentTotalMonthPay = latestServiceAmount*ServiceDaysPresents;
        		//logger.info("ServiceDaysPresents service "+ServiceDaysPresents);
        		//logger.info("presentTotalMonthPay service "+presentTotalMonthPay);
        		//basicAmount=basic.getBasicamount().doubleValue();
        		serviceIncentive=basic.getServiceincentive().doubleValue();
        		oldTotalMonthPay = serviceIncentive* oldServiceDayspresents;
        		//logger.info("oldServiceDayspresents service "+oldServiceDayspresents);
        		//logger.info("oldTotalMonthPay service "+oldTotalMonthPay);

        		totalPay = (oldTotalMonthPay + presentTotalMonthPay)/presentDays;
        		logger.info("totalPay service "+totalPay);

        	}
        }
       return totalPay;
    }
}
