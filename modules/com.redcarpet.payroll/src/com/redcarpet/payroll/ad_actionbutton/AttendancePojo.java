package com.redcarpet.payroll.ad_actionbutton;
/**
 *
 * @author Vinay kumar.K
 *
 *
 *
 */
public class AttendancePojo {

public double leavesCountMonth;

	public double getLeavesCountMonth() {
		return leavesCountMonth;
	}
	public void setLeavesCountMonth(double leavesCountMonth) {
		this.leavesCountMonth = leavesCountMonth;
	}
	public double getAbsents() {
		return absents;
	}
	public void setAbsents(double absents) {
		this.absents = absents;
	}
	public double getNoOfCoffs() {
		return noOfCoffs;
	}
	public void setNoOfCoffs(double noOfCoffs) {
		this.noOfCoffs = noOfCoffs;
	}
	public double getNoOfLates() {
		return noOfLates;
	}
	public void setNoOfLates(double noOfLates) {
		this.noOfLates = noOfLates;
	}
	private double noOfDaysWeeklyOffWorked;
	public double getNoOfDaysWeeklyOffWorked() {
		return noOfDaysWeeklyOffWorked;
	}
	public void setNoOfDaysWeeklyOffWorked(double noOfDaysWeeklyOffWorked) {
		this.noOfDaysWeeklyOffWorked = noOfDaysWeeklyOffWorked;
	}

	private double noOfWeekOffDaysIfWeekOffApplicable;
	public double getNoOfWeekOffDaysIfWeekOffApplicable() {
		return noOfWeekOffDaysIfWeekOffApplicable;
	}
	public void setNoOfWeekOffDaysIfWeekOffApplicable(
			double noOfWeekOffDaysIfWeekOffApplicable) {
		this.noOfWeekOffDaysIfWeekOffApplicable = noOfWeekOffDaysIfWeekOffApplicable;
	}
	private double noOfWorkingDays;
	public double getNoOfWorkingDays() {
		return noOfWorkingDays;
	}
	public void setNoOfWorkingDays(double noOfWorkingDays) {
		this.noOfWorkingDays = noOfWorkingDays;
	}
	private double noOfDaysPresent;
	private double absents;
	private double noOfWeekOffs;
	public double getNoOfWeekOffs() {
		return noOfWeekOffs;
	}
	public void setNoOfWeekOffs(double noOfWeekOffs) {
		this.noOfWeekOffs = noOfWeekOffs;
	}
	private double noOfCoffs;
	private double noOfLates;
	private double noOfLeaves;


	public double getNoOfShiftChangeAbsent() {
		return noOfShiftChangeAbsent;
	}

	public void setNoOfShiftChangeAbsent(double noOfShiftChangeAbsent) {
		this.noOfShiftChangeAbsent = noOfShiftChangeAbsent;
	}

	private double noOfShiftChangeAbsent;
	public double getNoOfCLs() {
		return noOfCLs;
	}

	public void setNoOfCLs(double noOfCLs) {
		this.noOfCLs = noOfCLs;
	}

	public double getNoOfSLs() {
		return noOfSLs;
	}

	public void setNoOfSLs(double noOfSLs) {
		this.noOfSLs = noOfSLs;
	}

	private double noOfCLs;
	private double noOfSLs;

	public double getNoOfPhysicalDaysPresent() {
		return noOfPhysicalDaysPresent;
	}

	public void setNoOfPhysicalDaysPresent(double noOfPhysicalDaysPresent) {
		this.noOfPhysicalDaysPresent = noOfPhysicalDaysPresent;
	}

	private double noOfPhysicalDaysPresent;

	public double getNoOfLeaves() {
		return noOfLeaves;
	}
	public void setNoOfLeaves(double noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}
	public double getNoOfDaysPresent() {
		return noOfDaysPresent;
	}
	public void setNoOfDaysPresent(double noOfDaysPresent) {
		this.noOfDaysPresent = noOfDaysPresent;
	}

	private double noOfLOP;
	public double getNoOfLOP() {
		return noOfLOP;
	}
	public void setNoOfLOP(double noOfLOP) {
		this.noOfLOP = noOfLOP;
	}

}
