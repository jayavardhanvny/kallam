package com.redcarpet.epcg.ad_callouts;

import com.redcarpet.epcg.data.Epcg_EpcgNew;
import com.redcarpet.epcg.data.EpcgMacSpare;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.math.BigDecimal;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;
 
import org.openbravo.base.session.OBPropertiesProvider;

public class PermitToDateCallout extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException 
	
	{

  		String strFrom = info.getStringParameter("inppermitfromdate", null);
		
		
		System.out.println("strFrom    "+strFrom); // YYYY-MM-DD HH:mm:ss Z
	
        String strYears = info.getStringParameter("inptenuremonths", null);
        
        System.out.println(strYears + " is strYears ");
   
      
        BigDecimal yearnew=new BigDecimal(strYears.replaceAll(",", ""));
        
        System.out.println("after conversion yearnew" +yearnew);
        
       
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = null;
		Date date2 = null;

         		
             	try{
             		date1 = sdf.parse(strFrom);
             		System.out.println("after convert into String" + date1);
             		
             		 int years=yearnew.intValue();
             		// years=years-1;
 		   			
 		   			
		             Calendar c = Calendar.getInstance(); 
		             c.setTime(date1); 
		             c.add(Calendar.YEAR,years);
		             c.add(Calendar.DAY_OF_MONTH, -1);
		             date1 =c.getTime(); 
		             
		             System.out.println("date1 after inc" + date1);
		             
		             String s2=sdf.format(date1);
		             System.out.println("s2 is:" +s2);
		             //String s1="hai";
		            
		             info.addResult("inppermittodate", s2);
		             
		         
             	}
		             
		             catch(Exception e){
							System.out.println(e);
							
					      	}
    
}
}
