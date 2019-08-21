package com.redcarpet.payroll.ad_callouts;

import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPL_PayrollTemplateIsAdditionsCall extends SimpleCallout {

    protected void execute(CalloutInfo info) throws ServletException {
        
        String strTabId = info.getStringParameter("inpTabId", null);
        if(StringUtils.equals(strTabId, "EC342B5014E24BCCA02E2232D337531C")){
            info.addResult("inpisaddition", "Y");
        }else{
            info.addResult("inpisaddition", "N");
        }
    }
}


    
