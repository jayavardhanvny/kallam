package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.EmployeeUtil;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_Incentives;
import com.redcarpet.payroll.data.RCPL_VariableLine;
import com.redcarpet.payroll.util.PayrollUtils;
import com.redcarpet.payroll.util.SalariesUtil;
import com.redcarpet.production.data.RCPRShift;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollTypesConstants;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.hibernate.Query;
import org.openbravo.dal.service.OBDal;

/**
 *
 * @author mateen
 *      Modified by K Vinay kumar
 *      **** Tirumalakumar. U  ****
 */
public class IncentivesCalculator extends AttendanceUtil {
    private static final Logger logger = Logger.getLogger(IncentivesCalculator.class);
    private PayrollEmployee emp;
    private String strIncentiveId;
    private boolean additionIncentive;

    public IncentivesCalculator(PayrollEmployee emp, String strIncentiveId, boolean additionIncentive) {
        super(null, emp.payrollPeriodId, emp.employeeId, emp.startDate, emp.endDate);
        this.emp = emp;
        this.strIncentiveId = strIncentiveId;
        this.additionIncentive = additionIncentive;
    }

    public PayrollEmployee getEmp() {
        return emp;
    }

    public void setEmp(PayrollEmployee emp) {
        this.emp = emp;
    }

    public String getStrIncentiveId() {
        return strIncentiveId;
    }

    public void setStrIncentiveId(String strIncentiveId) {
        this.strIncentiveId = strIncentiveId;
    }

    public boolean isAdditionIncentive() {
        return additionIncentive;
    }

    public void setAdditionIncentive(boolean additionIncentive) {
        this.additionIncentive = additionIncentive;
    }

