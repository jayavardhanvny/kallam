package com.rcss.humanresource.ad_process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class AttendanceIntegrationSecondVersion extends DalBaseProcess {
	final private static Logger logger = LoggerFactory.getLogger(AttendanceIntegrationSecondVersion.class);
	@Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
		try{
			doIt();
		}catch(Exception e){
			 System.out.println("In Parent Exception Case.....!");
			///logger.debug("connection is Parent Logger {}",e.getMessage());
		}
    	
    }
	public void doIt(){
		 System.out.println("doIt() method Second Version is starting.....!");
		logger.info("Second Process Started");
		Long statTimeBeforeSecondProcess = System.currentTimeMillis();
		try {
			DailyAttendanceProcess dailyAttendanceProcess = new DailyAttendanceProcess();
			dailyAttendanceProcess.processDailyAttendance();
		}catch (Exception e){
			e.printStackTrace();
			logger.info("Stopped Second Process with Error "+e.getMessage());
		}
		Long endTimeAfterSecondProcess = System.currentTimeMillis();
		Long totalTimeForSecond = endTimeAfterSecondProcess-statTimeBeforeSecondProcess;
		//logger.info("Second Process Started");
		logger.info("Time to Complete Second Process is " + TimeUnit.MILLISECONDS.toMinutes(totalTimeForSecond));
	}
	
}