package com.rcss.humanresource.ad_callouts;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import javax.servlet.ServletException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.BooleanList;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.financialmgmt.calendar.Period;

import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollUtils;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.data.*;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPL_EmpPayHead;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_PayDeducitionsHead;
import com.redcarpet.payroll.data.RCPL_PayrollIncentiveLines;
import com.redcarpet.payroll.data.RCPL_PayrollTemplateLines;
import com.redcarpet.payroll.data.RcplEmpfine;

public class Employeesetlinescallout extends SimpleCallout{
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	 String strPaydeductionId = info.getStringParameter("inprcplPaydeducitionsheadId", null);
    	 String strSettleId = info.getStringParameter("inprchrEmployeesettlementId", null);  
    	 
    	 RCPL_PayDeducitionsHead deductionId = OBDal.getInstance().get(RCPL_PayDeducitionsHead.class, strPaydeductionId);
    	 
    	 RCHR_Employeesettlement settlement = OBDal.getInstance().get(RCHR_Employeesettlement.class, strSettleId);
    	 
    	 String catogary = deductionId.getCategory();
    	 
    	 System.out.println("strSettleId  = "+strSettleId);
    	 
    	 Date settledate = settlement.getSettlementdate();
    	 
    	 System.out.println("settledate = "+settledate);
    	 
    	 RchrEmployee employeeId = settlement.getEmployee();
    	 
    	 /*
    	          //*********************************************************** 
    	          // By Vinay 
    	          Calendar headercal= Calendar.getInstance();
    	          // Actual Present Settlement data... 
    	          headercal.setTime(settledate);
    	          
    	          
    	          // Getting the Previous months date and adding -1 to present date..
    	          headercal.add(Calendar.MONTH, -1);
    	          
    	          // Setting the starting Date of previous month 
    	          headercal.set(Calendar.DATE, headercal.getActualMinimum(Calendar.DATE));
    	          
    	          // Previous Month Start date 
    	          Date startDate=headercal.getTime();
    	          
    	         // Setting the Ending Date of previous month
    	          headercal.set(Calendar.DATE, headercal.getActualMaximum(Calendar.DATE));
    	          // Getting the Previous months date and adding -1 to present date..
    	          Date lastmonthenddate = headercal.getTime();
    	          
    	          
    	          
    	          double checkMnth = aUtil.getCheckPayrollmonth(startDate, lastmonthenddate);
    	  	 	   System.out.println("checkMnth "+checkMnth);
    	  	 	   
    	  	 	    if(checkMnth==0){
    	  	 	    	Calendar headercal1= Calendar.getInstance();
    	  	 	    	headercal1.setTime(settledate);
    	  	 	    	headercal1.set(Calendar.DATE, headercal1.getActualMinimum(Calendar.DATE));
    	  	 	    	
    	  	 	    	startDate = headercal1.getTime();
    	  	 	    	
    	  	 	    	System.out.println("startDate in if condition "+startDate);
    	  	 	    }   
    	          
    	          
    	          //********************************
    	          */
    	          
    	  	 	    
	        Calendar headercal= Calendar.getInstance();
	        // Actual Present Settlement data... 
	        headercal.setTime(settledate);
	        
	        
	        // Getting the Previous months date and adding -1 to present date..
	        headercal.add(Calendar.MONTH, -1);
	        
	        // Setting the starting Date of previous month 
	        headercal.set(Calendar.DATE, headercal.getActualMinimum(Calendar.DATE));
	        
	        // Previous Month Start date 
	        Date startDate=headercal.getTime();
	        
	       // Setting the Ending Date of previous month
	        headercal.set(Calendar.DATE, headercal.getActualMaximum(Calendar.DATE));
	        // Getting the Previous months date and adding -1 to present date..
	        Date lastmonthenddate = headercal.getTime();
	        
	    	//headercal.set(Calendar.MONTH,settledate.getMonth());
	    	
	    	//headercal.set(Calendar.DATE, headercal.getActualMinimum(Calendar.DATE));
	    	
	    	//Date startDate=headercal.getTime();
	    	
	    	
	    	
	    	//System.out.println("startDate = "+startDate);
	    	
	    	  
	    	// headercal.add(Calendar.MONTH, -1);
	    	 
	    	 
	 	    
	 	   
	 	    
	 	   AttendanceUtil aUtil=new AttendanceUtil(startDate, lastmonthenddate, employeeId.getId());
	 	   
	 	    
	 	    //Date lastmonthenddate = headercal.getTime();
	 	    
