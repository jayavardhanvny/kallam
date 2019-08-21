package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.financialmgmt.calendar.Period;
import com.redcarpet.payroll.util.PayrollUtils;
import org.apache.commons.lang.StringUtils;
import com.rcss.humanresource.data.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

public class RollNumberYearCodeCallout extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
    	String chare = "";
    	  String strYearId = info.getStringParameter("inpcYearId", null);
    	  org.openbravo.model.financialmgmt.calendar.Year year = OBDal.getInstance().get(org.openbravo.model.financialmgmt.calendar.Year.class, strYearId);
    	  
    	  //System.out.println(year.getFiscalYear());
    	 // Period period = OBDal.getInstance().get(Period.class, strPeriodId);
    	  /*if (!StringUtils.isEmpty(strPeriodId)) {
    	  
    	  info.addResult("inpfromdate", PayrollUtils.getInstance().getCalloutCompatibleDate(period.getStartingDate()));
          info.addResult("inptodate", PayrollUtils.getInstance().getCalloutCompatibleDate(period.getEndingDate()));
          
     
    	  } */
    	 /* 
    	  String strFromdate = info.getStringParameter("inpfromdate", null);
    	  Date presentDate=new Date();
    	  try{
  			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
  			 presentDate = sdf.parse(strFromdate);
          }
          catch (ParseException e) {
             e.printStackTrace();
         }
//    	  Calendar headercal=Calendar.getInstance();*/
	   // int monthno = presentDate.getMonth();
	    String yearno =year.getFiscalYear();
	    if(yearno.equals("2014")){
	    	chare="Z";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2015")){
	    	chare="Y";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2016")){
	    	chare="X";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2017")){
	    	chare="W";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2018")){
	    	chare="V";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2019")){
	    	chare="U";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2020")){
	    	chare="T";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2021")){
	    	chare="S";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2022")){
	    	chare="R";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2023")){
	    	chare="Q";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2024")){
	    	chare="P";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2025")){
	    	chare="O";
	    	 
	    }else if(yearno.equals("2026")){
	    	chare="N";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2027")){
	    	chare="M";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2028")){
	    	chare="L";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2029")){
	    	chare="K";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2030")){
	    	chare="I";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2031")){
	    	chare="H";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2032")){
	    	chare="G";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2033")){
	    	chare="F";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2034")){
	    	chare="E";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2035")){
	    	chare="D";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2036")){
	    	chare="C";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2037")){
	    	chare="B";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2038")){
	    	chare="A";
	    	 ////info.addResult("inppiecechar", chare);
	    }else{
	    	chare="Enter Char";
	    }
	    info.addResult("inpyearchar", chare);
	   // System.out.println(chare +"    "+ monthno)
    }
}
