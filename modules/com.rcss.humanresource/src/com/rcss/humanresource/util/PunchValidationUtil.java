package com.rcss.humanresource.util;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PunchValidationUtil {
    final static Logger logger = Logger.getLogger(PunchValidationUtil.class);
    private ArrayList<String> stringArrayList = null;
    public static String outPunch="(out)";
    public static String inPunch="(in)";
    public static String defaultPunch="00:00:00";
    public PunchValidationUtil(String punchRecordsParameter){
        if((!punchRecordsParameter.equals("")) && (punchRecordsParameter!=null))
            this.addPunchesToArrayList(punchRecordsParameter);
        else {
            logger.info("Punch is Null ");
        }
    }
    private ArrayList<String> arrayListInPunch = null;
    private ArrayList<String> arrayListOutPunch = null;
    private String punch = "";
    private int index = 0;
    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return index;
    }
    public void setPunch(String punch){
        this.punch=punch;
    }
    public String getPunch(){
        return punch;
    }
    public void addPunchesToArrayList(String punchRecordsParameter){
        StringTokenizer punchRecordsStringTokenizer = new StringTokenizer(punchRecordsParameter, ",");
        stringArrayList = new ArrayList<String>();
        while(punchRecordsStringTokenizer.hasMoreTokens()){
            this.stringArrayList.add(punchRecordsStringTokenizer.nextToken());
        }
    }

    public void setArrayListInPunch(ArrayList<String> arrayListInPunch) {
        this.arrayListInPunch = arrayListInPunch;
    }

    public void setArrayListOutPunch(ArrayList<String> arrayListOutPunch) {
        this.arrayListOutPunch = arrayListOutPunch;
    }

    public ArrayList<String> getArrayListOutPunch() {
        return arrayListOutPunch;
    }

    public ArrayList<String> getArrayListInPunch() {
        return arrayListInPunch;
    }

    public void setStringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }
    public ArrayList<String> getStringArrayListPunches(){
        return this.stringArrayList;
    }
    public Boolean isLastOutPunch(){
        return stringArrayList.get(stringArrayList.size()-1).contains(outPunch);
    }
    public Boolean isFirstInPunch(){
        return stringArrayList.get(0).contains(inPunch);
    }

    public String getFirstInPuchTimeString(){
        if(stringArrayList.get(0).contains(inPunch)){
            return stringArrayList.get(0).replace(inPunch,"");
        }
        return defaultPunch;
    }
    public String getLastOutPunchTimeString(){
        if(stringArrayList.get(stringArrayList.size()-1).contains(outPunch)){
            return stringArrayList.get(stringArrayList.size()-1).replace(outPunch,"");
        }
        return defaultPunch;
    }
    public Boolean punchSequenceFollows() {
        Boolean parity = Boolean.TRUE;
        String punchString = "";
        ArrayList<String> stringArrayList =  this.getStringArrayListPunches();
        //logger.info("Array List size "+stringArrayList.size());
        int i = 1;
        Iterator<String> stringIterator = stringArrayList.iterator();
        //arrayListInPunch = new ArrayList<String>();
        //arrayListOutPunch = new ArrayList<String>();
        while (stringIterator.hasNext()) {
            //logger.info("IN While i value "+i);
            if ((i % 2 == 1) &&
                    (!stringIterator.next().contains("(in)"))) {
                punchString = "out";
                parity=Boolean.FALSE;
                //arrayListInPunch.add(stringIterator.next().replace(inPunch,""));
                break;
            }
            if ((i % 2 == 0) &&
                    (!stringIterator.next().contains("(out)"))) {
                punchString = "in";
                parity=Boolean.FALSE;
                //arrayListOutPunch.add(stringIterator.next().replace(outPunch,""));
                break;
            }
            i++;
        }
        this.setIndex((i));
        this.setPunch(punchString);
        return parity;
    }
	public Timestamp getPunchTimeStamp(Date attendanceDate, String timeString, String punchType){
        TimeStampUtil timeStampUtil = new TimeStampUtil();
        Calendar calendar = timeStampUtil.getPunchCalendar(attendanceDate, timeString);
        if(calendar.getTime().getHours()>=0 && punchType.equals(outPunch)){
            calendar.add(Calendar.DATE,1);
        }
        return new Timestamp(calendar.getTime().getTime());
    }
    public Timestamp getPunchTimeStamp(Date attendanceDate, String timeString){
        TimeStampUtil timeStampUtil = new TimeStampUtil();
        Calendar calendar = timeStampUtil.getPunchCalendar(attendanceDate, timeString);
        return new Timestamp(calendar.getTime().getTime());
    }
public java.sql.Timestamp getPunchTimeStamp(String punchTime) {
        java.sql.Timestamp timePunchin = null;
        try {
            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
            timePunchin = new java.sql.Timestamp(sdft.parse(punchTime).getTime());
        } catch (ParseException ex) {
            logger.error("Error in Parse Exception in PunchValidation "+ ex.getMessage());
        }
        return timePunchin;
    }
}
