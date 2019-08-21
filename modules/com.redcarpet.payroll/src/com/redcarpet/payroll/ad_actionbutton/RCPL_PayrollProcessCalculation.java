package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RchrDesignation;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.ProcessStatus;
import com.redcarpet.payroll.data.*;
import com.redcarpet.payroll.util.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
*
* @author Matheen
* Customized By Tirumalakumar.U
*  Surya rao.A
*  Vinay kumar.K
*
*/
@SuppressWarnings("unchecked")
public class RCPL_PayrollProcessCalculation extends DalBaseProcess  implements BundleProcess{
    public static final Logger logger = Logger.getLogger(RCPL_PayrollProcessCalculation.class);
    @Override
    public void doExecute(ProcessBundle bundle) throws Exception {

        OBError err = new OBError();
        ProcessStatus processStatus = new ProcessStatus(bundle, DalBaseProcess.PROCESSING);
        if (processStatus.isProcessing()){
            logger.info("In Second Condtion Already this process is in Running state ");
            //throw new SerialException("Already this Process is Running...!");
            err.setMessage(ProcessStatus.PREVIOUS_PROCESS_RUNNING);
            err.setTitle("Error");
            err.setType("Error");
            //bundle.setResult(err);
            bundle.setResult(err);
        } else {
            doIt(bundle);
        }
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        Long starTime = System.currentTimeMillis();
        OBContext.setAdminMode();
        String strProcessId = bundle.getParams().get("Rcpl_Payrollprocess_ID").toString();
        OBError err = new OBError();
        logger.info("Process Id is "+bundle.getProcessId());
        logger.info("strProcessId  "+strProcessId);
        //OBError err = new OBError();
        int errorMsg ;
        try {
            errorMsg = calculatePayroll(strProcessId);
            err.setMessage("Payroll Process Completed Successfully for "+errorMsg+" Employees");
            err.setTitle("Success");
            err.setType("Success");
            OBDal.getInstance().commitAndClose();
        } catch (Exception e) {
            err.setMessage(e.getMessage());
            err.setTitle("Error");
            err.setType("Error");
            //bundle.setResult(err);
            e.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }
        bundle.setResult(err);
        OBContext.restorePreviousMode();
        Long endTime = System.currentTimeMillis();
        //Long starTime = System.currentTimeMillis();
        logger.info("Total Time is "+ TimeUnit.MILLISECONDS.toSeconds(endTime-starTime));
        logger.info("Total Time is "+ TimeUnit.MILLISECONDS.toMinutes(endTime-starTime));
    }

