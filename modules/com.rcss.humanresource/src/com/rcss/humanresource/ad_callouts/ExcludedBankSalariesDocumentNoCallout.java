package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.util.DocumentNumberUtil;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;

public class ExcludedBankSalariesDocumentNoCallout extends SimpleCallout {
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        String strDocType = info.getStringParameter("inpcDoctypeId", null);
        String strPaymentDate = info.getStringParameter("inppaymentdate", null);

        DocumentNumberUtil util = new DocumentNumberUtil();
        String documentNo = util.getDocumentNo(strDocType, strPaymentDate);
        info.addResult("inpdocumentno", documentNo);
    }
}

