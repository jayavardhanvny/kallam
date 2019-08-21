package com.redcarpet.goodsissue.ad_callouts;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;
import java.math.BigDecimal;

public class MRGlitemCostCalculation extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {


        BigDecimal price = info.getBigDecimalParameter("inpunitprice");
        System.out.println("unitprice is" +price);
        BigDecimal qty = info.getBigDecimalParameter("inporderedqty");
        System.out.println("ordered qty is" +qty);

        BigDecimal res=price.multiply(qty);
        System.out.println("result of line total is:" +res);
        info.addResult("inplinenetamt", res);
    }
}
