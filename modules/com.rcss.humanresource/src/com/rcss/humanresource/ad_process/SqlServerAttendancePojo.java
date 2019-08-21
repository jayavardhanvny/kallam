package com.rcss.humanresource.ad_process;

import java.util.Date;

public class SqlServerAttendancePojo {
    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Long getLongCount() {
        return longCount;
    }

    public void setLongCount(Long longCount) {
        this.longCount = longCount;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPunchRecords() {
        return punchRecords;
    }

    public void setPunchRecords(String punchRecords) {
        this.punchRecords = punchRecords;
    }

    public String getAttendanceDateString() {
        return attendanceDateString;
    }

    public void setAttendanceDateString(String attendanceDateString) {
        this.attendanceDateString = attendanceDateString;
    }

    private String attendanceDateString;
    private String attendanceId;
    private String employeeId;
    private String employeeName;
    private Date attendanceDate;
    private Long longCount;
    private String deviceName ;
    private String direction;
    private String punchRecords;
}
