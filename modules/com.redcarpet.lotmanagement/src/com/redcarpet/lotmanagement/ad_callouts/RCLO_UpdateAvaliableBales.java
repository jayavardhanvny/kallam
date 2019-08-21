package com.redcarpet.lotmanagement.ad_callouts;

import com.redcarpet.lotmanagement.data.RCLO_LotReceipt;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author Mateen
 */
public class RCLO_UpdateAvaliableBales extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        
        String lotReceiptId = info.getStringParameter("inprcloLotreceiptId", null);
        RCLO_LotReceipt lotReceipt = OBDal.getInstance().get(RCLO_LotReceipt.class, lotReceiptId);
        BigDecimal openBales = lotReceipt.getOpenBales();
        BigDecimal noOfBalesEntered = info.getBigDecimalParameter("inpnoofbales");
        if(noOfBalesEntered.doubleValue()<0){
            noOfBalesEntered = new BigDecimal(BigInteger.ZERO);
            info.addResult("inpnoofbales", noOfBalesEntered);
        }
        BigDecimal newAvailBales = openBales.subtract(noOfBalesEntered);
        info.addResult("inpavailablelot", newAvailBales);
        info.addResult("inpissuedkgs", noOfBalesEntered.multiply(lotReceipt.getAverageQtyPerBale()));
        
    }
    
}
