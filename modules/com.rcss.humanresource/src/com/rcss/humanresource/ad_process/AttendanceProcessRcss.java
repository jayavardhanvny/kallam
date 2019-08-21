package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RCHRDailyattend;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.util.DatabaseConnectionsUtil;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.HqlUtils;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import org.apache.log4j.Logger;

public class AttendanceProcessRcss {
    protected Logger logger = Logger.getLogger(AttendanceProcessRcss.class);

    public void execute(HashMap attendanceProcessRcssHashMap,
                        Calendar caltemp,Calendar caltodate) throws SQLException{
        logger.info("Under Execute");
        int insertedLines = 0;
        int updatedLines = 0;
        int emptyRowsUpdated = 0;
        int emptyRowsInserted=0;
        if(!attendanceProcessRcssHashMap.isEmpty()) {
            //DatabaseConnectionsUtil databaseConnectionsUtil = new DatabaseConnectionsUtil();
            Connection connection = DatabaseConnectionsUtil.PostgresCon();

            Iterator<Set> setIterator = attendanceProcessRcssHashMap.keySet().iterator();

            while (setIterator.hasNext()) {
                //logger.info("In While ");
                SqlServerAttendancePojo sqlServerAttendancePojo = (SqlServerAttendancePojo) attendanceProcessRcssHashMap.get(setIterator.next());
                //logger.info("In While 2 "+sqlServerAttendancePojo.getAttendanceId());
                if (!sqlServerAttendancePojo.getEmployeeId().contains("Del") &&
                        sqlServerAttendancePojo.getEmployeeId() != null) {

                    OBCriteria<RCHRDailyattend> rchrDailyattendOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattend.class);
                    rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_ATTENDANCEDATE, sqlServerAttendancePojo.getAttendanceDate()));
                    rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_EMPLOYEEID, sqlServerAttendancePojo.getEmployeeId()));
			logger.info("log 1 "+ " Attndance date "+sqlServerAttendancePojo.getAttendanceDate()+" Employee Id "+sqlServerAttendancePojo.getEmployeeId());
                    if (rchrDailyattendOBCriteria.list().size() >0) {
                        logger.info("log 2 "+sqlServerAttendancePojo.getAttendanceId());
                        rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_ATTNDLOGSID,
                                sqlServerAttendancePojo.getAttendanceId()));
                        logger.info("log after attendance Id criteria "+rchrDailyattendOBCriteria.list().size());
                        if (rchrDailyattendOBCriteria.list().size() > 0) {
                            logger.info("log 3 ");
                            rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_COUNT, sqlServerAttendancePojo.getLongCount()));
                            if (rchrDailyattendOBCriteria.list().size() > 0) {
                                //doNothing();
                            } else {
                                //update();
                                // Updating the Records by Checking the Date and employeeId

                                    updateStatement(connection,  sqlServerAttendancePojo.getLongCount(),
                                            sqlServerAttendancePojo.getPunchRecords()
                                            , sqlServerAttendancePojo.getDirection(),
                                            sqlServerAttendancePojo.getDeviceName(),
                                            sqlServerAttendancePojo.getAttendanceId());

                                    logger.info("Log 4");



                                emptyRowsUpdated++;
                                //emptyRowsUpdated++;
                                logger.info("Log 5");
                            }
                        } else {
                            logger.info("Log 6");
                    updateStatement(connection,sqlServerAttendancePojo.getEmployeeId() ,sqlServerAttendancePojo.getEmployeeName()
        , sqlServerAttendancePojo.getAttendanceDateString(), sqlServerAttendancePojo.getLongCount(),
        sqlServerAttendancePojo.getPunchRecords()
        , sqlServerAttendancePojo.getDirection(), sqlServerAttendancePojo.getDeviceName(),
                            sqlServerAttendancePojo.getAttendanceId());
                            logger.info("Log 6 2nd ");

                            updatedLines++;
                        }
                    } else {
                        logger.info("Log 7");
                        insertStatement(connection,sqlServerAttendancePojo.getEmployeeId() ,sqlServerAttendancePojo.getEmployeeName()
                                , sqlServerAttendancePojo.getAttendanceDateString(), sqlServerAttendancePojo.getLongCount(),
                                sqlServerAttendancePojo.getPunchRecords()
                                , sqlServerAttendancePojo.getDirection(), sqlServerAttendancePojo.getDeviceName(), sqlServerAttendancePojo.getAttendanceId());

                        insertedLines++;
                    }


                } // End of While Loop...
            }
        }
        emptyRowsInserted = this.insertEmptyLines(caltemp,caltodate);
        logger.info("Inserted " +insertedLines);
        logger.info("emptyRowsUpdated Vary in LogCount" +emptyRowsUpdated);
        logger.info("emptyRowsInserted "+emptyRowsInserted);
        logger.info("updatedLines With date and " +updatedLines);
    }




    public void updateStatement(Connection connection, String employeeId,String employeeName,
                                String attendanceDate, Long longCount, String punchRecords
            , String direction, String deviceName, String attendanceId) throws SQLException{
        logger.info("before update");
        String stm = "UPDATE rchr_dailyattend " +
                " SET validate='N', description='', errorlog=?, count =?, direction = ?, devicename = ?, " +
                "employeename=?, attndlogsid=?, updated=now() " +
                " where stremployee=? and attdate=to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')";
        PreparedStatement preparedStatement =connection.prepareStatement(stm);
        preparedStatement.setString(1,punchRecords);
        preparedStatement.setLong(2,longCount);
        preparedStatement.setString(3,direction);
        preparedStatement.setString(4,deviceName);
        preparedStatement.setString(5,employeeName);
        preparedStatement.setString(6,attendanceId);
        preparedStatement.setString(7,employeeId);
        preparedStatement.setString(8,attendanceDate);
        preparedStatement.executeUpdate();
        logger.info("After update");

    }
    

    public void updateStatement(Connection connection, Long longCount, String punchRecords
            , String direction, String deviceName, String attendanceId) throws SQLException{
        logger.info("before update");
        String stm = "UPDATE rchr_dailyattend " +
                " SET validate='N', updated=now() ,description='', errorlog=?, count =?, direction = ?, devicename = ? " +
                " where attndlogsid=?";
        PreparedStatement preparedStatement =connection.prepareStatement(stm);
        preparedStatement.setString(1,punchRecords);
        preparedStatement.setLong(2,longCount);
        preparedStatement.setString(3,direction);
        preparedStatement.setString(4,deviceName);
        //preparedStatement.setString(5,employeeName);
        preparedStatement.setString(5,attendanceId);
        //preparedStatement.setString(7,employeeId);
        //preparedStatement.setString(8,attendanceDate);
        preparedStatement.executeUpdate();
        logger.info("After update");

    }

    // Inserting Empty Records...



    public int insertEmptyLines(Calendar caltemp,Calendar caltodate) {

        int emptyRowsInserted =0;

        while (caltemp.getTime().before(caltodate.getTime())) {
            HqlUtils hqlUtils = new HqlUtils();
            List<String> employeesList = hqlUtils.getEmployeesList(caltemp.getTime());
            EmployeeUtil employeeUtil = new EmployeeUtil();
            HashMap<String, RchrEmployee> rchrEmployeeHashMap1 = employeeUtil.getWorkingEmployeesList();
            logger.info("Size " + employeesList.size());
            for (String employeeId2 : employeesList) {
                logger.info("Log 10 " + employeeId2);
                //String[] strings = employeeId.split(",");
                //String punchNo =  strings[0];
                String employeeName2 = employeeId2;
                if (rchrEmployeeHashMap1.containsKey(employeeId2)) {
                    RchrEmployee employee = rchrEmployeeHashMap1.get(employeeId2);
                    employeeName2 = employee.getEmployeeName();
                }
                RCHRDailyattend rchrDailyattend1 = OBProvider.getInstance().get(RCHRDailyattend.class);
                rchrDailyattend1.setEmployeeId(employeeId2); // employeeId
                rchrDailyattend1.setAttendanceDate(caltemp.getTime());
                rchrDailyattend1.setEmployeeName(employeeName2);
                rchrDailyattend1.setReasonForLeave("No Punch");
                OBDal.getInstance().save(rchrDailyattend1);
                emptyRowsInserted++;
            }
            logger.info("count " + caltemp.getTime());
            caltemp.add(Calendar.DATE, 1);
        }


        return emptyRowsInserted;
    }
    public void insertStatement(Connection connection, String employeeId,String employeeName, String attendanceDate, Long longCount, String punchRecords
            , String direction, String deviceName, String attendanceId) throws SQLException {


        String stm = "INSERT INTO rchr_dailyattend(rchr_dailyattend_id, ad_client_id, ad_org_id, " +
                "createdby, updatedby, stremployee, attdate, errorlog, count,direction,devicename,employeename,attndlogsid) " +
                " VALUES(get_uuid(),?, ?, ?, ?, ?, to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS'), ?, ?,?,?,?,?) ";
        PreparedStatement preparedStatement =connection.prepareStatement(stm);
        preparedStatement.setString(1,"15E93AAB4F104C458EEA18F6672CF5DD");
        preparedStatement.setString(2,"256551BD83DF49DB80BCE5691149CA0B");
        preparedStatement.setString(3,"100");
        preparedStatement.setString(4,"100");
        preparedStatement.setString(5,employeeId);
        preparedStatement.setString(6,attendanceDate);
        preparedStatement.setString(7,punchRecords);
        preparedStatement.setLong(8,longCount);
        preparedStatement.setString(9,direction);
        preparedStatement.setString(10,deviceName);
        preparedStatement.setString(11,employeeName);
        preparedStatement.setString(12,attendanceId);
        preparedStatement.executeUpdate();
	logger.info("After Insert");
    }
}
