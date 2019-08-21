package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.*;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.RchrConstantType;
import com.rcss.humanresource.util.ShiftUtil;
import com.redcarpet.production.data.RCPRShift;
import com.timeutils.sam.TimeDiffUtil;
import org.apache.tools.ant.types.resources.Restrict;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class DailyAttendanceDemoTwo {
    protected Logger logger = Logger.getLogger(DailyAttendanceDemoTwo.class);
    public String execute(RchrEmployee emp,RCHRDailyattend tmpdemo,int flag) throws Exception{
        //String punchRecords="00:00:00(in),00:00:00(out)";
        String punchRecords = tmpdemo.getErrorLog();
        if (punchRecords == null) {
            punchRecords = "00:00:00(in),00:00:00(out)";
        }
        String[] temp;
        //String[] inouttemp;
        String[] outtemp;
        String delimiter = ",";
        temp = punchRecords.split(delimiter);
        String[] inouttemp = punchRecords.split(delimiter);
        int lngth = temp.length;
        logger.info("length is.." + lngth);
        String punchIn = "00:00:00";
        String punchInDemo = null;
        String nextpunchIn = "00:00:00";
        String nextpunchOut = "00:00:00";
        String punchOut = "00:00:00";
        String breakOut = "00:00:00";
        String breakIn = "00:00:00";
        String punchOutDemo = null;
        String punchShift = "00:00:00";
        String absent = "00:00:00";
        if (lngth == 4) {
            for (int i = 0; i < lngth; i++) {
                if (temp[i].contains("(in)")) {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(in)", "");
                } else {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(out)", "");
                }
                //logger.info(i+"th elementis.."+temp[i]);
                if (i == 0 && inouttemp[0].contains("(in)")) {
                    punchIn = temp[0];
                    punchInDemo = temp[0];
                } else if (inouttemp[0].contains("(out)") && inouttemp[1].contains("(in)")) {
                    punchIn = temp[1];
                    punchInDemo = temp[1];
                } else if (inouttemp[0].contains("(out)") && inouttemp[1].contains("(out)") && inouttemp[2].contains("(in)")) {
                    punchIn = temp[2];
                    punchInDemo = temp[2];
                }


				/*else if(i==0 && inouttemp[0].contains("(out)")){
					nextpunchOut=temp[0];
				}*/

                if (i == 1 && inouttemp[1].contains("(out)")) {
                    breakOut = temp[1];
                }
                if (i == 2 && inouttemp[2].contains("(in)")) {
                    breakIn = temp[2];
                }
                if (i == 3 && inouttemp[3].contains("(out)")) {

                    punchOut = temp[3];
                    punchOutDemo = temp[3];
                } else if (i == lngth - 1 && inouttemp[lngth - 1].contains("(in)")) {
                    nextpunchIn = temp[lngth - 1];
                }
            }
        } else if (lngth == 2) {
            for (int i = 0; i < lngth; i++) {
                if (temp[i].contains("(in)")) {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(in)", "");

                } else {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(out)", "");

                }

                if (i == 0 && inouttemp[0].contains("(in)")) {
                    punchIn = temp[0];
                    punchInDemo = temp[0];
                } else if (inouttemp[0].contains("(out)") && inouttemp[1].contains("(in)")) {
                    punchIn = temp[1];
                    punchInDemo = temp[1];
                }
                if (i == 1 && inouttemp[1].contains("(out)")) {
                    punchOut = temp[1];
                    punchOutDemo = temp[1];
                } else if (i == lngth - 1 && inouttemp[lngth - 1].contains("(in)")) {
                    nextpunchIn = temp[lngth - 1];
                    logger.info("nextpunchIn is.." + nextpunchIn);
                }
            }
        } else if (lngth == 3) {
            int j = 0;
            for (int i = 0; i < lngth; i++) {
                if (temp[i].contains("(in)")) {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(in)", "");

                } else {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(out)", "");

                }
                if (i == 0 && inouttemp[0].contains("(in)")) {
                    punchIn = temp[0];
                    punchInDemo = temp[0];
                } else if (inouttemp[0].contains("(out)") && inouttemp[1].contains("(in)")) {
                    punchIn = temp[1];
                    punchInDemo = temp[1];
                }
					/*if(i==1 && inouttemp[1].contains("(out)")){
						punchOut=temp[1];
						punchOutDemo=temp[1];
					}*/
                if (i == 2 && inouttemp[2].contains("(out)")) {
                    punchOut = temp[lngth - 1];
                    punchOutDemo = temp[lngth - 1];
                } else if (i == lngth - 1 && inouttemp[lngth - 1].contains("(in)")) {
                    nextpunchIn = temp[lngth - 1];
                }/*else if(i==2){
								punchOut=temp[lngth-1];
								punchOutDemo=temp[lngth-1];
							}*/
                //if(inouttemp[lngth-1].contains("(out)"))

            }

            for (int i = 0; i < lngth; i++) {

                if (inouttemp[i].contains("(out)")) {
                    if ((checkBreakOut(temp[i]).equalsIgnoreCase("true")) && (breakOut == "00:00:00")) {
                        breakOut = temp[i];
                        j = i;
                    }
                }
            }

            for (int i = j + 1; i < lngth; i++) {
                if (inouttemp[i].contains("(in)")) {
                    if ((checkBreakOut(temp[i]).equalsIgnoreCase("true")) && (breakIn == "00:00:00")) {
                        breakIn = temp[i];
                    }
                }
            }

        } else if (lngth > 4) {
            int j = 0;
            for (int i = 0; i < lngth; i++) {
                if (temp[i].contains("(in)")) {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(in)", "");

                } else {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(out)", "");

                }
                if (i == 0 && inouttemp[0].contains("(in)")) {
                    punchIn = temp[0];
                    punchInDemo = temp[0];
                } else if (inouttemp[0].contains("(out)") && inouttemp[1].contains("(in)")) {
                    punchIn = temp[1];
                    punchInDemo = temp[1];
                } else if (inouttemp[0].contains("(out)") && inouttemp[1].contains("(out)") && inouttemp[2].contains("(in)")) {
                    punchIn = temp[2];
                    punchInDemo = temp[2];
                } else if (inouttemp[0].contains("(out)") && inouttemp[1].contains("(out)") && inouttemp[2].contains("(out)") && inouttemp[3].contains("(in)")) {
                    punchIn = temp[3];
                    punchInDemo = temp[3];
                }
                if (i == lngth - 1 && inouttemp[lngth - 1].contains("(out)")) {
                    punchOut = temp[lngth - 1];
                    punchOutDemo = temp[lngth - 1];
                } else if (i == lngth - 1 && inouttemp[lngth - 1].contains("(in)")) {
                    nextpunchIn = temp[lngth - 1];
                }/*else if(i==lngth-1){
    						punchOut=temp[lngth-1];
    						punchOutDemo=temp[lngth-1];
    					}*/
            }


            for (int i = 0; i < lngth; i++) {

                if (inouttemp[i].contains("(out)")) {
                    if ((checkBreakOut(temp[i]).equalsIgnoreCase("true")) && (breakOut == "00:00:00")) {
                        breakOut = temp[i];
                        j = i;
                    }
                }
            }

            for (int i = j + 1; i < lngth; i++) {
                if (inouttemp[i].contains("(in)")) {
                    if ((checkBreakOut(temp[i]).equalsIgnoreCase("true")) && (breakIn == "00:00:00")) {
                        breakIn = temp[i];
                    }
                }
            }
        } else {

            for (int i = 0; i < lngth; i++) {
                if (temp[i].contains("(in)")) {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(in)", "");

                } else {
                    inouttemp[i] = temp[i];
                    temp[i] = temp[i].replace("(out)", "");

                }
                if (i == 0 && inouttemp[0].contains("(in)")) {
                    punchIn = temp[0];
                    punchInDemo = temp[0];
                }

            }

            //logger.info("user is absent..");
            absent = "absent";
        }








        // Start next day c-shift late punch ...

        SimpleDateFormat sdfrmt = new SimpleDateFormat("HH:mm:ss");
        // String punchOutDummy=null;

        Date tempin = sdfrmt.parse(punchIn);
        Date tempout = sdfrmt.parse(punchOut);
        java.sql.Timestamp timetempin = new java.sql.Timestamp(tempin.getTime());
        java.sql.Timestamp timetempout = new java.sql.Timestamp(tempout.getTime());
        Date attnddate = tmpdemo.getAttendanceDate();
	             /*if((timetempin.getHours() >=0 && timetempin.getMinutes() >=0 && timetempin.getSeconds() >=1) && (timetempin.getHours() <= 2)){
				   Calendar caltemp = Calendar.getInstance();
				   caltemp.setTime(attnddate);
				   caltemp.add(Calendar.DATE, -1);
				   attnddate=caltemp.getTime();
				  //getPunchoutCshift(tmpdemo,attnddate);

			   }*/
        //get the c-shift and blr c-shift Out punch ...
        Date tempnextin = sdfrmt.parse(nextpunchIn);
        java.sql.Timestamp timenextpunchIn = new java.sql.Timestamp(tempnextin.getTime());
        Date outPunchCdate = tmpdemo.getAttendanceDate();
        if (timenextpunchIn.getHours() >= 1) {
            Calendar caltemp = Calendar.getInstance();
            caltemp.setTime(outPunchCdate);
            caltemp.add(Calendar.DATE, +1);
            outPunchCdate = caltemp.getTime();
            String cshiftOutPunch = getPunchoutCshift(tmpdemo, outPunchCdate);
            if (cshiftOutPunch == null) {
                punchOut = new String("00:00:00");
                punchOutDemo = null;
                // punchOutDemo=cshiftOutPunch;
            } else {
                punchOut = cshiftOutPunch;
                punchOutDemo = cshiftOutPunch;
            }

        } else if (timenextpunchIn.getHours() == 0 && timenextpunchIn.getMinutes() > 0) {
            Calendar caltemp = Calendar.getInstance();
            caltemp.setTime(outPunchCdate);
            caltemp.add(Calendar.DATE, +1);
            outPunchCdate = caltemp.getTime();
            String cshiftOutPunch = getPunchoutCshift(tmpdemo, outPunchCdate);
            if (cshiftOutPunch == null) {
                punchOut = new String("00:00:00");
                punchOutDemo = null;
                // punchOutDemo=cshiftOutPunch;
            } else {
                punchOut = cshiftOutPunch;
                punchOutDemo = cshiftOutPunch;
            }
        }

        //*********** END *************************

        String result = getTimeDuration(punchIn, punchOut, punchOutDemo, punchInDemo);
        String res;

        if (result == null) {
            res = new String("00:00:00");

        } else {
            res = new String(result.replaceAll("-", ""));
        }

        // Two lines will Update IF OT

        Date duration;
        java.sql.Timestamp shiftDuration = null,totalPermissionTimeDuration = null;
        java.sql.Timestamp otduration = null;
        java.sql.Timestamp timePunchin = null;
        java.sql.Timestamp timePunchout = null;
        Integer permissoinMinutes = 0;
        Integer minimumWorkingHours = 0;
        String shiftGroup = "1234FAD6C1FE4DAFBEC11C919AFDD540";
        String shiftGroupGeneral = "1ECFE0E38A7B4A18AD6EA5F0BDC3C6FA";
        //String rchrEmployeeId=getEmployee(tmpdemo);
        //RchrEmployee emp =
        ShiftUtil shiftUtil = new ShiftUtil();
        EmployeeUtil employeeUtil = new EmployeeUtil();

        RchrShiftgroup rchrShiftgroup = shiftUtil.getShiftGroup(emp,tmpdemo.getAttendanceDate());
        int weeklyOff = attnddate.getDay();
        int empWeek = employeeUtil.getWeeklyOff(emp, tmpdemo.getAttendanceDate());
        Boolean isWeekOff = Boolean.FALSE;
        if(weeklyOff==empWeek){
            isWeekOff=Boolean.TRUE;
        }
        // Check permission to add duration to the actual duration...
        List<RCHRPermission> rchrPermissionList = checkPermission(tmpdemo.getAttendanceDate(), emp);
        Boolean permission = Boolean.FALSE;
        String totalPermissionTimeDurationString = "00:00:00";
        if (rchrPermissionList.size() > 0){
            permission = Boolean.TRUE;
            permissoinMinutes = rchrPermissionList.get(0).getShiftinmins().intValue();
            //totalPermissionTimeDurationString = rchrPermissionList.get(0).getTimedifference();
        }

        try {
            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
            duration = sdft.parse(res);
            timePunchin = new java.sql.Timestamp(sdft.parse(punchIn).getTime());
            otduration = new java.sql.Timestamp(duration.getTime());
            shiftDuration = new java.sql.Timestamp(sdft.parse(punchIn).getTime());
            totalPermissionTimeDuration = new java.sql.Timestamp(sdft.parse(totalPermissionTimeDurationString).getTime());
        } catch (ParseException ex) {
            // ex.printStackTrace();
        }
        String shiftId = "ED4817728DD24E86A132094AE5B1DCDE";
        try {
            // To get shift need to find out timePunchIn
            shiftId = shiftUtil.getEmployeeShift(rchrShiftgroup, timePunchin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Assigning the Shift");
        }
        RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
        logger.info("Total Duration is "+otduration.getHours());
        TimeStampUtil timeStampUtil = new TimeStampUtil();

        minimumWorkingHours = timeStampUtil.getShiftInMins(res);
        int totalDuration = minimumWorkingHours + permissoinMinutes;

        int difference = 0;
        if (shift.getMinshiftduration().intValue()>totalDuration)
            difference = shift.getMinshiftduration().intValue() - totalDuration;
        // Validating the totalDuration with Shift Duration...
        String rotatteShift = shiftUtil.getShiftRotation(tmpdemo, emp);
        RCPRShift rotationalShift = OBDal.getInstance().get(RCPRShift.class, rotatteShift);
        if (totalDuration >= shift.getMinshiftduration().intValue() || shift.getMinshiftduration().intValue() == 0 ) {
            if ((otduration.getHours() >= 14) && otduration.getHours() <= 18 &&
                    !shiftGroupGeneral.equals(emp.getRchrShiftgroup().getId())
                    && !shiftGroup.equals(emp.getRchrShiftgroup().getId())) {
                // try {
                Boolean overTime = Boolean.FALSE;
                Boolean present = Boolean.TRUE;
                RCHRDailyattenddemo atndTmpnext = OBProvider.getInstance().get(RCHRDailyattenddemo.class);
                try {
                    SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
                    Date inPunch = sdft.parse(punchIn);
                    Date outPunch = sdft.parse(punchOut);
                    timePunchin = new java.sql.Timestamp(inPunch.getTime());
                    timePunchout = new java.sql.Timestamp(outPunch.getTime());

                    atndTmpnext.setOrganization(tmpdemo.getOrganization());
                    atndTmpnext.setClient(tmpdemo.getClient());
                    atndTmpnext.setAttendanceDate(attnddate);
                    atndTmpnext.setErrorLog(tmpdemo.getErrorLog());
                    atndTmpnext.setEmployeeId(tmpdemo.getEmployeeId());
/*
                    String shiftId = "ED4817728DD24E86A132094AE5B1DCDE";
                    try {
                        shiftId = shiftUtil.getEmployeeShift(rchrShiftgroup, timePunchin);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("Error in Assigning the Shift");
                    }
*/

                    //RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
                    atndTmpnext.setPunchIn(timePunchin);
                    atndTmpnext.setPunchOut(shift.getToTime());
                    atndTmpnext.setRcprShift(shift);
                    // "2015-08-24 19:00:00"
                    String shiftOut = shift.getToTime().toString();
                    SimpleDateFormat sdftOt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date punchoutOT = sdftOt.parse(shiftOut);
                    String otPunchout = sdft.format(punchoutOT);
                    logger.info("tirumal check value for otPunchout is.." + otPunchout);
                    String resultot = getTimeDuration(punchIn, otPunchout, punchOutDemo, punchInDemo);
                    String resot;
                    if (resultot == null) {
                        resot = new String("00:00:00");

                    } else {
                        resot = new String(resultot.replaceAll("-", ""));
                    }

                    logger.info("tirumal check value for duration is.. " + resot);

                    if (resultot == null) {
                        atndTmpnext.setDuration(new String("00:00:00"));
                        atndTmpnext.setPresent(Boolean.FALSE);
                        present =Boolean.FALSE;
                    } else {
                        atndTmpnext.setDuration(resot);
                    }

                    if (isWeekOff) {
                        atndTmpnext.setWeeklyOff(Boolean.TRUE);
                    }

                    if (result != null && isWeekOff) {
                        atndTmpnext.setOvertime(Boolean.TRUE);
                        overTime = Boolean.TRUE;
                    }

                    //rotatteShift = shiftUtil.getShiftRotation(tmpdemo, emp);
                    String staff = new String("Staff");
                    String noShift = "ED4817728DD24E86A132094AE5B1DCDE";

                    if (!shiftId.equals(rotatteShift)  &&
                            !staff.equals(emp.getEmployeeType()) &&
                            !shiftGroupGeneral.equals(rchrShiftgroup.getId())
                            && !shiftGroup.equals(rchrShiftgroup.getId())) {
                        atndTmpnext.setOvertime(Boolean.TRUE);
                        overTime = Boolean.TRUE;
                    }

                    updateDayType(present, overTime, atndTmpnext, tmpdemo, emp);

                    atndTmpnext.setEmployee(emp);
                    atndTmpnext.setRchrDailyattend(tmpdemo);
                    tmpdemo.setValidate(Boolean.TRUE);
                    tmpdemo.setShift(shift.getName());
                    tmpdemo.setForecastedshift(OBDal.getInstance().get(RCPRShift.class, rotatteShift).getName());
                    //OBDal.getInstance().save(tmpdemo);
                    OBDal.getInstance().save(atndTmpnext);
                    flag++;


                    insertOtherAttendLine(tmpdemo, emp, punchIn, punchOut, punchOutDemo,
                            punchInDemo, punchShift, attnddate, flag,rchrShiftgroup,employeeUtil,isWeekOff, rotatteShift);
                } catch (ParseException ex) {
                    // ex.printStackTrace();
                }
                logger.info("If Condition");
            } else {
                Boolean overTime = Boolean.FALSE;
                Boolean present = Boolean.TRUE;
                //------- END Two lines will Update IF OT---------
                //-------------
                RCHRDailyattenddemo atndTmp = OBProvider.getInstance().get(RCHRDailyattenddemo.class);
                if (result == null) {
                    // logger.info("result1 is ..."+result);
                    atndTmp.setDuration(new String("00:00:00"));
                    atndTmp.setPresent(Boolean.FALSE);
                    present=Boolean.FALSE;
                } else {
                    //logger.info("time duration1 is ..."+res);
                    atndTmp.setDuration(res);
                }
                try {
                    String breakResult = getTimeDuration(breakOut, breakIn, absent, absent);
                    if (breakResult == null) {
                        logger.info("result2 is ..." + breakResult);
                        atndTmp.setShift(new String("00:00:00"));
                        //atndTmp.setPresent(Boolean.FALSE);
                    } else {
                        String brres = new String(breakResult.replaceAll("-", ""));
                        String time1 = "24:00:00";
                        // String date3;
                        String strArry[] = brres.split(":");
                        int hrs = new Integer(strArry[0]).intValue();
                        if (hrs >= 24) {
                            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                            timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            Date date1 = timeFormat.parse(time1);
                            Date date2 = timeFormat.parse(brres);
                            long summ = date2.getTime() - date1.getTime();
                            String date3 = timeFormat.format(new Date(summ));
                            logger.info("time duration2 is... " + date3);
                            atndTmp.setShift(date3);
                        } else {
                            logger.info("time duration2 is ... " + brres);
                            atndTmp.setShift(brres);
                        }
                    }

                    //logger.info("error process1 ...!!");
                    atndTmp.setOrganization(tmpdemo.getOrganization());
                    atndTmp.setClient(tmpdemo.getClient());
                    // logger.info("error process2 ...!!");

                    SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
                    Date inPunch = sdft.parse(punchIn);
                    Date outPunch = sdft.parse(punchOut);
                    timePunchin = new java.sql.Timestamp(inPunch.getTime());
                    timePunchout = new java.sql.Timestamp(outPunch.getTime());
                    atndTmp.setPunchIn(timePunchin);
                    atndTmp.setPunchOut(timePunchout);
                    Date inBreak = sdft.parse(breakIn);
                    Date outBreak = sdft.parse(breakOut);
                    java.sql.Timestamp timeBreakin = new java.sql.Timestamp(inBreak.getTime());
                    java.sql.Timestamp timeBreakout = new java.sql.Timestamp(outBreak.getTime());
                    atndTmp.setBreakin(timeBreakin);
                    //atndTmp.set
                    atndTmp.setBreakout(timeBreakout);
                } catch (ParseException ex) {
                    // ex.printStackTrace();
                }
                atndTmp.setAttendanceDate(attnddate);
                atndTmp.setErrorLog(tmpdemo.getErrorLog());
                atndTmp.setEmployeeId(tmpdemo.getEmployeeId());
                logger.info(" punch no and name is for group..." + tmpdemo.getEmployeeId());
                logger.info(" employee no and name is for group..." + emp.getDocumentNo() + " " + emp.getEmployeeName());
                //RchrShiftgroup rchrShiftgroup = shiftUtil.getShiftGroup(emp,tmpdemo.getAttendanceDate());
                //String shftid = shiftUtil.getEmployeeShift(emp, timePunchin,tmpdemo,rchrShiftgroup);
                //String shiftId = "ED4817728DD24E86A132094AE5B1DCDE";
                //String shftid = shiftUtil.getEmployeeShift(rchrShiftgroup, timePunchin);
                //RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shiftId);
                atndTmp.setRcprShift(shift);
                try {
                    shiftId = shiftUtil.getEmployeeShift(rchrShiftgroup, timePunchin);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("Error in Assigning the Shift");
                }
                if (otduration.getHours() <= 4) {
                    atndTmp.setPresent(Boolean.FALSE);
                    present=Boolean.FALSE;
                }
                Date date = new Date(shift.getFromTime().getTime());
                // date.setTime(shiftIntime);
                SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
                String inshiftTime = sdft.format(date);
                String lateDelay = getTimeDuration(inshiftTime, punchIn, punchShift, punchInDemo);
                //***********
                if (lateDelay == null) {
                    // logger.info("result2 is ..."+breakResult);
                    atndTmp.setLateduration(new String("00:00:00"));
                    //atndTmp.setPresent(Boolean.FALSE);
                } else {
                    try {
                        String brres = new String(lateDelay.replaceAll("-", ""));
                        String time1 = "24:00:00";
                        // String date3;
                        String strArry[] = brres.split(":");
                        int hrs = new Integer(strArry[0]).intValue();
                        if (hrs >= 24) {
                            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                            timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            Date date1 = timeFormat.parse(time1);
                            Date date2 = timeFormat.parse(brres);
                            long summ = date2.getTime() - date1.getTime();
                            String date3 = timeFormat.format(new Date(summ));
                            //logger.info("time duration2 is... "+date3);
                            //Date date4 = timeFormat.parse(date3);
                            atndTmp.setLateduration(date3);
                        } else {

                            atndTmp.setLateduration(brres);
                            //atndTmp.setLateduration(new String("00:00:00"));
                        }
                    } catch (ParseException ex) {
                        // ex.printStackTrace();
                    }
                }

                //*************** late
                //int weeklyOff = attnddate.getDay();
                //int empWeek = employeeUtil.getWeeklyOff(emp,tmpdemo.getAttendanceDate());
                if (isWeekOff) {
                    atndTmp.setWeeklyOff(Boolean.TRUE);
                }
                if (result != null && isWeekOff) {
                    atndTmp.setOvertime(Boolean.TRUE);
                    overTime = Boolean.TRUE;
                }

                //String rotatteShift = shiftUtil.getShiftRotation(tmpdemo, emp);

                String staff = new String("Staff");
                String noShift = "ED4817728DD24E86A132094AE5B1DCDE";
                //String semistaff = new String("")

                if (!shiftId.equals(rotatteShift) && !staff.equals(emp.getEmployeeType()) &&
                        result != null
                        && !shiftGroupGeneral.equals(rchrShiftgroup.getId())
                        && !shiftGroup.equals(rchrShiftgroup.getId())) {
                    atndTmp.setOvertime(Boolean.TRUE);
                    overTime = Boolean.TRUE;
                }
                atndTmp.setEmployee(emp);
                tmpdemo.setEmployee(emp);
                tmpdemo.setShift(shift.getName());
                tmpdemo.setDuration(res);
                tmpdemo.setValidate(Boolean.TRUE);
                tmpdemo.setForecastedshift(rotationalShift.getName());
                // atndTmp.set
                updateDayType(present, overTime, atndTmp, tmpdemo, emp);
                atndTmp.setRchrDailyattend(tmpdemo);
                //OBDal.getInstance().save(tmpdemo);
                //logger.info("error process5 ...!!");
                OBDal.getInstance().save(atndTmp);
                //logger.info("ending the process...!!");
                //	info.addResult("inpshiftinmins", getShiftInMinsByInp(info));
                //info.addResult("inpshiftinmins", getShiftInMins(res));
                flag++;
                logger.info("Else Condition");
            }
            return "CP";
        } else {
            tmpdemo.setShift(shift.getName());

            String setDuratoin =res.concat(" (").concat((Integer.valueOf(totalDuration)).toString()).concat(")");
            //logger.info("Minutes of total Duration "+setDuratoin);
            tmpdemo.setDuration(setDuratoin);
            //logger.info("Correct Punch But Shift Minimum Duration is not satisfied");
            tmpdemo.setDiffduration(new Long(difference));
            tmpdemo.setForecastedshift(rotationalShift.getName());
            return "CP but total shift duration is less "+difference +" Mins not OK";
        }
    }

    public void insertOtherAttendLine(RCHRDailyattend tmpdemo, RchrEmployee emp,
                                      String punchIn, String punchOut, String punchOutDemo,
                                      String punchInDemo, String punchShift, Date attnddate,
                                      int flag,RchrShiftgroup rchrShiftgroup,EmployeeUtil employeeUtil,
                                      Boolean isWeekOff,String rotatteShift){

        java.sql.Timestamp timePunchin=null;
        java.sql.Timestamp timePunchout=null;
        try{

            Boolean overTime = Boolean.FALSE;
            Boolean present = Boolean.TRUE;

		RCPRShift rotationalShift = OBDal.getInstance().get(RCPRShift.class, rotatteShift);
            RCHRDailyattenddemo atndTmpOt =  OBProvider.getInstance().get(RCHRDailyattenddemo.class);
            //atndTmpnext
            atndTmpOt.setOrganization(tmpdemo.getOrganization());
            atndTmpOt.setClient(tmpdemo.getClient());
            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
            Date inPunch=sdft.parse(punchIn);
            Date outPunch=sdft.parse(punchOut);
            timePunchin=new java.sql.Timestamp(inPunch.getTime());
            timePunchout=new java.sql.Timestamp(outPunch.getTime());

            atndTmpOt.setAttendanceDate(attnddate);
            atndTmpOt.setErrorLog(tmpdemo.getErrorLog());
            atndTmpOt.setEmployeeId(tmpdemo.getEmployeeId());
            //String rchrEmployeeId=getEmployee(tmpdemo);
            //RchrEmployee empot = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);

            String shiftId = "ED4817728DD24E86A132094AE5B1DCDE";
            try {
                shiftId = this.getEmployeeOTShift(rchrShiftgroup, timePunchout);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error in Assigning the Shift");
            }

            //String shftid=getEmployeeOTShift(rchrShiftgroup, timePunchout);
            RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
            atndTmpOt.setPunchIn(shift.getFromTime());
            atndTmpOt.setPunchOut(timePunchout);
            atndTmpOt.setRcprShift(shift);

            Date date=new Date(shift.getFromTime().getTime());
            //SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");private void deleteFromGrievance(RCHRDailyattend tmpdemo){

            String inshiftTime=sdft.format(date);
            String lateDelay=getTimeDuration(inshiftTime, punchIn, punchShift, punchInDemo);
            //***********
            if(lateDelay==null)
            {
                // logger.info("result2 is ..."+breakResult);
                atndTmpOt.setLateduration(new String("00:00:00"));
                //atndTmp.setPresent(Boolean.FALSE);
            }else{
                try{
                    String brres = new String(lateDelay.replaceAll("-", ""));
                    String time1="24:00:00";
                    // String date3;
                    String strArry[]=brres.split(":");
                    int hrs= new Integer(strArry[0]).intValue();
                    if(hrs>=24){
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                        Date date1 = timeFormat.parse(time1);
                        Date date2 = timeFormat.parse(brres);
                        long summ = date2.getTime() - date1.getTime();
                        String date3 = timeFormat.format(new Date(summ));

                        atndTmpOt.setLateduration(date3);
                    }else{
                        atndTmpOt.setLateduration(brres);
                        //atndTmpOt.setLateduration(new String("00:00:00"));
                    }
                }catch (ParseException ex) {
                    // ex.printStackTrace();
                }
            }

            String shiftIn=shift.getFromTime().toString();
            SimpleDateFormat sdftOt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date punchinOT=sdftOt.parse(shiftIn);
            String otPunchin=sdft.format(punchinOT);
            //String punchinOT=sdft.parse()
            logger.info("tirumal check value for otPunchin for row2 is.."+otPunchin);
            String result=getTimeDuration(otPunchin, punchOut, punchOutDemo, punchInDemo);
            String reslate=null;
            if(lateDelay==null)
            {
                reslate = new String("00:00:00");

            }else{
                reslate = new String(result.replaceAll("-", ""));
            }

            // logger.info("tirumal check value for duration is.. "+resot);

            if(lateDelay==null)
            {
                atndTmpOt.setLateduration(new String("00:00:00"));
                //atndTmpnext.setPresent(Boolean.FALSE);
            }else{
                Date latetime=sdft.parse(reslate);
                if(latetime.getHours()>0 && latetime.getMinutes()>0 && latetime.getSeconds()>0){
                    atndTmpOt.setLateduration(reslate);
                }
            }
            //int weeklyOff=attnddate.getDay();
            //int empWeek=employeeUtil.getWeeklyOff(emp,tmpdemo.getAttendanceDate());
            if(isWeekOff){
                atndTmpOt.setWeeklyOff(Boolean.TRUE);
            }
            if(result!=null && isWeekOff){
                atndTmpOt.setOvertime(Boolean.TRUE);
                overTime = Boolean.TRUE;
            }
            //String rotatteShift= getShiftRotation(tmpdemo,emp);

            String staff=new String ("Staff");
            String semiStaff=new String ("Semi Staff");
            String noShift="ED4817728DD24E86A132094AE5B1DCDE";
            String shiftGroup ="1234FAD6C1FE4DAFBEC11C919AFDD540";
            String shiftGroupGeneral ="1ECFE0E38A7B4A18AD6EA5F0BDC3C6FA";

            if(!shiftId.equals(rotatteShift)  &&
                    !staff.equals(emp.getEmployeeType()) && !shiftGroupGeneral.equals(rchrShiftgroup.getId())
                    && !shiftGroup.equals(rchrShiftgroup.getId()) ){
                atndTmpOt.setOvertime(Boolean.TRUE);
                overTime = Boolean.TRUE;
            }else if(staff.equals(emp.getEmployeeType())
                    || semiStaff.equals(emp.getEmployeeType()) &&
                    !shiftGroup.equals(rchrShiftgroup.getId())){
                atndTmpOt.setOvertime(Boolean.TRUE);
                overTime = Boolean.TRUE;
            }



            atndTmpOt.setEmployee(emp);


            updateDayType(present, overTime, atndTmpOt, tmpdemo, emp);
            tmpdemo.setForecastedshift(OBDal.getInstance().get(RCPRShift.class, rotatteShift).getName());
            tmpdemo.setPresent(present);
            tmpdemo.setValidate(Boolean.TRUE);
            atndTmpOt.setRchrDailyattend(tmpdemo);
		tmpdemo.setForecastedshift(rotationalShift.getName());
            //OBDal.getInstance().save(tmpdemo);
            OBDal.getInstance().save(atndTmpOt);
            flag++;
        }catch (ParseException ex) {
            // ex.printStackTrace();
        }

    }


    private List<RCHRPermission> checkPermission(Date date, RchrEmployee rchrEmployee){
        HqlUtils hqlUtils = new HqlUtils();
        logger.info("Date in Permission "+date + " and Employee "+rchrEmployee);
        List<RCHRPermission> rchrPermissionList = hqlUtils.getEmployeePermission(date, rchrEmployee.getId());
        logger.info("Permission Size is "+rchrPermissionList.size());
        return rchrPermissionList;
    }

    private void deleteFromGrievance(RCHRDailyattend tmpdemo){
        String hql = "DELETE FROM Rcpl_Emppprocessmanual AS emppp WHERE emppp.rchrDailyattend.id='"+tmpdemo.getId()+"' ";
        OBDal.getInstance().getSession().createQuery(hql).executeUpdate();
    }
    private void updatePermission(List<RCHRPermission> rchrPermissionList, RCHRDailyattenddemo atndTmpnext){
        if (rchrPermissionList.size()>0){
            rchrPermissionList.get(0).setRchrDailyattenddemo(atndTmpnext);
            atndTmpnext.setRchrPermission(rchrPermissionList.get(0));
        }
    }
    private void updateDayType(Boolean present, Boolean overTime, RCHRDailyattenddemo atndTmpnext, RCHRDailyattend tmpdemo, RchrEmployee rchrEmployee){
        List<RCHRPermission> rchrPermissionList = checkPermission(atndTmpnext.getAttendanceDate(), rchrEmployee);
        updatePermission(rchrPermissionList, atndTmpnext);
        if(present.equals(Boolean.TRUE) && overTime.equals(Boolean.TRUE)){
            atndTmpnext.setDaytype(RchrConstantType.DAY_TYPE_PROT);
            tmpdemo.setDaytype(RchrConstantType.DAY_TYPE_PROT);
            this.deleteFromGrievance(tmpdemo);

        }

        else if(present.equals(Boolean.TRUE) && overTime.equals(Boolean.FALSE)){
            atndTmpnext.setDaytype(RchrConstantType.DAY_TYPE_PRESENT);
            tmpdemo.setDaytype(RchrConstantType.DAY_TYPE_PRESENT);
            this.deleteFromGrievance(tmpdemo);
        }

        else if(present.equals(Boolean.FALSE) && overTime.equals(Boolean.FALSE)){
            atndTmpnext.setDaytype(RchrConstantType.DAY_TYPE_LOP);
            tmpdemo.setDaytype(RchrConstantType.DAY_TYPE_LOP);
        }
    }

//get employee shift

    public String getEmployeeOTShift(RchrShiftgroup rchrShiftgroup, java.sql.Timestamp timePunchout){
        String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
        for(RchrShiftlines sLine : rchrShiftgroup.getRchrShiftlinesList()){
            String shiftId= sLine.getRcprShift().getId();
            RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);

            if(shift.getToTime().getHours()-1 < timePunchout.getHours() &&
                    shift.getToTime().getHours()+1 > timePunchout.getHours())
            {
                shiftID=shift.getId();
            }
            else if((0 <= timePunchout.getHours() && 3 >= timePunchout.getHours() && shift.getFromTime().getHours() == 15) || (22 <= timePunchout.getHours() && 24 >= timePunchout.getHours()))
            {
                shiftID=shift.getId();
            }

        }
        return shiftID;
    }

//end get emp shift



// end the get shift rotation


    public String getTimeDuration(String strpunchin, String strpunchout,
                                  String strpunchOutDemo, String punchInDemo){
        String result=null;

        try {
            //SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            //Date datein=parseFormat.parse(strinpunch);
            //Date dateout=parseFormat.parse(stroutpunch);
            //String strpunchin=sdf.format(datein);
            //String strpunchout=sdf.format(dateout);
          /*  if(strpunchout.equals(new String("00:00:00"))){
            	strpunchout=strpunchin;
            }*/

            if(strpunchOutDemo==null){
                strpunchout=strpunchin;
            }
            if(punchInDemo==null){
                strpunchin=strpunchout;
            }
            Date dateTo = sdf.parse(strpunchin);
            logger.info("dateTo  is ..."+dateTo);
            Date dateFrom = sdf.parse(strpunchout);
            logger.info("dateFrom  is ..."+dateFrom);
            String str[] = strpunchin.split(":");
            String str1[] = strpunchout.split(":");
            logger.info("str[]  is ..."+str[0]);
            logger.info("str[] length  is ..."+str.length+"--"+str1.length);
            if (str.length == 0 || str1.length == 0) {
                throw new NullPointerException("str is null");
            }
            int hours = new Integer(str[0]).intValue();
            logger.info("hours  is ..."+hours);

            int increment = 0;
            if (hours < 6) {
                increment = -5;
            } else {
                increment = -18;
            }

            Calendar calTo = Calendar.getInstance();
            calTo.setTime(dateTo);
            calTo.add(Calendar.HOUR, increment);
            calTo.add(Calendar.MINUTE, -30);

            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(dateFrom);
            calFrom.add(Calendar.HOUR, increment);
            calFrom.add(Calendar.MINUTE, -30);
            String in = sdf.format(new Date(calTo.getTimeInMillis()));
            logger.info("time in is ..."+in);
            String out = sdf.format(new Date(calFrom.getTimeInMillis()));
            logger.info("time out is ..."+out);
            // if(in !="18:30:00" && out !="18:30:00")

            result = TimeDiffUtil.getworkedhours("13-11-2013", in, out);
            logger.info("result is ..."+result);

        } catch (ParseException ex) {
            // ex.printStackTrace();

        }
        return result;
    }

    public String getPunchoutCshift(RCHRDailyattend tmpdemo, Date attnddate)
    {
        String cPunchout=null;
        try{
            String qrytemp="select rchr_dailyattend_id from rchr_dailyattend where attdate='"+attnddate+"' and stremployee='"+tmpdemo.getEmployeeId()+"'";
            //logger.info("cpunchout method is ..."+qrytemp);
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmttemp = conn.createStatement();
            ResultSet rstemp= stmttemp.executeQuery(qrytemp);


            while (rstemp.next()){
                //logger.info("inside while loop....");
                String demoId = rstemp.getString("rchr_dailyattend_id");
                RCHRDailyattend OutPunchAttndDemo = OBDal.getInstance().get(RCHRDailyattend.class, demoId);

                if(Boolean.TRUE.equals(OutPunchAttndDemo.isActive())){
                    logger.info("inside if of method...");
                    String outpunch = OutPunchAttndDemo.getErrorLog();
                    if(outpunch.isEmpty())
                    {
                        //logger.info("inside if of empty...");
                        outpunch="00:00:00(in),00:00:00(out)";
                    }
                    String[] outtemp;
                    outtemp = outpunch.split(",");
                    // int pnchLngth = outtemp.length;
                    // if(pnchLngth >= 0){

        	/*if(outtemp[0].contains("(in)"))
			   {
 			    outtemp[0]=outtemp[0].replace("(in)", "");
 			    cPunchout=outtemp[0];
			   }*/
                    if(outtemp[0].contains("(out)"))
                    {
                        outtemp[0]=outtemp[0].replace("(out)", "");
                        cPunchout=outtemp[0];
                    }

                    // }
                    logger.info("cpunchout method is ..."+cPunchout);

                }

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return cPunchout;
    }//getpunchoutshift


    public String checkBreakOut(String strpunchout){
/*		String bstarttime = "19:30:00";	//1200 min
		String bendtime = "22:30:00";	//1350 min
		String astarttime = "11:30:00";	//690 min
		String aendtime = "15:00:00";	//870 min */

        String result = "false";
        int astarttime = 690;
        int aendtime = 900;
        int bstarttime = 1170;
        int bendtime = 1350;
        int strbrackout = 0;


        try {
            Date dateFrom = new SimpleDateFormat("HH:mm:ss").parse(strpunchout);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFrom);
            strbrackout = cal.getTime().getHours() * 60 + cal.getTime().getMinutes();

            if(((astarttime <= strbrackout) && (strbrackout <=aendtime)) || ((bstarttime <= strbrackout) && (strbrackout <=bendtime))){
                result = "true";
            }

        }catch (ParseException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
