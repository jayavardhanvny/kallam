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

public class PieceNoCallout extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
    	String chare = "";
    	String yChare = "";
    	  String strPeriodId = info.getStringParameter("inpcPeriodId", null);
    	  Period period = OBDal.getInstance().get(Period.class, strPeriodId);
    	  
    	  String strYearId = info.getStringParameter("inpcYearId", null);
    	  org.openbravo.model.financialmgmt.calendar.Year year = OBDal.getInstance().get(org.openbravo.model.financialmgmt.calendar.Year.class, strYearId);
    	  
    	  
    	  
    	  if (!StringUtils.isEmpty(strPeriodId)) {
    	  
    	  info.addResult("inpfromdate", PayrollUtils.getInstance().getCalloutCompatibleDate(period.getStartingDate()));
          info.addResult("inptodate", PayrollUtils.getInstance().getCalloutCompatibleDate(period.getEndingDate()));
          
     
    	  } 
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
    	  
    	  
	    int monthno =period.getStartingDate().getMonth();
	    
	    if(monthno==0){
	    	chare="A";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==1){
	    	chare="B";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==2){
	    	chare="C";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(monthno==3){
	    	chare="D";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(monthno==4){
	    	chare="E";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==5){
	    	chare="F";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==6){
	    	chare="G";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==7){
	    	chare="H";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(monthno==8){
	    	chare="I";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==9){
	    	chare="J";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==10){
	    	chare="K";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(monthno==11){
	    	chare="L";
	    	 
	    }else{
	    	chare="Enter Char";
	    }
	    
	    
	    
	    
	    String yearno =year.getFiscalYear();
	    if(yearno.equals("2014")){
	    	yChare="Z";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2015")){
	    	yChare="Y";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2016")){
	    	yChare="X";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2017")){
	    	yChare="W";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2018")){
	    	yChare="V";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2019")){
	    	yChare="U";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2020")){
	    	yChare="T";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2021")){
	    	yChare="S";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2022")){
	    	yChare="R";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2023")){
	    	yChare="Q";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2024")){
	    	yChare="P";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2025")){
	    	yChare="O";
	    	 
	    }else if(yearno.equals("2026")){
	    	yChare="N";
	    	 ////info.addResult("inppiecechar", yChare);
	    }else if(yearno.equals("2027")){
	    	yChare="M";
	    	 ////info.addResult("inppiecechar", yChare);
	    }else if(yearno.equals("2028")){
	    	yChare="L";
	    	 ////info.addResult("inppiecechar", yChare);
	    }else if(yearno.equals("2029")){
	    	yChare="K";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2030")){
	    	yChare="I";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2031")){
	    	yChare="H";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2032")){
	    	yChare="G";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2033")){
	    	yChare="F";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2034")){
	    	yChare="E";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2035")){
	    	yChare="D";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2036")){
	    	yChare="C";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2037")){
	    	yChare="B";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearno.equals("2038")){
	    	yChare="A";
	    	 ////info.addResult("inppiecechar", yChare);
	    }else{
	    	yChare="Enter Char";
	    }
	    
	    
	    info.addResult("inppiecechar", chare);
	   System.out.println(yChare+chare +"    "+ monthno);
    }
}
