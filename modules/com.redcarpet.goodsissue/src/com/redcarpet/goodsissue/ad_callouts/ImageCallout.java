package com.redcarpet.goodsissue.ad_callouts;

import com.rcss.humanresource.data.RchrEmployee;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.ad.utility.Image;

import javax.servlet.ServletException;
import java.math.BigDecimal;

public class ImageCallout extends SimpleCallout {

    @Override
    protected void execute(SimpleCallout.CalloutInfo info) throws ServletException {
        String productId = info.getStringParameter("inprchrEmployeeId", null);
        Image img= OBDal.getInstance().get(RchrEmployee.class,productId).getImage();
        if(img!=null)
        //System.out.println(img.getId());
        info.addResult("inpadImageId", img.getId());
        else info.addResult("inpadImageId",null);


    }
}
