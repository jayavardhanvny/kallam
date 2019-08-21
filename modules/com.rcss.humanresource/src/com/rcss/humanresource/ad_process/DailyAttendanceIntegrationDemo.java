package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.ShiftUtil;
import com.rcss.humanresource.util.ShiftUtil;

import com.redcarpet.production.data.RCPRShift;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Calendar;
import java.sql.*;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.timeutils.sam.TimeDiffUtil;
import javax.servlet.ServletException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBDal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;

import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
*
* @author Tirumal
* Modified by Vinay
*/

public class DailyAttendanceIntegrationDemo extends DalBaseProcess {
	protected Logger logger = Logger.getLogger("DailyAttendanceIntegrationDemo.java");
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	
    	
    	Long startBeforeHashMap = System.currentTimeMillis();

    	HashMap<String, RchrEmployee> employeeHashMapList = new EmployeeUtil().getWorkingEmployeesList();
    	

    	System.out.println("HashMap Size " + employeeHashMapList.size());
    	
    	Long endAfterHashMap = System.currentTimeMillis();
 		Long timeHashMap = endAfterHashMap - startBeforeHashMap;
 		
 		long hourHM = TimeUnit.MILLISECONDS.toHours(timeHashMap);
 		long minuteHM = TimeUnit.MILLISECONDS.toMinutes(timeHashMap);
 		long secondHM = TimeUnit.MILLISECONDS.toSeconds(timeHashMap);
 		
 		
 		logger.info("Time to Create HashMap" + hourHM+":"+minuteHM+":"+secondHM+" And "+timeHashMap);
    	
    	//System.out.println("in processss.....!!!");   	
    	//List<RCHRDailyattend> attndList = OBDal.getInstance().createCriteria(RCHRDailyattend.class).list();
    	Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        OBCriteria<RchrAttenddaysmaster> days = OBDal.getInstance().createCriteria(RchrAttenddaysmaster.class);
        
        int day = 0;
        for(RchrAttenddaysmaster master: days.list()){
        	day = master.getDailydayesfirst().intValue();
        }
        
        
        
        Date strtoDate= new Date();
		Date strfromDate= new Date();
		Calendar calfromdate = Calendar.getInstance();
		calfromdate.setTime(strfromDate);
/*		calfromdate.add(Calendar.DATE, -2);*/
		calfromdate.add(Calendar.DATE, -day);
		System.out.println("Processss Days are.....!!!"+day); 
		calfromdate.set(Calendar.HOUR_OF_DAY, 0);
		calfromdate.set(Calendar.MINUTE,0);
		calfromdate.set(Calendar.SECOND,0);
		calfromdate.set(Calendar.MILLISECOND,0);
	    strfromDate=calfromdate.getTime();
	   Calendar caltodate = Calendar.getInstance();   
	   caltodate.setTime(strtoDate);
	   caltodate.set(Calendar.HOUR_OF_DAY, 0);
	   caltodate.set(Calendar.MINUTE,0);
	   caltodate.set(Calendar.SECOND,0);
	   caltodate.set(Calendar.MILLISECOND,0);
	   strtoDate=caltodate.getTime();
	    
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String toDate=sdf.format(strtoDate);
		String fromDate=sdf.format(strfromDate);
        //stmt.executeUpdate("delete from rchr_dailyattenddemo where attdate between '"+fromDate+"' and '"+toDate+"'");
        //stmt.executeUpdate("delete from rchr_dailyattenddemo where attdate between '2017-06-01' and '2017-06-09'");
        //stmt.executeUpdate("delete from RCHR_Dailyattend where attdate between '"+fromDate+"' and '"+toDate+"'");
        //and stremployee='3095' 
        //String setFlag="N";
        ResultSet rs = stmt.executeQuery("select rchr_dailyattend_id from rchr_dailyattend where isactive='Y' and validate='N' " +
				//"and attdate>='2017-11-16'" +
				"and attdate between '"+fromDate+"' and '"+toDate+"' order by stremployee,attdate");
        //ResultSet rs = stmt.executeQuery(" select rchr_attend_tempdemo_id from rchr_attend_tempdemo where attdate between '"+fromDate+"' and '"+toDate+"'");
        int flag=0;
        
