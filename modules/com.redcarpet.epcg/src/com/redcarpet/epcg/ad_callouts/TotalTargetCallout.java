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

public class TotalTargetCallout extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {


            String strYears = info.getStringParameter("inptenuremonths", null);
            
            System.out.println(strYears + " is strYears ");
            
            String strPermit = info.getStringParameter("inppermittotal", null);
      		
      		System.out.println("strPermit "+strPermit); 
      		
            
         
            BigDecimal permitnew=new BigDecimal(strPermit.replaceAll(",", ""));
            System.out.println("after conversion permitnew" +permitnew);
           
            BigDecimal yearnew=new BigDecimal(strYears.replaceAll(",", ""));
            
            System.out.println("after conversion yearnew" +yearnew);
            
            BigDecimal res=permitnew.multiply(yearnew);
            System.out.println("result of total target is:" +res);
         
             info.addResult("inptotaltarget", res);
          
         
     }
    

}
