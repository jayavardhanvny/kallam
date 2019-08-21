package com.redcarpet.goodsissue.ad_callouts;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import javax.servlet.ServletException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DepartmentIssueConversionUOM extends SimpleCallout {

    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {

        String productId = info.getStringParameter("inpmProductId",null);
        String orgId=info.getStringParameter("inpadOrgId",null);
        BigDecimal orderQty=info.getBigDecimalParameter("inporderedqty");
        info.addResult("inpcUomId", getActualUOM(productId,orgId));
        info.addResult("inpmLocatorId",getLocation(productId,orgId));
        info.addResult("inpmovingavgprice", getMovingAveragePrice(productId));
        info.addResult("inpunitprice",getMovingAveragePrice(productId));
        info.addResult("inpmovementqty", getConversionRate(productId, orgId).multiply(orderQty));
        info.addResult("inpopenqty", getOpenQty(productId, orgId));
        info.addResult("inpopeninventoryqty", getInventoryQty(productId, orgId));
        info.addResult("inplinenetamt", orderQty.multiply(getMovingAveragePrice(productId)));

    }

    private String getActualUOM(String productId,String orgId){
        String hql=" SELECT p.uOM.id FROM Product p WHERE p.organization.id='"+orgId+"' AND p.id='"+productId+"'";
        List<String> li= OBDal.getInstance().getSession().createQuery(hql).list();
      //  System.out.println("uom.."+li.get(0));
        return li.get(0);
    }

    private String getLocation(String productId,String orgId){
        String hql=" SELECT p.locator.id FROM Rcob_Storagebins p WHERE p.product.id='"+productId+"' AND p.organization.id='"+orgId+"'";
        List<String> li= OBDal.getInstance().getSession().createQuery(hql).list();
     //   System.out.println("bin.."+li.get(0));
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
       // System.out.println("conversion"+li.get(0));
        return li.get(0);

    }

    /*private BigDecimal getProductSellingPrice(String productId, String orgId){
        try{
            Date currentDate=Calendar.getInstance().getTime();
        String hql = " SELECT p.listPrice FROM PricingProductPrice p WHERE p.product.id='" + productId + "' AND p.rcgiEffectivefrom<='"+currentDate+"' AND p.organization.id='" + orgId + "'";
        List<BigDecimal> li = OBDal.getInstance().getSession().createQuery(hql).list();
        System.out.println("conversion"+li.get(0));
        return li.size()>0?li.get(0):new BigDecimal(0);
        }
        catch(Exception e){
            e.printStackTrace();
            return new BigDecimal(0);
        }
    }*/

    private BigDecimal getOpenQty(String productId, String orgId){
        String hql = " SELECT COALESCE(SUM(t.openQuantity),0) FROM RCGI_Transactions t WHERE t.product.id='" + productId + "' AND t.organization.id='" + orgId + "'";
        List<BigDecimal> li = OBDal.getInstance().getSession().createQuery(hql).list();
       // System.out.println("conversion"+li.get(0));
        return li.size()>0?li.get(0):new BigDecimal(0);
    }

    private BigDecimal getInventoryQty(String productId, String orgId){
        String hql = " SELECT COALESCE(SUM(t.openinventoruqty),0) FROM RCGI_Transactions t WHERE t.product.id='" + productId + "' AND t.organization.id='" + orgId + "'";
        List<BigDecimal> li = OBDal.getInstance().getSession().createQuery(hql).list();
      //  System.out.println("conversion"+li.get(0));
        return li.size()>0?li.get(0):new BigDecimal(0);
    }

    /*private BigDecimal getPriceFromCombo(String productId, String orgId){
       return getProductSellingPrice(productId,orgId).divide(getConversionRate(productId,orgId).setScale(0,BigDecimal.ROUND_CEILING));
    }*/

    private BigDecimal getMovingAveragePrice(String productId, String orgId){
        org.openbravo.model.common.plm.Product product = OBDal.getInstance().get(Product.class, productId);

        String hql=" SELECT SUM(t.openQuantity*cost)/SUM(t.openQuantity) AS avgprice FROM RCGI_Transactions t WHERE t.product.id='"+productId+"' AND t.organization.id='"+orgId+"' AND t.openQuantity>0";
        List<BigDecimal> li=OBDal.getInstance().getSession().createQuery(hql).list();
        return li.size()>0? product.getRcgiAvgcp().add(li.get(0).multiply(new BigDecimal(0.05))).setScale(2,BigDecimal.ROUND_HALF_UP): new BigDecimal(0);
    }
    private BigDecimal getMovingAveragePrice(String productId){
        Product product = OBDal.getInstance().get(Product.class, productId);
        return product.getRcgiAvgcp().intValue()>0 ? product.getRcgiAvgcp().add(product.getRcgiAvgcp().multiply(new BigDecimal(0.05))).setScale(2,BigDecimal.ROUND_HALF_UP): new BigDecimal(0);
    }
}
