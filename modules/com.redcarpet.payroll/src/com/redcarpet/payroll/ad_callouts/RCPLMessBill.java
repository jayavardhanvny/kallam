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
import com.redcarpet.payroll.data.RCPLMessType;

public class RCPLMessBill extends SimpleCallout {
	
	
	BigDecimal breakfast,lunch,dinner,lunchCost,dinnerCost,totalLunchMeals,totalDinnerMeals,totalMeals;
    @Override
    protected void execute(CalloutInfo info) throws ServletException 
    {
    	MathContext mc = new MathContext(2, RoundingMode.FLOOR);
    	
    	 breakfast = info.getBigDecimalParameter("inpbreakfast");
         
    	 lunch = info.getBigDecimalParameter("inplunch");
         
    	 dinner = info.getBigDecimalParameter("inpdinner");
     
    	
    	String id = info.getStringParameter("inprcplMesstypeId", null);
    	RCPLMessType messType=OBDal.getInstance().get(RCPLMessType.class,id);
        lunchCost=messType.getLunchcost();
        //System.out.println("lunch cost.."+lunchCost);
        dinnerCost=messType.getDinnercost();
        //System.out.println("dinner cost.."+dinnerCost);
        /*totalLunchMeals=lunch.divide(lunchCost, mc);
        System.out.println("lunch meals"+totalLunchMeals);
        totalDinnerMeals=dinner.divide(dinnerCost, mc);
        System.out.println("dinner meals"+totalDinnerMeals);
        totalMeals=totalLunchMeals.add(totalDinnerMeals);*/
       // System.out.println("total meals.."+totalMeals);
    	info.addResult("inpsum",breakfast.add(lunch).add(dinner));
    	info.addResult("inptotalmeals",lunch.divide(lunchCost,mc).add(dinner.divide(dinnerCost,mc)));	 
         
       }
       

}
