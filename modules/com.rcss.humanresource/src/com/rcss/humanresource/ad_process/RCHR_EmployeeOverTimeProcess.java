package com.rcss.humanresource.ad_process;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;

import com.rcss.humanresource.data.RCHRDailyattenddemo;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrOvertimeprocess;
import com.rcss.humanresource.data.RchrOvertimeprocessLine;
import com.redcarpet.production.data.RCPRShift;
import com.redcarpet.payroll.data.*;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;

public class RCHR_EmployeeOverTimeProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	final String id = bundle.getParams().get("Rchr_Overtimeprocess_ID").toString();
    	//System.out.println("id "+id);
     	//final String emp = bundle.getParams().get("Rchr_Employee_ID").toString();
    	RchrOvertimeprocess overTimeprocess = OBDal.getInstance().get(RchrOvertimeprocess.class, id);

    	OBCriteria<RchrOvertimeprocessLine> overtimeProcessLine = OBDal.getInstance().createCriteria(RchrOvertimeprocessLine.class);
    	overtimeProcessLine.add(Restrictions.eq(RchrOvertimeprocessLine.PROPERTY_RCHROVERTIMEPROCESS,overTimeprocess));
    	for(RchrOvertimeprocessLine overtime : overtimeProcessLine.list()){
    		
    		OBCriteria<RCHRDailyattenddemo> dailyAttdDemo = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
        	dailyAttdDemo.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE,overTimeprocess.getDate()));
        	dailyAttdDemo.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_EMPLOYEE,overtime.getEmployee()));
        	dailyAttdDemo.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_RCPRSHIFT,overtime.getRcprShift()));
        	

        	AttendanceUtil aUtil=new AttendanceUtil(overTimeprocess.getDate(), overTimeprocess.getDate(), overtime.getEmployee().getId());
        	double lineCount = aUtil.getCountinuiesShift();
        	for(RCHRDailyattenddemo dailydemo : dailyAttdDemo.list()){

        		if  (Boolean.TRUE.equals(dailydemo.isOvertime())){
        			overtime.setDuration(dailydemo.getDuration());
        			overtime.setPunchIn(dailydemo.getPunchIn());
        			overtime.setPunchOut(dailydemo.getPunchOut());
        			overtime.setOvertime(Boolean.TRUE);
				overtime.setPresent(Boolean.TRUE);
        			overtime.setRchrDailyattenddemo(dailydemo);
        			if(lineCount==2){
						overtime.setContinueshift(Boolean.TRUE);
					}
        			overtime.setWeeklyOff(dailydemo.isWeeklyOff());
        		}else if(Boolean.TRUE.equals(dailydemo.isPresent())) {
					overtime.setDuration(dailydemo.getDuration());
					overtime.setPunchIn(dailydemo.getPunchIn());
					overtime.setPunchOut(dailydemo.getPunchOut());
					overtime.setOvertime(Boolean.TRUE);
					overtime.setPresent(Boolean.TRUE);
					overtime.setRchrDailyattenddemo(dailydemo);
					if(lineCount==2){
						overtime.setContinueshift(Boolean.TRUE);
					}
					overtime.setWeeklyOff(dailydemo.isWeeklyOff());
					overtime.setManual(Boolean.TRUE);
				}else{
					overtime.setOvertime(Boolean.FALSE);
					overtime.setPresent(Boolean.FALSE);
				}


        	}
        	
    	}
    	
    	final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Processed to the Confirmation Successfully");
        bundle.setResult(msg);
    }
    
}
