package com.rcss.humanresource.ad_callouts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import org.openbravo.model.ad.domain.Callout;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;
import org.openbravo.base.session.OBPropertiesProvider;
import org.apache.commons.lang.StringUtils;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrEmployee;

public class CoffEmployeeWeeklyOff extends SimpleCallout{
	@Override
	public void execute(CalloutInfo info) throws ServletException{
		
		String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
        RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, strEmployeeId);
        info.addResult("inpweeklyoff", rchrEmployee.getWeeklyOff());
		
	
}}