        Long startBeforeWhile = System.currentTimeMillis();


        while (rs.next()) {
            String demoId = rs.getString("rchr_dailyattend_id");
            //String demoId = rs.getString("rchr_attend_tempdemo_id");
           // RCHRDailyattend tmpdemo = OBDal.getInstance().get(RCHRDailyattend.class, demoId);
            RCHRDailyattend tmpdemo = OBDal.getInstance().get(RCHRDailyattend.class, demoId);
			// Boolean validate=new Boolean(Boolean.FALSE);

			//if(tmpdemo.getErrorLog().length()>4) {
				if (employeeHashMapList.containsKey(tmpdemo.getEmployeeId())) {
					RchrEmployee emp = (RchrEmployee) employeeHashMapList.get(tmpdemo.getEmployeeId());

					Boolean validate = tmpdemo.isValidate();


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
					System.out.println("length is.." + lngth);
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
							//System.out.println(i+"th elementis.."+temp[i]);
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
								System.out.println("nextpunchIn is.." + nextpunchIn);
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

						//System.out.println("user is absent..");
						absent = "absent";
					}

					// Start next day c-shift late punch ...

					SimpleDateFormat sdfrmt = new SimpleDateFormat("HH:mm:ss");
					// String punchOutDummy=null;

					Date tempin = sdfrmt.parse(punchIn);
					Date tempout = sdfrmt.parse(punchOut);
					Timestamp timetempin = new Timestamp(tempin.getTime());
					Timestamp timetempout = new Timestamp(tempout.getTime());
					Date attnddate = tmpdemo.getAttendanceDate();

	             /*
			   if((timetempin.getHours() >=0 && timetempin.getMinutes() >=0 && timetempin.getSeconds() >=1) && (timetempin.getHours() <= 2))
			   {
				   Calendar caltemp = Calendar.getInstance();
				   caltemp.setTime(attnddate);
				   caltemp.add(Calendar.DATE, -1);
				   attnddate=caltemp.getTime();
				  //getPunchoutCshift(tmpdemo,attnddate);
				   
			   } 
			  
				   */

					//get the c-shift and blr c-shift Out punch ...
					Date tempnextin = sdfrmt.parse(nextpunchIn);
					Timestamp timenextpunchIn = new Timestamp(tempnextin.getTime());
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
					RCHRDailyattenddemo atndTmp = OBProvider.getInstance().get(RCHRDailyattenddemo.class);

					String result = getTimeDuration(punchIn, punchOut, punchOutDemo, punchInDemo);
					String res;

					if (result == null) {
						res = new String("00:00:00");

					} else {
						res = new String(result.replaceAll("-", ""));
					}

					// Two lines will Update IF OT

					Date duration;
					Timestamp otduration = null;
					Timestamp timePunchin = null;
					Timestamp timePunchout = null;
					try {
						SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
						duration = sdft.parse(res);
						otduration = new Timestamp(duration.getTime());
					} catch (ParseException ex) {
						// ex.printStackTrace();
					}
					String shiftGroup = "1234FAD6C1FE4DAFBEC11C919AFDD540";
					String shiftGroupGeneral = "1ECFE0E38A7B4A18AD6EA5F0BDC3C6FA";
					//String rchrEmployeeId=getEmployee(tmpdemo);
					//RchrEmployee emp =
					ShiftUtil shiftUtil = new ShiftUtil();
					if ((otduration.getHours() >= 14) && otduration.getHours() <= 18 &&
							!shiftGroupGeneral.equals(emp.getRchrShiftgroup().getId())
							&& !shiftGroup.equals(emp.getRchrShiftgroup().getId())) {
						// try {
						RCHRDailyattenddemo atndTmpnext = OBProvider.getInstance().get(RCHRDailyattenddemo.class);
						try {
							SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
							Date inPunch = sdft.parse(punchIn);
							Date outPunch = sdft.parse(punchOut);
							timePunchin = new Timestamp(inPunch.getTime());
							timePunchout = new Timestamp(outPunch.getTime());

							atndTmpnext.setOrganization(tmpdemo.getOrganization());
							atndTmpnext.setClient(tmpdemo.getClient());
							atndTmpnext.setAttendanceDate(attnddate);
							atndTmpnext.setErrorLog(tmpdemo.getErrorLog());
							atndTmpnext.setEmployeeId(tmpdemo.getEmployeeId());
							//String rchrEmployeeId=getEmployee(tmpdemo);
							//RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);

							//RchrShiftgroup rchrShiftgroup = shiftUtil.getShiftGroup(emp,tmpdemo.getAttendanceDate());
							//String shftid = shiftUtil.getEmployeeShift(emp, timePunchin,tmpdemo,rchrShiftgroup);

							String shftid = getEmployeeShift(tmpdemo, emp, timePunchin, timePunchout);
							RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shftid);
							atndTmpnext.setPunchIn(timePunchin);
							atndTmpnext.setPunchOut(shift.getToTime());
							atndTmpnext.setRcprShift(shift);
							// "2015-08-24 19:00:00"
							String shiftOut = shift.getToTime().toString();
							SimpleDateFormat sdftOt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date punchoutOT = sdftOt.parse(shiftOut);
							String otPunchout = sdft.format(punchoutOT);
							System.out.println("tirumal check value for otPunchout is.." + otPunchout);
							String resultot = getTimeDuration(punchIn, otPunchout, punchOutDemo, punchInDemo);
							String resot;
							if (resultot == null) {
								resot = new String("00:00:00");

							} else {
								resot = new String(resultot.replaceAll("-", ""));
							}

							System.out.println("tirumal check value for duration is.. " + resot);

							if (resultot == null) {
								atndTmpnext.setDuration(new String("00:00:00"));
								atndTmpnext.setPresent(Boolean.FALSE);
							} else {
								atndTmpnext.setDuration(resot);
							}
							int weeklyOff = attnddate.getDay();
							int empWeek = getWeeklyOff(emp,tmpdemo);
							if (weeklyOff == empWeek) {
								atndTmpnext.setWeeklyOff(Boolean.TRUE);
							}
							if (result != null && weeklyOff == empWeek) {
								atndTmpnext.setOvertime(Boolean.TRUE);
							}






							String rotatteShift = shiftUtil.getShiftRotation(tmpdemo, emp);
							String staff = new String("Staff");
							String noShift = "ED4817728DD24E86A132094AE5B1DCDE";

							if (!shftid.equals(rotatteShift) && !shftid.equals(noShift) && !staff.equals(emp.getEmployeeType()) && !shiftGroupGeneral.equals(emp.getRchrShiftgroup().getId())
									&& !shiftGroup.equals(emp.getRchrShiftgroup().getId())) {
								atndTmpnext.setOvertime(Boolean.TRUE);
							}
							atndTmpnext.setEmployee(emp);
							atndTmpnext.setRchrDailyattend(tmpdemo);
							tmpdemo.setValidate(Boolean.TRUE);
							//OBDal.getInstance().save(tmpdemo);
							OBDal.getInstance().save(atndTmpnext);
							flag++;
							insertOtherAttendLine(tmpdemo, emp, punchIn, punchOut, punchOutDemo,
									punchInDemo, punchShift, attnddate, flag,shiftUtil);
						} catch (ParseException ex) {
							// ex.printStackTrace();
						}
					} else {
						//------- END Two lines will Update IF OT---------
						//-------------
						if (result == null) {
							// System.out.println("result1 is ..."+result);
							atndTmp.setDuration(new String("00:00:00"));
							atndTmp.setPresent(Boolean.FALSE);
						} else {
							//System.out.println("time duration1 is ..."+res);
							atndTmp.setDuration(res);
						}
						if (otduration.getHours() <= 4) {
							atndTmp.setPresent(Boolean.FALSE);
						}

						try {
							String breakResult = getTimeDuration(breakOut, breakIn, absent, absent);
							if (breakResult == null) {
								System.out.println("result2 is ..." + breakResult);
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
									System.out.println("time duration2 is... " + date3);
									atndTmp.setShift(date3);
								} else {
									System.out.println("time duration2 is ... " + brres);
									atndTmp.setShift(brres);
								}
							}

							//System.out.println("error process1 ...!!");
							atndTmp.setOrganization(tmpdemo.getOrganization());
							atndTmp.setClient(tmpdemo.getClient());
							// System.out.println("error process2 ...!!");

							SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
							Date inPunch = sdft.parse(punchIn);
							Date outPunch = sdft.parse(punchOut);
							timePunchin = new Timestamp(inPunch.getTime());
							timePunchout = new Timestamp(outPunch.getTime());
							atndTmp.setPunchIn(timePunchin);
							atndTmp.setPunchOut(timePunchout);

							Date inBreak = sdft.parse(breakIn);
							Date outBreak = sdft.parse(breakOut);
							Timestamp timeBreakin = new Timestamp(inBreak.getTime());
							Timestamp timeBreakout = new Timestamp(outBreak.getTime());
							atndTmp.setBreakin(timeBreakin);
							//atndTmp.set
							atndTmp.setBreakout(timeBreakout);
						} catch (ParseException ex) {
							// ex.printStackTrace();
						}

