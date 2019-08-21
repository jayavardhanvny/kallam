package com.redcarpet.epcg.ad_callouts;

import com.redcarpet.epcg.data.EPCG_Packaging;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class EPCG_Packaging_TotalWeight_Calc extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        
        String strPackagingId = info.getStringParameter("inpepcgPackagingId", null);
        BigDecimal noOfCones = info.getBigDecimalParameter("inpnoofcones");
        BigDecimal coneWeight = info.getBigDecimalParameter("inpconeweight");
        BigDecimal totalWeight = noOfCones.multiply(coneWeight);
        info.addResult("inptotalweight", totalWeight);
        
    }
}
