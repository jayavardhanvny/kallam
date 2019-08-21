package com.redcarpet.payroll.util;

import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.ad_actionbutton.AttendancePojo;
import com.redcarpet.payroll.data.RCPLElectricityBill;
import com.redcarpet.payroll.data.RCPL_PayrollTemplate;
import com.redcarpet.payroll.data.RcplSecuritydeposit;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;

import java.util.HashMap;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.service.db.DalConnectionProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author S.A. Mateen
 *    **** TirumalKumar.U ***
 * @description Utility class for calculating LIC Policy, Tokens, Power,
 * Cleaning, TDS, Employee Welfare Fund & Loans_Allowances
 */
public class PayrollDBSessionUtil implements PayrollDeductionsImpl {
    private static Logger logger = Logger.getLogger(PayrollDBSessionUtil.class);
    private String templateId;
    private String employeeId;
    private Date startDate;
    private Date endDate;

    public PayrollDBSessionUtil() {
    }

    public PayrollDBSessionUtil(String templateId, String employeeId, Date startDate, Date endDate) {
        this(templateId, employeeId, startDate);
        this.endDate = endDate;
    }

    public PayrollDBSessionUtil(String templateId, String employeeId, Date startDate) {
        this.templateId = templateId;
        this.employeeId = employeeId;
        this.startDate = startDate;
    }

    public PayrollDBSessionUtil(String templateId, String employeeId) {
        this.templateId = templateId;
        this.employeeId = employeeId;
        this.startDate = new Date();
    }

