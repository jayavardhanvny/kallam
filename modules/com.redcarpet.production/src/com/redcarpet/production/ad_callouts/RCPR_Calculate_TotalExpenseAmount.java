package com.redcarpet.production.ad_callouts;

import com.redcarpet.production.data.RCPR_PrBOM;
import com.redcarpet.production.data.RCPR_PrBOMLines;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.utility.OBError;

/**
 *
 * @author S.A. Mateen
 */
public class RCPR_Calculate_TotalExpenseAmount extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strBomId = info.getStringParameter("Rcpr_Prbom_ID", null);
        RCPR_PrBOM bom = OBDal.getInstance().get(RCPR_PrBOM.class, strBomId);
        RCPR_PrBOMLines primaryPrdLine = getPrimaryProductLine(bom);
        if (primaryPrdLine == null) {
            OBError error = new OBError();
            error.setTitle("Error");
            error.setType("Error");
            error.setMessage("Lines does not have primary product");
            info.vars.setMessage("B071A070131C4E86AB6111F5D8B5099A", error);
            info.addResult("inpamount", BigDecimal.ZERO);
        } else {
            BigDecimal uniCost = info.getBigDecimalParameter("inpunitcost");
            BigDecimal totalAmount = primaryPrdLine.getRatio().multiply(uniCost);
            info.addResult("inpamount", totalAmount);
        }

    }

    private RCPR_PrBOMLines getPrimaryProductLine(RCPR_PrBOM bom) {
        List<RCPR_PrBOMLines> lines = bom.getRCPRPrBOMLinesList();
        RCPR_PrBOMLines retVal = null;
        for (RCPR_PrBOMLines line : lines) {
            if (line.isPrimaryProduct()) {
                retVal = line;
                break;
            }
        }
        return retVal;
    }
}
