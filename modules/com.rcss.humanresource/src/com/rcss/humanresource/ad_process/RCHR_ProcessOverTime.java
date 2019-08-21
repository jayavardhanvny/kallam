package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.HqlUtils;
import com.rcss.humanresource.util.TimeDifference;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.util.PayrollUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

import com.redcarpet.payroll.util.SalariesUtil;
import org.hibernate.Session;
import org.hibernate.transaction.ResinTransactionManagerLookup;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.hibernate.criterion.Restrictions;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
/**
 *
 * @author Tirumal
 */
@SuppressWarnings("unchecked")
public class RCHR_ProcessOverTime extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rchr_Overtimeheader_ID").toString();
        RCHR_OverTimeHeader head = OBDal.getInstance().get(RCHR_OverTimeHeader.class, id);
        OBContext.setAdminMode();
        OBError err = new OBError();
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");

        try {
            doIt(head);
            head.setProcess(Boolean.TRUE);
        } catch (Exception e) {
            err.setMessage(e.getMessage());
            err.setTitle("Error");
            err.setType("Error");
            bundle.setResult(err);
            e.printStackTrace();
        }

        OBContext.restorePreviousMode();
        bundle.setResult(err);
    }

    
    private void doIt(RCHR_OverTimeHeader head){
    	 Date strDate = head.getStartingDate();
    	 Date endDate = head.getEndingDate();
    	 double basicAmount = 0;
    	 double serviceIncentive = 0;
    	 boolean parity = false;



    	 List<RchrOvertimeprocessLine> rchrOvertimeprocessLineList = this.getOvertimeProcessLinesList(strDate,endDate);

    	 String hql = "SELECT distinct(overtimeprocessline.employee) from RCHR_Overtimeprocess_Line AS overtimeprocessline "
                 + " JOIN overtimeprocessline.rchrOvertimeprocess AS overtimeprocess "
                 + " WHERE overtimeprocess.date BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(strDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endDate) + "' " 
                 + " AND overtimeprocessline.overtime = true " 
                 + " AND 1 = 1";
         Session session = OBDal.getInstance().getSession();
         List<RchrEmployee> li = session.createQuery(hql).list();
         Iterator itr = li.iterator();
         System.out.println("size of hql list "+li.size());
		HashMap<RchrEmployee, RCHR_OverTime> rchrEmployeeRCHROverTimeHashMap = new LinkedHashMap<RchrEmployee, RCHR_OverTime>();

		//for (RchrEmployee employee : li){

		//}


         //while(itr.hasNext()){
			 for (RchrEmployee employee : li){
        	 //String stremployeeId = itr.next().toString();
        	// System.out.println("str "+stremployeeId);
         //return Double.valueOf(li.get(0).toString());
         	//RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, stremployeeId);
	 		//OBCriteria<RCPL_EmpSalSetup> salSetupCriteria = OBDal.getInstance().createCriteria(RCPL_EmpSalSetup.class);
	 		//salSetupCriteria.add(Restrictions.eq(RCPL_EmpSalSetup.PROPERTY_EMPLOYEE,employee));
	 		//salSetupCriteria.add(Restrictions.eq(RCPL_EmpSalSetup.PROPERTY_ACTIVE,true));
	 		//System.out.println("size of salsetup "+salSetupCriteria.list().size());
	 		//for(RCPL_EmpSalSetup salsetup : salSetupCriteria.list()){
				//String stremployeeId = rchrOvertimeprocessLine.getEmployee().getId();
	 			basicAmount = 0;
		 		serviceIncentive=0;
		 		long presentServiceDays=0;
		 		AttendanceUtil aUtil=new AttendanceUtil(strDate, endDate, employee.getId());
			 	EmployeeUtil employeeUtil = new EmployeeUtil();
		 		double ots = getNoofOTsfromOtProcess(strDate, endDate, employee.getId());
		 		double continueShift = getNoofContinuessfromOtProcess(strDate, endDate, employee.getId());
		 		//long l=new long(continueShift);
		 	 	double attdIncentive = 0;
		 	 	
		 	 	Date eDate= new Date(head.getEndingDate().getTime());
		    	
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
		    	
		    	double checkMnth = aUtil.getCheckPayrollmonth(fromDateTemp, toDateTemp);
		    	double prvMnthDays = 0;
		        if(checkMnth==0){
		    		double importCheck=aUtil.getCheckAttendanceImport(toDateTemp);
		        	if(importCheck>0){
		        		prvMnthDays = aUtil.getPrevmonthPresentDays(fromDateTemp, toDateTemp);
		        	}else{
		        		prvMnthDays = aUtil.getPrevmonthPresentDaysDemo(fromDateTemp, toDateTemp);
		        		
		        	}
		        	//System.out.println("prvMnthDays 2 "+prvMnthDays);
		    	}
		 	 	
		        Calendar headercal=Calendar.getInstance();
				//Calendar tocal=Calendar.getInstance();
		    	headercal.set(Calendar.MONTH,eDate.getMonth());
		    	//headercal.add(Calendar.MONTH, -1);
		    	headercal.set(Calendar.DAY_OF_MONTH, headercal.getActualMinimum(Calendar.DATE));
		    	Date headerfromDate=headercal.getTime();
		    	
		    	double presentDays=aUtil.getNoOfPresentDaysForOT();
		    	//System.out.println("presentDays are wrong...... "+presentDays);
		    	double betweenDays=aUtil.getBtndaysPresentDaysForOT(headerfromDate);

			 	//RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, stremployeeId);
			 	RchrDesignation rchrDesignation = employeeUtil.getEmployeeDesignation(employee,strDate,endDate);
			 SalariesUtil salariesUtil = aUtil.getSalariesUtilOT(rchrDesignation,employee);


		    	System.out.println("between days...... "+betweenDays);
		    	System.out.println("prvMnthDays days...... "+prvMnthDays);
		    	System.out.println("employee.getPreattddays() days...... "+employee.getPreattddays());

		    	long servAndPredays= (long)prvMnthDays + employee.getPreattddays();
				if (employee.getRCPLEmpSalSetupList().size()>0){
					 presentServiceDays = (long)prvMnthDays + (long)betweenDays + employee.getPreattddays()+(long)presentDays;
					if(presentServiceDays>75 || employee.getRCPLEmpSalSetupList().get(0).isNotdesig() || employee.isSenior() ){
						attdIncentive=30;
					}else{
						attdIncentive=0;
					}

					if(employee.getRCPLEmpSalSetupList().get(0).isNotdesig()){
						basicAmount = employee.getRCPLEmpSalSetupList().get(0).getPerdaybasic().doubleValue();
						serviceIncentive = employee.getRCPLEmpSalSetupList().get(0).getServiceince().doubleValue();
						//attdIncentive=30;
					}else{
						basicAmount = salariesUtil.getBasicAmount();
						serviceIncentive = salariesUtil.getServiceIncentiveAmount();
					}
				}
		 		//System.out.println("basic amount "+basicAmount);
		 		//System.out.println("sercie amount "+serviceIncentive);
		 		//System.out.println("Ots "+ots);
	 			basicAmount= (double) Math.round(basicAmount);
	 			serviceIncentive= (double) Math.round(serviceIncentive);
		 		double totalAmount = (attdIncentive +  serviceIncentive + basicAmount)*ots;
		 		//System.out.println("total amout "+totalAmount);



				//RCHR_OverTime overTime = null;
				OBCriteria<RCHR_OverTime> overTimeOBCriteria = OBDal.getInstance().createCriteria(RCHR_OverTime.class);
				overTimeOBCriteria.add(Restrictions.eq(RCHR_OverTime.PROPERTY_OVERTIMEHEADER,head));
				overTimeOBCriteria.add(Restrictions.eq(RCHR_OverTime.PROPERTY_EMPLOYEE,employee));

				if(overTimeOBCriteria.list().size()==0){
					RCHR_OverTime overTime = OBProvider.getInstance().get(RCHR_OverTime.class);
					overTime.setOrganization(head.getOrganization());
					overTime.setOverTimeHeader(head);
					overTime.setDayRate(new BigDecimal(basicAmount));
					//overTime.setDayRate(new BigDecimal(basicAmount, MathContext))
					overTime.setSeviceincentive(new BigDecimal(serviceIncentive).longValue());
					overTime.setEmployeeDepartment(employee.getEmployeeDepartment());
					overTime.setEmployee(employee);
					overTime.setImage(employee.getImage());
					//overTime.setShfit(overTimeProcessLine.getRcprShift());
					//overTime.setNumberofots(new BigDecimal(ots));
					overTime.setNumberofots(new BigDecimal(ots));
					//overTime.setNumberofcontinues(continueShift);
					overTime.setNumberofcontinues(new BigDecimal(continueShift));
					overTime.setServicedays(new BigDecimal(presentServiceDays));
					overTime.setAmount(new BigDecimal(totalAmount));
					OBDal.getInstance().save(overTime);
					rchrEmployeeRCHROverTimeHashMap.put(employee,overTime);
				}
    	}
		if (rchrOvertimeprocessLineList.size()>0){
			for(RchrOvertimeprocessLine rchrOvertimeprocessLine : rchrOvertimeprocessLineList){
				if (rchrEmployeeRCHROverTimeHashMap.containsKey(rchrOvertimeprocessLine.getEmployee())){
					RCHR_OverTime rchrOverTime = rchrEmployeeRCHROverTimeHashMap.get(rchrOvertimeprocessLine.getEmployee());
					insertDateLines(rchrOvertimeprocessLine,rchrOverTime);
				}

			}
		}
    }

    private void insertDateLines(RchrOvertimeprocessLine rchrOvertimeprocessLine, RCHR_OverTime rchrOverTime){

		//RCHR_OverTime overTime = null;


			RchrOvertimedates rchrOvertimedates = OBProvider.getInstance().get(RchrOvertimedates.class);
			rchrOvertimedates.setOrganization(rchrOverTime.getOrganization());
			rchrOvertimedates.setOtdate(rchrOvertimeprocessLine.getRchrOvertimeprocess().getDate());
			rchrOvertimedates.setOverTime(rchrOverTime);
			rchrOvertimedates.setRcprShift(rchrOvertimeprocessLine.getRcprShift());
			rchrOvertimedates.setProcess(Boolean.TRUE);
			rchrOvertimedates.setRchrOvertimeprocessLine(rchrOvertimeprocessLine);
			OBDal.getInstance().save(rchrOvertimedates);


	}

    private List<RchrOvertimeprocessLine> getOvertimeProcessLinesList(Date startDate, Date endDate){
		String hql = "SELECT aline from RCHR_Overtimeprocess_Line AS aline "
				+ " JOIN aline.rchrOvertimeprocess AS att "
				+ " WHERE  "
				+ " aline.overtime= true "
				+ " AND att.date BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endDate) + "' "
				+ " AND 1 = 1 ";
		Session session = OBDal.getInstance().getSession();
		List list = session.createQuery(hql).list();
		return list;
	}


    private double getNoofOTsfromOtProcess(Date startDate, Date endDate, String employeeId) {
    	 String hql = "SELECT COUNT(aline.id) from RCHR_Overtimeprocess_Line AS aline "
                 + " JOIN aline.rchrOvertimeprocess AS att "
                 + " WHERE aline.employee.id = '" + employeeId + "' "
                 + " AND aline.overtime= true "
                 + " AND att.date BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endDate) + "' "
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


	private double getNoofContinuessfromOtProcess(Date startDate, Date endDate, String employeeId) {
    	 String hql = "SELECT COUNT(aline.id) from RCHR_Overtimeprocess_Line AS aline "
                 + " JOIN aline.rchrOvertimeprocess AS att "
                 + " WHERE aline.employee.id = '" + employeeId + "' "
                 + " AND aline.iscontinueshift= true "
                 + " AND att.date BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(endDate) + "' "
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
    private BigDecimal calculateOverTimeAmount(double attendOTInMins, double actualShiftTimeInMins, double dayRate) {
        System.out.println("dayrate is "+dayRate);
        System.out.println("actualShiftTimeInMins is "+actualShiftTimeInMins);
        System.out.println("attendOTInMins is "+attendOTInMins);
        return new BigDecimal((dayRate / actualShiftTimeInMins) * (attendOTInMins));
    }

    private BigDecimal getEmployeeSalary(String strEmployeeId, boolean isPerDay) {
        BigDecimal retVal = new BigDecimal(BigInteger.ZERO);
        String hql = " SELECT ess FROM RCPL_EmpSalSetup ess "
                + " WHERE ess.employee.id='" + strEmployeeId + "' "
                + " AND ess.active=true";
        List<RCPL_EmpSalSetup> empList = OBDal.getInstance().getSession().createQuery(hql).list();
        if (empList.size() > 0) {
            if (isPerDay) {
                retVal = empList.get(0).getPerDaySalary();
            } else {
                retVal = empList.get(0).getGrossAmtPerYear();
            }
        }
        return retVal;
    }

    private BigDecimal getOverTimeHours(RchrAttendLine line) {
        BigDecimal totalWorkedInMins = new TimeDifference(line.getPunchin(), line.getPunchout()).getShiftInMins();
        BigDecimal shiftTimeInMins = line.getShift().getShiftInMins();
        BigDecimal retVal = totalWorkedInMins.subtract(shiftTimeInMins).divide(new BigDecimal(60), 3, RoundingMode.HALF_UP);
        System.out.println(retVal + " should be 90");
        return retVal;
    }*/
}

