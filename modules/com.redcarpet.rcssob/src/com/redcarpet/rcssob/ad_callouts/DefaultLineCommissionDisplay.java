package com.redcarpet.rcssob.ad_callouts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;

 
import org.openbravo.base.session.OBPropertiesProvider;

public class DefaultLineCommissionDisplay extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {


        String agentId = info.getStringParameter("inpcBpartnerId", null);
        System.out.println("agentId is..." +agentId);
        
        BusinessPartner bpartner = OBDal.getInstance().get(BusinessPartner.class, agentId);
        BigDecimal commission= bpartner.getRcobCommission();
      
        System.out.println("commission is...." +commission);
 
            info.addResult("inpcommission", commission);
          
         
     }
    

}
