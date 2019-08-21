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
//import org.openbravo.erpCommon.ad_callouts.SimpleCallout.CalloutInfo;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollUtils;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPL_EmpPayHead;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_PayrollIncentiveLines;
import com.redcarpet.payroll.data.RCPL_PayrollTemplateLines;

public class Employeesetcallout extends SimpleCallout{
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
    	 String strSettleId = info.getStringParameter("inprchrEemployeesettlementId", null);
    	 String strSettlementdate1 = info.getStringParameter("inpsettlementdate", null);
        String employeeId = info.getStringParameter("inprchrEmployeeId", null);  
        Date presentDate=new Date();
        RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, employeeId); 
        
        try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			 presentDate = sdf.parse(strSettlementdate1);
        }
        catch (ParseException e) {
           e.printStackTrace();
       }
	        Calendar headercal=Calendar.getInstance();
	    	headercal.set(Calendar.MONTH,presentDate.getMonth());
	    	headercal.set(Calendar.DAY_OF_MONTH, headercal.getActualMinimum(Calendar.DATE));
	    	Date startDate=headercal.getTime();
	    AttendanceUtil aUtil=new AttendanceUtil(presentDate, presentDate, employee.getId());
	    double days=30;
	    double presentdays=aUtil.getPresentDaysOfSettlement();
	    if(employee.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){	
	    	
        info.addResult("inppresentdays", presentdays);
        info.addResult("inpperdaysalary", new BigDecimal(getPerDaySalaryOperator(employee)));
        info.addResult("inpproductionincnetive", new BigDecimal(getInsentivesOperator(employee, startDate, presentDate))); 
	    }else{
	    info.addResult("inppresentdays", presentdays);
	    info.addResult("inpperdaysalary", new BigDecimal(getPerDaySalaryStaff(employee, days)));
	    info.addResult("inpproductionincnetive", new BigDecimal(getInsentivesOfStaff(employee, days, presentdays))); 
	    	
	    }
    }    
    
    public double getPerDaySalaryOperator(RchrEmployee employee){
    	
        double perdaysal=0;
       
        String hql = " SELECT ess FROM RCPL_EmpSalSetup ess "
                + " WHERE ess.employee.id='" + employee.getId() + "' ";
              //  + " AND ess.active=true";
        List<RCPL_EmpSalSetup> empList = OBDal.getInstance().getSession().createQuery(hql).list();
        for(RCPL_EmpSalSetup setUp : empList){
        	if(setUp.isNotdesig()){
        		perdaysal=setUp.getServiceince().doubleValue()+setUp.getPerdaybasic().doubleValue();
        	}else{
        		RchrDesignation desig=OBDal.getInstance().get(RchrDesignation.class,employee.getDesignation().getId());
                OBCriteria<RCHRDesigBasic> desigbasic=OBDal.getInstance().createCriteria(RCHRDesigBasic.class);
                desigbasic.add(Restrictions.eq(RCHRDesigBasic.PROPERTY_RCHRSALARYSTRUCTURE,desig.getRchrSalarystructure()));
                desigbasic.add(Restrictions.le(RCHRDesigBasic.PROPERTY_FROMDAYS,employee.getPreattddays()));
               	desigbasic.add(Restrictions.ge(RCHRDesigBasic.PROPERTY_TODAYS,employee.getPreattddays()));
                for(RCHRDesigBasic basic : desigbasic.list()){
                	perdaysal=basic.getBasicamount().doubleValue()+basic.getServiceincentive().doubleValue(); 
                	//System.out.println("basic oprt employee"+basic.getBasicamount().doubleValue());
                }     		
        	}
        }
        //System.out.println("perdaySal of oprt employee"+perdaysal);
        return perdaysal;
    }
    
    // staff per day salries
    public double getPerDaySalaryStaff(RchrEmployee employee, double days){
    	
        double perdaysal=0;
        String hql = " SELECT ess FROM RCPL_EmpSalSetup ess "
                + " WHERE ess.employee.id='" + employee.getId() + "' ";
        List<RCPL_EmpSalSetup> empList = OBDal.getInstance().getSession().createQuery(hql).list();
        for(RCPL_EmpSalSetup setUp : empList){
        	perdaysal=setUp.getGrossAmtPerYear().doubleValue()/days;
        	
        }
        System.out.println("perdaySal of staff employee"+perdaysal);
        return perdaysal;
     }
   public double getInsentivesOfStaff(RchrEmployee employee, double days, double presetDays){
    	
	   double prepIncv=0;
	   String hql = " SELECT ess FROM RCPL_EmpSalSetup ess "
               + " WHERE ess.employee.id='" + employee.getId() + "' ";
       List<RCPL_EmpSalSetup> empList = OBDal.getInstance().getSession().createQuery(hql).list();
       for(RCPL_EmpSalSetup setUp : empList){
        	double perdayIncv=setUp.getProdincentive().doubleValue()/days;
        	prepIncv=perdayIncv*presetDays;
        }
        return prepIncv;
     }
    
    public double getInsentivesOperator(RchrEmployee emp, Date startDate, Date endDate){
    	double incentive=0;
    	double loomIncv=0;
    	double prepIncv=0;
    	String hql = "SELECT SUM(bill.amount) FROM RCPL_ProdIncentive AS bill " 
  		       + " WHERE bill.employee.id = '" + emp.getId()+ "' "
  		       + " AND bill.date BETWEEN '" +PayrollUtils.getInstance().getDBCompatiableDate(startDate)+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(endDate)+ "'";	
    	
    	 Session session = OBDal.getInstance().getSession();
         List li = session.createQuery(hql).list();
         if (li.size() <= 0 || li.isEmpty()) {
        	 loomIncv = 0;
         }else if (li.get(0) == null || li.get(0).toString() == null) {
        	 loomIncv = 0;
         }else{
        loomIncv= Double.valueOf(li.get(0).toString());
        }
        String hqlPrep = "SELECT SUM(prep.amount) FROM RCHR_Preparatprodincntive AS prep " 
   		       + " WHERE prep.employee.id = '" + emp.getId()+ "' "
   		       + " AND prep.date BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(startDate)+ "' AND '" + PayrollUtils.getInstance().getDBCompatiableDate(endDate)+ "'";	
        List prepli = session.createQuery(hqlPrep).list();
        if (prepli.size() <= 0 || prepli.isEmpty()) {
        	prepIncv = 0;
        }else if (prepli.get(0) == null || prepli.get(0).toString() == null) {
        	prepIncv = 0;
        }else{
        	prepIncv= Double.valueOf(prepli.get(0).toString());
        }
         incentive = loomIncv + prepIncv;
        return incentive;
    }
}
