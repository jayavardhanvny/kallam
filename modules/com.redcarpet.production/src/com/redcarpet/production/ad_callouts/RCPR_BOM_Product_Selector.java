package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPR_PrBOM;
import java.math.BigDecimal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.plm.Product;
import org.openbravo.service.db.DalConnectionProvider;

public class RCPR_BOM_Product_Selector extends SimpleCallout {

    protected void execute(CalloutInfo info) throws ServletException {

        if (StringUtils.equals(info.getStringParameter("inpTabId", null), "8F16AEB1B06C405C9CAEDBB2161577D0")) {
            //populate only uom and locator in bom production tab
            String prodId = info.getStringParameter("inpmProductId", null);
            Product product = OBDal.getInstance().get(Product.class, prodId);
            info.addSelect("inpcUomId");
            info.addSelectResult(product.getUOM().getId(), product.getUOM().getName(), true);
            info.endSelect();

        } else {
            //populate everything from bom production tab
            String bomId = info.getStringParameter("inprcprPrbomId", null);
            RCPR_PrBOM bom = OBDal.getInstance().get(RCPR_PrBOM.class, bomId);

            info.addSelect("inpmProductId");
            info.addSelectResult(bom.getProduct().getId(), bom.getProduct().getName(), true);
            info.endSelect();

            info.addSelect("inpcUomId");
            info.addSelectResult(bom.getUOM().getId(), bom.getUOM().getName(), true);
            info.endSelect();

            info.addSelect("inpmLocatorId");
            info.addSelectResult(bom.getStorageBin().getId(), bom.getStorageBin().getSearchKey(), true);
            info.endSelect();

            info.addResult("inpitemcost", getItemCost(bom.getProduct().getId()));



        }

    }

    private BigDecimal getItemCost(String strProductId) throws ServletException {

        String strAvgCost = RCPRGetProductCostData.getProductPrice(new DalConnectionProvider(), strProductId);
        strAvgCost = StringUtils.equalsIgnoreCase(strAvgCost, null) ? "0" : strAvgCost;
        
        if (new BigDecimal(strAvgCost).doubleValue() == 0) {
            String strCost = RCPRGetProductCostData.getPriceListCost(new DalConnectionProvider(), strProductId);
            return new BigDecimal(strCost);
        } else {
            return new BigDecimal(strAvgCost);
        }
    }
}
