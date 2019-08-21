/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.lotmanagement.ad_callouts;

import com.redcarpet.lotmanagement.data.RCLO_LotReceipt;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author Mateen
 */
public class RCLO_GetAvailableBales extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        
        String lotReceiptId = info.getStringParameter("inprcloLotreceiptId", null);
        RCLO_LotReceipt lotReceipt = OBDal.getInstance().get(RCLO_LotReceipt.class, lotReceiptId);
        info.addSelect("inpcDoctypeId");
        if(lotReceipt.getGoodsShipmentLine()==null){            
            info.addSelectResult("8646653F978B4E05B9644934FAD76EA5", "Production", true);
        }else{
            info.addSelectResult("2753CB9943A447AABDD2E8E6DA361F05", "MM Receipt", true);
        }
        info.endSelect();
        info.addResult("inpavailablelot", lotReceipt.getOpenBales());
        info.addResult("inpissuedkgs", "0");
    }
    
}
