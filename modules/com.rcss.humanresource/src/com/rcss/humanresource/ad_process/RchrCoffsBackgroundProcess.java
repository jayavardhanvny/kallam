package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.BundleProcess;

import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.RchrConstantType;
import com.rcss.humanresource.util.ShiftUtil;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;



import java.util.Calendar;
import java.util.Date;

public class RchrCoffsBackgroundProcess  extends DalBaseProcess implements BundleProcess{
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        doIt(bundle);
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        Date date = new Date();
        Calendar calendarFrom = Calendar.getInstance();
        calendarFrom.set(Calendar.DATE, calendarFrom.getActualMinimum(Calendar.DATE));
        calendarFrom.add(Calendar.DATE,-10);
        Calendar calendarTo = Calendar.getInstance();

        calendarTo.set(Calendar.DATE, calendarTo.getActualMaximum(Calendar.DATE));

        OBCriteria<RCHRDailyattenddemo> rchrDailyattenddemoOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
        rchrDailyattenddemoOBCriteria.add(Restrictions.ge(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE, calendarFrom.getTime()));
        rchrDailyattenddemoOBCriteria.add(Restrictions.le(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE, calendarTo.getTime()));
        //rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_WEEKLYOFF,Boolean.TRUE));
        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_OVERTIME, Boolean.TRUE));
        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_PRESENT, Boolean.TRUE));

        System.out.println("Size Of Coffs is " + rchrDailyattenddemoOBCriteria.list().size());
        OBCriteria<RCHR_Leavetype> rchrLeavetypeOBCriteria = OBDal.getInstance().createCriteria(RCHR_Leavetype.class);
        rchrLeavetypeOBCriteria.add(Restrictions.eq(RCHR_Leavetype.PROPERTY_SEARCHKEY, RchrConstantType.DAY_TYPE_CO));
        Integer integer = 0;
        for (RCHR_Leavetype rchrLeavetype : rchrLeavetypeOBCriteria.list()) {
            integer = rchrLeavetype.getPriority().intValue();
        }


        for (RCHRDailyattenddemo rchrDailyattenddemo : rchrDailyattenddemoOBCriteria.list()) {

            ShiftUtil shiftUtil = new ShiftUtil();
            String forecastedShift = shiftUtil.getShiftRotation(rchrDailyattenddemo.getAttendanceDate(), rchrDailyattenddemo.getEmployee());
            //if (forecastedShift.equals(rchrDailyattenddemo.getRcprShift().getId())) {
                if (rchrDailyattenddemo.getEmployee().isCoffapplicable()) {
                    OBCriteria<RchrCompensateOff> rchrCompensateOffOBCriteria = OBDal.getInstance().createCriteria(RchrCompensateOff.class);
                    rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_EMPLOYEE,
                            rchrDailyattenddemo.getEmployee()));
                    rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_ACURALDATE,
                            rchrDailyattenddemo.getAttendanceDate()));
                    if (rchrCompensateOffOBCriteria.list().size() == 0) {
		                System.out.println("in zero "+rchrDailyattenddemo.getEmployee().getDocumentNo() +" and "+rchrDailyattenddemo.getAttendanceDate());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(rchrDailyattenddemo.getAttendanceDate());
                        calendar.add(Calendar.DATE, integer);
                        RchrCompensateOff rchrCompensateOff = OBProvider.getInstance().get(RchrCompensateOff.class);
                        rchrCompensateOff.setAcuraldate(rchrDailyattenddemo.getAttendanceDate());
                        rchrCompensateOff.setOrganization(rchrDailyattenddemo.getOrganization());
                        rchrCompensateOff.setEmployee(rchrDailyattenddemo.getEmployee());
                        rchrCompensateOff.setAlertStatus("ACC");
                        //rchrCompensateOff.setExpriydate(calendar.getTime());
                        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_WEEKLYOFF, Boolean.TRUE));
                        if (rchrDailyattenddemoOBCriteria.list().size() == 2) {
                            insertCoffsLines(rchrDailyattenddemo);
                        }
                        OBDal.getInstance().save(rchrCompensateOff);
                    }
                }
            //}





















