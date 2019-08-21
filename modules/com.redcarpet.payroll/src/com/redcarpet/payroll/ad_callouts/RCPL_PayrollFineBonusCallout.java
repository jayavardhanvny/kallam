package com.redcarpet.payroll.ad_callouts;

import java.util.Enumeration;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class RCPL_PayrollFineBonusCallout extends SimpleCallout {

    protected void execute(CalloutInfo info) throws ServletException {
        String strTabId = info.getStringParameter("inpTabId", null);
        if(StringUtils.equals(strTabId, "4AB0F9E583264D31A87FAFDD756F03F0")){
            info.addResult("inpbonus", "Y");
        }else{
            info.addResult("inpbonus", "N");
        }
    }
}


    