    public double calculateShiftIncentives(AttendancePojo attandancePojo) throws ServletException {
        double dVal = 0;
        RCPL_Incentives incen = OBDal.getInstance().get(RCPL_Incentives.class, getStrIncentiveId());
        double no_of_days_present=attandancePojo.getNoOfDaysPresent();
        double noOfPhysicalDaysPresent=attandancePojo.getNoOfPhysicalDaysPresent();
        //double no_of_days_present_with_out_ot = getNoOfDaysPresentWithOutOt();
        double no_of_days_if_weekoff_applicable =  attandancePojo.getNoOfWeekOffDaysIfWeekOffApplicable();
        double cur_month_days = attandancePojo.getNoOfWorkingDays();
        double no_of_days_late = attandancePojo.getNoOfLates();
        double shift_change_absent = attandancePojo.getNoOfShiftChangeAbsent();
        double no_of_days_weeklyOffWorked = attandancePojo.getNoOfDaysWeeklyOffWorked();
        double totalNoOfLeaves = attandancePojo.getNoOfLeaves();
        double noOfCasualLeaves=attandancePojo.getNoOfLeaves();
        double noOfWeekOffsNotApplicable = attandancePojo.getNoOfWeekOffs();
        double incentive = 0;
        double loomIncv=0, prepIncv=0, incamount=0;
        double workingDays=getNoOfWorkingDays();
        double no_of_days_absent = getNoOfDaysAbsent();
        double no_of_days_oted = getNoOfDaysOTed();
        double no_of_days_NoWork = getNoOfDaysNoWorked();
        //double totalNoOfLeaves = attendancePojo.getNoOfLeaves() getNoOfLeavesNew();
        double LOP = getNoOfLOPNew();

        if (incen.getVariableSet().isAttendance()) {
            logger.info("under attendance");
        	String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.isAttendance='Y' "
                    + "ORDER BY vsl.lineNo ASC LIMIT 1 ";
            Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
            List<RCPL_VariableLine> attList = hQyery.list();
            if (attList.isEmpty() || attList.size() <= 0) {
            	//logger.info("list empty");
                dVal = 0;
            } else {

                RCPL_VariableLine line = attList.get(0);
                //double actualPresentDays = 0;
                //String shiftid=line.getShift().getId();
               // logger.info("shiftid.."+shiftid);
                //RCPRShift sf=OBDal.getInstance().get(RCPRShift.class,shiftid);
                //double no=getNoofDaysNightShift(shiftid,sf.getFromTime());
                //if(no>=line.getTypeValue().doubleValue()){
                	//dVal=getIncentivePay(no,line.getPay().doubleValue());
                //}

               // logger.info("date.."+sf.getFromTime());
                /*
                 * Subjected to client testing to add nowork or not
                 */
                /*actualPresentDays = no_of_days_present_with_out_ot + no_of_days_NoWork;
                logger.info("actual present days.."+actualPresentDays);
                dVal = getIncentivePay(actualPresentDays, line.getPay().doubleValue());*/

            }
            incentive = dVal;

        }  else if (incen.getVariableSet().isLate()) {
            double sum = 0;
            String lateHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.isLate='Y' AND vsl.typeValue >= " + no_of_days_late + " "
                    + "ORDER BY vsl.lineNo ASC LIMIT 1 ";
            Query hQyery = OBDal.getInstance().getSession().createQuery(lateHql);
            List<RCPL_VariableLine> attList = hQyery.list();
            if (!(attList.isEmpty() || attList.size() <= 0)) {
//                dVal = getIncentivePay(no_of_days_present, attList.get(0).getPay().doubleValue());
                List<RCPRShift> wsList = OBDal.getInstance().createCriteria(RCPRShift.class).list();
                for (RCPRShift ws : wsList) {
                    NightShiftIncentiveCalculator calc1 = new NightShiftIncentiveCalculator(emp.employeeId, ws.getId(), getStartDate(), getEndDate(), incen.getVariableSet().getId());
                    sum += calc1.getSumOfShiftPay();
                }
            }
//          incentive = dVal;
            incentive = sum;

        } else if (incen.getVariableSet().isShiftChangeAbsent() && incen.isDeductions()
                && (!isAdditionIncentive())) {
            String shaHQL = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.isShiftChangeAbsent='Y' AND vsl.typeValue < " + shift_change_absent + " "
                    + "ORDER BY vsl.lineNo ASC LIMIT 1 ";
            Query hQyery = OBDal.getInstance().getSession().createQuery(shaHQL);
            List<RCPL_VariableLine> attList = hQyery.list();
            if (!(attList.isEmpty() || attList.size() <= 0)) {
                dVal = -1 * getIncentivePay(no_of_days_present, attList.get(0).getPay().doubleValue());
            }
            incentive = dVal;
        }/* else if (incen.getVariableSet().isOTIncentive()) {
            String otHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.isOTIncentive='Y' AND vsl.typeValue >= " + no_of_days_absent + " "
                    + "ORDER BY vsl.lineNo ASC LIMIT 1 ";
            Query hQyery = OBDal.getInstance().getSession().createQuery(otHql);
            List<RCPL_VariableLine> attList = hQyery.list();
            if (!(attList.isEmpty() || attList.size() <= 0)) {
                dVal = getIncentivePay(no_of_days_oted, attList.get(0).getPay().doubleValue());
            }
            incentive = dVal;
        }*/ else if (incen.getVariableSet().isOTIncentive()) {
            long presentServiceDays=0;
            double attdIncentive = 0;
            //String stringStartDate = "2017-12-23";
            //String stringEndDate = "2017-12-31";
           // Date startDate = null;
            //Date endDate = null;
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


            double perDay=0;
            EmployeeUtil employeeUtil = new EmployeeUtil();
            RchrEmployee employee=OBDal.getInstance().get(RchrEmployee.class,emp.employeeId);
            RchrDesignation rchrDesignation = employeeUtil.getEmployeeDesignation(employee,emp.startDate,emp.endDate);
            SalariesUtil salariesUtil = this.getSalariesUtilOT(rchrDesignation,employee);
            if(employee.getRCPLEmpSalSetupList().get(0).isNotdesig()) {
                perDay = employee.getRCPLEmpSalSetupList().get(0).getServiceince().doubleValue() + employee.getRCPLEmpSalSetupList().get(0).getPerdaybasic().doubleValue();
                logger.info(" incentive..For OT not desig" + incentive);
            } else{
                perDay=(salariesUtil.getBasicAmount()+salariesUtil.getServiceIncentiveAmount());
            }

            double checkMnth = this.getCheckPayrollmonth(emp.startDate,emp.endDate);
            double prvMnthDays = 0;
            if(checkMnth==0){
                double importCheck=this.getCheckAttendanceImport(emp.endDate);
                if(importCheck>0){
                    prvMnthDays = this.getPrevmonthPresentDays(emp.startDate,emp.endDate);
                }else{
                    prvMnthDays = this.getPrevmonthPresentDaysDemo(emp.startDate,emp.endDate);
                }
                //logger.info("prvMnthDays 2 "+prvMnthDays);
            }
            Calendar headercal=Calendar.getInstance();
            //Calendar tocal=Calendar.getInstance();
            headercal.set(Calendar.MONTH,emp.startDate.getMonth());
            //headercal.add(Calendar.MONTH, -1);
            headercal.set(Calendar.DAY_OF_MONTH, headercal.getActualMinimum(Calendar.DATE));
            Date headerfromDate=headercal.getTime();
            double presentDays=this.getNoOfPresentDaysForOT();
            //logger.info("presentDays are wrong...... "+presentDays);
            double betweenDays=this.getBtndaysPresentDaysForOT(headerfromDate);
            presentServiceDays = (long)prvMnthDays + (long)betweenDays + employee.getPreattddays()+(long)presentDays;
            if(presentServiceDays>75 || employee.isSenior()){
                attdIncentive=30;
            }else{
                attdIncentive=0;
            }

            double noOfOts = this.getNoOfManualDaysOTPresents(emp.startDate,emp.endDate);
            logger.info("new payPerDay "+perDay);
            double totalAmount = (attdIncentive + perDay);
            logger.info("no of OTS "+noOfOts);
           // logger.info("getBasicAmountForOT() "+getBasicAmountForOT());
            //logger.info("getServiceIncentiveForOT() "+getServiceIncentiveForOT());
            //double perDay = getBasicAmountForOT() + getServiceIncentiveForOT();

            /*String hql = "SELECT SUM(ot.amount) FROM RCHR_OverTime AS ot "
         			 + "JOIN ot.overTimeHeader head "
 					 + " WHERE ot.employee.id = '" + emp.employeeId+ "' " +
                       " AND ot.isPaid=false"
 					 + " AND head.startingDate BETWEEN '"+ PayrollUtils.getInstance().getDBCompatiableDate(emp.startDate)+"' "+
                    " AND '"+PayrollUtils.getInstance().getDBCompatiableDate(emp.endDate)+"' "
   		             + " AND head.endingDate BETWEEN '"+ PayrollUtils.getInstance().getDBCompatiableDate(emp.startDate)+"' "+
                    " AND '"+PayrollUtils.getInstance().getDBCompatiableDate(emp.endDate)+"' ";
 	           Session session = OBDal.getInstance().getSession();


               List li = session.createQuery(hql).list();
               if (li.size() <= 0 || li.isEmpty()) {
     	        incamount= 0+(noOfOts*totalAmount);
               }
              else if (li.get(0) == null || li.get(0).toString() == null) {
     	      incamount =0+(noOfOts*totalAmount);
             }else{
                   //double basic = getBasicAmountForOT();
                   //double val=new PeriodInfo(getStartDate(),getEndDate()).getBasicAmountForOT();
     	        incamount =  getNoOfOTsNotPaidParollMonth() + noOfOts*totalAmount;
                   logger.info("incamount OT "+incamount);
          }*/
        incentive = super.getNoOfOTsNotPaidParollMonth() + (noOfOts*totalAmount);
    }else if (incen.getVariableSet().isMonthlyIncentive()) {
            String moHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.isMonthlyIncentive='Y' "
                    + "ORDER BY vsl.lineNo ASC LIMIT 1 ";
            Query hQyery = OBDal.getInstance().getSession().createQuery(moHql);
            List<RCPL_VariableLine> attList = hQyery.list();
            /*if (!(attList.isEmpty() || attList.size() <= 0)) {
                // check 3 conditions
            	PeriodInfo lastMonth = getLastMonthInfo();
                PeriodInfo lastTwoMonth = getTheyBeforeLastMonthInfo();
                if (no_of_days_present_with_out_ot >= 20 && lastMonth.getNoOfDaysPresentWithOutOt() >= 20 && lastTwoMonth.getNoOfDaysPresentWithOutOt() >= 20) {

                        dVal = getIncentivePay(no_of_days_present_with_out_ot, attList.get(0).getPay().doubleValue());

                }
            }*/
            incentive = dVal;
        }
        else if (incen.getVariableSet().isWeeklyOff() && isPFApplicable()
                && OBDal.getInstance().get(RchrEmployee.class, getEmployeeId()).isWeekOffApplicable()) {
            String woHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.isWeeklyOff='Y' "
                    + "ORDER BY vsl.lineNo ASC LIMIT 1 ";
            Query hQyery = OBDal.getInstance().getSession().createQuery(woHql);
            List<RCPL_VariableLine> attList = hQyery.list();
            if (!(attList.isEmpty() || attList.size() <= 0)) {
                dVal = getIncentivePay(no_of_days_weeklyOffWorked, attList.get(0).getPay().doubleValue());
            }
            incentive = dVal;
        }
        else if(incen.getVariableSet().isServicedaysincentive()){
       	 RchrEmployee e=OBDal.getInstance().get(RchrEmployee.class,emp.employeeId);
       	 if(e.equals(null)){
       		 return 0;
       	 }
       	 OBCriteria<RCPL_EmpSalSetup> salSetup=OBDal.getInstance().createCriteria(RCPL_EmpSalSetup.class);
       	 salSetup.add(Restrictions.eq(RCPL_EmpSalSetup.PROPERTY_EMPLOYEE,e));
       	 for(RCPL_EmpSalSetup sal:salSetup.list()){
       		if(sal.isNotdesig()){
       		    incentive=no_of_days_present*sal.getServiceince().doubleValue();
       		    logger.info("incentive..not desig"+incentive);}
                else{
                //AttendanceUtil aUtil=new AttendanceUtil(getStartDate(), getEndDate(),emp.employeeId);
                //EmployeeUtil employeeUtil = new EmployeeUtil();
                //RchrDesignation rchrDesignation = employeeUtil.getEmployeeDesignation(e,emp.startDate,emp.endDate);
                //SalariesUtil salariesUtil = super.getSalariesUtil(rchrDesignation,e);
                //logger.info("Service Incentive of Incentive Calculator class "+emp.serviceIncentiveAmount);
                incentive=no_of_days_present*emp.serviceIncentiveAmount;
                    //AttendanceUtil aUtil=new AttendanceUtil(getStartDate(), getEndDate(),emp.employeeId);
                    //incentive=aUtil.getServiceIncentive();
                     //incentive=no_of_days_present*getServiceIncentive();
                    /* RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,e.getDesignation().getId());
                     RchrEmpDept dept=OBDal.getInstance().get(RchrEmpDept.class,e.getEmployeeDepartment().getId());
                     RCHR_SubDepartment subdept=OBDal.getInstance().get(RCHR_SubDepartment.class,e.getSubDepartment().getId());
                     OBCriteria<RCHRDesigBasic> desigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);

                     desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));
                     //desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_EMPLOYEEDEPARTMENT,dept));
                     //desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_SUBDEPARTMENT,subdept));
                     desigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,e.getPreattddays()));
                     desigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,e.getPreattddays()));
                     for(RCHRDesigBasic desigbasic1:desigbasic.list()){
                             incentive= no_of_days_pres
                             ent*desigbasic1.getServiceincentive().doubleValue();
                             logger.info("incentive from des"+incentive);
                     } */
                }
         }
        } //*****  1300 Staff Attendance Incentive  ******
        else if(incen.getVariableSet().is1300attdincen()){
        	double absents;
       	 if((getEmpType()).equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)|| (getEmpType()).equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)){
       	 absents=getNoOfWorkingDays()-(no_of_days_present+no_of_days_if_weekoff_applicable-
                 noOfWeekOffsNotApplicable+ totalNoOfLeaves);
       	 }else{
       		  absents=getNoOfWorkingDays()-(no_of_days_present+no_of_days_if_weekoff_applicable);
       	 }
            if(absents<0){
            	absents=0;
            }
 //       	double absents=getNoOfWorkingDays()-(getNoOfDaysPresent()+getNoOfWeekOffDaysIfWeekOffApplicable()-getNoOfWeekOffs()+getNoOfLeaves()-getNoOfLOP());
        	String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.is1300attdincen='Y' "
                    + "AND ((vsl.condition='Equal' AND vsl.typeValue='"+absents+"') "
                    + "OR (vsl.condition='Greater Then Equal To' AND vsl.typeValue <='"+absents+"' ))";

            Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
            List<RCPL_VariableLine> attList = hQyery.list();

