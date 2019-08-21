package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.service.db.DalConnectionProvider;
/*
 * @author S.A. Mateen
 * 
 */
public class RCHR_LatestElectricalReadingPopUp extends SimpleCallout {
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        try {
            String strMeterId = info.getStringParameter("inprcmrMeterId", null);
            String strFinalReading = RCHRCalloutUtilsData.getLatestFinalMeterReading(new DalConnectionProvider(), strMeterId);
            if (!StringUtils.isEmpty(strFinalReading)) {
                info.addResult("inpinitialreading", strFinalReading);
            } else {
                info.addResult("inpinitialreading", "0");
            }
        } catch (Exception e) {
        }
    }
}
