package com.redcarpet.goodsissue.ad_callouts;

import com.redcarpet.epcg.data.EPCG_Packaging;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;
import java.math.BigDecimal;

public class PackingCalculation extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        BigDecimal noofpackes = info.getBigDecimalParameter("inpnoofpacks");
        String  packingType = info.getStringParameter("inpepcgPackagingId",null);       
        BigDecimal orederQty=noofpackes.multiply(OBDal.getInstance().get(EPCG_Packaging.class,packingType).getTotalweight());
	    info.addResult("inpnoofcones",OBDal.getInstance().get(EPCG_Packaging.class,packingType).getNoofcones().multiply(noofpackes));
	    info.addResult("inpavgconeweight",OBDal.getInstance().get(EPCG_Packaging.class,packingType).getConeweight());
        info.addResult("inporderedqty",orederQty);
        info.addResult("inppackcones",OBDal.getInstance().get(EPCG_Packaging.class,packingType).getNoofcones());
        info.addResult("inppackconeweight",OBDal.getInstance().get(EPCG_Packaging.class,packingType).getConeweight());
        info.addResult("inppacktotalweight",OBDal.getInstance().get(EPCG_Packaging.class,packingType).getTotalweight());

    }
}
