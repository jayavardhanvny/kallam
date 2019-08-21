package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;

public class Inspqtycallout extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
        String strndId = info.getStringParameter("inprchrQualitystandardId", null);
        
        RCHRQualitystandard standard = OBDal.getInstance().get(RCHRQualitystandard.class, strndId);
        String qstandard = standard.getQstandard();
        String strCloth = standard.getRchrInspclothtype().getId();
        BigDecimal orderquantity = standard.getRemainingquantity();
        BigDecimal warpCount=standard.getWarpcount();
        BigDecimal weftCount=standard.getWeftcount();
        RCHRWarpyarntype warp=standard.getRchrWarpyarntype();
        RCHRWarpyarntype weft=standard.getWarpyarntype();
        //String warpWeftCount = warpCount.toString()+" "+warp.getName()+" X "+weft.getName()+" "+weftCount.toString();
        String warpWeftCount = warpCount.toString()+" "+warp.getName()+" X "+weftCount.toString()+" "+weft.getName();
        info.addResult("inpwarpweftcount", warpWeftCount);
        info.addResult("inpqualityno", qstandard);
        info.addResult("inpremainingquantity",orderquantity);
        info.addResult("inprchrInspclothtypeId",strCloth);
       
    }
    
}
