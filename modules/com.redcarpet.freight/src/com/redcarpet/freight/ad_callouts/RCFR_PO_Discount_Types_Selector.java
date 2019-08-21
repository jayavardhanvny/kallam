package com.redcarpet.freight.ad_callouts;

import com.redcarpet.freight.ad_process.RCFR_CostType_Constants;
import java.util.Enumeration;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author S.A. Mateen
 */
public class RCFR_PO_Discount_Types_Selector extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        String strDiscountType = info.getStringParameter("inpdiscounttype", null);
        boolean isDistributedCost = StringUtils.equalsIgnoreCase(RCFR_CostType_Constants.DISCOUNT_DISTCOST, strDiscountType);
        if(isDistributedCost){
            info.addSelect("inpcostrule");
            info.addSelectResult(RCFR_CostType_Constants.COST_CALCULATION_TYPE1, RCFR_CostType_Constants.COST_CALCULATION_TYPE1);
            info.endSelect();
            info.addResult("inpemRcfrDiscounttype", "N");
        }else{
            info.addSelect("inpcostrule");
            info.addSelectResult(RCFR_CostType_Constants.COST_CALCULATION_TYPE1, RCFR_CostType_Constants.COST_CALCULATION_TYPE1);
            info.addSelectResult(RCFR_CostType_Constants.COST_CALCULATION_TYPE2, RCFR_CostType_Constants.COST_CALCULATION_TYPE2);
            info.addSelectResult(RCFR_CostType_Constants.COST_CALCULATION_TYPE3, RCFR_CostType_Constants.COST_CALCULATION_TYPE3);
            info.endSelect();
            info.addResult("inpemRcfrDiscounttype", "N");
            
        }
        
    }
    
}
