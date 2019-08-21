package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.data.RCHR_Emp_Loanlines;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.rowset.serial.SerialException;
import com.rcss.humanresource.data.RchrAttdProcess;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrLossOfPay;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.dal.core.OBContext;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;

/**
 *
 * @author Tirumal 
 * @description Calculates loan slab and auto check paid and cumulative balances
 */
public class RCHR_Loan_Schedule extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	
        final String id = bundle.getParams().get("Rchr_Emp_Loan_ID").toString();
        RCHR_Emp_Loan loan = OBDal.getInstance().get(RCHR_Emp_Loan.class, id);
        if(loan.getRchrAttdProcess()==null){
        	throw new SerialException("Must Enter Payroll Period."); 
        }
        
       
        RchrAttdProcess payrollmonth = OBDal.getInstance().get(RchrAttdProcess.class, loan.getRchrAttdProcess().getId());
        Calendar cal = Calendar.getInstance();
        Date strDate = payrollmonth.getStartingDate();
        Date lastDate = payrollmonth.getEndingDate();
        long startday = loan.getLoanCategory().getFromDate();
        long endday = loan.getLoanCategory().getToDate();
        cal.setTime(loan.getRequisitiondate());
        int reqday = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(strDate);
        BigDecimal  totamtpermonth = new BigDecimal(0);// totamtpermonth.multiply(multiplicand)
        BigDecimal totadvance = new BigDecimal(0);
        Date finStrDate=new Date();
        Date finEndDate=new Date(); 
    	double totalamountpermonth = 0;
    	 double presentdays = new PayrollDBSessionUtil(loan.getEmployee().getId(),  strDate, lastDate).getNoOfPresentsDailyDemo();
    	 if(loan.isRemark().equals(false)){
        
        RchrYear year= payrollmonth.getRchrYear();
        
       
        
       /* if(payrollmonth.getPeriodNo().longValue()>=10 && payrollmonth.getPeriodNo().longValue()<=60){
            
            finStrDate=getstartAndEndDates(year,new BigDecimal(10));
            finEndDate=getstartAndEndDates(year,new BigDecimal(60));
            
            }else{
            	finStrDate=getstartAndEndDates(year,new BigDecimal(70));
                finEndDate=getstartAndEndDates(year,new BigDecimal(120));
         }*/
        
        
        if(payrollmonth.getPeriodNo().longValue()>=10 && payrollmonth.getPeriodNo().longValue()<=30){            
            finStrDate=getstartAndEndDates(year,new BigDecimal(10));
            finEndDate=getstartAndEndDates(year,new BigDecimal(30));            
         }else if(payrollmonth.getPeriodNo().longValue()>=40 && payrollmonth.getPeriodNo().longValue()<=60){
            	finStrDate=getstartAndEndDates(year,new BigDecimal(40));
                finEndDate=getstartAndEndDates(year,new BigDecimal(60));
         }else if(payrollmonth.getPeriodNo().longValue()>=70 && payrollmonth.getPeriodNo().longValue()<=90){
        	 finStrDate=getstartAndEndDates(year,new BigDecimal(70));
             finEndDate=getstartAndEndDates(year,new BigDecimal(90));
         }else{
         	finStrDate=getstartAndEndDates(year,new BigDecimal(100));
            finEndDate=getstartAndEndDates(year,new BigDecimal(120));
         }
        
        
        if(!(startday <= reqday && reqday<=endday)){
        	throw new SerialException(" Advance is considered on between "+startday+" and "+endday+" only..");
        	}
        
      
        if(presentdays<18){
         	throw new SerialException("Must Have Minimum 18 Physical attendance.");            	 
        }
        
/*       
        double messBill =new PayrollDBSessionUtil(loan.getEmployee().getId(), strDate, lastDate).getMessAmount();       	
       	if(messBill>0){
       		throw new SerialException(" Loan cant be processed if You have mess Bill");
       		}  */  
        double messBill =new PayrollDBSessionUtil(loan.getEmployee().getId(), strDate, lastDate).getMessAmount();   
        
        double previousLoan= new PayrollDBSessionUtil(loan.getEmployee().getId(), finStrDate, finEndDate).getCheckLoanAmountForFinacialyr();
        
         if(loan.getEmployee().isRoomAllotted()){
         if(loan.getEmployee().getRoom().isBachelorquarter()){
        	 if(messBill > 0){
                 if(loan.getLoanAmount().longValue()>500 ){
                        throw new SerialException(" EMPLOYEE staying in BQ and having food at mess, hence entitled For Only 500 Rs/- ");
                  }
             }else{
                 if(loan.getLoanAmount().longValue()>1000 ){
                     throw new SerialException(" DUE TO THE EMPLOYEE staying in BQ and not having food at Mess, hence entitled For Only 1000 Rs/- ");
                 }
        	 }
        	 
         }else{
        	 OBCriteria<RchrEmployee> emp = OBDal.getInstance().createCriteria(RchrEmployee.class);
             emp.add(Restrictions.eq(RchrEmployee.PROPERTY_ROOM,loan.getEmployee().getRoom()));
             emp.add(Restrictions.eq(RchrEmployee.PROPERTY_ACTIVE, Boolean.TRUE));
             for(RchrEmployee emplist : emp.list()){
            	 double thisMonthLoan= new PayrollDBSessionUtil(emplist.getId(), strDate, lastDate).getCheckLoanAmountForFinacialyr();
            	 
            	 if(thisMonthLoan>0){
                 throw new SerialException(" your one of the family member has loan for this month ");}
            		 
             }
             if(previousLoan>0){
                 throw new SerialException(" we can give only once for 3 months ");}
             if(loan.getLoanAmount().longValue()>1000 && emp.list().size()<2){
				 throw new SerialException(" Advance Eligible For Only 1000 Rs/-");
             
               }else if(loan.getLoanAmount().longValue()>2000 &&  emp.list().size()>1){
				 throw new SerialException(" Advance Eligible For Only2000 Rs/-");
             
               }
             
            }
         }
         
        double pendingLoan= new PayrollDBSessionUtil(loan.getEmployee().getId(), finStrDate, finEndDate).pendingLoanAMount();
         if(pendingLoan>0){
             throw new SerialException(" You have Pending Adavances.. ");}
         
          
    
/*         if(!((loan.getLoanAmount().longValue()>=0 && loan.getLoanAmount().longValue()<=2000) && loan.getTenureMonths().longValue()<=1))
         {
        	 throw new SerialException(" bellow 20000 Rs/- TenureMonths only 1 ");
         }else if(!((loan.getLoanAmount().longValue()>=0 && loan.getLoanAmount().longValue()<=5000) && loan.getTenureMonths().longValue()<=2))
         {
        	 throw new SerialException(" bellow 50000 Rs/- TenureMonths only 2 ");
         }else if(!((loan.getLoanAmount().longValue()>=5000 && loan.getLoanAmount().longValue()>=10000) && loan.getTenureMonths().longValue()<=3))
         {
        	 throw new SerialException(" Above 50000 To Bellow 10000 Rs/- TenureMonths only 3 ");
         }else{
        	 throw new SerialException(" Exceed the Amount or TenureMonths ");
         }
*/        
 
         
        
         
         
         
         RCHRSaladvance salAdvance = OBDal.getInstance().get(RCHRSaladvance.class, getSalaryId(loan.getLoanAmount()));
         String salAdvanceId = salAdvance.getId();
        // String employeename = salAdvance.getEmployee().getEmployeeName();
         BigDecimal maxlimit = salAdvance.getMaxyearlimit();
         loan.setRchrSaladvance(salAdvance);
         
         
         
         OBCriteria<RCHR_Emp_Loan> loanamountinmonth = OBDal.getInstance().createCriteria(RCHR_Emp_Loan.class);
         loanamountinmonth.add(Restrictions.eq(RCHR_Emp_Loan.PROPERTY_RCHRATTDPROCESS, loan.getRchrAttdProcess()));
         loanamountinmonth.add(Restrictions.eq(RCHR_Emp_Loan.PROPERTY_RCHRSALADVANCE, salAdvance));
         loanamountinmonth.add(Restrictions.eq(RCHR_Emp_Loan.PROPERTY_PROCESS, true));
         for(RCHR_Emp_Loan checkamount : loanamountinmonth.list()){
        	 totadvance = totadvance.add(checkamount.getLoanAmount());
         }

         
         if((totadvance.longValue()+loan.getLoanAmount().longValue())>maxlimit.longValue()){
        	 System.out.println("totadvance= "+totadvance+ "  loan.getLoanAmount()= "+loan.getLoanAmount()+" maxlimit.longValue()= "+maxlimit);
        	 throw new SerialException(salAdvance.getEmployee().getEmployeeName()+" Sanctioning Amount Month Limit is Over..");        	 
         }
         
         
         OBCriteria<RCHR_Emp_Loan> totalmonthamount = OBDal.getInstance().createCriteria(RCHR_Emp_Loan.class);
         totalmonthamount.add(Restrictions.eq(RCHR_Emp_Loan.PROPERTY_RCHRATTDPROCESS, loan.getRchrAttdProcess()));
         totalmonthamount.add(Restrictions.eq(RCHR_Emp_Loan.PROPERTY_PROCESS, true));
         for(RCHR_Emp_Loan checkamount : totalmonthamount.list()){
      	   totamtpermonth = totamtpermonth.add(checkamount.getLoanAmount());
         }
      	   
         
         if(totamtpermonth.longValue()+loan.getLoanAmount().longValue()>100000){
        	 System.out.println("totamtpermonth = "+totamtpermonth+"  "+loan.getLoanAmount()+"  ..");
        	 throw new SerialException("Total Sanctioning Amount is Reached to 1,00,000 Rs/-");        	 
         }
         
        
       
         
         
         /*BigDecimal currentloneamount = new BigDecimal(0);
         if(!(loan.getLoanAmount().longValue() < 15000 && loan.getTenureMonths().longValue() <= 3)){
        	 currentloneamount =((loan.getLoanAmount().multiply(new BigDecimal(1.5))).divide(new BigDecimal(100)));
         }*/
         
         
    //     BigDecimal currentloneamount =loan.getLoanAmount();
         
        }   
         
         
         
     BigDecimal lastCumulativeAmt = BigDecimal.ZERO;      
        if(loan.getTenureMonths().equals(new BigDecimal("0.00"))){
        	throw new SerialException("Please check tenure months..");
        }else{
        	
             
        	for (int i = 1; i <= loan.getTenureMonths().intValue(); i++) {
        		System.out.println("else condition");
        		RCHR_Emp_Loanlines line = OBProvider.getInstance().get(RCHR_Emp_Loanlines.class);
        		line.setOrganization(loan.getOrganization());
        		line.setLoanSchedule(loan);
        		line.setLineNo(Long.valueOf((i * 10) + ""));
        		line.setDueDate(cal.getTime());
        		
        		
        		
       	/*	
                if(!(loan.getLoanAmount().longValue() < 15000 && loan.getTenureMonths().longValue() <= 3)){
                	System.out.println(currentloneamount);
               	 currentloneamount =((currentloneamount.multiply(new BigDecimal(1.5))).divide(new BigDecimal(100)));
               	 
               	line.setLoanAmount(loan.getLoanAmount().divide(loan.getTenureMonths(), MathContext.DECIMAL32).add(currentloneamount));
               	currentloneamount=currentloneamount.subtract(loan.getLoanAmount().divide(loan.getTenureMonths(), MathContext.DECIMAL32));
               	System.out.println(loan.getLoanAmount().divide(loan.getTenureMonths()));
               	
                }else{
                	line.setLoanAmount(loan.getLoanAmount().divide(loan.getTenureMonths(), MathContext.DECIMAL32));
                }
        		*/
        		
        		line.setLoanAmount(loan.getLoanAmount().divide(loan.getTenureMonths(), MathContext.DECIMAL32));
        		line.setPaidAmount(BigDecimal.ZERO);
        		line.setPendingAmount(line.getLoanAmount().subtract(line.getPaidAmount()));
        		lastCumulativeAmt = line.getPendingAmount().add(lastCumulativeAmt);
        		line.setCumulativeAmount(lastCumulativeAmt);
        		line.setManual(false);
        		line.setPaid(false);
        		cal.add(Calendar.MONTH, +1);
        		OBDal.getInstance().save(line);
        	}
        	loan.setProcess(true);
        	loan.setProcessing(true);
        	loan.setNoofpresents(new BigDecimal(presentdays));
        	loan.setImage(loan.getEmployee().getImage());
        	lastCumulativeAmt = BigDecimal.ZERO;
        	OBDal.getInstance().flush();
        }
     }
    
    public Date getstartAndEndDates(RchrYear year, BigDecimal periodNo){
    	Date finDate = new Date();
    	OBCriteria<RchrAttdProcess> rchrAttend = OBDal.getInstance().createCriteria(RchrAttdProcess.class); 
        rchrAttend.add(Restrictions.eq(RchrAttdProcess.PROPERTY_RCHRYEAR, year));
        rchrAttend.add(Restrictions.eq(RchrAttdProcess.PROPERTY_PERIODNO, periodNo));
        for(RchrAttdProcess payrollMonth : rchrAttend.list()){
        	finDate= payrollMonth.getStartingDate();
        }
        return finDate;
    }
    
 
    
    public String getSalaryId(BigDecimal loneamount){
        String advanceId="";
 //       BigDecimal totamtpermonth = new BigDecimal(0);
    OBCriteria<RCHRSaladvance> advance = OBDal.getInstance().createCriteria(RCHRSaladvance.class);
    advance.add(Restrictions.le(RCHRSaladvance.PROPERTY_MONTHLIMITFROM, loneamount));
    advance.add(Restrictions.ge(RCHRSaladvance.PROPERTY_MONTHLIMITTO, loneamount));
    for(RCHRSaladvance setauthority : advance.list()){
       advanceId = setauthority.getId();
}
    return advanceId;
    }
    
}