    private int calculatePayroll(String strProcessId) throws ServletException, Exception {
    	Long starTime = System.currentTimeMillis();
    	double basicAmount=0,serviceIncentive=0;
        RCPL_PayrollProcess pp = OBDal.getInstance().get(com.redcarpet.payroll.data.RCPL_PayrollProcess.class, strProcessId);
        String aurxilaryParameter = "";

        if (pp.getEmployee() !=null && !pp.getEmployee().getId().equals("")){
            aurxilaryParameter = " AND emp.id = '"+pp.getEmployee().getId()+"' ";
        }
        if (pp.getEmployeeType() != null && !pp.getEmployeeType().equals("")){
            aurxilaryParameter = aurxilaryParameter + " AND emp.employeeType= '"+pp.getEmployeeType()+"' ";
        }
        //aurxilaryParameter = " AND emp.employee.id= '"+pp.getEmployee().getId()+"' ";
        String hql = " SELECT ess FROM RCPL_EmpSalSetup ess JOIN ess.employee emp "
                + " WHERE ess.payrollTemplate.type='" + pp.getPayrollPeriod().getType() + "' "
                + " AND emp.employeestatus='W'"+ aurxilaryParameter +" AND ess.active=true and ess.employee not in (SELECT emppp.employee FROM RCPL_EmpPayrollProcess as emppp join emppp.payrollProcess as ppp "
                + " WHERE ppp.id='"+strProcessId +"' )";
        //AND emp.employeeType in ('Staff','Semi Staff')
       // List<RCPL_EmpSalSetup> empList = OBDal.getInstance().getSession().createQuery(hql).list();
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setMaxResults(pp.getMaxresults().intValue());
        List<RCPL_EmpSalSetup> empList=q.list();
        Map<String, PayrollEmployee> empPojos = new HashMap<String, PayrollEmployee>();
        Map<String, AttendancePojo> mapAttendancePojo = new HashMap<String, AttendancePojo>();
        // Initialization of AttendancePojo by getting from Attendance Util...

        for (RCPL_EmpSalSetup empShiftSal : empList) {
            EmployeeUtil employeeUtil = new EmployeeUtil();
        	basicAmount=0;
        	serviceIncentive=0;
            RCPL_EmpPayrollProcess empPay = OBProvider.getInstance().get(RCPL_EmpPayrollProcess.class);
            AttendancePojo attandancePojo = new AttendancePojo();
            RchrEmployee employee = empShiftSal.getEmployee();
            // Modified by Vinay
            AttendanceUtil aUtil=new AttendanceUtil(pp.getStartingDate(), pp.getEndingDate(),employee.getId());
            attandancePojo.setNoOfDaysPresent(aUtil.getNoOfDaysPresent());
            attandancePojo.setNoOfWeekOffs(aUtil.getNoOfWeekOffs());
            //attandancePojo.setNoOfLeaves(aUtil.getNoOfLeaves());
            attandancePojo.setNoOfDaysWeeklyOffWorked(aUtil.getNoOfDaysWeeklyOffWorked());
            attandancePojo.setNoOfWeekOffDaysIfWeekOffApplicable(aUtil.getNoOfWeekOffDaysIfWeekOffApplicable());
            attandancePojo.setNoOfWorkingDays(aUtil.getNoOfWorkingDays());
            attandancePojo.setNoOfLeaves(aUtil.getNoOfLeavesNew());
            //logger.info("Leave is "+aUtil.getNoOfLeavesNew());
            attandancePojo.setNoOfCLs(aUtil.getNoOfLeavesCL());
            attandancePojo.setNoOfPhysicalDaysPresent(aUtil.getNoOfPhysicalWorkingDaysPresent());
            attandancePojo.setNoOfLOP(aUtil.getNoOfLOPNew());
            attandancePojo.setLeavesCountMonth(aUtil.getLeavesCountMonth());
            mapAttendancePojo.put(employee.getId(),attandancePojo);
            //attandancePojo.setAbsents(aUtil.getNoOfDaysPresent());
            RchrDesignation rchrDesignation = employeeUtil.getEmployeeDesignation(employee,pp.getStartingDate(),pp.getEndingDate());
            if(employee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
	            if(empShiftSal.isNotdesig()){
	            	basicAmount=empShiftSal.getPerdaybasic().doubleValue();
	            	serviceIncentive=empShiftSal.getServiceince().doubleValue();
	            }
	            else{
                  SalariesUtil salariesUtil = aUtil.getSalariesUtil(rchrDesignation,employee);
	            	  basicAmount=salariesUtil.getBasicAmount();
	                serviceIncentive=salariesUtil.getServiceIncentiveAmount();
	                logger.info("Basic and Service Incentive is "+basicAmount +" and "+serviceIncentive);
	            }
	            empShiftSal.setPerdaybasic(new BigDecimal(basicAmount));
              empShiftSal.setServiceince(new BigDecimal(serviceIncentive));
            }
            empPay.setEmployee(employee);
            empPay.setEmployeeDepartment(employeeUtil.getEmployeeDepartment(employee,pp.getStartingDate(),pp.getEndingDate()));
            empPay.setDesignation(employeeUtil.getEmployeeDesignation(employee,pp.getStartingDate(),pp.getEndingDate()));
            empPay.setSubDepartment(employeeUtil.getEmployeeSubDepartment(employee,pp.getStartingDate(),pp.getEndingDate()));
            empPay.setPFApplicable(employee.isPFApplicable());
            empPay.setBank(employee.isBank());
            empPay.setStartingDate(pp.getStartingDate());
            empPay.setEndingDate(pp.getEndingDate());
            empPay.setProcessingDays(pp.getProcessingDays());
            empPay.setPresentdays(new BigDecimal(attandancePojo.getNoOfDaysPresent()));
            empPay.setWeekoffworked(new BigDecimal(attandancePojo.getNoOfDaysWeeklyOffWorked()));
            empPay.setRoom(employee.getRoom());
            empPay.setAccountNo(employee.getAccountNo());
            empPay.setEmployeeType(employee.getEmployeeType());
            if(employee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)||employee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)){
            	BigDecimal lateCount = (new BigDecimal(new PayrollDBSessionUtil(employee.getId(),
                        pp.getStartingDate(), pp.getEndingDate()).getLateComingCountForStaff()));
            	empPay.setLateDeduction(lateCount);
            	//logger.info(empPay.getLateDeduction());
            	//empPay.setLeaves(new BigDecimal(aUtil.getNoOfLeaves()-aUtil.getNoOfLOP()));
            	empPay.setLeaves(new BigDecimal(attandancePojo.getNoOfLeaves()));
                double noOfWeekOffsNotApplicable = attandancePojo.getNoOfWeekOffs();
            	double totalDaysInMonth = attandancePojo.getNoOfWorkingDays();
                //double presentDays = attandancePojo.getNoOfDaysPresent();
                double present = attandancePojo.getNoOfDaysPresent();
                logger.info("Testing 3 Present Days "+present);
                double noOfWeekOffIfApplicable = attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable();
                double totalNoOfLeaves = attandancePojo.getNoOfLeaves();
                double LOP = attandancePojo.getNoOfLOP();
                //double noOfCasualLeaves=totalNoOfLeaves-LOP;
                double leaveCount = attandancePojo.getLeavesCountMonth();
                double no_of_days_weeklyoff=attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable();
            	double result=0;
		double actualLeave = totalNoOfLeaves;
            	if(present>=20){
               	 if(present==(totalDaysInMonth-no_of_days_weeklyoff) ||
               			 totalDaysInMonth <=(present + totalNoOfLeaves +
                                 noOfWeekOffIfApplicable-noOfWeekOffsNotApplicable)){
               		result=present+totalNoOfLeaves+noOfWeekOffIfApplicable-noOfWeekOffsNotApplicable;
               		logger.info("presentDays above 20 days "+result);
               	 }else{
               		 result = (present + totalNoOfLeaves + noOfWeekOffIfApplicable-noOfWeekOffsNotApplicable);
                     logger.info("Result is 2 "+result);
               	 }
               }else if(present<20 && present>=10){
			logger.info("totalNoOfLeaves in 10 to 20 "+totalNoOfLeaves);
               		if(totalNoOfLeaves>1){
               			result = (present + noOfWeekOffIfApplicable-noOfWeekOffsNotApplicable + totalNoOfLeaves-1);
				//actualLeave = totalNoOfLeaves-1;
                        	logger.info("Result is between 10 to 20 presentDays "+result);
               		}

               		else{
			logger.info("totalNoOfLeaves in else "+totalNoOfLeaves);
               			result = (present + noOfWeekOffIfApplicable-noOfWeekOffsNotApplicable);
                        	logger.info("Result is 4 "+result);
               		}
			if (totalNoOfLeaves > 0)
                        actualLeave = totalNoOfLeaves-1;
   	    		   } else {
                    if (totalNoOfLeaves > leaveCount) {
                        result = (present + noOfWeekOffIfApplicable - noOfWeekOffsNotApplicable + totalNoOfLeaves - leaveCount);
                        logger.info("Result is 5 "+result);
                    } else {
                        result = (present + noOfWeekOffIfApplicable - noOfWeekOffsNotApplicable);
                        logger.info("Result is 6 "+result);
                    }

                }
                logger.info("getNoOfWeekOffs "+attandancePojo.getNoOfWeekOffs());
                logger.info("getNoOfWeekOffDaysIfWeekOffApplicable "+attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable());

                if((attandancePojo.getNoOfWorkingDays()-(attandancePojo.getNoOfDaysPresent()+
                    attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable()-attandancePojo.getNoOfWeekOffs()+
                    attandancePojo.getNoOfLeaves())) >= 0){
            	    empPay.setAbsents(new BigDecimal(attandancePojo.getNoOfWorkingDays()-
                        (result)));
			empPay.setLeaves(new BigDecimal(actualLeave));
                }else{
            	    empPay.setAbsents(new BigDecimal(0));
                }
                empPay.setWeeklyOff(new BigDecimal(attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable()-attandancePojo.getNoOfWeekOffs()));
                //completed if(Staff or Semi-Staff Logic)
                empPay.setGrosspay(empShiftSal.getGrossAmtPerYear());
            }else{
	            if((aUtil.getNoOfWorkingDays()-(attandancePojo.getNoOfDaysPresent()+attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable()))<0){
	            	empPay.setAbsents(new BigDecimal(0));
	            }else{

	              empPay.setAbsents(new BigDecimal(attandancePojo.getNoOfWorkingDays()-(attandancePojo.getNoOfDaysPresent()+attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable())));

	            		}
	            empPay.setBasicamount(new BigDecimal(basicAmount));
	            empPay.setServincentiveamount(new BigDecimal(serviceIncentive));
	            empPay.setWeeklyOff(new BigDecimal(attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable()));
            }
            empPay.setCurrentservicedays(new BigDecimal(employee.getPreattddays().doubleValue()+attandancePojo.getNoOfDaysPresent()));
            empPay.setPayrollProcess(pp);
            empPay.setProcessNow(Boolean.TRUE);
            OBDal.getInstance().save(empPay);
            String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            PayrollEmployee payrollEmployee = new PayrollEmployee(uuid, empPay.getId(), pp.getPayrollPeriod().getId(),
                    employee.getId(), employee.getEmployeeType(), pp.getStartingDate(), pp.getEndingDate(),
                    empShiftSal.getGrossAmtPerYear().doubleValue(),
                    empShiftSal.getGrossLimit().doubleValue(), empShiftSal.getPerDaySalary().doubleValue(),basicAmount,
                    empShiftSal.getPayrollTemplate().getId(), empShiftSal.getId(),empShiftSal.getTaperyear().doubleValue(),
                    empShiftSal.getQadperyear().doubleValue(), serviceIncentive);
            empPojos.put(employee.getId(), payrollEmployee);
        }
        OBDal.getInstance().flush();
        PayrollProcessInsertion payrollInsertion = new PayrollProcessInsertion();
        //payrollInsertion.getPayAdditionLines(mapAttendancePojo,empPojos);
        HashMap<RchrEmployee, HashMap> rchrEmployeeListHashMap = payrollInsertion.getPayAdditionLines(mapAttendancePojo,empPojos);
        payrollInsertion.insertPayAdditionsIncentives(mapAttendancePojo, empPojos);
        payrollInsertion.insertPayDeductions(mapAttendancePojo,empPojos, rchrEmployeeListHashMap);
        payrollInsertion.insertPayDeductionIncentives(empPojos, mapAttendancePojo);
        //payrollInsertion.insertServiceDaysToEmployees(attandancePojo.getNoOfDaysPresent(),empPojos);
        payrollInsertion.insertTotalWagesToEmployees(empPojos);
        //payrollInsertion.updateAttendanceLines(pp.getStartingDate(),pp.getEndingDate(),empPojos);
        pp.setProcessed(Boolean.TRUE);
        pp.setProcess(Boolean.TRUE);
        Long endTime = System.currentTimeMillis();
        //Long starTime = System.currentTimeMillis();
        logger.info("Total Time is "+TimeUnit.MILLISECONDS.toMinutes(endTime-starTime));
        return empList.size();
    }
