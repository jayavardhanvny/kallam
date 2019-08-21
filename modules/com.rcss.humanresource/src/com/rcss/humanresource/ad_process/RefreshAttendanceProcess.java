package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.production.data.RCPRShift;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.*;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.timeutils.sam.TimeDiffUtil;
import javax.servlet.ServletException;
import java.text.ParseException;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBDal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
//import org.openbravo.dal.service.OBDal;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
//import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
*
* @author Tirumal
*/

public class RefreshAttendanceProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

    	//System.out.println("in processss.....!!!");   	
    	//List<RCHRDailyattend> attndList = OBDal.getInstance().createCriteria(RCHRDailyattend.class).list();
    	Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        Date strtoDate= new Date();
		Date strfromDate= new Date();
		Calendar calfromdate = Calendar.getInstance();
		calfromdate.setTime(strfromDate);
		calfromdate.add(Calendar.DATE, -25);
		calfromdate.set(Calendar.HOUR_OF_DAY, 0);
		calfromdate.set(Calendar.MINUTE,0);
		calfromdate.set(Calendar.SECOND,0);
		calfromdate.set(Calendar.MILLISECOND,0);
	    strfromDate=calfromdate.getTime();
	    Calendar caltodate = Calendar.getInstance();   
	    caltodate.setTime(strtoDate);
	    caltodate.set(Calendar.HOUR_OF_DAY, 0);
	    caltodate.set(Calendar.MINUTE,0);
	    caltodate.set(Calendar.SECOND,0);
	    caltodate.set(Calendar.MILLISECOND,0);
	    strtoDate=caltodate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String toDate=sdf.format(strtoDate);
		String fromDate=sdf.format(strfromDate);
		 int flag=0;
        //stmt.executeUpdate("delete from rchr_dailyattenddemo where attdate between '"+fromDate+"' and '"+toDate+"'");
        
        flag=stmt.executeUpdate("delete FROM rchr_dailyattenddemo" +
         " WHERE attdate between '"+fromDate+"' and '"+toDate+"'"+
          "and rchr_dailyattenddemo_id NOT IN" +
	     " (SELECT MAX(dup.rchr_dailyattenddemo_id)" +
         " FROM   	rchr_dailyattenddemo As dup" +
         " GROUP BY dup.punchin, dup.punchout, dup.duration, dup.present, dup.stremployee," +
         " dup.isovertime, dup.isweeklyoff, dup.attdate, dup.rcpr_shift_id, dup.rchr_employee_id" +
         " having dup.attdate between '"+fromDate+"' and '"+toDate+"')");
        
    //System.out.println("total refreshed lines.. "+flag);
    
}//doexcute
 
}//main class