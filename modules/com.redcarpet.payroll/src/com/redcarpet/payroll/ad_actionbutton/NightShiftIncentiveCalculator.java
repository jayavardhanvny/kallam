/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.payroll.ad_actionbutton;

import com.redcarpet.payroll.util.PayrollUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.openbravo.dal.service.OBDal;

/**
 *
 * @author S.A. Mateen
 */
public class NightShiftIncentiveCalculator extends NightShiftPojo {

    private String varSetId;
    
    public NightShiftIncentiveCalculator(String empId, String shiftId, Date start, Date end, String varSetId) {
        super(empId, shiftId, start, end);
        this.varSetId = varSetId;
    }

    private double getNoOfDaysNightShift() {//aline.nightShift = true
    	
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + getEmployeeId() + "' "
                + " AND aline.shift.id = '" + getShiftId() + "' "
                + " AND 1=1 AND aline.present= true AND aline.overtime = false " //
                + " AND att.fromdate BETWEEN '" + PayrollUtils.getInstance().getDBCompatiableDate(getStartDate()) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(getEndDate()) + "' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li == null) {
            return 0;
        } else if (li.isEmpty() || li.size() <= 0) {
            return 0;
        } else {
            return Double.valueOf(li.get(0).toString());
        }
    }

    private double getPayPerShift() {//vs.isNightShiftIncentive='Y'
        String hql = "SELECT vsl.pay FROM RCPL_VariableLine vsl "
                + " JOIN vsl.variableSet vs  "
                + " WHERE  1=1 "
                + " AND vsl.shift.id = '" + getShiftId() + "' "
                + " AND vs.id = '"+this.varSetId+"' "
                + " ORDER BY vsl.lineNo ASC LIMIT 1 ";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li == null) {
            return 0;
        } else if (li.isEmpty() || li.size() <= 0) {
            return 0;
        } else {
            return Double.valueOf(li.get(0).toString());
        }
    }

    
    private double getPayPerShift(boolean test) {//
        String hql = "SELECT vsl.pay FROM RCPL_VariableLine vsl "
                + " JOIN vsl.variableSet vs  "
                + " WHERE  vs.isNightShiftIncentive='Y' "
                + " AND vsl.shift.id = '" + getShiftId() + "' "
                + " AND vs.id = '"+this.varSetId+"' "
                + " ORDER BY vsl.lineNo ASC LIMIT 1 ";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li == null) {
            return 0;
        } else if (li.isEmpty() || li.size() <= 0) {
            return 0;
        } else {
            return Double.valueOf(li.get(0).toString());
        }
    }

    public double getSumOfShiftPay() {
        return (this.getNoOfDaysNightShift() * this.getPayPerShift());
    }

    public double getSumOfShiftPay(boolean isfullnight) {
        return (this.getNoOfDaysNightShift() * this.getPayPerShift(isfullnight));
    }
}
