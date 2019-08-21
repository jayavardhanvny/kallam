package com.redcarpet.goodsissue.ad_callouts;


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
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
 
import org.openbravo.base.session.OBPropertiesProvider;

public class LineTotalCalculation extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {


        BigDecimal cost = info.getBigDecimalParameter("inpunitprice");       
        BigDecimal qty = info.getBigDecimalParameter("inporderedqty");       
        BigDecimal res=cost.multiply(qty);
            
        info.addResult("inplinenetamt", res);
          
         
     }
    

}