package com.redcarpet.goodsissue.ad_callouts;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;


import javax.servlet.ServletException;
import java.math.BigDecimal;
import java.util.List;

public class DepartmentReceiptConversionUOM extends SimpleCallout {

    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {

        String productId = info.getStringParameter("inpmProductId",null);
        String orgId=info.getStringParameter("inpadOrgId",null);
        BigDecimal orderQty=info.getBigDecimalParameter("inporderedqty");
        info.addResult("inpcUomId", getActualUOM(productId,orgId));
        info.addResult("inpmLocatorId",getLocation(productId,orgId));
       // info.addResult("inpinventoryuom",getConversionUOM(productId,orgId));
        info.addResult("inpmovementqty", getConversionRate(productId, orgId).multiply(orderQty));
        info.addResult("inplinenetamt", orderQty.multiply(new BigDecimal(0)));
    }

    private String getActualUOM(String productId,String orgId){
        String hql=" SELECT p.uOM.id FROM Product p WHERE p.organization.id='"+orgId+"' AND p.id='"+productId+"'";
        List<String> li= OBDal.getInstance().getSession().createQuery(hql).list();
        System.out.println("uom.."+li.get(0));
        return li.get(0);
    }

    private String getLocation(String productId,String orgId){
        String hql=" SELECT p.locator.id FROM Rcob_Storagebins p WHERE p.product.id='"+productId+"' AND p.organization.id='"+orgId+"'";
        List<String> li= OBDal.getInstance().getSession().createQuery(hql).list();
        System.out.println("bin.."+li.get(0));
        return li.get(0);
    }

    private String getConversionUOM(String productId,String orgId){
        String hql=" SELECT p.uOM.id FROM RCGI_ProductConversion p WHERE p.product.id='"+productId+"' AND p.organization.id='"+orgId+"'";
        List<String> li= OBDal.getInstance().getSession().createQuery(hql).list();
      //  System.out.println("conversion"+li.get(0));
        return li.get(0);
    }

    private BigDecimal getConversionRate(String productId, String orgId) {
        String hql = " SELECT COALESCE(p.weight,0) FROM Product p WHERE p.id='" + productId + "' AND p.organization.id='" + orgId + "'";
        List<BigDecimal> li = OBDal.getInstance().getSession().createQuery(hql).list();
        System.out.println("conversion"+li.get(0));
        return li.get(0);

    }
}