    public PayrollDBSessionUtil(String employeeId, Date startDate, Date endDate) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate=endDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getTemplateId() {
        return templateId;
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

    @Override
    public double getSumofLICAmount() {

        /*String hql = "SELECT SUM(lic.permiumPerYear) as licamount FROM RCHR_Emp_Policy AS lic "
         * + " WHERE lic.employee.id ='" + getEmployeeId() + "' "
         * + " AND lic.employee.lICApplicable='Y'";*/
        String hql = "SELECT permium AS permium FROM RCHR_PolicySlab AS ps "
                + "   WHERE ps.employee.id ='" + getEmployeeId() + "' "
                + "   AND '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' BETWEEN ps.fromDate AND ps.toDate ";
        return getFirstRecordValue(hql);
    }

    @Override
    public double getSumofTokensAmount() {
        String hql = " SELECT SUM(token.total) AS tokensum FROM RCHR_IssueToken as token "
                + " WHERE token.employee.id ='" + getEmployeeId() + "' "
                + " AND (token.issueDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";
        return getFirstRecordValue(hql);
    }

    @Override
    public double getSecurityDeposit() {
        return 0;
    }
@Override
    public double getDepartmentStoreRecovery() {
        
    	//logger.info("under departmental recovery");
    	String hql = "SELECT SUM(store.payableamount) FROM RCGI_DepartmentIssue AS store "
    			+ " WHERE store.employee.id = '" + getEmployeeId() + "' "
    			// + " AND store.movementDate BETWEEN '" +getStartDate() + "' AND '" +getEndDate()+ "'"
                + " AND store.documentStatus='CO' "
   		       + " AND store.movementDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'";
    	//logger.info("value.."+getFirstRecordValue(hql));
    	return getFirstRecordValue(hql);
    }
/*    @Override
    public double getDepartmentStoreRecovery() {
    	//logger.info("under departmental recovery");
    	String hql = "SELECT SUM(store.recoveramount) FROM RCPL_DepartmentStore AS store "
    			+ " WHERE store.employee.id = '" + getEmployeeId() + "' "
    			 + " AND store.fromDate BETWEEN '" +getStartDate() + "' AND '" +getEndDate()+ "'"
   		       + "AND store.toDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'";
    	//logger.info("value.."+getFirstRecordValue(hql));
    	return getFirstRecordValue(hql);
    }*/

/*
    public double getGasRecovery() {
    	String hql = "SELECT SUM(gas.recoveramount) FROM RCPL_Gas AS gas "
    			+ " JOIN gas.payrollPeriod as period"
    			+ " WHERE gas.employee.id = '" + getEmployeeId() + "' "
    			+ " AND (period.startingDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')"
    	        + " AND (period.endingDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";
    	return getFirstRecordValue(hql);
    }
*/
    public String getGasRecoveryHQL(){
        String hql = "SELECT loanline "
                + "  FROM RCHR_Gas_Advancelines as loanline "
                + "  JOIN loanline.loanSchedule as loan "
                + "  WHERE loan.employee.id = '" + getEmployeeId() + "'"
                + "  AND loanline.paid = false"
                + "  AND loanline.dueDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";
        return hql;
    }


    @Override
    public double getGasRecovery() {
        String hql = getGasRecoveryHQL();
        List<RCHRGasAdvancelines> loanList = OBDal.getInstance().getSession().createQuery(hql).list();
       BigDecimal loanamts = new BigDecimal("0");
       BigDecimal totalAmounts = BigDecimal.ZERO;
        for (RCHRGasAdvancelines loans : loanList) {
        	loanamts = loans.getLoanAmount();
        	totalAmounts=totalAmounts.add(loanamts);
        	//loans.setPaid(true);
        }
        return totalAmounts.doubleValue();
    }


    @Override
    public double getOtherDeductions() {
    	String hql = "SELECT SUM(od.amount) FROM RCPL_Otherdeductions AS od "
    			+ " JOIN od.payrollPeriod as period"
    			+ " WHERE od.employee.id = '" + getEmployeeId() + "' "
    			+ " AND (period.startingDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')"
    	        + " AND (period.endingDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";
    	return getFirstRecordValue(hql);
    }

    @Override
    public double getEmployeeFine() {
    	String hql = "SELECT SUM(empfine.fineamount) FROM RCPL_EmpFine AS empfine "
    			+ " WHERE empfine.employee.id = '" + getEmployeeId() + "' "
    			+ " AND (empfine.finedate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";
    	return getFirstRecordValue(hql);
    }

    public String getSecurityDepositHQL(){
        String hql = "SELECT sd FROM RCPL_SecurityDeposit AS sd "
                + " JOIN sd.payrollPeriod as period"
                + " WHERE sd.employee.id = '" + getEmployeeId() + "' "
                + " AND (period.startingDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')"
                + " AND (period.endingDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";
        return hql;
    }

    @Override
    public double getSecurityDeposit(double presentDays) {
    	String hql = this.getSecurityDepositHQL();
        List<RcplSecuritydeposit> depositList = OBDal.getInstance().getSession().createQuery(hql).list();
       BigDecimal loanamt = new BigDecimal("0");
       BigDecimal totalAmount = BigDecimal.ZERO;

   		//presentdays = getNoOfDaysPresent();
        if(presentDays > 10 ){
   		for (RcplSecuritydeposit security : depositList) {
        	loanamt = security.getDepositamount();
        	totalAmount=totalAmount.add(loanamt);
        	//security.setPaid(true);
        	//loans.setPaidAmount(loanamt);
        }
        }
        return totalAmount.doubleValue();

    }




    /*
		String hql = "SELECT loanline "
                + "  FROM RCHR_Gas_Advancelines as loanline "
                + "  JOIN loanline.loanSchedule as loan "
                + "  WHERE loan.employee.id = '" + getEmployeeId() + "'"
                + "  AND loanline.paid = false"
                + "  AND loanline.dueDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";




        public double getSecurityDeposit() {
    	String hql = "SELECT sd FROM RCPL_SecurityDeposit AS sd "
    			+ " JOIN sd.payrollPeriod as period"
    			+ " WHERE sd.employee.id = '" + getEmployeeId() + "' "
    			+ " AND (period.startingDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')"
    	        + " AND (period.endingDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";

        List<RcplSecuritydeposit> depositList = OBDal.getInstance().getSession().createQuery(hql).list();
       BigDecimal loanamt = new BigDecimal("0");
       BigDecimal totalAmount = BigDecimal.ZERO;
   		double presentdays = getNoOfDaysPresent();
        if(presentdays > 10 ){
   		for (RcplSecuritydeposit security : depositList) {
        	loanamt = security.getDepositamount();

        	totalAmount=totalAmount.add(loanamt);
        	security.setPaid(true);
        	//loans.setPaidAmount(loanamt);
        }
        }
        return totalAmount.doubleValue();
    }
    */



    @Override
    public double getSumofPowerAmount() {
        RCPL_PayrollTemplate template = OBDal.getInstance().get(RCPL_PayrollTemplate.class, getTemplateId());
        double unitRate = template.getUnitRate().doubleValue();
        RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
        if (!employee.isRoomAllotted()) {
            return 0;
        }
        String hql = "SELECT (reading.totalReading - rom.electricalSubsidyUnits) FROM RCHR_ElectricReading as reading  "
                + " JOIN reading.room as rom"
                + " WHERE rom.id IN (SELECT emp.room.id FROM Rchr_Employee as emp  "
                + "    WHERE roomAllotted='Y'  "
                + "    AND emp.id = '" + getEmployeeId() + "') "
                 + "    AND reading.fromDate = '" + getStartDate() + "'";
        String strNoOfSharing = "0";
        try {
            strNoOfSharing = PayrollDBUtilityData.getNoOfEmployeesSharingRoom(new DalConnectionProvider(), getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (getFirstRecordValue(hql) * unitRate) / Double.valueOf(strNoOfSharing);
    }

    @Override
    public double getCleaningAmount() {

        return OBDal.getInstance().get(RchrEmployee.class, getEmployeeId()).isRoomAllotted()
                ? OBDal.getInstance().get(RCPL_PayrollTemplate.class, getTemplateId()).getCleaningCharges().doubleValue() : 0;
    }

    @Override
    public double getEmployeeWelfareFund() {
        return OBDal.getInstance().get(RCPL_PayrollTemplate.class, getTemplateId()).getWelfareFund().doubleValue();
    }

    public double getEmployeeWelfareFund(HashMap<RCHR_Room, RoomPojo> roomRoomUtilHashMap,
                                         AttendancePojo  attandancePojo) {
        //return OBDal.getInstance().get(RCPL_PayrollTemplate.class, getTemplateId()).getWelfareFund().doubleValue();
        double welfareAmount = 0;
            RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
            if (employee.isRoomAllotted() && attandancePojo.getNoOfDaysPresent() > 0) {
                if (employee.getRoom().getBlock().getBlockNo().equals("FQ")){
                    if (roomRoomUtilHashMap.containsKey(employee.getRoom())){
                        RoomPojo roomPojo = roomRoomUtilHashMap.get(employee.getRoom());
                        welfareAmount = this.getProportionateAmount(attandancePojo.getNoOfDaysPresent(), roomPojo.getTotalEmployeesPresents(), employee.getRoom().getBlock().getWelfarefund().doubleValue());
                    }
                } else {
                    welfareAmount = employee.getRoom().getBlock().getWelfarefund().doubleValue();
                }

                logger.info("welfareAmount " +welfareAmount);
                //return employee.getRoom().getBlock().getWelfarefund().doubleValue();
            }
            return welfareAmount;
    }

    @Override
    public double getTDSAmount() {
        // to be calculated later
        return 0;
    }

    public String getLoansAndAdvanceHQL(){
        String hql = "SELECT loanline "
                + "  FROM RCHR_Emp_Loanlines as loanline "
                + "  JOIN loanline.loanSchedule as loan "
                + "  WHERE loan.employee.id = '" + getEmployeeId() + "'"
                + "  AND loanline.paid = false"
                + "  AND loanline.dueDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "')";
        return hql;
    }


    @Override
    public double getLoans_AllowancesAmount() {
        String hql = getLoansAndAdvanceHQL();
        List<RCHR_Emp_Loanlines> loanList = OBDal.getInstance().getSession().createQuery(hql).list();
       BigDecimal loanamt = new BigDecimal("0");
       BigDecimal totalAmount = BigDecimal.ZERO;
        for (RCHR_Emp_Loanlines loans : loanList) {
        	loanamt = loans.getLoanAmount();
        	totalAmount=totalAmount.add(loanamt);
        	//loans.setPaid(true);
        	//loans.setPaidAmount(loanamt);
        }
        return totalAmount.doubleValue();
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

    public double getLoans_DishCharges() {
        String strNoOfSharing = "0";
        try {
            strNoOfSharing = PayrollDBUtilityData.getNoOfEmployeesSharingRoom(new DalConnectionProvider(), getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        double dishCharges = OBDal.getInstance().get(RCPL_PayrollTemplate.class, getTemplateId()).getDishCharges().doubleValue();
        double sharing = Double.valueOf(strNoOfSharing);
        if (sharing == 0) {
            return 0;
        } else {
            return dishCharges / sharing;
        }
    }

    public double getVPFAmount() {
        return OBDal.getInstance().get(RchrEmployee.class, getEmployeeId()).isVPF()
                ? OBDal.getInstance().get(RchrEmployee.class, getEmployeeId()).getVPFAmount().doubleValue() : 0;
    }

    public double getInstantOverTimeAmount() {
        String strRetValue ="0";
        try {
           strRetValue = PayrollDBUtilityData.getSumOfInstantOverTimeAmount(new DalConnectionProvider(),
                   PayrollUtils.getInstance().getDBCompatiableDate(this.startDate), PayrollUtils.getInstance().getDBCompatiableDate(this.endDate),
                   getEmployeeId());
        } catch (Exception e) {
        }
        return new BigDecimal(strRetValue).doubleValue();
    }

    public double getFineAmount() {
        String strRetValue ="0";
        try {
           strRetValue = PayrollDBUtilityData.getSumOfFineAmount(new DalConnectionProvider(), getEmployeeId(),
                   PayrollUtils.getInstance().getDBCompatiableDate(this.startDate), PayrollUtils.getInstance().getDBCompatiableDate(this.endDate));
        } catch (Exception e) {
        }
        return new BigDecimal(strRetValue).doubleValue();
    }

    public double getLossOfPayBasedOnAttnd(double presentDays ,double noOfCasualLeaves,double LOP) {
        double result;
        if(presentDays>=20) {
        	result = LOP;
        } else if(presentDays<20 && presentDays>=10){
        	if(noOfCasualLeaves>1){
				result = (LOP +1);
			}
			else {
				result = LOP;
			}
       } else {
    	   result = (noOfCasualLeaves + LOP);
       }
        return result;
    }

    public double getTotalWeeklyOffs(double perDaySal,double noOfWeekOffsNotApplicable) {
    	return perDaySal*noOfWeekOffsNotApplicable;
    }

    public double getRentAmount(HashMap<RCHR_Room, RoomPojo> roomRoomUtilHashMap, AttendancePojo  attandancePojo){
        /*RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
        double roomRent = 0;
        double proportionateRentBill = 0;
        if (rchrEmployee.isRoomAllotted() && rchrEmployee.getRoom() != null && rchrEmployee.getRoom().isRented()){
            roomRent = rchrEmployee.getRoom().getBlock().getRentamount().doubleValue();
            if (roomRoomUtilHashMap.containsKey(rchrEmployee.getRoom()))
                proportionateRentBill = this.getProportionateAmount(attandancePojo.getNoOfDaysPresent(), roomRoomUtilHashMap.get(rchrEmployee.getRoom()).getTotalEmployeesPresents(), roomRent);
        }
        return proportionateRentBill;
        */

 	   String hql = "SELECT SUM(roomrentLines.rentamount) FROM RCHR_RoomRentLines AS roomrentLines "
 			   + " JOIN roomrentLines.rchrRoomrent as roomrent"
 		       + " WHERE roomrentLines.employee.id = '" + getEmployeeId() + "' "
 		       + " AND roomrent.fromDate BETWEEN '" +getStartDate()+ "' AND '" +getEndDate()+ "'"
 		       + "AND roomrent.toDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'";
        return getFirstRecordValue(hql);

     }

    public double getMessAmount(){
 	   String hql = "SELECT SUM(bill.sum) FROM RCPL_MessBill AS bill "
 		       + " WHERE bill.employee.id = '" + getEmployeeId() + "' "
 		       + " AND bill.fromDate BETWEEN '" +getStartDate() + "' AND '" +getEndDate()+ "'"
 		       + "AND bill.toDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'";
 	   return getFirstRecordValue(hql);
    }

    public double pendingLoanAMount(){
    	 String hql = "SELECT SUM(loanline.loanAmount) "
               + "  FROM RCHR_Emp_Loanlines as loanline "
               + "  JOIN loanline.loanSchedule as loan "
               + "  WHERE loan.employee.id = '" + getEmployeeId() + "'"
               + "  AND loanline.paid = false";
             //  + "  AND loanline.dueDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "'";
    	   return getFirstRecordValue(hql);
       }



    public double getLateComingCountForStaff(){
    	int count =0;
            String hql = "SELECT temp FROM RCHR_Dailyattenddemo as temp "
            + "join temp.rcprShift as shift "
            + "join temp.employee as employee"
      		+ " where temp.attendanceDate between '"+ getStartDate()+"'  And '"+ getEndDate() +"' "
      		+ "and temp.employee.id = '"+ getEmployeeId()+"'"
      		+ "and temp.rcprShift.id <> 'ED4817728DD24E86A132094AE5B1DCDE' and temp.present= 'Y' and temp.overtime= 'N'";
      Session session = OBDal.getInstance().getSession();
      List<RCHRDailyattenddemo> temp = OBDal.getInstance().getSession().createQuery(hql).list();
      for(RCHRDailyattenddemo attd: temp){
    	  try{
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date datefrom = format.parse(attd.getPunchIn().toString());
	    	Date dateto = format.parse(attd.getRcprShift().getFromTime().toString());
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(datefrom);
	    	cal.add(Calendar.HOUR, 1);
	    	Date punchtime = cal.getTime();
	    	int puninms = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	cal.setTime(dateto);
	    	cal.add(Calendar.HOUR, 1);
	    	int shiftstart = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	int timediff = puninms - shiftstart;
	    	RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
	    	String hql1 = "SELECT lateline "
	    	              + "  FROM RCHR_Latecomingcountline as lateline "
	    	              + "  JOIN lateline.rchrLatecomingcount as late "
	    	              + "  WHERE late.employeeType = '" + emp.getEmployeeType() + "'"
	    	              + "  AND late.timeexception <= '"+Long.valueOf(timediff)+"' and  '"+ Long.valueOf(timediff) +"' between lateline.minmumminutes and lateline.maximumminutes";
	    	       List<RCHRLatecomingcountline> lateList = OBDal.getInstance().getSession().createQuery(hql1).list();
	    	        for (RCHRLatecomingcountline lates : lateList) {
	    	        	count = count + 1;
	    	        }
    	  }
    	    catch (Exception e) {
    		  e.printStackTrace();
		}
      }
      return count;
    }


    public double getLateAttendanceDisincentiveForStaff(){
    	int count =0;
    	int exceptlatecount = 0;
    	int noofcountsperday = 0;
    	Boolean cutleaves = false;
    	double amount = 0;
            String hql = "SELECT temp FROM RCHR_Dailyattenddemo as temp "
            + "join temp.rcprShift as shift "
            + "join temp.employee as employee"
      		+ " where temp.attendanceDate between '"+ getStartDate()+"'  And '"+ getEndDate() +"' "
      		+ "and temp.employee.id = '"+ getEmployeeId()+"'"
      		+ "and temp.rcprShift.id <> 'ED4817728DD24E86A132094AE5B1DCDE' and temp.present= 'Y' and temp.overtime= 'N'";
      Session session = OBDal.getInstance().getSession();
      List<RCHRDailyattenddemo> temp = OBDal.getInstance().getSession().createQuery(hql).list();
      for(RCHRDailyattenddemo attd: temp){
    	  try{
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date datefrom = format.parse(attd.getPunchIn().toString());
	    	Date dateto = format.parse(attd.getRcprShift().getFromTime().toString());
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(datefrom);
	    	cal.add(Calendar.HOUR, 1);
	    	Date punchtime = cal.getTime();
	    	int puninms = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	cal.setTime(dateto);
	    	cal.add(Calendar.HOUR, 1);
	    	int shiftstart = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	int timediff = puninms - shiftstart;
	    	RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
	    	String hql1 = "SELECT lateline "
	    	              + "  FROM RCHR_Latecomingcountline as lateline "
	    	              + "  JOIN lateline.rchrLatecomingcount as late "
	    	              + "  WHERE late.employeeType = '" + emp.getEmployeeType() + "'"
	    	              + "  AND late.timeexception <= '"+Long.valueOf(timediff)+"' and  '"+ Long.valueOf(timediff) +"' between lateline.minmumminutes and lateline.maximumminutes";
	    	       List<RCHRLatecomingcountline> lateList = OBDal.getInstance().getSession().createQuery(hql1).list();
	    	        for (RCHRLatecomingcountline lates : lateList) {
	    	        	count = count + 1;
	    	        	exceptlatecount = lates.getNooflateexception().intValue();
		    			noofcountsperday = lates.getNooflatecountperday().intValue();
		    			cutleaves = lates.getRchrLatecomingcount().isCuttingfromleaves();
	    	        }
    	  }
    	    catch (Exception e) {
    		  e.printStackTrace();
		}
      }
     if(count >= exceptlatecount && exceptlatecount > 0){
    	  count = count - exceptlatecount;
    	  count = (count/noofcountsperday) ;
    	  if(cutleaves.equals(true)){
   		  amount =  cutLateAttendanceDisincentiveLeaves(count, getEmployeeId());
    	  }else{
	    	amount = count;
	    }
      }else{
    	  amount = 0;
      }
      return amount;
      }



    public double cutLateAttendanceDisincentiveLeaves(int count, String id){
          	double amount = 0;
      	RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, id);
      	OBCriteria<RCHR_Leavetype> leaveTypes = OBDal.getInstance().createCriteria(RCHR_Leavetype.class);
      	leaveTypes.addOrderBy(RCHR_Leavetype.PROPERTY_PRIORITY, true);
      	for(RCHR_Leavetype rchrLvType: leaveTypes.list()){
      		if(count > 0){
      		OBCriteria<RchrEmployeeleaveBal> rchrEmoloyeeLeaveBalCriteria = OBDal.getInstance().createCriteria(RchrEmployeeleaveBal.class);
      		rchrEmoloyeeLeaveBalCriteria.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_EMPLOYEE, emp));
      		rchrEmoloyeeLeaveBalCriteria.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_LEAVETYPE, rchrLvType));
      		for(RchrEmployeeleaveBal RchrEmployeeleaveBal: rchrEmoloyeeLeaveBalCriteria.list()){
      			if(RchrEmployeeleaveBal.getLobalance().intValue() >= count){
      				RchrEmployeeleaveBal.setLobalance(new BigDecimal(RchrEmployeeleaveBal.getLobalance().intValue()-count));
      				count = 0;
      		}else{
      			count = count - RchrEmployeeleaveBal.getLobalance().intValue();
      			RchrEmployeeleaveBal.setLobalance(new BigDecimal(0));
      		}
      	}
      		}
      	}
      	amount = count;
      	return amount;
          }



    public double getLateAttendanceDisincentive(){
        double amount = 0;
                  String hql = "SELECT temp FROM RCHR_Dailyattenddemo as temp "
                  + "join temp.rcprShift as shift "
                  + "join temp.employee as employee"
            		+ " where temp.attendanceDate between '"+ getStartDate()+"'  And '"+ getEndDate() +"' "
            		+ "and temp.employee.id = '"+ getEmployeeId()+"'"
            		+ "and temp.rcprShift.id <> 'ED4817728DD24E86A132094AE5B1DCDE' and temp.present= 'Y'";
            Session session = OBDal.getInstance().getSession();
            List<RCHRDailyattenddemo> temp = OBDal.getInstance().getSession().createQuery(hql).list();
            for(RCHRDailyattenddemo attd: temp){
          	  try{
      		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      	    	Date datefrom = format.parse(attd.getPunchIn().toString());
      	    	Date dateto = format.parse(attd.getRcprShift().getFromTime().toString());
      	    	Calendar cal = Calendar.getInstance();
      	    	cal.setTime(datefrom);
      	    	cal.add(Calendar.HOUR,1);
      	    	Date punchtime = cal.getTime();
      	    	int puninms = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
      	    	cal.setTime(dateto);
      	    	cal.add(Calendar.HOUR, 1);
      	    	int shiftstart = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
      	    	int timediff = puninms - shiftstart;
       	    	RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
       	 	String hql1 = "SELECT lateline "
  	              + "  FROM RCHR_Latecomingcountline as lateline "
  	              + "  JOIN lateline.rchrLatecomingcount as late "
  	              + "  WHERE late.employeeType = '" + emp.getEmployeeType() + "'"
  	              + "  AND late.timeexception <= '"+ Long.valueOf(timediff) +"' and  '"+ Long.valueOf(timediff) +"' between lateline.minmumminutes and lateline.maximumminutes";
  	       List<RCHRLatecomingcountline> lateList = OBDal.getInstance().getSession().createQuery(hql1).list();
  	        for (RCHRLatecomingcountline lates : lateList) {
  	        	amount = amount + lates.getAmount();
  	        }
       	  }
          	  catch (Exception e) {
          		  e.printStackTrace();
      		}
             }
            return amount;
    }







    /*

    public double getLateComingCountForStaff(){
    	int count =0;
            String hql = "SELECT temp FROM RCHR_Attend_Temp as temp "
            + "join temp.rcprShift as shift "
            + "join temp.employee as employee"
      		+ " where temp.attendanceDate between '"+ getStartDate()+"'  And '"+ getEndDate() +"' "
      		+ "and temp.employee.id = '"+ getEmployeeId()+"'"
      		+ "and temp.rcprShift.id <> 'ED4817728DD24E86A132094AE5B1DCDE' and temp.present= 'Y' and temp.overtime= 'N'";
      Session session = OBDal.getInstance().getSession();
      List<RCHR_Attend_Temp> temp = OBDal.getInstance().getSession().createQuery(hql).list();
      for(RCHR_Attend_Temp attd: temp){
    	  try{
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date datefrom = format.parse(attd.getPunchIn().toString());
	    	Date dateto = format.parse(attd.getRcprShift().getFromTime().toString());
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(datefrom);
	    	cal.add(Calendar.HOUR,1);
	    	Date punchtime = cal.getTime();
	    	int puninms = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	cal.setTime(dateto);
	    	cal.add(Calendar.HOUR, 1);
	    	int shiftstart = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	int timediff = puninms - shiftstart;
	    	if(timediff >= 1){
	    		count = count + 1;
	    	}
	  }
    	  catch (Exception e) {
    		  e.printStackTrace();
		}
      }
      logger.info("count : "+count);
        return count;
      }





    public double getLateAttendanceDisincentiveForStaff(){
    	int count =0;
    	double amount = 0;
            String hql = "SELECT temp FROM RCHR_Attend_Temp as temp "
            + "join temp.rcprShift as shift "
            + "join temp.employee as employee"
      		+ " where temp.attendanceDate between '"+ getStartDate()+"'  And '"+ getEndDate() +"' "
      		+ "and temp.employee.id = '"+ getEmployeeId()+"'"
      		+ "and temp.rcprShift.id <> 'ED4817728DD24E86A132094AE5B1DCDE' and temp.present= 'Y' and temp.overtime= 'N'";
      Session session = OBDal.getInstance().getSession();
      List<RCHR_Attend_Temp> temp = OBDal.getInstance().getSession().createQuery(hql).list();
  //    logger.info("Temp size : "+temp.size());
      for(RCHR_Attend_Temp attd: temp){
    	  try{
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date datefrom = format.parse(attd.getPunchIn().toString());
	    	Date dateto = format.parse(attd.getRcprShift().getFromTime().toString());
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(datefrom);
	    	cal.add(Calendar.HOUR,1);
	    	Date punchtime = cal.getTime();
	    	int puninms = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	cal.setTime(dateto);
	    	cal.add(Calendar.HOUR, 1);
	    	int shiftstart = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	int timediff = puninms - shiftstart;
	//    	logger.info("timediff = "+timediff);
	    	if(timediff >= 1){
	    		count = count + 1;
	//    		  logger.info("**"+count);
	    	}
	  }
    	  catch (Exception e) {
    		  e.printStackTrace();
		}
      }
      if(count >= 3){
  //  	  logger.info("*"+count);
    	  count = (count/2) - 1;
 //   	  logger.info(count);
    	  amount =  cutLateAttendanceDisincentiveLeaves(count, getEmployeeId());
 //   	logger.info("Amount = "+amount);
	    }else{
//	    	logger.info("else condition");
	    	amount = 0;
	    }
 //     logger.info("count in final"+amount);
      return amount;
      }



    public double cutLateAttendanceDisincentiveLeaves(int count, String id){
    	double amount = 0;
//	logger.info("In cutLateAttendanceDisincentiveLeaves Method");
	RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, id);
//	logger.info(emp.getPunchno());
	OBCriteria<RCHR_Leavetype> leaveTypes = OBDal.getInstance().createCriteria(RCHR_Leavetype.class);
	leaveTypes.addOrderBy(RCHR_Leavetype.PROPERTY_PRIORITY, true);
	for(RCHR_Leavetype rchrLvType: leaveTypes.list()){
		if(count > 0){
//			logger.info("cutLateAttendanceDisincentiveLeaves "+count);
		OBCriteria<RchrEmployeeleaveBal> rchrEmoloyeeLeaveBalCriteria = OBDal.getInstance().createCriteria(RchrEmployeeleaveBal.class);
		rchrEmoloyeeLeaveBalCriteria.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_EMPLOYEE, emp));
		rchrEmoloyeeLeaveBalCriteria.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_LEAVETYPE, rchrLvType));
		for(RchrEmployeeleaveBal RchrEmployeeleaveBal: rchrEmoloyeeLeaveBalCriteria.list()){
//			logger.info("count in for loop"+count);
			if(RchrEmployeeleaveBal.getLobalance().intValue() >= count){
//				logger.info("count in if "+count);
				RchrEmployeeleaveBal.setLobalance(new BigDecimal(RchrEmployeeleaveBal.getLobalance().intValue()-count));
				count = 0;
		}else{
			count = count - RchrEmployeeleaveBal.getLobalance().intValue();
			RchrEmployeeleaveBal.setLobalance(new BigDecimal(0));
//			logger.info("count in else"+count);
		}
	}
		}
	}
//	logger.info("loop"+count);
	amount = count;
	return amount;
    }

    public double getLateAttendanceDisincentive(){
    	double amount = 0;
            String hql = "SELECT temp FROM RCHR_Attend_Temp as temp "
            + "join temp.rcprShift as shift "
            + "join temp.employee as employee"
      		+ " where temp.attendanceDate between '"+ getStartDate()+"'  And '"+ getEndDate() +"' "
      		+ "and temp.employee.id = '"+ getEmployeeId()+"'"
      		+ "and temp.rcprShift.id <> 'ED4817728DD24E86A132094AE5B1DCDE' and temp.present= 'Y'";
      Session session = OBDal.getInstance().getSession();
      List<RCHR_Attend_Temp> temp = OBDal.getInstance().getSession().createQuery(hql).list();
      for(RCHR_Attend_Temp attd: temp){
    	  try{
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date datefrom = format.parse(attd.getPunchIn().toString());
	    	Date dateto = format.parse(attd.getRcprShift().getFromTime().toString());
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(datefrom);
	    	cal.add(Calendar.HOUR,1);
	    	Date punchtime = cal.getTime();
	    	int puninms = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	cal.setTime(dateto);
	    	cal.add(Calendar.HOUR, 1);
	    	int shiftstart = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();
	    	int timediff = puninms - shiftstart;
	    	if(timediff > 0 && timediff <= 5){
	    		amount = amount + 5;
	    	}else if(timediff > 5 && timediff <= 10){
	    		amount = amount + 10;
	    	}else if(timediff > 10 && timediff <= 15){
	    		amount = amount + 20;
	    	}else if(timediff > 15){
	    		amount = amount + 50;
	    	}else{
	    		amount = amount + 0;
	    	}
	  }
    	  catch (Exception e) {
    		  e.printStackTrace();
		}
      }
      return amount;
      }
   */
    public double getCheckLoanAmountForFinacialyr(){
  	 String hql = "SELECT SUM(loanline.loanAmount) "
             + "  FROM RCHR_Emp_Loanlines as loanline "
             + "  JOIN loanline.loanSchedule as loan "
             + "  WHERE loan.employee.id = '" + getEmployeeId() + "'"
             + "  AND loanline.dueDate BETWEEN '" + getStartDate() + "' AND '" + getEndDate() + "'";

  	   return getFirstRecordValue(hql);
     }

    public int getMesstypeCount(){
    	int messcount=0;
    	try{
        	String sql ="select count(DISTINCT(rcpl_messtype_id)) from RCPL_MessBill "
       		       + " where rchr_employee_id = '" + getEmployeeId() + "' "
      		       + " AND fromdate BETWEEN '" +getStartDate() + "' AND '" +getEndDate()+ "'"
      		       + "AND todate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'";
    	Connection conn = OBDal.getInstance().getConnection();
    	Statement stmt = conn.createStatement();
    	ResultSet res = stmt.executeQuery(sql);
    	while(res.next()){
         	//logger.info("desigId"+res.getString(1));
         	messcount=res.getInt(1);
         }
    	} catch (Exception e) {
         e.printStackTrace();
    	}

    	return messcount;
     }

    public double getElectricityAmount(HashMap<RCHR_Room, RoomPojo> roomRoomUtilHashMap, AttendancePojo  attandancePojo){
        RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
        Double deductedAmount  = new Double(0);
        //roomRoomUtilHashMap.
        if (rchrEmployee.isRoomAllotted() && rchrEmployee.getRoom() != null ) {
            RoomPojo roomPojo = this.getRoomPojoObject(roomRoomUtilHashMap, rchrEmployee);
            deductedAmount= this.getProportionateDeductedAmount(attandancePojo.getNoOfDaysPresent(),
                    roomPojo.getTotalEmployeesPresents(), roomPojo.getTotalElectricityBill(), roomPojo.getNoOfEmployeesInRoom());
            return  deductedAmount;
        } else
            return 0;



        /*
        String hql = "SELECT SUM(bill.amount) FROM RCPL_ElectricityBill AS bill "
 		       + " WHERE bill.employee.id = '" + getEmployeeId() + "' "
 		       + " AND bill.fromDate BETWEEN '" +getStartDate()+ "' AND '" +getEndDate()+ "'"
 		       + " AND bill.toDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'";


        double proportionateElectricityBill = 0 ;
        Double totalPresents, noOfEmployeesInRoom, totalElectricityBill  = new Double(0);

        if (rchrEmployee.isRoomAllotted() && rchrEmployee.getRoom() != null ){
            if (roomRoomUtilHashMap.containsKey(rchrEmployee.getRoom())){
                totalPresents = roomRoomUtilHashMap.get(rchrEmployee.getRoom()).getTotalEmployeesPresents();
                totalElectricityBill = roomRoomUtilHashMap.get(rchrEmployee.getRoom()).getTotalElectricityBill();
                noOfEmployeesInRoom = roomRoomUtilHashMap.get(rchrEmployee.getRoom()).getNoOfEmployeesInRoom();
                logger.info("In If Condition ");
            } else {
                logger.info("In ELse Condition ");
                totalPresents = getTotalPresentsEmployeesInRoom(rchrEmployee.getRoom().getId());
                totalElectricityBill = this.getRoomElectricityBill(rchrEmployee.getRoom().getId());
                noOfEmployeesInRoom = rchrEmployee.getRoom().getNoofemployees().doubleValue();
                RoomPojo roomPojo = new RoomPojo(rchrEmployee.getRoom().getId(), totalPresents, noOfEmployeesInRoom);
                roomPojo.setTotalElectricityBill(totalElectricityBill);
                roomRoomUtilHashMap.put(rchrEmployee.getRoom(), roomPojo);
            }



            logger.info("Presents "+totalPresents +" And electricitBill "+totalElectricityBill +" And Employee Present "+attandancePojo.getNoOfDaysPresent());

            return proportionateElectricityBill;
        } else
            return 0;
            */
    }

    public double getProportionateDeductedAmount(double singleEmployeePresents, double totalPresentsOfEmployeesInRoom, double amount, double noOfPersonsInRoom){
        double proportionateAmount = 0;
        if (totalPresentsOfEmployeesInRoom>0){
            proportionateAmount = this.getProportionateAmount(singleEmployeePresents, totalPresentsOfEmployeesInRoom, amount);
        } else {
            proportionateAmount = amount/noOfPersonsInRoom;
        }
        return proportionateAmount;
    }



    public RoomPojo getRoomPojoObject(HashMap<RCHR_Room, RoomPojo> roomRoomUtilHashMap, RchrEmployee rchrEmployee){
        //RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, getEmployeeId());
        RoomPojo roomPojo = null;
        double totalElectricityBill = 0;
        if (roomRoomUtilHashMap.containsKey(rchrEmployee.getRoom())){
            roomPojo = roomRoomUtilHashMap.get(rchrEmployee.getRoom());
            //logger.info("In If Condition RoomObject ");
        } else {
            //logger.info("In ELse Condition RoomObject");
            totalElectricityBill = this.getRoomElectricityBill(rchrEmployee.getRoom().getId());
            roomPojo = new RoomPojo(rchrEmployee.getRoom().getId(), this.getTotalPresentsEmployeesInRoom(rchrEmployee.getRoom().getId()),
                    rchrEmployee.getRoom().getNoofemployees().doubleValue(), rchrEmployee.getRoom().getBlock().getRentamount().doubleValue() ,
                    rchrEmployee.getRoom().getBlock().getWelfarefund().doubleValue(), totalElectricityBill);
            //roomPojo.setTotalElectricityBill(totalElectricityBill);
            roomRoomUtilHashMap.put(rchrEmployee.getRoom(), roomPojo);
        }
        return roomPojo;
    }



    public double getRoomElectricityBill(String roomId){
        String hql = "SELECT SUM(bill.amount) FROM RCHR_ElectricReading AS bill "
                + " JOIN bill.room AS room "
                + " WHERE bill.fromDate BETWEEN '" +PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(getEndDate())+ "' "
                + " AND bill.toDate BETWEEN '"+PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(getEndDate())+ "' "
                + " AND room.id = '"+roomId+"'";
        //logger.info("HQl is Electric Bill "+hql);
        return getFirstRecordValue(hql);
    }

    public double getProportionateAmount(double presents, double totalEmployeesPresentsInRoom, double totalAmount){
        if (totalEmployeesPresentsInRoom>0)
            return (totalAmount/totalEmployeesPresentsInRoom)*presents;
        else
            return 0;
    }

    public double getTotalPresentsEmployeesInRoom(String roomId){
        String hql = " SELECT COALESCE(count(demo.present),0) AS presents FROM RCHR_Dailyattenddemo AS demo, Rchr_Room_Employee AS emproom " +
                " WHERE demo.employee.id=emproom.employee.id " +
                " AND demo.attendanceDate BETWEEN '"+PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(getEndDate())+ "' " +
                " AND demo.present='Y' AND demo.overtime='N' " +
                " AND emproom.isvacating ='N' AND emproom.allocate='Y' " +
                " AND emproom.room.id= '"+roomId+"' GROUP BY emproom.room.id ";
        //logger.info("Electricity Bill is hql "+hql);

        return getFirstRecordValue(hql);
    }




    public List<RCPLElectricityBill> getElectricityBillObject(){
        String hql = "SELECT bill FROM RCPL_ElectricityBill AS bill "
                + " WHERE bill.employee.id = '" + getEmployeeId() + "' "
                + " AND bill.fromDate BETWEEN '" +PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(getEndDate())+ "'"
                + " AND bill.toDate BETWEEN '"+PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+"' AND '"+PayrollUtils.getInstance().getDBCompatiableDate(getEndDate())+"'";
        List<RCPLElectricityBill> rchrElectricityBillList = OBDal.getInstance().getSession().createCriteria(hql).list();
        return rchrElectricityBillList;
    }

    public RCPLElectricityBill getElectricityBillObject1(){
        String hql = "SELECT bill FROM RCPL_ElectricityBill AS bill "
                + " WHERE bill.employee.id = '" + getEmployeeId() + "' "
                + " AND bill.fromDate BETWEEN '" +PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(getEndDate())+ "'"
                + " AND bill.toDate BETWEEN '"+PayrollUtils.getInstance().getDBCompatiableDate(getStartDate())+"' AND '"+PayrollUtils.getInstance().getDBCompatiableDate(getEndDate())+"'";
        return  (RCPLElectricityBill)OBDal.getInstance().getSession().createCriteria(hql).list().get(0);
        //return rchrElectricityBillList;
    }
    public double getNoOfMeals(){
 	   String hql = "SELECT SUM(bill.totalmeals) FROM RCPL_MessBill AS bill "
 		       + " WHERE bill.employee.id = '" + getEmployeeId() + "' "
 		       + " AND bill.fromDate BETWEEN '" +getStartDate()+ "' AND '" +getEndDate()+ "'"
 		       + "AND bill.toDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'";
 	   return getFirstRecordValue(hql);
    }

    public double getDepartmentStoreRequisition() {
	     String hql = "SELECT SUM(store.amount) FROM RCPL_Deprtmtstorerequsition AS store "
	       + " WHERE store.employee.id = '" + getEmployeeId() + "' "
	        + " AND store.requisitondate BETWEEN '" +getStartDate() + "' AND '" +getEndDate()+ "'";
	           // + "AND store.toDate BETWEEN '"+getStartDate()+"' AND '"+getEndDate()+"'";
	     return getFirstRecordValue(hql);
	}

    public double getNoOfPresentsDailyDemo(){
    	String hql = "SELECT COUNT(dailyattnd.id) FROM RCHR_Dailyattenddemo AS dailyattnd "
    			+ "WHERE dailyattnd.employee.id = '" + getEmployeeId() + "' "
    			+ " AND dailyattnd.attendanceDate BETWEEN '" + getStartDate() + "' AND  '" + getEndDate() + "' "
    			+ " AND present = true AND overtime= false"
    			+ " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

    public double getNoOfDaysPresent() {
    	String hql = "SELECT COUNT(aline.id) from RCHR_Dailyattenddemo AS aline "
                + " WHERE aline.employee = '" + getEmployeeId() + "' "
                + " AND aline.present = true AND aline.overtime=false "
                + " AND aline.attendanceDate BETWEEN '" +  getStartDate()  + "' AND  '" + getEndDate() + "' "
                + " AND 1 = 1";
    	return getFirstRecordValue(hql);
    }

	public double getCheckDatesForLeaves(){
		String hql = "SELECT SUM(casual.noOfLeaves) from RCHR_LeaveRequisition AS casual "
                + " WHERE casual.employee.id = '" + getEmployeeId() + "' "
                + " AND casual.leaveStatus = 'Approved' "
                + " AND (('" + getStartDate()  + "' BETWEEN casual.fromDate AND casual.toDate ) OR ('" + getEndDate() + "'BETWEEN casual.fromDate AND casual.toDate))"
                + " AND 1 = 1";
		return getFirstRecordValue(hql);
	}

	public double getPendingAmount(){
    	 String hql = "SELECT SUM(loanLines.loanAmount-loanLines.paidAmount) FROM RCHR_Emp_Loanlines AS loanLines "
   			   + " JOIN loanLines.loanSchedule as loan"
   		       + " WHERE loan.employee.id = '" + getEmployeeId() + "' "
   		       + " AND loanLines.paid = false ";
    	return getFirstRecordValue(hql);
    }
}
