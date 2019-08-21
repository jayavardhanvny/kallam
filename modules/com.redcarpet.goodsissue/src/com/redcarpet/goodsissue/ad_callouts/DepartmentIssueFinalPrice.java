package com.redcarpet.goodsissue.ad_callouts;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;
import java.math.BigDecimal;

public class DepartmentIssueFinalPrice extends SimpleCallout{

    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {

        BigDecimal standredPrice=info.getBigDecimalParameter("inppricestd");
        BigDecimal extraPercentage=info.getBigDecimalParameter("inpemRcgiExtrapercentage");
        BigDecimal discount=info.getBigDecimalParameter("inpemRcgiDiscount");
        System.out.println("qty"+standredPrice.add(standredPrice.multiply(extraPercentage.divide(new BigDecimal(100)).setScale(2))).setScale(2,BigDecimal.ROUND_HALF_UP));
        BigDecimal extraValue=standredPrice.multiply(extraPercentage.divide(new BigDecimal(100)));
        BigDecimal discountValue=(standredPrice.add(extraValue)).multiply(discount.divide(new BigDecimal(100)));
        System.out.println("extraValue"+extraValue);
        System.out.println("discountValue"+discountValue);
        //info.addResult("inppricelist",standredPrice.add(standredPrice.multiply(extraPercentage.divide(new BigDecimal(100)).setScale(2))).setScale(2,BigDecimal.ROUND_HALF_UP));
        info.addResult("inppricelist",standredPrice.add(extraValue).subtract(discountValue).setScale(2,BigDecimal.ROUND_HALF_UP));
    }
}
