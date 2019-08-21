package com.redcarpet.epcg.ad_callouts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.math.*;
import java.sql.Time;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;
import org.openbravo.base.session.OBPropertiesProvider;
import com.redcarpet.epcg.data.*;
import org.openbravo.utils.FormatUtilities;



public class YearStartEndDates extends SimpleCallout{
	
    protected void execute(CalloutInfo info) throws ServletException {
    try{
    	
    	String strYearId = info.getStringParameter("inpcYearId", null);
    	System.out.println("strYearId is" +strYearId);
       
    	 Date startdate=null;
		 Date enddate=null;
		  
		  try{
	      		 Connection conn = OBDal.getInstance().getConnection();
	                  Statement stmt = conn.createStatement();
	                  
	                  String ss="select min(startdate) as startdate, max(enddate) as enddate from c_period where c_year_id='" + strYearId + "'";
	                  System.out.println("ss is" +ss);
	                  
	                  ResultSet rs = stmt.executeQuery(ss);
	                 
	                  
	                  while (rs.next()) {
	                	  startdate = rs.getDate("startdate");
	                	  enddate = rs.getDate("enddate");
	                      System.out.println("startdate is" + startdate);
	                      System.out.println("enddate is" + enddate);

	              } 
	                  }catch (Exception e) {
	                  e.printStackTrace();
	              }
	     
		  
		  SimpleDateFormat sdfmt2= new SimpleDateFormat("dd-MM-yyyy");
		   
		  String strFrom = sdfmt2.format( startdate );
		  String strTo = sdfmt2.format( enddate );

    	
    	info.addResult("inpstartdate",FormatUtilities.replaceJS(strFrom));
    	info.addResult("inpenddate",FormatUtilities.replaceJS(strTo));

	}
    catch(Exception e){
    	System.out.println(e);}
}

}