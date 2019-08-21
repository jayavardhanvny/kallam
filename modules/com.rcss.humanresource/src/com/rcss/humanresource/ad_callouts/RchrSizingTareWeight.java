package com.rcss.humanresource.ad_callouts;



import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.rcss.humanresource.data.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vinay
 */
public class RchrSizingTareWeight extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strBeamId = info.getStringParameter("inprchrBeamId", null);
    	
    	
    	System.out.println("strBeamId Is" +strBeamId);
    	RCHR_Beam beam = OBDal.getInstance().get(RCHR_Beam.class, strBeamId);
    	
    		 info.addResult("inptareWeight", beam.getTareweight());
    	
        }
    
}
