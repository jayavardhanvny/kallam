package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.EmployeeUtil;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OnDutyProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle)  {
        String strOnDutyId = (String)bundle.getParams().get("Rchr_Onduty_ID");
        OBError msg = new OBError();
        try{

            RchrOnduty info=OBDal.getInstance().get(RchrOnduty.class,strOnDutyId);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(info.getFromDate().toString()));

            Calendar c2 = Calendar.getInstance();
            c2.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(info.getToDate().toString()));
            process(info,c1,c2);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("OnDuty Process has been completed");
        }catch(Exception ex){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }

        bundle.setResult(msg);
    }

    private void process(RchrOnduty info,Calendar c1,Calendar c2){
        long totalDays=getTotalDays(c1,c2);
        long start=0;
        long weekoff=1,days=1;
        while(start<totalDays){
            RchrOndutylines line= OBProvider.getInstance().get(RchrOndutylines.class);
            line.setOrganization(info.getOrganization());
            line.setClient(info.getClient());
            EmployeeUtil employeeUtil = new EmployeeUtil();
            String weekOff = employeeUtil.getEmployeeWeekOffForPayroll(info.getEmployee(),c1.getTime());
            if(info.getEmployee().isWeekOffApplicable()&&

                    (c1.get(Calendar.DAY_OF_WEEK)==getDay(weekOff))){
            line.setWeeklyOff(Boolean.TRUE);
                info.setTotalweekoff(weekoff++);
            }else{
                info.setTotal(days++);
            }
            line.setOddate(c1.getTime());
            line.setRchrOnduty(info);
            OBDal.getInstance().save(line);
            OBDal.getInstance().flush();
            c1.add(Calendar.DATE,1);
            //System.out.println("Hello");
            start++;
            info.setTotalonduty(start);
        }
	info.setProcess(Boolean.TRUE);
    }

    private int getTotalDays(Calendar c1,Calendar c2){

        Calendar c3=Calendar.getInstance();
        c3.setTime(c1.getTime());

        Calendar c4=Calendar.getInstance();
        c4.setTime(c2.getTime());

        long diff = (c4.getTimeInMillis()-c3.getTimeInMillis())/(1000*60*60*24);
        System.out.println("total days.."+(diff+1));
        return (int)(diff+1);
    }

    private int getWeekoffs(Calendar c1,Calendar c2,RchrEmployee e) {
        Calendar c3=Calendar.getInstance();
        c3.setTime(c1.getTime());

        Calendar c4=Calendar.getInstance();
        c4.setTime(c2.getTime());

        int weekdays = 0;
        long start=0;
        long diff = (c4.getTimeInMillis()-c3.getTimeInMillis())/(1000*60*60*24);
        while(start++<(diff+1)){
            if(c3.get(Calendar.DAY_OF_WEEK)==getDay(e.getWeeklyOff()))
                weekdays++;
            c3.add(Calendar.DATE,1);
        }
        System.out.println("week days.."+(weekdays));
        return weekdays;
    }

    private int getDay(String day) {

        int weekOff = -1;

        if (StringUtils.equalsIgnoreCase(day, "SUNDAY")) {
            weekOff = 1;
        } else if (StringUtils.equalsIgnoreCase(day, "MONDAY")) {
            weekOff = 2;
        } else if (StringUtils.equalsIgnoreCase(day, "TUESDAY")) {
            weekOff = 3;
        } else if (StringUtils.equalsIgnoreCase(day, "WEDNESDAY")) {
            weekOff = 4;
        } else if (StringUtils.equalsIgnoreCase(day, "THURSDAY")) {
            weekOff = 5;
        } else if (StringUtils.equalsIgnoreCase(day, "FRIDAY")) {
            weekOff = 6;
        } else if (StringUtils.equalsIgnoreCase(day, "SATURDAY")) {
            weekOff = 7;
        }else{
            weekOff=-1;
        }
        return weekOff;
    }
}
