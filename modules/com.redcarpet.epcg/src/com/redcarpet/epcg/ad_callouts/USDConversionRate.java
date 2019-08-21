package com.redcarpet.epcg.ad_callouts;


import java.math.BigDecimal;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.currency.ConversionRate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Suneetha
 */
public class USDConversionRate extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strcurrencyid = info.getStringParameter("inpcCurrencyId", null);
        System.out.println(strcurrencyid + " is strcurrencyid ");
        
        try {
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sqry = "select C_Conversion_Rate.multiplyrate from C_Conversion_Rate where C_Currency_Id='" + strcurrencyid + "'";
            System.out.println("query is" + sqry);
            ResultSet rs = stmt.executeQuery(sqry);
            while (rs.next()) {
                BigDecimal usddivrate = rs.getBigDecimal("multiplyrate");		
                System.out.println(usddivrate + "is usddivrate");
                info.addResult("inpemEpcgUsdrate", usddivrate);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
