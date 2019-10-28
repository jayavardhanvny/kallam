package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.*;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.ServletException;

import com.rcss.humanresource.util.DatabaseConnectionsUtil;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.HqlUtils;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import java.security.Timestamp;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
*
* @author Tirumal
*/
public class DailyAttendanceIntegration extends DalBaseProcess {
	protected Logger logger = Logger.getLogger("DailyAttendanceIntegration.java");
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
		Long startBeforeDoIt = System.currentTimeMillis();
		try{
			doIt();
		}catch (ParseException ex) {
			logger.info("This ParseException "+ex.getMessage());
		}catch (SQLException e) {
			logger.info("This SQLException "+e.getMessage());
		}catch (Exception e) {
			logger.info("This Exception "+e.getMessage());
		}


		Long endAfterWhile = System.currentTimeMillis();
		Long time = endAfterWhile - startBeforeDoIt ;

		long hour = TimeUnit.MILLISECONDS.toHours(time);
		long minute = TimeUnit.MILLISECONDS.toMinutes(time);

		long second = TimeUnit.MILLISECONDS.toSeconds(time);
		logger.info("Total execution Time in Seconds is " +second);
    }
	public static Connection PostgresCon() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/production", "postgres", "rcss@123");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}
    	public static Connection SqlCon(){
    		// TODO Auto-generated method stub
    		Connection con=null;
    		String connectionUrl = "jdbc:sqlserver://172.16.0.47:1433;" +
                    "databaseName=esslsmartoffice;user=sa;password=ksml@123";
    		try
    		{  
    			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    			String userName = "sa";
                String password = "kallam";
                String url = "jdbc:sqlserver://localhost:1433;databaseName=etimetracklite1;integratedSecurity=true";
                //con = DriverManager.getConnection(url, userName, password);
                con = DriverManager.getConnection(connectionUrl);
               
    			
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		return con;
    	}

    	 private  ResultSet getMSQLServerResultSet(Date strfromDate,Date strtoDate) throws SQLException{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			 String toDate=sdf.format(strtoDate);
			 String fromDate=sdf.format(strfromDate);
			 Connection sqlcon = SqlCon();
			 logger.info("connection is " +sqlcon);
			 Statement st = sqlcon.createStatement();
			 ResultSet rs = st.executeQuery("select convert(varchar(10),Id) as id, userid as stremployee, " +
					 "convert(date,attendancedate,102), ReportPunchRecords,Ct_Num as count,Direction as direction,DeviceName as devicename," +
					 //"EmpName as employeename from AttendanceLogs_RCSS where attendancedate between '"+fromDate+"' and '"+toDate+"' " +
					 "EmpName as employeename from AttendanceLogs_RCSS where attendancedate between '2019-10-08' and '2019-10-08' and userid ='10005'" +
					 "order by stremployee, attendancedate");
			 return rs;
		 }

	private void doIt() throws Exception {
		logger.info("doIt() method is starting.....!");
		//Connection postgrescon =  PostgresCon();
		//logger.info("connection is " +postgrescon);
		OBCriteria<RchrAttenddaysmaster> days = OBDal.getInstance().createCriteria(RchrAttenddaysmaster.class);
		int day = 0;
		for (RchrAttenddaysmaster master : days.list()) {
			day = master.getDailydayesfirst().intValue();
		}

		//postgrescon.setAutoCommit(false);
		//int setFlag = 0;
		Date strtoDate = new Date();
		Date strfromDate = new Date();
		Calendar caltemp = Calendar.getInstance();
		caltemp.setTime(strfromDate);
		caltemp.add(Calendar.DATE, -day);
		caltemp.set(Calendar.HOUR_OF_DAY, 0);
		caltemp.set(Calendar.MINUTE, 0);
		caltemp.set(Calendar.SECOND, 0);
		caltemp.set(Calendar.MILLISECOND, 0);
		strfromDate = caltemp.getTime();

		Calendar caltodate = Calendar.getInstance();
		caltodate.setTime(strtoDate);
		caltodate.set(Calendar.HOUR_OF_DAY, 0);
		caltodate.set(Calendar.MINUTE, 0);
		caltodate.set(Calendar.SECOND, 0);
		caltodate.set(Calendar.MILLISECOND, 0);
		strtoDate = caltodate.getTime();
		ResultSet rs = getMSQLServerResultSet(strfromDate, strtoDate);
		logger.info("Before While: From Date "+strfromDate +" To Date "+strtoDate);
		int insertedLines = 0;
		int updatedLines = 0;
		int emptyRowsUpdated = 0;
		int emptyRowsInserted = 0;

		EmployeeUtil employeeUtil = new EmployeeUtil();
		HashMap<String, RchrEmployee> rchrEmployeeHashMap = employeeUtil.getWorkingEmployeesList();
		Connection connection = PostgresCon();

		HashMap<String, SqlServerAttendancePojo> sqlServerAttendancePojoHashMap = new HashMap<String, SqlServerAttendancePojo>();
		Long longStart = System.currentTimeMillis();

		while (rs.next()) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			logger.info("Log 2");
			String attendanceId = rs.getString(1).toString();
			String employeeId = rs.getString(2).toString();
			String employeeName = rs.getString(8).toString();
			String stringAttendanceDate = rs.getString(3).toString();
			Date attendanceDate = simpleDateFormat.parse(rs.getString(3).toString());
			Long longCount = new Long(rs.getInt(5));
			String deviceName = rs.getString(7).toString();
			String direction = rs.getString(6).toString();
			String punchRecords = rs.getString(4).toString();

			if(employeeId!=null && employeeId!="" && !employeeId.toString().contains("Del")){
				SqlServerAttendancePojo sqlServerAttendancePojo = new SqlServerAttendancePojo();
				sqlServerAttendancePojo.setPunchRecords(punchRecords);
				sqlServerAttendancePojo.setDirection(direction);
				sqlServerAttendancePojo.setLongCount(longCount);
				sqlServerAttendancePojo.setEmployeeName(employeeName);
				sqlServerAttendancePojo.setEmployeeId(employeeId);
				sqlServerAttendancePojo.setDeviceName(deviceName);
				sqlServerAttendancePojo.setAttendanceId(attendanceId);
				sqlServerAttendancePojo.setAttendanceDate(attendanceDate);
				sqlServerAttendancePojo.setAttendanceDateString(stringAttendanceDate);
				sqlServerAttendancePojoHashMap.put(attendanceId,sqlServerAttendancePojo);
			}


/*

			if (!employeeId.contains("Del") && employeeId != null) {

				OBCriteria<RCHRDailyattend> rchrDailyattendOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattend.class);
				rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_ATTENDANCEDATE, attendanceDate));
				rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_EMPLOYEEID, employeeId));
				if (rchrDailyattendOBCriteria.list().size() == 1) {
					rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_ATTNDLOGSID, attendanceId));
					if (rchrDailyattendOBCriteria.list().size() > 0) {
						rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_COUNT, longCount));
						if (rchrDailyattendOBCriteria.list().size() > 0) {
							//doNothing();
						} else {
							//update();
							// Updating the Records by Checking the Date and employeeId
							for (RCHRDailyattend dlyAtnd2 : rchrDailyattendOBCriteria.list()) {
								dlyAtnd2.setErrorLog(punchRecords);
								dlyAtnd2.setCount(longCount);
								dlyAtnd2.setDevicename(deviceName);
								dlyAtnd2.setDirection(direction);
								dlyAtnd2.setEmployeeName(employeeName);
								dlyAtnd2.setAttndlogsid(attendanceId);
								dlyAtnd2.setReasonForLeave("");
								dlyAtnd2.setValidate(Boolean.FALSE);
								logger.info("Log 3");
							}
							emptyRowsUpdated++;
							//emptyRowsUpdated++;
						}

					} else {
						insertStatement(connection,employeeId, employeeName,stringAttendanceDate, longCount, punchRecords
								, direction, deviceName, attendanceId);

						logger.info("Log 5");
						insertedLines++;
					}
				} else if (rchrDailyattendOBCriteria.list().size() ==0){
					logger.info("Log 6");
					insertStatement(connection,employeeId, employeeName,stringAttendanceDate, longCount, punchRecords
							, direction, deviceName, attendanceId);
					//logger.info("Log 5");
					insertedLines++;
				}
			}
*/

		}

		Long longEnd = System.currentTimeMillis();
		Long totalTime = longEnd-longStart;
		logger.info("Time taken to add in Attendance POJO is "+TimeUnit.MILLISECONDS.toMinutes(totalTime));
		logger.info("size is Atttendance pojo is "+sqlServerAttendancePojoHashMap.size());
		AttendanceProcessRcss attendanceProcessRcss = new AttendanceProcessRcss();
		attendanceProcessRcss.execute(sqlServerAttendancePojoHashMap,caltemp,caltodate);
			// Inserting Empty Records...
			
			logger.info("Inserted " + insertedLines);
			logger.info("emptyRowsUpdated " + emptyRowsUpdated);
			logger.info("emptyRowsInserted " + emptyRowsInserted);
			logger.info("updatedLines " + updatedLines);
		} // End of doItmethod();



} // End Of Class
