package com.redcarpet.quality.ad_callouts;

import com.redcarpet.quality.data.RcqaCombernoil;



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

public class TotalNoilDisplay extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {


              
            String strSliver = info.getStringParameter("inpsliver", null);
      		
      		System.out.println("strSliver "+strSliver); 
      		

            String strNoil = info.getStringParameter("inpnoil", null);
            
            System.out.println(strNoil + " is strNoil ");


            BigDecimal slivernew=new BigDecimal(strSliver);
            BigDecimal noilnew=new BigDecimal(strNoil);
            
             // long slivernew=Long.parseLong(strSliver);
             // long noilnew=Long.parseLong(strNoil);
                         
                           BigDecimal res=slivernew.add(noilnew);
                            
                           System.out.println("total is" +res);
                             
                           info.addResult("inptotalnoil", res);

      		          
                       
     }


}
