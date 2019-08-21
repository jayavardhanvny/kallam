package com.redcarpet.payroll.ad_actionbutton;

import java.util.Date;

public class PayrollEmployee {

    public String empuuid;
    public String empPayrollProcessId;
    public String payrollPeriodId;
    public String employeeId;
    public String employeeType;
    public Date startDate;
    public Date endDate;
    public double grossAmount;
    public double grossAmountLimit;
    public double payPerDay;
    public String payrollTemplateId;
    public String empSalSetupId;
    public double taPerYear;
    public double qadPerYear;
    public double basicAmount;
    public double serviceIncentiveAmount;
    public PayrollEmployee(String uuid, String empPayrollProcessId, String payrollPeriodId, String employeeId, String employeeType, Date startDate, Date endDate, double grossAmount,
                           double grossAmountLimit, double payPerDay, double basicAmount, String payrollTemplateId, String empSalSetupId, double taPerYear, double qadPerYear, double  serviceIncentive) {
        this.empuuid = uuid;
        this.empPayrollProcessId = empPayrollProcessId;
        this.payrollPeriodId = payrollPeriodId;
        this.employeeId = employeeId;
        this.employeeType = employeeType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grossAmount = grossAmount;
        this.grossAmountLimit = grossAmountLimit;
        this.payPerDay = payPerDay;
        this.payrollTemplateId = payrollTemplateId;
        this.empSalSetupId = empSalSetupId;
        this.taPerYear=taPerYear;
        this.qadPerYear=qadPerYear;
        this.basicAmount=basicAmount;
        this.serviceIncentiveAmount = serviceIncentive;
    }

    
    public String toString2() {
        return "PayrollEmployee{" + "empuuid=" + empuuid + ", empPayrollProcessId=" + empPayrollProcessId + ", payrollPeriodId=" + payrollPeriodId + ", employeeId=" + employeeId + ", employeeType=" + employeeType + ", startDate=" + startDate + ", endDate=" + endDate + ", grossAmount=" + grossAmount + ", grossAmountLimit=" + grossAmountLimit + ", payPerDay=" + payPerDay + ", payrollTemplateId=" + payrollTemplateId + ", empSalSetupId=" + empSalSetupId + '}';
    }

    
    
}

