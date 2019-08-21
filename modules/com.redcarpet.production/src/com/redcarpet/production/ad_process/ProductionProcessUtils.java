/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_PrProduct;
import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.plm.Product;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Administrator
 */
public class ProductionProcessUtils {
    
    public static boolean isNegativeQuantityAllowed(RCPR_ProductionRun run) throws ServletException, Exception {
        boolean retVal = false;
        
        try {
            if (!OBDal.getInstance().get(Client.class, run.getClient().getId()).getClientInformationList().get(0).isAllowNegativeStock()) {
                if (checkStockExists(run)) {
                    retVal = true;
                }
            } else {
                retVal = true;
            }
        } catch (Exception e) {
            throw new Exception("Client Information Error");
        }
        return retVal;
    }

    public static boolean isNegativeQuantityAllowed(RCPR_PrProduct line) throws ServletException, Exception {
        boolean retVal = false;
        
        try {
            if (!OBDal.getInstance().get(Client.class, line.getProductionRun().getClient().getId()).getClientInformationList().get(0).isAllowNegativeStock()) {
                if (checkStockExists(line)) {
                    retVal = true;
                }
            } else {
                retVal = true;
            }
        } catch (Exception e) {
            throw new Exception("Client Information Error");
        }
        return retVal;
    }
    
    private static boolean checkStockExists(RCPR_ProductionRun run) throws ServletException {
        double qtyInHand = getQuantityInHand(run.getProduct(), run.getStorageBin());
        if(qtyInHand<run.getIssueQuantity().doubleValue()){
            return false;
        }else{
            return true;
        }
        
    }
    private static boolean checkStockExists(RCPR_PrProduct line) throws ServletException {
        double qtyInHand = getQuantityInHand(line.getProduct(), line.getProductionRun().getStorageBin());
        if(qtyInHand<line.getQuantity().doubleValue()){
            return false;
        }else{
            return true;
        }
        
    }

    private static double getQuantityInHand(Product product, Locator locator) throws ServletException {
        String strQtyInHand = RCPRSelectPriceData.getQuantityInHand(new DalConnectionProvider(), product.getId(), locator.getId());
        return StringUtils.equals(null, strQtyInHand) ? 0 : new BigDecimal(strQtyInHand).doubleValue();
    }
}
