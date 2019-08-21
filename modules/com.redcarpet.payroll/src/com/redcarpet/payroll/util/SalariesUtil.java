package com.redcarpet.payroll.util;

public class SalariesUtil {
    public double getBasicAmount() {
        return basicAmount;
    }

    public void setBasicAmount(double basicAmount) {
        this.basicAmount = basicAmount;
    }

    public double getServiceIncentiveAmount() {
        return serviceIncentiveAmount;
    }

    public void setServiceIncentiveAmount(double serviceIncentiveAmount) {
        this.serviceIncentiveAmount = serviceIncentiveAmount;
    }

    private double basicAmount;
    private double serviceIncentiveAmount;

}
