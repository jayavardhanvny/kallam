/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.production.ad_process;

import java.math.BigDecimal;
import java.util.Iterator;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.pricing.pricelist.ProductPrice;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author mateen
 */
public class RCPR_Generate_Average_Cost extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
//        System.out.println("inside "+this.getClass().getName());
//        Iterator<String> it = bundle.getParams().keySet().iterator();
//        while(it.hasNext()){
//            String key = it.next();
//            System.out.println("key is "+key +" value is "+bundle.getParams().get(key));
//        }
        RCPRProductionCostingLinesData[] data = RCPRProductionCostingLinesData.getCostingLines(new DalConnectionProvider(), "3F9E302C1D2A4F8194B1BAA3813BF5F3");
        for(RCPRProductionCostingLinesData datum : data){
            String orgId = datum.adOrgId;
            String mProductId = datum.mProductId;
            String totQty = datum.totalqty;
            String totPrice = datum.totalprice;
            BigDecimal productPrice = new BigDecimal(totPrice).divide(new BigDecimal(totQty), 5, BigDecimal.ROUND_HALF_UP);
            
            String strPriceListId = RCPRProductionCostingLinesData.getPriceListId(new DalConnectionProvider(), mProductId);
            ProductPrice prodPrice = OBDal.getInstance().get(ProductPrice.class, strPriceListId);
            prodPrice.setListPrice(productPrice);
            prodPrice.setStandardPrice(productPrice);
            prodPrice.setCost(productPrice);
            
        }
    }
    
}
