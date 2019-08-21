package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_PayrollTemplate;
import com.redcarpet.payroll.data.RCPL_PayrollTemplateLines;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollDBUtilityData;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import com.redcarpet.payroll.util.PayrollUtils;
import com.redcarpet.payroll.util.RoomPojo;
import java.math.BigDecimal;
import com.rcss.humanresource.data.RCHR_Room;
import org.apache.log4j.Logger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import org.hibernate.Session;
import org.openbravo.dal.service.OBDal;
import org.openbravo.service.db.DalConnectionProvider;

/**
*
* @author Matheen
* Customized By Tirumalakumar.U
*  Surya rao.A
*  Vinay kumar.K
*/
@SuppressWarnings("unchecked")
public class PayCalculator extends PayrollEmployee {
  private static final Logger logger = Logger.getLogger(PayCalculator.class);
    private String templateLineId;
    private List liAdditions;

    public PayCalculator(PayrollEmployee emp, String templateLineId) {
        super(emp.empuuid, emp.empPayrollProcessId, emp.payrollPeriodId, emp.employeeId, emp.employeeType, emp.startDate, emp.endDate,
                emp.grossAmount, emp.grossAmountLimit, emp.payPerDay, emp.basicAmount, emp.payrollTemplateId,
                emp.empSalSetupId,emp.taPerYear,emp.qadPerYear, emp.serviceIncentiveAmount);
        this.templateLineId = templateLineId;
    }

    public String getTemplateLineId() {
        return templateLineId;
    }

    public void setTemplateLineId(String templateLineId) {
        this.templateLineId = templateLineId;
    }

