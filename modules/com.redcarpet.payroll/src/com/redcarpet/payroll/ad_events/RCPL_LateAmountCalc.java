package com.redcarpet.payroll.ad_events;

import com.rcss.humanresource.data.RchrAttendLine;
import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.production.data.RCPRShift;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBDal;

import java.util.Date;;

public class RCPL_LateAmountCalc {

    private String empId;
    private String shiftId;
    private Date sdate;
    private Date edate;
    
    public RCPL_LateAmountCalc(String empId, String shiftId, Date sdate, Date edate) {
        this.empId = empId;
        this.shiftId = shiftId;
        this.sdate = sdate;
        this.edate = edate;
    }

    private double getPerDaySal() {
        RCPL_EmpSalSetup ss = (RCPL_EmpSalSetup) OBDal.getInstance().createQuery(RCPL_EmpSalSetup.class, " as ss where ss.employee.id = '" + empId + "' ").list().get(0);
        return ss.getPerDaySalary().doubleValue();
    }

    private int getShiftInMins() {
        return OBDal.getInstance().get(RCPRShift.class, shiftId).getShiftInMins().intValue();
    }

    private int getLateTime() {

        double sum = 0;
        RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, empId);
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
        List<RchrAttendLine> attendance = (List<RchrAttendLine>) OBDal.getInstance().createCriteria(RchrAttendLine.class)
                .add(Restrictions.eq(RchrAttendLine.PROPERTY_RCHREMPLOYEE, employee))
                .add(Restrictions.eq(RchrAttendLine.PROPERTY_SHIFT, shift))
                .add(Restrictions.eq(RchrAttendLine.PROPERTY_LATE, true)).list();
        for (RchrAttendLine at : attendance) {
        	Date adate=at.getRchrAttendance().getFromdate();
        	if(((((adate.before(edate))&&(adate.after(sdate)))||(adate.equals(sdate)) ||(adate.equals(edate)))))
        	{
            sum += at.getLatetime().doubleValue();
        	} else
        	{
        		//System.out.println("no late in given period");
        	}
        }
        return new Double(sum).intValue();
    }

    public double getLateAmount() {
    	int latetime=getLateTime();
    	if(latetime != 0)
    	{
        return (getPerDaySal() / getShiftInMins()) * getLateTime();
    	} else
    	{
    		return 0;
    	}
    }
}
