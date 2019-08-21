/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.freight.ad_callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Enumeration;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.tax.TaxRate;

/**
 *
 * @author Administrator
 */
public class RCFR_CalculateNetUnitPrice extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        doIt(info);
        
    }

    private void doIt(CalloutInfo info) throws ServletException {
        Enumeration<String> en = info.vars.getParameterNames();
        String strCOrderId = info.getStringParameter("C_Order_ID", null);
        Order order = OBDal.getInstance().get(Order.class, strCOrderId);
        System.out.println(StringUtils.equalsIgnoreCase("NetUnit Price", order.getEpcgRatetype()));
        if(StringUtils.equalsIgnoreCase("NetUnit Price", order.getEpcgRatetype())){
            BigDecimal netUnitRate = info.getBigDecimalParameter("inpemRcfrNetunitrate");
            String strTaxId = info.getStringParameter("inpcTaxId", null);
            TaxRate tax = OBDal.getInstance().get(TaxRate.class, strTaxId);
            BigDecimal temp = new BigDecimal(1).add((tax.getRate().divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
            BigDecimal netUnitPrice = (netUnitRate.divide(temp, 6, RoundingMode.HALF_UP)).multiply(tax.getRate().multiply(new BigDecimal(0.01)));
            info.addResult("inppriceactual", netUnitRate.subtract(netUnitPrice));
        }else{
            
        }
    }
}