						//
						atndTmp.setAttendanceDate(attnddate);
						atndTmp.setErrorLog(tmpdemo.getErrorLog());
						// System.out.println("error process4 ...!!");
						atndTmp.setEmployeeId(tmpdemo.getEmployeeId());
						// try{
						//String rchrEmployeeId=getEmployee(tmpdemo);
						System.out.println(" punch no and name is for group..." + tmpdemo.getEmployeeId());

						// RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);
						System.out.println(" employee no and name is for group..." + emp.getDocumentNo() + " " + emp.getEmployeeName());


						//RchrShiftgroup rchrShiftgroup = shiftUtil.getShiftGroup(emp,tmpdemo.getAttendanceDate());
						//String shftid = shiftUtil.getEmployeeShift(emp, timePunchin,tmpdemo,rchrShiftgroup);


						String shftid = getEmployeeShift(tmpdemo, emp, timePunchin, timePunchout);
						RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, shftid);
						atndTmp.setRcprShift(shift);
	            /* String shiftgroID = emp.getRchrShiftgroup().getId();
	             RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);

	            // shiftid=getEmployeeShift(tmpdemo);
	             for(RchrShiftlines sLine : shiftGroup.getRchrShiftlinesList()){
	            	 String shiftId= sLine.getRcprShift().getId();
	            	 RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
	            	 java.sql.Timestamp shiftInTime=shift.getFromTime();
	            	 java.sql.Timestamp shiftOutTime=shift.getToTime();
	            	 if(shift.getFromTime().getHours()-1 < timePunchin.getHours() && shift.getToTime().getHours()-3 > timePunchin.getHours())
	            	 {
	            		 atndTmp.setRcprShift(shift);
	            		 shftid=shift.getId();
	            	 }
	            	 else if((0 <= timePunchin.getHours() && 3 >= timePunchin.getHours()) || (22 <= timePunchin.getHours() && 24 >= timePunchin.getHours()))
	            	 {
	            		 atndTmp.setRcprShift(shift);
	            		 shftid=shift.getId();
	            	 }

	             }*/
						//******** late
						//timePunchin=new java.sql.Timestamp(inPunch.getTime());
						//timePunchout
						//java.sql.Timestamp shiftIntime=shift.getFromTime();
						Date date = new Date(shift.getFromTime().getTime());
						// date.setTime(shiftIntime);
						SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
						String inshiftTime = sdft.format(date);
						String lateDelay = getTimeDuration(inshiftTime, punchIn, punchShift, punchInDemo);
						//***********
						if (lateDelay == null) {
							// System.out.println("result2 is ..."+breakResult);
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
									//System.out.println("time duration2 is... "+date3);
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
						int weeklyOff = attnddate.getDay();
						int empWeek = getWeeklyOff(emp,tmpdemo);
						if (weeklyOff == empWeek) {
							atndTmp.setWeeklyOff(Boolean.TRUE);
						}
						if (result != null && weeklyOff == empWeek) {
							atndTmp.setOvertime(Boolean.TRUE);
						}

						String rotatteShift = shiftUtil.getShiftRotation(tmpdemo, emp);
						String staff = new String("Staff");
						String noShift = "ED4817728DD24E86A132094AE5B1DCDE";
						//String semistaff = new String("")
						if (!shftid.equals(rotatteShift) && !staff.equals(emp.getEmployeeType()) && !shftid.equals(noShift) && result != null && !shiftGroupGeneral.equals(emp.getRchrShiftgroup().getId())
								&& !shiftGroup.equals(emp.getRchrShiftgroup().getId())) {
							atndTmp.setOvertime(Boolean.TRUE);
						}
						atndTmp.setEmployee(emp);
						tmpdemo.setValidate(Boolean.TRUE);
						// atndTmp.set
						atndTmp.setRchrDailyattend(tmpdemo);
						OBDal.getInstance().save(tmpdemo);
						System.out.println("error process5 ...!!");
						OBDal.getInstance().save(atndTmp);
						System.out.println("ending the process...!!");
						//	info.addResult("inpshiftinmins", getShiftInMinsByInp(info));
						//info.addResult("inpshiftinmins", getShiftInMins(res));
						flag++;
						//}
	           /*  }//try
	             catch(Exception e)
	     		{
	     			e.printStackTrace();
	     		}*/
					}
				} else {
					logger.info("Employee Not Present in Master");
				}

		
    }//while
    System.out.println("total updated lines.. "+flag);
    Long endAfterWhile = System.currentTimeMillis();
	Long time = endAfterWhile - startBeforeWhile;
	
	long hour = TimeUnit.MILLISECONDS.toHours(time);
	long minute = TimeUnit.MILLISECONDS.toMinutes(time);
	
	long second = TimeUnit.MILLISECONDS.toSeconds(time);
	System.out.println("Time is " + hour+":"+minute+":"+second);
}//doexcute
 
