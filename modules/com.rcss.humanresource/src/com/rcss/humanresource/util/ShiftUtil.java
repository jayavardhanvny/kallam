package com.rcss.humanresource.util;

import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.util.PayrollUtils;
import com.redcarpet.production.data.RCPRShift;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ShiftUtil {
    final static Logger logger = Logger.getLogger(ShiftUtil.class);

    public HashMap<String,RCPRShift> getActiveShifts(){
        OBCriteria<RCPRShift> rcprShiftOBCriteria = OBDal.getInstance().createCriteria(RCPRShift.class);
        rcprShiftOBCriteria.add(Restrictions.eq(RCPRShift.PROPERTY_ACTIVE,Boolean.TRUE));
        HashMap<String, RCPRShift> rcprShiftHashMap = new HashMap<String, RCPRShift>();
        for(RCPRShift rcprShift : rcprShiftOBCriteria.list()){
            rcprShiftHashMap.put(rcprShift.getId(), rcprShift);
        }
        return rcprShiftHashMap;
    }



    public String getEmployeeShift(RchrEmployee emp,
                                   java.sql.Timestamp timePunchin,RCHRDailyattend rchrDailyattend,
                                   RchrShiftgroup rchrShiftgroup){
        TimeStampUtil timeStampUtil = new TimeStampUtil();
        String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
        //String shiftgroID = emp.getRchrShiftgroup().getId();
        //logger.info(" get shift employee no and name is "+emp.getDocumentNo()+" "+emp.getEmployeeName());
        //RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
       if(rchrShiftgroup.getShifttype().equals(RchrConstantType.SHIFT_TYPE_REGULAR)){

            List li = this.getShiftMemo(rchrDailyattend.getAttendanceDate(), emp);
            if(li.size()>=1) {
                // System.out.println("In If condition ");
                System.out.println("shiftID 1 " + shiftID);
                RCPRShift rcprShift = OBDal.getInstance().get(RCPRShift.class,shiftID);

                Timestamp shiftMemoTimeStamp =timeStampUtil.getPunchTimestamp(new Date(timePunchin.getTime()),rcprShift.getFromTime());
                Date shiftMemoActualFromDateTime = timeStampUtil.getMinusMinutesTime(shiftMemoTimeStamp,
                        rcprShift.getPunchbeginbefore().intValue());
                Date shiftMemoActualToDateTime = timeStampUtil.getPlusMinutesTime(shiftMemoTimeStamp,
                        rcprShift.getInpunchgrace().intValue());
                if(shiftMemoActualFromDateTime.getHours()
                        <= timePunchin.getHours() &&
                        shiftMemoActualToDateTime.getHours() > timePunchin.getHours()) {
                    shiftID = li.get(0).toString();
                    //logger.info("In Shift Success "+actualToDateTime);
                }
            }else{
                for(RchrShiftlines sLine : rchrShiftgroup.getRchrShiftlinesList()){
                    Timestamp shiftTimestamp =  timeStampUtil.getPunchTimestamp(new Date(timePunchin.getTime()),
                            sLine.getRcprShift().getFromTime());
                    logger.info("timestamp "+shiftTimestamp);
                    Date actualFromDateTime = timeStampUtil.getMinusMinutesTime(shiftTimestamp,
                            sLine.getRcprShift().getPunchbeginbefore().intValue());
                    logger.info("actualFromDateTime is "+actualFromDateTime);
                    Date actualToDateTime = timeStampUtil.getPlusMinutesTime(shiftTimestamp,
                            sLine.getRcprShift().getInpunchgrace().intValue());
                    logger.info("actualToDateTime is "+actualToDateTime);
                    if(actualFromDateTime.getHours()
                            <= timePunchin.getHours() &&
                            actualToDateTime.getHours() > timePunchin.getHours()) {
                        shiftID=sLine.getRcprShift().getId();
                        logger.info("In Shift Success "+actualToDateTime);
                    }else {
                        logger.info("Shift not Success");
                    }
                }
            }
        }else if(rchrShiftgroup.getShifttype().equals(RchrConstantType.SHIFT_TYPE_ROTATIONAL)){
           for(RchrShiftlines sLine : rchrShiftgroup.getRchrShiftlinesList()){
               Timestamp shiftTimestamp =  timeStampUtil.getPunchTimestamp(new Date(timePunchin.getTime()),
                       sLine.getRcprShift().getFromTime());
               logger.info("timestamp "+shiftTimestamp);
               Date actualFromDateTime = timeStampUtil.getMinusMinutesTime(shiftTimestamp,
                       sLine.getRcprShift().getPunchbeginbefore().intValue());
               logger.info("actualFromDateTime is "+actualFromDateTime);
               Date actualToDateTime = timeStampUtil.getPlusMinutesTime(shiftTimestamp,
                       sLine.getRcprShift().getInpunchgrace().intValue());
               logger.info("actualToDateTime is "+actualToDateTime);
               if(actualFromDateTime.getHours()
                       <= timePunchin.getHours() &&
                       actualToDateTime.getHours() > timePunchin.getHours()) {
                   shiftID=sLine.getRcprShift().getId();
                   logger.info("In Shift Success "+actualToDateTime);
               }else {
                   logger.info("Shift not Success");
               }
           }
       }




        return shiftID;
    }


    public String getEmployeeOTShift(RchrEmployee emp,
                                   java.sql.Timestamp timePunchin,RCHRDailyattend rchrDailyattend){
        TimeStampUtil timeStampUtil = new TimeStampUtil();
        String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
        //String shiftgroID = emp.getRchrShiftgroup().getId();
        //logger.info(" get shift employee no and name is "+emp.getDocumentNo()+" "+emp.getEmployeeName());
        //RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
        List li = this.getShiftMemo(rchrDailyattend.getAttendanceDate(), emp);
        if(li.size()>=1) {
            // System.out.println("In If condition ");
            System.out.println("shiftID 1 " + shiftID);
            RCPRShift rcprShift = OBDal.getInstance().get(RCPRShift.class,shiftID);
            Timestamp shiftMemoTimeStamp =timeStampUtil.getPunchTimestamp(new Date(timePunchin.getTime()),rcprShift.getFromTime());
            Date shiftMemoActualFromDateTime = timeStampUtil.getMinusMinutesTime(shiftMemoTimeStamp,
                    rcprShift.getPunchbeginbefore().intValue());

            Date shiftMemoActualToDateTime = timeStampUtil.getPlusMinutesTime(shiftMemoTimeStamp,
                    rcprShift.getInpunchgrace().intValue());
            if(shiftMemoActualFromDateTime.getHours()
                    <= timePunchin.getHours() &&
                    shiftMemoActualToDateTime.getHours() > timePunchin.getHours()) {
                shiftID = li.get(0).toString();
                //logger.info("In Shift Success "+actualToDateTime);
            }
        }else{
            for(RchrShiftlines sLine : emp.getRchrShiftgroup().getRchrShiftlinesList()){

                Timestamp shiftTimestamp =  timeStampUtil.getPunchTimestamp(new Date(timePunchin.getTime()),
                        sLine.getRcprShift().getFromTime());
                logger.info("timestamp "+shiftTimestamp);
                Date actualFromDateTime = timeStampUtil.getMinusMinutesTime(shiftTimestamp,
                        sLine.getRcprShift().getPunchbeginbefore().intValue());
                logger.info("actualFromDateTime is "+actualFromDateTime);
                Date actualToDateTime = timeStampUtil.getPlusMinutesTime(shiftTimestamp,
                        sLine.getRcprShift().getInpunchgrace().intValue());
                logger.info("actualToDateTime is "+actualToDateTime);
                if(actualFromDateTime.getHours()
                        <= timePunchin.getHours() &&
                        actualToDateTime.getHours() > timePunchin.getHours()) {
                    shiftID=sLine.getRcprShift().getId();
                    logger.info("In Shift Success "+actualToDateTime);
                }else {
                    logger.info("Shift not Success");
                }
            }
        }
        return shiftID;
    }

    public RchrShiftgroup getShiftGroup(RchrEmployee employee,Date date){
        RchrShiftgroup rchrShiftgroup = employee.getRchrShiftgroup();
        String hql = "SELECT aline.rchrShiftgroup from RCHR_RelayShift_Details AS aline "
                + " WHERE aline.employee.id = '" + employee.getId()+ "' "
                + " AND aline.effectivedate <= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) +
                "' AND aline.effectivetodate >= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        logger.info("Size is  "+li.size());
        if(li.size()>=1) {
            rchrShiftgroup = (RchrShiftgroup)li.get(0);
            logger.info("Greater Than one in Shift Group Memo  "+li.size());
        }
        return rchrShiftgroup;
    }

    public List<RchrMRelay> getShiftRelay(RchrEmployee employee,Date date){
        String hql = "SELECT aline.relayName from RCHR_RelayShift_Details AS aline "
                + " WHERE aline.employee.id = '" + employee.getId()+ "' "
                + " AND aline.effectivedate <= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) +
                "' AND aline.effectivetodate >= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        logger.info("Size is  "+li.size());

        return li;
    }



    public List getShiftMemo(Date date, RchrEmployee emp){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String hql = "SELECT newshift.id from Rchr_Memo_Shift AS aline "
                + " WHERE aline.employee.id = '" + emp.getId()+ "' "
                + " AND aline.memfromdate <= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) +
                "' AND aline.memtodate >= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        //System.out.println("Size is 2 "+li.size());
        return li;
    }
    public Boolean isRegularShift(RchrEmployee emp,Date date,RCPRShift rcprShift){

        return Boolean.TRUE;
    }
public String getShiftRotation(Date date, RchrEmployee emp){
        String shiftId=null;
        List li = getShiftMemo(date, emp);
        //ShiftUtil shiftUtil = new ShiftUtil();
        List<RchrMRelay> shiftRelay = this.getShiftRelay(emp,date);
        String mrelayId = "";
        if(emp.getRelay()!=null)
            mrelayId = emp.getRelay().getId();

        if(li.size()>=1){
            // System.out.println("In If condition ");
            shiftId = li.get(0).toString();
            System.out.println("shiftID 1 "+shiftId);

        }else if(shiftRelay.size()>=1){
            logger.info("shiftRelay Name"+shiftRelay);
            mrelayId = (String) shiftRelay.get(0).getId();
            String hql = " SELECT ess FROM Rchr_Relay ess "
                    + " WHERE ess.rchrMrelay.id='" + mrelayId + "' "
                    + " AND ess.fromdate <='" + date + "' "
                    + " AND ess.todate >='" + date + "' "
                    + " AND ess.active=true";
            List<RchrRelay> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();

            for(RchrRelay rly : rlyList){
                shiftId= rly.getShift().getId();
            }

        }else {
            mrelayId=emp.getRelay().getId();
            String hql = " SELECT ess FROM Rchr_Relay ess "
                    + " WHERE ess.rchrMrelay.id='" + mrelayId + "' "
                    + " AND ess.fromdate <='" + date + "' "
                    + " AND ess.todate >='" + date + "' "
                    + " AND ess.active=true";
            List<RchrRelay> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();

            for(RchrRelay rly : rlyList){
                shiftId= rly.getShift().getId();
            }
        }

        return shiftId;
    }


    public String getShiftRotation(RCHRDailyattend rchrDailyattend, RchrEmployee emp){
        String shiftId=getShiftRotation(rchrDailyattend.getAttendanceDate(),emp);

        return shiftId;
    }

    private List getRelayIdByName(String name){
        String hql = " SELECT ess.id FROM Rchr_MRelay ess "
                + " WHERE ess.commercialName='" + name + "' "
                + " AND ess.active=true";
        List<String> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();
        return rlyList;
    }

    public String getEmployeeShift(RchrShiftgroup rchrShiftgroup,
                                   java.sql.Timestamp timePunchin){
        String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
        for(RchrShiftlines sLine : rchrShiftgroup.getRchrShiftlinesList()){

            if(sLine.getRcprShift().getFromTime().getHours()-1 <= timePunchin.getHours() &&
                    sLine.getRcprShift().getToTime().getHours()-3 > timePunchin.getHours())
            {
                shiftID=sLine.getRcprShift().getId();
            }
            else if((0 <= timePunchin.getHours() && 3 >= timePunchin.getHours()) ||
                    (22 <= timePunchin.getHours() && 24 >= timePunchin.getHours()))
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
}
