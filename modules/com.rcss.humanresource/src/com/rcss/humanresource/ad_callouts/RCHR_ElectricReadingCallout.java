package com.rcss.humanresource.ad_callouts;

import java.math.BigDecimal;
import javax.servlet.ServletException;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author S.A. Mateen
 */
public class RCHR_ElectricReadingCallout extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        BigDecimal finalReading = info.getBigDecimalParameter("inpfinalreading");
        BigDecimal initialReading = info.getBigDecimalParameter("inpinitialreading");
        info.addResult("inptotalreading", finalReading.subtract(initialReading));
    }
    
}