	 	    double checkMnth = aUtil.getCheckPayrollmonth(startDate, lastmonthenddate);
	 	   System.out.println("checkMnth "+checkMnth);
	 	   
	 	    if(checkMnth==0){
	 	    	Calendar headercal1= Calendar.getInstance();
	 	    	headercal1.setTime(settledate);
	 	    	headercal1.set(Calendar.DATE, headercal1.getActualMinimum(Calendar.DATE));
	 	    	
	 	    	startDate = headercal1.getTime();
	 	    	
	 	    	System.out.println("startDate in if condition "+startDate);
	 	    }   
	 	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 	   String strStartdate = sdf.format(startDate);
	 	   String strEnddate = sdf.format(settledate);
	 	   
	 	 	   System.out.println("getAmount = "+getAmount(catogary, employeeId, PayrollUtils.getInstance().getDBCompatiableDate(startDate) , PayrollUtils.getInstance().getDBCompatiableDate(settledate)));
	 	   
	    	info.addResult("inpamount", new BigDecimal(getAmount(catogary, employeeId, strStartdate, strEnddate )));
    }        
    
    public double getAmount(String catogary, RchrEmployee EmployeeId, String startDate, String settledate){
    	double amount = 0;
    	String hql = "";
    	String sql = "";
    	Connection conn = null;
    	Statement stmt = null;
    	try{
    		conn = OBDal.getInstance().getConnection();
            stmt = conn.createStatement();
    	}catch (Exception e) {
            e.printStackTrace();
        }
    	
    	/*try {
          
        	
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            sql ="select rcmr_meter_id from rchr_room where (rchr_room_id='"+strRoomId+"') and (AD_Org_ID='"+strOrgId+"')";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
            	 
                 meterno = rs.getString(1);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    	
    	
    	
    	
    	if(catogary.equalsIgnoreCase("Employee Fine"))
    		{
    		sql="select sum(fineamount) from RCPL_EmpFine"
    				+" where rchr_employee_id='"+EmployeeId.getId()+"'"
    	    		+" and finedate between '"+startDate+"' and '"+settledate+"'";
    	    		/*
    			hql = "SELECT SUM(fine.fineamount) FROM RCPL_EmpFine as fine WHERE fine.employee.id= '" + EmployeeId.getId() +"'"
    					+ "AND fine.finedate BETWEEN '"+startDate+"' AND '"+settledate+"'";*/
    			
    		}
    	if(catogary.equalsIgnoreCase("Mess Bill"))
    		{
    		sql="select sum(sum) from RCPL_MessBill"
    				+" where rchr_employee_id='"+EmployeeId.getId()+"'"
    	    		+" and fromdate between '"+startDate+"' and '"+settledate+"'"
    	    		+" and todate between '"+startDate+"' and '"+settledate+"'";
    		/*
    			hql = "SELECT SUM(bill.sum) FROM RCPL_MessBill AS bill  WHERE bill.employee.id = '" +  EmployeeId.getId() + "' "
    					+ " AND bill.fromDate BETWEEN '" +startDate + "' AND '" +settledate+ "'"
    					+ "AND bill.toDate BETWEEN '"+startDate+"' AND '"+settledate+"'";
    			System.out.println("hql "+hql);*/
    			
    		}
    	if(catogary.equalsIgnoreCase("Store Recovery"))
		{
    		
    		sql="select sum(recoveramount) from RCPL_DepartmentStore"
    				+" where rchr_employee_id='"+EmployeeId.getId()+"'"
    	    		+" and fromdate between '"+startDate+"' and '"+settledate+"'"
    	    		+" and todate between '"+startDate+"' and '"+settledate+"'";
    		
    		/*hql = "SELECT SUM(store.recoveramount) FROM RCPL_DepartmentStore AS store " 
        			+ " WHERE store.employee.id = '" + EmployeeId.getId() + "' "
        			 + " AND store.fromDate BETWEEN '" +startDate + "' AND '" +settledate+ "'"
       		       + "AND store.toDate BETWEEN '"+startDate+"' AND '"+settledate+"'";
    		System.out.println("hql "+hql);*/
		}
    	if(catogary.equalsIgnoreCase("Gas"))
		{
			sql="select sum(lines.loanamount) from RCHR_Gas_Advancelines lines,rchr_gas_advance head"
    		+" where head.rchr_gas_advance_id=head.rchr_gas_advance_id"
    		+" and lines.duedate between '"+startDate+"' and '"+settledate+"'"
    		+" and head.rchr_employee_id='"+EmployeeId.getId()+"'"
    		+" and lines.paid='N'";
			
    		/*hql = "SELECT loanline "
                    + "  FROM RCHR_Gas_Advancelines as loanline "
                    + "  JOIN loanline.loanSchedule as loan "
                    + "  WHERE loan.employee.id = '" + EmployeeId.getId() + "'"
                    + "  AND loanline.paid = false"
                    + "  AND loanline.dueDate BETWEEN '" + startDate + "' AND '" + settledate + "')";*/
		}
    	if(catogary.equalsIgnoreCase("Loans and Advances"))
		{
    		
    		sql="select sum(lines.loanamount) from  rchr_emp_loanlines lines, rchr_emp_loan loan"
    		+" where lines.rchr_emp_loan_id=loan.rchr_emp_loan_id"
    		+" and duedate between '"+startDate+"' and '"+settledate+"'"
    		+" and loan.rchr_employee_id='"+EmployeeId.getId()+"'"
    		+" and lines.paid='N'";
    		/*hql = "SELECT loanline "
                    + "  FROM RCHR_Emp_Loanlines as loanline "
                    + "  JOIN loanline.loanSchedule as loan "
                    + "  WHERE loan.employee.id = '" + EmployeeId.getId() + "'"
                    + "  AND loanline.paid = false"
                    + "  AND loanline.dueDate BETWEEN '" + startDate + "' AND '" + settledate + "')";*/
    		
		}
    	
    	if(catogary.equalsIgnoreCase("Rent Bill"))
		{
			sql="select sum(lines.rentamount) from rchr_roomrent head, RCHR_RoomRentLines lines"
    		+" where head.rchr_roomrent_id=lines.rchr_roomrent_id"
    		+" and head.fromdate between '"+startDate+"' and '"+settledate+"'"
    		+" and head.todate between '"+startDate+"' and '"+settledate+"'"
    		+" and lines.rchr_employee_id='"+EmployeeId.getId()+"'";
    		
			
    		/*hql = "SELECT SUM(roomrentLines.rentamount) FROM RCHR_RoomRentLines AS roomrentLines " 
    	 			   + " JOIN roomrentLines.rchrRoomrent as roomrent"
    	 		       + " WHERE roomrentLines.employee.id = '" + EmployeeId.getId() + "' "
    	 		       + " AND roomrent.fromDate BETWEEN '" +startDate+ "' AND '" +settledate+ "'"
    	 		       + "AND roomrent.toDate BETWEEN '"+startDate+"' AND '"+settledate+"'";
    		System.out.println("hql "+hql);*/
		}
    	
    	if(catogary.equalsIgnoreCase("Electricity Bill"))
		{
			sql="SELECT SUM(bill.amount) FROM RCPL_ElectricityBill AS bill " 
 		       + " WHERE bill.employee.id = '" + EmployeeId.getId() + "' "
 		       + " AND bill.fromDate BETWEEN '" +startDate+ "' AND '" +settledate+ "'"
 		       + "AND bill.toDate BETWEEN '"+startDate+"' AND '"+settledate+"'";
    		
			
    		/*hql = "SELECT SUM(roomrentLines.rentamount) FROM RCHR_RoomRentLines AS roomrentLines " 
    	 			   + " JOIN roomrentLines.rchrRoomrent as roomrent"
    	 		       + " WHERE roomrentLines.employee.id = '" + EmployeeId.getId() + "' "
    	 		       + " AND roomrent.fromDate BETWEEN '" +startDate+ "' AND '" +settledate+ "'"
    	 		       + "AND roomrent.toDate BETWEEN '"+startDate+"' AND '"+settledate+"'";
    		System.out.println("hql "+hql);*/
		}
    	
    	try{
    		ResultSet rs = stmt.executeQuery(sql);
    		if(!rs.wasNull()){
    			while (rs.next()) {
                	System.out.println("rs.getInt(1) "+rs.getInt(1));
                     amount = rs.getInt(1);
                     System.out.println("amount "+amount);
                }
    		}
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	
    	
    	
    	/*Session session = OBDal.getInstance().getSession();
         List li = session.createQuery(hql).list();
         if (li.size() <= 0 || li.isEmpty()) {
        	 amount = 0;
         }else if (li.get(0) == null || li.get(0).toString() == null) {
        	 amount = 0;
         }else{
        	 amount= Double.valueOf(li.get(0).toString());
        }*/
         return amount;
    }
}
    
    