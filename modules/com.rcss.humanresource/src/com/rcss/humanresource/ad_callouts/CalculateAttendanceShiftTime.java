package com.rcss.humanresource.ad_callouts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.timeutils.sam.TimeDiffUtil;

public class CalculateAttendanceShiftTime extends SimpleCallout {

    protected void execute(CalloutInfo info) throws ServletException {
        try {
            String strpunchin = info.getStringParameter("inppunchin", null);
            String strpunchout = info.getStringParameter("inppunchout", null);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date dateTo = sdf.parse(strpunchin);
            Date dateFrom = sdf.parse(strpunchout);
            String str[] = strpunchin.split(":");
            String str1[] = strpunchout.split(":");

            if (str.length == 0 || str1.length == 0) {
                throw new NullPointerException("str is null");
            }
            int hours = new Integer(str[0]).intValue();
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
            String out = sdf.format(new Date(calFrom.getTimeInMillis()));
            String result = TimeDiffUtil.getworkedhours("13-11-2013", in, out);
            String res = new String(result.replaceAll("-", ""));
            info.addResult("inpduration", res);
//          info.addResult("inpshiftinmins", getShiftInMinsByInp(info));
            //info.addResult("inpduration", getShiftInMins(res));
        } catch (ParseException ex) {
            //ex.printStackTrace();
        }

    }

//    public long getShiftInMinsByInp(CalloutInfo info) {
//        long retVal = 0;
//        try {
//            String strFromDate = info.getStringParameter("inpfromtime", null);
//            strFromDate = "13-11-2013 "+strFromDate;
//            String strToDate = info.getStringParameter("inptotime", null);
//            strToDate = "13-11-2013 "+strToDate;
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//            Date dateFrom = sdf.parse(strFromDate);
//            Date dateTo = sdf.parse(strToDate);
//            retVal = (dateTo.getTime() - dateFrom.getTime()) / 1000 / 60;
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
//        return retVal;
//    }
    
    public String getShiftInMins(String shftHrs){
        String[] str = shftHrs.split(":");
        int hours = 0;
        int mins = 0;
        int seconds = 0;
        
        hours = str[0] == null ? 0 : Integer.valueOf(str[0]);
        mins = str[1] == null ? 0 : Integer.valueOf(str[1]);
        seconds = str[2] == null ? 0 : Integer.valueOf(str[2]);
        
        int result = (hours *60) + (mins * 1) + (seconds/60);
        return result+"";
    }
}