    public double calculateShiftSalaryAddition() throws ServletException, Exception {
        double retVal = 0;
        RchrEmployee empTemp = OBDal.getInstance().get(RchrEmployee.class, employeeId);
        RCPL_PayrollTemplateLines pLine = OBDal.getInstance().get(RCPL_PayrollTemplateLines.class, templateLineId);
        double percentage = 0;
        boolean isBonus = pLine.getPayAdditions().getCategory().equals(PayrollTypesConstants.BONUS);
        // dont calculate any gross on bonus
        if (isBonus) {
            String start = PayrollUtils.getInstance().getDBCompatiableDate(startDate);
            String end = PayrollUtils.getInstance().getDBCompatiableDate(endDate);
            String temp = PayrollDBUtilityData.getSumOfBonusAmount(new DalConnectionProvider(), employeeId, start, end);
            retVal = Double.valueOf(temp);
        } else {
            if (empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)) {
                if (grossAmount > grossAmountLimit) {
                    percentage = grossAmount * ((double) pLine.getGrossPercentageIfGrossExceeds().doubleValue() / 100);
                } else {
                    percentage = grossAmount * ((double) pLine.getGrossPercentage().doubleValue() / 100);
                }
                retVal = percentage;
                System.out.println("ret val1 is.."+retVal);
            } else if (empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)) {
                if (grossAmount > grossAmountLimit) {
                    percentage = grossAmount * ((double) pLine.getGrossPercentageIfGrossExceeds().doubleValue() / 100);
                } else {
                    percentage = grossAmount * ((double) pLine.getGrossPercentage().doubleValue() / 100);
                }
                retVal = percentage;
                System.out.println("ret val2 is.."+retVal);
            } else if (empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)) {
            	//System.out.println("under operator");
                //percentage = payPerDay * ((double) pLine.getGrossPercentage().doubleValue() / 100);
                //retVal = percentage;
            	//System.out.println("pay per day(worker).."+basicAmount);
            	retVal=basicAmount;
            }
        }
        return retVal;
    }

    public double calculateShiftSalaryDeductions(boolean isAdditions, AttendancePojo attandancePojo, HashMap<RchrEmployee, HashMap> rchrEmployeeListHashMap,
                                                 HashMap<RCHR_Room, RoomPojo> roomRoomUtilHashMap) throws ServletException, Exception {
        double retVal = 0;
        RchrEmployee empTemp = OBDal.getInstance().get(RchrEmployee.class, employeeId);
        RCPL_PayrollTemplate payrollTemplate = OBDal.getInstance().get(RCPL_PayrollTemplate.class, payrollTemplateId);
        RCPL_PayrollTemplateLines pLine = OBDal.getInstance().get(RCPL_PayrollTemplateLines.class, templateLineId);
        double basic = 0;
        if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.PF)
                && empTemp.isPFApplicable()) {
            basic = 0;
        	//if(empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)||empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)){
        			//double basic = getBasicComponent(empPayrollProcessId, employeeId);
                if(rchrEmployeeListHashMap.containsKey(empTemp)){
                    //logger.info("In If Condition "+empTemp.getDocumentNo());
                    HashMap<String, BigDecimal> stringBigDecimalHashMap = rchrEmployeeListHashMap.get(empTemp);
                    basic = stringBigDecimalHashMap.get(PayrollTypesConstants.BASIC).doubleValue();
                    //logger.info("Basic Staff Pf "+stringBigDecimalHashMap.get(PayrollTypesConstants.BASIC));
                }
                if (basic <= payrollTemplate.getPFLimit().doubleValue()) {
        				retVal = (basic * ((double) pLine.getGrossPercentage().doubleValue())) / 100;
                      //  logger.info(" Return Value "+retVal);
                } else {
                        //logger.info(" Return Value Else "+retVal);
        				retVal = (payrollTemplate.getPFLimit().doubleValue() * ((double) pLine.getGrossPercentage().doubleValue())) / 100;
        		}
        	//} else if(empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
                //logger.info("Workers" );
        		//double basic = getBasicComponent(empPayrollProcessId, employeeId);

        		//logger.info("baisc PF "+basic);
        		//if (basic <= payrollTemplate.getPFLimit().doubleValue()) {
                  //  logger.info("PF 1 ");
                   // retVal = (basic * ((double) pLine.getGrossPercentage().doubleValue())) / 100;

                //} else {
                    //logger.info("PF 2 ");
                   // retVal = (payrollTemplate.getPFLimit().doubleValue() * ((double) pLine.getGrossPercentage().doubleValue())) / 100;
                  //  }

        	//}
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.ESI)
                && OBDal.getInstance().get(RchrEmployee.class, employeeId).isESIApplicable()) {

            //String strNetSal = PayrollDBUtilityData.selectNetPayableSalary(new DalConnectionProvider(), empPayrollProcessId);
            //double netGrossSal = Double.valueOf(strNetSal);
            //basic = getBasicComponent(empPayrollProcessId, employeeId);
		if(rchrEmployeeListHashMap.containsKey(empTemp)){
                    //logger.info("In If Condition "+empTemp.getDocumentNo());
                    HashMap<String, BigDecimal> stringBigDecimalHashMap = rchrEmployeeListHashMap.get(empTemp);
                    basic = stringBigDecimalHashMap.get(PayrollTypesConstants.BASIC).doubleValue();
                    //logger.info("Basic Staff Pf "+stringBigDecimalHashMap.get(PayrollTypesConstants.BASIC));
                }
            logger.info("Basic ESI "+basic);
            //double percentage = (netGrossSal * ((double) pLine.getGrossPercentage().doubleValue())) / 100;
            double percentage = (basic * ((double) pLine.getGrossPercentage().doubleValue())) / 100;
            retVal = percentage;
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.PT)) {
        	String strNetSal = PayrollDBUtilityData.selectNetPayableSalary(new DalConnectionProvider(), empPayrollProcessId);
	       // String strIncentive="";
        	String strIncentive = PayrollDBUtilityData.selectIncenSalary(new DalConnectionProvider(), empPayrollProcessId);
          double netGrossSal = Double.valueOf(strNetSal)+Double.valueOf(strIncentive);
           retVal = getPTAmountFromSlab(netGrossSal);
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.LateAttendanceDisincentive)) {
        	//AttendanceUtil aUtil = new AttendanceUtil(this.payrollPeriodId, this.employeeId);
            double totalDaysInMonth = attandancePojo.getNoOfWorkingDays();
            double netSalary = grossAmount;
            double perDaySal = netSalary/totalDaysInMonth;
            if(empTemp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
            	retVal = new PayrollDBSessionUtil(employeeId, startDate, endDate).getLateAttendanceDisincentive();
            }else{
	            retVal = new PayrollDBSessionUtil(employeeId, startDate, endDate).getLateAttendanceDisincentiveForStaff();
	            retVal = retVal * perDaySal;
        	 }
         } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.LIC)
                && OBDal.getInstance().get(RchrEmployee.class, employeeId).isLICApplicable()) {
            double temp = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate).getSumofLICAmount();
            retVal = temp;
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.TOKENS)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getSumofTokensAmount();
        } //else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.POWER)) {
            //retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate).getSumofPowerAmount();
        //}
        else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.CLEANING)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate).getCleaningAmount();
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.TDS)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate).getTDSAmount();
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.LOANS_ADVANCES)) {
        	//logger.info("under advances");
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate,endDate).getLoans_AllowancesAmount();
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.DISH)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate).getLoans_DishCharges();
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.VPF)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate).getVPFAmount();
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.Instant_Over_Time)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getInstantOverTimeAmount();
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.FINE)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getFineAmount();
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.RENTBILL)) {
        	retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getRentAmount(roomRoomUtilHashMap, attandancePojo);
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.MESSBILL)) {
             retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getMessAmount();
           //  logger.info("mess bill.."+retVal);
         } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.ELECTRICITYBILL)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getElectricityAmount(roomRoomUtilHashMap, attandancePojo);
        } else if (pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.WELFARE_FUND)) {
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate,endDate).getEmployeeWelfareFund(roomRoomUtilHashMap, attandancePojo);
        } else if(pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.DEPARTMENT_STORE)){
        	retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getDepartmentStoreRecovery();
        } else if(pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.GAS_RECOVERY)){
        	retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getGasRecovery();
        } else if(pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.OTHER_DEDUCTIONS)){
        	retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getOtherDeductions();
        } else if(pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.EMPLOYEE_FINE)){
        	retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getEmployeeFine();
        } else if(pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.Security_Deposit)){
            double presentDays = attandancePojo.getNoOfDaysPresent();
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getSecurityDeposit(presentDays);
        } else if(pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.LOSS_OF_PAY)){
        	//AttendanceUtil aUtil = new AttendanceUtil(this.payrollPeriodId, this.employeeId);
            double totalDaysInMonth = attandancePojo.getNoOfWorkingDays();
            double presentDays = attandancePojo.getNoOfDaysPresent();
            double noOfWeekOffIfApplicable = attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable();
            //double noOfCasualLeaves = aUtil.getNoOfCasualLeaves();
            //double LOP = aUtil.getNoOfLOP();
            double totalNoOfLeaves = attandancePojo.getNoOfLeaves();
            double leaveCount = attandancePojo.getLeavesCountMonth();
            double LOP = attandancePojo.getNoOfLOP();
            double noOfCasualLeaves=totalNoOfLeaves-LOP;

            double netSalary = grossAmount;

            //double netSalary = 0;
            double perDaySal = netSalary/totalDaysInMonth;
        	//BigDecimal noOfLeaves = this.getNoOfLeaves(employeeId);

        	double noOfLop = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getLossOfPayBasedOnAttnd(presentDays,
        			noOfCasualLeaves,LOP);

            retVal = (double)(perDaySal * noOfLop);

        } else if(pLine.getPayDeductions().getCategory().equals(PayrollTypesConstants.WEEKOFFS_NOT_APPLICABLE)){
        	//AttendanceUtil aUtil = new AttendanceUtil(this.payrollPeriodId, this.employeeId);
        	double noOfWeekOffsNotApplicable = attandancePojo.getNoOfWeekOffs();
        	double netSalary = grossAmount;
            //double netSalary = 0;
            double perDaySal = netSalary/attandancePojo.getNoOfWorkingDays();
            retVal = new PayrollDBSessionUtil(payrollTemplateId, employeeId, startDate, endDate).getTotalWeeklyOffs(perDaySal,noOfWeekOffsNotApplicable);
        }

        return retVal;
    }

    private double getBasicComponent(String empPayrollProcessId, String employeeId) throws Exception {
        String hql = "select emphead.additions from RCPL_EmpPayHead emphead "
                + " join emphead.employeePayrollProcess emppp  "
                + " join emphead.payAdditions additions  "
                + " where additions.category='Basic' "
                + " and  emppp.id ='" + empPayrollProcessId + "' "
                + " and  emppp.employee.id ='" + employeeId + "'  ";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li == null) {
            return 0;
        } else if (li.isEmpty() || li.size() <= 0) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }

    private double getPTAmountFromSlab(double netGrossSal) throws Exception {
        String hql = "SELECT slab.rate FROM RCPL_PTSlab slab "
                + " WHERE " + netGrossSal + " BETWEEN slab.lowerLimit and slab.uppeLimit"
                + " AND slab.payrollTemplate.id = '" + payrollTemplateId + "' ";
        List<RCPL_PayrollTemplateLines> lineList = OBDal.getInstance().getSession().createQuery(hql).list();
        if (lineList.size() <= 0 || lineList.isEmpty()) {
            return 0;
        }
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (!li.isEmpty() && li.size() > 0) {
            return Double.valueOf(li.get(0).toString());
        }
        return 0;
    }


}
