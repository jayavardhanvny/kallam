/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.payroll.ad_actionbutton;

import java.util.Date;

/**
 *
 * @author mateen
 */
public class NightShiftPojo {
    
    private String employeeId;
    private String shiftId;
    private Date startDate;
    private Date endDate;
    
    public NightShiftPojo(String employeeId, String shiftId, Date startDate, Date endDate) {
        this.employeeId = employeeId;
        this.shiftId = shiftId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
