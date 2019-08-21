package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.*;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollUtils;
import com.redcarpet.production.data.RCPRShift;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DailyAttendanceProcess {
    final static Logger logger = Logger.getLogger(DailyAttendanceProcess.class);
    private String description = "";
    public void processDailyAttendance() throws Exception{
        EmployeeUtil employeeUtil = new EmployeeUtil();
        HashMap<String, RchrEmployee> rchrEmployeeHashMap= employeeUtil.getWorkingEmployeesList();
        logger.info("Active Employees Size is "+rchrEmployeeHashMap.size());
        ShiftUtil shiftUtil = new ShiftUtil();
        String stringDate = "2017-12-01";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(stringDate);
        //PayrollUtils.getInstance().getDBCompatiableDate(stringDate);
        HashMap<String,RCPRShift> rcprShiftHashMap = shiftUtil.getActiveShifts();

        OBCriteria<RCHRDailyattend> rchrDailyattendOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattend.class);
        rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_VALIDATE, Boolean.FALSE));
        rchrDailyattendOBCriteria.add(Restrictions.ge(RCHRDailyattend.PROPERTY_ATTENDANCEDATE,date));
	//rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_EMPLOYEEID,"3009"));
        logger.info("Size of Records to validate and date is "+rchrDailyattendOBCriteria.list().size()+" and date is "+date);
        int flag=0;
        HqlUtils hqlUtils  = new HqlUtils();
        List rchrDailyattenddemoList = hqlUtils.getRchrDailyattenddemoList(date);
        logger.info("Size of deleted list is "+rchrDailyattenddemoList.size());
        if(rchrDailyattenddemoList.size()>0){
            // Delete
           int deletedRows= hqlUtils.deleteRchrDailyattenddemoList(date);
            logger.info("Deleted Rows = "+deletedRows);
        }

        for (RCHRDailyattend rchrDailyattend : rchrDailyattendOBCriteria.list()) {
            Boolean isInserted = Boolean.FALSE;
            description = "";
            if (rchrDailyattend.getErrorLog()!=null && rchrDailyattend.getErrorLog().trim().length()> 0) {
                logger.info("Punch Records are "+rchrDailyattend.getErrorLog()+" Date "+rchrDailyattend.getAttendanceDate()
                +" employee Id "+rchrDailyattend.getEmployeeId());
                PunchValidationUtil punchValidationUtil = new PunchValidationUtil(rchrDailyattend.getErrorLog().trim());
                if(punchValidationUtil.getStringArrayListPunches().size()>0) {
                    //if (punchValidationUtil.getStringArrayListPunches().size() >= 4) {
                        if (punchValidationUtil.isFirstInPunch()) {
                            if (punchValidationUtil.isLastOutPunch()) {

                                //if (punchValidationUtil.punchSequenceFollows()) {
                                 //   logger.info("is Followed " + punchValidationUtil.punchSequenceFollows());
                                    //logger.info("String array lIst "+punchValidationUtil.getStringArrayListPunches().size());
                                    description = getProcess(rchrDailyattend, punchValidationUtil,
                                            rchrEmployeeHashMap,flag);
                                    //description ="Correct Punch";
                                //} else {
                                   // logger.info("Punch Sequence Not Followed");
                                   // logger.info("Punch at " + punchValidationUtil.getIndex() + " : " +
                                       //     punchValidationUtil.getPunch() +
                                       //     " : " + rchrDailyattend.getErrorLog());
                                    //description = "Punch Sequence Not Followed";
                                //}
                            //}
                            } else {
                                logger.info("Last Out punch is Missed "+rchrDailyattend.getEmployeeId());
                                description = "Last Out punch is Missed";
    			                      isInserted = checkDates(rchrEmployeeHashMap,rchrDailyattend,employeeUtil);
                            }
                        } else {
                             logger.info("First In punch is Missed "+rchrDailyattend.getEmployeeId());
                             description = "First In punch is Missed";
			                       isInserted = checkDates(rchrEmployeeHashMap,rchrDailyattend,employeeUtil);
                        }
                    //}else {
                       // logger.info("Minimum Four Punches are Required");
                       // description = "Minimum Four Punches are Required You have only "+punchValidationUtil.getStringArrayListPunches().size();
                   // }
                }else{
                    logger.info("Array List size is zero");
                }

            }else{
                logger.info("Punch Records are null ");
                description="No Punch";
	              isInserted = checkDates(rchrEmployeeHashMap,rchrDailyattend,employeeUtil);
	               if (isInserted.equals(Boolean.FALSE)){
                    RCHRDailyattenddemo rchrDailyattenddemo = OBProvider.getInstance().get(RCHRDailyattenddemo.class);
                    rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_LOP);
                    rchrDailyattenddemo.setPresent(Boolean.FALSE);
                    rchrDailyattenddemo.setWeeklyOff(Boolean.FALSE);
                    //rchrDailyattenddemo.getRchrDailyattend().setDaytype(RchrConstantType.DAY_TYPE_WO);
                    rchrDailyattend.setValidate(Boolean.TRUE);
                    rchrDailyattenddemo.setRchrDailyattend(rchrDailyattend);
                    rchrDailyattenddemo.setAttendanceDate(rchrDailyattend.getAttendanceDate());
                    rchrDailyattenddemo.setEmployee((RchrEmployee) rchrEmployeeHashMap.get(rchrDailyattend.getEmployeeId()));
                    rchrDailyattenddemo.setEmployeeId(rchrDailyattend.getEmployeeId());
                    rchrDailyattend.setDaytype(RchrConstantType.DAY_TYPE_LOP);
                    OBDal.getInstance().save(rchrDailyattenddemo);
                    this.deleteFromGrievance(rchrDailyattend);
                }
               // ValidateDate validateDate = new ValidateDate();
                //check weekOffs, Leaves and Coff's()
            }
            if (isInserted.equals(Boolean.TRUE)){
                this.deleteFromGrievance(rchrDailyattend);
            }
            rchrDailyattend.setReasonForLeave(description);
        }
        logger.info("Inserted or Update lines are "+flag);
    }


    private void deleteFromGrievance(RCHRDailyattend tmpdemo){
        String hql = "DELETE FROM Rcpl_Emppprocessmanual AS emppp WHERE emppp.rchrDailyattend.id='"+tmpdemo.getId()+"' AND process='N' ";
        OBDal.getInstance().getSession().createQuery(hql).executeUpdate();
    }
    private String getProcess(RCHRDailyattend rchrDailyattend, PunchValidationUtil punchValidationUtil,
                           HashMap<String, RchrEmployee> rchrEmployeeHashMap,int flag) throws Exception{
        if(rchrEmployeeHashMap.containsKey(rchrDailyattend.getEmployeeId())) {
            RchrEmployee emp = (RchrEmployee) rchrEmployeeHashMap.get(rchrDailyattend.getEmployeeId());
            //String description="";
		
            if (emp.getRchrShiftgroup() != null && emp.getRelay() != null ) {
                ShiftUtil shiftUtil = new ShiftUtil();
                RchrShiftgroup rchrShiftgroup = shiftUtil.getShiftGroup(emp, rchrDailyattend.getAttendanceDate());
                String shiftId = "ED4817728DD24E86A132094AE5B1DCDE";
                try {
                    shiftId = shiftUtil.getEmployeeShift(rchrShiftgroup, punchValidationUtil.getPunchTimeStamp(rchrDailyattend.getAttendanceDate(),
                            punchValidationUtil.getFirstInPuchTimeString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("Error in Assigning the Shift");
                }
                RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
		logger.info("Employee Id "+emp.getDocumentNo());
                RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
                logger.info("Shift : "+ shift);
                logger.info("Shift getMinpunchesrequired : "+ shift.getMinpunchesrequired());
                if (rchrDailyattend.getCount() >= shift.getMinpunchesrequired().intValue() || shift.getMinpunchesrequired().intValue() == 0 ) {
                    DailyAttendanceDemoTwo dailyAttendanceDemoTwo = new DailyAttendanceDemoTwo();
                    description = dailyAttendanceDemoTwo.execute(emp, rchrDailyattend, flag);
                } else {
                    description = "No Min Punches";
                }
            }
        }
        return description;
    }

    /*
        DailyAttendanceInsertion dailyAttendanceInsertion = new DailyAttendanceInsertion();
        Timestamp timestampPunchIn = punchValidationUtil.getPunchTimeStamp(rchrDailyattend.getAttendanceDate(),
                punchValidationUtil.getFirstInPuchTimeString(),PunchValidationUtil.inPunch);
        ShiftUtil shiftUtil = new ShiftUtil();
        RchrShiftgroup rchrShiftgroup = shiftUtil.getShiftGroup(emp,rchrDailyattend.getAttendanceDate());
        String shiftId = shiftUtil.getEmployeeShift(emp, timestampPunchIn,rchrDailyattend,rchrShiftgroup);

        if(rchrShiftgroup.getShifttype().equals(RchrConstantType.SHIFT_TYPE_REGULAR)){
            if((shiftId!=null) && (!shiftId.equals("ED4817728DD24E86A132094AE5B1DCDE"))
                    && (!shiftId.equals(""))) {
                logger.info("Regular Insertion");
                description = dailyAttendanceInsertion.regularAttendanceLine(rchrDailyattend,
                        punchValidationUtil,emp, timestampPunchIn,shiftId);
            }else{
                logger.info("General Shift In Time Mismatch");
                description = "General Shift In Time Mismatch ";
            }
        }else if (rchrShiftgroup.getShifttype().equals(RchrConstantType.SHIFT_TYPE_ROTATIONAL)){
            //dailyAttendanceInsertion.rotationalAttendanceLine();

            if((shiftId!=null) && (!shiftId.equals("ED4817728DD24E86A132094AE5B1DCDE"))
                    && (!shiftId.equals(""))) {
                logger.info("8 Hrs Insertion");
                description = dailyAttendanceInsertion.rotationalAttendanceLine(rchrDailyattend,
                        punchValidationUtil,emp, timestampPunchIn,shiftId,shiftUtil);
            }else{
                logger.info("8 Hrs Rotational Insertion");
                description = "8 Hrs Rotational Insertion ";
            }
            //logger.info("8 Hrs Rotational Insertion");
            //description = "8 Hrs Rotational";

        } else if (rchrShiftgroup.getShifttype().equals(RchrConstantType.SHIFT_TYPE_ROTATIONAL)){
            dailyAttendanceInsertion.rotationalAttendanceLine(rchrDailyattend,punchValidationUtil, emp,
                     timestampPunchIn,
                     shiftId, shiftUtil);
            logger.info("12 Hrs Rotational Insertion");
            description="12 Hrs Rotational Insertion";
        }
    }else{
        logger.info("Not in Emplyoyee Master");
        description = "Not in Emplyoyee Master";
    } */
    private Boolean checkDates(HashMap rchrEmployeeHashMap,RCHRDailyattend rchrDailyattend,EmployeeUtil employeeUtil){
	//logger.info("Log 1");
        Boolean inserted = Boolean.FALSE;
        if(rchrEmployeeHashMap.containsKey(rchrDailyattend.getEmployeeId())) {
            //logger.info("Log 2");
            RchrEmployee emp = (RchrEmployee) rchrEmployeeHashMap.get(rchrDailyattend.getEmployeeId());

            List<RCHRLeaveRequisitionLine> rchrLeaveRequisitionLineList = employeeUtil.getEmployeeLeaves(emp, rchrDailyattend.getAttendanceDate());
            //logger.info("Log 3 "+list.size());
            List<RchrOndutylines> rchrOndutylinesList = employeeUtil.getEmployeesOnduty(emp, rchrDailyattend.getAttendanceDate());
            //logger.info("Log 4");
            if (emp.isWeekOffApplicable()) {
                int weeklyOff = rchrDailyattend.getAttendanceDate().getDay();
                int empWeek = employeeUtil.getWeeklyOff(emp, rchrDailyattend.getAttendanceDate());
                if (weeklyOff == empWeek) {
                    //logger.info("Log 5");
                    RCHRDailyattenddemo rchrDailyattenddemo = OBProvider.getInstance().get(RCHRDailyattenddemo.class);
                    rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_WO);
                    rchrDailyattenddemo.setPresent(Boolean.FALSE);
                    rchrDailyattenddemo.setWeeklyOff(Boolean.TRUE);
                    rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_WO);
                    //rchrDailyattenddemo.getRchrDailyattend().setDaytype(RchrConstantType.DAY_TYPE_WO);
                    description = "W/O";
                    rchrDailyattend.setValidate(Boolean.TRUE);
                    rchrDailyattenddemo.setRchrDailyattend(rchrDailyattend);
                    rchrDailyattenddemo.setAttendanceDate(rchrDailyattend.getAttendanceDate());
                    rchrDailyattenddemo.setEmployee(emp);
                    rchrDailyattenddemo.setEmployeeId(rchrDailyattend.getEmployeeId());
                    rchrDailyattend.setDaytype(RchrConstantType.DAY_TYPE_WO);
                    OBDal.getInstance().save(rchrDailyattenddemo);
                    inserted = Boolean.TRUE;
                }
            }
            // Pre-Leave Pre-Dated... Leave/ CL/ SL/ COff's

	    if (rchrLeaveRequisitionLineList.size() > 0 || rchrDailyattend.isLeave()) {
                //logger.info("In Leave  log 2");
                RCHRDailyattenddemo rchrDailyattenddemo = OBProvider.getInstance().get(RCHRDailyattenddemo.class);
                //rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_LOP);
                rchrDailyattenddemo.setPresent(Boolean.FALSE);
                String leaveType = rchrLeaveRequisitionLineList.get(0).getLeaveType().getSearchKey();
                if (leaveType.equals(RchrConstantType.DAY_TYPE_CO))
                    rchrDailyattenddemo.setPresent(Boolean.TRUE);
                rchrDailyattenddemo.setDaytype(leaveType);
                rchrDailyattenddemo.setRchrDailyattend(rchrDailyattend);
                rchrDailyattenddemo.setAttendanceDate(rchrDailyattend.getAttendanceDate());
                rchrDailyattenddemo.setEmployee(emp);
                rchrDailyattenddemo.setEmployeeId(rchrDailyattend.getEmployeeId());
                OBDal.getInstance().save(rchrDailyattenddemo);
                rchrDailyattend.setDaytype(leaveType);
                rchrDailyattend.setValidate(Boolean.TRUE);
                rchrDailyattend.setLeave(Boolean.TRUE);
                description = "Applied Leave";
                //rchrDailyattend.setDaytype(leaveType);
                inserted = Boolean.TRUE;

            } else if (rchrOndutylinesList.size() > 0) {
                //logger.info("Log 6");
                RCHRDailyattenddemo rchrDailyattenddemo = OBProvider.getInstance().get(RCHRDailyattenddemo.class);
                //rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_LOP);
                //rchrDailyattenddemo.setPresent(Boolean.FALSE);
                rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_OD);
                description = "Applied OD";
                rchrDailyattenddemo.setPresent(Boolean.TRUE);
                rchrDailyattenddemo.setRchrDailyattend(rchrDailyattend);
                rchrDailyattenddemo.setAttendanceDate(rchrDailyattend.getAttendanceDate());
                rchrDailyattenddemo.setEmployee(emp);
                rchrDailyattenddemo.setEmployeeId(rchrDailyattend.getEmployeeId());
                OBDal.getInstance().save(rchrDailyattenddemo);
                rchrDailyattend.setDaytype(RchrConstantType.DAY_TYPE_OD);
                rchrDailyattend.setValidate(Boolean.TRUE);
                //rchrDailyattend.setDaytype(RchrConstantType.DAY_TYPE_OD);

                inserted = Boolean.TRUE;
                //logger.info("In Leave ");
            }


        }
        return inserted;

    }
}
