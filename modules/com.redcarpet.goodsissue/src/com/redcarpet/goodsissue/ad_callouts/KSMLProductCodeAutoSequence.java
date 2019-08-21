package com.redcarpet.goodsissue.ad_callouts;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;


import javax.servlet.ServletException;
import java.math.BigDecimal;
import java.util.List;

public class KSMLProductCodeAutoSequence extends SimpleCallout{

    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {
        String productId = info.getStringParameter("inpmProductId",null);
        info.addResult("inpemRcgiKsmlbarcode", getNextNo());
    }

    private String getNextNo(){
        String hql=" SELECT MAX(p.rcgiKsmlbarcode) FROM Product p";
        List<String> li= OBDal.getInstance().getSession().createQuery(hql).list();
        if(li.size()==0) return Long.valueOf(1001).toString();
        else if(li.get(0)==null) return Long.valueOf(1001).toString();
        else return Long.valueOf(Long.valueOf(li.get(0))+Long.valueOf(1)).toString();
    }
}
