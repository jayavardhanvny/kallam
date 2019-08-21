package com.redcarpet.goodsissue.ad_callouts;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;
import java.math.BigDecimal;

public class DepartmentIssueDiscount extends SimpleCallout {

    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {

        BigDecimal movingAveragePrice=info.getBigDecimalParameter("inpmovingavgprice");
        BigDecimal discount=info.getBigDecimalParameter("inpdiscount");
        BigDecimal orderQty=info.getBigDecimalParameter("inporderedqty");
        BigDecimal discountOnPrice=(movingAveragePrice).multiply(discount.divide(new BigDecimal(100)));
        System.out.println("finalPrice..."+movingAveragePrice);
        info.addResult("inpunitprice",movingAveragePrice.subtract(discountOnPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
        info.addResult("inplinenetamt", orderQty.multiply(movingAveragePrice.subtract(discountOnPrice).setScale(2,BigDecimal.ROUND_HALF_UP)));
    }
}
