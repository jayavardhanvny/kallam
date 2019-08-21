package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.financialmgmt.calendar.Period;
import com.redcarpet.payroll.util.PayrollUtils;
import org.apache.commons.lang.StringUtils;
import com.rcss.humanresource.data.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

public class ProductionIncCallout extends SimpleCallout{
   @Override
        protected void execute(CalloutInfo info) throws ServletException {

            String strPeriodId = info.getStringParameter("inprchrAttdProcessId", null);
            if (!StringUtils.isEmpty(strPeriodId)) {
                
                RchrAttdProcess period = OBDal.getInstance().get(RchrAttdProcess.class, strPeriodId);
                info.addResult("inpfromdate", PayrollUtils.getInstance().getCalloutCompatibleDate(period.getStartingDate()));
                info.addResult("inptodate", PayrollUtils.getInstance().getCalloutCompatibleDate(period.getEndingDate()));                
            }
        }
}
