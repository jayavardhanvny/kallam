package com.redcarpet.payroll.util;

/**
 *
 * @author Vinay kumar.K
 *
 *
 *
 */
public class RoomPojo {
    private String roomPrimaryKey;
    private double totalEmployeesPresents;
    private double totalElectricityBill;
    private double noOfEmployeesInRoom;
    private double welfareFund;
    private double roomRent;
    private boolean isFirstPersonApplicable = Boolean.FALSE;
    private boolean completeAbsent = Boolean.FALSE;
    private boolean hasNewJoiners = Boolean.FALSE;
    public boolean isHasNewJoiners() {
        return hasNewJoiners;
    }

    public void setHasNewJoiners(boolean hasNewJoiners) {
        this.hasNewJoiners = hasNewJoiners;
    }


    public boolean isCompleteAbsent() {
        return completeAbsent;
    }

    public void setCompleteAbsent(boolean completeAbsent) {
        this.completeAbsent = completeAbsent;
    }


    public boolean isFirstPersonApplicable() {
        return isFirstPersonApplicable;
    }

    public void setFirstPersonApplicable(boolean firstPersonApplicable) {
        isFirstPersonApplicable = firstPersonApplicable;
    }

    public boolean isSecondPersonApplicable() {
        return isSecondPersonApplicable;
    }

    public void setSecondPersonApplicable(boolean secondPersonApplicable) {
        isSecondPersonApplicable = secondPersonApplicable;
    }

    private boolean isSecondPersonApplicable = false;
    public double getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(double roomRent) {
        this.roomRent = roomRent;
    }

    public double getWelfareFund() {
        return welfareFund;
    }

    public void setWelfareFund(double welfareFund) {
        this.welfareFund = welfareFund;
    }

    public double getNoOfEmployeesInRoom() {
        return noOfEmployeesInRoom;
    }

    public void setNoOfEmployeesInRoom(double noOfEmployeesInRoom) {
        this.noOfEmployeesInRoom = noOfEmployeesInRoom;
    }

    public String getRoomPrimaryKey() {
        return roomPrimaryKey;
    }

    public void setRoomPrimaryKey(String roomPrimaryKey) {
        this.roomPrimaryKey = roomPrimaryKey;
    }

    public RoomPojo(String roomPrimaryKey, double totalEmployeesPresents, double noOfEmployeesInRoom, double roomRent, double welfareFund, double totalElectricityBill ){
        this.roomPrimaryKey = roomPrimaryKey;
        this.totalEmployeesPresents = totalEmployeesPresents;
        this.noOfEmployeesInRoom = noOfEmployeesInRoom;
        this.roomRent = roomRent;
        this.welfareFund = welfareFund;
        this.totalElectricityBill = totalElectricityBill;
    }

    public RoomPojo(String roomPrimaryKey, double totalEmployeesPresents, double noOfEmployeesInRoom, double roomRent){
        this.roomPrimaryKey = roomPrimaryKey;
        this.totalEmployeesPresents = totalEmployeesPresents;
        this.noOfEmployeesInRoom = noOfEmployeesInRoom;
        this.roomRent = roomRent;
    }

    public double getTotalEmployeesPresents() {
        return totalEmployeesPresents;
    }

    public void setTotalEmployeesPresents(double totalEmployeesPresents) {
        this.totalEmployeesPresents = totalEmployeesPresents;
    }

    public double getTotalElectricityBill() {
        return totalElectricityBill;
    }

    public void setTotalElectricityBill(double totalElectricityBill) {
        this.totalElectricityBill = totalElectricityBill;
    }
}
