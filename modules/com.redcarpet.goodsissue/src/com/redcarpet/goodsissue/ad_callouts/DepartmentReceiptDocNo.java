package com.redcarpet.goodsissue.ad_callouts;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.rcss.humanresource.util.DocumentNumberUtil;
import javax.servlet.ServletException;

public class DepartmentReceiptDocNo extends SimpleCallout {
    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {
        // TODO Auto-generated method stub
        String strDoctypeId = info.getStringParameter("inpcDoctypeId", null);
        String strMovementDate = info.getStringParameter("inpmovementdate", null);
        DocumentNumberUtil documentNumberUtil = new DocumentNumberUtil();
        //documentNumberUtil.getDocumentNo(strDoctypeId,strMovementDate);
        info.addResult("inpdocumentno", documentNumberUtil.getDocumentNo(strDoctypeId,strMovementDate));
    }
}