//Method defination oF Insert another line for OT

public void insertOtherAttendLine(RCHRDailyattend tmpdemo, RchrEmployee emp,
								  String punchIn, String punchOut, String punchOutDemo,
								  String punchInDemo, String punchShift, Date attnddate, int flag,ShiftUtil shiftUtil){
	
	   Timestamp timePunchin=null;
  	   Timestamp timePunchout=null;
	try{
	
		RCHRDailyattenddemo atndTmpOt =  OBProvider.getInstance().get(RCHRDailyattenddemo.class);
	//atndTmpnext
		atndTmpOt.setOrganization(tmpdemo.getOrganization());
		atndTmpOt.setClient(tmpdemo.getClient());
      SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
      Date inPunch=sdft.parse(punchIn);
      Date outPunch=sdft.parse(punchOut);
      timePunchin=new Timestamp(inPunch.getTime());
      timePunchout=new Timestamp(outPunch.getTime());
	    
      atndTmpOt.setAttendanceDate(attnddate);
      atndTmpOt.setErrorLog(tmpdemo.getErrorLog());
      atndTmpOt.setEmployeeId(tmpdemo.getEmployeeId());
      //String rchrEmployeeId=getEmployee(tmpdemo);
      //RchrEmployee empot = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);
      String shftid=getEmployeeOTShift(tmpdemo, emp, timePunchin, timePunchout);
      RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shftid);
      
      atndTmpOt.setPunchIn(shift.getFromTime());
      atndTmpOt.setPunchOut(timePunchout);
      atndTmpOt.setRcprShift(shift);
      
      Date date=new Date(shift.getFromTime().getTime());
      //SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
      String inshiftTime=sdft.format(date);
      String lateDelay=getTimeDuration(inshiftTime, punchIn, punchShift, punchInDemo);
      //***********
      if(lateDelay==null)
      {
     // System.out.println("result2 is ..."+breakResult);
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
      System.out.println("tirumal check value for otPunchin for row2 is.."+otPunchin);
      String result=getTimeDuration(otPunchin, punchOut, punchOutDemo, punchInDemo);
      String reslate=null;
      if(lateDelay==null)
      {
     	 reslate = new String("00:00:00");
      
      }else{
     	 reslate = new String(result.replaceAll("-", ""));
      }
      
     // System.out.println("tirumal check value for duration is.. "+resot);
      
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
      int weeklyOff=attnddate.getDay();
      int empWeek=getWeeklyOff(emp,tmpdemo);
      if(weeklyOff==empWeek){
    	  atndTmpOt.setWeeklyOff(Boolean.TRUE); 
      }
       if(result!=null && weeklyOff==empWeek){
    	   atndTmpOt.setOvertime(Boolean.TRUE);  
       }


      String rotatteShift=shiftUtil.getShiftRotation(tmpdemo,emp);
      String staff=new String ("Staff");
      String semiStaff=new String ("Semi Staff");
      String noShift="ED4817728DD24E86A132094AE5B1DCDE";
      String shiftGroup ="1234FAD6C1FE4DAFBEC11C919AFDD540";
		String shiftGroupGeneral ="1ECFE0E38A7B4A18AD6EA5F0BDC3C6FA";
	  if(!shftid.equals(rotatteShift) && !shftid.equals(noShift) && !staff.equals(emp.getEmployeeType()) && !shiftGroupGeneral.equals(emp.getRchrShiftgroup().getId())
			  && !shiftGroup.equals(emp.getRchrShiftgroup().getId()) ){
    	  atndTmpOt.setOvertime(Boolean.TRUE); 
      }else if(staff.equals(emp.getEmployeeType()) || semiStaff.equals(emp.getEmployeeType()) && !shiftGroup.equals(emp.getRchrShiftgroup().getId())){
    	  atndTmpOt.setOvertime(Boolean.TRUE); 
      }
      atndTmpOt.setEmployee(emp);
      tmpdemo.setValidate(Boolean.TRUE); 
      //OBDal.getInstance().save(tmpdemo);
		atndTmpOt.setRchrDailyattend(tmpdemo);
      OBDal.getInstance().save(atndTmpOt);
      flag++;
	 }catch (ParseException ex) {
          // ex.printStackTrace();
       }
	
}

