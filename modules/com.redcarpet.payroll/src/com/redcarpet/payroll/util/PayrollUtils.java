package com.redcarpet.payroll.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.openbravo.dal.service.OBDal;

/**
 *
 * @author mateen
 */
public class PayrollUtils implements PayrollPropertiesProvider {

    private static PayrollUtils util = null;
    private static SimpleDateFormat ddMMyyyy = null;
    private static ResourceBundle props = null;
    private static SimpleDateFormat yyyyMMdd = null;

    private PayrollUtils() {
        ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
        yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        initProps();
    }

    public static PayrollUtils getInstance() {
        if (util == null) {
            return new PayrollUtils();
        }
        return util;
    }

    public static int getDaysDifference(Date start, Date end) {
        if (start == null || end == null) {
            return 0;
        }
        return Days.daysBetween(new DateTime(start), new DateTime(end)).getDays()+1;
    }

    public Session getSession() {
        return OBDal.getInstance().getSession();
    }

    public OBDal getOBDSI() {
        return OBDal.getInstance();
    }

    public static String getCalloutCompatibleDate(Date date) {
        return ddMMyyyy.format(date);
    }

    @Override
    public void initProps() {
//        props = ResourceBundle.getBundle("com.redcarpet.payroll.util.Payroll");
//        if (props.keySet().isEmpty()) {
//            throw new PayrollRuntimeException("Properties can't be loaded");
//        }

    }

    public double getProperty(String key) {
        return Double.valueOf(props.getObject(key).toString());
    }

    public String getDBCompatiableDate(Date date) {
        return yyyyMMdd.format(date);
        
    }

    public Date getStartingDate(Date endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String retVal = sdf.format(endDate);
        String strDate = "01"+retVal.subSequence(2, retVal.length());
        return sdf.parse(strDate);
    }

    public double getNoOfDaysInMonth(Date startDate) {
        int day = new DateTime(startDate.getTime()).getDayOfMonth();
        int month = new DateTime(startDate.getTime()).getMonthOfYear();
        int year = new DateTime(startDate.getTime()).getYear();
        DateTime dateTime = new DateTime(year, month, 14, 12, 0, 0, 000);
        return dateTime.dayOfMonth().getMaximumValue();
    }
    
    public Date getEndingDate(Date date) throws ParseException {
    	Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  

        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);  
        Date lastDayOfMonth = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        
        return sdf.parse(sdf.format(lastDayOfMonth));
    }
    
    public Date getStartDate(Date date) throws ParseException {
    	//Date today = new Date(); 
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date); 
    	calendar.set(Calendar.DAY_OF_MONTH,1);
        Date d = calendar.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.parse(sdf.format(d));
    }
    
    public Date getPreviousMonthStartDate() throws ParseException {
    	Date today = new Date(); 
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(today);
    	calendar.add(Calendar.MONTH,-1);
    	Date d = calendar.getTime();
    	DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        return sdf1.parse(sdf1.format(d));
    }
    public Date getPreviousMonthStartDate(Date startDate) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH,-1);
        calendar.set(Calendar.DATE,calendar.getActualMinimum(calendar.DATE));
        return yyyyMMdd.parse(yyyyMMdd.format(calendar.getTime()));
    }
    public Date getPreviousMonthStartDate(String startDate) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ddMMyyyy.parse(startDate));
        calendar.add(Calendar.MONTH,-1);
        calendar.set(Calendar.DATE,calendar.getActualMinimum(calendar.DATE));
        return yyyyMMdd.parse(yyyyMMdd.format(calendar.getTime()));
    }
    public Date getParsedDate(String stringDate) throws ParseException {
        //Date dateFormat = ddMMyyyy.parse(date);
        //DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(dateFormat);
        //calendar.add(Calendar.MONTH,-1);
        //Date d = calendar.getTime();
        return yyyyMMdd.parse(yyyyMMdd.format(ddMMyyyy.parse(stringDate)));
    }
    public Date getParsedDate(Date date) throws ParseException {
        //Date dateFormat = ddMMyyyy.parse(date);
        //DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(dateFormat);
        //calendar.add(Calendar.MONTH,-1);
        //Date d = calendar.getTime();
        return yyyyMMdd.parse(yyyyMMdd.format(date));
    }
    public String getParsedDateString(Date date) throws ParseException {
        //Date dateFormat = ddMMyyyy.parse(date);
        //DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(dateFormat);
        //calendar.add(Calendar.MONTH,-1);
        //Date d = calendar.getTime();
        return getDBCompatiableDate(date);
    }
    public String getParsedDateString(String stringDate) throws ParseException {
        //Date dateFormat = ddMMyyyy.parse(date);
        //DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(dateFormat);
        //calendar.add(Calendar.MONTH,-1);
        //Date d = calendar.getTime();
        return yyyyMMdd.format(ddMMyyyy.parse(stringDate));
    }
}

