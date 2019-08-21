package com.redcarpet.production.ad_callouts;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;
/*
 * @description: This callout is not being used
 */
public class RCPR_Auto_ProductionRunCallout extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        
        String strProductionId = info.getStringParameter("inpmProductionId", null);
        //info ["inpadOrgId","inpisactive","inpdocumentno","inpmProductionId","inpmovementdate","inpstartdate","inpstarttime","inpexenddate","inpexendtime","inprequiredqty","inpmProductId","inpcUomId"]
        
    }
}