public int getWeeklyOff(RchrEmployee emp,RCHRDailyattend tmpdemo){
	int weekNo=8;
	String weekOff="N/A";

	if(emp.getWeeklyOff()==null){
		weekOff="N/A";
	}else{
	 weekOff=emp.getWeeklyOff();
		List li = this.getWeeklyOffDetails(tmpdemo, emp);
		if(li.size()>=1){
			// System.out.println("In If condition ");
			weekOff = li.get(0).toString();
			System.out.println("weekOff History "+weekOff);
		}
	}
	if(weekOff.contains("SUNDAY")){
		weekNo=0;
	}else if(weekOff.contains("MONDAY")){
		weekNo=1;
	}else if(weekOff.contains("TUESDAY")){
		weekNo=2;
	}else if(weekOff.contains("WEDNESDAY")){
		weekNo=3;
	}else if(weekOff.contains("THURSDAY")){
		weekNo=4;
	}else if(weekOff.contains("FRIDAY")){
		weekNo=5;
	}else if(weekOff.contains("SATURDAY")){
		weekNo=6;
	}else{
		weekNo=7;
	}
	return weekNo;
}

// get Weekly Off
private List getWeeklyOffDetails(RCHRDailyattend tmpdemo, RchrEmployee emp){

	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
	String hql = "SELECT aline.currentweeklyoff from RCHR_WeeklyOffDetails AS aline "
			+ " WHERE aline.employee.id = '" + emp.getId()+ "' "
			+ " AND aline.effectivedate <= '" + sdf.format(tmpdemo.getAttendanceDate()) +
			"' AND aline.effectivetodate >= '" + sdf.format(tmpdemo.getAttendanceDate()) + "' "
			+ " AND 1 = 1";
	Session session = OBDal.getInstance().getSession();
	List li = session.createQuery(hql).list();
	System.out.println("Size Of Weekly Offs is  "+li.size());
	return li;
}