/*
    private void insertServiceDaysToEmployees(Double presentDays,Map<String, PayrollEmployee> empPojos){
    	Iterator<String> it = empPojos.keySet().iterator();
        while (it.hasNext()) {
        	 PayrollEmployee emp = empPojos.get(it.next());
             RchrEmployee empObj = OBDal.getInstance().get(RchrEmployee.class, emp.employeeId);
             empObj.setPreattddays(empObj.getPreattddays()+(presentDays.longValue()));
             OBDal.getInstance().save(empObj);

        }
    }

    private void updateAttendanceLines(Date fromDate, Date toDate, Map<String, PayrollEmployee> empPojos) throws Exception{
    	Iterator<String> it = empPojos.keySet().iterator();
        while (it.hasNext()) {
        PayrollEmployee emp = empPojos.get(it.next());
        Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("update rchr_attend_temp set ispayroll ='Y' where rchr_employee_id= '"+emp.employeeId+"' and attdate between '"+fromDate+"' and '"+toDate+"'");
        }
    }





    private double getSumShiftTimeInHrs(String employeeId, Date start, Date end) {
        String strStart = PayrollUtils.getInstance().getDBCompatiableDate(start);
        String strEnd = PayrollUtils.getInstance().getDBCompatiableDate(end);
        double retVal = 0;
        try {
            String strValue = PayrollDBUtilityData.getSumOfShiftTimeInHoursFromDates(new DalConnectionProvider(), employeeId, strStart, strEnd);
            retVal = Double.valueOf(strValue);
        } catch (Exception ex) {
        }
        return retVal;
    }

     private BigDecimal getPayHourlyAdditionValueForWorker(PayrollEmployee emp) {
        Date startingDate = emp.startDate;
        Date endingDate = emp.endDate;
        int no_of_days_in_month = PayrollUtils.getDaysDifference(emp.startDate, emp.endDate);
        double tempSalary = 0;
        String strStartDate = PayrollUtils.getInstance().getDBCompatiableDate(startingDate);
        String strEndDate = PayrollUtils.getInstance().getDBCompatiableDate(endingDate);
            BigDecimal perDaySal = OBDal.getInstance().get(RCPL_EmpSalSetup.class, emp.empSalSetupId).getPerDaySalary();
            try {
            PayrollDBUtilityNewData[] attendanceData = PayrollDBUtilityNewData.getAttendanceLineIdOnDate(new DalConnectionProvider(), emp.employeeId, strStartDate, strEndDate);

            for (int s = 0; s < attendanceData.length; s++) {

            String strAttendanceLineId = attendanceData[s].rchrAttendLineId;
            //logger.info("strAttendanceLineId is" +strAttendanceLineId);
            BigDecimal actual = OBDal.getInstance().get(RchrAttendLine.class, strAttendanceLineId).getShift().getShiftInMins();

            String strDuration = OBDal.getInstance().get(RchrAttendLine.class, strAttendanceLineId).getDuration();
            BigDecimal worked = new BigDecimal(strDuration);

            BigDecimal lateTime = OBDal.getInstance().get(RchrAttendLine.class, strAttendanceLineId).getLatetime();
            BigDecimal testvar = new BigDecimal("0");
            int res1=actual.compareTo(testvar);
            int res2=worked.compareTo(testvar);
            if((res1==1) && (res2==1))
            {
            tempSalary += (perDaySal.divide(actual, 6, RoundingMode.UP)).multiply((worked.subtract(lateTime))).doubleValue();
            }

            }
            }catch (Exception e) {
            }

        return new BigDecimal(tempSalary);
    }


    private BigDecimal getActualShiftTimeInMins(String employeeId, Date tempDate, boolean isActualShiftTimeFromMasters) {
        String strDate = PayrollUtils.getInstance().getDBCompatiableDate(tempDate);
        BigDecimal retVal = new BigDecimal(0);
        try {
            if (isActualShiftTimeFromMasters) {
                String strAttendanceLineId = PayrollDBUtilityData.getAttendanceLineIdOnDate(new DalConnectionProvider(), employeeId, strDate);
                retVal = OBDal.getInstance().get(RchrAttendLine.class, strAttendanceLineId).getShift().getShiftInMins();
            } else {
                String strAttendanceLineId = PayrollDBUtilityData.getAttendanceLineIdOnDate(new DalConnectionProvider(), employeeId, strDate);
                String strDuration = OBDal.getInstance().get(RchrAttendLine.class, strAttendanceLineId).getDuration();
                retVal = new BigDecimal(strDuration);
            }
        } catch (Exception e) {
        }
        return retVal;
    }

    private BigDecimal getLateTimeInMins(String employeeId, Date tempDate) {
        String strDate = PayrollUtils.getInstance().getDBCompatiableDate(tempDate);
        BigDecimal retVal = new BigDecimal(0);
        try {
            String strAttendanceLineId = PayrollDBUtilityData.getAttendanceLineIdOnLateDate(new DalConnectionProvider(), employeeId, strDate);
            retVal = (strAttendanceLineId != null) ? OBDal.getInstance().get(RchrAttendLine.class, strAttendanceLineId).getLatetime() : retVal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    
    public double getBonusAmount(String empId, String start, String end) {
        String strRetValue = "0";
        try {
            strRetValue = PayrollDBUtilityData.getSumOfBonusAmount(new DalConnectionProvider(), empId, start, end);
        } catch (Exception e) {
        }
        return new BigDecimal(strRetValue).doubleValue();
    }
*/
}

