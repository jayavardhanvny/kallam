package com.rcss.humanresource.util;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStampUtil {
    final static Logger logger = Logger.getLogger(TimeStampUtil.class);
    public Timestamp getPunchTimestamp(Date attendanceDate, String timeString){
        return new Timestamp(getPunchCalendar(attendanceDate,timeString).getTime().getTime());
    }
    public Calendar getPunchCalendar(Date date, String timeString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date parsedTimeFormat = new Date();
        try{
            parsedTimeFormat = simpleDateFormat.parse(timeString);
            logger.info("Parsed Time Date "+parsedTimeFormat);
        }catch (ParseException e){
            e.getErrorOffset();
        }
        Calendar calendarAttendanceDate = Calendar.getInstance();
        calendarAttendanceDate.setTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parsedTimeFormat);
        calendar.set(Calendar.DATE,calendarAttendanceDate.get(Calendar.DATE));
        calendar.set(Calendar.MONTH,calendarAttendanceDate.get(Calendar.MONTH));
        calendar.set(Calendar.YEAR,calendarAttendanceDate.get(Calendar.YEAR));
        return calendar;
    }
    public Timestamp getPunchTimestamp(Date date, Timestamp timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendarTimestamp = Calendar.getInstance();
        calendarTimestamp.setTime(timestamp);
        calendarTimestamp.set(Calendar.DATE,calendar.get(Calendar.DATE));
        calendarTimestamp.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
        calendarTimestamp.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
        return new Timestamp(calendarTimestamp.getTime().getTime());
    }
    public Date getMinusMinutesTime(Timestamp timestamp,int minutes){
        LocalDateTime localDateTime = new LocalDateTime(timestamp.getTime());
       return (localDateTime.minusMinutes(minutes).toDate());
    }
    public Date getPlusMinutesTime(Timestamp timestamp,int minutes){
        LocalDateTime localDateTime = new LocalDateTime(timestamp.getTime());
        return (localDateTime.plusMinutes(minutes).toDate());
    }
public int getShiftInMins(String shftHrs){
        String[] str = shftHrs.split(":");
        int hours = 0;
        int mins = 0;
        int seconds = 0;

        hours = str[0] == null ? 0 : Integer.valueOf(str[0]);
        mins = str[1] == null ? 0 : Integer.valueOf(str[1]);
        seconds = str[2] == null ? 0 : Integer.valueOf(str[2]);

        int result = (hours *60) + (mins * 1) + (seconds/60);
        return result;
    }
}