//get employee shift

public String getEmployeeShift(RCHRDailyattend tmpdemo, RchrEmployee emp, Timestamp timePunchin,
							   Timestamp timePunchout){
	String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
	 String shiftgroID = emp.getRchrShiftgroup().getId();
	 System.out.println(" get shift employee no and name is "+emp.getDocumentNo()+" "+emp.getEmployeeName()+" date is "+tmpdemo.getAttendanceDate());
	 RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
       for(RchrShiftlines sLine : emp.getRchrShiftgroup().getRchrShiftlinesList()){
         
         String shiftId= sLine.getRcprShift().getId();
         RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
         if(sLine.getRcprShift().getFromTime().getHours()-1 <= timePunchin.getHours() && sLine.getRcprShift().getToTime().getHours()-3 > timePunchin.getHours())
         {
                 shiftID=sLine.getRcprShift().getId();
         }
         else if((0 <= timePunchin.getHours() && 3 >= timePunchin.getHours()) || (22 <= timePunchin.getHours() && 24 >= timePunchin.getHours()))
         {
                 shiftID=sLine.getRcprShift().getId();
         }
         else if(19 <= timePunchin.getHours() && 20 >= timePunchin.getHours())
         {
                 shiftID=sLine.getRcprShift().getId();
         }
         
     }
     
     return shiftID;
}
public String getEmployeeOTShift(RCHRDailyattend tmpdemo, RchrEmployee emp, Timestamp timePunchin, Timestamp timePunchout){
	String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
	 String shiftgroID = emp.getRchrShiftgroup().getId();
     RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
     for(RchrShiftlines sLine : shiftGroup.getRchrShiftlinesList()){
    	 String shiftId= sLine.getRcprShift().getId();
    	 RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
    	 Timestamp shiftInTime=shift.getFromTime();
    	 Timestamp shiftOutTime=shift.getToTime();
    	 if(shift.getToTime().getHours()-1 < timePunchout.getHours() && shift.getToTime().getHours()+1 > timePunchout.getHours())
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
/*
// get shift rotation..
private List getShiftMemo(RCHRDailyattend tmpdemo, RchrEmployee emp){
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    String hql = "SELECT newshift.id from Rchr_Memo_Shift AS aline "
            + " WHERE aline.employee.id = '" + emp.getId()+ "' " 
            + " AND aline.memfromdate <= '" + sdf.format(tmpdemo.getAttendanceDate()) + "' AND aline.memtodate >= '" + sdf.format(tmpdemo.getAttendanceDate()) + "' "
            + " AND 1 = 1";

    Session session = OBDal.getInstance().getSession();
    List li = session.createQuery(hql).list();
    
    
    System.out.println("Size is 2 "+li.size());
    return li;
}


    public String getShiftRotation(RCHRDailyattend tmpdemo, RchrEmployee emp){
        String shiftId=null;
        List li = getShiftMemo(tmpdemo, emp);
        ShiftUtil shiftUtil = new ShiftUtil();
        String shiftRelay = shiftUtil.getShiftRelay(emp,tmpdemo.getAttendanceDate());
        String mrelayId = "";
        List listRelayId=getRelayIdByName(shiftRelay);
        if(li.size()>=1){
         // System.out.println("In If condition ");
            shiftId = li.get(0).toString();
            System.out.println("shiftID 1 "+shiftId);

        }else if(listRelayId.size()>=1){
            logger.info("shiftRelay Name"+shiftRelay);
                mrelayId = (String) listRelayId.get(0);
                String hql = " SELECT ess FROM Rchr_Relay ess "
                        + " WHERE ess.rchrMrelay.id='" + mrelayId + "' "
                        + " AND ess.fromdate <='" + tmpdemo.getAttendanceDate() + "' "
                        + " AND ess.todate >='" + tmpdemo.getAttendanceDate() + "' "
                        + " AND ess.active=true";
                List<RchrRelay> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();

                for(RchrRelay rly : rlyList){
                    shiftId= rly.getShift().getId();
                }

        }else {
            mrelayId=emp.getRelay().getId();
            String hql = " SELECT ess FROM Rchr_Relay ess "
                    + " WHERE ess.rchrMrelay.id='" + mrelayId + "' "
                    + " AND ess.fromdate <='" + tmpdemo.getAttendanceDate() + "' "
                    + " AND ess.todate >='" + tmpdemo.getAttendanceDate() + "' "
                    + " AND ess.active=true";
            List<RchrRelay> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();

            for(RchrRelay rly : rlyList){
                shiftId= rly.getShift().getId();
            }
        }

        return shiftId;
    }


    private List getRelayIdByName(String name){
        String hql = " SELECT ess.id FROM Rchr_MRelay ess "
                + " WHERE ess.commercialName='" + name + "' "
                + " AND ess.active=true";
        List<String> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();
        return rlyList;
    }

    // end the get shift rotation

    public String getEmployee(RCHRDailyattend tmpdemo){

        String rchrEmployeeId=null;
        try{
             Connection conn = OBDal.getInstance().getConnection();
             Statement stmt1 = conn.createStatement();
             String sqry="select rchr_employee_id from rchr_employee where punchno='"+tmpdemo.getEmployeeId()+"'";
             ResultSet res1 = stmt1.executeQuery(sqry);

             while(res1.next())
             {
                 rchrEmployeeId=res1.getString("rchr_employee_id");

             }


    }catch(Exception ex){
        ex.printStackTrace();
    }
     return rchrEmployeeId;
        }
    */
public String getTimeDuration(String strpunchin, String strpunchout, String strpunchOutDemo, String punchInDemo){ 
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
            System.out.println("dateTo  is ..."+dateTo); 
            Date dateFrom = sdf.parse(strpunchout);
            System.out.println("dateFrom  is ..."+dateFrom);
            String str[] = strpunchin.split(":");
            String str1[] = strpunchout.split(":");
            System.out.println("str[]  is ..."+str[0]);
            System.out.println("str[] length  is ..."+str.length+"--"+str1.length);          
            if (str.length == 0 || str1.length == 0) {
                throw new NullPointerException("str is null");
            }
            int hours = new Integer(str[0]).intValue();
            System.out.println("hours  is ..."+hours);
            
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
            System.out.println("time in is ..."+in);
            String out = sdf.format(new Date(calFrom.getTimeInMillis()));
            System.out.println("time out is ..."+out);
           // if(in !="18:30:00" && out !="18:30:00")
            
            result = TimeDiffUtil.getworkedhours("13-11-2013", in, out);
            System.out.println("result is ..."+result);
            
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
	//System.out.println("cpunchout method is ..."+qrytemp);
	Connection conn = OBDal.getInstance().getConnection();
	Statement stmttemp = conn.createStatement();
    ResultSet rstemp= stmttemp.executeQuery(qrytemp);
    
    
    while (rstemp.next()){
    	//System.out.println("inside while loop....");
    	String demoId = rstemp.getString("rchr_dailyattend_id");
        RCHRDailyattend OutPunchAttndDemo = OBDal.getInstance().get(RCHRDailyattend.class, demoId);
        
        if(Boolean.TRUE.equals(OutPunchAttndDemo.isActive())){
        	System.out.println("inside if of method...");
        String outpunch = OutPunchAttndDemo.getErrorLog();
        if(outpunch.isEmpty())
		 {  
        	//System.out.println("inside if of empty...");
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
        	System.out.println("cpunchout method is ..."+cPunchout);
        
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

}//main class