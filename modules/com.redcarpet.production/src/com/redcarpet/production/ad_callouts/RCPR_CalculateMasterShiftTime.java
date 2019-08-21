package com.redcarpet.production.ad_callouts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.timeutils.sam.TimeDiffUtil;

public class RCPR_CalculateMasterShiftTime extends SimpleCallout {

    protected void execute(CalloutInfo info) throws ServletException {
        try {
            String strpunchin = info.getStringParameter("inpfromtime", null);
            String strpunchout = info.getStringParameter("inptotime", null);
            System.out.println("strpunchin  is ..."+strpunchin);
		    System.out.println("strpunchout  is ..."+strpunchout);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date dateTo = sdf.parse(strpunchin);
            System.out.println("dateTo  is ..."+dateTo);
            Date dateFrom = sdf.parse(strpunchout);
            System.out.println("dateFrom  is ..."+dateFrom);
            String str[] = strpunchin.split(":");
            String str1[] = strpunchout.split(":");

            if (str.length == 0 || str1.length == 0) {
                throw new NullPointerException("str is null");
            }
            int hours = new Integer(str[0]).intValue();
            int hours2 = new Integer(str1[0]).intValue();
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
            String result = TimeDiffUtil.getworkedhours("13-11-2016", in, out);
            String res = new String(result.replaceAll("-", ""));
            int mins= Integer.parseInt(getShiftInMins(res));
            String newResukt= getShiftInMinsByInp(res, hours, hours2, mins);
            
            if(hours2-hours>0 && mins>=1440){
             info.addResult("inptimedifference", newResukt);
             info.addResult("inpshiftinmins", getShiftInMins(newResukt));
            }else{
            info.addResult("inptimedifference", res);
            info.addResult("inpshiftinmins", getShiftInMins(res));
            }
        } catch (ParseException ex) {
            //ex.printStackTrace();
        }

    }

   public String getShiftInMinsByInp(String brres, int hours, int hours2, int mins) {
       // String brres = new String(breakResult.replaceAll("-", ""));
	   String date3 ="00:00:00";
	   try {
        String time1="24:00:00";
        // String date3;
        String strArry[]=brres.split(":");
        int hrs= new Integer(strArry[0]).intValue();
        if(hours2-hours>0 && mins>=1440){
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date1 = timeFormat.parse(time1);
		Date date2 = timeFormat.parse(brres);
		long summ = date2.getTime() - date1.getTime();
		date3 = timeFormat.format(new Date(summ));
		//System.out.println("time duration2 is... "+date3);
		 
		}
	   }catch (ParseException ex) {
	          // ex.printStackTrace();
	       }
        return date3;
        }
    
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
