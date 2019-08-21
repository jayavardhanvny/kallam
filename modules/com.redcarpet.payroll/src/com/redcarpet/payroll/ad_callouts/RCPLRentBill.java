package com.redcarpet.payroll.ad_callouts;
import org.openbravo.base.secureApp.VariablesSecureApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;

import org.apache.commons.lang.time.DateUtils;
import org.openbravo.base.session.OBPropertiesProvider;

public class RCPLRentBill extends SimpleCallout {
	
	
    @Override
    protected void execute(CalloutInfo info) throws ServletException 
    {
    	 String strOrgId = info.getStringParameter("inpadOrgId", null);
         String strEmpId = info.getStringParameter("inprchrEmployeeId", null);
         if (strEmpId != null) {
         	String roomid=getRoomId(strOrgId,strEmpId,info);
         	info.addResult("inprchrRoomId",roomid);
         }
    }
    public String getRoomId(String strOrgId, String strEmpId,CalloutInfo info){
   	 
        String roomid="";
        try {
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String qry="select rchr_room_id from rchr_employee  where (rchr_employee_id='"+strEmpId+"') and (AD_Org_ID='"+ strOrgId+"')";
            ResultSet rs1=stmt.executeQuery(qry);
            while(rs1.next()){
            	System.out.println("id"+rs1.getString(1));
            	roomid=rs1.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomid;
   	
   }

}