            if (attList.isEmpty() || attList.size() <= 0) {
            	incentive=0;
            }
            else{
            		RCPL_VariableLine line = attList.get(0);
            		double pay=line.getPay().doubleValue();
            		logger.info("pay.."+pay);
            		if(no_of_days_present>=20){
            			//if(no_of_days_present==cur_month_days-(no_of_days_if_weekoff_applicable-getNoOfWeekOffs()+getNoOfLeaves()-getNoOfLOP())){
            				if(absents==0){
            				//logger.info("1300");
            				incentive=1300;
            			}
            			else{
            			incentive=no_of_days_present*(pay);}
            		}
            		else if(no_of_days_present<20){
            			incentive=0;}
            		 	else{
            		 		incentive=pay*no_of_days_present;}
            	}

            } //**** Staff Attendance Prize ******
        else if(incen.getVariableSet().is1000attdincen()){
        	double absents;
       	 if((getEmpType()).equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)|| (getEmpType()).equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)){
       	 absents=getNoOfWorkingDays()-(no_of_days_present+ no_of_days_if_weekoff_applicable-
                 noOfWeekOffsNotApplicable+totalNoOfLeaves);
       	 }else{
       		  absents=getNoOfWorkingDays()-(no_of_days_present+no_of_days_if_weekoff_applicable);
       	 }
       	if(absents<0){
        	absents=0;
        }
 //       	double absents=getNoOfWorkingDays()-(getNoOfDaysPresent()+getNoOfWeekOffDaysIfWeekOffApplicable()-getNoOfWeekOffs()+getNoOfLeaves()-getNoOfLOP());

        	String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.is1000attdincen='Y' "
                    + "AND (vsl.condition='Equal' AND vsl.typeValue='"+absents+"') ";
            Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
            List<RCPL_VariableLine> attList = hQyery.list();

            if (attList.isEmpty() || attList.size() <= 0) {
            	incentive=0;
            }
            else{
            		RCPL_VariableLine line = attList.get(0);
            		if(noOfCasualLeaves <=1){
            		incentive=line.getPay().doubleValue();
            		}
            	}

            }
        else if(incen.getVariableSet().is1040attdincen()){
//        	double absents=getNoOfWorkingDays()-(getNoOfDaysPresent()+getNoOfWeekOffDaysIfWeekOffApplicable()-getNoOfWeekOffs()+getNoOfLeaves()-getNoOfLOP());
        	double absents;
       	 if((getEmpType()).equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)|| (getEmpType()).equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)){
       	 absents=getNoOfWorkingDays()-(no_of_days_present+no_of_days_if_weekoff_applicable-
                 noOfWeekOffsNotApplicable+totalNoOfLeaves);
       	 }else{
       		  absents=getNoOfWorkingDays()-(no_of_days_present+no_of_days_if_weekoff_applicable);
       	 }
       	if(absents<0){
        	absents=0;
        }
        	String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.is1040attdincen='Y' "
                    + "AND (vsl.condition='Equal' AND vsl.typeValue='"+absents+"') ";

            Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
            List<RCPL_VariableLine> attList = hQyery.list();

            if (attList.isEmpty() || attList.size() <= 0) {
            	incentive=0;
            }
            else{
            		RCPL_VariableLine line = attList.get(0);
            		incentive=line.getPay().doubleValue();
            	}

            }
            else if(incen.getVariableSet().is1350attdincen()){
            logger.info("under 1350");
//        	double absents=getNoOfWorkingDays()-(getNoOfDaysPresent()+getNoOfWeekOffDaysIfWeekOffApplicable()-getNoOfWeekOffs()+getNoOfLeaves()-getNoOfLOP());
            double absents;
       	 if((getEmpType()).equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)|| (getEmpType())
                 .equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS)){
       	 absents=getNoOfWorkingDays()-(no_of_days_present+no_of_days_if_weekoff_applicable-
                 noOfWeekOffsNotApplicable+totalNoOfLeaves);
       	 }else{
       		  absents=getNoOfWorkingDays()-(no_of_days_present+no_of_days_if_weekoff_applicable);
       	 }
       	if(absents<0){
        	absents=0;
        }
            String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                    + "JOIN vsl.variableSet vs "
                    + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                    + "AND vs.is1350attdincen='Y' "
                    + "AND ((vsl.condition='Equal' AND vsl.typeValue='"+absents+"') "
                    + "OR (vsl.condition='Greater Then Equal To' AND vsl.typeValue <='"+absents+"' ))";
            Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
            List<RCPL_VariableLine> attList = hQyery.list();
            if (attList.isEmpty() || attList.size() <= 0) {
            	incentive=0;
            }
            else{
            		RCPL_VariableLine line = attList.get(0);
            		double pay=line.getPay().doubleValue();
            		logger.info("pay.."+pay);
            		if(no_of_days_present>=20){
            			//if(no_of_days_present==cur_month_days-(no_of_days_if_weekoff_applicable-getNoOfWeekOffs()+getNoOfLeaves()-getNoOfLOP())){
            				if(absents==0){
            				logger.info("1350");
            				incentive=1350;
            			}
            			else{
            			incentive=no_of_days_present*(pay);}
            		}
            		else if(no_of_days_present<20){
            			incentive=0;}
            		 	else{
            		 		incentive=pay*no_of_days_present;}
            	}

            }
        else if(incen.getVariableSet().isAttendanceprize()){
        	 double absents=getNoOfWorkingDays()-(no_of_days_present+
                     no_of_days_if_weekoff_applicable);
        	 if(absents<0){
             	absents=0;
             }
        	 RchrEmployee rchrEmplpyee = OBDal.getInstance().get(RchrEmployee.class, emp.employeeId);
        	 String desgId="208E9349EC804CE2BA26503B068FE694";
        	 String desgId2= "799FD8C784734E45BDCB1BF32D5E02A2";
        	 RchrDesignation designation = OBDal.getInstance().get(RchrDesignation.class, desgId);
        	 OBCriteria<RCPL_EmpSalSetup> salSetup=OBDal.getInstance().createCriteria(RCPL_EmpSalSetup.class);
           	 salSetup.add(Restrictions.eq(RCPL_EmpSalSetup.PROPERTY_EMPLOYEE,rchrEmplpyee));
           	 Boolean senior=false;
           	 // Changing on 08-03-2018 for seniors
           	 //for(RCPL_EmpSalSetup sal:salSetup.list()){
            if(rchrEmplpyee.isSenior()){
                senior=true;
            }
           	 //}
           	double doj = 0;
           	 // senor employee A.I code
           	String hql = "SELECT COUNT(employee.id) FROM Rchr_Employee AS employee "
     		       + " WHERE employee.id = '" +emp.employeeId+ "' "
     		       + " AND employee.date BETWEEN '" +getStartDate()+ "' AND '" +getEndDate()+ "'";
            Session session = OBDal.getInstance().getSession();
            List li = session.createQuery(hql).list();
            if (li.size() <= 0 || li.isEmpty()) {
                doj=0;
            }else if (li.get(0) == null || li.get(0).toString() == null) {
                doj=0;
            }else{
            doj= Double.valueOf(li.get(0).toString());
            }

		   //if(doj==1 && attndPrize==true || rchrEmplpyee.getPreattddays() >= 0 ){
		if(doj==1 && senior==true ){
           		incentive=getNoOfDaysPresent()*30;
			logger.info("Incentive 1 ");
           	  } else if(rchrEmplpyee.getPreattddays() >= 0 ||
            	 //}else if( attndPrize==true ||
                     rchrEmplpyee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_OS)||
           			rchrEmplpyee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_PS) || rchrEmplpyee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
        	  // if(!rchrEmplpyee.getDesignation().equals(designation)){
			logger.info("Incentive 2 ");
                 if(absents<0){
                	 absents=0;
                 }
            	String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                        + "JOIN vsl.variableSet vs "
                        + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                        + "AND vs.isattendanceprize='Y' "
                        + "AND vsl.typeValue='"+absents+"' ";

                Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
                List<RCPL_VariableLine> attList = hQyery.list();
                if (attList.isEmpty() || attList.size() <= 0) {
                	incentive=0;
                }
                else{
                		RCPL_VariableLine line = attList.get(0);
                		incentive=line.getPay().doubleValue();

                	}
        	//}
        	 }
        }
        else if(incen.getVariableSet().isMessdeduction()){

        	double amount = new PayrollDBSessionUtil(emp.employeeId, getStartDate(),getEndDate())
                    .getMessAmount();
        	RchrEmployee emp1 = OBDal.getInstance().get(RchrEmployee.class, emp.employeeId);

            if(emp1.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
            	if(emp1.isRoomAllotted()){

            	RCHR_Room roomObj=OBDal.getInstance().get(RCHR_Room.class,emp1.getRoom().getId());
            	double totalmeals = new PayrollDBSessionUtil(emp.employeeId,getStartDate(),getEndDate())
                        .getNoOfMeals();
            	int messcount = new PayrollDBSessionUtil(emp.employeeId, getStartDate(),getEndDate())
                        .getMesstypeCount();
            	if(roomObj.isBachelorquarter() && messcount==1 ){

            		String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                            + "JOIN vsl.variableSet vs "
                            + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                            + "AND vs.ismessdeduction='Y' "
                            + "AND ((vsl.condition='Equal' AND vsl.typeValue='"+noOfPhysicalDaysPresent+"') "
                            + "OR (vsl.condition='Greater Then Equal To' AND vsl.typeValue <='"+noOfPhysicalDaysPresent+"'))";

                    Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
                    List<RCPL_VariableLine> attList = hQyery.list();
                   // logger.info("list size.."+attList.size());
                    if (attList.isEmpty() || attList.size() <= 0) {
                    	incentive=0;
                    }
                    else{
                    		RCPL_VariableLine line = attList.get(0);
                    		incentive=totalmeals*line.getPay().doubleValue();
                    		if(incentive>780){
                    			incentive=780;
                    		}

                    	}
            	}
            	}
            	}else{incentive=0;}


        }
        else if(incen.getVariableSet().isProdincentive()){

        	// Lomm Incentive
        	String hql = "SELECT SUM(bill.amount+bill.yarndyedincentive) FROM RCPL_ProdIncentive AS bill "
      		       + " WHERE bill.employee.id = '" + emp.employeeId+ "' "
      		       + " AND bill.date BETWEEN '" +getStartDate()+ "' AND '" +getEndDate()+ "'";

        	 Session session = OBDal.getInstance().getSession();
             List li = session.createQuery(hql).list();
             if (li.size() <= 0 || li.isEmpty()) {
            	 loomIncv = 0;
             }else if (li.get(0) == null || li.get(0).toString() == null) {
            	 loomIncv = 0;
             }else{
            loomIncv= Double.valueOf(li.get(0).toString());
            }
            //Preparatory Incentive
            String hqlPrep = "SELECT SUM(prep.amount) FROM RCHR_Preparatprodincntive AS prep "
       		       + " WHERE prep.employee.id = '" + emp.employeeId+ "' "
       		       + " AND prep.date BETWEEN '" + getStartDate()+ "' AND '" + getEndDate()+ "'";

         	/*String hql = "SELECT SUM(bill.amount) FROM RCPL_PRODINCENT AS bill "
      		       + " WHERE bill.employee.id = '" +emp.employeeId+ "' "
      		       + " AND bill.fromDate BETWEEN '" +getStartDate()+ "' AND '" +getEndDate()+ "'"
      		       + " AND bill.toDate BETWEEN '" +getStartDate()+ "' AND '" +getEndDate()+ "'";*/
           // Session session = OBDal.getInstance().getSession();
            List prepli = session.createQuery(hqlPrep).list();
            if (prepli.size() <= 0 || prepli.isEmpty()) {
            	prepIncv = 0;
            }else if (prepli.get(0) == null || prepli.get(0).toString() == null) {
            	prepIncv = 0;
            }else{
            	prepIncv= Double.valueOf(prepli.get(0).toString());
            }
             incentive = loomIncv + prepIncv;
             //logger.info("Loom Incv."+loomIncv);
            // logger.info("Prep Incv."+prepIncv);
            // logger.info("Prod Incv."+incentive);


        }
        else if(incen.getVariableSet().isStaffprodincen()){
        	logger.info("under staff prod");
        	RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, emp.employeeId);
        	OBCriteria<RCPL_EmpSalSetup> salsetup=OBDal.getInstance().createCriteria(RCPL_EmpSalSetup.class);
        	salsetup.add(Restrictions.eq(RCPL_EmpSalSetup.PROPERTY_EMPLOYEE,employee));
            for(RCPL_EmpSalSetup obj:salsetup.list()){
            	incentive=obj.getProdincentive().doubleValue();
            	logger.info("incentive amount.."+incentive);
            }
            if((cur_month_days-no_of_days_if_weekoff_applicable)==(no_of_days_present+noOfCasualLeaves))
             	incentive=incentive;
             	else
             	incentive=(incentive/cur_month_days)*(no_of_days_present+no_of_days_if_weekoff_applicable-
                        noOfWeekOffsNotApplicable+noOfCasualLeaves);
        }



        else if(incen.getVariableSet().isStaffotherproincentive()){
         	String hql = "SELECT SUM(prep.amount) FROM RCPL_PRODINCENT AS prep "
         					+ " WHERE prep.employee.id = '" + emp.employeeId+ "' "
         					+ " AND prep.payrollPeriod.id ='"+emp.payrollPeriodId+"'";
         	Session session = OBDal.getInstance().getSession();
             List li = session.createQuery(hql).list();
             if (li.size() <= 0 || li.isEmpty()) {
             	incamount= 0;
             }
            else if (li.get(0) == null || li.get(0).toString() == null) {
             	incamount =0;
             }else{
             	incamount =  Double.valueOf(li.get(0).toString());
             }
             incentive = incamount;
        }

        else if(incen.getVariableSet().is12hrsbshiftincen()){
        	logger.info("under 12-Hrs night shift");
          	String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                      + "JOIN vsl.variableSet vs "
                      + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                      + "AND vs.is12hrsbshiftincen='Y' ";
              Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
              List<RCPL_VariableLine> attList = hQyery.list();
              if (attList.isEmpty() || attList.size() <= 0) {
                  dVal = 0;
              } else {
                  RCPL_VariableLine line = attList.get(0);
                  double actualPresentDays = 0;
                  String shiftid=line.getShift().getId();

                  RCPRShift sf=OBDal.getInstance().get(RCPRShift.class,shiftid);
                  double no=this.getNoofDaysNightShift(shiftid);
                  	dVal=getIncentivePay(no,line.getPay().doubleValue());
              }
              incentive = dVal;

        }



        else if(incen.getVariableSet().isCshiftincen()){
        	logger.info("under night shift");
          	String attHql = "SELECT vsl FROM RCPL_VariableLine vsl "
                      + "JOIN vsl.variableSet vs "
                      + "WHERE vs.id = '" + incen.getVariableSet().getId() + "' "
                      + "AND vs.iscshiftincen='Y' ";
              Query hQyery = OBDal.getInstance().getSession().createQuery(attHql);
              List<RCPL_VariableLine> attList = hQyery.list();
              if (attList.isEmpty() || attList.size() <= 0) {

                  dVal = 0;
              } else {

                  RCPL_VariableLine line = attList.get(0);
                  double actualPresentDays = 0;
                  String shiftid=line.getShift().getId();

                  RCPRShift sf=OBDal.getInstance().get(RCPRShift.class,shiftid);
                  double no=getNoofDaysNightShift(shiftid,sf.getFromTime());

                  	dVal=getIncentivePay(no,line.getPay().doubleValue());


              }
              incentive = dVal;

        }
        return incentive;
    }
    private double getNoofDaysNightShift(String shiftid){
        logger.info("under shift method");
        //Date date=new Date(fromTime.getTime());
        //Date date2=new Date(fromTime.getTime());
        //Calendar cal=Calendar.getInstance();
        //cal.setTime(date);
        //cal.add(Calendar.MINUTE,-43);
        //date=cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //String punchin=sdf.format(date);
        //String punchout=sdf.format(date2);
        //logger.info("in"+punchin);
        //logger.info("out"+punchout);
        //Date formattedDate = sdf.format(date);
        //String from=cal.toString();
        String hql="SELECT COUNT(line.id) FROM RCHR_Dailyattenddemo AS line"
                + " where line.attendanceDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'"
                + " AND line.rcprShift='"+shiftid+"'"
                + " AND line.present = true AND line.overtime=false "
                + " AND line.employee='"+emp.employeeId+"'";
                //+ " AND (to_char(line.punchIn,'HH24:MI:SS') BETWEEN '"+punchin+"' AND '"+punchout+"')";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        logger.info("noofdays.."+Double.valueOf(li.get(0).toString()));
        return Double.valueOf(li.get(0).toString());
    }

    private double getNoofDaysNightShift(String shiftid,Timestamp fromTime){
        logger.info("under shift method");
        Date date=new Date(fromTime.getTime());
        Date date2=new Date(fromTime.getTime());
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE,-43);
        date=cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String punchin=sdf.format(date);
        String punchout=sdf.format(date2);
        //logger.info("in"+punchin);
        //logger.info("out"+punchout);
        //Date formattedDate = sdf.format(date);
        String from=cal.toString();
        String hql="SELECT COUNT(line.id) FROM RCHR_Dailyattenddemo AS line"
                + " where line.attendanceDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'"
                + " AND line.rcprShift='"+shiftid+"'"
                + " AND line.present = true AND line.overtime=false "
                + " AND line.employee='"+emp.employeeId+"'"
                + " AND (to_char(line.punchIn,'HH24:MI:SS') BETWEEN '"+punchin+"' AND '"+punchout+"')";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        logger.info("noofdays.."+Double.valueOf(li.get(0).toString()));
        return Double.valueOf(li.get(0).toString());
    }

    private double getIncentivePay(double days, double pay) {
        return days * pay;
    }
}

