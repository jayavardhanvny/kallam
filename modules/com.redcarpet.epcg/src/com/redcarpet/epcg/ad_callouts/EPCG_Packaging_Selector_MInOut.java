package com.redcarpet.epcg.ad_callouts;

import com.redcarpet.epcg.data.EPCG_Packaging;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class EPCG_Packaging_Selector_MInOut extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        try {
            String strPackagingId = info.getStringParameter("inpepcgPackagingId", null);
            EPCG_Packaging packaging = OBDal.getInstance().get(EPCG_Packaging.class, strPackagingId);
            BigDecimal noOfPackages = info.getBigDecimalParameter("inpemEpcgNoofpackages");
            BigDecimal qty = packaging.getTotalweight().multiply(noOfPackages);
            info.addResult("inpmovementqty", qty);
        } catch (Exception e) {
        }
    }
}
