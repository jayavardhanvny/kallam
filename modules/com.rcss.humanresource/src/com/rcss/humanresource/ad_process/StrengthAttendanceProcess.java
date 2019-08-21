package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.*;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.timeutils.sam.TimeDiffUtil;
import javax.servlet.ServletException;
import java.text.ParseException;
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
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class StrengthAttendanceProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	String id = (String) bundle.getParams().get("Rchr_Attendstrength_ID").toString();
    	RchrAttendstrength attndStrngth = OBDal.getInstance().get(RchrAttendstrength.class, id);
    	//Date attndDate=attndStrngth.getReportdate();
    	try{
    	Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        Date attndDate= attndStrngth.getReportdate();
        System.out.println("date is .. "+attndDate);
        String strPresent= "Y"; 
        System.out.println("present is .. "+strPresent);
       // ResultSet rs = stmt.executeQuery("select attdate,present,stremployee from rchr_attend_temp where attdate='"+attndStrngth.getReportdate()+"'");
        String sqry="select stremployee from rchr_attend_temp where (attdate='"+attndStrngth.getReportdate()+"') and (present='"+strPresent+"')";
        ResultSet res = stmt.executeQuery(sqry);
        System.out.println("before while loop.. ");
        System.out.println("result set is.. "+res);
        while(res.next())
         {
        	System.out.println("start while loop.. ");
    
        	String employee=res.getString("stremployee");
        	
        	RchrStrngthlns line =  OBProvider.getInstance().get(RchrStrngthlns.class);
        	line.setOrganization(attndStrngth.getOrganization());
        	line.setRchrAttendstrength(attndStrngth);
        	String designId = attndStrngth.getEmployee().getId();
        	Statement stmt1 = conn.createStatement();
        	ResultSet rs1=stmt1.executeQuery("select rchr_employee_id from rchr_employee where documentno='"+employee+"' and supervisorname='"+designId+"'");
        	
        	System.out.println("employee result set.."+rs1);
        	RchrEmployee tmpdemo=new RchrEmployee();
        	while (rs1.next()){
        	String rchrEmployeeId=rs1.getString("rchr_employee_id");
        	tmpdemo = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);}
        
        	line.setEmployee(tmpdemo);
        
        	line.setAttendence(Boolean.TRUE);
        	OBDal.getInstance().save(line);
        	//OBDal.getInstance().getSession().flush();
        	
        	System.out.println("one line is saved.. ");
         
        }
        attndStrngth.setProcess(Boolean.TRUE);
        OBDal.getInstance().save(attndStrngth);
        System.out.println("end the process.. ");
    }//try
    	catch(Exception e)
		{
			e.printStackTrace();
		}

    
    }//doexcute
    }//main class