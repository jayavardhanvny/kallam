package com.redcarpet.epcg.ad_callouts;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class EpcgPackingTemplateCallout extends SimpleCallout{
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        BigDecimal totalYards = info.getBigDecimalParameter("inptotalyards");
        BigDecimal lengthPerPiece = info.getBigDecimalParameter("inplengthperpiece");
        info.addResult("inpnoofpieces",totalYards.divide(lengthPerPiece).setScale(2, RoundingMode.HALF_EVEN));
    }
}
