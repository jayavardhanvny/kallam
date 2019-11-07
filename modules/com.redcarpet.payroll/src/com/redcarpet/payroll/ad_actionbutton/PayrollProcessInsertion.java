package com.redcarpet.payroll.ad_actionbutton;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.rcss.humanresource.data.RCHR_Room;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import com.redcarpet.payroll.util.RoomPojo;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import javax.servlet.ServletException;
import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_EmpDedIncentives;
import com.redcarpet.payroll.data.RCPL_EmpPayDead;
import com.redcarpet.payroll.data.RCPL_EmpPayIncentives;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_PayrollIncentiveLines;
import com.redcarpet.payroll.data.RCPL_PayrollTemplate;
import com.redcarpet.payroll.data.RCPL_PayrollTemplateLines;
import  com.redcarpet.payroll.data.RCPL_EmpPayHead;

/**
*
* @author Vinay kumar.K
*
*/
public class PayrollProcessInsertion{
	public static final Logger logger = Logger.getLogger(PayrollProcessInsertion.class);
    public void insertPayAdditionsIncentives(Map<String, AttendancePojo> mapAttendancePojo, Map<String, PayrollEmployee> empPojos) throws ServletException {
   	   Iterator<String> it = empPojos.keySet().iterator();
          while (it.hasNext()) {
              String key = it.next();
              PayrollEmployee emp = empPojos.get(key);
							AttendancePojo attandancePojo = mapAttendancePojo.get(key);
              RCPL_PayrollTemplate template = OBDal.getInstance().get(RCPL_PayrollTemplate.class, emp.payrollTemplateId);
              List<RCPL_PayrollIncentiveLines> lineList = OBDal.getInstance().createCriteria(RCPL_PayrollIncentiveLines.class).add(Restrictions.eq(RCPL_PayrollIncentiveLines.PROPERTY_PAYROLLTEMPLATE, template)).list();
              int res=0;
              for (RCPL_PayrollIncentiveLines line : lineList) {
                  if (!line.getIncentives().isDeductions()) {
                      RCPL_EmpPayIncentives empAddIncent = OBProvider.getInstance().get(RCPL_EmpPayIncentives.class);
                      empAddIncent.setIncentives(line.getIncentives());
                      final BigDecimal incentivesAddition = this.getIncentivesAddition(emp, line.getIncentives().getId(), true, attandancePojo);
                      empAddIncent.setAdditions(incentivesAddition);
                      empAddIncent.setEmployeePayrollProcess(OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, emp.empPayrollProcessId));
                      OBDal.getInstance().save(empAddIncent);
                  }

              }
          }
   }


	 public void insertPayDeductions(Map<String,AttendancePojo> mapAttendancePojo,Map<String, PayrollEmployee> empPojos, HashMap<RchrEmployee,HashMap> rchrEmployeeListHashMap) throws ServletException, Exception {
			 Iterator<String> it = empPojos.keySet().iterator();
			 HashMap<RCHR_Room, RoomPojo> roomRoomUtilHashMap = new HashMap<>();
			 while (it.hasNext()) {
					 String key = it.next();
					 PayrollEmployee emp = empPojos.get(key);
					 AttendancePojo attandancePojo = mapAttendancePojo.get(key);
					 RCPL_PayrollTemplate template = OBDal.getInstance().get(RCPL_PayrollTemplate.class, emp.payrollTemplateId);
					 List<RCPL_PayrollTemplateLines> lineList = OBDal.getInstance().createCriteria(RCPL_PayrollTemplateLines.class).
							 add(Restrictions.eq(RCPL_PayrollTemplateLines.PROPERTY_PAYROLLTEMPLATE, template)).
							 add(Restrictions.eq(RCPL_PayrollTemplateLines.PROPERTY_ISADDITION, false)).list();
				 //double totalDaysInMonth = attandancePojo.getNoOfWorkingDays();
					 //double presentDays = attandancePojo.getNoOfDaysPresent();
					 //double no_of_days_weeklyoff=attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable();
					 for (RCPL_PayrollTemplateLines line : lineList) {
							 RCPL_EmpPayDead empAdd = OBProvider.getInstance().get(RCPL_EmpPayDead.class);
							 BigDecimal grossPay = this.getPayDeduction(emp, line.getId(),attandancePojo, rchrEmployeeListHashMap, roomRoomUtilHashMap);
							 empAdd.setDeductions(grossPay);
							logger.info(line.getPayDeductions().getName());
							 empAdd.setPayDeductions(line.getPayDeductions());
							 empAdd.setEmployeePayrollProcess(OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, emp.empPayrollProcessId));
							 OBDal.getInstance().save(empAdd);
					 }
			 }
	 }

		public void insertPayDeductionIncentives(Map<String, PayrollEmployee> empPojos, Map<String,AttendancePojo> mapAttendancePojo) throws ServletException {
        Iterator<String> it = empPojos.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            PayrollEmployee emp = empPojos.get(key);
            AttendancePojo attendancePojo = mapAttendancePojo.get(key);
            RCPL_PayrollTemplate template = OBDal.getInstance().get(RCPL_PayrollTemplate.class, emp.payrollTemplateId);
            List<RCPL_PayrollIncentiveLines> lineList = OBDal.getInstance().createCriteria(RCPL_PayrollIncentiveLines.class).
                    add(Restrictions.eq(RCPL_PayrollIncentiveLines.PROPERTY_PAYROLLTEMPLATE, template)).list();
            for (RCPL_PayrollIncentiveLines line : lineList) {
                if (line.getIncentives().isDeductions()) {
                    RCPL_EmpDedIncentives empAddIncent = OBProvider.getInstance().get(RCPL_EmpDedIncentives.class);
                    empAddIncent.setIncentives(line.getIncentives());
                    final BigDecimal incentivesAddition = getIncentivesAddition(emp, line.getIncentives().getId( ), false, attendancePojo);
                    empAddIncent.setDeductions(incentivesAddition);
                    empAddIncent.setEmployeePayrollProcess(OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, emp.empPayrollProcessId));
                    OBDal.getInstance().save(empAddIncent);
                }
            }
        }
    }


    public void insertTotalWagesToEmployees(Map<String, PayrollEmployee> empPojos){
    	double basicAmount=0,serviceIncentive=0;
    	Iterator<String> it = empPojos.keySet().iterator();
        while (it.hasNext()) {
        	 basicAmount=0;
        	 serviceIncentive=0;
        	 PayrollEmployee emp = empPojos.get(it.next());
        	 AttendanceUtil aUtil = new AttendanceUtil(emp.payrollPeriodId, emp.employeeId);
        	 RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, emp.employeeId);
        	 if(employee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
        		  RCPL_EmpSalSetup empSetup = OBDal.getInstance().get(RCPL_EmpSalSetup.class, emp.empSalSetupId);
        		 if(empSetup.isNotdesig()){
                 	basicAmount=empSetup.getPerdaybasic().doubleValue();
                 	serviceIncentive=empSetup.getServiceince().doubleValue();
                 }else{
                	 basicAmount = aUtil.getBasicAmount();
                	 serviceIncentive = aUtil.getServiceIncentive();
                 }
        		 empSetup.setPerDaySalary(new BigDecimal(Math.round(basicAmount+serviceIncentive)));
        	 }
        }
    }


    private BigDecimal getPayDeduction(PayrollEmployee emp, String templateLineId, AttendancePojo attandancePojo, HashMap<RchrEmployee, HashMap> rchrEmployeeListHashMap,
                                        HashMap<RCHR_Room, RoomPojo> roomRoomUtilHashMap) throws ServletException, Exception {
        PayCalculator calc = new PayCalculator(emp, templateLineId);
        double netSal = calc.calculateShiftSalaryDeductions(false,attandancePojo, rchrEmployeeListHashMap, roomRoomUtilHashMap);
	    logger.info("Net Sal: "+netSal);
        logger.info("Employee Id: "+emp.employeeId);
        return new BigDecimal(netSal);
    }

		private BigDecimal getIncentivesAddition(PayrollEmployee emp, String strIncentiveId, boolean isAdditionIncentive, AttendancePojo attendancePojo) throws ServletException {
        IncentivesCalculator iCal = new IncentivesCalculator(emp, strIncentiveId, isAdditionIncentive);
        double shiftIncPay = iCal.calculateShiftIncentives(attendancePojo);
        return new BigDecimal(shiftIncPay);
    }


    public HashMap<RchrEmployee, HashMap> getPayAdditionLines(Map<String,AttendancePojo> mapAttendancePojo, Map<String, PayrollEmployee> empPojos) {
				HashMap<RchrEmployee,HashMap> rchrEmployeeHashMap = new HashMap<>();
        Iterator<String> it = empPojos.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            PayrollEmployee emp = empPojos.get(key);
            AttendancePojo attandancePojo = mapAttendancePojo.get(key);
            RCPL_PayrollTemplate template = OBDal.getInstance().get(RCPL_PayrollTemplate.class, emp.payrollTemplateId);
            List<RCPL_PayrollTemplateLines> lineList = OBDal.getInstance().createCriteria(RCPL_PayrollTemplateLines.class).
                    add(Restrictions.eq(RCPL_PayrollTemplateLines.PROPERTY_PAYROLLTEMPLATE, template)).
                    add(Restrictions.eq(RCPL_PayrollTemplateLines.PROPERTY_ISADDITION, true)).list();
            String empType = OBDal.getInstance().get(RchrEmployee.class, emp.employeeId).getEmployeeType();
            //logger.info("Testing 2 Presents "+attandancePojo.getNoOfDaysPresent());
						HashMap<String, BigDecimal> stringBigDecimalHashMap = new HashMap<>();
            if (!empType.equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)) {
                for (RCPL_PayrollTemplateLines line : lineList) {
                    try {
                        RCPL_EmpPayHead empAdd = OBProvider.getInstance().get(RCPL_EmpPayHead.class);
                        BigDecimal grossPay = new BigDecimal(0);
                        grossPay = getPayAdditionValue(attandancePojo,emp, line.getId());
                        logger.info("Testing perDayPay "+grossPay);
                        empAdd.setAdditions(grossPay);
                        empAdd.setPayAdditions(line.getPayAdditions());
                        empAdd.setEmployeePayrollProcess(OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, emp.empPayrollProcessId));
												stringBigDecimalHashMap.put(line.getPayAdditions().getCategory(),grossPay);
                        OBDal.getInstance().save(empAdd);
                    } catch (Exception e) {
                    }
                }
            } else {
                for (RCPL_PayrollTemplateLines line : lineList) {
                    try {
                        //BigDecimal workerGrossPay = getPayHourlyAdditionValueForWorker(emp);
                    	BigDecimal perDayPay=getPayAdditionValue(attandancePojo,emp, line.getId());
                        logger.info("Testing perDayPay "+perDayPay);
                        RCPL_EmpPayHead empAdd = OBProvider.getInstance().get(RCPL_EmpPayHead.class);
                        //BigDecimal grossPay = new BigDecimal(0);
                        //grossPay = getWorkerPayAdditionValue(workerGrossPay, line, emp);
                       // empAdd.setAdditions(grossPay);
                        empAdd.setAdditions(perDayPay);
                        empAdd.setPayAdditions(line.getPayAdditions());
                        empAdd.setEmployeePayrollProcess(OBDal.getInstance().get(RCPL_EmpPayrollProcess.class, emp.empPayrollProcessId));
												stringBigDecimalHashMap.put(line.getPayAdditions().getCategory(),perDayPay);
                        OBDal.getInstance().save(empAdd);
                    } catch (Exception e) {
                        System.out.print("Exception 1");
                        e.printStackTrace();
                    }
                }
            }
						rchrEmployeeHashMap.put(OBDal.getInstance().get(RchrEmployee.class, emp.employeeId),stringBigDecimalHashMap);
        }
				return rchrEmployeeHashMap;
    }
    private BigDecimal getPayAdditionValue(AttendancePojo attandancePojo,PayrollEmployee emp, String templateLineId) {
   	 double result = 0;
   	// AttendanceUtil aUtil = new AttendanceUtil(emp.payrollPeriodId, emp.employeeId);
   	 double noOfWeekOffsNotApplicable = attandancePojo.getNoOfWeekOffs();
        RCPL_PayrollTemplateLines lineobj=OBDal.getInstance().get(RCPL_PayrollTemplateLines.class,templateLineId);
      //  RchrEmployee empTemp2 = OBDal.getInstance().get(RchrEmployee.class, emp.employeeId);
        PayCalculator payCalculator = new PayCalculator(emp, templateLineId);
        try {
            	  double netSal = payCalculator.calculateShiftSalaryAddition();
                double totalDaysInMonth = attandancePojo.getNoOfWorkingDays();
                //double presentDays = attandancePojo.getNoOfDaysPresent();
                double present = attandancePojo.getNoOfDaysPresent();
                //logger.info("Testing 3 Present Days "+present);
                double noOfWeekOffIfApplicable = attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable();
                double totalNoOfLeaves = attandancePojo.getNoOfLeaves();
                double LOP = attandancePojo.getNoOfLOP();
                double noOfCasualLeaves = totalNoOfLeaves;
                double leaveCount = attandancePojo.getLeavesCountMonth();
                //double no_of_days_weekoff_worked = attandancePojo.getNoOfWeekOffWorked();
                //double no_of_days_NoWork = attandancePojo.getNoOfDaysNoWorked();
                double no_of_days_weeklyoff=attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable();
                RchrEmployee empTemp = OBDal.getInstance().get(RchrEmployee.class, emp.employeeId);
                if (present == 0) {
                    return BigDecimal.ZERO;
                }
                if (empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)) {
                    result = getPerPeriodPay(emp, empTemp, netSal, totalDaysInMonth,no_of_days_weeklyoff, present,noOfWeekOffIfApplicable,
                   		 noOfCasualLeaves,LOP,noOfWeekOffsNotApplicable,leaveCount);
                        logger.info("Staff ");
                } else if (empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)) {
                    result = getPerPeriodPay(emp, empTemp, netSal, totalDaysInMonth,no_of_days_weeklyoff, present, noOfWeekOffIfApplicable,
                   		 noOfCasualLeaves,LOP,noOfWeekOffsNotApplicable,leaveCount);
                    logger.info("Semi Staff ");
                } else if (empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)) {
                	result = getPerPeriodPay(emp, empTemp, netSal, totalDaysInMonth,no_of_days_weeklyoff, present, noOfWeekOffIfApplicable,
                			noOfCasualLeaves,LOP,noOfWeekOffsNotApplicable,leaveCount);
                    logger.info("Operator "+result);
                }
           // }

        } catch (ServletException ex) {
            logger.info("ServletExeption "+ ex.getMessage());
        } catch (Exception ex) {
            logger.info("ServletExeption "+ ex.getMessage());
            //ex.printStackTrace();
        }
        return new BigDecimal(result);
   }
    private double getPerPeriodPay(PayrollEmployee emp, RchrEmployee empTemp, double netSal, double totalDaysInMonth,
            double no_of_days_weeklyoff, double presentDays, double noOfDaysWeekOffIfApplicable,
            double noOfCasualLeaves, double LOP, double noOfWeekOffsNotApplicable, double leaveCount) {
        double result = 0;
        double perDaySal = netSal / totalDaysInMonth;
        logger.info("Testing netSal "+netSal);
        logger.info("Testing Present Days "+presentDays);
        if (empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS) || empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)) {
            if (!empTemp.isWeekOffApplicable()) {
                result = (netSal / (totalDaysInMonth)) * (presentDays);
                logger.info("NOt applicable ");
            } else {

            	if(presentDays>=20){
                    logger.info("Above 20 ");
            	 if(presentDays==(totalDaysInMonth-no_of_days_weeklyoff) ||
            			 totalDaysInMonth <=(presentDays + noOfCasualLeaves + noOfDaysWeekOffIfApplicable-noOfWeekOffsNotApplicable)){
            		result=netSal;
                     logger.info("Above 20 ");
            	 }else{
            		 result = (perDaySal * (presentDays + noOfCasualLeaves + noOfDaysWeekOffIfApplicable-noOfWeekOffsNotApplicable));
            	 }
            	}

            	else if(presentDays<20 && presentDays>=10){
            		if(noOfCasualLeaves>1){
            			result = (perDaySal * (presentDays + noOfDaysWeekOffIfApplicable-noOfWeekOffsNotApplicable + noOfCasualLeaves-1));
            		}
            		else{
            			result = (perDaySal * (presentDays + noOfDaysWeekOffIfApplicable-noOfWeekOffsNotApplicable));
            		}
	    		} else {
	    			if(noOfCasualLeaves>leaveCount){
	    			result = (perDaySal * (presentDays + noOfDaysWeekOffIfApplicable-noOfWeekOffsNotApplicable + noOfCasualLeaves - leaveCount));
	    			}else{
            			result = (perDaySal * (presentDays +  noOfDaysWeekOffIfApplicable-noOfWeekOffsNotApplicable));
            		}

	    			}
            	}
        } else if (empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)) {
        	//if(presentDays > (totalDaysInMonth-no_of_days_weeklyoff)){
        		//presentDays = totalDaysInMonth-no_of_days_weeklyoff;
        	//}
            result = (netSal) * (presentDays);
        }
		logger.info("result "+result);
		//logger.info("non weekly off"+noOfWeekOffsNotApplicable);
        return result;

    }
}
