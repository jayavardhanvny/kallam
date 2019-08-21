package com.rcss.humanresource.ad_callouts;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.math.BigDecimal;
import com.timeutils.sam.TimeDiffUtil;
import java.sql.Time;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;
 
import org.openbravo.base.session.OBPropertiesProvider;

public class DurationCalculation extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

//    	  try {
//              String strpunchin = info.getStringParameter("inpstarttime", null);
//              String strpunchout = info.getStringParameter("inpendtime", null);
//              System.out.println(strpunchin + " is InTime " + strpunchout + " is OutTime -----------> ");
//
//              SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//              Date dateTo = sdf.parse(strpunchin);
//              Date dateFrom = sdf.parse(strpunchout);
//              String str[] = strpunchin.split(":");
//              String str1[] = strpunchout.split(":");
//
//              if (str.length == 0 || str1.length == 0) {
//                  throw new NullPointerException("str is null");
//              }
//              int hours = new Integer(str[0]).intValue();
//              System.out.println("hosurs " + hours);
//              int increment = 0;
//              if (hours < 6) {
//                  increment = -5;
//              } else {
//                  increment = -18;
//              }
//
//              Calendar calTo = Calendar.getInstance();
//              calTo.setTime(dateTo);
//              calTo.add(Calendar.HOUR, increment);
//              calTo.add(Calendar.MINUTE, -30);
//
//              Calendar calFrom = Calendar.getInstance();
//              calFrom.setTime(dateFrom);
//              calFrom.add(Calendar.HOUR, increment);
//              calFrom.add(Calendar.MINUTE, -30);
//
//              String in = sdf.format(new Date(calTo.getTimeInMillis()));
//              String out = sdf.format(new Date(calFrom.getTimeInMillis()));
//              System.out.println("strin " + in + " strout " + out + " ==============>");
//              String result = TimeDiffUtil.getworkedhours("13-11-2013", in, out);
//              String res = new String(result.replaceAll("-", ""));
//              System.out.println("res: "+res);
//              System.out.println("result " + result);
//              info.addResult("inpduration", res);
//          } catch (ParseException ex) {
//              ex.printStackTrace();
//          }
                       
     }


      }
