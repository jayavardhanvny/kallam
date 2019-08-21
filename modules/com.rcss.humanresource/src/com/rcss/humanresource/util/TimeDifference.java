package com.rcss.humanresource.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.timeutils.sam.TimeDiffUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class TimeDifference {

    private String strpunchin;
    private String strpunchout;

    public TimeDifference(String strpunchin, String strpunchout) {
        this.strpunchin = strpunchin;
        this.strpunchout = strpunchout;
    }

    public TimeDifference(Timestamp fromTime, Timestamp toTime) {
        this.strpunchin = new SimpleDateFormat("HH:mm:ss").format(fromTime);
        this.strpunchout = new SimpleDateFormat("HH:mm:ss").format(toTime);
    }

    public String getDifference() {
        String finalRes = "";
        try {
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
            finalRes = new String(result.replaceAll("-", ""));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return finalRes;

    }

    public BigDecimal getShiftInMins() {
        String shftHrs = this.getDifference();
        String[] str = shftHrs.split(":");
        int hours = 0;
        int mins = 0;
        int seconds = 0;

        hours = str[0] == null ? 0 : Integer.valueOf(str[0]);
        mins = str[1] == null ? 0 : Integer.valueOf(str[1]);
        seconds = str[2] == null ? 0 : Integer.valueOf(str[2]);

        int result = (hours * 60) + (mins * 1) + (seconds / 60);
        return new BigDecimal(result + "");
    }
}
