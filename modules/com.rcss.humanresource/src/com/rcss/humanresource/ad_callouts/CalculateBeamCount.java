package com.rcss.humanresource.ad_callouts;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.MathContext;


public class CalculateBeamCount extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	System.out.println("Starting..");
        //String strndId = info.getStringParameter("inprchrQualitystandardId", null);
        BigDecimal grossWght = info.getBigDecimalParameter("inpgrossWeight");
        BigDecimal tareWght = info.getBigDecimalParameter("inptareWeight"); //totalends inptareWeight
        BigDecimal totalEnds = info.getBigDecimalParameter("inptotalends");
        BigDecimal beamLength = info.getBigDecimalParameter("inpbeamlength");
      //  BigDecimal beamLength = info.getBigDecimalParameter("inpbeamlength");
        System.out.println("starting 22");
        
        BigDecimal netWeight = grossWght.subtract(tareWght);
        BigDecimal beamCount = (beamLength.divide(netWeight.divide(totalEnds,MathContext.DECIMAL32),MathContext.DECIMAL32)).divide(new BigDecimal(1693),MathContext.DECIMAL32);
        System.out.println("netweight.."+netWeight);
        System.out.println("netweight.."+beamCount);
        
        info.addResult("inpnetweight", netWeight);
        info.addResult("inpbeamCount", beamCount);
       
        //info.addResult("inpremainingquantity",orderquantity);
        //info.addResult("inprchrInspclothtypeId",strCloth);
       
    }
    
}

