/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.freight.ad_callouts;

import java.math.BigDecimal;
import java.util.Enumeration;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.order.Order;


/**
 *
 * @author Administrator
 */
public class RCFR_RecalculateLinetNetAmount extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        RCFR_CalculationsUtil reCalc = null;
        BigDecimal netUnitPrice = BigDecimal.ZERO;
        String strProduct = info.getStringParameter("inpmProductId", null);
        String strCOrderId=info.getStringParameter("inpcOrderId",null);
        Order ord = OBDal.getInstance().get(Order.class, strCOrderId);
        
        BigDecimal unitPrice = info.getBigDecimalParameter("inpemRcfrPrice");
        
        if(ord.isSalesTransaction())
        {
        	unitPrice=info.getBigDecimalParameter("inppriceactual");
        }
       
        
        BigDecimal freight = info.getBigDecimalParameter("inpemRcfrFreight");
        BigDecimal insuracne = info.getBigDecimalParameter("inpemRcfrInsurance");
        BigDecimal packaging = info.getBigDecimalParameter("inpemRcfrPnf");
        BigDecimal obDiscount = info.getBigDecimalParameter("inpdiscount");
        BigDecimal qtyOrdered = info.getBigDecimalParameter("inpqtyordered");

        reCalc = new RCFR_CalculationsUtil(strProduct, unitPrice, freight, insuracne, packaging, obDiscount, qtyOrdered);
        if (OBDal.getInstance().get(Product.class, strProduct).isRcloIslot()) {
            netUnitPrice = reCalc.calculateCottonLintProductPrice();
        } else {
            netUnitPrice = reCalc.calculateProductPrice();
        }
        
        if(ord.isSalesTransaction())
    	{
    	info.addResult("inpemRcfrNetunitrate",netUnitPrice);
    	}
        else
        {
        info.addResult("inppriceactual", netUnitPrice);
        }
        
        
        info.addResult("inplinenetamt", netUnitPrice.multiply(qtyOrdered));
        info.addResult("inpemRcfrDisco", reCalc.calculateDiscount().multiply(qtyOrdered));
        info.addResult("inpemRcfrCalcdistcost", "N");
    }

}
