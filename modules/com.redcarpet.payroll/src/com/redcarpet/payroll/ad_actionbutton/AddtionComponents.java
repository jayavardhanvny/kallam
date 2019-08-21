package com.redcarpet.payroll.ad_actionbutton;

public class AddtionComponents {
    
    public String uuid;
    public String empPayrollUUID;
    public String payrollTemplateId;
    public String employeeId;
    public String payAdditionId;
    public String additionType;
    public double payValue;

    public AddtionComponents(String uuid, String empPayrollUUID, String payrollTemplateId, 
            String employeeId, String payAdditionId, String additionType, double payValue) {
        this.uuid = uuid;
        this.empPayrollUUID = empPayrollUUID;
        this.payrollTemplateId = payrollTemplateId;
        this.employeeId = employeeId;
        this.payAdditionId = payAdditionId;
        this.additionType = additionType;
        this.payValue = payValue;
    }    
}