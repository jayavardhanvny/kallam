/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.payroll.util;

/**
 *
 * @author mateen
 */
interface PayrollDeductionsImpl {
	public double getLateAttendanceDisincentive();
    public double getSumofLICAmount();
    public double getSumofTokensAmount();
    public double getSumofPowerAmount();
    public double getCleaningAmount();
    public double getEmployeeWelfareFund();
    public double getTDSAmount();
    public double getLoans_AllowancesAmount();
    public double getDepartmentStoreRecovery();
    public double getGasRecovery();
    public double getEmployeeFine();
    public double getSecurityDeposit();
    public double getOtherDeductions();
    public double getSecurityDeposit(double presentdays);
}
