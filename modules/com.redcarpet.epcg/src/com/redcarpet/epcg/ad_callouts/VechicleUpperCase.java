package com.redcarpet.epcg.ad_callouts;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;
import java.math.BigDecimal;

public class VechicleUpperCase extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String vehicleNo = info.getStringParameter("inpvehicleno",null);
        System.out.println("vehicleno.."+vehicleNo.toUpperCase().replaceAll("\\s+",""));
        info.addResult("inpvehicleno", vehicleNo.toUpperCase().replaceAll("\\s+",""));


    }
}
