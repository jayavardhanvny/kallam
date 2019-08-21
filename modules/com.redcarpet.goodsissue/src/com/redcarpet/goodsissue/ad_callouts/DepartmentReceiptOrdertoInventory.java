package com.redcarpet.goodsissue.ad_callouts;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;
import java.math.BigDecimal;
import java.util.List;

public class DepartmentReceiptOrdertoInventory extends SimpleCallout {

    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {

        String productId = info.getStringParameter("inpmProductId", null);
        String orgId = info.getStringParameter("inpadOrgId", null);
        BigDecimal orderQty=info.getBigDecimalParameter("inporderedqty");
        BigDecimal price=info.getBigDecimalParameter("inpunitprice");
        System.out.println("qty"+orderQty);
        info.addResult("inpmovementqty", getConversionRate(productId, orgId).multiply(orderQty));
        info.addResult("inplinenetamt", orderQty.multiply(price));

    }

    private BigDecimal getConversionRate(String productId, String orgId) {
        try {
            String hql = " SELECT COALESCE(p.weight,0) FROM Product p WHERE p.id='" + productId + "' AND p.organization.id='" + orgId + "'";
            List<BigDecimal> li = OBDal.getInstance().getSession().createQuery(hql).list();
            System.out.println("conversion" + li.get(0));
            return li.get(0);
        }catch(Exception e){
            return new BigDecimal(0);
        }
    }
}
