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
import java.math.RoundingMode;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;

import java.math.*;
 
import org.openbravo.base.session.OBPropertiesProvider;

public class MacSparesCallout extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {


              
              String strQuantity = info.getStringParameter("inpquantity", null);
      		
      		System.out.println("strQuantity "+strQuantity); 
      		

            String strrate = info.getStringParameter("inprate", null);
            
            System.out.println(strrate + " is strrate ");
            //int quantnew= Integer.parseInt(strQuantity);
            
            BigDecimal quant=new BigDecimal(strQuantity.replaceAll(",", ""));
            BigDecimal ratenew=new BigDecimal(strrate.replaceAll(",", ""));
            
             // long slivernew=Long.parseLong(strSliver);
             // long noilnew=Long.parseLong(strNoil);
           
                           BigDecimal res=quant.multiply(ratenew);
                            
                           System.out.println("total is" +res);
                           
                           int quantnew=quant.intValue();



                         

                        // int ratenew= Integer.parseInt(strrate);
                         //  long res=quant*ratenew;
                             info.addResult("inptotal", res);
                             info.addResult("inpstillqntypurchase", quantnew);
                            

      		          
                       
     }


}
