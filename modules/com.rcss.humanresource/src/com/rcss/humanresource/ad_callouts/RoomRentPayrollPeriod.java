package com.rcss.humanresource.ad_callouts;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import com.redcarpet.payroll.util.PayrollUtils;
import com.rcss.humanresource.data.RchrAttdProcess;
import com.rcss.humanresource.data.RchrRoomrent;
import javax.servlet.ServletException;


public class RoomRentPayrollPeriod extends SimpleCallout{
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