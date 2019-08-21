package com.redcarpet.production.ad_callouts;

import java.util.Enumeration;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;

public class RCPR_TestCallout extends SimpleCallout {

    protected void execute(CalloutInfo info) throws ServletException {
//        System.out.println("info " + info.getStringParameter("_visibleProperties", null));
//        System.out.println(info.getTabId() + " tabid ");
//        Enumeration<String> en = info.vars.getParameterNames();
//        while (en.hasMoreElements()) {
//            String key = en.nextElement();
//            System.out.println("key is " + key + " value is " + info.getStringParameter(key, null));
//        }
    }
}
