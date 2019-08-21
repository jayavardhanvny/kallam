package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;


public class GetTareWeightFromBeam extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
        String beamId = info.getStringParameter("inprchrBeamId", null);
        
        RCHR_Beam beam = OBDal.getInstance().get(RCHR_Beam.class, beamId);
        //String qstandard = standard.getQstandard();
        //String strCloth = standard.getRchrInspclothtype().getId();
        //BigDecimal orderquantity = standard.getRemainingquantity();
        BigDecimal tareWeight = beam.getTareweight();
        info.addResult("inptareWeight", tareWeight);
        
       
    }
    
}