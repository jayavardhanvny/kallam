package com.rcss.humanresource.ad_callouts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.rcss.humanresource.data.*;
import javax.servlet.ServletException;
import java.math.BigDecimal;

public class RCHR_VehicleReadingCallout extends SimpleCallout{
	@Override
    protected void execute(CalloutInfo info) throws ServletException {
		String vehicleid = info.getStringParameter("inprchrVehiclemasterId", null);
		BigDecimal inreading = info.getBigDecimalParameter("inpinreading");
		BigDecimal outreading = info.getBigDecimalParameter("inpoutreading");		
//		RCHRVehiclemaster vehiclemaster = OBDal.getInstance().get(RCHRVehiclemaster.class, vehicleid);
//		info.addResult("inpinreading", vehiclemaster.getLastreading());
		if(outreading.longValue() > 0 && (outreading.longValue() > inreading.longValue())){
		info.addResult("inptravelkms", (outreading.longValue()-inreading.longValue()));
		}
	}
}