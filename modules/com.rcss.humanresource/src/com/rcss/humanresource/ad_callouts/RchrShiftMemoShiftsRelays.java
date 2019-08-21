
package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;

import org.hibernate.Session;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.rcss.humanresource.data.*;


import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class RchrShiftMemoShiftsRelays extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String tabId = info.getStringParameter("inpTabId", null);

        if(tabId.equals("700F8B7B48B147BEB7115DAD9B3631AC")){ // Window name = Shift Memo, Table Name= Rchr_Memo_Shift
            executeShiftMemoCallout(info);
        }else if(tabId.equals("018570FEAEEE4A5E995B3B5D0063DD5E")){ // Window name = Relay And ShiftGroup Details, Table Name= RCHR_RelayShift_Details
            executeShiftGroupRelayCallout(info);
        }else if (tabId.equals("AD0F65E3247E47CF995F1A47E6701A7B")){ // Window name = WeeklyOff Details, Table Name= RCHR_WeeklyOffDetails
            executeWeeklyOffs(info);
        }else if (tabId.equals("22DDF1EAE0734E4394F694A5C3B8336F")){ // Window name = Employee Job Details, Table Name= RCHR_JobDetails
            executeJobDetails(info);
        }

    }
    private void executeJobDetails(CalloutInfo info){
        String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, strEmployeeId);
        info.addResult("inpcurrentdepartment",emp.getEmployeeDepartment().getCommercialName());
        info.addResult("inpcurrentsubdepartment",emp.getSubDepartment().getName());
        info.addResult("inpcurrentdesignation",emp.getDesignation().getCommercialName());

    }
    private void executeWeeklyOffs(CalloutInfo info){
        String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);

        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, strEmployeeId);
        System.out.println(" emp.getWeeklyOff() "+emp.getWeeklyOff());
        info.addResult("inpcurrentweeklyoff", emp.getWeeklyOff());
    }
    private void executeShiftGroupRelayCallout(CalloutInfo info){
        String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
        String strFromDate = info.getStringParameter("inpeffectivedate", null);
        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, strEmployeeId);
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //System.out.println("strFromDate "+strFromDate);
        //SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String valueShiftGroup   = emp.getRchrShiftgroup()==null ? "Newly Joined" : emp.getRchrShiftgroup().getName();
        String valueShiftRelay = emp.getRelay()==null ? "Newly Joined" : emp.getRelay().getCommercialName();
        info.addResult("inpcurrentshiftgroup", valueShiftGroup);
        info.addResult("inpcurrentrelay", valueShiftRelay);
        //info.addResult("inpcurrentrelay", getEmployeeShift(date,emp.getRelay().getId()));
    }
    private void executeShiftMemoCallout(CalloutInfo info){

        String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
        String strFromDate = info.getStringParameter("inpmemfromdate", null);
        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, strEmployeeId);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("strFromDate "+strFromDate);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");


        Date date =null;
        try{
            System.out.println("1 date "+sdf2.format(sdf.parse(strFromDate)));
            date = sdf.parse(strFromDate);
            System.out.println("sdf2 date "+sdf2.parse(strFromDate));
        }catch(Exception e){

        }
        System.out.println("date "+date);
        info.addResult("inprchrShiftgroupId", emp.getRchrShiftgroup().getId());
        info.addResult("inprchrMrelayId", emp.getRelay().getId());
        info.addResult("inprcprShiftId", getEmployeeShift(date,emp.getRelay().getId()));

    }
  //get employee shift

    public String getEmployeeShift(Date fromdate , String relayId){
      String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
      
      String hql = "SELECT shift.id from Rchr_Relay AS aline "
          + " WHERE aline.rchrMrelay.id = '" + relayId + "' " 
          + " AND aline.fromdate <= '" + sdf.format(fromdate) + "' AND aline.todate >= '" + sdf.format(fromdate) + "' "
          + " AND 1 = 1";
      System.out.println("sdf.format(fromdate) "+sdf.format(fromdate));
      Session session = OBDal.getInstance().getSession();
      List li = session.createQuery(hql).list();
      System.out.println("Sizze "+li.size());
    if(li.size()>=1){
      // System.out.println("In If condition ");
       
         shiftID = li.get(0).toString();
         System.out.println("shiftID 1 "+shiftID);
         
         
     
     }
    
         return shiftID;
    }
}
