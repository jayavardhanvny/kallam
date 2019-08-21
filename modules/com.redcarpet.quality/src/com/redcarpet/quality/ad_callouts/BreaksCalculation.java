package com.redcarpet.quality.ad_callouts;

import com.redcarpet.quality.data.RcqaWrapbreak;



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
import java.math.*;
import java.lang.*;
 
import org.openbravo.base.session.OBPropertiesProvider;

public class BreaksCalculation extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {


              
            String strBrk = info.getStringParameter("inptotbrkcaused", null);
      		
      		System.out.println("strBrk "+strBrk); 
      		

            String strWrap = info.getStringParameter("inptotwraplength", null);
            
            System.out.println(strWrap + " is strWrap ");
            
            
            BigDecimal brknew=new BigDecimal(strBrk.replaceAll(",", ""));
            System.out.println("after conversion brknew" +brknew);
           
            BigDecimal wrapnew=new BigDecimal(strWrap.replaceAll(",", ""));
            
            System.out.println("after conversion brknew" +wrapnew);
            
            BigDecimal resone=brknew.divide(wrapnew, 20, RoundingMode.HALF_UP);  
            
            System.out.println("divide res is" +resone);
            
            BigDecimal mulvalue=new BigDecimal("1000000");
            


            BigDecimal restwo=resone.multiply(mulvalue);


                          
                         
                           System.out.println("total is" +restwo);
                             
                           info.addResult("inpbreaks", restwo);

      		          
                       
     }


}
