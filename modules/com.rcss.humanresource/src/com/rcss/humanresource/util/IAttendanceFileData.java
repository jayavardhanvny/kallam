package com.rcss.humanresource.util;

/**
 *
 * @author S.A. Mateen
 */
public class IAttendanceFileData {
 
    private String org;
    private String attendanceDate;
    private String employee;
    private String shiftId;
    private String lateTimeinMins;
    private String isPresent;
    private String isLate;
    private String weekOff;
    private String isShiftChangeAbsent;
    private String overTimeShift;
    private String noWork;
    private String nightShift;

    public IAttendanceFileData(String org, String attendanceDate, String employee, String shiftId, String lateTimeinMins, String isPresent, String isLate, String weekOff, String isShiftChangeAbsent, String overTimeShift, String noWork, String nightShift) {
        this.org = org;
        this.attendanceDate = attendanceDate;
        this.employee = employee;
        this.shiftId = shiftId;
        this.lateTimeinMins = lateTimeinMins;
        this.isPresent = isPresent;
        this.isLate = isLate;
        this.weekOff = weekOff;
        this.isShiftChangeAbsent = isShiftChangeAbsent;
        this.overTimeShift = overTimeShift;
        this.noWork = noWork;
        this.nightShift = nightShift;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getLateTimeinMins() {
        return lateTimeinMins;
    }

    public void setLateTimeinMins(String lateTimeinMins) {
        this.lateTimeinMins = lateTimeinMins;
    }

    public String getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(String isPresent) {
        this.isPresent = isPresent;
    }

    public String getIsLate() {
        return isLate;
    }

    public void setIsLate(String isLate) {
        this.isLate = isLate;
    }

    public String getWeekOff() {
        return weekOff;
    }

    public void setWeekOff(String weekOff) {
        this.weekOff = weekOff;
    }

    public String getIsShiftChangeAbsent() {
        return isShiftChangeAbsent;
    }

    public void setIsShiftChangeAbsent(String isShiftChangeAbsent) {
        this.isShiftChangeAbsent = isShiftChangeAbsent;
    }

    public String getOverTimeShift() {
        return overTimeShift;
    }

    public void setOverTimeShift(String overTimeShift) {
        this.overTimeShift = overTimeShift;
    }

    public String getNoWork() {
        return noWork;
    }

    public void setNoWork(String noWork) {
        this.noWork = noWork;
    }

    public String getNightShift() {
        return nightShift;
    }

    public void setNightShift(String nightShift) {
        this.nightShift = nightShift;
    }

    @Override
    public String toString() {
        return "IAttendanceFileData{" + "org=" + org + ", attendanceDate=" + attendanceDate + ", employee=" + employee + ", shiftId=" + shiftId + ", lateTimeinMins=" + lateTimeinMins + ", isPresent=" + isPresent + ", isLate=" + isLate + ", weekOff=" + weekOff + ", isShiftChangeAbsent=" + isShiftChangeAbsent + ", overTimeShift=" + overTimeShift + ", noWork=" + noWork + ", nightShift=" + nightShift + '}';
    }


    
}