/*

            if(rchrDailyattenddemo.getEmployee().isCoffapplicable()){

                if(rchrShiftgroup.getShifttype().equals(RchrConstantType.SHIFT_TYPE_REGULAR)){
                    OBCriteria<RchrCompensateOff> rchrCompensateOffOBCriteria = OBDal.getInstance().createCriteria(RchrCompensateOff.class);
                    rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_EMPLOYEE,
                            rchrDailyattenddemo.getEmployee()));
                    rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_ACURALDATE,
                            rchrDailyattenddemo.getAttendanceDate()));
                    if(rchrCompensateOffOBCriteria.list().size()==0){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(rchrDailyattenddemo.getAttendanceDate());
                        calendar.add(Calendar.DATE,integer);
                        RchrCompensateOff rchrCompensateOff = OBProvider.getInstance().get(RchrCompensateOff.class);
                        rchrCompensateOff.setAcuraldate(rchrDailyattenddemo.getAttendanceDate());
                        rchrCompensateOff.setOrganization(rchrDailyattenddemo.getOrganization());
                        rchrCompensateOff.setEmployee(rchrDailyattenddemo.getEmployee());
                        rchrCompensateOff.setAlertStatus("ACC");
                        //rchrCompensateOff.setExpriydate(calendar.getTime());
                        OBDal.getInstance().save(rchrCompensateOff);
                    }
                }else if(rchrShiftgroup.getShifttype().equals(RchrConstantType.SHIFT_TYPE_ROTATIONAL)){
                    if(rchrDailyattenddemo.isWeeklyOff()){
                        insertCoffsLines(rchrDailyattenddemo);
                    }
            OBCriteria<RCHRDailyattenddemo> rchrDailyattenddemoOBCriteria1 = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
            rchrDailyattenddemoOBCriteria1.add(Restrictions.ge(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE, calendarFrom.getTime()));
            rchrDailyattenddemoOBCriteria1.add(Restrictions.le(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE, calendarTo.getTime()));
            rchrDailyattenddemoOBCriteria1.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_WEEKLYOFF, Boolean.FALSE));
            rchrDailyattenddemoOBCriteria1.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_OVERTIME, Boolean.TRUE));
            rchrDailyattenddemoOBCriteria1.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_PRESENT, Boolean.TRUE));
            OBCriteria<RchrCompensateOff> rchrCompensateOffOBCriteria = OBDal.getInstance().createCriteria(RchrCompensateOff.class);
            rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_EMPLOYEE,
                    rchrDailyattenddemo.getEmployee()));
            rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_ACURALDATE,
                    rchrDailyattenddemo.getAttendanceDate()));
            if (rchrCompensateOffOBCriteria.list().size() == 0) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(rchrDailyattenddemo.getAttendanceDate());
                        calendar.add(Calendar.DATE,integer);
                        RchrCompensateOff rchrCompensateOff = OBProvider.getInstance().get(RchrCompensateOff.class);
                        rchrCompensateOff.setAcuraldate(rchrDailyattenddemo.getAttendanceDate());
                        rchrCompensateOff.setOrganization(rchrDailyattenddemo.getOrganization());
                        rchrCompensateOff.setEmployee(rchrDailyattenddemo.getEmployee());
                        rchrCompensateOff.setAlertStatus("ACC");
                        //rchrCompensateOff.setExpriydate(calendar.getTime());
                        OBDal.getInstance().save(rchrCompensateOff);
                insertCoffsLines(rchrDailyattenddemo);
                */
            }







            calendarFrom.add(Calendar.DATE,-integer);
            getExpired(calendarFrom);






    }

    private void getExpired(Calendar calendarFrom){
        OBCriteria<RchrCompensateOff> rchrCompensateOffOBCriteria = OBDal.getInstance().createCriteria(RchrCompensateOff.class);
        rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_ALERTSTATUS,"ACC"));
        rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_ACURALDATE,calendarFrom.getTime()));
        for (RchrCompensateOff rchrCompensateOff : rchrCompensateOffOBCriteria.list()){
            rchrCompensateOff.setAlertStatus("EX");
            rchrCompensateOff.setExpriydate(calendarFrom.getTime());
        }
    }

    private void insertCoffsLines(RCHRDailyattenddemo rchrDailyattenddemo){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rchrDailyattenddemo.getAttendanceDate());
        //calendar.add(Calendar.DATE,30);
        RchrCompensateOff rchrCompensateOff = OBProvider.getInstance().get(RchrCompensateOff.class);
        rchrCompensateOff.setAcuraldate(rchrDailyattenddemo.getAttendanceDate());
        rchrCompensateOff.setOrganization(rchrDailyattenddemo.getOrganization());
        rchrCompensateOff.setEmployee(rchrDailyattenddemo.getEmployee());
        rchrCompensateOff.setAlertStatus("ACC");
        //rchrCompensateOff.setExpriydate(calendar.getTime());
        OBDal.getInstance().save(rchrCompensateOff);
    }
}


