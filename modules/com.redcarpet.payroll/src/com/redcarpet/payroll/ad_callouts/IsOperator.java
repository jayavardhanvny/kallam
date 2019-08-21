package com.redcarpet.payroll.ad_callouts;
import org.openbravo.base.provider.OBProvider;
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

import com.redcarpet.payroll.util.PayrollTypesConstants;
import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;

import org.apache.commons.lang.time.DateUtils;
import org.openbravo.base.session.OBPropertiesProvider;

public class IsOperator extends SimpleCallout {
	
	

    @Override
    protected void execute(CalloutInfo info) throws ServletException 
    {
    	String id = info.getStringParameter("inprchrEmployeeId", null);
    	
    	RchrEmployee emp=OBDal.getInstance().get(RchrEmployee.class,id);
    	
    	if(emp.getEmployeeType().equals(PayrollTypesConstants.EMPLOYEE_TYPE_WO)){
    		
    		info.addResult("inpisoperator","Y");
    	}
    	else {
    		info.addResult("inpisoperator","N");
    	}
    	        
       }
}
